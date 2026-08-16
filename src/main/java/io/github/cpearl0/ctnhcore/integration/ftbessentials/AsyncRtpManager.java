package io.github.cpearl0.ctnhcore.integration.ftbessentials;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.mixin.mc.ServerChunkCacheAccessor;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ChunkHolder;
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
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = CTNHCore.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class AsyncRtpManager {

    private static final Map<UUID, Search> SEARCHES = new HashMap<>();

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
            requestNext(search);
            return 1;
        }).orElse(0);
    }

    private static void requestNext(Search search) {
        ServerPlayer player = currentPlayer(search);
        ServerLevel level = currentLevel(search, player);
        if (level == null) {
            cancel(search);
            return;
        }

        while (search.nextAttempt < search.maxTries) {
            int attempt = search.nextAttempt++;
            double distance = FTBEConfig.RTP_MIN_DISTANCE.get() +
                    level.random.nextDouble() * (FTBEConfig.RTP_MAX_DISTANCE.get() - FTBEConfig.RTP_MIN_DISTANCE.get());
            double angle = level.random.nextDouble() * Math.PI * 2D;
            int x = Mth.floor(Math.cos(angle) * distance);
            int z = Mth.floor(Math.sin(angle) * distance);
            BlockPos candidatePos = new BlockPos(x, 256, z);

            if (!level.getWorldBorder().isWithinBounds(candidatePos)) {
                continue;
            }

            EventResult eventResult = FTBEssentialsEvents.RTP_EVENT.invoker()
                    .teleport(level, player, candidatePos, attempt);
            if (eventResult.isFalse()) {
                continue;
            }

            Candidate candidate = new Candidate(candidatePos, attempt);
            CompletableFuture<Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure>> future = requestChunk(
                    level,
                    new ChunkPos(candidatePos));
            future.whenComplete((result, error) -> search.server.execute(
                    () -> inspectCandidate(search, candidate, result, error)));
            return;
        }

        fail(search);
    }

    private static CompletableFuture<Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure>> requestChunk(ServerLevel level,
                                                                                                        ChunkPos chunkPos) {
        // ServerChunkCache#getChunkFuture waits when called on the server thread. Its private
        // main-thread entry point only schedules the work and lets this search resume from the Future.
        return ((ServerChunkCacheAccessor) (Object) level.getChunkSource()).ctnhcore$getChunkFutureMainThread(
                chunkPos.x,
                chunkPos.z,
                ChunkStatus.FULL,
                true);
    }

    private static void inspectCandidate(Search search, Candidate candidate,
                                         Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure> result,
                                         Throwable error) {
        ServerPlayer player = currentPlayer(search);
        ServerLevel level = currentLevel(search, player);
        if (level == null) {
            cancel(search);
            return;
        }
        if (error != null || result == null || result.left().isEmpty()) {
            requestNext(search);
            return;
        }

        BlockPos candidatePos = candidate.pos;
        if (level.getBiome(candidatePos).is(TeleportCommands.IGNORE_RTP_BIOMES)) {
            requestNext(search);
            return;
        }

        BlockPos heightmapPos = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, candidatePos);
        if (heightmapPos.getY() <= 0) {
            requestNext(search);
        } else if (heightmapPos.getY() < level.getMaxBuildHeight()) {
            finish(search, player, level, heightmapPos, candidate.attempt);
        } else {
            requestFallbackArea(search, candidate);
        }
    }

    private static void requestFallbackArea(Search search, Candidate candidate) {
        ServerPlayer player = currentPlayer(search);
        ServerLevel level = currentLevel(search, player);
        if (level == null) {
            cancel(search);
            return;
        }

        int minChunkX = (candidate.pos.getX() - 16) >> 4;
        int maxChunkX = (candidate.pos.getX() + 16) >> 4;
        int minChunkZ = (candidate.pos.getZ() - 16) >> 4;
        int maxChunkZ = (candidate.pos.getZ() + 16) >> 4;
        List<CompletableFuture<Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure>>> futures = new ArrayList<>(9);
        for (int chunkX = minChunkX; chunkX <= maxChunkX; chunkX++) {
            for (int chunkZ = minChunkZ; chunkZ <= maxChunkZ; chunkZ++) {
                futures.add(requestChunk(level, new ChunkPos(chunkX, chunkZ)));
            }
        }

        CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new))
                .whenComplete((ignored, error) -> search.server
                        .execute(() -> inspectFallbackArea(search, candidate, futures, error)));
    }

    private static void inspectFallbackArea(
                                            Search search,
                                            Candidate candidate,
                                            List<CompletableFuture<Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure>>> futures,
                                            Throwable error) {
        ServerPlayer player = currentPlayer(search);
        ServerLevel level = currentLevel(search, player);
        if (level == null) {
            cancel(search);
            return;
        }
        if (error != null || futures.stream().anyMatch(future -> future.join().left().isEmpty())) {
            requestNext(search);
            return;
        }

        BlockPos center = new BlockPos(candidate.pos.getX(), level.getSeaLevel(), candidate.pos.getZ());
        for (BlockPos pos : BlockPos.spiralAround(center, 16, Direction.EAST, Direction.SOUTH)) {
            BlockState state = level.getBlockState(pos);
            if (state.blocksMotion() && !state.is(TeleportCommands.IGNORE_RTP_BLOCKS) &&
                    level.isEmptyBlock(pos.above()) && level.isEmptyBlock(pos.above(2)) &&
                    level.isEmptyBlock(pos.above(3))) {
                finish(search, player, level, pos.immutable(), candidate.attempt);
                return;
            }
        }
        requestNext(search);
    }

    private static void finish(Search search, ServerPlayer player, ServerLevel level, BlockPos groundPos, int attempt) {
        if (!SEARCHES.remove(search.playerId, search)) {
            return;
        }

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
    }

    private static void cancel(UUID playerId) {
        SEARCHES.remove(playerId);
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
    }

    private static final class Search {

        private final UUID playerId;
        private final MinecraftServer server;
        private final ResourceKey<Level> dimension;
        private final FTBEPlayerData playerData;
        private final int maxTries;
        private int nextAttempt;

        private Search(UUID playerId, MinecraftServer server, ResourceKey<Level> dimension,
                       FTBEPlayerData playerData, int maxTries) {
            this.playerId = playerId;
            this.server = server;
            this.dimension = dimension;
            this.playerData = playerData;
            this.maxTries = maxTries;
        }
    }

    private record Candidate(BlockPos pos, int attempt) {}
}
