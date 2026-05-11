package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.BiodieselFertileSoilMaterials;
import io.github.cpearl0.ctnhcore.data.materials.CreateMaterials;
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
                .inputItems(dust, SodaAsh)
                .inputFluids(Water.getFluid(1000))
                .outputFluids(BiodieselFertileSoilMaterials.SODIUM_CARBONATE_SOLUTION.getFluid(1000))
                .EUt(30).duration(200)
                .save(provider);

        // 2. Sodium chromate from sodium carbonate: chromite_dust + oxygen + sodium_carbonate_solution ->
        // magnetite_dust + carbon_dioxide + sodium_chromate_solution
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("sodium_chromate_from_sodium_carbonate"))
                .inputItems(dust, Chromite)
                .inputFluids(Oxygen.getFluid(1000))
                .inputFluids(BiodieselFertileSoilMaterials.SODIUM_CARBONATE_SOLUTION.getFluid(1000))
                .outputItems(dust, Magnetite)
                .outputFluids(CarbonDioxide.getFluid(1000))
                .outputFluids(BiodieselFertileSoilMaterials.SODIUM_CHROMATE_SOLUTION.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 3. Sodium dichromate from sodium chromate: sulfuric_acid + sodium_chromate_solution -> sodium_sulfate_dust +
        // sodium_dichromate_solution
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("sodium_dichromate_from_sodium_chromate"))
                .inputFluids(SulfuricAcid.getFluid(1000))
                .inputFluids(BiodieselFertileSoilMaterials.SODIUM_CHROMATE_SOLUTION.getFluid(1000))
                .outputItems(dust, SodiumSulfate)
                .outputFluids(BiodieselFertileSoilMaterials.SODIUM_DICHROMATE_SOLUTION.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 4. Chromium oxide from sodium dichromate: carbon_dust + sodium_dichromate_solution -> soda_ash_dust +
        // chromium_oxide_dust + carbon_monoxide
        CTNHRecipeTypes.DEHYDRATOR_RECIPES.recipeBuilder(CTNHCore.id("chromium_oxide_dust_from_sodium_dichromate"))
                .inputItems(dust, Carbon)
                .inputFluids(BiodieselFertileSoilMaterials.SODIUM_DICHROMATE_SOLUTION.getFluid(1000))
                .outputItems(dust, SodaAsh)
                .outputItems(dust, BiodieselFertileSoilMaterials.CHROMIUM_OXIDE)
                .outputFluids(CarbonMonoxide.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 5. Chrome from chromium oxide: chromium_oxide_dust + aluminium_dust -> chromium_dust + alumina_dust
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("chrome_from_chromium_3"))
                .inputItems(dust, BiodieselFertileSoilMaterials.CHROMIUM_OXIDE)
                .inputItems(dust, Aluminium)
                .outputItems(dust, Chromium)
                .outputItems(dust, Alumina)
                .EUt(480).duration(200)
                .blastFurnaceTemp(1700)
                .save(provider);

        // 6. Sodium sulfide from sodium sulfate: sodium_sulfate_dust + carbon_dust -> sodium_sulfide_dust +
        // carbon_dioxide
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("sodium_sulfide_from_sodium_sulfate"))
                .inputItems(dust, SodiumSulfate)
                .inputItems(dust, Carbon)
                .outputItems(dust, SodiumSulfide)
                .outputFluids(CarbonDioxide.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 7. Soda ash from sodium sulfide: sodium_sulfide_dust + quicklime_dust + carbon_dioxide -> soda_ash_dust +
        // calcium_sulfide_dust
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("soda_ash_from_sodium_sulfide"))
                .inputItems(dust, SodiumSulfide)
                .inputItems(dust, Quicklime)
                .inputFluids(CarbonDioxide.getFluid(1000))
                .outputItems(dust, SodaAsh)
                .outputItems(dust, CreateMaterials.CALCIUM_SULFIDE)
                .EUt(120).duration(200)
                .save(provider);
    }

    // no removals defined currently
}
