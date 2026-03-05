package io.github.cpearl0.ctnhcore.data.provider;

import io.github.cpearl0.ctnhcore.common.tconstruct.material.CTNHConstructMaterials;

import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToColorMapping;

import static slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider.INGOT;
import static slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider.STORAGE_BLOCK;

public class CTNHConstructMaterialSpriteProvider extends AbstractMaterialSpriteProvider {

    @Override
    public String getName() {
        return "";
    }

    @Override
    protected void addAllMaterials() {
        buildMaterial(CTNHConstructMaterials.Ids.SNOW_STEEL)
                .meleeHarvest().ranged().armor().statType(INGOT, STORAGE_BLOCK)
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builder()
                        .addARGB(0, 0xFF000000)
                        .addARGB(63, 0xFF1B4D60)
                        .addARGB(102, 0xFF286B77)
                        .addARGB(140, 0xFF5093A0)
                        .addARGB(178, 0xFF7DBCC6)
                        .addARGB(216, 0xFF4BD7DD)
                        .addARGB(255, 0xFFA6F4F9)
                        .build());
    }
}
