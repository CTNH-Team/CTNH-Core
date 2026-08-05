package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.BauxiteProcessingMaterials;
import io.github.cpearl0.ctnhcore.data.materials.RareEarthMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dustTiny;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Chlorine;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.CENTRIFUGE_RECIPES;

public class RareearthChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 研磨：稀土 -> 含铁稀土 + 石粉
        MACERATOR_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_fe_one"))
                .inputItems(dust, RareEarth, 32)
                .outputItems(dust, RareEarthMaterials.RARE_EARTH_FE_ONE, 32)
                .outputItems(dust, Stone, 12)
                .EUt(480).duration(60)
                .save(provider);

        // 电磁分选：含铁稀土 -> 含铁精磨稀土 + 磁性铁
        ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_fe_two"))
                .inputItems(dust, RareEarthMaterials.RARE_EARTH_FE_ONE, 32)
                .outputItems(dust, RareEarthMaterials.RARE_EARTH_FE_TWO, 16)
                .outputItems(dust, IronMagnetic, 32)
                .EUt(480).duration(240)
                .save(provider);

        // 化学浸洗：含铁精磨稀土 + 盐酸 -> 精磨稀土 + 三氯化铁
        CHEMICAL_BATH_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_intensive_research"))
                .inputItems(dust, RareEarthMaterials.RARE_EARTH_FE_TWO, 16)
                .inputFluids(HydrochloricAcid.getFluid(9000))
                .outputItems(dust, RareEarthMaterials.RARE_EARTH_INTENSIVE_RESEARCH, 12)
                .outputFluids(Iron3Chloride.getFluid(3000))
                .duration(200).EUt(480)
                .save(provider);

        // 搅拌：精磨稀土 + 独居石 + 氟碳铈矿 -> 稀土混合物
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_mixture"))
                .inputItems(dust, RareEarthMaterials.RARE_EARTH_INTENSIVE_RESEARCH)
                .inputItems(dust, Bastnasite)
                .inputItems(dust, Monazite)
                .outputItems(dust, RareEarthMaterials.RARE_EARTH_MIXTURE, 3)
                .EUt(480).duration(360)
                .save(provider);

        // 搅拌：稀土混合物 + 氢氧化钠 + 水 -> 碱式稀土混合物
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_mixture_oh"))
                .circuitMeta(1)
                .inputItems(dust, RareEarthMaterials.RARE_EARTH_MIXTURE, 4)
                .inputItems(dust, SodiumHydroxide, 4)
                .inputFluids(Water.getFluid(4000))
                .outputFluids(RareEarthMaterials.RARE_EARTH_MIXTURE_OH.getFluid(1000))
                .EUt(480).duration(480)
                .save(provider);

        // 化学反应：碱式稀土混合物 + 盐酸 -> 稀土氯化物溶液 + 盐
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_chloride_solution_2"))
                .inputFluids(RareEarthMaterials.RARE_EARTH_MIXTURE_OH.getFluid(1000))
                .inputFluids(HydrochloricAcid.getFluid(6000))
                .outputFluids(BauxiteProcessingMaterials.RARE_EARTH_CHLORIDE_SOLUTION.getFluid(6000))
                .outputItems(dust, Salt, 8)
                .EUt(480).duration(120)
                .save(provider);

        // 流体加热器：稀土氯化物溶液 -> 沸腾稀土氯化物
        FLUID_HEATER_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_chloride_boil"))
                .inputFluids(BauxiteProcessingMaterials.RARE_EARTH_CHLORIDE_SOLUTION.getFluid(3000))
                .outputFluids(RareEarthMaterials.RARE_EARTH_CHLORIDE_BOIL.getFluid(3000))
                .EUt(120).duration(200)
                .save(provider);

        // 结晶器：沸腾稀土氯化物 -> 稀土晶体 + 水
        CTNHRecipeTypes.CRYSTALLIZER.recipeBuilder(CTNHCore.id("rare_earth_crystals"))
                .inputFluids(RareEarthMaterials.RARE_EARTH_CHLORIDE_BOIL.getFluid(3000))
                .outputItems(dust, RareEarthMaterials.RARE_EARTH_CRYSTALS, 4)
                .outputFluids(Water.getFluid(6000))
                .EUt(1920).duration(480).blastFurnaceTemp(4500)
                .save(provider);

        // 离子交换器：稀土晶体 -> 高/低亲和力稀土
        CTNHRecipeTypes.ION_EXCHANGER.recipeBuilder(CTNHCore.id("rare_earth_high_affinity"))
                .inputItems(dust, RareEarthMaterials.RARE_EARTH_CRYSTALS)
                .inputFluids(HydrochloricAcid.getFluid(1500))
                .outputFluids(RareEarthMaterials.RARE_EARTH_HIGH_AFFINITY.getFluid(1000))
                .outputFluids(RareEarthMaterials.RARE_EARTH_LOW_AFFINTY.getFluid(1000))
                .outputFluids(DilutedHydrochloricAcid.getFluid(4000))
                .outputFluids(BauxiteProcessingMaterials.RED_MUD.getFluid(1000))
                .EUt(1920).duration(960)
                .save(provider);

        // 离心：低亲和力稀土 -> 轻稀土 + 中稀土
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_low"))
                .inputFluids(RareEarthMaterials.RARE_EARTH_LOW_AFFINTY.getFluid(1000))
                .outputItems(dust, RareEarthMaterials.RARE_EARTH_LOW, 4)
                .outputItems(dust, RareEarthMaterials.RARE_EARTH_MIDDLE, 2)
                .EUt(480).duration(800)
                .save(provider);

        // 离心：高亲和力稀土 -> 中稀土 + 重稀土
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_high"))
                .inputFluids(RareEarthMaterials.RARE_EARTH_HIGH_AFFINITY.getFluid(1000))
                .outputItems(dust, RareEarthMaterials.RARE_EARTH_MIDDLE, 4)
                .outputItems(dust, RareEarthMaterials.RARE_EARTH_HIGH, 2)
                .EUt(1920).duration(800)
                .save(provider);

        // 化学浸洗：重稀土 + 氢氟酸 -> 氟浸没重稀土 + 铕
        CHEMICAL_BATH_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_high_fluoride"))
                .inputItems(dust, RareEarthMaterials.RARE_EARTH_HIGH)
                .inputFluids(HydrofluoricAcid.getFluid(4000))
                .outputFluids(RareEarthMaterials.RARE_EARTH_HIGH_FLUORIDE.getFluid(1000))
                .outputItems(ChemicalHelper.get(dustTiny, Europium))
                .EUt(6144).duration(60)
                .save(provider);

        // 化学浸洗：中稀土 + 氢氟酸 -> 氟浸没中稀土 + 钐
        CHEMICAL_BATH_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_middle_fluoride"))
                .inputItems(dust, RareEarthMaterials.RARE_EARTH_MIDDLE)
                .inputFluids(HydrofluoricAcid.getFluid(4000))
                .outputFluids(RareEarthMaterials.RARE_EARTH_MIDDLE_FLUORIDE.getFluid(1000))
                .outputItems(ChemicalHelper.get(dustTiny, Samarium))
                .EUt(1920).duration(60)
                .save(provider);

        // 化学浸洗：轻稀土 + 氢氟酸 -> 氟浸没轻稀土 + 钕
        CHEMICAL_BATH_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_low_fluoride"))
                .inputItems(dust, RareEarthMaterials.RARE_EARTH_LOW)
                .inputFluids(HydrofluoricAcid.getFluid(4000))
                .outputFluids(RareEarthMaterials.RARE_EARTH_LOW_FLUORIDE.getFluid(1000))
                .outputItems(ChemicalHelper.get(dustTiny, Neodymium))
                .EUt(480).duration(60)
                .save(provider);

        // 真空烧结：氟浸没轻稀土 -> 蒸汽 + 氟
        CTNHRecipeTypes.VACUUM_SINTERING.recipeBuilder(CTNHCore.id("rare_earth_low_fluoride_steam"))
                .inputFluids(RareEarthMaterials.RARE_EARTH_LOW_FLUORIDE.getFluid(1000))
                .outputFluids(RareEarthMaterials.RARE_EARTH_LOW_FLUORIDE_STEAM.getFluid(1000))
                .outputFluids(Fluorine.getFluid(4000))
                .EUt(480).duration(60).blastFurnaceTemp(4500)
                .save(provider);

        // 真空烧结：氟浸没重稀土 -> 蒸汽 + 氟
        CTNHRecipeTypes.VACUUM_SINTERING.recipeBuilder(CTNHCore.id("rare_earth_high_fluoride_steam"))
                .inputFluids(RareEarthMaterials.RARE_EARTH_HIGH_FLUORIDE.getFluid(1000))
                .outputFluids(RareEarthMaterials.RARE_EARTH_HIGH_FLUORIDE_STEAM.getFluid(1000))
                .outputFluids(Fluorine.getFluid(4000))
                .EUt(6144).duration(60).blastFurnaceTemp(4500)
                .save(provider);

        // 真空烧结：氟浸没中稀土 -> 蒸汽 + 氟
        CTNHRecipeTypes.VACUUM_SINTERING.recipeBuilder(CTNHCore.id("rare_earth_middle_fluoride_steam"))
                .inputFluids(RareEarthMaterials.RARE_EARTH_MIDDLE_FLUORIDE.getFluid(1000))
                .outputFluids(RareEarthMaterials.RARE_EARTH_MIDDLE_FLUORIDE_STEAM.getFluid(1000))
                .outputFluids(Fluorine.getFluid(4000))
                .EUt(1920).duration(60).blastFurnaceTemp(4500)
                .save(provider);

        // 冷凝分离：轻稀土蒸汽
        CTNHRecipeTypes.CONDENSING_DISCRETE
                .recipeBuilder(CTNHCore.id("lanthanum_cerium_praseodymium_neodymium_oxygen_mixture"))
                .inputFluids(RareEarthMaterials.RARE_EARTH_LOW_FLUORIDE_STEAM.getFluid(1000))
                .outputItems(dust, RareEarthMaterials.LANTHANUM_CERIUM_PRASEODYMIUM_NEODYMIUM_OXYGEN_MIXTURE, 8)
                .outputItems(dust, RareEarthMaterials.EUROPIUM_GADOLINIUM_TERBIUM_DYSPROSIUM_OXYGEN_MIXTURE, 4)
                .outputItems(dust, RareEarthMaterials.YTTRIUM_HOLMIUM_ERBIUM_THULIUM_YTTERBIUM_OXYGEN_LUTETIUM_MIXTURE,
                        2)
                .EUt(6144).duration(240)
                .save(provider);

        // 冷凝分离：中稀土蒸汽
        CTNHRecipeTypes.CONDENSING_DISCRETE
                .recipeBuilder(CTNHCore.id("europium_gadolinium_terbium_dysprosium_oxygen_mixture"))
                .inputFluids(RareEarthMaterials.RARE_EARTH_MIDDLE_FLUORIDE_STEAM.getFluid(1000))
                .outputItems(dust, RareEarthMaterials.LANTHANUM_CERIUM_PRASEODYMIUM_NEODYMIUM_OXYGEN_MIXTURE, 2)
                .outputItems(dust, RareEarthMaterials.EUROPIUM_GADOLINIUM_TERBIUM_DYSPROSIUM_OXYGEN_MIXTURE, 8)
                .outputItems(dust, RareEarthMaterials.YTTRIUM_HOLMIUM_ERBIUM_THULIUM_YTTERBIUM_OXYGEN_LUTETIUM_MIXTURE,
                        4)
                .EUt(6144).duration(240)
                .save(provider);

        // 冷凝分离：重稀土蒸汽
        CTNHRecipeTypes.CONDENSING_DISCRETE
                .recipeBuilder(CTNHCore.id("yttrium_holmium_erbium_thulium_ytterbium_oxygen_lutetium_mixture"))
                .inputFluids(RareEarthMaterials.RARE_EARTH_HIGH_FLUORIDE_STEAM.getFluid(1000))
                .outputItems(dust, RareEarthMaterials.LANTHANUM_CERIUM_PRASEODYMIUM_NEODYMIUM_OXYGEN_MIXTURE, 2)
                .outputItems(dust, RareEarthMaterials.EUROPIUM_GADOLINIUM_TERBIUM_DYSPROSIUM_OXYGEN_MIXTURE, 4)
                .outputItems(dust, RareEarthMaterials.YTTRIUM_HOLMIUM_ERBIUM_THULIUM_YTTERBIUM_OXYGEN_LUTETIUM_MIXTURE,
                        8)
                .EUt(6144).duration(240)
                .save(provider);

        // 化学反应：轻稀土氧化混合物 + 盐酸 -> 氯化物 + 水
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("lan_cer_pra_neo_chloride"))
                .inputItems(dust, RareEarthMaterials.LANTHANUM_CERIUM_PRASEODYMIUM_NEODYMIUM_OXYGEN_MIXTURE, 5)
                .inputFluids(HydrochloricAcid.getFluid(24000))
                .outputFluids(Water.getFluid(12000))
                .outputItems(dust, RareEarthMaterials.LAN_CER_PRA_NEO_CHLORIDE, 5)
                .EUt(6144).duration(120)
                .save(provider);

        // 化学反应：重稀土氧化混合物 + 盐酸 -> 氯化物 + 水
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("ytt_hol_erb_thu_ytt_chloride"))
                .inputItems(dust, RareEarthMaterials.YTTRIUM_HOLMIUM_ERBIUM_THULIUM_YTTERBIUM_OXYGEN_LUTETIUM_MIXTURE,
                        5)
                .inputFluids(HydrochloricAcid.getFluid(24000))
                .outputFluids(Water.getFluid(12000))
                .outputItems(dust, RareEarthMaterials.YTT_HOL_ERB_THU_YTT_CHLORIDE, 5)
                .EUt(6144).duration(480)
                .save(provider);

        // 化学反应：中稀土氧化混合物 + 盐酸 -> 氯化物 + 水
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("eur_gado_ter_dyspr_chloride"))
                .inputItems(dust, RareEarthMaterials.EUROPIUM_GADOLINIUM_TERBIUM_DYSPROSIUM_OXYGEN_MIXTURE, 5)
                .inputFluids(HydrochloricAcid.getFluid(24000))
                .outputFluids(Water.getFluid(12000))
                .outputItems(dust, RareEarthMaterials.EUR_GADO_TER_DYSPR_CHLORIDE, 5)
                .EUt(6144).duration(240)
                .save(provider);

        // 离心：轻稀土氯化物 -> 单质稀土 + 氯气
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("lanthanum_dust"))
                .inputItems(dust, RareEarthMaterials.LAN_CER_PRA_NEO_CHLORIDE, 5)
                .outputItems(dust, Lanthanum)
                .outputItems(dust, Cerium)
                .outputItems(dust, Praseodymium)
                .outputItems(dust, Neodymium)
                .outputItems(dust, Promethium)
                .outputFluids(Chlorine.getFluid(24000))
                .EUt(6144).duration(120).blastFurnaceTemp(5200)
                .save(provider);

        // 离心：中稀土氯化物 -> 单质稀土 + 氯气
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("samarium_dust"))
                .inputItems(dust, RareEarthMaterials.EUR_GADO_TER_DYSPR_CHLORIDE, 5)
                .outputItems(dust, Samarium)
                .outputItems(dust, Europium)
                .outputItems(dust, Gadolinium)
                .outputItems(dust, Terbium)
                .outputItems(dust, Dysprosium)
                .outputItems(dust, Holmium)
                .outputFluids(Chlorine.getFluid(24000))
                .EUt(6144).duration(120).blastFurnaceTemp(5200)
                .save(provider);

        // 离心：重稀土氯化物 -> 单质稀土 + 氯气
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("holmium_dust"))
                .inputItems(dust, RareEarthMaterials.YTT_HOL_ERB_THU_YTT_CHLORIDE, 5)
                .outputItems(dust, Erbium)
                .outputItems(dust, Thulium)
                .outputItems(dust, Ytterbium)
                .outputItems(dust, Lutetium)
                .outputItems(dust, Scandium)
                .outputItems(dust, Yttrium)
                .outputFluids(Chlorine.getFluid(24000))
                .EUt(6144).duration(120).blastFurnaceTemp(5200)
                .save(provider);
    }
}
