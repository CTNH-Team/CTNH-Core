package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.data.materials.BauxiteProcessingMaterials;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static io.github.cpearl0.ctnhcore.data.materials.CrudeGoldRefiningMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.PreciousAlloy;

public class GoldChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 从 GoldChain.js 迁移
        // 1. Tier1 gold processing: gold_alloy_dust -> tiny_gold_dust + copper_dust
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("tier1_gold_processing")
                .inputItems(dust, GOLD_ALLOY)
                .outputItems(dustTiny, Gold)
                .outputItems(dust, Copper)
                .EUt(30).duration(200)
                .save(provider);

        // 2. Tier2 gold processing: gold_leach_dust + hydrogen -> water + copper_dust + tiny_gold_dust
        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("tier2_gold_processing")
                .inputItems(dust, GOLD_LEACH)
                .inputFluids(Hydrogen.getFluid(1000))
                .outputFluids(Water.getFluid(1000))
                .outputItems(dust, Copper)
                .outputItems(dustTiny, Gold)
                .EUt(120).duration(200)
                .save(provider);

        // 3. Tier3 gold processing: copper_leach_dust -> copper_dust + chancedOutput(lead/iron/gallium/nickel/silver)
        GTRecipeTypes.SIFTER_RECIPES.recipeBuilder("tier3_gold_processing")
                .inputItems(dust, COPPER_LEACH)
                .outputItems(dust, Copper)
                .chancedOutput(dust, Lead, 2500, 500)
                .chancedOutput(dust, Iron, 2000, 500)
                .chancedOutput(dust, Gallium, 1500, 500)
                .chancedOutput(dust, Nickel, 1000, 500)
                .chancedOutput(dust, Silver, 500, 250)
                .EUt(120).duration(200)
                .save(provider);

        // 4-7. Gold alloy recipes
        GTRecipeTypes.ALLOY_SMELTER_RECIPES.recipeBuilder("gold_alloy1")
                .inputItems(dust, PreciousAlloy)
                .inputItems(dust, Copper)
                .outputItems(ingot, GOLD_ALLOY)
                .EUt(16).duration(200)
                .save(provider);

        GTRecipeTypes.ALLOY_SMELTER_RECIPES.recipeBuilder("gold_alloy2")
                .inputItems(ingot, PreciousAlloy)
                .inputItems(dust, Copper)
                .outputItems(ingot, GOLD_ALLOY)
                .EUt(16).duration(200)
                .save(provider);

        GTRecipeTypes.ALLOY_SMELTER_RECIPES.recipeBuilder("gold_alloy3")
                .inputItems(dust, PreciousAlloy)
                .inputItems(ingot, Copper)
                .outputItems(ingot, GOLD_ALLOY)
                .EUt(16).duration(200)
                .save(provider);

        GTRecipeTypes.ALLOY_SMELTER_RECIPES.recipeBuilder("gold_alloy4")
                .inputItems(ingot, PreciousAlloy)
                .inputItems(ingot, Copper)
                .outputItems(ingot, GOLD_ALLOY)
                .EUt(16).duration(200)
                .save(provider);

        // 8. Gold leach dust
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("gold_leach_dust")
                .inputItems(ingot, GOLD_ALLOY)
                .inputFluids(GTMaterials.NitricAcid.getFluid(1000))
                .outputItems(dust, GOLD_LEACH)
                .outputFluids(GTMaterials.NitrogenDioxide.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 9. Copper leach dust
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("copper_leach_dust")
                .inputItems(dust, GOLD_LEACH)
                .inputFluids(GTMaterials.HydrochloricAcid.getFluid(1000))
                .outputItems(dust, COPPER_LEACH)
                .outputFluids(CHLOROAURIC_ACID.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 10. Chloroauric acid to gold
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("chloroauricacid_to_gold")
                .inputFluids(CHLOROAURIC_ACID.getFluid(1000))
                .notConsumable(dust, BauxiteProcessingMaterials.POTASSIUM_METABI_SULFITE)
                .outputItems(dust, Gold)
                .outputFluids(GTMaterials.Water.getFluid(1000))
                .outputFluids(GTMaterials.Chlorine.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 11. Potassium metabisulfite
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("potassium_metabi_sulfite_dust")
                .inputItems(dust, Potassium)
                .inputItems(dust, Sulfur)
                .inputFluids(GTMaterials.Oxygen.getFluid(2000))
                .outputItems(dust, BauxiteProcessingMaterials.POTASSIUM_METABI_SULFITE)
                .EUt(30).duration(200)
                .save(provider);
    }
}
