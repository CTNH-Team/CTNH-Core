package io.github.cpearl0.ctnhcore.mixin.sophisticatedcore;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.lowdragmc.lowdraglib.gui.modular.ModularUIContainer;
import net.minecraft.client.player.LocalPlayer;
import net.p3pp3rf1y.sophisticatedcore.network.SyncContainerStacksMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = SyncContainerStacksMessage.class, remap = false)
public class SyncContainerStacksMessageMixin {

    @Definition(id = "containerMenu", field = "Lnet/minecraft/client/player/LocalPlayer;containerMenu:Lnet/minecraft/world/inventory/AbstractContainerMenu;")
    @Definition(id = "player", local = @Local(type = LocalPlayer.class, name = "player"))
    @Expression("player.containerMenu instanceof ?")
    @ModifyExpressionValue(
            method = "handleMessage",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static boolean handle(boolean original, @Local(name = "player") LocalPlayer player) {
        return original || player.containerMenu instanceof ModularUIContainer;
    }
}
