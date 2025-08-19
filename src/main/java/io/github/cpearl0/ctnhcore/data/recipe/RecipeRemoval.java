package io.github.cpearl0.ctnhcore.data.recipe;

import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;
public class RecipeRemoval {
    public static void init(Consumer<ResourceLocation> registry) {
        ctnhRemovals(registry);
    }

    public static void ctnhRemovals(Consumer<ResourceLocation> registry){
        registry.accept(new ResourceLocation("gtceu:centrifuge/pgs_separation"));
        registry.accept(new ResourceLocation("gtceu:electrolyzer/raw_platinum_separation"));
        registry.accept(new ResourceLocation("gtceu:chemical_reactor/raw_palladium_separation"));
        registry.accept(new ResourceLocation("gtceu:large_chemical_reactor/inert_metal_mixture_separation"));
        registry.accept(new ResourceLocation("gtceu:extractor/extract_osmium_tetroxide_dust"));
        registry.accept(new ResourceLocation("gtceu:centrifuge/pgs_separation"));
        registry.accept(new ResourceLocation("gtceu:centrifuge/pgs_separation"));
        registry.accept(new ResourceLocation("gtceu:centrifuge/pgs_separation"));

    }
}
