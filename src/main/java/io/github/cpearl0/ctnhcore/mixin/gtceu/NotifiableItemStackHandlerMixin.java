package io.github.cpearl0.ctnhcore.mixin.gtceu;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.IntProviderIngredient;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import com.gregtechceu.gtceu.api.transfer.item.CustomItemStackHandler;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.llamalad7.mixinextras.sugar.Local;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.hugehatch.HugeItemBusPartMachine;
import io.github.cpearl0.ctnhcore.utils.IAllowSameContainer;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Objects;
import java.util.function.IntFunction;

@Mixin(value = NotifiableItemStackHandler.class, remap = false)
public abstract class NotifiableItemStackHandlerMixin implements IAllowSameContainer {

    @Shadow
    @Final
    public CustomItemStackHandler storage;

    @Final
    @Shadow
    public IO handlerIO;

    @Shadow
    public abstract void onContentsChanged();

    @Shadow
    private static @Nullable ItemStack getActioned(CustomItemStackHandler storage, int index, List<?> actions) {
        return null;
    }

    @Persisted
    @Unique
    boolean ctnhcore$allowSameItems;


    @Inject(method = "<init>(Lcom/gregtechceu/gtceu/api/machine/MetaMachine;ILcom/gregtechceu/gtceu/api/capability/recipe/IO;Lcom/gregtechceu/gtceu/api/capability/recipe/IO;Ljava/util/function/IntFunction;)V",
    at = @At("TAIL"))
    public void injectInit(MetaMachine machine, int slots, IO handlerIO, IO capabilityIO, IntFunction storageFactory, CallbackInfo ci){
        ctnhcore$allowSameItems = handlerIO.support(IO.OUT);
    }

    @Override
    public boolean isAllowSame() {
        return ctnhcore$allowSameItems;
    }

    @Override
    public void setAllowSame(boolean b) {
        ctnhcore$allowSameItems = b;
        onContentsChanged();
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public List<Ingredient> handleRecipeInner(IO io, GTRecipe recipe, List<Ingredient> left, boolean simulate) {
        if (io != handlerIO) return left;
        if (io != IO.IN && io != IO.OUT) return left.isEmpty() ? null : left;

        // Temporarily remove listener so that we can broadcast the entire set of transactions once
        Runnable listener = storage.getOnContentsChanged();
        storage.setOnContentsChanged(() -> {});
        boolean changed = false;

        // Store the ItemStack in each slot after an operation
        // Necessary for simulation since we don't actually modify the slot's contents
        // Doesn't hurt for execution, and definitely cheaper than copying the entire storage
        ItemStack[] visited = new ItemStack[storage.getSlots()];
        for (var it = left.listIterator(); it.hasNext();) {
            var ingredient = it.next();
            if (ingredient.isEmpty()) {
                it.remove();
                continue;
            }

            ItemStack[] items;
            int amount;
            if (ingredient instanceof IntProviderIngredient provider) {
                provider.setItemStacks(null);
                provider.setSampledCount(-1);

                ItemStack output;
                if (simulate) {
                    output = provider.getMaxSizeStack();
                    items = new ItemStack[] { output };
                } else {
                    items = provider.getItems();
                    if (items.length == 0 || items[0].isEmpty()) {
                        it.remove();
                        continue;
                    }
                    output = items[0];
                }
                amount = output.getCount();
            } else {
                items = ingredient.getItems();
                if (items.length == 0 || items[0].isEmpty()) {
                    it.remove();
                    continue;
                }
                if (ingredient instanceof SizedIngredient si) amount = si.getAmount();
                else amount = items[0].getCount();
            }
            if (io == IO.OUT && !ctnhcore$allowSameItems) {
                ItemStack output = items[0].copyWithCount(amount);
                int existingSlot = -1;
                for (int i = 0; i < storage.getSlots(); i++) {
                    if(GTUtil.isSameItemSameTags(output, storage.getStackInSlot(i))){
                        existingSlot = i;
                        break;
                    }
                }
                if(existingSlot != -1){
                    var remainder = storage.insertItem(existingSlot, output, simulate);
                    if (remainder.getCount() < amount) {
                        changed = true;
                        ItemStack current = visited[existingSlot] == null ? storage.getStackInSlot(existingSlot) : visited[existingSlot];
                        int count = current.getCount();
                        visited[existingSlot] = output.copyWithCount(count + amount - remainder.getCount());
                    }
                    amount = remainder.getCount();
                    if (amount > 0) {
                        if (ingredient instanceof SizedIngredient si) {
                            si.setAmount(amount);
                        } else {
                            items[0].setCount(amount);
                        }
                    }
                    else {
                        it.remove();
                    }
                    continue;
                }
            }
            for (int slot = 0; slot < storage.getSlots(); ++slot) {
                ItemStack current = visited[slot] == null ? storage.getStackInSlot(slot) : visited[slot];
                int count = current.getCount();

                if (io == IO.IN) {
                    if (current.isEmpty()) continue;
                    if (ingredient.test(current)) {
                        var extracted = getActioned(storage, slot, recipe.ingredientActions);
                        if (extracted == null) extracted = storage.extractItem(slot, Math.min(count, amount), simulate);
                        if (!extracted.isEmpty()) {
                            changed = true;
                            visited[slot] = extracted.copyWithCount(count - extracted.getCount());
                        }
                        amount -= extracted.getCount();
                    }
                } else { // IO.OUT
                    ItemStack output = items[0].copyWithCount(amount);
                    // Only try this slot if not visited or if visited with the same type of item
                    if (visited[slot] == null || GTUtil.isSameItemSameTags(visited[slot], output)) {
                        var remainder = getActioned(storage, slot, recipe.ingredientActions);
                        if (remainder == null){
                            remainder = storage.insertItem(slot, output, simulate);

                        }
                        if (remainder.getCount() < amount) {
                            changed = true;
                            visited[slot] = output.copyWithCount(count + amount - remainder.getCount());
                        }
                        amount = remainder.getCount();
                        if(!ctnhcore$allowSameItems){
                            if(amount <= 0) it.remove();
                            break;
                        }
                    }
                }

                if (amount <= 0) {
                    it.remove();
                    break;
                }
            }
            // Modify ingredient if we didn't finish it off
            if (amount > 0) {
                if (ingredient instanceof SizedIngredient si) {
                    si.setAmount(amount);
                } else {
                    items[0].setCount(amount);
                }
            }
        }

        storage.setOnContentsChanged(listener);
        if (changed && !simulate) listener.run();

        return left.isEmpty() ? null : left;
    }

    @Inject(
            method = "insertItem",
            at = @At("HEAD"),
            cancellable = true
    )
    private void gtceu$limitSameItemCapability(
            int slot,
            ItemStack stack,
            boolean simulate,
            CallbackInfoReturnable<ItemStack> cir
    ) {
        if (!ctnhcore$allowSameItems) {
            int existingSlot = -1;
            for (int i = 0; i < storage.getSlots(); i++) {
                if(GTUtil.isSameItemSameTags(stack, storage.getStackInSlot(i)))
                    existingSlot = i;
            }
            if (existingSlot != -1 && existingSlot != slot) {
                cir.setReturnValue(stack);
            }
        }
    }
}
