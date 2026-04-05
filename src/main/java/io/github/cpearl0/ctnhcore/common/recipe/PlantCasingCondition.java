package io.github.cpearl0.ctnhcore.common.recipe;

import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeCondition;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.ChemicalPlantMachine;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeConditions;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.joml.Math;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.CN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.EN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.Prefix;

import java.util.Map;

@Prefix("recipe.condition.plant_casing")
public class PlantCasingCondition extends RecipeCondition<PlantCasingCondition> {

    public static final Codec<PlantCasingCondition> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.optionalFieldOf("isReverse", false).forGetter(RecipeCondition::isReverse),
            Codec.INT.fieldOf("plantCasing").forGetter(cond -> cond.tier)).apply(instance, PlantCasingCondition::new));
    @CN("青铜")
    @EN("Bronze")
    static Lang bronze;
    @CN("钢")
    @EN("Steel")
    static Lang steel;
    @CN("铝")
    @EN("Aluminium")
    static Lang aluminium;
    @CN("不锈钢")
    @EN("Stainless Steel")
    static Lang stainless_steel;
    @CN("钛")
    @EN("Titanium")
    static Lang titanium;
    @CN("钨钢")
    @EN("Tungsten Steel")
    static Lang tungsten_steel;

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

    @CN("外壳等级: %s (%s)")
    @EN("Casing: %s (%s)")
    static Lang tooltip;

    @Override
    public Component getTooltips() {
        Map<Integer, Lang> CASING_TIERS = Map.of(
                1, bronze,
                2, steel,
                3, aluminium,
                4, stainless_steel,
                5, titanium,
                6, tungsten_steel);
        return tooltip.translate(tier, CASING_TIERS.get(tier).translate());
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
    public PlantCasingCondition createTemplate() {
        return new PlantCasingCondition();
    }
}
