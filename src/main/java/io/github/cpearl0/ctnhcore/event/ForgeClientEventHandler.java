package io.github.cpearl0.ctnhcore.event;

import com.gregtechceu.gtceu.api.GTValues;
import com.unrealdinnerbone.javd.JAVDRegistry;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.utils.emi.TooltipBakeQueue;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import dev.emi.emi.search.EmiSearch;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.CN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.EN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.Prefix;

import java.util.Arrays;
import java.util.List;

@Prefix("tooltip")
@Mod.EventBusSubscriber(modid = CTNHCore.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeClientEventHandler {

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        TooltipBakeQueue queue = TooltipBakeQueue.INSTANCE;
        if (queue != null && !TooltipBakeQueue.ready && GTValues.CLIENT_TIME % 20 == 0) {
            boolean done = queue.tick();
            if (done) {
                TooltipBakeQueue.ready = true;
                queue.tooltips.generate();
                EmiSearch.tooltips = queue.tooltips;
            }
        }
    }

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
        if(event.getItemStack().is(JAVDRegistry.PORTAL_BLOCK_ITEM.get())){
            Arrays.stream(void_feature).forEach(t -> event.getToolTip().add(t.translate()));
        }
    }
}
