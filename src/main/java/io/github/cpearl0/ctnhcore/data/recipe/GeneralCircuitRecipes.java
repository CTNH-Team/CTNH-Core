package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;

import com.gregtechceu.gtceu.data.recipe.CustomTags;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.FORMING_PRESS_RECIPES;

public class GeneralCircuitRecipes {

    private static final String[] TIERS = { "ulv", "lv", "mv", "hv", "ev", "iv", "luv", "zpm", "uv", "uhv", "uev",
            "uiv", "uxv", "opv", "max" };

    public static void init(Consumer<FinishedRecipe> provider) {
        int eUt = 1;
        for (int i = 0; i < TIERS.length; i++) {
            eUt *= 2;
            FORMING_PRESS_RECIPES.recipeBuilder(CTNHCore.id("general_" + TIERS[i]))
                    .inputItems(CustomTags.CIRCUITS_ARRAY[i])
                    .outputItems(new ItemStack(CTNHItems.GENERAL_CIRCUITS[i]))
                    .circuitMeta(24)
                    .EUt(eUt)
                    .duration(20 * i + 20)
                    .save(provider);
        }
    }
}
