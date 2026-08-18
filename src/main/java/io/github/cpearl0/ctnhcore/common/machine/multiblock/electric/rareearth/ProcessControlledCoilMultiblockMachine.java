package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.rareearth;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;

import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ProcessControlledCoilMultiblockMachine extends CoilWorkableElectricMultiblockMachine
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
    @Persisted
    @DescSynced
    private int settleTicks;
    @Persisted
    @DescSynced
    private int pendingPrimeA;
    @Persisted
    @DescSynced
    private int pendingPrimeB;
    @Persisted
    @DescSynced
    private int runsLeft;
    @Persisted
    @DescSynced
    private boolean maintenanceDue;
    @Persisted
    @DescSynced
    private long pendingHoldEU;
    @Persisted
    @DescSynced
    private int pendingHoldA;
    @Persisted
    @DescSynced
    private int pendingHoldB;

    public ProcessControlledCoilMultiblockMachine(IMachineBlockEntity holder, ProcessControlProfile profile) {
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
    public int getSettleTicks() {
        return settleTicks;
    }

    @Override
    public void setSettleTicks(int value) {
        settleTicks = value;
    }

    @Override
    public int getPendingPrimeA() {
        return pendingPrimeA;
    }

    @Override
    public void setPendingPrimeA(int amount) {
        pendingPrimeA = amount;
    }

    @Override
    public int getPendingPrimeB() {
        return pendingPrimeB;
    }

    @Override
    public void setPendingPrimeB(int amount) {
        pendingPrimeB = amount;
    }

    @Override
    public int getRunsLeft() {
        return runsLeft;
    }

    @Override
    public void setRunsLeft(int value) {
        runsLeft = value;
    }

    @Override
    public boolean isMaintenanceDue() {
        return maintenanceDue;
    }

    @Override
    public void setMaintenanceDueValue(boolean value) {
        maintenanceDue = value;
    }

    @Override
    public long getPendingHoldEU() {
        return pendingHoldEU;
    }

    @Override
    public void setPendingHoldEU(long amount) {
        pendingHoldEU = amount;
    }

    @Override
    public int getPendingHoldA() {
        return pendingHoldA;
    }

    @Override
    public void setPendingHoldA(int amount) {
        pendingHoldA = amount;
    }

    @Override
    public int getPendingHoldB() {
        return pendingHoldB;
    }

    @Override
    public void setPendingHoldB(int amount) {
        pendingHoldB = amount;
    }

    @Override
    protected RecipeLogic createRecipeLogic(Object... args) {
        return new ProcessControlMachine.ProcessControlRecipeLogic(this);
    }

    @Override
    public boolean keepSubscribing() {
        return getSettleTicks() > 0 || getPendingPrimeA() > 0 || getPendingPrimeB() > 0 ||
                getPendingHoldEU() > 0 || getPendingHoldA() > 0 || getPendingHoldB() > 0 || isMaintenanceDue();
    }

    @Override
    public void afterWorking() {
        super.afterWorking();
        ProcessControlMachine.super.consumeRecipeRun();
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
        settleTicks = 0;
        pendingPrimeA = 0;
        pendingPrimeB = 0;
        maintenanceDue = false;
    }
}
