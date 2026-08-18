package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.rareearth.ProcessControlProfile;
import io.github.cpearl0.ctnhcore.data.materials.RareEarthMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;

/**
 * 独居石线（中稀土）：高压碱煮回收磷酸钠、钍固定在浸出渣；铕经变价还原最先离队，
 * 并以 IV/LuV 电压与长时长单独对待（铕最难还原的镧系元素）。每种元素再走
 * 沉淀 → 焙烧 → 复溶结晶 → 还原（铕另加二次精制），比轻稀土线更长。
 */
public class RareearthMonaziteChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 高压碱煮：独居石的磷酸根以磷酸钠副产回收
        CTNHRecipeTypes.HIGH_PRESSURE_ALKALI_DIGESTION.recipeBuilder(CTNHCore.id("monazite_alkali_digestion"))
                .inputItems(dust, Monazite, 10)
                .inputItems(dust, SodiumHydroxide, 10)
                .inputFluids(Water.getFluid(10000))
                .outputFluids(RareEarthMaterials.ALKALINE_RARE_EARTH_SLURRY.getFluid(10000))
                .outputItems(dust, RareEarthMaterials.SODIUM_PHOSPHATE, 2)
                .addData(ProcessControlProfile.PRIMARY_RECIPE_DATA, 1800)
                .addData(ProcessControlProfile.SECONDARY_RECIPE_DATA, 35)
                .EUt(1920)
                .duration(900)
                .save(provider);

        // 盐酸浸出：中稀土进入溶液，钍钙固定在浸出渣
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("monazite_acid_leach"))
                .inputFluids(RareEarthMaterials.ALKALINE_RARE_EARTH_SLURRY.getFluid(10000))
                .inputFluids(HydrochloricAcid.getFluid(40000))
                .outputFluids(RareEarthMaterials.MIDDLE_RARE_EARTH_CHLORIDE_SOLUTION.getFluid(10000))
                .outputItems(dust, RareEarthMaterials.THORIUM_CALCIUM_RESIDUE, 2)
                .outputItems(dust, Salt, 10)
                .EUt(1920)
                .duration(720)
                .save(provider);

        // ── 铕专项：变价还原 → 酸化 → 沉淀 → 焙烧 → 复溶结晶 → 二次精制 → 还原 ──
        // 铕以 IV 电压与长时长处理，终点还原提升到 LuV
        RareEarthMaterials.CrudeRareEarth eu = RareEarthMaterials.CRUDE_RARE_EARTHS.get(Europium);
        CTNHRecipeTypes.REDUCTION_PRECIPITATION.recipeBuilder(CTNHCore.id("europium_selective_reduction"))
                .inputFluids(RareEarthMaterials.MIDDLE_RARE_EARTH_CHLORIDE_SOLUTION.getFluid(10000))
                .inputItems(dust, Zinc, 1)
                .inputFluids(DistilledWater.getFluid(500))
                .outputItems(dust, RareEarthMaterials.EUROPIUM_PRECIPITATE, 2)
                .outputFluids(RareEarthMaterials.EUROPIUM_FREE_MIDDLE_SOLUTION.getFluid(8000))
                .outputItems(dust, RareEarthMaterials.ZINC_CHLORIDE, 1)
                .outputFluids(DistilledWater.getFluid(500))
                .addData(ProcessControlProfile.PRIMARY_RECIPE_DATA, -140)
                .addData(ProcessControlProfile.SECONDARY_RECIPE_DATA, 380)
                .EUt(VA[IV])
                .duration(2400)
                .save(provider);

        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("europium_crude_solution"))
                .inputItems(dust, RareEarthMaterials.EUROPIUM_PRECIPITATE, 2)
                .inputFluids(HydrochloricAcid.getFluid(2000))
                .outputFluids(eu.crudeSolution().getFluid(2000))
                .outputFluids(Hydrogen.getFluid(1000))
                .EUt(VA[IV])
                .duration(1200)
                .save(provider);

        RareearthChain.addHydroxidePrecipitation(provider, "europium", eu.crudeSolution(), eu.crudeHydroxide(),
                VA[IV], 1800);
        RareearthChain.addHydroxideRoasting(provider, "europium", eu.crudeHydroxide(), eu.crudeOxide(), VA[IV], 2400);
        RareearthChain.addChlorideRecrystallization(provider, "europium", eu.crudeOxide(), eu.chloride(), VA[IV], 3000);
        RareearthChain.addChlorideRefinement(provider, "europium", eu.chloride(), eu.refinedChloride(), VA[IV],
                3600);
        RareearthChain.addMetalReduction(provider, "europium", eu.refinedChloride(), Europium, -140, 380, VA[LuV],
                3600);

        // ── 钐钆分离 ────────────────────────────────────────────
        CTNHRecipeTypes.SOLVENT_EXTRACTION.recipeBuilder(CTNHCore.id("samarium_gadolinium_extraction"))
                .inputFluids(RareEarthMaterials.EUROPIUM_FREE_MIDDLE_SOLUTION.getFluid(8000))
                .inputFluids(Benzene.getFluid(1000))
                .inputFluids(CTNHMaterials.Kerosene.getFluid(1000))
                .outputFluids(RareEarthMaterials.SAMARIUM_GADOLINIUM_LOADED_ORGANIC.getFluid(4000))
                .outputFluids(RareEarthMaterials.TERBIUM_DYSPROSIUM_RAFFINATE.getFluid(4000))
                .addData(ProcessControlProfile.PRIMARY_RECIPE_DATA, 450)
                .addData(ProcessControlProfile.SECONDARY_RECIPE_DATA, 100)
                .EUt(7680)
                .duration(1200)
                .save(provider);

        // 钐钆反萃：粗氯化钐溶液 + 钆富集液，有机相再生回用
        CTNHRecipeTypes.SOLVENT_EXTRACTION.recipeBuilder(CTNHCore.id("samarium_stripping"))
                .inputFluids(RareEarthMaterials.SAMARIUM_GADOLINIUM_LOADED_ORGANIC.getFluid(4000))
                .inputFluids(HydrochloricAcid.getFluid(1000))
                .outputFluids(RareEarthMaterials.CRUDE_RARE_EARTHS.get(Samarium).crudeSolution().getFluid(2000))
                .outputFluids(RareEarthMaterials.GADOLINIUM_ENRICHED_SOLUTION.getFluid(2000))
                .outputFluids(Benzene.getFluid(1000))
                .outputFluids(CTNHMaterials.Kerosene.getFluid(1000))
                .addData(ProcessControlProfile.PRIMARY_RECIPE_DATA, 450)
                .addData(ProcessControlProfile.SECONDARY_RECIPE_DATA, 100)
                .EUt(7680)
                .duration(1200)
                .save(provider);

        // 钆离子交换：粗氯化钆溶液
        CTNHRecipeTypes.ION_EXCHANGER.recipeBuilder(CTNHCore.id("gadolinium_ion_exchange"))
                .inputFluids(RareEarthMaterials.GADOLINIUM_ENRICHED_SOLUTION.getFluid(2000))
                .inputFluids(HydrochloricAcid.getFluid(250))
                .outputFluids(RareEarthMaterials.CRUDE_RARE_EARTHS.get(Gadolinium).crudeSolution().getFluid(2000))
                .outputFluids(HydrochloricAcid.getFluid(250))
                .addData(ProcessControlProfile.PRIMARY_RECIPE_DATA, 20)
                .addData(ProcessControlProfile.SECONDARY_RECIPE_DATA, 200)
                .EUt(7680)
                .duration(1200)
                .save(provider);

        // ── 铽镝分离 ────────────────────────────────────────────
        CTNHRecipeTypes.SOLVENT_EXTRACTION.recipeBuilder(CTNHCore.id("terbium_extraction"))
                .inputFluids(RareEarthMaterials.TERBIUM_DYSPROSIUM_RAFFINATE.getFluid(4000))
                .inputFluids(Benzene.getFluid(1000))
                .inputFluids(CTNHMaterials.Kerosene.getFluid(1000))
                .outputFluids(RareEarthMaterials.TERBIUM_LOADED_ORGANIC.getFluid(2000))
                .outputFluids(RareEarthMaterials.DYSPROSIUM_TAIL_SOLUTION.getFluid(2000))
                .addData(ProcessControlProfile.PRIMARY_RECIPE_DATA, 400)
                .addData(ProcessControlProfile.SECONDARY_RECIPE_DATA, 80)
                .EUt(7680)
                .duration(1200)
                .save(provider);

        // 铽反萃：粗氯化铽溶液，有机相再生回用
        CTNHRecipeTypes.SOLVENT_EXTRACTION.recipeBuilder(CTNHCore.id("terbium_stripping"))
                .inputFluids(RareEarthMaterials.TERBIUM_LOADED_ORGANIC.getFluid(2000))
                .inputFluids(HydrochloricAcid.getFluid(1000))
                .outputFluids(RareEarthMaterials.CRUDE_RARE_EARTHS.get(Terbium).crudeSolution().getFluid(2000))
                .outputFluids(Benzene.getFluid(1000))
                .outputFluids(CTNHMaterials.Kerosene.getFluid(1000))
                .addData(ProcessControlProfile.PRIMARY_RECIPE_DATA, 400)
                .addData(ProcessControlProfile.SECONDARY_RECIPE_DATA, 80)
                .EUt(7680)
                .duration(1200)
                .save(provider);

        // 镝离子交换：粗氯化镝溶液，独居石线结尾收尾
        CTNHRecipeTypes.ION_EXCHANGER.recipeBuilder(CTNHCore.id("dysprosium_ion_exchange"))
                .inputFluids(RareEarthMaterials.DYSPROSIUM_TAIL_SOLUTION.getFluid(2000))
                .inputFluids(HydrochloricAcid.getFluid(250))
                .outputFluids(RareEarthMaterials.CRUDE_RARE_EARTHS.get(Dysprosium).crudeSolution().getFluid(2000))
                .outputFluids(HydrochloricAcid.getFluid(250))
                .addData(ProcessControlProfile.PRIMARY_RECIPE_DATA, 15)
                .addData(ProcessControlProfile.SECONDARY_RECIPE_DATA, 250)
                .EUt(7680)
                .duration(1440)
                .save(provider);

        // ── 中稀土单元素加工：沉淀 → 焙烧 → 复溶结晶 → 还原 ──────────
        RareEarthMaterials.CrudeRareEarth sm = RareEarthMaterials.CRUDE_RARE_EARTHS.get(Samarium);
        RareearthChain.addHydroxidePrecipitation(provider, "samarium", sm.crudeSolution(), sm.crudeHydroxide(), 480,
                600);
        RareearthChain.addHydroxideRoasting(provider, "samarium", sm.crudeHydroxide(), sm.crudeOxide(), 1920, 800);
        RareearthChain.addChlorideRecrystallization(provider, "samarium", sm.crudeOxide(), sm.chloride(), 1920, 1200);
        RareearthChain.addMetalReduction(provider, "samarium", sm.chloride(), Samarium, -160, 360, 7680, 600);

        RareEarthMaterials.CrudeRareEarth gd = RareEarthMaterials.CRUDE_RARE_EARTHS.get(Gadolinium);
        RareearthChain.addHydroxidePrecipitation(provider, "gadolinium", gd.crudeSolution(), gd.crudeHydroxide(), 480,
                600);
        RareearthChain.addHydroxideRoasting(provider, "gadolinium", gd.crudeHydroxide(), gd.crudeOxide(), 1920, 800);
        RareearthChain.addChlorideRecrystallization(provider, "gadolinium", gd.crudeOxide(), gd.chloride(), 1920, 1200);
        RareearthChain.addMetalReduction(provider, "gadolinium", gd.chloride(), Gadolinium, -120, 400, 7680, 600);

        RareEarthMaterials.CrudeRareEarth tb = RareEarthMaterials.CRUDE_RARE_EARTHS.get(Terbium);
        RareearthChain.addHydroxidePrecipitation(provider, "terbium", tb.crudeSolution(), tb.crudeHydroxide(), 480,
                600);
        RareearthChain.addHydroxideRoasting(provider, "terbium", tb.crudeHydroxide(), tb.crudeOxide(), 1920, 800);
        RareearthChain.addChlorideRecrystallization(provider, "terbium", tb.crudeOxide(), tb.chloride(), 1920, 1200);
        RareearthChain.addMetalReduction(provider, "terbium", tb.chloride(), Terbium, -100, 420, 7680, 600);

        RareEarthMaterials.CrudeRareEarth dy = RareEarthMaterials.CRUDE_RARE_EARTHS.get(Dysprosium);
        RareearthChain.addHydroxidePrecipitation(provider, "dysprosium", dy.crudeSolution(), dy.crudeHydroxide(), 480,
                600);
        RareearthChain.addHydroxideRoasting(provider, "dysprosium", dy.crudeHydroxide(), dy.crudeOxide(), 1920, 800);
        RareearthChain.addChlorideRecrystallization(provider, "dysprosium", dy.crudeOxide(), dy.chloride(), 1920, 1200);
        RareearthChain.addMetalReduction(provider, "dysprosium", dy.chloride(), Dysprosium, -80, 440, 7680, 600);
    }
}
