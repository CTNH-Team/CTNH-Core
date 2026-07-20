package io.github.cpearl0.ctnhcore.data.machines;

import io.github.cpearl0.ctnhcore.common.machine.multiblock.part.CTNHPartAbility;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.part.NeutronAcceleratorMachine;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.part.NeutronSensorMachine;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Prefix;
import com.ctnhlang.Suffix;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;
import static io.github.cpearl0.ctnhcore.utils.CTNHMachineUtils.registerTieredMachines;

@Prefix("machine")
@Suffix("tooltip")
public class GTNNMachines {

    public static MachineDefinition[] NEUTRON_ACCELERATOR;
    public static MachineDefinition NEUTRON_SENSOR;

    public static void init() {
        NEUTRON_ACCELERATOR = registerTieredMachines(
                "neutron_accelerator",
                " 中子加速器", NeutronAcceleratorMachine::new,
                (tier, builder) -> builder.langValue(VNF[tier] + " Neutron Accelerator")
                        .rotationState(RotationState.ALL)
                        .abilities(CTNHPartAbility.NEUTRON_ACCELERATOR)
                        .tooltips(neutron_accelerator[0].translate())
                        .tooltips(neutron_accelerator[1].translate(V[tier]))
                        .tooltips(neutron_accelerator[2].translate(V[tier] * 8 / 10))
                        .tooltips(neutron_accelerator[3].translate())
                        .colorOverlayTieredHullModel("overlay_na")
                        .register(),
                GTValues.tiersBetween(ULV, UV));
        NEUTRON_SENSOR = REGISTRATE
                .machine("neutron_sensor", NeutronSensorMachine::new)
                .cnLangValue("中子传感器")
                .langValue("Neutron Sensor")
                .tier(IV)
                .rotationState(RotationState.ALL)
                .abilities(CTNHPartAbility.NEUTRON_SENSOR)
                .colorOverlayTieredHullModel("overlay_neutron_sensor", null, "overlay_neutron_sensor_emissive")
                .tooltips(neutron_sensor[0].translate())
                .tooltips(neutron_sensor[1].translate())
                .register();
    }

    @CN({
            "§o§7输入EU，加速中子!",
            "§6最大EU输入: %s",
            "§6最大EU消耗: %s",
            "§b每点EU都会转化为§e10~20-eV§b中子动能."
    })
    @EN({
            "§o§7Input EU to Accelerate the Neutron!",
            "§6Max EU Input: %s",
            "§6Max EU Cost: %s",
            "§bEach EU will be converted to §e10~20-eV§b of neutron kinetic energy."
    })
    static Lang[] neutron_accelerator;
    @CN({
            "§7可安装在§b中子活化器§7上",
            "基于§6中子动能§7输出红石信号，右键以打开GUI进行设置。"
    })
    @EN({
            "§7Could be installed on §bNeutron Activator§7",
            "Based on §6neutron kinetic energy §7output red stone signal, right-click to open the GUI for settings."
    })
    static Lang[] neutron_sensor;
}
