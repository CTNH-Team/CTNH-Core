package io.github.cpearl0.ctnhcore.data.recipe.create;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;

public final class CreateRecipeJsonHelper {

    private CreateRecipeJsonHelper() {}

    public static void save(Consumer<FinishedRecipe> provider, String id, JsonObject json) {
        ResourceLocation recipeId = Objects.requireNonNull(ResourceLocation.tryParse(id), "Invalid recipe id: " + id);
        String type = Objects.requireNonNull(json.get("type"), "Recipe type missing: " + id).getAsString();
        provider.accept(new FinishedRecipe() {

            @Override
            public void serializeRecipeData(JsonObject recipeJson) {
                json.entrySet().forEach(entry -> recipeJson.add(entry.getKey(), entry.getValue()));
            }

            @Override
            public ResourceLocation getId() {
                return recipeId;
            }

            @Override
            public RecipeSerializer<?> getType() {
                RecipeSerializer<?> serializer = ForgeRegistries.RECIPE_SERIALIZERS
                        .getValue(ResourceLocation.parse(type));
                return Objects.requireNonNull(serializer, "Recipe serializer not found: " + type);
            }

            @Nullable
            @Override
            public JsonObject serializeAdvancement() {
                return null;
            }

            @Nullable
            @Override
            public ResourceLocation getAdvancementId() {
                return null;
            }
        });
    }

    public static JsonObject recipe(String type) {
        JsonObject json = new JsonObject();
        json.addProperty("type", type);
        return json;
    }

    public static JsonArray array(JsonObject... values) {
        JsonArray array = new JsonArray();
        for (JsonObject value : values) {
            array.add(value);
        }
        return array;
    }

    public static JsonObject item(String id) {
        JsonObject json = new JsonObject();
        json.addProperty("item", id);
        return json;
    }

    static JsonObject item(String id, int count) {
        JsonObject json = item(id);
        if (count != 1) json.addProperty("count", count);
        return json;
    }

    public static JsonObject tag(String id) {
        JsonObject json = new JsonObject();
        json.addProperty("tag", id);
        return json;
    }

    static JsonObject fluid(String id, int amount) {
        JsonObject json = new JsonObject();
        json.addProperty("fluid", id);
        json.addProperty("amount", amount);
        return json;
    }

    static JsonObject chanceItem(String id, double chance) {
        JsonObject json = item(id);
        json.addProperty("chance", chance);
        return json;
    }

    static JsonObject chanceItem(String id, int count, double chance) {
        JsonObject json = item(id, count);
        json.addProperty("chance", chance);
        return json;
    }
}
