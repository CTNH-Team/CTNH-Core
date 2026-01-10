package io.github.cpearl0.ctnhcore.registry.machines.multiblock;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.api.Pattern.CTNHPredicates;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.*;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.generator.Arc_Generator;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.generator.Arc_Reactor;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.generator.NanoscaleTriboelectricGenerator;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.generator.PhotoVoltaicDroneStation;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.kinetic.Hybrid_Power_Mixer;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.part.CTNHPartAbility;
import io.github.cpearl0.ctnhcore.registry.CTNHBlocks;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import io.github.cpearl0.ctnhcore.registry.machines.CTNHMachines;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;
import io.github.cpearl0.ctnhcore.utils.CTNHCommonTooltips;
import io.github.cpearl0.ctnhcore.utils.CTNHMachineUtils;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.AssemblyLineMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.LargeMinerMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.generator.LargeTurbineMachine;
import com.gregtechceu.gtceu.utils.FormattingUtil;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import com.mo_guang.ctpp.CTPP;
import com.mo_guang.ctpp.api.CTPPPartAbility;
import com.simibubi.create.AllBlocks;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.api.pattern.util.RelativeDirection.*;
import static com.gregtechceu.gtceu.common.data.GCYMBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTMaterialBlocks.MATERIAL_BLOCKS;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.DUMMY_RECIPES;
import static committee.nova.mods.avaritia.init.registry.ModBlocks.neutron;
import static io.github.cpearl0.ctnhcore.registry.CTNHBlocks.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;
import static net.minecraft.world.level.block.Blocks.*;

public class MultiblocksB {

    public final static MultiblockMachineDefinition SILICA_ROCK_FUEL_REFINERY = REGISTRATE
            .multiblock("silica_rock_fuel_refinery", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeTypes(CTNHRecipeTypes.SILICA_ROCK_FUEL_REFINERY)
            .appearanceBlock(CTNHBlocks.CASING_NAQUADAH_BLOCK)
            .recipeModifiers(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.PERFECT_OVERCLOCK_SUBTICK))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("##AAA##", "##ABA##", "##AAA##", "###B###", "###B###", "###A###")
                    .aisle("###A###", "##AAA##", "###C###", "##ADA##", "###A###", "###A###")
                    .aisle("A##A##A", "AAADAAA", "A#ACA#A", "#AACAA#", "##ADA##", "###C###")
                    .aisle("AAAAAAA", "BADDDAB", "ACCDCCA", "BDCDCDB", "BADDDAB", "AACCCAA")
                    .aisle("A##A##A", "AAADAAA", "A#ACA#A", "#AACAA#", "##ADA##", "###C###")
                    .aisle("###A###", "##AAA##", "###C###", "##ADA##", "###A###", "###A###")
                    .aisle("##AAA##", "##A@A##", "##AAA##", "###B###", "###B###", "###A###")
                    .where("#", Predicates.any())
                    .where("A", Predicates.blocks(CASING_NAQUADAH_BLOCK.get()).setMinGlobalLimited(75)
                            .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                            .or(Predicates.autoAbilities(definition.getRecipeTypes())))
                    .where("B", Predicates.frames(GTMaterials.NaquadahEnriched))
                    .where("C", Predicates.blocks(ANNIHILATE_CORE_MKI.get()))
                    .where("D", Predicates.blocks(PLASMA_COOLED_CORE.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel((CTNHCore.id("block/casings/nq_casing")),
                    GTCEu.id("block/multiblock/implosion_compressor"))
            .register();

    public static MultiblockMachineDefinition NANOGENERATOR = REGISTRATE
            .multiblock("nanogenetor", NanoscaleTriboelectricGenerator::new)
            .rotationState(RotationState.ALL)
            .recipeType(CTNHRecipeTypes.NANO_GENERATOR)
            .generator(true)
            .recipeModifier(NanoscaleTriboelectricGenerator::recipeModifier, true)
            .tooltips(Component.translatable("ctnh.multiblock.nano_generator.tooltip.0"),
                    Component.translatable("ctnh.multiblock.nano_generator.tooltip.1"),
                    Component.translatable("ctnh.multiblock.nano_generator.tooltip.2"),
                    Component.translatable("ctnh.multiblock.nano_generator.tooltip.3"))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("###B###", "###C###", "###C###", "###C###", "###C###", "###C###", "###D###", "###D###",
                            "###D###", "###D###", "###E###", "###F###", "###F###", "###D###")
                    .aisle("##EEE##", "##B#B##", "##C#C##", "##C#C##", "##C#C##", "##C#C##", "##C#C##", "##D#D##",
                            "##D#D##", "##DED##", "##E#E##", "##F#F##", "##F#F##", "##DDD##")
                    .aisle("#EEEEE#", "#GHHHI#", "#BHHHB#", "#CHHHC#", "#CHHHC#", "#CHHHC#", "#CHHHC#", "#CHHHC#",
                            "#DHHHD#", "#DHHHD#", "#E###E#", "#F###F#", "#F###F#", "#DJJJD#")
                    .aisle("EEEEEEE", "K#HLH#M", "N#HLH#G", "B#HLH#B", "C#HLH#C", "C#HLH#C", "C#HLH#C", "C#HLH#C",
                            "C#HLH#C", "DEHLHEO", "E##P##E", "F#####F", "F#####F", "DDJJJDD")
                    .aisle("#EEEEE#", "#GHHHI#", "#BHHHB#", "#CHHHC#", "#CHHHC#", "#CHHHC#", "#CHHHC#", "#CHHHC#",
                            "#DHHHD#", "#DHHHD#", "#E###E#", "#F###F#", "#F###F#", "#DJJJD#")
                    .aisle("##EEE##", "##B#B##", "##C#C##", "##C#C##", "##C#C##", "##C#C##", "##C#C##", "##D#D##",
                            "##D#D##", "##DED##", "##E#E##", "##F#F##", "##F#F##", "##DDD##")
                    .aisle("###B###", "###C###", "###C###", "###C###", "###C###", "###C###", "###D###", "###D###",
                            "###D###", "###D###", "###E###", "###F###", "###F###", "###D###")
                    .where("#", Predicates.any())
                    .where("B",
                            Predicates.blocks(ForgeRegistries.BLOCKS
                                    .getValue(ResourceLocation.parse("gtceu:polyethylene_block"))))
                    .where("C", Predicates.blocks(CASING_TEMPERED_GLASS.get()))
                    .where("D", Predicates.blocks(CASING_STEEL_PIPE.get()))
                    .where("E", Predicates.blocks(CASING_STEEL_GEARBOX.get()))
                    .where("F", Predicates.blocks(MACHINE_CASING_MV.get()))
                    .where("G",
                            Predicates.blocks(ForgeRegistries.BLOCKS
                                    .getValue(ResourceLocation.parse("gtceu:polyethylene_block"))))
                    .where("H", (Predicates.any()))
                    .where("I", (abilities(PartAbility.OUTPUT_ENERGY)))
                    .where("J", Predicates.blocks(PISTON))
                    .where("K", abilities(PartAbility.IMPORT_ITEMS))
                    .where("L", (Predicates.any()))
                    .where("M", abilities(PartAbility.MAINTENANCE))
                    .where("N", abilities(PartAbility.EXPORT_ITEMS))
                    .where("P", abilities(CTPPPartAbility.OUTPUT_KINETIC))
                    .where("O", Predicates.controller(Predicates.blocks(definition.get())))

                    .build())
            .workableCasingModel((GTCEu.id("block/casings/solid/machine_casing_solid_steel")),
                    GTCEu.id("block/multiblock/fusion_reactor"))
            .register();

    public final static MultiblockMachineDefinition FOREST_SEA_TREE_FARM = REGISTRATE
            .multiblock("forest_sea_tree_farm", ForestMachine::new)
            .rotationState(RotationState.ALL)
            .recipeTypes(CTNHRecipeTypes.WOOD_BIONICS)
            .appearanceBlock(CTNHBlocks.NATURAL_ECOLOGICAL_SHELL_CASING)
            .recipeModifiers(ForestMachine::recipeModifier,
                    GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .tooltips(Component.translatable("ctnh.multiblock.forest_sea.tooltip.1").withStyle(ChatFormatting.GRAY),
                    Component.translatable("ctnh.multiblock.forest_sea.tooltip.2"),
                    Component.translatable("ctnh.multiblock.forest_sea.tooltip.3"),
                    Component.translatable("ctnh.multiblock.forest_sea.tooltip.4"),
                    Component.translatable("ctnh.multiblock.forest_sea.tooltip.5"),
                    Component.translatable("ctnh.multiblock.forest_sea.tooltip.6"),
                    Component.translatable("ctnh.multiblock.forest_sea.tooltip.7"))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAA", "AAA", "AAA")
                    .aisle("AAA", "A#A", "AAA")
                    .aisle("AAA", "A@A", "AAA")
                    .where("A", Predicates.blocks(NATURAL_ECOLOGICAL_SHELL_CASING.get())
                            .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                            .or(Predicates.autoAbilities(definition.getRecipeTypes())))
                    .where("#", Predicates.any())
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel((CTNHCore.id("block/casings/natural_ecological_shell_casing")),
                    CTNHCore.id("block/overlay/forest_sea_tree_farm"))
            .register();
    public final static MultiblockMachineDefinition SINOPE_CHEMICAL = REGISTRATE
            .multiblock("sinope_chemical", Sinope_Chemical::new)
            .rotationState(RotationState.ALL)
            .recipeTypes(CTNHRecipeTypes.SINOPE, GTRecipeTypes.CRACKING_RECIPES)
            .recipeModifiers(Sinope_Chemical::recipeModifier,
                    GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK_SUBTICK))
            .tooltips(Component.translatable("ctnh.multiblock.sinope_chemical.tooltip.0"),
                    Component.translatable("ctnh.multiblock.sinope_chemical.tooltip.1"),
                    Component.translatable("ctnh.multiblock.sinope_chemical.tooltip.2"),
                    Component.translatable("ctnh.multiblock.sinope_chemical.tooltip.3"),
                    Component.translatable("ctnh.multiblock.sinope_chemical.tooltip.4"),
                    Component.translatable("ctnh.multiblock.sinope_chemical.tooltip.5"),
                    Component.translatable("ctnh.multiblock.sinope_chemical.tooltip.6"),
                    Component.translatable("ctnh.multiblock.sinope_chemical.tooltip.7"),
                    Component.translatable("ctnh.multiblock.sinope_chemical.tooltip.8"),
                    Component.translatable("ctnh.multiblock.sinope_chemical.tooltip.9"))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAAAAAA", "A#######A", "A#######A", "A#######A", "A#######A", "A#######A", "A#######A",
                            "A#######A", "AAAAAAAAA")
                    .aisle("AAAAAAAAA", "#BBBBBBB#", "#########", "#########", "#########", "#########", "#########",
                            "#BBBBBBB#", "AAAAAAAAA")
                    .aisle("AAAAAAAAA", "#BCCCCCB#", "##DDDDD##", "##EEEEE##", "##EEEEE##", "##EEEEE##", "##DDDDD##",
                            "#BCCCCCB#", "AAAAAAAAA")
                    .aisle("AAAAAAAAA", "#BCFFFCB#", "##DFFFD##", "##EGGGE##", "##EGGGE##", "##EGGGE##", "##DFFFD##",
                            "#BCFFFCB#", "AAAAAAAAA")
                    .aisle("AAAAAAAAA", "#BCFFFCB#", "##DFFFD##", "##EG#GE##", "##EGHGE##", "##EG#GE##", "##DFFFD##",
                            "#BCFFFCB#", "AAAAAAAAA")
                    .aisle("AAAAAAAAA", "#BCFFFCB#", "##DFFFD##", "##EGGGE##", "##EGGGE##", "##EGGGE##", "##DFFFD##",
                            "#BCFFFCB#", "AAAAAAAAA")
                    .aisle("AAAAAAAAA", "#BCCCCCB#", "##DD@DD##", "##EEEEE##", "##EEEEE##", "##EEEEE##", "##DDDDD##",
                            "#BCCCCCB#", "AAAAAAAAA")
                    .aisle("AAAAAAAAA", "#BBBBBBB#", "#########", "#########", "#########", "#########", "#########",
                            "#BBBBBBB#", "AAAAAAAAA")
                    .aisle("AAAAAAAAA", "A#######A", "A#######A", "A#######A", "A#######A", "A#######A", "A#######A",
                            "A#######A", "AAAAAAAAA")
                    .where("A", Predicates.blocks(CASING_ASSEMBLY_CONTROL.get()).setMinGlobalLimited(170)
                            .or(Predicates.autoAbilities(definition.getRecipeTypes())))
                    .where("#", Predicates.any())
                    .where("B", Predicates.frames(GTMaterials.Naquadah))
                    .where("C", Predicates.blocks(MACHINE_CASING_ZPM.get()))
                    .where("D", Predicates.blocks(CTNHBlocks.CASING_NAQUADAH_GEARBOX.get()))
                    .where("E", Predicates.blocks(CASING_LAMINATED_GLASS.get()))
                    .where("F", Predicates.blocks(MACHINE_CASING_ZPM.get()))
                    .where("G", Predicates.heatingCoils())
                    .where("H", Predicates.blocks(MATERIAL_BLOCKS.get(TagPrefix.block, Naquadah).get())
                            .or(Predicates.blocks(MATERIAL_BLOCKS.get(TagPrefix.block, Naquadria).get()))
                            .or(Predicates.blocks(MATERIAL_BLOCKS.get(TagPrefix.block, NaquadahEnriched).get())))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel((GTCEu.id("block/casings/mechanic/machine_casing_assembly_control")),
                    GTCEu.id("block/multiblock/fusion_reactor"))
            .register();
    public final static MultiblockMachineDefinition WIDE_PARTICLE_ACCELERATOR = REGISTRATE
            .multiblock("wide_particle_accelerator", WideParticleAccelerator::new)
            .rotationState(RotationState.ALL)
            .recipeTypes(CTNHRecipeTypes.ACCELERATOR_UP)

            .recipeModifiers(WideParticleAccelerator::recipeModifier, GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(
                    OverclockingLogic.NON_PERFECT_OVERCLOCK_SUBTICK))
            .tooltips(Component.translatable("ctnh.multiblock.wide_accelerator.tooltip.0"),
                    Component.translatable("ctnh.multiblock.wide_accelerator.tooltip.1"),
                    Component.translatable("ctnh.multiblock.wide_accelerator.tooltip.2"),
                    Component.translatable("ctnh.multiblock.wide_accelerator.tooltip.3"),
                    Component.translatable("ctnh.multiblock.wide_accelerator.tooltip.4"),
                    Component.translatable("ctnh.multiblock.wide_accelerator.tooltip.5"),
                    Component.translatable("ctnh.multiblock.wide_accelerator.tooltip.6"),
                    Component.translatable("ctnh.multiblock.wide_accelerator.tooltip.7"),
                    Component.translatable("ctnh.multiblock.wide_accelerator.tooltip.8"),
                    Component.translatable("ctnh.multiblock.wide_accelerator.tooltip.9"),
                    Component.translatable("ctnh.multiblock.wide_accelerator.tooltip.10"),
                    Component.translatable("ctnh.multiblock.wide_accelerator.tooltip.11"))

            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("###########################", "###########################", "###########################",
                            "#############C#############", "############CDC############", "###########CD#DC###########",
                            "############CDC############", "#############C#############", "###########################",
                            "###########################", "###########################")
                    .aisle("###########################", "###########################", "#############C#############",
                            "############CDC############", "###########C###C###########", "##########CD###DC##########",
                            "###########C###C###########", "############CDC############", "#############C#############",
                            "###########################", "###########################")
                    .aisle("############CCC############", "############DDD############", "############CDC############",
                            "###########CDDDC###########", "#########CCD###DCC#########", "#########DDD###DDD#########",
                            "#########CCD###DCC#########", "###########CDDDC###########", "############CDC############",
                            "############DDD############", "############CCC############")
                    .aisle("###########CCFCC###########", "###########DDFDD###########", "###########CCCCC###########",
                            "############CDC############", "########CC#C###C#CC########", "########DDCD###DCDD########",
                            "########CC#C###C#CC########", "############CDC############", "###########CCCCC###########",
                            "###########DDFDD###########", "###########CCFCC###########")
                    .aisle("##########CCGGGCC##########", "##########DDGGGDD##########", "##########CCGGGCC##########",
                            "###########GGGGG###########", "#######CC##GCDCG##CC#######", "#######DD##GD#DG##DD#######",
                            "#######CC##GCDCG##CC#######", "###########GGGGG###########", "##########CCGGGCC##########",
                            "##########DDGGGDD##########", "##########CCGGGCC##########")
                    .aisle("#########CC#####CC#########", "#########DD#####DD#########", "#########CC#####CC#########",
                            "##########G#####G##########", "######CC##G#HIH#G##CC######", "######DD##G#I#I#G##DD######",
                            "######CC##G#HIH#G##CC######", "##########G#####G##########", "#########CC#####CC#########",
                            "#########DD#####DD#########", "#########CC#####CC#########")
                    .aisle("########CC#######CC########", "########DD#######DD########", "########CC#######CC########",
                            "#########G#######G#########", "#####CC##G##HIH##G##CC#####", "#####DD##G##I#I##G##DD#####",
                            "#####CC##G##HIH##G##CC#####", "#########G#######G#########", "########CC#######CC########",
                            "########DD#######DD########", "########CC#######CC########")
                    .aisle("#######CC#########CC#######", "#######DD#########DD#######", "#######CC#########CC#######",
                            "########G#########G########", "####CC##G###HIH###G##CC####", "####DD##G###I#I###G##DD####",
                            "####CC##G###HIH###G##CC####", "########G#########G########", "#######CC#########CC#######",
                            "#######DD#########DD#######", "#######CC#########CC#######")
                    .aisle("######CC###########CC######", "######DD###########DD######", "######CC###########CC######",
                            "#######G###########G#######", "###CC##G####HIH####G##CC###", "###DD##G####I#I####G##DD###",
                            "###CC##G####HIH####G##CC###", "#######G###########G#######", "######CC###########CC######",
                            "######DD###########DD######", "######CC###########CC######")
                    .aisle("#####CC#############CC#####", "#####DD#############DD#####", "#####CC#############CC#####",
                            "######G#############G######", "##CC##G#####HIH#####G##CC##", "##DD##G#####I#I#####G##DD##",
                            "##CC##G#####HIH#####G##CC##", "######G#############G######", "#####CC#############CC#####",
                            "#####DD#############DD#####", "#####CC#############CC#####")
                    .aisle("####CC###############CC####", "####DD###############DD####", "####CC###############CC####",
                            "#####G###############G#####", "##C##G######HIH######G##C##", "#CDC#G######I#I######G#CDC#",
                            "##C##G######HIH######G##C##", "#####G###############G#####", "####CC###############CC####",
                            "####DD###############DD####", "####CC###############CC####")
                    .aisle("###CC#######JJJ#######CC###", "###DD#######JJJ#######DD###", "###CC#######JJJ#######CC###",
                            "##C#G#######JJJ#######G#C##", "#CDCG#######HIH#######GCDC#", "CDDDG#######I#I#######GDDDC",
                            "#CDCG#######HIH#######GCDC#", "##C#G#######JJJ#######G#C##", "###CC#######JJJ#######CC###",
                            "###DD#######JJJ#######DD###", "###CC#######JJJ#######CC###")
                    .aisle("##CCG######J###J######GCC##", "##DDG######J###J######GDD##", "##CCG######J###J######GCC##",
                            "#CDCG######JFFFJ######GCDC#", "C###CHHHHHHH###HHHHHHHC###C", "D###DIIIIIII###IIIIIIID###D",
                            "C###CHHHHHHH###HHHHHHHC###C", "#CDCG######JFFFJ######GCDC#", "##CCG######J###J######GCC##",
                            "##DDG######J###J######GDD##", "##CCG######J###J######GCC##")
                    .aisle("##CFG######J#H#J######GFC##", "##DFG######J#K#J######GFD##", "#CDCG######J#F#J######GCDC#",
                            "CDDDG######JFFFJ######GDDDC", "D###DIIIIIII#F#IIIIIIID###D", "D############F############D",
                            "D###DIIIIIII#F#IIIIIIID###D", "CDDDG######JFFFJ######GDDDC", "#CDCG######J#F#J######GCDC#",
                            "##DFG######J#K#J######GFD##", "##CFG######J#H#J######GFC##")
                    .aisle("##CCG######J###J######GCC##", "##DDG######J###J######GDD##", "##CCG######J###J######GCC##",
                            "#CDCG######JFFFJ######GCDC#", "C###CHHHHHHH###HHHHHHHC###C", "D###DIIIIIII###IIIIIIID###D",
                            "C###CHHHHHHH###HHHHHHHC###C", "#CDCG######JFFFJ######GCDC#", "##CCG######J###J######GCC##",
                            "##DDG######J###J######GDD##", "##CCG######J###J######GCC##")
                    .aisle("###CC#######JJJ#######CC###", "###DD#######JJJ#######DD###", "###CC#######JJJ#######CC###",
                            "##C#G#######JJJ#######G#C##", "#CDCG#######HIH#######GCDC#", "CDDDG#######I#I#######GDDDC",
                            "#CDCG#######HIH#######GCDC#", "##C#G#######JJJ#######G#C##", "###CC#######JJJ#######CC###",
                            "###DD#######JJJ#######DD###", "###CC#######JJJ#######CC###")
                    .aisle("####CC###############CC####", "####DD###############DD####", "####CC###############CC####",
                            "#####G###############G#####", "##C##G######HIH######G##C##", "#CDC#G######I#I######G#CDC#",
                            "##C##G######HIH######G##C##", "#####G###############G#####", "####CC###############CC####",
                            "####DD###############DD####", "####CC###############CC####")
                    .aisle("#####CC#############CC#####", "#####DD#############DD#####", "#####CC#############CC#####",
                            "######G#############G######", "##CC##G#####HIH#####G##CC##", "##DD##G#####I#I#####G##DD##",
                            "##CC##G#####HIH#####G##CC##", "######G#############G######", "#####CC#############CC#####",
                            "#####DD#############DD#####", "#####CC#############CC#####")
                    .aisle("######CC###########CC######", "######DD###########DD######", "######CC###########CC######",
                            "#######G###########G#######", "###CC##G####HIH####G##CC###", "###DD##G####I#I####G##DD###",
                            "###CC##G####HIH####G##CC###", "#######G###########G#######", "######CC###########CC######",
                            "######DD###########DD######", "######CC###########CC######")
                    .aisle("#######CC#########CC#######", "#######DD#########DD#######", "#######CC#########CC#######",
                            "########G#########G########", "####CC##G###HIH###G##CC####", "####DD##G###I#I###G##DD####",
                            "####CC##G###HIH###G##CC####", "########G#########G########", "#######CC#########CC#######",
                            "#######DD#########DD#######", "#######CC#########CC#######")
                    .aisle("########CC#######CC########", "########DD#######DD########", "########CC#######CC########",
                            "#########G#######G#########", "#####CC##G##HIH##G##CC#####", "#####DD##G##I#I##G##DD#####",
                            "#####CC##G##HIH##G##CC#####", "#########G#######G#########", "########CC#######CC########",
                            "########DD#######DD########", "########CC#######CC########")
                    .aisle("#########CC#####CC#########", "#########DD#####DD#########", "#########CC#####CC#########",
                            "##########G#####G##########", "######CC##G#HIH#G##CC######", "######DD##G#I#I#G##DD######",
                            "######CC##G#HIH#G##CC######", "##########G#####G##########", "#########CC#####CC#########",
                            "#########DD#####DD#########", "#########CC#####CC#########")
                    .aisle("##########CCGGGCC##########", "##########DDGGGDD##########", "##########CCGGGCC##########",
                            "###########GGGGG###########", "#######CC##GCDCG##CC#######", "#######DD##GD#DG##DD#######",
                            "#######CC##GCDCG##CC#######", "###########GGGGG###########", "##########CCGGGCC##########",
                            "##########DDGGGDD##########", "##########CCGGGCC##########")
                    .aisle("###########CCFCC###########", "###########DDFDD###########", "###########CCCCC###########",
                            "############CDC############", "########CC#C###C#CC########", "########DDCD###DCDD########",
                            "########CC#C###C#CC########", "############CDC############", "###########CCCCC###########",
                            "###########DDFDD###########", "###########CCFCC###########")
                    .aisle("############CCC############", "############DDD############", "############CDC############",
                            "###########CDDDC###########", "#########CCD###DCC#########", "#########DDD###DDD#########",
                            "#########CCD###DCC#########", "###########CDDDC###########", "############CDC############",
                            "############DDD############", "############CCC############")
                    .aisle("###########################", "###########################", "#############C#############",
                            "############CDC############", "###########C###C###########", "##########CD###DC##########",
                            "###########C###C###########", "############CDC############", "#############C#############",
                            "###########################", "###########################")
                    .aisle("###########################", "###########################", "###########################",
                            "#############C#############", "############CDC############", "###########CDEDC###########",
                            "############CDC############", "#############C#############", "###########################",
                            "###########################", "###########################")
                    .where("#", Predicates.any())
                    .where("C", Predicates.blocks(CASING_NAQUADAH_BLOCK.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(abilities(PartAbility.INPUT_LASER).setMaxGlobalLimited(2))
                            .or(abilities(PartAbility.SUBSTATION_INPUT_ENERGY).setMaxGlobalLimited(2))
                            .or(abilities(PartAbility.PARALLEL_HATCH).setMaxGlobalLimited(1)))
                    .where("D", Predicates.blocks(WIDESPEEDINGPIPE.get()))
                    .where("E", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("F", Predicates.blocks(neutron.get()))
                    .where("G", Predicates.blocks(HERMETIC_CASING_UHV.get()))
                    .where("H", Predicates.blocks(MACHINE_CASING_UHV.get()))
                    .where("I", Predicates.blocks(SUPERCONDUCTING_COIL.get()))
                    .where("J", Predicates.blocks(HIGH_POWER_CASING.get()))
                    .where("K", Predicates.blocks(HIGH_POWER_CASING.get()))
                    .build())
            .workableCasingModel((GTCEu.id("block/casings/mechanic/machine_casing_assembly_control")),
                    GTCEu.id("block/multiblock/fusion_reactor"))
            .register();

    public final static MultiblockMachineDefinition ARC_GENERATOR = REGISTRATE
            .multiblock("arc_generator", holder -> new Arc_Generator(holder, 0.75, 1000))
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.ARC_GENERATOR)
            .generator(true)
            .recipeModifier(Arc_Generator::recipeModifier, true)
            .tooltips(Component.translatable("ctnh.multiblock.arcgenerator.tooltip.1"),
                    Component.translatable("ctnh.multiblock.arcgenerator.tooltip.arc.t1.1"),
                    Component.translatable("ctnh.multiblock.arcgenerator.tooltip.arc.t1.2"),
                    Component.translatable("ctnh.multiblock.arcgenerator.tooltip.2"),
                    Component.translatable("ctnh.multiblock.arcgenerator.tooltip.3"),
                    Component.translatable("ctnh.multiblock.arcgenerator.tooltip.4"),
                    Component.translatable("ctnh.multiblock.arcgenerator.tooltip.5"))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "###B#############B###", "#####################", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###B#############B###",
                            "##BCB###########BCB##", "###B#############B###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "###B#############B###", "##BCB###########BCB##",
                            "#BCDCBBBBBBBBBBBCDCB#", "##BCB#####B#####BCB##", "###B######B######B###",
                            "##########B##########")
                    .aisle("###B#############B###", "###B#############B###", "###B#############B###",
                            "###B#############B###", "##BCB###########BCB##", "#BCDCBBBBBBBBBBBCDCB#",
                            "BCDFDDDDDDDDDDDDDFDCB", "#BCDCBBBBBFBBBBBCDCB#", "##BCB####BFB####BCB##",
                            "###B#####BFB#####B###")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "###B#############B###", "##BCGGGGGGHCCCCCGCB##",
                            "#BCDGFFFFFHFFFFFGDCB#", "##BCGCCCCCHGGGGGGCB##", "###B######B######B###",
                            "##########B##########")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BG###########CB###",
                            "##BDF###########FDB##", "###BC###########GB###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BG###########CB###",
                            "##BDF###########FDB##", "###BC###########GB###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BG###########CB###",
                            "##BDF###########FDB##", "###BC###########GB###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BG###########CB###",
                            "##BDF###########FDB##", "###BC###########GB###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BG####CIC####CB###",
                            "##BDF####IJI####FDB##", "###BC####CIC####GB###", "###B#############B###",
                            "###B#############B###")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BH####IJI####HB###",
                            "##BDH####JJJ####HDB##", "##BFH####IJI####HFB##", "##BFB###########BFB##",
                            "##BFB###########BFB##")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BC####CIC####GB###",
                            "##BDF####IJI####FDB##", "###BG####CIC####CB###", "###B#############B###",
                            "###B#############B###")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BC###########GB###",
                            "##BDF###########FDB##", "###BG###########CB###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BC###########GB###",
                            "##BDF###########FDB##", "###BG###########CB###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BC###########GB###",
                            "##BDF###########FDB##", "###BG###########CB###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BC###########GB###",
                            "##BDF###########FDB##", "###BG###########CB###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "###B#############B###", "##BCGCCCCCHGGGGGGCB##",
                            "#BCDGFFFFFHFFFFFGDCB#", "##BCGGGGGGHCCCCCGCB##", "###B######B######B###",
                            "##########B##########")
                    .aisle("###B#############B###", "###B#############B###", "###B#############B###",
                            "###B#############B###", "##BCB###########BCB##", "#BCDCBBBBBBBBBBBCDCB#",
                            "BCDFDDDDDDDDDDDDDFDCB", "#BCDCBBBBBFBBBBBCDCB#", "##BCB####BFB####BCB##",
                            "###B#####BFB#####B###")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "###B#############B###", "##BCB###########BCB##",
                            "#BCDCBBBBBEBBBBBCDCB#", "##BCB#####B#####BCB##", "###B######B######B###",
                            "##########B##########")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###B#############B###",
                            "##BCB###########BCB##", "###B#############B###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "###B#############B###", "#####################", "#####################",
                            "#####################")
                    .where("#", Predicates.any())
                    .where("B", Predicates.blocks(CASING_NONCONDUCTING.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes())))

                    .where("C", Predicates.frames(TungstenSteel))
                    .where("D", Predicates.blocks(CASING_TUNGSTENSTEEL_PIPE.get()))
                    .where("E", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("F", Predicates.blocks(ARC_CELL.get()))
                    .where("G", Predicates.blocks(COIL_HSSG.get()))
                    .where("H", Predicates.blocks(HERMETIC_CASING_IV.get()))
                    .where("I", Predicates.blocks(MACHINE_CASING_IV.get()))
                    .where("J", Predicates.blocks(MATERIAL_BLOCKS.get(TagPrefix.block, TungstenSteel).get()))
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/gcym/nonconducting_casing"),
                    GTCEu.id("block/multiblock/generator/large_steam_turbine"))
            .register();
    public final static MultiblockMachineDefinition ARC_GENERATOR_MK1 = REGISTRATE
            .multiblock("arc_generator_mk1", holder -> new Arc_Generator(holder, 1.25, 10000))
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.ARC_GENERATOR)
            .generator(true)
            .recipeModifier(Arc_Generator::recipeModifier, true)
            .tooltips(Component.translatable("ctnh.multiblock.arcgenerator.tooltip.t2.1"),
                    Component.translatable("ctnh.multiblock.arcgenerator.tooltip.arc.t2.1"),
                    Component.translatable("ctnh.multiblock.arcgenerator.tooltip.arc.t2.2"),
                    Component.translatable("ctnh.multiblock.arcgenerator.tooltip.2"),
                    Component.translatable("ctnh.multiblock.arcgenerator.tooltip.3"),
                    Component.translatable("ctnh.multiblock.arcgenerator.tooltip.4"),
                    Component.translatable("ctnh.multiblock.arcgenerator.tooltip.5"))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "###B#############B###", "#####################", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###B#############B###",
                            "##BCB###########BCB##", "###B#############B###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "###B#############B###", "##BCB###########BCB##",
                            "#BCDCBBBBBBBBBBBCDCB#", "##BCB#####B#####BCB##", "###B######B######B###",
                            "##########B##########")
                    .aisle("###B#############B###", "###B#############B###", "###B#############B###",
                            "###B#############B###", "##BCB###########BCB##", "#BCDCBBBBBBBBBBBCDCB#",
                            "BCDFDDDDDDDDDDDDDFDCB", "#BCDCBBBBBFBBBBBCDCB#", "##BCB####BFB####BCB##",
                            "###B#####BFB#####B###")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "###B#############B###", "##BCGGGGGGHCCCCCGCB##",
                            "#BCDGFFFFFHFFFFFGDCB#", "##BCGCCCCCHGGGGGGCB##", "###B######B######B###",
                            "##########B##########")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BG###########CB###",
                            "##BDF###########FDB##", "###BC###########GB###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BG###########CB###",
                            "##BDF###########FDB##", "###BC###########GB###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BG###########CB###",
                            "##BDF###########FDB##", "###BC###########GB###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BG###########CB###",
                            "##BDF###########FDB##", "###BC###########GB###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BG####CIC####CB###",
                            "##BDF####IJI####FDB##", "###BC####CIC####GB###", "###B#############B###",
                            "###B#############B###")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BH####IJI####HB###",
                            "##BDH####JJJ####HDB##", "##BFH####IJI####HFB##", "##BFB###########BFB##",
                            "##BFB###########BFB##")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BC####CIC####GB###",
                            "##BDF####IJI####FDB##", "###BG####CIC####CB###", "###B#############B###",
                            "###B#############B###")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BC###########GB###",
                            "##BDF###########FDB##", "###BG###########CB###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BC###########GB###",
                            "##BDF###########FDB##", "###BG###########CB###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BC###########GB###",
                            "##BDF###########FDB##", "###BG###########CB###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BC###########GB###",
                            "##BDF###########FDB##", "###BG###########CB###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "###B#############B###", "##BCGCCCCCHGGGGGGCB##",
                            "#BCDGFFFFFHFFFFFGDCB#", "##BCGGGGGGHCCCCCGCB##", "###B######B######B###",
                            "##########B##########")
                    .aisle("###B#############B###", "###B#############B###", "###B#############B###",
                            "###B#############B###", "##BCB###########BCB##", "#BCDCBBBBBBBBBBBCDCB#",
                            "BCDFDDDDDDDDDDDDDFDCB", "#BCDCBBBBBFBBBBBCDCB#", "##BCB####BFB####BCB##",
                            "###B#####BFB#####B###")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "###B#############B###", "##BCB###########BCB##",
                            "#BCDCBBBBBEBBBBBCDCB#", "##BCB#####B#####BCB##", "###B######B######B###",
                            "##########B##########")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###B#############B###",
                            "##BCB###########BCB##", "###B#############B###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "###B#############B###", "#####################", "#####################",
                            "#####################")
                    .where("#", Predicates.any())
                    .where("B", Predicates.blocks(CASING_NONCONDUCTING.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes())))

                    .where("C", Predicates.frames(TungstenSteel))
                    .where("D", Predicates.blocks(CASING_TUNGSTENSTEEL_PIPE.get()))
                    .where("E", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("F", Predicates.blocks(ARC_CELL.get()))
                    .where("G", Predicates.blocks(COIL_HSSG.get()))
                    .where("H", Predicates.blocks(HERMETIC_CASING_IV.get()))
                    .where("I", Predicates.blocks(MACHINE_CASING_IV.get()))
                    .where("J", Predicates.blocks(MATERIAL_BLOCKS.get(TagPrefix.block, TungstenSteel).get()))
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/gcym/nonconducting_casing"),
                    GTCEu.id("block/multiblock/generator/large_steam_turbine"))
            .register();
    public final static MultiblockMachineDefinition ARC_GENERATOR_MK2 = REGISTRATE
            .multiblock("arc_generator_mk2", holder -> new Arc_Generator(holder, 2.25, 50000))
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.ARC_GENERATOR)
            .generator(true)
            .recipeModifier(Arc_Generator::recipeModifier, true)
            .tooltips(Component.translatable("ctnh.multiblock.arcgenerator.tooltip.t3.1"),
                    Component.translatable("ctnh.multiblock.arcgenerator.tooltip.arc.t3.1"),
                    Component.translatable("ctnh.multiblock.arcgenerator.tooltip.arc.t3.2"),
                    Component.translatable("ctnh.multiblock.arcgenerator.tooltip.2"),
                    Component.translatable("ctnh.multiblock.arcgenerator.tooltip.3"),
                    Component.translatable("ctnh.multiblock.arcgenerator.tooltip.4"),
                    Component.translatable("ctnh.multiblock.arcgenerator.tooltip.5"))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "###B#############B###", "#####################", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###B#############B###",
                            "##BCB###########BCB##", "###B#############B###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "###B#############B###", "##BCB###########BCB##",
                            "#BCDCBBBBBBBBBBBCDCB#", "##BCB#####B#####BCB##", "###B######B######B###",
                            "##########B##########")
                    .aisle("###B#############B###", "###B#############B###", "###B#############B###",
                            "###B#############B###", "##BCB###########BCB##", "#BCDCBBBBBBBBBBBCDCB#",
                            "BCDFDDDDDDDDDDDDDFDCB", "#BCDCBBBBBFBBBBBCDCB#", "##BCB####BFB####BCB##",
                            "###B#####BFB#####B###")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "###B#############B###", "##BCGGGGGGHCCCCCGCB##",
                            "#BCDGFFFFFHFFFFFGDCB#", "##BCGCCCCCHGGGGGGCB##", "###B######B######B###",
                            "##########B##########")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BG###########CB###",
                            "##BDF###########FDB##", "###BC###########GB###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BG###########CB###",
                            "##BDF###########FDB##", "###BC###########GB###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BG###########CB###",
                            "##BDF###########FDB##", "###BC###########GB###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BG###########CB###",
                            "##BDF###########FDB##", "###BC###########GB###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BG####CIC####CB###",
                            "##BDF####IJI####FDB##", "###BC####CIC####GB###", "###B#############B###",
                            "###B#############B###")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BH####IJI####HB###",
                            "##BDH####JJJ####HDB##", "##BFH####IJI####HFB##", "##BFB###########BFB##",
                            "##BFB###########BFB##")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BC####CIC####GB###",
                            "##BDF####IJI####FDB##", "###BG####CIC####CB###", "###B#############B###",
                            "###B#############B###")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BC###########GB###",
                            "##BDF###########FDB##", "###BG###########CB###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BC###########GB###",
                            "##BDF###########FDB##", "###BG###########CB###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BC###########GB###",
                            "##BDF###########FDB##", "###BG###########CB###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###BC###########GB###",
                            "##BDF###########FDB##", "###BG###########CB###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "###B#############B###", "##BCGCCCCCHGGGGGGCB##",
                            "#BCDGFFFFFHFFFFFGDCB#", "##BCGGGGGGHCCCCCGCB##", "###B######B######B###",
                            "##########B##########")
                    .aisle("###B#############B###", "###B#############B###", "###B#############B###",
                            "###B#############B###", "##BCB###########BCB##", "#BCDCBBBBBBBBBBBCDCB#",
                            "BCDFDDDDDDDDDDDDDFDCB", "#BCDCBBBBBFBBBBBCDCB#", "##BCB####BFB####BCB##",
                            "###B#####BFB#####B###")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "###B#############B###", "##BCB###########BCB##",
                            "#BCDCBBBBBEBBBBBCDCB#", "##BCB#####B#####BCB##", "###B######B######B###",
                            "##########B##########")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "###B#############B###",
                            "##BCB###########BCB##", "###B#############B###", "#####################",
                            "#####################")
                    .aisle("#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "###B#############B###", "#####################", "#####################",
                            "#####################")
                    .where("#", Predicates.any())
                    .where("B", Predicates.blocks(CASING_NONCONDUCTING.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes())))

                    .where("C", Predicates.frames(TungstenSteel))
                    .where("D", Predicates.blocks(CASING_TUNGSTENSTEEL_PIPE.get()))
                    .where("E", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("F", Predicates.blocks(ARC_CELL.get()))
                    .where("G", Predicates.blocks(COIL_HSSG.get()))
                    .where("H", Predicates.blocks(HERMETIC_CASING_IV.get()))
                    .where("I", Predicates.blocks(MACHINE_CASING_IV.get()))
                    .where("J", Predicates.blocks(MATERIAL_BLOCKS.get(TagPrefix.block, TungstenSteel).get()))
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/gcym/nonconducting_casing"),
                    GTCEu.id("block/multiblock/generator/large_steam_turbine"))
            .register();
    public final static MultiblockMachineDefinition ARC_REACTOR = REGISTRATE
            .multiblock("arc_reactor", holder -> new Arc_Reactor(holder, 10))
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.ARC_REACTOR)
            .recipeModifier(Arc_Reactor::recipeModifier)
            .tooltips(Component.translatable("ctnh.multiblock.tooltip.arcreactor.1"),
                    Component.translatable("ctnh.multiblock.tooltip.arcreactor.2"),
                    Component.translatable("ctnh.multiblock.tooltip.arcreactor.t1"))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("#########B########", "#########B########", "#########B########", "#########B########",
                            "#########B########", "#########B########", "##################", "##################")
                    .aisle("########BDB#######", "########BDB#######", "########BDB#######", "########BDB#######",
                            "########BDB#######", "#########B########", "##################", "##################")
                    .aisle("#########B########", "#########B########", "#########B########", "#########B########",
                            "########BDB#######", "#########B########", "##################", "##################")
                    .aisle("##################", "##################", "##################", "#########B########",
                            "########BDB#######", "#########B########", "##################", "##################")
                    .aisle("##################", "##################", "##################", "#########B########",
                            "########BDB#######", "#########B########", "##################", "##################")
                    .aisle("##################", "#########B########", "#########B########", "#########B########",
                            "######BBBDBBB#####", "#########B########", "#########B########", "#########B########")
                    .aisle("##################", "#########B########", "########BDB#######", "#######BBDBB######",
                            "######BDDDDDB#####", "#######BBDBB######", "########BDB#######", "#########B########")
                    .aisle("##B#############B#", "##B######B######B#", "##B####BBDBB####B#", "##B####B###B####B#",
                            "##BBBBBD###DBBBBB#", "#######B###B######", "#######BBDBB######", "#########B########")
                    .aisle("#BDB###########BDB", "#BDB##BBBBBBB##BDB", "#BDB##BDDDDDB##BDB", "#BDBBBBD###DBBBBDB",
                            "#BDDDDDD###DDDDDDB", "#BBBBBBD###DBBBBBB", "######BDDDDDB#####", "######BBBBBBB#####")
                    .aisle("##B#############B#", "##B######B######B#", "##B####BBDBB####B#", "##B####B###B####B#",
                            "##BBBBBD###DBBBBB#", "#######B###B######", "#######BBDBB######", "#########B########")
                    .aisle("##################", "#########B########", "########BDB#######", "#######BBDBB######",
                            "######BDDDDDB#####", "#######BBDBB######", "########BDB#######", "#########B########")
                    .aisle("##################", "#########B########", "#########B########", "#########B########",
                            "######BBBDBBB#####", "#########B########", "#########B########", "#########B########")
                    .aisle("##################", "##################", "##################", "#########B########",
                            "########BDB#######", "#########B########", "##################", "##################")
                    .aisle("##################", "##################", "##################", "#########B########",
                            "########BDB#######", "#########B########", "##################", "##################")
                    .aisle("#########B########", "#########B########", "#########B########", "#########B########",
                            "########BDB#######", "#########B########", "##################", "##################")
                    .aisle("########BDB#######", "########BDB#######", "########BDB#######", "########BDB#######",
                            "########BDB#######", "#########B########", "##################", "##################")
                    .aisle("#########B########", "#########@########", "#########B########", "#########B########",
                            "#########B########", "#########B########", "##################", "##################")
                    .where("#", Predicates.any())
                    .where("B", Predicates.blocks(CASING_NONCONDUCTING.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes())))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("D", Predicates.blocks(ARC_CELL.get()))
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/gcym/nonconducting_casing"),
                    GTCEu.id("block/multiblock/generator/large_steam_turbine"))
            .register();

    public final static MultiblockMachineDefinition SUPERCONDUCTING_PENNING_TRAP = REGISTRATE.multiblock(
            "superconducting_penning_trap", Superconducting_Penning_Trap::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(DUMMY_RECIPES)
            // .recipeModifiers(Superconducting_Penning_Trap::recipeModifier)
            .tooltips(Component.translatable("ctnh.trap.1"),
                    Component.translatable("ctnh.trap.2"),
                    Component.translatable("ctnh.trap.3"),
                    Component.translatable("ctnh.trap.4"),
                    Component.translatable("ctnh.trap.5"))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("A###############BBBBBBB###############A", "###############CCCCCCCCC###############",
                            "###############CCCCCCCCC###############", "###############CCCCCCCCC###############",
                            "################BBBBBBB################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "##################D#D##################", "###################B###################",
                            "##################D#D##################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("#############BBBEEEEEEEBBB#############", "############CCC#########CCC############",
                            "############CCC#########CCC############", "############CCC#########CCC############",
                            "#############BBBEEEEEEEBBB#############", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "##################D#D##################", "###################B###################",
                            "##################D#D##################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("###########BBEEEEEEEEEEEEEBB###########", "##########CC###############CC##########",
                            "##########CC###############CC##########", "##########CC###############CC##########",
                            "###########BBEEEEEEEEEEEEEBB###########", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "##################D#D##################", "###################B###################",
                            "##################D#D##################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("#########BBEEEEEEEEEEEEEEEEEBB#########", "########CC###################CC########",
                            "########CC###################CC########", "########CC###################CC########",
                            "#########BBEEEEEEEEEEEEEEEEEBB#########", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "##################D#D##################", "###################B###################",
                            "##################D#D##################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("########BEEEEEEEBBBEBBBEEEEEEEB########", "#######C#########CCCCC#########C#######",
                            "#######C#########CCCCC#########C#######", "#######C#########CCCCC#########C#######",
                            "########BEEEEEEEBBBBBBBEEEEEEEB########", "#################FFFFF#################",
                            "#################FGGGF#################", "#################FGGGF#################",
                            "#################FGGGF#################", "#################FGGGF#################",
                            "#################FFHFF#################", "#######################################",
                            "##################D#D##################", "#################GGBGG#################",
                            "##################D#D##################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("#######BEEEEEBBBIIIEIIIBBBEEEEEB#######", "######C#######CCC#####CCC#######C######",
                            "######C#######CCC#####CCC#######C######", "######C#######CCC#####CCC#######C######",
                            "#######BEEEEEBBB#######BBBEEEEEB#######", "##############FFF#####FFF##############",
                            "##############JJJ#####JJJ##############", "##############JJJ#####JJJ##############",
                            "##############JJJ#####JJJ##############", "##############JJJ#####JJJ##############",
                            "##############FFF#FFF#FFF##############", "#######################################",
                            "##################D#D##################", "##############GGG##B##GGG##############",
                            "##################D#D##################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("######BEEEEBBIIIIIIEIIIIIIBBEEEEB######", "#####C######CC###########CC######C#####",
                            "#####C######CC###########CC######C#####", "#####C######CC###########CC######C#####",
                            "######BEEEEBB#############BBEEEEB######", "############FF###########FF############",
                            "############JJ###########JJ############", "############JJ###########JJ############",
                            "############JJ###########JJ############", "############JJ###########JJ############",
                            "############FF####FFF####FF############", "#######################################",
                            "##################D#D##################", "############GG#####B#####GG############",
                            "##################D#D##################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("#####BEEEBBIIIIIIIIEIIIIIIIIBBEEEB#####", "####C#####CC###############CC#####C####",
                            "####C#####CC###############CC#####C####", "####C#####CC###############CC#####C####",
                            "#####BEEEBB#################BBEEEB#####", "##########FF###############FF##########",
                            "##########JJ###############JJ##########", "##########JJ###############JJ##########",
                            "##########JJ###############JJ##########", "##########JJ###############JJ##########",
                            "##########FF######FFF######FF##########", "#######################################",
                            "##################D#D##################", "##########GG#######B#######GG##########",
                            "##################D#D##################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("####BEEEBIIIIIIIIIIEIIIIIIIIIIBEEEB####", "###C#####C###################C#####C###",
                            "###C#####C###################C#####C###", "###C#####C###################C#####C###",
                            "####BEEEB#####################BEEEB####", "#########F###################F#########",
                            "#########G###################G#########", "#########G###################G#########",
                            "#########G###################G#########", "#########G#######FFFFF#######G#########",
                            "#########F#######GGHGG#######F#########", "#########G#######FFFFF#######G#########",
                            "#########G#######BBBBB#######G#########", "#########F#######GGGGG#######F#########",
                            "#################KKDKK#################", "#################DDDDD#################",
                            "#################KKKKK#################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("###BEEEBIIIIIIIIIIIEIIIIIIIIIIIBEEEB###", "###C####C#####################C####C###",
                            "###C####C#####################C####C###", "###C####C#####################C####C###",
                            "###BEEEB#######################BEEEB###", "########F#####################F########",
                            "########G#####################G########", "########G#####################G########",
                            "########G#####################G########", "########G#######FF###FF#######G########",
                            "########F#######GG###GG#######F########", "########G#######FF###FF#######G########",
                            "########G#######BB###BB#######G########", "########FFF#####GG###GG#####FFF########",
                            "################KK###KK################", "################DD#D#DD################",
                            "################KK###KK################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("###BEEEBIIIIIIIIIIIEIIIIIIIIIIIBEEEB###", "##C#L##C#######################C####C##",
                            "##C####C#######################C####C##", "##C####C#######################C####C##",
                            "###BEEEB#######################BEEEB###", "#######F#######################F#######",
                            "#######J#######################J#######", "#######J#######################J#######",
                            "#######J#######################J#######", "#######J#######FF#####FF#######J#######",
                            "#######F#######GG#####GG#######F#######", "###############FF#####FF###############",
                            "###############BB#####BB###############", "#######G#FFF###GG#####GG###FFF#G#######",
                            "###############KK#####KK###############", "###############DD##D##DD###############",
                            "###############KK#####KK###############", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("##BEEEBIIIIIIIIIIIIEIIIIIIIIIIIIBEEEB##", "##C####C#######################C####C##",
                            "##C####C#######################C####C##", "##C####C#######################C####C##",
                            "##BEEEB#########################BEEEB##", "#######F#######################F#######",
                            "#######J#######################J#######", "#######J#######################J#######",
                            "#######J#######################J#######", "#######J######FF#######FF######J#######",
                            "#######F######GG#######GG######F#######", "##############FF#######FF##############",
                            "##############BB#######BB##############", "#######G##FFFFGG#######GGFFFF##G#######",
                            "##############KK#######KK##############", "##############DD###D###DD##############",
                            "##############KK#######KK##############", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("##BEEEBIIIIIIIIIIIIEIIIIIIIIIIIIBEEEB##", "#C####C#########################C####C#",
                            "#C####C#########################C####C#", "#C####C#########################C####C#",
                            "##BEEEB#########################BEEEB##", "######F#########################F######",
                            "######J#########################J######", "######J#########################J######",
                            "######J#########################J######", "######J######FF#########FF######J######",
                            "######F######GG#########GG######F######", "#############FF#########FF#############",
                            "#############BB#########BB#############", "######G####FFGG#########GGFF####G######",
                            "#############KK#########KK#############", "#############DD####D####DD#############",
                            "#############KK#########KK#############", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("#BEEEBIIIIIIIIIIIIIEIIIIIIIIIIIIIBEEEB#", "#C####C#########################C####C#",
                            "#C####C#########################C####C#", "#C####C#########################C####C#",
                            "#BEEEB###########################BEEEB#", "######F#########################F######",
                            "######J#########################J######", "######J#########################J######",
                            "######J#########################J######", "######J#####FF###########FF#####J######",
                            "######F#####GG###########GG#####F######", "############FF###########FF############",
                            "############BB###########BB############", "######G####FGG###########GGF####G######",
                            "############KK###########KK############", "############DD#####D#####DD############",
                            "############KK###########KK############", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("#BEEEBIIIIIIIIIIIIIEIIIIIIIIIIIIIBEEEB#", "#C###C###########################C###C#",
                            "#C###C###########################C###C#", "#C###C###########################C###C#",
                            "#BEEEB###########################BEEEB#", "#####F###########################F#####",
                            "#####J###########################J#####", "#####J###########################J#####",
                            "#####J###########################J#####", "#####J#####FF#############FF#####J#####",
                            "#####F#####GG#############GG#####F#####", "###########FF#############FF###########",
                            "###########BB#############BB###########", "#####G#####GG#############GG#####G#####",
                            "###########KK#############KK###########", "###########DD######D######DD###########",
                            "###########KK#############KK###########", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("#BEEEBIIIIIIIIIIIIIEIIIIIIIIIIIIIBEEEB#", "C####C###########################C####C",
                            "C####C###########################C####C", "C####C###########################C####C",
                            "#BEEEB###########################BEEEB#", "#####F###########################F#####",
                            "#####J###########################J#####", "#####J###########################J#####",
                            "#####J###########################J#####", "#####J####FF###############FF####J#####",
                            "#####F####GG###############GG####F#####", "##########FF###############FF##########",
                            "##########BB###############BB##########", "#####G####GG###############GG####G#####",
                            "##########KK###############KK##########", "##########DD#######D#######DD##########",
                            "##########KK###############KK##########", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("BEEEBIIIIIIIIIIIIIIEIIIIIIIIIIIIIIBEEEB", "C####C###########################C####C",
                            "C####C###########################C####C", "C####C###########################C####C",
                            "BEEEB#############################BEEEB", "#####F###########################F#####",
                            "#####J###########################J#####", "#####J###########################J#####",
                            "#####J###########################J#####", "#####J###FF#################FF###J#####",
                            "#####F###GG#################GG###F#####", "#########FF#################FF#########",
                            "#########BB#################BB#########", "#####G###GG#################GG###G#####",
                            "#########KK#################KK#########", "#########DD########D########DD#########",
                            "#########KK#################KK#########", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("BEEEBIIIIIIIIIIIIIIEIIIIIIIIIIIIIIBEEEB", "C###C#############################C###C",
                            "C###C#############################C###C", "C###C#############################C###C",
                            "BEEEB#############################BEEEB", "####F#############################F####",
                            "####F#############################F####", "####F#############################F####",
                            "####F#############################F####", "####F###FF#########M#########FF###F####",
                            "####F###GG########MHM########GG###F####", "########FF#########M#########FF########",
                            "########BB###################BB########", "####G###GG###################GG###G####",
                            "########KK###################KK########", "########DD#########D#########DD########",
                            "########KK###################KK########", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("BEEEBIIIIIIIIIIIIIIEIIIIIIIIIIIIIIBEEEB", "C###C##############M##############C###C",
                            "C###C##############M##############C###C", "C###C##############M##############C###C",
                            "BEEEB##############M##############BEEEB", "####F##############M##############F####",
                            "####G##############M##############G####", "####G##############M##############G####",
                            "####G##############M##############G####", "####G###F#########BBB#########F###G####",
                            "####FFFFG########MBNBM########GFFFF####", "########F#########BBB#########F########",
                            "DDDDDDDDB##########M##########BDDDDDDDD", "####G###G##########M##########G###G####",
                            "DDDDDDDDK##########M##########KDDDDDDDD", "########D##########D##########D########",
                            "########K#####################K########", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("BEEEEEEEEEEEEEEEEEEIEEEEEEEEEEEEEEEEEEB", "C###C#############MEM#############C###C",
                            "C###C#############MEM#############C###C", "C###C#############MEM#############C###C",
                            "BEEEB#############MEM#############BEEEB", "####F#############MEM#############F####",
                            "####G#############MEM#############G####", "####G#############MEM#############G####",
                            "####G#############MEM#############G####", "####G###F########MBOBM########F###G####",
                            "####HFFFH########HNNNH########HFFFH####", "########F########MBOBM########F########",
                            "########B#########MOM#########B########", "BBBBBBBBG#########MOM#########GBBBBBBBB",
                            "########D#########MOM#########D########", "########DDDDDDDDDDDODDDDDDDDDDD########",
                            "########K##########O##########K########", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("BEEEBIIIIIIIIIIIIIIEIIIIIIIIIIIIIIBEEEB", "C###C##############M##############C###C",
                            "C###C##############M##############C###C", "C###C##############M##############C###C",
                            "BEEEB##############M##############BEEEB", "####F##############M##############F####",
                            "####G##############M##############G####", "####G##############M##############G####",
                            "####G##############M##############G####", "####G###F#########BBB#########F###G####",
                            "####FFFFG########MBNBM########GFFFF####", "########F#########BBB#########F########",
                            "DDDDDDDDB##########M##########BDDDDDDDD", "####G###G##########M##########G###G####",
                            "DDDDDDDDK##########M##########KDDDDDDDD", "########D##########D##########D########",
                            "########K#####################K########", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("BEEEBIIIIIIIIIIIIIIEIIIIIIIIIIIIIIBEEEB", "C###C#############################C###C",
                            "C###C#############################C###C", "C###C#############################C###C",
                            "BEEEB#############################BEEEB", "####F#############################F####",
                            "####F#############################F####", "####F#############################F####",
                            "####F#############################F####", "####F###FF#########M#########FF###F####",
                            "####F###GG########MHM########GG###F####", "########FF#########M#########FF########",
                            "########BB###################BB########", "####G###GG###################GG###G####",
                            "########KK###################KK########", "########DD#########D#########DD########",
                            "########KK###################KK########", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("BEEEBIIIIIIIIIIIIIIEIIIIIIIIIIIIIIBEEEB", "C####C###########################C####C",
                            "C####C###########################C####C", "C####C###########################C####C",
                            "BEEEB#############################BEEEB", "#####F###########################F#####",
                            "#####J###########################J#####", "#####J###########################J#####",
                            "#####J###########################J#####", "#####J###FF#################FF###J#####",
                            "#####F###GG#################GG###F#####", "#########FF#################FF#########",
                            "#########BB#################BB#########", "#####G###GG#################GG###G#####",
                            "#########KK#################KK#########", "#########DD########D########DD#########",
                            "#########KK#################KK#########", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("#BEEEBIIIIIIIIIIIIIEIIIIIIIIIIIIIBEEEB#", "C####C###########################C####C",
                            "C####C###########################C####C", "C####C###########################C####C",
                            "#BEEEB###########################BEEEB#", "#####F###########################F#####",
                            "#####J###########################J#####", "#####J###########################J#####",
                            "#####J###########################J#####", "#####J####FF###############FF####J#####",
                            "#####F####GG###############GG####F#####", "##########FF###############FF##########",
                            "##########BB###############BB##########", "#####G####GG###############GG####G#####",
                            "##########KK###############KK##########", "##########DD#######D#######DD##########",
                            "##########KK###############KK##########", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("#BEEEBIIIIIIIIIIIIIEIIIIIIIIIIIIIBEEEB#", "#C###C###########################C###C#",
                            "#C###C###########################C###C#", "#C###C###########################C###C#",
                            "#BEEEB###########################BEEEB#", "#####F###########################F#####",
                            "#####J###########################J#####", "#####J###########################J#####",
                            "#####J###########################J#####", "#####J#####FF#############FF#####J#####",
                            "#####F#####GG#############GG#####F#####", "###########FF#############FF###########",
                            "###########BB#############BB###########", "#####G#####GG#############GG#####G#####",
                            "###########KK#############KK###########", "###########DD######D######DD###########",
                            "###########KK#############KK###########", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("#BEEEBIIIIIIIIIIIIIEIIIIIIIIIIIIIBEEEB#", "#C####C#########################C####C#",
                            "#C####C#########################C####C#", "#C####C#########################C####C#",
                            "#BEEEB###########################BEEEB#", "######F#########################F######",
                            "######J#########################J######", "######J#########################J######",
                            "######J#########################J######", "######J#####FF###########FF#####J######",
                            "######F#####GG###########GG#####F######", "############FF###########FF############",
                            "############BB###########BB############", "######G####FGG###########GGF####G######",
                            "############KK###########KK############", "############DD#####D#####DD############",
                            "############KK###########KK############", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("##BEEEBIIIIIIIIIIIIEIIIIIIIIIIIIBEEEB##", "#C####C#########################C####C#",
                            "#C####C#########################C####C#", "#C####C#########################C####C#",
                            "##BEEEB#########################BEEEB##", "######F#########################F######",
                            "######J#########################J######", "######J#########################J######",
                            "######J#########################J######", "######J######FF#########FF######J######",
                            "######F######GG#########GG######F######", "#############FF#########FF#############",
                            "#############BB#########BB#############", "######G####FFGG#########GGFF####G######",
                            "#############KK#########KK#############", "#############DD####D####DD#############",
                            "#############KK#########KK#############", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("##BEEEBIIIIIIIIIIIIEIIIIIIIIIIIIBEEEB##", "##C####C#######################C####C##",
                            "##C####C#######################C####C##", "##C####C#######################C####C##",
                            "##BEEEB#########################BEEEB##", "#######F#######################F#######",
                            "#######J#######################J#######", "#######J#######################J#######",
                            "#######J#######################J#######", "#######J######FF#######FF######J#######",
                            "#######F######GG#######GG######F#######", "##############FF#######FF##############",
                            "##############BB#######BB##############", "#######G##FFFFGG#######GGFFFF##G#######",
                            "##############KK#######KK##############", "##############DD###D###DD##############",
                            "##############KK#######KK##############", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("###BEEEBIIIIIIIIIIIEIIIIIIIIIIIBEEEB###", "##C####C#######################C####C##",
                            "##C####C#######################C####C##", "##C####C#######################C####C##",
                            "###BEEEB#######################BEEEB###", "#######F#######################F#######",
                            "#######J#######################J#######", "#######J#######################J#######",
                            "#######J#######################J#######", "#######J#######FF#####FF#######J#######",
                            "#######F#######GG#####GG#######F#######", "###############FF#####FF###############",
                            "###############BB#####BB###############", "#######G#FFF###GG#####GG###FFF#G#######",
                            "###############KK#####KK###############", "###############DD##D##DD###############",
                            "###############KK#####KK###############", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("###BEEEBIIIIIIIIIIIEIIIIIIIIIIIBEEEB###", "###C####C#####################C####C###",
                            "###C####C#####################C####C###", "###C####C#####################C####C###",
                            "###BEEEB#######################BEEEB###", "########F#####################F########",
                            "########G#####################G########", "########G#####################G########",
                            "########G#####################G########", "########G#######FF###FF#######G########",
                            "########F#######GG###GG#######F########", "########G#######FF###FF#######G########",
                            "########G#######BB###BB#######G########", "########FFF#####GG###GG#####FFF########",
                            "################KK###KK################", "################DD#D#DD################",
                            "################KK###KK################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("####BEEEBIIIIIIIIIIEIIIIIIIIIIBEEEB####", "###C#####C###################C#####C###",
                            "###C#####C###################C#####C###", "###C#####C###################C#####C###",
                            "####BEEEB#####################BEEEB####", "#########F###################F#########",
                            "#########G###################G#########", "#########G###################G#########",
                            "#########G###################G#########", "#########G#######FFFFF#######G#########",
                            "#########F#######GGHGG#######F#########", "#########G#######FFFFF#######G#########",
                            "#########G#######BBBBB#######G#########", "#########F#######GGGGG#######F#########",
                            "#################KKDKK#################", "#################DDDDD#################",
                            "#################KKKKK#################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("#####BEEEBBIIIIIIIIEIIIIIIIIBBEEEB#####", "####C#####CC###############CC#####C####",
                            "####C#####CC###############CC#####C####", "####C#####CC###############CC#####C####",
                            "#####BEEEBB#################BBEEEB#####", "##########FF###############FF##########",
                            "##########JJ###############JJ##########", "##########JJ###############JJ##########",
                            "##########JJ###############JJ##########", "##########JJ###############JJ##########",
                            "##########FF######FFF######FF##########", "#######################################",
                            "##################D#D##################", "##########GG#######B#######GG##########",
                            "##################D#D##################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("######BEEEEBBIIIIIIEIIIIIIBBEEEEB######", "#####C######CC###########CC######C#####",
                            "#####C######CC###########CC######C#####", "#####C######CC###########CC######C#####",
                            "######BEEEEBB#############BBEEEEB######", "############FF###########FF############",
                            "############JJ###########JJ############", "############JJ###########JJ############",
                            "############JJ###########JJ############", "############JJ###########JJ############",
                            "############FF####FFF####FF############", "#######################################",
                            "##################D#D##################", "############GG#####B#####GG############",
                            "##################D#D##################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("#######BEEEEEBBBIIIEIIIBBBEEEEEB#######", "######C#######CCC#####CCC#######C######",
                            "######C#######CCC#####CCC#######C######", "######C#######CCC#####CCC#######C######",
                            "#######BEEEEEBBB#######BBBEEEEEB#######", "##############FFF#####FFF##############",
                            "##############JJJ#####JJJ##############", "##############JJJ#####JJJ##############",
                            "##############JJJ#####JJJ##############", "##############JJJ#####JJJ##############",
                            "##############FFF#FFF#FFF##############", "#######################################",
                            "##################D#D##################", "##############GGG##B##GGG##############",
                            "##################D#D##################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("########BEEEEEEEBBBEBBBEEEEEEEB########", "#######C#########CCCCC#########C#######",
                            "#######C#########CCCCC#########C#######", "#######C#########CCCCC#########C#######",
                            "########BEEEEEEEBBBBBBBEEEEEEEB########", "#################FFFFF#################",
                            "#################FGGGF#################", "#################FGGGF#################",
                            "#################FGGGF#################", "#################FGGGF#################",
                            "#################FFHFF#################", "#######################################",
                            "##################D#D##################", "#################GGBGG#################",
                            "##################D#D##################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("#########BBEEEEEEEEEEEEEEEEEBB#########", "########CC###################CC########",
                            "########CC###################CC########", "########CC###################CC########",
                            "#########BBEEEEEEEEEEEEEEEEEBB#########", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "##################D#D##################", "###################B###################",
                            "##################D#D##################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("###########BBEEEEEEEEEEEEEBB###########", "##########CC###############CC##########",
                            "##########CC###############CC##########", "##########CC###############CC##########",
                            "###########BBEEEEEEEEEEEEEBB###########", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "##################D#D##################", "###################B###################",
                            "##################D#D##################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("#############BBBEEEEEEEBBB#############", "############CCC#########CCC############",
                            "############CCC#########CCC############", "############CCC#########CCC############",
                            "#############BBBEEEEEEEBBB#############", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "##################D#D##################", "###################B###################",
                            "##################D#D##################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################")
                    .aisle("A###############BBBBBBB################", "###############CCCCCCCCC###############",
                            "###############CCCC@CCCC###############", "###############CCCCCCCCC###############",
                            "################BBBBBBB################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "##################D#D##################", "###################B###################",
                            "##################D#D##################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "#######################################", "#######################################",
                            "######################################A")
                    .where("#", Predicates.any())
                    .where("A", Predicates.any())
                    .where("B", Predicates.blocks(HIGH_POWER_CASING.get()))
                    .where("C", Predicates.blocks(CASING_NAQUADAH_ALLOY_BLOCK.get()))
                    .where("D", Predicates.blocks(ADVANCED_COMPUTER_CASING.get())
                            .or(Predicates.abilities(PartAbility.INPUT_ENERGY)))
                    .where("E", Predicates.blocks(WIDESPEEDINGPIPE.get()))
                    .where("F", Predicates.blocks(MACHINE_CASING_ZPM.get()))
                    .where("G", Predicates.blocks(neutron.get()))
                    .where("H", Predicates.blocks(HERMETIC_CASING_ZPM.get()))
                    .where("I", Predicates.blocks(HIGH_POWER_CASING.get()))
                    .where("J", Predicates.blocks(FUSION_GLASS.get()))
                    .where("K", Predicates.blocks(COMPUTER_HEAT_VENT.get()))
                    .where("L", Predicates.any())
                    .where("M", Predicates.frames(Neutronium))
                    .where("N", Predicates.blocks(FUSION_CASING.get()))
                    .where("O", Predicates.blocks(BATTERY_EMPTY_TIER_III.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel((CTNHCore.id("block/casings/nq_alloy_casing")),
                    GTCEu.id("block/multiblock/implosion_compressor"))
            .register();

    public static final MultiblockMachineDefinition ADVANCED_ASSEMBLY_LINE = REGISTRATE
            .multiblock("advance_assembly_line", AssemblyLineMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.ASSEMBLY_LINE_RECIPES)
            .alwaysTryModifyRecipe(true)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH, GTRecipeModifiers.OC_NON_PERFECT_SUBTICK,
                    GTRecipeModifiers.BATCH_MODE)
            .tooltips(Component.translatable("ctnh.advanceassemblyline.1"),
                    Component.translatable("gtceu.multiblock.laser.tooltip"),
                    Component.translatable("gtceu.multiblock.parallelizable.tooltip"))
            .appearanceBlock(ADVANCE_MACHINE_CASING_SOLID_STEEL)
            .pattern(definition -> FactoryBlockPattern.start(BACK, UP, RIGHT)
                    .aisle("FIF", "RTR", "SAG", "#Y#")
                    .aisle("FIF", "RTR", "DAG", "#Y#").setRepeatable(3, 15)
                    .aisle("FOF", "RTR", "DAG", "#Y#")
                    .where("S", Predicates.controller(blocks(definition.getBlock())))
                    .where("F", blocks(ADVANCE_MACHINE_CASING_SOLID_STEEL.get())
                            .or(abilities(PartAbility.IMPORT_FLUIDS).setMaxGlobalLimited(4))
                            .or(abilities(PartAbility.PARALLEL_HATCH).setMaxGlobalLimited(1).setPreviewCount(1)))
                    .where("O",
                            abilities(PartAbility.EXPORT_ITEMS)
                                    .addTooltips(Component.translatable("gtceu.multiblock.pattern.location_end")))
                    .where("Y",
                            blocks(ADVANCE_MACHINE_CASING_SOLID_STEEL.get())
                                    .or(abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(16))
                                    .or(abilities(PartAbility.INPUT_LASER).setMaxGlobalLimited(16)))
                    .where("I", abilities(PartAbility.IMPORT_ITEMS))
                    .where("G", blocks(ADVANCE_MACHINE_CASING_GRATE.get()))
                    .where("A", blocks(ADVANCE_MACHINE_CASING_ASSEMBLY_CONTROL.get()))
                    .where("R", blocks(FUSION_GLASS.get()))
                    .where("T", blocks(ADVANCE_MACHINE_CASING_ASSEMBLY_LINE.get()))
                    .where("D", dataHatchPredicate(blocks(ADVANCE_MACHINE_CASING_GRATE.get())))
                    .where("#", Predicates.any())
                    .build())
            .partSorter(AssemblyLineMachine::partSorter)
            .workableCasingModel(CTNHCore.id("block/casings/advance_machine_casing_solid_steel"),
                    GTCEu.id("block/multiblock/assembly_line"))
            .register();

    public final static MultiblockMachineDefinition CultivationRoom = REGISTRATE
            .multiblock("cultivationroom", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.CULTIVATION_ROOM)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH,
                    GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .tooltips(
                    Component.translatable("ctnh.multiblock.cultivation_room.tooltip.1")
                            .withStyle(ChatFormatting.GREEN),
                    Component.translatable("ctnh.multiblock.cultivation_room.tooltip.2"),
                    CTNHCommonTooltips.PARALLEL_HATCH,
                    Component.translatable("gtceu.multiblock.laser.tooltip"))
            .appearanceBlock(CASING_STAINLESS_CLEAN)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAA", "ABBBA", "ACCCA", "ADDDA", "ADDDA", "ADDDA", "AAAAA", "ABBBA", "AAAAA")
                    .aisle("AAAAA", "BBBBB", "CDDDC", "DEEED", "DEEED", "DEEED", "ADDDA", "BBBBB", "AAAAA")
                    .aisle("AAAAA", "BBBBB", "CDDDC", "DEEED", "DEEED", "DEEED", "ADDDA", "BBBBB", "AAAAA")
                    .aisle("AAAAA", "BBBBB", "CDDDC", "DEEED", "DEEED", "DEEED", "ADDDA", "BBBBB", "AAAAA")
                    .aisle("AAAAA", "ABBBA", "AC@CA", "ADDDA", "ADDDA", "ADDDA", "AAAAA", "ABBBA", "AAAAA")
                    .where("A", blocks(CASING_STAINLESS_CLEAN.get()))
                    .where("B", blocks(FILTER_CASING_STERILE.get()))
                    .where("C", Predicates.autoAbilities(definition.getRecipeTypes())
                            .or(abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2))
                            .or(abilities(PartAbility.PARALLEL_HATCH).setMaxGlobalLimited(1))
                            .or(abilities(PartAbility.INPUT_LASER).setMaxGlobalLimited(1))
                            .or(blocks(CASING_STAINLESS_CLEAN.get())))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("D", Predicates.blocks(CLEANROOM_GLASS.get()))
                    .where("E", Predicates.blocks(WATER))
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"),
                    GTCEu.id("block/multiblock/implosion_compressor"))
            .register();

    public final static MultiblockMachineDefinition PLASMA_ALLOY_BLAST_SMELTER = REGISTRATE
            .multiblock("plasma_alloy_blast_smelter", Plasma_alloy_blast::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GCYMRecipeTypes.ALLOY_BLAST_RECIPES)
            .recipeModifiers(Plasma_alloy_blast::recipeModifier, GTRecipeModifiers::ebfOverclock)
            .tooltips(Component.translatable("ctnh.multiblock.plasma_alloy.tooltip.1"),
                    Component.translatable("ctnh.multiblock.plasma_alloy.tooltip.11"),
                    Component.translatable("ctnh.multiblock.plasma_alloy.tooltip.recipe"),
                    Component.translatable("ctnh.multiblock.plasma_alloy.tooltip.2"),
                    Component.translatable("ctnh.multiblock.plasma_alloy.tooltip.3"),
                    Component.translatable("ctnh.multiblock.plasma_alloy.tooltip.4"),
                    Component.translatable("ctnh.multiblock.plasma_alloy.tooltip.5"),
                    Component.translatable("ctnh.multiblock.plasma_alloy.tooltip.6"),
                    Component.translatable("ctnh.multiblock.plasma_alloy.tooltip.7"),
                    Component.translatable("ctnh.multiblock.plasma_alloy.tooltip.8"),
                    Component.translatable("ctnh.multiblock.plasma_alloy.tooltip.9"),
                    Component.translatable("ctnh.multiblock.plasma_alloy.tooltip.10"))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("A####BBBBBBB####A", "#####BBBBBBB#####", "#################", "#################",
                            "#################", "#################", "#################", "#################",
                            "#################", "#################", "#################", "#################",
                            "#################", "#################", "#################", "#################",
                            "#################")
                    .aisle("###BBBBBBBBBBB###", "###BBBBBBBBBBB###", "#################", "#################",
                            "#################", "#################", "#################", "#################",
                            "#################", "#################", "#################", "#################",
                            "#################", "#################", "#################", "#################",
                            "#################")
                    .aisle("##BBBBBBBBBBBBB##", "##BBBBBBBBBBBBB##", "####CCCCCCCCC####", "####CCCCCCCCC####",
                            "#################", "#################", "#################", "#################",
                            "#################", "####CCCCCCCCC####", "####CCCCCCCCC####", "#################",
                            "#################", "#################", "#################", "#################",
                            "#################")
                    .aisle("#BBBBBBBBBBBBBBB#", "#BBBBBBBBBBBBBBB#", "###CCDDDDDDDCC###", "###CCDDDDDDDCC###",
                            "####C#######C####", "####C#######C####", "####C#######C####", "####C#######C####",
                            "####C#######C####", "###CCDDDDDDDCC###", "###CCDDDDDDDCC###", "#################",
                            "#################", "#################", "#################", "#################",
                            "#####EEEEEEE#####")
                    .aisle("#BBBBBBBBBBBBBBB#", "#BBBBBBBBBBBBBBB#", "##CCDFFFFFFFDCC##", "##CCDGGGGGGGDCC##",
                            "###C#########C###", "###C#########C###", "###C#########C###", "###C#########C###",
                            "###C#########C###", "##CCDGGGGGGGDCC##", "##CCDHHHHHHHDCC##", "#################",
                            "#################", "#################", "#################", "#################",
                            "####EEEEEEEEE####")
                    .aisle("BBBBBBBBBBBBBBBBB", "BBBBBBBBBBBBBBBBB", "##CDFFFFFFFFFDC##", "##CDGGGGGGGGGDC##",
                            "#####I##I##I#####", "#####I##I##I#####", "#####I##I##I#####", "#####I##I##I#####",
                            "#####I##I##I#####", "##CDGIGGIGGIGDC##", "##CDHIHHIHHIHDC##", "#####I##I##I#####",
                            "#####I##I##I#####", "#####I##I##I#####", "#####I##I##I#####", "#####I##I##I#####",
                            "###EEJEEJEEJEE###")
                    .aisle("BBBBBBBBBBBBBBBBB", "BBBBBBBBBBBBBBBBB", "##CDFFKKKKKFFDC##", "##CDGGLLLLLGGDC##",
                            "######MNNNM######", "######MNNNM######", "######MNNNM######", "######MNNNM######",
                            "######MNNNM######", "##CDGGLLLLLGGDC##", "##CDHHOOOOOHHDC##", "######PPPPP######",
                            "######QQQQQ######", "######QQQQQ######", "######QQQQQ######", "######PPPPP######",
                            "###EEEJEJEJEEE###")
                    .aisle("BBBBBBBBBBBBBBBBB", "BBBBBBBBBBBBBBBBB", "##CDFFK###KFFDC##", "##CDGGL###LGGDC##",
                            "######N###N######", "######N###N######", "######N###N######", "######N###N######",
                            "######N###N######", "##CDGGL###LGGDC##", "##CDHHO###OHHDC##", "######P###P######",
                            "######Q###Q######", "######Q###Q######", "######Q###Q######", "######P###P######",
                            "###EEEEJJJEEEE###")
                    .aisle("BBBBBBBBBBBBBBBBB", "BBBBBBBBBBBBBBBBB", "##CDFFK###KFFDC##", "##CDGGL###LGGDC##",
                            "#####IN###NI#####", "#####IN###NI#####", "#####IN###NI#####", "#####IN###NI#####",
                            "#####IN###NI#####", "##CDGIL###LIGDC##", "##CDHIO###OIHDC##", "#####IP###PI#####",
                            "#####IQ###QI#####", "#####IQ###QI#####", "#####IQ###QI#####", "#####IP###PI#####",
                            "###EEJJJEJJJEE###")
                    .aisle("BBBBBBBBBBBBBBBBB", "BBBBBBBBBBBBBBBBB", "##CDFFK###KFFDC##", "##CDGGL###LGGDC##",
                            "######N###N######", "######N###N######", "######N###N######", "######N###N######",
                            "######N###N######", "##CDGGL###LGGDC##", "##CDHHO###OHHDC##", "######P###P######",
                            "######Q###Q######", "######Q###Q######", "######Q###Q######", "######P###P######",
                            "###EEEEJJJEEEE###")
                    .aisle("BBBBBBBBBBBBBBBBB", "BBBBBBBBBBBBBBBBB", "##CDFFKKKKKFFDC##", "##CDGGLLLLLGGDC##",
                            "######MNNNM######", "######MNNNM######", "######MNNNM######", "######MNNNM######",
                            "######MNNNM######", "##CDGGLLLLLGGDC##", "##CDHHOOOOOHHDC##", "######PPPPP######",
                            "######QQQQQ######", "######QQQQQ######", "######QQQQQ######", "######PPPPP######",
                            "###EEEJEJEJEEE###")
                    .aisle("BBBBBBBBBBBBBBBBB", "BBBBBBBBBBBBBBBBB", "##CDFFFFFFFFFDC##", "##CDGGGGGGGGGDC##",
                            "#####I##I##I#####", "#####I##I##I#####", "#####I##I##I#####", "#####I##I##I#####",
                            "#####I##I##I#####", "##CDGIGGIGGIGDC##", "##CDHIHHIHHIHDC##", "#####I##I##I#####",
                            "#####I##I##I#####", "#####I##I##I#####", "#####I##I##I#####", "#####I##I##I#####",
                            "###EEJEEJEEJEE###")
                    .aisle("#BBBBBBBBBBBBBBB#", "#BBBBBBBBBBBBBBB#", "##CCDFFFFFFFDCC##", "##CCDGGGGGGGDCC##",
                            "###C#########C###", "###C#########C###", "###C#########C###", "###C#########C###",
                            "###C#########C###", "##CCDGGGGGGGDCC##", "##CCDHHHHHHHDCC##", "#################",
                            "#################", "#################", "#################", "#################",
                            "####EEEEEEEEE####")
                    .aisle("#BBBBBBBBBBBBBBB#", "#BBBBBBBBBBBBBBB#", "###CCDDDDDDDCC###", "###CCDDDDDDDCC###",
                            "####C#######C####", "####C#######C####", "####C#######C####", "####C#######C####",
                            "####C#######C####", "###CCDDDDDDDCC###", "###CCDDDDDDDCC###", "#################",
                            "#################", "#################", "#################", "#################",
                            "#####EEEEEEE#####")
                    .aisle("##BBBBBBBBBBBBB##", "##BBBBBBBBBBBBB##", "####CCCCCCCCC####", "####CCCCCCCCC####",
                            "#################", "#################", "#################", "#################",
                            "#################", "####CCCCCCCCC####", "####CCCCCCCCC####", "#################",
                            "#################", "#################", "#################", "#################",
                            "#################")
                    .aisle("###BBBBBBBBBBB###", "###BBBBBBBBBBB###", "#################", "#################",
                            "#################", "#################", "#################", "#################",
                            "#################", "#################", "#################", "#################",
                            "#################", "#################", "#################", "#################",
                            "#################")
                    .aisle("A####BBBBBBB####A", "#####BBB@BBB#####", "#################", "#################",
                            "#################", "#################", "#################", "#################",
                            "#################", "#################", "#################", "#################",
                            "#################", "#################", "#################", "#################",
                            "################A")
                    .where("A", Predicates.any())
                    .where("#", Predicates.any())
                    .where("B", Predicates.blocks(CASING_HIGH_TEMPERATURE_SMELTING.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.abilities(PartAbility.PARALLEL_HATCH))
                            .or(Predicates.abilities(PartAbility.INPUT_LASER)))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("C", Predicates.frames(Naquadria))
                    .where("D", Predicates.blocks(CASING_TUNGSTENCU_DIAMOND_PLATING.get()))
                    .where("E", Predicates.blocks(CASING_NAQUADAH_ALLOY_BLOCK.get()))
                    .where("F", Predicates.blocks(HIGH_POWER_CASING.get()))
                    .where("G", Predicates.blocks(PLASMA_COOLED_CORE.get()))
                    .where("H", Predicates.blocks(HEAT_VENT.get()))
                    .where("I", Predicates.blocks(HERMETIC_CASING_ZPM.get()))
                    .where("J", Predicates.blocks(CASING_NEUTRONIUM_ALLOY_BLOCK.get()))
                    .where("K", Predicates.blocks(MATERIAL_BLOCKS.get(TagPrefix.block, NaquadahEnriched).get()))
                    .where("L", Predicates.blocks(FUSION_COIL.get()))
                    .where("M", Predicates.blocks(CASING_ULTIMATE_ENGINE_INTAKE.get()))
                    .where("N", Predicates.heatingCoils())
                    .where("O", Predicates.blocks(CASING_NAQUADAH_GEARBOX.get()))
                    .where("P", Predicates.blocks(CASING_ANTIFREEZE_HEATPROOF_MACHINE.get()))
                    .where("Q", Predicates.blocks(WIDESPEEDINGPIPE.get()))
                    .where("Q", Predicates.blocks(WIDESPEEDINGPIPE.get()))

                    .build())
            .workableCasingModel(GTCEu.id("block/casings/gcym/high_temperature_smelting_casing"),
                    CTNHCore.id("block/overlay/super_ebf"))
            .register();
    public final static MultiblockMachineDefinition UNIVERSE_SINOPE = REGISTRATE.multiblock("universe_sinope",
            holder -> new Arc_Reactor(holder, 10))
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.ARC_REACTOR)
            .recipeModifiers(Plasma_alloy_blast::recipeModifier, GTRecipeModifiers::ebfOverclock)
            .tooltips(Component.translatable("ctnh.u_sinope.story.1"),
                    Component.translatable("ctnh.u_sinope.story.2"),
                    Component.translatable("ctnh.u_sinope.story.3"),
                    Component.translatable("ctnh.u_sinope.1"),
                    Component.translatable("ctnh.u_sinope.2"),
                    Component.translatable("ctnh.u_sinope.3"),
                    Component.translatable("ctnh.u_sinope.4"),
                    Component.translatable("ctnh.u_sinope.5"),
                    Component.translatable("ctnh.u_sinope.6"),
                    Component.translatable("ctnh.u_sinope.7"),
                    Component.translatable("ctnh.u_sinope.8"))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("A                   BB@BB                     ",
                            "CCC               BB     BB               CCC ",
                            "CDC           BBBB         BBBB           CDC ",
                            "CCC           BDB           BDB           CCC ",
                            "              BBB           BBB               ",
                            "             B                 B              ",
                            "          BBB                   BBB           ",
                            "          BDB    EEEEEEEEEEE    BDB           ",
                            "          BBB   EEEEEEEEEEEEE   BBB           ",
                            "         B     EEEEEEEEEEEEEEE     B          ",
                            "        B     EEEEEEEEEEEEEEEEE     B         ",
                            "     BBB     EEEEEEEEEEEEEEEEEEE     BBB      ",
                            "     BDB    EEEEEEEEEEEEEEEEEEEEE    BDB      ",
                            "     BBB   EEEEEEEEEEEEEEEEEEEEEEE   BBB      ",
                            "    B     EEEEEEEEEEEEEEEEEEEEEEEEE     B     ",
                            " BBB     EEEEEEEEEEEEEEEEEEEEEEEEEEE     BBB  ",
                            " BDB    EEEEEEEEEEEEEEEEEEEEEEEEEEEEE    BDB  ",
                            " BBB   EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE   BBB  ",
                            " B    EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE    B  ",
                            "B     EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE     B ",
                            "B     EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE     B ",
                            "      EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE      B",
                            "      EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE      B",
                            "      EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE      F",
                            "      EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE      B",
                            "      EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE      B",
                            "B     EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE     B ",
                            "B     EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE     B ",
                            " B    EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE    B  ",
                            " BBB   EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE   BBB  ",
                            " BDB    EEEEEEEEEEEEEEEEEEEEEEEEEEEEE    BDB  ",
                            " BBB     EEEEEEEEEEEEEEEEEEEEEEEEEEE     BBB  ",
                            "    B     EEEEEEEEEEEEEEEEEEEEEEEEE     B     ",
                            "     BBB   EEEEEEEEEEEEEEEEEEEEEEE   BBB      ",
                            "     BDB    EEEEEEEEEEEEEEEEEEEEE    BDB      ",
                            "     BBB     EEEEEEEEEEEEEEEEEEE     BBB      ",
                            "        B     EEEEEEEEEEEEEEEEE     B         ",
                            "         B     EEEEEEEEEEEEEEE     B          ",
                            "          BBB   EEEEEEEEEEEEE   BBB           ",
                            "          BDB    EEEEEEEEEEE    BDB           ",
                            "          BBB                   BBB           ",
                            "             B                 B              ",
                            "              BBB           BBB               ",
                            "CCC           BDB           BDB           CCC ",
                            "CDC           BBBB         BBBB           CDC ",
                            "CCC               BB     BB               CCC ",
                            "                    BBFBB                     ")
                    .aisle("                    FFFFF                     ",
                            "BBB               FF     FF               BBB ",
                            "BDB           BBBF         FBBB           BDB ",
                            "BBB           BDB           BDB           BBB ",
                            "              BBB           BBB               ",
                            "             F                 F              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB    GGGGGGGGGGG    BBB           ",
                            "         F      GGGGGGGGGGGGG      F          ",
                            "        F      GGGGGGGGGGGGGGG      F         ",
                            "     BBB      GGGGGGGGGGGGGGGGG      BBB      ",
                            "     BDB     GGGGGGGGGGGGGGGGGGG     BDB      ",
                            "     BBB    GGGGGGGGGGGGGGGGGGGGG    BBB      ",
                            "    F      GGGGGGGGGGGGGGGGGGGGGGG      F     ",
                            " BBB      GGGGGGGGGGGGGGGGGGGGGGGGG      BBB  ",
                            " BDB     GGGGGGGGGGGGGGGGGGGGGGGGGGG     BDB  ",
                            " BBB    GGGGGGGGGGGGGGGGGGGGGGGGGGGGG    BBB  ",
                            " F     GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG     F  ",
                            "F      GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG      F ",
                            "F      GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG      F ",
                            "       GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG       F",
                            "       GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG       F",
                            "       GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG       F",
                            "       GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG       F",
                            "       GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG       F",
                            "F      GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG      F ",
                            "F      GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG      F ",
                            " F     GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG     F  ",
                            " BBB    GGGGGGGGGGGGGGGGGGGGGGGGGGGGG    BBB  ",
                            " BDB     GGGGGGGGGGGGGGGGGGGGGGGGGGG     BDB  ",
                            " BBB      GGGGGGGGGGGGGGGGGGGGGGGGG      BBB  ",
                            "    F      GGGGGGGGGGGGGGGGGGGGGGG      F     ",
                            "     BBB    GGGGGGGGGGGGGGGGGGGGG    BBB      ",
                            "     BDB     GGGGGGGGGGGGGGGGGGG     BDB      ",
                            "     BBB      GGGGGGGGGGGGGGGGG      BBB      ",
                            "        F      GGGGGGGGGGGGGGG      F         ",
                            "         F      GGGGGGGGGGGGG      F          ",
                            "          BBB    GGGGGGGGGGG    BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "             F                 F              ",
                            "              BBB           BBB               ",
                            "BBB           BDB           BDB           BBB ",
                            "BDB           BBBF         FBBB           BDB ",
                            "BBB               FF     FF               BBB ",
                            "                    FFFFF                     ")
                    .aisle("                    BBFBB                     ",
                            "BBB               BB     BB               BBB ",
                            "BDB           BBBB         BBBB           BDB ",
                            "BBB           BDB           BDB           BBB ",
                            "              BBB           BBB               ",
                            "             B                 B              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "         B       HHHHHHHHHHH       B          ",
                            "        B       HHHHHHHHHHHHH       B         ",
                            "     BBB       HHHHHHHHHHHHHHH       BBB      ",
                            "     BDB      HHHHHHHHHHHHHHHHH      BDB      ",
                            "     BBB     HHHHHHHHHHHHHHHHHHH     BBB      ",
                            "    B       HHHHHHHHHHHHHHHHHHHHH       B     ",
                            " BBB       HHHHHHHHHHHHHHHHHHHHHHH       BBB  ",
                            " BDB      HHHHHHHHHHHHHHHHHHHHHHHHH      BDB  ",
                            " BBB     HHHHHHHHHHHHHHHHHHHHHHHHHHH     BBB  ",
                            " B      HHHHHHHHHHHHHHHHHHHHHHHHHHHHH      B  ",
                            "B       HHHHHHHHHHHHHHHHHHHHHHHHHHHHH       B ",
                            "B       HHHHHHHHHHHHHHHHHHHHHHHHHHHHH       B ",
                            "        HHHHHHHHHHHHHHHHHHHHHHHHHHHHH        B",
                            "        HHHHHHHHHHHHHHHHHHHHHHHHHHHHH        B",
                            "        HHHHHHHHHHHHHHHHHHHHHHHHHHHHH        F",
                            "        HHHHHHHHHHHHHHHHHHHHHHHHHHHHH        B",
                            "        HHHHHHHHHHHHHHHHHHHHHHHHHHHHH        B",
                            "B       HHHHHHHHHHHHHHHHHHHHHHHHHHHHH       B ",
                            "B       HHHHHHHHHHHHHHHHHHHHHHHHHHHHH       B ",
                            " B      HHHHHHHHHHHHHHHHHHHHHHHHHHHHH      B  ",
                            " BBB     HHHHHHHHHHHHHHHHHHHHHHHHHHH     BBB  ",
                            " BDB      HHHHHHHHHHHHHHHHHHHHHHHHH      BDB  ",
                            " BBB       HHHHHHHHHHHHHHHHHHHHHHH       BBB  ",
                            "    B       HHHHHHHHHHHHHHHHHHHHH       B     ",
                            "     BBB     HHHHHHHHHHHHHHHHHHH     BBB      ",
                            "     BDB      HHHHHHHHHHHHHHHHH      BDB      ",
                            "     BBB       HHHHHHHHHHHHHHH       BBB      ",
                            "        B       HHHHHHHHHHHHH       B         ",
                            "         B       HHHHHHHHHHH       B          ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "             B                 B              ",
                            "              BBB           BBB               ",
                            "BBB           BDB           BDB           BBB ",
                            "BDB           BBBB         BBBB           BDB ",
                            "BBB               BB     BB               BBB ",
                            "                    BBFBB                     ")
                    .aisle("                                              ",
                            "BBB                                       BBB ",
                            "BDB           BBB           BBB           BDB ",
                            "BBB           BDB           BDB           BBB ",
                            "              BBB           BBB               ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "                 IIIIIIIIIII                  ",
                            "     BBB        IIIIIIIIIIIII        BBB      ",
                            "     BDB       IIIIIIIIIIIIIII       BDB      ",
                            "     BBB      IIIIIIIIIIIIIIIII      BBB      ",
                            "             IIIIIIIIIIIIIIIIIII              ",
                            " BBB        IIIIIIIIIIIIIIIIIIIII        BBB  ",
                            " BDB       IIIIIIIIIIIIIIIIIIIIIII       BDB  ",
                            " BBB      IIIIIIIIIIIIIIIIIIIIIIIII      BBB  ",
                            "         IIIIIIIIIIIIIIIIIIIIIIIIIII          ",
                            "         IIIIIIIIIIIIIIIIIIIIIIIIIII          ",
                            "         IIIIIIIIIIIIIIIIIIIIIIIIIII          ",
                            "         IIIIIIIIIIIIIIIIIIIIIIIIIII          ",
                            "         IIIIIIIIIIIIIIIIIIIIIIIIIII          ",
                            "         IIIIIIIIIIIIIIIIIIIIIIIIIII          ",
                            "         IIIIIIIIIIIIIIIIIIIIIIIIIII          ",
                            "         IIIIIIIIIIIIIIIIIIIIIIIIIII          ",
                            "         IIIIIIIIIIIIIIIIIIIIIIIIIII          ",
                            "         IIIIIIIIIIIIIIIIIIIIIIIIIII          ",
                            "         IIIIIIIIIIIIIIIIIIIIIIIIIII          ",
                            " BBB      IIIIIIIIIIIIIIIIIIIIIIIII      BBB  ",
                            " BDB       IIIIIIIIIIIIIIIIIIIIIII       BDB  ",
                            " BBB        IIIIIIIIIIIIIIIIIIIII        BBB  ",
                            "             IIIIIIIIIIIIIIIIIII              ",
                            "     BBB      IIIIIIIIIIIIIIIII      BBB      ",
                            "     BDB       IIIIIIIIIIIIIII       BDB      ",
                            "     BBB        IIIIIIIIIIIII        BBB      ",
                            "                 IIIIIIIIIII                  ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "              BBB           BBB               ",
                            "BBB           BDB           BDB           BBB ",
                            "BDB           BBB           BBB           BDB ",
                            "BBB                                       BBB ",
                            "                                              ")
                    .aisle("                                              ",
                            "BBB                                       BBB ",
                            "BDB           BBB           BBB           BDB ",
                            "BBB           BDB           BDB           BBB ",
                            "              BBB           BBB               ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "                                              ",
                            "     BBB         AAAAAAAAAAA         BBB      ",
                            "     BDB        AAAAAAAAAAAAA        BDB      ",
                            "     BBB       AAAAAAAAAAAAAAA       BBB      ",
                            "              AAAAAAAAAAAAAAAAA               ",
                            " BBB         AAAAAAAAAAAAAAAAAAA         BBB  ",
                            " BDB        AAAAAAAAAAAAAAAAAAAAA        BDB  ",
                            " BBB       AAAAAAAAAAAAAAAAAAAAAAA       BBB  ",
                            "          AAAAAAAAAAAAAAAAAAAAAAAAA           ",
                            "          AAAAAAAAAAAAAAAAAAAAAAAAA           ",
                            "          AAAAAAAAAAAAAAAAAAAAAAAAA           ",
                            "          AAAAAAAAAAAAAAAAAAAAAAAAA           ",
                            "          AAAAAAAAAAAAAAAAAAAAAAAAA           ",
                            "          AAAAAAAAAAAAAAAAAAAAAAAAA           ",
                            "          AAAAAAAAAAAAAAAAAAAAAAAAA           ",
                            "          AAAAAAAAAAAAAAAAAAAAAAAAA           ",
                            "          AAAAAAAAAAAAAAAAAAAAAAAAA           ",
                            "          AAAAAAAAAAAAAAAAAAAAAAAAA           ",
                            "          AAAAAAAAAAAAAAAAAAAAAAAAA           ",
                            " BBB       AAAAAAAAAAAAAAAAAAAAAAA       BBB  ",
                            " BDB        AAAAAAAAAAAAAAAAAAAAA        BDB  ",
                            " BBB         AAAAAAAAAAAAAAAAAAA         BBB  ",
                            "              AAAAAAAAAAAAAAAAA               ",
                            "     BBB       AAAAAAAAAAAAAAA       BBB      ",
                            "     BDB        AAAAAAAAAAAAA        BDB      ",
                            "     BBB         AAAAAAAAAAA         BBB      ",
                            "                                              ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "              BBB           BBB               ",
                            "BBB           BDB           BDB           BBB ",
                            "BDB           BBB           BBB           BDB ",
                            "BBB                                       BBB ",
                            "                                              ")
                    .aisle("                                              ",
                            "BBB                                       BBB ",
                            "BDB           BBB           BBB           BDB ",
                            "BBB           BDB           BDB           BBB ",
                            "              BBB           BBB               ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "                                              ",
                            "     BBB                             BBB      ",
                            "     BDB         JJJJJJJJJJJ         BDB      ",
                            "     BBB        JJJJJJJJJJJJJ        BBB      ",
                            "               JJJJJJJJJJJJJJJ                ",
                            " BBB          JJJJJJJJJJJJJJJJJ          BBB  ",
                            " BDB         JJJJJJJJJJJJJJJJJJJ         BDB  ",
                            " BBB        JJJJJJJJJJJJJJJJJJJJJ        BBB  ",
                            "           JJJJJJJJJJJJJJJJJJJJJJJ            ",
                            "           JJJJJJJJJJJJJJJJJJJJJJJ            ",
                            "           JJJJJJJJJJJJJJJJJJJJJJJ            ",
                            "           JJJJJJJJJJJJJJJJJJJJJJJ            ",
                            "           JJJJJJJJJJJJJJJJJJJJJJJ            ",
                            "           JJJJJJJJJJJJJJJJJJJJJJJ            ",
                            "           JJJJJJJJJJJJJJJJJJJJJJJ            ",
                            "           JJJJJJJJJJJJJJJJJJJJJJJ            ",
                            "           JJJJJJJJJJJJJJJJJJJJJJJ            ",
                            "           JJJJJJJJJJJJJJJJJJJJJJJ            ",
                            "           JJJJJJJJJJJJJJJJJJJJJJJ            ",
                            " BBB        JJJJJJJJJJJJJJJJJJJJJ        BBB  ",
                            " BDB         JJJJJJJJJJJJJJJJJJJ         BDB  ",
                            " BBB          JJJJJJJJJJJJJJJJJ          BBB  ",
                            "               JJJJJJJJJJJJJJJ                ",
                            "     BBB        JJJJJJJJJJJJJ        BBB      ",
                            "     BDB         JJJJJJJJJJJ         BDB      ",
                            "     BBB                             BBB      ",
                            "                                              ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "              BBB           BBB               ",
                            "BBB           BDB           BDB           BBB ",
                            "BDB           BBB           BBB           BDB ",
                            "BBB                                       BBB ",
                            "                                              ")
                    .aisle("                                              ",
                            "BBB                                       BBB ",
                            "BDB           BBB           BBB           BDB ",
                            "BBB           BDB           BDB           BBB ",
                            "              BBB           BBB               ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "                                              ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB         KKKKKKKKKKK         BBB      ",
                            "                KKKKKKKKKKKKK                 ",
                            " BBB           KKKKKKKKKKKKKKK           BBB  ",
                            " BDB          KKKKKKKKKKKKKKKKK          BDB  ",
                            " BBB         KKKKKKKKKKKKKKKKKKK         BBB  ",
                            "            KKKKKKKKKKKKKKKKKKKKK             ",
                            "            KKKKKKKKKKKKKKKKKKKKK             ",
                            "            KKKKKKKKKKKKKKKKKKKKK             ",
                            "            KKKKKKKKKKKKKKKKKKKKK             ",
                            "            KKKKKKKKKKKKKKKKKKKKK             ",
                            "            KKKKKKKKKKKKKKKKKKKKK             ",
                            "            KKKKKKKKKKKKKKKKKKKKK             ",
                            "            KKKKKKKKKKKKKKKKKKKKK             ",
                            "            KKKKKKKKKKKKKKKKKKKKK             ",
                            "            KKKKKKKKKKKKKKKKKKKKK             ",
                            "            KKKKKKKKKKKKKKKKKKKKK          B  ",
                            " BBB         KKKKKKKKKKKKKKKKKKK         BBB  ",
                            " BDB          KKKKKKKKKKKKKKKKK          BDB  ",
                            " BBB           KKKKKKKKKKKKKKK           BBB  ",
                            "                KKKKKKKKKKKKK                 ",
                            "     BBB         KKKKKKKKKKK         BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "                                              ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "              BBB           BBB               ",
                            "BBB           BDB           BDB           BBB ",
                            "BDB           BBB           BBB           BDB ",
                            "BBB                                       BBB ",
                            "                                              ")
                    .aisle("                                              ",
                            "BBB                                       BBB ",
                            "BDB           BBB           BBB           BDB ",
                            "BBB           BDB           BDB           BBB ",
                            "              BBB           BBB               ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "                                              ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "                 LLLLLLLLLLL                  ",
                            " BBB            LLLLLLLLLLLLL            BBB  ",
                            " BDB           LLLLLLLLLLLLLLL           BDB  ",
                            " BBB          LLLLLLLLLLLLLLLLL          BBB  ",
                            "             LLLLLLLLLLLLLLLLLLL              ",
                            "             LLLLLLLLLLLLLLLLLLL              ",
                            "             LLLLLLLLLLLLLLLLLLL              ",
                            "             LLLLLLLLLLLLLLLLLLL              ",
                            "             LLLLLLLLLLLLLLLLLLL              ",
                            "             LLLLLLLLLLLLLLLLLLL              ",
                            "             LLLLLLLLLLLLLLLLLLL              ",
                            "             LLLLLLLLLLLLLLLLLLL              ",
                            "             LLLLLLLLLLLLLLLLLLL              ",
                            "             LLLLLLLLLLLLLLLLLLL              ",
                            "             LLLLLLLLLLLLLLLLLLL              ",
                            " BBB          LLLLLLLLLLLLLLLLL          BBB  ",
                            " BDB           LLLLLLLLLLLLLLL           BDB  ",
                            " BBB            LLLLLLLLLLLLL            BBB  ",
                            "                 LLLLLLLLLLL                  ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "                                              ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "              BBB           BBB               ",
                            "BBB           BDB           BDB           BBB ",
                            "BDB           BBB           BBB           BDB ",
                            "BBB                                       BBB ",
                            "                                              ")
                    .aisle("                                              ",
                            "BBB                                       BBB ",
                            "BDB           BBB           BBB           BDB ",
                            "BBB           BDB     B     BDB           BBB ",
                            "              BBB           BBB               ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "                                              ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "                 LMMMMMMMMML                  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            "             LNNNNNNNNNNNNNNNNNL              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "  B          MNNNNNNNNNNNNNNNNNM          B   ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             LNNNNNNNNNNNNNNNNNL              ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            "                 LMMMMMMMMML                  ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "                                              ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "              BBB           BBB               ",
                            "BBB           BDB     B     BDB           BBB ",
                            "BDB           BBB           BBB           BDB ",
                            "BBB                                       BBB ",
                            "                                              ")
                    .aisle("                                              ",
                            "BBB                                       BBB ",
                            "BDB           BBB     B     BBB           BDB ",
                            "BBB           BDB    BOB    BDB           BBB ",
                            "              BBB     B     BBB               ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "                                              ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "                 LMMMMMMMMML                  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            "             LNNNNNNNNNNNNNNNNNL              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "  B          MNNNNNNNNNNNNNNNNNM          B   ",
                            " BOB         MNNNNNNNNNNNNNNNNNM         BOB  ",
                            "  B          MNNNNNNNNNNNNNNNNNM          B   ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             LNNNNNNNNNNNNNNNNNL              ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            "                 LMMMMMMMMML                  ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "                                              ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "              BBB     B     BBB               ",
                            "BBB           BDB    BOB    BDB           BBB ",
                            "BDB           BBB     B     BBB           BDB ",
                            "BBB                                       BBB ",
                            "                                              ")
                    .aisle("                                              ",
                            "BBB                   B                   BBB ",
                            "BDB           BBB    BOB    BBB           BDB ",
                            "BBB           BDB   BOAOB   BDB           BBB ",
                            "              BBB    BOB    BBB               ",
                            "                     CBC                      ",
                            "          BBB        CBC        BBB           ",
                            "          BDB        CBC        BDB           ",
                            "          BBB        CBC        BBB           ",
                            "                     CBC                      ",
                            "                     CBC                      ",
                            "     BBB             CBC             BBB      ",
                            "     BDB             CBC             BDB      ",
                            "     BBB             CBC             BBB      ",
                            "                 LMMMLLLMMML                  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            "             LNNNNNNNNNNNNNNNNNL              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "  B          MNNNNNNNNNNNNNNNNNM          B   ",
                            " BOBCCCCCCCCCLNNNNNNNNNNNNNNNNNLCCCCCCCCCBOB  ",
                            "BOAOBBBBBBBBBLNNNNNNNNNNNNNNNNNLBBBBBBBBBOAOB ",
                            " BOBCCCCCCCCCLNNNNNNNNNNNNNNNNNLCCCCCCCCCBOB  ",
                            "  B          MNNNNNNNNNNNNNNNNNM          B   ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             LNNNNNNNNNNNNNNNNNL           B  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            "                 LMMMLLLMMML                  ",
                            "     BBB             CBC             BBB      ",
                            "     BDB             CBC             BDB      ",
                            "     BBB             CBC             BBB      ",
                            "                     CBC                      ",
                            "                     CBC                      ",
                            "          BBB        CBC        BBB           ",
                            "          BDB        CBC        BDB           ",
                            "          BBB        CBC        BBB           ",
                            "                     CBC                      ",
                            "              BBB    BOB    BBB               ",
                            "BBB           BDB   BOAOB   BDB           BBB ",
                            "BDB           BBB    BOB    BBB           BDB ",
                            "BBB                   B                   BBB ",
                            "                                              ")
                    .aisle("                      B                       ",
                            "BBB                  BOB                  BBB ",
                            "BDB           BBB   BOAOB   BBB           BDB ",
                            "BBB           BDB  BOAAAOB  BDB           BBB ",
                            "              BBB   BOAOB   BBB               ",
                            "                     BAB                      ",
                            "          BBB        BAB        BBB           ",
                            "          BDB        BAB        BDB           ",
                            "          BBB        BAB        BBB           ",
                            "                     BAB                      ",
                            "                     BAB                      ",
                            "     BBB             BAB             BBB      ",
                            "     BDB             BAB             BDB      ",
                            "     BBB             BAB             BBB      ",
                            "                 LMMMLLLMMML                  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            "             LNNNNNNNNNNNNNNNNNL              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "  B          MNNNNNNNNNNNNNNNNNM          B   ",
                            " BOB         MNNNNNNNNNNNNNNNNNM         BOB  ",
                            "BOAOBBBBBBBBBLNNNNNNNNNNNNNNNNNLBBBBBBBBBOAOB ",
                            "OAAAAAAAAAAAALNNNNNNNNNNNNNNNNNLAAAAAAAAAAAAOB",
                            "BOAOBBBBBBBBBLNNNNNNNNNNNNNNNNNLBBBBBBBBBOAOB ",
                            " BOB         MNNNNNNNNNNNNNNNNNM         BOB  ",
                            "  B          MNNNNNNNNNNNNNNNNNM          B   ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             LNNNNNNNNNNNNNNNNNL              ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            "                 LMMMLLLMMML                  ",
                            "     BBB             BAB             BBB      ",
                            "     BDB             BAB             BDB      ",
                            "     BBB             BAB             BBB      ",
                            "                     BAB                      ",
                            "                     BAB                      ",
                            "          BBB        BAB        BBB           ",
                            "          BDB        BAB        BDB           ",
                            "          BBB        BAB        BBB           ",
                            "                     BAB                      ",
                            "              BBB   BOAOB   BBB               ",
                            "BBB           BDB  BOAAAOB  BDB           BBB ",
                            "BDB           BBB   BOAOB   BBB           BDB ",
                            "BBB                  BOB                  BBB ",
                            "                      B                       ")
                    .aisle("                                              ",
                            "BBB                   B                   BBB ",
                            "BDB           BBB    BOB    BBB           BDB ",
                            "BBB           BDB   BOAOB   BDB           BBB ",
                            "              BBB    BOB    BBB               ",
                            "                     CBC                      ",
                            "          BBB        CBC        BBB           ",
                            "          BDB        CBC        BDB           ",
                            "          BBB        CBC        BBB           ",
                            "                     CBC                      ",
                            "                     CBC                      ",
                            "     BBB             CBC             BBB      ",
                            "     BDB             CBC             BDB      ",
                            "     BBB             CBC             BBB      ",
                            "                 LMMMLLLMMML                  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            "             LNNNNNNNNNNNNNNNNNL              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "  B          MNNNNNNNNNNNNNNNNNM          B   ",
                            " BOBCCCCCCCCCLNNNNNNNNNNNNNNNNNLCCCCCCCCCBOB  ",
                            "BOAOBBBBBBBBBLNNNNNNNNNNNNNNNNNLBBBBBBBBBOAOB ",
                            " BOBCCCCCCCCCLNNNNNNNNNNNNNNNNNLCCCCCCCCCBOB  ",
                            "  B          MNNNNNNNNNNNNNNNNNM          B   ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             LNNNNNNNNNNNNNNNNNL              ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            "                 LMMMLLLMMML                  ",
                            "     BBB             CBC             BBB      ",
                            "     BDB             CBC             BDB      ",
                            "     BBB             CBC             BBB      ",
                            "                     CBC                      ",
                            "                     CBC                      ",
                            "          BBB        CBC        BBB           ",
                            "          BDB        CBC        BDB           ",
                            "          BBB        CBC        BBB           ",
                            "                     CBC                      ",
                            "              BBB    BOB    BBB               ",
                            "BBB           BDB   BOAOB   BDB           BBB ",
                            "BDB           BBB    BOB    BBB           BDB ",
                            "BBB                   B                   BBB ",
                            "                                              ")
                    .aisle("                                              ",
                            "BBB                                       BBB ",
                            "BDB           BBB     B     BBB           BDB ",
                            "BBB           BDB    BOB    BDB           BBB ",
                            "              BBB     B     BBB               ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "                                              ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "                 LMMMMMMMMML                  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            "             LNNNNNNNNNNNNNNNNNL              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "  B          MNNNNNNNNNNNNNNNNNM          B   ",
                            " BOB         MNNNNNNNNNNNNNNNNNM         BOB  ",
                            "  B          MNNNNNNNNNNNNNNNNNM          B   ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             LNNNNNNNNNNNNNNNNNL              ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            "                 LMMMMMMMMML                  ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "                                              ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "              BBB     B     BBB               ",
                            "BBB           BDB    BOB    BDB           BBB ",
                            "BDB           BBB     B     BBB           BDB ",
                            "BBB                                       BBB ",
                            "                                              ")
                    .aisle("                    LLLLL                     ",
                            "BBB               LL     LL               BBB ",
                            "BDB           BBBL         LBBB           BDB ",
                            "BBB           BDB     B     BDB           BBB ",
                            "              BBB           BBB               ",
                            "             L                 L              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "         L                         L          ",
                            "        L                           L         ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "    L            LMMMMMMMMML            L     ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " L           LNNNNNNNNNNNNNNNNNL           L  ",
                            "L            MNNNNNNNNNNNNNNNNNM            L ",
                            "L            MNNNNNNNNNNNNNNNNNM            L ",
                            "             MNNNNNNNNNNNNNNNNNM             L",
                            "             MNNNNNNNNNNNNNNNNNM             L",
                            "  B          MNNNNNNNNNNNNNNNNNM          B  L",
                            "             MNNNNNNNNNNNNNNNNNM             L",
                            "             MNNNNNNNNNNNNNNNNNM             L",
                            "L            MNNNNNNNNNNNNNNNNNM            L ",
                            "L            MNNNNNNNNNNNNNNNNNM            L ",
                            " L           LNNNNNNNNNNNNNNNNNL           L  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            "    L            LMMMMMMMMML            L     ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "        L                           L         ",
                            "         L                         L          ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "             L                 L              ",
                            "              BBB           BBB               ",
                            "BBB           BDB     B     BDB           BBB ",
                            "BDB           BBBL         LBBB           BDB ",
                            "BBB               LL     LL               BBB ",
                            "                    LLLLL                     ")
                    .aisle("                    NNNNN                     ",
                            "BBB               NN     NN               BBB ",
                            "BDB           BBBN         NBBB           BDB ",
                            "BBB           BDB           BDB           BBB ",
                            "              BBB           BBB               ",
                            "             N                 N              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "         N                         N          ",
                            "        N                           N         ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "    N            LMMMMMMMMML            N     ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " N           LNNNNNNNNNNNNNNNNNL           N  ",
                            "N            MNNNNNNNNNNNNNNNNNM            N ",
                            "N            MNNNNNNNNNNNNNNNNNM            N ",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "N            MNNNNNNNNNNNNNNNNNM            N ",
                            "N            MNNNNNNNNNNNNNNNNNM            N ",
                            " N           LNNNNNNNNNNNNNNNNNL           N  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            "    N            LMMMMMMMMML            N     ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "        N                           N         ",
                            "         N                         N          ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "             N                 N              ",
                            "              BBB           BBB               ",
                            "BBB           BDB           BDB           BBB ",
                            "BDB           BBBN         NBBB           BDB ",
                            "BBB               NN     NN               BBB ",
                            "                    NNNNN                     ")
                    .aisle("                    NNNNN                     ",
                            "BBB               NN     NN               BBB ",
                            "BDB           BBBN         NBBB           BDB ",
                            "BBB           BDB           BDB           BBB ",
                            "              BBB           BBB               ",
                            "             N                 N              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "         N                         N          ",
                            "        N                           N         ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "    N            LMMMMMMMMML            N     ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " N           LNNNNNNNNNNNNNNNNNL           N  ",
                            "N            MNNNNNNNNNNNNNNNNNM            N ",
                            "N            MNNNNNNNNNNNNNNNNNM            N ",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "N            MNNNNNNNNNNNNNNNNNM            N ",
                            "N            MNNNNNNNNNNNNNNNNNM            N ",
                            " N           LNNNNNNNNNNNNNNNNNL           N  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            "    N            LMMMMMMMMML            N     ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "        N                           N         ",
                            "         N                         N          ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "             N                 N              ",
                            "              BBB           BBB               ",
                            "BBB           BDB           BDB           BBB ",
                            "BDB           BBBN         NBBB           BDB ",
                            "BBB               NN     NN               BBB ",
                            "                    NNNNN                     ")
                    .aisle("                    NNNNN                     ",
                            "BBB               NN     NN               BBB ",
                            "BDB           BBBN         NBBB           BDB ",
                            "BBB           BDB           BDB           BBB ",
                            "              BBB           BBB               ",
                            "             N                 N              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "         N                         N          ",
                            "        N                           N         ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "    N            LMMMMMMMMML            N     ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " N           LNNNNNNNNNNNNNNNNNL           N  ",
                            "N            MNNNNNNNNNNNNNNNNNM            N ",
                            "N            MNNNNNNNNNNNNNNNNNM            N ",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "N            MNNNNNNNNNNNNNNNNNM            N ",
                            "N            MNNNNNNNNNNNNNNNNNM            N ",
                            " N           LNNNNNNNNNNNNNNNNNL           N  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            "    N            LMMMMMMMMML            N     ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "        N                           N         ",
                            "         N                         N          ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "             N                 N              ",
                            "              BBB           BBB               ",
                            "BBB           BDB           BDB           BBB ",
                            "BDB           BBBN         NBBB           BDB ",
                            "BBB               NN     NN               BBB ",
                            "                    NNNNN                     ")
                    .aisle("                    LLLLL                     ",
                            "BBB               LL     LL               BBB ",
                            "BDB           BBBL         LBBB           BDB ",
                            "BBB           BDB           BDB           BBB ",
                            "              BBB           BBB               ",
                            "             L                 L              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "         L                         L          ",
                            "        L                           L         ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "    L            LMMMMMMMMML            L     ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " L           LNNNNNNNNNNNNNNNNNL           L  ",
                            "L            MNNNNNNNNNNNNNNNNNM            L ",
                            "L            MNNNNNNNNNNNNNNNNNM            L ",
                            "             MNNNNNNNNNNNNNNNNNM             L",
                            "             MNNNNNNNNNNNNNNNNNM             L",
                            "             MNNNNNNNNNNNNNNNNNM             L",
                            "             MNNNNNNNNNNNNNNNNNM             L",
                            "             MNNNNNNNNNNNNNNNNNM             L",
                            "L            MNNNNNNNNNNNNNNNNNM            L ",
                            "L            MNNNNNNNNNNNNNNNNNM            L ",
                            " L           LNNNNNNNNNNNNNNNNNL           L  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            "    L            LMMMMMMMMML            L     ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "        L                           L         ",
                            "         L                         L          ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "             L                 L              ",
                            "              BBB           BBB               ",
                            "BBB           BDB           BDB           BBB ",
                            "BDB           BBBL         LBBB           BDB ",
                            "BBB               LL     LL               BBB ",
                            "                    LLLLL                     ")
                    .aisle("                    PPPPP                     ",
                            "BBB               PP     PP               BBB ",
                            "BDB           BBBP         PBBB           BDB ",
                            "BBB           BDB           BDB           BBB ",
                            "              BBB           BBB               ",
                            "             P                 P              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "         P                         P          ",
                            "        P                           P         ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "    P            LMMMMMMMMML            P     ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " P           LNNNNNNNNNNNNNNNNNL           P  ",
                            "P            MNNNNNNNNNNNNNNNNNM            P ",
                            "P            MNNNNNNNNNNNNNNNNNM            P ",
                            "             MNNNNNNNNNNNNNNNNNM             P",
                            "             MNNNNNNNNNNNNNNNNNM             P",
                            "             MNNNNNNNNNNNNNNNNNM             P",
                            "             MNNNNNNNNNNNNNNNNNM             P",
                            "             MNNNNNNNNNNNNNNNNNM             P",
                            "P            MNNNNNNNNNNNNNNNNNM            P ",
                            "P            MNNNNNNNNNNNNNNNNNM            P ",
                            " P           LNNNNNNNNNNNNNNNNNL           P  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            "    P            LMMMMMMMMML            P     ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "        P                           P         ",
                            "         P                         P          ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "             P                 P              ",
                            "              BBB           BBB               ",
                            "BBB           BDB           BDB           BBB ",
                            "BDB           BBBP         PBBB           BDB ",
                            "BBB               PP     PP               BBB ",
                            "                    PPPPP                     ")
                    .aisle("                    PPPPP                     ",
                            "BBB               PP     PP               BBB ",
                            "BDB           BBBP         PBBB           BDB ",
                            "BBB           BDB           BDB           BBB ",
                            "              BBB           BBB               ",
                            "             P                 P              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "         P                         P          ",
                            "        P                           P         ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "    P            LMMMMMMMMML            P     ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " P           LNNNNNNNNNNNNNNNNNL           P  ",
                            "P            MNNNNNNNNNNNNNNNNNM            P ",
                            "P            MNNNNNNNNNNNNNNNNNM            P ",
                            "             MNNNNNNNNNNNNNNNNNM             P",
                            "             MNNNNNNNNNNNNNNNNNM             P",
                            "             MNNNNNNNNNNNNNNNNNM             P",
                            "             MNNNNNNNNNNNNNNNNNM             P",
                            "             MNNNNNNNNNNNNNNNNNM             P",
                            "P            MNNNNNNNNNNNNNNNNNM            P ",
                            "P            MNNNNNNNNNNNNNNNNNM            P ",
                            " P           LNNNNNNNNNNNNNNNNNL           P  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            "    P            LMMMMMMMMML            P     ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "        P                           P         ",
                            "         P                         P          ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "             P                 P              ",
                            "              BBB           BBB               ",
                            "BBB           BDB           BDB           BBB ",
                            "BDB           BBBP         PBBB           BDB ",
                            "BBB               PP     PP               BBB ",
                            "                    PPPPP                     ")
                    .aisle("                    LLLLL                     ",
                            "BBB               LL     LL               BBB ",
                            "BDB           BBBL         LBBB           BDB ",
                            "BBB           BDB           BDB           BBB ",
                            "              BBB           BBB               ",
                            "             L                 L              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "         L                         L          ",
                            "        L                           L         ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "    L            LMMMMMMMMML            L     ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " L           LNNNNNNNNNNNNNNNNNL           L  ",
                            "L            MNNNNNNNNNNNNNNNNNM            L ",
                            "L            MNNNNNNNNNNNNNNNNNM            L ",
                            "             MNNNNNNNNNNNNNNNNNM             L",
                            "             MNNNNNNNNNNNNNNNNNM             L",
                            "             MNNNNNNNNNNNNNNNNNM             L",
                            "             MNNNNNNNNNNNNNNNNNM             L",
                            "             MNNNNNNNNNNNNNNNNNM             L",
                            "L            MNNNNNNNNNNNNNNNNNM            L ",
                            "L            MNNNNNNNNNNNNNNNNNM            L ",
                            " L           LNNNNNNNNNNNNNNNNNL           L  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            "    L            LMMMMMMMMML            L     ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "        L                           L         ",
                            "         L                         L          ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "             L                 L              ",
                            "              BBB           BBB               ",
                            "BBB           BDB           BDB           BBB ",
                            "BDB           BBBL         LBBB           BDB ",
                            "BBB               LL     LL               BBB ",
                            "                    LLLLL                     ")
                    .aisle("                    NNNNN                     ",
                            "BBB               NN     NN               BBB ",
                            "BDB           BBBN         NBBB           BDB ",
                            "BBB           BDB           BDB           BBB ",
                            "              BBB           BBB               ",
                            "             N                 N              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "         N                         N          ",
                            "        N                           N         ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "    N            LMMMMMMMMML            N     ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " N           LNNNNNNNNNNNNNNNNNL           N  ",
                            "N            MNNNNNNNNNNNNNNNNNM            N ",
                            "N            MNNNNNNNNNNNNNNNNNM            N ",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "N            MNNNNNNNNNNNNNNNNNM            N ",
                            "N            MNNNNNNNNNNNNNNNNNM            N ",
                            " N           LNNNNNNNNNNNNNNNNNL           N  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            "    N            LMMMMMMMMML            N     ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "        N                           N         ",
                            "         N                         N          ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "             N                 N              ",
                            "              BBB           BBB               ",
                            "BBB           BDB           BDB           BBB ",
                            "BDB           BBBN         NBBB           BDB ",
                            "BBB               NN     NN               BBB ",
                            "                    NNNNN                     ")
                    .aisle("                    NNNNN                     ",
                            "BBB               NN     NN               BBB ",
                            "BDB           BBBN         NBBB           BDB ",
                            "BBB           BDB           BDB           BBB ",
                            "              BBB           BBB               ",
                            "             N                 N              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "         N                         N          ",
                            "        N                           N         ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "    N            LMMMMMMMMML            N     ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " N           LNNNNNNNNNNNNNNNNNL           N  ",
                            "N            MNNNNNNNNNNNNNNNNNM            N ",
                            "N            MNNNNNNNNNNNNNNNNNM            N ",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "N            MNNNNNNNNNNNNNNNNNM            N ",
                            "N            MNNNNNNNNNNNNNNNNNM            N ",
                            " N           LNNNNNNNNNNNNNNNNNL           N  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            "    N            LMMMMMMMMML            N     ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "        N                           N         ",
                            "         N                         N          ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "             N                 N              ",
                            "              BBB           BBB               ",
                            "BBB           BDB           BDB           BBB ",
                            "BDB           BBBN         NBBB           BDB ",
                            "BBB               NN     NN               BBB ",
                            "                    NNNNN                     ")
                    .aisle("                    NNNNN                     ",
                            "BBB               NN     NN               BBB ",
                            "BDB           BBBN         NBBB           BDB ",
                            "BBB           BDB           BDB           BBB ",
                            "              BBB           BBB               ",
                            "             N                 N              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "         N                         N          ",
                            "        N                           N         ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "    N            LMMMMMMMMML            N     ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " N           LNNNNNNNNNNNNNNNNNL           N  ",
                            "N            MNNNNNNNNNNNNNNNNNM            N ",
                            "N            MNNNNNNNNNNNNNNNNNM            N ",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "             MNNNNNNNNNNNNNNNNNM             N",
                            "N            MNNNNNNNNNNNNNNNNNM            N ",
                            "N            MNNNNNNNNNNNNNNNNNM            N ",
                            " N           LNNNNNNNNNNNNNNNNNL           N  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            "    N            LMMMMMMMMML            N     ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "        N                           N         ",
                            "         N                         N          ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "             N                 N              ",
                            "              BBB           BBB               ",
                            "BBB           BDB           BDB           BBB ",
                            "BDB           BBBN         NBBB           BDB ",
                            "BBB               NN     NN               BBB ",
                            "                    NNNNN                     ")
                    .aisle("                    LLLLL                     ",
                            "BBB               LL     LL               BBB ",
                            "BDB           BBBL         LBBB           BDB ",
                            "BBB           BDB     B     BDB           BBB ",
                            "              BBB           BBB               ",
                            "             L                 L              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "         L                         L          ",
                            "        L                           L         ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "    L            LMMMMMMMMML            L     ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " L           LNNNNNNNNNNNNNNNNNL           L  ",
                            "L            MNNNNNNNNNNNNNNNNNM            L ",
                            "L            MNNNNNNNNNNNNNNNNNM            L ",
                            "             MNNNNNNNNNNNNNNNNNM             L",
                            "             MNNNNNNNNNNNNNNNNNM             L",
                            "  B          MNNNNNNNNNNNNNNNNNM          B  L",
                            "             MNNNNNNNNNNNNNNNNNM             L",
                            "             MNNNNNNNNNNNNNNNNNM             L",
                            "L            MNNNNNNNNNNNNNNNNNM            L ",
                            "L            MNNNNNNNNNNNNNNNNNM            L ",
                            " L           LNNNNNNNNNNNNNNNNNL           L  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            "    L            LMMMMMMMMML            L     ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "        L                           L         ",
                            "         L                         L          ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "             L                 L              ",
                            "              BBB           BBB               ",
                            "BBB           BDB     B     BDB           BBB ",
                            "BDB           BBBL         LBBB           BDB ",
                            "BBB               LL     LL               BBB ",
                            "                    LLLLL                     ")
                    .aisle("                                              ",
                            "BBB                                       BBB ",
                            "BDB           BBB     B     BBB           BDB ",
                            "BBB           BDB    BOB    BDB           BBB ",
                            "              BBB     B     BBB               ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "                                              ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "                 LMMMMMMMMML                  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            "             LNNNNNNNNNNNNNNNNNL              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "  B          MNNNNNNNNNNNNNNNNNM          B   ",
                            " BOB         MNNNNNNNNNNNNNNNNNM         BOB  ",
                            "  B          MNNNNNNNNNNNNNNNNNM          B   ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             LNNNNNNNNNNNNNNNNNL              ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            "                 LMMMMMMMMML                  ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "                                              ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "              BBB     B     BBB               ",
                            "BBB           BDB    BOB    BDB           BBB ",
                            "BDB           BBB     B     BBB           BDB ",
                            "BBB                                       BBB ",
                            "                                              ")
                    .aisle("                                              ",
                            "BBB                   B                   BBB ",
                            "BDB           BBB    BOB    BBB           BDB ",
                            "BBB           BDB   BOAOB   BDB           BBB ",
                            "              BBB    BOB    BBB               ",
                            "                     CBC                      ",
                            "          BBB        CBC        BBB           ",
                            "          BDB        CBC        BDB           ",
                            "          BBB        CBC        BBB           ",
                            "                     CBC                      ",
                            "                     CBC                      ",
                            "     BBB             CBC             BBB      ",
                            "     BDB             CBC             BDB      ",
                            "     BBB             CBC             BBB      ",
                            "                 LMMMLLLMMML                  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            "             LNNNNNNNNNNNNNNNNNL              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "  B          MNNNNNNNNNNNNNNNNNM          B   ",
                            " BOBCCCCCCCCCLNNNNNNNNNNNNNNNNNLCCCCCCCCCBOB  ",
                            "BOAOBBBBBBBBBLNNNNNNNNNNNNNNNNNLBBBBBBBBBOAOB ",
                            " BOBCCCCCCCCCLNNNNNNNNNNNNNNNNNLCCCCCCCCCBOB  ",
                            "  B          MNNNNNNNNNNNNNNNNNM          B   ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             LNNNNNNNNNNNNNNNNNL              ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            "                 LMMMLLLMMML                  ",
                            "     BBB             CBC             BBB      ",
                            "     BDB             CBC             BDB      ",
                            "     BBB             CBC             BBB      ",
                            "                     CBC                      ",
                            "                     CBC                      ",
                            "          BBB        CBC        BBB           ",
                            "          BDB        CBC        BDB           ",
                            "          BBB        CBC        BBB           ",
                            "                     CBC                      ",
                            "              BBB    BOB    BBB               ",
                            "BBB           BDB   BOAOB   BDB           BBB ",
                            "BDB           BBB    BOB    BBB           BDB ",
                            "BBB                   B                   BBB ",
                            "                                              ")
                    .aisle("                      B                       ",
                            "BBB                  BOB                  BBB ",
                            "BDB           BBB   BOAOB   BBB           BDB ",
                            "BBB           BDB  BOAAAOB  BDB           BBB ",
                            "              BBB   BOAOB   BBB               ",
                            "                     BAB                      ",
                            "          BBB        BAB        BBB           ",
                            "          BDB        BAB        BDB           ",
                            "          BBB        BAB        BBB           ",
                            "                     BAB                      ",
                            "                     BAB                      ",
                            "     BBB             BAB             BBB      ",
                            "     BDB             BAB             BDB      ",
                            "     BBB             BAB             BBB      ",
                            "                 LMMMLLLMMML                  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            "             LNNNNNNNNNNNNNNNNNL              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "  B          MNNNNNNNNNNNNNNNNNM          B   ",
                            " BOB         MNNNNNNNNNNNNNNNNNM         BOB  ",
                            "BOAOBBBBBBBBBLNNNNNNNNNNNNNNNNNLBBBBBBBBBOAOB ",
                            "OAAAAAAAAAAAALNNNNNNNNNNNNNNNNNLAAAAAAAAAAAAOB",
                            "BOAOBBBBBBBBBLNNNNNNNNNNNNNNNNNLBBBBBBBBBOAOB ",
                            " BOB         MNNNNNNNNNNNNNNNNNM         BOB  ",
                            "  B          MNNNNNNNNNNNNNNNNNM          B   ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             LNNNNNNNNNNNNNNNNNL              ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            "                 LMMMLLLMMML                  ",
                            "     BBB             BAB             BBB      ",
                            "     BDB             BAB             BDB      ",
                            "     BBB             BAB             BBB      ",
                            "                     BAB                      ",
                            "                     BAB                      ",
                            "          BBB        BAB        BBB           ",
                            "          BDB        BAB        BDB           ",
                            "          BBB        BAB        BBB           ",
                            "                     BAB                      ",
                            "              BBB   BOAOB   BBB               ",
                            "BBB           BDB  BOAAAOB  BDB           BBB ",
                            "BDB           BBB   BOAOB   BBB           BDB ",
                            "BBB                  BOB                  BBB ",
                            "                      B                       ")
                    .aisle("                                              ",
                            "BBB                   B                   BBB ",
                            "BDB           BBB    BOB    BBB           BDB ",
                            "BBB           BDB   BOAOB   BDB           BBB ",
                            "              BBB    BOB    BBB               ",
                            "                     CBC                      ",
                            "          BBB        CBC        BBB           ",
                            "          BDB        CBC        BDB           ",
                            "          BBB        CBC        BBB           ",
                            "                     CBC                      ",
                            "                     CBC                      ",
                            "     BBB             CBC             BBB      ",
                            "     BDB             CBC             BDB      ",
                            "     BBB             CBC             BBB      ",
                            "                 LMMMLLLMMML                  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            "             LNNNNNNNNNNNNNNNNNL              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "  B          MNNNNNNNNNNNNNNNNNM          B   ",
                            " BOBCCCCCCCCCLNNNNNNNNNNNNNNNNNLCCCCCCCCCBOB  ",
                            "BOAOBBBBBBBBBLNNNNNNNNNNNNNNNNNLBBBBBBBBBOAOB ",
                            " BOBCCCCCCCCCLNNNNNNNNNNNNNNNNNLCCCCCCCCCBOB  ",
                            "  B          MNNNNNNNNNNNNNNNNNM          B   ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             LNNNNNNNNNNNNNNNNNL              ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            "                 LMMMLLLMMML                  ",
                            "     BBB             CBC             BBB      ",
                            "     BDB             CBC             BDB      ",
                            "     BBB             CBC             BBB      ",
                            "                     CBC                      ",
                            "                     CBC                      ",
                            "          BBB        CBC        BBB           ",
                            "          BDB        CBC        BDB           ",
                            "          BBB        CBC        BBB           ",
                            "                     CBC                      ",
                            "              BBB    BOB    BBB               ",
                            "BBB           BDB   BOAOB   BDB           BBB ",
                            "BDB           BBB    BOB    BBB           BDB ",
                            "BBB                   B                   BBB ",
                            "                                              ")
                    .aisle("                                              ",
                            "BBB                                       BBB ",
                            "BDB           BBB     B     BBB           BDB ",
                            "BBB           BDB    BOB    BDB           BBB ",
                            "              BBB     B     BBB               ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "                                              ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "                 LMMMMMMMMML                  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            "             LNNNNNNNNNNNNNNNNNL              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "  B          MNNNNNNNNNNNNNNNNNM          B   ",
                            " BOB         MNNNNNNNNNNNNNNNNNM         BOB  ",
                            "  B          MNNNNNNNNNNNNNNNNNM          B   ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             LNNNNNNNNNNNNNNNNNL              ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            "                 LMMMMMMMMML                  ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "                                              ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "              BBB     B     BBB               ",
                            "BBB           BDB    BOB    BDB           BBB ",
                            "BDB           BBB     B     BBB           BDB ",
                            "BBB                                       BBB ",
                            "                                              ")
                    .aisle("                                              ",
                            "BBB                                       BBB ",
                            "BDB           BBB           BBB           BDB ",
                            "BBB           BDB     B     BDB           BBB ",
                            "              BBB           BBB               ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "                                              ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "                 LMMMMMMMMML                  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            "             LNNNNNNNNNNNNNNNNNL              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "  B          MNNNNNNNNNNNNNNNNNM          B   ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             MNNNNNNNNNNNNNNNNNM              ",
                            "             LNNNNNNNNNNNNNNNNNL              ",
                            " BBB          LNNNNNNNNNNNNNNNL          BBB  ",
                            " BDB           LNNNNNNNNNNNNNL           BDB  ",
                            " BBB            LNNNNNNNNNNNL            BBB  ",
                            "                 LMMMMMMMMML                  ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "                                              ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "              BBB           BBB               ",
                            "BBB           BDB     B     BDB           BBB ",
                            "BDB           BBB           BBB           BDB ",
                            "BBB                                       BBB ",
                            "                                              ")
                    .aisle("                                              ",
                            "BBB                                       BBB ",
                            "BDB           BBB           BBB           BDB ",
                            "BBB           BDB           BDB           BBB ",
                            "              BBB           BBB               ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "                                              ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "                 LLLLLLLLLLL                  ",
                            " BBB            LLLLLLLLLLLLL            BBB  ",
                            " BDB           LLLLLLLLLLLLLLL           BDB  ",
                            " BBB          LLLLLLLLLLLLLLLLL          BBB  ",
                            "             LLLLLLLLLLLLLLLLLLL              ",
                            "             LLLLLLLLLLLLLLLLLLL              ",
                            "             LLLLLLLLLLLLLLLLLLL              ",
                            "             LLLLLLLLLLLLLLLLLLL              ",
                            "             LLLLLLLLLLLLLLLLLLL              ",
                            "             LLLLLLLLLLLLLLLLLLL              ",
                            "             LLLLLLLLLLLLLLLLLLL              ",
                            "             LLLLLLLLLLLLLLLLLLL              ",
                            "             LLLLLLLLLLLLLLLLLLL              ",
                            "             LLLLLLLLLLLLLLLLLLL              ",
                            "             LLLLLLLLLLLLLLLLLLL              ",
                            " BBB          LLLLLLLLLLLLLLLLL          BBB  ",
                            " BDB           LLLLLLLLLLLLLLL           BDB  ",
                            " BBB            LLLLLLLLLLLLL            BBB  ",
                            "                 LLLLLLLLLLL                  ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "                                              ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "              BBB           BBB               ",
                            "BBB           BDB           BDB           BBB ",
                            "BDB           BBB           BBB           BDB ",
                            "BBB                                       BBB ",
                            "                                              ")
                    .aisle("                                              ",
                            "BBB                                       BBB ",
                            "BDB           BBB           BBB           BDB ",
                            "BBB           BDB           BDB           BBB ",
                            "              BBB           BBB               ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "                                              ",
                            "     BBB                             BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB         KKKKKKKKKKK         BBB      ",
                            "                KKKKKKKKKKKKK                 ",
                            " BBB           KKKKKKKKKKKKKKK           BBB  ",
                            " BDB          KKKKKKKKKKKKKKKKK          BDB  ",
                            " BBB         KKKKKKKKKKKKKKKKKKK         BBB  ",
                            "            KKKKKKKKKKKKKKKKKKKKK             ",
                            "            KKKKKKKKKKKKKKKKKKKKK             ",
                            "            KKKKKKKKKKKKKKKKKKKKK             ",
                            "            KKKKKKKKKKKKKKKKKKKKK             ",
                            "            KKKKKKKKKKKKKKKKKKKKK             ",
                            "            KKKKKKKKKKKKKKKKKKKKK             ",
                            "            KKKKKKKKKKKKKKKKKKKKK             ",
                            "            KKKKKKKKKKKKKKKKKKKKK             ",
                            "            KKKKKKKKKKKKKKKKKKKKK             ",
                            "            KKKKKKKKKKKKKKKKKKKKK             ",
                            "            KKKKKKKKKKKKKKKKKKKKK             ",
                            " BBB         KKKKKKKKKKKKKKKKKKK         BBB  ",
                            " BDB          KKKKKKKKKKKKKKKKK          BDB  ",
                            " BBB           KKKKKKKKKKKKKKK           BBB  ",
                            "                KKKKKKKKKKKKK                 ",
                            "     BBB         KKKKKKKKKKK         BBB      ",
                            "     BDB                             BDB      ",
                            "     BBB                             BBB      ",
                            "                                              ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "              BBB           BBB               ",
                            "BBB           BDB           BDB           BBB ",
                            "BDB           BBB           BBB           BDB ",
                            "BBB                                       BBB ",
                            "                                              ")
                    .aisle("                                              ",
                            "BBB                                       BBB ",
                            "BDB           BBB           BBB           BDB ",
                            "BBB           BDB           BDB           BBB ",
                            "              BBB           BBB               ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "                                              ",
                            "     BBB                             BBB      ",
                            "     BDB         JJJJJJJJJJJ         BDB      ",
                            "     BBB        JJJJJJJJJJJJJ        BBB      ",
                            "               JJJJJJJJJJJJJJJ                ",
                            " BBB          JJJJJJJJJJJJJJJJJ          BBB  ",
                            " BDB         JJJJJJJJJJJJJJJJJJJ         BDB  ",
                            " BBB        JJJJJJJJJJJJJJJJJJJJJ        BBB  ",
                            "           JJJJJJJJJJJJJJJJJJJJJJJ            ",
                            "           JJJJJJJJJJJJJJJJJJJJJJJ            ",
                            "           JJJJJJJJJJJJJJJJJJJJJJJ            ",
                            "           JJJJJJJJJJJJJJJJJJJJJJJ            ",
                            "           JJJJJJJJJJJJJJJJJJJJJJJ            ",
                            "           JJJJJJJJJJJJJJJJJJJJJJJ            ",
                            "           JJJJJJJJJJJJJJJJJJJJJJJ            ",
                            "           JJJJJJJJJJJJJJJJJJJJJJJ            ",
                            "           JJJJJJJJJJJJJJJJJJJJJJJ            ",
                            "           JJJJJJJJJJJJJJJJJJJJJJJ            ",
                            "           JJJJJJJJJJJJJJJJJJJJJJJ            ",
                            " BBB        JJJJJJJJJJJJJJJJJJJJJ        BBB  ",
                            " BDB         JJJJJJJJJJJJJJJJJJJ         BDB  ",
                            " BBB          JJJJJJJJJJJJJJJJJ          BBB  ",
                            "               JJJJJJJJJJJJJJJ                ",
                            "     BBB        JJJJJJJJJJJJJ        BBB      ",
                            "     BDB         JJJJJJJJJJJ         BDB      ",
                            "     BBB                             BBB      ",
                            "                                              ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "              BBB           BBB               ",
                            "BBB           BDB           BDB           BBB ",
                            "BDB           BBB           BBB           BDB ",
                            "BBB                                       BBB ",
                            "                                              ")
                    .aisle("                                              ",
                            "BBB                                       BBB ",
                            "BDB           BBB           BBB           BDB ",
                            "BBB           BDB           BDB           BBB ",
                            "              BBB           BBB               ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "                                              ",
                            "     BBB         AAAAAAAAAAA         BBB      ",
                            "     BDB        AAAAAAAAAAAAA        BDB      ",
                            "     BBB       AAAAAAAAAAAAAAA       BBB      ",
                            "              AAAAAAAAAAAAAAAAA               ",
                            " BBB         AAAAAAAAAAAAAAAAAAA         BBB  ",
                            " BDB        AAAAAAAAAAAAAAAAAAAAA        BDB  ",
                            " BBB       AAAAAAAAAAAAAAAAAAAAAAA       BBB  ",
                            "          AAAAAAAAAAAAAAAAAAAAAAAAA           ",
                            "          AAAAAAAAAAAAAAAAAAAAAAAAA           ",
                            "          AAAAAAAAAAAAAAAAAAAAAAAAA           ",
                            "          AAAAAAAAAAAAAAAAAAAAAAAAA           ",
                            "          AAAAAAAAAAAAAAAAAAAAAAAAA           ",
                            "          AAAAAAAAAAAAAAAAAAAAAAAAA           ",
                            "          AAAAAAAAAAAAAAAAAAAAAAAAA           ",
                            "          AAAAAAAAAAAAAAAAAAAAAAAAA           ",
                            "          AAAAAAAAAAAAAAAAAAAAAAAAA           ",
                            "          AAAAAAAAAAAAAAAAAAAAAAAAA           ",
                            "          AAAAAAAAAAAAAAAAAAAAAAAAA           ",
                            " BBB       AAAAAAAAAAAAAAAAAAAAAAA       BBB  ",
                            " BDB        AAAAAAAAAAAAAAAAAAAAA        BDB  ",
                            " BBB         AAAAAAAAAAAAAAAAAAA         BBB  ",
                            "              AAAAAAAAAAAAAAAAA               ",
                            "     BBB       AAAAAAAAAAAAAAA       BBB      ",
                            "     BDB        AAAAAAAAAAAAA        BDB      ",
                            "     BBB         AAAAAAAAAAA         BBB      ",
                            "                                              ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "              BBB           BBB               ",
                            "BBB           BDB           BDB           BBB ",
                            "BDB           BBB           BBB           BDB ",
                            "BBB                                       BBB ",
                            "                                              ")
                    .aisle("                                              ",
                            "BBB                                       BBB ",
                            "BDB           BBB           BBB           BDB ",
                            "BBB           BDB           BDB           BBB ",
                            "              BBB           BBB               ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "                 IIIIIIIIIII                  ",
                            "     BBB        IIIIIIIIIIIII        BBB      ",
                            "     BDB       IIIIIIIIIIIIIII       BDB      ",
                            "     BBB      IIIIIIIIIIIIIIIII      BBB      ",
                            "             IIIIIIIIIIIIIIIIIII              ",
                            " BBB        IIIIIIIIIIIIIIIIIIIII        BBB  ",
                            " BDB       IIIIIIIIIIIIIIIIIIIIIII       BDB  ",
                            " BBB      IIIIIIIIIIIIIIIIIIIIIIIII      BBB  ",
                            "         IIIIIIIIIIIIIIIIIIIIIIIIIII          ",
                            "         IIIIIIIIIIIIIIIIIIIIIIIIIII          ",
                            "         IIIIIIIIIIIIIIIIIIIIIIIIIII          ",
                            "         IIIIIIIIIIIIIIIIIIIIIIIIIII          ",
                            "         IIIIIIIIIIIIIIIIIIIIIIIIIII          ",
                            "         IIIIIIIIIIIIIIIIIIIIIIIIIII          ",
                            "         IIIIIIIIIIIIIIIIIIIIIIIIIII          ",
                            "         IIIIIIIIIIIIIIIIIIIIIIIIIII          ",
                            "         IIIIIIIIIIIIIIIIIIIIIIIIIII          ",
                            "         IIIIIIIIIIIIIIIIIIIIIIIIIII          ",
                            "         IIIIIIIIIIIIIIIIIIIIIIIIIII          ",
                            " BBB      IIIIIIIIIIIIIIIIIIIIIIIII      BBB  ",
                            " BDB       IIIIIIIIIIIIIIIIIIIIIII       BDB  ",
                            " BBB        IIIIIIIIIIIIIIIIIIIII        BBB  ",
                            "             IIIIIIIIIIIIIIIIIII              ",
                            "     BBB      IIIIIIIIIIIIIIIII      BBB      ",
                            "     BDB       IIIIIIIIIIIIIII       BDB      ",
                            "     BBB        IIIIIIIIIIIII        BBB      ",
                            "                 IIIIIIIIIII                  ",
                            "                                              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "                                              ",
                            "              BBB           BBB               ",
                            "BBB           BDB           BDB           BBB ",
                            "BDB           BBB           BBB           BDB ",
                            "BBB                                       BBB ",
                            "                                              ")
                    .aisle("                    BBFBB                     ",
                            "BBB               BB     BB               BBB ",
                            "BDB           BBBB         BBBB           BDB ",
                            "BBB           BDB           BDB           BBB ",
                            "              BBB           BBB               ",
                            "             B                 B              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "         B       HHHHHHHHHHH       B          ",
                            "        B       HHHHHHHHHHHHH       B         ",
                            "     BBB       HHHHHHHHHHHHHHH       BBB      ",
                            "     BDB      HHHHHHHHHHHHHHHHH      BDB      ",
                            "     BBB     HHHHHHHHHHHHHHHHHHH     BBB      ",
                            "    B       HHHHHHHHHHHHHHHHHHHHH       B     ",
                            " BBB       HHHHHHHHHHHHHHHHHHHHHHH       BBB  ",
                            " BDB      HHHHHHHHHHHHHHHHHHHHHHHHH      BDB  ",
                            " BBB     HHHHHHHHHHHHHHHHHHHHHHHHHHH     BBB  ",
                            " B      HHHHHHHHHHHHHHHHHHHHHHHHHHHHH      B  ",
                            "B       HHHHHHHHHHHHHHHHHHHHHHHHHHHHH       B ",
                            "B       HHHHHHHHHHHHHHHHHHHHHHHHHHHHH       B ",
                            "        HHHHHHHHHHHHHHHHHHHHHHHHHHHHH        B",
                            "        HHHHHHHHHHHHHHHHHHHHHHHHHHHHH        B",
                            "        HHHHHHHHHHHHHHHHHHHHHHHHHHHHH        F",
                            "        HHHHHHHHHHHHHHHHHHHHHHHHHHHHH        B",
                            "        HHHHHHHHHHHHHHHHHHHHHHHHHHHHH        B",
                            "B       HHHHHHHHHHHHHHHHHHHHHHHHHHHHH       B ",
                            "B       HHHHHHHHHHHHHHHHHHHHHHHHHHHHH       B ",
                            " B      HHHHHHHHHHHHHHHHHHHHHHHHHHHHH      B  ",
                            " BBB     HHHHHHHHHHHHHHHHHHHHHHHHHHH     BBB  ",
                            " BDB      HHHHHHHHHHHHHHHHHHHHHHHHH      BDB  ",
                            " BBB       HHHHHHHHHHHHHHHHHHHHHHH       BBB  ",
                            "    B       HHHHHHHHHHHHHHHHHHHHH       B     ",
                            "     BBB     HHHHHHHHHHHHHHHHHHH     BBB      ",
                            "     BDB      HHHHHHHHHHHHHHHHH      BDB      ",
                            "     BBB       HHHHHHHHHHHHHHH       BBB      ",
                            "        B       HHHHHHHHHHHHH       B         ",
                            "         B       HHHHHHHHHHH       B          ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "             B                 B              ",
                            "              BBB           BBB               ",
                            "BBB           BDB           BDB           BBB ",
                            "BDB           BBBB         BBBB           BDB ",
                            "BBB               BB     BB               BBB ",
                            "                    BBFBB                     ")
                    .aisle("                    FFFFF                     ",
                            "BBB               FF     FF               BBB ",
                            "BDB           BBBF         FBBB           BDB ",
                            "BBB           BDB           BDB           BBB ",
                            "              BBB           BBB               ",
                            "             F                 F              ",
                            "          BBB                   BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB    GGGGGGGGGGG    BBB           ",
                            "         F      GGGGGGGGGGGGG      F          ",
                            "        F      GGGGGGGGGGGGGGG      F         ",
                            "     BBB      GGGGGGGGGGGGGGGGG      BBB      ",
                            "     BDB     GGGGGGGGGGGGGGGGGGG     BDB      ",
                            "     BBB    GGGGGGGGGGGGGGGGGGGGG    BBB      ",
                            "    F      GGGGGGGGGGGGGGGGGGGGGGG      F     ",
                            " BBB      GGGGGGGGGGGGGGGGGGGGGGGGG      BBB  ",
                            " BDB     GGGGGGGGGGGGGGGGGGGGGGGGGGG     BDB  ",
                            " BBB    GGGGGGGGGGGGGGGGGGGGGGGGGGGGG    BBB  ",
                            " F     GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG     F  ",
                            "F      GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG      F ",
                            "F      GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG      F ",
                            "       GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG       F",
                            "       GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG       F",
                            "       GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG       F",
                            "       GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG       F",
                            "       GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG       F",
                            "F      GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG      F ",
                            "F      GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG      F ",
                            " F     GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG     F  ",
                            " BBB    GGGGGGGGGGGGGGGGGGGGGGGGGGGGG    BBB  ",
                            " BDB     GGGGGGGGGGGGGGGGGGGGGGGGGGG     BDB  ",
                            " BBB      GGGGGGGGGGGGGGGGGGGGGGGGG      BBB  ",
                            "    F      GGGGGGGGGGGGGGGGGGGGGGG      F     ",
                            "     BBB    GGGGGGGGGGGGGGGGGGGGG    BBB      ",
                            "     BDB     GGGGGGGGGGGGGGGGGGG     BDB      ",
                            "     BBB      GGGGGGGGGGGGGGGGG      BBB      ",
                            "        F      GGGGGGGGGGGGGGG      F         ",
                            "         F      GGGGGGGGGGGGG      F          ",
                            "          BBB    GGGGGGGGGGG    BBB           ",
                            "          BDB                   BDB           ",
                            "          BBB                   BBB           ",
                            "             F                 F              ",
                            "              BBB           BBB               ",
                            "BBB           BDB           BDB           BBB ",
                            "BDB           BBBF         FBBB           BDB ",
                            "BBB               FF     FF               BBB ",
                            "                    FFFFF                     ")
                    .aisle("                    BBFBB                     ",
                            "CCC               BB     BB               CCC ",
                            "CDC           BBBB         BBBB           CDC ",
                            "CCC           BDB           BDB           CCC ",
                            "              BBB           BBB               ",
                            "             B                 B              ",
                            "          BBB                   BBB           ",
                            "          BDB    EEEEEEEEEEE    BDB           ",
                            "          BBB   EEEEEEEEEEEEE   BBB           ",
                            "         B     EEEEEEEEEEEEEEE     B          ",
                            "        B     EEEEEEEEEEEEEEEEE     B         ",
                            "     BBB     EEEEEEEEEEEEEEEEEEE     BBB      ",
                            "     BDB    EEEEEEEEEEEEEEEEEEEEE    BDB      ",
                            "     BBB   EEEEEEEEEEEEEEEEEEEEEEE   BBB      ",
                            "    B     EEEEEEEEEEEEEEEEEEEEEEEEE     B     ",
                            " BBB     EEEEEEEEEEEEEEEEEEEEEEEEEEE     BBB  ",
                            " BDB    EEEEEEEEEEEEEEEEEEEEEEEEEEEEE    BDB  ",
                            " BBB   EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE   BBB  ",
                            " B    EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE    B  ",
                            "B     EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE     B ",
                            "B     EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE     B ",
                            "      EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE      B",
                            "      EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE      B",
                            "      EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE      F",
                            "      EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE      B",
                            "      EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE      B",
                            "B     EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE     B ",
                            "B     EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE     B ",
                            " B    EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE    B  ",
                            " BBB   EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE   BBB  ",
                            " BDB    EEEEEEEEEEEEEEEEEEEEEEEEEEEEE    BDB  ",
                            " BBB     EEEEEEEEEEEEEEEEEEEEEEEEEEE     BBB  ",
                            "    B     EEEEEEEEEEEEEEEEEEEEEEEEE     B     ",
                            "     BBB   EEEEEEEEEEEEEEEEEEEEEEE   BBB      ",
                            "     BDB    EEEEEEEEEEEEEEEEEEEEE    BDB      ",
                            "     BBB     EEEEEEEEEEEEEEEEEEE     BBB      ",
                            "        B     EEEEEEEEEEEEEEEEE     B         ",
                            "         B     EEEEEEEEEEEEEEE     B          ",
                            "          BBB   EEEEEEEEEEEEE   BBB           ",
                            "          BDB    EEEEEEEEEEE    BDB           ",
                            "          BBB                   BBB           ",
                            "             B                 B              ",
                            "              BBB           BBB               ",
                            "CCC           BDB           BDB           CCC ",
                            "CDC           BBBB         BBBB           CDC ",
                            "CCC               BB     BB               CCC ",
                            "                    BBFBB                    A")

                    .where("A", Predicates.any())
                    .where("B", Predicates.frames(CTNHMaterials.Infinity))
                    .where("C", Predicates.blocks(CASING_NAQUADAH_GEARBOX.get()))
                    .where("D", Predicates.blocks(ADVANCED_COMPUTER_CASING.get()))
                    .where("E", Predicates.blocks(ANNIHILATE_CORE_MKI.get()))
                    .where("F", Predicates.blocks(MACHINE_CASING_UHV.get()))
                    .where("G", Predicates.blocks(FUSION_CASING_MK3.get()))
                    .where("H", Predicates.blocks(PLASMA_COOLED_CORE.get()))
                    .where("I", Predicates.blocks(CASING_NAQUADAH_GEARBOX.get()))
                    .where("J", Predicates.blocks(MACHINE_CASING_UHV.get()))
                    .where("K", Predicates.blocks(CASING_ANTIFREEZE_HEATPROOF_MACHINE.get()))
                    .where("L", Predicates.blocks(MATERIAL_BLOCKS.get(TagPrefix.block, CTNHMaterials.Infinity).get()))
                    .where("M", Predicates.blocks(FUSION_GLASS.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("N", heatingCoils())
                    .where("O", Predicates.blocks(HIGH_POWER_CASING.get()))
                    .where("P", Predicates.blocks(COMPUTER_CASING.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes())))

                    .build())
            .workableCasingModel(GTCEu.id("block/casings/gcym/nonconducting_casing"),
                    GTCEu.id("block/multiblock/generator/large_steam_turbine"))
            .register();
    // public final static MultiblockMachineDefinition HYBRID_POWER_MIXER = REGISTRATE.multiblock("hybrid_power_mixer",
    // Hybrid_Power_Mixer::new)
    // .rotationState(RotationState.NON_Y_AXIS)
    // .recipeType(GTRecipeTypes.MIXER_RECIPES)
    // .recipeModifiers(Hybrid_Power_Mixer::recipeModifier,
    // GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.PERFECT_OVERCLOCK_SUBTICK))
    // .tooltips(Component.translatable("ctnh.multiblock.hybrid_mixer.tooltip.0"),
    // Component.translatable("ctnh.multiblock.hybrid_mixer.tooltip.1"),
    // Component.translatable("ctnh.multiblock.hybrid_mixer.tooltip.2"),
    // Component.translatable("ctnh.multiblock.hybrid_mixer.tooltip.3"),
    // Component.translatable("ctnh.multiblock.hybrid_mixer.tooltip.4")
    // )
    // .pattern(definition -> FactoryBlockPattern.start()
    // .aisle("##BBB##", "##BBB##", "##BBB##", "###D###", "###D###", "###B###")
    // .aisle("##BEB##", "##FGF##", "##FHF##", "##FHF##", "##FGF##", "##BIB##")
    // .aisle("BBJEJBB", "BFJGJFB", "BFJHJFB", "#FJ#JF#", "#FJGJF#", "#BJIJB#")
    // .aisle("BEEEEEB", "GGGGGGG", "BHHHHHB", "DH#H#HD", "DGGGGGD", "BIIIIIB")
    // .aisle("BBJEJBB", "BFJGJFB", "BFJHJFB", "#FJ#JF#", "#FJGJF#", "#BJIJB#")
    // .aisle("##BEB##", "##FGF##", "##FHF##", "##FHF##", "##FGF##", "##BIB##")
    // .aisle("##BBB##", "##BCB##", "##BBB##", "###D###", "###D###", "###B###")
    // .where("#", Predicates.any())
    // .where("B", Predicates.blocks(CASING_OSMIRIDIUM.get())
    // .or(Predicates.autoAbilities(definition.getRecipeTypes()))
    // .or(Predicates.abilities(CTPPPartAbility.INPUT_KINETIC)))
    //
    // .where("C", Predicates.controller(Predicates.blocks(definition.get())))
    // .where("D", Predicates.frames(Titanium))
    // .where("E", Predicates.blocks(ZENITH_CASING_GEARBOX.get()))
    // .where("F", Predicates.blocks(CASING_LAMINATED_GLASS.get()))
    // .where("G", Predicates.blocks(COIL_ULTRA_MANA.get()))
    // .where("H", Predicates.blocks(ELEMENTIUM_PIPE_CASING.get()))
    // .where("I", Predicates.blocks(ELEMENTIUM_CASING.get()))
    // .where("J", Predicates.blocks(HERMETIC_CASING_LuV.get()))
    // .build())
    // .workableCasingModel(CTNHCore.id("block/casings/osmiridium_casing"),
    // GTCEu.id("block/multiblock/generator/large_steam_turbine"))
    // .register();
    public final static MultiblockMachineDefinition COMPONENT_ASSEMBLY_LINE_CT = REGISTRATE
            .multiblock("component_assembly_line_ct", Hybrid_Power_Mixer::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeTypes(CTNHRecipeTypes.PVB_RECIPE, CTNHRecipeTypes.CHEMICAL_VAPOR_DEPOSITION)
            .recipeModifiers(Hybrid_Power_Mixer::recipeModifier,
                    GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.PERFECT_OVERCLOCK_SUBTICK))
            .tooltips(Component.translatable("ctnh.mutiblock.hybrid_mixer.tooltip.0"),
                    Component.translatable("ctnh.mutiblock.hybrid_mixer.tooltip.1"),
                    Component.translatable("ctnh.mutiblock.hybrid_mixer.tooltip.2"),
                    Component.translatable("ctnh.mutiblock.hybrid_mixer.tooltip.3"),
                    Component.translatable("ctnh.mutiblock.hybrid_mixer.tooltip.4"))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("ABBBBBBBBBA", "ACCCCCCCCCA", "@AAAAAAAAAA", "A#########A")
                    .aisle("ADDDDDDDDDA", "B#########B", "AEEEEEEEEEA", "AAAAAAAAAAA")
                    .aisle("ABBBBBBBBBA", "ACCCCCCCCCA", "AAAAAAAAAAA", "A#########A")
                    .where("A", Predicates.blocks(AllBlocks.RAILWAY_CASING.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.abilities(CTPPPartAbility.INPUT_KINETIC)))
                    .where("B", Predicates.blocks(CASING_OSMIRIDIUM.get()))
                    .where("C", Predicates.blocks(FUSION_GLASS.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("#", Predicates.any())
                    .where("D", Predicates.blocks(AllBlocks.RAILWAY_CASING.get()))
                    .where("E", Predicates.blocks(AllBlocks.DEPLOYER.get()))
                    .build())

            .workableCasingModel(CTPP.id("block/create/railway_casing"),
                    GTCEu.id("block/multiblock/generator/large_steam_turbine"))
            .register();
    public final static MultiblockMachineDefinition COMBINED_VAPOR_DEPOSITION_FACILITY = REGISTRATE
            .multiblock("combined_vapor_deposition_facility", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeTypes(CTNHRecipeTypes.PVB_RECIPE, CTNHRecipeTypes.CHEMICAL_VAPOR_DEPOSITION)
            .recipeModifiers(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.PERFECT_OVERCLOCK_SUBTICK))
            .tooltips(CTNHCommonTooltips.PERFECT_OVERCLOCK)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("ABBBA", "BACAB", "BCCCB", "BACAB", "ABBBA")
                    .aisle("BDDDB", "AEFEA", "AGHGA", "AEFEA", "BDDDB")
                    .aisle("BDDDB", "AFFFA", "AIFIA", "AFFFA", "BDDDB")
                    .aisle("BDDDB", "AEFEA", "AGHGA", "AEFEA", "BDDDB")
                    .aisle("ABBBA", "BACAB", "BC@CB", "BACAB", "ABBBA")
                    .where("A", Predicates.blocks(MACHINE_CASING_LuV.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.abilities(PartAbility.COMPUTATION_DATA_RECEPTION).setExactLimit(1)
                                    .setPreviewCount(1))
                            .or(Predicates.autoAbilities(true, false, false)))
                    .where("B", Predicates.blocks(HERMETIC_CASING_LuV.get()))
                    .where("C", Predicates.blocks(CASING_LAMINATED_GLASS.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("D", Predicates.blocks(HIGH_POWER_CASING.get()))
                    .where("E", Predicates.blocks(COIL_HSSG.get()))
                    .where("F", Predicates.blocks(CASING_TUNGSTENSTEEL_PIPE.get()))
                    .where("G", Predicates.blocks(CASING_TUNGSTENSTEEL_GEARBOX.get()))
                    .where("H", Predicates.blocks(MOLYBDENUM_DISILICIDE_COIL_BLOCK.get()))
                    .where("I", Predicates.blocks(MOLYBDENUM_DISILICIDE_COIL_BLOCK.get()))
                    .build())

            .workableCasingModel(GTCEu.id("block/casings/hpca/high_power_casing"),
                    GTCEu.id("block/multiblock/generator/large_steam_turbine"))
            .register();
    public final static MultiblockMachineDefinition LaserSorder = REGISTRATE.multiblock("lasersorder", LaserSorter::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeTypes(CTNHRecipeTypes.LS_RECIPE, GTRecipeTypes.LASER_ENGRAVER_RECIPES)
            .recipeModifiers(LaserSorter::recipeModifier,
                    GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK_SUBTICK))
            .tooltips(Component.translatable("ctnh.multiblock.lasersorter.tooltip.0"),
                    Component.translatable("ctnh.multiblock.lasersorter.tooltip.1"),
                    Component.translatable("ctnh.multiblock.lasersorter.tooltip.2"),
                    Component.translatable("ctnh.multiblock.lasersorter.tooltip.3"),
                    Component.translatable("ctnh.multiblock.lasersorter.tooltip.4"),
                    Component.translatable("ctnh.multiblock.lasersorter.tooltip.5"),
                    Component.translatable("ctnh.multiblock.lasersorter.tooltip.6"),
                    Component.translatable("ctnh.multiblock.lasersorter.tooltip.7"),
                    Component.translatable("ctnh.multiblock.lasersorter.tooltip.8"),
                    Component.translatable("ctnh.multiblock.lasersorter.tooltip.9"),
                    Component.translatable("ctnh.multiblock.lasersorter.tooltip.10"),
                    Component.translatable("ctnh.multiblock.lasersorter.tooltip.11"))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAAAA", "AABBBAA", "AABBBAA", "AABBBAA", "AAAAAAA")
                    .aisle("ACCCCCA", "AC###CA", "AC###CA", "AC###CA", "ACCCCCA")
                    .aisle("ACCCCCA", "B#####B", "B#####B", "B#####B", "ACCDCCA")
                    .aisle("ACCCCCA", "B#####B", "B#####B", "B#####B", "ACDDDCA")
                    .aisle("ACCCCCA", "B#####B", "B#####B", "B#####B", "ACCDCCA")
                    .aisle("ACCCCCA", "AC###CA", "AC###CA", "AC###CA", "ACCCCCA")
                    .aisle("AAAAAAA", "AABBBAA", "AAB@BAA", "AABBBAA", "AAAAAAA")
                    .where("A", Predicates.blocks(COMPUTER_HEAT_VENT.get()))
                    .where("B", Predicates.blocks(HIGH_POWER_CASING.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.abilities(PartAbility.COMPUTATION_DATA_RECEPTION).setExactLimit(1))
                            .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1)))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("C", Predicates.blocks(ADVANCED_COMPUTER_CASING.get()))
                    .where("#", Predicates.any())
                    .where("D", Predicates.blocks(FUSION_GLASS.get()))
                    .build())

            .workableCasingModel(GTCEu.id("block/casings/hpca/high_power_casing"),
                    GTCEu.id("block/multiblock/generator/large_steam_turbine"))
            .register();
    public final static MultiblockMachineDefinition SPACEPHOTOVOLTAICBASESTATION = REGISTRATE
            .multiblock("space_photovoltai_cbase_station", SpacePhotovoltaicBaseStation::new)
            .rotationState(RotationState.ALL)
            .generator(true)
            .recipeTypes(CTNHRecipeTypes.PHOTOVOLTAIC_GENERATOR, CTNHRecipeTypes.PHOTOVOLTAIC_ASSEMBER)
            .recipeModifier(SpacePhotovoltaicBaseStation::recipeModifier, true)
            .tooltips(Component.translatable("ctnh.multiblock.spacephotovoltaicbasestation.tooltip.0"),
                    Component.translatable("ctnh.multiblock.spacephotovoltaicbasestation.tooltip.2"),
                    Component.translatable("ctnh.multiblock.spacephotovoltaicbasestation.tooltip.3"),
                    Component.translatable("ctnh.multiblock.spacephotovoltaicbasestation.tooltip.4"),
                    Component.translatable("ctnh.multiblock.spacephotovoltaicbasestation.tooltip.5"),
                    Component.translatable("ctnh.multiblock.spacephotovoltaicbasestation.tooltip.ex")

            )
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("A############B#######B##############", "#############B#######B##############",
                            "#############B#######B##############", "#############B#######B##############",
                            "#############B#######B##############", "#############B#######B##############",
                            "#############B#######B##############", "#############B#######B##############",
                            "#############B#######B##############", "#############B#######B##############",
                            "#############B#######B##############", "#############B#######B##############",
                            "#############B#######B##############", "#############B#######B##############",
                            "#############B#######B##############", "#############B#######B##############",
                            "#############B#######B##############", "#############B#######B##############",
                            "#############BCDCCCDCB##############", "#############BCDCECDCB##############",
                            "#############BCDCCCDCB##############", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################")
                    .aisle("####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "##############CDDDDDC###############",
                            "#############F#######F##############", "#############C###G###C##############",
                            "#############F##H#H##F##############", "##############CDDDDDC###############",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################")
                    .aisle("####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "##############CCCCCCC###############", "#############F#######F##############",
                            "############F#########F#############", "############C####G####C#############",
                            "############F###H#H###F#############", "#############F#######F##############",
                            "##############CCCCCCC###############", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################")
                    .aisle("####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "##############FFFFFFF###############",
                            "#############C#######C##############", "############F#########F#############",
                            "###########F###########F############", "###########C#####G#####C############",
                            "###########F####H#H####F############", "############F#########F#############",
                            "#############C#######C##############", "##############FFFFFFF###############",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################")
                    .aisle("####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "##############FFFFFFF###############", "#############F#######F##############",
                            "############C#########C#############", "###########F###########F############",
                            "##########F#############F###########", "##########C######G######C###########",
                            "##########F#####H#H#####F###########", "###########F###########F############",
                            "############C#########C#############", "#############F#######F##############",
                            "##############FFFFFFF###############", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################")
                    .aisle("####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "##############FFFFFFF###############",
                            "#############F#######F##############", "############F#########F#############",
                            "###########C###########C############", "##########F#############F###########",
                            "#########F###############F##########", "#########C#######G#######C##########",
                            "#########F######H#H######F##########", "##########F#############F###########",
                            "###########C###########C############", "############F#########F#############",
                            "#############F#######F##############", "##############FFFFFFF###############",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################")
                    .aisle("#############B#######B##############", "#############B#######B##############",
                            "#############B#######B##############", "#############B#######B##############",
                            "#############B#######B##############", "#############B#######B##############",
                            "#############B#######B##############", "#############B#######B##############",
                            "#############B#######B##############", "#############B#######B##############",
                            "#############B#######B##############", "#############B#######B##############",
                            "#############BFFFFFFFB##############", "#############F#######F##############",
                            "############F#########F#############", "###########F###########F############",
                            "##########C#############C###########", "#########F###############F##########",
                            "########F#################F#########", "########C########G########C#########",
                            "########F#######H#H#######F#########", "#########F###############F##########",
                            "##########C#############C###########", "###########F###########F############",
                            "############F#########F#############", "#############F#######F##############",
                            "##############FFFFFFF###############", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################")
                    .aisle("####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "##############FFFFFFF###############",
                            "#############F#######F##############", "############F#########F#############",
                            "###########F###########F############", "##########F#############F###########",
                            "#########C###############C##########", "########F#################F#########",
                            "#######F###################F########", "#######C#########G#########C########",
                            "#######F########H#H########F########", "########F#################F#########",
                            "#########C###############C##########", "##########F#############F###########",
                            "###########F###########F############", "############F#########F#############",
                            "#############F#######F##############", "##############FFFFFFF###############",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################")
                    .aisle("####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "##############FFFFFFF###############", "#############F#######F##############",
                            "############F#########F#############", "###########F###########F############",
                            "##########F#############F###########", "#########F###############F##########",
                            "########C#################C#########", "#######F###################F########",
                            "######F#####################F#######", "######C##########G##########C#######",
                            "######F#########H#H#########F#######", "#######F###################F########",
                            "########C#################C#########", "#########F###############F##########",
                            "##########F#############F###########", "###########F###########F############",
                            "############F#########F#############", "#############F#######F##############",
                            "##############FFFFFFF###############", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################")
                    .aisle("####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "##############FFFFFFF###############",
                            "#############F#######F##############", "############F#########F#############",
                            "###########F###########F############", "##########F#############F###########",
                            "#########F###############F##########", "########F#################F#########",
                            "#######C###################C########", "######F#####################F#######",
                            "#####F#######################F######", "#####C###########G###########C######",
                            "#####F##########H#H##########F######", "######F#####################F#######",
                            "#######C###################C########", "########F#################F#########",
                            "#########F###############F##########", "##########F#############F###########",
                            "###########F###########F############", "############F#########F#############",
                            "#############F#######F##############", "##############FFFFFFF###############",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################")
                    .aisle("####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "##############FFFFFFF###############", "#############F#######F##############",
                            "############F#########F#############", "###########F###########F############",
                            "##########F#############F###########", "#########F###############F##########",
                            "########F#################F#########", "#######F###################F########",
                            "######C#####################C#######", "#####F#######################F######",
                            "####F#########################F#####", "####C############G############C#####",
                            "####F###########H#H###########F#####", "#####F#######################F######",
                            "######C#####################C#######", "#######F###################F########",
                            "########F#################F#########", "#########F###############F##########",
                            "##########F#############F###########", "###########F###########F############",
                            "############F#########F#############", "#############F#######F##############",
                            "##############FFFFFFF###############", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################")
                    .aisle("##############BBBBBBB###############", "##############BBBBBBB###############",
                            "##############BBBBBBB###############", "##############BBBBBBB###############",
                            "##############BBBBBBB###############", "##############BBBBBBB###############",
                            "##############BBBBBBB###############", "##############BBBBBBB###############",
                            "#############F###B###F##############", "############F####B####F#############",
                            "###########F#####B#####F############", "##########F######B######F###########",
                            "#########F#######B#######F##########", "########F########B########F#########",
                            "#######F#########B#########F########", "######F##########B##########F#######",
                            "#####C###########B###########C######", "####F############B############F#####",
                            "###F#############B#############F####", "###C#############E#############C####",
                            "###F############H#H############F####", "####F#########################F#####",
                            "#####C#######################C######", "######F#####################F#######",
                            "#######F###################F########", "########F#################F#########",
                            "#########F###############F##########", "##########F#############F###########",
                            "###########F###########F############", "############F#########F#############",
                            "#############F#######F##############", "##############FFHEHFF###############",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################")
                    .aisle("#############IJJJJJJJI##############", "#############I#######I##############",
                            "#############I#######I##############", "#############I#######I##############",
                            "#############I#######I##############", "#############I#######I##############",
                            "#############I#######I##############", "#############I#######I##############",
                            "############F#########F#############", "###########F###########F############",
                            "##########F#############F###########", "#########F###############F##########",
                            "########F#################F#########", "#######F###################F########",
                            "######F#####################F#######", "#####F#######################F######",
                            "####C#########################C#####", "###F###########################F####",
                            "##F#############################F###", "##C#############FKF#############C###",
                            "##F############H###H############F###", "###F###########################F####",
                            "####C#########################C#####", "#####F#######################F######",
                            "######F#####################F#######", "#######F###################F########",
                            "########F#################F#########", "#########F###############F##########",
                            "##########F#############F###########", "###########F###########F############",
                            "############F#########F#############", "#############FEH###HEF##############",
                            "################EEE#################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################")
                    .aisle("############IJJJJJJJJJI#############", "############I#########I#############",
                            "############I#########I#############", "############I#########I#############",
                            "############I#########I#############", "############I#########I#############",
                            "############I#########I#############", "############I#########I#############",
                            "###########F###########F############", "##########F#############F###########",
                            "#########F###############F##########", "########F#################F#########",
                            "#######F###################F########", "######F#####################F#######",
                            "#####F#######################F######", "####F#########################F#####",
                            "###C###########################C####", "##F#############################F###",
                            "#F###############################F##", "#C#############F#K#F#############C##",
                            "#F############H#####H############F##", "##F#############################F###",
                            "###C###########################C####", "####F#########################F#####",
                            "#####F#######################F######", "######F#####################F#######",
                            "#######F###################F########", "########F#################F#########",
                            "#########F###############F##########", "##########F#############F###########",
                            "###########F###########F############", "############FEH#####HEF#############",
                            "###############E###E################", "################HHH#################",
                            "###############EBBBE################", "###############DBBBD################",
                            "###############EBBBE################", "###############DBBBD################",
                            "###############EBBBE################", "###############DBBBD################",
                            "###############EBBBE################")
                    .aisle("B####B#####IJJJJJJJJJJJI#####B####B#", "B####B#####I#K#######K#I#####B####B#",
                            "B####B#####I#K#######K#I#####B####B#", "B####B#####I#K#######K#I#####B####B#",
                            "B####B#####I#K#######K#I#####B####B#", "B####B#####I#K#######K#I#####B####B#",
                            "B####B#####I#K#######K#I#####B####B#", "B####B#####I#K#######K#I#####B####B#",
                            "B####B####F#############F####B####B#", "B####B###F###############F###B####B#",
                            "B####B##F#################F##B####B#", "B####B#F###################F#B####B#",
                            "B####BF#####################FB####B#", "B####F#######################F####B#",
                            "B###F#########################F###B#", "B##F###########################F##B#",
                            "B#C#############################C#B#", "BF###############################FB#",
                            "B#################################B#", "B#############F##K##F#############B#",
                            "B############H#######H############B#", "#F###############################F##",
                            "##C#############################C###", "###F###########################F####",
                            "####F#########################F#####", "#####F#######################F######",
                            "######F#####################F#######", "#######F###################F########",
                            "########F#################F#########", "#########F###############F##########",
                            "##########F#############F###########", "###########FEH#######HEF############",
                            "##############E#####E###############", "###############HLLLH################",
                            "##############E#####E###############", "##############D#####D###############",
                            "##############E#####E###############", "##############D#####D###############",
                            "##############E#####E###############", "##############D#####D###############",
                            "##############E#####E###############")
                    .aisle("##########BJJJJJJJJJJJJJB###########", "##########B###K#####K###B###########",
                            "##########B###K#####K###B###########", "##########B###K#####K###B###########",
                            "##########B###K#####K###B###########", "##########B###K#####K###B###########",
                            "##########B###K#####K###B###########", "##########B###K#####K###B###########",
                            "#########F####K#####K####F##########", "########F#####K#####K#####F#########",
                            "#######F######K#####K######F########", "######F#####################F#######",
                            "#####F#######################F######", "####F#########################F#####",
                            "###F###########################F####", "##F#############################F###",
                            "#C###############################C##", "C#################################C#",
                            "###################################C", "#############F###K###F#############C",
                            "############H#########H############C", "C#################################C#",
                            "#C###############################C##", "##F#############################F###",
                            "###F###########################F####", "####F#########################F#####",
                            "#####F#######################F######", "######F#####################F#######",
                            "#######F###################F########", "########F#################F#########",
                            "#########F###############F##########", "##########FEH#########HEF###########",
                            "#############E#######E##############", "##############JJJJJJJ###############",
                            "#############E#######E##############", "#############D#######D##############",
                            "#############E#######E##############", "#############D#######D##############",
                            "#############E#######E##############", "#############D#######D##############",
                            "#############E#######E##############")
                    .aisle("##########BJJJJJJJJJJJJJB###########", "##########B####K###K####B###########",
                            "##########B####K###K####B###########", "##########B####K###K####B###########",
                            "##########B####K###K####B###########", "##########B####K###K####B###########",
                            "##########B####K###K####B###########", "##########B####K###K####B###########",
                            "#########F#####K###K#####F##########", "########F######K###K######F#########",
                            "#######F#######K###K#######F########", "######F########K###K########F#######",
                            "#####F#########K###K#########F######", "####F##########K###K##########F#####",
                            "###F###########################F####", "##F#############################F###",
                            "#C###############################C##", "D#################################D#",
                            "###################################D", "############F####K####F############D",
                            "###########H###########H###########D", "D#################################D#",
                            "#C###############################C##", "##F#############################F###",
                            "###F###########################F####", "####F#########################F#####",
                            "#####F#######################F######", "######F#####################F#######",
                            "#######F###################F########", "########F#################F#########",
                            "#########F###############F##########", "##########FH###########HF###########",
                            "############E#########E#############", "#############FJJJJJJJH##############",
                            "############E#########E#############", "############D#########D#############",
                            "############E#########E#############", "############D#########D#############",
                            "############E#########E#############", "############D#########D#############",
                            "############E#########E#############")
                    .aisle("##########BJJJJJJJJJJJJJB###########", "##########B#####K#K#####B###########",
                            "##########B#####K#K#####B###########", "##########B#####K#K#####B###########",
                            "##########B#####K#K#####B###########", "##########B#####K#K#####B###########",
                            "##########B#####K#K#####B###########", "##########B#####K#K#####B###########",
                            "#########F######K#K######F##########", "########F#######K#K#######F#########",
                            "#######F########K#K########F########", "######F#########K#K#########F#######",
                            "#####F##########K#K##########F######", "####F###########K#K###########F#####",
                            "###F############K#K############F####", "##F#############K#K#############F###",
                            "#C##############K#K##############C##", "D###############K#K###############D#",
                            "################K#K################C", "###########F####EKE####F###########C",
                            "HHHHHHHHHHH#####G#G#####HHHHHHHHHHHC", "D###############G#G###############D#",
                            "#C##############G#G##############C##", "##F#############G#G#############F###",
                            "###F############G#G############F####", "####F###########G#G###########F#####",
                            "#####F##########G#G##########F######", "######F#########G#G#########F#######",
                            "#######F########G#G########F########", "########F#######G#G#######F#########",
                            "#########F######G#G######F##########", "##########H#####G#G#####H###########",
                            "###########E####G#G####E############", "############HLJJJJJJJLH#############",
                            "############B#########B#############", "############B#########B#############",
                            "############B#########B#############", "############B#########B#############",
                            "############B#########B#############", "############B#########B#############",
                            "############B#########B#############")
                    .aisle("##########BJJJJJJJJJJJJJB###########", "##########B######K######B###########",
                            "##########B######K######B###########", "##########B######K######B###########",
                            "##########B######K######B###########", "##########B######K######B###########",
                            "##########B######K######B###########", "##########B######K######B###########",
                            "#########FB######K######BF##########", "########F#B######K######B#F#########",
                            "#######F##B######K######B##F########", "######F###B######K######B###F#######",
                            "#####F####B######K######B####F######", "####F#####B######K######B#####F#####",
                            "###F######B######K######B######F####", "##F#######B######K######B#######F###",
                            "#C########B######K######B########C##", "D#########B######K######B#########D#",
                            "##########B######K######B##########C", "GGGGGGGGGGEKKKKKKEKKKKKKEGGGGGGGGGGE",
                            "#################G#################C", "D################G################D#",
                            "#C###############G###############C##", "##F##############G##############F###",
                            "###F#############G#############F####", "####F############G############F#####",
                            "#####F###########G###########F######", "######F##########G##########F#######",
                            "#######F#########G#########F########", "########F########G########F#########",
                            "#########F#######G#######F##########", "##########E######G######E###########",
                            "###########E#####G#####E############", "############HLJJJJJJJLH#############",
                            "############B#########B#############", "############B#########B#############",
                            "############B#########B#############", "############B#########B#############",
                            "############B#########B#############", "############B#########B#############",
                            "############B#########B#############")
                    .aisle("##########BJJJJJJJJJJJJJB###########", "##########B#####K#K#####B###########",
                            "##########B#####K#K#####B###########", "##########B#####K#K#####B###########",
                            "##########B#####K#K#####B###########", "##########B#####K#K#####B###########",
                            "##########B#####K#K#####B###########", "##########B#####K#K#####B###########",
                            "#########F######K#K######F##########", "########F#######K#K#######F#########",
                            "#######F########K#K########F########", "######F#########K#K#########F#######",
                            "#####F##########K#K##########F######", "####F###########K#K###########F#####",
                            "###F############K#K############F####", "##F#############K#K#############F###",
                            "#C##############K#K##############C##", "D###############K#K###############D#",
                            "################K#K################C", "###########F####EKE####F###########C",
                            "HHHHHHHHHHH#####G#G#####HHHHHHHHHHHC", "D###############G#G###############D#",
                            "#C##############G#G##############C##", "##F#############G#G#############F###",
                            "###F############G#G############F####", "####F###########G#G###########F#####",
                            "#####F##########G#G##########F######", "######F#########G#G#########F#######",
                            "#######F########G#G########F########", "########F#######G#G#######F#########",
                            "#########F######G#G######F##########", "##########H#####G#G#####H###########",
                            "###########E####G#G####E############", "############HLJJJJJJJLH#############",
                            "############B#########B#############", "############B#########B#############",
                            "############B#########B#############", "############B#########B#############",
                            "############B#########B#############", "############B#########B#############",
                            "############B#########B#############")
                    .aisle("##########BJJJJJJJJJJJJJB###########", "##########B####K###K####B###########",
                            "##########B####K###K####B###########", "##########B####K###K####B###########",
                            "##########B####K###K####B###########", "##########B####K###K####B###########",
                            "##########B####K###K####B###########", "##########B####K###K####B###########",
                            "#########F#####K###K#####F##########", "########F######K###K######F#########",
                            "#######F#######K###K#######F########", "######F########K###K########F#######",
                            "#####F#########K###K#########F######", "####F##########K###K##########F#####",
                            "###F###########################F####", "##F#############################F###",
                            "#C###############################C##", "D#################################D#",
                            "###################################D", "############F####K####F############D",
                            "###########H###########H###########D", "D#################################D#",
                            "#C###############################C##", "##F#############################F###",
                            "###F###########################F####", "####F#########################F#####",
                            "#####F#######################F######", "######F#####################F#######",
                            "#######F###################F########", "########F#################F#########",
                            "#########F###############F##########", "##########FH###########HF###########",
                            "############E#########E#############", "#############HJJJJJJJH##############",
                            "############E#########E#############", "############D#########D#############",
                            "############E#########E#############", "############D#########D#############",
                            "############E#########E#############", "############D#########D#############",
                            "############E#########E#############")
                    .aisle("##########BJJJJJJJJJJJJJB###########", "##########B###K#####K###B###########",
                            "##########B###K#####K###B###########", "##########B###K#####K###B###########",
                            "##########B###K#####K###B###########", "##########B###K#####K###B###########",
                            "##########B###K#####K###B###########", "##########B###K#####K###B###########",
                            "#########F####K#####K####F##########", "########F#####K#####K#####F#########",
                            "#######F######K#####K######F########", "######F#####################F#######",
                            "#####F#######################F######", "####F#########################F#####",
                            "###F###########################F####", "##F#############################F###",
                            "#C###############################C##", "C#################################C#",
                            "###################################C", "#############F###K###F#############C",
                            "############H#########H############C", "C#################################C#",
                            "#C###############################C##", "##F#############################F###",
                            "###F###########################F####", "####F#########################F#####",
                            "#####F#######################F######", "######F#####################F#######",
                            "#######F###################F########", "########F#################F#########",
                            "#########F###############F##########", "##########FEH#########HEF###########",
                            "#############E#######E##############", "##############JJJJJJJ###############",
                            "#############E#######E##############", "#############D#######D##############",
                            "#############E#######E##############", "#############D#######D##############",
                            "#############E#######E##############", "#############D#######D##############",
                            "#############E#######E##############")
                    .aisle("B####B#####IJJJJJJJJJJJI#####B####B#", "B####B#####I#K#######K#I#####B####B#",
                            "B####B#####I#K#######K#I#####B####B#", "B####B#####I#K#######K#I#####B####B#",
                            "B####B#####I#K#######K#I#####B####B#", "B####B#####I#K#######K#I#####B####B#",
                            "B####B#####I#K#######K#I#####B####B#", "B####B#####I#K#######K#I#####B####B#",
                            "B####B####FL###########LF####B####B#", "B####B###FLL###########LLF###B####B#",
                            "B####B##FLLL###########LLLF##B####B#", "B####B#FLLLL###########LLLLF#B####B#",
                            "B####BFLLLLL###########LLLLLFB####B#", "B####FLLLLLL###########LLLLLLF####B#",
                            "B###FLLLLLLL###########LLLLLLLF###B#", "B##FLLLLLLLL###########LLLLLLLLF##B#",
                            "B#FLLLLLLLLL###########LLLLLLLLLF#B#", "BFLLLLLLLLLL###########LLLLLLLLLLFB#",
                            "BLLLLLLLLLLL###########LLLLLLLLLLLB#", "BLLLLLLLLLLL##F##K##F##LLLLLLLLLLLB#",
                            "BLLLLLLLLLLL#H#######H#LLLLLLLLLLLB#", "#FLLLLLLLLLL###########LLLLLLLLLLF##",
                            "##FLLLLLLLLL###########LLLLLLLLLF###", "###FLLLLLLLL###########LLLLLLLLF####",
                            "####FLLLLLLL###########LLLLLLLF#####", "#####FLLLLLL###########LLLLLLF######",
                            "######FLLLLL###########LLLLLF#######", "#######FLLLL###########LLLLF########",
                            "########FLLL###########LLLF#########", "#########FLL###########LLF##########",
                            "##########FL###########LF###########", "###########FEH#######HEF############",
                            "##############E#####E###############", "###############HLLLH################",
                            "##############E#####E###############", "##############D#####D###############",
                            "##############E#####E###############", "##############D#####D###############",
                            "##############E#####E###############", "##############D#####D###############",
                            "##############E#####E###############")
                    .aisle("############IJJJJJJJJJI#############", "############I#########I#############",
                            "############I#########I#############", "############I#########I#############",
                            "############I#########I#############", "############I#########I#############",
                            "############I#########I#############", "############I#########I#############",
                            "############L#########L#############", "############L#########L#############",
                            "############L#########L#############", "############L#########L#############",
                            "############L#########L#############", "############L#########L#############",
                            "############L#########L#############", "############L#########L#############",
                            "############L#########L#############", "############L#########L#############",
                            "############L#########L#############", "############L##F#K#F##L#############",
                            "############L#H#####H#L#############", "############L#########L#############",
                            "############L#########L#############", "############L#########L#############",
                            "############L#########L#############", "############L#########L#############",
                            "############L#########L#############", "############L#########L#############",
                            "############L#########L#############", "############L#########L#############",
                            "############L#########L#############", "############LEH#####HEL#############",
                            "###############E###E################", "################HHH#################",
                            "###############EBBBE################", "###############DBBBD################",
                            "###############EBBBE################", "###############DBBBD################",
                            "###############EBBBE################", "###############DBBBD################",
                            "###############EBBBE################")
                    .aisle("#############IJJJJJJJI##############", "#############I#######I##############",
                            "#############I#######I##############", "#############I#######I##############",
                            "#############I#######I##############", "#############I#######I##############",
                            "#############I#######I##############", "#############I#######I##############",
                            "#############L#######L##############", "#############L#######L##############",
                            "#############L#######L##############", "#############L#######L##############",
                            "#############L#######L##############", "#############L#######L##############",
                            "#############L#######L##############", "#############L#######L##############",
                            "#############L#######L##############", "#############L#######L##############",
                            "#############L#######L##############", "#############L##FKF##L##############",
                            "#############L#H###H#L##############", "#############L#######L##############",
                            "#############L#######L##############", "#############L#######L##############",
                            "#############L#######L##############", "#############L#######L##############",
                            "#############L#######L##############", "#############L#######L##############",
                            "#############L#######L##############", "#############L#######L##############",
                            "#############L#######L##############", "#############LEH###HEL##############",
                            "################EEE#################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################")
                    .aisle("##############BBBBBBB###############", "##############BBBBBBB###############",
                            "##############BBBBBBB###############", "##############BBB@BBB###############",
                            "##############BBBBBBB###############", "##############BBBBBBB###############",
                            "##############BBBBBBB###############", "##############BBBBBBB###############",
                            "##############BBBBBBB###############", "##############BBBBBBB###############",
                            "##############BBBBBBB###############", "##############BBBBBBB###############",
                            "##############BBBBBBB###############", "##############BBBBBBB###############",
                            "##############BBBBBBB###############", "##############BBBBBBB###############",
                            "##############BBBBBBB###############", "##############BBBBBBB###############",
                            "##############BBBBBBB###############", "##############BBBBBBB###############",
                            "##############BBHHHBB###############", "##############LLLLLLL###############",
                            "##############LLLLLLL###############", "##############LLLLLLL###############",
                            "##############LLLLLLL###############", "##############LLLLLLL###############",
                            "##############LLLLLLL###############", "##############LLLLLLL###############",
                            "##############LLLLLLL###############", "##############LLLLLLL###############",
                            "##############LLLLLLL###############", "##############LLHHHLL###############",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "####################################", "####################################",
                            "###################################A")
                    .where("A", Predicates.any())
                    .where("#", Predicates.any())
                    .where("B", (CTNHPredicates.SpaceStructuralFrameworkBlock())
                            .or(Predicates.abilities(PartAbility.OUTPUT_ENERGY))
                            .or(Predicates.abilities(PartAbility.OUTPUT_LASER))
                            .or(Predicates.abilities(PartAbility.IMPORT_ITEMS))
                            .or(Predicates.abilities(PartAbility.EXPORT_ITEMS))
                            .or(Predicates.abilities(PartAbility.EXPORT_FLUIDS))
                            .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS)))
                    .where("C", Predicates.blocks(PV_COIL.get()))
                    .where("D", Predicates.blocks(FUSION_CASING.get()))
                    .where("E", Predicates.blocks(HIGH_POWER_CASING.get()))
                    .where("F", Predicates.blocks(STELLAR_RADIATION_ROUTER_CASING.get()))
                    .where("G", Predicates.blocks(SUPERCONDUCTING_COIL.get()))
                    .where("H", Predicates.blocks(MACHINE_CASING_LuV.get()))
                    .where("I", Predicates.blocks(HERMETIC_CASING_ZPM.get()))
                    .where("J", CTNHPredicates.PhotovoltaicBlock())
                    .where("K", Predicates.blocks(COIL_NAQUADAH.get()))
                    .where("L", Predicates.blocks(FUSION_GLASS.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel(CTNHCore.id("block/casings/depth_force_field_stabilizing_casing"),
                    GTCEu.id("block/multiblock/generator/large_steam_turbine"))
            .register();
    public final static MultiblockMachineDefinition HYPER_PLASMA_TURBINE = HyperPlasmaTurbineRegister.register();
    public final static MultiblockMachineDefinition PHOTOVOLTAIC_DRONE_STATION = REGISTRATE
            .multiblock("photovoltaic_drone_station", PhotoVoltaicDroneStation::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeTypes(CTNHRecipeTypes.PVDRONE)
            .recipeModifiers(PhotoVoltaicDroneStation::recipeModifier)
            .tooltips(Component.translatable("ctnh.multiblock.pvdrone.tooltip.0"),
                    Component.translatable("ctnh.multiblock.pvdrone.tooltip.1"),
                    Component.translatable("ctnh.multiblock.pvdrone.tooltip.2"),
                    Component.translatable("ctnh.multiblock.pvdrone.tooltip.3"),
                    Component.translatable("ctnh.multiblock.pvdrone.tooltip.4"),
                    Component.translatable("ctnh.multiblock.pvdrone.tooltip.5")

            )
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("A#####BBBCCCBBB######", "#########CCC#########", "#########CCC#########",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#########EEE#########",
                            "######FFFECEFFF######", "#########EEE#########", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#########EEE#########", "######FFFECEFFF######", "#########EEE#########",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#########EEE#########", "######FFFECEFFF######",
                            "#########EEE#########", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#########EEE#########",
                            "######FFFECEFFF######")
                    .aisle("####BBBBBBCBBBBBB####", "#########CFC#########", "##########G##########",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "####FF#########FF####", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "####FF#########FF####", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "####FF#########FF####",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "####FFHHHHHHHHHFF####")
                    .aisle("##BBBBBBBBCBBBBBBBB##", "##BCCCC##CFC##CCCCB##", "##B#######G#######B##",
                            "##B###############B##", "##B###############B##", "##B###############B##",
                            "##B###############B##", "##B###############B##", "##B###############B##",
                            "##BF#############FB##", "##B###############B##", "##B###############B##",
                            "##B###############B##", "##B###############B##", "##B###############B##",
                            "##B###############B##", "##B###############B##", "##B###############B##",
                            "##B###############B##", "##BF#############FB##", "##B###############B##",
                            "##B###############B##", "##B###############B##", "##B###############B##",
                            "##B###############B##", "##B###############B##", "##B###############B##",
                            "##B###############B##", "##B###############B##", "##BF#############FB##",
                            "##B###############B##", "##B###############B##", "##B###############B##",
                            "##B###############B##", "##B###############B##", "##B###############B##",
                            "##B###############B##", "##B###############B##", "##B###############B##",
                            "##BFHH####H####HHFB##")
                    .aisle("##BBBBBBBBCBBBBBBBB##", "##CFFFC##CFC##CFFFC##", "###III####G####JJJ###",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "##F###############F##", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "##F###############F##", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "##F###############F##",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "##FH######H######HF##")
                    .aisle("#BBBBBBBBBCBBBBBBBBB#", "##CFFFCCCCFCCCCFFFC##", "###III####G####JJJ###",
                            "####K###########K####", "####H###########H####", "####H###########H####",
                            "####H###########H####", "####H###########H####", "####H###########H####",
                            "#F##H###########H##F#", "####H###########H####", "####H###########H####",
                            "####H###########H####", "####H###########H####", "####H###########H####",
                            "####H###########H####", "####H###########H####", "####H###########H####",
                            "####H###########H####", "#F##H###########H##F#", "####H###########H####",
                            "####H###########H####", "####H###########H####", "####H###########H####",
                            "####H###########H####", "####H###########H####", "####H###########H####",
                            "####H###########H####", "####H###########H####", "#F##H###########H##F#",
                            "####H###########H####", "####H###########H####", "####H###########H####",
                            "####H###########H####", "####H###########H####", "####H###########H####",
                            "####H###########H####", "####H###########H####", "####H###########H####",
                            "#FH#HHHHHHHHHHHHH#HF#")
                    .aisle("#BBBBBBBBBCBBBBBBBBB#", "##CFFFFFFFFFFFFFFFC##", "###IIIGGGGGGGGGJJJ###",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#F#################F#", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#F#################F#", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#F#################F#",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#FH#H#####H#####H#HF#")
                    .aisle("BBBBBBBBBBCBBBBBBBBBB", "##CCCFFFFFFFFFFFCCC##", "#####GLLLLLLLLLG#####",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "F###################F", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "F###################F", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "F###################F",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "FH##H#####H#####H##HF")
                    .aisle("BBBBBBBBBBCBBBBBBBBBB", "####CFFFFFFFFFFFC####", "#####GLLLLLLLLLG#####",
                            "#######LLLLLLL#######", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "F###################F", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "F###################F", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "F###################F",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "FH##H#####H#####H##HF")
                    .aisle("BBBBBBBBBBCBBBBBBBBBB", "####CFFFFFFFFFFFC####", "#####GLLLLLLLLLG#####",
                            "#######LLLLLLL#######", "########LLLLL########", "#####################",
                            "#####################", "#####################", "#####################",
                            "F###################F", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "F###################F", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "F###################F",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "FH##H#####H#####H##HF")
                    .aisle("CBBBBBBBBBCBBBBBBBBBC", "CCCCCFFFFFFFFFFFCCCCC", "C####GLLLLLLLLLG####C",
                            "#######LLLLLLL#######", "########LLLLL########", "#########LLL#########",
                            "#####################", "#####################", "E###################E",
                            "E###################E", "E###################E", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "E###################E", "E###################E", "E###################E",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "E###################E", "E###################E",
                            "E###################E", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "E###################E",
                            "EH##H#####H#####H##HE")
                    .aisle("CCCCCCCCCCCCCCCCCCCCC", "CFFFFFFFFFFFFFFFFFFFC", "CGGGGGLLLLLLLLLGGGGGC",
                            "#######LLLLLLL#######", "########LLLLL########", "#########LLL#########",
                            "##########K##########", "##########H##########", "E#########H#########E",
                            "C#########H#########C", "E#########H#########E", "##########H##########",
                            "##########H##########", "##########H##########", "##########H##########",
                            "##########H##########", "##########H##########", "##########H##########",
                            "E#########H#########E", "C#########H#########C", "E#########H#########E",
                            "##########H##########", "##########H##########", "##########H##########",
                            "##########H##########", "##########H##########", "##########H##########",
                            "##########H##########", "E#########H#########E", "C#########H#########C",
                            "E#########H#########E", "F#########H##########", "F#########H##########",
                            "F#########H##########", "F#########H##########", "F#########H##########",
                            "F#########H##########", "F#########H##########", "E#########H#########E",
                            "CHHHHHHHHHHHHHHHHHHHC")
                    .aisle("CBBBBBBBBBCBBBBBBBBBC", "CCCCCFFFFFFFFFFFCCCCC", "C####GLLLLLLLLLG####C",
                            "#######LLLLLLL#######", "########LLLLL########", "#########LLL#########",
                            "#####################", "#####################", "E###################E",
                            "E###################E", "E###################E", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "E###################E", "E###################E", "E###################E",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "E###################E", "E###################E",
                            "E###################E", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "E###################E",
                            "EH##H#####H#####H##HE")
                    .aisle("BBBBBBBBBBCBBBBBBBBBB", "####CFFFFFFFFFFFC####", "#####GLLLLLLLLLG#####",
                            "#######LLLLLLL#######", "########LLLLL########", "#####################",
                            "#####################", "#####################", "#####################",
                            "F###################F", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "F###################F", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "F###################F",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "FH##H#####H#####H##HF")
                    .aisle("BBBBBBBBBBCBBBBBBBBBB", "####CFFFFFFFFFFFC####", "#####GLLLLLLLLLG#####",
                            "#######LLLLLLL#######", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "F###################F", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "F###################F", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "F###################F",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "FH##H#####H#####H##HF")
                    .aisle("BBBBBBBBBBCBBBBBBBBBB", "##CCCFFFFFFFFFFFCCC##", "#####GLLLLLLLLLG#####",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "F###################F", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "F###################F", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "F###################F",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "FH##H#####H#####H##HF")
                    .aisle("#BBBBBBBBBBBBBBBBBBB#", "##CFFFFFFCBCFFFFFFC##", "###MMMGGGCBCGGGNNN###",
                            "#########CBC#########", "#########CBC#########", "#########COC#########",
                            "#########CBC#########", "#########CBC#########", "#########CBC#########",
                            "#F#######CBC#######F#", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#F#################F#", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#F#################F#",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#FH#H#####H#####H#HF#")
                    .aisle("#BBBBBBBBBCBBBBBBBBB#", "##CFFFCCCCBCCCCFFFC##", "###MMM#########NNN###",
                            "####K###########K####", "####H###########H####", "####H###########H####",
                            "####H###########H####", "####H###########H####", "####H###########H####",
                            "#F##H####CBC####H##F#", "####H###########H####", "####H###########H####",
                            "####H###########H####", "####H###########H####", "####H###########H####",
                            "####H###########H####", "####H###########H####", "####H###########H####",
                            "####H###########H####", "#F##H###########H##F#", "####H###########H####",
                            "####H###########H####", "####H###########H####", "####H###########H####",
                            "####H###########H####", "####H###########H####", "####H###########H####",
                            "####H###########H####", "####H###########H####", "#F##H###########H##F#",
                            "####H###########H####", "####H###########H####", "####H###########H####",
                            "####H###########H####", "####H###########H####", "####H###########H####",
                            "####H###########H####", "####H###########H####", "####H###########H####",
                            "#FH#HHHHHHHHHHHHH#HF#")
                    .aisle("##BBBBBBBBCBBBBBBBB##", "##CFFFC##CBC##CFFFC##", "###MMM#########NNN###",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "##F######CBC######F##", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "##F###############F##", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "##F###############F##",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "##FH######H######HF##")
                    .aisle("##BBBBBBBBCBBBBBBBB##", "##BCCCC##CBC##CCCCB##", "##B###############B##",
                            "##B###############B##", "##B###############B##", "##B###############B##",
                            "##B###############B##", "##B###############B##", "##B###############B##",
                            "##BF#####CBC#####FB##", "##B###############B##", "##B###############B##",
                            "##B###############B##", "##B###############B##", "##B###############B##",
                            "##B###############B##", "##B###############B##", "##B###############B##",
                            "##B###############B##", "##BF#############FB##", "##B###############B##",
                            "##B###############B##", "##B###############B##", "##B###############B##",
                            "##B###############B##", "##B###############B##", "##B###############B##",
                            "##B###############B##", "##B###############B##", "##BF#############FB##",
                            "##B###############B##", "##B###############B##", "##B###############B##",
                            "##B###############B##", "##B###############B##", "##B###############B##",
                            "##B###############B##", "##B###############B##", "##B###############B##",
                            "##BFHH####H####HHFB##")
                    .aisle("####BBBBBBCBBBBBB####", "#########CBC#########", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "####FF###CBC###FF####", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "####FF#########FF####", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "####FF#########FF####",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "####FFHHHHHHHHHFF####")
                    .aisle("######BBBCCCBBB######", "#########C@C#########", "#########CCC#########",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#########EEE#########",
                            "######FFFECEFFF######", "#########EEE#########", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#########EEE#########", "######FFFECEFFF######", "#########EEE#########",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#########EEE#########", "######FFFECEFFF######",
                            "#########EEE#########", "#####################", "#####################",
                            "#####################", "#####################", "#####################",
                            "#####################", "#####################", "#########EEE#########",
                            "######FFFECEFFF#####A")
                    .where("A", Predicates.any())
                    .where("#", Predicates.any())
                    .where("B", CTNHPredicates.SpaceStructuralFrameworkBlock())
                    .where("C", Predicates.blocks(HIGH_POWER_CASING.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.abilities(PartAbility.PARALLEL_HATCH)))
                    .where("D", Predicates.any())
                    .where("E", Predicates.blocks(PV_COIL.get()))
                    .where("F", Predicates.blocks(FUSION_CASING.get()))
                    .where("G", Predicates.blocks(STELLAR_RADIATION_ROUTER_CASING.get()))
                    .where("H", Predicates.blocks(FUSION_GLASS.get()))
                    .where("I", Predicates.blocks(DIAMOND_BLOCK))
                    .where("J", Predicates.blocks(EMERALD_BLOCK))
                    .where("K", Predicates.blocks(BEACON))
                    .where("L", Predicates.blocks(NETHERITE_BLOCK))
                    .where("M", Predicates.blocks(GOLD_BLOCK))
                    .where("N", Predicates.blocks(IRON_BLOCK))
                    .where("O", Predicates.blocks(CTNHMachines.DRONEHOLDER.getBlock()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())

            .workableCasingModel(GTCEu.id("block/casings/hpca/high_power_casing"),
                    GTCEu.id("block/multiblock/generator/large_steam_turbine"))
            .register();
    public static final MultiblockMachineDefinition GAS_CENTRIFUGE = REGISTRATE
            .multiblock("gas_centrifuge", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.GAS_CENTRIFUGE_RECIPES)
            .appearanceBlock(GTBlocks.CASING_STAINLESS_CLEAN)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("#CCC#", "#CCC#", "#####", "#####", "#####", "#####", "#####")
                    .aisle("CBBBC", "CBBBC", "#DED#", "#D#D#", "#D#D#", "#D#D#", "#D#D#")
                    .aisle("CBBBC", "CBBBC", "#EEE#", "#####", "#####", "#####", "#####")
                    .aisle("CBBBC", "CBBBC", "#DED#", "#D#D#", "#D#D#", "#D#D#", "#D#D#")
                    .aisle("#CCC#", "#C@C#", "#####", "#####", "#####", "#####", "#####")
                    .where("B", Predicates.blocks(GTBlocks.CASING_STAINLESS_CLEAN.get()))
                    .where("C", Predicates.blocks(GTBlocks.CASING_STAINLESS_CLEAN.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1)))
                    .where("#", Predicates.air())
                    .where("D", Predicates.blocks(CASING_STEEL_SOLID.get()))
                    .where("E", Predicates.blocks(CASING_STEEL_PIPE.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"),
                    GTCEu.id("block/multiblock/generator/large_steam_turbine"))
            .register();
    public static final MultiblockMachineDefinition HOT_COOLANT_TURBINE = REGISTRATE
            .multiblock("hot_coolant_turbine", holder -> new LargeTurbineMachine(holder, GTValues.EV))
            .generator(true)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.HOT_COOLANT_TURBINE_RECIPES)
            .recipeModifier(LargeTurbineMachine::recipeModifier, true)
            .appearanceBlock(GTBlocks.CASING_TITANIUM_TURBINE)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAA", "ASSA", "AAAA")
                    .aisle("ASSA", "BCCB", "ASSA")
                    .aisle("AAAA", "A@SA", "AAAA")
                    .where("A", Predicates.blocks(GTBlocks.CASING_TITANIUM_TURBINE.get()))
                    .where("B", Predicates.abilities(PartAbility.OUTPUT_ENERGY).setExactLimit(1)
                            .or(Predicates.abilities(PartAbility.ROTOR_HOLDER).setExactLimit(1)))
                    .where("C", Predicates.blocks(GTBlocks.CASING_TITANIUM_GEARBOX.get()))
                    .where("S", Predicates.blocks(GTBlocks.CASING_TITANIUM_TURBINE.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                            .or(Predicates.abilities(PartAbility.MUFFLER).setExactLimit(1)))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/mechanic/machine_casing_turbine_titanium"),
                    GTCEu.id("block/multiblock/generator/large_steam_turbine"))
            .register();
    public static final MultiblockMachineDefinition NUCLEAR_REACTOR = REGISTRATE
            .multiblock("nuclear_reactor", NuclearReactorMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(CTNHRecipeTypes.NUCLEAR_REACTOR_RECIPES)
            .tooltips(Component.translatable("nuclear_reactor").withStyle(ChatFormatting.GRAY),
                    Component.translatable("ctnh.nuclear_reactor.basic"),
                    Component.translatable("ctnh.nuclear_reactor.coolant"),
                    Component.translatable("ctnh.nuclear_reactor.overclock"),
                    Component.translatable("ctnh.nuclear_reactor.safe"))
            .recipeModifier(NuclearReactorMachine::recipeModifier)
            .appearanceBlock(CASING_SHIELDED_REACTOR)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("DDD", "ABA", "ABA", "ABA", "ABA", "ABA", "ABA", "ABA", "DDD")
                    .aisle("DDD", "BCB", "BCB", "BCB", "BCB", "BCB", "BCB", "BCB", "DDD")
                    .aisle("D@D", "ABA", "ABA", "ABA", "ABA", "ABA", "ABA", "ABA", "DDD")
                    .where("A", Predicates.blocks(CASING_SHIELDED_REACTOR.get()))
                    .where("B", Predicates.blocks(CASING_TEMPERED_GLASS.get()))
                    .where("C", CTNHPredicates.reactorCore())
                    .where("D", Predicates.blocks(CASING_SHIELDED_REACTOR.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes())))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel(CTNHCore.id("block/casings/shielded_reactor_casing"),
                    GTCEu.id("block/machines/nuclear_reactor"))
            .register();
    public final static MultiblockMachineDefinition CRYOTHEUMFREEZER = REGISTRATE
            .multiblock("cryotheum_freezer", CryotheumFreezer::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTRecipeTypes.VACUUM_RECIPES)
            .recipeModifiers(CryotheumFreezer::recipeModifier,
                    GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK_SUBTICK))
            .tooltips(Component.translatable("ctnh.multiblock.cryotheum_freezer.tip.0"),
                    Component.translatable("ctnh.multiblock.cryotheum_freezer.tip.1"),
                    Component.translatable("ctnh.multiblock.cryotheum_freezer.tip.2"))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("A###BBBBB####", "####BCCCB####", "####BCCCB####", "####BCCCB####", "####BCCCB####",
                            "####BCCCB####", "####BBBBB####")
                    .aisle("#BBBBBDBBBBB#", "#DDDBEDEBDDD#", "#BBBBEDEBBBB#", "#DDDBEFEBDDD#", "#BBBBEDEBBBB#",
                            "#DDDBEDEBDDD#", "#BBBBBEBBBBB#")
                    .aisle("#B##BBBBB##B#", "#D##BCCCB##D#", "#B##BCCCB##B#", "#D##BCFCB##D#", "#B##BCCCB##B#",
                            "#D##BCCCB##D#", "#B##BBBBB##B#")
                    .aisle("#B#########B#", "#D#########D#", "#B###CCC###B#", "#D###CFC###D#", "#B###CCC###B#",
                            "#D#########D#", "#B#########B#")
                    .aisle("BBB#######BBB", "BBB##BBB##BBB", "BBB#BEEEB#BBB", "BBB#BEFEB#BBB", "BBB#BEEEB#BBB",
                            "BBB##BBB##BBB", "BBB#######BBB")
                    .aisle("BBB##BBB##BBB", "CEC#BEEEB#CEC", "CECCEBBBECCEC", "CECCEBGBECCEC", "CECCEBBBECCEC",
                            "CEC#BEEEB#CEC", "BBB##BBB##BBB")
                    .aisle("BDB##BDB##BDB", "CDC#BEDEB#CDC", "CDCCEBDBECCDC", "CFFFFGDGFFFFC", "CDCCEBDBECCDC",
                            "CDC#BEDEB#CDC", "BEB##BDB##BEB")
                    .aisle("BBB##BBB##BBB", "CEC#BEEEB#CEC", "CECCEEGEECCEC", "CECCEGDGECCEC", "CECCEEGEECCEC",
                            "CEC#BEEEB#CEC", "BBB##BBB##BBB")
                    .aisle("BBB#######BBB", "BBB##BBB##BBB", "BBB#BDDDB#BBB", "BBB#BDDDB#BBB", "BBB#BDDDB#BBB",
                            "BBB##BBB##BBB", "BBB#######BBB")
                    .aisle("#############", "#############", "#####BBB#####", "#####BHB#####", "#####BBB#####",
                            "#############", "############A")
                    .where("A", Predicates.any())
                    .where("#", Predicates.any())
                    .where("B",
                            Predicates.blocks(SUPER_FREEZE_BLOCK.get())
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes())))
                    .where("C", Predicates.blocks(CASING_LAMINATED_GLASS.get()))
                    .where("D", Predicates.blocks(SUPERCOOLED_BLOCK.get()))
                    .where("E", Predicates.blocks(MOLYBDENUM_DISILICIDE_COIL_BLOCK.get()))
                    .where("F", Predicates.blocks(CASING_POLYBENZIMIDAZOLE_PIPE.get()))
                    .where("G", Predicates.blocks(HIGH_SPEED_PIPE_BLOCK.get()))
                    .where("H", Predicates.controller(Predicates.blocks(definition.get())))

                    .build())
            .appearanceBlock(SUPER_FREEZE_BLOCK)
            .workableCasingModel(CTNHCore.id("block/casings/super_machine_casing_frost_proof"),
                    GTCEu.id("block/multiblock/vacuum_freezer"))
            .register();

    public final static MultiblockMachineDefinition NERUOMATRIXCOMPILER = REGISTRATE.multiblock("neruo_martix_compiler",
            NeuroMatrixCompiler::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.COMPILER_RECIPE)
            .appearanceBlock(CASING_ANTIFREEZE_HEATPROOF_MACHINE)
            .recipeModifiers(NeuroMatrixCompiler::recipeModifier)
            .tooltips(Component.translatable("ctnh.multiblock.neuro_martix_compiler.tip.0"),
                    Component.translatable("ctnh.multiblock.neuro_martix_compiler.tip.01"),
                    Component.translatable("ctnh.multiblock.neuro_martix_compiler.tip.1"),
                    Component.translatable("ctnh.multiblock.neuro_martix_compiler.tip.2"),
                    Component.translatable("ctnh.multiblock.neuro_martix_compiler.tip.3"),
                    Component.translatable("ctnh.multiblock.neuro_martix_compiler.tip.4"),
                    Component.translatable("ctnh.multiblock.neuro_martix_compiler.tip.5"),
                    Component.translatable("ctnh.multiblock.neuro_martix_compiler.tip.6"),
                    Component.translatable("ctnh.multiblock.neuro_martix_compiler.tip.part1"),
                    Component.translatable("ctnh.multiblock.neuro_martix_compiler.tip.part2"),
                    Component.translatable("ctnh.multiblock.neuro_martix_compiler.tip.part3"),
                    Component.translatable("ctnh.multiblock.neuro_martix_compiler.tip.part4"),
                    Component.translatable("ctnh.multiblock.neuro_martix_compiler.tip.7"),
                    Component.translatable("ctnh.multiblock.neuro_martix_compiler.tip.8"),
                    Component.translatable("ctnh.multiblock.neuro_martix_compiler.tip.9"),
                    Component.translatable("ctnh.multiblock.neuro_martix_compiler.tip.10"),
                    Component.translatable("ctnh.multiblock.neuro_martix_compiler.tip.11"),

                    Component.translatable("ctnh.multiblock.neuro_martix_compiler.tip.12"))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("A############BBBBB#############", "#############CCDCC#############",
                            "#############B#B#B#############", "###############################",
                            "###############################", "###############################",
                            "##############B#B##############", "#############CCCCC#############",
                            "#############BBBBB#############")
                    .aisle("#############BBBBB#############", "#############CEFEC#############",
                            "##############EFE##############", "##############EFE##############",
                            "##############EFE##############", "##############EFE##############",
                            "#############BEFEB#############", "#############CEFEC#############",
                            "#############BBBBB#############")
                    .aisle("#############BBBBB#############", "#############CFGFC#############",
                            "#############BFGFB#############", "##############FGF##############",
                            "##############FGF##############", "##############FGF##############",
                            "##############FGF##############", "#############CFGFC#############",
                            "#############BBBBB#############")
                    .aisle("#############BBBBB#############", "#############CEHEC#############",
                            "##############ECE##############", "##############ECE##############",
                            "##############ECE##############", "##############ECE##############",
                            "#############BECEB#############", "#############CECEC#############",
                            "#############BBBBB#############")
                    .aisle("#############BBBBB#############", "#############CCHCC#############",
                            "#############BBCBB#############", "###############################",
                            "###############################", "###############################",
                            "###############################", "#############CCCCC#############",
                            "#############BBBBB#############")
                    .aisle("##############III##############", "##############FHF##############",
                            "##############BCB##############", "###############################",
                            "###############################", "###############################",
                            "###############################", "###############################",
                            "###############################")
                    .aisle("##############III##############", "##############FHF##############",
                            "##############BCB##############", "###############################",
                            "###############################", "###############################",
                            "###############################", "###############################",
                            "###############################")
                    .aisle("##############III##############", "##############FHF##############",
                            "##############BCB##############", "###############################",
                            "###############################", "###############################",
                            "###############################", "###############################",
                            "###############################")
                    .aisle("#############IIIII#############", "#############IIHII#############",
                            "#############IIIII#############", "#############FIIIF#############",
                            "#############FFIFF#############", "#############FFIFF#############",
                            "#############FIIIF#############", "#############IIIII#############",
                            "#############IIIII#############")
                    .aisle("###########IIJJIJJII###########", "###########II##I##II###########",
                            "###########FF##I##FF###########", "###########FF##I##FF###########",
                            "###########FF##F##FF###########", "###########FF##F##FF###########",
                            "###########FF##I##FF###########", "###########FF##I##FF###########",
                            "###########IIKKIKKII###########")
                    .aisle("##########IJJJJIJJJJI##########", "##########I####I####I##########",
                            "##########I####I####I##########", "##########I####F####I##########",
                            "##########I####F####I##########", "##########I####F####I##########",
                            "##########I####F####I##########", "##########I####I####I##########",
                            "##########IKKKKIKKKKI##########")
                    .aisle("#########IJJJJJIJJJJJI#########", "#########I#####I#####I#########",
                            "#########F#####F#####F#########", "#########F#####F#####F#########",
                            "#########F#####F#####F#########", "#########F#####F#####F#########",
                            "#########F#####F#####F#########", "#########F#####F#####F#########",
                            "#########IKKKKKIKKKKKI#########")
                    .aisle("#########IJJJJJIJJJJJI#########", "#########I#####I#####I#########",
                            "#########F#####F#####F#########", "#########F#####F#####F#########",
                            "#########F#####F#####F#########", "#########F#####F#####F#########",
                            "#########F#####F#####F#########", "#########F#####F#####F#########",
                            "#########IKKKKKIKKKKKI#########")
                    .aisle("BBBBB###IJJJJJJIJJJJJJI###BBBBB", "CCCCC###I######I######I###CCCCC",
                            "B#B#B###I######I######I###B#B#B", "########F######F######F########",
                            "########F######F######F########", "########F######F######F########",
                            "#B#B####F######F######F####B#B#", "CCCCC###I######I######I###CCCCC",
                            "BBBBB###IKKKKKKIKKKKKKI###BBBBB")
                    .aisle("BBBBBIIIIJJJJJJIJJJJJJIIIIBBBBB", "CEFECFFFI######I######IFFFCEFEC",
                            "#EFEBBBBI######I######IBBBBEFE#", "#EFE####I######I######I####EFE#",
                            "#EFE####F######F######F####EFE#", "#EFE####F######F######F####EFE#",
                            "BEFE####I######I######I####EFEB", "CEFEC###I######I######I###CEFEC",
                            "BBBBB###IKKKKKKIKKKKKKI###BBBBB")
                    .aisle("BBBBBIIIIIIIIIIIIIIIIIIIIIBBBBB", "DFGHHHHHHIIIIIIIIIIIIIHHHHHHGCD",
                            "BFGCCCCCIIIFFIIIIIFFIIICCCCCGCB", "#FGC####IIFFFFIIIFFFFII####CGC#",
                            "#FGC####IFFFFFFIFFFFFFI####CGC#", "#FGC####IFFFFFFIFFFFFFI####CGC#",
                            "#FGC####IIFFFFIIIFFFFII####CGC#", "CFGCC###IIIFFIIIIIFFIII###CCGCC",
                            "BBBBB###IIIIIIILIIIIIII###BBBBB")
                    .aisle("BBBBBIIIIJJJJJJIJJJJJJIIIIBBBBB", "CEFECFFFI######I######IFFFCEFEC",
                            "#EFEBBBBI######I######IBBBBEFE#", "#EFE####I######I######I####EFE#",
                            "#EFE####F######F######F####EFE#", "#EFE####F######F######F####EFE#",
                            "BEFE####I######I######I####EFEB", "CEFEC###I######I######I###CEFEC",
                            "BBBBB###IKKKKKKIKKKKKKI###BBBBB")
                    .aisle("BBBBB###IJJJJJJIJJJJJJI###BBBBB", "CCCCC###I######I######I###CCCCC",
                            "B#B#B###I######I######I###B#B#B", "########F######F######F########",
                            "########F######F######F########", "########F######F######F########",
                            "#B#B####F######F######F####B#B#", "CCCCC###I######I######I###CCCCC",
                            "BBBBB###IKKKKKKIKKKKKKI###BBBBB")
                    .aisle("#########IJJJJJIJJJJJI#########", "#########I#####I#####I#########",
                            "#########F#####F#####F#########", "#########F#####F#####F#########",
                            "#########F#####F#####F#########", "#########F#####F#####F#########",
                            "#########F#####F#####F#########", "#########F#####F#####F#########",
                            "#########IKKKKKIKKKKKI#########")
                    .aisle("#########IJJJJJIJJJJJI#########", "#########I#####I#####I#########",
                            "#########F#####F#####F#########", "#########F#####F#####F#########",
                            "#########F#####F#####F#########", "#########F#####F#####F#########",
                            "#########F#####F#####F#########", "#########F#####F#####F#########",
                            "#########IKKKKKIKKKKKI#########")
                    .aisle("#########IIJJJJIJJJJII#########", "#########FH####I####HF#########",
                            "#########BI####I####IB#########", "##########I####F####I##########",
                            "##########I####F####I##########", "##########I####F####I##########",
                            "##########I####F####I##########", "##########I####I####I##########",
                            "##########IKKKKIKKKKI##########")
                    .aisle("########IIIIIJJIJJIIIII########", "########FHFII##I##IIFHF########",
                            "########BCBFF##I##FFBCB########", "###########FF##I##FF###########",
                            "###########FF##F##FF###########", "###########FF##F##FF###########",
                            "###########FF##I##FF###########", "###########FF##I##FF###########",
                            "###########IIKKIKKII###########")
                    .aisle("#######III###IIIII###III#######", "#######FHF###II@II###FHF#######",
                            "#######BCB###IIDII###BCB#######", "#############FIIIF#############",
                            "#############FFIFF#############", "#############FFIFF#############",
                            "#############FIIIF#############", "#############IIIII#############",
                            "#############IIIII#############")
                    .aisle("######III#############III######", "######FHF#############FHF######",
                            "######BCB#############BCB######", "###############################",
                            "###############################", "###############################",
                            "###############################", "###############################",
                            "###############################")
                    .aisle("##BBBBBI###############IBBBBB##", "##CCCCHF###############FCCCCC##",
                            "##B#BBCB###############BCBB#B##", "###############################",
                            "###############################", "###############################",
                            "###B#######################B###", "##CCCCC#################CCCCC##",
                            "##BBBBB#################BBBBB##")
                    .aisle("##BBBBB#################BBBBB##", "##CEFEC#################CEFEC##",
                            "###EFEB#################BEFE###", "###EFC###################CFE###",
                            "###EFC###################CFE###", "###EFC###################CFE###",
                            "##BEFC###################CFEB##", "##CEFCC#################CCFEC##",
                            "##BBBBB#################BBBBB##")
                    .aisle("##BBBBB#################BBBBB##", "##CFGFC#################CFGCC##",
                            "##BFGFB#################BFGCB##", "###FGF###################FGC###",
                            "###FGF###################FGC###", "###FGF###################FG####",
                            "###FGF###################FG####", "##CFGFC#################CFGCC##",
                            "##BBBBB#################BBBBB##")
                    .aisle("##BBBBB#################BBBBB##", "##CEFEC#################CEFEC##",
                            "###EFE###################EFE###", "###EFE###################EFE###",
                            "###EFE###################EFE###", "###EFE###################EFE###",
                            "##BEFEB#################BEFEB##", "##CEFEC#################CEFEC##",
                            "##BBBBB#################BBBBB##")
                    .aisle("##BBBBB#################BBBBB##", "##CCDCC#################CCDCC##",
                            "##B#B#B#################B#B#B##", "###############################",
                            "###############################", "###############################",
                            "###B#B###################B#B###", "##CCCCC#################CCCCC##",
                            "##BBBBB#################BBBBB#A")
                    .where("A", Predicates.any())
                    .where("#", Predicates.any())
                    .where("B", Predicates.blocks(ADVANCED_BIO_REACTOR_CASING.get()))
                    .where("C", Predicates.blocks(FUSION_CASING.get()))
                    .where("D", Predicates.abilities(CTNHPartAbility.COMPILER))
                    .where("E", Predicates.blocks(ADVANCED_COMPUTER_CASING.get()))
                    .where("F", Predicates.blocks(FUSION_CASING.get()))
                    .where("G", Predicates.blocks(HIGH_POWER_CASING.get()))
                    .where("H", Predicates.blocks(WIDESPEEDINGPIPE.get()))
                    .where("I", Predicates.blocks(CASING_ANTIFREEZE_HEATPROOF_MACHINE.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes())))
                    .where("J", Predicates.blocks(CASING_PTFE_INERT.get()))
                    .where("K", Predicates.frames(Naquadria))
                    .where("L", Predicates.blocks(CASING_NEUTRONIUM_ALLOY_BLOCK.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel(CTNHCore.id("block/casings/antifreeze_heatproof_machine_casing"),
                    GTCEu.id("block/multiblock/vacuum_freezer"))
            .register();

    public static final MultiblockMachineDefinition[] FLUID_DRILLING_INF = CTNHMachineUtils.registerTieredMultis(
            "fluid_drilling_inf", INFFluidDrillMachine::new, (tier, builder) -> builder
                    .rotationState(RotationState.ALL)
                    .langValue("%s Fluid Drilling Rig %s".formatted(VLVH[tier], VLVT[tier]))
                    .recipeType(DUMMY_RECIPES)
                    .tooltips(
                            Component.translatable("ctnh.multiblock.fluid_drilling_rig.description.inf"),
                            Component.translatable("ctnh.multiblock.fluid_drilling_rig.depletion.inf"),
                            Component.translatable("gtceu.universal.tooltip.energy_tier_range", GTValues.VNF[tier],
                                    GTValues.VNF[tier + 1]),
                            Component.translatable("gtceu.machine.fluid_drilling_rig.production",
                                    INFFluidDrillMachine.getRigMultiplier(tier),
                                    FormattingUtil.formatNumbers(INFFluidDrillMachine.getRigMultiplier(tier) * 2)))
                    .appearanceBlock(() -> INFFluidDrillMachine.getCasingState(tier))
                    .pattern((definition) -> FactoryBlockPattern.start()
                            .aisle("XXX", "#F#", "#F#", "#F#", "###", "###", "###")
                            .aisle("XXX", "FCF", "FCF", "FCF", "#F#", "#F#", "#F#")
                            .aisle("XSX", "#F#", "#F#", "#F#", "###", "###", "###")
                            .where('S', controller(blocks(definition.get())))
                            .where('X', blocks(INFFluidDrillMachine.getCasingState(tier)).setMinGlobalLimited(3)
                                    .or(abilities(PartAbility.INPUT_ENERGY).setMinGlobalLimited(1)
                                            .setMaxGlobalLimited(2))
                                    .or(abilities(PartAbility.EXPORT_FLUIDS).setMaxGlobalLimited(1)))
                            .where('C', blocks(INFFluidDrillMachine.getCasingState(tier)))
                            .where('F', blocks(INFFluidDrillMachine.getFrameState(tier)))
                            .where('#', any())
                            .build())
                    .workableCasingModel(INFFluidDrillMachine.getBaseTexture(tier),
                            GTCEu.id("block/multiblock/fluid_drilling_rig"))
                    .register(),
            UHV);
    public static final MultiblockMachineDefinition INF_LARGE_MINER = REGISTRATE
            .multiblock("inf_large_miner", holder -> new LargeMinerMachine(holder, GTValues.UHV, 1, 99, 7, 9))
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTRecipeTypes.MACERATOR_RECIPES)
            .tooltips(
                    Component.translatable("ctnh.multiblock.large_miner_zpm.tooltip.0"),
                    Component.translatable("gtceu.machine.miner.multi.description"))
            .tooltipBuilder((stack, tooltip) -> {
                int workingAreaChunks = 99;
                tooltip.add(Component.translatable("gtceu.machine.miner.multi.modes"));
                tooltip.add(Component.translatable("gtceu.machine.miner.multi.production"));
                tooltip.add(Component.translatable("gtceu.machine.miner.fluid_usage", 9,
                        DrillingFluid.getLocalizedName()));
                tooltip.add(Component.translatable("gtceu.universal.tooltip.working_area_chunks",
                        workingAreaChunks, workingAreaChunks));
                tooltip.add(Component.translatable("gtceu.universal.tooltip.energy_tier_range",
                        GTValues.VNF[UHV], GTValues.VNF[UHV + 1]));
            })
            .pattern((definition) -> FactoryBlockPattern.start()
                    .aisle("XXX", "#F#", "#F#", "#F#", "###", "###", "###")
                    .aisle("XXX", "FCF", "FCF", "FCF", "#F#", "#F#", "#F#")
                    .aisle("XSX", "#F#", "#F#", "#F#", "###", "###", "###")
                    .where("S", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("X", Predicates.blocks(CTNHBlocks.CASING_NEUTRONIUM_ALLOY_BLOCK.get())
                            .or(abilities(PartAbility.EXPORT_ITEMS).setMaxGlobalLimited(1).setPreviewCount(1))
                            .or(abilities(PartAbility.IMPORT_FLUIDS).setExactLimit(1).setPreviewCount(1))
                            .or(abilities(PartAbility.INPUT_ENERGY).setMinGlobalLimited(1)
                                    .setMaxGlobalLimited(2).setPreviewCount(1)))
                    .where("C", Predicates.blocks(CTNHBlocks.CASING_NEUTRONIUM_ALLOY_BLOCK.get()))
                    .where("F", Predicates.frames(Neutronium))
                    .where("#", Predicates.any())
                    .build())
            .workableCasingModel(CTNHCore.id("block/casings/nq_neutronium_casing"),
                    GTCEu.id("block/multiblock/large_miner"))
            .register();

    public static void init() {}
}
