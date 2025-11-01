package io.github.cpearl0.ctnhcore.registry;

import io.github.cpearl0.ctnhcore.registry.machines.multiblock.*;

import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class CTNHMultiblockMachines {
    static {
        REGISTRATE.creativeModeTab(() -> CTNHCreativeModeTabs.MACHINE);
    }
    public static void init() {
        MultiblocksA.init();
        MultiblocksB.init();
        MultiblocksC.init();
        GTNNMultiblocks.init();
        Mechanical.init();
    }
}