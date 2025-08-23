package io.github.cpearl0.ctnhcore.registry.machines.multiblock;

import com.google.common.primitives.Ints;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.pattern.util.RelativeDirection;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.common.data.GCYMBlocks;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.common.data.models.GTMachineModels;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.api.Pattern.CTNHBlockMaps;
import io.github.cpearl0.ctnhcore.api.Pattern.CTNHPredicates;
import io.github.cpearl0.ctnhcore.client.renderer.DynamicCasingRender;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.ChemicalPlantMachine;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.NeutronActivatorMachine;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.generator.LargeNaquadahReactorMachine;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.part.CTNHPartAbility;
import io.github.cpearl0.ctnhcore.registry.*;
import io.github.cpearl0.ctnhcore.utils.StructureUtils;
import net.minecraft.network.chat.Component;

import java.util.Comparator;

import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class GTNNMultiblocks {
    public static void init() {}
    public static final MultiblockMachineDefinition CHEMICAL_PLANT = REGISTRATE.multiblock(
            "exxonmobil_chemical_plant",
            ChemicalPlantMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
        .tooltips(Component.translatable("ctnh.multiblock.chemical_plant.tooltip.0"))
            .tooltips(Component.translatable("ctnh.multiblock.chemical_plant.tooltip.1"))
            .tooltips(Component.translatable("ctnh.multiblock.chemical_plant.tooltip.2"))
            .tooltips(Component.translatable("ctnh.multiblock.chemical_plant.tooltip.3"))
            .recipeTypes(CTNHRecipeTypes.CHEMICAL_PLANT_RECIPES)
        .recipeModifiers(CTNHRecipeModifiers::chemicalPlantOverclock)
        .appearanceBlock(GTBlocks.CASING_BRONZE_BRICKS)
        .pattern(definition -> FactoryBlockPattern.start()
            .aisle("VVVVVVV", "A#####A", "A#####A", "A#####A", "A#####A", "A#####A", "AAAAAAA")
            .aisle("VBBBBBV", "#BBBBB#", "#######", "#######", "#######", "#BBBBB#", "AAAAAAA")
            .aisle("VBBBBBV", "#BCCCB#", "##DDD##", "##CCC##", "##DDD##", "#BCCCB#", "AAAAAAA")
            .aisle("VBBBBBV", "#BCCCB#", "##DDD##", "##CCC##", "##DDD##", "#BCCCB#", "AAAAAAA")
            .aisle("VBBBBBV", "#BCCCB#", "##DDD##", "##CCC##", "##DDD##", "#BCCCB#", "AAAAAAA")
            .aisle("VBBBBBV", "#BBBBB#", "#######", "#######", "#######", "#BBBBB#", "AAAAAAA")
            .aisle("VVVSVVV", "A#####A", "A#####A", "A#####A", "A#####A", "A#####A", "AAAAAAA")
            .where("S", controller(blocks(definition.getBlock())))
            .where("V", CTNHPredicates.plantCasings
                            .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                            .or(abilities(PartAbility.EXPORT_FLUIDS).setMinGlobalLimited(1))
                            .or(abilities(PartAbility.EXPORT_ITEMS).setMinGlobalLimited(1))
                            .or(abilities(PartAbility.IMPORT_ITEMS).setMinGlobalLimited(1))
                            .or(abilities(PartAbility.IMPORT_FLUIDS).setMinGlobalLimited(1))
                            .or(abilities(CTNHPartAbility.CATALYST).setMaxGlobalLimited(2))
                            .or(abilities(PartAbility.INPUT_ENERGY)
                                    .setMinGlobalLimited(1).setMaxGlobalLimited(2)))
            .where("A", CTNHPredicates.plantCasings)
            .where("D", CTNHPredicates.pipeBlock)
            .where("C", CTNHPredicates.coilBlock)
            .where("B", CTNHPredicates.machineCasing)
            .where("#", Predicates.any())
            .build())
//            .shapeInfos(definition ->{
//                int maxSize = Ints.max(
//                CTNHBlockMaps.CasingBlock.size(),
//                CTNHBlockMaps.PipeBlock.size(),
//                CTNHBlockMaps.MachineCasingBlock.size(),
//                CTNHBlockMaps.CoilBlock.size()
//                );
//                return StructureUtils.getMatchingShapes(
//                        definition.getPatternFactory().get(),
//                        maxSize
//                );
//            })
            .partSorter(Comparator.comparingInt(a -> a.self().getPos().getY()))
            .model(GTMachineModels.createWorkableCasingMachineModel(
            GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),
            CTNHCore.id("block/multiblock/chemical_plant")).andThen(b ->
                b.addDynamicRenderer(() -> new DynamicCasingRender(
                        GTBlocks.CASING_BRONZE_BRICKS.getDefaultState(), DynamicCasingRender.ModelType.ChemicalPlant) )
            ))
            .register();

    public static final MultiblockMachineDefinition NEUTRON_ACTIVATOR = REGISTRATE.multiblock("neutron_activator", NeutronActivatorMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeTypes(CTNHRecipeTypes.NEUTRON_ACTIVATOR_RECIPES)
            .tooltips(Component.translatable("ctnh.multiblock.neutron_activator.tooltip.0"))
            .tooltips(Component.translatable("ctnh.multiblock.neutron_activator.tooltip.1"))
            .tooltips(Component.translatable("ctnh.multiblock.neutron_activator.tooltip.2"))
            .tooltips(Component.translatable("ctnh.multiblock.neutron_activator.tooltip.3"))
            .tooltips(Component.translatable("ctnh.multiblock.neutron_activator.tooltip.4"))
            .appearanceBlock(GTBlocks.CASING_STAINLESS_CLEAN)
            .pattern(definition ->
            FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.UP)
                    .aisle("AASAA", "ABBBA", "ABBBA", "ABBBA", "AAAAA")
                    .aisle("C###C", "#DDD#", "#DED#", "#DDD#", "C###C").setRepeatable(4, 34)
                    .aisle("VVVVV", "VBBBV", "VBBBV", "VBBBV", "VVVVV")
                    .where("S", controller(blocks(definition.get())))
                    .where(
                            "V",
                            blocks(GTBlocks.CASING_STAINLESS_CLEAN.get()).or(abilities(PartAbility.IMPORT_FLUIDS))
                                    .or(abilities(PartAbility.IMPORT_ITEMS))
                    ).where(
                            "A",
                            blocks(GTBlocks.CASING_STAINLESS_CLEAN.get()).or(abilities(PartAbility.EXPORT_FLUIDS))
                                .or(abilities(PartAbility.EXPORT_ITEMS)).or(abilities(CTNHPartAbility.NEUTRON_ACCELERATOR))
                                    .or(abilities(CTNHPartAbility.NEUTRON_SENSOR)).or(autoAbilities(true, false, false))
                    ).where("B", blocks(CTNHBlocks.PROCESS_MACHINE_CASING.get()))
                    .where("C", blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, GTMaterials.Steel)))
                    .where("D", blocks(GTBlocks.CASING_LAMINATED_GLASS.get()))
                    .where("E", blocks(CTNHMachines.HIGH_SPEED_PIPE_BLOCK.get().self()))
                    .where("#", any()).build()

            )
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"),
                CTNHCore.id("block/multiblock/neutron_activator"))
            .register();

    public static final MultiblockMachineDefinition LARGE_DEHYDRATOR = REGISTRATE.multiblock("large_dehydrator", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeTypes(CTNHRecipeTypes.DEHYDRATOR_RECIPES)
            .recipeModifiers(
            GTRecipeModifiers.DEFAULT_ENVIRONMENT_REQUIREMENT,
            GTRecipeModifiers.PARALLEL_HATCH,
            GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK)
            )
            .appearanceBlock(GCYMBlocks.CASING_HIGH_TEMPERATURE_SMELTING)
            .pattern(definition ->
                FactoryBlockPattern.start()
                    .aisle("XXX", "CCC", "CCC", "CCC", "XXX")
                    .aisle("XXX", "C#C", "C#C", "C#C", "XXX")
                    .aisle("XSX", "CCC", "CCC", "CCC", "XXX")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where(
                            'X', blocks(GCYMBlocks.CASING_HIGH_TEMPERATURE_SMELTING.get()).setMinGlobalLimited(9)
                                    .or(autoAbilities(definition.getRecipeTypes()))
                    .or(autoAbilities(true, false, true))
                    )
                    .where('C', blocks(GTBlocks.COIL_NAQUADAH.get()))
                    .where('#', air())
                    .build())
            .workableCasingModel(
                GTCEu.id("block/casings/gcym/high_temperature_smelting_casing"),
                GTCEu.id("block/multiblock/gcym/large_assembler")
            )
            .register();


    public static final MultiblockMachineDefinition LARGE_NAQUADAH_REACTOR = REGISTRATE.multiblock("large_naquadah_reactor", LargeNaquadahReactorMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeTypes(CTNHRecipeTypes.LARGE_NAQUADAH_REACTOR_RECIPES)
            .tooltips(Component.translatable("ctnh.multiblock.large_naquadah_reactor.tooltip.0"))
            .tooltips(Component.translatable("ctnh.multiblock.large_naquadah_reactor.tooltip.1"))
            .tooltips(Component.translatable("ctnh.multiblock.large_naquadah_reactor.tooltip.2"))
            .tooltips(Component.translatable("ctnh.multiblock.large_naquadah_reactor.tooltip.3"))
            .tooltips(Component.translatable("ctnh.multiblock.large_naquadah_reactor.tooltip.4"))
            .tooltips(Component.translatable("ctnh.multiblock.large_naquadah_reactor.tooltip.5"))
            .tooltips(Component.translatable("ctnh.multiblock.large_naquadah_reactor.tooltip.6"))
            .tooltips(Component.translatable("ctnh.multiblock.large_naquadah_reactor.tooltip.7"))
            .tooltips(Component.translatable("ctnh.multiblock.large_naquadah_reactor.tooltip.8"))
            .recipeModifier(LargeNaquadahReactorMachine::modifyRecipe)
            .appearanceBlock(CTNHBlocks.RADIATION_PROOF_MACHINE_CASING).pattern(definition ->
                FactoryBlockPattern.start()
                    .aisle("AAAAAAA", "VBBBBBV", "VVVVVVV", "B#####B", "B#####B", "B#####B", "B#####B", "VVVVVVV")
                    .aisle("AAAAAAA", "B#####B", "V#####V", "#######", "#######", "#######", "#######", "VVVVVVV")
                    .aisle("AAAAAAA", "B#CCC#B", "V#CCC#V", "##CCC##", "##CCC##", "##CCC##", "##CCC##", "VVVVVVV")
                    .aisle("AAAAAAA", "B#CCC#B", "V#CDC#V", "##CDC##", "##CDC##", "##CDC##", "##CDC##", "VVVVVVV")
                    .aisle("AAAAAAA", "B#CCC#B", "V#CCC#V", "##CCC##", "##CCC##", "##CCC##", "##CCC##", "VVVVVVV")
                    .aisle("AAAAAAA", "B#####B", "V#####V", "#######", "#######", "#######", "#######", "VVVVVVV")
                    .aisle("AAASAAA", "VBBBBBV", "VVVVVVV", "B#####B", "B#####B", "B#####B", "B#####B", "VVVVVVV")
                    .where("S", controller(blocks(definition.get())))
                    .where("V", blocks(CTNHBlocks.RADIATION_PROOF_MACHINE_CASING.get())).where(
                            "A", blocks(CTNHBlocks.RADIATION_PROOF_MACHINE_CASING.get()).or(
                                            autoAbilities(
                                                    true, false, false
                                            )
                                    ).or(abilities(PartAbility.OUTPUT_ENERGY, PartAbility.OUTPUT_LASER).setMinGlobalLimited(1, 1).setMaxGlobalLimited(6))
                                    .or(abilities(PartAbility.IMPORT_FLUIDS).setPreviewCount(1))
                                    .or(abilities(PartAbility.EXPORT_FLUIDS).setPreviewCount(1))
                    ).where("B", blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, CTNHMaterials.RadiationProtection)))
                    .where("C", blocks(CTNHBlocks.MAR_CASING.get()))
                    .where("D", blocks(GTBlocks.CASING_TUNGSTENSTEEL_PIPE.get())).where("#", air())
                    .build())
            .workableCasingModel(
            CTNHCore.id("block/casings/solid/radiation_proof_machine_casing"),
                CTNHCore.id("block/multiblock/large_naquadah_reactor"))
            .additionalDisplay((controller, components) -> {
                if (controller instanceof LargeNaquadahReactorMachine largeNaquadahReactorMachine && controller.isFormed()) {
                    components.add(
                            Component.translatable(
                                    "ctnh.multiblock.large_naquadah_reactor.info.power", largeNaquadahReactorMachine.getFinalPowerRate()
                            )
                    );
                }
            })
            .register();
}
