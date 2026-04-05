package io.github.cpearl0.ctnhcore.common.recipe;

import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeCondition;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.CN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.EN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.Prefix;

@Prefix("recipe.condition.tier_casing")
public class TierCasingCondition extends RecipeCondition<TierCasingCondition> {

    @Override
    public RecipeConditionType<TierCasingCondition> getType() {
        return null;
    }

    @CN("外壳等级：%s")
    @EN("Casing Tier: %s")
    static Lang tooltip;

    @Override
    public Component getTooltips() {
        return tooltip.translate();
    }

    @Override
    protected boolean testCondition(@NotNull GTRecipe gtRecipe, @NotNull RecipeLogic recipeLogic) {
        return false;
    }

    @Override
    public TierCasingCondition createTemplate() {
        return null;
    }
}
