package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.multithread;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.RecipeElectricMultiblockMachine;
import com.gregtechceu.gtceu.common.machine.trait.multiblock.CoilMachineTrait;

public class CNCAlloySmelter extends RecipeElectricMultiblockMachine {

    public CNCAlloySmelter(IMachineBlockEntity holder) {
        super(holder);
        attachTrait(new CoilMachineTrait(this));
    }
}
