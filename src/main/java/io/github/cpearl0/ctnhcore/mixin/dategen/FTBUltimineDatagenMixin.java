package io.github.cpearl0.ctnhcore.mixin.dategen;

import dev.ftb.mods.ftbultimine.client.FTBUltimineClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = FTBUltimineClient.class, remap = false)
public class FTBUltimineDatagenMixin {

    @Inject(method = "<init>", at = @At("TAIL"), cancellable = true)
    private void cancelDatagen(CallbackInfo ci) {
        ci.cancel();
    }
}
