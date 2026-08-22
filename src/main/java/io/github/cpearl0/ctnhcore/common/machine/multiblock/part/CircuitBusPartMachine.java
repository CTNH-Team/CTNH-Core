package io.github.cpearl0.ctnhcore.common.machine.multiblock.part;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.widget.BlockableSlotWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IMachineLife;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IDistinctPart;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.item.ItemIngredient;
import com.gregtechceu.gtceu.api.transfer.item.CustomItemStackHandler;

import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.ItemStack;

import lombok.Getter;
import org.jetbrains.annotations.MustBeInvokedByOverriders;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CircuitBusPartMachine extends TieredIOPartMachine implements IDistinctPart, IMachineLife {

    @Getter
    @Persisted
    private final NotifiableItemStackHandler inventory;

    @Getter
    @Persisted
    @DescSynced
    private boolean isLocked;

    public CircuitBusPartMachine(IMachineBlockEntity holder) {
        super(holder, GTValues.HV, IO.IN);
        inventory = new CircuitItemHandler(this);
    }

    @Override
    public void onMachineRemoved() {
        clearInventory(inventory.storage);
    }

    @Override
    public boolean isDistinct() {
        return getInventory().isDistinct();
    }

    @Override
    public void setDistinct(boolean isDistinct) {
        getInventory().setDistinct(isDistinct);
    }

    @MustBeInvokedByOverriders
    @Override
    public void removedFromController(IMultiController controller) {
        super.removedFromController(controller);
        isLocked = false;
    }

    @Override
    public Widget createUIWidget() {
        var group = new WidgetGroup(0, 0, 34, 34);
        var container = new WidgetGroup(4, 4, 26, 26);
        container.addWidget(
                new BlockableSlotWidget(getInventory(), 0, 4, 4, true, true)
                        .setIsBlocked(this::isLocked)
                        .setBackground(GuiTextures.SLOT, GuiTextures.BACKGROUND_INVERSE));
        group.addWidget(container);

        return group;
    }

    private class CircuitItemHandler extends NotifiableItemStackHandler {

        private CircuitItemHandler(MetaMachine machine) {
            super(machine, 1, IO.BOTH, IO.BOTH, size -> new CustomItemStackHandler(size) {

                @Override
                public int getSlotLimit(int slot) {
                    return 1;
                }
            });
        }

        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }

        @NotNull
        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (!isLocked) {
                return super.extractItem(slot, amount, simulate);
            }
            return ItemStack.EMPTY;
        }

        @Override
        public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
            if (!isLocked) {
                return super.insertItem(slot, stack, simulate);
            }
            return stack;
        }

        @Override
        public boolean handleRecipe(IO io, GTRecipe recipe, List<ItemIngredient> left, boolean simulate) {
            if (io == IO.OUT && simulate) {
                return true;
            }
            boolean result = super.handleRecipe(io, recipe, left, simulate);
            if (result && !simulate) {
                isLocked = io == IO.IN;
            }
            return result;
        }
    }
}
