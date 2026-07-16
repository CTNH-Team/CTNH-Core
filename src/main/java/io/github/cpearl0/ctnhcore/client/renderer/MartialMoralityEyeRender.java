package io.github.cpearl0.ctnhcore.client.renderer;

import io.github.cpearl0.ctnhcore.client.ClientUtil;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.MartialMoralityEyeMachine;

import com.gregtechceu.gtceu.api.machine.feature.IMachineFeature;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRender;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderType;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTDimensionMarkers;

import com.lowdragmc.lowdraglib.utils.TrackedDummyWorld;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import com.moguang.ctnhbio.registry.CBBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.Codec;
import com.simibubi.create.AllBlocks;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import tech.vixhentx.mcmod.ctnhlib.utils.MachineUtils;

@SuppressWarnings("removal")
public class MartialMoralityEyeRender extends DynamicRender<IMachineFeature, MartialMoralityEyeRender> {

    public static Codec<MartialMoralityEyeRender> CODEC = Codec.unit(MartialMoralityEyeRender::new);
    public static DynamicRenderType<IMachineFeature, MartialMoralityEyeRender> TYPE = new DynamicRenderType<>(CODEC);

    private static final int MAX_LIGHT = 15728880;

    public MartialMoralityEyeRender() {}

    @Override
    public DynamicRenderType<IMachineFeature, MartialMoralityEyeRender> getType() {
        return TYPE;
    }

    @Override
    public void render(IMachineFeature feature, float partialTicks, PoseStack poseStack, MultiBufferSource buffer,
                       int combinedLight, int combinedOverlay) {
        var metaMachine = feature.self();
        if (metaMachine instanceof MartialMoralityEyeMachine machine) {
            if (machine.isFormed() || machine.getLevel() instanceof TrackedDummyWorld) {
                Level level = machine.getLevel();
                BlockPos machinePos = machine.getPos();
                BlockPos targetPos = MachineUtils.getOffset(machine, 0, 0, 16);

                double dx = targetPos.getX() - machinePos.getX();
                double dy = targetPos.getY() - machinePos.getY();
                double dz = targetPos.getZ() - machinePos.getZ();

                float time = level.getGameTime() + partialTicks;

                poseStack.pushPose();

                poseStack.translate(dx + 0.5, dy + 0.5, dz + 0.5);

                poseStack.pushPose();

                float centerRotY = time * 0.0261799f;
                float centerRotX = time * 0.0139626f;

                poseStack.mulPose(new Quaternionf().rotationYXZ(centerRotY, centerRotX, 0));

                // 被围绕方块尺寸
                poseStack.scale(9.0f, 9.0f, 9.0f);

                poseStack.translate(0, -0.125f, 0);

                ClientUtil.renderStatic(GTDimensionMarkers.OVERWORLD_MARKER.asStack(), ItemDisplayContext.GROUND,
                        MAX_LIGHT, OverlayTexture.NO_OVERLAY, poseStack, buffer, level, 0);
                poseStack.popPose();

                ItemStack[] planets = new ItemStack[] {
                        // CMBlocks.MANA_STEEL_CASING.asStack(),
                        CBBlocks.FLESH_CASING.asStack(),
                        GTBlocks.MACHINE_CASING_IV.asStack(),
                        AllBlocks.BRASS_CASING.asStack()
                };

                double[] orbitRadii = { 3.5, 5.5, 7.5, 9.5 };
                float[] orbitSpeeds = { 0.0436332f, 0.0314159f, 0.0209439f, 0.0139626f };
                float[] selfRotationSpeeds = { 0.0872664f, 0.0523598f, 0.0698131f, 0.0349065f };
                float[] orbitTilts = { 15f, -10f, 25f, -5f };

                for (int i = 0; i < planets.length; i++) {
                    if (planets[i] == null || planets[i].isEmpty()) continue;

                    poseStack.pushPose();

                    float orbitRad = time * orbitSpeeds[i];
                    poseStack.mulPose(new Quaternionf().fromAxisAngleDeg(new Vector3f(0, 0, 1), orbitTilts[i]));
                    poseStack.mulPose(new Quaternionf().rotationY(orbitRad));

                    poseStack.translate(orbitRadii[i], 0, 0);

                    float selfRad = time * selfRotationSpeeds[i];

                    poseStack.mulPose(new Quaternionf().rotationYXZ(selfRad, selfRad * 0.5f, 0));

                    // 围绕方块尺寸
                    poseStack.scale(4.5f, 4.5f, 4.5f);

                    poseStack.translate(0, -0.125f, 0);

                    ClientUtil.renderStatic(planets[i], ItemDisplayContext.GROUND, MAX_LIGHT, OverlayTexture.NO_OVERLAY,
                            poseStack, buffer, level, 0);

                    poseStack.popPose();
                }

                poseStack.popPose();
            }
        }
    }

    @Override
    public AABB getRenderBoundingBox(IMachineFeature machine) {
        return super.getRenderBoundingBox(machine).inflate(20.0);
    }

    @Override
    public int getViewDistance() {
        return 128;
    }
}
