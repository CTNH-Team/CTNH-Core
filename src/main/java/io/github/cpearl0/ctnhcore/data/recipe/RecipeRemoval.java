package io.github.cpearl0.ctnhcore.data.recipe;

import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;
public class RecipeRemoval {
    public static void init(Consumer<ResourceLocation> registry) {
        ctnhRemovals(registry);
    }

    public static void ctnhRemovals(Consumer<ResourceLocation> registry){
        registry.accept(ResourceLocation.parse("gtceu:centrifuge/pgs_separation"));
        registry.accept(ResourceLocation.parse("gtceu:electrolyzer/raw_platinum_separation"));
        registry.accept(ResourceLocation.parse("gtceu:chemical_reactor/raw_palladium_separation"));
        registry.accept(ResourceLocation.parse("gtceu:large_chemical_reactor/inert_metal_mixture_separation"));
        registry.accept(ResourceLocation.parse("gtceu:extractor/extract_osmium_tetroxide_dust"));
        registry.accept(ResourceLocation.parse("gtceu:circuit_assembler/wetware_processor_luv_soc_soldering_alloy"));
        registry.accept(ResourceLocation.parse("gtceu:circuit_assembler/wetware_processor_luv_soc"));
        registry.accept(ResourceLocation.parse("gtceu:chemical_reactor/inert_metal_mixture_separation"));
        registry.accept(ResourceLocation.parse("gtceu:electrolyzer/rhodium_sulfate_separation"));
        registry.accept(ResourceLocation.parse("gtceu:centrifuge/impure_enriched_naquadah_solution_separation"));
        registry.accept(ResourceLocation.parse("gtceu:centrifuge/acidic_enriched_naquadah_separation"));
        registry.accept(ResourceLocation.parse("gtceu:large_chemical_reactor/naquadah_separation"));
        registry.accept(ResourceLocation.parse("gtceu:centrifuge/impure_naquadria_solution_separation"));
        registry.accept(ResourceLocation.parse("gtceu:centrifuge/acidic_naquadria_solution_separation"));
        registry.accept(ResourceLocation.parse("gtceu:electrolyzer/decomposition_electrolyzing_cooperite"));
    }
}
