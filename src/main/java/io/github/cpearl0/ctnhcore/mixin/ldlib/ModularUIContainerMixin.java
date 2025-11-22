package io.github.cpearl0.ctnhcore.mixin.ldlib;

import com.lowdragmc.lowdraglib.gui.modular.ModularUIContainer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ModularUIContainer.class)
public class ModularUIContainerMixin {
    @Redirect(
            method = "mergeItemStack",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getMaxStackSize()I")
    )
    private static int getMaxStackSize(ItemStack instance){
        return Integer.MAX_VALUE;
    }

    @Redirect(
            method = "quickMoveStack",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getCount()I")
    )
    private int getCount(ItemStack stack){
        return Math.min(stack.getCount(), stack.getMaxStackSize());
    }
}
