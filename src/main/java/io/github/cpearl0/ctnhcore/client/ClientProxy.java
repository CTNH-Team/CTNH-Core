package io.github.cpearl0.ctnhcore.client;

import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderManager;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.client.renderer.*;
import io.github.cpearl0.ctnhcore.common.CommonProxy;


public class ClientProxy extends CommonProxy {
    public ClientProxy() {
        super();
        init();
    }

    public static void init() {
        DynamicRenderManager.register(CTNHCore.id("mana_condenser"), ManaCondenserRender.TYPE);
        DynamicRenderManager.register(CTNHCore.id("eternal_garden"), EternalGardenRender.TYPE);
        DynamicRenderManager.register(CTNHCore.id("martial_morality_eye"), MartialMoralityEyeRender.TYPE);
        DynamicRenderManager.register(CTNHCore.id("hyper_plasma_turbine"), HyperPlasmaTurbineRender.TYPE);
        DynamicRenderManager.register(CTNHCore.id("dynamic_casing"), DynamicCasingRender.TYPE);
    }

}
