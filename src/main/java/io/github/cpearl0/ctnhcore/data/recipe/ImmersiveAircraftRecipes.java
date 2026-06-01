package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import com.mo_guang.ctpp.common.recipe.builder.create.*;
import com.mo_guang.ctpp.registry.CTPPItems;
import com.mo_guang.ctpp.registry.CTPPMaterials;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;

import java.util.function.Consumer;

import static immersive_aircraft.Items.*;

public class ImmersiveAircraftRecipes {

    private static final TagKey<Item> LOGS_TAG = TagKey.create(Registries.ITEM,
            ResourceLocation.parse("minecraft:logs"));
    private static final TagKey<Item> SCREWDRIVERS_TAG = TagKey.create(Registries.ITEM,
            ResourceLocation.parse("forge:tools/screwdrivers"));
    private static final TagKey<Item> HAMMERS_TAG = TagKey.create(Registries.ITEM,
            ResourceLocation.parse("forge:tools/hammers"));
    private static final TagKey<Item> WIRE_CUTTERS_TAG = TagKey.create(Registries.ITEM,
            ResourceLocation.parse("forge:tools/wire_cutters"));
    private static final TagKey<Item> FILES_TAG = TagKey.create(Registries.ITEM,
            ResourceLocation.parse("forge:tools/files"));
    private static final TagKey<Item> WRENCHES_TAG = TagKey.create(Registries.ITEM,
            ResourceLocation.parse("forge:tools/wrenches"));
    private static final TagKey<Item> ANDESITE_ALLOY_PLATES_TAG = TagKey.create(Registries.ITEM,
            ResourceLocation.parse("forge:plates/andesite_alloy"));
    private static final TagKey<Item> MALLETS_TAG = TagKey.create(Registries.ITEM,
            ResourceLocation.parse("forge:tools/mallets"));

    private static final ItemStack PRECISION_MECHANISM = AllItems.PRECISION_MECHANISM.asStack();
    private static final ItemStack MECHANICAL_BEARING = AllBlocks.MECHANICAL_BEARING.asStack();
    private static final ItemStack FLUID_PIPE = AllBlocks.FLUID_PIPE.asStack();
    private static final ItemStack POWERED_LATCH = AllBlocks.POWERED_LATCH.asStack();
    private static final ItemStack POWERED_TOGGLE_LATCH = AllBlocks.POWERED_TOGGLE_LATCH.asStack();
    private static final ItemStack BLAZE_BURNER = AllBlocks.BLAZE_BURNER.asStack();

    private static final ItemStack ANDESITE_ALLOY_PLATE = ChemicalHelper.get(TagPrefix.plate,
            CTPPMaterials.AndesiteAlloy);
    private static final ItemStack BRONZE_FIREBOX_CASING = GTBlocks.FIREBOX_BRONZE.asStack();
    private static final ItemStack STEEL_FIREBOX_CASING = GTBlocks.FIREBOX_STEEL.asStack();
    private static final ItemStack SMALL_COPPER_GEAR = ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.Copper);
    private static final ItemStack SMALL_IRON_GEAR = ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.Iron);

    private static final ItemStack BASIC_MECHANISM = CTPPItems.BASIC_MECHANISM.asStack();

    public static void init(Consumer<FinishedRecipe> provider) {
        shapedRecipes(provider);
        sequencedAssembly(provider);
        mechanicalCrafting(provider);
    }

    private static void shapedRecipes(Consumer<FinishedRecipe> provider) {
        // hull
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/hull"),
                new ItemStack(HULL.get()),
                "DQD", "SWA", "DQD",
                'D', LOGS_TAG,
                'Q', ANDESITE_ALLOY_PLATE,
                'S', SCREWDRIVERS_TAG,
                'W', BASIC_MECHANISM,
                'A', HAMMERS_TAG);

        // propeller
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/propeller"),
                new ItemStack(PROPELLER.get()),
                "SDA", "DWD", " D ",
                'S', SCREWDRIVERS_TAG,
                'D', ANDESITE_ALLOY_PLATE,
                'A', HAMMERS_TAG,
                'W', BASIC_MECHANISM);

        // sail
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/sail"),
                new ItemStack(SAIL.get()),
                "DDW", "DDA", "DDW",
                'D', new ItemStack(Items.LEATHER),
                'W', new ItemStack(Items.STRING),
                'A', WIRE_CUTTERS_TAG);

        // gyrodyne
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/gyrodyne"),
                new ItemStack(GYRODYNE.get()),
                "SAE", "QWD", "ZAX",
                'S', FILES_TAG,
                'A', new ItemStack(SAIL.get()),
                'E', HAMMERS_TAG,
                'Q', MECHANICAL_BEARING,
                'W', new ItemStack(PROPELLER.get()),
                'D', new ItemStack(HULL.get()),
                'Z', SCREWDRIVERS_TAG,
                'X', WRENCHES_TAG);

        // sturdy_pipes
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/sturdy_pipes"),
                new ItemStack(STURDY_PIPES.get()),
                "SAW", "DDD", "WQE",
                'S', MALLETS_TAG,
                'A', HAMMERS_TAG,
                'W', ANDESITE_ALLOY_PLATES_TAG,
                'D', FLUID_PIPE,
                'Q', FILES_TAG,
                'E', WRENCHES_TAG);

        // boiler
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/boiler"),
                new ItemStack(BOILER.get()),
                "DDD", "DAD", "DWD",
                'D', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Bronze),
                'A', WRENCHES_TAG,
                'W', BRONZE_FIREBOX_CASING);

        // enhanced_propeller
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/enhanced_propeller"),
                new ItemStack(ENHANCED_PROPELLER.get()),
                "SAE", "AWA", "ZAX",
                'S', FILES_TAG,
                'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Brass),
                'E', HAMMERS_TAG,
                'W', new ItemStack(PROPELLER.get()),
                'Z', SCREWDRIVERS_TAG,
                'X', WRENCHES_TAG);

        // gyroscope
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/gyroscope"),
                new ItemStack(GYROSCOPE.get()),
                "AWD", "QSE", " X ",
                'A', HAMMERS_TAG,
                'W', WRENCHES_TAG,
                'D', SCREWDRIVERS_TAG,
                'Q', POWERED_LATCH,
                'S', new ItemStack(Items.COMPASS),
                'E', POWERED_TOGGLE_LATCH,
                'X', new ItemStack(Items.COMPARATOR));

        // industrial_gears
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/industrial_gears"),
                new ItemStack(INDUSTRIAL_GEARS.get()),
                "AWD", "QS ", "   ",
                'A', FILES_TAG,
                'W', SMALL_COPPER_GEAR,
                'D', WRENCHES_TAG,
                'Q', SMALL_IRON_GEAR,
                'S', HAMMERS_TAG);

        // heavy_crossbow
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/heavy_crossbow"),
                new ItemStack(HEAVY_CROSSBOW.get()),
                "AWD", "QSE", "AZD",
                'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Iron),
                'W', HAMMERS_TAG,
                'D', LOGS_TAG,
                'Q', FILES_TAG,
                'S', new ItemStack(Items.CROSSBOW),
                'E', new ItemStack(Items.TRIPWIRE_HOOK),
                'Z', SCREWDRIVERS_TAG);

        // steel_boiler
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/steel_boiler"),
                new ItemStack(STEEL_BOILER.get()),
                "DDD", "SAS", "DWD",
                'D', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel),
                'S', new ItemStack(BOILER.get()),
                'A', WRENCHES_TAG,
                'W', STEEL_FIREBOX_CASING);

        // bomb_bay
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/bomb_bay"),
                new ItemStack(BOMB_BAY.get()),
                "QDW", "DAD", "EDS",
                'Q', HAMMERS_TAG,
                'D', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel),
                'W', FILES_TAG,
                'A', new ItemStack(Items.TNT),
                'E', WRENCHES_TAG,
                'S', ChemicalHelper.get(TagPrefix.rod, GTMaterials.Steel));

        // improved_landing_gear
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/improved_landing_gear"),
                new ItemStack(IMPROVED_LANDING_GEAR.get()),
                "QEW", "AWW", "SAD",
                'Q', HAMMERS_TAG,
                'E', WRENCHES_TAG,
                'W', ChemicalHelper.get(TagPrefix.rod, GTMaterials.Brass),
                'A', ChemicalHelper.get(TagPrefix.screw, GTMaterials.Steel),
                'S', ChemicalHelper.get(TagPrefix.ring, GTMaterials.Steel),
                'D', SCREWDRIVERS_TAG);
    }

    private static void sequencedAssembly(Consumer<FinishedRecipe> provider) {
        // engine: polished_blackstone_slab → engine
        SequencedAssemblyRecipeBuilder.builder("immersive_aircraft/engine")
                .input(new ItemStack(Items.POLISHED_BLACKSTONE_SLAB))
                .transitional(new ItemStack(Items.POLISHED_BLACKSTONE_SLAB))
                .result(new ItemStack(ENGINE.get()))
                .deploying(new ItemStack(Items.PISTON))
                .deploying(new ItemStack(Items.PISTON))
                .deploying(new ItemStack(BOILER.get()))
                .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel))
                .pressing()
                .loops(1)
                .save(provider);

        // hull_reinforcement: hull → hull_reinforcement
        SequencedAssemblyRecipeBuilder.builder("immersive_aircraft/hull_reinforcement")
                .input(new ItemStack(HULL.get()))
                .transitional(new ItemStack(HULL.get()))
                .result(new ItemStack(HULL_REINFORCEMENT.get()))
                .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Brass))
                .deploying(ChemicalHelper.get(TagPrefix.screw, GTMaterials.Brass))
                .pressing()
                .loops(3)
                .save(provider);

        // eco_engine: engine → eco_engine
        SequencedAssemblyRecipeBuilder.builder("immersive_aircraft/eco_engine")
                .input(new ItemStack(ENGINE.get()))
                .transitional(new ItemStack(ENGINE.get()))
                .result(new ItemStack(ECO_ENGINE.get()))
                .deploying(ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.Gold))
                .deploying(new ItemStack(Items.BRICK))
                .deploying(PRECISION_MECHANISM)
                .pressing()
                .loops(1)
                .save(provider);

        // nether_engine: engine → nether_engine
        SequencedAssemblyRecipeBuilder.builder("immersive_aircraft/nether_engine")
                .input(new ItemStack(ENGINE.get()))
                .transitional(new ItemStack(ENGINE.get()))
                .result(new ItemStack(NETHER_ENGINE.get()))
                .deploying(new ItemStack(Items.BLAZE_ROD))
                .deploying(new ItemStack(Items.NETHERITE_INGOT))
                .deploying(BLAZE_BURNER)
                .deploying(CTPPItems.STEEL_MECHANISM.asStack())
                .pressing()
                .loops(1)
                .save(provider);

        // rotary_cannon: double_copper_plate → rotary_cannon
        SequencedAssemblyRecipeBuilder.builder("immersive_aircraft/rotary_cannon")
                .input(ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.Copper))
                .transitional(ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.Copper))
                .result(new ItemStack(ROTARY_CANNON.get()))
                .deploying(new ItemStack(INDUSTRIAL_GEARS.get()))
                .deploying(new ItemStack(Items.DISPENSER))
                .deploying(PRECISION_MECHANISM)
                .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Copper))
                .pressing()
                .loops(1)
                .save(provider);

        // telescope: double_iron_plate → telescope
        SequencedAssemblyRecipeBuilder.builder("immersive_aircraft/telescope")
                .input(ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.Iron))
                .transitional(ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.Iron))
                .result(new ItemStack(TELESCOPE.get()))
                .deploying(new ItemStack(INDUSTRIAL_GEARS.get()))
                .deploying(PRECISION_MECHANISM)
                .deploying(new ItemStack(Blocks.GLASS.asItem()))
                .pressing()
                .loops(1)
                .save(provider);
    }

    private static void mechanicalCrafting(Consumer<FinishedRecipe> provider) {
        // quadrocopter
        MechanicalCraftingRecipeBuilder.builder("immersive_aircraft/quadrocopter")
                .pattern("QAQ", "SWS", "SAS", "QEQ")
                .key('Q', new ItemStack(ENHANCED_PROPELLER.get()))
                .key('W', new ItemStack(ENGINE.get()))
                .key('E', PRECISION_MECHANISM)
                .key('A', new ItemStack(HULL_REINFORCEMENT.get()))
                .key('S', new ItemStack(INDUSTRIAL_GEARS.get()))
                .output(new ItemStack(QUADROCOPTER.get()))
                .save(provider);

        // airship
        MechanicalCraftingRecipeBuilder.builder("immersive_aircraft/airship")
                .pattern(" QDQ ", " SWS ", "A E A", "AAAAA")
                .key('Q', new ItemStack(SAIL.get()))
                .key('W', new ItemStack(ENGINE.get()))
                .key('A', new ItemStack(HULL.get()))
                .key('D', new ItemStack(ENHANCED_PROPELLER.get()))
                .key('S', new ItemStack(PROPELLER.get()))
                .key('E', PRECISION_MECHANISM)
                .output(new ItemStack(AIRSHIP.get()))
                .save(provider);

        // cargo_airship
        MechanicalCraftingRecipeBuilder.builder("immersive_aircraft/cargo_airship")
                .pattern(" WQW ", " DWD ", "SEAES", "SERES")
                .key('A', new ItemStack(AIRSHIP.get()))
                .key('S', new ItemStack(Blocks.CHEST.asItem()))
                .key('D', new ItemStack(ENHANCED_PROPELLER.get()))
                .key('Q', new ItemStack(PROPELLER.get()))
                .key('W', new ItemStack(SAIL.get()))
                .key('E', new ItemStack(HULL_REINFORCEMENT.get()))
                .key('R', new ItemStack(ECO_ENGINE.get()))
                .output(new ItemStack(CARGO_AIRSHIP.get()))
                .save(provider);

        // warship
        MechanicalCraftingRecipeBuilder.builder("immersive_aircraft/warship")
                .pattern("ABCBK", "AFEFK", "AGHGK", "JJIJJ", "D   K")
                .key('A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel))
                .key('B', new ItemStack(SAIL.get()))
                .key('C', new ItemStack(ENHANCED_PROPELLER.get()))
                .key('D', new ItemStack(TELESCOPE.get()))
                .key('E', new ItemStack(CARGO_AIRSHIP.get()))
                .key('F', new ItemStack(NETHER_ENGINE.get()))
                .key('G', new ItemStack(STEEL_BOILER.get()))
                .key('H', new ItemStack(INDUSTRIAL_GEARS.get()))
                .key('I', CTPPItems.STEEL_MECHANISM.asStack())
                .key('J', new ItemStack(HULL_REINFORCEMENT.get()))
                .key('K', new ItemStack(PROPELLER.get()))
                .output(new ItemStack(WARSHIP.get()))
                .save(provider);

        // biplane
        MechanicalCraftingRecipeBuilder.builder("immersive_aircraft/biplane")
                .pattern("AABAA", " DCD ", " DED ", " DFD ", "  G  ")
                .key('A', new ItemStack(HULL_REINFORCEMENT.get()))
                .key('B', new ItemStack(PROPELLER.get()))
                .key('C', new ItemStack(INDUSTRIAL_GEARS.get()))
                .key('D', new ItemStack(HULL.get()))
                .key('E', new ItemStack(STEEL_BOILER.get()))
                .key('F', new ItemStack(ENGINE.get()))
                .key('G', new ItemStack(ENHANCED_PROPELLER.get()))
                .output(new ItemStack(BIPLANE.get()))
                .save(provider);
    }
}
