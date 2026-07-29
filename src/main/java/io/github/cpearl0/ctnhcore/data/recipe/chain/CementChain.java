package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Water;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MIXER_RECIPES;

public class CementChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 15. cement: circuit 6, calcite_dust + 4x clay_dust + 2x iron_dust + water 10000 -> cement 14400. EUt 24, dur
        // 100
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("cement"))
                .EUt(24).duration(100)
                .circuitMeta(6)
                .inputItems(dust, Calcite)
                .inputItems(dust, Clay, 4)
                .inputItems(dust, Iron, 2)
                .inputFluids(Water.getFluid(10000))
                .outputFluids(Concrete.getFluid(14400))
                .save(provider);
    }
}
