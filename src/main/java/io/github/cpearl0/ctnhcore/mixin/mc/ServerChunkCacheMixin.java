package io.github.cpearl0.ctnhcore.mixin.mc;

import io.github.cpearl0.ctnhcore.CTNHConfig;

import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LocalMobCapCalculator;
import net.minecraft.world.level.NaturalSpawner;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import javax.annotation.Nullable;

@Mixin(value = ServerChunkCache.class)
public class ServerChunkCacheMixin {

    @Shadow
    @Nullable
    private NaturalSpawner.SpawnState lastSpawnState;

    @Shadow
    @Final
    public ServerLevel level;

    @Redirect(method = "tickChunks",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/world/level/NaturalSpawner;createState(ILjava/lang/Iterable;Lnet/minecraft/world/level/NaturalSpawner$ChunkGetter;Lnet/minecraft/world/level/LocalMobCapCalculator;)Lnet/minecraft/world/level/NaturalSpawner$SpawnState;"))
    NaturalSpawner.SpawnState useLast(int spawnableChunkCount, Iterable<Entity> entities,
                                      NaturalSpawner.ChunkGetter chunkGetter, LocalMobCapCalculator calculator) {
        if (lastSpawnState != null && level.getGameTime() % CTNHConfig.INSTANCE.optimization.natureSpawnCycle != 0) {
            return lastSpawnState;
        }
        return NaturalSpawner.createState(spawnableChunkCount, entities, chunkGetter, calculator);
    }
}
