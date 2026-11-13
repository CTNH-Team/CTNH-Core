package io.github.cpearl0.ctnhcore.data.tags;

import io.github.cpearl0.ctnhcore.registry.CTNHTags;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import com.aetherteam.aether.block.AetherBlocks;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import vazkii.botania.common.block.BotaniaBlocks;

import java.util.Objects;

public class StoneTags {

    public static void init(RegistrateTagsProvider<Block> provider) {
        create(provider, CTNHTags.AETHER_STONES, AetherBlocks.HOLYSTONE.get(), AetherBlocks.MOSSY_HOLYSTONE.get(),
                AetherBlocks.ICESTONE.get());
        create(provider, CTNHTags.ALFHEIM_STONES, BotaniaBlocks.livingrock);
    }

    public static void create(RegistrateTagsProvider<Block> provider, TagKey<Block> tagKey, Block... rls) {
        var builder = provider.addTag(tagKey);
        for (Block block : rls) {
            builder.addOptional(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)));
        }
    }
}
