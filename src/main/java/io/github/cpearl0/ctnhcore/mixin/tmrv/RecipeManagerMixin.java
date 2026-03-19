package io.github.cpearl0.ctnhcore.mixin.tmrv;

import com.llamalad7.mixinextras.sugar.Local;
import dev.nolij.toomanyrecipeviewers.impl.jei.api.recipe.RecipeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Set;

/**
 * 修复 https://github.com/Nolij/TooManyRecipeViewers/issues/24 鼓风机只显示非原版配方的问题
 */
@Mixin(value = RecipeManager.class, remap = false)
public class RecipeManagerMixin {

    @Redirect(
              method = "addRecipe",
              at = @At(value = "INVOKE", target = "Ljava/util/Set;contains(Ljava/lang/Object;)Z"),
              remap = false)
    private <T> boolean ctnhcore$fixIgnoredRecipesCheck(
                                                        Set<?> set,
                                                        Object recipe,
                                                        @Local(argsOnly = true,
                                                               ordinal = 0) RecipeManager.Category<T> category) {
        var jeiCategory = category.getJEICategory();
        if (jeiCategory == null) return false;
        var jeiRecipeType = jeiCategory.getRecipeType();
        if (!RecipeManager.vanillaJEITypeEMICategoryMap.containsKey(jeiRecipeType)) return false;
        return set.contains(recipe);
    }
}
