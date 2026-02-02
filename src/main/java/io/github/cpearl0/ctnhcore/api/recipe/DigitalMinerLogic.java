package io.github.cpearl0.ctnhcore.api.recipe;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.capability.recipe.*;
import com.gregtechceu.gtceu.api.cover.filter.ItemFilter;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeHandlerList;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.misc.IgnoreEnergyRecipeHandler;
import com.gregtechceu.gtceu.api.misc.ItemRecipeHandler;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.transfer.item.NotifiableAccountedInvWrapper;
import com.gregtechceu.gtceu.common.data.GTMaterialItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.gregtechceu.gtceu.utils.GTTransferUtils;
import com.gregtechceu.gtceu.utils.GTUtil;

import net.minecraft.commands.arguments.blocks.BlockStateParser;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.ForgeChunkManager;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.IItemHandlerModifiable;

import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.github.cpearl0.ctnhcore.api.machine.feature.IDigitalMiner;
import io.github.cpearl0.ctnhcore.CTNHCore;

import java.util.*;

public class DigitalMinerLogic extends RecipeLogic implements IRecipeCapabilityHolder {

    public static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(DigitalMinerLogic.class,
            RecipeLogic.MANAGED_FIELD_HOLDER);
    private static final short MAX_SPEED = Short.MAX_VALUE;
    private static final byte POWER = 5;
    private static final byte TICK_TOLERANCE = 20;
    private static final double DIVIDEND = MAX_SPEED * Math.pow(TICK_TOLERANCE, POWER);
    protected final IDigitalMiner miner;
    @Nullable
    private NotifiableAccountedInvWrapper cachedItemTransfer = null;
    @Getter
    private int silk;
    @Getter
    private final int speed;
    @Getter
    private int maximumRadius;
    @Getter
    public ItemStack pickaxeTool;
    private final LinkedList<BlockPos> blocksToMine = new LinkedList<>();
    @Getter
    @Persisted
    protected int x = Integer.MAX_VALUE;
    @Getter
    @Persisted
    protected int y = Integer.MAX_VALUE;
    @Getter
    @Persisted
    protected int z = Integer.MAX_VALUE;
    @Getter
    @Persisted
    protected int startX = Integer.MAX_VALUE;
    @Getter
    @Persisted
    protected int startZ = Integer.MAX_VALUE;
    @Getter
    @Persisted
    protected int startY = Integer.MAX_VALUE;
    @Getter
    @Persisted
    protected int mineX = Integer.MAX_VALUE;
    @Getter
    @Persisted
    protected int mineZ = Integer.MAX_VALUE;
    @Getter
    @Persisted
    protected int mineY = Integer.MAX_VALUE;
    @Getter
    private int minBuildHeight = Integer.MAX_VALUE;
    @Getter
    private int minHeight;
    @Getter
    private int maxHeight;
    @Getter
    @Setter
    @Persisted
    private int currentRadius;
    @Getter
    @Persisted
    private boolean isDone;
    @Getter
    private boolean isInventoryFull;
    @Getter
    protected final Map<IO, List<RecipeHandlerList>> capabilitiesProxy;
    @Getter
    protected final Map<IO, Map<RecipeCapability<?>, List<IRecipeHandler<?>>>> capabilitiesFlat;
    private final ItemRecipeHandler inputItemHandler, outputItemHandler;
    private final IgnoreEnergyRecipeHandler inputEnergyHandler;
    @Getter
    private int oreAmount;
    @Getter
    private ItemFilter itemFilter;

    private static final TagKey<Item> RAW_MATERIALS_TAG = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(),
        ResourceLocation.fromNamespaceAndPath("forge", "raw_materials"));

    private final Map<net.minecraft.world.level.block.Block, Boolean> rawOreFilterCache = new IdentityHashMap<>();

    private boolean isChunkForced;
    private BlockPos forcedChunkOwner;
    private int forcedChunkX = Integer.MIN_VALUE;
    private int forcedChunkZ = Integer.MIN_VALUE;

    public DigitalMinerLogic(@NotNull IRecipeLogicMachine machine, int maximumRadius, int minHeight, int maxHeight,
                             int silk, ItemFilter itemFilter, int speed) {
        super(machine);
        this.miner = (IDigitalMiner) machine;
        this.silk = silk;
        this.speed = speed;
        this.currentRadius = maximumRadius;
        this.maximumRadius = maximumRadius;
        this.isDone = false;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.pickaxeTool = GTMaterialItems.TOOL_ITEMS.get(GTMaterials.Neutronium, GTToolType.PICKAXE).get().get();
        this.pickaxeTool.enchant(Enchantments.BLOCK_FORTUNE, 1);
        this.itemFilter = itemFilter;
        this.capabilitiesProxy = new EnumMap<>(IO.class);
        this.capabilitiesFlat = new EnumMap<>(IO.class);
        this.inputItemHandler = new ItemRecipeHandler(IO.IN,
                machine.getRecipeType().getMaxInputs(ItemRecipeCapability.CAP));
        this.outputItemHandler = new ItemRecipeHandler(IO.OUT,
                machine.getRecipeType().getMaxOutputs(ItemRecipeCapability.CAP));
        this.inputEnergyHandler = new IgnoreEnergyRecipeHandler();
        addHandlerList(RecipeHandlerList.of(IO.IN, inputItemHandler, inputEnergyHandler));
        addHandlerList(RecipeHandlerList.of(IO.OUT, outputItemHandler));
    }

    @Override
    public void resetRecipeLogic() {
        super.resetRecipeLogic();
        resetArea(false);
        this.cachedItemTransfer = null;
        this.setWorkingEnabled(false);
    }

    public void resetRecipeLogic(int maximumRadius, int minHeight, int maxHeight, int silk, ItemFilter itemFilter) {
        this.silk = silk;
        this.currentRadius = maximumRadius;
        this.maximumRadius = maximumRadius;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.itemFilter = itemFilter;
        this.rawOreFilterCache.clear();
        this.blocksToMine.clear();
        this.oreAmount = 0;
        this.resetRecipeLogic();
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Override
    public void inValid() {
        super.inValid();
        this.cachedItemTransfer = null;
        ensureChunkUnforced();
    }

    public void ensureChunkUnforced() {
        if (getMachine().getLevel() instanceof ServerLevel serverLevel) {
            setChunkForced(serverLevel, false);
        }
    }

    private static BlockState findMiningReplacementBlock(Level level) {
        try {
            return BlockStateParser.parseForBlock(level.holderLookup(Registries.BLOCK),
                    ConfigHolder.INSTANCE.machines.replaceMinedBlocksWith, false).blockState();
        } catch (CommandSyntaxException ignored) {
            GTCEu.LOGGER.error("failed to parse replaceMinedBlocksWith, invalid BlockState: {}",
                    ConfigHolder.INSTANCE.machines.replaceMinedBlocksWith);
            return Blocks.COBBLESTONE.defaultBlockState();
        }
    }

    /**
     * Performs the actual mining in world
     * Call this method every tick in update
     */
    public void serverTick() {
        if (!(getMachine().getLevel() instanceof ServerLevel serverLevel)) return;

        setChunkForced(serverLevel, isWorkingEnabled() && !isDone);

        if (!isSuspend() && checkCanMine()) {
            // if the inventory is not full, drain energy etc. from the miner
            // the storages have already been checked earlier
            if (!isInventoryFull()) {
                // always drain storages when working, even if blocksToMine ends up being empty
                miner.drainInput(false);
                // since energy is being consumed the miner is now active
                setStatus(Status.WORKING);
            } else {
                // the miner cannot drain, therefore it is inactive
                if (this.isWorking()) {
                    setWaiting(Component.translatable("gtceu.recipe_logic.insufficient_out").append(": ")
                            .append(ItemRecipeCapability.CAP.getName()));
                }
            }

            // check if the miner needs new blocks to mine and get them if needed
            checkBlocksToMine();

            // if there are blocks to mine and the correct amount of time has passed, do the mining
            if (getMachine().getOffsetTimer() % this.speed == 0 && !blocksToMine.isEmpty()) {
                NonNullList<ItemStack> blockDrops = NonNullList.create();
                BlockState blockState = serverLevel.getBlockState(blocksToMine.getFirst());

                // check to make sure the ore is still there,
                while (!blockState.is(Tags.Blocks.ORES)) {
                    blocksToMine.removeFirst();
                    this.oreAmount = blocksToMine.size();
                    if (blocksToMine.isEmpty()) break;
                    blockState = serverLevel.getBlockState(blocksToMine.getFirst());
                }
                // When we are here we have an ore to mine! I'm glad we aren't threaded
                if (!blocksToMine.isEmpty() & blockState.is(Tags.Blocks.ORES)) {
                    LootParams.Builder builder = new LootParams.Builder(serverLevel)
                            .withParameter(LootContextParams.BLOCK_STATE, blockState)
                            .withParameter(LootContextParams.ORIGIN, Vec3.atLowerCornerOf(blocksToMine.getFirst()))
                            .withParameter(LootContextParams.TOOL, getPickaxeTool());

                    // get the block's drops.
                    if (isSilkTouchMode()) {
                        getSilkTouchDrops(blockDrops, blockState, builder);
                    } else {
                        getRegularBlockDrops(blockDrops, blockState, builder);
                    }
                    // handle recipe type
                    if (hasPostProcessing()) {
                        doPostProcessing(blockDrops, blockState, builder);
                    }
                    // try to insert them
                    mineAndInsertItems(blockDrops, serverLevel);
                    this.oreAmount = blocksToMine.size();
                }
            }

            if (blocksToMine.isEmpty()) {
                // there were no blocks to mine, so the current position is the previous position
                x = mineX;
                y = mineY;
                z = mineZ;

                // attempt to get more blocks to mine, if there are none, the miner is done mining
                blocksToMine.addAll(getBlocksToMine());
                if (blocksToMine.isEmpty()) {
                    this.isDone = true;
                    this.setStatus(Status.IDLE);
                }
                this.oreAmount = blocksToMine.size();
            }
        } else {
            // machine isn't working enabled
            this.setStatus(Status.IDLE);
            if (subscription != null) {
                subscription.unsubscribe();
                subscription = null;
            }
        }
    }

    private void setChunkForced(@NotNull ServerLevel serverLevel, boolean shouldForce) {
        BlockPos ownerPos = getMachine().getPos();
        ChunkPos chunkPos = new ChunkPos(ownerPos);

        if (shouldForce) {
            if (!isChunkForced || forcedChunkX != chunkPos.x || forcedChunkZ != chunkPos.z || forcedChunkOwner == null || !forcedChunkOwner.equals(ownerPos)) {
                if (isChunkForced && forcedChunkOwner != null) {
                    ForgeChunkManager.forceChunk(serverLevel, CTNHCore.MODID, forcedChunkOwner, forcedChunkX, forcedChunkZ, false, true);
                }
                ForgeChunkManager.forceChunk(serverLevel, CTNHCore.MODID, ownerPos, chunkPos.x, chunkPos.z, true, true);
                isChunkForced = true;
                forcedChunkOwner = ownerPos;
                forcedChunkX = chunkPos.x;
                forcedChunkZ = chunkPos.z;
            }
        } else if (isChunkForced) {
            if (forcedChunkOwner != null) {
                ForgeChunkManager.forceChunk(serverLevel, CTNHCore.MODID, forcedChunkOwner, forcedChunkX, forcedChunkZ, false, true);
            }
            isChunkForced = false;
            forcedChunkOwner = null;
            forcedChunkX = Integer.MIN_VALUE;
            forcedChunkZ = Integer.MIN_VALUE;
        }
    }

    /**
     * @return true if the miner is able to mine, else false
     */
    protected boolean checkCanMine() {
        // if the miner is finished, the target coordinates are invalid, or it cannot drain storages, stop
        // if the miner is not finished and has invalid coordinates, get new and valid starting coordinates
        if (!isDone && checkCoordinatesInvalid()) {
            initPos(getMiningPos(), currentRadius);
        }
        return !isDone && miner.drainInput(true);
    }

    /**
     * Called after each block is mined, used to perform additional actions afterwards
     */
    protected void onMineOperation() {}

    /**
     * Should we apply additional processing according to the recipe type.
     */
    protected boolean hasPostProcessing() {
        return false;
    }

    protected boolean isSilkTouchMode() {
        return silk == 1;
    }

    /**
     * called to handle mining regular ores and blocks
     *
     * @param blockDrops the List of items to fill after the operation
     * @param blockState the {@link BlockState} of the block being mined
     */
    protected void getRegularBlockDrops(NonNullList<ItemStack> blockDrops, BlockState blockState,
                                        LootParams.Builder builder) {
        blockDrops.addAll(blockState.getDrops(builder));
    }

    protected int getVoltageTier() {
        return 0;
    }

    protected boolean doPostProcessing(NonNullList<ItemStack> blockDrops, BlockState blockState,
                                       LootParams.Builder builder) {
        ItemStack oreDrop = blockDrops.get(0);

        // create dummy recipe handler
        inputItemHandler.storage.setStackInSlot(0, oreDrop);
        inputItemHandler.storage.onContentsChanged(0);
        for (int i = 0; i < outputItemHandler.storage.getSlots(); ++i) {
            outputItemHandler.storage.setStackInSlot(i, ItemStack.EMPTY);
        }
        outputItemHandler.storage.onContentsChanged(0);

        var matches = machine.getRecipeType().searchRecipe(this, r -> RecipeHelper.matchContents(this, r).isSuccess());

        while (matches != null && matches.hasNext()) {
            GTRecipe match = matches.next();
            if (match == null) continue;

            var eut = match.getInputEUt().getTotalEU();
            if (GTUtil.getTierByVoltage(eut) <= getVoltageTier()) {
                if (RecipeHelper.handleRecipeIO(this, match, IO.OUT, this.chanceCaches).isSuccess()) {
                    blockDrops.clear();
                    var result = new ArrayList<ItemStack>();
                    for (int i = 0; i < outputItemHandler.storage.getSlots(); ++i) {
                        var stack = outputItemHandler.storage.getStackInSlot(i);
                        if (stack.isEmpty()) continue;
                        result.add(stack);
                    }
                    dropPostProcessing(blockDrops, result, blockState, builder);
                    return true;
                }
            }
        }
        return false;
    }

    protected void dropPostProcessing(NonNullList<ItemStack> blockDrops, List<ItemStack> outputs, BlockState blockState,
                                      LootParams.Builder builder) {
        blockDrops.addAll(outputs);
    }

    /**
     * called to handle mining regular ores and blocks with silk touch
     *
     * @param blockDrops the List of items to fill after the operation
     * @param blockState the {@link BlockState} of the block being mined
     */
    protected void getSilkTouchDrops(NonNullList<ItemStack> blockDrops, BlockState blockState,
                                     LootParams.Builder builder) {
        blockDrops.add(new ItemStack(blockState.getBlock()));
    }

    protected NotifiableAccountedInvWrapper getCachedItemTransfer() {
        if (cachedItemTransfer == null) {
            cachedItemTransfer = new NotifiableAccountedInvWrapper(machine.getCapabilitiesFlat(IO.OUT, ItemRecipeCapability.CAP).stream().map(IItemHandlerModifiable.class::cast).toArray(IItemHandlerModifiable[]::new));
        }
        return cachedItemTransfer;
    }

    /**
     * called in order to insert the mined items into the inventory and actually remove the block in world
     * marks the inventory as full if the items cannot fit, and not full if it previously was full and items could fit
     *
     * @param blockDrops the List of items to insert
     * @param world      the {@link ServerLevel} the miner is in
     */
    private void mineAndInsertItems(NonNullList<ItemStack> blockDrops, ServerLevel world) {
        // If the block's drops can fit in the inventory, move the previously mined position to the block
        // replace the ore block with cobblestone instead of breaking it to prevent mob spawning
        // remove the ore block's position from the mining queue
        var transfer = getCachedItemTransfer();
        if (transfer != null) {
            if (GTTransferUtils.addItemsToItemHandler(transfer, true, blockDrops)) {
                GTTransferUtils.addItemsToItemHandler(transfer, false, blockDrops);
                world.setBlock(blocksToMine.getFirst(), findMiningReplacementBlock(world), 3);
                mineX = blocksToMine.getFirst().getX();
                mineZ = blocksToMine.getFirst().getZ();
                mineY = blocksToMine.getFirst().getY();
                blocksToMine.removeFirst();
                onMineOperation();

                // if the inventory was previously considered full, mark it as not since an item was able to fit
                isInventoryFull = false;
            } else {
                // the ore block was not able to fit, so the inventory is considered full
                isInventoryFull = true;
            }
        }
    }

    /**
     * This method designates the starting position for mining blocks
     *
     * @param pos           the {@link BlockPos} of the miner itself
     * @param currentRadius the currently set mining radius
     */
    public void initPos(@NotNull BlockPos pos, int currentRadius) {
        x = pos.getX() - currentRadius;
        z = pos.getZ() - currentRadius;
        y = maxHeight;
        startX = pos.getX() - currentRadius;
        startZ = pos.getZ() - currentRadius;
        startY = maxHeight;
        mineX = pos.getX() - currentRadius;
        mineZ = pos.getZ() - currentRadius;
        mineY = maxHeight;
    }

    /**
     * Checks if the current coordinates are invalid
     *
     * @return {@code true} if the coordinates are invalid, else false
     */
    private boolean checkCoordinatesInvalid() {
        return x == Integer.MAX_VALUE && y == Integer.MAX_VALUE && z == Integer.MAX_VALUE;
    }

    /**
     * Checks whether there are any more blocks to mine, if there are currently none queued
     */
    public void checkBlocksToMine() {
        if (blocksToMine.isEmpty())
            blocksToMine.addAll(getBlocksToMine());
    }

    /**
     * Recalculates the mining area, refills the block list and restarts the miner, if it was done
     */
    public void resetArea(boolean checkToMine) {
        initPos(getMiningPos(), currentRadius);
        if (this.isDone) this.setWorkingEnabled(false);
        this.isDone = false;
        if (checkToMine) {
            blocksToMine.clear();
            checkBlocksToMine();
        }
    }

    /**
     * Gets the blocks to mine
     *
     * @return a {@link LinkedList} of {@link BlockPos} for each ore to mine
     */
    private LinkedList<BlockPos> getBlocksToMine() {
        LinkedList<BlockPos> blocks = new LinkedList<>();

        Level level = getMachine().getLevel();
        ServerLevel serverLevel = level instanceof ServerLevel sl ? sl : null;

        // determine how many blocks to retrieve this time
        double quotient = getQuotient(getMeanTickTime(getMachine().getLevel()));
        // int calcAmount = quotient < 1 ? 1 : (int) (Math.min(quotient, Short.MAX_VALUE));
        int calcAmount = Short.MAX_VALUE;
        int calculated = 0;

        if (this.minBuildHeight == Integer.MAX_VALUE)
            this.minBuildHeight = this.getMachine().getLevel().getMinBuildHeight();

        // keep getting blocks until the target amount is reached
        while (calculated < calcAmount) {
            // moving down the y-axis
            if (y > this.minHeight) {
                // moving across the z-axis
                if (z <= startZ + currentRadius * 2) {
                    // check every block along the x-axis
                    if (x <= startX + currentRadius * 2) {
                        BlockPos blockPos = new BlockPos(x, y, z);
                        BlockState state = level.getBlockState(blockPos);
                        if (state.getBlock().defaultDestroyTime() >= 0 &&
                                level.getBlockEntity(blockPos) == null &&
                                state.is(Tags.Blocks.ORES)) {
                            if (itemFilter == null) {
                                blocks.addLast(blockPos);
                            } else if (matchesItemFilter(serverLevel, blockPos, state)) {
                                blocks.addLast(blockPos);
                            }
                        }
                        // move to the next x position
                        ++x;
                    } else {
                        // reset x and move to the next z layer
                        x = startX;
                        ++z;
                    }
                } else {
                    // reset z and move to the next y layer
                    z = startZ;
                    --y;
                }
            } else
                return blocks;

            // only count iterations where blocks were found
            if (!blocks.isEmpty())
                calculated = blocks.size();
        }
        return blocks;
    }

    private boolean matchesItemFilter(@Nullable ServerLevel serverLevel, @NotNull BlockPos pos, @NotNull BlockState state) {
        if (itemFilter == null) return true;
        if (itemFilter.test(state.getBlock().asItem().getDefaultInstance())) return true;
        if (isSilkTouchMode()) return false;
        if (serverLevel == null) return false;
        return matchesRawOreDrops(serverLevel, pos, state);
    }

    private boolean matchesRawOreDrops(@NotNull ServerLevel serverLevel, @NotNull BlockPos pos, @NotNull BlockState state) {
        var block = state.getBlock();
        Boolean cached = rawOreFilterCache.get(block);
        if (cached != null) return cached;

        NonNullList<ItemStack> predictedDrops = NonNullList.create();
        LootParams.Builder builder = new LootParams.Builder(serverLevel)
                .withParameter(LootContextParams.BLOCK_STATE, state)
                .withParameter(LootContextParams.ORIGIN, Vec3.atLowerCornerOf(pos))
                .withParameter(LootContextParams.TOOL, getPickaxeTool());

        getRegularBlockDrops(predictedDrops, state, builder);
        if (!predictedDrops.isEmpty() && hasPostProcessing()) {
            doPostProcessing(predictedDrops, state, builder);
        }

        boolean matches = false;
        for (ItemStack drop : predictedDrops) {
            if (!drop.isEmpty() && drop.is(RAW_MATERIALS_TAG) && itemFilter.test(drop)) {
                matches = true;
                break;
            }
        }

        rawOreFilterCache.put(block, matches);
        return matches;
    }

    /**
     * @param values to find the mean of
     * @return the mean value
     */
    private static long mean(@NotNull long[] values) {
        if (values.length == 0L)
            return 0L;

        long sum = 0L;
        for (long v : values)
            sum += v;
        return sum / values.length;
    }

    /**
     * @param world the {@link Level} to get the average tick time of
     * @return the mean tick time
     */
    private static double getMeanTickTime(@NotNull Level world) {
        return mean(Objects.requireNonNull(world.getServer()).tickTimes) * 1.0E-6D;
    }

    /**
     * gets the quotient for determining the amount of blocks to mine
     *
     * @param base is a value used for calculation, intended to be the mean tick time of the world the miner is in
     * @return the quotient
     */
    private static double getQuotient(double base) {
        return DIVIDEND / Math.pow(base, POWER);
    }

    /**
     * @return the position to start mining from
     */
    public BlockPos getMiningPos() {
        return getMachine().getPos();
    }
}
