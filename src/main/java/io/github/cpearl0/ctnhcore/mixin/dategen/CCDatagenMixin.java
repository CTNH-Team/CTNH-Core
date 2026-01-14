package io.github.cpearl0.ctnhcore.mixin.dategen;

import com.hlysine.create_connected.datagen.CCDatagen;
import net.minecraftforge.data.event.GatherDataEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = CCDatagen.class, remap = false)
public class CCDatagenMixin {
    @Inject(method = "gatherData", at = @At("HEAD"), cancellable = true)
    private static void cancellDategen(GatherDataEvent event, CallbackInfo ci){
        ci.cancel();
    }
}
