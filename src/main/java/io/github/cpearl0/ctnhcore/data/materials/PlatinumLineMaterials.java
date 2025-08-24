package io.github.cpearl0.ctnhcore.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import io.github.cpearl0.ctnhcore.CTNHCore;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Oxygen;
import static io.github.cpearl0.ctnhcore.registry.CTNHMaterials.*;
public class PlatinumLineMaterials {
    public static void init() {
        addFluid(GTMaterials.RutheniumTetroxide);
        addFluid(GTMaterials.OsmiumTetroxide);
        addFluid(GTMaterials.CalciumChloride);
        addFluid(GTMaterials.AmmoniumChloride);
        addFluid(GTMaterials.SodiumBisulfate);
        addFluid(GTMaterials.SodiumHydroxide);
        addGas(GTMaterials.OsmiumTetroxide);
        addGas(GTMaterials.RutheniumTetroxide);

        PlatinumSalt = new Material.Builder(CTNHCore.id("platinum_salt"))
                .dust()
                .color(0xEEF2AE)
                .iconSet(SAND)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        RefinedPlatinumSalt = new Material.Builder(CTNHCore.id("refined_platinum_salt"))
                .dust()
                .color(0xFFFEC2)
                .iconSet(SAND)
                .flags(DISABLE_DECOMPOSITION).buildAndRegister();

        PalladiumSalt = new Material.Builder(CTNHCore.id("palladium_salt")).dust().color(0x33302D).iconSet(SAND).flags(DISABLE_DECOMPOSITION)
                        .buildAndRegister();

        RhodiumNitrate = new Material.Builder(CTNHCore.id("rhodium_nitrate")).dust().color(0x8C5A0C).iconSet(SAND).flags(DISABLE_DECOMPOSITION)
                        .buildAndRegister();

        RoughlyRhodiumMetal = new Material.Builder(CTNHCore.id("roughly_rhodium_metal")).dust().color(0x594C1A).iconSet(SAND)
                        .flags(DISABLE_DECOMPOSITION).buildAndRegister().setFormula("??Rh??");

        PalladiumMetal = new Material.Builder(CTNHCore.id("palladium_metal")).dust().color(0x30302E).iconSet(SAND).flags(DISABLE_DECOMPOSITION)
                        .buildAndRegister().setFormula("??Pd??");

        MetalSludge = new Material.Builder(CTNHCore.id("metal_sludge")).dust().color(0x362605).iconSet(SAND).buildAndRegister()
                        .setFormula("NiCu");

        PlatinumSlag = new Material.Builder(CTNHCore.id("platinum_slag")).dust().color(0x343318).iconSet(SAND).flags(DISABLE_DECOMPOSITION)
                        .buildAndRegister().setFormula("??IrOsRhRb??");

        ReprecipitatedRhodium = new Material.Builder(CTNHCore.id("reprecipitated_rhodium")).dust().color(0xD40849).iconSet(SAND)
                        .flags(DISABLE_DECOMPOSITION).buildAndRegister().setFormula("Rh2NH4");

        SodiumNitrate = new Material.Builder(CTNHCore.id("sodium_nitrate")).dust().color(0x4e2a40).iconSet(SAND).flags(DISABLE_DECOMPOSITION)
                        .buildAndRegister().setFormula("NaNO3");

        RhodiumSalt = new Material.Builder(CTNHCore.id("rhodium_salt")).dust().fluid().color(0x61200A).iconSet(SAND)
                .flags(DISABLE_DECOMPOSITION).buildAndRegister();

        RhodiumFilterCake = new Material.Builder(CTNHCore.id("rhodium_filter_cake")).dust().fluid().color(0x87350C).iconSet(ROUGH)
                        .flags(DISABLE_DECOMPOSITION).buildAndRegister().setFormula("?Ru?");

        PlatinumMetal = new Material.Builder(CTNHCore.id("platinum_metal")).dust().color(0xEBEBB2).iconSet(ROUGH).flags(DISABLE_DECOMPOSITION)
                        .buildAndRegister().setFormula("??PtPdIrOsRhRu??");
        Platinum.getProperty(PropertyKey.ORE).setDirectSmeltResult(PlatinumMetal);

        SodiumRutheniate = new Material.Builder(CTNHCore.id("sodium_rutheniate")).dust().color(0x282C8C).iconSet(METALLIC)
                        .flags(DISABLE_DECOMPOSITION).buildAndRegister().setFormula("Na2RbO3");

        IridiumDioxide = new Material.Builder(CTNHCore.id("iridium_dioxide")).dust().color(0xA2BFFF).iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION).buildAndRegister().setFormula("IrO2");

        ConcentratedPlatinum = new Material.Builder(CTNHCore.id("concentrated_platinum")).fluid().color(0xC3C39A).iconSet(ROUGH).buildAndRegister();

        PalladiumRichAmmonia = new Material.Builder(CTNHCore.id("palladium_rich_ammonia")).fluid().color(0x676767).iconSet(ROUGH).buildAndRegister();

        RutheniumTetroxideLQ = new Material.Builder(CTNHCore.id("ruthenium_tetroxide_lq")).fluid().color(0xA8A8A8).iconSet(ROUGH).buildAndRegister();

        SodiumFormate = new Material.Builder(CTNHCore.id("sodium_formate")).fluid().color(0xf1939c).iconSet(ROUGH).buildAndRegister();

        RhodiumSulfateGas = new Material.Builder(CTNHCore.id("rhodium_sulfate_gas")).gas().color(0xBD8743).iconSet(ROUGH).buildAndRegister();

        AcidicIridium = new Material.Builder(CTNHCore.id("acidic_iridium")).gas().color(0x634E3A).iconSet(ROUGH).buildAndRegister()
                        .setFormula("???");

        RutheniumTetroxideHot = new Material.Builder(CTNHCore.id("ruthenium_tetroxide_hot")).gas().color(0x9B9B9B).iconSet(ROUGH).buildAndRegister();
        //新铂材料
        GoldPlatinumPalladiumAcidSolution = new Material.Builder(CTNHCore.id("gold_platinum_palladium_acid_solution"))
                .liquid()
                .color(0xFFD700)
                .iconSet(DULL)
                .buildAndRegister()
                .setFormula("H[AuCl4]*H2[PtCl6]*H2[PdCl6]*HNO3", true);

        DenitratedGoldPlatinumPalladiumSolution = new Material.Builder(CTNHCore.id("denitrated_gold_platinum_palladium_solution"))
                .liquid()
                .color(0x8A2BE2)
                .iconSet(SHINY)
                .buildAndRegister()
                .setFormula("H[AuCl4]*H2[PtCl6]*H2[PdCl6]*HCl", true);

        FerricSulfate = new Material.Builder(CTNHCore.id("ferric_sulfate"))
                .dust()
                .liquid()
                .color(0xB22222)
                .iconSet(ROUGH)
                .buildAndRegister()
                .setFormula("Fe2(SO4)3", true);

        FerrousSulfate = new Material.Builder(CTNHCore.id("ferrous_sulfate"))
                .dust()
                .liquid()
                .color(0x2E8B57)
                .iconSet(FINE)
                .buildAndRegister()
                .setFormula("2FeSO4·H2SO4", true);

        ChloroplatinicChloropalladicSolution = new Material.Builder(CTNHCore.id("chloroplatinic_chloropalladic_solution"))
                .liquid()
                .color(0x9370DB)
                .iconSet(SHINY)
                .buildAndRegister()
                .setFormula("H2[PtCl6]*H2[PdCl6]", true);

        AmmoniumChloroplatinate = new Material.Builder(CTNHCore.id("ammonium_chloroplatinate"))
                .dust()
                .color(0xFFD700)
                .iconSet(BRIGHT)
                .buildAndRegister()
                .setFormula("(NH4)2PtCl6", true);

        AmmoniaMonohydrate = new Material.Builder(CTNHCore.id("ammonia_monohydrate"))
                .liquid()
                .color(0xADD8E6)
                .iconSet(DULL)
                .buildAndRegister()
                .setFormula("NH3·H2O", true);

        Diamminedichloropalladium = new Material.Builder(CTNHCore.id("diamminedichloropalladium"))
                .dust()
                .color(0x87CEEB)
                .iconSet(METALLIC)
                .buildAndRegister()
                .setFormula("Pd(NH3)2Cl2", true);

        SpongePalladium = new Material.Builder(CTNHCore.id("sponge_palladium"))
                .ingot()
                .color(0xB0C4DE)
                .iconSet(SHINY)
                .buildAndRegister()
                .setFormula("Pd", true);

        SpongePlatinum = new Material.Builder(CTNHCore.id("sponge_platinum"))
                .ingot()
                .color(0xE0FFFF)
                .iconSet(BRIGHT)
                .buildAndRegister()
                .setFormula("Pt", true);

        ChloropalladicAcidMixture = new Material.Builder(CTNHCore.id("chloropalladic_acid_mixture"))
                .liquid() // 液态混合物
                .color(0x8A2BE2) // 紫罗兰色（标识含钯）
                .iconSet(SHINY)  // 高光图标
                .flags(DISABLE_DECOMPOSITION) // 禁止分解（保持混合物稳定）
                .buildAndRegister()
                .setFormula("H2[PdCl6]?", true); // 化学式（氯钯酸为主成分）

// 铂族贵金属残渣（含Rh/Ir/Ru等）
        PlatinumGroupResidue = new Material.Builder(CTNHCore.id("platinum_group_residue"))
                .dust()
                .color(0x4B3621) // 深棕褐色
                .iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister()
                .setFormula("Rh*Ir*Ru*Os", true); // *代表其他杂质

// 密陀僧（PbO，铅冶炼副产品）
        Litharge = new Material.Builder(CTNHCore.id("litharge"))
                .dust()
                .color(0xD4AF37) // 金属金色
                .iconSet(METALLIC)
                .buildAndRegister()
                .setFormula("PbO", true);

// 贵铅（含Ag/Au的粗铅）
        NobleLead = new Material.Builder(CTNHCore.id("noble_lead"))
                .ingot()
                .color(0x8B8B8B) // 铅灰色
                .iconSet(DULL)
                .buildAndRegister()
                .setFormula("Pb*Ag*Rh*Ir*Ru*Os", true);

// 硝酸浸没溶液（溶解Ag/Pb后的酸性液）
        NitricLeachSolution = new Material.Builder(CTNHCore.id("nitric_leach_solution"))
                .liquid()
                .color(0xADD8E6) // 淡蓝色
                .iconSet(DULL)
                .buildAndRegister()
                .setFormula("HNO3*AgNO3*Pb(NO3)2", true);

// 富集惰性混合物（Rh/Ir/Ru富集物）
        EnrichedInertMixture = new Material.Builder(CTNHCore.id("enriched_inert_mixture"))
                .dust()
                .color(0x696969) // 深灰色
                .iconSet(SHINY)
                .buildAndRegister()
                .setFormula("Rh*Ir*Ru*Os", true);

// 含硫酸铑水溶液（Rh₂(SO₄)₃）
        RhodiumSulfateSolution = new Material.Builder(CTNHCore.id("rhodium_sulfate_solution"))
                .liquid()
                .color(0x9370DB) // 紫罗兰色
                .iconSet(SHINY)
                .buildAndRegister()
                .setFormula("Rh2(SO4)3*H2O", true);

// 氢氧化铑（Rh(OH)₃）
        RhodiumHydroxide = new Material.Builder(CTNHCore.id("rhodium_hydroxide"))
                .dust()
                .color(0xC0C0C0) // 浅灰色
                .iconSet(FINE)
                .buildAndRegister()
                .setFormula("Rh(OH)3", true);

// 氯铑酸（H₃[RhCl₆]）
        ChlororhodicAcid = new Material.Builder(CTNHCore.id("chlororhodic_acid"))
                .liquid()
                .color(0x8B0000) // 暗红色
                .iconSet(SHINY)
                .buildAndRegister()
                .setFormula("H3[RhCl6]", true);

// 浓缩氯铑酸铵溶液（(NH₄)₃[RhCl₆]）
        ConcentratedAmmoniumChlororhodate = new Material.Builder(CTNHCore.id("concentrated_ammonium_chlororhodate"))
                .liquid()
                .color(0xFF6347) // 橙红色
                .iconSet(BRIGHT)
                .buildAndRegister()
                .setFormula("(NH4)3[RhCl6]/H2O", true);

// 氯铑酸铵晶体（(NH₄)₃[RhCl₆]）
        AmmoniumChlororhodate = new Material.Builder(CTNHCore.id("ammonium_chlororhodate"))
                .dust()
                .color(0xFF4500) // 橙红色晶体
                .iconSet(BRIGHT)
                .buildAndRegister()
                .setFormula("(NH4)3[RhCl6]", true);

// 海绵铑（高纯度铑粉）
        SpongeRhodium = new Material.Builder(CTNHCore.id("sponge_rhodium"))
                .dust()
                .color(0xE6E6FA) // 银白色
                .iconSet(SHINY)
                .buildAndRegister()
                .setFormula("Rh", true);
//珍贵金属混合物
        PreciousMetalMixture = new Material.Builder(CTNHCore.id("precious_metal_mixture"))
                .dust()
                .color(0x2F4F4F)  // 深石板灰
                .iconSet(METALLIC)
                .buildAndRegister()
                .setFormula("Ru*Ir*Os", true);  // 化学式标记为混合物
//氯铑酸铵溶液
        AmmoniumChlororhodateSolution = new Material.Builder(CTNHCore.id("ammonium_chlororhodate_solution"))
                .liquid()                      // 液态
                .color(0xFFA500)               // 橙红色（标识含铵）
                .iconSet(DULL)                 // 无光泽
                .buildAndRegister()
                .setFormula("(NH4)3[RhCl6]*H2O", true);  // 化学式（水溶液）
// 锇酸钠-钌酸钠混合溶液
        SodiumOsmateRuthenateSolution = new Material.Builder(CTNHCore.id("sodium_osmate_ruthenate_solution"))
                .liquid()
                .color(0x8A2BE2)
                .buildAndRegister()
                .setFormula("Na2OsO4*Na2RuO4*H+", true);

// 过氧化钠粉
        SodiumPeroxide = new Material.Builder(CTNHCore.id("sodium_peroxide"))
                .dust()
                .liquid()
                .color(0xFFECB3)
                .buildAndRegister()
                .setFormula("Na2O2", true);

// 氯锇酸-乙醇盐酸溶液
        ChlorosmicAcidEthanolSolution = new Material.Builder(CTNHCore.id("chlorosmic_acid_ethanol_solution"))
                .liquid()
                .color(0x9400D3)
                .buildAndRegister()
                .setFormula("H2OsCl6/C2H5OH/HCl", true);

// 氯钌酸-乙醇盐酸溶液
        ChlororuthenicAcidEthanolSolution = new Material.Builder(CTNHCore.id("chlororuthenic_acid_ethanol_solution"))
                .liquid()
                .color(0x9932CC)
                .buildAndRegister()
                .setFormula("H2RuCl6/C2H5OH/HCl", true);

// 氯锇酸气体
        ChlorosmicAcidGas = new Material.Builder(CTNHCore.id("chlorosmic_acid_gas"))
                .gas()
                .color(0xDA70D6)
                .buildAndRegister()
                .setFormula("H2OsCl6(g)", true);

// 氯钌酸气体
        ChlororuthenicAcidGas = new Material.Builder(CTNHCore.id("chlororuthenic_acid_gas"))
                .gas()
                .color(0xBA55D3)
                .buildAndRegister()
                .setFormula("H2RuCl6(g)", true);

// 氯锇酸铵
        AmmoniumChlorosmate = new Material.Builder(CTNHCore.id("ammonium_chlorosmate"))
                .dust()
                .color(0x8A2BE2)
                .buildAndRegister()
                .setFormula("(NH4)2OsCl6", true);

// 氯钌酸铵
        AmmoniumChlororuthenate = new Material.Builder(CTNHCore.id("ammonium_chlororuthenate"))
                .dust()
                .color(0x9932CC)
                .buildAndRegister()
                .setFormula("(NH4)2RuCl6", true);
// 锇酸钠-钌酸钠-氯化溶液（含OsO₄²⁻/RuO₄²⁻/Cl⁻）
        SodiumOsmateRuthenateChlorideSolution = new Material.Builder(CTNHCore.id("sodium_osmate_ruthenate_chloride_solution"))
                .liquid()                      // 液态
                .color(0x9932CC)               // 深紫色（标识含钌、锇）
                .iconSet(DULL)                 // 无光泽
                .buildAndRegister()
                .setFormula("Na2[OsO4]/Na2[RuO4]/NaCl", true); // 化学式
//乙醛
        Acetaldehyde = new Material.Builder(CTNHCore.id("acetaldehyde"))
                .liquid(new FluidBuilder().temperature(294))  // 沸点20.8°C（294K），设为室温液态
                .color(0xFFF8DC)  // 淡黄色（工业乙醛常含杂质）
                .iconSet(DULL)     // 无光泽图标
                .components(Carbon, 2, Hydrogen, 4, Oxygen, 1)  // C₂H₄O
                .buildAndRegister()
                .setFormula("CH3CHO", true);  // 结构式
    }
}
