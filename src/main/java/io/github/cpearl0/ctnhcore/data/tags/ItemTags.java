package io.github.cpearl0.ctnhcore.data.tags;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.tag.TagUtil;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import appeng.api.features.P2PTunnelAttunement;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import tech.luckyblock.mcmod.ctnhenergy.registry.CEItems;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;

public class ItemTags {

    public static void init(RegistrateTagsProvider<Item> provider) {
        var attunement = P2PTunnelAttunement.getAttunementTag(CEItems.EU_P2P);
        // batteries 子标签
        provider.addTag(attunement)
                .addTag(TagUtil.createModItemTag("batteries"));

        // GTCEu 导线和线缆 → EU P2P attunement tag（addOptional 写 registry key）
        addGTCablesAndWiresToTag(provider, attunement);

        // GTCEu 挤出模具 → vintageimprovements curving_heads tag
        provider.addTag(curvingHeadsTag())
                .add(TagEntry.optionalTag(TagUtil.createModItemTag("small_gear_extruder_mold").location()))
                .add(TagEntry.optionalTag(TagUtil.createModItemTag("gear_extruder_mold").location()));
    }

    private static void addGTCablesAndWiresToTag(RegistrateTagsProvider<Item> provider, TagKey<Item> attunement) {
        for (var mat : GTCEuAPI.materialManager.getRegisteredMaterials()) {
            if (mat.hasProperty(PropertyKey.WIRE)) {
                registerWireIfPresent(provider, attunement, mat);
            }
        }
    }

    private static void registerWireIfPresent(RegistrateTagsProvider<Item> provider, TagKey<Item> tag,
                                              Material mat) {
        // 导线
        addOptionalItem(provider, tag, ChemicalHelper.get(wireGtSingle, mat));
        // 线缆（超导无 cable）
        if (!mat.getProperty(PropertyKey.WIRE).isSuperconductor()) {
            addOptionalItem(provider, tag, ChemicalHelper.get(cableGtSingle, mat));
        }
    }

    private static void addOptionalItem(RegistrateTagsProvider<Item> provider, TagKey<Item> tag, ItemStack stack) {
        if (stack != null && !stack.isEmpty()) {
            var key = ForgeRegistries.ITEMS.getKey(stack.getItem());
            if (key != null) provider.addTag(tag).addOptional(key);
        }
    }

    private static TagKey<Item> curvingHeadsTag() {
        return TagKey.create(Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath("vintageimprovements", "curving_heads"));
    }
}
