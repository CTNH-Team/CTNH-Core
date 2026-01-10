package io.github.cpearl0.ctnhcore.data.materials;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHElements;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.METALLIC;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.NeutroniumMixture;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.addOre;

public class AdastraMaterials {

    public static Material Desh;
    public static Material Ostrum;
    public static Material Calorite;

    public static void init() {
        addOre(Neutronium, NeutroniumMixture);
        addOre(NaquadahEnriched, NaquadahMaterials.EnrichedNaquadahOxideMixture);
        addOre(Naquadria, NaquadahMaterials.NaquadriaOxideMixture);
        addOre(Perlite);
        addOre(Uvarovite);
        addOre(Andradite);
        addOre(Arsenic);
        addOre(Bismuth);
        addOre(Antimony);
        addOre(Uranium235);
        addOre(Uranium238);
        addOre(Plutonium241);
        addOre(Gallium);
        addOre(Niobium);
        addOre(Vanadium);
        addOre(Osmium);
        addOre(Iridium);
        addOre(Titanium);
        addOre(Manganese);
        addOre(Rutile);
        addOre(Tungsten);
        addOre(Chromium);
        Desh = REGISTRATE.material(CTNHCore.id("desh"))
                .cnlang("戴斯")
                .ingot()
                .fluid()
                .ore()
                .color(0xF2A057)
                .secondaryColor(0x2E2F04)
                .element(CTNHElements.Ds)
                .iconSet(METALLIC)
                .appendFlags(EXT2_METAL, GENERATE_ROTOR, GENERATE_DENSE, GENERATE_SMALL_GEAR)
                .buildAndRegister();
        Ostrum = REGISTRATE.material(CTNHCore.id("ostrum"))
                .cnlang("紫金")
                .ingot()
                .fluid()
                .ore()
                .color(0xE5939B)
                .secondaryColor(0x2F0425)
                .element(CTNHElements.Ot)
                .iconSet(METALLIC)
                .appendFlags(EXT2_METAL, GENERATE_ROTOR, GENERATE_DENSE, GENERATE_SMALL_GEAR)
                .buildAndRegister();
        Calorite = REGISTRATE.material(CTNHCore.id("calorite"))
                .cnlang("耐热金属")
                .ingot()
                .fluid()
                .ore()
                .color(0xE65757)
                .secondaryColor(0x2F0506)
                .element(CTNHElements.Ct)
                .iconSet(METALLIC)
                .appendFlags(EXT2_METAL, GENERATE_ROTOR, GENERATE_DENSE, GENERATE_SMALL_GEAR)
                .buildAndRegister();
    }
}
