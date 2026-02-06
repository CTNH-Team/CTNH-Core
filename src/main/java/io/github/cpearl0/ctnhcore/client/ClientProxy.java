package io.github.cpearl0.ctnhcore.client;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.client.ponder.CTNHPonderPlugin;
import io.github.cpearl0.ctnhcore.client.renderer.DynamicCasingRender;
import io.github.cpearl0.ctnhcore.client.renderer.HyperPlasmaTurbineRender;
import io.github.cpearl0.ctnhcore.client.renderer.MartialMoralityEyeRender;
import io.github.cpearl0.ctnhcore.common.CommonProxy;

import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderManager;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientProxy extends CommonProxy {

    @SuppressWarnings("removal")
    public ClientProxy() {
        super();
        init();
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(ClientProxy::onClientSetupEvent);
    }

    public static void init() {
        DynamicRenderManager.register(CTNHCore.id("martial_morality_eye"), MartialMoralityEyeRender.TYPE);
        DynamicRenderManager.register(CTNHCore.id("hyper_plasma_turbine"), HyperPlasmaTurbineRender.TYPE);
        DynamicRenderManager.register(CTNHCore.id("dynamic_casing"), DynamicCasingRender.TYPE);
    }

    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            PonderIndex.addPlugin(new CTNHPonderPlugin());
        });
    }
}
