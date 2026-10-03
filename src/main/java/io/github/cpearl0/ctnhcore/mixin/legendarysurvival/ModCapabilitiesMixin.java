package io.github.cpearl0.ctnhcore.mixin.legendarysurvival;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import sfiomn.legendarysurvivaloverhaul.common.capabilities.ModCapabilities;

/** 停用 LSO 玩家体温、湿度的更新与同步；其装备数据、口渴和肢体健康仍独立工作。 */
@Mixin(value = ModCapabilities.class, remap = false)
public abstract class ModCapabilitiesMixin {

    @Redirect(method = { "onPlayerTick", "deathHandler", "syncCapsOnDimensionChange", "syncCapsOnLogin" },
              at = @At(value = "FIELD",
                       target = "Lsfiomn/legendarysurvivaloverhaul/config/Config$Baked;temperatureEnabled:Z"),
              require = 6)
    private static boolean ctnh$astralPlayerTemperature() {
        return false;
    }

    @Redirect(method = { "onPlayerTick", "deathHandler", "syncCapsOnDimensionChange", "syncCapsOnLogin" },
              at = @At(value = "FIELD",
                       target = "Lsfiomn/legendarysurvivaloverhaul/config/Config$Baked;wetnessEnabled:Z"),
              require = 4)
    private static boolean ctnh$disableLsoWetness() {
        return false;
    }
}
