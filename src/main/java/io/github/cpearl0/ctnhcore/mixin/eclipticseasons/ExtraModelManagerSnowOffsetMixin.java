package io.github.cpearl0.ctnhcore.mixin.eclipticseasons;

import io.github.cpearl0.ctnhcore.client.util.SnowOverlayQuadOffset;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.world.level.block.state.BlockState;

import com.teamtea.eclipticseasons.client.core.ExtraModelManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = ExtraModelManager.class, remap = false)
public abstract class ExtraModelManagerSnowOffsetMixin {

    @Inject(method = "makeSnowyBakedQuads", at = @At("RETURN"))
    private static void ctnhcore$offsetSnowyBakedQuads(BlockState state, List<BakedQuad> original,
                                                       ArrayList<BakedQuad> extra,
                                                       CallbackInfoReturnable<List<BakedQuad>> cir) {
        SnowOverlayQuadOffset.offsetAllIfNeeded(extra);
    }
}
