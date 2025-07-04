package io.github.cpearl0.ctnhcore.client.renderer;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.client.renderer.machine.WorkableCasingMachineRenderer;
import com.lowdragmc.lowdraglib.utils.TrackedDummyWorld;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.MachineUtils;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.magic.ManaCondenserMachine;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EnderDragonRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class ManaCondenserRender extends WorkableCasingMachineRenderer {
    public ManaCondenserRender() {
        super(GTCEu.id("block/casings/solid/machine_casing_stable_titanium"), GTCEu.id("block/multiblock/generator/large_steam_turbine"), false);
    }

    @Override
    public boolean hasTESR(BlockEntity blockEntity) {
        return true;
    }
    @Override
    public int getViewDistance() {
        return 48;
    }
    @Override
    public boolean isGlobalRenderer(BlockEntity blockEntity) {
        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void render(BlockEntity blockEntity, float gameTime, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        if (blockEntity instanceof IMachineBlockEntity machineBlockEntity && machineBlockEntity.getMetaMachine() instanceof ManaCondenserMachine machine && machine.isFormed() && (machine.isActive() || blockEntity.getLevel() instanceof TrackedDummyWorld)) {
            List<BlockPos> target = List.of(
                    MachineUtils.getOffset(machine, 0, 11, 13),
                    MachineUtils.getOffset(machine, 0, 11, -13),
                    MachineUtils.getOffset(machine, 8, 11, 8),
                    MachineUtils.getOffset(machine, 8, 11, -8),
                    MachineUtils.getOffset(machine, -8, 11, 8),
                    MachineUtils.getOffset(machine, -8, 11, -8),
                    MachineUtils.getOffset(machine, 13, 11, 0),
                    MachineUtils.getOffset(machine, -13, 11, 0),
                    MachineUtils.getOffset(machine, 5, 8, 5),
                    MachineUtils.getOffset(machine, 5, 8, -5),
                    MachineUtils.getOffset(machine, -5, 8, 5),
                    MachineUtils.getOffset(machine, -5, 8, -5));
            BlockPos core = MachineUtils.getOffset(machine, 0, 5, 0);
            boolean reverse = false;
            if (machine.getRecipeLogic().getLastRecipe().data != null && machine.getRecipeLogic().getLastRecipe().data.getString("mode").equals("reverse")) {
                reverse = true;
            }
            float xoff = 0;
            float zoff = 0;
            var facing = machine.getFrontFacing();
            switch (facing) {
                case NORTH -> {
                    xoff = -0.5F;
                    zoff = -0.5F;
                }
                case SOUTH -> {
                    xoff = 0.5F;
                    zoff = 0.5F;
                }
                case WEST -> {
                    xoff = -0.5F;
                    zoff = 0.5F;
                }
                case EAST -> {
                    xoff = 0.5F;
                    zoff = -0.5F;
                }
            }
            for (BlockPos pos : target) {
                float x = pos.getX() - core.getX();
                float y = pos.getY() - core.getY();
                float z = pos.getZ() - core.getZ();
                if (reverse) {
                    poseStack.pushPose();
                    poseStack.translate(xoff, 4, zoff);
                    EnderDragonRenderer.renderCrystalBeams(-x, -y, -z, blockEntity.getLevel().getGameTime() + gameTime, 2000, poseStack, buffer, 15);
                    poseStack.popPose();
                }
                else {
                    poseStack.pushPose();
                    poseStack.translate(xoff, 4, zoff);
                    EnderDragonRenderer.renderCrystalBeams(x, y, z, blockEntity.getLevel().getGameTime() + gameTime, 2000, poseStack, buffer, 15);
                    poseStack.popPose();
                }

            }
        }
    }
}
