package io.github.cpearl0.ctnhcore.client.ponder.Mana;

import net.createmod.ponder.api.scene.EffectInstructions;
import net.minecraft.world.phys.Vec3;

import vazkii.botania.client.fx.WispParticleData;

public final class PonderParticleUtil {

    private PonderParticleUtil() {}

    /**
     * 在 Ponder 场景中创建由 A 到 B 的 Botania 火花传魔粒子特效。
     * 粒子的随机偏移、颜色抖动、大小范围、运动速度均与 Botania 原版
     * {@code SPARK_MANA_FLOW} 行为一致。
     *
     * @param effects      特效控制器，{@code builder.effects()}
     * @param from         起点
     * @param to           终点
     * @param color        颜色 {@code 0xRRGGBB}，魔力蓝为 {@code 0x00CCFF}
     * @param countPerTick 每 tick 粒子数，推荐 {@code 1}
     * @param duration     持续 tick 数，推荐 {@code 100}
     */
    public static void sparkManaFlow(EffectInstructions effects, Vec3 from, Vec3 to, int color, float countPerTick,
                                     int duration) {
        float r = ((color >> 16) & 0xFF) / 255.0F;
        float g = ((color >> 8) & 0xFF) / 255.0F;
        float b = (color & 0xFF) / 255.0F;

        effects.emitParticles(from, (world, x, y, z) -> {
            double ox = (Math.random() - 0.5) * 0.45;
            double oy = (Math.random() - 0.5) * 0.45;
            double oz = (Math.random() - 0.5) * 0.45;

            double tox = (Math.random() - 0.5) * 0.45;
            double toy = (Math.random() - 0.5) * 0.45;
            double toz = (Math.random() - 0.5) * 0.45;

            Vec3 start = new Vec3(x + ox, y + oy, z + oz);
            Vec3 end = new Vec3(to.x + tox, to.y + toy, to.z + toz);
            Vec3 motion = end.subtract(start).scale(0.04);

            float fr = r;
            float fg = g;
            float fb = b;
            if (world.random.nextFloat() < 0.25F) {
                fr += 0.2F * (float) world.random.nextGaussian();
                fg += 0.2F * (float) world.random.nextGaussian();
                fb += 0.2F * (float) world.random.nextGaussian();
            }
            float size = 0.125F + 0.125F * world.random.nextFloat();

            WispParticleData data = WispParticleData.wisp(size, fr, fg, fb).withNoClip(true);
            world.addParticle(data, start.x, start.y, start.z, motion.x, motion.y, motion.z);
        }, countPerTick, duration);
    }

    /**
     * 简化版本，使用默认值 {@code countPerTick=1, duration=100}。
     */
    public static void sparkManaFlow(EffectInstructions effects, Vec3 from, Vec3 to, int duration) {
        sparkManaFlow(effects, from, to, 0xFFFFFF, 1f, duration);
    }
}
