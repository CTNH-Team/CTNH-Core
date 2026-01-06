package io.github.cpearl0.ctnhcore.mixin.sophisticatedcore;

import com.lowdragmc.lowdraglib.gui.modular.ModularUIContainer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import net.p3pp3rf1y.sophisticatedcore.network.SyncSlotStackMessage;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SyncSlotStackMessage.class, remap = false)
public abstract class SyncSlotStackMessageMixin {

    @Shadow
    @Final
    private int windowId;

    @Shadow
    @Final
    private int slotNumber;

    @Shadow
    @Final
    private int stateId;

    @Shadow
    @Final
    private ItemStack stack;

    @Inject(method = "handleMessage", at = @At("HEAD"))
    private static void handle(SyncSlotStackMessage msg, CallbackInfo ci) {
        LocalPlayer player = Minecraft.getInstance().player;
        SyncSlotStackMessageMixin accessor = (SyncSlotStackMessageMixin) (Object) msg;
        if (player != null && player.containerMenu instanceof ModularUIContainer &&
                player.containerMenu.containerId == accessor.windowId) {
            player.containerMenu.setItem(accessor.slotNumber, accessor.stateId, accessor.stack);
        }
    }
}
