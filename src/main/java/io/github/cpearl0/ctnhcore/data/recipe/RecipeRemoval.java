package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.tconstruct.TConstructFluidTagFilter;
import io.github.cpearl0.ctnhcore.data.recipe.create.CreateRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.immersiveaircraft.ImmersiveAircraftRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.modmodify.EIORecipes;
import io.github.cpearl0.ctnhcore.data.recipe.modmodify.omnicells.QuantumOmniRecipes;
import io.github.cpearl0.ctnhcore.data.recipe.tconstruct.TConstructRecipes;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;

/**
 * 配方删除中心。
 *
 * <p>
 * 所有删除统一通过 {@link #remove(RemoveFilter)} 入口，filter 接受与 KubeJS
 * {@code event.remove({...})} 完全一致的字段。
 *
 * <p>
 * 支持的 filter 字段：
 * <ul>
 * <li>{@code id} — 精确ID（String 或 List&lt;String&gt;）</li>
 * <li>{@code idRegex} — ID正则</li>
 * <li>{@code mod} — 模组ID</li>
 * <li>{@code output} — 输出物品ID（支持 tag 形式 {@code #namespace:tag}）</li>
 * <li>{@code outputRegex} — 输出物品ID正则</li>
 * <li>{@code input} — 输入物品ID（String 或 List&lt;String&gt;）</li>
 * <li>{@code type} — 配方类型ID</li>
 * <li>{@code not} — 排除条件（KubeJS 风格的反选 filter）</li>
 * </ul>
 *
 * <p>
 * 所有删除均在 {@code RecipeManager.apply()} 的 HEAD 阶段（与 KubeJS 一致）
 * 通过 {@code RecipeManagerApplyMixin} 介入 JSON 级别的配方数据处理。
 * {@link #init(Consumer)} 入参的 registry 钩子保留为空操作以兼容 GTAddon 接口。
 */
@Mod.EventBusSubscriber(modid = CTNHCore.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RecipeRemoval {

    // 统一的过滤器列表（所有删除规则都通过 remove(filter) 存入此处）
    private static final List<RemoveFilter> FILTERS = new ArrayList<>();

    // replaceInput / replaceOutput 的操作列表
    // 每个操作包含：filter（匹配哪些配方）、from（被替换的物品/tag）、to（替换成什么）
    private static final List<ReplaceOperation> REPLACE_OPERATIONS = new ArrayList<>();

    // ========== Mixin 需要的公开访问器 ==========

    public static List<RemoveFilter> getFilters() {
        return FILTERS;
    }

    public static List<ReplaceOperation> getReplaceOperations() {
        return REPLACE_OPERATIONS;
    }

    // ========== 唯一公开的删除/替换入口 ==========

    /**
     * 与 KubeJS {@code event.remove({...})} 一致的删除入口。
     * 多个 filter 字段以 AND 关系组合。
     */
    public static void remove(RemoveFilter filter) {
        if (filter != null) {
            FILTERS.add(filter);
        }
    }

    // ========== replaceInput / replaceOutput 入口 ==========

    /**
     * 与 KubeJS {@code event.replaceInput({...}, from, to)} 完全一致的替换入口。
     * from/to 格式：
     * - "#forge:ingots/tin" 表示 tag
     * - "minecraft:iron_ingot" 表示具体物品
     * - "#forge:iron" 表示流体 tag
     * - "gtceu:iron" 表示流体 id
     * 内部统一递归处理，不区分输入/输出/物品/流体。
     */
    public static void replaceInput(RemoveFilter filter, String from, String to) {
        if (filter != null && from != null && to != null) {
            REPLACE_OPERATIONS.add(new ReplaceOperation(filter, from, to, ReplaceOperation.Type.INPUT));
        }
    }

    /**
     * 与 KubeJS {@code event.replaceOutput({...}, from, to)} 完全一致的替换入口。
     */
    public static void replaceOutput(RemoveFilter filter, String from, String to) {
        if (filter != null && from != null && to != null) {
            REPLACE_OPERATIONS.add(new ReplaceOperation(filter, from, to, ReplaceOperation.Type.OUTPUT));
        }
    }

    // ========== 主入口 ==========

    /**
     * 初始化所有删除规则。所有删除通过 {@link #remove(RemoveFilter)} 注册，
     * 最终在 {@code RecipeManagerApplyMixin} 中统一处理。
     */
    public static void init(Consumer<ResourceLocation> registry) {
        // ===== 外部类删除（与 RecipeRemoval 同模块/相关模块） =====
        EIORecipes.eioRemovals();
        QuantumOmniRecipes.omniRemovals();
        CreateRecipes.createRemovals();
        ImmersiveAircraftRecipes.immersiveAircraftRemovals();
        SophisticatedStorageRecipes.sophisticatedStorageRemovals();
        TConstructRecipes.tconstructRemovals();

        // ===== 本类删除（按模组/功能分组） =====
        adAstraRemovals();
        adExtendraRemovals();
        ae2Removals();
        ae2additionsRemovals();
        ae2csRemovals();
        ae2thingsRemovals();
        aetherRemovals();
        alexscavesRemovals();
        angelblockrenewedRemovals();
        apotheosisRemovals();
        arsNouveauRemovals();
        avaritiaRemovals();
        biofactoryRemovals();
        biomancyRemovals();
        biomesoplentyRemovals();
        bloodmagicRemovals();
        botaniaRemovals();
        createNewAgeRemovals();
        createdieselgeneratorsRemovals();
        createFallenRemovals();
        createmetallurgyRemovals();
        createoreexcavationRemovals();
        ctnhcoreRemovals();
        deepAetherRemovals();
        delightRemovals();
        expatternproviderRemovals();
        extrabotanyRemovals();
        farmersdelightRemovals();
        functionalstorageRemovals();
        gtceuRemovals();
        hostilenetworksRemovals();
        javdRemovals();
        legendarysurvivaloverhaulRemovals();
        mae2Removals();
        merequesterRemovals();
        minecraftRemovals();
        miscRemovals();
        mynethersdelightRemovals();
        mythicbotanyRemovals();
        pccardRemovals();
        sophisticatedbackpacksRemovals();
        tetranichematerialsRemovals();
        tfmgRemovals();
        thermalRemovals();
        twilightforestRemovals();
        oreReplacementRemovals();
        siliconChainRemovals();
        ae2ReplaceInputRemovals();
        functionalStorageReplaceRemovals();
        createdieselgeneratorsReplaceRemovals();
        vintageimprovementsRemovals();
        woodRemovals();
    }

    // ========== 按模组/功能分组的删除方法 ==========

    public static void adAstraRemovals() {
        remove(new RemoveFilter().idRegex("ad_astra:(.*)"));
        remove(new RemoveFilter().output("ad_astra:desh_fluid_pipe"));
        remove(new RemoveFilter().output("ad_astra:ostrum_fluid_pipe"));
        remove(new RemoveFilter().output("ad_astra:fluid_pipe_duct"));
    }

    public static void adExtendraRemovals() {
        remove(new RemoveFilter().mod("ad_extendra").output("#forge:ingots").type("minecraft:smelting"));
        remove(new RemoveFilter().mod("ad_extendra").output("#forge:gems").type("minecraft:smelting"));
    }

    public static void ae2Removals() {
        remove(new RemoveFilter().output("ae2:cell_component_1k"));
        remove(new RemoveFilter().output("ae2:cell_component_4k"));
        remove(new RemoveFilter().output("ae2:cell_component_16k"));
        remove(new RemoveFilter().output("ae2:cell_component_64k"));
        remove(new RemoveFilter().output("ae2:cell_component_256k"));
        remove(new RemoveFilter().output("ae2:logic_processor"));
        remove(new RemoveFilter().output("ae2:calculation_processor"));
        remove(new RemoveFilter().output("ae2:engineering_processor"));
        remove(new RemoveFilter().id("ae2:network/cables/glass_fluix"));
        remove(new RemoveFilter().id("ae2:network/cables/covered_fluix"));
        remove(new RemoveFilter().id("ae2:network/blocks/pattern_providers_interface"));
        remove(new RemoveFilter().id("ae2:network/blocks/interfaces_interface"));
        remove(new RemoveFilter().id("ae2:transform/certus_quartz_crystals"));
        remove(new RemoveFilter().type("ae2:inscriber"));
        remove(new RemoveFilter().type("expatternprovider:circuit_cutter"));
        remove(new RemoveFilter().output("ae2:energy_acceptor"));
        remove(new RemoveFilter().output("ae2:charger"));
        remove(new RemoveFilter().output("ae2:inscriber"));
        remove(new RemoveFilter().output("ae2:quartz_fiber"));
        remove(new RemoveFilter().output("ae2:storage_bus"));
        remove(new RemoveFilter().output("ae2:blank_pattern"));
        remove(new RemoveFilter().output("ae2:pattern_encoding_terminal"));
        remove(new RemoveFilter().output("ae2:molecular_assembler"));
        remove(new RemoveFilter().output("ae2:basic_card"));
        remove(new RemoveFilter().output("ae2:advanced_card"));
        remove(new RemoveFilter().output("ae2:printed_silicon"));
        remove(new RemoveFilter().output("ae2:ender_dust"));
        remove(new RemoveFilter().output("ae2:certus_quartz_dust"));
        remove(new RemoveFilter().output("ae2:crafting_unit"));
        remove(new RemoveFilter().output("ae2:item_cell_housing"));
        remove(new RemoveFilter().output("ae2:fluid_cell_housing"));
        remove(new RemoveFilter().output("ae2:wireless_booster"));
        remove(new RemoveFilter().output("ae2:quartz_glass"));
        remove(new RemoveFilter().id("ae2:network/cells/item_storage_cell_1k"));
        remove(new RemoveFilter().id("ae2:network/cells/item_storage_cell_4k"));
        remove(new RemoveFilter().id("ae2:network/cells/item_storage_cell_16k"));
        remove(new RemoveFilter().id("ae2:network/cells/item_storage_cell_64k"));
        remove(new RemoveFilter().id("ae2:network/cells/item_storage_cell_256k"));
        remove(new RemoveFilter().id("ae2:network/cells/fluid_storage_cell_1k"));
        remove(new RemoveFilter().id("ae2:network/cells/fluid_storage_cell_4k"));
        remove(new RemoveFilter().id("ae2:network/cells/fluid_storage_cell_16k"));
        remove(new RemoveFilter().id("ae2:network/cells/fluid_storage_cell_64k"));
        remove(new RemoveFilter().id("ae2:network/cells/fluid_storage_cell_256k"));
    }

    public static void ae2additionsRemovals() {
        remove(new RemoveFilter().output("ae2additions:disk_item_256k"));
        remove(new RemoveFilter().output("ae2additions:disk_fluid_housing"));
        remove(new RemoveFilter().output("ae2additions:super_cell_housing"));
    }

    public static void ae2csRemovals() {
        remove(new RemoveFilter().idRegex("ae2cs:(.*)"));
    }

    public static void ae2thingsRemovals() {
        remove(new RemoveFilter().output("ae2things:disk_housing"));
        remove(new RemoveFilter().id("ae2things:cells/disk_drive_1k"));
    }

    public static void aetherRemovals() {
        remove(new RemoveFilter().id("aether:skyroot_crafting_table"));
        remove(new RemoveFilter().id("aether:skyroot_chest"));
        remove(new RemoveFilter().id("aether:aether_saddle"));
        remove(new RemoveFilter().id("aether:moa_egg_pumpkin_pie"));
        remove(new RemoveFilter().idRegex("aether:skyroot_(.*)").mod("aether").type("minecraft:crafting_shaped"));
        remove(new RemoveFilter().output("#forge:plates").type("minecraft:crafting_shapeless"));
    }

    public static void alexscavesRemovals() {
        remove(new RemoveFilter().id("alexscaves:azure_neodymium_ingot"));
        remove(new RemoveFilter().id("alexscaves:scarlet_neodymium_ingot"));
    }

    public static void angelblockrenewedRemovals() {
        remove(new RemoveFilter().output("angelblockrenewed:angel_block"));
    }

    public static void apotheosisRemovals() {
        remove(new RemoveFilter().id("apotheosis:spawner/ignore_light"));
        remove(new RemoveFilter().id("apotheosis:spawner/ignore_light_inverted"));
        remove(new RemoveFilter().id("apotheosis:spawner/spawn_count"));
        remove(new RemoveFilter().id("apotheosis:spawner/spawn_count_inverted"));
        remove(new RemoveFilter().id("apotheosis:spawner/max_nearby"));
        remove(new RemoveFilter().id("apotheosis:spawner/max_nearby_inverted"));
        remove(new RemoveFilter().id("apotheosis:spawner/baby"));
        remove(new RemoveFilter().id("apotheosis:spawner/baby_inverted"));
        remove(new RemoveFilter().id("apotheosis:spawner/redstone_control"));
        remove(new RemoveFilter().id("apotheosis:spawner/redstone_control_inverted"));
        remove(new RemoveFilter().id("apotheosis:spawner/no_ai"));
        remove(new RemoveFilter().id("apotheosis:spawner/no_ai_inverted"));
        remove(new RemoveFilter().id("apotheosis:spawner/min_delay"));
        remove(new RemoveFilter().id("apotheosis:spawner/min_delay_inverted"));
        remove(new RemoveFilter().id("apotheosis:spawner/max_delay"));
        remove(new RemoveFilter().id("apotheosis:spawner/max_delay_inverted"));
        remove(new RemoveFilter().id("apotheosis:spawner/ignore_conditions"));
        remove(new RemoveFilter().id("apotheosis:spawner/ignore_conditions_inverted"));
        remove(new RemoveFilter().id("apotheosis:spawner/player_range"));
        remove(new RemoveFilter().id("apotheosis:spawner/player_range_inverted"));
        remove(new RemoveFilter().id("apotheosis:spawner/ignore_players"));
        remove(new RemoveFilter().id("apotheosis:spawner/ignore_players_inverted"));
        remove(new RemoveFilter().id("apotheosis:spawner/spawn_range"));
        remove(new RemoveFilter().id("apotheosis:spawner/spawn_range_inverted"));
    }

    public static void arsNouveauRemovals() {
        remove(new RemoveFilter().id("ars_nouveau:novice_spell_book"));
    }

    public static void avaritiaRemovals() {
        remove(new RemoveFilter().output("minecraft:end_portal_frame"));
        remove(new RemoveFilter().output("avaritia:star_fuel"));
        remove(new RemoveFilter().id("avaritia:botania_mana_tablet"));
        remove(new RemoveFilter().id("avaritia:infinity_ingot"));
        remove(new RemoveFilter().id("avaritia:infinity_catalyst_eternal"));
        remove(new RemoveFilter().id("avaritia:infinity_catalyst"));
        remove(new RemoveFilter().idRegex("avaritia:(.*)neutron_collector"));
        remove(new RemoveFilter().idRegex("avaritia:(.*)neutron_compressor"));
        remove(new RemoveFilter().id("avaritia:extreme_crafting_table"));
    }

    public static void biofactoryRemovals() {
        remove(new RemoveFilter().id("biofactory:compacting/flesh_block_from_flesh_bits"));
    }

    public static void biomancyRemovals() {
        remove(new RemoveFilter().type("biomancy:bio_brewing"));
        remove(new RemoveFilter().type("biomancy:bio_forging"));
        remove(new RemoveFilter().type("biomancy:digesting"));
    }

    public static void biomesoplentyRemovals() {
        remove(new RemoveFilter().id("biomesoplenty:tnt_from_bop_sand"));
    }

    public static void bloodmagicRemovals() {
        remove(new RemoveFilter().id("bloodmagic:smelting/ingot_copper"));
        remove(new RemoveFilter().id("bloodmagic:smelting/blasting_ingot_copper"));
        remove(new RemoveFilter().id("bloodmagic:smelting/ingot_iron"));
        remove(new RemoveFilter().id("bloodmagic:smelting/ingot_gold"));
        remove(new RemoveFilter().id("bloodmagic:smelting/blasting_ingot_gold"));
        remove(new RemoveFilter().mod("bloodmagic").type("bloodmagic:altar"));
        remove(new RemoveFilter().mod("bloodmagic").type("bloodmagic:soulforge"));
    }

    public static void botaniaRemovals() {
        remove(new RemoveFilter().output("botania:creative_pool"));
        remove(new RemoveFilter().id("botania:mana_fluxfield"));
        remove(new RemoveFilter().output("botania:lens_normal"));
        remove(new RemoveFilter().output("botania:lens_magnet"));
        remove(new RemoveFilter().outputRegex("botania:apothecary_(.*)"));
        remove(new RemoveFilter().mod("botania").type("botania:petal_apothecary"));
        remove(new RemoveFilter().mod("botania").type("botania:runic_altar"));
        remove(new RemoveFilter().mod("botania").type("botania:terra_plate"));
    }

    public static void createNewAgeRemovals() {
        remove(new RemoveFilter().id("create_new_age:shaped/redstone_magnet"));
        remove(new RemoveFilter().id("create_new_age:shapeless/basic_energiser"));
        remove(new RemoveFilter().id("create_new_age:pressing/overcharged_gold"));
        remove(new RemoveFilter().id("create_new_age:pressing/overcharged_iron"));
        remove(new RemoveFilter().id("create_new_age:shaped/layered_magnet"));
        remove(new RemoveFilter().id("create_new_age:shaped/netherite_magnet"));
        remove(new RemoveFilter().id("create_new_age:shaped/fluxuated_magnetite"));
        remove(new RemoveFilter().output("create_new_age:carbon_brushes"));
        remove(new RemoveFilter().output("create_new_age:generator_coil"));
    }

    public static void createdieselgeneratorsRemovals() {
        remove(new RemoveFilter().id("createdieselgenerators:basin_fermenting/fermented_spider_eye"));
        remove(new RemoveFilter().id("createdieselgenerators:compression_molding/bucket"));
        remove(new RemoveFilter().output("createdieselgenerators:wood_chip"));
        remove(new RemoveFilter().id("createdieselgenerators:distillation/acid"));
        remove(new RemoveFilter().id("createdieselgenerators:distillation/superheated_crude_oil"));
        remove(new RemoveFilter().id("createdieselgenerators:bulk_fermenting/fermentable"));
        remove(new RemoveFilter().id("createdieselgenerators:crafting/engine_piston_from_rods"));
        remove(new RemoveFilter().id("createdieselgenerators:mixing/asphalt_block"));
        remove(new RemoveFilter().id("createdieselgenerators:crafting/asphalt_block"));
        remove(new RemoveFilter().id("createdieselgenerators:mixing/biodiesel"));
    }

    public static void createmetallurgyRemovals() {
        // createmetallurgy 模组下的所有配方，例外：createmetallurgy:belt_grinder
        remove(new RemoveFilter().mod("createmetallurgy")
                .not(new RemoveFilter().type("createmetallurgy:belt_grinder")));
    }

    public static void createoreexcavationRemovals() {
        remove(new RemoveFilter().idRegex("createoreexcavation:ore_vein_type(.*)"));
        remove(new RemoveFilter().idRegex("createoreexcavation:drilling\\/(.*)"));
        remove(new RemoveFilter().id("createoreexcavation:vein_finder"));
    }

    public static void ctnhcoreRemovals() {
        remove(new RemoveFilter().id("ctnhcore:assembler/cover_ender_fluid_link"));
    }

    public static void deepAetherRemovals() {
        remove(new RemoveFilter().id("deep_aether:skyroot_crafting_table"));
        remove(new RemoveFilter().id("deep_aether:pumpkin_pie"));
    }

    public static void delightRemovals() {
        remove(new RemoveFilter().outputRegex("(.*)delight:(.*)_knife"));
    }

    public static void expatternproviderRemovals() {
        remove(new RemoveFilter().id("expatternprovider:ei"));
        remove(new RemoveFilter().id("expatternprovider:epp"));
        remove(new RemoveFilter().output("expatternprovider:interface_upgrade"));
        remove(new RemoveFilter().output("expatternprovider:pattern_provider_upgrade"));
        remove(new RemoveFilter().output("expatternprovider:ex_export_bus_part"));
        remove(new RemoveFilter().output("expatternprovider:ex_import_bus_part"));
        remove(new RemoveFilter().output("expatternprovider:io_bus_upgrade"));
        remove(new RemoveFilter().output("expatternprovider:ex_pattern_access_part"));
        remove(new RemoveFilter().output("expatternprovider:pattern_terminal_upgrade"));
        remove(new RemoveFilter().output("expatternprovider:ex_drive"));
        remove(new RemoveFilter().output("expatternprovider:drive_upgrade"));
        remove(new RemoveFilter().output("expatternprovider:tag_storage_bus"));
        remove(new RemoveFilter().output("expatternprovider:tag_export_bus"));
        remove(new RemoveFilter().output("expatternprovider:ex_molecular_assembler"));
        remove(new RemoveFilter().output("expatternprovider:ingredient_buffer"));
        remove(new RemoveFilter().id("expatternprovider:ex_inscriber"));
        remove(new RemoveFilter().id("expatternprovider:ex_charger"));
        remove(new RemoveFilter().id("expatternprovider:circuit_cutter"));
    }

    public static void extrabotanyRemovals() {
        remove(new RemoveFilter().id("extrabotany:terra_plate/the_universe"));
        remove(new RemoveFilter().mod("extrabotany").type("botania:petal_apothecary"));
        remove(new RemoveFilter().mod("extrabotany").type("botania:terra_plate"));
    }

    public static void farmersdelightRemovals() {
        remove(new RemoveFilter().id("farmersdelight:paper_from_tree_bark"));
    }

    public static void functionalstorageRemovals() {
        remove(new RemoveFilter().id("functionalstorage:oak_drawer_alternate_x1"));
        remove(new RemoveFilter().id("functionalstorage:oak_drawer_alternate_x2"));
        remove(new RemoveFilter().id("functionalstorage:oak_drawer_alternate_x4"));
    }

    public static void gtceuRemovals() {
        remove(new RemoveFilter().id("gtceu:assembler/plate_radiation_2"));
        remove(new RemoveFilter().id("gtceu:assembler/plate_radiation"));
        remove(new RemoveFilter().id("gtceu:electric_blast_furnace/iro2"));
        remove(new RemoveFilter().id("gtceu:shaped/casing_assembly_control"));
        remove(new RemoveFilter().id("gtceu:shaped/casing_assembly_line"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_ammonium_chloride"));
        remove(new RemoveFilter().id("gtceu:assembler/mar_casing"));
        remove(new RemoveFilter().id("gtceu:arc_furnace/arc_cleaning_maintenance_hatch"));
        remove(new RemoveFilter()
                .id("gtceu:smashing_factory_recipes/smashing_factory_recipes/macerate_cleaning_maintenance_hatch"));
        remove(new RemoveFilter().id("gtceu:fusion_reactor/americium_and_naquadria_to_neutronium_plasma"));
        remove(new RemoveFilter().output("gtceu:compressed_clay"));
        remove(new RemoveFilter().output("gtceu:coke_oven_bricks"));
        remove(new RemoveFilter().output("gtceu:fireclay_dust").input("gtceu:clay_dust"));
        remove(new RemoveFilter().output("gtceu:firebricks"));
        remove(new RemoveFilter().outputRegex("gtceu:(.*)_gem").inputRegex("gtceu:flawless_(.*)_gem")
                .type("minecraft:crafting_shaped"));
        remove(new RemoveFilter().output("#forge:plates").type("minecraft:crafting_shaped"));
        remove(new RemoveFilter().output("#forge:chipped_gems").type("minecraft:crafting_shapeless"));
        remove(new RemoveFilter().output("#forge:flawed_gems").type("minecraft:crafting_shapeless"));
        remove(new RemoveFilter().output("#forge:flawless_gems").type("minecraft:crafting_shapeless"));
        remove(new RemoveFilter().output("#forge:exquisite_gems").type("minecraft:crafting_shapeless"));
        remove(new RemoveFilter().id("gtceu:large_chemical_reactor/raw_palladium_separation"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_niobium_oxide"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_tantalite_oxide"));
        remove(new RemoveFilter().id("gtceu:shaped/casing_hsse_sturdy"));
        remove(new RemoveFilter().id("gtceu:assembler/casing_hsse_sturdy"));
        remove(new RemoveFilter().id("gtceu:shaped/diamond_sword"));
        remove(new RemoveFilter().id("gtceu:electric_blast_furnace/titanium_from_tetrachloride"));
        remove(new RemoveFilter().id("gtceu:chemical_reactor/titaniumtetrachloride"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/tungstic_acid_electrolysis"));
        remove(new RemoveFilter().id("gtceu:neutron_activator/naquadah"));
        remove(new RemoveFilter().id("gtceu:large_chemical_reactor/iridium_chloride"));
        remove(new RemoveFilter().id("gtceu:large_chemical_reactor/iridium_dioxide_dissolving"));
        remove(new RemoveFilter().id("gtceu:electric_blast_furnace/iridium_metal_residue_processh"));
        remove(new RemoveFilter().id("gtceu:chemical_reactor/iridium_chloride"));
        remove(new RemoveFilter().id("gtceu:large_chemical_reactor/iridium_chloride_separation"));
        remove(new RemoveFilter().id("gtceu:large_chemical_reactor/raw_platinum_separation"));
        remove(new RemoveFilter().id("gtceu:chemical_reactor/raw_platinum_separation"));
        remove(new RemoveFilter().id("gtceu:electric_blast_furnace/refined_platinum_salt_dust_ebf"));
        remove(new RemoveFilter().id("gtceu:electric_blast_furnace/iridium_metal_residue_process"));
        remove(new RemoveFilter().id("gtceu:smelting/smelt_dust_bedrock_dust_to_ingot"));
        remove(new RemoveFilter().id("gtceu:arc_furnace/arc_bedrock_dust_dust"));
        remove(new RemoveFilter().id("gtceu:chemical_reactor/indium_concentrate_separation"));
        remove(new RemoveFilter().id("gtceu:chemical_reactor/indium_concentrate_separation_4x"));
        remove(new RemoveFilter().id("gtceu:large_chemical_reactor/indium_concentrate_separation_4x"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_aluminium_sulfite"));
        remove(new RemoveFilter().id("gtceu:large_chemical_reactor/phosphoric_acid_from_pentoxide"));
        remove(new RemoveFilter().id("gtceu:shaped/large_bronze_boiler"));
        remove(new RemoveFilter().id("gtceu:chemical_reactor/soda_ash_from_carbon_dioxide"));
        remove(new RemoveFilter().id("gtceu:electric_blast_furnace/blast_adamantite"));
        remove(new RemoveFilter().id("gtceu:fluid_solidifier/solidify_adamantite_to_plate"));
        remove(new RemoveFilter().id("gtceu:fluid_solidifier/solidify_adamantite_gear"));
        remove(new RemoveFilter().id("gtceu:fluid_solidifier/solidify_adamantite_block"));
        remove(new RemoveFilter().id("gtceu:fluid_solidifier/solidify_adamantite_to_ingot"));
        remove(new RemoveFilter().id("gtceu:fluid_solidifier/solidify_adamantite_small_gear"));
        remove(new RemoveFilter().id("gtceu:fluid_solidifier/solidify_adamantite_to_nugget"));
        remove(new RemoveFilter().id("gtceu:shaped/plate_double_graphite_ir_plate"));
        remove(new RemoveFilter().id("gtceu:chemical_reactor/iridium_dioxide_dissolving"));
        remove(new RemoveFilter().id("gtceu:chemical_reactor/iridium_chloride_separation"));
        remove(new RemoveFilter().id("gtceu:dehydrator/xenoauric_fluoroantimonic_acid"));
        remove(new RemoveFilter().id("gtceu:assembly_line/energy_hatch_uhv"));
        remove(new RemoveFilter().id("gtceu:rocket_engine/rp_1_mixed_fuel"));
        remove(new RemoveFilter().id("gtceu:rocket_engine/methylhydrazine_nitrate_rocket_fuel"));
        remove(new RemoveFilter().id("gtceu:rocket_engine/udmh_rocket_fuel"));
        remove(new RemoveFilter().id("gtceu:rocket_engine/dense_hydrazine_mixed_fuel"));
        remove(new RemoveFilter().id("gtceu:gas_turbine/coal_gas"));
        remove(new RemoveFilter().id("gtceu:gas_turbine/wood_gas"));
        remove(new RemoveFilter().id("gtceu:combustion_generator/naphtha"));
        remove(new RemoveFilter().id("gtceu:combustion_generator/diesel"));
        remove(new RemoveFilter().id("gtceu:combustion_generator/light_fuel"));
        remove(new RemoveFilter().id("gtceu:shaped/filter_casing_sterile"));
        remove(new RemoveFilter().id("gtceu:shaped/maintenance_hatch_cleaning"));
        remove(new RemoveFilter().id("gtceu:chemical_reactor/calcite_from_quicklime"));
        remove(new RemoveFilter().id("gtceu:extractor/extract_osmium_tetroxide_dust"));
        remove(new RemoveFilter().id("gtceu:combustion_generator/biodiesel"));
        remove(new RemoveFilter().id("gtceu:combustion_generator/cetane_diesel"));
        remove(new RemoveFilter().id("gtceu:gas_turbine/benzene"));
        remove(new RemoveFilter().id("gtceu:gas_turbine/nitrobenzene"));
        remove(new RemoveFilter().id("gtceu:large_chemical_reactor/hydrogen_peroxide"));
        remove(new RemoveFilter().output("gtceu:fermented_biomass"));
        remove(new RemoveFilter().id("gtceu:pyrolyse_oven/bio_chaff_to_fermented_biomass"));
        remove(new RemoveFilter().id("gtceu:pyrolyse_oven/bio_chaff_to_biomass"));
        remove(new RemoveFilter().id("gtceu:fermenter/fermented_biomass"));
        remove(new RemoveFilter().id("gtceu:chemical_reactor/iodine_solution"));
        remove(new RemoveFilter().id("gtceu:large_chemical_reactor/iodine_solution"));
        remove(new RemoveFilter().id("gtceu:assembler/cover_ender_fluid_link"));
        remove(new RemoveFilter().id("gtceu:assembler/space_helmet"));
        remove(new RemoveFilter().id("gtceu:shaped/space_suit"));
        remove(new RemoveFilter().id("gtceu:shaped/space_pants"));
        remove(new RemoveFilter().id("gtceu:shaped/space_boots"));
        remove(new RemoveFilter().id("gtceu:electric_blast_furnace/rutile_from_ilmenite"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_green_sapphire"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_sapphire"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_ruby"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_pyrope"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_granite_red"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_potassium_feldspar"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_pollucite"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_kyanite"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/bauxite_electrolysis"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_topaz"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_spodumene"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_spessartine"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_sodalite"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_mica"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_lepidolite"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_lazurite"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_grossular"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_glauconite_sand"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_emerald"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_blue_topaz"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_biotite"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_alunite"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_almandine"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_chromite"));
        remove(new RemoveFilter().id("gtceu:large_chemical_reactor/platinum_group_sludge_tiny_dust1"));
        remove(new RemoveFilter().id("gtceu:large_chemical_reactor/pgs_from_pentlandite"));
        remove(new RemoveFilter().id("gtceu:large_chemical_reactor/platinum_group_sludge_dust1_lv"));
        remove(new RemoveFilter().id("gtceu:large_chemical_reactor/pgs_from_chalcopyrite"));
        remove(new RemoveFilter().id("gtceu:large_chemical_reactor/pgs_from_chalcocite"));
        remove(new RemoveFilter().id("gtceu:large_chemical_reactor/pgs_from_tetrahedrite"));
        remove(new RemoveFilter().id("gtceu:large_chemical_reactor/pgs_from_bornite"));
        remove(new RemoveFilter().id("gtceu:chemical_reactor/platinum_group_sludge_tiny_dust1"));
        remove(new RemoveFilter().id("gtceu:chemical_reactor/pgs_from_pentlandite"));
        remove(new RemoveFilter().id("gtceu:chemical_reactor/platinum_group_sludge_dust1_lv"));
        remove(new RemoveFilter().id("gtceu:chemical_reactor/pgs_from_chalcopyrite"));
        remove(new RemoveFilter().id("gtceu:chemical_reactor/pgs_from_chalcocite"));
        remove(new RemoveFilter().id("gtceu:chemical_reactor/pgs_from_tetrahedrite"));
        remove(new RemoveFilter().id("gtceu:chemical_reactor/pgs_from_bornite"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_andradite"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_ferrosilite"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_wollastonite"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_obsidian"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_talc"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_soapstone"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/bentonite_electrolysis"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_asbestos"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_uvarovite"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_fullers_earth"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_silicon_dioxide"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_silicon_fluoride"));
        remove(new RemoveFilter().id("gtceu:vacuum_freezer/liquid_oxygen"));
        remove(new RemoveFilter().id("gtceu:shapeless/dust_brass"));
        remove(new RemoveFilter().id("gtceu:shapeless/dust_bronze"));
        remove(new RemoveFilter().id("gtceu:shapeless/potin_dust"));
        remove(new RemoveFilter().id("gtceu:shaped/vacuum_tube"));
        remove(new RemoveFilter().id("gtceu:shaped/steam_turbine_lv"));
        remove(new RemoveFilter().id("gtceu:shapeless/iron_magnetic_stick"));
        remove(new RemoveFilter().id("gtceu:shaped/steam_turbine_mv"));
        remove(new RemoveFilter().id("gtceu:shaped/steam_turbine_hv"));
        remove(new RemoveFilter().id("gtceu:combustion_generator/raw_oil"));
        remove(new RemoveFilter().id("gtceu:assembler/oak_stairs"));
        remove(new RemoveFilter().id("gtceu:assembler/spruce_stairs"));
        remove(new RemoveFilter().id("gtceu:assembler/birch_stairs"));
        remove(new RemoveFilter().id("gtceu:assembler/jungle_stairs"));
        remove(new RemoveFilter().id("gtceu:assembler/acacia_stairs"));
        remove(new RemoveFilter().id("gtceu:assembler/dark_oak_stairs"));
        remove(new RemoveFilter().id("gtceu:assembler/mangrove_stairs"));
        remove(new RemoveFilter().id("gtceu:assembler/cherry_stairs"));
        remove(new RemoveFilter().id("gtceu:assembler/bamboo_stairs"));
        remove(new RemoveFilter().id("gtceu:assembler/crimson_stairs"));
        remove(new RemoveFilter().id("gtceu:assembler/warped_stairs"));
        remove(new RemoveFilter().id("gtceu:extractor/extract_ammonium_chloride_dust"));
        remove(new RemoveFilter().id("gtceu:extruder/nan_certificate"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_clay"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_wolframite"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_tarkianite"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_rheniite"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_palladium_sulfide"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_ruthenium_amalgam"));
        remove(new RemoveFilter().id("gtceu:electrolyzer/decomposition_electrolyzing_osmium_iron_spinel"));
        remove(new RemoveFilter().id("gtceu:smashing_factory_recipes/smashing_factory_recipes/macerate_rail"));
        remove(new RemoveFilter().id("gtceu:smashing_factory_recipes/smashing_factory_recipes/macerate_powered_rail"));
        remove(new RemoveFilter()
                .id("gtceu:smashing_factory_recipes/smashing_factory_recipes/macerate_activator_rail"));
        remove(new RemoveFilter().id("gtceu:smashing_factory_recipes/smashing_factory_recipes/macerate_detector_rail"));
        remove(new RemoveFilter().id("gtceu:electric_blast_furnace/blast_high_temp_wrought_precursor"));
        remove(new RemoveFilter().id("gtceu:electric_blast_furnace/blast_high_temp_wrought_precursor_gas"));
        remove(new RemoveFilter().id("gtceu:vacuum_freezer/cool_hot_high_temp_wrought_precursor_ingot"));
        remove(new RemoveFilter().id("gtceu:shaped/bronze_primitive_blast_furnace"));
        remove(new RemoveFilter().id("gtceu:smelting/wrought_iron_nugget"));
        remove(new RemoveFilter().idRegex("gtceu:shaped\\/foil_(.*)"));
        remove(new RemoveFilter().idRegex("gtceu:shaped\\/spring_(.*)"));
        remove(new RemoveFilter().id("gtceu:shapeless/block_decompress_ender_eye"));
        remove(new RemoveFilter().id("gtceu:forge_hammer/hammer_ender_eye_block_to_gem"));
        remove(new RemoveFilter().id("gtceu:shapeless/pumpkin_pie_from_dough"));
        remove(new RemoveFilter().id("gtceu:research_station/1x_gtceu_wetware_processor_assembly"));
        remove(new RemoveFilter().id("gtceu:research_station/1x_gtceu_wetware_processor_computer"));
        remove(new RemoveFilter().id("gtceu:assembler/assembly_line_casing"));
        remove(new RemoveFilter().id("gtceu:assembler/assembly_control_casing"));
        remove(new RemoveFilter().id("gtceu:electric_blast_furnace/naq_ingot"));
        remove(new RemoveFilter().outputRegex("gtceu:high_temp_wrought_precursor_(.*)"));
        remove(new RemoveFilter().output("gtceu:small_high_temp_wrought_precursor_dust"));
        remove(new RemoveFilter().output("gtceu:tiny_high_temp_wrought_precursor_dust"));

        // fluid_solidifier: replace tconstruct fluids with gtceu equivalents
        var fluidFilter = new RemoveFilter().idRegex("gtceu:fluid_solidifier/.*");
        for (var entry : TConstructFluidTagFilter.FORGE_TAG_TO_GTCEU_FLUID_MAP.entrySet()) {
            replaceInput(fluidFilter, entry.getKey(), entry.getValue());
        }
        replaceInput(new RemoveFilter().id("gtceu:shaped/plunger_*"), "#forge:rods", "gtceu:tin_rod");
    }

    public static void hostilenetworksRemovals() {
        remove(new RemoveFilter().id("hostilenetworks:sim_chamber"));
        remove(new RemoveFilter().id("hostilenetworks:loot_fabricator"));
        remove(new RemoveFilter().id("hostilenetworks:deep_learner"));
        remove(new RemoveFilter().id("hostilenetworks:framework"));
    }

    public static void javdRemovals() {
        remove(new RemoveFilter().id("javd:portal_block"));
    }

    public static void legendarysurvivaloverhaulRemovals() {
        remove(new RemoveFilter().output("legendarysurvivaloverhaul:thermometer"));
    }

    public static void mae2Removals() {
        remove(new RemoveFilter().outputRegex("mae2:(.*)x_crafting_accelerator"));
    }

    public static void merequesterRemovals() {
        remove(new RemoveFilter().output("merequester:requester"));
    }

    public static void minecraftRemovals() {
        remove(new RemoveFilter().output("minecraft:end_portal_frame"));
        remove(new RemoveFilter().output("minecraft:bricks"));
        remove(new RemoveFilter().id("minecraft:lightning_rod"));
        remove(new RemoveFilter().id("minecraft:iron_trapdoor"));
    }

    public static void miscRemovals() {
        remove(new RemoveFilter().type("twilightforest:uncrafting_table"));
        remove(new RemoveFilter().type("createdieselgenerators:hammering"));
        remove(new RemoveFilter().input(java.util.Arrays.asList("thermal:constantan_ingot")));
    }

    public static void mynethersdelightRemovals() {
        remove(new RemoveFilter().id("mynethersdelight:tnt_alt"));
    }

    public static void mythicbotanyRemovals() {
        remove(new RemoveFilter().mod("mythicbotany").type("botania:runic_altar"));
    }

    public static void pccardRemovals() {
        remove(new RemoveFilter().id("pccard:item/card_programmed_circuit"));
    }

    public static void tetranichematerialsRemovals() {
        remove(new RemoveFilter().id("tetranichematerials:red_gold_powder"));
        remove(new RemoveFilter().output("tetranichematerials:lockwood_ingot"));
    }

    public static void tfmgRemovals() {
        remove(new RemoveFilter().id("tfmg:sequenced_assembly/steel_mechanism"));
        remove(new RemoveFilter().id("tfmg:sequenced_assembly/turbine_engine"));
        remove(new RemoveFilter().id("tfmg:distillation/heavy_oil"));
        remove(new RemoveFilter().output("tfmg:screw"));
        remove(new RemoveFilter().output("tfmg:turbine_blade"));
    }

    public static void thermalRemovals() {
        remove(new RemoveFilter().output("thermal:constantan_ingot"));
    }

    public static void twilightforestRemovals() {
        remove(new RemoveFilter().id("twilightforest:wood/sorting_planks"));
        remove(new RemoveFilter().id("twilightforest:wood/mangrove_planks"));
        remove(new RemoveFilter().id("twilightforest:wood/dark_planks"));
        remove(new RemoveFilter().id("twilightforest:wood/time_planks"));
        remove(new RemoveFilter().id("twilightforest:wood/transformation_planks"));
        remove(new RemoveFilter().id("twilightforest:wood/mining_planks"));
    }

    public static void sophisticatedbackpacksRemovals() {
        // sophisticatedbackpacks/sophisticatedbackpacks.js:
        // event.remove({ output: 'sophisticatedbackpacks:void_upgrade' })
        remove(new RemoveFilter().output("sophisticatedbackpacks:void_upgrade"));
    }

    public static void woodRemovals() {
        // wood.js 中对所有 #minecraft:logs 标签中的木材动态生成 planks 配方并删除原 planks 配方：
        // ...
        // let result2 = e.substring(0, pos + 1) + e.substring(pos + 1, pos2 + 1) + "planks"
        // event.remove({ id: result2 })
        // })
        // 排除：ars_nouveau / botania / aether:ironwood / aether:golden_oak / magic_vine / avocado / fig / wolfberry
        remove(new RemoveFilter()
                .idRegex("(.*):(.*)_planks")
                .not(new RemoveFilter().mod("ars_nouveau"))
                .not(new RemoveFilter().mod("botania"))
                .not(new RemoveFilter().id("aether:ironwood_planks"))
                .not(new RemoveFilter().id("aether:golden_oak_planks"))
                .not(new RemoveFilter().idRegex("(.*):(.*)magic_vine(.*)_planks"))
                .not(new RemoveFilter().idRegex("(.*):(.*)avocado(.*)_planks"))
                .not(new RemoveFilter().idRegex("(.*):(.*)fig(.*)_planks"))
                .not(new RemoveFilter().idRegex("(.*):(.*)wolfberry(.*)_planks")));
        // wood.js 中显式删除的 aether 和 twilightforest planks 配方
        remove(new RemoveFilter().id("aether:skyroot_planks"));
    }

    public static void vintageimprovementsRemovals() {
        remove(new RemoveFilter().id("vintageimprovements:craft/centrifuge"));
        remove(new RemoveFilter().type("vintageimprovements:coiling"));
        remove(new RemoveFilter().id("vintageimprovements:craft/spring_coiling_machine"));
        remove(new RemoveFilter().id("vintageimprovements:craft/vacuum_chamber"));
        remove(new RemoveFilter().id("vintageimprovements:craft/vibrating_table"));
        remove(new RemoveFilter().id("vintageimprovements:craft/curving_press"));
        remove(new RemoveFilter().id("vintageimprovements:craft/laser"));
        remove(new RemoveFilter().id("vintageimprovements:mechanical_crafting/helve_hammer"));
        remove(new RemoveFilter().id("vintageimprovements:pressing/cobalt_ingot"));
        remove(new RemoveFilter().id("vintageimprovements:pressing/rhodium_ingot"));
        remove(new RemoveFilter().id("vintageimprovements:pressing/uranium_ingot"));
        remove(new RemoveFilter().id("vintageimprovements:pressing/rose_gold_ingot"));
        remove(new RemoveFilter().id("vintageimprovements:pressing/vanadium_ingot"));
        remove(new RemoveFilter().id("vintageimprovements:pressing/invar_ingot"));
        remove(new RemoveFilter().id("vintageimprovements:pressing/lead_ingot"));
        remove(new RemoveFilter().id("vintageimprovements:pressing/tin_ingot"));
        remove(new RemoveFilter().id("vintageimprovements:pressing/andesite_alloy"));
        remove(new RemoveFilter().id("vintageimprovements:pressing/bronze_ingot"));
        remove(new RemoveFilter().id("vintageimprovements:pressing/silver_ingot"));
        remove(new RemoveFilter().id("vintageimprovements:pressing/platinum_ingot"));
        remove(new RemoveFilter().id("vintageimprovements:pressing/palladium_ingot"));
        remove(new RemoveFilter().id("vintageimprovements:pressing/zinc_ingot"));
        remove(new RemoveFilter().id("vintageimprovements:pressing/nickel_ingot"));
        remove(new RemoveFilter().id("vintageimprovements:pressing/osmium_ingot"));
        remove(new RemoveFilter().id("vintageimprovements:sequenced_assembly/recipe_card"));
        remove(new RemoveFilter().id("vintageimprovements:pressurizing/sulfuric_acid"));
        remove(new RemoveFilter().id("vintageimprovements:pressurizing/sulfur_trioxide_alt"));
        remove(new RemoveFilter().id("vintageimprovements:pressurizing/sulfur_trioxide"));
        remove(new RemoveFilter().id("vintageimprovements:pressurizing/sulfur_dioxide"));
        remove(new RemoveFilter().id("vintageimprovements:craft/sulfur_items_to_block"));
        remove(new RemoveFilter().id("vintageimprovements:grinder_polishing/rose_quartz"));
        remove(new RemoveFilter().id("vintageimprovements:craft/belt_grinder"));
        remove(new RemoveFilter().id("vintageimprovements:craft/grinder_belt"));
        remove(new RemoveFilter().id("vintageimprovements:craft/tin_rod"));
        remove(new RemoveFilter().outputRegex("vintageimprovements:(.*)_sheet"));
        remove(new RemoveFilter().outputRegex("vintageimprovements:(.*)_rod"));
        remove(new RemoveFilter().outputRegex("vintageimprovements:(.*)_wire"));
        remove(new RemoveFilter().id("vintageimprovements:curving/iron_sheet"));
        remove(new RemoveFilter().id("vintageimprovements:craft/steel_rod"));
        remove(new RemoveFilter().id("vintageimprovements:craft/nickel_rod"));
        remove(new RemoveFilter().id("vintageimprovements:craft/sulfur_item_to_nuggets"));
        remove(new RemoveFilter().id("vintageimprovements:craft/sulfur_nuggets_to_item"));
        remove(new RemoveFilter().id("vintageimprovements:craft/sulfur_block_to_items"));
    }

    /**
     * 杂项 replaceInput/replaceOutput 迁移：来自 orereplace.js 中的循环（针对各种金属的 ingot/nugget/block/plate/rod/gear/dust/molten 替换），
     * filter 为 {not: {mod: "gtceu"}} 或 {}（全部）。
     *
     * 注意：所有调用都直接内联展开，便于静态验证与人工审查。
     */
    public static void oreReplacementRemovals() {
        // ===== 循环1：28 种材料的完整替换（orereplace.js:8-43）=====
        String[] fullMaterials = {
                "tin", "silver", "lead", "nickel", "vibrant_alloy", "energetic_alloy",
                "pulsalting_alloy", "dark_steel", "end_steel", "conductive_alloy",
                "redstone_alloy", "copper_alloy", "soularium", "uranium", "osmium",
                "zinc", "cobalt", "iridium", "brass", "bronze", "constantan",
                "electrum", "steel", "sulfur", "ender_pearl", "calorite", "desh", "ostrum", "invar", "coal",
                "refined_radiance", "silver", "aluminium", "rhodium", "netherite", "palladium", "platinum", "rose_gold",
                "andesite"
        };
        String mod = "gtceu";

        for (String mat : fullMaterials) {
            // replaceOutput: not gtceu, tag → gtceu item
            // [orereplace.js:10] replaceOutput not:gtceu, #forge:ingots/<mat> → gtceu:<mat>_ingot
            replaceOutput(new ReplaceFilter().not(new RemoveFilter().mod("gtceu")),
                    "#forge:ingots/" + mat, mod + ":" + mat + "_ingot");
            // [orereplace.js:12] replaceOutput not:gtceu, #forge:nuggets/<mat> → gtceu:<mat>_nugget
            replaceOutput(new ReplaceFilter().not(new RemoveFilter().mod("gtceu")),
                    "#forge:nuggets/" + mat, mod + ":" + mat + "_nugget");
            // [orereplace.js:14] replaceOutput not:gtceu, #forge:storage_blocks/<mat> → gtceu:<mat>_block
            replaceOutput(new ReplaceFilter().not(new RemoveFilter().mod("gtceu")),
                    "#forge:storage_blocks/" + mat, mod + ":" + mat + "_block");
            // [orereplace.js:16] replaceOutput not:gtceu, #forge:plates/<mat> → gtceu:<mat>_plate
            replaceOutput(new ReplaceFilter().not(new RemoveFilter().mod("gtceu")),
                    "#forge:plates/" + mat, mod + ":" + mat + "_plate");
            // [orereplace.js:18] replaceOutput not:gtceu, #forge:rods/<mat> → gtceu:<mat>_rod
            replaceOutput(new ReplaceFilter().not(new RemoveFilter().mod("gtceu")),
                    "#forge:rods/" + mat, mod + ":" + mat + "_rod");
            // [orereplace.js:20] replaceOutput not:gtceu, #forge:gears/<mat> → gtceu:<mat>_gear
            replaceOutput(new ReplaceFilter().not(new RemoveFilter().mod("gtceu")),
                    "#forge:gears/" + mat, mod + ":" + mat + "_gear");
            // [orereplace.js:22] replaceOutput not:gtceu, #forge:crushed_ores/<mat> → gtceu:<mat>_crushed
            replaceOutput(new ReplaceFilter().not(new RemoveFilter().mod("gtceu")),
                    "#forge:crushed_ores/" + mat, mod + ":" + mat + "_crushed");
            // [orereplace.js:24] replaceOutput not:gtceu, #forge:dusts/<mat> → gtceu:<mat>_dust
            replaceOutput(new ReplaceFilter().not(new RemoveFilter().mod("gtceu")),
                    "#forge:dusts/" + mat, mod + ":" + mat + "_dust");
            // [orereplace.js:26] replaceOutput not:gtceu, #forge:molten_<mat> → gtceu:<mat>
            replaceOutput(new ReplaceFilter().not(new RemoveFilter().mod("gtceu")),
                    "#forge:molten_" + mat, mod + ":" + mat);

            // replaceInput: all mods, tag → gtceu item
            // [orereplace.js:28] replaceInput {}, #forge:ingots/<mat> → gtceu:<mat>_ingot
            replaceInput(new ReplaceFilter(),
                    "#forge:ingots/" + mat, mod + ":" + mat + "_ingot");
            // [orereplace.js:30] replaceInput {}, #forge:nuggets/<mat> → gtceu:<mat>_nugget
            replaceInput(new ReplaceFilter(),
                    "#forge:nuggets/" + mat, mod + ":" + mat + "_nugget");
            // [orereplace.js:32] replaceInput {}, #forge:storage_blocks/<mat> → gtceu:<mat>_block
            replaceInput(new ReplaceFilter(),
                    "#forge:storage_blocks/" + mat, mod + ":" + mat + "_block");
            // [orereplace.js:34] replaceInput {}, #forge:plates/<mat> → gtceu:<mat>_plate
            replaceInput(new ReplaceFilter(),
                    "#forge:plates/" + mat, mod + ":" + mat + "_plate");
            // [orereplace.js:36] replaceInput {}, #forge:rods/<mat> → gtceu:<mat>_rod
            replaceInput(new ReplaceFilter(),
                    "#forge:rods/" + mat, mod + ":" + mat + "_rod");
            // [orereplace.js:38] replaceInput {}, #forge:gears/<mat> → gtceu:<mat>_gear
            replaceInput(new ReplaceFilter(),
                    "#forge:gears/" + mat, mod + ":" + mat + "_gear");
            // [orereplace.js:40] replaceInput {}, #forge:dusts/<mat> → gtceu:<mat>_dust
            replaceInput(new ReplaceFilter(),
                    "#forge:dusts/" + mat, mod + ":" + mat + "_dust");
            // [orereplace.js:42] replaceInput {}, #forge:molten_<mat> → gtceu:<mat>
            replaceInput(new ReplaceFilter(),
                    "#forge:molten_" + mat, mod + ":" + mat);
        }

        // ===== 循环2：copper 的子集（orereplace.js:46-77）=====
        for (String mat : new String[] { "copper" }) {
            // [orereplace.js:52] replaceOutput not:gtceu, #forge:nuggets/copper → gtceu:copper_nugget
            replaceOutput(new ReplaceFilter().not(new RemoveFilter().mod("gtceu")),
                    "#forge:nuggets/" + mat, mod + ":" + mat + "_nugget");
            // [orereplace.js:54] replaceOutput not:gtceu, #forge:plates/copper → gtceu:copper_plate
            replaceOutput(new ReplaceFilter().not(new RemoveFilter().mod("gtceu")),
                    "#forge:plates/" + mat, mod + ":" + mat + "_plate");
            // [orereplace.js:56] replaceOutput not:gtceu, #forge:rods/copper → gtceu:copper_rod
            replaceOutput(new ReplaceFilter().not(new RemoveFilter().mod("gtceu")),
                    "#forge:rods/" + mat, mod + ":" + mat + "_rod");
            // [orereplace.js:58] replaceOutput not:gtceu, #forge:gears/copper → gtceu:copper_gear
            replaceOutput(new ReplaceFilter().not(new RemoveFilter().mod("gtceu")),
                    "#forge:gears/" + mat, mod + ":" + mat + "_gear");
            // [orereplace.js:60] replaceOutput not:gtceu, #forge:crushed_ores/copper → gtceu:copper_crushed
            replaceOutput(new ReplaceFilter().not(new RemoveFilter().mod("gtceu")),
                    "#forge:crushed_ores/" + mat, mod + ":" + mat + "_crushed");
            // [orereplace.js:62] replaceOutput not:gtceu, #forge:dusts/copper → gtceu:copper_dust
            replaceOutput(new ReplaceFilter().not(new RemoveFilter().mod("gtceu")),
                    "#forge:dusts/" + mat, mod + ":" + mat + "_dust");

            // [orereplace.js:64] replaceInput {}, #forge:nuggets/copper → gtceu:copper_nugget
            replaceInput(new ReplaceFilter(),
                    "#forge:nuggets/" + mat, mod + ":" + mat + "_nugget");
            // [orereplace.js:66] replaceInput {}, #forge:plates/copper → gtceu:copper_plate
            replaceInput(new ReplaceFilter(),
                    "#forge:plates/" + mat, mod + ":" + mat + "_plate");
            // [orereplace.js:68] replaceInput {}, #forge:rods/copper → gtceu:copper_rod
            replaceInput(new ReplaceFilter(),
                    "#forge:rods/" + mat, mod + ":" + mat + "_rod");
            // [orereplace.js:70] replaceInput {}, #forge:gears/copper → gtceu:copper_gear
            replaceInput(new ReplaceFilter(),
                    "#forge:gears/" + mat, mod + ":" + mat + "_gear");
            // [orereplace.js:74] replaceInput {}, #forge:dusts/copper → gtceu:copper_dust
            replaceInput(new ReplaceFilter(),
                    "#forge:dusts/" + mat, mod + ":" + mat + "_dust");
        }

        // ===== 循环3：iron + gold 的子集（orereplace.js:78-102）=====
        for (String mat : new String[] { "iron", "gold" }) {
            // [orereplace.js:85] replaceOutput not:gtceu, #forge:plates/<mat> → gtceu:<mat>_plate
            replaceOutput(new ReplaceFilter().not(new RemoveFilter().mod("gtceu")),
                    "#forge:plates/" + mat, mod + ":" + mat + "_plate");
            // [orereplace.js:87] replaceOutput not:gtceu, #forge:rods/<mat> → gtceu:<mat>_rod
            replaceOutput(new ReplaceFilter().not(new RemoveFilter().mod("gtceu")),
                    "#forge:rods/" + mat, mod + ":" + mat + "_rod");
            // [orereplace.js:89] replaceOutput not:gtceu, #forge:gears/<mat> → gtceu:<mat>_gear
            replaceOutput(new ReplaceFilter().not(new RemoveFilter().mod("gtceu")),
                    "#forge:gears/" + mat, mod + ":" + mat + "_gear");
            // [orereplace.js:91] replaceOutput not:gtceu, #forge:dusts/<mat> → gtceu:<mat>_dust
            replaceOutput(new ReplaceFilter().not(new RemoveFilter().mod("gtceu")),
                    "#forge:dusts/" + mat, mod + ":" + mat + "_dust");

            // [orereplace.js:93] replaceInput {}, #forge:plates/<mat> → gtceu:<mat>_plate
            replaceInput(new ReplaceFilter(),
                    "#forge:plates/" + mat, mod + ":" + mat + "_plate");
            // [orereplace.js:95] replaceInput {}, #forge:rods/<mat> → gtceu:<mat>_rod
            replaceInput(new ReplaceFilter(),
                    "#forge:rods/" + mat, mod + ":" + mat + "_rod");
            // [orereplace.js:97] replaceInput {}, #forge:gears/<mat> → gtceu:<mat>_gear
            replaceInput(new ReplaceFilter(),
                    "#forge:gears/" + mat, mod + ":" + mat + "_gear");
            // [orereplace.js:99] replaceInput {}, #forge:dusts/<mat> → gtceu:<mat>_dust
            replaceInput(new ReplaceFilter(),
                    "#forge:dusts/" + mat, mod + ":" + mat + "_dust");
        }

        String[] fullFluid = {
                "oxygen", "hydrogen", "oil", "sulfuric_acid", "polyethylene", "polyvinyl_chloride", "terra_steel",
                "mana_steel", "chromium"
        };
        for (String fluid : fullFluid) {
            replaceInput(new ReplaceFilter(),
                    "#forge:" + fluid, mod + ":" + fluid);
            replaceOutput(new ReplaceFilter(),
                    "#forge:" + fluid, mod + ":" + fluid);
        }

        // 移除所有宝石的 gem_to_gem_flawed_gem 配方（无序合成：gem → flawed gem）
        for (Material mat : GTCEuAPI.materialManager.getRegisteredMaterials()) {
            if (mat.hasProperty(PropertyKey.GEM)) {
                remove(new RemoveFilter().id("gtceu:shapeless/gem_to_gem_flawed_gem_" + mat.getName()));
                remove(new RemoveFilter().id("gtceu:shapeless/gem_to_gem_flawless_gem_" + mat.getName()));
                remove(new RemoveFilter().id("gtceu:shapeless/gem_to_gem_chipped_gem_" + mat.getName()));
                remove(new RemoveFilter().id("gtceu:shapeless/gem_to_gem_gem_" + mat.getName()));
            }
        }

        // ===== orereplace.js 中独立的若干条（第103-143行）=====
        // [orereplace.js:104] replaceInput {}, create:andesite_alloy → gtceu:andesite_alloy_ingot
        replaceInput(new ReplaceFilter(), "create:andesite_alloy", "gtceu:andesite_alloy_ingot");
        // [orereplace.js:105] replaceInput {}, #forge:silicon → gtceu:silicon_ingot
        replaceInput(new ReplaceFilter(), "#forge:silicon", "gtceu:silicon_ingot");
        // [orereplace.js:106] replaceInput {}, #forge:ingots/pulsating_alloy → gtceu:pulsating_alloy_ingot
        replaceInput(new ReplaceFilter(), "#forge:ingots/pulsating_alloy", "gtceu:pulsating_alloy_ingot");
        // [orereplace.js:107] replaceOutput not:gtceu, #forge:ingots/pulsating_alloy → gtceu:pulsating_alloy_ingot
        replaceOutput(new ReplaceFilter().not(new RemoveFilter().mod("gtceu")),
                "#forge:ingots/pulsating_alloy", "gtceu:pulsating_alloy_ingot");

        // [orereplace.js:108] replaceInput create
        // not:{or:[mixing,compacting,cutting,mechanical_crafting,copper_nugget]}
        // minecraft:copper_ingot → gtceu:bronze_ingot
        replaceInput(new ReplaceFilter()
                .mod("create")
                .not(new RemoveFilter().type("create:mixing"))
                .not(new RemoveFilter().type("create:compacting"))
                .not(new RemoveFilter().type("create:cutting"))
                .not(new RemoveFilter().type("create:mechanical_crafting"))
                .not(new RemoveFilter().id("create:crafting/materials/copper_nugget")),
                "minecraft:copper_ingot", "gtceu:bronze_ingot");
        // [orereplace.js:109] replaceInput create not:{or:[mixing,compacting,cutting,mechanical_crafting]}
        // gtceu:copper_plate → gtceu:bronze_plate
        replaceInput(new ReplaceFilter()
                .mod("create")
                .not(new RemoveFilter().type("create:mixing"))
                .not(new RemoveFilter().type("create:compacting"))
                .not(new RemoveFilter().type("create:cutting"))
                .not(new RemoveFilter().type("create:mechanical_crafting")),
                "gtceu:copper_plate", "gtceu:bronze_plate");
        // [orereplace.js:110] replaceInput create_connected:crafting/kinetics/fluid_vessel
        // gtceu:copper_plate → gtceu:bronze_plate
        replaceInput(new ReplaceFilter().id("create_connected:crafting/kinetics/fluid_vessel"),
                "gtceu:copper_plate", "gtceu:bronze_plate");
        // Additional
        remove(new ReplaceFilter().id("create:crafting/kinetics/steam_engine"));
        // [orereplace.js:112] replaceOutput create:milling/andesite
        // minecraft:cobblestone → gtceu:andesite_dust
        replaceOutput(new ReplaceFilter().id("create:milling/andesite"),
                "minecraft:cobblestone", "gtceu:andesite_dust");
        // [orereplace.js:113] replaceInput create:crafting/logistics/andesite_funnel
        // gtceu:andesite_alloy_ingot → gtceu:andesite_alloy_plate
        replaceInput(new ReplaceFilter().id("create:crafting/logistics/andesite_funnel"),
                "gtceu:andesite_alloy_ingot", "gtceu:andesite_alloy_plate");
        // [orereplace.js:114] replaceInput create:crafting/logistics/andesite_tunnel
        // gtceu:andesite_alloy_ingot → gtceu:andesite_alloy_plate
        replaceInput(new ReplaceFilter().id("create:crafting/logistics/andesite_tunnel"),
                "gtceu:andesite_alloy_ingot", "gtceu:andesite_alloy_plate");
        // [orereplace.js:115] replaceInput create:crafting/logistics/brass_funnel
        // gtceu:brass_ingot → gtceu:brass_plate
        replaceInput(new ReplaceFilter().id("create:crafting/logistics/brass_funnel"),
                "gtceu:brass_ingot", "gtceu:brass_plate");
        // [orereplace.js:116] replaceInput create:crafting/logistics/brass_tunnel
        // gtceu:brass_ingot → gtceu:brass_plate
        replaceInput(new ReplaceFilter().id("create:crafting/logistics/brass_tunnel"),
                "gtceu:brass_ingot", "gtceu:brass_plate");
        // [orereplace.js:117] replaceInput output:create:brass_casing
        // gtceu:brass_ingot → gtceu:brass_plate
        replaceInput(new ReplaceFilter().output("create:brass_casing"),
                "gtceu:brass_ingot", "gtceu:brass_plate");
        // [orereplace.js:118] replaceInput {}, biomesoplenty:cherry_wood → minecraft:cherry_wood
        replaceInput(new ReplaceFilter(), "biomesoplenty:cherry_wood", "minecraft:cherry_wood");
        // [orereplace.js:119] replaceOutput {}, create:powered_obsidian → gtceu:obsidian_dust
        replaceOutput(new ReplaceFilter(), "create:powered_obsidian", "gtceu:obsidian_dust");
        // [orereplace.js:120] replaceInput {}, create:powered_obsidian → gtceu:obsidian_dust
        replaceInput(new ReplaceFilter(), "create:powered_obsidian", "gtceu:obsidian_dust");
        // [orereplace.js:121] replaceOutput create:splashing/red_sand
        // minecraft:gold_nugget → gtceu:precious_alloy_nugget
        replaceOutput(new ReplaceFilter().id("create:splashing/red_sand"),
                "minecraft:gold_nugget", "gtceu:precious_alloy_nugget");
        // [orereplace.js:122] replaceOutput create:splashing/soul_sand
        // minecraft:gold_nugget → gtceu:precious_alloy_nugget
        replaceOutput(new ReplaceFilter().id("create:splashing/soul_sand"),
                "minecraft:gold_nugget", "gtceu:precious_alloy_nugget");

        // [orereplace.js:124] replaceOutput {}, #forge:ethanol → gtceu:ethanol
        replaceOutput(new ReplaceFilter(), "#forge:ethanol", "gtceu:ethanol");
        // [orereplace.js:125] replaceInput {}, #forge:ethanol → gtceu:ethanol
        replaceInput(new ReplaceFilter(), "#forge:ethanol", "gtceu:ethanol");
        // [orereplace.js:126] replaceInput {}, #forge:ingots/soularium → gtceu:soularium_ingot
        replaceInput(new ReplaceFilter(), "#forge:ingots/soularium", "gtceu:soularium_ingot");
        // [orereplace.js:127] replaceOutput {}, #forge:ingots/soularium → gtceu:soularium_ingot
        replaceOutput(new ReplaceFilter(), "#forge:ingots/soularium", "gtceu:soularium_ingot");
        // [orereplace.js:128] replaceInput {}, enderio:powdered_quartz → gtceu:nether_quartz_dust
        replaceInput(new ReplaceFilter(), "enderio:powdered_quartz", "gtceu:nether_quartz_dust");
        // [orereplace.js:129] replaceInput {}, gtceu:refined_radiance_ingot → create:refined_radiance
        replaceInput(new ReplaceFilter(), "gtceu:refined_radiance_ingot", "create:refined_radiance");
        // [orereplace.js:130] replaceInput {}, gtceu:shadow_steel_ingot → create:shadow_steel
        replaceInput(new ReplaceFilter(), "gtceu:shadow_steel_ingot", "create:shadow_steel");
        // [orereplace.js:131] replaceInput {}, #forge:dusts/quartz → gtceu:nether_quartz_dust
        replaceInput(new ReplaceFilter(), "#forge:dusts/quartz", "gtceu:nether_quartz_dust");
        // [orereplace.js:132] replaceInput create_new_age:cutting/copper_sheet
        // gtceu:copper_plate → gtceu:double_copper_plate
        replaceInput(new ReplaceFilter().id("create_new_age:cutting/copper_sheet"),
                "gtceu:copper_plate", "gtceu:double_copper_plate");

        // [orereplace.js:133-139] replaceInput bloodmagic runes (9 个)
        // minecraft:netherite_scrap → minecraft:netherite_ingot
        String[] bloodMagicRunes = {
                "bloodmagic:blood_rune_speed_2", "bloodmagic:blood_rune_sac_2",
                "bloodmagic:blood_rune_self_sac_2", "bloodmagic:blood_rune_displacement_2",
                "bloodmagic:blood_rune_capacity_2", "bloodmagic:blood_rune_aug_capacity_2",
                "bloodmagic:blood_rune_orb_2", "bloodmagic:blood_rune_acceleration_2",
                "bloodmagic:blood_rune_charging_2"
        };
        for (String runeid : bloodMagicRunes) {
            replaceInput(new ReplaceFilter().id(runeid),
                    "minecraft:netherite_scrap", "minecraft:netherite_ingot");
        }

        // [orereplace.js:140] replaceInput {}, #forge:storage_blocks/nether_star → gtceu:nether_star_block
        replaceInput(new ReplaceFilter(), "#forge:storage_blocks/nether_star", "gtceu:nether_star_block");
        // [orereplace.js:141] replaceOutput {}, #forge:storage_blocks/nether_star → gtceu:nether_star_block
        replaceOutput(new ReplaceFilter(), "#forge:storage_blocks/nether_star", "gtceu:nether_star_block");
        // [orereplace.js:143] replaceOutput not:gtceu, #forge:molten_brass → gtceu:brass
        replaceOutput(new ReplaceFilter().not(new RemoveFilter().mod("gtceu")),
                "#forge:molten_brass", "gtceu:brass");
    }

    /**
     * create/createFallen.js 中的 replaceInput：'vintageimprovements:iron_spring' -> 'gtceu:iron_spring'
     */
    public static void createFallenRemovals() {
        // [createFallen.js:24] replaceInput {}, vintageimprovements:iron_spring → gtceu:iron_spring
        replaceInput(new ReplaceFilter(), "vintageimprovements:iron_spring", "gtceu:iron_spring");
    }

    /**
     * gtceu/chain/SiliconChain.js 中的 replaceOutput（来自 zeolite 链的 2 条）
     */
    public static void siliconChainRemovals() {
        // [SiliconChain.js:3] replaceOutput gtceu:electrolyzer/zeolite_electrolysis
        // gtceu:aluminium_dust → gtceu:alumina_dust
        replaceOutput(new ReplaceFilter().id("gtceu:electrolyzer/zeolite_electrolysis"),
                "gtceu:aluminium_dust", "gtceu:alumina_dust");
        // [SiliconChain.js:4] replaceOutput gtceu:electrolyzer/zeolite_electrolysis
        // gtceu:silicon_dust → gtceu:silicon_dioxide_dust
        replaceOutput(new ReplaceFilter().id("gtceu:electrolyzer/zeolite_electrolysis"),
                "gtceu:silicon_dust", "gtceu:silicon_dioxide_dust");
        // [SiliconChain.js:5] replaceOutput gtceu:centrifuge/decomposition_centrifuging__redstone
        // gtceu:silicon_dust → gtceu:silicon_dioxide_dust
        replaceOutput(new ReplaceFilter().id("gtceu:centrifuge/decomposition_centrifuging__redstone"),
                "gtceu:silicon_dust", "gtceu:silicon_dioxide_dust");
    }

    /**
     * ae2.js 中的 replaceInput（4 条）
     */
    public static void ae2ReplaceInputRemovals() {
        // [ae2.js:222] replaceInput ae2:network/wireless_part, minecraft:iron_ingot → gtceu:iron_plate
        replaceInput(new ReplaceFilter().id("ae2:network/wireless_part"),
                "minecraft:iron_ingot", "gtceu:iron_plate");
        // [ae2.js:223] replaceInput ae2:network/blocks/storage_drive, minecraft:iron_ingot →
        // gtceu:stainless_steel_plate
        replaceInput(new ReplaceFilter().id("ae2:network/blocks/storage_drive"),
                "minecraft:iron_ingot", "gtceu:stainless_steel_plate");
        // [ae2.js:224] replaceInput ae2:network/parts/import_bus, minecraft:iron_ingot → gtceu:steel_plate
        replaceInput(new ReplaceFilter().id("ae2:network/parts/import_bus"),
                "minecraft:iron_ingot", "gtceu:steel_plate");
        // [ae2.js:225] replaceInput ae2:network/parts/export_bus, minecraft:iron_ingot → gtceu:steel_plate
        replaceInput(new ReplaceFilter().id("ae2:network/parts/export_bus"),
                "minecraft:iron_ingot", "gtceu:steel_plate");
    }

    /**
     * functional_storage.js 中的 replaceInput（2 条）
     */
    public static void functionalStorageReplaceRemovals() {
        // [functional_storage.js:2-3] replaceInput functionalstorage:fluid_2
        // minecraft:bucket → create:fluid_tank
        replaceInput(new ReplaceFilter().id("functionalstorage:fluid_2"),
                "minecraft:bucket", "create:fluid_tank");
        // [functional_storage.js:3] replaceInput functionalstorage:fluid_2
        // #minecraft:planks → minecraft:smooth_stone
        replaceInput(new ReplaceFilter().id("functionalstorage:fluid_2"),
                "#minecraft:planks", "minecraft:smooth_stone");
        // [functional_storage.js:4-5] replaceInput functionalstorage:fluid_4
        // minecraft:bucket → create:fluid_tank
        replaceInput(new ReplaceFilter().id("functionalstorage:fluid_4"),
                "minecraft:bucket", "create:fluid_tank");
        // [functional_storage.js:5] replaceInput functionalstorage:fluid_4
        // #minecraft:planks → minecraft:smooth_stone
        replaceInput(new ReplaceFilter().id("functionalstorage:fluid_4"),
                "#minecraft:planks", "minecraft:smooth_stone");
    }

    /**
     * create/dieselgenerator.js 中的 replaceInput/replaceOutput（3+1 条）
     */
    public static void createdieselgeneratorsReplaceRemovals() {
        // [dieselgenerator.js:47] replaceInput pumpjack_crank, gtceu:andesite_alloy_ingot → gtceu:andesite_alloy_plate
        replaceInput(new ReplaceFilter().id("createdieselgenerators:mechanical_crafting/pumpjack_crank"),
                "gtceu:andesite_alloy_ingot", "gtceu:andesite_alloy_plate");
        // [dieselgenerator.js:48] replaceInput pumpjack_crank, gtceu:iron_plate → gtceu:steel_plate
        replaceInput(new ReplaceFilter().id("createdieselgenerators:mechanical_crafting/pumpjack_crank"),
                "gtceu:iron_plate", "gtceu:steel_plate");
        // [dieselgenerator.js:49] replaceInput pumpjack_crank, gtceu:zinc_ingot → gtceu:zinc_plate
        replaceInput(new ReplaceFilter().id("createdieselgenerators:mechanical_crafting/pumpjack_crank"),
                "gtceu:zinc_ingot", "gtceu:zinc_plate");
        // [dieselgenerator.js:61] replaceOutput plant_oil (fluid replacement, handled separately)
        // NOTE: Fluid replacement is handled by DieselGeneratorRecipes.java via new recipe registration
        // replaceOutput(new RemoveFilter().id("createdieselgenerators:compacting/plant_oil"), ...);
    }

    /**
     * KubeJS 风格的 filter。支持的字段名与 {@code event.remove({...})} 一致。
     */
    public static class RemoveFilter {

        private Object id;          // String 或 List<String>
        private String idRegex;
        private String mod;
        private String output;
        private String outputRegex;
        private Object input;       // String 或 List<String>
        private String inputRegex;
        private String type;
        private List<RemoveFilter> notList;   // KubeJS 风格的反选 filter 列表（可调用多次 not() 叠加）
        private List<RemoveFilter> orList;    // KubeJS 风格的 or 列表（任一匹配即匹配）

        public RemoveFilter id(String id) {
            this.id = id;
            return this;
        }

        /** 接受 KubeJS 风格的 {@code id}（字符串或字符串数组） */
        public RemoveFilter id(List<String> ids) {
            this.id = ids;
            return this;
        }

        public RemoveFilter idRegex(String idRegex) {
            this.idRegex = idRegex;
            return this;
        }

        public RemoveFilter mod(String mod) {
            this.mod = mod;
            return this;
        }

        public RemoveFilter output(String output) {
            this.output = output;
            return this;
        }

        public RemoveFilter outputRegex(String outputRegex) {
            this.outputRegex = outputRegex;
            return this;
        }

        public RemoveFilter input(String input) {
            this.input = input;
            return this;
        }

        public RemoveFilter input(List<String> inputs) {
            this.input = inputs;
            return this;
        }

        public RemoveFilter inputRegex(String inputRegex) {
            this.inputRegex = inputRegex;
            return this;
        }

        public RemoveFilter type(String type) {
            this.type = type;
            return this;
        }

        /** 设置反选条件：与 not 指定的 filter 匹配的配方不会被删除。可多次调用叠加多个反选条件。 */
        public RemoveFilter not(RemoveFilter not) {
            if (this.notList == null) {
                this.notList = new ArrayList<>();
            }
            this.notList.add(not);
            return this;
        }

        /** 设置或条件：任一 or 指定的 filter 匹配即匹配。可多次调用叠加多个子 filter。 */
        public RemoveFilter or(RemoveFilter or) {
            if (this.orList == null) {
                this.orList = new ArrayList<>();
            }
            this.orList.add(or);
            return this;
        }

        // ===== Mixin 需要的查询方法 =====

        /** 若 filter 仅包含一个精确 ID 匹配（无其他条件），返回该 ID；否则返回 null */
        public String getSingleExactId() {
            if (id != null && idRegex == null && mod == null && output == null && outputRegex == null &&
                    input == null && inputRegex == null && type == null && notList == null && orList == null) {
                if (id instanceof String) return (String) id;
            }
            return null;
        }

        public String idAsString() {
            return id instanceof String ? (String) id : null;
        }

        @SuppressWarnings("unchecked")
        public List<String> idAsList() {
            if (id instanceof String) return List.of((String) id);
            if (id instanceof List) return (List<String>) id;
            return null;
        }

        public String idRegex() {
            return idRegex;
        }

        public String mod() {
            return mod;
        }

        public String type() {
            return type;
        }

        public String output() {
            return output;
        }

        public String outputRegex() {
            return outputRegex;
        }

        public boolean hasInputCheck() {
            return input != null || inputRegex != null;
        }

        public List<RemoveFilter> getNotList() {
            return notList;
        }

        public List<RemoveFilter> getOrList() {
            return orList;
        }

        /**
         * JSON 级别的 filter 匹配（不需要 Recipe 对象）。
         * 只能匹配 id, mod, type（从 JSON）。不检查 output/input。
         */
        public boolean matchesJsonLevel(String idStr, String namespace, String jsonType) {
            if (id != null) {
                boolean matched = false;
                if (id instanceof String sid) matched = sid.equals(idStr);
                else if (id instanceof List<?> lid) matched = lid.contains(idStr);
                if (!matched) return false;
            }
            if (idRegex != null && !java.util.regex.Pattern.matches(idRegex, idStr)) return false;
            if (mod != null && !mod.equals(namespace)) return false;
            if (type != null && !type.equals(jsonType)) return false;

            if (notList != null) {
                for (var n : notList) {
                    if (n.matchesJsonLevel(idStr, namespace, jsonType)) return false;
                }
            }
            if (orList != null) {
                boolean anyMatched = false;
                for (var o : orList) {
                    if (o.matchesJsonLevel(idStr, namespace, jsonType)) {
                        anyMatched = true;
                        break;
                    }
                }
                if (!anyMatched) return false;
            }
            return true;
        }

        // ===== 原有的 Recipe 级别匹配方法（保留用于兼容） =====

        boolean matches(String idStr, String modId, String typeId, String resultIdStr,
                        Recipe<?> recipe, RegistryAccess registryAccess) {
            // 获取配方结果物品
            ItemStack result = recipe.getResultItem(registryAccess);

            // id 精确匹配（支持 String 和 List<String>）
            if (this.id != null) {
                boolean idMatched = false;
                if (this.id instanceof String) {
                    idMatched = this.id.equals(idStr);
                } else if (this.id instanceof List) {
                    for (Object o : (List<?>) this.id) {
                        if (o instanceof String && o.equals(idStr)) {
                            idMatched = true;
                            break;
                        }
                    }
                }
                if (!idMatched) return false;
            }

            // id 正则匹配
            if (this.idRegex != null && !Pattern.matches(this.idRegex, idStr)) {
                return false;
            }

            // mod 匹配
            if (this.mod != null && !this.mod.equals(modId)) {
                return false;
            }

            // type 匹配
            if (this.type != null && !this.type.equals(typeId)) {
                return false;
            }

            // output 匹配（支持 item ID 和 tag 格式如 #forge:plates）
            if (this.output != null) {
                boolean outputMatched = false;
                if (resultIdStr != null && resultIdStr.equals(this.output)) {
                    outputMatched = true;
                } else if (this.output.startsWith("#")) {
                    // Tag-based output matching
                    String tagName = this.output.substring(1);
                    ResourceLocation tagId = ResourceLocation.parse(tagName);
                    var tagKey = ItemTags.create(tagId);
                    if (!result.isEmpty() && result.is(tagKey)) {
                        outputMatched = true;
                    }
                }
                if (!outputMatched) {
                    return false;
                }
            }

            // outputRegex 匹配
            if (this.outputRegex != null) {
                if (resultIdStr == null || !Pattern.matches(this.outputRegex, resultIdStr)) {
                    return false;
                }
            }

            // input 匹配
            if (this.input != null) {
                List<String> inputList;
                if (this.input instanceof String) {
                    inputList = List.of((String) this.input);
                } else if (this.input instanceof List) {
                    inputList = new ArrayList<>();
                    for (Object o : (List<?>) this.input) {
                        if (o instanceof String) inputList.add((String) o);
                    }
                } else {
                    inputList = List.of();
                }
                boolean found = false;
                for (Ingredient ingredient : recipe.getIngredients()) {
                    for (ItemStack stack : ingredient.getItems()) {
                        ResourceLocation itemId = ForgeRegistries.ITEMS.getKey(stack.getItem());
                        if (itemId != null && inputList.contains(itemId.toString())) {
                            found = true;
                            break;
                        }
                    }
                    if (found) break;
                }
                if (!found) return false;
            }

            // inputRegex 匹配：任一输入物品的 id 匹配正则即算匹配
            if (this.inputRegex != null) {
                boolean found = false;
                for (Ingredient ingredient : recipe.getIngredients()) {
                    for (ItemStack stack : ingredient.getItems()) {
                        ResourceLocation itemId = ForgeRegistries.ITEMS.getKey(stack.getItem());
                        if (itemId != null && Pattern.matches(this.inputRegex, itemId.toString())) {
                            found = true;
                            break;
                        }
                    }
                    if (found) break;
                }
                if (!found) return false;
            }

            // not 反选：若与任意一个 not 指定的 filter 匹配，则不删除
            if (this.notList != null) {
                for (RemoveFilter n : notList) {
                    if (n.matches(idStr, modId, typeId, resultIdStr, recipe, registryAccess)) {
                        return false;
                    }
                }
            }

            // or 列表：若设置了 or 列表，则只判断 or 中的子 filter；任一子 filter 匹配即匹配
            // （注意：orList 与本 filter 其他字段的组合是"本 filter AND (orList 任一)"的语义）
            if (this.orList != null) {
                boolean anyOrMatched = false;
                for (RemoveFilter o : orList) {
                    if (o.matches(idStr, modId, typeId, resultIdStr, recipe, registryAccess)) {
                        anyOrMatched = true;
                        break;
                    }
                }
                if (!anyOrMatched) return false;
            }

            return true;
        }

        @Override
        public String toString() {
            // 显示所有字段，避免丢信息（旧实现用 else if 链，只显示第一个被设置的字段）
            StringBuilder sb = new StringBuilder("filter[");
            boolean first = true;
            if (id != null) {
                if (!first) sb.append(", ");
                sb.append("id=").append(id);
                first = false;
            }
            if (idRegex != null) {
                if (!first) sb.append(", ");
                sb.append("idRegex=").append(idRegex);
                first = false;
            }
            if (mod != null) {
                if (!first) sb.append(", ");
                sb.append("mod=").append(mod);
                first = false;
            }
            if (output != null) {
                if (!first) sb.append(", ");
                sb.append("output=").append(output);
                first = false;
            }
            if (outputRegex != null) {
                if (!first) sb.append(", ");
                sb.append("outputRegex=").append(outputRegex);
                first = false;
            }
            if (input != null) {
                if (!first) sb.append(", ");
                sb.append("input=").append(input);
                first = false;
            }
            if (inputRegex != null) {
                if (!first) sb.append(", ");
                sb.append("inputRegex=").append(inputRegex);
                first = false;
            }
            if (type != null) {
                if (!first) sb.append(", ");
                sb.append("type=").append(type);
                first = false;
            }
            if (notList != null) {
                for (RemoveFilter n : notList) {
                    if (!first) sb.append(", ");
                    sb.append("not=").append(n);
                    first = false;
                }
            }
            if (orList != null) {
                for (RemoveFilter o : orList) {
                    if (!first) sb.append(", ");
                    sb.append("or=").append(o);
                    first = false;
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }

    public static class ReplaceOperation {

        public enum Type {
            INPUT,
            OUTPUT
        }

        public final RemoveFilter filter;
        public final String from;  // e.g., "#forge:ingots/tin" or "minecraft:iron_ingot" or "#forge:iron"
        public final String to;    // e.g., "gtceu:tin_ingot" or "gtceu:iron"
        public final Type type;

        ReplaceOperation(RemoveFilter filter, String from, String to, Type type) {
            this.filter = filter;
            this.from = from;
            this.to = to;
            this.type = type;
        }

        @Override
        public String toString() {
            return "replace" + type.name().toLowerCase() + "[" + filter + ", " + from + " -> " + to + "]";
        }
    }

    /**
     * KubeJS 风格的 filter，用于 event.replaceInput / event.replaceOutput。
     * 字段语义与 RemoveFilter 完全一致，仅作为类型区分以让 Java 调用者明确语义。
     */
    public static class ReplaceFilter extends RemoveFilter {}
}
