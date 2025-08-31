package io.github.cpearl0.ctnhcore.common.recipe;

import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeCondition;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeConditions;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class TierCasingCondition extends RecipeCondition {
    @Override
    public RecipeConditionType<TierCasingCondition> getType() {
        return null;
    }

    @Override
    public Component getTooltips() {
        return null;
    }

    @Override
    protected boolean testCondition(@NotNull GTRecipe gtRecipe, @NotNull RecipeLogic recipeLogic) {
        return false;
    }

    @Override
    public RecipeCondition createTemplate() {
        return null;
    }
}
