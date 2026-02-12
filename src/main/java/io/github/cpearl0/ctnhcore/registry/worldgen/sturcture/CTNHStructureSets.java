package io.github.cpearl0.ctnhcore.registry.worldgen.sturcture;

import io.github.cpearl0.ctnhcore.CTNHCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

import java.util.List;

public class CTNHStructureSets {
    public static final ResourceKey<StructureSet> ASTRAL_METEOR_SET = ResourceKey
            .create(Registries.STRUCTURE_SET, CTNHCore.id("meteorite"));
    public static void bootstrap(BootstapContext<StructureSet> context) {
        var structures = context.lookup(Registries.STRUCTURE);
        var meteorite = structures.getOrThrow(CTNHStructures.ASTRAL_METEOR);

        var structureSet = new StructureSet(
                List.of(StructureSet.entry(meteorite)),
                new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 124895654));

        context.register(ASTRAL_METEOR_SET, structureSet);
    }
}
