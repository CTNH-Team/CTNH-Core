package io.github.cpearl0.ctnhcore.registry.ores;

import io.github.cpearl0.ctnhcore.data.materials.AdastraMaterials;
import io.github.cpearl0.ctnhcore.data.materials.NaquadahMaterials;
import io.github.cpearl0.ctnhcore.data.materials.PlatinumLineMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHWorldgenLayers;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import static com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE;
import static io.github.cpearl0.ctnhcore.registry.CTNHOres.create;
import static io.github.cpearl0.ctnhcore.registry.CTNHWorlds.*;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.*;

public class AdAstraOres {

    // ==================== Moon ====================

    public static GTOreDefinition SHELDONITE_VEIN_MOON = create("sheldonite_vein_moon",
            "Moon Sheldonite Vein",
            "月球谢尔顿矿脉",
            vein -> vein.clusterSize(40)
                    .density(0.3F)
                    .weight(40)
                    .layer(CTNHWorldgenLayers.ADASTRA)
                    .heightRangeUniform(5, 50)
                    .dimensions(MOON)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(GTMaterials.Bornite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Cooperite).size(1, 1))
                                    .layer(l -> l.weight(2).mat(PlatinumLineMaterials.PlatinumOre).size(1, 1))
                                    .layer(l -> l.weight(1).mat(PlatinumLineMaterials.PalladiumOre).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(PlatinumLineMaterials.PlatinumOre)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));

    public static GTOreDefinition PHOSPHATE_VEIN = create("phosphate_vein",
            "Phosphate Vein",
            "磷酸盐矿脉",
            vein -> vein.weight(40)
                    .clusterSize(30)
                    .density(0.30F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.ADASTRA)
                    .dimensions(MOON)
                    .heightRangeUniform(-20, 50)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(GTMaterials.Apatite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.TricalciumPhosphate).size(1, 3))
                                    .layer(l -> l.weight(2).mat(CTNHMaterials.TrisodiumPhosphate).size(1, 2))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.TrisodiumPhosphate)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));

    public static final GTOreDefinition BAUXITE_VEIN = create("bauxite_vein",
            "Moon Bauxite Vein",
            "月球铝土矿脉",
            vein -> {
                vein.clusterSize(36).weight(80).layer(CTNHWorldgenLayers.ADASTRA).density(0.3f).dimensions(MOON)
                        .heightRangeUniform(10, 80).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(GTMaterials.Bauxite).size(1, 4))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Ilmenite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(CTNHMaterials.Alumina).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition ILMENITE_VEIN = create("ilmenite_vein",
            "Ilmenite Vein",
            "钛铁矿脉",
            vein -> {
                vein.clusterSize(24).weight(16).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f).dimensions(MOON)
                        .heightRangeUniform(-70, 10).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Ilmenite).size(1, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Chromite).size(1, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Uvarovite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Perlite).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition DESH_VEIN_AD = create("desh_vein_ad",
            "Moon Desh Vein",
            "月球戴斯矿脉",
            vein -> {
                vein.clusterSize(24).weight(30).layer(CTNHWorldgenLayers.ADASTRA).density(0.3f).dimensions(MOON)
                        .heightRangeUniform(5, 40).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(AdastraMaterials.Desh).size(2, 3))
                                        .layer(l -> l.weight(1).mat(ArcaneCrystal).size(1, 2));
                            });
                        });
            });

    // ==================== Mars ====================

    public static final GTOreDefinition OSTRUM_VEIN_AD = create("ostrum_vein_ad",
            "Mars Ostrum Vein",
            "火星紫金矿脉",
            vein -> {
                vein.clusterSize(24).weight(30).layer(CTNHWorldgenLayers.ADASTRA).density(0.3f).dimensions(MARS)
                        .heightRangeUniform(5, 40).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(AdastraMaterials.Ostrum).size(2, 3))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Scheelite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Tungstate).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition ARSENIC_VEIN_AD = create("arsenic_vein_ad",
            "Space Arsenic Vein",
            "太空砷矿脉",
            vein -> {
                vein.clusterSize(32).weight(60).layer(CTNHWorldgenLayers.ADASTRA).density(0.4f).dimensions(MARS)
                        .heightRangeUniform(40, 60).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(1).mat(GTMaterials.Arsenic).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Bismuth).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Antimony).size(1, 2));
                            });
                        });
            });

    public static final GTOreDefinition SALTS_VEIN_AD = create("salts_vein_ad",
            "Mars Salts Vein",
            "火星盐矿脉",
            vein -> {
                vein.clusterSize(30).weight(50).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f).dimensions(MARS)
                        .heightRangeUniform(30, 70).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.RockSalt).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Salt).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Lepidolite).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Spodumene).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition NAQUADAH_VEIN_AD_MARS = create("naquadah_vein_ad_mars",
            "Mars Naquadah Vein",
            "火星硅岩矿脉",
            vein -> {
                vein.clusterSize(48).weight(5).layer(CTNHWorldgenLayers.ADASTRA).density(0.4f).dimensions(MARS)
                        .heightRangeUniform(10, 90).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(NaquadahMaterials.NaquadahOxideMixture).size(2, 4))
                                        .layer(l -> l.weight(1).mat(NaquadahMaterials.EnrichedNaquadahOxideMixture)
                                                .size(1,
                                                        2));
                            });
                        });
            });

    // ==================== Mercury ====================

    public static final GTOreDefinition CHROMIUM_VEIN_AD = create("chromium_vein_ad",
            "Mercury Chromium Vein",
            "水星铬矿脉",
            vein -> {
                vein.clusterSize(24).weight(5).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f).dimensions(MERCURY)
                        .heightRangeUniform(-15, 15).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(1).mat(GTMaterials.Chromite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Tungsten).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Molybdenum).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Manganese).size(1, 2));
                            });
                        });
            });

    public static final GTOreDefinition URANIUM238_VEIN_AD = create("uranium238_vein_ad",
            "Mercury Uranium-238 Vein",
            "水星铀238矿脉",
            vein -> {
                vein.clusterSize(12).weight(5).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f).dimensions(MERCURY)
                        .heightRangeUniform(65, 120).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(GTMaterials.Uranium238).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Plutonium239).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Thorium).size(1, 2));
                            });
                        });
            });

    public static final GTOreDefinition MAGNESITE_VEIN_AD = create("magnesite_vein_ad",
            "Mercury Magnesite Vein",
            "水星菱镁矿脉",
            vein -> {
                vein.clusterSize(36).weight(55).layer(CTNHWorldgenLayers.ADASTRA).density(0.4f).dimensions(MERCURY)
                        .heightRangeUniform(35, 65).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(GTMaterials.Magnesite).size(1, 3))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Hematite).size(1, 2))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Sulfur).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Opal).size(1, 2));
                            });
                        });
            });

    public static final GTOreDefinition PLATINUM_VEIN_AD = create("platinum_vein_ad",
            "Space Platinum Vein",
            "太空铂矿脉",
            vein -> {
                vein.clusterSize(24).weight(10).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f).dimensions(MERCURY)
                        .heightRangeUniform(-5, 25).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(PlatinumLineMaterials.PlatinumOre).size(1, 3))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Chromite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Cooperite).size(1, 2));
                            });
                        });
            });

    public static final GTOreDefinition LAPIS_VEIN_AD = create("lapis_vein_ad",
            "Ad Astra Lapis Lazuli Vein",
            "青金石矿脉",
            vein -> {
                vein.clusterSize(24).weight(40).layer(CTNHWorldgenLayers.ADASTRA).density(0.3f).dimensions(MERCURY)
                        .heightRangeUniform(20, 50).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Lazurite).size(1, 3))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Sodalite).size(1, 2))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Lapis).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Calcite).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition OLIVINE_VEIN_AD = create("olivine_vein_ad",
            "Mercury Olivine Vein",
            "水星橄榄石矿脉",
            vein -> {
                vein.clusterSize(30).weight(30).layer(CTNHWorldgenLayers.ADASTRA).density(0.25f).dimensions(MERCURY)
                        .heightRangeUniform(-20, 10).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Bentonite).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Magnetite).size(1, 1))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Olivine).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.GlauconiteSand).size(1, 1));
                            });
                        });
            });

    public static GTOreDefinition RUTHENIUM_AMALGAM_VEIN = create("ruthenium_amalgam_vein",
            "Ruthenium Amalgam Vein",
            "钌汞齐矿脉",
            vein -> vein.weight(50)
                    .clusterSize(40)
                    .density(0.25F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.ADASTRA)
                    .dimensions(MERCURY)
                    .heightRangeUniform(30, 80)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(CTNHMaterials.RutheniumAmalgam).size(1, 1))
                                    .layer(l -> l.weight(2).mat(CTNHMaterials.Smithsonite).size(1, 1))
                                    .layer(l -> l.weight(2).mat(CTNHMaterials.Tarkianite).size(1, 1))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.SolarFlareBlackDiamond).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.RutheniumAmalgam)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));

    public static final GTOreDefinition LUBRICANT_VEIN_AD = create("lubricant_vein_ad",
            "Mercury Talc Vein",
            "水星滑石矿脉",
            vein -> {
                vein.clusterSize(25).weight(40).layer(CTNHWorldgenLayers.ADASTRA).density(0.25f).dimensions(MERCURY)
                        .heightRangeUniform(0, 50).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Soapstone).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Talc).size(1, 1))
                                        .layer(l -> l.weight(2).mat(GTMaterials.GlauconiteSand).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Pentlandite).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition SALTPETER_VEIN_AD = create("saltpeter_vein_ad",
            "Mercury Saltpeter Vein",
            "水星粗硝石矿脉",
            vein -> {
                vein.clusterSize(30).weight(40).layer(CTNHWorldgenLayers.ADASTRA).density(0.25f).dimensions(MERCURY)
                        .heightRangeUniform(5, 45).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Saltpeter).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Diatomite).size(1, 1))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Electrotine).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Alunite).size(1, 1));
                            });
                        });
            });

    // ==================== Venus ====================

    public static final GTOreDefinition CALORITE_VEIN_AD = create("calorite_vein_ad",
            "Space Calorite Vein",
            "太空耐热金属矿脉",
            vein -> {
                vein.clusterSize(24).weight(30).layer(CTNHWorldgenLayers.ADASTRA).density(0.3f).dimensions(VENUS)
                        .heightRangeUniform(5, 40).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(AdastraMaterials.Calorite).size(2, 3))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Amethyst).size(1, 2));
                            });
                        });
            });

    public static final GTOreDefinition RUTILE_VEIN_AD = create("rutile_vein_ad",
            "Venus Rutile Vein",
            "金星金红石矿脉",
            vein -> {
                vein.clusterSize(18).weight(8).layer(CTNHWorldgenLayers.ADASTRA).density(0.4f).dimensions(VENUS)
                        .heightRangeUniform(-15, 20).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Rutile).size(1, 3))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Titanium).size(1, 2))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Bauxite).size(1, 2));
                            });
                        });
            });

    public static final GTOreDefinition IRIDIUM_VEIN_AD = create("iridium_vein_ad",
            "Venus Iridium Vein",
            "金星铱矿脉",
            vein -> {
                vein.clusterSize(24).weight(10).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f).dimensions(VENUS)
                        .heightRangeUniform(-5, 40).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(GTMaterials.Nickel).size(2, 3))
                                        .layer(l -> l.weight(1).mat(CTNHMaterials.MeteoricTroilite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(PlatinumLineMaterials.PalladiumOre).size(1, 2));
                            });
                        });
            });

    public static final GTOreDefinition PYROLUSITE_VEIN_AD = create("pyrolusite_vein_ad",
            "Venus Pyrolusite Vein",
            "金星软锰矿脉",
            vein -> {
                vein.clusterSize(24).weight(10).layer(CTNHWorldgenLayers.ADASTRA).density(0.4f).dimensions(VENUS)
                        .heightRangeUniform(0, 30).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Pyrolusite).size(2, 3))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Apatite).size(1, 2))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Tantalite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Pyrochlore).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition NAQUADAH_VEIN_AD = create("naquadah_vein_ad",
            "Venus Naquadah Vein",
            "金星硅岩矿脉",
            vein -> {
                vein.clusterSize(48).weight(30).layer(CTNHWorldgenLayers.ADASTRA).density(0.4f).dimensions(VENUS)
                        .heightRangeUniform(10, 90).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(NaquadahMaterials.NaquadahOxideMixture).size(2, 3))
                                        .layer(l -> l.weight(1).mat(NaquadahMaterials.EnrichedNaquadahOxideMixture)
                                                .size(1,
                                                        2));
                            });
                        });
            });

    public static GTOreDefinition RHODIUM_SULFUR_CRYSTAL_VEIN = create("rhodium_sulfur_crystal_vein",
            "Rhodium Sulfur Crystal Vein",
            "铑硫晶矿脉",
            vein -> vein.weight(40)
                    .clusterSize(40)
                    .density(0.25F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.ADASTRA)
                    .dimensions(VENUS)
                    .heightRangeUniform(30, 80)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(CTNHMaterials.RhodiumSulfurCrystal).size(1, 1))
                                    .layer(l -> l.weight(2).mat(CTNHMaterials.PalladiumSulfide).size(1, 1))
                                    .layer(l -> l.weight(2).mat(CTNHMaterials.Cerite).size(1, 1))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.GadoliniteSm).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.RhodiumSulfurCrystal)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));

    // ==================== Glacio ====================

    public static final GTOreDefinition OSMIUM_VEIN_AD = create("osmium_vein_ad",
            "Glacio Osmium Vein",
            "霜原星锇矿脉",
            vein -> {
                vein.clusterSize(24).weight(10).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f).dimensions(GLACIO)
                        .heightRangeUniform(-5, 30).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Nickel).size(2, 3))
                                        .layer(l -> l.weight(2).mat(CTNHMaterials.OsmiumIronSpinel).size(1, 2))
                                        .layer(l -> l.weight(1).mat(CTNHMaterials.MeteoricTroilite).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition NIOBIUM_VEIN_AD = create("niobium_vein_ad",
            "Glacio Niobium Vein",
            "霜原星铌矿脉",
            vein -> {
                vein.clusterSize(36).weight(60).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f).dimensions(GLACIO)
                        .heightRangeUniform(-50, -10).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(GTMaterials.Niobium).size(1, 2))
                                        .layer(l -> l.weight(2).mat(CTNHMaterials.MeteoricTroilite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Gallium).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition THORIUM_VEIN_AD = create("thorium_vein_ad",
            "Space Thorium Vein",
            "太空钍矿脉",
            vein -> {
                vein.clusterSize(36).weight(60).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f).dimensions(GLACIO)
                        .heightRangeUniform(-10, 30).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(GTMaterials.Thorium).size(1, 2))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Uranium235).size(1, 2))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Plutonium241).size(1, 2));
                            });
                        });
            });

    public static GTOreDefinition EUROPIUM_VEIN = create("europium_vein",
            "Europium Fluorite Vein",
            "铕萤石矿脉",
            vein -> vein.weight(30)
                    .clusterSize(40)
                    .density(0.25F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.ADASTRA)
                    .dimensions(GLACIO)
                    .heightRangeUniform(30, 80)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.Germanite).size(1, 1))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.Roquesite).size(1, 1))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.Yttrofluorite).size(1, 1))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.EuropiumFluorite).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.EuropiumFluorite)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));

    // ==================== Cross-planet ====================

    public static GTOreDefinition ZIRKELITE_VEIN = create("zirkelite_vein",
            "Zirkelite Vein",
            "钛锆钍石矿脉",
            vein -> vein.weight(60)
                    .clusterSize(40)
                    .density(0.25F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.ADASTRA)
                    .dimensions(MARS, VENUS, MERCURY)
                    .heightRangeUniform(30, 80)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(CTNHMaterials.Zirkelite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Thorium).size(1, 1))
                                    .layer(l -> l.weight(2).mat(CTNHMaterials.Zircon).size(1, 1))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Ilmenite).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.Zirkelite)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));

    public static final GTOreDefinition MONAZITE_VEIN_N = create("monazite_vein_n",
            "Moon Monazite Vein",
            "月球独居石矿脉",
            vein -> {
                vein.clusterSize(24).weight(30).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f)
                        .dimensions(MOON, VENUS, GLACIO).heightRangeUniform(20, 40)
                        .discardChanceOnAirExposure(0f).layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Bastnasite).size(2, 4))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Monazite).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Neodymium).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition QUARTZITE_VEIN = create("quartzite_vein",
            "Quartzite Vein",
            "石英岩矿脉",
            vein -> {
                vein.clusterSize(24).weight(20).layer(CTNHWorldgenLayers.ADASTRA).density(0.3f)
                        .dimensions(MOON, MARS, VENUS).heightRangeUniform(30, 80)
                        .discardChanceOnAirExposure(0f).layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Quartzite).size(2, 4))
                                        .layer(l -> l.weight(3).mat(GTMaterials.Barite).size(2, 4))
                                        .layer(l -> l.weight(3).mat(GTMaterials.CertusQuartz).size(2, 4));
                            });
                        });
            });

    public static final GTOreDefinition MOLYBDENUM_VEIN_AD = create("molybdenum_vein_ad",
            "Space Molybdenite Vein",
            "太空辉钼矿脉",
            vein -> {
                vein.clusterSize(25).weight(5).layer(CTNHWorldgenLayers.ADASTRA).density(0.25f)
                        .dimensions(MOON, MERCURY).heightRangeUniform(20, 50).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Wulfenite).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Molybdenite).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Molybdenum).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Powellite).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition GALENA_VEIN_AD = create("galena_vein_ad",
            "Space Galena Vein",
            "太空方铅矿脉",
            vein -> {
                vein.clusterSize(30).weight(40).layer(CTNHWorldgenLayers.ADASTRA).density(0.25f)
                        .dimensions(MOON, MARS, VENUS, GLACIO).heightRangeUniform(-15, 45)
                        .discardChanceOnAirExposure(0f).layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Galena).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Silver).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Lead).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition COPPER_VEIN_AD = create("copper_vein_ad",
            "Space Copper Vein",
            "太空铜矿脉",
            vein -> {
                vein.clusterSize(36).weight(80).layer(CTNHWorldgenLayers.ADASTRA).density(0.3f)
                        .dimensions(MOON, MERCURY).heightRangeUniform(-40, 15).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(GTMaterials.Chalcopyrite).size(2, 3))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Iron).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Pyrite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Copper).size(1, 2));
                            });
                        });
            });

    public static final GTOreDefinition CASSITERITE_VEIN_AD = create("cassiterite_vein_ad",
            "Space Cassiterite Vein",
            "太空锡石矿脉",
            vein -> {
                vein.clusterSize(36).weight(50).layer(CTNHWorldgenLayers.ADASTRA).density(0.4f)
                        .dimensions(MOON, VENUS).heightRangeUniform(10, 80).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Tin).size(2, 3))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Cassiterite).size(1, 2));
                            });
                        });
            });

    public static final GTOreDefinition PITCHBLENDE_VEIN_AD = create("pitchblende_vein_ad",
            "Space Pitchblende Vein",
            "太空沥青铀矿脉",
            vein -> {
                vein.clusterSize(24).weight(40).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f)
                        .dimensions(MARS, VENUS).heightRangeUniform(20, 60).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(GTMaterials.Pitchblende).size(1, 2))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Uraninite).size(1, 2));
                            });
                        });
            });

    public static final GTOreDefinition TUFF_URANINITE_VEIN_AD = create("tuff_uraninite_vein_ad",
            "Space Tuff Uraninite Vein",
            "太空晶质铀矿脉",
            vein -> {
                vein.clusterSize(24).weight(20).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f)
                        .dimensions(MARS, MERCURY).heightRangeUniform(20, 30).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(GTMaterials.Uraninite).size(1, 2))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Pitchblende).size(1, 2));
                            });
                        });
            });

    public static final GTOreDefinition MANGANESE_VEIN_AD = create("manganese_vein_ad",
            "Space Manganese Vein",
            "太空锰矿脉",
            vein -> {
                vein.clusterSize(24).weight(20).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f)
                        .dimensions(MERCURY, GLACIO).heightRangeUniform(-30, 0).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(GTMaterials.Grossular).size(1, 2))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Spessartine).size(1, 2))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Pyrolusite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Tantalite).size(1, 2));
                            });
                        });
            });

    public static final GTOreDefinition SCHEELITE_VEIN_AD = create("scheelite_vein_ad",
            "Space Scheelite Vein",
            "太空白钨矿脉",
            vein -> {
                vein.clusterSize(24).weight(16).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f)
                        .dimensions(MARS, GLACIO).heightRangeUniform(20, 60).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Scheelite).size(2, 4))
                                        .layer(l -> l.weight(3).mat(GTMaterials.Tungstate).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Lithium).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition SULFUR_VEIN_AD = create("sulfur_vein_ad",
            "Space Sulfur Vein",
            "太空硫矿脉",
            vein -> {
                vein.clusterSize(30).weight(100).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f)
                        .dimensions(MARS, VENUS).heightRangeUniform(10, 30).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Sulfur).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Pyrite).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Sphalerite).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition REDSTONE_VEIN_AD = create("redstone_vein_ad",
            "Space Redstone Vein",
            "太空红石矿脉",
            vein -> {
                vein.clusterSize(30).weight(60).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f)
                        .dimensions(MARS, VENUS).heightRangeUniform(5, 40).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Redstone).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Ruby).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Cinnabar).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition NICKEL_VEIN_AD = create("nickel_vein_ad",
            "Space Nickel Vein",
            "太空镍矿脉",
            vein -> {
                vein.clusterSize(30).weight(40).layer(CTNHWorldgenLayers.ADASTRA).density(0.25f)
                        .dimensions(MARS, VENUS, GLACIO).heightRangeUniform(-10, 60)
                        .discardChanceOnAirExposure(0f).layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Garnierite).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Nickel).size(1, 1))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Cobaltite).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Pentlandite).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition MAGNETITE_VEIN_AD = create("magnetite_vein_ad",
            "Space Magnetite Vein",
            "太空磁铁矿脉",
            vein -> {
                vein.clusterSize(35).weight(80).layer(CTNHWorldgenLayers.ADASTRA).density(0.15f)
                        .dimensions(MARS, MERCURY, GLACIO).heightRangeUniform(10, 60)
                        .discardChanceOnAirExposure(0f).layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Magnetite).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.VanadiumMagnetite).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Gold).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition IRON_VEIN_AD = create("iron_vein_ad",
            "Space Banded Iron Vein",
            "太空带状铁矿脉",
            vein -> {
                vein.clusterSize(36).weight(120).layer(CTNHWorldgenLayers.ADASTRA).density(0.3f)
                        .dimensions(MARS, MERCURY).heightRangeUniform(-10, 60).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(5).mat(GTMaterials.Goethite).size(1, 3))
                                        .layer(l -> l.weight(2).mat(GTMaterials.YellowLimonite).size(1, 2))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Hematite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Malachite).size(1, 2));
                            });
                        });
            });

    public static final GTOreDefinition BERYLLIUM_VEIN_AD = create("beryllium_vein_ad",
            "Space Beryllium Vein",
            "太空铍矿脉",
            vein -> {
                vein.clusterSize(24).weight(30).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f)
                        .dimensions(MARS, MERCURY, VENUS).heightRangeUniform(5, 30)
                        .discardChanceOnAirExposure(0f).layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Beryllium).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Emerald).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Thorium).size(1, 1));
                            });
                        });
            });

    public static final GTOreDefinition TETRAHEDRITE_VEIN_AD = create("tetrahedrite_vein_ad",
            "Space Tetrahedrite Vein",
            "太空黝铜矿脉",
            vein -> {
                vein.clusterSize(36).weight(70).layer(CTNHWorldgenLayers.ADASTRA).density(0.3f)
                        .dimensions(MARS, VENUS).heightRangeUniform(80, 120).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(4).mat(GTMaterials.Tetrahedrite).size(2, 3))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Copper).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Stibnite).size(1, 1));
                            });
                        });
            });

    public static void init() {}
}
