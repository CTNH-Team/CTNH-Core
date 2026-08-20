package io.github.cpearl0.ctnhcore.registry.ores;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHWorldgenLayers;

import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import com.magicbee.ctnhmana.registry.CMMaterials;
import net.minecraft.util.valueproviders.UniformInt;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import static com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE;
import static com.gregtechceu.gtceu.common.data.GTOres.create;
import static io.github.cpearl0.ctnhcore.registry.CTNHWorlds.*;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.*;

public class AlfheimOres {

    // ==================== Alfheim ====================

    @Key("ctnhcore:mana_fused_vein")
    @CN("蕴魔矿脉")
    @EN("Mana-Fused Vein")
    public static Lang ctnhManaFusedVein;

    @Key("gtceu.jei.ore_vein.mana_fused_vein")
    @CN("蕴魔矿脉")
    @EN("Mana-Fused Vein")
    public static Lang gtceuManaFusedVein;

    public static GTOreDefinition MANA_FUSED_VEIN = create(CTNHCore.id("mana_fused_vein"),
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
