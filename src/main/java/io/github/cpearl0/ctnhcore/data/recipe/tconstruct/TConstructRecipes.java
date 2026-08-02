package io.github.cpearl0.ctnhcore.data.recipe.tconstruct;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.crafting.Ingredient;

import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.melting.MeltingRecipeBuilder;

import java.util.function.Consumer;

public class TConstructRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        MeltingRecipeBuilder.melting(
                Ingredient.of(CTNHItems.REFINED_IRON_INGOT.get()),
                TinkerFluids.moltenSteel,
                FluidValues.INGOT)
                .save(provider, CTNHCore.id("smeltery/melting/refined_iron_ingot_to_steel"));//Todo:todo标注一下，方便swinter之后改
    }
}
