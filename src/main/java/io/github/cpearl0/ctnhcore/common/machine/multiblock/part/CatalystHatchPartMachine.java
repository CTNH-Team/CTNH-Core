package io.github.cpearl0.ctnhcore.common.machine.multiblock.part;

import io.github.cpearl0.ctnhcore.common.item.CatalystBehavior;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.ChemicalPlantMachine;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.widget.SlotWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.item.ItemIngredient;
import com.gregtechceu.gtceu.api.transfer.item.CustomItemStackHandler;

import com.lowdragmc.lowdraglib.gui.texture.ResourceTexture;
import com.lowdragmc.lowdraglib.gui.widget.ImageWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.ISubscription;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class CatalystHatchPartMachine extends TieredIOPartMachine {

    ResourceTexture SMALL_ARROW_OVERLAY = new ResourceTexture("ctnhcore:textures/gui/arrows/small_arrow_overlay.png");

    public CatalystHatchPartMachine(IMachineBlockEntity holder) {
        super(holder, GTValues.EV, IO.IN);
    }

    @Persisted
    public NotifiableItemStackHandler inventory = attachTrait(createInventory());
    @Persisted
    public NotifiableItemStackHandler buffer = attachTrait(new NotifiableItemStackHandler(this, 16, IO.NONE, IO.BOTH));

    private ISubscription bufferSubs = null;
    private ISubscription inventorySubs = null;
    private TickableSubscription transferSubs = null;

    //////////////////////////////////////
    // ***** Initialization ******//
    //////////////////////////////////////

    @Override
    public void onLoad() {
        super.onLoad();
        if (!isRemote()) {
            inventorySubs = inventory.addChangedListener(this::onInventoryChanged);
            bufferSubs = buffer.addChangedListener(this::onInventoryChanged);

        }
    }

    @Override
    public void onUnload() {
        super.onUnload();
        if (bufferSubs != null) {
            bufferSubs.unsubscribe();
        }
        if (inventorySubs != null) {
            inventorySubs.unsubscribe();
        }
    }

    private NotifiableItemStackHandler createInventory() {
        return new NotifiableItemStackHandler(this, 16, IO.IN, IO.OUT, (slots) -> new CustomItemStackHandler(slots) {

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }
        }) {

            @Override
            public boolean handleRecipe(IO io, GTRecipe recipe, List<ItemIngredient> left, boolean simulate) {
                if (!handlerIO.support(io)) return false;
                CustomItemStackHandler capability;
                if (simulate) {
                    NonNullList<ItemStack> items = NonNullList.create();
                    for (int i = 0; i < storage.getSlots(); i++) {
                        items.add(storage.getStackInSlot(i).copy());
                    }
                    capability = new CustomItemStackHandler(items);
                } else capability = storage;
                var iterator = left.listIterator();
                if (io == IO.IN) {
                    while (iterator.hasNext()) {
                        var ingredient = iterator.next();
                        int amountToConsume = ingredient.getCount();
                        if (!simulate) {
                            var chance = getChance();
                            var average = amountToConsume * chance;
                            var variance = amountToConsume * chance * (1 - chance);
                            amountToConsume = (int) Math.ceil(
                                    Math.sqrt(variance) * GTValues.RNG.nextGaussian() + average);
                        }
                        amountToConsume = Math.max(0, Math.min(ingredient.getCount(), amountToConsume));
                        int consumed = 0;
                        for (int i = 0; i < capability.getSlots(); i++) {
                            var item = capability.getStackInSlot(i);
                            if (item.isEmpty() || !ingredient.test(item)) continue;

                            CatalystBehavior behavior = CatalystBehavior.getBehaviour(item);
                            if (behavior != null) {
                                int damage = Math.min(amountToConsume - consumed, behavior.getDurability(item));
                                behavior.applyDamage(item, damage);
                                consumed += damage;
                                if (!simulate && item.isEmpty()) transferItems();
                            } else {
                                var extracted = capability.extractItem(i, amountToConsume - consumed, false);
                                consumed += extracted.getCount();
                            }
                            if (consumed >= amountToConsume) break;
                        }
                        int remaining = ingredient.getCount() - consumed;
                        if (remaining <= 0) {
                            iterator.remove();
                        } else if (consumed > 0) {
                            iterator.set(ingredient.copyWithCount(remaining));
                        }
                    }
                }
                return left.isEmpty();
            }
        };
    }

    //////////////////////////////////////
    // ******** Subscriptions ********//
    //////////////////////////////////////
    private void onInventoryChanged() {
        if (isWorkingEnabled() && !buffer.isEmpty()) {
            transferSubs = subscribeServerTick(this::transferItems);
        } else unsubscribe();
    }

    private void transferItems() {
        for (int i = 0; i < buffer.getSlots(); i++) {
            var stack = buffer.getStackInSlot(i);
            if (stack.isEmpty() || !inventory.getStackInSlot(i).isEmpty()) continue;
            if (!buffer.extractItem(i, 1, true).isEmpty()) {
                var copy = stack.copyWithCount(1);
                if (inventory.insertItemInternal(i, copy, true).isEmpty()) {
                    buffer.extractItem(i, 1, false);
                    inventory.insertItemInternal(i, copy, false);
                }
            }
        }
        unsubscribe();
    }

    private void unsubscribe() {
        if (transferSubs != null) {
            transferSubs.unsubscribe();
            transferSubs = null;
        }
    }

    //////////////////////////////////////
    // ********** GUI ***********//
    //////////////////////////////////////

    @Override
    public Widget createUIWidget() {
        var group = new WidgetGroup(0, 0, 18 * 8 + 31, 18 * 4 + 16);
        var slotsContainer = new WidgetGroup(4, 4, 18 * 8 + 23, 18 * 4 + 8);
        slotsContainer.addWidget(new ImageWidget(75, 31, 18, 18, SMALL_ARROW_OVERLAY));
        addSlots(slotsContainer, buffer, 4, 4, true);
        addSlots(slotsContainer, inventory, 91, 4, false);
        slotsContainer.setBackground(GuiTextures.BACKGROUND_INVERSE);
        group.addWidget(slotsContainer);
        return group;
    }

    private void addSlots(WidgetGroup container, NotifiableItemStackHandler handler, int x, int y,
                          boolean canPutItems) {
        var index = 0;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                container.addWidget(
                        new SlotWidget(
                                handler,
                                index++,
                                x + i * 18,
                                y + j * 18,
                                true,
                                canPutItems).setBackground(GuiTextures.SLOT));
            }
        }
    }

    //////////////////////////////////////
    // ********** Data **********//
    //////////////////////////////////////
    private float getChance() {
        for (var controller : controllers) {
            if (controller instanceof ChemicalPlantMachine chemicalPlantMachine) {
                return chemicalPlantMachine.getChance() / 100f;
            }
        }
        return 1f;
    }

    @Override
    public void setWorkingEnabled(boolean workingEnabled) {
        super.setWorkingEnabled(workingEnabled);
        onInventoryChanged();
    }
}
