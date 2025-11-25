package io.github.cpearl0.ctnhcore.mixin.gtceu;

import com.gregtechceu.gtceu.api.data.worldgen.ores.GeneratedVeinMetadata;

import com.gregtechceu.gtceu.integration.map.xaeros.worldmap.ore.OreVeinElement;
import com.gregtechceu.gtceu.integration.map.xaeros.worldmap.ore.OreVeinElementRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.TextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import xaero.map.graphics.renderer.multitexture.MultiTextureRenderTypeRendererProvider;

@Mixin(value = OreVeinElementRenderer.class, remap = false)
public class OreVeinElementRendererWorldMixin {
    @Inject(
            method = "renderElement(ILcom/gregtechceu/gtceu/integration/map/xaeros/worldmap/ore/OreVeinElement;ZLnet/minecraft/client/Minecraft;Lnet/minecraft/client/gui/GuiGraphics;DDDDFDDLnet/minecraft/client/renderer/texture/TextureManager;Lnet/minecraft/client/gui/Font;Lnet/minecraft/client/renderer/MultiBufferSource$BufferSource;Lxaero/map/graphics/renderer/multitexture/MultiTextureRenderTypeRendererProvider;IDFDDZF)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void ctnhcore$validateVeinBeforeRender(int location,
                                                OreVeinElement element,
                                                boolean hovered,
                                                Minecraft mc,
                                                GuiGraphics graphics,
                                                double cameraX,
                                                double cameraZ,
                                                double mouseX,
                                                double mouseZ,
                                                float brightness,
                                                double scale,
                                                double screenSizeBasedScale,
                                                TextureManager textureManager,
                                                Font fontRenderer,
                                                MultiBufferSource.BufferSource renderTypeBuffers, MultiTextureRenderTypeRendererProvider rendererProvider, int elementIndex, double optionalDepth, float optionalScale, double partialX, double partialY, boolean cave, float partialTicks, CallbackInfoReturnable<Boolean> cir) {
        GeneratedVeinMetadata vein = element.getVein();
        if (vein == null
                || vein.definition() == null
                || vein.definition().veinGenerator() == null
                || vein.definition().veinGenerator().getAllMaterials().isEmpty()) {
            //System.err.println("[GTCEu][Mixin] Ore vein has no valid materials! Vein Info: " + vein.id());
            cir.setReturnValue(false); // 提前结束，不渲染
        }
    }
}
