package io.github.cpearl0.ctnhcore.common.recipe.builder;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

import com.mo_guang.ctpp.api.StressRecipeCapability;
import com.mo_guang.ctpp.common.condition.MechanicalTierCondition;
import com.mo_guang.ctpp.common.condition.RPMCondition;
import com.moguang.ctnhbio.api.capability.recipe.EntityRecipeCapability;
import com.moguang.ctnhbio.api.capability.recipe.NutrientRecipeCapability;
import com.moguang.ctnhbio.api.recipe.ingredient.entity.EntityIngredient;

public class CTNHRecipeBuilder extends GTRecipeBuilder {

    public CTNHRecipeBuilder(ResourceLocation id, GTRecipeType recipeType) {
        super(id, recipeType);
    }

    public static CTNHRecipeBuilder of(ResourceLocation id, GTRecipeType recipeType) {
        return new CTNHRecipeBuilder(id, recipeType);
    }

    public static CTNHRecipeBuilder of(String path, GTRecipeType recipeType) {
        return of(CTNHCore.id(path), recipeType);
    }

    public CTNHRecipeBuilder inputStress(float stress) {
        input(StressRecipeCapability.CAP, stress);
        return this;
    }

    public CTNHRecipeBuilder outputStress(float stress) {
        output(StressRecipeCapability.CAP, stress);
        return this;
    }

    public CTNHRecipeBuilder rpm(float rpm, boolean reverse) {
        addCondition(new RPMCondition(rpm).setReverse(reverse));
        return this;
    }

    public CTNHRecipeBuilder rpm(float rpm) {
        return rpm(rpm, false);
    }

    public CTNHRecipeBuilder tier(int tier) {
        addCondition(new MechanicalTierCondition(tier));
        // this.data.putInt("mechanical_tier", tier);
        return this;
    }

    public CTNHRecipeBuilder nutrient(double nutrient) {
        if (nutrient >= 0) {
            input(NutrientRecipeCapability.CAP, nutrient);
        } else {
            output(NutrientRecipeCapability.CAP, -nutrient);
        }
        return this;
    }

    /* Entity Recipe */
    // basics
    public CTNHRecipeBuilder inputEntity(EntityIngredient entity) {
        perTick = false;
        input(EntityRecipeCapability.CAP, entity);
        return this;
    }

    public CTNHRecipeBuilder inputEntity(EntityIngredient entity, int chance) {
        perTick = false;
        int lastChance = this.chance;
        this.chance = chance;
        input(EntityRecipeCapability.CAP, entity);
        this.chance = lastChance;
        return this;
    }

    public CTNHRecipeBuilder outputEntity(EntityIngredient entity) {
        perTick = false;
        output(EntityRecipeCapability.CAP, entity);
        return this;
    }

    // forward EntityIngredient constructors
    public CTNHRecipeBuilder inputEntity(EntityType<?> type) {
        return inputEntity(EntityIngredient.of(type));
    }

    public CTNHRecipeBuilder inputEntity(EntityType<?> type, int count, int chance) {
        return inputEntity(EntityIngredient.of(type, count), chance);
    }

    public CTNHRecipeBuilder inputEntity(TagKey<EntityType<?>> tag) {
        return inputEntity(EntityIngredient.of(tag));
    }

    public CTNHRecipeBuilder inputEntity(String id) {
        return inputEntity(EntityIngredient.of(id));
    }

    public CTNHRecipeBuilder outputEntity(EntityType<?> type) {
        return outputEntity(EntityIngredient.of(type));
    }

    public CTNHRecipeBuilder outputEntity(TagKey<EntityType<?>> tag) {
        return outputEntity(EntityIngredient.of(tag));
    }

    public CTNHRecipeBuilder outputEntity(String id) {
        return outputEntity(EntityIngredient.of(id));
    }
}
