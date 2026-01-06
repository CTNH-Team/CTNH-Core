package io.github.cpearl0.ctnhcore.data.tags;

import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class FluidTypeTags {

    public static void init(RegistrateTagsProvider<Fluid> provider) {

    }
    public static void create(RegistrateTagsProvider<Fluid> provider, TagKey<Fluid> tagKey, Fluid... rls) {
        var builder = provider.addTag(tagKey);
        for (Fluid fluid : rls) {
            builder.addOptional(Objects.requireNonNull(ForgeRegistries.FLUIDS.getKey(fluid)));
        }
    }
}
