package io.github.cpearl0.ctnhcore.mixin.legendarysurvival;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import sfiomn.legendarysurvivaloverhaul.client.events.ClientForgeEvents;

/** LSO 的体温视觉与声音不再更新；环境检测器仍按服务端快照显示。 */
@Mixin(value = ClientForgeEvents.class, remap = false)
public abstract class ClientTemperatureEffectsMixin {

    @Redirect(method = "onClientTick",
              at = @At(value = "FIELD",
                       target = "Lsfiomn/legendarysurvivaloverhaul/config/Config$Baked;temperatureEnabled:Z"),
              require = 1)
    private static boolean ctnh$disableLsoTemperatureEffects() {
        return false;
    }

    @Redirect(method = "onClientTick",
              at = @At(value = "FIELD",
                       target = "Lsfiomn/legendarysurvivaloverhaul/config/Config$Baked;wetnessEnabled:Z"),
              require = 1)
    private static boolean ctnh$disableLsoWetnessEffects() {
        return false;
    }
}
