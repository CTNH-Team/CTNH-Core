package io.github.cpearl0.ctnhcore.data.materials;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class ZrHfSeparationMaterials {

    public static Material ZR_HF_SEPARATION_MIX;
    public static Material ZR_HF_CHLORIDE;
    public static Material ZIRCON_CHLORINATING_RESIDUE;
    public static Material ZR_HF_OXY_CHLORIDE;
    public static Material CUBIC_ZIRCONIA;
    public static Material HAFNIUM_OXIDE;
    public static Material HAFNIUM_CHLORIDE;

    public static void init() {
        ZR_HF_SEPARATION_MIX = REGISTRATE.material(CTNHCore.id("zr_hf_separation_mix"))
                .cnlang("锆-铪混合物分离液")
                .liquid()
                .color(0xCFF760)
                .buildAndRegister();

        ZR_HF_CHLORIDE = REGISTRATE.material(CTNHCore.id("zr_hf_chloride"))
                .cnlang("锆-铪氯化物")
                .liquid()
                .color(0x65F04A)
                .buildAndRegister();

        ZIRCON_CHLORINATING_RESIDUE = REGISTRATE.material(CTNHCore.id("zircon_chlorinating_residue"))
                .cnlang("锆氯化反应残渣")
                .liquid()
                .color(0x50BA3A)
                .buildAndRegister();

        ZR_HF_OXY_CHLORIDE = REGISTRATE.material(CTNHCore.id("zr_hf_oxy_chloride"))
                .cnlang("锆-铪氯氧化物")
                .liquid()
                .color(0x70B063)
                .buildAndRegister();

        CUBIC_ZIRCONIA = REGISTRATE.material(CTNHCore.id("cubic_zirconia"))
                .cnlang("立方氧化锆")
                .dust()
                .color(0xF9E0FC)
                .components(Zirconium, 1, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        HAFNIUM_OXIDE = REGISTRATE.material(CTNHCore.id("hafnium_oxide"))
                .cnlang("二氧化铪")
                .dust()
                .color(0x6B6E6B)
                .components(Hafnium, 1, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        HAFNIUM_CHLORIDE = REGISTRATE.material(CTNHCore.id("hafnium_chloride"))
                .cnlang("四氯化铪")
                .dust()
                .color(0x9DA19C)
                .components(Hafnium, 1, Chlorine, 4)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
    }
}
