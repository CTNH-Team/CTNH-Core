package io.github.cpearl0.ctnhcore.common.machine.multiblock.hugehatch;

import io.github.cpearl0.ctnhcore.common.gui.HugeSlotWidget;
import io.github.cpearl0.ctnhcore.common.gui.rightconfigurator.IAllowSameUIProvider;
import io.github.cpearl0.ctnhcore.registry.machines.CTNHMachines;
import io.github.cpearl0.ctnhcore.utils.HugeBusTransferHelper;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.fancy.ConfiguratorPanel;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.fancyconfigurator.ButtonConfigurator;
import com.gregtechceu.gtceu.api.machine.fancyconfigurator.FancyInvConfigurator;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.transfer.item.CustomItemStackHandler;
import com.gregtechceu.gtceu.common.machine.multiblock.part.ItemBusPartMachine;
import com.gregtechceu.gtceu.utils.GTTransferUtils;

import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.texture.GuiTextureGroup;
import com.lowdragmc.lowdraglib.gui.texture.TextTexture;
import com.lowdragmc.lowdraglib.gui.util.ClickData;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.jei.IngredientIO;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemHandlerHelper;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Suffix;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import tech.vixhentx.mcmod.ctnhlib.client.gui.RCUIWidget;
import tech.vixhentx.mcmod.ctnhlib.client.gui.RightConfiguratorPanel;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.Collections;
import java.util.List;

@Suffix("tooltip")
public class HugeItemBusPartMachine extends ItemBusPartMachine implements IAllowSameUIProvider {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            HugeItemBusPartMachine.class,
            ItemBusPartMachine.MANAGED_FIELD_HOLDER);

    @Getter
    @Persisted
    protected final NotifiableItemStackHandler shareInventory;

    public HugeItemBusPartMachine(IMachineBlockEntity holder, int tier, IO io, Object... args) {
        super(holder, tier, io, args);
        this.shareInventory = new NotifiableItemStackHandler(this, 9, IO.IN, IO.NONE).shouldSearchContent(false);
    }

    @Override
    protected @NotNull NotifiableItemStackHandler createInventory(Object... args) {
        return new NotifiableItemStackHandler(this, getInventorySize(), io, io,
                i -> new HugeItemStackHandler(i, getSlotMultiplier())) {

            @Override
            public boolean canCapOutput() {
                return true;
            }

            @Override
            public void exportToNearby(@NotNull Direction... facings) {
                if (isEmpty()) return;
                var level = getMachine().getLevel();
                var pos = getMachine().getPos();
                for (Direction facing : facings) {
                    var filter = getMachine().getItemCapFilter(facing, IO.OUT);
                    GTTransferUtils.getAdjacentItemHandler(level, pos, facing)
                            .ifPresent(adj -> HugeBusTransferHelper.transferItemsFiltered(this, adj, filter,
                                    Integer.MAX_VALUE));
                }
            }
        };
    }

    public static int getSlotMultiplier(int tier) {
        return Integer.MAX_VALUE;
    }

    int getSlotMultiplier() {
        return getSlotMultiplier(getTier());
    }

    @Override
    protected int getInventorySize() {
        return getInventorySize(getTier());
    }

    public static int getInventorySize(int tier) {
        if (tier < GTValues.EV) return 1 + tier;
        else return (1 + tier) * 2;
    }

    @Override
    public @NotNull Widget createUIWidget() {
        int inventorySize = getInventorySize();
        inventorySize = Math.min(inventorySize, 25); // 限制最大25个

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
                            new HugeSlotWidget(getInventory().storage, index, 4 + x * 18, 4 + y * 18, true,
                                    io.support(IO.IN))
                                    .setBackgroundTexture(GuiTextures.SLOT)
                                    .setIngredientIO(this.io == IO.IN ? IngredientIO.INPUT : IngredientIO.OUTPUT));
                    index++;
                }
            }
        }

        container.setBackground(GuiTextures.BACKGROUND_INVERSE);
        group.addWidget(container);

        return group;
    }

    @Override
    protected void autoIO() {
        super.autoIO();
    }

    protected void refundAll(ClickData clickData) {
        if (!clickData.isRemote) {
            this.setWorkingEnabled(false);
            getInventory().exportToNearby(getFrontFacing());
        }
    }

    @EN("Returns al items to the container in front")
    @CN("返还所有物品到面前的容器中")
    static Lang refund_item;

    @Override
    public void attachConfigurators(ConfiguratorPanel configuratorPanel) {
        super.attachConfigurators(configuratorPanel);
        if (this.io == IO.IN) {
            configuratorPanel.attachConfigurators(
                    new ButtonConfigurator(new GuiTextureGroup(GuiTextures.BUTTON, new TextTexture("\ud83d\udd19")),
                            this::refundAll)
                            .setTooltips(List.of(refund_item.translate())));

        }
    }

    @Override
    public void attachRightConfigurators(RightConfiguratorPanel configuratorPanel) {
        IAllowSameUIProvider.super.attachRightConfigurators(configuratorPanel);
        if (io != IO.IN) return;
        configuratorPanel.attachConfigurators(new FancyInvConfigurator(
                shareInventory.storage, Component.translatable("gui.gtceu.share_inventory.title"))
                .setTooltips(List.of(
                        Component.translatable("gui.gtceu.share_inventory.desc.1"))));
    }

    @Override
    public void onMachineRemoved() {
        super.onMachineRemoved();
        clearInventory(shareInventory);
    }

    @Override
    public ModularUI createUI(Player entityPlayer) {
        return new ModularUI(176, 166, this, entityPlayer).widget(new RCUIWidget(this, 176, 166));
    }

    public int[] calculateOptimalLayout(int slotCount) {
        if (slotCount <= 0) return new int[] { 1, 1 };

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
        } else if (slotCount <= 16) {
            // 10-16个：4x4网格
            cols = 4;
            rows = (int) Math.ceil(slotCount / 4.0);
        } else {
            // 16-25个：5x5网格
            cols = 5;
            rows = (int) Math.ceil(slotCount / 5.0);
        }

        return new int[] { cols, rows };
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

    public static class HugeItemStackHandler extends CustomItemStackHandler {

        public final int multiplier;

        public HugeItemStackHandler(int i, int p) {
            super(i);
            multiplier = p;
        }

        @Override
        public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (amount == 0) {
                return ItemStack.EMPTY;
            } else {
                this.validateSlotIndex(slot);
                ItemStack existing = this.stacks.get(slot);
                if (existing.isEmpty()) {
                    return ItemStack.EMPTY;
                } else {
                    int toExtract = Math.min(amount, getStackLimit(slot, existing));
                    if (existing.getCount() <= toExtract) {
                        if (!simulate) {
                            this.stacks.set(slot, ItemStack.EMPTY);
                            this.onContentsChanged(slot);
                            return existing;
                        } else {
                            return existing.copy();
                        }
                    } else {
                        if (!simulate) {
                            this.stacks.set(slot,
                                    ItemHandlerHelper.copyStackWithSize(existing, existing.getCount() - toExtract));
                            this.onContentsChanged(slot);
                        }

                        return ItemHandlerHelper.copyStackWithSize(existing, toExtract);
                    }
                }
            }
        }

        @Override
        public int getSlotLimit(int slot) {
            return multiplier == Integer.MAX_VALUE ? Integer.MAX_VALUE : 64 * multiplier;
        }

        @Override
        public int getStackLimit(int slot, @NotNull ItemStack stack) {
            return multiplier == Integer.MAX_VALUE ? Integer.MAX_VALUE :
                    Math.min(this.getSlotLimit(slot), stack.getMaxStackSize() * multiplier);
        }

        @Override
        public CompoundTag serializeNBT() {
            ListTag nbtTagList = new ListTag();

            for (int i = 0; i < this.stacks.size(); ++i) {
                ItemStack stack = this.stacks.get(i);

                if (!stack.isEmpty()) {
                    CompoundTag itemTag = new CompoundTag();

                    itemTag.putInt("Slot", i);

                    CompoundTag itemIdTag = new CompoundTag();
                    stack.save(itemIdTag);
                    itemTag.put("Item", itemIdTag);

                    itemTag.putInt("Count", stack.getCount());

                    nbtTagList.add(itemTag);
                }
            }

            CompoundTag nbt = new CompoundTag();
            nbt.put("Items", nbtTagList);
            nbt.putInt("Size", this.stacks.size());
            return nbt;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            this.setSize(nbt.contains("Size", CompoundTag.TAG_INT) ? nbt.getInt("Size") : this.stacks.size());

            ListTag tagList = nbt.getList("Items", CompoundTag.TAG_COMPOUND);

            Collections.fill(this.stacks, ItemStack.EMPTY);

            for (int i = 0; i < tagList.size(); ++i) {
                CompoundTag itemTags = tagList.getCompound(i);
                int slot = itemTags.getInt("Slot");

                if (slot >= 0 && slot < this.stacks.size()) {

                    CompoundTag itemData = itemTags.getCompound("Item");
                    ItemStack stack = ItemStack.of(itemData);

                    if (itemTags.contains("Count", CompoundTag.TAG_INT)) {
                        stack.setCount(itemTags.getInt("Count"));
                    }

                    this.stacks.set(slot, stack);
                }
            }

            this.onLoad();
        }
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }
}
