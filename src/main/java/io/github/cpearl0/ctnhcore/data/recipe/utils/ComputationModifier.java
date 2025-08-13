package io.github.cpearl0.ctnhcore.data.recipe.utils;

import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.chance.logic.ChanceLogic;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.common.data.GTRecipeCapabilities;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import it.unimi.dsi.fastutil.objects.Object2IntFunction;

import java.util.ArrayList;

public class ComputationModifier {
    public static ModifierFunction append(int cwut){
        return r->new GTRecipeBuilder(r,r.getType())
                .CWUt(cwut)
                .buildRawRecipe();
    }
}
