package io.github.cpearl0.ctnhcore.data.lang;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

public final class CTNHCoreLang {

    private CTNHCoreLang() {}

    @Key("ctnh.test_terminal.lack_error")
    @CN("在%s处缺少")
    @EN("At %s, you need")
    static Lang testTerminalLackError;

    @Key("ctnh.test_terminal.wrong_error")
    @CN("在%s处应为")
    @EN("At %s, it should be")
    static Lang testTerminalWrongError;

    @Key("ctnh.test_terminal.position")
    @CN("(%s,%s,%s)")
    @EN("(%s,%s,%s)")
    static Lang testTerminalPosition;

    @Key("ctnh.test_terminal.error_info")
    @CN("(%s)")
    @EN("(%s)")
    static Lang testTerminalErrorInfo;

    @Key("ctnh.test_terminal.success")
    @CN("一切正常！")
    @EN("Everything is OK！")
    static Lang testTerminalSuccess;

    @Key("ctnh.testing_terminal.tooltip.1")
    @CN("用于检测多方块搭建时产生的错误")
    @EN("Use to check the error when building the multiblock")
    static Lang testingTerminalTooltip1;

    @Key("ctnh.testing_terminal.tooltip.2")
    @CN("右键多方块的主方块以显示错误信息")
    @EN("Right-click the controller to show the error info")
    static Lang testingTerminalTooltip2;

    @Key("ctnh.testing_terminal.tooltip.3")
    @CN("按住Shift右键切换普通/翻转模式")
    @EN("Right-click with Shift to change between Normal/Flipped mode")
    static Lang testingTerminalTooltip3;

    @Key("ctnh.me_advanced_terminal.tooltip.1")
    @CN("§l格雷科技-多方块结构终端-异步成型模式")
    @EN("§lGregTech Multiblock Structure Terminal - Asynchronous Formation Mode")
    static Lang meAdvancedTerminalTooltip1;

    @Key("ctnh.me_advanced_terminal.tooltip.2")
    @CN("复刻了曾经的旗舰款，终端屏幕上闪烁着久违的画面")
    @EN("A replica of the former flagship model; its terminal screen flickers with a long-lost display")
    static Lang meAdvancedTerminalTooltip2;

    @Key("ctnh.me_advanced_terminal.tooltip.3")
    @CN("通过ME无线访问点链接到网络")
    @EN("Connects to the network via an ME Wireless Access Point")
    static Lang meAdvancedTerminalTooltip3;

    @Key("ctnh.advanced_ram_wafer.tooltip")
    @CN("更好的内存原料")
    @EN("Enhanced RAM Wafer")
    static Lang advancedRamWaferTooltip;

    @Key("ctnh.advanced_ram_chip.tooltip")
    @CN("更好的随机存取存储器")
    @EN("Advanced Random Access Memory")
    static Lang advancedRamChipTooltip;

    @Key("ctnh.blood_magic_gregtech_hv")
    @CN("在坠星位标仪式中使用此物品作为祭品可以召唤陨石")
    @EN("Use this item as an offering in the Falling Star Beacon Ritual to summon meteorites")
    static Lang bloodMagicGregtechHv;

    @Key("ctnh.stone_process_catalyst")
    @CN("村庄里的石匠掌握这个古老的技术，成为村庄英雄后他就会传授给你")
    @EN("Village stonemasons know this ancient technique - they'll teach you after you become a Hero of the Village")
    static Lang stoneProcessCatalyst;

    @Key("ctnh.metallurgical_catalyst")
    @CN("地狱的猪灵掌握这个技术，尝试与他们交易吧")
    @EN("Nether Piglins possess this knowledge - try bartering with them")
    static Lang metallurgicalCatalyst;

    @Key("ctnh.bauxite_process_catalyst")
    @CN("村庄里的图书管理员掌握这个古老的技术，成为村庄英雄后他就会传授给你")
    @EN("Village librarians guard this ancient technique - prove yourself as a Hero of the Village to learn it")
    static Lang bauxiteProcessCatalyst;

    @Key("ctnh.platinum_metal_catalyst_shard1")
    @CN("久远的时间使他们变成了水里的宝藏，通过钓鱼获得")
    @EN("The tides of time turned these into aquatic treasures - fish them up")
    static Lang platinumMetalCatalystShard1;

    @Key("ctnh.platinum_metal_catalyst_shard2")
    @CN("深渊里的深潜一组掌握这个技术，尝试与他们交易吧")
    @EN("The abyssal Drowned Ones hold this secret - attempt to trade with them")
    static Lang platinumMetalCatalystShard2;

    @Key("ctnh.psionic_medulla")
    @CN("通过血魔法邪恶的生灵萃取仪式萃取艾尔夫海姆精灵获取")
    @EN("Obtain it by extracting Alfheim Elves through the Blood Magic Evil Creature Extraction ritual")
    static Lang psionicMedulla;

    @Key("ctnh.doubt")
    @CN("通过击杀浸泡在生命源质的生物，将其困扰注入生命源质之中获得")
    @EN("Obtain it by killing creatures soaked in Life Essence and infusing their doubt into Life Essence")
    static Lang doubt;

    @Key("ctnh.tooltips.simplecomputationmachine")
    @CN("注意：部分配方需要算力执行")
    @EN("Note: Some recipes require computation to run")
    static Lang tooltipsSimplecomputationmachine;

    @Key("ctnh.recipe.industrial_altar.info.0")
    @CN("消耗/输入的lp量:%.1f")
    @EN("LP consumption/input: %.1f")
    static Lang recipeIndustrialAltarInfo0;

    @Key("ctnh.recipe.quasar_eye.info.0")
    @CN("启动消耗:%.1f")
    @EN("Activation Cost: %.1f")
    static Lang recipeQuasarEyeInfo0;

    @Key("ctnh.recipe.quasar_eye.info.1")
    @CN("能量等级: %d")
    @EN("Energy Tier: %d")
    static Lang recipeQuasarEyeInfo1;

    @Key("ctnh.recipe.quasar_eye.info.2")
    @CN("启动等级: %d")
    @EN("Activation Tier: %d")
    static Lang recipeQuasarEyeInfo2;

    @Key("ctnh.recipe.hellforge.info.minimum_drain")
    @CN("最少：%s意志")
    @EN("Minimum Drain: %s Will")
    static Lang recipeHellforgeInfoMinimumDrain;

    @Key("ctnh.recipe.hellforge.info.drain")
    @CN("消耗：%s意志")
    @EN("Drain: %s Will")
    static Lang recipeHellforgeInfoDrain;

    @Key("ctnh.recipe.accelerator.mode.nu")
    @CN("模式：加速中子")
    @EN("Mode: Neutron Acceleration")
    static Lang recipeAcceleratorModeNu;

    @Key("ctnh.recipe.accelerator.mode.proton")
    @CN("模式：加速质子")
    @EN("Mode: Proton Acceleration")
    static Lang recipeAcceleratorModeProton;

    @Key("ctnh.recipe.accelerator.mode.element")
    @CN("模式：加速电子")
    @EN("Mode: Electron Acceleration")
    static Lang recipeAcceleratorModeElement;

    @Key("ctnh.recipe.accelerator.mode.element.consume")
    @CN("加速类型：电子")
    @EN("Accelerated Particle: Electron")
    static Lang recipeAcceleratorModeElementConsume;

    @Key("ctnh.recipe.accelerator.mode.proton.consume")
    @CN("加速类型：质子")
    @EN("Accelerated Particle: Proton")
    static Lang recipeAcceleratorModeProtonConsume;

    @Key("ctnh.recipe.accelerator.mode.nu.consume")
    @CN("加速类型：中子")
    @EN("Accelerated Particle: Neutron")
    static Lang recipeAcceleratorModeNuConsume;

    @Key("ctnh.recipe.accelerator.mode.speed.m")
    @CN("需求速度：%.2fMev")
    @EN("Required Velocity: %.2f MeV")
    static Lang recipeAcceleratorModeSpeedM;

    @Key("ctnh.recipe.accelerator.mode.speed.g")
    @CN("需求速度:%.2fGev")
    @EN("Required Velocity: %.2f GeV")
    static Lang recipeAcceleratorModeSpeedG;

    @Key("ctnh.common_tooltip.parallel_hatch")
    @CN("·允许使用并行控制仓")
    @EN("Voltage levels increase the number of parallels")
    static Lang commonTooltipParallelHatch;

    @Key("ctnh.common_tooltip.subtick_overclock")
    @CN("当配方运行时间小于1t时,会自动计算并行")
    @EN("When recipe runtime is less than 1 tick, parallel calculations will be performed automatically.")
    static Lang commonTooltipSubtickOverclock;

    @Key("ctnh.common_tooltip.perfect_overclock")
    @CN("无损超频！")
    @EN("Perfect Overclock！")
    static Lang commonTooltipPerfectOverclock;

    @Key("ctnh.common_tooltip.steel_machine.0")
    @CN("只能使用HV级能源仓及以下等级")
    @EN("Can only use HV-grade energy hatches and below")
    static Lang commonTooltipSteelMachine0;

    @Key("ctnh.common_tooltip.steel_machine.1")
    @CN("最大并行为32")
    @EN("Maximum parallelism: 32")
    static Lang commonTooltipSteelMachine1;

    @Key("ctnh.multiblock.underfloor_heating_system.info.efficiency")
    @CN("效率：%d")
    @EN("Efficiency: %d")
    static Lang underfloorHeatingSystemInfoEfficiency;

    @Key("ctnh.multiblock.underfloor_heating_system.info.rate")
    @CN("速率：%s")
    @EN("Rate: %s")
    static Lang underfloorHeatingSystemInfoRate;

    @Key("ctnh.multiblock.underfloor_heating_system.info.rate.tooltip")
    @CN("减少蒸汽的消耗来降低地暖的发热功率")
    @EN("Reduce the consumption of steam to reduce the heating power of the floor heating")
    static Lang underfloorHeatingSystemInfoRateTooltip;

    @Key("ctnh.multiblock.underfloor_heating_system.info.rate_modify")
    @CN("调节速率：")
    @EN("Adjust rate: ")
    static Lang underfloorHeatingSystemInfoRateModify;

    @Key("ctnh.multiblock.underfloor_heating_system.info.steam_consumption")
    @CN("蒸汽消耗速率：%d")
    @EN("Steam consumption rate: %d")
    static Lang underfloorHeatingSystemInfoSteamConsumption;

    @Key("ctnh.multiblock.photovoltaic_power_station.info.invalid")
    @CN("有方块阻挡")
    @EN("Shadowed")
    static Lang photovoltaicPowerStationInfoInvalid;

    @Key("ctnh.multiblock.photovoltaic_power_station.info.night")
    @CN("光照过于微弱")
    @EN("At night")
    static Lang photovoltaicPowerStationInfoNight;

    @Key("ctnh.multiblock.photovoltaic_power_station.info.1")
    @CN("发电效率：%s%%")
    @EN("Efficiency: %s%%")
    static Lang photovoltaicPowerStationInfo1;

    @Key("ctnh.multiblock.photovoltaic_power_station.info.2")
    @CN("产能功率：%s/%s EU/t")
    @EN("Generating: %s/%s EU/t")
    static Lang photovoltaicPowerStationInfo2;

    @Key("ctnh.mutliblock.wind_power_array.info.network_machine")
    @CN("发电网络机器数：%d")
    @EN("Network Machine Count：%d")
    static Lang windPowerArrayInfoNetworkMachine;

    @Key("ctnh.mutliblock.wind_power_array.info.network_machine_efficiency")
    @CN("发电效率：%d")
    @EN("Generating Efficiency: %d")
    static Lang windPowerArrayInfoNetworkMachineEfficiency;

    @Key("ctnh.mutliblock.wind_power_array.info.network_dirty")
    @CN("网络将在%d秒后重建")
    @EN("Network will rebuild in %d second(s)")
    static Lang windPowerArrayInfoNetworkDirty;

    @Key("ctnh.multiblock.slaughter_house.info.mobcount")
    @CN("怪物种类：%d (%s)")
    @EN("Mob Types: %d (%s)")
    static Lang slaughterHouseInfoMobcount;

    @Key("ctnh.multiblock.naq_reactor.info.temperature")
    @CN("§c内核温度: %d")
    @EN("§cCore temperature: %d")
    static Lang naqReactorInfoTemperature;

    @Key("ctnh.multiblock.naq_reactor.info.nickel_consumption")
    @CN("镍等离子体消耗量: %d")
    @EN("Nickel plasma consumption: %d")
    static Lang naqReactorInfoNickelConsumption;

    @Key("ctnh.multiblock.naq_reactor.info.parallel_count")
    @CN("发电并行数: %d")
    @EN("Power generation parallel count: %d")
    static Lang naqReactorInfoParallelCount;

    @Key("ctnh.multiblock.sweat_shop.info.villager_count")
    @CN("员工数量：%s")
    @EN("Employee Count: %s")
    static Lang sweatShopInfoVillagerCount;

    @Key("ctnh.multiblock.sweat_shop.info.basic_rate")
    @CN("基础效率：x%s")
    @EN("Base Productivity: x%s")
    static Lang sweatShopInfoBasicRate;

    @Key("ctnh.multiblock.void_miner.info.cryotheum")
    @CN("极寒之凛冰消耗：%d ")
    @EN("Cryotheum consumption: %d ")
    static Lang voidMinerInfoCryotheum;

    @Key("ctnh.multiblock.void_miner.info.pyrotheum")
    @CN("烈焰之炽焱消耗：%d ")
    @EN("Pyrotheum consumption: %d ")
    static Lang voidMinerInfoPyrotheum;

    @Key("ctnh.multiblock.void_miner.info.overheat")
    @CN("过热!!!")
    @EN("Overheating!!!")
    static Lang voidMinerInfoOverheat;

    @Key("ctnh.multiblock.blaze_blast_furnace.info.pyrotheum")
    @CN("烈焰之炽焱：%d mB")
    @EN("Blazing Pyrotheum: %d mB")
    static Lang blazeBlastFurnaceInfoPyrotheum;

    @Key("ctnh.multiblock.mega_lcr.info.coil")
    @CN("当前线圈温度:%s")
    @EN("Current Coil Temperature: %s")
    static Lang megaLcrInfoCoil;

    @Key("ctnh.multiblock.mega_lcr.info.speed")
    @CN("当前配方时间倍率:%s")
    @EN("Current Recipe Time Multiplier: %s")
    static Lang megaLcrInfoSpeed;

    @Key("ctnh.multiblock.water_power_station.info.0")
    @CN("水量：%d")
    @EN("Water Flow: %d")
    static Lang waterPowerStationInfo0;

    @Key("ctnh.multiblock.water_power_station.info.1")
    @CN("线圈效率：%d%%")
    @EN("Coil Efficiency: %d%%")
    static Lang waterPowerStationInfo1;

    @Key("ctnh.multiblock.water_power_station.info.2")
    @CN("产能功率：%d/%d EU/t")
    @EN("Power Output: %d/%d EU/t")
    static Lang waterPowerStationInfo2;

    @Key("ctnh.multiblock.forest_machine.info.humidity")
    @CN("湿度值：%d")
    @EN("Humidity level: %d")
    static Lang forestMachineInfoHumidity;

    @Key("ctnh.multiblock.forest_machine.info.parallel_count")
    @CN("并行数：%d")
    @EN("Parallel count: %d")
    static Lang forestMachineInfoParallelCount;

    @Key("ctnh.multiblock.zenith_machine.info.max_parallel")
    @CN("最大并行数：%d")
    @EN("Max Parallels：%d")
    static Lang zenithMachineInfoMaxParallel;

    @Key("ctnh.multiblock.zenith_machine.info.now_parallel")
    @CN("当前并行数：%d")
    @EN("Now Parallels：%d")
    static Lang zenithMachineInfoNowParallel;

    @Key("ctnh.multiblock.industrial_altar.info.current_lp")
    @CN("当前含有lp量:%d")
    @EN("Current LP amount: %d")
    static Lang industrialAltarInfoCurrentLp;

    @Key("ctnh.multiblock.industrial_altar.info.max_lp")
    @CN("最大lp量:%d")
    @EN("Max LP amount: %d")
    static Lang industrialAltarInfoMaxLp;

    @Key("ctnh.multiblock.astronomical.info.invalid")
    @CN("只能在夜晚使用")
    @EN("Can only be used at night")
    static Lang astronomicalInfoInvalid;

    @Key("ctnh.multiblock.sinope_chemical.info.level")
    @CN("线圈加速倍率:%d")
    @EN("Coil Accelerating Rate: %d")
    static Lang sinopeChemicalInfoLevel;

    @Key("ctnh.multiblock.sinope_chemical.info.parallel")
    @CN("并行数:%d")
    @EN("Parallel Count: %d")
    static Lang sinopeChemicalInfoParallel;

    @Key("ctnh.multiblock.wide_accelerator.info.nu_speed")
    @CN("中子速度:%.2fMev")
    @EN("Neutron Velocity: %.2f MeV")
    static Lang wideAcceleratorInfoNuSpeed;

    @Key("ctnh.multiblock.wide_accelerator.info.proton_speed")
    @CN("质子速度:%.2fMev")
    @EN("Proton Velocity: %.2f MeV")
    static Lang wideAcceleratorInfoProtonSpeed;

    @Key("ctnh.multiblock.wide_accelerator.info.electric_speed")
    @CN("电子速度:%.2fMev")
    @EN("Electron Velocity: %.2f MeV")
    static Lang wideAcceleratorInfoElectricSpeed;

    @Key("ctnh.multiblock.wide_accelerator.info.consume")
    @CN("电量消耗倍率:%.2f")
    @EN("Power Consumption Multiplier: %.2f")
    static Lang wideAcceleratorInfoConsume;

    @Key("ctnh.multiblock.wide_accelerator.gui.electric")
    @CN("电子轨道")
    @EN("Electron Beamline")
    static Lang wideAcceleratorGuiElectric;

    @Key("ctnh.multiblock.wide_accelerator.gui.nu")
    @CN("中子轨道")
    @EN("Neutron Beamline")
    static Lang wideAcceleratorGuiNu;

    @Key("ctnh.multiblock.wide_accelerator.gui.proton")
    @CN("原子轨道")
    @EN("Proton Beamline")
    static Lang wideAcceleratorGuiProton;

    @Key("ctnh.multiblock.wide_accelerator.gui.name")
    @CN("访问轨道")
    @EN("Access Beamline")
    static Lang wideAcceleratorGuiName;

    @Key("ctnh.multiblock.arcgenerator.info.0")
    @CN("电弧最大强度:%d")
    @EN("Max Arc Intensity: %d")
    static Lang arcgeneratorInfo0;

    @Key("ctnh.multiblock.arcgenerator.info.1")
    @CN("电弧强度:%d")
    @EN("Current Arc Intensity: %d")
    static Lang arcgeneratorInfo1;

    @Key("ctnh.multiblock.arcgenerator.info.2")
    @CN("支持最大效率:%.2f%%")
    @EN("Max Supported Efficiency: %.2f%%")
    static Lang arcgeneratorInfo2;

    @Key("ctnh.multiblock.arcgenerator.info.3")
    @CN("当前效率:%.2f%%")
    @EN("Current Efficiency: %.2f%%")
    static Lang arcgeneratorInfo3;

    @Key("gtceu.multiblock.laser.tooltip")
    @CN("允许使用激光仓")
    @EN("The use of the laser chamber is permitted")
    static Lang gtceuMultiblockLaserTooltip;

    @Key("ctnhcore.copyright.info")
    @CN("§6由CTNH添加")
    @EN("§6Added by CTNH")
    static Lang ctnhCopyrightInfo;

    @Key("ctnh.recipe_type.info")
    @CN("配方类型：%s")
    @EN("Recipe Type：%s")
    static Lang recipeTypeInfo;

    @Key("ctnh.multiblock.plasma_condenser.tooltip.1")
    @CN("氤氲之气，凝为霜露")
    @EN("The dense air condenses into frost and dew")
    static Lang plasmaCondenserTooltip1;

    @Key("ctnh.multiblock.forest_sea.tooltip.1")
    @CN("手植千木，绿荫千秋")
    @EN("Plant trees by hand, create shade for millennia")
    static Lang forestSeaTooltip1;

    @Key("ctnh.multiblock.forest_sea.tooltip.2")
    @CN("林海树场是一个只消耗水来产出大量木材的大机器")
    @EN("The Forest Sea is a massive machine that consumes only water to produce large quantities of lumber")
    static Lang forestSeaTooltip2;

    @Key("ctnh.multiblock.forest_sea.tooltip.3")
    @CN("每5s进行一次水储量的判定")
    @EN("Performs water storage check every 5 seconds")
    static Lang forestSeaTooltip3;

    @Key("ctnh.multiblock.forest_sea.tooltip.4")
    @CN("水充足时，增加1%的湿度值")
    @EN("When water is sufficient, increases humidity by 1%")
    static Lang forestSeaTooltip4;

    @Key("ctnh.multiblock.forest_sea.tooltip.5")
    @CN("水不足时，减少10%的湿度值")
    @EN("When water is insufficient, decreases humidity by 10%")
    static Lang forestSeaTooltip5;

    @Key("ctnh.multiblock.forest_sea.tooltip.6")
    @CN("配方运行时间不变，但并行值会随湿度值与电压等级上升")
    @EN("Recipe processing time remains constant, but parallel value increases with humidity and voltage tier")
    static Lang forestSeaTooltip6;

    @Key("ctnh.multiblock.forest_sea.tooltip.7")
    @CN("比温室好！")
    @EN("Better than greenhouses!")
    static Lang forestSeaTooltip7;

    @Key("ctnh.multiblock.cultivation_room.tooltip.1")
    @CN("菌群孕育，菌种滋长")
    @EN("Microbial incubation, fungal proliferation")
    static Lang cultivationRoomTooltip1;

    @Key("ctnh.multiblock.cultivation_room.tooltip.2")
    @CN("运用好这台机器来繁殖那些难以获取的真菌和细菌")
    @EN("Utilize this machine to cultivate hard-to-obtain fungi and bacteria")
    static Lang cultivationRoomTooltip2;

    @Key("ctnh.multiblock.sweat_shop.tooltip.0")
    @CN("生产资料与剩余价值")
    @EN("Means of Production and Surplus Value")
    static Lang sweatShopTooltip0;

    @Key("ctnh.multiblock.sweat_shop.tooltip.1")
    @CN("工厂内的村民数量决定了工作效率，配方耗时x(2/村民数量)")
    @EN("The number of villagers in the factory determines efficiency. Recipe time x (2 / number of villagers)")
    static Lang sweatShopTooltip1;

    @Key("ctnh.multiblock.sweat_shop.tooltip.2")
    @CN("工厂内的有效员工数量受限于工厂大小，初始上限为4，工厂长度每增加4格上限+1")
    @EN("The effective number of workers in the factory is limited by the factory size. Initial limit: 4 workers; for every 4 blocks added to the factory length, the limit increases by 1.")
    static Lang sweatShopTooltip2;

    @Key("ctnh.multiblock.sweat_shop.tooltip.3")
    @CN("放入的生产资料(机器)决定了可以工作的配方：\n动力辊压机----卷板机配方\n动力搅拌机----搅拌机配方\n车床----车床配方\n离心机----离心机配方\n烈焰人燃烧室----提取机配方\n工作盆----流体成型配方\n粉碎轮----研磨机配方\n动力锯----线材轧机配方\n激光加工器----激光蚀刻配方\n==============================")
    @EN("The production materials (machines) placed determine the available recipes:\nPowered Rolling Machine ---- Rolling Mill Recipes\nPowered Mixer ---- Mixer Recipes\nLathe ---- Lathe Recipes\nCentrifuge ---- Centrifuge Recipes\nBlaze Burner ---- Extractor Recipes\nWork Basin ---- Fluid Forming Recipes\nCrushing Wheel ---- Grinder Recipes\nPowered Saw ---- Wire Rolling Machine Recipes\nLaser Processor ---- Laser Etching Recipes\n==============================")
    static Lang sweatShopTooltip3;

    @Key("ctnh.multiblock.sweat_shop.tooltip.4")
    @CN("放入的生产资料(机器)数量决定了对应工作配方的并行数：并行数 = sqrt(机器数)")
    @EN("The number of production materials (machines) placed determines the parallelism of corresponding recipes: Parallelism = sqrt(number of machines)")
    static Lang sweatShopTooltip4;

    @Key("ctnh.multiblock.sweat_shop.tooltip.5")
    @CN("放入机械手可以提高整体配方运行速度，配方耗时x (1/1 + 0.25 * sqrt(机械手数))")
    @EN("Adding robotic arms improves the overall recipe execution speed. Recipe time x (1 / 1 + 0.25 * sqrt(number of robotic arms))")
    static Lang sweatShopTooltip5;

    @Key("ctnh.multiblock.sweat_shop.tooltip.6")
    @CN("放入机器的多样性会提高配方运行速度")
    @EN("The diversity of machines placed improves recipe execution speed.")
    static Lang sweatShopTooltip6;

    @Key("ctnh.multiblock.sweat_shop.tooltip.7")
    @CN("基础配方耗时为2倍，配方所需的电压等级越高，基础耗时x(配方等级的平方)")
    @EN("The base recipe time is 2x. The higher the recipe voltage tier, the more the base time is multiplied by the square of the recipe tier.")
    static Lang sweatShopTooltip7;

    @Key("ctnh.multiblock.naq_reactor_mk3.tooltip.1")
    @CN("浩瀚能量，天地震动")
    @EN("Vast energy, the earth trembles")
    static Lang naqReactorMk3Tooltip1;

    @Key("ctnh.multiblock.naq_reactor_mk3.tooltip.2")
    @CN("利用超能燃料进行发电,无镍等离子体时无法完全消耗燃料")
    @EN("Generates power using supercharged fuel - cannot fully consume fuel without nickel plasma")
    static Lang naqReactorMk3Tooltip2;

    @Key("ctnh.multiblock.naq_reactor_mk3.tooltip.3")
    @CN("机器构型中必须有一个动力仓")
    @EN("A power core must be present in the machine configuration")
    static Lang naqReactorMk3Tooltip3;

    @Key("ctnh.multiblock.naq_reactor_mk3.tooltip.4")
    @CN("随着内核温度上升,发电效率增大")
    @EN("As the core temperature increases, power generation efficiency improves")
    static Lang naqReactorMk3Tooltip4;

    @Key("ctnh.multiblock.meadow.tooltip.0")
    @CN("§7自动化放牧")
    @EN("§7Automated Grazing")
    static Lang meadowTooltip0;

    @Key("ctnh.multiblock.meadow.tooltip.1")
    @CN("输入配方所需n倍应力时，获得n并行")
    @EN("Gains n parallel processing when provided with n× the required stress.")
    static Lang meadowTooltip1;

    @Key("ctnh.multiblock.meadow.tooltip.2")
    @CN("可以同时养殖不同动物")
    @EN("Allows breeding of different animals at the same time.")
    static Lang meadowTooltip2;

    @Key("ctnh.multiblock.meadow.tooltip.3")
    @CN("§7只有动物跑出去时，你才知道你不是在种菜！")
    @EN("§7Only when the animals run away will you realize that you are not growing crops!")
    static Lang meadowTooltip3;

    @Key("ctnh.multiblock.fermenting_tank.tooltip.0")
    @CN("一个专为微生物提供的生长罐，注意时刻关注他！")
    @EN("A tank designed specifically for microbial growth. Always keep an eye on it!")
    static Lang fermentingTankTooltip0;

    @Key("ctnh.multiblock.fermenting_tank.tooltip.1")
    @CN("发酵罐的生物生长机制：")
    @EN("Biological Growth Mechanism of the Fermenting Tank:")
    static Lang fermentingTankTooltip1;

    @Key("ctnh.multiblock.fermenting_tank.tooltip.2")
    @CN("当发酵罐温度处于§236§r至§238§r度之间时为最适生长温度，配方获得1.2倍效率，越偏离最适生长温度，配方效率越低，最低为三分之一")
    @EN("The optimal growth temperature is between §236§r and §238§r degrees. Recipes get 1.2x efficiency at optimal temperature. The further it deviates, the lower the efficiency, down to one-third.")
    static Lang fermentingTankTooltip2;

    @Key("ctnh.multiblock.fermenting_tank.tooltip.3")
    @CN("微生物的生长符合逻辑斯蒂方程，当输入仓内液体体积为容量的一半时，§2生长效率达到两倍§r，而满仓和空仓时生长效率最低，保底为20%")
    @EN("Microbial growth follows the logistic equation. When the liquid volume in the input tank is half of its capacity, §2growth efficiency doubles§r. Efficiency is lowest when the tank is full or empty, with a minimum of 20%.")
    static Lang fermentingTankTooltip3;

    @Key("ctnh.multiblock.large_fermenting_tank.tooltip.0")
    @CN("高效工业化发酵生产")
    @EN("Efficient Industrial Fermentation")
    static Lang largeFermentingTankTooltip0;

    @Key("ctnh.multiblock.large_fermenting_tank.tooltip.1")
    @CN("可接入附属结构，在对应位置连接上一个大发酵瓶后，可以根据发酵瓶中的液体种类提升保底效率：水(50%)，简易培养基(150%)，无菌培养基(200%)")
    @EN("Can connect auxiliary structures. By attaching a large fermentation bottle with a specific liquid type, the minimum efficiency increases: Water (50%), Basic Medium (150%), Sterile Medium (200%).")
    static Lang largeFermentingTankTooltip1;

    @Key("ctnh.multiblock.large_bottle.tooltip.0")
    @CN("真是一个大罐子")
    @EN("This is truly a large container.")
    static Lang largeBottleTooltip0;

    @Key("ctnh.multiblock.large_bottle.tooltip.1")
    @CN("可以存储10000桶液体")
    @EN("Can store up to 10,000 buckets of liquid.")
    static Lang largeBottleTooltip1;

    @Key("ctnh.multiblock.large_bottle.tooltip.2")
    @CN("与大型发酵罐一起使用时，其中的液体会以§e100mb/s§r的速度消耗")
    @EN("When used with a large fermenting tank, its liquid will be consumed at a rate of §e100mb/s§r.")
    static Lang largeBottleTooltip2;

    @Key("ctnh.multiblock.digestion_tank.tooltip.0")
    @CN("其实产生的是很有价值的原料......")
    @EN("Actually, it produces very valuable materials...")
    static Lang digestionTankTooltip0;

    @Key("ctnh.multiblock.digestion_tank.tooltip.1")
    @CN("化粪池堆肥机制：")
    @EN("Composting Mechanism of the Digestion Tank:")
    static Lang digestionTankTooltip1;

    @Key("ctnh.multiblock.digestion_tank.tooltip.2")
    @CN("当化粪池温度处于§236§r至§238§r度之间时为最适生长温度，配方获得1.2倍效率，越偏离最适生长温度，配方效率越低，最低为三分之一")
    @EN("The optimal growth temperature is between §236§r and §238§r degrees. Recipes get 1.2x efficiency at optimal temperature. The further it deviates, the lower the efficiency, down to one-third.")
    static Lang digestionTankTooltip2;

    @Key("ctnh.multiblock.blaze_blast_furnace.tooltip.0")
    @CN("比电力高炉快")
    @EN("Faster than an electric blast furnace.")
    static Lang blazeBlastFurnaceTooltip0;

    @Key("ctnh.multiblock.blaze_blast_furnace.tooltip.1")
    @CN("每秒基础消耗§a10mB§r烈焰之炽焱，电压每超过§6HV§r一级，消耗量变为原来的两倍")
    @EN("Base consumption is §a10mB§r of Blazing Pyrotheum per second. For each voltage tier above §6HV§r, the consumption doubles.")
    static Lang blazeBlastFurnaceTooltip1;

    @Key("ctnh.multiblock.blaze_blast_furnace.tooltip.2")
    @CN("运行耗能x0.75")
    @EN("Consumes 0.75x energy.")
    static Lang blazeBlastFurnaceTooltip2;

    @Key("ctnh.multiblock.blaze_blast_furnace.tooltip.3")
    @CN("允许一次性处理8个配方")
    @EN("Allows processing of 8 recipes simultaneously.")
    static Lang blazeBlastFurnaceTooltip3;

    @Key("ctnh.multiblock.large_steel_furnace.tooltip.0")
    @CN("钢质熔炉")
    @EN("Steel Furnace")
    static Lang largeSteelFurnaceTooltip0;

    @Key("ctnh.multiblock.large_steel_alloy_furnace.tooltip.0")
    @CN("钢质合金炉")
    @EN("Steel Alloy Furnace")
    static Lang largeSteelAlloyFurnaceTooltip0;

    @Key("ctnh.multiblock.advanced_coke_oven.tooltip.0")
    @CN("高级焦炉")
    @EN("Advanced Coke Oven")
    static Lang advancedCokeOvenTooltip0;

    @Key("ctnh.multiblock.advanced_coke_oven.tooltip.1")
    @CN("§6§l自带32并行")
    @EN("§6§lComes with 32 parallelism")
    static Lang advancedCokeOvenTooltip1;

    @Key("ctnh.multiblock.advanced_coke_oven.tooltip.2")
    @CN("只可运行焦炉配方,且运行配方时间固定为15s")
    @EN("Can only run coke oven recipes, and recipe time is fixed at 15 seconds")
    static Lang advancedCokeOvenTooltip2;

    @Key("ctnh.multiblock.advanced_coke_oven.tooltip.3")
    @CN("产生大量的焦化产物与杂酚油")
    @EN("Produces a large amount of coke products and phenolic oil")
    static Lang advancedCokeOvenTooltip3;

    @Key("ctnh.multiblock.advanced_coke_oven.tooltip.4")
    @CN("§c§l不能使用焦炉仓")
    @EN("§c§lCannot use coke oven cells")
    static Lang advancedCokeOvenTooltip4;

    @Key("ctnh.multiblock.large_gas_collection_chamber.tooltip.0")
    @CN("全维度集气")
    @EN("Dimensional Gas Collection Chamber")
    static Lang largeGasCollectionChamberTooltip0;

    @Key("ctnh.multiblock.large_gas_collection_chamber.tooltip.1")
    @CN("这台机器可以收集任意维度的气体")
    @EN("This machine can collect gases from any dimension")
    static Lang largeGasCollectionChamberTooltip1;

    @Key("ctnh.multiblock.large_gas_collection_chamber.tooltip.2")
    @CN("由于它的产量较大，建议你用ME输出总成来收集产物")
    @EN("Since its output is large, it is recommended to use an ME Output Assembly to collect the products")
    static Lang largeGasCollectionChamberTooltip2;

    @Key("ctnh.multiblock.underfloor_heating_system.tooltip.0")
    @CN("用蒸汽温暖你的心")
    @EN("Warm your heart with steam")
    static Lang underfloorHeatingSystemTooltip0;

    @Key("ctnh.multiblock.underfloor_heating_system.tooltip.1")
    @CN("地暖系统依靠蒸汽供暖，占地一个区块，能对§a周围5*5的区块§r产生供暖，供暖只会在地暖上方十格内生效")
    @EN("The underfloor heating system uses steam for heating. Occupying one chunk, it can heat §aa 5×5 chunk area§r around it. Heating only works within 10 blocks above the system")
    static Lang underfloorHeatingSystemTooltip1;

    @Key("ctnh.multiblock.underfloor_heating_system.tooltip.2")
    @CN("铜砖瓦会生锈，生锈后地暖系统的供暖能力会减弱")
    @EN("Copper brick tiles will rust over time, reducing the heating efficiency of the system when rusted")
    static Lang underfloorHeatingSystemTooltip2;

    @Key("ctnh.multiblock.underfloor_heating_system.tooltip.3")
    @CN("可以调节速率，以降低供暖功率并减少蒸汽消耗，最低降至25%")
    @EN("Adjustable rate allows reducing heating power and steam consumption, with minimum setting at 25%")
    static Lang underfloorHeatingSystemTooltip3;

    @Key("ctnh.multiblock.slaughter_house.tooltip.0")
    @CN("无情的杀戮机器")
    @EN("A merciless killing machine")
    static Lang slaughterHouseTooltip0;

    @Key("ctnh.multiblock.slaughter_house.tooltip.1")
    @CN("输入总线放入电动刷怪笼后，机器会自动输出对应怪物的战利品，可放入多个电动刷怪笼")
    @EN("When powered spawners are placed in the input bus, the machine will automatically output corresponding mob drops. Multiple powered spawners can be inserted")
    static Lang slaughterHouseTooltip1;

    @Key("ctnh.multiblock.slaughter_house.tooltip.2")
    @CN("电压每升高1级，虚拟刷怪量会增加4（HV为4）")
    @EN("Each voltage tier increase adds +4 to virtual spawn count (HV provides 4)")
    static Lang slaughterHouseTooltip2;

    @Key("ctnh.multiblock.slaughter_house.tooltip.3")
    @CN("怪物血量和护甲值越高，配方运行所需时间越长")
    @EN("Higher mob health and armor values will increase processing time")
    static Lang slaughterHouseTooltip3;

    @Key("ctnh.multiblock.slaughter_house.tooltip.4")
    @CN("武器的伤害和附魔会减少配方运行的时间")
    @EN("Weapon damage and enchantments will reduce processing time")
    static Lang slaughterHouseTooltip4;

    @Key("ctnh.multiblock.slaughter_house.tooltip.5")
    @CN("时运等附魔也能生效")
    @EN("Fortune and other enchantments also take effect")
    static Lang slaughterHouseTooltip5;

    @Key("ctnh.multiblock.industrial_primitive_blast_furnace.tooltip.0")
    @CN("更强大的土高炉，你的炼钢好帮手")
    @EN("A more powerful primitive blast furnace, your best helper for steelmaking")
    static Lang industrialPrimitiveBlastFurnaceTooltip0;

    @Key("ctnh.multiblock.industrial_primitive_blast_furnace.tooltip.1")
    @CN("工业土高炉在持续运行配方时，会不断升温，而一旦中止，则会迅速冷却")
    @EN("The industrial primitive blast furnace will continuously heat up while running a recipe, and will cool down rapidly once the process is stopped")
    static Lang industrialPrimitiveBlastFurnaceTooltip1;

    @Key("ctnh.multiblock.industrial_primitive_blast_furnace.tooltip.2")
    @CN("温度越高，工业土高炉的并行数越高，最高为8并行")
    @EN("The higher the temperature, the higher the parallelism of the industrial primitive blast furnace, up to a maximum of 8 parallelism")
    static Lang industrialPrimitiveBlastFurnaceTooltip2;

    @Key("ctnh.multiblock.industrial_primitive_blast_furnace.tooltip.3")
    @CN("温度越高，工业土高炉的效率越高，最高为两倍效率")
    @EN("The higher the temperature, the higher the efficiency of the industrial primitive blast furnace, up to a maximum of double efficiency")
    static Lang industrialPrimitiveBlastFurnaceTooltip3;

    @Key("ctnh.multiblock.sintering_kiln.tooltip.0")
    @CN("需要通入8192应力使其内部活塞压实待加工料")
    @EN("Requires 8,192 Stress to activate internal pistons for compacting materials")
    static Lang sinteringKilnTooltip0;

    @Key("ctnh.multiblock.decay_pools.tooltip.0")
    @CN("衰变")
    @EN("Decay")
    static Lang decayPoolsTooltip0;

    @Key("ctnh.multiblock.decay_pools.tooltip.1")
    @CN("当电路板为0时为不通电状态---不启用世界加速")
    @EN("When the circuit board is set to 0, the machine is unpowered and world acceleration is disabled.")
    static Lang decayPoolsTooltip1;

    @Key("ctnh.multiblock.decay_pools.tooltip.2")
    @CN("当电路板为1时为通电状态---启用世界加速")
    @EN("When the circuit board is set to 1, the machine is powered and world acceleration is enabled.")
    static Lang decayPoolsTooltip2;

    @Key("ctnh.multiblock.decay_pools.tooltip.3")
    @CN("加速衰变过程")
    @EN("Accelerates the decay process.")
    static Lang decayPoolsTooltip3;

    @Key("ctnh.multiblock.vacuum_sintering_tower.tooltip.0")
    @CN("真空烧结")
    @EN("Vacuum Sintering")
    static Lang vacuumSinteringTowerTooltip0;

    @Key("ctnh.multiblock.crystallizer.tooltip.0")
    @CN("专业结晶")
    @EN("Professional Crystallization")
    static Lang crystallizerTooltip0;

    @Key("ctnh.multiblock.crystallizer.tooltip.1")
    @CN("结晶器能更加快速的完成晶体配方")
    @EN("The crystallizer completes crystal recipes more efficiently.")
    static Lang crystallizerTooltip1;

    @Key("ctnh.multiblock.crystallizer.tooltip.2")
    @CN("随着线圈等级上升，工作效率逐级提升")
    @EN("Efficiency improves as the coil level increases.")
    static Lang crystallizerTooltip2;

    @Key("ctnh.multiblock.crystallizer.tooltip.3")
    @CN("可以运行部分化学气相沉积的配方和部分高压釜的配方")
    @EN("Can process chemical vapor deposition recipes and some autoclave recipes.")
    static Lang crystallizerTooltip3;

    @Key("ctnh.multiblock.crystallizer.tooltip.4")
    @CN("省材料的最佳帮手")
    @EN("The best assistant for saving materials.")
    static Lang crystallizerTooltip4;

    @Key("ctnh.multiblock.desalting_factory.tooltip.0")
    @CN("从海水中烘干出盐，很环保不是吗？")
    @EN("Drying salt out of seawater—eco-friendly, isn't it?")
    static Lang desaltingFactoryTooltip0;

    @Key("ctnh.multiblock.water_power_station.tooltip.0")
    @CN("环保能源！")
    @EN("Eco-Friendly Energy!")
    static Lang waterPowerStationTooltip0;

    @Key("ctnh.multiblock.water_power_station.tooltip.1")
    @CN("发电量和以控制器为中心，机器长度为半径，高为4的范围内的水量成正比")
    @EN("Power generation is proportional to the amount of water within a radius equal to the machine length and height of 4, centered on the controller.")
    static Lang waterPowerStationTooltip1;

    @Key("ctnh.multiblock.water_power_station.tooltip.2")
    @CN("发电量随机在0.6至1的倍率间波动")
    @EN("Power output fluctuates randomly between a multiplier of 0.6 to 1.")
    static Lang waterPowerStationTooltip2;

    @Key("ctnh.multiblock.bio_reactor.tooltip.0")
    @CN("一个大罐子")
    @EN("A big tank")
    static Lang bioReactorTooltip0;

    @Key("ctnh.computer.a1")
    @CN("§c一切伟大之作都需要§4牺牲§r§j来铸就。其他生物或许不能理解，但他们必将§4服从§r。")
    @EN("§cAll great works require§4 sacrifice§r§j to forge. Other beings may not understand, but they will§4 obey§r.")
    static Lang computerA1;

    @Key("ctnh.computer.a2")
    @CN("机器类型:§c突触凝练机")
    @EN("Machine type: §cSynapse Refining Machine")
    static Lang computerA2;

    @Key("ctnh.computer.a3")
    @CN("将其他智慧生物作为§4湿件§r来进行运算，获得大量算力，甚至直接做成湿件")
    @EN("Uses other intelligent beings as §4wetware§r for computation, gaining massive processing power, even converting them into wetware.")
    static Lang computerA3;

    @Key("ctnh.computer.a4")
    @CN("机制介绍占位符")
    @EN("Mechanism introduction placeholder")
    static Lang computerA4;

    @Key("ctnh.computer.a5")
    @CN("该机器会超载所有智慧生物体的大脑。§4不可避免§r地§4永久损坏§r智慧生物的大脑，§4不会留下§r任何掉落物")
    @EN("This machine will overload the brains of all intelligent beings. §4Inevitable§r §4permanent damage§r to their brains,§4 no drops§r will be left.")
    static Lang computerA5;

    @Key("ctnh.computer.a6")
    @CN("诸如村民这种§7低智慧§r的新人类的生命与智慧太低了，我们需要§c更加聪明，可爱和生命更高的生物§r")
    @EN("For beings like villagers, who are §7low-intelligence§r new humans, their life and intellect are too low. We need§c smarter, cuter, and more life-capable beings§r.")
    static Lang computerA6;

    @Key("ctnh.computer.a7")
    @CN("为了无尽的知识，我们必须§4做出一切必要的牺牲§4")
    @EN("For endless knowledge, we must§4 make all necessary sacrifices§4.")
    static Lang computerA7;

    @Key("ctnh.multiblock.martial_morality_eye.tooltip.0")
    @CN("丐版鸿蒙之眼")
    @EN("Poor version of the Primordial Eye")
    static Lang martialMoralityEyeTooltip0;

    @Key("ctnh.multiblock.martial_morality_eye.tooltip.1")
    @CN("原始时代时消耗64000mb的蒸汽和64个原石")
    @EN("Consumes 64000mb of steam and 64 raw stones in the early stages")
    static Lang martialMoralityEyeTooltip1;

    @Key("ctnh.multiblock.martial_morality_eye.tooltip.2")
    @CN("产出主世界和暮色森林以及月球的矿")
    @EN("Produces ores from the Overworld, Twilight Forest, and the Moon")
    static Lang martialMoralityEyeTooltip2;

    @Key("ctnh.multiblock.martial_morality_eye.tooltip.3")
    @CN("随着电压等级提高能够解锁更多配方")
    @EN("Unlocks more recipes as the voltage level increases")
    static Lang martialMoralityEyeTooltip3;

    @Key("ctnh.multiblock.martial_morality_eye.tooltip.4")
    @CN("在前期比坠星好用")
    @EN("More useful than falling stars in the early stages")
    static Lang martialMoralityEyeTooltip4;

    @Key("ctnh.multiblock.martial_morality_eye.tooltip.5")
    @CN("结构中心似乎存在着神秘力量，充满危险的气息，请远离！")
    @EN("The center of the structure seems to emanate a mysterious force, filled with an aura of danger. Stay away!")
    static Lang martialMoralityEyeTooltip5;

    @Key("ctnh.multiblock.martial_morality_eye.tooltip.6")
    @CN("结构来源:Twist Space Technology")
    @EN("Structure source: Twist Space Technology")
    static Lang martialMoralityEyeTooltip6;

    @Key("ctnh.multiblock.large_miner_zpm.tooltip.0")
    @CN("听说你很担心矿物的来源？")
    @EN("Heard you're worried about the source of minerals?")
    static Lang largeMinerZpmTooltip0;

    @Key("ctnh.multiblock.astronomical.tooltip.0")
    @CN("知天易，逆天难")
    @EN("Knowing the heavens is easy, but defying them is difficult")
    static Lang astronomicalTooltip0;

    @Key("ctnh.multiblock.astronomical.tooltip.1")
    @CN("无法在阳光直射下工作，工作时会自动为芯片总线中的芯片收集数据")
    @EN("Cannot work under direct sunlight, but will automatically collect data for the chips in the chip bus while working")
    static Lang astronomicalTooltip1;

    @Key("ctnh.multiblock.sinope_chemical.tooltip.0")
    @CN("来自§b某个神秘东方大国§r的工业力量")
    @EN("From §bA certain mysterious eastern country§r's industrial power.")
    static Lang sinopeChemicalTooltip0;

    @Key("ctnh.multiblock.sinope_chemical.tooltip.1")
    @CN("格雷员工不骗格雷员工，并行是真实的")
    @EN("Gray employees don’t deceive gray employees, parallel is real.")
    static Lang sinopeChemicalTooltip1;

    @Key("ctnh.multiblock.sinope_chemical.tooltip.2")
    @CN("没有外壳等级要求，配方不需要催化剂")
    @EN("No shell level requirements, recipes don't need catalysts.")
    static Lang sinopeChemicalTooltip2;

    @Key("ctnh.multiblock.sinope_chemical.tooltip.3")
    @CN("并行数与中心的方块有关")
    @EN("Parallel count is related to the central block.")
    static Lang sinopeChemicalTooltip3;

    @Key("ctnh.multiblock.sinope_chemical.tooltip.4")
    @CN("硅岩块:8并行")
    @EN("Silicon rock block: 8 parallel")
    static Lang sinopeChemicalTooltip4;

    @Key("ctnh.multiblock.sinope_chemical.tooltip.5")
    @CN("富集硅岩块:32并行")
    @EN("Enriched silicon rock block: 32 parallel")
    static Lang sinopeChemicalTooltip5;

    @Key("ctnh.multiblock.sinope_chemical.tooltip.6")
    @CN("超能硅岩块:128并行")
    @EN("Super silicon rock block: 128 parallel")
    static Lang sinopeChemicalTooltip6;

    @Key("ctnh.multiblock.sinope_chemical.tooltip.7")
    @CN("每一点实际的并行数减少0.5%的能耗和运行时间，至多减少25%(独立乘区)")
    @EN("Each point of actual parallel reduces energy consumption and operation time by 0.5%, up to a maximum reduction of 25% (independently multiplied)")
    static Lang sinopeChemicalTooltip7;

    @Key("ctnh.multiblock.sinope_chemical.tooltip.8")
    @CN("线圈每提供1800K，运行速度+100%")
    @EN("Each coil providing 1800K increases the operation speed by +100%")
    static Lang sinopeChemicalTooltip8;

    @Key("ctnh.multiblock.sinope_chemical.tooltip.9")
    @CN("§c任何虚假的并行都将绳之以法!§r")
    @EN("§cAny false parallel will be punished!§r")
    static Lang sinopeChemicalTooltip9;

    @Key("ctnh.multiblock.nano_generator.tooltip.0")
    @CN("利用摩擦热的力量")
    @EN("Utilize the power of friction heat")
    static Lang nanoGeneratorTooltip0;

    @Key("ctnh.multiblock.nano_generator.tooltip.1")
    @CN("最大并行数:2048")
    @EN("Maximum parallel count: 2048")
    static Lang nanoGeneratorTooltip1;

    @Key("ctnh.multiblock.nano_generator.tooltip.2")
    @CN("每有1并行数，总体发电量提升4%\n实际运行时间为配方时间*sqrt(并行数)")
    @EN("For each parallel process, total power generation increases by 4%\nActual operation time is recipe time * sqrt(parallel count)")
    static Lang nanoGeneratorTooltip2;

    @Key("ctnh.multiblock.nano_generator.tooltip.3")
    @CN("在控制器内放入特定材料可提升倍率，但也有概率消耗\n无材料：0.8倍率\n橡胶片：1.0倍率,并行数/512几率消耗\n聚乙烯片：1.6倍率，并行数/1024几率消耗\n硅橡胶片：2.4倍率，并行数/4096几率消耗\n聚四氟乙烯片：3.2倍率，并行数/65535几率消耗\n丁苯橡胶片：4.6倍率，并行数/131070几率消耗\n聚苯并咪唑片：5倍率，并行数/1048576几率消耗")
    @EN("Inserting specific materials into the controller increases the multiplier, but each material may also be consumed\nNo material: 0.8x multiplier\nRubber Sheet: 1.0x multiplier, consumption chance = parallel count / 512\nPolyethylene Sheet: 1.6x multiplier, consumption chance = parallel count / 1024\nSilicone Rubber Sheet: 2.4x multiplier, consumption chance = parallel count / 4096\nPTFE Sheet: 3.2x multiplier, consumption chance = parallel count / 65535\nStyrene-Butadiene Rubber Sheet: 4.6x multiplier, consumption chance = parallel count / 131070\nPolybenzimidazole Sheet: 5x multiplier, consumption chance = parallel count / 1048576")
    static Lang nanoGeneratorTooltip3;

    @Key("ctnh.multiblock.photovoltaic_power_station_energetic.tooltip.0")
    @CN("简易太阳能发电")
    @EN("Basic Solar Power Generation")
    static Lang photovoltaicPowerStationEnergeticTooltip0;

    @Key("ctnh.multiblock.photovoltaic_power_station_energetic.tooltip.1")
    @CN("§e基础产能功率：§r512 EU/t")
    @EN("§eBase Output:§r 512 EU/t")
    static Lang photovoltaicPowerStationEnergeticTooltip1;

    @Key("ctnh.multiblock.photovoltaic_power_station_energetic.tooltip.2")
    @CN("只在白天工作，不同维度会对太阳能发电的效率产生影响，基础产能功率为在主世界正午的功率")
    @EN("Operates only during daytime. Efficiency varies across dimensions. Base output reflects noon in the Overworld")
    static Lang photovoltaicPowerStationEnergeticTooltip2;

    @Key("ctnh.multiblock.photovoltaic_power_station_pulsating.tooltip.0")
    @CN("高效太阳能发电")
    @EN("Advanced Solar Power Generation")
    static Lang photovoltaicPowerStationPulsatingTooltip0;

    @Key("ctnh.multiblock.photovoltaic_power_station_pulsating.tooltip.1")
    @CN("§e基础产能功率：§r2048 EU/t")
    @EN("§eBase Output:§r 2048 EU/t")
    static Lang photovoltaicPowerStationPulsatingTooltip1;

    @Key("ctnh.multiblock.photovoltaic_power_station_pulsating.tooltip.2")
    @CN("只在白天工作，不同维度会对太阳能发电的效率产生影响，基础产能功率为在主世界正午的功率")
    @EN("Operates only during daytime. Efficiency varies across dimensions. Base output reflects noon in the Overworld")
    static Lang photovoltaicPowerStationPulsatingTooltip2;

    @Key("ctnh.multiblock.photovoltaic_power_station_vibrant.tooltip.0")
    @CN("究极太阳能发电")
    @EN("Ultimate Solar Power Generation")
    static Lang photovoltaicPowerStationVibrantTooltip0;

    @Key("ctnh.multiblock.photovoltaic_power_station_vibrant.tooltip.1")
    @CN("§e基础产能功率：§r8192 EU/t")
    @EN("§eBase Output:§r 8192 EU/t")
    static Lang photovoltaicPowerStationVibrantTooltip1;

    @Key("ctnh.multiblock.photovoltaic_power_station_vibrant.tooltip.2")
    @CN("只在白天工作，不同维度会对太阳能发电的效率产生影响，基础产能功率为在主世界正午的功率")
    @EN("Operates only during daytime. Efficiency varies across dimensions. Base output reflects noon in the Overworld")
    static Lang photovoltaicPowerStationVibrantTooltip2;

    @Key("ctnh.multiblock.ion_exchanger.tooltip.0")
    @CN("离子交换")
    @EN("Ion Exchange")
    static Lang ionExchangerTooltip0;

    @Key("ctnh.multiblock.coke_tower.tooltip.0")
    @CN("拥有强大的焦化产能来支撑你的木化产线！")
    @EN("Boasts formidable coking capacity to fuel your petrochemical production line!")
    static Lang cokeTowerTooltip0;

    @Key("ctnh.multiblock.coke_tower.tooltip.1")
    @CN("有着如同工业熔炉一般的速度")
    @EN("Delivers blistering speeds rivaling industrial furnaces")
    static Lang cokeTowerTooltip1;

    @Key("ctnh.multiblock.wide_accelerator.tooltip.0")
    @CN("粒子加速集成者")
    @EN("Particle Accelerator Integrator")
    static Lang wideAcceleratorTooltip0;

    @Key("ctnh.multiblock.wide_accelerator.tooltip.1")
    @CN("允许§9使用激光仓§r和§a变电仓§r")
    @EN("Allows the use of §9laser hatches§r and §avoltage converter hatches§r")
    static Lang wideAcceleratorTooltip1;

    @Key("ctnh.multiblock.wide_accelerator.tooltip.2")
    @CN("通过三个轨道加速三种粒子")
    @EN("Accelerates three types of particles through three beamlines")
    static Lang wideAcceleratorTooltip2;

    @Key("ctnh.multiblock.wide_accelerator.tooltip.3")
    @CN("本机器只要求粒子速度大于配方需求，不满足需求无法运行。粒子速度不会超过50Gev，这台机器非常安全，不会引发爆炸。")
    @EN("The machine only requires particle velocities to exceed the recipe requirements; it cannot run otherwise. Particle velocity is capped at 50 GeV, making the machine very safe and preventing explosions.")
    static Lang wideAcceleratorTooltip3;

    @Key("ctnh.multiblock.wide_accelerator.tooltip.4")
    @CN("允许使用§b并行控制仓§r，不适用并行控制仓则使用默认值")
    @EN("Allows the use of §bparallel control hatches§r; without one, the default value is used")
    static Lang wideAcceleratorTooltip4;

    @Key("ctnh.multiblock.wide_accelerator.tooltip.5")
    @CN("在运行一般配方时如无§b并行控制仓§r,默认使用16并行")
    @EN("When running normal recipes without a §bparallel control hatch§r, the default is 16 parallel processes")
    static Lang wideAcceleratorTooltip5;

    @Key("ctnh.multiblock.wide_accelerator.tooltip.6")
    @CN("如果粒子速度过慢，则什么都不会产生")
    @EN("Insufficient particle velocity yields no products")
    static Lang wideAcceleratorTooltip6;

    @Key("ctnh.multiblock.wide_accelerator.tooltip.7")
    @CN("本机器可以存储至多50E EU能量，存储的能量可以在加速界面调整三种粒子速度，每调整1M ev粒子速度需要消耗10M EU存储的能量，按住shift时可以一次性更改10M ev粒子速度，按住ctrl时可以一次性更改100M ev粒子速度")
    @EN("The machine can store up to 50E EU. Stored energy can be used to adjust the three particle velocities in the acceleration interface. Each 1 MeV increase in particle velocity consumes 10M EU from stored energy. Hold Shift to change velocity by 10 MeV at once; hold Ctrl to change it by 100 MeV at once.")
    static Lang wideAcceleratorTooltip7;

    @Key("ctnh.multiblock.wide_accelerator.tooltip.8")
    @CN("三种粒子轨道速度之和每有1M ev，每tick便消耗机器存储的100EU能量，电量不足时，每tick粒子速度会衰减10%")
    @EN("For every 1 MeV in the sum of the three particle velocities, the machine consumes 100 EU of stored energy per tick. If power is insufficient, particle velocity decays by 10% per tick.")
    static Lang wideAcceleratorTooltip8;

    @Key("ctnh.multiblock.wide_accelerator.tooltip.9")
    @CN("机器没有运行时，默认使用存储能源舱室的所有能源为机器充能。机器运行时仍然使用能源仓的能量。但在机器运行时除非机器存储电量不够，否则能源仓不会为机器充能")
    @EN("When the machine is idle, it uses all energy in the energy-storage hatches to charge itself by default. While running, it continues to use energy from the energy hatches. However, during operation, the energy hatches will not charge the machine unless its stored energy is insufficient.")
    static Lang wideAcceleratorTooltip9;

    @Key("ctnh.multiblock.wide_accelerator.tooltip.10")
    @CN("注意:本机器用电量极高，且暂时无法做到只能计算正确并行，使用低电压可能导致§c配方无法运行§r或者§c跳电§r,建议搭配§9激光仓§r使用，如遇配方不工作，请报告给作者")
    @EN("WARNING: Extreme power consumption. Improper voltage may cause §crecipe failure§r or §ccircuit tripping§r. Recommended with §9laser hatches§r. Reduce parallelism if malfunction occurs")
    static Lang wideAcceleratorTooltip10;

    @Key("ctnh.multiblock.wide_accelerator.tooltip.11")
    @CN("可以与约束器链接传递部分粒子。§c警告：如果没有链接约束器，不要随意尝试某些危险的配方§r (目前还是饼)")
    @EN("Particle transfer available via containment links. §cCAUTION: Hazardous recipes require pre-installed containment systems§r (Currently conceptual)")
    static Lang wideAcceleratorTooltip11;

    @Key("ctnh.multiblock.greenhouse.tooltip.0")
    @CN("室内种植")
    @EN("Plant In Room")
    static Lang greenhouseTooltip0;

    @Key("ctnhcore.src.sacrifice_empty")
    @CN("无牺牲者")
    @EN("No sacrifices found")
    static Lang ctnhSrcSacrificeEmpty;

    @Key("ctnhcore.src.sacrifice_locked")
    @CN("已锁定牺牲者！")
    @EN("Sacrifice LOCKED")
    static Lang ctnhSrcSacrificeLocked;

    @Key("ctnhcore.src.sacrifice_unlocked")
    @CN("无法锁定牺牲者")
    @EN("Sacrifice UNLOCKED")
    static Lang ctnhSrcSacrificeUnlocked;

    @Key("ctnhcore.src.wetware_duration")
    @CN("湿件剩余存活时间: %s ticks")
    @EN("Wetware duration: %s ticks")
    static Lang ctnhSrcWetwareDuration;

    @Key("ctnhcore.src.sacrifice")
    @CN("牺牲者: %s")
    @EN("Sacrifices: %s")
    static Lang ctnhSrcSacrifice;

    @Key("ctnh.multiblock.hyper_plasma_turbine.tooltip0")
    @CN("§a精密计算§f与§e等离子体§f的§5终极艺术")
    @EN("§5FINAL FANTASY §fof §a Precise Computation §f and §e Plasma Energy")
    static Lang hyperPlasmaTurbineTooltip0;

    @Key("ctnh.multiblock.hyper_plasma_turbine.tooltip1")
    @CN("提供%d算力以达到基础功率，每提供%d算力，输出功率翻一倍")
    @EN("Provide %d computation to reach the Base Production. For every %d computation provided, the Output Production DOUBLES.")
    static Lang hyperPlasmaTurbineTooltip1;

    @Key("ctnhcore.recipe_logic.insufficient_cwut")
    @CN("算力不足")
    @EN("Insufficient Computation")
    static Lang ctnhRecipeLogicInsufficientCwut;

    @Key("ctnhcore.machine.digital_miner.tooltip.0")
    @CN("§7来自GTMThings的挖矿黑科技，速度更快且无采矿管道，仅挖取矿石")
    @EN("§7From GTMThings, faster speed and no mining pipes, only mining ores")
    static Lang ctnhMachineDigitalMinerTooltip0;

    @Key("ctnhcore.machine.digital_miner.tooltip.1")
    @CN("§b工作时自带自身区块强加载")
    @EN("§bForce loading self chunk while working")
    static Lang ctnhMachineDigitalMinerTooltip1;

    @Key("ctnhcore.machine.digital_miner.tooltip.2")
    @CN("工作时消耗§f%d EU/t§7，每个方块需要§f%d§7刻")
    @EN("Uses §f%d EU/t §7while working, each block takes §f%d§7 ticks")
    static Lang ctnhMachineDigitalMinerTooltip2;

    @Key("ctnh.multiblock.wind_array.tooltip0")
    @CN("§7§o风力狼群:真正的自然之力")
    @EN("§7§oWind Wolf: The TRUE POWER of NATURE")
    static Lang windArrayTooltip0;

    @Key("ctnh.multiblock.wind_array.tooltip1")
    @CN("§8---------------§a基础数据§8-----------------")
    @EN("§8-----------------§aBasic Data§8--------------------")
    static Lang windArrayTooltip1;

    @Key("ctnh.multiblock.wind_array.tooltip2")
    @CN("§f- 基础发电功率: §e%d EU/t  §7(地球)")
    @EN("§f- Basic Production: §e%d EU/t  §7(on earth)")
    static Lang windArrayTooltip2;

    @Key("ctnh.multiblock.wind_array.tooltip3")
    @CN("§f- 天气风力增益: §e雨天x2,雷雨x4")
    @EN("§f- Weather Boost: §eRainy x2, Thunder x4")
    static Lang windArrayTooltip3;

    @Key("ctnh.multiblock.wind_array.tooltip4")
    @CN("§f- 高度增益: §e Clamp(Y-64, 0, 256-64) / (256-64)")
    @EN("§f- Altitude Boost: §eClamp(Y-64, 0, 256-64) / (256-64)")
    static Lang windArrayTooltip4;

    @Key("ctnh.multiblock.wind_array.tooltip5")
    @CN("§f- 网络增益: §e 0.3*[log2(网络大小)]")
    @EN("§f- Network Boost: §e 0.3*[log2(NetSize)]")
    static Lang windArrayTooltip5;

    @Key("ctnh.multiblock.wind_array.tooltip6")
    @CN("§f增益乘算得到发电效率")
    @EN("§fAll boosts are multiplied to determine production.")
    static Lang windArrayTooltip6;

    @Key("ctnh.multiblock.wind_array.tooltip7")
    @CN("§f需要§e%d mB/s§f 润滑油以维护机器运行")
    @EN("§fRequires §e%d mB/s§f Lubricant.")
    static Lang windArrayTooltip7;

    @Key("ctnh.multiblock.wind_array.tooltip8")
    @CN("§8---------------§a风力网络§8-----------------")
    @EN("§8-----------------§aWind Network§8------------------")
    static Lang windArrayTooltip8;

    @Key("ctnh.multiblock.wind_array.tooltip9")
    @CN("§f所有结构对齐且间距<=1的风力发电机阵列会组成风力网络")
    @EN("§fAligned structure within a distance of <= 1 form a Wind Network.")
    static Lang windArrayTooltip9;

    @Key("ctnh.multiblock.wind_array.tooltip10")
    @CN("§f润滑油会从风力网络中抽取.")
    @EN("§fLubricant will be extracted from the network.")
    static Lang windArrayTooltip10;

    @Key("ctnh.multiblock.wind_array.tooltip11")
    @CN("§5顺应风力网络的工作规律,以抵挡自然之力的摧残")
    @EN("§5DO OBEY the rules to resist the DESTRUCTION from nature force.")
    static Lang windArrayTooltip11;

    @Key("config.jade.plugin_ctnhcore.thread_status_provider")
    @CN("线程信息")
    @EN("Thread Info")
    static Lang configJadePluginCtnhcoreThreadStatusProvider;

    @Key("config.jade.plugin_ctnhcore.recipe_logic_provider")
    @CN("配方耗电信息")
    @EN("Recipe Logic Info")
    static Lang configJadePluginCtnhcoreRecipeLogicProvider;

    @Key("config.jade.plugin_ctnhcore.recipe_output_provider")
    @CN("配方输出信息")
    @EN("Recipe Output Info")
    static Lang configJadePluginCtnhcoreRecipeOutputProvider;

    @Key("gui.ctnh.neutron_sensor.invert.enabled")
    @CN("输出：反转\n\n切换以反转红石逻辑\n默认情况下，中子动能介于所设定的最小值和最大值之间时传感器将发出红石信号，小于最小值时则停止发出红石信号")
    @EN("Output: Reverse\n\nSwitch to reverse redstone logic\nBy default, the sensor will emit a redstone signal when the neutron kinetic energy is between the set minimum and maximum values, and stop emitting a redstone signal when it is less than the minimum value.")
    static Lang guiNeutronSensorInvertEnabled;

    @Key("gui.ctnh.neutron_sensor.invert.disabled")
    @CN("输出：正常\n\n切换以反转红石逻辑\n默认情况下，中子动能介于所设定的最小值和最大值之间时传感器将发出红石信号，小于最小值时则停止发出红石信号")
    @EN("Output: Normal\n\nSwitch to reverse redstone logic\nBy default, the sensor will emit a redstone signal when the neutron kinetic energy is between the set minimum and maximum values, and stop emitting a redstone signal when it is less than the minimum value.")
    static Lang guiNeutronSensorInvertDisabled;

    @Key("ctnh.machine.naquadah_reactor.tooltip")
    @CN("效率: %s%%")
    @EN("Efficiency: %s%%")
    static Lang machineNaquadahReactorTooltip;

    @Key("ctnh.machine.rocket_engine.tooltip")
    @CN("效率: %s%%")
    @EN("Efficiency: %s%%")
    static Lang machineRocketEngineTooltip;

    @Key("ctnhcore.machine.oxygen_required")
    @CN("此机器需要在可供氧环境中运行")
    @EN("This machine requires a breathable atmosphere")
    static Lang ctnhMachineOxygenRequired;

    @Key("ctnhcore.machine.oxygen_enricher.no_input")
    @CN("氧气供应不足")
    @EN("Insufficient oxygen supply")
    static Lang ctnhMachineOxygenEnricherNoInput;

    @Key("ctnhcore.machine.oxygen_enricher.tooltip.0")
    @CN("消耗氧气流体以维持密闭空间内的可呼吸环境")
    @EN("Consumes oxygen fluid to maintain a breathable sealed room")
    static Lang ctnhMachineOxygenEnricherTooltip0;

    @Key("ctnhcore.machine.oxygen_enricher.tooltip.1")
    @CN("可为半径 %s 格的密闭空间供氧")
    @EN("Supplies oxygen to enclosed spaces within %s blocks")
    static Lang ctnhMachineOxygenEnricherTooltip1;

    @Key("enchantment.ctnhcore.warming.desc")
    @CN("增强御暑能力")
    @EN("Improves resistance to heat")
    static Lang enchantmentWarmingDesc;

    @Key("enchantment.ctnhcore.cooling.desc")
    @CN("增强御寒能力")
    @EN("Improves resistance to cold")
    static Lang enchantmentCoolingDesc;

    @Key("message.ctnhcore.portal.invalid_dimension")
    @CN("该传送门只能在主世界使用")
    @EN("This portal can only be used in the Overworld")
    static Lang messagePortalInvalidDimension;

    @Key("block.gtceu.starlight")
    @CN("星能液")
    @EN("Starlight Fluid")
    static Lang blockGtceuStarlight;

    @Key("block.gtceu.sulfuric_acid")
    @CN("硫酸")
    @EN("Sulfuric Acid")
    static Lang blockGtceuSulfuricAcid;

    @Key("gtceu.recipe_logic.setup_fail")
    @CN("配方启动失败：")
    @EN("Recipe failed to start:")
    static Lang gtceuRecipeLogicSetupFail;

    @Key("gtceu.recipe_logic.recipe_waiting")
    @CN("配方等待中：")
    @EN("Recipe waiting:")
    static Lang gtceuRecipeLogicRecipeWaiting;

    @Key("gtceu.recipe_modifier.default_fail")
    @CN("配方修改失败")
    @EN("Recipe modification failed")
    static Lang gtceuRecipeModifierDefaultFail;

    @Key("gtceu.recipe_modifier.insufficient_voltage")
    @CN("电压等级过低！")
    @EN("Voltage tier too low!")
    static Lang gtceuRecipeModifierInsufficientVoltage;

    @Key("gtceu.recipe_modifier.insufficient_eu_to_start_fusion")
    @CN("缺少足够能量以启动核聚变反应")
    @EN("Not enough energy to start the fusion reaction")
    static Lang gtceuRecipeModifierInsufficientEuToStartFusion;

    @Key("gtceu.recipe_modifier.coil_temperature_too_low")
    @CN("线圈温度过低！")
    @EN("Coil temperature too low!")
    static Lang gtceuRecipeModifierCoilTemperatureTooLow;

    @Key("config.ctnhcore.option.ftbPlugin")
    @CN("FTB相关")
    @EN("FTB Options")
    static Lang configOptionFtbplugin;

    @Key("config.ctnhcore.option.kinetic")
    @CN("应力相关")
    @EN("Kinetic Options")
    static Lang configOptionKinetic;

    @Key("config.ctnhcore.option.enableFTBUltimineOnGTOres")
    @CN("开启GT矿物连锁")
    @EN("Enable GT ore vein mining")
    static Lang configOptionEnableftbultimineongtores;

    @Key("config.ctnhcore.option.pressorRpmRequirement")
    @CN("机械辊压厂最低转速需求")
    @EN("Mechanical Pressor minimum RPM")
    static Lang configOptionPressorrpmrequirement;

    @Key("config.ctnhcore.option.pressorSpeedMultiplier")
    @CN("机械辊压厂加速倍率")
    @EN("Mechanical Pressor speed multiplier")
    static Lang configOptionPressorspeedmultiplier;

    @Key("config.ctnhcore.option.pressorStressRequirement")
    @CN("机械辊压厂应力消耗")
    @EN("Mechanical Pressor stress consumption")
    static Lang configOptionPressorstressrequirement;

    @Key("config.ctnhcore.option.mixerRpmRequirement")
    @CN("机械搅拌厂最低转速需求")
    @EN("Mechanical Mixer minimum RPM")
    static Lang configOptionMixerrpmrequirement;

    @Key("config.ctnhcore.option.mixerSpeedMultiplier")
    @CN("机械搅拌厂加速倍率")
    @EN("Mechanical Mixer speed multiplier")
    static Lang configOptionMixerspeedmultiplier;

    @Key("config.ctnhcore.option.mixerStressRequirement")
    @CN("机械搅拌厂应力消耗")
    @EN("Mechanical Mixer stress consumption")
    static Lang configOptionMixerstressrequirement;

    @Key("config.ctnhcore.option.centrifugeRpmRequirement")
    @CN("机械离心厂最低转速需求")
    @EN("Mechanical Centrifuge minimum RPM")
    static Lang configOptionCentrifugerpmrequirement;

    @Key("config.ctnhcore.option.centrifugeSpeedMultiplier")
    @CN("机械离心厂加速倍率")
    @EN("Mechanical Centrifuge speed multiplier")
    static Lang configOptionCentrifugespeedmultiplier;

    @Key("config.ctnhcore.option.centrifugeStressRequirement")
    @CN("机械离心厂应力消耗")
    @EN("Mechanical Centrifuge stress consumption")
    static Lang configOptionCentrifugestressrequirement;

    @Key("config.ctnhcore.option.sifterRpmRequirement")
    @CN("机械筛选厂最低转速需求")
    @EN("Mechanical Sifter minimum RPM")
    static Lang configOptionSifterrpmrequirement;

    @Key("config.ctnhcore.option.sifterSpeedMultiplier")
    @CN("机械筛选厂加速倍率")
    @EN("Mechanical Sifter speed multiplier")
    static Lang configOptionSifterspeedmultiplier;

    @Key("config.ctnhcore.option.sifterStressRequirement")
    @CN("机械筛选厂应力消耗")
    @EN("Mechanical Sifter stress consumption")
    static Lang configOptionSifterstressrequirement;

    @Key("config.ctnhcore.option.extractorRpmRequirement")
    @CN("机械提取厂最低转速需求")
    @EN("Mechanical Extractor minimum RPM")
    static Lang configOptionExtractorrpmrequirement;

    @Key("config.ctnhcore.option.extractorSpeedMultiplier")
    @CN("机械提取厂加速倍率")
    @EN("Mechanical Extractor speed multiplier")
    static Lang configOptionExtractorspeedmultiplier;

    @Key("config.ctnhcore.option.extractorStressRequirement")
    @CN("机械提取厂应力消耗")
    @EN("Mechanical Extractor stress consumption")
    static Lang configOptionExtractorstressrequirement;

    @Key("config.ctnhcore.option.latheRpmRequirement")
    @CN("机械车床厂最低转速需求")
    @EN("Mechanical Lathe minimum RPM")
    static Lang configOptionLatherpmrequirement;

    @Key("config.ctnhcore.option.latheSpeedMultiplier")
    @CN("机械车床厂加速倍率")
    @EN("Mechanical Lathe speed multiplier")
    static Lang configOptionLathespeedmultiplier;

    @Key("config.ctnhcore.option.latheStressRequirement")
    @CN("机械车床厂应力消耗")
    @EN("Mechanical Lathe stress consumption")
    static Lang configOptionLathestressrequirement;

    @Key("config.ctnhcore.option.laserRpmRequirement")
    @CN("机械激光厂最低转速需求")
    @EN("Mechanical Laser minimum RPM")
    static Lang configOptionLaserrpmrequirement;

    @Key("config.ctnhcore.option.laserSpeedMultiplier")
    @CN("机械激光厂加速倍率")
    @EN("Mechanical Laser speed multiplier")
    static Lang configOptionLaserspeedmultiplier;

    @Key("config.ctnhcore.option.laserStressRequirement")
    @CN("机械激光厂应力消耗")
    @EN("Mechanical Laser stress consumption")
    static Lang configOptionLaserstressrequirement;

    @Key("item.ctnh.me_advanced_terminal.setting.title")
    @CN("多方块结构成型配置")
    @EN("Multiblock Formation Settings")
    static Lang itemMeAdvancedTerminalSettingTitle;

    @Key("item.ctnh.me_advanced_terminal.setting.1")
    @CN("线圈等级")
    @EN("Coil Tier")
    static Lang itemMeAdvancedTerminalSetting1;

    @Key("item.ctnh.me_advanced_terminal.setting.1.tooltip")
    @CN("设置自动放置的线圈等级(0:不指定等级)\n设置后会忽略结构本身的要求")
    @EN("Set the tier of coils placed automatically (0: no tier specified)\nThe structure's own requirements are ignored after setting this")
    static Lang itemMeAdvancedTerminalSetting1Tooltip;

    @Key("item.ctnh.me_advanced_terminal.setting.2")
    @CN("重复结构次数")
    @EN("Repeated Structure Count")
    static Lang itemMeAdvancedTerminalSetting2;

    @Key("item.ctnh.me_advanced_terminal.setting.2.tooltip")
    @CN("设置可重复结构(蒸馏塔、装配线等)的重复部分放置次数\n对超净间无效")
    @EN("Set the number of repeated sections placed for repeatable structures (Distillation Towers, Assembly Lines, etc.)\nDoes not apply to Cleanrooms")
    static Lang itemMeAdvancedTerminalSetting2Tooltip;

    @Key("item.ctnh.me_advanced_terminal.setting.10")
    @CN("工业血之祭坛结构等级")
    @EN("Industrial Blood Altar Tier")
    static Lang itemMeAdvancedTerminalSetting10;

    @Key("item.ctnh.me_advanced_terminal.setting.10.tooltip")
    @CN("仅对工业血之祭坛自动成型生效\n设置要构建的工业血之祭坛等级(0-4)\n0 对应二阶，4 对应六阶")
    @EN("Only applies to automatic formation of Industrial Blood Altars\nSet the Industrial Blood Altar tier to build (0-4)\n0 corresponds to Tier II and 4 corresponds to Tier VI")
    static Lang itemMeAdvancedTerminalSetting10Tooltip;

    @Key("item.ctnh.me_advanced_terminal.setting.3")
    @CN("无仓室模式")
    @EN("Hatchless Mode")
    static Lang itemMeAdvancedTerminalSetting3;

    @Key("item.ctnh.me_advanced_terminal.setting.3.tooltip")
    @CN("是否启用无仓室模式(0:不启用,1:启用)\n启用无仓室模式后不会放置任何仓室")
    @EN("Enable hatchless mode (0: disabled, 1: enabled)\nWhen enabled, no hatches will be placed")
    static Lang itemMeAdvancedTerminalSetting3Tooltip;

    @Key("item.ctnh.me_advanced_terminal.setting.4")
    @CN("线圈替换模式")
    @EN("Coil Replacement Mode")
    static Lang itemMeAdvancedTerminalSetting4;

    @Key("item.ctnh.me_advanced_terminal.setting.4.tooltip")
    @CN("是否启用线圈替换模式(0:不启用,1:启用)\n启用线圈替换模式会将所有线圈替换为指定等级的线圈\n请确保物品栏中有空间存放替换下来的线圈")
    @EN("Enable coil replacement mode (0: disabled, 1: enabled)\nWhen enabled, all coils are replaced with the specified tier\nMake sure the inventory has room for the replaced coils")
    static Lang itemMeAdvancedTerminalSetting4Tooltip;

    @Key("item.ctnh.me_advanced_terminal.setting.5")
    @CN("使用AE存储")
    @EN("Use AE Storage")
    static Lang itemMeAdvancedTerminalSetting5;

    @Key("item.ctnh.me_advanced_terminal.setting.5.tooltip")
    @CN("是否启用AE库存(0:不启用,1:启用)\n启用后将优先在AE库存中检索\n通过ME无线访问点链接到网络")
    @EN("Enable AE storage (0: disabled, 1: enabled)\nWhen enabled, the AE storage is searched first\nConnect to the network through an ME Wireless Access Point")
    static Lang itemMeAdvancedTerminalSetting5Tooltip;

    @Key("item.ctnh.me_advanced_terminal.setting.6")
    @CN("放置流体")
    @EN("Place Fluids")
    static Lang itemMeAdvancedTerminalSetting6;

    @Key("item.ctnh.me_advanced_terminal.setting.6.tooltip")
    @CN("是否启用流体放置(0:不启用,1:启用)\n启用后将检索并消耗物品栏/背包流体容器中的或AE库存中的流体\n可堆叠的流体容器须保证堆叠数为1")
    @EN("Enable fluid placement (0: disabled, 1: enabled)\nWhen enabled, fluids are searched for and consumed from fluid containers in the inventory/backpack or AE storage\nStackable fluid containers must have a stack size of 1")
    static Lang itemMeAdvancedTerminalSetting6Tooltip;

    @Key("item.ctnh.me_advanced_terminal.setting.7")
    @CN("在流体中放置")
    @EN("Place in Fluids")
    static Lang itemMeAdvancedTerminalSetting7;

    @Key("item.ctnh.me_advanced_terminal.setting.7.tooltip")
    @CN("是否在流体中放置方块(0:不启用,1:启用)\n启用后会将空间中的流体视为空位\n与“放置流体”同时启用时，不会在流体中放置流体")
    @EN("Place blocks in fluids (0: disabled, 1: enabled)\nWhen enabled, fluids in the space are treated as empty space\nWhen enabled together with Place Fluids, fluids will not be placed in fluids")
    static Lang itemMeAdvancedTerminalSetting7Tooltip;

    @Key("item.ctnh.me_advanced_terminal.setting.8")
    @CN("拆除模式")
    @EN("Removal Mode")
    static Lang itemMeAdvancedTerminalSetting8;

    @Key("item.ctnh.me_advanced_terminal.setting.8.tooltip")
    @CN("是否启用拆除模式(0:不启用,1:启用)\n请确保物品栏中有空间存放拆除的方块\n与“使用AE存储”同时启用时，拆除的方块会自动存入AE存储")
    @EN("Enable removal mode (0: disabled, 1: enabled)\nMake sure the inventory has room for removed blocks\nWhen enabled together with AE Storage, removed blocks are automatically stored in AE storage")
    static Lang itemMeAdvancedTerminalSetting8Tooltip;

    @Key("item.ctnh.me_advanced_terminal.setting.9")
    @CN("多方块成型配置")
    @EN("Multiblock Formation Configuration")
    static Lang itemMeAdvancedTerminalSetting9;

    @Key("item.ctnh.me_advanced_terminal.setting.9.tooltip")
    @CN("多方块成型配置")
    @EN("Multiblock formation configuration")
    static Lang itemMeAdvancedTerminalSetting9Tooltip;

    @Key("ctnh.item.runes.starlight_rune")
    @CN("Per Aspera Ad Astra")
    @EN("Per Aspera Ad Astra")
    static Lang itemRunesStarlightRune;

    @Key("ctnh.item.runes.twist_rune")
    @CN("速度与人性的扭曲")
    @EN("The Distortion of Speed and Humanity")
    static Lang itemRunesTwistRune;

    @Key("ctnh.item.runes.proliferation_rune")
    @CN("金融与生物的本能")
    @EN("Finance and Biological Instinct")
    static Lang itemRunesProliferationRune;

    @Key("ctnh.item.runes.quasar_rune")
    @CN("毁灭与创造交替")
    @EN("The Alternation of Destruction and Creation")
    static Lang itemRunesQuasarRune;

    @Key("ctnh.item.runes.horizen_rune")
    @CN("视野所向之处")
    @EN("As Far as the Eye Can See")
    static Lang itemRunesHorizenRune;

    @Key("ctnh.item.drone_tier")
    @CN("无人机等级：%d")
    @EN("Drone Tier: %d")
    static Lang itemDroneTier;

    @Key("ctnh.item.drone_eut")
    @CN("单个无人机产生的电压: %dEU/t")
    @EN("Voltage generated by one drone: %d EU/t")
    static Lang itemDroneEut;

    @Key("ctnh.item.drone_durability")
    @CN("无人机最大耐久:%d")
    @EN("Maximum Drone Durability: %d")
    static Lang itemDroneDurability;

    @Key("ctnh.item.dyson_tier1")
    @CN("集成性光伏无人机蜂群")
    @EN("Integrated Photovoltaic Drone Swarm")
    static Lang itemDysonTier1;

    @Key("ctnh.item.dyson_tier2")
    @CN("§4我们的蜂群遮天蔽日")
    @EN("§4Our swarm blocks out the sky")
    static Lang itemDysonTier2;

    @Key("ctnh.item.nuclear_reactor_heat")
    @CN("基础堆温：%d°C")
    @EN("Base Reactor Temperature: %d°C")
    static Lang itemNuclearReactorHeat;

    @Key("ctnh.item.terminal.success_get")
    @CN("已经获取坐标!")
    @EN("Coordinates acquired!")
    static Lang itemTerminalSuccessGet;

    @Key("ctnh.item.terminal.success_write")
    @CN("已成功写入坐标!")
    @EN("Coordinates written successfully!")
    static Lang itemTerminalSuccessWrite;

    @Key("ctnh.item.terminal.location")
    @CN("已经绑定的坐标：(%s,%s,%s)")
    @EN("Bound coordinates: (%s,%s,%s)")
    static Lang itemTerminalLocation;

    @Key("ctnh.item.terminal.success_clear")
    @CN("已清除坐标！")
    @EN("Coordinates cleared!")
    static Lang itemTerminalSuccessClear;

    @Key("ctnh.item.terminal.tips")
    @CN("使用右键绑定光伏模块控制器，然后再右键将控制器和光伏基站绑定\nshift+右键任意方块清除坐标")
    @EN("Right-click a photovoltaic module controller to bind it, then right-click again to bind the controller to the photovoltaic station\nShift+right-click any block to clear the coordinates")
    static Lang itemTerminalTips;

    @Key("ctnh.simple_nutritious_meal.tooltip.1")
    @CN("能维持机体基本的生理功能")
    @EN("Maintains the body's basic physiological functions")
    static Lang simpleNutritiousMealTooltip1;

    @Key("item.ctnh.ecological_star.desc")
    @CN("蕴含生态圈的所有精华")
    @EN("Contains all the essence of an ecosystem")
    static Lang itemEcologicalStarDesc;

    @Key("item.sculk_cell.desc")
    @CN("分化....")
    @EN("Differentiation....")
    static Lang itemSculkCellDesc;

    @Key("ctnh.multiblock.lasersorter.recipe.cwut")
    @CN("所需的基础算力：%d")
    @EN("Required base computation: %d")
    static Lang lasersorterRecipeCwut;

    @Key("ctnh.item.data.tip1")
    @CN("当前公式: a%s+b%s+c%s+d")
    @EN("Current formula: a%s+b%s+c%s+d")
    static Lang itemDataTip1;

    @Key("ctnh.item.data.tip2")
    @CN("获取的倍率: %s")
    @EN("Obtained multiplier: %s")
    static Lang itemDataTip2;

    @Key("ctnh.neuro_matrix_compiler.info.part_states")
    @CN("片区%d状态:%s")
    @EN("Region %d status: %s")
    static Lang neuroMatrixCompilerInfoPartStates;

    @Key("ctnh.neuro_matrix_compiler.info.state.idle")
    @CN("§6待机")
    @EN("§6Idle")
    static Lang neuroMatrixCompilerInfoStateIdle;

    @Key("ctnh.neuro_matrix_compiler.info.state.error")
    @CN("§c错误,类型：%s")
    @EN("§cError, type: %s")
    static Lang neuroMatrixCompilerInfoStateError;

    @Key("ctnh.neuro_matrix_compiler.info.state.working")
    @CN("§9运行中:%ss/%ss")
    @EN("§9Working: %ss/%ss")
    static Lang neuroMatrixCompilerInfoStateWorking;

    @Key("ctnh.neuro_matrix_compiler.info.state.finish")
    @CN("§a完成")
    @EN("§aComplete")
    static Lang neuroMatrixCompilerInfoStateFinish;

    @Key("ctnh.neuro_matrix_compiler.info.state.waiting")
    @CN("§b等待中......")
    @EN("§bWaiting......")
    static Lang neuroMatrixCompilerInfoStateWaiting;

    @Key("ctnh.compiler.id")
    @CN("该舱室被分配到的片区编号:%s")
    @EN("Assigned region ID: %s")
    static Lang compilerId;

    @Key("ctnh.compiler.noid")
    @CN("§c当前舱室尚未连接到主机！")
    @EN("§cThis chamber is not connected to the host!")
    static Lang compilerNoid;

    @Key("ctnh.multiblock.industrial_primitive_blast_furnace.info.parallel_count")
    @CN("并行数：%d")
    @EN("Parallel count: %d")
    static Lang industrialPrimitiveBlastFurnaceInfoParallelCount;

    @Key("ctnh.spacephotovoltaicbasestation.jei.error.pv_block")
    @CN("§c必须使用同种光伏方块")
    @EN("§cAll photovoltaic blocks must be the same type")
    static Lang spacephotovoltaicbasestationJeiErrorPvBlock;

    @Key("ctnh.spacephotovoltaicbasestation.recipe.pvc_tier")
    @CN("需求光伏方块等级: %d")
    @EN("Required photovoltaic block tier: %d")
    static Lang spacephotovoltaicbasestationRecipePvcTier;

    @Key("ctnh.spacephotovoltaicbasestation.recipe.eut_model")
    @CN("模拟电压消耗: %d EUt")
    @EN("Simulated voltage consumption: %d EUt")
    static Lang spacephotovoltaicbasestationRecipeEutModel;

    @Key("ctnh.spacephotovoltaicbasestation.info.pvc_tier.0")
    @CN("当前光伏方块等级:%d")
    @EN("Current photovoltaic block tier: %d")
    static Lang spacephotovoltaicbasestationInfoPvcTier0;

    @Key("ctnh.spacephotovoltaicbasestation.info.pvc_tier.1")
    @CN("当前结构耐热等级:%d")
    @EN("Current structure heat-resistance tier: %d")
    static Lang spacephotovoltaicbasestationInfoPvcTier1;

    @Key("ctnh.spacephotovoltaicbasestation.info.pvc_tier.2")
    @CN("当前结构发电量:%.2f")
    @EN("Current power generation: %.2f")
    static Lang spacephotovoltaicbasestationInfoPvcTier2;

    @Key("ctnh.spacephotovoltaicbasestation.info.pvc_tier.3")
    @CN("当前维度光倍率:%d")
    @EN("Current dimensional light multiplier: %d")
    static Lang spacephotovoltaicbasestationInfoPvcTier3;

    @Key("ctnh.multiblock.cryotheum_freezer.ui.0")
    @CN("当前泪之晶点数:%d")
    @EN("Current Tear Crystal points: %d")
    static Lang cryotheumFreezerUi0;

    @Key("ctnh.multiblock.cryotheum_freezer.ui.1")
    @CN("当前冷冻机加速：%.2f / %.2f")
    @EN("Current freezer speed: %.2f / %.2f")
    static Lang cryotheumFreezerUi1;

    @Key("ctnh.multiblock.cryotheum_freezer.ui.2")
    @CN("打开升级面板")
    @EN("Open Upgrade Panel")
    static Lang cryotheumFreezerUi2;

    @Key("ctnh.multiblock.cryotheum_freezer.ui.3")
    @CN("当前能量利用效率：%.2f / %.2f")
    @EN("Current energy efficiency: %.2f / %.2f")
    static Lang cryotheumFreezerUi3;

    @Key("ctnh.multiblock.cryotheum_freezer.ui.4")
    @CN("当前并行：%d / %d")
    @EN("Current parallelism: %d / %d")
    static Lang cryotheumFreezerUi4;

    @Key("ctnh.multiblock.cryotheum_freezer.ui.5")
    @CN("§b当前消耗的凛冰:%d / %d")
    @EN("§bCurrent Cryotheum consumption: %d / %d")
    static Lang cryotheumFreezerUi5;

    @Key("gtceu.machine.parallel_hatch_mk9.tooltip")
    @CN("允许同时处理至多1024个配方。")
    @EN("Allows up to 1024 recipes to be processed simultaneously.")
    static Lang gtceuMachineParallelHatchMk9Tooltip;

    @Key("gtceu.machine.parallel_hatch_mk10.tooltip")
    @CN("允许同时处理至多4096个配方。")
    @EN("Allows up to 4096 recipes to be processed simultaneously.")
    static Lang gtceuMachineParallelHatchMk10Tooltip;

    @Key("gtceu.machine.parallel_hatch_mk11.tooltip")
    @CN("允许同时处理至多16384个配方。")
    @EN("Allows up to 16384 recipes to be processed simultaneously.")
    static Lang gtceuMachineParallelHatchMk11Tooltip;

    @Key("gtceu.machine.parallel_hatch_mk12.tooltip")
    @CN("允许同时处理至多65536个配方。")
    @EN("Allows up to 65536 recipes to be processed simultaneously.")
    static Lang gtceuMachineParallelHatchMk12Tooltip;

    @Key("gtceu.machine.parallel_hatch_mk13.tooltip")
    @CN("允许同时处理至多262144个配方。")
    @EN("Allows up to 262144 recipes to be processed simultaneously.")
    static Lang gtceuMachineParallelHatchMk13Tooltip;

    @Key("gtceu.machine.parallel_hatch_mk14.tooltip")
    @CN("允许同时处理至多1048576个配方。")
    @EN("Allows up to 1048576 recipes to be processed simultaneously.")
    static Lang gtceuMachineParallelHatchMk14Tooltip;

    @Key("block.ctnhcore.luv_compressed_fusion_reactor")
    @CN("压缩核聚变反应堆控制电脑 MK-I")
    @EN("Compressed Fusion Reactor Computer MK-I")
    static Lang blockLuvCompressedFusionReactor;

    @Key("block.ctnhcore.zpm_compressed_fusion_reactor")
    @CN("压缩核聚变反应堆控制电脑 MK-II")
    @EN("Compressed Fusion Reactor Computer MK-II")
    static Lang blockZpmCompressedFusionReactor;

    @Key("block.ctnhcore.uv_compressed_fusion_reactor")
    @CN("压缩核聚变反应堆控制电脑 MK-III")
    @EN("Compressed Fusion Reactor Computer MK-III")
    static Lang blockUvCompressedFusionReactor;

    @Key("ctnh.recipe_type.list")
    @CN("%s, %s")
    @EN("%s, %s")
    static Lang recipeTypeList;

    @Key("ctnh.multiblock.sweat_shop.tooltip.8")
    @CN("每5秒机器会消耗(员工数量)份简易营养餐")
    @EN("Every 5 seconds, the machine consumes (number of employees) servings of Simple Worker Meals.")
    static Lang sweatShopTooltip8;

    @Key("ctnh.multiblock.demon_will_generator.tooltip.0")
    @CN("借用恶魔之力")
    @EN("Harnessing Demonic Power")
    static Lang demonWillGeneratorTooltip0;

    @Key("ctnh.multiblock.demon_will_generator.tooltip.01")
    @CN("允许使用变电仓，不限制变电仓数量")
    @EN("Allows voltage converter hatches; there is no limit on the number of converter hatches")
    static Lang demonWillGeneratorTooltip01;

    @Key("ctnh.multiblock.demon_will_generator.tooltip.1")
    @CN("利用机器两侧的区块内的恶魔意志浓度差发电，浓度差与发电量呈指数关系，当浓度差超过500时，超过500的浓度与发电量改为线性关系")
    @EN("Generates power from the difference in Demonic Will concentration between the chunks on both sides of the machine. The difference increases power output exponentially up to 500; above 500, the excess contributes linearly.")
    static Lang demonWillGeneratorTooltip1;

    @Key("ctnh.multiblock.demon_will_generator.tooltip.2")
    @CN("以机器两侧的恶魔合金方块处的意志浓度为基准进行计算")
    @EN("Calculations use the Will concentration at the Demonic Alloy blocks on both sides of the machine.")
    static Lang demonWillGeneratorTooltip2;

    @Key("ctnh.multiblock.demon_will_generator.tooltip.3")
    @CN("两侧区块中的各种恶魔意志的多样性会影响发电效率")
    @EN("The diversity of Demonic Wills in both chunks affects generation efficiency.")
    static Lang demonWillGeneratorTooltip3;

    @Key("ctnh.multiblock.demon_will_generator.tooltip.4")
    @CN("机器内可以放入意志核心，将机器转化为对于某种意志专精的模式，该模式下每秒会有5%的概率消耗一个核心")
    @EN("Will Cores can be inserted to specialize the machine in a type of Will; this mode has a 5% chance per second to consume one core.")
    static Lang demonWillGeneratorTooltip4;

    @Key("ctnh.multiblock.demon_will_generator.tooltip.5")
    @CN("机器内的符文方块可替换，从而起到不同的增益效果:\n§4献祭符文和牺牲符文----提高生命源质强化模式的发电倍率§r\n§3速度符文----提升一次配方运行的时长（节省恶魔意志消耗）§r\n§e增容符文----每一个符文增加2点恶魔意志浓度差§r\n§c超容符文----每一个符文增加百分之2的恶魔意志浓度差（叠乘）§r\n==============================")
    @EN("The rune blocks inside the machine can be replaced for different bonuses:\n§4Sacrifice and Self-Sacrifice Runes ---- Increase the power multiplier of Life Essence Fortified Mode§r\n§3Speed Runes ---- Increase the duration of one recipe operation (reducing Demonic Will consumption)§r\n§eCapacity Runes ---- Each rune adds 2 to the Demonic Will concentration difference§r\n§cOvercapacity Runes ---- Each rune adds 2% to the Demonic Will concentration difference (multiplicative)§r\n==============================")
    static Lang demonWillGeneratorTooltip5;

    @Key("ctnh.multiblock.demon_will_generator.tooltip.6")
    @CN("输入§4生命源质§r开启血祭模式，发电量翻倍，每秒消耗§a100mb§r的生命源质")
    @EN("Insert §4Life Essence§r to activate Blood Sacrifice Mode, doubling power output while consuming §a100 mB§r of Life Essence per second")
    static Lang demonWillGeneratorTooltip6;

    @Key("ctnh.multiblock.void_miner.tooltip.0")
    @CN("取天材，掘地精")
    @EN("Harvesting heaven's materials, digging the earth's essence")
    static Lang voidMinerTooltip0;

    @Key("ctnh.multiblock.void_miner.tooltip.1")
    @CN("虚空采矿场自动生成并提取矿石")
    @EN("The Void Miner automatically generates and extracts ores")
    static Lang voidMinerTooltip1;

    @Key("ctnh.multiblock.void_miner.tooltip.2")
    @CN("如果你对矿物需求极大，虚空采矿机是必不可少的帮手")
    @EN("If you have a huge demand for minerals, the Void Miner is an essential helper")
    static Lang voidMinerTooltip2;

    @Key("ctnh.multiblock.void_miner.tooltip.3")
    @CN("每次工作消耗100,000B钻井液，升降温度时消耗极寒之凛冰和烈焰之炽焱")
    @EN("Each operation consumes 100,000 B of drilling fluid; Cryotheum and Pyrotheum are consumed when adjusting temperature")
    static Lang voidMinerTooltip3;

    @Key("ctnh.multiblock.void_miner.tooltip.4")
    @CN("在奇数次运行前，机器会试图消耗烈焰之炽焱来升温。初始烈焰之炽焱消耗量为1000mb,若成功消耗,则温度将会增加 ⌊(消耗量 ÷ 100)⌋,接着消耗量将会自乘以 1.02")
    @EN("Before odd-numbered operations, the machine attempts to consume Pyrotheum to raise the temperature. Initial consumption is 1000 mB; if successful, the temperature increases by floor(consumption / 100), then consumption is multiplied by 1.02.")
    static Lang voidMinerTooltip4;

    @Key("ctnh.multiblock.void_miner.tooltip.5")
    @CN("在偶数次运行前，机器会试图消耗极寒之凛冰来降温。初始极寒之凛冰消耗量为1000mb,若成功消耗,则温度将会降低 ⌊(消耗量 ÷ 100)⌋,接着消耗量将会自乘以 1.02")
    @EN("Before even-numbered operations, the machine attempts to consume Cryotheum to lower the temperature. Initial consumption is 1000 mB; if successful, the temperature decreases by floor(consumption / 100), then consumption is multiplied by 1.02.")
    static Lang voidMinerTooltip5;

    @Key("ctnh.multiblock.void_miner.tooltip.6")
    @CN("温度越高，虚空采矿场的产出倍率越高")
    @EN("The higher the temperature, the higher the Void Miner's output multiplier")
    static Lang voidMinerTooltip6;

    @Key("ctnh.multiblock.void_miner.tooltip.7")
    @CN("当温度达到25000K时，虚空采矿机将进入强制降温模式，直至温度降至0K时，恢复正常工作模式")
    @EN("At 25,000 K, the Void Miner enters forced cooling mode until the temperature reaches 0 K, then resumes normal operation.")
    static Lang voidMinerTooltip7;

    @Key("ctnh.multiblock.void_miner.tooltip.8")
    @CN("请交替输入烈焰之炽焱和极寒之凛冰来控制温度")
    @EN("Alternate Pyrotheum and Cryotheum inputs to control the temperature.")
    static Lang voidMinerTooltip8;

    @Key("ctnh.multiblock.mega_lcr.tooltip.0")
    @CN("§b具有4个异步线程§r")
    @EN("§bHas 4 asynchronous threads§r")
    static Lang megaLcrTooltip0;

    @Key("ctnh.multiblock.mega_lcr.tooltip.1")
    @CN("使用§d异步线程控制仓§r以配置多线程运行模式")
    @EN("Use §dAsynchronous Thread Control Hatches§r to configure multithreaded operation")
    static Lang megaLcrTooltip1;

    @Key("ctnh.multiblock.quasar_eye.tooltip.0")
    @CN("§9魔力§r的§c终极奥秘§r，足以制造§5类星体§r的装置掌握在§6你§r的手中")
    @EN("§9Mana's§r §cUltimate Mystery§r, a device capable of creating §5quasars§r now rests in §6your§r hands")
    static Lang quasarEyeTooltip0;

    @Key("ctnh.multiblock.quasar_eye.tooltip.1")
    @CN("该机器启动需要§r初始魔力燃料消耗§R，查阅EMI以查找消耗量")
    @EN("Machine activation requires §rinitial mana fuel consumption§R; consult EMI for the exact amount")
    static Lang quasarEyeTooltip1;

    @Key("ctnh.multiblock.quasar_eye.tooltip.2")
    @CN("在能量等级高时启动能量等级低的配方§b不需要启动花费§r")
    @EN("Activating lower-tier recipes at high energy tiers §bdoes not require an activation cost§r")
    static Lang quasarEyeTooltip2;

    @Key("ctnh.multiblock.quasar_eye.tooltip.3")
    @CN("§5符文能量§r控制着输出的强度，输入§b五级符文§r来增强符文能量，以加强你的输出,使用§5类星体符文§r产生大量符文能量")
    @EN("§5Rune Energy§r governs output strength. Input §bTier V Runes§r to increase rune energy and strengthen your output; use §5Quasar Runes§r to generate massive amounts of rune energy")
    static Lang quasarEyeTooltip3;

    @Key("ctnh.multiblock.quasar_eye.tooltip.4")
    @CN("该机器获取符文能量逻辑为：在§5每次配方运行前§r读取并消耗每类可消耗符文§c最多各一个§r")
    @EN("Rune energy is acquired as follows: §5before each recipe operation§r, read and consume §cat most one§r of each consumable rune type")
    static Lang quasarEyeTooltip4;

    @Key("ctnh.multiblock.quasar_eye.tooltip.5")
    @CN("§c注意§r：符文能量越高，其消耗速度就§c越快§r，且符文能量低于50时§c效率将会减半！§r")
    @EN("§cWarning§r: the higher the rune energy, the §cfaster§r it is consumed; when rune energy is below 50, §cefficiency is halved!§r")
    static Lang quasarEyeTooltip5;

    @Key("ctnh.multiblock.quasar_eye.tooltip.6")
    @CN("该机器能量效率为log((符文能量)/50)+1，最大能量效率为(1+能量等级)")
    @EN("Energy efficiency is log((rune energy) / 50) + 1, with a maximum of (1 + energy tier)")
    static Lang quasarEyeTooltip6;

    @Key("ctnh.multiblock.quasar_eye.tooltip.7")
    @CN("该机器拥有时间并行，消耗量和持续时间均会乘上并行数，且并行数为效率*5")
    @EN("The machine has time parallelism: consumption and duration are multiplied by parallelism, and parallelism equals efficiency × 5")
    static Lang quasarEyeTooltip7;

    @Key("ctnh.multiblock.quasar_eye.tooltip.8")
    @CN("该机器燃料消耗量为1-0.05*Math.max((rune_energy-50)/50,0.75)")
    @EN("Fuel consumption is 1 - 0.05 * Math.max((rune_energy - 50) / 50, 0.75)")
    static Lang quasarEyeTooltip8;

    @Key("ctnh.multiblock.quasar_eye.tooltip.9")
    @CN("在普通模式下发电时积将发电量的1%积累入类星体之眼之中，你每有25符文能量，就可以额外积累1%")
    @EN("In normal mode, 1% of generated power is accumulated in the Quasar Eye; every 25 rune energy grants an additional 1% accumulation")
    static Lang quasarEyeTooltip9;

    @Key("ctnh.multiblock.quasar_eye.tooltip.10")
    @CN("在创生模式下释放所有积累的电量，使用高级燃料可以使输出获得倍乘。同时每积累1000E EU就额外产出一份气体产出,积累电量小于1E时无法启动创生模式")
    @EN("In Creation Mode, release all accumulated power. Advanced fuels multiply the output. Every 1000E EU accumulated produces one additional gas output; Creation Mode cannot start with less than 1E stored power")
    static Lang quasarEyeTooltip10;

    @Key("ctnh.multiblock.quasar_eye.tooltip.11")
    @CN("§b好消息§r：这个机器不会爆炸，§c但我不保证未来它不会爆炸！§r")
    @EN("§bGood news§r: this machine will not explode, §cbut I cannot guarantee it will not explode in the future!§r")
    static Lang quasarEyeTooltip11;

    @Key("ctnh.multiblock.mana_reactor.tooltip.0")
    @CN("工业魔力奠基者")
    @EN("Industrial Mana Foundation")
    static Lang manaReactorTooltip0;

    @Key("ctnh.multiblock.mana_reactor.tooltip.1")
    @CN("允许使用并行控制仓")
    @EN("Allows parallel control hatches")
    static Lang manaReactorTooltip1;

    @Key("ctnh.multiblock.meteor_capturer.tooltip.0")
    @CN("§8为什么陨石总能落在陨石坑里？§r\n该机器无法超频")
    @EN("§8Why do meteors always land in meteor craters?§r\nThis machine cannot be overclocked")
    static Lang meteorCapturerTooltip0;

    @Key("ctnh.multiblock.meteor_capturer.tooltip.1")
    @CN("消耗少量引物和大量的生命源质，从外太空拉取满是矿石的陨石。")
    @EN("Consumes a small amount of primer and a large amount of Life Essence to pull ore-rich meteors from outer space.")
    static Lang meteorCapturerTooltip1;

    @Key("ctnh.multiblock.meteor_capturer.tooltip.2")
    @CN("配方需要大量的输入输出空间，建议使用高级输入总成。")
    @EN("Recipes require a large amount of input/output space; Advanced Input Hatches are recommended.")
    static Lang meteorCapturerTooltip2;

    @Key("ctnh.multiblock.meteor_capturer.tooltip.3")
    @CN("陨石会在多方块结构上方的空腔内生成（真的）。不要在里面放置人或设备。")
    @EN("Meteors generate in the cavity above the multiblock structure (really). Do not place people or equipment inside.")
    static Lang meteorCapturerTooltip3;

    @Key("ctnh.multiblock.meteor_capturer.tooltip.4")
    @CN("半径大于13的配方会破坏多方块结构（不存在这种配方）。")
    @EN("Recipes with a radius greater than 13 will destroy the multiblock structure (no such recipes exist).")
    static Lang meteorCapturerTooltip4;

    @Key("ctnh.recipe.arc_generator.require")
    @CN("需求电弧强度:%d")
    @EN("Required Arc Intensity: %d")
    static Lang recipeArcGeneratorRequire;

    @Key("ctnh.recipe.arc_generator.max_require")
    @CN("满功率需求电弧强度:%d")
    @EN("Full-Power Required Arc Intensity: %d")
    static Lang recipeArcGeneratorMaxRequire;

    @Key("ctnh.multiblock.arcreactor.arc")
    @CN("可输出的电弧强度:%d")
    @EN("Output Arc Intensity: %d")
    static Lang arcreactorArc;

    @Key("ctnh.multiblock.arcreactor.connect")
    @CN("§b桥接已启用§r")
    @EN("§bBridge Enabled§r")
    static Lang arcreactorConnect;

    @Key("ctnh.multiblock.arcgenerator.tooltip.1")
    @CN("物质撕裂器")
    @EN("Matter Ripper")
    static Lang arcgeneratorTooltip1;

    @Key("ctnh.multiblock.arcgenerator.tooltip.t2.1")
    @CN("分子撕裂器")
    @EN("Molecular Ripper")
    static Lang arcgeneratorTooltipT21;

    @Key("ctnh.multiblock.arcgenerator.tooltip.t3.1")
    @CN("原子撕裂器")
    @EN("Atomic Ripper")
    static Lang arcgeneratorTooltipT31;

    @Key("ctnh.multiblock.magic_fuel_generator.tip")
    @CN("精炼天地之魔精")
    @EN("Refined Essence of Heaven and Earth")
    static Lang magicFuelGeneratorTip;

    @Key("ctnh.multiblock.arcgenerator.tooltip.arc.t1.1")
    @CN("§b最大支持电弧强度:1000")
    @EN("§bMaximum Supported Arc Intensity: 1000")
    static Lang arcgeneratorTooltipArcT11;

    @Key("ctnh.multiblock.arcgenerator.tooltip.arc.t1.2")
    @CN("§c最大发电效率:75%")
    @EN("§cMaximum Power Generation Efficiency: 75%")
    static Lang arcgeneratorTooltipArcT12;

    @Key("ctnh.multiblock.arcgenerator.tooltip.arc.t2.1")
    @CN("§b最大支持电弧强度:10000")
    @EN("§bMaximum Supported Arc Intensity: 10000")
    static Lang arcgeneratorTooltipArcT21;

    @Key("ctnh.multiblock.arcgenerator.tooltip.arc.t2.2")
    @CN("§c最大发电效率:125%")
    @EN("§cMaximum Power Generation Efficiency: 125%")
    static Lang arcgeneratorTooltipArcT22;

    @Key("ctnh.multiblock.arcgenerator.tooltip.arc.t3.1")
    @CN("§b最大支持电弧强度:50000")
    @EN("§bMaximum Supported Arc Intensity: 50000")
    static Lang arcgeneratorTooltipArcT31;

    @Key("ctnh.multiblock.arcgenerator.tooltip.arc.t3.2")
    @CN("§c最大发电效率:225%")
    @EN("§cMaximum Power Generation Efficiency: 225%")
    static Lang arcgeneratorTooltipArcT32;

    @Key("ctnh.multiblock.arcgenerator.tooltip.2")
    @CN("该机器必须配合电弧生成器使用，要求电弧生成器必须在该机器主方块上方5格，当完成链接时，电弧生成器会显示已完成连接")
    @EN("This machine must be used with an Arc Generator. The Arc Generator must be five blocks above this machine's controller. When linked, the Arc Generator shows that the connection is complete")
    static Lang arcgeneratorTooltip2;

    @Key("ctnh.multiblock.arcgenerator.tooltip.3")
    @CN("当电弧强度小于配方最小电弧强度时，配方将无法运行")
    @EN("If arc intensity is below the recipe's minimum, the recipe cannot run")
    static Lang arcgeneratorTooltip3;

    @Key("ctnh.multiblock.arcgenerator.tooltip.4")
    @CN("当电弧强度大于配方最大电弧强度时，配方将以(机器电弧强度-配方需求电弧强度)/(满功率需求电弧强度-需求电弧强度)的效率运行。效率允许超过100%，但不能超过机器最大发电效率")
    @EN("When arc intensity exceeds the recipe maximum, the recipe runs at (machine arc intensity - recipe required arc intensity) / (full-power required arc intensity - required arc intensity) efficiency. Efficiency may exceed 100%, but cannot exceed the machine maximum power generation efficiency")
    static Lang arcgeneratorTooltip4;

    @Key("ctnh.multiblock.arcgenerator.tooltip.5")
    @CN("当效率未达100%时，因为湮灭的不完全，将产生少量额外产出")
    @EN("When efficiency is below 100%, incomplete annihilation produces a small amount of extra output")
    static Lang arcgeneratorTooltip5;

    @Key("ctnh.multiblock.arcreactor.tooltip")
    @CN("电弧发生者")
    @EN("Arc Generator")
    static Lang arcreactorTooltip;

    @Key("ctnh.multiblock.arcreactor.tooltip.1")
    @CN("该机器必须配合电弧撕裂者使用，要求电弧生成器必须在电弧撕裂者主方块上方5格，当完成链接时，电弧生成器会显示桥接已启用")
    @EN("This machine must be used with an Arc Ripper. The Arc Generator must be five blocks above the Arc Ripper's controller; when linked, the Arc Generator shows that the bridge is enabled")
    static Lang arcreactorTooltip1;

    @Key("ctnh.multiblock.arcreactor.tooltip.2")
    @CN("机器基础每次运行配方产生10电弧强度，无法超频，高等级机器具有更高并行数")
    @EN("The machine produces 10 arc intensity each time it runs a recipe, cannot be overclocked, and higher-tier machines have more parallelism")
    static Lang arcreactorTooltip2;

    @Key("ctnh.multiblock.arcreactor.tooltip.t1")
    @CN("该机器并行数:1")
    @EN("Machine parallelism: 1")
    static Lang arcreactorTooltipT1;

    @Key("ctnh.magic.generator")
    @CN("精炼天地之魔精")
    @EN("Refined Essence of Heaven and Earth")
    static Lang magicGenerator;

    @Key("ctnh.magic.generator.1")
    @CN("具有8并行，每秒基础消耗12mB液态魔力，电压每超过§7LV§r一级，消耗量变为原来的两倍")
    @EN("Has 8 parallel operations and consumes 12 mB of Liquid Mana per second at base. For each voltage tier above §7LV§r, consumption doubles")
    static Lang magicGenerator1;

    @Key("ctnh.gcym.reduction")
    @CN("配方耗时x0.8，配方耗能x0.6")
    @EN("Recipe time ×0.8, recipe energy ×0.6")
    static Lang gcymReduction;

    @Key("ctnh.mechanical_lathe.structure")
    @CN("结构中的车床必须严格依照EMI结构信息页面展示的位置和方向摆放")
    @EN("The lathe in the structure must be placed exactly as shown in EMI's structure information, including position and orientation")
    static Lang mechanicalLatheStructure;

    @Key("ctnh.mechanical_lathe.structure.1")
    @CN("§7EMI中显示的车床数量有问题，实际只需要6个")
    @EN("§7The number of lathes shown in EMI is incorrect; only 6 are actually required")
    static Lang mechanicalLatheStructure1;

    @Key("ctnh.multiblock.plasma_alloy.tooltip.1")
    @CN("§4转底炉的复仇")
    @EN("§4The Revenge of the Rotary Kiln")
    static Lang plasmaAlloyTooltip1;

    @Key("ctnh.multiblock.plasma_alloy.tooltip.11")
    @CN("允许使用§b激光仓§r，使用激光仓时最终速度将除以4，速度低于原速度时拒绝运行")
    @EN("Allows §blaser hatches§r; when using a laser hatch, final speed is divided by 4, and operation is rejected if it falls below the original speed")
    static Lang plasmaAlloyTooltip11;

    @Key("ctnh.multiblock.plasma_alloy.tooltip.2")
    @CN("线圈温度每有1800K，获得4点并行，线圈温度超过10000K时，获得(线圈温度-10000)/10000的额外加速")
    @EN("Every 1800 K of coil temperature grants 4 parallel operations; above 10000 K, it grants an additional speed multiplier of (coil temperature - 10000) / 10000")
    static Lang plasmaAlloyTooltip2;

    @Key("ctnh.multiblock.plasma_alloy.tooltip.3")
    @CN("运行前消耗(并行数*对应等离子体消耗)的等离子体，获得额外加速")
    @EN("Consumes (parallel count × corresponding plasma consumption) of plasma before operation to gain an additional speed boost")
    static Lang plasmaAlloyTooltip3;

    @Key("ctnh.multiblock.plasma_alloy.tooltip.4")
    @CN("氦等离子体：消耗500*并行的等离子体，速度+100%")
    @EN("Helium plasma: consumes 500 × parallel plasma, speed +100%")
    static Lang plasmaAlloyTooltip4;

    @Key("ctnh.multiblock.plasma_alloy.tooltip.5")
    @CN("氧，氮等离子体：消耗300*并行的等离子体，速度+200%")
    @EN("Oxygen or nitrogen plasma: consumes 300 × parallel plasma, speed +200%")
    static Lang plasmaAlloyTooltip5;

    @Key("ctnh.multiblock.plasma_alloy.tooltip.6")
    @CN("镍，铁等离子体：消耗200*并行的等离子体，速度+300%")
    @EN("Nickel or iron plasma: consumes 200 × parallel plasma, speed +300%")
    static Lang plasmaAlloyTooltip6;

    @Key("ctnh.multiblock.plasma_alloy.tooltip.7")
    @CN("消耗特殊的冶炼等离子体可以获得额外的速度加成，§c但是同样会将增加你冶炼的风险")
    @EN("Special smelting plasmas grant additional speed bonuses, §cbut also increase the risk of your smelting process")
    static Lang plasmaAlloyTooltip7;

    @Key("ctnh.multiblock.plasma_alloy.tooltip.8")
    @CN("压缩精金等离子：消耗固定100等离子体，使速度*5,使消耗电压翻倍（§c这可能导致配方不运行，请使用多安能源仓）")
    @EN("Compressed Enriched Naquadah Plasma: consumes a fixed 100 plasma, speed ×5, and doubles voltage consumption (§cwhich may prevent recipes from running; use multi-amp energy hatches)")
    static Lang plasmaAlloyTooltip8;

    @Key("ctnh.multiblock.plasma_alloy.tooltip.9")
    @CN("精炼超能以太等离子体：消耗50*并行等离子体，使速度*10,§c使最终产物在80%-100%中浮动")
    @EN("Refined Super Energetic Aether Plasma: consumes 50 × parallel plasma, speed ×10, §cand makes final output fluctuate between 80% and 100%")
    static Lang plasmaAlloyTooltip9;

    @Key("ctnh.multiblock.plasma_alloy.tooltip.10")
    @CN("§c速度增幅超过5000%时，最终产物量将会在0%-50%中浮动！")
    @EN("§cWhen the speed bonus exceeds 5000%, final output fluctuates between 0% and 50%!")
    static Lang plasmaAlloyTooltip10;

    @Key("ctnh.plasma_alloy.tooltip.recipe")
    @CN("配方类型：合金冶炼炉")
    @EN("Recipe Type: Alloy Blast Smelter")
    static Lang plasmaAlloyTooltipRecipe;

    @Key("ctnh.acc.danger")
    @CN("§c危险粒子实验")
    @EN("§cDangerous Particle Experiment")
    static Lang accDanger;

    @Key("ctnh.multiblock.hybrid_mixer.tooltip.0")
    @CN("动力学的电力复兴")
    @EN("Kinetic-Electric Renaissance")
    static Lang hybridMixerTooltip0;

    @Key("ctnh.multiblock.hybrid_mixer.tooltip.1")
    @CN("执行特殊的电压-应力驱动机制")
    @EN("Uses a special voltage-stress drive mechanism")
    static Lang hybridMixerTooltip1;

    @Key("ctnh.multiblock.hybrid_mixer.tooltip.2")
    @CN("机器真实电压等级为配方电压等级和应力等级的较小值。应力输入仓要求转速至少为64，应力输入仓转速为256时，应力等级+1")
    @EN("The machine's actual voltage tier is the lower of the recipe voltage tier and stress tier. Stress input hatches require at least 64 RPM; at 256 RPM, the stress tier increases by 1")
    static Lang hybridMixerTooltip2;

    @Key("ctnh.multiblock.hybrid_mixer.tooltip.3")
    @CN("混合动力超频：应力等级和配方电压等级每同时提升一级，运行速度*4")
    @EN("Hybrid overclocking: each time both stress tier and recipe voltage tier increase by one, processing speed ×4")
    static Lang hybridMixerTooltip3;

    @Key("ctnh.multiblock.hybrid_mixer.tooltip.4")
    @CN("当转速超过64时，使配方时间*0.8。转速超过128时配方时间和电压速度将随着转速提升进一步减少")
    @EN("Above 64 RPM, recipe time ×0.8. Above 128 RPM, recipe time and voltage speed decrease further as RPM increases")
    static Lang hybridMixerTooltip4;

    @Key("ctnh.multiblock.spacephotovoltaicbasestation.tooltip.0")
    @CN("§6光辉灿烂的太空之路")
    @EN("§6A Glorious Path Through Space")
    static Lang spacephotovoltaicbasestationTooltip0;

    @Key("ctnh.multiblock.spacephotovoltaicbasestation.tooltip.2")
    @CN("在太空发电模式下，星球类型和光伏方块的等级都会提升发电量，在空间站被视为无重力环境，且发电量*4,消耗特定材料以进一步提升发电量")
    @EN("In Space Power Generation mode, planet type and photovoltaic block tier increase power output. The station is treated as a zero-gravity environment, multiplying output by 4; consuming specific materials increases output further")
    static Lang spacephotovoltaicbasestationTooltip2;

    @Key("ctnh.multiblock.spacephotovoltaicbasestation.tooltip.3")
    @CN("在太空光伏组装模式下，不消耗EUt，发电量将锁定为1，根据配方的模拟F功率来计算速度和并行量")
    @EN("In Space Photovoltaic Assembly mode, no EUt is consumed and power output is locked at 1. Speed and parallelism are calculated from the recipe's simulated F power")
    static Lang spacephotovoltaicbasestationTooltip3;

    @Key("ctnh.multiblock.spacephotovoltaicbasestation.tooltip.4")
    @CN("光伏等级，光照强度共同决定了是否可以执行太空组装配方，光伏方块耐热性和耐热结构方块决定了可以获得的光照最大倍率，太空结构方块决定了可以使用的光伏方块等级和是否可以使用附属结构")
    @EN("Photovoltaic tier and light intensity determine whether space assembly recipes can run. Photovoltaic block heat resistance and heat-resistant structural blocks determine the maximum light multiplier. Space structural blocks determine the usable photovoltaic tier and whether auxiliary structures are allowed")
    static Lang spacephotovoltaicbasestationTooltip4;

    @Key("ctnh.multiblock.spacephotovoltaicbasestation.tooltip.5")
    @CN("在太空光伏组装模式下，最终并行为(太空发电模式下发电量/模拟功率),最终时间倍率为(模拟功率/太空发电模式下发电量)，当太空发电模式下发电量小于模拟功率时，最终时间倍率将变为平方")
    @EN("In Space Photovoltaic Assembly mode, final parallelism = (Space Power Generation output / simulated power), and final time multiplier = (simulated power / Space Power Generation output). If Space Power Generation output is lower than simulated power, the final time multiplier is squared")
    static Lang spacephotovoltaicbasestationTooltip5;

    @Key("ctnh.multiblock.spacephotovoltaicbasestation.tooltip.ex")
    @CN("§6该结构将持续拓展，这还不是它的完全体状态！")
    @EN("§6This structure will continue to expand; it is not yet complete!")
    static Lang spacephotovoltaicbasestationTooltipEx;

    @Key("ctnh.multiblock.lasersorter.tooltip.0")
    @CN("持续调整激光频率")
    @EN("Continuously adjusts the laser frequency")
    static Lang lasersorterTooltip0;

    @Key("ctnh.multiblock.lasersorter.tooltip.1")
    @CN("配方类型：激光分配/激光蚀刻")
    @EN("Recipe types: Laser Distribution / Laser Etching")
    static Lang lasersorterTooltip1;

    @Key("ctnh.multiblock.lasersorter.tooltip.2")
    @CN("本机器需要消耗算力才能运行")
    @EN("This machine consumes computation to operate")
    static Lang lasersorterTooltip2;

    @Key("ctnh.multiblock.lasersorter.tooltip.3")
    @CN("————————激光蚀刻模式————————")
    @EN("————————Laser Etching Mode————————")
    static Lang lasersorterTooltip3;

    @Key("ctnh.multiblock.lasersorter.tooltip.4")
    @CN("LuV及以下的电压固定基础请求8算力，电压每高于LuV一级，请求的基础算力翻倍")
    @EN("At LuV and below, the base computation requirement is fixed at 8 CWU; for each voltage tier above LuV, the base requirement doubles")
    static Lang lasersorterTooltip4;

    @Key("ctnh.multiblock.lasersorter.tooltip.5")
    @CN("输入的算力如果为基础请求算力的整数倍，则最终输出*1.25，并行等同于⌊(输入的算力/基础请求算力)⌋的三次方")
    @EN("If input computation is an integer multiple of the base requirement, final output ×1.25 and parallelism = floor(input computation / base requirement)^3")
    static Lang lasersorterTooltip5;

    @Key("ctnh.multiblock.lasersorter.tooltip.6")
    @CN("输入的算力每比基础算力多一倍，将一次超频转化为无损超频（即运行速度*2），该效果转化的次数不超过你能超频的等级（即上限为将你所有的有损超频转化为无损）")
    @EN("Each time input computation doubles the base requirement, one lossy overclock is converted into a perfect overclock (processing speed ×2). Conversions cannot exceed the machine's overclock tier, up to converting all lossy overclocks into perfect overclocks")
    static Lang lasersorterTooltip6;

    @Key("ctnh.multiblock.lasersorter.tooltip.7")
    @CN("————————激光分配模式————————")
    @EN("————————Laser Distribution Mode————————")
    static Lang lasersorterTooltip7;

    @Key("ctnh.multiblock.lasersorter.tooltip.8")
    @CN("配方给出请求算力，如果配方没有给出则按照激光蚀刻模式的公式计算")
    @EN("The recipe specifies the required computation; if it does not, calculate it using the Laser Etching formula")
    static Lang lasersorterTooltip8;

    @Key("ctnh.multiblock.lasersorter.tooltip.9")
    @CN("输入的算力如果为基础请求算力的整数倍，则并行等同于⌊(输入的算力/基础请求算力)⌋的三次方")
    @EN("If input computation is an integer multiple of the base requirement, parallelism = floor(input computation / base requirement)^3")
    static Lang lasersorterTooltip9;

    @Key("ctnh.multiblock.lasersorter.tooltip.10")
    @CN("输入的算力每比基础算力多一倍，将一次超频转化为无损超频（即运行速度*2），该效果转化的次数不超过你能超频的等级（即上限为将你所有的有损超频转化为无损）")
    @EN("Each time input computation doubles the base requirement, one lossy overclock is converted into a perfect overclock (processing speed ×2). Conversions cannot exceed the machine's overclock tier, up to converting all lossy overclocks into perfect overclocks")
    static Lang lasersorterTooltip10;

    @Key("ctnh.multiblock.lasersorter.tooltip.11")
    @CN("§c如果输入的算力不为整数倍，则以上所有的增益全部无效且最终所需时间*4")
    @EN("§cIf input computation is not an integer multiple, all bonuses above are disabled and final time ×4")
    static Lang lasersorterTooltip11;

    @Key("ctnh.multiblock.pvdrone.tooltip.0")
    @CN("戴森云计划")
    @EN("Dyson Swarm Project")
    static Lang pvdroneTooltip0;

    @Key("ctnh.multiblock.pvdrone.tooltip.1")
    @CN("允许使用并行控制仓，并行数为运行时间倍率")
    @EN("Allows parallel control hatches; parallelism equals the processing-time multiplier")
    static Lang pvdroneTooltip1;

    @Key("ctnh.multiblock.pvdrone.tooltip.2")
    @CN("为光伏基站提供电力增幅，使用光伏绑定终端来为这两个结构绑定")
    @EN("Provides a power boost to the photovoltaic station; use the Photovoltaic Binding Terminal to bind these two structures")
    static Lang pvdroneTooltip2;

    @Key("ctnh.multiblock.pvdrone.tooltip.3")
    @CN("将无人机放入无人机支架以开始发送无人机，每5秒和运行结束时，每个无人机都有一定概率消耗，无人机发电同样受维度和空间站增幅")
    @EN("Place drones in the drone holder to begin transmitting them. Each drone has a chance to be consumed every 5 seconds and when operation ends; drone generation is also affected by dimension and station bonuses")
    static Lang pvdroneTooltip3;

    @Key("ctnh.multiblock.pvdrone.tooltip.4")
    @CN("无人机的消耗概率公式为1.0 / (1.0 + Math.exp(-0.25* (x - 9)))")
    @EN("Drone consumption chance = 1.0 / (1.0 + Math.exp(-0.25 * (x - 9)))")
    static Lang pvdroneTooltip4;

    @Key("ctnh.multiblock.pvdrone.tooltip.5")
    @CN("使用无人机收集陨石时，产出的倍率公式为0.1*Math.sqrt(无人机电压之和)，倍率小于1时无产出，无法超频")
    @EN("When drones collect meteors, output multiplier = 0.1 × sqrt(sum of drone voltages). No output below multiplier 1; cannot be overclocked")
    static Lang pvdroneTooltip5;

    @Key("ctnh.multiblock.pvdrone.info.t1")
    @CN("提供的能量:%d")
    @EN("Power supplied: %d")
    static Lang pvdroneInfoT1;

    @Key("ctnh.multiblock.pvdrone.info.t2")
    @CN("无人机消耗概率:%.4f")
    @EN("Drone consumption chance: %.4f")
    static Lang pvdroneInfoT2;

    @Key("multiblock.ctnh.nuclear_reactor.coolant")
    @CN("冷却液：%s")
    @EN("Coolant: %s")
    static Lang multiblockCtnhNuclearReactorCoolant;

    @Key("multiblock.ctnh.nuclear_reactor.coolant_amount")
    @CN("冷却液量：%s mB")
    @EN("Coolant amount: %s mB")
    static Lang multiblockCtnhNuclearReactorCoolantAmount;

    @Key("multiblock.ctnh.nuclear_reactor.consume_amount")
    @CN("冷却液消耗率：%s mB/s")
    @EN("Coolant consumption rate: %s mB/s")
    static Lang multiblockCtnhNuclearReactorConsumeAmount;

    @Key("nuclear_reactor")
    @CN("核能转化时刻")
    @EN("Nuclear Energy Conversion")
    static Lang nuclearReactor;

    @Key("ctnh.nuclear_reactor.basic")
    @CN("这是一个耗能设备，但是会产生大量的热量，可以转化用以发电")
    @EN("This is an energy-consuming machine that produces a large amount of heat, which can be converted into power")
    static Lang nuclearReactorBasic;

    @Key("ctnh.nuclear_reactor.coolant")
    @CN("冷却液可以使用蒸汽（150°C），氘（450°C），钠（800°C），钠钾合金（900°C），反应的堆温越高，消耗冷却液的速度越快，冷却液的热容越大，消耗速度越慢")
    @EN("Coolant can be steam (150°C), deuterium (450°C), sodium (800°C), or sodium-potassium alloy (900°C). Higher reactor temperature increases coolant consumption, while higher coolant heat capacity reduces consumption")
    static Lang nuclearReactorCoolant;

    @Key("ctnh.nuclear_reactor.overclock")
    @CN("冷却液并非运行所必须，但是在有冷却液时，配方每运行一秒，进度会增加两秒")
    @EN("Coolant is not required for operation, but with coolant present, recipe progress increases by two seconds for every one second of operation")
    static Lang nuclearReactorOverclock;

    @Key("ctnh.nuclear_reactor.safe")
    @CN("反应堆不会过热爆炸")
    @EN("The reactor will not explode from overheating")
    static Lang nuclearReactorSafe;

    @Key("ctnh.multiblock.cryotheum_freezer.tip.0")
    @CN("§b泪水如凛冰般落下")
    @EN("§bTears fall like Cryotheum")
    static Lang cryotheumFreezerTip0;

    @Key("ctnh.multiblock.cryotheum_freezer.tip.1")
    @CN("每次运行配方消耗5*并行mb极寒之凛冰，电压每高于§9IV§r一级，这个消耗就翻4倍")
    @EN("Each recipe operation consumes 5 × parallel mB of Cryotheum; for each voltage tier above §9IV§r, consumption quadruples")
    static Lang cryotheumFreezerTip1;

    @Key("ctnh.multiblock.cryotheum_freezer.tip.2")
    @CN("初始具有4并行和3泪之晶点数，可以在升级界面加点。每消耗10000mb凛冰，就获得一点点数，随后将目标翻四倍")
    @EN("Starts with 4 parallel operations and 3 Tear Crystal points; add points in the upgrade screen. Every 10000 mB of Cryotheum consumed grants one point, then the target amount quadruples")
    static Lang cryotheumFreezerTip2;

    @Key("ctnh.multiblock.neuro_martix_compiler.tip.0")
    @CN("将生物的进化之道完全放任于碳基生物的自然演变是一种低效且缓慢的做法，现在我们将亲自编码每一个基因序列，将我们的至臻完美编译在神经元的逻辑之中")
    @EN("Leaving the evolution of living beings entirely to the natural evolution of carbon-based life is inefficient and slow. We will now encode every genetic sequence ourselves, compiling our ultimate perfection into neuronal logic")
    static Lang neuroMartixCompilerTip0;

    @Key("ctnh.multiblock.neuro_martix_compiler.tip.01")
    @CN("神经矩阵编码器（CMP）是一台编译神经序列的机器，其不同于其他机器，不执行正常的输入逻辑，无法超频")
    @EN("The Neural Matrix Compiler (CMP) compiles neural sequences. Unlike other machines, it does not use normal input logic and cannot be overclocked")
    static Lang neuroMartixCompilerTip01;

    @Key("ctnh.multiblock.neuro_martix_compiler.tip.1")
    @CN("该机器的输入由6个神经矩阵研究舱室组成，每个舱室在结构完成时将被编码，所有研究舱室必须为同一等级，编码完成后，每个研究舱室将会显示他们所属的片区（并未实现）现在片区分配固定为：机器主方块左前方为1，右前方为2，左后方为3，右后方为4")
    @EN("The machine input consists of 6 Neural Matrix Research Chambers. Each chamber is encoded when the structure is completed, and all research chambers must be the same tier. After encoding, each chamber displays its assigned region (not implemented). Regions are currently fixed as follows: the front-left of the controller is 1, front-right is 2, back-left is 3, and back-right is 4")
    static Lang neuroMartixCompilerTip1;

    @Key("ctnh.multiblock.neuro_martix_compiler.tip.2")
    @CN("该机器的输入§c必须严格按照EMI的物品顺序§9从左到右§r从第一行到第二行放置在§91-5片区§r，同时在第六片区放置§9研究数据集§r§r,任何错误的放置或者外部舱室的放置都会导致机器故障并在对应舱室显示故障")
    @EN("Machine inputs §cmust strictly follow the EMI item order§9, placed §rfrom left to right§r and from the first row to the second row in §9Regions 1-5§r. Place the §9Research Dataset§r in Region 6. Any incorrect placement or external chamber placement will cause a machine fault, shown in the corresponding chamber")
    static Lang neuroMartixCompilerTip2;

    @Key("ctnh.multiblock.neuro_martix_compiler.tip.3")
    @CN("必须保证所有神经矩阵研究舱室的等级不低于配方等级，否则配方不会运行")
    @EN("All Neural Matrix Research Chambers must be at least the recipe tier; otherwise the recipe will not run")
    static Lang neuroMartixCompilerTip3;

    @Key("ctnh.multiblock.neuro_martix_compiler.tip.4")
    @CN("————————机器总体机制————————")
    @EN("————————Overall Machine Mechanics————————")
    static Lang neuroMartixCompilerTip4;

    @Key("ctnh.multiblock.neuro_martix_compiler.tip.5")
    @CN("每次检测到新的配方时，机器将§6完美诉诸于随机§r，生成函数F(x1,x2,x3)=§6y=ax1+bx2+cx3+d§r，其中，x1,x2,x3为期望的片区所消耗的物品数量，同时在给定范围内随机x1,x2,x3,获取答案y")
    @EN("Whenever a new recipe is detected, the machine §6entrusts itself to randomness§r and generates F(x1,x2,x3)=§6y=ax1+bx2+cx3+d§r, where x1, x2, and x3 are the quantities of items consumed by the target regions. It then randomly chooses x1, x2, and x3 within the given ranges to obtain y")
    static Lang neuroMartixCompilerTip5;

    @Key("ctnh.multiblock.neuro_martix_compiler.tip.6")
    @CN("当配方执行时，在开始逻辑运算，1-5片区将各自运行5s,运行完毕时将消耗舱室内所有物品来取得函数")
    @EN("When a recipe runs, logical computation begins. Regions 1-5 each run for 5 s; when finished, they consume all items in their chambers to obtain the function")
    static Lang neuroMartixCompilerTip6;

    @Key("ctnh.multiblock.neuro_martix_compiler.tip.part1")
    @CN("片区1-3：用于提供函数F(x)的真实x1,x2,x3")
    @EN("Regions 1-3: provide the actual x1, x2, and x3 for function F(x)")
    static Lang neuroMartixCompilerTipPart1;

    @Key("ctnh.multiblock.neuro_martix_compiler.tip.part2")
    @CN("片区4：代表函数F(x)的常数量d，同时决定噪声ϵ")
    @EN("Region 4: represents the constant d in function F(x) and determines noise ϵ")
    static Lang neuroMartixCompilerTipPart2;

    @Key("ctnh.multiblock.neuro_martix_compiler.tip.part3")
    @CN("片区5：此片区用为神经编译提供电路板支持，决定噪声ϵ波动，如果提供电路板大于配方给定值则不造成噪声影响")
    @EN("Region 5: provides circuit-board support for neural compilation and determines noise ϵ fluctuation. If the provided circuit board exceeds the recipe requirement, it causes no noise impact")
    static Lang neuroMartixCompilerTipPart3;

    @Key("ctnh.multiblock.neuro_martix_compiler.tip.part4")
    @CN("片区6：收集最终编译结果的片区，在完成一次逻辑运算流程后，将根据结果对神经数据集进行修改")
    @EN("Region 6: collects the final compilation result and modifies the neural dataset based on the result after a logical computation cycle")
    static Lang neuroMartixCompilerTipPart4;

    @Key("ctnh.multiblock.neuro_martix_compiler.tip.7")
    @CN("在片区1-5执行完毕后，进行持续5s的总计算流程，在此过程中给出x1,x2,x3，计算得到计算值y，与真实比较，进行最终编译运算")
    @EN("After Regions 1-5 finish, a total computation process runs for 5 s. During this process, x1, x2, and x3 are supplied, the calculated y is compared with the true value, and the final compilation is performed")
    static Lang neuroMartixCompilerTip7;

    @Key("ctnh.multiblock.neuro_martix_compiler.tip.8")
    @CN("————————最终编译运算————————")
    @EN("————————Final Compilation————————")
    static Lang neuroMartixCompilerTip8;

    @Key("ctnh.multiblock.neuro_martix_compiler.tip.9")
    @CN("最终编译运算将比较真实y与结果y，如果结果y的值在真实值y的0.9-1.1倍内，则运算成功，将编译数据集变为配方输出")
    @EN("Final compilation compares true y with result y. If result y is within 0.9 to 1.1 times true y, the computation succeeds and the compiled dataset becomes the recipe output")
    static Lang neuroMartixCompilerTip9;

    @Key("ctnh.multiblock.neuro_martix_compiler.tip.10")
    @CN("如果运算失败，则定义噪声结果函数f(x1,x2,x3)=ax1+bx2+cx3+d+ϵ,根据噪声决定片区，噪声波动最多翻倍5倍，片区误差项为0.9-1.1间，则噪声变为0.5倍率")
    @EN("If computation fails, define the noise result function f(x1,x2,x3)=ax1+bx2+cx3+d+ϵ. The region is determined by noise; noise fluctuation can be multiplied by up to 5, and if the region error term is between 0.9 and 1.1, noise becomes a 0.5 multiplier")
    static Lang neuroMartixCompilerTip10;

    @Key("ctnh.multiblock.neuro_martix_compiler.tip.11")
    @CN("最终编译数据集将获得三个信息，信息1代表本次的方程，信息2代表噪声结果函数获得的结果值对于y的倍率，信息3代表误差项的比率")
    @EN("The final compiled dataset contains three pieces of information: information 1 is the equation used, information 2 is the result value from the noise function as a multiplier of y, and information 3 is the error-term ratio")
    static Lang neuroMartixCompilerTip11;

    @Key("ctnh.multiblock.neuro_martix_compiler.tip.12")
    @CN("在执行相同配方时方程不会重置，在执行配方或者结构重新成型时，重置y和方程")
    @EN("The equation is not reset when executing the same recipe. It resets y and the equation when a recipe is executed or the structure is re-formed")
    static Lang neuroMartixCompilerTip12;

    @Key("ctnh.data.noise")
    @CN("当前噪声值：%s")
    @EN("Current noise: %s")
    static Lang dataNoise;

    @Key("super_centrifuge")
    @CN("超速离心")
    @EN("Super Centrifuge")
    static Lang superCentrifuge;

    @Key("ctnh.super_centrifuge.parallel")
    @CN("普通离心机模式下会获得8并行")
    @EN("Provides 8 parallel operations in normal centrifuge mode")
    static Lang superCentrifugeParallel;

    @Key("ultrasonic_apparatus")
    @CN("超声破碎")
    @EN("Ultrasonic Disruptor")
    static Lang ultrasonicApparatus;

    @Key("ctnh.compiler.error.0")
    @CN("§c未知错误")
    @EN("§cUnknown error")
    static Lang compilerError0;

    @Key("ctnh.compiler.error.1")
    @CN("§c舱室等级与片区1不匹配")
    @EN("§cChamber tier does not match Region 1")
    static Lang compilerError1;

    @Key("ctnh.dwof.tooltip")
    @CN("运行时为模型积累数据，至多将模型提高至[进阶]等级")
    @EN("Accumulates data for the model during operation, raising it up to [Advanced] at most")
    static Lang dwofTooltip;

    @Key("ctnh.multiblock.fluid_drilling_rig.description.inf")
    @CN("§6钻取来自无尽之中的流体之海")
    @EN("§6Drilling a sea of fluids from the Infinite")
    static Lang fluidDrillingRigDescriptionInf;

    @Key("ctnh.multiblock.fluid_drilling_rig.depletion.inf")
    @CN("§6永§b不§d损§a耗，你在担心什么？")
    @EN("§6E§bver§dlast§aing—what are you worried about?")
    static Lang fluidDrillingRigDepletionInf;

    @Key("zenith_machine_sp")
    @CN("§5灵能灯塔屹立不倒！")
    @EN("§5The Psionic Beacon Stands Unyielding!")
    static Lang zenithMachineSp;

    @Key("ctnh.multiblock.wide_accelerator.info.power")
    @CN("存储的电量：%.2f E/%.2f E")
    @EN("Stored energy: %.2f E/%.2f E")
    static Lang wideAcceleratorInfoPower;

    @Key("ctnh.eternal_engine.1")
    @CN("当前发电量:%d EU /tick")
    @EN("Current power output: %d EU/tick")
    static Lang eternalEngine1;

    @Key("ctnh.eternal_engine.2")
    @CN("累计的工作时间:%.2f s/36000 s")
    @EN("Total operating time: %.2f s/36000 s")
    static Lang eternalEngine2;

    @Key("ctnh.connect")
    @CN("连接已搭建")
    @EN("Connection established")
    static Lang connect;

    @Key("ctnh.anti_nu")
    @CN("反中子量:%d")
    @EN("Antineutron amount: %d")
    static Lang antiNu;

    @Key("ctnh.anti_proton")
    @CN("反质子量:%d")
    @EN("Antiproton amount: %d")
    static Lang antiProton;

    @Key("ctnh.anti_electric")
    @CN("反电子量:%d")
    @EN("Positron amount: %d")
    static Lang antiElectric;

    @Key("ctnh.trap_electric")
    @CN("当前存储电量:%deu")
    @EN("Current stored power: %d EU")
    static Lang trapElectric;

    @Key("ctnh.trap_electric_max")
    @CN("允许存储电量上限:%deu")
    @EN("Maximum stored power: %d EU")
    static Lang trapElectricMax;

    @Key("ctnh.restore_danger")
    @CN("约束危险物质")
    @EN("Contain hazardous materials")
    static Lang restoreDanger;

    @Key("ctnh.no_energy_waring")
    @CN("§c警告：供电不足，约束场即将失效！")
    @EN("§cWarning: insufficient power; the containment field is about to fail!")
    static Lang noEnergyWaring;

    @Key("ctnh.u_sinope.story.1")
    @CN("在战争没有开始前，人们曾团结在一起，一齐建造这工业的巴别巨塔")
    @EN("Before the war began, people stood united and built this industrial Tower of Babel together")
    static Lang uSinopeStory1;

    @Key("ctnh.u_sinope.story.2")
    @CN("直到那场永恒的战争，这座真空巨塔化为永恒的残骸，随着战争的双方破碎在真空中")
    @EN("Until the Eternal War, when this vacuum tower became eternal ruins, shattered in the vacuum along with both sides of the war")
    static Lang uSinopeStory2;

    @Key("ctnh.u_sinope.story.3")
    @CN("你已无法再知晓那场战争的双方是否已经相互毁灭，但你直到，这座巨型结构将宣告着人类的复兴")
    @EN("You can no longer know whether both sides of that war destroyed each other, but you know this giant structure will herald humanity's revival")
    static Lang uSinopeStory3;

    @Key("ctnh.u_sinope.1")
    @CN("配方类型：蒸馏塔/蒸馏室/裂化机/流体加热机/流体固化机/真空石化处理/???")
    @EN("Recipe types: Distillation Tower / Distillation Room / Cracker / Fluid Heater / Fluid Solidifier / Vacuum Petrochemical Processing / ???")
    static Lang uSinope1;

    @Key("ctnh.u_sinope.2")
    @CN("§c它那究极的结构已然无法让你的EMI承受，你需要寻求蓝图的帮忙，同时在修改结构时最好直接破坏主方块以避免检测卡死游戏")
    @EN("§cIts ultimate structure is too much for your EMI to bear; seek help from blueprints. When modifying the structure, it is best to break the controller directly to avoid detection freezing the game")
    static Lang uSinope2;

    @Key("ctnh.u_sinope.3")
    @CN("巨型的结构只能在真空建立，否则巨大的结构将会使周围坍缩（效率减少99.99%）")
    @EN("This giant structure can only be built in a vacuum; otherwise the surrounding area will collapse (efficiency reduced by 99.99%)")
    static Lang uSinope3;

    @Key("ctnh.u_sinope.4")
    @CN("除非你使用四维工程学材料，否则它无法再承受UIV即以上的线圈，效率将减少99.99%")
    @EN("Unless four-dimensional engineering materials are used, it cannot withstand coils at UIV or above; efficiency is reduced by 99.99%")
    static Lang uSinope4;

    @Key("ctnh.u_sinope.5")
    @CN("线圈等级决定了最大的配方等级，你最大只能使用线圈电压等级+1的配方等级，否则效率减少99%")
    @EN("Coil tier determines the maximum recipe tier. You can use at most a recipe tier one above the coil voltage tier; otherwise efficiency is reduced by 99%")
    static Lang uSinope5;

    @Key("ctnh.u_sinope.6")
    @CN("允许使用激光仓，但你的配方电压等级必须达到OPV，否则效率将减少99%")
    @EN("Laser hatches are allowed, but the recipe voltage tier must reach OPV; otherwise efficiency is reduced by 99%")
    static Lang uSinope6;

    @Key("ctnh.u_sinope.7")
    @CN("对于常规配方，该巨构拥有8^（电压等级）的并行，最高不超过2^32，在能源仓等级达到OPV时解锁无损超频，配方等级每超过UHV一级，处理速度+555%,每100点并行使处理速度增加333%,如果使用了四维工程学材料，则速度额外增加5000%")
    @EN("For normal recipes, this multiblock has 8^(voltage tier) parallel operations, capped at 2^32. Lossless overclocking unlocks when the energy hatch reaches OPV. Each recipe tier above UHV adds 555% processing speed; every 100 parallels adds 333% processing speed. Four-dimensional engineering materials add another 5000% speed")
    static Lang uSinope7;

    @Key("ctnh.u_sinope.8")
    @CN("对于该巨构特有的配方类型具有特殊机制：时间固定为100秒，并行固定为10，电压每超过UHV一级，则时间减少10秒，并行增加10,如果使用了四维工程学材料且线圈等级大于等于UIV，则时间固定为1秒")
    @EN("This multiblock's unique recipe types use special mechanics: time is fixed at 100 seconds and parallelism at 10; for each voltage tier above UHV, time decreases by 10 seconds and parallelism increases by 10. If four-dimensional engineering materials are used and the coil tier is at least UIV, time is fixed at 1 second")
    static Lang uSinope8;

    @Key("block.ctnhcore.ev_personal_computer")
    @CN("§5EV§r个人计算机")
    @EN("§5Advanced Personal Computer III§r")
    static Lang blockEvPersonalComputer;

    @Key("block.ctnhcore.hv_personal_computer")
    @CN("§6HV§r个人计算机")
    @EN("§6Advanced Personal Computer II§r")
    static Lang blockHvPersonalComputer;

    @Key("block.ctnhcore.iv_personal_computer")
    @CN("§9IV§r个人计算机")
    @EN("§9Elite Personal Computer §r")
    static Lang blockIvPersonalComputer;

    @Key("block.ctnhcore.luv_personal_computer")
    @CN("§dLuV§r个人计算机")
    @EN("§dElite Personal Computer II§r")
    static Lang blockLuvPersonalComputer;

    @Key("block.ctnhcore.lv_personal_computer")
    @CN("§7LV§r个人计算机")
    @EN("Basic Personal Computer §r")
    static Lang blockLvPersonalComputer;

    @Key("block.ctnhcore.mv_personal_computer")
    @CN("§bMV§r个人计算机")
    @EN("§bAdvanced Personal Computer §r")
    static Lang blockMvPersonalComputer;

    @Key("block.ctnhcore.opv_personal_computer")
    @CN("§9§lOpV§r个人计算机")
    @EN("§9§lLegendary Personal Computer §r")
    static Lang blockOpvPersonalComputer;

    @Key("block.ctnhcore.uev_personal_computer")
    @CN("§aUEV§r个人计算机")
    @EN("§aEpic Personal Computer II§r")
    static Lang blockUevPersonalComputer;

    @Key("block.ctnhcore.uhv_personal_computer")
    @CN("§4UHV§r个人计算机")
    @EN("§4Epic Personal Computer §r")
    static Lang blockUhvPersonalComputer;

    @Key("block.ctnhcore.uiv_personal_computer")
    @CN("§2UIV§r个人计算机")
    @EN("§2Epic Personal Computer III§r")
    static Lang blockUivPersonalComputer;

    @Key("block.ctnhcore.uv_personal_computer")
    @CN("§3UV§r个人计算机")
    @EN("§3Ultimate Personal Computer §r")
    static Lang blockUvPersonalComputer;

    @Key("block.ctnhcore.uxv_personal_computer")
    @CN("§eUXV§r个人计算机")
    @EN("§eEpic Personal Computer IV§r")
    static Lang blockUxvPersonalComputer;

    @Key("block.ctnhcore.zpm_personal_computer")
    @CN("§cZPM§r个人计算机")
    @EN("§cElite Personal Computer III§r")
    static Lang blockZpmPersonalComputer;

    @Key("block.ctnhcore.max_parallel_hatch")
    @CN("§c§lMAX§r并行控制仓")
    @EN("MAX Parallel Control Hatch")
    static Lang blockMaxParallelHatch;

    @Key("block.ctnhcore.opv_parallel_hatch")
    @CN("§9§lOpV§r并行控制仓")
    @EN("Eternal Parallel Control Hatch")
    static Lang blockOpvParallelHatch;

    @Key("block.ctnhcore.uev_parallel_hatch")
    @CN("§aUEV§r并行控制仓")
    @EN("Epic Parallel Control Hatch")
    static Lang blockUevParallelHatch;

    @Key("block.ctnhcore.uhv_parallel_hatch")
    @CN("§4UHV§r并行控制仓")
    @EN("Epic Parallel Control Hatch")
    static Lang blockUhvParallelHatch;

    @Key("block.ctnhcore.uiv_parallel_hatch")
    @CN("§2UIV§r并行控制仓")
    @EN("Epic Parallel Control Hatch")
    static Lang blockUivParallelHatch;

    @Key("block.ctnhcore.uxv_parallel_hatch")
    @CN("§eUXV§r并行控制仓")
    @EN("Legendary Parallel Control Hatch")
    static Lang blockUxvParallelHatch;

    @Key("block.ctnhcore.hv_energy_output_hatch_4a")
    @CN("4安§6HV§r动力仓")
    @EN("§6HV 4A Dynamo Hatch")
    static Lang blockHvEnergyOutputHatch4a;

    @Key("block.ctnhcore.lv_energy_output_hatch_4a")
    @CN("4安§7LV§r动力仓")
    @EN("§7LV 4A Dynamo Hatch")
    static Lang blockLvEnergyOutputHatch4a;

    @Key("block.ctnhcore.mv_energy_output_hatch_4a")
    @CN("4安§bMV§r动力仓")
    @EN("§bMV 4A Dynamo Hatch")
    static Lang blockMvEnergyOutputHatch4a;

    @Key("block.ctnhcore.lv_rotor_holder")
    @CN("§7LV§r转子支架")
    @EN("§7LV Rotor Holder")
    static Lang blockLvRotorHolder;

    @Key("block.ctnhcore.mv_rotor_holder")
    @CN("§bMV§r转子支架")
    @EN("§bMV Rotor Holder")
    static Lang blockMvRotorHolder;

    @Key("block.ctnhcore.ulv_rotor_holder")
    @CN("§8ULV§r转子支架")
    @EN("§8ULV Rotor Holder")
    static Lang blockUlvRotorHolder;

    @Key("block.ctnhcore.uhv_neuro_compiler")
    @CN("§4UHV§r神经拟合仓")
    @EN("§4UHV Neuro Compiler")
    static Lang blockUhvNeuroCompiler;

    @Key("block.ctnhcore.uv_neuro_compiler")
    @CN("§3UV§r神经拟合仓")
    @EN("§3UV Neuro Compiler")
    static Lang blockUvNeuroCompiler;

    @Key("block.ctnhcore.sterile_cleanroom_maintenance_hatch")
    @CN("无菌超净间维护仓")
    @EN("Sterile Cleanroom Maintenance Hatch")
    static Lang blockSterileCleanroomMaintenanceHatch;

    @Key("block.ctnhcore.drone_holder")
    @CN("无人机支架")
    @EN("drone Holder")
    static Lang blockDroneHolder;

    @Key("block.ctnhcore.mv_dehydrator")
    @CN("§b高级脱水机 §r")
    @EN("§bAdvanced Dehydrator §r")
    static Lang blockMvDehydrator;

    @Key("block.ctnhcore.hv_dehydrator")
    @CN("§6高级脱水机 II§r")
    @EN("§6Advanced Dehydrator II§r")
    static Lang blockHvDehydrator;

    @Key("block.ctnhcore.ev_dehydrator")
    @CN("§5高级脱水机 III§r")
    @EN("§5Advanced Dehydrator III§r")
    static Lang blockEvDehydrator;

    @Key("block.ctnhcore.iv_dehydrator")
    @CN("§9精英脱水机 §r")
    @EN("§9Elite Dehydrator §r")
    static Lang blockIvDehydrator;

    @Key("block.ctnhcore.luv_dehydrator")
    @CN("§d精英脱水机 II§r")
    @EN("§dElite Dehydrator II§r")
    static Lang blockLuvDehydrator;

    @Key("block.ctnhcore.zpm_dehydrator")
    @CN("§c精英脱水机 III§r")
    @EN("§cElite Dehydrator III§r")
    static Lang blockZpmDehydrator;

    @Key("block.ctnhcore.ev_naquadah_reactor")
    @CN("§5高级硅岩发电机 I")
    @EN("§5Advanced Naquadah Reactor III§r")
    static Lang blockEvNaquadahReactor;

    @Key("block.ctnhcore.iv_naquadah_reactor")
    @CN("§9精英硅岩发电机 II")
    @EN("§9Elite Naquadah Reactor §r")
    static Lang blockIvNaquadahReactor;

    @Key("block.ctnhcore.luv_naquadah_reactor")
    @CN("§d精英硅岩发电机 III")
    @EN("§dElite Naquadah Reactor II§r")
    static Lang blockLuvNaquadahReactor;

    @Key("block.ctnhcore.zpm_naquadah_reactor")
    @CN("§c精英硅岩发电机 IV")
    @EN("§cElite Naquadah Reactor III§r")
    static Lang blockZpmNaquadahReactor;

    @Key("block.ctnhcore.uv_naquadah_reactor")
    @CN("§3终极硅岩发电机 V")
    @EN("§3Ultimate Naquadah Reactor §r")
    static Lang blockUvNaquadahReactor;

    @Key("block.ctnhcore.ev_rocket_engine")
    @CN("§5高级火箭引擎发电机 I")
    @EN("§5Advanced Rocket Engine III§r")
    static Lang blockEvRocketEngine;

    @Key("block.ctnhcore.iv_rocket_engine")
    @CN("§9精英火箭引擎发电机 II")
    @EN("§9Elite Rocket Engine §r")
    static Lang blockIvRocketEngine;

    @Key("block.ctnhcore.luv_rocket_engine")
    @CN("§d精英火箭引擎发电机 III")
    @EN("§dElite Rocket Engine II§r")
    static Lang blockLuvRocketEngine;

    @Key("block.ctnhcore.ulv_neutron_accelerator")
    @CN("§8ULV 中子加速器")
    @EN("§8ULV Neutron Accelerator")
    static Lang blockUlvNeutronAccelerator;

    @Key("block.ctnhcore.lv_neutron_accelerator")
    @CN("§7LV 中子加速器")
    @EN("§7LV Neutron Accelerator")
    static Lang blockLvNeutronAccelerator;

    @Key("block.ctnhcore.mv_neutron_accelerator")
    @CN("§bMV 中子加速器")
    @EN("§bMV Neutron Accelerator")
    static Lang blockMvNeutronAccelerator;

    @Key("block.ctnhcore.hv_neutron_accelerator")
    @CN("§6HV 中子加速器")
    @EN("§6HV Neutron Accelerator")
    static Lang blockHvNeutronAccelerator;

    @Key("block.ctnhcore.ev_neutron_accelerator")
    @CN("§5EV 中子加速器")
    @EN("§5EV Neutron Accelerator")
    static Lang blockEvNeutronAccelerator;

    @Key("block.ctnhcore.iv_neutron_accelerator")
    @CN("§9IV 中子加速器")
    @EN("§9IV Neutron Accelerator")
    static Lang blockIvNeutronAccelerator;

    @Key("block.ctnhcore.luv_neutron_accelerator")
    @CN("§dLuV 中子加速器")
    @EN("§dLuV Neutron Accelerator")
    static Lang blockLuvNeutronAccelerator;

    @Key("block.ctnhcore.zpm_neutron_accelerator")
    @CN("§cZPM 中子加速器")
    @EN("§cZPM Neutron Accelerator")
    static Lang blockZpmNeutronAccelerator;

    @Key("block.ctnhcore.uv_neutron_accelerator")
    @CN("§3UV 中子加速器")
    @EN("§3UV Neutron Accelerator")
    static Lang blockUvNeutronAccelerator;

    @Key("block.ctnhcore.lv_digital_miner")
    @CN("数字型采矿机")
    @EN("Basic Digital Miner §r")
    static Lang blockLvDigitalMiner;

    @Key("block.ctnhcore.mv_digital_miner")
    @CN("§b进阶数字型采矿机§r")
    @EN("§bAdvanced Digital Miner §r")
    static Lang blockMvDigitalMiner;

    @Key("block.ctnhcore.hv_digital_miner")
    @CN("§6进阶数字型采矿机 II§r")
    @EN("§6Advanced Digital Miner II§r")
    static Lang blockHvDigitalMiner;

    @Key("block.ctnhcore.mv_oxygen_enricher")
    @CN("§b氧气富集器§r")
    @EN("§bAdvanced Oxygen Enricher §r")
    static Lang blockMvOxygenEnricher;

    @Key("block.ctnhcore.hv_oxygen_enricher")
    @CN("§6进阶氧气富集器§r")
    @EN("§6Advanced Oxygen Enricher II§r")
    static Lang blockHvOxygenEnricher;

    @Key("block.ctnhcore.ev_oxygen_enricher")
    @CN("§5高级氧气富集器§r")
    @EN("§5Advanced Oxygen Enricher III§r")
    static Lang blockEvOxygenEnricher;

    @Key("itemGroup.ctnhcore.machine")
    @CN("CTNH机器")
    @EN("CTNH Machines")
    static Lang itemgroupMachine;

    @Key("itemGroup.ctnhcore.item")
    @CN("CTNH物品")
    @EN("CTNH Items")
    static Lang itemgroupItem;

    @Key("itemGroup.ctnhcore.block")
    @CN("CTNH方块")
    @EN("CTNH Blocks")
    static Lang itemgroupBlock;

    @Key("block.ctnhcore.underfloor_heating_system")
    @CN("地暖")
    @EN("Underfloor Heating System")
    static Lang blockUnderfloorHeatingSystem;

    @Key("block.ctnhcore.astronomical_observatory")
    @CN("天文台")
    @EN("Astronomical Observatory")
    static Lang blockAstronomicalObservatory;

    @Key("block.ctnhcore.photovoltaic_power_station_energetic")
    @CN("充能光伏发电站")
    @EN("Charged Photovoltaic Power Station")
    static Lang blockPhotovoltaicPowerStationEnergetic;

    @Key("block.ctnhcore.photovoltaic_power_station_pulsating")
    @CN("脉冲光伏发电站")
    @EN("Photovoltaic Power Station Pulsating")
    static Lang blockPhotovoltaicPowerStationPulsating;

    @Key("block.ctnhcore.photovoltaic_power_station_vibrant")
    @CN("振动光伏发电站")
    @EN("Photovoltaic Power Station Vibrant")
    static Lang blockPhotovoltaicPowerStationVibrant;

    @Key("block.ctnhcore.wind_power_array")
    @CN("风力发电阵列")
    @EN("Wind Power Array")
    static Lang blockWindPowerArray;

    @Key("block.ctnhcore.advanced_wind_power_array")
    @CN("进阶风力发电阵列")
    @EN("Advanced Wind Power Array")
    static Lang blockAdvancedWindPowerArray;

    @Key("block.ctnhcore.super_wind_power_array")
    @CN("超级风力发电阵列")
    @EN("Super Wind Power Array")
    static Lang blockSuperWindPowerArray;

    @Key("block.ctnhcore.slaughter_house")
    @CN("屠宰场")
    @EN("Slaughter House")
    static Lang blockSlaughterHouse;

    @Key("block.ctnhcore.coke_tower")
    @CN("焦化塔")
    @EN("Coke Tower")
    static Lang blockCokeTower;

    @Key("block.ctnhcore.plasma_condenser")
    @CN("等离子冷凝器")
    @EN("Plasma Condenser")
    static Lang blockPlasmaCondenser;

    @Key("block.ctnhcore.bedrock_drilling_rigs")
    @CN("基岩钻机")
    @EN("Bedrock Drilling Rigs")
    static Lang blockBedrockDrillingRigs;

    @Key("block.ctnhcore.naq_reactor_mk3")
    @CN("超级硅岩反应堆")
    @EN("Super Naquadah Reactor")
    static Lang blockNaqReactorMk3;

    @Key("block.ctnhcore.sweat_shop")
    @CN("§4血汗工厂")
    @EN("§4Sweatshop")
    static Lang blockSweatShop;

    @Key("block.ctnhcore.meadow")
    @CN("§6牧场")
    @EN("Meadow")
    static Lang blockMeadow;

    @Key("block.ctnhcore.fermenting_tank")
    @CN("发酵罐")
    @EN("Fermenting Tank")
    static Lang blockFermentingTank;

    @Key("block.ctnhcore.large_fermenting_tank")
    @CN("大型发酵罐")
    @EN("Large Fermenting Tank")
    static Lang blockLargeFermentingTank;

    @Key("block.ctnhcore.digestion_tank")
    @CN("化粪池")
    @EN("Digestion Tank")
    static Lang blockDigestionTank;

    @Key("block.ctnhcore.blaze_blast_furnace")
    @CN("§c炽焱高炉")
    @EN("§cBlazing Blast Furnace")
    static Lang blockBlazeBlastFurnace;

    @Key("block.ctnhcore.large_bottle")
    @CN("发酵瓶")
    @EN("Fermentation Bottle")
    static Lang blockLargeBottle;

    @Key("block.ctnhcore.super_ebf")
    @CN("超级电力高炉")
    @EN("Super Electric Blast Furnace")
    static Lang blockSuperEbf;

    @Key("block.ctnhcore.mega_lcr")
    @CN("巨型化学反应釜")
    @EN("Mega Large Chemical Reactor")
    static Lang blockMegaLcr;

    @Key("block.ctnhcore.ev_chemical_generator")
    @CN("化学能发电机")
    @EN("Chemical Power Generator")
    static Lang blockEvChemicalGenerator;

    @Key("block.ctnhcore.iv_chemical_generator")
    @CN("化学能吞噬者")
    @EN("Chemical Energy Devourer")
    static Lang blockIvChemicalGenerator;

    @Key("block.ctnhcore.mega_oil_cracking_unit")
    @CN("巨型原油裂解厂")
    @EN("Mega Oil Cracking Unit")
    static Lang blockMegaOilCrackingUnit;

    @Key("block.ctnhcore.industrial_primitive_blast_furnace")
    @CN("工业土高炉")
    @EN("Industrial Primitive Blast Furnace")
    static Lang blockIndustrialPrimitiveBlastFurnace;

    @Key("block.ctnhcore.void_miner")
    @CN("虚空采矿场")
    @EN("Void Miner")
    static Lang blockVoidMiner;

    @Key("block.ctnhcore.sintering_kiln")
    @CN("烧结窑")
    @EN("Sintering Kiln")
    static Lang blockSinteringKiln;

    @Key("block.ctnhcore.chemical_vapor_deposition_machine")
    @CN("化学气相沉积器")
    @EN("Chemical Vapor Deposition Machine")
    static Lang blockChemicalVaporDepositionMachine;

    @Key("block.ctnhcore.martial_morality_eye")
    @CN("武德之眼")
    @EN("Martial Morality Eye")
    static Lang blockMartialMoralityEye;

    @Key("block.ctnhcore.advanced_coke_oven")
    @CN("高级焦炉")
    @EN("Advanced Coke Oven")
    static Lang blockAdvancedCokeOven;

    @Key("block.ctnhcore.dimensional_gas_collection_chamber")
    @CN("维度集气室")
    @EN("Dimensional Gas Collection Chamber")
    static Lang blockDimensionalGasCollectionChamber;

    @Key("block.ctnhcore.condensing_discrete")
    @CN("冷凝离散塔")
    @EN("Condensing Discrete Tower")
    static Lang blockCondensingDiscrete;

    @Key("block.ctnhcore.ion_exchanger")
    @CN("离子交换机")
    @EN("Ion Exchanger")
    static Lang blockIonExchanger;

    @Key("block.ctnhcore.large_steel_furnace")
    @CN("大型钢制熔炉")
    @EN("Large Steel Furnace")
    static Lang blockLargeSteelFurnace;

    @Key("block.ctnhcore.large_steel_alloy_furnace")
    @CN("大型钢制合金炉")
    @EN("Large Steel Alloy Furnace")
    static Lang blockLargeSteelAlloyFurnace;

    @Key("block.ctnhcore.decay_pools_machine")
    @CN("衰变罐")
    @EN("Decay Pool")
    static Lang blockDecayPoolsMachine;

    @Key("block.ctnhcore.fuel_refining_factory")
    @CN("燃料精炼厂")
    @EN("Fuel Refining Factory")
    static Lang blockFuelRefiningFactory;

    @Key("block.ctnhcore.vacuum_sintering_tower")
    @CN("真空烧结厂")
    @EN("Vacuum Sintering Tower")
    static Lang blockVacuumSinteringTower;

    @Key("block.ctnhcore.crystallizer")
    @CN("结晶器")
    @EN("Crystallizer")
    static Lang blockCrystallizer;

    @Key("block.ctnhcore.seawater_desalting_factory")
    @CN("海水晒盐工厂")
    @EN("Seawater Desalting Factory")
    static Lang blockSeawaterDesaltingFactory;

    @Key("block.ctnhcore.bio_reactor")
    @CN("生物反应器")
    @EN("Bio Reactor")
    static Lang blockBioReactor;

    @Key("block.ctnhcore.super_centrifuge")
    @CN("超速离心机")
    @EN("Super Centrifuge")
    static Lang blockSuperCentrifuge;

    @Key("block.ctnhcore.ultrasonic_apparatus")
    @CN("超声破碎仪")
    @EN("Ultrasonic Disruptor")
    static Lang blockUltrasonicApparatus;

    @Key("block.ctnhcore.ultimate_combustion_engine")
    @CN("无尽内燃引擎")
    @EN("Ultimate Combustion Engine")
    static Lang blockUltimateCombustionEngine;

    @Key("block.ctnhcore.silica_rock_fuel_refinery")
    @CN("硅岩燃料精炼厂")
    @EN("Naquadah Fuel Refinery")
    static Lang blockSilicaRockFuelRefinery;

    @Key("block.ctnhcore.nanogenetor")
    @CN("纳米摩擦发电机")
    @EN("Nanoscale Triboelectric Generator")
    static Lang blockNanogenetor;

    @Key("block.ctnhcore.forest_sea_tree_farm")
    @CN("林海树场")
    @EN("Forest Sea Tree Farm")
    static Lang blockForestSeaTreeFarm;

    @Key("block.ctnhcore.sinope_chemical")
    @CN("SINOPE化工厂")
    @EN("SINOPE Chemical Plant")
    static Lang blockSinopeChemical;

    @Key("block.ctnhcore.wide_particle_accelerator")
    @CN("广粒子加速器")
    @EN("Wide Particle Accelerator")
    static Lang blockWideParticleAccelerator;

    @Key("block.ctnhcore.arc_generator")
    @CN("电弧撕裂者")
    @EN("Arc Ripper")
    static Lang blockArcGenerator;

    @Key("block.ctnhcore.arc_cell")
    @CN("电弧发生器")
    @EN("Arc Generator")
    static Lang blockArcCell;

    @Key("block.ctnhcore.advance_assembly_line")
    @CN("进阶装配线")
    @EN("Advanced Assembly Line")
    static Lang blockAdvanceAssemblyLine;

    @Key("block.ctnhcore.cultivationroom")
    @CN("培养室")
    @EN("Cultivation Room")
    static Lang blockCultivationroom;

    @Key("block.ctnhcore.mechanical_lathe")
    @CN("机械车床厂")
    @EN("Mechanical Lathe")
    static Lang blockMechanicalLathe;

    @Key("block.ctnhcore.mechanical_centrifuge")
    @CN("机械离心厂")
    @EN("Mechanical Centrifuge")
    static Lang blockMechanicalCentrifuge;

    @Key("block.ctnhcore.mechanical_extractor")
    @CN("机械熔炼厂")
    @EN("Mechanical Smelter")
    static Lang blockMechanicalExtractor;

    @Key("block.ctnhcore.mechanical_mixer")
    @CN("机械搅拌厂")
    @EN("Mechanical Mixer")
    static Lang blockMechanicalMixer;

    @Key("block.ctnhcore.mechanical_sifter")
    @CN("机械筛选厂")
    @EN("Mechanical Sifter")
    static Lang blockMechanicalSifter;

    @Key("block.ctnhcore.mechanical_pressor")
    @CN("机械辊压厂")
    @EN("Mechanical Pressor")
    static Lang blockMechanicalPressor;

    @Key("block.ctnhcore.superconducting_penning_trap")
    @CN("超导潘宁势阱")
    @EN("Superconducting Penning Trap")
    static Lang blockSuperconductingPenningTrap;

    @Key("block.ctnhcore.arc_generator_mk1")
    @CN("超压电弧撕裂者MK1")
    @EN("Overpressure Arc Ripper Mk1")
    static Lang blockArcGeneratorMk1;

    @Key("block.ctnhcore.arc_generator_mk2")
    @CN("过载电弧撕裂者MK1")
    @EN("Overloaded Arc Ripper Mk1")
    static Lang blockArcGeneratorMk2;

    @Key("block.ctnhcore.plasma_alloy_blast_smelter")
    @CN("等离子合金冶炼转底炉")
    @EN("Plasma Alloy Rotary Kiln")
    static Lang blockPlasmaAlloyBlastSmelter;

    @Key("block.ctnhcore.combined_vapor_deposition_facility")
    @CN("集成沉积工厂")
    @EN("Combined Vapor Deposition Facility")
    static Lang blockCombinedVaporDepositionFacility;

    @Key("block.ctnhcore.space_photovoltai_cbase_station")
    @CN("太空光伏基站")
    @EN("Space Photovoltaic Base Station")
    static Lang blockSpacePhotovoltaiCbaseStation;

    @Key("block.ctnhcore.lasersorder")
    @CN("激光分配仪")
    @EN("Laser Sorter")
    static Lang blockLasersorder;

    @Key("block.ctnhcore.photovoltaic_drone_station")
    @CN("光伏无人机道标基站")
    @EN("Photovoltaic Drone Beacon Station")
    static Lang blockPhotovoltaicDroneStation;

    @Key("block.ctnhcore.hot_coolant_turbine")
    @CN("热冷却涡轮")
    @EN("Hot Coolant Turbine")
    static Lang blockHotCoolantTurbine;

    @Key("block.ctnhcore.gas_centrifuge")
    @CN("气体离心机")
    @EN("Gas Centrifuge")
    static Lang blockGasCentrifuge;

    @Key("block.ctnhcore.cryotheum_freezer")
    @CN("凛冰冷冻机")
    @EN("Cryotheum Freezer")
    static Lang blockCryotheumFreezer;

    @Key("block.ctnhcore.hyper_plasma_turbine")
    @CN("超極等离子涡轮")
    @EN("Hyper Plasma Turbine")
    static Lang blockHyperPlasmaTurbine;

    @Key("block.ctnhcore.neruo_martix_compiler")
    @CN("神经矩阵编译器")
    @EN("Neural Matrix Compiler")
    static Lang blockNeruoMartixCompiler;

    @Key("block.ctnhcore.uhv_fluid_drilling_inf")
    @CN("无尽流体钻机")
    @EN("Infinite Fluid Drill")
    static Lang blockUhvFluidDrillingInf;

    @Key("block.ctnhcore.greenhouse")
    @CN("温室")
    @EN("Greenhouse")
    static Lang blockGreenhouse;

    @Key("block.ctnhcore.cnc_alloy_smelter")
    @CN("数控合金冶炼炉")
    @EN("CNC ALLOY Smelter")
    static Lang blockCncAlloySmelter;

    @Key("gtceu.underfloor_heating_system")
    @CN("地暖")
    @EN("Underfloor Heating")
    static Lang gtceuUnderfloorHeatingSystem;

    @Key("gtceu.astronomical_observatory")
    @CN("天文台")
    @EN("Astronomical Observatory")
    static Lang gtceuAstronomicalObservatory;

    @Key("gtceu.photovoltaic_power")
    @CN("光伏发电")
    @EN("Photovoltaic Powering")
    static Lang gtceuPhotovoltaicPower;

    @Key("gtceu.slaughter_house")
    @CN("屠宰场")
    @EN("Slaughter House")
    static Lang gtceuSlaughterHouse;

    @Key("gtceu.big_dam")
    @CN("大坝")
    @EN("Big Dam")
    static Lang gtceuBigDam;

    @Key("gtceu.coke_oven")
    @CN("焦炉")
    @EN("Coke Oven")
    static Lang gtceuCokeOven;

    @Key("gtceu.naq_mk1")
    @CN("超级燃料")
    @EN("Super Fuel")
    static Lang gtceuNaqMk1;

    @Key("gtceu.bedrock_drilling_rigs")
    @CN("基岩钻机")
    @EN("Bedrock Drilling Rigs")
    static Lang gtceuBedrockDrillingRigs;

    @Key("gtceu.plasma_condenser")
    @CN("等离子冷凝")
    @EN("Plasma Condensation")
    static Lang gtceuPlasmaCondenser;

    @Key("ctnh.multiblock.mana_turbine.info.efficiency")
    @CN("发电效率：%d%%")
    @EN("Generating Efficiency：%d%%")
    static Lang manaTurbineInfoEfficiency;

    @Key("ctnh.multiblock.mana_turbine.info.consumption_rate")
    @CN("消耗速率：%d")
    @EN("Consumption Rate：%d")
    static Lang manaTurbineInfoConsumptionRate;

    @Key("ctnh.multiblock.demon_generator.info.default")
    @CN("专精增益：无")
    @EN("Specialization Boost: None")
    static Lang demonGeneratorInfoDefault;

    @Key("ctnh.multiblock.demon_generator.info.vengeful")
    @CN("专精增益：复仇")
    @EN("Specialization Boost: Vengeful")
    static Lang demonGeneratorInfoVengeful;

    @Key("ctnh.multiblock.demon_generator.info.corrosive")
    @CN("专精增益：腐蚀")
    @EN("Specialization Boost: Corrosive")
    static Lang demonGeneratorInfoCorrosive;

    @Key("ctnh.multiblock.demon_generator.info.steadfast")
    @CN("专精增益：坚毅")
    @EN("Specialization Boost: Steadfast")
    static Lang demonGeneratorInfoSteadfast;

    @Key("ctnh.multiblock.demon_generator.info.destructive")
    @CN("专精增益：毁灭")
    @EN("Specialization Boost: Destructive")
    static Lang demonGeneratorInfoDestructive;

    @Key("ctnh.multiblock.demon_generator.info.1")
    @CN("浓度差：%s")
    @EN("Concentration Difference: %s")
    static Lang demonGeneratorInfo1;

    @Key("ctnh.multiblock.demon_generator.info.boosted")
    @CN("§b生命源质增幅已启用")
    @EN("§bLife Essence Boost Active")
    static Lang demonGeneratorInfoBoosted;

    @Key("ctnh.multiblock.super_ebf.tooltip.0")
    @CN("所有配方速度提高50%！")
    @EN("All recipes are 50% faster!")
    static Lang superEbfTooltip0;

    @Key("material.ctnhcore.holystone")
    @CN("神圣石")
    @EN("Holystone")
    static Lang materialHolystone;

    @Key("material.ctnhcore.zanite")
    @CN("紫晶石")
    @EN("Zanite")
    static Lang materialZanite;

    @Key("material.ctnhcore.ambrosium")
    @CN("神能晶")
    @EN("Ambrosium")
    static Lang materialAmbrosium;

    @Key("material.ctnhcore.skyjade")
    @CN("穹玉")
    @EN("Skyjade")
    static Lang materialSkyjade;

    @Key("material.ctnhcore.stratus")
    @CN("云母钢")
    @EN("Stratus")
    static Lang materialStratus;

    @Key("dimension.ad_astra:moon")
    @CN("月球")
    @EN("Moon")
    static Lang dimensionAdAstraMoon;

    @Key("dimension.ad_astra:mars")
    @CN("火星")
    @EN("Mars")
    static Lang dimensionAdAstraMars;

    @Key("dimension.ad_astra:venus")
    @CN("金星")
    @EN("Venus")
    static Lang dimensionAdAstraVenus;

    @Key("dimension.ad_astra:mercury")
    @CN("水星")
    @EN("Mercury")
    static Lang dimensionAdAstraMercury;

    @Key("dimension.ad_extendra:jupiter")
    @CN("木星")
    @EN("Jupiter")
    static Lang dimensionAdExtendraJupiter;

    @Key("dimension.ad_astra:glacio")
    @CN("霜原星")
    @EN("Glacio")
    static Lang dimensionAdAstraGlacio;

    @Key("dimension.twilightforest:twilight_forest")
    @CN("暮色森林")
    @EN("Twilight Forest")
    static Lang dimensionTwilightforestTwilightForest;

    @Key("dimension.aether:the_aether")
    @CN("天境")
    @EN("Aether")
    static Lang dimensionAetherTheAether;

}
