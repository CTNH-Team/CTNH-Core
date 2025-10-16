package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.capability.IParallelHatch;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IDisplayUIMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockDisplayText;
import com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.client.model.machine.MachineRenderState;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import io.github.cpearl0.ctnhcore.api.machine.IMultiThreadMachine;
import io.github.cpearl0.ctnhcore.api.recipe.MultiThreadRecipeLogic;
import lombok.Getter;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MegaLCRMachine extends CoilWorkableElectricMultiblockMachine implements ITieredMachine, IMultiThreadMachine {
    public MegaLCRMachine(IMachineBlockEntity holder) {
        super(holder);
    }
    @Persisted
    @DescSynced
    public int temperature=0;
    public double eff=0.0;

    @Persisted
    public int threads;

    @Persisted
    public final int maxThreads = 4;

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        temperature = getCoilType().getCoilTemperature();
        //maxThreads = 4;
        threads = maxThreads;
    }

    @Override
    public void addDisplayText(List<Component> textList) {

        var builder = MultiblockDisplayText.builder(textList, isFormed())
                .setWorkingStatus(recipeLogic.isWorkingEnabled(), recipeLogic.isActive())
                .addEnergyUsageLine(energyContainer)
                .addEnergyTierLine(tier)
                .addWorkingStatusLine();
        textList.add(Component.translatable("ctnh.multiblock.mega_lcr.info.coil", temperature + "K"));
        textList.add(Component.translatable("ctnh.multiblock.mega_lcr.info.speed", eff));
        for(int i=0; i<maxThreads; i++)
        {
            var thread = getRecipeLogic().getAllWorkers().get(i);
            int numParallels;
            int batchParallels;
            boolean exact = false;
            if (thread.isActive() && thread.getLastRecipe() != null) {
                numParallels = thread.getLastRecipe().parallels;
                batchParallels = thread.getLastRecipe().batchParallels;
                exact = true;
            } else {
                numParallels = getParallelHatch()
                        .map(IParallelHatch::getCurrentParallel)
                        .orElse(0);
                batchParallels = 0;
            }
            String key;
            if (!thread.isWorkingEnabled()) {
                key = "gtceu.multiblock.work_paused";
            } else if (thread.isActive()) {
                key = "gtceu.multiblock.running";
            } else {
                key= "gtceu.multiblock.idling";
            }

            textList.add(Component.translatable("ctnh.multiblock.multithread.status", i+1).append(Component.translatable(key)));
            builder.addMachineModeLine(getRecipeType(), getRecipeTypes().length > 1)
                    .addParallelsLine(numParallels, exact)
                    .addBatchModeLine(isBatchEnabled(), batchParallels)
                    .addProgressLine(thread.getProgress(), thread.getMaxProgress(), thread.getProgressPercent())
                    .addOutputLines(thread.getLastRecipe());
        }


        getDefinition().getAdditionalDisplay().accept(this, textList);


    }

    @Override
    public int getProgress() {
        return super.getProgress();
    }

    @Override
    protected MultiThreadRecipeLogic createRecipeLogic(Object... args) {
        return new MultiThreadRecipeLogic(this, maxThreads);
    }

    @Override
    public MultiThreadRecipeLogic getRecipeLogic() {
        return (MultiThreadRecipeLogic)super.getRecipeLogic();
    }

    @Override
    public void notifyStatusChanged(RecipeLogic.Status oldStatus, RecipeLogic.Status newStatus) {
        super.notifyStatusChanged(oldStatus, getRecipeLogic().getStatus());
    }


    @Override
    public int getMaxThreads() {
        return maxThreads;
    }
}
