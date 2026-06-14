package io.github.cpearl0.ctnhcore.data.recipe.tconstruct;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.recipe.RecipeRemoval;
import io.github.cpearl0.ctnhcore.data.recipe.RecipeRemoval.RemoveFilter;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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

    public static void tconstructRemovals() {
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/iron/ingot_1"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/gold/powered_rail"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/iron/nugget_3"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/seared/melter"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/casting/seared/smeltery_controller"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/glass/sand"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/glass/sand_cast"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/scorched/alloyer"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:tables/cast_chest"));
        RecipeRemoval.remove(new RemoveFilter().output("tconstruct:seared_brick"));
        RecipeRemoval.remove(new RemoveFilter().output("tconstruct:grout"));
        RecipeRemoval.remove(new RemoveFilter().output("tconstruct:puny_smelting"));
        RecipeRemoval.remove(new RemoveFilter().output("tconstruct:seared_fuel_tank"));
        RecipeRemoval.remove(new RemoveFilter().output("tconstruct:seared_fuel_gauge"));
        RecipeRemoval.remove(new RemoveFilter().output("tconstruct:seared_ingot_tank"));
        RecipeRemoval.remove(new RemoveFilter().output("tconstruct:seared_ingot_gauge"));
        RecipeRemoval.remove(new RemoveFilter().idRegex("tconstruct:compat\\/create\\/andesite_alloy_(.*)"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/casting/clay/block"));
        RecipeRemoval.remove(new RemoveFilter().idRegex("tconstruct:smeltery\\/melting\\/clay\\/(.*)"));
        RecipeRemoval.remove(new RemoveFilter().idRegex("tconstruct:smeltery\\/casting\\/clay\\/brick_(.*)_cast"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/iron/chain_boots"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/iron/chain_chestplate"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/iron/chain_helmet"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/iron/chain_leggings"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/casting/filling/scorched_ingot_gauge"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/casting/filling/scorched_ingot_tank"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/casting/filling/scorched_fuel_gauge"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/casting/filling/scorched_fuel_tank"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/casting/filling/scorched_lantern_full"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/casting/filling/scorched_lantern_pixel"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/casting/filling/seared_ingot_tank"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/casting/filling/seared_ingot_gauge"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/casting/filling/seared_fuel_gauge"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/casting/filling/seared_fuel_tank"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/casting/filling/seared_lantern_full"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/casting/filling/seared_lantern_pixel"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/iron/nugget"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/entity_melting/heads/creeper"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/amethyst/tinted_glass"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/ender/end_crystal"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/copper/gauge"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/obsidian/beacon"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/obsidian/gauge"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/quartz/daylight_detector"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/scorched/glass_tinted"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/seared/fluid_cannon"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/seared/fuel_tank"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/seared/gauge"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/seared/glass"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/seared/glass_tinted"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/seared/ingot_tank"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/seared/lantern"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/seared/melter"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/seared/pane"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/seared/seared_casting_tank"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:tools/materials/melting/glass"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/casting/ender/eye"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/diamond/raw"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/diamond/raw_block"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/diamond/ore_singular"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/diamond/ore_dense"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/diamond/ore_sparse"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/diamond/geore"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/gold/gilded_blackstone"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/gold/nether_gold_ore"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/emerald/raw"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/emerald/raw_block"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/emerald/ore_singular"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/emerald/ore_dense"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/emerald/ore_sparse"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/emerald/geore"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/precious_alloy/raw"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/precious_alloy/raw_block"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/precious_alloy/ore_singular"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/precious_alloy/ore_dense"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/precious_alloy/ore_sparse"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/precious_alloy/geore"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/tin/raw"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/tin/raw_block"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/tin/ore_singular"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/tin/ore_dense"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/tin/ore_sparse"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/tin/geore"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/silver/raw"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/silver/raw_block"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/silver/ore_singular"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/silver/ore_dense"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/silver/ore_sparse"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/silver/geore"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/zinc/raw"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/zinc/raw_block"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/zinc/ore_singular"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/zinc/ore_dense"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/zinc/ore_sparse"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/zinc/geore"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/nickel/raw"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/nickel/raw_block"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/nickel/ore_singular"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/nickel/ore_dense"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/nickel/ore_sparse"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/nickel/geore"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/lead/raw"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/lead/raw_block"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/lead/ore_singular"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/lead/ore_dense"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/lead/ore_sparse"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/lead/geore"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/beryllium/raw"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/beryllium/raw_block"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/beryllium/ore_singular"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/beryllium/ore_dense"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/beryllium/ore_sparse"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/beryllium/geore"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/molybdenum/raw"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/molybdenum/raw_block"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/molybdenum/ore_singular"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/molybdenum/ore_dense"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/molybdenum/ore_sparse"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/molybdenum/geore"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/brass/raw"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/brass/raw_block"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/brass/ore_singular"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/brass/ore_dense"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/brass/ore_sparse"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/brass/geore"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/gold/raw"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/gold/raw_block"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/gold/ore_singular"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/gold/ore_dense"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/gold/ore_sparse"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/gold/geore"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/iron/raw"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/iron/raw_block"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/iron/ore_singular"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/iron/ore_dense"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/iron/ore_sparse"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/iron/geore"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/bronze/raw"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/bronze/raw_block"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/bronze/ore_singular"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/bronze/ore_dense"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/bronze/ore_sparse"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/bronze/geore"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/copper/raw"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/copper/raw_block"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/copper/ore_singular"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/copper/ore_dense"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/copper/ore_sparse"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/copper/geore"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/cobalt/raw"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/cobalt/raw_block"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/cobalt/ore_singular"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/cobalt/ore_dense"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/cobalt/ore_sparse"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/cobalt/geore"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/manganese/raw"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/manganese/raw_block"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/manganese/ore_singular"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/manganese/ore_dense"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/manganese/ore_sparse"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/manganese/geore"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/slag/raw"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/slag/raw_block"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/slag/ore_singular"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/slag/ore_dense"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/slag/ore_sparse"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/slag/geore"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/steel/raw"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/steel/raw_block"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/steel/ore_singular"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/steel/ore_dense"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/steel/ore_sparse"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/steel/geore"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/aluminum/raw"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/aluminum/raw_block"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/aluminum/ore_singular"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/aluminum/ore_dense"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/aluminum/ore_sparse"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/aluminum/geore"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/uranium/raw"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/uranium/raw_block"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/uranium/ore_singular"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/uranium/ore_dense"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/uranium/ore_sparse"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/uranium/geore"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/glass/raw"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/glass/raw_block"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/glass/ore_singular"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/glass/ore_dense"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/glass/ore_sparse"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/glass/geore"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/invar/raw"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/invar/raw_block"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/invar/ore_singular"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/invar/ore_dense"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/invar/ore_sparse"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/invar/geore"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/platinum/raw"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/platinum/raw_block"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/platinum/ore_singular"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/platinum/ore_dense"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/platinum/ore_sparse"));
        RecipeRemoval.remove(new RemoveFilter().id("tconstruct:smeltery/melting/metal/platinum/geore"));
    }

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
