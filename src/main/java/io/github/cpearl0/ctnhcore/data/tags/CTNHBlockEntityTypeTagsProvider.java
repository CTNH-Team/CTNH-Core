package io.github.cpearl0.ctnhcore.data.tags;

import io.github.cpearl0.ctnhcore.CTNHCore;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.data.ExistingFileHelper;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;

public class CTNHBlockEntityTypeTagsProvider extends TagsProvider<BlockEntityType<?>> {

    public CTNHBlockEntityTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider,
                                           @Nullable ExistingFileHelper existingFileHelper) {
        super(output, Registries.BLOCK_ENTITY_TYPE, provider, CTNHCore.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        var builder = tag(blockEntityTypeTag("tconstruct", "side_inventories"));
        builder.addOptional(ResourceLocation.parse("sophisticatedstorage:controller"));
        builder.addOptional(ResourceLocation.parse("sophisticatedstorage:chest"));
        builder.addOptional(ResourceLocation.parse("sophisticatedbackpacks:backpack"));
        var storageChestPattern = Pattern.compile("sophisticatedstorage:(.*)_chest");
        BuiltInRegistries.BLOCK_ENTITY_TYPE.keySet().stream()
                .filter(id -> storageChestPattern.matcher(id.toString()).matches())
                .forEach(builder::addOptional);
        var backpackPattern = Pattern.compile("sophisticatedbackpacks:(.*)_backpack");
        BuiltInRegistries.BLOCK_ENTITY_TYPE.keySet().stream()
                .filter(id -> backpackPattern.matcher(id.toString()).matches())
                .forEach(builder::addOptional);
    }

    private static TagKey<BlockEntityType<?>> blockEntityTypeTag(String namespace, String path) {
        return TagKey.create(Registries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(namespace, path));
    }
}
