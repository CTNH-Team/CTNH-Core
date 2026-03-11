package io.github.cpearl0.ctnhcore.mixin.emi;

import com.llamalad7.mixinextras.sugar.Local;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.stack.ListEmiIngredient;
import dev.emi.emi.registry.EmiTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;

@Mixin(value = EmiTags.class, remap = false)
public class EmiTagsMixin {

    @Inject(method = "getIngredient",
            at = @At(value = "INVOKE", target = "Ljava/util/Map;size()I", ordinal = 1),
            cancellable = true)
    private static void checkNbt(Class<?> clazz, List<EmiStack> stacks, long amount,
                                 CallbackInfoReturnable<EmiIngredient> cir, @Local(name = "map") Map<?, EmiStack> map) {
        if (map.size() > 1 && map.values().stream().anyMatch(s -> s.getNbt() != null)) {
            cir.setReturnValue(new ListEmiIngredient(map.values().stream().toList(), amount));
        }
    }
}
