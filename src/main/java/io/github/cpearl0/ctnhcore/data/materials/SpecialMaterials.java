package io.github.cpearl0.ctnhcore.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import io.github.cpearl0.ctnhcore.CTNHCore;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.IndiumGalliumPhosphide;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class SpecialMaterials {

    public static Material QUARTZ_GLASS;
    public static Material AMMONIUM_FLUORIDE;
    public static Material DIBISMUTHHYDROBORAT;
    public static Material BISMUTH_TELLURITE;
    public static Material CIRCUIT_COMPOUND;
    public static Material TUNGSTEN_TRIOXIDE;
    public static Material RESONANCE_CRYSTAL;
    public static Material ECHO_CRYSTAL;
    public static Material STELLAR_ENERGY;
    public static Material ENERGY_ESSENCE_SECRETION;

    public static void init() {
        QUARTZ_GLASS = REGISTRATE.material(CTNHCore.id("quartz_glass"))
                .cnlang("石英玻璃")
                .dust()
                .components(Glass, 1, CertusQuartz, 1)
                .color(0xD1F1FA)
                .buildAndRegister();

        AMMONIUM_FLUORIDE = REGISTRATE.material(CTNHCore.id("ammonium_fluoride"))
                .cnlang("氟化铵")
                .formula("NH4F")
                .liquid()
                .color(0xFFCCCC)
                .buildAndRegister();

        DIBISMUTHHYDROBORAT = REGISTRATE.material(CTNHCore.id("dibismuthhydroborat"))
                .cnlang("硼氢二铋")
                .dust()
                .color(0x0ED138)
                .components(Boron, 1, Hydrogen, 1, Bismuth, 2)
                .buildAndRegister();

        BISMUTH_TELLURITE = REGISTRATE.material(CTNHCore.id("bismuth_tellurite"))
                .cnlang("亚碲酸铋")
                .dust()
                .color(0x95EB88)
                .components(Bismuth, 2, Tellurium, 3)
                .buildAndRegister();

        CIRCUIT_COMPOUND = REGISTRATE.material(CTNHCore.id("circuit_compound"))
                .cnlang("电路板化合物")
                .dust()
                .color(0x4A4A4A)
                .components(DIBISMUTHHYDROBORAT, 3, BISMUTH_TELLURITE, 2, IndiumGalliumPhosphide, 1)
                .buildAndRegister();

        TUNGSTEN_TRIOXIDE = REGISTRATE.material(CTNHCore.id("tungsten_trioxide"))
                .cnlang("三氧化钨")
                .formula("WO3")
                .dust()
                .color(0x474444)
                .buildAndRegister();

        RESONANCE_CRYSTAL = REGISTRATE.material(CTNHCore.id("resonance_crystal"))
                .cnlang("共振水晶")
                .dust()
                .color(0xDDA0DD) // 粉紫色
                .buildAndRegister();

        ECHO_CRYSTAL = REGISTRATE.material(CTNHCore.id("echo_crystal"))
                .cnlang("回声水晶")
                .dust()
                .color(0x4B0082) // 幽匿深紫色
                .buildAndRegister();

        STELLAR_ENERGY = REGISTRATE.material(CTNHCore.id("stellar_energy"))
                .cnlang("星际能量体")
                .liquid()
                .color(0xFFFFFF) // 纯白色
                .buildAndRegister();

        ENERGY_ESSENCE_SECRETION = REGISTRATE.material(CTNHCore.id("energy_essence_secretion"))
                .cnlang("能量体分泌物")
                .liquid()
                .color(0x808080) // 中性灰色
                .buildAndRegister();
    }
}
