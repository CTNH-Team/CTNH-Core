package io.github.cpearl0.ctnhcore.data.recipe;

import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;
public class RecipeRemoval {
    public static void init(Consumer<ResourceLocation> registry) {
        ctnhRemovals(registry);
    }

    public static void ctnhRemovals(Consumer<ResourceLocation> registry){
        String[] recipePaths = {
                "gtceu:centrifuge/pgs_separation",
                "gtceu:electrolyzer/raw_platinum_separation",
                "gtceu:chemical_reactor/raw_palladium_separation",
                "gtceu:large_chemical_reactor/inert_metal_mixture_separation",
                "gtceu:extractor/extract_osmium_tetroxide_dust",
                "gtceu:circuit_assembler/wetware_processor_luv_soc_soldering_alloy",
                "gtceu:circuit_assembler/wetware_processor_luv_soc",
                "gtceu:chemical_reactor/inert_metal_mixture_separation",
                "gtceu:electrolyzer/rhodium_sulfate_separation",
                "gtceu:centrifuge/impure_enriched_naquadah_solution_separation",
                "gtceu:centrifuge/acidic_enriched_naquadah_separation",
                "gtceu:large_chemical_reactor/naquadah_separation",
                "gtceu:centrifuge/impure_naquadria_solution_separation",
                "gtceu:centrifuge/acidic_naquadria_solution_separation",
                "gtceu:electrolyzer/decomposition_electrolyzing_cooperite",
                "gtceu:chemical_reactor/hydrogen_peroxide",
                "gtceu:large_chemical_reactor/hydrogen_peroxide",
                "gtceu:electrolyzer/sugar_electrolysis",
                "gtceu:chemical_reactor/saltpeter",
                "gtceu:large_chemical_reactor/saltpeter",
                //数据模块
                "gtceu:circuit_assembler/data_module",
                "gtceu:circuit_assembler/data_module_soldering_alloy",
                //晶体电路
                "gtceu:circuit_assembler/crystal_assembly_luv",
                "gtceu:circuit_assembler/crystal_assembly_luv_soldering_alloy",
                "gtceu:circuit_assembler/crystal_computer_zpm",
                "gtceu:circuit_assembler/crystal_computer_zpm_soldering_alloy",
                "gtceu:assembly_line/crystal_mainframe_uv"

        };

        for (String path : recipePaths) {
            registry.accept(ResourceLocation.parse(path));
        }
    }
}
