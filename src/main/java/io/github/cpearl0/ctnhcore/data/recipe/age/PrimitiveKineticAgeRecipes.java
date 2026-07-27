package io.github.cpearl0.ctnhcore.data.recipe.age;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.UncategorizedMaterials;
import io.github.cpearl0.ctnhcore.data.recipe.create.CreateRecipeJsonHelper;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import com.google.gson.JsonObject;
import com.mo_guang.ctpp.data.recipe.builder.create.CompactingRecipeBuilder;
import com.mo_guang.ctpp.data.recipe.builder.create.CuttingRecipeBuilder;
import com.mo_guang.ctpp.data.recipe.builder.create.MechanicalCraftingRecipeBuilder;
import com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder;
import com.mo_guang.ctpp.data.recipe.builder.create.SequencedAssemblyRecipeBuilder;
import com.mo_guang.ctpp.registry.CTPPItems;
import com.mo_guang.ctpp.registry.CreateMaterials;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import io.github.lounode.ae2cs.common.init.AECSBlocks;
import vazkii.botania.common.item.BotaniaItems;

import java.util.function.Consumer;

public class PrimitiveKineticAgeRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        addWroughtIronRecipes(provider);
        addMortarRecipes(provider);
        addAndesiteAlloyRecipes(provider);
        addRoseQuartzRecipes(provider);
        addKineticCraftingRecipes(provider);
        addKineticMechanicalCraftingRecipes(provider);
        addKineticMechanismRecipes(provider);
        addEarlyMaterialMixingRecipes(provider);
        addSteelPrecursorRecipes(provider);
        addElectronTubeRecipes(provider);
        addVacuumTubeRecipes(provider);
        addFluidPipeRecipes(provider);
        addCopperCasingRecipes(provider);
    }

    private static void addWroughtIronRecipes(Consumer<FinishedRecipe> provider) {
        // 锻铁锭（GT 锻造锤）
        GTRecipeTypes.FORGE_HAMMER_RECIPES.recipeBuilder(CTNHCore.id("hot_wrought_iron_ingot_fixed"))
                .EUt(8)
                .duration(100)
                .inputItems(TagPrefix.ingotHot, GTMaterials.WroughtIron)
                .outputItems(TagPrefix.ingot, GTMaterials.WroughtIron)
                .save(provider);

        // 锻铁锭（工作台锻造锤）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("shaped/wrought_iron_ingot_from_hot"),
                ChemicalHelper.get(TagPrefix.ingot, GTMaterials.WroughtIron),
                "hA",
                'A', ChemicalHelper.get(TagPrefix.ingotHot, GTMaterials.WroughtIron));

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

    private static void addKineticCraftingRecipes(Consumer<FinishedRecipe> provider) {
        // 动力轴（安山合金锭）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/shaft"), AllBlocks.SHAFT.asStack(4),
                "A", "A", "A",
                'A', ChemicalHelper.get(TagPrefix.ingot, CreateMaterials.AndesiteAlloy));

        // 动力轴（安山合金锭切割）
        ItemStack andesiteAlloyIngot = ChemicalHelper.get(TagPrefix.ingot, CreateMaterials.AndesiteAlloy);
        ItemStack shaft = new ItemStack(AllBlocks.SHAFT.asItem());
        if (!andesiteAlloyIngot.isEmpty() && !shaft.isEmpty()) {
            CuttingRecipeBuilder.builder("cutting_shaft_from_andesite_alloy_ingot")
                    .input(andesiteAlloyIngot)
                    .result(new ItemStack(shaft.getItem(), 2))
                    .save(provider);
        }

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
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/cogwheel"), AllBlocks.COGWHEEL.asStack(4),
                " A ", "ABA", " A ",
                'A', ItemTags.PLANKS,
                'B', ChemicalHelper.get(TagPrefix.gearSmall, CreateMaterials.AndesiteAlloy));

        // 大型齿轮
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/large_cogwheel"),
                AllBlocks.LARGE_COGWHEEL.asStack(4),
                " A ", "ABA", " A ",
                'A', GTBlocks.TREATED_WOOD_PLANK.asStack(),
                'B', ChemicalHelper.get(TagPrefix.gear, CreateMaterials.AndesiteAlloy));

        // 水车
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/water_wheel"), AllBlocks.WATER_WHEEL.asStack(),
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
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/millstone"), AllBlocks.MILLSTONE.asStack(),
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
                .key('A', new ItemStack(AllBlocks.SHAFT.asItem()))
                .key('B', ChemicalHelper.get(TagPrefix.screw, GTMaterials.Steel))
                .key('C', ChemicalHelper.get(TagPrefix.ring, GTMaterials.Gold))
                .key('D', AllBlocks.WATER_WHEEL.asItem())
                .output(new ItemStack(AllBlocks.LARGE_WATER_WHEEL.asItem())).save(provider);
    }

    private static void addKineticMechanismRecipes(Consumer<FinishedRecipe> provider) {
        // 基础机构（序列组装）
        ItemStack incompleteBasic = CTPPItems.INCOMPLETE_BASIC_MECHANISM.asStack();
        ItemStack basicMechanism = CTPPItems.BASIC_MECHANISM.asStack();
        if (!incompleteBasic.isEmpty() && !basicMechanism.isEmpty()) {
            SequencedAssemblyRecipeBuilder.builder("basic_mechanism_from_slabs")
                    .input(ItemTags.WOODEN_SLABS)
                    .transitional(incompleteBasic)
                    .result(basicMechanism)
                    .deploying(ChemicalHelper.get(TagPrefix.ingot, CreateMaterials.AndesiteAlloy))
                    .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Iron))
                    .cutting()
                    .loops(1)
                    .save(provider);
        }

        // 精密机构（序列组装）
        ItemStack incompletePrecision = AllItems.INCOMPLETE_PRECISION_MECHANISM.asStack();
        ItemStack precision = AllItems.PRECISION_MECHANISM.asStack();
        if (!incompletePrecision.isEmpty() && !precision.isEmpty() && !basicMechanism.isEmpty()) {
            SequencedAssemblyRecipeBuilder.builder("precision_mechanism_from_basic")
                    .input(basicMechanism)
                    .transitional(incompletePrecision)
                    .result(precision)
                    .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Brass))
                    .deploying(AllBlocks.COGWHEEL.asItem())
                    .deploying(AllBlocks.LARGE_COGWHEEL.asItem())
                    .loops(1)
                    .save(provider);
        }
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
        ItemStack steelPrecursorDust = ChemicalHelper.get(TagPrefix.dust, UncategorizedMaterials.STEEL_PRECURSOR, 8);
        if (!steelPrecursorDust.isEmpty()) {
            // 预制钢粉（锻铁粉与焦炭粉）
            MixingRecipeBuilder.builder("steel_precursor_from_wrought_and_coke")
                    .result(new ItemStack(steelPrecursorDust.getItem(), 8))
                    .input(new ItemStack(ChemicalHelper.get(TagPrefix.dust, GTMaterials.WroughtIron).getItem(), 8))
                    .input(new ItemStack(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Coke).getItem(), 3))
                    .heatRequirement("heated")
                    .save(provider);

            // 预制钢粉（锻铁粉与木炭粉）
            MixingRecipeBuilder.builder("steel_precursor_from_wrought_and_charcoal")
                    .result(new ItemStack(steelPrecursorDust.getItem(), 8))
                    .input(new ItemStack(ChemicalHelper.get(TagPrefix.dust, GTMaterials.WroughtIron).getItem(), 8))
                    .input(TagUtil.createItemTag("dusts/charcoal", false), 6)
                    .heatRequirement("heated")
                    .save(provider);
        }
    }

    private static void addElectronTubeRecipes(Consumer<FinishedRecipe> provider) {
        // 电子管（安山合金板与铁小齿轮）
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/electron_tube"),
                AllItems.ELECTRON_TUBE.asStack(4),
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
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/electron_tube_from_glass_tube_steel_gear"),
                AllItems.ELECTRON_TUBE.asStack(6),
                " A ", " C ", " B ",
                'A', AllItems.POLISHED_ROSE_QUARTZ.asItem(),
                'B', ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.Steel),
                'C', GTItems.GLASS_TUBE.asStack());
    }

    private static void addVacuumTubeRecipes(Consumer<FinishedRecipe> provider) {
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
        recipe.add("ingredients", CreateRecipeJsonHelper.array(baseIngredient, CreateRecipeJsonHelper.item(metalIngredient)));
        recipe.add("results", CreateRecipeJsonHelper.array(CreateRecipeJsonHelper.item("create:copper_casing")));
        CreateRecipeJsonHelper.save(provider, CTNHCore.id("create/" + name).toString(), recipe);
    }
}
