package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;

import com.buuz135.functionalstorage.FunctionalStorage;

import java.util.function.Consumer;

public class SophisticatedBackpacksScriptRecipe {

    private static final String[] MATERIALS = { "basic", "copper", "iron", "gold", "diamond", "netherite" };
    private static final Object[] BASE_INPUTS = {
            ItemTags.PLANKS,
            Items.COPPER_INGOT,
            Items.IRON_INGOT,
            Items.GOLD_INGOT,
            Items.DIAMOND
    };

    public static void init(Consumer<FinishedRecipe> provider) {
        // 迁移来源：Z:/Git/Create-New-Horizon/kubejs/server_scripts/src/sophisticatedbackpacks/sophisticatedbackpacks.js
        addSophisticatedBackpacksRecipes(provider);
    }

    private static void addSophisticatedBackpacksRecipes(Consumer<FinishedRecipe> provider) {
        shaped(provider,
                CTNHCore.id("sophisticatedbackpacks/void_upgrade"),
                itemStack(ModItems.VOID_UPGRADE.get()),
                " A ", "BCD", " E ",
                'A', FunctionalStorage.VOID_UPGRADE.get(),
                'B', Items.DISPENSER,
                'C', ModItems.UPGRADE_BASE.get(),
                'D', Items.DROPPER,
                'E', ModItems.FILTER_UPGRADE.get());
    }

    private static void shaped(Consumer<FinishedRecipe> provider, ResourceLocation id, ItemStack result,
                               Object... recipe) {
        VanillaRecipeHelper.addShapedRecipe(provider, id, result, recipe);
    }

    private static ItemStack itemStack(ItemLike item) {
        return new ItemStack(item);
    }
}
