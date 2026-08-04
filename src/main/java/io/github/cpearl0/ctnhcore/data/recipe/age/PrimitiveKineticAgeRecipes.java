package io.github.cpearl0.ctnhcore.data.recipe.age;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.UncategorizedMaterials;
import io.github.cpearl0.ctnhcore.data.recipe.create.CreateRecipeJsonHelper;
import io.github.cpearl0.ctnhcore.registry.CTNHBlocks;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import com.google.gson.JsonObject;
import com.mo_guang.ctpp.data.recipe.builder.create.*;
import com.mo_guang.ctpp.data.recipe.builder.diesel.DistillationRecipeBuilder;
import com.mo_guang.ctpp.data.recipe.builder.diesel.HammerRecipeBuilder;
import com.mo_guang.ctpp.data.recipe.builder.vintage.CentrifugationRecipeBuilder;
import com.mo_guang.ctpp.data.recipe.builder.vintage.PressurizingRecipeBuilder;
import com.mo_guang.ctpp.data.recipe.builder.vintage.VacuumizingRecipeBuilder;
import com.mo_guang.ctpp.registry.CTPPBlocks;
import com.mo_guang.ctpp.registry.CTPPItems;
import com.mo_guang.ctpp.registry.CreateMaterials;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import io.github.lounode.ae2cs.common.init.AECSBlocks;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.ingredient.NoContainerIngredient;
import slimeknights.tconstruct.library.recipe.melting.IMeltingRecipe;
import slimeknights.tconstruct.library.recipe.melting.MeltingRecipeBuilder;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.smeltery.block.component.SearedTankBlock.TankType;
import vazkii.botania.common.item.BotaniaItems;

import java.util.function.Consumer;

public class PrimitiveKineticAgeRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        addWroughtIronRecipes(provider);
        addMortarRecipes(provider);
        addGlassRecipes(provider);
        addAndesiteAlloyRecipes(provider);
        addRoseQuartzRecipes(provider);
        addCokeOvenBrickRecipes(provider);
        addSmeltingBrickRecipes(provider);
        addTConstructMachineRecipes(provider);
        addSmelteryControllerRecipe(provider);
        addTConstructSmelteryIORecipes(provider);
        addSteelRecipes(provider);
        addPlantOilRecipes(provider);
        addFirebrickRecipes(provider);
        addRubberRecipes(provider);
        addKineticCraftingRecipes(provider);
        addKineticMechanicalCraftingRecipes(provider);
        addKineticMechanismRecipes(provider);
        addEarlyMaterialMixingRecipes(provider);
        addSteelPrecursorRecipes(provider);
        addElectronTubeRecipes(provider);
        addVacuumTubeRecipes(provider);
        addFluidPipeRecipes(provider);
        addCopperCasingRecipes(provider);
        addCarbonizedLogRecipes(provider);
        addSteelCasingRecipes(provider);
    }

    private static void addWroughtIronRecipes(Consumer<FinishedRecipe> provider) {
        // 锻铁锭（GT 锻造锤）
        GTRecipeTypes.FORGE_HAMMER_RECIPES.recipeBuilder(CTNHCore.id("hot_wrought_iron_ingot_fixed"))
                .EUt(8)
                .duration(20)
                .inputItems(TagPrefix.ingotHot, GTMaterials.WroughtIron)
                .outputItems(TagPrefix.ingot, GTMaterials.WroughtIron)
                .save(provider);

        HammerRecipeBuilder.builder("wrought_iron_ingot_from_hot")
                .input(TagPrefix.ingotHot, GTMaterials.WroughtIron)
                .output(TagPrefix.ingot, GTMaterials.WroughtIron)
                .save(provider);

        PressingRecipeBuilder.builder("wrought_iron_ingot_from_hot")
                .input(TagPrefix.ingotHot, GTMaterials.WroughtIron)
                .output(TagPrefix.ingot, GTMaterials.WroughtIron)
                .save(provider);

        // 热锻铁锭（熔炉）
        VanillaRecipeHelper.addSmeltingRecipe(provider, CTNHCore.id("smelting_iron_to_hot_wrought_iron"),
                Items.IRON_INGOT.getDefaultInstance(),
                ChemicalHelper.get(TagPrefix.ingotHot, GTMaterials.WroughtIron),
                1.4f);
    }

    private static void addMortarRecipes(Consumer<FinishedRecipe> provider) {
        // 燧石粉（研钵）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/flint_dust_from_mortar"),
                ChemicalHelper.get(TagPrefix.dust, GTMaterials.Flint),
                "X", "m",
                'X', Items.FLINT);

        // 下界石英粉（研钵）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/nether_quartz_dust_from_mortar"),
                ChemicalHelper.get(TagPrefix.dust, GTMaterials.NetherQuartz),
                "X", "m",
                'X', Items.QUARTZ);
    }

    private static void addGlassRecipes(Consumer<FinishedRecipe> provider) {
        // 玻璃粉（机械动力搅拌：1 石英砂粉 + 1 燧石微粉）
        MixingRecipeBuilder.builder(CTNHCore.id("create/glass_dust_flint"))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.QuartzSand))
                .input(ChemicalHelper.get(TagPrefix.dustTiny, GTMaterials.Flint))
                .output(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Glass))
                .save(provider);

        // 玻璃粉（机械动力搅拌：8 石英砂粉 + 1 燧石粉 → 8 玻璃粉）
        MixingRecipeBuilder.builder(CTNHCore.id("create/glass_full_dust_flint"))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.QuartzSand, 8))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Flint))
                .output(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Glass, 8))
                .save(provider);

        // 玻璃粉（1 玻璃粉 → 144 mB GTCEu 玻璃）
        MeltingRecipeBuilder.melting(
                Ingredient.of(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Glass)),
                GTMaterials.Glass.getFluid(),
                FluidValues.INGOT)
                .save(provider, CTNHCore.id("smeltery/melting/glass"));

        // 玻璃（熔铸盆：144 mB GTCEu 玻璃 → 1 原版玻璃）
        ItemCastingRecipeBuilder.basinRecipe(Items.GLASS)
                .setFluidAndTime(GTMaterials.Glass.getFluid(144))
                .save(provider, CTNHCore.id("smeltery/casting/glass_from_gtceu_glass"));
    }

    private static void addAndesiteAlloyRecipes(Consumer<FinishedRecipe> provider) {
        // 安山合金锭（工作台）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/andesite_alloy_ingot"),
                ChemicalHelper.get(TagPrefix.ingot, CreateMaterials.AndesiteAlloy, 4),
                "ABA", "BAB", "ABA",
                'A', Items.IRON_INGOT,
                'B', Items.ANDESITE);

        // 安山合金粉（熔融铁与安山岩粉）
        MixingRecipeBuilder.builder("andesite_alloy_from_iron")
                .result(new ItemStack(ChemicalHelper.get(TagPrefix.dust, CreateMaterials.AndesiteAlloy).getItem(), 2))
                .inputFluid(GTMaterials.Iron.getFluid(144))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Andesite))
                .save(provider);

        // 安山合金粉（铁粉副产）
        MixingRecipeBuilder.builder("andesite_alloy_dust_with_secondary")
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Andesite))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Iron))
                .result(ChemicalHelper.get(TagPrefix.dust, CreateMaterials.AndesiteAlloy))
                .result(ChemicalHelper.get(TagPrefix.dust, CreateMaterials.AndesiteAlloy), 0.3)
                .save(provider);

        // 安山合金台阶（动力锯：1 安山合金块 → 2 台阶）
        CuttingRecipeBuilder.builder("andesite_alloy_block_to_slab")
                .input(AllBlocks.ANDESITE_ALLOY_BLOCK.asItem())
                .result(CTNHBlocks.ANDESITE_ALLOY_SLAB.asStack(2))
                .save(provider);

        // 安山合金台阶（切石机：1 安山合金块 → 2 台阶）
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(AllBlocks.ANDESITE_ALLOY_BLOCK),
                RecipeCategory.BUILDING_BLOCKS, CTNHBlocks.ANDESITE_ALLOY_SLAB, 2)
                .unlockedBy("has_andesite_alloy_block",
                        InventoryChangeTrigger.TriggerInstance.hasItems(AllBlocks.ANDESITE_ALLOY_BLOCK))
                .save(provider, CTNHCore.id("stonecutting/andesite_alloy_block_to_slab"));

        // 小安山合金齿轮（1 安山合金板 + 锉刀）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/small_gear_andesite_alloy"),
                ChemicalHelper.get(TagPrefix.gearSmall, CreateMaterials.AndesiteAlloy),
                "P", "f",
                'P', ChemicalHelper.get(TagPrefix.plate, CreateMaterials.AndesiteAlloy));

        // 安山合金齿轮（1 安山合金台阶 + 锉刀）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/gear_andesite_alloy"),
                ChemicalHelper.get(TagPrefix.gear, CreateMaterials.AndesiteAlloy),
                "S", "f",
                'S', CTNHBlocks.ANDESITE_ALLOY_SLAB.asStack());
    }

    private static void addRoseQuartzRecipes(Consumer<FinishedRecipe> provider) {
        // 玫瑰石英块（加热塑形）
        CompactingRecipeBuilder.builder(CTNHCore.id("create/rose_quartz_block"))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.NetherQuartz, 9))
                .input(new net.minecraft.world.item.ItemStack(Items.REDSTONE, 32))
                .result(AECSBlocks.PURE_ROSE_QUARTZ_BLOCK.toStack())
                .heated()
                .save(provider);

        // 玫瑰石英（动力锯切割）
        CuttingRecipeBuilder.builder("rose_quartz_block_to_rose_quartz")
                .input(AECSBlocks.PURE_ROSE_QUARTZ_BLOCK.asItem())
                .result(AllItems.ROSE_QUARTZ.asStack(9))
                .save(provider);

        // 磨制玫瑰石英（GT 切割机）
        GTRecipeTypes.CUTTER_RECIPES.recipeBuilder(CTNHCore.id("rose_quartz_block_to_polished_rose_quartz"))
                .inputItems(AECSBlocks.PURE_ROSE_QUARTZ_BLOCK.asItem())
                .outputItems(AllItems.POLISHED_ROSE_QUARTZ.asItem(), 9)
                .duration(40)
                .EUt(8)
                .save(provider);
    }

    private static void addCokeOvenBrickRecipes(Consumer<FinishedRecipe> provider) {
        // 焦炉砖泥（搅拌机混合黏土球 + 沙子，参考原版压缩焦黏土配方，无需模具）
        MixingRecipeBuilder.builder(CTNHCore.id("create/coke_oven_brick_mud"))
                .input(Items.CLAY_BALL, 3)
                .input(ItemTags.SAND, 5)
                .output(CTNHItems.COKE_OVEN_BRICK_MUD.asStack(3))
                .processingTime(40)
                .save(provider);

        // 焦炉泥砖（焦炉砖泥 + 木制砖模具）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/coke_oven_brick_from_mold"),
                GTItems.COKE_OVEN_BRICK.asStack(4),
                "FF",
                "FM",
                'F', CTNHItems.COKE_OVEN_BRICK_MUD.asStack(),
                'M', GTItems.WOODEN_FORM_BRICK.asStack());

        // 焦炉砖块（加热塑形）
        CompactingRecipeBuilder.builder(CTNHCore.id("create/coke_oven_bricks"))
                .input(GTItems.COKE_OVEN_BRICK.asStack(4))
                .inputFluid(GTMaterials.Concrete.getFluid(200))
                .result(GTBlocks.CASING_COKE_BRICKS.asStack())
                .heated()
                .processingTime(40)
                .save(provider);

        // 高级焦炉砖（钢框架右键焦炉砖）
        ItemApplicationRecipeBuilder.builder("high_grade_coke_oven_bricks")
                .input(GTBlocks.CASING_COKE_BRICKS.asStack())
                .input(ChemicalHelper.get(TagPrefix.frameGt, GTMaterials.Steel))
                .output(CTNHBlocks.HIGH_GRADE_COKE_OVEN_BRICKS.asStack())
                .save(provider);
    }

    private static void addSmeltingBrickRecipes(Consumer<FinishedRecipe> provider) {
        // 砖泥（搅拌机混合黏土球 + 沙子 + 砾石，小份）
        MixingRecipeBuilder.builder(CTNHCore.id("create/brick_mud"))
                .input(Items.CLAY_BALL)
                .input(ItemTags.SAND)
                .input(Items.GRAVEL)
                .output(CTNHItems.BRICK_MUD.asStack(2))
                .processingTime(40)
                .save(provider);

        // 砖胚（砖泥 + 木制砖模具，3:4）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/brick_preform_from_mold"),
                CTNHItems.BRICK_PREFORM.asStack(4),
                "FF",
                "FM",
                'F', CTNHItems.BRICK_MUD.asStack(),
                'M', GTItems.WOODEN_FORM_BRICK.asStack());

        // 焦黑砖（烧制砖胚）
        VanillaRecipeHelper.addSmeltingRecipe(provider, CTNHCore.id("brick_preform_to_seared_brick"),
                Ingredient.of(CTNHItems.BRICK_PREFORM.asStack()),
                new ItemStack(TinkerSmeltery.searedBrick.get()), 0.3f);
    }

    private static void addTConstructMachineRecipes(Consumer<FinishedRecipe> provider) {
        // 焦黑熔化炉：5 焦黑砖 + 1 焦黑储罐（燃料表/储量表，空罐）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/seared_melter"),
                new ItemStack(TinkerSmeltery.searedMelter.get()),
                "BGB",
                "BBB",
                'B', TinkerSmeltery.searedBrick.get(),
                'G', NoContainerIngredient.of(TinkerSmeltery.searedTank.get(TankType.FUEL_GAUGE),
                        TinkerSmeltery.searedTank.get(TankType.INGOT_GAUGE)));
    }

    private static void addSmelteryControllerRecipe(Consumer<FinishedRecipe> provider) {
        // 冶炼炉控制器（动力合成器机械合成：20 焦黑砖 + 1 铜量计 + 3 精密构件 + 1 焦黑熔化炉）
        // 流程：焦黑熔化炉 →(动力合成器) 冶炼炉；熔化炉居中，上方铜量计，左右下精密构件
        MechanicalCraftingRecipeBuilder.builder("smeltery_controller")
                .pattern("BBBBB", "BBGBB", "BAMAB", "BBABB", "BBBBB")
                .key('A', AllItems.PRECISION_MECHANISM.asItem())
                .key('B', TinkerSmeltery.searedBrick.get())
                .key('G', TinkerSmeltery.copperGauge.get().asItem())
                .key('M', TinkerSmeltery.searedMelter.get().asItem())
                .output(new ItemStack(TinkerSmeltery.smelteryController.get()))
                .save(provider);
    }

    private static void addTConstructSmelteryIORecipes(Consumer<FinishedRecipe> provider) {
        // 焦黑排液孔（4 焦黑砖 + 2 机械动力流体管道；原 GTC 铜锭配方已注释停用）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/seared_drain"),
                new ItemStack(TinkerSmeltery.searedDrain.get()),
                "# #", "C C", "# #",
                '#', TinkerSmeltery.searedBrick.get(),
                'C', AllBlocks.FLUID_PIPE.asItem());

        // 焦黑疏导孔（4 焦黑砖 + 2 机械动力智能流体管道；原 GTC 金锭配方已注释停用）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/seared_duct"),
                new ItemStack(TinkerSmeltery.searedDuct.get()),
                "# #", "C C", "# #",
                '#', TinkerSmeltery.searedBrick.get(),
                'C', AllBlocks.SMART_FLUID_PIPE.asItem());
    }

    private static void addSteelRecipes(Consumer<FinishedRecipe> provider) {
        MeltingRecipeBuilder.melting(
                Ingredient.of(CTNHItems.REFINED_IRON_INGOT.get()),
                TinkerFluids.moltenSteel,
                FluidValues.INGOT * 8)
                .save(provider, CTNHCore.id("smeltery/melting/refined_iron_ingot_to_steel"));
    }

    private static void addPlantOilRecipes(Consumer<FinishedRecipe> provider) {
        // 植物油质块（搅拌机：8 种子 + 水 → 2 块）
        MixingRecipeBuilder.builder(CTNHCore.id("create/plant_oil_mass"))
                .input(TagUtil.createItemTag("seeds"), 8)
                .inputFluid(GTMaterials.Water.getFluid(250))
                .output(CTNHBlocks.PLANT_OIL_MASS.asStack(2))
                .processingTime(40)
                .save(provider);

        // 植物油（压实：1 块 → 100mB 种子油）
        CompactingRecipeBuilder.builder(CTNHCore.id("create/plant_oil"))
                .input(CTNHBlocks.PLANT_OIL_MASS.asStack())
                .resultFluid(GTMaterials.SeedOil.getFluid(100))
                .processingTime(40)
                .save(provider);

        // 润滑油（分馏：500mB 种子油 → 250mB 润滑油，加热）
        new DistillationRecipeBuilder(CTNHCore.id("create/plant_oil_distillation"))
                .inputFluid(GTMaterials.SeedOil.getFluid(500))
                .heat(HeatCondition.HEATED)
                .duration(200)
                .outputFluid(GTMaterials.Lubricant.getFluid(250))
                .outputFluid(GTMaterials.Water.getFluid(250))
                .save(provider);
    }

    private static void addFirebrickRecipes(Consumer<FinishedRecipe> provider) {
        // 耐火黏土粉（机械搅拌）
        MixingRecipeBuilder.builder(CTNHCore.id("create/fireclay_dust"))
                .input(ChemicalHelper.get(TagPrefix.dust, CTNHMaterials.Kaolinite))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Graphite))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Clay))
                .output(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Fireclay, 3))
                .processingTime(40)
                .save(provider);

        // 压缩耐火黏土（加压处理）
        PressurizingRecipeBuilder.builder(CTNHCore.id("vintageimprovements/compressed_fireclay"))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Fireclay))
                .inputFluid(GTMaterials.Water.getFluid(250))
                .result(GTItems.COMPRESSED_FIRECLAY.asStack())
                .heatRequirement("heated")
                .processingTime(0)
                .save(provider);

        // 耐火砖块（加热塑形）
        CompactingRecipeBuilder.builder(CTNHCore.id("create/firebricks"))
                .input(GTItems.FIRECLAY_BRICK.asStack(4))
                .inputFluid(GTMaterials.Concrete.getFluid(400))
                .result(GTBlocks.CASING_PRIMITIVE_BRICKS.asStack())
                .heated()
                .processingTime(40)
                .save(provider);
    }

    private static void addRubberRecipes(Consumer<FinishedRecipe> provider) {
        // 预处理橡胶粉（加热混合）
        MixingRecipeBuilder.builder("createfallen_rubber_powder_from_sulfur")
                .input(Ingredient.of(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Sulfur)))
                .input(Ingredient.of(ChemicalHelper.get(TagPrefix.dust, GTMaterials.RawRubber)), 3)
                .output(CTNHItems.RUBBER_POWDER.asStack())
                .heatRequirement("heated")
                .save(provider);

        // 黏性树脂离心（Vintage Improvements 离心机）
        CentrifugationRecipeBuilder.builder(CTNHCore.id("vintageimprovements/sticky_resin_centrifugation"))
                .input(GTItems.STICKY_RESIN.asStack())
                .result(ChemicalHelper.get(TagPrefix.dust, GTMaterials.RawRubber))
                .result(ChemicalHelper.get(TagPrefix.dust, GTMaterials.RawRubber), 0.5)
                .resultFluid(GTMaterials.Glue.getFluid(50))
                .result(ChemicalHelper.get(TagPrefix.dust, GTMaterials.RawRubber), 0.3)
                .result(GTItems.PLANT_BALL.asStack(), 0.1)
                .minimalRpm(256)
                .save(provider);

        // 液态橡胶（蓝火加压处理）
        PressurizingRecipeBuilder.builder(
                CTNHCore.id("vintageimprovements/liquid_rubber_from_rubber_powder"))
                .input(CTNHItems.RUBBER_POWDER.asStack())
                .resultFluid(GTMaterials.Rubber.getFluid(144))
                .heatRequirement("superheated")
                .processingTime(200)
                .save(provider);

        // 橡胶（冶炼炉 1000°C 熔化预处理橡胶粉 → 熔融橡胶 144mB，蓝火加压路线保留）
        MeltingRecipeBuilder.melting(
                Ingredient.of(CTNHItems.RUBBER_POWDER.asStack()),
                GTMaterials.Rubber.getFluid(FluidValues.INGOT),
                1000,
                IMeltingRecipe.calcTimeForAmount(1000, FluidValues.INGOT))
                .save(provider, CTNHCore.id("smeltery/melting/rubber_powder_to_rubber"));
    }

    private static void addKineticCraftingRecipes(Consumer<FinishedRecipe> provider) {
        // 动力轴（安山合金锭切割）
        CuttingRecipeBuilder.builder("cutting_shaft_from_andesite_alloy_ingot")
                .input(ChemicalHelper.get(TagPrefix.ingot, CreateMaterials.AndesiteAlloy))
                .result(new ItemStack(AllBlocks.SHAFT.asItem(), 2))
                .save(provider);

        // 机械冲压机
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/mechanical_press"),
                AllBlocks.MECHANICAL_PRESS.asStack(),
                " A ", "BCB", " D ",
                'A', Items.IRON_INGOT,
                'B', AllBlocks.SHAFT.asStack(),
                'C', AllBlocks.ANDESITE_CASING.asStack(),
                'D', Items.IRON_BLOCK);

        // 风车轴承
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/windmill_bearing"),
                AllBlocks.WINDMILL_BEARING.asStack(),
                "AAA", "BCB", "BDB",
                'A', ItemTags.WOODEN_SLABS,
                'B', Items.STONE,
                'C', CTPPItems.BASIC_MECHANISM.asStack(),
                'D', AllBlocks.SHAFT.asStack());

        // 机械轴承
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/mechanical_bearing"),
                AllBlocks.MECHANICAL_BEARING.asStack(),
                "AAA", "BCB", "BDB",
                'A', ItemTags.WOODEN_SLABS,
                'B', ItemTags.PLANKS,
                'C', ChemicalHelper.get(TagPrefix.plate, CreateMaterials.AndesiteAlloy),
                'D', AllBlocks.SHAFT.asStack());

        // 货仓
        VanillaRecipeHelper.addShapelessRecipe(provider, CTNHCore.id("crafttable/depot"), AllBlocks.DEPOT.asStack(),
                AllBlocks.ANDESITE_CASING.asStack(),
                ChemicalHelper.get(TagPrefix.plate, CreateMaterials.AndesiteAlloy));

        // 小型齿轮
        VanillaRecipeHelper.addShapedRecipe(provider, true,
                CTNHCore.id("crafttable/cogwheel"), AllBlocks.COGWHEEL.asStack(4),
                " A ", "ABA", " A ",
                'A', ItemTags.PLANKS,
                'B', ChemicalHelper.get(TagPrefix.gearSmall, CreateMaterials.AndesiteAlloy));

        // 大型齿轮
        VanillaRecipeHelper.addShapedRecipe(provider, true,
                CTNHCore.id("crafttable/large_cogwheel"),
                AllBlocks.LARGE_COGWHEEL.asStack(4),
                " A ", "ABA", " A ",
                'A', GTBlocks.TREATED_WOOD_PLANK.asStack(),
                'B', ChemicalHelper.get(TagPrefix.gear, CreateMaterials.AndesiteAlloy));

        // 水车
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/water_wheel"),
                AllBlocks.WATER_WHEEL.asStack(),
                "AAA", "ABA", "AAA",
                'A', GTBlocks.TREATED_WOOD_PLANK.asStack(),
                'B', AllBlocks.LARGE_COGWHEEL.asStack());

        // 机械钻头
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/mechanical_drill"),
                AllBlocks.MECHANICAL_DRILL.asStack(),
                " D ", "CBC", " A ",
                'A', AllBlocks.ANDESITE_CASING.asStack(),
                'B', AllBlocks.SHAFT.asStack(),
                'C', ChemicalHelper.get(TagPrefix.ingot, CreateMaterials.AndesiteAlloy),
                'D', ChemicalHelper.get(TagPrefix.toolHeadDrill, GTMaterials.Iron));

        // 机械搅拌器
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/mechanical_mixer_create"),
                AllBlocks.MECHANICAL_MIXER.asStack(),
                "ABA", "CDC", " E ",
                'A', CTPPItems.BASIC_MECHANISM.asStack(),
                'B', AllBlocks.SHAFT.asStack(),
                'C', AllBlocks.ANDESITE_CASING.asStack(),
                'D', AllBlocks.LARGE_COGWHEEL.asStack(),
                'E', AllItems.WHISK.asStack());

        // 机械手
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/deployer"), AllBlocks.DEPLOYER.asStack(),
                "ABA", "CDC", " E ",
                'A', CTPPItems.BASIC_MECHANISM.asStack(),
                'B', AllItems.ELECTRON_TUBE.asStack(),
                'C', AllBlocks.ANDESITE_CASING.asStack(),
                'D', AllBlocks.SHAFT.asStack(),
                'E', AllItems.BRASS_HAND.asStack());

        // 装置控制器
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/contraption_controls"),
                AllBlocks.CONTRAPTION_CONTROLS.asStack(),
                " A ", "BCB", " D ",
                'A', Items.OAK_BUTTON,
                'B', CTPPItems.BASIC_MECHANISM.asStack(),
                'C', AllBlocks.ANDESITE_CASING.asStack(),
                'D', AllItems.ELECTRON_TUBE.asStack());

        // 机械注液器
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/spout"), AllBlocks.SPOUT.asStack(),
                "ABA", "ACA", " D ",
                'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Bronze),
                'B', Items.GLASS,
                'C', AllBlocks.COPPER_CASING.asStack(),
                'D', AllBlocks.FLUID_PIPE.asItem());

        // 转速控制器
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/rotation_speed_controller"),
                AllBlocks.ROTATION_SPEED_CONTROLLER.asStack(),
                " A ", "BCB", "DDD",
                'A', AllBlocks.COGWHEEL.asStack(),
                'B', AllBlocks.SHAFT.asStack(),
                'C', AllItems.PRECISION_MECHANISM.asStack(),
                'D', AllBlocks.BRASS_CASING.asStack());

        // 便携式存储接口
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/portable_storage_interface"),
                AllBlocks.PORTABLE_STORAGE_INTERFACE.asStack(),
                "   ", "ABC", "   ",
                'A', AllBlocks.ANDESITE_CASING.asStack(),
                'B', CTPPItems.BASIC_MECHANISM.asStack(),
                'C', AllBlocks.CHUTE.asStack());

        // 矿车组装器
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/cart_assembler"),
                AllBlocks.CART_ASSEMBLER.asStack(),
                " A ", "BCB", "DED",
                'A', Items.SLIME_BALL,
                'B', AllBlocks.POWERED_LATCH.asStack(),
                'C', AllBlocks.ANDESITE_CASING.asStack(),
                'D', ChemicalHelper.get(TagPrefix.plate, CreateMaterials.AndesiteAlloy),
                'E', CTPPItems.BASIC_MECHANISM.asStack());

        // 石磨
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/millstone"),
                AllBlocks.MILLSTONE.asStack(),
                " A ", "BCB", "DDD",
                'A', AllBlocks.CHUTE.asStack(),
                'B', AllBlocks.COGWHEEL.asStack(),
                'C', CTPPItems.BASIC_MECHANISM.asStack(),
                'D', Items.SMOOTH_STONE);

        // 动力皮带（皮革）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/belt_connector_leather"),
                AllItems.BELT_CONNECTOR.asStack(3),
                "   ", "AAA", "BBB",
                'A', Items.LEATHER,
                'B', Items.DRIED_KELP);

        // 动力皮带（聚乙烯）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/belt_connector_polyethylene"),
                AllItems.BELT_CONNECTOR.asStack(6),
                "   ", "AAA", "BBB",
                'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Polyethylene),
                'B', Items.DRIED_KELP);

        // 动力皮带（聚氯乙烯）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/belt_connector_polyvinyl_chloride"),
                AllItems.BELT_CONNECTOR.asStack(8),
                "   ", "AAA", "BBB",
                'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.PolyvinylChloride),
                'B', Items.DRIED_KELP);

        // 动力皮带（聚苯并咪唑）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/belt_connector_polybenzimidazole"),
                AllItems.BELT_CONNECTOR.asStack(16),
                "   ", "AAA", "BBB",
                'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Polybenzimidazole),
                'B', Items.DRIED_KELP);

        // 工程师护目镜
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/goggles"), AllItems.GOGGLES.asStack(),
                "ABA", "CDC", "   ",
                'A', Items.LEAD,
                'B', Items.SLIME_BALL,
                'C', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Gold),
                'D', BotaniaItems.engineerGoggles);
    }

    private static void addKineticMechanicalCraftingRecipes(Consumer<FinishedRecipe> provider) {
        // 机械动力风扇（机械合成）
        MechanicalCraftingRecipeBuilder.builder("encased_fan")
                .pattern("ABCBA", "DDEDD", "AFBFA", "AFBFA", "GGHGG")
                .key('A', AllBlocks.ANDESITE_CASING.asItem())
                .key('B', AllBlocks.SHAFT.asItem())
                .key('C', Items.REDSTONE_TORCH)
                .key('D', ChemicalHelper.get(TagPrefix.rod, GTMaterials.WroughtIron))
                .key('E', Items.REDSTONE)
                .key('F', ChemicalHelper.get(TagPrefix.plate, GTMaterials.WroughtIron))
                .key('G', Blocks.IRON_BARS.asItem())
                .key('H', AllItems.PROPELLER.asItem())
                .output(new ItemStack(AllBlocks.ENCASED_FAN.asItem())).save(provider);

        // 粉碎轮（机械合成）
        MechanicalCraftingRecipeBuilder.builder("crushing_wheel")
                .pattern(" AAA ", "ABCBA", "ACDCA", "ABCBA", " AAA ")
                .key('A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.WroughtIron))
                .key('B', ChemicalHelper.get(TagPrefix.plate, CreateMaterials.AndesiteAlloy))
                .key('C', ChemicalHelper.get(TagPrefix.rod, GTMaterials.Iron))
                .key('D', ChemicalHelper.get(TagPrefix.gear, GTMaterials.WroughtIron))
                .output(new ItemStack(AllBlocks.CRUSHING_WHEEL.asItem(), 2)).save(provider);

        // 大型水车（机械合成）
        MechanicalCraftingRecipeBuilder.builder("large_water_wheel")
                .pattern(" AAA ", "ABCBA", "ACDCA", "ABCBA", " AAA ")
                .key('A', GTBlocks.TREATED_WOOD_PLANK.asItem())
                .key('B', ChemicalHelper.get(TagPrefix.screw, GTMaterials.Steel))
                .key('C', ChemicalHelper.get(TagPrefix.ring, GTMaterials.Gold))
                .key('D', AllBlocks.WATER_WHEEL.asItem())
                .output(new ItemStack(AllBlocks.LARGE_WATER_WHEEL.asItem())).save(provider);
    }

    private static void addKineticMechanismRecipes(Consumer<FinishedRecipe> provider) {
        // 基础机构（序列组装）
        SequencedAssemblyRecipeBuilder.builder("basic_mechanism_from_slabs")
                .input(ItemTags.WOODEN_SLABS)
                .transitional(CTPPItems.INCOMPLETE_BASIC_MECHANISM.asStack())
                .result(CTPPItems.BASIC_MECHANISM.asStack())
                .deploying(ChemicalHelper.get(TagPrefix.ingot, CreateMaterials.AndesiteAlloy))
                .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Iron))
                .cutting()
                .loops(1)
                .save(provider);

        // 精密机构（序列组装）
        SequencedAssemblyRecipeBuilder.builder("precision_mechanism_from_basic")
                .input(CTPPItems.BASIC_MECHANISM.asStack())
                .transitional(AllItems.INCOMPLETE_PRECISION_MECHANISM.asStack())
                .result(AllItems.PRECISION_MECHANISM.asStack())
                .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Brass))
                .deploying(AllBlocks.COGWHEEL.asItem())
                .deploying(AllBlocks.LARGE_COGWHEEL.asItem())
                .loops(1)
                .save(provider);

        // 钢铁构件（序列组装：精密构件 + 钢板 + 红石合金板 + 钢螺丝 + 熔融橡胶）
        SequencedAssemblyRecipeBuilder.builder("steel_mechanism_from_precision")
                .input(AllItems.PRECISION_MECHANISM.asStack())
                .transitional(CTPPItems.INCOMPLETE_STEEL_MECHANISM.asStack())
                .result(CTPPItems.STEEL_MECHANISM.asStack())
                .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel))
                .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.RedAlloy))
                .deploying(ChemicalHelper.get(TagPrefix.screw, GTMaterials.Steel))
                .filling(CTPPItems.INCOMPLETE_STEEL_MECHANISM.asStack(), GTMaterials.Rubber.getFluid(576))
                .loops(1)
                .save(provider);
    }

    private static void addEarlyMaterialMixingRecipes(Consumer<FinishedRecipe> provider) {
        // 防腐木板（杂酚油混合）
        MixingRecipeBuilder.builder("treated_wood_planks_from_creosote")
                .result(new ItemStack(GTBlocks.TREATED_WOOD_PLANK.asItem(), 2))
                .inputFluid(GTMaterials.Creosote.getFluid(250))
                .input(ItemTags.PLANKS, 2)
                .save(provider);

        // 红合金粉（加热混合）
        MixingRecipeBuilder.builder("red_alloy_dust")
                .result(ChemicalHelper.get(TagPrefix.dust, GTMaterials.RedAlloy))
                .input(new ItemStack(Items.REDSTONE, 4))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Copper))
                .heatRequirement("heated")
                .save(provider);

        // 青铜粉（加热混合）
        MixingRecipeBuilder.builder("bronze_dust_from_copper_tin")
                .result(new ItemStack(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Bronze).getItem(), 3))
                .input(new ItemStack(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Copper).getItem(), 3))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Tin))
                .heatRequirement("heated")
                .save(provider);
    }

    private static void addSteelPrecursorRecipes(Consumer<FinishedRecipe> provider) {
        // 预制钢粉（锻铁粉与焦炭粉）
        MixingRecipeBuilder.builder("steel_precursor_from_wrought_and_coke")
                .result(ChemicalHelper.get(TagPrefix.dust, UncategorizedMaterials.STEEL_PRECURSOR, 8))
                .input(new ItemStack(ChemicalHelper.get(TagPrefix.dust, GTMaterials.WroughtIron).getItem(), 8))
                .input(new ItemStack(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Coke).getItem(), 3))
                .heatRequirement("heated")
                .save(provider);

        // 预制钢粉（锻铁粉与木炭粉）
        MixingRecipeBuilder.builder("steel_precursor_from_wrought_and_charcoal")
                .result(ChemicalHelper.get(TagPrefix.dust, UncategorizedMaterials.STEEL_PRECURSOR, 8))
                .input(new ItemStack(ChemicalHelper.get(TagPrefix.dust, GTMaterials.WroughtIron).getItem(), 8))
                .input(TagUtil.createItemTag("dusts/charcoal", false), 6)
                .heatRequirement("heated")
                .save(provider);
    }

    private static void addElectronTubeRecipes(Consumer<FinishedRecipe> provider) {
        // 电子管（安山合金板与铁小齿轮）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/electron_tube"),
                AllItems.ELECTRON_TUBE.asStack(1),
                " A ", "BCB", " B ",
                'A', AllItems.POLISHED_ROSE_QUARTZ.asItem(),
                'B', ChemicalHelper.get(TagPrefix.plate, CreateMaterials.AndesiteAlloy),
                'C', ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.Iron));

        // 电子管（玻璃管与铁小齿轮）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/electron_tube_from_glass_tube"),
                AllItems.ELECTRON_TUBE.asStack(4),
                " A ", " C ", " B ",
                'A', AllItems.POLISHED_ROSE_QUARTZ.asItem(),
                'B', ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.Iron),
                'C', GTItems.GLASS_TUBE.asStack());

        // 电子管（玻璃管与钢小齿轮）
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("crafttable/electron_tube_from_glass_tube_steel_gear"),
                AllItems.ELECTRON_TUBE.asStack(6),
                " A ", " C ", " B ",
                'A', AllItems.POLISHED_ROSE_QUARTZ.asItem(),
                'B', ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.Steel),
                'C', GTItems.GLASS_TUBE.asStack());
    }

    private static void addVacuumTubeRecipes(Consumer<FinishedRecipe> provider) {
        // 真空管（真空腔：1 电子管 → 1 真空管）
        VacuumizingRecipeBuilder.builder(CTNHCore.id("vintageimprovements/vacuum_tube_from_electron_tube"))
                .input(AllItems.ELECTRON_TUBE.asStack())
                .result(GTItems.VACUUM_TUBE.asStack())
                .save(provider);
        // 真空管（电子管）
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("vacuum_tube_plain_from_electron_tube"))
                .inputItems(AllItems.ELECTRON_TUBE.asItem())
                .inputItems(TagPrefix.bolt, GTMaterials.Steel)
                .inputItems(TagPrefix.wireGtSingle, GTMaterials.Copper, 2)
                .circuitMeta(1)
                .outputItems(GTItems.VACUUM_TUBE, 4)
                .duration(120)
                .EUt(GTValues.VA[GTValues.ULV])
                .save(provider);

        // 真空管（电子管与红合金）
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("vacuum_tube_red_alloy_from_electron_tube"))
                .inputItems(AllItems.ELECTRON_TUBE.asItem())
                .inputItems(TagPrefix.bolt, GTMaterials.Steel)
                .inputItems(TagPrefix.wireGtSingle, GTMaterials.Copper, 2)
                .inputFluids(GTMaterials.RedAlloy.getFluid(18))
                .outputItems(GTItems.VACUUM_TUBE, 6)
                .duration(40)
                .EUt(GTValues.VA[GTValues.ULV])
                .save(provider);

        // 真空管（电子管、红合金与退火铜）
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(
                CTNHCore.id("vacuum_tube_red_alloy_annealed_from_electron_tube"))
                .inputItems(AllItems.ELECTRON_TUBE.asItem())
                .inputItems(TagPrefix.bolt, GTMaterials.Steel)
                .inputItems(TagPrefix.wireGtSingle, GTMaterials.AnnealedCopper, 2)
                .inputFluids(GTMaterials.RedAlloy.getFluid(18))
                .outputItems(GTItems.VACUUM_TUBE, 8)
                .duration(40)
                .EUt(GTValues.VA[GTValues.ULV])
                .save(provider);
    }

    private static void addFluidPipeRecipes(Consumer<FinishedRecipe> provider) {
        // 流体管道（青铜横向）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("create/fluid_pipe_bronze"),
                AllBlocks.FLUID_PIPE.asStack(4),
                "SCS",
                'S', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Bronze),
                'C', ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Bronze));

        // 流体管道（青铜纵向）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("create/fluid_pipe_bronze_vertical"),
                AllBlocks.FLUID_PIPE.asStack(4),
                "S", "C", "S",
                'S', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Bronze),
                'C', ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Bronze));

        // 流体管道（铜横向）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("create/fluid_pipe_vanilla_copper"),
                AllBlocks.FLUID_PIPE.asStack(),
                "SCS",
                'S', com.gregtechceu.gtceu.api.data.tag.TagUtil.createItemTag("plates/copper", false),
                'C', Items.COPPER_INGOT);

        // 流体管道（铜纵向）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("create/fluid_pipe_vanilla_copper_vertical"),
                AllBlocks.FLUID_PIPE.asStack(),
                "S", "C", "S",
                'S', com.gregtechceu.gtceu.api.data.tag.TagUtil.createItemTag("plates/copper", false),
                'C', Items.COPPER_INGOT);

        // 流体储罐（青铜板）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("create/fluid_tank_bronze"),
                AllBlocks.FLUID_TANK.asStack(2),
                "B", "C", "B",
                'B', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Bronze),
                'C', com.gregtechceu.gtceu.api.data.tag.TagUtil.createItemTag("barrels/wooden", false));
    }

    private static void addCopperCasingRecipes(Consumer<FinishedRecipe> provider) {
        // 铜机壳（青铜锭与去皮原木）
        addCopperCasingRecipe(provider, "copper_casing_from_log", CreateRecipeJsonHelper.tag("forge:stripped_logs"),
                "gtceu:bronze_ingot");

        // 铜机壳（青铜锭与去皮木）
        addCopperCasingRecipe(provider, "copper_casing_from_wood", CreateRecipeJsonHelper.tag("forge:stripped_wood"),
                "gtceu:bronze_ingot");

        // 铜机壳（铜板与安山机壳）
        addCopperCasingRecipe(provider, "copper_casing_from_andesite_casing",
                CreateRecipeJsonHelper.item("create:andesite_casing"), "gtceu:copper_plate");
    }

    private static void addCopperCasingRecipe(Consumer<FinishedRecipe> provider, String name, JsonObject baseIngredient,
                                              String metalIngredient) {
        JsonObject recipe = CreateRecipeJsonHelper.recipe("create:item_application");
        recipe.add("ingredients",
                CreateRecipeJsonHelper.array(baseIngredient, CreateRecipeJsonHelper.item(metalIngredient)));
        recipe.add("results", CreateRecipeJsonHelper.array(CreateRecipeJsonHelper.item("create:copper_casing")));
        CreateRecipeJsonHelper.save(provider, CTNHCore.id("create/" + name).toString(), recipe);
    }

    private static void addCarbonizedLogRecipes(Consumer<FinishedRecipe> provider) {
        // 碳化原木（熔炉：任意原木 → 1 碳化原木）
        VanillaRecipeHelper.addSmeltingRecipe(provider, CTNHCore.id("carbonized_log_from_logs"),
                ItemTags.LOGS, CTNHBlocks.CARBONIZED_LOG.asStack(), 0.1f);

        // 防腐木板（碳化原木 + GT 锯子）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/carbonized_log_to_treated_wood_plank"),
                GTBlocks.TREATED_WOOD_PLANK.asStack(),
                "X", "s",
                'X', CTNHBlocks.CARBONIZED_LOG.asStack());

        // 防腐木板（碳化原木 + Create 动力锯）
        CuttingRecipeBuilder.builder("carbonized_log_to_treated_wood_plank")
                .input(CTNHBlocks.CARBONIZED_LOG.asItem())
                .result(GTBlocks.TREATED_WOOD_PLANK.asStack())
                .save(provider);
    }

    private static void addSteelCasingRecipes(Consumer<FinishedRecipe> provider) {
        // 钢制机壳（防腐木板 + 钢板 → 钢制机壳）
        ItemApplicationRecipeBuilder.builder("steel_casing")
                .input(GTBlocks.TREATED_WOOD_PLANK.asStack())
                .input(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel))
                .result(CTPPBlocks.STEEL_CASING.asStack())
                .save(provider);

        // 重型机械外壳（钢制机壳 + 钢板 → 重型机械外壳）
        ItemApplicationRecipeBuilder.builder("heavy_machinery_casing")
                .input(CTPPBlocks.STEEL_CASING.asStack())
                .input(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel))
                .result(CTPPBlocks.HEAVY_MACHINERY_CASING.asStack())
                .save(provider);
    }
}
