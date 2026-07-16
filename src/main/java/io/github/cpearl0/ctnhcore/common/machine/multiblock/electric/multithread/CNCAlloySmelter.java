package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.multithread;

import com.gregtechceu.gtceu.api.machine.multiblock.RecipeElectricMultiblockMachine;
import io.github.cpearl0.ctnhcore.api.machine.feature.ICoilMachine;

import com.gregtechceu.gtceu.api.block.ICoilType;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.common.block.CoilBlock;

public class CNCAlloySmelter extends RecipeElectricMultiblockMachine implements ICoilMachine {

    public CNCAlloySmelter(IMachineBlockEntity holder) {
        super(holder);
    }

    private ICoilType coilType = CoilBlock.CoilType.CUPRONICKEL;

    @Override
    public ICoilType getCoilType() {
        return coilType;
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        var type = getMultiblockState().getMatchContext().get("CoilType");
        if (type instanceof ICoilType coil) {
            this.coilType = coil;
        }
    }
}
