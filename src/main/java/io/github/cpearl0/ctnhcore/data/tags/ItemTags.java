package io.github.cpearl0.ctnhcore.data.tags;

import com.gregtechceu.gtceu.api.data.tag.TagUtil;

import net.minecraft.world.item.Item;

import appeng.api.features.P2PTunnelAttunement;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import tech.luckyblock.mcmod.ctnhenergy.registry.CEItems;

public class ItemTags {

    public static void init(RegistrateTagsProvider<Item> provider) {
        provider.addTag(P2PTunnelAttunement.getAttunementTag(CEItems.EU_P2P))
                .addTag(TagUtil.createModItemTag("batteries"));
    }
}
