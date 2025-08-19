package io.github.cpearl0.ctnhcore.api.Pattern;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.block.ICoilType;
import com.gregtechceu.gtceu.api.capability.recipe.EURecipeCapability;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.pattern.TraceabilityPredicate;
import com.gregtechceu.gtceu.api.pattern.error.PatternStringError;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.block.CoilBlock;
import com.lowdragmc.lowdraglib.utils.BlockInfo;
import io.github.cpearl0.ctnhcore.api.CTNHAPI;
import io.github.cpearl0.ctnhcore.common.block.SpaceStructuralFramework;
import io.github.cpearl0.ctnhcore.common.block.blockdata.IPBData;
import io.github.cpearl0.ctnhcore.common.block.PhotovoltaicBlock;
import io.github.cpearl0.ctnhcore.common.block.blockdata.ISSFData;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;
import java.util.function.Supplier;

public class CTNHPredicates {
    public static TraceabilityPredicate PhotovoltaicBlock() {
        return (new TraceabilityPredicate((blockWorldState) -> {
            BlockState blockState = blockWorldState.getBlockState();

            for(Map.Entry<IPBData, Supplier<PhotovoltaicBlock>> entry : CTNHBlockMaps.PhotovoltaicBlock.entrySet()) {
                if (blockState.is((Block)((Supplier)entry.getValue()).get())) {
                    IPBData stats = (IPBData)entry.getKey();
                    Object currentCoil = blockWorldState.getMatchContext().getOrPut("IPBData", stats);
                    if (!currentCoil.equals(stats)) {
                        blockWorldState.setError(new PatternStringError("ctnh.error.pv"));
                        return false;
                    }

                    return true;
                }
            }

            return false;
        }, () -> (BlockInfo[])CTNHBlockMaps.PhotovoltaicBlock.entrySet().stream().sorted(Comparator.comparingInt((value) -> ((IPBData)value.getKey()).getTier())).map((pb) -> BlockInfo.fromBlockState(((PhotovoltaicBlock)((Supplier)pb.getValue()).get()).defaultBlockState())).toArray((x$0) -> new BlockInfo[x$0]))).addTooltips(new Component[]{Component.translatable("ctnh.spacephotovoltaicbasestation.jei.error.pv_block")});
    }
    public static TraceabilityPredicate SpaceStructuralFrameworkBlock() {
        return (new TraceabilityPredicate((blockWorldState) -> {
            BlockState blockState = blockWorldState.getBlockState();

            for(Map.Entry<ISSFData, Supplier<SpaceStructuralFramework>> entry : CTNHBlockMaps.SpaceStructuralFramework.entrySet()) {
                if (blockState.is((Block)((Supplier)entry.getValue()).get())) {
                    ISSFData stats = (ISSFData)entry.getKey();
                    Object currentCoil = blockWorldState.getMatchContext().getOrPut("ISSFData", stats);
                    if (!currentCoil.equals(stats)) {
                        blockWorldState.setError(new PatternStringError("ctnh.error.issf"));
                        return false;
                    }

                    return true;
                }
            }

            return false;
        }, () -> CTNHBlockMaps.SpaceStructuralFramework.entrySet().stream()
                .sorted(Comparator.comparingInt((value) -> value.getKey().getTier()))
                .map((pb) -> BlockInfo.fromBlockState((pb.getValue()).get().defaultBlockState()))
                .toArray(BlockInfo[]::new)))
                .addTooltips(Component.translatable("ctnh.spacephotovoltaicbasestation.jei.error.pv_block"));
    }
    static TraceabilityPredicate autoLaserAbilities(GTRecipeType... recipeType) {
        TraceabilityPredicate predicate = Predicates.autoAbilities(recipeType, false, false, true, true, true, true);
        for(var type : recipeType) {
            if (type.getMaxInputs(EURecipeCapability.CAP) > 0) {
                predicate = predicate.or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2).setPreviewCount(1)).or(Predicates.abilities(PartAbility.INPUT_LASER).setMaxGlobalLimited(1).setPreviewCount(1));
                break;
            }

            if (type.getMaxOutputs(EURecipeCapability.CAP) > 0) {
                predicate = predicate.or(Predicates.abilities(PartAbility.OUTPUT_ENERGY).setMaxGlobalLimited(2).setPreviewCount(1)).or(Predicates.abilities(PartAbility.OUTPUT_LASER).setMaxGlobalLimited(1).setPreviewCount(1));
                break;
            }
        }
        return predicate;
    }
    static TraceabilityPredicate tierBlock(Map<Integer, Supplier<? extends Block>> map, String tierType) {
        List<BlockInfo> blockInfos = new ArrayList<>();

        for(var entry : map.entrySet()) {
            var blockSupplier = entry.getValue();
            Block block = blockSupplier.get();
            blockInfos.add(BlockInfo.fromBlockState(block.defaultBlockState()));
        }

        return (new TraceabilityPredicate((state) -> {
            BlockState blockState = state.getBlockState();
            for (var entry : map.entrySet()) {
                if (blockState.is(entry.getValue().get())) {
                    int tier = entry.getKey();
                    int type = state.getMatchContext().getOrPut(tierType, tier);
                    if (type != tier) {
                        state.setError(new PatternStringError("ctnh.machine.pattern.error.tier"));
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            return false;
        }, () -> blockInfos.toArray(BlockInfo[]::new))).addTooltips(Component.translatable("ctnh.machine.pattern.error.tier"));
    }
    public static TraceabilityPredicate reactorCore() {
        return new TraceabilityPredicate(blockWorldState -> {
            var blockState = blockWorldState.getBlockState();
            for (Map.Entry<Integer, Supplier<Block>> entry : CTNHBlockMaps.ReactorCoreBlock.entrySet()) {
                if (blockState.is(entry.getValue().get())) {
                    var heat = entry.getKey();
                    int current_heat = (int) blockWorldState.getMatchContext().getOrPut("ReactorCore", 0);
                    current_heat += heat;
                    blockWorldState.getMatchContext().set("ReactorCore", current_heat);
                    return true;
                }
            }
            return false;
        }, () -> CTNHBlockMaps.ReactorCoreBlock.entrySet().stream()
                // sort to make autogenerated jei previews not pick random coils each game load
                .sorted(Comparator.comparingInt(Map.Entry::getKey))
                .map(block-> BlockInfo.fromBlockState(block.getValue().get().defaultBlockState()))
                .toArray(BlockInfo[]::new))
                .addTooltips(Component.translatable("ctnh.multiblock.pattern.error.reactor"));
    }
    public static TraceabilityPredicate coilBlock = tierBlock(CTNHBlockMaps.CoilBlock,"CoilType");

    public static TraceabilityPredicate plantCasings = tierBlock(CTNHBlockMaps.CasingBlock, "PlantCasing");

    public static TraceabilityPredicate pipeBlock = tierBlock(CTNHBlockMaps.PipeBlock, "Pipe");

    public static TraceabilityPredicate machineCasing = tierBlock(CTNHBlockMaps.MachineCasingBlock, "MachineCasing");
}
