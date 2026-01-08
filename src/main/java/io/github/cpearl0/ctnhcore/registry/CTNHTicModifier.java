package io.github.cpearl0.ctnhcore.registry;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.tconstruct.modifier.Global_Traveller;
import slimeknights.tconstruct.common.TinkerModule;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

public class CTNHTicModifier extends TinkerModule {

    public static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(CTNHCore.MODID);

    public static final StaticModifier<Modifier> GLOBAL_TRAVELLER =
            MODIFIERS.register("global_traveller", Global_Traveller::new);
}
