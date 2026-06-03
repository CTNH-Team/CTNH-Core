package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.data.recipe.immersiveaircraft.ImmersiveAircraftRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.modmodify.EIORecipes;
import io.github.cpearl0.ctnhcore.data.recipe.modmodify.omnicells.QuantumOmniRecipes;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RecipeRemoval {

    public static List<String> removePaths = new ArrayList<>();

    public static void init(Consumer<ResourceLocation> registry) {
        removePaths.clear();
        centrifugeRecipeRemovals();
        maceratorRecipeRemovals();
        EIORecipes.eioRemovals();
        QuantumOmniRecipes.omniRemovals();
        ImmersiveAircraftRecipes.removals(registry);
        crafttableRecipeRemovals();
        biomancyRemovals();
        functionalStorageRemovals();
        dieselGeneratorRemovals();
        vintageRemovals();
        tconstructRecipeRemovals();
        migratedModRecipeRemovals();
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

    public static void crafttableRecipeRemovals() {
        removePaths.addAll(List.of(
                "sophisticatedbackpacks:stack_upgrade_omega_tier",
                "deep_aether:skyroot_crafting_table",
                "aether:skyroot_crafting_table",
                "aether:skyroot_chest"));
    }

    public static void tconstructRecipeRemovals() {
        // 迁移来源：Z:/Git/Create-New-Horizon/kubejs/server_scripts/src/tconstruct/remove_tinkers_repices.js
        removePaths.addAll(List.of(
                "tconstruct:smeltery/casting/clay/block",
                "tconstruct:smeltery/melting/metal/iron/chain_boots",
                "tconstruct:smeltery/melting/metal/iron/chain_chestplate",
                "tconstruct:smeltery/melting/metal/iron/chain_helmet",
                "tconstruct:smeltery/melting/metal/iron/chain_leggings",
                "tconstruct:smeltery/casting/filling/scorched_ingot_gauge",
                "tconstruct:smeltery/casting/filling/scorched_ingot_tank",
                "tconstruct:smeltery/casting/filling/scorched_fuel_gauge",
                "tconstruct:smeltery/casting/filling/scorched_fuel_tank",
                "tconstruct:smeltery/casting/filling/scorched_lantern_full",
                "tconstruct:smeltery/casting/filling/scorched_lantern_pixel",
                "tconstruct:smeltery/casting/filling/seared_ingot_tank",
                "tconstruct:smeltery/casting/filling/seared_ingot_gauge",
                "tconstruct:smeltery/casting/filling/seared_fuel_gauge",
                "tconstruct:smeltery/casting/filling/seared_fuel_tank",
                "tconstruct:smeltery/casting/filling/seared_lantern_full",
                "tconstruct:smeltery/casting/filling/seared_lantern_pixel",
                "tconstruct:smeltery/melting/metal/iron/nugget",
                "tconstruct:smeltery/entity_melting/heads/creeper",
                "tconstruct:smeltery/melting/amethyst/tinted_glass",
                "tconstruct:smeltery/melting/ender/end_crystal",
                "tconstruct:smeltery/melting/metal/copper/gauge",
                "tconstruct:smeltery/melting/obsidian/beacon",
                "tconstruct:smeltery/melting/obsidian/gauge",
                "tconstruct:smeltery/melting/quartz/daylight_detector",
                "tconstruct:smeltery/melting/scorched/glass_tinted",
                "tconstruct:smeltery/melting/seared/fluid_cannon",
                "tconstruct:smeltery/melting/seared/fuel_tank",
                "tconstruct:smeltery/melting/seared/gauge",
                "tconstruct:smeltery/melting/seared/glass",
                "tconstruct:smeltery/melting/seared/glass_tinted",
                "tconstruct:smeltery/melting/seared/ingot_tank",
                "tconstruct:smeltery/melting/seared/lantern",
                "tconstruct:smeltery/melting/seared/melter",
                "tconstruct:smeltery/melting/seared/pane",
                "tconstruct:smeltery/melting/seared/seared_casting_tank",
                "tconstruct:tools/materials/melting/glass",
                "tconstruct:smeltery/casting/ender/eye",
                "tconstruct:smeltery/melting/metal/gold/gilded_blackstone",
                "tconstruct:smeltery/melting/metal/gold/nether_gold_ore"));

        removePaths.addAll(List.of(
                "tconstruct:compat/create/andesite_alloy_mixing",
                "tconstruct:compat/create/andesite_alloy_melting"));

        for (String clayPath : List.of("ball", "block", "brick", "terracotta")) {
            removePaths.add("tconstruct:smeltery/melting/clay/" + clayPath);
        }
        for (String cast : List.of("gold", "sand", "red_sand")) {
            removePaths.add("tconstruct:smeltery/casting/clay/brick_" + cast + "_cast");
        }

        for (String fluidMaterial : List.of("diamond", "emerald", "precious_alloy", "tin", "silver", "zinc", "nickel",
                "lead", "beryllium", "molybdenum", "brass", "gold", "iron", "bronze", "copper", "cobalt",
                "manganese", "slag", "steel", "aluminum", "uranium", "glass", "invar", "platinum")) {
            removePaths.add("tconstruct:smeltery/melting/metal/" + fluidMaterial + "/raw");
            removePaths.add("tconstruct:smeltery/melting/metal/" + fluidMaterial + "/raw_block");
            removePaths.add("tconstruct:smeltery/melting/metal/" + fluidMaterial + "/ore_singular");
            removePaths.add("tconstruct:smeltery/melting/metal/" + fluidMaterial + "/ore_dense");
            removePaths.add("tconstruct:smeltery/melting/metal/" + fluidMaterial + "/ore_sparse");
            removePaths.add("tconstruct:smeltery/melting/metal/" + fluidMaterial + "/geore");
        }
    }

    public static void biomancyRemovals() {
        // biomancy:bio_brewing, biomancy:bio_forging, biomancy:digesting — recipe type removal
        // (handled via GTCEu remove recipe type mechanism)
    }

    public static void dieselGeneratorRemovals() {
        removePaths.addAll(List.of(
                "createdieselgenerators:crafting/engine_piston_from_rods",
                "createdieselgenerators:mixing/asphalt_block",
                "createdieselgenerators:crafting/asphalt_block",
                "createdieselgenerators:mixing/biodiesel",
                // 迁移自 kubejs：replaceInput pumpjack_crank + replaceOutput plant_oil
                "createdieselgenerators:mechanical_crafting/pumpjack_crank",
                "createdieselgenerators:compacting/plant_oil"));
    }

    public static void functionalStorageRemovals() {
        removePaths.addAll(List.of(
                "functionalstorage:storage_controller",
                "functionalstorage:framed_storage_controller",
                "functionalstorage:copper_upgrade",
                "functionalstorage:gold_upgrade",
                "functionalstorage:diamond_upgrade",
                "functionalstorage:netherite_upgrade",
                "functionalstorage:oak_drawer_alternate_x1",
                "functionalstorage:oak_drawer_alternate_x2",
                "functionalstorage:oak_drawer_alternate_x4"));
    }

    public static void vintageRemovals() {
        // 迁移自 kubejs/server_scripts/src/create/createFallen.js：remove_recipes_id + remove_recipes_type
        removePaths.addAll(List.of(
                "vintageimprovements:craft/centrifuge",
                "vintageimprovements:craft/spring_coiling_machine",
                "vintageimprovements:craft/vacuum_chamber",
                "vintageimprovements:craft/vibrating_table",
                "vintageimprovements:craft/curving_press",
                "vintageimprovements:craft/laser",
                "vintageimprovements:mechanical_crafting/helve_hammer",
                // 原版 coiling 配方（remove_recipes_type "vintageimprovements:coiling"）
                "vintageimprovements:coiling/iron_spring",
                "vintageimprovements:coiling/gold_spring",
                "vintageimprovements:coiling/steel_spring",
                "vintageimprovements:coiling/copper_spring"));
        // 迁移自 kubejs：remove_recipes_id 移除电路板工作台合成
        removePaths.addAll(List.of(
                "gtceu:shaped/basic_circuit_board",
                "gtceu:shaped/good_circuit_board"));
    }

    public static void migratedModRecipeRemovals() {
        removePaths.addAll(List.of(
                // 迁移来源：Z:/Git/Create-New-Horizon/kubejs/server_scripts/src/apothesis/spawner.js
                "apotheosis:spawner/ignore_light",
                "apotheosis:spawner/ignore_light_inverted",
                "apotheosis:spawner/spawn_count",
                "apotheosis:spawner/spawn_count_inverted",
                "apotheosis:spawner/max_nearby",
                "apotheosis:spawner/max_nearby_inverted",
                "apotheosis:spawner/baby",
                "apotheosis:spawner/baby_inverted",
                "apotheosis:spawner/redstone_control",
                "apotheosis:spawner/redstone_control_inverted",
                "apotheosis:spawner/no_ai",
                "apotheosis:spawner/no_ai_inverted",
                "apotheosis:spawner/min_delay",
                "apotheosis:spawner/min_delay_inverted",
                "apotheosis:spawner/max_delay",
                "apotheosis:spawner/max_delay_inverted",
                "apotheosis:spawner/ignore_conditions",
                "apotheosis:spawner/ignore_conditions_inverted",
                "apotheosis:spawner/player_range",
                "apotheosis:spawner/player_range_inverted",
                "apotheosis:spawner/ignore_players",
                "apotheosis:spawner/ignore_players_inverted",
                "apotheosis:spawner/spawn_range",
                "apotheosis:spawner/spawn_range_inverted",
                // 迁移来源：Z:/Git/Create-New-Horizon/kubejs/server_scripts/src/sophisticatedbackpacks/sophisticatedbackpacks.js
                "sophisticatedbackpacks:void_upgrade",
                // 迁移来源：Z:/Git/Create-New-Horizon/kubejs/server_scripts/src/sophisticatedbackpacks/sophisticatedstorage.js
                "sophisticatedstorage:basic_to_copper_tier_upgrade",
                "sophisticatedstorage:basic_to_iron_tier_upgrade",
                "sophisticatedstorage:basic_to_gold_tier_upgrade",
                "sophisticatedstorage:basic_to_diamond_tier_upgrade",
                "sophisticatedstorage:basic_to_netherite_tier_upgrade",
                "sophisticatedstorage:copper_to_iron_tier_upgrade",
                "sophisticatedstorage:copper_to_gold_tier_upgrade",
                "sophisticatedstorage:copper_to_diamond_tier_upgrade",
                "sophisticatedstorage:copper_to_netherite_tier_upgrade",
                "sophisticatedstorage:iron_to_gold_tier_upgrade",
                "sophisticatedstorage:iron_to_diamond_tier_upgrade",
                "sophisticatedstorage:iron_to_netherite_tier_upgrade",
                "sophisticatedstorage:gold_to_diamond_tier_upgrade",
                "sophisticatedstorage:gold_to_netherite_tier_upgrade",
                "sophisticatedstorage:diamond_to_netherite_tier_upgrade"));
    }

    public static void ctnhRemovals(Consumer<ResourceLocation> registry) {
        // gtceu_remove.js migration
        removePaths.addAll(List.of(
                "gtceu:assembler/plate_radiation_2",
                "gtceu:assembler/plate_radiation",
                "gtceu:electric_blast_furnace/iro2",
                "gtceu:shaped/casing_assembly_control",
                "gtceu:shaped/casing_assembly_line",
                "gtceu:electrolyzer/decomposition_electrolyzing_ammonium_chloride",
                "gtceu:assembler/mar_casing",
                "gtceu:arc_furnace/arc_cleaning_maintenance_hatch",
                "gtceu:smashing_factory_recipes/smashing_factory_recipes/macerate_cleaning_maintenance_hatch",
                "gtceu:fusion_reactor/americium_and_naquadria_to_neutronium_plasma",
                "gtceu:large_chemical_reactor/raw_palladium_separation",
                "gtceu:electrolyzer/decomposition_electrolyzing_niobium_oxide",
                "gtceu:electrolyzer/decomposition_electrolyzing_tantalite_oxide",
                "gtceu:assembler/casing_hsse_sturdy",
                "gtceu:shaped/diamond_sword",
                "gtceu:electric_blast_furnace/titanium_from_tetrachloride",
                "gtceu:chemical_reactor/titaniumtetrachloride",
                "gtceu:electrolyzer/tungstic_acid_electrolysis",
                "gtceu:neutron_activator/naquadah",
                "gtceu:large_chemical_reactor/iridium_chloride",
                "gtceu:large_chemical_reactor/iridium_dioxide_dissolving",
                "gtceu:electric_blast_furnace/iridium_metal_residue_processh",
                "gtceu:chemical_reactor/iridium_chloride",
                "gtceu:large_chemical_reactor/iridium_chloride_separation",
                "gtceu:large_chemical_reactor/raw_platinum_separation",
                "gtceu:chemical_reactor/raw_platinum_separation",
                "gtceu:electric_blast_furnace/refined_platinum_salt_dust_ebf",
                "gtceu:electric_blast_furnace/iridium_metal_residue_process",
                "gtceu:smelting/smelt_dust_bedrock_dust_to_ingot",
                "gtceu:arc_furnace/arc_bedrock_dust_dust",
                "gtceu:chemical_reactor/indium_concentrate_separation",
                "gtceu:chemical_reactor/indium_concentrate_separation_4x",
                "gtceu:large_chemical_reactor/indium_concentrate_separation_4x",
                "gtceu:electrolyzer/decomposition_electrolyzing_aluminium_sulfite",
                "gtceu:large_chemical_reactor/phosphoric_acid_from_pentoxide",
                "gtceu:shaped/large_bronze_boiler",
                "gtceu:chemical_reactor/soda_ash_from_carbon_dioxide",
                "gtceu:electric_blast_furnace/blast_adamantite",
                "gtceu:fluid_solidifier/solidify_adamantite_to_plate",
                "gtceu:fluid_solidifier/solidify_adamantite_gear",
                "gtceu:fluid_solidifier/solidify_adamantite_block",
                "gtceu:fluid_solidifier/solidify_adamantite_to_ingot",
                "gtceu:fluid_solidifier/solidify_adamantite_small_gear",
                "gtceu:fluid_solidifier/solidify_adamantite_to_nugget",
                "gtceu:shaped/plate_double_graphite_ir_plate",
                "gtceu:chemical_reactor/iridium_dioxide_dissolving",
                "gtceu:chemical_reactor/iridium_chloride_separation",
                "gtceu:dehydrator/xenoauric_fluoroantimonic_acid",
                "gtceu:rocket_engine/rp_1_mixed_fuel",
                "gtceu:rocket_engine/methylhydrazine_nitrate_rocket_fuel",
                "gtceu:rocket_engine/udmh_rocket_fuel",
                "gtceu:rocket_engine/dense_hydrazine_mixed_fuel",
                "gtceu:gas_turbine/coal_gas",
                "gtceu:gas_turbine/wood_gas",
                "gtceu:combustion_generator/naphtha",
                "gtceu:combustion_generator/diesel",
                "gtceu:combustion_generator/light_fuel",
                "gtceu:shaped/filter_casing_sterile",
                "gtceu:shaped/maintenance_hatch_cleaning",
                "gtceu:chemical_reactor/calcite_from_quicklime",
                "gtceu:combustion_generator/biodiesel",
                "gtceu:combustion_generator/cetane_diesel",
                "gtceu:gas_turbine/benzene",
                "gtceu:gas_turbine/nitrobenzene",
                "gtceu:pyrolyse_oven/bio_chaff_to_fermented_biomass",
                "gtceu:pyrolyse_oven/bio_chaff_to_biomass",
                "gtceu:fermenter/fermented_biomass",
                "gtceu:chemical_reactor/iodine_solution",
                "gtceu:large_chemical_reactor/iodine_solution",
                "gtceu:assembler/cover_ender_fluid_link",
                "gtceu:assembler/space_helmet",
                "gtceu:shaped/space_suit",
                "gtceu:shaped/space_pants",
                "gtceu:shaped/space_boots",
                "gtceu:electric_blast_furnace/rutile_from_ilmenite",
                "gtceu:electrolyzer/decomposition_electrolyzing_green_sapphire",
                "gtceu:electrolyzer/decomposition_electrolyzing_sapphire",
                "gtceu:electrolyzer/decomposition_electrolyzing_ruby",
                "gtceu:electrolyzer/decomposition_electrolyzing_pyrope",
                "gtceu:electrolyzer/decomposition_electrolyzing_granite_red",
                "gtceu:electrolyzer/decomposition_electrolyzing_potassium_feldspar",
                "gtceu:electrolyzer/decomposition_electrolyzing_pollucite",
                "gtceu:electrolyzer/decomposition_electrolyzing_kyanite",
                "gtceu:electrolyzer/bauxite_electrolysis",
                "gtceu:electrolyzer/decomposition_electrolyzing_topaz",
                "gtceu:electrolyzer/decomposition_electrolyzing_spodumene",
                "gtceu:electrolyzer/decomposition_electrolyzing_spessartine",
                "gtceu:electrolyzer/decomposition_electrolyzing_sodalite",
                "gtceu:electrolyzer/decomposition_electrolyzing_mica",
                "gtceu:electrolyzer/decomposition_electrolyzing_lepidolite",
                "gtceu:electrolyzer/decomposition_electrolyzing_lazurite",
                "gtceu:electrolyzer/decomposition_electrolyzing_grossular",
                "gtceu:electrolyzer/decomposition_electrolyzing_glauconite_sand",
                "gtceu:electrolyzer/decomposition_electrolyzing_emerald",
                "gtceu:electrolyzer/decomposition_electrolyzing_blue_topaz",
                "gtceu:electrolyzer/decomposition_electrolyzing_biotite",
                "gtceu:electrolyzer/decomposition_electrolyzing_alunite",
                "gtceu:electrolyzer/decomposition_electrolyzing_almandine",
                "gtceu:electrolyzer/decomposition_electrolyzing_chromite",
                "gtceu:large_chemical_reactor/platinum_group_sludge_tiny_dust1",
                "gtceu:large_chemical_reactor/pgs_from_pentlandite",
                "gtceu:large_chemical_reactor/platinum_group_sludge_dust1_lv",
                "gtceu:large_chemical_reactor/pgs_from_chalcopyrite",
                "gtceu:large_chemical_reactor/pgs_from_chalcocite",
                "gtceu:large_chemical_reactor/pgs_from_tetrahedrite",
                "gtceu:large_chemical_reactor/pgs_from_bornite",
                "gtceu:chemical_reactor/platinum_group_sludge_tiny_dust1",
                "gtceu:chemical_reactor/pgs_from_pentlandite",
                "gtceu:chemical_reactor/platinum_group_sludge_dust1_lv",
                "gtceu:chemical_reactor/pgs_from_chalcopyrite",
                "gtceu:chemical_reactor/pgs_from_chalcocite",
                "gtceu:chemical_reactor/pgs_from_tetrahedrite",
                "gtceu:chemical_reactor/pgs_from_bornite",
                "gtceu:electrolyzer/decomposition_electrolyzing_andradite",
                "gtceu:electrolyzer/decomposition_electrolyzing_ferrosilite",
                "gtceu:electrolyzer/decomposition_electrolyzing_wollastonite",
                "gtceu:electrolyzer/decomposition_electrolyzing_obsidian",
                "gtceu:electrolyzer/decomposition_electrolyzing_talc",
                "gtceu:electrolyzer/decomposition_electrolyzing_soapstone",
                "gtceu:electrolyzer/bentonite_electrolysis",
                "gtceu:electrolyzer/decomposition_electrolyzing_asbestos",
                "gtceu:electrolyzer/decomposition_electrolyzing_uvarovite",
                "gtceu:electrolyzer/decomposition_electrolyzing_fullers_earth",
                "gtceu:electrolyzer/decomposition_electrolyzing_silicon_dioxide",
                "gtceu:electrolyzer/decomposition_electrolyzing_silicon_fluoride",
                "gtceu:vacuum_freezer/liquid_oxygen",
                "gtceu:shapeless/dust_brass",
                "gtceu:shapeless/dust_bronze",
                "gtceu:shapeless/potin_dust",
                "gtceu:shaped/vacuum_tube",
                "gtceu:shaped/steam_turbine_lv",
                "gtceu:shapeless/iron_magnetic_stick",
                "gtceu:shaped/steam_turbine_mv",
                "gtceu:shaped/steam_turbine_hv",
                "gtceu:combustion_generator/raw_oil",
                "gtceu:assembler/oak_stairs",
                "gtceu:assembler/spruce_stairs",
                "gtceu:assembler/birch_stairs",
                "gtceu:assembler/jungle_stairs",
                "gtceu:assembler/acacia_stairs",
                "gtceu:assembler/dark_oak_stairs",
                "gtceu:assembler/mangrove_stairs",
                "gtceu:assembler/cherry_stairs",
                "gtceu:assembler/bamboo_stairs",
                "gtceu:assembler/crimson_stairs",
                "gtceu:assembler/warped_stairs",
                "gtceu:extruder/nan_certificate",
                "gtceu:electrolyzer/decomposition_electrolyzing_clay",
                "gtceu:electrolyzer/decomposition_electrolyzing_wolframite",
                "gtceu:electrolyzer/decomposition_electrolyzing_tarkianite",
                "gtceu:electrolyzer/decomposition_electrolyzing_rheniite",
                "gtceu:electrolyzer/decomposition_electrolyzing_palladium_sulfide",
                "gtceu:electrolyzer/decomposition_electrolyzing_ruthenium_amalgam",
                "gtceu:electrolyzer/decomposition_electrolyzing_osmium_iron_spinel",
                "gtceu:smashing_factory_recipes/smashing_factory_recipes/macerate_rail",
                "gtceu:smashing_factory_recipes/smashing_factory_recipes/macerate_powered_rail",
                "gtceu:smashing_factory_recipes/smashing_factory_recipes/macerate_activator_rail",
                "gtceu:smashing_factory_recipes/smashing_factory_recipes/macerate_detector_rail",
                "gtceu:electric_blast_furnace/blast_high_temp_wrought_precursor",
                "gtceu:electric_blast_furnace/blast_high_temp_wrought_precursor_gas",
                "gtceu:vacuum_freezer/cool_hot_high_temp_wrought_precursor_ingot",
                "gtceu:shaped/bronze_primitive_blast_furnace",
                "gtceu:smelting/wrought_iron_nugget",
                "gtceu:shapeless/block_decompress_ender_eye",
                "gtceu:forge_hammer/hammer_ender_eye_block_to_gem",
                "gtceu:shapeless/pumpkin_pie_from_dough",
                "gtceu:assembler/assembly_line_casing",
                "gtceu:assembler/assembly_control_casing",
                "gtceu:electric_blast_furnace/naq_ingot",
                "gtceu:shapeless/compressed_clay",
                "gtceu:shapeless/fireclay_dust",
                "gtceu:shaped/casing_coke_bricks",
                "gtceu:shaped/casing_primitive_bricks",
                "gtceu:shaped_fluid_container/casing_primitive_bricks"));

        // gtceu_remove.js: 原本按 output/tag/正则批量删除的几类 GTCEu crafting 配方，这里统一展开成精确 id。
        for (Material material : GTCEuAPI.materialManager.getRegisteredMaterials()) {
            String name = material.getName();
            removePaths.add("gtceu:shaped/plate_" + name);
            removePaths.add("gtceu:shaped/foil_" + name);
            removePaths.add("gtceu:shaped/spring_" + name);
            removePaths.add("gtceu:shaped/spring_small_" + name);
            removePaths.add("gtceu:shapeless/gem_to_gem_gem_" + name);
            removePaths.add("gtceu:shapeless/gem_to_gem_chipped_gem_" + name);
            removePaths.add("gtceu:shapeless/gem_to_gem_flawed_gem_" + name);
            removePaths.add("gtceu:shapeless/gem_to_gem_flawless_gem_" + name);
        }

        // gtceu_remove.js: /gtceu:high_temp_wrought_precursor_(.*)/ + small/tiny dust 输出删除
        removePaths.addAll(List.of(
                "gtceu:shaped/small_dust_assembling_high_temp_wrought_precursor",
                "gtceu:shaped/tiny_dust_assembling_high_temp_wrought_precursor",
                "gtceu:shapeless/nugget_disassembling_high_temp_wrought_precursor",
                "gtceu:shaped/nugget_assembling_high_temp_wrought_precursor",
                "gtceu:shaped/block_compress_high_temp_wrought_precursor",
                "gtceu:shapeless/block_decompress_high_temp_wrought_precursor",
                "gtceu:alloy_smelter/alloy_smelt_high_temp_wrought_precursor_to_nugget",
                "gtceu:alloy_smelter/alloy_smelt_high_temp_wrought_precursor_nugget_to_ingot",
                "gtceu:compressor/compress_high_temp_wrought_precursor_nugget_to_ingot",
                "gtceu:alloy_smelter/alloy_smelt_high_temp_wrought_precursor_to_ingot",
                "gtceu:compressor/compress_high_temp_wrought_precursor_to_block",
                "gtceu:alloy_smelter/alloy_smelt_high_temp_wrought_precursor_ingot_to_block",
                "gtceu:shaped/small_dust_disassembling_high_temp_wrought_precursor",
                "gtceu:shaped/small_dust_disassembling_3x3_high_temp_wrought_precursor",
                "gtceu:packer/unpackage_high_temp_wrought_precursor_small_dust",
                "gtceu:shaped/tiny_dust_disassembling_high_temp_wrought_precursor",
                "gtceu:shaped/tiny_dust_disassembling_3x3_high_temp_wrought_precursor",
                "gtceu:packer/unpackage_high_temp_wrought_precursor_tiny_dust"));

        // #forge:exquisite_gems 的 shapeless 删除在 GTCEu 生成配方里没有找到对应输出，当前无需展开精确 id。

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
