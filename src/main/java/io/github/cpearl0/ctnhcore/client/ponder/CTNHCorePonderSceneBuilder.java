package io.github.cpearl0.ctnhcore.client.ponder;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.GTCEu;

import net.createmod.ponder.api.scene.SceneBuilder;

import tech.vixhentx.mcmod.ctnhlib.client.ponder.CTNHPonderSceneBuilder;

import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class CTNHCorePonderSceneBuilder extends CTNHPonderSceneBuilder {

    public CTNHCorePonderSceneBuilder(SceneBuilder builder) {
        super(builder, CTNHCore.MODID, CTNHCorePonderSceneBuilder::registerLang);
    }

    private static void registerLang(String key, String en, String cn) {
        if (GTCEu.isDataGen()) {
            REGISTRATE.genLang(key, en, cn);
        }
    }
}
