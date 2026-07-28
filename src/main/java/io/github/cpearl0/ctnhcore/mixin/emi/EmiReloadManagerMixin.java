package io.github.cpearl0.ctnhcore.mixin.emi;

import io.github.cpearl0.ctnhcore.integration.emi.CTNHExtraEmiPlugin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.emi.emi.api.EmiRegistry;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "dev.emi.emi.runtime.EmiReloadManager$ReloadWorker", remap = false)
public class EmiReloadManagerMixin {

    @Inject(method = "run",
            at = @At(value = "FIELD",
                     target = "Ldev/emi/emi/runtime/EmiReloadManager;restart:Z",
                     ordinal = 4,
                     opcode = Opcodes.GETSTATIC,
                     shift = At.Shift.BEFORE))
    void injectExtraPlugin(CallbackInfo ci, @Local(name = "registry") EmiRegistry registry) {
        new CTNHExtraEmiPlugin().register(registry);
    }
}
