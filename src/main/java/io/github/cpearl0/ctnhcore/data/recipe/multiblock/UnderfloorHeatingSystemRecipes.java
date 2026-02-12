package io.github.cpearl0.ctnhcore.data.recipe.multiblock;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;

import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class UnderfloorHeatingSystemRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        CTNHRecipeTypes.UNDERFLOOR_HEATING_SYSTEM.recipeBuilder(CTNHCore.id("heating"))
                .inputFluids(GTMaterials.Steam.getFluid(640))
                .outputFluids(GTMaterials.DistilledWater.getFluid(4))
                .duration(80)
                .save(provider);
    }
}
