package io.github.cpearl0.ctnhcore.data.lang;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.machines.GTMachineUtils;
import dev.arbor.gtnn.data.GTNNMaterials;
import io.github.cpearl0.ctnhcore.api.data.material.CTNHPropertyKeys;
import io.github.cpearl0.ctnhcore.registry.*;
import io.github.cpearl0.ctnhcore.registry.adventure.CTNHEnchantments;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksA;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksB;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksC;
import io.github.cpearl0.ctnhcore.registry.nuclear.NuclearMaterials;
import io.github.cpearl0.ctnhcore.registry.worldgen.AstralBlocks;
import net.minecraftforge.common.data.LanguageProvider;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Map;

import static com.gregtechceu.gtceu.api.GTValues.*;

public class ChineseLangHandler {
    public static void init(RegistrateCNLangProvider provider) {
        //Tagprefix
        replace(provider, CTNHTagPrefixes.oreHolystone.getUnlocalizedName(), "圣石%s矿石");
        replace(provider, CTNHTagPrefixes.oreMossyHolystone.getUnlocalizedName(), "覆苔圣石%s矿石");
        replace(provider, CTNHTagPrefixes.oreAstralStone.getUnlocalizedName(), "星辉%s矿石");
        replace(provider, CTNHTagPrefixes.oreIcestone.getUnlocalizedName(), "冰石%s矿石");
        replace(provider, CTNHTagPrefixes.oreLivingrock.getUnlocalizedName(), "活石%s矿石");
        replace(provider, CTNHTagPrefixes.nuclear.getUnlocalizedName(), "%s");
        replace(provider, CTNHTagPrefixes.fuel.getUnlocalizedName(), "%s燃料");
        replace(provider, CTNHTagPrefixes.DepletedFuel.getUnlocalizedName(), "%s枯竭燃料");
        replace(provider, CTNHTagPrefixes.waste.getUnlocalizedName(), "%s废料");
        replace(provider, CTNHTagPrefixes.hyperRotor.getUnlocalizedName(), "超级%s转子");

        replace(provider, CTNHMaterials.Moonstone.getUnlocalizedName(), "月石");
        replace(provider, CTNHMaterials.Marsstone.getUnlocalizedName(), "火星石");
        replace(provider, CTNHMaterials.Venusstone.getUnlocalizedName(), "金星石");
        replace(provider, CTNHMaterials.Mercurystone.getUnlocalizedName(), "水星石");
        replace(provider, CTNHMaterials.Glaciostone.getUnlocalizedName(), "霜原石");

        replace(provider, CTNHMaterials.FlowingAmberGold.getUnlocalizedName(), "通流琥珀金");
        replace(provider, CTNHMaterials.NQ_END_OF_GASOLINE.getUnlocalizedName(), "硅岩基终末燃油-NQ");
        replace(provider, CTNHMaterials.LIVING_METAL.getUnlocalizedName(), "活体金属");
        replace(provider, CTNHMaterials.SpecialCompositeSteelM77.getUnlocalizedName(), "特种复合钢-M77");
        replace(provider, CTNHMaterials.HiddenAlloy.getUnlocalizedName(), "幽匿合金");
        replace(provider, CTNHMaterials.SpiritAsh.getUnlocalizedName(), "巫师之骨");
        replace(provider, CTNHMaterials.SteelLeaf.getUnlocalizedName(), "钢叶");
        replace(provider, CTNHMaterials.EclipseShadow.getUnlocalizedName(), "幽影");
        replace(provider, CTNHMaterials.Dragonflame.getUnlocalizedName(), "龙炎");
        replace(provider, CTNHMaterials.PolarIceCore.getUnlocalizedName(), "极寒晶核");
        replace(provider, CTNHMaterials.IllusionIron.getUnlocalizedName(), "幻铁");
        replace(provider, CTNHMaterials.ToxicSwampAmber.getUnlocalizedName(), "毒沼琥珀");
        replace(provider, CTNHMaterials.LightningPattern.getUnlocalizedName(), "雷纹");
        replace(provider, CTNHMaterials.Holystone.getUnlocalizedName(), "圣石");
        replace(provider, CTNHMaterials.Zanite.getUnlocalizedName(), "紫晶石");
        replace(provider, CTNHMaterials.Ambrosium.getUnlocalizedName(), "神能晶");
        replace(provider, CTNHMaterials.Skyjade.getUnlocalizedName(), "穹玉");
        replace(provider, CTNHMaterials.Stratus.getUnlocalizedName(), "云母钢");
        replace(provider, CTNHMaterials.Zenith_essence.getUnlocalizedName(), "§5天顶源质§r");
        replace(provider, CTNHMaterials.BiologicalCultureMediumStockSolution.getUnlocalizedName(), "生物培养基原液");
        replace(provider, CTNHMaterials.SterileBiologicalCultureMediumStockSolution.getUnlocalizedName(), "无菌生物培养基原液");
        replace(provider, CTNHMaterials.EVE.getUnlocalizedName(),"EVE高能粒子");
        replace(provider, CTNHMaterials.Ignitium.getUnlocalizedName(), "腾炎");
        replace(provider, CTNHMaterials.QUASER_MANA.getUnlocalizedName(), "类星体魔力");
        replace(provider, CTNHMaterials.starlight.getUnlocalizedName(), "星能液");
        replace(provider, CTNHMaterials.COMPRESSED_ADAMANTITE.getUnlocalizedName(),"压缩精金");
        replace(provider, CTNHMaterials.COMPRESSED_AETHER.getUnlocalizedName(),"精炼超能以太");
        replace(provider, CTNHMaterials.SUNNARIUM.getUnlocalizedName(),"阳光化合物");
        replace(provider, CTNHMaterials.HIKARIUM.getUnlocalizedName(),"§6光素");
        replace(provider, CTNHMaterials.carbonFluoride.getUnlocalizedName(), "氟化碳");
        replace(provider, CTNHMaterials.siliconFluoride.getUnlocalizedName(), "氟化硅");
        replace(provider, CTNHMaterials.zirconiumTetrachloride.getUnlocalizedName(), "四氯化锆");
        replace(provider, CTNHMaterials.siliconCarbide.getUnlocalizedName(), "碳化硅");
        replace(provider, CTNHMaterials.HotSteam.getUnlocalizedName(), "过热蒸汽");
        replace(provider, CTNHMaterials.HotDeuterium.getUnlocalizedName(), "过热氘");
        replace(provider, CTNHMaterials.HotSodium.getUnlocalizedName(), "过热钠");
        replace(provider, CTNHMaterials.HotSodiumPotassium.getUnlocalizedName(), "过热钠钾合金");
        replace(provider, CTNHMaterials.Eglinalloy.getUnlocalizedName(), "埃格林合金");
        replace(provider, CTNHMaterials.Inconel625.getUnlocalizedName(), "镍铬基合金-625");
        replace(provider, CTNHMaterials.Starmetal.getUnlocalizedName(), "炫星");
        replace(provider, CTNHMaterials.AlfSteel.getUnlocalizedName(), "精灵钢");
        replace(provider, CTNHMaterials.Jasper.getUnlocalizedName(), "碧玉");
        replace(provider, CTNHMaterials.Abyssalalloy.getUnlocalizedName(), "渊狱合金");
        replace(provider, CTNHMaterials.Titansteel.getUnlocalizedName(), "泰坦钢");
        replace(provider, CTNHMaterials.Pikyonium.getUnlocalizedName(), "皮卡优");
        replace(provider, CTNHMaterials.BlackTitanium.getUnlocalizedName(), "黑钛");
        replace(provider, CTNHMaterials.Zircon.getUnlocalizedName(), "锆石");
        replace(provider, CTNHMaterials.Zirkelite.getUnlocalizedName(), "钛锆钍石");
        replace(provider, CTNHMaterials.Alumina.getUnlocalizedName(), "氧化铝");
        replace(provider, CTNHMaterials.PreciousAlloy.getUnlocalizedName(), "贵金属");
        replace(provider, CTNHMaterials.ManaFused.getUnlocalizedName(), "蕴魔");
        replace(provider, CTNHMaterials.CombustibleIce.getUnlocalizedName(), "可燃冰");
        replace(provider, CTNHMaterials.Livingrock.getUnlocalizedName(), "活石");
        replace(provider, CTNHMaterials.icestone.getUnlocalizedName(), "冰石");
        replace(provider, CTNHMaterials.AERIALITE.getUnlocalizedName(), "天空");
        replace(provider, CTNHMaterials.SHADOWIUM.getUnlocalizedName(), "暗影");
        replace(provider, CTNHMaterials.ORICHALCOS.getUnlocalizedName(), "奥利哈刚");
        replace(provider, CTNHMaterials.PHOTONIUM.getUnlocalizedName(), "         光子");
        replace(provider, CTNHMaterials.COLORFUL_GEM.getUnlocalizedName(), "异彩（无材质版）");
        replace(provider, CTNHMaterials.RhodiumSulfurCrystal.getUnlocalizedName(), "铑硫晶");
        replace(provider, CTNHMaterials.RutheniumAmalgam.getUnlocalizedName(), "钌汞齐");
        replace(provider, CTNHMaterials.OsmiumIronSpinel.getUnlocalizedName(), "锇铁尖晶石");
        replace(provider, CTNHMaterials.MeteoricTroilite.getUnlocalizedName(), "陨硫铁镍");
        replace(provider, CTNHMaterials.PalladiumSulfide.getUnlocalizedName(), "硫晶钯矿");
        replace(provider, CTNHMaterials.SolarFlareBlackDiamond.getUnlocalizedName(), "太阳耀斑黑钻");
        replace(provider, CTNHMaterials.Cerite.getUnlocalizedName(), "铈硅石");
        replace(provider, CTNHMaterials.EuropiumFluorite.getUnlocalizedName(), "铕萤石");
        replace(provider, CTNHMaterials.GadoliniteSm.getUnlocalizedName(), "钐硅铍钇");
        replace(provider, CTNHMaterials.Sperrylite.getUnlocalizedName(), "砷铂");
        replace(provider, CTNHMaterials.Wolframite.getUnlocalizedName(), "黑钨");
        replace(provider, CTNHMaterials.Germanite.getUnlocalizedName(), "锗镓");
        replace(provider, CTNHMaterials.Bismuthinite.getUnlocalizedName(), "辉铋");
        replace(provider, CTNHMaterials.Yttrofluorite.getUnlocalizedName(), "钇萤石");
        replace(provider, CTNHMaterials.Rheniite.getUnlocalizedName(), "辉铼");
        replace(provider, CTNHMaterials.Tarkianite.getUnlocalizedName(), "铼钼");
        replace(provider, CTNHMaterials.Crocoite.getUnlocalizedName(), "红铬铅");
        replace(provider, CTNHMaterials.Roquesite.getUnlocalizedName(), "铜铟");
        replace(provider, CTNHMaterials.Smithsonite.getUnlocalizedName(), "菱锌");

        replace(provider, NuclearMaterials.ThoriumHexafluoride.getUnlocalizedName(), "六氟化钍");
        replace(provider, NuclearMaterials.ProtactiniumHexafluoride.getUnlocalizedName(), "六氟化镤");
        replace(provider, NuclearMaterials.NeptuniumHexafluoride.getUnlocalizedName(), "六氟化镎");
        replace(provider, NuclearMaterials.PlutoniumHexafluoride.getUnlocalizedName(), "六氟化钋");
        replace(provider, NuclearMaterials.AmericiumHexafluoride.getUnlocalizedName(), "六氟化镅");
        replace(provider, NuclearMaterials.CuriumHexafluoride.getUnlocalizedName(), "六氟化锔");
        replace(provider, NuclearMaterials.BerkeliumHexafluoride.getUnlocalizedName(), "六氟化锫");
        replace(provider, NuclearMaterials.CaliforniumHexafluoride.getUnlocalizedName(), "六氟化锎");
        replace(provider, NuclearMaterials.EinsteiniumHexafluoride.getUnlocalizedName(), "六氟化锿");
        replace(provider, NuclearMaterials.FermiumHexafluoride.getUnlocalizedName(), "六氟化镄");
        replace(provider, NuclearMaterials.MendeleviumHexafluoride.getUnlocalizedName(), "六氟化钔");
        nuclearTranslation(provider, NuclearMaterials.Thorium233, "钍233");
        nuclearTranslation(provider, GTNNMaterials.Thorium232, "钍232");
        nuclearTranslation(provider, NuclearMaterials.Protactinium233, "镤233");
        nuclearTranslation(provider, NuclearMaterials.Uranium233, "铀233");
        nuclearTranslation(provider, NuclearMaterials.Uranium234, "铀234");
        nuclearTranslation(provider, NuclearMaterials.Uranium239, "铀239");
        nuclearTranslation(provider, NuclearMaterials.Neptunium235, "镎235");
        nuclearTranslation(provider, NuclearMaterials.Neptunium237, "镎237");
        nuclearTranslation(provider, NuclearMaterials.Neptunium239, "镎239");
        nuclearTranslation(provider, NuclearMaterials.Plutonium240, "钋240");
        nuclearTranslation(provider, NuclearMaterials.Plutonium244, "钋244");
        nuclearTranslation(provider, NuclearMaterials.Plutonium245, "钋245");
        nuclearTranslation(provider, NuclearMaterials.Americium241, "镅241");
        nuclearTranslation(provider, NuclearMaterials.Americium243, "镅243");
        nuclearTranslation(provider, NuclearMaterials.Americium245, "镅245");
        nuclearTranslation(provider, NuclearMaterials.Curium245, "锔245");
        nuclearTranslation(provider, NuclearMaterials.Curium246, "锔246");
        nuclearTranslation(provider, NuclearMaterials.Curium247, "锔247");
        nuclearTranslation(provider, NuclearMaterials.Curium250, "锔250");
        nuclearTranslation(provider, NuclearMaterials.Curium251, "锔251");
        nuclearTranslation(provider, NuclearMaterials.Berkelium247, "锫247");
        nuclearTranslation(provider, NuclearMaterials.Berkelium249, "锫249");
        nuclearTranslation(provider, NuclearMaterials.Berkelium251, "锫251");
        nuclearTranslation(provider, NuclearMaterials.Californium251, "锎251");
        nuclearTranslation(provider, NuclearMaterials.Californium252, "锎252");
        nuclearTranslation(provider, NuclearMaterials.Californium253, "锎253");
        nuclearTranslation(provider, NuclearMaterials.Californium256, "锎256");
        nuclearTranslation(provider, NuclearMaterials.Californium257, "锎257");
        nuclearTranslation(provider, NuclearMaterials.Einsteinium253, "锿253");
        nuclearTranslation(provider, NuclearMaterials.Einsteinium255, "锿255");
        nuclearTranslation(provider, NuclearMaterials.Einsteinium257, "锿257");
        nuclearTranslation(provider, NuclearMaterials.Fermium257, "镄257");
        nuclearTranslation(provider, NuclearMaterials.Fermium258, "镄258");
        nuclearTranslation(provider, NuclearMaterials.Fermium259, "镄259");
        nuclearTranslation(provider, NuclearMaterials.Fermium262, "镄262");
        nuclearTranslation(provider, NuclearMaterials.Fermium263, "镄263");
        nuclearTranslation(provider, NuclearMaterials.Mendelevium259, "钔259");
        nuclearTranslation(provider, NuclearMaterials.Mendelevium261, "钔261");
        nuclearTranslation(provider, NuclearMaterials.Mendelevium263, "钔263");
        replace(provider, NuclearMaterials.CarbideUranium235.getUnlocalizedName(), "碳化铀235");
        replace(provider, NuclearMaterials.OxideUranium235.getUnlocalizedName(), "氧化铀235");
        replace(provider, NuclearMaterials.NitrideUranium235.getUnlocalizedName(), "氮化铀235");
        replace(provider, NuclearMaterials.ZirconiumAlloyUranium235.getUnlocalizedName(), "锆合金铀235");
        replace(provider, NuclearMaterials.CarbideUranium238.getUnlocalizedName(), "碳化铀238");
        replace(provider, NuclearMaterials.OxideUranium238.getUnlocalizedName(), "氧化铀238");
        replace(provider, NuclearMaterials.NitrideUranium238.getUnlocalizedName(), "氮化铀238");
        replace(provider, NuclearMaterials.ZirconiumAlloyUranium238.getUnlocalizedName(), "锆合金铀238");
        nuclearTranslation2(provider, GTMaterials.Plutonium239, "钚239");
        nuclearTranslation2(provider, GTMaterials.Plutonium241, "钚241");


        //Config
        provider.add("config.ctnhcore.option.ftbPlugin", "FTB相关");
        provider.add("config.ctnhcore.option.kinetic", "应力相关");
        provider.add("config.ctnhcore.option.enableFTBUltimineOnGTOres", "开启GT矿物连锁");
        provider.add("config.ctnhcore.option.pressorRpmRequirement", "机械辊压厂最低转速需求");
        provider.add("config.ctnhcore.option.pressorSpeedMultiplier", "机械辊压厂加速倍率");
        provider.add("config.ctnhcore.option.pressorStressRequirement", "机械辊压厂应力消耗");
        provider.add("config.ctnhcore.option.mixerRpmRequirement", "机械搅拌厂最低转速需求");
        provider.add("config.ctnhcore.option.mixerSpeedMultiplier", "机械搅拌厂加速倍率");
        provider.add("config.ctnhcore.option.mixerStressRequirement", "机械搅拌厂应力消耗");
        provider.add("config.ctnhcore.option.centrifugeRpmRequirement", "机械离心厂最低转速需求");
        provider.add("config.ctnhcore.option.centrifugeSpeedMultiplier", "机械离心厂加速倍率");
        provider.add("config.ctnhcore.option.centrifugeStressRequirement", "机械离心厂应力消耗");
        provider.add("config.ctnhcore.option.sifterRpmRequirement", "机械筛选厂最低转速需求");
        provider.add("config.ctnhcore.option.sifterSpeedMultiplier", "机械筛选厂加速倍率");
        provider.add("config.ctnhcore.option.sifterStressRequirement", "机械筛选厂应力消耗");
        provider.add("config.ctnhcore.option.extractorRpmRequirement", "机械提取厂最低转速需求");
        provider.add("config.ctnhcore.option.extractorSpeedMultiplier", "机械提取厂加速倍率");
        provider.add("config.ctnhcore.option.extractorStressRequirement", "机械提取厂应力消耗");
        provider.add("config.ctnhcore.option.latheRpmRequirement", "机械车床厂最低转速需求");
        provider.add("config.ctnhcore.option.latheSpeedMultiplier", "机械车床厂加速倍率");
        provider.add("config.ctnhcore.option.latheStressRequirement", "机械车床厂应力消耗");
        provider.add("config.ctnhcore.option.laserRpmRequirement", "机械激光厂最低转速需求");
        provider.add("config.ctnhcore.option.laserSpeedMultiplier", "机械激光厂加速倍率");
        provider.add("config.ctnhcore.option.laserStressRequirement", "机械激光厂应力消耗");

        //Recipe Type
        provider.add("gtceu.phase_inversion","反相蚀刻");
        provider.add("gtceu.underfloor_heating_system", "地暖");
        provider.add("gtceu.astronomical_observatory", "天文台");
        provider.add("gtceu.photovoltaic_power", "光伏发电");
        provider.add("gtceu.slaughter_house", "屠宰场");
        provider.add("gtceu.big_dam", "三峡大坝");
        provider.add("gtceu.coke_oven", "焦化塔");
        provider.add("gtceu.demon_will_generator", "恶魔意志发电");
        provider.add("gtceu.meadow","牧场养殖");
        provider.add("gtceu.chemical_generator","化学能发电");
        provider.add("gtceu.void_miner","虚空采矿");
        provider.add("gtceu.cultivation_room", "培养");
        provider.add("gtceu.sintering_kiln","烧结");
        provider.add("gtceu.chemical_vapor_deposition","化学气相沉积");
        provider.add("gtceu.pvb_recipe","物理气相沉积");
        provider.add("gtceu.martial_morality_eye","武德之眼");
        provider.add("gtceu.advanced_coke_oven","高级焦炉");
        provider.add("gtceu.dimensional_gas_collection_chamber","维度集气");
        provider.add("gtceu.condensing_discrete","冷凝离散");
        provider.add("gtceu.ion_exchanger","离子交换");
        provider.add("gtceu.large_steel_furnace","大型钢制熔炉");
        provider.add("gtceu.large_steel_alloy_furnace","大型钢制合金炉");
        provider.add("gtceu.fuel_refining", "燃料精炼");
        provider.add("gtceu.water_power", "水力发电");
        provider.add("gtceu.bio_reactor", "生物反应");
        provider.add("gtceu.resonant_assemble","振动共鸣组装");
        provider.add("gtceu.wind_power_array", "风力发电阵列");
        provider.add("gtceu.crystallizer", "结晶反应");
        provider.add("gtceu.mana_generator", "魔力发电");
        provider.add("gtceu.season_steam","季节共鸣器");
        provider.add("gtceu.naq_mk1", "超能燃料");
        provider.add("gtceu.bedrock_drilling_rigs", "基岩钻机");
        provider.add("gtceu.plasma_condenser", "等离子冷凝");
        provider.add("gtceu.vacuum_sintering", "真空烧结");
        provider.add("gtceu.dimensional_gas_collection", "维度集气");
        provider.add("gtceu.silica_rock_fuel_refinery", "硅岩燃料精炼");
        provider.add("gtceu.hellforge", "狱火锻炉");
        provider.add("gtceu.digital_well_of_suffer", "数字化苦难之井");
        provider.add("gtceu.twisted_fusion", "扭曲聚变反应堆");
        provider.add("gtceu.nano_generator","摩擦发电");
        provider.add("gtceu.quasar_eye","类星体之眼");
        provider.add("gtceu.decay_pools", "衰变罐");
        provider.add("gtceu.fermenting", "发酵罐");
        provider.add("gtceu.beams", "戴森光束");
        provider.add("gtceu.altar", "血之祭坛");
        provider.add("gtceu.wood_bionics", "§e林海树场");
        provider.add("gtceu.sinope", "规模化化工");
        provider.add("gtceu:quasar_eye","类星体发电");
        provider.add("gtceu.personal_computer", "个人电脑");
        provider.add("gtceu.accelerator_upmode","粒子加速：加速模式");
        provider.add("gtceu.accelerator_downmode","粒子加速：减速模式");
        provider.add("gtceu.mana_reactor","魔力反应");
        provider.add("gtceu.arc_generator","物质撕裂湮灭");
        provider.add("gtceu.arc_reactor","电弧发生");
        provider.add("gtceu.magic_fuel_generator","魔力燃料精炼");
        provider.add("gtceu.mechanical_pressor_recipes", "机械辊压");
        provider.add("gtceu.mechanical_mixer_recipes", "机械搅拌");
        provider.add("gtceu.mechanical_lathe_recipes", "机械车床");
        provider.add("gtceu.mechanical_laser_recipes", "机械激光");
        provider.add("gtceu.mechanical_extractor_recipes", "机械提取");
        provider.add("gtceu.mechanical_sifter_recipes", "机械筛选");
        provider.add("gtceu.mechanical_centrifuge_recipes", "机械离心");
        provider.add("gtceu.photovoltaic_generator","光伏发电");
        provider.add("gtceu.photovoltaic_assember","太空光伏组装");
        provider.add("gtceu.gas_centrifuge", "气体离心");
        provider.add("gtceu.nuclear_reactor", "核能反应");
        provider.add("gtceu.hot_coolant_turbine", "热冷却液涡轮");
        provider.add("gtceu.mana_condenser", "魔力凝集");
        provider.add("gtceu.quasar_create","类星体创生");
        provider.add("gtceu.differential_centrifuge", "差速离心");
        provider.add("gtceu.ultrasonication", "超声破碎");
        provider.add("gtceu.eye_of_quasar","§5类星体§r§1之§c眼");
        provider.add("gtceu.industrial_altar", "§4工业血之祭坛§r");
        provider.add("gtceu.mana_seperator", "魔力分选");
        provider.add("gtceu.gaia_reactor", "盖亚反应");
        provider.add("gtceu.greenhouse", "温室");
        provider.add("gtceu.meteor_capturer", "集成式坠星位标");

        provider.add("ctnh.test_terminal.lack_error","在%s处缺少");
        provider.add("ctnh.test_terminal.wrong_error","在%s处应为");
        provider.add("ctnh.test_terminal.position","(%s,%s,%s)");
        provider.add("ctnh.test_terminal.error_info","(%s)");
        provider.add("ctnh.test_terminal.success","一切正常！");

        provider.add("ctnh.testing_terminal.tooltip.1","用于检测多方块搭建时产生的错误");
        provider.add("ctnh.testing_terminal.tooltip.2","右键多方块的主方块以显示错误信息");
        provider.add("ctnh.me_advanced_terminal.tooltip.1","§l格雷科技-多方块结构终端-异步成型模式");
        provider.add("ctnh.me_advanced_terminal.tooltip.2","复刻了曾经的旗舰款，终端屏幕上闪烁着久违的画面");
        provider.add("ctnh.me_advanced_terminal.tooltip.3","通过ME无线访问点链接到网络");

//        "item.gtmthings.advanced_terminal": "§b高级终端",
//                "item.gtmthings.advanced_terminal.setting.title": "高级终端设置",
//                "item.gtmthings.advanced_terminal.setting.1": "线圈等级",
//                "item.gtmthings.advanced_terminal.setting.1.tooltip": "设置优先自动放置的线圈等级。",
//                "item.gtmthings.advanced_terminal.setting.2": "重复结构次数",
//                "item.gtmthings.advanced_terminal.setting.2.tooltip": "用于设置可重复结构(蒸馏塔、装配线等)的重复部分放置次数",
//                "item.gtmthings.advanced_terminal.setting.3": "无仓室模式",
//                "item.gtmthings.advanced_terminal.setting.3.tooltip": "是否启用无仓室模式(0:不启用,1:启用)\n启用无仓室模式后不会在非唯一时放置各种仓室。",
//                "item.gtmthings.advanced_terminal.setting.4": "线圈替换模式",
//                "item.gtmthings.advanced_terminal.setting.4.tooltip": "是否启用线圈替换模式(0:不启用,1:启用)\n启用线圈替换模式会将所有线圈替换为线圈等级中指定的线圈。",

        provider.add("item.ctnh.me_advanced_terminal.setting.title","多方块结构成型配置");
        provider.add("item.ctnh.me_advanced_terminal.setting.1","线圈等级");
        provider.add("item.ctnh.me_advanced_terminal.setting.1.tooltip","设置自动放置的线圈等级(0:不指定等级)\n设置后会忽略结构本身的要求");
        provider.add("item.ctnh.me_advanced_terminal.setting.2","重复结构次数");
        provider.add("item.ctnh.me_advanced_terminal.setting.2.tooltip","设置可重复结构(蒸馏塔、装配线等)的重复部分放置次数\n对超净间无效");
        provider.add("item.ctnh.me_advanced_terminal.setting.3","无仓室模式");
        provider.add("item.ctnh.me_advanced_terminal.setting.3.tooltip","是否启用无仓室模式(0:不启用,1:启用)\n启用无仓室模式后不会放置任何仓室");
        provider.add("item.ctnh.me_advanced_terminal.setting.4","线圈替换模式");
        provider.add("item.ctnh.me_advanced_terminal.setting.4.tooltip","是否启用线圈替换模式(0:不启用,1:启用)\n启用线圈替换模式会将所有线圈替换为指定等级的线圈\n请确保物品栏中有空间存放替换下来的线圈");
        provider.add("item.ctnh.me_advanced_terminal.setting.5","使用AE存储");
        provider.add("item.ctnh.me_advanced_terminal.setting.5.tooltip","是否启用AE库存(0:不启用,1:启用)\n启用后将优先在AE库存中检索\n通过ME无线访问点链接到网络");
        provider.add("item.ctnh.me_advanced_terminal.setting.6","放置流体");
        provider.add("item.ctnh.me_advanced_terminal.setting.6.tooltip","是否启用流体放置(0:不启用,1:启用)\n启用后将检索并消耗物品栏/背包流体容器中的或AE库存中的流体\n可堆叠的流体容器须保证堆叠数为1");
        provider.add("item.ctnh.me_advanced_terminal.setting.7","在流体中放置");
        provider.add("item.ctnh.me_advanced_terminal.setting.7.tooltip","是否在流体中放置方块(0:不启用,1:启用)\n启用后会将空间中的流体视为空位\n与“放置流体”同时启用时，不会在流体中放置流体");
        provider.add("item.ctnh.me_advanced_terminal.setting.8","拆除模式");
        provider.add("item.ctnh.me_advanced_terminal.setting.8.tooltip","是否启用拆除模式(0:不启用,1:启用)\n请确保物品栏中有空间存放拆除的方块\n与“使用AE存储”同时启用时，拆除的方块会自动存入AE存储");
        provider.add("item.ctnh.me_advanced_terminal.setting.9","多方块成型配置");
        provider.add("item.ctnh.me_advanced_terminal.setting.9.tooltip","多方块成型配置");

        provider.add("ctnh.simple_nutritious_meal.tooltip.1","能维持机体基本的生理功能");
        provider.add("item.ctnh.ecological_star.desc", "蕴含生态圈的所有精华");
        provider.add("item.sculk_cell.desc", "分化....");
        provider.add("ctnh.tooltips.simplecomputationmachine","执行大于电压等级HV的配方时,需要2^(配方等级-HV)CWU/t算力");

        provider.add("ctnh.recipe.industrial_altar.info.0","消耗/输入的lp量:%.1f");

        provider.add("ctnh.recipe.quasar_eye.info.0","启动消耗:%.1f");
        provider.add("ctnh.recipe.quasar_eye.info.1","能量等级: %d");
        provider.add("ctnh.recipe.quasar_eye.info.2","启动等级: %d");

        provider.add("ctnh.recipe.hellforge.info.minimum_drain", "最少：%s意志");
        provider.add("ctnh.recipe.hellforge.info.drain", "消耗：%s意志");

        provider.add("ctnh.recipe.kinetic.info.stress_output", "输出应力: %d");
        provider.add("ctnh.recipe.kinetic.info.stress_input", "输入应力: %d");

        provider.add("ctnh.recipe.accelerator.mode.nu","模式：加速中子");
        provider.add("ctnh.recipe.accelerator.mode.proton","模式：加速质子");
        provider.add("ctnh.recipe.accelerator.mode.element","模式：加速电子");
        provider.add("ctnh.recipe.accelerator.mode.element.consume","加速类型：电子");
        provider.add("ctnh.recipe.accelerator.mode.proton.consume","加速类型：质子");
        provider.add("ctnh.recipe.accelerator.mode.nu.consume","加速类型：中子");
        provider.add("ctnh.recipe.accelerator.mode.speed.m","需求速度：%.2fMev");
        provider.add("ctnh.recipe.accelerator.mode.speed.g","需求速度:%.2fGev");

        //Common Tooltips
        provider.add("ctnh.common_tooltip.parallel_hatch", "·允许使用并行控制仓");
        provider.add("ctnh.common_tooltip.subtick_overclock", "当配方运行时间小于1t时,会自动计算并行");
        provider.add("ctnh.common_tooltip.perfect_overclock", "无损超频！");
        provider.add("ctnh.common_tooltip.steel_machine.0", "只能使用HV级能源仓及以下等级");
        provider.add("ctnh.common_tooltip.steel_machine.1", "最大并行为32");

        provider.add("ctnh.common_tooltip.mana_machine.0", "魔法，神奇吧");
        provider.add("ctnh.common_tooltip.mana_machine.1","§c魔力机器不再拥有任何并行");
        provider.add("ctnh.common_tooltip.mana_machine.2","运行中的每一并行提供1%时间和耗能减免，至多减少75%");
        provider.add("ctnh.common_tooltip.mana_machine.3","§4当电压低于LUV且配方电压等级等于当前配方电压时，使配方时间增加50%（魔力组装只增加1%)");
        provider.add("ctnh.common_tooltip.mana_machine.4","放入§5类星体符文§r以在100次配方内启用§5星体之眼模式§r：并行变为无限，并行不再提供额外的时间与电压减少。启动此模式不消耗类星体符文");

        provider.add("ctnh.common_tooltip.mana_generator.0","机器的最大发电量为(配方发电量)*符文提供倍率*转子最大转速*转子发电效率/100*机器自身提供发电量倍率");
        provider.add("ctnh.common_tooltip.mana_generator.1","机器的实际发电量为(机器当前转子转速/转子最大转子)^2*机器最大发电量");
        provider.add("ctnh.common_tooltip.mana_generator.2","§c注意：运行时需要额外消耗液态魔力，如果消耗液态魔力不足，则本次发电配方发电量将会除以5，通过机器UI可以获得魔力消耗量");
        provider.add("ctnh.common_tooltip.mana_generator.3", "在机器内放入符文可以提升发电效率：\n" +
                "  一级符文：发电量x1.5，魔力消耗量x0.8，每5秒符文消耗概率：0.2\n" +
                "  二级符文：发电量x2。4，魔力消耗量x1.2，每5秒符文消耗概率：0.1\n" +
                "  三级符文：发电量x3，魔力消耗量x0.8，每5秒符文消耗概率：0.05\n" +
                "   四级符文：发电量x4， 魔力消耗量x0.6，每5秒符文消耗概率：0.025\n" +
                "  五级符文： 发电量x5, 魔力消耗量x0.3， 每5秒符文消耗概率：0.02\n" +
                "  §5类星体符文§r: 发电量*999, 消耗量*999，§c在被吞噬的星辰中绽放最终的光芒§r");
        provider.add("ctnh.common_tooltip.basic_mana_consume", "每秒基础消耗4mB液态魔力，电压每超过§7LV§r一级，消耗量变为原来的两倍");
        provider.add("ctnh.common_tooltip.advanced_mana_consume", "每秒基础消耗10mB液态魔力，电压每超过§7LV§r一级，消耗量变为原来的两倍");
        provider.add("ctnh.common_tooltip.super_mana_consume", "每秒基础消耗12mB液态魔力，电压每超过§7LV§r一级，消耗量变为原来的两倍");

        provider.add("ctnh.common_tooltip.zenith_machine.0","§5超越魔法");
        provider.add("ctnh.common_tooltip.zenith_machine.1","在达到LUV电压后，如果§5天顶源质§r足够，每次运行会消耗(60*(当前电压等级-6))的天顶源质，获得2^(当前电压等级-6)的并行数，最大并行数取决于当前电压。但是不输入天顶源质会损失4并行数。");
        provider.add("ctnh.common_tooltip.zenith_machine.2","注意，源质的消耗与当前你输入的物品数无关，即使没有并行，我也会克扣你的天顶源质，当并行达到上限后仍然会消耗天顶源质，但是消耗量固定为60");

        //Machine Info
        provider.add("ctnh.multiblock.underfloor_heating_system.info.efficiency", "效率：%d");
        provider.add("ctnh.multiblock.underfloor_heating_system.info.rate", "速率：%s");
        provider.add("ctnh.multiblock.underfloor_heating_system.info.rate.tooltip", "减少蒸汽的消耗来降低地暖的发热功率");
        provider.add("ctnh.multiblock.underfloor_heating_system.info.rate_modify", "调节速率：");
        provider.add("ctnh.multiblock.underfloor_heating_system.info.steam_consumption", "蒸汽消耗速率：%d");

        provider.add("ctnh.multiblock.photovoltaic_power_station.info.invalid", "有方块阻挡");
        provider.add("ctnh.multiblock.photovoltaic_power_station.info.night", "光照过于微弱");
        provider.add("ctnh.multiblock.photovoltaic_power_station.info.1", "发电效率：%s%%");
        provider.add("ctnh.multiblock.photovoltaic_power_station.info.2", "产能功率：%s/%s EU/t");

        provider.add("ctnh.mutliblock.wind_power_array.info.network_machine", "发电网络机器数：%d");
        provider.add("ctnh.mutliblock.wind_power_array.info.network_machine_efficiency", "发电效率：%d");
        provider.add("ctnh.mutliblock.wind_power_array.info.network_dirty", "网络将在%d秒后重建");

        provider.add("ctnh.multiblock.slaughter_house.info.mobcount", "怪物种类：%d (%s)");

        provider.add("ctnh.multiblock.fermenting_tank.info.growing_temperature", "生长温度：§2%d°C§r");
        provider.add("ctnh.multiblock.fermenting_tank.info.growth_efficiency", "生长效率：%d%%");

        provider.add("ctnh.multiblock.mana_turbine.info.efficiency", "发电效率：%d%%");
        provider.add("ctnh.multiblock.mana_turbine.info.consumption_rate", "运行魔力消耗量：%d mb");

        provider.add("ctnh.multiblock.naq_reactor.info.temperature", "§c内核温度: %d");
        provider.add("ctnh.multiblock.naq_reactor.info.nickel_consumption", "镍等离子体消耗量: %d");
        provider.add("ctnh.multiblock.naq_reactor.info.parallel_count", "发电并行数: %d");

        provider.add("ctnh.multiblock.demon_generator.info.default","专精强化：无");
        provider.add("ctnh.multiblock.demon_generator.info.vengeful","专精强化：复仇");
        provider.add("ctnh.multiblock.demon_generator.info.corrosive","专精强化：腐蚀");
        provider.add("ctnh.multiblock.demon_generator.info.steadfast","专精强化：坚韧");
        provider.add("ctnh.multiblock.demon_generator.info.destructive","专精强化：破坏");
        provider.add("ctnh.multiblock.demon_generator.info.1","浓度差异：%s");
        provider.add("ctnh.multiblock.demon_generator.info.boosted","§4血祭模式开启，生命源质强化中");

        provider.add("ctnh.multiblock.sweat_shop.info.villager_count","员工数量：%s");
        provider.add("ctnh.multiblock.sweat_shop.info.basic_rate","基础效率：x%s");

        provider.add("ctnh.multiblock.void_miner.info.cryotheum", "极寒之凛冰消耗：%d ");
        provider.add("ctnh.multiblock.void_miner.info.pyrotheum", "烈焰之炽焱消耗：%d ");
        provider.add("ctnh.multiblock.void_miner.info.overheat", "过热!!!");

        provider.add("ctnh.multiblock.blaze_blast_furnace.info.pyrotheum", "烈焰之炽焱：%d mB");

        provider.add("ctnh.multiblock.mega_lcr.info.coil","当前线圈温度:%s");
        provider.add("ctnh.multiblock.mega_lcr.info.speed","当前配方时间倍率:%s");

        provider.add("ctnh.multiblock.industrial_primitive_blast_furnace.info.parallel_count", "并行数：%d");

        provider.add("ctnh.multiblock.water_power_station.info.0", "水量：%d");
        provider.add("ctnh.multiblock.water_power_station.info.1", "线圈效率：%d%%");
        provider.add("ctnh.multiblock.water_power_station.info.2", "产能功率：%d/%d EU/t");

        provider.add("ctnh.multiblock.forest_machine.info.humidity", "湿度值：%d");
        provider.add("ctnh.multiblock.forest_machine.info.parallel_count", "并行数：%d");

        provider.add("ctnh.multiblock.zenith_machine.info.max_parallel","最大并行数：%d");
        provider.add("ctnh.multiblock.zenith_machine.info.now_parallel","当前并行数：%d");

        provider.add("ctnh.multiblock.industrial_altar.info.current_lp","当前含有lp量:%d");
        provider.add("ctnh.multiblock.industrial_altar.info.max_lp","最大lp量:%d");

        provider.add("ctnh.multiblock.quasar_eye.info.rune_energy","符文能量：%.2f");
        provider.add("ctnh.multiblock.quasar_eye.info.rune_consumption","当前消耗符文能量速率:%.2f /100tick");
        provider.add("ctnh.multiblock.quasar_eye.info.mana_model","当前魔力燃料等级:%d");
        provider.add("ctnh.multiblock.quasar_eye.info.mana_production","当前发电效率:%.2f");
        provider.add("ctnh.multiblock.quasar_eye.info.quasar_parallel","时间并行:%.2f");
        provider.add("ctnh.multiblock.quasar_eye.info.consumption_parallel","能源消耗率:%.2f");
        provider.add("ctnh.multiblock.quasar_eye.info.0","积累的能量:%s");

        provider.add("ctnh.multiblock.hellforge.info.will", "意志：%s");

        provider.add("ctnh.multiblock.astronomical.info.invalid", "只能在夜晚使用");

        provider.add("ctnh.multiblock.sinope_chemical.info.level","线圈加速倍率:%d");
        provider.add("ctnh.multiblock.sinope_chemical.info.parallel","并行数:%d");

        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.overload","§c警告：机器过载！！！");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.overload_1","§c机器过载度:%d/%d");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.crash","§c机器已损坏");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.mana","当前魔力量:%.4fM");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.twist_consumption","扭曲符文消耗概率:%.2f");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.starlight_consumption","星光符文消耗概率:%.2f");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.overload_2","§c！！！警告：能量溢出！！！");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.max_mana","魔力上限:%.4fM");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.mana_required","魔力需求:%.2fM");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.mana_consumption","消耗魔力:%.2fM");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.time","运行时间倍率:%.2f");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.eut_consumption","消耗能源倍率:%.2f");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.stable","魔力稳定值:%.2f");

        provider.add("ctnh.multiblock.wide_accelerator.info.nu_speed","中子速度:%.2fMev");
        provider.add("ctnh.multiblock.wide_accelerator.info.proton_speed","质子速度:%.2fMev");
        provider.add("ctnh.multiblock.wide_accelerator.info.electric_speed","电子速度:%.2fMev");
        provider.add("ctnh.multiblock.wide_accelerator.info.consume","电量消耗倍率:%.2f");

        provider.add("ctnh.multiblock.wide_accelerator.gui.electric","电子轨道");
        provider.add("ctnh.multiblock.wide_accelerator.gui.nu","中子轨道");
        provider.add("ctnh.multiblock.wide_accelerator.gui.proton","原子轨道");
        provider.add("ctnh.multiblock.wide_accelerator.gui.name","访问轨道");

        provider.add("ctnh.multiblock.arcgenerator.info.0","电弧最大强度:%d");
        provider.add("ctnh.multiblock.arcgenerator.info.1","电弧强度:%d");
        provider.add("ctnh.multiblock.arcgenerator.info.2","支持最大效率:%.2f%%");
        provider.add("ctnh.multiblock.arcgenerator.info.3","当前效率:%.2f%%");

        provider.add("ctnh.eternalgarden.info.fire","当前烧煤花温度:%.1f");
        provider.add("ctnh.eternalgarden.info.eat","当前彼方兰营养值:%d");

        provider.add("ctnh.spacephotovoltaicbasestation.jei.error.pv_block","§c必须使用同种光伏方块");

        provider.add("ctnh.spacephotovoltaicbasestation.recipe.pvc_tier","需求光伏方块等级: %d");
        provider.add("ctnh.spacephotovoltaicbasestation.recipe.eut_model","模拟电压消耗: %d EUt");

        provider.add("ctnh.spacephotovoltaicbasestation.info.pvc_tier.0","当前光伏方块等级:%d");
        provider.add("ctnh.spacephotovoltaicbasestation.info.pvc_tier.1","当前结构耐热等级:%d");
        provider.add("ctnh.spacephotovoltaicbasestation.info.pvc_tier.2","当前结构发电量:%.2f");
        provider.add("ctnh.spacephotovoltaicbasestation.info.pvc_tier.3","当前维度光倍率:%d");

        //Machine tooltip
        provider.add("gtceu.machine.parallel_hatch_mk9.tooltip", "允许同时处理至多1024个配方。") ;
        provider.add("gtceu.machine.parallel_hatch_mk10.tooltip", "允许同时处理至多4096个配方。");
        provider.add("gtceu.machine.parallel_hatch_mk11.tooltip", "允许同时处理至多16384个配方。");
        provider.add("gtceu.machine.parallel_hatch_mk12.tooltip", "允许同时处理至多65536个配方。");
        provider.add("gtceu.machine.parallel_hatch_mk13.tooltip", "允许同时处理至多262144个配方。");
        provider.add("gtceu.machine.parallel_hatch_mk14.tooltip", "允许同时处理至多1048576个配方。");
        provider.add("block.ctnhcore.luv_compressed_fusion_reactor", "压缩核聚变反应堆控制电脑 MK-I");
        provider.add("block.ctnhcore.zpm_compressed_fusion_reactor", "压缩核聚变反应堆控制电脑 MK-II");
        provider.add("block.ctnhcore.uv_compressed_fusion_reactor", "压缩核聚变反应堆控制电脑 MK-III");
        provider.add("gtceu.multiblock.laser.tooltip", "允许使用激光仓");
        provider.add("ctnh.copyright.info", "§6由CTNH添加");
        provider.add("ctnh.recipe_type.info", "配方类型：%s");

        provider.add("ctnh.copyright.magic.info","§bCTNH：工业魔力学");


        provider.add("ctnh.multiblock.plasma_condenser.tooltip.1", "氤氲之气，凝为霜露");

        provider.add("ctnh.multiblock.forest_sea.tooltip.1", "手植千木，绿荫千秋");
        provider.add("ctnh.multiblock.forest_sea.tooltip.2", "林海树场是一个只消耗水来产出大量木材的大机器");
        provider.add("ctnh.multiblock.forest_sea.tooltip.3", "每5s进行一次水储量的判定");
        provider.add("ctnh.multiblock.forest_sea.tooltip.4", "水充足时，增加1%的湿度值");
        provider.add("ctnh.multiblock.forest_sea.tooltip.5", "水不足时，减少10%的湿度值");
        provider.add("ctnh.multiblock.forest_sea.tooltip.6", "配方运行时间不变，但并行值会随湿度值与电压等级上升");
        provider.add("ctnh.multiblock.forest_sea.tooltip.7", "比温室好！");

        provider.add("ctnh.multiblock.cultivation_room.tooltip.1", "菌群孕育，菌种滋长");
        provider.add("ctnh.multiblock.cultivation_room.tooltip.2", "运用好这台机器来繁殖那些难以获取的真菌和细菌");

        provider.add("ctnh.multiblock.sweat_shop.tooltip.0","生产资料与剩余价值");
        provider.add("ctnh.multiblock.sweat_shop.tooltip.1","工厂内的村民数量决定了工作效率，配方耗时x(2/村民数量)");
        provider.add("ctnh.multiblock.sweat_shop.tooltip.2","工厂内的有效员工数量受限于工厂大小，初始上限为4，工厂长度每增加4格上限+1");
        provider.add("ctnh.multiblock.sweat_shop.tooltip.3","放入的生产资料(机器)决定了可以工作的配方：\n动力辊压机----卷板机配方\n动力搅拌机----搅拌机配方\n车床----车床配方\n离心机----离心机配方\n烈焰人燃烧室----提取机配方\n工作盆----流体成型配方\n粉碎轮----研磨机配方\n动力锯----线材轧机配方\n激光加工器----激光蚀刻配方\n==============================");
        provider.add("ctnh.multiblock.sweat_shop.tooltip.4","放入的生产资料(机器)数量决定了对应工作配方的并行数：并行数 = sqrt(机器数)");
        provider.add("ctnh.multiblock.sweat_shop.tooltip.5","放入机械手可以提高整体配方运行速度，配方耗时x (1/1 + 0.25 * sqrt(机械手数))");
        provider.add("ctnh.multiblock.sweat_shop.tooltip.6","放入机器的多样性会提高配方运行速度");
        provider.add("ctnh.multiblock.sweat_shop.tooltip.7","基础配方耗时为2倍，配方所需的电压等级越高，基础耗时x(配方等级的平方)");
        provider.add("ctnh.multiblock.sweat_shop.tooltip.8","每5秒机器会消耗(员工数量)份简易员工餐");

        provider.add("ctnh.multiblock.demon_will_generator.tooltip.0","借用恶魔之力");
        provider.add("ctnh.multiblock.demon_will_generator.tooltip.1","利用机器两侧的区块内的恶魔意志浓度差发电，浓度差与发电量呈指数关系");
        provider.add("ctnh.multiblock.demon_will_generator.tooltip.2","以机器两侧的恶魔合金方块处的意志浓度为基准进行计算");
        provider.add("ctnh.multiblock.demon_will_generator.tooltip.3","两侧区块中的各种恶魔意志的多样性会影响发电效率");
        provider.add("ctnh.multiblock.demon_will_generator.tooltip.4","机器内可以放入意志核心，将机器转化为对于某种意志专精的模式，该模式下每秒会有5%的概率消耗一个核心");
        provider.add("ctnh.multiblock.demon_will_generator.tooltip.5","机器内的符文方块可替换，从而起到不同的增益效果:\n§4献祭符文和牺牲符文----提高生命源质强化模式的发电倍率§r\n§3速度符文----提升一次配方运行的时长（节省恶魔意志消耗）§r\n§e增容符文----每一个符文增加2点恶魔意志浓度差§r\n§c超容符文----每一个符文增加百分之2的恶魔意志浓度差（叠乘）§r\n==============================");
        provider.add("ctnh.multiblock.demon_will_generator.tooltip.6","输入§4生命源质§r开启血祭模式，发电量翻倍，每秒消耗§a100mb§r的生命源质");

        provider.add("ctnh.multiblock.naq_reactor_mk3.tooltip.1", "浩瀚能量，天地震动");
        provider.add("ctnh.multiblock.naq_reactor_mk3.tooltip.2", "利用超能燃料进行发电,无镍等离子体时无法完全消耗燃料");
        provider.add("ctnh.multiblock.naq_reactor_mk3.tooltip.3", "机器构型中必须有一个动力仓");
        provider.add("ctnh.multiblock.naq_reactor_mk3.tooltip.4", "随着内核温度上升,发电效率增大");

        provider.add("ctnh.multiblock.meadow.tooltip.0", "自动化放牧");
        provider.add("ctnh.multiblock.meadow.tooltip.1", "只能养鸡牛羊猪，牛会产生皮革，羊会产生羊毛，鸡会产生鸡蛋，猪会产生猪肉");
        provider.add("ctnh.multiblock.meadow.tooltip.2", "牧场里每多一只动物，产生的排泄物数量就会提升，养的动物越多，产物越多");
        provider.add("ctnh.multiblock.meadow.tooltip.3", "只有动物跑出去时，你才知道你不是在种菜！");

        provider.add("ctnh.multiblock.fermenting_tank.tooltip.0", "一个专为微生物提供的生长罐，注意时刻关注他！");
        provider.add("ctnh.multiblock.fermenting_tank.tooltip.1", "发酵罐的生物生长机制：");
        provider.add("ctnh.multiblock.fermenting_tank.tooltip.2", "当发酵罐温度处于§236§r至§238§r度之间时为最适生长温度，配方获得1.2倍效率，越偏离最适生长温度，配方效率越低，最低为三分之一");
        provider.add("ctnh.multiblock.fermenting_tank.tooltip.3", "微生物的生长符合逻辑斯蒂方程，当输入仓内液体体积为容量的一半时，§2生长效率达到两倍§r，而满仓和空仓时生长效率最低，保底为20%");

        provider.add("ctnh.multiblock.void_miner.tooltip.0", "取天材，掘地精");
        provider.add("ctnh.multiblock.void_miner.tooltip.1", "虚空采矿场自动生成并提取矿石");
        provider.add("ctnh.multiblock.void_miner.tooltip.2", "如果你对矿物需求极大，虚空采矿机是必不可少的帮手");
        provider.add("ctnh.multiblock.void_miner.tooltip.3", "一次性输入100,000,000mB的钻井液，极寒之凛冰和烈焰之炽焱会在升降温度时消耗");
        provider.add("ctnh.multiblock.void_miner.tooltip.4", "当温度达到25000K时，虚空采矿机将进入强制降温模式，请交替输入烈焰之炽焱和极寒之凛冰来控制温度");
        provider.add("ctnh.multiblock.void_miner.tooltip.5", "当温度降至0K时，虚空采矿机将恢复正常工作模式");
        provider.add("ctnh.multiblock.void_miner.tooltip.6", "在奇数次运行前，机器会试图消耗烈焰之炽焱来升温。初始烈焰之炽焱消耗量为1000mb,若成功消耗,则\uD835\uDC47 将会增加 ⌊(\uD835\uDC49 ÷ 100)⌋,接着\uD835\uDC49 将会自乘以 1.02");
        provider.add("ctnh.multiblock.void_miner.tooltip.7", "在偶数次运行前，机器会试图消耗极寒之凛冰来见降温。初始极寒之凛冰消耗量为1000mb,若成功消耗,则 \uD835\uDC47 将会降低 ⌊(\uD835\uDC49 ÷ 100)⌋,接着\uD835\uDC49 将会自乘以 1.02");
        provider.add("ctnh.multiblock.void_miner.tooltip.8", "温度越高，虚空采矿场的工作效率越高");

        provider.add("ctnh.multiblock.large_fermenting_tank.tooltip.0", "高效工业化发酵生产");
        provider.add("ctnh.multiblock.large_fermenting_tank.tooltip.1", "可接入附属结构，在对应位置连接上一个大发酵瓶后，可以根据发酵瓶中的液体种类提升保底效率：水(50%)，简易培养基(150%)，无菌培养基(200%)");

        provider.add("ctnh.multiblock.large_bottle.tooltip.0", "真是一个大罐子");
        provider.add("ctnh.multiblock.large_bottle.tooltip.1", "可以存储10000桶液体");
        provider.add("ctnh.multiblock.large_bottle.tooltip.2", "与大型发酵罐一起使用时，其中的液体会以§e100mb/s§r的速度消耗");

        provider.add("ctnh.multiblock.digestion_tank.tooltip.0", "其实产生的是很有价值的原料......");
        provider.add("ctnh.multiblock.digestion_tank.tooltip.1", "化粪池堆肥机制：");
        provider.add("ctnh.multiblock.digestion_tank.tooltip.2", "当化粪池温度处于§236§r至§238§r度之间时为最适生长温度，配方获得1.2倍效率，越偏离最适生长温度，配方效率越低，最低为三分之一");

        provider.add("ctnh.multiblock.blaze_blast_furnace.tooltip.0", "比电力高炉快");
        provider.add("ctnh.multiblock.blaze_blast_furnace.tooltip.1", "每秒基础消耗§a10mB§r烈焰之炽焱，电压每超过§6HV§r一级，消耗量变为原来的两倍");
        provider.add("ctnh.multiblock.blaze_blast_furnace.tooltip.2", "运行耗能x0.75");
        provider.add("ctnh.multiblock.blaze_blast_furnace.tooltip.3", "允许一次性处理8个配方");

        provider.add("ctnh.multiblock.large_steel_furnace.tooltip.0", "钢质熔炉");

        provider.add("ctnh.multiblock.large_steel_alloy_furnace.tooltip.0", "钢质合金炉");

        provider.add("ctnh.multiblock.advanced_coke_oven.tooltip.0", "高级焦炉");
        provider.add("ctnh.multiblock.advanced_coke_oven.tooltip.1", "§6§l自带32并行");
        provider.add("ctnh.multiblock.advanced_coke_oven.tooltip.2", "只可运行焦炉配方,且运行配方时间固定为15s");
        provider.add("ctnh.multiblock.advanced_coke_oven.tooltip.3", "产生大量的焦化产物与杂酚油");
        provider.add("ctnh.multiblock.advanced_coke_oven.tooltip.4", "§c§l不能使用焦炉仓");

        provider.add("ctnh.multiblock.large_gas_collection_chamber.tooltip.0", "全维度集气");
        provider.add("ctnh.multiblock.large_gas_collection_chamber.tooltip.1", "这台机器可以收集任意维度的气体");
        provider.add("ctnh.multiblock.large_gas_collection_chamber.tooltip.2", "由于它的产量较大，建议你用ME输出总成来收集产物");

        provider.add("ctnh.multiblock.underfloor_heating_system.tooltip.0", "用蒸汽温暖你的心");
        provider.add("ctnh.multiblock.underfloor_heating_system.tooltip.1", "地暖系统依靠蒸汽供暖，占地一个区块，能对§a周围5*5的区块§r产生供暖，供暖只会在地暖上方十格内生效");
        provider.add("ctnh.multiblock.underfloor_heating_system.tooltip.2", "铜砖瓦会生锈，生锈后地暖系统的供暖能力会减弱");
        provider.add("ctnh.multiblock.underfloor_heating_system.tooltip.3", "可以调节速率，以降低供暖功率并减少蒸汽消耗，最低降至25%");

        provider.add("ctnh.multiblock.mana_generator_turbine_tier1.tooltip.0", "简易魔力转换器");
        provider.add("ctnh.multiblock.mana_generator_turbine_tier1.tooltip.1", "转子支架等级不能超过§bMV§r");

        provider.add("ctnh.multiblock.mana_generator_turbine_tier2.tooltip.0", "进阶魔力转换器");
        provider.add("ctnh.multiblock.mana_generator_turbine_tier2.tooltip.1", "转子支架等级不能超过§5EV§r");
        provider.add("ctnh.multiblock.mana_generator_turbine_tier2.tooltip.2","运行时消耗2.25倍燃料，获得4倍的发电量");

        provider.add("ctnh.multiblock.mana_generator_turbine_tier3.tooltip.0", "精密魔力转换器");
        provider.add("ctnh.mutliblock.mana_generator_turbine_tier3.tooltip.1", "转子支架等级不能超过§dLuV§r");
        provider.add("ctnh.mutliblock.mana_generator_turbine_tier3.tooltip.2","运行时消耗3倍燃料，获得16倍的发电量");

        provider.add("ctnh.multiblock.mana_generator_turbine_tier4.tooltip.0", "神奇的能量守恒");
        provider.add("ctnh.multiblock.mana_generator_turbine_tier4.tooltip.1", "转子支架等级不能超过§cZPM§r");
        provider.add("ctnh.multiblock.mana_generator_turbine_tier4.tooltip.2","运行时消耗4倍燃料，获得24倍的发电量");
        provider.add("ctnh.multiblock.mana_generator_turbine_tier4.tooltip.3","只能使用激光仓");

        provider.add("ctnh.multiblock.zenith_laser.tooltip.0","允许使用§5反相蚀刻§r，消耗§5天顶源质§r来将芯片制成晶圆");

        provider.add("ctnh.multiblock.zenith_circuit_assember.tooltip.0","允许使用§5魔力共振电路组装§r，以更低电压和特殊材料组装共振电路");

        provider.add("ctnh.multiblock.super_ebf.tooltip.0", "所有配方耗时减半");

        provider.add("ctnh.multiblock.mega_lcr.tooltip.0","机器运行时每有一实际并行，能源消耗减少2%（至多75%）。运行时间减少2%(至多75%)");
        provider.add("ctnh.multiblock.mega_lcr.tooltip.1","线圈温度大于3600K时，每额外的1800K温度额外提供25%的速度加成");

        provider.add("ctnh.multiblock.slaughter_house.tooltip.0", "无情的杀戮机器");
        provider.add("ctnh.multiblock.slaughter_house.tooltip.1", "输入总线放入电动刷怪笼后，机器会自动输出对应怪物的战利品，可放入多个电动刷怪笼");
        provider.add("ctnh.multiblock.slaughter_house.tooltip.2", "电压每升高1级，虚拟刷怪量会增加4（HV为4）");
        provider.add("ctnh.multiblock.slaughter_house.tooltip.3", "怪物血量和护甲值越高，配方运行所需时间越长");
        provider.add("ctnh.multiblock.slaughter_house.tooltip.4", "武器的伤害和附魔会减少配方运行的时间");
        provider.add("ctnh.multiblock.slaughter_house.tooltip.5", "时运等附魔也能生效");

        provider.add("ctnh.multiblock.industrial_primitive_blast_furnace.tooltip.0", "更强大的土高炉，你的炼钢好帮手");
        provider.add("ctnh.multiblock.industrial_primitive_blast_furnace.tooltip.1", "工业土高炉在持续运行配方时，会不断升温，而一旦中止，则会迅速冷却");
        provider.add("ctnh.multiblock.industrial_primitive_blast_furnace.tooltip.2", "温度越高，工业土高炉的并行数越高，最高为8并行");
        provider.add("ctnh.multiblock.industrial_primitive_blast_furnace.tooltip.3", "温度越高，工业土高炉的效率越高，最高为两倍效率");

        provider.add("ctnh.multiblock.sintering_kiln.tooltip.0", "需要通入8192应力使其内部活塞压实待加工料");

        provider.add("ctnh.multiblock.decay_pools.tooltip.0", "衰变");
        provider.add("ctnh.multiblock.decay_pools.tooltip.1", "当电路板为0时为不通电状态---不启用世界加速");
        provider.add("ctnh.multiblock.decay_pools.tooltip.2", "当电路板为1时为通电状态---启用世界加速");
        provider.add("ctnh.multiblock.decay_pools.tooltip.3", "加速衰变过程");

        provider.add("ctnh.multiblock.vacuum_sintering_tower.tooltip.0", "真空烧结");

        provider.add("ctnh.multiblock.crystallizer.tooltip.0", "专业结晶");
        provider.add("ctnh.multiblock.crystallizer.tooltip.1", "结晶器能更加快速的完成晶体配方");
        provider.add("ctnh.multiblock.crystallizer.tooltip.2", "随着线圈等级上升，工作效率逐级提升");
        provider.add("ctnh.multiblock.crystallizer.tooltip.3", "可以运行部分化学气相沉积的配方和部分高压釜的配方");
        provider.add("ctnh.multiblock.crystallizer.tooltip.4", "省材料的最佳帮手");

        provider.add("ctnh.multiblock.desalting_factory.tooltip.0", "从海水中烘干出盐，很环保不是吗？");

        provider.add("ctnh.multiblock.water_power_station.tooltip.0", "环保能源！");
        provider.add("ctnh.multiblock.water_power_station.tooltip.1", "发电量和以控制器为中心，机器长度为半径，高为4的范围内的水量成正比");
        provider.add("ctnh.multiblock.water_power_station.tooltip.2", "发电量随机在0.6至1的倍率间波动");

        provider.add("ctnh.multiblock.bio_reactor.tooltip.0", "一个大罐子");

        provider.add("ctnh.computer.a1","§c一切伟大之作都需要§4牺牲§r§j来铸就。其他生物或许不能理解，但他们必将§4服从§r。");
        provider.add("ctnh.computer.a2","机器类型:§c突触凝练机");
        provider.add("ctnh.computer.a3","将其他智慧生物作为§4湿件§r来进行运算，获得大量算力，甚至直接做成湿件");
        provider.add("ctnh.computer.a4","机制介绍占位符");
        provider.add("ctnh.computer.a5","该机器会超载所有智慧生物体的大脑。§4不可避免§r地§4永久损坏§r智慧生物的大脑，§4不会留下§r任何掉落物");
        provider.add("ctnh.computer.a6","诸如村民这种§7低智慧§r的新人类的生命与智慧太低了，我们需要§c更加聪明，可爱和生命更高的生物§r");
        provider.add("ctnh.computer.a7","为了无尽的知识，我们必须§4做出一切必要的牺牲§4");

        provider.add("ctnh.multiblock.martial_morality_eye.tooltip.0", "丐版鸿蒙之眼");
        provider.add("ctnh.multiblock.martial_morality_eye.tooltip.1", "原始时代时消耗64000mb的蒸汽和64个原石");
        provider.add("ctnh.multiblock.martial_morality_eye.tooltip.2", "产出主世界和暮色森林以及月球的矿");
        provider.add("ctnh.multiblock.martial_morality_eye.tooltip.3", "随着电压等级提高能够解锁更多配方");
        provider.add("ctnh.multiblock.martial_morality_eye.tooltip.4", "在前期比坠星好用");
        provider.add("ctnh.multiblock.martial_morality_eye.tooltip.5", "结构中心似乎存在着神秘力量，充满危险的气息，请远离！");
        provider.add("ctnh.multiblock.martial_morality_eye.tooltip.6", "结构来源:Twist Space Technology");

        provider.add("ctnh.multiblock.industrial_altar.tooltip.0","§4血魔法，就在你家门口！");
        provider.add("ctnh.multiblock.industrial_altar.tooltip.1","与血祭坛相同，该结构有输入LP上限，同时你§4必须通过特定配方来增加其lp§r\n详见JEI以查询增加的配方");
        provider.add("ctnh.multiblock.industrial_altar.tooltip.2","电压每超过HV一级，就增加10000可存储LP上限，达到LUV后每级额外增加30000");
        provider.add("ctnh.multiblock.industrial_altar.tooltip.3","每一个增容符文增加2500LP上限，强化增容符文增加5000,达到LUV后每级额外增加1000000/2000000LP上限");

        provider.add("ctnh.multiblock.quasar_eye.tooltip.0","§9魔力§r的§c终极奥秘§r，足以制造§5类星体§r的装置掌握在§6你§r的手中");
        provider.add("ctnh.multiblock.quasar_eye.tooltip.1","该机器启动需要§r初始魔力燃料消耗§R，查阅JEI以查找消耗量");
        provider.add("ctnh.multiblock.quasar_eye.tooltip.2","在能量等级高时启动能量等级低的配方§b不需要启动花费§r");
        provider.add("ctnh.multiblock.quasar_eye.tooltip.3","§5符文能量§r控制着输出的强度，输入§b五级符文§r来增强符文能量，以加强你的输出,使用§5类星体符文§r产生大量符文能量");
        provider.add("ctnh.multiblock.quasar_eye.tooltip.4","该机器获取符文能量逻辑为：在§5每次配方运行前§r读取并消耗每类可消耗符文§c最多各一个§r");
        provider.add("ctnh.multiblock.quasar_eye.tooltip.5","§c注意§r：符文能量越高，其消耗速度就§c越快§r，且符文能量低于50时§c效率将会减半！§r");
        provider.add("ctnh.multiblock.quasar_eye.tooltip.6","该机器能量效率为log((符文能量)/50)+1，最大能量效率为(1+能量等级)");
        provider.add("ctnh.multiblock.quasar_eye.tooltip.7","该机器拥有时间并行，消耗量和持续时间均会乘上并行数，且并行数为效率*5");
        provider.add("ctnh.multiblock.quasar_eye.tooltip.8","该机器燃料消耗量为1-0.05*Math.max((rune_energy-50)/50,0.75)");
        provider.add("ctnh.multiblock.quasar_eye.tooltip.9","在普通模式下发电时积将发电量的1%积累入类星体之眼之中，你每有25符文能量，就可以额外积累1%");
        provider.add("ctnh.multiblock.quasar_eye.tooltip.10","在创生模式下释放所有积累的电量，使用高级燃料可以使输出获得倍乘。同时每积累1000E EU就额外产出一份气体产出,积累电量小于1E时无法启动创生模式");
        provider.add("ctnh.multiblock.quasar_eye.tooltip.11","§b好消息§r：这个机器不会爆炸，§c但我不保证未来它不会爆炸！§r");

        provider.add("ctnh.multiblock.large_miner_zpm.tooltip.0", "听说你很担心矿物的来源？");

        provider.add("ctnh.multiblock.eternal_well_of_suffer.tooltip.0", "§8撒旦一觉醒来发现自己掉到榜二了§r");
        provider.add("ctnh.multiblock.eternal_well_of_suffer.tooltip.1", "享受生灵痛苦的嘶吼吧。§r");
        provider.add("ctnh.multiblock.eternal_well_of_suffer.tooltip.2", "配方时间始终固定在1s。提高电压等级会提高产出生命源质的产出，等效于无损超频。§r");
        provider.add("ctnh.multiblock.eternal_well_of_suffer.tooltip.3", "使用残缺的数据模型不会产出任何东西，模型等级越高，产出越多");
        provider.add("ctnh.multiblock.eternal_well_of_suffer.tooltip.4", "§b灵魂模式：§r");
        provider.add("ctnh.multiblock.eternal_well_of_suffer.tooltip.5", "灵魂模式下，机器不生产生命源质，而是为下方的§b工业狱火锻炉§r提供意志。");
        provider.add("ctnh.multiblock.eternal_well_of_suffer.tooltip.6", "两台机器需要共用岩浆池，且控制器必须位于狱火锻炉的正上方。请查阅JEI以获得更多信息。");
        provider.add("ctnh.multiblock.eternal_well_of_suffer.tooltip.7", "产出生命源质量（mB）/100000的意志。");

        provider.add("ctnh.multiblock.hellforge.tooltip.0", "§8机器也会有灵魂吗？§r");
        provider.add("ctnh.multiblock.hellforge.tooltip.1", "运行狱火锻炉的配方，需要满足配方的最小意志条件。§r");
        provider.add("ctnh.multiblock.hellforge.tooltip.2", "如何向机器内填充意志：§r");
        provider.add("ctnh.multiblock.hellforge.tooltip.3", "1.在控制器附近用§b感知之剑§r杀死一只浸泡于§c生命源质§r的怪物。获得基于怪物最大生命值的意志。");
        provider.add("ctnh.multiblock.hellforge.tooltip.4", "§8到控制器的曼哈顿距离小于8即可，不用非得是中间的血杯§r");
        provider.add("ctnh.multiblock.hellforge.tooltip.5", "2.在控制器附近丢出一块魂石。机器会自动吸取其中的意志。");
        provider.add("ctnh.multiblock.hellforge.tooltip.6", "3.使用§4永恒苦难之井§r。请查阅对应机器的tooltip§r");

        provider.add("ctnh.multiblock.twisted_fusion_mk1.tooltip.0", "§8以不可思议的伟力。§r");
        provider.add("ctnh.multiblock.twisted_fusion_mk1.tooltip.1", "§e核聚变反应堆模式：§r");
        provider.add("ctnh.multiblock.twisted_fusion_mk1.tooltip.2", "不需要启动电量，不限制仓室等级，进行4/2超频。提供取决于配方启动电量的并行：");
        provider.add("ctnh.multiblock.twisted_fusion_mk1.tooltip.3", "小于160MEU：16+16*反应堆等级并行");
        provider.add("ctnh.multiblock.twisted_fusion_mk1.tooltip.4", "大于160MEU，小于320MEU：4+4*聚变反应堆等级并行");
        provider.add("ctnh.multiblock.twisted_fusion_mk1.tooltip.5", "大于320MEU，小于480MEU：1+聚变反应堆等级并行");
        provider.add("ctnh.multiblock.twisted_fusion_mk1.tooltip.6", "§5扭曲聚变反应堆模式：§r");
        provider.add("ctnh.multiblock.twisted_fusion_mk1.tooltip.7", "遵循字母守恒定律的反应。");
        provider.add("ctnh.multiblock.twisted_fusion_mk1.tooltip.8", "或许可以用来生产一些§9奇怪的东西§r...");

        provider.add("ctnh.multiblock.astronomical.tooltip.0", "知天易，逆天难");
        provider.add("ctnh.multiblock.astronomical.tooltip.1", "无法在阳光直射下工作，工作时会自动为芯片总线中的芯片收集数据");

        provider.add("ctnh.multiblock.sinope_chemical.tooltip.0","来自§b某个神秘东方大国§r的工业力量");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.1","格雷员工不骗格雷员工，并行是真实的");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.2","没有外壳等级要求，配方不需要催化剂");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.3","并行数与中心的方块有关");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.4","硅岩块:8并行");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.5","富集硅岩块:32并行");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.6","超能硅岩块:128并行");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.7","每一点实际的并行数减少0.5%的能耗和运行时间，至多减少25%(独立乘区)");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.8","线圈每提供1800K，运行速度+100%");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.9","§c任何虚假的并行都将绳之以法!§r");

        provider.add("ctnh.multiblock.nano_generator.tooltip.0","利用摩擦热的力量");
        provider.add("ctnh.multiblock.nano_generator.tooltip.1","最大并行数:1024");
        provider.add("ctnh.multiblock.nano_generator.tooltip.2","每有1并行数，总体发电量提升0.5%\n实际运行时间为配方时间*sqrt(并行数)");
        provider.add("ctnh.multiblock.nano_generator.tooltip.3","在机器内塞入特定材料可提升倍率，但也有概率消耗\n无材料：0.8倍率\n橡胶片：1.0倍率,并行数/512几率消耗\n聚乙烯片：1.6倍率，并行数/1024几率消耗\n硅橡胶片：2.4倍率，并行数/4096几率消耗\n丁苯橡胶片：3.2倍率，并行数/65535几率消耗\n聚苯并咪唑片：8倍率，并行数/1048576几率消耗");

        provider.add("ctnh.multiblock.photovoltaic_power_station_energetic.tooltip.0", "简易太阳能发电");
        provider.add("ctnh.multiblock.photovoltaic_power_station_energetic.tooltip.1", "§e基础产能功率：§r512 EU/t");
        provider.add("ctnh.multiblock.photovoltaic_power_station_energetic.tooltip.2", "只在白天工作，不同维度会对太阳能发电的效率产生影响，基础产能功率为在主世界正午的功率");

        provider.add("ctnh.multiblock.photovoltaic_power_station_pulsating.tooltip.0", "高效太阳能发电");
        provider.add("ctnh.multiblock.photovoltaic_power_station_pulsating.tooltip.1", "§e基础产能功率：§r2048 EU/t");
        provider.add("ctnh.multiblock.photovoltaic_power_station_pulsating.tooltip.2", "只在白天工作，不同维度会对太阳能发电的效率产生影响，基础产能功率为在主世界正午的功率");

        provider.add("ctnh.multiblock.photovoltaic_power_station_vibrant.tooltip.0", "究极太阳能发电");
        provider.add("ctnh.multiblock.photovoltaic_power_station_vibrant.tooltip.1", "§e基础产能功率：§r8192 EU/t");
        provider.add("ctnh.multiblock.photovoltaic_power_station_vibrant.tooltip.2", "只在白天工作，不同维度会对太阳能发电的效率产生影响，基础产能功率为在主世界正午的功率");

        provider.add("ctnh.multiblock.ion_exchanger.tooltip.0", "离子交换");

        provider.add("ctnh.multiblock.coke_tower.tooltip.0", "拥有强大的焦化产能来支撑你的木化产线！");
        provider.add("ctnh.multiblock.coke_tower.tooltip.1", "有着如同工业熔炉一般的速度");

        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.0","§9魔枢巨星，重构万物尺度");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.1","允许使用并行控制仓，§c其不会为配方提供并行§r，只修改每秒输入魔力量");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.2","插入机器的几种§9五级符文§r决定了该机器的各种能力");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.3","§9星空符文§r的能量降低了能源消耗并增强了机器稳定性");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.4","§c扭曲符文§r的能量降低了所用时间并增大了机器魔力注入频率，§c但会让机器更加不稳定");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.5","§d视域符文§r的能量极大增大了魔力上限和魔力使用效率");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.6","§5类星体符文§r的能量太过强大，它会直接让机器进入§c不稳定状态，但是同样使配方的消耗，产出，电压需求翻10倍");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.7","§c扭曲§r与§9星空§r的对抗决定了机器的稳定性");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.8","稳定性公式:-((twist_power /3)+((mana/100000)*(Math.max(twist_power/9,1))))+starlight_power*4+5+tier,当稳定性低于0时机器会开始过载！");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.9","§c过载度§r每秒提升1且在机器拥有过载度时下§c配方时间会翻1倍§r");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.10","在不处于过载状态下每3秒减少1过载度，§c过载度积累满时机器将会爆炸§r");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.11","§c扭曲符文§r消耗概率公式:每次运行有Math.max((twist_power-3)/3,1)*0.01+(Math.max(starlight_power-twist_power,0)*0.01)+(Math.max((100-mana/100000)*0.0005,0))概率消耗");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.12","§9星空符文§r消耗概率公式:每次运行有Math.max((starlight_power-3)/3,1)*0.01+(Math.max(twist_power-starlight_power,0)*0.01)+(mana/100000*0.005)概率消耗");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.13","§d视域符文§r消耗概率公式：每次运行有0.0025*(horizen_power)概率消耗");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.14","该机器无法超频");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.15","运行时每运行1秒，固定消耗100*并行Kmb(B)液态魔力来为光束注能，可以使用只消耗冷却液的非需求魔力配方来为机器注能");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.16","注意：如果你在输入魔力时超过了魔力上限，则超过上限的魔力不会被返还。如果你的魔力量超过了上限，则运行时不会减少多出于魔力上限的魔力");

        provider.add("ctnh.multiblock.twisted_fusion_mk_infinity.tooltip.0","§8无穷无尽的扭曲之力§r");
        provider.add("ctnh.multiblock.twisted_fusion_mk_infinity.tooltip.1","可以使用激光仓");
        provider.add("ctnh.multiblock.twisted_fusion_mk_infinity.tooltip.2","对所有配方都有§8无法理喻§r的并行数,所有配方能耗和运行时间减少75%");
        provider.add("ctnh.multiblock.twisted_fusion_mk_infinity.tooltip.3","§5想制作这台机器的你疯的不轻，当然这台机器也同样疯狂至极§r");

        provider.add("ctnh.multiblock.wide_accelerator.tooltip.0","粒子加速集成者");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.1","允许§9使用激光仓§r和§a变电仓§r，无法超频");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.2","通过三个轨道加速三种粒子");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.3","本机器只要求粒子速度大于配方需求，粒子速度不得大于50Gev");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.4","允许使用§b并行控制仓§r，使用§b并行控制仓§r可以自由控制配方并行和粒子加速减速并行，否则使用默认值");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.5","在运行一般配方时如无§b并行控制仓§r,默认使用16并行");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.6","如果粒子速度过慢，则什么都不会产生");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.7","本机器在运行时根据运行配方模式来决定之后逻辑");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.8","加速模式:使用加速配方时如无§b并行控制仓§r将试图以1024并行来运行，运行配方时需要大量能源来维持。运行配方时三种粒子速度之和每有100Mev，能源消耗提升10%，此状态下粒子速度§9不会§r减少");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.9","减速模式:使用加速配方时如无§b并行控制仓§r将试图以1并行来减少粒子速度，运行配方时需要少量能源来维持。运行配方时三种粒子速度之和每有100Mev，能源消耗提升2.5%。此状态下粒子速度在运行完配方后减少sqrt(配方所需速度)的对应粒子速度");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.10","注意:本机器用电量极高，且暂时无法做到只能计算正确并行，使用低电压可能导致§c配方无法运行§r或者§c跳电§r,建议搭配§9激光仓§r使用，如遇配方不工作，请降低该机器并行");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.11","可以与约束器链接传递部分粒子。§c警告：如果没有链接约束器，不要随意尝试某些危险的配方§r (目前还是饼)");

        provider.add("ctnh.multiblock.mana_reactor.tooltip.0","工业魔力奠基者");
        provider.add("ctnh.multiblock.mana_reactor.tooltip.1","允许使用并行控制仓");

        provider.add("ctnh.multiblock.greenhouse.tooltip.0", "室内种植");

        provider.add("ctnh.multiblock.meteor_capturer.tooltip.0", "§8为什么陨石总能落在陨石坑里？§r\n该机器无法超频");
        provider.add("ctnh.multiblock.meteor_capturer.tooltip.1", "消耗少量引物和大量的生命源质，从外太空拉取满是矿石的陨石。");
        provider.add("ctnh.multiblock.meteor_capturer.tooltip.2", "配方需要大量的输入输出空间，建议使用高级输入总成。");
        provider.add("ctnh.multiblock.meteor_capturer.tooltip.3", "陨石会在多方块结构上方的空腔内生成（真的）。不要在里面放置人或设备。");
        provider.add("ctnh.multiblock.meteor_capturer.tooltip.4", "半径大于13的配方会破坏多方块结构（不存在这种配方）。");


        provider.add("ctnh.recipe.arc_generator.require","需求电弧强度:%d");
        provider.add("ctnh.recipe.arc_generator.max_require","满功率需求电弧强度:%d");
        provider.add("ctnh.mutiblock.arcreactor.arc","可输出的电弧强度:%d");
        provider.add("ctnh.mutiblock.arcreactor.connect","§b桥接已启用§r");

        provider.add("ctnh.arcgenerator.1","物质撕裂器");
        provider.add("ctnh.arcgenerator.t2.1","分子撕裂器");
        provider.add("ctnh.arcgenerator.t3.1","原子撕裂器");
        provider.add("ctnh.magic_fuel_generator","魔导精炼厂");


        provider.add("ctnh.arcgenerator.arc.t1.1","§b最大支持电弧强度:1000");
        provider.add("ctnh.arcgenerator.arc.t1.2","§c最大发电效率:75%");
        provider.add("ctnh.arcgenerator.arc.t2.1","§b最大支持电弧强度:10000");
        provider.add("ctnh.arcgenerator.arc.t2.2","§c最大发电效率:125%");
        provider.add("ctnh.arcgenerator.arc.t3.1","§b最大支持电弧强度:50000");
        provider.add("ctnh.arcgenerator.arc.t3.2","§c最大发电效率:225%");

        provider.add("ctnh.arcgenerator.2","该机器必须配合电弧生成器使用，要求电弧生成器必须在该机器主方块上方5格，当完成链接时，电弧生成器会显示已完成连接");
        provider.add("ctnh.arcgenerator.3","当电弧强度小于配方最小电弧强度时，配方将无法运行");
        provider.add("ctnh.arcgenerator.4","当电弧强度大于配方最大电弧强度时，配方将以(机器电弧强度-配方需求电弧强度)/(满功率需求电弧强度-需求电弧强度)的效率运行。效率允许超过100%，但不能超过机器最大发电效率");
        provider.add("ctnh.arcgenerator.5","当效率未达100%时，因为湮灭的不完全，将产生少量额外产出");
        provider.add("ctnh.arcreactor","电弧发生者");
        provider.add("ctnh.arcreactor.1","该机器必须配合电弧撕裂者使用，要求电弧生成器必须在电弧撕裂者主方块上方5格，当完成链接时，电弧生成器会显示桥接已启用");
        provider.add("ctnh.arcreactor.2","机器基础每次运行配方产生10电弧强度，无法超频，高等级机器具有更高并行数");
        provider.add("ctnh.arcreactor.t1","该机器并行数:1");

        provider.add("ctnh.advanceassemblyline.1", "更好的装配线,但是有序输入");
        provider.add("ctnh.magic.generator","精炼天地之魔精");
        provider.add("ctnh.magic.generator.1","具有8并行，每秒基础消耗12mB液态魔力，电压每超过§7LV§r一级，消耗量变为原来的两倍");
        provider.add("ctnh.gcym.reduction","配方耗时x0.8，配方耗能x0.6");
        provider.add("ctnh.boss_summoner.use", "右键长按蓄力掷出，在落点处召唤一只神化boss，每次使用有五分之一的概率消耗");
        provider.add("ctnh.mechanical_lathe.structure", "结构须知：车床必须放满6个，且动力侧朝外，移动侧朝中心");

        provider.add("ctnh.gtceu.tooltip.tfmkalephzero.1","§4不，这不可能，你到底是怎么做出来这个东西的？§r");
        provider.add("ctnh.gtceu.tooltip.tfmkalephzero.2","§c你到底是无聊到什么地步能凑到这么多材料和算力，只为了这点并行和速度？§r");
        provider.add("ctnh.gtceu.tooltip.tfmkalephzero.3","§8好吧，我不管你是开创还是怎么回事的，你确实和你的AE征服我了§r");
        provider.add("ctnh.gtceu.tooltip.tfmkalephzero.4","§7并行数为2147483637，配方时间乘以0.0001，无损超频，满意了吧？§r");

        provider.add("ctnh.eternalgarden.tooltip.1","§b万物在这世间绽放的永恒的一瞥§r");
        provider.add("ctnh.eternalgarden.tooltip.2","无法超频，但是机器自身电压每比配方电压高一级就使最终产出乘以1.25,通过在输入总线内加入五级符文来增大这个系数");
        provider.add("ctnh.eternalgarden.tooltip.3","每朵花都有自己独特的机制，机制太复杂了！请参阅§5魔力飞升§r章节来获取各种花的机制");
        provider.add("ctnh.eternalgarden.tooltip.unknown","§5......等待着永恒的紫罗兰如今在何方？");
        //
        provider.add("ctnh.anti_inf_matter.1","-∞");
        provider.add("ctnh.anti_inf_matter.2","它到底是怎么在现实世界存在的......");
        //
        provider.add("ctnh.plasma_alloy.tooltip.1","§4转底炉的复仇");
        provider.add("ctnh.plasma_alloy.tooltip.11","允许使用§b激光仓§r，使用激光仓时最终速度将除以4，速度低于原速度时拒绝运行");
        provider.add("ctnh.plasma_alloy.tooltip.2","线圈温度每有1800K，获得4点并行，线圈温度超过10000K时，获得(线圈温度-10000)/10000的额外加速");
        provider.add("ctnh.plasma_alloy.tooltip.3","运行前消耗(并行数*对应等离子体消耗)的等离子体，获得额外加速");
        provider.add("ctnh.plasma_alloy.tooltip.4","氦等离子体：消耗500*并行的等离子体，速度+100%");
        provider.add("ctnh.plasma_alloy.tooltip.5","氧，氮等离子体：消耗300*并行的等离子体，速度+200%");
        provider.add("ctnh.plasma_alloy.tooltip.6","镍，铁等离子体：消耗200*并行的等离子体，速度+300%");
        provider.add("ctnh.plasma_alloy.tooltip.7","消耗特殊的冶炼等离子体可以获得额外的速度加成，§c但是同样会将增加你冶炼的风险");
        provider.add("ctnh.plasma_alloy.tooltip.8","压缩精金等离子：消耗固定100等离子体，使速度*5,使消耗电压翻倍（§c这可能导致配方不运行，请使用多安能源仓）");
        provider.add("ctnh.plasma_alloy.tooltip.9","精炼超能以太等离子体：消耗50*并行等离子体，使速度*10,§c使最终产物在80%-100%中浮动");
        provider.add("ctnh.plasma_alloy.tooltip.10","§c速度增幅超过5000%时，最终产物量将会在0%-50%中浮动！");


        provider.add("ctnh.plasma_alloy.tooltip.recipe","配方类型：合金冶炼炉");

        provider.add("ctnh.acc.danger","§c危险粒子实验");



        provider.add("ctnh.hybrid_mixer.tooltip.0","动力学的电力复兴");
        provider.add("ctnh.hybrid_mixer.tooltip.1","执行特殊的电压-应力驱动机制");
        provider.add("ctnh.hybrid_mixer.tooltip.2","机器真实电压等级为配方电压等级和应力等级的较小值。应力输入仓要求转速至少为64，应力输入仓转速为256时，应力等级+1");
        provider.add("ctnh.hybrid_mixer.tooltip.3","混合动力超频：应力等级和配方电压等级每同时提升一级，运行速度*4");
        provider.add("ctnh.hybrid_mixer.tooltip.4","当转速超过64时，使配方时间*0.8。转速超过128时配方时间和电压速度将随着转速提升进一步减少");


        provider.add("ctnh.spacephotovoltaicbasestation.tooltip.0","§6光辉灿烂的太空之路");
        provider.add("ctnh.spacephotovoltaicbasestation.tooltip.1","可执行配方类型：太空光伏发电，太空光伏组装");
        provider.add("ctnh.spacephotovoltaicbasestation.tooltip.2","在太空发电模式下，星球类型和光伏方块的等级都会提升发电量，在空间站被视为无重力环境，且发电量*4,消耗特定材料以进一步提升发电量");
        provider.add("ctnh.spacephotovoltaicbasestation.tooltip.3","在太空光伏组装模式下，不消耗EUt，发电量将锁定为1，根据配方的模拟F功率来计算速度和并行量");
        provider.add("ctnh.spacephotovoltaicbasestation.tooltip.4","光伏等级，光照强度共同决定了是否可以执行太空组装配方，光伏方块耐热性和耐热结构方块决定了可以获得的光照最大倍率，太空结构方块决定了可以使用的光伏方块等级和是否可以使用附属结构");
        provider.add("ctnh.spacephotovoltaicbasestation.tooltip.5","在太空光伏组装模式下，最终并行为(太空发电模式下发电量/模拟功率),最终时间倍率为(模拟功率/太空发电模式下发电量)，当太空发电模式下发电量小于模拟功率时，最终时间倍率将变为平方");
        provider.add("ctnh.spacephotovoltaicbasestation.tooltip.ex","§6该结构将持续拓展，这还不是它的完全体状态！");

        provider.add("ctnh.lasersorter.tooltip.0","持续调整激光频率");
        provider.add("ctnh.lasersorter.tooltip.1","配方类型：激光分配/激光蚀刻");
        provider.add("ctnh.lasersorter.tooltip.2","本机器需要消耗算力才能运行");
        provider.add("ctnh.lasersorter.tooltip.3","————————激光蚀刻模式————————");
        provider.add("ctnh.lasersorter.tooltip.4","LUV及以下的电压固定基础请求8算力，电压每高于LUV一级，请求的基础算力翻倍");
        provider.add("ctnh.lasersorter.tooltip.5","输入的算力如果为基础请求算力的整数倍，则最终输出*1.25，并行等同于⌊(输入的算力/基础请求算力)⌋的三次方");
        provider.add("ctnh.lasersorter.tooltip.6","输入的算力每比基础算力多一倍，将一次超频转化为无损超频（即运行速度*2），该效果转化的次数不超过你能超频的等级（即上限为将你所有的有损超频转化为无损）");
        provider.add("ctnh.lasersorter.tooltip.7","————————激光分配模式————————");
        provider.add("ctnh.lasersorter.tooltip.8","配方给出请求算力，如果配方没有给出则按照激光蚀刻模式的公式计算");
        provider.add("ctnh.lasersorter.tooltip.9","输入的算力如果为基础请求算力的整数倍，则并行等同于⌊(输入的算力/基础请求算力)⌋的三次方");
        provider.add("ctnh.lasersorter.tooltip.10","输入的算力每比基础算力多一倍，将一次超频转化为无损超频（即运行速度*2），该效果转化的次数不超过你能超频的等级（即上限为将你所有的有损超频转化为无损）");
        provider.add("ctnh.lasersorter.tooltip.11","§c如果输入的算力不为整数倍，则以上所有的增益全部无效且最终所需时间*4");

        provider.add("ctnh.drone_tier","无人机等级：%d");
        provider.add("ctnh.drone_eut","单个无人机产生的电压: %dEU/t");
        provider.add("ctnh.drone_durability","无人机最大耐久:%d");
        provider.add("ctnh.dyson_tier1","集成性光伏无人机蜂群");
        provider.add("ctnh.dyson_tier2","§4我们的蜂群遮天蔽日");
        provider.add("ctnh.nuclear_reactor_heat", "基础堆温：%d°C");
        provider.add("ctnh.terminal.success_get","已经获取坐标!");
        provider.add("ctnh.terminal.success_write","已成功写入坐标!");
        provider.add("ctnh.terminal.location","已经绑定的坐标：(%s,%s,%s)");
        provider.add("ctnh.terminal.success_clear","已清除坐标！");
        provider.add("ctnh.terminal.tips","使用右键绑定光伏模块控制器，然后再右键将控制器和光伏基站绑定\nshift+右键任意方块清除坐标");
        provider.add("ctnh.pvdrone.0","戴森云计划");
        provider.add("ctnh.pvdrone.1","允许使用并行控制仓，并行控制仓提供的每个数值为运行时间倍率");
        provider.add("ctnh.pvdrone.2","为光伏基站提供电力增幅，使用光伏绑定终端来为这两个结构绑定");
        provider.add("ctnh.pvdrone.3","将无人机放入无人机支架以开始发送无人机，每5秒和运行结束时，每个无人机都有一定概率消耗，无人机发电同样受维度和空间站增幅");
        provider.add("ctnh.pvdrone.4","无人机的消耗概率公式为1.0 / (1.0 + Math.exp(-0.25* (x - 9)))");
        provider.add("ctnh.pvdrone.t1","提供的能量:%d");
        provider.add("ctnh.pvdrone.t2","无人机消耗概率:%.4f");
        provider.add("multiblock.ctnh.nuclear_reactor.coolant", "冷却液：%s");
        provider.add("multiblock.ctnh.nuclear_reactor.coolant_amount", "冷却液量：%s mB");
        provider.add("multiblock.ctnh.nuclear_reactor.consume_amount", "冷却液消耗率：%s mB/s");
        provider.add("nuclear_reactor", "核能转化时刻");
        provider.add("ctnh.nuclear_reactor.basic", "这是一个耗能设备，但是会产生大量的热量，可以转化用以发电");
        provider.add("ctnh.nuclear_reactor.coolant", "冷却液可以使用蒸汽（150°C），氘（450°C），钠（800°C），钠钾合金（900°C），反应的堆温越高，消耗冷却液的速度越快，冷却液的热容越大，消耗速度越慢");
        provider.add("ctnh.nuclear_reactor.overclock", "冷却液并非运行所必须，但是在有冷却液时，配方每运行一秒，进度会增加两秒");
        provider.add("ctnh.nuclear_reactor.safe", "反应堆不会过热爆炸");

        provider.add("ctnh.cryotheum.0","§b泪水如凛冰般落下");
        provider.add("ctnh.cryotheum.1","每次运行配方消耗5*并行mb极寒之凛冰，电压每高于§9IV§r一级，这个消耗就翻4倍");
        provider.add("ctnh.cryotheum.2","初始具有4并行和3泪之晶点数，可以在升级界面加点。每消耗10000mb凛冰，就获得一点点数，随后将目标翻四倍");
        provider.add("ctnh.compiler.part_states","片区%d状态:%s");
        provider.add("ctnh.compiler.state.idle","§6待机");
        provider.add("ctnh.compiler.state.error","§c错误,类型：%s");
        provider.add("ctnh.compiler.state.working","§9运行中:%ss/%ss");
        provider.add("ctnh.compiler.state.finish","§a完成");
        provider.add("ctnh.compiler.state.waiting","§b等待中......");
        provider.add("ctnh.data.tip1","当前公式: a%s+b%s+c%s+d");
        provider.add("ctnh.compiler.id","该舱室被分配到的片区编号:%s");
        provider.add("ctnh.compiler.noid","§c当前舱室尚未连接到主机！");
        provider.add("ctnh.data.muti","获取的倍率: %s");
        provider.add("ctnh.compiler.0","将生物的进化之道完全放任于碳基生物的自然演变是一种低效且缓慢的做法，现在我们将亲自编码每一个基因序列，将我们的至臻完美编译在神经元的逻辑之中");
        provider.add("ctnh.compiler.01","神经矩阵编码器（CMP）是一台编译神经序列的机器，其不同于其他机器，不执行正常的输入逻辑，无法超频");
        provider.add("ctnh.compiler.1","该机器的输入由6个神经矩阵研究舱室组成，每个舱室在结构完成时将被编码，所有研究舱室必须为同一等级，编码完成后，每个研究舱室将会显示他们所属的片区（并未实现）现在片区分配固定为：机器主方块左前方为1，右前方为2，左后方为3，右后方为4");
        provider.add("ctnh.compiler.2","该机器的输入§c必须严格按照JEI的物品顺序§9从左到右§r从第一行到第二行放置在§91-5片区§r，同时在第六片区放置§9研究数据集§r§r,任何错误的放置或者外部舱室的放置都会导致机器故障并在对应舱室显示故障");
        provider.add("ctnh.compiler.3","必须保证所有神经矩阵研究舱室的等级不低于配方等级，否则配方不会运行");
        provider.add("ctnh.compiler.4","————————机器总体机制————————");
        provider.add("ctnh.compiler.5","每次检测到新的配方时，机器将§6完美诉诸于随机§r，生成函数F(x1,x2,x3)=§6y=ax1+bx2+cx3+d§r，其中，x1,x2,x3为期望的片区所消耗的物品数量，同时在给定范围内随机x1,x2,x3,获取答案y");
        provider.add("ctnh.compiler.6","当配方执行时，在开始逻辑运算，1-5片区将各自运行5s,运行完毕时将消耗舱室内所有物品来取得函数");
        provider.add("ctnh.compiler.part1","片区1-3：用于提供函数F(x)的真实x1,x2,x3");
        provider.add("ctnh.compiler.part2","片区4：代表函数F(x)的常数量d，同时决定噪声ϵ");
        provider.add("ctnh.compiler.part3","片区5：此片区用为神经编译提供电路板支持，决定噪声ϵ波动，如果提供电路板大于配方给定值则不造成噪声影响");
        provider.add("ctnh.compiler.part4","片区6：收集最终编译结果的片区，在完成一次逻辑运算流程后，将根据结果对神经数据集进行修改");
        provider.add("ctnh.compiler.7","在片区1-5执行完毕后，进行持续5s的总计算流程，在此过程中给出x1,x2,x3，计算得到计算值y，与真实比较，进行最终编译运算");
        provider.add("ctnh.compiler.8","————————最终编译运算————————");
        provider.add("ctnh.compiler.9","最终编译运算将比较真实y与结果y，如果结果y的值在真实值y的0.9-1.1倍内，则运算成功，将编译数据集变为配方输出");
        provider.add("ctnh.compiler.10","如果运算失败，则定义噪声结果函数f(x1,x2,x3)=ax1+bx2+cx3+d+ϵ,根据噪声决定片区，噪声波动最多翻倍5倍，片区误差项为0.9-1.1间，则噪声变为0.5倍率");
        provider.add("ctnh.compiler.11","最终编译数据集将获得三个信息，信息1代表本次的方程，信息2代表噪声结果函数获得的结果值对于y的倍率，信息3代表误差项的比率");
        provider.add("ctnh.compiler.12","在执行相同配方时方程不会重置，在执行配方或者结构重新成型时，重置y和方程");

        provider.add("ctnhcore.src.sacrifice_empty","无牺牲者");
        provider.add("ctnhcore.src.sacrifice_locked","已锁定牺牲者！");
        provider.add("ctnhcore.src.sacrifice_unlocked","无法锁定牺牲者");
        provider.add("ctnhcore.src.wetware_duration", "湿件剩余存活时间: %s ticks");
        provider.add("ctnhcore.src.sacrifice", "牺牲者: %s");
        provider.add("ctnh.data.noise","当前噪声值：%s");
        provider.add("super_centrifuge", "超速离心");
        provider.add("ctnh.super_centrifuge.parallel", "普通离心机模式下会获得8并行");
        provider.add("ultrasonic_apparatus", "超声破碎");
        provider.add("ctnhcore.machine.high_performance_computer.tooltip.0", "§3飞龙一般的超频计算");
        provider.add("ctnhcore.machine.high_performance_computer.tooltip.1", "§r能量充足时,提供%d算力");
        provider.add("ctnh.compiler.error.0","§c未知错误");
        provider.add("ctnh.compiler.error.1","§c舱室等级与片区1不匹配");
        provider.add("ctnh.testui.0","当前泪之晶点数:%d");
        provider.add("ctnh.freezeui.1","当前冷冻机加速：%.2f / %.2f");
        provider.add("ctnh.freezeui.2","打开升级面板");
        provider.add("ctnh.freezeui.3","当前能量利用效率：%.2f / %.2f");
        provider.add("ctnh.freezeui.4","当前并行：%d / %d");
        provider.add("ctnh.freezeui.5","§b当前消耗的凛冰:%d / %d");
        provider.add("ctnh.machine.fluid_drilling_rig.description.inf","§6钻取来自无尽之中的流体之海");
        provider.add("ctnh.machine.fluid_drilling_rig.depletion.inf","§6永§b不§d损§a耗，你在担心什么？");

        provider.add("ctnh.multiblock.hyper_plasma_turbine.tooltip0","§a精密计算§f与§e等离子体§f的§5终极艺术");
        provider.add("ctnh.multiblock.hyper_plasma_turbine.tooltip1","提供%d算力以达到基础功率，每提供%d算力，输出功率翻一倍");
        provider.add("ctnhcore.recipe_logic.insufficient_cwut","算力不足");
        provider.add("zenith_machine_sp","§5灵能灯塔屹立不倒！");
        provider.add("zenith_extruder","配方类型：压膜机/§5天顶灵压塑形");
        provider.add("zenith_extruder.1","允许使用§5天顶灵压塑形§r，其以每个形态1mb§5天顶源质§5r的代价来一次性塑造大部分锭的各种形态");
        provider.add("zenith_extruder.2","允许塑形的形态包括：§7板，杆，小型齿轮，齿轮，转子，环，螺栓，§4不允许塑形南瓜派！");


        provider.add("ctnh.multiblock.wide_accelerator.info.power","存储的电量：%.2f E/%.2f E");
        provider.add("ctnh.eternal_engine.1","当前发电量:%d EU /tick");
        provider.add("ctnh.eternal_engine.2","累计的工作时间:%.2f s/36000 s");

        provider.add("ctnh.connect","连接已搭建");
        provider.add("ctnh.anti_nu","反中子量:%d");
        provider.add("ctnh.anti_proton","反质子量:%d");
        provider.add("ctnh.anti_electric","反电子量:%d");
        provider.add("ctnh.trap_electric","当前存储电量:%deu");
        provider.add("ctnh.trap_electric_max","允许存储电量上限:%deu");
        provider.add("ctnh.restore_danger","约束危险物质");
        provider.add("ctnh.no_energy_waring","§c警告：供电不足，约束场即将失效！");

        provider.add("ctnh.multiblock.wind_array.tooltip0", "§7§o风力狼群:真正的自然之力");
        provider.add("ctnh.multiblock.wind_array.tooltip1", "§8---------------§a基础数据§8-----------------");
        provider.add("ctnh.multiblock.wind_array.tooltip2", "§f- 基础发电功率: §e%d EU/t  §7(地球)");
        provider.add("ctnh.multiblock.wind_array.tooltip3", "§f- 天气风力增益: §e雨天x2,雷雨x4");
        provider.add("ctnh.multiblock.wind_array.tooltip4", "§f- 高度增益: §e Clamp(Y-64, 0, 256-64) / (256-64)");
        provider.add("ctnh.multiblock.wind_array.tooltip5", "§f- 网络增益: §e 0.3*[log2(网络大小)]");
        provider.add("ctnh.multiblock.wind_array.tooltip6", "§f增益乘算得到发电效率");
        provider.add("ctnh.multiblock.wind_array.tooltip7", "§f需要§e%d mB/s§f 润滑油以维护机器运行");
        provider.add("ctnh.multiblock.wind_array.tooltip8", "§8---------------§a风力网络§8-----------------");
        provider.add("ctnh.multiblock.wind_array.tooltip9", "§f所有结构对齐且间距<=1的风力发电机阵列会组成风力网络");
        provider.add("ctnh.multiblock.wind_array.tooltip10", "§f润滑油会从风力网络中抽取.");
        provider.add("ctnh.multiblock.wind_array.tooltip11", "§5顺应风力网络的工作规律,以抵挡自然之力的摧残");

        provider.add("ctnh.multiblock.mana_condenser.tooltips.0", "反熵物质转化！");
        provider.add("ctnh.multiblock.mana_condenser.tooltips.1", "可以将魔力转化为液态魔力，或者将液态魔力转化为魔力，后者所需的能量更多");
        provider.add("ctnh.multiblock.mana_condenser.tooltips.2", "所有魔力输入输出均通过结构中心的魔力池进行");

        provider.add("ritual.ctnh.chargerRitual", "充能仪式");
        provider.add("ctnh.terminal.mutiblockhelper.tips","第一次右键选择第一个方块，第二次右键选择第二个方块，使用shift+右键启用多方块构建\nshift+右键后将清除原坐标\n选择的方块请按照：底部西北角出发，前往顶部东南角来选择不然无法输出完整结构");

        provider.add("ctnh.u_sinope.story.1","在战争没有开始前，人们曾团结在一起，一齐建造这工业的巴别巨塔");
        provider.add("ctnh.u_sinope.story.2","直到那场永恒的战争，这座真空巨塔化为永恒的残骸，随着战争的双方破碎在真空中");
        provider.add("ctnh.u_sinope.story.3","你已无法再知晓那场战争的双方是否已经相互毁灭，但你直到，这座巨型结构将宣告着人类的复兴");
        provider.add("ctnh.u_sinope.1","配方类型：蒸馏塔/蒸馏室/裂化机/流体加热机/流体固化机/真空石化处理/???");
        provider.add("ctnh.u_sinope.2","§c它那究极的结构已然无法让你的JEI承受，你需要寻求蓝图的帮忙，同时在修改结构时最好直接破坏主方块以避免检测卡死游戏");
        provider.add("ctnh.u_sinope.3","巨型的结构只能在真空建立，否则巨大的结构将会使周围坍缩（效率减少99.99%）");
        provider.add("ctnh.u_sinope.4","除非你使用四维工程学材料，否则它无法再承受UIV即以上的线圈，效率将减少99.99%");
        provider.add("ctnh.u_sinope.5","线圈等级决定了最大的配方等级，你最大只能使用线圈电压等级+1的配方等级，否则效率减少99%");
        provider.add("ctnh.u_sinope.6","允许使用激光仓，但你的配方电压等级必须达到OPV，否则效率将减少99%");
        provider.add("ctnh.u_sinope.7","对于常规配方，该巨构拥有8^（电压等级）的并行，最高不超过2^32，在能源仓等级达到OPV时解锁无损超频，配方等级每超过UHV一级，处理速度+555%,每100点并行使处理速度增加333%,如果使用了四维工程学材料，则速度额外增加5000%");
        provider.add("ctnh.u_sinope.8","对于该巨构特有的配方类型具有特殊机制：时间固定为100秒，并行固定为10，电压每超过UHV一级，则时间减少10秒，并行增加10,如果使用了四维工程学材料且线圈等级大于等于UIV，则时间固定为1秒");

        for (var tier : GTMachineUtils.ALL_TIERS) {
            provider.add(CTNHMachines.CIRCUIT_BUS[tier].getBlock(), GTValues.VNF[tier] + "§r芯片总线");
        }
        for (var tier : GTMachineUtils.ELECTRIC_TIERS) {
            provider.add(CTNHMachines.PERSONAL_COMPUTER[tier].getBlock(), GTValues.VNF[tier] + "§r个人计算机");
        }
        for (int tier = GTValues.UHV; tier <= GTValues.MAX; tier++){
            provider.add(CTNHMachines.PARALLEL_HATCH[tier].getBlock(),GTValues.VNF[tier] + "§r并行控制仓");
        }
        for (int tier : GTValues.tiersBetween(LV, HV)){
            provider.add(CTNHMachines.ENERGY_OUTPUT_HATCH_4A_LOWER[tier].getBlock(), "4安" + GTValues.VNF[tier] + "§r动力仓");
        }
        for (int tier : GTValues.tiersBetween(ULV, MV)){
            provider.add(CTNHMachines.ROTOR_HOLDER_EXTEND[tier].getBlock(), GTValues.VNF[tier] + "§r转子支架");
        }
        for(int tier:GTValues.tiersBetween(ZPM,MAX))
        {
            provider.add(CTNHMachines.COMPILERMACHINE[tier].getBlock(),GTValues.VNF[tier]+"§r神经拟合仓" );
        }
//        for (var definiton : CTNHMachines.HIGH_PERFORMANCE_COMPUTER){
//            var tier = definiton.getTier();
//            provider.add(CTNHMachines.HIGH_PERFORMANCE_COMPUTER[tier].getBlock(), GTValues.VNF[tier] + "§r高性能计算机");
//        }
        for (int tier : GTValues.tiersBetween(HV, IV)){
            provider.add(CTNHMachines.HIGH_PERFORMANCE_COMPUTER[tier].getBlock(), GTValues.VNF[tier] + "§r高性能计算机");
        }

//        provider.add(CTNHMachines.STERILE_CLEANROOM_MAINTENANCE_HATCH.getBlock(), "无菌超净间维护仓");

        provider.add(CTNHMachines.DIGITAL_WELL_OF_SUFFER[LV].getBlock(), "基础数字化苦难之井");
        provider.add(CTNHMachines.DIGITAL_WELL_OF_SUFFER[MV].getBlock(), "§b进阶数字化苦难之井§r");
        provider.add(CTNHMachines.DIGITAL_WELL_OF_SUFFER[HV].getBlock(), "§6进阶数字化苦难之井 II§r");
        provider.add(CTNHMachines.DIGITAL_WELL_OF_SUFFER[EV].getBlock(), "§5进阶数字化苦难之井 III§r");
        provider.add(CTNHMachines.DIGITAL_WELL_OF_SUFFER[IV].getBlock(), "§9精英数字化苦难之井§r");
        provider.add(CTNHMachines.DIGITAL_WELL_OF_SUFFER[LuV].getBlock(), "§d精英数字化苦难之井 II§r");
        provider.add(CTNHMachines.DIGITAL_WELL_OF_SUFFER[ZPM].getBlock(), "§c数字化猩红深渊§r");
        provider.add(CTNHMachines.DIGITAL_WELL_OF_SUFFER[UV].getBlock(), "§3数字化猩红深渊 II§r");
        provider.add(CTNHMachines.DRONEHOLDER.getBlock(),"无人机支架");

        provider.add(CTNHCreativeModeTabs.MACHINE.get(), "CTNH机器");
        provider.add(CTNHCreativeModeTabs.ITEM.get(), "CTNH物品");
        provider.add(CTNHCreativeModeTabs.BLOCK.get(), "CTNH方块");

        provider.addEnchantment(CTNHEnchantments.VACUUM_SEAL, "真空密封");
        provider.addEnchantment(CTNHEnchantments.WARMING, "御寒");
        provider.addEnchantment(CTNHEnchantments.COOLING, "御暑");
        provider.add("enchantment.kubejs.vacuum_seal.desc", "使你不再受到真空的伤害。注：必须所有装备均拥有该附魔");
        provider.add("enchantment.kubejs.warming.desc", "增强御暑能力");
        provider.add("enchantment.kubejs.cooling.desc", "增强御寒能力");

        provider.addItem(CTNHItems.GREAT_ASTRONOMY_CIRCUIT_1, "完善的一阶航天数据芯片");
        provider.addItem(CTNHItems.ASTRONOMY_CIRCUIT_1, "一阶航天数据芯片");
        provider.addItem(CTNHItems.PROGRAM_EMPTY, "空白程序");
        provider.addItem(CTNHItems.PROGRAM_ROCKET_CORE_1, "一阶火箭核心代码");
        provider.addItem(CTNHItems.PROGRAM_ROCKET_1, "一阶火箭控制代码");
        provider.addItem(CTNHItems.TESTING_TERMINAL, "检测终端");
        provider.addItem(CTNHItems.ME_ADVANCED_TERMINAL, "GT-MBST-A v7.0.1");
        provider.addItem(CTNHItems.SIMPLE_NUTRITIOUS_MEAL, "简易营养餐");
        provider.addItem(CTNHItems.ECOLOGICAL_STAR, "生态之星");
        provider.addItem(CTNHItems.ANIMAL_EXCRETA, "动物排泄物");
        provider.addItem(CTNHItems.SCULK_CELL, "幽匿干细胞");
        provider.addItem(CTNHItems.TUMOR, "肿瘤");
        provider.addItem(CTNHItems.HORIZEN_RUNE, "视域符文");
        provider.addItem(CTNHItems.REFINED_IRON_INGOT, "精炼铁方坯");
        provider.addItem(CTNHItems.CORROSIVE_CORE, "腐蚀核心");
        provider.addItem(CTNHItems.VENGEFUL_CORE, "复仇核心");
        provider.addItem(CTNHItems.DESTRUCTIVE_CORE, "破坏核心");
        provider.addItem(CTNHItems.STEADFAST_CORE, "坚毅核心");
        provider.add(CTNHItems.TWIST_RUNE.get(), "扭曲符文");
        provider.add(CTNHItems.STARLIGHT_RUNE.get(), "星光符文");
        provider.add(CTNHItems.QUASAR_RUNE.get(), "§5类星体§r符文");
        provider.add(CTNHItems.PROLIFERATION_RUNE.get(), "增殖符文");
        provider.add(CTNHItems.ANTI_INF_MATTER.get(),"§0反无穷聚合体");
        provider.addItem(CTNHItems.BOSS_SUMMONER, "boss召唤器");
        provider.addItem(CTNHItems.ADVANCED_BOSS_SUMMONER, "进阶boss召唤器");
        provider.addItem(CTNHItems.PV_DRONE_PROTOTYPE,"光伏无人机原型");
        provider.addItem(CTNHItems.PV_DRONE_TIER1,"标准化光伏无人机");
        provider.addItem(CTNHItems.PV_DRONE_TIER2,"共振结构化光伏无人机");
        provider.addItem(CTNHItems.MODULAR_DYSON_SWARM_T1,"戴森云无人机蜂群MKI");
        provider.addItem(CTNHItems.MODULAR_DYSON_SWARM_T2,"戴森云无人机蜂群MKII");
        provider.addItem(CTNHItems.PV_TERMINAL,"光伏绑定终端");
        provider.addItem(CTNHItems.RESEARCH_DATASET,"研究数据集");
        provider.addItem(CTNHItems.RESEARCH_DATASET_LIVING_MATERIAL,"研究数据集：活体金属");
        provider.addBlock(CTNHBlocks.CASING_REFLECT_LIGHT, "反光机械方块");
        provider.addBlock(CTNHBlocks.ADVANCE_MACHINE_CASING_ASSEMBLY_CONTROL, "进阶线程控制外壳");
        provider.addBlock(CTNHBlocks.ADVANCE_MACHINE_CASING_ASSEMBLY_LINE, "进阶装配核心");
        provider.addBlock(CTNHBlocks.CASING_OSMIRIDIUM, "铱锇合金机械方块");
        provider.addBlock(CTNHBlocks.CASING_TUNGSTENCU_DIAMOND_PLATING, "W-Cu覆膜金刚石机械方块");
        provider.addBlock(CTNHBlocks.ENERGETIC_PHOTOVOLTAIC_BLOCK, "充能光伏方块");
        provider.addBlock(CTNHBlocks.ZENITH_CASING_BLOCK, "天顶强化机械方块");
        provider.addBlock(CTNHBlocks.ZENITH_EYE, "§5天顶之眼");
        provider.addBlock(CTNHBlocks.ZENITH_CASING_GEARBOX,"天顶强化魔力齿轮箱机械方块");
        provider.addBlock(CTNHBlocks.PULSATING_PHOTOVOLTAIC_BLOCK, "脉冲光伏方块");
        provider.addBlock(CTNHBlocks.VIBRANT_PHOTOVOLTAIC_BLOCK, "振动光伏方块");
        provider.addBlock(CTNHBlocks.PLASMA_COOLED_CORE,"等离子交换热线圈方块");
        provider.addBlock(CTNHBlocks.CASING_NAQUADAH_BLOCK,"铿铀强化硅岩铕机械方块");
        provider.addBlock(CTNHBlocks.CASING_NAQUADAH_ALLOY_BLOCK,"三钛强化中子素硅岩合金机械方块");
        provider.addBlock(CTNHBlocks.CASING_ANTIFREEZE_HEATPROOF_MACHINE,"等离子冷凝机械方块");
        provider.addBlock(CTNHBlocks.NATURAL_ECOLOGICAL_SHELL_CASING, "环保机械外壳");
        provider.addBlock(CTNHBlocks.ANNIHILATE_CORE_MKI,"超级硅岩反应堆核心");
        provider.addBlock(CTNHBlocks.CASING_ADVANCED_HYPER,"暗物质强化超能硅岩机械方块");
        provider.addBlock(CTNHBlocks.ADVANCE_MACHINE_CASING_SOLID_STEEL, "特种钢质外壳");
        provider.addBlock(CTNHBlocks.ADVANCE_MACHINE_CASING_GRATE, "进阶装配线格栅方块");
        provider.addBlock(CTNHBlocks.CASING_HYPER,"黑钚强化硅岩合金机械方块");
        provider.addBlock(CTNHBlocks.COIL_ABYSALALLOY, "渊狱合金线圈");
        provider.addBlock(CTNHBlocks.COIL_TITANSTEEL, "泰坦钢线圈");
        provider.addBlock(CTNHBlocks.COIL_PIKYONIUM, "皮卡优线圈");
        provider.addBlock(CTNHBlocks.COIL_BLACKTITANIUM, "黑钛合金线圈");
        provider.addBlock(CTNHBlocks.COIL_STARMETAL, "星辉线圈");
        provider.addBlock(CTNHBlocks.COIL_INFINITY, "无尽线圈");
        provider.addBlock(CTNHBlocks.CASING_SPACE_ELEVATOR_MECHANICAL, "太空电梯机械方块");
        provider.addBlock(CTNHBlocks.HIGH_GRADE_COKE_OVEN_BRICKS, "高级焦炉砖");
        provider.addBlock(CTNHBlocks.SPACE_ELEVATOR_POWER_CORE, "太空电梯维持反应堆核心");
        provider.addBlock(CTNHBlocks.BLAZE_BLAST_FURNACE_CASING, "炽焱高炉机械外壳");
        provider.addBlock(CTNHBlocks.MANA_STEEL_CASING,"魔力钢机械外壳");
        provider.addBlock(CTNHBlocks.TERRA_STEEL_CASING,"泰拉钢机械外壳");
        provider.addBlock(CTNHBlocks.ELEMENTIUM_CASING,"源质钢机械外壳");
        provider.addBlock(CTNHBlocks.ALF_STEEL_CASING,"精灵钢机械外壳");
        provider.addBlock(CTNHBlocks.REACTOR_CONDENSATION_BLOCK,"反应堆冷凝方块");
        provider.addBlock(CTNHBlocks.DEPTH_FORCE_FIELD_STABILIZING_CASING,"深度力场稳定外壳");
        provider.addBlock(CTNHBlocks.BRONZE_FRAMED_GLASS, "青铜镶边玻璃");
        provider.addBlock(CTNHBlocks.BIO_REACTOR_CASING, "生物反应器外壳");
        provider.addBlock(CTNHBlocks.ELEMENTIUM_NORMAL_FLUID_PIPE,"源质管道方块");
        provider.addBlock(CTNHBlocks.CASING_MANASTEEL_GEARBOX,"魔力钢齿轮箱方块");
        provider.addBlock(CTNHBlocks.RESERVOIR_COMPUTING_CASING,"高能突触机器外壳");
        provider.addBlock(CTNHBlocks.CASING_NAQUADAH_GEARBOX, "硅岩合金齿轮箱机械方块");
        provider.addBlock(CTNHBlocks.CASING_ULTIMATE_ENGINE_INTAKE, "无尽引擎进气机械方块");
        provider.addBlock(CTNHBlocks.COIL_ULTRA_MANA,"类星体魔力线圈方块");
        provider.addBlock(CTNHBlocks.ELEMENTIUM_PIPE_CASING, "源质钢管道机械方块");
        provider.addBlock(CTNHBlocks.TEST_CASING,"测试方块");
        provider.addBlock(CTNHBlocks.ATOMS_SPLIT_BLOCKS, "原子裂解方块");
        provider.addBlock(CTNHBlocks.QUASAR_ENERGY_STABILIZATION_CASING,"类星体能量稳定机械外壳");
        provider.addBlock(CTNHBlocks.WIDESPEEDINGPIPE,"广粒子加速器通道");
        provider.addBlock(CTNHBlocks.ARC_CELL,"电弧发生器");
        provider.addBlock(CTNHBlocks.CASING_BLOOD, "血染机械方块");
        provider.addBlock(CTNHBlocks.CASING_FORCE_FILED, "力场领域机械方块");
        provider.addBlock(AstralBlocks.ASTRAL_LOG, "星辉木");
        provider.addBlock(AstralBlocks.ASTRAL_STONE, "星辉石");
        provider.addBlock(AstralBlocks.ASTRAL_COBBLESTONE, "星辉圆石");
        provider.addBlock(AstralBlocks.ASTRAL_SAND, "星辉沙");
        provider.addBlock(AstralBlocks.ASTRAL_DIRT, "星辉泥土");
        provider.addBlock(AstralBlocks.ASTRAL_GRASS_BLOCK, "星辉草方块");
        provider.addBlock(AstralBlocks.ASTRAL_SAPLING, "星辉树苗");
        provider.addBlock(AstralBlocks.ASTRAL_GRASS, "星辉草");
        provider.addBlock(AstralBlocks.ASTRAL_TALL_GRASS, "星辉高草丛");
        provider.addBlock(AstralBlocks.PEPPER_CRATE, "箱装辣椒");
        provider.addBlock(AstralBlocks.GARLIC_CRATE, "箱装大蒜");
        provider.addBlock(AstralBlocks.CASSAVA_CRATE, "箱装木薯");
        provider.addBlock(AstralBlocks.FRUIT_CAFE_CRATE, "箱装水果");
        provider.addBlock(AstralBlocks.ASPARAGUS_CRATE, "箱装芦荟");
        provider.addBlock(CTNHBlocks.CASING_NEUTRONIUM_ALLOY_BLOCK,"以太强化超能中子基岩合金钅达智能机械方块");
        provider.addBlock(AstralBlocks.BLUE_FLOWER, "蓝焰花");
        provider.addBlock(AstralBlocks.PINK_FLOWER, "粉球花");
        provider.addBlock(CTNHBlocks.PHOTON_PRESS_COND_BLOCK,"光压传导光伏方块");
        provider.addBlock(CTNHBlocks.STELLAR_RADIATION_ROUTER_CASING,"恒星辐射分流方块");
        provider.addBlock(CTNHBlocks.PV_COIL,"光伏线圈方块");
        provider.addBlock(CTNHBlocks.NQ_EXCITE_CARBON_CARBON_NANOFIBER_STRUCTURAL_BLOCK,"硅岩激发碳纳米太空结构方块");
        provider.addBlock(CTNHBlocks.CASING_SHIELDED_REACTOR, "覆层核反应堆外壳");
        provider.addBlock(CTNHBlocks.SUPERCOOLED_BLOCK,"超级冷冻机械线圈");
        provider.addBlock(CTNHBlocks.HYPER_PLASMA_TURBINE_ROTOR,"超極等离子涡轮转子");
        provider.addBlock(CTNHBlocks.NEUTRONIUM_REINFORCED_TURBINE_CASING,"中子素强化涡轮外壳");
        provider.addBlock(CTNHBlocks.DEMON_FLYTRAP, "恶魔捕蝇草");
        provider.addBlock(CTNHBlocks.BLOOD_ANTIARIS, "见血封喉");
        provider.add(MultiblocksA.UNDERFLOOR_HEATING_SYSTEM.getBlock(), "地暖");
        provider.add(MultiblocksA.ASTRONOMICAL_OBSERVATORY.getBlock(), "天文台");
        provider.add(MultiblocksA.PHOTOVOLTAIC_POWER_STATION_ENERGETIC.getBlock(), "充能光伏发电站");
        provider.add(MultiblocksA.PHOTOVOLTAIC_POWER_STATION_PULSATING.getBlock(), "脉冲光伏发电站");
        provider.add(MultiblocksA.PHOTOVOLTAIC_POWER_STATION_VIBRANT.getBlock(), "振动光伏发电站");
        provider.add(MultiblocksA.WIND_POWER_ARRAY.getBlock(), "风力发电阵列");
        provider.add(MultiblocksA.ADVANCED_WIND_POWER_ARRAY.getBlock(), "进阶风力发电阵列");
        provider.add(MultiblocksA.SUPER_WIND_POWER_ARRAY.getBlock(), "超级风力发电阵列");
        provider.add(MultiblocksA.SLAUGHTER_HOUSE.getBlock(), "屠宰场");
        provider.add(MultiblocksA.BIG_DAM.getBlock(), "三峡大坝");
        provider.add(MultiblocksA.COKE_TOWER.getBlock(), "焦化塔");
        provider.add(MultiblocksA.PLASMA_CONDENSER.getBlock(),"等离子冷凝器");
        provider.add(MultiblocksA.ZENITH_LASER.getBlock(), "§5天顶激光蚀刻机");
        provider.add(MultiblocksA.BEDROCK_DRILLING_RIGS.getBlock(), "基岩钻机");
        provider.add(MultiblocksA.NAQ_REACTOR_MK3.getBlock(),"超级硅岩反应堆");
        provider.add(MultiblocksA.SWEATSHOP.getBlock(),"§4血汗工厂");
        provider.add(MultiblocksA.DEMON_WILL_GENERATOR.getBlock(),"§b恶魔意志发电机");
        provider.add(MultiblocksA.MEADOW.getBlock(),"§6牧场");
        provider.add(MultiblocksA.FERMENTING_TANK.getBlock(), "发酵罐");
        provider.add(MultiblocksA.LARGE_FERMENTING_TANK.getBlock(), "大型发酵罐");
        provider.add(MultiblocksA.DIGESTION_TANK.getBlock(),"化粪池");
        provider.add(MultiblocksA.BLAZE_BLAST_FURNACE.getBlock(), "§c炽焱高炉");
        provider.add(MultiblocksA.MANA_MACERATOR.getBlock(),"§b魔力粉碎机");
        provider.add(MultiblocksA.MANA_BENDER.getBlock(),"§b魔力卷板机");
        provider.add(MultiblocksA.MANA_WIREMILL.getBlock(),"§b魔力线材轧机");
        provider.add(MultiblocksA.MANA_LATHE.getBlock(),"§b魔力车床");
        provider.add(MultiblocksA.MANA_ASSEMBLER.getBlock(),"§b魔力组装机");
        provider.add(MultiblocksA.LARGE_BOTTLE.getBlock(), "发酵瓶");
        provider.add(MultiblocksA.MANA_GENERATOR_TIER1.getBlock(), "魔力涡轮发电机MK1");
        provider.add(MultiblocksA.MANA_GENERATOR_TIER2.getBlock(), "魔力涡轮发电机MK2");
        provider.add(MultiblocksA.MANA_GENERATOR_TIER3.getBlock(), "魔力涡轮发电机MK3");
        provider.add(MultiblocksA.MANA_GENERATOR_TIER4.getBlock(), "魔力涡轮发电机MK4");
        provider.add(MultiblocksA.SUPER_EBF.getBlock(),"超级电力高炉");
        provider.add(MultiblocksA.MEGA_LCR.getBlock(), "巨型化学反应釜");
        provider.add(MultiblocksA.EV_CHEMICAL_GENERATOR.getBlock(), "化学能发电机");
        provider.add(MultiblocksA.IV_CHEMICAL_GENERATOR.getBlock(), "化学能吞噬者");
        provider.add(MultiblocksA.MEGA_OIL_CRACKING_UNIT.getBlock(), "巨型原油裂解厂");
        provider.add(MultiblocksA.INDUSTRIAL_PRIMITIVE_BLAST_FURNACE.getBlock(), "工业土高炉");
        provider.add(MultiblocksA.VOID_MINER.getBlock(), "虚空采矿场");
        provider.add(MultiblocksA.SINTERING_KILN.getBlock(), "烧结窑");
        provider.add(MultiblocksA.CHEMICAL_VAPOR_DEPOSITION_MACHINE.getBlock(), "化学气相沉积器");
        provider.add(MultiblocksA.MARTIAL_MORALITY_EYE.getBlock(),"武德之眼");
        provider.add(MultiblocksA.ADVANCED_COKE_OVEN.getBlock(),"高级焦炉");
        provider.add(MultiblocksA.DIMENSIONAL_GAS_COLLECTION_CHAMBER.getBlock(),"维度集气室");
        provider.add(MultiblocksA.CONDENSING_DISCRETE.getBlock(),"冷凝离散塔");
        provider.add(MultiblocksA.ION_EXCHANGER.getBlock(),"离子交换机");
        provider.add(MultiblocksA.LARGE_STEEL_FURNACE.getBlock(),"大型钢制熔炉");
        provider.add(MultiblocksA.LARGE_STEEL_ALLOY_FURNACE.getBlock(),"大型钢制合金炉");
        provider.add(MultiblocksA.ZPM_LARGE_MINER.getBlock(),"§c精英大型采矿机 III§r");
        provider.add(MultiblocksA.DECAY_POOLS.getBlock(), "衰变罐");
        provider.add(MultiblocksA.FUEL_REFINING_FACTORY.getBlock(), "燃料精炼厂");
        provider.add(MultiblocksA.VACUUM_SINTERING_TOWER.getBlock(), "真空烧结厂");
        provider.add(MultiblocksA.CRYSTALLIZER.getBlock(), "结晶器");
        provider.add(MultiblocksA.WATER_POWER_STATION.getBlock(), "水电站");
        provider.add(MultiblocksA.SEAWATER_DESALTING_FACTORY.getBlock(), "海水晒盐工厂");
        provider.add(MultiblocksA.BIO_REACTOR.getBlock(), "生物反应器");
        provider.add(MultiblocksA.MANA_MIXER.getBlock(),"§b魔力搅拌机");
        provider.add(MultiblocksA.SUPER_CENTRIFUGE.getBlock(), "超速离心机");
        provider.add(MultiblocksA.ULTRASONIC_APPARATUS.getBlock(), "超声破碎仪");
        provider.add(MultiblocksA.ULTIMATE_COMBUSTION_ENGINE.getBlock(), "无尽内燃引擎");
        provider.add(MultiblocksB.ZENITH_CIRCUIT_ASSEMBLER.getBlock(), "§5天顶电路组装机§r");
        provider.add(MultiblocksB.ZENITH_DISTILLATION_TOWER.getBlock(),"§5天顶聚焦蒸馏塔");
        provider.add(MultiblocksB.SCALABLE_RESERVOIR_COMPUTING.getBlock(), "§j突触凝练机");
        provider.add(MultiblocksB.SILICA_ROCK_FUEL_REFINERY.getBlock(),"硅岩燃料精炼厂");
        provider.add(MultiblocksB.INDUSTRIAL_ALTAR.getBlock(), "§b工业血之祭坛");
        provider.add(MultiblocksB.EYE_OF_QUASAR.getBlock(), "§5类星体§r§1之§c眼");
        provider.add(MultiblocksB.ETERNAL_WELL_OF_SUFFER.getBlock(), "§4永恒苦难之井§r");
        provider.add(MultiblocksB.HELLFORGE.getBlock(), "§b工业狱火锻炉§r");
        provider.add(MultiblocksB.NICOLL_DYSON_BEAMS.getBlock(), "§9尼魔尔—戴森光束");
        provider.add(MultiblocksB.NANOGENERATOR.getBlock(), "纳米摩擦发电机");
        provider.add(MultiblocksB.TWISTED_FUSION_MK1.getBlock(),"扭曲聚变反应堆mk1");
        provider.add(MultiblocksB.TWISTED_FUSION_MK2.getBlock(),"扭曲聚变反应堆mk2");
        provider.add(MultiblocksB.TWISTED_FUSION_MK3.getBlock(),"扭曲聚变反应堆mk3");
        provider.add(MultiblocksB.TWISTED_FUSION_MKINFINITY.getBlock(),"扭曲聚变反应堆mk∞");
        provider.add(MultiblocksB.TWISTED_FUSION_MKALEPHNULL.getBlock(), "扭曲聚变反应恒星ℵ₀");
        provider.add(MultiblocksB.MAGIC_FUEL_GENERATOR.getBlock(),"魔导燃料精炼场");
        provider.add(MultiblocksB.FOREST_SEA_TREE_FARM.getBlock(), "林海树场");
        provider.add(MultiblocksB.SINOPE_CHEMICAL.getBlock(), "SINOPE化工厂");
        provider.add(MultiblocksB.WIDE_PARTICLE_ACCELERATOR.getBlock(), "广粒子加速器");
        provider.add(MultiblocksB.MANA_REACTOR.getBlock(),"魔力反应器");
        provider.add(MultiblocksB.ARC_GENERATOR.getBlock(),"电弧撕裂者");
        provider.add(MultiblocksB.ARC_REACTOR.getBlock(),"电弧发生器");
        provider.add(MultiblocksB.ADVANCED_ASSEMBLY_LINE.getBlock(), "进阶装配线");
        provider.add(MultiblocksB.CultivationRoom.getBlock(), "培养室");
        provider.add(MultiblocksB.MECHANICAL_LATHE.getBlock(), "机械车床厂");
        provider.add(MultiblocksB.MECHANICAL_CENTRIFUGE.getBlock(), "机械离心厂");
        provider.add(MultiblocksB.MECHANICAL_EXTRACTOR.getBlock(), "机械熔炼厂");
        provider.add(MultiblocksB.MECHANICAL_LASER.getBlock(), "机械激光厂");
        provider.add(MultiblocksB.MECHANICAL_MIXER.getBlock(), "机械搅拌厂");
        provider.add(MultiblocksB.MECHANICAL_SIFTER.getBlock(), "机械筛选厂");
        provider.add(MultiblocksB.MECHANICAL_PRESSOR.getBlock(), "机械辊压厂");
        provider.add(MultiblocksB.ETERNAL_GARDEN.getBlock(), "芙蕾雅的永恒花园");
        provider.add(MultiblocksB.SUPERCONDUCTING_PENNING_TRAP.getBlock(), "超导潘宁势阱");
        provider.add(MultiblocksB.ARC_GENERATOR_MK1.getBlock(),"超压电弧撕裂者MK1");
        provider.add(MultiblocksB.ARC_GENERATOR_MK2.getBlock(),"过载电弧撕裂者MK1");
        provider.add(MultiblocksB.PLASMA_ALLOY_BLAST_SMELTER.getBlock(),"等离子合金冶炼转底炉");
        provider.add(MultiblocksB.UNIVERSE_SINOPE.getBlock(), "中石化宇宙处理中心");
        provider.add(MultiblocksB.COMBINED_VAPOR_DEPOSITION_FACILITY.getBlock(), "集成沉积工厂");
        provider.add(MultiblocksB.SPACEPHOTOVOLTAICBASESTATION.getBlock(), "太空光伏基站");
        provider.add(MultiblocksB.LaserSorder.getBlock(),"激光分配仪");
        provider.add(MultiblocksB.PHOTOVOLTAIC_DRONE_STATION.getBlock(), "光伏无人机道标基站");
        provider.add(MultiblocksB.HOT_COOLANT_TURBINE.getBlock(), "热冷却涡轮");
        provider.add(MultiblocksB.NUCLEAR_REACTOR.getBlock(), "核反应堆");
        provider.add(MultiblocksB.GAS_CENTRIFUGE.getBlock(), "气体离心机");
        provider.add(MultiblocksB.CRYOTHEUMFREEZER.getBlock(),"凛冰冷冻机");
        provider.add(MultiblocksB.HYPER_PLASMA_TURBINE.getBlock(), "超極等离子涡轮");
        provider.add(MultiblocksB.NERUOMATRIXCOMPILER.getBlock(),"神经矩阵编译器");
        provider.add(MultiblocksB.HYBRID_POWER_MIXER.getBlock(),"混合动力搅拌机");
        provider.add(MultiblocksB.ZENITH_EXTRUDER.getBlock(),"§5天顶灵能塑形者");
        provider.add(MultiblocksB.FLUID_DRILLING_INF[UHV].getBlock(),"无尽流体钻机");
        provider.add(MultiblocksB.MANA_CONDENSER.getBlock(), "魔力凝缩器");

        provider.add(MultiblocksC.MANA_SEPERATOR.getBlock(), "魔力分选器");
        provider.add(MultiblocksC.GAIA_REACTOR.getBlock(), "盖亚反应器");
        provider.add(MultiblocksC.GREENHOUSE.getBlock(), "温室");
        provider.add(MultiblocksC.METEOR_CAPTURER.getBlock(), "§4坠星操纵者§r");



    }
    public static void replace(@NotNull RegistrateCNLangProvider provider, @NotNull String key,
                               @NotNull String value) {
        try {
            // the regular lang mappings
            Field field = LanguageProvider.class.getDeclaredField("data");
            field.setAccessible(true);
            // noinspection unchecked
            Map<String, String> map = (Map<String, String>) field.get(provider);
            map.put(key, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error replacing entry in datagen.", e);
        }
    }
    public static void nuclearTranslation(RegistrateCNLangProvider provider, Material material, String name) {
        var nuclearProperty = material.getProperty(CTNHPropertyKeys.NUCLEAR);
        replace(provider, material.getUnlocalizedName(), name);
        replace(provider, nuclearProperty.getCarbideMaterial().getUnlocalizedName(), "碳化" + name);
        replace(provider, nuclearProperty.getOxideMaterial().getUnlocalizedName(), "氧化" + name);
        replace(provider, nuclearProperty.getNitrideMaterial().getUnlocalizedName(), "氮化" + name);
        replace(provider, nuclearProperty.getZirconiumAlloyMaterial().getUnlocalizedName(), "锆合金" + name);
//        replace(provider, nuclearProperty.getFuel(material).getDisplayName().getString(), name + "纯净燃料");
//        replace(provider, nuclearProperty.getFuel(nuclearProperty.getCarbideMaterial()).getDisplayName().getString(), name + "TRISO燃料");
//        replace(provider, nuclearProperty.getFuel(nuclearProperty.getOxideMaterial()).getDisplayName().getString(), name + "氧化燃料");
//        replace(provider, nuclearProperty.getFuel(nuclearProperty.getNitrideMaterial()).getDisplayName().getString(), name + "氮化燃料");
//        replace(provider, nuclearProperty.getFuel(nuclearProperty.getZirconiumAlloyMaterial()).getDisplayName().getString(), name + "锆合金燃料");
//        replace(provider, nuclearProperty.getDepletedFuel(material).getDisplayName().getString(), name + "枯竭燃料");
//        replace(provider, nuclearProperty.getDepletedFuel(nuclearProperty.getCarbideMaterial()).getDisplayName().getString(), name + "枯竭TRISO燃料");
//        replace(provider, nuclearProperty.getDepletedFuel(nuclearProperty.getOxideMaterial()).getDisplayName().getString(), name + "枯竭氧化燃料");
//        replace(provider, nuclearProperty.getDepletedFuel(nuclearProperty.getNitrideMaterial()).getDisplayName().getString(), name + "枯竭氮化燃料");
//        replace(provider, nuclearProperty.getDepletedFuel(nuclearProperty.getZirconiumAlloyMaterial()).getDisplayName().getString(), name + "枯竭锆合金燃料");
        replace(provider, GTMaterials.get(material.getName() + "_hexafluoride").getUnlocalizedName(), "六氟化" + name);
        replace(provider, GTMaterials.get(material.getName() + "_hexafluoride_steam_cracked").getUnlocalizedName(), "蒸汽裂解的六氟化" + name);
    }
    public static void nuclearTranslation2(RegistrateCNLangProvider provider, Material material, String name) {
        var nuclearProperty = material.getProperty(CTNHPropertyKeys.NUCLEAR);
        replace(provider, nuclearProperty.getCarbideMaterial().getUnlocalizedName(), "碳化" + name);
        replace(provider, nuclearProperty.getOxideMaterial().getUnlocalizedName(), "氧化" + name);
        replace(provider, nuclearProperty.getNitrideMaterial().getUnlocalizedName(), "氮化" + name);
        replace(provider, nuclearProperty.getZirconiumAlloyMaterial().getUnlocalizedName(), "锆合金" + name);
        if(material.equals(GTMaterials.Plutonium239)){
            replace(provider, GTMaterials.get(material.getName() + "_239_hexafluoride").getUnlocalizedName(), "六氟化" + name);
            replace(provider, GTMaterials.get(material.getName() + "_239_hexafluoride_steam_cracked").getUnlocalizedName(), "蒸汽裂解的六氟化" + name);
        }
        else {
            replace(provider, GTMaterials.get(material.getName() + "_hexafluoride").getUnlocalizedName(), "六氟化" + name);
            replace(provider, GTMaterials.get(material.getName() + "_hexafluoride_steam_cracked").getUnlocalizedName(), "蒸汽裂解的六氟化" + name);
        }
    }
}
