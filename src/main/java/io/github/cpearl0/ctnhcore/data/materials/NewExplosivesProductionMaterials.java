package io.github.cpearl0.ctnhcore.data.materials;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.METALLIC;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Potassium;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class NewExplosivesProductionMaterials {

    public static Material CHALCOGEN_ANODE_MUD;
    public static Material SODIUM_TELLURITE;
    public static Material SELENIUM_DIOXIDE;
    public static Material TELLURIUM_DIOXIDE;
    public static Material SELENOUS_ACID;
    public static Material AMMONIUM_SULFATE;
    public static Material AMMONIUM_CARBONATE;
    public static Material POTASSIUM_BISULFITE;
    public static Material POTASSIUM_NITRITE;
    public static Material NITROUS_ACID;
    public static Material SODIUM_ACETATE;
    public static Material POTASSIUM_HYDROXYLAMINE_DISULFONATE;
    public static Material HYDROXYLAMMONIUM_SULFATE;
    public static Material BARIUM_CHLORIDE;
    public static Material BARIUM_SULFATE_SOLUTION;
    public static Material HYDROXYLAMINE_HYDROCHLORIDE;
    public static Material SUCCINIC_ACID;
    public static Material SUCCINIC_ANHYDRIDE;
    public static Material ACETYLENE;
    public static Material TETRAHYDROFURAN;
    public static Material NHYDROXY_SUCCINIMIDE;
    public static Material TRIETHYLAMINE;
    public static Material SUCCINIMIDYL_ACETATE;
    public static Material GLYOXAL;
    public static Material AMMONIUM_ACETATE;
    public static Material ACETAMIDE;
    public static Material ACETONITRILE;
    public static Material BENZYL_CHLORIDE;
    public static Material HEXAMETHYLENETETRAMINE;
    public static Material BENZYLAMINE;
    public static Material HEXABENZYLHEXAAZAISOWURTZITANE;
    public static Material PALLADIUM_CHLORIDE;
    public static Material DIBENZYL_TETRAACETYLHEXAAZAISOWURTZITANE;
    public static Material SUCCINIMIDE;
    public static Material BORON_FLUORIDE;
    public static Material NITRONIUM_TETRAFLUOROBORATE;
    public static Material NITROSONIUM_TETRAFLUOROBORATE;
    public static Material TETRAACETYLDINITROSOHEXAAZAISOWURTZITANE;
    public static Material TETRAFLUOROBORIC_ACID;
    public static Material BENZALDEHYDE;
    public static Material CRUDE_HEXANITRO_HEXAAZAISOWURTZITANE;
    public static Material ETHYLENEDIAMINE;
    public static Material SILICA_GEL;
    public static Material HEXANITRO_HEXAAZAISOWURTZITANE;
    public static Material BORIC_ACID;
    public static Material DIMETHYLFORMAMIDE;
    public static Material SODIUM_PERIODATE;
    public static Material CALCIUM_CARBIDE;
    public static Material SODIUM_IODATE;
    public static Material SODIUM_SULFATE_SOLUTION;
    public static Material SODIUM_IODIDE;
    public static Material AETERNIUM;
    public static Material ANCIENT_DEBRIS;
    public static Material ANCIENT_DEBRIS_LEACH;
    public static Material HYDROBROMIC_ACID;
    public static Material BORON_OXIDE;
    public static Material FLINAK;
    public static Material SILICON_CHLORIDE;
    public static Material BARIUM_HYDROXIDE;
    public static Material MESITYL_OXIDE;
    public static Material METHYL_ISOBUTYL_KETONE;
    public static Material THIOCYANIC_ACID;

    public static void init() {
        CHALCOGEN_ANODE_MUD = REGISTRATE.material(CTNHCore.id("chalcogen_anode_mud"))
                .cnlang("硫族阳离子矿泥")
                .dust()
                .color(0x247A93)
                .buildAndRegister();

        SODIUM_TELLURITE = REGISTRATE.material(CTNHCore.id("sodium_tellurite"))
                .cnlang("亚碲酸钠")
                .dust()
                .color(0x47D2E7)
                .buildAndRegister();

        SELENIUM_DIOXIDE = REGISTRATE.material(CTNHCore.id("selenium_dioxide"))
                .cnlang("二氧化硒")
                .dust()
                .color(0xE4F437)
                .components(Selenium, 1, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        TELLURIUM_DIOXIDE = REGISTRATE.material(CTNHCore.id("tellurium_dioxide"))
                .cnlang("二氧化碲")
                .dust()
                .color(0xCBCD22)
                .components(Tellurium, 1, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        SELENOUS_ACID = REGISTRATE.material(CTNHCore.id("selenous_acid"))
                .cnlang("亚硒酸")
                .liquid()
                .color(0x8D1152)
                .components(Hydrogen, 2, Selenium, 1, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        AMMONIUM_SULFATE = REGISTRATE.material(CTNHCore.id("ammonium_sulfate"))
                .cnlang("硫酸铵")
                .formula("(NH4)2SO4")
                .liquid()
                .color(0x2759E7)
                .components(Nitrogen, 2, Hydrogen, 8, Sulfur, 1, Oxygen, 4)
                .buildAndRegister();

        AMMONIUM_CARBONATE = REGISTRATE.material(CTNHCore.id("ammonium_carbonate"))
                .cnlang("碳酸铵")
                .dust()
                .color(0x2654D8)
                .components(Nitrogen, 2, Hydrogen, 8, Carbon, 1, Oxygen, 3)
                .buildAndRegister();

        POTASSIUM_BISULFITE = REGISTRATE.material(CTNHCore.id("potassium_bisulfite"))
                .cnlang("亚硫酸氢钾")
                .dust()
                .color(0xB0AFA5)
                .components(Potassium, 1, Sulfur, 1, Hydrogen, 1, Oxygen, 3)
                .buildAndRegister();

        POTASSIUM_NITRITE = REGISTRATE.material(CTNHCore.id("potassium_nitrite"))
                .cnlang("亚硝酸钾")
                .dust()
                .color(0xC5C3B2)
                .components(Potassium, 1, Nitrogen, 1, Oxygen, 2)
                .buildAndRegister();

        NITROUS_ACID = REGISTRATE.material(CTNHCore.id("nitrous_acid"))
                .cnlang("亚硝酸")
                .liquid()
                .color(0xE2DEB2)
                .components(Hydrogen, 1, Nitrogen, 1, Oxygen, 2)
                .buildAndRegister();

        SODIUM_ACETATE = REGISTRATE.material(CTNHCore.id("sodium_acetate"))
                .cnlang("乙酸钠")
                .liquid()
                .color(0x80E566)
                .components(Sodium, 1, Carbon, 2, Hydrogen, 3, Oxygen, 2)
                .buildAndRegister();

        POTASSIUM_HYDROXYLAMINE_DISULFONATE = REGISTRATE.material(CTNHCore.id("potassium_hydroxylamine_disulfonate"))
                .cnlang("羟胺二磺酸钾")
                .dust()
                .color(0x3F7A30)
                .components(Potassium, 2, Nitrogen, 1, Hydrogen, 1, Sulfur, 2, Oxygen, 7)
                .buildAndRegister();

        HYDROXYLAMMONIUM_SULFATE = REGISTRATE.material(CTNHCore.id("hydroxylammonium_sulfate"))
                .cnlang("羟铵硫酸盐")
                .dust()
                .color(0xA0A591)
                .components(Nitrogen, 2, Hydrogen, 8, Sulfur, 1, Oxygen, 6)
                .buildAndRegister();

        BARIUM_CHLORIDE = REGISTRATE.material(CTNHCore.id("barium_chloride"))
                .cnlang("氯化钡")
                .dust()
                .color(0xD26D4F)
                .components(Barium, 1, Chlorine, 2)
                .buildAndRegister();
        BARIUM_SULFATE_SOLUTION = REGISTRATE.material(CTNHCore.id("barium_sulfate_solution"))
                .cnlang("硫酸钡悬浊液")
                .liquid()
                .color(0xA7C6E6)
                .buildAndRegister();

        HYDROXYLAMINE_HYDROCHLORIDE = REGISTRATE.material(CTNHCore.id("hydroxylamine_hydrochloride"))
                .cnlang("盐酸羟胺")
                .liquid()
                .color(0xE33333)
                .buildAndRegister();

        SUCCINIC_ACID = REGISTRATE.material(CTNHCore.id("succinic_acid"))
                .cnlang("琥珀酸")
                .dust()
                .color(0x0F6F6D)
                .buildAndRegister();

        SUCCINIC_ANHYDRIDE = REGISTRATE.material(CTNHCore.id("succinic_anhydride"))
                .cnlang("丁二酸酐")
                .dust()
                .color(0x634010)
                .buildAndRegister();

        ACETYLENE = REGISTRATE.material(CTNHCore.id("acetylene"))
                .cnlang("乙炔")
                .gas()
                .color(0x8EB256)
                .components(Carbon, 2, Hydrogen, 2)
                .buildAndRegister();

        TETRAHYDROFURAN = REGISTRATE.material(CTNHCore.id("tetrahydrofuran"))
                .cnlang("四氢呋喃")
                .liquid()
                .color(0x8FE5BC)
                .buildAndRegister();

        NHYDROXY_SUCCINIMIDE = REGISTRATE.material(CTNHCore.id("nhydroxy_succinimide"))
                .cnlang("N-羟基丁二酰亚胺")
                .dust()
                .color(0x997DB2)
                .buildAndRegister();

        TRIETHYLAMINE = REGISTRATE.material(CTNHCore.id("triethylamine"))
                .cnlang("三乙胺")
                .liquid()
                .color(0x52686A)
                .buildAndRegister();

        SUCCINIMIDYL_ACETATE = REGISTRATE.material(CTNHCore.id("succinimidyl_acetate"))
                .cnlang("琥珀酰亚胺醋酸酯")
                .dust()
                .color(0x855C75)
                .buildAndRegister();

        GLYOXAL = REGISTRATE.material(CTNHCore.id("glyoxal"))
                .cnlang("乙二醛")
                .liquid()
                .color(0xF0ED4D)
                .components(Carbon, 2, Hydrogen, 2, Oxygen, 2)
                .buildAndRegister();

        AMMONIUM_ACETATE = REGISTRATE.material(CTNHCore.id("ammonium_acetate"))
                .cnlang("乙酸铵")
                .dust()
                .color(0x73A3A7)
                .buildAndRegister();

        ACETAMIDE = REGISTRATE.material(CTNHCore.id("acetamide"))
                .cnlang("乙酰胺")
                .dust()
                .color(0x638E92)
                .buildAndRegister();

        ACETONITRILE = REGISTRATE.material(CTNHCore.id("acetonitrile"))
                .cnlang("乙腈")
                .dust()
                .color(0x889FB5)
                .components(Carbon, 2, Hydrogen, 3, Nitrogen, 1)
                .buildAndRegister();

        BENZYL_CHLORIDE = REGISTRATE.material(CTNHCore.id("benzyl_chloride"))
                .cnlang("氯化苄")
                .liquid()
                .color(0x85E7E7)
                .components(Carbon, 7, Hydrogen, 7, Chlorine, 1)
                .buildAndRegister();

        HEXAMETHYLENETETRAMINE = REGISTRATE.material(CTNHCore.id("hexamethylenetetramine"))
                .cnlang("六亚甲基四胺")
                .dust()
                .color(0x5A6261)
                .components(Carbon, 6, Hydrogen, 12, Nitrogen, 4)
                .buildAndRegister();

        BENZYLAMINE = REGISTRATE.material(CTNHCore.id("benzylamine"))
                .cnlang("苄胺")
                .liquid()
                .color(0x5B6363)
                .components(Carbon, 7, Hydrogen, 9, Nitrogen, 1)
                .buildAndRegister();

        HEXABENZYLHEXAAZAISOWURTZITANE = REGISTRATE.material(CTNHCore.id("hexabenzylhexaazaisowurtzitane"))
                .cnlang("六苄基六氮杂异伍兹烷")
                .dust()
                .color(0x4A2367)
                .components(Carbon, 48, Hydrogen, 48, Nitrogen, 6)
                .buildAndRegister();

        PALLADIUM_CHLORIDE = REGISTRATE.material(CTNHCore.id("palladium_chloride"))
                .cnlang("氯化钯")
                .dust()
                .color(0xCFCFCF)
                .components(Palladium, 1, Chlorine, 2)
                .buildAndRegister();

        DIBENZYL_TETRAACETYLHEXAAZAISOWURTZITANE = REGISTRATE
                .material(CTNHCore.id("dibenzyl_tetraacetylhexaazaisowurtzitane"))
                .cnlang("二苄基四乙酰六氮杂异纤锌烷")
                .dust()
                .color(0x8EA268)
                .components(Carbon, 28, Hydrogen, 32, Nitrogen, 6, Oxygen, 4)
                .buildAndRegister();

        SUCCINIMIDE = REGISTRATE.material(CTNHCore.id("succinimide"))
                .cnlang("琥珀酰亚胺")
                .dust()
                .color(0x21A7C5)
                .buildAndRegister();

        BORON_FLUORIDE = REGISTRATE.material(CTNHCore.id("boron_fluoride"))
                .cnlang("三氟化硼")
                .liquid()
                .color(0xCECAD0)
                .components(Boron, 1, Fluorine, 3)
                .buildAndRegister();

        NITRONIUM_TETRAFLUOROBORATE = REGISTRATE.material(CTNHCore.id("nitronium_tetrafluoroborate"))
                .cnlang("四氟硝铵")
                .dust()
                .color(0x3C3F40)
                .components(Nitrogen, 1, Oxygen, 2, Boron, 1, Fluorine, 4)
                .buildAndRegister();

        NITROSONIUM_TETRAFLUOROBORATE = REGISTRATE.material(CTNHCore.id("nitrosonium_tetrafluoroborate"))
                .cnlang("四氟硼酸亚硝铵")
                .dust()
                .color(0x485054)
                .components(Nitrogen, 1, Oxygen, 1, Boron, 1, Fluorine, 4)
                .buildAndRegister();

        TETRAACETYLDINITROSOHEXAAZAISOWURTZITANE = REGISTRATE
                .material(CTNHCore.id("tetraacetyldinitrosohexaazaisowurtzitane"))
                .cnlang("四乙酰二硝基六氮杂异戊二烯")
                .dust()
                .color(0x500449)
                .buildAndRegister();

        TETRAFLUOROBORIC_ACID = REGISTRATE.material(CTNHCore.id("tetrafluoroboric_acid"))
                .cnlang("四氟硼酸")
                .liquid()
                .color(0x7C8915)
                .buildAndRegister();

        BENZALDEHYDE = REGISTRATE.material(CTNHCore.id("benzaldehyde"))
                .cnlang("苯甲醛")
                .liquid()
                .color(0x905A1B)
                .components(Carbon, 7, Hydrogen, 6, Oxygen, 1)
                .buildAndRegister();
        CRUDE_HEXANITRO_HEXAAZAISOWURTZITANE = REGISTRATE.material(CTNHCore.id("crude_hexanitro_hexaazaisowurtzitane"))
                .cnlang("粗制六硝基六氧杂纤锌烷")
                .dust()
                .color(0x19586D)
                .buildAndRegister();

        ETHYLENEDIAMINE = REGISTRATE.material(CTNHCore.id("ethylenediamine"))
                .cnlang("乙二胺")
                .liquid()
                .color(0x1B5D74)
                .components(Carbon, 2, Hydrogen, 8, Nitrogen, 2)
                .buildAndRegister();

        SILICA_GEL = REGISTRATE.material(CTNHCore.id("silica_gel"))
                .cnlang("硅胶")
                .dust()
                .color(0x57C3E4)
                .buildAndRegister();

        HEXANITRO_HEXAAZAISOWURTZITANE = REGISTRATE.material(CTNHCore.id("hexanitro_hexaazaisowurtzitane"))
                .cnlang("六硝基六轴异伍兹烷")
                .dust()
                .color(0x3D464B)
                .buildAndRegister();

        BORIC_ACID = REGISTRATE.material(CTNHCore.id("boric_acid"))
                .cnlang("硼酸")
                .liquid()
                .color(0x8FBC8F)
                .buildAndRegister();

        DIMETHYLFORMAMIDE = REGISTRATE.material(CTNHCore.id("dimethylformamide"))
                .cnlang("二甲基甲酰胺")
                .liquid()
                .color(0x34F4A5)
                .buildAndRegister();

        SODIUM_PERIODATE = REGISTRATE.material(CTNHCore.id("sodium_periodate"))
                .cnlang("高碘酸钠")
                .dust()
                .color(0x1438BB)
                .components(Sodium, 1, Iodine, 1, Oxygen, 4)
                .iconSet(METALLIC)
                .buildAndRegister();

        CALCIUM_CARBIDE = REGISTRATE.material(CTNHCore.id("calcium_carbide"))
                .cnlang("碳化钙")
                .dust()
                .color(0x3E4133)
                .components(Carbon, 1, Calcium, 1)
                .buildAndRegister();
        SODIUM_IODATE = REGISTRATE.material(CTNHCore.id("sodium_iodate"))
                .cnlang("碘酸钠")
                .dust()
                .color(0x0E2887)
                .buildAndRegister();

        SODIUM_SULFATE_SOLUTION = REGISTRATE.material(CTNHCore.id("sodium_sulfate_solution"))
                .cnlang("硫酸钠溶液")
                .liquid()
                .color(0x454141)
                .buildAndRegister();

        SODIUM_IODIDE = REGISTRATE.material(CTNHCore.id("sodium_iodide"))
                .cnlang("碘化钠")
                .dust()
                .color(0x335187)
                .components(Sodium, 1, Iodine, 1)
                .buildAndRegister();

        AETERNIUM = REGISTRATE.material(CTNHCore.id("aeternium"))
                .cnlang("太古合金")
                .dust()
                .color(0x376169)
                .secondaryColor(0x2C7D74)
                .buildAndRegister();

        ANCIENT_DEBRIS = REGISTRATE.material(CTNHCore.id("ancient_debris"))
                .cnlang("远古残骸")
                .dust()
                .color(0x59342A)
                .secondaryColor(0x734437)
                .buildAndRegister();

        ANCIENT_DEBRIS_LEACH = REGISTRATE.material(CTNHCore.id("ancient_debris_leach"))
                .cnlang("远古残骸浸出物")
                .liquid()
                .color(0x7A4437)
                .buildAndRegister();

        HYDROBROMIC_ACID = REGISTRATE.material(CTNHCore.id("hydrobromic_acid"))
                .cnlang("氢溴酸")
                .liquid()
                .color(0xD1593E)
                .components(Hydrogen, 1, Bromine, 1)
                .buildAndRegister();

        BORON_OXIDE = REGISTRATE.material(CTNHCore.id("boron_oxide"))
                .cnlang("氧化硼")
                .dust()
                .color(0x87CFED)
                .components(Boron, 2, Oxygen, 3)
                .buildAndRegister();

        FLINAK = REGISTRATE.material(CTNHCore.id("flinak"))
                .cnlang("氟锂钠钾")
                .dust()
                .liquid()
                .color(0x5C5757)
                .components(Fluorine, 3, Lithium, 1, Sodium, 1, Potassium, 1)
                .buildAndRegister();

        SILICON_CHLORIDE = REGISTRATE.material(CTNHCore.id("silicon_chloride"))
                .cnlang("四氯化硅")
                .liquid()
                .color(0x8BA4B0)
                .buildAndRegister();

        BARIUM_HYDROXIDE = REGISTRATE.material(CTNHCore.id("barium_hydroxide"))
                .cnlang("氢氧化钡")
                .dust()
                .color(0x58C95C)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        MESITYL_OXIDE = REGISTRATE.material(CTNHCore.id("mesityl_oxide"))
                .cnlang("异亚丙基丙酮")
                .liquid()
                .color(0x97F78B)
                .buildAndRegister();

        METHYL_ISOBUTYL_KETONE = REGISTRATE.material(CTNHCore.id("methyl_isobutyl_ketone"))
                .cnlang("甲基异丁基酮")
                .liquid()
                .color(0x56D652)
                .buildAndRegister();

        THIOCYANIC_ACID = REGISTRATE.material(CTNHCore.id("thiocyanic_acid"))
                .cnlang("硫氰酸")
                .liquid()
                .color(0xFFFC64)
                .components(Sulfur, 1, Carbon, 1, Nitrogen, 1, Hydrogen, 1)
                .buildAndRegister();
    }
}
