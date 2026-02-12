package io.github.cpearl0.ctnhcore.data.recipe.multiblock;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;

import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class WaterPowerStationRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        CTNHRecipeTypes.WATER_POWER.recipeBuilder(CTNHCore.id("water_power"))
                .inputFluids(GTMaterials.Lubricant.getFluid(2))
                .duration(20)
                .EUt(-32)
                .save(provider);
    }
}
