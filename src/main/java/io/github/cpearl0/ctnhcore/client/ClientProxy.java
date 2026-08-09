package io.github.cpearl0.ctnhcore.client;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.client.model.ModelDefinition;
import io.github.cpearl0.ctnhcore.client.ponder.CTNHCorePonderPlugin;
import io.github.cpearl0.ctnhcore.client.renderer.ArcBlockRender;
import io.github.cpearl0.ctnhcore.client.renderer.DynamicCasingRender;
import io.github.cpearl0.ctnhcore.client.renderer.HyperPlasmaTurbineRender;
import io.github.cpearl0.ctnhcore.client.renderer.MartialMoralityEyeRender;
import io.github.cpearl0.ctnhcore.client.util.SnowOverlayQuadOffset;
import io.github.cpearl0.ctnhcore.common.CommonProxy;
import io.github.cpearl0.ctnhcore.registry.CTNHBlockEntities;
import io.github.cpearl0.ctnhcore.registry.CTNHModelLayers;
import io.github.cpearl0.ctnhcore.registry.CTNHRegistration;

import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderManager;

import com.lowdragmc.lowdraglib.client.renderer.ATESRRendererProvider;

import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientProxy extends CommonProxy {

    @SuppressWarnings("removal")
    public ClientProxy() {
        super();
        init();
    }

    public static void init() {
        DynamicRenderManager.register(CTNHCore.id("martial_morality_eye"), MartialMoralityEyeRender.TYPE);
        DynamicRenderManager.register(CTNHCore.id("hyper_plasma_turbine"), HyperPlasmaTurbineRender.TYPE);
        DynamicRenderManager.register(CTNHCore.id("dynamic_casing"), DynamicCasingRender.TYPE);
        DynamicRenderManager.register(CTNHCore.id("arc_generator"), ArcBlockRender.TYPE);
    }

    @SubscribeEvent
    public void onClientSetupEvent(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            PonderIndex.addPlugin(new CTNHCorePonderPlugin());
        });
    }

    @SubscribeEvent
    public void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        CTNHCore.LOGGER.info("Registering Models...");
        CTNHModelLayers.init();
        var models = CTNHRegistration.REGISTRATE.getModels();
        for (ModelDefinition model : models) {
            event.registerLayerDefinition(model.LAYER_LOCATION, model.createBodyLayer);
        }
    }

    @SubscribeEvent
    public void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        CTNHCore.LOGGER.info("Registering External Renderers...");
        event.registerBlockEntityRenderer(CTNHBlockEntities.TURBINE_ROTOR.get(), ATESRRendererProvider::new);
    }

    @SubscribeEvent
    public void onRegisterClientReloadListeners(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(new ResourceManagerReloadListener() {

            @Override
            public void onResourceManagerReload(ResourceManager resourceManager) {
                SnowOverlayQuadOffset.clearOffsetCache();
            }
        });
    }

    @SubscribeEvent
    public void onBakingCompleted(ModelEvent.BakingCompleted event) {
        SnowOverlayQuadOffset.clearOffsetCache();
    }
}
