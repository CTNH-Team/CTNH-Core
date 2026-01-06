package io.github.cpearl0.ctnhcore.utils;

import earth.terrarium.adastra.AdAstra;
import net.minecraft.resources.ResourceLocation;

public class ModUtils {

    public static ResourceLocation AdAstraRL(String path) {return ResourceLocation.tryBuild(AdAstra.MOD_ID, path);}
}
