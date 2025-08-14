package io.github.cpearl0.ctnhcore.data.recipe.multiblock;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;
public class Sinope_recipes {
    public static void init(Consumer<FinishedRecipe> provider){
        CTNHRecipeTypes.SINOPE.recipeBuilder("impure_enriched_naquadah_solution1")
                .inputFluids(GTMaterials.AcidicNaquadriaSolution.getFluid(3000))
                .outputFluids(GTMaterials.NaquadriaWaste.getFluid(1000))
                .outputFluids(GTMaterials.ImpureEnrichedNaquadahSolution.getFluid(1000))
                .EUt(GTValues.VA[GTValues.HV])
                .duration(1000)
                //.blastFurnaceTemp(1280)
                .save(provider);
        CTNHRecipeTypes.SINOPE.recipeBuilder("enriched_naquadah_residue_solution1")
                .inputItems(TagPrefix.dust, CTNHMaterials.GoldTrifluoride, 4)
                .inputFluids(CTNHMaterials.XenonHexafluoroEnrichedNaquadate.getFluid(1000))
                .inputFluids(GTMaterials.FluoroantimonicAcid.getFluid(1000))
                .inputFluids(GTMaterials.Hydrogen.getFluid(9000))
                .outputFluids(GTMaterials.EnrichedNaquadahSolution.getFluid(1000))
                .outputFluids(CTNHMaterials.EnrichedNaquadahResidueSolution.getFluid(1000))
                .outputFluids(GTMaterials.HydrofluoricAcid.getFluid(8000))
                .EUt(GTValues.VA[GTValues.LuV])
                .duration(1200)
                .save(provider);
        CTNHRecipeTypes.SINOPE.recipeBuilder("rp_1_mixed_fuel1")
                .inputFluids(GTMaterials.Oxygen.getFluid(2000))
                .inputFluids(CTNHMaterials.RP1RocketFuel.getFluid(500))
                .outputFluids(CTNHMaterials.RP1.getFluid(1000))
                .circuitMeta(1)
                .duration(300).EUt(GTValues.VA[GTValues.HV]).save(provider);
        CTNHRecipeTypes.SINOPE.recipeBuilder("dense_hydrazine_mixed_fuel1")
                .inputFluids(GTMaterials.Methanol.getFluid(6000))
                .inputFluids(CTNHMaterials.Hydrazine.getFluid(4000))
                .outputFluids(CTNHMaterials.DenseHydrazineMixedFuel.getFluid(10000))
                .circuitMeta(2)
                .duration(600).EUt(GTValues.VA[GTValues.HV]).save(provider);
        CTNHRecipeTypes.SINOPE.recipeBuilder("hydrazine1")
                .inputFluids(GTMaterials.Ammonia.getFluid(1000))
                .inputFluids(GTMaterials.HydrogenPeroxide.getFluid(1000))
                .outputFluids(CTNHMaterials.Hydrazine.getFluid(1000))
                .circuitMeta(2)
                .duration(200).EUt(GTValues.VA[GTValues.HV]).save(provider);
        CTNHRecipeTypes.SINOPE.recipeBuilder("hydrogen_peroxide_oxygen1")
                .inputFluids(GTMaterials.Oxygen.getFluid(10000))
                .inputFluids(CTNHMaterials.EthylAnthraHydroQuinone.getFluid(5000))
                .inputFluids(CTNHMaterials.Anthracene.getFluid(50))
                .outputFluids(GTMaterials.HydrogenPeroxide.getFluid(5000))
                .outputFluids(CTNHMaterials.EthylAnthraQuinone.getFluid(5000))
                .circuitMeta(4)
                .duration(100).EUt(GTValues.VA[GTValues.HV]).save(provider);
        CTNHRecipeTypes.SINOPE.recipeBuilder("hydrogen_peroxide_air1")
                .inputFluids(GTMaterials.Air.getFluid(20000))
                .inputFluids(CTNHMaterials.EthylAnthraHydroQuinone.getFluid(5000))
                .inputFluids(CTNHMaterials.Anthracene.getFluid(50))
                .outputFluids(GTMaterials.HydrogenPeroxide.getFluid(5000))
                .outputFluids(CTNHMaterials.EthylAnthraQuinone.getFluid(5000))
                .circuitMeta(4)
                .duration(600).EUt(GTValues.VA[GTValues.HV]).save(provider);
        CTNHRecipeTypes.SINOPE.recipeBuilder("ethyl_anthra_quinone1")
                .inputItems(ChemicalHelper.get(TagPrefix.dust, CTNHMaterials.PhthalicAnhydride, 15))
                .inputFluids(GTMaterials.Ethylbenzene.getFluid(1000))
                .outputFluids(CTNHMaterials.EthylAnthraQuinone.getFluid(1000))
                .circuitMeta(4)
                .duration(600).EUt(GTValues.VA[GTValues.MV]).save(provider);
        CTNHRecipeTypes.SINOPE.recipeBuilder("ethyl_anthra_hydro_quinone1")
                .inputFluids(CTNHMaterials.EthylAnthraQuinone.getFluid(1000))
                .inputFluids(GTMaterials.Hydrogen.getFluid(2000))
                .outputFluids(CTNHMaterials.EthylAnthraHydroQuinone.getFluid(1000))
                .circuitMeta(4)
                .duration(800).EUt(GTValues.VA[GTValues.MV]).save(provider);
        CTNHRecipeTypes.SINOPE.recipeBuilder("methyl_hydrazine1")
                .inputItems(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Carbon, 1))
                .inputFluids(GTMaterials.Hydrogen.getFluid(2000))
                .inputFluids(CTNHMaterials.Hydrazine.getFluid(1000))
                .outputFluids(CTNHMaterials.MethylHydrazine.getFluid(1000))
                .circuitMeta(21)
                .duration(1000).EUt(GTValues.VA[GTValues.HV]).save(provider);
        CTNHRecipeTypes.SINOPE.recipeBuilder("methylhydrazine_nitrate_rocket_fuel1")
                .inputFluids(CTNHMaterials.MethylHydrazine.getFluid(2000))
                .inputFluids(GTMaterials.NitricAcid.getFluid(1000))
                .outputFluids(CTNHMaterials.MethylhydrazineNitrateRocketFuel.getFluid(2000))
                .circuitMeta(3)
                .duration(1000).EUt(GTValues.VA[GTValues.HV]).save(provider);

    }
}
