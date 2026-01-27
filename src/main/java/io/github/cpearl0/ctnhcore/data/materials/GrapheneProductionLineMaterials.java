package io.github.cpearl0.ctnhcore.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import io.github.cpearl0.ctnhcore.CTNHCore;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.GENERATE_PLATE;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class GrapheneProductionLineMaterials {

    public static Material GRAPHITE_STEAM;
    public static Material GRAPHITE_IR_PLATE;

    public static void init() {
        GRAPHITE_STEAM = REGISTRATE.material(CTNHCore.id("graphite_steam"))
                .cnlang("石墨气")
                .liquid()
                .color(0x000000)
                .buildAndRegister();

        GRAPHITE_IR_PLATE = REGISTRATE.material(CTNHCore.id("graphite_ir_plate"))
                .cnlang("覆石墨烯铱")
                .ingot()
                .color(0x000000)
                .flags(GENERATE_PLATE)
                .buildAndRegister();
    }
}
