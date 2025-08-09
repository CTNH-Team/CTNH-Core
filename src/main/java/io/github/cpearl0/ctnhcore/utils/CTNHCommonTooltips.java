package io.github.cpearl0.ctnhcore.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class CTNHCommonTooltips {
    public static MutableComponent PARALLEL_HATCH = Component.translatable("ctnh.common_tooltip.parallel_hatch").withStyle(ChatFormatting.GOLD);
    public static MutableComponent SUBTICK_PARALLEL = Component.translatable("ctnh.common_tooltip.subtick_overclock").withStyle(ChatFormatting.YELLOW);
    public static MutableComponent PERFECT_OVERCLOCK = Component.translatable("ctnh.common_tooltip.perfect_overclock").withStyle(ChatFormatting.GREEN);
    public static MutableComponent[] STEEL_MACHINE = new MutableComponent[] {
            Component.translatable("ctnh.common_tooltip.steel_machine.0"),
            //Component.translatable("ctnh.common_tooltip.steel_machine.1"),
            PERFECT_OVERCLOCK
    };
    public static MutableComponent[] MANA_MACHINE = new MutableComponent[]{
            Component.translatable("ctnh.common_tooltip.mana_machine.0").withStyle(ChatFormatting.GRAY),
            //Component.translatable("ctnh.common_tooltip.mana_machine.1"),
            Component.translatable("ctnh.common_tooltip.mana_machine.2"),
            Component.translatable("ctnh.common_tooltip.mana_machine.3"),
            Component.translatable("ctnh.common_tooltip.mana_machine.4")
    };

    public static MutableComponent[] MANA_GENERATOR = new MutableComponent[] {
            Component.translatable("ctnh.common_tooltip.mana_generator.0"),
            //Component.translatable("ctnh.common_tooltip.mana_generator.1"),
            Component.translatable("ctnh.common_tooltip.mana_generator.2"),
            Component.translatable("ctnh.common_tooltip.mana_generator.3")
    };
    public static MutableComponent BASIC_MANA_CONSUME = Component.translatable("ctnh.common_tooltip.basic_mana_consume");
    public static MutableComponent ADVANCED_MANA_CONSUME = Component.translatable("ctnh.common_tooltip.advanced_mana_consume");
    public static MutableComponent SUPER_MANA_CONSUME = Component.translatable("ctnh.common_tooltip.super_mana_consume");
    public static MutableComponent[] ZENITH_MACHINE = new MutableComponent[] {
            Component.translatable("ctnh.common_tooltip.zenith_machine.0").withStyle(ChatFormatting.DARK_PURPLE),
            Component.translatable("ctnh.common_tooltip.zenith_machine.1"),
            Component.translatable("ctnh.common_tooltip.zenith_machine.2"),
            CTNHCommonTooltips.SUPER_MANA_CONSUME,
            CTNHCommonTooltips.PERFECT_OVERCLOCK,
            //Component.translatable("ctnh.common_tooltip.mana_machine.1"),
            Component.translatable("ctnh.common_tooltip.mana_machine.2")
    };
}
