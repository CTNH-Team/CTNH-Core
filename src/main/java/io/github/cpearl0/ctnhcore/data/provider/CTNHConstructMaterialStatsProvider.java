package io.github.cpearl0.ctnhcore.common.tconstruct.tool.stats;

import io.github.cpearl0.ctnhcore.common.tconstruct.material.CTNHConstructMaterials;
import io.github.cpearl0.ctnhcore.common.tconstruct.tool.CTNHConstructPartMaterialStats;
import io.github.cpearl0.ctnhcore.data.provider.CTNHConstructMaterialsDataProvider;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.tools.data.material.MaterialIds;
import slimeknights.tconstruct.tools.stats.*;

import static net.minecraft.world.item.Tiers.*;

public final class CTNHConstructMaterialStats extends AbstractMaterialStatsDataProvider {
    public CTNHConstructMaterialStats(PackOutput packOutput) {
        super(packOutput, CTNHConstructMaterialsDataProvider.INSTANCE);
    }

    @Override
    public String getName() {
        return "CTNHConstruct Material Stats";
    }

    @Override
    protected void addMaterialStats() {
        addMaterialStats(MaterialIds.wood, CTNHConstructPartMaterialStats.values());

        addMaterialStats(CTNHConstructMaterials.Ids.SNOW_STEEL,
                new HeadMaterialStats(220, 5.5F, NETHERITE, 1.5F),
                new HandleMaterialStats(0.15F, 0F, 0F, -0.05F),
                new LimbMaterialStats(220, -0.2F, 0.1F, 0.0F),
                new GripMaterialStats(0.1F, 0.0F, 1.5F),
                StatlessMaterialStats.BINDING,
                StatlessMaterialStats.ARROW_HEAD
        );
    }
}