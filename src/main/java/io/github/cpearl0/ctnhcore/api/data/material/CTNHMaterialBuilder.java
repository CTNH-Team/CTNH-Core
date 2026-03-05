package io.github.cpearl0.ctnhcore.api.data.material;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import net.minecraft.resources.ResourceLocation;

import lombok.experimental.Accessors;

@Accessors(chain = true, fluent = true)
public class CTNHMaterialBuilder extends Material.Builder {

    public CTNHMaterialBuilder(ResourceLocation resourceLocation) {
        super(resourceLocation);
    }

    public static CTNHMaterialBuilder Builder(String name) {
        return new CTNHMaterialBuilder(CTNHCore.id(name));
    }
}
