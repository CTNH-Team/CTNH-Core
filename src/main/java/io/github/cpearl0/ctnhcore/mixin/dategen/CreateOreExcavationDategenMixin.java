package io.github.cpearl0.ctnhcore.mixin.dategen;

import net.minecraftforge.data.event.GatherDataEvent;

import com.tom.createores.data.DataGenerators;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = DataGenerators.class, remap = false)
public class CreateOreExcavationDategenMixin {

    @Inject(method = "gatherData", at = @At("HEAD"), cancellable = true)
    private static void cancellDategen(GatherDataEvent event, CallbackInfo ci) {
        ci.cancel();
    }
}
