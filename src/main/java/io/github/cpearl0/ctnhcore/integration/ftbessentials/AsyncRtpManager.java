package io.github.cpearl0.ctnhcore.integration.ftbessentials;

import io.github.cpearl0.ctnhcore.CTNHCore;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.levelgen.Heightmap;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Mod.EventBusSubscriber(modid = CTNHCore.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class AsyncRtpManager {

    /**
     * How many candidate chunks a single search keeps generating at the same time.
     * More candidates in flight = shorter wall-clock time per search, at the cost of
     * more concurrent FULL chunk generations. Bounded by the global cap below.
     */
    private static final int WINDOW_SIZE = 3;

    /**
     * Global cap on concurrent FULL chunk requests across all searches. This keeps the
     * server-side load bounded even when many players use /rtp at once: instead of the
     * old "every player generates 1 chunk serially", we generate up to this many in
     * parallel and queue the rest.
     */
    private static final int MAX_GLOBAL_IN_FLIGHT = 4;

    /** Resample guard so the sampler can never spin forever on rejected points. */
    private static final int MAX_RESAMPLE = 32;

    private static final Map<UUID, Search> SEARCHES = new HashMap<>();

    /** Searches currently blocked by {@link #MAX_GLOBAL_IN_FLIGHT}; woken by releaseGlobalSlots(). */
    private static final Set<Search> WAITING = new LinkedHashSet<>();

    /** Mutated/read only on the server thread (all chunk callbacks re-enter via server.execute). */
    private static int globalInFlight;

    /**
     * Worker threads that block inside {@link ServerChunkCache#getChunk} until a remote chunk
     * is FULL. The private {@code getChunkFutureMainThread} path (with create=true) creates a
     * holder without any ticket, so the freshly generated chunk can be unloaded again before the
     * future completes - observed in practice as 100% ChunkLoadingFailure. The public
     * {@code getChunk} call uses the vanilla ticket/loading path and reliably returns a FULL chunk.
     */
    private static final ExecutorService CHUNK_LOADERS = Executors.newFixedThreadPool(4, runnable -> {
        Thread thread = new Thread(runnable, "CTNH-RTP-ChunkLoader");
        thread.setDaemon(true);
        return thread;
    });

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
            requestChunk(level, chunkPos).whenComplete((result, error) -> search.server.execute(() -> {
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
                CTNHCore.LOGGER.info("[RTP] {}: sample [{}, {}] vetoed by RTP_EVENT",
                        search.playerId, x, z);
                continue;
            }

            CTNHCore.LOGGER.info("[RTP] {}: sample [{}, {}] (chunk ({}, {})) -> requesting FULL",
                    search.playerId, x, z, x >> 4, z >> 4);
            return new Candidate(candidatePos, search.nextAttempt);
        }
        return null;
    }

    /**
     * Loads/generates the chunk on a worker thread via {@link ServerChunkCache#getChunk}, which
     * blocks the calling thread until the chunk reaches FULL (vanilla's standard syntax, used by
     * e.g. player teleports). Work happens off the server thread, so the main thread never stalls;
     * the caller re-enters the server thread via {@code server.execute} once the future completes.
     */
    private static CompletableFuture<Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure>> requestChunk(ServerLevel level,
                                                                                                        ChunkPos chunkPos) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                ChunkAccess chunk = ((ServerChunkCache) level.getChunkSource())
                        .getChunk(chunkPos.x, chunkPos.z, ChunkStatus.FULL, true);
                return Either.<ChunkAccess, ChunkHolder.ChunkLoadingFailure>left(chunk);
            } catch (Throwable t) {
                CTNHCore.LOGGER.error("[RTP] chunk ({}, {}) load failed", chunkPos.x, chunkPos.z, t);
                return Either.right(new ChunkHolder.ChunkLoadingFailure() {
                    @Override
                    public String toString() {
                        return "getChunk threw " + t;
                    }
                });
            }
        }, CHUNK_LOADERS);
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
            CTNHCore.LOGGER.info("[RTP] {}: candidate [{}, {}] rejected: chunk not delivered (error={}, empty={})",
                    search.playerId, candidate.pos.getX(), candidate.pos.getZ(), error != null, result == null || result.left().isEmpty());
            fillWindow(search);
            return;
        }

        BlockPos candidatePos = candidate.pos;
        if (level.getBiome(candidatePos).is(TeleportCommands.IGNORE_RTP_BIOMES)) {
            CTNHCore.LOGGER.info("[RTP] {}: candidate [{}, {}] rejected: biome {} in ignore tag",
                    search.playerId, candidatePos.getX(), candidatePos.getZ(), level.getBiome(candidatePos));
            fillWindow(search);
            return;
        }

        BlockPos heightmapPos = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, candidatePos);
        if (heightmapPos.getY() <= 0) {
            CTNHCore.LOGGER.info("[RTP] {}: candidate [{}, {}] rejected: heightmap {} <= 0",
                    search.playerId, candidatePos.getX(), candidatePos.getZ(), heightmapPos.getY());
            fillWindow(search);
        } else if (heightmapPos.getY() < level.getMaxBuildHeight()) {
            CTNHCore.LOGGER.info("[RTP] {}: candidate [{}, {}] accepted, ground y={}",
                    search.playerId, candidatePos.getX(), candidatePos.getZ(), heightmapPos.getY());
            finish(search, player, level, heightmapPos, candidate.attempt);
        } else if (search.fallbackActive) {
            CTNHCore.LOGGER.info("[RTP] {}: candidate [{}, {}] heightmap {} >= max {}, fallback busy, skipping",
                    search.playerId, candidatePos.getX(), candidatePos.getZ(), heightmapPos.getY(), level.getMaxBuildHeight());
            // Another in-window candidate already owns the fallback scan; keep the
            // neighbour-chunk load bounded by not starting a second one.
            fillWindow(search);
        } else {
            CTNHCore.LOGGER.info("[RTP] {}: candidate [{}, {}] heightmap {} >= max {}, starting fallback scan",
                    search.playerId, candidatePos.getX(), candidatePos.getZ(), heightmapPos.getY(), level.getMaxBuildHeight());
            requestFallbackArea(search, candidate);
        }
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

        int[] pending = {8};
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                if (dx == 0 && dz == 0) {
                    continue;
                }
                int[] neighbor = {chunkX + dx, chunkZ + dz};
                ChunkPos neighborPos = new ChunkPos(neighbor[0], neighbor[1]);
                requestChunk(level, neighborPos).whenComplete((result, error) -> search.server.execute(() -> {
                    if (SEARCHES.get(search.playerId) != search) {
                        return; // finish/cancel happened while this neighbor was generating
                    }
                    pending[0]--;
                    boolean ok = error == null && result != null && result.left().isPresent() &&
                            scanChunk(search, level, candidate, neighbor[0], neighbor[1]);
                    if (ok) {
                        return; // scanChunk finished the search
                    }
                    if (pending[0] == 0) {
                        search.fallbackActive = false;
                        if (SEARCHES.get(search.playerId) == search) {
                            fillWindow(search);
                        }
                    }
                }));
            }
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
                if (state.blocksMotion() && !state.is(TeleportCommands.IGNORE_RTP_BLOCKS) &&
                        level.isEmptyBlock(pos.above()) && level.isEmptyBlock(pos.above(2)) &&
                        level.isEmptyBlock(pos.above(3))) {
                    CTNHCore.LOGGER.info("[RTP] {}: fallback found spot [{}, {}, {}] in chunk ({}, {})",
                            search.playerId, pos.getX(), pos.getY(), pos.getZ(), chunkX, chunkZ);
                    finish(search, player, level, pos.immutable(), candidate.attempt);
                    return true;
                }
            }
        }
        CTNHCore.LOGGER.info("[RTP] {}: fallback chunk ({}, {}) scanned, no spot",
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
        CTNHCore.LOGGER.info("[RTP] {}: FINISHED at [{}, {}, {}] after {} attempts, teleporting",
                search.playerId, groundPos.getX(), groundPos.getY(), groundPos.getZ(), attempt + 1);

        player.sendSystemMessage(Component.literal(String.format(
                "Found good location after %d %s @ [x %d, z %d]",
                attempt + 1,
                attempt == 0 ? "attempt" : "attempts",
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
        CTNHCore.LOGGER.info("[RTP] {}: search failed after {} attempts ({} chunks attempted)",
                search.playerId, search.nextAttempt, search.attemptedChunks.size());
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
        SEARCHES.remove(search.playerId, search);
        WAITING.remove(search);
    }

    private static void cancel(UUID playerId) {
        SEARCHES.remove(playerId);
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
        CHUNK_LOADERS.shutdownNow();
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

        private int nextAttempt;
        private int inFlight;
        private boolean fallbackActive;

        private Search(UUID playerId, MinecraftServer server, ResourceKey<Level> dimension,
                       FTBEPlayerData playerData, int maxTries) {
            this.playerId = playerId;
            this.server = server;
            this.dimension = dimension;
            this.playerData = playerData;
            this.maxTries = maxTries;
            this.windowSize = Math.min(WINDOW_SIZE, maxTries);
        }
    }

    private record Candidate(BlockPos pos, int attempt) {}
}