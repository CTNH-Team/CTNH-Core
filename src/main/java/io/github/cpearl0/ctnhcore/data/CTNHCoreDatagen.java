package io.github.cpearl0.ctnhcore.data;

import io.github.cpearl0.ctnhcore.data.lang.old.ChineseLangHandler;
import io.github.cpearl0.ctnhcore.data.lang.old.EnglishLangHandler;
import io.github.cpearl0.ctnhcore.data.tags.FluidTypeTags;
import io.github.cpearl0.ctnhcore.data.tags.StoneTags;
import io.github.cpearl0.ctnhcore.registry.CTNHRegistration;

import com.tterrag.registrate.providers.ProviderType;

import static tech.vixhentx.mcmod.ctnhlib.registrate.data.ProviderTypes.CNLANG;

public class CTNHCoreDatagen {

    // public static final ProviderType<RegistrateCNLangProvider> CNLANG = ProviderType.register("ctnh_cn_lang", (p, e)
    // -> new RegistrateCNLangProvider(p, e.getGenerator().getPackOutput()));
    // public static final ProviderType<CTNHLangProvider> CTNHLANG = ProviderType.register("ctnh_lang", (p, e) -> new
    // CTNHLangProvider(p, e.getGenerator().getPackOutput()));
    public static void init() {
        CTNHRegistration.REGISTRATE.addDataGenerator(ProviderType.LANG, EnglishLangHandler::init);
        CTNHRegistration.REGISTRATE.addDataGenerator(CNLANG, ChineseLangHandler::init);
        CTNHRegistration.REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, StoneTags::init);
        CTNHRegistration.REGISTRATE.addDataGenerator(ProviderType.FLUID_TAGS, FluidTypeTags::init);
    }
}
