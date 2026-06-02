package io.github.cpearl0.ctnhcore.data.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

import com.tterrag.registrate.providers.RegistrateTagsProvider;
import fr.lucreeper74.createmetallurgy.registries.CMFluids;

import java.util.Objects;

public class FluidTypeTags {

    public static void init(RegistrateTagsProvider<Fluid> provider) {
        create(provider, fluidTag("forge", "steel"), CMFluids.MOLTEN_STEEL.get());
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
