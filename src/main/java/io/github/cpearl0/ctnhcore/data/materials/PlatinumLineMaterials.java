package io.github.cpearl0.ctnhcore.data.materials;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.api.data.material.CatalystProperty;

import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static io.github.cpearl0.ctnhcore.api.data.material.CTNHPropertyKeys.CATALYST;
import static io.github.cpearl0.ctnhcore.registry.CTNHMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class PlatinumLineMaterials {

    public static void init() {
        addFluid(GTMaterials.RutheniumTetroxide);
        addFluid(GTMaterials.OsmiumTetroxide);
        addFluid(GTMaterials.CalciumChloride);
        addFluid(GTMaterials.SodiumBisulfate);
        addFluid(GTMaterials.SodiumHydroxide);
        addGas(GTMaterials.OsmiumTetroxide);
        addGas(GTMaterials.RutheniumTetroxide);

        PalladiumMetal = REGISTRATE.material(CTNHCore.id("palladium_metal")).cnlang("钯金属").dust().color(0x30302E)
                .iconSet(SAND).flags(DISABLE_DECOMPOSITION)
                .buildAndRegister().setFormula("??Pd??");
        PlatinumMetal = REGISTRATE.material(CTNHCore.id("platinum_metal")).cnlang("铂金属").dust().color(0xEBEBB2)
                .iconSet(ROUGH).flags(DISABLE_DECOMPOSITION)
                .buildAndRegister().setFormula("??PtPdIrOsRhRu??");

        PlatinumOre = REGISTRATE.material(CTNHCore.id("platinum_ore")).cnlang("铂金矿")
                .ore()
                .dust()
                .color(0xFFD700)
                .iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(GTMaterials.Platinum, 1)
                .buildAndRegister()
                .setFormula("Pt*Pd*", true);
        Platinum.getProperty(PropertyKey.ORE).setDirectSmeltResult(PlatinumOre);
        PalladiumOre = REGISTRATE.material(CTNHCore.id("palladium_ore")).cnlang("钯金矿")
                .ore()
                .dust()
                .color(0xD8BFD8)
                .iconSet(ROUGH)
                .components(GTMaterials.Palladium, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister()
                .setFormula("Pd*", true);
        Palladium.getProperty(PropertyKey.ORE).setDirectSmeltResult(PalladiumOre);

        IridiumDioxide = REGISTRATE.material(CTNHCore.id("iridium_dioxide")).cnlang("二氧化铱").dust().color(0xA2BFFF)
                .iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION).buildAndRegister().setFormula("IrO2");
        // 新铂材料
        GoldPlatinumPalladiumAcidSolution = REGISTRATE.material(CTNHCore.id("gold_platinum_palladium_acid_solution"))
                .cnlang("金-铂-钯酸性溶液")
                .liquid()
                .color(0xFFD700)
                .iconSet(DULL)
                .buildAndRegister()
                .setFormula("H[AuCl4]*H2[PtCl6]*H2[PdCl6]*HNO3", true);

        DenitratedGoldPlatinumPalladiumSolution = REGISTRATE
                .material(CTNHCore.id("denitrated_gold_platinum_palladium_solution")).cnlang("除硝金-铂-钯酸性溶液")
                .liquid()
                .color(0x8A2BE2)
                .iconSet(SHINY)
                .buildAndRegister()
                .setFormula("H[AuCl4]*H2[PtCl6]*H2[PdCl6]*HCl", true);

        FerricSulfate = REGISTRATE.material(CTNHCore.id("ferric_sulfate")).cnlang("硫酸铁")
                .dust()
                .liquid()
                .color(0xB22222)
                .iconSet(ROUGH)
                .buildAndRegister()
                .setFormula("Fe2(SO4)3", true);

        FerrousSulfate = REGISTRATE.material(CTNHCore.id("ferrous_sulfate")).cnlang("酸性硫酸亚铁")
                .dust()
                .liquid()
                .color(0x2E8B57)
                .iconSet(FINE)
                .buildAndRegister()
                .setFormula("2FeSO4·H2SO4", true);

        ChloroplatinicChloropalladicSolution = REGISTRATE
                .material(CTNHCore.id("chloroplatinic_chloropalladic_solution")).cnlang("氯铂酸-氯钯酸混合溶液")
                .liquid()
                .color(0x9370DB)
                .iconSet(SHINY)
                .buildAndRegister()
                .setFormula("H2[PtCl6]*H2[PdCl6]", true);

        AmmoniumChloroplatinate = REGISTRATE.material(CTNHCore.id("ammonium_chloroplatinate")).cnlang("氯铂酸铵")
                .dust()
                .color(0xFFD700)
                .iconSet(BRIGHT)
                .buildAndRegister()
                .setFormula("(NH4)2PtCl6", true);

        AmmoniaMonohydrate = REGISTRATE.material(CTNHCore.id("ammonia_monohydrate")).cnlang("氨水")
                .liquid()
                .color(0xADD8E6)
                .iconSet(DULL)
                .buildAndRegister()
                .setFormula("NH3·H2O", true);

        Diamminedichloropalladium = REGISTRATE.material(CTNHCore.id("diamminedichloropalladium")).cnlang("二氯二氨络亚钯")
                .dust()
                .color(0x87CEEB)
                .iconSet(METALLIC)
                .buildAndRegister()
                .setFormula("Pd(NH3)2Cl2", true);

        SpongePalladium = REGISTRATE.material(CTNHCore.id("sponge_palladium")).cnlang("海绵钯")
                .dust()
                .color(0xB0C4DE)
                .iconSet(SHINY)
                .buildAndRegister()
                .setFormula("Pd", true);

        SpongePlatinum = REGISTRATE.material(CTNHCore.id("sponge_platinum")).cnlang("海绵铂")
                .dust()
                .color(0xE0FFFF)
                .iconSet(BRIGHT)
                .buildAndRegister()
                .setFormula("Pt", true);

        ChloropalladicAcidMixture = REGISTRATE.material(CTNHCore.id("chloropalladic_acid_mixture")).cnlang("氯钯酸混合物")
                .liquid() // 液态混合物
                .color(0x8A2BE2) // 紫罗兰色（标识含钯）
                .iconSet(SHINY)  // 高光图标
                .flags(DISABLE_DECOMPOSITION) // 禁止分解（保持混合物稳定）
                .buildAndRegister()
                .setFormula("H2[PdCl6]?", true); // 化学式（氯钯酸为主成分）

        // 铂族贵金属残渣（含Rh/Ir/Ru等）
        PlatinumGroupResidue = REGISTRATE.material(CTNHCore.id("platinum_group_residue")).cnlang("铂族贵金属残渣")
                .dust()
                .color(0x4B3621) // 深棕褐色
                .iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister()
                .setFormula("Rh*Ir*Ru*Os", true); // *代表其他杂质

        // 密陀僧（PbO，铅冶炼副产品）
        Litharge = REGISTRATE.material(CTNHCore.id("litharge")).cnlang("密陀僧")
                .dust()
                .color(0xD4AF37) // 金属金色
                .iconSet(METALLIC)
                .buildAndRegister()
                .setFormula("PbO", true);

        // 贵铅（含Ag/Au的粗铅）
        NobleLead = REGISTRATE.material(CTNHCore.id("noble_lead")).cnlang("贵铅")
                .ingot()
                .color(0x8B8B8B) // 铅灰色
                .iconSet(DULL)
                .buildAndRegister()
                .setFormula("Pb*Ag*Rh*Ir*Ru*Os", true);

        // 硝酸浸没溶液（溶解Ag/Pb后的酸性液）
        NitricLeachSolution = REGISTRATE.material(CTNHCore.id("nitric_leach_solution")).cnlang("硝酸浸没溶液")
                .liquid()
                .color(0xADD8E6) // 淡蓝色
                .iconSet(DULL)
                .buildAndRegister()
                .setFormula("HNO3*AgNO3*Pb(NO3)2", true);

        // 富集惰性混合物（Rh/Ir/Ru富集物）
        EnrichedInertMixture = REGISTRATE.material(CTNHCore.id("enriched_inert_mixture")).cnlang("富集惰性混合物")
                .dust()
                .color(0x696969) // 深灰色
                .iconSet(SHINY)
                .buildAndRegister()
                .setFormula("Rh*Ir*Ru*Os", true);

        // 含硫酸铑水溶液（Rh₂(SO₄)₃）
        RhodiumSulfateSolution = REGISTRATE.material(CTNHCore.id("rhodium_sulfate_solution")).cnlang("含硫酸铑水溶液")
                .liquid()
                .color(0x9370DB) // 紫罗兰色
                .iconSet(SHINY)
                .buildAndRegister()
                .setFormula("Rh2(SO4)3*H2O", true);

        // 氢氧化铑（Rh(OH)₃）
        RhodiumHydroxide = REGISTRATE.material(CTNHCore.id("rhodium_hydroxide")).cnlang("氢氧化铑")
                .dust()
                .color(0xC0C0C0) // 浅灰色
                .iconSet(FINE)
                .buildAndRegister()
                .setFormula("Rh(OH)3", true);

        // 氯铑酸（H₃[RhCl₆]）
        ChlororhodicAcid = REGISTRATE.material(CTNHCore.id("chlororhodic_acid")).cnlang("氯铑酸")
                .liquid()
                .color(0x8B0000) // 暗红色
                .iconSet(SHINY)
                .buildAndRegister()
                .setFormula("H3[RhCl6]", true);

        // 浓缩氯铑酸铵溶液（(NH₄)₃[RhCl₆]）
        ConcentratedAmmoniumChlororhodate = REGISTRATE.material(CTNHCore.id("concentrated_ammonium_chlororhodate"))
                .cnlang("浓缩氯铑酸铵溶液")
                .liquid()
                .color(0xFF6347) // 橙红色
                .iconSet(BRIGHT)
                .buildAndRegister()
                .setFormula("(NH4)3[RhCl6]/H2O", true);

        // 氯铑酸铵晶体（(NH₄)₃[RhCl₆]）
        AmmoniumChlororhodate = REGISTRATE.material(CTNHCore.id("ammonium_chlororhodate")).cnlang("氯铑酸铵")
                .dust()
                .color(0xFF4500) // 橙红色晶体
                .iconSet(BRIGHT)
                .buildAndRegister()
                .setFormula("(NH4)3[RhCl6]", true);

        // 海绵铑（高纯度铑粉）
        SpongeRhodium = REGISTRATE.material(CTNHCore.id("sponge_rhodium")).cnlang("海绵铑")
                .dust()
                .color(0xE6E6FA) // 银白色
                .iconSet(SHINY)
                .buildAndRegister()
                .setFormula("Rh", true);
        // 珍贵金属混合物
        PreciousMetalMixture = REGISTRATE.material(CTNHCore.id("precious_metal_mixture")).cnlang("珍贵金属混合物")
                .dust()
                .color(0x2F4F4F)  // 深石板灰
                .iconSet(METALLIC)
                .buildAndRegister()
                .setFormula("Ru*Ir*Os", true);  // 化学式标记为混合物
        // 氯铑酸铵溶液
        AmmoniumChlororhodateSolution = REGISTRATE.material(CTNHCore.id("ammonium_chlororhodate_solution"))
                .cnlang("氯铑酸铵溶液")
                .liquid()                      // 液态
                .color(0xFFA500)               // 橙红色（标识含铵）
                .iconSet(DULL)                 // 无光泽
                .buildAndRegister()
                .setFormula("(NH4)3[RhCl6]*H2O", true);  // 化学式（水溶液）
        // 锇酸钠-钌酸钠混合溶液
        SodiumOsmateRuthenateSolution = REGISTRATE.material(CTNHCore.id("sodium_osmate_ruthenate_solution"))
                .cnlang("锇酸钠-钌酸钠混合溶液")
                .liquid()
                .color(0x8A2BE2)
                .buildAndRegister()
                .setFormula("Na2OsO4*Na2RuO4*H+", true);

        // 过氧化钠粉
        SodiumPeroxide = REGISTRATE.material(CTNHCore.id("sodium_peroxide")).cnlang("过氧化钠")
                .dust()
                .liquid()
                .color(0xFFECB3)
                .buildAndRegister()
                .setFormula("Na2O2", true);

        // 氯锇酸气体
        ChlorosmicAcidGas = REGISTRATE.material(CTNHCore.id("chlorosmic_acid_gas")).cnlang("氯锇酸")
                .gas()
                .color(0xDA70D6)
                .buildAndRegister()
                .setFormula("H2OsCl6(g)", true);

        // 氯钌酸气体
        ChlororuthenicAcidGas = REGISTRATE.material(CTNHCore.id("chlororuthenic_acid_gas")).cnlang("氯钌酸")
                .gas()
                .color(0xBA55D3)
                .buildAndRegister()
                .setFormula("H2RuCl6(g)", true);

        // 氯锇酸铵
        AmmoniumChlorosmate = REGISTRATE.material(CTNHCore.id("ammonium_chlorosmate")).cnlang("氯锇酸铵")
                .dust()
                .color(0x8A2BE2)
                .buildAndRegister()
                .setFormula("(NH4)2OsCl6", true);

        // 氯钌酸铵
        AmmoniumChlororuthenate = REGISTRATE.material(CTNHCore.id("ammonium_chlororuthenate")).cnlang("氯钌酸铵")
                .dust()
                .color(0x9932CC)
                .buildAndRegister()
                .setFormula("(NH4)2RuCl6", true);
        // 锇酸钠-钌酸钠-氯化溶液（含OsO₄²⁻/RuO₄²⁻/Cl⁻）
        SodiumOsmateRuthenateChlorideSolution = REGISTRATE
                .material(CTNHCore.id("sodium_osmate_ruthenate_chloride_solution")).cnlang("锇酸钠-钌酸钠-氯化溶液")
                .liquid()                      // 液态
                .color(0x9932CC)               // 深紫色（标识含钌、锇）
                .iconSet(DULL)                 // 无光泽
                .buildAndRegister()
                .setFormula("Na2[OsO4]/Na2[RuO4]/NaCl", true); // 化学式
        // 乙醛
        Acetaldehyde = REGISTRATE.material(CTNHCore.id("acetaldehyde")).cnlang("乙醛")
                .liquid(new FluidBuilder().temperature(294))  // 沸点20.8°C（294K），设为室温液态
                .color(0xFFF8DC)  // 淡黄色（工业乙醛常含杂质）
                .iconSet(DULL)     // 无光泽图标
                .components(Carbon, 2, Hydrogen, 4, Oxygen, 1)  // C₂H₄O
                .buildAndRegister()
                .setFormula("CH3CHO", true);  // 结构式

        PalladiumOnPlatinum.setProperty(CATALYST, new CatalystProperty(300));
    }
}
