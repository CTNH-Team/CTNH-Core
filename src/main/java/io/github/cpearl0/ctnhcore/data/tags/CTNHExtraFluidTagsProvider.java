package io.github.cpearl0.ctnhcore.data.tags;

import io.github.cpearl0.ctnhcore.CTNHCore;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.data.ExistingFileHelper;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class CTNHExtraFluidTagsProvider extends TagsProvider<Fluid> {

    public CTNHExtraFluidTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider,
                                      @Nullable ExistingFileHelper existingFileHelper) {
        super(output, Registries.FLUID, provider, CTNHCore.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(fluidTag("createdieselgenerators", "pumpjack_output"))
                .addOptional(CTNHCore.id("impure_oil"))
                .remove(ResourceLocation.fromNamespaceAndPath("createdieselgenerators", "crude_oil"));
        tag(fluidTag("forge", "ethanol"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("gtceu", "ethanol"));
    }

    private static TagKey<Fluid> fluidTag(String namespace, String path) {
        return TagKey.create(Registries.FLUID, ResourceLocation.fromNamespaceAndPath(namespace, path));
    }
}
