package io.github.cpearl0.ctnhcore.registry.ores;

import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.valueproviders.UniformInt;

import static com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE;
import static io.github.cpearl0.ctnhcore.registry.CTNHOres.create;

public class EndOres {

    // ==================== End ====================

    public static final GTOreDefinition SCHEELITE_VEIN = create("scheelite_vein_ed",
            "End Tungsten Vein",
            "末地钨矿脉",
            vein -> vein
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

    public static final GTOreDefinition MAGNETITE_VEIN_END = create("magnetite_vein_ed",
            "End Magnetite Vein",
            "末地磁铁矿脉",
            vein -> vein
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

    public static GTOreDefinition CHROMITE_VEIN = create("chromite_vein_ed",
            "End Chromite Vein",
            "末地铬铁矿脉",
            vein -> vein.weight(60)
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

    public static final GTOreDefinition PITCHBLENDE_VEIN = create("pitchblende_vein_ed",
            "End Pitchblende Vein",
            "末地沥青铀矿脉",
            vein -> vein
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

    public static final GTOreDefinition BAUXITE_VEIN_ED = create("bauxite_vein_ed",
            "End Bauxite Vein",
            "末地铝土矿脉",
            vein -> vein
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

    public static final GTOreDefinition BORAX_VEIN_ED = create("borax_vein_ed",
            "End Borax Vein",
            "末地硼砂矿脉",
            vein -> vein
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

    public static final GTOreDefinition CRYOLITE_VEIN_ED = create("cryolite_vein_ed",
            "End Cryolite Vein",
            "末地冰晶石矿脉",
            vein -> vein
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

    public static final GTOreDefinition NIOBIUM_TANTALUM_VEIN_ED = create("niobium_tantalum_vein_ed",
            "End Niobium-Tantalum Vein",
            "末地铌钽矿脉",
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
