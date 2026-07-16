package io.github.cpearl0.ctnhcore.client.renderer;

import io.github.cpearl0.ctnhcore.common.machine.multiblock.generator.Arc_Generator;

import com.gregtechceu.gtceu.api.machine.feature.IMachineFeature;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRender;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderType;

import com.lowdragmc.lowdraglib.utils.TrackedDummyWorld;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import tech.vixhentx.mcmod.ctnhlib.utils.MachineUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.serialization.Codec;
import org.joml.Matrix4f;

import java.util.Random;

public class ArcBlockRender extends DynamicRender<IMachineFeature, ArcBlockRender> {

    public static final Codec<ArcBlockRender> CODEC = Codec.unit(ArcBlockRender::new);
    public static final DynamicRenderType<IMachineFeature, ArcBlockRender> TYPE = new DynamicRenderType<>(CODEC);

    private static final int ARC_CENTER_OFFSET_X = 0;
    private static final int ARC_CENTER_OFFSET_Y = 0;
    private static final int ARC_CENTER_OFFSET_Z = 8;

    // 等离子辉光球参数
    private static final float ARC_RADIUS = 5.0f;       // 辉光球放电外壳半径
    private static final int NUM_STREAMERS = 8;         // 电弧线的数量
    private static final int SEGMENTS_PER_STREAMER = 12; // 每条电弧折线段数
    private static final float ARC_HALF_THICKNESS = 0.0625f;
    private static final double TWO_PI = Math.PI * 2.0;
    private static final float MIN_DIRECTION_SPEED = 0.35f;
    private static final float MAX_DIRECTION_SPEED = 1.6f;
    private static final float MIN_ARC_REFRESH_SPEED = 0.55f;
    private static final float MAX_ARC_REFRESH_SPEED = 2.2f;

    public ArcBlockRender() {}

    @Override
    public DynamicRenderType<IMachineFeature, ArcBlockRender> getType() {
        return TYPE;
    }

    @Override
    public int getViewDistance() {
        return 64;
    }

    private static Vec3 createRandomizedEndPoint(float time, Random streamerRandom) {
        float directionSpeed = randomBetween(streamerRandom, MIN_DIRECTION_SPEED, MAX_DIRECTION_SPEED);
        float phaseOffset = streamerRandom.nextFloat() * 1024.0f;
        long directionFrame = (long) Math.floor((time + phaseOffset) * directionSpeed / 6.0f);
        long directionSeed = streamerRandom.nextLong() + directionFrame * 0xC2B2AE3D27D4EB4FL;
        Random directionRandom = new Random(directionSeed);

        double y = directionRandom.nextDouble() * 2.0 - 1.0;
        double angle = directionRandom.nextDouble() * TWO_PI;
        double horizontalScale = Math.sqrt(Math.max(0.0, 1.0 - y * y));
        float radius = ARC_RADIUS * randomBetween(directionRandom, 0.65f, 1.08f);

        return new Vec3(Math.cos(angle) * horizontalScale * radius, y * radius,
                Math.sin(angle) * horizontalScale * radius);
    }

    private static float randomBetween(Random random, float min, float max) {
        return Mth.lerp(random.nextFloat(), min, max);
    }

    private static long mixSeed(long seed) {
        seed ^= seed >>> 33;
        seed *= 0xff51afd7ed558ccdL;
        seed ^= seed >>> 33;
        seed *= 0xc4ceb9fe1a85ec53L;
        seed ^= seed >>> 33;
        return seed;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void render(IMachineFeature feature, float gameTime, PoseStack poseStack, MultiBufferSource buffer,
                       int combinedLight, int combinedOverlay) {
        var metaMachine = feature.self();

        if (metaMachine instanceof Arc_Generator machine && machine.isFormed() &&
                (machine.isActive() || machine.getLevel() instanceof TrackedDummyWorld)) {

            float time = machine.getLevel().getGameTime() + gameTime;
            VertexConsumer builder = buffer.getBuffer(RenderType.lightning());

            BlockPos controllerPos = machine.self().getPos();
            BlockPos targetPos = MachineUtils.getOffset(machine, ARC_CENTER_OFFSET_X, ARC_CENTER_OFFSET_Y,
                    ARC_CENTER_OFFSET_Z);

            double offsetX = targetPos.getX() - controllerPos.getX() + 0.5;
            double offsetY = targetPos.getY() - controllerPos.getY() + 0.5;
            double offsetZ = targetPos.getZ() - controllerPos.getZ() + 0.5;

            poseStack.pushPose();
            poseStack.translate(offsetX, offsetY, offsetZ);

            Matrix4f matrix = poseStack.last().pose();

            renderCentralCore(matrix, builder, time);

            Vec3 start = new Vec3(0.0, 0.0, 0.0);
            float baseHalfThickness = ARC_HALF_THICKNESS;

            for (int t = 0; t < NUM_STREAMERS; t++) {
                long streamerSeed = mixSeed(controllerPos.asLong() ^ (0x9E3779B97F4A7C15L * (long) (t + 1)));
                Random streamerRandom = new Random(streamerSeed);
                Vec3 end = createRandomizedEndPoint(time, streamerRandom);
                Vec3 delta = end.subtract(start);

                if (delta.lengthSqr() < 1.0E-12) continue;

                Vec3 direction = delta.normalize();
                Vec3 up = new Vec3(0.0, 1.0, 0.0);
                if (Math.abs(direction.y()) > 0.999999) {
                    up = new Vec3(1.0, 0.0, 0.0);
                }

                Vec3 side = direction.cross(up);
                if (side.lengthSqr() < 1.0E-12) {
                    up = new Vec3(0.0, 0.0, 1.0);
                    side = direction.cross(up);
                }
                side = side.normalize();
                Vec3 renderUp = side.cross(direction).normalize();

                float arcRefreshSpeed = randomBetween(streamerRandom, MIN_ARC_REFRESH_SPEED, MAX_ARC_REFRESH_SPEED);
                long seed = ((long) Math.floor(time * arcRefreshSpeed / 3.0f)) * 31337L + streamerSeed;
                Random rnd = new Random(seed);

                Vec3 prevL1 = start;
                Vec3 prevR1 = start;
                Vec3 prevL2 = start;
                Vec3 prevR2 = start;

                for (int i = 1; i <= SEGMENTS_PER_STREAMER; ++i) {
                    float progress = (float) i / (float) SEGMENTS_PER_STREAMER;
                    Vec3 currentMidpoint = start.add(delta.scale(progress));

                    float displacementMagnitude = baseHalfThickness *
                            ((float) Math.PI * randomBetween(rnd, 4.0f, 8.0f));
                    float falloff = 1.0F - (float) Math.pow(2.0F * progress - 1.0F, 2.0F);
                    displacementMagnitude *= falloff;
                    displacementMagnitude *= rnd.nextFloat() * 2.0F - 1.0F;

                    double angle = rnd.nextDouble() * (Math.PI * 2.0);
                    Vec3 displacementDir = side.scale(Math.cos(angle)).add(renderUp.scale(Math.sin(angle)));

                    Vec3 currentPos = currentMidpoint.add(displacementDir.scale(displacementMagnitude));

                    float currentHalfThickness = baseHalfThickness * (1.0F + 0.4F * (rnd.nextFloat() * 2.0F - 1.0F));
                    currentHalfThickness = Math.max(baseHalfThickness * 0.1F, currentHalfThickness);

                    Vec3 currentL1 = currentPos.subtract(side.scale(currentHalfThickness));
                    Vec3 currentR1 = currentPos.add(side.scale(currentHalfThickness));
                    Vec3 currentL2 = currentPos.subtract(renderUp.scale(currentHalfThickness));
                    Vec3 currentR2 = currentPos.add(renderUp.scale(currentHalfThickness));

                    float r, g, b;
                    if (progress < 0.3f) {
                        float p = progress / 0.3f;
                        r = Mth.lerp(p, 0.9f, 0.1f);
                        g = Mth.lerp(p, 0.95f, 0.4f);
                        b = Mth.lerp(p, 1.0f, 1.0f);
                    } else if (progress < 0.7f) {
                        float p = (progress - 0.3f) / 0.4f;
                        r = Mth.lerp(p, 0.1f, 0.0f);
                        g = Mth.lerp(p, 0.4f, 0.2f);
                        b = Mth.lerp(p, 1.0f, 1.0f);
                    } else {
                        float p = (progress - 0.7f) / 0.3f;
                        r = Mth.lerp(p, 0.0f, 0.0f);
                        g = Mth.lerp(p, 0.2f, 0.7f);
                        b = Mth.lerp(p, 1.0f, 1.0f);
                    }
                    float a = 1.0f;

                    builder.vertex(matrix, (float) prevL1.x, (float) prevL1.y, (float) prevL1.z).color(r, g, b, a)
                            .endVertex();
                    builder.vertex(matrix, (float) prevR1.x, (float) prevR1.y, (float) prevR1.z).color(r, g, b, a)
                            .endVertex();
                    builder.vertex(matrix, (float) currentR1.x, (float) currentR1.y, (float) currentR1.z)
                            .color(r, g, b, a).endVertex();
                    builder.vertex(matrix, (float) currentL1.x, (float) currentL1.y, (float) currentL1.z)
                            .color(r, g, b, a).endVertex();

                    builder.vertex(matrix, (float) prevL2.x, (float) prevL2.y, (float) prevL2.z).color(r, g, b, a)
                            .endVertex();
                    builder.vertex(matrix, (float) prevR2.x, (float) prevR2.y, (float) prevR2.z).color(r, g, b, a)
                            .endVertex();
                    builder.vertex(matrix, (float) currentR2.x, (float) currentR2.y, (float) currentR2.z)
                            .color(r, g, b, a).endVertex();
                    builder.vertex(matrix, (float) currentL2.x, (float) currentL2.y, (float) currentL2.z)
                            .color(r, g, b, a).endVertex();

                    prevL1 = currentL1;
                    prevR1 = currentR1;
                    prevL2 = currentL2;
                    prevR2 = currentR2;
                }
            }

            poseStack.popPose();
        }
    }

    private void renderCentralCore(Matrix4f pose, VertexConsumer consumer, float time) {
        float coreRadius = 0.35f + Mth.sin(time * 0.2f) * 0.03f;
        float r = 0.85f, g = 0.95f, b = 1.0f, a = 0.85f;

        consumer.vertex(pose, -coreRadius, -coreRadius, 0).color(r, g, b, a).endVertex();
        consumer.vertex(pose, coreRadius, -coreRadius, 0).color(r, g, b, a).endVertex();
        consumer.vertex(pose, coreRadius, coreRadius, 0).color(r, g, b, a).endVertex();
        consumer.vertex(pose, -coreRadius, coreRadius, 0).color(r, g, b, a).endVertex();

        consumer.vertex(pose, -coreRadius, 0, -coreRadius).color(r, g, b, a).endVertex();
        consumer.vertex(pose, coreRadius, 0, -coreRadius).color(r, g, b, a).endVertex();
        consumer.vertex(pose, coreRadius, 0, coreRadius).color(r, g, b, a).endVertex();
        consumer.vertex(pose, -coreRadius, 0, coreRadius).color(r, g, b, a).endVertex();

        consumer.vertex(pose, 0, -coreRadius, -coreRadius).color(r, g, b, a).endVertex();
        consumer.vertex(pose, 0, coreRadius, -coreRadius).color(r, g, b, a).endVertex();
        consumer.vertex(pose, 0, coreRadius, coreRadius).color(r, g, b, a).endVertex();
        consumer.vertex(pose, 0, -coreRadius, coreRadius).color(r, g, b, a).endVertex();
    }

    @Override
    public AABB getRenderBoundingBox(IMachineFeature machine) {
        BlockPos pos = machine.self().getPos();
        return new AABB(pos).inflate(64.0, 64.0, 64.0);
    }
}
