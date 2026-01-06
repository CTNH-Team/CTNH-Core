package io.github.cpearl0.ctnhcore.data.materials;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.EXT2_METAL;
import static io.github.cpearl0.ctnhcore.registry.CTNHMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class CreateMaterials {

    public static void init() {
        AndesiteAlloy = REGISTRATE.material(CTNHCore.id("andesite_alloy"))
                .cnlang("安山合金")
                .color(0xA7AD9F)
                .ingot()
                .liquid()
                .iconSet(MaterialIconSet.DULL)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR)
                .buildAndRegister().setFormula("(Mg3Si2H4O9)4(KNO3)Fe");

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
    }
}
