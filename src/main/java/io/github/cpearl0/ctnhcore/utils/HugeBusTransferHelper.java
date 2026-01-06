package io.github.cpearl0.ctnhcore.utils;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class HugeBusTransferHelper {

    public static int transferItemsFiltered(@NotNull IItemHandler source, @NotNull IItemHandler dest,
                                            @NotNull Predicate<ItemStack> filter, int transferLimit) {
        int toTransfer = transferLimit;

        MainLoop:
        for (int i = 0; i < source.getSlots(); i++) {
            while (toTransfer > 0) {
                ItemStack stack = source.getStackInSlot(i);
                if (stack.isEmpty() || !filter.test(stack)) continue MainLoop;

                var canExtract = source.extractItem(i, toTransfer, true);
                if (canExtract.isEmpty()) continue MainLoop;
                int canInsert = canExtract.getCount() -
                        ItemHandlerHelper.insertItemStacked(dest, canExtract, true).getCount();

                if (canInsert <= 0) continue MainLoop;
                var extracted = source.extractItem(i, canInsert, false);
                var remainder = ItemHandlerHelper.insertItemStacked(dest, extracted, false);
                toTransfer -= (canInsert - remainder.getCount());

            }
        }
        return transferLimit - toTransfer;
    }
}
