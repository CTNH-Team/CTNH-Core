package io.github.cpearl0.ctnhcore.mixin.gtceu;

import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.transfer.item.CustomItemStackHandler;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.hugehatch.HugeItemBusPartMachine;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = NotifiableItemStackHandler.class, remap = false)
public class NotifiableItemStackHandlerMixin {
    @Redirect(
            method = "handleRecipe",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getMaxStackSize()I")
    )
    private static int getMaxStackSize(ItemStack instance, @Local(name = "storage") CustomItemStackHandler storage, @Local(name = "slot") int slot){
        if(storage instanceof HugeItemBusPartMachine.HugeItemStackHandler hugeItemStackHandler)
            return hugeItemStackHandler.getStackLimit(slot, instance);
        else return instance.getMaxStackSize();
    }
}
