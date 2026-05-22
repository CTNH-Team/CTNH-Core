package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.data.recipe.modmodify.EIORecipes;
import io.github.cpearl0.ctnhcore.data.recipe.modmodify.omnicells.QuantumOmniRecipes;

import net.minecraft.resources.ResourceLocation;

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
                // 数据库
                "gtceu:assembly_line/data_bank",
                // 高级计算机外壳
                "gtceu:assembler/advanced_computer_casing",
                // 计算组件
                "gtceu:assembler/hpca_computation_component",
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
                // 原版样板总成
                "gtceu:assembly_line/me_pattern_buffer",
                "gtceu:assembly_line/me_pattern_buffer_proxy",
                // ME库存输入总线
                // "gtceu:assembler/me_stocking_import_bus",
                // 变电站
                "gtceu:shaped/power_substation",
                // 氯化铵
                "gtceu:extractor/extract_ammonium_chloride_dust",
                // 电解水
                "gtceu:electrolyzer/water_electrolysis",
                // 侯氏制碱法
                "gtceu:large_chemical_reactor/sodium_bicarbonate_from_salt",
                "gtceu:chemical_reactor/sodium_bicarbonate_from_salt",
                // 石墨烯
                "gtceu:mixer/graphene",
                // 钨钢
                "gtceu:mixer/tungstensteel",
                // 原版 UHV 动力仓/能源仓
                "gtceu:assembly_line/dynamo_hatch_uhv",
                "gtceu:assembly_line/energy_hatch_uhv",
                // 原版四氯化钛
                "gtceu:chemical_reactor/titanium_tetrachloride",
                "gtceu:large_chemical_reactor/titanium_tetrachloride",
                // Replace operations from JS migration
                "gtceu:centrifuge/endstone_separation",
                "gtceu:shaped/small_wooden_pipe",
                "gtceu:shaped/hv_diode",
                // SnowAdjust.js migration
                "ctnhcore:assembler/cover_ender_fluid_link",
                // SiliconChain.js replaceOutput
                "gtceu:electrolyzer/zeolite_electrolysis",
                "gtceu:centrifuge/decomposition_centrifuging__redstone",
                // AE2Script migration (from ae2.js KubeJS)
                // remove_recipes_output: AE2 storage components
                "ae2:network/cells/item_storage_components_cell_1k_part",
                "ae2:network/cells/item_storage_components_cell_4k_part",
                "ae2:network/cells/item_storage_components_cell_16k_part",
                "ae2:network/cells/item_storage_components_cell_64k_part",
                "ae2:network/cells/item_storage_components_cell_256k_part",
                "ae2omnicells:components/shaped/omni_cell_component_1k",
                "ae2omnicells:components/shaped/omni_cell_component_4k",
                "ae2omnicells:components/shaped/omni_cell_component_16k",
                "ae2omnicells:components/shaped/omni_cell_component_64k",
                "ae2omnicells:components/shaped/omni_cell_component_256k",
                "ae2omnicells:components/shaped/omni_cell_component_1m",
                "ae2omnicells:components/shaped/omni_cell_component_4m",
                "ae2omnicells:components/shaped/omni_cell_component_16m",
                "ae2omnicells:components/shaped/omni_cell_component_64m",
                "ae2omnicells:components/shaped/omni_cell_component_256m",
                "ae2omnicells:components/shaped/complex_omni_cell_component_1k",
                "ae2omnicells:components/shaped/complex_omni_cell_component_4k",
                "ae2omnicells:components/shaped/complex_omni_cell_component_16k",
                "ae2omnicells:components/shaped/complex_omni_cell_component_64k",
                "ae2omnicells:components/shaped/complex_omni_cell_component_256k",
                "ae2omnicells:components/shaped/complex_omni_cell_component_1m",
                "ae2omnicells:components/shaped/complex_omni_cell_component_4m",
                "ae2omnicells:components/shaped/complex_omni_cell_component_16m",
                "ae2omnicells:components/shaped/complex_omni_cell_component_64m",
                "ae2omnicells:components/shaped/complex_omni_cell_component_256m",
                "ae2omnicells:components/shaped/quantum_omni_cell_component_4k",
                "ae2omnicells:components/shaped/quantum_omni_cell_component_16k",
                "ae2omnicells:components/shaped/quantum_omni_cell_component_64k",
                "ae2omnicells:components/shaped/quantum_omni_cell_component_256k",
                "ae2omnicells:components/shaped/quantum_omni_cell_component_1m",
                "ae2omnicells:components/shaped/quantum_omni_cell_component_4m",
                "ae2omnicells:components/shaped/quantum_omni_cell_component_16m",
                "ae2omnicells:components/shaped/quantum_omni_cell_component_64m",
                "ae2omnicells:components/shaped/quantum_omni_cell_component_256m",
                // remove_recipes_output/remove_recipes_type: AE2 processors and original AE2 recipes
                "ae2:inscriber/logic_processor",
                "ae2:inscriber/calculation_processor",
                "ae2:inscriber/engineering_processor",
                "ae2:inscriber/logic_processor_print",
                "ae2:inscriber/calculation_processor_print",
                "ae2:inscriber/engineering_processor_print",
                "ae2:inscriber/silicon_print",
                "ae2:materials/ender_dust",
                "ae2:materials/certus_quartz_dust",
                "ae2:network/blocks/energy_energy_acceptor",
                "ae2:network/blocks/energy_energy_acceptor_alt",
                "ae2:network/blocks/crystal_processing_charger",
                "ae2:network/blocks/inscribers",
                "ae2:network/cables/glass_fluix",
                "ae2:network/cables/covered_fluix",
                "ae2:network/parts/quartz_fiber_part",
                "ae2:network/blocks/storage_drive",
                "ae2:network/crafting/pattern_encoding_terminal",
                "ae2:network/crafting/molecular_assembler",
                "ae2:network/crafting/cpu_crafting_unit",
                "ae2:network/cells/item_cell_housing",
                "ae2:network/cells/fluid_cell_housing",
                "ae2:network/wireless_part",
                "ae2:network/parts/import_bus",
                "ae2:network/parts/export_bus",
                "ae2:network/parts/wireless_booster",
                "ae2:decorative/quartz_glass",
                "ae2:materials/basiccard",
                "ae2:materials/advancedcard",
                "ae2:transform/certus_quartz_crystals",
                // remove_recipes_output/remove_recipes_type: ExtendedAE and requester recipes
                "expatternprovider:ei",
                "expatternprovider:epp",
                "expatternprovider:ei_upgrade",
                "expatternprovider:epp_upgrade",
                "expatternprovider:ei_part",
                "expatternprovider:epp_part",
                "expatternprovider:ebus_upgrade",
                "expatternprovider:epa",
                "expatternprovider:ex_drive",
                "expatternprovider:ex_drive_upgrade",
                "expatternprovider:tag_storage_bus",
                "expatternprovider:tag_export_bus",
                "expatternprovider:ex_molecular_assembler",
                "expatternprovider:ingredient_buffer",
                "expatternprovider:cutter/accumulation",
                "expatternprovider:cutter/calculation",
                "expatternprovider:cutter/engineering",
                "expatternprovider:cutter/logic",
                "expatternprovider:cutter/silicon",
                "merequester:requester",
                // remove_recipes_id: storage cells and OmniCells recipes from ae2.js
                "expatternprovider:ex_inscriber",
                "expatternprovider:ex_charger",
                "ae2:network/blocks/pattern_providers_interface",
                "ae2:network/blocks/interfaces_interface",
                "ae2:network/cells/item_storage_cell_1k",
                "ae2:network/cells/item_storage_cell_4k",
                "ae2:network/cells/item_storage_cell_16k",
                "ae2:network/cells/item_storage_cell_64k",
                "ae2:network/cells/item_storage_cell_256k",
                "ae2:network/cells/fluid_storage_cell_1k",
                "ae2:network/cells/fluid_storage_cell_4k",
                "ae2:network/cells/fluid_storage_cell_16k",
                "ae2:network/cells/fluid_storage_cell_64k",
                "ae2:network/cells/fluid_storage_cell_256k",
                "ae2omnicells:cells/shaped/omni_cell_1k",
                "ae2omnicells:cells/shaped/omni_cell_4k",
                "ae2omnicells:cells/shaped/omni_cell_16k",
                "ae2omnicells:cells/shaped/omni_cell_64k",
                "ae2omnicells:cells/shaped/omni_cell_256k",
                "ae2omnicells:cells/shaped/omni_cell_1m",
                "ae2omnicells:cells/shaped/omni_cell_4m",
                "ae2omnicells:cells/shaped/omni_cell_16m",
                "ae2omnicells:cells/shaped/omni_cell_64m",
                "ae2omnicells:cells/shaped/omni_cell_256m",
                "ae2omnicells:cells/shaped/complex_omni_cell_1k",
                "ae2omnicells:cells/shaped/complex_omni_cell_4k",
                "ae2omnicells:cells/shaped/complex_omni_cell_16k",
                "ae2omnicells:cells/shaped/complex_omni_cell_64k",
                "ae2omnicells:cells/shaped/complex_omni_cell_256k",
                "ae2omnicells:cells/shaped/complex_omni_cell_1m",
                "ae2omnicells:cells/shaped/complex_omni_cell_4m",
                "ae2omnicells:cells/shaped/complex_omni_cell_16m",
                "ae2omnicells:cells/shaped/complex_omni_cell_64m",
                "ae2omnicells:cells/shaped/complex_omni_cell_256m",
                "ae2omnicells:cells/shaped/quantum_omni_cell_1k",
                "ae2omnicells:cells/shaped/quantum_omni_cell_4k",
                "ae2omnicells:cells/shaped/quantum_omni_cell_16k",
                "ae2omnicells:cells/shaped/quantum_omni_cell_64k",
                "ae2omnicells:cells/shaped/quantum_omni_cell_256k",
                "ae2omnicells:cells/shaped/quantum_omni_cell_1m",
                "ae2omnicells:cells/shaped/quantum_omni_cell_4m",
                "ae2omnicells:cells/shaped/quantum_omni_cell_16m",
                "ae2omnicells:cells/shaped/quantum_omni_cell_64m",
                "ae2omnicells:cells/shaped/quantum_omni_cell_256m",
                "ae2additions:cells/item/disk_256k",
                "ae2things:disk_housing",
                "ae2additions:cells/fluid/disk-housing",
                "ae2additions:super_cell_housing",
                "ae2omnicells:cells/housing/omni_cell_housing",
                "ae2omnicells:cells/housing/complex_omni_cell_housing",
                "ae2omnicells:cells/housing/quantum_omni_cell_housing",
                "ae2omnicells:components/shaped/omni_cell_component_256m",
                "ae2omnicells:components/shaped/complex_omni_cell_component_256m",
                "ae2omnicells:components/shaped/quantum_omni_cell_component_256m",
                "ae2omnicells:processors/omni_link_print_press",
                "ae2omnicells:processors/complex_link_print_press",
                "ae2omnicells:processors/multidimensional_expansion_print_press",
                "ae2omnicells:blocks/omni_crafting_unit_block",
                "ae2omnicells:blocks/complex_crafting_unit_block",
                "ae2omnicells:blocks/quantum_crafting_unit_block"));

        for (String path : removePaths) {
            registry.accept(ResourceLocation.parse(path));
        }
    }
}
