package io.github.cpearl0.ctnhcore.data.materials;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

/** Lunar brine processing states and the precursors for its sulfide solid electrolyte. */
public class MoonBrineMaterials {

    public static Material CONCENTRATED_MOON_BRINE;
    public static Material LITHIUM_MOTHER_LIQUOR;
    public static Material PURIFIED_LITHIUM_BRINE;
    public static Material MAGNESIUM_CALCIUM_PRECIPITATE;
    public static Material BATTERY_GRADE_LITHIUM_CHLORIDE;
    public static Material LITHIUM_SULFIDE;
    public static Material PHOSPHORUS_PENTASULFIDE;
    public static Material SULFIDE_ELECTROLYTE_MIXTURE;
    public static Material LITHIUM_THIOPHOSPHATE_CHLORIDE_ELECTROLYTE;

    public static void init() {
        CONCENTRATED_MOON_BRINE = REGISTRATE.material(CTNHCore.id("concentrated_moon_brine"))
                .lang("Concentrated Moon Brine").cnlang("月海浓缩卤水")
                .formula("(NaCl)16(MgCl2)2(CaCl2)(LiCl)6+Br?+I?+H2O")
                .liquid().color(0x426F86)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        LITHIUM_MOTHER_LIQUOR = REGISTRATE.material(CTNHCore.id("lithium_mother_liquor"))
                .lang("Lunar Lithium Mother Liquor").cnlang("月海含锂母液")
                .formula("(MgCl2)2(CaCl2)(LiCl)6+H2O")
                .liquid().color(0x759487)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        PURIFIED_LITHIUM_BRINE = REGISTRATE.material(CTNHCore.id("purified_lithium_brine"))
                .lang("Purified Lithium Brine").cnlang("净化锂卤液")
                .formula("LiCl(aq)")
                .liquid().color(0xBBCFDB)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        MAGNESIUM_CALCIUM_PRECIPITATE = REGISTRATE.material(CTNHCore.id("magnesium_calcium_precipitate"))
                .lang("Magnesium Calcium Precipitate").cnlang("镁钙沉淀")
                .formula("(Mg(OH)2)2CaCO3")
                .dust().color(0xC8C3B3)
                .components(Magnesium, 2, Calcium, 1, Hydrogen, 4, Carbon, 1, Oxygen, 7)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        BATTERY_GRADE_LITHIUM_CHLORIDE = REGISTRATE.material(CTNHCore.id("battery_grade_lithium_chloride"))
                .lang("Battery Grade Lithium Chloride").cnlang("电池级氯化锂")
                .formula("LiCl")
                .dust().color(0xE2EAF0)
                .components(Lithium, 1, Chlorine, 1)
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .buildAndRegister();
        LITHIUM_SULFIDE = REGISTRATE.material(CTNHCore.id("lithium_sulfide"))
                .lang("Lithium Sulfide").cnlang("硫化锂")
                .formula("Li2S")
                .dust().color(0xD9DCA5)
                .components(Lithium, 2, Sulfur, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        PHOSPHORUS_PENTASULFIDE = REGISTRATE.material(CTNHCore.id("phosphorus_pentasulfide"))
                .lang("Phosphorus Pentasulfide").cnlang("五硫化二磷")
                .formula("P2S5")
                .dust().color(0xE8BE4B)
                .components(Phosphorus, 2, Sulfur, 5)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        SULFIDE_ELECTROLYTE_MIXTURE = REGISTRATE.material(CTNHCore.id("sulfide_electrolyte_mixture"))
                .lang("Sulfide Electrolyte Mixture").cnlang("氯硫化物电解质混料")
                .formula("Li6PS5Cl")
                .dust().color(0xC7BC75)
                .components(Lithium, 6, Phosphorus, 1, Sulfur, 5, Chlorine, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        LITHIUM_THIOPHOSPHATE_CHLORIDE_ELECTROLYTE = REGISTRATE
                .material(CTNHCore.id("lithium_thiophosphate_chloride_electrolyte"))
                .lang("Lithium Thiophosphate Chloride Solid Electrolyte").cnlang("氯硫化磷锂固态电解质")
                .formula("Li6PS5Cl")
                .dust().color(0xE4DBA8)
                .components(Lithium, 6, Phosphorus, 1, Sulfur, 5, Chlorine, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
    }
}
