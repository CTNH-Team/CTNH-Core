package io.github.cpearl0.ctnhcore.mixin.mc;

import com.lowdragmc.lowdraglib.gui.modular.ModularUIContainer;
import io.github.cpearl0.ctnhcore.common.gui.HugeSlotWidget;
import net.minecraft.core.NonNullList;
import net.minecraft.network.protocol.game.ClientboundContainerSetContentPacket;
import net.minecraft.network.protocol.game.ClientboundContainerSetDataPacket;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerSynchronizer;
import net.minecraft.world.item.ItemStack;
import net.p3pp3rf1y.sophisticatedcore.common.gui.HighStackCountSynchronizer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ServerPlayer.class)
public class ServerPlayerMixin {

    @Unique
    ServerPlayer self = (ServerPlayer)(Object)this;

    @Redirect(
            method = "initMenu",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/inventory/AbstractContainerMenu;setSynchronizer(Lnet/minecraft/world/inventory/ContainerSynchronizer;)V")

    )
    public void setSynchronizer(AbstractContainerMenu instance, ContainerSynchronizer synchronizer){
        if(instance instanceof ModularUIContainer &&
                instance.slots.stream().anyMatch(slot -> slot instanceof HugeSlotWidget.HugeWidgetSlotItemHandler))
            instance.setSynchronizer(new HighStackCountSynchronizer(self));
        else
            instance.setSynchronizer(synchronizer);
    }
}
