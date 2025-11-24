package io.github.cpearl0.ctnhcore.mixin.gtceu;

import com.gregtechceu.gtceu.api.data.worldgen.ores.GeneratedVeinMetadata;
import com.gregtechceu.gtceu.integration.map.xaeros.minimap.ore.OreVeinElement;
import com.gregtechceu.gtceu.integration.map.xaeros.minimap.ore.OreVeinElementRenderer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import xaero.hud.minimap.element.render.MinimapElementRenderInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = OreVeinElementRenderer.class, remap = false)
public class OreVeinElementRendererMixin {

    @Inject(
            method = "renderElement(Lcom/gregtechceu/gtceu/integration/map/xaeros/minimap/ore/OreVeinElement;ZZDFDDLxaero/hud/minimap/element/render/MinimapElementRenderInfo;Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/renderer/MultiBufferSource$BufferSource;)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void ctnhcore$validateVeinBeforeRender(OreVeinElement element,
                                                boolean highlit,
                                                boolean outOfBounds,
                                                double optionalDepth, float optionalScale,
                                                double partialX, double partialY,
                                                MinimapElementRenderInfo renderInfo,
                                                GuiGraphics graphics,
                                                MultiBufferSource.BufferSource renderTypeBuffers,
                                                CallbackInfoReturnable<Boolean> cir) {
        GeneratedVeinMetadata vein = element.getVein();
        if (vein == null
                || vein.definition() == null
                || vein.definition().veinGenerator() == null
                || vein.definition().veinGenerator().getAllMaterials().isEmpty()) {
            //TODO: 为什么移除了的矿脉还会生成？
            //System.err.println("[GTCEu][Mixin] Ore vein has no valid materials! Vein Info: " + vein.id());
            cir.setReturnValue(false); // 提前结束，不渲染
        }
    }
}
