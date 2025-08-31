package io.github.cpearl0.ctnhcore.data.materials;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import io.github.cpearl0.ctnhcore.api.data.material.CTNHPropertyKeys;
import io.github.cpearl0.ctnhcore.common.item.CatalystBehavior;
import io.github.cpearl0.ctnhcore.common.item.TagPrefixBehavior;
import io.github.cpearl0.ctnhcore.registry.CTNHCreativeModeTabs;

import static com.gregtechceu.gtceu.common.data.GTItems.attach;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;
import static io.github.cpearl0.ctnhcore.registry.CTNHTagPrefixes.catalyst;

public class ChemicalItems {
    static {
        REGISTRATE.creativeModeTab(() -> CTNHCreativeModeTabs.ITEM);
    }
    public static Table<TagPrefix, Material, ItemEntry<ComponentItem>> CATALYST_ITEMS;

    private static void generateCatalystItems() {
        var builder = new ImmutableTable.Builder<TagPrefix, Material, ItemEntry<ComponentItem>>();
        for (var registry : GTCEuAPI.materialManager.getRegistries()) {
            for (var material : registry.getAllMaterials()) {
                if (material.hasProperty(CTNHPropertyKeys.CATALYST)) {
                    builder.put(catalyst,
                                material,
                                REGISTRATE.item(catalyst.idPattern().format(material.getName()), ComponentItem::create)
                            .setData(ProviderType.LANG, NonNullBiConsumer.noop())
                            .transform(GTItems.unificationItem(catalyst, material))
                            .properties(p -> p.stacksTo(catalyst.maxStackSize()))
                            .model(NonNullBiConsumer.noop())
                            .color(() -> TagPrefixBehavior::tintColor)
                            .onRegister(
                            attach(
                                    new CatalystBehavior(
                                            material.getProperty(CTNHPropertyKeys.CATALYST).maxDurability
                                    )
                            )
                    )
                            .onRegister(attach(new TagPrefixBehavior(catalyst, material)))
                            .register());
                }
            }
        }
        CATALYST_ITEMS = builder.build();
    }
    public static void init() {
        generateCatalystItems();
    }

}
