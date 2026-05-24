package io.github.cpearl0.ctnhcore.data.provider;

import net.minecraft.data.PackOutput;

import slimeknights.tconstruct.data.resource.TiCDynamicResourceGenerator;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider;

public class CTNHConstructMaterialPartTextureGenerator extends MaterialPartTextureGenerator {

    public CTNHConstructMaterialPartTextureGenerator(PackOutput packOutput) {
        super(packOutput, TiCDynamicResourceGenerator.createExistingFileHelperForAddons(),
                new TinkerPartSpriteProvider(), new CTNHConstructMaterialSpriteProvider());
    }
}
