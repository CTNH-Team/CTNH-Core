package io.github.cpearl0.ctnhcore.mixin.gtceu.recipe;

import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.List;

@Mixin(value = RecipeLogic.class, remap = false)
public class RecipeLogicMixin {
//    @Unique
//    public final Collection<GTRecipe> ctnh$cachedRecipes = new ArrayDeque<>(36);

}
