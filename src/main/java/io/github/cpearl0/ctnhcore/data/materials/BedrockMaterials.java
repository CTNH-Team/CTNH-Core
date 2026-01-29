package io.github.cpearl0.ctnhcore.data.materials;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.MAGNETIC;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.METALLIC;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class BedrockMaterials {

    public static Material SUPERFUELMK1;
    public static Material SUPERFUELMK2;
    public static Material SUPERFUELMK3;
    public static Material NQ_FUELMK1;
    public static Material NQ_FUELMK2;
    public static Material NQ_FUELMK3;
    public static Material TARANLIQUID;
    public static Material BEDROCK_FOG;
    public static Material BEDROCK_SMOKE;
    public static Material BEDROCK_GAS;
    public static Material BEDROCK_FOG_RE;
    public static Material BEDROCK_FOG_RE_L;
    public static Material BEDROCK_FOG_RE_M;
    public static Material BEDROCK_FOG_RE_H;
    public static Material BEDROCK_SMOKE_RE_L;
    public static Material BEDROCK_SMOKE_RE_M;
    public static Material BEDROCK_SMOKE_RE_H;
    public static Material BEDROCK_SMOKE_RE;
    public static Material BEDROCK_GAS_RE_L;
    public static Material BEDROCK_GAS_RE_M;
    public static Material BEDROCK_GAS_RE_H;
    public static Material BEDROCK_GAS_RE;
    public static Material TARANLIQUIDFUEL_L;
    public static Material TARANLIQUIDFUEL_M;
    public static Material TARANLIQUIDFUEL_H;
    public static Material TUNGSTENCU_DIAMOND_PLATING;
    public static Material STEARIC_ACID;
    public static Material PALMITIC_ACID;
    public static Material OLEIC_ACID;
    public static Material SPECTRIN;
    public static Material HEMOGLOBIN;
    public static Material BEDROCK_DUST_SOLUTION;
    public static Material BEDROCK_DUST;
    public static Material BEDROCK_SOOT_SOLUTION;
    public static Material TARANMIX;
    public static Material F_BEDROCK_SMOKE_RE;
    public static Material DR_BEDROCK_GAS_RE;
    public static Material AX_JIYANQI;
    public static Material RADIOACTIVE_METALS_MIX;
    public static Material BOUNDLESS;
    public static Material BEDROCK_NEUTRONIUM;
    public static Material ENRICH_RADIOACTIVE_WASTE;
    public static Material NEUTRON_IRRADIATION_OF_RADIOACTIVE_WASTE;
    public static Material RADIOACTIVE_ION_SOLUTION;
    public static Material RADIOACTIVE_METAL_INGOT;
    public static Material RADIATION_DUST;
    public static Material LOW_LEVEL_RADIOACTIVE_DUST;
    public static Material HIGH_LEVEL_RADIOACTIVE_DUST;
    public static Material CONCENTRATED_LOW_LEVEL_RADIOACTIVE_DUST;
    public static Material CONCENTRATED_HIGH_LEVEL_RADIOACTIVE_DUST;
    public static Material NUCLEAR_WASTE_WATER;
    public static Material AETHER;
    public static Material TARANIUM_DIRTY_HELIUM3;
    public static Material TARANIUM_ENRICHED_LIQUID_HELIUM3;
    public static Material TARANIUM_HALF_LIFE_LIQUID_HELIUM3;
    public static Material TARANIUM_DEPLETED_LIQUID_HELIUM3;
    public static Material TARANIUM_ENRICHED_DIRTY_HELIUM_PLASMA;
    public static Material TARANIUM_ENRICHED_HELIUM4_PLASMA;
    public static Material TARANIUM_DEPLETED_HELIUM_PLASMA;
    public static Material TARANIUM_ENRICHED_LIQUID_HELIUM4;
    public static Material TARANIUM_DEPLETED_LIQUID_HELIUM;
    public static Material ADAMANT_MUD;
    public static Material P507_EXTRACTANT;
    public static Material DI_2_ETHYLHEXYL_PHOSPHITE;
    public static Material _1_CHLORO_2_ETHYLHEXANE;
    public static Material PHOSPHONATE;
    public static Material CHLOROCYCLOHEXANE;
    public static Material BETA_AMINOPHOSPHONATE;
    public static Material DIMETHYL_PHOSPHITE;
    public static Material _2_ETHYLHEXANOL;
    public static Material COPPER_CHROMIUM_CATALYST;
    public static Material PHOSPHOROUS_ACID;
    public static Material REFINED_GOLD_AQ;
    public static Material REFINED_GOLD_AQ_LOW;
    public static Material SAMARIUM_DYSPROSIUM_TERBIUM_PERMANENT_MAGNET_ALLOY;
    public static Material SAMARIUM_DYSPROSIUM_TERBIUM_PERMANENT_MAGNET_ALLOY_MAGNETIC;
    public static Material ADAMANTITE;
    public static Material ADAMANTITELIQUID;

    public static void init() {
        SUPERFUELMK1 = REGISTRATE.material(CTNHCore.id("superfuelmk1"))
                .cnlang("超能燃料MK-I")
                .liquid()
                .color(0xA52A2A)
                .buildAndRegister();

        SUPERFUELMK2 = REGISTRATE.material(CTNHCore.id("superfuelmk2"))
                .cnlang("超能燃料MK-II")
                .liquid()
                .color(0xFF69B4)
                .buildAndRegister();

        SUPERFUELMK3 = REGISTRATE.material(CTNHCore.id("superfuelmk3"))
                .cnlang("超能燃料MK-III")
                .liquid()
                .color(0x8A2BE2)
                .buildAndRegister();

        NQ_FUELMK1 = REGISTRATE.material(CTNHCore.id("nq_fuelmk1"))
                .cnlang("惰性硅岩基燃料")
                .liquid()
                .color(0x54FF9F)
                .buildAndRegister();

        NQ_FUELMK2 = REGISTRATE.material(CTNHCore.id("nq_fuelmk2"))
                .cnlang("惰性富集硅岩基燃料")
                .liquid()
                .color(0x9AFF9A)
                .buildAndRegister();

        NQ_FUELMK3 = REGISTRATE.material(CTNHCore.id("nq_fuelmk3"))
                .cnlang("惰性超能硅岩基燃料")
                .liquid()
                .color(0x00FF7F)
                .buildAndRegister();

        TARANLIQUID = REGISTRATE.material(CTNHCore.id("taranliquid"))
                .cnlang("掺塔兰基岩烟")
                .gas()
                .color(0x8B7E66)
                .buildAndRegister();

        BEDROCK_FOG = REGISTRATE.material(CTNHCore.id("bedrock_fog"))
                .cnlang("基岩尘")
                .gas()
                .color(0xDCDCDC)
                .buildAndRegister();

        BEDROCK_SMOKE = REGISTRATE.material(CTNHCore.id("bedrock_smoke"))
                .cnlang("基岩烟")
                .gas()
                .color(0xBEBEBE)
                .buildAndRegister();

        BEDROCK_GAS = REGISTRATE.material(CTNHCore.id("bedrock_gas"))
                .cnlang("基岩气")
                .gas()
                .color(0xD3D3D3)
                .buildAndRegister();

        BEDROCK_FOG_RE = REGISTRATE.material(CTNHCore.id("bedrock_fog_re"))
                .cnlang("洁净基岩尘")
                .gas()
                .color(0xDCDC33)
                .buildAndRegister();

        BEDROCK_FOG_RE_L = REGISTRATE.material(CTNHCore.id("bedrock_fog_re_l"))
                .cnlang("轻质量基岩尘")
                .gas()
                .color(0xDCDC22)
                .buildAndRegister();

        BEDROCK_FOG_RE_M = REGISTRATE.material(CTNHCore.id("bedrock_fog_re_m"))
                .cnlang("中质量基岩尘")
                .gas()
                .color(0xDCDC11)
                .buildAndRegister();

        BEDROCK_FOG_RE_H = REGISTRATE.material(CTNHCore.id("bedrock_fog_re_h"))
                .cnlang("重质量基岩尘")
                .gas()
                .color(0xDCDC00)
                .buildAndRegister();

        BEDROCK_SMOKE_RE_L = REGISTRATE.material(CTNHCore.id("bedrock_smoke_re_l"))
                .cnlang("轻质量基岩烟")
                .gas()
                .color(0xDC1111)
                .buildAndRegister();

        BEDROCK_SMOKE_RE_M = REGISTRATE.material(CTNHCore.id("bedrock_smoke_re_m"))
                .cnlang("中质量基岩烟")
                .gas()
                .color(0xDC2222)
                .buildAndRegister();

        BEDROCK_SMOKE_RE_H = REGISTRATE.material(CTNHCore.id("bedrock_smoke_re_h"))
                .cnlang("重质量基岩烟")
                .gas()
                .color(0xDC3333)
                .buildAndRegister();

        BEDROCK_SMOKE_RE = REGISTRATE.material(CTNHCore.id("bedrock_smoke_re"))
                .cnlang("洁净基岩烟")
                .gas()
                .color(0xDC4444)
                .buildAndRegister();

        BEDROCK_GAS_RE_L = REGISTRATE.material(CTNHCore.id("bedrock_gas_re_l"))
                .cnlang("轻质量基岩气")
                .gas()
                .color(0xDC1111)
                .buildAndRegister();

        BEDROCK_GAS_RE_M = REGISTRATE.material(CTNHCore.id("bedrock_gas_re_m"))
                .cnlang("中质量基岩气")
                .gas()
                .color(0xDC2222)
                .buildAndRegister();

        BEDROCK_GAS_RE_H = REGISTRATE.material(CTNHCore.id("bedrock_gas_re_h"))
                .cnlang("重质量基岩气")
                .gas()
                .color(0xDC3333)
                .buildAndRegister();

        BEDROCK_GAS_RE = REGISTRATE.material(CTNHCore.id("bedrock_gas_re"))
                .cnlang("洁净基岩气")
                .gas()
                .color(0xDC4444)
                .buildAndRegister();

        TARANLIQUIDFUEL_L = REGISTRATE.material(CTNHCore.id("taranliquidfuel_l"))
                .cnlang("塔兰基岩燃料")
                .liquid()
                .color(0x8B1111)
                .buildAndRegister();

        TARANLIQUIDFUEL_M = REGISTRATE.material(CTNHCore.id("taranliquidfuel_m"))
                .cnlang("富集等塔兰基岩燃料")
                .liquid()
                .color(0x8B2222)
                .buildAndRegister();

        TARANLIQUIDFUEL_H = REGISTRATE.material(CTNHCore.id("taranliquidfuel_h"))
                .cnlang("超重塔兰基岩燃料")
                .liquid()
                .color(0x8B3333)
                .buildAndRegister();

        TUNGSTENCU_DIAMOND_PLATING = REGISTRATE.material(CTNHCore.id("tungstencu_diamond_plating"))
                .cnlang("W-Cu镀层金刚石")
                .gem()
                .color(0x223A40)
                .flags(GENERATE_ROD, GENERATE_FRAME, GENERATE_PLATE)
                .buildAndRegister();

        STEARIC_ACID = REGISTRATE.material(CTNHCore.id("stearic_acid"))
                .cnlang("硬脂酸")
                .liquid()
                .color(0xEEF52B)
                .buildAndRegister();

        PALMITIC_ACID = REGISTRATE.material(CTNHCore.id("palmitic_acid"))
                .cnlang("软脂酸")
                .liquid()
                .color(0xF4FF7E)
                .buildAndRegister();

        OLEIC_ACID = REGISTRATE.material(CTNHCore.id("oleic_acid"))
                .cnlang("油酸")
                .liquid()
                .color(0xC6F24E)
                .buildAndRegister();

        SPECTRIN = REGISTRATE.material(CTNHCore.id("spectrin"))
                .cnlang("血影蛋白")
                .dust()
                .color(0xCF5252)
                .buildAndRegister();

        HEMOGLOBIN = REGISTRATE.material(CTNHCore.id("hemoglobin"))
                .cnlang("血红蛋白")
                .dust()
                .color(0xAB2121)
                .buildAndRegister();

        BEDROCK_DUST_SOLUTION = REGISTRATE.material(CTNHCore.id("bedrock_dust_solution"))
                .cnlang("基岩尘灰溶液")
                .liquid()
                .color(0x66A499)
                .buildAndRegister();

        BEDROCK_DUST = REGISTRATE.material(CTNHCore.id("bedrock_dust"))
                .cnlang("基岩")
                .ingot()
                .dust()
                .liquid()
                .blastTemp(10200)
                .color(0x7C7878)
                .cableProperties(GTValues.V[GTValues.ZPM], 16, 1)
                .flags(GENERATE_ROD, GENERATE_FRAME, GENERATE_PLATE, GENERATE_GEAR, GENERATE_BOLT_SCREW, GENERATE_FOIL)
                .buildAndRegister();

        BEDROCK_SOOT_SOLUTION = REGISTRATE.material(CTNHCore.id("bedrock_soot_solution"))
                .cnlang("基岩烟灰溶液")
                .liquid()
                .color(0x164039)
                .buildAndRegister();

        TARANMIX = REGISTRATE.material(CTNHCore.id("taranmix"))
                .cnlang("塔兰类金属混合物")
                .dust()
                .color(0x463030)
                .buildAndRegister();

        F_BEDROCK_SMOKE_RE = REGISTRATE.material(CTNHCore.id("f_bedrock_smoke_re"))
                .cnlang("氟裂化基岩烟")
                .liquid()
                .color(0x24A156)
                .buildAndRegister();

        DR_BEDROCK_GAS_RE = REGISTRATE.material(CTNHCore.id("dr_bedrock_gas_re"))
                .cnlang("氡裂化基岩气")
                .liquid()
                .color(0x123AAA)
                .buildAndRegister();

        AX_JIYANQI = REGISTRATE.material(CTNHCore.id("ax_jiyanqi"))
                .cnlang("放射性基岩气")
                .gas()
                .color(0x325ADE)
                .buildAndRegister();

        RADIOACTIVE_METALS_MIX = REGISTRATE.material(CTNHCore.id("radioactive_metals_mix"))
                .cnlang("放射性金属混合物")
                .dust()
                .color(0x66ADAD)
                .buildAndRegister();

        BOUNDLESS = REGISTRATE.material(CTNHCore.id("boundless"))
                .cnlang("惰性无尽")
                .formula("∞-")
                .ingot()
                .dust()
                .liquid()
                .blastTemp(100000)
                .rotorStats(600, 550, 10, 2000000)
                .radioactiveHazard(3)
                .cableProperties(GTValues.V[GTValues.UXV], 16, 1)
                .color(0xFFFFFF)
                .iconSet(METALLIC)
                .flags(GENERATE_ROD, GENERATE_FRAME, GENERATE_PLATE, GENERATE_GEAR, GENERATE_BOLT_SCREW, GENERATE_FOIL)
                .buildAndRegister();

        BEDROCK_NEUTRONIUM = REGISTRATE.material(CTNHCore.id("bedrock_neutronium"))
                .cnlang("中子素-基岩合金")
                .formula("NtBR*")
                .ingot()
                .dust()
                .liquid()
                .blastTemp(10200)
                .radioactiveHazard(20)
                .cableProperties(GTValues.V[GTValues.UV], 16, 1)
                .color(0xFFFFFA)
                .iconSet(METALLIC)
                .flags(GENERATE_ROD, GENERATE_FRAME, GENERATE_PLATE, GENERATE_GEAR, GENERATE_BOLT_SCREW, GENERATE_FOIL)
                .buildAndRegister();

        ENRICH_RADIOACTIVE_WASTE = REGISTRATE.material(CTNHCore.id("enrich_radioactive_waste"))
                .cnlang("富集放射性废料")
                .dust()
                .color(0xFF5733)
                .buildAndRegister();

        NEUTRON_IRRADIATION_OF_RADIOACTIVE_WASTE = REGISTRATE
                .material(CTNHCore.id("neutron_irradiation_of_radioactive_waste"))
                .cnlang("中子照射放射性废料")
                .liquid()
                .color(0x33FF57)
                .buildAndRegister();

        RADIOACTIVE_ION_SOLUTION = REGISTRATE.material(CTNHCore.id("radioactive_ion_solution"))
                .cnlang("含放射性离子溶液")
                .liquid()
                .color(0x5733FF)
                .buildAndRegister();

        RADIOACTIVE_METAL_INGOT = REGISTRATE.material(CTNHCore.id("radioactive_metal_ingot"))
                .cnlang("辐射金属")
                .ingot()
                .color(0xFFD700)
                .buildAndRegister();

        RADIATION_DUST = REGISTRATE.material(CTNHCore.id("radiation_dust"))
                .cnlang("辐射")
                .dust()
                .color(0xFF33FF)
                .buildAndRegister();

        LOW_LEVEL_RADIOACTIVE_DUST = REGISTRATE.material(CTNHCore.id("low_level_radioactive_dust"))
                .cnlang("较低放射性尘埃")
                .gas()
                .color(0xFFAA33)
                .buildAndRegister();

        HIGH_LEVEL_RADIOACTIVE_DUST = REGISTRATE.material(CTNHCore.id("high_level_radioactive_dust"))
                .cnlang("较高放射性尘埃")
                .gas()
                .color(0xAA33FF)
                .buildAndRegister();

        CONCENTRATED_LOW_LEVEL_RADIOACTIVE_DUST = REGISTRATE
                .material(CTNHCore.id("concentrated_low_level_radioactive_dust"))
                .cnlang("浓缩低放射性尘埃")
                .gas()
                .color(0x33AAFF)
                .buildAndRegister();

        CONCENTRATED_HIGH_LEVEL_RADIOACTIVE_DUST = REGISTRATE
                .material(CTNHCore.id("concentrated_high_level_radioactive_dust"))
                .cnlang("浓缩高放射性尘埃")
                .gas()
                .color(0xFFFF33)
                .buildAndRegister();

        NUCLEAR_WASTE_WATER = REGISTRATE.material(CTNHCore.id("nuclear_waste_water"))
                .cnlang("核废水")
                .liquid()
                .color(0x33FF33)
                .buildAndRegister();

        AETHER = REGISTRATE.material(CTNHCore.id("aether"))
                .cnlang("以太")
                .dust()
                .ingot(5)
                .liquid()
                .plasma()
                .radioactiveHazard(3)
                .blastTemp(12600)
                .color(0x00FF80)
                .secondaryColor(0x2DCB7C)
                .iconSet(METALLIC)
                .cableProperties(GTValues.V[GTValues.UEV], 16, 1)
                .flags(GENERATE_ROD, GENERATE_FRAME, GENERATE_PLATE, GENERATE_GEAR, GENERATE_BOLT_SCREW, GENERATE_FOIL)
                .buildAndRegister();

        TARANIUM_DIRTY_HELIUM3 = REGISTRATE.material(CTNHCore.id("taranium_dirty_helium3"))
                .cnlang("污浊氦-3")
                .gas()
                .color(0xFF5733)
                .buildAndRegister();

        TARANIUM_ENRICHED_LIQUID_HELIUM3 = REGISTRATE.material(CTNHCore.id("taranium_enriched_liquid_helium3"))
                .cnlang("富塔兰金属的液氦-3")
                .liquid()
                .color(0x33FF57)
                .buildAndRegister();

        TARANIUM_HALF_LIFE_LIQUID_HELIUM3 = REGISTRATE.material(CTNHCore.id("taranium_half_life_liquid_helium3"))
                .cnlang("半衰塔兰金属的液氦-3")
                .liquid()
                .color(0x3357FF)
                .buildAndRegister();

        TARANIUM_DEPLETED_LIQUID_HELIUM3 = REGISTRATE.material(CTNHCore.id("taranium_depleted_liquid_helium3"))
                .cnlang("枯竭塔兰金属的液氦-3")
                .liquid()
                .color(0xFF3333)
                .buildAndRegister();

        TARANIUM_ENRICHED_DIRTY_HELIUM_PLASMA = REGISTRATE
                .material(CTNHCore.id("taranium_enriched_dirty_helium_plasma"))
                .cnlang("富塔兰金属的污浊氦")
                .plasma()
                .color(0x33FFFF)
                .buildAndRegister();

        TARANIUM_ENRICHED_HELIUM4_PLASMA = REGISTRATE.material(CTNHCore.id("taranium_enriched_helium4_plasma"))
                .cnlang("富塔兰金属的氦-4")
                .plasma()
                .color(0x5733FF)
                .buildAndRegister();

        TARANIUM_DEPLETED_HELIUM_PLASMA = REGISTRATE.material(CTNHCore.id("taranium_depleted_helium_plasma"))
                .cnlang("枯竭塔兰金属的氦")
                .plasma()
                .color(0xFF33FF)
                .buildAndRegister();

        TARANIUM_ENRICHED_LIQUID_HELIUM4 = REGISTRATE.material(CTNHCore.id("taranium_enriched_liquid_helium4"))
                .cnlang("富塔兰金属的液氦-4")
                .liquid()
                .color(0x33FF33)
                .buildAndRegister();

        TARANIUM_DEPLETED_LIQUID_HELIUM = REGISTRATE.material(CTNHCore.id("taranium_depleted_liquid_helium"))
                .cnlang("塔兰金属贫瘠的液氦")
                .liquid()
                .color(0x33FF33)
                .buildAndRegister();

        ADAMANT_MUD = REGISTRATE.material(CTNHCore.id("adamant_mud"))
                .cnlang("精金泥")
                .dust()
                .color(0xFFCC33)
                .buildAndRegister();

        P507_EXTRACTANT = REGISTRATE.material(CTNHCore.id("p507_extractant"))
                .cnlang("P507萃取剂")
                .liquid()
                .color(0x33FF57)
                .buildAndRegister();

        DI_2_ETHYLHEXYL_PHOSPHITE = REGISTRATE.material(CTNHCore.id("di_2_ethylhexyl_phosphite"))
                .cnlang("二(2-乙基己基)亚磷酸酯")
                .liquid()
                .color(0x33AADD)
                .buildAndRegister();

        _1_CHLORO_2_ETHYLHEXANE = REGISTRATE.material(CTNHCore.id("1_chloro_2_ethylhexane"))
                .cnlang("氯代2-乙基己烷")
                .liquid()
                .color(0x33FF57)
                .buildAndRegister();

        PHOSPHONATE = REGISTRATE.material(CTNHCore.id("phosphonate"))
                .cnlang("膦酸酯")
                .liquid()
                .color(0x3333FF)
                .buildAndRegister();

        CHLOROCYCLOHEXANE = REGISTRATE.material(CTNHCore.id("chlorocyclohexane"))
                .cnlang("氯代环己烷")
                .liquid()
                .color(0x33AADD)
                .buildAndRegister();

        BETA_AMINOPHOSPHONATE = REGISTRATE.material(CTNHCore.id("beta_aminophosphonate"))
                .cnlang("β-氨基膦酸酯")
                .liquid()
                .color(0xFFCC33)
                .buildAndRegister();

        DIMETHYL_PHOSPHITE = REGISTRATE.material(CTNHCore.id("dimethyl_phosphite"))
                .cnlang("亚磷酸二甲酯")
                .liquid()
                .color(0x33AADD)
                .buildAndRegister();

        _2_ETHYLHEXANOL = REGISTRATE.material(CTNHCore.id("2_ethylhexanol"))
                .cnlang("2-乙基己醇")
                .liquid()
                .color(0x33FF57)
                .buildAndRegister();

        COPPER_CHROMIUM_CATALYST = REGISTRATE.material(CTNHCore.id("copper_chromium_catalyst"))
                .cnlang("铜-铬催化剂")
                .dust()
                .color(0x33FF57)
                .buildAndRegister();

        PHOSPHOROUS_ACID = REGISTRATE.material(CTNHCore.id("phosphorous_acid"))
                .cnlang("亚磷酸")
                .formula("H3PO3")
                .liquid()
                .color(0xFF5733)
                .buildAndRegister();

        REFINED_GOLD_AQ = REGISTRATE.material(CTNHCore.id("refined_gold_aq"))
                .cnlang("富集精金溶液")
                .formula("Ad?*Nq*?")
                .liquid()
                .color(0x325547)
                .buildAndRegister();

        REFINED_GOLD_AQ_LOW = REGISTRATE.material(CTNHCore.id("refined_gold_aq_low"))
                .cnlang("精金废液")
                .liquid()
                .color(0x16477A)
                .buildAndRegister();

        SAMARIUM_DYSPROSIUM_TERBIUM_PERMANENT_MAGNET_ALLOY = REGISTRATE
                .material(CTNHCore.id("samarium_dysprosium_terbium_permanent_magnet_alloy"))
                .cnlang("未磁化钐镝铽合金")
                .ingot()
                .blastTemp(10200)
                .iconSet(METALLIC)
                .cableProperties(GTValues.V[GTValues.UV], 16, 1)
                .components(Samarium, 1, Dysprosium, 1, Terbium, 1)
                .color(0x124435)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_BOLT_SCREW,
                        GENERATE_FOIL, GENERATE_FRAME, GENERATE_RING, GENERATE_LONG_ROD)
                .buildAndRegister();

        SAMARIUM_DYSPROSIUM_TERBIUM_PERMANENT_MAGNET_ALLOY_MAGNETIC = REGISTRATE
                .material(CTNHCore.id("samarium_dysprosium_terbium_permanent_magnet_alloy_magnetic"))
                .cnlang("磁化钐镝铽合金")
                .ingot()
                .blastTemp(10200)
                .iconSet(MAGNETIC)
                .cableProperties(GTValues.V[GTValues.UV], 16, 1)
                .components(Samarium, 1, Dysprosium, 1, Terbium, 1)
                .color(0x148072)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_BOLT_SCREW,
                        GENERATE_FOIL, GENERATE_FRAME, GENERATE_RING, GENERATE_LONG_ROD)
                .buildAndRegister();

        ADAMANTITE = REGISTRATE.material(CTNHCore.id("adamantite"))
                .cnlang("精金")
                .formula("Ad")
                .ingot()
                .liquid()
                .dust()
                .plasma()
                .radioactiveHazard(3)
                .rotorStats(500, 450, 10, 1000000)
                .blastTemp(18900)
                .cableProperties(GTValues.V[GTValues.UHV], 16, 1)
                .color(0xFF2828)
                .secondaryColor(0x8A2020)
                .iconSet(METALLIC)
                .flags(GENERATE_ROD, GENERATE_FRAME, GENERATE_PLATE, GENERATE_GEAR,
                        GENERATE_BOLT_SCREW, GENERATE_FOIL, GENERATE_FINE_WIRE,
                        GENERATE_ROUND, GENERATE_LONG_ROD, GENERATE_RING, GENERATE_SMALL_GEAR)
                .buildAndRegister();

        ADAMANTITELIQUID = REGISTRATE.material(CTNHCore.id("adamantiteliquid"))
                .cnlang("液态精金")
                .liquid()
                .color(0x125555)
                .buildAndRegister();
    }
}
