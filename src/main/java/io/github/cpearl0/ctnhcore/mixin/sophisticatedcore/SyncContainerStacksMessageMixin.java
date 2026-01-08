package io.github.cpearl0.ctnhcore.mixin.sophisticatedcore;

import com.lowdragmc.lowdraglib.gui.modular.ModularUIContainer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import net.p3pp3rf1y.sophisticatedcore.network.SyncContainerStacksMessage;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = SyncContainerStacksMessage.class, remap = false)
public abstract class SyncContainerStacksMessageMixin {

    @Shadow
    @Final
    private int windowId;

    @Shadow
    @Final
    private int stateId;

    @Shadow
    @Final
    private List<ItemStack> itemStacks;

    @Shadow
    @Final
    private ItemStack carriedStack;

    @Inject(method = "handleMessage", at = @At("HEAD"))
    private static void handle(SyncContainerStacksMessage msg, CallbackInfo ci) {
        LocalPlayer player = Minecraft.getInstance().player;
        SyncContainerStacksMessageMixin accessor = (SyncContainerStacksMessageMixin) (Object) msg;
        if (player != null && player.containerMenu instanceof ModularUIContainer &&
                player.containerMenu.containerId == accessor.windowId) {
            player.containerMenu.initializeContents(accessor.stateId, accessor.itemStacks, accessor.carriedStack);
        }
    }
}
