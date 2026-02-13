package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.data.recipe.modmodify.EIORecipes;
import io.github.cpearl0.ctnhcore.data.recipe.modmodify.omnicells.QuantumOmniRecipes;
import net.minecraft.resources.ResourceLocation;
import tech.vixhentx.mcmod.ctnhlib.data.DataFilterPack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RecipeRemoval {

    public static List<String> removePaths = new ArrayList<>();

    public static void init(Consumer<ResourceLocation> registry) {
        centrifugeRecipeRemovals();
        maceratorRecipeRemovals();
        EIORecipes.eioRemovals();
        QuantumOmniRecipes.omniRemovals();
        // 放最后
        ctnhRemovals(registry);
    }

    public static void centrifugeRecipeRemovals() {
        removePaths.addAll(List.of(
                "gtceu:centrifuge/ruby_slurry_centrifuging",
                "gtceu:centrifuge/pgs_separation",
                "gtceu:centrifuge/impure_enriched_naquadah_solution_separation",
                "gtceu:centrifuge/acidic_enriched_naquadah_separation",
                "gtceu:centrifuge/impure_naquadria_solution_separation",
                "gtceu:centrifuge/acidic_naquadria_solution_separation",
                "gtceu:centrifuge/rare_earth_separation",
                "gtceu:centrifuge/iridium_metal_residue_separation",
                "gtceu:centrifuge/platinum_group_sludge_dust"));
    }

    public static void maceratorRecipeRemovals() {
        removePaths.addAll(List.of(
                "gtceu:centrifuge/platinum_group_sludge_dust"));
    }

    public static void benderRecipeRemovals() {
        removePaths.addAll(List.of(
                "gtceu:bender/bend_graphite_ir_plate_ingot_to_double_plate",
                "gtceu:bender/bend_graphite_ir_plate_plate_to_double_plate"));
    }

    public static void ctnhRemovals(Consumer<ResourceLocation> registry) {
        removePaths.addAll(List.of(
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
                "gtceu:shaped/casing_hsse_sturdy",
                // 数据模块
                "gtceu:circuit_assembler/data_module",
                "gtceu:circuit_assembler/data_module_soldering_alloy",
                // 晶体电路
                "gtceu:circuit_assembler/crystal_assembly_luv",
                "gtceu:circuit_assembler/crystal_assembly_luv_soldering_alloy",
                "gtceu:circuit_assembler/crystal_computer_zpm",
                "gtceu:circuit_assembler/crystal_computer_zpm_soldering_alloy",
                "gtceu:assembly_line/crystal_mainframe_uv",
                // HPCA
                "gtceu:assembly_line/high_performance_computing_array",
                // 凯金线圈
                "gtceu:assembler/coil_trinium",
                // 模拟室
                "hostilenetworks:sim_chamber",
                // 编程电路卡
                "pccard:item/card_programmed_circuit",
                // 湿件
                "gtceu:circuit_assembler/wetware_processor_luv",
                "gtceu:circuit_assembler/wetware_processor_luv_soldering_alloy",
                "gtceu:circuit_assembler/wetware_processor_assembly_zpm",
                "gtceu:circuit_assembler/wetware_processor_assembly_zpm_soldering_alloy",
                "gtceu:assembly_line/wetware_super_computer_uv",
                "gtceu:assembly_line/wetware_mainframe_uhv",
                "gtceu:research_station/1x_gtceu_wetware_processor_assembly",
                "gtceu:research_station/1x_gtceu_wetware_processor_computer",
                //原版样板总成
                "gtceu:assembly_line/me_pattern_buffer",
                "gtceu:assembly_line/me_pattern_buffer_proxy",
                //ME库存输入总线
                "gtceu:assembler/me_stocking_import_bus",
                //变电站
                "gtceu:shaped/power_substation",
                //氯化铵
                "gtceu:extractor/extract_ammonium_chloride_dust",
                //电解水
                "gtceu:electrolyzer/water_electrolysis",
                //侯氏制碱法
                "gtceu:large_chemical_reactor/sodium_bicarbonate_from_salt",
                "gtceu:chemical_reactor/sodium_bicarbonate_from_salt"

        ));

        for (String path : removePaths) {
            registry.accept(ResourceLocation.parse(path));
        }
    }
}
