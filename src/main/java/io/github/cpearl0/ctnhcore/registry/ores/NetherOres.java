package io.github.cpearl0.ctnhcore.registry.ores;

import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.core.Direction;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;

import static com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE;
import static io.github.cpearl0.ctnhcore.registry.CTNHOres.create;

public class NetherOres {

    // ==================== Nether ====================

    public static GTOreDefinition NICKEL_VEIN_NT = create("nickel_vein_nt",
            "Nether Nickel Vein",
            "下界镍矿脉",
            vein -> vein
                    .weight(80)
                    .clusterSize(UniformInt.of(30, 50))
                    .density(0.3f)
                    .discardChanceOnAirExposure(0)
                    .layer(WorldGenLayers.NETHERRACK)
                    .biomes(BiomeTags.IS_NETHER)
                    .heightRangeUniform(10, 60)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Nickel).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Pentlandite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Cobaltite).size(2, 4))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Nickel)
                            .placement(ABOVE)));

    public static final GTOreDefinition MONAZITE_VEIN_NT = create("monazite_vein_nt",
            "Nether Monazite Vein",
            "下界独居石矿脉",
            vein -> vein
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.3f)
                    .weight(60)
                    .layer(WorldGenLayers.NETHERRACK)
                    .heightRangeUniform(20, 40)
                    .biomes(BiomeTags.IS_NETHER)
                    .dikeVeinGenerator(generator -> generator
                            .withBlock(GTMaterials.Monazite, 2, 20, 40)
                            .withBlock(GTMaterials.Bastnasite, 2, 20, 40)
                            .withBlock(GTMaterials.Neodymium, 1, 20, 40))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Bastnasite)
                            .placement(ABOVE)));

    public static final GTOreDefinition REDSTONE_VEIN_NT = create("redstone_vein_nt",
            "Nether Redstone Vein",
            "下界红石矿脉",
            vein -> vein
                    .clusterSize(80)
                    .density(1.0f)
                    .weight(80)
                    .layer(WorldGenLayers.NETHERRACK)
                    .heightRangeUniform(10, 40)
                    .biomes(BiomeTags.IS_NETHER)
                    .veinedVeinGenerator(generator -> generator
                            .oreBlock(GTMaterials.Redstone, 2)
                            .oreBlock(GTMaterials.Ruby, 1)
                            .oreBlock(GTMaterials.Cinnabar, 1)
                            .minRichness(0.6f)
                            .maxRichness(1.0f)
                            .maxRichnessThreshold(1.0f))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Redstone)
                            .placement(ABOVE)));

    public static final GTOreDefinition BERYLLIUM_VEIN_NT = create("beryllium_vein_nt",
            "Nether Beryllium Vein",
            "下界铍矿脉",
            vein -> vein
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.6f)
                    .weight(40)
                    .layer(WorldGenLayers.NETHERRACK)
                    .heightRangeUniform(10, 40)
                    .biomes(BiomeTags.IS_NETHER)
                    .dikeVeinGenerator(generator -> generator
                            .withBlock(GTMaterials.Beryllium, 1, 10, 40)
                            .withBlock(GTMaterials.Emerald, 1, 10, 40)
                            .withBlock(GTMaterials.Thorium, 2, 10, 20))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Beryllium)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static final GTOreDefinition MOLYBDENUM_VEIN_NT = create("molybdenum_vein_nt",
            "Nether Molybdenum Vein",
            "下界钼矿脉",
            vein -> vein
                    .clusterSize(60)
                    .density(1.0f)
                    .weight(40)
                    .layer(WorldGenLayers.NETHERRACK)
                    .heightRangeUniform(20, 50)
                    .biomes(BiomeTags.IS_NETHER)
                    .veinedVeinGenerator(generator -> generator
                            .oreBlock(GTMaterials.Wulfenite, 2)
                            .oreBlock(GTMaterials.Molybdenite, 2)
                            .oreBlock(GTMaterials.Powellite, 1)
                            .minRichness(0.6f)
                            .maxRichness(1.0f)
                            .maxRichnessThreshold(1.0f))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Wulfenite)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static final GTOreDefinition TETRAHEDRITE_VEIN_NT = create("tetrahedrite_vein_nt",
            "Nether Tetrahedrite Vein",
            "下界黝铜矿脉",
            vein -> vein
                    .clusterSize(100)
                    .density(1.0f)
                    .weight(80)
                    .layer(WorldGenLayers.NETHERRACK)
                    .heightRangeUniform(80, 120)
                    .biomes(BiomeTags.IS_NETHER)
                    .veinedVeinGenerator(generator -> generator
                            .oreBlock(GTMaterials.Tetrahedrite, 1)
                            .oreBlock(GTMaterials.Chalcopyrite, 1)
                            .oreBlock(GTMaterials.Stibnite, 1)
                            .minRichness(0.6f)
                            .maxRichness(1.0f)
                            .maxRichnessThreshold(1.0f))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Tetrahedrite)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static final GTOreDefinition BANDED_IRON_VEIN_NT = create("banded_iron_vein_nt",
            "Nether Banded Iron Vein",
            "下界带状铁矿脉",
            vein -> vein
                    .clusterSize(100)
                    .density(1.0f)
                    .weight(60)
                    .layer(WorldGenLayers.NETHERRACK)
                    .heightRangeUniform(20, 40)
                    .biomes(BiomeTags.IS_NETHER)
                    .veinedVeinGenerator(generator -> generator
                            .oreBlock(GTMaterials.Hematite, 2)
                            .oreBlock(GTMaterials.Iron, 1)
                            .oreBlock(GTMaterials.Gold, 1)
                            .minRichness(0.6f)
                            .maxRichness(1.0f)
                            .maxRichnessThreshold(1.0f))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Hematite)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static final GTOreDefinition TOPAZ_VEIN_NT = create("topaz_vein_nt",
            "Nether Topaz Vein",
            "下界黄玉矿脉",
            vein -> vein
                    .clusterSize(60)
                    .density(1.0f)
                    .weight(80)
                    .layer(WorldGenLayers.NETHERRACK)
                    .heightRangeUniform(80, 120)
                    .biomes(BiomeTags.IS_NETHER)
                    .veinedVeinGenerator(generator -> generator
                            .oreBlock(GTMaterials.Topaz, 2)
                            .oreBlock(GTMaterials.Chalcopyrite, 1)
                            .minRichness(0.6f)
                            .maxRichness(1.0f)
                            .maxRichnessThreshold(1.0f))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Topaz)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static final GTOreDefinition SULFUR_VEIN_NT = create("sulfur_vein_nt",
            "Nether Sulfur Vein",
            "下界硫矿脉",
            vein -> vein
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.3f)
                    .weight(80)
                    .layer(WorldGenLayers.NETHERRACK)
                    .heightRangeUniform(20, 60)
                    .biomes(BiomeTags.IS_NETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Sulfur).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Pyrite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Sphalerite).size(2, 4)))
                            .withLayerAxis(Direction.Axis.Y))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Sulfur)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static final GTOreDefinition SALTPETER_VEIN_NT = create("saltpeter_vein_nt",
            "Nether Saltpeter Vein",
            "下界硝石矿脉",
            vein -> vein
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.3f)
                    .weight(80)
                    .layer(WorldGenLayers.NETHERRACK)
                    .heightRangeUniform(80, 120)
                    .biomes(BiomeTags.IS_NETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Saltpeter).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Electrotine).size(2, 4)))
                            .withLayerAxis(Direction.Axis.Y))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Saltpeter)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static final GTOreDefinition MANGANESE_VEIN_NT = create("manganese_vein_nt",
            "Nether Manganese Vein",
            "下界锰矿脉",
            vein -> vein
                    .clusterSize(UniformInt.of(50, 60))
                    .density(0.6f)
                    .weight(60)
                    .layer(WorldGenLayers.NETHERRACK)
                    .heightRangeUniform(20, 30)
                    .biomes(BiomeTags.IS_NETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Pyrolusite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Tantalite).size(2, 4))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Pyrolusite)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static final GTOreDefinition CERTUS_QUARTZ_VEIN_NT = create("certus_quartz_nt",
            "Nether Certus Quartz",
            "下界赛特斯石英",
            vein -> vein
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.6f)
                    .weight(80)
                    .layer(WorldGenLayers.NETHERRACK)
                    .heightRangeUniform(80, 120)
                    .biomes(BiomeTags.IS_NETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.CertusQuartz).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Quartzite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Barite).size(2, 4)))
                            .withLayerAxis(Direction.Axis.Y))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.CertusQuartz)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static GTOreDefinition ANCIENT_DEBRIS_VEIN_NT = create("ancient_debris_vein_nt",
            "Nether Ancient Debris Vein",
            "下界远古残骸矿脉",
            vein -> vein
                    .weight(20)
                    .clusterSize(70)
                    .density(1.0f)
                    .discardChanceOnAirExposure(0)
                    .layer(WorldGenLayers.NETHERRACK)
                    .biomes(BiomeTags.IS_NETHER)
                    .heightRangeUniform(5, 20)
                    .veinedVeinGenerator(generator -> generator
                            .oreBlock(CTNHMaterials.PreciousAlloy, 2)
                            .oreBlock(GTMaterials.NetherQuartz, 1)
                            .oreBlock(Blocks.ANCIENT_DEBRIS.defaultBlockState(), 1)
                            .minRichness(0.6f)
                            .maxRichness(1.0f)
                            .maxRichnessThreshold(1.0f))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.PreciousAlloy)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static final GTOreDefinition BAUXITE_VEIN_NT = create("bauxite_vein_nt",
            "Nether Bauxite Vein",
            "下界铝土矿脉",
            vein -> vein
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.3f)
                    .weight(60)
                    .layer(WorldGenLayers.NETHERRACK)
                    .heightRangeUniform(20, 60)
                    .biomes(BiomeTags.IS_NETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Bauxite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.Alumina).size(2, 4))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Bauxite)
                            .placement(ABOVE)
                            .density(0.4f)
                            .radius(5)));

    public static void init() {}
}
