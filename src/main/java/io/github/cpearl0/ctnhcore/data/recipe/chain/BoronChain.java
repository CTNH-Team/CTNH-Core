package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.common.data.GTRecipeTypes;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Boron;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Borax;
import static com.gregtechceu.gtceu.common.data.GTMaterials.CarbonDioxide;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Chlorine;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Hematite;
import static com.gregtechceu.gtceu.common.data.GTMaterials.HydrochloricAcid;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Iron3Chloride;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Magnesite;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Magnesia;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Magnesium;
import static com.gregtechceu.gtceu.common.data.GTMaterials.MagnesiumChloride;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Olivine;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Oxygen;
import static com.gregtechceu.gtceu.common.data.GTMaterials.SaltWater;
import static com.gregtechceu.gtceu.common.data.GTMaterials.SiliconDioxide;
import static com.gregtechceu.gtceu.common.data.GTMaterials.SodiumHydroxide;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Water;
import static io.github.cpearl0.ctnhcore.data.materials.BoronChainMaterials.LEACHED_BORAX_SOLUTION;
import static io.github.cpearl0.ctnhcore.data.materials.BoronChainMaterials.BORON_TRIOXIDE;
import static io.github.cpearl0.ctnhcore.data.materials.BoronChainMaterials.LEACHED_OLIVINE_SOLUTION;
import static io.github.cpearl0.ctnhcore.data.materials.BoronChainMaterials.MAGNESIUM_HYDROXIDE;
import static io.github.cpearl0.ctnhcore.data.materials.BoronChainMaterials.IRON_HYDROXIDE;
import static io.github.cpearl0.ctnhcore.data.materials.NewExplosivesProductionMaterials.BORIC_ACID;

public class BoronChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        GTRecipeTypes.CHEMICAL_BATH_RECIPES.recipeBuilder(CTNHCore.id("borax_acid_leaching"))// 硼砂酸浸洗
                .inputItems(dust, Borax, 23)
                .inputFluids(HydrochloricAcid.getFluid(2000))
                .outputFluids(LEACHED_BORAX_SOLUTION.getFluid(1000))
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.DISTILLATION_RECIPES.recipeBuilder(CTNHCore.id("borax_acid_solution_distillation"))// 蒸馏硼砂酸溶液
                .inputFluids(LEACHED_BORAX_SOLUTION.getFluid(1000))
                .outputItems(dust, BORIC_ACID, 28)
                .outputFluids(SaltWater.getFluid(2000))
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.BLAST_RECIPES.recipeBuilder(CTNHCore.id("boric_acid_to_boron_trioxide"))// 焙烧硼酸制三氧化二硼
                .inputItems(dust, BORIC_ACID, 14)// 2mol 硼酸(H3BO3, 7原子/mol)
                .outputItems(dust, BORON_TRIOXIDE, 5)// 1mol 三氧化二硼(B2O3, 5原子/mol)
                .outputFluids(Water.getFluid(3000))
                .blastFurnaceTemp(1000)
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.BLAST_RECIPES.recipeBuilder(CTNHCore.id("boron_trioxide_reduction"))// 镁热还原三氧化二硼
                .inputItems(dust, BORON_TRIOXIDE, 5)// 1mol 三氧化二硼(B2O3, 5原子/mol)
                .inputItems(dust, Magnesium, 3)// 3mol 镁
                .outputItems(dust, Boron, 2)// 2mol 硼
                .outputItems(dust, Magnesia, 6)// 3mol 氧化镁(MgO, 2原子/mol)
                .blastFurnaceTemp(1000)
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.BLAST_RECIPES.recipeBuilder(CTNHCore.id("magnesite_roasting"))// 煅烧菱镁矿
                .inputItems(dust, Magnesite, 5)// 1mol 菱镁矿(MgCO3, 5原子/mol)
                .outputItems(dust, Magnesia, 2)// 1mol 氧化镁(MgO, 2原子/mol)
                .outputFluids(CarbonDioxide.getFluid(1000))// 1mol 二氧化碳
                .blastFurnaceTemp(1000)
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("magnesite_hydrochloric_acid_leaching"))// 盐酸浸取菱镁矿
                .inputItems(dust, Magnesite, 5)// 1mol 菱镁矿(MgCO3, 5原子/mol)
                .inputFluids(HydrochloricAcid.getFluid(2000))// 2mol 盐酸
                .outputItems(dust, MagnesiumChloride, 3)// 1mol 氯化镁(MgCl2, 3原子/mol)
                .outputFluids(Water.getFluid(1000))// 1mol 水
                .outputFluids(CarbonDioxide.getFluid(1000))// 1mol 二氧化碳
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("magnesia_hydrochloric_acid_leaching"))// 盐酸浸取氧化镁
                .inputItems(dust, Magnesia, 2)// 1mol 氧化镁(MgO, 2原子/mol)
                .inputFluids(HydrochloricAcid.getFluid(2000))// 2mol 盐酸
                .outputItems(dust, MagnesiumChloride, 3)// 1mol 氯化镁(MgCl2, 3原子/mol)
                .outputFluids(Water.getFluid(1000))// 1mol 水
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("olivine_acid_leaching"))// 盐酸浸取橄榄石
                .inputItems(dust, Olivine, 16)
                .inputFluids(HydrochloricAcid.getFluid(10000))// 10mol 盐酸
                .inputFluids(Oxygen.getFluid(5000))// 气态氧
                .outputFluids(LEACHED_OLIVINE_SOLUTION.getFluid(2000))
                .outputItems(dust, SiliconDioxide, 12)// 4mol 二氧化硅(SiO2, 3原子/mol)
                .EUt(30).duration(200)
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("leached_olivine_precipitation"))// 碱沉酸浸橄榄石溶液
                .inputFluids(LEACHED_OLIVINE_SOLUTION.getFluid(1000))
                .inputItems(dust, SodiumHydroxide, 15)// 5mol 氢氧化钠(NaOH, 3原子/mol)
                .outputFluids(SaltWater.getFluid(5000))// 5mol 盐水
                .outputItems(dust, MAGNESIUM_HYDROXIDE, 5)// 1mol 氢氧化镁(Mg(OH)2, 5原子/mol)
                .outputItems(dust, IRON_HYDROXIDE, 7)// 1mol 氢氧化铁(Fe(OH)3, 7原子/mol)
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("magnesium_hydroxide_to_chloride"))// 盐酸溶解氢氧化镁
                .inputItems(dust, MAGNESIUM_HYDROXIDE, 5)// 1mol 氢氧化镁
                .inputFluids(HydrochloricAcid.getFluid(2000))// 2mol 盐酸
                .outputItems(dust, MagnesiumChloride, 3)// 1mol 氯化镁(MgCl2, 3原子/mol)
                .outputFluids(Water.getFluid(2000))// 2mol 水
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("iron_hydroxide_to_chloride"))// 盐酸溶解氢氧化铁
                .inputItems(dust, IRON_HYDROXIDE, 7)// 1mol 氢氧化铁
                .inputFluids(HydrochloricAcid.getFluid(3000))// 3mol 盐酸
                .outputFluids(Iron3Chloride.getFluid(1000))// 1mol 氯化铁
                .outputFluids(Water.getFluid(3000))// 3mol 水
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.BLAST_RECIPES.recipeBuilder(CTNHCore.id("magnesium_hydroxide_roasting"))// 煅烧氢氧化镁
                .inputItems(dust, MAGNESIUM_HYDROXIDE, 5)// 1mol 氢氧化镁
                .outputItems(dust, Magnesia, 2)// 1mol 氧化镁(MgO, 2原子/mol)
                .outputFluids(Water.getFluid(1000))// 1mol 水
                .blastFurnaceTemp(1000)
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.BLAST_RECIPES.recipeBuilder(CTNHCore.id("iron_hydroxide_roasting"))// 煅烧氢氧化铁
                .inputItems(dust, IRON_HYDROXIDE, 14)// 2mol 氢氧化铁
                .outputItems(dust, Hematite, 5)// 1mol 赤铁矿(Fe2O3, 5原子/mol)
                .outputFluids(Water.getFluid(3000))// 3mol 水
                .blastFurnaceTemp(1000)
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("magnesium_chloride_electrolysis"))// 电解熔融氯化镁
                .inputItems(dust, MagnesiumChloride, 3)// 1mol 氯化镁(MgCl2, 3原子/mol)
                .outputItems(dust, Magnesium, 1)// 1mol 镁(单原子, 1粉/mol)
                .outputFluids(Chlorine.getFluid(2000))// 2mol 氯(单原子气体)
                .EUt(480).duration(100)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("magnesia_electrolysis"))// 电解熔融氧化镁
                .inputItems(dust, Magnesia, 2)// 1mol 氧化镁(MgO, 2原子/mol)
                .outputItems(dust, Magnesium, 1)// 1mol 镁
                .outputFluids(Oxygen.getFluid(1000))// 1mol 氧(单原子气体)
                .EUt(1920).duration(50)
                .save(provider);
    }
}
