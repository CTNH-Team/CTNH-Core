package io.github.cpearl0.ctnhcore.event;

import io.github.cpearl0.ctnhcore.CTNHCore;

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
    }
}
