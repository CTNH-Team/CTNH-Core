package io.github.cpearl0.ctnhcore.registry;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.worldgen.bedrockfluid.BedrockFluidDefinition;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.ctnh.ctnhastral.data.CAMaterials;

import java.util.Set;

import static com.gregtechceu.gtceu.common.data.GTBedrockFluids.create;

public class CTNHFluidVeins {

    public static void init() {}

    public static BedrockFluidDefinition SEAWATER_VEIN_OVERWORLD = create(CTNHCore.id("seawater_vein_overworld"),
            vein -> {
                vein.dimensions(Set.of(Level.OVERWORLD))
                        .fluid(() -> CAMaterials.Seawater.getFluid())
                        .weight(100)
                        .minimumYield(200)
                        .maximumYield(500)
                        .depletionAmount(10)
                        .depletionChance(1)
                        .depletedYield(100)
                        .biomes(200, Biomes.DEEP_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_FROZEN_OCEAN)
                        .biomes(150, BiomeTags.IS_OCEAN)
                        .register();
            });
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
}
