package io.github.cpearl0.ctnhcore.data.worldgen;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CTNHBiomeModifiers implements DataProvider {

    private static final List<String> TARGET_BIOMES = List.of(
            "#minecraft:is_overworld",
            "#minecraft:is_nether",
            "#aether:is_aether",
            "#twilightforest:valid_quest_grove_biomes",
            "#ad_astra:has_moon_structure",
            "#ad_astra:has_mars_structure",
            "#ad_astra:has_venus_structure",
            "#ad_astra:has_mercury_structure",
            "#ad_astra:has_glacio_structure",
            "mythicbotany:alfheim");

    private static final List<String> UNDERGROUND_ORES = List.of(
            "aether:gravitite_ore",
            "aether:gravitite_ore_buried",
            "aether:ambrosium_ore",
            "aether:zanite_ore",
            "ad_astra:glacio_coal_ore",
            "ad_astra:glacio_copper_ore",
            "ad_astra:glacio_deepslate_coal_ore",
            "ad_astra:glacio_deepslate_copper_ore",
            "ad_astra:glacio_deepslate_iron_ore",
            "ad_astra:glacio_deepslate_lapis_ore",
            "ad_astra:glacio_ice_shard_ore",
            "ad_astra:glacio_iron_ore",
            "ad_astra:glacio_lapis_ore",
            "ad_astra:mars_diamond_ore",
            "ad_astra:mars_ice_shard_ore",
            "ad_astra:mars_iron_ore",
            "ad_astra:mars_ostrum_ore",
            "ad_astra:mercury_iron_ore",
            "ad_astra:moon_cheese_ore",
            "ad_astra:moon_desh_ore",
            "ad_astra:moon_ice_shard_ore",
            "ad_astra:moon_iron_ore",
            "ad_astra:venus_calorite_ore",
            "ad_astra:venus_coal_ore",
            "ad_astra:venus_diamond_ore",
            "ad_astra:venus_gold_ore",
            "twilightforest:legacy_coal_ore",
            "twilightforest:legacy_iron_ore",
            "twilightforest:legacy_gold_ore",
            "twilightforest:legacy_redstone_ore",
            "twilightforest:legacy_diamond_ore",
            "twilightforest:legacy_lapis_ore",
            "twilightforest:legacy_copper_ore",
            "create:zinc_ore",
            "ae2cs:certus_quartz_ore_placed",
            "ae2cs:charged_certus_quartz_ore_placed",
            "mythicbotany:elementium_ore",
            "mythicbotany:dragonstone_ore",
            "mythicbotany:gold_ore");

    private final PackOutput.PathProvider biomeModifierPathProvider;
    private final PackOutput.PathProvider biomeTagPathProvider;
    private final PackOutput.PathProvider placedFeatureTagPathProvider;

    public CTNHBiomeModifiers(PackOutput output) {
        this.biomeModifierPathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "forge/biome_modifier");
        this.biomeTagPathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "tags/worldgen/biome");
        this.placedFeatureTagPathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK,
                "tags/worldgen/placed_feature");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        CompletableFuture<?> undergroundOres = DataProvider.saveStable(output,
                createRemoveFeaturesModifier(UNDERGROUND_ORES, "underground_ores", "underground_decoration"),
                biomeModifierPathProvider.json(ResourceLocation.tryBuild("ctnhcore", "remove_worldgen_ores")));
        CompletableFuture<?> biomeTag = DataProvider.saveStable(output,
                createOptionalTag(TARGET_BIOMES),
                biomeTagPathProvider.json(ResourceLocation.tryBuild("ctnhcore", "worldgen_removal_biomes")));
        CompletableFuture<?> oreTag = DataProvider.saveStable(output,
                createOptionalTag(UNDERGROUND_ORES),
                placedFeatureTagPathProvider.json(ResourceLocation.tryBuild("ctnhcore", "worldgen_removal_ores")));
        // Separate direct-remove modifier for vanilla ancient debris (nether-hardcoded)
        CompletableFuture<?> ancientDebris = DataProvider.saveStable(output,
                createDirectRemoveModifier(
                        new String[] { "minecraft:ore_ancient_debris_large", "minecraft:ore_debris_small" },
                        "underground_decoration"),
                biomeModifierPathProvider.json(ResourceLocation.tryBuild("ctnhcore", "remove_ancient_debris")));
        return CompletableFuture.allOf(undergroundOres, biomeTag, oreTag, ancientDebris);
    }

    private static JsonObject createRemoveFeaturesModifier(List<String> features, String... steps) {
        JsonObject json = new JsonObject();
        json.addProperty("type", "forge:remove_features");
        json.addProperty("biomes", "#ctnhcore:worldgen_removal_biomes");
        json.addProperty("features", "#ctnhcore:worldgen_removal_ores");
        JsonArray stepsArray = new JsonArray();
        for (var step : steps) {
            stepsArray.add(step);
        }
        json.add("steps", stepsArray);
        return json;
    }

    private static JsonObject createOptionalTag(List<String> values) {
        JsonObject json = new JsonObject();
        json.addProperty("replace", false);
        JsonArray array = new JsonArray();
        for (String value : values) {
            JsonObject entry = new JsonObject();
            entry.addProperty("id", value);
            entry.addProperty("required", false);
            array.add(entry);
        }
        json.add("values", array);
        return json;
    }

    /** Creates a remove_features modifier with a direct feature ID (not tag) for vanilla-proof removal. */
    private static JsonObject createDirectRemoveModifier(String[] featureIds, String step) {
        JsonObject json = new JsonObject();
        json.addProperty("type", "forge:remove_features");
        json.addProperty("biomes", "#minecraft:is_nether");
        JsonArray featuresArray = new JsonArray();
        for (String id : featureIds) {
            featuresArray.add(id);
        }
        json.add("features", featuresArray);
        JsonArray stepsArray = new JsonArray();
        stepsArray.add(step);
        json.add("steps", stepsArray);
        return json;
    }

    @Override
    public String getName() {
        return "CTNH Core Biome Modifiers";
    }
}
