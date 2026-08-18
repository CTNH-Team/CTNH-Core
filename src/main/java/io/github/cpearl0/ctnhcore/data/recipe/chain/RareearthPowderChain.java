package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.rareearth.ProcessControlProfile;
import io.github.cpearl0.ctnhcore.data.materials.RareEarthMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;

/**
 * 稀土矿粉线（重稀土，最长）：盐酸浸出时钪低 pH 水解最先离队；镱变价还原、钇溶剂萃取、
 * 铥真空挥发 + 冷凝分馏依次离队，钬、铒、镥离子交换递进分离。每种元素再走
 * 沉淀 → 焙烧 → 复溶结晶 → 二次精制 → 还原五步提纯，镥在全链结尾收尾。
 */
public class RareearthPowderChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 盐酸浸出：主体进入重稀土氯化物溶液，钪水解沉淀最先离队
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_powder_acid_leach"))
                .inputItems(dust, RareEarth, 14)
                .inputFluids(HydrochloricAcid.getFluid(56000))
                .outputFluids(RareEarthMaterials.HEAVY_RARE_EARTH_CHLORIDE_SOLUTION.getFluid(12000))
                .outputItems(dust, RareEarthMaterials.SCANDIUM_RESIDUE, 2)
                .EUt(1920)
                .duration(720)
                .save(provider);

        // ── 钪（产线开头）：焙烧 → 复溶结晶 → 二次精制 → 还原 ──────
        RareEarthMaterials.CrudeRareEarth sc = RareEarthMaterials.CRUDE_RARE_EARTHS.get(Scandium);
        RareearthChain.addHydroxideRoasting(provider, "scandium", RareEarthMaterials.SCANDIUM_RESIDUE, sc.crudeOxide(),
                1920, 1200);
        RareearthChain.addChlorideRecrystallization(provider, "scandium", sc.crudeOxide(), sc.chloride(), 1920, 1440);
        RareearthChain.addChlorideRefinement(provider, "scandium", sc.chloride(), sc.refinedChloride(), 7680, 1800);
        RareearthChain.addMetalReduction(provider, "scandium", sc.refinedChloride(), Scandium, 40, 440, 7680, 720);

        // ── 镱：变价还原 → 酸化 → 沉淀 → 焙烧 → 复溶结晶 → 二次精制 → 还原 ──
        RareEarthMaterials.CrudeRareEarth yb = RareEarthMaterials.CRUDE_RARE_EARTHS.get(Ytterbium);
        CTNHRecipeTypes.REDUCTION_PRECIPITATION.recipeBuilder(CTNHCore.id("ytterbium_selective_reduction"))
                .inputFluids(RareEarthMaterials.HEAVY_RARE_EARTH_CHLORIDE_SOLUTION.getFluid(12000))
                .inputItems(dust, Zinc, 1)
                .inputFluids(DistilledWater.getFluid(500))
                .outputItems(dust, RareEarthMaterials.YTTERBIUM_PRECIPITATE, 2)
                .outputFluids(RareEarthMaterials.YTTERBIUM_FREE_HEAVY_SOLUTION.getFluid(10000))
                .outputItems(dust, RareEarthMaterials.ZINC_CHLORIDE, 1)
                .outputFluids(DistilledWater.getFluid(500))
                .addData(ProcessControlProfile.PRIMARY_RECIPE_DATA, 0)
                .addData(ProcessControlProfile.SECONDARY_RECIPE_DATA, 400)
                .EUt(7680)
                .duration(600)
                .save(provider);

        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("ytterbium_crude_solution"))
                .inputItems(dust, RareEarthMaterials.YTTERBIUM_PRECIPITATE, 2)
                .inputFluids(HydrochloricAcid.getFluid(2000))
                .outputFluids(yb.crudeSolution().getFluid(2000))
                .outputFluids(Hydrogen.getFluid(1000))
                .EUt(1920)
                .duration(480)
                .save(provider);

        RareearthChain.addHydroxidePrecipitation(provider, "ytterbium", yb.crudeSolution(), yb.crudeHydroxide(), 480,
                900);
        RareearthChain.addHydroxideRoasting(provider, "ytterbium", yb.crudeHydroxide(), yb.crudeOxide(), 1920, 1200);
        RareearthChain.addChlorideRecrystallization(provider, "ytterbium", yb.crudeOxide(), yb.chloride(), 1920, 1440);
        RareearthChain.addChlorideRefinement(provider, "ytterbium", yb.chloride(), yb.refinedChloride(), 7680, 1800);
        RareearthChain.addMetalReduction(provider, "ytterbium", yb.refinedChloride(), Ytterbium, 0, 400, 7680, 720);

        // ── 钇：萃取 → 反萃 → 沉淀 → 焙烧 → 复溶结晶 → 二次精制 → 还原 ──
        CTNHRecipeTypes.SOLVENT_EXTRACTION.recipeBuilder(CTNHCore.id("yttrium_extraction"))
                .inputFluids(RareEarthMaterials.YTTERBIUM_FREE_HEAVY_SOLUTION.getFluid(10000))
                .inputFluids(Benzene.getFluid(1000))
                .inputFluids(CTNHMaterials.Kerosene.getFluid(1000))
                .outputFluids(RareEarthMaterials.YTTRIUM_LOADED_ORGANIC.getFluid(2000))
                .outputFluids(RareEarthMaterials.HOLMIUM_ERBIUM_THULIUM_LUTETIUM_RAFFINATE.getFluid(8000))
                .addData(ProcessControlProfile.PRIMARY_RECIPE_DATA, 380)
                .addData(ProcessControlProfile.SECONDARY_RECIPE_DATA, 90)
                .EUt(7680)
                .duration(1200)
                .save(provider);

        CTNHRecipeTypes.SOLVENT_EXTRACTION.recipeBuilder(CTNHCore.id("yttrium_stripping"))
                .inputFluids(RareEarthMaterials.YTTRIUM_LOADED_ORGANIC.getFluid(2000))
                .inputFluids(HydrochloricAcid.getFluid(1000))
                .outputFluids(RareEarthMaterials.CRUDE_RARE_EARTHS.get(Yttrium).crudeSolution().getFluid(2000))
                .outputFluids(Benzene.getFluid(1000))
                .outputFluids(CTNHMaterials.Kerosene.getFluid(1000))
                .addData(ProcessControlProfile.PRIMARY_RECIPE_DATA, 380)
                .addData(ProcessControlProfile.SECONDARY_RECIPE_DATA, 90)
                .EUt(7680)
                .duration(1200)
                .save(provider);

        RareEarthMaterials.CrudeRareEarth y = RareEarthMaterials.CRUDE_RARE_EARTHS.get(Yttrium);
        RareearthChain.addHydroxidePrecipitation(provider, "yttrium", y.crudeSolution(), y.crudeHydroxide(), 480, 900);
        RareearthChain.addHydroxideRoasting(provider, "yttrium", y.crudeHydroxide(), y.crudeOxide(), 1920, 1200);
        RareearthChain.addChlorideRecrystallization(provider, "yttrium", y.crudeOxide(), y.chloride(), 1920, 1440);
        RareearthChain.addChlorideRefinement(provider, "yttrium", y.chloride(), y.refinedChloride(), 7680, 1800);
        RareearthChain.addMetalReduction(provider, "yttrium", y.refinedChloride(), Yttrium, 60, 460, 7680, 720);

        // ── 铥：真空挥发 → 冷凝 → 沉淀 → 焙烧 → 复溶结晶 → 二次精制 → 还原 ──
        CTNHRecipeTypes.VACUUM_SINTERING.recipeBuilder(CTNHCore.id("thulium_vapor_separation"))
                .inputFluids(RareEarthMaterials.HOLMIUM_ERBIUM_THULIUM_LUTETIUM_RAFFINATE.getFluid(8000))
                .inputFluids(Nitrogen.getFluid(500))
                .outputFluids(RareEarthMaterials.THULIUM_CHLORIDE_VAPOR.getFluid(2000))
                .outputFluids(RareEarthMaterials.HOLMIUM_ERBIUM_LUTETIUM_RAFFINATE.getFluid(6000))
                .outputFluids(Nitrogen.getFluid(500))
                .addData(ProcessControlProfile.PRIMARY_RECIPE_DATA, 40)
                .addData(ProcessControlProfile.SECONDARY_RECIPE_DATA, 50)
                .EUt(7680)
                .duration(900)
                .blastFurnaceTemp(4000)
                .save(provider);

        CTNHRecipeTypes.CONDENSING_DISCRETE.recipeBuilder(CTNHCore.id("thulium_chloride_condensing"))
                .inputFluids(RareEarthMaterials.THULIUM_CHLORIDE_VAPOR.getFluid(2000))
                .inputFluids(DistilledWater.getFluid(500))
                .outputFluids(RareEarthMaterials.CRUDE_RARE_EARTHS.get(Thulium).crudeSolution().getFluid(2000))
                .outputFluids(DistilledWater.getFluid(500))
                .addData(ProcessControlProfile.PRIMARY_RECIPE_DATA, 40)
                .addData(ProcessControlProfile.SECONDARY_RECIPE_DATA, 65)
                .EUt(7680)
                .duration(600)
                .save(provider);

        RareEarthMaterials.CrudeRareEarth tm = RareEarthMaterials.CRUDE_RARE_EARTHS.get(Thulium);
        RareearthChain.addHydroxidePrecipitation(provider, "thulium", tm.crudeSolution(), tm.crudeHydroxide(), 480,
                900);
        RareearthChain.addHydroxideRoasting(provider, "thulium", tm.crudeHydroxide(), tm.crudeOxide(), 1920, 1200);
        RareearthChain.addChlorideRecrystallization(provider, "thulium", tm.crudeOxide(), tm.chloride(), 1920, 1440);
        RareearthChain.addChlorideRefinement(provider, "thulium", tm.chloride(), tm.refinedChloride(), 7680, 1800);
        RareearthChain.addMetalReduction(provider, "thulium", tm.refinedChloride(), Thulium, -20, 380, 7680, 720);

        // ── 铒：离子交换 → 沉淀 → 焙烧 → 复溶结晶 → 二次精制 → 还原 ──
        CTNHRecipeTypes.ION_EXCHANGER.recipeBuilder(CTNHCore.id("erbium_ion_exchange"))
                .inputFluids(RareEarthMaterials.HOLMIUM_ERBIUM_LUTETIUM_RAFFINATE.getFluid(6000))
                .inputFluids(HydrochloricAcid.getFluid(250))
                .outputFluids(RareEarthMaterials.CRUDE_RARE_EARTHS.get(Erbium).crudeSolution().getFluid(2000))
                .outputFluids(RareEarthMaterials.HOLMIUM_LUTETIUM_TAIL_SOLUTION.getFluid(4000))
                .outputFluids(HydrochloricAcid.getFluid(250))
                .addData(ProcessControlProfile.PRIMARY_RECIPE_DATA, 15)
                .addData(ProcessControlProfile.SECONDARY_RECIPE_DATA, 250)
                .EUt(7680)
                .duration(1440)
                .save(provider);

        RareEarthMaterials.CrudeRareEarth er = RareEarthMaterials.CRUDE_RARE_EARTHS.get(Erbium);
        RareearthChain.addHydroxidePrecipitation(provider, "erbium", er.crudeSolution(), er.crudeHydroxide(), 480, 900);
        RareearthChain.addHydroxideRoasting(provider, "erbium", er.crudeHydroxide(), er.crudeOxide(), 1920, 1200);
        RareearthChain.addChlorideRecrystallization(provider, "erbium", er.crudeOxide(), er.chloride(), 1920, 1440);
        RareearthChain.addChlorideRefinement(provider, "erbium", er.chloride(), er.refinedChloride(), 7680, 1800);
        RareearthChain.addMetalReduction(provider, "erbium", er.refinedChloride(), Erbium, -40, 360, 7680, 720);

        // ── 钬：离子交换 → 沉淀 → 焙烧 → 复溶结晶 → 二次精制 → 还原 ──
        CTNHRecipeTypes.ION_EXCHANGER.recipeBuilder(CTNHCore.id("holmium_ion_exchange"))
                .inputFluids(RareEarthMaterials.HOLMIUM_LUTETIUM_TAIL_SOLUTION.getFluid(4000))
                .inputFluids(HydrochloricAcid.getFluid(250))
                .outputFluids(RareEarthMaterials.CRUDE_RARE_EARTHS.get(Holmium).crudeSolution().getFluid(2000))
                .outputFluids(RareEarthMaterials.LUTETIUM_TAIL_SOLUTION.getFluid(2000))
                .outputFluids(HydrochloricAcid.getFluid(250))
                .addData(ProcessControlProfile.PRIMARY_RECIPE_DATA, 10)
                .addData(ProcessControlProfile.SECONDARY_RECIPE_DATA, 300)
                .EUt(7680)
                .duration(1680)
                .save(provider);

        RareEarthMaterials.CrudeRareEarth ho = RareEarthMaterials.CRUDE_RARE_EARTHS.get(Holmium);
        RareearthChain.addHydroxidePrecipitation(provider, "holmium", ho.crudeSolution(), ho.crudeHydroxide(), 480,
                900);
        RareearthChain.addHydroxideRoasting(provider, "holmium", ho.crudeHydroxide(), ho.crudeOxide(), 1920, 1200);
        RareearthChain.addChlorideRecrystallization(provider, "holmium", ho.crudeOxide(), ho.chloride(), 1920, 1440);
        RareearthChain.addChlorideRefinement(provider, "holmium", ho.chloride(), ho.refinedChloride(), 7680, 1800);
        RareearthChain.addMetalReduction(provider, "holmium", ho.refinedChloride(), Holmium, -60, 460, 7680, 720);

        // ── 镥：离子交换 → 沉淀 → 焙烧 → 复溶结晶 → 二次精制 → 还原（全链结尾）──
        CTNHRecipeTypes.ION_EXCHANGER.recipeBuilder(CTNHCore.id("lutetium_ion_exchange"))
                .inputFluids(RareEarthMaterials.LUTETIUM_TAIL_SOLUTION.getFluid(2000))
                .inputFluids(HydrochloricAcid.getFluid(250))
                .outputFluids(RareEarthMaterials.CRUDE_RARE_EARTHS.get(Lutetium).crudeSolution().getFluid(2000))
                .outputFluids(HydrochloricAcid.getFluid(250))
                .addData(ProcessControlProfile.PRIMARY_RECIPE_DATA, 5)
                .addData(ProcessControlProfile.SECONDARY_RECIPE_DATA, 400)
                .EUt(7680)
                .duration(1680)
                .save(provider);

        RareEarthMaterials.CrudeRareEarth lu = RareEarthMaterials.CRUDE_RARE_EARTHS.get(Lutetium);
        RareearthChain.addHydroxidePrecipitation(provider, "lutetium", lu.crudeSolution(), lu.crudeHydroxide(), 480,
                900);
        RareearthChain.addHydroxideRoasting(provider, "lutetium", lu.crudeHydroxide(), lu.crudeOxide(), 1920, 1200);
        RareearthChain.addChlorideRecrystallization(provider, "lutetium", lu.crudeOxide(), lu.chloride(), 1920, 1440);
        RareearthChain.addChlorideRefinement(provider, "lutetium", lu.chloride(), lu.refinedChloride(), 7680, 1800);
        RareearthChain.addMetalReduction(provider, "lutetium", lu.refinedChloride(), Lutetium, 20, 420, 7680, 720);
    }
}
