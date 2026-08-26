package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.SpecialMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.ingotHot;

public class WChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 钨酸粉脱水 (dehydrator)
        CTNHRecipeTypes.DEHYDRATOR_RECIPES.recipeBuilder(CTNHCore.id("tungsten_trioxide_dust"))
                .inputItems(dust, GTMaterials.TungsticAcid, 7)
                .outputItems(dust, SpecialMaterials.TUNGSTEN_TRIOXIDE, 4)
                .outputFluids(GTMaterials.Water.getFluid(1000))
                .EUt(480)
                .duration(100)
                .blastFurnaceTemp(3500)
                .save(provider);

        // 三氧化钨变为钨锭和钨粉 (electric blast furnace)
        GTRecipeTypes.BLAST_RECIPES.recipeBuilder(CTNHCore.id("tungsten_dust"))
                .inputItems(dust, SpecialMaterials.TUNGSTEN_TRIOXIDE, 4)
                .inputFluids(GTMaterials.Hydrogen.getFluid(6000))
                .outputItems(dust, GTMaterials.Tungsten, 1)
                .outputFluids(GTMaterials.Water.getFluid(3000))
                .circuitMeta(2)
                .EUt(480)
                .duration(60)
                .blastFurnaceTemp(3500)
                .save(provider);

        GTRecipeTypes.BLAST_RECIPES.recipeBuilder(CTNHCore.id("tungsten_ingot"))
                .inputItems(dust, SpecialMaterials.TUNGSTEN_TRIOXIDE, 8)
                .inputItems(dust, GTMaterials.Carbon, 3)
                .outputItems(ingotHot, GTMaterials.Tungsten, 2)
                .outputFluids(GTMaterials.CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(1000)
                .blastFurnaceTemp(3500)
                .save(provider);
    }
}
