package io.github.cpearl0.ctnhcore.mixin.legendarysurvival;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

import com.ctnh.ctnhastral.client.ClientEnvironmentTemperature;
import com.ctnh.ctnhastral.common.environment.PlanetEnvironmentService;
import com.ctnh.ctnhastral.registry.CAEnvironments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sfiomn.legendarysurvivaloverhaul.common.capabilities.temperature.TemperatureItemCapability;
import sfiomn.legendarysurvivaloverhaul.util.WorldUtil;

/** 温度计等待服务端快照；请求期间保留最后有效读数，不写入未同步的 NaN。 */
@Mixin(value = TemperatureItemCapability.class, remap = false)
public abstract class TemperatureItemCapabilityMixin {

    @Shadow
    private long updateTick;

    @Shadow
    public abstract void setWorldTemperatureLevel(float temperature);

    @Inject(method = "updateWorldTemperature", at = @At("HEAD"), cancellable = true, require = 1)
    private void ctnh$serverReading(Level level, Entity entity, long tick, CallbackInfo ci) {
        var pos = WorldUtil.getSidedBlockPos(level, entity);
        float temperature = level instanceof ServerLevel serverLevel ?
                PlanetEnvironmentService.getEnvironment(serverLevel, pos).get(CAEnvironments.TEMPERATURE).celsius() :
                DistExecutor.unsafeCallWhenOn(Dist.CLIENT,
                        () -> () -> ClientEnvironmentTemperature.celsius(level, pos));
        if (Float.isFinite(temperature)) {
            setWorldTemperatureLevel(temperature);
            updateTick = tick;
        }
        ci.cancel();
    }
}
