package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.rareearth.ProcessControlProfile;
import io.github.cpearl0.ctnhcore.common.recipe.NeutronActivatorCondition;
import io.github.cpearl0.ctnhcore.data.materials.RareEarthMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;

/**
 * 氟碳铈镧矿线（轻稀土，最短）：焙烧氧化 Ce3+→Ce4+ 后盐酸浸出，铈以 CeO2 渣最先离队；
 * 镨钕共萃反萃得到粗氯化镨/钕溶液，镧留在萃余粗液。每种元素再走
 * 沉淀（粗氢氧化物）→ 复溶结晶（纯氯化物）→ 还原（单质粉）三步，铈终点走熔盐电解。
 * 钷由 Nd 中子活化（Nd-146 → Pm-147），氟以氢氟酸副产回收。
 */
public class RareearthBastnasiteChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 氧化焙烧：Ce3+ → Ce4+，为浸出时的铈分离做准备
        CTNHRecipeTypes.OXIDATION_ROASTING.recipeBuilder(CTNHCore.id("bastnasite_oxidation_roasting"))
                .inputItems(dust, Bastnasite, 8)
                .inputFluids(Oxygen.getFluid(12000))
                .outputItems(dust, RareEarthMaterials.ROASTED_BASTNASITE, 8)
                .addData(ProcessControlProfile.PRIMARY_RECIPE_DATA, 110)
                .addData(ProcessControlProfile.SECONDARY_RECIPE_DATA, 8)
                .EUt(1920)
                .duration(600)
                .blastFurnaceTemp(3600)
                .save(provider);

        // 盐酸浸出：镧镨钕进入溶液，铈留在浸出渣，氟以氢氟酸回收
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("bastnasite_acid_leach"))
                .inputItems(dust, RareEarthMaterials.ROASTED_BASTNASITE, 8)
                .inputFluids(HydrochloricAcid.getFluid(24000))
                .outputFluids(RareEarthMaterials.LIGHT_RARE_EARTH_CHLORIDE_SOLUTION.getFluid(6000))
                .outputItems(dust, RareEarthMaterials.CERIUM_RESIDUE, 2)
                .outputFluids(HydrofluoricAcid.getFluid(2000))
                .EUt(1920)
                .duration(720)
                .save(provider);

        // 氢氟酸电解：氟碳铈镧矿的氟回收为氟气
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("hydrofluoric_acid_electrolysis"))
                .inputFluids(HydrofluoricAcid.getFluid(2000))
                .outputFluids(Hydrogen.getFluid(1000))
                .outputFluids(Fluorine.getFluid(1000))
                .EUt(1920)
                .duration(480)
                .save(provider);

        // 镨钕共萃：轻稀土溶液 → 载镨钕有机相 + 粗氯化镧溶液
        CTNHRecipeTypes.SOLVENT_EXTRACTION.recipeBuilder(CTNHCore.id("praseodymium_neodymium_extraction"))
                .inputFluids(RareEarthMaterials.LIGHT_RARE_EARTH_CHLORIDE_SOLUTION.getFluid(6000))
                .inputFluids(Benzene.getFluid(1500))
                .inputFluids(CTNHMaterials.Kerosene.getFluid(1500))
                .outputFluids(RareEarthMaterials.PRASEODYMIUM_NEODYMIUM_LOADED_ORGANIC.getFluid(4000))
                .outputFluids(RareEarthMaterials.CRUDE_RARE_EARTHS.get(Lanthanum).crudeSolution().getFluid(2000))
                .addData(ProcessControlProfile.PRIMARY_RECIPE_DATA, 450)
                .addData(ProcessControlProfile.SECONDARY_RECIPE_DATA, 100)
                .EUt(7680)
                .duration(1200)
                .save(provider);

        // 镨钕反萃分离：粗氯化镨溶液与粗氯化钕溶液分别析出，有机相再生回用
        CTNHRecipeTypes.SOLVENT_EXTRACTION.recipeBuilder(CTNHCore.id("praseodymium_neodymium_partition"))
                .inputFluids(RareEarthMaterials.PRASEODYMIUM_NEODYMIUM_LOADED_ORGANIC.getFluid(4000))
                .inputFluids(HydrochloricAcid.getFluid(1000))
                .outputFluids(RareEarthMaterials.CRUDE_RARE_EARTHS.get(Praseodymium).crudeSolution().getFluid(2000))
                .outputFluids(RareEarthMaterials.CRUDE_RARE_EARTHS.get(Neodymium).crudeSolution().getFluid(2000))
                .outputFluids(Benzene.getFluid(1500))
                .outputFluids(CTNHMaterials.Kerosene.getFluid(1500))
                .addData(ProcessControlProfile.PRIMARY_RECIPE_DATA, 300)
                .addData(ProcessControlProfile.SECONDARY_RECIPE_DATA, 60)
                .EUt(7680)
                .duration(1200)
                .save(provider);

        // Ce 支线（产线开头）：富铈渣还原酸溶为粗氯化铈溶液
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("cerium_crude_solution"))
                .inputItems(dust, RareEarthMaterials.CERIUM_RESIDUE, 2)
                .inputFluids(HydrochloricAcid.getFluid(8000))
                .outputFluids(RareEarthMaterials.CRUDE_RARE_EARTHS.get(Cerium).crudeSolution().getFluid(2000))
                .outputFluids(Chlorine.getFluid(1000))
                .EUt(1920)
                .duration(720)
                .save(provider);

        // 铈熔盐电解：产线开头就出铈粉，氯气回补盐酸再生
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("cerium_electrolysis"))
                .inputItems(dust, RareEarthMaterials.CERIUM_CHLORIDE, 2)
                .outputItems(dust, Cerium, 2)
                .outputFluids(Chlorine.getFluid(3000))
                .EUt(1920)
                .duration(480)
                .save(provider);

        // ── 轻稀土单元素加工：沉淀 → 复溶结晶 → 还原 ──────────────
        RareEarthMaterials.CrudeRareEarth la = RareEarthMaterials.CRUDE_RARE_EARTHS.get(Lanthanum);
        RareearthChain.addHydroxidePrecipitation(provider, "lanthanum", la.crudeSolution(), la.crudeHydroxide(), 480,
                400);
        RareearthChain.addChlorideRecrystallization(provider, "lanthanum", la.crudeHydroxide(), la.chloride(), 1920,
                960);
        RareearthChain.addMetalReduction(provider, "lanthanum", la.chloride(), Lanthanum, -260, 360, 7680, 480);

        RareEarthMaterials.CrudeRareEarth ce = RareEarthMaterials.CRUDE_RARE_EARTHS.get(Cerium);
        RareearthChain.addHydroxidePrecipitation(provider, "cerium", ce.crudeSolution(), ce.crudeHydroxide(), 480, 400);
        RareearthChain.addChlorideRecrystallization(provider, "cerium", ce.crudeHydroxide(), ce.chloride(), 1920, 960);

        RareEarthMaterials.CrudeRareEarth pr = RareEarthMaterials.CRUDE_RARE_EARTHS.get(Praseodymium);
        RareearthChain.addHydroxidePrecipitation(provider, "praseodymium", pr.crudeSolution(), pr.crudeHydroxide(),
                480, 400);
        RareearthChain.addChlorideRecrystallization(provider, "praseodymium", pr.crudeHydroxide(), pr.chloride(),
                1920, 960);
        RareearthChain.addMetalReduction(provider, "praseodymium", pr.chloride(), Praseodymium, -220, 400, 7680, 480);

        RareEarthMaterials.CrudeRareEarth nd = RareEarthMaterials.CRUDE_RARE_EARTHS.get(Neodymium);
        RareearthChain.addHydroxidePrecipitation(provider, "neodymium", nd.crudeSolution(), nd.crudeHydroxide(), 480,
                400);
        RareearthChain.addChlorideRecrystallization(provider, "neodymium", nd.crudeHydroxide(), nd.chloride(), 1920,
                960);
        RareearthChain.addMetalReduction(provider, "neodymium", nd.chloride(), Neodymium, -200, 420, 7680, 480);

        // 钷独立小线：1 份 Nd 经中子活化转化为 1 份 Pm
        CTNHRecipeTypes.NEUTRON_ACTIVATOR_RECIPES.recipeBuilder(CTNHCore.id("promethium_neutron_activation"))
                .inputItems(dust, Neodymium, 1)
                .notConsumable(TagPrefix.plate, Tungsten)
                .outputItems(dust, Promethium, 1)
                .addCondition(new NeutronActivatorCondition(450, 550))
                .duration(1000)
                .save(provider);

        // 旧存档兼容：已有的氯化钷仍可还原成钷粉
        RareearthChain.addMetalReduction(provider, "promethium", RareEarthMaterials.PROMETHIUM_CHLORIDE, Promethium,
                -180, 440, 7680, 480);
    }
}
