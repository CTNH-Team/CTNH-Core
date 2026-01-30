package io.github.cpearl0.ctnhcore.data.materials;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class PitchblendeRefiningMaterials {

    public static Material URANYL_CHLORIDE_SOLUTION;
    public static Material URANYL_NITRATE_SOLUTION;
    public static Material PURIFIED_URANYL_NITRATE;
    public static Material URANIUM_SULFATE_WASTE_SOLUTION;
    public static Material URANIUM_DIURANATE;
    public static Material POTASSIUM_URANYL_TRICARBONATE;
    public static Material PIRANHA_SOLUTION;
    public static Material URANIUM_REFINEMENT_WASTE_SOLUTION;
    public static Material URANIUM_PEROXIDE_THORIUM_OXIDE;
    public static Material CAESIUM_HYDROXIDE;
    public static Material MOLYBDENUM_TRIOXIDE;
    public static Material URANIUM_THORIUM_OXIDE;
    public static Material URANYL_THORIUM_SULFATE;
    public static Material URANYL_THORIUM_NITRATE;
    public static Material URANIUM_OXIDE_THORIUM_NITRATE;
    public static Material URANIUM_DIOXIDE;
    public static Material THORIUM_NITRATE_SOLUTION;
    public static Material THORIUM_OXIDE;

    public static void init() {
        URANYL_CHLORIDE_SOLUTION = REGISTRATE.material(CTNHCore.id("uranyl_chloride_solution"))
                .cnlang("氯化铀酰溶液")
                .liquid()
                .color(0xC5DE56)
                .buildAndRegister();

        URANYL_NITRATE_SOLUTION = REGISTRATE.material(CTNHCore.id("uranyl_nitrate_solution"))
                .cnlang("硝酸铀酰溶液")
                .liquid()
                .color(0xCAE065)
                .buildAndRegister();
        PURIFIED_URANYL_NITRATE = REGISTRATE.material(CTNHCore.id("purified_uranyl_nitrate"))
                .cnlang("纯化硝酸铀酰溶液")
                .liquid()
                .color(0xE5F552)
                .buildAndRegister();

        URANIUM_SULFATE_WASTE_SOLUTION = REGISTRATE.material(CTNHCore.id("uranium_sulfate_waste_solution"))
                .cnlang("硫酸铀废液")
                .liquid()
                .color(0x9EB837)
                .buildAndRegister();

        URANIUM_DIURANATE = REGISTRATE.material(CTNHCore.id("uranium_diuranate"))
                .cnlang("重铀酸铵")
                .liquid()
                .color(0xC7DB42)
                .buildAndRegister();

        POTASSIUM_URANYL_TRICARBONATE = REGISTRATE.material(CTNHCore.id("potassium_uranyl_tricarbonate"))
                .cnlang("三碳酸铀酰钾")
                .dust()
                .color(0xD4E04C)
                .buildAndRegister();

        PIRANHA_SOLUTION = REGISTRATE.material(CTNHCore.id("piranha_solution"))
                .cnlang("食人鱼洗液")
                .liquid()
                .color(0x6C5FF5)
                .buildAndRegister();

        URANIUM_REFINEMENT_WASTE_SOLUTION = REGISTRATE.material(CTNHCore.id("uranium_refinement_waste_solution"))
                .cnlang("精纯铀废液")
                .liquid()
                .color(0xC6DB5B)
                .buildAndRegister();

        URANIUM_PEROXIDE_THORIUM_OXIDE = REGISTRATE.material(CTNHCore.id("uranium_peroxide_thorium_oxide"))
                .cnlang("四氧化铀-氧化钍混合物")
                .dust()
                .color(0x5B5C5A)
                .buildAndRegister();

        CAESIUM_HYDROXIDE = REGISTRATE.material(CTNHCore.id("caesium_hydroxide"))
                .cnlang("氢氧化铯")
                .dust()
                .color(0xDADBD6)
                .components(Caesium, 1, Oxygen, 1, Hydrogen, 1)
                .buildAndRegister();

        MOLYBDENUM_TRIOXIDE = REGISTRATE.material(CTNHCore.id("molybdenum_trioxide"))
                .cnlang("三氧化钼")
                .dust()
                .color(0x8F8AE5)
                .components(Molybdenum, 1, Oxygen, 3)
                .buildAndRegister();

        URANIUM_THORIUM_OXIDE = REGISTRATE.material(CTNHCore.id("uranium_thorium_oxide"))
                .cnlang("二氧化铀-氧化钍混合物")
                .dust()
                .color(0x666665)
                .buildAndRegister();

        URANYL_THORIUM_SULFATE = REGISTRATE.material(CTNHCore.id("uranyl_thorium_sulfate"))
                .cnlang("硫酸铀酰-硫酸钍混合物")
                .dust()
                .color(0xDCED3F)
                .buildAndRegister();

        URANYL_THORIUM_NITRATE = REGISTRATE.material(CTNHCore.id("uranyl_thorium_nitrate"))
                .cnlang("硝酸铀酰-硝酸钍混合物")
                .dust()
                .color(0xDFF236)
                .buildAndRegister();

        URANIUM_OXIDE_THORIUM_NITRATE = REGISTRATE.material(CTNHCore.id("uranium_oxide_thorium_nitrate"))
                .cnlang("氧化铀酰-硝酸钍混合物")
                .dust()
                .color(0x62F236)
                .buildAndRegister();

        URANIUM_DIOXIDE = REGISTRATE.material(CTNHCore.id("uranium_dioxide"))
                .cnlang("二氧化铀")
                .dust()
                .color(0x4ECF26)
                .components(Uranium238, 1, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        THORIUM_NITRATE_SOLUTION = REGISTRATE.material(CTNHCore.id("thorium_nitrate_solution"))
                .cnlang("硝酸钍溶液")
                .liquid()
                .color(0x4BAB2D)
                .components(Thorium, 1, Nitrogen, 1, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        THORIUM_OXIDE = REGISTRATE.material(CTNHCore.id("thorium_oxide"))
                .cnlang("氧化钍")
                .dust()
                .color(0x757373)
                .components(Thorium, 1, Oxygen, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
    }
}
