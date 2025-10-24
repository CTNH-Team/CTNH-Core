package io.github.cpearl0.ctnhcore.data.recipe.multiblock;

import com.gregtechceu.gtceu.common.data.GTRecipeConditions;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

import static io.github.cpearl0.ctnhcore.registry.CTNHWorlds.THE_AETHER;

public class AstronomicalObservatoryRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        CTNHRecipeTypes.ASTRONOMICAL_OBSERVATORY.recipeBuilder("observe")
                .inputItems(CTNHItems.ASTRONOMY_CIRCUIT_1)
                .outputItems(CTNHItems.GREAT_ASTRONOMY_CIRCUIT_1)
                .dimension(THE_AETHER)
                .duration(1200)
                .EUt(30)
                .save(provider);
    }
}
