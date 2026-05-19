package io.github.cpearl0.ctnhcore.mixin.mc;

import io.github.cpearl0.ctnhcore.CTNHConfig;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.chunk.LevelChunk;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = NaturalSpawner.class)
public class NaturalSpawnerMixin {

    @Inject(method = "spawnCategoryForChunk", at = @At("HEAD"), cancellable = true)
    private static void sampleChunks(MobCategory category, ServerLevel level, LevelChunk chunk,
                                     NaturalSpawner.SpawnPredicate filter, NaturalSpawner.AfterSpawnCallback callback,
                                     CallbackInfo ci) {
        int hash = chunk.getPos().x * 31 + chunk.getPos().z;
        long time = level.getGameTime();
        if ((time + hash) % CTNHConfig.INSTANCE.optimization.natureSpawnCycle != 0) {
            ci.cancel();
        }
    }
}
