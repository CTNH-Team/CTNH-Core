package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.rareearth;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.RecipeElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;

import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ProcessControlledElectricMultiblockMachine extends RecipeElectricMultiblockMachine
                                                        implements ProcessControlMachine {

    private final ProcessControlProfile processProfile;

    @Persisted
    @DescSynced
    private int primarySetting;
    @Persisted
    @DescSynced
    private int secondarySetting;
    @DescSynced
    private int primaryTarget;
    @DescSynced
    private int secondaryTarget;
    @DescSynced
    private boolean targetKnown;

    public ProcessControlledElectricMultiblockMachine(IMachineBlockEntity holder, ProcessControlProfile profile) {
        super(holder);
        this.processProfile = profile;
        this.primarySetting = profile.primary().defaultValue();
        this.secondarySetting = profile.secondary().defaultValue();
    }

    @Override
    public ProcessControlProfile getProcessProfile() {
        return processProfile;
    }

    @Override
    public int getPrimarySetting() {
        return primarySetting;
    }

    @Override
    public int getSecondarySetting() {
        return secondarySetting;
    }

    @Override
    public int getPrimaryTarget() {
        return primaryTarget;
    }

    @Override
    public int getSecondaryTarget() {
        return secondaryTarget;
    }

    @Override
    public boolean isTargetKnown() {
        return targetKnown;
    }

    @Override
    public void setPrimarySettingValue(int value) {
        primarySetting = value;
    }

    @Override
    public void setSecondarySettingValue(int value) {
        secondarySetting = value;
    }

    @Override
    public void setPrimaryTargetValue(int value) {
        primaryTarget = value;
    }

    @Override
    public void setSecondaryTargetValue(int value) {
        secondaryTarget = value;
    }

    @Override
    public void setTargetKnownValue(boolean value) {
        targetKnown = value;
    }

    @Override
    public @Nullable Component beforeWorking(@NotNull GTRecipe recipe) {
        var failure = super.beforeWorking(recipe);
        return failure == null ? checkProcessControl(recipe) : failure;
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);
        addProcessControlText(textList);
    }

    @Override
    public Widget createUIWidget() {
        return ProcessControlMachine.super.createProcessControlWidget(this::addDisplayText);
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
        targetKnown = false;
    }
}
