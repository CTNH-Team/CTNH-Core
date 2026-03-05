package io.github.cpearl0.ctnhcore.api.machine.feature;

import io.github.cpearl0.ctnhcore.api.recipe.DigitalMinerLogic;

import com.gregtechceu.gtceu.api.machine.feature.IMachineLife;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;

public interface IDigitalMiner extends IRecipeLogicMachine, IMachineLife {

    @Override
    DigitalMinerLogic getRecipeLogic();

    boolean drainInput(boolean simulate);

    static int getWorkingArea(int maximumRadius) {
        return maximumRadius * 2 + 1;
    }
}
