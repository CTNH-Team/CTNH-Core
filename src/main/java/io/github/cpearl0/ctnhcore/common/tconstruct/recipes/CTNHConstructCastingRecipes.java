package io.github.cpearl0.ctnhcore.common.tconstruct.recipes;

import io.github.cpearl0.ctnhcore.utils.CTNHConstructRecipeProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;

import java.util.function.Consumer;

public final class CTNHConstructAlloyRecipes extends CTNHConstructRecipeProvider {

    public CTNHConstructAlloyRecipes(PackOutput generator) {
        super(generator);
    }

    @Override
    public String getType() {
        return "alloying";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
    }
}