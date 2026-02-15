package io.github.cpearl0.ctnhcore.data.provider;

import io.github.cpearl0.ctnhcore.common.tconstruct.material.CTNHConstructMaterials;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialRenderInfoProvider;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;

public final class CTNHConstructMaterialRenderInfoProvider extends AbstractMaterialRenderInfoProvider {
    public CTNHConstructMaterialRenderInfoProvider(PackOutput packOutput, AbstractMaterialSpriteProvider spriteProvider, ExistingFileHelper existingFileHelper) {
        super(packOutput, spriteProvider, existingFileHelper);
    }
    @Override
    protected void addMaterialRenderInfo() {
        buildRenderInfo(CTNHConstructMaterials.Ids.SNOW_STEEL).color(0xFFA1A7B1).fallbacks("metal");
    }

    @Override
    public String getName() {
        return "CTNHConstruct Material Render Info";
    }
}
