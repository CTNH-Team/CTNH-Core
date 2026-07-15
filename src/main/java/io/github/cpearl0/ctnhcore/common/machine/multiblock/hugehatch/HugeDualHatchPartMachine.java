package io.github.cpearl0.ctnhcore.common.machine.multiblock.hugehatch;

import io.github.cpearl0.ctnhcore.registry.machines.CTNHMachines;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.fancy.ConfiguratorPanel;
import com.gregtechceu.gtceu.api.gui.widget.LargeStackSlotWidget;
import com.gregtechceu.gtceu.api.gui.widget.TankWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.fancyconfigurator.FancyTankConfigurator;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.gregtechceu.gtceu.utils.GTTransferUtils;

import com.lowdragmc.lowdraglib.gui.util.ClickData;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.jei.IngredientIO;
import com.lowdragmc.lowdraglib.syncdata.ISubscription;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidType;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.gregtechceu.gtceu.api.GTValues.*;

public class HugeDualHatchPartMachine extends HugeItemBusPartMachine {

    public static final int INITIAL_TANK_CAPACITY = 16 * FluidType.BUCKET_VOLUME;

    @Persisted
    public final NotifiableFluidTank tank;

    @Getter
    @Persisted
    protected final NotifiableFluidTank shareTank;

    @Nullable
    protected ISubscription tankSubs;

    private boolean hasFluidHandler;
    private boolean hasItemHandler;

    public HugeDualHatchPartMachine(IMachineBlockEntity holder, int tier, IO io, Object... args) {
        super(holder, tier, io, args);
        this.tank = createTank(INITIAL_TANK_CAPACITY, getTankSize(), args);
        this.shareTank = new NotifiableFluidTank(this, 9, 8 * FluidType.BUCKET_VOLUME, IO.IN, IO.NONE);
    }

    public int getTankSize() {
        return getTankSize(getTier());
    }

    public static int getTankSize(int tier) {
        return tier + 1;
    }

    public static int getTankCapacity(int initialCapacity, int tier) {
        return Integer.MAX_VALUE;
    }

    protected NotifiableFluidTank createTank(int initialCapacity, int slots, Object... args) {
        return new NotifiableFluidTank(this, slots, getTankCapacity(initialCapacity, getTier()), io) {

            public boolean canCapOutput() {
                return true;
            }
        };
    }

    @Override
    public void onLoad() {
        super.onLoad();
        tankSubs = tank.addChangedListener(this::updateInventorySubscription);
    }

    @Override
    public void onUnload() {
        super.onUnload();
        if (tankSubs != null) {
            tankSubs.unsubscribe();
            tankSubs = null;
        }
    }

    @Override
    protected void updateInventorySubscription() {
        boolean canOutput = io == IO.OUT && (!tank.isEmpty() || !getInventory().isEmpty());
        var level = getLevel();
        if (level != null) {
            this.hasItemHandler = GTTransferUtils.hasAdjacentItemHandler(level, getPos(), getFrontFacing());
            this.hasFluidHandler = GTTransferUtils.hasAdjacentFluidHandler(level, getPos(), getFrontFacing());
        } else {
            this.hasItemHandler = false;
            this.hasFluidHandler = false;
        }

        if (isWorkingEnabled() && (canOutput || io == IO.IN) && (hasItemHandler || hasFluidHandler)) {
            autoIOSubs = subscribeServerTick(autoIOSubs, this::autoIO);
        } else if (autoIOSubs != null) {
            autoIOSubs.unsubscribe();
            autoIOSubs = null;
        }
    }

    @Override
    protected void refundAll(ClickData clickData) {
        super.refundAll(clickData);
        this.tank.exportToNearby(getFrontFacing());
    }

    @Override
    protected void autoIO() {
        if (getOffsetTimer() % 5 == 0) {
            if (isWorkingEnabled()) {
                if (io == IO.OUT) {
                    if (hasItemHandler) {
                        getInventory().exportToNearby(getFrontFacing());
                    }
                    if (hasFluidHandler) {
                        tank.exportToNearby(getFrontFacing());
                    }
                } else if (io == IO.IN) {
                    if (hasItemHandler) {
                        getInventory().importFromNearby(getFrontFacing());
                    }
                    if (hasFluidHandler) {
                        tank.importFromNearby(getFrontFacing());
                    }
                }
            }
            updateInventorySubscription();
        }
    }

    @Override
    public boolean swapIO() {
        BlockPos blockPos = getHolder().pos();
        MachineDefinition newDefinition = null;

        if (io == IO.IN) {
            newDefinition = CTNHMachines.HUGE_DUAL_EXPORT_HATCH[this.getTier()];
        } else if (io == IO.OUT) {
            newDefinition = CTNHMachines.HUGE_DUAL_IMPORT_HATCH[this.getTier()];
        }
        if (newDefinition == null) return false;

        BlockState newBlockState = newDefinition.getBlock().defaultBlockState();

        getLevel().setBlockAndUpdate(blockPos, newBlockState);

        if (getLevel().getBlockEntity(blockPos) instanceof IMachineBlockEntity newHolder) {
            if (newHolder.getMetaMachine() instanceof HugeDualHatchPartMachine newMachine) {
                newMachine.setFrontFacing(this.getFrontFacing());
                newMachine.setUpwardsFacing(this.getUpwardsFacing());
                for (int i = 0; i < this.tank.getTanks(); i++) {
                    newMachine.tank.setFluidInTank(i, this.tank.getFluidInTank(i));
                }
            }
        }
        return true;
    }

    @Override
    public Widget createUIWidget() {
        int inventorySize = getInventorySize();
        inventorySize = Math.min(inventorySize, 25);

        int fluidTankCount = getTankSize();
        fluidTankCount = Math.min(fluidTankCount, 10);

        // 流体槽布局
        int fluidCols = Math.min(fluidTankCount, 5);
        int fluidRows = fluidTankCount <= 5 ? 1 : 2;

        // 计算物品槽布局
        int[] itemLayout = calculateOptimalLayout(inventorySize);
        int itemCols = Math.max(fluidCols, itemLayout[0]);
        int itemRows = (int) Math.ceil(inventorySize / (float) itemCols);

        // 计算总布局
        int totalCols = Math.max(itemCols, fluidCols); // 取最大列数
        int totalRows = itemRows + fluidRows; // 物品槽行数 + 流体槽行

        var group = new WidgetGroup(0, 0, 18 * totalCols + 16, 18 * totalRows + 16);
        var container = new WidgetGroup(4, 4, 18 * totalCols + 8, 18 * totalRows + 8);

        // 渲染物品槽
        int index = 0;
        for (int y = 0; y < itemRows; y++) {
            for (int x = 0; x < itemCols; x++) {
                if (index < inventorySize) {
                    container.addWidget(
                            new LargeStackSlotWidget(getInventory().storage, index, 4 + x * 18, 4 + y * 18, true,
                                    io.support(IO.IN))
                                    .setBackgroundTexture(GuiTextures.SLOT)
                                    .setIngredientIO(this.io == IO.IN ? IngredientIO.INPUT : IngredientIO.OUTPUT));
                    index++;
                }
            }
        }

        // 渲染流体槽
        int fluidIndex = 0;
        for (int y = 0; y < fluidRows; y++) {
            for (int x = 0; x < fluidCols; x++) {
                if (fluidIndex < fluidTankCount) {
                    container.addWidget(new TankWidget(
                            tank.getStorages()[fluidIndex],
                            4 + x * 18,
                            4 + itemRows * 18 + y * 18,
                            true,
                            io.support(IO.IN))
                            .setBackground(GuiTextures.FLUID_SLOT));
                    fluidIndex++;
                }
            }
        }

        container.setBackground(GuiTextures.BACKGROUND_INVERSE);
        group.addWidget(container);

        return group;
    }

    @Override
    public void attachConfigurators(ConfiguratorPanel left, ConfiguratorPanel right) {
        super.attachConfigurators(left, right);
        if (io != IO.IN) return;
        right.attachConfigurators(new FancyTankConfigurator(
                shareTank.getStorages(), Component.translatable("gui.gtceu.share_tank.title"))
                .setTooltips(List.of(
                        Component.translatable("gui.gtceu.share_inventory.desc.1"))));
    }
}
