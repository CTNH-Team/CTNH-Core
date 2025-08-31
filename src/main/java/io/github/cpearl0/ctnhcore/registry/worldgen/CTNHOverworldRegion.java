package io.github.cpearl0.ctnhcore.registry.worldgen;

import com.mojang.datafixers.util.Pair;
import io.github.cpearl0.ctnhcore.CTNHCore;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

import static terrablender.api.ParameterUtils.*;

public class CTNHOverworldRegion extends Region {
    public CTNHOverworldRegion(int weight) {
        super(CTNHCore.id("overworld_region"), RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();
        new ParameterPointListBuilder()
                .temperature(Temperature.HOT)
                .humidity(Humidity.ARID)
                .continentalness(Continentalness.INLAND)
                .erosion(Erosion.EROSION_0)
                .depth(ParameterUtils.Depth.SURFACE.parameter())
                .weirdness(ParameterUtils.Weirdness.FULL_RANGE.parameter())
                .offset(0.6f)
                .build().forEach(point -> builder.add(point, CTNHBiomes.PLAGUE_WASTELAND));
        builder.build().forEach(mapper);
    }
}
