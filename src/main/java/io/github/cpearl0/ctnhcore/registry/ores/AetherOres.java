package io.github.cpearl0.ctnhcore.registry.ores;

import io.github.cpearl0.ctnhcore.registry.CTNHWorldgenLayers;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.util.valueproviders.UniformInt;

import static io.github.cpearl0.ctnhcore.registry.CTNHOres.create;
import static io.github.cpearl0.ctnhcore.registry.CTNHWorlds.THE_AETHER;

public class AetherOres {

    // ==================== Aether ====================

    public static GTOreDefinition ZANITE_VEIN_AT = create("zanite_vein_at",
            "Aether Zanite Vein",
            "天境紫晶矿脉",
            vein -> vein
                    .weight(60)
                    .clusterSize(UniformInt.of(30, 50))
                    .density(0.5f)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.AETHER)
                    .dimensions(THE_AETHER)
                    .heightRangeUniform(0, 100)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(CTNHMaterials.Zanite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(CTNHMaterials.Ambrosium))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.Skyjade))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.Zanite)));

    public static final GTOreDefinition TOPAZ_VEIN_AT = create("topaz_vein_at",
            "Aether Topaz Vein",
            "天境黄玉矿脉",
            vein -> vein
                    .weight(50)
                    .clusterSize(UniformInt.of(25, 30))
                    .density(0.5f)
                    .layer(CTNHWorldgenLayers.AETHER)
                    .dimensions(THE_AETHER)
                    .heightRangeUniform(0, 100)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Topaz).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Chalcopyrite))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Topaz)));

    public static final GTOreDefinition EMERALD_VEIN_AT = create("emerald_vein_at",
            "Aether Emerald Vein",
            "天境绿宝石矿脉",
            vein -> vein
                    .clusterSize(UniformInt.of(50, 60))
                    .density(0.5f)
                    .weight(30)
                    .layer(CTNHWorldgenLayers.AETHER)
                    .dimensions(THE_AETHER)
                    .heightRangeUniform(0, 100)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(1).mat(GTMaterials.Beryllium))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Emerald).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Thorium))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Emerald)));

    public static final GTOreDefinition OLIVINE_VEIN_AT = create("olivine_vein_at",
            "Aether Olivine Vein",
            "天境橄榄石矿脉",
            vein -> vein
                    .weight(20)
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.25f)
                    .layer(CTNHWorldgenLayers.AETHER)
                    .heightRangeUniform(-20, 10)
                    .dimensions(THE_AETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(1).mat(GTMaterials.Olivine).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Magnesite).size(2, 4))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Olivine)));

    public static final GTOreDefinition DIAMOND_VEIN_AT = create("diamond_vein_at",
            "Aether Diamond Vein",
            "天境钻石矿脉",
            vein -> vein
                    .weight(40)
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.25f)
                    .layer(CTNHWorldgenLayers.AETHER)
                    .heightRangeUniform(-60, -30)
                    .dimensions(THE_AETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Graphite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Diamond).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Coal))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Diamond)));

    public static final GTOreDefinition REDSTONE_VEIN_AT = create("redstone_vein_at",
            "Aether Redstone Vein",
            "天境红石矿脉",
            vein -> vein
                    .weight(60)
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.2f)
                    .layer(CTNHWorldgenLayers.AETHER)
                    .heightRangeUniform(-60, -10)
                    .dimensions(THE_AETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Redstone).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Ruby))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Cinnabar))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Redstone)));

    public static final GTOreDefinition SAPPHIRE_VEIN_AT = create("sapphire_vein_at",
            "Aether Sapphire Vein",
            "天境蓝宝石矿脉",
            vein -> vein
                    .weight(60)
                    .clusterSize(UniformInt.of(25, 30))
                    .density(0.25f)
                    .layer(CTNHWorldgenLayers.AETHER)
                    .heightRangeUniform(-40, 0)
                    .dimensions(THE_AETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Electrotine).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Sapphire))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Sapphire)));

    public static void init() {}
}
