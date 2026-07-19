package io.github.cpearl0.ctnhcore.data.tags;

import io.github.cpearl0.ctnhcore.CTNHCore;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CTNHExtraItemTagsProvider extends TagsProvider<Item> {

    private static final List<String> FERMENTABLE = List.of(
            "minecraft:carrot", "minecraft:potato", "minecraft:beetroot",
            "minecraft:wheat_seeds", "minecraft:pumpkin_seeds", "minecraft:melon_stem");

    private static final List<String> UPRIGHT_ON_BELT = List.of(
            "gtmfo:smore_1", "gtmfo:smore_2", "gtmfo:smore_4");

    public CTNHExtraItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider,
                                     @Nullable ExistingFileHelper existingFileHelper) {
        super(output, Registries.ITEM, provider, CTNHCore.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        addOptionals(itemTag("forge", "fermentable"), FERMENTABLE);
        addOptionals(itemTag("forge", "stripped_logs"), List.of("gtceu:stripped_rubber_log"));
        addOptionals(itemTag("curios", "curio"), List.of("tiab:time_in_a_bottle"));
        addOptionals(itemTag("create", "upright_on_belt"), UPRIGHT_ON_BELT);
    }

    private void addOptionals(TagKey<Item> tagKey, List<String> ids) {
        var builder = tag(tagKey);
        ids.stream()
                .map(ResourceLocation::parse)
                .forEach(builder::addOptional);
    }

    private static TagKey<Item> itemTag(String namespace, String path) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(namespace, path));
    }
}
