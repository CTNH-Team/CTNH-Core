package io.github.cpearl0.ctnhcore.api.Pattern;

import appeng.api.config.Actionable;
import appeng.api.networking.IGrid;
import appeng.api.networking.security.IActionSource;
import appeng.api.stacks.AEFluidKey;
import appeng.api.stacks.AEItemKey;
import appeng.api.storage.MEStorage;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.pattern.BlockPattern;
import com.gregtechceu.gtceu.api.pattern.MultiblockState;
import com.gregtechceu.gtceu.api.pattern.TraceabilityPredicate;
import com.gregtechceu.gtceu.api.pattern.predicates.SimplePredicate;
import com.gregtechceu.gtceu.api.pattern.util.RelativeDirection;
import com.gregtechceu.gtceu.common.block.CoilBlock;

import com.lowdragmc.lowdraglib.utils.BlockInfo;

import io.github.cpearl0.ctnhcore.CTNHConfig;
import io.github.cpearl0.ctnhcore.common.item.MEAdvancedTerminalBehavior;
import io.github.cpearl0.ctnhcore.utils.OrientedItem;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper;
import net.minecraftforge.items.IItemHandler;

import com.mojang.datafixers.util.Pair;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import static net.minecraft.world.level.block.Block.UPDATE_CLIENTS;

public class AsynBlockPattern extends BlockPattern {

    private static final int BLOCKS_PER_TICK = CTNHConfig.INSTANCE.terminal.blocksPerTick; // 每tick放置的方块数量
    private static final int BLOCKS_PER_TICK_WITH_AE = CTNHConfig.INSTANCE.terminal.blocksPerTickWithAE;
    //优先队列，按照y由低到高放置
    private final Queue<BuildTask> buildQueue = new PriorityQueue<>(
            Comparator.comparingInt((BuildTask task) -> task.pos.getY())
            .thenComparingInt(task -> task.pos.getX())
            .thenComparingInt(task -> task.pos.getZ())
            );
    //private final Queue<BuildTask> buildQueue = new ConcurrentLinkedQueue<>();
    @Getter
    private boolean completed = false;

    static Direction[] FACINGS = { Direction.SOUTH, Direction.NORTH, Direction.WEST, Direction.EAST, Direction.UP,
            Direction.DOWN };
    static Direction[] FACINGS_H = { Direction.SOUTH, Direction.NORTH, Direction.WEST, Direction.EAST };

    public final int[][] aisleRepetitions;
    public final RelativeDirection[] structureDir;
    protected final TraceabilityPredicate[][][] blockMatches; // [z][y][x]
    protected final int fingerLength; // z size
    protected final int thumbLength; // y size
    protected final int palmLength; // x size
    protected final int[] centerOffset; // x, y, z, minZ, maxZ

    protected MEAdvancedTerminalBehavior.AutoBuildSetting setting;

    public AsynBlockPattern(TraceabilityPredicate[][][] predicatesIn, RelativeDirection[] structureDir, int[][] aisleRepetitions, int[] centerOffset) {
        super(predicatesIn, structureDir, aisleRepetitions, centerOffset);
        this.blockMatches = predicatesIn;
        this.fingerLength = predicatesIn.length;
        this.structureDir = structureDir;
        this.aisleRepetitions = aisleRepetitions;

        if (this.fingerLength > 0) {
            this.thumbLength = predicatesIn[0].length;

            if (this.thumbLength > 0) {
                this.palmLength = predicatesIn[0][0].length;
            } else {
                this.palmLength = 0;
            }
        } else {
            this.thumbLength = 0;
            this.palmLength = 0;
        }

        this.centerOffset = centerOffset;


    }

    public static AsynBlockPattern getAdvancedBlockPattern(BlockPattern blockPattern) {
        try {
            Class<?> clazz = BlockPattern.class;
            // blockMatches
            Field blockMatchesField = clazz.getDeclaredField("blockMatches");
            blockMatchesField.setAccessible(true);
            TraceabilityPredicate[][][] blockMatches = (TraceabilityPredicate[][][]) blockMatchesField.get(blockPattern);
            // structureDir
            Field structureDirField = clazz.getDeclaredField("structureDir");
            structureDirField.setAccessible(true);
            RelativeDirection[] structureDir = (RelativeDirection[]) structureDirField.get(blockPattern);
            // aisleRepetitions
            Field aisleRepetitionsField = clazz.getDeclaredField("aisleRepetitions");
            aisleRepetitionsField.setAccessible(true);
            int[][] aisleRepetitions = (int[][]) aisleRepetitionsField.get(blockPattern);
            // centerOffset
            Field centerOffsetField = clazz.getDeclaredField("centerOffset");
            centerOffsetField.setAccessible(true);
            int[] centerOffset = (int[]) centerOffsetField.get(blockPattern);

            return new AsynBlockPattern(blockMatches, structureDir, aisleRepetitions, centerOffset);
        } catch (Exception ignored) {}
        return null;
    }

    public void tick() {
        if (completed || buildQueue.isEmpty()) return;

        int num = setting.getUseAEStorage() == 0 ? BLOCKS_PER_TICK : BLOCKS_PER_TICK_WITH_AE;
        // 每tick处理一定数量的方块
        try {
            for (int i = 0; i < num && !buildQueue.isEmpty(); i++) {
                BuildTask task = buildQueue.poll();
                if (task != null) {
                    task.execute();
                }
            }
        } catch (Exception e) {
            completed = true;
            throw new RuntimeException(e);
        }


        if (buildQueue.isEmpty()) {
            completed = true;

        }
    }

    public void startAutoBuild(Player player, MultiblockState worldState,
                               MEAdvancedTerminalBehavior.AutoBuildSetting autoBuildSetting) {
        // 初始化阶段
        this.setting = autoBuildSetting;
        BuildContext context = initializeBuildContext(player, worldState, autoBuildSetting);

        // 计算每层的重复次数
        int[] repeatCounts = calculateLayerRepeatCounts(autoBuildSetting);

        // 生成所有建造任务
        for (int c = 0, z = context.minZ++, r; c < this.fingerLength; c++) {
            for (r = 0; r < repeatCounts[c]; r++) {
                context.worldState.getLayerCount().clear();
                generateLayerTasks(context, c, z);
                z++;
            }
        }

        // 执行分层构建
        //executeLayeredBuilding(context, repeatCounts);

        // 调整所有方块的朝向
        //adjustAllBlockFacings(context);
    }
    private void generateLayerTasks(BuildContext context, int layerIndex, int z) {
        for (int b = 0, y = -centerOffset[1]; b < this.thumbLength; b++, y++) {
            for (int a = 0, x = -centerOffset[0]; a < this.palmLength; a++, x++) {
                BlockPos pos = calculateBlockPosition(context, x, y, z);
                TraceabilityPredicate predicate = this.blockMatches[layerIndex][b][a];
                if(predicate.isAir() || predicate.isAny()) continue;
                // 创建建造任务并加入队列
                buildQueue.add(new BuildTask(context, pos, predicate, layerIndex, b, a));
            }
        }

    }

    private class BuildTask {
        private final BuildContext context;
        public final BlockPos pos;
        private final TraceabilityPredicate predicate;
        private final int layerIndex;
        private final int yIndex;
        private final int xIndex;

        public BuildTask(BuildContext context, BlockPos pos, TraceabilityPredicate predicate,
                         int layerIndex, int yIndex, int xIndex) {
            this.context = context;
            this.pos = pos;
            this.predicate = predicate;
            this.layerIndex = layerIndex;
            this.yIndex = yIndex;
            this.xIndex = xIndex;
        }

        public void execute() {

            updateWorldState(context.worldState, pos, predicate);

            if (handleExistingBlock(context, pos, predicate)) return;


            BlockInfo[] infos = determineRequiredBlockInfo(context, predicate);
            if (infos == null) return;

            if (!findSuitableItemStack(context, infos)) return;

            if(!handleCoilReplacement(context, pos)) return;

            if (placeBlock(context, pos)) {
                extractInventory(context);
            }
        }
    }

    private BuildContext initializeBuildContext(Player player, MultiblockState worldState,
                                                MEAdvancedTerminalBehavior.AutoBuildSetting settings) {
        Level world = player.level();
        IMultiController controller = worldState.getController();

        BuildContext context = new BuildContext();
        context.world = world;
        context.player = player;
        context.worldState = worldState;
        context.settings = settings;
        context.controller = controller;
        context.centerPos = controller.self().getPos();
        context.facing = controller.self().getFrontFacing();
        context.upwardsFacing = controller.self().getUpwardsFacing();
        context.isFlipped = controller.self().isFlipped();
        context.minZ = -centerOffset[4];

        clearWorldState(worldState);
        context.blocks.put(context.centerPos, controller);

        return context;
    }

    private int[] calculateLayerRepeatCounts(MEAdvancedTerminalBehavior.AutoBuildSetting settings) {
        int[] repeat = new int[this.fingerLength];
        for (int h = 0; h < this.fingerLength; h++) {
            var minH = aisleRepetitions[h][0];
            var maxH = aisleRepetitions[h][1];
            repeat[h] = (minH != maxH) ?
                    Math.max(minH, Math.min(maxH, settings.getRepeatCount())) :
                    minH;
        }
        return repeat;
    }

//    private void executeLayeredBuilding(BuildContext context, int[] repeatCounts) {
//        for (int c = 0, z = context.minZ++, r; c < this.fingerLength; c++) {
//            for (r = 0; r < repeatCounts[c]; r++) {
//                context.worldState.getLayerCount().clear();
//                buildSingleLayer(context, c, z);
//                z++;
//            }
//        }
//    }

//    private void buildSingleLayer(BuildContext context, int layerIndex, int z) {
//        for (int b = 0, y = -centerOffset[1]; b < this.thumbLength; b++, y++) {
//            for (int a = 0, x = -centerOffset[0]; a < this.palmLength; a++, x++) {
//                processSingleBlock(context, layerIndex, b, a, x, y, z);
//            }
//        }
//    }

//    private void processSingleBlock(BuildContext context, int layerIndex, int yIndex, int xIndex,
//                                    int x, int y, int z) {
//        TraceabilityPredicate predicate = this.blockMatches[layerIndex][yIndex][xIndex];
//        BlockPos pos = calculateBlockPosition(context, x, y, z);
//
//        updateWorldState(context.worldState, pos, predicate);
//
//        // 处理已有方块的情况
//        if (handleExistingBlock(context, pos, predicate)) {
//            return;
//        }
//        //FluidUtil.getFluidContained()
//        // 获取需要放置的方块信息
//        BlockInfo[] infos = determineRequiredBlockInfo(context, predicate);
//
//        if (infos == null) return;
//
//        // 从玩家物品栏中查找合适的方块
//        if (!findSuitableItemStack(context, infos)) return;
//
//        // 处理线圈替换逻辑
//        handleCoilReplacement(context, pos);
//
//        // 实际放置方块
//        if(placeBlock(context, pos))
//            extractInventory(context);
//    }

    private BlockPos calculateBlockPosition(BuildContext context, int x, int y, int z) {
        return setActualRelativeOffset(x, y, z, context.facing, context.upwardsFacing, context.isFlipped)
                .offset(context.centerPos.getX(), context.centerPos.getY(), context.centerPos.getZ());
    }

    private boolean handleExistingBlock(BuildContext context, BlockPos pos, TraceabilityPredicate predicate) {
        context.replacingCoil = false;
        context.inFluid = false;
        if (!context.world.isEmptyBlock(pos)) {
            BlockState existingState = context.world.getBlockState(pos);

            // 处理线圈替换
            if (existingState.getBlock() instanceof CoilBlock && context.settings.isReplaceCoilMode()) {
                context.coilItemStack = (existingState.getBlock()).asItem().getDefaultInstance();
                context.replacingCoil = true;
                return false; // 继续处理替换逻辑
            }
            if(existingState.liquid()){
                context.inFluid = true;
                if(context.settings.getPlaceInFluid()==1) return false;
            }

            // 其他情况直接记录现有方块并跳过
            context.blocks.put(pos, existingState);
            for (SimplePredicate limit : predicate.limited) {
                limit.testLimited(context.worldState);
            }
            return true;
        }
        return false;
    }

    private BlockInfo[] determineRequiredBlockInfo(BuildContext context, TraceabilityPredicate predicate) {
        // 有限谓词优先
        BlockInfo[] infos = checkLimitedPredicates(context, predicate);
        if (infos != null) return infos;

        // 无限制谓词
        return checkCommonPredicates(context, predicate);
    }

    private BlockInfo[] checkLimitedPredicates(BuildContext context, TraceabilityPredicate predicate) {
        Map<SimplePredicate, Integer> cacheLayer = context.worldState.getLayerCount();
        Map<SimplePredicate, Integer> cacheGlobal = context.worldState.getGlobalCount();

        // 检查层级限制
        for (SimplePredicate limit : predicate.limited) {
            if (limit.minLayerCount > 0 && context.settings.isPlaceHatch(limit.candidates.get())) {
                if (!cacheLayer.containsKey(limit)) {
                    cacheLayer.put(limit, 1);
                    return limit.candidates.get();
                } else if (cacheLayer.get(limit) < limit.minLayerCount &&
                        (limit.maxLayerCount == -1 || cacheLayer.get(limit) < limit.maxLayerCount)) {
                    cacheLayer.put(limit, cacheLayer.get(limit) + 1);
                    return limit.candidates.get();
                }
            }
        }

        // 检查全局限制
        for (SimplePredicate limit : predicate.limited) {
            if (limit.minCount > 0 && context.settings.isPlaceHatch(limit.candidates.get())) {
                if (!cacheGlobal.containsKey(limit)) {
                    cacheGlobal.put(limit, 1);
                    return limit.candidates.get();
                } else if (cacheGlobal.get(limit) < limit.minCount &&
                        (limit.maxCount == -1 || cacheGlobal.get(limit) < limit.maxCount)) {
                    cacheGlobal.put(limit, cacheGlobal.get(limit) + 1);
                    return limit.candidates.get();
                }
            }
        }

        return null;
    }

    private BlockInfo[] checkCommonPredicates(BuildContext context, TraceabilityPredicate predicate) {
        List<BlockInfo> infos = new ArrayList<>();
        Map<SimplePredicate, Integer> cacheLayer = context.worldState.getLayerCount();
        Map<SimplePredicate, Integer> cacheGlobal = context.worldState.getGlobalCount();

        // 无限制谓词处理
        for (SimplePredicate limit : predicate.limited) {
            if (!context.settings.isPlaceHatch(limit.candidates.get())) continue;

            if (limit.maxLayerCount != -1 &&
                    cacheLayer.getOrDefault(limit, Integer.MAX_VALUE) == limit.maxLayerCount) continue;

            if (limit.maxCount != -1 &&
                    cacheGlobal.getOrDefault(limit, Integer.MAX_VALUE) == limit.maxCount) continue;

            if (cacheLayer.containsKey(limit)) {
                cacheLayer.put(limit, cacheLayer.get(limit) + 1);
            } else {
                cacheLayer.put(limit, 1);
            }

            if (cacheGlobal.containsKey(limit)) {
                cacheGlobal.put(limit, cacheGlobal.get(limit) + 1);
            } else {
                cacheGlobal.put(limit, 1);
            }

            if (limit.candidates != null) {
                infos.addAll(Arrays.asList(limit.candidates.get()));
            }
        }

        // 通用谓词
        for (SimplePredicate common : predicate.common) {
            if (common.candidates != null && predicate.common.size() > 1 &&
                    !context.settings.isPlaceHatch(common.candidates.get())) {
                continue;
            }
            if (common.candidates != null) {
                infos.addAll(Arrays.asList(common.candidates.get()));
            }
        }

        return infos.isEmpty() ? null : infos.toArray(new BlockInfo[0]);
    }

    private boolean findSuitableItemStack(BuildContext context, BlockInfo[] infos) {
        List<OrientedItem> candidates = context.settings.apply(infos);

        // 跳过相同线圈的替换
        if (context.settings.isReplaceCoilMode() && context.coilItemStack != null) {
            for (OrientedItem candidate : candidates) {
                if (ItemStack.isSameItem(candidate.itemStack, context.coilItemStack)) {
                    return false;
                }
            }
        }
        return foundItem(context, candidates);

    }

    private boolean handleCoilReplacement(BuildContext context, BlockPos pos) {
        if(context.player.isCreative() || !context.replacingCoil) return true;
        if (!context.settings.isReplaceCoilMode() || context.coilItemStack == null) return true;

        // 检查是否能存放旧线圈
        Pair<IItemHandler, Integer> holderResult = foundHolderSlot(context.player, context.coilItemStack);
        if (holderResult.getFirst() != null && holderResult.getSecond() < 0) {
            return false;
        }

        // 替换线圈
        context.world.removeBlock(pos, true);
        if (holderResult.getFirst() != null) {
            holderResult.getFirst().insertItem(holderResult.getSecond(), context.coilItemStack, false);
        }
        return true;
    }

    private boolean placeBlock(BuildContext context, BlockPos pos) {
        if(context.fluid)
        {

            return !context.inFluid && context.world.setBlock(pos, context.fluidState.createLegacyBlock(), UPDATE_CLIENTS);
        }
        else
        {
            BlockItem itemBlock = (BlockItem) context.foundItemStack.getItem();
            Direction frontFacing = context.blockFacing != null ? context.blockFacing : context.controller.self().getFrontFacing();

            BlockPlaceContext placeContext = new BlockPlaceContext(
                    context.world, context.player, InteractionHand.MAIN_HAND,
                    context.foundItemStack.copy(), BlockHitResult.miss(context.player.getEyePosition(0), frontFacing, pos)
            );

            InteractionResult result = itemBlock.place(placeContext);
            return result != InteractionResult.FAIL;
        }
    }


    private void extractInventory(BuildContext context)
    {
        if (context.meStorage != null)
        {
            if(context.fluid)
            {
                context.meStorage.extract(AEFluidKey.of(context.fluidState.getType()), 1000, Actionable.MODULATE, IActionSource.ofMachine(context.settings.getAccessPoint()));
            }
            else {
                context.meStorage.extract(AEItemKey.of(context.foundItemStack), 1, Actionable.MODULATE, IActionSource.ofMachine(context.settings.getAccessPoint()));
            }
        }
        else {
            if(context.fluid){
                if(//context.fluidState.getType() == WATER &&
                    context.foundFluidHandler != null)
                {
                    if(context.foundFluidHandler instanceof FluidBucketWrapper bucketWrapper)
                    {
                        ItemStack bucket = bucketWrapper.getContainer().getItem().getCraftingRemainingItem().getDefaultInstance();
                        context.foundItemHandler.extractItem(context.foundItemSlot, 1, false);
                        context.foundItemHandler.insertItem(context.foundItemSlot, bucket, false);
                    }
                    else context.foundFluidHandler.drain(1000, IFluidHandler.FluidAction.EXECUTE);
                }
            }
            else
            {
                if(context.foundItemHandler != null)
                {
                    context.foundItemHandler.extractItem(context.foundItemSlot, 1, false);
                }
            }
        }
    }



    // 用于保存构建过程中的上下文数据
    public static class BuildContext {
        Level world;
        Player player;
        MultiblockState worldState;
        MEAdvancedTerminalBehavior.AutoBuildSetting settings;
        IMultiController controller;
        BlockPos centerPos;
        Direction facing;
        Direction upwardsFacing;
        boolean isFlipped;
        int minZ;

        boolean replacingCoil;
        ItemStack coilItemStack;

        ItemStack foundItemStack;
        IItemHandler foundItemHandler;
        Integer foundItemSlot;
        Direction blockFacing;

        boolean fluid = false;
        FluidState fluidState;
        IFluidHandler foundFluidHandler;

        MEStorage meStorage;
        boolean inFluid;

        Map<BlockPos, Object> blocks = new HashMap<>();
        Set<BlockPos> placeBlockPos = new HashSet<>();


    }

    private boolean foundItem(BuildContext context, List<OrientedItem> candidates) {
        Player player = context.player;
        context.foundItemSlot = -1;
        context.foundItemHandler = null;
        context.foundItemStack = null;
        context.blockFacing = null;

        context.fluid = false;
        context.fluidState = null;

        context.meStorage = null;

        if (!player.isCreative()) {
            if(context.settings.getUseAEStorage() == 1)
            {
                if(searchAEStorage(context, candidates))
                    return true;
            }
            return getMatchStackWithHandler(context,candidates, player.getCapability(ForgeCapabilities.ITEM_HANDLER), player);
        } else {
            for (var candidate : candidates) {
                if(candidate.fluidState != null && context.settings.getPlaceFluid() == 1)
                {
                    context.fluid = true;
                    context.fluidState = candidate.fluidState;
                    return true;
                }
                else {
                    context.foundItemStack = candidate.itemStack.copy();

                    if (!context.foundItemStack.isEmpty() && context.foundItemStack.getItem() instanceof BlockItem) {
                        context.blockFacing = candidate.facing;
                        return true;
                    }
                    //context.foundItemStack = null;
                }
            }
            return false;
        }

    }

    private boolean searchAEStorage(BuildContext context, List<OrientedItem> candidates)
    {
        if(context.settings.getAccessPoint() != null)
        {
            IGrid grid = context.settings.getAccessPoint().getGrid();
            if(grid == null || grid.getStorageService() == null) return false;
            MEStorage inventory = grid.getStorageService().getInventory();
            for (var candidate : candidates) {

                if(candidate.fluidState != null  && context.settings.getPlaceFluid() == 1)
                {
                    long amount = inventory.extract(AEFluidKey.of(candidate.fluidState.getType()), 1000, Actionable.SIMULATE, IActionSource.ofMachine(context.settings.getAccessPoint()));
                    if(amount >= 1000)
                    {
                        context.fluid = true;
                        context.fluidState = candidate.fluidState;
                        context.meStorage = inventory;
                        return true;
                    }
                }
                else {
                    long amount = inventory.extract(AEItemKey.of(candidate.itemStack), 1, Actionable.SIMULATE, IActionSource.ofMachine(context.settings.getAccessPoint()));
                    if(amount >= 1)
                    {
                        context.foundItemStack = candidate.itemStack.copy();
                        context.blockFacing = candidate.facing;
                        context.meStorage = inventory;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Pair<IItemHandler, Integer> foundHolderSlot(Player player, ItemStack coilItemStack) {
        IItemHandler handler;
        int foundSlot = -1;
        handler = player.getCapability(ForgeCapabilities.ITEM_HANDLER).orElse(null);
        for (int i = 0; i < handler.getSlots(); i++) {
            @NotNull
            ItemStack stack = handler.getStackInSlot(i);
            if (stack.isEmpty()) {
                if (foundSlot < 0) {
                    foundSlot = i;
                }
            } else if (ItemStack.isSameItemSameTags(coilItemStack, stack) && (stack.getCount() + 1) <= stack.getMaxStackSize()) {
                foundSlot = i;
            }
        }

        return new Pair<>(handler, foundSlot);
    }

    private void clearWorldState(MultiblockState worldState) {
        try {
            Class<?> clazz = Class.forName("com.gregtechceu.gtceu.api.pattern.MultiblockState");
            // Object obj = clazz.newInstance();
            Method method = clazz.getDeclaredMethod("clean");
            method.setAccessible(true);
            method.invoke(worldState);
        } catch (Exception ignored) {}
    }

    private void updateWorldState(MultiblockState worldState, BlockPos posIn, TraceabilityPredicate predicate) {
        try {
            Class<?> clazz = Class.forName("com.gregtechceu.gtceu.api.pattern.MultiblockState");
            Method method = clazz.getDeclaredMethod("update", BlockPos.class, TraceabilityPredicate.class);
            method.setAccessible(true);
            method.invoke(worldState, posIn, predicate);
        } catch (Exception ignored) {}
    }

    private BlockPos setActualRelativeOffset(int x, int y, int z, Direction facing, Direction upwardsFacing,
                                             boolean isFlipped) {
        int[] c0 = new int[] { x, y, z }, c1 = new int[3];
        if (facing == Direction.UP || facing == Direction.DOWN) {
            Direction of = facing == Direction.DOWN ? upwardsFacing : upwardsFacing.getOpposite();
            for (int i = 0; i < 3; i++) {
                switch (structureDir[i].getActualFacing(of)) {
                    case UP -> c1[1] = c0[i];
                    case DOWN -> c1[1] = -c0[i];
                    case WEST -> c1[0] = -c0[i];
                    case EAST -> c1[0] = c0[i];
                    case NORTH -> c1[2] = -c0[i];
                    case SOUTH -> c1[2] = c0[i];
                }
            }
            int xOffset = upwardsFacing.getStepX();
            int zOffset = upwardsFacing.getStepZ();
            int tmp;
            if (xOffset == 0) {
                tmp = c1[2];
                c1[2] = zOffset > 0 ? c1[1] : -c1[1];
                c1[1] = zOffset > 0 ? -tmp : tmp;
            } else {
                tmp = c1[0];
                c1[0] = xOffset > 0 ? c1[1] : -c1[1];
                c1[1] = xOffset > 0 ? -tmp : tmp;
            }
            if (isFlipped) {
                if (upwardsFacing == Direction.NORTH || upwardsFacing == Direction.SOUTH) {
                    c1[0] = -c1[0]; // flip X-axis
                } else {
                    c1[2] = -c1[2]; // flip Z-axis
                }
            }
        } else {
            for (int i = 0; i < 3; i++) {
                switch (structureDir[i].getActualFacing(facing)) {
                    case UP -> c1[1] = c0[i];
                    case DOWN -> c1[1] = -c0[i];
                    case WEST -> c1[0] = -c0[i];
                    case EAST -> c1[0] = c0[i];
                    case NORTH -> c1[2] = -c0[i];
                    case SOUTH -> c1[2] = c0[i];
                }
            }
            if (upwardsFacing == Direction.WEST || upwardsFacing == Direction.EAST) {
                int xOffset = upwardsFacing == Direction.EAST ? facing.getClockWise().getStepX() :
                        facing.getClockWise().getOpposite().getStepX();
                int zOffset = upwardsFacing == Direction.EAST ? facing.getClockWise().getStepZ() :
                        facing.getClockWise().getOpposite().getStepZ();
                int tmp;
                if (xOffset == 0) {
                    tmp = c1[2];
                    c1[2] = zOffset > 0 ? -c1[1] : c1[1];
                    c1[1] = zOffset > 0 ? tmp : -tmp;
                } else {
                    tmp = c1[0];
                    c1[0] = xOffset > 0 ? -c1[1] : c1[1];
                    c1[1] = xOffset > 0 ? tmp : -tmp;
                }
            } else if (upwardsFacing == Direction.SOUTH) {
                c1[1] = -c1[1];
                if (facing.getStepX() == 0) {
                    c1[0] = -c1[0];
                } else {
                    c1[2] = -c1[2];
                }
            }
            if (isFlipped) {
                if (upwardsFacing == Direction.NORTH || upwardsFacing == Direction.SOUTH) {
                    if (facing == Direction.NORTH || facing == Direction.SOUTH) {
                        c1[0] = -c1[0]; // flip X-axis
                    } else {
                        c1[2] = -c1[2]; // flip Z-axis
                    }
                } else {
                    c1[1] = -c1[1]; // flip Y-axis
                }
            }
        }
        return new BlockPos(c1[0], c1[1], c1[2]);
    }

    @Nullable
    private static boolean getMatchStackWithHandler(
            BuildContext context,
            List<OrientedItem> candidates,
            LazyOptional<IItemHandler> cap,
            Player player) {
        IItemHandler handler = cap.orElse(null);
        if (handler == null) {
            return false;
        }
        for (int i = 0; i < handler.getSlots(); i++) {
            @NotNull
            ItemStack stack = handler.getStackInSlot(i);
            if (stack.isEmpty()) continue;

            @NotNull
            LazyOptional<IItemHandler> stackCap = stack.getCapability(ForgeCapabilities.ITEM_HANDLER);
            if (stackCap.isPresent()) {
                if(getMatchStackWithHandler(context, candidates, stackCap, player)) return true;
            } else
            {
                for(var candidate: candidates)
                {
                    if(candidate.fluidState != null && context.settings.getPlaceFluid() == 1)
                    {
                        var fluidStack = FluidUtil.getFluidContained(stack);
                        if(fluidStack.isPresent() && fluidStack.get().getAmount() >= 1000 && fluidStack.get().getFluid() == candidate.fluidState.getType())
                        {
                            context.fluidState = candidate.fluidState;
                            context.fluid = true;
                            context.foundFluidHandler = FluidUtil.getFluidHandler(stack).resolve().get();

                            context.foundItemSlot = i;
                            context.foundItemHandler = handler;
                            return true;
                        }
                    }
                    else {
                        if(ItemStack.isSameItemSameTags(candidate.itemStack, stack) &&
                                !stack.isEmpty() && stack.getItem() instanceof BlockItem)
                        {
                            context.foundItemSlot = i;
                            context.foundItemHandler = handler;
                            context.foundItemStack = context.foundItemHandler.getStackInSlot(context.foundItemSlot).copy();
                            context.blockFacing = candidate.facing;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void resetFacing(BlockPos pos, BlockState blockState, Direction facing,
                             BiFunction<BlockPos, Direction, Boolean> checker, Consumer<BlockState> consumer) {
        if (blockState.hasProperty(BlockStateProperties.FACING)) {
            tryFacings(blockState, pos, checker, consumer, BlockStateProperties.FACING,
                    facing == null ? FACINGS : ArrayUtils.addAll(new Direction[] { facing }, FACINGS));
        } else if (blockState.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
            tryFacings(blockState, pos, checker, consumer, BlockStateProperties.HORIZONTAL_FACING,
                    facing == null || facing.getAxis() == Direction.Axis.Y ? FACINGS_H :
                            ArrayUtils.addAll(new Direction[] { facing }, FACINGS_H));
        }
    }

    private void tryFacings(BlockState blockState, BlockPos pos, BiFunction<BlockPos, Direction, Boolean> checker,
                            Consumer<BlockState> consumer, Property<Direction> property, Direction[] facings) {
        Direction found = null;
        for (Direction facing : facings) {
            if (checker.apply(pos, facing)) {
                found = facing;
                break;
            }
        }
        if (found == null) {
            found = Direction.NORTH;
        }
        consumer.accept(blockState.setValue(property, found));
    }
}
