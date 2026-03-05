package io.github.cpearl0.ctnhcore.registry;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.tconstruct.modifier.Global_Traveller;

import slimeknights.tconstruct.common.TinkerModule;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

public class CTNHConstructModifier extends TinkerModule {

    public static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(CTNHCore.MODID);

    public static final class Ids {

        public static final ModifierId GLOBAL_TRAVELLER = id("global_traveller");
        public static final ModifierId SnowCity = id("snow_city");

        private static ModifierId id(String id) {
            return ModifierId.tryBuild(CTNHCore.MODID, id);
        }
    }

    public static final StaticModifier<Modifier> GLOBAL_TRAVELLER = MODIFIERS.register("global_traveller",
            Global_Traveller::new);
}
