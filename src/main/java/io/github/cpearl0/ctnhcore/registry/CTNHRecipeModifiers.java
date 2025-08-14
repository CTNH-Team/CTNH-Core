package io.github.cpearl0.ctnhcore.registry;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.ChemicalPlantMachine;
import org.jetbrains.annotations.NotNull;

import static com.gregtechceu.gtceu.api.recipe.OverclockingLogic.getCoilEUtDiscount;

public class CTNHRecipeModifiers {
    public static final ModifierFunction accurateParallel(MetaMachine machine,GTRecipe recipe,int parallel) {
        var maxParallel = ParallelLogic.getParallelAmount(machine,recipe,parallel);
        if(recipe.hasTick()) {
            return ModifierFunction.builder()
                    .parallels(maxParallel)
                    .inputModifier(ContentModifier.multiplier(maxParallel))
                    .outputModifier(ContentModifier.multiplier(maxParallel))
                    .eutMultiplier(maxParallel)
                    .build();
        }
        else {
            return ModifierFunction.builder()
                    .parallels(maxParallel)
                    .inputModifier(ContentModifier.multiplier(maxParallel))
                    .outputModifier(ContentModifier.multiplier(maxParallel))
                    .build();
        }
    }

    public static final RecipeModifier GCYM_REDUCTION = (machine, recipe) -> CTNHRecipeModifiers
            .reduction(machine, recipe, 0.8, 0.6);

    public static final RecipeModifier COIL_PARALLEL = (machine, recipe) -> CTNHRecipeModifiers.accurateParallel(machine,recipe,Math.min(2147483647, (int) Math.pow(2, ((double) ((CoilWorkableElectricMultiblockMachine) machine).getCoilType().getCoilTemperature() / 900))));

    public static ModifierFunction chemicalPlantOverclock(MetaMachine machine, @NotNull GTRecipe recipe) {
        if (!(machine instanceof IMultiController multiController) || !multiController.isFormed()) return ModifierFunction.IDENTITY;
        if (machine instanceof ChemicalPlantMachine chemicalPlantMachine) {
            var speedMultiplier = 100.0 / (100.0 + (chemicalPlantMachine.getSpeedMultiplier()));
            var energyConsumeMultiplier = 1;
            var parallels = chemicalPlantMachine.getMaxParallel();
                parallels = (int) Math.min(parallels,
                        Math.max(chemicalPlantMachine.getMaxVoltage() / recipe.getInputEUt().getTotalEU(), 1));
            if (parallels == 1 && speedMultiplier == 1.0 && energyConsumeMultiplier == 1.0)
                return ModifierFunction.IDENTITY;
            return  ModifierFunction.builder()
                    .modifyAllContents(ContentModifier.multiplier(parallels))
                    .eutMultiplier(parallels * energyConsumeMultiplier)
                    .durationMultiplier(speedMultiplier)
                    .parallels(parallels)
                    .build();
        }
        return ModifierFunction.IDENTITY;
    }
    public static ModifierFunction superEbfOverclock(MetaMachine machine, @NotNull GTRecipe recipe) {
        if (machine instanceof CoilWorkableElectricMultiblockMachine coilMachine) {
            final var blastFurnaceTemperature = coilMachine.getCoilType().getCoilTemperature() +
                    100 * Math.max(0, coilMachine.getTier() - GTValues.MV);
            var recipeTemp = recipe.data.getInt("ebf_temp");
            if (!recipe.data.contains("ebf_temp") || recipe.data.getInt("ebf_temp") > blastFurnaceTemperature) {
                return ModifierFunction.NULL;
            }
            if (RecipeHelper.getRecipeEUtTier(recipe) > coilMachine.getTier()) {
                return ModifierFunction.NULL;
            }
            var discount = ModifierFunction.builder()
                    .eutMultiplier(getCoilEUtDiscount(recipeTemp, blastFurnaceTemperature))
                    .durationMultiplier(0.5)
                    .build();

            OverclockingLogic logic = (p, v) -> OverclockingLogic.heatingCoilOC(p, v, recipeTemp, blastFurnaceTemperature);
            var oc = logic.getModifier(machine, recipe, coilMachine.getOverclockVoltage());
            return oc.compose(discount);
        }
        return ModifierFunction.IDENTITY;
    }
    private static ModifierFunction reduction(MetaMachine machine, @NotNull GTRecipe recipe, double duration, double eut) {
        return ModifierFunction.builder()
                .durationMultiplier(duration)
                .eutMultiplier(eut)
                .build();
    }


}
