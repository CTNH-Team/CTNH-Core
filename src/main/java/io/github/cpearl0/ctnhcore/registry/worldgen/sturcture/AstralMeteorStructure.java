package io.github.cpearl0.ctnhcore.registry.worldgen.sturcture;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;

import com.google.common.math.StatsAccumulator;
import com.mojang.serialization.Codec;

import java.util.Optional;

public class AstralMeteorStructure extends Structure {

    public static final Codec<AstralMeteorStructure> CODEC = simpleCodec(AstralMeteorStructure::new);

    public static StructureType<AstralMeteorStructure> TYPE;

    public AstralMeteorStructure(StructureSettings settings) {
        super(settings);
    }

    @Override
    protected Optional<GenerationStub> findGenerationPoint(GenerationContext generationContext) {
        return onTopOfChunkCenter(generationContext, Heightmap.Types.OCEAN_FLOOR_WG, structurePiecesBuilder -> {
            generatePieces(structurePiecesBuilder, generationContext);
        });
    }

    private static void generatePieces(StructurePiecesBuilder piecesBuilder, GenerationContext context) {
        var chunkPos = context.chunkPos();
        var random = context.random();
        var heightAccessor = context.heightAccessor();
        var generator = context.chunkGenerator();

        final int centerX = chunkPos.getMinBlockX() + random.nextInt(16);
        final int centerZ = chunkPos.getMinBlockZ() + random.nextInt(16);
        final float meteoriteRadius = random.nextFloat() * 6.0f + 2;
        final int yOffset = (int) Math.ceil(meteoriteRadius) + 1;

        var t2 = generator.getBiomeSource().getBiomesWithin(centerX, generator.getSeaLevel(), centerZ, 0,
                context.randomState().sampler());
        var spawnBiome = t2.stream().findFirst().orElseThrow();

        final Heightmap.Types heightmapType = Heightmap.Types.WORLD_SURFACE_WG;

        // Accumulate stats about the surrounding heightmap
        StatsAccumulator stats = new StatsAccumulator();
        int scanRadius = (int) Math.max(1, meteoriteRadius * 2);
        for (int x = -scanRadius; x <= scanRadius; x++) {
            for (int z = -scanRadius; z <= scanRadius; z++) {
                int h = generator.getBaseHeight(centerX + x, centerZ + z, heightmapType, heightAccessor,
                        context.randomState());
                stats.add(h);
            }
        }

        int centerY = (int) stats.mean();
        // Spawn it down a bit further with a high variance.
        if (stats.populationVariance() > 5) {
            centerY -= (stats.mean() - stats.min()) * .75;
        }

        // Offset caused by the meteorsize
        centerY -= yOffset;

        // If we seemingly don't have enough space to spawn (as can happen in flat chunks generators)
        // we snugly generate it on bedrock.
        centerY = Math.max(heightAccessor.getMinBuildHeight() + yOffset, centerY);

        BlockPos actualPos = new BlockPos(centerX, centerY, centerZ);

        piecesBuilder.addPiece(
                new AstralMeteorStructurePiece(actualPos, meteoriteRadius));
    }

    public static void init() {
        AstralMeteorStructurePiece.register();
        AstralMeteorStructure.TYPE = StructureType.register("ctnh_meteor", AstralMeteorStructure.CODEC);
    }

    @Override
    public StructureType<?> type() {
        return TYPE;
    }
}
