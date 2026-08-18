package io.github.cpearl0.ctnhcore.registry.ores;

import io.github.cpearl0.ctnhcore.CTNHCore;
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

public class AetherOres {

    // ==================== Aether ====================

    @Key("ctnhcore:cryolite_vein_aether")
    @CN("天境冰晶石矿脉")
    @EN("Aether Cryolite Vein")
    public static Lang ctnhCryoliteVeinAether;

    @Key("gtceu.jei.ore_vein.cryolite_vein_aether")
    @CN("天境冰晶石矿脉")
    @EN("Aether Cryolite Vein")
    public static Lang gtceuCryoliteVeinAether;

    public static GTOreDefinition CRYOLITE_VEIN_AETHER = create(CTNHCore.id("cryolite_vein_aether"),
            vein -> vein.weight(40)
                    .clusterSize(40)
                    .density(0.45F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.AETHER)
                    .dimensions(THE_AETHER)
                    .heightRangeUniform(20, 80)
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

    @Key("ctnhcore:bauxite_vein_aether")
    @CN("天境铝土矿脉")
    @EN("Aether Bauxite Vein")
    public static Lang ctnhBauxiteVeinAether;

    @Key("gtceu.jei.ore_vein.bauxite_vein_aether")
    @CN("天境铝土矿脉")
    @EN("Aether Bauxite Vein")
    public static Lang gtceuBauxiteVeinAether;

    public static GTOreDefinition BAUXITE_VEIN_AETHER = create(CTNHCore.id("bauxite_vein_aether"),
            vein -> vein.weight(60)
                    .clusterSize(40)
                    .density(0.35F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.AETHER)
                    .dimensions(THE_AETHER)
                    .heightRangeUniform(20, 80)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(GTMaterials.Bauxite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Ilmenite).size(1, 1))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.Alumina).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Bauxite)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));

    @Key("ctnhcore:scheelite_vein_aether")
    @CN("天境白钨矿脉")
    @EN("Aether Scheelite Vein")
    public static Lang ctnhScheeliteVeinAether;

    @Key("gtceu.jei.ore_vein.scheelite_vein_aether")
    @CN("天境白钨矿脉")
    @EN("Aether Scheelite Vein")
    public static Lang gtceuScheeliteVeinAether;

    public static GTOreDefinition SCHEELITE_VEIN_AETHER = create(CTNHCore.id("scheelite_vein_aether"),
            vein -> vein.weight(50)
                    .clusterSize(45)
                    .density(0.45F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.AETHER)
                    .dimensions(THE_AETHER)
                    .heightRangeUniform(10, 50)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(GTMaterials.Scheelite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Tungstate).size(1, 1))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Lithium).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Scheelite)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)));

    @Key("ctnhcore:zanite_vein_aether")
    @CN("天境紫晶矿石")
    @EN("Aether Zanite Vein")
    public static Lang ctnhZaniteVeinAether;

    @Key("gtceu.jei.ore_vein.zanite_vein_aether")
    @CN("天境紫晶矿石")
    @EN("Aether Zanite Vein")
    public static Lang gtceuZaniteVeinAether;

    public static GTOreDefinition ZANITE_VEIN_AETHER = create(CTNHCore.id("zanite_vein_aether"), vein -> vein.weight(40)
            .clusterSize(45)
            .density(0.25F)
            .discardChanceOnAirExposure(0)
            .layer(CTNHWorldgenLayers.AETHER)
            .dimensions(THE_AETHER)
            .heightRangeUniform(10, 60)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(CTNHMaterials.Zanite).size(2, 4))
                            .layer(l -> l.weight(2).mat(CTNHMaterials.Ambrosium).size(1, 1))
                            .layer(l -> l.weight(1).mat(CTNHMaterials.Skyjade).size(1, 1))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(CTNHMaterials.Zanite)
                    .placement(ABOVE)
                    .density(0.4F)
                    .radius(5)));

    @Key("ctnhcore:combustible_ice_vein_aether")
    @CN("天境可燃冰矿脉")
    @EN("Aether Combustible Ice Vein")
    public static Lang ctnhCombustibleIceVeinAether;

    @Key("gtceu.jei.ore_vein.combustible_ice_vein_aether")
    @CN("天境可燃冰矿脉")
    @EN("Aether Combustible Ice Vein")
    public static Lang gtceuCombustibleIceVeinAether;

    public static GTOreDefinition COMBUSTIBLE_ICE_VEIN_AETHER = create(CTNHCore.id("combustible_ice_vein_aether"),
            vein -> vein.weight(80)
                    .clusterSize(30)
                    .density(0.55F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.AETHER)
                    .dimensions(THE_AETHER)
                    .heightRangeUniform(20, 80)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(CTNHMaterials.CombustibleIce).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Coal).size(1, 1))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Opal).size(1, 1))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.CombustibleIce).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.CombustibleIce)
                            .placement(ABOVE)
                            .density(0.2F)
                            .radius(5)));

    public static void init() {}
}
