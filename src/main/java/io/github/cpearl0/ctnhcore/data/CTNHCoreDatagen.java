package io.github.cpearl0.ctnhcore.data;

import io.github.cpearl0.ctnhcore.data.lang.old.ChineseLangHandler;
import io.github.cpearl0.ctnhcore.data.lang.old.EnglishLangHandler;
import io.github.cpearl0.ctnhcore.data.tags.FluidTypeTags;
import io.github.cpearl0.ctnhcore.data.tags.StoneTags;

import com.tterrag.registrate.providers.ProviderType;

import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;
import static tech.vixhentx.mcmod.ctnhlib.registrate.data.ProviderTypes.CNLANG;

public class CTNHCoreDatagen {

    public static void init() {
        REGISTRATE.addLangProcessor();
        REGISTRATE.addDataGenerator(ProviderType.LANG, EnglishLangHandler::init);
        REGISTRATE.addDataGenerator(CNLANG, ChineseLangHandler::init);
        REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, StoneTags::init);
        REGISTRATE.addDataGenerator(ProviderType.FLUID_TAGS, FluidTypeTags::init);
    }
}
