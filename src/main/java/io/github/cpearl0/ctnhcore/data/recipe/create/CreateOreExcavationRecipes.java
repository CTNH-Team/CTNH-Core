package io.github.cpearl0.ctnhcore.data.recipe.create;

import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Objects;
import java.util.function.Consumer;

public class CreateOreExcavationRecipes {

    private record Vein(String key, String output, int y, int spread, int seed, int minSize, int maxSize,
                        String[] biomes, Integer priority) {}

    private record Drop(String item, double chance) {}

    private record Drill(String vein, int duration, int stress, String drill, Drop... drops) {}

    private static final Vein[] VEINS = {
            new Vein("iron", "gtceu:raw_goethite", 64, 8, 1666484, 20, 100, new String[] { "minecraft:is_overworld" },
                    null),
            new Vein("copper", "minecraft:raw_copper", 80, 8, 465123, 20, 100,
                    new String[] { "minecraft:is_overworld" }, null),
            new Vein("zinc", "gtceu:raw_zinc", 80, 8, 235491, 20, 55, new String[] { "minecraft:is_overworld" }, null),
            new Vein("redstone", "gtceu:raw_redstone", 128, 8, 178819, 30, 85,
                    new String[] { "minecraft:is_overworld" }, null),
            new Vein("coal", "gtceu:raw_coal", 80, 8, 333566, 40, 95, new String[] { "minecraft:is_overworld" }, null),
            new Vein("lapis", "gtceu:raw_lapis", 128, 8, 266484, 10, 40, new String[] { "minecraft:is_overworld" }, 1),
            new Vein("oilsands", "gtceu:raw_oilsands", 128, 8, 168915, 30, 60,
                    new String[] { "minecraft:is_overworld" }, 1),
            new Vein("precious_alloy", "ctnhcore:raw_precious_alloy", 80, 8, 168784, 25, 85,
                    new String[] { "minecraft:is_overworld" }, null),
            new Vein("nether_quartz", "gtceu:raw_nether_quartz", 64, 8, 116564, 25, 65,
                    new String[] { "minecraft:is_overworld" }, null),
            new Vein("tin", "gtceu:raw_tin", 80, 8, 177784, 20, 100, new String[] { "minecraft:is_overworld" }, null),
            new Vein("diamond", "gtceu:raw_diamond", 128, 8, 1715514, 25, 85, new String[] { "minecraft:is_overworld" },
                    null),
            new Vein("salt", "gtceu:raw_salt", 64, 8, 168784, 25, 65, new String[] { "minecraft:is_overworld" }, null),
            new Vein("magnetite", "gtceu:raw_magnetite", 64, 8, 264664, 30, 100,
                    new String[] { "minecraft:is_overworld" }, null),
            new Vein("beryllium", "gtceu:raw_beryllium", 144, 8, 6889134, 20, 80,
                    new String[] { "minecraft:is_nether" }, null),
            new Vein("molybdenum", "gtceu:raw_molybdenum", 256, 8, 7739134, 30, 60,
                    new String[] { "minecraft:is_nether" }, null),
            new Vein("topaz", "gtceu:raw_topaz", 64, 8, 667734, 30, 65, new String[] { "minecraft:is_nether" }, null),
            new Vein("sulfur", "gtceu:raw_sulfur", 60, 8, 7984516, 40, 100, new String[] { "minecraft:is_nether" },
                    null),
            new Vein("nickel", "gtceu:raw_nickel", 128, 8, 2649365, 20, 60, new String[] { "minecraft:is_nether" },
                    null),
            new Vein("saltpeter", "gtceu:raw_saltpeter", 128, 8, 689495, 20, 70, new String[] { "minecraft:is_nether" },
                    null),
            new Vein("pyrolusite", "gtceu:raw_pyrolusite", 192, 8, 7789695, 20, 70,
                    new String[] { "minecraft:is_nether" }, null),
            new Vein("certus", "gtceu:raw_certus_quartz", 128, 8, 7964355, 30, 80,
                    new String[] { "minecraft:is_nether" }, null),
            new Vein("ancient_debris", "minecraft:ancient_debris", 256, 8, 9984655, 20, 45,
                    new String[] { "minecraft:is_nether" }, null),
            new Vein("seawater", "ctnhcore:seawater_bucket", 64, 8, 12345678, 3000, 8000,
                    new String[] { "minecraft:is_ocean", "minecraft:is_overworld" }, null)
    };

    private static final Drill[] DRILLS = {
            new Drill("iron", 300, 192, null, new Drop("gtceu:raw_goethite", 0.5),
                    new Drop("gtceu:raw_yellow_limonite", 0.25), new Drop("gtceu:raw_hematite", 0.15),
                    new Drop("gtceu:raw_malachite", 0.1)),
            new Drill("copper", 300, 192, null, new Drop("gtceu:raw_chalcopyrite", 0.4),
                    new Drop("minecraft:raw_iron", 0.15), new Drop("gtceu:raw_pyrite", 0.25),
                    new Drop("minecraft:raw_copper", 0.2)),
            new Drill("zinc", 300, 256, "createoreexcavation:diamond_drill", new Drop("gtceu:raw_zinc", 0.4),
                    new Drop("minecraft:raw_copper", 0.4), new Drop("gtceu:raw_yellow_limonite", 0.1),
                    new Drop("gtceu:raw_malachite", 0.1)),
            new Drill("redstone", 400, 192, null, new Drop("gtceu:raw_redstone", 0.4), new Drop("gtceu:raw_ruby", 0.3),
                    new Drop("gtceu:raw_cinnabar", 0.3)),
            new Drill("coal", 100, 192, null, new Drop("gtceu:raw_coal", 1)),
            new Drill("lapis", 400, 256, null, new Drop("gtceu:raw_lazurite", 0.4), new Drop("gtceu:raw_sodalite", 0.4),
                    new Drop("gtceu:raw_lapis", 0.1), new Drop("gtceu:raw_calcite", 0.1)),
            new Drill("oilsands", 300, 192, null, new Drop("gtceu:raw_oilsands", 1)),
            new Drill("precious_alloy", 400, 512, "createoreexcavation:diamond_drill",
                    new Drop("ctnhcore:raw_precious_alloy", 0.4), new Drop("gtceu:raw_silver", 0.2),
                    new Drop("gtceu:raw_tin", 0.2), new Drop("minecraft:raw_copper", 0.2)),
            new Drill("nether_quartz", 400, 512, "createoreexcavation:diamond_drill",
                    new Drop("gtceu:raw_nether_quartz", 0.5), new Drop("gtceu:raw_quartzite", 0.4),
                    new Drop("gtceu:raw_opal", 0.1)),
            new Drill("tin", 300, 192, null, new Drop("gtceu:raw_tin", 0.7), new Drop("gtceu:raw_cassiterite", 0.3)),
            new Drill("diamond", 400, 512, "createoreexcavation:diamond_drill", new Drop("gtceu:raw_diamond", 0.4),
                    new Drop("gtceu:raw_graphite", 0.4), new Drop("gtceu:raw_coal", 0.2)),
            new Drill("magnetite", 300, 256, null, new Drop("gtceu:raw_magnetite", 0.6),
                    new Drop("gtceu:raw_vanadium_magnetite", 0.2), new Drop("ctnhcore:raw_precious_alloy", 0.2)),
            new Drill("salt", 300, 192, null, new Drop("gtceu:raw_rock_salt", 0.3), new Drop("gtceu:raw_salt", 0.3),
                    new Drop("gtceu:raw_lepidolite", 0.2), new Drop("gtceu:raw_spodumene", 0.2)),
            new Drill("beryllium", 200, 288, "createoreexcavation:diamond_drill", new Drop("gtceu:raw_beryllium", 0.6),
                    new Drop("gtceu:raw_emerald", 0.4)),
            new Drill("molybdenum", 300, 512, "createoreexcavation:diamond_drill",
                    new Drop("gtceu:raw_molybdenum", 0.2), new Drop("gtceu:raw_wulfenite", 0.5),
                    new Drop("gtceu:raw_molybdenite", 0.2), new Drop("gtceu:raw_powellite", 0.1)),
            new Drill("nickel", 250, 512, "createoreexcavation:diamond_drill", new Drop("gtceu:raw_nickel", 0.3),
                    new Drop("gtceu:raw_garnierite", 0.4), new Drop("gtceu:raw_cobaltite", 0.2),
                    new Drop("gtceu:raw_pentlandite", 0.1)),
            new Drill("topaz", 400, 512, "createoreexcavation:netherite_drill", new Drop("gtceu:raw_topaz", 0.3),
                    new Drop("gtceu:raw_blue_topaz", 0.3), new Drop("gtceu:raw_chalcocite", 0.2),
                    new Drop("gtceu:raw_bornite", 0.2)),
            new Drill("sulfur", 200, 288, "createoreexcavation:diamond_drill", new Drop("gtceu:raw_sulfur", 0.6),
                    new Drop("gtceu:raw_pyrite", 0.15), new Drop("gtceu:raw_sphalerite", 0.25)),
            new Drill("saltpeter", 300, 512, "createoreexcavation:diamond_drill", new Drop("gtceu:raw_saltpeter", 0.3),
                    new Drop("gtceu:raw_diatomite", 0.3), new Drop("gtceu:raw_electrotine", 0.2),
                    new Drop("gtceu:raw_alunite", 0.2)),
            new Drill("pyrolusite", 300, 768, "createoreexcavation:netherite_drill",
                    new Drop("gtceu:raw_pyrolusite", 0.3), new Drop("gtceu:raw_grossular", 0.4),
                    new Drop("gtceu:raw_tantalite", 0.3)),
            new Drill("certus", 300, 512, "createoreexcavation:diamond_drill",
                    new Drop("gtceu:raw_certus_quartz", 0.45), new Drop("gtceu:raw_quartzite", 0.25),
                    new Drop("gtceu:raw_barite", 0.3)),
            new Drill("ancient_debris", 400, 1024, "createoreexcavation:netherite_drill",
                    new Drop("minecraft:ancient_debris", 0.1), new Drop("gtceu:raw_sulfur", 0.35),
                    new Drop("ctnhcore:raw_precious_alloy", 0.3), new Drop("gtceu:raw_nether_quartz", 0.25))
    };

    public static void init(Consumer<FinishedRecipe> provider) {
        addVeinFinder(provider);
        for (Vein vein : VEINS) addVein(provider, vein);
        for (Drill drill : DRILLS) addDrill(provider, drill);
        addSeawaterExtraction(provider);
    }

    private static void addVeinFinder(Consumer<FinishedRecipe> provider) {
        VanillaRecipeHelper.addShapedRecipe(provider,
                ResourceLocation.parse("ctnhcore:createoreexcavation/vein_finder"),
                itemStack("createoreexcavation:vein_finder"),
                " AB", " CA", "C  ",
                'A', item("minecraft:amethyst_shard"),
                'B', item("gtceu:wrought_iron_gear"),
                'C', item("minecraft:stick"));
    }

    private static ItemStack itemStack(String id) {
        return new ItemStack(item(id));
    }

    private static Item item(String id) {
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(id)), id);
    }

    private static void addVein(Consumer<FinishedRecipe> provider, Vein vein) {
        JsonObject json = CreateRecipeJsonHelper.recipe("createoreexcavation:vein");
        json.addProperty("name", "translate:ctnh." + vein.key() + "_vein");
        json.add("output", CreateRecipeJsonHelper.item(vein.output()));
        JsonObject placement = new JsonObject();
        placement.addProperty("salt", vein.seed());
        placement.addProperty("separation", vein.spread());
        placement.addProperty("spacing", vein.y());
        json.add("placement", placement);
        json.addProperty("finite", true);
        JsonObject size = new JsonObject();
        size.addProperty("min", vein.minSize());
        size.addProperty("max", vein.maxSize());
        json.add("veinSize", size);
        JsonArray biomes = new JsonArray();
        for (String biome : vein.biomes()) biomes.add(biome);
        json.add("biomeWhitelist", biomes);
        if (vein.priority() != null) json.addProperty("priority", vein.priority());
        CreateRecipeJsonHelper.save(provider, "kubejs:" + vein.key() + "_vein", json);
    }

    private static void addDrill(Consumer<FinishedRecipe> provider, Drill drill) {
        JsonObject json = CreateRecipeJsonHelper.recipe("createoreexcavation:drilling");
        JsonArray results = new JsonArray();
        for (Drop drop : drill.drops()) results.add(CreateRecipeJsonHelper.chanceItem(drop.item(), drop.chance()));
        json.add("results", results);
        json.addProperty("vein", "kubejs:" + drill.vein() + "_vein");
        json.addProperty("processingTime", drill.duration());
        json.add("fluid", CreateRecipeJsonHelper.fluid("gtceu:lubricant", 50));
        if (drill.drill() != null) json.addProperty("drill", drill.drill());
        json.addProperty("stress", drill.stress());
        CreateRecipeJsonHelper.save(provider, "kubejs:" + drill.vein() + "_vein1", json);
    }

    private static void addSeawaterExtraction(Consumer<FinishedRecipe> provider) {
        JsonObject json = CreateRecipeJsonHelper.recipe("createoreexcavation:extracting");
        json.add("result", CreateRecipeJsonHelper.fluid("ctnhcore:seawater", 1000));
        json.addProperty("vein", "kubejs:seawater_vein");
        json.addProperty("processingTime", 250);
        json.add("fluid", CreateRecipeJsonHelper.fluid("gtceu:lubricant", 50));
        json.addProperty("stress", 192);
        CreateRecipeJsonHelper.save(provider, "kubejs:seawater_extraction", json);
    }
}
