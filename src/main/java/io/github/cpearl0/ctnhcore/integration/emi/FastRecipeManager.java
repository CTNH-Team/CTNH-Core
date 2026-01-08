package io.github.cpearl0.ctnhcore.integration.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.recipe.EmiRecipeManager;
import dev.emi.emi.api.recipe.EmiRecipeSorting;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.data.EmiRecipeCategoryProperties;
import dev.emi.emi.registry.EmiRecipes;
import dev.emi.emi.registry.EmiStackList;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenCustomHashMap;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Supplier;

public class FastRecipeManager implements EmiRecipeManager {

    private final List<EmiRecipeCategory> categories;
    private final Map<EmiRecipeCategory, List<EmiIngredient>> workstations;

    private final List<EmiRecipe> recipes;
    private final Map<EmiRecipeCategory, List<EmiRecipe>> byCategory;
    private final Map<EmiStack, List<EmiRecipe>> byInput;
    private final Map<EmiStack, List<EmiRecipe>> byOutput;
    private final Map<ResourceLocation, EmiRecipe> byId;

    public FastRecipeManager(
            List<EmiRecipeCategory> rawCategories,
            Map<EmiRecipeCategory, List<EmiIngredient>> rawWorkstations,
            List<EmiRecipe> recipes
    ) {
        // ===== 0. 基础结构初始化 =====
        this.categories = rawCategories;
        this.workstations = rawWorkstations;

        int estimated = recipes.size();
        this.recipes = recipes;
        this.byCategory = new HashMap<>();
        this.byInput = new Object2ObjectOpenCustomHashMap<>(
                new EmiStackList.ComparisonHashStrategy()
        );
        this.byOutput = new Object2ObjectOpenCustomHashMap<>(
                new EmiStackList.ComparisonHashStrategy()
        );
        this.byId = new HashMap<>(estimated);

        // ===== 1. 收集 recipes =====
        for (var recipe : recipes) {

            EmiRecipeCategory cat = recipe.getCategory();
            byCategory.computeIfAbsent(cat, c -> new ArrayList<>())
                    .add(recipe);

            ResourceLocation id = recipe.getId();
            if (id != null && !byId.containsKey(id)) {
                byId.put(id, recipe);
            }
        }

        // ===== 2. 分类内排序 =====
//        for (Map.Entry<EmiRecipeCategory, List<EmiRecipe>> e
//                : byCategory.entrySet()) {
//
//            Comparator<EmiRecipe> sort =
//                    EmiRecipeCategoryProperties.getSort(e.getKey());
//
//            if (sort != EmiRecipeSorting.none()) {
//                e.getValue().sort(sort);
//            }
//        }

        // ===== 3. 构建 byInput / byOutput =====
        for (EmiRecipe recipe : recipes) {

            // inputs
            for (EmiIngredient ing : recipe.getInputs()) {
                for (EmiStack stack : ing.getEmiStacks()) {
                    byInput.computeIfAbsent(stack, s -> new ArrayList<>())
                            .add(recipe);
                }
            }

            // catalysts → input
            for (EmiIngredient ing : recipe.getCatalysts()) {
                for (EmiStack stack : ing.getEmiStacks()) {
                    byInput.computeIfAbsent(stack, s -> new ArrayList<>())
                            .add(recipe);
                }
            }

            // outputs
            for (EmiStack stack : recipe.getOutputs()) {
                byOutput.computeIfAbsent(stack, s -> new ArrayList<>())
                        .add(recipe);
            }
        }

        for (Map.Entry<EmiRecipeCategory, List<EmiIngredient>> e
                : workstations.entrySet()) {

            EmiRecipeCategory category = e.getKey();
            List<EmiRecipe> catRecipes = byCategory.get(category);
            if (catRecipes == null || catRecipes.isEmpty()) {
                continue;
            }

            for (EmiIngredient ingredient : e.getValue()) {
                for (EmiStack stack : ingredient.getEmiStacks()) {
                    EmiRecipes.byWorkstation
                            .computeIfAbsent(stack, s -> new ArrayList<>())
                            .addAll(catRecipes);
                }
            }
        }
    }

    @Override
    public List<EmiRecipeCategory> getCategories() {
        return categories;
    }

    @Override
    public List<EmiIngredient> getWorkstations(
            EmiRecipeCategory category
    ) {
        return workstations.getOrDefault(category, List.of());
    }

    @Override
    public List<EmiRecipe> getRecipes() {
        return recipes;
    }

    @Override
    public List<EmiRecipe> getRecipes(
            EmiRecipeCategory category
    ) {
        return byCategory.getOrDefault(category, List.of());
    }

    @Override
    public @Nullable EmiRecipe getRecipe(
            ResourceLocation id
    ) {
        return byId.get(id);
    }

    @Override
    public List<EmiRecipe> getRecipesByInput(
            EmiStack stack
    ) {
        return byInput.getOrDefault(stack, List.of());
    }

    @Override
    public List<EmiRecipe> getRecipesByOutput(
            EmiStack stack
    ) {
        return byOutput.getOrDefault(stack, List.of());
    }
}
