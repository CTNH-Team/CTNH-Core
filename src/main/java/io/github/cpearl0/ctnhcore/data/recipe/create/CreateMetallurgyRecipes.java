package io.github.cpearl0.ctnhcore.data.recipe.create;

import com.mo_guang.ctpp.registry.CreateMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.data.tag.TagUtil;
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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;

import com.mo_guang.ctpp.common.recipe.builder.create.CompactingRecipeBuilder;
import com.mo_guang.ctpp.common.recipe.builder.create.CrushingRecipeBuilder;
import com.mo_guang.ctpp.common.recipe.builder.create.SequencedAssemblyRecipeBuilder;
import com.mo_guang.ctpp.common.recipe.builder.create.SplashingRecipeBuilder;
import com.mo_guang.ctpp.common.recipe.builder.create.metallurgy.AlloyingRecipeBuilder;
import com.mo_guang.ctpp.common.recipe.builder.create.metallurgy.BulkMeltingRecipeBuilder;
import com.mo_guang.ctpp.common.recipe.builder.create.metallurgy.CastingInBasinRecipeBuilder;
import com.mo_guang.ctpp.common.recipe.builder.create.metallurgy.CastingInTableRecipeBuilder;
import com.mo_guang.ctpp.common.recipe.builder.create.metallurgy.MeltingRecipeBuilder;
import com.mo_guang.ctpp.registry.CTPPItems;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import fr.lucreeper74.createmetallurgy.registries.CMBlocks;
import fr.lucreeper74.createmetallurgy.registries.CMItems;

import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

public class CreateMetallurgyRecipes {

    private static final String[] MATERIALS = {
            "hematite", "magnetite", "precious_alloy", "copper", "diamond", "tin", "silver", "vanadium_magnetite",
            "spodumene", "rock_salt", "salt", "lepidolite", "lazurite", "lapis", "sodalite", "calcite", "graphite",
            "coal", "zinc", "gold", "cassiterite", "chalcopyrite", "pyrite", "iron", "yellow_limonite", "malachite",
            "oilsands", "goethite", "nether_quartz", "quartzite", "opal", "redstone", "ruby", "cinnabar", "nickel",
            "lead", "pentlandite", "realgar", "yellow_garnet", "red_garnet", "basaltic_mineral_sand",
            "granitic_mineral_sand", "beryllium", "molybdenum", "molybdenite", "garnierite", "pentlandite",
            "cobaltite", "topaz", "blue_topaz", "sulfur", "chalcocite", "bornite", "sphalerite", "sulfur",
            "saltpeter", "diatomite", "electrotine", "alunite", "grossular", "pyrolusite", "tantalite", "certus_quartz",
            "barite", "spessartine", "gypsum"
    };

    private static final Set<String> MATERIALS2 = Set.of("nether_quartz", "quartzite", "coal", "cassiterite", "salt",
            "rock_salt", "lepidolite", "blue_topaz", "saltpeter", "certus_quartz", "emerald");
    private static final Set<String> MATERIALS3 = Set.of("alunite", "grossular", "spessartine");
    private static final Set<String> MATERIALS4 = Set.of("yellow_garnet", "red_garnet");
    private static final Set<String> MATERIALS5 = Set.of("redstone", "electrotine");
    private static final Set<String> MATERIALS6 = Set.of("lazurite", "lapis", "sodalite");

    private static final TagKey<Item> HAMMER_TOOL_TAG = itemTag("forge:tools/hammers");
    private static final TagKey<Item> SANDPAPER_TAG = itemTag("create:sandpaper");

    public static void init(Consumer<FinishedRecipe> provider) {
        addCraftingAndBaseRecipes(provider);
        addOreProcessing(provider);
        addMetalMelting(provider);
        addGemSplashing(provider);
        addGroupedOreMelting(provider);
        addAlloys(provider);
        addCasting(provider);
        addIndustrialCrucible(provider);
        addBulkMelting(provider);
        addRubberAndGlass(provider);
    }

    private static void addCraftingAndBaseRecipes(Consumer<FinishedRecipe> provider) {
        CompactingRecipeBuilder.builder("createmetallurgy/graphite_blank_mold")
                .input(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Graphite, 2))
                .output(CMItems.GRAPHITE_BLANK_MOLD.asStack())
                .save(provider);
        VanillaRecipeHelper.addShapedRecipe(provider,
                ResourceLocation.parse("ctnhcore:createmetallurgy/graphite_ingot_mold"),
                CMItems.GRAPHITE_INGOT_MOLD.asStack(),
                "B", "A",
                'A', TagUtil.createItemTag("tools/hammers"),
                'B', CMItems.GRAPHITE_BLANK_MOLD.asStack());
        VanillaRecipeHelper.addShapedRecipe(provider,
                ResourceLocation.parse("ctnhcore:createmetallurgy/graphite_plate_mold"),
                CMItems.GRAPHITE_PLATE_MOLD.asStack(),
                "A", "B",
                'A', TagUtil.createItemTag("tools/hammers"),
                'B', CMItems.GRAPHITE_BLANK_MOLD.asStack());
        VanillaRecipeHelper.addShapedRecipe(provider, ResourceLocation.parse("ctnhcore:createmetallurgy/casting_basin"),
                CMBlocks.CASTING_BASIN_BLOCK.asStack(),
                "A A", "A A", "ABA",
                'A', ChemicalHelper.get(TagPrefix.plate, CreateMaterials.AndesiteAlloy),
                'B', ChemicalHelper.get(TagPrefix.plateDouble, CreateMaterials.AndesiteAlloy));
        VanillaRecipeHelper.addShapedRecipe(provider, ResourceLocation.parse("ctnhcore:createmetallurgy/casting_table"),
                CMBlocks.CASTING_TABLE_BLOCK.asStack(),
                "ABA", "A A", "A A",
                'A', ChemicalHelper.get(TagPrefix.plate, CreateMaterials.AndesiteAlloy),
                'B', ChemicalHelper.get(TagPrefix.plateDouble, CreateMaterials.AndesiteAlloy));
        VanillaRecipeHelper.addShapedRecipe(provider, ResourceLocation.parse("ctnhcore:createmetallurgy/sturdy_whisk"),
                CMItems.STURDY_WHISK.asStack(),
                " A ", "BCB", "DBD",
                'A', AllBlocks.SHAFT.asItem(),
                'B', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Obsidian),
                'C', AllItems.WHISK.asItem(),
                'D', ChemicalHelper.get(TagPrefix.plate, GTMaterials.WroughtIron));
        VanillaRecipeHelper.addShapedRecipe(provider, ResourceLocation.parse("ctnhcore:createmetallurgy/foundry_mixer"),
                CMBlocks.FOUNDRY_MIXER_BLOCK.asStack(),
                "ABA", "CDC", " E ",
                'A', CTPPItems.BASIC_MECHANISM.asStack(),
                'B', AllBlocks.SHAFT.asItem(),
                'C', AllBlocks.COPPER_CASING.asItem(),
                'D', AllBlocks.LARGE_COGWHEEL.asItem(),
                'E', CMItems.STURDY_WHISK.asStack());
        VanillaRecipeHelper.addShapedRecipe(provider,
                ResourceLocation.parse("ctnhcore:createmetallurgy/sandpaper_belt"),
                CMItems.SANDPAPER_BELT.asStack(2),
                "AAA", "AAA",
                'A', SANDPAPER_TAG);
        VanillaRecipeHelper.addShapedRecipe(provider,
                ResourceLocation.parse("ctnhcore:createmetallurgy/mechanical_belt_grinder"),
                CMBlocks.BELT_GRINDER_BLOCK.asStack(),
                "AAA", "BCB", "DBD",
                'A', CMItems.SANDPAPER_BELT.asStack(),
                'B', AllBlocks.ANDESITE_CASING.asItem(),
                'C', CTPPItems.BASIC_MECHANISM.asStack(),
                'D', AllBlocks.SHAFT.asItem());
        VanillaRecipeHelper.addShapedRecipe(provider, ResourceLocation.parse("ctnhcore:createmetallurgy/foundry_basin"),
                CMBlocks.FOUNDRY_BASIN_BLOCK.asStack(),
                "A A", "A A", "ABA",
                'A', ChemicalHelper.get(TagPrefix.plate, CreateMaterials.AndesiteAlloy),
                'B', AllBlocks.ANDESITE_CASING.asItem());
        VanillaRecipeHelper.addShapedRecipe(provider, ResourceLocation.parse("ctnhcore:createmetallurgy/foundry_lid"),
                CMBlocks.FOUNDRY_LID_BLOCK.asStack(),
                "ABA", "A A", "A A",
                'A', ChemicalHelper.get(TagPrefix.plate, CreateMaterials.AndesiteAlloy),
                'B', AllBlocks.ANDESITE_CASING.asItem());
        VanillaRecipeHelper.addShapedRecipe(provider, ResourceLocation.parse("ctnhcore:createmetallurgy/faucet"),
                CMBlocks.FAUCET_BLOCK.asStack(3),
                "   ", "A A", " B ",
                'A', ChemicalHelper.get(TagPrefix.plate, CreateMaterials.AndesiteAlloy),
                'B', CTPPItems.BASIC_MECHANISM.asStack());
        VanillaRecipeHelper.addShapedRecipe(provider,
                ResourceLocation.parse("ctnhcore:createmetallurgy/gauge_attachment"),
                CMItems.GAUGE_ATTACHMENT.asStack(),
                "ABA",
                'A', ChemicalHelper.get(TagPrefix.plate, CreateMaterials.AndesiteAlloy),
                'B', Items.COMPASS);
    }

    private static void addOreProcessing(Consumer<FinishedRecipe> provider) {
        int index = 0;
        for (String material : MATERIALS) {
            int count = 1;
            double chance = 0.3;
            if (MATERIALS2.contains(material)) {
                count = 2;
                chance = 0.6;
            } else if (MATERIALS3.contains(material)) {
                count = 3;
                chance = 0.9;
            } else if (MATERIALS4.contains(material)) {
                count = 5;
                chance = 0.2;
            } else if (MATERIALS5.contains(material)) {
                count = 6;
                chance = 0.5;
            } else if (MATERIALS6.contains(material)) {
                count = 7;
                chance = 0.8;
            }
            String raw = (material.equals("iron") || material.equals("copper") || material.equals("gold")) ?
                    "minecraft:raw_" + material : "gtceu:raw_" + material;
            CrushingRecipeBuilder.builder("createmetallurgy/raw_" + material + "_crushing_" + index)
                    .input(ingredient(raw))
                    .output(stack("gtceu:crushed_" + material + "_ore", count))
                    .result(stack("gtceu:crushed_" + material + "_ore", 1), chance)
                    .save(provider);
            CrushingRecipeBuilder.builder("createmetallurgy/crushed_" + material + "_ore_crushing_" + index)
                    .input(ingredient("gtceu:crushed_" + material + "_ore"))
                    .output(stack("gtceu:impure_" + material + "_dust", 1))
                    .result(stack("gtceu:impure_" + material + "_dust", 1), 0.3)
                    .save(provider);
            SplashingRecipeBuilder.builder("createmetallurgy/purified_" + material + "_ore_" + index)
                    .input(ingredient("gtceu:crushed_" + material + "_ore"))
                    .result(stack("gtceu:purified_" + material + "_ore", 1))
                    .save(provider);
            SplashingRecipeBuilder.builder("createmetallurgy/clean_impure_" + material + "_dust_" + index)
                    .input(ingredient("gtceu:impure_" + material + "_dust"))
                    .result(stack(material.equals("redstone") ? "minecraft:redstone" : "gtceu:" + material + "_dust",
                            1))
                    .save(provider);
            index++;
        }
    }

    private static void addMetalMelting(Consumer<FinishedRecipe> provider) {
        for (String material : new String[] { "silver", "nickel", "lead", "beryllium", "molybdenum" }) {
            SplashingRecipeBuilder.builder("createmetallurgy/" + material + "_nuggets_from_purified_ore")
                    .input(ingredient("gtceu:purified_" + material + "_ore"))
                    .result(stack("gtceu:" + material + "_nugget", 11))
                    .result(stack("gtceu:" + material + "_nugget", 2), 0.4)
                    .save(provider);
            addStandardMetalMelting(provider, material, material, true, false);
        }
        SplashingRecipeBuilder.builder("createmetallurgy/precious_alloy_nuggets_from_purified_ore")
                .input(ingredient("gtceu:purified_precious_alloy_ore"))
                .result(stack("gtceu:precious_alloy_nugget", 11))
                .result(stack("gtceu:precious_alloy_nugget", 2), 0.4)
                .save(provider);
        addPreciousAlloyMelting(provider);
        for (String material : new String[] { "iron", "copper", "gold" }) {
            String nugget = material.equals("copper") ? "gtceu:copper_nugget" : "minecraft:" + material + "_nugget";
            SplashingRecipeBuilder.builder("createmetallurgy/" + material + "_nuggets_from_purified_ore")
                    .input(ingredient("gtceu:purified_" + material + "_ore"))
                    .result(stack(nugget, 11))
                    .result(stack(nugget, 2), 0.4)
                    .save(provider);
            addStandardMetalMelting(provider, material, material, false, true);
        }
        for (String material : new String[] { "tin", "zinc" }) {
            SplashingRecipeBuilder.builder("createmetallurgy/" + material + "_nuggets_from_purified_ore")
                    .input(ingredient("gtceu:purified_" + material + "_ore"))
                    .result(stack("gtceu:" + material + "_nugget", 11))
                    .result(stack("gtceu:" + material + "_nugget", 2), 0.4)
                    .save(provider);
            addStandardMetalMelting(provider, material, material, true, false);
        }
    }

    private static void addGemSplashing(Consumer<FinishedRecipe> provider) {
        for (String material : new String[] { "salt", "rock_salt", "lazurite", "ruby", "cinnabar", "opal", "quartzite",
                "realgar", "topaz", "blue_topaz", "grossular", "spessartine" }) {
            rawGemSplashing(provider, material, "gtceu:" + material + "_gem");
        }
        rawGemSplashing(provider, "nether_quartz", "minecraft:quartz");
        rawGemSplashing(provider, "diamond", "minecraft:diamond");
        rawGemSplashing(provider, "coal", "minecraft:coal");
        rawGemSplashing(provider, "lapis", "minecraft:lapis_lazuli");
        rawGemSplashing(provider, "emerald", "minecraft:emerald");
        rawGemSplashing(provider, "certus_quartz", "ae2:certus_quartz_crystal");
    }

    private static void addGroupedOreMelting(Consumer<FinishedRecipe> provider) {
        addGrouped(provider, "iron", new String[] { "hematite", "magnetite", "yellow_limonite", "pyrite",
                "goethite", "basaltic_mineral_sand", "granitic_mineral_sand" });
        addGrouped(provider, "copper", new String[] { "chalcopyrite", "malachite", "chalcocite", "bornite" });
        addGrouped(provider, "zinc", new String[] { "sphalerite" });
        addGrouped(provider, "molybdenum", new String[] { "molybdenite" });
        addGrouped(provider, "nickel", new String[] { "garnierite", "pentlandite" });
        addGrouped(provider, "cobalt", new String[] { "cobaltite" });
        addGrouped(provider, "manganese", new String[] { "pyrolusite" });
    }

    private static void addAlloys(Consumer<FinishedRecipe> provider) {
        AlloyingRecipeBuilder.builder("ctnhcore:createmetallurgy/alloying/brass")
                .inputFluid("gtceu:copper", 432)
                .inputFluid("gtceu:zinc", 144)
                .heatRequirement(HeatCondition.HEATED)
                .duration(200)
                .resultFluid("gtceu:brass", 576)
                .save(provider);
        AlloyingRecipeBuilder.builder("ctnhcore:createmetallurgy/alloying/bronze")
                .inputFluid("gtceu:copper", 432)
                .inputFluid("gtceu:tin", 144)
                .heatRequirement(HeatCondition.HEATED)
                .duration(200)
                .resultFluid("gtceu:bronze", 576)
                .save(provider);
        AlloyingRecipeBuilder.builder("ctnhcore:createmetallurgy/alloying/potin")
                .inputFluid("gtceu:bronze", 576)
                .inputFluid("gtceu:lead", 72)
                .heatRequirement(HeatCondition.HEATED)
                .duration(400)
                .resultFluid("gtceu:potin", 648)
                .save(provider);
        AlloyingRecipeBuilder.builder("ctnhcore:createmetallurgy/alloying/tin_alloy")
                .inputFluid("gtceu:iron", 144)
                .inputFluid("gtceu:tin", 144)
                .heatRequirement(HeatCondition.HEATED)
                .duration(100)
                .resultFluid("gtceu:tin_alloy", 288)
                .save(provider);
        MeltingRecipeBuilder.builder("ctnhcore:createmetallurgy/melting/andesite_alloy_dust")
                .input("gtceu:andesite_alloy_dust")
                .heatRequirement(HeatCondition.HEATED)
                .duration(90)
                .resultFluid("gtceu:andesite_alloy", 144)
                .save(provider);
        MeltingRecipeBuilder.builder("ctnhcore:createmetallurgy/melting/andesite_alloy_ingot")
                .input("gtceu:andesite_alloy_ingot")
                .heatRequirement(HeatCondition.SUPERHEATED)
                .duration(90)
                .resultFluid("gtceu:andesite_alloy", 144)
                .save(provider);
    }

    private static void addCasting(Consumer<FinishedRecipe> provider) {
        for (String metal : new String[] { "andesite_alloy", "brass", "steel", "silver", "nickel", "lead", "tin",
                "zinc", "bronze" }) {
            CastingInTableRecipeBuilder.builder("ctnhcore:createmetallurgy/casting_in_table/" + metal + "_ingot")
                    .input("createmetallurgy:graphite_ingot_mold")
                    .inputFluid("gtceu:" + metal, 144)
                    .duration(20)
                    .result("gtceu:" + metal + "_ingot")
                    .save(provider);
            CastingInTableRecipeBuilder.builder("ctnhcore:createmetallurgy/casting_in_table/" + metal + "_plate")
                    .input("createmetallurgy:graphite_plate_mold")
                    .inputFluid("gtceu:" + metal, 216)
                    .duration(20)
                    .result("gtceu:" + metal + "_plate")
                    .save(provider);
            CastingInBasinRecipeBuilder.builder("ctnhcore:createmetallurgy/casting_in_basin/" + metal + "_block")
                    .inputFluid("gtceu:" + metal, 1296)
                    .duration(90)
                    .result("gtceu:" + metal + "_block")
                    .save(provider);
        }
        CastingInTableRecipeBuilder.builder("ctnhcore:createmetallurgy/casting_in_table/precious_alloy_ingot")
                .input("createmetallurgy:graphite_ingot_mold")
                .inputFluid("gtceu:precious_alloy", 144)
                .duration(20)
                .result("gtceu:precious_alloy_ingot")
                .save(provider);
        CastingInBasinRecipeBuilder.builder("ctnhcore:createmetallurgy/casting_in_basin/precious_alloy_block")
                .inputFluid("gtceu:precious_alloy", 1296)
                .duration(90)
                .result("gtceu:precious_alloy_block")
                .save(provider);
        for (String metal : new String[] { "iron", "copper", "gold" }) {
            CastingInTableRecipeBuilder.builder("ctnhcore:createmetallurgy/casting_in_table/" + metal + "_ingot")
                    .input("createmetallurgy:graphite_ingot_mold")
                    .inputFluid("gtceu:" + metal, 144)
                    .duration(20)
                    .result("minecraft:" + metal + "_ingot")
                    .save(provider);
            CastingInTableRecipeBuilder.builder("ctnhcore:createmetallurgy/casting_in_table/" + metal + "_plate")
                    .input("createmetallurgy:graphite_plate_mold")
                    .inputFluid("gtceu:" + metal, 216)
                    .duration(20)
                    .result("gtceu:" + metal + "_plate")
                    .save(provider);
            CastingInBasinRecipeBuilder.builder("ctnhcore:createmetallurgy/casting_in_basin/" + metal + "_block")
                    .inputFluid("gtceu:" + metal, 1296)
                    .duration(90)
                    .result("minecraft:" + metal + "_block")
                    .save(provider);
        }
        CastingInTableRecipeBuilder.builder("ctnhcore:createmetallurgy/casting_in_table/rubber_ingot")
                .input("createmetallurgy:graphite_ingot_mold")
                .inputFluid("gtceu:rubber", 144)
                .duration(10)
                .result("gtceu:rubber_ingot")
                .save(provider);
        CastingInBasinRecipeBuilder.builder("ctnhcore:createmetallurgy/casting_in_basin/rubber_block")
                .inputFluid("gtceu:rubber", 1296)
                .duration(2)
                .result("gtceu:rubber_block")
                .save(provider);
        CastingInBasinRecipeBuilder.builder("ctnhcore:createmetallurgy/casting_in_basin/glass")
                .inputFluid("gtceu:glass", 144)
                .duration(2)
                .result("minecraft:glass")
                .save(provider);
    }

    private static void addIndustrialCrucible(Consumer<FinishedRecipe> provider) {
        ItemStack transitional = CMItems.INCOMPLETE_INDUSTRIAL_CRUCIBLE.asStack();
        SequencedAssemblyRecipeBuilder.builder(ResourceLocation.parse("ctnhcore:createmetallurgy/industrial_crucible"))
                .input(GTBlocks.CASING_PRIMITIVE_BRICKS.asStack())
                .transitional(transitional)
                .result(CMBlocks.INDUSTRIAL_CRUCIBLE.asStack(2))
                .deploying(ChemicalHelper.get(TagPrefix.plate, GTMaterials.WroughtIron))
                .filling(transitional, GTMaterials.Rubber, 144)
                .pressing()
                .deploying(ChemicalHelper.get(TagPrefix.screw, GTMaterials.WroughtIron))
                .cutting()
                .loops(2)
                .save(provider);
    }

    private static void addBulkMelting(Consumer<FinishedRecipe> provider) {
        BulkMeltingRecipeBuilder.builder(ResourceLocation.parse("ctnhcore:createmetallurgy/refined_iron_to_steel"))
                .input(CTNHItems.REFINED_IRON_INGOT.asStack())
                .minHeat(25)
                .maxHeat(50)
                .duration(3600)
                .resultFluid("gtceu:steel", 1152)
                .save(provider);
    }

    private static void addRubberAndGlass(Consumer<FinishedRecipe> provider) {
        MeltingRecipeBuilder.builder("ctnhcore:createmetallurgy/melting/rubber_ingot")
                .input("gtceu:rubber_ingot")
                .heatRequirement(HeatCondition.HEATED)
                .duration(40)
                .resultFluid("gtceu:rubber", 144)
                .save(provider);
        MeltingRecipeBuilder.builder("ctnhcore:createmetallurgy/melting/rubber_powder")
                .input("ctnhcore:rubber_powder")
                .heatRequirement(HeatCondition.HEATED)
                .duration(40)
                .resultFluid("gtceu:rubber", 144)
                .save(provider);
        MeltingRecipeBuilder.builder("ctnhcore:createmetallurgy/melting/glass_dust")
                .input("gtceu:glass_dust")
                .heatRequirement(HeatCondition.HEATED)
                .duration(40)
                .resultFluid("gtceu:glass", 144)
                .save(provider);
    }

    private static void addStandardMetalMelting(Consumer<FinishedRecipe> provider, String material, String fluid,
                                                boolean gtIngot, boolean vanillaIngot) {
        MeltingRecipeBuilder.builder("ctnhcore:createmetallurgy/melting/" + material + "_crushed")
                .input("gtceu:crushed_" + material + "_ore")
                .duration(40)
                .heatRequirement(HeatCondition.HEATED)
                .resultFluid("gtceu:" + fluid, 108)
                .resultFluid("gtceu:slag", 100)
                .save(provider);
        MeltingRecipeBuilder.builder("ctnhcore:createmetallurgy/melting/" + material + "_purified")
                .input("gtceu:purified_" + material + "_ore")
                .duration(40)
                .heatRequirement(HeatCondition.HEATED)
                .resultFluid("gtceu:" + fluid, 144)
                .save(provider);
        MeltingRecipeBuilder.builder("ctnhcore:createmetallurgy/melting/" + material + "_impure")
                .input("gtceu:impure_" + material + "_dust")
                .duration(40)
                .heatRequirement(HeatCondition.HEATED)
                .resultFluid("gtceu:" + fluid, 144)
                .resultFluid("gtceu:slag", 50)
                .save(provider);
        MeltingRecipeBuilder.builder("ctnhcore:createmetallurgy/melting/" + material + "_dust")
                .input("gtceu:" + material + "_dust")
                .duration(40)
                .heatRequirement(HeatCondition.HEATED)
                .resultFluid("gtceu:" + fluid, 144)
                .save(provider);
        MeltingRecipeBuilder.builder("ctnhcore:createmetallurgy/melting/" + material + "_ingot")
                .input((vanillaIngot ? "minecraft:" : "gtceu:") + material + "_ingot")
                .duration(80)
                .heatRequirement(HeatCondition.HEATED)
                .resultFluid("gtceu:" + fluid, 144)
                .save(provider);
    }

    private static void addPreciousAlloyMelting(Consumer<FinishedRecipe> provider) {
        MeltingRecipeBuilder.builder("ctnhcore:createmetallurgy/melting/precious_alloy_crushed")
                .input("gtceu:crushed_precious_alloy_ore")
                .duration(40)
                .heatRequirement(HeatCondition.SUPERHEATED)
                .resultFluid("gtceu:precious_alloy", 108)
                .resultFluid("gtceu:slag", 100)
                .save(provider);
        MeltingRecipeBuilder.builder("ctnhcore:createmetallurgy/melting/precious_alloy_purified")
                .input("gtceu:purified_precious_alloy_ore")
                .duration(40)
                .heatRequirement(HeatCondition.SUPERHEATED)
                .resultFluid("gtceu:precious_alloy", 144)
                .save(provider);
        MeltingRecipeBuilder.builder("ctnhcore:createmetallurgy/melting/precious_alloy_impure")
                .input("gtceu:impure_precious_alloy_dust")
                .duration(40)
                .heatRequirement(HeatCondition.SUPERHEATED)
                .resultFluid("gtceu:precious_alloy", 144)
                .resultFluid("gtceu:slag", 50)
                .save(provider);
        MeltingRecipeBuilder.builder("ctnhcore:createmetallurgy/melting/precious_alloy_dust")
                .input("gtceu:precious_alloy_dust")
                .duration(40)
                .heatRequirement(HeatCondition.SUPERHEATED)
                .resultFluid("gtceu:slag", 50)
                .resultFluid("gtceu:gold", 48)
                .save(provider);
        MeltingRecipeBuilder.builder("ctnhcore:createmetallurgy/melting/precious_alloy_ingot")
                .input("gtceu:precious_alloy_ingot")
                .duration(80)
                .heatRequirement(HeatCondition.SUPERHEATED)
                .resultFluid("gtceu:slag", 50)
                .resultFluid("gtceu:gold", 48)
                .save(provider);
    }

    private static void rawGemSplashing(Consumer<FinishedRecipe> provider, String material, String middle) {
        SplashingRecipeBuilder.builder("createmetallurgy/gem_splashing_" + material)
                .input(ingredient("gtceu:purified_" + material + "_ore"))
                .result(stack("gtceu:flawless_" + material + "_gem", 1), 0.2)
                .result(stack(middle, 1), 0.4)
                .result(stack("gtceu:flawed_" + material + "_gem", 1), 0.4)
                .save(provider);
    }

    private static void addGrouped(Consumer<FinishedRecipe> provider, String fluid, String[] ores) {
        for (String material : ores) {
            MeltingRecipeBuilder.builder("ctnhcore:createmetallurgy/melting/" + material + "_to_" + fluid + "_crushed")
                    .input("gtceu:crushed_" + material + "_ore")
                    .duration(40)
                    .heatRequirement(HeatCondition.HEATED)
                    .resultFluid("gtceu:" + fluid, 108)
                    .resultFluid("gtceu:slag", 125)
                    .save(provider);
            MeltingRecipeBuilder.builder("ctnhcore:createmetallurgy/melting/" + material + "_to_" + fluid + "_purified")
                    .input("gtceu:purified_" + material + "_ore")
                    .duration(40)
                    .heatRequirement(HeatCondition.HEATED)
                    .resultFluid("gtceu:" + fluid, 144)
                    .save(provider);
            MeltingRecipeBuilder.builder("ctnhcore:createmetallurgy/melting/" + material + "_to_" + fluid + "_impure")
                    .input("gtceu:impure_" + material + "_dust")
                    .duration(40)
                    .heatRequirement(HeatCondition.HEATED)
                    .resultFluid("gtceu:" + fluid, 144)
                    .resultFluid("gtceu:slag", 75)
                    .save(provider);
            MeltingRecipeBuilder.builder("ctnhcore:createmetallurgy/melting/" + material + "_dust_to_" + fluid)
                    .input("gtceu:" + material + "_dust")
                    .duration(40)
                    .heatRequirement(HeatCondition.HEATED)
                    .resultFluid("gtceu:" + fluid, 144)
                    .save(provider);
        }
    }

    private static Ingredient ingredient(String id) {
        return Ingredient.of(requireItem(id));
    }

    private static ItemStack stack(Item item, int count) {
        return new ItemStack(item, count);
    }

    private static ItemStack stack(String id, int count) {
        return stack(requireItem(id), count);
    }

    private static TagKey<Item> itemTag(String id) {
        return TagKey.create(Registries.ITEM, ResourceLocation.parse(id));
    }

    private static Item requireItem(String id) {
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(id)), id);
    }
}
