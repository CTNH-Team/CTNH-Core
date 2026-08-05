package io.github.cpearl0.ctnhcore.data.recipe.multiblock;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;

import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class SlaughterHouseRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        CTNHRecipeTypes.SLAUGHTER_HOUSE.recipeBuilder(CTNHCore.id("kill"))
                .inputFluids(GTMaterials.Lubricant.getFluid(2))
                .duration(60)
                .EUt(480)
                .save(provider);
    }
}
