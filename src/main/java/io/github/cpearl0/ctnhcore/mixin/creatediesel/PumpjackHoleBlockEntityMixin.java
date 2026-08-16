package io.github.cpearl0.ctnhcore.mixin.creatediesel;

import io.github.cpearl0.ctnhcore.integration.creatediesel.GTBedrockOilBridge;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.material.Fluid;

import com.jesz.createdieselgenerators.content.pumpjack.PumpjackHoleBlockEntity;
import com.jesz.createdieselgenerators.world.OilChunksSavedData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = PumpjackHoleBlockEntity.class, remap = false)
public class PumpjackHoleBlockEntityMixin {

    @ModifyArg(
               method = "pumpjackRotation",
               at = @At(
                        value = "INVOKE",
                        target = "Lnet/minecraftforge/fluids/FluidStack;<init>(Lnet/minecraft/world/level/material/Fluid;I)V",
                        ordinal = 0),
               index = 0)
    private Fluid ctnhcore$useGTBedrockOilFluid(Fluid originalFluid) {
        PumpjackHoleBlockEntity pumpjack = (PumpjackHoleBlockEntity) (Object) this;
        if (pumpjack.getLevel() instanceof ServerLevel level) {
            Fluid oilFluid = GTBedrockOilBridge.getOilFluid(level, new ChunkPos(pumpjack.getBlockPos()));
            if (oilFluid != null) {
                return oilFluid;
            }
        }
        return originalFluid;
    }

    @Redirect(
              method = "pumpjackRotation",
              at = @At(
                       value = "INVOKE",
                       target = "Lcom/jesz/createdieselgenerators/world/OilChunksSavedData;getChunkOilAmount(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/ChunkPos;)I"))
    private int ctnhcore$getGTBedrockOilAmount(ServerLevel level, ChunkPos chunkPos) {
        if (GTBedrockOilBridge.hasOilVein(level, chunkPos)) {
            return GTBedrockOilBridge.getPumpjackAvailable(level, chunkPos);
        }
        return OilChunksSavedData.getChunkOilAmount(level, chunkPos);
    }

    @Redirect(
              method = "pumpjackRotation",
              at = @At(
                       value = "INVOKE",
                       target = "Lcom/jesz/createdieselgenerators/world/OilChunksSavedData;setChunkOilAmount(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/ChunkPos;I)V"))
    private void ctnhcore$depleteGTBedrockOil(ServerLevel level, ChunkPos chunkPos, int remainingAmount) {
        if (GTBedrockOilBridge.hasOilVein(level, chunkPos)) {
            int previousAmount = GTBedrockOilBridge.getPumpjackAvailable(level, chunkPos);
            GTBedrockOilBridge.consumePumpedFluid(level, chunkPos,
                    Math.max(0, previousAmount - remainingAmount));
            return;
        }
        OilChunksSavedData.setChunkOilAmount(level, chunkPos, remainingAmount);
    }
}
