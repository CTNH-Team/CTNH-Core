package io.github.cpearl0.ctnhcore.registry.machines.multiblock;

import io.github.cpearl0.ctnhcore.data.CreateRecipeTypes;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.MultiblockShapeInfo;
import com.gregtechceu.gtceu.api.pattern.Predicates;

import com.lowdragmc.lowdraglib.utils.BlockInfo;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;

import com.mo_guang.ctpp.CTPP;
import com.mo_guang.ctpp.api.CTPPPartAbility;
import com.mo_guang.ctpp.common.machine.multiblock.KineticWorkableMultiblockMachine;
import com.mo_guang.ctpp.registry.CTPPRecipeModifiers;
import com.mo_guang.ctpp.util.CommonTooltips;
import com.negodya1.vintageimprovements.VintageBlocks;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.base.DirectionalKineticBlock;
import fr.lucreeper74.createmetallurgy.registries.CMBlocks;

import static com.gregtechceu.gtceu.common.data.GTBlocks.CASING_TEMPERED_GLASS;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;
import static io.github.cpearl0.ctnhcore.utils.LatheStateProvider.LATHE_EAST;
import static io.github.cpearl0.ctnhcore.utils.LatheStateProvider.LATHE_WEST;
import static net.minecraft.world.level.block.Blocks.GLASS;

public class Mechanical {

    public final static MultiblockMachineDefinition MECHANICAL_PRESSOR = REGISTRATE
            .multiblock("mechanical_pressor", KineticWorkableMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CreateRecipeTypes.MECHANICAL_PRESSOR_RECIPES)
            .appearanceBlock(AllBlocks.RAILWAY_CASING)
            .recipeModifier(CTPPRecipeModifiers.KINETIC_PARALLEL)
            .tooltips(CommonTooltips.KINETIC_OVERCLOCK)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAA", "BCCCB", "B###B", "BBBBB", "B###B", "BCCCB", "AAAAA")
                    .aisle("AAAAA", "CDDDC", "#####", "B###B", "#####", "CEEEC", "AAAAA")
                    .aisle("AAAAA", "CDDDC", "#####", "B###B", "#####", "CEEEC", "AAAAA")
                    .aisle("AAAAA", "CDDDC", "#####", "B###B", "#####", "CEEEC", "AAAAA")
                    .aisle("AA@AA", "BCCCB", "B###B", "BBBBB", "B###B", "BCCCB", "AAAAA")
                    .where("A", Predicates.blocks(AllBlocks.RAILWAY_CASING.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.abilities(CTPPPartAbility.INPUT_KINETIC))
                            .or(Predicates.abilities(CTPPPartAbility.MECHANICAL_UPGRADE).setExactLimit(1)))
                    .where("B", Predicates.blocks(AllBlocks.METAL_GIRDER.get()))
                    .where("C", Predicates.blocks(AllBlocks.BRASS_CASING.get()))
                    .where("#", Predicates.any())
                    .where("D", Predicates.blocks(AllBlocks.DEPOT.get()))
                    .where("E", Predicates.blocks(AllBlocks.MECHANICAL_PRESS.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel(CTPP.id("block/create/railway_casing"),
                    GTCEu.id("block/multiblock/large_chemical_reactor"))
            .register();
    public final static MultiblockMachineDefinition MECHANICAL_MIXER = REGISTRATE
            .multiblock("mechanical_mixer", KineticWorkableMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CreateRecipeTypes.MECHANICAL_MIXER_RECIPES)
            .appearanceBlock(AllBlocks.RAILWAY_CASING)
            .recipeModifier(CTPPRecipeModifiers.KINETIC_PARALLEL)
            .tooltips(CommonTooltips.KINETIC_OVERCLOCK)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAA", "BCCCB", "BDDDB", "B###B", "B###B", "BDDDB", "AAAAA")
                    .aisle("AAAAA", "CEEEC", "DFFFD", "#####", "#####", "DGGGD", "AAAAA")
                    .aisle("AAAAA", "CEEEC", "DFFFD", "#####", "#####", "DGGGD", "AAAAA")
                    .aisle("AAAAA", "CEEEC", "DFFFD", "#####", "#####", "DGGGD", "AAAAA")
                    .aisle("AA@AA", "BCCCB", "BDDDB", "B###B", "B###B", "BDDDB", "AAAAA")
                    .where("A", Predicates.blocks(AllBlocks.RAILWAY_CASING.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.abilities(CTPPPartAbility.INPUT_KINETIC))
                            .or(Predicates.abilities(CTPPPartAbility.MECHANICAL_UPGRADE).setExactLimit(1)))
                    .where("B", Predicates.blocks(AllBlocks.METAL_GIRDER.get()))
                    .where("C", Predicates.blocks(GLASS))
                    .where("D", Predicates.blocks(AllBlocks.BRASS_CASING.get()))
                    .where("#", Predicates.any())
                    .where("E", Predicates.blocks(AllBlocks.BLAZE_BURNER.get()))
                    .where("F", Predicates.blocks(AllBlocks.BASIN.get()))
                    .where("G", Predicates.blocks(AllBlocks.MECHANICAL_MIXER.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel(CTPP.id("block/create/railway_casing"),
                    GTCEu.id("block/multiblock/large_chemical_reactor"))
            .register();
    public final static MultiblockMachineDefinition MECHANICAL_CENTRIFUGE = REGISTRATE
            .multiblock("mechanical_centrifuge", KineticWorkableMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CreateRecipeTypes.MECHANICAL_CENTRIFUGE_RECIPES)
            .appearanceBlock(AllBlocks.RAILWAY_CASING)
            .recipeModifier(CTPPRecipeModifiers.KINETIC_PARALLEL)
            .tooltips(CommonTooltips.KINETIC_OVERCLOCK)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAA", "BCCCB", "B###B", "B###B", "B###B", "BBBBB", "AAAAA")
                    .aisle("AAAAA", "CDDDC", "#####", "#####", "#####", "BF#FB", "AAAAA")
                    .aisle("AAAAA", "CDDDC", "##G##", "##G##", "##G##", "B#H#B", "AAAAA")
                    .aisle("AAAAA", "CDDDC", "#####", "#####", "#####", "BF#FB", "AAAAA")
                    .aisle("AA@AA", "BCCCB", "B###B", "B###B", "B###B", "BBBBB", "AAAAA")
                    .where("A", Predicates.blocks(AllBlocks.RAILWAY_CASING.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.abilities(CTPPPartAbility.INPUT_KINETIC))
                            .or(Predicates.abilities(CTPPPartAbility.MECHANICAL_UPGRADE).setExactLimit(1)))
                    .where("B", Predicates.blocks(AllBlocks.METAL_GIRDER.get()))
                    .where("C", Predicates.blocks(AllBlocks.BRASS_CASING.get()))
                    .where("#", Predicates.any())
                    .where("D", Predicates.blocks(AllBlocks.BASIN.get()))
                    .where("F", Predicates.blocks(AllBlocks.BRASS_ENCASED_COGWHEEL.get()))
                    .where("G", Predicates.blocks(VintageBlocks.CENTRIFUGE.get()))
                    .where("H", Predicates.blocks(AllBlocks.BRASS_ENCASED_LARGE_COGWHEEL.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel(CTPP.id("block/create/railway_casing"),
                    GTCEu.id("block/multiblock/large_chemical_reactor"))
            .register();
    public final static MultiblockMachineDefinition MECHANICAL_SIFTER = REGISTRATE
            .multiblock("mechanical_sifter", KineticWorkableMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CreateRecipeTypes.MECHANICAL_SIFTER_RECIPES)
            .appearanceBlock(AllBlocks.RAILWAY_CASING)
            .recipeModifier(CTPPRecipeModifiers.KINETIC_PARALLEL)
            .tooltips(CommonTooltips.KINETIC_OVERCLOCK)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAA", "BCCCB", "B###B", "AAAAA", "B###B", "BCCCB", "AAAAA")
                    .aisle("AAAAA", "CDDDC", "#EEE#", "ADDDA", "#####", "CFFFC", "AAAAA")
                    .aisle("AAAAA", "CDDDC", "#EEE#", "ADDDA", "#####", "CFFFC", "AAAAA")
                    .aisle("AAAAA", "CDDDC", "#EEE#", "ADDDA", "#####", "CFFFC", "AAAAA")
                    .aisle("AA@AA", "BCCCB", "B###B", "AAAAA", "B###B", "BCCCB", "AAAAA")
                    .where("A", Predicates.blocks(AllBlocks.RAILWAY_CASING.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.abilities(CTPPPartAbility.INPUT_KINETIC))
                            .or(Predicates.abilities(CTPPPartAbility.MECHANICAL_UPGRADE).setExactLimit(1)))
                    .where("B", Predicates.blocks(AllBlocks.METAL_GIRDER.get()))
                    .where("C", Predicates.blocks(AllBlocks.BRASS_CASING.get()))
                    .where("#", Predicates.any())
                    .where("D", Predicates.blocks(AllBlocks.BASIN.get()))
                    .where("E", Predicates.blocks(VintageBlocks.VIBRATING_TABLE.get()))
                    .where("F", Predicates.blocks(VintageBlocks.VACUUM_CHAMBER.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel(CTPP.id("block/create/railway_casing"),
                    GTCEu.id("block/multiblock/large_chemical_reactor"))
            .register();
    public final static MultiblockMachineDefinition MECHANICAL_EXTRACTOR = REGISTRATE
            .multiblock("mechanical_extractor", KineticWorkableMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CreateRecipeTypes.MECHANICAL_EXTRACTOR_RECIPES)
            .appearanceBlock(AllBlocks.RAILWAY_CASING)
            .recipeModifier(CTPPRecipeModifiers.KINETIC_PARALLEL)
            .tooltips(CommonTooltips.KINETIC_OVERCLOCK)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAA", "BCCCB", "BDDDB", "B###B", "B###B", "BDDDB", "AAAAA")
                    .aisle("AAAAA", "CEEEC", "DFFFD", "#GGG#", "#HHH#", "DIIID", "AAAAA")
                    .aisle("AAAAA", "CEEEC", "DFFFD", "#GGG#", "#HHH#", "DIIID", "AAAAA")
                    .aisle("AAAAA", "CEEEC", "DFFFD", "#GGG#", "#HHH#", "DIIID", "AAAAA")
                    .aisle("AA@AA", "BCCCB", "BDDDB", "B###B", "B###B", "BDDDB", "AAAAA")
                    .where("A", Predicates.blocks(AllBlocks.RAILWAY_CASING.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.abilities(CTPPPartAbility.INPUT_KINETIC))
                            .or(Predicates.abilities(CTPPPartAbility.MECHANICAL_UPGRADE).setExactLimit(1)))
                    .where("B", Predicates.blocks(AllBlocks.METAL_GIRDER.get()))
                    .where("C", Predicates.blocks(GLASS))
                    .where("D", Predicates.blocks(AllBlocks.BRASS_CASING.get()))
                    .where("#", Predicates.any())
                    .where("E", Predicates.blocks(AllBlocks.BLAZE_BURNER.get()))
                    .where("F", Predicates.blocks(CMBlocks.FOUNDRY_BASIN_BLOCK.get()))
                    .where("G", Predicates.blocks(AllBlocks.COPPER_CASING.get()))
                    .where("H", Predicates.blocks(CMBlocks.FOUNDRY_LID_BLOCK.get()))
                    .where("I", Predicates.blocks(CMBlocks.FOUNDRY_MIXER_BLOCK.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel(CTPP.id("block/create/railway_casing"),
                    GTCEu.id("block/multiblock/large_chemical_reactor"))
            .register();

    public final static MultiblockMachineDefinition MECHANICAL_LATHE = REGISTRATE
            .multiblock("mechanical_lathe", KineticWorkableMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CreateRecipeTypes.MECHANICAL_LATHE_RECIPES)
            .appearanceBlock(AllBlocks.RAILWAY_CASING)
            .recipeModifier(CTPPRecipeModifiers.KINETIC_PARALLEL)
            .tooltips(CommonTooltips.KINETIC_OVERCLOCK)
            .tooltips(Component.translatable("ctnh.mechanical_lathe.structure").withStyle(ChatFormatting.DARK_RED))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAAAAA", "AAAAAAAB", "AAACCCCB", "BBBBBBBB", "B######B", "BDDDDDDB", "AAAAAAAA")
                    .aisle("AAAAAAAA", "A#AEFFEA", "ADAGGGGC", "B######B", "########", "D######D", "AAAAAAAA")
                    .aisle("AAAAAAAA", "A#AEFFEA", "ADAGGGGC", "B######B", "########", "D######D", "AAAAAAAA")
                    .aisle("AAAAAAAA", "A#AEFFEA", "ADAGGGGC", "B######B", "########", "D######D", "AAAAAAAA")
                    .aisle("AAAAAAAA", "A@AGGGGB", "AAAGGGGB", "BBBBBBBB", "B######B", "BDDDDDDB", "AAAAAAAA")
                    .where("A", Predicates.blocks(AllBlocks.RAILWAY_CASING.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.abilities(CTPPPartAbility.INPUT_KINETIC))
                            .or(Predicates.abilities(CTPPPartAbility.MECHANICAL_UPGRADE).setExactLimit(1)))
                    .where("B", Predicates.blocks(AllBlocks.METAL_GIRDER.get()))
                    .where("C", Predicates.blocks(AllBlocks.ITEM_VAULT.get()))
                    .where("#", Predicates.any())
                    .where("D", Predicates.blocks(AllBlocks.BRASS_CASING.get()))
                    .where("E", Predicates.blocks(VintageBlocks.LATHE_MOVING.get()))
                    .where("F", Predicates.blocks(VintageBlocks.LATHE_ROTATING.get()))
                    .where("G", Predicates.blocks(CASING_TEMPERED_GLASS.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .shapeInfo(
                    definition -> MultiblockShapeInfo.builder()
                            .aisle("AAAAAAAA", "BGGGGA@A", "BGGGGAAA", "BBBBBBBB", "B######B", "BDDDDDDB", "AAAAAAAA")
                            .aisle("AAAAAAAA", "AYXFEA#A", "CGGGGADA", "B######B", "########", "D######D", "AAAAAAAA")
                            .aisle("AAAAAAAA", "AYXFEA#A", "CGGGGADA", "B######B", "########", "D######D", "AAAAAAAA")
                            .aisle("AAAAAAAA", "AYXFEA#A", "CGGGGADA", "B######B", "########", "D######D", "AAAAAAAA")
                            .aisle("AAAAAAAA", "BAAAAAAA", "BCCCCAAA", "BBBBBBBB", "B######B", "BDDDDDDB", "AAAAAAAA")
                            .where('A', AllBlocks.RAILWAY_CASING)
                            .where('B', AllBlocks.METAL_GIRDER)
                            .where('C', AllBlocks.ITEM_VAULT)
                            .where('#', BlockInfo.EMPTY)
                            .where('D', AllBlocks.BRASS_CASING)
                            .where('E',
                                    VintageBlocks.LATHE_MOVING.getDefaultState()
                                            .setValue(DirectionalKineticBlock.FACING, Direction.EAST))
                            .where('F', LATHE_WEST)
                            .where('Y',
                                    VintageBlocks.LATHE_MOVING.getDefaultState()
                                            .setValue(DirectionalKineticBlock.FACING, Direction.WEST))
                            .where('X', LATHE_EAST)
                            .where('G', CASING_TEMPERED_GLASS)
                            .where('@', Mechanical.MECHANICAL_LATHE, Direction.NORTH)
                            .build())
            .workableCasingModel(CTPP.id("block/create/railway_casing"),
                    GTCEu.id("block/multiblock/large_chemical_reactor"))
            .register();

    public static void init() {}
}
