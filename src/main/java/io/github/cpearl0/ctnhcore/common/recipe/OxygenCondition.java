package io.github.cpearl0.ctnhcore.common.recipe;

import io.github.cpearl0.ctnhcore.common.oxygen.OxygenMachineRules;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeConditions;

import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeCondition;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;

import net.minecraft.network.chat.Component;

import com.mojang.serialization.Codec;
import org.jetbrains.annotations.NotNull;

public class OxygenCondition extends RecipeCondition<OxygenCondition> {

    public static final Codec<OxygenCondition> CODEC = RecipeCondition.simpleCodec(OxygenCondition::new);
    private static final Component TOOLTIP = Component.translatable("ctnhcore.machine.oxygen_required");

    public OxygenCondition() {}

    public OxygenCondition(boolean isReverse) {
        super(isReverse);
    }

    @Override
    public RecipeConditionType<OxygenCondition> getType() {
        return CTNHRecipeConditions.OXYGEN;
    }

    @Override
    public Component getTooltips() {
        return TOOLTIP;
    }

    @Override
    protected boolean testCondition(@NotNull GTRecipe recipe, @NotNull RecipeLogic recipeLogic) {
        return OxygenMachineRules.hasRequiredAtmosphere(recipeLogic.machine.self());
    }

    @Override
    public OxygenCondition createTemplate() {
        return new OxygenCondition();
    }
}
