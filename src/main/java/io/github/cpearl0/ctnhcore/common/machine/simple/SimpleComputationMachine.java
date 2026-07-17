package io.github.cpearl0.ctnhcore.common.machine.simple;

import io.github.cpearl0.ctnhcore.common.machine.trait.SimpleComputationContainer;

import com.gregtechceu.gtceu.api.machine.trait.DirectComputationPortTrait;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;

import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import org.jetbrains.annotations.NotNull;

public class SimpleComputationMachine extends SimpleTieredMachine {

    public SimpleComputationMachine(IMachineBlockEntity holder, int tier, Int2IntFunction tankScalingFunction,
                                    Object... args) {
        super(holder, tier, tankScalingFunction, args);
        var importComputation = createImportComputationContainer(args);
        var computationPort = new DirectComputationPortTrait(this, true, null, importComputation);
        computationPort.setCapabilityValidator(side -> side == null || side == getFrontFacing());
    }

    @Override
    @NotNull
    protected SimpleComputationContainer createImportComputationContainer(Object... args) {
        var container = new SimpleComputationContainer(this);
        container.setCapabilityValidator(s -> s == null || s == getFrontFacing());
        return container;
    }
}
