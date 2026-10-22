package io.github.cpearl0.ctnhcore.integration.legendary;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.adventure.CTNHEnchantments;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.ctnh.ctnhastral.common.medical.PlayerThermalProtectionEvent;
import sfiomn.legendarysurvivaloverhaul.registry.AttributeRegistry;
import sfiomn.legendarysurvivaloverhaul.registry.MobEffectRegistry;

/** 外部装备和药水只提供防护数据，玩家温度状态和医疗判定归 Astral 所有。 */
@Mod.EventBusSubscriber(modid = CTNHCore.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class PlayerTemperatureIntegration {

    @SubscribeEvent
    public static void onThermalProtection(PlayerThermalProtectionEvent event) {
        Player player = event.getEntity();
        float thermal = (float) player.getAttributeValue(AttributeRegistry.THERMAL_RESISTANCE.get());
        event.addResistanceK(
                thermal + (float) player.getAttributeValue(AttributeRegistry.COLD_RESISTANCE.get()),
                thermal + (float) player.getAttributeValue(AttributeRegistry.HEAT_RESISTANCE.get()));
        event.addTemperatureOffsetK(
                (float) (player.getAttributeValue(AttributeRegistry.HEATING_TEMPERATURE.get()) +
                        player.getAttributeValue(AttributeRegistry.COOLING_TEMPERATURE.get())));

        int warming = 0, cooling = 0;
        for (var armor : player.getArmorSlots()) {
            warming += armor.getEnchantmentLevel(CTNHEnchantments.WARMING.get());
            cooling += armor.getEnchantmentLevel(CTNHEnchantments.COOLING.get());
        }
        // 每级抵消 5% 的对应暴露温差，全身合计 20 级完全防护。
        boolean immune = player.hasEffect(MobEffectRegistry.TEMPERATURE_IMMUNITY.get());
        event.addProtection(
                immune || player.hasEffect(MobEffectRegistry.COLD_IMMUNITY.get()) ? 1.0F : warming / 20.0F,
                immune || player.hasEffect(MobEffectRegistry.HEAT_IMMUNITY.get()) ? 1.0F : cooling / 20.0F);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onThermalEffect(MobEffectEvent.Applicable event) {
        if (event.getEntity() instanceof Player && isRetiredEffect(event.getEffectInstance().getEffect())) {
            event.setResult(Event.Result.DENY);
        }
    }

    /** 活动效果也不能绕过单一医疗判定，包括命令或存档中的效果。 */
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START || event.player.level().isClientSide) return;
        Player player = event.player;
        player.removeEffect(MobEffectRegistry.FROSTBITE.get());
        player.removeEffect(MobEffectRegistry.HEAT_STROKE.get());
        player.removeEffect(MobEffectRegistry.COLD_HUNGER.get());
        player.removeEffect(MobEffectRegistry.HEAT_THIRST.get());
    }

    private static boolean isRetiredEffect(MobEffect effect) {
        return effect == MobEffectRegistry.FROSTBITE.get() || effect == MobEffectRegistry.HEAT_STROKE.get() ||
                effect == MobEffectRegistry.COLD_HUNGER.get() || effect == MobEffectRegistry.HEAT_THIRST.get();
    }

    private PlayerTemperatureIntegration() {}
}
