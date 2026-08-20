package io.github.cpearl0.ctnhcore.registry.ores;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHWorldgenLayers;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.util.valueproviders.UniformInt;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import static com.gregtechceu.gtceu.common.data.GTOres.create;
import static io.github.cpearl0.ctnhcore.registry.CTNHWorlds.THE_AETHER;

public class AetherOres {

    // ==================== Aether ====================

    @Key("ctnhcore:zanite_vein_at")
    @CN("天境紫晶矿脉")
    @EN("Aether Zanite Vein")
    public static Lang ctnhZaniteVeinAether;

    @Key("gtceu.jei.ore_vein.zanite_vein_at")
    @CN("天境紫晶矿脉")
    @EN("Aether Zanite Vein")
    public static Lang gtceuZaniteVeinAether;

    public static GTOreDefinition ZANITE_VEIN_AT = create(CTNHCore.id("zanite_vein_at"),
            vein -> vein
                    .weight(60)
                    .clusterSize(UniformInt.of(30, 50))
                    .density(0.5f)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.AETHER)
                    .dimensions(THE_AETHER)
                    .heightRangeUniform(0, 100)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(CTNHMaterials.Zanite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(CTNHMaterials.Ambrosium))
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.Skyjade))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.Zanite)));

    @Key("ctnhcore:topaz_vein_at")
    @CN("天境黄玉矿脉")
    @EN("Aether Topaz Vein")
    public static Lang ctnhTopazVeinAether;

    @Key("gtceu.jei.ore_vein.topaz_vein_at")
    @CN("天境黄玉矿脉")
    @EN("Aether Topaz Vein")
    public static Lang gtceuTopazVeinAether;

    public static final GTOreDefinition TOPAZ_VEIN_AT = create(CTNHCore.id("topaz_vein_at"),
            vein -> vein
                    .weight(50)
                    .clusterSize(UniformInt.of(25, 30))
                    .density(0.5f)
                    .layer(CTNHWorldgenLayers.AETHER)
                    .dimensions(THE_AETHER)
                    .heightRangeUniform(0, 100)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Topaz).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Chalcopyrite))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Topaz)));

    @Key("ctnhcore:emerald_vein_at")
    @CN("天境绿宝石矿脉")
    @EN("Aether Emerald Vein")
    public static Lang ctnhEmeraldVeinAether;

    @Key("gtceu.jei.ore_vein.emerald_vein_at")
    @CN("天境绿宝石矿脉")
    @EN("Aether Emerald Vein")
    public static Lang gtceuEmeraldVeinAether;

    public static final GTOreDefinition EMERALD_VEIN_NT = create(CTNHCore.id("emerald_vein_nt"),
            vein -> vein
                    .clusterSize(UniformInt.of(50, 60))
                    .density(0.5f)
                    .weight(30)
                    .layer(CTNHWorldgenLayers.AETHER)
                    .dimensions(THE_AETHER)
                    .heightRangeUniform(0, 100)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(1).mat(GTMaterials.Beryllium))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Emerald).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Thorium))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Emerald)));

    @Key("ctnhcore:olivine_vein_at")
    @CN("天境橄榄石矿脉")
    @EN("Aether Olivine Vein")
    public static Lang ctnhOlivineVeinAether;

    @Key("gtceu.jei.ore_vein.olivine_vein_at")
    @CN("天境橄榄石矿脉")
    @EN("Aether Olivine Vein")
    public static Lang gtceuOlivineVeinAether;

    public static final GTOreDefinition OLIVINE_VEIN_AT = create(CTNHCore.id("olivine_vein_at"),
            vein -> vein
                    .weight(20)
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.25f)
                    .layer(CTNHWorldgenLayers.AETHER)
                    .heightRangeUniform(-20, 10)
                    .dimensions(THE_AETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(1).mat(GTMaterials.Olivine).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Magnesite).size(2, 4))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Olivine)));

    @Key("ctnhcore:diamond_vein_at")
    @CN("天境钻石矿脉")
    @EN("Aether Diamond Vein")
    public static Lang ctnhDiamondVeinAether;

    @Key("gtceu.jei.ore_vein.diamond_vein_at")
    @CN("天境钻石矿脉")
    @EN("Aether Diamond Vein")
    public static Lang gtceuDiamondVeinAether;

    public static final GTOreDefinition DIAMOND_VEIN_AT = create(CTNHCore.id("diamond_vein_at"),
            vein -> vein
                    .weight(40)
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.25f)
                    .layer(CTNHWorldgenLayers.AETHER)
                    .heightRangeUniform(-60, -30)
                    .dimensions(THE_AETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Graphite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Diamond).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Coal))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Diamond)));

    @Key("ctnhcore:redstone_vein_at")
    @CN("天境红石矿脉")
    @EN("Aether Redstone Vein")
    public static Lang ctnhRedstoneVeinAether;

    @Key("gtceu.jei.ore_vein.redstone_vein_at")
    @CN("天境红石矿脉")
    @EN("Aether Redstone Vein")
    public static Lang gtceuRedstoneVeinAether;

    public static final GTOreDefinition REDSTONE_VEIN_AT = create(CTNHCore.id("redstone_vein_at"),
            vein -> vein
                    .weight(60)
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.2f)
                    .layer(CTNHWorldgenLayers.AETHER)
                    .heightRangeUniform(-60, -10)
                    .dimensions(THE_AETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Redstone).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Ruby))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Cinnabar))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Redstone)));

    @Key("ctnhcore:sapphire_vein_at")
    @CN("天境蓝宝石矿脉")
    @EN("Aether Sapphire Vein")
    public static Lang ctnhSapphireVeinAether;

    @Key("gtceu.jei.ore_vein.sapphire_vein_at")
    @CN("天境蓝宝石矿脉")
    @EN("Aether Sapphire Vein")
    public static Lang gtceuSapphireVeinAether;

    public static final GTOreDefinition SAPPHIRE_VEIN_AT = create(CTNHCore.id("sapphire_vein_at"),
            vein -> vein
                    .weight(60)
                    .clusterSize(UniformInt.of(25, 30))
                    .density(0.25f)
                    .layer(CTNHWorldgenLayers.AETHER)
                    .heightRangeUniform(-40, 0)
                    .dimensions(THE_AETHER)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Electrotine).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Sapphire))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Sapphire)));

    public static void init() {}
}
