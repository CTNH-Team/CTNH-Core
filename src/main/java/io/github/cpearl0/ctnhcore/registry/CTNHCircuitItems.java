package io.github.cpearl0.ctnhcore.registry;

import io.github.cpearl0.ctnhcore.common.item.CircuitBlueprintItem;
import io.github.cpearl0.ctnhcore.common.item.CustomCircuitBoardItem;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import com.tterrag.registrate.util.entry.ItemEntry;

import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

/**
 * 电路板玩法物品注册（设计文档 §二）。
 * 基板与大部分元件直接复用 GTM 原版物品（见 SubstrateTier / CircuitComponent），
 * 这里只注册 GTM 没有的元件（晶振、散热片）以及图纸与成品电路板。
 * 贴图暂用原版纹理占位，正式贴图后续由美术补齐。
 */
public class CTNHCircuitItems {

    static {
        REGISTRATE.creativeModeTab(() -> CTNHCreativeModeTabs.ITEM);
    }

    public static final ItemEntry<Item> CIRCUIT_OSCILLATOR = REGISTRATE
            .item("circuit_oscillator", Item::new)
            .cnlang("晶振")
            .lang("Crystal Oscillator")
            .model((ctx, prov) -> prov.generated(ctx, ResourceLocation.parse("minecraft:item/prismarine_crystals")))
            .register();

    public static final ItemEntry<Item> CIRCUIT_HEATSINK = REGISTRATE
            .item("circuit_heatsink", Item::new)
            .cnlang("散热片")
            .lang("Heatsink")
            .model((ctx, prov) -> prov.generated(ctx, ResourceLocation.parse("minecraft:item/iron_ingot")))
            .register();

    public static final ItemEntry<CircuitBlueprintItem> CIRCUIT_BLUEPRINT = REGISTRATE
            .item("circuit_blueprint", CircuitBlueprintItem::new)
            .cnlang("电路图纸")
            .lang("Circuit Blueprint")
            .model((ctx, prov) -> prov.generated(ctx, ResourceLocation.parse("minecraft:item/paper")))
            .register();

    public static final ItemEntry<CustomCircuitBoardItem> CUSTOM_CIRCUIT_BOARD = REGISTRATE
            .item("custom_circuit_board", CustomCircuitBoardItem::new)
            .cnlang("自定义电路板")
            .lang("Custom Circuit Board")
            .model((ctx, prov) -> prov.generated(ctx, ResourceLocation.parse("minecraft:item/filled_map")))
            .register();

    public static void init() {}
}
