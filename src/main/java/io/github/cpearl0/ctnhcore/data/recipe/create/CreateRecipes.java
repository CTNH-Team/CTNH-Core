package io.github.cpearl0.ctnhcore.data.recipe.create;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.BiodieselFertileSoilMaterials;
import io.github.cpearl0.ctnhcore.data.materials.EnderIOMaterials;
import io.github.cpearl0.ctnhcore.data.materials.SpecialMaterials;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

import com.Imphuls3.createcafe.core.registry.FluidRegistry;
import com.aetherteam.aether.item.AetherItems;
import com.google.gson.JsonArray;
import com.hollingsworth.arsnouveau.setup.registry.BlockRegistry;
import com.jesz.createdieselgenerators.CDGBlocks;
import com.jesz.createdieselgenerators.CDGItems;
import com.jesz.createdieselgenerators.CreateDieselGenerators;
import com.mo_guang.ctpp.data.recipe.builder.create.CuttingRecipeBuilder;
import com.mo_guang.ctpp.registry.CTPPBlocks;
import com.mo_guang.ctpp.registry.CTPPItems;
import com.mo_guang.ctpp.registry.CreateMaterials;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.soytutta.mynethersdelight.common.registry.MNDItems;
import samebutdifferent.ecologics.registry.ModItems;

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
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/mechanical_saw"));
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
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/fluid_pipe"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/kinetics/fluid_pipe_vertical"));
        RecipeRemoval.remove(new RemoveFilter().id("create:item_application/copper_casing_from_log"));
        RecipeRemoval.remove(new RemoveFilter().id("create:item_application/copper_casing_from_wood"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/curiosities/brown_toolbox"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/curiosities/toolbox_dyeing"));
        RecipeRemoval.remove(new RemoveFilter().id("create:smelting/glass_from_framed_glass"));
        RecipeRemoval.remove(new RemoveFilter().id("create:smelting/glass_from_horizontal_framed_glass"));
        RecipeRemoval.remove(new RemoveFilter().id("create:smelting/glass_from_vertical_framed_glass"));
        // 统一到 GT 锌材料：删除机械动力锌锭/锌粒/锌块的全部产出配方
        RecipeRemoval.remove(new RemoveFilter().id("create:smelting/zinc_ingot_from_crushed"));
        RecipeRemoval.remove(new RemoveFilter().id("create:smelting/zinc_ingot_from_ore"));
        RecipeRemoval.remove(new RemoveFilter().id("create:smelting/zinc_ingot_from_raw_ore"));
        RecipeRemoval.remove(new RemoveFilter().id("create:blasting/zinc_ingot_from_crushed"));
        RecipeRemoval.remove(new RemoveFilter().id("create:blasting/zinc_ingot_from_ore"));
        RecipeRemoval.remove(new RemoveFilter().id("create:blasting/zinc_ingot_from_raw_ore"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/materials/zinc_ingot_from_compacting"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/materials/zinc_ingot_from_decompacting"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/materials/zinc_nugget_from_decompacting"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crafting/materials/zinc_block_from_compacting"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crushing/asurine"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crushing/asurine_recycling"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crushing/tuff"));
        RecipeRemoval.remove(new RemoveFilter().id("create:crushing/tuff_recycling"));
        RecipeRemoval.remove(new RemoveFilter().id("create:splashing/crushed_raw_zinc"));
        // 移除机械动力原版蒸汽引擎配方，统一使用 CTNH 自定义序列组装（ctnhcore:bronze_machine_casing_to_steam_engine）
        RecipeRemoval.remove(new RemoveFilter().idRegex("create:.*steam_engine.*"));
    }

    public static void init(Consumer<FinishedRecipe> provider) {
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
                com.mo_guang.ctpp.data.recipe.builder.create.CrushingRecipeBuilder
                        .builder(CTNHCore.id("crushing_" + i + "_dust"))
                        .input(ingot).output(dust).save(provider);
                com.mo_guang.ctpp.data.recipe.builder.create.MillingRecipeBuilder
                        .builder(CTNHCore.id("milling_" + i + "_dust"))
                        .input(ingot).output(dust).save(provider);
            }
        }

        // andesite_alloy
        ItemStack aaIngot = ChemicalHelper.get(TagPrefix.ingot, CreateMaterials.AndesiteAlloy);
        ItemStack aaDust = ChemicalHelper.get(TagPrefix.dust, CreateMaterials.AndesiteAlloy);
        if (!aaIngot.isEmpty() && !aaDust.isEmpty()) {
            com.mo_guang.ctpp.data.recipe.builder.create.CrushingRecipeBuilder
                    .builder(CTNHCore.id("crushing_andesite_alloy_dust"))
                    .input(aaIngot).output(aaDust).save(provider);
            com.mo_guang.ctpp.data.recipe.builder.create.MillingRecipeBuilder
                    .builder(CTNHCore.id("milling_andesite_alloy_dust"))
                    .input(aaIngot).output(aaDust).save(provider);
        }

        // coke
        ItemStack cokeGem = ChemicalHelper.get(TagPrefix.gem, GTMaterials.Coke);
        ItemStack cokeDust = ChemicalHelper.get(TagPrefix.dust, GTMaterials.Coke);
        if (!cokeGem.isEmpty() && !cokeDust.isEmpty()) {
            com.mo_guang.ctpp.data.recipe.builder.create.CrushingRecipeBuilder
                    .builder(CTNHCore.id("crushing_coke_dust"))
                    .input(cokeGem).output(cokeDust).save(provider);
            com.mo_guang.ctpp.data.recipe.builder.create.MillingRecipeBuilder.builder(CTNHCore.id("milling_coke_dust"))
                    .input(cokeGem).output(cokeDust).save(provider);
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
                com.mo_guang.ctpp.data.recipe.builder.create.CrushingRecipeBuilder
                        .builder(CTNHCore.id("crushing_gtceu_" + i + "_to_mc")).input(gtIngot).output(mcIngot)
                        .save(provider);
                com.mo_guang.ctpp.data.recipe.builder.create.MillingRecipeBuilder
                        .builder(CTNHCore.id("milling_gtceu_" + i + "_to_mc")).input(gtIngot).output(mcIngot)
                        .save(provider);
            }
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
                com.mo_guang.ctpp.data.recipe.builder.create.CuttingRecipeBuilder
                        .builder(CTNHCore.id("cutting_" + p + "_to_single_wire")).input(plate)
                        .result(new ItemStack(wire.getItem(), 2)).save(provider);
            }
        }

        // martial morality eye (7x7 pattern from server_scripts create.js)
        ItemStack drillingMachine = item("createoreexcavation:drilling_machine") == null ? ItemStack.EMPTY :
                new ItemStack(item("createoreexcavation:drilling_machine"));
        ItemStack martialMoralityEye = MultiblocksA.MARTIAL_MORALITY_EYE.asStack();
        if (!drillingMachine.isEmpty() && !martialMoralityEye.isEmpty()) {
            com.mo_guang.ctpp.data.recipe.builder.create.MechanicalCraftingRecipeBuilder
                    .builder(CTNHCore.id("martial_morality_eye"))
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
                if (!maybeIngot.isEmpty()) com.mo_guang.ctpp.data.recipe.builder.create.CompactingRecipeBuilder
                        .builder(CTNHCore.id("compacting_" + i + "_plate"))
                        .input(maybeIngot).output(plate).save(provider);
            }
        }

        // pressing rings
        com.mo_guang.ctpp.data.recipe.builder.create.CompactingRecipeBuilder.builder(CTNHCore.id("pressing_gold_ring"))
                .input(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Gold))
                .output(ChemicalHelper.get(TagPrefix.ring, GTMaterials.Gold)).save(provider);
        com.mo_guang.ctpp.data.recipe.builder.create.CompactingRecipeBuilder.builder(CTNHCore.id("pressing_iron_ring"))
                .input(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Iron))
                .output(ChemicalHelper.get(TagPrefix.ring, GTMaterials.Iron)).save(provider);
        com.mo_guang.ctpp.data.recipe.builder.create.CompactingRecipeBuilder
                .builder(CTNHCore.id("pressing_copper_ring"))
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
            com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder.builder(CTNHCore.id("potin_from_dusts"))
                    .input(new ItemStack(copperDust.getItem(), 6))
                    .input(new ItemStack(tinDust.getItem(), 2))
                    .input(leadDust)
                    .output(new ItemStack(potinDust.getItem(), 8)).save(provider);
        }

        // rose quartz from rose quartz chunk + water
        ItemStack roseQuartz = AllItems.ROSE_QUARTZ.asStack();
        ItemStack roseChunk = item("biomesoplenty:rose_quartz_chunk") == null ? ItemStack.EMPTY :
                new ItemStack(item("biomesoplenty:rose_quartz_chunk"));
        if (!roseChunk.isEmpty() && !roseQuartz.isEmpty()) {
            com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder
                    .builder(CTNHCore.id("rose_quartz_from_chunk_and_water"))
                    .result(roseQuartz)
                    .input(roseChunk)
                    .inputFluid(GTMaterials.Water.getFluid(100))
                    .save(provider);
        }

        // concrete fluid result mixing (gtceu:concrete)
        com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder
                .builder(CTNHCore.id("mixing_concrete_from_dusts"))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Stone))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.QuartzSand))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Clay))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Calcite))
                .inputFluid(GTMaterials.Water.getFluid(1000))
                .resultFluid(GTMaterials.Concrete.getFluid(1000))
                .heatRequirement("heated")
                .save(provider);

        // stem cells from growth medium fluid + animal excreta
        com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder
                .builder(CTNHCore.id("stem_cells_from_growth_medium"))
                .result(GTItems.STEM_CELLS.asStack())
                .inputFluid(CTNHMaterials.SimpleGrowthMedium.getFluid(144))
                .input(CTNHItems.ANIMAL_EXCRETA.asItem())
                .save(provider);

        // Sequenced assembly recipes

        // item_application: shadow steel casing
        com.mo_guang.ctpp.data.recipe.builder.create.ItemApplicationRecipeBuilder
                .builder(CTNHCore.id("shadow_steel_casing_item_application"))
                .input(Items.OBSIDIAN)
                .input(ChemicalHelper.get(TagPrefix.plate, CreateMaterials.ShadowSteel).getItem())
                .output(AllBlocks.SHADOW_STEEL_CASING.asStack())
                .save(provider);

        // mixing: chromatic compound
        com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder
                .builder(CTNHCore.id("chromatic_compound_from_lava"))
                .result(AllItems.CHROMATIC_COMPOUND.asStack(4))
                .inputFluid(GTMaterials.Lava.getFluid(500))
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Netherite))
                .input(ChemicalHelper.get(TagPrefix.ingot, CreateMaterials.AndesiteAlloy))
                .input(AllItems.POLISHED_ROSE_QUARTZ.asStack())
                .save(provider);

        // mixing: aqua regia gold nugget extraction
        com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder
                .builder(CTNHCore.id("gold_nuggets_from_aqua_regia"))
                .result(new ItemStack(ChemicalHelper.get(TagPrefix.nugget, GTMaterials.Gold).getItem(), 5))
                .inputFluid(GTMaterials.AquaRegia.getFluid(500))
                .input(new ItemStack(ChemicalHelper.get(TagPrefix.dust, CreateMaterials.OCHRUM).getItem(), 2))
                .save(provider);

        // splashing series (ores -> outputs)
        ItemStack asurineIn = ChemicalHelper.get(TagPrefix.dust, CreateMaterials.ASURINE);
        ItemStack asurineSil = ChemicalHelper.get(TagPrefix.dust, GTMaterials.SiliconDioxide);
        ItemStack asurineZn = ChemicalHelper.get(TagPrefix.nugget, GTMaterials.Zinc);
        if (!asurineIn.isEmpty() && !asurineSil.isEmpty()) {
            com.mo_guang.ctpp.data.recipe.builder.create.SplashingRecipeBuilder
                    .builder(CTNHCore.id("splashing_asurine"))
                    .input(asurineIn)
                    .result(asurineSil)
                    .result(new ItemStack(asurineZn.getItem(), 4), 0.5)
                    .save(provider);
        }

        ItemStack crimsiteIn = ChemicalHelper.get(TagPrefix.dust, CreateMaterials.CRIMSITE);
        ItemStack crimsiteSil = ChemicalHelper.get(TagPrefix.dust, GTMaterials.SiliconDioxide);
        ItemStack crimsiteIron = ChemicalHelper.get(TagPrefix.nugget, GTMaterials.Iron);
        if (!crimsiteIn.isEmpty() && !crimsiteSil.isEmpty()) {
            com.mo_guang.ctpp.data.recipe.builder.create.SplashingRecipeBuilder
                    .builder(CTNHCore.id("splashing_crimsite"))
                    .input(crimsiteIn)
                    .result(crimsiteSil)
                    .result(new ItemStack(crimsiteIron.getItem(), 4), 0.5)
                    .save(provider);
        }

        ItemStack ochrumIn = ChemicalHelper.get(TagPrefix.dust, CreateMaterials.OCHRUM);
        ItemStack ochrumSil = ChemicalHelper.get(TagPrefix.dust, GTMaterials.SiliconDioxide);
        ItemStack ochrumPrec = ChemicalHelper.get(TagPrefix.nugget, GTMaterials.Gold);
        if (!ochrumIn.isEmpty() && !ochrumSil.isEmpty()) {
            com.mo_guang.ctpp.data.recipe.builder.create.SplashingRecipeBuilder.builder(CTNHCore.id("splashing_ochrum"))
                    .input(ochrumIn)
                    .result(ochrumSil)
                    .result(new ItemStack(ochrumPrec.getItem(), 4), 0.5)
                    .save(provider);
        }

        ItemStack veridiumIn = ChemicalHelper.get(TagPrefix.dust, CreateMaterials.VERIDIUM);
        ItemStack veridiumSil = ChemicalHelper.get(TagPrefix.dust, GTMaterials.SiliconDioxide);
        ItemStack veridiumCu = ChemicalHelper.get(TagPrefix.nugget, GTMaterials.Copper);
        if (!veridiumIn.isEmpty() && !veridiumSil.isEmpty()) {
            com.mo_guang.ctpp.data.recipe.builder.create.SplashingRecipeBuilder
                    .builder(CTNHCore.id("splashing_veridium"))
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
            com.mo_guang.ctpp.data.recipe.builder.create.CrushingRecipeBuilder builder = com.mo_guang.ctpp.data.recipe.builder.create.CrushingRecipeBuilder
                    .builder(CTNHCore.id("crushing_tuff_to_deepslate"));
            builder.input(tuff).result(deepslateDust);
            if (!flintDust.isEmpty()) builder.result(flintDust, 0.25);
            builder.save(provider);
        }

        // deepslate splashing (many outputs with chances)
        ItemStack deepslateIn = ChemicalHelper.get(TagPrefix.dust, GTMaterials.Deepslate);
        if (!deepslateIn.isEmpty()) {
            com.mo_guang.ctpp.data.recipe.builder.create.SplashingRecipeBuilder splashBuilder = com.mo_guang.ctpp.data.recipe.builder.create.SplashingRecipeBuilder
                    .builder(CTNHCore.id("splashing_deepslate"));
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
            com.mo_guang.ctpp.data.recipe.builder.create.SplashingRecipeBuilder splash = com.mo_guang.ctpp.data.recipe.builder.create.SplashingRecipeBuilder
                    .builder(CTNHCore.id("splashing_precious_alloy_gold")).input(preciousIn);
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
        com.mo_guang.ctpp.data.recipe.builder.create.MillingRecipeBuilder
                .builder(CTNHCore.id("milling_obsidian_chance"))
                .input(Blocks.OBSIDIAN.asItem())
                .result(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Obsidian), 0.75)
                .save(provider);

        // ender pearl dust -> ender eye dust
        ItemStack enderPearlDust = ChemicalHelper.get(TagPrefix.dust, GTMaterials.EnderPearl);
        ItemStack enderEyeDust = ChemicalHelper.get(TagPrefix.dust, GTMaterials.EnderEye);
        if (!enderPearlDust.isEmpty() && !enderEyeDust.isEmpty()) {
            com.mo_guang.ctpp.data.recipe.builder.create.SequencedAssemblyRecipeBuilder
                    .builder(CTNHCore.id("ender_pearl_to_eye_dust"))
                    .input(enderPearlDust)
                    .transitional(enderPearlDust)
                    .result(enderEyeDust)
                    .filling(enderPearlDust, GTMaterials.Blaze.getFluid(288))
                    .pressing()
                    .loops(1)
                    .save(provider);
        }

        // small gallium arsenide -> diode
        ItemStack smallGa = ChemicalHelper.get(TagPrefix.dustSmall, GTMaterials.GalliumArsenide);
        ItemStack diode = GTItems.DIODE.asStack();
        if (!smallGa.isEmpty() && !diode.isEmpty()) {
            com.mo_guang.ctpp.data.recipe.builder.create.SequencedAssemblyRecipeBuilder
                    .builder(CTNHCore.id("gallium_arsenide_to_diodes"))
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
            com.mo_guang.ctpp.data.recipe.builder.create.SequencedAssemblyRecipeBuilder
                    .builder(CTNHCore.id("high_strength_concrete_to_sintering_kiln"))
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
            com.mo_guang.ctpp.data.recipe.builder.create.SequencedAssemblyRecipeBuilder
                    .builder(CTNHCore.id("orange_glass_to_bronze_framed"))
                    .input(new ItemStack(Blocks.GLASS.asItem()))
                    .transitional(orangeGlass)
                    .result(bronzeFramed)
                    .deploying(ChemicalHelper.get(TagPrefix.pipeTinyFluid, GTMaterials.Bronze))
                    .deploying(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Bronze))
                    .deploying(ChemicalHelper.get(TagPrefix.rod, GTMaterials.Bronze))
                    .loops(2)
                    .save(provider);
        }

        // steam machine casing -> industrial steam casing
        ItemStack steamMachineCasing = GTBlocks.CASING_BRONZE_BRICKS.asStack();
        ItemStack industrialSteam = GCYMBlocks.CASING_INDUSTRIAL_STEAM.asStack();
        if (!steamMachineCasing.isEmpty() && !industrialSteam.isEmpty()) {
            com.mo_guang.ctpp.data.recipe.builder.create.SequencedAssemblyRecipeBuilder
                    .builder(CTNHCore.id("steam_machine_casing_to_industrial"))
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
            com.mo_guang.ctpp.data.recipe.builder.create.SequencedAssemblyRecipeBuilder
                    .builder(CTNHCore.id("double_blaze_cake_from_blaze_cake"))
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
            com.mo_guang.ctpp.data.recipe.builder.create.SequencedAssemblyRecipeBuilder
                    .builder(CTNHCore.id("ulv_input_bus_from_wooden_chest"))
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
            com.mo_guang.ctpp.data.recipe.builder.create.SequencedAssemblyRecipeBuilder
                    .builder(CTNHCore.id("ulv_input_hatch_from_bronze_drum"))
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
            com.mo_guang.ctpp.data.recipe.builder.create.SequencedAssemblyRecipeBuilder
                    .builder(CTNHCore.id("tungsten_steel_frame_sequence"))
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
            com.mo_guang.ctpp.data.recipe.builder.create.SequencedAssemblyRecipeBuilder
                    .builder(CTNHCore.id("tungstensteel_gearbox_sequence"))
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
        com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder
                .builder(CTNHCore.id("createcafe_blueberry_tea"))
                .input(AetherItems.BLUE_BERRY.get())
                .inputFluid(GTMaterials.Milk.getFluid(250))
                .inputFluid(FluidRegistry.MELTED_SUGAR.get(), 250)
                .resultFluid(FluidRegistry.BLUEBERRY_TEA.get(), 500)
                .save(provider);
        com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder.builder(CTNHCore.id("createcafe_coconut_tea"))
                .input(ModItems.COCONUT_SLICE.get())
                .inputFluid(GTMaterials.Milk.getFluid(250))
                .inputFluid(FluidRegistry.MELTED_SUGAR.get(), 250)
                .resultFluid(FluidRegistry.COCONUT_TEA.get(), 500)
                .save(provider);
        com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder
                .builder(CTNHCore.id("createcafe_coconut_syrup"))
                .input(ModItems.COCONUT_SLICE.get())
                .inputFluid(GTMaterials.Milk.getFluid(250))
                .inputFluid(FluidRegistry.MELTED_SUGAR.get(), 750)
                .resultFluid(FluidRegistry.COCONUT_SYRUP.get(), 1000)
                .save(provider);
        com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder
                .builder(CTNHCore.id("createcafe_pomegranate_tea"))
                .input(BlockRegistry.BOMBEGRANTE_POD.asItem())
                .inputFluid(GTMaterials.Milk.getFluid(250))
                .inputFluid(FluidRegistry.MELTED_SUGAR.get(), 250)
                .resultFluid(FluidRegistry.POMEGRANATE_TEA.get(), 500)
                .save(provider);
        com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder.builder(CTNHCore.id("createcafe_blood_tea"))
                .input(ChemicalHelper.get(TagPrefix.ingot, CTNHMaterials.SNOW_STEEL))
                .inputFluid(GTMaterials.Milk.getFluid(250))
                .inputFluid(FluidRegistry.MELTED_SUGAR.get(), 250)
                .resultFluid(FluidRegistry.BLOOD_TEA.get(), 500)
                .save(provider);
    }

    private static void addCreateFallenRecipes(Consumer<FinishedRecipe> provider) {
        addPhenolicCircuitSequence(provider);
        com.mo_guang.ctpp.data.recipe.builder.create.CrushingRecipeBuilder
                .builder(CTNHCore.id("createfallen_logs_to_wood_dust"))
                .input(Ingredient.of(ItemTags.LOGS))
                .result(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Wood), 0.8)
                .save(provider);
        com.mo_guang.ctpp.data.recipe.builder.create.CrushingRecipeBuilder
                .builder(CTNHCore.id("createfallen_planks_to_wood_dust"))
                .input(Ingredient.of(ItemTags.PLANKS))
                .result(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Wood), 0.4)
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
                CreateDieselGenerators.rl("crafting/basin_lid"),
                new ItemStack(CDGBlocks.BASIN_LID.get()),
                "   ", "ABA", "CDC",
                'A', Items.REDSTONE,
                'B', ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.Steel),
                'C', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel),
                'D', Items.CLOCK);
        com.mo_guang.ctpp.data.recipe.builder.create.ItemApplicationRecipeBuilder
                .builder(CreateDieselGenerators.ID + "_heavy_machinery_casing_application")
                .input(CTPPBlocks.STEEL_CASING.asStack())
                .input(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel))
                .output(CTPPBlocks.HEAVY_MACHINERY_CASING.asStack())
                .save(provider);
        com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder
                .builder(CreateDieselGenerators.ID + "_emulsified_bitumen_slurry")
                .inputFluid(BiodieselFertileSoilMaterials.BITUMEN.getFluid(800))
                .inputFluid(BiodieselFertileSoilMaterials.SIMPLE_EMULGATOR.getFluid(200))
                .resultFluid(BiodieselFertileSoilMaterials.EMULSIFIED_BITUMEN_SLURRY.getFluid(1000))
                .save(provider);
        com.mo_guang.ctpp.data.recipe.builder.create.CompactingRecipeBuilder
                .builder(CTNHCore.id(CreateDieselGenerators.ID + "/emulsified_bitumen"))
                .inputFluid(BiodieselFertileSoilMaterials.EMULSIFIED_BITUMEN_SLURRY.getFluid(1000))
                .resultFluid(BiodieselFertileSoilMaterials.EMULSIFIED_BITUMEN.getFluid(1000))
                .result(ChemicalHelper.get(TagPrefix.dust, BiodieselFertileSoilMaterials.OIL_REFINED_RESIDUES, 2))
                .result(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Salt, 1))
                .save(provider);
        com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder
                .builder(CreateDieselGenerators.ID + "_pure_bitumen")
                .inputFluid(BiodieselFertileSoilMaterials.EMULSIFIED_BITUMEN.getFluid(800))
                .inputFluid(BiodieselFertileSoilMaterials.SIMPLE_DEMULSIFIER.getFluid(200))
                .resultFluid(BiodieselFertileSoilMaterials.PURE_BITUMEN.getFluid(1000))
                .save(provider);
        com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder
                .builder(CreateDieselGenerators.ID + "_simple_emulgator")
                .input(Ingredient.of(ChemicalHelper.get(TagPrefix.dust, GTMaterials.SodaAsh, 6)))
                .input(CTNHItems.TALLOW.asStack())
                .inputFluid(GTMaterials.Water.getFluid(1000))
                .resultFluid(BiodieselFertileSoilMaterials.SIMPLE_EMULGATOR.getFluid(1000))
                .save(provider);
        com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder
                .builder(CreateDieselGenerators.ID + "_simple_demulsifier")
                .input(CTNHItems.TALLOW.asStack())
                .inputFluid(GTMaterials.DilutedSulfuricAcid.getFluid(1000))
                .resultFluid(BiodieselFertileSoilMaterials.SIMPLE_DEMULSIFIER.getFluid(1000))
                .save(provider);
        com.mo_guang.ctpp.data.recipe.builder.create.CompactingRecipeBuilder
                .builder(CTNHCore.id(CreateDieselGenerators.ID + "/petroleum_coke_gem"))
                .inputFluid(BiodieselFertileSoilMaterials.PETROLEUM_COKE.getFluid(144))
                .result(ChemicalHelper.get(TagPrefix.gem, BiodieselFertileSoilMaterials.PETROLEUM_COKE))
                .save(provider);
        com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder
                .builder(CreateDieselGenerators.ID + "_asphalt_block")
                .input(Items.SAND)
                .input(Items.GRAVEL)
                .inputFluid(BiodieselFertileSoilMaterials.PURE_BITUMEN.getFluid(100))
                .output(new ItemStack(CDGBlocks.ASPHALT_BLOCK.get(), 4))
                .save(provider);
        com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder
                .builder(CreateDieselGenerators.ID + "_raw_bio_diesel")
                .inputFluid(GTMaterials.Ethanol.getFluid(100))
                .inputFluid(GTMaterials.SeedOil.getFluid(100))
                .resultFluid(BiodieselFertileSoilMaterials.RAW_BIO_DIESEL.getFluid(200))
                .save(provider);
        addDieselGeneratorCustomRecipes(provider);
        com.mo_guang.ctpp.data.recipe.builder.create.CrushingRecipeBuilder
                .builder(CreateDieselGenerators.ID + "_rich_soil_dust")
                .input(Ingredient.of(vectorwing.farmersdelight.common.registry.ModItems.RICH_SOIL.get()))
                .output(ChemicalHelper.get(TagPrefix.dust, BiodieselFertileSoilMaterials.RICH_SOIL, 3))
                .save(provider);
        com.mo_guang.ctpp.data.recipe.builder.create.CrushingRecipeBuilder
                .builder(CreateDieselGenerators.ID + "_rich_soul_soil_dust")
                .input(Ingredient.of(MNDItems.RESURGENT_SOIL.get()))
                .output(ChemicalHelper.get(TagPrefix.dust, BiodieselFertileSoilMaterials.RICH_SOUL_SOIL, 3))
                .save(provider);
    }

    private static void addPhenolicCircuitSequence(Consumer<FinishedRecipe> provider) {
        ItemStack phenolicBoard = GTItems.PHENOLIC_BOARD.asStack();
        com.mo_guang.ctpp.data.recipe.builder.create.SequencedAssemblyRecipeBuilder.builder(
                CTNHCore.id("createfallen/good_electronic_circuit_from_phenolic_board"))
                .input(phenolicBoard)
                .transitional(phenolicBoard)
                .result(GTItems.ELECTRONIC_CIRCUIT_MV.asStack())
                .deploying(ChemicalHelper.get(TagPrefix.wireGtDouble, GTMaterials.Silver, 1))
                .deploying(ChemicalHelper.get(TagPrefix.wireGtSingle, GTMaterials.Copper, 1))
                .deploying(GTItems.ELECTRONIC_CIRCUIT_LV.asStack())
                .deploying(GTItems.DIODE.asStack())
                .filling(phenolicBoard, GTMaterials.Tin.getFluid(144))
                .loops(2)
                .save(provider);
    }

    private static void addDieselGeneratorCustomRecipes(Consumer<FinishedRecipe> provider) {
        // BasinFermentingRecipeBuilder.builder(CreateDieselGenerators.rl("basin_fermenting/fermentable"))
        // .input(TagUtil.createItemTag("fermentable", false))
        // .inputFluid(GTMaterials.Water.getFluid(200))
        // .input(itemStack("ctnhcore:small_rich_soil_dust"))
        // .duration(200)
        // .resultFluid(BiodieselFertileSoilMaterials.ETHANOL_MIXTURE.getFluid(200))
        // .result("ctnhcore:normal_yeast_dust", 1, 0.1)
        // .result("ctnhcore:small_rich_soil_dust", 1, 0.8)
        // .save(provider);

        // DistillationRecipeBuilder.builder(CTNHCore.id(CreateDieselGenerators.ID + "/ethanol_mixture_distillation"))
        // .inputFluid(BiodieselFertileSoilMaterials.ETHANOL_MIXTURE.getFluid(100))
        // .heatRequirement(HeatCondition.HEATED)
        // .duration(200)
        // .resultFluid(GTMaterials.FermentedBiomass.getFluid(30))
        // .resultFluid(GTMaterials.Ethanol.getFluid(50))
        // .resultFluid(GTMaterials.Methanol.getFluid(20))
        // .save(provider);
    }

    private static JsonArray stackElements(ItemStack... stacks) {
        JsonArray array = new JsonArray();
        for (ItemStack stack : stacks) {
            array.add(Ingredient.of(stack).toJson());
        }
        return array;
    }

    private static void addHeatedQuartzGlassMixing(Consumer<FinishedRecipe> provider) {
        com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder
                .builder(CTNHCore.id("mixing/quartz_glass_dust"))
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
}
