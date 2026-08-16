package io.github.cpearl0.ctnhcore.mixin.emi;

import net.minecraft.world.item.ItemStack;

import com.google.gson.JsonElement;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.stack.serializer.EmiIngredientSerializer;
import dev.emi.emi.jemi.JemiStack;
import dev.emi.emi.jemi.JemiStackSerializer;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.ITypedIngredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * TMRV uses {@link JemiStack} to preserve JEI custom renderers, including renderers for normal item stacks.
 * EMI's JEMI deserializer intentionally skips vanilla item types, so those stacks cannot survive the
 * serialize/deserialize round trip performed when an ingredient is added to favorites.
 */
@Mixin(value = JemiStackSerializer.class, remap = false)
public class JemiStackSerializerMixin {

    @Inject(method = "serialize(Ldev/emi/emi/jemi/JemiStack;)Lcom/google/gson/JsonElement;",
            at = @At("HEAD"),
            cancellable = true)
    private void ctnhcore$serializeCustomRenderedItemAsVanilla(JemiStack<?> stack,
                                                               CallbackInfoReturnable<JsonElement> cir) {
        if (stack instanceof ITypedIngredient<?> typedIngredient &&
                typedIngredient.getType() == VanillaTypes.ITEM_STACK &&
                typedIngredient.getIngredient() instanceof ItemStack itemStack) {
            cir.setReturnValue(EmiIngredientSerializer.getSerialized(EmiStack.of(itemStack)));
        }
    }
}
