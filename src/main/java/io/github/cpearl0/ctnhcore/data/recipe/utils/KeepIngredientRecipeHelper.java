package io.github.cpearl0.ctnhcore.data.recipe.utils;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipes;

import com.lowdragmc.lowdraglib.utils.NBTToJsonConverter;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Objects;
import java.util.function.Consumer;

public final class KeepIngredientRecipeHelper {

    private KeepIngredientRecipeHelper() {}

    public static void addKeepIngredientShapedRecipe(Consumer<FinishedRecipe> provider, ResourceLocation id,
                                                     ItemStack result, String[] pattern, Ingredient keepIngredient,
                                                     Object... key) {
        provider.accept(new KeepIngredientFinishedRecipe(id, result, pattern, keepIngredient, key));
    }

    public static void addKeepIngredientShapedRecipe(Consumer<FinishedRecipe> provider, ResourceLocation id,
                                                     ItemStack result, String[] pattern,
                                                     Ingredient[] keepIngredients,
                                                     Object... key) {
        provider.accept(new KeepIngredientFinishedRecipe(id, result, pattern, keepIngredients, key));
    }

    private record KeepIngredientFinishedRecipe(ResourceLocation id, ItemStack result, String[] pattern,
                                                Object keepIngredient, Object[] key)
            implements FinishedRecipe {

        @Override
        public void serializeRecipeData(JsonObject json) {
            json.addProperty("type", CTNHCore.id("keep_ingredient_shaped").toString());

            JsonArray patternJson = new JsonArray();
            for (String row : pattern) {
                patternJson.add(row);
            }
            json.add("pattern", patternJson);

            JsonObject keyJson = new JsonObject();
            for (int i = 0; i < key.length; i += 2) {
                keyJson.add(String.valueOf(key[i]), ingredientJson(key[i + 1]));
            }
            json.add("key", keyJson);

            json.add("result", resultJson(result));
            json.add("keepIngredient", keepIngredientJson(keepIngredient));
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return CTNHRecipes.KEEP_INGREDIENT_SHAPED_SERIALIZER.get();
        }

        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }

    private static JsonObject resultJson(ItemStack stack) {
        JsonObject result = new JsonObject();
        result.addProperty("item",
                Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(stack.getItem())).toString());
        if (stack.getCount() > 1) {
            result.addProperty("count", stack.getCount());
        }
        if (stack.hasTag() && stack.getTag() != null) {
            result.add("nbt", NBTToJsonConverter.getObject(stack.getTag()));
        }
        return result;
    }

    private static JsonObject ingredientJson(Object ingredient) {
        if (ingredient instanceof Ingredient directIngredient) {
            return directIngredient.toJson().getAsJsonObject();
        }
        if (ingredient instanceof TagKey<?> key) {
            return Ingredient.of((TagKey<Item>) key).toJson().getAsJsonObject();
        }
        if (ingredient instanceof ItemStack stack) {
            return Ingredient.of(stack).toJson().getAsJsonObject();
        }
        if (ingredient instanceof ItemLike itemLike) {
            return Ingredient.of(itemLike).toJson().getAsJsonObject();
        }
        throw new IllegalArgumentException("Unsupported keep recipe ingredient: " + ingredient);
    }

    private static com.google.gson.JsonElement keepIngredientJson(Object keepIngredient) {
        if (keepIngredient instanceof Ingredient[] ingredients) {
            JsonArray keepJson = new JsonArray();
            for (Ingredient ingredient : ingredients) {
                keepJson.add(ingredient.toJson());
            }
            return keepJson;
        }
        return ingredientJson(keepIngredient);
    }
}
