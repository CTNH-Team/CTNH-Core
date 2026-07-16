package io.github.cpearl0.ctnhcore.data.recipe.utils;

import com.gregtechceu.gtceu.api.capability.recipe.CWURecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;

public class ComputationModifier {

    public static void append(GTRecipe recipe, int cwut) {
        recipe.tickInputs.computeIfAbsent(CWURecipeCapability.CAP, ignored -> new java.util.ArrayList<>()).add(cwut);
    }
}
