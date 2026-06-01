package io.github.cpearl0.ctnhcore.data.recipe.create;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
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

import com.mo_guang.ctpp.common.recipe.builder.create.*;
import com.mo_guang.ctpp.registry.CTPPItems;
import com.mo_guang.ctpp.registry.CTPPMaterials;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import fr.lucreeper74.createmetallurgy.registries.CMBlocks;
import fr.lucreeper74.createmetallurgy.registries.CMItems;

import java.util.*;
import java.util.function.Consumer;

public class MetallurgyRecipes {

    private static final String[] ALL_MATERIALS = {
            "hematite", "magnetite", "precious_alloy", "copper", "diamond", "tin", "silver", "vanadium_magnetite",
            "spodumene", "rock_salt", "salt", "lepidolite", "lazurite", "lapis", "sodalite", "calcite", "graphite",
            "coal", "zinc", "gold", "cassiterite", "chalcopyrite", "pyrite", "iron", "yellow_limonite", "malachite",
            "oilsands", "goethite", "nether_quartz", "quartzite", "opal", "redstone", "ruby", "cinnabar", "nickel",
            "lead", "pentlandite", "realgar", "yellow_garnet", "red_garnet", "basaltic_mineral_sand",
            "granitic_mineral_sand", "beryllium", "molybdenum", "molybdenite", "garnierite", "cobaltite",
            "topaz", "blue_topaz", "sulfur", "chalcocite", "bornite", "sphalerite", "saltpeter", "diatomite",
            "electrotine", "alunite", "grossular", "pyrolusite", "tantalite", "certus_quartz", "barite",
            "spessartine", "gypsum"
    };

    private static final Set<String> MAT_2X = Set.of("nether_quartz", "quartzite", "coal", "cassiterite",
            "salt", "rock_salt", "lepidolite", "blue_topaz", "saltpeter", "certus_quartz", "emerald");
    private static final Set<String> MAT_3X = Set.of("alunite", "grossular", "spessartine");
    private static final Set<String> MAT_5X = Set.of("yellow_garnet", "red_garnet");
    private static final Set<String> MAT_6X = Set.of("redstone", "electrotine");
    private static final Set<String> MAT_7X = Set.of("lazurite", "lapis", "sodalite");
    private static final Set<String> MC_RAW = Set.of("iron", "copper", "gold");
    private static final Set<String> SPECIAL_INGOTS = Set.of("silver", "nickel", "lead", "beryllium", "molybdenum");
    private static final Set<String> PRECIOUS = Set.of("precious_alloy");
    private static final Set<String> MC_NUGGETS = Set.of("iron", "gold");
    private static final Set<String> GT_INGOTS = Set.of("tin", "zinc");

    private static final Set<String> GEM_MATERIALS = Set.of("salt", "rock_salt", "lazurite", "ruby", "cinnabar",
            "opal", "quartzite", "realgar", "topaz", "blue_topaz", "grossular", "spessartine");
    private static final Map<String, ItemStack> SPECIAL_GEMS = Map.of(
            "nether_quartz", new ItemStack(Items.QUARTZ),
            "diamond", new ItemStack(Items.DIAMOND),
            "coal", new ItemStack(Items.COAL),
            "lapis", new ItemStack(Items.LAPIS_LAZULI),
            "emerald", new ItemStack(Items.EMERALD));

    private static final TagKey<Item> HAMMER_TAG = TagKey.create(Registries.ITEM,
            ResourceLocation.parse("forge:tools/hammers"));
    private static final TagKey<Item> SANDPAPER_TAG = TagKey.create(Registries.ITEM,
            ResourceLocation.parse("create:sandpaper"));

    private static final Map<String, Material> MATERIAL_MAP = Map.ofEntries(
            Map.entry("hematite", GTMaterials.Hematite),
            Map.entry("magnetite", GTMaterials.Magnetite),
            Map.entry("precious_alloy", CTNHMaterials.PreciousAlloy),
            Map.entry("copper", GTMaterials.Copper),
            Map.entry("diamond", GTMaterials.Diamond),
            Map.entry("tin", GTMaterials.Tin),
            Map.entry("silver", GTMaterials.Silver),
            Map.entry("vanadium_magnetite", GTMaterials.VanadiumMagnetite),
            Map.entry("spodumene", GTMaterials.Spodumene),
            Map.entry("rock_salt", GTMaterials.RockSalt),
            Map.entry("salt", GTMaterials.Salt),
            Map.entry("lepidolite", GTMaterials.Lepidolite),
            Map.entry("lazurite", GTMaterials.Lazurite),
            Map.entry("lapis", GTMaterials.Lapis),
            Map.entry("sodalite", GTMaterials.Sodalite),
            Map.entry("calcite", GTMaterials.Calcite),
            Map.entry("graphite", GTMaterials.Graphite),
            Map.entry("coal", GTMaterials.Coal),
            Map.entry("zinc", GTMaterials.Zinc),
            Map.entry("gold", GTMaterials.Gold),
            Map.entry("cassiterite", GTMaterials.Cassiterite),
            Map.entry("chalcopyrite", GTMaterials.Chalcopyrite),
            Map.entry("pyrite", GTMaterials.Pyrite),
            Map.entry("iron", GTMaterials.Iron),
            Map.entry("yellow_limonite", GTMaterials.YellowLimonite),
            Map.entry("malachite", GTMaterials.Malachite),
            Map.entry("oilsands", GTMaterials.Oilsands),
            Map.entry("goethite", GTMaterials.Goethite),
            Map.entry("nether_quartz", GTMaterials.NetherQuartz),
            Map.entry("quartzite", GTMaterials.Quartzite),
            Map.entry("opal", GTMaterials.Opal),
            Map.entry("redstone", GTMaterials.Redstone),
            Map.entry("ruby", GTMaterials.Ruby),
            Map.entry("cinnabar", GTMaterials.Cinnabar),
            Map.entry("nickel", GTMaterials.Nickel),
            Map.entry("lead", GTMaterials.Lead),
            Map.entry("pentlandite", GTMaterials.Pentlandite),
            Map.entry("realgar", GTMaterials.Realgar),
            Map.entry("yellow_garnet", GTMaterials.GarnetYellow),
            Map.entry("red_garnet", GTMaterials.GarnetRed),
            Map.entry("basaltic_mineral_sand", GTMaterials.BasalticMineralSand),
            Map.entry("granitic_mineral_sand", GTMaterials.GraniticMineralSand),
            Map.entry("beryllium", GTMaterials.Beryllium),
            Map.entry("molybdenum", GTMaterials.Molybdenum),
            Map.entry("molybdenite", GTMaterials.Molybdenite),
            Map.entry("garnierite", GTMaterials.Garnierite),
            Map.entry("cobaltite", GTMaterials.Cobaltite),
            Map.entry("topaz", GTMaterials.Topaz),
            Map.entry("blue_topaz", GTMaterials.BlueTopaz),
            Map.entry("sulfur", GTMaterials.Sulfur),
            Map.entry("chalcocite", GTMaterials.Chalcocite),
            Map.entry("bornite", GTMaterials.Bornite),
            Map.entry("sphalerite", GTMaterials.Sphalerite),
            Map.entry("saltpeter", GTMaterials.Saltpeter),
            Map.entry("diatomite", GTMaterials.Diatomite),
            Map.entry("electrotine", GTMaterials.Electrotine),
            Map.entry("alunite", GTMaterials.Alunite),
            Map.entry("grossular", GTMaterials.Grossular),
            Map.entry("pyrolusite", GTMaterials.Pyrolusite),
            Map.entry("tantalite", GTMaterials.Tantalite),
            Map.entry("certus_quartz", GTMaterials.CertusQuartz),
            Map.entry("barite", GTMaterials.Barite),
            Map.entry("spessartine", GTMaterials.Spessartine),
            Map.entry("emerald", GTMaterials.Emerald),
            Map.entry("gypsum", GTMaterials.Gypsum));

    public static void init(Consumer<FinishedRecipe> provider) {
        compactingRecipes(provider);
        shapedRecipes(provider);
        oreCrushingRecipes(provider);
        oreSplashingRecipes(provider);
        sequencedAssemblyRecipes(provider);
    }

    private static ItemStack rawOre(String material) {
        if (MC_RAW.contains(material)) {
            return switch (material) {
                case "iron" -> new ItemStack(Items.RAW_IRON);
                case "copper" -> new ItemStack(Items.RAW_COPPER);
                case "gold" -> new ItemStack(Items.RAW_GOLD);
                default -> ChemicalHelper.get(TagPrefix.rawOre, MATERIAL_MAP.get(material));
            };
        }
        return ChemicalHelper.get(TagPrefix.rawOre, MATERIAL_MAP.get(material));
    }

    private static void compactingRecipes(Consumer<FinishedRecipe> provider) {
        ItemStack graphiteDust = ChemicalHelper.get(TagPrefix.dust, GTMaterials.Graphite);
        CompactingRecipeBuilder.builder("graphite_blank_mold")
                .input(new ItemStack(graphiteDust.getItem(), 2))
                .result(CMItems.GRAPHITE_BLANK_MOLD.asStack())
                .save(provider);
    }

    private static void shapedRecipes(Consumer<FinishedRecipe> provider) {
        ItemStack blankMold = CMItems.GRAPHITE_BLANK_MOLD.asStack();
        ItemStack aaPlate = ChemicalHelper.get(TagPrefix.plate, CTPPMaterials.AndesiteAlloy);
        ItemStack obsidianPlate = ChemicalHelper.get(TagPrefix.plate, GTMaterials.Obsidian);
        ItemStack wiPlate = ChemicalHelper.get(TagPrefix.plate, GTMaterials.WroughtIron);

        // graphite_ingot_mold
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/graphite_ingot_mold"),
                CMItems.GRAPHITE_INGOT_MOLD.asStack(),
                "B", "A",
                'A', HAMMER_TAG,
                'B', blankMold);

        // graphite_plate_mold
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/graphite_plate_mold"),
                CMItems.GRAPHITE_PLATE_MOLD.asStack(),
                "A", "B",
                'A', HAMMER_TAG,
                'B', blankMold);

        // casting_basin
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/casting_basin"),
                CMBlocks.CASTING_BASIN_BLOCK.asStack(),
                "A A", "A A", "ABA",
                'A', aaPlate,
                'B', new ItemStack(aaPlate.getItem(), 2));

        // casting_table
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/casting_table"),
                CMBlocks.CASTING_TABLE_BLOCK.asStack(),
                "ABA", "A A", "A A",
                'A', aaPlate,
                'B', new ItemStack(aaPlate.getItem(), 2));

        // sturdy_whisk
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/sturdy_whisk"),
                CMItems.STURDY_WHISK.asStack(),
                " A ", "BCB", "DBD",
                'A', AllBlocks.SHAFT.asStack(),
                'B', obsidianPlate,
                'C', AllItems.WHISK.asStack(),
                'D', wiPlate);

        // foundry_mixer
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/foundry_mixer"),
                CMBlocks.FOUNDRY_MIXER_BLOCK.asStack(),
                "ABA", "CDC", " E ",
                'A', CTPPItems.BASIC_MECHANISM.asStack(),
                'B', AllBlocks.SHAFT.asStack(),
                'C', AllBlocks.COPPER_CASING.asStack(),
                'D', AllBlocks.LARGE_COGWHEEL.asStack(),
                'E', CMItems.STURDY_WHISK.asStack());

        // sandpaper_belt
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/sandpaper_belt"),
                CMItems.SANDPAPER_BELT.asStack(2),
                "AAA", "AAA",
                'A', SANDPAPER_TAG);

        // mechanical_belt_grinder
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/mechanical_belt_grinder"),
                CMBlocks.BELT_GRINDER_BLOCK.asStack(),
                "AAA", "BCB", "DBD",
                'A', CMItems.SANDPAPER_BELT.asStack(),
                'B', AllBlocks.ANDESITE_CASING.asStack(),
                'C', CTPPItems.BASIC_MECHANISM.asStack(),
                'D', AllBlocks.SHAFT.asStack());

        // foundry_basin
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/foundry_basin"),
                CMBlocks.FOUNDRY_BASIN_BLOCK.asStack(),
                "A A", "A A", "ABA",
                'A', aaPlate,
                'B', AllBlocks.ANDESITE_CASING.asStack());

        // foundry_lid
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/foundry_lid"),
                CMBlocks.FOUNDRY_LID_BLOCK.asStack(),
                "ABA", "A A", "A A",
                'A', aaPlate,
                'B', AllBlocks.ANDESITE_CASING.asStack());

        // faucet (3x)
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/faucet"),
                CMBlocks.FAUCET_BLOCK.asStack(3),
                "   ", "A A", " B ",
                'A', aaPlate,
                'B', CTPPItems.BASIC_MECHANISM.asStack());

        // gauge_attachment
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/gauge_attachment"),
                CMItems.GAUGE_ATTACHMENT.asStack(),
                "ABA",
                'A', aaPlate,
                'B', new ItemStack(Items.COMPASS));
    }

    private static void oreCrushingRecipes(Consumer<FinishedRecipe> provider) {
        for (String material : ALL_MATERIALS) {
            ItemStack raw = rawOre(material);
            Material mat = MATERIAL_MAP.get(material);
            ItemStack crushed = ChemicalHelper.get(TagPrefix.crushed, mat);
            ItemStack impureDust = ChemicalHelper.get(TagPrefix.dustImpure, mat);

            if (MAT_2X.contains(material)) {
                CrushingRecipeBuilder.builder("crushing_raw_" + material)
                        .input(raw)
                        .result(new ItemStack(crushed.getItem(), 2))
                        .result(crushed, 0.6)
                        .save(provider);
            } else if (MAT_3X.contains(material)) {
                CrushingRecipeBuilder.builder("crushing_raw_" + material)
                        .input(raw)
                        .result(new ItemStack(crushed.getItem(), 3))
                        .result(crushed, 0.9)
                        .save(provider);
            } else if (MAT_5X.contains(material)) {
                CrushingRecipeBuilder.builder("crushing_raw_" + material)
                        .input(raw)
                        .result(new ItemStack(crushed.getItem(), 5))
                        .result(crushed, 0.2)
                        .save(provider);
            } else if (MAT_6X.contains(material)) {
                CrushingRecipeBuilder.builder("crushing_raw_" + material)
                        .input(raw)
                        .result(new ItemStack(crushed.getItem(), 6))
                        .result(crushed, 0.5)
                        .save(provider);
            } else if (MAT_7X.contains(material)) {
                CrushingRecipeBuilder.builder("crushing_raw_" + material)
                        .input(raw)
                        .result(new ItemStack(crushed.getItem(), 7))
                        .result(crushed, 0.8)
                        .save(provider);
            } else if (MC_RAW.contains(material)) {
                CrushingRecipeBuilder.builder("crushing_raw_" + material)
                        .input(raw)
                        .result(crushed)
                        .result(crushed, 0.3)
                        .save(provider);
            } else {
                CrushingRecipeBuilder.builder("crushing_raw_" + material)
                        .input(raw)
                        .result(crushed)
                        .result(crushed, 0.3)
                        .save(provider);
            }

            // crushed → impure dust
            CrushingRecipeBuilder.builder("crushing_crushed_" + material + "_to_impure")
                    .input(crushed)
                    .result(impureDust)
                    .result(impureDust, 0.3)
                    .save(provider);
        }
    }

    private static void oreSplashingRecipes(Consumer<FinishedRecipe> provider) {
        for (String material : ALL_MATERIALS) {
            Material mat = MATERIAL_MAP.get(material);
            ItemStack crushed = ChemicalHelper.get(TagPrefix.crushed, mat);
            ItemStack purified = ChemicalHelper.get(TagPrefix.crushedPurified, mat);
            ItemStack impureDust = ChemicalHelper.get(TagPrefix.dustImpure, mat);

            // splashing: crushed → purified ore
            SplashingRecipeBuilder.builder("splashing_crushed_" + material)
                    .input(crushed)
                    .result(purified)
                    .save(provider);

            // splashing: impure dust → dust (except redstone → minecraft:redstone)
            if ("redstone".equals(material)) {
                SplashingRecipeBuilder.builder("splashing_impure_" + material)
                        .input(impureDust)
                        .result(new ItemStack(Items.REDSTONE))
                        .save(provider);
            } else {
                ItemStack dust = ChemicalHelper.get(TagPrefix.dust, mat);
                SplashingRecipeBuilder.builder("splashing_impure_" + material)
                        .input(impureDust)
                        .result(dust)
                        .save(provider);
            }
        }

        // Nugget splashing from purified ores
        for (String material : SPECIAL_INGOTS) {
            Material mat = MATERIAL_MAP.get(material);
            ItemStack purified = ChemicalHelper.get(TagPrefix.crushedPurified, mat);
            ItemStack nugget = ChemicalHelper.get(TagPrefix.nugget, mat);
            SplashingRecipeBuilder.builder("splashing_purified_" + material + "_nuggets")
                    .input(purified)
                    .result(new ItemStack(nugget.getItem(), 11))
                    .result(new ItemStack(nugget.getItem(), 2), 0.4)
                    .save(provider);
        }

        // precious_alloy nuggets
        for (String material : PRECIOUS) {
            Material mat = MATERIAL_MAP.get(material);
            ItemStack purified = ChemicalHelper.get(TagPrefix.crushedPurified, mat);
            ItemStack nugget = ChemicalHelper.get(TagPrefix.nugget, mat);
            SplashingRecipeBuilder.builder("splashing_purified_" + material + "_nuggets")
                    .input(purified)
                    .result(new ItemStack(nugget.getItem(), 11))
                    .result(new ItemStack(nugget.getItem(), 2), 0.4)
                    .save(provider);
        }

        // Minecraft ingots nuggets (iron, gold) and copper
        for (String material : MC_RAW) {
            Material mat = MATERIAL_MAP.get(material);
            ItemStack purified = ChemicalHelper.get(TagPrefix.crushedPurified, mat);
            ItemStack nugget;
            if ("copper".equals(material)) {
                nugget = ChemicalHelper.get(TagPrefix.nugget, mat);
            } else {
                nugget = switch (material) {
                    case "iron" -> new ItemStack(Items.IRON_NUGGET);
                    case "gold" -> new ItemStack(Items.GOLD_NUGGET);
                    default -> ChemicalHelper.get(TagPrefix.nugget, mat);
                };
            }
            SplashingRecipeBuilder.builder("splashing_purified_" + material + "_nuggets")
                    .input(purified)
                    .result(new ItemStack(nugget.getItem(), 11))
                    .result(new ItemStack(nugget.getItem(), 2), 0.4)
                    .save(provider);
        }

        // GT ingots nuggets (tin, zinc)
        for (String material : GT_INGOTS) {
            Material mat = MATERIAL_MAP.get(material);
            ItemStack purified = ChemicalHelper.get(TagPrefix.crushedPurified, mat);
            ItemStack nugget = ChemicalHelper.get(TagPrefix.nugget, mat);
            SplashingRecipeBuilder.builder("splashing_purified_" + material + "_nuggets")
                    .input(purified)
                    .result(new ItemStack(nugget.getItem(), 11))
                    .result(new ItemStack(nugget.getItem(), 2), 0.4)
                    .save(provider);
        }

        // Gems splashing
        Set<String> allGemMaterials = new HashSet<>(GEM_MATERIALS);
        allGemMaterials.addAll(SPECIAL_GEMS.keySet());
        for (String material : allGemMaterials) {
            Material mat = MATERIAL_MAP.get(material);
            ItemStack purified = ChemicalHelper.get(TagPrefix.crushedPurified, mat);
            ItemStack gem;
            if (SPECIAL_GEMS.containsKey(material)) {
                gem = SPECIAL_GEMS.get(material);
            } else {
                gem = ChemicalHelper.get(TagPrefix.gem, mat);
            }
            ItemStack flawless = ChemicalHelper.get(TagPrefix.gemFlawless, mat);
            ItemStack flawed = ChemicalHelper.get(TagPrefix.gemFlawed, mat);
            SplashingRecipeBuilder.builder("splashing_purified_" + material + "_gems")
                    .input(purified)
                    .result(flawless, 0.2)
                    .result(gem, 0.4)
                    .result(flawed, 0.4)
                    .save(provider);
        }
    }

    private static void sequencedAssemblyRecipes(Consumer<FinishedRecipe> provider) {
        ItemStack firebricks = GTBlocks.CASING_PRIMITIVE_BRICKS.asStack();
        ItemStack transitional = CMItems.INCOMPLETE_INDUSTRIAL_CRUCIBLE.asStack();
        ItemStack result = CMBlocks.INDUSTRIAL_CRUCIBLE.asStack(2);
        ItemStack wiPlate = ChemicalHelper.get(TagPrefix.plate, GTMaterials.WroughtIron);
        ItemStack wiScrew = ChemicalHelper.get(TagPrefix.screw, GTMaterials.WroughtIron);

        SequencedAssemblyRecipeBuilder.builder("industrial_crucible")
                .input(firebricks)
                .transitional(transitional)
                .result(result)
                .deploying(wiPlate)
                .filling(transitional, GTMaterials.Rubber, 144)
                .pressing()
                .deploying(wiScrew)
                .cutting()
                .loops(2)
                .save(provider);
    }
}
