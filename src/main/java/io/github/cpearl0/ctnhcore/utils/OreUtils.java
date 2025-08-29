package io.github.cpearl0.ctnhcore.utils;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import java.util.Map;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHMaterials.*;

public class OreUtils {
    public static Map<Material, Material> ORE_REPLACEMENTS = Map.of(
            Platinum, PlatinumMetal,
            Palladium, PalladiumMetal,
            Naquadah, NaquadahOxideMixture,
            NaquadahEnriched, EnrichedNaquadahOxideMixture,
            Naquadria, NaquadriaOxideMixture);
}
