package io.github.cpearl0.ctnhcore.common.machine.multiblock;

import com.gregtechceu.gtceu.api.capability.IOpticalComputationProvider;
import com.gregtechceu.gtceu.api.capability.IOpticalComputationReceiver;
import com.gregtechceu.gtceu.api.capability.forge.GTCapability;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MultiblockComputationMachine extends WorkableElectricMultiblockMachine implements IOpticalComputationReceiver {


    protected IOpticalComputationProvider computationContainer;

    public MultiblockComputationMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }


    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        getParts().forEach(part -> part.self().holder.self()
                .getCapability(GTCapability.CAPABILITY_COMPUTATION_PROVIDER)
                .ifPresent(provider -> this.computationContainer = provider));
        if (computationContainer == null) {
            onStructureInvalid();
        }
    }
    @Override
    public void onStructureInvalid() {
        computationContainer = null;
        super.onStructureInvalid();
    }

    @Override
    public IOpticalComputationProvider getComputationProvider()
    {
        return computationContainer;
    }

    public int getMaxCWUt() {
        return computationContainer!= null? computationContainer.getMaxCWUt() : 0;
    }
    //////////////////////////////////////
    // ******* GUI ********//
    //////////////////////////////////////
    @Override
    public void addDisplayText(@NotNull List<Component> textList) {
        if(isFormed()) {
            int maxCUWt = getMaxCWUt();
            textList.add(Component.translatable("gtceu.multiblock.computation.max",
                    FormattingUtil.formatNumbers(maxCUWt)));
        }
        super.addDisplayText(textList);
    }
}

