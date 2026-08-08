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

        // WoodTypeEntry 统一木材加工所需的原木标签（mod 未自带，由 CTNH 补）
        addOptionals(itemTag("ecologics", "walnut_logs"), List.of(
                "ecologics:walnut_log", "ecologics:stripped_walnut_log",
                "ecologics:walnut_wood", "ecologics:stripped_walnut_wood"));
        addOptionals(itemTag("ecologics", "coconut_logs"), List.of(
                "ecologics:coconut_log", "ecologics:stripped_coconut_log",
                "ecologics:coconut_wood", "ecologics:stripped_coconut_wood"));
        addOptionals(itemTag("ecologics", "azalea_logs"), List.of(
                "ecologics:azalea_log", "ecologics:stripped_azalea_log",
                "ecologics:azalea_wood", "ecologics:stripped_azalea_wood",
                "ecologics:flowering_azalea_log", "ecologics:flowering_azalea_wood"));
        addOptionals(itemTag("cataclysm", "chorus_stems"), List.of("cataclysm:chorus_stem"));
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
