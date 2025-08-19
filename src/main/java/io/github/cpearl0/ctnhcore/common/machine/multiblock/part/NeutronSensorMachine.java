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
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.lowdragmc.lowdraglib.utils.LocalizationUtils;
import com.lowdragmc.lowdraglib.utils.Position;
import com.lowdragmc.lowdraglib.utils.Size;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NeutronSensorMachine extends TieredPartMachine {
    private ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            NeutronSensorMachine.class, TieredPartMachine.MANAGED_FIELD_HOLDER
        );
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
    //**********     GUI     ***********//
    //////////////////////////////////////

    @Override
    public Widget createUIWidget() {
        var group = new WidgetGroup(Position.ORIGIN, new Size(176, 112));
        group.addWidget(
                new TextBoxWidget(
                        8, 35, 65, List.of(LocalizationUtils.format("ctnh.gui.neutron_kinetic_energy.min", "KeV"))
                )
        );
        group.addWidget(
                new TextBoxWidget(
                        8, 80, 65, List.of(LocalizationUtils.format("ctnh.gui.neutron_kinetic_energy.max", "KeV"))
                )
        );
        group.addWidget(
        new TextFieldWidget(80, 35, 85, 18, () -> String.valueOf(min), (it) -> min = clamp(fromText(it), 0, max)) {
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
        new TextFieldWidget(80, 80, 85, 18, () -> String.valueOf(max), (it) -> max = clamp(fromText(it), min, 1200000)) {
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
                        8, 8, 20, 20, GuiTextures.INVERT_REDSTONE_BUTTON, () -> this.isInverted, this::setIsInverted
                ).setTooltipText("gui.ctnh.neutron_sensor.invert")
        );
        group.addWidget(
                new LabelWidget(80, 13, "1000 KeV = 1 MeV").setTextColor(ColorPattern.BLACK.color).setDropShadow(false)
        );
        return group;
    }

    //////////////////////////////////////
    //********     Redstone     ********//
    //////////////////////////////////////

    public void update(int energy) {
        this.energy = energy;
        var output = RedstoneUtil.computeRedstoneBetweenValues(
                energy, (max * k), (min * k), isInverted
        );
        if (redstoneSignalOutput != output) {
            redstoneSignalOutput = output;
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
    //**********     Data     **********//
    //////////////////////////////////////
    private int fromText(String num) {
        return Integer.parseInt(num);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }
}
