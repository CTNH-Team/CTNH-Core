package io.github.cpearl0.ctnhcore.registry;

import io.github.cpearl0.ctnhcore.CTNHCore;

import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.ModifierId;

public class CTNHConstructModifier {

    public static final class Ids {

        public static final ModifierId GLOBAL_TRAVELLER = ModifierId.tryBuild(TConstruct.MOD_ID, "global_traveller");
        public static final ModifierId SnowCity = id("snow_city");

        private static ModifierId id(String id) {
            return ModifierId.tryBuild(CTNHCore.MODID, id);
        }
    }
}
