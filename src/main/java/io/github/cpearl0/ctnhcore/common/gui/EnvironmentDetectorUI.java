package io.github.cpearl0.ctnhcore.common.gui;

import com.gregtechceu.gtceu.api.capability.GTCapabilityHelper;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.config.ConfigHolder;

import com.lowdragmc.lowdraglib.gui.factory.HeldItemUIFactory;
import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.texture.ColorRectTexture;
import com.lowdragmc.lowdraglib.gui.widget.ButtonWidget;
import com.lowdragmc.lowdraglib.gui.widget.ComponentPanelWidget;
import com.lowdragmc.lowdraglib.gui.widget.DraggableScrollableWidgetGroup;
import com.lowdragmc.lowdraglib.gui.widget.ImageWidget;
import com.lowdragmc.lowdraglib.gui.widget.ProgressWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import com.ctnh.ctnhastral.common.environment.Atmosphere;
import com.ctnh.ctnhastral.common.environment.PlanetEnvironmentService;
import com.ctnh.ctnhastral.data.CAMedicalConditions;
import com.ctnh.ctnhastral.registry.CAEnvironments;
import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

/** 环境检测器的布局、读数格式与同步；环境和医疗状态均读取既有服务。 */
public final class EnvironmentDetectorUI {

    private static final int TEXT_COLOR = 0xE8E8E8;
    private static final int MUTED_COLOR = 0xC5C5C5;
    private static final int FRAME_TEXT_COLOR = 0x404040;
    // 颜色区分读数类别，不代表玩家防护后的危险等级。
    private static final int TEMPERATURE_COLOR = 0xFFC66D;
    private static final int PRESSURE_COLOR = 0x6DD5ED;
    private static final int RADIATION_COLOR = 0xC8A2FF;
    private static final int CORROSION_COLOR = 0xA9D879;
    private static final int COLD_COLOR = 0x77BFFF;
    private static final int HEAT_COLOR = 0xFF997D;

    @Key("gui.ctnhcore.environment_detector.close")
    @CN("关闭")
    @EN("Close")
    private static Lang close;

    @Key("message.ctnhcore.thermometer.header")
    @CN("环境检测器")
    @EN("Environment Detector")
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
    @EN("Temperature")
    private static Lang temperature;

    @Key("message.ctnhcore.thermometer.thermal_status")
    @CN("玩家状态")
    @EN("Player status")
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
    @EN("Pressure")
    private static Lang totalPressure;

    @Key("gui.ctnhcore.environment_detector.radiation")
    @CN("辐射剂量率")
    @EN("Radiation")
    private static Lang radiation;

    @Key("gui.ctnhcore.environment_detector.corrosion")
    @CN("腐蚀速率")
    @EN("Corrosion")
    private static Lang corrosion;

    @Key("message.ctnhcore.thermometer.vacuum")
    @CN("真空：无气体")
    @EN("Vacuum: no gases")
    private static Lang vacuum;

    @Key("message.ctnhcore.thermometer.composition")
    @CN("大气成分")
    @EN("Atmosphere")
    private static Lang composition;

    @Key("gui.ctnhcore.environment_detector.partial_pressure")
    @CN("分压 (atm)")
    @EN("Pressure (atm)")
    private static Lang partialPressure;

    @Key("gui.ctnhcore.environment_detector.share")
    @CN("占比")
    @EN("Share")
    private static Lang share;

    @Key("gui.ctnhcore.environment_detector.refresh")
    @CN("每秒刷新")
    @EN("Updates every second")
    private static Lang refresh;

    @Key("gui.ctnhcore.environment_detector.close_hint")
    @CN("Esc 关闭")
    @EN("Esc to close")
    private static Lang closeHint;

    @Key("message.ctnhcore.thermometer.unlisted")
    @CN("未描述气体")
    @EN("Unspecified gases")
    private static Lang unlisted;

    public static ModularUI createUI(Player player, HeldItemUIFactory.HeldItemHolder holder) {
        var data = new PanelReadings();
        updateReadings(player, data);
        var content = new WidgetGroup(0, 0, 300, 232) {

            @Override
            public void detectAndSendChanges() {
                if (gui.getTickCount() % 20 == 0) {
                    updateReadings(player, data);
                    super.detectAndSendChanges();
                }
            }
        };
        content.addWidget(new EnvironmentReadingsWidget(12, 10, 252, 9, Layout.TEXT,
                text -> text.add(frameText(header))));
        content.addWidget(inset(10, 29, 280, 28));
        content.addWidget(new EnvironmentReadingsWidget(18, 32, 264, 12, Layout.TEXT,
                text -> text.addAll(data.location)));
        content.addWidget(section(63, environmentConditions));
        content.addWidget(inset(10, 76, 280, 38));
        content.addWidget(rect(149, 78, 1, 34, 0xFF505050));
        content.addWidget(rect(12, 94, 276, 1, 0xFF414141));
        content.addWidget(reading(18, 80, temperature, () -> data.temperature));
        content.addWidget(reading(158, 80, totalPressure, () -> data.pressure));
        content.addWidget(reading(18, 99, radiation, () -> data.radiation));
        content.addWidget(reading(158, 99, corrosion, () -> data.corrosion));
        content.addWidget(section(120, thermalStatus));
        content.addWidget(inset(10, 132, 280, 26));
        content.addWidget(rect(149, 134, 1, 22, 0xFF505050));
        content.addWidget(reading(18, 134, hypothermia, () -> data.coldValue));
        content.addWidget(reading(158, 134, hyperthermia, () -> data.heatValue));
        content.addWidget(rect(18, 149, 124, 5, 0xFF090909));
        content.addWidget(rect(158, 149, 124, 5, 0xFF090909));
        content.addWidget(new ProgressWidget(() -> Math.min(1.0, data.coldProgress), 19, 150, 122, 3)
                .setProgressTexture(new ColorRectTexture(0xFF464646), new ColorRectTexture(0xFF000000 | COLD_COLOR)));
        content.addWidget(new ProgressWidget(() -> Math.min(1.0, data.heatProgress), 159, 150, 122, 3)
                .setProgressTexture(new ColorRectTexture(0xFF464646), new ColorRectTexture(0xFF000000 | HEAT_COLOR)));
        content.addWidget(new EnvironmentReadingsWidget(18, 164, 260, 9, Layout.GAS_HEADER,
                text -> text.addAll(List.of(frameText(composition), frameText(partialPressure), frameText(share)))));
        content.addWidget(inset(10, 176, 280, 34));
        // 上下各留 1 像素；两行内容不顶满滚动区，缩回两行时也能正确重算范围。
        var gases = new EnvironmentReadingsWidget(8, 1, 260, 16, Layout.GASES,
                text -> text.addAll(data.gases));
        var scroll = new DraggableScrollableWidgetGroup(10, 176, 280, 34) {

            @Override
            public void computeMax() {
                int previousOffset = getScrollYOffset();
                super.computeMax();
                // LDLib 默认随内容高度变化调整位置；读数刷新只收紧边界，不跳到列表末尾。
                setScrollYOffset(Math.min(previousOffset, Math.max(0, getMaxHeight() - getSizeHeight())));
            }

            @Override
            public Widget getHoverElement(double mouseX, double mouseY) {
                return isMouseOverElement(mouseX, mouseY) ? super.getHoverElement(mouseX, mouseY) : null;
            }
        }.setYScrollBarWidth(4)
                .setYBarStyle(null, GuiTextures.BUTTON)
                .setDraggable(false);
        scroll.addWidget(gases);
        content.addWidget(scroll);
        content.addWidget(new EnvironmentReadingsWidget(12, 218, 276, 9, Layout.PAIR,
                text -> text.addAll(List.of(frameText(refresh), frameText(closeHint)))));

        return new ModularUI(300, 232, holder, player)
                .background(GuiTextures.BACKGROUND)
                .widget(content)
                .widget(new ButtonWidget(274, 6, 18, 18, click -> {
                    if (player instanceof ServerPlayer serverPlayer) {
                        serverPlayer.closeContainer();
                    }
                }).setButtonTexture(GuiTextures.BUTTON, GuiTextures.CLOSE_ICON)
                        .setHoverBorderTexture(1, 0xFFFFFFFF)
                        .setClickedTexture(GuiTextures.BACKGROUND_INVERSE, GuiTextures.CLOSE_ICON)
                        .setHoverTooltips(close.translate()));
    }

    private static Component frameText(Lang text) {
        return text.translate().withStyle(style -> style.withColor(FRAME_TEXT_COLOR));
    }

    private static EnvironmentReadingsWidget section(int y, Lang title) {
        return new EnvironmentReadingsWidget(12, y, 276, 9, Layout.SECTION, text -> text.add(frameText(title)));
    }

    private static EnvironmentReadingsWidget reading(int x, int y, Lang title, Supplier<Component> value) {
        return new EnvironmentReadingsWidget(x, y, 124, 13, Layout.PAIR,
                text -> text.addAll(
                        List.of(title.translate().withStyle(style -> style.withColor(TEXT_COLOR)), value.get())));
    }

    private static ImageWidget rect(int x, int y, int width, int height, int color) {
        return new ImageWidget(x, y, width, height, new ColorRectTexture(color));
    }

    private static WidgetGroup inset(int x, int y, int width, int height) {
        return new WidgetGroup(x, y, width, height)
                .addWidget(rect(0, 0, width, height, 0xFFF0F0F0))
                .addWidget(rect(0, 0, width - 1, height - 1, 0xFF555555))
                .addWidget(rect(1, 1, width - 2, height - 2, 0xFF242424));
    }

    private static void updateReadings(Player player, PanelReadings data) {
        // 客户端只展示同步后的文本，不自行计算环境或读取服务端医疗状态。
        if (!(player.level() instanceof ServerLevel level)) return;
        var pos = player.blockPosition();
        var environment = PlanetEnvironmentService.getEnvironment(level, pos);
        Atmosphere atmosphere = environment.get(CAEnvironments.ATMOSPHERE);
        data.location = List.of(
                location.translate(value(level.dimension().location().toString(), TEXT_COLOR))
                        .withStyle(style -> style.withColor(MUTED_COLOR)),
                coordinates.translate(value(Integer.toString(pos.getX()), TEXT_COLOR),
                        value(Integer.toString(pos.getY()), TEXT_COLOR),
                        value(Integer.toString(pos.getZ()), TEXT_COLOR))
                        .withStyle(style -> style.withColor(MUTED_COLOR)));
        data.temperature = quantity(decimal(environment.get(CAEnvironments.TEMPERATURE).celsius()),
                "°C", TEMPERATURE_COLOR);
        data.pressure = quantity(number(atmosphere.pressure()), "atm", PRESSURE_COLOR);
        data.radiation = quantity(number(environment.get(CAEnvironments.RADIATION).rate()), "rad/s", RADIATION_COLOR);
        data.corrosion = quantity(number(environment.get(CAEnvironments.CORROSION).rate()), "mm/a", CORROSION_COLOR);

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
            data.coldValue = quantity(decimal(data.coldProgress * 100.0), "%", COLD_COLOR);
            data.heatValue = quantity(decimal(data.heatProgress * 100.0), "%", HEAT_COLOR);
        }

        data.gases = new ArrayList<>();
        float pressure = atmosphere.pressure();
        if (pressure == 0.0F) {
            data.gases.add(vacuum.translate().withStyle(ChatFormatting.GOLD));
            data.gases.add(Component.empty());
            data.gases.add(Component.empty());
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
        text.add(name.copy().withStyle(style -> style.withColor(TEXT_COLOR)));
        text.add(value(number(partialPressure), PRESSURE_COLOR));
        text.add(value(String.format(Locale.ROOT, "%.1f%%", share), TEMPERATURE_COLOR));
    }

    private static MutableComponent value(String value, int color) {
        return Component.literal(value).withStyle(style -> style.withColor(color));
    }

    private static Component quantity(String value, String unit, int color) {
        return value(value, color)
                .append(Component.literal(" " + unit).withStyle(style -> style.withColor(MUTED_COLOR).withBold(false)));
    }

    private static String number(double value) {
        return String.format(Locale.ROOT, "%.4g", value);
    }

    private static String decimal(double value) {
        return Math.abs(value) < 100000.0 ? String.format(Locale.ROOT, "%.1f", value) : number(value);
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

    private enum Layout {

        TEXT(1),
        PAIR(2),
        GAS_HEADER(3),
        GASES(3),
        SECTION(1);

        private final int columns;

        Layout(int columns) {
            this.columns = columns;
        }
    }

    /** 固定行高的只读表格，复用 ComponentPanelWidget 的服务端文本同步。 */
    private static final class EnvironmentReadingsWidget extends ComponentPanelWidget {

        private final int tableWidth;
        private final int rowHeight;
        private final Layout layout;
        private final Set<Integer> clippedRows = new HashSet<>();

        private EnvironmentReadingsWidget(int x, int y, int width, int rowHeight, Layout layout,
                                          Consumer<List<Component>> textSupplier) {
            super(x, y, textSupplier);
            this.tableWidth = width;
            this.rowHeight = rowHeight;
            this.layout = layout;
            updateComponentTextSize();
        }

        @Override
        public void formatDisplayText() {
            // 单元格在绘制时按 Font 度量，不能让父类的自动换行改变固定布局。
        }

        @Override
        public void updateComponentTextSize() {
            setSize(tableWidth, Math.max(1, (lastText.size() + layout.columns - 1) / layout.columns) * rowHeight);
        }

        @Override
        @OnlyIn(Dist.CLIENT)
        public void drawInBackground(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            clippedRows.clear();
            Font font = Minecraft.getInstance().font;
            int x = getPositionX();
            for (int index = 0; index < lastText.size(); index += layout.columns) {
                int row = index / layout.columns;
                int top = getPositionY() + row * rowHeight;
                int y = top + Math.max(0, (rowHeight - font.lineHeight + 1) / 2);
                Component label = lastText.get(index);
                boolean gasColumns = (layout == Layout.GASES || layout == Layout.GAS_HEADER) &&
                        index + 2 < lastText.size() && !lastText.get(index + 1).getString().isEmpty();
                boolean clipped;
                if (layout == Layout.PAIR && index + 1 < lastText.size()) {
                    Component value = lastText.get(index + 1);
                    int valueWidth = Math.min(tableWidth - 32, font.width(value));
                    clipped = drawCell(graphics, label, x, y, tableWidth - valueWidth - 8, false);
                    clipped |= drawCell(graphics, value, x + tableWidth - valueWidth, y, valueWidth, true);
                } else if (gasColumns) {
                    // 名称、分压、占比列：表头与数据使用同一组右边界。
                    clipped = drawCell(graphics, label, x, y, tableWidth - 148, false);
                    clipped |= drawCell(graphics, lastText.get(index + 1), x + tableWidth - 140, y, 80, true);
                    clipped |= drawCell(graphics, lastText.get(index + 2), x + tableWidth - 52, y, 52, true);
                } else {
                    clipped = drawCell(graphics, label, x, y, tableWidth, false);
                }
                if (clipped) clippedRows.add(row);
                if (layout == Layout.SECTION) {
                    int start = x + Math.min(tableWidth, font.width(label) + 8);
                    graphics.fill(start, y + 4, x + tableWidth, y + 5, 0xFF8B8B8B);
                    graphics.fill(start, y + 5, x + tableWidth, y + 6, 0xFFEDEDED);
                } else if (layout == Layout.GASES && row > 0) {
                    graphics.fill(x, top - 2, x + tableWidth, top - 1, 0xFF414141);
                }
            }
        }

        @OnlyIn(Dist.CLIENT)
        private static boolean drawCell(GuiGraphics graphics, Component text, int x, int y, int width, boolean right) {
            Font font = Minecraft.getInstance().font;
            boolean clipped = font.width(text) > width;
            FormattedText shown = text;
            if (clipped) {
                Component ellipsis = Component.literal("…").withStyle(text.getStyle());
                shown = FormattedText.composite(font.substrByWidth(text, Math.max(0, width - font.width(ellipsis))),
                        ellipsis);
            }
            graphics.drawString(font, Language.getInstance().getVisualOrder(shown),
                    right ? x + width - font.width(shown) : x, y, 0xFFE8E8E8, false);
            return clipped;
        }

        @Override
        @OnlyIn(Dist.CLIENT)
        public void drawInForeground(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            if (!isMouseOverElement(mouseX, mouseY)) return;
            int row = (mouseY - getPositionY()) / rowHeight;
            int index = row * layout.columns;
            if (index >= lastText.size() || !clippedRows.contains(row)) return;
            List<Component> tooltip = new ArrayList<>();
            tooltip.add(lastText.get(index));
            if ((layout == Layout.GASES || layout == Layout.GAS_HEADER) && index + 2 < lastText.size()) {
                Component pressure = lastText.get(index + 1);
                if (!pressure.getString().isEmpty()) {
                    tooltip.add(layout == Layout.GASES ? pressure.copy().append(" atm") : pressure);
                }
                if (!lastText.get(index + 2).getString().isEmpty()) {
                    tooltip.add(lastText.get(index + 2));
                }
            } else if (layout == Layout.PAIR && index + 1 < lastText.size()) {
                tooltip.add(lastText.get(index + 1));
            }
            setHoverTooltips(tooltip);
            // 由 ModularUI 最后统一画 tooltip，避免被气体列表的裁切区域截掉。
            drawTooltipTexts(mouseX, mouseY);
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            return false;
        }
    }

    private EnvironmentDetectorUI() {}
}
