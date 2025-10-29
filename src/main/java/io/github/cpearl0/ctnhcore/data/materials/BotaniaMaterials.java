package io.github.cpearl0.ctnhcore.data.materials;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import io.github.cpearl0.ctnhcore.CTNHCore;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.METALLIC;
import static io.github.cpearl0.ctnhcore.registry.CTNHMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class BotaniaMaterials {
    public static void init() {
        ManaSteel = REGISTRATE.material(GTCEu.id("mana_steel"))
                .cnlang("魔力钢")
                .ingot()
                .fluid()
                .color(0x438FFE)
                .secondaryColor(0x3962D7)
                .iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE,
                        GENERATE_ROD,
                        GENERATE_GEAR,
                        GENERATE_SMALL_GEAR, GENERATE_BOLT_SCREW, GENERATE_FOIL, GENERATE_FRAME, GENERATE_RING
                        )
                .cableProperties(GTValues.V[GTValues.LV], 6, 1)
                .buildAndRegister();
        TerraSteel = REGISTRATE.material(CTNHCore.id("terra_steel"))
                .cnlang("泰拉钢")
                .ingot()
                .fluid()
                .color(0x63D12F)
                .secondaryColor(0x2AB73A)
                .iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE,
                        GENERATE_ROD,
                        GENERATE_GEAR,
                        GENERATE_SMALL_GEAR, GENERATE_BOLT_SCREW, GENERATE_FOIL, GENERATE_FRAME, GENERATE_RING)
                .cableProperties(GTValues.V[GTValues.HV], 6, 1)
                .buildAndRegister();
        Elementium = REGISTRATE.material(CTNHCore.id("elementium"))
                .cnlang("源质钢")
                .ingot()
                .fluid()
                .color(0xf762a3)
                .secondaryColor(0xf768d1)
                .iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE,
                        GENERATE_ROD,
                        GENERATE_GEAR,
                        GENERATE_SMALL_GEAR, GENERATE_BOLT_SCREW, GENERATE_FOIL, GENERATE_FRAME, GENERATE_RING)
                .cableProperties(GTValues.V[GTValues.MV], 6, 1)
                .buildAndRegister();
        AlfSteel = REGISTRATE.material(CTNHCore.id("alfsteel"))
                .cnlang("精灵钢")
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_BOLT_SCREW, GENERATE_FOIL, GENERATE_FRAME, GENERATE_RING)
                .ingot()
                .color(0xFD9D31)
                .iconSet(METALLIC)
                .cableProperties(GTValues.V[GTValues.EV], 6, 1, false)
                .buildAndRegister();
    }
}
