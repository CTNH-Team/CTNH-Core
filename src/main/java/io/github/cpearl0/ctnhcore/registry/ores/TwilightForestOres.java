package io.github.cpearl0.ctnhcore.registry.ores;

import io.github.cpearl0.ctnhcore.registry.CTNHWorldgenLayers;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import twilightforest.data.tags.BiomeTagGenerator;

import static io.github.cpearl0.ctnhcore.registry.CTNHOres.create;
import static io.github.cpearl0.ctnhcore.registry.CTNHWorlds.TWILIGHT_FOREST;

public class TwilightForestOres {

    // ==================== TwilightForest ====================

    public static GTOreDefinition CRYOLITE_VEIN_TF = create("cryolite_vein_tf",
            "Twilight Forest Cryolite Vein",
            "暮色森林冰晶石矿脉",
            vein -> vein
                    .weight(70)
                    .clusterSize(40)
                    .density(0.25F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.TWILIGHT)
                    .dimensions(TWILIGHT_FOREST)
                    .heightRangeUniform(-30, 0)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(CTNHMaterials.Cryolite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Mica))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Silver))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Lead))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.Cryolite)));

    public static GTOreDefinition STEEL_LEAF_VEINN_TF = create("steel_leaf_vein_tf",
            "Twilight Forest Steel Leaf Vein",
            "暮色森林钢叶矿脉",
            vein -> vein
                    .weight(20)
                    .clusterSize(40)
                    .density(0.35F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.TWILIGHT)
                    .dimensions(TWILIGHT_FOREST)
                    .biomes(BiomeTagGenerator.VALID_NAGA_COURTYARD_BIOMES)
                    .heightRangeUniform(-30, 20)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    // .layer(l -> l.weight(2).mat(CTNHMaterials.SteelLeaf).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Apatite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Pyrochlore).size(2, 4))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Apatite)));

    public static GTOreDefinition LICH_BONE_VEIN_TF = create("lich_bone_vein_tf",
            "Twilight Forest Lich Bone Vein",
            "暮色森林巫师之骨矿脉",
            vein -> vein
                    .weight(20)
                    .clusterSize(45)
                    .density(0.35F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.TWILIGHT)
                    .dimensions(TWILIGHT_FOREST)
                    .biomes(BiomeTagGenerator.VALID_LICH_TOWER_BIOMES)
                    .heightRangeUniform(-30, 20)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Apatite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Coal).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Graphite))))
                    // .layer(l -> l.weight(1).mat(CTNHMaterials.SpiritAsh).size(1, 1))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Apatite)));

    public static GTOreDefinition TOXIC_SWAMP_AMBER_VEIN_TF = create("toxic_swamp_amber_vein_tf",
            "Twilight Forest Toxic Swamp Amber Vein",
            "暮色森林毒沼琥珀矿脉",
            vein -> vein.weight(50)
                    .clusterSize(35)
                    .density(0.45F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.TWILIGHT)
                    .dimensions(TWILIGHT_FOREST)
                    .biomes(BiomeTagGenerator.VALID_LABYRINTH_BIOMES)
                    .heightRangeUniform(-30, 20)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(GTMaterials.Cinnabar).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Galena))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Saltpeter))))
                    // .layer(l -> l.weight(1).mat(CTNHMaterials.ToxicSwampAmber).size(1, 1))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Cinnabar)));

    public static GTOreDefinition ILLUSION_IRON_VEIN_TF = create("illusion_iron_vein_tf",
            "Twilight Forest Illusion Iron Vein",
            "暮色森林幻铁矿脉",
            vein -> vein
                    .weight(50)
                    .clusterSize(25)
                    .density(0.45F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.TWILIGHT)
                    .dimensions(TWILIGHT_FOREST)
                    .biomes(BiomeTagGenerator.VALID_KNIGHT_STRONGHOLD_BIOMES)
                    .heightRangeUniform(-30, 20)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Pyrite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.VanadiumMagnetite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Tantalite).size(2, 4))))
                    // .layer(l -> l.weight(1).mat(CTNHMaterials.IllusionIron).size(1, 1))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.VanadiumMagnetite)));

    public static GTOreDefinition ARCTIC_CRYSTAL_CORE_VEIN_TF = create("arctic_crystal_core_vein_tf",
            "Twilight Forest Arctic Crystal Core Vein",
            "暮色森林极寒冰核矿脉",
            vein -> vein
                    .weight(50)
                    .clusterSize(25)
                    .density(0.45F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.TWILIGHT)
                    .dimensions(TWILIGHT_FOREST)
                    .biomes(BiomeTagGenerator.VALID_AURORA_PALACE_BIOMES)
                    .heightRangeUniform(-30, 20)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Electrotine).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Lapis))))
                    // .layer(l -> l.weight(1).mat(CTNHMaterials.PolarIceCore).size(1, 1))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Lapis)));

    public static GTOreDefinition DRAGONFLAME_VEIN_TF = create("dragonflame_vein_tf",
            "Twilight Forest Dragonflame Vein",
            "暮色森林龙焰矿脉",
            vein -> vein
                    .weight(50)
                    .clusterSize(35)
                    .density(0.55F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.TWILIGHT)
                    .dimensions(TWILIGHT_FOREST)
                    .biomes(BiomeTagGenerator.VALID_HYDRA_LAIR_BIOMES)
                    .heightRangeUniform(-30, 20)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Hematite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Ruby))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Pyrochlore))))
                    // .layer(l -> l.weight(1).mat(CTNHMaterials.Dragonflame).size(1, 1))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Ruby)));

    public static GTOreDefinition ECLIPSE_SHADOW_VEIN_TF = create("eclipse_shadow_vein_tf",
            "Twilight Forest Eclipse Shadow Vein",
            "暮色森林幽影矿脉",
            vein -> vein
                    .weight(50)
                    .clusterSize(45)
                    .density(0.25F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.TWILIGHT)
                    .dimensions(TWILIGHT_FOREST)
                    .biomes(BiomeTagGenerator.VALID_DARK_TOWER_BIOMES)
                    .heightRangeUniform(-30, 20)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(GTMaterials.Stibnite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Antimony))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Silver))))
                    // .layer(l -> l.weight(1).mat(CTNHMaterials.EclipseShadow).size(1, 1))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Stibnite)));

    public static GTOreDefinition LIGHTNING_VEIN_VEIN_TF = create("thunderstrike_vein_tf",
            "Twilight Forest Thunderstrike Vein",
            "暮色森林雷纹矿脉",
            vein -> vein
                    .weight(50)
                    .clusterSize(65)
                    .density(0.65F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.TWILIGHT)
                    .dimensions(TWILIGHT_FOREST)
                    .biomes(BiomeTagGenerator.VALID_TROLL_CAVE_BIOMES)
                    .heightRangeUniform(-30, 20)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Gold).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Diamond).size(2, 4))))
                    // .layer(l -> l.weight(1).mat(CTNHMaterials.EclipseShadow).size(1, 1))
                    // .layer(l -> l.weight(1).mat(CTNHMaterials.LightningPattern).size(1, 1))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Diamond)));

    public static GTOreDefinition ARSENIC_VEIN_TF = create("arsenic_vein_tf",
            "Twilight Forest Red Arsenic Nickel Vein",
            "暮色森林红砷镍矿脉",
            vein -> vein
                    .weight(20)
                    .clusterSize(40)
                    .density(0.25F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.TWILIGHT)
                    .dimensions(TWILIGHT_FOREST)
                    .heightRangeUniform(-30, 0)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(CTNHMaterials.Nickeline).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.ArsenicTrioxide))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Pentlandite))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Realgar))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.Nickeline)));

    public static final GTOreDefinition GALENA_VEIN_TF = create("galena_vein_tf",
            "Twilight Forest Galena Vein",
            "暮色森林方铅矿脉",
            vein -> vein
                    .clusterSize(30)
                    .weight(40)
                    .layer(CTNHWorldgenLayers.TWILIGHT)
                    .density(0.25f)
                    .dimensions(TWILIGHT_FOREST)
                    .heightRangeUniform(-30, 0).discardChanceOnAirExposure(0f)
                    .layeredVeinGenerator(generator -> generator.buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Galena).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Silver))
                            .layer(l -> l.weight(1).mat(GTMaterials.Lead))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Galena)));

    public static final GTOreDefinition DIAMOND_VEIN_TF = create("diamond_vein_tf",
            "Twilight Forest Diamond Vein",
            "暮色森林钻石矿脉",
            vein -> vein
                    .clusterSize(30)
                    .weight(40)
                    .layer(CTNHWorldgenLayers.TWILIGHT)
                    .density(0.25f)
                    .dimensions(TWILIGHT_FOREST)
                    .heightRangeUniform(-30, 0).discardChanceOnAirExposure(0f)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(GTMaterials.Graphite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Diamond))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Coal))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Diamond)));

    public static final GTOreDefinition APATITE_VEIN_TF = create("apatite_vein_tf",
            "Twilight Forest Apatite Vein",
            "暮色森林磷灰石矿脉",
            vein -> vein
                    .clusterSize(30)
                    .weight(40)
                    .layer(CTNHWorldgenLayers.TWILIGHT)
                    .density(0.25f)
                    .dimensions(TWILIGHT_FOREST)
                    .heightRangeUniform(-30, 0).discardChanceOnAirExposure(0f)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(1).mat(GTMaterials.Apatite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Pyrochlore).size(2, 4))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Apatite)));

    public static final GTOreDefinition SALTS_VEIN_TF = create("salts_vein_tf",
            "Twilight Forest Salts Vein",
            "暮色森林盐矿脉",
            vein -> vein
                    .clusterSize(30)
                    .weight(50)
                    .layer(CTNHWorldgenLayers.TWILIGHT)
                    .density(0.2f)
                    .dimensions(TWILIGHT_FOREST)
                    .heightRangeUniform(-30, 0).discardChanceOnAirExposure(0f)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.RockSalt).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Salt).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Lepidolite))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Spodumene))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Salt)));

    public static void init() {}
}
