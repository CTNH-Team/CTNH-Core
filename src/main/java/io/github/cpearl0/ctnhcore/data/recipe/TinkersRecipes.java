package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
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

import com.mo_guang.ctpp.common.recipe.builder.create.*;
import com.mo_guang.ctpp.registry.CTPPItems;
import com.simibubi.create.AllItems;
import fr.lucreeper74.createmetallurgy.registries.CMBlocks;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.smeltery.block.component.SearedTankBlock;
import slimeknights.tconstruct.tables.TinkerTables;

import java.util.function.Consumer;

public class TinkersRecipes {

    private static final ItemStack PRECISION_MECHANISM = AllItems.PRECISION_MECHANISM.asStack();

    private static final TagKey<Item> GRAVEL_TAG = TagKey.create(Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath("forge", "gravel"));
    private static final TagKey<Item> SAND_TAG = TagKey.create(Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath("forge", "sand"));
    private static final TagKey<Item> WOODEN_CHESTS_TAG = TagKey.create(Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath("forge", "chests/wooden"));

    public static void init(Consumer<FinishedRecipe> provider) {
        mechanicalCrafting(provider);
        mixingRecipes(provider);
        shapedRecipes(provider);
        smeltingRecipes(provider);
        shapelessRecipes(provider);
    }

    private static void mechanicalCrafting(Consumer<FinishedRecipe> provider) {
        // seared_fuel_tank
        MechanicalCraftingRecipeBuilder.builder("seared_fuel_tank")
                .pattern(" BAB ", " BCB ", " BCB ", " BBB ")
                .key('A', new ItemStack(TinkerSmeltery.searedBrick))
                .key('B', new ItemStack(TinkerSmeltery.searedBricks))
                .key('C', new ItemStack(TinkerSmeltery.searedGlass))
                .output(new ItemStack(TinkerSmeltery.searedTank.get(SearedTankBlock.TankType.FUEL_TANK)))
                .save(provider);

        // seared_melter
        MechanicalCraftingRecipeBuilder.builder("seared_melter")
                .pattern("BBBBB", "BAAAB", "BACAB", "BADAB", "BBBBB")
                .key('A', new ItemStack(TinkerSmeltery.searedBrick))
                .key('B', new ItemStack(TinkerSmeltery.searedBricks))
                .key('C', PRECISION_MECHANISM)
                .key('D', CMBlocks.FOUNDRY_BASIN_BLOCK.asStack())
                .output(new ItemStack(TinkerSmeltery.searedMelter))
                .save(provider);

        // smeltery_controller
        MechanicalCraftingRecipeBuilder.builder("smeltery_controller")
                .pattern("BBBBB", "BEEEB", "BECEB", "BADAB", "BBBBB")
                .key('A', new ItemStack(TinkerSmeltery.searedBrick))
                .key('B', new ItemStack(TinkerSmeltery.searedBricks))
                .key('C', new ItemStack(TinkerSmeltery.searedMelter))
                .key('D', CTPPItems.STEEL_MECHANISM.asStack())
                .key('E', ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Rubber))
                .output(new ItemStack(TinkerSmeltery.smelteryController))
                .save(provider);
    }

    private static void mixingRecipes(Consumer<FinishedRecipe> provider) {
        // heatproof_smelting_brick_dust: 4x asbestos_dust + 4x grout_dust → 8x
        MixingRecipeBuilder.builder("heatproof_smelting_brick_dust")
                .input(new ItemStack(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Asbestos).getItem(), 4))
                .input(new ItemStack(CTNHItems.GROUT_DUST.asItem(), 4))
                .result(CTNHItems.HEATPROOF_SMELTING_BRICK_DUST.asStack(8))
                .save(provider);
    }

    private static void shapedRecipes(Consumer<FinishedRecipe> provider) {
        ItemStack clayDust = ChemicalHelper.get(TagPrefix.dust, GTMaterials.Clay);

        // grout_dust (2x)
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/grout_dust_x2"),
                CTNHItems.GROUT_DUST.asStack(2),
                "CB ", "A  ", "   ",
                'A', GRAVEL_TAG,
                'B', SAND_TAG,
                'C', clayDust);

        // grout_dust (8x)
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/grout_dust_x8"),
                CTNHItems.GROUT_DUST.asStack(8),
                "CBB", "BBA", "AAA",
                'A', GRAVEL_TAG,
                'B', SAND_TAG,
                'C', clayDust);

        // cast_chest
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/cast_chest"),
                new ItemStack(TinkerTables.castChest),
                " A ", "CDC", "CBC",
                'A', new ItemStack(TinkerSmeltery.ingotCast),
                'B', new ItemStack(TinkerSmeltery.searedBricks),
                'C', new ItemStack(TinkerSmeltery.searedBrick),
                'D', WOODEN_CHESTS_TAG);
    }

    private static void smeltingRecipes(Consumer<FinishedRecipe> provider) {
        // heatproof_smelting_brick_dust -> seared_brick
        VanillaRecipeHelper.addSmeltingRecipe(provider, CTNHCore.id("smelting_seared_brick"),
                CTNHItems.HEATPROOF_SMELTING_BRICK_DUST.asStack(),
                new ItemStack(TinkerSmeltery.searedBrick),
                0.3f);
    }

    private static void shapelessRecipes(Consumer<FinishedRecipe> provider) {
        // book + grout_dust -> puny_smelting
        VanillaRecipeHelper.addShapelessRecipe(provider, CTNHCore.id("crafttable/puny_smelting"),
                new ItemStack(TinkerCommons.punySmelting),
                new ItemStack(Items.BOOK),
                CTNHItems.GROUT_DUST.asStack());
    }
}
