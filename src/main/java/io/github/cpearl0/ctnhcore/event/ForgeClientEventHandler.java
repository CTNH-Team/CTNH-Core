package io.github.cpearl0.ctnhcore.event;

import dev.emi.emi.search.EmiSearch;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.utils.emi.TooltipBakeQueue;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CTNHCore.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeClientEventHandler {
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        TooltipBakeQueue queue = TooltipBakeQueue.INSTANCE;
        if (queue != null && !TooltipBakeQueue.ready) {
            boolean done = queue.tick();
            if (done) {
                TooltipBakeQueue.ready = true;
                queue.tooltips.generate();
                EmiSearch.tooltips = queue.tooltips;
            }
        }
    }
}
