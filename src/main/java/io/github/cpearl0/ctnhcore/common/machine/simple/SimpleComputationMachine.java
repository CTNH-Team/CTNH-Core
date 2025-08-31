package io.github.cpearl0.ctnhcore.common.machine.simple;

import com.gregtechceu.gtceu.api.capability.IOpticalComputationProvider;
import com.gregtechceu.gtceu.api.capability.IOpticalComputationReceiver;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import io.github.cpearl0.ctnhcore.common.machine.trait.SimpleComputationContainer;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class SimpleComputationMachine extends SimpleTieredMachine {

    public SimpleComputationMachine(IMachineBlockEntity holder, int tier, Int2IntFunction tankScalingFunction, Object... args) {
        super(holder, tier, tankScalingFunction, args);
    }

    @Override
    @NotNull
    protected SimpleComputationContainer createImportComputationContainer(Object... args) {
        var container = new SimpleComputationContainer(this);
        container.setCapabilityValidator(s -> s == null || s == getFrontFacing());
        return container;
    }

    @Override
    public void onNeighborChanged(Block block, BlockPos fromPos, boolean isMoving) {
        super.onNeighborChanged(block, fromPos, isMoving);
        ((SimpleComputationContainer) importComputation).updateComputationProvider();
    }
}
