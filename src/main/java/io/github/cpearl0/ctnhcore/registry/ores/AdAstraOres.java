package io.github.cpearl0.ctnhcore.registry.ores;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.AdastraMaterials;
import io.github.cpearl0.ctnhcore.data.materials.NaquadahMaterials;
import io.github.cpearl0.ctnhcore.data.materials.PlatinumLineMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHWorldgenLayers;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import static com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE;
import static com.gregtechceu.gtceu.common.data.GTOres.create;
import static io.github.cpearl0.ctnhcore.registry.CTNHWorlds.*;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.*;

public class AdAstraOres {

    // ==================== Moon ====================

    @Key("ctnhcore:sheldonite_vein_moon")
    @CN("月球谢尔顿矿脉")
    @EN("Moon Sheldonite Vein")
    public static Lang ctnhSheldoniteVeinMoon;

    @Key("gtceu.jei.ore_vein.sheldonite_vein_moon")
    @CN("月球谢尔顿矿脉")
    @EN("Moon Sheldonite Vein")
    public static Lang gtceuSheldoniteVeinMoon;

    public static GTOreDefinition SHELDONITE_VEIN_MOON = create(CTNHCore.id("sheldonite_vein_moon"),
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

    @Key("ctnhcore:phosphate_vein")
    @CN("磷酸盐矿脉")
    @EN("Phosphate Vein")
    public static Lang ctnhPhosphateVein;

    @Key("gtceu.jei.ore_vein.phosphate_vein")
    @CN("磷酸盐矿脉")
    @EN("Phosphate Vein")
    public static Lang gtceuPhosphateVein;

    public static GTOreDefinition PHOSPHATE_VEIN = create(CTNHCore.id("phosphate_vein"), vein -> vein.weight(40)
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

    @Key("bauxite_vein")
    @CN("铝土矿脉")
    @EN("Bauxite Vein")
    public static Lang bauxiteVein;

    @Key("ctnhcore:bauxite_vein")
    @CN("月球铝土矿脉")
    @EN("Moon Bauxite Vein")
    public static Lang ctnhBauxiteVein;

    @Key("gtceu.jei.ore_vein.bauxite_vein")
    @CN("月球铝土矿脉")
    @EN("Moon Bauxite Vein")
    public static Lang gtceuBauxiteVein;

    public static final GTOreDefinition BAUXITE_VEIN = create(
            CTNHCore.id("bauxite_vein"), vein -> {
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

    @Key("ilmenite_vein")
    @CN("钛铁矿脉")
    @EN("Ilmenite Vein")
    public static Lang ilmeniteVein;

    @Key("ctnhcore:ilmenite_vein")
    @CN("钛铁矿脉")
    @EN("Ilmenite Vein")
    public static Lang ctnhIlmeniteVein;

    @Key("gtceu.jei.ore_vein.ilmenite_vein")
    @CN("钛铁矿脉")
    @EN("Ilmenite Vein")
    public static Lang gtceuIlmeniteVein;

    public static final GTOreDefinition ILMENITE_VEIN = create(
            CTNHCore.id("ilmenite_vein"), vein -> {
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

    @Key("desh_vein_ad")
    @CN("戴斯矿脉")
    @EN("Ad Astra Desh Vein")
    public static Lang deshVeinAd;

    @Key("ctnhcore:desh_vein_ad")
    @CN("月球戴斯矿脉")
    @EN("Moon Desh Vein")
    public static Lang ctnhDeshVeinAd;

    @Key("gtceu.jei.ore_vein.desh_vein_ad")
    @CN("月球戴斯矿脉")
    @EN("Moon Desh Vein")
    public static Lang gtceuDeshVeinAd;

    public static final GTOreDefinition DESH_VEIN_AD = create(
            CTNHCore.id("desh_vein_ad"), vein -> {
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

    @Key("ostrum_vein_ad")
    @CN("紫金矿脉")
    @EN("Ad Astra Ostrum Vein")
    public static Lang ostrumVeinAd;

    @Key("ctnhcore:ostrum_vein_ad")
    @CN("火星紫金矿脉")
    @EN("Mars Ostrum Vein")
    public static Lang ctnhOstrumVeinAd;

    @Key("gtceu.jei.ore_vein.ostrum_vein_ad")
    @CN("火星紫金矿脉")
    @EN("Mars Ostrum Vein")
    public static Lang gtceuOstrumVeinAd;

    public static final GTOreDefinition OSTRUM_VEIN_AD = create(
            CTNHCore.id("ostrum_vein_ad"), vein -> {
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

    @Key("arsenic_vein_ad")
    @CN("砷矿脉")
    @EN("Ad Astra Arsenic Vein")
    public static Lang arsenicVeinAd;

    @Key("ctnhcore:arsenic_vein_ad")
    @CN("太空砷矿脉")
    @EN("Space Arsenic Vein")
    public static Lang ctnhArsenicVeinAd;

    @Key("gtceu.jei.ore_vein.arsenic_vein_ad")
    @CN("太空砷矿脉")
    @EN("Space Arsenic Vein")
    public static Lang gtceuArsenicVeinAd;

    public static final GTOreDefinition ARSENIC_VEIN_AD = create(
            CTNHCore.id("arsenic_vein_ad"), vein -> {
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

    @Key("salts_vein_ad")
    @CN("盐矿脉")
    @EN("Ad Astra Salts Vein")
    public static Lang saltsVeinAd;

    @Key("ctnhcore:salts_vein_ad")
    @CN("火星盐矿脉")
    @EN("Mars Salts Vein")
    public static Lang ctnhSaltsVeinAd;

    @Key("gtceu.jei.ore_vein.salts_vein_ad")
    @CN("火星盐矿脉")
    @EN("Mars Salts Vein")
    public static Lang gtceuSaltsVeinAd;

    public static final GTOreDefinition SALTS_VEIN_AD = create(
            CTNHCore.id("salts_vein_ad"), vein -> {
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

    @Key("naquadah_vein_ad_mars")
    @CN("硅岩矿脉")
    @EN("Mars Naquadah Vein")
    public static Lang naquadahVeinAdMars;

    @Key("ctnhcore:naquadah_vein_ad_mars")
    @CN("火星硅岩矿脉")
    @EN("Mars Naquadah Vein")
    public static Lang ctnhNaquadahVeinAdMars;

    @Key("gtceu.jei.ore_vein.naquadah_vein_ad_mars")
    @CN("火星硅岩矿脉")
    @EN("Mars Naquadah Vein")
    public static Lang gtceuNaquadahVeinAdMars;

    public static final GTOreDefinition NAQUADAH_VEIN_AD_MARS = create(
            CTNHCore.id("naquadah_vein_ad_mars"), vein -> {
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

    @Key("chromium_vein_ad")
    @CN("铬矿脉")
    @EN("Ad Astra Chromium Vein")
    public static Lang chromiumVeinAd;

    @Key("ctnhcore:chromium_vein_ad")
    @CN("水星铬矿脉")
    @EN("Mercury Chromium Vein")
    public static Lang ctnhChromiumVeinAd;

    @Key("gtceu.jei.ore_vein.chromium_vein_ad")
    @CN("水星铬矿脉")
    @EN("Mercury Chromium Vein")
    public static Lang gtceuChromiumVeinAd;

    public static final GTOreDefinition CHROMIUM_VEIN_AD = create(
            CTNHCore.id("chromium_vein_ad"), vein -> {
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

    @Key("uranium238_vein_ad")
    @CN("铀238矿脉")
    @EN("Ad Astra Uranium-238 Vein")
    public static Lang uranium238VeinAd;

    @Key("ctnhcore:uranium238_vein_ad")
    @CN("水星铀238矿脉")
    @EN("Mercury Uranium-238 Vein")
    public static Lang ctnhUranium238VeinAd;

    @Key("gtceu.jei.ore_vein.uranium238_vein_ad")
    @CN("水星铀238矿脉")
    @EN("Mercury Uranium-238 Vein")
    public static Lang gtceuUranium238VeinAd;

    public static final GTOreDefinition URANIUM238_VEIN_AD = create(
            CTNHCore.id("uranium238_vein_ad"), vein -> {
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

    @Key("magnesite_vein_ad")
    @CN("菱镁矿脉")
    @EN("Ad Astra Magnesite Vein")
    public static Lang magnesiteVeinAd;

    @Key("ctnhcore:magnesite_vein_ad")
    @CN("水星菱镁矿脉")
    @EN("Mercury Magnesite Vein")
    public static Lang ctnhMagnesiteVeinAd;

    @Key("gtceu.jei.ore_vein.magnesite_vein_ad")
    @CN("水星菱镁矿脉")
    @EN("Mercury Magnesite Vein")
    public static Lang gtceuMagnesiteVeinAd;

    public static final GTOreDefinition MAGNESITE_VEIN_AD = create(
            CTNHCore.id("magnesite_vein_ad"), vein -> {
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

    @Key("platinum_vein_ad")
    @CN("铂矿脉")
    @EN("Ad Astra Platinum Vein")
    public static Lang platinumVeinAd;

    @Key("ctnhcore:platinum_vein_ad")
    @CN("太空铂矿脉")
    @EN("Space Platinum Vein")
    public static Lang ctnhPlatinumVeinAd;

    @Key("gtceu.jei.ore_vein.platinum_vein_ad")
    @CN("太空铂矿脉")
    @EN("Space Platinum Vein")
    public static Lang gtceuPlatinumVeinAd;

    public static final GTOreDefinition PLATINUM_VEIN_AD = create(
            CTNHCore.id("platinum_vein_ad"), vein -> {
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

    @Key("lapis_vein_ad")
    @CN("青金石矿脉")
    @EN("Ad Astra Lapis Lazuli Vein")
    public static Lang lapisVeinAd;

    public static final GTOreDefinition LAPIS_VEIN_AD = create(
            CTNHCore.id("lapis_vein_ad"), vein -> {
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

    @Key("olivine_vein_ad")
    @CN("橄榄石矿脉")
    @EN("Ad Astra Olivine Vein")
    public static Lang olivineVeinAd;

    @Key("ctnhcore:olivine_vein_ad")
    @CN("水星橄榄石矿脉")
    @EN("Mercury Olivine Vein")
    public static Lang ctnhOlivineVeinAd;

    @Key("gtceu.jei.ore_vein.olivine_vein_ad")
    @CN("水星橄榄石矿脉")
    @EN("Mercury Olivine Vein")
    public static Lang gtceuOlivineVeinAd;

    public static final GTOreDefinition OLIVINE_VEIN_AD = create(
            CTNHCore.id("olivine_vein_ad"), vein -> {
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

    @Key("ctnhcore:ruthenium_amalgam_vein")
    @CN("钌汞齐矿脉")
    @EN("Ruthenium Amalgam Vein")
    public static Lang ctnhRutheniumAmalgamVein;

    @Key("gtceu.jei.ore_vein.ruthenium_amalgam_vein")
    @CN("钌汞齐矿脉")
    @EN("Ruthenium Amalgam Vein")
    public static Lang gtceuRutheniumAmalgamVein;

    public static GTOreDefinition RUTHENIUM_AMALGAM_VEIN = create(CTNHCore.id("ruthenium_amalgam_vein"),
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

    @Key("lubricant_vein_ad")
    @CN("皂石矿脉")
    @EN("Ad Astra Soapstone Vein")
    public static Lang lubricantVeinAd;

    @Key("ctnhcore:lubricant_vein_ad")
    @CN("水星滑石矿脉")
    @EN("Mercury Talc Vein")
    public static Lang ctnhLubricantVeinAd;

    @Key("gtceu.jei.ore_vein.lubricant_vein_ad")
    @CN("水星滑石矿脉")
    @EN("Mercury Talc Vein")
    public static Lang gtceuLubricantVeinAd;

    public static final GTOreDefinition LUBRICANT_VEIN_AD = create(
            CTNHCore.id("lubricant_vein_ad"), vein -> {
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

    @Key("saltpeter_vein_ad")
    @CN("蓝石矿脉")
    @EN("Ad Astra Saltpeter Vein")
    public static Lang saltpeterVeinAd;

    @Key("ctnhcore:saltpeter_vein_ad")
    @CN("水星粗硝石矿脉")
    @EN("Mercury Saltpeter Vein")
    public static Lang ctnhSaltpeterVeinAd;

    @Key("gtceu.jei.ore_vein.saltpeter_vein_ad")
    @CN("水星粗硝石矿脉")
    @EN("Mercury Saltpeter Vein")
    public static Lang gtceuSaltpeterVeinAd;

    public static final GTOreDefinition SALTPETER_VEIN_AD = create(
            CTNHCore.id("saltpeter_vein_ad"), vein -> {
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

    @Key("calorite_vein_ad")
    @CN("耐热合金矿脉")
    @EN("Ad Astra Calorite Vein")
    public static Lang caloriteVeinAd;

    @Key("ctnhcore:calorite_vein_ad")
    @CN("太空耐热金属矿脉")
    @EN("Space Calorite Vein")
    public static Lang ctnhCaloriteVeinAd;

    @Key("gtceu.jei.ore_vein.calorite_vein_ad")
    @CN("太空耐热金属矿脉")
    @EN("Space Calorite Vein")
    public static Lang gtceuCaloriteVeinAd;

    public static final GTOreDefinition CALORITE_VEIN_AD = create(
            CTNHCore.id("calorite_vein_ad"), vein -> {
                vein.clusterSize(24).weight(30).layer(CTNHWorldgenLayers.ADASTRA).density(0.3f).dimensions(VENUS)
                        .heightRangeUniform(5, 40).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(AdastraMaterials.Calorite).size(2, 3))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Amethyst).size(1, 2));
                            });
                        });
            });

    @Key("rutile_vein_ad")
    @CN("金红石矿脉")
    @EN("Ad Astra Rutile Vein")
    public static Lang rutileVeinAd;

    @Key("ctnhcore:rutile_vein_ad")
    @CN("金星金红石矿脉")
    @EN("Venus Rutile Vein")
    public static Lang ctnhRutileVeinAd;

    @Key("gtceu.jei.ore_vein.rutile_vein_ad")
    @CN("金星金红石矿脉")
    @EN("Venus Rutile Vein")
    public static Lang gtceuRutileVeinAd;

    public static final GTOreDefinition RUTILE_VEIN_AD = create(
            CTNHCore.id("rutile_vein_ad"), vein -> {
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

    @Key("iridium_vein_ad")
    @CN("铱矿脉")
    @EN("Ad Astra Iridium Vein")
    public static Lang iridiumVeinAd;

    @Key("ctnhcore:iridium_vein_ad")
    @CN("金星铱矿脉")
    @EN("Venus Iridium Vein")
    public static Lang ctnhIridiumVeinAd;

    @Key("gtceu.jei.ore_vein.iridium_vein_ad")
    @CN("金星铱矿脉")
    @EN("Venus Iridium Vein")
    public static Lang gtceuIridiumVeinAd;

    public static final GTOreDefinition IRIDIUM_VEIN_AD = create(
            CTNHCore.id("iridium_vein_ad"), vein -> {
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

    @Key("pyrolusite_vein_ad")
    @CN("软锰矿脉")
    @EN("Ad Astra Pyrolusite Vein")
    public static Lang pyrolusiteVeinAd;

    @Key("ctnhcore:pyrolusite_vein_ad")
    @CN("金星软锰矿脉")
    @EN("Venus Pyrolusite Vein")
    public static Lang ctnhPyrolusiteVeinAd;

    @Key("gtceu.jei.ore_vein.pyrolusite_vein_ad")
    @CN("金星软锰矿脉")
    @EN("Venus Pyrolusite Vein")
    public static Lang gtceuPyrolusiteVeinAd;

    public static final GTOreDefinition PYROLUSITE_VEIN_AD = create(
            CTNHCore.id("pyrolusite_vein_ad"), vein -> {
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

    @Key("naquadah_vein_ad")
    @CN("硅岩矿脉")
    @EN("Ad Astra Naquadah Vein")
    public static Lang naquadahVeinAd;

    @Key("ctnhcore:naquadah_vein_ad")
    @CN("金星硅岩矿脉")
    @EN("Venus Naquadah Vein")
    public static Lang ctnhNaquadahVeinAd;

    @Key("gtceu.jei.ore_vein.naquadah_vein_ad")
    @CN("金星硅岩矿脉")
    @EN("Venus Naquadah Vein")
    public static Lang gtceuNaquadahVeinAd;

    public static final GTOreDefinition NAQUADAH_VEIN_AD = create(
            CTNHCore.id("naquadah_vein_ad"), vein -> {
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

    @Key("ctnhcore:rhodium_sulfur_crystal_vein")
    @CN("铑硫晶矿脉")
    @EN("Rhodium Sulfur Crystal Vein")
    public static Lang ctnhRhodiumSulfurCrystalVein;

    @Key("gtceu.jei.ore_vein.rhodium_sulfur_crystal_vein")
    @CN("铑硫晶矿脉")
    @EN("Rhodium Sulfur Crystal Vein")
    public static Lang gtceuRhodiumSulfurCrystalVein;

    public static GTOreDefinition RHODIUM_SULFUR_CRYSTAL_VEIN = create(CTNHCore.id("rhodium_sulfur_crystal_vein"),
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

    @Key("osmium_vein_ad")
    @CN("锇矿脉")
    @EN("Ad Astra Osmium Vein")
    public static Lang osmiumVeinAd;

    @Key("ctnhcore:osmium_vein_ad")
    @CN("霜原星锇矿脉")
    @EN("Glacio Osmium Vein")
    public static Lang ctnhOsmiumVeinAd;

    @Key("gtceu.jei.ore_vein.osmium_vein_ad")
    @CN("霜原星锇矿脉")
    @EN("Glacio Osmium Vein")
    public static Lang gtceuOsmiumVeinAd;

    public static final GTOreDefinition OSMIUM_VEIN_AD = create(
            CTNHCore.id("osmium_vein_ad"), vein -> {
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

    @Key("niobium_vein_ad")
    @CN("铌矿脉")
    @EN("Ad Astra Niobium Vein")
    public static Lang niobiumVeinAd;

    @Key("ctnhcore:niobium_vein_ad")
    @CN("霜原星铌矿脉")
    @EN("Glacio Niobium Vein")
    public static Lang ctnhNiobiumVeinAd;

    @Key("gtceu.jei.ore_vein.niobium_vein_ad")
    @CN("霜原星铌矿脉")
    @EN("Glacio Niobium Vein")
    public static Lang gtceuNiobiumVeinAd;

    public static final GTOreDefinition NIOBIUM_VEIN_AD = create(
            CTNHCore.id("niobium_vein_ad"), vein -> {
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

    @Key("ctnhcore:thorium_vein_ad")
    @CN("太空钍矿脉")
    @EN("Space Thorium Vein")
    public static Lang ctnhThoriumVeinAd;

    @Key("gtceu.jei.ore_vein.thorium_vein_ad")
    @CN("太空钍矿脉")
    @EN("Space Thorium Vein")
    public static Lang gtceuThoriumVeinAd;

    public static final GTOreDefinition THORIUM_VEIN_AD = create(
            CTNHCore.id("thorium_vein_ad"), vein -> {
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

    @Key("ctnhcore:europium_vein")
    @CN("铕萤石矿脉")
    @EN("Europium Fluorite Vein")
    public static Lang ctnhEuropiumVein;

    @Key("gtceu.jei.ore_vein.europium_vein")
    @CN("铕萤石矿脉")
    @EN("Europium Fluorite Vein")
    public static Lang gtceuEuropiumVein;

    public static GTOreDefinition EUROPIUM_VEIN = create(CTNHCore.id("europium_vein"), vein -> vein.weight(30)
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

    @Key("ctnhcore:zirkelite_vein")
    @CN("钛锆钍石矿脉")
    @EN("Zirkelite Vein")
    public static Lang ctnhZirkeliteVein;

    @Key("gtceu.jei.ore_vein.zirkelite_vein")
    @CN("钛锆钍石矿脉")
    @EN("Zirkelite Vein")
    public static Lang gtceuZirkeliteVein;

    public static GTOreDefinition ZIRKELITE_VEIN = create(CTNHCore.id("zirkelite_vein"), vein -> vein.weight(60)
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

    @Key("monazite_vein_n")
    @CN("独居石矿脉")
    @EN("Moon Monazite Vein")
    public static Lang monaziteVeinN;

    @Key("ctnhcore:monazite_vein_n")
    @CN("月球独居石矿脉")
    @EN("Moon Monazite Vein")
    public static Lang ctnhMonaziteVeinN;

    @Key("gtceu.jei.ore_vein.monazite_vein_n")
    @CN("月球独居石矿脉")
    @EN("Moon Monazite Vein")
    public static Lang gtceuMonaziteVeinN;

    public static final GTOreDefinition MONAZITE_VEIN_N = create(
            CTNHCore.id("monazite_vein_n"), vein -> {
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

    @Key("quartzite_vein")
    @CN("石英岩矿脉")
    @EN("Quartzite Vein")
    public static Lang quartziteVein;

    @Key("ctnhcore:quartzite_vein")
    @CN("石英岩矿脉")
    @EN("Quartzite Vein")
    public static Lang ctnhQuartziteVein;

    @Key("gtceu.jei.ore_vein.quartzite_vein")
    @CN("石英岩矿脉")
    @EN("Quartzite Vein")
    public static Lang gtceuQuartziteVein;

    public static final GTOreDefinition QUARTZITE_VEIN = create(
            CTNHCore.id("quartzite_vein"), vein -> {
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

    @Key("molybdenum_vein_ad")
    @CN("钼矿脉")
    @EN("Ad Astra Molybdenum Vein")
    public static Lang molybdenumVeinAd;

    @Key("ctnhcore:molybdenum_vein_ad")
    @CN("太空辉钼矿脉")
    @EN("Space Molybdenite Vein")
    public static Lang ctnhMolybdenumVeinAd;

    @Key("gtceu.jei.ore_vein.molybdenum_vein_ad")
    @CN("太空辉钼矿脉")
    @EN("Space Molybdenite Vein")
    public static Lang gtceuMolybdenumVeinAd;

    public static final GTOreDefinition MOLYBDENUM_VEIN_AD = create(
            CTNHCore.id("molybdenum_vein_ad"), vein -> {
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

    @Key("galena_vein_ad")
    @CN("方铅矿脉")
    @EN("Ad Astra Galena Vein")
    public static Lang galenaVeinAd;

    @Key("ctnhcore:galena_vein_ad")
    @CN("太空方铅矿脉")
    @EN("Space Galena Vein")
    public static Lang ctnhGalenaVeinAd;

    @Key("gtceu.jei.ore_vein.galena_vein_ad")
    @CN("太空方铅矿脉")
    @EN("Space Galena Vein")
    public static Lang gtceuGalenaVeinAd;

    public static final GTOreDefinition GALENA_VEIN_AD = create(
            CTNHCore.id("galena_vein_ad"), vein -> {
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

    @Key("copper_vein_ad")
    @CN("铜矿脉")
    @EN("Ad Astra Copper Vein")
    public static Lang copperVeinAd;

    @Key("ctnhcore:copper_vein_ad")
    @CN("太空铜矿脉")
    @EN("Space Copper Vein")
    public static Lang ctnhCopperVeinAd;

    @Key("gtceu.jei.ore_vein.copper_vein_ad")
    @CN("太空铜矿脉")
    @EN("Space Copper Vein")
    public static Lang gtceuCopperVeinAd;

    public static final GTOreDefinition COPPER_VEIN_AD = create(
            CTNHCore.id("copper_vein_ad"), vein -> {
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

    @Key("cassiterite_vein_ad")
    @CN("锡石矿脉")
    @EN("Ad Astra Cassiterite Vein")
    public static Lang cassiteriteVeinAd;

    @Key("ctnhcore:cassiterite_vein_ad")
    @CN("太空锡石矿脉")
    @EN("Space Cassiterite Vein")
    public static Lang ctnhCassiteriteVeinAd;

    @Key("gtceu.jei.ore_vein.cassiterite_vein_ad")
    @CN("太空锡石矿脉")
    @EN("Space Cassiterite Vein")
    public static Lang gtceuCassiteriteVeinAd;

    public static final GTOreDefinition CASSITERITE_VEIN_AD = create(
            CTNHCore.id("cassiterite_vein_ad"), vein -> {
                vein.clusterSize(36).weight(50).layer(CTNHWorldgenLayers.ADASTRA).density(0.4f)
                        .dimensions(MOON, VENUS).heightRangeUniform(10, 80).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Tin).size(2, 3))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Cassiterite).size(1, 2));
                            });
                        });
            });

    @Key("pitchblende_vein_ad")
    @CN("沥青铀矿脉")
    @EN("Ad Astra Pitchblende Vein")
    public static Lang pitchblendeVeinAd;

    @Key("ctnhcore:pitchblende_vein_ad")
    @CN("太空沥青铀矿脉")
    @EN("Space Pitchblende Vein")
    public static Lang ctnhPitchblendeVeinAd;

    @Key("gtceu.jei.ore_vein.pitchblende_vein_ad")
    @CN("太空沥青铀矿脉")
    @EN("Space Pitchblende Vein")
    public static Lang gtceuPitchblendeVeinAd;

    public static final GTOreDefinition PITCHBLENDE_VEIN_AD = create(
            CTNHCore.id("pitchblende_vein_ad"), vein -> {
                vein.clusterSize(24).weight(40).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f)
                        .dimensions(MARS, VENUS).heightRangeUniform(20, 60).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(GTMaterials.Pitchblende).size(1, 2))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Uraninite).size(1, 2));
                            });
                        });
            });

    @Key("tuff_uraninite_vein_ad")
    @CN("晶质铀矿脉")
    @EN("Ad Astra Tuff Uraninite Vein")
    public static Lang tuffUraniniteVeinAd;

    @Key("ctnhcore:tuff_uraninite_vein_ad")
    @CN("太空晶质铀矿脉")
    @EN("Space Tuff Uraninite Vein")
    public static Lang ctnhTuffUraniniteVeinAd;

    @Key("gtceu.jei.ore_vein.tuff_uraninite_vein_ad")
    @CN("太空晶质铀矿脉")
    @EN("Space Tuff Uraninite Vein")
    public static Lang gtceuTuffUraniniteVeinAd;

    public static final GTOreDefinition TUFF_URANINITE_VEIN_AD = create(
            CTNHCore.id("tuff_uraninite_vein_ad"), vein -> {
                vein.clusterSize(24).weight(20).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f)
                        .dimensions(MARS, MERCURY).heightRangeUniform(20, 30).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(GTMaterials.Uraninite).size(1, 2))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Pitchblende).size(1, 2));
                            });
                        });
            });

    @Key("manganese_vein_ad")
    @CN("锰矿脉")
    @EN("Ad Astra Manganese Vein")
    public static Lang manganeseVeinAd;

    @Key("ctnhcore:manganese_vein_ad")
    @CN("太空锰矿脉")
    @EN("Space Manganese Vein")
    public static Lang ctnhManganeseVeinAd;

    @Key("gtceu.jei.ore_vein.manganese_vein_ad")
    @CN("太空锰矿脉")
    @EN("Space Manganese Vein")
    public static Lang gtceuManganeseVeinAd;

    public static final GTOreDefinition MANGANESE_VEIN_AD = create(
            CTNHCore.id("manganese_vein_ad"), vein -> {
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

    @Key("scheelite_vein_ad")
    @CN("白钨矿脉")
    @EN("Ad Astra Scheelite Vein")
    public static Lang scheeliteVeinAd;

    @Key("ctnhcore:scheelite_vein_ad")
    @CN("太空白钨矿脉")
    @EN("Space Scheelite Vein")
    public static Lang ctnhScheeliteVeinAd;

    @Key("gtceu.jei.ore_vein.scheelite_vein_ad")
    @CN("太空白钨矿脉")
    @EN("Space Scheelite Vein")
    public static Lang gtceuScheeliteVeinAd;

    public static final GTOreDefinition SCHEELITE_VEIN_AD = create(
            CTNHCore.id("scheelite_vein_ad"), vein -> {
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

    @Key("sulfur_vein_ad")
    @CN("硫矿脉")
    @EN("Ad Astra Sulfur Vein")
    public static Lang sulfurVeinAd;

    @Key("ctnhcore:sulfur_vein_ad")
    @CN("太空硫矿脉")
    @EN("Space Sulfur Vein")
    public static Lang ctnhSulfurVeinAd;

    @Key("gtceu.jei.ore_vein.sulfur_vein_ad")
    @CN("太空硫矿脉")
    @EN("Space Sulfur Vein")
    public static Lang gtceuSulfurVeinAd;

    public static final GTOreDefinition SULFUR_VEIN_AD = create(
            CTNHCore.id("sulfur_vein_ad"), vein -> {
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

    @Key("redstone_vein_ad")
    @CN("红石矿脉")
    @EN("Ad Astra Redstone Vein")
    public static Lang redstoneVeinAd;

    @Key("ctnhcore:redstone_vein_ad")
    @CN("太空红石矿脉")
    @EN("Space Redstone Vein")
    public static Lang ctnhRedstoneVeinAd;

    @Key("gtceu.jei.ore_vein.redstone_vein_ad")
    @CN("太空红石矿脉")
    @EN("Space Redstone Vein")
    public static Lang gtceuRedstoneVeinAd;

    public static final GTOreDefinition REDSTONE_VEIN_AD = create(
            CTNHCore.id("redstone_vein_ad"), vein -> {
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

    @Key("nickel_vein_ad")
    @CN("镍矿脉")
    @EN("Ad Astra Nickel Vein")
    public static Lang nickelVeinAd;

    @Key("ctnhcore:nickel_vein_ad")
    @CN("太空镍矿脉")
    @EN("Space Nickel Vein")
    public static Lang ctnhNickelVeinAd;

    @Key("gtceu.jei.ore_vein.nickel_vein_ad")
    @CN("太空镍矿脉")
    @EN("Space Nickel Vein")
    public static Lang gtceuNickelVeinAd;

    public static final GTOreDefinition NICKEL_VEIN_AD = create(
            CTNHCore.id("nickel_vein_ad"), vein -> {
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

    @Key("magnetite_vein_ad")
    @CN("磁铁矿脉")
    @EN("Ad Astra Magnetite Vein")
    public static Lang magnetiteVeinAd;

    @Key("ctnhcore:magnetite_vein_ad")
    @CN("太空磁铁矿脉")
    @EN("Space Magnetite Vein")
    public static Lang ctnhMagnetiteVeinAd;

    @Key("gtceu.jei.ore_vein.magnetite_vein_ad")
    @CN("太空磁铁矿脉")
    @EN("Space Magnetite Vein")
    public static Lang gtceuMagnetiteVeinAd;

    public static final GTOreDefinition MAGNETITE_VEIN_AD = create(
            CTNHCore.id("magnetite_vein_ad"), vein -> {
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

    @Key("iron_vein_ad")
    @CN("铁矿脉")
    @EN("Ad Astra Iron Vein")
    public static Lang ironVeinAd;

    @Key("ctnhcore:iron_vein_ad")
    @CN("太空带状铁矿脉")
    @EN("Space Banded Iron Vein")
    public static Lang ctnhIronVeinAd;

    @Key("gtceu.jei.ore_vein.iron_vein_ad")
    @CN("太空带状铁矿脉")
    @EN("Space Banded Iron Vein")
    public static Lang gtceuIronVeinAd;

    public static final GTOreDefinition IRON_VEIN_AD = create(
            CTNHCore.id("iron_vein_ad"), vein -> {
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

    @Key("beryllium_vein_ad")
    @CN("铍矿脉")
    @EN("Ad Astra Beryllium Vein")
    public static Lang berylliumVeinAd;

    @Key("ctnhcore:beryllium_vein_ad")
    @CN("太空铍矿脉")
    @EN("Space Beryllium Vein")
    public static Lang ctnhBerylliumVeinAd;

    @Key("gtceu.jei.ore_vein.beryllium_vein_ad")
    @CN("太空铍矿脉")
    @EN("Space Beryllium Vein")
    public static Lang gtceuBerylliumVeinAd;

    public static final GTOreDefinition BERYLLIUM_VEIN_AD = create(
            CTNHCore.id("beryllium_vein_ad"), vein -> {
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

    @Key("tetrahedrite_vein_ad")
    @CN("黝铜矿脉")
    @EN("Ad Astra Tetrahedrite Vein")
    public static Lang tetrahedriteVeinAd;

    @Key("ctnhcore:tetrahedrite_vein_ad")
    @CN("太空黝铜矿脉")
    @EN("Space Tetrahedrite Vein")
    public static Lang ctnhTetrahedriteVeinAd;

    @Key("gtceu.jei.ore_vein.tetrahedrite_vein_ad")
    @CN("太空黝铜矿脉")
    @EN("Space Tetrahedrite Vein")
    public static Lang gtceuTetrahedriteVeinAd;

    public static final GTOreDefinition TETRAHEDRITE_VEIN_AD = create(
            CTNHCore.id("tetrahedrite_vein_ad"), vein -> {
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
