package io.github.cpearl0.ctnhcore.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public final class CreateJeiRecipeCache {

    private static volatile RecipeManager lastManager;
    private static volatile Map<RecipeType<?>, List<Recipe<?>>> byType;
    private static volatile List<Recipe<?>> all;

    // 用于调试 / reload 感知
    private static final AtomicInteger VERSION = new AtomicInteger(0);

    private CreateJeiRecipeCache() {}

    public static int version() {
        return VERSION.get();
    }

    /** 在已知 reload 发生时调用（推荐） */
    public static void invalidate() {
        lastManager = null;
        byType = null;
        all = null;
        VERSION.incrementAndGet();
    }

    private static void ensureInit() {
        RecipeManager current = Minecraft.getInstance()
                .getConnection()
                .getRecipeManager();

        if (current == lastManager && byType != null && all != null) {
            return;
        }

        Map<RecipeType<?>, List<Recipe<?>>> newByType = new HashMap<>();
        List<Recipe<?>> newAll = new ArrayList<>();

        // ✅ 使用公开 API
        for (Recipe<?> recipe : current.getRecipes()) {
            newAll.add(recipe);
            newByType
                    .computeIfAbsent(recipe.getType(), t -> new ArrayList<>())
                    .add(recipe);
        }

        // 冻结结构，避免误改
        newByType.replaceAll((k, v) -> List.copyOf(v));

        lastManager = current;
        byType = newByType;
        all = List.copyOf(newAll);
        VERSION.incrementAndGet();
    }

    public static List<Recipe<?>> allRecipes() {
        ensureInit();
        return all;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Recipe<?>> List<T> recipesByType(RecipeType<T> type) {
        ensureInit();
        return (List<T>) byType.getOrDefault(type, List.of());
    }
}
