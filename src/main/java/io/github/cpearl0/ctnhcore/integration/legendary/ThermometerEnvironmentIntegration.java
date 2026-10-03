package io.github.cpearl0.ctnhcore.integration.legendary;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.capability.GTCapabilityHelper;
import com.gregtechceu.gtceu.config.ConfigHolder;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.ctnh.ctnhastral.common.environment.Atmosphere;
import com.ctnh.ctnhastral.common.environment.PlanetEnvironmentService;
import com.ctnh.ctnhastral.common.environment.Temperature;
import com.ctnh.ctnhastral.data.CAMedicalConditions;
import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import sfiomn.legendarysurvivaloverhaul.registry.ItemRegistry;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.Locale;
import java.util.TreeMap;

/** 复用温度计外观，普通读温和详细环境查询均消费 Astral 服务端快照。 */
@Mod.EventBusSubscriber(modid = CTNHCore.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class ThermometerEnvironmentIntegration {

    @Key("tooltip.ctnhcore.thermometer.environment")
    @CN("对空气右键测温；潜行右键查询大气成分与冷热状态")
    @EN("Right-click in the air for temperature; sneak for atmospheric composition and thermal status")
    private static Lang usage;

    @Key("tooltip.ctnhcore.thermometer.readings")
    @CN("读数显示在聊天栏，每秒可检测一次")
    @EN("Readings appear in chat; one scan per second")
    private static Lang readings;

    @Key("message.ctnhcore.thermometer.header")
    @CN("环境检测读数")
    @EN("Environment readings")
    private static Lang header;

    @Key("message.ctnhcore.thermometer.location")
    @CN("位置：%1$s [%2$s, %3$s, %4$s]")
    @EN("Location: %1$s [%2$s, %3$s, %4$s]")
    private static Lang location;

    @Key("message.ctnhcore.thermometer.temperature")
    @CN("环境温度：%s ℃")
    @EN("Ambient temperature: %s °C")
    private static Lang temperature;

    @Key("message.ctnhcore.thermometer.thermal_status")
    @CN("玩家冷热累积：失温 %1$s%% / 热射病 %2$s%%")
    @EN("Player thermal exposure: hypothermia %1$s%% / hyperthermia %2$s%%")
    private static Lang thermalStatus;

    @Key("message.ctnhcore.thermometer.pressure")
    @CN("总压：%s atm")
    @EN("Total pressure: %s atm")
    private static Lang totalPressure;

    @Key("message.ctnhcore.thermometer.vacuum")
    @CN("真空：无气体")
    @EN("Vacuum: no gases")
    private static Lang vacuum;

    @Key("message.ctnhcore.thermometer.composition")
    @CN("大气成分（分压 / 占比）：")
    @EN("Atmospheric composition (partial pressure / share):")
    private static Lang composition;

    @Key("message.ctnhcore.thermometer.gas")
    @CN("  %1$s：%2$s atm / %3$s%%")
    @EN("  %1$s: %2$s atm / %3$s%%")
    private static Lang gasReading;

    @Key("message.ctnhcore.thermometer.unlisted")
    @CN("未描述气体")
    @EN("Unspecified gases")
    private static Lang unlisted;

    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        if (!event.getItemStack().is(ItemRegistry.THERMOMETER.get())) return;

        // 两侧均消费本次使用，避免继续处理另一只手；测量和冷却只由服务端执行。
        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.sidedSuccess(event.getLevel().isClientSide));
        if (event.getLevel() instanceof ServerLevel level &&
                !player.getCooldowns().isOnCooldown(ItemRegistry.THERMOMETER.get())) {
            if (player.isShiftKeyDown()) {
                report(level, player);
            } else {
                float celsius = PlanetEnvironmentService.getEnvironment(level, player.blockPosition())
                        .get(Temperature.TYPE).celsius();
                player.displayClientMessage(temperature.translate(String.format(Locale.ROOT, "%.1f", celsius)), true);
            }
            player.getCooldowns().addCooldown(ItemRegistry.THERMOMETER.get(), 20);
        }
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        if (!event.getItemStack().is(ItemRegistry.THERMOMETER.get())) return;
        event.getToolTip().add(usage.translate().withStyle(ChatFormatting.GRAY));
        event.getToolTip().add(readings.translate().withStyle(ChatFormatting.DARK_GRAY));
    }

    private static void report(ServerLevel level, Player player) {
        var pos = player.blockPosition();
        var environment = PlanetEnvironmentService.getEnvironment(level, pos);
        Atmosphere atmosphere = environment.get(Atmosphere.TYPE);
        MutableComponent message = header.translate().withStyle(ChatFormatting.AQUA);
        line(message, location, level.dimension().location().toString(), pos.getX(), pos.getY(), pos.getZ());
        line(message, temperature, String.format(Locale.ROOT, "%.1f", environment.get(Temperature.TYPE).celsius()));
        var tracker = GTCapabilityHelper.getMedicalConditionTracker(player);
        if (tracker != null && ConfigHolder.INSTANCE.gameplay.hazardsEnabled) {
            var conditions = tracker.getMedicalConditions();
            line(message, thermalStatus,
                    String.format(Locale.ROOT, "%.1f", Math.max(0.0F,
                            conditions.getFloat(CAMedicalConditions.HYPOTHERMIA)) * 100.0F /
                            CAMedicalConditions.HYPOTHERMIA.maxProgression),
                    String.format(Locale.ROOT, "%.1f", Math.max(0.0F,
                            conditions.getFloat(CAMedicalConditions.HYPERTHERMIA)) * 100.0F /
                            CAMedicalConditions.HYPERTHERMIA.maxProgression));
        }
        float pressure = atmosphere.pressure();
        line(message, totalPressure, number(pressure));
        if (pressure == 0.0F) {
            line(message, vacuum);
        } else {
            line(message, composition);
            new TreeMap<>(atmosphere.partialPressures()).forEach((gas, partialPressure) -> {
                Component name = Component.translatableWithFallback(
                        "gas." + gas.getNamespace() + "." + gas.getPath().replace('/', '.'), gas.toString());
                line(message, gasReading, name, number(partialPressure), number(100.0 * partialPressure / pressure));
            });
            double described = atmosphere.partialPressures().values().stream().mapToDouble(Float::doubleValue).sum();
            double remainder = pressure - described;
            // 忽略浮点归一化的一位舍入误差，不把已描述完整的成分报为额外背景。
            if (remainder > Math.ulp(pressure)) {
                line(message, gasReading, unlisted.translate(), number(remainder),
                        number(100.0 * remainder / pressure));
            }
        }
        player.sendSystemMessage(message);
    }

    private static void line(MutableComponent message, Lang key, Object... args) {
        message.append("\n").append(key.translate(args).withStyle(ChatFormatting.GRAY));
    }

    private static String number(double value) {
        return String.format(Locale.ROOT, "%.4g", value);
    }

    private ThermometerEnvironmentIntegration() {}
}
