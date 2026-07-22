package io.github.cpearl0.ctnhcore.common.machine.multiblock.part;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.widget.ToggleButtonWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredPartMachine;
import com.gregtechceu.gtceu.utils.RedstoneUtil;

import com.lowdragmc.lowdraglib.gui.editor.ColorPattern;
import com.lowdragmc.lowdraglib.gui.widget.*;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.utils.Position;
import com.lowdragmc.lowdraglib.utils.Size;

import net.minecraft.core.Direction;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import com.ctnhlang.Prefix;
import org.jetbrains.annotations.Nullable;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.List;

@Prefix("gui.multiblock.neutron_sensor")
public class NeutronSensorMachine extends TieredPartMachine {

    @Key("gui.ctnh.neutron_sensor.invert.disabled")
    @CN("输出：正常\n\n切换以反转红石逻辑\n默认情况下，中子动能介于所设定的最小值和最大值之间时传感器将发出红石信号，小于最小值时则停止发出红石信号")
    @EN("Output: Normal\n\nSwitch to reverse redstone logic\nBy default, the sensor will emit a redstone signal when the neutron kinetic energy is between the set minimum and maximum values, and stop emitting a redstone signal when it is less than the minimum value.")
    public static Lang guiNeutronSensorInvertDisabled;

    @Key("gui.ctnh.neutron_sensor.invert.enabled")
    @CN("输出：反转\n\n切换以反转红石逻辑\n默认情况下，中子动能介于所设定的最小值和最大值之间时传感器将发出红石信号，小于最小值时则停止发出红石信号")
    @EN("Output: Reverse\n\nSwitch to reverse redstone logic\nBy default, the sensor will emit a redstone signal when the neutron kinetic energy is between the set minimum and maximum values, and stop emitting a redstone signal when it is less than the minimum value.")
    public static Lang guiNeutronSensorInvertEnabled;

    public NeutronSensorMachine(IMachineBlockEntity holder) {
        super(holder, GTValues.IV);
    }

    @Persisted
    private int energy = 0;

    @Persisted
    @DescSynced
    private int min = 0;

    private void setMin(int min) {
        this.min = min;
        update();
    }

    @Persisted
    @DescSynced
    private int max = 0;

    private void setMax(int max) {
        this.max = max;
        update();
    }

    @Persisted
    private boolean isInverted = false;

    private void setIsInverted(boolean isInverted) {
        this.isInverted = isInverted;
        update();
    }

    @Persisted
    private int redstoneSignalOutput = 0;

    private void setRedstoneSignalOutput(int redstoneSignalOutput) {
        this.redstoneSignalOutput = redstoneSignalOutput;
        updateSignal();
    }

    private int k = 1000;

    private int clamp(int value, int min, int max) {
        if (value < min) {
            return min;
        } else {
            return Math.min(value, max);
        }
    }

    //////////////////////////////////////
    // ********** GUI ***********//
    //////////////////////////////////////
    @CN("最小中子动能\n(%s)")
    @EN("Min Neutron Kinetic Energy\n(%s)")
    static Lang energy_min;
    @CN("最大中子动能\n(%s)")
    @EN("Max Neutron Kinetic Energy\n(%s)")
    static Lang energy_max;

    @Override
    public Widget createUIWidget() {
        var group = new WidgetGroup(Position.ORIGIN, new Size(176, 112));
        group.addWidget(
                new TextBoxWidget(
                        8, 35, 65, List.of(energy_min.translate("KeV").getString())));
        group.addWidget(
                new TextBoxWidget(
                        8, 80, 65, List.of(energy_max.translate("KeV").getString())));
        group.addWidget(
                new TextFieldWidget(80, 35, 85, 18, () -> String.valueOf(min),
                        (it) -> setMin(clamp(fromText(it), 0, max))) {

                    public int maxValue = 0;

                    @Override
                    public void updateScreen() {
                        super.updateScreen();
                        if (maxValue != max) {
                            maxValue = max;
                            setNumbersOnly(0, maxValue);
                        }
                    }
                }.setNumbersOnly(0, max));
        group.addWidget(
                new TextFieldWidget(80, 80, 85, 18, () -> String.valueOf(max),
                        (it) -> setMax(clamp(fromText(it), min, 1200000))) {

                    public int minValue = 0;

                    @Override
                    public void updateScreen() {
                        super.updateScreen();
                        if (minValue != min) {
                            minValue = min;
                            setNumbersOnly(minValue, 1200000);
                        }
                    }
                }.setNumbersOnly(min, 1200000));
        group.addWidget(
                new ToggleButtonWidget(
                        8, 8, 20, 20, GuiTextures.INVERT_REDSTONE_BUTTON, () -> this.isInverted, this::setIsInverted)
                        .setTooltipText("gui.ctnh.neutron_sensor.invert"));
        group.addWidget(
                new LabelWidget(80, 13, "1000 KeV = 1 MeV").setTextColor(ColorPattern.BLACK.color)
                        .setDropShadow(false));
        return group;
    }

    //////////////////////////////////////
    // ******** Redstone ********//
    //////////////////////////////////////

    public void update(int energy) {
        this.energy = energy;
        var output = RedstoneUtil.computeRedstoneBetweenValues(
                energy, (max * k), (min * k), isInverted);
        if (redstoneSignalOutput != output) {
            setRedstoneSignalOutput(output);
        }
    }

    private void update() {
        update(energy);
        updateSignal();
    }

    @Override
    public int getOutputSignal(@Nullable Direction side) {
        if (side == getFrontFacing().getOpposite()) {
            return redstoneSignalOutput;
        }
        return 0;
    }

    @Override
    public boolean canConnectRedstone(Direction side) {
        return false;
    }

    //////////////////////////////////////
    // ********** Data **********//
    //////////////////////////////////////
    private int fromText(String num) {
        return Integer.parseInt(num);
    }
}
