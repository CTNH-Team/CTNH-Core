package io.github.cpearl0.ctnhcore.utils;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipes;

import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.common.data.GTRecipeCapabilities;

import com.lowdragmc.lowdraglib.utils.NBTToJsonConverter;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.ForgeRegistries;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.netty.util.internal.UnstableApi;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;

public class CTNHRecipeHelper extends RecipeHelper {

    @UnstableApi
    public static int getInputCWUt(@NotNull GTRecipe recipe) {
        return recipe.tickInputs.getOrDefault(GTRecipeCapabilities.CWU, new ArrayList<>())
                .stream().mapToInt(i -> (int) i.content).sum();
    }

    public static ItemStack insertItemToOutput(NotifiableItemStackHandler handler, ItemStack stack, boolean simulate) {
        if (handler == null || stack.isEmpty()) {
            return stack;
        }
        if (!stack.isStackable()) {
            return insertToEmpty(handler, stack, simulate);
        }

        IntList emptySlots = new IntArrayList();
        int slots = handler.getSlots();

        for (int i = 0; i < slots; i++) {
            ItemStack slotStack = handler.getStackInSlot(i);
            if (slotStack.isEmpty()) {
                emptySlots.add(i);
            } else if (ItemHandlerHelper.canItemStacksStack(stack, slotStack)) {
                stack = handler.insertItemInternal(i, stack, simulate);
                if (stack.isEmpty()) {
                    return ItemStack.EMPTY;
                }
            }
        }

        for (int slot : emptySlots) {
            stack = handler.insertItemInternal(slot, stack, simulate);
            if (stack.isEmpty()) {
                return ItemStack.EMPTY;
            }
        }
        return stack;
    }

    /**
     * Only inerts to empty slots. Perfect for not stackable items
     */
    public static ItemStack insertToEmpty(NotifiableItemStackHandler handler, ItemStack stack, boolean simulate) {
        if (handler == null || stack.isEmpty()) {
            return stack;
        }
        int slots = handler.getSlots();
        for (int i = 0; i < slots; i++) {
            ItemStack slotStack = handler.getStackInSlot(i);
            if (slotStack.isEmpty()) {
                stack = handler.insertItemInternal(i, stack, simulate);
                if (stack.isEmpty()) {
                    return ItemStack.EMPTY;
                }
            }
        }
        return stack;
    }

    public static final class KeepIngredientRecipeHelper {

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
}
