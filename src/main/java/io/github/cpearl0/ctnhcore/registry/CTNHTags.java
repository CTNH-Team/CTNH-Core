package io.github.cpearl0.ctnhcore.registry;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.tag.TagUtil;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class CTNHTags {

    public static TagKey<Block> AD_ASTRA_STONES = TagUtil.createBlockTag("ad_astra_stones");
    public static TagKey<Block> AETHER_STONES = TagUtil.createBlockTag("aether_stones");
    public static TagKey<Block> ALFHEIM_STONES = TagUtil.createBlockTag("alfheim_stones");
    // public static TagKey<Item> TIER5_RUNES = TagUtil.createItemTag("zenith_runes");
    // public static TagKey<Biome> TWILIGHT_TIER1 = TagUtil.createTag(Registries.BIOME, "twilight_tier1", true);
    // public static TagKey<Biome> TWILIGHT_TIER2 = TagUtil.createTag(Registries.BIOME, "twilight_tier2", true);
    // public static TagKey<Biome> TWILIGHT_TIER3 = TagUtil.createTag(Registries.BIOME, "twilight_tier3", true);
    public static final TagKey<Biome> WORLDGEN_REMOVAL_BIOMES = TagKey.create(Registries.BIOME,
            CTNHCore.id("worldgen_removal_biomes"));

    public static final TagKey<Biome> HAS_ASTRAL_METEORITES = TagKey.create(Registries.BIOME,
            CTNHCore.id("astral_meteorites"));
}
