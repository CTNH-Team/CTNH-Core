package io.github.cpearl0.ctnhcore.mixin.create;

import net.minecraft.world.item.ItemStack;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.content.logistics.BigItemStack;
import com.simibubi.create.content.logistics.packager.InventorySummary;
import com.simibubi.create.content.logistics.stockTicker.StockKeeperRequestScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

/**
 * Fixes Create's Stock Keeper request screen so it correctly handles
 * modded items whose {@code ItemStack} NBT is mutated on the client
 * during tooltip rendering (Apotheosis socketed gems, GTCEU tool
 * stats, etc.). Those mutations cause the displayed stack to drift
 * away from the server snapshot, breaking
 * {@link StockKeeperRequestScreen#getOrderForItem} which uses strict
 * NBT equality.
 *
 * <p>
 * <b>Strategy:</b> use the flat index of a click position in
 * {@code displayedItems} as the lookup key. On every
 * {@code refreshSearchResults} RETURN we build a parallel
 * {@code ctnh$originList} of fresh origin stacks (one per entry,
 * indexed identically to {@code displayedItems}). When the user
 * clicks an item, the {@code copyWithCount} {@code @Redirect} in
 * {@code mouseClicked} records the flat index of the clicked entry,
 * and the TAIL injection stores it in {@code ctnh$orderFlatIndices}.
 * On REBUILD we re-link each order's {@code stack} to the new origin
 * at its stored index. {@code getOrderForItem} and
 * {@code forcedEntries.getCountOf} are wrapped to look up by index
 * instead of NBT, so client-side NBT mutations no longer affect
 * matching.
 *
 * <p>
 * <b>Why instance fields, not static:</b> static state outlives the
 * screen instance: if the player closes and reopens the stock
 * ticker, the lists carry stale entries. Instance fields tie the
 * parallel state to the screen lifetime, eliminating cross-session
 * pollution.
 */
@Mixin(value = StockKeeperRequestScreen.class, remap = false)
public class StockKeeperRequestScreenMixin {

    @Shadow
    public List<List<BigItemStack>> displayedItems;

    @Shadow
    public List<BigItemStack> itemsToOrder;

    @Unique
    private final List<ItemStack> ctnh$originList = new ArrayList<>();

    @Unique
    private final List<Integer> ctnh$orderFlatIndices = new ArrayList<>();

    @Unique
    private int ctnh$pendingFlatIdx = -2;

    @Unique
    private int ctnh$orderSizeAtClickStart = -1;

    @Unique
    private static ItemStack ctnh$copyStack(ItemStack src) {
        if (src == null || src.isEmpty()) {
            return src;
        }
        return src.copy();
    }

    @Unique
    private int ctnh$findFlatIndex(ItemStack target) {
        if (this.displayedItems == null || target == null) {
            return -1;
        }
        int flat = 0;
        for (List<BigItemStack> row : this.displayedItems) {
            if (row == null) {
                continue;
            }
            for (BigItemStack entry : row) {
                if (entry != null && entry.stack == target) {
                    return flat;
                }
                flat++;
            }
        }
        return -1;
    }

    @Inject(method = "refreshSearchResults", at = @At("RETURN"), remap = false)
    private void ctnh$rebuildOriginList(boolean scrollBackUp, CallbackInfo ci) {
        ctnh$originList.clear();
        if (this.displayedItems != null) {
            for (List<BigItemStack> row : this.displayedItems) {
                if (row == null) {
                    continue;
                }
                for (BigItemStack entry : row) {
                    if (entry == null || entry.stack == null || entry.stack.isEmpty()) {
                        ctnh$originList.add(null);
                    } else {
                        ctnh$originList.add(ctnh$copyStack(entry.stack));
                    }
                }
            }
        }

        // Keep ctnh$orderFlatIndices aligned with itemsToOrder.
        // Pad with -1 if we somehow missed an add (e.g. scroll/schematic
        // paths bypass our copyWithCount redirect). Trim from the end if
        // itemsToOrder shrank (e.g. revalidateOrders called removeAll).
        if (this.itemsToOrder != null) {
            int target = this.itemsToOrder.size();
            while (ctnh$orderFlatIndices.size() < target) {
                ctnh$orderFlatIndices.add(-1);
            }
            while (ctnh$orderFlatIndices.size() > target) {
                ctnh$orderFlatIndices.remove(ctnh$orderFlatIndices.size() - 1);
            }
            // Re-link orders to origins at the stored indices.
            for (int i = this.itemsToOrder.size() - 1; i >= 0; i--) {
                int idx = i < ctnh$orderFlatIndices.size() ? ctnh$orderFlatIndices.get(i) : -1;
                if (idx < 0 || idx >= ctnh$originList.size() || ctnh$originList.get(idx) == null) {
                    // No reliable origin to re-link to. Drop the order.
                    this.itemsToOrder.remove(i);
                    if (i < ctnh$orderFlatIndices.size()) {
                        ctnh$orderFlatIndices.remove(i);
                    }
                } else {
                    this.itemsToOrder.get(i).stack = ctnh$copyStack(ctnh$originList.get(idx));
                }
            }
        } else {
            ctnh$orderFlatIndices.clear();
        }
    }

    /**
     * Redirects the {@code ItemStack.copyWithCount(1)} call that
     * builds the {@code BigItemStack} appended to
     * {@code itemsToOrder} in {@code mouseClicked}. We build the new
     * stack from the pristine origin at the same flat index, and
     * record that index so the TAIL {@code @Inject} can store it in
     * {@link #ctnh$orderFlatIndices}.
     */
    @Redirect(
              method = "mouseClicked",
              remap = true,
              at = @At(
                       value = "INVOKE",
                       target = "Lnet/minecraft/world/item/ItemStack;copyWithCount(I)Lnet/minecraft/world/item/ItemStack;",
                       ordinal = 0))
    private ItemStack ctnh$redirectCopyWithCountForOrder(ItemStack displayed, int count) {
        int flatIdx = ctnh$findFlatIndex(displayed);
        ctnh$pendingFlatIdx = flatIdx;
        if (flatIdx >= 0 && flatIdx < ctnh$originList.size() && ctnh$originList.get(flatIdx) != null) {
            return ctnh$copyStack(ctnh$originList.get(flatIdx)).copyWithCount(count);
        }
        return displayed.copyWithCount(count);
    }

    @Inject(method = "mouseClicked", at = @At("HEAD"), remap = true)
    private void ctnh$onMouseClickedHead(double mouseX, double mouseY, int button,
                                         CallbackInfoReturnable<Boolean> cir) {
        ctnh$orderSizeAtClickStart = (this.itemsToOrder == null) ? -1 : this.itemsToOrder.size();
        ctnh$pendingFlatIdx = -2;
    }

    @Inject(method = "mouseClicked", at = @At("TAIL"), remap = true)
    private void ctnh$onMouseClickedTail(double mouseX, double mouseY, int button,
                                         CallbackInfoReturnable<Boolean> cir) {
        if (ctnh$pendingFlatIdx < 0 || ctnh$orderSizeAtClickStart < 0) {
            ctnh$pendingFlatIdx = -2;
            ctnh$orderSizeAtClickStart = -1;
            return;
        }
        if (this.itemsToOrder != null) {
            int now = this.itemsToOrder.size();
            if (now == ctnh$orderSizeAtClickStart + 1) {
                while (ctnh$orderFlatIndices.size() < now) {
                    ctnh$orderFlatIndices.add(-1);
                }
                ctnh$orderFlatIndices.set(now - 1, ctnh$pendingFlatIdx);
            }
        }
        ctnh$pendingFlatIdx = -2;
        ctnh$orderSizeAtClickStart = -1;
    }

    @WrapOperation(
                   method = "renderItemEntry",
                   remap = false,
                   at = @At(
                            value = "INVOKE",
                            target = "Lcom/simibubi/create/content/logistics/stockTicker/StockKeeperRequestScreen;getOrderForItem(Lnet/minecraft/world/item/ItemStack;)Lcom/simibubi/create/content/logistics/BigItemStack;"))
    private BigItemStack ctnh$wrapGetOrderForItem(StockKeeperRequestScreen self, ItemStack displayed,
                                                  Operation<BigItemStack> original) {
        int flatIdx = ctnh$findFlatIndex(displayed);
        if (flatIdx < 0) {
            return original.call(self, displayed);
        }
        if (this.itemsToOrder != null) {
            for (int i = 0; i < this.itemsToOrder.size() && i < ctnh$orderFlatIndices.size(); i++) {
                if (ctnh$orderFlatIndices.get(i) == flatIdx) {
                    return this.itemsToOrder.get(i);
                }
            }
        }
        return null;
    }

    @WrapOperation(
                   method = "renderItemEntry",
                   remap = false,
                   at = @At(
                            value = "INVOKE",
                            target = "Lcom/simibubi/create/content/logistics/packager/InventorySummary;getCountOf(Lnet/minecraft/world/item/ItemStack;)I"))
    private int ctnh$wrapGetCountOf(InventorySummary forcedEntries, ItemStack displayed,
                                    Operation<Integer> original) {
        int flatIdx = ctnh$findFlatIndex(displayed);
        if (flatIdx < 0 || flatIdx >= ctnh$originList.size()) {
            return original.call(forcedEntries, displayed);
        }
        ItemStack origin = ctnh$originList.get(flatIdx);
        if (origin == null) {
            return original.call(forcedEntries, displayed);
        }
        return original.call(forcedEntries, origin);
    }
}
