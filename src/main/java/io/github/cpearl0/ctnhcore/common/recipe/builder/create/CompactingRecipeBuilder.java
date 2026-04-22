package io.github.cpearl0.ctnhcore.common.recipe.builder.create;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.cpearl0.ctnhcore.CTNHCore;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import javax.annotation.Nonnull;

public class CompactingRecipeBuilder {

    private final ResourceLocation id;
    private final List<Ingredient> ingredients = new ArrayList<>();
    private final List<ItemStack> results = new ArrayList<>();

    public CompactingRecipeBuilder(String name) {
        this.id = CTNHCore.id(name);
    }

    public static CompactingRecipeBuilder builder(String name) {
        return new CompactingRecipeBuilder(name);
    }

    public CompactingRecipeBuilder input(ItemStack stack) {
        return input(Ingredient.of(stack));
    }

    public CompactingRecipeBuilder input(Item item) {
        return input(Ingredient.of(new ItemStack(item, 1)));
    }

    public CompactingRecipeBuilder input(TagKey<Item> tag) {
        return input(Ingredient.of(tag));
    }

    public CompactingRecipeBuilder input(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        return this;
    }

    public CompactingRecipeBuilder result(ItemStack stack) {
        this.results.add(stack.copy());
        return this;
    }

    public CompactingRecipeBuilder output(ItemStack stack) {
        return result(stack);
    }

    public void toJson(JsonObject json) {
        if (ingredients.isEmpty() || results.isEmpty()) {
            throw new IllegalStateException("Compacting recipe missing required fields");
        }

        json.addProperty("type", "create:compacting");

        JsonArray ingredientsJson = new JsonArray();
        ingredients.forEach(ing -> ingredientsJson.add(ing.toJson()));
        json.add("ingredients", ingredientsJson);

        JsonArray resultsJson = new JsonArray();
        results.forEach(stack -> resultsJson.add(serializeItemStack(stack)));
        json.add("results", resultsJson);
    }

    public FinishedRecipe build() {
        return new FinishedRecipe() {
            @Override
            public void serializeRecipeData(@Nonnull JsonObject pJson) {
                CompactingRecipeBuilder.this.toJson(pJson);
            }

            @Nonnull
            @Override
            public ResourceLocation getId() {
                return ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "compacting/" + id.getPath());
            }

            @Nonnull
            @Override
            public RecipeSerializer<?> getType() {
                return Objects.requireNonNull(ForgeRegistries.RECIPE_SERIALIZERS.getValue(
                        ResourceLocation.tryParse("create:compacting")), "Create compacting serializer not found");
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
        };
    }

    public void save(Consumer<FinishedRecipe> consumer) {
        consumer.accept(build());
    }

    private static JsonObject serializeItemStack(ItemStack stack) {
        JsonObject json = new JsonObject();
        json.addProperty("item", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(stack.getItem())).toString());
        if (stack.getCount() != 1) json.addProperty("count", stack.getCount());
        if (stack.hasTag()) json.addProperty("nbt", String.valueOf(stack.getTag()));
        return json;
    }
}

