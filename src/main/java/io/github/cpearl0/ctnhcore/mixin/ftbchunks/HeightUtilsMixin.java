package io.github.cpearl0.ctnhcore.mixin.ftbchunks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;

import dev.ftb.mods.ftbchunks.util.HeightUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static dev.ftb.mods.ftbchunks.util.HeightUtils.UNKNOWN;

@Mixin(value = HeightUtils.class, remap = false)
public class HeightUtilsMixin {

    @Inject(method = "skipBlock", at = @At("HEAD"), cancellable = true)
    private static void checkNull(Level level, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state == null) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "getHeight",
            at = @At(value = "INVOKE",
                     target = "Lnet/minecraft/world/level/chunk/ChunkAccess;getBlockState(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;",
                     remap = true,
                     shift = At.Shift.AFTER),
            cancellable = true)
    private static void checkNull(Level level, ChunkAccess chunkAccess, BlockPos.MutableBlockPos pos,
                                  CallbackInfoReturnable<Integer> cir) {
        if (chunkAccess.getBlockState(pos) == null) {
            cir.setReturnValue(UNKNOWN);
        }
    }
}
