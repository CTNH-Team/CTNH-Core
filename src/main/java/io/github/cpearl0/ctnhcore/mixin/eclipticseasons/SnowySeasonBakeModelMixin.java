package io.github.cpearl0.ctnhcore.mixin.eclipticseasons;

import io.github.cpearl0.ctnhcore.client.util.SnowOverlayQuadOffset;

import net.minecraft.client.renderer.block.model.BakedQuad;

import com.teamtea.eclipticseasons.client.model.SnowySeasonBakeModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = SnowySeasonBakeModel.class, remap = false)
public abstract class SnowySeasonBakeModelMixin {

    @Inject(method = "combineBakedQuads", at = @At("RETURN"))
    private void ctnhcore$offsetSnowQuads(List<BakedQuad> original, List<BakedQuad> snow,
                                          CallbackInfoReturnable<List<BakedQuad>> cir) {
        SnowOverlayQuadOffset.offsetAllIfNeeded(snow);
    }
}
