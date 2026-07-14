package io.github.cpearl0.ctnhcore.event;

import io.github.cpearl0.ctnhcore.CTNHCore;

import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Prefix;
import com.unrealdinnerbone.javd.JAVDRegistry;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.Arrays;

@Prefix("tooltip")
@Mod.EventBusSubscriber(modid = CTNHCore.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeClientEventHandler {

    @CN({
            "虚空维度具有如下特性：",
            " - 不会自然生成生物",
            " - 玩家无条件获得创造飞行",
            " - ME无线访问点无距离限制",
            " - 光伏、风力发电机无法工作"
    })
    @EN({
            "The Void Dimension has the following features:",
            " - No mobs spawn naturally",
            " - Players are granted creative flight unconditionally",
            " - ME Wireless Access Points have no distance limit",
            " - Solar panels and wind generators do not function"
    })
    static Lang[] void_feature;

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        if (event.getItemStack().is(JAVDRegistry.PORTAL_BLOCK_ITEM.get())) {
            Arrays.stream(void_feature).forEach(t -> event.getToolTip().add(t.translate()));
        }
        appendMigratedTooltips(event);
    }

    private static void appendMigratedTooltips(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(stack.getItem());
        if (itemId == null) return;

        switch (itemId.toString()) {
            case "gtceu:hv_emitter" -> addTooltip(event,
                    Component.literal("在坠星位标仪式中使用此物品作为祭品可以召唤陨石"));
            case "gtceu:psionic_medulla_gem" -> addTooltip(event,
                    Component.literal("通过血魔法邪恶的生灵萃取仪式萃取艾尔夫海姆精灵获取"));
            case "bloodmagic:doubt_bucket" -> addTooltip(event,
                    Component.literal("通过击杀浸泡在生命源质的生物，将其困扰注入生命源质之中获得"));
            case "gtceu:nan_certificate" -> addTooltip(event, ChatFormatting.YELLOW,
                    Component.literal("1.2.1版本毕业证明"));
            case "hostilenetworks:deep_learner" -> addTooltip(event, ChatFormatting.YELLOW,
                    Component.literal("最高可将模型提升至[基础]等级"));
            case "hostilenetworks:sim_chamber" -> addTooltip(event, ChatFormatting.YELLOW,
                    Component.literal("可放入等级为[缺陷]或[基础]的模型，最高可将模型提升至[进阶]，不会产生预测产物"));
            case "gtceu:nightvision_goggles" -> addTooltip(event, ChatFormatting.GRAY,
                    Component.literal("用GregTech的 [Armor Mode Switch] 键开启"));
            case "gtceu:lp_steam_macerator", "gtceu:hp_steam_macerator", "gtceu:steam_grinder", "gtceu:lv_macerator", "gtceu:mv_macerator", "ctpp:smashing_factory" -> addTooltip(
                    event, ChatFormatting.DARK_RED, Component.literal("研磨副产物只能通过HV及以上的电压获得！"));
            case "tconstruct:crafting_station" -> {
                addTooltip(event, ChatFormatting.DARK_RED,
                        Component.literal("在服务器内，请不要将工作站连接到任何带有堆叠升级的容器中."));
                addTooltip(event, ChatFormatting.DARK_RED,
                        Component.literal("此举动不会吞物品，但会导致工作站无法识别到大多出堆叠大于64的物品."));
            }
            case "enderio:conduit_probe" -> addTooltip(event, ChatFormatting.GRAY,
                    Component.literal("下蹲滚轮或通过按键绑定切换模式，仅复制粘贴模式可用"));
            default -> {}
        }
    }

    private static void addTooltip(ItemTooltipEvent event, Component text) {
        event.getToolTip().add(text);
    }

    private static void addTooltip(ItemTooltipEvent event, ChatFormatting formatting, Component text) {
        event.getToolTip().add(text.copy().withStyle(formatting));
    }
}
