package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.recipe.RecipeRemoval.RemoveFilter;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.p3pp3rf1y.sophisticatedstorage.init.ModBlocks;
import net.p3pp3rf1y.sophisticatedstorage.init.ModItems;

import com.mo_guang.ctpp.common.recipe.builder.create.*;
import com.mo_guang.ctpp.registry.CTPPItems;
import com.simibubi.create.AllBlocks;

import java.util.function.Consumer;

public class SophisticatedStorageRecipes {

    private static final TagKey<Item> PLANKS_TAG = TagKey.create(Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath("minecraft", "planks"));
    private static final TagKey<Item> CHESTS_TAG = TagKey.create(Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath("forge", "chests"));

    public static void init(Consumer<FinishedRecipe> provider) {
        tierUpgradeRecipes(provider);
        mechanicalCrafting(provider);
    }

    private static ItemStack tierUpgrade(String from, String to) {
        return switch (from + "_to_" + to) {
            case "basic_to_copper" -> new ItemStack(ModItems.BASIC_TO_COPPER_TIER_UPGRADE.get());
            case "basic_to_iron" -> new ItemStack(ModItems.BASIC_TO_IRON_TIER_UPGRADE.get());
            case "basic_to_gold" -> new ItemStack(ModItems.BASIC_TO_GOLD_TIER_UPGRADE.get());
            case "basic_to_diamond" -> new ItemStack(ModItems.BASIC_TO_DIAMOND_TIER_UPGRADE.get());
            case "basic_to_netherite" -> new ItemStack(ModItems.BASIC_TO_NETHERITE_TIER_UPGRADE.get());
            case "copper_to_iron" -> new ItemStack(ModItems.COPPER_TO_IRON_TIER_UPGRADE.get());
            case "copper_to_gold" -> new ItemStack(ModItems.COPPER_TO_GOLD_TIER_UPGRADE.get());
            case "copper_to_diamond" -> new ItemStack(ModItems.COPPER_TO_DIAMOND_TIER_UPGRADE.get());
            case "copper_to_netherite" -> new ItemStack(ModItems.COPPER_TO_NETHERITE_TIER_UPGRADE.get());
            case "iron_to_gold" -> new ItemStack(ModItems.IRON_TO_GOLD_TIER_UPGRADE.get());
            case "iron_to_diamond" -> new ItemStack(ModItems.IRON_TO_DIAMOND_TIER_UPGRADE.get());
            case "iron_to_netherite" -> new ItemStack(ModItems.IRON_TO_NETHERITE_TIER_UPGRADE.get());
            case "gold_to_diamond" -> new ItemStack(ModItems.GOLD_TO_DIAMOND_TIER_UPGRADE.get());
            case "gold_to_netherite" -> new ItemStack(ModItems.GOLD_TO_NETHERITE_TIER_UPGRADE.get());
            case "diamond_to_netherite" -> new ItemStack(ModItems.DIAMOND_TO_NETHERITE_TIER_UPGRADE.get());
            default -> ItemStack.EMPTY;
        };
    }

    private static ItemStack plate(Material material) {
        return ChemicalHelper.get(TagPrefix.plate, material);
    }

    private static ItemStack rod(Material material) {
        return ChemicalHelper.get(TagPrefix.rod, material);
    }

    private static ItemStack screw(Material material) {
        return ChemicalHelper.get(TagPrefix.screw, material);
    }

    private static ItemStack bolt(Material material) {
        return ChemicalHelper.get(TagPrefix.bolt, material);
    }

    private static Material getMaterial(String name) {
        return switch (name) {
            case "bronze" -> GTMaterials.Bronze;
            case "iron" -> GTMaterials.Iron;
            case "gold" -> GTMaterials.Gold;
            case "copper" -> GTMaterials.Copper;
            case "diamond" -> GTMaterials.Diamond;
            case "netherite" -> GTMaterials.Netherite;
            default -> null;
        };
    }

    private static void addUpgradeRecipe(Consumer<FinishedRecipe> provider, String from, String to,
                                         Object centerItem, String matForParts) {
        Material mat = getMaterial(matForParts);
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/" + from + "_to_" + to + "_upgrade"),
                tierUpgrade(from, to),
                "DBE", "BAB", "CBC",
                'A', centerItem,
                'B', plate(mat),
                'C', rod(mat),
                'D', screw(mat),
                'E', bolt(mat));
    }

    private static void tierUpgradeRecipes(Consumer<FinishedRecipe> provider) {
        // basic → copper (special: uses bronze parts)
        addUpgradeRecipe(provider, "basic", "copper", PLANKS_TAG, "bronze");

        // basic → iron
        addUpgradeRecipe(provider, "basic", "iron", tierUpgrade("basic", "copper"), "iron");

        // basic → gold
        addUpgradeRecipe(provider, "basic", "gold", tierUpgrade("basic", "iron"), "gold");

        // basic → diamond
        addUpgradeRecipe(provider, "basic", "diamond", tierUpgrade("basic", "gold"), "diamond");

        // basic → netherite
        addUpgradeRecipe(provider, "basic", "netherite", tierUpgrade("basic", "diamond"), "netherite");

        // copper → iron
        addUpgradeRecipe(provider, "copper", "iron", new ItemStack(Items.COPPER_INGOT), "iron");

        // copper → gold
        addUpgradeRecipe(provider, "copper", "gold", tierUpgrade("copper", "iron"), "gold");

        // copper → diamond
        addUpgradeRecipe(provider, "copper", "diamond", tierUpgrade("copper", "gold"), "diamond");

        // copper → netherite
        addUpgradeRecipe(provider, "copper", "netherite", tierUpgrade("copper", "diamond"), "netherite");

        // iron → gold
        addUpgradeRecipe(provider, "iron", "gold", new ItemStack(Items.IRON_INGOT), "gold");

        // iron → diamond
        addUpgradeRecipe(provider, "iron", "diamond", tierUpgrade("iron", "gold"), "diamond");

        // iron → netherite
        addUpgradeRecipe(provider, "iron", "netherite", tierUpgrade("iron", "diamond"), "netherite");

        // gold → diamond
        addUpgradeRecipe(provider, "gold", "diamond", new ItemStack(Items.GOLD_INGOT), "diamond");

        // gold → netherite
        addUpgradeRecipe(provider, "gold", "netherite", tierUpgrade("gold", "diamond"), "netherite");

        // diamond → netherite
        addUpgradeRecipe(provider, "diamond", "netherite", new ItemStack(Items.DIAMOND), "netherite");
    }

    private static void mechanicalCrafting(Consumer<FinishedRecipe> provider) {
        // controller
        MechanicalCraftingRecipeBuilder.builder("storage_controller")
                .pattern("EEAEE", "EFBFE", "ECGDE", "EFBFE", "EEEEE")
                .key('A', new ItemStack(ModBlocks.STORAGE_LINK_ITEM.get()))
                .key('B', new ItemStack(ModItems.BASIC_TIER_UPGRADE.get()))
                .key('C', new ItemStack(ModBlocks.STORAGE_OUTPUT_ITEM.get()))
                .key('D', new ItemStack(ModBlocks.STORAGE_INPUT_ITEM.get()))
                .key('E', AllBlocks.ANDESITE_CASING.asStack())
                .key('F', CTPPItems.STEEL_MECHANISM.asStack())
                .key('G', CHESTS_TAG)
                .output(new ItemStack(ModBlocks.CONTROLLER_ITEM.get()))
                .save(provider);
    }

    public static void sophisticatedStorageRemovals() {
        RecipeRemoval.remove(new RemoveFilter().id("sophisticatedbackpacks:stack_upgrade_omega_tier"));
        RecipeRemoval.remove(new RemoveFilter().outputRegex("sophisticatedstorage:(.*)_barrel"));
        RecipeRemoval.remove(new RemoveFilter().outputRegex("sophisticatedstorage:limited_(.*)_barrel"));
        RecipeRemoval.remove(new RemoveFilter().outputRegex("sophisticatedstorage:(.*)_chest"));
        RecipeRemoval.remove(new RemoveFilter().outputRegex("sophisticatedstorage:(.*)_shulker_box"));
        RecipeRemoval.remove(new RemoveFilter()
                .id("sophisticatedstorage:backpack_stack_upgrade_omega_tier_from_storage_stack_upgrade_omega_tier"));
        RecipeRemoval.remove(new RemoveFilter().id("sophisticatedstorage:stack_upgrade_omega_tier"));
        RecipeRemoval.remove(new RemoveFilter().id("sophisticatedstorage:void_upgrade"));
        RecipeRemoval.remove(new RemoveFilter().id("sophisticatedstorage:controller"));
        RecipeRemoval.remove(new RemoveFilter().output("sophisticatedstorage:basic_to_copper_tier_upgrade"));
        RecipeRemoval.remove(new RemoveFilter().output("sophisticatedstorage:basic_to_iron_tier_upgrade"));
        RecipeRemoval.remove(new RemoveFilter().output("sophisticatedstorage:basic_to_gold_tier_upgrade"));
        RecipeRemoval.remove(new RemoveFilter().output("sophisticatedstorage:basic_to_diamond_tier_upgrade"));
        RecipeRemoval.remove(new RemoveFilter().output("sophisticatedstorage:basic_to_netherite_tier_upgrade"));
        RecipeRemoval.remove(new RemoveFilter().output("sophisticatedstorage:copper_to_iron_tier_upgrade"));
        RecipeRemoval.remove(new RemoveFilter().output("sophisticatedstorage:copper_to_gold_tier_upgrade"));
        RecipeRemoval.remove(new RemoveFilter().output("sophisticatedstorage:copper_to_diamond_tier_upgrade"));
        RecipeRemoval.remove(new RemoveFilter().output("sophisticatedstorage:copper_to_netherite_tier_upgrade"));
        RecipeRemoval.remove(new RemoveFilter().output("sophisticatedstorage:iron_to_gold_tier_upgrade"));
        RecipeRemoval.remove(new RemoveFilter().output("sophisticatedstorage:iron_to_diamond_tier_upgrade"));
        RecipeRemoval.remove(new RemoveFilter().output("sophisticatedstorage:iron_to_netherite_tier_upgrade"));
        RecipeRemoval.remove(new RemoveFilter().output("sophisticatedstorage:gold_to_diamond_tier_upgrade"));
        RecipeRemoval.remove(new RemoveFilter().output("sophisticatedstorage:gold_to_netherite_tier_upgrade"));
        RecipeRemoval.remove(new RemoveFilter().output("sophisticatedstorage:diamond_to_netherite_tier_upgrade"));
        RecipeRemoval.remove(new RemoveFilter().output("sophisticatedstorage:copper_upgrade"));
        RecipeRemoval.remove(new RemoveFilter().output("sophisticatedstorage:gold_upgrade"));
        RecipeRemoval.remove(new RemoveFilter().output("sophisticatedstorage:diamond_upgrade"));
        RecipeRemoval.remove(new RemoveFilter().output("sophisticatedstorage:netherite_upgrade"));
    }
}
