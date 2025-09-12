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
        addFluid(GTMaterials.SodiumBisulfate);
        addFluid(GTMaterials.SodiumHydroxide);
        addGas(GTMaterials.OsmiumTetroxide);
        addGas(GTMaterials.RutheniumTetroxide);

        PalladiumMetal = new Material.Builder(CTNHCore.id("palladium_metal")).dust().color(0x30302E).iconSet(SAND).flags(DISABLE_DECOMPOSITION)
                .buildAndRegister().setFormula("??Pd??");
        PlatinumMetal = new Material.Builder(CTNHCore.id("platinum_metal")).dust().color(0xEBEBB2).iconSet(ROUGH).flags(DISABLE_DECOMPOSITION)
                .buildAndRegister().setFormula("??PtPdIrOsRhRu??");

        PlatinumOre = new Material.Builder(CTNHCore.id("platinum_ore"))
                .dust()
                .color(0xFFD700)
                .iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(GTMaterials.Platinum, 1)
                .buildAndRegister()
                .setFormula("Pt*Pd*", true);
        Platinum.getProperty(PropertyKey.ORE).setDirectSmeltResult(PlatinumOre);
        PalladiumOre = new Material.Builder(CTNHCore.id("palladium_ore"))
                .dust()
                .color(0xD8BFD8)
                .iconSet(ROUGH)
                .components(GTMaterials.Palladium, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister()
                .setFormula("Pd*", true);
        Palladium.getProperty(PropertyKey.ORE).setDirectSmeltResult(PalladiumOre);

        IridiumDioxide = new Material.Builder(CTNHCore.id("iridium_dioxide")).dust().color(0xA2BFFF).iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION).buildAndRegister().setFormula("IrO2");
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
                .dust()
                .color(0xB0C4DE)
                .iconSet(SHINY)
                .buildAndRegister()
                .setFormula("Pd", true);

        SpongePlatinum = new Material.Builder(CTNHCore.id("sponge_platinum"))
                .dust()
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
