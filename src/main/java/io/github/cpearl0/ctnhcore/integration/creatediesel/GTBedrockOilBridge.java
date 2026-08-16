package io.github.cpearl0.ctnhcore.integration.creatediesel;

import com.gregtechceu.gtceu.api.data.worldgen.bedrockfluid.BedrockFluidVeinSavedData;
import com.gregtechceu.gtceu.api.data.worldgen.bedrockfluid.FluidVeinWorldEntry;
import com.gregtechceu.gtceu.common.data.GTBedrockFluids;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

import com.jesz.createdieselgenerators.CDGConfig;

/**
 * Adapts GTCEu bedrock oil veins to Create Diesel Generators' chunk-oil model.
 */
public final class GTBedrockOilBridge {

    private static final int MINIMUM_PUMPJACK_AVAILABLE = 8_000;
    public static final int MAX_SEARCH_RADIUS_CHUNKS = 4;

    private GTBedrockOilBridge() {}

    public static boolean hasOilVein(ServerLevel level, ChunkPos chunkPos) {
        return findOilVein(level, chunkPos) != null;
    }

    public static OilVeinInfo getOilVeinInfo(ServerLevel level, ChunkPos chunkPos) {
        OilVein oilVein = findOilVein(level, chunkPos);
        return oilVein == null ? null : createInfo(chunkPos, oilVein);
    }

    public static Fluid getOilFluid(ServerLevel level, ChunkPos chunkPos) {
        OilVein oilVein = findOilVein(level, chunkPos);
        if (oilVein == null) return null;

        var definition = oilVein.entry().getDefinition();
        return definition == null ? null : definition.getStoredFluid().get();
    }

    /**
     * Finds the nearest GTCEu oil-bearing chunk without scanning farther once a
     * closer result is geometrically impossible.
     */
    public static OilVeinInfo findNearestOilVein(ServerLevel level, BlockPos origin) {
        if (level.dimension() != Level.OVERWORLD) return null;

        ChunkPos originChunk = new ChunkPos(origin);
        OilVeinInfo current = getOilVeinInfo(level, originChunk);
        if (current != null) return current;

        OilVeinInfo nearest = null;
        double nearestDistanceSquared = Double.MAX_VALUE;
        for (int radius = 1; radius <= MAX_SEARCH_RADIUS_CHUNKS; radius++) {
            for (int offset = -radius; offset <= radius; offset++) {
                SearchResult north = checkCandidate(level, origin, originChunk.x + offset, originChunk.z - radius,
                        nearest, nearestDistanceSquared);
                nearest = north.info();
                nearestDistanceSquared = north.distanceSquared();

                SearchResult south = checkCandidate(level, origin, originChunk.x + offset, originChunk.z + radius,
                        nearest, nearestDistanceSquared);
                nearest = south.info();
                nearestDistanceSquared = south.distanceSquared();
            }
            for (int offset = -radius + 1; offset < radius; offset++) {
                SearchResult west = checkCandidate(level, origin, originChunk.x - radius, originChunk.z + offset,
                        nearest, nearestDistanceSquared);
                nearest = west.info();
                nearestDistanceSquared = west.distanceSquared();

                SearchResult east = checkCandidate(level, origin, originChunk.x + radius, originChunk.z + offset,
                        nearest, nearestDistanceSquared);
                nearest = east.info();
                nearestDistanceSquared = east.distanceSquared();
            }

            if (nearest != null && radius < MAX_SEARCH_RADIUS_CHUNKS &&
                    minimumDistanceSquaredToRing(origin, originChunk, radius + 1) >= nearestDistanceSquared) {
                break;
            }
        }
        return nearest;
    }

    /**
     * Returns a positive, finite value suitable for CDG's scanner thresholds.
     */
    public static int getScannerAmount(ServerLevel level, ChunkPos chunkPos) {
        OilVein oilVein = findOilVein(level, chunkPos);
        if (oilVein == null) return 0;

        int lowerThreshold = Math.max(1, CDGConfig.OIL_CHUNK_THRESHOLD.get());
        int upperThreshold = Math.max(lowerThreshold + 1, CDGConfig.OIL_CHUNK_INFINITE_THRESHOLD.get());
        int operations = Mth.clamp(oilVein.entry().getOperationsRemaining(), 0,
                BedrockFluidVeinSavedData.MAXIMUM_VEIN_OPERATIONS);
        long scaled = lowerThreshold +
                (long) (upperThreshold - lowerThreshold) * operations /
                        BedrockFluidVeinSavedData.MAXIMUM_VEIN_OPERATIONS;
        return (int) Math.min(Integer.MAX_VALUE - 1L, scaled);
    }

    /**
     * Estimates the remaining fluid for CDG's pumpjack clamp and display. The value
     * is deliberately finite so CDG still executes its depletion callback.
     */
    public static int getPumpjackAvailable(ServerLevel level, ChunkPos chunkPos) {
        OilVein oilVein = findOilVein(level, chunkPos);
        if (oilVein == null) return 0;

        int effectiveYield = getEffectiveYield(oilVein.entry());
        int operations = Math.max(1_000, oilVein.entry().getOperationsRemaining());
        long estimatedAmount = Math.max(MINIMUM_PUMPJACK_AVAILABLE, (long) effectiveYield * operations);
        return (int) Math.min(Integer.MAX_VALUE - 1L, estimatedAmount);
    }

    /**
     * Converts successfully pumped millibuckets into GTCEu depletion operations.
     * Fractional operations use stochastic rounding so no additional saved data is
     * required and the long-term depletion rate matches an MV fluid drilling rig.
     */
    public static void consumePumpedFluid(ServerLevel level, ChunkPos chunkPos, int pumpedAmount) {
        if (pumpedAmount <= 0) return;

        OilVein oilVein = findOilVein(level, chunkPos);
        if (oilVein == null || oilVein.entry().getOperationsRemaining() <= 0) return;

        int effectiveYield = Math.max(1, getEffectiveYield(oilVein.entry()));
        int operations = pumpedAmount / effectiveYield;
        int remainder = pumpedAmount % effectiveYield;
        if (remainder > 0 && level.getRandom().nextInt(effectiveYield) < remainder) {
            operations++;
        }
        if (operations > 0) {
            oilVein.data().depleteVein(chunkPos.x, chunkPos.z, operations, true);
        }
    }

    private static int getEffectiveYield(FluidVeinWorldEntry entry) {
        var definition = entry.getDefinition();
        if (definition == null) return 0;
        return Math.max(definition.getDepletedYield(),
                entry.getFluidYield() * entry.getOperationsRemaining() /
                        BedrockFluidVeinSavedData.MAXIMUM_VEIN_OPERATIONS);
    }

    private static OilVeinInfo createInfo(ChunkPos chunkPos, OilVein oilVein) {
        var definition = oilVein.entry().getDefinition();
        Component fluidName = definition == null ? Component.empty() :
                new FluidStack(definition.getStoredFluid().get(), 1).getDisplayName();
        return new OilVeinInfo(chunkPos, fluidName, oilVein.entry().getOperationsRemaining(),
                getEffectiveYield(oilVein.entry()));
    }

    private static SearchResult checkCandidate(ServerLevel level, BlockPos origin, int chunkX, int chunkZ,
                                               OilVeinInfo nearest, double nearestDistanceSquared) {
        ChunkPos candidatePos = new ChunkPos(chunkX, chunkZ);
        double distanceSquared = distanceSquaredToChunk(origin, candidatePos);
        if (distanceSquared >= nearestDistanceSquared) {
            return new SearchResult(nearest, nearestDistanceSquared);
        }

        OilVeinInfo candidate = getOilVeinInfo(level, candidatePos);
        if (candidate == null) {
            return new SearchResult(nearest, nearestDistanceSquared);
        }
        return new SearchResult(candidate, distanceSquared);
    }

    private static double minimumDistanceSquaredToRing(BlockPos origin, ChunkPos originChunk, int radius) {
        double minimum = Double.MAX_VALUE;
        for (int offset = -radius; offset <= radius; offset++) {
            minimum = Math.min(minimum,
                    distanceSquaredToChunk(origin, new ChunkPos(originChunk.x + offset, originChunk.z - radius)));
            minimum = Math.min(minimum,
                    distanceSquaredToChunk(origin, new ChunkPos(originChunk.x + offset, originChunk.z + radius)));
        }
        for (int offset = -radius + 1; offset < radius; offset++) {
            minimum = Math.min(minimum,
                    distanceSquaredToChunk(origin, new ChunkPos(originChunk.x - radius, originChunk.z + offset)));
            minimum = Math.min(minimum,
                    distanceSquaredToChunk(origin, new ChunkPos(originChunk.x + radius, originChunk.z + offset)));
        }
        return minimum;
    }

    public static double distanceSquaredToChunk(BlockPos origin, ChunkPos chunkPos) {
        double closestX = Mth.clamp(origin.getX(), chunkPos.getMinBlockX(), chunkPos.getMinBlockX() + 15);
        double closestZ = Mth.clamp(origin.getZ(), chunkPos.getMinBlockZ(), chunkPos.getMinBlockZ() + 15);
        double deltaX = closestX - origin.getX();
        double deltaZ = closestZ - origin.getZ();
        return deltaX * deltaX + deltaZ * deltaZ;
    }

    private static OilVein findOilVein(ServerLevel level, ChunkPos chunkPos) {
        BedrockFluidVeinSavedData data = BedrockFluidVeinSavedData.getOrCreate(level);
        FluidVeinWorldEntry entry = data.getFluidVeinWorldEntry(chunkPos.x, chunkPos.z);
        var definition = entry.getDefinition();
        if (definition != GTBedrockFluids.HEAVY_OIL && definition != GTBedrockFluids.LIGHT_OIL &&
                definition != GTBedrockFluids.OIL && definition != GTBedrockFluids.RAW_OIL) {
            return null;
        }
        return new OilVein(data, entry);
    }

    private record OilVein(BedrockFluidVeinSavedData data, FluidVeinWorldEntry entry) {}

    private record SearchResult(OilVeinInfo info, double distanceSquared) {}

    public record OilVeinInfo(ChunkPos chunkPos, Component fluidName, int operationsRemaining, int effectiveYield) {

        public int reservePercentage() {
            return Mth.clamp((int) ((long) operationsRemaining * 100 /
                    BedrockFluidVeinSavedData.MAXIMUM_VEIN_OPERATIONS), 0, 100);
        }
    }
}
