package io.github.cpearl0.ctnhcore.registry;

import io.github.cpearl0.ctnhcore.common.blockentity.TurbineRotorBE;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;

import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class CTNHBlockEntities {

    public static void init() {}

    @SuppressWarnings("unchecked")
    public static final BlockEntityEntry<TurbineRotorBE> TURBINE_ROTOR = REGISTRATE
            .blockEntity("turbine_rotor", TurbineRotorBE::new)
            .validBlocks(CTNHMaterialBlocks.HYPER_ROTOR_BLOCKS.values().toArray(BlockEntry[]::new))
            .validBlocks(() -> CTNHBlocks.HYPER_PLASMA_TURBINE_ROTOR.get())

            .register();
}
