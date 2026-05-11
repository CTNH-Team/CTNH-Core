package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.NewExplosivesProductionMaterials;
import io.github.cpearl0.ctnhcore.data.materials.YeastRelatedMaterials;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.LARGE_CHEMICAL_RECIPES;

public class IodineChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("sodium_iodate"))
                .inputItems(dust, SodiumHydroxide, 18)
                .inputItems(dust, NewExplosivesProductionMaterials.SODIUM_IODIDE, 2)
                .inputFluids(YeastRelatedMaterials.BLUE_VITRIOL_SOLUTION.getFluid(3000))
                .inputFluids(SulfurTrioxide.getFluid(3000))
                .outputItems(dust, NewExplosivesProductionMaterials.SODIUM_IODATE, 5)
                .outputItems(dust, Copper, 3)
                .outputItems(dust, Sodium, 3)
                .outputFluids(NewExplosivesProductionMaterials.SODIUM_SULFATE_SOLUTION.getFluid(3000))
                .outputFluids(Water.getFluid(6000))
                .outputFluids(SulfurDioxide.getFluid(3000))
                .EUt(1920)
                .duration(290)
                .save(provider);
    }
}
