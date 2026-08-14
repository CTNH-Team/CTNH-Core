package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.rareearth;

import io.github.cpearl0.ctnhcore.common.gui.SimpleNumberInputWidget;

import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IDisplayUIMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;

import com.lowdragmc.lowdraglib.gui.widget.ComponentPanelWidget;
import com.lowdragmc.lowdraglib.gui.widget.DraggableScrollableWidgetGroup;
import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public interface ProcessControlMachine extends IRecipeLogicMachine, IDisplayUIMachine {

    ProcessControlProfile getProcessProfile();

    int getPrimarySetting();

    int getSecondarySetting();

    int getPrimaryTarget();

    int getSecondaryTarget();

    boolean isTargetKnown();

    void setPrimarySettingValue(int value);

    void setSecondarySettingValue(int value);

    void setPrimaryTargetValue(int value);

    void setSecondaryTargetValue(int value);

    void setTargetKnownValue(boolean value);

    default @Nullable Component checkProcessControl(GTRecipe recipe) {
        var profile = getProcessProfile();
        setPrimaryTargetValue(profile.resolvePrimaryTarget(recipe));
        setSecondaryTargetValue(profile.resolveSecondaryTarget(recipe));
        setTargetKnownValue(true);
        return isProcessCalibrated() ? null : ProcessControlProfile.calibrationRequired();
    }

    default boolean isProcessCalibrated() {
        var profile = getProcessProfile();
        return isTargetKnown() &&
                profile.primary().matches(getPrimarySetting(), getPrimaryTarget()) &&
                profile.secondary().matches(getSecondarySetting(), getSecondaryTarget());
    }

    default void setPrimarySetting(int value) {
        if (self().isRemote() || getRecipeLogic().isActive()) {
            return;
        }
        int clamped = getProcessProfile().primary().clamp(value);
        if (clamped == getPrimarySetting()) {
            return;
        }
        setPrimarySettingValue(clamped);
        self().markDirty();
        getRecipeLogic().updateTickSubscription();
    }

    default void setSecondarySetting(int value) {
        if (self().isRemote() || getRecipeLogic().isActive()) {
            return;
        }
        int clamped = getProcessProfile().secondary().clamp(value);
        if (clamped == getSecondarySetting()) {
            return;
        }
        setSecondarySettingValue(clamped);
        self().markDirty();
        getRecipeLogic().updateTickSubscription();
    }

    default void addProcessControlText(List<Component> textList) {
        var profile = getProcessProfile();
        textList.add(ProcessControlProfile.processLine(profile.displayName()).copy().withStyle(ChatFormatting.AQUA));
        if (!isTargetKnown()) {
            textList.add(ProcessControlProfile.waitingForRecipe().copy().withStyle(ChatFormatting.GRAY));
            return;
        }
        textList.add(ProcessControlProfile.parameterLine(profile.primaryLabel(), getPrimarySetting(),
                profile.primary().targetMin(getPrimaryTarget()), profile.primary().targetMax(getPrimaryTarget())));
        textList.add(ProcessControlProfile.parameterLine(profile.secondaryLabel(), getSecondarySetting(),
                profile.secondary().targetMin(getSecondaryTarget()),
                profile.secondary().targetMax(getSecondaryTarget())));
        boolean calibrated = isProcessCalibrated();
        textList.add((calibrated ? ProcessControlProfile.calibrated() : ProcessControlProfile.uncalibrated()).copy()
                .withStyle(calibrated ? ChatFormatting.GREEN : ChatFormatting.RED));
        if (getRecipeLogic().isActive()) {
            textList.add(ProcessControlProfile.lockedWhileRunning().copy().withStyle(ChatFormatting.YELLOW));
        }
    }

    default Widget createProcessControlWidget(Consumer<List<Component>> displayText) {
        var profile = getProcessProfile();
        var group = new WidgetGroup(0, 0, 190, 125);
        group.addWidget(new DraggableScrollableWidgetGroup(4, 4, 112, 117).setBackground(getScreenTexture())
                .addWidget(new LabelWidget(4, 5, self().getBlockState().getBlock().getDescriptionId()))
                .addWidget(new ComponentPanelWidget(4, 17,
                        self().getLevel().isClientSide ? text -> {} : displayText)
                        .setMaxWidthLimit(104)
                        .clickHandler(this::handleDisplayClick)));

        var controls = new WidgetGroup(120, 4, 66, 117);
        controls.addWidget(new LabelWidget(4, 6, ProcessControlProfile.calibrationTitle()));
        controls.addWidget(new LabelWidget(4, 28, ProcessControlProfile.primaryInput()));
        controls.addWidget(new SimpleNumberInputWidget(4, 40, 58, 12, this::getPrimarySetting,
                this::setPrimarySetting)
                .setMin(profile.primary().min())
                .setMax(profile.primary().max()));
        controls.addWidget(new LabelWidget(4, 62, ProcessControlProfile.secondaryInput()));
        controls.addWidget(new SimpleNumberInputWidget(4, 74, 58, 12, this::getSecondarySetting,
                this::setSecondarySetting)
                .setMin(profile.secondary().min())
                .setMax(profile.secondary().max()));
        controls.addWidget(new LabelWidget(4, 98, ProcessControlProfile.lockedWhileRunning())
                .setTextColor(ChatFormatting.GRAY.getColor()));
        controls.setBackground(GuiTextures.BACKGROUND_INVERSE);
        group.addWidget(controls);
        group.setBackground(GuiTextures.BACKGROUND_INVERSE);
        return group;
    }
}
