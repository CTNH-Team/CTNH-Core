package io.github.cpearl0.ctnhcore.data.lang;

import io.github.cpearl0.ctnhcore.client.ponder.CTNHCorePonderPlugin;

import net.createmod.ponder.foundation.PonderIndex;
import net.createmod.ponder.foundation.registration.PonderLocalization;

public class PonderLang {

    public static void init() {
        PonderIndex.addPlugin(new CTNHCorePonderPlugin());
        PonderIndex.registerAll();
        if (PonderIndex.getLangAccess() instanceof PonderLocalization localization) {
            localization.generateSceneLang();
        }
    }
}
