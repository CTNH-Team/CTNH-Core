package io.github.cpearl0.ctnhcore.data.recipe;

import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.material.Fluids;

import java.util.function.Consumer;

public class ManaCondenserRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        CTNHRecipeTypes.MANA_CONDENSER_RECIPES.recipeBuilder("mana")
                .circuitMeta(0)
                .outputFluids(CTNHMaterials.Mana.getFluid(200))
                .EUt(480)
                .duration(50)
                .save(provider);
        CTNHRecipeTypes.MANA_CONDENSER_RECIPES.recipeBuilder("mana_helium")
                .circuitMeta(1)
                .inputFluids(GTMaterials.Helium.getFluid(100))
                .outputFluids(GTMaterials.Helium.getFluid(FluidStorageKeys.LIQUID, 100))
                .outputFluids(CTNHMaterials.Mana.getFluid(200))
                .EUt(200)
                .duration(50)
                .save(provider);
        CTNHRecipeTypes.MANA_CONDENSER_RECIPES.recipeBuilder("mana_steam")
                .circuitMeta(2)
                .inputFluids(FluidIngredient.of(4000, Fluids.WATER.getFlowing()))
                .outputFluids(GTMaterials.Steam.getFluid(4000))
                .inputFluids(CTNHMaterials.Mana.getFluid(200))
                .EUt(1920)
                .duration(50)
                .addData("mode", "reverse")
                .save(provider);
        CTNHRecipeTypes.MANA_CONDENSER_RECIPES.recipeBuilder("mana_de")
                .circuitMeta(2)
                .inputFluids(GTMaterials.Deuterium.getFluid(40))
                .outputFluids(CTNHMaterials.HotDeuterium.getFluid(40))
                .inputFluids(CTNHMaterials.Mana.getFluid(200))
                .EUt(1920)
                .duration(50)
                .addData("mode", "reverse")
                .save(provider);
        CTNHRecipeTypes.MANA_CONDENSER_RECIPES.recipeBuilder("mana_na")
                .circuitMeta(2)
                .inputFluids(GTMaterials.Sodium.getFluid(20))
                .outputFluids(CTNHMaterials.HotSodium.getFluid(20))
                .inputFluids(CTNHMaterials.Mana.getFluid(200))
                .EUt(1920)
                .duration(50)
                .addData("mode", "reverse")
                .save(provider);
        CTNHRecipeTypes.MANA_CONDENSER_RECIPES.recipeBuilder("mana_nak")
                .circuitMeta(2)
                .inputFluids(GTMaterials.SodiumPotassium.getFluid(20))
                .outputFluids(CTNHMaterials.HotSodiumPotassium.getFluid(20))
                .inputFluids(CTNHMaterials.Mana.getFluid(200))
                .EUt(1920)
                .duration(50)
                .addData("mode", "reverse")
                .save(provider);
    }
}
