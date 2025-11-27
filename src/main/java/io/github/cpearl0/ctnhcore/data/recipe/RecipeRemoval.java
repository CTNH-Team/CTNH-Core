package io.github.cpearl0.ctnhcore.data.recipe;

import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
public class RecipeRemoval {
    public static List<String> recipePaths = new ArrayList<>();
    public static void init(Consumer<ResourceLocation> registry) {
        centrifugeRecipeRemovals();
        maceratorRecipeRemovals();
        //放最后
        ctnhRemovals(registry);
    }
    public static void centrifugeRecipeRemovals() {
        recipePaths.addAll(List.of(
                "gtceu:centrifuge/ruby_slurry_centrifuging",
                "gtceu:centrifuge/pgs_separation",
                "gtceu:centrifuge/impure_enriched_naquadah_solution_separation",
                "gtceu:centrifuge/acidic_enriched_naquadah_separation",
                "gtceu:centrifuge/impure_naquadria_solution_separation",
                "gtceu:centrifuge/acidic_naquadria_solution_separation",
                "gtceu:centrifuge/rare_earth_separation",
                "gtceu:centrifuge/iridium_metal_residue_separation",
                "gtceu:centrifuge/platinum_group_sludge_dust"
                ));
    }
    public static void maceratorRecipeRemovals() {
        recipePaths.addAll(List.of(
                "gtceu:centrifuge/platinum_group_sludge_dust"
        ));
    }
    public static void benderRecipeRemovals() {
        recipePaths.addAll(List.of(
                "gtceu:bender/bend_graphite_ir_plate_ingot_to_double_plate",
                "gtceu:bender/bend_graphite_ir_plate_plate_to_double_plate"
        ));
    }

    public static void ctnhRemovals(Consumer<ResourceLocation> registry){
        recipePaths.addAll(List.of(
                "gtceu:electrolyzer/raw_platinum_separation",
                "gtceu:chemical_reactor/raw_palladium_separation",
                "gtceu:large_chemical_reactor/inert_metal_mixture_separation",
                "gtceu:extractor/extract_osmium_tetroxide_dust",
                "gtceu:circuit_assembler/wetware_processor_luv_soc_soldering_alloy",
                "gtceu:circuit_assembler/wetware_processor_luv_soc",
                "gtceu:chemical_reactor/inert_metal_mixture_separation",
                "gtceu:electrolyzer/rhodium_sulfate_separation",
                "gtceu:large_chemical_reactor/naquadah_separation",
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
                "gtceu:assembly_line/crystal_mainframe_uv",
                //HPCA
                "gtceu:assembly_line/high_performance_computing_array",
                //凯金线圈
                "gtceu:assembler/coil_trinium",
                //模拟室
                "hostilenetworks:sim_chamber",
                //me总成
                "gtmthings:assembler/me_export_buffer",
                //编程电路卡
                "pccard:item/card_programmed_circuit"

        ));

        for (String path : recipePaths) {
            registry.accept(ResourceLocation.parse(path));
        }
    }

}
