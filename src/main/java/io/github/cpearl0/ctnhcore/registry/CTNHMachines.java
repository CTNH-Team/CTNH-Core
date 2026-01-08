package io.github.cpearl0.ctnhcore.registry;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.hugehatch.HugeDualHatchPartMachine;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.hugehatch.HugeItemBusPartMachine;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.part.*;
import io.github.cpearl0.ctnhcore.common.machine.simple.EfficiencyGeneratorMachine;
import io.github.cpearl0.ctnhcore.common.machine.simple.HighPerformanceComputerMachine;
import io.github.cpearl0.ctnhcore.data.machines.GTNNMachines;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.common.data.machines.GTMachineUtils;
import com.gregtechceu.gtceu.common.data.models.GTModels;
import com.gregtechceu.gtceu.common.machine.multiblock.part.*;
import com.gregtechceu.gtceu.data.lang.LangHandler;
import com.gregtechceu.gtceu.utils.FormattingUtil;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.Prefix;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.Suffix;

import java.util.Locale;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.capability.recipe.IO.IN;
import static com.gregtechceu.gtceu.api.capability.recipe.IO.OUT;
import static com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties.IS_FORMED;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.DUAL_INPUT_HATCH_ABILITIES;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.DUAL_OUTPUT_HATCH_ABILITIES;
import static com.gregtechceu.gtceu.common.data.models.GTMachineModels.*;
import static com.gregtechceu.gtceu.utils.FormattingUtil.toEnglishName;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;
import static io.github.cpearl0.ctnhcore.utils.CTNHMachineUtils.*;

@Prefix("machine")
@Suffix("tooltip")
public class CTNHMachines {

    static {
        REGISTRATE.creativeModeTab(() -> CTNHCreativeModeTabs.MACHINE);
    }

    public static final MachineDefinition HIGH_SPEED_PIPE_BLOCK = REGISTRATE
            .machine("high_speed_pipe_block", HighSpeedPipeBlock::new)
            .cnLangValue("高速管道方块")
            .blockModel(GTModels.cubeAllModel(CTNHCore.id("block/speedingpipe")))
            .hasBER(false)
            .itemBuilder(item -> item.model(
                    (ctx, prov) -> prov.withExistingParent(ctx.getName(), CTNHCore.id("block/" + ctx.getName()))))
            .rotationState(RotationState.Y_AXIS).register();

    public static final MachineDefinition CATALYST_HATCH = REGISTRATE
            .machine("catalyst_hatch", CatalystHatchPartMachine::new)
            .cnLangValue("催化剂仓")
            .langValue("Catalyst Hatch")
            .tier(IV)
            .rotationState(RotationState.ALL)
            .abilities(CTNHPartAbility.CATALYST)
            .colorOverlayTieredHullModel("overlay_catalyst_in", null, "overlay_catalyst_hatch")
            .tooltips()
            .register();
    public static final MachineDefinition[] DEHYDRATOR = registerSimpleMachines("dehydrator",
            CTNHRecipeTypes.DEHYDRATOR_RECIPES, GTValues.tiersBetween(MV, ZPM));
    public static final MachineDefinition[] NAQUADAH_REACTOR = registerEfficiencyGeneratorMachines(
            "naquadah_reactor",
            CTNHRecipeTypes.NAQUADAH_REACTOR_RECIPES,
            CTNHRecipeModifiers::naquadahReactor,
            tier -> tier * 32000,
            EfficiencyGeneratorMachine::naquadahReactor,
            tiersBetween(EV, UV));
    public static final MachineDefinition[] ROCKET_ENGINE = registerEfficiencyGeneratorMachines(
            "rocket_engine",
            CTNHRecipeTypes.ROCKET_ENGINE_RECIPES,
            CTNHRecipeModifiers::rocketEngine,
            tier -> tier * 32000,
            EfficiencyGeneratorMachine::rocketEngine,
            tiersBetween(EV, LuV));
    public static final MachineDefinition[] CIRCUIT_BUS = registerTieredMachines("circuit_bus",
            CircuitBusPartMachine::new,
            (tier, builder) -> builder
                    .langValue(GTValues.VNF[tier] + " Circuit Bus")
                    .rotationState(RotationState.ALL)
                    .abilities(CTNHPartAbility.CIRCUIT)
                    .modelProperty(IS_FORMED, false)
                    .colorOverlayTieredHullModel(GTCEu.id("block/overlay/machine/overlay_pipe_in"), null,
                            GTCEu.id("block/overlay/machine/" + OVERLAY_ITEM_HATCH))
                    .register(),
            GTMachineUtils.ALL_TIERS);
    public static final MachineDefinition DRONEHOLDER = REGISTRATE.machine("drone_holder", DroneHolderMachine::new)
            .langValue("drone Holder")
            .tier(UV)
            .rotationState(RotationState.ALL)
            .abilities(CTNHPartAbility.DRONE)
            .modelProperty(IS_FORMED, false)
            .modelProperty(GTMachineModelProperties.RECIPE_LOGIC_STATUS, RecipeLogic.Status.IDLE)
            .model(createWorkableTieredHullMachineModel(GTCEu.id("block/machines/object_holder"))
                    .andThen((ctx, prov, model) -> {
                        model.addReplaceableTextures("bottom", "top", "side");
                    }))
            .register();
    public static final MachineDefinition[] COMPILERMACHINE = registerTieredMachines("neuro_compiler",
            CompilerMachine::new,
            (tier, builder) -> builder
                    .langValue(GTValues.VNF[tier] + " Neuro Compiler")
                    .rotationState(RotationState.ALL)
                    .abilities(CTNHPartAbility.COMPILER)
                    .colorOverlayTieredHullModel(GTCEu.id("block/overlay/machine/overlay_pipe_in"), null,
                            GTCEu.id("block/overlay/machine/" + OVERLAY_ITEM_HATCH))
                    .register(),
            GTMachineUtils.ALL_TIERS);

    public static final MachineDefinition[] PERSONAL_COMPUTER = registerSimpleComputationMachines("personal_computer",
            CTNHRecipeTypes.PERSONAL_COMPUTER);

    public static final MachineDefinition[] ASYNC_THREAD_HATCH = registerTieredMachines("async_thread_hatch",
            AsynThreadHatchMachine::new,
            (tier, builder) -> builder
                    .langValue("Async Thread HATCH")
                    .rotationState(RotationState.ALL)
                    .abilities(CTNHPartAbility.THREAD_HATCH)
                    .modelProperty(IS_FORMED, false)
                    .modelProperty(GTMachineModelProperties.RECIPE_LOGIC_STATUS, RecipeLogic.Status.IDLE)
                    .model(createWorkableTieredHullMachineModel(
                            CTNHCore.id("block/machines/thread_hatch"))
                            .andThen((ctx, prov, model) -> {
                                model.addReplaceableTextures("bottom", "top", "side");
                            }))
                    .tooltips(Component.translatable("gtceu.part_sharing.disabled"))
                    // .tooltips(Component.literal("配置以启用机器的多线程模式，基础消耗1点算力"),
                    // Component.literal("每有一个线程启用线程保护，算力消耗x2"),
                    // Component.literal("每有一个线程启用配方锁定，算力消耗x4")
                    // )
                    .register(),
            LuV);

    public static final MachineDefinition[] PARALLEL_HATCH = registerTieredMachines("parallel_hatch",
            ParallelHatchPartMachine::new,
            (tier, builder) -> builder
                    .langValue(switch (tier) {
                        case UHV, UEV, UIV -> "Epic";
                        case UXV -> "Legendary";
                        case OpV -> "Eternal";
                        case MAX -> "MAX";
                        default -> "Simple"; // Should never be hit.
                    } + " Parallel Control Hatch")
                    .rotationState(RotationState.ALL)
                    .abilities(PartAbility.PARALLEL_HATCH)
                    .modelProperty(IS_FORMED, false)
                    .modelProperty(GTMachineModelProperties.RECIPE_LOGIC_STATUS, RecipeLogic.Status.IDLE)
                    .model(createWorkableTieredHullMachineModel(
                            GTCEu.id("block/machines/parallel_hatch_mk" + (tier - 4)))
                            .andThen((ctx, prov, model) -> {
                                model.addReplaceableTextures("bottom", "top", "side");
                            }))
                    .tooltips(Component.translatable("gtceu.machine.parallel_hatch_mk" + tier + ".tooltip"),
                            Component.translatable("gtceu.part_sharing.disabled"))
                    .register(),
            UHV, UEV, UIV, UXV, OpV, MAX);
    public static final MachineDefinition[] ENERGY_OUTPUT_HATCH_4A_LOWER = registerTieredMachines(
            "energy_output_hatch_4a",
            (holder, tier) -> new EnergyHatchPartMachine(holder, tier, OUT, 4),
            (tier, builder) -> builder
                    .langValue(VNF[tier] + " 4A Dynamo Hatch")
                    .rotationState(RotationState.ALL)
                    .abilities(PartAbility.OUTPUT_ENERGY)
                    .modelProperty(IS_FORMED, false)
                    .tooltips(Component.translatable("gtceu.universal.tooltip.voltage_out",
                            FormattingUtil.formatNumbers(V[tier]), VNF[tier]),
                            Component.translatable("gtceu.universal.tooltip.amperage_out", 4),
                            Component.translatable("gtceu.universal.tooltip.energy_storage_capacity",
                                    FormattingUtil
                                            .formatNumbers(EnergyHatchPartMachine.getHatchEnergyCapacity(tier, 4))),
                            Component.translatable("gtceu.machine.energy_hatch.output_hi_amp.tooltip"))
                    .overlayTieredHullModel(GTCEu.id("block/machine/part/energy_output_hatch_4a"))
                    .register(),
            GTValues.tiersBetween(LV, HV));
    public static final MachineDefinition[] ROTOR_HOLDER_EXTEND = registerTieredMachines("rotor_holder",
            RotorHolderPartMachine::new,
            (tier, builder) -> builder
                    .langValue("%s Rotor Holder".formatted(VNF[tier]))
                    .rotationState(RotationState.ALL)
                    .abilities(PartAbility.ROTOR_HOLDER)
                    .modelProperty(IMultiController.IS_FORMED_PROPERTY, false)
                    .modelProperty(RotorHolderPartMachine.HAS_ROTOR_PROPERTY, false)
                    .modelProperty(RotorHolderPartMachine.ROTOR_SPINNING_PROPERTY, false)
                    .modelProperty(RotorHolderPartMachine.EMISSIVE_ROTOR_PROPERTY, false)
                    .model(createRotorHolderModel())
                    .tooltips(LangHandler.getFromMultiLang("gtceu.machine.rotor_holder.tooltip", 0),
                            LangHandler.getFromMultiLang("gtceu.machine.rotor_holder.tooltip", 1),
                            Component.translatable("gtceu.universal.disabled"))
                    .register(),
            GTValues.tiersBetween(ULV, MV));

    public static final MachineDefinition STERILE_CLEANROOM_MAINTENANCE_HATCH = REGISTRATE
            .machine("sterile_cleanroom_maintenance_hatch",
                    holder -> new CleaningMaintenanceHatchPartMachine(holder, CleanroomType.STERILE_CLEANROOM))
            .rotationState(RotationState.ALL)
            .abilities(PartAbility.MAINTENANCE)
            .tooltips(Component.translatable("gtceu.universal.disabled"),
                    Component.translatable("gtceu.machine.maintenance_hatch_cleanroom_auto.tooltip.0"),
                    Component.translatable("gtceu.machine.maintenance_hatch_cleanroom_auto.tooltip.1"))
            .tooltipBuilder((stack, tooltips) -> {
                tooltips.add(Component.literal("  ").append(Component
                        .translatable(CleanroomType.STERILE_CLEANROOM.getTranslationKey())
                        .withStyle(ChatFormatting.LIGHT_PURPLE)));
            })
            .modelProperty(MaintenanceHatchPartMachine.MAINTENANCE_TAPED_PROPERTY, false)
            .overlayTieredHullModel(CTNHCore.id("block/machine/part/sterile_cleanroom_maintenance_hatch"))
            .tier(UHV)
            .register();

    public static final MachineDefinition[] HIGH_PERFORMANCE_COMPUTER = registerTieredMachines(
            "high_performance_computer",
            HighPerformanceComputerMachine::new,
            (tier, builder) -> builder.langValue("%s High Performance Computer".formatted(VNF[tier]))
                    .langValue("%s %s %s".formatted(VLVH[tier], toEnglishName("high_performance_computer"), VLVT[tier]))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .workableTieredHullModel(
                            GTCEu.id("block/machines/high_performance_computer/" + VN[tier].toLowerCase(Locale.ROOT)))
                    .tooltips(Component.translatable("ctnhcore.machine.high_performance_computer.tooltip.0"),
                            Component.translatable("ctnhcore.machine.high_performance_computer.tooltip.1",
                                    (tier >= GTValues.HV ? 1 << (tier - GTValues.HV) : 0)),
                            Component.translatable("gtceu.universal.tooltip.voltage_in",
                                    FormattingUtil.formatNumbers(VA[tier] *
                                            HighPerformanceComputerMachine.getMaxInputOutputAmperageStatic()),
                                    VNF[tier]))   // 输入电流16A
                    .register(),
            GTValues.tiersBetween(HV, IV));

    public static final MachineDefinition[] HUGE_ITEM_IMPORT_BUS = registerTieredMachines(
            "huge_input_bus",
            "§r巨型输入总线",
            (holder, tier) -> new HugeItemBusPartMachine(holder, tier, IN),
            (tier, builder) -> builder
                    .langValue(VNF[tier] + "§r Huge Input Bus")
                    .rotationState(RotationState.ALL)
                    .abilities(PartAbility.IMPORT_ITEMS)
                    .modelProperty(IS_FORMED, false)
                    .colorOverlayTieredHullModel("huge_bus_in", null, null)
                    .tooltips(Component.translatable("gtceu.machine.item_bus.import.tooltip"),
                            Component.translatable("gtceu.universal.tooltip.item_storage_capacity", 1 + tier),
                            REGISTRATE.genLang("ctnhcore.universal.tooltip.item_storage_multiplier",
                                    "§6Item Stack Multiplier: §f%d",
                                    "§6物品堆叠倍数：§f%d",
                                    FormattingUtil.formatNumbers(HugeItemBusPartMachine.getSlotMultiplier(tier))))
                    .allowCoverOnFront(true)
                    .register(),
            ALL_TIERS);

    public static final MachineDefinition[] HUGE_ITEM_EXPORT_BUS = registerTieredMachines(
            "huge_output_bus",
            "§r巨型输出总线",
            (holder, tier) -> new HugeItemBusPartMachine(holder, tier, OUT),
            (tier, builder) -> builder
                    .langValue(VNF[tier] + " §rHuge Output Bus")
                    .rotationState(RotationState.ALL)
                    .abilities(PartAbility.EXPORT_ITEMS)
                    .modelProperty(IS_FORMED, false)
                    .colorOverlayTieredHullModel("huge_bus_out", null, null)
                    .tooltips(Component.translatable("gtceu.machine.item_bus.export.tooltip"),
                            Component.translatable("gtceu.universal.tooltip.item_storage_capacity", 1 + tier),
                            Component.translatable("ctnhcore.universal.tooltip.item_storage_multiplier",
                                    FormattingUtil.formatNumbers(HugeItemBusPartMachine.getSlotMultiplier(tier))))
                    .allowCoverOnFront(true)
                    .register(),
            ALL_TIERS);

    public static final MachineDefinition[] HUGE_DUAL_IMPORT_HATCH = registerTieredMachines(
            "huge_dual_input_hatch",
            "§r巨型输入总成",
            (holder, tier) -> new HugeDualHatchPartMachine(holder, tier, IN),
            (tier, builder) -> builder
                    .langValue("%s Huge Dual Input Hatch".formatted(VNF[tier]))
                    .rotationState(RotationState.ALL)
                    .abilities(DUAL_INPUT_HATCH_ABILITIES)
                    .modelProperty(IS_FORMED, false)
                    .colorOverlayTieredHullModel("huge_dual_hatch_in", null, null)
                    .tooltips(
                            Component.translatable("gtceu.machine.dual_hatch.import.tooltip"),
                            Component.translatable("gtceu.universal.tooltip.item_storage_capacity", (1 + tier)),
                            Component.translatable("ctnhcore.universal.tooltip.item_storage_multiplier",
                                    FormattingUtil.formatNumbers(HugeItemBusPartMachine.getSlotMultiplier(tier))),
                            Component.translatable(
                                    "gtceu.universal.tooltip.fluid_storage_capacity_mult",
                                    HugeDualHatchPartMachine.getTankSize(tier),
                                    FormattingUtil.formatNumbers(HugeDualHatchPartMachine.getTankCapacity(
                                            DualHatchPartMachine.INITIAL_TANK_CAPACITY,
                                            tier))))
                    .register(),
            ALL_TIERS);
    public static final MachineDefinition[] HUGE_DUAL_EXPORT_HATCH = registerTieredMachines(
            "huge_dual_output_hatch",
            "§r巨型输出总成",
            (holder, tier) -> new HugeDualHatchPartMachine(holder, tier, OUT),
            (tier, builder) -> builder
                    .langValue("%s Huge Dual Output Hatch".formatted(VNF[tier]))
                    .rotationState(RotationState.ALL)
                    .abilities(DUAL_OUTPUT_HATCH_ABILITIES)
                    .modelProperty(IS_FORMED, false)
                    .colorOverlayTieredHullModel("huge_dual_hatch_out", null, null)
                    .tooltips(
                            Component.translatable("gtceu.machine.dual_hatch.export.tooltip"),
                            Component.translatable("gtceu.universal.tooltip.item_storage_capacity", (1 + tier)),
                            Component.translatable("ctnhcore.universal.tooltip.item_storage_multiplier",
                                    FormattingUtil.formatNumbers(HugeItemBusPartMachine.getSlotMultiplier(tier))),
                            Component.translatable(
                                    "gtceu.universal.tooltip.fluid_storage_capacity_mult",
                                    HugeDualHatchPartMachine.getTankSize(tier),
                                    FormattingUtil.formatNumbers(HugeDualHatchPartMachine.getTankCapacity(
                                            DualHatchPartMachine.INITIAL_TANK_CAPACITY,
                                            tier))))
                    .register(),
            ALL_TIERS);

    public static void init() {
        GTNNMachines.init();
    }
}
