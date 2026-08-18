package io.github.cpearl0.ctnhcore.registry.ores;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHWorldgenLayers;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import twilightforest.data.tags.BiomeTagGenerator;

import static com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE;
import static com.gregtechceu.gtceu.common.data.GTOres.create;
import static io.github.cpearl0.ctnhcore.registry.CTNHWorlds.*;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.*;

public class TwilightForestOres {

    // ==================== TwilightForest ====================

    @Key("ctnhcore:cryolite_vein")
    @CN("冰晶石矿脉")
    @EN("Cryolite Vein")
    public static Lang ctnhCryoliteVein;

    @Key("gtceu.jei.ore_vein.cryolite_vein")
    @CN("冰晶石矿脉")
    @EN("Cryolite Vein")
    public static Lang gtceuCryoliteVein;

    public static GTOreDefinition CRYOLITE_VEIN = create(CTNHCore.id("cryolite_vein"), vein -> vein.weight(70)
            .clusterSize(40)
            .density(0.25F)
            .discardChanceOnAirExposure(0)
            .layer(CTNHWorldgenLayers.TWILIGHT)
            .dimensions(TWILIGHT_FOREST)
            .heightRangeUniform(-30, 0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(CTNHMaterials.Cryolite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Silver).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Mica).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Lead).size(1, 1))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(CTNHMaterials.Cryolite)
                    .placement(ABOVE)
                    .density(0.4F)
                    .radius(5)));

    @Key("ctnhcore:steel_leaf_vein")
    @CN("暮色森林钢叶矿脉")
    @EN("Twilight Forest Steel Leaf Vein")
    public static Lang ctnhSteelLeafVein;

    @Key("gtceu.jei.ore_vein.steel_leaf_vein")
    @CN("暮色森林钢叶矿脉")
    @EN("Twilight Forest Steel Leaf Vein")
    public static Lang gtceuSteelLeafVein;

    public static GTOreDefinition STEEL_LEAF_VEINN = create(CTNHCore.id("steel_leaf_vein"), vein -> vein.weight(20)
            .clusterSize(40)
            .density(0.35F)
            .discardChanceOnAirExposure(0)
            .layer(CTNHWorldgenLayers.TWILIGHT)
            .dimensions(TWILIGHT_FOREST)
            .biomes(BiomeTagGenerator.VALID_NAGA_COURTYARD_BIOMES)
            .heightRangeUniform(-33, 20)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(CTNHMaterials.SteelLeaf).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Apatite).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Galena).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Pyrochlore).size(1, 1))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(CTNHMaterials.SteelLeaf)
                    .placement(ABOVE)
                    .density(0.4F)
                    .radius(5)));

    @Key("ctnhcore:lich_bone_vein")
    @CN("暮色森林巫师之骨矿脉")
    @EN("Twilight Forest Lich Bone Vein")
    public static Lang ctnhLichBoneVein;

    @Key("gtceu.jei.ore_vein.lich_bone_vein")
    @CN("暮色森林巫师之骨矿脉")
    @EN("Twilight Forest Lich Bone Vein")
    public static Lang gtceuLichBoneVein;

    public static GTOreDefinition LICH_BONE_VEIN = create(CTNHCore.id("lich_bone_vein"), vein -> vein.weight(20)
            .clusterSize(45)
            .density(0.35F)
            .discardChanceOnAirExposure(0)
            .layer(CTNHWorldgenLayers.TWILIGHT)
            .dimensions(TWILIGHT_FOREST)
            .biomes(BiomeTagGenerator.VALID_LICH_TOWER_BIOMES)
            .heightRangeUniform(-33, 20)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.TricalciumPhosphate).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Coal).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Graphite).size(1, 1))
                            .layer(l -> l.weight(1).mat(CTNHMaterials.SpiritAsh).size(1, 1))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(CTNHMaterials.SpiritAsh)
                    .placement(ABOVE)
                    .density(0.4F)
                    .radius(5)));

    @Key("ctnhcore:toxic_swamp_amber_vein")
    @CN("暮色森林毒沼琥珀矿脉")
    @EN("Twilight Forest Toxic Swamp Amber Vein")
    public static Lang ctnhToxicSwampAmberVein;

    @Key("gtceu.jei.ore_vein.toxic_swamp_amber_vein")
    @CN("暮色森林毒沼琥珀矿脉")
    @EN("Twilight Forest Toxic Swamp Amber Vein")
    public static Lang gtceuToxicSwampAmberVein;

    public static GTOreDefinition TOXIC_SWAMP_AMBER_VEIN = create(CTNHCore.id("toxic_swamp_amber_vein"),
            vein -> vein.weight(50)
                    .clusterSize(35)
                    .density(0.45F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.TWILIGHT)
                    .dimensions(TWILIGHT_FOREST)
                    .biomes(BiomeTagGenerator.VALID_LABYRINTH_BIOMES)
                    .heightRangeUniform(-33, 20)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(GTMaterials.Cinnabar).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Galena).size(1, 1))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Saltpeter).size(1, 1))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.ToxicSwampAmber).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.ToxicSwampAmber)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));

    @Key("ctnhcore:illusion_iron_vein")
    @CN("幻铁矿脉")
    @EN("Illusion Iron Vein")
    public static Lang ctnhIllusionIronVein;

    @Key("gtceu.jei.ore_vein.illusion_iron_vein")
    @CN("幻铁矿脉")
    @EN("Illusion Iron Vein")
    public static Lang gtceuIllusionIronVein;

    public static GTOreDefinition ILLUSION_IRON_VEIN = create(CTNHCore.id("illusion_iron_vein"), vein -> vein.weight(50)
            .clusterSize(25)
            .density(0.45F)
            .discardChanceOnAirExposure(0)
            .layer(CTNHWorldgenLayers.TWILIGHT)
            .dimensions(TWILIGHT_FOREST)
            .biomes(BiomeTagGenerator.VALID_KNIGHT_STRONGHOLD_BIOMES)
            .heightRangeUniform(-33, 20)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Pyrite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.VanadiumMagnetite).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Tantalite).size(1, 1))
                            .layer(l -> l.weight(1).mat(CTNHMaterials.IllusionIron).size(1, 1))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(CTNHMaterials.IllusionIron)
                    .placement(ABOVE)
                    .density(0.4F)
                    .radius(5)));

    @Key("ctnhcore:arctic_crystal_core_vein")
    @CN("暮色森林极寒冰核矿脉")
    @EN("Twilight Forest Arctic Crystal Core Vein")
    public static Lang ctnhArcticCrystalCoreVein;

    @Key("gtceu.jei.ore_vein.arctic_crystal_core_vein")
    @CN("暮色森林极寒冰核矿脉")
    @EN("Twilight Forest Arctic Crystal Core Vein")
    public static Lang gtceuArcticCrystalCoreVein;

    public static GTOreDefinition ARCTIC_CRYSTAL_CORE_VEIN = create(CTNHCore.id("arctic_crystal_core_vein"),
            vein -> vein.weight(50)
                    .clusterSize(25)
                    .density(0.45F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.TWILIGHT)
                    .dimensions(TWILIGHT_FOREST)
                    .biomes(BiomeTagGenerator.VALID_AURORA_PALACE_BIOMES)
                    .heightRangeUniform(-33, 20)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(GTMaterials.Electrotine).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Kyanite).size(1, 1))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Lapis).size(1, 1))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.PolarIceCore).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.PolarIceCore)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));

    @Key("ctnhcore:dragonflame_vein")
    @CN("暮色森林龙焰矿脉")
    @EN("Twilight Forest Dragonflame Vein")
    public static Lang ctnhDragonflameVein;

    @Key("gtceu.jei.ore_vein.dragonflame_vein")
    @CN("暮色森林龙焰矿脉")
    @EN("Twilight Forest Dragonflame Vein")
    public static Lang gtceuDragonflameVein;

    public static GTOreDefinition DRAGONFLAME_VEIN = create(CTNHCore.id("dragonflame_vein"), vein -> vein.weight(50)
            .clusterSize(35)
            .density(0.55F)
            .discardChanceOnAirExposure(0)
            .layer(CTNHWorldgenLayers.TWILIGHT)
            .dimensions(TWILIGHT_FOREST)
            .biomes(BiomeTagGenerator.VALID_HYDRA_LAIR_BIOMES)
            .heightRangeUniform(-33, 20)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Hematite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Ruby).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Pyrochlore).size(1, 1))
                            .layer(l -> l.weight(1).mat(CTNHMaterials.Dragonflame).size(1, 1))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(CTNHMaterials.Dragonflame)
                    .placement(ABOVE)
                    .density(0.4F)
                    .radius(5)));

    @Key("ctnhcore:eclipse_shadow_vein")
    @CN("暮色森林幽影矿脉")
    @EN("Twilight Forest Eclipse Shadow Vein")
    public static Lang ctnhEclipseShadowVein;

    @Key("gtceu.jei.ore_vein.eclipse_shadow_vein")
    @CN("暮色森林幽影矿脉")
    @EN("Twilight Forest Eclipse Shadow Vein")
    public static Lang gtceuEclipseShadowVein;

    public static GTOreDefinition ECLIPSE_SHADOW_VEIN = create(CTNHCore.id("eclipse_shadow_vein"),
            vein -> vein.weight(50)
                    .clusterSize(45)
                    .density(0.25F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.TWILIGHT)
                    .dimensions(TWILIGHT_FOREST)
                    .biomes(BiomeTagGenerator.VALID_DARK_TOWER_BIOMES)
                    .heightRangeUniform(-33, 20)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(GTMaterials.Stibnite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Antimony).size(1, 1))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Silver).size(1, 1))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.EclipseShadow).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.EclipseShadow)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));

    @Key("ctnhcore:thunderstrike_vein")
    @CN("暮色森林雷纹矿脉")
    @EN("Twilight Forest Thunderstrike Vein")
    public static Lang ctnhThunderstrikeVein;

    @Key("gtceu.jei.ore_vein.thunderstrike_vein")
    @CN("暮色森林雷纹矿脉")
    @EN("Twilight Forest Thunderstrike Vein")
    public static Lang gtceuThunderstrikeVein;

    public static GTOreDefinition LIGHTNING_VEIN_VEIN = create(CTNHCore.id("thunderstrike_vein"),
            vein -> vein.weight(50)
                    .clusterSize(65)
                    .density(0.65F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.TWILIGHT)
                    .dimensions(TWILIGHT_FOREST)
                    .biomes(BiomeTagGenerator.VALID_TROLL_CAVE_BIOMES)
                    .heightRangeUniform(-33, 20)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(GTMaterials.Gold).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Diamond).size(1, 1))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.EclipseShadow).size(1, 1))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.LightningPattern).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.LightningPattern)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));

    @Key("ctnhcore:arsenic_vein")
    @CN("红砷镍矿脉")
    @EN("Red Arsenic Nickel Vein")
    public static Lang ctnhArsenicVein;

    @Key("gtceu.jei.ore_vein.arsenic_vein")
    @CN("红砷镍矿脉")
    @EN("Red Arsenic Nickel Vein")
    public static Lang gtceuArsenicVein;

    public static GTOreDefinition ARSENIC_VEIN = create(CTNHCore.id("arsenic_vein"), vein -> vein.weight(10)
            .clusterSize(40)
            .density(0.25F)
            .discardChanceOnAirExposure(0)
            .layer(CTNHWorldgenLayers.TWILIGHT)
            .dimensions(TWILIGHT_FOREST)
            .heightRangeUniform(-30, 0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(CTNHMaterials.Nickeline).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.ArsenicTrioxide).size(1, 3))
                            .layer(l -> l.weight(2).mat(GTMaterials.Pentlandite).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Realgar).size(1, 1))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(CTNHMaterials.Nickeline)
                    .placement(ABOVE)
                    .density(0.4F)
                    .radius(5)));

    @Key("ctnhcore:galena_vein_tf")
    @CN("暮色森林方铅矿脉")
    @EN("Twilight Forest Galena Vein")
    public static Lang ctnhGalenaVeinTf;

    @Key("gtceu.jei.ore_vein.galena_vein_tf")
    @CN("暮色森林方铅矿脉")
    @EN("Twilight Forest Galena Vein")
    public static Lang gtceuGalenaVeinTf;

    public static final GTOreDefinition GALENA_VEIN_TF = create(
            CTNHCore.id("galena_vein_tf"),
            vein -> {
                vein.clusterSize(30).weight(40).layer(CTNHWorldgenLayers.TWILIGHT).density(0.25f)
                        .dimensions(TWILIGHT_FOREST)
                        .heightRangeUniform(-30, 0).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> {
                                    l.weight(3).mat(GTMaterials.Galena).size(2, 4);
                                }).layer(l -> {
                                    l.weight(3).mat(GTMaterials.Silver).size(1, 1);
                                }).layer(l -> {
                                    l.weight(1).mat(GTMaterials.Lead).size(1, 1);
                                });
                            });
                        }).surfaceIndicatorGenerator(indicator -> {
                            indicator.surfaceRock(GTMaterials.Galena)
                                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE).density(0.4f)
                                    .radius(5);
                        });
            });

    @Key("ctnhcore:diamond_vein_tf")
    @CN("暮色森林钻石矿脉")
    @EN("Twilight Forest Diamond Vein")
    public static Lang ctnhDiamondVeinTf;

    @Key("gtceu.jei.ore_vein.diamond_vein_tf")
    @CN("暮色森林钻石矿脉")
    @EN("Twilight Forest Diamond Vein")
    public static Lang gtceuDiamondVeinTf;

    public static final GTOreDefinition DIAMOND_VEIN_TF = create(
            CTNHCore.id("diamond_vein_tf"), vein -> {
                vein.clusterSize(30).weight(40).layer(CTNHWorldgenLayers.TWILIGHT).density(0.25f)
                        .dimensions(TWILIGHT_FOREST)
                        .heightRangeUniform(-30, 0).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Graphite).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Diamond).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Coal).size(1, 1));
                            });
                        }).surfaceIndicatorGenerator(indicator -> {
                            indicator.surfaceRock(GTMaterials.Diamond)
                                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE).density(0.4f)
                                    .radius(5);
                        });
            });

    @Key("ctnhcore:apatite_vein_tf")
    @CN("暮色森林磷灰石矿脉")
    @EN("Twilight Forest Apatite Vein")
    public static Lang ctnhApatiteVeinTf;

    @Key("gtceu.jei.ore_vein.apatite_vein_tf")
    @CN("暮色森林磷灰石矿脉")
    @EN("Twilight Forest Apatite Vein")
    public static Lang gtceuApatiteVeinTf;

    public static final GTOreDefinition APATITE_VEIN_TF = create(
            CTNHCore.id("apatite_vein_tf"), vein -> {
                vein.clusterSize(30).weight(40).layer(CTNHWorldgenLayers.TWILIGHT).density(0.25f)
                        .dimensions(TWILIGHT_FOREST)
                        .heightRangeUniform(-30, 0).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Apatite).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.TricalciumPhosphate).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Pyrochlore).size(1, 1));
                            });
                        }).surfaceIndicatorGenerator(indicator -> {
                            indicator.surfaceRock(GTMaterials.Apatite)
                                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE).density(0.4f)
                                    .radius(5);
                        });
            });

    @Key("ctnhcore:salts_vein_tf")
    @CN("暮色森林盐矿脉")
    @EN("Twilight Forest Salts Vein")
    public static Lang ctnhSaltsVeinTf;

    @Key("gtceu.jei.ore_vein.salts_vein_tf")
    @CN("暮色森林盐矿脉")
    @EN("Twilight Forest Salts Vein")
    public static Lang gtceuSaltsVeinTf;

    public static final GTOreDefinition SALTS_VEIN_TF = create(
            CTNHCore.id("salts_vein_tf"), vein -> {
                vein.clusterSize(30).weight(50).layer(CTNHWorldgenLayers.TWILIGHT).density(0.2f)
                        .dimensions(TWILIGHT_FOREST)
                        .heightRangeUniform(-30, 0).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.RockSalt).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Salt).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Lepidolite).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Spodumene).size(1, 1));
                            });
                        }).surfaceIndicatorGenerator(indicator -> {
                            indicator.surfaceRock(GTMaterials.Salt)
                                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE).density(0.4f)
                                    .radius(5);
                        });
            });

    public static void init() {}
}
