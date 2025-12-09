package io.github.cpearl0.ctnhcore.api.machine.multiblock;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.IParallelHatch;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockDisplayText;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import io.github.cpearl0.ctnhcore.api.recipe.multithread.MultiThreadRecipeLogic;
import io.github.cpearl0.ctnhcore.api.recipe.multithread.ThreadRecipeLogic;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.part.AsynThreadHatchMachine;
import net.minecraft.network.chat.Component;

import java.util.List;

public class MultiThreadElectricMachine extends WorkableElectricMultiblockMachine {
    public MultiThreadElectricMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    public final int maxThreads = 4;

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        if(getParts().stream().noneMatch(p -> p instanceof AsynThreadHatchMachine))
            getRecipeLogic().resetConfig();
        getRecipeLogic().getAllWorkers().forEach(
                thread -> {
                    if(thread.getOverclockTier() == -1)
                        thread.setOverclockTier(GTUtil.getOCTierByVoltage(getOverclockVoltage()));
                }
        );
    }

    public int threadOverclockTier = -1;

    @Override
    public int getMaxOverclockTier() {
        getRecipeLogic().getAllWorkers().stream().filter(
                        ThreadRecipeLogic::isModifying
                )
                .findFirst()
                .map(ThreadRecipeLogic::getOverclockTier)
                .ifPresent(t -> threadOverclockTier = t);

        if(threadOverclockTier >= 0 && threadOverclockTier <= 30)
            return Math.min(threadOverclockTier, getTier());
        else
            return getTier();
    }

    @Override
    public long getOverclockVoltage() {
        getRecipeLogic().getAllWorkers().stream().filter(
                        ThreadRecipeLogic::isModifying
                )
                .findFirst()
                .map(ThreadRecipeLogic::getOverclockTier)
                .ifPresent(t -> threadOverclockTier = t);

        if(threadOverclockTier >= 0 && threadOverclockTier <= 30)
            return Math.min(GTValues.VEX[threadOverclockTier], super.getOverclockVoltage());
        else
            return super.getOverclockVoltage();
    }

    @Override
    public Widget createUIWidget() {
        return super.createUIWidget();
    }

    @Override
    public void addDisplayText(List<Component> textList) {

        var builder = MultiblockDisplayText.builder(textList, isFormed())
                .setWorkingStatus(recipeLogic.isWorkingEnabled(), recipeLogic.isActive())
                .addEnergyUsageLine(energyContainer)
                .addEnergyTierLine(tier)
                .addWorkingStatusLine();
        if(isFormed)
        {
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
                if(!thread.isEnabled()){
                    key = "§c已禁用§r";
                }
                else if (!thread.isWorkingEnabled()) {
                    key = "gtceu.multiblock.work_paused";
                } else if (thread.isActive()) {
                    key = "gtceu.multiblock.running";
                } else {
                    key= "gtceu.multiblock.idling";
                }
                textList.add(Component.literal("-----------------------------"));
                textList.add(Component.translatable("§b线程§%s：§r", i+1).append(Component.translatable(key)));
                builder.addMachineModeLine(getRecipeType(), getRecipeTypes().length > 1)
                        .addParallelsLine(numParallels, exact)
                        .addBatchModeLine(isBatchEnabled(), batchParallels)
                        .addProgressLine(thread.getProgress(), thread.getMaxProgress(), thread.getProgressPercent())
                        .addOutputLines(thread.getLastRecipe());
            }
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

    public int getWorkingThreadNum(){
        return (int)getRecipeLogic().getAllWorkers().stream()
                .filter(RecipeLogic::isWorking)
                .count();
    }
}
