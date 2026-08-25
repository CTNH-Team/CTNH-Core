package io.github.cpearl0.ctnhcore.data.tags;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHTags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class CTNHBiomeTagsProvider extends BiomeTagsProvider {

    public CTNHBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider,
                                 @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, CTNHCore.MODID, existingFileHelper);
    }

    @SuppressWarnings("removal")
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(CTNHTags.WORLDGEN_REMOVAL_BIOMES)
                .addTag(BiomeTags.IS_OVERWORLD)
                .addTag(BiomeTags.IS_NETHER)
                .addOptionalTag(ResourceLocation.tryBuild("aether", "is_aether"))
                .addOptionalTag(ResourceLocation.tryBuild("twilightforest", "valid_quest_grove_biomes"))
                .addOptional(ResourceLocation.tryBuild("mythicbotany", "alfheim"))
                .addOptional(new ResourceLocation("ad_astra:glacio_ice_peaks"))
                .addOptional(new ResourceLocation("ad_astra:glacio_snowy_barrens"))
                .addOptional(new ResourceLocation("ad_astra:infernal_venus_barrens"))
                .addOptional(new ResourceLocation("ad_astra:lunar_wastelands"))
                .addOptional(new ResourceLocation("ad_astra:martian_canyon_creek"))
                .addOptional(new ResourceLocation("ad_astra:martian_polar_caps"))
                .addOptional(new ResourceLocation("ad_astra:martian_wastelands"))
                .addOptional(new ResourceLocation("ad_astra:mercury_deltas"))
                .addOptional(new ResourceLocation("ad_astra:venus_wastelands"));

        tag(CTNHTags.HAS_OILSAND)
                .add(Biomes.DESERT)
                .add(Biomes.SWAMP)
                .add(Biomes.MANGROVE_SWAMP);

        // tag(CTNHTags.TWILIGHT_TIER1)
        // .add(TFBiomes.DARK_FOREST)
        // .add(TFBiomes.SNOWY_FOREST)
        // .add(TFBiomes.SWAMP);
        // tag(CTNHTags.TWILIGHT_TIER2)
        // .add(TFBiomes.DARK_FOREST_CENTER)
        // .add(TFBiomes.GLACIER)
        // .add(TFBiomes.FIRE_SWAMP);
        // tag(CTNHTags.TWILIGHT_TIER3)
        // .add(TFBiomes.HIGHLANDS);
    }
}
