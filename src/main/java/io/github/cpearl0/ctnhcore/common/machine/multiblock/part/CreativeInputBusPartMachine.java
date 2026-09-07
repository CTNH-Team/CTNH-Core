package io.github.cpearl0.ctnhcore.common.machine.multiblock.part;

import io.github.cpearl0.ctnhcore.api.machine.multiblock.UnlimitedItemStackTransfer;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.fancy.ConfiguratorPanel;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.fancyconfigurator.CircuitFancyConfigurator;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IDistinctPart;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.item.ItemIngredient;
import com.gregtechceu.gtceu.api.transfer.item.CustomItemStackHandler;
import com.gregtechceu.gtceu.common.item.IntCircuitBehaviour;

import com.lowdragmc.lowdraglib.gui.widget.PhantomSlotWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.misc.ItemStackTransfer;
import com.lowdragmc.lowdraglib.side.item.IItemTransfer;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.utils.Position;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;

import javax.annotation.ParametersAreNonnullByDefault;

import static com.lowdragmc.lowdraglib.gui.util.DrawerHelper.drawItemStack;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CreativeInputBusPartMachine extends TieredIOPartMachine implements IDistinctPart {

    private final int ITEM_SIZE = 5;

    @Getter
    @Persisted
    private final InfinityItemStackHandler inventory;

    @Getter
    @Persisted
    @DescSynced
    protected final NotifiableItemStackHandler circuitInventory;

    private boolean distinct;

    public CreativeInputBusPartMachine(IMachineBlockEntity holder,
                                       Function<Integer, ItemStackTransfer> transferFactory) {
        super(holder, GTValues.MAX, IO.IN);
        this.inventory = attachTrait(new InfinityItemStackHandler(this, getInventorySize(), io, io,
                UnlimitedItemStackTransfer::new));
        this.circuitInventory = attachTrait(createCircuitItemHandler());
    }

    public CreativeInputBusPartMachine(IMachineBlockEntity holder) {
        this(holder, ItemStackTransfer::new);
    }

    protected int getInventorySize() {
        return ITEM_SIZE * ITEM_SIZE;
    }

    protected NotifiableItemStackHandler createCircuitItemHandler() {
        return new NotifiableItemStackHandler(this, 1, IO.IN, IO.NONE)
                .setFilter(IntCircuitBehaviour::isIntegratedCircuit);
    }

    @Override
    public int tintColor(int index) {
        if (index == 9) return getRealColor();
        return -1;
    }

    @Override
    public boolean isDistinct() {
        return distinct;
    }

    @Override
    public void setDistinct(boolean isDistinct) {
        distinct = isDistinct;
    }

    @Override
    public void setWorkingEnabled(boolean workingEnabled) {
        super.setWorkingEnabled(workingEnabled);
        inventory.notifyListeners();
    }

    public void attachConfigurators(ConfiguratorPanel left, ConfiguratorPanel right) {
        IDistinctPart.super.attachConfigurators(left, right);
        left.attachConfigurators(new CircuitFancyConfigurator(circuitInventory.storage));
    }

    @Override
    public Widget createUIWidget() {
        int rowSize = ITEM_SIZE;
        int colSize = ITEM_SIZE;
        if (getInventorySize() == 8) {
            rowSize = 4;
            colSize = 2;
        }
        var group = new WidgetGroup(0, 0, 18 * rowSize + 16, 18 * colSize + 16);
        var container = new WidgetGroup(4, 4, 18 * rowSize + 8, 18 * colSize + 8);
        int index = 0;
        for (int y = 0; y < colSize; y++) {
            for (int x = 0; x < rowSize; x++) {
                int finalIndex = index++;
                container.addWidget(
                        new PhantomSlotWidget(inventory, finalIndex, 4 + x * 18, 4 + y * 18) {

                            @Override
                            @OnlyIn(Dist.CLIENT)
                            public void drawInBackground(@NotNull GuiGraphics graphics, int mouseX, int mouseY,
                                                         float partialTicks) {
                                Position position = getPosition();
                                GuiTextures.SLOT.draw(graphics, mouseX, mouseY, position.x, position.y, 18, 18);
                                GuiTextures.CONFIG_ARROW_DARK.draw(graphics, mouseX, mouseY, position.x, position.y,
                                        18, 18);
                                int stackX = position.x + 1;
                                int stackY = position.y + 1;
                                if (getHandler() != null) {
                                    var stack = getHandler().getItem().copy();
                                    stack.setCount(1);
                                    drawItemStack(graphics, stack, stackX, stackY, 0xFFFFFFFF, null);
                                    if (mouseOverStock(mouseX, mouseY)) {
                                        int color = 0x80FFFFFF;
                                        graphics.fill(stackX, stackY + 18, stackX + 16, stackY + 18 + 1, color);
                                        graphics.fill(stackX, stackY + 18 + 16 - 1, stackX + 16, stackY + 18 + 16,
                                                color);
                                        graphics.fill(stackX, stackY + 18, stackX + 1, stackY + 18 + 16, color);
                                        graphics.fill(stackX + 16 - 1, stackY + 18, stackX + 16, stackY + 18 + 16,
                                                color);
                                    }
                                }
                            }

                            private boolean mouseOverStock(double mouseX, double mouseY) {
                                Position position = getPosition();
                                return isMouseOver(position.x, position.y + 18, 18, 18, mouseX, mouseY);
                            }
                        }
                                .setClearSlotOnRightClick(false)
                                .setChangeListener(this::markDirty));
            }
        }

        container.setBackground(GuiTextures.BACKGROUND_INVERSE);
        group.addWidget(container);

        return group;
    }

    private class InfinityItemStackHandler extends NotifiableItemStackHandler implements IItemTransfer {

        public InfinityItemStackHandler(MetaMachine machine, int slots, @NotNull IO handlerIO,
                                        @NotNull IO capabilityIO, IntFunction<CustomItemStackHandler> storageFactory) {
            super(machine, slots, handlerIO, capabilityIO, storageFactory);
        }

        @Override
        public boolean handleRecipe(IO io, GTRecipe recipe, List<ItemIngredient> left, boolean simulate) {
            if (isWorkingEnabled()) {
                return super.handleRecipe(io, recipe, left, simulate);
            }
            return false;
        }

        @Override
        public ItemStack insertItem(int index, ItemStack itemStack, boolean simulate, boolean notify) {
            if (notify) {
                notifyListeners();
            }
            return insertItem(index, itemStack, simulate);
        }

        @Override
        public ItemStack extractItem(int index, int count, boolean simulate, boolean notify) {
            if (notify) {
                notifyListeners();
            }
            return extractItem(index, count, simulate);
        }

        @Override
        public @NotNull Object createSnapshot() {
            return null;
        }

        @Override
        public void restoreFromSnapshot(Object o) {}

        @Override
        public void setStackInSlot(int index, @NotNull ItemStack stack) {
            if (!stack.isEmpty()) {
                stack.setCount(Integer.MAX_VALUE);
            }
            super.setStackInSlot(index, stack);
        }
    }
}
