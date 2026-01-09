package io.github.cpearl0.ctnhcore.mixin.gtceu;

import com.gregtechceu.gtceu.api.data.chemical.material.ItemMaterialData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ItemMaterialData.class, remap = false)
public class ItemMaterialDataMixin {
    @Unique
    private static boolean initialized = false;

    @Inject(method = "reinitializeMaterialData", at = @At("HEAD"), cancellable = true)
    private static void reinitializeMaterialData(CallbackInfo ci){
        if(initialized) ci.cancel();
        initialized = true;
    }
}
