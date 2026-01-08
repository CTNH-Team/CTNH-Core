package io.github.cpearl0.ctnhcore.mixin.gtceu.recipe;

import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = RecipeLogic.class, remap = false)
public class RecipeLogicMixin {
    // @Unique
    // public final Collection<GTRecipe> ctnh$cachedRecipes = new ArrayDeque<>(36);

}
