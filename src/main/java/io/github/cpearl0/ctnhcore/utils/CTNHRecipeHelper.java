package io.github.cpearl0.ctnhcore.utils;


import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.common.data.GTRecipeCapabilities;
import io.netty.util.internal.UnstableApi;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CTNHRecipeHelper extends RecipeHelper {
    @UnstableApi
    public static int getInputCWUt(@NotNull GTRecipe recipe){
        return recipe.tickInputs.getOrDefault(GTRecipeCapabilities.CWU,new ArrayList<>())
                .stream().mapToInt(i-> (int) i.content).sum();
    }
}
