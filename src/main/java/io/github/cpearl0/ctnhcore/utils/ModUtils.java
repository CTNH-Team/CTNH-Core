package io.github.cpearl0.ctnhcore.utils;

import net.minecraft.resources.ResourceLocation;

import earth.terrarium.adastra.AdAstra;

public class ModUtils {

    public static ResourceLocation AdAstraRL(String path) {
        return ResourceLocation.tryBuild(AdAstra.MOD_ID, path);
    }
}
