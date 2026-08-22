package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.BiodieselFertileSoilMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.CHEMICAL_RECIPES;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.*;

public class ChromiteChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 1. Sodium carbonate solution: soda_ash_dust + water -> sodium_carbonate_solution
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("sodium_carbonate_solution"))
                .circuitMeta(1)
                .inputItems(dust, SodaAsh, 6)
                .inputFluids(Water.getFluid(1000))
                .outputFluids(BiodieselFertileSoilMaterials.SODIUM_CARBONATE_SOLUTION.getFluid(1000))
                .EUt(30).duration(60)
                .save(provider);

        // 2. Sodium chromate from sodium carbonate: chromite_dust + oxygen + sodium_carbonate_solution ->
        // magnetite_dust + carbon_dioxide + sodium_chromate_solution
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("sodium_chromate_from_sodium_carbonate"))
                .inputItems(dust, Chromite, 21)
                .inputFluids(Oxygen.getFluid(10000))
                .inputFluids(BiodieselFertileSoilMaterials.SODIUM_CARBONATE_SOLUTION.getFluid(6000))
                .outputItems(dust, Magnetite, 7)
                .outputFluids(CarbonDioxide.getFluid(6000))
                .outputFluids(BiodieselFertileSoilMaterials.SODIUM_CHROMATE_SOLUTION.getFluid(6000))
                .EUt(120).duration(120)
                .save(provider);

        // 3. Sodium dichromate from sodium chromate: sulfuric_acid + sodium_chromate_solution -> sodium_sulfate_dust +
        // sodium_dichromate_solution
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("sodium_dichromate_from_sodium_chromate"))
                .inputFluids(SulfuricAcid.getFluid(1000))
                .inputFluids(BiodieselFertileSoilMaterials.SODIUM_CHROMATE_SOLUTION.getFluid(2000))
                .outputItems(dust, SodiumSulfate, 7)
                .outputFluids(BiodieselFertileSoilMaterials.SODIUM_DICHROMATE_SOLUTION.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 4. Chromium oxide from sodium dichromate: carbon_dust + sodium_dichromate_solution -> soda_ash_dust +
        // chromium_oxide_dust + carbon_monoxide
        CTNHRecipeTypes.DEHYDRATOR_RECIPES.recipeBuilder(CTNHCore.id("chromium_oxide_dust_from_sodium_dichromate"))
                .inputItems(dust, Carbon, 2)
                .inputFluids(BiodieselFertileSoilMaterials.SODIUM_DICHROMATE_SOLUTION.getFluid(1000))
                .outputItems(dust, SodaAsh, 6)
                .outputItems(dust, BiodieselFertileSoilMaterials.CHROMIUM_OXIDE, 5)
                .outputFluids(CarbonMonoxide.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 5. Chrome from chromium oxide: chromium_oxide_dust + aluminium_dust -> chromium_dust + alumina_dust
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("chrome_from_chromium_3"))
                .inputItems(dust, BiodieselFertileSoilMaterials.CHROMIUM_OXIDE, 5)
                .inputItems(dust, Aluminium, 2)
                .outputItems(dust, Chromium, 2)
                .outputItems(dust, Alumina, 5)
                .EUt(120).duration(200)
                .blastFurnaceTemp(1200)
                .save(provider);

        // 6. Sodium sulfide from sodium sulfate: sodium_sulfate_dust + carbon_dust -> sodium_sulfide_dust +
        // carbon_dioxide
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("sodium_sulfide_from_sodium_sulfate"))
                .inputItems(dust, SodiumSulfate, 7)
                .inputItems(dust, Carbon, 2)
                .outputItems(dust, SodiumSulfide, 3)
                .outputFluids(CarbonDioxide.getFluid(2000))
                .EUt(120).duration(40)
                .save(provider);

        // 7. Soda ash from sodium sulfide: sodium_sulfide_dust + quicklime_dust + carbon_dioxide -> soda_ash_dust +
        // calcium_sulfide_dust
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("soda_ash_from_sodium_sulfide"))
                .inputItems(dust, SodiumSulfide, 3)
                .inputItems(dust, Quicklime, 2)
                .inputFluids(CarbonDioxide.getFluid(1000))
                .outputItems(dust, SodaAsh, 6)
                .outputItems(dust, BiodieselFertileSoilMaterials.CALCIUM_SULFIDE, 2)
                .EUt(120).duration(40)
                .save(provider);
    }

    // no removals defined currently
}
