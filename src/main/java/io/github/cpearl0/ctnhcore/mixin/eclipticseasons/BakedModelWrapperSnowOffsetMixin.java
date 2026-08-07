package io.github.cpearl0.ctnhcore.mixin.eclipticseasons;

import io.github.cpearl0.ctnhcore.client.util.SnowOverlayQuadOffset;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.BakedModelWrapper;
import net.minecraftforge.client.model.data.ModelData;

import com.teamtea.eclipticseasons.client.model.SnowyBakedModelWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = BakedModelWrapper.class, remap = false)
public abstract class BakedModelWrapperSnowOffsetMixin {

    @Inject(method = "getQuads(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/Direction;Lnet/minecraft/util/RandomSource;)Ljava/util/List;",
            at = @At("RETURN"),
            remap = false)
    private void ctnhcore$offsetSnowyWrapperQuads3(BlockState state, Direction direction, RandomSource random,
                                                   CallbackInfoReturnable<List<BakedQuad>> cir) {
        if ((Object) this instanceof SnowyBakedModelWrapper wrapper && !wrapper.isReplace()) {
            SnowOverlayQuadOffset.offsetAllIfNeeded(cir.getReturnValue());
        }
    }

    @Inject(method = "getQuads(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/Direction;Lnet/minecraft/util/RandomSource;Lnet/minecraftforge/client/model/data/ModelData;Lnet/minecraft/client/renderer/RenderType;)Ljava/util/List;",
            at = @At("RETURN"),
            remap = false)
    private void ctnhcore$offsetSnowyWrapperQuads(BlockState state, Direction direction, RandomSource random,
                                                  ModelData modelData, RenderType renderType,
                                                  CallbackInfoReturnable<List<BakedQuad>> cir) {
        if ((Object) this instanceof SnowyBakedModelWrapper wrapper && !wrapper.isReplace()) {
            SnowOverlayQuadOffset.offsetAllIfNeeded(cir.getReturnValue());
        }
    }
}
