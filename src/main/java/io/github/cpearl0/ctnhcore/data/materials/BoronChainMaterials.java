package io.github.cpearl0.ctnhcore.data.materials;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Boron;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Oxygen;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class BoronChainMaterials {

    public static Material LEACHED_BORAX_SOLUTION;
    public static Material BORON_TRIOXIDE;
    public static Material LEACHED_OLIVINE_SOLUTION;
    public static Material MAGNESIUM_HYDROXIDE;
    public static Material IRON_HYDROXIDE;

    public static void init() {
        LEACHED_BORAX_SOLUTION = REGISTRATE.material(CTNHCore.id("leached_borax_solution"))
                .cnlang("酸浸硼砂溶液")
                .formula("2NaCl+4H3BO3+nH2O")
                .liquid()
                .color(0xE8E8E8)
                .buildAndRegister();
        BORON_TRIOXIDE = REGISTRATE.material(CTNHCore.id("boron_trioxide"))
                .cnlang("三氧化二硼")
                .dust()
                .color(0xE8E8F0)
                .components(Boron, 2, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        LEACHED_OLIVINE_SOLUTION = REGISTRATE.material(CTNHCore.id("leached_olivine_solution"))
                .cnlang("酸浸橄榄石溶液")
                .formula("MgCl2+FeCl3+nH2O")
                .liquid()
                .color(0x7A8A7A)
                .buildAndRegister();
        MAGNESIUM_HYDROXIDE = REGISTRATE.material(CTNHCore.id("magnesium_hydroxide"))
                .cnlang("氢氧化镁")
                .formula("Mg(OH)2")
                .dust()
                .color(0xE8E8E8)
                .buildAndRegister();
        IRON_HYDROXIDE = REGISTRATE.material(CTNHCore.id("iron_hydroxide"))
                .cnlang("氢氧化铁")
                .formula("Fe(OH)3")
                .dust()
                .color(0xB8860B)
                .buildAndRegister();
    }
}
