package io.github.cpearl0.ctnhcore.data.lang;

import io.github.cpearl0.ctnhcore.registry.CTNHMachines;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.GTNNMultiblocks;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static io.github.cpearl0.ctnhcore.data.lang.CTNHLangHandler.tsl;
import static io.github.cpearl0.ctnhcore.data.lang.CTNHLangHandler.tslBlock;

public class MachineLang {
    public static void init(CTNHLangProvider provider) {
        machineInfo(provider);
        machineTooltip(provider);
        machineGui(provider);
        machineName(provider);
        tsl(provider, 
                "ctnh.recipe.condition.neutron_activator_condition_tooltip",
                "最小中子动能:\n%s MeV\n最大中子动能:\n%s MeV",
                "Min Neutron Kinetic Energy:\n%s MeV\nMax Neutron Kinetic Energy:\n%s MeV"
        );
        tsl(provider, "ctnh.recipe.condition.plant_casing.tooltip", "外壳等级: %s (%s)", "Casing: %s (%s)");
        tsl(provider, "ctnh.recipe.condition.tier_casing.desc", "外壳等级：%s", "Casing Tier: %s");
        tsl(provider, "ctnh.recipe.condition.plant_casing.tier.bronze", "青铜", "Bronze");
        tsl(provider, "ctnh.recipe.condition.plant_casing.tier.steel", "钢", "Steel");
        tsl(provider, "ctnh.recipe.condition.plant_casing.tier.aluminium", "铝", "Aluminium");
        tsl(provider, "ctnh.recipe.condition.plant_casing.tier.stainless_steel", "不锈钢", "Stainless Steel");
        tsl(provider, "ctnh.recipe.condition.plant_casing.tier.titanium", "钛", "Titanium");
        tsl(provider, "ctnh.recipe.condition.plant_casing.tier.tungsten_steel", "钨钢", "Tungsten Steel");
    }
    public static void machineName(CTNHLangProvider provider) {
        tslBlock(provider, GTNNMultiblocks.LARGE_DEHYDRATOR.getBlock(), "大型脱水机");
        tslBlock(provider, CTNHMachines.NEUTRON_SENSOR.getBlock(), "中子传感器");
        tslBlock(provider, GTNNMultiblocks.CHEMICAL_PLANT.getBlock(), "埃克森美孚化工厂");
        tslBlock(provider, GTNNMultiblocks.NEUTRON_ACTIVATOR.getBlock(), "中子活化器");
        tslBlock(provider, GTNNMultiblocks.LARGE_NAQUADAH_REACTOR.getBlock(), "大型硅岩发电堆");
        tslBlock(provider, CTNHMachines.CATALYST_HATCH.getBlock(),  "催化剂仓");
        tslBlock(provider, CTNHMachines.HIGH_SPEED_PIPE_BLOCK.getBlock(), "高速管道方块");

        tslBlock(provider, CTNHMachines.DEHYDRATOR[MV].getBlock(), "§b高级脱水机 §r");
        tslBlock(provider, CTNHMachines.DEHYDRATOR[HV].getBlock(), "§6高级脱水机 II§r");
        tslBlock(provider, CTNHMachines.DEHYDRATOR[EV].getBlock(), "§5高级脱水机 III§r");
        tslBlock(provider, CTNHMachines.DEHYDRATOR[IV].getBlock(), "§9精英脱水机 §r");
        tslBlock(provider, CTNHMachines.DEHYDRATOR[LuV].getBlock(), "§d精英脱水机 II§r");
        tslBlock(provider, CTNHMachines.DEHYDRATOR[ZPM].getBlock(), "§c精英脱水机 III§r");
        tslBlock(provider, CTNHMachines.NAQUADAH_REACTOR[EV].getBlock(), "§5高级硅岩发电机 I");
        tslBlock(provider, CTNHMachines.NAQUADAH_REACTOR[IV].getBlock(), "§9精英硅岩发电机 II");
        tslBlock(provider, CTNHMachines.NAQUADAH_REACTOR[LuV].getBlock(), "§d精英硅岩发电机 III");
        tslBlock(provider, CTNHMachines.NAQUADAH_REACTOR[ZPM].getBlock(), "§c精英硅岩发电机 IV");
        tslBlock(provider, CTNHMachines.NAQUADAH_REACTOR[UV].getBlock(), "§3终极硅岩发电机 V");
        tslBlock(provider, CTNHMachines.ROCKET_ENGINE[EV].getBlock(), "§5高级火箭引擎发电机 I");
        tslBlock(provider, CTNHMachines.ROCKET_ENGINE[IV].getBlock(), "§9精英火箭引擎发电机 II");
        tslBlock(provider, CTNHMachines.ROCKET_ENGINE[LuV].getBlock(), "§d精英火箭引擎发电机 III");
        tslBlock(provider, CTNHMachines.NEUTRON_ACCELERATOR[ULV].getBlock(), "§8ULV 中子加速器");
        tslBlock(provider, CTNHMachines.NEUTRON_ACCELERATOR[LV].getBlock(), "§7LV 中子加速器");
        tslBlock(provider, CTNHMachines.NEUTRON_ACCELERATOR[MV].getBlock(), "§bMV 中子加速器");
        tslBlock(provider, CTNHMachines.NEUTRON_ACCELERATOR[HV].getBlock(), "§6HV 中子加速器");
        tslBlock(provider, CTNHMachines.NEUTRON_ACCELERATOR[EV].getBlock(), "§5EV 中子加速器");
        tslBlock(provider, CTNHMachines.NEUTRON_ACCELERATOR[IV].getBlock(), "§9IV 中子加速器");
        tslBlock(provider, CTNHMachines.NEUTRON_ACCELERATOR[LuV].getBlock(), "§dLuV 中子加速器");
        tslBlock(provider, CTNHMachines.NEUTRON_ACCELERATOR[ZPM].getBlock(), "§cZPM 中子加速器");
        tslBlock(provider, CTNHMachines.NEUTRON_ACCELERATOR[UV].getBlock(), "§3UV 中子加速器");
    }
    public static void machineGui(CTNHLangProvider provider) {
        tsl(provider, 
                "gui.ctnh.neutron_sensor.invert.enabled",
                "输出：反转\n\n切换以反转红石逻辑\n默认情况下，中子动能介于所设定的最小值和最大值之间时传感器将发出红石信号，小于最小值时则停止发出红石信号",
                "Output: Reverse\n\nSwitch to reverse redstone logic\nBy default, the sensor will emit a redstone signal when the neutron kinetic energy is between the set minimum and maximum values, and stop emitting a redstone signal when it is less than the minimum value."
        );
        tsl(provider, 
                "gui.ctnh.neutron_sensor.invert.disabled",
                "输出：正常\n\n切换以反转红石逻辑\n默认情况下，中子动能介于所设定的最小值和最大值之间时传感器将发出红石信号，小于最小值时则停止发出红石信号",
                "Output: Normal\n\nSwitch to reverse redstone logic\nBy default, the sensor will emit a redstone signal when the neutron kinetic energy is between the set minimum and maximum values, and stop emitting a redstone signal when it is less than the minimum value."
        );

        tsl(provider, "ctnh.gui.neutron_kinetic_energy.min", "最小中子动能\n(%s)", "Min Neutron Kinetic Energy\n(%s)");
        tsl(provider, "ctnh.gui.neutron_kinetic_energy.max", "最大中子动能\n(%s)", "Max Neutron Kinetic Energy\n(%s)");
    }
    public static void machineInfo(CTNHLangProvider provider) {
        tsl(provider, "ctnh.multiblock.chemical_plant.info.parallel_level", "§5并行: %s", "§bParallel: %s");
        tsl(provider, "ctnh.multiblock.chemical_plant.info.heating_coil", "§6提速: %s%%", "§6Speed: %s%%");
        tsl(provider, "ctnh.multiblock.chemical_plant.info.tier", "§e配方电压最大支持: %s", "§eRecipe voltage maximum support:\n%s");
        tsl(provider, "ctnh.multiblock.chemical_plant.info.chance", "§6催化剂消耗概率: %s%%", "§6Catalyst consumption probability:\n%s%%");

        tsl(provider, "ctnh.multiblock.large_naquadah_reactor.info.power", "发电倍率: %s", "Power: %s");

        tsl(provider, "ctnh.multiblock.neutron_activator.info.ev", "当前中子动能: %deV", "Current Neutron Kinetic Energy: %deV");
        tsl(provider, "ctnh.multiblock.neutron_activator.info.height", "高度: %s", "Height: %s");
        tsl(provider, "ctnh.multiblock.neutron_activator.info.efficiency", "耗时: %s%%", "Efficiency: %s%%");
    }
    public static void machineTooltip(CTNHLangProvider provider) {
        tsl(provider, 
                "ctnh.multiblock.chemical_plant.tooltip.0",
                "§o§7重工业，现在就在你家门口！",
                "§o§7Heavy industry, right at your doorstep now!"
        );
        tsl(provider, "ctnh.multiblock.chemical_plant.tooltip.1", "§6线圈：§e+50%§6 速度/级", "§6Coil：§e+50%§6 speed/tier");
        tsl(provider, "ctnh.multiblock.chemical_plant.tooltip.2",
                "§b管道方块：§e+2§b 并行 及 §e-20%§b 催化剂消耗概率/级", "§bPipe：§e+2§b parallel and §e-20%§b catalyst consumption/tier");
        tsl(provider, 
                "ctnh.multiblock.chemical_plant.tooltip.3",
                "§5机械方块：配方电压支持等级",
                "§5MachineCasing：Recipe voltage support level"
        );

        tsl(provider, 
                "ctnh.machine.neutron_accelerator.tooltip.0",
                "§o§7输入EU，加速中子!",
                "§o§7Input EU to Accelerate the Neutron!"
        );
        tsl(provider, "ctnh.machine.neutron_accelerator.tooltip.1", "§6最大EU输入: %s", "§6Max EU Input: %s");
        tsl(provider, "ctnh.machine.neutron_accelerator.tooltip.2", "§6最大EU消耗: %s", "§6Max EU Cost: %s");
        tsl(provider, 
                "ctnh.machine.neutron_accelerator.tooltip.3",
                "§b每点EU都会转化为§e10~20-eV§b中子动能.",
                "§bEach EU will be converted to §e10~20-eV§b of neutron kinetic energy."
        );

        tsl(provider, "ctnh.multiblock.neutron_activator.tooltip.0", "§o§7超光速运动!", "§o§7Faster-Light Movement!");
        tsl(provider, 
                "ctnh.multiblock.neutron_activator.tooltip.1",
                "§6额外的高速管道方块提供配方时间减免，同时降低中子加速器的效率",
                "§6Extra high-speed pipe blocks provide recipe time reduction, and lower the efficiency of the neutron accelerator"
        );
        tsl(provider, 
                "ctnh.multiblock.neutron_activator.tooltip.2",
                "§6没有中子加速器运行时，中子动能每秒降低§e72KeV§6中子动能",
                "§6Without a neutron accelerator running, neutron kinetic energy decreases by §e72KeV §6neutron kinetic energy per second"
        );
        tsl(provider, 
                "ctnh.multiblock.neutron_activator.tooltip.3",
                "§6输入石墨/铍粉可以立即吸收§e10MeV§6中子动能",
                "§6Absorb §e10MeV §6neutron kinetic energy immediately when input cesium or beryllium dust"
        );
        tsl(provider, 
                "ctnh.multiblock.neutron_activator.tooltip.4",
                "§6当中子动能超过§41200MeV§6后将会爆炸！",
                "§6When the neutron kinetic energy exceeds §41200MeV§6, it will explode!"
        );

        tsl(provider, "ctnh.multiblock.large_naquadah_reactor.tooltip.0", "§o§7环境友好型!", "§o§7Environment Friendly!");
        tsl(provider, 
                "ctnh.multiblock.large_naquadah_reactor.tooltip.1",
                "§6从高能流体中获取能量",
                "§6Get energy from high-power fluid"
        );
        tsl(provider, 
                "ctnh.multiblock.large_naquadah_reactor.tooltip.2",
                "§6运行时需要消耗§e2400mB/s§6液态空气， 否则你的燃料将会被销毁",
                "§6When the reactor is running, it needs to consume §e2400mB/s§6 liquid air, otherwise your fuel will be destroyed"
        );
        tsl(provider, 
                "ctnh.multiblock.large_naquadah_reactor.tooltip.3",
                "§6输入液态燃料, 输入仓内出现不止§4一种§6燃料时，反应堆将会爆炸",
                "§6Input liquid fuel, if there are more than §4one §6fuel in the input hatch, the reactor will explode"
        );
        tsl(provider, 
                "ctnh.multiblock.large_naquadah_reactor.tooltip.4",
                "§6可以消耗§e1000mB/s§6冷却液获得§e150%效率提升",
                "§6Can consume §e1000mB/s§6 cooling fluid to get §e150% efficiency"
        );
        tsl(provider, 
                "ctnh.multiblock.large_naquadah_reactor.tooltip.5",
                "§6消耗激发流体以提升输出功率",
                "§6Consume igniting fluid to increase output power"
        );
        tsl(provider, 
                "ctnh.multiblock.large_naquadah_reactor.tooltip.6",
                "液态铯        | §e2x功率 | §6180mB/s",
                "Cesium             | §e2x power | §6180mB/s"
        );
        tsl(provider, 
                "ctnh.multiblock.large_naquadah_reactor.tooltip.7",
                "液态铀-235  | §e3x功率 | §6180mB/s",
                "Uranium-235   | §e3x power | §6180mB/s"
        );
        tsl(provider, 
                "ctnh.multiblock.large_naquadah_reactor.tooltip.8",
                "液态硅岩      | §e4x功率 | §620mB/s",
                "Naquadah       | §e4x power | §620mB/s"
        );

        tsl(provider, 
                "block.ctnh.neutron_sensor.tooltip.0",
                "§7可安装在§b中子活化器§7上",
                "§7Could be installed on §bNeutron Activator§7"
        );
        tsl(provider, 
                "block.ctnh.neutron_sensor.tooltip.1",
                "基于§6中子动能§7输出红石信号，右键以打开GUI进行设置。",
                "Based on §6neutron kinetic energy §7output red stone signal, right-click to open the GUI for settings."
        );

        tsl(provider, "ctnh.machine.naquadah_reactor.tooltip", "效率: %s%%", "Efficiency: %s%%");
        tsl(provider, "ctnh.machine.rocket_engine.tooltip", "效率: %s%%", "Efficiency: %s%%");
    }
}
