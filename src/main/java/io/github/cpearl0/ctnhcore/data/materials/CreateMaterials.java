package io.github.cpearl0.ctnhcore.data.materials;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHMaterials;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.EXT2_METAL;
import static io.github.cpearl0.ctnhcore.registry.CTNHMaterials.*;

public class CreateMaterials {
    public static void init() {
        AndesiteAlloy = new Material.Builder(CTNHCore.id("andesite_alloy"))
                .color(0xA7AD9F)
                .ingot()
                .liquid()
                .iconSet(MaterialIconSet.DULL)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR)
                .buildAndRegister().setFormula("(Mg3Si2H4O9)4(KNO3)Fe");

        RefinedRadiance = new Material.Builder(CTNHCore.id("refined_radiance"))
                .ingot()
                .fluid()
                .color(0xfffef9)
                .iconSet(MaterialIconSet.METALLIC)
                .appendFlags(EXT2_METAL, GENERATE_FINE_WIRE, GENERATE_GEAR, GENERATE_FRAME)
                .buildAndRegister();

        ShadowSteel = new Material.Builder(CTNHCore.id("shadow_steel"))
                .ingot()
                .fluid()
                .color(0x35333c)
                .iconSet(MaterialIconSet.METALLIC)
                .appendFlags(EXT2_METAL, GENERATE_FINE_WIRE, GENERATE_GEAR, GENERATE_FRAME)
                .buildAndRegister();
    }
}
