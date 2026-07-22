package io.github.cpearl0.ctnhcore.registry;

import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksA;

import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;

import net.minecraft.world.item.CreativeModeTab;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.tterrag.registrate.util.entry.RegistryEntry;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class CTNHCreativeModeTabs {

    @CN("CTNH方块")
    @EN("CTNH Blocks")
    public static Lang itemGroupBlock;

    @CN("CTNH物品")
    @EN("CTNH Items")
    public static Lang itemGroupItem;

    @CN("CTNH机器")
    @EN("CTNH Machines")
    public static Lang itemGroupMachine;

    public static RegistryEntry<CreativeModeTab> MACHINE = REGISTRATE.defaultCreativeTab("machine",
            builder -> builder
                    .displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator("machine", REGISTRATE))
                    .icon(MultiblocksA.ASTRONOMICAL_OBSERVATORY::asStack)
                    .title(itemGroupMachine.translate())
                    .build())
            .register();
    public static RegistryEntry<CreativeModeTab> ITEM = REGISTRATE.defaultCreativeTab("item",
            builder -> builder.displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator("item", REGISTRATE))
                    .icon(CTNHItems.ASTRONOMY_CIRCUIT_1::asStack)
                    .title(itemGroupItem.translate())
                    .build())
            .register();
    public static RegistryEntry<CreativeModeTab> BLOCK = REGISTRATE.defaultCreativeTab("block",
            builder -> builder.displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator("block", REGISTRATE))
                    .icon(CTNHBlocks.CASING_REFLECT_LIGHT::asStack)
                    .title(itemGroupBlock.translate())
                    .build())
            .register();

    public static void init() {}
}
