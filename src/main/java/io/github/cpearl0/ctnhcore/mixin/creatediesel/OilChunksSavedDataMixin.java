package io.github.cpearl0.ctnhcore.mixin.creatediesel;

import io.github.cpearl0.ctnhcore.integration.creatediesel.GTBedrockOilBridge;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;

import com.jesz.createdieselgenerators.world.OilChunksSavedData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = OilChunksSavedData.class, remap = false)
public class OilChunksSavedDataMixin {

    @Inject(
            method = "getChunkOilAmount(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/ChunkPos;)I",
            at = @At("HEAD"),
            cancellable = true)
    private static void ctnhcore$excludeGTFieldsFromCDGOil(ServerLevel level, ChunkPos chunkPos,
                                                           CallbackInfoReturnable<Integer> cir) {
        if (GTBedrockOilBridge.hasOilVein(level, chunkPos)) {
            cir.setReturnValue(0);
        }
    }

    @Inject(method = "getBaseOilAmount", at = @At("HEAD"), cancellable = true)
    private static void ctnhcore$preventOverlappingOilFields(ServerLevel level, ChunkPos chunkPos,
                                                             CallbackInfoReturnable<Integer> cir) {
        if (GTBedrockOilBridge.hasOilVein(level, chunkPos)) {
            cir.setReturnValue(0);
        }
    }

    @Inject(method = "setChunkOilAmount", at = @At("HEAD"), cancellable = true)
    private static void ctnhcore$ignoreCDGOilWritesInGTFields(ServerLevel level, ChunkPos chunkPos, int amount,
                                                              CallbackInfo ci) {
        if (GTBedrockOilBridge.hasOilVein(level, chunkPos)) {
            ci.cancel();
        }
    }
}
