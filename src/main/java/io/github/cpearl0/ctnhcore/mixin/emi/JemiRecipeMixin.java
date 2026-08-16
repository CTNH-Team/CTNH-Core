package io.github.cpearl0.ctnhcore.mixin.emi;

import io.github.cpearl0.ctnhcore.integration.creatediesel.DistillationCategoryLayout;

import com.jesz.createdieselgenerators.compat.jei.DistillationCategory;
import com.jesz.createdieselgenerators.content.distillation.DistillationRecipe;
import dev.emi.emi.jemi.JemiRecipe;
import mezz.jei.api.recipe.category.IRecipeCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = JemiRecipe.class, remap = false)
public class JemiRecipeMixin {

    @Shadow
    public IRecipeCategory<?> category;

    @Shadow
    public Object recipe;

    @Inject(method = "getDisplayHeight", at = @At("RETURN"), cancellable = true)
    private void ctnhcore$useDynamicDistillationHeight(CallbackInfoReturnable<Integer> cir) {
        if (category instanceof DistillationCategory && recipe instanceof DistillationRecipe distillationRecipe) {
            cir.setReturnValue(DistillationCategoryLayout.getDisplayHeight(distillationRecipe, cir.getReturnValue()));
        }
    }
}
