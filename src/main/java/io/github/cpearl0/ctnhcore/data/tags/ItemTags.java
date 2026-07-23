package io.github.cpearl0.ctnhcore.data.tags;

import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.data.recipe.CustomTags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import appeng.api.features.P2PTunnelAttunement;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import tech.luckyblock.mcmod.ctnhenergy.registry.CEItems;

import java.util.Arrays;
import java.util.Objects;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;

public class ItemTags {

    public static void init(RegistrateTagsProvider<Item> provider) {
        var attunement = P2PTunnelAttunement.getAttunementTag(CEItems.EU_P2P);

        provider.addTag(attunement)
                .addTag(CustomTags.EV_CIRCUITS);

        // GTCEu 模头
        var appender = provider.addTag(curvingHeadsTag());
        Arrays.stream(GTItems.SHAPE_EXTRUDERS)
                .filter(Objects::nonNull)
                .forEach(mold -> appender.add(mold.getKey()));
    }

    private static TagKey<Item> curvingHeadsTag() {
        return TagKey.create(Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath("vintageimprovements", "curving_heads"));
    }
}
