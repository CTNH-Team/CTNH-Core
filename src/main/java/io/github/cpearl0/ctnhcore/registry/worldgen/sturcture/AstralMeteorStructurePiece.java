package io.github.cpearl0.ctnhcore.registry.worldgen.sturcture;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

public class AstralMeteorStructurePiece extends StructurePiece {
    public static final StructurePieceType TYPE = StructurePieceType.setPieceId(AstralMeteorStructurePiece::new,
            "ctnhastral_meteor");
    public BlockPos center;
    public float radius;
    protected AstralMeteorStructurePiece(BlockPos center, float coreRadius) {
        super(TYPE, 0, createBoundingBox(center));
        this.center = center;
        this.radius = coreRadius;
    }
    public static void register() {}
    private static BoundingBox createBoundingBox(BlockPos origin) {
        // Assume a normal max height of 128 blocks for most biomes,
        // meteors spawned at about y64 are 9x9 chunks large at most.
        int range = 4 * 16;

        ChunkPos chunkPos = new ChunkPos(origin);

        return new BoundingBox(chunkPos.getMinBlockX() - range, origin.getY(),
                chunkPos.getMinBlockZ() - range, chunkPos.getMaxBlockX() + range, origin.getY(),
                chunkPos.getMaxBlockZ() + range);
    }

    public AstralMeteorStructurePiece(CompoundTag tag) {
        super(TYPE, tag);
        this.center = BlockPos.of(tag.getLong("center"));
        this.radius = tag.getFloat("radius");
    }

    @Override
    protected void addAdditionalSaveData(StructurePieceSerializationContext structurePieceSerializationContext, CompoundTag tag) {
        tag.putLong("center", this.center.asLong());
        tag.putFloat("radius", this.radius);
    }

    @Override
    public void postProcess(WorldGenLevel worldGenLevel, StructureManager structureManager, ChunkGenerator chunkGenerator, RandomSource randomSource, BoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
        AstralMeteorPlacer.place(worldGenLevel, center, radius, boundingBox, randomSource);
    }
}
