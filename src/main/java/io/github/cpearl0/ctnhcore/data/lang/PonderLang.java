package io.github.cpearl0.ctnhcore.data.lang;

import io.github.cpearl0.ctnhcore.client.ponder.CTNHPonderPlugin;

import net.createmod.ponder.foundation.PonderIndex;
import net.createmod.ponder.foundation.registration.PonderLocalization;

public class PonderLang {

    public static void init() {
        PonderIndex.addPlugin(new CTNHPonderPlugin());
        PonderIndex.registerAll();
        if (PonderIndex.getLangAccess() instanceof PonderLocalization localization) {
            localization.generateSceneLang();
        }
    }
}
