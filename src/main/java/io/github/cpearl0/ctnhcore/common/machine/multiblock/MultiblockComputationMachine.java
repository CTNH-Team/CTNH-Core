package io.github.cpearl0.ctnhcore.common.machine.multiblock;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.RecipeElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.NetworkedComputationContainer;
import com.gregtechceu.gtceu.utils.FormattingUtil;

import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MultiblockComputationMachine extends RecipeElectricMultiblockMachine {

    protected final NetworkedComputationContainer computationContainer;

    public MultiblockComputationMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
        computationContainer = new NetworkedComputationContainer(this, IO.IN);
    }

    public int getCurrentCWUt() {
        return computationContainer.getReceivedCWUt();
    }

    //////////////////////////////////////
    // ******* GUI ********//
    //////////////////////////////////////
    @Override
    public void addDisplayText(@NotNull List<Component> textList) {
        if (isFormed()) {
            int maxCUWt = getCurrentCWUt();
            textList.add(Component.translatable("gtceu.multiblock.computation.max",
                    FormattingUtil.formatNumbers(maxCUWt)));
        }
        super.addDisplayText(textList);
    }
}
