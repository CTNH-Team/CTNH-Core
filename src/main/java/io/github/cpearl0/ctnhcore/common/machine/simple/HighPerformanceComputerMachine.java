package io.github.cpearl0.ctnhcore.common.machine.simple;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.IWorkable;
import com.gregtechceu.gtceu.api.computation.ComputationProducer;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TieredEnergyMachine;
import com.gregtechceu.gtceu.api.machine.trait.DirectComputationPortTrait;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableEnergyContainer;

import lombok.Getter;
import lombok.Setter;

public final class HighPerformanceComputerMachine extends TieredEnergyMachine
                                                  implements ComputationProducer, IWorkable {

    /* 属性 */
    @Setter
    @Getter
    boolean isWorkingEnabled = true;
    @Getter
    final long maxInputOutputAmperage = getMaxInputOutputAmperageStatic();
    int CWUtToProduce;
    long energyToDrain;
    int lastCWUt = 0;

    public HighPerformanceComputerMachine(IMachineBlockEntity holder, int tier) {
        super(holder, tier);
        energyContainer.setSideInputCondition((direction -> direction != getFrontFacing()));
        /* HV为1，超过HV每级翻倍 */
        CWUtToProduce = (tier >= GTValues.HV ? 1 << (tier - GTValues.HV) : 0);
        energyToDrain = (long) GTValues.VA[tier] * maxInputOutputAmperage;
        var computationPort = attachTrait(new DirectComputationPortTrait(this, true, this, null));
        computationPort.setCapabilityValidator(side -> side == null || side == getFrontFacing());
    }

    @Override
    protected NotifiableEnergyContainer createEnergyContainer(Object... args) {
        var tierVoltage = GTValues.VA[tier] * getMaxInputOutputAmperageStatic();
        return NotifiableEnergyContainer.receiverContainer(this,
                tierVoltage * 64L, tierVoltage, getMaxInputOutputAmperageStatic());
    }

    @Override
    public int getOfferedCWUt() {
        return energyContainer.getEnergyStored() >= energyToDrain ? CWUtToProduce : 0;
    }

    @Override
    public void applyProducedCWUt(int allocatedCWUt) {
        lastCWUt = allocatedCWUt;
        if (allocatedCWUt > 0) {
            drainEnergy(false);
        }
    }

    private boolean drainEnergy(boolean simulate) {
        long resultEnergy = energyContainer.getEnergyStored() - energyToDrain;
        if (resultEnergy >= 0L && resultEnergy <= energyContainer.getEnergyCapacity()) {
            if (!simulate) energyContainer.removeEnergy(energyToDrain);
            return true;
        }
        return false;
    }

    @Override
    public boolean isActive() {
        return energyContainer.getEnergyStored() >= (energyToDrain >> 2);
    }

    public static long getMaxInputOutputAmperageStatic() {
        return 16;
    }

    @Override
    public int getProgress() {
        return 0;
    }

    @Override
    public int getMaxProgress() {
        return 0;
    }
}
