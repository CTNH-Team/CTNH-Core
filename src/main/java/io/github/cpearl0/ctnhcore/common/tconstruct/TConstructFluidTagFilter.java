package io.github.cpearl0.ctnhcore.common.tconstruct;

import net.minecraft.resources.ResourceLocation;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class TConstructFluidTagFilter {

    private static final Set<String> BLOCKED_MATERIALS = Set.of(
            "precious_alloy", "tin", "silver", "zinc", "nickel", "lead", "beryllium",
            "molybdenum", "brass", "gold", "iron", "bronze", "copper", "cobalt",
            "manganese", "slag", "steel", "aluminium", "uranium", "osmium", "invar",
            "eleectrum", "platinum", "tungsten", "rose_gold", "electrum");

    /**
     * Materials that have both tconstruct and gtceu fluid counterparts.
     * Derived from compare_report analysis of gtceu:fluid_solidifier/* recipe inputs.
     * If a gtceu fluid doesn't actually exist at runtime, the replacement is skipped.
     */
    private static final Set<String> REPLACEABLE_MATERIALS = Set.of(
            "aluminium", "bronze", "cobalt", "copper", "electrum", "glass", "gold", "invar", "iron", "lead", "osmium",
            "rose_gold", "silver", "steel");

    /**
     * Mapping from forge fluid tag to gtceu fluid id, used by RecipeRemoval
     * to replace fluid inputs in GTCEu fluid_solidifier recipes.
     * Key format: "#forge:material_name" (the tag that GTCEu FluidIngredient references).
     * Value format: "gtceu:material_name" (the target fluid id).
     */
    public static final Map<String, String> FORGE_TAG_TO_GTCEU_FLUID_MAP;

    static {
        Map<String, String> map = new LinkedHashMap<>();
        for (String mat : REPLACEABLE_MATERIALS) {
            map.put("tconstruct:" + mat, "gtceu:" + mat);
        }
        map.put("tconstruct:honey", "create:honey");
        FORGE_TAG_TO_GTCEU_FLUID_MAP = Collections.unmodifiableMap(map);
    }

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
