package io.github.cpearl0.ctnhcore.data.materials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import io.github.cpearl0.ctnhcore.CTNHCore;

import static com.gregtechceu.gtceu.api.GTValues.LuV;
import static com.gregtechceu.gtceu.api.GTValues.VA;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.METALLIC;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.SHINY;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class OrdinaryMaterials {
    public static Material PYRROLE;
    public static Material FENTONS_REAGENT;
    public static Material POLYPYRROLE;
    public static Material BLUE_TITANIUM_ALLOY;
    public static Material BIO_FLEXIBLE;
    public static void init() {
        PYRROLE = new Material.Builder(CTNHCore.id("pyrrole"))
                .liquid(new FluidBuilder()
                        .color(0xD4AF37)
                        .temperature(398)
                )
                .components(Carbon, 4, Hydrogen, 5, Nitrogen, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        FENTONS_REAGENT = new Material.Builder(CTNHCore.id("fentons_reagent"))
                .liquid(new FluidBuilder()
                        .color(0x7FFFD4)
                        .temperature(303)
                )
                .components(Iron2Chloride, 1, HydrogenPeroxide, 1)
                .flags(EXPLOSIVE)
                .buildAndRegister();
        POLYPYRROLE = new Material.Builder(CTNHCore.id("polypyrrole"))
                .dust()
                .ingot()
                .liquid()
                .color(0x1A1A1A)
                .iconSet(SHINY)
                .components(Carbon, 4, Hydrogen, 3, Nitrogen, 1)
                .cableProperties(128, 1, 1)
                .buildAndRegister();
        BLUE_TITANIUM_ALLOY = new Material.Builder(CTNHCore.id("blue_titanium_alloy"))
                .dust()
                .ingot(5)
                .liquid()
                .color(0x1E90FF)
                .iconSet(METALLIC)
                .components(
                        Titanium, 32,
                        Tantalum, 4,
                        Gadolinium, 1
                )
                .flags(GENERATE_FOIL,
                        GENERATE_SPRING,
                        GENERATE_FINE_WIRE
                )
                .blast(b -> b.temp(6000, BlastProperty.GasTier.HIGH)
                        .blastStats(VA[GTValues.LuV], 3000)
                        .vacuumStats(VA[LuV]))
                .cableProperties(2048, 8, 16)
                .buildAndRegister();
        BIO_FLEXIBLE = new Material.Builder(CTNHCore.id("bio_flexible"))
                .color(0x8AFF70)
                //.components(POLYPYRROLE, 1, BLUE_TITANIUM_ALLOY, 1)
                .flags(
                        NO_SMELTING,
                        GENERATE_FINE_WIRE
                )
                //.cableProperties(32768, 8, 1)
                .buildAndRegister();
    }
}
