package io.github.cpearl0.ctnhcore.common.recipe;

import io.github.cpearl0.ctnhcore.registry.CTNHRecipes;

import com.gregtechceu.gtceu.core.mixins.ShapedRecipeAccessor;

import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;

import com.google.gson.JsonObject;

import java.util.Map;

public class KeepIngredientShapedRecipe extends ShapedRecipe {

    public static final RecipeSerializer<KeepIngredientShapedRecipe> SERIALIZER = new Serializer();

    private final Ingredient keepIngredient;

    public KeepIngredientShapedRecipe(ResourceLocation id, String group, int width, int height,
                                      NonNullList<Ingredient> recipeItems, ItemStack result,
                                      Ingredient keepIngredient) {
        super(id, group, CraftingBookCategory.MISC, width, height, recipeItems, result);
        this.keepIngredient = keepIngredient;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingContainer container) {
        NonNullList<ItemStack> remainingItems = super.getRemainingItems(container);
        for (int i = 0; i < remainingItems.size(); i++) {
            ItemStack stack = container.getItem(i);
            if (!stack.isEmpty() && keepIngredient.test(stack)) {
                ItemStack keptStack = stack.copy();
                keptStack.setCount(1);
                remainingItems.set(i, keptStack);
            }
        }
        return remainingItems;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return CTNHRecipes.KEEP_INGREDIENT_SHAPED_SERIALIZER.get();
    }

    public static class Serializer implements RecipeSerializer<KeepIngredientShapedRecipe> {

        @Override
        public KeepIngredientShapedRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            String group = GsonHelper.getAsString(json, "group", "");
            Map<String, Ingredient> key = ShapedRecipeAccessor.callKeyFromJson(GsonHelper.getAsJsonObject(json, "key"));
            String[] pattern = ShapedRecipeAccessor.callPatternFromJson(GsonHelper.getAsJsonArray(json, "pattern"));
            int width = pattern[0].length();
            int height = pattern.length;
            NonNullList<Ingredient> ingredients = ShapedRecipeAccessor.callDissolvePattern(pattern, key, width, height);
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            Ingredient keepIngredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "keepIngredient"));
            return new KeepIngredientShapedRecipe(recipeId, group, width, height, ingredients, result, keepIngredient);
        }

        @Override
        public KeepIngredientShapedRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            int width = buffer.readVarInt();
            int height = buffer.readVarInt();
            String group = buffer.readUtf();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(width * height, Ingredient.EMPTY);
            ingredients.replaceAll($ -> Ingredient.fromNetwork(buffer));
            ItemStack result = buffer.readItem();
            Ingredient keepIngredient = Ingredient.fromNetwork(buffer);
            return new KeepIngredientShapedRecipe(recipeId, group, width, height, ingredients, result, keepIngredient);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, KeepIngredientShapedRecipe recipe) {
            buffer.writeVarInt(recipe.getWidth());
            buffer.writeVarInt(recipe.getHeight());
            buffer.writeUtf(recipe.getGroup());
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }
            buffer.writeItem(((ShapedRecipeAccessor) recipe).getResult());
            recipe.keepIngredient.toNetwork(buffer);
        }
    }
}
