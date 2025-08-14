package io.github.cpearl0.ctnhcore.common.recipe;

import com.google.gson.JsonObject;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeCondition;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.ChemicalPlantMachine;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeConditions;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.util.GsonHelper;
import org.jetbrains.annotations.NotNull;
import org.joml.Math;

import java.util.Map;

public class PlantCasingCondition extends RecipeCondition {
    public static final Codec<PlantCasingCondition> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.BOOL.optionalFieldOf("isReverse", false).forGetter(RecipeCondition::isReverse),
                    Codec.INT.fieldOf("plantCasing").forGetter(cond -> cond.tier)
            ).apply(instance, PlantCasingCondition::new)
    );
    public static Map<Integer, String> CASING_TIERS = Map.of(
            1, "ctnh.recipe.condition.plant_casing.tier.bronze",
            2, "ctnh.recipe.condition.plant_casing.tier.steel",
            3, "ctnh.recipe.condition.plant_casing.tier.aluminium",
            4, "ctnh.recipe.condition.plant_casing.tier.stainless_steel",
            5, "ctnh.recipe.condition.plant_casing.tier.titanium",
            6, "ctnh.recipe.condition.plant_casing.tier.tungsten_steel"
            );
    private int tier;
    public PlantCasingCondition() {}

    public PlantCasingCondition(int tier) {
        this.tier = Math.clamp(tier, 1, 6);
    }

    public PlantCasingCondition(boolean isReverse, int tier) {
        super(isReverse);
        this.tier = Math.clamp(tier, 1, 6);
    }
    @Override
    public RecipeConditionType<PlantCasingCondition> getType() {
        return CTNHRecipeConditions.PLANT_CASING;
    }

    @Override
    public Component getTooltips() {
        return Component.translatable(
                "ctnh.recipe.condition.plant_casing.tooltip",
                tier, Component.translatable(CASING_TIERS.get(tier))
        );
    }

    @Override
    protected boolean testCondition(@NotNull GTRecipe gtRecipe, @NotNull RecipeLogic recipeLogic) {
        var machine = recipeLogic.machine;
        if (machine instanceof ChemicalPlantMachine chemicalPlantMachine) {
            return chemicalPlantMachine.casingTier >= tier;
        }
        return false;
    }

    @Override
    public RecipeCondition createTemplate() {
        return new PlantCasingCondition();
    }
    @Override
    public @NotNull JsonObject serialize() {
        var value = super.serialize();
        value.addProperty("plantCasing", tier);
        return value;
    }
    @Override
    public RecipeCondition deserialize(@NotNull JsonObject config) {
        super.deserialize(config);
        this.tier = GsonHelper.getAsInt(config, "plantCasing", 0);
        return this;
    }
    @Override
    public void toNetwork(FriendlyByteBuf buf) {
        super.toNetwork(buf);
        buf.writeInt(tier);
    }
    @Override
    public RecipeCondition fromNetwork(FriendlyByteBuf buf) {
        super.fromNetwork(buf);
        this.tier = buf.readInt();
        return this;
    }
}
