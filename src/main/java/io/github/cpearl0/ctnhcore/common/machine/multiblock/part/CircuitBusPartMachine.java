package io.github.cpearl0.ctnhcore.common.machine.multiblock.part;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.widget.BlockableSlotWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.IMachineModifyDrops;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IDistinctPart;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;

import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.ItemStack;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CircuitBusPartMachine extends TieredIOPartMachine implements IDistinctPart, IMachineModifyDrops {

    @Getter
    @Persisted
    private final NotifiableItemStackHandler inventory;

    @Getter
    @Setter
    @Persisted
    @DescSynced
    private boolean isLocked;

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(CircuitBusPartMachine.class,
            TieredIOPartMachine.MANAGED_FIELD_HOLDER);

    public CircuitBusPartMachine(IMachineBlockEntity holder, int tier) {
        super(holder, tier, IO.IN);
        inventory = new NotifiableItemStackHandler(this, 1, IO.IN, IO.BOTH);
    }

    @Override
    public void onDrops(List<ItemStack> drops) {
        clearInventory(getInventory().storage);
    }

    @Override
    public boolean isDistinct() {
        return getInventory().isDistinct();
    }

    @Override
    public void setDistinct(boolean isDistinct) {
        getInventory().setDistinct(isDistinct);
    }

    public void setItem(ItemStack dataItem) {
        inventory.setStackInSlot(0, dataItem);
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

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }
}
