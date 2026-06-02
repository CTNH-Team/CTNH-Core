package io.github.cpearl0.ctnhcore.data.recipe.tconstruct;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import com.mo_guang.ctpp.common.recipe.builder.create.MechanicalCraftingRecipeBuilder;
import com.mo_guang.ctpp.common.recipe.builder.create.MixingRecipeBuilder;
import com.mo_guang.ctpp.registry.CTPPItems;
import com.simibubi.create.AllItems;
import fr.lucreeper74.createmetallurgy.registries.CMBlocks;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.smeltery.block.component.SearedTankBlock;
import slimeknights.tconstruct.tables.TinkerTables;

import java.util.function.Consumer;

public class TConstructRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 迁移自 kubejs/server_scripts/src/tconstruct/tinkers.js。
        MechanicalCraftingRecipeBuilder.builder("tconstruct/seared_fuel_tank")
                .pattern(" BAB ", " BCB ", " BCB ", " BBB ")
                .key('A', TinkerSmeltery.searedBrick.get())
                .key('B', TinkerSmeltery.searedBricks.asItem())
                .key('C', TinkerSmeltery.searedGlass.asItem())
                .result(new ItemStack(TinkerSmeltery.searedTank.get(SearedTankBlock.TankType.FUEL_TANK)))
                .save(provider);

        MechanicalCraftingRecipeBuilder.builder("tconstruct/seared_melter")
                .pattern("BBBBB", "BAAAB", "BACAB", "BADAB", "BBBBB")
                .key('A', TinkerSmeltery.searedBrick.get())
                .key('B', TinkerSmeltery.searedBricks.asItem())
                .key('C', AllItems.PRECISION_MECHANISM.get())
                .key('D', CMBlocks.FOUNDRY_BASIN_BLOCK.asItem())
                .result(new ItemStack(TinkerSmeltery.searedMelter.get()))
                .save(provider);

        MechanicalCraftingRecipeBuilder.builder("tconstruct/smeltery_controller")
                .pattern("BBBBB", "BEEEB", "BECEB", "BADAB", "BBBBB")
                .key('A', TinkerSmeltery.searedBrick.get())
                .key('B', TinkerSmeltery.searedBricks.asItem())
                .key('C', TinkerSmeltery.searedMelter.asItem())
                .key('D', CTPPItems.STEEL_MECHANISM.get())
                .key('E', ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Rubber))
                .result(new ItemStack(TinkerSmeltery.smelteryController.get()))
                .save(provider);

        MixingRecipeBuilder.builder("tconstruct/heatproof_smelting_brick_dust")
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Asbestos, 4))
                .input(CTNHItems.GROUT_DUST.asStack(4))
                .output(CTNHItems.HEATPROOF_SMELTING_BRICK_DUST.asStack(8))
                .save(provider);

        VanillaRecipeHelper.addSmeltingRecipe(provider, CTNHCore.id("tconstruct/seared_brick"),
                CTNHItems.HEATPROOF_SMELTING_BRICK_DUST.asStack(), new ItemStack(TinkerSmeltery.searedBrick.get()),
                0.0f);
        VanillaRecipeHelper.addShapelessRecipe(provider, CTNHCore.id("tconstruct/puny_smelting"),
                new ItemStack(TinkerCommons.punySmelting.get()), Items.BOOK, CTNHItems.GROUT_DUST.asItem());

        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("tconstruct/grout_dust_small"),
                CTNHItems.GROUT_DUST.asStack(2),
                "CB ", "A  ", "   ",
                'A', Tags.Items.GRAVEL,
                'B', Tags.Items.SAND,
                'C', ChemicalHelper.get(TagPrefix.dust, GTMaterials.Clay));
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("tconstruct/grout_dust"),
                CTNHItems.GROUT_DUST.asStack(8),
                "CBB", "BBA", "AAA",
                'A', Tags.Items.GRAVEL,
                'B', Tags.Items.SAND,
                'C', ChemicalHelper.get(TagPrefix.dust, GTMaterials.Clay));
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("tconstruct/cast_chest"),
                new ItemStack(TinkerTables.castChest.get()),
                " A ", "CDC", "CBC",
                'A', TinkerSmeltery.ingotCast.get(),
                'B', TinkerSmeltery.searedBricks.get(),
                'C', TinkerSmeltery.searedBrick.get(),
                'D', Tags.Items.CHESTS_WOODEN);
    }
}
