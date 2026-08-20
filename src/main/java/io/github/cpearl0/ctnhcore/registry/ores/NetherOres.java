package io.github.cpearl0.ctnhcore.registry.ores;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.tags.BiomeTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import static com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE;
import static com.gregtechceu.gtceu.common.data.GTOres.create;

public class NetherOres {

    // ==================== Nether ====================

    @Key("ctnhcore:nickel_vein_nt")
    @CN("下界镍矿脉")
    @EN("Nether Nickel Vein")
    public static Lang ctnhNickelVein;

    @Key("gtceu.jei.ore_vein.nickel_vein_nt")
    @CN("下界镍矿脉")
    @EN("Nether Nickel Vein")
    public static Lang gtceuNickelVein;

    public static GTOreDefinition NICKEL_VEIN_NT = create(CTNHCore.id("nickel_vein_nt"),
            vein -> vein
                    .weight(40)
                    .clusterSize(UniformInt.of(30, 50))
                    .density(0.25F)
                    .discardChanceOnAirExposure(0)
                    .layer(WorldGenLayers.NETHERRACK)
                    .biomes(BiomeTags.IS_NETHER)
                    .heightRangeUniform(10, 60)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Nickel).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Pentlandite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Cobaltite))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Nickel)
                            .placement(ABOVE)));

    @Key("ctnhcore:monazite_vein_nt")
    @CN("下界独居石矿脉")
    @EN("Nether Monazite Vein")
    public static Lang ctnhMonaziteVein;

    @Key("gtceu.jei.ore_vein.monazite_vein_nt")
    @CN("下界独居石矿脉")
    @EN("Nether Monazite Vein")
    public static Lang gtceuMonaziteVein;

    public static final GTOreDefinition MONAZITE_VEIN_NT = create(CTNHCore.id("monazite_vein_nt"),
            vein -> vein
                    .clusterSize(UniformInt.of(25, 30))
                    .density(0.25f)
                    .weight(30)
                    .layer(WorldGenLayers.NETHERRACK)
                    .heightRangeUniform(20, 40)
                    .biomes(BiomeTags.IS_NETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Monazite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Bastnasite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Neodymium))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Bastnasite)
                            .placement(ABOVE)));

    @Key("ctnhcore:redstone_vein_nt")
    @CN("下界红石矿脉")
    @EN("Nether Redstone Vein")
    public static Lang ctnhRedstoneVein;

    @Key("gtceu.jei.ore_vein.redstone_vein_nt")
    @CN("下界红石矿脉")
    @EN("Nether Redstone Vein")
    public static Lang gtceuRedstoneVein;

    public static final GTOreDefinition REDSTONE_VEIN_NT = create(CTNHCore.id("redstone_vein_nt"),
            vein -> vein
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.2f)
                    .weight(50)
                    .layer(WorldGenLayers.NETHERRACK)
                    .heightRangeUniform(10, 40)
                    .biomes(BiomeTags.IS_NETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Redstone).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Ruby))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Cinnabar))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Redstone)
                            .placement(ABOVE)));

    @Key("ctnhcore:beryllium_vein_nt")
    @CN("下界铍矿脉")
    @EN("Nether Beryllium Vein")
    public static Lang ctnhBerylliumVein;

    @Key("gtceu.jei.ore_vein.beryllium_vein_nt")
    @CN("下界铍矿脉")
    @EN("Nether Beryllium Vein")
    public static Lang gtceuBerylliumVein;

    public static final GTOreDefinition BERYLLIUM_VEIN_NT = create(CTNHCore.id("beryllium_vein_nt"),
            vein -> vein
                    .clusterSize(UniformInt.of(50, 60))
                    .density(0.75f)
                    .weight(30)
                    .layer(WorldGenLayers.NETHERRACK)
                    .heightRangeUniform(10, 30)
                    .biomes(BiomeTags.IS_NETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Beryllium).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Emerald).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Thorium))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Beryllium)
                            .placement(ABOVE)));

    @Key("ctnhcore:molybdenum_vein_nt")
    @CN("下界钼矿脉")
    @EN("Nether Molybdenum Vein")
    public static Lang ctnhMolybdenumVein;

    @Key("gtceu.jei.ore_vein.molybdenum_vein_nt")
    @CN("下界钼矿脉")
    @EN("Nether Molybdenum Vein")
    public static Lang gtceuMolybdenumVein;

    public static final GTOreDefinition MOLYBDENUM_VEIN_NT = create(CTNHCore.id("molybdenum_vein_nt"),
            vein -> vein
                    .clusterSize(UniformInt.of(25, 30))
                    .density(0.25f)
                    .weight(20)
                    .layer(WorldGenLayers.NETHERRACK)
                    .heightRangeUniform(20, 50)
                    .biomes(BiomeTags.IS_NETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Wulfenite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Molybdenite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Powellite))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Wulfenite)
                            .placement(ABOVE)));

    @Key("ctnhcore:tetrahedrite_vein_nt")
    @CN("下界黝铜矿脉")
    @EN("Nether Tetrahedrite Vein")
    public static Lang ctnhTetrahedriteVein;

    @Key("gtceu.jei.ore_vein.tetrahedrite_vein_nt")
    @CN("下界黝铜矿脉")
    @EN("Nether Tetrahedrite Vein")
    public static Lang gtceuTetrahedriteVein;

    public static final GTOreDefinition TETRAHEDRITE_VEIN_NT = create(CTNHCore.id("tetrahedrite_vein_nt"),
            vein -> vein
                    .clusterSize(UniformInt.of(40, 50))
                    .density(1.0f)
                    .weight(50)
                    .layer(WorldGenLayers.NETHERRACK)
                    .heightRangeUniform(80, 120)
                    .biomes(BiomeTags.IS_NETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(1).mat(GTMaterials.Tetrahedrite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Chalcopyrite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Stibnite).size(2, 4))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Tetrahedrite)
                            .placement(ABOVE)));

    @Key("ctnhcore:banded_iron_vein_nt")
    @CN("下界带状铁矿脉")
    @EN("Nether Banded Iron Vein")
    public static Lang ctnhBandedIronVein;

    @Key("gtceu.jei.ore_vein.banded_iron_vein_nt")
    @CN("下界带状铁矿脉")
    @EN("Nether Banded Iron Vein")
    public static Lang gtceuBandedIronVein;

    public static final GTOreDefinition BANDED_IRON_VEIN_NT = create(CTNHCore.id("banded_iron_vein_nt"),
            vein -> vein
                    .clusterSize(UniformInt.of(40, 50))
                    .density(1.0f)
                    .weight(30)
                    .layer(WorldGenLayers.NETHERRACK)
                    .heightRangeUniform(20, 40)
                    .biomes(BiomeTags.IS_NETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(1).mat(GTMaterials.Iron))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Hematite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Gold))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Hematite)
                            .placement(ABOVE)));

    @Key("ctnhcore:topaz_vein_nt")
    @CN("下界黄玉矿脉")
    @EN("Nether Topaz Vein")
    public static Lang ctnhTopazVein;

    @Key("gtceu.jei.ore_vein.topaz_vein_nt")
    @CN("下界黄玉矿脉")
    @EN("Nether Topaz Vein")
    public static Lang gtceuTopazVein;

    public static final GTOreDefinition TOPAZ_VEIN_NT = create(CTNHCore.id("topaz_vein_nt"),
            vein -> vein
                    .clusterSize(UniformInt.of(25, 30))
                    .density(0.25f)
                    .weight(50)
                    .layer(WorldGenLayers.NETHERRACK)
                    .heightRangeUniform(80, 120)
                    .biomes(BiomeTags.IS_NETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Topaz).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Chalcopyrite))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Topaz)
                            .placement(ABOVE)));

    @Key("ctnhcore:sulfur_vein_nt")
    @CN("下界硫矿脉")
    @EN("Nether Sulfur Vein")
    public static Lang ctnhSulfurVein;

    @Key("gtceu.jei.ore_vein.sulfur_vein_nt")
    @CN("下界硫矿脉")
    @EN("Nether Sulfur Vein")
    public static Lang gtceuSulfurVein;

    public static final GTOreDefinition SULFUR_VEIN_NT = create(CTNHCore.id("sulfur_vein_nt"),
            vein -> vein
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.2f)
                    .weight(50)
                    .layer(WorldGenLayers.NETHERRACK)
                    .heightRangeUniform(20, 60)
                    .biomes(BiomeTags.IS_NETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Sulfur).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Pyrite))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Sphalerite))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Sulfur)
                            .placement(ABOVE)));

    @Key("ctnhcore:saltpeter_vein_nt")
    @CN("下界硝石矿脉")
    @EN("Nether Saltpeter Vein")
    public static Lang ctnhSaltpeterVein;

    @Key("gtceu.jei.ore_vein.saltpeter_vein_nt")
    @CN("下界硝石矿脉")
    @EN("Nether Saltpeter Vein")
    public static Lang gtceuSaltpeterVein;

    public static final GTOreDefinition SALTPETER_VEIN_NT = create(CTNHCore.id("saltpeter_vein_nt"),
            vein -> vein
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.25f)
                    .weight(50)
                    .layer(WorldGenLayers.NETHERRACK)
                    .heightRangeUniform(80, 120)
                    .biomes(BiomeTags.IS_NETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Saltpeter).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Electrotine))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Saltpeter)
                            .placement(ABOVE)));

    @Key("ctnhcore:manganese_vein_nt")
    @CN("下界锰矿脉")
    @EN("Nether Manganese Vein")
    public static Lang ctnhManganeseVein;

    @Key("gtceu.jei.ore_vein.manganese_vein_nt")
    @CN("下界锰矿脉")
    @EN("Nether Manganese Vein")
    public static Lang gtceuManganeseVein;

    public static final GTOreDefinition MANGANESE_VEIN_NT = create(CTNHCore.id("manganese_vein_nt"),
            vein -> vein
                    .clusterSize(UniformInt.of(50, 70))
                    .density(0.75f)
                    .weight(30)
                    .layer(WorldGenLayers.NETHERRACK)
                    .heightRangeUniform(20, 30)
                    .biomes(BiomeTags.IS_NETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Pyrolusite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Tantalite))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Pyrolusite)
                            .placement(ABOVE)));

    @Key("ctnhcore:certus_quartz_nt")
    @CN("下界赛特斯石英")
    @EN("Nether Certus Quartz")
    public static Lang ctnhCertusQuartz;

    @Key("gtceu.jei.ore_vein.certus_quartz_nt")
    @CN("下界赛特斯石英")
    @EN("Nether Certus Quartz")
    public static Lang gtceuCertusQuartz;

    public static final GTOreDefinition CERTUS_QUARTZ_VEIN_NT = create(CTNHCore.id("certus_quartz_nt"),
            vein -> vein
                    .clusterSize(UniformInt.of(25, 30))
                    .density(0.25f)
                    .weight(40)
                    .layer(WorldGenLayers.NETHERRACK)
                    .heightRangeUniform(80, 120)
                    .biomes(BiomeTags.IS_NETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.CertusQuartz).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Quartzite))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Barite))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.CertusQuartz)
                            .placement(ABOVE)));

    @Key("ctnhcore:ancient_debris_vein_nt")
    @CN("下界远古残骸矿脉")
    @EN("Nether Ancient Debris Vein")
    public static Lang ctnhAncientDebrisVein;

    @Key("gtceu.jei.ore_vein.ancient_debris_vein_nt")
    @CN("下界远古残骸矿脉")
    @EN("Nether Ancient Debris Vein")
    public static Lang gtceuAncientDebrisVein;

    public static GTOreDefinition ANCIENT_DEBRIS_VEIN_NT = create(CTNHCore.id("ancient_debris_vein_nt"),
            vein -> vein
                    .weight(10)
                    .clusterSize(35)
                    .density(0.4F)
                    .discardChanceOnAirExposure(0)
                    .layer(WorldGenLayers.NETHERRACK)
                    .biomes(BiomeTags.IS_NETHER)
                    .heightRangeUniform(5, 20)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(CTNHMaterials.PreciousAlloy).size(2, 4))
                                    .layer(l -> l.weight(1).block(() -> Blocks.ANCIENT_DEBRIS))
                                    .layer(l -> l.weight(1).mat(GTMaterials.NetherQuartz))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.PreciousAlloy)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));

    @Key("ctnhcore:bauxite_vein_nt")
    @CN("下界铝土矿脉")
    @EN("Nether Bauxite Vein")
    public static Lang ctnhBauxiteVeinNether;

    @Key("gtceu.jei.ore_vein.bauxite_vein_nt")
    @CN("下界铝土矿脉")
    @EN("Nether Bauxite Vein")
    public static Lang gtceuBauxiteVeinNether;

    public static final GTOreDefinition BAUXITE_VEIN_NT = create(CTNHCore.id("bauxite_vein_nt"),
            vein -> vein
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.25f)
                    .weight(30)
                    .layer(WorldGenLayers.NETHERRACK)
                    .heightRangeUniform(20, 60)
                    .biomes(BiomeTags.IS_NETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Bauxite).size(2, 4))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.Alumina))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Bauxite)
                            .placement(ABOVE)));

    public static void init() {}
}
