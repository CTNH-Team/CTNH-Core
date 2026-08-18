package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.rareearth;

import io.github.cpearl0.ctnhcore.common.gui.SimpleNumberInputWidget;

import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.misc.EnergyContainerList;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IDisplayUIMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;

import com.lowdragmc.lowdraglib.gui.widget.ComponentPanelWidget;
import com.lowdragmc.lowdraglib.gui.widget.DraggableScrollableWidgetGroup;
import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public interface ProcessControlMachine extends IRecipeLogicMachine, IDisplayUIMachine {

    int SETTLE_TICKS_PER_TUNE = 60;
    int RUNS_PER_TUNE = 120;
    int HOLD_MULTIPLIER = 120;

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

    EnergyContainerList getEnergyContainer();

    int getSettleTicks();

    void setSettleTicks(int value);

    int getPendingPrimeA();

    void setPendingPrimeA(int amount);

    int getPendingPrimeB();

    void setPendingPrimeB(int amount);

    int getRunsLeft();

    void setRunsLeft(int value);

    boolean isMaintenanceDue();

    void setMaintenanceDueValue(boolean value);

    long getPendingHoldEU();

    void setPendingHoldEU(long amount);

    int getPendingHoldA();

    void setPendingHoldA(int amount);

    int getPendingHoldB();

    void setPendingHoldB(int amount);

    default @Nullable Component checkProcessControl(GTRecipe recipe) {
        var profile = getProcessProfile();
        setPrimaryTargetValue(profile.resolvePrimaryTarget(recipe));
        setSecondaryTargetValue(profile.resolveSecondaryTarget(recipe));
        setTargetKnownValue(true);
        if (getPendingPrimeA() > 0 || getPendingPrimeB() > 0 || getSettleTicks() > 0) {
            return ProcessControlProfile.settling(Component.literal(String.valueOf((getSettleTicks() + 19) / 20)));
        }
        if (getPendingHoldEU() > 0 || getPendingHoldA() > 0 || getPendingHoldB() > 0) {
            return ProcessControlProfile.holdSupplyMissing();
        }
        if (getRunsLeft() <= 0) {
            return ProcessControlProfile.runsExhausted();
        }
        var batchItem = profile.batchItem(getPrimarySetting());
        if (!batchItem.isEmpty() &&
                drainInputItem(batchItem.copy(), IFluidHandler.FluidAction.SIMULATE) < batchItem.getCount()) {
            return ProcessControlProfile.batchSupplyMissing(batchItem.getHoverName());
        }
        var batchFluid = profile.batchFluid();
        if (!batchFluid.isEmpty() &&
                drainInputFluid(batchFluid.copy(), IFluidHandler.FluidAction.SIMULATE) < batchFluid.getAmount()) {
            return ProcessControlProfile.batchSupplyMissing(batchFluid.getDisplayName());
        }
        if (!batchItem.isEmpty()) {
            drainInputItem(batchItem.copy(), IFluidHandler.FluidAction.EXECUTE);
        }
        if (!batchFluid.isEmpty()) {
            drainInputFluid(batchFluid.copy(), IFluidHandler.FluidAction.EXECUTE);
        }
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
        queueTuningCost();
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
        queueTuningCost();
        self().markDirty();
        getRecipeLogic().updateTickSubscription();
    }

    default void queueTuningCost() {
        var profile = getProcessProfile();
        setSettleTicks(SETTLE_TICKS_PER_TUNE);
        var primeA = profile.primeFluidA();
        if (!primeA.isEmpty()) {
            setPendingPrimeA(getPendingPrimeA() + primeA.getAmount());
        }
        var primeB = profile.primeFluidB();
        if (!primeB.isEmpty()) {
            setPendingPrimeB(getPendingPrimeB() + primeB.getAmount());
        }
        getRecipeLogic().updateTickSubscription();
    }

    default void processControlServerTick() {
        if (self().isRemote()) {
            return;
        }
        var profile = getProcessProfile();
        if (getPendingPrimeA() > 0) {
            var prime = profile.primeFluidA().copy();
            prime.setAmount(getPendingPrimeA());
            int drained = drainInputFluid(prime, IFluidHandler.FluidAction.EXECUTE);
            if (drained > 0) {
                setPendingPrimeA(getPendingPrimeA() - drained);
                self().markDirty();
            }
            return;
        }
        if (getPendingPrimeB() > 0) {
            var prime = profile.primeFluidB().copy();
            prime.setAmount(getPendingPrimeB());
            int drained = drainInputFluid(prime, IFluidHandler.FluidAction.EXECUTE);
            if (drained > 0) {
                setPendingPrimeB(getPendingPrimeB() - drained);
                self().markDirty();
            }
            return;
        }
        if (getPendingHoldEU() > 0) {
            long available = getEnergyContainer().getEnergyStored();
            if (available > 0) {
                long drained = getEnergyContainer().removeEnergy(Math.min(getPendingHoldEU(), available));
                if (drained > 0) {
                    setPendingHoldEU(getPendingHoldEU() - drained);
                    self().markDirty();
                }
            }
            return;
        }
        if (getPendingHoldA() > 0) {
            var hold = profile.holdFluidA(getPrimarySetting(), getSecondarySetting()).copy();
            hold.setAmount(getPendingHoldA());
            int drained = drainInputFluid(hold, IFluidHandler.FluidAction.EXECUTE);
            if (drained > 0) {
                setPendingHoldA(getPendingHoldA() - drained);
                self().markDirty();
            }
            return;
        }
        if (getPendingHoldB() > 0) {
            var hold = profile.holdFluidB(getPrimarySetting(), getSecondarySetting()).copy();
            hold.setAmount(getPendingHoldB());
            int drained = drainInputFluid(hold, IFluidHandler.FluidAction.EXECUTE);
            if (drained > 0) {
                setPendingHoldB(getPendingHoldB() - drained);
                self().markDirty();
            }
            return;
        }
        if (getSettleTicks() <= 0) {
            if (isMaintenanceDue() && getRunsLeft() <= 0 &&
                    getPendingHoldEU() <= 0 && getPendingHoldA() <= 0 && getPendingHoldB() <= 0) {
                setMaintenanceDueValue(false);
                setRunsLeft(RUNS_PER_TUNE);
                self().markDirty();
            }
            getRecipeLogic().updateTickSubscription();
            return;
        }
        setSettleTicks(getSettleTicks() - 1);
        if (getSettleTicks() <= 0) {
            queueHoldUpkeep();
        }
        self().markDirty();
        getRecipeLogic().updateTickSubscription();
    }

    default void queueHoldUpkeep() {
        var profile = getProcessProfile();
        setMaintenanceDueValue(true);
        int primary = getPrimarySetting();
        int secondary = getSecondarySetting();
        setPendingHoldEU(getPendingHoldEU() + profile.holdEUt(primary, secondary) * HOLD_MULTIPLIER);
        var holdA = profile.holdFluidA(primary, secondary);
        if (!holdA.isEmpty()) {
            setPendingHoldA(getPendingHoldA() + holdA.getAmount() * HOLD_MULTIPLIER);
        }
        var holdB = profile.holdFluidB(primary, secondary);
        if (!holdB.isEmpty()) {
            setPendingHoldB(getPendingHoldB() + holdB.getAmount() * HOLD_MULTIPLIER);
        }
        getRecipeLogic().updateTickSubscription();
    }

    default void consumeRecipeRun() {
        if (getRunsLeft() <= 0) {
            return;
        }
        int next = getRunsLeft() - 1;
        setRunsLeft(next);
        if (next <= 0) {
            queueHoldUpkeep();
        }
        self().markDirty();
    }

    default int drainInputFluid(FluidStack stack, IFluidHandler.FluidAction action) {
        if (stack.isEmpty()) {
            return 0;
        }
        int remaining = stack.getAmount();
        for (var handler : getCapabilitiesFlat(IO.IN, FluidRecipeCapability.CAP)) {
            if (!(handler instanceof NotifiableFluidTank fluidTank)) {
                continue;
            }
            var candidate = stack.copy();
            candidate.setAmount(remaining);
            remaining -= fluidTank.drainInternal(candidate, action).getAmount();
            if (remaining <= 0) {
                break;
            }
        }
        return stack.getAmount() - remaining;
    }

    default int drainInputItem(ItemStack stack, IFluidHandler.FluidAction action) {
        if (stack.isEmpty()) {
            return 0;
        }
        int remaining = stack.getCount();
        for (var handler : getCapabilitiesFlat(IO.IN, ItemRecipeCapability.CAP)) {
            if (!(handler instanceof NotifiableItemStackHandler itemHandler)) {
                continue;
            }
            for (int slot = 0; slot < itemHandler.storage.getSlots() && remaining > 0; slot++) {
                var stored = itemHandler.storage.getStackInSlot(slot);
                if (stored.isEmpty() || !ItemStack.isSameItemSameTags(stored, stack)) {
                    continue;
                }
                int take = Math.min(stored.getCount(), remaining);
                if (action == IFluidHandler.FluidAction.EXECUTE) {
                    var updated = stored.copy();
                    updated.shrink(take);
                    itemHandler.storage.setStackInSlot(slot, updated);
                    itemHandler.onContentsChanged();
                }
                remaining -= take;
            }
            if (remaining <= 0) {
                break;
            }
        }
        return stack.getCount() - remaining;
    }

    default void addProcessControlText(List<Component> textList) {
        var profile = getProcessProfile();
        textList.add(ProcessControlProfile.processLine(profile.displayName()).copy().withStyle(ChatFormatting.AQUA));
        if (!isTargetKnown()) {
            textList.add(ProcessControlProfile.waitingForRecipe().copy().withStyle(ChatFormatting.GRAY));
            return;
        }
        int primary = getPrimarySetting();
        int secondary = getSecondarySetting();
        textList.add(ProcessControlProfile.parameterLine(profile.primaryLabel(), primary,
                profile.primary().targetMin(getPrimaryTarget()), profile.primary().targetMax(getPrimaryTarget())));
        textList.add(ProcessControlProfile.parameterLine(profile.secondaryLabel(), secondary,
                profile.secondary().targetMin(getSecondaryTarget()),
                profile.secondary().targetMax(getSecondaryTarget())));
        var holdA = profile.holdFluidA(primary, secondary);
        var holdB = profile.holdFluidB(primary, secondary);
        var tuningA = profile.primeFluidA();
        var tuningB = profile.primeFluidB();
        StringBuilder tuningText = new StringBuilder();
        if (!tuningA.isEmpty()) {
            tuningText.append(tuningA.getDisplayName().getString()).append(" ").append(tuningA.getAmount())
                    .append("mB");
        }
        if (!tuningB.isEmpty()) {
            if (tuningText.length() > 0) {
                tuningText.append("、");
            }
            tuningText.append(tuningB.getDisplayName().getString()).append(" ").append(tuningB.getAmount())
                    .append("mB");
        }
        textList.add(ProcessControlProfile.tuningSupply(Component.literal(tuningText.toString()))
                .copy().withStyle(ChatFormatting.GRAY));
        StringBuilder maintenanceText = new StringBuilder();
        if (!holdA.isEmpty()) {
            maintenanceText.append(holdA.getDisplayName().getString()).append(" ")
                    .append(holdA.getAmount() * HOLD_MULTIPLIER).append("mB");
        }
        if (!holdB.isEmpty()) {
            if (maintenanceText.length() > 0) {
                maintenanceText.append("、");
            }
            maintenanceText.append(holdB.getDisplayName().getString()).append(" ")
                    .append(holdB.getAmount() * HOLD_MULTIPLIER).append("mB");
        }
        textList.add(ProcessControlProfile.maintenanceSupply(Component.literal(maintenanceText.toString()))
                .copy().withStyle(ChatFormatting.GRAY));
        StringBuilder holdExtra = new StringBuilder();
        if (!holdA.isEmpty()) {
            holdExtra.append(ProcessControlProfile.holdFluidPart(
                    Component.literal(String.valueOf(holdA.getAmount() * HOLD_MULTIPLIER)),
                    holdA.getDisplayName()).getString());
        }
        if (!holdB.isEmpty()) {
            holdExtra.append(ProcessControlProfile.holdFluidPart(
                    Component.literal(String.valueOf(holdB.getAmount() * HOLD_MULTIPLIER)),
                    holdB.getDisplayName()).getString());
        }
        textList.add(ProcessControlProfile
                .holdCostLine(Component.literal(String.valueOf(profile.holdEUt(primary, secondary) * HOLD_MULTIPLIER)),
                        Component.literal(holdExtra.toString()))
                .copy().withStyle(ChatFormatting.GRAY));
        textList.add(ProcessControlProfile
                .runQuota(Component.literal(String.valueOf(getRunsLeft())),
                        Component.literal(String.valueOf(RUNS_PER_TUNE)))
                .copy().withStyle(getRunsLeft() > 0 ? ChatFormatting.GRAY : ChatFormatting.RED));
        if (getPendingPrimeA() > 0 || getPendingPrimeB() > 0) {
            StringBuilder primeNames = new StringBuilder();
            if (getPendingPrimeA() > 0) {
                var primeA = profile.primeFluidA();
                primeNames.append(primeA.getDisplayName().getString()).append(" ")
                        .append(getPendingPrimeA()).append("mB");
            }
            if (getPendingPrimeB() > 0) {
                if (primeNames.length() > 0) {
                    primeNames.append(", ");
                }
                var primeB = profile.primeFluidB();
                primeNames.append(primeB.getDisplayName().getString()).append(" ")
                        .append(getPendingPrimeB()).append("mB");
            }
            textList.add(ProcessControlProfile.waitingForPrime(Component.literal(primeNames.toString()))
                    .copy().withStyle(ChatFormatting.YELLOW));
        } else if (getSettleTicks() > 0) {
            textList.add(ProcessControlProfile.settling(Component.literal(String.valueOf((getSettleTicks() + 19) / 20)))
                    .copy().withStyle(ChatFormatting.YELLOW));
        }
        if (getPendingHoldEU() > 0 || getPendingHoldA() > 0 || getPendingHoldB() > 0) {
            StringBuilder holdNames = new StringBuilder();
            if (getPendingHoldEU() > 0) {
                holdNames.append(getPendingHoldEU()).append("EU");
            }
            if (getPendingHoldA() > 0) {
                if (holdNames.length() > 0) {
                    holdNames.append(", ");
                }
                holdNames.append(profile.holdFluidA(primary, secondary).getDisplayName().getString()).append(" ")
                        .append(getPendingHoldA()).append("mB");
            }
            if (getPendingHoldB() > 0) {
                if (holdNames.length() > 0) {
                    holdNames.append(", ");
                }
                holdNames.append(profile.holdFluidB(primary, secondary).getDisplayName().getString()).append(" ")
                        .append(getPendingHoldB()).append("mB");
            }
            textList.add(ProcessControlProfile.waitingForHold(Component.literal(holdNames.toString()))
                    .copy().withStyle(ChatFormatting.YELLOW));
        }
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

    class ProcessControlRecipeLogic extends RecipeLogic {

        public ProcessControlRecipeLogic(IRecipeLogicMachine machine) {
            super(machine);
        }

        @Override
        public void serverTick() {
            if (machine instanceof ProcessControlMachine pcMachine) {
                pcMachine.processControlServerTick();
            }
            super.serverTick();
        }

    }
}
