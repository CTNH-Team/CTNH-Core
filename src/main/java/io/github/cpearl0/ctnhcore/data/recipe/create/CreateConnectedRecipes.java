package io.github.cpearl0.ctnhcore.data.recipe.create;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;

import com.hlysine.create_connected.registries.CCBlocks;
import com.hlysine.create_connected.registries.CCItems;
import com.mo_guang.ctpp.data.recipe.builder.create.CuttingRecipeBuilder;
import com.mo_guang.ctpp.data.recipe.builder.create.FillingRecipeBuilder;
import com.mo_guang.ctpp.data.recipe.builder.create.ItemApplicationRecipeBuilder;
import com.mo_guang.ctpp.data.recipe.builder.create.SequencedAssemblyRecipeBuilder;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;

import java.util.Objects;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper.getTag;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.ingot;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.plate;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Brass;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Copper;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Gold;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Iron;

/**
 * Create Connected 配方全量覆盖（基于 create_connected-1.2.3 配方数据包，187 个配方）。
 *
 * <p>
 * 原配方由 {@link io.github.cpearl0.ctnhcore.data.recipe.RecipeRemoval} 按
 * {@code create_connected} 命名空间全量移除，这里以相同路径（{@code ctnhcore:create_connected/...}）重加。
 * 硬编码的 create 命名空间物品在输入侧替换为 forge 标准 tag（如 {@code create:brass_ingot} →
 * {@code forge:ingots/brass}，见 {@code ChemicalHelper.getTag}），输出保持原物品；其余物品使用直接对象引用。
 *
 * <p>
 * builder 复用（均位于 CTPP 模块）：
 * <ul>
 * <li>工作台配方（crafting_shaped / crafting_shapeless）— GTCEu {@link VanillaRecipeHelper}；
 * 原 {@code create_connected:feature_enabled} 条件因该包装不支持而省略（整合包配置中所有特性均开启）</li>
 * <li>切割配方 — CTPP {@link CuttingRecipeBuilder}（条件与 processingTime 省略）</li>
 * <li>物品应用配方 — CTPP {@link ItemApplicationRecipeBuilder}（条件省略）</li>
 * <li>填充配方 — CTPP {@link FillingRecipeBuilder}（create 包，条件完整保留）</li>
 * <li>切石机配方 — 原版 {@link SingleItemRecipeBuilder}（条件省略）</li>
 * <li>装配配方 — CTPP {@link SequencedAssemblyRecipeBuilder}（原 chance 权重因 builder 不支持而省略）</li>
 * </ul>
 *
 * <p>
 * 原配方中引用 packwiz 之外的附属模组（copycats/garnished/create_dragons_plus/
 * createnuclear/create_dd/create_henry/create_more_catalysts/dndesires 等）的
 * {@code forge:mod_loaded} / {@code feature_enabled_in_copycats} 条件配方直接跳过不重写
 * （对应模组不在整合包中，原配方条件恒不满足）；无法直接引用的物品回退为注册表查询。
 */
public class CreateConnectedRecipes {

    private CreateConnectedRecipes() {}

    private static Item item(String id) {
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(id)),
                "Unknown item: " + id);
    }

    private static ItemStack stack(String id) {
        return new ItemStack(item(id));
    }

    private static TagKey<Item> tagKey(String id) {
        return TagKey.create(Registries.ITEM, ResourceLocation.tryParse(id));
    }

    public static void init(Consumer<FinishedRecipe> provider) {
        // ===== crafting/kinetics =====
        // crafting/kinetics/brake
        VanillaRecipeHelper.addShapelessRecipe(provider, CTNHCore.id("create_connected/crafting/kinetics/brake"),
                CCBlocks.BRAKE.asStack(),
                AllBlocks.ANDESITE_CASING.asStack(),
                AllBlocks.SHAFT.asStack(),
                new ItemStack(Items.REDSTONE),
                new ItemStack(Items.OBSIDIAN));
        // crafting/kinetics/brass_chute
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("create_connected/crafting/kinetics/brass_chute"),
                CCBlocks.BRASS_CHUTE.asStack(4),
                "S",
                "I",
                "S",
                'I', getTag(ingot, Brass),
                'S', getTag(plate, Brass));
        // crafting/kinetics/brass_gearbox
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("create_connected/crafting/kinetics/brass_gearbox"),
                CCBlocks.BRASS_GEARBOX.asStack(),
                " c ",
                "csc",
                " c ",
                'c', AllBlocks.COGWHEEL.asStack(),
                's', AllBlocks.ROTATION_SPEED_CONTROLLER.asStack());
        // crafting/kinetics/brass_gearbox_from_conversion
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/brass_gearbox_from_conversion"),
                CCBlocks.BRASS_GEARBOX.asStack(),
                CCItems.VERTICAL_BRASS_GEARBOX.asStack());
        // crafting/kinetics/centrifugal_clutch
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/centrifugal_clutch"),
                CCBlocks.CENTRIFUGAL_CLUTCH.asStack(),
                AllBlocks.ANDESITE_CASING.asStack(),
                AllBlocks.SHAFT.asStack(),
                getTag(plate, Iron),
                AllBlocks.SPEEDOMETER.asStack());
        // crafting/kinetics/clutch_from_conversion
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/clutch_from_conversion"),
                AllBlocks.CLUTCH.asStack(),
                CCBlocks.INVERTED_CLUTCH.asStack());
        // crafting/kinetics/crank_wheel
        VanillaRecipeHelper.addShapelessRecipe(provider, CTNHCore.id("create_connected/crafting/kinetics/crank_wheel"),
                CCBlocks.CRANK_WHEEL.asStack(),
                AllBlocks.HAND_CRANK.asStack(),
                AllBlocks.COGWHEEL.asStack());
        // crafting/kinetics/cross_connector
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("create_connected/crafting/kinetics/cross_connector"),
                CCBlocks.CROSS_CONNECTOR.asStack(),
                " s ",
                "sgs",
                " s ",
                'g', AllBlocks.GEARBOX.asStack(),
                's', AllBlocks.SHAFT.asStack());
        // crafting/kinetics/dashboard
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("create_connected/crafting/kinetics/dashboard"),
                CCBlocks.DASHBOARD.asStack(),
                "B",
                "C",
                'B', AllBlocks.DISPLAY_BOARD.asStack(),
                'C', AllBlocks.BRASS_CASING.asStack());
        // crafting/kinetics/empty_fan_catalyst
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                "bib",
                "i i",
                "bib",
                'b', getTag(ingot, Brass),
                'i', new ItemStack(Items.IRON_BARS));
        // crafting/kinetics/empty_fan_catalyst_from_black_dye
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_black_dye"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_DYEING_CATALYSTS.get(DyeColor.BLACK).asStack());
        // crafting/kinetics/empty_fan_catalyst_from_blasting
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_blasting"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_BLASTING_CATALYST.asStack());
        // crafting/kinetics/empty_fan_catalyst_from_blue_dye
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_blue_dye"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_DYEING_CATALYSTS.get(DyeColor.BLUE).asStack());
        // crafting/kinetics/empty_fan_catalyst_from_brown_dye
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_brown_dye"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_DYEING_CATALYSTS.get(DyeColor.BROWN).asStack());
        // crafting/kinetics/empty_fan_catalyst_from_chocolate_coating
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_chocolate_coating"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_CHOCOLATE_COATING_CATALYST.asStack());
        // crafting/kinetics/empty_fan_catalyst_from_cyan_dye
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_cyan_dye"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_DYEING_CATALYSTS.get(DyeColor.CYAN).asStack());
        // crafting/kinetics/empty_fan_catalyst_from_ending_dragon_head
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_ending_dragon_head"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_ENDING_CATALYST_DRAGON_HEAD.asStack());
        // crafting/kinetics/empty_fan_catalyst_from_ending_dragons_breath
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_ending_dragons_breath"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_ENDING_CATALYST_DRAGONS_BREATH.asStack());
        // crafting/kinetics/empty_fan_catalyst_from_enriched
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_enriched"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_ENRICHED_CATALYST.asStack());
        // crafting/kinetics/empty_fan_catalyst_from_exploding
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_exploding"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_EXPLODING_CATALYST.asStack());
        // crafting/kinetics/empty_fan_catalyst_from_freezing
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_freezing"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_FREEZING_CATALYST.asStack());
        // crafting/kinetics/empty_fan_catalyst_from_gray_dye
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_gray_dye"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_DYEING_CATALYSTS.get(DyeColor.GRAY).asStack());
        // crafting/kinetics/empty_fan_catalyst_from_green_dye
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_green_dye"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_DYEING_CATALYSTS.get(DyeColor.GREEN).asStack());
        // crafting/kinetics/empty_fan_catalyst_from_haunting
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_haunting"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_HAUNTING_CATALYST.asStack());
        // crafting/kinetics/empty_fan_catalyst_from_honey_coating
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_honey_coating"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_HONEY_COATING_CATALYST.asStack());
        // crafting/kinetics/empty_fan_catalyst_from_light_blue_dye
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_light_blue_dye"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_DYEING_CATALYSTS.get(DyeColor.LIGHT_BLUE).asStack());
        // crafting/kinetics/empty_fan_catalyst_from_light_gray_dye
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_light_gray_dye"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_DYEING_CATALYSTS.get(DyeColor.LIGHT_GRAY).asStack());
        // crafting/kinetics/empty_fan_catalyst_from_lime_dye
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_lime_dye"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_DYEING_CATALYSTS.get(DyeColor.LIME).asStack());
        // crafting/kinetics/empty_fan_catalyst_from_magenta_dye
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_magenta_dye"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_DYEING_CATALYSTS.get(DyeColor.MAGENTA).asStack());
        // crafting/kinetics/empty_fan_catalyst_from_orange_dye
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_orange_dye"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_DYEING_CATALYSTS.get(DyeColor.ORANGE).asStack());
        // crafting/kinetics/empty_fan_catalyst_from_pink_dye
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_pink_dye"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_DYEING_CATALYSTS.get(DyeColor.PINK).asStack());
        // crafting/kinetics/empty_fan_catalyst_from_purifying
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_purifying"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_PURIFYING_CATALYST.asStack());
        // crafting/kinetics/empty_fan_catalyst_from_purple_dye
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_purple_dye"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_DYEING_CATALYSTS.get(DyeColor.PURPLE).asStack());
        // crafting/kinetics/empty_fan_catalyst_from_red_dye
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_red_dye"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_DYEING_CATALYSTS.get(DyeColor.RED).asStack());
        // crafting/kinetics/empty_fan_catalyst_from_resonance
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_resonance"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_RESONANCE_CATALYST.asStack());
        // crafting/kinetics/empty_fan_catalyst_from_sanding
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_sanding"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_SANDING_CATALYST.asStack());
        // crafting/kinetics/empty_fan_catalyst_from_sculking
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_sculking"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_SCULKING_CATALYST.asStack());
        // crafting/kinetics/empty_fan_catalyst_from_seething
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_seething"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_SEETHING_CATALYST.asStack());
        // crafting/kinetics/empty_fan_catalyst_from_smoking
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_smoking"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_SMOKING_CATALYST.asStack());
        // crafting/kinetics/empty_fan_catalyst_from_splashing
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_splashing"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_SPLASHING_CATALYST.asStack());
        // crafting/kinetics/empty_fan_catalyst_from_white_dye
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_white_dye"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_DYEING_CATALYSTS.get(DyeColor.WHITE).asStack());
        // crafting/kinetics/empty_fan_catalyst_from_withering
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_withering"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_WITHERING_CATALYST.asStack());
        // crafting/kinetics/empty_fan_catalyst_from_yellow_dye
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/empty_fan_catalyst_from_yellow_dye"),
                CCBlocks.EMPTY_FAN_CATALYST.asStack(),
                CCBlocks.FAN_DYEING_CATALYSTS.get(DyeColor.YELLOW).asStack());
        // crafting/kinetics/encased_chain_cogwheel
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/encased_chain_cogwheel"),
                CCBlocks.ENCASED_CHAIN_COGWHEEL.asStack(),
                AllBlocks.ENCASED_CHAIN_DRIVE.asStack(),
                AllBlocks.COGWHEEL.asStack());
        // crafting/kinetics/fluid_tank_from_conversion
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/fluid_tank_from_conversion"),
                AllBlocks.FLUID_TANK.asStack(),
                CCBlocks.FLUID_VESSEL.asStack());
        // crafting/kinetics/fluid_vessel
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("create_connected/crafting/kinetics/fluid_vessel"),
                CCBlocks.FLUID_VESSEL.asStack(),
                "BCB",
                'B', getTag(plate, Copper),
                'C', tagKey("forge:barrels/wooden"));
        // crafting/kinetics/fluid_vessel_from_conversion
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/fluid_vessel_from_conversion"),
                CCBlocks.FLUID_VESSEL.asStack(),
                AllBlocks.FLUID_TANK.asStack());
        // crafting/kinetics/freewheel_clutch
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/freewheel_clutch"),
                CCBlocks.FREEWHEEL_CLUTCH.asStack(),
                AllBlocks.ANDESITE_CASING.asStack(),
                AllBlocks.SHAFT.asStack(),
                getTag(plate, Iron),
                AllBlocks.COGWHEEL.asStack());
        // crafting/kinetics/gearshift_from_conversion
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/gearshift_from_conversion"),
                AllBlocks.GEARSHIFT.asStack(),
                CCBlocks.INVERTED_GEARSHIFT.asStack());
        // crafting/kinetics/inventory_access_port
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/inventory_access_port"),
                CCBlocks.INVENTORY_ACCESS_PORT.asStack(2),
                "B",
                "C",
                "E",
                'B', AllBlocks.BRASS_CASING.asStack(),
                'C', AllBlocks.CHUTE.asStack(),
                'E', AllItems.ELECTRON_TUBE.asStack());
        // crafting/kinetics/inventory_bridge
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/inventory_bridge"),
                CCBlocks.INVENTORY_BRIDGE.asStack(),
                CCBlocks.INVENTORY_ACCESS_PORT.asStack(),
                CCBlocks.INVENTORY_ACCESS_PORT.asStack());
        // crafting/kinetics/inverted_clutch_from_conversion
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/inverted_clutch_from_conversion"),
                CCBlocks.INVERTED_CLUTCH.asStack(),
                AllBlocks.CLUTCH.asStack());
        // crafting/kinetics/inverted_gearshift_from_conversion
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/inverted_gearshift_from_conversion"),
                CCBlocks.INVERTED_GEARSHIFT.asStack(),
                AllBlocks.GEARSHIFT.asStack());
        // crafting/kinetics/item_silo
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("create_connected/crafting/kinetics/item_silo"),
                CCBlocks.ITEM_SILO.asStack(),
                "BCB",
                'B', getTag(plate, Iron),
                'C', tagKey("forge:barrels/wooden"));
        // crafting/kinetics/item_silo_from_conversion
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/item_silo_from_conversion"),
                CCBlocks.ITEM_SILO.asStack(),
                AllBlocks.ITEM_VAULT.asStack());
        // crafting/kinetics/item_vault_from_conversion
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/item_vault_from_conversion"),
                AllBlocks.ITEM_VAULT.asStack(),
                CCBlocks.ITEM_SILO.asStack());
        // crafting/kinetics/kinetic_battery
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("create_connected/crafting/kinetics/kinetic_battery"),
                CCBlocks.KINETIC_BATTERY.asStack(8),
                " p ",
                " b ",
                "iri",
                'b', AllBlocks.BRASS_CASING.asStack(),
                'i', getTag(plate, Iron),
                'p', AllItems.PRECISION_MECHANISM.asStack(),
                'r', new ItemStack(Items.REDSTONE));
        // crafting/kinetics/kinetic_bridge
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("create_connected/crafting/kinetics/kinetic_bridge"),
                CCBlocks.KINETIC_BRIDGE.asStack(),
                " b ",
                "scs",
                " b ",
                'b', AllBlocks.BRASS_CASING.asStack(),
                'c', AllBlocks.CLUTCH.asStack(),
                's', AllBlocks.SHAFT.asStack());
        // crafting/kinetics/large_crank_wheel
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/large_crank_wheel"),
                CCBlocks.LARGE_CRANK_WHEEL.asStack(),
                AllBlocks.HAND_CRANK.asStack(),
                AllBlocks.LARGE_COGWHEEL.asStack());
        // crafting/kinetics/linked_transmitter_from_conversion
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/linked_transmitter_from_conversion"),
                CCItems.LINKED_TRANSMITTER.asStack(),
                AllBlocks.REDSTONE_LINK.asStack());
        // crafting/kinetics/overstress_clutch
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/overstress_clutch"),
                CCBlocks.OVERSTRESS_CLUTCH.asStack(),
                AllBlocks.ANDESITE_CASING.asStack(),
                AllBlocks.SHAFT.asStack(),
                getTag(plate, Iron),
                AllItems.ELECTRON_TUBE.asStack());
        // crafting/kinetics/parallel_gearbox
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/parallel_gearbox"),
                CCBlocks.PARALLEL_GEARBOX.asStack(),
                AllBlocks.GEARBOX.asStack(),
                AllBlocks.LARGE_COGWHEEL.asStack());
        // crafting/kinetics/parallel_gearbox_from_conversion
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/parallel_gearbox_from_conversion"),
                CCBlocks.PARALLEL_GEARBOX.asStack(),
                CCItems.VERTICAL_PARALLEL_GEARBOX.asStack());
        // crafting/kinetics/redstone_link_from_conversion
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/redstone_link_from_conversion"),
                AllBlocks.REDSTONE_LINK.asStack(),
                CCItems.LINKED_TRANSMITTER.asStack());
        // crafting/kinetics/redstone_link_wildcard
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/redstone_link_wildcard"),
                CCItems.REDSTONE_LINK_WILDCARD.asStack(),
                item("create:transmitter"),
                AllItems.CRAFTER_SLOT_COVER.asStack());
        // crafting/kinetics/sequenced_pulse_generator
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/sequenced_pulse_generator"),
                CCBlocks.SEQUENCED_PULSE_GENERATOR.asStack(),
                "EC ",
                "EBT",
                "SSS",
                'B', getTag(plate, Brass),
                'C', CCItems.CONTROL_CHIP.asStack(),
                'E', AllItems.ELECTRON_TUBE.asStack(),
                'S', tagKey("forge:stone"),
                'T', new ItemStack(Items.REDSTONE_TORCH));
        // crafting/kinetics/six_way_gearbox
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("create_connected/crafting/kinetics/six_way_gearbox"),
                CCBlocks.SIX_WAY_GEARBOX.asStack(),
                "lc ",
                "csc",
                " cl",
                'c', AllBlocks.COGWHEEL.asStack(),
                'l', AllBlocks.LARGE_COGWHEEL.asStack(),
                's', AllBlocks.ANDESITE_CASING.asStack());
        // crafting/kinetics/six_way_gearbox_from_conversion
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/six_way_gearbox_from_conversion"),
                CCBlocks.SIX_WAY_GEARBOX.asStack(),
                CCItems.VERTICAL_SIX_WAY_GEARBOX.asStack());
        // crafting/kinetics/six_way_gearbox_from_gearbox
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/six_way_gearbox_from_gearbox"),
                CCBlocks.SIX_WAY_GEARBOX.asStack(),
                AllBlocks.GEARBOX.asStack(),
                AllBlocks.LARGE_COGWHEEL.asStack(),
                AllBlocks.LARGE_COGWHEEL.asStack());
        // crafting/kinetics/six_way_gearbox_from_parallel
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/six_way_gearbox_from_parallel"),
                CCBlocks.SIX_WAY_GEARBOX.asStack(),
                CCBlocks.PARALLEL_GEARBOX.asStack(),
                AllBlocks.LARGE_COGWHEEL.asStack());
        // crafting/kinetics/vertical_brass_gearbox_from_conversion
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/vertical_brass_gearbox_from_conversion"),
                CCItems.VERTICAL_BRASS_GEARBOX.asStack(),
                CCBlocks.BRASS_GEARBOX.asStack());
        // crafting/kinetics/vertical_parallel_gearbox_from_conversion
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/vertical_parallel_gearbox_from_conversion"),
                CCItems.VERTICAL_PARALLEL_GEARBOX.asStack(),
                CCBlocks.PARALLEL_GEARBOX.asStack());
        // crafting/kinetics/vertical_six_way_gearbox_from_conversion
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/kinetics/vertical_six_way_gearbox_from_conversion"),
                CCItems.VERTICAL_SIX_WAY_GEARBOX.asStack(),
                CCBlocks.SIX_WAY_GEARBOX.asStack());
        // ===== crafting/palettes =====
        // crafting/palettes/copycat_beam
        SingleItemRecipeBuilder
                .stonecutting(Ingredient.of(tagKey("forge:ingots/zinc")), RecipeCategory.BUILDING_BLOCKS,
                        CCBlocks.COPYCAT_BEAM.get(), 4)
                .save(provider, CTNHCore.id("create_connected/crafting/palettes/copycat_beam"));
        // crafting/palettes/copycat_block
        SingleItemRecipeBuilder
                .stonecutting(Ingredient.of(tagKey("forge:ingots/zinc")), RecipeCategory.BUILDING_BLOCKS,
                        CCBlocks.COPYCAT_BLOCK.get(), 1)
                .save(provider, CTNHCore.id("create_connected/crafting/palettes/copycat_block"));
        // crafting/palettes/copycat_block_from_slabs
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("create_connected/crafting/palettes/copycat_block_from_slabs"),
                CCBlocks.COPYCAT_BLOCK.asStack(),
                "s",
                "s",
                's', CCBlocks.COPYCAT_SLAB.asStack());
        // crafting/palettes/copycat_board
        SingleItemRecipeBuilder
                .stonecutting(Ingredient.of(tagKey("forge:ingots/zinc")), RecipeCategory.BUILDING_BLOCKS,
                        CCBlocks.COPYCAT_BOARD.get(), 8)
                .save(provider, CTNHCore.id("create_connected/crafting/palettes/copycat_board"));
        // crafting/palettes/copycat_box
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("create_connected/crafting/palettes/copycat_box"),
                CCItems.COPYCAT_BOX.asStack(),
                "ss ",
                "s s",
                " ss",
                's', CCBlocks.COPYCAT_BOARD.asStack());
        // crafting/palettes/copycat_catwalk
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("create_connected/crafting/palettes/copycat_catwalk"),
                CCItems.COPYCAT_CATWALK.asStack(),
                "s s",
                " s ",
                's', CCBlocks.COPYCAT_BOARD.asStack());
        // crafting/palettes/copycat_fence
        SingleItemRecipeBuilder
                .stonecutting(Ingredient.of(tagKey("forge:ingots/zinc")), RecipeCategory.BUILDING_BLOCKS,
                        CCBlocks.COPYCAT_FENCE.get(), 1)
                .save(provider, CTNHCore.id("create_connected/crafting/palettes/copycat_fence"));
        // crafting/palettes/copycat_fence_gate
        SingleItemRecipeBuilder
                .stonecutting(Ingredient.of(tagKey("forge:ingots/zinc")), RecipeCategory.BUILDING_BLOCKS,
                        CCBlocks.COPYCAT_FENCE_GATE.get(), 1)
                .save(provider, CTNHCore.id("create_connected/crafting/palettes/copycat_fence_gate"));
        // crafting/palettes/copycat_slab
        SingleItemRecipeBuilder
                .stonecutting(Ingredient.of(tagKey("forge:ingots/zinc")), RecipeCategory.BUILDING_BLOCKS,
                        CCBlocks.COPYCAT_SLAB.get(), 2)
                .save(provider, CTNHCore.id("create_connected/crafting/palettes/copycat_slab"));
        // crafting/palettes/copycat_slab_from_beams
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("create_connected/crafting/palettes/copycat_slab_from_beams"),
                CCBlocks.COPYCAT_SLAB.asStack(),
                "ss",
                's', CCBlocks.COPYCAT_BEAM.asStack());
        // crafting/palettes/copycat_slab_from_panels
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("create_connected/crafting/palettes/copycat_slab_from_panels"),
                CCBlocks.COPYCAT_SLAB.asStack(),
                "p",
                "p",
                'p', AllBlocks.COPYCAT_PANEL.asStack());
        // crafting/palettes/copycat_slab_from_steps
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("create_connected/crafting/palettes/copycat_slab_from_steps"),
                CCBlocks.COPYCAT_SLAB.asStack(),
                "ss",
                's', AllBlocks.COPYCAT_STEP.asStack());
        // crafting/palettes/copycat_stairs
        SingleItemRecipeBuilder
                .stonecutting(Ingredient.of(tagKey("forge:ingots/zinc")), RecipeCategory.BUILDING_BLOCKS,
                        CCBlocks.COPYCAT_STAIRS.get(), 1)
                .save(provider, CTNHCore.id("create_connected/crafting/palettes/copycat_stairs"));
        // crafting/palettes/copycat_step_from_conversion
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/palettes/copycat_step_from_conversion"),
                AllBlocks.COPYCAT_STEP.asStack(),
                CCBlocks.COPYCAT_VERTICAL_STEP.asStack());
        // crafting/palettes/copycat_vertical_step
        SingleItemRecipeBuilder
                .stonecutting(Ingredient.of(tagKey("forge:ingots/zinc")), RecipeCategory.BUILDING_BLOCKS,
                        CCBlocks.COPYCAT_VERTICAL_STEP.get(), 4)
                .save(provider, CTNHCore.id("create_connected/crafting/palettes/copycat_vertical_step"));
        // crafting/palettes/copycat_vertical_step_from_conversion
        VanillaRecipeHelper.addShapelessRecipe(provider,
                CTNHCore.id("create_connected/crafting/palettes/copycat_vertical_step_from_conversion"),
                CCBlocks.COPYCAT_VERTICAL_STEP.asStack(),
                AllBlocks.COPYCAT_STEP.asStack());
        // crafting/palettes/copycat_wall
        SingleItemRecipeBuilder
                .stonecutting(Ingredient.of(tagKey("forge:ingots/zinc")), RecipeCategory.BUILDING_BLOCKS,
                        CCBlocks.COPYCAT_WALL.get(), 1)
                .save(provider, CTNHCore.id("create_connected/crafting/palettes/copycat_wall"));
        // ===== cutting =====
        // cutting/shaft
        CuttingRecipeBuilder.builder(CTNHCore.id("create_connected/shaft"))
                .input(AllBlocks.SHAFT.asStack())
                .result(CCBlocks.SHEAR_PIN.asStack())
                .save(provider);
        // ===== filling =====
        // filling/fan_blasting_catalyst
        FillingRecipeBuilder.builder(CTNHCore.id("create_connected/filling/fan_blasting_catalyst"))
                .input(CCBlocks.EMPTY_FAN_CATALYST.asStack())
                .inputFluid("minecraft:lava", 1000)
                .result(CCBlocks.FAN_BLASTING_CATALYST.asStack())
                .save(provider);
        // filling/fan_splashing_catalyst
        FillingRecipeBuilder.builder(CTNHCore.id("create_connected/filling/fan_splashing_catalyst"))
                .input(CCBlocks.EMPTY_FAN_CATALYST.asStack())
                .inputFluid("minecraft:water", 1000)
                .result(CCBlocks.FAN_SPLASHING_CATALYST.asStack())
                .save(provider);
        // ===== item_application =====
        // item_application/blasting_catalyst_from_empty
        ItemApplicationRecipeBuilder.builder(CTNHCore.id("create_connected/blasting_catalyst_from_empty"))
                .input(CCBlocks.EMPTY_FAN_CATALYST.asStack())
                .input(new ItemStack(Items.LAVA_BUCKET))
                .result(CCBlocks.FAN_BLASTING_CATALYST.asStack())
                .save(provider);
        // item_application/haunting_catalyst_from_empty
        ItemApplicationRecipeBuilder.builder(CTNHCore.id("create_connected/haunting_catalyst_from_empty"))
                .input(CCBlocks.EMPTY_FAN_CATALYST.asStack())
                .input(new ItemStack(Items.SOUL_SAND))
                .result(CCBlocks.FAN_HAUNTING_CATALYST.asStack())
                .save(provider);
        // item_application/smoking_catalyst_from_empty
        ItemApplicationRecipeBuilder.builder(CTNHCore.id("create_connected/smoking_catalyst_from_empty"))
                .input(CCBlocks.EMPTY_FAN_CATALYST.asStack())
                .input(new ItemStack(Items.NETHERRACK))
                .result(CCBlocks.FAN_SMOKING_CATALYST.asStack())
                .save(provider);
        // item_application/splashing_catalyst_from_empty
        ItemApplicationRecipeBuilder.builder(CTNHCore.id("create_connected/splashing_catalyst_from_empty"))
                .input(CCBlocks.EMPTY_FAN_CATALYST.asStack())
                .input(new ItemStack(Items.WATER_BUCKET))
                .result(CCBlocks.FAN_SPLASHING_CATALYST.asStack())
                .save(provider);
        // ===== sequenced_assembly =====
        // sequenced_assembly/control_chip
        SequencedAssemblyRecipeBuilder.builder(CTNHCore.id("create_connected/control_chip"))
                .input(getTag(plate, Gold))
                .transitional(CCItems.INCOMPLETE_CONTROL_CHIP.asStack())
                .result(CCItems.CONTROL_CHIP.asStack())
                .result(new ItemStack(Items.REDSTONE))
                .result(AllItems.ELECTRON_TUBE.asStack())
                .result(AllItems.GOLDEN_SHEET.asStack())
                .result(new ItemStack(Items.GOLD_NUGGET))
                .result(AllItems.IRON_SHEET.asStack())
                .result(AllItems.CRUSHED_GOLD.asStack())
                .result(new ItemStack(Items.QUARTZ))
                .result(new ItemStack(Items.COMPASS))
                .loops(3)
                .deploying(AllItems.ELECTRON_TUBE.asStack())
                .deploying(new ItemStack(Items.REDSTONE))
                .pressing()
                .save(provider);
    }
}
