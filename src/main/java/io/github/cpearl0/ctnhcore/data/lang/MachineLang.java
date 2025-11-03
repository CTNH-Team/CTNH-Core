package io.github.cpearl0.ctnhcore.data.lang;

import static io.github.cpearl0.ctnhcore.data.lang.CTNHLangHandler.tsl;

public class MachineLang {
    public static void init() {
        machineTooltip();
        machineGui();
    }
    public static void machineGui() {
        tsl(
                "gui.ctnh.neutron_sensor.invert.enabled",
                "输出：反转\n\n切换以反转红石逻辑\n默认情况下，中子动能介于所设定的最小值和最大值之间时传感器将发出红石信号，小于最小值时则停止发出红石信号",
                "Output: Reverse\n\nSwitch to reverse redstone logic\nBy default, the sensor will emit a redstone signal when the neutron kinetic energy is between the set minimum and maximum values, and stop emitting a redstone signal when it is less than the minimum value."
        );
        tsl(
                "gui.ctnh.neutron_sensor.invert.disabled",
                "输出：正常\n\n切换以反转红石逻辑\n默认情况下，中子动能介于所设定的最小值和最大值之间时传感器将发出红石信号，小于最小值时则停止发出红石信号",
                "Output: Normal\n\nSwitch to reverse redstone logic\nBy default, the sensor will emit a redstone signal when the neutron kinetic energy is between the set minimum and maximum values, and stop emitting a redstone signal when it is less than the minimum value."
        );

    }

    public static void machineTooltip() {
        tsl(
                "ctnh.multiblock.multithread.status",
                "§b线程%s:",
                "§bThread %s:");


        tsl(
                "ctnh.multiblock.precision_assembly.tooltip.0",
                "其他可用配方类型: 精密组装",
                "Other available recipe type: Precision Assembly"
        );
        tsl(
                "ctnh.multiblock.precision_assembly.tooltip.1",
                "注意：在精密组装模式下无法并行",
                "NOTE: Parallelization is not possible in precision assembly mode"
        );

    }
}
