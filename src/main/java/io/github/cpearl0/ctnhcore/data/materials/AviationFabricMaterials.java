package io.github.cpearl0.ctnhcore.data.materials;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class AviationFabricMaterials {

    public static Material OXYDIANILINE_SLUDGE;
    public static Material OXYDIANILINE;
    public static Material PYROMETILLIC_DIANHYDRIDE;
    public static Material KAPTON_K;
    public static Material KAPTON_E;
    public static Material COBALT_BROMIDE;
    public static Material MANGANESE_BROMIDE;
    public static Material MANGANESE_ACETATE;
    public static Material CO_MN_BR_CATALYST;
    public static Material BIS_TRICHLOROMETHYL_BENZENE;
    public static Material TEREPHTHALIC_ACID;
    public static Material TEREPHTHALOYL_CHLORIDE;
    public static Material NITROANILINE;
    public static Material PARA_PHENYLENEDIAMINE;
    public static Material PARA_ARAMID;
    public static Material FIBER_GLASS;

    public static void init() {
        OXYDIANILINE_SLUDGE = REGISTRATE.material(CTNHCore.id("oxydianiline_sludge"))
                .cnlang("对氨基二苯醚沉降物")
                .liquid()
                .color(0x68D6CB)
                .buildAndRegister();

        OXYDIANILINE = REGISTRATE.material(CTNHCore.id("oxydianiline"))
                .cnlang("对氨基二苯醚")
                .liquid()
                .color(0x87F2E8)
                .buildAndRegister();

        PYROMETILLIC_DIANHYDRIDE = REGISTRATE.material(CTNHCore.id("pyrometillic_dianhydride"))
                .cnlang("均苯四酸二酐")
                .liquid()
                .color(0xE89C9C)
                .buildAndRegister();

        KAPTON_K = REGISTRATE.material(CTNHCore.id("kapton_k"))
                .cnlang("聚酰亚胺K")
                .ingot()
                .liquid()
                .color(0x97F5A5)
                .flags(GENERATE_PLATE, GENERATE_FOIL, GENERATE_ROD, GENERATE_GEAR, GENERATE_BOLT_SCREW)
                .buildAndRegister();

        KAPTON_E = REGISTRATE.material(CTNHCore.id("kapton_e"))
                .cnlang("聚酰亚胺E")
                .ingot()
                .liquid()
                .color(0xF0F597)
                .flags(GENERATE_PLATE, GENERATE_FOIL, GENERATE_ROD, GENERATE_GEAR, GENERATE_BOLT_SCREW)
                .buildAndRegister();

        COBALT_BROMIDE = REGISTRATE.material(CTNHCore.id("cobalt_bromide"))
                .cnlang("溴化钴")
                .liquid()
                .color(0x9983E8)
                .components(Cobalt, 1, Bromine, 2)
                .buildAndRegister();

        MANGANESE_BROMIDE = REGISTRATE.material(CTNHCore.id("manganese_bromide"))
                .cnlang("溴化猛")
                .liquid()
                .color(0xE883E1)
                .components(Manganese, 1, Bromine, 2)
                .buildAndRegister();

        MANGANESE_ACETATE = REGISTRATE.material(CTNHCore.id("manganese_acetate"))
                .cnlang("乙酸锰")
                .liquid()
                .color(0xCDED99)
                .buildAndRegister();

        CO_MN_BR_CATALYST = REGISTRATE.material(CTNHCore.id("co_mn_br_catalyst"))
                .cnlang("钴锰溴催化剂")
                .liquid()
                .color(0xDBE875)
                .buildAndRegister();

        BIS_TRICHLOROMETHYL_BENZENE = REGISTRATE.material(CTNHCore.id("bis_trichloromethyl_benzene"))
                .cnlang("三氯甲苯")
                .liquid()
                .color(0x75BAE8)
                .buildAndRegister();

        TEREPHTHALIC_ACID = REGISTRATE.material(CTNHCore.id("terephthalic_acid"))
                .cnlang("对苯二酸")
                .liquid()
                .color(0x75E8A3)
                .buildAndRegister();

        TEREPHTHALOYL_CHLORIDE = REGISTRATE.material(CTNHCore.id("terephthaloyl_chloride"))
                .cnlang("对苯二酰氯")
                .liquid()
                .color(0x90E0C7)
                .buildAndRegister();

        NITROANILINE = REGISTRATE.material(CTNHCore.id("nitroaniline"))
                .cnlang("硝基苯胺")
                .liquid()
                .color(0xE090A8)
                .buildAndRegister();

        PARA_PHENYLENEDIAMINE = REGISTRATE.material(CTNHCore.id("para_phenylenediamine"))
                .cnlang("对苯二胺")
                .liquid()
                .color(0x4BB34D)
                .buildAndRegister();

        PARA_ARAMID = REGISTRATE.material(CTNHCore.id("para_aramid"))
                .cnlang("对芳纶")
                .ingot()
                .liquid()
                .color(0x30CF7A)
                .flags(GENERATE_PLATE, GENERATE_FOIL, GENERATE_ROD, GENERATE_GEAR, GENERATE_BOLT_SCREW)
                .buildAndRegister();

        FIBER_GLASS = REGISTRATE.material(CTNHCore.id("fiber_glass"))
                .cnlang("玻璃纤维")
                .ingot()
                .liquid()
                .color(0xE0FFFC)
                .flags(GENERATE_FINE_WIRE)
                .buildAndRegister();
    }
}
