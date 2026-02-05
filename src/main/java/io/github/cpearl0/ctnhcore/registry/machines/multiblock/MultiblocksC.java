package io.github.cpearl0.ctnhcore.registry.machines.multiblock;

import io.github.cpearl0.ctnhcore.api.machine.feature.ICoilMachine;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.multithread.CNCAlloySmelter;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.part.CTNHPartAbility;
import io.github.cpearl0.ctnhcore.registry.CTNHBlocks;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeModifiers;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.utils.FormattingUtil;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.level.block.Blocks;

import appeng.core.definitions.AEBlocks;

import static com.gregtechceu.gtceu.api.pattern.Predicates.abilities;
import static com.gregtechceu.gtceu.api.pattern.Predicates.heatingCoils;
import static com.gregtechceu.gtceu.common.data.GCYMBlocks.CASING_HIGH_TEMPERATURE_SMELTING;
import static com.gregtechceu.gtceu.common.data.GCYMRecipeTypes.ALLOY_BLAST_RECIPES;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

// spotless:off
public class MultiblocksC {
    public static void init() {}

    public static final MultiblockMachineDefinition GREENHOUSE = REGISTRATE.multiblock("greenhouse", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.GREENHOUSE_RECIPES)
            .tooltips(Component.translatable("ctnh.multiblock.greenhouse.tooltip.0").withStyle(ChatFormatting.GRAY))
            .appearanceBlock(GTBlocks.CASING_STEEL_SOLID)
            .pattern(definition -> FactoryBlockPattern.start()
            .aisle("CCC", "CGC", "CGC", "CLC", "CCC")
            .aisle("CMC", "G#G", "G#G", "LIL", "COC")
            .aisle("CKC", "CGC", "CGC", "CLC", "CNC")
            .where("K", Predicates.controller(Predicates.blocks(definition.get())))
            .where("M", Predicates.blocks(Blocks.MOSS_BLOCK)
                    .or(Predicates.blocks(Blocks.DIRT))
                    .or(Predicates.blocks(Blocks.GRASS_BLOCK))
            )
            .where("G", Predicates.blocks(AEBlocks.QUARTZ_GLASS.block()))
            .where("I", Predicates.blocks(Blocks.GLOWSTONE))
            .where("L", Predicates.blocks(GTBlocks.CASING_GRATE.get()))
            .where("C", Predicates.blocks(GTBlocks.CASING_STEEL_SOLID.get())
                    .or(Predicates.autoAbilities(definition.getRecipeTypes()))
            )
            .where("O", Predicates.abilities(PartAbility.MUFFLER)
                    .setExactLimit(1)
            )
            .where("N", Predicates.abilities(PartAbility.MAINTENANCE))
            .where("#", Predicates.air())
            .build())
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_solid_steel"), GTCEu.id("block/multiblock/implosion_compressor"))
            .register();


    public static final MultiblockMachineDefinition CNC_ALLOY_SMELTER = REGISTRATE
            .multiblock("cnc_alloy_smelter", CNCAlloySmelter::new)
            .langValue("CNC ALLOY Smelter")
            .recipeType(ALLOY_BLAST_RECIPES)
            .tooltips(Component.literal("§b具有4个异步线程§r"),
                    Component.literal("使用§d异步线程控制仓§r以配置多线程运行模式")
                    //,Component.literal("多线程模式下需要消耗算力")
            )
            .tooltips(Component.translatable("gtceu.multiblock.parallelizable.tooltip"))
            .tooltips(Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.0"),
                    Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.1"),
                    Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.2"))
            .allowExtendedFacing(false)
            .allowFlip(false)
            //.rotationState(RotationState.NON_Y_AXIS)

            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH, CTNHRecipeModifiers::ebfOverclock, GTRecipeModifiers.BATCH_MODE)
            .appearanceBlock(GTBlocks.COMPUTER_CASING)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("#####AAAAA#####", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############")
                    .aisle("###AABBBBBAA###", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############")
                    .aisle("##ABBCCCCCBBA##", "#####DDDDD#####", "#####DDDDD#####", "#####DDDDD#####", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "####AAAAAAA####")
                    .aisle("#ABCCEEEEECCBA#", "###DD#####DD###", "###DD#####DD###", "###DD#####DD###", "###AAFFFFFAA###", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "#####AAAAA#####", "###AACCGCCAA###")
                    .aisle("#ABCEHBBBHECBA#", "###D#######D###", "###D#######D###", "###D#######D###", "###AA#####AA###", "####AEEEEEA####", "####AEEEEEA####", "####AFFFFFA####", "####AFFFFFA####", "####AFFFFFA####", "####AFFFFFA####", "####AFFFFFA####", "####AEEEEEA####", "####A#####A####", "##AAGAAGAAGAA##")
                    .aisle("ABCEHBHBHBHECBA", "##D#########D##", "##D#########D##", "##D#########D##", "###F#######F###", "####E#####E####", "####E#####E####", "####FGCCCGF####", "####FGHHHGF####", "####FGHHHGF####", "####FGHHHGF####", "####FGCCCGF####", "####EGGGGGE####", "###A#G###G#A###", "##ACAGGGGGACA##")
                    .aisle("ABCEBHBBBHBECBA", "##D###B#B###D##", "##D###B#B###D##", "##D###B#B###D##", "###F##B#B##F###", "####E#B#B#E####", "####E#B#B#E####", "####FCB#BCF####", "####FHB#BHF####", "####FHB#BHF####", "####FHB#BHF####", "####FCB#BCF####", "####EGB#BGE####", "###A##BBB##A###", "##ACAGCGCGACA##")
                    .aisle("ABCEBBBCBBBECBA", "##D####G####D##", "##D####G####D##", "##D####G####D##", "###F###G###F###", "####E##G##E####", "####E##G##E####", "####FC#C#CF####", "####FH#H#HF####", "####FH#H#HF####", "####FH#H#HF####", "####FC#C#CF####", "####EG###GE####", "###A##BBB##A###", "##AGGGGIGGGGA##")
                    .aisle("ABCEBHBBBHBECBA", "##D###B#B###D##", "##D###B#B###D##", "##D###B#B###D##", "###F##B#B##F###", "####E#B#B#E####", "####E#B#B#E####", "####FCB#BCF####", "####FHB#BHF####", "####FHB#BHF####", "####FHB#BHF####", "####FCB#BCF####", "####EGB#BGE####", "###A##BBB##A###", "##ACAGCGCGACA##")
                    .aisle("ABCEHBHBHBHECBA", "##D#########D##", "##D#########D##", "##D#########D##", "###F#######F###", "####E#####E####", "####E#####E####", "####FGCCCGF####", "####FGHHHGF####", "####FGHHHGF####", "####FGHHHGF####", "####FGCCCGF####", "####EGGGGGE####", "###A#G###G#A###", "##ACAGGGGGACA##")
                    .aisle("#ABCEHBBBHECBA#", "###D#######D###", "###D#######D###", "###D#######D###", "###AA#####AA###", "####AEEEEEA####", "####AEEEEEA####", "####AFFFFFA####", "####AFFFFFA####", "####AFFFFFA####", "####AFFFFFA####", "####AFFFFFA####", "####AEEEEEA####", "####A#####A####", "##AAGAAGAAGAA##")
                    .aisle("#ABCCEEEEECCBA#", "###DD#####DD###", "###DD#####DD###", "###DD#####DD###", "###AAFFFFFAA###", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "#####AAAAA#####", "###AACCGCCAA###")
                    .aisle("##ABBCCCCCBBA##", "#####DDDDD#####", "#####DD@DD#####", "#####DDDDD#####", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "####AAAAAAA####")
                    .aisle("###AABBBBBAA###", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############")
                    .aisle("#####AAAAA#####", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############")
                    .where("C", Predicates.blocks(GTBlocks.MACHINE_CASING_LuV.get()))
                    .where("D", Predicates.blocks(GTBlocks.COMPUTER_CASING.get())
                            .or(Predicates.abilities(CTNHPartAbility.THREAD_HATCH).setMaxGlobalLimited(1).setPreviewCount(1))
                            .or(Predicates.autoAbilities(definition.getRecipeTypes(),false, false, true, true, true,
                                    true))
                            .or(Predicates.autoAbilities(true, false, true))
                            .or(abilities(PartAbility.INPUT_ENERGY).setMinGlobalLimited(1)
                                    .setMaxGlobalLimited(4).setPreviewCount(4))
                    )
                    .where("#", Predicates.any())
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("H", heatingCoils())
                    .where("I", abilities(PartAbility.MUFFLER))
                    .where("B", Predicates.blocks(CTNHBlocks.CASING_POLYBENZIMIDAZOLE_PIPE.get()))
                    .where("G", Predicates.blocks(GTBlocks.COMPUTER_CASING.get()))
                    .where("A", Predicates.blocks(CASING_HIGH_TEMPERATURE_SMELTING.get()))
                    .where("E", Predicates.blocks(CTNHBlocks.NAQUADAH_FIREBOX.get()))
                    .where("F", Predicates.blocks(GTBlocks.FUSION_GLASS.get()))
                    .build())
            .additionalDisplay((controller, components) -> {
                if (controller instanceof ICoilMachine coilMachine
                        && controller instanceof ITieredMachine tieredMachine
                        && controller.isFormed()
                ) {
                    components.add(Component.translatable("gtceu.multiblock.blast_furnace.max_temperature",
                            Component
                                    .translatable(
                                            FormattingUtil
                                                    .formatNumbers(coilMachine.getCoilType().getCoilTemperature() +
                                                            100L * Math.max(0, tieredMachine.getTier() - GTValues.MV)) +
                                                    "K")
                                    .setStyle(Style.EMPTY.withColor(ChatFormatting.RED))));
                }
            })
            .sidedWorkableCasingModel(GTCEu.id("block/casings/hpca/computer_casing"),
                    GTCEu.id("block/multiblock/gcym/blast_alloy_smelter"))
            .register();
}
// spotless:on
