package io.github.cpearl0.ctnhcore.integration.ftbessentials;

import io.github.cpearl0.ctnhcore.CTNHConfig;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.mixin.mc.ServerChunkCacheAccessor;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.ChunkLevel;
import net.minecraft.server.level.DistanceManager;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
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

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import com.mojang.datafixers.util.Either;
import dev.architectury.event.EventResult;
import dev.ftb.mods.ftbessentials.FTBEssentialsEvents;
import dev.ftb.mods.ftbessentials.command.TeleportCommands;
import dev.ftb.mods.ftbessentials.config.FTBEConfig;
import dev.ftb.mods.ftbessentials.util.DimensionFilter;
import dev.ftb.mods.ftbessentials.util.FTBEPlayerData;
import dev.ftb.mods.ftbessentials.util.TeleportPos;
import it.unimi.dsi.fastutil.longs.Long2IntMap;
import it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Mod.EventBusSubscriber(modid = CTNHCore.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class AsyncRtpManager {

    @Key("ctnhcore.rtp.looking")
    @EN("Looking for random location...")
    @CN("正在寻找随机传送点...")
    public static Lang rtpLooking;

    @Key("ctnhcore.rtp.found")
    @EN("Found good location after %d attempts (took %d s) @ [x %d, z %d]")
    @CN("已找到合适的传送点，尝试 %d 次，耗时 %d 秒 @ [x %d, z %d]")
    public static Lang rtpFound;

    @Key("ctnhcore.rtp.dimension_not_allowed")
    @EN("You may not use /rtp in this dimension!")
    @CN("你不能在当前维度使用 /rtp！")
    public static Lang rtpDimensionNotAllowed;

    @Key("ctnhcore.rtp.already_looking")
    @EN("Already looking for a random location!")
    @CN("正在寻找随机传送点中，请稍候！")
    public static Lang rtpAlreadyLooking;

    @Key("ctnhcore.rtp.failed")
    @EN("Could not find a valid location to teleport to!")
    @CN("未能找到可传送的有效位置！")
    public static Lang rtpFailed;

    /**
     * Chunk status a candidate is generated to before it is scanned.
     * <p>
     * FEATURES is the last status that changes actual blocks: terrain, carvers, structures and
     * decoration are all done there, so anything we can inspect about the surface is final. The
     * statuses above it ({@code LIGHT} light propagation, {@code SPAWN} original mob spawning) do
     * not change blocks and are the expensive tail of a full generation, so rejected candidates -
     * which are the vast majority - never pay for them. An accepted candidate is promoted to FULL
     * by the player's own chunk loading when the teleport happens.
     */
    private static final ChunkStatus SCAN_STATUS = ChunkStatus.FEATURES;

    // The three concurrency knobs are owned by the config only (rtp.rtpWindowSize,
    // rtp.rtpMaxGlobalInFlight, rtp.rtpFallbackConcurrency) and read on every dispatch through
    // windowSize() / maxGlobalInFlight() / fallbackConcurrency() below; no duplicated constants.

    /**
     * Resample guard so the sampler can never spin forever on rejected points
     * (already-tried chunks, world-border rejects, ignored biomes, RTP_EVENT vetoes).
     */
    private static final int MAX_RESAMPLE = 128;

    /**
     * Upper bound on one chunk request. A future that never completes would otherwise hold a
     * global in-flight slot forever and stall /rtp for every player.
     */
    private static final long CHUNK_TIMEOUT_SECONDS = 30L;

    /** Vertical window a landing spot is searched in, relative to sea level. */
    private static final int SCAN_BELOW_SEA = 2;

    private static final int SCAN_ABOVE_SEA = 120;

    /**
     * How much of a chunk has to be water before the 3x3 fallback scan is skipped. Over open
     * ocean every neighbour is water too, so generating 8 more chunks only to find that out is
     * pure waste; resampling a fresh position is far cheaper.
     */
    private static final int WATER_CHUNK_THRESHOLD = 161;

    private static final Map<UUID, Search> SEARCHES = new HashMap<>();

    /** Searches currently blocked by {@link #maxGlobalInFlight()}; woken by releaseGlobalSlots(). */
    private static final Set<Search> WAITING = new LinkedHashSet<>();

    /** Mutated/read only on the server thread (all chunk callbacks re-enter via server.execute). */
    private static int globalInFlight;

    /**
     * Ticket level used to keep a candidate chunk alive (generated to {@link #SCAN_STATUS}, but
     * not lit or ticked) while the search inspects it. Without a ticket, a holder created for a
     * remote chunk can be unloaded again before its future completes (observed as 100%
     * ChunkLoadingFailure via the private getChunkFutureMainThread path).
     * <p>
     * {@code ChunkLevel.byStatus(FEATURES)} is 34, which is the level that lets the chunk reach
     * FEATURES while stopping short of LIGHT/SPAWN; a level of 33 (FULL) would silently promote
     * every rejection back into a full generation, undo the saving and waste light propagation.
     */
    private static final int CHUNK_TICKET_LEVEL = ChunkLevel.byStatus(SCAN_STATUS);

    /**
     * Custom ticket type so the search does not interfere with vanilla/forge chunk tickets.
     * Keyed by player UUID: two searches holding the same chunk must not collapse into one
     * ticket entry, because DistanceManager dedupes tickets by (type, level, key).
     */
    private static final TicketType<UUID> RTP_TICKET = TicketType.create("ctnhcore:rtp", UUID::compareTo);

    /**
     * Self-expiring ticket (10 s) put on the landing chunk right before teleporting. The chunk is
     * only at {@link #SCAN_STATUS} when the search finishes; without this the teleport's own
     * synchronous load could find it already unloaded and regenerate it from scratch.
     */
    private static final TicketType<Unit> RTP_KEEP_TICKET = TicketType.create("ctnhcore:rtp_keep", ($1, $2) -> 0, 200);

    private AsyncRtpManager() {}

    /**
     * Per-search window, read from the config on every use so a reload takes effect immediately.
     * Config values are clamped to the documented ranges by the config library, but a search
     * always needs at least one slot, hence the floor. The config field's own initializer is the
     * single source of the default, so the two can never drift apart.
     */
    private static int windowSize() {
        CTNHConfig config = CTNHConfig.INSTANCE;
        return config == null ? 2 : Math.max(1, config.rtp.rtpWindowSize);
    }

    /** Global concurrency cap; fallback neighbour requests count against it too. */
    private static int maxGlobalInFlight() {
        CTNHConfig config = CTNHConfig.INSTANCE;
        return config == null ? 4 : Math.max(1, config.rtp.rtpMaxGlobalInFlight);
    }

    /** Fallback neighbour concurrency. */
    private static int fallbackConcurrency() {
        CTNHConfig config = CTNHConfig.INSTANCE;
        return config == null ? 2 : Math.max(1, config.rtp.rtpFallbackConcurrency);
    }

    public static int start(ServerPlayer player) {
        if (!player.hasPermissions(2)) {
            TeleportPos.TeleportResult blacklistResult = new TeleportPos(player).checkDimensionBlacklist(player);
            if (!blacklistResult.isSuccess()) {
                return blacklistResult.runCommand(player);
            }
            if (!DimensionFilter.isRtpDimensionOK(player.level().dimension())) {
                player.sendSystemMessage(rtpDimensionNotAllowed.translate()
                        .withStyle(ChatFormatting.RED));
                return 0;
            }
        }

        UUID playerId = player.getUUID();
        if (SEARCHES.containsKey(playerId)) {
            player.sendSystemMessage(rtpAlreadyLooking.translate()
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
                    FTBEConfig.RTP_MAX_TRIES.get(),
                    FTBEConfig.RTP_MIN_DISTANCE.get(),
                    FTBEConfig.RTP_MAX_DISTANCE.get());
            SEARCHES.put(playerId, search);
            player.sendSystemMessage(rtpLooking.translate());
            fillWindow(search);
            return 1;
        }).orElse(0);
    }

    /**
     * Sliding-window fill: keep up to {@link Search#windowSize()} candidate chunks in
     * flight at all times (bounded globally by {@link #maxGlobalInFlight()}). When a
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

        int globalCap = maxGlobalInFlight();
        while (search.inFlight < search.windowSize() && search.nextAttempt < search.maxTries) {
            if (globalInFlight >= globalCap) {
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
            ChunkPos chunkPos = new ChunkPos(candidate.pos);
            search.attemptedChunks.add(ChunkPos.asLong(chunkPos.x, chunkPos.z));
            // The global slot is taken and released inside requestChunk, so the fallback scan
            // (which also goes through it) is held to the same cap.
            requestChunk(search, level, chunkPos,
                    (chunk, delivered) -> onCandidateComplete(search, candidate, chunk, delivered));
        }

        if (search.inFlight == 0 && search.nextAttempt >= search.maxTries) {
            fail(search);
        }
    }

    /**
     * Samples a fresh candidate, skipping chunks already attempted, world-border rejects and
     * candidates in a biome the pack has excluded from /rtp.
     * <p>
     * The biome test runs here, before any chunk is requested, because it is free:
     * {@code getUncachedNoiseBiome} is a pure climate-noise lookup and never loads a chunk.
     * Rejecting ocean/river positions up front is what keeps the expensive path - a real
     * generation - for positions that can actually host a player.
     */
    private static Candidate nextCandidate(Search search, ServerLevel level, ServerPlayer player) {
        int biomeY = level.getSeaLevel() >> 2;
        for (int i = 0; i < MAX_RESAMPLE; i++) {
            double distance = search.minDistance +
                    level.random.nextDouble() * (search.maxDistance - search.minDistance);
            double angle = level.random.nextDouble() * Math.PI * 2D;
            int x = Mth.floor(Math.cos(angle) * distance);
            int z = Mth.floor(Math.sin(angle) * distance);
            BlockPos candidatePos = new BlockPos(x, level.getSeaLevel(), z);
            if (!level.getWorldBorder().isWithinBounds(candidatePos)) {
                continue;
            }
            if (search.attemptedChunks.contains(ChunkPos.asLong(x >> 4, z >> 4))) {
                continue;
            }
            if (level.getUncachedNoiseBiome(x >> 2, biomeY, z >> 2).is(TeleportCommands.IGNORE_RTP_BIOMES)) {
                if (CTNHCore.LOGGER.isDebugEnabled()) {
                    CTNHCore.LOGGER.debug("[RTP] {}: sample [{}, {}] rejected: biome in ignore tag",
                            search.playerId, x, z);
                }
                continue;
            }

            EventResult eventResult = FTBEssentialsEvents.RTP_EVENT.invoker()
                    .teleport(level, player, candidatePos, search.nextAttempt);
            if (eventResult.isFalse()) {
                if (CTNHCore.LOGGER.isDebugEnabled()) {
                    CTNHCore.LOGGER.debug("[RTP] {}: sample [{}, {}] vetoed by RTP_EVENT",
                            search.playerId, x, z);
                }
                continue;
            }

            if (CTNHCore.LOGGER.isDebugEnabled()) {
                CTNHCore.LOGGER.debug("[RTP] {}: sample [{}, {}] (chunk ({}, {})) -> requesting {}",
                        search.playerId, x, z, x >> 4, z >> 4, SCAN_STATUS);
            }
            return new Candidate(candidatePos, search.nextAttempt);
        }
        return null;
    }

    /**
     * Requests one chunk at {@link #SCAN_STATUS} and hands the generated chunk back to the
     * consumer on the server thread.
     * <p>
     * A load ticket ({@link #CHUNK_TICKET_LEVEL}) is placed first so the holder stays alive while
     * it generates; without one, the holder created by the private
     * {@code getChunkFutureMainThread} path can be unloaded again before the future completes
     * (observed as 100% ChunkLoadingFailure). A ticket takes effect immediately, so the future is
     * requested in the same tick.
     * <p>
     * This is the single place that owns the {@link #globalInFlight} slot: both the window and the
     * fallback scan go through here, so the configured global cap covers fallback neighbour
     * generations too. The ticket is released <b>after</b> the consumer returns, not before it is
     * invoked: the consumer scans the chunk, and releasing first would let a distance-manager pass
     * in between drop the chunk we are about to read.
     */
    private static void requestChunk(Search search, ServerLevel level, ChunkPos chunkPos, ChunkConsumer consumer) {
        ServerChunkCache cache = (ServerChunkCache) level.getChunkSource();
        DistanceManager distanceManager = ((ServerChunkCacheAccessor) (Object) cache).getDistanceManager();
        long packed = chunkPos.toLong();
        // Reference counted: the same chunk can be requested twice by one search (a fallback
        // neighbour that is also an in-flight candidate, or two overlapping fallback areas).
        // DistanceManager dedupes tickets by (type, level, key), so dropping the ticket on the
        // first completion would strip it from the request that is still reading the chunk.
        int refs = search.tickets.get(packed);
        search.tickets.put(packed, refs + 1);
        if (refs == 0) {
            distanceManager.addTicket(RTP_TICKET, chunkPos, CHUNK_TICKET_LEVEL, search.playerId);
        }
        globalInFlight++;

        MinecraftServer server = level.getServer();
        // Deferred to the next task pass on purpose: runDistanceManagerUpdates() re-enters the
        // chunk map, and this method is called from inside window/fallback callbacks.
        server.execute(() -> {
            CompletableFuture<Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure>> holderFuture;
            try {
                holderFuture = ((ServerChunkCacheAccessor) (Object) cache)
                        .ctnhcore$getChunkFutureMainThread(chunkPos.x, chunkPos.z, SCAN_STATUS, true);
            } catch (Throwable t) {
                deliver(search, level, chunkPos, consumer, null, false);
                return;
            }

            // Never orTimeout() the holder's own future: that completes this status' future
            // exceptionally for good, and every later getOrScheduleFuture on the same chunk and
            // status rethrows the stored CompletionException out of getNow(). A private future
            // gives us the timeout without touching vanilla chunk state.
            CompletableFuture<ChunkAccess> pending = new CompletableFuture<>();
            holderFuture.whenComplete((either, error) -> {
                if (error != null) {
                    pending.completeExceptionally(error);
                } else {
                    pending.complete(either == null ? null : either.left().orElse(null));
                }
            });
            pending.orTimeout(CHUNK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                    .whenComplete((chunk, error) -> server.execute(() -> {
                        if (error != null && (error instanceof TimeoutException ||
                                error.getCause() instanceof TimeoutException)) {
                            CTNHCore.LOGGER.warn(
                                    "[RTP] Chunk ({}, {}) did not finish loading within {}s, dropping this candidate",
                                    chunkPos.x, chunkPos.z, CHUNK_TIMEOUT_SECONDS);
                        }
                        boolean delivered = error == null && chunk != null;
                        deliver(search, level, chunkPos, consumer, delivered ? chunk : null, delivered);
                    }));
        });
    }

    /** Frees the global slot, wakes parked searches, then runs the consumer and drops the ticket. */
    private static void deliver(Search search, ServerLevel level, ChunkPos chunkPos, ChunkConsumer consumer,
                                ChunkAccess chunk, boolean delivered) {
        // Guarded: after ServerStoppedEvent resets the counter, a late callback would otherwise
        // drive it negative and silently raise the effective cap for the next server run.
        globalInFlight = Math.max(0, globalInFlight - 1);
        releaseGlobalSlots();
        try {
            consumer.accept(chunk, delivered);
        } finally {
            releaseChunkTicket(search, level, chunkPos);
        }
    }

    private static void releaseChunkTicket(Search search, ServerLevel level, ChunkPos chunkPos) {
        long packed = chunkPos.toLong();
        int remaining = search.tickets.get(packed) - 1;
        if (remaining > 0) {
            search.tickets.put(packed, remaining);
            return; // another request for the same chunk is still reading it
        }
        if (!search.tickets.containsKey(packed)) {
            return; // already released: finish/fail/cancel dropped every ticket of this search
        }
        search.tickets.remove(packed);
        ServerChunkCache cache = (ServerChunkCache) level.getChunkSource();
        DistanceManager distanceManager = ((ServerChunkCacheAccessor) (Object) cache).getDistanceManager();
        distanceManager.removeTicket(RTP_TICKET, chunkPos, CHUNK_TICKET_LEVEL, search.playerId);
    }

    /** Releases every still-open load ticket of a search (finish/fail/cancel path). */
    private static void releaseAllTickets(Search search) {
        ServerLevel level = search.server.getLevel(search.dimension);
        if (level != null && !search.tickets.isEmpty()) {
            LongIterator iterator = search.tickets.keySet().iterator();
            List<Long> open = new ArrayList<>(search.tickets.size());
            while (iterator.hasNext()) {
                open.add(iterator.nextLong());
            }
            for (long packed : open) {
                ServerChunkCache cache = (ServerChunkCache) level.getChunkSource();
                DistanceManager distanceManager = ((ServerChunkCacheAccessor) (Object) cache).getDistanceManager();
                distanceManager.removeTicket(RTP_TICKET, new ChunkPos(packed), CHUNK_TICKET_LEVEL, search.playerId);
            }
        }
        search.tickets.clear();
    }

    private static void onCandidateComplete(Search search, Candidate candidate, ChunkAccess chunk, boolean delivered) {
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

        if (!delivered || chunk == null) {
            if (CTNHCore.LOGGER.isDebugEnabled()) {
                CTNHCore.LOGGER.debug("[RTP] {}: candidate [{}, {}] rejected: chunk not delivered",
                        search.playerId, candidate.pos.getX(), candidate.pos.getZ());
            }
            fillWindow(search);
            return;
        }

        BlockPos candidatePos = candidate.pos;

        // The saved MOTION_BLOCKING_NO_LEAVES heightmap is not reliable on this pack's worlds
        // (observed: reported ground y=63 while the block there was air). The heightmap of a chunk
        // that was just generated is, so it is used only as the upper bound of the scan while every
        // candidate block is verified against the real chunk data.
        ScanBand band = scanBand(level);
        BlockPos groundPos = scanColumn(chunk, candidatePos.getX(), candidatePos.getZ(), band, hasWaterTop(chunk));
        if (groundPos == null) {
            if (search.fallbackActive) {
                if (CTNHCore.LOGGER.isDebugEnabled()) {
                    CTNHCore.LOGGER.debug(
                            "[RTP] {}: candidate [{}, {}] rejected: no dry landing spot in column, fallback busy",
                            search.playerId, candidatePos.getX(), candidatePos.getZ());
                }
                fillWindow(search);
            } else if (isMostlyWater(chunk)) {
                // Over open ocean every neighbour chunk is water as well: generating the other 8
                // chunks of the fallback scan only to learn that would be 8 wasted generations.
                if (CTNHCore.LOGGER.isDebugEnabled()) {
                    CTNHCore.LOGGER.debug(
                            "[RTP] {}: candidate [{}, {}] rejected: water chunk, resampling instead of fallback",
                            search.playerId, candidatePos.getX(), candidatePos.getZ());
                }
                fillWindow(search);
            } else {
                if (CTNHCore.LOGGER.isDebugEnabled()) {
                    CTNHCore.LOGGER.debug(
                            "[RTP] {}: candidate [{}, {}] rejected: no dry landing spot, starting fallback",
                            search.playerId, candidatePos.getX(), candidatePos.getZ());
                }
                // Another in-window candidate may own the fallback scan already; keep the
                // neighbour-chunk load bounded by not starting a second one.
                requestFallbackArea(search, candidate, chunk);
            }
        } else if (isIgnoredBiome(chunk, groundPos)) {
            // The sampler probe runs on raw climate noise at sea level, which can disagree with the
            // biome that actually reaches the surface here (a beach edge, a ravine exposing a cave
            // biome). Re-testing the real landing biome is exact and, read from the chunk we already
            // hold, costs one section lookup - no noise sampling, no second chunk load.
            if (CTNHCore.LOGGER.isDebugEnabled()) {
                CTNHCore.LOGGER.debug("[RTP] {}: candidate [{}, {}] rejected: landing biome in ignore tag",
                        search.playerId, candidatePos.getX(), candidatePos.getZ());
            }
            fillWindow(search);
        } else {
            if (CTNHCore.LOGGER.isDebugEnabled()) {
                CTNHCore.LOGGER.debug(
                        "[RTP] {}: candidate [{}, {}] accepted, ground y={}, block {}, above=[{}, {}, {}]",
                        search.playerId, candidatePos.getX(), candidatePos.getZ(), groundPos.getY(),
                        chunk.getBlockState(groundPos),
                        chunk.getBlockState(groundPos.above()),
                        chunk.getBlockState(groundPos.above(2)),
                        chunk.getBlockState(groundPos.above(3)));
            }
            finish(search, player, level, groundPos, candidate.attempt);
        }
    }

    /**
     * Exact biome test for a landing spot against the pack's /rtp blacklist.
     * <p>
     * {@code ChunkAccess#getNoiseBiome} reads the already-generated biome section, so this is a plain
     * array/palette lookup. The equivalent {@code Level#getBiome} would instead interpolate eight
     * corners through the climate sampler and fall back to the noise source - roughly an order of
     * magnitude more work for the same answer once the chunk is in hand.
     */
    private static boolean isIgnoredBiome(ChunkAccess chunk, BlockPos pos) {
        return chunk.getNoiseBiome(pos.getX() >> 2, pos.getY() >> 2, pos.getZ() >> 2)
                .is(TeleportCommands.IGNORE_RTP_BIOMES);
    }

    /** Vertical band a landing spot is searched in: just below sea level up to the terrain ceiling. */
    private static ScanBand scanBand(ServerLevel level) {
        int minY = level.getSeaLevel() - SCAN_BELOW_SEA;
        int maxY = Math.min(level.getMaxBuildHeight() - 4, level.getSeaLevel() + SCAN_ABOVE_SEA);
        return new ScanBand(minY, maxY);
    }

    /**
     * Scans one column of a chunk top-down for a breathing landing spot: a solid, non-leaf block
     * with 3 collision-free, fluid-free blocks above it (player hitbox + head).
     * <p>
     * Both heightmaps of the chunk bound the scan for free: {@code MOTION_BLOCKING} counts water
     * while {@code OCEAN_FLOOR} does not, so {@code MOTION_BLOCKING > OCEAN_FLOOR} means standing
     * water above the terrain - an ocean, river or lake column that can be rejected without
     * touching a single block. That is the difference between ~123 block reads per candidate and
     * two heightmap lookups for the majority of rejected positions.
     * <p>
     * The scan is confined to the surface band around sea level ({@link #scanBand}): without this,
     * a top-down scan keeps drilling through terrain and can end up inside an underground cave
     * (observed: a deepslate cave wall at y=-10 accepted as "land").
     */
    private static BlockPos scanColumn(ChunkAccess chunk, int x, int z, ScanBand band, boolean useHeightmaps) {
        int top = band.maxY();
        if (useHeightmaps) {
            int waterTop = chunk.getHeight(Heightmap.Types.MOTION_BLOCKING, x, z);
            int floorTop = chunk.getHeight(Heightmap.Types.OCEAN_FLOOR, x, z);
            if (waterTop > floorTop) {
                return null; // 水面高过地面：这一列是水体
            }
            top = Math.min(top, waterTop);
        }
        if (top < band.minY()) {
            return null;
        }

        BlockPos.MutableBlockPos cursor = new BlockPos.MutableBlockPos();
        for (int y = top; y >= band.minY(); y--) {
            BlockState state = chunk.getBlockState(cursor.set(x, y, z));
            if (isLandable(state) && hasRoomAbove(chunk, x, y, z, cursor)) {
                return new BlockPos(x, y, z);
            }
        }
        return null;
    }

    /** True when a whole chunk is (almost) all open water, so a 3x3 fallback scan is pointless. */
    private static boolean isMostlyWater(ChunkAccess chunk) {
        if (!hasWaterTop(chunk)) {
            return false;
        }
        int baseX = chunk.getPos().getMinBlockX();
        int baseZ = chunk.getPos().getMinBlockZ();
        int water = 0;
        for (int dx = 0; dx < 16; dx++) {
            for (int dz = 0; dz < 16; dz++) {
                if (chunk.getHeight(Heightmap.Types.MOTION_BLOCKING, baseX + dx, baseZ + dz) >
                        chunk.getHeight(Heightmap.Types.OCEAN_FLOOR, baseX + dx, baseZ + dz)) {
                    water++;
                    if (water >= WATER_CHUNK_THRESHOLD) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Whether the chunk has the two heightmaps this scan reads.
     * <p>
     * The FEATURES generation task primes {@code MOTION_BLOCKING} and {@code OCEAN_FLOOR} for the
     * whole chunk, so the status is the reliable test. Testing {@code hasPrimedHeightmap} directly
     * would be wrong for the common case: a chunk that is already fully loaded comes back wrapped
     * in an {@code ImposterProtoChunk}, whose own heightmap map stays empty while its wrapped
     * {@code LevelChunk} owns the real maps - and asking for a map the status does not prime runs
     * {@code primeHeightmaps} over the entire chunk, costing more than the scan it saves.
     */
    private static boolean hasWaterTop(ChunkAccess chunk) {
        return chunk.getStatus().isOrAfter(SCAN_STATUS);
    }

    /** A block the player can stand on. Leaves are excluded: they are solid but not a intended floor. */
    private static boolean isLandable(BlockState state) {
        return state.blocksMotion() && !state.is(BlockTags.LEAVES) && !state.is(TeleportCommands.IGNORE_RTP_BLOCKS);
    }

    /** Air-like: no collision and no fluid, so a player hitbox fits without swimming or suffocating. */
    private static boolean isFree(BlockState state) {
        return !state.blocksMotion() && state.getFluidState().isEmpty();
    }

    /** Player head and body room: 3 free blocks directly above the ground block. */
    private static boolean hasRoomAbove(ChunkAccess chunk, int x, int y, int z, BlockPos.MutableBlockPos cursor) {
        if (y + 3 >= chunk.getMaxBuildHeight()) {
            return false;
        }
        for (int i = 1; i <= 3; i++) {
            if (!isFree(chunk.getBlockState(cursor.set(x, y + i, z)))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Progressive fallback scan. The candidate chunk itself is already generated, so it is
     * scanned immediately; the 8 surrounding chunks are then generated in parallel and
     * each one is scanned as soon as it arrives (no allOf barrier waiting for the
     * slowest chunk). Every failure path re-enters the window via {@link #fillWindow}.
     */
    private static void requestFallbackArea(Search search, Candidate candidate, ChunkAccess candidateChunk) {
        ServerPlayer player = currentPlayer(search);
        ServerLevel level = currentLevel(search, player);
        if (level == null) {
            cancel(search);
            return;
        }

        search.fallbackActive = true;
        search.fallbackCandidate = candidate;
        search.fallbackBand = scanBand(level);
        ScanBand band = search.fallbackBand;
        ChunkPos center = new ChunkPos(candidate.pos);
        BlockPos centerSpot = scanChunk(candidateChunk, center.x, center.z, band);
        if (centerSpot != null) {
            finish(search, player, level, centerSpot, candidate.attempt);
            return;
        }

        List<ChunkPos> neighbors = new ArrayList<>(8);
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                if (dx == 0 && dz == 0) {
                    continue;
                }
                neighbors.add(new ChunkPos(center.x + dx, center.z + dz));
            }
        }
        search.fallbackNeighbors = neighbors;
        search.fallbackNext = 0;
        search.fallbackPending = neighbors.size();
        search.fallbackRunning = 0;
        dispatchFallbackNeighbor(search, level, band);
    }

    /**
     * Dispatches fallback neighbours with at most {@link #fallbackConcurrency()} requests in
     * flight, so the fallback scan cannot stall the server thread. The global cap is honoured
     * too, because {@link #requestChunk} is the single owner of the in-flight counter: when it is
     * reached the scan parks in {@link #WAITING} and {@link #resume(Search)} continues it once a
     * slot frees up, instead of piling more generation onto an already saturated server.
     */
    private static void dispatchFallbackNeighbor(Search search, ServerLevel level, ScanBand band) {
        int concurrency = fallbackConcurrency();
        int globalCap = maxGlobalInFlight();
        while (search.fallbackRunning < concurrency && search.fallbackNext < search.fallbackNeighbors.size()) {
            if (globalInFlight >= globalCap) {
                WAITING.add(search);
                return;
            }
            ChunkPos neighborPos = search.fallbackNeighbors.get(search.fallbackNext++);
            search.fallbackRunning++;
            requestChunk(search, level, neighborPos, (chunk, delivered) -> {
                if (SEARCHES.get(search.playerId) != search) {
                    return; // finish/cancel happened while this neighbor was generating
                }
                search.fallbackRunning--;
                search.fallbackPending--;
                ServerPlayer player = currentPlayer(search);
                if (player == null) {
                    cancel(search);
                    return;
                }
                BlockPos spot = delivered && chunk != null ? scanChunk(chunk, neighborPos.x, neighborPos.z, band) :
                        null;
                if (spot != null) {
                    if (CTNHCore.LOGGER.isDebugEnabled()) {
                        CTNHCore.LOGGER.debug("[RTP] {}: fallback found spot [{}, {}, {}] in chunk ({}, {})",
                                search.playerId, spot.getX(), spot.getY(), spot.getZ(), neighborPos.x, neighborPos.z);
                    }
                    finish(search, player, level, spot, search.fallbackCandidate.attempt);
                    return;
                }
                if (CTNHCore.LOGGER.isDebugEnabled()) {
                    CTNHCore.LOGGER.debug("[RTP] {}: fallback chunk ({}, {}) scanned, no spot",
                            search.playerId, neighborPos.x, neighborPos.z);
                }
                if (search.fallbackPending == 0) {
                    search.fallbackActive = false;
                    search.fallbackNeighbors = null;
                    search.fallbackCandidate = null;
                    if (SEARCHES.get(search.playerId) == search) {
                        fillWindow(search);
                    }
                } else {
                    dispatchFallbackNeighbor(search, level, band);
                }
            });
        }
    }

    /**
     * Scans a whole chunk column by column for a landing spot. Every column is bounded by its own
     * heightmaps, so water columns and empty sky cost two lookups instead of a full vertical walk.
     */
    private static BlockPos scanChunk(ChunkAccess chunk, int chunkX, int chunkZ, ScanBand band) {
        int baseX = chunkX << 4;
        int baseZ = chunkZ << 4;
        boolean useHeightmaps = hasWaterTop(chunk);
        for (int dx = 0; dx < 16; dx++) {
            for (int dz = 0; dz < 16; dz++) {
                BlockPos spot = scanColumn(chunk, baseX + dx, baseZ + dz, band, useHeightmaps);
                if (spot != null) {
                    return spot;
                }
            }
        }
        return null;
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
                resume(search);
            }
        }
    }

    /**
     * Picks a parked search back up. A fallback scan that stopped on the global cap continues
     * where it left off; anything else re-enters the window.
     */
    private static void resume(Search search) {
        if (search.fallbackActive && search.fallbackNeighbors != null) {
            ServerLevel level = currentLevel(search, currentPlayer(search));
            if (level != null) {
                dispatchFallbackNeighbor(search, level, search.fallbackBand);
                return;
            }
        }
        fillWindow(search);
    }

    private static void finish(Search search, ServerPlayer player, ServerLevel level, BlockPos groundPos, int attempt) {
        if (!SEARCHES.remove(search.playerId, search)) {
            return;
        }
        WAITING.remove(search);
        releaseAllTickets(search);
        long tookSec = Math.round((System.currentTimeMillis() - search.startMillis) / 1000.0D);
        if (CTNHCore.LOGGER.isDebugEnabled()) {
            CTNHCore.LOGGER.debug("[RTP] {}: FINISHED at [{}, {}, {}] after {} attempts (took {}s), teleporting",
                    search.playerId, groundPos.getX(), groundPos.getY(), groundPos.getZ(), attempt + 1, tookSec);
        }

        // Keep the landing chunk resident for a few seconds: it is only generated to SCAN_STATUS,
        // and the teleport must not lose it to an unload between here and the player's arrival.
        keepChunkResident(level, new ChunkPos(groundPos));

        TeleportPos foundPos = new TeleportPos(level.dimension(), groundPos.above());
        player.sendSystemMessage(rtpFound.translate(
                attempt + 1,
                tookSec,
                foundPos.getPos().getX(),
                foundPos.getPos().getZ()));
        search.playerData.rtpTeleporter.teleport(player, ignored -> foundPos).runCommand(player);
    }

    /** Adds a short-lived FULL ticket so the teleport finds an already generated chunk. */
    private static void keepChunkResident(ServerLevel level, ChunkPos pos) {
        ServerChunkCache cache = (ServerChunkCache) level.getChunkSource();
        DistanceManager distanceManager = ((ServerChunkCacheAccessor) (Object) cache).getDistanceManager();
        distanceManager.addTicket(RTP_KEEP_TICKET, pos, ChunkLevel.byStatus(ChunkStatus.FULL), Unit.INSTANCE);
    }

    private static void fail(Search search) {
        if (!SEARCHES.remove(search.playerId, search)) {
            return;
        }
        WAITING.remove(search);
        releaseAllTickets(search);
        long tookSec = Math.round((System.currentTimeMillis() - search.startMillis) / 1000.0D);
        if (CTNHCore.LOGGER.isDebugEnabled()) {
            CTNHCore.LOGGER.debug("[RTP] {}: search failed after {} attempts ({} chunks attempted, took {}s)",
                    search.playerId, search.nextAttempt, search.attemptedChunks.size(), tookSec);
        }
        ServerPlayer player = search.server.getPlayerList().getPlayer(search.playerId);
        if (player != null) {
            player.sendSystemMessage(rtpFailed.translate()
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

    /** Vertical scan window, resolved once per search step. */
    private record ScanBand(int minY, int maxY) {}

    /** Chunk delivery callback: {@code delivered == false} means the generation never arrived. */
    @FunctionalInterface
    private interface ChunkConsumer {

        void accept(ChunkAccess chunk, boolean delivered);
    }

    private static final class Search {

        private final UUID playerId;
        private final MinecraftServer server;
        private final ResourceKey<Level> dimension;
        private final FTBEPlayerData playerData;
        private final int maxTries;

        /** RTP distance range, resolved once instead of on every resample iteration. */
        private final double minDistance;

        private final double maxDistance;

        /** Chunks this search already requested (implicitly: already failed ones), keyed by ChunkPos.asLong(). */
        private final LongSet attemptedChunks = new LongOpenHashSet();

        /**
         * Chunks with an open load ticket, mapped to the number of in-flight requests for that
         * chunk. Reference counted because one search can legitimately request the same chunk
         * twice (a fallback neighbour that is also a window candidate); DistanceManager only
         * keeps a single ticket per (type, level, key), so releasing on the first completion
         * would strip the ticket from the request that is still reading the chunk.
         */
        private final Long2IntMap tickets = new Long2IntOpenHashMap();

        private int nextAttempt;
        private int inFlight;

        /** A fallback scan owns the search until it finishes; it re-fills the window afterwards. */
        private boolean fallbackActive;
        private List<ChunkPos> fallbackNeighbors;
        private Candidate fallbackCandidate;
        private ScanBand fallbackBand;
        private int fallbackNext;
        private int fallbackPending;
        private int fallbackRunning;

        /** Tick when this search was started, used to report time-to-teleport. */
        private final long startMillis;

        private Search(UUID playerId, MinecraftServer server, ResourceKey<Level> dimension,
                       FTBEPlayerData playerData, int maxTries, double minDistance, double maxDistance) {
            this.playerId = playerId;
            this.server = server;
            this.dimension = dimension;
            this.playerData = playerData;
            this.maxTries = maxTries;
            this.minDistance = minDistance;
            this.maxDistance = Math.max(minDistance, maxDistance);
            this.startMillis = System.currentTimeMillis();
        }

        /**
         * Parallel candidates in flight for this search; never exceeds maxTries.
         * <p>
         * Resolved from the config on demand rather than captured at construction, so lowering
         * the limit mid-search stops new dispatches instead of only affecting the next search.
         */
        private int windowSize() {
            return Math.min(AsyncRtpManager.windowSize(), maxTries);
        }
    }

    private record Candidate(BlockPos pos, int attempt) {}
}
