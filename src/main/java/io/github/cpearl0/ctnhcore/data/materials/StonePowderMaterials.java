package io.github.cpearl0.ctnhcore.data.materials;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.ROUGH;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.Alumina;

public class StonePowderMaterials {

    public static Material DIRTY_HEXAFLUOROSILICIC_ACID;
    public static Material DILUTE_HEXAFLUOROSILICIC_ACID;
    public static Material STONE_RESIDUE;
    public static Material DEGASSED_MOON_STONE;
    public static Material MOON_ANORTHOSITE;
    public static Material FLUOROSILICIC_ACID;
    public static Material UNCOMMON_RESIDUES;
    public static Material DIOXYGENDIFLUORIDE;
    public static Material PARTIALLY_OXIDIZED_RESIDUES;
    public static Material INERT_RESIDUES;
    public static Material OXIDIZED_RESIDUAL_SOLUTION;
    public static Material OXIDIZED_RESIDUES;
    public static Material HEAVY_OXIDIZED_RESIDUES;
    public static Material METALLIC_RESIDUES;
    public static Material HEAVY_METALLIC_RESIDUES;
    public static Material DIAMAGNETIC_RESIDUES;
    public static Material PARAMAGNETIC_RESIDUES;
    public static Material FERROMAGNETIC_RESIDUES;
    public static Material HEAVY_DIAMAGNETIC_RESIDUES;
    public static Material HEAVY_PARAMAGNETIC_RESIDUES;
    public static Material HEAVY_FERROMAGNETIC_RESIDUES;
    public static Material EXOTIC_HEAVY_RESIDUES;
    public static Material CLEAN_INERT_RESIDUES;
    public static Material DILUTE_HYDROFLUORIC_ACID;
    public static Material HELIUM3_HYDRIDE;
    public static Material ULTRAACIDIC_RESIDUE_SOLUTION;

    public static void init() {
        DIRTY_HEXAFLUOROSILICIC_ACID = REGISTRATE.material(CTNHCore.id("dirty_hexafluorosilicic_acid"))
                .cnlang("污浊的六氟硅酸")
                .liquid()
                .color(0xDB6767)
                .buildAndRegister();

        DILUTE_HEXAFLUOROSILICIC_ACID = REGISTRATE.material(CTNHCore.id("dilute_hexafluorosilicic_acid"))
                .cnlang("稀释的六氟硅酸")
                .liquid()
                .color(0x7AE880)
                .buildAndRegister();

        STONE_RESIDUE = REGISTRATE.material(CTNHCore.id("stone_residue"))
                .cnlang("石头残渣")
                .dust()
                .color(0xA1A19E)
                .buildAndRegister();

        // GT generates the LV mineral separation recipe; gas-bearing Moonstone remains a separate material.
        // Keep Moonstone's ROUGH texture and neutral greys, darkened equally for the processed state.
        DEGASSED_MOON_STONE = REGISTRATE.material(CTNHCore.id("degassed_moon_stone"))
                .cnlang("脱气月石")
                .dust()
                .color(0x9B9B9B).secondaryColor(0x656565).iconSet(ROUGH)
                .components(SiliconDioxide, 20, Alumina, 8, Quicklime, 4, Magnesia, 3, Ilmenite, 1)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .buildAndRegister();

        // Simplify anorthite-rich lunar rock into oxide groups for GT's generated mineral separation.
        MOON_ANORTHOSITE = REGISTRATE.material(CTNHCore.id("moon_anorthosite"))
                .cnlang("月球斜长岩")
                .dust()
                .color(0xC9CDD3).secondaryColor(0x9298A2).iconSet(ROUGH)
                .components(SiliconDioxide, 2, Alumina, 1, Quicklime, 1)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .buildAndRegister();

        FLUOROSILICIC_ACID = REGISTRATE.material(CTNHCore.id("fluorosilicic_acid"))
                .cnlang("氟硅酸")
                .liquid()
                .color(0x40ED57)
                .components(Hydrogen, 2, Silicon, 1, Fluorine, 6)
                .buildAndRegister();

        UNCOMMON_RESIDUES = REGISTRATE.material(CTNHCore.id("uncommon_residues"))
                .cnlang("精良残渣")
                .dust()
                .color(0x4F86C9)
                .buildAndRegister();

        DIOXYGENDIFLUORIDE = REGISTRATE.material(CTNHCore.id("dioxygendifluoride"))
                .cnlang("二氟化二氧")
                .liquid()
                .color(0x4FBBC9)
                .components(Fluorine, 2, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        PARTIALLY_OXIDIZED_RESIDUES = REGISTRATE.material(CTNHCore.id("partially_oxidized_residues"))
                .cnlang("待分离氧化金属残渣")
                .dust()
                .color(0x853525)
                .buildAndRegister();

        INERT_RESIDUES = REGISTRATE.material(CTNHCore.id("inert_residues"))
                .cnlang("纯净残渣")
                .dust()
                .color(0x9D85A6)
                .buildAndRegister();

        OXIDIZED_RESIDUAL_SOLUTION = REGISTRATE.material(CTNHCore.id("oxidized_residual_solution"))
                .cnlang("氧化残渣溶液")
                .liquid()
                .color(0x83F2E1)
                .buildAndRegister();

        OXIDIZED_RESIDUES = REGISTRATE.material(CTNHCore.id("oxidized_residues"))
                .cnlang("氧化残渣")
                .dust()
                .color(0x5E2B87)
                .buildAndRegister();

        HEAVY_OXIDIZED_RESIDUES = REGISTRATE.material(CTNHCore.id("heavy_oxidized_residues"))
                .cnlang("重氧化残渣")
                .dust()
                .color(0x5D208F)
                .buildAndRegister();

        METALLIC_RESIDUES = REGISTRATE.material(CTNHCore.id("metallic_residues"))
                .cnlang("金属残渣")
                .dust()
                .color(0x22A372)
                .buildAndRegister();

        HEAVY_METALLIC_RESIDUES = REGISTRATE.material(CTNHCore.id("heavy_metallic_residues"))
                .cnlang("重金属残渣")
                .dust()
                .color(0x3488B5)
                .buildAndRegister();

        DIAMAGNETIC_RESIDUES = REGISTRATE.material(CTNHCore.id("diamagnetic_residues"))
                .cnlang("抗磁性残渣")
                .dust()
                .color(0x4CD98E)
                .buildAndRegister();

        PARAMAGNETIC_RESIDUES = REGISTRATE.material(CTNHCore.id("paramagnetic_residues"))
                .cnlang("顺磁性残渣")
                .dust()
                .color(0x4CBFD9)
                .buildAndRegister();

        FERROMAGNETIC_RESIDUES = REGISTRATE.material(CTNHCore.id("ferromagnetic_residues"))
                .cnlang("铁磁性残渣")
                .dust()
                .color(0x32B065)
                .buildAndRegister();

        HEAVY_DIAMAGNETIC_RESIDUES = REGISTRATE.material(CTNHCore.id("heavy_diamagnetic_residues"))
                .cnlang("重抗磁性残渣")
                .dust()
                .color(0x3269B0)
                .buildAndRegister();
        HEAVY_PARAMAGNETIC_RESIDUES = REGISTRATE.material(CTNHCore.id("heavy_paramagnetic_residues"))
                .cnlang("重顺磁性残渣")
                .dust()
                .color(0x2D9E4D)
                .buildAndRegister();

        HEAVY_FERROMAGNETIC_RESIDUES = REGISTRATE.material(CTNHCore.id("heavy_ferromagnetic_residues"))
                .cnlang("重铁磁性残渣")
                .dust()
                .color(0x1E873B)
                .buildAndRegister();

        EXOTIC_HEAVY_RESIDUES = REGISTRATE.material(CTNHCore.id("exotic_heavy_residues"))
                .cnlang("重奇异残渣")
                .dust()
                .color(0x26A2B5)
                .buildAndRegister();

        CLEAN_INERT_RESIDUES = REGISTRATE.material(CTNHCore.id("clean_inert_residues"))
                .cnlang("纯净惰性残渣")
                .dust()
                .color(0x0EBD54)
                .buildAndRegister();

        DILUTE_HYDROFLUORIC_ACID = REGISTRATE.material(CTNHCore.id("dilute_hydrofluoric_acid"))
                .cnlang("稀氢氟酸")
                .liquid()
                .color(0xC7D7DE)
                .components(Hydrogen, 1, Fluorine, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        HELIUM3_HYDRIDE = REGISTRATE.material(CTNHCore.id("helium3_hydride"))
                .cnlang("氢化氦-3")
                .liquid()
                .color(0xD7E36A)
                .components(Helium3, 1, Hydrogen, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        ULTRAACIDIC_RESIDUE_SOLUTION = REGISTRATE.material(CTNHCore.id("ultraacidic_residue_solution"))
                .cnlang("超酸性残渣溶液")
                .liquid()
                .color(0x59FF7D)
                .buildAndRegister();
    }
}
