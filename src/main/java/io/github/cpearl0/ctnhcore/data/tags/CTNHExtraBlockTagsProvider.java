package io.github.cpearl0.ctnhcore.data.tags;

import io.github.cpearl0.ctnhcore.CTNHCore;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CTNHExtraBlockTagsProvider extends TagsProvider<Block> {

    public CTNHExtraBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider,
                                      @Nullable ExistingFileHelper existingFileHelper) {
        super(output, Registries.BLOCK, provider, CTNHCore.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        var builder = tag(blockTag("gtceu", "cleanroom_doors"));
        List.of(
                "create:brass_door",
                "create:copper_door",
                "create:train_door",
                "create:framed_glass_door")
                .stream()
                .map(ResourceLocation::parse)
                .forEach(builder::addOptional);
    }

    private static TagKey<Block> blockTag(String namespace, String path) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(namespace, path));
    }
}
