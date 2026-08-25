package io.github.cpearl0.ctnhcore.registry.ores;

import io.github.cpearl0.ctnhcore.registry.CTNHTags;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.worldgen.BiomeWeightModifier;
import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
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
                    .clusterSize(UniformInt.of(45, 60))
                    .density(1.0F)
                    .discardChanceOnAirExposure(0)
                    .layer(WorldGenLayers.STONE)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .heightRangeUniform(20, 60)
                    .veinedVeinGenerator(generator -> generator
                            .oreBlock(GTMaterials.NetherQuartz, 2)
                            .oreBlock(GTMaterials.Quartzite, 1)
                            .minRichness(0.6f)
                            .maxRichness(1.0f)
                            .maxRichnessThreshold(1.0f))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.NetherQuartz)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static GTOreDefinition ZINC_VEIN_OW = create("zinc_vein_ow",
            "Overworld Zinc Vein",
            "主世界锌矿脉",
            vein -> vein
                    .weight(60)
                    .clusterSize(UniformInt.of(55, 70))
                    .density(1.0F)
                    .discardChanceOnAirExposure(0)
                    .layer(WorldGenLayers.STONE)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .heightRangeUniform(20, 50)
                    .veinedVeinGenerator(generator -> generator
                            .oreBlock(GTMaterials.Sphalerite, 2)
                            .oreBlock(GTMaterials.Copper, 1)
                            .oreBlock(GTMaterials.Pyrite, 1)
                            .minRichness(0.6f)
                            .maxRichness(1.0f)
                            .maxRichnessThreshold(1.0f))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Copper)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static GTOreDefinition PRECIOUS_ALLOY_VEIN_OW = create("precious_alloy_vein_ow",
            "Overworld Precious Alloy Vein",
            "主世界贵金属矿脉",
            vein -> vein
                    .weight(30)
                    .clusterSize(UniformInt.of(45, 60))
                    .density(1.0f)
                    .discardChanceOnAirExposure(0)
                    .layer(WorldGenLayers.STONE)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .heightRangeUniform(-10, 30)
                    .veinedVeinGenerator(generator -> generator
                            .oreBlock(CTNHMaterials.PreciousAlloy, 2)
                            .oreBlock(GTMaterials.Silver, 2)
                            .oreBlock(GTMaterials.Copper, 2)
                            .oreBlock(GTMaterials.Tin, 1)
                            .minRichness(0.6f)
                            .maxRichness(1.0f)
                            .maxRichnessThreshold(1.0f))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.PreciousAlloy)
                            .placement(ABOVE)
                            .density(0.4f)
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
                                    .layer(l -> l.weight(1).mat(GTMaterials.Gypsum).size(2, 4)))
                            .withLayerAxis(Direction.Axis.Y))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.Kaolinite)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static final GTOreDefinition APATITE_VEIN_OW = create("apatite_vein_ow",
            "Overworld Apatite Vein",
            "主世界磷灰石矿脉",
            vein -> vein
                    .weight(40)
                    .clusterSize(UniformInt.of(90, 110))
                    .density(0.6f)
                    .layer(WorldGenLayers.STONE)
                    .heightRangeUniform(10, 80)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .dikeVeinGenerator(generator -> generator
                            .withBlock(GTMaterials.Apatite, 3, 10, 80)
                            .withBlock(GTMaterials.Pyrochlore, 2, 10, 55))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Apatite)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static final GTOreDefinition CASSITERITE_VEIN_OW = create("cassiterite_vein_ow",
            "Overworld Cassiterite Vein",
            "主世界锡石矿脉",
            vein -> vein
                    .weight(80)
                    .clusterSize(UniformInt.of(60, 80))
                    .density(1.0f)
                    .layer(WorldGenLayers.STONE)
                    .heightRangeUniform(10, 80)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .veinedVeinGenerator(generator -> generator
                            .oreBlock(GTMaterials.Tin, 1)
                            .oreBlock(GTMaterials.Cassiterite, 2)
                            .minRichness(0.6f)
                            .maxRichness(1.0f)
                            .maxRichnessThreshold(1.0f))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Cassiterite)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static final GTOreDefinition COAL_VEIN_OW = create("coal_vein_ow",
            "Overworld Coal Vein",
            "主世界煤炭矿脉",
            vein -> vein
                    .weight(60)
                    .clusterSize(UniformInt.of(40, 50))
                    .density(0.3f)
                    .layer(WorldGenLayers.STONE)
                    .heightRangeUniform(10, 140)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .biomeWeightModifier(new BiomeWeightModifier(
                            () -> GTRegistries.builtinRegistry().lookupOrThrow(Registries.BIOME)
                                    .getOrThrow(CTNHTags.HAS_OILSAND),
                            60))
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(1).mat(GTMaterials.Coal).size(2, 4)))
                            .withLayerAxis(Direction.Axis.Y))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Coal)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static final GTOreDefinition COPPER_TIN_VEIN_OW = create("copper_tin_vein_ow",
            "Overworld Copper Tin Vein",
            "主世界铜锡矿脉",
            vein -> vein
                    .weight(50)
                    .clusterSize(UniformInt.of(60, 80))
                    .density(1.0f)
                    .layer(WorldGenLayers.STONE)
                    .heightRangeUniform(-10, 160)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .veinedVeinGenerator(generator -> generator
                            .oreBlock(GTMaterials.Chalcopyrite, 2)
                            .oreBlock(GTMaterials.Cassiterite, 2)
                            .oreBlock(GTMaterials.Realgar, 1)
                            .minRichness(0.6f)
                            .maxRichness(1.0f)
                            .maxRichnessThreshold(1.0f))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Chalcopyrite)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static final GTOreDefinition IRON_VEIN_OW = create("iron_vein_ow",
            "Overworld Iron Vein",
            "主世界铁矿脉",
            vein -> vein
                    .weight(120)
                    .clusterSize(UniformInt.of(40, 50))
                    .density(0.6f)
                    .layer(WorldGenLayers.STONE)
                    .heightRangeUniform(-10, 60)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .discardChanceOnAirExposure(0.5f)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(1).mat(GTMaterials.Iron).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Hematite).size(2, 4))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Hematite)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static final GTOreDefinition MAGNETITE_VEIN_OW = create("magnetite_vein_ow",
            "Overworld Magnetite Vein",
            "主世界磁铁矿脉",
            vein -> vein
                    .weight(60)
                    .clusterSize(UniformInt.of(40, 50))
                    .density(0.3f)
                    .layer(WorldGenLayers.STONE)
                    .heightRangeUniform(10, 60)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Magnetite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.VanadiumMagnetite).size(2, 4))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Magnetite)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static final GTOreDefinition SALTS_VEIN_OW = create("salts_vein_ow",
            "Overworld Salts Vein",
            "主世界盐矿脉",
            vein -> vein
                    .weight(50)
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.3f)
                    .layer(WorldGenLayers.STONE)
                    .heightRangeUniform(30, 70)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .biomeWeightModifier(new BiomeWeightModifier(
                            () -> GTRegistries.builtinRegistry().lookupOrThrow(Registries.BIOME)
                                    .getOrThrow(BiomeTags.IS_OCEAN),
                            -50))
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.RockSalt).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Salt).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Lepidolite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Spodumene).size(2, 4)))
                            .withLayerAxis(Direction.Axis.Y))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Salt)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static final GTOreDefinition OILSANDS_VEIN_OW = create("oilsands_vein_ow",
            "Overworld Oilsands Vein",
            "主世界油砂矿脉",
            vein -> vein
                    .weight(60)
                    .clusterSize(UniformInt.of(25, 30))
                    .density(0.3f)
                    .layer(WorldGenLayers.STONE)
                    .heightRangeUniform(30, 80)
                    .biomes(CTNHTags.HAS_OILSAND)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(1).mat(GTMaterials.Oilsands).size(2, 4)))
                            .withLayerAxis(Direction.Axis.Y))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Oilsands)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static final GTOreDefinition COMBUSTIBLE_ICE_VEIN_OW = create("combustible_ice_vein_ow",
            "Overworld Combustible Ice Vein",
            "主世界可燃冰矿脉",
            vein -> vein
                    .weight(40)
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.3f)
                    .layer(WorldGenLayers.STONE)
                    .heightRangeUniform(0, 30)
                    .biomes(BiomeTags.IS_OCEAN)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(CTNHMaterials.CombustibleIce).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Graphite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Coal).size(2, 4)))
                            .withLayerAxis(Direction.Axis.Y))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.CombustibleIce)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static final GTOreDefinition COPPER_VEIN_OW = create("copper_vein_ow",
            "Overworld Copper Vein",
            "主世界铜矿脉",
            vein -> vein
                    .weight(60)
                    .clusterSize(UniformInt.of(40, 50))
                    .density(0.6f)
                    .layer(WorldGenLayers.DEEPSLATE)
                    .heightRangeUniform(-40, 10)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .discardChanceOnAirExposure(0.5f)
                    .classicVeinGenerator(generator -> generator
                            .primary(layer -> layer.mat(GTMaterials.Chalcopyrite))
                            .secondary(layer -> layer.mat(GTMaterials.Copper))
                            .between(layer -> layer.mat(GTMaterials.Pyrite))
                            .sporadic(layer -> layer.mat(GTMaterials.Pyrite))
                            .yRadius(12))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Copper)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static final GTOreDefinition DIAMOND_VEIN_OW = create("diamond_vein_ow",
            "Overworld Diamond Vein",
            "主世界钻石矿脉",
            vein -> vein
                    .weight(40)
                    .clusterSize(UniformInt.of(50, 60))
                    .density(0.6f)
                    .layer(WorldGenLayers.DEEPSLATE)
                    .heightRangeUniform(-60, -30)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .dikeVeinGenerator(generator -> generator
                            .withBlock(GTMaterials.Graphite, 3, -60, -34)
                            .withBlock(GTMaterials.Diamond, 2, -60, -46)
                            .withBlock(GTMaterials.Coal, 1, -40, -30))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Diamond)
                            .density(0.4f)
                            .placement(ABOVE)
                            .radius(2)));

    public static final GTOreDefinition LAPIS_VEIN_OW = create("lapis_vein_ow",
            "Overworld Lapis Vein",
            "主世界青金石矿脉",
            vein -> vein
                    .weight(40)
                    .clusterSize(UniformInt.of(60, 80))
                    .density(1.0f)
                    .layer(WorldGenLayers.DEEPSLATE)
                    .heightRangeUniform(-60, 10)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .veinedVeinGenerator(generator -> generator
                            .oreBlock(GTMaterials.Lapis, 2)
                            .oreBlock(GTMaterials.Calcite, 1)
                            .minRichness(0.6f)
                            .maxRichness(1.0f)
                            .maxRichnessThreshold(1.0f))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Lapis)
                            .density(0.4f)
                            .placement(ABOVE)
                            .radius(3)));

    public static final GTOreDefinition MANGANESE_VEIN_OW = create("manganese_vein_ow",
            "Overworld Manganese Vein",
            "主世界锰矿脉",
            vein -> vein
                    .weight(20)
                    .clusterSize(UniformInt.of(50, 60))
                    .density(0.6f)
                    .layer(WorldGenLayers.DEEPSLATE)
                    .heightRangeUniform(-30, 0)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Pyrolusite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Tantalite).size(2, 4))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Pyrolusite)
                            .density(0.4f)
                            .radius(3)));

    public static final GTOreDefinition OLIVINE_VEIN_OW = create("olivine_vein_ow",
            "Overworld Olivine Vein",
            "主世界橄榄石矿脉",
            vein -> vein
                    .weight(20)
                    .clusterSize(UniformInt.of(44, 52))
                    .density(0.3f)
                    .layer(WorldGenLayers.DEEPSLATE)
                    .heightRangeUniform(-20, 10)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .classicVeinGenerator(generator -> generator
                            .primary(layer -> layer.mat(GTMaterials.Olivine))
                            .secondary(layer -> layer.mat(GTMaterials.Magnesite))
                            .between(layer -> layer.mat(GTMaterials.Olivine))
                            .sporadic(layer -> layer.mat(GTMaterials.Magnesite))
                            .yRadius(8))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Olivine)
                            .density(0.4f)
                            .radius(3)));

    public static final GTOreDefinition REDSTONE_VEIN_OW = create("redstone_vein_ow",
            "Overworld Redstone Vein",
            "主世界红石矿脉",
            vein -> vein
                    .weight(60)
                    .clusterSize(UniformInt.of(50, 65))
                    .density(1.0f)
                    .layer(WorldGenLayers.DEEPSLATE)
                    .heightRangeUniform(-60, -10)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .veinedVeinGenerator(generator -> generator
                            .oreBlock(GTMaterials.Redstone, 2)
                            .oreBlock(GTMaterials.Ruby, 1)
                            .oreBlock(GTMaterials.Cinnabar, 1)
                            .minRichness(0.6f)
                            .maxRichness(1.0f)
                            .maxRichnessThreshold(1.0f))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Redstone)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static final GTOreDefinition SAPPHIRE_VEIN_OW = create("sapphire_vein_ow",
            "Overworld Sapphire Vein",
            "主世界蓝宝石矿脉",
            vein -> vein
                    .weight(60)
                    .clusterSize(UniformInt.of(30, 36))
                    .density(0.3f)
                    .layer(WorldGenLayers.DEEPSLATE)
                    .heightRangeUniform(-40, 0)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .classicVeinGenerator(generator -> generator
                            .primary(layer -> layer.mat(GTMaterials.Electrotine))
                            .secondary(layer -> layer.mat(GTMaterials.Sapphire))
                            .between(layer -> layer.mat(GTMaterials.Electrotine))
                            .sporadic(layer -> layer.mat(GTMaterials.Sapphire)))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Sapphire)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(3)));

    public static void init() {}
}
