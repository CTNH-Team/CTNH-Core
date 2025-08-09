package io.github.cpearl0.ctnhcore.api.data.material;

import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;

public class CTNHPropertyKeys {
    public static void init() {}
    public static final PropertyKey<NuclearProperty> NUCLEAR = new PropertyKey<>("nuclear", NuclearProperty.class);
    public static final PropertyKey<CatalystProperty> CATALYST = new PropertyKey<>("catalyst", CatalystProperty.class);
}
