package io.github.cpearl0.ctnhcore.data.tags;

import com.gregtechceu.gtceu.api.data.tag.TagUtil;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import appeng.api.features.P2PTunnelAttunement;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import tech.luckyblock.mcmod.ctnhenergy.registry.CEItems;

public class ItemTags {

    public static void init(RegistrateTagsProvider<Item> provider) {
        provider.addTag(P2PTunnelAttunement.getAttunementTag(CEItems.EU_P2P))
                .addTag(TagUtil.createModItemTag("batteries"));
        // 迁移自 kubejs：添加 GTCEu 挤出模具到 vintageimprovements 冲压头标签
        provider.addTag(curvingHeadsTag())
                .add(TagUtil.createModItemTag("small_gear_extruder_mold"))
                .add(TagUtil.createModItemTag("gear_extruder_mold"));
    }

    private static TagKey<Item> curvingHeadsTag() {
        return TagKey.create(Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath("vintageimprovements", "curving_heads"));
    }
}
