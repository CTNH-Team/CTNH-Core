package io.github.cpearl0.ctnhcore.data.materials;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.OreProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHElements;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.METALLIC;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHMaterials.*;

public class AdastraMaterials {
    public static void init() {
        addOre(Neutronium, NeutroniumMixture);
        addOre(NaquadahEnriched, EnrichedNaquadahOxideMixture);
        addOre(Naquadria, NaquadriaOxideMixture);
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
        Desh = new Material.Builder(CTNHCore.id("desh"))
                .ingot()
                .fluid()
                .ore()
                .color(0xF2A057)
                .secondaryColor(0x2E2F04)
                .element(CTNHElements.Ds)
                .iconSet(METALLIC)
                .appendFlags(EXT2_METAL, GENERATE_ROTOR, GENERATE_DENSE, GENERATE_SMALL_GEAR)
                .buildAndRegister();
        Ostrum = new Material.Builder(CTNHCore.id("ostrum"))
                .ingot()
                .fluid()
                .ore()
                .color(0xE5939B)
                .secondaryColor(0x2F0425)
                .element(CTNHElements.Ot)
                .iconSet(METALLIC)
                .appendFlags(EXT2_METAL, GENERATE_ROTOR, GENERATE_DENSE, GENERATE_SMALL_GEAR)
                .buildAndRegister();
        Calorite = new Material.Builder(CTNHCore.id("calorite"))
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
