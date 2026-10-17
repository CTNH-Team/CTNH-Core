package io.github.cpearl0.ctnhcore.event;

// BuildTaskManager.java

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.api.Pattern.AsynBlockPattern;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BuildTaskManager {

    private static final BuildTaskManager INSTANCE = new BuildTaskManager();
    private final Map<UUID, AsynBlockPattern> activeTasks = new ConcurrentHashMap<>();
    private static final int MAX_TICK_TIME_NS = 2_000_000; // 2ms per tick

    public static BuildTaskManager getInstance() {
        return INSTANCE;
    }

    public void registerTask(Player player, AsynBlockPattern task) {
        activeTasks.put(player.getUUID(), task);
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            getInstance().tickAllTasks();
        }
    }

    // @SubscribeEvent
    // public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
    // if (event.getEntity() instanceof ServerPlayer) {
    // getInstance().cancelPlayerTasks(event.getEntity().getUUID());
    // }
    // }

    private void tickAllTasks() {
        long startTime = System.nanoTime();
        Iterator<Map.Entry<UUID, AsynBlockPattern>> it = activeTasks.entrySet().iterator();

        while (it.hasNext() && System.nanoTime() - startTime < MAX_TICK_TIME_NS) {
            Map.Entry<UUID, AsynBlockPattern> entry = it.next();
            AsynBlockPattern task = entry.getValue();

            if (task.isCompleted()) {
                it.remove();
            } else {
                try {
                    task.tick();
                } catch (RuntimeException e) {
                    // 兜底：单个建造任务的意外异常不该把整个服务端 tick 打挂。
                    it.remove();
                    CTNHCore.LOGGER.error("Auto-build task of {} failed, dropped it", entry.getKey(), e);
                }
            }
        }
    }

    // private void cancelPlayerTasks(UUID playerId) {
    // AsynBlockPattern task = activeTasks.remove(playerId);
    // if (task != null) {
    // task.onCancelled();
    // }
    // }
}
