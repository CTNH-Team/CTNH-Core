package io.github.cpearl0.ctnhcore.registry.ores;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.valueproviders.UniformInt;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import static com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE;
import static com.gregtechceu.gtceu.common.data.GTOres.create;

public class EndOres {

    // ==================== End ====================

    @Key("ctnhcore:scheelite_vein_ed")
    @CN("末地钨矿脉")
    @EN("End Tungsten Vein")
    public static Lang ctnhScheeliteVeinEd;

    @Key("gtceu.jei.ore_vein.scheelite_vein_ed")
    @CN("末地钨矿脉")
    @EN("End Tungsten Vein")
    public static Lang gtceuScheeliteVeinEd;

    public static final GTOreDefinition SCHEELITE_VEIN = create(CTNHCore.id("scheelite_vein_ed"), vein -> vein
            .clusterSize(UniformInt.of(50, 60)).density(0.7f).weight(50)
            .layer(WorldGenLayers.ENDSTONE)
            .heightRangeUniform(0, 100)
            .biomes(BiomeTags.IS_END)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(2).mat(GTMaterials.Scheelite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Tungstate).size(2, 4))
                            .layer(l -> l.weight(1).mat(GTMaterials.Lithium))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Scheelite)
                    .placement(ABOVE)));

    @Key("ctnhcore:magnetite_vein_ed")
    @CN("末地磁铁矿脉")
    @EN("End Magnetite Vein")
    public static Lang ctnhMagnetiteVeinEd;

    @Key("gtceu.jei.ore_vein.magnetite_vein_ed")
    @CN("末地磁铁矿脉")
    @EN("End Magnetite Vein")
    public static Lang gtceuMagnetiteVeinEd;

    public static final GTOreDefinition MAGNETITE_VEIN_END = create(CTNHCore.id("magnetite_vein_ed"), vein -> vein
            .clusterSize(UniformInt.of(38, 44)).density(0.15f).weight(40)
            .layer(WorldGenLayers.ENDSTONE)
            .heightRangeUniform(0, 100)
            .biomes(BiomeTags.IS_END)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Magnetite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.VanadiumMagnetite))
                            .layer(l -> l.weight(2).mat(GTMaterials.Chromite))
                            .layer(l -> l.weight(1).mat(GTMaterials.Gold))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Magnetite)
                    .placement(ABOVE)));

    @Key("ctnhcore:chromite_vein_ed")
    @CN("末地铬铁矿脉")
    @EN("End Chromite Vein")
    public static Lang ctnhChromiteVeinEd;

    @Key("gtceu.jei.ore_vein.chromite_vein_ed")
    @CN("末地铬铁矿脉")
    @EN("End Chromite Vein")
    public static Lang gtceuChromiteVeinEd;

    public static GTOreDefinition CHROMITE_VEIN = create(CTNHCore.id("chromite_vein_ed"), vein -> vein.weight(60)
            .clusterSize(40)
            .density(0.25F)
            .discardChanceOnAirExposure(0)
            .layer(WorldGenLayers.ENDSTONE)
            .dimensions(ResourceLocation.tryParse("minecraft:the_end"))
            .heightRangeUniform(0, 100)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Chromite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Magnetite))
                            .layer(l -> l.weight(1).mat(GTMaterials.Iron))
                            .layer(l -> l.weight(1).mat(GTMaterials.Lead))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Chromite)
                    .placement(ABOVE)
                    .density(0.4F)
                    .radius(5)));

    @Key("ctnhcore:pitchblende_vein_ed")
    @CN("末地沥青铀矿脉")
    @EN("End Pitchblende Vein")
    public static Lang ctnhPitchblendeVeinEd;

    @Key("gtceu.jei.ore_vein.pitchblende_vein_ed")
    @CN("末地沥青铀矿脉")
    @EN("End Pitchblende Vein")
    public static Lang gtceuPitchblendeVeinEd;

    public static final GTOreDefinition PITCHBLENDE_VEIN = create(CTNHCore.id("pitchblende_vein_ed"), vein -> vein
            .clusterSize(UniformInt.of(32, 64)).density(0.25f).weight(30)
            .layer(WorldGenLayers.ENDSTONE)
            .heightRangeUniform(0, 100)
            .biomes(BiomeTags.IS_END)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(5).mat(GTMaterials.Pitchblende).size(2, 4))
                            .layer(l -> l.weight(1).mat(GTMaterials.Uraninite))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Pitchblende)
                    .placement(ABOVE)));

    @Key("ctnhcore:bauxite_vein_ed")
    @CN("末地铝土矿脉")
    @EN("End Bauxite Vein")
    public static Lang ctnhBauxiteVeinEd;

    @Key("gtceu.jei.ore_vein.bauxite_vein_ed")
    @CN("末地铝土矿脉")
    @EN("End Bauxite Vein")
    public static Lang gtceuBauxiteVeinEd;

    public static final GTOreDefinition BAUXITE_VEIN_ED = create(CTNHCore.id("bauxite_vein_ed"), vein -> vein
            .clusterSize(UniformInt.of(30, 40)).density(0.25f).weight(40)
            .layer(WorldGenLayers.ENDSTONE)
            .heightRangeUniform(0, 100)
            .biomes(BiomeTags.IS_END)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(2).mat(GTMaterials.Bauxite).size(2, 4))
                            .layer(l -> l.weight(1).mat(CTNHMaterials.Alumina))
                            .layer(l -> l.weight(2).mat(GTMaterials.Ilmenite).size(2, 4))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Bauxite)
                    .placement(ABOVE)));

    @Key("ctnhcore:borax_vein_ed")
    @CN("末地硼砂矿脉")
    @EN("End Borax Vein")
    public static Lang ctnhBoraxVeinEd;

    @Key("gtceu.jei.ore_vein.borax_vein_ed")
    @CN("末地硼砂矿脉")
    @EN("End Borax Vein")
    public static Lang gtceuBoraxVeinEd;

    public static final GTOreDefinition BORAX_VEIN_ED = create(CTNHCore.id("borax_vein_ed"), vein -> vein
            .clusterSize(UniformInt.of(30, 40)).density(0.25f).weight(40)
            .layer(WorldGenLayers.ENDSTONE)
            .heightRangeUniform(0, 100)
            .biomes(BiomeTags.IS_END)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(2).mat(GTMaterials.Borax).size(2, 4))
                            .layer(l -> l.weight(1).mat(GTMaterials.Lepidolite))
                            .layer(l -> l.weight(1).mat(GTMaterials.Spodumene))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Borax)
                    .placement(ABOVE)));

    @Key("ctnhcore:cryolite_vein_ed")
    @CN("末地冰晶石矿脉")
    @EN("End Cryolite Vein")
    public static Lang ctnhCryoliteVeinEd;

    @Key("gtceu.jei.ore_vein.cryolite_vein_ed")
    @CN("末地冰晶石矿脉")
    @EN("End Cryolite Vein")
    public static Lang gtceuCryoliteVeinEd;

    public static final GTOreDefinition CRYOLITE_VEIN_ED = create(CTNHCore.id("cryolite_vein_ed"), vein -> vein
            .clusterSize(UniformInt.of(30, 40)).density(0.25f).weight(40)
            .layer(WorldGenLayers.ENDSTONE)
            .heightRangeUniform(0, 100)
            .biomes(BiomeTags.IS_END)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(2).mat(CTNHMaterials.Cryolite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Mica).size(2, 4))
                            .layer(l -> l.weight(1).mat(GTMaterials.Silver))
                            .layer(l -> l.weight(1).mat(GTMaterials.Lead))))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(CTNHMaterials.Cryolite)
                    .placement(ABOVE)));

    @Key("ctnhcore:niobium_tantalum_vein_ed")
    @CN("末地铌钽矿脉")
    @EN("End Niobium-Tantalum Vein")
    public static Lang ctnhNiobiumTantalumVeinEd;

    @Key("gtceu.jei.ore_vein.niobium_tantalum_vein_ed")
    @CN("末地铌钽矿脉")
    @EN("End Niobium-Tantalum Vein")
    public static Lang gtceuNiobiumTantalumVeinEd;

    public static final GTOreDefinition NIOBIUM_TANTALUM_VEIN_ED = create(CTNHCore.id("niobium_tantalum_vein_ed"),
            vein -> vein
                    .clusterSize(UniformInt.of(30, 40)).density(0.25f).weight(30)
                    .layer(WorldGenLayers.ENDSTONE)
                    .heightRangeUniform(0, 100)
                    .biomes(BiomeTags.IS_END)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(GTMaterials.Tantalite).size(2, 4))
                                    .layer(l -> l.weight(2).mat(GTMaterials.Pyrochlore).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Pyrolusite))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Tantalite)
                            .placement(ABOVE)));

    public static void init() {}
}
