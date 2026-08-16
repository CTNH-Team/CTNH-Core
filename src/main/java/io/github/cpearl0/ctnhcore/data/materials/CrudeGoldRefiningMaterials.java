package io.github.cpearl0.ctnhcore.data.materials;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.METALLIC;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class CrudeGoldRefiningMaterials {

    public static Material GOLD_ALLOY;
    public static Material SODIUM_HEXAFLUOROALUMINATE;
    public static Material GOLD_LEACH;
    public static Material COPPER_LEACH;
    public static Material CHLOROAURIC_ACID;
    public static Material SODIUM_CYANIDE;
    public static Material PRECIOUS_METAL_CYANO_COMPLEX;
    public static Material ZINC_CYANIDE_COMPLEX;
    public static Material CYANIDE_TAILINGS;
    public static Material GOLD_MUD;
    public static Material COPPER_CHLORIDE;
    public static Material SILVER_CHLORIDE;

    public static void init() {
        GOLD_ALLOY = REGISTRATE.material(CTNHCore.id("gold_alloy"))
                .cnlang("金合金")
                .ingot()
                .color(0xA69226)
                .iconSet(METALLIC)
                .buildAndRegister();
        SODIUM_HEXAFLUOROALUMINATE = REGISTRATE.material(CTNHCore.id("sodium_hexafluoroaluminate"))
                .cnlang("六氟铝酸钠")
                .formula("Na3AlF6")
                .liquid()
                .color(0x75471F)
                .buildAndRegister();
        GOLD_LEACH = REGISTRATE.material(CTNHCore.id("gold_leach"))
                .cnlang("金浸出物")
                .formula("(Au)")
                .dust()
                .color(0x695D18)
                .buildAndRegister();
        COPPER_LEACH = REGISTRATE.material(CTNHCore.id("copper_leach"))
                .cnlang("铜浸出物")
                .formula("(Cu)")
                .dust()
                .color(0x70552D)
                .buildAndRegister();
        CHLOROAURIC_ACID = REGISTRATE.material(CTNHCore.id("chloroauric_acid"))
                .cnlang("氯金酸")
                .formula("HAuCl4")
                .liquid()
                .color(0xBAB02D)
                .buildAndRegister();
        SODIUM_CYANIDE = REGISTRATE.material(CTNHCore.id("sodium_cyanide"))
                .cnlang("氰化钠")
                .formula("NaCN")
                .liquid()
                .color(0xD8D8D8)
                .buildAndRegister();
        PRECIOUS_METAL_CYANO_COMPLEX = REGISTRATE.material(CTNHCore.id("precious_metal_cyano_complex"))
                .cnlang("贵金属氰络合物")
                .formula("(Na[Au(CN)2])(NaOH)")
                .liquid()
                .color(0xC9A227)
                .buildAndRegister();
        ZINC_CYANIDE_COMPLEX = REGISTRATE.material(CTNHCore.id("zinc_cyanide_complex"))
                .cnlang("氰化锌络合物")
                .formula("(Zn(CN)2)(NaCN)2(NaOH)2")
                .liquid()
                .color(0x9EA7AD)
                .buildAndRegister();
        CYANIDE_TAILINGS = REGISTRATE.material(CTNHCore.id("cyanide_tailings"))
                .cnlang("氰化尾渣")
                .formula("?")
                .dust()
                .color(0x6E5B3A)
                .buildAndRegister();
        GOLD_MUD = REGISTRATE.material(CTNHCore.id("gold_mud"))
                .cnlang("金泥")
                .formula("(Au?)")
                .dust()
                .liquid()
                .color(0xC7A317)
                .iconSet(METALLIC)
                .buildAndRegister();
        COPPER_CHLORIDE = REGISTRATE.material(CTNHCore.id("copper_chloride"))
                .cnlang("氯化铜")
                .formula("CuCl2")
                .liquid()
                .color(0x6FA5C9)
                .buildAndRegister();
        SILVER_CHLORIDE = REGISTRATE.material(CTNHCore.id("silver_chloride"))
                .cnlang("氯化银")
                .formula("AgCl")
                .dust()
                .color(0xD8D8DE)
                .buildAndRegister();
    }
}
