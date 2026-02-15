package io.github.cpearl0.ctnhcore.data.provider;

import io.github.cpearl0.ctnhcore.common.tconstruct.material.CTNHConstructMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHConstructModifier;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.tools.data.ModifierIds;
import slimeknights.tconstruct.tools.data.material.MaterialIds;
import slimeknights.tconstruct.tools.stats.SkullStats;

import static slimeknights.tconstruct.library.materials.MaterialRegistry.*;

public final class CTNHConstructMaterialsTraitsProvider extends AbstractMaterialTraitDataProvider {
    public CTNHConstructMaterialsTraitsProvider(PackOutput packOutput) {
        super(packOutput, CTNHConstructMaterialsDataProvider.INSTANCE);
    }

    @Override
    public String getName() {
        return "CTNHConstruct Material Traits";
    }

    @Override
    protected void addMaterialTraits() {
        addTraits(CTNHConstructMaterials.Ids.SNOW_STEEL, MELEE_HARVEST, CTNHConstructModifier.Ids.GLOBAL_TRAVELLER, ModifierIds.enhanced, ModifierIds.luck, ModifierIds.lightweight, ModifierIds.reach);
    }
}
