package io.github.cpearl0.ctnhcore.data.recipe.utils;

import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;

public class ComputationModifier {
    public static ModifierFunction append(int cwut){
        return r->new GTRecipeBuilder(r,r.getType())
                .CWUt(cwut)
                .buildRawRecipe();
    }
}
