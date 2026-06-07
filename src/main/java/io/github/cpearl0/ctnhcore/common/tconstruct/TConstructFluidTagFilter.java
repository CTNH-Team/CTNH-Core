package io.github.cpearl0.ctnhcore.common.tconstruct;

import net.minecraft.resources.ResourceLocation;

import java.util.Set;

public final class TConstructFluidTagFilter {

    private static final Set<String> BLOCKED_MATERIALS = Set.of(
            "precious_alloy", "tin", "silver", "zinc", "nickel", "lead", "beryllium",
            "molybdenum", "brass", "gold", "iron", "bronze", "copper", "cobalt",
            "manganese", "slag", "steel", "aluminium", "uranium", "osmium", "invar",
            "eleectrum", "platinum", "tungsten", "rose_gold", "electrum");

    private TConstructFluidTagFilter() {}

    public static boolean shouldSkipDynamicFluidTag(ResourceLocation location) {
        if (!"forge".equals(location.getNamespace())) {
            return false;
        }
        String path = location.getPath();
        if (!path.startsWith("tags/fluids/") || !path.endsWith(".json")) {
            return false;
        }
        String tagPath = path.substring("tags/fluids/".length(), path.length() - ".json".length());
        return !tagPath.contains("/") && isBlockedMaterialPath(tagPath);
    }

    public static boolean isBlockedMaterialPath(String path) {
        return BLOCKED_MATERIALS.contains(path);
    }
}
