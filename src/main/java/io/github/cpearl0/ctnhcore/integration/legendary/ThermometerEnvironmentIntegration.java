package io.github.cpearl0.ctnhcore.integration.legendary;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.capability.GTCapabilityHelper;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.config.ConfigHolder;

import com.lowdragmc.lowdraglib.gui.editor.ColorPattern;
import com.lowdragmc.lowdraglib.gui.factory.HeldItemUIFactory;
import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.texture.ColorRectTexture;
import com.lowdragmc.lowdraglib.gui.widget.ButtonWidget;
import com.lowdragmc.lowdraglib.gui.widget.ComponentPanelWidget;
import com.lowdragmc.lowdraglib.gui.widget.DraggableScrollableWidgetGroup;
import com.lowdragmc.lowdraglib.gui.widget.ImageWidget;
import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.ProgressWidget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.gui.widget.layout.Layout;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.ctnh.ctnhastral.common.environment.Atmosphere;
import com.ctnh.ctnhastral.common.environment.Corrosion;
import com.ctnh.ctnhastral.common.environment.PlanetEnvironmentService;
import com.ctnh.ctnhastral.common.environment.Radiation;
import com.ctnh.ctnhastral.common.environment.Temperature;
import com.ctnh.ctnhastral.data.CAMedicalConditions;
import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import sfiomn.legendarysurvivaloverhaul.registry.ItemRegistry;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

/** 复用温度计外观，在物品面板中显示并同步 Astral 服务端环境读数。 */
@Mod.EventBusSubscriber(modid = CTNHCore.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class ThermometerEnvironmentIntegration {

    private static final int TEXT_COLOR = 0xE4EDF2;
    private static final int MUTED_COLOR = 0x9FAFB8;
    // 颜色区分读数类别，不代表玩家防护后的危险等级。
    private static final int TEMPERATURE_COLOR = 0xFFC66D;
    private static final int PRESSURE_COLOR = 0x6DD5ED;
    private static final int RADIATION_COLOR = 0xC8A2FF;
    private static final int CORROSION_COLOR = 0xA9D879;
    private static final int COLD_COLOR = 0x77BFFF;
    private static final int HEAT_COLOR = 0xFF997D;

    @Key("tooltip.ctnhcore.thermometer.environment")
    @CN("对空气右键打开环境检测面板")
    @EN("Right-click in the air to open the environment panel")
    private static Lang usage;

    @Key("tooltip.ctnhcore.thermometer.readings")
    @CN("每秒刷新读数，按 Esc 关闭")
    @EN("Updates every second; press Esc to close")
    private static Lang readings;

    @Key("gui.ctnhcore.environment_detector.close")
    @CN("关闭")
    @EN("Close")
    private static Lang close;

    @Key("message.ctnhcore.thermometer.header")
    @CN("环境检测读数")
    @EN("Environment readings")
    private static Lang header;

    @Key("message.ctnhcore.thermometer.location")
    @CN("维度  %s")
    @EN("Dimension  %s")
    private static Lang location;

    @Key("gui.ctnhcore.environment_detector.coordinates")
    @CN("坐标  X %1$s  ·  Y %2$s  ·  Z %3$s")
    @EN("Position  X %1$s  ·  Y %2$s  ·  Z %3$s")
    private static Lang coordinates;

    @Key("gui.ctnhcore.environment_detector.conditions")
    @CN("环境条件")
    @EN("Environment conditions")
    private static Lang environmentConditions;

    @Key("message.ctnhcore.thermometer.temperature")
    @CN("环境温度")
    @EN("Ambient temperature")
    private static Lang temperature;

    @Key("message.ctnhcore.thermometer.thermal_status")
    @CN("玩家冷热累积")
    @EN("Player thermal exposure")
    private static Lang thermalStatus;

    @Key("gui.ctnhcore.environment_detector.hypothermia")
    @CN("失温累积")
    @EN("Hypothermia")
    private static Lang hypothermia;

    @Key("gui.ctnhcore.environment_detector.hyperthermia")
    @CN("热射病累积")
    @EN("Hyperthermia")
    private static Lang hyperthermia;

    @Key("gui.ctnhcore.environment_detector.no_thermal_data")
    @CN("暂无读数")
    @EN("Unavailable")
    private static Lang noThermalData;

    @Key("message.ctnhcore.thermometer.pressure")
    @CN("总气压")
    @EN("Total pressure")
    private static Lang totalPressure;

    @Key("gui.ctnhcore.environment_detector.radiation")
    @CN("辐射剂量率")
    @EN("Radiation dose rate")
    private static Lang radiation;

    @Key("gui.ctnhcore.environment_detector.corrosion")
    @CN("腐蚀速率")
    @EN("Corrosion rate")
    private static Lang corrosion;

    @Key("message.ctnhcore.thermometer.vacuum")
    @CN("真空：无气体")
    @EN("Vacuum: no gases")
    private static Lang vacuum;

    @Key("message.ctnhcore.thermometer.composition")
    @CN("大气成分")
    @EN("Atmospheric composition")
    private static Lang composition;

    @Key("message.ctnhcore.thermometer.gas")
    @CN("分压 %1$s  ·  占比 %2$s")
    @EN("Pressure %1$s  ·  Share %2$s")
    private static Lang gasReading;

    @Key("message.ctnhcore.thermometer.unlisted")
    @CN("未描述气体")
    @EN("Unspecified gases")
    private static Lang unlisted;

    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        if (!event.getItemStack().is(ItemRegistry.THERMOMETER.get())) return;

        // 两侧均消费本次使用，避免继续处理另一只手；由服务端打开并同步面板。
        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.sidedSuccess(event.getLevel().isClientSide));
        if (player instanceof ServerPlayer serverPlayer &&
                !player.getCooldowns().isOnCooldown(ItemRegistry.THERMOMETER.get())) {
            HeldItemUIFactory.INSTANCE.openUI(serverPlayer, event.getHand());
            player.getCooldowns().addCooldown(ItemRegistry.THERMOMETER.get(), 20);
        }
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        if (!event.getItemStack().is(ItemRegistry.THERMOMETER.get())) return;
        event.getToolTip().add(usage.translate().withStyle(ChatFormatting.GRAY));
        event.getToolTip().add(readings.translate().withStyle(ChatFormatting.DARK_GRAY));
    }

    public static ModularUI createUI(Player player, HeldItemUIFactory.HeldItemHolder holder) {
        var data = new PanelReadings();
        updateReadings(player, data);
        var content = new WidgetGroup(8, 0, 264, 0) {

            @Override
            public void detectAndSendChanges() {
                if (gui.getTickCount() % 20 == 0) {
                    updateReadings(player, data);
                    super.detectAndSendChanges();
                }
            }
        };
        content.setLayout(Layout.VERTICAL_LEFT);
        content.setLayoutPadding(6);
        content.setDynamicSized(true);
        content.addWidget(new ComponentPanelWidget(0, 0, text -> text.addAll(data.location))
                .setMaxWidthLimit(264).setSpace(3));
        content.addWidget(section(environmentConditions));
        content.addWidget(new WidgetGroup(0, 0, 264, 32)
                .addWidget(readingCard(0, temperature, TEMPERATURE_COLOR, () -> data.temperature))
                .addWidget(readingCard(136, totalPressure, PRESSURE_COLOR, () -> data.pressure)));
        content.addWidget(new WidgetGroup(0, 0, 264, 32)
                .addWidget(readingCard(0, radiation, RADIATION_COLOR, () -> data.radiation))
                .addWidget(readingCard(136, corrosion, CORROSION_COLOR, () -> data.corrosion)));
        content.addWidget(section(thermalStatus));
        content.addWidget(new WidgetGroup(0, 0, 264, 38)
                .addWidget(thermalCard(0, hypothermia, COLD_COLOR, () -> data.coldValue, () -> data.coldProgress))
                .addWidget(thermalCard(136, hyperthermia, HEAT_COLOR, () -> data.heatValue, () -> data.heatProgress)));
        content.addWidget(section(composition));
        content.addWidget(new ComponentPanelWidget(0, 0, text -> text.addAll(data.gases))
                .setMaxWidthLimit(264).setSpace(3));

        var scroll = new DraggableScrollableWidgetGroup(8, 30, 284, 180)
                .setBackground(GuiTextures.DISPLAY)
                .setYScrollBarWidth(4)
                .setYBarStyle(null, ColorPattern.T_WHITE.rectTexture().setRadius(2));
        scroll.addWidget(content);

        return new ModularUI(300, 238, holder, player)
                .background(GuiTextures.BACKGROUND)
                .widget(new LabelWidget(12, 12, header.translate()).setTextColor(0xFF404040).setDropShadow(false))
                .widget(scroll)
                .widget(new LabelWidget(12, 221, readings.translate()).setTextColor(0xFF404040).setDropShadow(false))
                .widget(new ButtonWidget(270, 7, 18, 18, click -> {
                    if (player instanceof ServerPlayer serverPlayer) {
                        serverPlayer.closeContainer();
                    }
                }).setButtonTexture(GuiTextures.BUTTON, GuiTextures.CLOSE_ICON)
                        .setHoverTooltips(close.translate()));
    }

    private static WidgetGroup section(Lang title) {
        return new WidgetGroup(0, 0, 264, 14)
                .addWidget(new LabelWidget(0, 0, title.translate().withStyle(ChatFormatting.BOLD))
                        .setTextColor(0xFF000000 | TEXT_COLOR).setDropShadow(false))
                .addWidget(new ImageWidget(0, 12, 264, 1, new ColorRectTexture(0xFF42545F)));
    }

    private static WidgetGroup readingCard(int x, Lang title, int color, Supplier<Component> value) {
        var card = new WidgetGroup(x, 0, 128, 32);
        card.setBackground(new ColorRectTexture(0xFF20313B));
        card.addWidget(new ImageWidget(0, 0, 2, 32, new ColorRectTexture(0xFF000000 | color)));
        card.addWidget(new LabelWidget(8, 5, title.translate())
                .setTextColor(0xFF000000 | MUTED_COLOR).setDropShadow(false));
        card.addWidget(new ComponentPanelWidget(8, 18, text -> text.add(value.get())).setMaxWidthLimit(112));
        return card;
    }

    private static WidgetGroup thermalCard(int x, Lang title, int color, Supplier<Component> value,
                                           DoubleSupplier progress) {
        var card = readingCard(x, title, color, value);
        card.setSizeHeight(38);
        card.addWidget(new ProgressWidget(() -> Math.min(1.0, progress.getAsDouble()), 8, 31, 112, 3)
                .setProgressTexture(new ColorRectTexture(0xFF101C24), new ColorRectTexture(0xFF000000 | color)));
        return card;
    }

    private static void updateReadings(Player player, PanelReadings data) {
        // 客户端只展示同步后的文本，不自行计算环境或读取服务端医疗状态。
        if (!(player.level() instanceof ServerLevel level)) return;
        var pos = player.blockPosition();
        var environment = PlanetEnvironmentService.getEnvironment(level, pos);
        Atmosphere atmosphere = environment.get(Atmosphere.TYPE);
        data.location = List.of(
                location.translate(value(level.dimension().location().toString(), TEXT_COLOR))
                        .withStyle(style -> style.withColor(MUTED_COLOR)),
                coordinates.translate(value(Integer.toString(pos.getX()), TEXT_COLOR),
                        value(Integer.toString(pos.getY()), TEXT_COLOR),
                        value(Integer.toString(pos.getZ()), TEXT_COLOR))
                        .withStyle(style -> style.withColor(MUTED_COLOR)));
        data.temperature = quantity(String.format(Locale.ROOT, "%.1f", environment.get(Temperature.TYPE).celsius()),
                "°C", TEMPERATURE_COLOR);
        data.pressure = quantity(number(atmosphere.pressure()), "atm", PRESSURE_COLOR);
        data.radiation = quantity(number(environment.get(Radiation.TYPE).rate()), "rad/s", RADIATION_COLOR);
        data.corrosion = quantity(number(environment.get(Corrosion.TYPE).rate()), "mm/a", CORROSION_COLOR);

        data.coldProgress = 0.0;
        data.heatProgress = 0.0;
        data.coldValue = noThermalData.translate().withStyle(style -> style.withColor(MUTED_COLOR));
        data.heatValue = data.coldValue;
        var tracker = GTCapabilityHelper.getMedicalConditionTracker(player);
        if (tracker != null && ConfigHolder.INSTANCE.gameplay.hazardsEnabled) {
            var conditions = tracker.getMedicalConditions();
            data.coldProgress = Math.max(0.0F, conditions.getFloat(CAMedicalConditions.HYPOTHERMIA)) /
                    CAMedicalConditions.HYPOTHERMIA.maxProgression;
            data.heatProgress = Math.max(0.0F, conditions.getFloat(CAMedicalConditions.HYPERTHERMIA)) /
                    CAMedicalConditions.HYPERTHERMIA.maxProgression;
            data.coldValue = quantity(String.format(Locale.ROOT, "%.1f", data.coldProgress * 100.0), "%", COLD_COLOR);
            data.heatValue = quantity(String.format(Locale.ROOT, "%.1f", data.heatProgress * 100.0), "%", HEAT_COLOR);
        }

        data.gases = new ArrayList<>();
        float pressure = atmosphere.pressure();
        if (pressure == 0.0F) {
            data.gases.add(vacuum.translate().withStyle(ChatFormatting.GOLD));
        } else {
            new TreeMap<>(atmosphere.partialPressures()).forEach((gas, partialPressure) -> {
                Component name = Component.translatableWithFallback(
                        "gas." + gas.getNamespace() + "." + gas.getPath().replace('/', '.'), gas.toString());
                gasReadings(data.gases, name, partialPressure, 100.0 * partialPressure / pressure);
            });
            double described = atmosphere.partialPressures().values().stream().mapToDouble(Float::doubleValue).sum();
            double remainder = pressure - described;
            // 忽略浮点归一化的一位舍入误差，不把已描述完整的成分报为额外背景。
            if (remainder > Math.ulp(pressure)) {
                gasReadings(data.gases, unlisted.translate(), remainder, 100.0 * remainder / pressure);
            }
        }
    }

    private static void gasReadings(List<Component> text, Component name, double partialPressure, double share) {
        if (!text.isEmpty()) text.add(Component.empty());
        text.add(name.copy().withStyle(style -> style.withColor(TEXT_COLOR).withBold(true)));
        text.add(gasReading.translate(quantity(number(partialPressure), "atm", PRESSURE_COLOR),
                quantity(String.format(Locale.ROOT, "%.1f", share), "%", TEMPERATURE_COLOR))
                .withStyle(style -> style.withColor(MUTED_COLOR)));
    }

    private static MutableComponent value(String value, int color) {
        return Component.literal(value).withStyle(style -> style.withColor(color).withBold(true));
    }

    private static Component quantity(String value, String unit, int color) {
        return value(value, color)
                .append(Component.literal(" " + unit).withStyle(style -> style.withColor(MUTED_COLOR).withBold(false)));
    }

    private static String number(double value) {
        return String.format(Locale.ROOT, "%.4g", value);
    }

    /** 每个打开的面板各自持有显示值；所有读数在同一次服务端采样中更新。 */
    private static final class PanelReadings {

        private List<Component> location = List.of();
        private Component temperature = Component.empty();
        private Component pressure = Component.empty();
        private Component radiation = Component.empty();
        private Component corrosion = Component.empty();
        private Component coldValue = Component.empty();
        private Component heatValue = Component.empty();
        private double coldProgress;
        private double heatProgress;
        private List<Component> gases = List.of();
    }

    private ThermometerEnvironmentIntegration() {}
}
