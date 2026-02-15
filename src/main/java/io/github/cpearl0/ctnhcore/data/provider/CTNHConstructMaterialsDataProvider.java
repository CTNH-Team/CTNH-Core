package io.github.cpearl0.ctnhcore.data.provider;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.tconstruct.material.CTNHConstructMaterials;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.json.JsonRedirect;

public final class CTNHConstructMaterialsDataProvider extends AbstractMaterialDataProvider {
    public static CTNHConstructMaterialsDataProvider INSTANCE;

    public CTNHConstructMaterialsDataProvider(PackOutput packOutput) {
        super(packOutput);
        INSTANCE = this;
    }

    @Override
    public String getName() {
        return "CTNHConstruct Materials";
    }

    @Override
    protected void addMaterials() {
        addMaterial(CTNHConstructMaterials.Ids.SNOW_STEEL, 2, ORDER_GENERAL, true
        );
    }
}
