package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.BiodieselFertileSoilMaterials;
import io.github.cpearl0.ctnhcore.data.materials.NiobiumTantalumJointProcessingMaterials;
import io.github.cpearl0.ctnhcore.data.materials.PlatinumLineMaterials;
import io.github.cpearl0.ctnhcore.data.materials.SpecialMaterials;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.Alumina;

public class TantaliteChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 1. Tantalum alkaline mixture: tantalite_dust + pyrochlore_dust + sodium_carbonate_solution ->
        // tantalum_alkaline_mixture
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("tantalum_alkaline_mixture"))
                .inputItems(dust, Tantalite, 32)
                .inputItems(dust, Pyrochlore, 32)
                .inputFluids(BiodieselFertileSoilMaterials.SODIUM_CARBONATE_SOLUTION.getFluid(8000))
                .outputFluids(NiobiumTantalumJointProcessingMaterials.TANTALUM_ALKALINE_MIXTURE.getFluid(4000))
                .EUt(100).duration(10)
                .save(provider);

        // 2. Tantalite fluorine: potassium_fluoride_dust + tantalum_alkaline_mixture -> tantalite_fluorine +
        // soda_ash_dust + manganese_dust + stone_dust
        LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("tantalite_fluorine"))
                .inputItems(dust, NiobiumTantalumJointProcessingMaterials.POTASSIUM_FLUORIDE, 16)
                .inputFluids(NiobiumTantalumJointProcessingMaterials.TANTALUM_ALKALINE_MIXTURE.getFluid(12000))
                .outputFluids(NiobiumTantalumJointProcessingMaterials.TANTALITE_FLUORINE.getFluid(4000))
                .outputItems(dust, SodaAsh, 144)
                .outputItems(dust, Manganese, 32)
                .outputItems(dust, Stone, 48)
                .EUt(480).duration(160)
                .save(provider);

        // 3. Potassium fluoride: potassium_dust + fluorine -> potassium_fluoride_dust
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("potassium_fluoride"))
                .inputItems(dust, Potassium)
                .inputFluids(Fluorine.getFluid(1000))
                .outputItems(dust, NiobiumTantalumJointProcessingMaterials.POTASSIUM_FLUORIDE, 2)
                .EUt(32).duration(5)
                .save(provider);

        // 4. Niobium tantalite: chromium_trioxide_dust + ammonia_monohydrate + tantalite_fluorine ->
        // potassium_hydroxide_dust + chromium_dust + ammonium_fluoride + niobium_tantalite
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("niobium_tantalite"))
                .inputItems(dust, ChromiumTrioxide, 8)
                .inputFluids(PlatinumLineMaterials.AmmoniaMonohydrate.getFluid(8000))
                .inputFluids(NiobiumTantalumJointProcessingMaterials.TANTALITE_FLUORINE.getFluid(4000))
                .outputItems(dust, PotassiumHydroxide, 24)
                .outputItems(dust, Chromium, 2)
                .outputFluids(SpecialMaterials.AMMONIUM_FLUORIDE.getFluid(8000))
                .outputFluids(NiobiumTantalumJointProcessingMaterials.NIOBIUM_TANTALITE.getFluid(8000))
                .EUt(480).duration(360)
                .save(provider);

        // 5. Tantalite oxide: niobium_tantalite -> tantalite_oxide_dust + niobium_oxide_dust + water
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("tantalite_oxide_dust"))
                .inputFluids(NiobiumTantalumJointProcessingMaterials.NIOBIUM_TANTALITE.getFluid(2000))
                .outputItems(dust, NiobiumTantalumJointProcessingMaterials.TANTALITE_OXIDE, 56)
                .outputItems(dust, NiobiumTantalumJointProcessingMaterials.NIOBIUM_OXIDE, 63)
                .outputFluids(Water.getFluid(16000))
                .outputFluids(Water.getFluid(16000))
                .EUt(480).duration(480)
                .save(provider);

        // 6. Niobium dust: niobium_oxide_dust + hematite_dust + aluminium_dust -> niobium_dust + iron_dust +
        // alumina_dust
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("niobium_dust"))
                .inputItems(dust, NiobiumTantalumJointProcessingMaterials.NIOBIUM_OXIDE, 21)
                .inputItems(dust, Hematite, 5)
                .inputItems(dust, Aluminium, 12)
                .outputItems(dust, Niobium, 6)
                .outputItems(dust, Iron, 2)
                .outputItems(dust, Alumina, 30)
                .EUt(480).duration(200)
                .blastFurnaceTemp(2500)
                .save(provider);

        // 7. Tantalum dust: tantalite_oxide_dust + hematite_dust + aluminium_dust -> tantalum_dust + iron_dust +
        // alumina_dust
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("tantalum_dust"))
                .inputItems(dust, NiobiumTantalumJointProcessingMaterials.TANTALITE_OXIDE, 21)
                .inputItems(dust, Hematite, 5)
                .inputItems(dust, Aluminium, 12)
                .outputItems(dust, Tantalum, 6)
                .outputItems(dust, Iron, 2)
                .outputItems(dust, Alumina, 30)
                .EUt(480).duration(200)
                .blastFurnaceTemp(2500)
                .save(provider);

        // 8. Ammonia from ammonium fluoride: ammonium_fluoride -> ammonia + fluorine
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("ammonia"))
                .inputFluids(SpecialMaterials.AMMONIUM_FLUORIDE.getFluid(1000))
                .outputFluids(Ammonia.getFluid(1000))
                .outputFluids(Fluorine.getFluid(1000))
                .EUt(24).duration(80)
                .save(provider);

        // 9. Hematite: iron_dust + oxygen -> hematite_dust
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("hematite_dust"))
                .inputItems(dust, Iron, 4)
                .inputFluids(Oxygen.getFluid(6000))
                .outputItems(dust, Hematite, 10)
                .EUt(24).duration(80)
                .save(provider);
    }
}
