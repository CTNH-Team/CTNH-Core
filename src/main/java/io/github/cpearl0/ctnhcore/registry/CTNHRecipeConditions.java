package io.github.cpearl0.ctnhcore.registry;

import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import io.github.cpearl0.ctnhcore.common.recipe.NeutronActivatorCondition;
import io.github.cpearl0.ctnhcore.common.recipe.PlantCasingCondition;
import io.github.cpearl0.ctnhcore.common.recipe.TierCasingCondition;

public class CTNHRecipeConditions {
    public static RecipeConditionType<PlantCasingCondition> PLANT_CASING = GTRegistries.RECIPE_CONDITIONS.register(
            "plant_casing_condition", new RecipeConditionType<>(PlantCasingCondition::new, PlantCasingCondition.CODEC));
    public static RecipeConditionType<NeutronActivatorCondition> NEUTRON_ACTIVATOR = GTRegistries.RECIPE_CONDITIONS.register(
            "neutron_activator_condition", new RecipeConditionType<>(NeutronActivatorCondition::new, NeutronActivatorCondition.CODEC));
//    public static RecipeConditionType<TierCasingCondition> TIER_CASING = GTRegistries.RECIPE_CONDITIONS.register(
//            "tier_casing_condition", new RecipeConditionType<>(TierCasingCondition::new, TierCasingCondition.CODEC));
    public static void init() {}
}
