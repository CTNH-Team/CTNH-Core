package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.AdastraMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

import earth.terrarium.adastra.common.registry.ModItems;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLER_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLY_LINE_RECIPES;

public class AdAstraRecipes {

    private static Consumer<FinishedRecipe> output;

    public static void init(Consumer<FinishedRecipe> provider) {
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("heavy_ingot_t1"))
                .inputItems(plateDense, Brass)
                .inputItems(plateDense, Aluminium)
                .inputItems(plateDense, Steel)
                .outputItems(CTNHItems.HEAVY_INGOT_T1)
                .inputFluids(StainlessSteel.getFluid(72))
                .circuitMeta(1)
                .duration(300).EUt(GTValues.VA[GTValues.HV]).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("heavy_ingot_t2"))
                .inputItems(CTNHItems.HEAVY_INGOT_T1)
                .inputItems(plateDense, AdastraMaterials.Desh, 2)
                .outputItems(CTNHItems.HEAVY_INGOT_T2)
                .inputFluids(TungstenSteel.getFluid(72))
                .circuitMeta(1)
                .duration(300).EUt(GTValues.VA[GTValues.EV]).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("heavy_ingot_t3"))
                .inputItems(CTNHItems.HEAVY_INGOT_T2)
                .inputItems(plateDense, AdastraMaterials.Ostrum, 4)
                .outputItems(CTNHItems.HEAVY_INGOT_T3)
                .inputFluids(Platinum.getFluid(72))
                .circuitMeta(1)
                .duration(300).EUt(GTValues.VA[GTValues.IV]).save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("heavy_ingot_t4"))
                .inputItems(CTNHItems.HEAVY_INGOT_T3)
                .inputItems(plateDense, AdastraMaterials.Calorite, 3)
                .inputItems(plateDense, AdastraMaterials.Calorite, 3)
                .inputItems(bolt, Ruridit, 4)
                .outputItems(CTNHItems.HEAVY_INGOT_T4)
                .inputFluids(CTNHMaterials.Cerrobase140.getFluid(36))
                .duration(300).EUt(GTValues.VA[GTValues.LuV]).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("space_helmet_1"))
                .inputItems(Items.CHAINMAIL_HELMET.getDefaultInstance(), ChemicalHelper.get(plate, Glass))
                .outputItems(ModItems.SPACE_HELMET)
                .inputFluids(Glue.getFluid(72))
                .EUt(GTValues.VA[GTValues.MV]).duration(400).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("netherite_space_helmet"))
                .inputItems(ModItems.SPACE_HELMET.get().getDefaultInstance(), CTNHItems.HEAVY_PLATE_T3.asStack(5))
                .outputItems(ModItems.NETHERITE_SPACE_HELMET)
                .inputFluids(StainlessSteel.getFluid(72))
                .EUt(GTValues.VA[GTValues.HV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("netherite_space_suit"))
                .inputItems(
                        ModItems.SPACE_SUIT.get().getDefaultInstance(),
                        new ItemStack(ModItems.STEEL_TANK.get(), 2),
                        CTNHItems.HEAVY_PLATE_T3.asStack(8))
                .outputItems(ModItems.NETHERITE_SPACE_SUIT)
                .inputFluids(StainlessSteel.getFluid(72))
                .EUt(GTValues.VA[GTValues.HV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("netherite_space_pants"))
                .inputItems(ModItems.SPACE_PANTS.get().getDefaultInstance(), CTNHItems.HEAVY_PLATE_T3.asStack(7))
                .outputItems(ModItems.NETHERITE_SPACE_PANTS)
                .inputFluids(StainlessSteel.getFluid(72))
                .EUt(GTValues.VA[GTValues.HV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("netherite_space_boots"))
                .inputItems(ModItems.SPACE_BOOTS.get().getDefaultInstance(), CTNHItems.HEAVY_PLATE_T3.asStack(4))
                .outputItems(ModItems.NETHERITE_SPACE_BOOTS)
                .inputFluids(StainlessSteel.getFluid(72))
                .EUt(GTValues.VA[GTValues.HV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("jet_suit_helmet"))
                .inputItems(ModItems.NETHERITE_SPACE_HELMET.get().getDefaultInstance(),
                        CTNHItems.HEAVY_PLATE_T4.asStack(5))
                .outputItems(ModItems.JET_SUIT_HELMET)
                .inputFluids(Titanium.getFluid(144))
                .EUt(GTValues.VA[GTValues.EV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("jet_suit"))
                .inputItems(
                        ModItems.NETHERITE_SPACE_SUIT.get().getDefaultInstance(),
                        new ItemStack(ModItems.OSTRUM_TANK.get(), 2),
                        GTItems.POWER_THRUSTER_ADVANCED.asStack(2), CTNHItems.HEAVY_PLATE_T4.asStack(8))
                .outputItems(ModItems.JET_SUIT)
                .inputFluids(Titanium.getFluid(144))
                .EUt(GTValues.VA[GTValues.EV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("jet_suit_pants"))
                .inputItems(ModItems.NETHERITE_SPACE_PANTS.get().getDefaultInstance(),
                        CTNHItems.HEAVY_PLATE_T4.asStack(7))
                .outputItems(ModItems.JET_SUIT_PANTS)
                .inputFluids(Titanium.getFluid(144))
                .EUt(GTValues.VA[GTValues.EV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("jet_suit_boots"))
                .inputItems(ModItems.NETHERITE_SPACE_BOOTS.get().getDefaultInstance(),
                        CTNHItems.HEAVY_PLATE_T4.asStack(4))
                .outputItems(ModItems.JET_SUIT_BOOTS)
                .inputFluids(Titanium.getFluid(144))
                .EUt(GTValues.VA[GTValues.EV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("desh_engine"))
                .inputItems(
                        new ItemStack(ModItems.STEEL_TANK.get(), 3), CTNHItems.HEAVY_PLATE_T2.asStack(2),
                        new ItemStack(ModItems.STEEL_ENGINE.get(), 2), CTNHItems.CHIP_T2.asStack())
                .outputItems(ModItems.DESH_ENGINE)
                .inputFluids(Polyethylene.getFluid(144))
                .EUt(GTValues.VA[GTValues.EV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("ostrum_engine"))
                .inputItems(
                        new ItemStack(ModItems.OSTRUM_TANK.get(), 4),
                        CTNHItems.HEAVY_PLATE_T3.asStack(4),
                        CTNHItems.HEAVY_PLATE_T2.asStack(2),
                        new ItemStack(ModItems.DESH_ENGINE.get(), 2),
                        CTNHItems.CHIP_T3.asStack())
                .outputItems(ModItems.OSTRUM_ENGINE)
                .inputFluids(Polytetrafluoroethylene.getFluid(144))
                .EUt(GTValues.VA[GTValues.IV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("ostrum_tank"))
                .inputItems(
                        ModItems.STEEL_TANK.get().getDefaultInstance(),
                        ChemicalHelper.get(plateDouble, Titanium, 5),
                        CTNHItems.CHIP_T3.asStack(4),
                        CTNHItems.HEAVY_PLATE_T2.asStack(2))
                .outputItems(ModItems.OSTRUM_TANK)
                .inputFluids(StainlessSteel.getFluid(144))
                .EUt(GTValues.VA[GTValues.HV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("steel_tank"))
                .inputItems(GTItems.FLUID_CELL_LARGE_STEEL.asStack(), CTNHItems.CHIP_T1.asStack(2))
                .inputFluids(StainlessSteel.getFluid(72))
                .outputItems(ModItems.STEEL_TANK)
                .circuitMeta(1)
                .EUt(GTValues.VA[GTValues.LV]).duration(20).save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("calorite_engine"))
                .inputItems(CTNHItems.HEAVY_PLATE_T4.asStack(32), CTNHItems.HEAVY_PLATE_T3.asStack(16))
                .inputItems(new ItemStack(ModItems.OSTRUM_ENGINE.get(), 8), CTNHItems.CHIP_T4.asStack(2))
                .inputItems(new ItemStack(ModItems.OSTRUM_TANK.get(), 8))
                .outputItems(ModItems.CALORITE_ENGINE)
                .inputFluids(Platinum.getFluid(4032))
                .inputFluids(Iridium.getFluid(2016))
                .inputFluids(Palladium.getFluid(1008))
                .inputFluids(Osmium.getFluid(504))
                .EUt(GTValues.VA[GTValues.LuV]).duration(600).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("rocket_nose_cone"))
                .inputItems(CTNHItems.HEAVY_PLATE_T1.asStack(4), new ItemStack(Items.LIGHTNING_ROD))
                .inputFluids(StainlessSteel.getFluid(36))
                .outputItems(ModItems.ROCKET_NOSE_CONE)
                .circuitMeta(4)
                .EUt(GTValues.VA[GTValues.LV]).duration(50).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("rocket_fin"))
                .inputItems(plateDouble, Steel, 2)
                .inputItems(CTNHItems.HEAVY_PLATE_T1.asStack(4))
                .inputItems(ModItems.ROCKET_NOSE_CONE)
                .outputItems(ModItems.ROCKET_FIN)
                .circuitMeta(5)
                .EUt(GTValues.VA[GTValues.LV]).duration(50).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("steel_engine"))
                .inputItems(CTNHItems.HEAVY_PLATE_T1.asStack(4), new ItemStack(ModItems.STEEL_TANK.get(), 2))
                .inputItems(
                        GTBlocks.FIREBOX_STEEL.asStack(),
                        GTItems.COVER_ACTIVITY_DETECTOR.asStack(),
                        CTNHItems.CHIP_T1.asStack())
                .outputItems(ModItems.STEEL_ENGINE)
                .EUt(GTValues.VA[GTValues.LV]).duration(100).save(provider);
        VanillaRecipeHelper.addShapedRecipe(
                provider, "steel_tank", new ItemStack(ModItems.STEEL_TANK.get()),
                "DhD", "ABA", "DdD",
                'A', CTNHItems.CHIP_T1.asStack(),
                'B', GTItems.FLUID_CELL_LARGE_STEEL.asStack(),
                'D', new MaterialEntry(screw, StainlessSteel));
        VanillaRecipeHelper.addShapedRecipe(
                provider, "rocket_nose_cone", new ItemStack(ModItems.ROCKET_NOSE_CONE.get()),
                "dBh", "ADA", "DDD",
                'A', new MaterialEntry(screw, StainlessSteel),
                'B', new ItemStack(Items.LIGHTNING_ROD),
                'D', CTNHItems.HEAVY_PLATE_T1.asStack());
        VanillaRecipeHelper.addShapedRecipe(
                provider, "rocket_fin", new ItemStack(ModItems.ROCKET_FIN.get()),
                "hAf", "BAB", "BsB",
                'A', new MaterialEntry(plateDouble, StainlessSteel),
                'B', CTNHItems.HEAVY_PLATE_T1.asStack());
        VanillaRecipeHelper.addShapedRecipe(
                provider, "steel_engine", new ItemStack(ModItems.STEEL_ENGINE.get()),
                "DED", "CBC", "DAD",
                'A', GTItems.COVER_ACTIVITY_DETECTOR.asStack(),
                'B', GTBlocks.FIREBOX_STEEL.asStack(),
                'C', new ItemStack(ModItems.STEEL_TANK.get()),
                'D', CTNHItems.HEAVY_PLATE_T1.asStack(),
                'E', CTNHItems.CHIP_T1.asStack());
        VanillaRecipeHelper.addShapedRecipe(
                provider, "space_suit", new ItemStack(ModItems.SPACE_SUIT.get()),
                "ADA", "CBC", "ADA",
                'A', CTNHItems.HEAVY_PLATE_T1.asStack(),
                'B', new ItemStack(ModItems.OXYGEN_GEAR.get()),
                'C', new ItemStack(ModItems.GAS_TANK.get()),
                'D', new MaterialEntry(screw, StainlessSteel));
        VanillaRecipeHelper.addShapedRecipe(
                provider, "oxygen_gear", new ItemStack(ModItems.OXYGEN_GEAR.get()),
                "AEA", "CBC", "ADA",
                'A', new MaterialEntry(plateDouble, Steel),
                'B', GTItems.ELECTRIC_PUMP_HV.asStack(),
                'C', GTItems.FLUID_CELL.asStack(),
                'D', new MaterialEntry(rotor, Steel),
                'E', GTItems.SENSOR_HV);
        VanillaRecipeHelper.addShapedRecipe(
                provider, "space_pants", new ItemStack(ModItems.SPACE_PANTS.get()),
                "AAA", "AhA", "A A",
                'A', CTNHItems.HEAVY_PLATE_T1.asStack());
        VanillaRecipeHelper.addShapedRecipe(
                provider, "space_boots", new ItemStack(ModItems.SPACE_BOOTS.get()),
                "AhA", "A A",
                'A', CTNHItems.HEAVY_PLATE_T1.asStack());
        VanillaRecipeHelper.addShapedRecipe(
                provider, "oxygen_tank", new ItemStack(ModItems.GAS_TANK.get()),
                "AhA", "ACA", "AAA",
                'A', new MaterialEntry(plateDouble, Aluminium),
                'C', GTItems.FLUID_CELL.asStack());
        VanillaRecipeHelper.addShapedRecipe(
                provider, "fan", new ItemStack(ModItems.FAN.get()),
                "AwA", "ECE", "AdA",
                'A', CTNHItems.HEAVY_PLATE_T1.asStack(),
                'C', new MaterialEntry(rodLong, StainlessSteel),
                'E', new MaterialEntry(rotor, Steel));
        VanillaRecipeHelper.addShapedRecipe(
                provider, "oxygen_loader", new ItemStack(ModItems.OXYGEN_LOADER.get()),
                "ABA", "CDE", "FGF",
                'A', new MaterialEntry(plateDouble, Steel),
                'B', new ItemStack(ModItems.OXYGEN_GEAR.get()),
                'C', new ItemStack(Items.IRON_BARS),
                'D', new ItemStack(ModItems.FAN.get()),
                'E', GTItems.ELECTRIC_MOTOR_HV.asStack(),
                'F', new MaterialEntry(plateDouble, Aluminium),
                'G', new MaterialEntry(cableGtDouble, Aluminium));
        VanillaRecipeHelper.addShapedRecipe(
                provider, "nasa_workbench", new ItemStack(ModItems.NASA_WORKBENCH.get()),
                "ABA", "CDC", "BEB",
                'A', new MaterialEntry(rod, Iron),
                'B', new MaterialEntry(plate, Steel),
                'C', new ItemStack(Items.REDSTONE_TORCH),
                'D', new ItemStack(Items.CRAFTING_TABLE),
                'E', new MaterialEntry(block, Steel));
        VanillaRecipeHelper.addShapedRecipe(
                provider, "launch_pad", new ItemStack(ModItems.LAUNCH_PAD.get()),
                "ABA", "BAB", "ABA",
                'A', new MaterialEntry(plate, Steel),
                'B', new MaterialEntry(rod, Platinum));

        stoneRelatedRecipes(provider);
    }

    private static void stoneRelatedRecipes(Consumer<FinishedRecipe> provider) {
        output = provider;

        smelt(ModItems.MOON_STONE, ModItems.MOON_COBBLESTONE);
        smelt(ModItems.MARS_STONE, ModItems.MARS_COBBLESTONE);
        smelt(ModItems.VENUS_STONE, ModItems.VENUS_COBBLESTONE);
        smelt(ModItems.MERCURY_STONE, ModItems.MERCURY_COBBLESTONE);
        smelt(ModItems.GLACIO_STONE, ModItems.GLACIO_COBBLESTONE);

        shaped(ModItems.MOON_STONE_STAIRS, 4, ModItems.MOON_STONE, "#  ", "## ", "###");
        shaped(ModItems.MARS_STONE_STAIRS, 4, ModItems.MARS_STONE, "#  ", "## ", "###");
        shaped(ModItems.VENUS_STONE_STAIRS, 4, ModItems.VENUS_STONE, "#  ", "## ", "###");
        shaped(ModItems.MERCURY_STONE_STAIRS, 4, ModItems.MERCURY_STONE, "#  ", "## ", "###");
        shaped(ModItems.GLACIO_STONE_STAIRS, 4, ModItems.GLACIO_STONE, "#  ", "## ", "###");

        shaped(ModItems.MOON_STONE_SLAB, 6, ModItems.MOON_STONE, "###");
        shaped(ModItems.MARS_STONE_SLAB, 6, ModItems.MARS_STONE, "###");
        shaped(ModItems.VENUS_STONE_SLAB, 6, ModItems.VENUS_STONE, "###");
        shaped(ModItems.MERCURY_STONE_SLAB, 6, ModItems.MERCURY_STONE, "###");
        shaped(ModItems.GLACIO_STONE_SLAB, 6, ModItems.GLACIO_STONE, "###");

        shaped(ModItems.MOON_COBBLESTONE_STAIRS, 4, ModItems.MOON_COBBLESTONE, "#  ", "## ", "###");
        shaped(ModItems.MARS_COBBLESTONE_STAIRS, 4, ModItems.MARS_COBBLESTONE, "#  ", "## ", "###");
        shaped(ModItems.VENUS_COBBLESTONE_STAIRS, 4, ModItems.VENUS_COBBLESTONE, "#  ", "## ", "###");
        shaped(ModItems.MERCURY_COBBLESTONE_STAIRS, 4, ModItems.MERCURY_COBBLESTONE, "#  ", "## ", "###");
        shaped(ModItems.GLACIO_COBBLESTONE_STAIRS, 4, ModItems.GLACIO_COBBLESTONE, "#  ", "## ", "###");

        shaped(ModItems.MOON_COBBLESTONE_SLAB, 6, ModItems.MOON_COBBLESTONE, "###");
        shaped(ModItems.MARS_COBBLESTONE_SLAB, 6, ModItems.MARS_COBBLESTONE, "###");
        shaped(ModItems.VENUS_COBBLESTONE_SLAB, 6, ModItems.VENUS_COBBLESTONE, "###");
        shaped(ModItems.MERCURY_COBBLESTONE_SLAB, 6, ModItems.MERCURY_COBBLESTONE, "###");
        shaped(ModItems.GLACIO_COBBLESTONE_SLAB, 6, ModItems.GLACIO_COBBLESTONE, "###");

        shaped(ModItems.MOON_STONE_BRICKS, 4, ModItems.MOON_STONE, "##", "##");
        shaped(ModItems.MARS_STONE_BRICKS, 4, ModItems.MARS_STONE, "##", "##");
        shaped(ModItems.VENUS_STONE_BRICKS, 4, ModItems.VENUS_STONE, "##", "##");
        shaped(ModItems.MERCURY_STONE_BRICKS, 4, ModItems.MERCURY_STONE, "##", "##");
        shaped(ModItems.GLACIO_STONE_BRICKS, 4, ModItems.GLACIO_STONE, "##", "##");

        shaped(ModItems.MOON_STONE_BRICK_STAIRS, 4, ModItems.MOON_STONE_BRICKS, "#  ", "## ", "###");
        shaped(ModItems.MARS_STONE_BRICK_STAIRS, 4, ModItems.MARS_STONE_BRICKS, "#  ", "## ", "###");
        shaped(ModItems.VENUS_STONE_BRICK_STAIRS, 4, ModItems.VENUS_STONE_BRICKS, "#  ", "## ", "###");
        shaped(ModItems.MERCURY_STONE_BRICK_STAIRS, 4, ModItems.MERCURY_STONE_BRICKS, "#  ", "## ", "###");
        shaped(ModItems.GLACIO_STONE_BRICK_STAIRS, 4, ModItems.GLACIO_STONE_BRICKS, "#  ", "## ", "###");

        shaped(ModItems.MOON_STONE_BRICK_SLAB, 6, ModItems.MOON_STONE_BRICKS, "###");
        shaped(ModItems.MARS_STONE_BRICK_SLAB, 6, ModItems.MARS_STONE_BRICKS, "###");
        shaped(ModItems.VENUS_STONE_BRICK_SLAB, 6, ModItems.VENUS_STONE_BRICKS, "###");
        shaped(ModItems.MERCURY_STONE_BRICK_SLAB, 6, ModItems.MERCURY_STONE_BRICKS, "###");
        shaped(ModItems.GLACIO_STONE_BRICK_SLAB, 6, ModItems.GLACIO_STONE_BRICKS, "###");

        smelt(ModItems.CRACKED_MOON_STONE_BRICKS, ModItems.MOON_STONE_BRICKS);
        smelt(ModItems.CRACKED_MARS_STONE_BRICKS, ModItems.MARS_STONE_BRICKS);
        smelt(ModItems.CRACKED_VENUS_STONE_BRICKS, ModItems.VENUS_STONE_BRICKS);
        smelt(ModItems.CRACKED_MERCURY_STONE_BRICKS, ModItems.MERCURY_STONE_BRICKS);
        smelt(ModItems.CRACKED_GLACIO_STONE_BRICKS, ModItems.GLACIO_STONE_BRICKS);

        shaped(ModItems.CHISELED_MOON_STONE_BRICKS, 1, ModItems.MOON_STONE_BRICK_SLAB, "#", "#");
        shaped(ModItems.CHISELED_MARS_STONE_BRICKS, 1, ModItems.MARS_STONE_BRICK_SLAB, "#", "#");
        shaped(ModItems.CHISELED_VENUS_STONE_BRICKS, 1, ModItems.VENUS_STONE_BRICK_SLAB, "#", "#");
        shaped(ModItems.CHISELED_MERCURY_STONE_BRICKS, 1, ModItems.MERCURY_STONE_BRICK_SLAB, "#", "#");
        shaped(ModItems.CHISELED_GLACIO_STONE_BRICKS, 1, ModItems.GLACIO_STONE_BRICK_SLAB, "#", "#");

        shaped(ModItems.CHISELED_MOON_STONE_STAIRS, 4, ModItems.CHISELED_MOON_STONE_BRICKS, "#  ", "## ", "###");
        shaped(ModItems.CHISELED_MARS_STONE_STAIRS, 4, ModItems.CHISELED_MARS_STONE_BRICKS, "#  ", "## ", "###");
        shaped(ModItems.CHISELED_VENUS_STONE_STAIRS, 4, ModItems.CHISELED_VENUS_STONE_BRICKS, "#  ", "## ", "###");
        shaped(ModItems.CHISELED_MERCURY_STONE_STAIRS, 4, ModItems.CHISELED_MERCURY_STONE_BRICKS, "#  ", "## ", "###");
        shaped(ModItems.CHISELED_GLACIO_STONE_STAIRS, 4, ModItems.CHISELED_GLACIO_STONE_BRICKS, "#  ", "## ", "###");

        shaped(ModItems.CHISELED_MOON_STONE_SLAB, 6, ModItems.CHISELED_MOON_STONE_BRICKS, "###");
        shaped(ModItems.CHISELED_MARS_STONE_SLAB, 6, ModItems.CHISELED_MARS_STONE_BRICKS, "###");
        shaped(ModItems.CHISELED_VENUS_STONE_SLAB, 6, ModItems.CHISELED_VENUS_STONE_BRICKS, "###");
        shaped(ModItems.CHISELED_MERCURY_STONE_SLAB, 6, ModItems.CHISELED_MERCURY_STONE_BRICKS, "###");
        shaped(ModItems.CHISELED_GLACIO_STONE_SLAB, 6, ModItems.CHISELED_GLACIO_STONE_BRICKS, "###");

        shaped(ModItems.POLISHED_MOON_STONE, 4, ModItems.MOON_COBBLESTONE, "##", "##");
        shaped(ModItems.POLISHED_MARS_STONE, 4, ModItems.MARS_COBBLESTONE, "##", "##");
        shaped(ModItems.POLISHED_VENUS_STONE, 4, ModItems.VENUS_COBBLESTONE, "##", "##");
        shaped(ModItems.POLISHED_MERCURY_STONE, 4, ModItems.MERCURY_COBBLESTONE, "##", "##");
        shaped(ModItems.POLISHED_GLACIO_STONE, 4, ModItems.GLACIO_COBBLESTONE, "##", "##");

        shaped(ModItems.POLISHED_MOON_STONE_STAIRS, 4, ModItems.POLISHED_MOON_STONE, "#  ", "## ", "###");
        shaped(ModItems.POLISHED_MARS_STONE_STAIRS, 4, ModItems.POLISHED_MARS_STONE, "#  ", "## ", "###");
        shaped(ModItems.POLISHED_VENUS_STONE_STAIRS, 4, ModItems.POLISHED_VENUS_STONE, "#  ", "## ", "###");
        shaped(ModItems.POLISHED_MERCURY_STONE_STAIRS, 4, ModItems.POLISHED_MERCURY_STONE, "#  ", "## ", "###");
        shaped(ModItems.POLISHED_GLACIO_STONE_STAIRS, 4, ModItems.POLISHED_GLACIO_STONE, "#  ", "## ", "###");

        shaped(ModItems.POLISHED_MOON_STONE_SLAB, 6, ModItems.POLISHED_MOON_STONE, "###");
        shaped(ModItems.POLISHED_MARS_STONE_SLAB, 6, ModItems.POLISHED_MARS_STONE, "###");
        shaped(ModItems.POLISHED_VENUS_STONE_SLAB, 6, ModItems.POLISHED_VENUS_STONE, "###");
        shaped(ModItems.POLISHED_MERCURY_STONE_SLAB, 6, ModItems.POLISHED_MERCURY_STONE, "###");
        shaped(ModItems.POLISHED_GLACIO_STONE_SLAB, 6, ModItems.POLISHED_GLACIO_STONE, "###");

        shaped(ModItems.MOON_PILLAR, 2, ModItems.MOON_STONE, "#", "#");
        shaped(ModItems.MARS_PILLAR, 2, ModItems.MARS_STONE, "#", "#");
        shaped(ModItems.VENUS_PILLAR, 2, ModItems.VENUS_STONE, "#", "#");
        shaped(ModItems.MERCURY_PILLAR, 2, ModItems.MERCURY_STONE, "#", "#");
        shaped(ModItems.GLACIO_PILLAR, 2, ModItems.GLACIO_STONE, "#", "#");

        shaped(ModItems.MOON_STONE_BRICK_WALL, 6, ModItems.MOON_STONE_BRICKS, "###", "###");
        shaped(ModItems.MARS_STONE_BRICK_WALL, 6, ModItems.MARS_STONE_BRICKS, "###", "###");
        shaped(ModItems.VENUS_STONE_BRICK_WALL, 6, ModItems.VENUS_STONE_BRICKS, "###", "###");
        shaped(ModItems.MERCURY_STONE_BRICK_WALL, 6, ModItems.MERCURY_STONE_BRICKS, "###", "###");
        shaped(ModItems.GLACIO_STONE_BRICK_WALL, 6, ModItems.GLACIO_STONE_BRICKS, "###", "###");

        shaped(ModItems.POLISHED_CONGLOMERATE, 4, ModItems.CONGLOMERATE, "##", "##");

        shaped(ModItems.VENUS_SANDSTONE, 4, ModItems.VENUS_SAND, "##", "##");
        shaped(ModItems.VENUS_SANDSTONE_BRICKS, 4, ModItems.VENUS_SANDSTONE, "##", "##");
        shaped(ModItems.VENUS_SANDSTONE_BRICK_STAIRS, 4, ModItems.VENUS_SANDSTONE_BRICKS, "#  ", "## ", "###");
        shaped(ModItems.VENUS_SANDSTONE_BRICK_SLAB, 6, ModItems.VENUS_SANDSTONE_BRICKS, "###");
        smelt(ModItems.CRACKED_VENUS_SANDSTONE_BRICKS, ModItems.VENUS_SANDSTONE_BRICKS);
    }

    private static void smelt(Supplier<Item> result, Supplier<Item> mainItem) {
        VanillaRecipeHelper.addSmeltingRecipe(
                output,
                CTNHCore.id(
                        "smelting/%s_from_smelting_%s".formatted(ForgeRegistries.ITEMS.getKey(result.get()).getPath(),
                                ForgeRegistries.ITEMS.getKey(mainItem.get()).getPath())),
                new ItemStack(mainItem.get()), new ItemStack(result.get()), 0.1f);
    }

    private static void shaped(Supplier<Item> result, int count, Supplier<Item> mainItem, String... pattern) {
        VanillaRecipeHelper.addShapedRecipe(
                output, CTNHCore.id(ForgeRegistries.ITEMS.getKey(result.get()).getPath()),
                new ItemStack(result.get(), count),
                pattern, '#', new ItemStack(mainItem.get()));
    }
}
