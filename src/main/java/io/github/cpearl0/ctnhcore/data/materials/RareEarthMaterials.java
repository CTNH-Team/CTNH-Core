package io.github.cpearl0.ctnhcore.data.materials;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import java.util.HashMap;
import java.util.Map;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

/**
 * 稀土材料：三条独立产线的中间体，每种元素自带粗混合物（粗氯化物溶液/粗氢氧化物/粗氧化物）与纯氯化物。
 * 氟碳铈镧矿线（轻稀土 La/Ce/Pr/Nd）、独居石线（中稀土 Sm/Eu/Gd/Tb/Dy）、
 * 稀土矿粉线（重稀土 Sc/Y/Ho/Er/Tm/Yb/Lu），外加 17 种独立氯化物与辅料。
 */
public class RareEarthMaterials {

    public static Material ALKALINE_RARE_EARTH_SLURRY;

    public static Material LIGHT_RARE_EARTH_CHLORIDE_SOLUTION;
    public static Material MIDDLE_RARE_EARTH_CHLORIDE_SOLUTION;
    public static Material HEAVY_RARE_EARTH_CHLORIDE_SOLUTION;

    public static Material LANTHANUM_CHLORIDE;
    public static Material CERIUM_CHLORIDE;
    public static Material PRASEODYMIUM_CHLORIDE;
    public static Material NEODYMIUM_CHLORIDE;
    public static Material PROMETHIUM_CHLORIDE;
    public static Material SAMARIUM_CHLORIDE;
    public static Material EUROPIUM_CHLORIDE;
    public static Material GADOLINIUM_CHLORIDE;
    public static Material TERBIUM_CHLORIDE;
    public static Material DYSPROSIUM_CHLORIDE;
    public static Material HOLMIUM_CHLORIDE;
    public static Material ERBIUM_CHLORIDE;
    public static Material THULIUM_CHLORIDE;
    public static Material YTTERBIUM_CHLORIDE;
    public static Material LUTETIUM_CHLORIDE;
    public static Material SCANDIUM_CHLORIDE;
    public static Material YTTRIUM_CHLORIDE;
    public static Material ZINC_CHLORIDE;

    public static Material ROASTED_BASTNASITE;
    public static Material CERIUM_RESIDUE;
    public static Material SCANDIUM_RESIDUE;
    public static Material THORIUM_CALCIUM_RESIDUE;
    public static Material SODIUM_PHOSPHATE;
    public static Material PRASEODYMIUM_NEODYMIUM_LOADED_ORGANIC;
    public static Material EUROPIUM_PRECIPITATE;
    public static Material EUROPIUM_FREE_MIDDLE_SOLUTION;
    public static Material SAMARIUM_GADOLINIUM_LOADED_ORGANIC;
    public static Material GADOLINIUM_ENRICHED_SOLUTION;
    public static Material TERBIUM_DYSPROSIUM_RAFFINATE;
    public static Material TERBIUM_LOADED_ORGANIC;
    public static Material DYSPROSIUM_TAIL_SOLUTION;
    public static Material YTTERBIUM_PRECIPITATE;
    public static Material YTTERBIUM_FREE_HEAVY_SOLUTION;
    public static Material YTTRIUM_LOADED_ORGANIC;
    public static Material HOLMIUM_ERBIUM_THULIUM_LUTETIUM_RAFFINATE;
    public static Material THULIUM_CHLORIDE_VAPOR;
    public static Material HOLMIUM_ERBIUM_LUTETIUM_RAFFINATE;
    public static Material HOLMIUM_LUTETIUM_TAIL_SOLUTION;
    public static Material LUTETIUM_TAIL_SOLUTION;

    /** 单种稀土元素的粗混合物、纯氯化物与高纯氯化物集合。 */
    public record CrudeRareEarth(Material metal, Material chloride, Material crudeSolution, Material crudeHydroxide,
                                 Material crudeOxide, Material refinedChloride) {}

    /** 元素金属 → 粗/精材料集合；配方代码按金属查找。 */
    public static final Map<Material, CrudeRareEarth> CRUDE_RARE_EARTHS = new HashMap<>();

    private record CrudeSpec(String id, String symbol, String cn, String en, Material metal, Material chloride,
                             int color, boolean deep, boolean refine) {}

    public static void init() {
        ALKALINE_RARE_EARTH_SLURRY = REGISTRATE.material(CTNHCore.id("rare_earth_mixture_oh"))
                .lang("Alkaline Rare Earth Slurry")
                .cnlang("碱浸稀土浆液")
                .formula("RE(OH)3.NaOH")
                .liquid()
                .color(0xDBB88F)
                .buildAndRegister();

        LIGHT_RARE_EARTH_CHLORIDE_SOLUTION = REGISTRATE.material(CTNHCore.id("rare_earth_low_fluoride"))
                .lang("Light Rare Earth Chloride Solution")
                .cnlang("轻稀土氯化物溶液")
                .formula("LRECl3(aq)")
                .liquid()
                .color(0xD8C95B)
                .buildAndRegister();

        MIDDLE_RARE_EARTH_CHLORIDE_SOLUTION = REGISTRATE.material(CTNHCore.id("rare_earth_middle_fluoride"))
                .lang("Middle Rare Earth Chloride Solution")
                .cnlang("中稀土氯化物溶液")
                .formula("MRECl3(aq)")
                .liquid()
                .color(0xAAA45E)
                .buildAndRegister();

        HEAVY_RARE_EARTH_CHLORIDE_SOLUTION = REGISTRATE.material(CTNHCore.id("rare_earth_high_fluoride"))
                .lang("Heavy Rare Earth Chloride Solution")
                .cnlang("重稀土氯化物溶液")
                .formula("HRECl3(aq)")
                .liquid()
                .color(0x77794A)
                .buildAndRegister();

        LANTHANUM_CHLORIDE = registerChloride("lanthanum_chloride", "氯化镧", "LaCl3", 0xD7E6C1, Lanthanum);
        CERIUM_CHLORIDE = registerChloride("cerium_chloride", "氯化铈", "CeCl3", 0xE2D28B, Cerium);
        PRASEODYMIUM_CHLORIDE = registerChloride("praseodymium_chloride", "氯化镨", "PrCl3", 0xA8BE8E,
                Praseodymium);
        NEODYMIUM_CHLORIDE = registerChloride("neodymium_chloride", "氯化钕", "NdCl3", 0xB6A4D4, Neodymium);
        PROMETHIUM_CHLORIDE = registerChloride("promethium_chloride", "氯化钷", "PmCl3", 0xB77A89, Promethium);
        SAMARIUM_CHLORIDE = registerChloride("samarium_chloride", "氯化钐", "SmCl3", 0xD9C4A4, Samarium);
        EUROPIUM_CHLORIDE = registerChloride("europium_chloride", "氯化铕", "EuCl3", 0xC7A77B, Europium);
        GADOLINIUM_CHLORIDE = registerChloride("gadolinium_chloride", "氯化钆", "GdCl3", 0xBEC8B3, Gadolinium);
        TERBIUM_CHLORIDE = registerChloride("terbium_chloride", "氯化铽", "TbCl3", 0xB3C69D, Terbium);
        DYSPROSIUM_CHLORIDE = registerChloride("dysprosium_chloride", "氯化镝", "DyCl3", 0xD2C5A9, Dysprosium);
        HOLMIUM_CHLORIDE = registerChloride("holmium_chloride", "氯化钬", "HoCl3", 0xD0B8A0, Holmium);
        ERBIUM_CHLORIDE = registerChloride("erbium_chloride", "氯化铒", "ErCl3", 0xE0BDD1, Erbium);
        THULIUM_CHLORIDE = registerChloride("thulium_chloride", "氯化铥", "TmCl3", 0xA9B9C4, Thulium);
        YTTERBIUM_CHLORIDE = registerChloride("ytterbium_chloride", "氯化镱", "YbCl3", 0xB7B29E, Ytterbium);
        LUTETIUM_CHLORIDE = registerChloride("lutetium_chloride", "氯化镥", "LuCl3", 0xC5C1A7, Lutetium);
        SCANDIUM_CHLORIDE = registerChloride("scandium_chloride", "氯化钪", "ScCl3", 0xD6D8C8, Scandium);
        YTTRIUM_CHLORIDE = registerChloride("yttrium_chloride", "氯化钇", "YCl3", 0xC7D2C7, Yttrium);
        ZINC_CHLORIDE = registerChloride("zinc_chloride", "氯化锌", "ZnCl2", 0xE4E0C8, Zinc, 2);

        // ── 氟碳铈镧矿线（轻稀土）────────────────────────────────
        ROASTED_BASTNASITE = REGISTRATE.material(CTNHCore.id("roasted_bastnasite"))
                .lang("Roasted Bastnasite")
                .cnlang("焙烧氟碳铈镧矿")
                .formula("CeO2.REOF")
                .dust()
                .color(0xB89B5E)
                .buildAndRegister();

        CERIUM_RESIDUE = REGISTRATE.material(CTNHCore.id("cerium_leach_residue"))
                .lang("Cerium Leach Residue")
                .cnlang("富铈浸出渣")
                .formula("CeO2")
                .dust()
                .color(0xE8D8A0)
                .buildAndRegister();

        PRASEODYMIUM_NEODYMIUM_LOADED_ORGANIC = REGISTRATE.material(CTNHCore.id(
                "praseodymium_neodymium_loaded_organic"))
                .lang("Praseodymium Neodymium Loaded Organic")
                .cnlang("载镨钕有机相")
                .formula("PrNdCl3.Org")
                .liquid()
                .color(0xA8B25E)
                .buildAndRegister();

        // ── 独居石线（中稀土）────────────────────────────────────
        SCANDIUM_RESIDUE = REGISTRATE.material(CTNHCore.id("scandium_leach_residue"))
                .lang("Scandium Leach Residue")
                .cnlang("富钪浸出渣")
                .formula("Sc(OH)3")
                .dust()
                .color(0xD8D0B8)
                .buildAndRegister();

        THORIUM_CALCIUM_RESIDUE = REGISTRATE.material(CTNHCore.id("thorium_calcium_leach_residue"))
                .lang("Thorium Calcium Leach Residue")
                .cnlang("钍钙浸出渣")
                .formula("(Th,Ca)Ox")
                .dust()
                .color(0xB0A48E)
                .buildAndRegister();

        SODIUM_PHOSPHATE = REGISTRATE.material(CTNHCore.id("sodium_phosphate"))
                .lang("Sodium Phosphate")
                .cnlang("磷酸钠")
                .formula("Na3PO4")
                .dust()
                .color(0xE8E4D8)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        EUROPIUM_PRECIPITATE = REGISTRATE.material(CTNHCore.id("europium_precipitate"))
                .lang("Europium Precipitate")
                .cnlang("铕富集沉淀")
                .formula("EuCl2")
                .dust()
                .color(0xC7A77B)
                .buildAndRegister();

        EUROPIUM_FREE_MIDDLE_SOLUTION = REGISTRATE.material(CTNHCore.id("europium_free_middle_rare_earth_solution"))
                .lang("Europium Free Middle Rare Earth Solution")
                .cnlang("除铕中稀土溶液")
                .formula("MRECl3(aq)")
                .liquid()
                .color(0xAAA45E)
                .buildAndRegister();

        SAMARIUM_GADOLINIUM_LOADED_ORGANIC = REGISTRATE.material(CTNHCore.id("samarium_gadolinium_loaded_organic"))
                .lang("Samarium Gadolinium Loaded Organic")
                .cnlang("载钐钆有机相")
                .formula("SmGdCl3.Org")
                .liquid()
                .color(0xB0A855)
                .buildAndRegister();

        GADOLINIUM_ENRICHED_SOLUTION = REGISTRATE.material(CTNHCore.id("gadolinium_enriched_solution"))
                .lang("Gadolinium Enriched Solution")
                .cnlang("钆富集液")
                .formula("GdCl3(aq)")
                .liquid()
                .color(0xBEC8B3)
                .buildAndRegister();

        TERBIUM_DYSPROSIUM_RAFFINATE = REGISTRATE.material(CTNHCore.id("terbium_dysprosium_raffinate"))
                .lang("Terbium Dysprosium Raffinate")
                .cnlang("铽镝萃余液")
                .formula("TbDyCl3(aq)")
                .liquid()
                .color(0x9E9E52)
                .buildAndRegister();

        TERBIUM_LOADED_ORGANIC = REGISTRATE.material(CTNHCore.id("terbium_loaded_organic"))
                .lang("Terbium Loaded Organic")
                .cnlang("载铽有机相")
                .formula("TbCl3.Org")
                .liquid()
                .color(0xB3C69D)
                .buildAndRegister();

        DYSPROSIUM_TAIL_SOLUTION = REGISTRATE.material(CTNHCore.id("dysprosium_tail_solution"))
                .lang("Dysprosium Tail Solution")
                .cnlang("镝富集尾液")
                .formula("DyCl3(aq)")
                .liquid()
                .color(0xD2C5A9)
                .buildAndRegister();

        // ── 稀土矿粉线（重稀土）──────────────────────────────────
        YTTERBIUM_PRECIPITATE = REGISTRATE.material(CTNHCore.id("ytterbium_precipitate"))
                .lang("Ytterbium Precipitate")
                .cnlang("镱富集沉淀")
                .formula("YbCl2")
                .dust()
                .color(0xB7B29E)
                .buildAndRegister();

        YTTERBIUM_FREE_HEAVY_SOLUTION = REGISTRATE.material(CTNHCore.id("ytterbium_free_heavy_rare_earth_solution"))
                .lang("Ytterbium Free Heavy Rare Earth Solution")
                .cnlang("除镱重稀土溶液")
                .formula("HRECl3(aq)")
                .liquid()
                .color(0x77794A)
                .buildAndRegister();

        YTTRIUM_LOADED_ORGANIC = REGISTRATE.material(CTNHCore.id("yttrium_loaded_organic"))
                .lang("Yttrium Loaded Organic")
                .cnlang("载钇有机相")
                .formula("YCl3.Org")
                .liquid()
                .color(0xC7D2C7)
                .buildAndRegister();

        HOLMIUM_ERBIUM_THULIUM_LUTETIUM_RAFFINATE = REGISTRATE.material(CTNHCore.id(
                "holmium_erbium_thulium_lutetium_raffinate"))
                .lang("Holmium Erbium Thulium Lutetium Raffinate")
                .cnlang("钬铒铥镥萃余液")
                .formula("HoErTmLuCl3(aq)")
                .liquid()
                .color(0x7A7C4E)
                .buildAndRegister();

        THULIUM_CHLORIDE_VAPOR = REGISTRATE.material(CTNHCore.id("thulium_chloride_vapor"))
                .lang("Thulium Chloride Vapor")
                .cnlang("铥氯化物蒸汽")
                .formula("TmCl3(g)")
                .gas()
                .color(0xBFC7D0)
                .buildAndRegister();

        HOLMIUM_ERBIUM_LUTETIUM_RAFFINATE = REGISTRATE.material(CTNHCore.id("holmium_erbium_lutetium_raffinate"))
                .lang("Holmium Erbium Lutetium Raffinate")
                .cnlang("钬铒镥萃余液")
                .formula("HoErLuCl3(aq)")
                .liquid()
                .color(0x6F7148)
                .buildAndRegister();

        HOLMIUM_LUTETIUM_TAIL_SOLUTION = REGISTRATE.material(CTNHCore.id("holmium_lutetium_tail_solution"))
                .lang("Holmium Lutetium Tail Solution")
                .cnlang("钬镥尾液")
                .formula("HoLuCl3(aq)")
                .liquid()
                .color(0xAE967A)
                .buildAndRegister();

        LUTETIUM_TAIL_SOLUTION = REGISTRATE.material(CTNHCore.id("lutetium_tail_solution"))
                .lang("Lutetium Tail Solution")
                .cnlang("镥富集尾液")
                .formula("LuCl3(aq)")
                .liquid()
                .color(0xC5C1A7)
                .buildAndRegister();

        // ── 单元素粗混合物（粗氯化物溶液/粗氢氧化物/粗氧化物）──────────
        CrudeSpec[] specs = {
                new CrudeSpec("lanthanum", "La", "镧", "Lanthanum", Lanthanum, LANTHANUM_CHLORIDE, 0xD7E6C1, false,
                        false),
                new CrudeSpec("cerium", "Ce", "铈", "Cerium", Cerium, CERIUM_CHLORIDE, 0xE2D28B, false, false),
                new CrudeSpec("praseodymium", "Pr", "镨", "Praseodymium", Praseodymium, PRASEODYMIUM_CHLORIDE,
                        0xA8BE8E, false, false),
                new CrudeSpec("neodymium", "Nd", "钕", "Neodymium", Neodymium, NEODYMIUM_CHLORIDE, 0xB6A4D4, false,
                        false),
                new CrudeSpec("samarium", "Sm", "钐", "Samarium", Samarium, SAMARIUM_CHLORIDE, 0xD9C4A4, true, false),
                new CrudeSpec("europium", "Eu", "铕", "Europium", Europium, EUROPIUM_CHLORIDE, 0xC7A77B, true, true),
                new CrudeSpec("gadolinium", "Gd", "钆", "Gadolinium", Gadolinium, GADOLINIUM_CHLORIDE, 0xBEC8B3, true,
                        false),
                new CrudeSpec("terbium", "Tb", "铽", "Terbium", Terbium, TERBIUM_CHLORIDE, 0xB3C69D, true, false),
                new CrudeSpec("dysprosium", "Dy", "镝", "Dysprosium", Dysprosium, DYSPROSIUM_CHLORIDE, 0xD2C5A9, true,
                        false),
                new CrudeSpec("holmium", "Ho", "钬", "Holmium", Holmium, HOLMIUM_CHLORIDE, 0xD0B8A0, true, true),
                new CrudeSpec("erbium", "Er", "铒", "Erbium", Erbium, ERBIUM_CHLORIDE, 0xE0BDD1, true, true),
                new CrudeSpec("thulium", "Tm", "铥", "Thulium", Thulium, THULIUM_CHLORIDE, 0xA9B9C4, true, true),
                new CrudeSpec("ytterbium", "Yb", "镱", "Ytterbium", Ytterbium, YTTERBIUM_CHLORIDE, 0xB7B29E, true,
                        true),
                new CrudeSpec("lutetium", "Lu", "镥", "Lutetium", Lutetium, LUTETIUM_CHLORIDE, 0xC5C1A7, true, true),
                new CrudeSpec("yttrium", "Y", "钇", "Yttrium", Yttrium, YTTRIUM_CHLORIDE, 0xC7D2C7, true, true)
        };
        for (CrudeSpec spec : specs) {
            Material solution = registerCrudeSolution(spec);
            Material hydroxide = registerCrudeHydroxide(spec);
            Material oxide = spec.deep() ? registerCrudeOxide(spec) : null;
            Material refined = spec.refine() ? registerRefinedChloride(spec) : null;
            CRUDE_RARE_EARTHS.put(spec.metal(),
                    new CrudeRareEarth(spec.metal(), spec.chloride(), solution, hydroxide, oxide, refined));
        }
        // 钪的粗氢氧化物复用富钪浸出渣（Sc(OH)3），没有粗氯化物溶液段
        CrudeSpec scandiumSpec = new CrudeSpec("scandium", "Sc", "钪", "Scandium", Scandium, SCANDIUM_CHLORIDE,
                0xD6D8C8, true, true);
        Material scandiumOxide = registerCrudeOxide(scandiumSpec);
        Material scandiumRefined = registerRefinedChloride(scandiumSpec);
        CRUDE_RARE_EARTHS.put(Scandium,
                new CrudeRareEarth(Scandium, SCANDIUM_CHLORIDE, null, SCANDIUM_RESIDUE, scandiumOxide,
                        scandiumRefined));

        registerLegacyMaterials();
    }

    private static Material registerRefinedChloride(CrudeSpec spec) {
        return registerChloride("refined_" + spec.id() + "_chloride", "高纯氯化" + spec.cn(),
                spec.symbol() + "Cl3", spec.color(), spec.metal());
    }

    private static Material registerCrudeSolution(CrudeSpec spec) {
        return REGISTRATE.material(CTNHCore.id("crude_" + spec.id() + "_chloride_solution"))
                .lang("Crude " + spec.en() + " Chloride Solution")
                .cnlang("粗氯化" + spec.cn() + "溶液")
                .formula(spec.symbol() + "Cl3(aq)")
                .liquid()
                .color(spec.color())
                .buildAndRegister();
    }

    private static Material registerCrudeHydroxide(CrudeSpec spec) {
        return REGISTRATE.material(CTNHCore.id("crude_" + spec.id() + "_hydroxide"))
                .lang("Crude " + spec.en() + " Hydroxide")
                .cnlang("粗氢氧化" + spec.cn())
                .formula(spec.symbol() + "(OH)3")
                .dust()
                .color(spec.color())
                .buildAndRegister();
    }

    private static Material registerCrudeOxide(CrudeSpec spec) {
        return REGISTRATE.material(CTNHCore.id("crude_" + spec.id() + "_oxide"))
                .lang("Crude " + spec.en() + " Oxide")
                .cnlang("粗氧化" + spec.cn())
                .formula(spec.symbol() + "2O3")
                .dust()
                .color(spec.color())
                .buildAndRegister();
    }

    private static Material registerChloride(String id, String cnName, String formula, int color, Material metal) {
        return registerChloride(id, cnName, formula, color, metal, 3);
    }

    private static Material registerChloride(String id, String cnName, String formula, int color, Material metal,
                                             int chlorineAmount) {
        return REGISTRATE.material(CTNHCore.id(id))
                .cnlang(cnName)
                .formula(formula)
                .dust()
                .color(color)
                .flags(DISABLE_DECOMPOSITION)
                .components(metal, 1, Chlorine, chlorineAmount)
                .buildAndRegister();
    }

    private static void registerLegacyMaterials() {
        REGISTRATE.material(CTNHCore.id("rare_earth_chloride_boil"))
                .lang("Legacy Rare Earth Chloride Solution")
                .cnlang("旧式稀土氯化液")
                .formula("RECl3(aq)")
                .liquid()
                .color(0x595B24)
                .buildAndRegister();

        registerLegacyDust("rare_earth_low", "旧式轻稀土混合物", "(La,Ce,Pr,Nd,Pm)2O3", 0x94982A);
        registerLegacyDust("rare_earth_middle", "旧式中稀土混合物", "(Sm,Eu,Gd,Tb,Dy,Ho)2O3", 0xBCBE79);
        registerLegacyDust("rare_earth_high", "旧式重稀土混合物", "(Er,Tm,Yb,Lu,Sc,Y)2O3", 0x70723B);
        registerLegacyDust("lanthanum_cerium_praseodymium_neodymium_oxygen_mixture", "旧式轻稀土氧化混合物",
                "(La,Ce,Pr,Nd,Pm)2O3", 0xEAEBD1);
        registerLegacyDust("europium_gadolinium_terbium_dysprosium_oxygen_mixture", "旧式中稀土氧化混合物",
                "(Sm,Eu,Gd,Tb,Dy,Ho)2O3", 0xE7EA8F);
        registerLegacyDust("yttrium_holmium_erbium_thulium_ytterbium_oxygen_lutetium_mixture", "旧式重稀土氧化混合物",
                "(Er,Tm,Yb,Lu,Sc,Y)2O3", 0x9AA108);
        registerLegacyDust("lan_cer_pra_neo_chloride", "旧式轻稀土氯化混合物", "(La,Ce,Pr,Nd,Pm)Cl3", 0x5EA108);
        registerLegacyDust("eur_gado_ter_dyspr_chloride", "旧式中稀土氯化混合物", "(Sm,Eu,Gd,Tb,Dy,Ho)Cl3",
                0xB7F665);
        registerLegacyDust("ytt_hol_erb_thu_ytt_chloride", "旧式重稀土氯化混合物", "(Er,Tm,Yb,Lu,Sc,Y)Cl3",
                0x3F5D18);
    }

    private static void registerLegacyDust(String id, String cnName, String formula, int color) {
        REGISTRATE.material(CTNHCore.id(id))
                .cnlang(cnName)
                .formula(formula)
                .dust()
                .color(color)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
    }
}
