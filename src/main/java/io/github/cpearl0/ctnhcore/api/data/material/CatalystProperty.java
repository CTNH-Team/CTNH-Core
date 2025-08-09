package io.github.cpearl0.ctnhcore.api.data.material;

import com.gregtechceu.gtceu.api.data.chemical.material.properties.IMaterialProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.MaterialProperties;


public class CatalystProperty implements IMaterialProperty {
    public CatalystProperty(int maxDurability) {
        this.maxDurability = maxDurability;
    }
    public int maxDurability;
    @Override
    public void verifyProperty(MaterialProperties materialProperties) {

    }
}
