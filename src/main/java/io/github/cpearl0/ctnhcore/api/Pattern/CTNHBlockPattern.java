//package io.github.cpearl0.ctnhcore.api.Pattern;
//
//import com.gregtechceu.gtceu.api.pattern.BlockPattern;
//import com.gregtechceu.gtceu.api.pattern.TraceabilityPredicate;
//import com.gregtechceu.gtceu.api.pattern.predicates.SimplePredicate;
//import com.gregtechceu.gtceu.api.pattern.util.RelativeDirection;
//import com.lowdragmc.lowdraglib.utils.BlockInfo;
//import com.mojang.datafixers.util.Pair;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.Direction;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class CTNHBlockPattern extends BlockPattern {
//    public CTNHBlockPattern(TraceabilityPredicate[][][] predicatesIn, RelativeDirection[] structureDir, int[][] aisleRepetitions, int[] centerOffset) {
//        super(predicatesIn, structureDir, aisleRepetitions, centerOffset);
//    }
//    public BlockInfo[][][] getPreview(int[] repetition, int index) {
//        Map<SimplePredicate, Integer> cacheGlobal = new HashMap<>();
//        Map<BlockPos, BlockInfo> blocks = new HashMap<>();
//        int minX = Integer.MAX_VALUE;
//        int minY = Integer.MAX_VALUE;
//        int minZ = Integer.MAX_VALUE;
//        int maxX = Integer.MIN_VALUE;
//        int maxY = Integer.MIN_VALUE;
//        int maxZ = Integer.MIN_VALUE;
//
//        int x = 0;
//        for (int l = 0; l < fingerLength; l++) {
//            for (int i = 0; i < repetition[l]; i++) {
//                Map<SimplePredicate, Integer> cacheLayer = new HashMap<>();
//                for (int y = 0; y < thumbLength; y++) {
//                    for (int z = 0; z < palmLength; z++) {
//                        SimplePredicate predicate = blockMatches[l][y][z];
//                        Pair<List<BlockInfo>, Boolean> predicateInfo = findPredicateInfo(predicate, cacheLayer, cacheGlobal);
//                        List<BlockInfo> infos = predicateInfo.getFirst();
//                        boolean preview = predicateInfo.getSecond();
//
//                        BlockInfo info;
//                        if (preview && index > -1) {
//                            info = infos != null ? NNUtils.getOrLast(infos, index) : BlockInfo.EMPTY;
//                        } else {
//                            info = infos != null && !infos.isEmpty() ? infos.get(0) : BlockInfo.EMPTY;
//                        }
//
//                        BlockPos pos = setActualRelativeOffset(z, y, x, Direction.NORTH);
//                        blocks.put(pos, info);
//                        minX = Math.min(minX, pos.getX());
//                        minY = Math.min(minY, pos.getY());
//                        minZ = Math.min(minZ, pos.getZ());
//                        maxX = Math.max(maxX, pos.getX());
//                        maxY = Math.max(maxY, pos.getY());
//                        maxZ = Math.max(maxZ, pos.getZ());
//                    }
//                }
//                x++;
//            }
//        }
//
//        BlockInfo[][][] result = new BlockInfo[maxX - minX + 1][maxY - minY + 1][maxZ - minZ + 1];
//
//        for (Map.Entry<BlockPos, BlockInfo> entry : blocks.entrySet()) {
//            BlockPos pos = entry.getKey();
//            BlockInfo info = entry.getValue();
//
//            resetFacing(
//                    pos, info.blockState, null,
//                    (p, f) -> canResetFacing(blocks, info, p, f),
//                    state -> {
//                        if (state.hasProperty(NNBlockProperties.STRUCTURE_TIER)) {
//                            info.blockState = state.setValue(NNBlockProperties.STRUCTURE_TIER, structureTier);
//                        } else {
//                            info.blockState = state;
//                        }
//                    }
//            );
//
//            result[pos.getX() - minX][pos.getY() - minY][pos.getZ() - minZ] = info;
//        }
//
//        return result;
//    }
//}
