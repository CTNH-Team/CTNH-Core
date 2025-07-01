package io.github.cpearl0.ctnhcore.registry.machines.multiblock;

import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.kinetic.SteamBoomerMachine;

import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class MultiblocksC {
    public static void init() {

    }
    public static final MultiblockMachineDefinition STEAM_BOOMER = REGISTRATE.multiblock("steam_boomer", SteamBoomerMachine::new).register();
}
