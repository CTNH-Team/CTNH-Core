package io.github.cpearl0.ctnhcore.registry;

import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlag;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;

public class CTNHMaterialFlags {
    public static final MaterialFlag GENERATE_HYPER_ROTOR = new MaterialFlag.Builder("generate_hyper_rotor")
            .requireFlags(MaterialFlags.GENERATE_PLATE)
            .build();
}
