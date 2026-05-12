package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.BedrockMaterials;
import io.github.cpearl0.ctnhcore.data.materials.UncategorizedMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.GTItems;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.nugget;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Neutronium;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.FUSION_RECIPES;
import static io.github.cpearl0.ctnhcore.registry.CTNHItems.*;

public class FuelRefiningChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 柴油
        CTNHRecipeTypes.FUEL_REFINING.recipeBuilder(CTNHCore.id("diesel"))
                .inputFluids(HeavyFuel.getFluid(4000))
                .inputFluids(LightFuel.getFluid(1000))
                .outputFluids(Diesel.getFluid(10000))
                .EUt(1920).duration(50).blastFurnaceTemp(2273)
                .save(provider);

        // 生物柴油 - 鱼油 + 乙醇
        CTNHRecipeTypes.FUEL_REFINING.recipeBuilder(CTNHCore.id("bio_diesel1"))
                .inputItems(dust, SodiumHydroxide, 4)
                .inputFluids(FishOil.getFluid(32000))
                .inputFluids(Ethanol.getFluid(2000))
                .outputFluids(BioDiesel.getFluid(60000))
                .EUt(1920).duration(60).blastFurnaceTemp(2273)
                .save(provider);

        // 生物柴油 - 种子油 + 乙醇
        CTNHRecipeTypes.FUEL_REFINING.recipeBuilder(CTNHCore.id("bio_diesel2"))
                .inputItems(dust, SodiumHydroxide, 4)
                .inputFluids(SeedOil.getFluid(32000))
                .inputFluids(Ethanol.getFluid(2000))
                .outputFluids(BioDiesel.getFluid(60000))
                .EUt(1920).duration(60).blastFurnaceTemp(2273)
                .save(provider);

        // 生物柴油 - 种子油 + 甲醇
        CTNHRecipeTypes.FUEL_REFINING.recipeBuilder(CTNHCore.id("bio_diesel3"))
                .inputItems(dust, SodiumHydroxide, 4)
                .inputFluids(SeedOil.getFluid(32000))
                .inputFluids(Methanol.getFluid(2000))
                .outputFluids(BioDiesel.getFluid(60000))
                .EUt(1920).duration(60).blastFurnaceTemp(2273)
                .save(provider);

        // 生物柴油 - 鱼油 + 甲醇
        CTNHRecipeTypes.FUEL_REFINING.recipeBuilder(CTNHCore.id("bio_diesel4"))
                .inputItems(dust, SodiumHydroxide, 4)
                .inputFluids(FishOil.getFluid(32000))
                .inputFluids(Methanol.getFluid(2000))
                .outputFluids(BioDiesel.getFluid(60000))
                .EUt(1920).duration(60).blastFurnaceTemp(2273)
                .save(provider);

        // 汽油
        CTNHRecipeTypes.FUEL_REFINING.recipeBuilder(CTNHCore.id("gasoline"))
                .inputFluids(Naphtha.getFluid(16000))
                .inputFluids(WoodVinegar.getFluid(1000))
                .outputFluids(Gasoline.getFluid(32000))
                .EUt(1920).duration(60).blastFurnaceTemp(4500)
                .save(provider);

        // 高辛烷值汽油
        CTNHRecipeTypes.FUEL_REFINING.recipeBuilder(CTNHCore.id("high_octane_gasoline"))
                .inputFluids(Nitrogen.getFluid(4000))
                .inputFluids(Hydrogen.getFluid(20000))
                .inputFluids(Gasoline.getFluid(20000))
                .outputFluids(HighOctaneGasoline.getFluid(56000))
                .EUt(1920).duration(80).blastFurnaceTemp(5200)
                .save(provider);

        // 高十六烷值柴油 - 生物柴油
        CTNHRecipeTypes.FUEL_REFINING.recipeBuilder(CTNHCore.id("cetane_boosted_diesel1"))
                .inputFluids(BioDiesel.getFluid(12000))
                .inputFluids(NitricAcid.getFluid(4000))
                .outputFluids(CetaneBoostedDiesel.getFluid(26000))
                .EUt(1920).duration(25).blastFurnaceTemp(4500)
                .save(provider);

        // 高十六烷值柴油 - 柴油
        CTNHRecipeTypes.FUEL_REFINING.recipeBuilder(CTNHCore.id("cetane_boosted_diesel2"))
                .inputFluids(Diesel.getFluid(12000))
                .inputFluids(NitricAcid.getFluid(4000))
                .outputFluids(CetaneBoostedDiesel.getFluid(26000))
                .EUt(1920).duration(25).blastFurnaceTemp(4500)
                .save(provider);

        // 高级线圈处理配方 - 轻油
        CTNHRecipeTypes.FUEL_REFINING.recipeBuilder(CTNHCore.id("high_octane_gasoline1"))
                .circuitMeta(1)
                .inputFluids(OilLight.getFluid(20000))
                .inputFluids(Hydrogen.getFluid(2000))
                .inputFluids(Nitrogen.getFluid(1000))
                .outputFluids(HighOctaneGasoline.getFluid(4000))
                .EUt(6144).duration(15).blastFurnaceTemp(7200)
                .save(provider);

        // 高级线圈处理配方 - 原油
        CTNHRecipeTypes.FUEL_REFINING.recipeBuilder(CTNHCore.id("high_octane_gasoline2"))
                .circuitMeta(2)
                .inputFluids(Oil.getFluid(15000))
                .inputFluids(Hydrogen.getFluid(2000))
                .inputFluids(Nitrogen.getFluid(1000))
                .outputFluids(HighOctaneGasoline.getFluid(8000))
                .EUt(6144).duration(15).blastFurnaceTemp(7200)
                .save(provider);

        // 高级线圈处理配方 - 重油
        CTNHRecipeTypes.FUEL_REFINING.recipeBuilder(CTNHCore.id("high_octane_gasoline3"))
                .circuitMeta(3)
                .inputFluids(RawOil.getFluid(15000))
                .inputFluids(Hydrogen.getFluid(4000))
                .inputFluids(Nitrogen.getFluid(2000))
                .outputFluids(HighOctaneGasoline.getFluid(16000))
                .EUt(24567).duration(80).blastFurnaceTemp(7200)
                .save(provider);

        // 高级线圈处理配方 - 高硫重油
        CTNHRecipeTypes.FUEL_REFINING.recipeBuilder(CTNHCore.id("high_octane_gasoline4"))
                .circuitMeta(4)
                .inputFluids(OilHeavy.getFluid(15000))
                .inputFluids(Hydrogen.getFluid(8000))
                .inputFluids(Nitrogen.getFluid(4000))
                .outputFluids(HighOctaneGasoline.getFluid(24000))
                .EUt(24567).duration(80).blastFurnaceTemp(7200)
                .save(provider);

        // 硅岩燃料精炼 - NQ MK1
        CTNHRecipeTypes.SILICA_ROCK_FUEL_REFINERY.recipeBuilder(CTNHCore.id("naq1"))
                .inputFluids(CTNHMaterials.NaquadahBasedLiquidFuel.getFluid(100))
                .inputFluids(Helium.getFluid(FluidStorageKeys.PLASMA, 100))
                .inputItems(dust, Naquadah, 8)
                .inputItems(dust, Palladium, 4)
                .outputFluids(BedrockMaterials.NQ_FUELMK1.getFluid(500))
                .EUt(21305).duration(300)
                .save(provider);

        // 硅岩燃料精炼 - NQ MK2
        CTNHRecipeTypes.SILICA_ROCK_FUEL_REFINERY.recipeBuilder(CTNHCore.id("naq2"))
                .inputFluids(BedrockMaterials.NQ_FUELMK1.getFluid(300))
                .inputFluids(Oxygen.getFluid(FluidStorageKeys.PLASMA, 100))
                .inputItems(dust, NaquadahEnriched, 8)
                .inputItems(dust, Ruthenium, 4)
                .outputFluids(BedrockMaterials.NQ_FUELMK2.getFluid(200))
                .EUt(393216).duration(450).blastFurnaceTemp(10800)
                .save(provider);

        // 硅岩燃料精炼 - NQ MK3
        CTNHRecipeTypes.SILICA_ROCK_FUEL_REFINERY.recipeBuilder(CTNHCore.id("naq3"))
                .inputFluids(BedrockMaterials.NQ_FUELMK2.getFluid(190))
                .inputFluids(Argon.getFluid(FluidStorageKeys.PLASMA, 100))
                .inputItems(dust, Naquadria, 8)
                .inputItems(dust, Osmium, 4)
                .outputFluids(BedrockMaterials.NQ_FUELMK3.getFluid(170))
                .EUt(393216).duration(600).blastFurnaceTemp(10800)
                .save(provider);

        // 裂解硅岩燃料 - 氡裂解
        CRACKING_RECIPES.recipeBuilder(CTNHCore.id("refuel_radon_cracking_1"))
                .inputFluids(BedrockMaterials.NQ_FUELMK1.getFluid(1000))
                .inputFluids(Radon.getFluid(1000))
                .outputFluids(UncategorizedMaterials.CRACKING_SILICA_ROCK_BASED_FUEL.getFluid(2000))
                .EUt(122330).duration(300)
                .save(provider);

        // 裂解富集硅岩燃料 - 氙裂解
        CRACKING_RECIPES.recipeBuilder(CTNHCore.id("refuel_radon_cracking_2"))
                .inputFluids(BedrockMaterials.NQ_FUELMK2.getFluid(1000))
                .inputFluids(Xenon.getFluid(1000))
                .outputFluids(UncategorizedMaterials.CRACKING_ENRICHED_SILICA_ROCK_BASED_FUEL.getFluid(2000))
                .EUt(122330).duration(300)
                .save(provider);

        // 裂解硅岩燃料蒸馏
        DISTILLATION_RECIPES.recipeBuilder(CTNHCore.id("cracking_fix_1"))
                .inputFluids(UncategorizedMaterials.CRACKING_SILICA_ROCK_BASED_FUEL.getFluid(2000))
                .outputFluids(BedrockMaterials.NQ_FUELMK2.getFluid(200))
                .outputFluids(BedrockMaterials.NQ_FUELMK3.getFluid(50))
                .outputFluids(Radon.getFluid(750))
                .outputFluids(Fluorine.getFluid(1000))
                .outputItems(ChemicalHelper.get(nugget, Naquadah, 8))
                .EUt(21430).duration(100)
                .save(provider);

        // 裂解富集硅岩燃料蒸馏
        DISTILLATION_RECIPES.recipeBuilder(CTNHCore.id("cracking_fix_2"))
                .inputFluids(UncategorizedMaterials.CRACKING_ENRICHED_SILICA_ROCK_BASED_FUEL.getFluid(2000))
                .outputFluids(BedrockMaterials.NQ_FUELMK3.getFluid(300))
                .outputFluids(BedrockMaterials.NQ_FUELMK1.getFluid(550))
                .outputFluids(Xenon.getFluid(750))
                .outputFluids(Fluorine.getFluid(3000))
                .outputItems(ChemicalHelper.get(nugget, NaquadahEnriched, 8))
                .EUt(21430).duration(100)
                .save(provider);

        // 聚变反应堆 - 充能硅岩燃料 MK-I
        FUSION_RECIPES.recipeBuilder(CTNHCore.id("charged_silica_rock-i"))
                .inputFluids(BedrockMaterials.NQ_FUELMK1.getFluid(100))
                .inputFluids(Nitrogen.getFluid(FluidStorageKeys.PLASMA, 200))
                .outputFluids(UncategorizedMaterials.CHARGED_SILICA_ROCK_BASED_FLUID_FUEL_MK_I.getFluid(100))
                .EUt(8192).fusionStartEU(300000000).duration(50)
                .save(provider);

        // 聚变反应堆 - 充能硅岩燃料 MK-II
        FUSION_RECIPES.recipeBuilder(CTNHCore.id("charged_silica_rock-ii"))
                .inputFluids(BedrockMaterials.NQ_FUELMK2.getFluid(100))
                .inputFluids(Iron.getFluid(FluidStorageKeys.PLASMA, 200))
                .outputFluids(UncategorizedMaterials.CHARGED_SILICA_ROCK_BASED_FLUID_FUEL_MK_II.getFluid(100))
                .EUt(24102).fusionStartEU(310000000).duration(50)
                .save(provider);

        // 硅岩燃料精炼 - 充能硅岩燃料 MK-I
        CTNHRecipeTypes.SILICA_ROCK_FUEL_REFINERY.recipeBuilder(CTNHCore.id("charged_silica_rock-i_1"))
                .inputItems(dust, Sodium, 64)
                .inputItems(GTItems.GELLED_TOLUENE.asStack(64))
                .inputFluids(BedrockMaterials.NQ_FUELMK1.getFluid(100))
                .inputFluids(Nitrogen.getFluid(FluidStorageKeys.PLASMA, 100))
                .outputFluids(UncategorizedMaterials.CHARGED_SILICA_ROCK_BASED_FLUID_FUEL_MK_I.getFluid(200))
                .EUt(24102).duration(100)
                .save(provider);

        // 硅岩燃料精炼 - 充能硅岩燃料 MK-II（从 MK-I 升级）
        CTNHRecipeTypes.SILICA_ROCK_FUEL_REFINERY.recipeBuilder(CTNHCore.id("charged_silica_rock-ii_1"))
                .inputItems(dust, Potassium, 64)
                .inputItems(GTItems.GELLED_TOLUENE.asStack(64))
                .inputFluids(BedrockMaterials.NQ_FUELMK2.getFluid(100))
                .inputFluids(Iron.getFluid(FluidStorageKeys.PLASMA, 100))
                .outputFluids(UncategorizedMaterials.CHARGED_SILICA_ROCK_BASED_FLUID_FUEL_MK_II.getFluid(200))
                .EUt(122330).duration(100)
                .save(provider);

        // 硅岩燃料精炼 - 充能硅岩燃料 MK-II（从 MK-I 升级）
        CTNHRecipeTypes.SILICA_ROCK_FUEL_REFINERY.recipeBuilder(CTNHCore.id("charged_silica_rock-ii_2"))
                .inputItems(dust, Potassium, 64)
                .inputItems(GTItems.GELLED_TOLUENE.asStack(64))
                .inputFluids(UncategorizedMaterials.CHARGED_SILICA_ROCK_BASED_FLUID_FUEL_MK_I.getFluid(125))
                .inputFluids(Iron.getFluid(FluidStorageKeys.PLASMA, 100))
                .outputFluids(UncategorizedMaterials.CHARGED_SILICA_ROCK_BASED_FLUID_FUEL_MK_II.getFluid(200))
                .EUt(122330).duration(100)
                .save(provider);

        // 硅岩燃料精炼 - 充能硅岩燃料 MK-III（从 NQ MK3）
        CTNHRecipeTypes.SILICA_ROCK_FUEL_REFINERY.recipeBuilder(CTNHCore.id("charged_silica_rock-iii"))
                .inputItems(GTItems.GELLED_TOLUENE.asStack(64))
                .inputItems(EnrichedUranium.asStack(2))
                .inputItems(dust, Neutronium, 4)
                .inputFluids(BedrockMaterials.NQ_FUELMK3.getFluid(100))
                .inputFluids(Radon.getFluid(1000))
                .inputFluids(Nickel.getFluid(FluidStorageKeys.PLASMA, 1000))
                .outputFluids(UncategorizedMaterials.CHARGED_SILICA_ROCK_BASED_FLUID_FUEL_MK_III.getFluid(125))
                .EUt(2000000).duration(100)
                .save(provider);

        // 硅岩燃料精炼 - 充能硅岩燃料 MK-III（从 MK-II 升级）
        CTNHRecipeTypes.SILICA_ROCK_FUEL_REFINERY.recipeBuilder(CTNHCore.id("charged_silica_rock-iii_1"))
                .inputItems(GTItems.GELLED_TOLUENE.asStack(64))
                .inputItems(EnrichedUranium.asStack(2))
                .inputItems(dust, Neutronium, 4)
                .inputFluids(UncategorizedMaterials.CHARGED_SILICA_ROCK_BASED_FLUID_FUEL_MK_II.getFluid(100))
                .inputFluids(Radon.getFluid(1000))
                .inputFluids(Nickel.getFluid(FluidStorageKeys.PLASMA, 1000))
                .outputFluids(UncategorizedMaterials.CHARGED_SILICA_ROCK_BASED_FLUID_FUEL_MK_III.getFluid(125))
                .EUt(2000000).duration(100)
                .save(provider);

        // 超级燃料 MK-I
        CTNHRecipeTypes.SILICA_ROCK_FUEL_REFINERY.recipeBuilder(CTNHCore.id("super_fuel_mki_1"))
                .inputItems(EnrichedThorium.asStack(3))
                .inputItems(EnrichedUranium.asStack(2))
                .inputItems(EnrichedPlutonium.asStack(1))
                .inputFluids(UncategorizedMaterials.CHARGED_SILICA_ROCK_BASED_FLUID_FUEL_MK_III.getFluid(500))
                .inputFluids(UncategorizedMaterials.HIGH_ENERGY_FUEL.getFluid(500))
                .inputFluids(Neutronium.getFluid(1000))
                .outputFluids(BedrockMaterials.SUPERFUELMK1.getFluid(250))
                .EUt(8100000).duration(300)
                .save(provider);

        // 高能燃料
        CTNHRecipeTypes.SILICA_ROCK_FUEL_REFINERY.recipeBuilder(CTNHCore.id("high_fuel_too"))
                .inputFluids(CTNHMaterials.ThoriumBasedLiquidFuelExcited.getFluid(1000))
                .inputFluids(CTNHMaterials.UraniumBasedLiquidFuelExcited.getFluid(1000))
                .inputFluids(CTNHMaterials.PlutoniumBasedLiquidFuelExcited.getFluid(1000))
                .inputItems(dust, BedrockMaterials.BOUNDLESS)
                .outputFluids(UncategorizedMaterials.HIGH_ENERGY_FUEL.getFluid(3000))
                .EUt(2000000).duration(300)
                .save(provider);
    }
}
