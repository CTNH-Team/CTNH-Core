package io.github.cpearl0.ctnhcore.api.recipe.crossparalell;

import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.category.GTRecipeCategory;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

import java.util.*;

public class MergedGTRecipe extends GTRecipe {

    //public List<GTRecipe> recipes = new ArrayList<>();

    public MergedGTRecipe(GTRecipeType recipeType, GTRecipeCategory recipeCategory, ResourceLocation id){
        super(recipeType,
                id,
                new RecipeContentMap(),
                new RecipeContentMap(),
                new RecipeContentMap(),
                new RecipeContentMap(),
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
        ((RecipeContentMap)inputs).mergeFrom(recipe.inputs);
        ((RecipeContentMap)outputs).mergeFrom(recipe.outputs);
        ((RecipeContentMap)tickInputs).mergeFrom(recipe.tickInputs);
        ((RecipeContentMap)tickOutputs).mergeFrom(recipe.tickOutputs);

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

    public static class RecipeContentMap extends HashMap<RecipeCapability<?>, List<Content>> {

        public void mergeFrom(Map<RecipeCapability<?>, List<Content>> other) {
            if (other == null || other.isEmpty()) {
                return;
            }

            for (Map.Entry<RecipeCapability<?>, List<Content>> entry : other.entrySet()) {
                RecipeCapability<?> key = entry.getKey();
                List<Content> incoming = entry.getValue();

                if (incoming == null || incoming.isEmpty()) {
                    continue;
                }

                List<Content> existing = this.get(key);

                if (existing == null) {
                    this.put(key, new ArrayList<>(incoming));
                } else {
                    existing.addAll(incoming);
                }
            }
        }
    }

}
