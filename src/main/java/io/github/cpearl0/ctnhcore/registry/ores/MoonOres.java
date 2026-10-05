package io.github.cpearl0.ctnhcore.registry.ores;

import io.github.cpearl0.ctnhcore.data.materials.AdastraMaterials;
import io.github.cpearl0.ctnhcore.data.materials.PlatinumLineMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHWorldgenLayers;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import com.ctnh.ctnhastral.data.CATagPrefixes;
import com.ctnh.ctnhastral.registry.worldgen.MoonBlocks;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import static com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE;
import static io.github.cpearl0.ctnhcore.registry.CTNHOres.create;
import static io.github.cpearl0.ctnhcore.registry.CTNHWorlds.MOON;

/** Lunar ore composition and host rocks; placement and vein shapes use the standard GT generators. */
public final class MoonOres {

    private MoonOres() {}

    public static Map<Supplier<BlockState>, TagPrefix> oreHosts() {
        // Reuse existing ore variants for solid host rocks; ice, sand and crystals are not ore hosts.
        Map<Supplier<BlockState>, TagPrefix> hosts = new LinkedHashMap<>();
        hosts.put(MoonBlocks.MOON_STONE::getDefaultState, CATagPrefixes.oreMoonStone);
        hosts.put(MoonBlocks.MOON_ANORTHOSITE::getDefaultState, CATagPrefixes.oreMoonStone);
        hosts.put(MoonBlocks.MOON_BROKEN_ANORTHOSITE::getDefaultState, CATagPrefixes.oreMoonStone);
        hosts.put(MoonBlocks.SILICON_BRECCIA::getDefaultState, CATagPrefixes.oreMoonStone);
        hosts.put(Blocks.BASALT::defaultBlockState, TagPrefix.oreBasalt);
        hosts.put(Blocks.SMOOTH_BASALT::defaultBlockState, TagPrefix.oreBasalt);
        return hosts;
    }

    public static void init() {
        // Recreate definitions when GT rebuilds its ore registry during a resource reload.
        create("sheldonite_vein_moon", "Moon Sheldonite Vein", "月球谢尔顿矿脉",
                vein -> vein.clusterSize(40)
                        .density(0.3F).weight(40)
                        .layer(CTNHWorldgenLayers.MOON).dimensions(MOON)
                        .heightRangeUniform(5, 50)
                        .discardChanceOnAirExposure(0)
                        .layeredVeinGenerator(generator -> generator
                                .buildLayerPattern(pattern -> pattern
                                        .layer(l -> l.weight(3).mat(GTMaterials.Bornite).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Cooperite).size(1, 1))
                                        .layer(l -> l.weight(2).mat(PlatinumLineMaterials.PlatinumOre).size(1, 1))
                                        .layer(l -> l.weight(1).mat(PlatinumLineMaterials.PalladiumOre).size(1, 1))))
                        .surfaceIndicatorGenerator(indicator -> indicator
                                .surfaceRock(PlatinumLineMaterials.PlatinumOre)
                                .placement(ABOVE).density(0.4F).radius(5)));

        create("phosphate_vein", "Phosphate Vein", "磷酸盐矿脉",
                vein -> vein.clusterSize(30)
                        .density(0.3F).weight(40)
                        .layer(CTNHWorldgenLayers.MOON).dimensions(MOON)
                        .heightRangeUniform(-20, 50)
                        .discardChanceOnAirExposure(0)
                        .layeredVeinGenerator(generator -> generator
                                .buildLayerPattern(pattern -> pattern
                                        .layer(l -> l.weight(3).mat(GTMaterials.Apatite).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.TricalciumPhosphate).size(1, 3))
                                        .layer(l -> l.weight(2).mat(CTNHMaterials.TrisodiumPhosphate).size(1, 2))))
                        .surfaceIndicatorGenerator(indicator -> indicator
                                .surfaceRock(CTNHMaterials.TrisodiumPhosphate)
                                .placement(ABOVE).density(0.4F).radius(5)));

        create("bauxite_vein", "Moon Bauxite Vein", "月球铝土矿脉",
                vein -> vein.clusterSize(36)
                        .density(0.3F).weight(80)
                        .layer(CTNHWorldgenLayers.MOON).dimensions(MOON)
                        .heightRangeUniform(10, 80)
                        .discardChanceOnAirExposure(0)
                        .layeredVeinGenerator(generator -> generator
                                .buildLayerPattern(pattern -> pattern
                                        .layer(l -> l.weight(2).mat(GTMaterials.Bauxite).size(1, 4))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Ilmenite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(CTNHMaterials.Alumina).size(1, 1))))
                        .surfaceIndicatorGenerator(indicator -> indicator
                                .surfaceRock(GTMaterials.Bauxite)
                                .placement(ABOVE).density(0.4F).radius(5)));

        create("ilmenite_vein", "Ilmenite Vein", "钛铁矿脉",
                vein -> vein.clusterSize(24)
                        .density(0.2F).weight(16)
                        .layer(CTNHWorldgenLayers.MOON).dimensions(MOON)
                        // Keep a 12-block radius above the Moon's bottom bedrock layers.
                        .heightRangeUniform(-48, 10)
                        .discardChanceOnAirExposure(0)
                        .layeredVeinGenerator(generator -> generator
                                .buildLayerPattern(pattern -> pattern
                                        .layer(l -> l.weight(3).mat(GTMaterials.Ilmenite).size(1, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Chromite).size(1, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Uvarovite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Perlite).size(1, 1))))
                        .surfaceIndicatorGenerator(indicator -> indicator
                                .surfaceRock(GTMaterials.Ilmenite)
                                .placement(ABOVE).density(0.4F).radius(5)));

        create("desh_vein_ad", "Moon Desh Vein", "月球戴斯矿脉",
                vein -> vein.clusterSize(24)
                        .density(0.3F).weight(30)
                        .layer(CTNHWorldgenLayers.MOON).dimensions(MOON)
                        .heightRangeUniform(5, 40)
                        .discardChanceOnAirExposure(0)
                        .layeredVeinGenerator(generator -> generator
                                .buildLayerPattern(pattern -> pattern
                                        .layer(l -> l.weight(3).mat(AdastraMaterials.Desh).size(2, 3))
                                        .layer(l -> l.weight(1).mat(CTNHMaterials.ArcaneCrystal).size(1, 2))))
                        .surfaceIndicatorGenerator(indicator -> indicator
                                .surfaceRock(AdastraMaterials.Desh)
                                .placement(ABOVE).density(0.4F).radius(5)));

        create("monazite_vein_moon", "Moon Monazite Vein", "月球独居石矿脉",
                vein -> vein.clusterSize(24)
                        .density(0.2F).weight(30)
                        .layer(CTNHWorldgenLayers.MOON).dimensions(MOON)
                        .heightRangeUniform(20, 40)
                        .discardChanceOnAirExposure(0)
                        .layeredVeinGenerator(generator -> generator
                                .buildLayerPattern(pattern -> pattern
                                        .layer(l -> l.weight(3).mat(GTMaterials.Bastnasite).size(2, 4))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Monazite).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Neodymium).size(1, 1))))
                        .surfaceIndicatorGenerator(indicator -> indicator
                                .surfaceRock(GTMaterials.Monazite)
                                .placement(ABOVE).density(0.4F).radius(5)));

        create("quartzite_vein_moon", "Moon Quartzite Vein", "月球石英岩矿脉",
                vein -> vein.clusterSize(24)
                        .density(0.3F).weight(20)
                        .layer(CTNHWorldgenLayers.MOON).dimensions(MOON)
                        .heightRangeUniform(30, 80)
                        .discardChanceOnAirExposure(0)
                        .layeredVeinGenerator(generator -> generator
                                .buildLayerPattern(pattern -> pattern
                                        .layer(l -> l.weight(3).mat(GTMaterials.Quartzite).size(2, 4))
                                        .layer(l -> l.weight(3).mat(GTMaterials.Barite).size(2, 4))
                                        .layer(l -> l.weight(3).mat(GTMaterials.CertusQuartz).size(2, 4))))
                        .surfaceIndicatorGenerator(indicator -> indicator
                                .surfaceRock(GTMaterials.Quartzite)
                                .placement(ABOVE).density(0.4F).radius(5)));

        create("molybdenum_vein_moon", "Moon Molybdenite Vein", "月球辉钼矿脉",
                vein -> vein.clusterSize(25)
                        .density(0.25F).weight(5)
                        .layer(CTNHWorldgenLayers.MOON).dimensions(MOON)
                        .heightRangeUniform(20, 50)
                        .discardChanceOnAirExposure(0)
                        .layeredVeinGenerator(generator -> generator
                                .buildLayerPattern(pattern -> pattern
                                        .layer(l -> l.weight(3).mat(GTMaterials.Wulfenite).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Molybdenite).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Molybdenum).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Powellite).size(1, 1))))
                        .surfaceIndicatorGenerator(indicator -> indicator
                                .surfaceRock(GTMaterials.Molybdenite)
                                .placement(ABOVE).density(0.4F).radius(5)));

        create("galena_vein_moon", "Moon Galena Vein", "月球方铅矿脉",
                vein -> vein.clusterSize(30)
                        .density(0.25F).weight(40)
                        .layer(CTNHWorldgenLayers.MOON).dimensions(MOON)
                        .heightRangeUniform(-15, 45)
                        .discardChanceOnAirExposure(0)
                        .layeredVeinGenerator(generator -> generator
                                .buildLayerPattern(pattern -> pattern
                                        .layer(l -> l.weight(3).mat(GTMaterials.Galena).size(2, 4))
                                        .layer(l -> l.weight(2).mat(GTMaterials.Silver).size(1, 1))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Lead).size(1, 1))))
                        .surfaceIndicatorGenerator(indicator -> indicator
                                .surfaceRock(GTMaterials.Galena)
                                .placement(ABOVE).density(0.4F).radius(5)));

        create("copper_vein_moon", "Moon Copper Vein", "月球铜矿脉",
                vein -> vein.clusterSize(36)
                        .density(0.3F).weight(80)
                        .layer(CTNHWorldgenLayers.MOON).dimensions(MOON)
                        .heightRangeUniform(-40, 15)
                        .discardChanceOnAirExposure(0)
                        .layeredVeinGenerator(generator -> generator
                                .buildLayerPattern(pattern -> pattern
                                        .layer(l -> l.weight(2).mat(GTMaterials.Chalcopyrite).size(2, 3))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Iron).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Pyrite).size(1, 2))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Copper).size(1, 2))))
                        .surfaceIndicatorGenerator(indicator -> indicator
                                .surfaceRock(GTMaterials.Chalcopyrite)
                                .placement(ABOVE).density(0.4F).radius(5)));

        create("cassiterite_vein_moon", "Moon Cassiterite Vein", "月球锡石矿脉",
                vein -> vein.clusterSize(36)
                        .density(0.4F).weight(50)
                        .layer(CTNHWorldgenLayers.MOON).dimensions(MOON)
                        .heightRangeUniform(10, 80)
                        .discardChanceOnAirExposure(0)
                        .layeredVeinGenerator(generator -> generator
                                .buildLayerPattern(pattern -> pattern
                                        .layer(l -> l.weight(3).mat(GTMaterials.Tin).size(2, 3))
                                        .layer(l -> l.weight(1).mat(GTMaterials.Cassiterite).size(1, 2))))
                        .surfaceIndicatorGenerator(indicator -> indicator
                                .surfaceRock(GTMaterials.Cassiterite)
                                .placement(ABOVE).density(0.4F).radius(5)));
    }
}
