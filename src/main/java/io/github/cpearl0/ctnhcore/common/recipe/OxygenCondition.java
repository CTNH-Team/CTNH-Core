package io.github.cpearl0.ctnhcore.common.recipe;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import com.ctnhlang.CN;
import com.ctnhlang.EN;

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

    @CN("此机器需要在可供氧环境中运行")
    @EN("This machine requires a breathable atmosphere")
    public static Lang ctnhMachineOxygenRequired;



    public static final Codec<OxygenCondition> CODEC = RecipeCondition.simpleCodec(OxygenCondition::new);
    private static final Component TOOLTIP = ctnhMachineOxygenRequired.translate();

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
