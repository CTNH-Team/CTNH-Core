package io.github.cpearl0.ctnhcore.mixin.alexcaves;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import com.github.alexmodguy.alexscaves.server.entity.util.MagnetUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MagnetUtil.class)
public class MagnetUtilMixin {

    @Inject(method = "tickMagnetism", at = @At("HEAD"), cancellable = true, remap = false)
    private static void fix1(Entity entity, CallbackInfo ci) {
        if (!(entity instanceof ServerPlayer)) ci.cancel();
    }
}
