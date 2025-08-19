package io.github.cpearl0.ctnhcore.data.lang;

import com.gregtechceu.gtceu.data.lang.LangHandler;
import io.github.cpearl0.ctnhcore.registry.CTNHTagPrefixes;

import static io.github.cpearl0.ctnhcore.data.lang.CTNHLangHandler.tsl;

public class MaterialLang {
    public static void init(CTNHLangProvider provider) {
        tagPrefix(provider);
    }
    public static void tagPrefix(CTNHLangProvider provider) {
        tsl("tagprefix.blackstone", "嵌%s黑石", "Blackstone %s Ore");
        tsl("tagprefix.glacio_stone", "坚冰岩%s矿石", "Glacio Stone %s Ore");
        tsl("tagprefix.mars_stone", "深红岩%s矿石", "Mars Stone %s Ore");
        tsl("tagprefix.mercury_stone", "旱海岩%s矿石", "Mercury Stone %s Ore");
        tsl("tagprefix.moon_stone", "月岩%s矿石", "Moon Stone %s Ore");
        tsl("tagprefix.soul_soil", "含%s灵魂土", "Soul Soil %s Ore");
        tsl("tagprefix.venus_stone", "锃金岩%s矿石", "Venus Stone %s Ore");
        tsl("tagprefix.catalyst", "%s催化剂", "%s Catalyst");
        tsl(CTNHTagPrefixes.oreHolystone.getUnlocalizedName(), "圣石%s矿石", "Holystone %s Ore");
        tsl(CTNHTagPrefixes.oreMossyHolystone.getUnlocalizedName(), "覆苔圣石%s矿石", "Mossy Holystone %s Ore");
//        tsl(CTNHTagPrefixes.oreAstralStone.getUnlocalizedName(), "星辉%s矿石", "Astral %s Ore");
        tsl(CTNHTagPrefixes.oreIcestone.getUnlocalizedName(), "冰石%s矿石", "Icestone %s Ore");
        tsl(CTNHTagPrefixes.oreLivingrock.getUnlocalizedName(), "活石%s矿石", "Livingrock %s Ore");
        tsl(CTNHTagPrefixes.nuclear.getUnlocalizedName(), "%s", "%s");
        tsl(CTNHTagPrefixes.fuel.getUnlocalizedName(), "%s燃料", "%s Fuel");
        tsl(CTNHTagPrefixes.DepletedFuel.getUnlocalizedName(), "%s枯竭燃料", "%s Depleted Fuel");
        tsl(CTNHTagPrefixes.waste.getUnlocalizedName(), "%s废料", "%s Waste");
        tsl(CTNHTagPrefixes.hyperRotor.getUnlocalizedName(), "超级%s转子", "%s Hyper Rotor");
    }
}
