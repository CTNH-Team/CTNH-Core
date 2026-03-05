package io.github.cpearl0.ctnhcore.common.tconstruct.materials;

import io.github.cpearl0.ctnhcore.CTNHCore;

import net.minecraft.network.chat.Component;

import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.library.materials.stats.IMaterialStats;
import slimeknights.tconstruct.library.materials.stats.MaterialStatType;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;

import java.util.List;

public enum CTNHConstructPartMaterialStats implements IMaterialStats {
    ;

    private static final List<Component> LOCALIZED = List
            .of(IMaterialStats.makeTooltip(TConstruct.getResource("extra.no_stats")));
    private static final List<Component> DESCRIPTION = List.of(Component.empty());

    public static void register() {
        for (CTNHConstructPartMaterialStats value : CTNHConstructPartMaterialStats.values()) {
            MaterialRegistry.getInstance().registerStatType(value.getType());
        }
    }

    private final MaterialStatType<CTNHConstructPartMaterialStats> type;

    CTNHConstructPartMaterialStats(String name) {
        this.type = MaterialStatType.singleton(new MaterialStatsId(CTNHCore.asResource(name)), this);
    }

    @Override
    public MaterialStatType<?> getType() {
        return this.type;
    }

    @Override
    public List<Component> getLocalizedInfo() {
        return LOCALIZED;
    }

    @Override
    public List<Component> getLocalizedDescriptions() {
        return DESCRIPTION;
    }

    @Override
    public void apply(ModifierStatsBuilder builder, float scale) {}
}
