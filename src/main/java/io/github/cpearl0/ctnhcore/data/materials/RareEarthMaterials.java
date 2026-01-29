package io.github.cpearl0.ctnhcore.data.materials;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class RareEarthMaterials {

    public static Material RARE_EARTH_FE_ONE;
    public static Material RARE_EARTH_FE_TWO;
    public static Material RARE_EARTH_INTENSIVE_RESEARCH;
    public static Material RARE_EARTH_MIXTURE;
    public static Material RARE_EARTH_MIXTURE_OH;
    public static Material RARE_EARTH_CRYSTALS;
    public static Material RARE_EARTH_CHLORIDE_BOIL;
    public static Material RARE_EARTH_HIGH_AFFINITY;
    public static Material RARE_EARTH_LOW_AFFINTY;
    public static Material RARE_EARTH_HIGH;
    public static Material RARE_EARTH_LOW;
    public static Material RARE_EARTH_MIDDLE;
    public static Material RARE_EARTH_HIGH_FLUORIDE;
    public static Material RARE_EARTH_MIDDLE_FLUORIDE;
    public static Material RARE_EARTH_LOW_FLUORIDE;
    public static Material RARE_EARTH_LOW_FLUORIDE_STEAM;
    public static Material RARE_EARTH_MIDDLE_FLUORIDE_STEAM;
    public static Material RARE_EARTH_HIGH_FLUORIDE_STEAM;
    public static Material LANTHANUM_CERIUM_PRASEODYMIUM_NEODYMIUM_OXYGEN_MIXTURE;
    public static Material EUROPIUM_GADOLINIUM_TERBIUM_DYSPROSIUM_OXYGEN_MIXTURE;
    public static Material YTTRIUM_HOLMIUM_ERBIUM_THULIUM_YTTERBIUM_OXYGEN_LUTETIUM_MIXTURE;
    public static Material LAN_CER_PRA_NEO_CHLORIDE;
    public static Material EUR_GADO_TER_DYSPR_CHLORIDE;
    public static Material YTT_HOL_ERB_THU_YTT_CHLORIDE;

    public static void init() {
        RARE_EARTH_FE_ONE = REGISTRATE.material(CTNHCore.id("rare_earth_fe_one"))
                .cnlang("含铁稀土")
                .formula("Fe4?")
                .dust()
                .color(0x6D6531)
                .buildAndRegister();

        RARE_EARTH_FE_TWO = REGISTRATE.material(CTNHCore.id("rare_earth_fe_two"))
                .cnlang("含铁精磨稀土")
                .formula("Fe2?")
                .dust()
                .color(0x6D5231)
                .buildAndRegister();

        RARE_EARTH_INTENSIVE_RESEARCH = REGISTRATE.material(CTNHCore.id("rare_earth_intensive_research"))
                .cnlang("精磨稀土")
                .formula("???")
                .dust()
                .color(0x8A5D28)
                .buildAndRegister();

        RARE_EARTH_MIXTURE = REGISTRATE.material(CTNHCore.id("rare_earth_mixture"))
                .cnlang("稀土元素混合物")
                .dust()
                .color(0xCF8226)
                .buildAndRegister();

        RARE_EARTH_MIXTURE_OH = REGISTRATE.material(CTNHCore.id("rare_earth_mixture_oh"))
                .cnlang("碱式稀土元素混合物")
                .liquid()
                .color(0xDBB88F)
                .buildAndRegister();

        RARE_EARTH_CRYSTALS = REGISTRATE.material(CTNHCore.id("rare_earth_crystals"))
                .cnlang("稀土晶体")
                .formula("??")
                .dust()
                .color(0x5F7632)
                .buildAndRegister();

        RARE_EARTH_CHLORIDE_BOIL = REGISTRATE.material(CTNHCore.id("rare_earth_chloride_boil"))
                .cnlang("沸腾稀土氯化物")
                .liquid()
                .color(0x595B24)
                .buildAndRegister();

        RARE_EARTH_HIGH_AFFINITY = REGISTRATE.material(CTNHCore.id("rare_earth_high_affinity"))
                .cnlang("高亲和力稀土")
                .liquid()
                .color(0x535612)
                .buildAndRegister();

        RARE_EARTH_LOW_AFFINTY = REGISTRATE.material(CTNHCore.id("rare_earth_low_affinty"))
                .cnlang("低亲和力稀土")
                .liquid()
                .color(0x34351D)
                .buildAndRegister();

        RARE_EARTH_HIGH = REGISTRATE.material(CTNHCore.id("rare_earth_high"))
                .cnlang("重稀土")
                .formula("GdTbDyHoErTmYbLuY?")
                .dust()
                .color(0x70723B)
                .buildAndRegister();

        RARE_EARTH_LOW = REGISTRATE.material(CTNHCore.id("rare_earth_low"))
                .cnlang("轻稀土")
                .formula("LaCePrNd?")
                .dust()
                .color(0x94982A)
                .buildAndRegister();

        RARE_EARTH_MIDDLE = REGISTRATE.material(CTNHCore.id("rare_earth_middle"))
                .cnlang("中稀土")
                .formula("PmEuSm?")
                .dust()
                .color(0xBCBE79)
                .buildAndRegister();

        RARE_EARTH_HIGH_FLUORIDE = REGISTRATE.material(CTNHCore.id("rare_earth_high_fluoride"))
                .cnlang("氟浸没重稀土")
                .liquid()
                .color(0xCFD534)
                .buildAndRegister();

        RARE_EARTH_MIDDLE_FLUORIDE = REGISTRATE.material(CTNHCore.id("rare_earth_middle_fluoride"))
                .cnlang("氟浸没中稀土")
                .liquid()
                .color(0x919447)
                .buildAndRegister();

        RARE_EARTH_LOW_FLUORIDE = REGISTRATE.material(CTNHCore.id("rare_earth_low_fluoride"))
                .cnlang("氟浸没轻稀土")
                .liquid()
                .color(0x9EA40A)
                .buildAndRegister();

        RARE_EARTH_LOW_FLUORIDE_STEAM = REGISTRATE.material(CTNHCore.id("rare_earth_low_fluoride_steam"))
                .cnlang("氟浸没轻稀土蒸汽")
                .liquid()
                .color(0xEFAFCD)
                .buildAndRegister();

        RARE_EARTH_MIDDLE_FLUORIDE_STEAM = REGISTRATE.material(CTNHCore.id("rare_earth_middle_fluoride_steam"))
                .cnlang("氟浸没中稀土蒸汽")
                .liquid()
                .color(0xE0E65C)
                .buildAndRegister();

        RARE_EARTH_HIGH_FLUORIDE_STEAM = REGISTRATE.material(CTNHCore.id("rare_earth_high_fluoride_steam"))
                .cnlang("氟浸没重稀土蒸汽")
                .liquid()
                .color(0xE1E3A0)
                .buildAndRegister();

        LANTHANUM_CERIUM_PRASEODYMIUM_NEODYMIUM_OXYGEN_MIXTURE = REGISTRATE
                .material(CTNHCore.id("lanthanum_cerium_praseodymium_neodymium_oxygen_mixture"))
                .cnlang("轻稀土单质氧化混合物")
                .dust()
                .color(0xEAEBD1)
                .buildAndRegister();

        EUROPIUM_GADOLINIUM_TERBIUM_DYSPROSIUM_OXYGEN_MIXTURE = REGISTRATE
                .material(CTNHCore.id("europium_gadolinium_terbium_dysprosium_oxygen_mixture"))
                .cnlang("中稀土单质氧化混合物")
                .dust()
                .color(0xE7EA8F)
                .buildAndRegister();

        YTTRIUM_HOLMIUM_ERBIUM_THULIUM_YTTERBIUM_OXYGEN_LUTETIUM_MIXTURE = REGISTRATE
                .material(CTNHCore.id("yttrium_holmium_erbium_thulium_ytterbium_oxygen_lutetium_mixture"))
                .cnlang("重稀土单质氧化混合物")
                .dust()
                .color(0x9AA108)
                .buildAndRegister();

        LAN_CER_PRA_NEO_CHLORIDE = REGISTRATE.material(CTNHCore.id("lan_cer_pra_neo_chloride"))
                .cnlang("轻稀土单质氯化混合物")
                .dust()
                .color(0x5EA108)
                .buildAndRegister();

        EUR_GADO_TER_DYSPR_CHLORIDE = REGISTRATE.material(CTNHCore.id("eur_gado_ter_dyspr_chloride"))
                .cnlang("中稀土单质氯化混合物")
                .dust()
                .color(0xB7F665)
                .buildAndRegister();

        YTT_HOL_ERB_THU_YTT_CHLORIDE = REGISTRATE.material(CTNHCore.id("ytt_hol_erb_thu_ytt_chloride"))
                .cnlang("重稀土单质氯化混合物")
                .dust()
                .color(0x3F5D18)
                .buildAndRegister();
    }
}
