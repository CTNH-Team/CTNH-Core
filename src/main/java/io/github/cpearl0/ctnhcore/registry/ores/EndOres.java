package io.github.cpearl0.ctnhcore.registry.ores;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.valueproviders.UniformInt;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import static com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers.ENDSTONE;
import static com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE;
import static com.gregtechceu.gtceu.api.data.worldgen.generator.veins.DikeVeinGenerator.DikeBlockDefinition;
import static com.gregtechceu.gtceu.common.data.GTOres.create;

public class EndOres {

    // ==================== End ====================

    @Key("ctnhcore:chromite_vein")
    @CN("铬铁矿脉")
    @EN("Chromite Vein")
    public static Lang ctnhChromiteVein;

    @Key("gtceu.jei.ore_vein.chromite_vein")
    @CN("铬铁矿脉")
    @EN("Chromite Vein")
    public static Lang gtceuChromiteVein;

    public static GTOreDefinition CHROMITE_VEIN = create(CTNHCore.id("chromite_vein"), vein -> vein.weight(60)
            .clusterSize(40)
            .density(0.25F)
            .discardChanceOnAirExposure(0)
            .layer(ENDSTONE)
            .dimensions(ResourceLocation.tryParse("minecraft:the_end"))
            .heightRangeUniform(0, 40)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Chromite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.YellowLimonite).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Magnetite).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Lead).size(1, 1))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Chromite)
                    .placement(ABOVE)
                    .density(0.4F)
                    .radius(5)));

    // ==================== Migrated GregTech End Veins ====================

    @Key("ctnhcore:magnetite_vein_end")
    @CN("末地磁铁矿脉")
    @EN("Magnetite Vein End")
    public static Lang ctnhMagnetiteVeinEnd;

    @Key("gtceu.jei.ore_vein.magnetite_vein_end")
    @CN("末地磁铁矿脉")
    @EN("Magnetite Vein End")
    public static Lang gtceuMagnetiteVeinEnd;

    public static final GTOreDefinition MAGNETITE_VEIN_END = create(CTNHCore.id("magnetite_vein_end"), vein -> vein
            .clusterSize(UniformInt.of(38, 44)).density(0.15f).weight(30)
            .layer(ENDSTONE)
            .heightRangeUniform(20, 80)
            .biomes(BiomeTags.IS_END)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Magnetite).size(1, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.VanadiumMagnetite).size(1, 2))
                            .layer(l -> l.weight(2).mat(GTMaterials.Chromite).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Gold).size(1, 1))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Magnetite)
                    .placement(ABOVE)));

    @Key("ctnhcore:pitchblende_vein_end")
    @CN("末地沥青铀矿脉")
    @EN("Pitchblende Vein End")
    public static Lang ctnhPitchblendeVeinEnd;

    @Key("gtceu.jei.ore_vein.pitchblende_vein_end")
    @CN("末地沥青铀矿脉")
    @EN("Pitchblende Vein End")
    public static Lang gtceuPitchblendeVeinEnd;

    public static final GTOreDefinition PITCHBLENDE_VEIN = create(CTNHCore.id("pitchblende_vein_end"), vein -> vein
            .clusterSize(UniformInt.of(32, 64)).density(0.25f).weight(30)
            .layer(ENDSTONE)
            .heightRangeUniform(30, 60)
            .biomes(BiomeTags.IS_END)
            .cuboidVeinGenerator(generator -> generator
                    .top(b -> b.mat(GTMaterials.Pitchblende).size(2))
                    .middle(b -> b.mat(GTMaterials.Pitchblende).size(3))
                    .bottom(b -> b.mat(GTMaterials.Pitchblende).size(2))
                    .spread(b -> b.mat(GTMaterials.Uraninite)))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Pitchblende)
                    .placement(ABOVE)));

    @Key("ctnhcore:scheelite_vein")
    @CN("白钨矿脉")
    @EN("Scheelite Vein")
    public static Lang ctnhScheeliteVein;

    @Key("gtceu.jei.ore_vein.scheelite_vein")
    @CN("白钨矿脉")
    @EN("Scheelite Vein")
    public static Lang gtceuScheeliteVein;

    public static final GTOreDefinition SCHEELITE_VEIN = create(CTNHCore.id("scheelite_vein"), vein -> vein
            .clusterSize(UniformInt.of(50, 64)).density(0.7f).weight(20)
            .layer(ENDSTONE)
            .heightRangeUniform(20, 60)
            .biomes(BiomeTags.IS_END)
            .dikeVeinGenerator(generator -> generator
                    .withBlock(new DikeBlockDefinition(GTMaterials.Scheelite, 3, 20, 60))
                    .withBlock(new DikeBlockDefinition(GTMaterials.Tungstate, 2, 35, 55))
                    .withBlock(new DikeBlockDefinition(GTMaterials.Lithium, 1, 20, 40)))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Scheelite)
                    .placement(ABOVE)));

    public static void init() {}
}
