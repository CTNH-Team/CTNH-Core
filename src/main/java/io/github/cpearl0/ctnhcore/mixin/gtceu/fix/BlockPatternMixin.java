package io.github.cpearl0.ctnhcore.mixin.gtceu.fix;

import com.gregtechceu.gtceu.api.pattern.BlockPattern;
import com.gregtechceu.gtceu.api.pattern.MultiblockState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BlockPattern.class, remap = false)
public class BlockPatternMixin {
    @Shadow
    protected int[] formedRepetitionCount;

    @Inject(method = "checkPatternAt(Lcom/gregtechceu/gtceu/api/pattern/MultiblockState;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;Lnet/minecraft/core/Direction;ZZ)Z",
            at = @At("TAIL")
    )
    void addFormedRepetitionCount(MultiblockState worldState, BlockPos centerPos, Direction frontFacing, Direction upwardsFacing, boolean isFlipped, boolean savePredicate, CallbackInfoReturnable<Boolean> cir){
        worldState.getMatchContext().getOrPut("formedRepetitionCount", formedRepetitionCount);
    }
}
