package io.github.cpearl0.ctnhcore.api.recipe.crossparalell;

import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.RecipeCondition;
import com.gregtechceu.gtceu.api.recipe.category.GTRecipeCategory;
import com.gregtechceu.gtceu.api.recipe.chance.logic.ChanceLogic;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class MergedGTRecipe extends GTRecipe {

    //public List<GTRecipe> recipes = new ArrayList<>();

    public MergedGTRecipe(GTRecipeType recipeType, GTRecipeCategory recipeCategory){
        super(recipeType,
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new CompoundTag(),
                1,
                recipeCategory);

        parallels = 0;
    }

    public void add(GTRecipe recipe){
        recipe.inputs.forEach((key, value) ->
                inputs.merge(key, value, (oldList, newList) -> {
                    oldList.addAll(newList);
                    return oldList;
                })
        );

        recipe.outputs.forEach((key, value) ->
                outputs.merge(key, value, (oldList, newList) -> {
                    oldList.addAll(newList);
                    return oldList;
                })
        );

        recipe.tickInputs.forEach((key, value) ->
                tickInputs.merge(key, value, (oldList, newList) -> {
                    oldList.addAll(newList);
                    return oldList;
                })
        );

        recipe.tickOutputs.forEach((key, value) ->
                tickOutputs.merge(key, value, (oldList, newList) -> {
                    oldList.addAll(newList);
                    return oldList;
                })
        );

        inputChanceLogics.putAll(recipe.inputChanceLogics);
        outputChanceLogics.putAll(recipe.outputChanceLogics);
        tickInputChanceLogics.putAll(recipe.tickInputChanceLogics);
        tickOutputChanceLogics.putAll(recipe.tickOutputChanceLogics);

        conditions.addAll(recipe.conditions);
        data.merge(recipe.data);

        duration = Math.max(duration, recipe.duration);
        parallels += recipe.parallels;
    }

    public void clear(){
        inputs.clear();
        outputs.clear();
        tickInputs.clear();
        tickOutputs.clear();

        inputChanceLogics.clear();
        outputChanceLogics.clear();
        tickInputChanceLogics.clear();
        tickOutputChanceLogics.clear();

        conditions.clear();
        data = new CompoundTag();
        duration = 1;
        parallels = 0;
    }

    public boolean isAvailable(){
        return parallels != 0;
    }

    public static Map<RecipeCapability<?>, List<Content>> merge(
            Map<RecipeCapability<?>, List<Content>> map1,
            Map<RecipeCapability<?>, List<Content>> map2) {

        Map<RecipeCapability<?>, List<Content>> result = new HashMap<>(map1);

        map2.forEach((key, value) ->
                result.merge(key, value, (oldList, newList) -> {
                    oldList.addAll(newList);
                    return oldList;
                })
        );

        return result;
    }

}
