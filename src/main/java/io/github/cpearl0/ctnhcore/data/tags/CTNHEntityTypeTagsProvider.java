package io.github.cpearl0.ctnhcore.data.tags;

import io.github.cpearl0.ctnhcore.CTNHCore;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CTNHEntityTypeTagsProvider extends TagsProvider<EntityType<?>> {

    private static final List<ResourceLocation> ALL_BOSSES = List.of(
            ResourceLocation.parse("twilightforest:swarm_spider"),
            ResourceLocation.parse("twilightforest:hedge_spider"),
            ResourceLocation.parse("minecraft:wither"),
            ResourceLocation.parse("minecraft:ender_dragon"),
            ResourceLocation.parse("alexscaves:luxtructosaurus"),
            ResourceLocation.parse("alexscaves:tremorzilla"),
            ResourceLocation.parse("ars_nouveau:wilden_boss"),
            ResourceLocation.parse("artifacts:mimic"),
            ResourceLocation.parse("cataclysm:ender_guardian"),
            ResourceLocation.parse("cataclysm:ignis"),
            ResourceLocation.parse("cataclysm:netherite_monstrosity"),
            ResourceLocation.parse("cataclysm:the_harbinger"),
            ResourceLocation.parse("cataclysm:the_leviathan"),
            ResourceLocation.parse("cataclysm:ancient_remnant"),
            ResourceLocation.parse("twilightforest:naga"),
            ResourceLocation.parse("twilightforest:lich"),
            ResourceLocation.parse("twilightforest:minoshroom"),
            ResourceLocation.parse("twilightforest:hydra"),
            ResourceLocation.parse("twilightforest:knight_phantom"),
            ResourceLocation.parse("twilightforest:ur_ghast"),
            ResourceLocation.parse("twilightforest:yeti_alpha"),
            ResourceLocation.parse("twilightforest:snow_queen"));

    public CTNHEntityTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider,
                                      @Nullable ExistingFileHelper existingFileHelper) {
        super(output, Registries.ENTITY_TYPE, provider, CTNHCore.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        addBossesToTag(entityTag("ars_nouveau", "drygmy_blacklist"));
        addBossesToTag(entityTag("ars_nouveau", "jar_blacklist"));
        addBossesToTag(entityTag("ars_nouveau", "jar_release_blacklist"));
    }

    private void addBossesToTag(TagKey<EntityType<?>> tagKey) {
        var builder = tag(tagKey);
        ALL_BOSSES.forEach(builder::addOptional);
    }

    private static TagKey<EntityType<?>> entityTag(String namespace, String path) {
        return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(namespace, path));
    }
}
