package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.recipe.NeutronActivatorCondition;
import io.github.cpearl0.ctnhcore.data.materials.*;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.api.recipe.ingredient.fluid.FluidIngredient;
import com.gregtechceu.gtceu.common.data.GTItems;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dustSmall;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dustTiny;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.ingot;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHItems.NeutronSource;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.NeutroniumMixture;

public class GeyanChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 基岩主线

        // 基岩钻机 - 基岩尘
        CTNHRecipeTypes.BEDROCK_DRILLING_RIGS.recipeBuilder(CTNHCore.id("bedrock_dust_1"))
                .circuitMeta(1)
                .inputFluids(DrillingFluid.getFluid(500))
                .outputItems(dust, BedrockMaterials.BEDROCK_DUST)
                .outputFluids(BedrockMaterials.BEDROCK_FOG.getFluid(1000))
                .EUt(491520).duration(100)
                .save(provider);

        // 化学气相沉积 - 基岩尘气分离
        CTNHRecipeTypes.CHEMICAL_VAPOR_DEPOSITION.recipeBuilder(CTNHCore.id("dust_gas_separation"))
                .inputFluids(BedrockMaterials.BEDROCK_FOG.getFluid(1000))
                .outputItems(dust, BedrockMaterials.BEDROCK_DUST)
                .outputFluids(BedrockMaterials.BEDROCK_FOG_RE.getFluid(1000))
                .EUt(122880).duration(50)
                .save(provider);

        // 搅拌机 - 基岩尘灰溶液
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("bedrock_dust_ash_solution"))
                .chancedInput(ChemicalHelper.get(dust, AmmoniumChloride, 2), 500, 0)
                .chancedInput(ChemicalHelper.get(dust, Osmium, 2), 100, 0)
                .inputFluids(BedrockMaterials.BEDROCK_FOG_RE.getFluid(1000), Water.getFluid(1000))
                .outputFluids(BedrockMaterials.BEDROCK_DUST_SOLUTION.getFluid(1000))
                .EUt(122880).duration(120)
                .save(provider);

        // 离心机 - 气液分离 1
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("gas_liquid_separation_1"))
                .inputFluids(BedrockMaterials.BEDROCK_DUST_SOLUTION.getFluid(3000))
                .outputFluids(BedrockMaterials.BEDROCK_FOG_RE_H.getFluid(1000),
                        BedrockMaterials.BEDROCK_FOG_RE_M.getFluid(1000),
                        BedrockMaterials.BEDROCK_FOG_RE_L.getFluid(1000))
                .EUt(122880).duration(240)
                .save(provider);

        // 离心机 - 气液分离 2
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("gas_liquid_separation_2"))
                .inputFluids(BedrockMaterials.BEDROCK_FOG_RE_H.getFluid(1000))
                .outputItems(dust, Iridium, 3)
                .outputItems(dust, Osmium, 2)
                .chancedOutput(dustSmall, BedrockMaterials.BEDROCK_DUST, 5000, 0)
                .outputFluids(BedrockMaterials.TARANLIQUID.getFluid(1000))
                .EUt(122880).duration(140)
                .save(provider);

        // 离心机 - 气液分离 3
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("gas_liquid_separation_3"))
                .inputFluids(BedrockMaterials.BEDROCK_FOG_RE_M.getFluid(1000))
                .outputItems(dust, Ruthenium, 4)
                .outputItems(dust, Rhodium, 5)
                .chancedOutput(dustSmall, BedrockMaterials.BEDROCK_DUST, 5000, 0)
                .outputFluids(BedrockMaterials.TARANLIQUID.getFluid(500))
                .EUt(122880).duration(140)
                .save(provider);

        // 离心机 - 气液分离 4
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("gas_liquid_separation_4"))
                .inputFluids(BedrockMaterials.BEDROCK_FOG_RE_L.getFluid(1000))
                .outputItems(dust, Platinum, 6)
                .outputItems(dust, Palladium, 7)
                .chancedOutput(dustSmall, BedrockMaterials.BEDROCK_DUST, 5000, 0)
                .outputFluids(BedrockMaterials.TARANLIQUID.getFluid(250))
                .EUt(122880).duration(140)
                .save(provider);

        // 蒸馏室 - 烟分离
        DISTILLERY_RECIPES.recipeBuilder(CTNHCore.id("smoke_separation"))
                .inputFluids(BedrockMaterials.TARANLIQUID.getFluid(1000))
                .outputFluids(BedrockMaterials.BEDROCK_SMOKE.getFluid(500))
                .outputItems(dust, BedrockMaterials.TARANMIX, 4)
                .EUt(491520).duration(100)
                .save(provider);

        // 离心机 - 塔兰结晶
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("taran_crystallization"))
                .inputFluids(BedrockMaterials.BEDROCK_SMOKE.getFluid(1000))
                .outputItems(dustSmall, BedrockMaterials.TARANMIX, 2)
                .outputFluids(BedrockMaterials.BEDROCK_SMOKE_RE.getFluid(1000))
                .duration(200).EUt(491520)
                .save(provider);

        // 搅拌机 - 基岩烟分离
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("bedrock_fog_separation"))
                .inputFluids(BedrockMaterials.BEDROCK_SMOKE_RE.getFluid(1000), Water.getFluid(1000))
                .chancedInput(ChemicalHelper.get(dust, AmmoniumChloride, 2), 1000, 0)
                .chancedInput(ChemicalHelper.get(dust, Naquadria, 2), 500, 0)
                .outputFluids(BedrockMaterials.BEDROCK_SOOT_SOLUTION.getFluid(1000))
                .EUt(491520).duration(120)
                .save(provider);

        // 裂化机 - 氟化
        CRACKING_RECIPES.recipeBuilder(CTNHCore.id("fluorination"))
                .inputFluids(Fluorine.getFluid(1000), BedrockMaterials.BEDROCK_SOOT_SOLUTION.getFluid(1000))
                .outputFluids(BedrockMaterials.F_BEDROCK_SMOKE_RE.getFluid(2000))
                .EUt(491520).duration(250)
                .save(provider);

        // 蒸馏塔 - 基岩烟蒸馏
        DISTILLATION_RECIPES.recipeBuilder(CTNHCore.id("bedrock_smoke_distillation"))
                .inputFluids(BedrockMaterials.F_BEDROCK_SMOKE_RE.getFluid(2000))
                .outputFluids(BedrockMaterials.BEDROCK_SMOKE_RE_L.getFluid(1000),
                        BedrockMaterials.BEDROCK_SMOKE_RE_M.getFluid(500),
                        BedrockMaterials.BEDROCK_SMOKE_RE_H.getFluid(250),
                        Fluorine.getFluid(500))
                .outputItems(dustTiny, BedrockMaterials.TARANMIX, 2)
                .EUt(491520).duration(500)
                .save(provider);

        // 离心机 - 基岩烟分离 1
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("bedrock_smoke_separation_1"))
                .inputFluids(BedrockMaterials.BEDROCK_SMOKE_RE_L.getFluid(1000))
                .outputFluids(BedrockMaterials.AX_JIYANQI.getFluid(100),
                        BedrockMaterials.NQ_FUELMK1.getFluid(75))
                .outputItems(dust, NaquadahMaterials.NaquadahOxideMixture, 3)
                .outputItems(dust, Samarium, 8)
                .EUt(1966080).duration(140)
                .save(provider);

        // 离心机 - 基岩烟分离 2
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("bedrock_smoke_separation_2"))
                .inputFluids(BedrockMaterials.BEDROCK_SMOKE_RE_M.getFluid(1000))
                .outputFluids(BedrockMaterials.AX_JIYANQI.getFluid(200),
                        BedrockMaterials.NQ_FUELMK2.getFluid(45))
                .outputItems(dust, NaquadahMaterials.EnrichedNaquadahOxideMixture, 3)
                .outputItems(dust, Europium, 4)
                .EUt(1966080).duration(140)
                .save(provider);

        // 离心机 - 基岩烟分离 3
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("bedrock_smoke_separation_3"))
                .inputFluids(BedrockMaterials.BEDROCK_SMOKE_RE_H.getFluid(1000))
                .outputFluids(BedrockMaterials.AX_JIYANQI.getFluid(300),
                        BedrockMaterials.NQ_FUELMK3.getFluid(15))
                .outputItems(dust, NaquadahMaterials.NaquadriaOxideMixture, 3)
                .outputItems(dust, Americium, 2)
                .EUt(1966080).duration(140)
                .save(provider);

        // 电弧炉 - 放射性材料分离
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("radioactive_material_separation"))
                .inputFluids(BedrockMaterials.BEDROCK_GAS.getFluid(1000))
                .circuitMeta(0)
                .outputItems(dust, BedrockMaterials.RADIOACTIVE_METALS_MIX)
                .outputFluids(BedrockMaterials.BEDROCK_GAS_RE.getFluid(1000))
                .duration(500).EUt(1966080)
                .blastFurnaceTemp(9000)
                .save(provider);

        // 中子活化器 - 基岩气中子活化
        CTNHRecipeTypes.NEUTRON_ACTIVATOR_RECIPES.recipeBuilder(CTNHCore.id("neutron_activation_of_bedrock_gas"))
                .inputFluids(BedrockMaterials.AX_JIYANQI.getFluid(1000))
                .outputFluids(BedrockMaterials.BEDROCK_GAS.getFluid(1000))
                .outputItems(dustTiny, BedrockMaterials.RADIOACTIVE_METALS_MIX, 4)
                .outputItems(dustTiny, Francium)
                .outputItems(dustTiny, NaquadahMaterials.NaquadriaOxideMixture, 3)
                .outputItems(dustSmall, Technetium, 2)
                .duration(100)
                .addCondition(new NeutronActivatorCondition(440, 600))
                .save(provider);

        // 裂化机 - 基岩气裂化
        CRACKING_RECIPES.recipeBuilder(CTNHCore.id("bedrock_gas_cracking"))
                .inputFluids(BedrockMaterials.BEDROCK_GAS_RE.getFluid(1000), Radon.getFluid(1000))
                .outputFluids(BedrockMaterials.DR_BEDROCK_GAS_RE.getFluid(2000))
                .EUt(1966080).duration(500)
                .save(provider);

        // 蒸馏塔 - 氡裂化蒸馏
        DISTILLATION_RECIPES.recipeBuilder(CTNHCore.id("radon_cracking_distillation"))
                .inputFluids(BedrockMaterials.DR_BEDROCK_GAS_RE.getFluid(2000))
                .outputFluids(BedrockMaterials.BEDROCK_GAS_RE_L.getFluid(400),
                        BedrockMaterials.BEDROCK_GAS_RE_M.getFluid(250),
                        BedrockMaterials.BEDROCK_GAS_RE_H.getFluid(100),
                        Radon.getFluid(500))
                .EUt(7864320).duration(400)
                .save(provider);

        // 离心机 - 基岩气离心 1
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("bedrock_gas_centrifugation_1"))
                .inputFluids(BedrockMaterials.BEDROCK_GAS_RE_L.getFluid(1000))
                .outputFluids(BedrockMaterials.TARANLIQUIDFUEL_L.getFluid(1000))
                .chancedOutput(dust, BedrockMaterials.BOUNDLESS, 1000, 3000)
                .EUt(7864320).duration(500)
                .save(provider);

        // 离心机 - 基岩气离心 2
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("bedrock_gas_centrifugation_2"))
                .inputFluids(BedrockMaterials.BEDROCK_GAS_RE_M.getFluid(1000))
                .outputFluids(BedrockMaterials.TARANLIQUIDFUEL_M.getFluid(500))
                .chancedOutput(dust, BedrockMaterials.BOUNDLESS, 3000, 2000)
                .EUt(7864320).duration(500)
                .save(provider);

        // 离心机 - 基岩气离心 3
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("bedrock_gas_centrifugation_3"))
                .inputFluids(BedrockMaterials.BEDROCK_GAS_RE_H.getFluid(1000))
                .outputFluids(BedrockMaterials.TARANLIQUIDFUEL_H.getFluid(250))
                .chancedOutput(dust, BedrockMaterials.BOUNDLESS, 5000, 1000)
                .EUt(7864320).duration(500)
                .save(provider);

        // 放射性处理产线 --- 基岩支线

        // 搅拌机 - 放射性材料富集
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("enrichment_of_radioactive_materials"))
                .inputItems(dust, Plutonium241)
                .inputItems(dust, BedrockMaterials.RADIOACTIVE_METALS_MIX)
                .inputItems(dust, Uranium235)
                .inputFluids(NitrationMixture.getFluid(4000))
                .outputItems(dust, BedrockMaterials.ENRICH_RADIOACTIVE_WASTE, 6)
                .EUt(491020).duration(100)
                .save(provider);

        // 大型化学反应器 - 放射性粒子
        LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("radioactive_particles"))
                .circuitMeta(1)
                .inputItems(dust, BedrockMaterials.ENRICH_RADIOACTIVE_WASTE)
                .inputFluids(PlatinumLineMaterials.AmmoniaMonohydrate.getFluid(1000))
                .outputFluids(BedrockMaterials.RADIOACTIVE_ION_SOLUTION.getFluid(2000))
                .EUt(114514).duration(20)
                .save(provider);

        // 中子活化器 - 放射性材料中子照射
        CTNHRecipeTypes.NEUTRON_ACTIVATOR_RECIPES
                .recipeBuilder(CTNHCore.id("neutron_irradiation_of_radioactive_materials"))
                .inputFluids(BedrockMaterials.RADIOACTIVE_ION_SOLUTION.getFluid(1000))
                .inputItems(dustSmall, Neutronium)
                .outputItems(dustSmall, Americium, 2)
                .outputItems(dustSmall, Radium, 2)
                .outputItems(dustSmall, Francium, 2)
                .outputFluids(BedrockMaterials.NEUTRON_IRRADIATION_OF_RADIOACTIVE_WASTE.getFluid(1000))
                .duration(100)
                .addCondition(new NeutronActivatorCondition(110, 600))
                .save(provider);

        // 离心机 - 中子物质分离
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("neutron_substance_separation"))
                .inputFluids(BedrockMaterials.NEUTRON_IRRADIATION_OF_RADIOACTIVE_WASTE.getFluid(1000))
                .outputFluids(Radon.getFluid(8000), Fluorine.getFluid(16000),
                        Helium.getFluid(FluidStorageKeys.PLASMA, 4000),
                        BedrockMaterials.NUCLEAR_WASTE_WATER.getFluid(16000))
                .outputItems(dust, BedrockMaterials.RADIATION_DUST, 3)
                .outputItems(dustSmall, Mendelevium, 2)
                .outputItems(dustSmall, Tritanium)
                .EUt(491020).duration(80)
                .save(provider);

        // 压缩机 - 辐射锭
        COMPRESSOR_RECIPES.recipeBuilder(CTNHCore.id("radiation_ingot"))
                .inputItems(dust, BedrockMaterials.RADIATION_DUST)
                .outputItems(ingot, BedrockMaterials.RADIOACTIVE_METAL_INGOT)
                .EUt(114514).duration(100)
                .save(provider);

        // 衰变罐 - 辐射锭衰变 1
        CTNHRecipeTypes.DECAY_POOLS.recipeBuilder(CTNHCore.id("decay_radiation_ingot_1"))
                .circuitMeta(0)
                .inputItems(ingot, BedrockMaterials.RADIOACTIVE_METAL_INGOT)
                .outputFluids(BedrockMaterials.LOW_LEVEL_RADIOACTIVE_DUST.getFluid(1000),
                        BedrockMaterials.HIGH_LEVEL_RADIOACTIVE_DUST.getFluid(1000))
                .outputItems(dustTiny, NeutroniumMixture, 6)
                .duration(800)
                .save(provider);

        // 衰变罐 - 辐射锭衰变 2
        CTNHRecipeTypes.DECAY_POOLS.recipeBuilder(CTNHCore.id("decay_radiation_ingot_2"))
                .circuitMeta(1)
                .inputItems(ingot, BedrockMaterials.RADIOACTIVE_METAL_INGOT)
                .outputFluids(BedrockMaterials.LOW_LEVEL_RADIOACTIVE_DUST.getFluid(1000),
                        BedrockMaterials.HIGH_LEVEL_RADIOACTIVE_DUST.getFluid(1000))
                .outputItems(dust, NeutroniumMixture, 6)
                .duration(40).EUt(491020)
                .save(provider);

        // 电弧炉 - 低放射性材料富集
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("enrichment_of_low_radioactive_materials"))
                .inputFluids(BedrockMaterials.LOW_LEVEL_RADIOACTIVE_DUST.getFluid(1000))
                .notConsumable(NeutronSource.asStack())
                .outputFluids(BedrockMaterials.CONCENTRATED_LOW_LEVEL_RADIOACTIVE_DUST.getFluid(1000))
                .outputItems(dust, Americium, 16)
                .outputItems(dust, EnderIOMaterials.MelodicAlloy, 8)
                .blastFurnaceTemp(9100).EUt(491020).duration(100)
                .save(provider);

        // 电弧炉 - 高放射性材料富集
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("enrichment_of_high_radioactive_materials"))
                .inputFluids(BedrockMaterials.HIGH_LEVEL_RADIOACTIVE_DUST.getFluid(1000))
                .notConsumable(NeutronSource.asStack())
                .outputFluids(BedrockMaterials.CONCENTRATED_HIGH_LEVEL_RADIOACTIVE_DUST.getFluid(1000))
                .outputItems(dust, Lutetium, 16)
                .outputItems(dust, EnderIOMaterials.StellarAlloy, 8)
                .blastFurnaceTemp(9100).EUt(491020).duration(100)
                .save(provider);

        // 聚变反应堆 - 以太
        FUSION_RECIPES.recipeBuilder(CTNHCore.id("aether"))
                .inputFluids(BedrockMaterials.CONCENTRATED_LOW_LEVEL_RADIOACTIVE_DUST.getFluid(1000))
                .inputFluids(BedrockMaterials.CONCENTRATED_HIGH_LEVEL_RADIOACTIVE_DUST.getFluid(1000))
                .outputFluids(BedrockMaterials.AETHER.getFluid(FluidStorageKeys.PLASMA, 2000))
                .fusionStartEU(600000000).EUt(491020).duration(200)
                .save(provider);

        // 等离子体发电机 - 以太锭
        PLASMA_GENERATOR_FUELS.recipeBuilder(CTNHCore.id("aether_ingot"))
                .inputFluids(BedrockMaterials.AETHER.getFluid(FluidStorageKeys.PLASMA, 4))
                .outputFluids(BedrockMaterials.AETHER.getFluid(4))
                .EUt(-491020).duration(300)
                .save(provider);

        // 离心机 - 核废水处理
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("nuclear_waste_water_treatment"))
                .inputFluids(BedrockMaterials.NUCLEAR_WASTE_WATER.getFluid(2000))
                .outputItems(dustTiny, Naquadria)
                .EUt(114514).duration(40)
                .save(provider);

        // P-507 产线

        // 大型化学反应器 - 2-乙基己醇 1
        LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("2-ethyl_hexanol_1"))
                .notConsumable(dust, BedrockMaterials.COPPER_CHROMIUM_CATALYST)
                .inputFluids(Diesel.getFluid(1000))
                .outputFluids(BedrockMaterials._2_ETHYLHEXANOL.getFluid(1000))
                .EUt(114514).duration(200)
                .save(provider);

        // 大型化学反应器 - 2-乙基己醇 2
        LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("2-ethyl_hexanol_2"))
                .notConsumable(dust, BedrockMaterials.COPPER_CHROMIUM_CATALYST)
                .inputFluids(SeedOil.getFluid(500))
                .outputFluids(BedrockMaterials._2_ETHYLHEXANOL.getFluid(1000))
                .EUt(114514).duration(200)
                .save(provider);

        // 化学反应器 - 磷酸 1
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("phosphoric_acid_1"))
                .circuitMeta(0)
                .inputItems(dust, PhosphorusPentoxide, 14)
                .inputFluids(Water.getFluid(6000))
                .outputFluids(PhosphoricAcid.getFluid(4000))
                .EUt(16).duration(50)
                .save(provider);

        // 化学反应器 - 亚磷酸 1
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("hypo_phosphoric_acid_1"))
                .circuitMeta(1)
                .inputItems(dust, PhosphorusPentoxide, 14)
                .inputFluids(Water.getFluid(3000))
                .outputFluids(BedrockMaterials.PHOSPHOROUS_ACID.getFluid(2000))
                .EUt(120).duration(200)
                .save(provider);

        // 大型化学反应器 - 亚磷酸二甲酯
        LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("dimethyl_hypo_phosphite"))
                .inputFluids(Methanol.getFluid(2000), BedrockMaterials.PHOSPHOROUS_ACID.getFluid(1000))
                .outputFluids(BedrockMaterials.DIMETHYL_PHOSPHITE.getFluid(1000), Water.getFluid(2000))
                .EUt(1920).duration(200)
                .save(provider);

        // 大型化学反应器 - 磷酸二甲酯-2-乙基己醇
        LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("phosphoric_acid_dimethyl_ester_2-ethyl_hexanol"))
                .inputFluids(BedrockMaterials.DIMETHYL_PHOSPHITE.getFluid(1000),
                        BedrockMaterials._2_ETHYLHEXANOL.getFluid(2000))
                .outputFluids(Methanol.getFluid(2000),
                        BedrockMaterials.DI_2_ETHYLHEXYL_PHOSPHITE.getFluid(1000))
                .EUt(6144).duration(250)
                .save(provider);

        // 大型化学反应器 - 氯代环己烷
        LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("chlorinated_cyclohexane"))
                .inputFluids(Chlorine.getFluid(1000), Cyclohexane.getFluid(1000))
                .outputFluids(BedrockMaterials.CHLOROCYCLOHEXANE.getFluid(1000),
                        HydrochloricAcid.getFluid(1000))
                .EUt(480).duration(50)
                .save(provider);

        // 大型化学反应器 - 2-氯乙基己醇
        LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("2-chloroethyl_hexanol"))
                .inputFluids(BedrockMaterials.CHLOROCYCLOHEXANE.getFluid(1000), Acetone.getFluid(1000))
                .outputFluids(BedrockMaterials._2_ETHYLHEXANOL.getFluid(1000))
                .EUt(24678).duration(100)
                .save(provider);

        // 大型化学反应器 - 氯代2-乙基己烷
        LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("2-cl-yijijichun"))
                .inputFluids(Chlorine.getFluid(1000), BedrockMaterials._2_ETHYLHEXANOL.getFluid(1000))
                .outputFluids(BedrockMaterials._1_CHLORO_2_ETHYLHEXANE.getFluid(1000),
                        HydrochloricAcid.getFluid(1000))
                .EUt(24678).duration(100)
                .save(provider);

        // 大型化学反应器 - 膦酸酯
        LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("phosphonate_ester"))
                .circuitMeta(0)
                .inputFluids(BedrockMaterials._1_CHLORO_2_ETHYLHEXANE.getFluid(1000),
                        BedrockMaterials.DI_2_ETHYLHEXYL_PHOSPHITE.getFluid(1000))
                .outputFluids(BedrockMaterials.PHOSPHONATE.getFluid(1000))
                .EUt(114514).duration(500)
                .save(provider);

        // 大型化学反应器 - β-氨基膦酸酯
        LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("beta_phosphonate_ester"))
                .circuitMeta(1)
                .inputFluids(BedrockMaterials._1_CHLORO_2_ETHYLHEXANE.getFluid(1000),
                        BedrockMaterials.DI_2_ETHYLHEXYL_PHOSPHITE.getFluid(1000))
                .outputFluids(BedrockMaterials.BETA_AMINOPHOSPHONATE.getFluid(1000))
                .EUt(24768).duration(500)
                .save(provider);

        // 脱水机 - 膦酸酯脱水
        CTNHRecipeTypes.DEHYDRATOR_RECIPES.recipeBuilder(CTNHCore.id("phosphonate_ester_dehydration"))
                .inputFluids(BedrockMaterials.PHOSPHONATE.getFluid(1000))
                .inputItems(GTItems.FLUID_CELL.asStack())
                .outputFluids(BedrockMaterials.P507_EXTRACTANT.getFluid(1000), Water.getFluid(1000))
                .outputItems(GTItems.FLUID_CELL.asStack())
                .EUt(24768).duration(150)
                .save(provider);

        // 搅拌机 - 铜铬催化剂
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("copper_chromium_catalyst"))
                .circuitMeta(10)
                .inputItems(dust, Copper)
                .inputItems(dust, Chromium)
                .outputItems(dust, BedrockMaterials.COPPER_CHROMIUM_CATALYST)
                .EUt(1920).duration(1000)
                .save(provider);

        // 塔兰金属混合物的处理

        // 蒸馏室 - 塔兰处理 0
        DISTILLERY_RECIPES.recipeBuilder(CTNHCore.id("talanchuli0"))
                .inputItems(dust, BedrockMaterials.TARANMIX, 5)
                .inputFluids(Helium3.getFluid(1000))
                .outputFluids(BedrockMaterials.TARANIUM_DIRTY_HELIUM3.getFluid(1000))
                .EUt(24720).duration(100)
                .save(provider);

        // 离心机 - 塔兰处理 1
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("taran_processing_1"))
                .inputFluids(BedrockMaterials.TARANIUM_DIRTY_HELIUM3.getFluid(1000))
                .outputFluids(BedrockMaterials.TARANIUM_ENRICHED_LIQUID_HELIUM3.getFluid(1000),
                        BedrockMaterials.TARANIUM_HALF_LIFE_LIQUID_HELIUM3.getFluid(1000),
                        BedrockMaterials.TARANIUM_DEPLETED_LIQUID_HELIUM3.getFluid(1000))
                .EUt(122880).duration(100)
                .save(provider);

        // 离心机 - 塔兰处理 2
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("taran_processing_2"))
                .inputFluids(BedrockMaterials.TARANIUM_HALF_LIFE_LIQUID_HELIUM3.getFluid(1000))
                .outputFluids(BedrockMaterials.TARANIUM_ENRICHED_LIQUID_HELIUM3.getFluid(500),
                        BedrockMaterials.TARANIUM_DEPLETED_LIQUID_HELIUM3.getFluid(500))
                .EUt(122880).duration(200)
                .save(provider);

        // 聚变反应堆 - 塔兰处理 3
        FUSION_RECIPES.recipeBuilder(CTNHCore.id("taran_processing_3"))
                .inputFluids(BedrockMaterials.TARANIUM_ENRICHED_LIQUID_HELIUM3.getFluid(1000),
                        Helium3.getFluid(1000))
                .outputFluids(BedrockMaterials.TARANIUM_ENRICHED_DIRTY_HELIUM_PLASMA.getFluid(
                        FluidStorageKeys.PLASMA, 1000))
                .EUt(122880).duration(100)
                .fusionStartEU(300000000)
                .save(provider);

        // 离心机 - 塔兰处理 4
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("taran_processing_4"))
                .notConsumable(GTItems.ITEM_MAGNET_HV.asStack())
                .inputFluids(BedrockMaterials.TARANIUM_ENRICHED_DIRTY_HELIUM_PLASMA.getFluid(
                        FluidStorageKeys.PLASMA, 1000))
                .outputFluids(BedrockMaterials.TARANIUM_ENRICHED_HELIUM4_PLASMA.getFluid(
                        FluidStorageKeys.PLASMA, 1000),
                        BedrockMaterials.TARANIUM_DEPLETED_HELIUM_PLASMA.getFluid(FluidStorageKeys.PLASMA, 1000),
                        Hydrogen.getFluid(8000))
                .EUt(122880).duration(100)
                .save(provider);

        // 搅拌机 - P-507
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("p-507"))
                .inputFluids(BedrockMaterials.P507_EXTRACTANT.getFluid(1000))
                .inputItems(dust, BedrockMaterials.ADAMANT_MUD)
                .outputFluids(BedrockMaterials.ADAMANTITELIQUID.getFluid(1000))
                .EUt(24678).duration(100)
                .save(provider);

        // 蒸馏塔 - 精金分离
        DISTILLATION_RECIPES.recipeBuilder(CTNHCore.id("refined_gold_separation"))
                .inputFluids(BedrockMaterials.ADAMANTITELIQUID.getFluid(1000))
                .outputItems(dust, NewExplosivesProductionMaterials.CHALCOGEN_ANODE_MUD, 16)
                .outputFluids(BedrockMaterials.P507_EXTRACTANT.getFluid(1000),
                        BedrockMaterials.REFINED_GOLD_AQ.getFluid(1000),
                        BedrockMaterials.REFINED_GOLD_AQ_LOW.getFluid(10))
                .EUt(114514).duration(320)
                .save(provider);

        // 差速离心机 - 精金分离纯化
        CTNHRecipeTypes.DIFFERENTIAL_CENTRIFUGE_RECIPES
                .recipeBuilder(CTNHCore.id("refined_gold_separation_and_purification"))
                .circuitMeta(1)
                .inputFluids(BedrockMaterials.REFINED_GOLD_AQ.getFluid(1000))
                .outputItems(dust, NaquadahEnriched, 6)
                .outputItems(dust, BedrockMaterials.ADAMANTITE, 3)
                .EUt(122340).duration(120)
                .save(provider);

        // 等离子体冷凝器 - 塔兰处理 5
        CTNHRecipeTypes.PLASMA_CONDENSER_RECIPES.recipeBuilder(CTNHCore.id("taran_processing_5"))
                .inputFluids(BedrockMaterials.TARANIUM_ENRICHED_HELIUM4_PLASMA.getFluid(
                        FluidStorageKeys.PLASMA, 1000),
                        Helium.getFluid(FluidStorageKeys.LIQUID, 4000))
                .outputFluids(BedrockMaterials.TARANIUM_ENRICHED_LIQUID_HELIUM4.getFluid(1000),
                        Helium.getFluid(FluidStorageKeys.PLASMA, 4000))
                .EUt(122340).duration(150)
                .save(provider);

        // 等离子体冷凝器 - 塔兰处理 6
        CTNHRecipeTypes.PLASMA_CONDENSER_RECIPES.recipeBuilder(CTNHCore.id("taran_processing_6"))
                .inputFluids(BedrockMaterials.TARANIUM_DEPLETED_HELIUM_PLASMA.getFluid(
                        FluidStorageKeys.PLASMA, 1000),
                        Helium.getFluid(FluidStorageKeys.LIQUID, 4000))
                .outputFluids(Helium.getFluid(FluidStorageKeys.PLASMA, 4000))
                .outputItems(dust, BedrockMaterials.ADAMANT_MUD, 8)
                .EUt(122340).duration(150)
                .save(provider);

        // 离心机 - 塔兰处理 7
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("taran_processing_7"))
                .inputFluids(BedrockMaterials.TARANIUM_DEPLETED_LIQUID_HELIUM3.getFluid(1000))
                .outputFluids(Helium3.getFluid(333),
                        BauxiteProcessingMaterials.RED_SLURRY.getFluid(16000))
                .outputItems(dust, BedrockMaterials.TARANMIX)
                .EUt(24768).duration(120)
                .save(provider);

        // 化学气相沉积 - 塔兰处理 8
        CTNHRecipeTypes.CHEMICAL_VAPOR_DEPOSITION.recipeBuilder(CTNHCore.id("taran_processing_8"))
                .inputFluids(BedrockMaterials.TARANIUM_ENRICHED_LIQUID_HELIUM4.getFluid(1000))
                .outputItems(dust, BedrockMaterials.TARANIUM, 2)
                .outputFluids(BedrockMaterials.TARANIUM_DEPLETED_LIQUID_HELIUM.getFluid(1000))
                .EUt(122340).duration(500)
                .save(provider);

        // 离心机 - 塔兰处理 9
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("taran_processing_9"))
                .inputFluids(BedrockMaterials.TARANIUM_DEPLETED_LIQUID_HELIUM.getFluid(1000))
                .outputItems(dust, BedrockMaterials.ADAMANT_MUD)
                .duration(120).EUt(122340)
                .save(provider);

        // 蒸馏塔 - 精金馏分
        DISTILLATION_RECIPES.recipeBuilder(CTNHCore.id("refined_gold_fractionation"))
                .inputFluids(BedrockMaterials.REFINED_GOLD_AQ_LOW.getFluid(1000))
                .outputFluids(Helium.getFluid(500), Neon.getFluid(250),
                        CTNHMaterials.SNOW_STEEL.getFluid(250))
                .EUt(120).duration(3000)
                .save(provider);

        // 等离子体冷凝器 - 以太等离子体冷凝
        CTNHRecipeTypes.PLASMA_CONDENSER_RECIPES.recipeBuilder(CTNHCore.id("aether_plasma_condensation"))
                .inputFluids(BedrockMaterials.AETHER.getFluid(FluidStorageKeys.PLASMA, 1000),
                        Helium.getFluid(FluidStorageKeys.LIQUID, 4000))
                .outputFluids(BedrockMaterials.AETHER.getFluid(1000),
                        Helium.getFluid(FluidStorageKeys.PLASMA, 4000))
                .EUt(1920152).duration(150)
                .save(provider);

        // 聚变反应堆 - 精金等离子体聚合
        FUSION_RECIPES.recipeBuilder(CTNHCore.id("refined_gold_plasma_polymerization"))
                .inputFluids(BedrockMaterials.ADAMANTITE.getFluid(1000),
                        Nickel.getFluid(FluidStorageKeys.PLASMA, 1000))
                .outputFluids(BedrockMaterials.ADAMANTITE.getFluid(FluidStorageKeys.PLASMA, 1000))
                .EUt(22468).duration(200)
                .fusionStartEU(300000000)
                .save(provider);

        // 等离子体冷凝器 - 精金等离子体冷凝
        CTNHRecipeTypes.PLASMA_CONDENSER_RECIPES
                .recipeBuilder(CTNHCore.id("refined_gold_plasma_condensation"))
                .notConsumable(GTItems.SHAPE_MOLD_INGOT.asStack())
                .inputFluids(BedrockMaterials.ADAMANTITE.getFluid(FluidStorageKeys.PLASMA, 144),
                        CTNHMaterials.Cryotheum.getFluid(1000))
                .outputFluids(Argon.getFluid(FluidStorageKeys.PLASMA, 1000))
                .outputItems(ingot, BedrockMaterials.ADAMANTITE)
                .EUt(1920152).duration(150)
                .save(provider);

        // 电弧炉 - 液态精金
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("liquid_platinum"))
                .inputItems(dust, BedrockMaterials.ADAMANTITE)
                .inputItems(dust, Saltpeter)
                .outputFluids(BedrockMaterials.ADAMANTITE.getFluid(144))
                .blastFurnaceTemp(10800).EUt(491520).duration(100)
                .save(provider);
    }
}
