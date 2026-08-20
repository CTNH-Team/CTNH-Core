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

public class OverworldOres {

    // ==================== Overworld ====================

    @Key("ctnhcore:nether_quartz_vein_ow")
    @CN("主世界石英矿脉")
    @EN("Overworld Quartz Vein")
    public static Lang ctnhNetherQuartzVeinOverWorld;

    @Key("gtceu.jei.ore_vein.nether_quartz_vein_ow")
    @CN("主世界石英矿脉")
    @EN("Overworld Quartz Vein")
    public static Lang gtceuNetherQuartzVeinOverWorld;

    public static GTOreDefinition NETHER_QUARTZ_VEIN_OW = create(CTNHCore.id("nether_quartz_vein_ow"),
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

    @Key("ctnhcore:zinc_vein_ow")
    @CN("主世界锌矿脉")
    @EN("Overworld Zinc Vein")
    public static Lang ctnhZincVeinOverWorld;

    @Key("gtceu.jei.ore_vein.zinc_vein_ow")
    @CN("主世界锌矿脉")
    @EN("Overworld Zinc Vein")
    public static Lang gtceuZincVeinOverWorld;

    public static GTOreDefinition ZINC_VEIN_OW = create(CTNHCore.id("zinc_vein_ow"),
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

    @Key("ctnhcore:precious_alloy_vein_ow")
    @CN("主世界贵金属矿脉")
    @EN("Overworld Precious Alloy Vein")
    public static Lang ctnhPreciousAlloyVeinOverWorld;

    @Key("gtceu.jei.ore_vein.precious_alloy_vein_ow")
    @CN("主世界贵金属矿脉")
    @EN("Overworld Precious Alloy Vein")
    public static Lang gtceuPreciousAlloyVeinOverWorld;

    public static GTOreDefinition PRECIOUS_ALLOY_VEIN_OW = create(CTNHCore.id("precious_alloy_vein_ow"),
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

    @Key("ctnhcore:kaolinite_vein_ow")
    @CN("主世界高岭石矿脉")
    @EN("Overworld Kaolinite Vein")
    public static Lang ctnhKaoliniteVeinOverWorld;

    @Key("gtceu.jei.ore_vein.kaolinite_vein_ow")
    @CN("主世界高岭石矿脉")
    @EN("Overworld Kaolinite Vein")
    public static Lang gtceuKaoliniteVeinOverWorld;

    public static final GTOreDefinition KAOLINITE_VEIN_OW = create(CTNHCore.id("kaolinite_vein_ow"),
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

    @Key("ctnhcore:apatite_vein_ow")
    @CN("主世界磷灰石矿脉")
    @EN("Overworld Apatite Vein")
    public static Lang ctnhApatiteVeinOverWorld;

    @Key("gtceu.jei.ore_vein.apatite_vein_ow")
    @CN("主世界磷灰石矿脉")
    @EN("Overworld Apatite Vein")
    public static Lang gtceuApatiteVeinOverWorld;

    public static final GTOreDefinition APATITE_VEIN_OW = create(CTNHCore.id("apatite_vein_ow"),
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

    @Key("ctnhcore:cassiterite_vein_ow")
    @CN("主世界锡石矿脉")
    @EN("Overworld Cassiterite Vein")
    public static Lang ctnhCassiteriteVeinOverWorld;

    @Key("gtceu.jei.ore_vein.cassiterite_vein_ow")
    @CN("主世界锡石矿脉")
    @EN("Overworld Cassiterite Vein")
    public static Lang gtceuCassiteriteVeinOverWorld;

    public static final GTOreDefinition CASSITERITE_VEIN_OW = create(CTNHCore.id("cassiterite_vein_ow"),
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

    @Key("ctnhcore:coal_vein_ow")
    @CN("主世界煤炭矿脉")
    @EN("Overworld Coal Vein")
    public static Lang ctnhCoalVeinOverWorld;

    @Key("gtceu.jei.ore_vein.coal_vein_ow")
    @CN("主世界煤炭矿脉")
    @EN("Overworld Coal Vein")
    public static Lang gtceuCoalVeinOverWorld;

    public static final GTOreDefinition COAL_VEIN_OW = create(CTNHCore.id("coal_vein_ow"),
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

    @Key("ctnhcore:copper_tin_vein_ow")
    @CN("主世界铜锡矿脉")
    @EN("Overworld Copper Tin Vein")
    public static Lang ctnhCopperTinVeinOverWorld;

    @Key("gtceu.jei.ore_vein.copper_tin_vein_ow")
    @CN("主世界铜锡矿脉")
    @EN("Overworld Copper Tin Vein")
    public static Lang gtceuCopperTinVeinOverWorld;

    public static final GTOreDefinition COPPER_TIN_VEIN_OW = create(CTNHCore.id("copper_tin_vein_ow"),
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

    @Key("ctnhcore:iron_vein_ow")
    @CN("主世界铁矿脉")
    @EN("Overworld Iron Vein")
    public static Lang ctnhIronVeinOverWorld;

    @Key("gtceu.jei.ore_vein.iron_vein_ow")
    @CN("主世界铁矿脉")
    @EN("Overworld Iron Vein")
    public static Lang gtceuIronVeinOverWorld;

    public static final GTOreDefinition IRON_VEIN_OW = create(CTNHCore.id("iron_vein_ow"),
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

    @Key("ctnhcore:magnetite_vein_ow")
    @CN("主世界磁铁矿脉")
    @EN("Overworld Magnetite Vein")
    public static Lang ctnhMagnetiteVeinOverWorld;

    @Key("gtceu.jei.ore_vein.magnetite_vein_ow")
    @CN("主世界磁铁矿脉")
    @EN("Overworld Magnetite Vein")
    public static Lang gtceuMagnetiteVeinOverWorld;

    public static final GTOreDefinition MAGNETITE_VEIN_OW = create(CTNHCore.id("magnetite_vein_ow"),
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

    @Key("ctnhcore:salts_vein_ow")
    @CN("主世界盐矿脉")
    @EN("Overworld Salts Vein")
    public static Lang ctnhSaltsVeinOverWorld;

    @Key("gtceu.jei.ore_vein.salts_vein_ow")
    @CN("主世界盐矿脉")
    @EN("Overworld Salts Vein")
    public static Lang gtceuSaltsVeinOverWorld;

    public static final GTOreDefinition SALTS_VEIN_OW = create(CTNHCore.id("salts_vein_ow"),
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

    @Key("ctnhcore:oilsands_vein_ow")
    @CN("主世界油砂矿脉")
    @EN("Overworld Oilsands Vein")
    public static Lang ctnhOilsandsVeinOverWorld;

    @Key("gtceu.jei.ore_vein.oilsands_vein_ow")
    @CN("主世界油砂矿脉")
    @EN("Overworld Oilsands Vein")
    public static Lang gtceuOilsandsVeinOverWorld;

    public static final GTOreDefinition OILSANDS_VEIN_OW = create(CTNHCore.id("oilsands_vein_ow"),
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

    @Key("ctnhcore:copper_vein_ow")
    @CN("主世界铜矿脉")
    @EN("Overworld Copper Vein")
    public static Lang ctnhCopperVeinOverWorld;

    @Key("gtceu.jei.ore_vein.copper_vein_ow")
    @CN("主世界铜矿脉")
    @EN("Overworld Copper Vein")
    public static Lang gtceuCopperVeinOverWorld;

    public static final GTOreDefinition COPPER_VEIN_OW = create(CTNHCore.id("copper_vein_ow"),
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

    @Key("ctnhcore:diamond_vein_ow")
    @CN("主世界钻石矿脉")
    @EN("Overworld Diamond Vein")
    public static Lang ctnhDiamondVeinOverWorld;

    @Key("gtceu.jei.ore_vein.diamond_vein_ow")
    @CN("主世界钻石矿脉")
    @EN("Overworld Diamond Vein")
    public static Lang gtceuDiamondVeinOverWorld;

    public static final GTOreDefinition DIAMOND_VEIN_OW = create(CTNHCore.id("diamond_vein_ow"),
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

    @Key("ctnhcore:lapis_vein_ow")
    @CN("主世界青金石矿脉")
    @EN("Overworld Lapis Vein")
    public static Lang ctnhLapisVeinOverWorld;

    @Key("gtceu.jei.ore_vein.lapis_vein_ow")
    @CN("主世界青金石矿脉")
    @EN("Overworld Lapis Vein")
    public static Lang gtceuLapisVeinOverWorld;

    public static final GTOreDefinition LAPIS_VEIN_OW = create(CTNHCore.id("lapis_vein_ow"),
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

    @Key("ctnhcore:manganese_vein_ow")
    @CN("主世界锰矿脉")
    @EN("Overworld Manganese Vein")
    public static Lang ctnhManganeseVeinOverWorld;

    @Key("gtceu.jei.ore_vein.manganese_vein_ow")
    @CN("主世界锰矿脉")
    @EN("Overworld Manganese Vein")
    public static Lang gtceuManganeseVeinOverWorld;

    public static final GTOreDefinition MANGANESE_VEIN_OW = create(CTNHCore.id("manganese_vein_ow"),
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

    @Key("ctnhcore:olivine_vein_ow")
    @CN("主世界橄榄石矿脉")
    @EN("Overworld Olivine Vein")
    public static Lang ctnhOlivineVeinOverWorld;

    @Key("gtceu.jei.ore_vein.olivine_vein_ow")
    @CN("主世界橄榄石矿脉")
    @EN("Overworld Olivine Vein")
    public static Lang gtceuOlivineVeinOverWorld;

    public static final GTOreDefinition OLIVINE_VEIN_OW = create(CTNHCore.id("olivine_vein_ow"),
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

    @Key("ctnhcore:redstone_vein_ow")
    @CN("主世界红石矿脉")
    @EN("Overworld Redstone Vein")
    public static Lang ctnhRedstoneVeinOverWorld;

    @Key("gtceu.jei.ore_vein.redstone_vein_ow")
    @CN("主世界红石矿脉")
    @EN("Overworld Redstone Vein")
    public static Lang gtceuRedstoneVeinOverWorld;

    public static final GTOreDefinition REDSTONE_VEIN_OW = create(CTNHCore.id("redstone_vein_ow"),
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

    @Key("ctnhcore:sapphire_vein_ow")
    @CN("主世界蓝宝石矿脉")
    @EN("Overworld Sapphire Vein")
    public static Lang ctnhSapphireVeinOverWorld;

    @Key("gtceu.jei.ore_vein.sapphire_vein_ow")
    @CN("主世界蓝宝石矿脉")
    @EN("Overworld Sapphire Vein")
    public static Lang gtceuSapphireVeinOverWorld;

    public static final GTOreDefinition SAPPHIRE_VEIN_OW = create(CTNHCore.id("sapphire_vein_ow"),
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
