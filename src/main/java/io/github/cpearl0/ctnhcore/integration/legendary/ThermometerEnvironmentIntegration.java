package io.github.cpearl0.ctnhcore.integration.legendary;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.lowdragmc.lowdraglib.gui.factory.HeldItemUIFactory;

import net.minecraft.ChatFormatting;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import sfiomn.legendarysurvivaloverhaul.registry.ItemRegistry;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

/** 将 LSO 温度计的物品事件接入环境检测器。 */
@Mod.EventBusSubscriber(modid = CTNHCore.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class ThermometerEnvironmentIntegration {

    @Key("tooltip.ctnhcore.thermometer.environment")
    @CN("对空气右键打开环境检测面板")
    @EN("Right-click in the air to open the environment panel")
    private static Lang usage;

    @Key("tooltip.ctnhcore.thermometer.readings")
    @CN("每秒刷新读数，按 Esc 关闭")
    @EN("Updates every second; press Esc to close")
    private static Lang readings;

    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        if (!event.getItemStack().is(ItemRegistry.THERMOMETER.get())) return;

        // 两侧均消费本次使用，避免继续处理另一只手；由服务端打开并同步面板。
        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.sidedSuccess(event.getLevel().isClientSide));
        if (player instanceof ServerPlayer serverPlayer &&
                !player.getCooldowns().isOnCooldown(ItemRegistry.THERMOMETER.get())) {
            HeldItemUIFactory.INSTANCE.openUI(serverPlayer, event.getHand());
            player.getCooldowns().addCooldown(ItemRegistry.THERMOMETER.get(), 20);
        }
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        if (!event.getItemStack().is(ItemRegistry.THERMOMETER.get())) return;
        event.getToolTip().add(usage.translate().withStyle(ChatFormatting.GRAY));
        event.getToolTip().add(readings.translate().withStyle(ChatFormatting.DARK_GRAY));
    }

    private ThermometerEnvironmentIntegration() {}
}
