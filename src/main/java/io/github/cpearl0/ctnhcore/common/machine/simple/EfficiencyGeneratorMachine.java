package io.github.cpearl0.ctnhcore.common.machine.simple;

import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.SimpleGeneratorMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;

import it.unimi.dsi.fastutil.ints.Int2IntFunction;

import org.jetbrains.annotations.NotNull;

public class EfficiencyGeneratorMachine extends SimpleGeneratorMachine {

    public int efficiency;

    public EfficiencyGeneratorMachine(IMachineBlockEntity holder, int tier, float hazardStrengthPerOperation,
                                      Int2IntFunction tankScalingFunction, Object... args) {
        super(holder, tier, hazardStrengthPerOperation, tankScalingFunction, args);
    }

    public EfficiencyGeneratorMachine(IMachineBlockEntity holder, int tier, Int2IntFunction tankScalingFunction,
                                      Int2IntFunction efficiencyFunction, Object... args) {
        super(holder, tier, tankScalingFunction, args);
        efficiency = efficiencyFunction.apply(tier);
    }

    public static int naquadahReactor(int tier) {
        if (tier == 4) {
            return 80;
        }
        return (tier - 5) * 50 + 100;
    }

    public static int rocketEngine(int tier) {
        return 80 - (tier - 4) * 10;
    }

    public static int normal(int tier) {
        return tier * 20 + 100;
    }

    public static ModifierFunction recipeModifier(@NotNull MetaMachine machine, @NotNull GTRecipe recipe) {
        if (!(machine instanceof EfficiencyGeneratorMachine generator)) {
            return RecipeModifier.nullWrongType(EfficiencyGeneratorMachine.class, machine);
        }

        long recipeEUt = recipe.getOutputEUt().getTotalEU();
        if (recipeEUt <= 0) return ModifierFunction.NULL;

        int maxParallel = (int) (generator.getOverclockVoltage() / recipeEUt);
        if (maxParallel <= 0) return ModifierFunction.NULL;

        int parallels = ParallelLogic.getParallelAmountFast(generator, recipe, maxParallel);
        if (parallels <= 0) return ModifierFunction.NULL;

        return ModifierFunction.builder()
                .inputModifier(ContentModifier.multiplier(parallels))
                .outputModifier(ContentModifier.multiplier(parallels))
                .eutMultiplier(parallels)
                .parallels(parallels)
                .durationMultiplier((double) generator.efficiency / 100)
                .build();
    }
}
