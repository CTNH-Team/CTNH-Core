package io.github.cpearl0.ctnhcore.common.machine.multiblock.part;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.widget.SlotWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.transfer.item.CustomItemStackHandler;
import com.lowdragmc.lowdraglib.gui.texture.ResourceTexture;
import com.lowdragmc.lowdraglib.gui.widget.ImageWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.ISubscription;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import io.github.cpearl0.ctnhcore.common.item.CatalystBehavior;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.ChemicalPlantMachine;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public class CatalystHatchPartMachine extends TieredIOPartMachine {
    public final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(CatalystHatchPartMachine.class, TieredIOPartMachine.MANAGED_FIELD_HOLDER);
    ResourceTexture SMALL_ARROW_OVERLAY = new ResourceTexture("ctnhcore:textures/gui/arrows/small_arrow_overlay.png");
    public CatalystHatchPartMachine(IMachineBlockEntity holder) {
        super(holder, GTValues.IV, IO.IN);
    }
    @Persisted
    public NotifiableItemStackHandler buffer = new NotifiableItemStackHandler(this, 16,IO.NONE, IO.BOTH);

    @Persisted
    public NotifiableItemStackHandler inventory = createInventory();
    private ISubscription bufferSubs = null;
    private ISubscription inventorySubs = null;
    private TickableSubscription transferSubs = null;


    //////////////////////////////////////
    // *****     Initialization    ******//
    //////////////////////////////////////

    @Override
    public void onLoad() {
        super.onLoad();
        if (!isRemote()) {
            bufferSubs = buffer.addChangedListener(this::onInventoryChanged);
            inventorySubs = inventory.addChangedListener(this::onInventoryChanged);
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
            public List<Ingredient> handleRecipeInner(IO io, GTRecipe recipe, List<Ingredient> left, boolean simulate) {
                if (io != handlerIO) return left;

                CustomItemStackHandler capability;
                if (simulate) {
                    NonNullList<ItemStack> items = NonNullList.create();
                    for (int i = 0; i < storage.getSlots(); i++) {
                        items.add(storage.getStackInSlot(i));
                    }
                    capability = new CustomItemStackHandler(items);
                } else capability = storage;
                var iterator = left.iterator();
                if (io == IO.IN) {
                    while (iterator.hasNext()) {
                        var ingredient = iterator.next();
                        for (int i = 0; i < capability.getSlots(); i++) {
                            var item = capability.getStackInSlot(i);
                            var itemStack = simulate? item.copy() : item;
                            // Does not look like a good implementation, but I think it's at least equal to
                            // vanilla Ingredient::test
                            if (ingredient.test(itemStack)) {
                                var ingredientStacks = ingredient.getItems();
                                for (var ingredientStack : ingredientStacks) {
                                    if (ingredientStack.getItem().equals(itemStack.getItem())) {
                                        CatalystBehavior behavior = CatalystBehavior.getBehaviour(itemStack);
                                        var count = ingredientStack.getCount();
                                        if (!simulate) {
                                            var chance = getChance();
                                            var u = (count * chance);
                                            var e = (count * chance * (1 - chance));
                                            count = (int) Math.ceil(Math.sqrt(e) * GTValues.RNG.nextGaussian() + u);
                                        }
                                        if (behavior != null) {
                                            var damage = Math.min(count, behavior.getDurability(itemStack));
                                            behavior.applyDamage(itemStack, damage);
                                            ingredientStack.shrink(damage);
                                            if (itemStack.isEmpty() || ingredientStack.isEmpty()) {
                                                transferItems();
                                            }
                                        } else {
                                            var extracted = capability.extractItem(i, count, false);
                                            ingredientStack.shrink(extracted.getCount());
                                        }
                                        if (ingredientStack.isEmpty()) {
                                            iterator.remove();
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return left.isEmpty()? null : left;
            }
        };
    }

    //////////////////////////////////////
    // ********   Subscriptions  ********//
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
    // **********     GUI     ***********//
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
    private void addSlots(WidgetGroup container, NotifiableItemStackHandler handler, int x, int y, boolean canPutItems) {
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
                                canPutItems
                        ).setBackground(GuiTextures.SLOT)
                );
            }
        }
    }

    //////////////////////////////////////
    // **********     Data     **********//
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

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }
}
