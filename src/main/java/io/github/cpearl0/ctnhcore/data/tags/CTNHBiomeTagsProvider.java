package io.github.cpearl0.ctnhcore.data.tags;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHTags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import org.jetbrains.annotations.Nullable;
import twilightforest.init.TFBiomes;

import java.util.concurrent.CompletableFuture;

public class CTNHBiomeTagsProvider extends BiomeTagsProvider {

    public CTNHBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider,
                                 @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, CTNHCore.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(CTNHTags.TWILIGHT_TIER1)
                .add(TFBiomes.DARK_FOREST)
                .add(TFBiomes.SNOWY_FOREST)
                .add(TFBiomes.SWAMP);
        tag(CTNHTags.TWILIGHT_TIER2)
                .add(TFBiomes.DARK_FOREST_CENTER)
                .add(TFBiomes.GLACIER)
                .add(TFBiomes.FIRE_SWAMP);
        tag(CTNHTags.TWILIGHT_TIER3)
                .add(TFBiomes.HIGHLANDS);
    }
}
