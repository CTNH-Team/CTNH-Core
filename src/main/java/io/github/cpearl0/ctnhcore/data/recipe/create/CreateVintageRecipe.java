package io.github.cpearl0.ctnhcore.data.recipe.create;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

import com.mo_guang.ctpp.data.recipe.builder.create.MechanicalCraftingRecipeBuilder;
import com.mo_guang.ctpp.data.recipe.builder.create.SequencedAssemblyRecipeBuilder;
import com.mo_guang.ctpp.data.recipe.builder.vintage.CoilingRecipeBuilder;
import com.mo_guang.ctpp.data.recipe.builder.vintage.CurvingRecipeBuilder;
import com.mo_guang.ctpp.data.recipe.builder.vintage.HammeringRecipeBuilder;
import com.mo_guang.ctpp.data.recipe.builder.vintage.TurningRecipeBuilder;
import com.mo_guang.ctpp.data.recipe.builder.vintage.VibratingRecipeBuilder;
import com.mo_guang.ctpp.registry.CTPPItems;
import com.mo_guang.ctpp.registry.CreateMaterials;
import com.negodya1.vintageimprovements.VintageBlocks;
import com.simibubi.create.AllBlocks;

import java.util.Objects;
import java.util.function.Consumer;

public final class CreateVintageRecipe {

    private CreateVintageRecipe() {}

    public static void addRecipes(Consumer<FinishedRecipe> provider) {
        addCreateFallenVintageRecipes(provider);
        addVintageImprovementsRecipes(provider);
    }

    private static void addCreateFallenVintageRecipes(Consumer<FinishedRecipe> provider) {
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("vintageimprovements/spring_coiling_machine"),
                VintageBlocks.SPRING_COILING_MACHINE.asStack(),
                "A  ", "BCD", "A  ",
                'A', Items.IRON_INGOT,
                'B', vintageItem("vintageimprovements:spring_coiling_machine_wheel"),
                'C', AllBlocks.ANDESITE_CASING.asItem(),
                'D', CTPPItems.BASIC_MECHANISM.asStack());
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("vintageimprovements/vacuum_chamber"),
                VintageBlocks.VACUUM_CHAMBER.asStack(),
                " A ", "BCB", "DED",
                'A', CTPPItems.BASIC_MECHANISM.asStack(),
                'B', ChemicalHelper.get(TagPrefix.spring, GTMaterials.Iron),
                'C', AllBlocks.ANDESITE_CASING.asItem(),
                'D', ChemicalHelper.get(TagPrefix.ingot, CreateMaterials.AndesiteAlloy),
                'E', AllBlocks.MECHANICAL_PUMP.asItem());
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("vintageimprovements/vibrating_table"),
                VintageBlocks.VIBRATING_TABLE.asStack(),
                " A ", "BCB", "BDB",
                'A', CTPPItems.BASIC_MECHANISM.asStack(),
                'B', ChemicalHelper.get(TagPrefix.spring, GTMaterials.Iron),
                'C', ItemTags.WOODEN_SLABS,
                'D', AllBlocks.MECHANICAL_PISTON.asItem());
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("vintageimprovements/centrifuge"),
                VintageBlocks.CENTRIFUGE.asStack(),
                "ABA", "CDC", "AEA",
                'A', ChemicalHelper.get(TagPrefix.spring, GTMaterials.Iron),
                'B', CTPPItems.BASIC_MECHANISM.asStack(),
                'C', ItemTags.LOGS,
                'D', AllBlocks.SHAFT.asItem(),
                'E', AllBlocks.ANDESITE_CASING.asItem());
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("vintageimprovements/curving_press"),
                VintageBlocks.CURVING_PRESS.asStack(),
                " A ", " B ", "CDC",
                'A', CTPPItems.BASIC_MECHANISM.asStack(),
                'B', AllBlocks.SHAFT.asItem(),
                'C', ChemicalHelper.get(TagPrefix.spring, GTMaterials.Iron),
                'D', AllBlocks.ANDESITE_CASING.asItem());
        VibratingRecipeBuilder.builder(CTNHCore.id("vintageimprovements/rubber_sapling_to_sticky_resin"))
                .input(GTBlocks.RUBBER_SAPLING.asStack())
                .result(GTItems.STICKY_RESIN.asStack())
                .save(provider);
        VibratingRecipeBuilder.builder(CTNHCore.id("vintageimprovements/rubber_leaves_to_sticky_resin"))
                .input(GTBlocks.RUBBER_LEAVES.asStack())
                .result(GTItems.STICKY_RESIN.asStack())
                .save(provider);
        CurvingRecipeBuilder.builder(CTNHCore.id("vintageimprovements/bronze_small_fluid_pipe_curving"))
                .input(TagUtil.createItemTag("plates/bronze", false))
                .result(ChemicalHelper.get(TagPrefix.pipeSmallFluid, GTMaterials.Bronze))
                .mode(3)
                .save(provider);
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("vintageimprovements/laser"),
                VintageBlocks.LASER.asStack(),
                "ABA", "CDE", "FGF",
                'A', AllBlocks.COGWHEEL.asItem(),
                'B', Blocks.REDSTONE_BLOCK.asItem(),
                'C', CTPPItems.STEEL_MECHANISM.asStack(),
                'D', AllBlocks.BRASS_CASING.asItem(),
                'E', ChemicalHelper.get(TagPrefix.spring, GTMaterials.Iron),
                'F', Items.QUARTZ,
                'G', vintageItem("vintageimprovements:laser_item"));
        MechanicalCraftingRecipeBuilder.builder(CTNHCore.id("vintageimprovements/helve_hammer"))
                .pattern(" A FF", "ABBCD", "AA  E")
                .key('A', Blocks.IRON_BLOCK.asItem())
                .key('B', ItemTags.LOGS)
                .key('C', CTPPItems.STEEL_MECHANISM.asStack())
                .key('D', AllBlocks.ANDESITE_CASING.asItem())
                .key('E', AllBlocks.SHAFT.asItem())
                .key('F', ChemicalHelper.get(TagPrefix.spring, GTMaterials.Iron))
                .output(VintageBlocks.HELVE.asStack())
                .save(provider);
    }

    private static void addVintageImprovementsRecipes(Consumer<FinishedRecipe> provider) {
        CoilingRecipeBuilder.builder(CTNHCore.id("vintageimprovements/iron_spring"))
                .input(ChemicalHelper.get(TagPrefix.rodLong, GTMaterials.Iron))
                .result(ChemicalHelper.get(TagPrefix.spring, GTMaterials.Iron))
                .save(provider);
        CoilingRecipeBuilder.builder(CTNHCore.id("vintageimprovements/small_iron_spring"))
                .input(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Iron))
                .result(ChemicalHelper.get(TagPrefix.springSmall, GTMaterials.Iron))
                .save(provider);
        CoilingRecipeBuilder.builder(CTNHCore.id("vintageimprovements/gold_spring"))
                .input(ChemicalHelper.get(TagPrefix.rodLong, GTMaterials.Gold))
                .result(ChemicalHelper.get(TagPrefix.spring, GTMaterials.Gold))
                .save(provider);
        CoilingRecipeBuilder.builder(CTNHCore.id("vintageimprovements/small_gold_spring"))
                .input(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Gold))
                .result(ChemicalHelper.get(TagPrefix.springSmall, GTMaterials.Gold))
                .save(provider);
        CoilingRecipeBuilder.builder(CTNHCore.id("vintageimprovements/steel_spring"))
                .input(ChemicalHelper.get(TagPrefix.rodLong, GTMaterials.Steel))
                .result(ChemicalHelper.get(TagPrefix.spring, GTMaterials.Steel))
                .save(provider);
        CoilingRecipeBuilder.builder(CTNHCore.id("vintageimprovements/small_steel_spring"))
                .input(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Steel))
                .result(ChemicalHelper.get(TagPrefix.springSmall, GTMaterials.Steel))
                .save(provider);
        CoilingRecipeBuilder.builder(CTNHCore.id("vintageimprovements/copper_spring"))
                .input(ChemicalHelper.get(TagPrefix.rodLong, GTMaterials.Copper))
                .result(ChemicalHelper.get(TagPrefix.spring, GTMaterials.Copper))
                .save(provider);
        CoilingRecipeBuilder.builder(CTNHCore.id("vintageimprovements/small_copper_spring"))
                .input(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Copper))
                .result(ChemicalHelper.get(TagPrefix.springSmall, GTMaterials.Copper))
                .save(provider);
        HammeringRecipeBuilder.builder(CTNHCore.id("vintageimprovements/gold_foil"))
                .input(TagUtil.createItemTag("plates/gold", false))
                .result(ChemicalHelper.get(TagPrefix.foil, GTMaterials.Gold, 3))
                .save(provider);
        HammeringRecipeBuilder.builder(CTNHCore.id("vintageimprovements/steel_foil"))
                .input(TagUtil.createItemTag("plates/steel", false))
                .result(ChemicalHelper.get(TagPrefix.foil, GTMaterials.Steel, 3))
                .save(provider);
        HammeringRecipeBuilder.builder(CTNHCore.id("vintageimprovements/copper_foil"))
                .input(TagUtil.createItemTag("plates/copper", false))
                .result(ChemicalHelper.get(TagPrefix.foil, GTMaterials.Copper, 3))
                .save(provider);
        HammeringRecipeBuilder.builder(CTNHCore.id("vintageimprovements/silver_foil"))
                .input(TagUtil.createItemTag("plates/silver", false))
                .result(ChemicalHelper.get(TagPrefix.foil, GTMaterials.Silver, 3))
                .save(provider);
        HammeringRecipeBuilder.builder(CTNHCore.id("vintageimprovements/bronze_foil"))
                .input(TagUtil.createItemTag("plates/bronze", false))
                .result(ChemicalHelper.get(TagPrefix.foil, GTMaterials.Bronze, 3))
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_iron_rod"))
                .input(new ItemStack(Items.IRON_INGOT))
                .result(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Iron, 2))
                .processingTime(20)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_iron_bolt"))
                .input(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Iron))
                .result(ChemicalHelper.get(TagPrefix.bolt, GTMaterials.Iron, 3))
                .processingTime(40)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_iron_screw"))
                .input(ChemicalHelper.get(TagPrefix.bolt, GTMaterials.Iron))
                .result(ChemicalHelper.get(TagPrefix.screw, GTMaterials.Iron))
                .processingTime(40)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_copper_rod"))
                .input(new ItemStack(Items.COPPER_INGOT))
                .result(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Copper, 2))
                .processingTime(20)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_copper_bolt"))
                .input(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Copper))
                .result(ChemicalHelper.get(TagPrefix.bolt, GTMaterials.Copper, 3))
                .processingTime(40)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_copper_screw"))
                .input(ChemicalHelper.get(TagPrefix.bolt, GTMaterials.Copper))
                .result(ChemicalHelper.get(TagPrefix.screw, GTMaterials.Copper))
                .processingTime(40)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_gold_rod"))
                .input(new ItemStack(Items.GOLD_INGOT))
                .result(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Gold, 2))
                .processingTime(200)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_gold_bolt"))
                .input(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Gold))
                .result(ChemicalHelper.get(TagPrefix.bolt, GTMaterials.Gold, 3))
                .processingTime(40)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_gold_screw"))
                .input(ChemicalHelper.get(TagPrefix.bolt, GTMaterials.Gold))
                .result(ChemicalHelper.get(TagPrefix.screw, GTMaterials.Gold))
                .processingTime(100)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_red_alloy_rod"))
                .input(ChemicalHelper.get(TagPrefix.ingot, GTMaterials.RedAlloy))
                .result(ChemicalHelper.get(TagPrefix.rod, GTMaterials.RedAlloy, 2))
                .processingTime(100)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_red_alloy_bolt"))
                .input(ChemicalHelper.get(TagPrefix.rod, GTMaterials.RedAlloy))
                .result(ChemicalHelper.get(TagPrefix.bolt, GTMaterials.RedAlloy, 3))
                .processingTime(40)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_red_alloy_screw"))
                .input(ChemicalHelper.get(TagPrefix.bolt, GTMaterials.RedAlloy))
                .result(ChemicalHelper.get(TagPrefix.screw, GTMaterials.RedAlloy))
                .processingTime(40)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_bronze_rod"))
                .input(ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Bronze))
                .result(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Bronze, 2))
                .processingTime(60)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_bronze_bolt"))
                .input(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Bronze))
                .result(ChemicalHelper.get(TagPrefix.bolt, GTMaterials.Bronze, 3))
                .processingTime(40)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_bronze_screw"))
                .input(ChemicalHelper.get(TagPrefix.bolt, GTMaterials.Bronze))
                .result(ChemicalHelper.get(TagPrefix.screw, GTMaterials.Bronze))
                .processingTime(40)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_wrought_iron_rod"))
                .input(ChemicalHelper.get(TagPrefix.ingot, GTMaterials.WroughtIron))
                .result(ChemicalHelper.get(TagPrefix.rod, GTMaterials.WroughtIron, 2))
                .processingTime(60)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_wrought_iron_bolt"))
                .input(ChemicalHelper.get(TagPrefix.rod, GTMaterials.WroughtIron))
                .result(ChemicalHelper.get(TagPrefix.bolt, GTMaterials.WroughtIron, 3))
                .processingTime(40)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_wrought_iron_screw"))
                .input(ChemicalHelper.get(TagPrefix.bolt, GTMaterials.WroughtIron))
                .result(ChemicalHelper.get(TagPrefix.screw, GTMaterials.WroughtIron))
                .processingTime(40)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_steel_rod"))
                .input(ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Steel))
                .result(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Steel, 2))
                .processingTime(100)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_steel_bolt"))
                .input(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Steel))
                .result(ChemicalHelper.get(TagPrefix.bolt, GTMaterials.Steel, 3))
                .processingTime(40)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_steel_screw"))
                .input(ChemicalHelper.get(TagPrefix.bolt, GTMaterials.Steel))
                .result(ChemicalHelper.get(TagPrefix.screw, GTMaterials.Steel))
                .processingTime(40)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_brass_rod"))
                .input(ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Brass))
                .result(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Brass, 2))
                .processingTime(60)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_brass_bolt"))
                .input(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Brass))
                .result(ChemicalHelper.get(TagPrefix.bolt, GTMaterials.Brass, 3))
                .processingTime(40)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_brass_screw"))
                .input(ChemicalHelper.get(TagPrefix.bolt, GTMaterials.Brass))
                .result(ChemicalHelper.get(TagPrefix.screw, GTMaterials.Brass))
                .processingTime(40)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_potin_rod"))
                .input(ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Potin))
                .result(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Potin, 2))
                .processingTime(60)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_potin_bolt"))
                .input(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Potin))
                .result(ChemicalHelper.get(TagPrefix.bolt, GTMaterials.Potin, 3))
                .processingTime(40)
                .save(provider);
        TurningRecipeBuilder.builder(CTNHCore.id("vintageimprovements/turning_potin_screw"))
                .input(ChemicalHelper.get(TagPrefix.bolt, GTMaterials.Potin))
                .result(ChemicalHelper.get(TagPrefix.screw, GTMaterials.Potin))
                .processingTime(40)
                .save(provider);
        SequencedAssemblyRecipeBuilder.builder(CTNHCore.id("vintageimprovements/small_iron_gear"))
                .input(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Iron))
                .transitional(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Iron))
                .deploying(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Iron))
                .vintageCurving(GTItems.SHAPE_EXTRUDER_GEAR_SMALL.asStack())
                .result(ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.Iron))
                .loops(1)
                .save(provider);
        SequencedAssemblyRecipeBuilder.builder(CTNHCore.id("vintageimprovements/iron_gear"))
                .input(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Iron))
                .transitional(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Iron))
                .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Iron))
                .deploying(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Iron))
                .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Iron))
                .deploying(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Iron))
                .vintageCurving(GTItems.SHAPE_EXTRUDER_GEAR.asStack())
                .result(ChemicalHelper.get(TagPrefix.gear, GTMaterials.Iron))
                .loops(1)
                .save(provider);
        SequencedAssemblyRecipeBuilder.builder(CTNHCore.id("vintageimprovements/small_bronze_gear"))
                .input(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Bronze))
                .transitional(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Bronze))
                .deploying(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Bronze))
                .vintageCurving(GTItems.SHAPE_EXTRUDER_GEAR_SMALL.asStack())
                .result(ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.Bronze))
                .loops(1)
                .save(provider);
        SequencedAssemblyRecipeBuilder.builder(CTNHCore.id("vintageimprovements/bronze_gear"))
                .input(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Bronze))
                .transitional(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Bronze))
                .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Bronze))
                .deploying(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Bronze))
                .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Bronze))
                .deploying(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Bronze))
                .vintageCurving(GTItems.SHAPE_EXTRUDER_GEAR.asStack())
                .result(ChemicalHelper.get(TagPrefix.gear, GTMaterials.Bronze))
                .loops(1)
                .save(provider);
        SequencedAssemblyRecipeBuilder.builder(CTNHCore.id("vintageimprovements/small_wrought_iron_gear"))
                .input(ChemicalHelper.get(TagPrefix.plate, GTMaterials.WroughtIron))
                .transitional(ChemicalHelper.get(TagPrefix.plate, GTMaterials.WroughtIron))
                .deploying(ChemicalHelper.get(TagPrefix.rod, GTMaterials.WroughtIron))
                .vintageCurving(GTItems.SHAPE_EXTRUDER_GEAR_SMALL.asStack())
                .result(ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.WroughtIron))
                .loops(1)
                .save(provider);
        SequencedAssemblyRecipeBuilder.builder(CTNHCore.id("vintageimprovements/wrought_iron_gear"))
                .input(ChemicalHelper.get(TagPrefix.plate, GTMaterials.WroughtIron))
                .transitional(ChemicalHelper.get(TagPrefix.plate, GTMaterials.WroughtIron))
                .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.WroughtIron))
                .deploying(ChemicalHelper.get(TagPrefix.rod, GTMaterials.WroughtIron))
                .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.WroughtIron))
                .deploying(ChemicalHelper.get(TagPrefix.rod, GTMaterials.WroughtIron))
                .vintageCurving(GTItems.SHAPE_EXTRUDER_GEAR.asStack())
                .result(ChemicalHelper.get(TagPrefix.gear, GTMaterials.WroughtIron))
                .loops(1)
                .save(provider);
        SequencedAssemblyRecipeBuilder.builder(CTNHCore.id("vintageimprovements/small_steel_gear"))
                .input(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel))
                .transitional(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel))
                .deploying(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Steel))
                .vintageCurving(GTItems.SHAPE_EXTRUDER_GEAR_SMALL.asStack())
                .result(ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.Steel))
                .loops(1)
                .save(provider);
        SequencedAssemblyRecipeBuilder.builder(CTNHCore.id("vintageimprovements/steel_gear"))
                .input(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel))
                .transitional(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel))
                .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel))
                .deploying(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Steel))
                .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel))
                .deploying(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Steel))
                .vintageCurving(GTItems.SHAPE_EXTRUDER_GEAR.asStack())
                .result(ChemicalHelper.get(TagPrefix.gear, GTMaterials.Steel))
                .loops(1)
                .save(provider);
        SequencedAssemblyRecipeBuilder.builder(CTNHCore.id("vintageimprovements/small_brass_gear"))
                .input(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Brass))
                .transitional(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Brass))
                .deploying(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Brass))
                .vintageCurving(GTItems.SHAPE_EXTRUDER_GEAR_SMALL.asStack())
                .result(ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.Brass))
                .loops(1)
                .save(provider);
        SequencedAssemblyRecipeBuilder.builder(CTNHCore.id("vintageimprovements/brass_gear"))
                .input(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Brass))
                .transitional(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Brass))
                .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Brass))
                .deploying(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Brass))
                .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Brass))
                .deploying(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Brass))
                .vintageCurving(GTItems.SHAPE_EXTRUDER_GEAR.asStack())
                .result(ChemicalHelper.get(TagPrefix.gear, GTMaterials.Brass))
                .loops(1)
                .save(provider);
        SequencedAssemblyRecipeBuilder.builder(CTNHCore.id("vintageimprovements/potin_gear"))
                .input(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Potin))
                .transitional(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Potin))
                .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Potin))
                .deploying(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Potin))
                .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Potin))
                .deploying(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Potin))
                .vintageCurving(GTItems.SHAPE_EXTRUDER_GEAR.asStack())
                .result(ChemicalHelper.get(TagPrefix.gear, GTMaterials.Potin))
                .loops(1)
                .save(provider);
        VibratingRecipeBuilder.builder(CTNHCore.id("vintageimprovements/steel_block_to_ingots"))
                .input(ChemicalHelper.get(TagPrefix.block, GTMaterials.Steel))
                .result(ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Steel, 9))
                .save(provider);
    }

    // 已检查当前依赖中的 com.negodya1.vintageimprovements.VintageBlocks 与 VintageItems：机器方块有静态字段，
    // 但 spring_coiling_machine_wheel 与 laser_item 只存在于资源/配方数据中，未作为 VintageItems 字段暴露。
    // 因此这两个 Vintage recipe-only item 只能通过注册表 id 取出，其他可静态访问的对象仍保持静态引用。
    private static ItemLike vintageItem(String id) {
        return Objects.requireNonNull(item(id), id);
    }

    private static Item item(String id) {
        ResourceLocation rl = ResourceLocation.parse(id);
        return ForgeRegistries.ITEMS.getValue(rl);
    }
}
