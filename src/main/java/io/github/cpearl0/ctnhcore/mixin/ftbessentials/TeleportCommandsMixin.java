package io.github.cpearl0.ctnhcore.mixin.ftbessentials;

import io.github.cpearl0.ctnhcore.integration.ftbessentials.AsyncRtpManager;

import net.minecraft.server.level.ServerPlayer;

import dev.ftb.mods.ftbessentials.command.TeleportCommands;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TeleportCommands.class, remap = false)
public class TeleportCommandsMixin {

    @Inject(method = "rtp", at = @At("HEAD"), cancellable = true)
    private static void ctnhcore$useAsyncRtp(ServerPlayer player, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(AsyncRtpManager.start(player));
    }
}
