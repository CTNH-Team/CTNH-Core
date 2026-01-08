package io.github.cpearl0.ctnhcore.mixin.emi;

import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.search.EmiSearch;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = EmiSearch.class, remap = false)
public class EmiSearchMixin {
    @Unique
    private static boolean baked = false;

    @Inject(method = "bake", at = @At("HEAD"), remap = false, cancellable = true)
    private static void bakeOneTime(CallbackInfo ci){
        if(baked) ci.cancel();
        baked = true;
    }

    @Redirect(method = "bake", at = @At(value = "INVOKE", target = "Ldev/emi/emi/api/stack/EmiStack;getTooltipText()Ljava/util/List;"))
    private static List<Component> noTooltip(EmiStack instance){
        return null;
    }
}
