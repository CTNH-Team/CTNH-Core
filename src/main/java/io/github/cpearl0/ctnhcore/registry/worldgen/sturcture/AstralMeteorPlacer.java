package io.github.cpearl0.ctnhcore.registry.worldgen.sturcture;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterialBlocks;
import io.github.cpearl0.ctnhcore.registry.CTNHBlocks;
import io.github.cpearl0.ctnhcore.registry.CTNHTagPrefixes;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;
import io.github.cpearl0.ctnhcore.registry.worldgen.AstralBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos.MutableBlockPos;

public class AstralMeteorPlacer {
    public static void place(LevelAccessor level, BlockPos pos, float radius, BoundingBox boundingBox,
                             RandomSource random) {
        var placer = new AstralMeteorPlacer(level, pos, radius, boundingBox, random);
        placer.place();
    }

    private final BlockState astralStone;
    private final MeteoriteBlockPutter putter = new MeteoriteBlockPutter();
    private final LevelAccessor level;
    private final RandomSource random;
    private final BlockPos pos;
    private final int x;
    private final int y;
    private final int z;
    private final double meteoriteSize;
    private final double squaredMeteoriteSize;
    private final double crater;

    private final BoundingBox boundingBox;

    private AstralMeteorPlacer(LevelAccessor level, BlockPos pos, float radius, BoundingBox boundingBox,
                            RandomSource random) {
        this.boundingBox = boundingBox;
        this.level = level;
        this.random = random;
        this.pos = pos;
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
        this.meteoriteSize = radius;
        this.squaredMeteoriteSize = this.meteoriteSize * this.meteoriteSize;

        double realCrater = this.meteoriteSize * 2 + 5;
        this.crater = realCrater * realCrater;

        this.astralStone = AstralBlocks.ASTRAL_STONE.getDefaultState();
    }

    public void place() {
        // creator
        this.placeCrater();

        this.placeMeteorite();

        // collapse blocks...
        this.decay();
        this.placeCraterLake();
    }

    private int minX(int x) {
        if (x < boundingBox.minX()) {
            return boundingBox.minX();
        } else if (x > boundingBox.maxX()) {
            return boundingBox.maxX();
        }
        return x;
    }

    private int minZ(int x) {
        if (x < boundingBox.minZ()) {
            return boundingBox.minZ();
        } else if (x > boundingBox.maxZ()) {
            return boundingBox.maxZ();
        }
        return x;
    }

    private int maxX(int x) {
        if (x < boundingBox.minX()) {
            return boundingBox.minX();
        } else if (x > boundingBox.maxX()) {
            return boundingBox.maxX();
        }
        return x;
    }

    private int maxZ(int x) {
        if (x < boundingBox.minZ()) {
            return boundingBox.minZ();
        } else if (x > boundingBox.maxZ()) {
            return boundingBox.maxZ();
        }
        return x;
    }

    private void placeCrater() {
        final int maxY = 255;
        BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();

        for (int j = y - 5; j <= maxY; j++) {
            blockPos.setY(j);

            for (int i = boundingBox.minX(); i <= boundingBox.maxX(); i++) {
                blockPos.setX(i);

                for (int k = boundingBox.minZ(); k <= boundingBox.maxZ(); k++) {
                    blockPos.setZ(k);
                    final double dx = i - x;
                    final double dz = k - z;
                    final double h = y - this.meteoriteSize + 1;

                    final double distanceFrom = dx * dx + dz * dz;

                    if (j > h + distanceFrom * 0.02) {
                        this.putter.put(level, blockPos, Blocks.AIR.defaultBlockState());
                    }
                }
            }
        }

        for (var e : level.getEntitiesOfClass(ItemEntity.class,
                new AABB(minX(x - 30), y - 5, minZ(z - 30), maxX(x + 30), y + 30, maxZ(z + 30)))) {
            e.discard();
        }
    }

    private void placeMeteorite() {
        // spawn meteor
        this.placeMeteoriteAstralStone();

        // If the meteorite's center is within the BB of the current placer, place the chest
        if (boundingBox.isInside(pos)) {
            placeChest();
        }
    }

    private void placeChest() {
        this.putter.put(level, pos, GTMaterialBlocks.MATERIAL_BLOCKS.get(TagPrefix.block, CTNHMaterials.Starmetal).getDefaultState());
    }

    private void placeMeteoriteAstralStone() {
        final int meteorXLength = minX(x - 8);
        final int meteorXHeight = maxX(x + 8);
        final int meteorZLength = minZ(z - 8);
        final int meteorZHeight = maxZ(z + 8);

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int i = meteorXLength; i <= meteorXHeight; i++) {
            pos.setX(i);
            for (int j = y - 8; j < y + 8; j++) {
                pos.setY(j);
                for (int k = meteorZLength; k <= meteorZHeight; k++) {
                    pos.setZ(k);
                    var dx = i - x;
                    var dy = j - y;
                    var dz = k - z;

                    if (dx * dx * 0.7 + dy * dy * (j > y ? 1.4 : 0.8) + dz * dz * 0.7 < this.squaredMeteoriteSize) {
                        // Leave a tiny room in the center
                        if (Math.abs(dx) <= 3 && Math.abs(dy) <= 3 && Math.abs(dz) <= 3) {
                            var rand = random.nextDouble();
                            if (rand >= 0.5) {
                                this.putter.put(level, pos, astralStone);
                            }
                            else {
                                this.putter.put(level, pos, GTMaterialBlocks.MATERIAL_BLOCKS.get(CTNHTagPrefixes.oreAstralStone, CTNHMaterials.Starmetal).getDefaultState());
                            }
                            if (dx == 0 && dy == 0 && dz == 0) {
                            }
                        } else {
                            this.putter.put(level, pos, astralStone);
                        }
                    }
                }
            }
        }
    }

    private void decay() {
        double randomShit = 0;

        final int meteorXLength = minX(x - 30);
        final int meteorXHeight = maxX(x + 30);
        final int meteorZLength = minZ(z - 30);
        final int meteorZHeight = maxZ(z + 30);

        MutableBlockPos blockPos = new MutableBlockPos();
        MutableBlockPos blockPosUp = new MutableBlockPos();
        MutableBlockPos blockPosDown = new MutableBlockPos();
        for (int i = meteorXLength; i <= meteorXHeight; i++) {
            blockPos.setX(i);
            blockPosUp.setX(i);
            blockPosDown.setX(i);
            for (int k = meteorZLength; k <= meteorZHeight; k++) {
                blockPos.setZ(k);
                blockPosUp.setZ(k);
                blockPosDown.setZ(k);
                for (int j = y - 9; j < y + 30; j++) {
                    blockPos.setY(j);
                    blockPosUp.setY(j + 1);
                    blockPosDown.setY(j - 1);
                    BlockState state = level.getBlockState(blockPos);

                    // TODO reconsider
                    if (state.canBeReplaced()) {
                        if (!level.isEmptyBlock(blockPosUp)) {
                            final BlockState stateUp = level.getBlockState(blockPosUp);
                            level.setBlock(blockPos, stateUp, Block.UPDATE_ALL);
                        } else if (randomShit < 100 * this.crater) {
                            final double dx = i - x;
                            final double dy = j - y;
                            final double dz = k - z;
                            final double dist = dx * dx + dy * dy + dz * dz;

                            final BlockState xf = level.getBlockState(blockPosDown);
                            if (!xf.canBeReplaced()) {
                                final double extraRange = random.nextDouble() * 0.6;
                                final double height = this.crater * (extraRange + 0.2)
                                        - Math.abs(dist - this.crater * 1.7);

                                if (!xf.isAir() && height > 0 && random.nextDouble() > 0.6) {
                                    randomShit++;
                                    getRandomFall(level, blockPos);
                                }
                            }
                        }
                    } else if (level.isEmptyBlock(blockPosUp) && random.nextDouble() > 0.4) { // decay.
                        final double dx = i - x;
                        final double dy = j - y;
                        final double dz = k - z;
                        double dr2 = dx * dx + dy * dy + dz * dz;

                        // Don't touch the center room!
                        if (!(Math.abs(dx) <= 1 && Math.abs(dy) <= 1 && Math.abs(dz) <= 1) && dr2 < this.crater * 1.6) {
                            getRandomInset(level, blockPos);
                        }
                    }
                }
            }
        }
    }

    /**
     * If it finds a single water block at y62, it will replace any air blocks below the sea level with water.
     */
    private void placeCraterLake() {
        final int maxY = level.getSeaLevel() - 1;
        MutableBlockPos blockPos = new MutableBlockPos();

        for (int j = y - 5; j <= maxY; j++) {
            blockPos.setY(j);

            for (int i = boundingBox.minX(); i <= boundingBox.maxX(); i++) {
                blockPos.setX(i);

                for (int k = boundingBox.minZ(); k <= boundingBox.maxZ(); k++) {
                    blockPos.setZ(k);
                    final double dx = i - x;
                    final double dz = k - z;
                    final double h = y - this.meteoriteSize + 1;

                    final double distanceFrom = dx * dx + dz * dz;

                    if (j > h + distanceFrom * 0.02) {
                        BlockState currentBlock = level.getBlockState(blockPos);
                        if (currentBlock.getBlock() == Blocks.AIR) {
                            this.putter.put(level, blockPos, CTNHMaterials.starlight.getFluid().defaultFluidState().createLegacyBlock());
                        }

                    }
                }
            }
        }
    }
    public void getRandomFall(LevelAccessor level, BlockPos pos) {
        var a = random.nextFloat();
        if (a > 0.9f) {
            this.putter.put(level, pos, AstralBlocks.ASTRAL_STONE.getDefaultState());
        } else if (a > 0.8f) {
            this.putter.put(level, pos, AstralBlocks.ASTRAL_COBBLESTONE.getDefaultState());
        } else if (a > 0.7f) {
            this.putter.put(level, pos, AstralBlocks.ASTRAL_DIRT.getDefaultState());
        } else {
            this.putter.put(level, pos, AstralBlocks.ASTRAL_SAND.getDefaultState());
        }
    }

    public void getRandomInset(LevelAccessor level, BlockPos pos) {
        var a = random.nextFloat();
        if (a > 0.9f) {
            this.putter.put(level, pos, AstralBlocks.ASTRAL_COBBLESTONE.getDefaultState());
        } else if (a > 0.8f) {
            this.putter.put(level, pos, AstralBlocks.ASTRAL_STONE.getDefaultState());
        } else if (a > 0.7f) {
            this.putter.put(level, pos, AstralBlocks.ASTRAL_GRASS_BLOCK.getDefaultState());
        } else if (a > 0.6f) {
            this.putter.put(level, pos, this.astralStone);
        } else if (a > 0.5f) {
            this.putter.put(level, pos, AstralBlocks.ASTRAL_SAND.getDefaultState());
        } else {
            this.putter.put(level, pos, Blocks.AIR.defaultBlockState());
        }
    }
    public class MeteoriteBlockPutter {
        public boolean put(LevelAccessor level, BlockPos pos, BlockState blk) {
            final BlockState original = level.getBlockState(pos);

            if (original.getBlock() == Blocks.BEDROCK || original == blk) {
                return false;
            }

            level.setBlock(pos, blk, Block.UPDATE_ALL);
            return true;
        }

    }
}
