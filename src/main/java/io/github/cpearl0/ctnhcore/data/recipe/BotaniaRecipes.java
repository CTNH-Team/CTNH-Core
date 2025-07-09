package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.api.recipe.PetalRecipeBuilder;
import io.github.cpearl0.ctnhcore.registry.CTNHBlocks;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static io.github.cpearl0.ctnhcore.data.recipe.utils.BotaniaIngredients.*;

public class BotaniaRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        PetalRecipeBuilder.builder("demon_flytrap")
                .input(LIGHTBLUE, LIGHTBLUE, GREEN, GREEN, BROWN)
                .input(runeGreed, runeEnvy, gaiaSpirit)
                .output(CTNHBlocks.DEMON_FLYTRAP.asStack())
                .reagent(Items.GRASS.getDefaultInstance())
                .save(provider);
        PetalRecipeBuilder.builder("blood_antiaris")
                .input(RED, RED, GREEN, GRAY)
                .input(runeSloth, runeFire, runeWrath, gaiaSpirit)
                .output(CTNHBlocks.BLOOD_ANTIARIS.asStack())
                .reagent(Items.GRASS.getDefaultInstance())
                .save(provider);
    }
}
