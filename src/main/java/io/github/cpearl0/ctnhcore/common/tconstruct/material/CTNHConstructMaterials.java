package io.github.cpearl0.ctnhcore.common.tconstruct.material;

import io.github.cpearl0.ctnhcore.CTNHCore;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CTNHConstructMaterials {

    public static final class Ids {
        public static final MaterialId SNOW_STEEL = id("snow_steel");

        private static MaterialId id(String name) {
            return new MaterialId(CTNHCore.MODID, name);
        }
    }
}