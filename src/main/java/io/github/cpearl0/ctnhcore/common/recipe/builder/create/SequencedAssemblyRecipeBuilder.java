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
import com.simibubi.create.AllRecipeTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import javax.annotation.Nonnull;

public class SequencedAssemblyRecipeBuilder {

	private Ingredient ingredient;
	private ItemStack transitionalItem;
	private final List<ItemStack> results = new ArrayList<>();
	private final List<JsonObject> sequence = new ArrayList<>();
	private final ResourceLocation id;
	private int loops = 1;

	public SequencedAssemblyRecipeBuilder(String name) {
		this.id = CTNHCore.id(name);
	}

	public static SequencedAssemblyRecipeBuilder builder(String name) {
		return new SequencedAssemblyRecipeBuilder(name);
	}

	public SequencedAssemblyRecipeBuilder input(ItemStack itemStack) {
		return ingredient(Ingredient.of(itemStack));
	}

	public SequencedAssemblyRecipeBuilder input(Item item) {
		return ingredient(Ingredient.of(new ItemStack(item, 1)));
	}

	public SequencedAssemblyRecipeBuilder input(TagKey<Item> tagKey) {
		return ingredient(Ingredient.of(tagKey));
	}

	public SequencedAssemblyRecipeBuilder input(Ingredient ingredient) {
		return ingredient(ingredient);
	}

	public SequencedAssemblyRecipeBuilder ingredient(Ingredient ingredient) {
		this.ingredient = ingredient;
		return this;
	}

	public SequencedAssemblyRecipeBuilder transitional(ItemStack itemStack) {
		this.transitionalItem = itemStack.copy();
		return this;
	}

	public SequencedAssemblyRecipeBuilder transitional(Item item) {
		return transitional(new ItemStack(item, 1));
	}

	public SequencedAssemblyRecipeBuilder output(ItemStack itemStack) {
		return result(itemStack);
	}

	public SequencedAssemblyRecipeBuilder result(ItemStack itemStack) {
		this.results.add(itemStack.copy());
		return this;
	}

	public SequencedAssemblyRecipeBuilder loops(int loops) {
		this.loops = Math.max(1, loops);
		return this;
	}

	public SequencedAssemblyRecipeBuilder filling(ItemStack itemStack, String fluidId) {
		return filling(itemStack, fluidId, 1000);
	}

	public SequencedAssemblyRecipeBuilder filling(ItemStack itemStack, String fluidId, int amount) {
		return step("create:filling", json -> json.add("ingredients", ingredients(
				itemIngredient(itemStack),
				fluidIngredient(fluidId, amount))));
	}

	public SequencedAssemblyRecipeBuilder pressing() {
		return step("create:pressing", json -> {
		});
	}

	public SequencedAssemblyRecipeBuilder step(ResourceLocation type, java.util.function.Consumer<JsonObject> config) {
		JsonObject json = new JsonObject();
		json.addProperty("type", type.toString());
		config.accept(json);
		this.sequence.add(json);
		return this;
	}

	public SequencedAssemblyRecipeBuilder step(String type, java.util.function.Consumer<JsonObject> config) {
		ResourceLocation id = Objects.requireNonNull(ResourceLocation.tryParse(type), "Invalid sequenced assembly step type: " + type);
		return step(id, config);
	}

	public SequencedAssemblyRecipeBuilder step(JsonObject json) {
		this.sequence.add(json);
		return this;
	}

	public void toJson(JsonObject json) {
		if (ingredient == null || transitionalItem == null || results.isEmpty() || sequence.isEmpty()) {
			throw new IllegalStateException("Sequenced assembly recipe missing required fields");
		}

		json.add("ingredient", ingredient.toJson());
		json.add("transitionalItem", serializeItemStack(transitionalItem));
		json.addProperty("loops", loops);

		JsonArray sequenceJson = new JsonArray();
		sequence.forEach(sequenceJson::add);
		json.add("sequence", sequenceJson);

		JsonArray resultsJson = new JsonArray();
		results.forEach(stack -> resultsJson.add(serializeItemStack(stack)));
		json.add("results", resultsJson);
		json.add("result", serializeItemStack(results.get(0)));
	}

	public FinishedRecipe build() {
		return new FinishedRecipe() {
			@Override
			public void serializeRecipeData(@Nonnull JsonObject pJson) {
				SequencedAssemblyRecipeBuilder.this.toJson(pJson);
			}

			@Nonnull
			@Override
			public ResourceLocation getId() {
				return ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "sequenced_assembly/" + id.getPath());
			}

			@Nonnull
			@Override
			public RecipeSerializer<?> getType() {
				return AllRecipeTypes.SEQUENCED_ASSEMBLY.getSerializer();
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
		if (stack.getCount() != 1) {
			json.addProperty("count", stack.getCount());
		}
		if (stack.hasTag()) {
			json.addProperty("nbt", String.valueOf(stack.getTag()));
		}
		return json;
	}

	private static JsonArray ingredients(JsonObject... ingredients) {
		JsonArray array = new JsonArray();
		for (JsonObject ingredient : ingredients) {
			array.add(ingredient);
		}
		return array;
	}

	private static JsonObject itemIngredient(ItemStack stack) {
		return serializeItemStack(stack);
	}

	private static JsonObject fluidIngredient(String fluidId, int amount) {
		JsonObject json = new JsonObject();
		json.addProperty("fluid", fluidId);
		json.addProperty("amount", amount);
		return json;
	}
}
