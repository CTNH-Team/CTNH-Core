package io.github.cpearl0.ctnhcore.data.recipe.create;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.BiodieselFertileSoilMaterials;
import com.mo_guang.ctpp.registry.CreateMaterials;
import io.github.cpearl0.ctnhcore.data.materials.EnderIOMaterials;
import io.github.cpearl0.ctnhcore.data.materials.SpecialMaterials;
import io.github.cpearl0.ctnhcore.data.materials.UncategorizedMaterials;
import io.github.cpearl0.ctnhcore.data.recipe.RecipeRemoval;
import io.github.cpearl0.ctnhcore.data.recipe.RecipeRemoval.RemoveFilter;
import io.github.cpearl0.ctnhcore.registry.CTNHBlocks;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksA;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jesz.createdieselgenerators.CDGBlocks;
import com.jesz.createdieselgenerators.CDGItems;
import com.jesz.createdieselgenerators.CreateDieselGenerators;
import com.mo_guang.ctpp.common.recipe.builder.create.*;
import com.mo_guang.ctpp.common.recipe.builder.create.diesel.BasinFermentingRecipeBuilder;
import com.mo_guang.ctpp.common.recipe.builder.create.diesel.DistillationRecipeBuilder;
import com.mo_guang.ctpp.registry.CTPPBlocks;
import com.mo_guang.ctpp.registry.CTPPItems;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.processing.recipe.HeatCondition;

import java.util.Objects;
import java.util.function.Consumer;

public class CreateRecipes {

    public static void createRemovals() {
        RecipeRemoval.remove(new RemoveFilter().id("create:pressing/copper_ingot"));
        RecipeRemoval.remove(new RemoveFilter().id("create:pressing/brass_ingot"));
        RecipeRemoval.remove(new RemoveFilter().id("create:pressing/gold_ingot"));
        RecipeRemoval.remove(new RemoveFilter().type("create:automatic_brewing"));
        RecipeRemoval.remove(new RemoveFilter().id("create:pressing/iron_ingot"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/materials/andesite_alloy_from_zinc"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/materials/andesite_alloy"));
        RecipeRemoval.remove(new RemoveFilter().id("create:pressing/sugar_cane"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/water_wheel"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/large_water_wheel"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/mechanical_drill"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/mechanical_mixer"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/materials/rose_quartz"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/materials/electron_tube"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/materials/copper_ingot"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/deployer"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/contraption_controls"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/appliances/slime_ball"));
        RecipeRemoval.remove(new RemoveFilter().id("create:mechanical_crafting/crushing_wheel"));
        RecipeRemoval.remove(new RemoveFilter().id("create:milling/charcoal"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/mechanical_bearing"));
        RecipeRemoval.remove(new RemoveFilter().id("create:mixing/brass_ingot"));
        RecipeRemoval.remove(new RemoveFilter().id("create:pressing/juperium_ingot"));
        RecipeRemoval.remove(new RemoveFilter().id("create:pressing/saturlyte_ingot"));
        RecipeRemoval.remove(new RemoveFilter().id("create:pressing/radium_ingot"));
        RecipeRemoval.remove(new RemoveFilter().id("create:pressing/electrolyte_ingot"));
        RecipeRemoval.remove(new RemoveFilter().id("create:pressing/plutonium_ingot"));
        RecipeRemoval.remove(new RemoveFilter().id("create:pressing/neptunium_ingot"));
        RecipeRemoval.remove(new RemoveFilter().id("create:pressing/uranium_ingot"));
        RecipeRemoval.remove(new RemoveFilter().id("create:pressing/desh_ingot"));
        RecipeRemoval.remove(new RemoveFilter().id("create:pressing/ostrum_ingot"));
        RecipeRemoval.remove(new RemoveFilter().id("create:pressing/steel_ingot"));
        RecipeRemoval.remove(new RemoveFilter().id("create:pressing/calorite_ingot"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/materials/andesite_alloy_from_block"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/materials/andesite_alloy_block"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/cart_assembler"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/portable_storage_interface"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/rotation_speed_controller"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/belt_connector"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/goggles"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/shaft"));
        RecipeRemoval.remove(new RemoveFilter().id("create:cutting/andesite_alloy"));
        RecipeRemoval.remove(new RemoveFilter().id("create:sequenced_assembly/precision_mechanism"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/encased_fan"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/windmill_bearing"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/depot"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/mechanical_press"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/large_cogwheel"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/large_cogwheel_from_little"));
        RecipeRemoval.remove(new RemoveFilter().id("create:deploying/large_cogwheel"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/spout"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/cogwheel"));
        RecipeRemoval.remove(new RemoveFilter().id("create:deploying/cogwheel"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/millstone"));
        RecipeRemoval.remove(new RemoveFilter().id("create:mixing/andesite_alloy"));
        RecipeRemoval.remove(new RemoveFilter().id("create:mixing/andesite_alloy_from_zinc"));
        RecipeRemoval.remove(new RemoveFilter().id("create:milling/calcite"));
        RecipeRemoval.remove(new RemoveFilter().id("create:item_application/copper_casing_from_log"));
        RecipeRemoval.remove(new RemoveFilter().id("create:item_application/copper_casing_from_wood"));
    }

    public static void init(Consumer<FinishedRecipe> provider) {
        addCopperCasingRecipes(provider);

        // Crushing/milling for gtceu ingots -> dusts
        String[] ingots = new String[] { "tin", "bronze", "zinc", "brass", "nickel", "lead" };
        for (String i : ingots) {
            ItemStack ingot = switch (i) {
                case "tin" -> ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Tin);
                case "bronze" -> ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Bronze);
                case "zinc" -> ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Zinc);
                case "brass" -> ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Brass);
                case "nickel" -> ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Nickel);
                case "lead" -> ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Lead);
                default -> ItemStack.EMPTY;
            };
            ItemStack dust = switch (i) {
                case "tin" -> ChemicalHelper.get(TagPrefix.dust, GTMaterials.Tin);
                case "bronze" -> ChemicalHelper.get(TagPrefix.dust, GTMaterials.Bronze);
                case "zinc" -> ChemicalHelper.get(TagPrefix.dust, GTMaterials.Zinc);
                case "brass" -> ChemicalHelper.get(TagPrefix.dust, GTMaterials.Brass);
                case "nickel" -> ChemicalHelper.get(TagPrefix.dust, GTMaterials.Nickel);
                case "lead" -> ChemicalHelper.get(TagPrefix.dust, GTMaterials.Lead);
                default -> ItemStack.EMPTY;
            };
            if (!ingot.isEmpty() && !dust.isEmpty()) {
                CrushingRecipeBuilder.builder("crushing_" + i + "_dust").input(ingot).output(dust).save(provider);
                MillingRecipeBuilder.builder("milling_" + i + "_dust").input(ingot).output(dust).save(provider);
            }
        }

        // andesite_alloy
        ItemStack aaIngot = ChemicalHelper.get(TagPrefix.ingot, CreateMaterials.AndesiteAlloy);
        ItemStack aaDust = ChemicalHelper.get(TagPrefix.dust, CreateMaterials.AndesiteAlloy);
        if (!aaIngot.isEmpty() && !aaDust.isEmpty()) {
            CrushingRecipeBuilder.builder("crushing_andesite_alloy_dust").input(aaIngot).output(aaDust).save(provider);
            MillingRecipeBuilder.builder("milling_andesite_alloy_dust").input(aaIngot).output(aaDust).save(provider);
        }

        // coke
        ItemStack cokeGem = ChemicalHelper.get(TagPrefix.gem, GTMaterials.Coke);
        ItemStack cokeDust = ChemicalHelper.get(TagPrefix.dust, GTMaterials.Coke);
        if (!cokeGem.isEmpty() && !cokeDust.isEmpty()) {
            CrushingRecipeBuilder.builder("crushing_coke_dust").input(cokeGem).output(cokeDust).save(provider);
            MillingRecipeBuilder.builder("milling_coke_dust").input(cokeGem).output(cokeDust).save(provider);
        }

        // copper/iron/gold gtceu -> minecraft ingots
        String[] vanillaIngots = new String[] { "copper", "iron", "gold" };
        for (String i : vanillaIngots) {
            ItemStack gtIngot = switch (i) {
                case "copper" -> ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Copper);
                case "iron" -> ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Iron);
                case "gold" -> ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Gold);
                default -> ItemStack.EMPTY;
            };
            ItemStack mcIngot = switch (i) {
                case "copper" -> new ItemStack(Items.COPPER_INGOT);
                case "iron" -> new ItemStack(Items.IRON_INGOT);
                case "gold" -> new ItemStack(Items.GOLD_INGOT);
                default -> ItemStack.EMPTY;
            };
            if (!gtIngot.isEmpty() && !mcIngot.isEmpty()) {
                CrushingRecipeBuilder.builder("crushing_gtceu_" + i + "_to_mc").input(gtIngot).output(mcIngot)
                        .save(provider);
                MillingRecipeBuilder.builder("milling_gtceu_" + i + "_to_mc").input(gtIngot).output(mcIngot)
                        .save(provider);
            }
        }

        // Cutting: shaft from andesite_alloy_ingot
        ItemStack shaft2 = new ItemStack(AllBlocks.SHAFT.asItem());
        if (!aaIngot.isEmpty() && !shaft2.isEmpty()) {
            // produce 2x shaft
            CuttingRecipeBuilder.builder("cutting_shaft_from_andesite_alloy_ingot").input(aaIngot)
                    .result(new ItemStack(shaft2.getItem(), 2)).save(provider);
        }

        // Cutting plates -> single_wire (produce 2x)
        String[] plates = new String[] { "copper", "iron", "gold", "lead", "nickel", "tin", "silver", "annealed_copper",
                "cupronickel", "steel", "red_alloy", "mana_steel", "conductive_alloy" };
        for (String p : plates) {
            ItemStack plate = switch (p) {
                case "copper" -> ChemicalHelper.get(TagPrefix.plate, GTMaterials.Copper);
                case "iron" -> ChemicalHelper.get(TagPrefix.plate, GTMaterials.Iron);
                case "gold" -> ChemicalHelper.get(TagPrefix.plate, GTMaterials.Gold);
                case "lead" -> ChemicalHelper.get(TagPrefix.plate, GTMaterials.Lead);
                case "nickel" -> ChemicalHelper.get(TagPrefix.plate, GTMaterials.Nickel);
                case "tin" -> ChemicalHelper.get(TagPrefix.plate, GTMaterials.Tin);
                case "silver" -> ChemicalHelper.get(TagPrefix.plate, GTMaterials.Silver);
                case "annealed_copper" -> ChemicalHelper.get(TagPrefix.plate, GTMaterials.AnnealedCopper);
                case "cupronickel" -> ChemicalHelper.get(TagPrefix.plate, GTMaterials.Cupronickel);
                case "steel" -> ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel);
                case "red_alloy" -> ChemicalHelper.get(TagPrefix.plate, GTMaterials.RedAlloy);
                case "mana_steel" -> ChemicalHelper.get(TagPrefix.plate, CreateMaterials.AndesiteAlloy);
                case "conductive_alloy" -> ChemicalHelper.get(TagPrefix.plate, EnderIOMaterials.ConductiveAlloy);
                default -> ItemStack.EMPTY;
            };
            ItemStack wire = switch (p) {
                case "copper" -> ChemicalHelper.get(TagPrefix.wireGtSingle, GTMaterials.Copper);
                case "iron" -> ChemicalHelper.get(TagPrefix.wireGtSingle, GTMaterials.Iron);
                case "gold" -> ChemicalHelper.get(TagPrefix.wireGtSingle, GTMaterials.Gold);
                case "lead" -> ChemicalHelper.get(TagPrefix.wireGtSingle, GTMaterials.Lead);
                case "nickel" -> ChemicalHelper.get(TagPrefix.wireGtSingle, GTMaterials.Nickel);
                case "tin" -> ChemicalHelper.get(TagPrefix.wireGtSingle, GTMaterials.Tin);
                case "silver" -> ChemicalHelper.get(TagPrefix.wireGtSingle, GTMaterials.Silver);
                case "annealed_copper" -> ChemicalHelper.get(TagPrefix.wireGtSingle, GTMaterials.AnnealedCopper);
                case "cupronickel" -> ChemicalHelper.get(TagPrefix.wireGtSingle, GTMaterials.Cupronickel);
                case "steel" -> ChemicalHelper.get(TagPrefix.wireGtSingle, GTMaterials.Steel);
                case "red_alloy" -> ChemicalHelper.get(TagPrefix.wireGtSingle, GTMaterials.RedAlloy);
                case "mana_steel" -> ChemicalHelper.get(TagPrefix.wireGtSingle, CreateMaterials.AndesiteAlloy);
                case "conductive_alloy" -> ChemicalHelper.get(TagPrefix.wireGtSingle, EnderIOMaterials.ConductiveAlloy);
                default -> ItemStack.EMPTY;
            };
            if (!plate.isEmpty() && !wire.isEmpty()) {
                CuttingRecipeBuilder.builder("cutting_" + p + "_to_single_wire").input(plate)
                        .result(new ItemStack(wire.getItem(), 2)).save(provider);
            }
        }

        // Mechanical crafting (register with basic ingredient set)
        // encased fan - full 5x5 pattern from create.js
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

        // crushing wheel (2x) - 5x5 pattern from create.js
        MechanicalCraftingRecipeBuilder.builder("crushing_wheel")
                .pattern(" AAA ", "ABCBA", "ACDCA", "ABCBA", " AAA ")
                .key('A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.WroughtIron))
                .key('B', ChemicalHelper.get(TagPrefix.plate, CreateMaterials.AndesiteAlloy))
                .key('C', ChemicalHelper.get(TagPrefix.rod, GTMaterials.Iron))
                .key('D', ChemicalHelper.get(TagPrefix.gear, GTMaterials.WroughtIron))
                .output(new ItemStack(AllBlocks.CRUSHING_WHEEL.asItem(), 2)).save(provider);

        // generator coil - 5x5 pattern from create.js
        MechanicalCraftingRecipeBuilder.builder("generator_coil")
                .pattern("  A  ", " BCB ", "ACDCA", " BCB ", "  A  ")
                .key('A', ChemicalHelper.get(TagPrefix.wireFine, GTMaterials.Copper))
                .key('B', ChemicalHelper.get(TagPrefix.plate, CreateMaterials.AndesiteAlloy))
                .key('C', GTItems.BASIC_CIRCUIT_BOARD.asStack())
                .key('D', AllItems.PRECISION_MECHANISM.asItem())
                .output(new ItemStack(AllItems.PRECISION_MECHANISM.asItem())).save(provider);

        // large water wheel - 5x5 pattern from create.js
        MechanicalCraftingRecipeBuilder.builder("large_water_wheel")
                .pattern(" AAA ", "ABCBA", "ACDCA", "ABCBA", " AAA ")
                .key('A', new ItemStack(AllBlocks.SHAFT.asItem()))
                .key('B', ChemicalHelper.get(TagPrefix.screw, GTMaterials.Steel))
                .key('C', ChemicalHelper.get(TagPrefix.ring, GTMaterials.Gold))
                .key('D', AllBlocks.WATER_WHEEL.asItem())
                .output(new ItemStack(AllBlocks.LARGE_WATER_WHEEL.asItem())).save(provider);

        // portal block (from server_scripts create.js)
        ItemStack doubleShadowSteelPlate = ChemicalHelper.get(TagPrefix.plateDouble, CreateMaterials.ShadowSteel);
        if (!doubleShadowSteelPlate.isEmpty()) {
            MechanicalCraftingRecipeBuilder.builder("javd_portal_block")
                    .pattern("AAAAA", "ABCBA", "ACDCA", "ABCBA", "AAAAA")
                    .key('A', doubleShadowSteelPlate)
                    .key('B', CustomTags.HV_CIRCUITS)
                    .key('C', ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.BlackSteel))
                    .key('D', GTBlocks.MACHINE_CASING_HV.asStack())
                    .output(AllBlocks.ENCASED_FAN.asStack()).save(provider);
        }

        // martial morality eye (7x7 pattern from server_scripts create.js)
        ItemStack drillingMachine = item("createoreexcavation:drilling_machine") == null ? ItemStack.EMPTY :
                new ItemStack(item("createoreexcavation:drilling_machine"));
        ItemStack martialMoralityEye = MultiblocksA.MARTIAL_MORALITY_EYE.asStack();
        if (!drillingMachine.isEmpty() && !martialMoralityEye.isEmpty()) {
            MechanicalCraftingRecipeBuilder.builder("martial_morality_eye")
                    .pattern("ABCCCBA", "BADCDAB", "BADCDAB", "BAAEAAB", "BADCDAB", "BADCDAB", "ABCCCBA")
                    .key('A', GTMachines.STEAM_ROCK_CRUSHER.left().asStack())
                    .key('B', CTPPBlocks.HEAVY_MACHINERY_CASING.asStack())
                    .key('C', AllItems.PRECISION_MECHANISM.asItem())
                    .key('D', drillingMachine)
                    .key('E', GTMachines.STEAM_ROCK_CRUSHER.right().asStack())
                    .output(martialMoralityEye).save(provider);
        }

        // compacting: many plates
        String[] compactIngots = new String[] { "iron", "copper", "gold", "zinc", "brass", "wrought_iron", "steel",
                "rubber", "red_alloy", "andesite_alloy", "bronze", "potin", "nickel", "tin", "mana_steel" };
        for (String i : compactIngots) {
            ItemStack plate = switch (i) {
                case "tin" -> ChemicalHelper.get(TagPrefix.plate, GTMaterials.Tin);
                case "bronze" -> ChemicalHelper.get(TagPrefix.plate, GTMaterials.Bronze);
                case "zinc" -> ChemicalHelper.get(TagPrefix.plate, GTMaterials.Zinc);
                case "brass" -> ChemicalHelper.get(TagPrefix.plate, GTMaterials.Brass);
                case "nickel" -> ChemicalHelper.get(TagPrefix.plate, GTMaterials.Nickel);
                case "lead" -> ChemicalHelper.get(TagPrefix.plate, GTMaterials.Lead);
                default -> ItemStack.EMPTY;
            };
            if (!plate.isEmpty()) {
                ItemStack maybeIngot = switch (i) {
                    case "tin" -> ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Tin);
                    case "bronze" -> ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Bronze);
                    case "zinc" -> ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Zinc);
                    case "brass" -> ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Brass);
                    case "nickel" -> ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Nickel);
                    case "lead" -> ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Lead);
                    default -> ItemStack.EMPTY;
                };
                if (!maybeIngot.isEmpty()) CompactingRecipeBuilder.builder("compacting_" + i + "_plate")
                        .input(maybeIngot).output(plate).save(provider);
            }
        }

        // pressing rings
        CompactingRecipeBuilder.builder("pressing_gold_ring")
                .input(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Gold))
                .output(ChemicalHelper.get(TagPrefix.ring, GTMaterials.Gold)).save(provider);
        CompactingRecipeBuilder.builder("pressing_iron_ring")
                .input(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Iron))
                .output(ChemicalHelper.get(TagPrefix.ring, GTMaterials.Iron)).save(provider);
        CompactingRecipeBuilder.builder("pressing_copper_ring")
                .input(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Copper))
                .output(ChemicalHelper.get(TagPrefix.ring, GTMaterials.Copper)).save(provider);

        // Mixing recipes from create.js
        // 8x potin dust
        ItemStack copperDust = ChemicalHelper.get(TagPrefix.dust, GTMaterials.Copper);
        ItemStack tinDust = ChemicalHelper.get(TagPrefix.dust, GTMaterials.Tin);
        ItemStack leadDust = ChemicalHelper.get(TagPrefix.dust, GTMaterials.Lead);
        ItemStack potinDust = ChemicalHelper.get(TagPrefix.dust, GTMaterials.Potin);
        addHeatedQuartzGlassMixing(provider);
        if (!copperDust.isEmpty() && !tinDust.isEmpty() && !leadDust.isEmpty() && !potinDust.isEmpty()) {
            MixingRecipeBuilder.builder("potin_from_dusts").input(new ItemStack(copperDust.getItem(), 6))
                    .input(new ItemStack(tinDust.getItem(), 2))
                    .input(leadDust)
                    .output(new ItemStack(potinDust.getItem(), 8)).save(provider);
        }

        // rose quartz from quartz + redstone
        ItemStack quartz = new ItemStack(Items.QUARTZ);
        ItemStack redstone = new ItemStack(Items.REDSTONE);
        ItemStack roseQuartz = AllItems.ROSE_QUARTZ.asStack();
        if (!quartz.isEmpty() && !redstone.isEmpty() && !roseQuartz.isEmpty()) {
            MixingRecipeBuilder.builder("rose_quartz_from_quartz_redstone").input(quartz)
                    .input(new ItemStack(redstone.getItem(), 4))
                    .output(roseQuartz).save(provider);
        }

        // rose quartz from rose quartz chunk + water
        ItemStack roseChunk = item("biomesoplenty:rose_quartz_chunk") == null ? ItemStack.EMPTY :
                new ItemStack(item("biomesoplenty:rose_quartz_chunk"));
        if (!roseChunk.isEmpty() && !roseQuartz.isEmpty()) {
            MixingRecipeBuilder.builder("rose_quartz_from_chunk_and_water")
                    .result(roseQuartz)
                    .input(roseChunk)
                    .inputFluid(GTMaterials.Water.getFluid(100))
                    .save(provider);
        }

        // concrete fluid result mixing (gtceu:concrete)
        MixingRecipeBuilder.builder("mixing_concrete_from_dusts")
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Stone))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.QuartzSand))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Clay))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Calcite))
                .inputFluid(GTMaterials.Water.getFluid(1000))
                .resultFluid(GTMaterials.Concrete.getFluid(1000))
                .heatRequirement("heated")
                .save(provider);

        // andesite alloy dust from iron fluid + dusts
        MixingRecipeBuilder.builder("andesite_alloy_from_iron")
                .result(new ItemStack(ChemicalHelper.get(TagPrefix.dust, CreateMaterials.AndesiteAlloy).getItem(), 2))
                .inputFluid(GTMaterials.Iron.getFluid(144))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Andesite))
                .save(provider);

        // stem cells from growth medium fluid + animal excreta
        MixingRecipeBuilder.builder("stem_cells_from_growth_medium")
                .result(GTItems.STEM_CELLS.asStack())
                .inputFluid(CTNHMaterials.SimpleGrowthMedium.getFluid(144))
                .input(CTNHItems.ANIMAL_EXCRETA.asItem())
                .save(provider);

        // treated wood planks from creosote + planks tag
        MixingRecipeBuilder.builder("treated_wood_planks_from_creosote")
                .result(new ItemStack(GTBlocks.TREATED_WOOD_PLANK.asItem(), 2))
                .inputFluid(GTMaterials.Creosote.getFluid(250))
                .input(ItemTags.PLANKS, 2)
                .save(provider);

        // red alloy dust
        MixingRecipeBuilder.builder("red_alloy_dust")
                .result(ChemicalHelper.get(TagPrefix.dust, GTMaterials.RedAlloy))
                .input(new ItemStack(Items.REDSTONE, 4))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Copper))
                .heatRequirement("heated")
                .save(provider);

        // andesite_alloy_dust with chance secondary
        MixingRecipeBuilder.builder("andesite_alloy_dust_with_secondary")
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Andesite))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Iron))
                .result(ChemicalHelper.get(TagPrefix.dust, CreateMaterials.AndesiteAlloy))
                .result(ChemicalHelper.get(TagPrefix.dust, CreateMaterials.AndesiteAlloy), 0.3)
                .save(provider);

        // steel precursor mixing (wrought iron + coke or charcoal) - simplified as two recipes
        ItemStack steelPrecursorDust = ChemicalHelper.get(TagPrefix.dust, UncategorizedMaterials.STEEL_PRECURSOR, 8);
        if (!steelPrecursorDust.isEmpty()) {
            MixingRecipeBuilder.builder("steel_precursor_from_wrought_and_coke")
                    .result(new ItemStack(steelPrecursorDust.getItem(), 8))
                    .input(new ItemStack(ChemicalHelper.get(TagPrefix.dust, GTMaterials.WroughtIron).getItem(), 8))
                    .input(new ItemStack(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Coke).getItem(), 3))
                    .heatRequirement("heated")
                    .save(provider);
            MixingRecipeBuilder.builder("steel_precursor_from_wrought_and_charcoal")
                    .result(new ItemStack(steelPrecursorDust.getItem(), 8))
                    .input(new ItemStack(ChemicalHelper.get(TagPrefix.dust, GTMaterials.WroughtIron).getItem(), 8))
                    .input(TagUtil.createItemTag("dusts/charcoal", false), 6)
                    .save(provider);
        }

        // bronze dust
        MixingRecipeBuilder.builder("bronze_dust_from_copper_tin")
                .result(new ItemStack(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Bronze).getItem(), 3))
                .input(new ItemStack(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Copper).getItem(), 3))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Tin))
                .heatRequirement("heated")
                .save(provider);

        // alexscaves magnets
        MixingRecipeBuilder.builder("scarlet_neodymium_ingot")
                .result(item("alexscaves:scarlet_neodymium_ingot") == null ? ItemStack.EMPTY :
                        new ItemStack(item("alexscaves:scarlet_neodymium_ingot"), 2))
                .input(item("alexscaves:raw_scarlet_neodymium") == null ? ItemStack.EMPTY :
                        new ItemStack(item("alexscaves:raw_scarlet_neodymium")))
                .input(ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Iron))
                .save(provider);
        MixingRecipeBuilder.builder("azure_neodymium_ingot")
                .result(item("alexscaves:azure_neodymium_ingot") == null ? ItemStack.EMPTY :
                        new ItemStack(item("alexscaves:azure_neodymium_ingot"), 2))
                .input(item("alexscaves:raw_azure_neodymium") == null ? ItemStack.EMPTY :
                        new ItemStack(item("alexscaves:raw_azure_neodymium")))
                .input(ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Iron))
                .save(provider);

        // Sequenced assembly recipes

        // item_application: shadow steel casing
        ItemApplicationRecipeBuilder.builder("shadow_steel_casing_item_application")
                .input(Items.OBSIDIAN)
                .input(ChemicalHelper.get(TagPrefix.plate, CreateMaterials.ShadowSteel).getItem())
                .output(AllBlocks.SHADOW_STEEL_CASING.asStack())
                .save(provider);

        // mixing: chromatic compound
        MixingRecipeBuilder.builder("chromatic_compound_from_lava")
                .result(AllItems.CHROMATIC_COMPOUND.asStack(4))
                .inputFluid(GTMaterials.Lava.getFluid(500))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Netherite))
                .input(ChemicalHelper.get(TagPrefix.ingot, CreateMaterials.AndesiteAlloy))
                .input(AllItems.POLISHED_ROSE_QUARTZ.asStack())
                .save(provider);

        // mixing: aqua regia gold nugget extraction
        MixingRecipeBuilder.builder("gold_nuggets_from_aqua_regia")
                .result(new ItemStack(ChemicalHelper.get(TagPrefix.nugget, GTMaterials.Gold).getItem(), 5))
                .inputFluid(GTMaterials.AquaRegia.getFluid(500))
                .input(new ItemStack(ChemicalHelper.get(TagPrefix.dust, CreateMaterials.OCHRUM).getItem(), 2))
                .save(provider);

        // splashing series (ores -> outputs)
        ItemStack asurineIn = ChemicalHelper.get(TagPrefix.dust, CreateMaterials.ASURINE);
        ItemStack asurineSil = ChemicalHelper.get(TagPrefix.dust, GTMaterials.SiliconDioxide);
        ItemStack asurineZn = ChemicalHelper.get(TagPrefix.nugget, GTMaterials.Zinc);
        if (!asurineIn.isEmpty() && !asurineSil.isEmpty()) {
            SplashingRecipeBuilder.builder("splashing_asurine")
                    .input(asurineIn)
                    .result(asurineSil)
                    .result(new ItemStack(asurineZn.getItem(), 4), 0.5)
                    .save(provider);
        }

        ItemStack crimsiteIn = ChemicalHelper.get(TagPrefix.dust, CreateMaterials.CRIMSITE);
        ItemStack crimsiteSil = ChemicalHelper.get(TagPrefix.dust, GTMaterials.SiliconDioxide);
        ItemStack crimsiteIron = ChemicalHelper.get(TagPrefix.nugget, GTMaterials.Iron);
        if (!crimsiteIn.isEmpty() && !crimsiteSil.isEmpty()) {
            SplashingRecipeBuilder.builder("splashing_crimsite")
                    .input(crimsiteIn)
                    .result(crimsiteSil)
                    .result(new ItemStack(crimsiteIron.getItem(), 4), 0.5)
                    .save(provider);
        }

        ItemStack ochrumIn = ChemicalHelper.get(TagPrefix.dust, CreateMaterials.OCHRUM);
        ItemStack ochrumSil = ChemicalHelper.get(TagPrefix.dust, GTMaterials.SiliconDioxide);
        ItemStack ochrumPrec = ChemicalHelper.get(TagPrefix.nugget, GTMaterials.Gold);
        if (!ochrumIn.isEmpty() && !ochrumSil.isEmpty()) {
            SplashingRecipeBuilder.builder("splashing_ochrum")
                    .input(ochrumIn)
                    .result(ochrumSil)
                    .result(new ItemStack(ochrumPrec.getItem(), 4), 0.5)
                    .save(provider);
        }

        ItemStack veridiumIn = ChemicalHelper.get(TagPrefix.dust, CreateMaterials.VERIDIUM);
        ItemStack veridiumSil = ChemicalHelper.get(TagPrefix.dust, GTMaterials.SiliconDioxide);
        ItemStack veridiumCu = ChemicalHelper.get(TagPrefix.nugget, GTMaterials.Copper);
        if (!veridiumIn.isEmpty() && !veridiumSil.isEmpty()) {
            SplashingRecipeBuilder.builder("splashing_veridium")
                    .input(veridiumIn)
                    .result(veridiumSil)
                    .result(new ItemStack(veridiumCu.getItem(), 4), 0.5)
                    .save(provider);
        }

        // deepslate tuff crushing (primary + optional flint secondary)
        ItemStack tuff = new ItemStack(Blocks.TUFF.asItem());
        ItemStack deepslateDust = ChemicalHelper.get(TagPrefix.dust, GTMaterials.Deepslate);
        ItemStack flintDust = ChemicalHelper.get(TagPrefix.dust, GTMaterials.Flint);
        if (!tuff.isEmpty() && !deepslateDust.isEmpty()) {
            CrushingRecipeBuilder builder = CrushingRecipeBuilder.builder("crushing_tuff_to_deepslate");
            builder.input(tuff).result(deepslateDust);
            if (!flintDust.isEmpty()) builder.result(flintDust, 0.25);
            builder.save(provider);
        }

        // deepslate splashing (many outputs with chances)
        ItemStack deepslateIn = ChemicalHelper.get(TagPrefix.dust, GTMaterials.Deepslate);
        if (!deepslateIn.isEmpty()) {
            SplashingRecipeBuilder splashBuilder = SplashingRecipeBuilder
                    .builder("splashing_deepslate");
            splashBuilder = splashBuilder.input(deepslateIn);
            ItemStack sd = ChemicalHelper.get(TagPrefix.dust, GTMaterials.Stone);
            if (!sd.isEmpty()) splashBuilder.result(sd);
            ItemStack dia = new ItemStack(Items.DIAMOND);
            if (!dia.isEmpty()) splashBuilder.result(dia, 0.05);
            ItemStack gld = ChemicalHelper.get(TagPrefix.nugget, GTMaterials.Gold);
            if (!gld.isEmpty()) splashBuilder.result(gld, 0.05);
            ItemStack irn = ChemicalHelper.get(TagPrefix.nugget, GTMaterials.Iron);
            if (!irn.isEmpty()) splashBuilder.result(irn, 0.1);
            ItemStack cup = ChemicalHelper.get(TagPrefix.nugget, GTMaterials.Copper);
            if (!cup.isEmpty()) splashBuilder.result(cup, 0.08);
            ItemStack zn = ChemicalHelper.get(TagPrefix.nugget, GTMaterials.Zinc);
            if (!zn.isEmpty()) splashBuilder.result(zn, 0.05);
            ItemStack pt = ChemicalHelper.get(TagPrefix.nugget, GTMaterials.Platinum);
            if (!pt.isEmpty()) splashBuilder.result(pt, 0.01);
            ItemStack mn = ChemicalHelper.get(TagPrefix.nugget, GTMaterials.Manganese);
            if (!mn.isEmpty()) splashBuilder.result(mn, 0.04);
            ItemStack cr = ChemicalHelper.get(TagPrefix.nugget, GTMaterials.Chromium);
            if (!cr.isEmpty()) splashBuilder.result(cr, 0.02);
            splashBuilder.save(provider);
        }

        // crushing tuff -> deepslate_dust + flint chance already added above

        // remove tuff crushing outputs replacements: emulate original removes by not generating those specific create
        // recipes (skip)

        // item_application/mixing/splashing for precious alloy dust -> gold nuggets
        ItemStack preciousIn = ChemicalHelper.get(TagPrefix.dust, CTNHMaterials.PreciousAlloy);
        if (!preciousIn.isEmpty()) {
            SplashingRecipeBuilder splash = SplashingRecipeBuilder
                    .builder("splashing_precious_alloy_gold").input(preciousIn);
            ItemStack g3 = new ItemStack(ChemicalHelper.get(TagPrefix.nugget, GTMaterials.Gold).getItem(), 3);
            if (!g3.isEmpty()) splash.result(g3, 0.8);
            ItemStack g2 = new ItemStack(ChemicalHelper.get(TagPrefix.nugget, GTMaterials.Gold).getItem(), 2);
            if (!g2.isEmpty()) splash.result(g2, 0.6);
            ItemStack g1 = new ItemStack(ChemicalHelper.get(TagPrefix.nugget, GTMaterials.Gold).getItem(), 1);
            if (!g1.isEmpty()) splash.result(g1, 0.4);
            ItemStack s1 = ChemicalHelper.get(TagPrefix.nugget, GTMaterials.Silver);
            if (!s1.isEmpty()) splash.result(s1, 0.6);
            splash.save(provider);
        }

        // milling with chance: obsidian -> obsidian_dust (0.75)
        MillingRecipeBuilder.builder("milling_obsidian_chance")
                .input(Blocks.OBSIDIAN.asItem())
                .result(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Obsidian), 0.75)
                .save(provider);

        // basic mechanism from wooden slabs -> ctpp:basic_mechanism
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

        // precision mechanism from basic mechanism
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
                    .filling(incompletePrecision, "alexscaves:acid", 500)
                    .loops(1)
                    .save(provider);
        }

        // electron tube
        ItemStack electronTrans = AllItems.ELECTRON_TUBE.asStack();
        ItemStack vacuumTube = GTItems.VACUUM_TUBE.asStack();
        if (!electronTrans.isEmpty() && !vacuumTube.isEmpty()) {
            SequencedAssemblyRecipeBuilder.builder("electron_tube_from_vacuum")
                    .input(vacuumTube)
                    .transitional(electronTrans)
                    .result(vacuumTube)
                    .deploying(new ItemStack(Blocks.GLASS.asItem()))
                    .deploying(ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Steel))
                    .deploying(ChemicalHelper.get(TagPrefix.wireGtDouble, GTMaterials.Copper))
                    .loops(1)
                    .save(provider);
        }

        // unfinished steel mechanism (create precision -> ctpp:steel_mechanism)
        ItemStack unfinishedSteel = CTPPItems.INCOMPLETE_STEEL_MECHANISM.asStack();
        ItemStack steelMech = CTPPItems.STEEL_MECHANISM.asStack();
        if (!unfinishedSteel.isEmpty() && !steelMech.isEmpty() && !precision.isEmpty()) {
            SequencedAssemblyRecipeBuilder.builder("steel_mechanism_from_precision")
                    .input(precision)
                    .transitional(unfinishedSteel)
                    .result(steelMech)
                    .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel))
                    .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.RedAlloy))
                    .deploying(ChemicalHelper.get(TagPrefix.screw, GTMaterials.Steel))
                    .filling(unfinishedSteel, GTMaterials.Rubber.getFluid(576))
                    .loops(1)
                    .save(provider);
        }

        // ender pearl dust -> ender eye dust
        ItemStack enderPearlDust = ChemicalHelper.get(TagPrefix.dust, GTMaterials.EnderPearl);
        ItemStack enderEyeDust = ChemicalHelper.get(TagPrefix.dust, GTMaterials.EnderEye);
        if (!enderPearlDust.isEmpty() && !enderEyeDust.isEmpty()) {
            SequencedAssemblyRecipeBuilder.builder("ender_pearl_to_eye_dust")
                    .input(enderPearlDust)
                    .transitional(enderPearlDust)
                    .result(enderEyeDust)
                    .filling(enderPearlDust, GTMaterials.Blaze.getFluid(288))
                    .pressing()
                    .loops(1)
                    .save(provider);
        }

        // bronze machine casing -> steam engine (many steps)
        ItemStack bronzeCasing = GTBlocks.BRONZE_HULL.asStack();
        ItemStack steamEngine = AllBlocks.STEAM_ENGINE.asStack();
        if (!bronzeCasing.isEmpty() && !steamEngine.isEmpty()) {
            SequencedAssemblyRecipeBuilder.builder("bronze_machine_casing_to_steam_engine")
                    .input(bronzeCasing)
                    .transitional(bronzeCasing)
                    .result(steamEngine)
                    .deploying(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Steel))
                    .deploying(ChemicalHelper.get(TagPrefix.rodLong, GTMaterials.Steel))
                    .deploying(CTPPItems.STEEL_MECHANISM.asStack())
                    .pressing()
                    .filling(bronzeCasing, GTMaterials.Lubricant.getFluid(250))
                    .loops(3)
                    .save(provider);
        }

        // paper -> resistors
        ItemStack paper = new ItemStack(Items.PAPER);
        ItemStack resistor = GTItems.RESISTOR.asStack();
        if (!paper.isEmpty() && !resistor.isEmpty()) {
            SequencedAssemblyRecipeBuilder.builder("paper_to_resistor")
                    .input(paper)
                    .transitional(paper)
                    .result(new ItemStack(resistor.getItem(), 2))
                    .deploying(ChemicalHelper.get(TagPrefix.wireGtSingle, GTMaterials.Copper))
                    .deploying(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Coal))
                    .deploying(GTItems.STICKY_RESIN.asStack())
                    .pressing()
                    .loops(1)
                    .save(provider);
        }

        // small gallium arsenide -> diode
        ItemStack smallGa = ChemicalHelper.get(TagPrefix.dustSmall, GTMaterials.GalliumArsenide);
        ItemStack diode = GTItems.DIODE.asStack();
        if (!smallGa.isEmpty() && !diode.isEmpty()) {
            SequencedAssemblyRecipeBuilder.builder("gallium_arsenide_to_diodes")
                    .input(smallGa)
                    .transitional(smallGa)
                    .result(new ItemStack(diode.getItem(), 2))
                    .deploying(ChemicalHelper.get(TagPrefix.wireGtSingle, GTMaterials.Copper))
                    .filling(smallGa, GTMaterials.Tin.getFluid(144))
                    .pressing()
                    .loops(1)
                    .save(provider);
        }

        // high strength concrete -> sintering kiln
        ItemStack highConcrete = CTNHBlocks.HIGH_GRADE_COKE_OVEN_BRICKS.asStack();
        ItemStack sinteringKiln = MultiblocksA.SINTERING_KILN.asStack();
        ItemStack steelFirebox = GTBlocks.FIREBOX_STEEL.asStack();
        if (!highConcrete.isEmpty() && !sinteringKiln.isEmpty() && !steelFirebox.isEmpty()) {
            SequencedAssemblyRecipeBuilder.builder("high_strength_concrete_to_sintering_kiln")
                    .input(steelFirebox)
                    .transitional(highConcrete)
                    .result(sinteringKiln)
                    .deploying(ChemicalHelper.get(TagPrefix.block, GTMaterials.Steel))
                    .deploying(MultiblocksA.ADVANCED_COKE_OVEN.asStack())
                    .deploying(GTBlocks.CASING_PRIMITIVE_BRICKS.asStack())
                    .filling(highConcrete, GTMaterials.Creosote.getFluid(1000))
                    .loops(1)
                    .save(provider);
        }

        // orange stained glass -> bronze framed glass
        ItemStack orangeGlass = new ItemStack(Blocks.ORANGE_STAINED_GLASS.asItem());
        ItemStack bronzeFramed = CTNHBlocks.BRONZE_FRAMED_GLASS.asStack();
        if (!orangeGlass.isEmpty() && !bronzeFramed.isEmpty()) {
            SequencedAssemblyRecipeBuilder.builder("orange_glass_to_bronze_framed")
                    .input(new ItemStack(Blocks.GLASS.asItem()))
                    .transitional(orangeGlass)
                    .result(bronzeFramed)
                    .deploying(ChemicalHelper.get(TagPrefix.pipeTinyFluid, GTMaterials.Bronze))
                    .deploying(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Bronze))
                    .deploying(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Bronze))
                    .loops(2)
                    .save(provider);
        }

        // resin circuit board -> basic electronic circuit (custom sequence)
        ItemStack resinBoard = GTItems.COATED_BOARD.asStack();
        ItemStack basicCircuit = GTItems.ELECTRONIC_CIRCUIT_LV.asStack();
        if (!resinBoard.isEmpty() && !basicCircuit.isEmpty()) {
            SequencedAssemblyRecipeBuilder.builder("basic_electronic_circuit_from_resin")
                    .input(resinBoard)
                    .transitional(resinBoard)
                    .result(basicCircuit)
                    .deploying(ChemicalHelper.get(TagPrefix.wireGtQuadruple, GTMaterials.Copper))
                    .deploying(ChemicalHelper.get(TagPrefix.wireGtDouble, GTMaterials.RedAlloy))
                    .deploying(vacuumTube)
                    .deploying(resistor)
                    .filling(resinBoard, GTMaterials.Rubber.getFluid(288))
                    .pressing()
                    .step("create_new_age:energising", json -> {
                        json.addProperty("energy_needed", 10000);
                        json.add("ingredients", stackElements(resinBoard));
                        json.add("results", stackElements(resinBoard));
                    })
                    .loops(1)
                    .save(provider);
        }

        // steam machine casing -> industrial steam casing
        ItemStack steamMachineCasing = GTBlocks.CASING_BRONZE_BRICKS.asStack();
        ItemStack industrialSteam = GCYMBlocks.CASING_INDUSTRIAL_STEAM.asStack();
        if (!steamMachineCasing.isEmpty() && !industrialSteam.isEmpty()) {
            SequencedAssemblyRecipeBuilder.builder("steam_machine_casing_to_industrial")
                    .input(steamMachineCasing)
                    .transitional(steamMachineCasing)
                    .result(industrialSteam)
                    .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Brass))
                    .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Brass))
                    .filling(steamMachineCasing, GTMaterials.SolderingAlloy.getFluid(144))
                    .pressing()
                    .loops(1)
                    .save(provider);
        }

        // blaze cake -> double blaze cake (multiple fill steps)
        ItemStack blazeCake = AllItems.BLAZE_CAKE.asStack();
        ItemStack doubleBlaze = CTPPItems.DOUBLE_BLAZE_CAKE.asStack();
        if (!blazeCake.isEmpty() && !doubleBlaze.isEmpty()) {
            SequencedAssemblyRecipeBuilder.builder("double_blaze_cake_from_blaze_cake")
                    .input(blazeCake)
                    .transitional(blazeCake)
                    .result(doubleBlaze)
                    .filling(blazeCake, GTMaterials.Lava.getFluid(100))
                    .filling(blazeCake, GTMaterials.Lava.getFluid(100))
                    .filling(blazeCake, GTMaterials.Lava.getFluid(100))
                    .filling(blazeCake, GTMaterials.Lava.getFluid(100))
                    .filling(blazeCake, GTMaterials.Lava.getFluid(100))
                    .filling(blazeCake, GTMaterials.Lava.getFluid(100))
                    .filling(blazeCake, GTMaterials.Lava.getFluid(200))
                    .filling(blazeCake, GTMaterials.Lava.getFluid(200))
                    .loops(1)
                    .save(provider);
        }

        // ulv input bus/hatch (from chest tags)
        ItemStack ulvInputBus = GTMachines.ITEM_IMPORT_BUS[GTValues.ULV].asStack();
        if (!ulvInputBus.isEmpty()) {
            SequencedAssemblyRecipeBuilder.builder("ulv_input_bus_from_wooden_chest")
                    .input(TagUtil.createItemTag("chests/wooden", false))
                    .transitional(ulvInputBus)
                    .result(ulvInputBus)
                    .deploying(CTPPItems.STEEL_MECHANISM.asStack())
                    .deploying(GTBlocks.MACHINE_CASING_ULV.asStack())
                    .deploying(ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.WroughtIron))
                    .loops(1)
                    .save(provider);
        }

        ItemStack ulvInputHatch = GTMachines.FLUID_IMPORT_HATCH[GTValues.ULV].asStack();
        if (!ulvInputHatch.isEmpty()) {
            SequencedAssemblyRecipeBuilder.builder("ulv_input_hatch_from_bronze_drum")
                    .input(GTMachines.BRONZE_DRUM.asStack())
                    .transitional(ulvInputHatch)
                    .result(ulvInputHatch)
                    .deploying(CTPPItems.STEEL_MECHANISM.asStack())
                    .deploying(GTBlocks.MACHINE_CASING_ULV.asStack())
                    .deploying(ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.WroughtIron))
                    .loops(1)
                    .save(provider);
        }

        // tungsten steel frame (pick primary output)
        ItemStack tungstenFrame = ChemicalHelper.get(TagPrefix.frameGt, GTMaterials.TungstenSteel);
        ItemStack assemblyLineCasing = GTBlocks.CASING_ASSEMBLY_CONTROL.asStack();
        if (!tungstenFrame.isEmpty() && !assemblyLineCasing.isEmpty()) {
            SequencedAssemblyRecipeBuilder.builder("tungsten_steel_frame_sequence")
                    .input(tungstenFrame)
                    .transitional(tungstenFrame)
                    .result(assemblyLineCasing)
                    .deploying(CustomTags.ZPM_CIRCUITS)
                    .pressing()
                    .pressing()
                    .deploying(CustomTags.ZPM_CIRCUITS)
                    .pressing()
                    .pressing()
                    .deploying(CustomTags.LuV_CIRCUITS)
                    .pressing()
                    .loops(2)
                    .save(provider);
        }

        // tungstensteel gearbox
        ItemStack gearbox = GTBlocks.CASING_TUNGSTENSTEEL_GEARBOX.asStack();
        ItemStack assemblyLineUnit = GTBlocks.CASING_ASSEMBLY_LINE.asStack();
        if (!gearbox.isEmpty() && !assemblyLineUnit.isEmpty()) {
            SequencedAssemblyRecipeBuilder.builder("tungstensteel_gearbox_sequence")
                    .input(gearbox)
                    .transitional(gearbox)
                    .result(assemblyLineUnit)
                    .deploying(ChemicalHelper.get(TagPrefix.gear, GTMaterials.Ruridit))
                    .pressing()
                    .cutting()
                    .deploying(GTItems.ROBOT_ARM_IV.asStack())
                    .cutting()
                    .pressing()
                    .loops(4)
                    .save(provider);
        }
        addMigratedKubeJsCreateRecipes(provider);
    }

    private static void addCopperCasingRecipes(Consumer<FinishedRecipe> provider) {
        addCopperCasingRecipe(provider, "copper_casing_from_log", "forge:stripped_logs");
        addCopperCasingRecipe(provider, "copper_casing_from_wood", "forge:stripped_wood");
    }

    private static void addCopperCasingRecipe(Consumer<FinishedRecipe> provider, String name, String woodTag) {
        JsonObject recipe = CreateRecipeJsonHelper.recipe("create:item_application");
        recipe.add("ingredients", CreateRecipeJsonHelper.array(
                CreateRecipeJsonHelper.tag(woodTag),
                CreateRecipeJsonHelper.item("gtceu:bronze_ingot")));
        recipe.add("results", CreateRecipeJsonHelper.array(CreateRecipeJsonHelper.item("create:copper_casing")));
        CreateRecipeJsonHelper.save(provider, CTNHCore.id("create/" + name).toString(), recipe);
    }

    private static void addMigratedKubeJsCreateRecipes(Consumer<FinishedRecipe> provider) {
        // 迁移来源：Z:\Git\Create-New-Horizon\kubejs\server_scripts\src\create\cafe.js
        addCafeRecipes(provider);
        // 迁移来源：Z:\Git\Create-New-Horizon\kubejs\server_scripts\src\create\createFallen.js
        addCreateFallenRecipes(provider);
        // 迁移来源：Z:\Git\Create-New-Horizon\kubejs\server_scripts\src\create\dieselgenerator.js
        addDieselGeneratorRecipes(provider);
        // 迁移来源：Z:\Git\Create-New-Horizon\kubejs\server_scripts\src\create\Vintage_Improvements.js
        CreateVintageRecipe.addRecipes(provider);
    }

    private static void addCafeRecipes(Consumer<FinishedRecipe> provider) {
        MixingRecipeBuilder.builder("createcafe_blueberry_tea")
                .input(Objects.requireNonNull(item("aether:blue_berry")))
                .inputFluid(GTMaterials.Milk.getFluid(250))
                .inputFluid("createcafe:melted_sugar", 250)
                .resultFluid(fluidStack("createcafe:blueberry_tea", 500))
                .save(provider);
        MixingRecipeBuilder.builder("createcafe_coconut_tea")
                .input(Objects.requireNonNull(item("ecologics:coconut_slice")))
                .inputFluid(GTMaterials.Milk.getFluid(250))
                .inputFluid("createcafe:melted_sugar", 250)
                .resultFluid(fluidStack("createcafe:coconut_tea", 500))
                .save(provider);
        MixingRecipeBuilder.builder("createcafe_coconut_syrup")
                .input(Objects.requireNonNull(item("ecologics:coconut_slice")))
                .inputFluid(GTMaterials.Milk.getFluid(250))
                .inputFluid("createcafe:melted_sugar", 750)
                .resultFluid(fluidStack("createcafe:coconut_syrup", 1000))
                .save(provider);
        MixingRecipeBuilder.builder("createcafe_pomegranate_tea")
                .input(Objects.requireNonNull(item("ars_nouveau:bombegranate_pod")))
                .inputFluid(GTMaterials.Milk.getFluid(250))
                .inputFluid("createcafe:melted_sugar", 250)
                .resultFluid(fluidStack("createcafe:pomegranate_tea", 500))
                .save(provider);
        MixingRecipeBuilder.builder("createcafe_blood_tea")
                .input(Objects.requireNonNull(item("gtceu:snow_steel_ingot")))
                .inputFluid(GTMaterials.Milk.getFluid(250))
                .inputFluid("createcafe:melted_sugar", 250)
                .resultFluid(fluidStack("createcafe:blood_tea", 500))
                .save(provider);
    }

    private static void addCreateFallenRecipes(Consumer<FinishedRecipe> provider) {
        addPhenolicCircuitSequence(provider);
        CrushingRecipeBuilder.builder("createfallen_logs_to_wood_dust")
                .input(Ingredient.of(ItemTags.LOGS))
                .result(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Wood), 0.8)
                .save(provider);
        CrushingRecipeBuilder.builder("createfallen_planks_to_wood_dust")
                .input(Ingredient.of(ItemTags.PLANKS))
                .result(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Wood), 0.4)
                .save(provider);
        rawSequencedResinBoard(provider);
        MixingRecipeBuilder.builder("createfallen_rubber_powder_from_sulfur")
                .input(Ingredient.of(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Sulfur)))
                .input(Ingredient.of(ChemicalHelper.get(TagPrefix.dust, GTMaterials.RawRubber, 3)))
                .output(CTNHItems.RUBBER_POWDER.asStack())
                .save(provider);
        MixingRecipeBuilder.builder("createfallen_rubber_powder_from_alex_sulfur")
                .input(Ingredient.of(new ItemStack(Objects.requireNonNull(item("alexscaves:sulfur_dust")))))
                .input(Ingredient.of(ChemicalHelper.get(TagPrefix.dust, GTMaterials.RawRubber, 3)))
                .output(CTNHItems.RUBBER_POWDER.asStack())
                .save(provider);
    }

    private static void addDieselGeneratorRecipes(Consumer<FinishedRecipe> provider) {
        VanillaRecipeHelper.addShapedRecipe(provider,
                CreateDieselGenerators.rl("crafting/engine_piston"),
                new ItemStack(CDGItems.ENGINE_PISTON.get(), 2),
                "AB ", "BC ", "  D",
                'A', CTPPItems.STEEL_MECHANISM.asStack(),
                'B', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Iron),
                'C', Items.PISTON,
                'D', ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Zinc));
        VanillaRecipeHelper.addShapedRecipe(provider,
                CreateDieselGenerators.rl("crafting/pumpjack_hole"),
                new ItemStack(CDGBlocks.PUMPJACK_HOLE.get()),
                "ABA", "CDC", "EFE",
                'A', CTPPItems.STEEL_MECHANISM.asStack(),
                'B', AllBlocks.HOSE_PULLEY.asStack(),
                'C', AllBlocks.COPPER_CASING.asStack(),
                'D', CTPPBlocks.STEEL_CASING.asStack(),
                'E', Items.CHAIN,
                'F', AllBlocks.FLUID_PIPE.asStack());
        VanillaRecipeHelper.addShapedRecipe(provider,
                CreateDieselGenerators.rl("crafting/pumpjack_bearing"),
                new ItemStack(CDGBlocks.PUMPJACK_BEARING.get()),
                "   ", "ABA", "ACA",
                'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel),
                'B', AllBlocks.MECHANICAL_BEARING.asStack(),
                'C', CTPPBlocks.HEAVY_MACHINERY_CASING.asStack());
        VanillaRecipeHelper.addShapedRecipe(provider,
                CreateDieselGenerators.rl("crafting/basin_lid"),
                new ItemStack(CDGBlocks.BASIN_LID.get()),
                "   ", "ABA", "CDC",
                'A', Items.REDSTONE,
                'B', ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.Steel),
                'C', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel),
                'D', Items.CLOCK);
        ItemApplicationRecipeBuilder.builder(CreateDieselGenerators.ID + "_steel_casing_application")
                .input(GTBlocks.TREATED_WOOD_PLANK.asStack())
                .input(ChemicalHelper.get(TagPrefix.plate, GTMaterials.WroughtIron))
                .output(CTPPBlocks.STEEL_CASING.asStack())
                .save(provider);
        ItemApplicationRecipeBuilder.builder(CreateDieselGenerators.ID + "_heavy_machinery_casing_application")
                .input(CTPPBlocks.STEEL_CASING.asStack())
                .input(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel))
                .output(CTPPBlocks.HEAVY_MACHINERY_CASING.asStack())
                .save(provider);
        MixingRecipeBuilder.builder(CreateDieselGenerators.ID + "_emulsified_bitumen_slurry")
                .inputFluid(BiodieselFertileSoilMaterials.BITUMEN.getFluid(800))
                .inputFluid(BiodieselFertileSoilMaterials.SIMPLE_EMULGATOR.getFluid(200))
                .resultFluid(BiodieselFertileSoilMaterials.EMULSIFIED_BITUMEN_SLURRY.getFluid(1000))
                .save(provider);
        CompactingRecipeBuilder.builder(CTNHCore.id(CreateDieselGenerators.ID + "/emulsified_bitumen"))
                .inputFluid(BiodieselFertileSoilMaterials.EMULSIFIED_BITUMEN_SLURRY.getFluid(1000))
                .resultFluid(BiodieselFertileSoilMaterials.EMULSIFIED_BITUMEN.getFluid(1000))
                .result(ChemicalHelper.get(TagPrefix.dust, BiodieselFertileSoilMaterials.OIL_REFINED_RESIDUES, 2))
                .result(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Salt, 1))
                .save(provider);
        MixingRecipeBuilder.builder(CreateDieselGenerators.ID + "_pure_bitumen")
                .inputFluid(BiodieselFertileSoilMaterials.EMULSIFIED_BITUMEN.getFluid(800))
                .inputFluid(BiodieselFertileSoilMaterials.SIMPLE_DEMULSIFIER.getFluid(200))
                .resultFluid(BiodieselFertileSoilMaterials.PURE_BITUMEN.getFluid(1000))
                .save(provider);
        MixingRecipeBuilder.builder(CreateDieselGenerators.ID + "_simple_emulgator")
                .input(Ingredient.of(ChemicalHelper.get(TagPrefix.dust, GTMaterials.SodaAsh, 6)))
                .input(CTNHItems.TALLOW.asStack())
                .inputFluid(GTMaterials.Water.getFluid(1000))
                .resultFluid(BiodieselFertileSoilMaterials.SIMPLE_EMULGATOR.getFluid(1000))
                .save(provider);
        MixingRecipeBuilder.builder(CreateDieselGenerators.ID + "_simple_demulsifier")
                .input(CTNHItems.TALLOW.asStack())
                .inputFluid(GTMaterials.DilutedSulfuricAcid.getFluid(1000))
                .resultFluid(BiodieselFertileSoilMaterials.SIMPLE_DEMULSIFIER.getFluid(1000))
                .save(provider);
        CompactingRecipeBuilder.builder(CTNHCore.id(CreateDieselGenerators.ID + "/petroleum_coke_gem"))
                .inputFluid(BiodieselFertileSoilMaterials.PETROLEUM_COKE.getFluid(144))
                .result(itemStack("gtceu:petroleum_coke_gem", 1))
                .save(provider);
        MixingRecipeBuilder.builder(CreateDieselGenerators.ID + "_asphalt_block")
                .input(Items.SAND)
                .input(Items.GRAVEL)
                .inputFluid(BiodieselFertileSoilMaterials.PURE_BITUMEN.getFluid(100))
                .output(new ItemStack(CDGBlocks.ASPHALT_BLOCK.get(), 4))
                .save(provider);
        MixingRecipeBuilder.builder(CreateDieselGenerators.ID + "_raw_bio_diesel")
                .inputFluid(GTMaterials.Ethanol.getFluid(100))
                .inputFluid(GTMaterials.SeedOil.getFluid(100))
                .resultFluid(BiodieselFertileSoilMaterials.RAW_BIO_DIESEL.getFluid(200))
                .save(provider);
        addDieselGeneratorCustomRecipes(provider);
        CrushingRecipeBuilder.builder(CreateDieselGenerators.ID + "_rich_soil_dust")
                .input(Ingredient
                        .of(Objects.requireNonNull(item("farmersdelight:rich_soil"), "farmersdelight:rich_soil")))
                .output(new ItemStack(Objects.requireNonNull(item("gtceu:rich_soil_dust"), "gtceu:rich_soil_dust"), 3))
                .save(provider);
        CrushingRecipeBuilder.builder(CreateDieselGenerators.ID + "_rich_soul_soil_dust")
                .input(Ingredient.of(Objects.requireNonNull(item("mynethersdelight:resurgent_soil"),
                        "mynethersdelight:resurgent_soil")))
                .output(new ItemStack(Objects.requireNonNull(item("gtceu:rich_soul_soil_dust"),
                        "gtceu:rich_soul_soil_dust"), 3))
                .save(provider);
    }

    private static void addPhenolicCircuitSequence(Consumer<FinishedRecipe> provider) {
        ItemStack phenolicBoard = itemStack("gtceu:phenolic_circuit_board");
        SequencedAssemblyRecipeBuilder.builder(
                CTNHCore.id("createfallen/good_electronic_circuit_from_phenolic_board"))
                .input(phenolicBoard)
                .transitional(phenolicBoard)
                .result(itemStack("gtceu:good_electronic_circuit"))
                .deploying(ChemicalHelper.get(TagPrefix.wireGtDouble, GTMaterials.Silver, 1))
                .deploying(ChemicalHelper.get(TagPrefix.wireGtSingle, GTMaterials.Copper, 1))
                .deploying(itemStack("gtceu:basic_electronic_circuit"))
                .deploying(GTItems.DIODE.asStack())
                .filling(phenolicBoard, GTMaterials.Tin.getFluid(144))
                .step("create_new_age:energising", json -> {
                    json.addProperty("energy_needed", 10000);
                    json.add("ingredients", stackElements(phenolicBoard));
                    json.add("results", stackElements(phenolicBoard));
                })
                .loops(2)
                .save(provider);
    }

    private static void rawSequencedResinBoard(Consumer<FinishedRecipe> provider) {
        ItemStack circuitBoardMOne = itemStack("ctnhcore:circuit_board_m_one");
        SequencedAssemblyRecipeBuilder.builder(CTNHCore.id("createfallen/resin_printed_circuit_board"))
                .input(itemStack("gtceu:wood_plate"))
                .transitional(circuitBoardMOne)
                .result(itemStack("gtceu:resin_printed_circuit_board"))
                .deploying(itemStack("gtceu:copper_foil"))
                .filling(circuitBoardMOne, GTMaterials.Glue.getFluid(25))
                .pressing()
                .loops(4)
                .save(provider);
    }

    private static void addDieselGeneratorCustomRecipes(Consumer<FinishedRecipe> provider) {
        BasinFermentingRecipeBuilder.builder(CreateDieselGenerators.rl("basin_fermenting/fermentable"))
                .input(TagUtil.createItemTag("fermentable", false))
                .inputFluid(GTMaterials.Water.getFluid(200))
                .input(itemStack("gtceu:small_rich_soil_dust"))
                .duration(200)
                .resultFluid(BiodieselFertileSoilMaterials.ETHANOL_MIXTURE.getFluid(200))
                .result("gtceu:normal_yeast_dust", 1, 0.1)
                .result("gtceu:small_rich_soil_dust", 1, 0.8)
                .save(provider);

        DistillationRecipeBuilder.builder(CTNHCore.id(CreateDieselGenerators.ID + "/ethanol_mixture_distillation"))
                .inputFluid(BiodieselFertileSoilMaterials.ETHANOL_MIXTURE.getFluid(100))
                .heatRequirement(HeatCondition.HEATED)
                .duration(200)
                .resultFluid(GTMaterials.FermentedBiomass.getFluid(30))
                .resultFluid(GTMaterials.Ethanol.getFluid(50))
                .resultFluid(GTMaterials.Methanol.getFluid(20))
                .save(provider);
    }

    private static JsonArray stackElements(ItemStack... stacks) {
        JsonArray array = new JsonArray();
        for (ItemStack stack : stacks) {
            array.add(Ingredient.of(stack).toJson());
        }
        return array;
    }

    private static void addHeatedQuartzGlassMixing(Consumer<FinishedRecipe> provider) {
        MixingRecipeBuilder.builder(CTNHCore.id("mixing/quartz_glass_dust"))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.CertusQuartz))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Glass))
                .result(ChemicalHelper.get(TagPrefix.dust, SpecialMaterials.QUARTZ_GLASS, 2))
                .heatRequirement(HeatCondition.HEATED)
                .save(provider);
    }

    private static Item item(String id) {
        if (id == null) return null;
        // tags (starting with #) cannot be resolved to Item here
        if (id.startsWith("#")) return null;
        ResourceLocation rl = ResourceLocation.tryParse(id);
        if (rl == null) return null;
        return ForgeRegistries.ITEMS.getValue(rl);
    }

    private static ItemLike itemLike(String id) {
        return Objects.requireNonNull(item(id), id);
    }

    private static ItemStack itemStack(String id, int count) {
        return new ItemStack(itemLike(id), count);
    }

    private static ItemStack itemStack(String id) {
        return itemStack(id, 1);
    }

    private static FluidStack fluidStack(String id, int amount) {
        ResourceLocation rl = ResourceLocation.parse(id);
        return new FluidStack(Objects.requireNonNull(ForgeRegistries.FLUIDS.getValue(rl), id), amount);
    }
}
