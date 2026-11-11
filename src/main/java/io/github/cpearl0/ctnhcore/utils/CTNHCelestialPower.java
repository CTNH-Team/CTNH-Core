package io.github.cpearl0.ctnhcore.utils;

import com.ctnh.ctnhastral.common.universe.CACelestialBodies;
import com.ctnh.ctnhastral.common.universe.CelestialBody;

import net.minecraft.world.level.Level;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;

/**
 * 光伏设备的维度倍率表。行星倍率由天体光照强度按倒数平方推出，地球为基准 1，光照越弱倍率越高。
 */
public final class CTNHCelestialPower {

    // TODO: 数值待设计。当前行星倍率取地球光照与目标光照之比的平方（倒数平方），
    // 与旧版硬编码倍率不完全一致（火星 4.00 对旧 5，无人机太阳倍率火星 4.00 对旧 16）。
    // Astral 的 solarPower 在各行星间差距很小，无法还原旧的指数式梯度，需重新设计曲线或改用查表。
    private static final double ORBIT_BONUS = 4.0D;

    private CTNHCelestialPower() {}

    private static boolean isOverworldLike(Level level) {
        var dimension = level.dimension();
        var location = dimension.location().toString();
        return dimension == Level.OVERWORLD || location.equals("twilightforest:twilight_forest") ||
                location.equals("mythicbotany:alfheim");
    }

    public static boolean isOrbit(Level level) {
        CelestialBody body = CACelestialBodies.get(level.dimension());
        return body != null && body.isSatellite();
    }

    public static double solarFactor(Level level) {
        CelestialBody body = CACelestialBodies.get(level.dimension());
        if (body == null || body.solarPower() <= 0.0F) {
            return 0.0D;
        }
        double ratio = CACelestialBodies.EARTH.solarPower() / body.solarPower();
        return ratio * ratio;
    }

    public static int photovoltaicRate(Level level) {
        if (isOverworldLike(level)) {
            return 1;
        }
        if (level.dimension() == AetherDimensions.AETHER_LEVEL) {
            return 2;
        }
        double factor = solarFactor(level);
        return factor <= 0.0D ? 0 : Math.max(1, (int) Math.round(factor));
    }

    public static double solarMultiplier(Level level) {
        if (isOverworldLike(level)) {
            return 0.5D;
        }
        if (level.dimension() == AetherDimensions.AETHER_LEVEL) {
            return 1.0D;
        }
        double factor = solarFactor(level);
        if (factor <= 0.0D) {
            return 1.0D;
        }
        return isOrbit(level) ? factor * ORBIT_BONUS : factor;
    }
}
