package io.github.cpearl0.ctnhcore.registry.ores;

import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.tags.BiomeTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;

import static com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE;
import static io.github.cpearl0.ctnhcore.registry.CTNHOres.create;

public class OverworldOres {

    // ==================== Overworld ====================

    public static GTOreDefinition NETHER_QUARTZ_VEIN_OW = create("nether_quartz_vein_ow",
            "Overworld Quartz Vein",
            "主世界石英矿脉",
            vein -> vein
                    .weight(80)
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.25F)
                    .discardChanceOnAirExposure(0)
                    .layer(WorldGenLayers.STONE)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .heightRangeUniform(20, 60)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.NetherQuartz).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Quartzite))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.NetherQuartz)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));

    public static GTOreDefinition ZINC_VEIN_OW = create("zinc_vein_ow",
            "Overworld Zinc Vein",
            "主世界锌矿脉",
            vein -> vein
                    .weight(60)
                    .clusterSize(40)
                    .density(0.25F)
                    .discardChanceOnAirExposure(0)
                    .layer(WorldGenLayers.STONE)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .heightRangeUniform(20, 50)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Sphalerite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Copper))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Pyrite))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Copper)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));

    public static GTOreDefinition PRECIOUS_ALLOY_VEIN_OW = create("precious_alloy_vein_ow",
            "Overworld Precious Alloy Vein",
            "主世界贵金属矿脉",
            vein -> vein
                    .weight(30)
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.25F)
                    .discardChanceOnAirExposure(0)
                    .layer(WorldGenLayers.STONE)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .heightRangeUniform(-10, 30)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(CTNHMaterials.PreciousAlloy).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Silver).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Copper).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Tin))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.PreciousAlloy)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));

    public static final GTOreDefinition KAOLINITE_VEIN_OW = create("kaolinite_vein_ow",
            "Overworld Kaolinite Vein",
            "主世界高岭石矿脉",
            vein -> vein
                    .weight(60)
                    .clusterSize(UniformInt.of(25, 30))
                    .density(0.4f)
                    .layer(WorldGenLayers.STONE)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .heightRangeUniform(30, 70)
                    .discardChanceOnAirExposure(0f)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.Kaolinite).size(2, 4))
                                    .layer(l -> l.weight(1).block(() -> Blocks.CLAY).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Gypsum).size(2, 4)))));

    public static final GTOreDefinition APATITE_VEIN_OW = create("apatite_vein_ow",
            "Overworld Apatite Vein",
            "主世界磷灰石矿脉",
            vein -> vein
                    .weight(40)
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.25f)
                    .layer(WorldGenLayers.STONE)
                    .heightRangeUniform(10, 80)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(1).mat(GTMaterials.Apatite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Pyrochlore).size(2, 4))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Apatite)
                            .placement(ABOVE)));

    public static final GTOreDefinition CASSITERITE_VEIN_OW = create("cassiterite_vein_ow",
            "Overworld Cassiterite Vein",
            "主世界锡石矿脉",
            vein -> vein
                    .weight(80)
                    .clusterSize(UniformInt.of(40, 50))
                    .density(1.0f)
                    .layer(WorldGenLayers.STONE)
                    .heightRangeUniform(10, 80)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(1).mat(GTMaterials.Tin))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Cassiterite).size(2, 4))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Cassiterite)));

    public static final GTOreDefinition COAL_VEIN_OW = create("coal_vein_ow",
            "Overworld Coal Vein",
            "主世界煤炭矿脉",
            vein -> vein
                    .weight(80)
                    .clusterSize(UniformInt.of(40, 50))
                    .density(0.25f)
                    .layer(WorldGenLayers.STONE)
                    .heightRangeUniform(10, 140)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(1).mat(GTMaterials.Coal).size(2, 4))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Coal)));

    public static final GTOreDefinition COPPER_TIN_VEIN_OW = create("copper_tin_vein_ow",
            "Overworld Copper Tin Vein",
            "主世界铜锡矿脉",
            vein -> vein
                    .weight(50)
                    .clusterSize(UniformInt.of(40, 50))
                    .density(1.0f)
                    .layer(WorldGenLayers.STONE)
                    .heightRangeUniform(-10, 160)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Chalcopyrite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Cassiterite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Realgar))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Chalcopyrite)));

    public static final GTOreDefinition IRON_VEIN_OW = create("iron_vein_ow",
            "Overworld Iron Vein",
            "主世界铁矿脉",
            vein -> vein
                    .weight(120)
                    .clusterSize(UniformInt.of(40, 52))
                    .density(1.0f)
                    .layer(WorldGenLayers.STONE)
                    .heightRangeUniform(-10, 60)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(1).mat(GTMaterials.Iron))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Hematite).size(2, 4))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Hematite)));

    public static final GTOreDefinition MAGNETITE_VEIN_OW = create("magnetite_vein_ow",
            "Overworld Magnetite Vein",
            "主世界磁铁矿脉",
            vein -> vein
                    .weight(80)
                    .clusterSize(UniformInt.of(40, 50))
                    .density(0.15f)
                    .layer(WorldGenLayers.STONE)
                    .heightRangeUniform(10, 60)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Magnetite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.VanadiumMagnetite))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Magnetite)));

    public static final GTOreDefinition SALTS_VEIN_OW = create("salts_vein_ow",
            "Overworld Salts Vein",
            "主世界盐矿脉",
            vein -> vein
                    .weight(50)
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.2f)
                    .layer(WorldGenLayers.STONE)
                    .heightRangeUniform(30, 70)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.RockSalt).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Salt).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Lepidolite))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Spodumene))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Salt)));

    public static final GTOreDefinition OILSANDS_VEIN_OW = create("oilsands_vein_ow",
            "Overworld Oilsands Vein",
            "主世界油砂矿脉",
            vein -> vein
                    .weight(40)
                    .clusterSize(UniformInt.of(25, 30))
                    .density(0.3f)
                    .layer(WorldGenLayers.STONE)
                    .heightRangeUniform(30, 80)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(1).mat(GTMaterials.Oilsands).size(2, 4))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Oilsands)));

    public static final GTOreDefinition COPPER_VEIN_OW = create("copper_vein_ow",
            "Overworld Copper Vein",
            "主世界铜矿脉",
            vein -> vein
                    .weight(80)
                    .clusterSize(UniformInt.of(40, 50))
                    .density(1.0f)
                    .layer(WorldGenLayers.DEEPSLATE)
                    .heightRangeUniform(-40, 10)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(1).mat(GTMaterials.Copper))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Chalcopyrite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Pyrite))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Copper)));

    public static final GTOreDefinition DIAMOND_VEIN_OW = create("diamond_vein_ow",
            "Overworld Diamond Vein",
            "主世界钻石矿脉",
            vein -> vein
                    .weight(40)
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.25f)
                    .layer(WorldGenLayers.DEEPSLATE)
                    .heightRangeUniform(-60, -30)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(7).mat(GTMaterials.Graphite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Diamond))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Coal))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Diamond)
                            .density(0.1f)
                            .placement(ABOVE)
                            .radius(2)));

    public static final GTOreDefinition LAPIS_VEIN_OW = create("lapis_vein_ow",
            "Overworld Lapis Vein",
            "主世界青金石矿脉",
            vein -> vein
                    .weight(40)
                    .clusterSize(UniformInt.of(40, 50))
                    .density(0.75f)
                    .layer(WorldGenLayers.DEEPSLATE)
                    .heightRangeUniform(-60, 10)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Lapis).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Calcite))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Lapis)
                            .density(0.15f)
                            .placement(ABOVE)
                            .radius(3)));

    public static final GTOreDefinition MANGANESE_VEIN_OW = create("manganese_vein_ow",
            "Overworld Manganese Vein",
            "主世界锰矿脉",
            vein -> vein
                    .weight(20)
                    .clusterSize(UniformInt.of(50, 60))
                    .density(0.75f)
                    .layer(WorldGenLayers.DEEPSLATE)
                    .heightRangeUniform(-30, 0)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Pyrolusite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Tantalite))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Pyrolusite)
                            .density(0.15f)
                            .radius(3)));

    public static final GTOreDefinition OLIVINE_VEIN_OW = create("olivine_vein_ow",
            "Overworld Olivine Vein",
            "主世界橄榄石矿脉",
            vein -> vein
                    .weight(20)
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.25f)
                    .layer(WorldGenLayers.DEEPSLATE)
                    .heightRangeUniform(-20, 10)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(1).mat(GTMaterials.Olivine).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Magnesite).size(2, 4))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Olivine)
                            .density(0.15f)
                            .radius(3)));

    public static final GTOreDefinition REDSTONE_VEIN_OW = create("redstone_vein_ow",
            "Overworld Redstone Vein",
            "主世界红石矿脉",
            vein -> vein
                    .weight(60)
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.2f)
                    .layer(WorldGenLayers.DEEPSLATE)
                    .heightRangeUniform(-60, -10)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Redstone).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Ruby))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Cinnabar))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Redstone)));

    public static final GTOreDefinition SAPPHIRE_VEIN_OW = create("sapphire_vein_ow",
            "Overworld Sapphire Vein",
            "主世界蓝宝石矿脉",
            vein -> vein
                    .weight(60)
                    .clusterSize(UniformInt.of(25, 30))
                    .density(0.25f)
                    .layer(WorldGenLayers.DEEPSLATE)
                    .heightRangeUniform(-40, 0)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Electrotine).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Sapphire))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Sapphire)
                            .density(0.15f)
                            .placement(ABOVE)
                            .radius(3)));

    public static void init() {}
}
