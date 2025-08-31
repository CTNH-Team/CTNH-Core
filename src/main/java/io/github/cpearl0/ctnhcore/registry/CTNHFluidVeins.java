package io.github.cpearl0.ctnhcore.registry;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.gregtechceu.gtceu.api.data.worldgen.bedrockfluid.BedrockFluidDefinition;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import earth.terrarium.adastra.api.planets.Planet;
import io.github.cpearl0.ctnhcore.CTNHCore;

import java.util.Set;

import static com.gregtechceu.gtceu.common.data.GTBedrockFluids.create;
public class CTNHFluidVeins {
    public static void init() {

    }
    public static BedrockFluidDefinition FLUORINE_VEIN_AETHER = create(CTNHCore.id("fluorine_vein"), vein -> {
        vein.dimensions(Set.of(AetherDimensions.AETHER_LEVEL))
        .fluid(() -> GTMaterials.Fluorine.getFluid())
        .weight(150)
        .minimumYield(120)
        .maximumYield(600)
        .depletionAmount(2)
        .depletionChance(1)
        .depletedYield(50)
        .register();
    });
    public static BedrockFluidDefinition OXYGEN_VEIN_AETHER = create(CTNHCore.id("oxygen_vein"), vein -> {
        vein.dimensions(Set.of(AetherDimensions.AETHER_LEVEL))
        .fluid(() -> GTMaterials.Oxygen.getFluid())
        .weight(200)
        .minimumYield(120)
        .maximumYield(720)
        .depletionAmount(2)
        .depletionChance(1)
        .depletedYield(50)
                .register();
    });
    public static BedrockFluidDefinition NITROGEN_VEIN_AETHER = create(CTNHCore.id("nitrogen_vein"), vein -> {
        vein.dimensions(Set.of(AetherDimensions.AETHER_LEVEL))
        .fluid(() -> GTMaterials.Nitrogen.getFluid())
        .weight(800)
        .minimumYield(120)
        .maximumYield(900)
        .depletionAmount(2)
        .depletionChance(1)
        .depletedYield(50)
                .register();
    });
    public static BedrockFluidDefinition HYDROGEN_VEIN_AETHER = create(CTNHCore.id("hydrogen_vein"), vein -> {
        vein.dimensions(Set.of(AetherDimensions.AETHER_LEVEL))
        .fluid(() -> GTMaterials.Hydrogen.getFluid())
        .weight(100)
        .minimumYield(120)
        .maximumYield(600)
        .depletionAmount(2)
        .depletionChance(1)
        .depletedYield(50)
                .register();
    });
    public static BedrockFluidDefinition MYSTERY_VEIN_MARS = create(CTNHCore.id("mars_mystery_fluid_vein"), vein ->{
        vein.dimensions(Set.of(Planet.MARS))
        .fluid(() -> CTNHMaterials.MysteryFluid.getFluid())
        .weight(100)
        .minimumYield(120)
        .maximumYield(600)
        .depletionAmount(2)
        .depletionChance(1)
        .depletedYield(50)
                .register();
    });
    public static BedrockFluidDefinition WATER_VEIN_MARS = create(CTNHCore.id("mars_mystery_fluid_vein"), vein ->{
        vein.dimensions(Set.of(Planet.MARS))
        .fluid(() -> GTMaterials.DistilledWater.getFluid())
        .weight(800)
        .minimumYield(120)
        .maximumYield(600)
        .depletionAmount(2)
        .depletionChance(1)
        .depletedYield(50)
                .register();
    });
    public static BedrockFluidDefinition HYDROCHLORIC_ACID_VEIN_MARS = create(CTNHCore.id("mars_hydrochloric_acid_vein"), vein -> {
        vein.dimensions(Set.of(Planet.MARS))
        .fluid(() -> GTMaterials.HydrochloricAcid.getFluid())
        .weight(200)
        .minimumYield(100)
        .maximumYield(500)
        .depletionAmount(2)
        .depletionChance(1)
        .depletedYield(50)
                .register();
    });
    public static BedrockFluidDefinition IRON_LIQUID_VEIN_MERCURY = create(CTNHCore.id("mercury_iron_liquid_vein"), vein -> {
        vein.dimensions(Set.of(Planet.MERCURY))
        .fluid(() -> GTMaterials.Iron.getFluid())
        .weight(300)
        .minimumYield(200)
        .maximumYield(800)
        .depletionAmount(2)
        .depletionChance(1)
        .depletedYield(100)
                .register();
    });
    public static BedrockFluidDefinition NICKEL_LIQUID_VEIN_MERCURY = create(CTNHCore.id("mercury_nickel_liquid_vein"), vein -> {
        vein.dimensions(Set.of(Planet.MERCURY))
        .fluid(() -> GTMaterials.Nickel.getFluid())
        .weight(200)
        .minimumYield(200)
        .maximumYield(800)
        .depletionAmount(2)
        .depletionChance(1)
        .depletedYield(100)
                .register();
    });
    public static BedrockFluidDefinition SULFUR_LIQUID_VEIN_MERCURY = create(CTNHCore.id("mercury_sulfur_liquid_vein"), vein -> {
        vein.dimensions(Set.of(Planet.MERCURY))
        .fluid(() -> GTMaterials.Sulfur.getFluid())
        .weight(300)
        .minimumYield(200)
        .maximumYield(800)
        .depletionAmount(2)
        .depletionChance(1)
        .depletedYield(100)
                .register();
    });
    public static BedrockFluidDefinition HELIUM3_VEIN_MOON = create(CTNHCore.id("moon_helium3_vein"), vein -> {
        vein.dimensions(Set.of(Planet.MOON))
        .fluid(() -> GTMaterials.Helium3.getFluid())
        .weight(100)
        .minimumYield(100)
        .maximumYield(500)
        .depletionAmount(2)
        .depletionChance(1)
        .depletedYield(20)
                .register();
    });
    public static BedrockFluidDefinition SALTWATER_VEIN_MOON = create(CTNHCore.id("moon_saltwater_vein"), vein -> {
        vein.dimensions(Set.of(Planet.MOON))
        .fluid(() -> GTMaterials.SaltWater.getFluid())
        .weight(800)
        .minimumYield(200)
        .maximumYield(800)
        .depletionAmount(2)
        .depletionChance(1)
        .depletedYield(100)
                .register();
    });
    public static BedrockFluidDefinition SULFURIC_ACID_VEIN_VENUS = create(CTNHCore.id("venus_sulfuric_acid_vein"), vein -> {
        vein.dimensions(Set.of(Planet.VENUS))
        .fluid(() -> GTMaterials.SulfuricAcid.getFluid())
        .weight(300)
        .minimumYield(150)
        .maximumYield(500)
        .depletionAmount(2)
        .depletionChance(1)
        .depletedYield(50)
                .register();
    });
    public static BedrockFluidDefinition LEAD_LIQUID_VEIN_VENUS = create(CTNHCore.id("venus_lead_liquid_vein"), vein -> {
        vein.dimensions(Set.of(Planet.VENUS))
        .fluid(() -> GTMaterials.Lead.getFluid())
        .weight(100)
        .minimumYield(150)
        .maximumYield(500)
        .depletionAmount(2)
        .depletionChance(1)
        .depletedYield(50)
                .register();
    });
    public static BedrockFluidDefinition CRYOTHEUM_VEIN_GLACIO = create(CTNHCore.id("glacio_cryotheum_vein"), vein -> {
        vein.dimensions(Set.of(Planet.GLACIO))
        .fluid(() -> CTNHMaterials.Cryotheum.getFluid())
        .weight(80)
        .minimumYield(150)
        .maximumYield(500)
        .depletionAmount(2)
        .depletionChance(1)
        .depletedYield(50)
                .register();
    });
    public static BedrockFluidDefinition DISTILLED_WATER_VEIN_GLACIO = create(CTNHCore.id("glacio_distilled_water_vein"), vein -> {
        vein.dimensions(Set.of(Planet.GLACIO))
        .fluid(() -> GTMaterials.DistilledWater.getFluid())
        .weight(800)
        .minimumYield(200)
        .maximumYield(800)
        .depletionAmount(2)
        .depletionChance(1)
        .depletedYield(100)
                .register();
    });
}
