package io.github.cpearl0.ctnhcore.mixin.ldlib;

import io.github.cpearl0.ctnhcore.common.machine.multiblock.hugehatch.HugeItemBusPartMachine;

import com.gregtechceu.gtceu.api.machine.WorkableTieredMachine;

import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.modular.ModularUIContainer;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerSynchronizer;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.p3pp3rf1y.sophisticatedcore.common.gui.HighStackCountSynchronizer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ModularUIContainer.class)
public abstract class ModularUIContainerMixin extends AbstractContainerMenu {

    @Shadow(remap = false)
    @Final
    private ModularUI modularUI;

    protected ModularUIContainerMixin(@Nullable MenuType<?> menuType, int containerId) {
        super(menuType, containerId);
    }

    @Redirect(
              method = "mergeItemStack",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/world/item/ItemStack;getMaxStackSize()I",
                       remap = true),
              remap = false)
    private static int getMaxStackSize(ItemStack instance) {
        return Integer.MAX_VALUE;
    }

    @Redirect(
              method = "quickMoveStack",
              at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getCount()I"))
    private int getCount(ItemStack stack) {
        return Math.min(stack.getCount(), stack.getMaxStackSize());
    }

    @Override
    public void setSynchronizer(@NotNull ContainerSynchronizer synchronizer) {
        Player player = modularUI.entityPlayer;
        var holder = modularUI.holder;
        if (player instanceof ServerPlayer serverPlayer &&
                (holder instanceof HugeItemBusPartMachine || holder instanceof WorkableTieredMachine)) {
            super.setSynchronizer(new HighStackCountSynchronizer(serverPlayer));
        } else {
            super.setSynchronizer(synchronizer);
        }
    }
}
