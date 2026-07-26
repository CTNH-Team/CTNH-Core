package io.github.cpearl0.ctnhcore.data.recipe.create;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;

import com.google.gson.JsonObject;
import com.mo_guang.ctpp.data.recipe.builder.create.CompactingRecipeBuilder;
import com.mo_guang.ctpp.data.recipe.builder.create.CuttingRecipeBuilder;
import com.mo_guang.ctpp.registry.CreateMaterials;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import io.github.lounode.ae2cs.common.init.AECSBlocks;

import java.util.function.Consumer;

public class PrimitiveKineticAgeRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        addWroughtIronRecipes(provider);
        addMortarRecipes(provider);
        addRoseQuartzRecipes(provider);
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
