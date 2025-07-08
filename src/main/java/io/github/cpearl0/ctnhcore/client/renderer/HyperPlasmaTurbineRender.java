package io.github.cpearl0.ctnhcore.client.renderer;

import com.gregtechceu.gtceu.client.renderer.block.FluidBlockRenderer;
import com.gregtechceu.gtceu.client.renderer.machine.WorkableCasingMachineRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Fluid;

public class HyperPlasmaTurbineRender extends WorkableCasingMachineRenderer {

    private final FluidBlockRenderer fluidBlockRenderer;
    private Fluid cachedFluid;
    private ResourceLocation cachedRecipe;

    public HyperPlasmaTurbineRender(ResourceLocation baseCasing, ResourceLocation workableModel) {
        super(baseCasing, workableModel);
        fluidBlockRenderer = FluidBlockRenderer.Builder.create()
//                .setFaceOffset(-0.125f)
                .setForcedLight(LightTexture.FULL_BRIGHT)
                .getRenderer();
    }

    @Override
    public boolean hasTESR(BlockEntity blockEntity) {
        return true;
    }

    @Override
    public void render(BlockEntity blockEntity, float partialTicks, PoseStack stack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        super.render(blockEntity, partialTicks, stack, buffer, combinedLight, combinedOverlay);
//        if(blockEntity instanceof MetaMachineBlockEntity mm){
//            if(mm.metaMachine instanceof HyperPlasmaTurbineMachine hptm && hptm.recipeLogic.isWorking()){
//                var lastRecipe = hptm.recipeLogic.getLastRecipe();
//                if (lastRecipe == null) {
//                    cachedRecipe = null;
//                    cachedFluid = null;
//                } else if (hptm.getOffsetTimer() % 20 == 0 || lastRecipe.id != cachedRecipe) {
//                    cachedRecipe = lastRecipe.id;
//                    if (hptm.isActive()) {
//                        cachedFluid = RenderUtil.getRecipeFluidToRender(lastRecipe);
//                    } else {
//                        cachedFluid = null;
//                    }
//                }
//
//                if (cachedFluid == null) {
//                    return;
//                }
//
//                BlockPos center = hptm.getPos();
//
//                var fluidRenderType = ItemBlockRenderTypes.getRenderLayer(cachedFluid.defaultFluidState());
//                var consumer = buffer.getBuffer(RenderTypeHelper.getEntityRenderType(fluidRenderType, false));
//                fluidBlockRenderer.drawPlane(Direction.WEST, HyperPlasmaTurbineRegister.blockOffsetWEST0,stack.last().pose(),consumer,cachedFluid,RenderUtil.FluidTextureType.FLOWING,combinedOverlay,center);
//                fluidBlockRenderer.drawPlane(Direction.EAST, HyperPlasmaTurbineRegister.blockOffsetEAST0,stack.last().pose(),consumer,cachedFluid,RenderUtil.FluidTextureType.FLOWING,combinedOverlay,center);
//
//            }
//        }
    }
}
