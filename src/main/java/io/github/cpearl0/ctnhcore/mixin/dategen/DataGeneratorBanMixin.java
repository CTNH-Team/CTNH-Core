package io.github.cpearl0.ctnhcore.mixin.dategen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = DataGenerator.class, remap = false)
public class DataGeneratorBanMixin {

    private static final String BANNED_MODID = "createcafe";

    @Inject(method = "addProvider(ZLnet/minecraft/data/DataProvider;)Lnet/minecraft/data/DataProvider;",
            at = @At("HEAD"),
            cancellable = true)
    private void skipBannedMods(boolean run, DataProvider provider, CallbackInfoReturnable<DataProvider> cir) {
        String name = provider.getName();
        if (name != null && name.contains(BANNED_MODID)) {
            cir.cancel();
        }
    }
}
