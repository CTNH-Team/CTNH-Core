package io.github.cpearl0.ctnhcore.data.recipe.chain;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import io.github.cpearl0.ctnhcore.CTNHCoreGTAddon;
import io.github.cpearl0.ctnhcore.common.recipe.PlantCasingCondition;
import io.github.cpearl0.ctnhcore.registry.CTNHMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import io.github.cpearl0.ctnhcore.registry.CTNHTagPrefixes;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.GTNNMultiblocks;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.ibm.icu.impl.CurrencyData.provider;
import static twilightforest.init.custom.Restrictions.asStack;

public class FuelChain {
    public static void init(Consumer<FinishedRecipe> provider){

        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("black_matter")
                .inputItems(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Lead, 3))
                .inputItems(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Manganese, 5))
                .inputItems(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Carbon, 12))
                .outputItems(ChemicalHelper.get(TagPrefix.dust, CTNHMaterials.BlackMatter, 20))
                .circuitMeta(13)
                .duration(82).EUt(120).save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("chemical_plant")
                .inputItems(GTMachines.HULL[GTValues.MV].asStack(4))
                .inputItems(ChemicalHelper.get(TagPrefix.plate, GTMaterials.AnnealedCopper, 16))
                .inputItems(ChemicalHelper.get(TagPrefix.pipeLargeFluid, GTMaterials.Polyethylene, 4))
                .inputItems(ChemicalHelper.get(TagPrefix.gear, GTMaterials.Aluminium, 4))
                .inputItems(ChemicalHelper.get(TagPrefix.frameGt, CTNHMaterials.BlackMatter, 4))
                .inputFluids(GTMaterials.BlackSteel.getFluid(1000))
                .outputItems(GTNNMultiblocks.CHEMICAL_PLANT.asStack())
                .circuitMeta(19)
                .duration(120).EUt(120).save(provider);

        CTNHRecipeTypes.CHEMICAL_PLANT_RECIPES.recipeBuilder("rp_1_mixed_fuel")
                .addCondition(new PlantCasingCondition(4))
                .inputFluids(GTMaterials.Oxygen.getFluid(2000))
                .inputFluids(CTNHMaterials.RP1RocketFuel.getFluid(500))
                .outputFluids(CTNHMaterials.RP1.getFluid(1000))
                .circuitMeta(1)
                .duration(15).EUt(480).save(provider);

        GTRecipeTypes.DISTILLERY_RECIPES.recipeBuilder("rp_1_rocket_fuel")
                .inputFluids(CTNHMaterials.Kerosene.getFluid(20))
                .outputFluids(CTNHMaterials.RP1RocketFuel.getFluid(15))
                .circuitMeta(23)
                .duration(5).EUt(120).save(provider);

        GTRecipeTypes.DISTILLERY_RECIPES.recipeBuilder("kerosene")
                .inputFluids(GTMaterials.Diesel.getFluid(250))
                .outputFluids(CTNHMaterials.Kerosene.getFluid(150))
                .circuitMeta(23)
                .duration(16).EUt(120).save(provider);

        CTNHRecipeTypes.CHEMICAL_PLANT_RECIPES.recipeBuilder("dense_hydrazine_mixed_fuel")
                .addCondition(new PlantCasingCondition(5))
                .inputFluids(GTMaterials.Methanol.getFluid(6000))
                .inputFluids(CTNHMaterials.Hydrazine.getFluid(4000))
                .outputFluids(CTNHMaterials.DenseHydrazineMixedFuel.getFluid(10000))
                .circuitMeta(2)
                .duration(30).EUt(480).save(provider);

        CTNHRecipeTypes.CHEMICAL_PLANT_RECIPES.recipeBuilder("hydrazine")
                .addCondition(new PlantCasingCondition(2))
                .inputFluids(GTMaterials.Ammonia.getFluid(1000))
                .inputFluids(GTMaterials.HydrogenPeroxide.getFluid(1000))
                .outputFluids(CTNHMaterials.Hydrazine.getFluid(1000))
                .circuitMeta(2)
                .duration(10).EUt(480).save(provider);

        CTNHRecipeTypes.CHEMICAL_PLANT_RECIPES.recipeBuilder("hydrogen_peroxide_oxygen")
                .addCondition(new PlantCasingCondition(2))
                .inputFluids(GTMaterials.Oxygen.getFluid(10000))
                .inputFluids(CTNHMaterials.EthylAnthraHydroQuinone.getFluid(5000))
                .inputFluids(CTNHMaterials.Anthracene.getFluid(50))
                .outputFluids(GTMaterials.HydrogenPeroxide.getFluid(5000))
                .outputFluids(CTNHMaterials.EthylAnthraQuinone.getFluid(5000))
                .circuitMeta(4)
                .duration(5).EUt(480).save(provider);

        CTNHRecipeTypes.CHEMICAL_PLANT_RECIPES.recipeBuilder("hydrogen_peroxide_air")
                .addCondition(new PlantCasingCondition(2))
                .inputFluids(GTMaterials.Air.getFluid(20000))
                .inputFluids(CTNHMaterials.EthylAnthraHydroQuinone.getFluid(5000))
                .inputFluids(CTNHMaterials.Anthracene.getFluid(50))
                .outputFluids(GTMaterials.HydrogenPeroxide.getFluid(5000))
                .outputFluids(CTNHMaterials.EthylAnthraQuinone.getFluid(5000))
                .circuitMeta(4)
                .duration(30).EUt(480).save(provider);

        CTNHRecipeTypes.CHEMICAL_PLANT_RECIPES.recipeBuilder("ethyl_anthra_quinone")
                .addCondition(new PlantCasingCondition(3))
                .inputItems(ChemicalHelper.get(TagPrefix.dust, CTNHMaterials.PhthalicAnhydride, 15))
                .inputFluids(GTMaterials.Ethylbenzene.getFluid(1000))
                .outputFluids(CTNHMaterials.EthylAnthraQuinone.getFluid(1000))
                .circuitMeta(4)
                .duration(15).EUt(120).save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("phthalic_anhydride")
                .inputItems(ChemicalHelper.get(TagPrefix.dustSmall, CTNHMaterials.VanadiumPentoxide, 1))
                .inputFluids(GTMaterials.Naphthalene.getFluid(1000))
                .inputFluids(GTMaterials.Air.getFluid(1000))
                .outputItems(ChemicalHelper.get(TagPrefix.dust, CTNHMaterials.PhthalicAnhydride, 15))
                .duration(40).EUt(1960).save(provider);

        GTRecipeTypes.BLAST_RECIPES.recipeBuilder("vanadium_pentoxide")
                .inputItems(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Vanadium, 2))
                .inputFluids(GTMaterials.Oxygen.getFluid(5000))
                .outputItems(ChemicalHelper.get(TagPrefix.dust, CTNHMaterials.VanadiumPentoxide, 7))
                .circuitMeta(24)
                .blastFurnaceTemp(2500)
                .duration(10).EUt(120).save(provider);

        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("orange_metal_catalyst")
                .inputItems(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Vanadium, 1))
                .inputItems(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Palladium, 1))
                .outputItems(ChemicalHelper.get(CTNHTagPrefixes.catalyst, CTNHMaterials.OrangeMetal, 1))
                .circuitMeta(32)
                .duration(8).EUt(480).save(provider);

        CTNHRecipeTypes.CHEMICAL_PLANT_RECIPES.recipeBuilder("ethyl_anthra_hydro_quinone")
                .addCondition(new PlantCasingCondition(3))
                .inputFluids(CTNHMaterials.EthylAnthraQuinone.getFluid(1000))
                .inputFluids(GTMaterials.Hydrogen.getFluid(2000))
                .inputItems(ChemicalHelper.get(CTNHTagPrefixes.catalyst, CTNHMaterials.OrangeMetal, 1))
                .outputFluids(CTNHMaterials.EthylAnthraHydroQuinone.getFluid(1000))
                .circuitMeta(4)
                .duration(40).EUt(120).save(provider);

        GTRecipeTypes.DISTILLATION_RECIPES.recipeBuilder("distill_coal_tar")
                .inputFluids(GTMaterials.CoalTar.getFluid(1000))
                .outputItems(TagPrefix.dustSmall, GTMaterials.Coke)
                .outputFluids(GTMaterials.Naphthalene.getFluid(400))
                .outputFluids(GTMaterials.HydrogenSulfide.getFluid(300))
                .outputFluids(GTMaterials.Creosote.getFluid(200))
                .outputFluids(GTMaterials.Phenol.getFluid(100))
                .outputFluids(CTNHMaterials.Anthracene.getFluid(50))
                .duration(80).EUt(120)
                .save(provider);

        CTNHRecipeTypes.CHEMICAL_PLANT_RECIPES.recipeBuilder("methyl_hydrazine")
                .addCondition(new PlantCasingCondition(3))
                .inputItems(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Carbon, 1))
                .inputFluids(GTMaterials.Hydrogen.getFluid(2000))
                .inputFluids(CTNHMaterials.Hydrazine.getFluid(1000))
                .outputFluids(CTNHMaterials.MethylHydrazine.getFluid(1000))
                .circuitMeta(21)
                .duration(48).EUt(480).save(provider);

        CTNHRecipeTypes.CHEMICAL_PLANT_RECIPES.recipeBuilder("methylhydrazine_nitrate_rocket_fuel")
                .addCondition(new PlantCasingCondition(4))
                .inputFluids(CTNHMaterials.MethylHydrazine.getFluid(2000))
                .inputFluids(GTMaterials.NitricAcid.getFluid(1000))
                .outputFluids(CTNHMaterials.MethylhydrazineNitrateRocketFuel.getFluid(2000))
                .circuitMeta(3)
                .duration(45).EUt(480).save(provider);

        GTRecipeTypes.DISTILLATION_RECIPES.recipeBuilder("distill_coal_tar")
                .inputFluids(GTMaterials.CoalTar.getFluid(1000))
                .outputItems(TagPrefix.dustSmall, GTMaterials.Coke)
                .outputFluids(GTMaterials.Naphthalene.getFluid(400))
                .outputFluids(GTMaterials.HydrogenSulfide.getFluid(300))
                .outputFluids(GTMaterials.Creosote.getFluid(200))
                .outputFluids(GTMaterials.Phenol.getFluid(100))
                .outputFluids(CTNHMaterials.Anthracene.getFluid(50))
                .duration(80).EUt(120)
                .save(provider);

        CTNHRecipeTypes.CHEMICAL_PLANT_RECIPES.recipeBuilder("methyl_hydrazine")
                .addCondition(new PlantCasingCondition(3))
                .inputItems(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Carbon, 1))
                .inputFluids(GTMaterials.Hydrogen.getFluid(2000))
                .inputFluids(CTNHMaterials.Hydrazine.getFluid(1000))
                .outputFluids(CTNHMaterials.MethylHydrazine.getFluid(1000))
                .circuitMeta(21)
                .duration(48).EUt(480).save(provider);

        CTNHRecipeTypes.CHEMICAL_PLANT_RECIPES.recipeBuilder("methylhydrazine_nitrate_rocket_fuel")
                .addCondition(new PlantCasingCondition(4))
                .inputFluids(CTNHMaterials.MethylHydrazine.getFluid(2000))
                .inputFluids(GTMaterials.NitricAcid.getFluid(1000))
                .outputFluids(CTNHMaterials.MethylhydrazineNitrateRocketFuel.getFluid(2000))
                .circuitMeta(3)
                .duration(45).EUt(480).save(provider);

        CTNHRecipeTypes.ROCKET_ENGINE_RECIPES.recipeBuilder("rp1fuel_ele")
                .inputFluids(CTNHMaterials.RP1.getFluid(4))
                .EUt(-GTValues.V[GTValues.EV])
                .duration(3)
                .save(provider);
        CTNHRecipeTypes.ROCKET_ENGINE_RECIPES.recipeBuilder("dense_hydrazine_mixed_fuel")
                .inputFluids(CTNHMaterials.DenseHydrazineMixedFuel.getFluid(2))
                .EUt(-GTValues.V[GTValues.EV])
                .duration(3)
                .save(provider);

        CTNHRecipeTypes.ROCKET_ENGINE_RECIPES.recipeBuilder("methylhydrazine_nitrate_rocket_fuel")
                .inputFluids(CTNHMaterials.MethylhydrazineNitrateRocketFuel.getFluid(1))
                .EUt(-GTValues.V[GTValues.EV])
                .duration(3)
                .save(provider);

        CTNHRecipeTypes.ROCKET_ENGINE_RECIPES.recipeBuilder("udmh_rocket_fuel")
                .inputFluids(CTNHMaterials.UDMHRocketFuel.getFluid(1))
                .EUt(-GTValues.V[GTValues.EV])
                .duration(6)
                .save(provider);
//        VanillaRecipeHelper.addShapedRecipe(
//                provider, true, "rocket_engine_ev", GTNNMultiblocks.Rocket_Engine[GTValues.EV]
//                .asStack(),
//                "ABA", "CDC", "EFE",
//                'A', GTItems.ELECTRIC_PISTON_EV,
//                'B', CustomTags.EV_CIRCUITS,
//                'C', GTItems.ELECTRIC_MOTOR_EV,
//                'D', GTMachines.HULL[GTValues.EV].asStack(),
//                'E', TagPrefix.gear, GTMaterials.TungstenSteel,
//                'F', TagPrefix.cableGtDouble, GTMaterials.Aluminium
//        );
//        VanillaRecipeHelper.addShapedRecipe(
//                provider, true, "rocket_engine_iv", GTNNMultiblocks.Rocket_Engine[GTValues.IV]
//                .asStack(),
//                "ABA", "CDC", "EFE",
//                'A', GTItems.ELECTRIC_PISTON_IV,
//                'B', CustomTags.IV_CIRCUITS,
//                'C', GTItems.ELECTRIC_MOTOR_IV,
//                'D', GTMachines.HULL[GTValues.IV].asStack(),
//                'E', TagPrefix.gear, GTMaterials.Titanium,
//                'F', TagPrefix.cableGtDouble, GTMaterials.Platinum
//        );
//        VanillaRecipeHelper.addShapedRecipe(
//                provider, true, "rocket_engine_luv", GTNNMultiblocks.Rocket_Engine[GTValues.LuV]
//                .asStack(),
//                "ABA", "CDC", "EFE",
//                'A', GTItems.ELECTRIC_PISTON_LuV,
//                'B', CustomTags.LuV_CIRCUITS,
//                'C', GTItems.ELECTRIC_MOTOR_LuV,
//                'D', GTMachines.HULL[GTValues.LuV].asStack(),
//                'E', TagPrefix.gear, GTMaterials.Zeron100,
//                'F', TagPrefix.cableGtDouble, GTMaterials.Tungsten
//        );
    }
}
