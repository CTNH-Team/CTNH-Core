package io.github.cpearl0.ctnhcore.mixin.emi;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(targets = "dev.emi.emi.runtime.EmiReloadManager$ReloadWorker", remap = false)
public class EmiReloadManagerMixin {

    // TODO: CEI 多线程修复之后恢复
    // @Inject(method = "run",
    // at = @At(value = "FIELD",
    // target = "Ldev/emi/emi/runtime/EmiReloadManager;restart:Z",
    // ordinal = 4,
    // opcode = Opcodes.GETSTATIC,
    // shift = At.Shift.BEFORE))
    // void injectExtraPlugin(CallbackInfo ci, @Local(name = "registry") EmiRegistry registry) {
    // new CTNHExtraEmiPlugin().register(registry);
    // }
}
