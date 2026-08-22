package io.github.cpearl0.ctnhcore.integration.ftbessentials;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.mixin.mc.ServerChunkCacheAccessor;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.DistanceManager;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.mojang.datafixers.util.Either;
import dev.architectury.event.EventResult;
import dev.ftb.mods.ftbessentials.FTBEssentialsEvents;
import dev.ftb.mods.ftbessentials.command.TeleportCommands;
import dev.ftb.mods.ftbessentials.config.FTBEConfig;
import dev.ftb.mods.ftbessentials.util.DimensionFilter;
import dev.ftb.mods.ftbessentials.util.FTBEPlayerData;
import dev.ftb.mods.ftbessentials.util.TeleportPos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = CTNHCore.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class AsyncRtpManager {

    /**
     * How many candidate chunks a single search keeps generating at the same time.
     * More candidates in flight = shorter wall-clock time per search, at the cost of
     * more concurrent FULL chunk generations (which all run on the server thread).
     * Bounded by the global cap below.
     */
    private static final int WINDOW_SIZE = 2;

    /**
     * Global cap on concurrent FULL chunk requests across all searches. This keeps the
     * server-side load bounded even when many players use /rtp at once: instead of the
     * old "every player generates 1 chunk serially", we generate up to this many in
     * parallel and queue the rest.
     */
    private static final int MAX_GLOBAL_IN_FLIGHT = 4;

    /**
     * Fallback neighbour chunks are generated with at most this many in flight at a time.
     * A 3x3 fallback scan would otherwise fire 8 FULL generations in one tick and stall
     * the server thread; the remaining neighbours are dispatched one by one as they finish.
     */
    private static final int FALLBACK_CONCURRENCY = 2;

    /**
     * Resample guard so the sampler can never spin forever on rejected points
     * (already-tried chunks, world-border rejects, RTP_EVENT vetoes).
     */
    private static final int MAX_RESAMPLE = 128;

    private static final Map<UUID, Search> SEARCHES = new HashMap<>();

    /** Searches currently blocked by {@link #MAX_GLOBAL_IN_FLIGHT}; woken by releaseGlobalSlots(). */
    private static final Set<Search> WAITING = new LinkedHashSet<>();

    /** Mutated/read only on the server thread (all chunk callbacks re-enter via server.execute). */
    private static int globalInFlight;

    /**
     * Ticket level used to keep a candidate chunk alive (fully generated, but not ticking)
     * while the search inspects it. Without a ticket, a holder created for a remote chunk
     * can be unloaded again before the FULL future completes (observed as 100%
     * ChunkLoadingFailure via the private getChunkFutureMainThread path).
     */
    private static final int CHUNK_TICKET_LEVEL = 33;

    /** Custom ticket type so the search does not interfere with vanilla/forge chunk tickets. */
    private static final TicketType<Unit> RTP_TICKET = TicketType.create("ctnhcore:rtp", (left, right) -> 0);

    private AsyncRtpManager() {}

    public static int start(ServerPlayer player) {
        if (!player.hasPermissions(2)) {
            TeleportPos.TeleportResult blacklistResult = new TeleportPos(player).checkDimensionBlacklist(player);
            if (!blacklistResult.isSuccess()) {
                return blacklistResult.runCommand(player);
            }
            if (!DimensionFilter.isRtpDimensionOK(player.level().dimension())) {
                player.sendSystemMessage(Component.literal("You may not use /rtp in this dimension!")
                        .withStyle(ChatFormatting.RED));
                return 0;
            }
        }

        UUID playerId = player.getUUID();
        if (SEARCHES.containsKey(playerId)) {
            player.sendSystemMessage(Component.literal("Already looking for a random location!")
                    .withStyle(ChatFormatting.YELLOW));
            return 0;
        }

        return FTBEPlayerData.getOrCreate(player).map(data -> {
            TeleportPos.TeleportResult cooldownResult = data.rtpTeleporter.checkCooldown();
            if (!cooldownResult.isSuccess()) {
                return cooldownResult.runCommand(player);
            }

            MinecraftServer server = player.getServer();
            if (server == null) {
                return 0;
            }

            Search search = new Search(
                    playerId,
                    server,
                    player.level().dimension(),
                    data,
                    FTBEConfig.RTP_MAX_TRIES.get());
            SEARCHES.put(playerId, search);
            player.sendSystemMessage(Component.literal("Looking for random location..."));
            fillWindow(search);
            return 1;
        }).orElse(0);
    }

    /**
     * Sliding-window fill: keep up to {@link Search#windowSize} candidate chunks in
     * flight at all times (bounded globally by {@link #MAX_GLOBAL_IN_FLIGHT}). When a
     * candidate is rejected, the next one has already been generating in the background,
     * so failures no longer cost a full serial chunk-generation round-trip.
     */
    private static void fillWindow(Search search) {
        ServerPlayer player = currentPlayer(search);
        ServerLevel level = currentLevel(search, player);
        if (level == null) {
            cancel(search);
            return;
        }
        if (search.fallbackActive) {
            return; // fallback scan owns the search until it finishes; it re-fills the window
        }

        while (search.inFlight < search.windowSize && search.nextAttempt < search.maxTries) {
            if (globalInFlight >= MAX_GLOBAL_IN_FLIGHT) {
                WAITING.add(search);
                return;
            }

            Candidate candidate = nextCandidate(search, level, player);
            if (candidate == null) {
                // Could not sample a fresh chunk (resample guard hit): treat as exhausted.
                search.nextAttempt = search.maxTries;
                break;
            }

            search.nextAttempt++;
            search.inFlight++;
            globalInFlight++;
            ChunkPos chunkPos = new ChunkPos(candidate.pos);
            search.attemptedChunks.add(ChunkPos.asLong(chunkPos.x, chunkPos.z));
            requestChunk(search, level, chunkPos).whenComplete((result, error) -> search.server.execute(() -> {
                globalInFlight--;
                releaseGlobalSlots();
                onCandidateComplete(search, candidate, result, error);
            }));
        }

        if (search.inFlight == 0 && search.nextAttempt >= search.maxTries) {
            fail(search);
        }
    }

    /** Samples a fresh candidate, skipping chunks already attempted and world-border rejects. */
    private static Candidate nextCandidate(Search search, ServerLevel level, ServerPlayer player) {
        for (int i = 0; i < MAX_RESAMPLE; i++) {
            double distance = FTBEConfig.RTP_MIN_DISTANCE.get() +
                    level.random.nextDouble() * (FTBEConfig.RTP_MAX_DISTANCE.get() - FTBEConfig.RTP_MIN_DISTANCE.get());
            double angle = level.random.nextDouble() * Math.PI * 2D;
            int x = Mth.floor(Math.cos(angle) * distance);
            int z = Mth.floor(Math.sin(angle) * distance);
            BlockPos candidatePos = new BlockPos(x, 256, z);
            if (!level.getWorldBorder().isWithinBounds(candidatePos)) {
                continue;
            }
            if (search.attemptedChunks.contains(ChunkPos.asLong(x >> 4, z >> 4))) {
                continue;
            }

            EventResult eventResult = FTBEssentialsEvents.RTP_EVENT.invoker()
                    .teleport(level, player, candidatePos, search.nextAttempt);
            if (eventResult.isFalse()) {
                CTNHCore.LOGGER.debug("[RTP] {}: sample [{}, {}] vetoed by RTP_EVENT",
                        search.playerId, x, z);
                continue;
            }

            CTNHCore.LOGGER.debug("[RTP] {}: sample [{}, {}] (chunk ({}, {})) -> requesting FULL",
                    search.playerId, x, z, x >> 4, z >> 4);
            return new Candidate(candidatePos, search.nextAttempt);
        }
        return null;
    }

    /**
     * Generates a remote chunk to FULL entirely on the server thread.
     * <p>
     * A load ticket ({@link TicketType#PLUGIN}, level {@link #CHUNK_TICKET_LEVEL}) is placed
     * first so the holder stays alive while it generates; without one, the holder created by
     * the private {@code getChunkFutureMainThread} path can be unloaded again before the FULL
     * future completes (observed as 100% ChunkLoadingFailure). The future is then requested on
     * the next tick - {@link MinecraftServer#execute} drains its queue once per tick, so the
     * double submit runs one tick later, by which time the ticket has taken effect.
     * All chunk generation runs on the server thread; nothing here blocks it.
     */
    private static CompletableFuture<Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure>> requestChunk(
                                                                                                        Search search,
                                                                                                        ServerLevel level,
                                                                                                        ChunkPos chunkPos) {
        ServerChunkCache cache = (ServerChunkCache) level.getChunkSource();
        DistanceManager distanceManager = ((ServerChunkCacheAccessor) (Object) cache).getDistanceManager();
        distanceManager.addTicket(RTP_TICKET, chunkPos, CHUNK_TICKET_LEVEL, Unit.INSTANCE);
        search.tickets.add(chunkPos);

        CompletableFuture<Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure>> result = new CompletableFuture<>();
        MinecraftServer server = level.getServer();
        server.execute(() -> server.execute(() -> {
            try {
                ((ServerChunkCacheAccessor) (Object) cache).ctnhcore$getChunkFutureMainThread(
                        chunkPos.x, chunkPos.z, ChunkStatus.FULL, true)
                        .whenComplete((either, error) -> {
                            releaseChunkTicket(search, level, chunkPos);
                            if (error != null) {
                                result.completeExceptionally(error);
                            } else {
                                result.complete(either);
                            }
                        });
            } catch (Throwable t) {
                releaseChunkTicket(search, level, chunkPos);
                result.completeExceptionally(t);
            }
        }));
        return result;
    }

    private static void releaseChunkTicket(Search search, ServerLevel level, ChunkPos chunkPos) {
        search.tickets.remove(chunkPos);
        ServerChunkCache cache = (ServerChunkCache) level.getChunkSource();
        DistanceManager distanceManager = ((ServerChunkCacheAccessor) (Object) cache).getDistanceManager();
        distanceManager.removeTicket(RTP_TICKET, chunkPos, CHUNK_TICKET_LEVEL, Unit.INSTANCE);
    }

    /** Releases every still-open load ticket of a search (finish/fail/cancel path). */
    private static void releaseAllTickets(Search search) {
        ServerLevel level = search.server.getLevel(search.dimension);
        if (level != null) {
            for (ChunkPos pos : new ArrayList<>(search.tickets)) {
                releaseChunkTicket(search, level, pos);
            }
        }
        search.tickets.clear();
    }

    private static void onCandidateComplete(Search search, Candidate candidate,
                                            Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure> result,
                                            Throwable error) {
        if (SEARCHES.get(search.playerId) != search) {
            return; // this search was finished/cancelled while the chunk was generating
        }
        ServerPlayer player = currentPlayer(search);
        ServerLevel level = currentLevel(search, player);
        if (level == null) {
            cancel(search);
            return;
        }
        search.inFlight--;

        if (error != null || result == null || result.left().isEmpty()) {
            CTNHCore.LOGGER.debug("[RTP] {}: candidate [{}, {}] rejected: chunk not delivered (error={}, empty={})",
                    search.playerId, candidate.pos.getX(), candidate.pos.getZ(), error != null,
                    result == null || result.left().isEmpty());
            fillWindow(search);
            return;
        }

        BlockPos candidatePos = candidate.pos;
        if (level.getBiome(candidatePos).is(TeleportCommands.IGNORE_RTP_BIOMES)) {
            CTNHCore.LOGGER.debug("[RTP] {}: candidate [{}, {}] rejected: biome {} in ignore tag",
                    search.playerId, candidatePos.getX(), candidatePos.getZ(), level.getBiome(candidatePos));
            fillWindow(search);
            return;
        }
        // The MOTION_BLOCKING_NO_LEAVES heightmap is not reliable on this pack's worlds
        // (observed: reported ground y=63 while the block there was air, so ocean positions
        // sailed through). Scan the actual column instead: first block from the top that is
        // solid, dry (non-water), not leaves, with 3 water-free blocks above for the player.
        BlockPos groundPos = findLandingSpot(level, candidatePos.getX(), candidatePos.getZ());
        if (groundPos == null) {
            if (search.fallbackActive) {
                CTNHCore.LOGGER.debug("[RTP] {}: candidate [{}, {}] rejected: no dry landing spot in column, fallback busy",
                        search.playerId, candidatePos.getX(), candidatePos.getZ());
                fillWindow(search);
            } else {
                CTNHCore.LOGGER.debug("[RTP] {}: candidate [{}, {}] rejected: no dry landing spot, starting fallback",
                        search.playerId, candidatePos.getX(), candidatePos.getZ());
                // Another in-window candidate may own the fallback scan already; keep the
                // neighbour-chunk load bounded by not starting a second one.
                requestFallbackArea(search, candidate);
            }
        } else {
            CTNHCore.LOGGER.debug("[RTP] {}: candidate [{}, {}] accepted, ground y={}, block {}, above=[{}, {}, {}]",
                    search.playerId, candidatePos.getX(), candidatePos.getZ(), groundPos.getY(),
                    level.getBlockState(groundPos),
                    level.getBlockState(groundPos.above()),
                    level.getBlockState(groundPos.above(2)),
                    level.getBlockState(groundPos.above(3)));
            finish(search, player, level, groundPos, candidate.attempt);
        }
    }

    /**
     * Scans one column top-down for a breathing landing spot: a solid, non-leaf block with
     * 3 collision-free blocks above it (player hitbox + head). Collision-free means the
     * blocks must not block motion; water blocks motion, so this also rejects water without
     * relying on fluid tags or the (unreliable, on this pack) world heightmap.
     * <p>
     * The scan is confined to the surface band around sea level (seaLevel-2 .. seaLevel+120):
     * without this, the top-down scan keeps drilling through terrain and can end up inside an
     * underground cave (observed: a deepslate cave wall at y=-10 accepted as "land").
     * Returns the ground block position, or null if the column has no such spot.
     */
    private static BlockPos findLandingSpot(ServerLevel level, int x, int z) {
        int minY = level.getSeaLevel() - 2;
        int maxY = Math.min(level.getMaxBuildHeight() - 1, level.getSeaLevel() + 120);
        for (int y = maxY; y >= minY; y--) {
            BlockState state = level.getBlockState(new BlockPos(x, y, z));
            if (state.blocksMotion() && !state.is(BlockTags.LEAVES)
                    && !state.is(TeleportCommands.IGNORE_RTP_BLOCKS)
                    && hasClearSpaceAbove(level, x, y, z)) {
                return new BlockPos(x, y, z);
            }
        }
        return null;
    }

    private static boolean hasClearSpaceAbove(ServerLevel level, int x, int y, int z) {
        for (int i = 1; i <= 3; i++) {
            BlockState above = level.getBlockState(new BlockPos(x, y + i, z));
            if (above.blocksMotion() || above.getFluidState().is(FluidTags.WATER)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Progressive fallback scan. The candidate chunk itself is already FULL, so it is
     * scanned immediately; the 8 surrounding chunks are then generated in parallel and
     * each one is scanned as soon as it arrives (no allOf barrier waiting for the
     * slowest chunk). Every failure path re-enters the window via {@link #fillWindow}.
     */
    private static void requestFallbackArea(Search search, Candidate candidate) {
        ServerPlayer player = currentPlayer(search);
        ServerLevel level = currentLevel(search, player);
        if (level == null) {
            cancel(search);
            return;
        }

        search.fallbackActive = true;
        int chunkX = candidate.pos.getX() >> 4;
        int chunkZ = candidate.pos.getZ() >> 4;

        if (scanChunk(search, level, candidate, chunkX, chunkZ)) {
            return;
        }

        List<ChunkPos> neighbors = new ArrayList<>(8);
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                if (dx == 0 && dz == 0) {
                    continue;
                }
                neighbors.add(new ChunkPos(chunkX + dx, chunkZ + dz));
            }
        }
        int[] next = { 0 };    // next neighbour index to dispatch
        int[] pending = { 8 }; // neighbours not yet scanned (or failed/chunk-empty)
        int[] running = { 0 }; // neighbours currently loading
        dispatchFallbackNeighbor(search, level, candidate, neighbors, next, pending, running);
    }

    /**
     * Dispatches fallback neighbours with at most {@link #FALLBACK_CONCURRENCY} FULL
     * generations in flight, so the fallback scan cannot stall the server thread.
     */
    private static void dispatchFallbackNeighbor(Search search, ServerLevel level, Candidate candidate,
                                                 List<ChunkPos> neighbors, int[] next, int[] pending, int[] running) {
        while (running[0] < FALLBACK_CONCURRENCY && next[0] < neighbors.size()) {
            ChunkPos neighborPos = neighbors.get(next[0]++);
            running[0]++;
            requestChunk(search, level, neighborPos).whenComplete((result, error) -> search.server.execute(() -> {
                if (SEARCHES.get(search.playerId) != search) {
                    return; // finish/cancel happened while this neighbor was generating
                }
                running[0]--;
                pending[0]--;
                boolean ok = error == null && result != null && result.left().isPresent() &&
                        scanChunk(search, level, candidate, neighborPos.x, neighborPos.z);
                if (ok) {
                    return; // scanChunk finished the search
                }
                if (pending[0] == 0) {
                    search.fallbackActive = false;
                    if (SEARCHES.get(search.playerId) == search) {
                        fillWindow(search);
                    }
                } else {
                    dispatchFallbackNeighbor(search, level, candidate, neighbors, next, pending, running);
                }
            }));
        }
    }

    /** Scans one whole chunk column-plane at sea level for a spot with 3 free blocks above. */
    private static boolean scanChunk(Search search, ServerLevel level, Candidate candidate, int chunkX, int chunkZ) {
        ServerPlayer player = currentPlayer(search);
        if (player == null) {
            cancel(search);
            return true; // nobody left to scan for; treat as handled
        }

        int baseX = chunkX << 4;
        int baseZ = chunkZ << 4;
        int seaLevel = level.getSeaLevel();
        for (int dx = 0; dx < 16; dx++) {
            for (int dz = 0; dz < 16; dz++) {
                BlockPos pos = new BlockPos(baseX + dx, seaLevel, baseZ + dz);
                BlockState state = level.getBlockState(pos);
                if (state.blocksMotion() && state.getFluidState().isEmpty() &&
                        !state.is(TeleportCommands.IGNORE_RTP_BLOCKS) &&
                        level.isEmptyBlock(pos.above()) && level.isEmptyBlock(pos.above(2)) &&
                        level.isEmptyBlock(pos.above(3))) {
                    CTNHCore.LOGGER.debug("[RTP] {}: fallback found spot [{}, {}, {}] in chunk ({}, {})",
                            search.playerId, pos.getX(), pos.getY(), pos.getZ(), chunkX, chunkZ);
                    finish(search, player, level, pos.immutable(), candidate.attempt);
                    return true;
                }
            }
        }
        CTNHCore.LOGGER.debug("[RTP] {}: fallback chunk ({}, {}) scanned, no spot",
                search.playerId, chunkX, chunkZ);
        return false;
    }

    /** Wakes searches that were parked because the global in-flight cap was reached. */
    private static void releaseGlobalSlots() {
        if (WAITING.isEmpty()) {
            return;
        }
        List<Search> waiting = new ArrayList<>(WAITING);
        WAITING.clear();
        for (Search search : waiting) {
            if (SEARCHES.get(search.playerId) == search) {
                fillWindow(search);
            }
        }
    }

    private static void finish(Search search, ServerPlayer player, ServerLevel level, BlockPos groundPos, int attempt) {
        if (!SEARCHES.remove(search.playerId, search)) {
            return;
        }
        WAITING.remove(search);
        releaseAllTickets(search);
        long tookSec = Math.round((System.currentTimeMillis() - search.startMillis) / 1000.0D);
        CTNHCore.LOGGER.debug("[RTP] {}: FINISHED at [{}, {}, {}] after {} attempts (took {}s), teleporting",
                search.playerId, groundPos.getX(), groundPos.getY(), groundPos.getZ(), attempt + 1, tookSec);

        player.sendSystemMessage(Component.literal(String.format(
                "Found good location after %d %s (took %ds) @ [x %d, z %d]",
                attempt + 1,
                attempt == 0 ? "attempt" : "attempts",
                tookSec,
                groundPos.getX(),
                groundPos.getZ())));
        TeleportPos foundPos = new TeleportPos(level.dimension(), groundPos.above());
        search.playerData.rtpTeleporter.teleport(player, ignored -> foundPos).runCommand(player);
    }

    private static void fail(Search search) {
        if (!SEARCHES.remove(search.playerId, search)) {
            return;
        }
        WAITING.remove(search);
        releaseAllTickets(search);
        long tookSec = Math.round((System.currentTimeMillis() - search.startMillis) / 1000.0D);
        CTNHCore.LOGGER.debug("[RTP] {}: search failed after {} attempts ({} chunks attempted, took {}s)",
                search.playerId, search.nextAttempt, search.attemptedChunks.size(), tookSec);
        ServerPlayer player = search.server.getPlayerList().getPlayer(search.playerId);
        if (player != null) {
            player.sendSystemMessage(Component.literal("Could not find a valid location to teleport to!")
                    .withStyle(ChatFormatting.RED));
        }
    }

    private static ServerPlayer currentPlayer(Search search) {
        if (SEARCHES.get(search.playerId) != search) {
            return null;
        }
        return search.server.getPlayerList().getPlayer(search.playerId);
    }

    private static ServerLevel currentLevel(Search search, ServerPlayer player) {
        if (player == null || !player.level().dimension().equals(search.dimension)) {
            return null;
        }
        return search.server.getLevel(search.dimension);
    }

    private static void cancel(Search search) {
        if (SEARCHES.remove(search.playerId, search)) {
            releaseAllTickets(search);
        }
        WAITING.remove(search);
    }

    private static void cancel(UUID playerId) {
        Search removed = SEARCHES.remove(playerId);
        if (removed != null) {
            releaseAllTickets(removed);
        }
        WAITING.removeIf(search -> search.playerId.equals(playerId));
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        cancel(event.getEntity().getUUID());
    }

    @SubscribeEvent
    public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        cancel(event.getEntity().getUUID());
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            cancel(player.getUUID());
        }
    }

    @SubscribeEvent
    public static void onServerStopped(ServerStoppedEvent event) {
        SEARCHES.clear();
        WAITING.clear();
        globalInFlight = 0;
    }

    private static final class Search {

        private final UUID playerId;
        private final MinecraftServer server;
        private final ResourceKey<Level> dimension;
        private final FTBEPlayerData playerData;
        private final int maxTries;

        /** Parallel candidates in flight for this search; never exceeds maxTries. */
        private final int windowSize;

        /** Chunks this search already requested (implicitly: already failed ones), keyed by ChunkPos.asLong(). */
        private final Set<Long> attemptedChunks = new HashSet<>();

        /** Still-open load tickets ({@link #CHUNK_TICKET_LEVEL}); released on completion or cancel. */
        private final List<ChunkPos> tickets = new ArrayList<>();

        private int nextAttempt;
        private int inFlight;
        private boolean fallbackActive;

        /** Tick when this search was started, used to report time-to-teleport. */
        private final long startMillis;

        private Search(UUID playerId, MinecraftServer server, ResourceKey<Level> dimension,
                       FTBEPlayerData playerData, int maxTries) {
            this.playerId = playerId;
            this.server = server;
            this.dimension = dimension;
            this.playerData = playerData;
            this.maxTries = maxTries;
            this.windowSize = Math.min(WINDOW_SIZE, maxTries);
            this.startMillis = System.currentTimeMillis();
        }
    }

    private record Candidate(BlockPos pos, int attempt) {}
}
