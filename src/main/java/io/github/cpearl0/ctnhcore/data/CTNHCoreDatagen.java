package io.github.cpearl0.ctnhcore.data;

import io.github.cpearl0.ctnhcore.data.tags.FluidTypeTags;
import io.github.cpearl0.ctnhcore.data.tags.ItemTags;
import io.github.cpearl0.ctnhcore.data.tags.StoneTags;

import com.tterrag.registrate.providers.ProviderType;

import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class CTNHCoreDatagen {

    public static void init() {
        REGISTRATE.addLangProcessor();
        REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, StoneTags::init);
        REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, ItemTags::init);
    }
}
