package io.github.cpearl0.ctnhcore.registry.machines;

import io.github.cpearl0.ctnhcore.common.machine.simple.CircuitAssemblerMachine;
import io.github.cpearl0.ctnhcore.common.machine.simple.CircuitDesignBenchMachine;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import static com.gregtechceu.gtceu.api.GTValues.EV;
import static com.gregtechceu.gtceu.api.GTValues.HV;
import static com.gregtechceu.gtceu.api.GTValues.LV;
import static com.gregtechceu.gtceu.api.GTValues.MV;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

/**
 * 电路板玩法机器注册（设计文档 §二）。贴图复用 GTCEu 现有资源。
 * 自定义电路组装机与 GTM 原版 circuit_assembler 区分：原版是配方机，
 * 这里的是图纸驱动（NBT 材料清单 + 良品率/品质波动，§六），故独立注册。
 */
public class CTNHCircuitMachines {

    @CN("在网格基板上摆放元件，设计电路板")
    @EN("Design circuit boards by placing components on a substrate grid")
    @Key("machine.ctnhcore.circuit_design_bench.tooltip.0")
    public static Lang designBenchTooltip;

    @CN("按图纸生产自定义电路板（可处理 ≤%s 级基板）")
    @EN("Assembles custom circuit boards from blueprints (up to %s substrates)")
    @Key("machine.ctnhcore.custom_circuit_assembler.tooltip.0")
    public static Lang assemblerTooltip;

    public static MachineDefinition CIRCUIT_DESIGN_BENCH;
    public static MachineDefinition CUSTOM_CIRCUIT_ASSEMBLER_MV;
    public static MachineDefinition CUSTOM_CIRCUIT_ASSEMBLER_HV;
    public static MachineDefinition CUSTOM_CIRCUIT_ASSEMBLER_EV;

    private static final int[] ASSEMBLER_TIERS = { MV, HV, EV };
    private static final String[] ASSEMBLER_IDS = { "mv_custom_circuit_assembler", "hv_custom_circuit_assembler",
            "ev_custom_circuit_assembler" };
    private static final String[] ASSEMBLER_CN = { "自定义电路组装机", "高级自定义电路组装机", "精英自定义电路组装机" };
    private static final String[] ASSEMBLER_EN = { "Custom Circuit Assembler", "Advanced Custom Circuit Assembler",
            "Elite Custom Circuit Assembler" };

    public static void init() {
        CIRCUIT_DESIGN_BENCH = REGISTRATE.machine("circuit_design_bench", CircuitDesignBenchMachine::new)
                .cnLangValue("电路板设计台")
                .langValue("Circuit Design Bench")
                .tier(LV)
                .rotationState(RotationState.ALL)
                .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"),
                        GTCEu.id("block/multiblock/central_monitor"))
                .tooltips(designBenchTooltip.translate())
                .register();
        MachineDefinition[] assemblers = { null, null, null };
        for (int i = 0; i < ASSEMBLER_TIERS.length; i++) {
            int tier = ASSEMBLER_TIERS[i];
            assemblers[i] = REGISTRATE.machine(ASSEMBLER_IDS[i], holder -> new CircuitAssemblerMachine(holder, tier))
                    .cnLangValue(ASSEMBLER_CN[i])
                    .langValue(ASSEMBLER_EN[i])
                    .tier(tier)
                    .rotationState(RotationState.ALL)
                    .workableTieredHullModel(GTCEu.id("block/machines/circuit_assembler"))
                    .tooltips(assemblerTooltip.translate(GTValues.VN[tier]))
                    .register();
        }
        CUSTOM_CIRCUIT_ASSEMBLER_MV = assemblers[0];
        CUSTOM_CIRCUIT_ASSEMBLER_HV = assemblers[1];
        CUSTOM_CIRCUIT_ASSEMBLER_EV = assemblers[2];
    }
}
