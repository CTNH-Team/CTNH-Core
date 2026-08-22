package io.github.cpearl0.ctnhcore.registry.ores;

import io.github.cpearl0.ctnhcore.registry.CTNHWorldgenLayers;

import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.util.valueproviders.UniformInt;

import com.magicbee.ctnhmana.registry.CMMaterials;

import static io.github.cpearl0.ctnhcore.registry.CTNHOres.create;
import static io.github.cpearl0.ctnhcore.registry.CTNHWorlds.*;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.*;

public class AlfheimOres {

    // ==================== Alfheim ====================

    public static GTOreDefinition MANA_FUSED_VEIN = create("mana_fused_vein",
            "Mana-Fused Vein",
            "蕴魔矿脉",
            vein -> vein
                    .weight(80)
                    .clusterSize(UniformInt.of(30, 40))
                    .density(0.25F)
                    .discardChanceOnAirExposure(0)
                    .layer(CTNHWorldgenLayers.ALFHEIM)
                    .dimensions(ALFHEIM)
                    .heightRangeUniform(20, 40)
                    .layeredVeinGenerator(generator -> generator
                            .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(2).mat(CMMaterials.Fused_Mana).size(2, 4))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Gold).size(1, 1))))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Gold)));

    public static void init() {}
}
