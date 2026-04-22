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

public class MechanicalCraftingRecipeBuilder {

    private final ResourceLocation id;
    private final List<Ingredient> ingredients = new ArrayList<>();
    private final List<ItemStack> results = new ArrayList<>();

    public MechanicalCraftingRecipeBuilder(String name) {
        this.id = CTNHCore.id(name);
    }

    public static MechanicalCraftingRecipeBuilder builder(String name) {
        return new MechanicalCraftingRecipeBuilder(name);
    }

    public MechanicalCraftingRecipeBuilder input(ItemStack stack) {
        return input(Ingredient.of(stack));
    }

    public MechanicalCraftingRecipeBuilder input(Item item) {
        return input(Ingredient.of(new ItemStack(item, 1)));
    }

    public MechanicalCraftingRecipeBuilder input(TagKey<Item> tag) {
        return input(Ingredient.of(tag));
    }

    public MechanicalCraftingRecipeBuilder input(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        return this;
    }

    public MechanicalCraftingRecipeBuilder result(ItemStack stack) {
        this.results.add(stack.copy());
        return this;
    }

    public MechanicalCraftingRecipeBuilder output(ItemStack stack) {
        return result(stack);
    }

    public void toJson(JsonObject json) {
        if (ingredients.isEmpty() || results.isEmpty()) {
            throw new IllegalStateException("Mechanical crafting recipe missing required fields");
        }

        json.addProperty("type", "create:mechanical_crafting");

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
                MechanicalCraftingRecipeBuilder.this.toJson(pJson);
            }

            @Nonnull
            @Override
            public ResourceLocation getId() {
                return ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "mechanical_crafting/" + id.getPath());
            }

            @Nonnull
            @Override
            public RecipeSerializer<?> getType() {
                return Objects.requireNonNull(ForgeRegistries.RECIPE_SERIALIZERS.getValue(
                        ResourceLocation.tryParse("create:mechanical_crafting")), "Create mechanical_crafting serializer not found");
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

