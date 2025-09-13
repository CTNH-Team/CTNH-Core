package io.github.cpearl0.ctnhcore.registry;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers;
import com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.worldgen.CTNHDimensions;
import mythicbotany.register.ModBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import twilightforest.data.tags.BiomeTagGenerator;

import static com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers.ENDSTONE;
import static com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers.STONE;
import static com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE;
import static com.gregtechceu.gtceu.common.data.GTOres.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHWorlds.*;

public class CTNHOres {
    public static void init() {
        MAGNETITE_VEIN_OW.layeredVeinGenerator(generator -> generator
                .buildLayerPattern(pattern -> pattern
                .layer(l -> l.weight(3).mat(GTMaterials.Magnetite).size(2, 4))
                .layer(l -> l.weight(2).mat(GTMaterials.VanadiumMagnetite).size(1, 1))
                .layer(l -> l.weight(1).mat(CTNHMaterials.PreciousAlloy).size(1, 1))
            )
        );
        //Chromite
        //Alumina
//        GTNNOres.INSTANCE.getGOLD_VEIN_TF().layeredVeinGenerator(generator -> generator
//                .buildLayerPattern(pattern -> pattern
//                .layer(l -> l.weight(3).mat(GTMaterials.Magnetite).size(2, 4))
//                .layer(l -> l.weight(2).mat(GTMaterials.VanadiumMagnetite).size(1, 1))
//                .layer(l -> l.weight(1).mat(CTNHMaterials.PreciousAlloy).size(1, 1))
//            )
//        );
//        GTNNOres.INSTANCE.getARSENIC_VEIN_AD().layeredVeinGenerator(generator -> generator
//                .buildLayerPattern(pattern -> pattern
//                        .layer(l -> l.weight(1).mat(CTNHMaterials.Sperrylite).size(1, 2))
//                        .layer(l -> l.weight(1).mat(CTNHMaterials.Bismuthinite).size(1, 2))
//                        .layer(l -> l.weight(1).mat(GTMaterials.Stibnite).size(1, 2))
//                )
//        );
//        GTNNOres.INSTANCE.getIRIDIUM_VEIN_AD().layeredVeinGenerator(generator -> generator
//                .buildLayerPattern(pattern -> pattern
//                        .layer(l -> l.weight(1).mat(GTMaterials.Nickel).size(2, 3))
//                        .layer(l -> l.weight(1).mat(CTNHMaterials.MeteoricTroilite).size(1, 2))
//                        .layer(l -> l.weight(1).mat(CTNHMaterials.PalladiumSulfide).size(1, 2))
//                )
//        );
//        GTNNOres.INSTANCE.getCHROMIUM_VEIN_AD().layeredVeinGenerator(generator -> generator
//                .buildLayerPattern(pattern -> pattern
//                        .layer(l -> l.weight(1).mat(GTMaterials.Chromite).size(2, 3))
//                        .layer(l -> l.weight(1).mat(CTNHMaterials.Wolframite).size(1, 2))
//                        .layer(l -> l.weight(1).mat(CTNHMaterials.Tarkianite).size(1, 2))
//                        .layer(l -> l.weight(1).mat(GTMaterials.Pyrolusite).size(1, 2))
//                )
//        );
//        GTNNOres.INSTANCE.getNIOBIUM_VEIN_AD().layeredVeinGenerator(generator -> generator
//                .buildLayerPattern(pattern -> pattern
//                        .layer(l -> l.weight(1).mat(GTMaterials.Niobium).size(2, 3))
//                        .layer(l -> l.weight(1).mat(CTNHMaterials.MeteoricTroilite).size(1, 2))
//                        .layer(l -> l.weight(1).mat(CTNHMaterials.Germanite).size(1, 2))
//                )
//        );
//        GTNNOres.INSTANCE.getOSMIUM_VEIN_AD().layeredVeinGenerator(generator -> generator
//                .buildLayerPattern(pattern -> pattern
//                        .layer(l -> l.weight(1).mat(GTMaterials.Nickel).size(2, 3))
//                        .layer(l -> l.weight(1).mat(CTNHMaterials.OsmiumIronSpinel).size(1, 1))
//                        .layer(l -> l.weight(1).mat(CTNHMaterials.MeteoricTroilite).size(1, 1))
//                        .layer(l -> l.weight(1).mat(CTNHMaterials.Crocoite).size(2, 3))
//                )
//        );
//        GTNNOres.INSTANCE.getNEUTRONIUM_VEIN_AD().layeredVeinGenerator(generator -> generator
//                .buildLayerPattern(pattern -> pattern
//                        .layer(l -> l.weight(1).mat(GTMaterials.Neutronium).size(2, 3))
//                        .layer(l -> l.weight(1).mat(CTNHMaterials.OsmiumIronSpinel).size(1, 1))
//                        .layer(l -> l.weight(1).mat(GTMaterials.Naquadria).size(1, 1))
//                        .layer(l -> l.weight(1).mat(CTNHMaterials.Rheniite).size(1, 1))
//                )
//        );
        GTRegistries.ORE_VEINS.remove(GTCEu.id("nether_quartz_vein"));
        GTRegistries.ORE_VEINS.remove(GTCEu.id("nickel_vein"));
        GTRegistries.ORE_VEINS.remove(GTCEu.id("galena_vein"));
        GTRegistries.ORE_VEINS.remove(GTCEu.id("sheldonite_vein"));
        GTRegistries.ORE_VEINS.remove(GTCEu.id("bauxite_vein_end"));
        GTRegistries.ORE_VEINS.remove(GTCEu.id("naquadah_vein"));
        MICA_VEIN.layer(WorldGenLayers.NETHERRACK)
                .dimensions(ResourceLocation.tryParse("minecraft:the_nether"))
                .biomes(BiomeTags.IS_NETHER)
                .heightRangeUniform(0, 25)
                .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                        .layer(l -> l.weight(3).mat(GTMaterials.Kyanite).size(2, 4))
                        .layer(l -> l.weight(2).mat(GTMaterials.Mica).size(1, 1))
                        .layer(l -> l.weight(2).mat(CTNHMaterials.Alumina).size(1, 1))
                        .layer(l -> l.weight(1).mat(GTMaterials.Pollucite).size(1, 1))
                    )
                );
//        GTNNOres.INSTANCE.getOSTRUM_VEIN_AD()
//                .clusterSize(40);

//        let ADASTRA = ["ad_astra:lunar_wastelands","ad_astra:glacio_ice_peaks","ad_astra:glacio_snowny_barrens",
//                "ad_astra:inferno_venus_barrens","ad_astra:martian_canyon_creek","ad_astra:martian_polar_caps",
//                "ad_astra:martian_wastelands","ad_astra:mercury_deltas","ad_astra:venus_wastelands","ad_extendra:cosmic_gaslands",
//                "ad_extendra:intriguing_wastelands"]
        // event.modify("gtceu:naquadah_vein", vein => {
        //     vein.layer("all_layer")
        //     ADASTRA.forEach(biome =>{
        //         vein.biomes(biome)
        //     })
        //     vein.dimensions(["ad_astra:mercury", "ad_extendra:jupiter"])
        // })
        // event.modify("gtceu:monazite_vein", vein => {
        //     vein.layer("all_layer")
        //     ADASTRA.forEach(biome =>{
        //         vein.biomes(biome)
        //     })
        //     vein.dimensions("ad_extendra:jupiter")
        // })
    }

    public static GTOreDefinition NETHER_QUARTZ_VEIN_OW = create(CTNHCore.id("nether_quartz_vein_ow"), vein -> vein
            .weight(80)
        .clusterSize(40)
        .density(0.25F)
        .discardChanceOnAirExposure(0)
        .layer(STONE)
        .dimensions(ResourceLocation.tryParse("minecraft:overworld"))
        .heightRangeUniform(20, 60)
        .layeredVeinGenerator(generator -> generator
                .buildLayerPattern(pattern -> pattern
                .layer(l -> l.weight(3).mat(GTMaterials.NetherQuartz).size(2, 4))
                .layer(l -> l.weight(1).mat(GTMaterials.Quartzite).size(1, 1))
                .layer(l -> l.weight(1).mat(GTMaterials.Opal).size(1, 1))
            )
        )
        .surfaceIndicatorGenerator(indicator -> indicator
                .surfaceRock(GTMaterials.NetherQuartz)
                .placement(ABOVE)
                .density(0.4F)
                .radius(5)
        ));
    public static GTOreDefinition NICKEL_VEIN_OW = create(CTNHCore.id("nickel_vein"), vein -> vein
                    .weight(40)
            .clusterSize(40)
            .density(0.25F)
            .discardChanceOnAirExposure(0)
            .layer(WorldGenLayers.NETHERRACK)
            .dimensions(ResourceLocation.tryParse("minecraft:the_nether"))
            .heightRangeUniform(10, 60)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                    .layer(l -> l.weight(3).mat(GTMaterials.Garnierite).size(2, 4))
                    .layer(l -> l.weight(2).mat(GTMaterials.Nickel).size(1, 1))
                    .layer(l -> l.weight(2).mat(GTMaterials.Cobaltite).size(1, 1))
                    .layer(l -> l.weight(1).mat(GTMaterials.Pentlandite).size(1, 1))
                )
            )
            .surfaceIndicatorGenerator(indicator -> indicator
                .surfaceRock(GTMaterials.Nickel)
                .placement(ABOVE)
                .density(0.4F)
                .radius(5)
            ));
    public static GTOreDefinition ANCIENT_DEBRIS_VEIN = create(CTNHCore.id("ancient_debris_vein"), vein ->
            vein.weight(5).clusterSize(35)
            .density(0.4F)
            .discardChanceOnAirExposure(0)
            .layer(WorldGenLayers.NETHERRACK)
            .dimensions(ResourceLocation.tryParse("minecraft:the_nether"))
            .heightRangeUniform(0, 25)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                    .layer(l -> l.weight(3).mat(CTNHMaterials.PreciousAlloy).size(2, 4))
                    .layer(l -> l.weight(2).mat(GTMaterials.Sulfur).size(2, 2))
                    .layer(l -> l.weight(1).block(() -> Blocks.ANCIENT_DEBRIS).size(1, 1))
                    .layer(l -> l.weight(1).mat(GTMaterials.NetherQuartz).size(1, 1))
                )
            )
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(CTNHMaterials.PreciousAlloy)
                    .placement(ABOVE)
                    .density(0.4F)
                    .radius(5)
            ));

    public static GTOreDefinition CHROMITE_VEIN = create(CTNHCore.id("chromite_vein"), vein ->
        vein.weight(60)
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
                .layer(l -> l.weight(1).mat(GTMaterials.Lead).size(1, 1))
            )
        )
        .surfaceIndicatorGenerator(indicator -> indicator
                .surfaceRock(GTMaterials.Chromite)
                .placement(ABOVE)
                .density(0.4F)
                .radius(5)
        ));
    public static GTOreDefinition CRYOLITE_VEIN = create(CTNHCore.id("cryolite_vein"), vein ->
        vein.weight(70)
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
                .layer(l -> l.weight(1).mat(GTMaterials.Lead).size(1, 1))
            )
        )
        .surfaceIndicatorGenerator(indicator -> indicator
                .surfaceRock(CTNHMaterials.Cryolite)
                .placement(ABOVE)
                .density(0.4F)
                .radius(5)
        ));
    public static GTOreDefinition STEEL_LEAF_VEINN = create(CTNHCore.id("steel_leaf_vein"), vein ->
            vein.weight(20)
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
                                    .layer(l -> l.weight(1).mat(GTMaterials.Pyrochlore).size(1, 1))
                            )
                    )
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.SteelLeaf)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)
                    ));
    public static GTOreDefinition LICH_BONE_VEIN = create(CTNHCore.id("lich_bone_vein"), vein ->
            vein.weight(20)
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
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.SpiritAsh).size(1, 1))
                            )
                    )
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.SpiritAsh)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)
                    ));
    public static GTOreDefinition TOXIC_SWAMP_AMBER_VEIN = create(CTNHCore.id("toxic_swamp_amber_vein"), vein ->
            vein.weight(50)
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
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.ToxicSwampAmber).size(1, 1))
                            )
                    )
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.ToxicSwampAmber)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)
                    ));
    public static GTOreDefinition ILLUSION_IRON_VEIN = create(CTNHCore.id("illusion_iron_vein"), vein ->
            vein.weight(50)
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
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.IllusionIron).size(1, 1))
                            )
                    )
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.IllusionIron)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)
                    ));
    public static GTOreDefinition ARCTIC_CRYSTAL_CORE_VEIN = create(CTNHCore.id("arctic_crystal_core_vein"), vein ->
            vein.weight(50)
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
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.PolarIceCore).size(1, 1))
                            )
                    )
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.PolarIceCore)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)
                    ));
    public static GTOreDefinition DRAGONFLAME_VEIN = create(CTNHCore.id("dragonflame_vein"), vein ->
            vein.weight(50)
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
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.Dragonflame).size(1, 1))
                            )
                    )
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.Dragonflame)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)
                    ));
    public static GTOreDefinition ECLIPSE_SHADOW_VEIN = create(CTNHCore.id("eclipse_shadow_vein"), vein ->
            vein.weight(50)
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
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.EclipseShadow).size(1, 1))
                            )
                    )
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.EclipseShadow)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)
                    ));
    public static GTOreDefinition LIGHTNING_VEIN_VEIN = create(CTNHCore.id("thunderstrike_vein"), vein ->
            vein.weight(50)
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
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.LightningPattern).size(1, 1))
                            )
                    )
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.LightningPattern)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)
                    ));
    public static GTOreDefinition CRYOLITE_VEIN_AETHER = create(CTNHCore.id("cryolite_vein_aether"), vein ->
        vein.weight(40)
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
                .layer(l -> l.weight(1).mat(GTMaterials.Lead).size(1, 1))
            )
        )
        .surfaceIndicatorGenerator(indicator -> indicator
                .surfaceRock(CTNHMaterials.Cryolite)
                .placement(ABOVE)
                .density(0.4F)
                .radius(5)
        ));
    public static GTOreDefinition BAUXITE_VEIN_AETHER = create(CTNHCore.id("bauxite_vein_aether"), vein ->
        vein.weight(60)
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
                .layer(l -> l.weight(1).mat(CTNHMaterials.Alumina).size(1, 1))
            )
        )
        .surfaceIndicatorGenerator(indicator -> indicator
                .surfaceRock(GTMaterials.Bauxite)
                .placement(ABOVE)
                .density(0.4F)
                .radius(5)
        ));
    public static GTOreDefinition SCHEELITE_VEIN_AETHER = create(CTNHCore.id("scheelite_vein_aether"), vein ->
        vein.weight(50)
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
                .layer(l -> l.weight(1).mat(GTMaterials.Lithium).size(1, 1))
            )
        )
        .surfaceIndicatorGenerator(indicator -> indicator
                .surfaceRock(GTMaterials.Scheelite)
                .placement(ABOVE)
                .density(0.4F)
                .radius(5)
        ));
    public static GTOreDefinition ZANITE_VEIN_AETHER = create(CTNHCore.id("zanite_vein_aether"), vein ->
        vein.weight(40)
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
                .layer(l -> l.weight(1).mat(CTNHMaterials.Skyjade).size(1, 1))
            )
        )
        .surfaceIndicatorGenerator(indicator -> indicator
                .surfaceRock(CTNHMaterials.Zanite)
                .placement(ABOVE)
                .density(0.4F)
                .radius(5)
        ));
    public static GTOreDefinition SHELDONITE_VEIN_MOON = create(CTNHCore.id("sheldonite_vein_moon"),vein ->
        vein.clusterSize(40)
        .density(0.3F)
        .weight(40)
        .layer(CTNHWorldgenLayers.ADASTRA)
        .heightRangeUniform(5, 50)
        .dimensions(MOON)
        .layeredVeinGenerator(generator -> generator
                .buildLayerPattern(pattern -> pattern
                .layer(l -> l.weight(3).mat(GTMaterials.Bornite).size(2, 4))
                .layer(l -> l.weight(2).mat(GTMaterials.Cooperite).size(1, 1))
                .layer(l -> l.weight(2).mat(CTNHMaterials.PlatinumOre).size(1, 1))
                .layer(l -> l.weight(1).mat(CTNHMaterials.PalladiumOre).size(1, 1))
                ))
        .surfaceIndicatorGenerator(indicator -> indicator
                .surfaceRock(CTNHMaterials.PlatinumOre)
                .placement(ABOVE)
                .density(0.4F)
                .radius(5)
        ));
    public static GTOreDefinition ZINC_VEIN = create(CTNHCore.id("zinc_vein"), vein ->
        vein.weight(60)
        .clusterSize(40)
        .density(0.25F)
        .discardChanceOnAirExposure(0)
        .layer(STONE)
        .dimensions(ResourceLocation.tryParse("minecraft:overworld"))
        .heightRangeUniform(20, 50)
        .layeredVeinGenerator(generator -> generator
                .buildLayerPattern(pattern -> pattern
                .layer(l -> l.weight(3).mat(GTMaterials.Zinc).size(2, 4))
                .layer(l -> l.weight(2).mat(GTMaterials.Copper).size(1, 1))
                .layer(l -> l.weight(2).mat(GTMaterials.YellowLimonite).size(1, 1))
                .layer(l -> l.weight(1).mat(GTMaterials.Hematite).size(1, 1))
            )
        )
        .surfaceIndicatorGenerator(indicator -> indicator
                .surfaceRock(GTMaterials.Copper)
                .placement(ABOVE)
                .density(0.4F)
                .radius(5)
        ));
    public static GTOreDefinition PRECIOUS_ALLOY_VEIN = create(CTNHCore.id("precious_alloy_vein"), vein ->
        vein.weight(30)
        .clusterSize(40)
        .density(0.25F)
        .discardChanceOnAirExposure(0)
        .layer(STONE)
                .biomes(BiomeTags.IS_OVERWORLD)
        .heightRangeUniform(-10, 30)
        .layeredVeinGenerator(generator -> generator
                .buildLayerPattern(pattern -> pattern
                .layer(l -> l.weight(3).mat(CTNHMaterials.PreciousAlloy).size(2, 4))
                .layer(l -> l.weight(2).mat(GTMaterials.Silver).size(1, 3))
                .layer(l -> l.weight(2).mat(GTMaterials.Tin).size(1, 1))
                .layer(l -> l.weight(1).mat(GTMaterials.Copper).size(1, 1))
            )
        )
        .surfaceIndicatorGenerator(indicator -> indicator
                .surfaceRock(CTNHMaterials.PreciousAlloy)
                .placement(ABOVE)
                .density(0.4F)
                .radius(5)
        ));
    public static GTOreDefinition ARSENIC_VEIN = create(CTNHCore.id("arsenic_vein"), vein ->
        vein.weight(10)
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
                .layer(l -> l.weight(1).mat(GTMaterials.Realgar).size(1, 1))
            )
        )
        .surfaceIndicatorGenerator(indicator -> indicator
                .surfaceRock(CTNHMaterials.Nickeline)
                .placement(ABOVE)
                .density(0.4F)
                .radius(5)
        ));
    public static GTOreDefinition PHOSPHATE_VEIN = create(CTNHCore.id("phosphate_vein"), vein ->
        vein.weight(40)
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
                .layer(l -> l.weight(2).mat(CTNHMaterials.TrisodiumPhosphate).size(1, 2))
            )
        )
        .surfaceIndicatorGenerator(indicator -> indicator
                .surfaceRock(CTNHMaterials.TrisodiumPhosphate)
                .placement(ABOVE)
                .density(0.4F)
                .radius(5)
        ));
    public static GTOreDefinition ZIRKELITE_VEIN = create(CTNHCore.id("zirkelite_vein"), vein ->
            vein.weight(60)
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
            .layer(l -> l.weight(1).mat(GTMaterials.Ilmenite).size(1, 1))
        )
    )
    .surfaceIndicatorGenerator(indicator -> indicator
            .surfaceRock(CTNHMaterials.Zirkelite)
            .placement(ABOVE)
            .density(0.4F)
            .radius(5)
    ));
    public static GTOreDefinition RHODIUM_SULFUR_CRYSTAL_VEIN = create(CTNHCore.id("rhodium_sulfur_crystal_vein"), vein ->
            vein.weight(40)
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
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.GadoliniteSm).size(1, 1))
                            )
                    )
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.RhodiumSulfurCrystal)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)
                    ));
    public static GTOreDefinition RUTHENIUM_AMALGAM_VEIN = create(CTNHCore.id("ruthenium_amalgam_vein"), vein ->
            vein.weight(50)
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
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.SolarFlareBlackDiamond).size(1, 1))
                            )
                    )
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.RutheniumAmalgam)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)
                    ));
    public static GTOreDefinition EUROPIUM_VEIN = create(CTNHCore.id("europium_vein"), vein ->
            vein.weight(30)
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
                                    .layer(l -> l.weight(1).mat(CTNHMaterials.EuropiumFluorite).size(1, 1))
                            )
                    )
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(CTNHMaterials.EuropiumFluorite)
                            .placement(ABOVE)
                            .density(0.4F)
                            .radius(5)
                    ));
    public static GTOreDefinition COMBUSTIBLE_ICE_VEIN_AETHER = create(CTNHCore.id("combustible_ice_vein_aether"), vein ->
        vein.weight(80)
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
                    .layer(l -> l.weight(1).mat(CTNHMaterials.CombustibleIce).size(1, 1))
                )
            )
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(CTNHMaterials.CombustibleIce)
                    .placement(ABOVE)
                    .density(0.2F)
                    .radius(5)
            )
    );
    public static GTOreDefinition MANA_FUSED_VEIN = create(CTNHCore.id("mana_fused_vein"), vein ->
        vein.weight(80)
        .clusterSize(40)
        .density(0.25F)
        .discardChanceOnAirExposure(0)
        .layer(CTNHWorldgenLayers.ALFHEIM)
        .dimensions(ALFHEIM)
        .heightRangeUniform(20, 40)
        .layeredVeinGenerator(generator -> generator
                .buildLayerPattern(pattern -> pattern
                .layer(l -> l.weight(3).mat(CTNHMaterials.ManaFused).size(2, 4))
                .layer(l -> l.weight(2).block(() -> ModBlocks.goldOre).size(1, 1))
                .layer(l -> l.weight(1).block(() -> ModBlocks.dragonstoneOre).size(1, 1))
            )
        )
        .surfaceIndicatorGenerator(indicator -> indicator
                .surfaceRock(GTMaterials.Gold)
                .placement(ABOVE)
                .density(0.4F)
                .radius(5)
        )
    );
    public static final GTOreDefinition KAOLINITE_VEIN = create(
            CTNHCore.id("kaolinite_vein"),
            vein -> {
                vein.clusterSize(24).weight(60).layer(WorldGenLayers.STONE).density(0.4f)
                        .dimensions(BuiltinDimensionTypes.OVERWORLD.location()).heightRangeUniform(30, 70)
                        .discardChanceOnAirExposure(0f).layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(Kaolinite).size(1, 3))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Zeolite).size(1, 3))
                                        .layer(l -> l.weight(2).mat(GTMaterials.FullersEarth).size(1, 3))
                                        .layer(l -> l.weight(1).mat(GTMaterials.GlauconiteSand).size(1, 1));
                            });
                        });
            }
    );

    public static final GTOreDefinition WOLLASTONITE_VEIN = create(
            CTNHCore.id("wollastonite_vein"),
            vein -> {
                vein.clusterSize(36).weight(40).layer(WorldGenLayers.STONE).density(0.4f)
                        .dimensions(BuiltinDimensionTypes.OVERWORLD.location()).heightRangeUniform(120, 200)
                        .discardChanceOnAirExposure(0f).layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> {
                                    l.weight(2).mat(Dolomite).size(1, 3);
                                }).layer(l -> {
                                    l.weight(2).mat(Wollastonite).size(1, 3);
                                }).layer(l -> {
                                    l.weight(2).mat(GTMaterials.Trona).size(1, 3);
                                }).layer(l -> {
                                    l.weight(1).mat(GTMaterials.Andradite).size(1, 1);
                                });
                            });
                        });
            }
    );

    public static final GTOreDefinition GALENA_VEIN_TF = create(
            CTNHCore.id("galena_vein_tf"),
            vein -> {
                vein.clusterSize(30).weight(40).layer(CTNHWorldgenLayers.TWILIGHT).density(0.25f).dimensions(TWILIGHT_FOREST)
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
                                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE).density(0.4f).radius(5);
                        });
            }
    );

    public static final GTOreDefinition DIAMOND_VEIN_TF = create(
            CTNHCore.id("diamond_vein_tf"), vein -> {
                vein.clusterSize(30).weight(40).layer(CTNHWorldgenLayers.TWILIGHT).density(0.25f).dimensions(TWILIGHT_FOREST)
                        .heightRangeUniform(-30, 0).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Graphite).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Diamond).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Coal).size(1, 1));
                            });
                        }).surfaceIndicatorGenerator(indicator -> {
                            indicator.surfaceRock(GTMaterials.Diamond)
                                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE).density(0.4f).radius(5);
                        });
            }
    );

    public static final GTOreDefinition APATITE_VEIN_TF = create(
            CTNHCore.id("apatite_vein_tf"), vein -> {
                vein.clusterSize(30).weight(40).layer(CTNHWorldgenLayers.TWILIGHT).density(0.25f).dimensions(TWILIGHT_FOREST)
                        .heightRangeUniform(-30, 0).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(GTMaterials.Apatite).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.TricalciumPhosphate).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Pyrochlore).size(1, 1));
                            });
                        }).surfaceIndicatorGenerator(indicator -> {
                            indicator.surfaceRock(GTMaterials.Apatite)
                                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE).density(0.4f).radius(5);
                        });
            }
    );

    public static final GTOreDefinition SALTS_VEIN_TF = create(
            CTNHCore.id("salts_vein_tf"), vein -> {
                vein.clusterSize(30).weight(50).layer(CTNHWorldgenLayers.TWILIGHT).density(0.2f).dimensions(TWILIGHT_FOREST)
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
                                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE).density(0.4f).radius(5);
                        });
            }
    );

    // Moon 和其他星球的矿脉
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
            }
    );

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
            }
    );

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
            }
    );

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
            }
    );
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
            }
    );

    // Galena AD
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
            }
    );

    // Copper AD
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
            }
    );

    // Cassiterite AD
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
            }
    );

    // Desh AD
    public static final GTOreDefinition DESH_VEIN_AD = create(
            CTNHCore.id("desh_vein_ad"), vein -> {
                vein.clusterSize(24).weight(30).layer(CTNHWorldgenLayers.ADASTRA).density(0.3f).dimensions(MOON)
                        .heightRangeUniform(5, 40).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(Desh).size(2, 3))
                                        .layer(l -> l.weight(1).mat(ArcaneCrystal).size(1, 2));
                            });
                        });
            }
    );

    // Ostrum AD (Mars)
    public static final GTOreDefinition OSTRUM_VEIN_AD = create(
            CTNHCore.id("ostrum_vein_ad"), vein -> {
                vein.clusterSize(24).weight(30).layer(CTNHWorldgenLayers.ADASTRA).density(0.3f).dimensions(MARS)
                        .heightRangeUniform(5, 40).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(Ostrum).size(2, 3))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Scheelite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Tungstate).size(1, 1));
                            });
                        });
            }
    );

    // Arsenic AD
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
            }
    );

    // Pitchblende AD
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
            }
    );

    // Tuff Uraninite AD
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
            }
    );

    // Scheelite AD
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
            }
    );

    // Sulfur AD
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
            }
    );

    // Redstone AD
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
            }
    );

    // Nickel AD
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
            }
    );

    // Magnetite AD
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
            }
    );

    // Iron AD
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
            }
    );

    // Beryllium AD
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
            }
    );

    // Tetrahedrite AD
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
            }
    );

    // Salts AD
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
            }
    );

    // Naquadah AD Mars
    public static final GTOreDefinition NAQUADAH_VEIN_AD_MARS = create(
            CTNHCore.id("naquadah_vein_ad_mars"), vein -> {
                vein.clusterSize(48).weight(5).layer(CTNHWorldgenLayers.ADASTRA).density(0.4f).dimensions(MARS)
                        .heightRangeUniform(10, 90).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(CTNHMaterials.NaquadahOxideMixture).size(2, 4))
                                        .layer(l -> l.weight(1).mat(CTNHMaterials.EnrichedNaquadahOxideMixture).size(1, 2));
                            });
                        });
            }
    );
    // Mercury 矿脉
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
            }
    );

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
            }
    );

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
            }
    );

    public static final GTOreDefinition PLATINUM_VEIN_AD = create(
            CTNHCore.id("platinum_vein_ad"), vein -> {
                vein.clusterSize(24).weight(10).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f).dimensions(MERCURY)
                        .heightRangeUniform(-5, 25).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(CTNHMaterials.PlatinumOre).size(1, 3))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Chromite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Cooperite).size(1, 2));
                            });
                        });
            }
    );

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
            }
    );

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
            }
    );

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
            }
    );

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
            }
    );

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
            }
    );

    // Venus 矿脉
    public static final GTOreDefinition CALORITE_VEIN_AD = create(
            CTNHCore.id( "calorite_vein_ad"), vein -> {
                vein.clusterSize(24).weight(30).layer(CTNHWorldgenLayers.ADASTRA).density(0.3f).dimensions(VENUS)
                        .heightRangeUniform(5, 40).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(Calorite).size(2, 3))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Amethyst).size(1, 2));
                            });
                        });
            }
    );

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
            }
    );

    public static final GTOreDefinition IRIDIUM_VEIN_AD = create(
            CTNHCore.id("iridium_vein_ad"), vein -> {
                vein.clusterSize(24).weight(10).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f).dimensions(VENUS)
                        .heightRangeUniform(-5, 40).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(2).mat(GTMaterials.Nickel).size(2, 3))
                                        .layer(l -> l.weight(1).mat(CTNHMaterials.MeteoricTroilite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(CTNHMaterials.PalladiumOre).size(1, 2));
                            });
                        });
            }
    );

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
            }
    );

    public static final GTOreDefinition NAQUADAH_VEIN_AD = create(
            CTNHCore.id( "naquadah_vein_ad"), vein -> {
                vein.clusterSize(48).weight(30).layer(CTNHWorldgenLayers.ADASTRA).density(0.4f).dimensions(VENUS)
                        .heightRangeUniform(10, 90).discardChanceOnAirExposure(0f)
                        .layeredVeinGenerator(generator -> {
                            generator.buildLayerPattern(pattern -> {
                                pattern.layer(l -> l.weight(3).mat(CTNHMaterials.NaquadahOxideMixture).size(2, 3))
                                        .layer(l -> l.weight(1).mat(CTNHMaterials.EnrichedNaquadahOxideMixture).size(1, 2));
                            });
                        });
            }
    );

    // Glacio 矿脉
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
            }
    );

//    public static final GTOreDefinition NEUTRONIUM_VEIN_AD = create(
//            CTNHCore.id("neutronium_vein_ad"), vein -> {
//                vein.clusterSize(24).weight(10).layer(CTNHWorldgenLayers.ADASTRA).density(0.2f).dimensions(GLACIO)
//                        .heightRangeUniform(-50, -10).discardChanceOnAirExposure(0f)
//                        .layeredVeinGenerator(generator -> {
//                            generator.buildLayerPattern(pattern -> {
//                                pattern.layer(l -> l.weight(2).mat(GTMaterials.Neutronium).size(1, 2))
//                                        .layer(l -> l.weight(1).mat(InfinityCatalyst).size(1, 2))
//                                        .layer(l -> l.weight(1).mat(GTMaterials.Naquadria).size(1, 2))
//                                        .layer(l -> l.weight(1).mat(GTMaterials.Titanium).size(1, 2));
//                            });
//                        });
//            }
//    );
//
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
            }
    );

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
            }
    );
}
