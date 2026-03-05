package io.github.cpearl0.ctnhcore.integration.emi;

import net.minecraft.world.item.Item;

import com.enderio.base.common.init.EIOItems;
import com.enderio.machines.common.blockentity.capacitorbank.CapacitorTier;
import com.enderio.machines.common.blockentity.solar.SolarPanelTier;
import com.enderio.machines.common.init.MachineBlocks;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiInitRegistry;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.stack.EmiStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.gregtechceu.gtceu.common.data.machines.GTMultiMachines.PRIMITIVE_BLAST_FURNACE;

@EmiEntrypoint
public class CTNHCoreEmiPlugin implements EmiPlugin {

    public static List<Supplier<? extends Item>> disabled = new ArrayList<>();

    @Override
    public void register(EmiRegistry emiRegistry) {}

    @Override
    public void initialize(EmiInitRegistry registry) {
        EIODisable();

        for (var item : disabled) {
            registry.disableStack(EmiStack.of(item.get()));
        }
    }

    public static void EIODisable() {
        disabled.addAll(List.of(
                MachineBlocks.FLUID_TANK::asItem,
                MachineBlocks.PRESSURIZED_FLUID_TANK::asItem,
                MachineBlocks.STIRLING_GENERATOR::asItem,
                MachineBlocks.SAG_MILL::asItem,
                MachineBlocks.ALLOY_SMELTER::asItem,
                MachineBlocks.PRIMITIVE_ALLOY_SMELTER::asItem,
                MachineBlocks.STIRLING_GENERATOR::asItem,
                MachineBlocks.SOUL_ENGINE::asItem,
                EIOItems.BASIC_CAPACITOR::asItem,
                EIOItems.DOUBLE_LAYER_CAPACITOR::asItem,
                EIOItems.OCTADIC_CAPACITOR::asItem,
                PRIMITIVE_BLAST_FURNACE::getItem));
        for (SolarPanelTier tier : SolarPanelTier.values()) {
            disabled.add(MachineBlocks.SOLAR_PANELS.get(tier)::asItem);
        }
        for (CapacitorTier tier : CapacitorTier.values()) {
            disabled.add(MachineBlocks.CAPACITOR_BANKS.get(tier)::asItem);
        }
    }
}
