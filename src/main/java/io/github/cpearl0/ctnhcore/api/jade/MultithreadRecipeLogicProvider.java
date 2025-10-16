package io.github.cpearl0.ctnhcore.api.jade;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.common.machine.multiblock.part.EnergyHatchPartMachine;
import com.gregtechceu.gtceu.integration.jade.provider.RecipeLogicProvider;
import io.github.cpearl0.ctnhcore.api.recipe.MultiThreadRecipeLogic;
import net.minecraft.nbt.CompoundTag;

public class MultithreadRecipeLogicProvider extends RecipeLogicProvider {
    @Override
    protected void write(CompoundTag data, RecipeLogic capability) {
        data.putBoolean("Working", capability.isWorking());
        var recipeInfo = new CompoundTag();
        if(capability instanceof MultiThreadRecipeLogic m)
        {
            long totalEU = 0;
            long voltage = 0;
            boolean isInput = false;
            for (RecipeLogic thread : m.getAllWorkers()) {
                var recipe = thread.getLastRecipe();
                if (recipe != null) {
                    var EUt = RecipeHelper.getRealEUtWithIO(recipe);
                    totalEU += EUt.getTotalEU();
                    voltage = Math.max(voltage, getVoltage(thread));
                    isInput = EUt.isInput();
                }
            }
            recipeInfo.putLong("EUt", totalEU);
            recipeInfo.putLong("voltage", voltage);
            recipeInfo.putBoolean("isInput", isInput);
        }
        else {
            var recipe = capability.getLastRecipe();
            if (recipe != null) {
                var EUt = RecipeHelper.getRealEUtWithIO(recipe);
                recipeInfo.putLong("EUt", EUt.getTotalEU());
                recipeInfo.putLong("voltage", getVoltage(capability));
                recipeInfo.putBoolean("isInput", EUt.isInput());
            }
        }


        if (!recipeInfo.isEmpty()) {
            data.put("Recipe", recipeInfo);
        }
    }

    public static long getVoltage(RecipeLogic capability) {
        long voltage = -1;
        if (capability.machine instanceof SimpleTieredMachine machine) {
            voltage = GTValues.V[machine.getTier()];
        } else if (capability.machine instanceof WorkableElectricMultiblockMachine machine) {
            voltage = machine.getParts().stream()
                    .filter(EnergyHatchPartMachine.class::isInstance)
                    .map(EnergyHatchPartMachine.class::cast)
                    .mapToLong(dynamo -> GTValues.V[dynamo.getTier()])
                    .max()
                    .orElse(-1);
        }
        // default display as LV, this shouldn't happen because a machine is either electric or steam
        if (voltage == -1) voltage = 32;
        return voltage;
    }
}
