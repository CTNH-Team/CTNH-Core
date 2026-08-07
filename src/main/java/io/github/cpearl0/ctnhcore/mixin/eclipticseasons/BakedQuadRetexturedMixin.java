package io.github.cpearl0.ctnhcore.mixin.eclipticseasons;

import net.minecraft.client.renderer.block.model.BakedQuad;

import com.teamtea.eclipticseasons.client.model.bakequad.BakedQuadRetextured;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BakedQuadRetextured.class, remap = false)
public abstract class BakedQuadRetexturedMixin {

    @Inject(method = "<init>(Lnet/minecraft/client/renderer/block/model/BakedQuad;Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;)V",
            at = @At("RETURN"))
    private void ctnhcore$offsetSnowOverlayQuad(BakedQuad quad,
                                                net.minecraft.client.renderer.texture.TextureAtlasSprite textureIn,
                                                CallbackInfo ci) {
        SnowOverlayQuadOffset.pushOutAlongFaceNormal((BakedQuad) (Object) this,
                SnowOverlayQuadOffset.SNOW_OVERLAY_OFFSET);
    }
}
