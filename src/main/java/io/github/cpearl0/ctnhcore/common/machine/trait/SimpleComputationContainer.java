package io.github.cpearl0.ctnhcore.common.machine.trait;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.NetworkedComputationContainer;

public class SimpleComputationContainer extends NetworkedComputationContainer {

    public SimpleComputationContainer(MetaMachine machine) {
        super(machine, IO.IN);
    }
}
