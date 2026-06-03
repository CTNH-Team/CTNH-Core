package io.github.cpearl0.ctnhcore.data.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

import com.tterrag.registrate.providers.RegistrateTagsProvider;
import fr.lucreeper74.createmetallurgy.registries.CMFluids;

import java.util.List;
import java.util.Objects;

public class FluidTypeTags {

    /** 迁移自 kubejs：需要从 tconstruct 标签中移除的 GTCEu 流体材料列表 */
    private static final List<String> TCONSTRUCT_MATERIALS = List.of(
            "precious_alloy", "tin", "silver", "zinc", "nickel", "lead", "beryllium",
            "molybdenum", "brass", "gold", "iron", "bronze", "copper", "cobalt",
            "manganese", "slag", "steel", "aluminium", "uranium", "osmium", "invar",
            "electrum", "platinum", "tungsten", "rose_gold", "glass");

    public static void init(RegistrateTagsProvider<Fluid> provider) {
        // 迁移自 kubejs：event.add('forge:steel','createmetallurgy:molten_steel')
        create(provider, fluidTag("forge", "steel"), CMFluids.MOLTEN_STEEL.get());
        // 迁移自 kubejs：event.removeAll('tconstruct:<material>') + event.remove('tconstruct:glass','tconstruct:glass')
        clearTConstructTags(provider);
    }

    private static void clearTConstructTags(RegistrateTagsProvider<Fluid> provider) {
        for (String material : TCONSTRUCT_MATERIALS) {
            TagClearHelper.clear(provider,
                    TagKey.create(Registries.FLUID,
                            ResourceLocation.fromNamespaceAndPath("tconstruct", material)));
        }
    }

    private static TagKey<Fluid> fluidTag(String namespace, String path) {
        return TagKey.create(Registries.FLUID, ResourceLocation.fromNamespaceAndPath(namespace, path));
    }

    public static void create(RegistrateTagsProvider<Fluid> provider, TagKey<Fluid> tagKey, Fluid... rls) {
        var builder = provider.addTag(tagKey);
        for (Fluid fluid : rls) {
            builder.addOptional(Objects.requireNonNull(ForgeRegistries.FLUIDS.getKey(fluid)));
        }
    }
}
