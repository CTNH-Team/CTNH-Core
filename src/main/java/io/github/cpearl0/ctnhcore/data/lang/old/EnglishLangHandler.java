package io.github.cpearl0.ctnhcore.data.lang.old;

import io.github.cpearl0.ctnhcore.api.data.material.CTNHPropertyKeys;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.lang.LangHandler;
import com.gregtechceu.gtceu.utils.FormattingUtil;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public class EnglishLangHandler {

    public static RegistrateLangProvider enLangProvider;

    public static void init(RegistrateLangProvider provider) {
        enLangProvider = provider;
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

        provider.add("message.ctnhcore.portal.invalid_dimension", "This portal can only be used in the Overworld");

        provider.add("enchantment.kubejs.vacuum_seal.desc",
                "Protects you from vacuum damage. Note: All equipped items must have this enchantment to take effect");
        provider.add("enchantment.kubejs.warming.desc", "Enhance your hot defending ability");
        provider.add("enchantment.kubejs.cooling.desc", "Enhance your cold defending ability");

        // Item Tooltip
        provider.add("ctnh.advanced_ram_wafer.tooltip", "Enhanced RAM Wafer");
        provider.add("ctnh.advanced_ram_chip.tooltip", "Advanced Random Access Memory");

        provider.add("config.jade.plugin_ctnhcore.thread_status_provider", "Thread Info");
        provider.add("config.jade.plugin_ctnhcore.recipe_logic_provider", "Recipe Logic Info");
        provider.add("config.jade.plugin_ctnhcore.recipe_output_provider", "Recipe Output Info");

        // Recipe Types
        provider.add("gtceu.underfloor_heating_system", "Underfloor Heating");
        provider.add("gtceu.astronomical_observatory", "Astronomical Observatory");
        provider.add("gtceu.photovoltaic_power", "Photovoltaic Powering");
        provider.add("gtceu.slaughter_house", "Slaughter House");
        provider.add("gtceu.big_dam", "Big Dam");
        provider.add("gtceu.coke_oven", "Coke Oven");
        provider.add("gtceu.naq_mk1", "Super Fuel");
        provider.add("gtceu.bedrock_drilling_rigs", "Bedrock Drilling Rigs");
        provider.add("gtceu.plasma_condenser", "Plasma Condensation");

        provider.add("ctnh.test_terminal.lack_error", "At %s, you need");
        provider.add("ctnh.test_terminal.wrong_error", "At %s, it should be");
        provider.add("ctnh.test_terminal.position", "(%s,%s,%s)");
        provider.add("ctnh.test_terminal.error_info", "(%s)");
        provider.add("ctnh.test_terminal.success", "Everything is OK！");

        provider.add("ctnh.testing_terminal.tooltip.1", "Use to check the error when building the multiblock");
        provider.add("ctnh.testing_terminal.tooltip.2", "Right-click the controller to show the error info");
        provider.add("ctnh.testing_terminal.tooltip.3", "Right-click with Shift to change between Normal/Flipped mode");
        provider.add("ctnh.me_advanced_terminal.tooltip.1",
                "Can build multiblock structures using items from the ME network");
        provider.add("ctnh.me_advanced_terminal.tooltip.2", "Connects to the network via ME Wireless Access Point");
        provider.add("ctnh.me_advanced_terminal.tooltip.3", "Prioritizes items from the player's inventory");

        provider.add("ctnh.tooltips.simplecomputationmachine",
                "Requires 2^(recipe level - HV) CWU/t when handling recipes which tier is no lower than HV");

        provider.add("ctnh.recipe.industrial_altar.info.0", "LP consumption/input: %.1f");

        provider.add("ctnh.recipe.quasar_eye.info.0", "Activation Cost: %.1f");
        provider.add("ctnh.recipe.quasar_eye.info.1", "Energy Tier: %d");
        provider.add("ctnh.recipe.quasar_eye.info.2", "Activation Tier: %d");

        provider.add("ctnh.recipe.hellforge.info.minimum_drain", "MinimumDrain: %s Will");
        provider.add("ctnh.recipe.hellforge.info.drain", "Drain: %s Will");

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
        provider.add("ctnh.common_tooltip.subtick_overclock",
                "When recipe runtime is less than 1 tick, parallel calculations will be performed automatically.");
        provider.add("ctnh.common_tooltip.perfect_overclock", "Perfect Overclock！");
        provider.add("ctnh.common_tooltip.steel_machine.0", "Can only use HV-grade energy hatches and below");
        provider.add("ctnh.common_tooltip.steel_machine.1", "Maximum parallelism: 32");

        // Machine Info
        provider.add("ctnh.multiblock.underfloor_heating_system.info.efficiency", "Efficiency: %d");
        provider.add("ctnh.multiblock.underfloor_heating_system.info.rate", "Rate: %s");
        provider.add("ctnh.multiblock.underfloor_heating_system.info.rate.tooltip",
                "Reduce the consumption of steam to reduce the heating power of the floor heating");
        provider.add("ctnh.multiblock.underfloor_heating_system.info.rate_modify", "Adjust rate: ");
        provider.add("ctnh.multiblock.underfloor_heating_system.info.steam_consumption", "Steam consumption rate: %d");

        provider.add("ctnh.multiblock.photovoltaic_power_station.info.night", "At night");
        provider.add("ctnh.multiblock.photovoltaic_power_station.info.invalid", "Shadowed");
        provider.add("ctnh.multiblock.photovoltaic_power_station.info.1", "Efficiency: %s%%");
        provider.add("ctnh.multiblock.photovoltaic_power_station.info.2", "Generating: %s/%s EU/t");

        provider.add("ctnh.mutliblock.wind_power_array.info.network_machine", "Network Machine Count：%d");
        provider.add("ctnh.mutliblock.wind_power_array.info.network_machine_efficiency", "Generating Efficiency: %d");
        provider.add("ctnh.mutliblock.wind_power_array.info.network_dirty", "Network will rebuild in %d second(s)");

        provider.add("ctnh.multiblock.slaughter_house.info.mobcount", "Mob Types: %d (%s)");

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

        provider.add("ctnh.multiblock.zenith_machine.info.max_parallel", "Max Parallels：%d");
        provider.add("ctnh.multiblock.zenith_machine.info.now_parallel", "Now Parallels：%d");

        provider.add("ctnh.multiblock.industrial_altar.info.current_lp", "Current LP amount: %d");
        provider.add("ctnh.multiblock.industrial_altar.info.max_lp", "Max LP amount: %d");

        provider.add("ctnh.multiblock.astronomical.info.invalid", "Can only be used at night");

        provider.add("ctnh.multiblock.sinope_chemical.info.level", "Coil Accelerating Rate: %d");
        provider.add("ctnh.multiblock.sinope_chemical.info.parallel", "Parallel Count: %d");

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
        provider.add("ctnhcore.copyright.info", "Added by CTNH");

        provider.add("ctnh.recipe_type.info", "Recipe Type：%s");

        provider.add("gtceu.multiblock.laser.tooltip", "The use of the laser chamber is permitted");

        provider.add("ctnh.multiblock.plasma_condenser.tooltip.1", "The dense air condenses into frost and dew");

        provider.add("ctnh.multiblock.forest_sea.tooltip.1", "Plant trees by hand, create shade for millennia");
        provider.add("ctnh.multiblock.forest_sea.tooltip.2",
                "The Forest Sea is a massive machine that consumes only water to produce large quantities of lumber");
        provider.add("ctnh.multiblock.forest_sea.tooltip.3", "Performs water storage check every 5 seconds");
        provider.add("ctnh.multiblock.forest_sea.tooltip.4", "When water is sufficient, increases humidity by 1%");
        provider.add("ctnh.multiblock.forest_sea.tooltip.5", "When water is insufficient, decreases humidity by 10%");
        provider.add("ctnh.multiblock.forest_sea.tooltip.6",
                "Recipe processing time remains constant, but parallel value increases with humidity and voltage tier");
        provider.add("ctnh.multiblock.forest_sea.tooltip.7", "Better than greenhouses!");

        provider.add("ctnh.multiblock.cultivation_room.tooltip.1", "Microbial incubation, fungal proliferation");
        provider.add("ctnh.multiblock.cultivation_room.tooltip.2",
                "Utilize this machine to cultivate hard-to-obtain fungi and bacteria");

        provider.add("ctnh.multiblock.sweat_shop.tooltip.0", "Means of Production and Surplus Value");
        provider.add("ctnh.multiblock.sweat_shop.tooltip.1",
                "The number of villagers in the factory determines efficiency. Recipe time x (2 / number of villagers)");
        provider.add("ctnh.multiblock.sweat_shop.tooltip.2",
                "The effective number of workers in the factory is limited by the factory size. Initial limit: 4 workers; for every 4 blocks added to the factory length, the limit increases by 1.");
        provider.add("ctnh.multiblock.sweat_shop.tooltip.3",
                "The production materials (machines) placed determine the available recipes:\nPowered Rolling Machine ---- Rolling Mill Recipes\nPowered Mixer ---- Mixer Recipes\nLathe ---- Lathe Recipes\nCentrifuge ---- Extractor Recipes\nBlaze Burner ---- Extractor Recipes\nWork Basin ---- Fluid Forming Recipes\nCrushing Wheel ---- Grinder Recipes\nPowered Saw ---- Wire Rolling Machine Recipes\nLaser Processor ---- Laser Etching Recipes");
        provider.add("ctnh.multiblock.sweat_shop.tooltip.4",
                "The number of production materials (machines) placed determines the parallelism of corresponding recipes: Parallelism = sqrt(number of machines)");
        provider.add("ctnh.multiblock.sweat_shop.tooltip.5",
                "Adding robotic arms improves the overall recipe execution speed. Recipe time x (1 / 1 + 0.25 * sqrt(number of robotic arms))");
        provider.add("ctnh.multiblock.sweat_shop.tooltip.6",
                "The diversity of machines placed improves recipe execution speed.");
        provider.add("ctnh.multiblock.sweat_shop.tooltip.7",
                "Every 5 seconds, machines consume (number of workers) servings of simple worker meals.");

        provider.add("ctnh.multiblock.naq_reactor_mk3.tooltip.1", "Vast energy, the earth trembles");
        provider.add("ctnh.multiblock.naq_reactor_mk3.tooltip.2",
                "Generates power using supercharged fuel - cannot fully consume fuel without nickel plasma");
        provider.add("ctnh.multiblock.naq_reactor_mk3.tooltip.3",
                "A power core must be present in the machine configuration");
        provider.add("ctnh.multiblock.naq_reactor_mk3.tooltip.4",
                "As the core temperature increases, power generation efficiency improves");

        provider.add("ctnh.multiblock.meadow.tooltip.0", "Automated Pasture");
        provider.add("ctnh.multiblock.meadow.tooltip.1",
                "Gains n parallel processing when provided with n× the required stress.");
        provider.add("ctnh.multiblock.meadow.tooltip.2",
                "Allows breeding of different animals at the same time.");
        provider.add("ctnh.multiblock.meadow.tooltip.3",
                "Only when the animals run away can you know that you are not farming!");

        provider.add("ctnh.multiblock.fermenting_tank.tooltip.0",
                "A tank designed specifically for microbial growth. Always keep an eye on it!");
        provider.add("ctnh.multiblock.fermenting_tank.tooltip.1",
                "Biological Growth Mechanism of the Fermenting Tank:");
        provider.add("ctnh.multiblock.fermenting_tank.tooltip.2",
                "The optimal growth temperature is between §236§r and §238§r degrees. Recipes get 1.2x efficiency at optimal temperature. The further it deviates, the lower the efficiency, down to one-third.");
        provider.add("ctnh.multiblock.fermenting_tank.tooltip.3",
                "Microbial growth follows the logistic equation. When the liquid volume in the input tank is half of its capacity, §2growth efficiency doubles§r. Efficiency is lowest when the tank is full or empty, with a minimum of 20%.");

//        provider.add("ctnh.multiblock.void_miner.tooltip.0",
//                "Harvesting heaven’s materials, digging the earth’s essence");
//        provider.add("ctnh.multiblock.void_miner.tooltip.1",
//                "The Void Miner automatically generates and extracts ores");
//        provider.add("ctnh.multiblock.void_miner.tooltip.2",
//                "If you have a huge demand for minerals, the Void Miner is an essential helper");
//        provider.add("ctnh.multiblock.void_miner.tooltip.3",
//                "Input 100,000,000mB of drilling fluid at once, Cryotheum and Pyrotheum will be consumed during temperature adjustment");
//        provider.add("ctnh.multiblock.void_miner.tooltip.4",
//                "When the temperature reaches 25,000K, the Void Miner will enter forced cooling mode. Please alternate between inputting Pyrotheum and Cryotheum to control the temperature");
//        provider.add("ctnh.multiblock.void_miner.tooltip.5",
//                "When the temperature drops to 0K, the Void Miner will return to normal operation mode");
//        provider.add("ctnh.multiblock.void_miner.tooltip.6",
//                "Initial Pyrotheum consumption is 100mb. If successfully consumed, the energy will increase by ⌊(Pyrotheum / 100)⌋, then Pyrotheum will multiply by 1.02");
//        provider.add("ctnh.multiblock.void_miner.tooltip.7",
//                "Initial Cryotheum consumption is 100mb. If successfully consumed, the energy will decrease by ⌊(Cryotheum / 100)⌋, then Cryotheum will multiply by 1.02");
//        provider.add("ctnh.multiblock.void_miner.tooltip.8",
//                "The higher the temperature, the higher the efficiency of the Void Miner");

        provider.add("ctnh.multiblock.large_fermenting_tank.tooltip.0", "Efficient Industrial Fermentation");
        provider.add("ctnh.multiblock.large_fermenting_tank.tooltip.1",
                "Can connect auxiliary structures. By attaching a large fermentation bottle with a specific liquid type, the minimum efficiency increases: Water (50%), Basic Medium (150%), Sterile Medium (200%).");

        provider.add("ctnh.multiblock.large_bottle.tooltip.0", "This is truly a large container.");
        provider.add("ctnh.multiblock.large_bottle.tooltip.1", "Can store up to 10,000 buckets of liquid.");
        provider.add("ctnh.multiblock.large_bottle.tooltip.2",
                "When used with a large fermenting tank, its liquid will be consumed at a rate of §e100mb/s§r.");

        provider.add("ctnh.multiblock.digestion_tank.tooltip.0", "Actually, it produces very valuable materials...");
        provider.add("ctnh.multiblock.digestion_tank.tooltip.1", "Composting Mechanism of the Digestion Tank:");
        provider.add("ctnh.multiblock.digestion_tank.tooltip.2",
                "The optimal growth temperature is between §236§r and §238§r degrees. Recipes get 1.2x efficiency at optimal temperature. The further it deviates, the lower the efficiency, down to one-third.");

        provider.add("ctnh.multiblock.blaze_blast_furnace.tooltip.0", "Faster than an electric blast furnace.");
        provider.add("ctnh.multiblock.blaze_blast_furnace.tooltip.1",
                "Base consumption is §a10mB§r of Blazing Pyrotheum per second. For each voltage tier above §6HV§r, the consumption doubles.");
        provider.add("ctnh.multiblock.blaze_blast_furnace.tooltip.2", "Consumes 0.75x energy.");
        provider.add("ctnh.multiblock.blaze_blast_furnace.tooltip.3", "Allows processing of 8 recipes simultaneously.");

        provider.add("ctnh.multiblock.large_steel_furnace.tooltip.0", "Steel Furnace");

        provider.add("ctnh.multiblock.large_steel_alloy_furnace.tooltip.0", "Steel Alloy Furnace");

        provider.add("ctnh.multiblock.advanced_coke_oven.tooltip.0", "Advanced Blast Furnace");
        provider.add("ctnh.multiblock.advanced_coke_oven.tooltip.1", "§6§lComes with 32 parallelism");
        provider.add("ctnh.multiblock.advanced_coke_oven.tooltip.2",
                "Can only run blast furnace recipes, and recipe time is fixed at 15 seconds");
        provider.add("ctnh.multiblock.advanced_coke_oven.tooltip.3",
                "Produces a large amount of coke products and phenolic oil");
        provider.add("ctnh.multiblock.advanced_coke_oven.tooltip.4", "§c§lCannot use blast furnace cells");

        provider.add("ctnh.multiblock.large_gas_collection_chamber.tooltip.0", "Dimensional Gas Collection Chamber");
        provider.add("ctnh.multiblock.large_gas_collection_chamber.tooltip.1",
                "This machine can collect gases from any dimension");
        provider.add("ctnh.multiblock.large_gas_collection_chamber.tooltip.2",
                "Since its output is large, it is recommended to use an ME Output Assembly to collect the products");

        provider.add("ctnh.multiblock.underfloor_heating_system.tooltip.0", "Warm your heart with steam");
        provider.add("ctnh.multiblock.underfloor_heating_system.tooltip.1",
                "The underfloor heating system uses steam for heating. Occupying one chunk, it can heat §aa 5×5 chunk area§r around it. Heating only works within 10 blocks above the system");
        provider.add("ctnh.multiblock.underfloor_heating_system.tooltip.2",
                "Copper brick tiles will rust over time, reducing the heating efficiency of the system when rusted");
        provider.add("ctnh.multiblock.underfloor_heating_system.tooltip.3",
                "Adjustable rate allows reducing heating power and steam consumption, with minimum setting at 25%");

        provider.add("ctnh.multiblock.super_ebf.tooltip.0", "All recipes are 50% faster!");

        provider.add("ctnh.multiblock.slaughter_house.tooltip.0", "A merciless killing machine");
        provider.add("ctnh.multiblock.slaughter_house.tooltip.1",
                "When powered spawners are placed in the input bus, the machine will automatically output corresponding mob drops. Multiple powered spawners can be inserted");
        provider.add("ctnh.multiblock.slaughter_house.tooltip.2",
                "Each voltage tier increase adds +4 to virtual spawn count (HV provides 4)");
        provider.add("ctnh.multiblock.slaughter_house.tooltip.3",
                "Higher mob health and armor values will increase processing time");
        provider.add("ctnh.multiblock.slaughter_house.tooltip.4",
                "Weapon damage and enchantments will reduce processing time");
        provider.add("ctnh.multiblock.slaughter_house.tooltip.5", "Fortune and other enchantments also take effect");

        provider.add("ctnh.multiblock.industrial_primitive_blast_furnace.tooltip.0",
                "A more powerful primitive blast furnace, your best helper for steelmaking");
        provider.add("ctnh.multiblock.industrial_primitive_blast_furnace.tooltip.1",
                "The industrial primitive blast furnace will continuously heat up while running a recipe, and will cool down rapidly once the process is stopped");
        provider.add("ctnh.multiblock.industrial_primitive_blast_furnace.tooltip.2",
                "The higher the temperature, the higher the parallelism of the industrial primitive blast furnace, up to a maximum of 8 parallelism");
        provider.add("ctnh.multiblock.industrial_primitive_blast_furnace.tooltip.3",
                "The higher the temperature, the higher the efficiency of the industrial primitive blast furnace, up to a maximum of double efficiency");

        provider.add("ctnh.multiblock.sintering_kiln.tooltip.0",
                "Requires 8,192 Stress to activate internal pistons for compacting materials");

        provider.add("ctnh.multiblock.decay_pools.tooltip.0", "Decay");
        provider.add("ctnh.multiblock.decay_pools.tooltip.1",
                "When the circuit board is set to 0, the machine is unpowered and world acceleration is disabled.");
        provider.add("ctnh.multiblock.decay_pools.tooltip.2",
                "When the circuit board is set to 1, the machine is powered and world acceleration is enabled.");
        provider.add("ctnh.multiblock.decay_pools.tooltip.3", "Accelerates the decay process.");

        provider.add("ctnh.multiblock.vacuum_sintering_tower.tooltip.0", "Vacuum Sintering");

        provider.add("ctnh.multiblock.crystallizer.tooltip.0", "Professional Crystallization");
        provider.add("ctnh.multiblock.crystallizer.tooltip.1",
                "The crystallizer completes crystal recipes more efficiently.");
        provider.add("ctnh.multiblock.crystallizer.tooltip.2", "Efficiency improves as the coil level increases.");
        provider.add("ctnh.multiblock.crystallizer.tooltip.3",
                "Can process chemical vapor deposition recipes and some autoclave recipes.");
        provider.add("ctnh.multiblock.crystallizer.tooltip.4", "The best assistant for saving materials.");

        provider.add("ctnh.multiblock.desalting_factory.tooltip.0",
                "Drying salt out of seawater—eco-friendly, isn't it?");

        provider.add("ctnh.multiblock.water_power_station.tooltip.0", "Eco-Friendly Energy!");
        provider.add("ctnh.multiblock.water_power_station.tooltip.1",
                "Power generation is proportional to the amount of water within a radius equal to the machine length and height of 4, centered on the controller.");
        provider.add("ctnh.multiblock.water_power_station.tooltip.2",
                "Power output fluctuates randomly between a multiplier of 0.6 to 1.");

        provider.add("ctnh.multiblock.bio_reactor.tooltip.0", "A big tank");

        provider.add("ctnh.computer.a1",
                "§cAll great works require§4 sacrifice§r§j to forge. Other beings may not understand, but they will§4 obey§r.");
        provider.add("ctnh.computer.a2", "Machine type: §cSynapse Refining Machine");
        provider.add("ctnh.computer.a3",
                "Uses other intelligent beings as §4wetware§r for computation, gaining massive processing power, even converting them into wetware.");
        provider.add("ctnh.computer.a4", "Mechanism introduction placeholder");
        provider.add("ctnh.computer.a5",
                "This machine will overload the brains of all intelligent beings. §4Inevitable§r §4permanent damage§r to their brains,§4 no drops§r will be left.");
        provider.add("ctnh.computer.a6",
                "For beings like villagers, who are §7low-intelligence§r new humans, their life and intellect are too low. We need§c smarter, cuter, and more life-capable beings§r.");
        provider.add("ctnh.computer.a7", "For endless knowledge, we must§4 make all necessary sacrifices§4.");

        provider.add("ctnh.multiblock.martial_morality_eye.tooltip.0", "Poor version of the Primordial Eye");
        provider.add("ctnh.multiblock.martial_morality_eye.tooltip.1",
                "Consumes 64000mb of steam and 64 raw stones in the early stages");
        provider.add("ctnh.multiblock.martial_morality_eye.tooltip.2",
                "Produces ores from the Overworld, Twilight Forest, and the Moon");
        provider.add("ctnh.multiblock.martial_morality_eye.tooltip.3",
                "Unlocks more recipes as the voltage level increases");
        provider.add("ctnh.multiblock.martial_morality_eye.tooltip.4",
                "More useful than falling stars in the early stages");
        provider.add("ctnh.multiblock.martial_morality_eye.tooltip.5",
                "The center of the structure seems to emanate a mysterious force, filled with an aura of danger. Stay away!");
        provider.add("ctnh.multiblock.martial_morality_eye.tooltip.6", "Structure source: Twist Space Technology");

        provider.add("ctnh.multiblock.large_miner_zpm.tooltip.0", "Heard you're worried about the source of minerals?");

        provider.add("ctnh.multiblock.astronomical.tooltip.0",
                "Knowing the heavens is easy, but defying them is difficult");
        provider.add("ctnh.multiblock.astronomical.tooltip.1",
                "Cannot work under direct sunlight, but will automatically collect data for the chips in the chip bus while working");

        provider.add("ctnh.multiblock.sinope_chemical.tooltip.0",
                "From §bA certain mysterious eastern country§r's industrial power.");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.1",
                "Gray employees don’t deceive gray employees, parallel is real.");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.2",
                "No shell level requirements, recipes don't need catalysts.");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.3", "Parallel count is related to the central block.");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.4", "Silicon rock block: 8 parallel");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.5", "Enriched silicon rock block: 32 parallel");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.6", "Super silicon rock block: 128 parallel");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.7",
                "Each point of actual parallel reduces energy consumption and operation time by 0.5%, up to a maximum reduction of 25% (independently multiplied)");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.8",
                "Each coil providing 1800K increases the operation speed by +50%");
        provider.add("ctnh.multiblock.sinope_chemical.tooltip.9", "§cAny false parallel will be punished!§r");

        provider.add("ctnh.multiblock.nano_generator.tooltip.0", "Utilize the power of friction heat");
        provider.add("ctnh.multiblock.nano_generator.tooltip.1", "Maximum parallel count: 1024");
        provider.add("ctnh.multiblock.nano_generator.tooltip.2",
                "For each parallel count, the overall power generation increases by 0.2%\nActual operation time is recipe time * sqrt(parallel count)");
        provider.add("ctnh.multiblock.nano_generator.tooltip.3",
                "Inserting specific materials into the machine can enhance the multiplier, but also has a chance of consumption\nNo material: 0.4 multiplier\nRubber sheet: 0.8 multiplier, consumption chance per 512 parallel count\nPolyethylene sheet: 1.2 multiplier, consumption chance per 1024 parallel count\nSilicone rubber sheet: 2.0 multiplier, consumption chance per 4096 parallel count\nStyrene-butadiene rubber sheet: 3.2 multiplier, consumption chance per 65535 parallel count\nPolybenzimidazole sheet: 5 multiplier, consumption chance per 1048576 parallel count");

        provider.add("ctnh.multiblock.photovoltaic_power_station_energetic.tooltip.0", "Basic Solar Power Generation");
        provider.add("ctnh.multiblock.photovoltaic_power_station_energetic.tooltip.1", "§eBase Output:§r 512 EU/t");
        provider.add("ctnh.multiblock.photovoltaic_power_station_energetic.tooltip.2",
                "Operates only during daytime. Efficiency varies across dimensions. Base output reflects noon in the Overworld");

        provider.add("ctnh.multiblock.photovoltaic_power_station_pulsating.tooltip.0",
                "Advanced Solar Power Generation");
        provider.add("ctnh.multiblock.photovoltaic_power_station_pulsating.tooltip.1", "§eBase Output:§r 2048 EU/t");
        provider.add("ctnh.multiblock.photovoltaic_power_station_pulsating.tooltip.2",
                "Operates only during daytime. Efficiency varies across dimensions. Base output reflects noon in the Overworld");

        provider.add("ctnh.multiblock.photovoltaic_power_station_vibrant.tooltip.0", "Ultimate Solar Power Generation");
        provider.add("ctnh.multiblock.photovoltaic_power_station_vibrant.tooltip.1", "§eBase Output:§r 8192 EU/t");
        provider.add("ctnh.multiblock.photovoltaic_power_station_vibrant.tooltip.2",
                "Operates only during daytime. Efficiency varies across dimensions. Base output reflects noon in the Overworld");

        provider.add("ctnh.multiblock.ion_exchanger.tooltip.0", "Ion Exchange");

        provider.add("ctnh.multiblock.coke_tower.tooltip.0",
                "Boasts formidable coking capacity to fuel your petrochemical production line!");
        provider.add("ctnh.multiblock.coke_tower.tooltip.1", "Delivers blistering speeds rivaling industrial furnaces");

        provider.add("ctnh.multiblock.wide_accelerator.tooltip.0", "Particle Accelerator Integrator");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.1",
                "Supports §9laser pods§r and §avoltage converter pods§r, cannot be overclocked");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.2",
                "Accelerates three particle types simultaneously through triple beamlines");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.3",
                "Requires particle velocity exceeding recipe specifications, with maximum threshold of 50GeV");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.4",
                "§bParallel control pods§r enable customized recipe/acceleration parallelism, otherwise uses default values");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.5",
                "Default parallelism: 16 (standard recipes without §bcontrol pods§r)");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.6", "Insufficient particle velocity yields no products");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.7", "Operational logic varies by selected recipe mode");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.8",
                "ACCELERATION MODE: Default 1024-parallelism. Energy cost increases 10% per 100MeV combined velocity. §9Particle velocity remains constant§r during operation");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.9",
                "DECELERATION MODE: Default single-parallelism. Energy cost increases 2.5% per 100MeV. Reduces particle velocity by sqrt(required velocity) post-operation");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.10",
                "WARNING: Extreme power consumption. Improper voltage may cause §crecipe failure§r or §ccircuit tripping§r. Recommended with §9laser pods§r. Reduce parallelism if malfunction occurs");
        provider.add("ctnh.multiblock.wide_accelerator.tooltip.11",
                "Particle transfer available via containment links. §cCAUTION: Hazardous recipes require pre-installed containment systems§r (Currently conceptual)");

        provider.add("ctnh.multiblock.greenhouse.tooltip.0", "Plant In Room");

        provider.add("ctnhcore.src.sacrifice_empty", "No sacrifices found");
        provider.add("ctnhcore.src.sacrifice_locked", "Sacrifice LOCKED");
        provider.add("ctnhcore.src.sacrifice_unlocked", "Sacrifice UNLOCKED");
        provider.add("ctnhcore.src.wetware_duration", "Wetware duration: %s ticks");
        provider.add("ctnhcore.src.sacrifice", "Sacrifices: %s");
        provider.add("ctnh.multiblock.hyper_plasma_turbine.tooltip0",
                "§5FINAL FANTASY §fof §a Precise Computation §f and §e Plasma Energy");
        provider.add("ctnh.multiblock.hyper_plasma_turbine.tooltip1",
                "Provide %d computation to reach the Base Production. For every %d computation provided, the Output Production DOUBLES.");
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
        provider.add("ctnh.multiblock.wind_array.tooltip9",
                "§fAligned structure within a distance of <= 1 form a Wind Network.");
        provider.add("ctnh.multiblock.wind_array.tooltip10", "§fLubricant will be extracted from the network.");
        provider.add("ctnh.multiblock.wind_array.tooltip11",
                "§5DO OBEY the rules to resist the DESTRUCTION from nature force.");

        provider.add("ctnhcore.machine.high_performance_computer.tooltip.0", "§3Fetal OVERCLOCKING?");
        provider.add("ctnhcore.machine.high_performance_computer.tooltip.1",
                "§rProvider %d computation when energy is sufficient.");

        provider.add("ctnh.machine.naquadah_reactor.tooltip", "Efficiency: %s%%");
        provider.add("ctnh.machine.rocket_engine.tooltip", "Efficiency: %s%%");

        //tconstruct
        provider.add("modifier.ctnhcore.global_traveller", "Global Traveller");
        
        provider.add("ctnhcore.machine.digital_miner.tooltip.0", "§7From GTMThings, faster speed and no mining pipes, only mining ores");
        provider.add("ctnhcore.machine.digital_miner.tooltip.1", "§bForce loading self chunk while working");
        provider.add("ctnhcore.machine.digital_miner.tooltip.2", "Uses §f%d EU/t §7while working, each block takes §f%d§7 ticks");

        translateOreVein("kaolinite_vein");
        translateOreVein("wollastonite_vein");
        translateOreVein("galena_vein_tf");
        translateOreVein("sapphire_vein_tf");
        translateOreVein("olivine_vein_tf");
        translateOreVein("nickel_vein_tf");
        translateOreVein("diamond_vein_tf");
        translateOreVein("lapis_vein_tf");
        translateOreVein("molybdenite_vein_tf");
        translateOreVein("coal_vein_tf");
        translateOreVein("lubricant_vein_tf");
        translateOreVein("gold_vein_tf");
        translateOreVein("iron_vein_tf");
        translateOreVein("apatite_vein_tf");
        translateOreVein("salts_vein_tf");
        translateOreVein("cassiterite_vein_tf");
        translateOreVein("monazite_vein_n");
        translateOreVein("bauxite_vein");
        translateOreVein("ilmenite_vein");
        translateOreVein("quartzite_vein");
        translateOreVein("molybdenum_vein_ad");
        translateOreVein("galena_vein_ad");
        translateOreVein("copper_vein_ad");
        translateOreVein("cassiterite_vein_ad");
        translateOreVein("desh_vein_ad");
        translateOreVein("ostrum_vein_ad");
        translateOreVein("arsenic_vein_ad");
        translateOreVein("pitchblende_vein_ad");
        translateOreVein("tuff_uraninite_vein_ad");
        translateOreVein("scheelite_vein_ad");
        translateOreVein("sulfur_vein_ad");
        translateOreVein("redstone_vein_ad");
        translateOreVein("nickel_vein_ad");
        translateOreVein("magnetite_vein_ad");
        translateOreVein("iron_vein_ad");
        translateOreVein("beryllium_vein_ad");
        translateOreVein("tetrahedrite_vein_ad");
        translateOreVein("salts_vein_ad");
        translateOreVein("naquadah_vein_ad_mars");
        translateOreVein("chromium_vein_ad");
        translateOreVein("uranium238_vein_ad");
        translateOreVein("magnesite_vein_ad");
        translateOreVein("platinum_vein_ad");
        translateOreVein("lapis_vein_ad");
        translateOreVein("olivine_vein_ad");
        translateOreVein("manganese_vein_ad");
        translateOreVein("lubricant_vein_ad");
        translateOreVein("saltpeter_vein_ad");
        translateOreVein("calorite_vein_ad");
        translateOreVein("rutile_vein_ad");
        translateOreVein("iridium_vein_ad");
        translateOreVein("pyrolusite_vein_ad");
        translateOreVein("naquadah_vein_ad");
        translateOreVein("osmium_vein_ad");
        translateOreVein("neutronium_vein_ad");
        translateOreVein("niobium_vein_ad");
    }

    private static void translateMaterial(Material material, String en) {
        if (material == null) return;
        var enName = FormattingUtil.toEnglishName(material.getName());
        enLangProvider.add(material.getUnlocalizedName(), enName);
    }

    private static void translateOreVein(String key) {
        enLangProvider.add(key, FormattingUtil.toEnglishName(key));
    }
}
