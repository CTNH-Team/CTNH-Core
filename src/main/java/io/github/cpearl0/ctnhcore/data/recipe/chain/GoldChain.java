package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;
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
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("tier1_gold_processing"))
                .inputItems(dust, GOLD_ALLOY, 4)
                .outputItems(dustTiny, Gold, 4)
                .outputItems(dust, Copper, 3)
                .EUt(30).duration(500)
                .save(provider);

        // 2. Tier2 gold processing: gold_leach_dust + hydrogen -> water + copper_dust + tiny_gold_dust
        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("tier2_gold_processing"))
                .inputItems(dust, GOLD_LEACH, 4)
                .inputFluids(Hydrogen.getFluid(1000))
                .outputFluids(Water.getFluid(1000))
                .outputItems(dust, Copper, 3)
                .outputItems(dustTiny, Gold, 8)
                .EUt(30).duration(300)
                .save(provider);

        // 3. Tier3 gold processing: copper_leach_dust -> copper_dust + chancedOutput(lead/iron/gallium/nickel/silver)
        GTRecipeTypes.SIFTER_RECIPES.recipeBuilder(CTNHCore.id("tier3_gold_processing"))
                .inputItems(dust, COPPER_LEACH, 4)
                .outputItems(dust, Copper, 3)
                .chancedOutput(dust, Lead, 1500, 500)
                .chancedOutput(dust, Iron, 1200, 400)
                .chancedOutput(dust, Gallium, 1200, 400)
                .chancedOutput(dust, Nickel, 1000, 300)
                .chancedOutput(dust, Silver, 800, 200)
                .EUt(30).duration(80)
                .save(provider);

        // 4-7. Gold alloy recipes
        GTRecipeTypes.ALLOY_SMELTER_RECIPES.recipeBuilder(CTNHCore.id("gold_alloy1"))
                .inputItems(dust, PreciousAlloy)
                .inputItems(dust, Copper, 3)
                .outputItems(ingot, GOLD_ALLOY, 4)
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.ALLOY_SMELTER_RECIPES.recipeBuilder(CTNHCore.id("gold_alloy2"))
                .inputItems(ingot, PreciousAlloy)
                .inputItems(dust, Copper, 3)
                .outputItems(ingot, GOLD_ALLOY, 4)
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.ALLOY_SMELTER_RECIPES.recipeBuilder(CTNHCore.id("gold_alloy3"))
                .inputItems(dust, PreciousAlloy)
                .inputItems(ingot, Copper, 3)
                .outputItems(ingot, GOLD_ALLOY, 4)
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.ALLOY_SMELTER_RECIPES.recipeBuilder(CTNHCore.id("gold_alloy4"))
                .inputItems(ingot, PreciousAlloy)
                .inputItems(ingot, Copper, 3)
                .outputItems(ingot, GOLD_ALLOY, 4)
                .EUt(30).duration(100)
                .save(provider);

        // 8. Gold leach dust
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("gold_leach_dust"))
                .inputItems(ingot, GOLD_ALLOY, 4)
                .inputFluids(GTMaterials.NitricAcid.getFluid(1000))
                .outputItems(dust, GOLD_LEACH, 4)
                .outputFluids(GTMaterials.NitrogenDioxide.getFluid(1000))
                .EUt(120).duration(80)
                .save(provider);

        // 9. Copper leach dust
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("copper_leach_dust"))
                .inputItems(dust, GOLD_LEACH, 4)
                .inputFluids(GTMaterials.HydrochloricAcid.getFluid(1000))
                .outputItems(dust, COPPER_LEACH, 4)
                .outputFluids(CHLOROAURIC_ACID.getFluid(1000))
                .EUt(120).duration(80)
                .save(provider);

        // 10. Chloroauric acid to gold
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("chloroauricacid_to_gold"))
                .inputFluids(CHLOROAURIC_ACID.getFluid(1000))
                .notConsumable(dust, BauxiteProcessingMaterials.POTASSIUM_METABI_SULFITE)
                .outputItems(dust, Gold, 2)
                .outputFluids(GTMaterials.Water.getFluid(1000))
                .outputFluids(GTMaterials.Chlorine.getFluid(1000))
                .EUt(120).duration(100)
                .save(provider);

        // 11. Potassium metabisulfite
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder(CTNHCore.id("potassium_metabi_sulfite_dust"))
                .circuitMeta(1)
                .inputItems(dust, Potassium, 2)
                .inputItems(dust, Sulfur, 2)
                .inputFluids(GTMaterials.Oxygen.getFluid(5000))
                .outputItems(dust, BauxiteProcessingMaterials.POTASSIUM_METABI_SULFITE, 9)
                .EUt(30).duration(100)
                .save(provider);
    }
}
