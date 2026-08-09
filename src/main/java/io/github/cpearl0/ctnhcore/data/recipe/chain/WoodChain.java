package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;

import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import com.hollingsworth.arsnouveau.setup.registry.BlockRegistry;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.init.DAItems;
import twilightforest.init.TFBlocks;

import java.util.function.Consumer;

/** Converted from server_scripts/src/gtceu/chain/wood.js */
public class WoodChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 橡木树苗
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("oak_sapling_1"))
                .notConsumable(new ItemStack(Items.OAK_SAPLING))
                .outputItems(new ItemStack(Items.OAK_LOG, 10))
                .outputItems(new ItemStack(Items.OAK_LEAVES, 8))
                .outputItems(new ItemStack(Items.OAK_SAPLING, 5))
                .EUt(120).duration(100)
                .save(provider);

        // 云杉树苗
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("spruce_sapling_1"))
                .notConsumable(new ItemStack(Items.SPRUCE_SAPLING))
                .outputItems(new ItemStack(Items.SPRUCE_LOG, 10))
                .outputItems(new ItemStack(Items.SPRUCE_LEAVES, 8))
                .outputItems(new ItemStack(Items.SPRUCE_SAPLING, 5))
                .EUt(120).duration(100)
                .save(provider);

        // 白桦树苗
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("birch_sapling_1"))
                .notConsumable(new ItemStack(Items.BIRCH_SAPLING))
                .outputItems(new ItemStack(Items.BIRCH_LOG, 10))
                .outputItems(new ItemStack(Items.BIRCH_LEAVES, 8))
                .outputItems(new ItemStack(Items.BIRCH_SAPLING, 5))
                .EUt(120).duration(100)
                .save(provider);

        // 丛林树苗
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("jungle_sapling_1"))
                .notConsumable(new ItemStack(Items.JUNGLE_SAPLING))
                .outputItems(new ItemStack(Items.JUNGLE_LOG, 10))
                .outputItems(new ItemStack(Items.JUNGLE_LEAVES, 8))
                .outputItems(new ItemStack(Items.JUNGLE_SAPLING, 5))
                .EUt(120).duration(100)
                .save(provider);

        // 金合欢树苗
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("acacia_sapling_1"))
                .notConsumable(new ItemStack(Items.ACACIA_SAPLING))
                .outputItems(new ItemStack(Items.ACACIA_LOG, 10))
                .outputItems(new ItemStack(Items.ACACIA_LEAVES, 8))
                .outputItems(new ItemStack(Items.ACACIA_SAPLING, 5))
                .EUt(120).duration(100)
                .save(provider);

        // 深色橡木树苗
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("dark_oak_sapling_1"))
                .notConsumable(new ItemStack(Items.DARK_OAK_SAPLING))
                .outputItems(new ItemStack(Items.DARK_OAK_LOG, 10))
                .outputItems(new ItemStack(Items.DARK_OAK_LEAVES, 8))
                .outputItems(new ItemStack(Items.DARK_OAK_SAPLING, 5))
                .EUt(120).duration(100)
                .save(provider);

        // 樱花树苗
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("cherry_sapling_1"))
                .notConsumable(new ItemStack(Items.CHERRY_SAPLING))
                .outputItems(new ItemStack(Items.CHERRY_LOG, 10))
                .outputItems(new ItemStack(Items.CHERRY_LEAVES, 8))
                .outputItems(new ItemStack(Items.CHERRY_SAPLING, 5))
                .EUt(120).duration(100)
                .save(provider);

        // 水晶树苗 (Aether)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("crystal_sapling"))
                .notConsumable(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("lost_aether_content:crystal_sapling"))))
                .outputItems(new ItemStack(AetherBlocks.SKYROOT_LOG.get().asItem(), 10))
                .outputItems(new ItemStack(AetherBlocks.CRYSTAL_LEAVES.get().asItem(), 8))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("lost_aether_content:crystal_sapling")),
                        5))
                .outputItems(new ItemStack(AetherItems.WHITE_APPLE.get(), 8))
                .EUt(120).duration(100)
                .save(provider);

        // 节日树苗 (Aether)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("holiday_sapling"))
                .notConsumable(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("lost_aether_content:holiday_sapling"))))
                .outputItems(new ItemStack(AetherBlocks.SKYROOT_LOG.get().asItem(), 10))
                .outputItems(new ItemStack(AetherBlocks.HOLIDAY_LEAVES.get().asItem(), 8))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("lost_aether_content:holiday_sapling")),
                        5))
                .outputItems(new ItemStack(AetherItems.WHITE_APPLE.get(), 8))
                .EUt(120).duration(100)
                .save(provider);

        // 冷杉树苗 (BoP)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("fir_sapling"))
                .notConsumable(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:fir_sapling"))))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:fir_log")), 10))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:fir_leaves")), 8))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:fir_sapling")), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 红杉树苗 (BoP)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("redwood_sapling"))
                .notConsumable(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:redwood_sapling"))))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:redwood_log")), 10))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:redwood_leaves")), 8))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:redwood_sapling")), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 桃花心木树苗 (BoP)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("mahogany_sapling"))
                .notConsumable(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:mahogany_sapling"))))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:mahogany_log")), 10))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:mahogany_leaves")), 8))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:mahogany_sapling")), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 蓝花楹树苗 (BoP)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("jacaranda_sapling"))
                .notConsumable(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:jacaranda_sapling"))))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:jacaranda_log")), 10))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:jacaranda_leaves")), 8))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:jacaranda_sapling")), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 棕榈树苗 (BoP)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("palm_sapling"))
                .notConsumable(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:palm_sapling"))))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:palm_log")), 10))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:palm_leaves")), 8))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:palm_sapling")), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 柳树苗 (BoP)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("willow_sapling"))
                .notConsumable(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:willow_sapling"))))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:willow_log")), 10))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:willow_leaves")), 8))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:willow_sapling")), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 枯木树苗 (BoP)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("dead_sapling"))
                .notConsumable(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:dead_sapling"))))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:dead_log")), 10))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:dead_leaves")), 8))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:dead_sapling")), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 魔法树苗 (BoP)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("magic_sapling"))
                .notConsumable(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:magic_sapling"))))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:magic_log")), 10))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:magic_leaves")), 8))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:magic_sapling")), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 暗影树苗 (BoP)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("umbran_sapling"))
                .notConsumable(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:umbran_sapling"))))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:umbran_log")), 10))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:umbran_leaves")), 8))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:umbran_sapling")), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 地狱树苗 (BoP)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("hellbark_sapling"))
                .notConsumable(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:hellbark_sapling"))))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:hellbark_log")), 10))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:hellbark_leaves")), 8))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:hellbark_sapling")), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 胡桃树苗 (Ecologics)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("walnut_sapling"))
                .notConsumable(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ecologics:walnut_sapling"))))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ecologics:walnut_log")), 10))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ecologics:walnut_leaves")), 8))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ecologics:walnut_sapling")), 5))
                .outputItems(
                        new ItemStack(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ecologics:walnut")), 4))
                .EUt(120).duration(100)
                .save(provider);

        // 雅格根树苗 (Deep Aether)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("yagroot_sapling"))
                .notConsumable(new ItemStack(DABlocks.YAGROOT_SAPLING.get().asItem()))
                .outputItems(new ItemStack(DABlocks.YAGROOT_LOG.get().asItem(), 10))
                .outputItems(new ItemStack(DABlocks.YAGROOT_LEAVES.get().asItem(), 8))
                .outputItems(new ItemStack(DABlocks.YAGROOT_SAPLING.get().asItem(), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 玫瑰根树苗 (Deep Aether)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("roseroot_sapling"))
                .notConsumable(new ItemStack(DABlocks.ROSEROOT_SAPLING.get().asItem()))
                .outputItems(new ItemStack(DABlocks.ROSEROOT_LOG.get().asItem(), 10))
                .outputItems(new ItemStack(DABlocks.ROSEROOT_LEAVES.get().asItem(), 8))
                .outputItems(new ItemStack(DABlocks.ROSEROOT_SAPLING.get().asItem(), 5))
                .outputItems(new ItemStack(DAItems.AERGLOW_BLOSSOM.get(), 16))
                .EUt(120).duration(100)
                .save(provider);

        // 蓝玫瑰根树苗 (Deep Aether)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("blue_roseroot_sapling"))
                .notConsumable(new ItemStack(DABlocks.BLUE_ROSEROOT_SAPLING.get().asItem()))
                .outputItems(new ItemStack(DABlocks.ROSEROOT_LOG.get().asItem(), 10))
                .outputItems(new ItemStack(DABlocks.BLUE_ROSEROOT_LEAVES.get().asItem(), 8))
                .outputItems(new ItemStack(DABlocks.BLUE_ROSEROOT_SAPLING.get().asItem(), 5))
                .outputItems(new ItemStack(DAItems.AERGLOW_BLOSSOM.get(), 16))
                .EUt(120).duration(100)
                .save(provider);

        // 绿色奥术树苗 (Ars Nouveau)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("green_archwood_sapling"))
                .notConsumable(new ItemStack(BlockRegistry.FLOURISHING_SAPLING.get()))
                .outputItems(new ItemStack(BlockRegistry.FLOURISHING_LOG.get(), 10))
                .outputItems(new ItemStack(BlockRegistry.FLOURISHING_LEAVES.get(), 8))
                .outputItems(new ItemStack(BlockRegistry.FLOURISHING_SAPLING.get(), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 紫色奥术树苗 (Ars Nouveau)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("purple_archwood_sapling"))
                .notConsumable(new ItemStack(BlockRegistry.VEXING_SAPLING.get()))
                .outputItems(new ItemStack(BlockRegistry.VEXING_LOG.get(), 10))
                .outputItems(new ItemStack(BlockRegistry.VEXING_LEAVES.get(), 8))
                .outputItems(new ItemStack(BlockRegistry.VEXING_SAPLING.get(), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 红色奥术树苗 (Ars Nouveau)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("red_archwood_sapling"))
                .notConsumable(new ItemStack(BlockRegistry.BLAZING_SAPLING.get()))
                .outputItems(new ItemStack(BlockRegistry.BLAZING_LOG.get(), 10))
                .outputItems(new ItemStack(BlockRegistry.BLAZING_LEAVES.get(), 8))
                .outputItems(new ItemStack(BlockRegistry.BLAZING_SAPLING.get(), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 蓝色奥术树苗 (Ars Nouveau)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("blue_archwood_sapling"))
                .notConsumable(new ItemStack(BlockRegistry.CASCADING_SAPLING.get()))
                .outputItems(new ItemStack(BlockRegistry.CASCADING_LOG.get(), 10))
                .outputItems(new ItemStack(BlockRegistry.CASCADING_LEAVE.get(), 8))
                .outputItems(new ItemStack(BlockRegistry.CASCADING_SAPLING.get(), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 太阳根树苗 (Deep Aether)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("sunroot_sapling"))
                .notConsumable(new ItemStack(DABlocks.SUNROOT_SAPLING.get().asItem()))
                .outputItems(new ItemStack(DABlocks.SUNROOT_LOG.get().asItem(), 10))
                .outputItems(new ItemStack(DABlocks.SUNROOT_LEAVES.get().asItem(), 8))
                .outputItems(new ItemStack(DABlocks.SUNROOT_SAPLING.get().asItem(), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 康贝里树苗 (Deep Aether)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("conberry_sapling"))
                .notConsumable(new ItemStack(DABlocks.CONBERRY_SAPLING.get().asItem()))
                .outputItems(new ItemStack(DABlocks.CONBERRY_LOG.get().asItem(), 10))
                .outputItems(new ItemStack(DABlocks.CONBERRY_LEAVES.get().asItem(), 8))
                .outputItems(new ItemStack(DABlocks.CONBERRY_SAPLING.get().asItem(), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 粗根树苗 (Deep Aether)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("cruderoot_sapling"))
                .notConsumable(new ItemStack(DABlocks.CRUDEROOT_SAPLING.get().asItem()))
                .outputItems(new ItemStack(DABlocks.CRUDEROOT_LOG.get().asItem(), 10))
                .outputItems(new ItemStack(DABlocks.CRUDEROOT_LEAVES.get().asItem(), 8))
                .outputItems(new ItemStack(DABlocks.CRUDEROOT_SAPLING.get().asItem(), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 牛油果树苗 (Cultural Delights)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("avocado_sapling"))
                .notConsumable(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("culturaldelights:avocado_sapling"))))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("culturaldelights:avocado_log")), 10))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("culturaldelights:avocado_leaves")), 8))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("culturaldelights:avocado_sapling")), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 暗木树苗 (暮色森林)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("darkwood_sapling"))
                .notConsumable(new ItemStack(TFBlocks.DARKWOOD_SAPLING.get().asItem()))
                .outputItems(new ItemStack(TFBlocks.DARK_LOG.get().asItem(), 10))
                .outputItems(new ItemStack(TFBlocks.DARK_LEAVES.get().asItem(), 8))
                .outputItems(new ItemStack(TFBlocks.DARKWOOD_SAPLING.get().asItem(), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 红树林苗 (暮色森林)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("mangrove_sapling"))
                .notConsumable(new ItemStack(TFBlocks.MANGROVE_SAPLING.get().asItem()))
                .outputItems(new ItemStack(TFBlocks.MANGROVE_LOG.get().asItem(), 10))
                .outputItems(new ItemStack(TFBlocks.MANGROVE_LEAVES.get().asItem(), 8))
                .outputItems(new ItemStack(TFBlocks.MANGROVE_SAPLING.get().asItem(), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 树冠树苗 (暮色森林)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("canopy_sapling"))
                .notConsumable(new ItemStack(TFBlocks.CANOPY_SAPLING.get().asItem()))
                .outputItems(new ItemStack(TFBlocks.CANOPY_LOG.get().asItem(), 10))
                .outputItems(new ItemStack(TFBlocks.CANOPY_LEAVES.get().asItem(), 8))
                .outputItems(new ItemStack(TFBlocks.CANOPY_SAPLING.get().asItem(), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 暮色橡木树苗 (暮色森林)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("twilight_oak_sapling"))
                .notConsumable(new ItemStack(TFBlocks.TWILIGHT_OAK_SAPLING.get().asItem()))
                .outputItems(new ItemStack(TFBlocks.TWILIGHT_OAK_LOG.get().asItem(), 10))
                .outputItems(new ItemStack(TFBlocks.TWILIGHT_OAK_LEAVES.get().asItem(), 8))
                .outputItems(new ItemStack(TFBlocks.TWILIGHT_OAK_SAPLING.get().asItem(), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 橡胶树苗
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("rubber_sapling_1"))
                .notConsumable(GTBlocks.RUBBER_SAPLING.asStack())
                .outputItems(GTBlocks.RUBBER_LOG.asStack(10))
                .outputItems(GTBlocks.RUBBER_LEAVES.asStack(8))
                .outputItems(GTBlocks.RUBBER_SAPLING.asStack(5))
                .outputItems(GTItems.STICKY_RESIN.asStack(5))
                .EUt(120).duration(100)
                .save(provider);

        // 起源树苗 (BoP)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("origin_sapling"))
                .notConsumable(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:origin_sapling"))))
                .outputItems(new ItemStack(Items.OAK_LOG, 10))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:origin_leaves")), 8))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:origin_sapling")), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 彩虹白桦树苗 (BoP)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("rainbow_birch_sapling"))
                .notConsumable(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:rainbow_birch_sapling"))))
                .outputItems(new ItemStack(Items.BIRCH_LOG, 10))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:rainbow_birch_leaves")),
                        8))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:rainbow_birch_sapling")),
                        5))
                .EUt(120).duration(100)
                .save(provider);

        // 花橡树苗 (BoP)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("flowering_oak_sapling"))
                .notConsumable(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:flowering_oak_sapling"))))
                .outputItems(new ItemStack(Items.OAK_LOG, 10))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:flowering_oak_leaves")),
                        8))
                .outputItems(new ItemStack(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:flowering_oak_sapling")),
                        5))
                .EUt(120).duration(100)
                .save(provider);

        // 金橡树苗 (Aether)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("golden_oak_sapling"))
                .notConsumable(new ItemStack(AetherBlocks.GOLDEN_OAK_SAPLING.get().asItem()))
                .outputItems(new ItemStack(AetherBlocks.GOLDEN_OAK_LOG.get().asItem(), 10))
                .outputItems(new ItemStack(AetherBlocks.GOLDEN_OAK_LEAVES.get().asItem(), 8))
                .outputItems(new ItemStack(AetherBlocks.GOLDEN_OAK_SAPLING.get().asItem(), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 彩虹橡木树苗 (暮色森林)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("rainbow_oak_sapling"))
                .notConsumable(new ItemStack(TFBlocks.RAINBOW_OAK_SAPLING.get().asItem()))
                .outputItems(new ItemStack(TFBlocks.TWILIGHT_OAK_LOG.get().asItem(), 10))
                .outputItems(new ItemStack(TFBlocks.RAINBOW_OAK_LEAVES.get().asItem(), 8))
                .outputItems(new ItemStack(TFBlocks.RAINBOW_OAK_SAPLING.get().asItem(), 5))
                .EUt(120).duration(100)
                .save(provider);

        // 时间树苗 (暮色森林)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("time_sapling"))
                .notConsumable(new ItemStack(TFBlocks.TIME_SAPLING.get().asItem()))
                .outputItems(new ItemStack(TFBlocks.TIME_LOG.get().asItem(), 10))
                .outputItems(new ItemStack(TFBlocks.TIME_LEAVES.get().asItem(), 8))
                .chancedOutput(new ItemStack(TFBlocks.TIME_SAPLING.get().asItem()), 100, 0)
                .chancedOutput(new ItemStack(TFBlocks.TIME_LOG_CORE.get().asItem()), 100, 0)
                .EUt(120).duration(100)
                .save(provider);

        // 变形树苗 (暮色森林)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("transformation_sapling"))
                .notConsumable(new ItemStack(TFBlocks.TRANSFORMATION_SAPLING.get().asItem()))
                .outputItems(new ItemStack(TFBlocks.TRANSFORMATION_LOG.get().asItem(), 10))
                .outputItems(new ItemStack(TFBlocks.TRANSFORMATION_LEAVES.get().asItem(), 8))
                .chancedOutput(new ItemStack(TFBlocks.TRANSFORMATION_SAPLING.get().asItem()), 100, 0)
                .chancedOutput(new ItemStack(TFBlocks.TRANSFORMATION_LOG_CORE.get().asItem()), 100, 0)
                .EUt(120).duration(100)
                .save(provider);

        // 采矿树苗 (暮色森林)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("mining_sapling"))
                .notConsumable(new ItemStack(TFBlocks.MINING_SAPLING.get().asItem()))
                .outputItems(new ItemStack(TFBlocks.MINING_LOG.get().asItem(), 10))
                .outputItems(new ItemStack(TFBlocks.MINING_LEAVES.get().asItem(), 8))
                .chancedOutput(new ItemStack(TFBlocks.MINING_SAPLING.get().asItem()), 100, 0)
                .chancedOutput(new ItemStack(TFBlocks.MINING_LOG_CORE.get().asItem()), 100, 0)
                .EUt(120).duration(100)
                .save(provider);

        // 分拣树苗 (暮色森林)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("sorting_sapling"))
                .notConsumable(new ItemStack(TFBlocks.SORTING_SAPLING.get().asItem()))
                .outputItems(new ItemStack(TFBlocks.SORTING_LOG.get().asItem(), 10))
                .outputItems(new ItemStack(TFBlocks.SORTING_LEAVES.get().asItem(), 8))
                .chancedOutput(new ItemStack(TFBlocks.SORTING_SAPLING.get().asItem()), 100, 0)
                .chancedOutput(new ItemStack(TFBlocks.SORTING_LOG_CORE.get().asItem()), 100, 0)
                .EUt(120).duration(100)
                .save(provider);

        // 空心橡木树苗 (暮色森林)
        CTNHRecipeTypes.WOOD_BIONICS.recipeBuilder(CTNHCore.id("hollow_oak_sapling_1"))
                .notConsumable(new ItemStack(TFBlocks.HOLLOW_OAK_SAPLING.get().asItem()))
                .outputItems(new ItemStack(TFBlocks.TWILIGHT_OAK_LOG.get().asItem(), 64))
                .outputItems(new ItemStack(TFBlocks.TWILIGHT_OAK_LEAVES.get().asItem(), 64))
                .outputItems(new ItemStack(TFBlocks.FIREFLY.get().asItem(), 10))
                .chancedOutput(new ItemStack(TFBlocks.HOLLOW_OAK_SAPLING.get().asItem()), 100, 0)
                .EUt(120).duration(100)
                .save(provider);
    }
}
