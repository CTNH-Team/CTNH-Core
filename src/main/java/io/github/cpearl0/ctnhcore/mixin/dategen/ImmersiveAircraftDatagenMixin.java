package io.github.cpearl0.ctnhcore.mixin.dategen;

import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

import immersive_aircraft.forge.ClientForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ClientForge.class, remap = false)
public class ImmersiveAircraftDatagenMixin {

    @Inject(method = "data", at = @At("HEAD"), cancellable = true)
    private static void cancelDatagen(FMLConstructModEvent event, CallbackInfo ci) {
        ci.cancel();
    }
}
