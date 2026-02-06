package io.github.cpearl0.ctnhcore.client;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.client.renderer.DynamicCasingRender;
import io.github.cpearl0.ctnhcore.client.renderer.HyperPlasmaTurbineRender;
import io.github.cpearl0.ctnhcore.client.renderer.MartialMoralityEyeRender;
import io.github.cpearl0.ctnhcore.common.CommonProxy;

import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderManager;

// @Mod.EventBusSubscriber(modid = CTNHCore.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientProxy extends CommonProxy {

    public ClientProxy() {
        super();
        init();
    }

    public static void init() {
        DynamicRenderManager.register(CTNHCore.id("martial_morality_eye"), MartialMoralityEyeRender.TYPE);
        DynamicRenderManager.register(CTNHCore.id("hyper_plasma_turbine"), HyperPlasmaTurbineRender.TYPE);
        DynamicRenderManager.register(CTNHCore.id("dynamic_casing"), DynamicCasingRender.TYPE);
    }
}
