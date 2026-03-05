package io.github.cpearl0.ctnhcore.data.materials;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.*;

public class CreateMaterials {

    public static Material RefinedRadiance;
    public static Material ShadowSteel;
    public static Material SLAG;
    public static Material CALCIUM_SULFIDE;
    public static Material ASURINE;
    public static Material CRIMSITE;
    public static Material OCHRUM;
    public static Material VERIDIUM;
    public static Material ASURINE_SLURRY;
    public static Material CRIMSITE_SLURRY;
    public static Material OCHRUM_SLURRY;
    public static Material VERIDIUM_SLURRY;
    // public static Material MAGNETO_RESONATIC;

    public static void init() {
        RefinedRadiance = REGISTRATE.material(CTNHCore.id("refined_radiance"))
                .cnlang("光辉石")
                .ingot()
                .fluid()
                .color(0xfffef9)
                .iconSet(MaterialIconSet.METALLIC)
                .appendFlags(EXT2_METAL, GENERATE_FINE_WIRE, GENERATE_GEAR, GENERATE_FRAME)
                .buildAndRegister();

        ShadowSteel = REGISTRATE.material(CTNHCore.id("shadow_steel"))
                .cnlang("暗影钢")
                .ingot()
                .fluid()
                .color(0x35333c)
                .iconSet(MaterialIconSet.METALLIC)
                .appendFlags(EXT2_METAL, GENERATE_FINE_WIRE, GENERATE_GEAR, GENERATE_FRAME)
                .buildAndRegister();

        SLAG = REGISTRATE.material(CTNHCore.id("slag"))
                .cnlang("炉渣")
                .liquid()
                .ingot()
                .color(0x9E570A)
                .buildAndRegister();

        CALCIUM_SULFIDE = REGISTRATE.material(CTNHCore.id("calcium_sulfide"))
                .cnlang("硫化钙")
                .dust()
                .color(0xFFFFDC)
                .components(Calcium, 1, Sulfur, 1)
                .buildAndRegister();

        ASURINE = REGISTRATE.material(CTNHCore.id("asurine"))
                .cnlang("皓蓝石")
                .dust()
                .color(0x50A0DE)
                .buildAndRegister();

        CRIMSITE = REGISTRATE.material(CTNHCore.id("crimsite"))
                .cnlang("绯红岩")
                .dust()
                .color(0xBF4848)
                .buildAndRegister();

        OCHRUM = REGISTRATE.material(CTNHCore.id("ochrum"))
                .cnlang("赭金砂")
                .dust()
                .color(0xC9AF03)
                .buildAndRegister();

        VERIDIUM = REGISTRATE.material(CTNHCore.id("veridium"))
                .cnlang("辉绿岩")
                .dust()
                .color(0x43B567)
                .buildAndRegister();

        ASURINE_SLURRY = REGISTRATE.material(CTNHCore.id("asurine_slurry"))
                .cnlang("皓蓝石浆液")
                .liquid()
                .color(0x50A0DE)
                .buildAndRegister();

        CRIMSITE_SLURRY = REGISTRATE.material(CTNHCore.id("crimsite_slurry"))
                .cnlang("绯红岩浆液")
                .liquid()
                .color(0xBF4848)
                .buildAndRegister();

        OCHRUM_SLURRY = REGISTRATE.material(CTNHCore.id("ochrum_slurry"))
                .cnlang("赭金砂浆液")
                .liquid()
                .color(0xC9AF03)
                .buildAndRegister();

        VERIDIUM_SLURRY = REGISTRATE.material(CTNHCore.id("veridium_slurry"))
                .cnlang("辉绿岩浆液")
                .liquid()
                .color(0x43B567)
                .buildAndRegister();

        // MAGNETO_RESONATIC = REGISTRATE.material(CTNHCore.id("magneto_resonatic"))
        // .cnlang("共振紫晶")
        // .gem()
        // .color(0xFF97FF)
        // .iconSet(MAGNETIC)
        // .components(Zanite, 1, Ambrosium, 1, Skyjade, 1, CUBIC_ZIRCONIA, 1, MagneticSteel, 1)
        // .buildAndRegister();
    }
}
