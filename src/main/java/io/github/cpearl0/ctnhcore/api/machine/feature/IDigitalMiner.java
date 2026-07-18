package io.github.cpearl0.ctnhcore.api.machine.feature;

import io.github.cpearl0.ctnhcore.api.recipe.DigitalMinerLogic;

import com.gregtechceu.gtceu.api.capability.recipe.IRecipeHandler;
import com.gregtechceu.gtceu.api.machine.feature.IMachineLife;
import com.gregtechceu.gtceu.api.machine.feature.IWorkLogicMachine;

import java.util.List;

public interface IDigitalMiner extends IWorkLogicMachine, IMachineLife {

    @Override
    DigitalMinerLogic getWorkLogic();

    boolean drainInput(boolean simulate);

    static int getWorkingArea(int maximumRadius) {
        return maximumRadius * 2 + 1;
    }

    List<? extends IRecipeHandler<?>> getOutputHandlers();
}
