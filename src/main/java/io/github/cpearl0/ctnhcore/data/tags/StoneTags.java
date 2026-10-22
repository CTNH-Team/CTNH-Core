package io.github.cpearl0.ctnhcore.data.tags;

import io.github.cpearl0.ctnhcore.registry.CTNHTags;
import io.github.cpearl0.ctnhcore.registry.ores.MoonOres;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

import com.aetherteam.aether.block.AetherBlocks;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import earth.terrarium.adastra.common.registry.ModBlocks;
import vazkii.botania.common.block.BotaniaBlocks;

import java.util.Objects;

public class StoneTags {

    public static void init(RegistrateTagsProvider<Block> provider) {
        var moonHosts = provider.addTag(CTNHTags.MOON_ORE_REPLACEABLES);
        MoonOres.oreHosts().keySet()
                .forEach(state -> moonHosts.add(state.get().getBlock().builtInRegistryHolder().key()));
        create(provider, CTNHTags.AD_ASTRA_STONES, ModBlocks.MARS_STONE.get(),
                ModBlocks.MERCURY_STONE.get(),
                ModBlocks.VENUS_STONE.get(), ModBlocks.GLACIO_STONE.get(), Blocks.BLACKSTONE, Blocks.BASALT,
                Blocks.DEEPSLATE, Blocks.SOUL_SOIL);
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
