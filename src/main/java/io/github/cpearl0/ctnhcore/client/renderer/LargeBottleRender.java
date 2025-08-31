package io.github.cpearl0.ctnhcore.client.renderer;

import com.gregtechceu.gtceu.api.machine.feature.multiblock.IFluidRenderMulti;
import com.gregtechceu.gtceu.client.renderer.block.FluidBlockRenderer;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRender;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderType;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.level.material.Fluid;

public class LargeBottleRender extends DynamicRender<IFluidRenderMulti, LargeBottleRender> {
    private final FluidBlockRenderer fluidBlockRenderer;
    private Fluid cachedFluid;

    public LargeBottleRender() {
        fluidBlockRenderer = FluidBlockRenderer.Builder.create()
                .setFaceOffset(-0.125f)
                .setForcedLight(LightTexture.FULL_BRIGHT)
                .getRenderer();
    }

    @Override
    public int getViewDistance() {
        return 32;
    }
    @Override
    public void render(IFluidRenderMulti iFluidRenderMulti, float partialTicks, PoseStack stack, MultiBufferSource buffer,
                       int combinedLight, int combinedOverlay) {
//        if (!ConfigHolder.INSTANCE.client.renderer.renderFluids) return;
//        if (blockEntity instanceof MetaMachineBlockEntity mm) {
//            if (mm.metaMachine instanceof LargeBottleMachine tankMachine) {
//                if(tankMachine.getTank().isEmpty()){
//                    cachedFluid = null;
//                }
//                cachedFluid = tankMachine.getTank().getFluidInTank(0).getFluid();
//
//                if (cachedFluid == null) {
//                    return;
//                }
//
//                stack.pushPose();
//                var pose = stack.last().pose();
//
//                var fluidRenderType = ItemBlockRenderTypes.getRenderLayer(cachedFluid.defaultFluidState());
//                var consumer = buffer.getBuffer(RenderTypeHelper.getEntityRenderType(fluidRenderType, false));
//
//                var up = RelativeDirection.UP.getRelativeFacing(tankMachine.getFrontFacing(), tankMachine.getUpwardsFacing(),
//                        tankMachine.isFlipped());
//                if (up.getAxis() != Direction.Axis.Y) up = up.getOpposite();
//                fluidBlockRenderer.drawPlane(up, tankMachine.SideBlockOffsets, pose, consumer, cachedFluid,
//                        RenderUtil.FluidTextureType.STILL, combinedOverlay, tankMachine.getPos());
//
//                stack.popPose();
//            }
//        }
    }

    @Override
    public DynamicRenderType<IFluidRenderMulti, LargeBottleRender> getType() {
        return null;
    }
}
