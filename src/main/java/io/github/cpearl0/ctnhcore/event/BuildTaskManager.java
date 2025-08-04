package io.github.cpearl0.ctnhcore.event;

// BuildTaskManager.java
import io.github.cpearl0.ctnhcore.api.Pattern.AsynBlockPattern;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;
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

//    @SubscribeEvent
//    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
//        if (event.getEntity() instanceof ServerPlayer) {
//            getInstance().cancelPlayerTasks(event.getEntity().getUUID());
//        }
//    }

    private void tickAllTasks() {
        long startTime = System.nanoTime();
        Iterator<Map.Entry<UUID, AsynBlockPattern>> it = activeTasks.entrySet().iterator();

        while (it.hasNext() && System.nanoTime() - startTime < MAX_TICK_TIME_NS) {
            Map.Entry<UUID, AsynBlockPattern> entry = it.next();
            AsynBlockPattern task = entry.getValue();

            if (task.isCompleted()) {
                it.remove();
            } else {
                task.tick();
            }
        }
    }

//    private void cancelPlayerTasks(UUID playerId) {
//        AsynBlockPattern task = activeTasks.remove(playerId);
//        if (task != null) {
//            task.onCancelled();
//        }
//    }
}
