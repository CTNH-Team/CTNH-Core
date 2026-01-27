package io.github.cpearl0.ctnhcore.event;

import dev.shadowsoffire.attributeslib.api.ALObjects;
import io.github.cpearl0.ctnhcore.CTNHCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = CTNHCore.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DimensionFlightHandler {

    private static final UUID FLIGHT_UUID =
            UUID.fromString("9d7cbb6e-4c62-4f0c-b6c4-8e66f1d8a111");

    // 你的目标维度
    private static final ResourceKey<Level> TARGET_DIM =
            ResourceKey.create(Registries.DIMENSION, ResourceLocation.tryBuild("javd", "void"));

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!(event.player instanceof ServerPlayer player)) return;

        boolean inTargetDim = player.level().dimension().equals(TARGET_DIM);

        var attr = player.getAttribute(
                ALObjects.Attributes.CREATIVE_FLIGHT.get()
        );
        if (attr == null) return;

        AttributeModifier modifier = new AttributeModifier(
                FLIGHT_UUID,
                "dimension_flight",
                1.0D,
                AttributeModifier.Operation.ADDITION
        );

        if (inTargetDim) {
            if (!attr.hasModifier(modifier)) {
                attr.addPermanentModifier(modifier);
            }
        } else {
            if (attr.getModifier(FLIGHT_UUID) != null) {
                attr.removeModifier(FLIGHT_UUID);
            }
        }
    }
}
