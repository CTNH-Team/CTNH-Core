package io.github.cpearl0.ctnhcore.integration.legendary;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import com.ctnh.ctnhastral.common.environment.BlockTemperature;
import com.ctnh.ctnhastral.common.environment.TemperatureClimate;
import com.teamtea.eclipticseasons.api.EclipticSeasonsApi;
import com.teamtea.eclipticseasons.api.constant.solar.SolarTerm;
import com.teamtea.eclipticseasons.api.util.EclipticUtil;
import sfiomn.legendarysurvivaloverhaul.registry.BlockRegistry;

/** 跨模组来源只在 Core 登记；LSO 不再计算环境温度。 */
public final class EnvironmentTemperatureIntegration {

    // LSO 1.20.1-2.3.20.1 的二十四节气默认偏移，独立于其运行时配置。
    private static final float[] SEASON_OFFSETS = {
            -10, -7, -5, -3, -1, 0, 1, 3, 5, 7, 9, 10,
            9, 7, 5, 3, 1, 0, -1, -3, -5, -7, -10, -12
    };

    private EnvironmentTemperatureIntegration() {}

    public static void init() {
        TemperatureClimate.register(Level.OVERWORLD, EnvironmentTemperatureIntegration::overworldClimate);
        BlockTemperature.register(BlockRegistry.HEATER.get(),
                state -> state.getValue(BlockStateProperties.LIT) ? 15.0F : 0.0F);
        BlockTemperature.register(BlockRegistry.COOLER.get(),
                state -> state.getValue(BlockStateProperties.LIT) ? -15.0F : 0.0F);
        BlockTemperature.register(BlockRegistry.SUN_FERN_CROP.get(),
                state -> state.getValue(BlockStateProperties.AGE_3) == 3 ? 1.5F : 0.0F);
        BlockTemperature.register(BlockRegistry.SUN_FERN_GOLD.get(),
                state -> state.getValue(BlockStateProperties.AGE_3) == 3 ? 1.5F : 0.0F);
        BlockTemperature.register(BlockRegistry.ICE_FERN_CROP.get(),
                state -> state.getValue(BlockStateProperties.AGE_3) == 3 ? -1.5F : 0.0F);
        BlockTemperature.register(BlockRegistry.ICE_FERN_GOLD.get(),
                state -> state.getValue(BlockStateProperties.AGE_3) == 3 ? -1.5F : 0.0F);
    }

    private static TemperatureClimate overworldClimate(ServerLevel level, BlockPos pos) {
        var vanilla = TemperatureClimate.vanilla(level, pos);
        var seasons = EclipticSeasonsApi.getInstance();
        var precipitation = seasons.hasLocalWeather(level) ? seasons.getCurrentPrecipitationAt(level, pos) :
                vanilla.precipitation();
        SolarTerm term = seasons.getSolarTerm(level);
        if (!seasons.isSeasonEnabled(level) || term == SolarTerm.NONE) {
            return new TemperatureClimate(0.0F, 12000, precipitation);
        }
        double day = Math.floorMod(level.getDayTime(), 24000L) / 24000.0;
        float progress = Mth.clamp((float) ((EclipticUtil.getTimeInSolarTerm(level) + day) /
                seasons.getLastingDaysOfEachTerm(level)), 0.0F, 1.0F);
        int current = term.ordinal();
        int from = progress < 0.5F ? Math.floorMod(current - 1, 24) : current;
        int to = progress < 0.5F ? current : (current + 1) % 24;
        float blend = progress < 0.5F ? progress + 0.5F : progress - 0.5F;
        float offset = Mth.lerp(blend, SEASON_OFFSETS[from], SEASON_OFFSETS[to]);
        return new TemperatureClimate(offset, term.getDayTime(), precipitation);
    }
}
