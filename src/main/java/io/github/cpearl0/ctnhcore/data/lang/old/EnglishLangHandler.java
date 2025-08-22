package io.github.cpearl0.ctnhcore.data.lang.old;

import com.gregtechceu.gtceu.data.lang.LangHandler;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import io.github.cpearl0.ctnhcore.registry.CTNHMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHMultiblockMachines;
import io.github.cpearl0.ctnhcore.registry.CTNHTagPrefixes;

public class EnglishLangHandler {
    public static void init(RegistrateLangProvider provider) {
        LangHandler.replace(provider, CTNHMaterials.Moonstone.getUnlocalizedName(), "Moon Stone");
        LangHandler.replace(provider, CTNHMaterials.Marsstone.getUnlocalizedName(), "Mars Stone");
        LangHandler.replace(provider, CTNHMaterials.Venusstone.getUnlocalizedName(), "Venus Stone");
        LangHandler.replace(provider, CTNHMaterials.Mercurystone.getUnlocalizedName(), "Mercury Stone");
        LangHandler.replace(provider, CTNHMaterials.Glaciostone.getUnlocalizedName(), "Glacio Stone");

        LangHandler.replace(provider, CTNHMaterials.Holystone.getUnlocalizedName(), "Holystone");
        LangHandler.replace(provider, CTNHMaterials.Zanite.getUnlocalizedName(), "Zanite");
        LangHandler.replace(provider, CTNHMaterials.Ambrosium.getUnlocalizedName(), "Ambrosium");
        LangHandler.replace(provider, CTNHMaterials.Skyjade.getUnlocalizedName(), "Skyjade");
        LangHandler.replace(provider, CTNHMaterials.Stratus.getUnlocalizedName(), "Stratus");

        provider.add("enchantment.kubejs.vacuum_seal.desc", "Protects you from vacuum damage. Note: All equipped items must have this enchantment to take effect");
        provider.add("enchantment.kubejs.warming.desc", "Enhance your hot defending ability");
        provider.add("enchantment.kubejs.cooling.desc", "Enhance your cold defending ability");

        //Item Tooltip
        provider.add("ctnh.advanced_ram_wafer.tooltip", "Enhanced RAM Wafer");
        provider.add("ctnh.advanced_ram_chip.tooltip", "Advanced Random Access Memory");

        //Recipe Types
        provider.add("gtceu.underfloor_heating_system", "Underfloor Heating");
        provider.add("gtceu.astronomical_observatory", "Astronomical Observatory");
        provider.add("gtceu.photovoltaic_power", "Photovoltaic Powering");
        provider.add("gtceu.slaughter_house", "Slaughter House");
        provider.add("gtceu.big_dam", "Big Dam");
        provider.add("gtceu.coke_oven", "Coke Oven");
        provider.add("gtceu.naq_mk1", "Super Fuel");
        provider.add("gtceu.bedrock_drilling_rigs", "Bedrock Drilling Rigs");
        provider.add("gtceu.plasma_condenser", "Plasma Condensation");

        provider.add("ctnh.test_terminal.lack_error","At %s, you need");
        provider.add("ctnh.test_terminal.wrong_error","At %s, it should be");
        provider.add("ctnh.test_terminal.position","(%s,%s,%s)");
        provider.add("ctnh.test_terminal.error_info","(%s)");
        provider.add("ctnh.test_terminal.success","Everything is OK！");

        provider.add("ctnh.testing_terminal.tooltip.1","Use to check the error when building the multiblock");
        provider.add("ctnh.testing_terminal.tooltip.2","Right-click the controller to show the error info");
        provider.add("ctnh.me_advanced_terminal.tooltip.1", "Can build multiblock structures using items from the ME network");
        provider.add("ctnh.me_advanced_terminal.tooltip.2", "Connects to the network via ME Wireless Access Point");
        provider.add("ctnh.me_advanced_terminal.tooltip.3", "Prioritizes items from the player's inventory");

        provider.add("ctnh.tooltips.simplecomputationmachine", "Requires 2^(recipe level - HV) CWU/t when handling recipes which tier is no lower than HV");

        provider.add("ctnh.recipe.industrial_altar.info.0", "LP consumption/input: %.1f");

        provider.add("ctnh.recipe.quasar_eye.info.0", "Activation Cost: %.1f");
        provider.add("ctnh.recipe.quasar_eye.info.1", "Energy Tier: %d");
        provider.add("ctnh.recipe.quasar_eye.info.2", "Activation Tier: %d");

        provider.add("ctnh.recipe.hellforge.info.minimum_drain", "MinimumDrain: %s Will");
        provider.add("ctnh.recipe.hellforge.info.drain", "Drain: %s Will");

        provider.add("ctnh.recipe.kinetic.info.stress_output", "Output Stress: %d");
        provider.add("ctnh.recipe.kinetic.info.stress_input", "Input Stress: %d");

        provider.add("ctnh.recipe.accelerator.mode.nu", "Mode: Neutron Acceleration");
        provider.add("ctnh.recipe.accelerator.mode.proton", "Mode: Proton Acceleration");
        provider.add("ctnh.recipe.accelerator.mode.element", "Mode: Electron Acceleration");
        provider.add("ctnh.recipe.accelerator.mode.element.consume", "Accelerated Particle: Electron");
        provider.add("ctnh.recipe.accelerator.mode.proton.consume", "Accelerated Particle: Proton");
        provider.add("ctnh.recipe.accelerator.mode.nu.consume", "Accelerated Particle: Neutron");
        provider.add("ctnh.recipe.accelerator.mode.speed.m", "Required Velocity: %.2f MeV");
        provider.add("ctnh.recipe.accelerator.mode.speed.g", "Required Velocity: %.2f GeV");

        // Common Tooltips
        provider.add("ctnh.common_tooltip.parallel_hatch", "Voltage levels increase the number of parallels");
        provider.add("ctnh.common_tooltip.subtick_overclock", "When recipe runtime is less than 1 tick, parallel calculations will be performed automatically.");
        provider.add("ctnh.common_tooltip.perfect_overclock", "Perfect Overclock！");
        provider.add("ctnh.common_tooltip.steel_machine.0", "Can only use HV-grade energy hatches and below");
        provider.add("ctnh.common_tooltip.steel_machine.1", "Maximum parallelism: 32");

        provider.add("ctnh.common_tooltip.mana_machine.0", "Magic, isn't it?");
        provider.add("ctnh.common_tooltip.mana_machine.1", "§cMana Machines no longer have any parallelism");
        provider.add("ctnh.common_tooltip.mana_machine.2", "Each operational parallel provides 1% time and energy reduction, up to 75% maximum reduction");
        provider.add("ctnh.common_tooltip.mana_machine.3", "§4When voltage is below LUV and recipe voltage tier matches current voltage, increases processing time by 50% (Mana Assembly only increases by 1%)");
        provider.add("ctnh.common_tooltip.mana_machine.4", "Insert §5Quasar Rune§r to activate §5Eye of Astral Mode§r for 100 recipes: Parallelism becomes unlimited, but no longer provides additional time or voltage reduction. Activating this mode doesn't consume the Quasar Rune");

        provider.add("ctnh.common_tooltip.mana_generator.0", "Max power output = (Recipe base output) * (Rune multiplier) * (Rotor max RPM) * (Rotor efficiency/100) * (Machine bonus multiplier)");
        provider.add("ctnh.common_tooltip.mana_generator.1", "Actual output = (Current rotor RPM / Max rotor RPM)^2 * Max power output");
        provider.add("ctnh.common_tooltip.mana_generator.2", "§cWarning: Requires continuous Mana fluid consumption. Insufficient Mana will reduce output to 1/5 of normal. Check UI for consumption rate.");
        provider.add("ctnh.common_tooltip.mana_generator.3", "Inserting Runes into the machine boosts generation efficiency:\n" +
                " Tier I Rune: Output×1.5, Mana cost×0.8, 20% decay chance per 5s\n" +
                " Tier II Rune: Output×2.4, Mana cost×1.2, 10% decay chance per 5s\n" +
                " Tier III Rune: Output×3, Mana cost×0.8, 5% decay chance per 5s\n" +
                " Tier IV Rune: Output×4, Mana cost×0.6, 2.5% decay chance per 5s\n" +
                " Tier V Rune: Output×5, Mana cost×0.3, 2% decay chance per 5s\n" +
                " §5Quasar Rune§r: Output×999, Cost×999, §cBursts with final brilliance amidst devoured stars§r");
        provider.add("ctnh.common_tooltip.basic_mana_consume", "Base consumption is 4mB of Liquid Mana per second. For each voltage tier above §7LV§r, the consumption doubles.");
        provider.add("ctnh.common_tooltip.advanced_mana_consume", "Base consumption is 10mB of Liquid Mana per second. For each voltage tier above §7LV§r, the consumption doubles.");
        provider.add("ctnh.common_tooltip.super_mana_consume", "Base consumption is 12mB of Liquid Mana per second. For each voltage tier above §7LV§r, the consumption doubles.");

        provider.add("ctnh.common_tooltip.zenith_machine.0", "§5Transcendent Magic");
        provider.add("ctnh.common_tooltip.zenith_machine.1", "After reaching LUV voltage, if §5Zenith Source Matter§r is sufficient, each operation will consume (60*(current voltage level-6)) Zenith Source Matter, gaining 2^(current voltage level-6) parallelism. The maximum parallelism depends on the current voltage. However, not inputting Zenith Source Matter will result in a loss of 4 parallelism.");
        provider.add("ctnh.common_tooltip.zenith_machine.2", "Warning: The consumption of Source Matter is unrelated to the number of items you input. Even without parallelism, your Zenith Source Matter will be deducted. After reaching the maximum parallelism, Zenith Source Matter will continue to be consumed, but the consumption rate will be fixed at 60.");

        // Machine Info
        provider.add("ctnh.multiblock.underfloor_heating_system.info.efficiency", "Efficiency: %d");
        provider.add("ctnh.multiblock.underfloor_heating_system.info.rate", "Rate: %s");
        provider.add("ctnh.multiblock.underfloor_heating_system.info.rate.tooltip", "Reduce the consumption of steam to reduce the heating power of the floor heating");
        provider.add("ctnh.multiblock.underfloor_heating_system.info.rate_modify", "Adjust rate: ");
        provider.add("ctnh.multiblock.underfloor_heating_system.info.steam_consumption", "Steam consumption rate: %d");

        provider.add("ctnh.multiblock.photovoltaic_power_station.info.night", "At night");
        provider.add("ctnh.multiblock.photovoltaic_power_station.info.invalid", "Shadowed");
        provider.add("ctnh.multiblock.photovoltaic_power_station.info.1", "Efficiency: %s%%");
        provider.add("ctnh.multiblock.photovoltaic_power_station.info.2", "Generating: %s/%s EU/t");

        provider.add("ctnh.mutliblock.wind_power_array.info.network_machine","Network Machine Count：%d");
        provider.add("ctnh.mutliblock.wind_power_array.info.network_machine_efficiency","Generating Efficiency: %d");
        provider.add("ctnh.mutliblock.wind_power_array.info.network_dirty", "Network will rebuild in %d second(s)");

        provider.add("ctnh.multiblock.slaughter_house.info.mobcount","Mob Types: %d (%s)");

        provider.add("ctnh.multiblock.fermenting_tank.info.growing_temperature", "Growth Temperature：§2%d°C§r");
        provider.add("ctnh.multiblock.fermenting_tank.info.growth_efficiency", "Growth Efficiency：%d%%");

        provider.add("ctnh.multiblock.mana_turbine.info.efficiency", "Generating Efficiency：%d%%");
        provider.add("ctnh.multiblock.mana_turbine.info.consumption_rate", "Consumption Rate：%d");

        provider.add("ctnh.multiblock.naq_reactor.info.temperature", "§cCore temperature: %d");
        provider.add("ctnh.multiblock.naq_reactor.info.nickel_consumption", "Nickel plasma consumption: %d");
        provider.add("ctnh.multiblock.naq_reactor.info.parallel_count", "Power generation parallel count: %d");

        provider.add("ctnh.multiblock.demon_generator.info.default", "Specialization Boost: None");
        provider.add("ctnh.multiblock.demon_generator.info.vengeful", "Specialization Boost: Vengeful");
        provider.add("ctnh.multiblock.demon_generator.info.corrosive", "Specialization Boost: Corrosive");
        provider.add("ctnh.multiblock.demon_generator.info.steadfast", "Specialization Boost: Steadfast");
        provider.add("ctnh.multiblock.demon_generator.info.destructive", "Specialization Boost: Destructive");
        provider.add("ctnh.multiblock.demon_generator.info.1", "Concentration Difference: %s");
        provider.add("ctnh.multiblock.demon_generator.info.boosted", "§bLife Essence Boost Active");

        provider.add("ctnh.multiblock.sweat_shop.info.villager_count", "Employee Count: %s");
        provider.add("ctnh.multiblock.sweat_shop.info.basic_rate", "Base Productivity: x%s");

        provider.add("ctnh.multiblock.void_miner.info.cryotheum", "Cryotheum consumption: %d ");
        provider.add("ctnh.multiblock.void_miner.info.pyrotheum", "Pyrotheum consumption: %d ");
        provider.add("ctnh.multiblock.void_miner.info.overheat", "Overheating!!!");

        provider.add("ctnh.multiblock.blaze_blast_furnace.info.pyrotheum", "Blazing Pyrotheum: %d mB");

        provider.add("ctnh.multiblock.mega_lcr.info.coil", "Current Coil Temperature: %s");
        provider.add("ctnh.multiblock.mega_lcr.info.speed", "Current Recipe Time Multiplier: %s");

        provider.add("ctnh.multiblock.water_power_station.info.0", "Water Flow: %d");
        provider.add("ctnh.multiblock.water_power_station.info.1", "Coil Efficiency: %d%%");
        provider.add("ctnh.multiblock.water_power_station.info.2", "Power Output: %d/%d EU/t");

        provider.add("ctnh.multiblock.forest_machine.info.humidity", "Humidity level: %d");
        provider.add("ctnh.multiblock.forest_machine.info.parallel_count", "Parallel count: %d");

        provider.add("ctnh.multiblock.zenith_machine.info.max_parallel","Max Parallels：%d");
        provider.add("ctnh.multiblock.zenith_machine.info.now_parallel","Now Parallels：%d");

        provider.add("ctnh.multiblock.industrial_altar.info.current_lp", "Current LP amount: %d");
        provider.add("ctnh.multiblock.industrial_altar.info.max_lp", "Max LP amount: %d");

        provider.add("ctnh.multiblock.quasar_eye.info.rune_energy", "Rune energy: %.2f");
        provider.add("ctnh.multiblock.quasar_eye.info.rune_consumption", "Current rune energy consumption rate: %.2f /100ticks");
        provider.add("ctnh.multiblock.quasar_eye.info.mana_model", "Current mana fuel level: %d");
        provider.add("ctnh.multiblock.quasar_eye.info.mana_production", "Current power generation efficiency: %.2f");
        provider.add("ctnh.multiblock.quasar_eye.info.quasar_parallel", "Time Parallelism: %.2f");
        provider.add("ctnh.multiblock.quasar_eye.info.consumption_parallel","Energy Consumption Rate: %.2f");
        provider.add("ctnh.multiblock.quasar_eye.info.0","Accumulated Energy: %s");

        provider.add("ctnh.multiblock.hellforge.info.will", "Will: %s");

        provider.add("ctnh.multiblock.astronomical.info.invalid", "Can only be used at night");

        provider.add("ctnh.multiblock.sinope_chemical.info.level","Coil Accelerating Rate: %d");
        provider.add("ctnh.multiblock.sinope_chemical.info.parallel","Parallel Count: %d");

        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.overload", "§cWarning: Machine Overloaded!!!");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.crash", "§cMachine has been damaged");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.mana", "Current mana: %.2f");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.twist_consumption", "Twisted rune consumption probability: %.2f");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.starlight_consumption", "Starlight rune consumption probability: %.2f");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.overload_1", "§cMachine overload degree: %d/%d");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.overload_2", "§c!!! Warning: Energy overflow!!!");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.max_mana", "Maximum mana: %.2f");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.mana_required", "Mana required: %.2f");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.mana_consumption", "Mana consumption: %.2f");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.time", "Operation time multiplier: %.2f");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.eut_consumption", "Energy consumption multiplier: %.2f");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.info.stable", "Mana stability value: %.2f");

        provider.add("ctnh.multiblock.wide_accelerator.info.nu_speed", "Neutron Velocity: %.2f MeV");
        provider.add("ctnh.multiblock.wide_accelerator.info.proton_speed", "Proton Velocity: %.2f MeV");
        provider.add("ctnh.multiblock.wide_accelerator.info.electric_speed", "Electron Velocity: %.2f MeV");
        provider.add("ctnh.multiblock.wide_accelerator.info.consume", "Power Consumption Multiplier: %.2f");

        provider.add("ctnh.multiblock.wide_accelerator.gui.electric", "Electron Beamline");
        provider.add("ctnh.multiblock.wide_accelerator.gui.nu", "Neutron Beamline");
        provider.add("ctnh.multiblock.wide_accelerator.gui.proton", "Proton Beamline");
        provider.add("ctnh.multiblock.wide_accelerator.gui.name", "Access Beamline");

        provider.add("ctnh.multiblock.arcgenerator.info.0", "Max Arc Intensity: %d");
        provider.add("ctnh.multiblock.arcgenerator.info.1", "Current Arc Intensity: %d");
        provider.add("ctnh.multiblock.arcgenerator.info.2", "Max Supported Efficiency: %.2f%%");
        provider.add("ctnh.multiblock.arcgenerator.info.3", "Current Efficiency: %.2f%%");

        // Machine Tooltips
        provider.add("ctnh.copyright.info", "Added by CTNH");

        provider.add("ctnh.recipe_type.info", "Recipe Type：%s");

        provider.add("gtceu.multiblock.laser.tooltip", "The use of the laser chamber is permitted");

        provider.add("ctnh.multiblock.plasma_condenser.tooltip.1","The dense air condenses into frost and dew");

        provider.add("ctnh.multiblock.forest_sea.tooltip.1", "Plant trees by hand, create shade for millennia");
        provider.add("ctnh.multiblock.forest_sea.tooltip.2", "The Forest Sea is a massive machine that consumes only water to produce large quantities of lumber");
        provider.add("ctnh.multiblock.forest_sea.tooltip.3", "Performs water storage check every 5 seconds");
        provider.add("ctnh.multiblock.forest_sea.tooltip.4", "When water is sufficient, increases humidity by 1%");
        provider.add("ctnh.multiblock.forest_sea.tooltip.5", "When water is insufficient, decreases humidity by 10%");
        provider.add("ctnh.multiblock.forest_sea.tooltip.6", "Recipe processing time remains constant, but parallel value increases with humidity and voltage tier");
        provider.add("ctnh.multiblock.forest_sea.tooltip.7", "Better than greenhouses!");

        provider.add("ctnh.multiblock.cultivation_room.tooltip.1", "Microbial incubation, fungal proliferation");
        provider.add("ctnh.multiblock.cultivation_room.tooltip.2", "Utilize this machine to cultivate hard-to-obtain fungi and bacteria");

        provider.add("ctnh.multiblock.sweat_shop.tooltip.0", "Means of Production and Surplus Value");
        provider.add("ctnh.multiblock.sweat_shop.tooltip.1", "The number of villagers in the factory determines efficiency. Recipe time x (2 / number of villagers)");
        provider.add("ctnh.multiblock.sweat_shop.tooltip.2", "The effective number of workers in the factory is limited by the factory size. Initial limit: 4 workers; for every 4 blocks added to the factory length, the limit increases by 1.");
        provider.add("ctnh.multiblock.sweat_shop.tooltip.3", "The production materials (machines) placed determine the available recipes:\nPowered Rolling Machine ---- Rolling Mill Recipes\nPowered Mixer ---- Mixer Recipes\nLathe ---- Lathe Recipes\nCentrifuge ---- Extractor Recipes\nBlaze Burner ---- Extractor Recipes\nWork Basin ---- Fluid Forming Recipes\nCrushing Wheel ---- Grinder Recipes\nPowered Saw ---- Wire Rolling Machine Recipes\nLaser Processor ---- Laser Etching Recipes");
        provider.add("ctnh.multiblock.sweat_shop.tooltip.4", "The number of production materials (machines) placed determines the parallelism of corresponding recipes: Parallelism = sqrt(number of machines)");
        provider.add("ctnh.multiblock.sweat_shop.tooltip.5", "Adding robotic arms improves the overall recipe execution speed. Recipe time x (1 / 1 + 0.25 * sqrt(number of robotic arms))");
        provider.add("ctnh.multiblock.sweat_shop.tooltip.6", "The diversity of machines placed improves recipe execution speed.");
        provider.add("ctnh.multiblock.sweat_shop.tooltip.7", "Every 5 seconds, machines consume (number of workers) servings of simple worker meals.");

        provider.add("ctnh.multiblock.demon_will_generator.tooltip.0", "Harnessing demonic power");
        provider.add("ctnh.multiblock.demon_will_generator.tooltip.1", "Generates power by utilizing the difference in demonic will concentration between the chunks on either side of the machine. The power generation increases exponentially with the concentration difference.");
        provider.add("ctnh.multiblock.demon_will_generator.tooltip.2", "Calculations are based on the will concentration at the demonic alloy blocks on either side of the machine.");
        provider.add("ctnh.multiblock.demon_will_generator.tooltip.3", "The diversity of various demonic wills in the chunks on both sides affects power generation efficiency.");
        provider.add("ctnh.multiblock.demon_will_generator.tooltip.4", "Will cores can be placed inside the machine, transforming it into a specialized mode focused on a specific type of will.");
        provider.add("ctnh.multiblock.demon_will_generator.tooltip.5",
                "The rune blocks inside the machine can be replaced to provide different enhancements:\n" +
                        "§4Sacrifice Runes and Self-Sacrifice Runes§r----Increase the power generation multiplier for the Life Essence Fortified Mode§r\n" +
                        "§3Speed Runes§r----Increase the duration of a single recipe operation (saving demonic will consumption)§r\n" +
                        "§eAugment Runes§r----Each rune increases the demonic will concentration difference by 1§r\n" +
                        "§cSupercharge Runes§r----Each rune increases the demonic will concentration difference by 5% (multiplied)§r\n" +
                        "=============================="
        );
        provider.add("ctnh.multiblock.demon_will_generator.tooltip.6",
                "Insert §4Life Essence§r to activate the Fortified Mode, doubling power output while consuming §a100mb§r of Life Essence per second."
        );

        provider.add("ctnh.multiblock.naq_reactor_mk3.tooltip.1", "Vast energy, the earth trembles");
        provider.add("ctnh.multiblock.naq_reactor_mk3.tooltip.2", "Generates power using supercharged fuel - cannot fully consume fuel without nickel plasma");
        provider.add("ctnh.multiblock.naq_reactor_mk3.tooltip.3", "A power core must be present in the machine configuration");
        provider.add("ctnh.multiblock.naq_reactor_mk3.tooltip.4", "As the core temperature increases, power generation efficiency improves");

        provider.add("ctnh.multiblock.meadow.tooltip.0", "Automated Pasture");
        provider.add("ctnh.multiblock.meadow.tooltip.1", "Can only raise chickens, cows, pigs and sheep. Cows produce leather, sheep produce wool, pigs produce pork and chickens produce eggs.");
        provider.add("ctnh.multiblock.meadow.tooltip.2", "The more animals in the pasture, the more waste they generate. The more animals you have, the more byproducts are produced.");
        provider.add("ctnh.multiblock.meadow.tooltip.3", "Only when the animals run away can you know that you are not farming!");

        provider.add("ctnh.multiblock.fermenting_tank.tooltip.0", "A tank designed specifically for microbial growth. Always keep an eye on it!");
        provider.add("ctnh.multiblock.fermenting_tank.tooltip.1", "Biological Growth Mechanism of the Fermenting Tank:");
        provider.add("ctnh.multiblock.fermenting_tank.tooltip.2", "The optimal growth temperature is between §236§r and §238§r degrees. Recipes get 1.2x efficiency at optimal temperature. The further it deviates, the lower the efficiency, down to one-third.");
        provider.add("ctnh.multiblock.fermenting_tank.tooltip.3", "Microbial growth follows the logistic equation. When the liquid volume in the input tank is half of its capacity, §2growth efficiency doubles§r. Efficiency is lowest when the tank is full or empty, with a minimum of 20%.");

        provider.add("ctnh.multiblock.void_miner.tooltip.0", "Harvesting heaven’s materials, digging the earth’s essence");
        provider.add("ctnh.multiblock.void_miner.tooltip.1", "The Void Miner automatically generates and extracts ores");
        provider.add("ctnh.multiblock.void_miner.tooltip.2", "If you have a huge demand for minerals, the Void Miner is an essential helper");
        provider.add("ctnh.multiblock.void_miner.tooltip.3", "Input 100,000,000mB of drilling fluid at once, Cryotheum and Pyrotheum will be consumed during temperature adjustment");
        provider.add("ctnh.multiblock.void_miner.tooltip.4", "When the temperature reaches 25,000K, the Void Miner will enter forced cooling mode. Please alternate between inputting Pyrotheum and Cryotheum to control the temperature");
        provider.add("ctnh.multiblock.void_miner.tooltip.5", "When the temperature drops to 0K, the Void Miner will return to normal operation mode");
        provider.add("ctnh.multiblock.void_miner.tooltip.6", "Initial Pyrotheum consumption is 100mb. If successfully consumed, the energy will increase by ⌊(Pyrotheum / 100)⌋, then Pyrotheum will multiply by 1.02");
        provider.add("ctnh.multiblock.void_miner.tooltip.7", "Initial Cryotheum consumption is 100mb. If successfully consumed, the energy will decrease by ⌊(Cryotheum / 100)⌋, then Cryotheum will multiply by 1.02");
        provider.add("ctnh.multiblock.void_miner.tooltip.8", "The higher the temperature, the higher the efficiency of the Void Miner");

        provider.add("ctnh.multiblock.large_fermenting_tank.tooltip.0", "Efficient Industrial Fermentation");
        provider.add("ctnh.multiblock.large_fermenting_tank.tooltip.1", "Can connect auxiliary structures. By attaching a large fermentation bottle with a specific liquid type, the minimum efficiency increases: Water (50%), Basic Medium (150%), Sterile Medium (200%).");

        provider.add("ctnh.multiblock.large_bottle.tooltip.0", "This is truly a large container.");
        provider.add("ctnh.multiblock.large_bottle.tooltip.1", "Can store up to 10,000 buckets of liquid.");
        provider.add("ctnh.multiblock.large_bottle.tooltip.2", "When used with a large fermenting tank, its liquid will be consumed at a rate of §e100mb/s§r.");

        provider.add("ctnh.multiblock.digestion_tank.tooltip.0", "Actually, it produces very valuable materials...");
        provider.add("ctnh.multiblock.digestion_tank.tooltip.1", "Composting Mechanism of the Digestion Tank:");
        provider.add("ctnh.multiblock.digestion_tank.tooltip.2", "The optimal growth temperature is between §236§r and §238§r degrees. Recipes get 1.2x efficiency at optimal temperature. The further it deviates, the lower the efficiency, down to one-third.");

        provider.add("ctnh.multiblock.blaze_blast_furnace.tooltip.0", "Faster than an electric blast furnace.");
        provider.add("ctnh.multiblock.blaze_blast_furnace.tooltip.1", "Base consumption is §a10mB§r of Blazing Pyrotheum per second. For each voltage tier above §6HV§r, the consumption doubles.");
        provider.add("ctnh.multiblock.blaze_blast_furnace.tooltip.2", "Consumes 0.75x energy.");
        provider.add("ctnh.multiblock.blaze_blast_furnace.tooltip.3", "Allows processing of 8 recipes simultaneously.");

        provider.add("ctnh.multiblock.large_steel_furnace.tooltip.0", "Steel Furnace");

        provider.add("ctnh.multiblock.large_steel_alloy_furnace.tooltip.0", "Steel Alloy Furnace");

        provider.add("ctnh.multiblock.advanced_coke_oven.tooltip.0", "Advanced Blast Furnace");
        provider.add("ctnh.multiblock.advanced_coke_oven.tooltip.1", "§6§lComes with 32 parallelism");
        provider.add("ctnh.multiblock.advanced_coke_oven.tooltip.2", "Can only run blast furnace recipes, and recipe time is fixed at 15 seconds");
        provider.add("ctnh.multiblock.advanced_coke_oven.tooltip.3", "Produces a large amount of coke products and phenolic oil");
        provider.add("ctnh.multiblock.advanced_coke_oven.tooltip.4", "§c§lCannot use blast furnace cells");

        provider.add("ctnh.multiblock.large_gas_collection_chamber.tooltip.0", "Dimensional Gas Collection Chamber");
        provider.add("ctnh.multiblock.large_gas_collection_chamber.tooltip.1", "This machine can collect gases from any dimension");
        provider.add("ctnh.multiblock.large_gas_collection_chamber.tooltip.2", "Since its output is large, it is recommended to use an ME Output Assembly to collect the products");

        provider.add("ctnh.multiblock.underfloor_heating_system.tooltip.0", "Warm your heart with steam");
        provider.add("ctnh.multiblock.underfloor_heating_system.tooltip.1", "The underfloor heating system uses steam for heating. Occupying one chunk, it can heat §aa 5×5 chunk area§r around it. Heating only works within 10 blocks above the system");
        provider.add("ctnh.multiblock.underfloor_heating_system.tooltip.2", "Copper brick tiles will rust over time, reducing the heating efficiency of the system when rusted");
        provider.add("ctnh.multiblock.underfloor_heating_system.tooltip.3", "Adjustable rate allows reducing heating power and steam consumption, with minimum setting at 25%");

        provider.add("ctnh.multiblock.mana_generator_turbine_tier1.tooltip.0", "Basic Mana Converter");
        provider.add("ctnh.multiblock.mana_generator_turbine_tier1.tooltip.1", "Rotor frame tier cannot exceed §bMV§r");

        provider.add("ctnh.multiblock.mana_generator_turbine_tier2.tooltip.0", "Advanced Mana Converter");
        provider.add("ctnh.multiblock.mana_generator_turbine_tier2.tooltip.1", "Rotor frame tier cannot exceed §5EV§r");
        provider.add("ctnh.multiblock.mana_generator_turbine_tier2.tooltip.2", "Consumes 2.25× fuel but generates 4× power output when operating");

        provider.add("ctnh.multiblock.mana_generator_turbine_tier3.tooltip.0", "Precision Mana Converter");
        provider.add("ctnh.multiblock.mana_generator_turbine_tier3.tooltip.1", "Rotor frame tier cannot exceed §dLuV§r");
        provider.add("ctnh.multiblock.mana_generator_turbine_tier3.tooltip.2", "Consumes 3× fuel but generates 16× power output when operating");

        provider.add("ctnh.multiblock.mana_generator_turbine_tier4.tooltip.0", "Magical Energy Conservation");
        provider.add("ctnh.multiblock.mana_generator_turbine_tier4.tooltip.1", "Rotor frame tier cannot exceed §3UV§r");
        provider.add("ctnh.multiblock.mana_generator_turbine_tier4.tooltip.2", "Consumes 4× fuel but generates 24× power output when operating");
        provider.add("ctnh.multiblock.mana_generator_turbine_tier4.tooltip.3", "Can only use Laser Cores");

        provider.add("ctnh.multiblock.zenith_laser.tooltip.0", "Allows the use of §5Inverse Etching§r, consuming §5Zenith Source Matter§r to turn chips into wafers");

        provider.add("ctnh.multiblock.zenith_circuit_assember.tooltip.0", "Allows the use of §5Magical Resonance Circuit Assembly§r to assemble resonant circuits at lower voltages and with special materials");

        provider.add("ctnh.multiblock.super_ebf.tooltip.0", "All recipes are 50% faster!");

        provider.add("ctnh.multiblock.mega_lcr.tooltip.0", "For each actual parallel operation, reduces energy consumption by 2% (up to 75% max) and processing time by 2% (up to 75% max) while the machine is running");
        provider.add("ctnh.multiblock.mega_lcr.tooltip.1", "When coil temperature exceeds 3600K, every additional 1800K provides an extra 25% speed boost");

        provider.add("ctnh.multiblock.slaughter_house.tooltip.0", "A merciless killing machine");
        provider.add("ctnh.multiblock.slaughter_house.tooltip.1", "When powered spawners are placed in the input bus, the machine will automatically output corresponding mob drops. Multiple powered spawners can be inserted");
        provider.add("ctnh.multiblock.slaughter_house.tooltip.2", "Each voltage tier increase adds +4 to virtual spawn count (HV provides 4)");
        provider.add("ctnh.multiblock.slaughter_house.tooltip.3", "Higher mob health and armor values will increase processing time");
        provider.add("ctnh.multiblock.slaughter_house.tooltip.4", "Weapon damage and enchantments will reduce processing time");
        provider.add("ctnh.multiblock.slaughter_house.tooltip.5", "Fortune and other enchantments also take effect");

        provider.add("ctnh.multiblock.industrial_primitive_blast_furnace.tooltip.0", "A more powerful primitive blast furnace, your best helper for steelmaking");
        provider.add("ctnh.multiblock.industrial_primitive_blast_furnace.tooltip.1", "The industrial primitive blast furnace will continuously heat up while running a recipe, and will cool down rapidly once the process is stopped");
        provider.add("ctnh.multiblock.industrial_primitive_blast_furnace.tooltip.2", "The higher the temperature, the higher the parallelism of the industrial primitive blast furnace, up to a maximum of 8 parallelism");
        provider.add("ctnh.multiblock.industrial_primitive_blast_furnace.tooltip.3", "The higher the temperature, the higher the efficiency of the industrial primitive blast furnace, up to a maximum of double efficiency");

        provider.add("ctnh.multiblock.sintering_kiln.tooltip.0", "Requires 8,192 Stress to activate internal pistons for compacting materials");

        provider.add("ctnh.multiblock.decay_pools.tooltip.0", "Decay");
        provider.add("ctnh.multiblock.decay_pools.tooltip.1", "When the circuit board is set to 0, the machine is unpowered and world acceleration is disabled.");
        provider.add("ctnh.multiblock.decay_pools.tooltip.2", "When the circuit board is set to 1, the machine is powered and world acceleration is enabled.");
        provider.add("ctnh.multiblock.decay_pools.tooltip.3", "Accelerates the decay process.");

        provider.add("ctnh.multiblock.vacuum_sintering_tower.tooltip.0", "Vacuum Sintering");

        provider.add("ctnh.multiblock.crystallizer.tooltip.0", "Professional Crystallization");
        provider.add("ctnh.multiblock.crystallizer.tooltip.1", "The crystallizer completes crystal recipes more efficiently.");
        provider.add("ctnh.multiblock.crystallizer.tooltip.2", "Efficiency improves as the coil level increases.");
        provider.add("ctnh.multiblock.crystallizer.tooltip.3", "Can process chemical vapor deposition recipes and some autoclave recipes.");
        provider.add("ctnh.multiblock.crystallizer.tooltip.4", "The best assistant for saving materials.");

        provider.add("ctnh.multiblock.desalting_factory.tooltip.0", "Drying salt out of seawater—eco-friendly, isn't it?");

        provider.add("ctnh.multiblock.water_power_station.tooltip.0", "Eco-Friendly Energy!");
        provider.add("ctnh.multiblock.water_power_station.tooltip.1", "Power generation is proportional to the amount of water within a radius equal to the machine length and height of 4, centered on the controller.");
        provider.add("ctnh.multiblock.water_power_station.tooltip.2", "Power output fluctuates randomly between a multiplier of 0.6 to 1.");

        provider.add("ctnh.multiblock.bio_reactor.tooltip.0", "A big tank");

        provider.add("ctnh.computer.a1", "§cAll great works require§4 sacrifice§r§j to forge. Other beings may not understand, but they will§4 obey§r.");
        provider.add("ctnh.computer.a2", "Machine type: §cSynapse Refining Machine");
        provider.add("ctnh.computer.a3", "Uses other intelligent beings as §4wetware§r for computation, gaining massive processing power, even converting them into wetware.");
        provider.add("ctnh.computer.a4", "Mechanism introduction placeholder");
        provider.add("ctnh.computer.a5", "This machine will overload the brains of all intelligent beings. §4Inevitable§r §4permanent damage§r to their brains,§4 no drops§r will be left.");
        provider.add("ctnh.computer.a6", "For beings like villagers, who are §7low-intelligence§r new humans, their life and intellect are too low. We need§c smarter, cuter, and more life-capable beings§r.");
        provider.add("ctnh.computer.a7", "For endless knowledge, we must§4 make all necessary sacrifices§4.");

        provider.add("ctnh.multiblock.martial_morality_eye.tooltip.0", "Poor version of the Primordial Eye");
        provider.add("ctnh.multiblock.martial_morality_eye.tooltip.1", "Consumes 64000mb of steam and 64 raw stones in the early stages");
        provider.add("ctnh.multiblock.martial_morality_eye.tooltip.2", "Produces ores from the Overworld, Twilight Forest, and the Moon");
        provider.add("ctnh.multiblock.martial_morality_eye.tooltip.3", "Unlocks more recipes as the voltage level increases");
        provider.add("ctnh.multiblock.martial_morality_eye.tooltip.4", "More useful than falling stars in the early stages");
        provider.add("ctnh.multiblock.martial_morality_eye.tooltip.5","The center of the structure seems to emanate a mysterious force, filled with an aura of danger. Stay away!");
        provider.add("ctnh.multiblock.martial_morality_eye.tooltip.6", "Structure source: Twist Space Technology");

        provider.add("ctnh.multiblock.industrial_altar.tooltip.0", "§4Blood Magic, right at your doorstep!");
        provider.add("ctnh.multiblock.industrial_altar.tooltip.1", "Like the Blood Altar, this structure has an LP input limit. You §4must§r use specific recipes to increase its LP§r\nSee JEI for the recipes that increase LP.");
        provider.add("ctnh.multiblock.industrial_altar.tooltip.2", "Every time the voltage exceeds HV, the LP storage limit increases by 10,000. When reaching LUV, each level increases by 30,000.");
        provider.add("ctnh.multiblock.industrial_altar.tooltip.3", "Each capacity rune increases the LP storage limit by 2500, and the enhanced capacity rune increases it by 5000. After reaching LUV, each level adds an extra 10,000/20,000 LP limit.");

        provider.add("ctnh.multiblock.quasar_eye.tooltip.0", "§9Mana's§r §cUltimate Mystery§r, a device capable of creating §5quasars§r now rests in §6your§r hands");
        provider.add("ctnh.multiblock.quasar_eye.tooltip.1", "Machine activation requires §rinitial mana fuel consumption§R, consult JEI for specific values");
        provider.add("ctnh.multiblock.quasar_eye.tooltip.2", "Activating lower-tier recipes at high energy tiers §bwaives activation costs§r");
        provider.add("ctnh.multiblock.quasar_eye.tooltip.3", "§5Rune Energy§r governs output potency. Input §bTier V Runes§r to amplify rune energy and enhance outputs. Use §5Quasar Runes§r to generate massive rune energy");
        provider.add("ctnh.multiblock.quasar_eye.tooltip.4", "Rune energy acquisition logic: §5Before each recipe cycle§r, consumes §cup to one§r of each consumable rune type");
        provider.add("ctnh.multiblock.quasar_eye.tooltip.5", "§cWarning§r: Higher rune energy accelerates §cdepletion rate§r. Efficiency §chalves§r when rune energy falls below 50!");
        provider.add("ctnh.multiblock.quasar_eye.tooltip.6", "Energy efficiency formula: log((rune_energy)/50)+1. Max efficiency: (1 + energy tier)");
        provider.add("ctnh.multiblock.quasar_eye.tooltip.7", "Features time parallelism. Both consumption and duration multiply by parallel factor (efficiency*5)");
        provider.add("ctnh.multiblock.quasar_eye.tooltip.8", "Fuel consumption formula: 1-0.05*Math.max((rune_energy-50)/50,0.75)");
        provider.add("ctnh.multiblock.quasar_eye.tooltip.9", "In generation mode, accumulates 1% of EU output into the Quasar Eye. Gains +1% accumulation per 25 rune energy");
        provider.add("ctnh.multiblock.quasar_eye.tooltip.10", "In creation mode, releases all stored EU. Advanced fuels multiply output. Every 1000E EU generates bonus gas byproduct. Creation mode disabled when stored EU <1E");
        provider.add("ctnh.multiblock.quasar_eye.tooltip.11", "§bGood news§r: This machine won't explode. §cBut no guarantees for future versions!§r");

        provider.add("ctnh.multiblock.large_miner_zpm.tooltip.0", "Heard you're worried about the source of minerals?");

        provider.add("ctnh.multiblock.eternal_well_of_suffer.tooltip.0", "§8Satan woke up to find himself demoted to second place.§r");
        provider.add("ctnh.multiblock.eternal_well_of_suffer.tooltip.1", "Enjoy the anguished screams of the suffering souls.§r");
        provider.add("ctnh.multiblock.eternal_well_of_suffer.tooltip.2", "Recipe time is always fixed at 1s. Increasing the voltage tier will boost the production of Vital Essence, equivalent to lossless overclocking.§r");
        provider.add("ctnh.multiblock.eternal_well_of_suffer.tooltip.3", "Using an incomplete Data Model yields no output. Higher model levels result in greater production.");
        provider.add("ctnh.multiblock.eternal_well_of_suffer.tooltip.4", "§bSoul Mode:§r");
        provider.add("ctnh.multiblock.eternal_well_of_suffer.tooltip.5", "In Soul Mode, the machine does not produce Vital Essence but provides Will to the §bIndustrial Hellforge§r below.");
        provider.add("ctnh.multiblock.eternal_well_of_suffer.tooltip.6", "Both machines must share the same lava pool, and the controller must be positioned directly above the Hellforge. Please consult JEI for more details.");
        provider.add("ctnh.multiblock.eternal_well_of_suffer.tooltip.7", "Outputs Will equal to the Vital Essence production (mB)/100,000.");

        provider.add("ctnh.multiblock.hellforge.tooltip.0", "§8Do machines have souls too?§r");
        provider.add("ctnh.multiblock.hellforge.tooltip.1", "Running Hellforge recipes requires meeting the minimum Will requirements.§r");
        provider.add("ctnh.multiblock.hellforge.tooltip.2", "How to fill the machine with Will:§r");
        provider.add("ctnh.multiblock.hellforge.tooltip.3", "1. Kill a mob soaked in §cVital Essence§r near the controller using the §bSword of Perception§r. Will gained is based on the mob's max health.");
        provider.add("ctnh.multiblock.hellforge.tooltip.4", "§8The Manhattan distance to the controller must be less than 8; it doesn’t necessarily have to be the central blood chalice.§r");
        provider.add("ctnh.multiblock.hellforge.tooltip.5", "2. Throw a Soulstone near the controller. The machine will absorb the Will automatically.");
        provider.add("ctnh.multiblock.hellforge.tooltip.6", "3. Use the §4Eternal Well of Suffering§r. Please refer to the tooltip of the respective machine.§r");

        provider.add("ctnh.multiblock.twisted_fusion_mk1.tooltip.0", "§8With unbelievable power.§r");
        provider.add("ctnh.multiblock.twisted_fusion_mk1.tooltip.1", "§eNuclear Fusion Reactor Mode:§r");
        provider.add("ctnh.multiblock.twisted_fusion_mk1.tooltip.2", "No start-up energy required, no room level restrictions, perform 4/2 overclocking. Provides parallel depending on recipe start-up energy:");
        provider.add("ctnh.multiblock.twisted_fusion_mk1.tooltip.3", "Less than 160MEU: 16+16*reactor level parallel");
        provider.add("ctnh.multiblock.twisted_fusion_mk1.tooltip.4", "Greater than 160MEU, less than 320MEU: 4+4*fusion reactor level parallel");
        provider.add("ctnh.multiblock.twisted_fusion_mk1.tooltip.5", "Greater than 320MEU, less than 480MEU: 1+fusion reactor level parallel");
        provider.add("ctnh.multiblock.twisted_fusion_mk1.tooltip.6", "§5Twisted Fusion Reactor Mode:§r");
        provider.add("ctnh.multiblock.twisted_fusion_mk1.tooltip.7", "Follows the law of conservation of letters.");
        provider.add("ctnh.multiblock.twisted_fusion_mk1.tooltip.8", "Perhaps this can be used to produce some §9strange things§r...");

        provider.add("ctnh.multiblock.astronomical.tooltip.0", "Knowing the heavens is easy, but defying them is difficult");
        provider.add("ctnh.multiblock.astronomical.tooltip.1", "Cannot work under direct sunlight, but will automatically collect data for the chips in the chip bus while working");

        provider.add("ctnh.multiblock.sinope_chemical.tooltip.0", "From §bA certain mysterious eastern country§r's industrial power.");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.1", "Gray employees don’t deceive gray employees, parallel is real.");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.2", "No shell level requirements, recipes don't need catalysts.");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.3", "Parallel count is related to the central block.");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.4", "Silicon rock block: 8 parallel");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.5", "Enriched silicon rock block: 32 parallel");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.6", "Super silicon rock block: 128 parallel");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.7", "Each point of actual parallel reduces energy consumption and operation time by 0.5%, up to a maximum reduction of 25% (independently multiplied)");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.8", "Each coil providing 1800K increases the operation speed by +50%");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.9", "§cAny false parallel will be punished!§r");

        provider.add("ctnh.multiblock.nano_generator.tooltip.0", "Utilize the power of friction heat");
        provider.add("ctnh.multiblock.nano_generator.tooltip.1", "Maximum parallel count: 1024");
        provider.add("ctnh.multiblock.nano_generator.tooltip.2", "For each parallel count, the overall power generation increases by 0.2%\nActual operation time is recipe time * sqrt(parallel count)");
        provider.add("ctnh.multiblock.nano_generator.tooltip.3", "Inserting specific materials into the machine can enhance the multiplier, but also has a chance of consumption\nNo material: 0.4 multiplier\nRubber sheet: 0.8 multiplier, consumption chance per 512 parallel count\nPolyethylene sheet: 1.2 multiplier, consumption chance per 1024 parallel count\nSilicone rubber sheet: 2.0 multiplier, consumption chance per 4096 parallel count\nStyrene-butadiene rubber sheet: 3.2 multiplier, consumption chance per 65535 parallel count\nPolybenzimidazole sheet: 5 multiplier, consumption chance per 1048576 parallel count");

        provider.add("ctnh.multiblock.photovoltaic_power_station_energetic.tooltip.0", "Basic Solar Power Generation");
        provider.add("ctnh.multiblock.photovoltaic_power_station_energetic.tooltip.1", "§eBase Output:§r 512 EU/t");
        provider.add("ctnh.multiblock.photovoltaic_power_station_energetic.tooltip.2", "Operates only during daytime. Efficiency varies across dimensions. Base output reflects noon in the Overworld");

        provider.add("ctnh.multiblock.photovoltaic_power_station_pulsating.tooltip.0", "Advanced Solar Power Generation");
        provider.add("ctnh.multiblock.photovoltaic_power_station_pulsating.tooltip.1", "§eBase Output:§r 2048 EU/t");
        provider.add("ctnh.multiblock.photovoltaic_power_station_pulsating.tooltip.2", "Operates only during daytime. Efficiency varies across dimensions. Base output reflects noon in the Overworld");

        provider.add("ctnh.multiblock.photovoltaic_power_station_vibrant.tooltip.0", "Ultimate Solar Power Generation");
        provider.add("ctnh.multiblock.photovoltaic_power_station_vibrant.tooltip.1", "§eBase Output:§r 8192 EU/t");
        provider.add("ctnh.multiblock.photovoltaic_power_station_vibrant.tooltip.2", "Operates only during daytime. Efficiency varies across dimensions. Base output reflects noon in the Overworld");

        provider.add("ctnh.multiblock.ion_exchanger.tooltip.0", "Ion Exchange");

        provider.add("ctnh.multiblock.coke_tower.tooltip.0", "Boasts formidable coking capacity to fuel your petrochemical production line!");
        provider.add("ctnh.multiblock.coke_tower.tooltip.1", "Delivers blistering speeds rivaling industrial furnaces");

        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.0", "§9Arcane Pivot Colossus - Reshaping the Fabric of Scale§r");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.1", "Supports parallel control pods, §cwhich don't provide recipe parallelism§r, only modifying mana input per second");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.2", "Inserted §9Tier V Runes§r determine various machine capabilities");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.3", "§9Starlight Rune§r energy reduces power consumption and enhances machine stability");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.4", "§cDistortion Rune§r energy decreases processing time and increases mana injection frequency, §cat the cost of reduced stability§r");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.5", "§dHorizon Rune§r energy significantly increases mana capacity and utilization efficiency");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.6", "§5Quasar Rune§r energy forces the machine into §coverload state§r while decupling recipe requirements, output, and voltage");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.7", "The interplay between §cDistortion§r and §9Starlight§r determines machine stability");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.8", "Stability formula: -((twist_power/3)+((mana/100000)*(Math.max(twist_power/9,1))))+starlight_power*4+5+tier. Machine overloads when stability <0!");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.9", "§cOverload§r increases by 1/sec; §crecipe duration doubles§r during overload");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.10", "Overload decreases by 1 every 3 sec when stable. §cMachine explodes at maximum overload!§r");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.11", "§cDistortion Rune§r consumption probability: Math.max((twist_power-3)/3,1)*0.01+(Math.max(starlight_power-twist_power,0)*0.01)+(Math.max((100-mana/100000)*0.0005,0)) per operation");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.12", "§9Starlight Rune§r consumption probability: Math.max((starlight_power-3)/3,1)*0.01+(Math.max(twist_power-starlight_power,0)*0.01)+(mana/100000*0.005) per operation");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.13", "§dHorizon Rune§r consumption probability: 0.0025*(horizen_power) per operation");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.14", "This machine cannot be overclocked");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.15", "Consumes 100*parallelism Kmb(B) liquid mana/sec for beam energization. Non-mana coolant recipes can power the machine");
        provider.add("ctnh.multiblock.nicoll_dyson_beams.tooltip.16", "Warning: Excess mana beyond capacity won't be refunded. Surplus mana above capacity won't be consumed during operation");

        provider.add("ctnh.multiblock.twisted_fusion_mk_infinity.tooltip.0", "§8Endless Twisted Power§r");
        provider.add("ctnh.multiblock.twisted_fusion_mk_infinity.tooltip.1", "Can use laser warehouse.");
        provider.add("ctnh.multiblock.twisted_fusion_mk_infinity.tooltip.2", "Applies §8incomprehensible§r parallel to all recipes, reducing energy consumption and operation time by 75%");
        provider.add("ctnh.multiblock.twisted_fusion_mk_infinity.tooltip.3", "§5You must be crazy to make this machine, and indeed this machine is equally crazy§r.");

        provider.add("ctnh.multiblock.wide_accelerator.tooltip.0", "Particle Accelerator Integrator");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.1", "Supports §9laser pods§r and §avoltage converter pods§r, cannot be overclocked");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.2", "Accelerates three particle types simultaneously through triple beamlines");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.3", "Requires particle velocity exceeding recipe specifications, with maximum threshold of 50GeV");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.4", "§bParallel control pods§r enable customized recipe/acceleration parallelism, otherwise uses default values");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.5", "Default parallelism: 16 (standard recipes without §bcontrol pods§r)");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.6", "Insufficient particle velocity yields no products");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.7", "Operational logic varies by selected recipe mode");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.8", "ACCELERATION MODE: Default 1024-parallelism. Energy cost increases 10% per 100MeV combined velocity. §9Particle velocity remains constant§r during operation");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.9", "DECELERATION MODE: Default single-parallelism. Energy cost increases 2.5% per 100MeV. Reduces particle velocity by sqrt(required velocity) post-operation");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.10", "WARNING: Extreme power consumption. Improper voltage may cause §crecipe failure§r or §ccircuit tripping§r. Recommended with §9laser pods§r. Reduce parallelism if malfunction occurs");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.11", "Particle transfer available via containment links. §cCAUTION: Hazardous recipes require pre-installed containment systems§r (Currently conceptual)");

        provider.add("ctnh.multiblock.mana_reactor.tooltip.0","工业魔力奠基者");
        provider.add("ctnh.multiblock.mana_reactor.tooltip.1","允许使用并行控制仓");

        provider.add("ctnh.multiblock.greenhouse.tooltip.0", "Plant In Room");

        provider.add("ctnhcore.src.sacrifice_empty","No sacrifices found");
        provider.add("ctnhcore.src.sacrifice_locked","Sacrifice LOCKED");
        provider.add("ctnhcore.src.sacrifice_unlocked","Sacrifice UNLOCKED");
        provider.add("ctnhcore.src.wetware_duration", "Wetware duration: %s ticks");
        provider.add("ctnhcore.src.sacrifice", "Sacrifices: %s");
        provider.add("ctnh.multiblock.hyper_plasma_turbine.tooltip0","§5FINAL FANTASY §fof §a Precise Computation §f and §e Plasma Energy");
        provider.add("ctnh.multiblock.hyper_plasma_turbine.tooltip1","Provide %d computation to reach the Base Production. For every %d computation provided, the Output Production DOUBLES.");
        provider.add("ctnhcore.recipe_logic.insufficient_cwut", "Insufficient Computation");

        provider.add("ctnh.multiblock.wind_array.tooltip0", "§7§oWind Wolf: The TRUE POWER of NATURE");
        provider.add("ctnh.multiblock.wind_array.tooltip1", "§8-----------------§aBasic Data§8--------------------");
        provider.add("ctnh.multiblock.wind_array.tooltip2", "§f- Basic Production: §e%d EU/t  §7(on earth)");
        provider.add("ctnh.multiblock.wind_array.tooltip3", "§f- Weather Boost: §eRainy x2, Thunder x4");
        provider.add("ctnh.multiblock.wind_array.tooltip4", "§f- Altitude Boost: §eClamp(Y-64, 0, 256-64) / (256-64)");
        provider.add("ctnh.multiblock.wind_array.tooltip5", "§f- Network Boost: §e 0.3*[log2(NetSize)]");
        provider.add("ctnh.multiblock.wind_array.tooltip6", "§fAll boosts are multiplied to determine production.");
        provider.add("ctnh.multiblock.wind_array.tooltip7", "§fRequires §e%d mB/t§f Lubricant.");
        provider.add("ctnh.multiblock.wind_array.tooltip8", "§8-----------------§aWind Network§8------------------");
        provider.add("ctnh.multiblock.wind_array.tooltip9", "§fAligned structure within a distance of <= 1 form a Wind Network.");
        provider.add("ctnh.multiblock.wind_array.tooltip10", "§fLubricant will be extracted from the network.");
        provider.add("ctnh.multiblock.wind_array.tooltip11", "§5DO OBEY the rules to resist the DESTRUCTION from nature force.");

        provider.add("ctnh.multiblock.mana_condenser.tooltips.0", "Entropy-reversal matter conversion!");
        provider.add("ctnh.multiblock.mana_condenser.tooltips.1", "Converts mana into liquid mana or vice versa - the latter requiring significantly more energy");
        provider.add("ctnh.multiblock.mana_condenser.tooltips.2", "All mana I/O operations are processed through the central mana pool in the structure");

        provider.add("ctnhcore.machine.high_performance_computer.tooltip.0", "§3Fetal OVERCLOCKING?");
        provider.add("ctnhcore.machine.high_performance_computer.tooltip.1", "§rProvider %d computation when energy is sufficient.");
    }
}
