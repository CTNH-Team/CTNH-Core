package io.github.cpearl0.ctnhcore.data.recipe.immersiveaircraft;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import com.mo_guang.ctpp.registry.CTPPItems;
import com.mo_guang.ctpp.registry.CTPPMaterials;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.mo_guang.ctpp.common.recipe.builder.create.MechanicalCraftingRecipeBuilder;
import com.mo_guang.ctpp.common.recipe.builder.create.SequencedAssemblyRecipeBuilder;

import java.util.function.Consumer;

public class ImmersiveAircraftRecipes {

    // 迁移来源：Z:\Git\Create-New-Horizon\kubejs\server_scripts\src\immersive aircraft\fly.js
    // 迁移来源：Z:\Git\Create-New-Horizon\kubejs\server_scripts\src\immersive aircraft\create.js
    public static void init(Consumer<FinishedRecipe> provider) {
        addCraftingTableRecipes(provider);
        addCreateRecipes(provider);
    }

    public static void removals(Consumer<ResourceLocation> registry) {
        // 迁移来源：fly.js 中的 event.remove({mod:'immersive_aircraft'})。
        String[] paths = {
                "hull",
                "propeller",
                "sail",
                "gyrodyne",
                "sturdy_pipes",
                "boiler",
                "enhanced_propeller",
                "gyroscope",
                "industrial_gears",
                "heavy_crossbow",
                "steel_boiler",
                "bomb_bay",
                "improved_landing_gear",
                "engine",
                "hull_reinforcement",
                "eco_engine",
                "nether_engine",
                "rotary_cannon",
                "telescope",
                "quadrocopter",
                "airship",
                "cargo_airship",
                "warship",
                "biplane"
        };
        for (String path : paths) {
            registry.accept(ResourceLocation.fromNamespaceAndPath("immersive_aircraft", path));
        }
    }

    private static void addCraftingTableRecipes(Consumer<FinishedRecipe> provider) {
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/hull"),
                stack(immersive_aircraft.Items.HULL.get()),
                "DQD", "SWA", "DQD",
                'D', ItemTags.LOGS,
                'W', CTPPItems.BASIC_MECHANISM.asStack(),
                'S', CustomTags.SCREWDRIVERS,
                'A', CustomTags.HAMMERS,
                'Q', ChemicalHelper.get(TagPrefix.plate, CTPPMaterials.AndesiteAlloy));
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/propeller"),
                stack(immersive_aircraft.Items.PROPELLER.get()),
                "SDA", "DWD", " D ",
                'D', ChemicalHelper.get(TagPrefix.plate, CTPPMaterials.AndesiteAlloy),
                'W', CTPPItems.BASIC_MECHANISM.asStack(),
                'S', CustomTags.SCREWDRIVERS,
                'A', CustomTags.HAMMERS);
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/sail"),
                stack(immersive_aircraft.Items.SAIL.get()),
                "DDW", "DDA", "DDW",
                'D', Items.LEATHER,
                'W', Items.STRING,
                'A', CustomTags.WIRE_CUTTERS);
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/gyrodyne"),
                stack(immersive_aircraft.Items.GYRODYNE.get()),
                "SAE", "QWD", "ZAX",
                'D', immersive_aircraft.Items.HULL.get(),
                'W', immersive_aircraft.Items.PROPELLER.get(),
                'S', CustomTags.FILES,
                'A', immersive_aircraft.Items.SAIL.get(),
                'Q', AllBlocks.MECHANICAL_BEARING.asStack(),
                'E', CustomTags.HAMMERS,
                'Z', CustomTags.SCREWDRIVERS,
                'X', CustomTags.WRENCHES);
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/sturdy_pipes"),
                stack(immersive_aircraft.Items.STURDY_PIPES.get()),
                "SAW", "DDD", "WQE",
                'D', AllBlocks.FLUID_PIPE.asStack(),
                'W', ChemicalHelper.get(TagPrefix.plate, CTPPMaterials.AndesiteAlloy),
                'S', CustomTags.MALLETS,
                'A', CustomTags.HAMMERS,
                'Q', CustomTags.FILES,
                'E', CustomTags.WRENCHES);
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/boiler"),
                stack(immersive_aircraft.Items.BOILER.get()),
                "DDD", "DAD", "DWD",
                'D', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Bronze),
                'W', GTBlocks.FIREBOX_BRONZE.asStack(),
                'A', CustomTags.WRENCHES);
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/enhanced_propeller"),
                stack(immersive_aircraft.Items.ENHANCED_PROPELLER.get()),
                "SAE", "AWA", "ZAX",
                'W', immersive_aircraft.Items.PROPELLER.get(),
                'S', CustomTags.FILES,
                'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Brass),
                'E', CustomTags.HAMMERS,
                'Z', CustomTags.SCREWDRIVERS,
                'X', CustomTags.WRENCHES);
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/gyroscope"),
                stack(immersive_aircraft.Items.GYROSCOPE.get()),
                "AWD", "QSE", " X ",
                'A', CustomTags.HAMMERS,
                'W', CustomTags.WRENCHES,
                'D', CustomTags.SCREWDRIVERS,
                'Q', AllBlocks.POWERED_LATCH.asStack(),
                'S', Items.COMPASS,
                'E', AllBlocks.POWERED_TOGGLE_LATCH.asStack(),
                'X', Items.COMPARATOR);
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/industrial_gears"),
                stack(immersive_aircraft.Items.INDUSTRIAL_GEARS.get()),
                "AWD", "QS ", "   ",
                'A', CustomTags.FILES,
                'W', ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.Copper),
                'D', CustomTags.WRENCHES,
                'Q', ChemicalHelper.get(TagPrefix.gearSmall, GTMaterials.Iron),
                'S', CustomTags.HAMMERS);
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/heavy_crossbow"),
                stack(immersive_aircraft.Items.HEAVY_CROSSBOW.get()),
                "AWD", "QSE", "AZD",
                'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Iron),
                'W', CustomTags.HAMMERS,
                'D', ItemTags.LOGS,
                'Q', CustomTags.FILES,
                'S', Items.CROSSBOW,
                'E', Items.TRIPWIRE_HOOK,
                'Z', CustomTags.SCREWDRIVERS);
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/steel_boiler"),
                stack(immersive_aircraft.Items.STEEL_BOILER.get()),
                "DDD", "SAS", "DWD",
                'D', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel),
                'W', GTBlocks.FIREBOX_STEEL.asStack(),
                'A', CustomTags.WRENCHES,
                'S', immersive_aircraft.Items.BOILER.get());
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/bomb_bay"),
                stack(immersive_aircraft.Items.BOMB_BAY.get()),
                "QDW", "DAD", "EDS",
                'Q', CustomTags.HAMMERS,
                'W', CustomTags.FILES,
                'E', CustomTags.WRENCHES,
                'A', Items.TNT,
                'S', ChemicalHelper.get(TagPrefix.rod, GTMaterials.Steel),
                'D', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel));
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("immersive_aircraft/improved_landing_gear"),
                stack(immersive_aircraft.Items.IMPROVED_LANDING_GEAR.get()),
                "QEW", "AWW", "SAD",
                'Q', CustomTags.HAMMERS,
                'W', ChemicalHelper.get(TagPrefix.rod, GTMaterials.Brass),
                'E', CustomTags.WRENCHES,
                'A', ChemicalHelper.get(TagPrefix.screw, GTMaterials.Steel),
                'S', ChemicalHelper.get(TagPrefix.ring, GTMaterials.Steel),
                'D', CustomTags.SCREWDRIVERS);
    }

    private static void addCreateRecipes(Consumer<FinishedRecipe> provider) {
        SequencedAssemblyRecipeBuilder.builder("immersive_aircraft/engine")
                .input(Items.POLISHED_BLACKSTONE_SLAB)
                .transitional(Items.POLISHED_BLACKSTONE_SLAB)
                .result(stack(immersive_aircraft.Items.ENGINE.get()))
                .loops(1)
                .deploying(Items.PISTON)
                .deploying(Items.PISTON)
                .deploying(immersive_aircraft.Items.BOILER.get())
                .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel))
                .pressing()
                .save(provider);
        SequencedAssemblyRecipeBuilder.builder("immersive_aircraft/hull_reinforcement")
                .input(immersive_aircraft.Items.HULL.get())
                .transitional(immersive_aircraft.Items.HULL.get())
                .result(stack(immersive_aircraft.Items.HULL_REINFORCEMENT.get(), 3))
                .loops(3)
                .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Brass))
                .deploying(ChemicalHelper.get(TagPrefix.screw, GTMaterials.Brass))
                .pressing()
                .save(provider);
        SequencedAssemblyRecipeBuilder.builder("immersive_aircraft/eco_engine")
                .input(immersive_aircraft.Items.ENGINE.get())
                .transitional(immersive_aircraft.Items.ENGINE.get())
                .result(stack(immersive_aircraft.Items.ECO_ENGINE.get()))
                .loops(1)
                .deploying(ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.Gold))
                .deploying(Items.BRICK)
                .deploying(AllItems.PRECISION_MECHANISM.asStack())
                .pressing()
                .save(provider);
        SequencedAssemblyRecipeBuilder.builder("immersive_aircraft/nether_engine")
                .input(immersive_aircraft.Items.ENGINE.get())
                .transitional(immersive_aircraft.Items.ENGINE.get())
                .result(stack(immersive_aircraft.Items.NETHER_ENGINE.get()))
                .loops(1)
                .deploying(Items.BLAZE_ROD)
                .deploying(Items.NETHERITE_INGOT)
                .deploying(AllBlocks.BLAZE_BURNER.asStack())
                .deploying(CTPPItems.STEEL_MECHANISM.asStack())
                .pressing()
                .save(provider);
        SequencedAssemblyRecipeBuilder.builder("immersive_aircraft/rotary_cannon")
                .input(ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.Copper))
                .transitional(ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.Copper))
                .result(stack(immersive_aircraft.Items.ROTARY_CANNON.get()))
                .loops(1)
                .deploying(immersive_aircraft.Items.INDUSTRIAL_GEARS.get())
                .deploying(Items.DISPENSER)
                .deploying(AllItems.PRECISION_MECHANISM.asStack())
                .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Copper))
                .pressing()
                .save(provider);
        SequencedAssemblyRecipeBuilder.builder("immersive_aircraft/telescope")
                .input(ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.Iron))
                .transitional(ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.Iron))
                .result(stack(immersive_aircraft.Items.TELESCOPE.get()))
                .loops(1)
                .deploying(immersive_aircraft.Items.INDUSTRIAL_GEARS.get())
                .deploying(AllItems.PRECISION_MECHANISM.asStack())
                .deploying(Items.GLASS)
                .pressing()
                .save(provider);

        MechanicalCraftingRecipeBuilder.builder("immersive_aircraft/quadrocopter")
                .pattern("QAQ", "SWS", "SAS", "QEQ")
                .key('Q', immersive_aircraft.Items.ENHANCED_PROPELLER.get())
                .key('W', immersive_aircraft.Items.ENGINE.get())
                .key('E', AllItems.PRECISION_MECHANISM.asStack())
                .key('A', immersive_aircraft.Items.HULL_REINFORCEMENT.get())
                .key('S', immersive_aircraft.Items.INDUSTRIAL_GEARS.get())
                .result(stack(immersive_aircraft.Items.QUADROCOPTER.get()))
                .save(provider);
        MechanicalCraftingRecipeBuilder.builder("immersive_aircraft/airship")
                .pattern(" QDQ ", " SWS ", "A E A", "AAAAA")
                .key('Q', immersive_aircraft.Items.SAIL.get())
                .key('W', immersive_aircraft.Items.ENGINE.get())
                .key('A', immersive_aircraft.Items.HULL.get())
                .key('D', immersive_aircraft.Items.ENHANCED_PROPELLER.get())
                .key('S', immersive_aircraft.Items.PROPELLER.get())
                .key('E', AllItems.PRECISION_MECHANISM.asStack())
                .result(stack(immersive_aircraft.Items.AIRSHIP.get()))
                .save(provider);
        MechanicalCraftingRecipeBuilder.builder("immersive_aircraft/cargo_airship")
                .pattern(" WQW ", " DWD ", "SEAES", "SERES")
                .key('A', immersive_aircraft.Items.AIRSHIP.get())
                .key('S', Items.CHEST)
                .key('D', immersive_aircraft.Items.ENHANCED_PROPELLER.get())
                .key('Q', immersive_aircraft.Items.PROPELLER.get())
                .key('W', immersive_aircraft.Items.SAIL.get())
                .key('E', immersive_aircraft.Items.HULL_REINFORCEMENT.get())
                .key('R', immersive_aircraft.Items.ECO_ENGINE.get())
                .result(stack(immersive_aircraft.Items.CARGO_AIRSHIP.get()))
                .save(provider);
        MechanicalCraftingRecipeBuilder.builder("immersive_aircraft/warship")
                .pattern("ABCBK", "AFEFK", "AGHGK", "JJIJJ", "D   K")
                .key('A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel))
                .key('B', immersive_aircraft.Items.SAIL.get())
                .key('C', immersive_aircraft.Items.ENHANCED_PROPELLER.get())
                .key('D', immersive_aircraft.Items.TELESCOPE.get())
                .key('E', immersive_aircraft.Items.CARGO_AIRSHIP.get())
                .key('F', immersive_aircraft.Items.NETHER_ENGINE.get())
                .key('G', immersive_aircraft.Items.STEEL_BOILER.get())
                .key('H', immersive_aircraft.Items.INDUSTRIAL_GEARS.get())
                .key('I', CTPPItems.STEEL_MECHANISM.asStack())
                .key('J', immersive_aircraft.Items.HULL_REINFORCEMENT.get())
                .key('K', immersive_aircraft.Items.PROPELLER.get())
                .result(stack(immersive_aircraft.Items.WARSHIP.get()))
                .save(provider);
        MechanicalCraftingRecipeBuilder.builder("immersive_aircraft/biplane")
                .pattern("AABAA", " DCD ", " DED ", " DFD ", "  G  ")
                .key('A', immersive_aircraft.Items.HULL_REINFORCEMENT.get())
                .key('B', immersive_aircraft.Items.PROPELLER.get())
                .key('C', immersive_aircraft.Items.INDUSTRIAL_GEARS.get())
                .key('D', immersive_aircraft.Items.HULL.get())
                .key('E', immersive_aircraft.Items.STEEL_BOILER.get())
                .key('F', immersive_aircraft.Items.ENGINE.get())
                .key('G', immersive_aircraft.Items.ENHANCED_PROPELLER.get())
                .result(stack(immersive_aircraft.Items.BIPLANE.get()))
                .save(provider);
    }

    private static ItemStack stack(ItemLike item) {
        return stack(item, 1);
    }

    private static ItemStack stack(ItemLike item, int count) {
        return new ItemStack(item, count);
    }
}
