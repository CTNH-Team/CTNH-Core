package io.github.cpearl0.ctnhcore.data.materials;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Boron;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Oxygen;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class BoronChainMaterials {

    public static Material BORAX_ACID_SOLUTION;
    public static Material BORON_TRIOXIDE;

    public static void init() {
        BORAX_ACID_SOLUTION = REGISTRATE.material(CTNHCore.id("borax_acid_solution"))
                .cnlang("硼砂酸溶液")
                .formula("2NaCl+4H3BO3+5H2O")
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
    }
}
