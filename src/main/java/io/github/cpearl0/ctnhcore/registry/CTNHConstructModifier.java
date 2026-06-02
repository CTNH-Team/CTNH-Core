package io.github.cpearl0.ctnhcore.registry;

import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.ModifierId;

public class CTNHConstructModifier {

    public static final class Ids {

        public static final ModifierId GLOBAL_TRAVELLER = id("global_traveller");
        public static final ModifierId SNOW_CITY = id("snow_city");
        public static final ModifierId FORTIFICATION = id("fortification");

        private static ModifierId id(String id) {
            return ModifierId.tryBuild(TConstruct.MOD_ID, id);
        }
    }
}
