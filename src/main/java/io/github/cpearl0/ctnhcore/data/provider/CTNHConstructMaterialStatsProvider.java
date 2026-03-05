package io.github.cpearl0.ctnhcore.data.provider;

import io.github.cpearl0.ctnhcore.common.tconstruct.material.CTNHConstructMaterials;

import net.minecraft.data.PackOutput;

import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.tools.stats.*;

import static net.minecraft.world.item.Tiers.*;

public final class CTNHConstructMaterialStatsProvider extends AbstractMaterialStatsDataProvider {

    public CTNHConstructMaterialStatsProvider(PackOutput packOutput) {
        super(packOutput, CTNHConstructMaterialsDataProvider.INSTANCE);
    }

    @Override
    public String getName() {
        return "CTNHConstruct Material Stats";
    }

    @Override
    protected void addMaterialStats() {
        addMaterialStats(CTNHConstructMaterials.Ids.SNOW_STEEL,
                new HeadMaterialStats(114514, 19.19F, NETHERITE, 8.1F),
                new HandleMaterialStats(1, 2, 2, 3),
                new LimbMaterialStats(1, 2, 2, 2),
                new GripMaterialStats(1, 2, 3),
                new PlatingMaterialStats(PlatingMaterialStats.HELMET, 114514, 19.19F, 8.1F, 1),
                new PlatingMaterialStats(PlatingMaterialStats.CHESTPLATE, 114514, 19.19F, 8.1F, 1),
                new PlatingMaterialStats(PlatingMaterialStats.LEGGINGS, 114514, 19.19F, 8.1F, 1),
                new PlatingMaterialStats(PlatingMaterialStats.BOOTS, 114514, 19.19F, 8.1F, 1),
                new PlatingMaterialStats(PlatingMaterialStats.SHIELD, 114514, 1.25F, 1.25F, 1.25F),
                StatlessMaterialStats.BINDING,
                StatlessMaterialStats.ARROW_HEAD,
                StatlessMaterialStats.SHIELD_CORE);
    }
}
