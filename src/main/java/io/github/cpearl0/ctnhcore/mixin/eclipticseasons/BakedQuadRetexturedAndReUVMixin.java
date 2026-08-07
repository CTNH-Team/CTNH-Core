package io.github.cpearl0.ctnhcore.mixin.eclipticseasons;

import net.minecraft.client.renderer.block.model.BakedQuad;

import com.teamtea.eclipticseasons.client.model.bakequad.BakedQuadRetexturedAndReUV;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Ecliptic Seasons lays a snow-overlay quad on top of block faces with identical
// vertex positions, which makes the overlay z-fight with GT machine faces. Nudge the
// overlay out along its face normal by a small epsilon.
@Mixin(value = BakedQuadRetexturedAndReUV.class, remap = false)
public abstract class BakedQuadRetexturedAndReUVMixin {

    @Inject(method = "<init>(Lnet/minecraft/client/renderer/block/model/BakedQuad;Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;ZF)V",
            at = @At("RETURN"))
    private void ctnhcore$offsetSnowOverlayQuad(BakedQuad quad,
                                                net.minecraft.client.renderer.texture.TextureAtlasSprite textureIn,
                                                boolean isSlabDown, float offset, CallbackInfo ci) {
        SnowOverlayQuadOffset.pushOutAlongFaceNormal((BakedQuad) (Object) this,
                SnowOverlayQuadOffset.SNOW_OVERLAY_OFFSET);
    }
}
