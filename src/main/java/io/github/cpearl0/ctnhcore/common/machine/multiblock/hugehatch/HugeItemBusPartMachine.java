package io.github.cpearl0.ctnhcore.common.machine.multiblock.hugehatch;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.widget.SlotWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.transfer.item.CustomItemStackHandler;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.machine.multiblock.part.ItemBusPartMachine;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.jei.IngredientIO;
import io.github.cpearl0.ctnhcore.common.gui.HugeSlotWidget;
import io.github.cpearl0.ctnhcore.registry.CTNHMachines;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class HugeItemBusPartMachine extends ItemBusPartMachine {

    public HugeItemBusPartMachine(IMachineBlockEntity holder, int tier, IO io, Object... args) {
        super(holder, tier, io, args);
    }

    @Override
    protected @NotNull NotifiableItemStackHandler createInventory(Object... args) {
        return new NotifiableItemStackHandler(this, getInventorySize(), io, io, i -> new HugeItemStackHandler(i, getSlotMultiplier()));
    }

    int getSlotMultiplier(){
        return 1 << (4 + 2 * getTier());
    }

    @Override
    protected int getInventorySize() {
        return 1 + getTier();
    }

    @Override
    public Widget createUIWidget() {
        int inventorySize = getInventorySize();
        inventorySize = Math.min(inventorySize, 16); // 限制最大16个

        // 智能计算行列数
        int[] layout = calculateOptimalLayout(inventorySize);
        int rowSize = layout[0];
        int colSize = layout[1];

        var group = new WidgetGroup(0, 0, 18 * rowSize + 16, 18 * colSize + 16);
        var container = new WidgetGroup(4, 4, 18 * rowSize + 8, 18 * colSize + 8);

        int index = 0;
        for (int y = 0; y < colSize; y++) {
            for (int x = 0; x < rowSize; x++) {
                if (index < inventorySize) {
                    container.addWidget(
                            new HugeSlotWidget(getInventory().storage, index, 4 + x * 18, 4 + y * 18, true, io.support(IO.IN))
                                    .setBackgroundTexture(GuiTextures.SLOT)
                                    .setIngredientIO(this.io == IO.IN ? IngredientIO.INPUT : IngredientIO.OUTPUT)
                    );
                    index++;
                }
            }
        }

        container.setBackground(GuiTextures.BACKGROUND_INVERSE);
        group.addWidget(container);

        return group;
    }

    /**
     * 智能计算最优布局
     */
    private int[] calculateOptimalLayout(int slotCount) {
        if (slotCount <= 0) return new int[]{1, 1};

        // 优先选择接近正方形的布局
        int sqrt = (int) Math.sqrt(slotCount);
        int rows = sqrt;
        int cols = sqrt;

        // 调整布局使其更美观
        if (slotCount <= 4) {
            // 1-4个：单行或2x2
            cols = Math.min(slotCount, 2);
            rows = (int) Math.ceil(slotCount / 2.0);
        } else if (slotCount <= 9) {
            // 5-9个：3x3网格
            cols = 3;
            rows = (int) Math.ceil(slotCount / 3.0);
        } else {
            // 10-16个：4x4网格
            cols = 4;
            rows = (int) Math.ceil(slotCount / 4.0);
        }

        return new int[]{cols, rows};
    }

    @Override
    public boolean swapIO() {
        BlockPos blockPos = getHolder().pos();
        MachineDefinition newDefinition = null;
        if (io == IO.IN) {
            newDefinition = CTNHMachines.HUGE_ITEM_EXPORT_BUS[this.getTier()];
        } else if (io == IO.OUT) {
            newDefinition = CTNHMachines.HUGE_ITEM_IMPORT_BUS[this.getTier()];
        }

        if (newDefinition == null) return false;
        BlockState newBlockState = newDefinition.getBlock().defaultBlockState();

        getLevel().setBlockAndUpdate(blockPos, newBlockState);

        if (getLevel().getBlockEntity(blockPos) instanceof IMachineBlockEntity newHolder) {
            if (newHolder.getMetaMachine() instanceof ItemBusPartMachine newMachine) {
                newMachine.setFrontFacing(this.getFrontFacing());
                newMachine.setUpwardsFacing(this.getUpwardsFacing());
                newMachine.setPaintingColor(this.getPaintingColor());
            }
        }
        return true;
    }

    public static class HugeItemStackHandler extends CustomItemStackHandler{
        public final int multiplier;
        public int limit = 0;

        public HugeItemStackHandler(int i, int p) {
            super(i);
            multiplier = p;
        }

        @Override
        public int getSlotLimit(int slot) {
            return 64 * multiplier;
        }

        @Override
        public int getStackLimit(int slot, @NotNull ItemStack stack) {
            return Math.min(this.getSlotLimit(slot), stack.getMaxStackSize() * multiplier);
        }

        @Override
        public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
            return super.insertItem(slot, stack, simulate);
        }
    }
}
