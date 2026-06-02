package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;

import com.mo_guang.ctpp.registry.CTPPItems;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.api.data.recipe.MechanicalCraftingRecipeBuilder;

import java.util.Objects;
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
        // 迁移来源：Z:/Git/Create-New-Horizon/kubejs/server_scripts/src/sophisticatedbackpacks/sophisticatedstorage.js
        addSophisticatedStorageRecipes(provider);
    }

    private static void addSophisticatedBackpacksRecipes(Consumer<FinishedRecipe> provider) {
        shaped(provider,
                CTNHCore.id("sophisticatedbackpacks/void_upgrade"),
                itemStack(ModItems.VOID_UPGRADE.get()),
                " A ", "BCD", " E ",
                'A', externalItem("functionalstorage:void_upgrade"),
                'B', Items.DISPENSER,
                'C', ModItems.UPGRADE_BASE.get(),
                'D', Items.DROPPER,
                'E', ModItems.FILTER_UPGRADE.get());
    }

    private static void addSophisticatedStorageRecipes(Consumer<FinishedRecipe> provider) {
        addTierUpgradeRecipes(provider);
        addControllerRecipe(provider);
    }

    private static void addTierUpgradeRecipes(Consumer<FinishedRecipe> provider) {
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j <= 5; j++) {
                if (j == 1) continue;
                String source = MATERIALS[i];
                String target = MATERIALS[j];
                Object coreInput = j == i + 1 ? BASE_INPUTS[i] : externalItem(upgradeId(source, MATERIALS[j - 1]));
                addTierUpgradeRecipe(provider, source, target, coreInput, gtMaterial(target));
            }
        }
        addTierUpgradeRecipe(provider, "basic", "copper", ItemTags.PLANKS, GTMaterials.Bronze);
    }

    private static void addTierUpgradeRecipe(Consumer<FinishedRecipe> provider, String source, String target,
                                             Object coreInput, Material componentMaterial) {
        shaped(provider,
                CTNHCore.id("sophisticatedstorage/" + source + "_to_" + target + "_tier_upgrade"),
                itemStack(externalItem(upgradeId(source, target))),
                "DBE", "BAB", "CBC",
                'A', coreInput,
                'B', material(TagPrefix.plate, componentMaterial),
                'C', material(TagPrefix.rod, componentMaterial),
                'D', material(TagPrefix.screw, componentMaterial),
                'E', material(TagPrefix.bolt, componentMaterial));
    }

    private static void addControllerRecipe(Consumer<FinishedRecipe> provider) {
        MechanicalCraftingRecipeBuilder builder = MechanicalCraftingRecipeBuilder
                .shapedRecipe(externalItem("sophisticatedstorage:controller"));
        for (String row : new String[] { "EEAEE", "EFBFE", "ECGDE", "EFBFE", "EEEEE" }) {
            builder.patternLine(row);
        }
        builder.key('A', ingredient(externalItem("sophisticatedstorage:storage_link")));
        builder.key('B', ingredient(externalItem("sophisticatedstorage:basic_tier_upgrade")));
        builder.key('C', ingredient(externalItem("sophisticatedstorage:storage_output")));
        builder.key('D', ingredient(externalItem("sophisticatedstorage:storage_input")));
        builder.key('E', ingredient(AllBlocks.ANDESITE_CASING.asItem()));
        builder.key('F', ingredient(CTPPItems.STEEL_MECHANISM.get()));
        builder.key('G', ingredient(Tags.Items.CHESTS));
        builder.build(provider, CTNHCore.id("mechanical_crafting/sophisticatedstorage/controller"));
    }

    private static void shaped(Consumer<FinishedRecipe> provider, ResourceLocation id, ItemStack result,
                               Object... recipe) {
        VanillaRecipeHelper.addShapedRecipe(provider, id, result, recipe);
    }

    private static String upgradeId(String source, String target) {
        return "sophisticatedstorage:" + source + "_to_" + target + "_tier_upgrade";
    }

    private static MaterialEntry material(TagPrefix prefix, Material material) {
        return new MaterialEntry(prefix, material);
    }

    private static Material gtMaterial(String material) {
        return switch (material) {
            case "copper" -> GTMaterials.Copper;
            case "iron" -> GTMaterials.Iron;
            case "gold" -> GTMaterials.Gold;
            case "diamond" -> GTMaterials.Diamond;
            case "netherite" -> GTMaterials.Netherite;
            default -> throw new IllegalArgumentException("Unsupported storage upgrade material: " + material);
        };
    }

    private static ItemStack itemStack(ItemLike item) {
        return new ItemStack(item);
    }

    private static Item externalItem(String id) {
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(id)), id);
    }

    private static Ingredient ingredient(Object ingredient) {
        if (ingredient instanceof Ingredient directIngredient) {
            return directIngredient;
        }
        if (ingredient instanceof TagKey<?> tag) {
            return Ingredient.of(TagKey.create(net.minecraft.core.registries.Registries.ITEM, tag.location()));
        }
        if (ingredient instanceof ItemStack stack) {
            return Ingredient.of(stack);
        }
        if (ingredient instanceof ItemLike item) {
            return Ingredient.of(item);
        }
        throw new IllegalArgumentException("Unsupported recipe ingredient: " + ingredient);
    }
}
