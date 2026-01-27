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
                .formula("AuCl3")
                .dust()
                .color(0x70552D)
                .buildAndRegister();
        CHLOROAURIC_ACID = REGISTRATE.material(CTNHCore.id("chloroauric_acid"))
                .cnlang("氯金酸")
                .formula("AuCl3")
                .liquid()
                .color(0xBAB02D)
                .buildAndRegister();
    }
}
