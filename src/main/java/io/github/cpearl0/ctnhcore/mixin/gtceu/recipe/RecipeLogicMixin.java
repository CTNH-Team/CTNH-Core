package io.github.cpearl0.ctnhcore.mixin.gtceu.recipe;

import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.ActionResult;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = RecipeLogic.class, remap = false)
public class RecipeLogicMixin {

    @Redirect(method = "handleRecipeWorking",
              at = @At(value = "INVOKE",
                       target = "Lcom/gregtechceu/gtceu/api/recipe/ActionResult;capability()Lcom/gregtechceu/gtceu/api/capability/recipe/RecipeCapability;"))
    @Nullable
    RecipeCapability<?> noPowerFail(ActionResult instance) {
        return null;
    }
}
