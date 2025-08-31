package io.github.cpearl0.ctnhcore.data.recipe.chain;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import io.github.cpearl0.ctnhcore.common.recipe.NeutronActivatorCondition;
import io.github.cpearl0.ctnhcore.common.recipe.PlantCasingCondition;
import io.github.cpearl0.ctnhcore.registry.CTNHMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import io.github.cpearl0.ctnhcore.registry.CTNHTagPrefixes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.util.valueproviders.UniformInt;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;

public class NaquadahLine {
    public static void init(Consumer<FinishedRecipe> provider) {
        removeOriginalRecipes(provider);
        registerSiliconProcess(provider);
    }
    /* ==== 配方移除组 ==== */
    public static void removeOriginalRecipes(Consumer<FinishedRecipe> provider) {
        // 离心机配方移除
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("remove_impure_enriched_naquadah").save(provider);
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("remove_acidic_enriched_naquadah").save(provider);
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("remove_impure_naquadria").save(provider);
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("remove_acidic_naquadria").save(provider);
    }

    /* ==== 硅岩处理配方组 ==== */
    public static void registerSiliconProcess(Consumer<FinishedRecipe> provider) {
        // --- 六氟化阶段 ---
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("hexafluoride_enriched_naquadah")
                .inputFluids(GTMaterials.ImpureEnrichedNaquadahSolution.getFluid(1000))
                .inputFluids(GTMaterials.Fluorine.getFluid(6000))
                .outputFluids(CTNHMaterials.HexafluorideEnrichedNaquadahSolution.getFluid(1000))
                .EUt(GTValues.VA[GTValues.IV])
                .duration(400)
                .save(provider);

        // --- 氙化合物合成 ---
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("xenon_hexafluoro_naquadate")
                .inputFluids(CTNHMaterials.HexafluorideEnrichedNaquadahSolution.getFluid(1000))
                .inputFluids(GTMaterials.Xenon.getFluid(1000))
                .outputFluids(CTNHMaterials.XenonHexafluoroEnrichedNaquadate.getFluid(1000))
                .EUt(GTValues.VA[GTValues.HV])
                .duration(200)
                .cleanroom(CleanroomType.CLEANROOM)
                .save(provider);

        // --- 钯碳催化剂 ---
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("palladium_on_carbon")
                .inputItems(TagPrefix.dust, GTMaterials.Carbon)
                .inputItems(TagPrefix.dust, GTMaterials.Palladium)
                .outputItems(CTNHTagPrefixes.catalyst, CTNHMaterials.PalladiumOnCarbon)
                .circuitMeta(32)
                .duration(160)
                .EUt(GTValues.VA[GTValues.HV])
                .save(provider);

        // --- 主还原反应（化学工厂）---
        CTNHRecipeTypes.CHEMICAL_PLANT_RECIPES.recipeBuilder("enriched_naquadah_reduction")
                .inputItems(CTNHTagPrefixes.catalyst, CTNHMaterials.PalladiumOnCarbon)
                .inputItems(TagPrefix.dust, CTNHMaterials.GoldTrifluoride, 4)
                .inputFluids(CTNHMaterials.XenonHexafluoroEnrichedNaquadate.getFluid(1000))
                .inputFluids(GTMaterials.FluoroantimonicAcid.getFluid(1000))
                .inputFluids(GTMaterials.Hydrogen.getFluid(9000))
                .outputFluids(GTMaterials.EnrichedNaquadahSolution.getFluid(1000))
                .outputFluids(CTNHMaterials.EnrichedNaquadahResidueSolution.getFluid(1000))
                .outputFluids(GTMaterials.HydrofluoricAcid.getFluid(8000))
                .EUt(GTValues.VA[GTValues.LuV])
                .duration(1200)
                .addCondition(new PlantCasingCondition(6))
                .save(provider);

        // --- 废液处理 ---
        CTNHRecipeTypes.DEHYDRATOR_RECIPES.recipeBuilder("xenoauric_acid_production")
                .inputFluids(CTNHMaterials.EnrichedNaquadahResidueSolution.getFluid(2000))
                .outputItemsRanged(ChemicalHelper.get(dust, GTMaterials.TriniumSulfide), UniformInt.of(1, 3))
                .outputFluids(CTNHMaterials.XenoauricFluoroantimonicAcid.getFluid(1000))
                .EUt(GTValues.VA[GTValues.EV])
                .duration(240)
                .save(provider);

        // --- 金化合物循环 ---
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("gold_trifluoride_synthesis")
                .inputFluids(CTNHMaterials.GoldChloride.getFluid(1000))
                .inputFluids(CTNHMaterials.BromineTrifluoride.getFluid(2000))
                .outputItems(TagPrefix.dust, CTNHMaterials.GoldTrifluoride, 8)
                .outputFluids(GTMaterials.Bromine.getFluid(2000))
                .outputFluids(GTMaterials.Chlorine.getFluid(6000))
                .EUt(GTValues.VA[GTValues.MV])
                .duration(200)
                .save(provider);

        // --- 钯碳催化剂制备 ---
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("palladium_on_carbon")
                .inputItems(TagPrefix.dust, GTMaterials.Carbon)
                .inputItems(TagPrefix.dust, GTMaterials.Palladium)
                .outputItems(CTNHTagPrefixes.catalyst, CTNHMaterials.PalladiumOnCarbon)
                .circuitMeta(32)
                .duration(160) // 8秒 = 160 ticks
                .EUt(GTValues.VA[GTValues.HV]) // 480 EU/t
                .save(provider);

        // --- 金氟锑酸循环处理 ---
        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("xenoauric_fluoroantimonic_acid_recycle")
                .inputFluids(CTNHMaterials.XenoauricFluoroantimonicAcid.getFluid(1000))
                .outputItems(TagPrefix.dust, GTMaterials.Gold)
                .outputItems(TagPrefix.dust, GTMaterials.AntimonyTrifluoride,8)
                .outputFluids(GTMaterials.Xenon.getFluid(2000))
                .outputFluids(GTMaterials.Fluorine.getFluid(6000))
                .EUt(GTValues.VA[GTValues.IV]) // 1920 EU/t
                .duration(1200) // 60秒
                .save(provider);

        // 溴 + 氟 -> 三氟化溴
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("bromine_trifluoride_synthesis")
                .inputFluids(GTMaterials.Bromine.getFluid(1000))
                .inputFluids(GTMaterials.Fluorine.getFluid(3000))
                .circuitMeta(3)  // 标识反应类型
                .outputFluids(CTNHMaterials.BromineTrifluoride.getFluid(1000))
                .EUt(GTValues.VA[GTValues.LV])  // 30 EU/t
                .duration(120)  // 6秒
                .save(provider);

        // 金粉 + 氯气 -> 氯化金溶液 (电解)
        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("gold_chloride_synthesis")
                .inputItems(TagPrefix.dust, GTMaterials.Gold, 2)
                .inputFluids(GTMaterials.Chlorine.getFluid(6000))
                .outputFluids(CTNHMaterials.GoldChloride.getFluid(1000))
                .EUt(GTValues.VA[GTValues.MV]) // 480 EU/t
                .duration(360) // 18秒
                .save(provider);

        // 不纯Naquadria溶液 + 氟 -> 六氟化Naquadria溶液
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("naquadria_hexafluoridation")
                .inputFluids(GTMaterials.ImpureNaquadriaSolution.getFluid(1000))
                .inputFluids(GTMaterials.Fluorine.getFluid(6000))
                .outputFluids(CTNHMaterials.HexafluorideNaquadriaSolution.getFluid(1000))
                .EUt(GTValues.VA[GTValues.IV]) // 1920 EU/t
                .duration(400) // 20秒
                .save(provider);

        // 氡气 + 氟 -> 二氟化氡
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("radon_difluoride_synthesis")
                .inputFluids(GTMaterials.Radon.getFluid(1000))
                .inputFluids(GTMaterials.Fluorine.getFluid(2000))
                .circuitMeta(2) // 标识二氟化反应
                .outputFluids(CTNHMaterials.RadonDifluoride.getFluid(1000))
                .EUt(GTValues.VA[GTValues.MV]) // 480 EU/t
                .duration(100) // 5秒
                .save(provider);

        // 六氟化Naquadria溶液 + 二氟化氡 -> 八氟化氡Naquadria + Naquadria残液
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("radon_naquadria_octafluoride")
                .inputFluids(CTNHMaterials.HexafluorideNaquadriaSolution.getFluid(1000))
                .inputFluids(CTNHMaterials.RadonDifluoride.getFluid(1000))
                .outputFluids(CTNHMaterials.RadonNaquadriaOctafluoride.getFluid(1000))
                .outputFluids(CTNHMaterials.NaquadriaResidueSolution.getFluid(1000))
                .EUt(GTValues.VA[GTValues.HV]) // 480 EU/t
                .duration(800) // 40秒
                .cleanroom(CleanroomType.CLEANROOM) // 需要洁净环境
                .save(provider);

        // 铯 + 氟 -> 氟化铯
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("caesium_fluoride_synthesis")
                .inputItems(TagPrefix.dust, GTMaterials.Caesium)
                .inputFluids(GTMaterials.Fluorine.getFluid(1000))
                .circuitMeta(1)  // 标识单氟化反应
                .outputFluids(CTNHMaterials.CaesiumFluoride.getFluid(1000))
                .EUt(GTValues.VA[GTValues.MV])  // 480 EU/t
                .duration(100)  // 5秒
                .save(provider);

        // 氟化铯 + 三氧化氙 -> 氟化铯氙三氧化物
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("caesium_xenontrioxide_fluoride")
                .inputFluids(CTNHMaterials.CaesiumFluoride.getFluid(1000))
                .inputFluids(CTNHMaterials.XenonTrioxide.getFluid(1000))
                .outputFluids(CTNHMaterials.CaesiumXenontrioxideFluoride.getFluid(1000))
                .EUt(GTValues.VA[GTValues.MV])  // 480 EU/t
                .duration(480)  // 24秒
                .save(provider);

        // Naquadria残液 -> 磷化铟 + Naquadria溶液 (高温熔炼)
        GTRecipeTypes.BLAST_RECIPES.recipeBuilder("naquadria_solution_recovery")
                .inputFluids(CTNHMaterials.NaquadriaResidueSolution.getFluid(2000))
                .outputItems(TagPrefix.dust, GTMaterials.IndiumPhosphide)
                .outputFluids(GTMaterials.NaquadriaSolution.getFluid(1000))
                .EUt(GTValues.VA[GTValues.IV])  // 1920 EU/t
                .duration(1200)  // 60秒
                .blastFurnaceTemp(880)  // 880K
                .save(provider);

        // Naquadria溶液脱水 -> 硫粉 + Naquadria废液
        CTNHRecipeTypes.DEHYDRATOR_RECIPES.recipeBuilder("naquadria_waste_separation")
                .inputFluids(GTMaterials.NaquadriaSolution.getFluid(3000))
                .outputItems(TagPrefix.dust, GTMaterials.Sulfur, 6)
                .outputFluids(GTMaterials.NaquadriaWaste.getFluid(1000))
                .EUt(GTValues.VA[GTValues.HV])  // 480 EU/t
                .duration(100)  // 5秒
                .save(provider);

        // 氙气 + 氧气 -> 三氧化氙
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("xenon_trioxide_synthesis")
                .inputFluids(GTMaterials.Xenon.getFluid(1000))
                .inputFluids(GTMaterials.Oxygen.getFluid(3000))
                .outputFluids(CTNHMaterials.XenonTrioxide.getFluid(1000))
                .EUt(GTValues.VA[GTValues.MV])  // 480 EU/t
                .duration(100)  // 5秒
                .save(provider);

        // 八氟化氡Naquadria + 氟化铯氙三氧化物 -> Naquadria铯氙九氟化物 + 三氧化氡 (洁净室)
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("naquadria_caesium_xenonnonfluoride")
                .inputFluids(CTNHMaterials.RadonNaquadriaOctafluoride.getFluid(1000))
                .inputFluids(CTNHMaterials.CaesiumXenontrioxideFluoride.getFluid(1000))
                .outputFluids(CTNHMaterials.NaquadriaCaesiumXenonnonfluoride.getFluid(1000))
                .outputFluids(CTNHMaterials.RadonTrioxide.getFluid(1000))
                .EUt(GTValues.VA[GTValues.IV])  // 1920 EU/t
                .duration(800)  // 40秒
                .cleanroom(CleanroomType.CLEANROOM)  // 需要洁净环境
                .save(provider);

        // 氮氧化物 + 氟 -> 硝酰氟
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("nitryl_fluoride_synthesis")
                .inputFluids(GTMaterials.NitrogenDioxide.getFluid(1000))
                .inputFluids(GTMaterials.Fluorine.getFluid(1000))
                .outputFluids(CTNHMaterials.NitrylFluoride.getFluid(1000))
                .EUt(GTValues.VA[GTValues.MV])  // 480 EU/t
                .duration(160)  // 8秒
                .save(provider);

        // 硫酸 + Naquadria铯氟化物 -> 酸性Naquadria铯氟化物
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("acidic_naquadria_caesiumfluoride")
                .inputFluids(GTMaterials.SulfuricAcid.getFluid(2000))
                .inputFluids(CTNHMaterials.NaquadriaCaesiumfluoride.getFluid(1000))
                .outputFluids(CTNHMaterials.AcidicNaquadriaCaesiumfluoride.getFluid(3000))
                .EUt(GTValues.VA[GTValues.IV])  // 1920 EU/t
                .duration(360)  // 18秒
                .save(provider);

        // Naquadria铯氙九氟化物 + 硝酰氟 -> Naquadria铯氟化物 + 硝酰八氟氙酸盐 (洁净室)
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("nitrosonium_octafluoroxenate")
                .inputFluids(CTNHMaterials.NaquadriaCaesiumXenonnonfluoride.getFluid(1000))
                .inputFluids(CTNHMaterials.NitrylFluoride.getFluid(2000))
                .outputFluids(CTNHMaterials.NaquadriaCaesiumfluoride.getFluid(1000))
                .outputFluids(CTNHMaterials.NitrosoniumOctafluoroxenate.getFluid(1000))
                .EUt(GTValues.VA[GTValues.EV])  // 7680 EU/t
                .duration(400)  // 20秒
                .cleanroom(CleanroomType.CLEANROOM)
                .save(provider);

        // 酸性Naquadria溶液 -> 不纯浓缩Naquadah溶液 + Naquadria废液
        CTNHRecipeTypes.CHEMICAL_PLANT_RECIPES.recipeBuilder("impure_enriched_naquadah_separation")
                .inputFluids(GTMaterials.AcidicNaquadriaSolution.getFluid(3000))
                .outputFluids(GTMaterials.NaquadriaWaste.getFluid(1000))
                .outputFluids(GTMaterials.ImpureEnrichedNaquadahSolution.getFluid(1000))
                .EUt(GTValues.VA[GTValues.HV])  // 480 EU/t
                .duration(1000)  // 50秒
                .addCondition(new PlantCasingCondition(3))
                .save(provider);

        // Naquadria处理 (酸性Naquadria铯氟化物 -> Naquadria硫酸盐)
        CTNHRecipeTypes.NEUTRON_ACTIVATOR_RECIPES.recipeBuilder("naquadria_activation")
                .inputFluids(CTNHMaterials.AcidicNaquadriaCaesiumfluoride.getFluid(9000))
                .outputItems(TagPrefix.dust, GTMaterials.NaquadriaSulfate, 3)
                .outputItems(TagPrefix.dust, GTMaterials.Caesium, 3)
                .outputFluids(GTMaterials.Fluorine.getFluid(18000))
                .duration(100)  // 5秒
                .addCondition(new NeutronActivatorCondition(1100, 1050))  // 设置中子通量参数
                .save(provider);

        // 浓缩Naquadah处理 (酸性溶液 -> 硫酸盐)
        CTNHRecipeTypes.NEUTRON_ACTIVATOR_RECIPES.recipeBuilder("enriched_naquadah_activation")
                .inputFluids(GTMaterials.AcidicEnrichedNaquadahSolution.getFluid(16000))
                .outputItems(TagPrefix.dust, GTMaterials.EnrichedNaquadahSulfate, 15)
                .outputFluids(GTMaterials.ImpureNaquadriaSolution.getFluid(1000))
                .duration(120)  // 6秒
                .addCondition(new NeutronActivatorCondition(480, 460))
                .save(provider);

        // Naquadah直接活化 (氧化物混合物 -> 纯Naquadah)-完美循环
        CTNHRecipeTypes.NEUTRON_ACTIVATOR_RECIPES.recipeBuilder("naquadah_direct_activation")
                .inputItems(TagPrefix.dust, CTNHMaterials.NaquadahOxideMixture, 16)
                .inputFluids(GTMaterials.FluoroantimonicAcid.getFluid(6000))
                .outputItems(TagPrefix.dust, GTMaterials.TitaniumTrifluoride, 32)
                .outputItems(TagPrefix.dust, GTMaterials.Naquadah, 2)
                .outputItems(TagPrefix.dust, GTMaterials.AntimonyTrifluoride, 24)
                .chancedOutput(TagPrefix.dust, GTMaterials.Gallium, 7500, 0)  // 75%概率副产物
                .duration(100)  // 5秒
                .addCondition(new NeutronActivatorCondition(240, 220))
                .save(provider);
        // 浓缩Naquadah氧化物混合物 + 氟锑酸 -> 三氧化二锑 + 三氟化钛 + 不纯浓缩溶液-半循环
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("enriched_naquadah_oxide_decomposition")
                .inputItems(TagPrefix.dust, CTNHMaterials.EnrichedNaquadahOxideMixture, 7)
                .inputFluids(GTMaterials.FluoroantimonicAcid.getFluid(3000))
                .outputItems(TagPrefix.dust, GTMaterials.AntimonyTrioxide, 7)
                .outputItems(TagPrefix.dust, GTMaterials.TitaniumTrifluoride, 14)
                .outputFluids(GTMaterials.ImpureEnrichedNaquadahSolution.getFluid(2000))
                .EUt(GTValues.VA[GTValues.EV])  // 1920 EU/t
                .duration(200)  // 10秒
                .save(provider);

        // Naquadria氧化物混合物 + 氟锑酸 -> 三氧化二锑 + 三氟化钛 + 不纯Naquadria溶液-四分之一循环
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("naquadria_oxide_decomposition")
                .inputItems(TagPrefix.dust, CTNHMaterials.NaquadriaOxideMixture, 14)
                .inputFluids(GTMaterials.FluoroantimonicAcid.getFluid(3000))
                .outputItems(TagPrefix.dust, GTMaterials.AntimonyTrioxide, 5)
                .outputItems(TagPrefix.dust, GTMaterials.TitaniumTrifluoride, 7)
                .outputFluids(GTMaterials.ImpureNaquadriaSolution.getFluid(2000))
                .EUt(GTValues.VA[GTValues.IV])  // 7680 EU/t
                .duration(400)  // 20秒
                .save(provider);
    }



    /* ==== 统一注册入口 ==== */

}

