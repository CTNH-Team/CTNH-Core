package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;

public class MegaLCRMachine extends CoilWorkableElectricMultiblockMachine implements ITieredMachine {

    public MegaLCRMachine(IMachineBlockEntity holder) {
        super(holder);
    }
    // @Persisted
    // @DescSynced
    // public int temperature=0;
    // public double eff=0.0;
}
