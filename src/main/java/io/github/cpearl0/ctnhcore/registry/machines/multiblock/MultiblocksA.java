package io.github.cpearl0.ctnhcore.registry.machines.multiblock;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.client.renderer.MartialMoralityEyeRender;
import io.github.cpearl0.ctnhcore.common.block.CTNHFusionCasingType;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.LargeBottleMachine;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.SlaughterHouseMachine;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.UnderfloorHeatingMachine;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.*;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.generator.ChemicalGeneratorMachine;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.generator.NaqReactorMachine;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.generator.PhotovoltaicPowerStationMachine;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.kinetic.IndustrialPrimitiveBlastFurnaceMachine;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.kinetic.MeadowMachine;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.part.CTNHPartAbility;
import io.github.cpearl0.ctnhcore.integration.legendary.UnderfloorHeatingSystemTempModifier;
import io.github.cpearl0.ctnhcore.registry.CTNHBlocks;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeModifiers;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;
import io.github.cpearl0.ctnhcore.utils.CTNHCommonTooltips;
import io.github.cpearl0.ctnhcore.utils.CTNHMachineUtils;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.RecipeElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.pattern.TraceabilityPredicate;
import com.gregtechceu.gtceu.api.pattern.util.RelativeDirection;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.FusionReactorMachine;
import com.gregtechceu.gtceu.utils.FormattingUtil;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;

import com.enderio.base.common.init.EIOBlocks;
import com.mo_guang.ctpp.api.CTPPPartAbility;
import com.mo_guang.ctpp.api.pattern.FactoryStaticBlockPattern;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.block.CopperBlockSet;
import com.tterrag.registrate.util.entry.BlockEntry;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.abilities;
import static com.gregtechceu.gtceu.common.data.GCYMBlocks.HEAT_VENT;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTMaterialBlocks.MATERIAL_BLOCKS;
import static com.gregtechceu.gtceu.common.data.GTMaterialItems.MATERIAL_ITEMS;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeModifiers.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.DUMMY_RECIPES;
import static com.gregtechceu.gtceu.common.data.models.GTMachineModels.createWorkableCasingMachineModel;
import static io.github.cpearl0.ctnhcore.registry.CTNHBlocks.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.Ignitium;
import static net.minecraft.world.level.block.Blocks.*;

// spotless:off
public class MultiblocksA {
    public static void init(){}
    public static final MultiblockMachineDefinition UNDERFLOOR_HEATING_SYSTEM = REGISTRATE.multiblock("underfloor_heating_system", UnderfloorHeatingMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.UNDERFLOOR_HEATING_SYSTEM)
            .tooltips(Component.translatable("ctnh.multiblock.underfloor_heating_system.tooltip.0").withStyle(ChatFormatting.GRAY),
                    Component.translatable("ctnh.multiblock.underfloor_heating_system.tooltip.1"),
                    Component.translatable("ctnh.multiblock.underfloor_heating_system.tooltip.2"),
                    Component.translatable("ctnh.multiblock.underfloor_heating_system.tooltip.3"))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAAAAAAAAAAAAA")
                    .aisle("AAAAAAAABAAAAAAA")
                    .aisle("AAAAAAAABAAAAAAA")
                    .aisle("AAAAAAAABAAAAAAA")
                    .aisle("AAAAAAAABAAAAAAA")
                    .aisle("AAAAAAAABAAAAAAA")
                    .aisle("AAAAAAAABAAAAAAA")
                    .aisle("AAAAAAAABAAAAAAA")
                    .aisle("AAAAAAAABAAAAAAA")
                    .aisle("AAAAAAAABAAAAAAA")
                    .aisle("AAAAAAAABAAAAAAA")
                    .aisle("AAAAAAAABAAAAAAA")
                    .aisle("AAAAAAAABAAAAAAA")
                    .aisle("AAAAAAAABAAAAAAA")
                    .aisle("AAAAAAAABAAAAAAA")
                    .aisle("AAAAAAAA@AAAAAAA")
                    .where("A", Predicates.blocks(AllBlocks.COPPER_SHINGLES.getStandard().get())
                            .or(Predicates.blocks(AllBlocks.COPPER_SHINGLES.get(CopperBlockSet.BlockVariant.INSTANCE, WeatheringCopper.WeatherState.EXPOSED, false).get()))
                            .or(Predicates.blocks(AllBlocks.COPPER_SHINGLES.get(CopperBlockSet.BlockVariant.INSTANCE, WeatheringCopper.WeatherState.WEATHERED, false).get()))
                            .or(Predicates.blocks(AllBlocks.COPPER_SHINGLES.get(CopperBlockSet.BlockVariant.INSTANCE, WeatheringCopper.WeatherState.OXIDIZED, false).get()))
                            .or(Predicates.blocks(AllBlocks.COPPER_SHINGLES.get(CopperBlockSet.BlockVariant.INSTANCE, WeatheringCopper.WeatherState.EXPOSED, true).get()))
                            .or(Predicates.blocks(AllBlocks.COPPER_SHINGLES.get(CopperBlockSet.BlockVariant.INSTANCE, WeatheringCopper.WeatherState.WEATHERED, true).get()))
                            .or(Predicates.blocks(AllBlocks.COPPER_SHINGLES.get(CopperBlockSet.BlockVariant.INSTANCE, WeatheringCopper.WeatherState.OXIDIZED, true).get()))
                            .or(Predicates.blocks(AllBlocks.COPPER_SHINGLES.get(CopperBlockSet.BlockVariant.INSTANCE, WeatheringCopper.WeatherState.UNAFFECTED, true).get()))
                            .or(Predicates.autoAbilities(definition.getRecipeTypes())))
                    .where("B", Predicates.blocks(CASING_BRONZE_PIPE.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel(Create.asResource("block/copper/copper_shingles"),
                    GTCEu.id("block/multiblock/multiblock_tank"))
            .beforeWorking((machine, recipe) -> {
                var efficiency = ((UnderfloorHeatingMachine) machine).getEfficiency();
                machine.self().getHolder().self().getPersistentData().putDouble("efficiency", efficiency);
                return null;
            })
            .recipeModifier((machine, group, recipe) -> {
                if (machine instanceof UnderfloorHeatingMachine underfloorHeatingMachine) {
                    recipe.multiplyInputs(underfloorHeatingMachine.rate / 100);
                }
                return null;
            })
            .onWorking(machine -> {
                if (machine instanceof UnderfloorHeatingMachine) {
                    var pos = machine.self().getPos();
                    var facing = machine.self().getFrontFacing();
                    double efficiency = machine.self().getHolder().self().getPersistentData().getDouble("efficiency");
                    if (machine.self().getOffsetTimer() % 20 == 0) {
                        efficiency = ((UnderfloorHeatingMachine) machine).getEfficiency();
                        machine.self().getHolder().self().getPersistentData().putDouble("efficiency", efficiency);
                    }
                    AABB range = switch (facing) {
                        case NORTH -> AABB.of(BoundingBox.fromCorners(pos.offset(-39, 0, -32), pos.offset(40, 16, 47)));
                        case SOUTH -> AABB.of(BoundingBox.fromCorners(pos.offset(-40, 0, -47), pos.offset(39, 16, 32)));
                        case WEST -> AABB.of(BoundingBox.fromCorners(pos.offset(-32, 0, -40), pos.offset(47, 16, 39)));
                        case EAST -> AABB.of(BoundingBox.fromCorners(pos.offset(-47, 0, -39), pos.offset(32, 16, 40)));
                        default -> throw new IllegalStateException("Unexpected value: " + facing);
                    };
                    UnderfloorHeatingSystemTempModifier.UNDERFLOOR_HEATING_SYSTEM_RANGE.put(range, efficiency * ((UnderfloorHeatingMachine) machine).rate / 100);
                }
                return true;
            })
            .afterWorking(machine -> {
                var pos = machine.self().getPos();
                var facing = machine.self().getFrontFacing();
                AABB range = switch (facing) {
                    case NORTH -> AABB.of(BoundingBox.fromCorners(pos.offset(-39, 0, -32), pos.offset(40, 16, 47)));
                    case SOUTH -> AABB.of(BoundingBox.fromCorners(pos.offset(-40, 0, -47), pos.offset(39, 16, 32)));
                    case WEST -> AABB.of(BoundingBox.fromCorners(pos.offset(-32, 0, -40), pos.offset(47, 16, 39)));
                    case EAST -> AABB.of(BoundingBox.fromCorners(pos.offset(-47, 0, -39), pos.offset(32, 16, 40)));
                    default -> throw new IllegalStateException("Unexpected value: " + facing);
                };
                UnderfloorHeatingSystemTempModifier.UNDERFLOOR_HEATING_SYSTEM_RANGE.remove(range);
            })
            .register();


    public static final MultiblockMachineDefinition ASTRONOMICAL_OBSERVATORY = REGISTRATE.multiblock("astronomical_observatory", AstronomicalMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.ASTRONOMICAL_OBSERVATORY)
            .tooltips(Component.translatable("ctnh.multiblock.astronomical.tooltip.0").withStyle(ChatFormatting.GRAY),
                    Component.translatable("ctnh.multiblock.astronomical.tooltip.1"))
            .appearanceBlock(CASING_STAINLESS_CLEAN)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("   BBB   ", "   BBB   ", "   BBB   ", "   BBB   ", "   BBB   ", "   RDR   ", "         ", "         ", "         ")
                    .aisle("  BBBBB  ", "  B   B  ", "  B E B  ", "  B F B  ", "  B   B  ", "  R   R  ", "   RDR   ", "         ", "         ")
                    .aisle(" BBBBBBB ", " B     B ", " B  E  B ", " B  F  B ", " B     B ", " R     R ", "  R   R  ", "   RDR   ", "         ")
                    .aisle("BBBBBBBBB", "B       B", "B   E   B", "B   F   B", "B       B", "R       R", " R     R ", "  R   R  ", "   RDR   ")
                    .aisle("BBBBBBBBB", "B       B", "BEEEEEEEB", "B   F   B", "B       B", "R       R", " R     R ", "  R   R  ", "   RDR   ")
                    .aisle("BBBBBBBBB", "B       B", "B   E   B", "B   F   B", "B       B", "R       R", " R     R ", "  R   R  ", "   RDR   ")
                    .aisle(" BBBBBBB ", " B     B ", " B  E  B ", " B  F  B ", " B     B ", " R     R ", "  R   R  ", "   RDR   ", "         ")
                    .aisle("  BBBBB  ", "  B   B  ", "  B E B  ", "  B F B  ", "  B   B  ", "  R   R  ", "   RDR   ", "         ", "         ")
                    .aisle("   BBB   ", "   B@B   ", "   BAB   ", "   BBB   ", "   BBB   ", "   RDR   ", "         ", "         ", "         ")
                    .where("A", abilities(CTNHPartAbility.CIRCUIT))
                    .where(" ", Predicates.any())
                    .where("R", Predicates.blocks(CTNHBlocks.CASING_REFLECT_LIGHT.get()))
                    .where("B", Predicates.blocks(CASING_STAINLESS_CLEAN.get())
                            .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(1).setPreviewCount(1))
                    )
                    .where("D", Predicates.blocks(CASING_TEMPERED_GLASS.get()))
                    .where("E", Predicates.blocks(GTMachines.HULL[GTValues.HV].getBlock()))
                    .where("F", Predicates.blocks(Blocks.DAYLIGHT_DETECTOR))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"),
                    GTCEu.id("block/multiblock/assembly_line"))
            .register();

    public static final MultiblockMachineDefinition PHOTOVOLTAIC_POWER_STATION_ENERGETIC =
            registerPhotovoltaicPowerStation("energetic", 1, CTNHBlocks.ENERGETIC_PHOTOVOLTAIC_BLOCK);
    public static final MultiblockMachineDefinition PHOTOVOLTAIC_POWER_STATION_PULSATING =
            registerPhotovoltaicPowerStation("pulsating", 4, CTNHBlocks.PULSATING_PHOTOVOLTAIC_BLOCK);
    public static final MultiblockMachineDefinition PHOTOVOLTAIC_POWER_STATION_VIBRANT =
            registerPhotovoltaicPowerStation("vibrant", 16, CTNHBlocks.VIBRANT_PHOTOVOLTAIC_BLOCK);

    public static MultiblockMachineDefinition registerPhotovoltaicPowerStation(String tier, int basicRate, BlockEntry<?> photovoltaicBlock) {
        return REGISTRATE.multiblock("photovoltaic_power_station_" + tier, holder -> new PhotovoltaicPowerStationMachine(holder, basicRate))
                .rotationState(RotationState.NON_Y_AXIS)
                .tooltips(Component.translatable("ctnh.multiblock.photovoltaic_power_station_" + tier + ".tooltip.0").withStyle(ChatFormatting.GRAY),
                        Component.translatable("ctnh.multiblock.photovoltaic_power_station_" + tier + ".tooltip.1"),
                        Component.translatable("ctnh.multiblock.photovoltaic_power_station_" + tier + ".tooltip.2"))
                .appearanceBlock(CTNHBlocks.CASING_REFLECT_LIGHT)
                .allowExtendedFacing(false)
                .allowFlip(false)
                .pattern(definition -> FactoryStaticBlockPattern.start()
                        .aisle("#AAAAAAA#", "#########", "#AAAAAAA#", "####B####", "####B####", "####B####", "#########")
                        .aisle("AAAAAAAAA", "##AAAAA##", "AAAAAAAAA", "#########", "#########", "##CCCCC##", "#CC###CC#")
                        .aisle("AAAAAAAAA", "#AA###AA#", "AAADDDAAA", "#########", "#########", "##CEEEC##", "#CE###EC#")
                        .aisle("AAAAAAAAA", "#A#####A#", "AADDDDDAA", "#########", "#########", "##CEEEC##", "#CE###EC#")
                        .aisle("AAAAAAAAA", "#A#####A#", "AADDDDDAA", "#########", "#########", "##CEEEC##", "#CE###EC#")
                        .aisle("AAAAAAAAA", "#A#####A#", "AADDDDDAA", "#########", "#########", "##CEEEC##", "#CE###EC#")
                        .aisle("AAAAAAAAA", "#AA###AA#", "AAADDDAAA", "#########", "#########", "##CEEEC##", "#CE###EC#")
                        .aisle("AAAAAAAAA", "##AAAAA##", "AAAAAAAAA", "#########", "#########", "##CCCCC##", "#CC###CC#")
                        .aisle("#AAA@AAA#", "#########", "#AAAAAAA#", "####B####", "####B####", "####B####", "#########")
                        .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                        .where("A", Predicates.blocks(CASING_REFLECT_LIGHT.get())
                                .or(Predicates.abilities(PartAbility.OUTPUT_ENERGY)
                                        .setMinGlobalLimited(1)
                                        .setMaxGlobalLimited(2)
                                        .setPreviewCount(2)
                                )
                                .or(Predicates.autoAbilities(true, false, false))
                        )
                        .where("B", Predicates.blocks(AllBlocks.METAL_GIRDER.get()))
                        .where("#", Predicates.any())
                        .where("D", Predicates.blocks(CASING_TEMPERED_GLASS.get()))
                        .where("E", Predicates.blocks(photovoltaicBlock.get()), false)
                        .where("C", Predicates.blocks(CASING_REFLECT_LIGHT.get()), false)
                        .build())
                .workableCasingModel(CTNHCore.id("block/casings/reflect_light_casing"),
                        GTCEu.id("block/multiblock/generator/large_steam_turbine"))
                .register();
    }

    public static final MultiblockMachineDefinition WIND_POWER_ARRAY = WindPowerArrayRegister.register("wind_power_array",1,CASING_STEEL_SOLID, GTMaterials.Steel,"machine_casing_solid_steel");
    public static final MultiblockMachineDefinition ADVANCED_WIND_POWER_ARRAY = WindPowerArrayRegister.register("advanced_wind_power_array",2,CASING_STAINLESS_CLEAN,GTMaterials.StainlessSteel,"machine_casing_clean_stainless_steel");
    public static final MultiblockMachineDefinition SUPER_WIND_POWER_ARRAY = WindPowerArrayRegister.register("super_wind_power_array",3,CASING_TUNGSTENSTEEL_ROBUST, TungstenSteel,"machine_casing_robust_tungstensteel");

    public static final MultiblockMachineDefinition SLAUGHTER_HOUSE = REGISTRATE.multiblock("slaughter_house", SlaughterHouseMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.SLAUGHTER_HOUSE)
            .tooltips(Component.translatable("ctnh.multiblock.slaughter_house.tooltip.0").withStyle(ChatFormatting.GRAY),
                    Component.translatable("ctnh.multiblock.slaughter_house.tooltip.1"),
                    Component.translatable("ctnh.multiblock.slaughter_house.tooltip.2"),
                    Component.translatable("ctnh.multiblock.slaughter_house.tooltip.3").withStyle(ChatFormatting.RED),
                    Component.translatable("ctnh.multiblock.slaughter_house.tooltip.4").withStyle(ChatFormatting.GREEN),
                    Component.translatable("ctnh.multiblock.slaughter_house.tooltip.5"))
            .recipeModifiers(SlaughterHouseMachine::recipeModifier, GTRecipeModifiers.BATCH_MODE)
            .appearanceBlock(GTBlocks.CASING_STEEL_SOLID)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("ABBBA", "ABBBA", "CCCCC", "CCCCC", "CCCCC", "CCCCC", "ABBBA")
                    .aisle("BAAAB", "BDDDB", "CDDDC", "CDDDC", "CDDDC", "CDDDC", "BAAAB")
                    .aisle("BAAAB", "BD#DB", "CD#DC", "CD#DC", "CD#DC", "CD#DC", "BAEAB")
                    .aisle("BAAAB", "BDDDB", "CDDDC", "CDDDC", "CDDDC", "CDDDC", "BAAAB")
                    .aisle("AB@BA", "ABBBA", "CCCCC", "CCCCC", "CCCCC", "CCCCC", "ABBBA")
                    .where("A", Predicates.blocks(CASING_STEEL_SOLID.get()))
                    .where("B", Predicates.blocks(CASING_STEEL_SOLID.get()).setMinGlobalLimited(15)
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1)))
                    .where("#", Predicates.any())
                    .where("C", Predicates.blocks(CASING_TEMPERED_GLASS.get()))
                    .where("D", Predicates.blocks(EIOBlocks.DARK_STEEL_BARS.get()))
                    .where("E", abilities(PartAbility.MUFFLER).setExactLimit(1))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_solid_steel"), GTCEu.id("block/multiblock/implosion_compressor"))
            .register();

    public final static MultiblockMachineDefinition COKE_TOWER = REGISTRATE.multiblock("coke_tower", CoilWorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.PYROLYSE_RECIPES)
            .tooltips(Component.translatable("ctnh.multiblock.coke_tower.tooltip.0").withStyle(ChatFormatting.GRAY),
                    Component.translatable("ctnh.multiblock.coke_tower.tooltip.1"))
            .recipeModifiers(GTRecipeModifiers::multiSmelterParallel, BATCH_MODE)
            .appearanceBlock(GTBlocks.CASING_STAINLESS_CLEAN)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("ABBBA", "ACCCA", "ACCCA", "ACCCA", "ACCCA", "ACCCA", "ACCCA", "ACCCA", "ACCCA", "ACCCA", "ACCCA", "ACCCA", "ACCCA", "ACCCA", "ACCCA", "ACCCA", "#ACA#")
                    .aisle("BDDDB", "CEEEC", "CFFFC", "CEEEC", "CFFFC", "CEEEC", "CFFFC", "CEEEC", "CFFFC", "CEEEC", "CFFFC", "CEEEC", "CFFFC", "CEEEC", "CFFFC", "CEEEC", "ACCCA")
                    .aisle("BDDDB", "CEGEC", "CFGFC", "CEGEC", "CFGFC", "CEGEC", "CFGFC", "CEGEC", "CFGFC", "CEGEC", "CFGFC", "CEGEC", "CFGFC", "CEGEC", "CFGFC", "CEGEC", "CCHCC")
                    .aisle("BDDDB", "CEEEC", "CFFFC", "CEEEC", "CFFFC", "CEEEC", "CFFFC", "CEEEC", "CFFFC", "CEEEC", "CFFFC", "CEEEC", "CFFFC", "CEEEC", "CFFFC", "CEEEC", "ACCCA")
                    .aisle("ABBBA", "ACCCA", "AC@CA", "ACCCA", "ACCCA", "ACCCA", "ACCCA", "ACCCA", "ACCCA", "ACCCA", "ACCCA", "ACCCA", "ACCCA", "ACCCA", "ACCCA", "ACCCA", "#ACA#")
                    .where("A", Predicates.frames(GTMaterials.StainlessSteel))
                    .where("B", Predicates.blocks(HEAT_VENT.get()))
                    .where("C", Predicates.blocks(GTBlocks.CASING_STAINLESS_CLEAN.get()).setMinGlobalLimited(130)
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1)))
                    .where("#", Predicates.any())
                    .where("D", Predicates.blocks(FIREBOX_TITANIUM.get()))
                    .where("E", Predicates.heatingCoils())
                    .where("F", Predicates.blocks(CASING_INVAR_HEATPROOF.get()))
                    .where("G", Predicates.blocks(GTBlocks.CASING_STEEL_PIPE.get()))
                    .where("H", abilities(PartAbility.MUFFLER).setExactLimit(1))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .additionalDisplay((controller, components) -> {
                if (controller.isFormed() && controller instanceof CoilWorkableElectricMultiblockMachine machine) {
                    components.add(Component.translatable("gtceu.multiblock.pyrolyse_oven.speed", machine.getCoilTier() == 0 ? 75 : 50 * (machine.getCoilTier() + 15)));
                }
            })
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"), GTCEu.id("block/machines/fluid_heater"))
            .register();

    public final static MultiblockMachineDefinition BEDROCK_DRILLING_RIGS = REGISTRATE.multiblock("bedrock_drilling_rigs", CoilWorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(CTNHRecipeTypes.BEDROCK_DRILLING_RIGS)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH)
            .tooltips(Component.translatable("gtceu.multiblock.laser.tooltip"))
            .tooltips(Component.translatable("gtceu.multiblock.parallelizable.tooltip"))
            .appearanceBlock(CTNHBlocks.CASING_TUNGSTENCU_DIAMOND_PLATING)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("#######", "AAAAAAA", "A#####A", "A#####A", "A#####A", "A#####A", "A#####A", "AAAAAAA")
                    .aisle("#######", "A#####A", "#######", "#B###B#", "#######", "#######", "#######", "AB###BA")
                    .aisle("#######", "A#####A", "##BCB##", "###C###", "##CCC##", "##CCC##", "##CCC##", "A#BCB#A")
                    .aisle("###E###", "A##C##A", "##CCC##", "##CDC##", "##CDC##", "##CDC##", "##CDC##", "A#CCC#A")
                    .aisle("#######", "A#####A", "##BCB##", "###C###", "##CCC##", "##C@C##", "##CCC##", "A#BCB#A")
                    .aisle("#######", "A#####A", "#######", "#B###B#", "#######", "#######", "#######", "AB###BA")
                    .aisle("#######", "AAAAAAA", "A#####A", "A#####A", "A#####A", "A#####A", "A#####A", "AAAAAAA")
                    .where("A", Predicates.blocks(GCYMBlocks.CASING_SECURE_MACERATION.get()))
                    .where("#", Predicates.any())
                    .where("B", Predicates.frames(GTMaterials.TungstenCarbide))
                    .where("C", Predicates.blocks(CTNHBlocks.CASING_TUNGSTENCU_DIAMOND_PLATING.get()).setMinGlobalLimited(20)
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(abilities(PartAbility.PARALLEL_HATCH).setMaxGlobalLimited(1))
                            .or(abilities(PartAbility.INPUT_LASER).setPreviewCount(2)))
                    .where("D", Predicates.blocks(CASING_TUNGSTENSTEEL_PIPE.get()))
                    .where("E", Predicates.blocks(Blocks.BEDROCK))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel(CTNHCore.id("block/casings/tungstencu_diamond_plating_casing"),
                    GTCEu.id("block/multiblock/implosion_compressor"))
            .register();
    public final static MultiblockMachineDefinition NAQ_REACTOR_MK3 = REGISTRATE.multiblock("naq_reactor_mk3", NaqReactorMachine::new)
            .rotationState(RotationState.ALL)
            .recipeTypes(CTNHRecipeTypes.NAQ_MK1)
            .generator(true)
            .recipeModifier(NaqReactorMachine::recipeModifier, true)
            .tooltips(Component.translatable("ctnh.multiblock.naq_reactor_mk3.tooltip.1").withStyle(ChatFormatting.GRAY))
            .tooltips(Component.translatable("ctnh.multiblock.naq_reactor_mk3.tooltip.2"))
            .tooltips(Component.translatable("ctnh.multiblock.naq_reactor_mk3.tooltip.3"))
            .tooltips(Component.translatable("ctnh.multiblock.naq_reactor_mk3.tooltip.4"))
            .tooltips(Component.translatable("gtceu.multiblock.laser.tooltip"))
            .appearanceBlock(CTNHBlocks.CASING_NAQUADAH_ALLOY_BLOCK)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("        BBBBBBB        ", "          CDC          ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "          CDC          ", "        BBBBBBB        ")
                    .aisle("      BBBBBBBBBBB      ", "          DDD          ", "          CDC          ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "          CDC          ", "          DDD          ", "      BBBBBBBBBBB      ")
                    .aisle("    BBBBBBBBBBBBBBB    ", "        BBBBBBB        ", "          DDD          ", "          CDC          ", "          CDC          ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "          CDC          ", "          CDC          ", "          DDD          ", "        BBBBBBB        ", "    BBBBBBBBBBBBBBB    ")
                    .aisle("   BBBBBBBBBBBBBBBBB   ", "      BBBBBBBBBBB      ", "          EEE          ", "          DDD          ", "          DDD          ", "          CDC          ", "          CDC          ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "          CDC          ", "          CDC          ", "          DDD          ", "          DDD          ", "          EEE          ", "      BBBBBBBBBBB      ", "   BBBBBBBBBBBBBBBBB   ")
                    .aisle("  BBBBBBBBBBBBBBBBBBB  ", "     BBBBBBBBBBBBB     ", "          EEE          ", "           E           ", "           D           ", "          DDD          ", "          DDD          ", "          CDC          ", "          CDC          ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "          CDC          ", "          CDC          ", "          DDD          ", "          DDD          ", "           D           ", "          EEE          ", "          EEE          ", "     BBBBBBBBBBBBB     ", "  BBBBBBBBBBBBBBBBBBB  ")
                    .aisle("  BBBBBBBBBBBBBBBBBBB  ", "    BBBBBBBBBBBBBBB    ", "        EEEEEEE        ", "           E           ", "           E           ", "           D           ", "           D           ", "          DDD          ", "          DDD          ", "          CDC          ", "          CDC          ", "          CDC          ", "         FFFFF         ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "         FFFFF         ", "          CDC          ", "          CDC          ", "          CDC          ", "          DDD          ", "          DDD          ", "           D           ", "           D           ", "           E           ", "          EEE          ", "        EEEEEEE        ", "    BBBBBBBBBBBBBBB    ", "  BBBBBBBBBBBBBBBBBBB  ")
                    .aisle(" BBBBBBBBBBBBBBBBBBBBB ", "   BBBBBBBBBBBBBBBBB   ", "      BBEEEBEEEBB      ", "          EEE          ", "           E           ", "                       ", "                       ", "           D           ", "           D           ", "          DDD          ", "          DDD          ", "          DDD          ", "       FF     FF       ", "         FFFFF         ", "                       ", "                       ", "                       ", "         FFFFF         ", "                       ", "                       ", "                       ", "         FFFFF         ", "       FF     FF       ", "          DDD          ", "          DDD          ", "          DDD          ", "           D           ", "           D           ", "                       ", "                       ", "           E           ", "          EEE          ", "      BBEEEBEEEBB      ", "   BBBBBBBBBBBBBBBBB   ", " BBBBBBBBBBBBBBBBBBBBB ")
                    .aisle(" BBBBBBBBBBBBBBBBBBBBB ", "   BBBBBBBBBBBBBBBBB   ", "      BBBEBBBEBBB      ", "          EEE          ", "           E           ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "      FF       FF      ", "        F     F        ", "                       ", "                       ", "         FFFFF         ", "        FFFFFFF        ", "         FFFFF         ", "                       ", "                       ", "        F     F        ", "      FF       FF      ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "           E           ", "          EEE          ", "      BBBEBBBEBBB      ", "   BBBBBBBBBBBBBBBBB   ", " BBBBBBBBBBBBBBBBBBBBB ")
                    .aisle("BBBBBBBBBBBBBBBBBBBBBBB", "  BBBBBBBBBBBBBBBBBBB  ", "     EEBBEBBBEBBEE     ", "          EEE          ", "           E           ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "      F         F      ", "       F       F       ", "                       ", "                       ", "        F     F        ", "       FF     FF       ", "        F     F        ", "                       ", "                       ", "       F       F       ", "      F         F      ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "           E           ", "          EEE          ", "     EEBBEBBBEBBEE     ", "  BBBBBBBBBBBBBBBBBBB  ", "BBBBBBBBBBBBBBBBBBBBBBB")
                    .aisle("BBBBBBBBBBBBBBBBBBBBBBB", "  BBBBBBBBBBBBBBBBBBB  ", "     EEEEEEBEEEEEE     ", "         EEEEE         ", "           E           ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "     F           F     ", "      F         F      ", "                       ", "                       ", "       F       F       ", "      FF   G   FF      ", "       F       F       ", "                       ", "                       ", "      F         F      ", "     F           F     ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "           E           ", "         EEEEE         ", "     EEEEEEBEEEEEE     ", "  BBBBBBBBBBBBBBBBBBB  ", "BBBBBBBBBBBBBBBBBBBBBBB")
                    .aisle("BBBBBBBBBBBBBBBBBBBBBBB", "CDBBBBBBBBBBBBBBBBBBBDC", " CDEEEEBBEHHHEBBEEEEDC ", "  CD  EEEEEEEEEEE  DC  ", "  CD      EEE      DC  ", "   CD     CEC     DC   ", "   CD     CEC     DC   ", "    CD    CEC    DC    ", "    CD    CEC    DC    ", "     CD   CEC   DC     ", "     CD   CEC   DC     ", "     CD   CEC   DC     ", "     F     E     F     ", "      F         F      ", "                       ", "                       ", "       F   G   F       ", "      FF  GGG  FF      ", "       F   G   F       ", "                       ", "                       ", "      F         F      ", "     F     E     F     ", "     CD   CEC   DC     ", "     CD   CEC   DC     ", "     CD   CEC   DC     ", "    CD    CEC    DC    ", "    CD    CEC    DC    ", "   CD     CEC     DC   ", "   CD     CEC     DC   ", "  CD      EEE      DC  ", "  CDEEEEEEEEEEEEEEEDC  ", " CDEEEEBBEHHHEBBEEEEDC ", "CDBBBBBBBBBBBBBBBBBBBDC", "BBBBBBBBBBBBBBBBBBBBBBB")
                    .aisle("BBBBBBBBBBBBBBBBBBBBBBB", "DDBBBBBBBBBBBBBBBBBBBDD", " DDEEEBBBBHIHBBBBEEEDD ", "  DDEEEEEEEIEEEEEEEDD  ", "  DDDEEEEEEIEEEEEEDDD  ", "   DDD    EIE    DDD   ", "   DDD    EIE    DDD   ", "    DDD   EIE   DDD    ", "    DDD   EIE   DDD    ", "     DD   EIE   DD     ", "     DD   EIE   DD     ", "     DD   EIE   DD     ", "     F    EIE    F     ", "      F    E    F      ", "                       ", "           G           ", "       F  GGG  F       ", "      FF GGGGG FF      ", "       F  GGG  F       ", "           G           ", "                       ", "      F    E    F      ", "     F    EIE    F     ", "     DD   EIE   DD     ", "     DD   EIE   DD     ", "     DD   EIE   DD     ", "    DDD   EIE   DDD    ", "    DDD   EIE   DDD    ", "   DDD    EIE    DDD   ", "   DDD    EIE    DDD   ", "  DDDEEEEEEIEEEEEEDDD  ", "  DDEEEEEEEIEEEEEEEDD  ", " DDEEEBBBBHIHBBBBEEEDD ", "DDBBBBBBBBBBBBBBBBBBBDD", "BBBBBBBBBBBBBBBBBBBBBBB")
                    .aisle("BBBBBBBBBBBBBBBBBBBBBBB", "CDBBBBBBBBBBBBBBBBBBBDC", " CDEEEEBBEHHHEBBEEEEDC ", "  CD  EEEEEEEEEEE  DC  ", "  CD      EEE      DC  ", "   CD     CEC     DC   ", "   CD     CEC     DC   ", "    CD    CEC    DC    ", "    CD    CEC    DC    ", "     CD   CEC   DC     ", "     CD   CEC   DC     ", "     CD   CEC   DC     ", "     F     E     F     ", "      F         F      ", "                       ", "                       ", "       F   G   F       ", "      FF  GGG  FF      ", "       F   G   F       ", "                       ", "                       ", "      F         F      ", "     F     E     F     ", "     CD   CEC   DC     ", "     CD   CEC   DC     ", "     CD   CEC   DC     ", "    CD    CEC    DC    ", "    CD    CEC    DC    ", "   CD     CEC     DC   ", "   CD     CEC     DC   ", "  CD      EEE      DC  ", "  CDEEEEEEEEEEEEEEEDC  ", " CDEEEEBBEHHHEBBEEEEDC ", "CDBBBBBBBBBBBBBBBBBBBDC", "BBBBBBBBBBBBBBBBBBBBBBB")
                    .aisle("BBBBBBBBBBBBBBBBBBBBBBB", "  BBBBBBBBBBBBBBBBBBB  ", "     EEEEEEBEEEEEE     ", "         EEEEE         ", "           E           ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "     F           F     ", "      F         F      ", "                       ", "                       ", "       F       F       ", "      FF   G   FF      ", "       F       F       ", "                       ", "                       ", "      F         F      ", "     F           F     ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "           E           ", "         EEEEE         ", "     EEEEEEBEEEEEE     ", "  BBBBBBBBBBBBBBBBBBB  ", "BBBBBBBBBBBBBBBBBBBBBBB")
                    .aisle("BBBBBBBBBBBBBBBBBBBBBBB", "  BBBBBBBBBBBBBBBBBBB  ", "     EEBBEBBBEBBEE     ", "          EEE          ", "           E           ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "      F         F      ", "       F       F       ", "                       ", "                       ", "        F     F        ", "       FF     FF       ", "        F     F        ", "                       ", "                       ", "       F       F       ", "      F         F      ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "           E           ", "          EEE          ", "     EEBBEBBBEBBEE     ", "  BBBBBBBBBBBBBBBBBBB  ", "BBBBBBBBBBBBBBBBBBBBBBB")
                    .aisle(" BBBBBBBBBBBBBBBBBBBBB ", "   BBBBBBBBBBBBBBBBB   ", "      BBBEBBBEBBB      ", "          EEE          ", "           E           ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "      FF       FF      ", "        F     F        ", "                       ", "                       ", "         FFFFF         ", "        FFFFFFF        ", "         FFFFF         ", "                       ", "                       ", "        F     F        ", "      FF       FF      ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "           E           ", "          EEE          ", "      BBBEBBBEBBB      ", "   BBBBBBBBBBBBBBBBB   ", " BBBBBBBBBBBBBBBBBBBBB ")
                    .aisle(" BBBBBBBBBBBBBBBBBBBBB ", "   BBBBBBBBBBBBBBBBB   ", "      BBEEEBEEEBB      ", "          EEE          ", "           E           ", "                       ", "                       ", "           D           ", "           D           ", "          DDD          ", "          DDD          ", "          DDD          ", "       FF     FF       ", "         FFFFF         ", "                       ", "                       ", "                       ", "         FFFFF         ", "                       ", "                       ", "                       ", "         FFFFF         ", "       FF     FF       ", "          DDD          ", "          DDD          ", "          DDD          ", "           D           ", "           D           ", "                       ", "                       ", "           E           ", "          EEE          ", "      BBEEEBEEEBB      ", "   BBBBBBBBBBBBBBBBB   ", " BBBBBBBBBBBBBBBBBBBBB ")
                    .aisle("  BBBBBBBBBBBBBBBBBBB  ", "    BBBBBBBBBBBBBBB    ", "        EEEEEEE        ", "          EEE          ", "           E           ", "           D           ", "           D           ", "          DDD          ", "          DDD          ", "          CDC          ", "          CDC          ", "          CDC          ", "         FFFFF         ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "         FFFFF         ", "          CDC          ", "          CDC          ", "          CDC          ", "          DDD          ", "          DDD          ", "           D           ", "           D           ", "           E           ", "          EEE          ", "        EEEEEEE        ", "    BBBBBBBBBBBBBBB    ", "  BBBBBBBBBBBBBBBBBBB  ")
                    .aisle("  BBBBBBBBBBBBBBBBBBB  ", "     BBBBBBBBBBBBB     ", "          EEE          ", "          EEE          ", "           D           ", "          DDD          ", "          DDD          ", "          CDC          ", "          CDC          ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "          CDC          ", "          CDC          ", "          DDD          ", "          DDD          ", "           D           ", "          EEE          ", "          EEE          ", "     BBBBBBBBBBBBB     ", "  BBBBBBBBBBBBBBBBBBB  ")
                    .aisle("   BBBBBBBBBBBBBBBBB   ", "      BBBBBBBBBBB      ", "          EEE          ", "          DDD          ", "          DDD          ", "          CDC          ", "          CDC          ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "          CDC          ", "          CDC          ", "          DDD          ", "          DDD          ", "          EEE          ", "      BBBBBBBBBBB      ", "   BBBBBBBBBBBBBBBBB   ")
                    .aisle("    BBBBBBBBBBBBBBB    ", "        BBBBBBB        ", "          DDD          ", "          CDC          ", "          CDC          ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "          CDC          ", "          CDC          ", "          DDD          ", "        BBBBBBB        ", "    BBBBBBBBBBBBBBB    ")
                    .aisle("      BBBBBBBBBBB      ", "          DDD          ", "          CDC          ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "          CDC          ", "          DDD          ", "      BBBBBBBBBBB      ")
                    .aisle("        BBB@BBB        ", "          CDC          ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "          CDC          ", "        BBBBBBB        ")
                    .where(" ", Predicates.any())
                    .where("B", Predicates.blocks(CTNHBlocks.CASING_NAQUADAH_ALLOY_BLOCK.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                            .or(abilities(PartAbility.OUTPUT_LASER).setPreviewCount(1)))
                    .where("C", Predicates.frames(GTMaterials.Naquadria))
                    .where("D", Predicates.blocks(CTNHBlocks.CASING_NAQUADAH_BLOCK.get()))
                    .where("E", Predicates.blocks(CTNHBlocks.CASING_NAQUADAH_BLOCK.get()))
                    .where("F", Predicates.blocks(CTNHBlocks.PLASMA_COOLED_CORE.get()))
                    .where("G", Predicates.blocks(CTNHBlocks.ANNIHILATE_CORE_MKI.get()))
                    .where("H", Predicates.blocks(CTNHBlocks.REACTOR_CONDENSATION_BLOCK.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("I", Predicates.blocks(ATOMS_SPLIT_BLOCKS.get()))
                    .build()
            )

            .workableCasingModel(CTNHCore.id("block/casings/nq_alloy_casing"), GTCEu.id("block/multiblock/fusion_reactor"))
            .register();

    public static final MultiblockMachineDefinition[] COMPRESSED_FUSION_REACTOR = CTNHMachineUtils.registerTieredMultis("compressed_fusion_reactor",
            (holder, tier) -> new FusionReactorMachine(holder, tier),
            (tier, builder) -> builder
                    .rotationState(RotationState.ALL)
                    .langValue("Fusion Reactor Computer MK %s".formatted(FormattingUtil.toRomanNumeral(tier - 3)))
                    .recipeType(GTRecipeTypes.FUSION_RECIPES)
                    .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH, FusionReactorMachine::recipeModifier)
                    .tooltips(
                            Component.translatable("gtceu.machine.fusion_reactor.capacity",
                                    FusionReactorMachine.calculateEnergyStorageFactor(tier, 16) / 1000000L),
                            Component.translatable("gtceu.machine.fusion_reactor.overclocking"))
                    .tooltips(Component.translatable("gtceu.multiblock.laser.tooltip"))
                    .tooltips(Component.translatable("gtceu.multiblock.parallelizable.tooltip"))

                    .appearanceBlock(() -> CTNHFusionCasingType.getCasingState(tier))
                    .pattern((definition) -> {
                        TraceabilityPredicate casing = Predicates.blocks(CTNHFusionCasingType.getCasingState(tier));
                        return FactoryBlockPattern.start()
                                .aisle("                                               ", "                                               ", "                    FCCCCCF                    ", "                    FCIBICF                    ", "                    FCCCCCF                    ", "                                               ", "                                               ")
                                .aisle("                                               ", "                    FCBBBCF                    ", "                   CC#####CC                   ", "                   CC#####CC                   ", "                   CC#####CC                   ", "                    FCBBBCF                    ", "                                               ")
                                .aisle("                    FCCCCCF                    ", "                   CC#####CC                   ", "                CCCCC#####CCCCC                ", "                CCCHHHHHHHHHCCC                ", "                CCCCC#####CCCCC                ", "                   CC#####CC                   ", "                    FCCCCCF                    ")
                                .aisle("                    FCIBICF                    ", "                CCCCC#####CCCCC                ", "              CCCCCHHHHHHHHHCCCCC              ", "              CCHHHHHHHHHHHHHHHCC              ", "              CCCCCHHHHHHHHHCCCCC              ", "                CCCCC#####CCCCC                ", "                    FCIBICF                    ")
                                .aisle("                    FCCCCCF                    ", "              CCCCCCC#####CCCCCCC              ", "            CCCCHHHCC#####CCHHHCCCC            ", "            CCHHHHHHHHHHHHHHHHHHHCC            ", "            CCCCHHHCC#####CCHHHCCCC            ", "              CCCCCCC#####CCCCCCC              ", "                    FCCCCCF                    ")
                                .aisle("                                               ", "            CCCCCCC FCBBBCF CCCCCCC            ", "           CCCHHCCCCC#####CCCCCHHCCC           ", "           CHHHHHHHCC#####CCHHHHHHHC           ", "           CCCHHCCCCC#####CCCCCHHCCC           ", "            CCCCCCC FCBBBCF CCCCCCC            ", "                                               ")
                                .aisle("                                               ", "           CCCCC               CCCCC           ", "          CCHHCCCCC FCCCCCF CCCCCHHCC          ", "          CHHHHHCCC FCIBICF CCCHHHHHC          ", "          CCHHCCCCC FCCCCCF CCCCCHHCC          ", "           CCCCC               CCCCC           ", "                                               ")
                                .aisle("                                               ", "          CCCC                   CCCC          ", "         CCHCCCC               CCCCHCC         ", "         CHHHHCC               CCHHHHC         ", "         CCHCCCC               CCCCHCC         ", "          CCCC                   CCCC          ", "                                               ")
                                .aisle("                                               ", "         CCC                       CCC         ", "        CCHCCC                   CCCHCC        ", "        CHHHCC                   CCHHHC        ", "        CCHCCC                   CCCHCC        ", "         CCC                       CCC         ", "                                               ")
                                .aisle("                                               ", "        CCC                         CCC        ", "       CCHCE                       ECHCC       ", "       CHHHC                       CHHHC       ", "       CCHCE                       ECHCC       ", "        CCC                         CCC        ", "                                               ")
                                .aisle("                                               ", "       CCC                           CCC       ", "      CCHCC                         CCHCC      ", "      CHHHC                         CHHHC      ", "      CCHCC                         CCHCC      ", "       CCC                           CCC       ", "                                               ")
                                .aisle("                                               ", "      CCC                             CCC      ", "     CCHCE                           ECHCC     ", "     CHHHC                           CHHHC     ", "     CCHCE                           ECHCC     ", "      CCC                             CCC      ", "                                               ")
                                .aisle("                                               ", "     CCC                               CCC     ", "    CCHCC                             CCHCC    ", "    CHHHC                             CHHHC    ", "    CCHCC                             CCHCC    ", "     CCC                               CCC     ", "                                               ")
                                .aisle("                                               ", "     CCC                               CCC     ", "    CCHCC                             CCHCC    ", "    CHHHC                             CHHHC    ", "    CCHCC                             CCHCC    ", "     CCC                               CCC     ", "                                               ")
                                .aisle("                                               ", "    CCC                                 CCC    ", "   CCHCC                               CCHCC   ", "   CHHHC                               CHHHC   ", "   CCHCC                               CCHCC   ", "    CCC                                 CCC    ", "                                               ")
                                .aisle("                                               ", "    CCC                                 CCC    ", "   CCHCC                               CCHCC   ", "   CHHHC                               CHHHC   ", "   CCHCC                               CCHCC   ", "    CCC                                 CCC    ", "                                               ")
                                .aisle("                                               ", "   CCC                                   CCC   ", "  CCHCC                                 CCHCC  ", "  CHHHC                                 CHHHC  ", "  CCHCC                                 CCHCC  ", "   CCC                                   CCC   ", "                                               ")
                                .aisle("                                               ", "   CCC                                   CCC   ", "  CCHCC                                 CCHCC  ", "  CHHHC                                 CHHHC  ", "  CCHCC                                 CCHCC  ", "   CCC                                   CCC   ", "                                               ")
                                .aisle("                                               ", "   CCC                                   CCC   ", "  CCHCC                                 CCHCC  ", "  CHHHC                                 CHHHC  ", "  CCHCC                                 CCHCC  ", "   CCC                                   CCC   ", "                                               ")
                                .aisle("                                               ", "  CCC                                     CCC  ", " CCHCC                                   CCHCC ", " CHHHC                                   CHHHC ", " CCHCC                                   CCHCC ", "  CCC                                     CCC  ", "                                               ")
                                .aisle("  FFF                                     FFF  ", " FCCCF                                   FCCCF ", "FCCHCCF                                 FCCHCCF", "FCHHHCF                                 FCHHHCF", "FCCHCCF                                 FCCHCCF", " FCCCF                                   FCCCF ", "  FFF                                     FFF  ")
                                .aisle("  CCC                                     CCC  ", " C###C                                   C###C ", "C##H##C                                 C##H##C", "C#HHH#C                                 C#HHH#C", "C##H##C                                 C##H##C", " C###C                                   C###C ", "  CCC                                     CCC  ")
                                .aisle("  CIC                                     CIC  ", " B###B                                   B###B ", "C##H##C                                 C##H##C", "I#HHH#I                                 I#HHH#I", "C##H##C                                 C##H##C", " B###B                                   B###B ", "  CIC                                     CIC  ")
                                .aisle("  CBC                                     CBC  ", " B###B                                   B###B ", "C##H##C                                 C##H##C", "B#HHH#B                                 B#HHH#B", "C##H##C                                 C##H##C", " B###B                                   B###B ", "  CBC                                     CBC  ")
                                .aisle("  CIC                                     CIC  ", " B###B                                   B###B ", "C##H##C                                 C##H##C", "I#HHH#I                                 I#HHH#I", "C##H##C                                 C##H##C", " B###B                                   B###B ", "  CIC                                     CIC  ")
                                .aisle("  CCC                                     CCC  ", " C###C                                   C###C ", "C##H##C                                 C##H##C", "C#HHH#C                                 C#HHH#C", "C##H##C                                 C##H##C", " C###C                                   C###C ", "  CCC                                     CCC  ")
                                .aisle("  FFF                                     FFF  ", " FCCCF                                   FCCCF ", "FCCHCCF                                 FCCHCCF", "FCHHHCF                                 FCHHHCF", "FCCHCCF                                 FCCHCCF", " FCCCF                                   FCCCF ", "  FFF                                     FFF  ")
                                .aisle("                                               ", "  CCC                                     CCC  ", " CCHCC                                   CCHCC ", " CHHHC                                   CHHHC ", " CCHCC                                   CCHCC ", "  CCC                                     CCC  ", "                                               ")
                                .aisle("                                               ", "   CCC                                   CCC   ", "  CCHCC                                 CCHCC  ", "  CHHHC                                 CHHHC  ", "  CCHCC                                 CCHCC  ", "   CCC                                   CCC   ", "                                               ")
                                .aisle("                                               ", "   CCC                                   CCC   ", "  CCHCC                                 CCHCC  ", "  CHHHC                                 CHHHC  ", "  CCHCC                                 CCHCC  ", "   CCC                                   CCC   ", "                                               ")
                                .aisle("                                               ", "   CCC                                   CCC   ", "  CCHCC                                 CCHCC  ", "  CHHHC                                 CHHHC  ", "  CCHCC                                 CCHCC  ", "   CCC                                   CCC   ", "                                               ")
                                .aisle("                                               ", "    CCC                                 CCC    ", "   CCHCC                               CCHCC   ", "   CHHHC                               CHHHC   ", "   CCHCC                               CCHCC   ", "    CCC                                 CCC    ", "                                               ")
                                .aisle("                                               ", "    CCC                                 CCC    ", "   CCHCC                               CCHCC   ", "   CHHHC                               CHHHC   ", "   CCHCC                               CCHCC   ", "    CCC                                 CCC    ", "                                               ")
                                .aisle("                                               ", "     CCC                               CCC     ", "    CCHCC                             CCHCC    ", "    CHHHC                             CHHHC    ", "    CCHCC                             CCHCC    ", "     CCC                               CCC     ", "                                               ")
                                .aisle("                                               ", "     CCC                               CCC     ", "    CCHCC                             CCHCC    ", "    CHHHC                             CHHHC    ", "    CCHCC                             CCHCC    ", "     CCC                               CCC     ", "                                               ")
                                .aisle("                                               ", "      CCC                             CCC      ", "     CCHCE                           ECHCC     ", "     CHHHC                           CHHHC     ", "     CCHCE                           ECHCC     ", "      CCC                             CCC      ", "                                               ")
                                .aisle("                                               ", "       CCC                           CCC       ", "      CCHCC                         CCHCC      ", "      CHHHC                         CHHHC      ", "      CCHCC                         CCHCC      ", "       CCC                           CCC       ", "                                               ")
                                .aisle("                                               ", "        CCC                         CCC        ", "       CCHCE                       ECHCC       ", "       CHHHC                       CHHHC       ", "       CCHCE                       ECHCC       ", "        CCC                         CCC        ", "                                               ")
                                .aisle("                                               ", "         CCC                       CCC         ", "        CCHCCC                   CCCHCC        ", "        CHHHCC                   CCHHHC        ", "        CCHCCC                   CCCHCC        ", "         CCC                       CCC         ", "                                               ")
                                .aisle("                                               ", "          CCCC                   CCCC          ", "         CCHCCCC               CCCCHCC         ", "         CHHHHCC               CCHHHHC         ", "         CCHCCCC               CCCCHCC         ", "          CCCC                   CCCC          ", "                                               ")
                                .aisle("                                               ", "           CCCCC               CCCCC           ", "          CCHHCCCCC FCCCCCF CCCCCHHCC          ", "          CHHHHHCCC FCIBICF CCCHHHHHC          ", "          CCHHCCCCC FCCCCCF CCCCCHHCC          ", "           CCCCC               CCCCC           ", "                                               ")
                                .aisle("                                               ", "            CCCCCCC FCBBBCF CCCCCCC            ", "           CCCHHCCCCC#####CCCCCHHCCC           ", "           CHHHHHHHCC#####CCHHHHHHHC           ", "           CCCHHCCCCC#####CCCCCHHCCC           ", "            CCCCCCC FCBBBCF CCCCCCC            ", "                                               ")
                                .aisle("                    FCCCCCF                    ", "              CCCCCCC#####CCCCCCC              ", "            CCCCHHHCC#####CCHHHCCCC            ", "            CCHHHHHHHHHHHHHHHHHHHCC            ", "            CCCCHHHCC#####CCHHHCCCC            ", "              CCCCCCC#####CCCCCCC              ", "                    FCCCCCF                    ")
                                .aisle("                    FCIBICF                    ", "                CCCCC#####CCCCC                ", "              CCCCCHHHHHHHHHCCCCC              ", "              CCHHHHHHHHHHHHHHHCC              ", "              CCCCCHHHHHHHHHCCCCC              ", "                CCCCC#####CCCCC                ", "                    FCIBICF                    ")
                                .aisle("                    FCCCCCF                    ", "                   CC#####CC                   ", "                CCCCC#####CCCCC                ", "                CCCHHHHHHHHHCCC                ", "                CCCCC#####CCCCC                ", "                   CC#####CC                   ", "                    FCCCCCF                    ")
                                .aisle("                                               ", "                    FCBBBCF                    ", "                   CC#####CC                   ", "                   CC#####CC                   ", "                   CC#####CC                   ", "                    FCBBBCF                    ", "                                               ")
                                .aisle("                                               ", "                                               ", "                    FCPPPCF                    ", "                    FCISICF                    ", "                    FCPPPCF                    ", "                                               ", "                                               ")
                                .where("S", Predicates.controller(Predicates.blocks(definition.get())))
                                .where("B", Predicates.blocks(FUSION_GLASS.get()))
                                .where("C", casing)
                                .where("P", casing.or(abilities(PartAbility.PARALLEL_HATCH)))
                                .where("I", casing.or(abilities(PartAbility.IMPORT_FLUIDS).setPreviewCount(16))
                                        .or(abilities(PartAbility.EXPORT_FLUIDS).setPreviewCount(16)))
                                .where("F", Predicates.blocks(CTNHFusionCasingType.getFrameState(tier)))
                                .where("H", Predicates.blocks(CTNHFusionCasingType.getCompressedCoilState(tier)))
                                .where("E", casing.or(abilities(PartAbility.INPUT_ENERGY)).or(abilities(PartAbility.INPUT_LASER).setPreviewCount(16)))
                                .where("#", Predicates.any())
                                .where(" ", Predicates.any())
                                .build();//结构相关代码取自GTL
                    })
                    .workableCasingModel(CTNHFusionCasingType.getCasingType(tier).getTexture(), GTCEu.id("block/multiblock/fusion_reactor"))
                    .register(),
            GTValues.LuV, GTValues.ZPM, GTValues.UV);
    public final static MultiblockMachineDefinition SWEATSHOP = REGISTRATE.multiblock("sweat_shop", FactoryMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeTypes(GTRecipeTypes.CENTRIFUGE_RECIPES, GTRecipeTypes.LATHE_RECIPES, GTRecipeTypes.BENDER_RECIPES,
                    GTRecipeTypes.MACERATOR_RECIPES, GTRecipeTypes.MIXER_RECIPES, GTRecipeTypes.EXTRACTOR_RECIPES,
                    GTRecipeTypes.WIREMILL_RECIPES, GTRecipeTypes.LASER_ENGRAVER_RECIPES, GTRecipeTypes.FLUID_SOLIDFICATION_RECIPES)
            .recipeModifiers(FactoryMachine::recipeModifier, OC_NON_PERFECT, BATCH_MODE)
            .tooltips(Component.translatable("ctnh.multiblock.sweat_shop.tooltip.0").withStyle(ChatFormatting.GRAY))
            .tooltips(Component.translatable("ctnh.multiblock.sweat_shop.tooltip.1"))
            .tooltips(Component.translatable("ctnh.multiblock.sweat_shop.tooltip.2"))
            .tooltips(Component.translatable("ctnh.multiblock.sweat_shop.tooltip.3"))
            .tooltips(Component.translatable("ctnh.multiblock.sweat_shop.tooltip.4"))
            .tooltips(Component.translatable("ctnh.multiblock.sweat_shop.tooltip.5"))
            .tooltips(Component.translatable("ctnh.multiblock.sweat_shop.tooltip.6"))
            .tooltips(Component.translatable("ctnh.multiblock.sweat_shop.tooltip.7"))
            .tooltips(Component.translatable("ctnh.multiblock.sweat_shop.tooltip.8"))
            .appearanceBlock(CASING_STEEL_SOLID)
            .pattern(definition -> FactoryBlockPattern.start(RelativeDirection.LEFT, RelativeDirection.UP, RelativeDirection.BACK)
                    .aisle("aaaaa", "aadaa", "aaaaa", "aaaaa")
                    .aisle("ccccc", "a b a", "e   e", "ccccc").setRepeatable(3, 16)
                    .aisle("aaaaa", "aaaaa", "aaaaa", "aaaaa")
                    .where("a", Predicates.blocks(CASING_STEEL_SOLID.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1)))
                    .where("b", Predicates.blocks(AllBlocks.ANDESITE_CASING.get()))
                    .where("c", Predicates.blocks(CASING_STEEL_SOLID.get()))
                    .where("d", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("e", Predicates.blocks(Blocks.IRON_BARS))
                    .where(" ", Predicates.any())
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_solid_steel"), GTCEu.id("block/multiblock/vacuum_freezer"))
            .register();

    public final static MultiblockMachineDefinition PLASMA_CONDENSER = REGISTRATE.multiblock("plasma_condenser", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(CTNHRecipeTypes.PLASMA_CONDENSER_RECIPES)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH, OC_NON_PERFECT, BATCH_MODE)
            .tooltips(Component.translatable("ctnh.multiblock.plasma_condenser.tooltip.1").withStyle(ChatFormatting.GRAY),
                    Component.translatable("gtceu.multiblock.laser.tooltip"),
                    Component.translatable("gtceu.multiblock.parallelizable.tooltip"))
            .appearanceBlock(CTNHBlocks.CASING_ANTIFREEZE_HEATPROOF_MACHINE)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("#####AAA#####", "#####AAA#####", "#####AAA#####")
                    .aisle("####AAAAA####", "####BCCCB####", "####AAAAA####")
                    .aisle("##AAAAAAAAA##", "##AACABACAA##", "##AAAAAAAAA##")
                    .aisle("##AAA###AAA##", "##ACA###ACA##", "##AAA###AAA##")
                    .aisle("#AAA#####AAA#", "#BCA#####ACB#", "#AAA#####AAA#")
                    .aisle("AAA#######AAA", "ACA#######ACA", "AAA#######AAA")
                    .aisle("AAA#######AAA", "ACB#######BCA", "AAA#######AAA")
                    .aisle("AAA#######AAA", "ACA#######ACA", "AAA#######AAA")
                    .aisle("#AAA#####AAA#", "#BCA#####ACB#", "#AAA#####AAA#")
                    .aisle("##AAA###AAA##", "##ACA###ACA##", "##AAA###AAA##")
                    .aisle("##AAAAAAAAA##", "##AACABACAA##", "##AAAAAAAAA##")
                    .aisle("####AAAAA####", "####BCCCB####", "####AAAAA####")
                    .aisle("#####AAA#####", "#####A@A#####", "#####AAA#####")
                    .where("A", Predicates.blocks(CTNHBlocks.CASING_ANTIFREEZE_HEATPROOF_MACHINE.get()).setMinGlobalLimited(160)
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                            .or(abilities(PartAbility.PARALLEL_HATCH))
                            .or(abilities(PartAbility.INPUT_LASER))
                            .or(abilities(PartAbility.INPUT_ENERGY)))
                    .where("#", Predicates.any())
                    .where("B", Predicates.blocks(FUSION_GLASS.get()))
                    .where("C", Predicates.blocks(CTNHBlocks.PLASMA_COOLED_CORE.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel(CTNHCore.id("block/casings/antifreeze_heatproof_machine_casing"),
                    GTCEu.id("block/multiblock/fusion_reactor"))
            .register();



    public final static MultiblockMachineDefinition MEADOW = REGISTRATE.multiblock("meadow", MeadowMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.MEADOW)
            .recipeModifier(RecipeModifier.NO_MODIFIER)
            .tooltips(Component.translatable("ctnh.multiblock.meadow.tooltip.0"),
                    Component.translatable("ctnh.multiblock.meadow.tooltip.1"),
                    Component.translatable("ctnh.multiblock.meadow.tooltip.2"),
                    Component.translatable("ctnh.multiblock.meadow.tooltip.3")
            )
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("BBBBBBBBBBB", "JCCCJCCCCCC", "J###J######", "JJJJJD#####", "EEEEE######", "###########")
                    .aisle("BBBBFFFBBBB", "CEE####GG#C", "#E#####GG##", "J###JD#####", "EEEEE######", "#EEE#######")
                    .aisle("BBBBFFFBBBB", "CE#####GG#C", "###########", "J###JD#####", "EEEEE######", "#EEE#######")
                    .aisle("BBBBFFFBBBB", "C#######G#C", "###########", "J###JD#####", "EEEEE######", "#EEE#######")
                    .aisle("BBBBBFFBBBB", "J###J#####C", "J###J######", "JJJJJD#####", "EEEEE######", "###########")
                    .aisle("BEEBFFFHHHB", "C#########C", "###########", "DDDDDD#####", "###########", "###########")
                    .aisle("BEEBFFFHHHB", "C######II#C", "###########", "###########", "###########", "###########")
                    .aisle("BEEBFFFHHHB", "C#######I#C", "###########", "###########", "###########", "###########")
                    .aisle("BEEBFFFBHHB", "C#########C", "###########", "###########", "###########", "###########")
                    .aisle("BEEBFFFBHHB", "C########IC", "###########", "###########", "###########", "###########")
                    .aisle("BBBBB@BBBBB", "CCCCCCCCCCC", "###########", "###########", "###########", "###########")
                    .where("B", Predicates.blocks(Blocks.DIRT)
                            .or(Predicates.blocks(Blocks.GRASS_BLOCK))
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(abilities(CTPPPartAbility.INPUT_KINETIC)))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("C", Predicates.blocks(Blocks.OAK_FENCE))
                    .where("#", Predicates.any())
                    .where("D", Predicates.blocks(Blocks.OAK_STAIRS))
                    .where("E", Predicates.blocks(Blocks.HAY_BLOCK))
                    .where("F", Predicates.blocks(Blocks.DIRT_PATH))
                    .where("G", Predicates.blocks(Blocks.BONE_BLOCK))
                    .where("H", Predicates.blocks(Blocks.WATER))
                    .where("I", Predicates.blocks(Blocks.LILY_PAD))
                    .where("J", Predicates.blocks(Blocks.OAK_LOG))
                    .build()
            )
            .workableCasingModel(ResourceLocation.tryParse("minecraft:block/dirt"), GTCEu.id("block/multiblock/implosion_compressor"))
            .register();

    public final static MultiblockMachineDefinition LARGE_BOTTLE = REGISTRATE.multiblock("large_bottle", holder -> new LargeBottleMachine(holder, 10000 * 1000, null))
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(DUMMY_RECIPES)
            .tooltips(Component.translatable("ctnh.multiblock.large_bottle.tooltip.0").withStyle(ChatFormatting.GRAY),
                    Component.translatable("ctnh.multiblock.large_bottle.tooltip.1"),
                    Component.translatable("ctnh.multiblock.large_bottle.tooltip.2"))
            .appearanceBlock(GTBlocks.CASING_STEEL_SOLID)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("##AAAAA##", "##BBBBB##", "##BBBBB##", "##BBBBB##", "##CCCCC##", "##BBBBB##", "##BBBBB##", "##BBBBB##", "#########", "#########", "#########", "#########", "#########", "#########", "#########")
                    .aisle("#AAAAAAA#", "#B#####B#", "#B#####B#", "#BAAAAAB#", "#C#####C#", "#B#####B#", "#B#####B#", "#B#####B#", "#BBBBBBB#", "###BBB###", "#########", "#########", "#########", "#########", "#########")
                    .aisle("AAAAAAAAA", "B#######B", "B#######B", "BAAAAAAAB", "C#######C", "B#######B", "B#######B", "B#######B", "#B#####B#", "##BBBBB##", "###CCC###", "###BBB###", "###BBB###", "###BBB###", "###AAA###")
                    .aisle("AAAAAAAAA", "B#######B", "B#######B", "BAAAAAAAB", "C#######C", "B#######B", "B#######B", "B#######B", "#B#####B#", "#BB###BB#", "##CDDDC##", "##B###B##", "##B###B##", "##B###B##", "##AEEEA##")
                    .aisle("AAAAAAAAA", "B###E###B", "B###E###B", "BAAAEAAAB", "C###E###C", "B###E###B", "B###E###B", "B###E###B", "#B##E##B#", "#BB#E#BB#", "##CDEDC##", "##B###B##", "##B###B##", "##B###B##", "##AEEEA##")
                    .aisle("AAAAAAAAA", "B#######B", "B#######B", "BAAAAAAAB", "C#######C", "B#######B", "B#######B", "B#######B", "#B#####B#", "#BB###BB#", "##CDDDC##", "##B###B##", "##B###B##", "##B###B##", "##AEEEA##")
                    .aisle("AAAAAAAAA", "B#######B", "B#######B", "BAAAAAAAB", "C#######C", "B#######B", "B#######B", "B#######B", "#B#####B#", "##BBBBB##", "###CCC###", "###BBB###", "###BBB###", "###BBB###", "###AAA###")
                    .aisle("#AAAAAAA#", "#B#####B#", "#B#####B#", "#BAAAAAB#", "#C#####C#", "#B#####B#", "#B#####B#", "#B#####B#", "#BBBBBBB#", "###BBB###", "#########", "#########", "#########", "#########", "#########")
                    .aisle("##AA@AA##", "##BBBBB##", "##BBBBB##", "##BBBBB##", "##CCCCC##", "##BBBBB##", "##BBBBB##", "##BBBBB##", "#########", "#########", "#########", "#########", "#########", "#########", "#########")
                    .where("#", Predicates.any())
                    .where("A", Predicates.blocks(CASING_STEEL_SOLID.get()))
                    .where("B", Predicates.blocks(CASING_TEMPERED_GLASS.get()))
                    .where("C", Predicates.heatingCoils())
                    .where("D", Predicates.blocks(FILTER_CASING.get()))
                    .where("E", Predicates.blocks(CASING_TITANIUM_PIPE.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build()
            )
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_solid_steel"), GTCEu.id("block/multiblock/implosion_compressor"))
            .register();
    public final static MultiblockMachineDefinition FERMENTING_TANK = REGISTRATE.multiblock("fermenting_tank", FermentingTankMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.FERMENTING)
            .tooltips(Component.translatable("ctnh.multiblock.fermenting_tank.tooltip.0").withStyle(ChatFormatting.GRAY),
                    Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.0"),
                    Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.1"),
                    Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.2"),
                    CTNHCommonTooltips.SUBTICK_PARALLEL,
                    Component.literal("=========================================================="),
                    Component.translatable("ctnh.multiblock.fermenting_tank.tooltip.1").withStyle(ChatFormatting.GREEN),
                    Component.translatable("ctnh.multiblock.fermenting_tank.tooltip.2"),
                    Component.translatable("ctnh.multiblock.fermenting_tank.tooltip.3"))
            .recipeModifiers(FermentingTankMachine::recipeModifier, GTRecipeModifiers::ebfOverclock)
            .appearanceBlock(GTBlocks.CASING_STEEL_SOLID)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("C   C", "C   C", "CCCCC", "H   H", "H   H", "H   H", "DAAAD")
                    .aisle("     ", " GGG ", "CGGGC", " MMM ", " GGG ", " GGG ", "AAAAA")
                    .aisle("     ", " GGG ", "CG GC", " M M ", " G G ", " G G ", "AABAA")
                    .aisle("     ", " GGG ", "CGGGC", " MMM ", " GGG ", " GGG ", "AAAAA")
                    .aisle("C   C", "CAKAC", "CAAAC", "H   H", "H   H", "H   H", "DAAAD")
                    .where("C", Predicates.frames(GTMaterials.Steel))
                    .where("H", Predicates.blocks(AllBlocks.METAL_GIRDER.get()))
                    .where("K", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("M", Predicates.heatingCoils())
                    .where("D", Predicates.blocks(GTBlocks.CASING_STEEL_SOLID.get()))
                    .where("B", abilities(PartAbility.MUFFLER).setExactLimit(1))
                    .where("A", Predicates.blocks(GTBlocks.CASING_STEEL_SOLID.get()).setMinGlobalLimited(15)
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(abilities(PartAbility.IMPORT_FLUIDS).setExactLimit(1))
                            .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                    )
                    .where("G", Predicates.blocks(GTBlocks.CASING_TEMPERED_GLASS.get()))
                    .where(" ", Predicates.air())
                    .build()
            )
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_solid_steel"), GTCEu.id("block/multiblock/implosion_compressor"))
            .register();

    public final static MultiblockMachineDefinition LARGE_FERMENTING_TANK = REGISTRATE.multiblock("large_fermenting_tank", LargeFermentingTankMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.FERMENTING)
            .tooltips(Component.translatable("ctnh.multiblock.large_fermenting_tank.tooltip.0").withStyle(ChatFormatting.GRAY),
                    Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.0"),
                    Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.1"),
                    Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.2"),
                    CTNHCommonTooltips.SUBTICK_PARALLEL,
                    Component.literal("=========================================================="),
                    Component.translatable("ctnh.multiblock.fermenting_tank.tooltip.1").withStyle(ChatFormatting.GREEN),
                    Component.translatable("ctnh.multiblock.fermenting_tank.tooltip.2"),
                    Component.translatable("ctnh.multiblock.fermenting_tank.tooltip.3"),
                    Component.translatable("ctnh.multiblock.large_fermenting_tank.tooltip.1"))
            .recipeModifiers((machine, group, recipe) -> {
                var failure = FermentingTankMachine.recipeModifier(machine, group, recipe);
                return failure != null ? failure : CTNHRecipeModifiers.accurateParallel(machine, group, recipe, 8);
            }, GTRecipeModifiers::ebfOverclock)
            .appearanceBlock(GTBlocks.CASING_STEEL_SOLID)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("##########AAAAAAAAAAA", "##########ABBBBBBBBBA", "##########ABBBBBBBBBA", "##########AAAAAAAAAAA", "#####################", "#####################", "#####################", "#####################", "#####################", "#####################", "#####################", "#####################", "#####################", "#####################", "############AAAAAAA##", "############AABBBAA##", "############AABBBAA##", "############AABBBAA##", "############AAAAAAA##")
                    .aisle("##########AAAAAAAAAAA", "##########B#########B", "##########B#########B", "##########AAAAAAAAAAA", "############CCCCCCC##", "#####################", "#####################", "#####################", "#####################", "#####################", "#####################", "#####################", "#####################", "############CCCCCCC##", "###########DABBBBBAD#", "###########DB#####BD#", "###########DB#####BD#", "###########DB#####BD#", "###########DAAAAAAAD#")
                    .aisle("##########AAAAAAAAAAA", "##########B#########B", "##########B#########B", "##########AAAAAAAAAAA", "###########CCDAAADCC#", "#############D###D###", "#############D###D###", "#############D###D###", "#############D###D###", "#############D###D###", "#############D###D###", "#############D###D###", "#############D###D###", "###########CCDAAADCC#", "##########AAABBBBBAAA", "##########ABB#####BBA", "##########ABB#####BBA", "##########ABB#####BBA", "##########AAAAAAAAAAA")
                    .aisle("##########AAAAAAAAAAA", "##########B#########B", "##########B#########B", "##########AAAAAAAAAAA", "###########CDAAAAADC#", "############DBBBBBD##", "############DBBBBBD##", "############DBBBBBD##", "############DFFFFFD##", "############DFFFFFD##", "############DBBBBBD##", "############DBBBBBD##", "############DBBBBBD##", "###########CDAAAAADC#", "##########ABBBBBBBBBA", "AAAAA#####A#########A", "ABBBA#####A#########A", "AAAAA#####A#########A", "##########AAAAAAAAAAA")
                    .aisle("##########AAAAAAAAAAA", "##########B#########B", "##########B#########B", "##########AAAAAAAAAAA", "###########CAAFFFAAC#", "#############B###B###", "#############B###B###", "#############B###B###", "#############F###F###", "#############F###F###", "#############B###B###", "#############B###B###", "#############B###B###", "###########CAAFFFAAC#", "##########ABBBBBBBBBA", "AHHHAAAAAAA#########B", "B###BBBBBBB#########B", "AAAAAAAAAAA#########B", "##########AAAAAAAAAAA")
                    .aisle("##########AAAAAAAAAAA", "##########B#########B", "##########B#########B", "##########AAAAAAAAAAA", "###########CAAFFFAAC#", "#############B#H#B###", "#############B#H#B###", "#############B#H#B###", "#############F#H#F###", "#############F#H#F###", "#############B#H#B###", "#############B#H#B###", "#############B#H#B###", "###########CAAFIFAAC#", "##########ABBBBBBBBBA", "AHHHBBBBBBB#########B", "B###################B", "AAAAABBBBBB#########B", "##########AAAAAAAAAAA")
                    .aisle("##########AAAAAAAAAAA", "##########B#########B", "##########B#########B", "##########AAAAAAAAAAA", "###########CAAFFFAAC#", "#############B###B###", "#############B###B###", "#############B###B###", "#############F###F###", "#############F###F###", "#############B###B###", "#############B###B###", "#############B###B###", "###########CAAFFFAAC#", "##########ABBBBBBBBBA", "AHHHAAAAAAA#########B", "B###BBBBBBB#########B", "AAAAAAAAAAA#########B", "##########AAAAAAAAAAA")
                    .aisle("##########AAAAAAAAAAA", "##########B#########B", "##########B#########B", "##########AAAAAAAAAAA", "###########CDAAAAADC#", "############DBBBBBD##", "############DBBBBBD##", "############DBBBBBD##", "############DFFFFFD##", "############DFFFFFD##", "############DBBBBBD##", "############DBBBBBD##", "############DBBBBBD##", "###########CDAAAAADC#", "##########ABBBBBBBBBA", "AAAAA#####A#########A", "ABBBA#####A#########A", "AAAAA#####A#########A", "##########AAAAAAAAAAA")
                    .aisle("##########AAAAAAAAAAA", "##########B#########B", "##########B#########B", "##########AAAAAAAAAAA", "###########CCDAAADCC#", "#############D###D###", "#############D###D###", "#############D###D###", "#############D###D###", "#############D###D###", "#############D###D###", "#############D###D###", "#############D###D###", "###########CCDAAADCC#", "##########AAABBBBBAAA", "##########ABB#####BBA", "##########ABB#####BBA", "##########ABB#####BBA", "##########AAAAAAAAAAA")
                    .aisle("##########AAAAAAAAAAA", "##########B#########B", "##########B#########B", "##########AAAAAAAAAAA", "############CCCCCCC##", "#####################", "#####################", "#####################", "#####################", "#####################", "#####################", "#####################", "#####################", "############CCCCCCC##", "###########DABBBBBAD#", "###########DB#####BD#", "###########DB#####BD#", "###########DB#####BD#", "###########DAAAAAAAD#")
                    .aisle("##########AJJJJ@JJJJA", "##########AJJJJJJJJJA", "##########AJJJJJJJJJA", "##########AJJJJJJJJJA", "#####################", "#####################", "#####################", "#####################", "#####################", "#####################", "#####################", "#####################", "#####################", "#####################", "############AAAAAAA##", "############AABBBAA##", "############AABBBAA##", "############AABBBAA##", "############AAAAAAA##")
                    .where("#", Predicates.any())
                    .where("A", Predicates.blocks(CASING_STEEL_SOLID.get()))
                    .where("B", Predicates.blocks(CASING_TEMPERED_GLASS.get()))
                    .where("C", Predicates.blocks(Blocks.SMOOTH_STONE_SLAB))
                    .where("D", Predicates.frames(GTMaterials.Invar))
                    .where("F", Predicates.heatingCoils())
                    .where("H", Predicates.blocks(CASING_TITANIUM_PIPE.get()))
                    .where("I", Predicates.blocks(CASING_TITANIUM_GEARBOX.get()))
                    .where("J", Predicates.blocks(CASING_STEEL_SOLID.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1)))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build()
            )
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_solid_steel"), GTCEu.id("block/multiblock/implosion_compressor"))
            .register();
    public final static MultiblockMachineDefinition DIGESTION_TANK = REGISTRATE.multiblock("digestion_tank", DigestingTankMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.DIGESTING)
            .tooltips(Component.translatable("ctnh.multiblock.digestion_tank.tooltip.0").withStyle(ChatFormatting.GRAY),
                    Component.translatable("ctnh.multiblock.digestion_tank.tooltip.1").withStyle(ChatFormatting.GREEN),
                    Component.translatable("ctnh.multiblock.digestion_tank.tooltip.2"))
            .recipeModifiers(BioMachine::recipeModifier, OC_NON_PERFECT, BATCH_MODE)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("CCCCC", "CAAAC", "CCCCC")
                    .aisle("CCCCC", "AWWWA", "CDDDC")
                    .aisle("CCCCC", "CAKAC", "CGGGC")
                    .where("C", Predicates.blocks(Blocks.BRICKS))
                    .where("K", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("D", Predicates.blocks(Blocks.IRON_TRAPDOOR))
                    .where("A", Predicates.blocks(Blocks.BRICKS)
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                    )
                    .where("G", Predicates.blocks(GTBlocks.CASING_TEMPERED_GLASS.get()))
                    .where("W", Predicates.blocks(Blocks.WATER))
                    .build()
            )
            .workableCasingModel(ResourceLocation.tryParse("minecraft:block/bricks"), GTCEu.id("block/multiblock/implosion_compressor"))
            .register();
    public final static MultiblockMachineDefinition BLAZE_BLAST_FURNACE = REGISTRATE.multiblock("blaze_blast_furnace", BlazeBlastFurnaceMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTRecipeTypes.BLAST_RECIPES)
            .recipeModifiers(BlazeBlastFurnaceMachine::recipeModifier, GTRecipeModifiers::ebfOverclock, BATCH_MODE)
            .appearanceBlock(CTNHBlocks.BLAZE_BLAST_FURNACE_CASING)
            .tooltips(Component.translatable("ctnh.multiblock.blaze_blast_furnace.tooltip.0").withStyle(ChatFormatting.GRAY),
                    Component.translatable("ctnh.multiblock.blaze_blast_furnace.tooltip.1"),
                    Component.translatable("ctnh.multiblock.blaze_blast_furnace.tooltip.2"),
                    Component.translatable("ctnh.multiblock.blaze_blast_furnace.tooltip.3").withStyle(ChatFormatting.DARK_GREEN),
                    Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.0"),
                    Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.1"),
                    Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.2"))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("GGG", "MMM", "MMM", "GGG")
                    .aisle("GGG", "M M", "M M", "GBG")
                    .aisle("GKG", "MMM", "MMM", "GGG")
                    .where("K", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("M", Predicates.heatingCoils())
                    .where("B", abilities(PartAbility.MUFFLER).setExactLimit(1))
                    .where("G", Predicates.blocks(CTNHBlocks.BLAZE_BLAST_FURNACE_CASING.get()).setMinGlobalLimited(4)
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                    )
                    .where(" ", Predicates.air())
                    .build()
            )
            .workableCasingModel(CTNHCore.id("block/casings/blaze_blast_furnace_casing"), GTCEu.id("block/multiblock/implosion_compressor"))
            .register();

    // Come from GTCA
    public static final MultiblockMachineDefinition SUPER_EBF = REGISTRATE
            .multiblock("super_ebf", CoilWorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTRecipeTypes.BLAST_RECIPES)
            .recipeModifiers(PARALLEL_HATCH, (machine, group, recipe) -> {
                recipe.multiplyDuration(0.5);
                return null;
            }, GTRecipeModifiers::ebfOverclock, BATCH_MODE)
            .appearanceBlock(CASING_STAINLESS_CLEAN)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("XXXXXXX", "FFXXXFF", "F#####F", "F#####F", "F#####F", "FFXXXFF", "XXXVXXX", "##XXX##", "#######")
                    .aisle("XXXXXXX", "FXCCCXF", "##CCC##", "##III##", "##CCC##", "FXCCCXF", "XXXXXXX", "#XXXXX#", "##XXX##")
                    .aisle("XXXXXXX", "XCC#CCX", "#CC#CC#", "#I###I#", "#CC#CC#", "XCC#CCX", "XXXXXXX", "XXXHXXX", "#X###X#")
                    .aisle("XXXXXXX", "XC###CX", "#C###C#", "#I###I#", "#C###C#", "XC###CX", "VXXXXXV", "XXHHHXX", "#X###X#")
                    .aisle("XXXXXXX", "XCC#CCX", "#CC#CC#", "#I###I#", "#CC#CC#", "XCC#CCX", "XXXXXXX", "XXXHXXX", "#X###X#")
                    .aisle("XXXXXXX", "FXCCCXF", "##CCC##", "##III##", "##CCC##", "FXCCCXF", "XXXXXXX", "#XXXXX#", "##XXX##")
                    .aisle("XXXSXXX", "FFXXXFF", "F#####F", "F#####F", "F#####F", "FFXXXFF", "XXXVXXX", "##XXX##", "#######")
                    .where("S", Predicates.controller(Predicates.blocks(definition.getBlock())))
                    .where("F", Predicates.frames(GTMaterials.Tungsten))
                    .where("V", Predicates.blocks(GTBlocks.CASING_EXTREME_ENGINE_INTAKE.get()))
                    .where("I", Predicates.blocks(HEAT_VENT.get()))
                    .where("X", Predicates.blocks(CASING_STAINLESS_CLEAN.get()).setMinGlobalLimited(158)
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.autoAbilities(true, true, true)))
                    .where("H", abilities(PartAbility.MUFFLER))
                    .where("C", Predicates.heatingCoils())
                    .where("#", Predicates.any())
                    .build()
            )
            .recoveryItems(
                    () -> new ItemLike[]{MATERIAL_ITEMS.get(TagPrefix.dustTiny, GTMaterials.Ash).get()})
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"), CTNHCore.id("block/overlay/super_ebf"))
            .tooltips(
                    CTNHCommonTooltips.PARALLEL_HATCH,
                    Component.translatable("ctnh.multiblock.super_ebf.tooltip.0")
            )
            .additionalDisplay((controller, components) -> {
                if (controller instanceof CoilWorkableElectricMultiblockMachine coilMachine && controller.isFormed()) {
                    components.add(Component.translatable("gtceu.multiblock.blast_furnace.max_temperature",
                            Component.translatable(FormattingUtil.formatNumbers(coilMachine.getCoilType().getCoilTemperature() +
                                            100L * Math.max(0, coilMachine.getTier() - MV)) + "K")
                                    .withStyle(ChatFormatting.RED)));
                }
            })
            .register();
    //Come from GTCA
    public static final MultiblockMachineDefinition MEGA_OIL_CRACKING_UNIT = REGISTRATE
            .multiblock("mega_oil_cracking_unit", CoilWorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.CRACKING_RECIPES)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH, GTRecipeModifiers::crackerOverclock, BATCH_MODE)
            .appearanceBlock(CASING_STAINLESS_CLEAN)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("HHHHHHHHHHHHH", "#H#########H#", "#H#########H#", "#H#########H#", "#H#########H#", "#H#########H#", "#H#########H#")
                    .aisle("HHHHHHHHHHHHH", "HGGGGGGGGGGGH", "HGGGGGGGGGGGH", "HGGGGGGGGGGGH", "HGGGGGGGGGGGH", "HGGGGGGGGGGGH", "HHGGGGGGGGGHH")
                    .aisle("HHHHHHHHHHHHH", "#G#C#C#C#C#G#", "#G#C#C#C#C#G#", "#G#C#C#C#C#G#", "#G#C#C#C#C#G#", "#G#C#C#C#C#G#", "#HGGGGGGGGGH#")
                    .aisle("HHHHHHHHHHHHH", "#G#C#C#C#C#G#", "#H#C###C###H#", "#H#C#C#C#C#H#", "#H#C###C###H#", "#G#C#C#C#C#G#", "#HGGGHHHGGGH#")
                    .aisle("HHHHHHHHHHHHH", "#G#C#C#C#C#G#", "#H#C#C#C#C#H#", "#O#C#C#C#C#I#", "#H#C#C#C#C#H#", "#G#C#C#C#C#G#", "#HGGGHAHGGGH#")
                    .aisle("HHHHHHHHHHHHH", "#G#C#C#C#C#G#", "#H#C###C###H#", "#H#C#C#C#C#H#", "#H#C###C###H#", "#G#C#C#C#C#G#", "#HGGGHHHGGGH#")
                    .aisle("HHHHHHHHHHHHH", "#G#C#C#C#C#G#", "#G#C#C#C#C#G#", "#G#C#C#C#C#G#", "#G#C#C#C#C#G#", "#G#C#C#C#C#G#", "#HGGGGGGGGGH#")
                    .aisle("HHHHHHHHHHHHH", "HGGGGGGGGGGGH", "HGGGGGGGGGGGH", "HGGGGGGGGGGGH", "HGGGGGGGGGGGH", "HGGGGGGGGGGGH", "HHGGGGGGGGGHH")
                    .aisle("HHHHHHXHHHHHH", "#H#########H#", "#H#########H#", "#H#########H#", "#H#########H#", "#H#########H#", "#H#########H#")
                    .where("X", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("H", Predicates.blocks(CASING_STAINLESS_CLEAN.get()).setMinGlobalLimited(12)
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.autoAbilities(true, true, true)))
                    .where("#", Predicates.any())
                    .where("C", Predicates.heatingCoils())
                    .where("G", Predicates.blocks(CASING_LAMINATED_GLASS.get()))
                    .where("I", abilities(PartAbility.IMPORT_FLUIDS))
                    .where("A", abilities(PartAbility.IMPORT_FLUIDS))
                    .where("O", abilities(PartAbility.EXPORT_FLUIDS))
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"), GTCEu.id("block/multiblock/cracking_unit"))
            .tooltips(
                    CTNHCommonTooltips.PARALLEL_HATCH,
                    Component.translatable("gtceu.machine.cracker.tooltip.1")
            )
            .additionalDisplay((controller, components) -> {
                if (controller instanceof CoilWorkableElectricMultiblockMachine coilMachine && controller.isFormed()) {
                    components.add(Component.translatable("gtceu.multiblock.cracking_unit.energy",
                            100 - 10 * coilMachine.getCoilTier()));
                }
            })
            .register();
    //Come from GTCA
    public static final MultiblockMachineDefinition MEGA_LCR = REGISTRATE
            .multiblock("mega_lcr", RecipeElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeTypes(GTRecipeTypes.LARGE_CHEMICAL_RECIPES)
            .appearanceBlock(CASING_PTFE_INERT)
            .recipeModifiers(GTRecipeModifiers.OC_PERFECT_SUBTICK, GTRecipeModifiers.BATCH_MODE)
            .pattern(definition ->
                    FactoryBlockPattern.start()
                            .aisle("CCCCC", "CCCCC", "CCCCC", "CCCCC", "CCCCC")
                            .aisle("CCCCC", "G#N#G", "G#P#G", "G#N#G", "CCCCC")
                            .aisle("CCCCC", "G#N#G", "G#P#G", "G#N#G", "CCCCC")
                            .aisle("CCCCC", "G#N#G", "G#P#G", "G#N#G", "CCCCC")
                            .aisle("CCCCC", "G#N#G", "G#P#G", "G#N#G", "CCCCC")
                            .aisle("CCCCC", "G#N#G", "G#P#G", "G#N#G", "CCCCC")
                            .aisle("CCCCC", "G#N#G", "G#P#G", "G#N#G", "CCCCC")
                            .aisle("CCCCC", "G#N#G", "G#P#G", "G#N#G", "CCCCC")
                            .aisle("CCCCC", "CGCGC", "CGEGC", "CGCGC", "CCCCC")
                            .where("E", Predicates.controller(Predicates.blocks(definition.get())))
                            .where("N", Predicates.heatingCoils())
                            .where("G", Predicates.blocks(CASING_LAMINATED_GLASS.get()))
                            .where("P", Predicates.blocks(CASING_POLYTETRAFLUOROETHYLENE_PIPE.get()))
                            .where("#", Predicates.air())
                            .where("C", Predicates.blocks(CASING_PTFE_INERT.get()).setMinGlobalLimited(80)
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes(),false, false, true, true, true,
                                            true))
                                    .or(Predicates.autoAbilities(true, true, false))
                                    .or(abilities(PartAbility.INPUT_ENERGY).setMinGlobalLimited(1)
                                            .setMaxGlobalLimited(4).setPreviewCount(4))
                            )
                            .build()
            )
            .workableCasingModel(
                    GTCEu.id("block/casings/solid/machine_casing_inert_ptfe"),
                    CTNHCore.id("block/overlay/super_ebf"))
            .tooltips(
                    CTNHCommonTooltips.PERFECT_OVERCLOCK,
                    Component.translatable("ctnh.multiblock.mega_lcr.tooltip.0"),
                    Component.translatable("ctnh.multiblock.mega_lcr.tooltip.1")
            )
            .register();
    public static final MultiblockMachineDefinition IV_CHEMICAL_GENERATOR = registerChemicalGenerator(
            "iv_chemical_generator", IV,
            CASING_TUNGSTENSTEEL_TURBINE, CASING_TUNGSTENSTEEL_GEARBOX, FIREBOX_TUNGSTENSTEEL, CASING_TUNGSTENSTEEL_PIPE,
            GTCEu.id("block/casings/mechanic/machine_casing_turbine_tungstensteel"),
            CTNHCore.id("block/overlay/super_chemical"));

    public static final MultiblockMachineDefinition EV_CHEMICAL_GENERATOR = registerChemicalGenerator(
            "ev_chemical_generator", EV,
            CASING_TITANIUM_TURBINE, CASING_TITANIUM_GEARBOX, FIREBOX_TITANIUM, CASING_TITANIUM_PIPE,
            GTCEu.id("block/casings/mechanic/machine_casing_turbine_titanium"),
            CTNHCore.id("block/overlay/super_chemical"));

    //Comes from GTCA
    public static MultiblockMachineDefinition registerChemicalGenerator(String name, int tier,
                                                                        Supplier<? extends Block> casing,
                                                                        Supplier<? extends Block> gear,
                                                                        Supplier<? extends Block> firebox,
                                                                        Supplier<? extends Block> pipe,
                                                                        ResourceLocation casingTexture,
                                                                        ResourceLocation overlayModel) {
        return REGISTRATE.multiblock(name, holder -> new ChemicalGeneratorMachine(holder, tier))
                .rotationState(RotationState.NON_Y_AXIS)
                .recipeType(CTNHRecipeTypes.CHEMICAL_GENERATOR)
                .generator(true)
                .recipeModifier(ChemicalGeneratorMachine::recipeModifier, true)
                .appearanceBlock(casing)
                .pattern(definition -> FactoryBlockPattern.start()
                        .aisle("III", "PPP", "III")
                        .aisle("III", "P#P", "III")
                        .aisle("III", "PPP", "III")
                        .aisle("III", "CSC", "III")
                        .aisle("III", "FGF", "III")
                        .aisle("IMI", "IDI", "III")
                        .where("M", Predicates.controller(Predicates.blocks(definition.getBlock())))
                        .where("P", Predicates.blocks(GTBlocks.CASING_PTFE_INERT.get()))
                        .where("#", Predicates.blocks(GTBlocks.CASING_POLYTETRAFLUOROETHYLENE_PIPE.get()))
                        .where("C", Predicates.blocks(GTBlocks.COIL_CUPRONICKEL.get()))
                        .where("S", Predicates.blocks(pipe.get()))
                        .where("F", Predicates.blocks(firebox.get()))
                        .where("G", Predicates.blocks(gear.get()))
                        .where("I", Predicates.blocks(casing.get()).setMinGlobalLimited(30)
                                .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                                .or(Predicates.autoAbilities(true, true, true)))
                        .where("D",
                                Predicates.ability(PartAbility.OUTPUT_ENERGY,
                                                Stream.of(ULV, LV, MV, HV, EV, IV, LuV, ZPM, UV, UHV).filter(t -> t >= tier)
                                                        .mapToInt(Integer::intValue).toArray())
                                        .addTooltips(Component.translatable("gtceu.multiblock.pattern.error.limited.1",
                                                GTValues.VN[tier])))
                        .build())
                .recoveryItems(
                        () -> new ItemLike[]{MATERIAL_ITEMS.get(TagPrefix.dustTiny, GTMaterials.Ash).get()})
                .workableCasingModel(casingTexture, overlayModel)
                .tooltips(
                        Component.translatable("gtceu.universal.tooltip.base_production_eut", V[tier]),
                        tier > EV ?
                                Component.translatable("gtceu.machine.large_combustion_engine.tooltip.boost_extreme",
                                        V[tier] * 4) :
                                Component.translatable("gtceu.machine.large_combustion_engine.tooltip.boost_regular",
                                        V[tier] * 3))
                .register();
    }

    public static final MultiblockMachineDefinition INDUSTRIAL_PRIMITIVE_BLAST_FURNACE = REGISTRATE.multiblock("industrial_primitive_blast_furnace", IndustrialPrimitiveBlastFurnaceMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTRecipeTypes.PRIMITIVE_BLAST_FURNACE_RECIPES)
            .appearanceBlock(CASING_PRIMITIVE_BRICKS)
            .tooltips(Component.translatable("ctnh.multiblock.industrial_primitive_blast_furnace.tooltip.0").withStyle(ChatFormatting.GRAY),
                    Component.translatable("ctnh.multiblock.industrial_primitive_blast_furnace.tooltip.1"),
                    Component.translatable("ctnh.multiblock.industrial_primitive_blast_furnace.tooltip.2").withStyle(ChatFormatting.GREEN),
                    Component.translatable("ctnh.multiblock.industrial_primitive_blast_furnace.tooltip.3").withStyle(ChatFormatting.GREEN))
            .recipeModifiers(IndustrialPrimitiveBlastFurnaceMachine::recipeModifier, BATCH_MODE)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("CAAAC", " CCC ", " CCC ", " CCC ", "  C  ", "  C  ", "  C  ")
                    .aisle("ABBBA", "C   C", "C   C", "C   C", " C C ", " C C ", " C C ")
                    .aisle("CCBCC", "FC CF", "FC CF", "FC CF", "  C  ", "  C  ", "  C  ")
                    .aisle("CAAAC", " AKA ", " CAC ", " CCC ", "     ", "     ", "     ")
                    .where("C", Predicates.blocks(CASING_PRIMITIVE_BRICKS.get()))
                    .where("K", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("F", Predicates.frames(GTMaterials.Bronze))
                    .where("A", Predicates.blocks(CASING_PRIMITIVE_BRICKS.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                    )
                    .where("B", Predicates.blocks(GTBlocks.FIREBOX_BRONZE.get()))
                    .where(" ", Predicates.any())
                    .build()
            )
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_primitive_bricks"), GTCEu.id("block/multiblock/steam_oven"))
            .register();
    public static final MultiblockMachineDefinition VOID_MINER = REGISTRATE.multiblock("void_miner", VoidMinerProcessingMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.VOID_MINER)
            .appearanceBlock(GTBlocks.CASING_TUNGSTENSTEEL_ROBUST)
            .tooltips(Component.translatable("ctnh.multiblock.void_miner.tooltip.0").withStyle(ChatFormatting.GRAY),
                    Component.translatable("ctnh.multiblock.void_miner.tooltip.1"),
                    Component.translatable("ctnh.multiblock.void_miner.tooltip.2"),
                    Component.translatable("ctnh.multiblock.void_miner.tooltip.3"),
                    Component.translatable("ctnh.multiblock.void_miner.tooltip.4"),
                    Component.translatable("ctnh.multiblock.void_miner.tooltip.5"),
                    Component.translatable("ctnh.multiblock.void_miner.tooltip.6").withStyle(ChatFormatting.GOLD),
                    Component.translatable("ctnh.multiblock.void_miner.tooltip.7").withStyle(ChatFormatting.AQUA),
                    Component.translatable("ctnh.multiblock.void_miner.tooltip.8"))
            .recipeModifiers(VoidMinerProcessingMachine::recipeModifier, OC_NON_PERFECT)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("CCCCCCC", "XF   FX", "XF   FX", "XXXXXXX", "XF   FX", "XF   FX", "XF   FX", " F   F ", "       ", "       ", "       ", "       ")
                    .aisle("CCCCCCC", "F     F", "F     F", "X     X", "F     F", "F     F", "FX   XF", "FX   XF", " FFFFF ", "       ", "       ", "       ")
                    .aisle("CCBBBCC", "       ", "   A   ", "X  F  X", "   F   ", "   F   ", "  PGP  ", "  PGP  ", " FVFVF ", "  #F#  ", "  #F#  ", "  ###  ")
                    .aisle("CCBBBCC", "   A   ", "  AAA  ", "X FPF X", "  FPF  ", "  FPF  ", "  GGG  ", "  GGG  ", " FFXFF ", "  FXF  ", "  FXF  ", "  #F#  ")
                    .aisle("CCBBBCC", "       ", "   A   ", "X  F  X", "   F   ", "   F   ", "  PGP  ", "  PGP  ", " FVFVF ", "  #F#  ", "  #F#  ", "  ###  ")
                    .aisle("CCCCCCC", "F     F", "F     F", "X     X", "F     F", "F     F", "FX   XF", "FX   XF", " FFFFF ", "       ", "       ", "       ")
                    .aisle("CCCCCCC", "XF   FX", "XF   FX", "XXXYXXX", "XF   FX", "XF   FX", "XF   FX", " F   F ", "       ", "       ", "       ", "       ")
                    .where("C", Predicates.blocks(DARK_CONCRETE.get()))
                    .where("B", Predicates.blocks(REINFORCED_DEEPSLATE))
                    .where("A", Predicates.blocks(CTNHBlocks.CASING_TUNGSTENCU_DIAMOND_PLATING.get()))
                    .where("V", Predicates.blocks(HEAT_VENT.get()))
                    .where("F", Predicates.frames(GTMaterials.TungstenSteel))
                    .where("P", Predicates.blocks(CASING_TUNGSTENSTEEL_PIPE.get()))
                    .where("G", Predicates.blocks(CASING_TUNGSTENSTEEL_GEARBOX.get()))
                    .where("X", Predicates.blocks(CASING_TUNGSTENSTEEL_ROBUST.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                            .or(abilities(PartAbility.PARALLEL_HATCH))
                            .or(abilities(PartAbility.INPUT_LASER))
                            .or(abilities(PartAbility.INPUT_ENERGY))
                            .or(Predicates.autoAbilities(definition.getRecipeTypes())))
                    .where("#", Predicates.air())
                    .where(" ", Predicates.any())
                    .where("Y", Predicates.controller(Predicates.blocks(definition.get())))
                    .build()
            )
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_robust_tungstensteel"), GTCEu.id("block/multiblock/large_chemical_reactor"))
            .register();

    public static final MultiblockMachineDefinition SINTERING_KILN = REGISTRATE.multiblock("sintering_kiln", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.SINTERING_KILN)
            .tooltips(Component.translatable("ctnh.multiblock.sintering_kiln.tooltip.0").withStyle(ChatFormatting.GRAY))
            .appearanceBlock(CTNHBlocks.HIGH_GRADE_COKE_OVEN_BRICKS)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAA", "#AAA#", "#AAA#", "#ADA#", "#####")
                    .aisle("AAAAA", "ABBBA", "AB#BA", "ABCBA", "#AAA#")
                    .aisle("AAAAA", "ABBBA", "AB#BA", "ABCBA", "#AAA#")
                    .aisle("AAAAA", "ABBBA", "AB#BA", "ABCBA", "#AAA#")
                    .aisle("AAAAA", "ABBBA", "AB#BA", "ABCBA", "#AAA#")
                    .aisle("AAAAA", "ABBBA", "AB#BA", "ABCBA", "#AAA#")
                    .aisle("AAAAA", "#AAA#", "#A@A#", "#ADA#", "#####")
                    .where("A", Predicates.blocks(CTNHBlocks.HIGH_GRADE_COKE_OVEN_BRICKS.get()).setMinGlobalLimited(85)
                            .or(abilities(PartAbility.STEAM_EXPORT_ITEMS).setPreviewCount(1))
                            .or(abilities(PartAbility.STEAM_IMPORT_ITEMS).setPreviewCount(1)))
                    .where("#", Predicates.any())
                    .where("B", Predicates.blocks(CASING_PRIMITIVE_BRICKS.get()))
                    .where("C", Predicates.blocks(Blocks.PISTON))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("D", abilities(CTPPPartAbility.INPUT_KINETIC).setExactLimit(1)
                            .or(Predicates.blocks(CTNHBlocks.HIGH_GRADE_COKE_OVEN_BRICKS.get())))
                    .build()
            )
            .workableCasingModel(CTNHCore.id("block/high_grade_coke_oven_bricks"), GTCEu.id("block/machines/alloy_smelter"))
            .register();
    public static final MultiblockMachineDefinition ULTIMATE_COMBUSTION_ENGINE = CTNHMachineUtils.registerLargeCombustionEngine(
            "ultimate_combustion_engine", ZPM,
            CASING_NAQUADAH_BLOCK, CASING_NAQUADAH_GEARBOX, CASING_ULTIMATE_ENGINE_INTAKE,
            CTNHCore.id("block/casings/nq_casing"),
            GTCEu.id("block/multiblock/generator/extreme_combustion_engine"));

    public static final MultiblockMachineDefinition CHEMICAL_VAPOR_DEPOSITION_MACHINE = REGISTRATE.multiblock("chemical_vapor_deposition_machine", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.CHEMICAL_VAPOR_DEPOSITION)
            .recipeModifiers(OC_NON_PERFECT, BATCH_MODE)
            .appearanceBlock(CASING_STEEL_SOLID)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAAAA", "AAABBBA", "AAABBBA")
                    .aisle("AAAAAAA", "ACADDDA", "AAABBBA")
                    .aisle("AAAAAAA", "A@ABBBA", "AAABBBA")
                    .where("A", Predicates.blocks(CASING_STEEL_SOLID.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1)))
                    .where("B", Predicates.blocks(CASING_LAMINATED_GLASS.get()))
                    .where("C", Predicates.blocks(GCYMBlocks.MOLYBDENUM_DISILICIDE_COIL_BLOCK.get()))
                    .where("D", Predicates.blocks(CASING_PTFE_INERT.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build()
            )
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_solid_steel"), GTCEu.id("block/multiblock/implosion_compressor"))
            .register();

    public static final MultiblockMachineDefinition MARTIAL_MORALITY_EYE = REGISTRATE.multiblock("martial_morality_eye", MartialMoralityEyeMachine::new)
            .allowExtendedFacing(false)
            .recipeType(CTNHRecipeTypes.MARTIAL_MORALITY_EYE)
            .appearanceBlock(CASING_BRONZE_BRICKS)
            .tooltips(Component.translatable("ctnh.multiblock.martial_morality_eye.tooltip.0").withStyle(ChatFormatting.GRAY),
                    Component.translatable("ctnh.multiblock.martial_morality_eye.tooltip.1"),
                    Component.translatable("ctnh.multiblock.martial_morality_eye.tooltip.2"),
                    Component.translatable("ctnh.multiblock.martial_morality_eye.tooltip.3"),
                    Component.translatable("ctnh.multiblock.martial_morality_eye.tooltip.4"),
                    Component.translatable("ctnh.multiblock.martial_morality_eye.tooltip.5").withStyle(ChatFormatting.RED),
                    Component.translatable("ctnh.multiblock.martial_morality_eye.tooltip.6"))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "###############A#A###############", "###############A#A###############", "###############A#A###############", "############AAAAAAAAA############", "###############A#A###############", "############AAAAAAAAA############", "###############A#A###############", "###############A#A###############", "###############A#A###############", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################")
                    .aisle("#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "###############A#A###############", "###############A#A###############", "###############A#A###############", "###############A#A###############", "##############BBBBB##############", "#############BBABABB#############", "#########AAAABAABAABAAAA#########", "#############BBBBBBB#############", "#########AAAABAABAABAAAA#########", "#############BBABABB#############", "##############BBBBB##############", "###############A#A###############", "###############A#A###############", "###############A#A###############", "###############A#A###############", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################")
                    .aisle("#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "###############A#A###############", "###############A#A###############", "###############A#A###############", "################B################", "################B################", "#############BBBBBBB#############", "############BB#####BB############", "############B##CCC##B############", "#######AAA##B#CDDDC#B##AAA#######", "##########BBB#CDDDC#BBB##########", "#######AAA##B#CDDDC#B##AAA#######", "############B##CCC##B############", "############BB#####BB############", "#############BBBBBBB#############", "################B################", "################B################", "###############A#A###############", "###############A#A###############", "###############A#A###############", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################")
                    .aisle("#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "###############A#A###############", "###############A#A###############", "################B################", "################B################", "#################################", "#################################", "#################################", "#################################", "#################################", "######AA#################AA######", "########BB#############BB########", "######AA#################AA######", "#################################", "#################################", "#################################", "#################################", "#################################", "################B################", "################B################", "###############A#A###############", "###############A#A###############", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################")
                    .aisle("#################################", "#################################", "#################################", "#################################", "#################################", "###############A#A###############", "##############AAAAA##############", "################B################", "################D################", "################D################", "#################################", "#################################", "#################################", "#################################", "######A###################A######", "#####AA###################AA#####", "######ABDD#############DDBA######", "#####AA###################AA#####", "######A###################A######", "#################################", "#################################", "#################################", "#################################", "################D################", "################D################", "################B################", "##############AAAAA##############", "###############A#A###############", "#################################", "#################################", "#################################", "#################################", "#################################")
                    .aisle("#################################", "#################################", "#################################", "#################################", "###############A#A###############", "###############A#A###############", "################B################", "#############ECCDCCE#############", "#################################", "#################################", "#################################", "#################################", "#################################", "#######E#################E#######", "#######C#################C#######", "####AA#C#################C#AA####", "######BD#################DB######", "####AA#C#################C#AA####", "#######C#################C#######", "#######E#################E#######", "#################################", "#################################", "#################################", "#################################", "#################################", "#############ECCDCCE#############", "################B################", "###############A#A###############", "###############A#A###############", "#################################", "#################################", "#################################", "#################################")
                    .aisle("#################################", "#################################", "#################################", "###############A#A###############", "##############AAAAA##############", "################B################", "################D################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "####A#######################A####", "###AA#######################AA###", "####ABD###################DBA####", "###AA#######################AA###", "####A#######################A####", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "################D################", "################B################", "##############AAAAA##############", "###############A#A###############", "#################################", "#################################", "#################################")
                    .aisle("#################################", "#################################", "###############A#A###############", "###############A#A###############", "################B################", "#############ECCDCCE#############", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#####E#####################E#####", "#####C#####################C#####", "##AA#C#####################C#AA##", "####BD#####################DB####", "##AA#C#####################C#AA##", "#####C#####################C#####", "#####E#####################E#####", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#############ECCDCCE#############", "################B################", "###############A#A###############", "###############A#A###############", "#################################", "#################################")
                    .aisle("#################################", "#################################", "###############A#A###############", "################B################", "################D################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "##A###########################A##", "###BD#######################DB###", "##A###########################A##", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "################D################", "################B################", "###############A#A###############", "#################################", "#################################")
                    .aisle("#################################", "###############A#A###############", "###############A#A###############", "################B################", "################D################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#AA###########################AA#", "###BD#######################DB###", "#AA###########################AA#", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "################D################", "################B################", "###############A#A###############", "###############A#A###############", "#################################")
                    .aisle("#################################", "###############A#A###############", "################B################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#A#############################A#", "##B###########################B##", "#A#############################A#", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "################B################", "###############A#A###############", "#################################")
                    .aisle("#################################", "###############A#A###############", "################B################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#A#############################A#", "##B###########################B##", "#A#############################A#", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "################B################", "###############A#A###############", "#################################")
                    .aisle("###############A#A###############", "###############A#A###############", "#############BBBBBBB#############", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "##B###########################B##", "##B###########################B##", "AAB###########################BAA", "##B###########################B##", "AAB###########################BAA", "##B###########################B##", "##B###########################B##", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#############BBBBBBB#############", "###############A#A###############", "###############A#A###############")
                    .aisle("###############A#A###############", "##############BBBBB##############", "############BB#####BB############", "#################################", "#################################", "#######E#################E#######", "#################################", "#####E#####################E#####", "#################################", "#################################", "#################################", "#################################", "##B###########################B##", "##B###########################B##", "#B#############################B#", "AB#############################BA", "#B#############################B#", "AB#############################BA", "#B#############################B#", "##B###########################B##", "##B###########################B##", "#################################", "#################################", "#################################", "#################################", "#####E#####################E#####", "#################################", "#######E#################E#######", "#################################", "#################################", "############BB#####BB############", "##############BBBBB##############", "###############A#A###############")
                    .aisle("###############A#A###############", "#############BBABABB#############", "############B##CCC##B############", "#################################", "######A###################A######", "#######C#################C#######", "####A#######################A####", "#####C#####################C#####", "#################################", "#################################", "#################################", "#################################", "##B###########################B##", "#B#############################B#", "#B#############################B#", "AAC###########################CAA", "#BC###########################CB#", "AAC###########################CAA", "#B#############################B#", "#B#############################B#", "##B###########################B##", "#################################", "#################################", "#################################", "#################################", "#####C#####################C#####", "####A#######################A####", "#######C#################C#######", "######A###################A######", "#################################", "############B##CCC##B############", "#############BBABABB#############", "###############A#A###############")
                    .aisle("############AAAAAAAAA############", "#########AAAABAABAABAAAA#########", "#######AAA##B#CDDDC#B##AAA#######", "######AA#################AA######", "#####AA###################AA#####", "####AA#C#################C#AA####", "###AA#######################AA###", "##AA#C#####################C#AA##", "##A###########################A##", "#AA###########################AA#", "#A#############################A#", "#A#############################A#", "AAB###########################BAA", "AB#############################BA", "AAC###########################CAA", "AAD###########################DAA", "ABD###########################DBA", "AAD###########################DAA", "AAC###########################CAA", "AB#############################BA", "AAB###########################BAA", "#A#############################A#", "#A#############################A#", "#AA###########################AA#", "##A###########################A##", "##AA#C#####################C#AA##", "###AA#######################AA###", "####AA#C#################C#AA####", "#####AA###################AA#####", "######AA#################AA######", "#######AAA##B#CDDDC#B##AAA#######", "#########AAAABAABAABAAAA#########", "############AAAAAAAAA############")
                    .aisle("###############A#A###############", "#############BBBBBBB#############", "##########BBB#CDDDC#BBB##########", "########BB#############BB########", "######ABDD#############DDBA######", "######BD#################DB######", "####ABD###################DBA####", "####BD#####################DB####", "###BD#######################DB###", "###BD#######################DB###", "##B###########################B##", "##B###########################B##", "##B###########################B##", "#B#############################B#", "#BC###########################CB#", "ABD###########################DBA", "#BD###########################DB#", "ABD###########################DBA", "#BC###########################CB#", "#B#############################B#", "##B###########################B##", "##B###########################B##", "##B###########################B##", "###BD#######################DB###", "###BD#######################DB###", "####BD#####################DB####", "####ABD###################DBA####", "######BD#################DB######", "######ABDD#############DDBA######", "########BB#############BB########", "##########BBB#CDDDC#BBB##########", "#############BBBBBBB#############", "###############A#A###############")
                    .aisle("############AAAAAAAAA############", "#########AAAABAABAABAAAA#########", "#######AAA##B#CDDDC#B##AAA#######", "######AA#################AA######", "#####AA###################AA#####", "####AA#C#################C#AA####", "###AA#######################AA###", "##AA#C#####################C#AA##", "##A###########################A##", "#AA###########################AA#", "#A#############################A#", "#A#############################A#", "AAB###########################BAA", "AB#############################BA", "AAC###########################CAA", "AAD###########################DAA", "ABD###########################DBA", "AAD###########################DAA", "AAC###########################CAA", "AB#############################BA", "AAB###########################BAA", "#A#############################A#", "#A#############################A#", "#AA###########################AA#", "##A###########################A##", "##AA#C#####################C#AA##", "###AA#######################AA###", "####AA#C#################C#AA####", "#####AA###################AA#####", "######AA#################AA######", "#######AAA##B#CDDDC#B##AAA#######", "#########AAAABAABAABAAAA#########", "############AAAAAAAAA############")
                    .aisle("###############A#A###############", "#############BBABABB#############", "############B##CCC##B############", "#################################", "######A###################A######", "#######C#################C#######", "####A#######################A####", "#####C#####################C#####", "#################################", "#################################", "#################################", "#################################", "##B###########################B##", "#B#############################B#", "#B#############################B#", "AAC###########################CAA", "#BC###########################CB#", "AAC###########################CAA", "#B#############################B#", "#B#############################B#", "##B###########################B##", "#################################", "#################################", "#################################", "#################################", "#####C#####################C#####", "####A#######################A####", "#######C#################C#######", "######A###################A######", "#################################", "############B##CCC##B############", "#############BBABABB#############", "###############A#A###############")
                    .aisle("###############A#A###############", "##############BBBBB##############", "############BB#####BB############", "#################################", "#################################", "#######E#################E#######", "#################################", "#####E#####################E#####", "#################################", "#################################", "#################################", "#################################", "##B###########################B##", "##B###########################B##", "#B#############################B#", "AB#############################BA", "#B#############################B#", "AB#############################BA", "#B#############################B#", "##B###########################B##", "##B###########################B##", "#################################", "#################################", "#################################", "#################################", "#####E#####################E#####", "#################################", "#######E#################E#######", "#################################", "#################################", "############BB#####BB############", "##############BBBBB##############", "###############A#A###############")
                    .aisle("###############A#A###############", "###############A#A###############", "#############BBBBBBB#############", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "##B###########################B##", "##B###########################B##", "AAB###########################BAA", "##B###########################B##", "AAB###########################BAA", "##B###########################B##", "##B###########################B##", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#############BBBBBBB#############", "###############A#A###############", "###############A#A###############")
                    .aisle("#################################", "###############A#A###############", "################B################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#A#############################A#", "##B###########################B##", "#A#############################A#", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "################B################", "###############A#A###############", "#################################")
                    .aisle("#################################", "###############A#A###############", "################B################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#A#############################A#", "##B###########################B##", "#A#############################A#", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "################B################", "###############A#A###############", "#################################")
                    .aisle("#################################", "###############A#A###############", "###############A#A###############", "################B################", "################D################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#AA###########################AA#", "###BD#######################DB###", "#AA###########################AA#", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "################D################", "################B################", "###############A#A###############", "###############A#A###############", "#################################")
                    .aisle("#################################", "#################################", "###############A#A###############", "################B################", "################D################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "##A###########################A##", "###BD#######################DB###", "##A###########################A##", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "################D################", "################B################", "###############A#A###############", "#################################", "#################################")
                    .aisle("#################################", "#################################", "###############A#A###############", "###############A#A###############", "################B################", "#############ECCDCCE#############", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#####E#####################E#####", "#####C#####################C#####", "##AA#C#####################C#AA##", "####BD#####################DB####", "##AA#C#####################C#AA##", "#####C#####################C#####", "#####E#####################E#####", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#############ECCDCCE#############", "################B################", "###############A#A###############", "###############A#A###############", "#################################", "#################################")
                    .aisle("#################################", "#################################", "#################################", "###############A#A###############", "##############AAAAA##############", "################B################", "################D################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "####A#######################A####", "###AA#######################AA###", "####ABD###################DBA####", "###AA#######################AA###", "####A#######################A####", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "################D################", "################B################", "##############AAAAA##############", "###############A#A###############", "#################################", "#################################", "#################################")
                    .aisle("#################################", "#################################", "#################################", "#################################", "###############A#A###############", "###############A#A###############", "################B################", "#############ECCDCCE#############", "#################################", "#################################", "#################################", "#################################", "#################################", "#######E#################E#######", "#######C#################C#######", "####AA#C#################C#AA####", "######BD#################DB######", "####AA#C#################C#AA####", "#######C#################C#######", "#######E#################E#######", "#################################", "#################################", "#################################", "#################################", "#################################", "#############ECCDCCE#############", "################B################", "###############A#A###############", "###############A#A###############", "#################################", "#################################", "#################################", "#################################")
                    .aisle("#################################", "#################################", "#################################", "#################################", "#################################", "###############A#A###############", "##############AAAAA##############", "################B################", "################D################", "################D################", "#################################", "#################################", "#################################", "#################################", "######A###################A######", "#####AA###################AA#####", "######ABDD#############DDBA######", "#####AA###################AA#####", "######A###################A######", "#################################", "#################################", "#################################", "#################################", "################D################", "################D################", "################B################", "##############AAAAA##############", "###############A#A###############", "#################################", "#################################", "#################################", "#################################", "#################################")
                    .aisle("#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "###############A#A###############", "###############A#A###############", "################B################", "################B################", "#################################", "#################################", "#################################", "#################################", "#################################", "######AA#################AA######", "########BB#############BB########", "######AA#################AA######", "#################################", "#################################", "#################################", "#################################", "#################################", "################B################", "################B################", "###############A#A###############", "###############A#A###############", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################")
                    .aisle("#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "###############A#A###############", "###############A#A###############", "###############A#A###############", "################B################", "################B################", "#############BBBBBBB#############", "############BB#####BB############", "############B##CCC##B############", "#######AAA##B#CDDDC#B##AAA#######", "##########BBB#CDDDC#BBB##########", "#######AAA##B#CDDDC#B##AAA#######", "############B##CCC##B############", "############BB#####BB############", "#############BBBBBBB#############", "################B################", "################B################", "###############A#A###############", "###############A#A###############", "###############A#A###############", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################")
                    .aisle("#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "###############A#A###############", "###############A#A###############", "###############A#A###############", "###############A#A###############", "##############BBBBB##############", "#############BBABABB#############", "#########AAAABAABAABAAAA#########", "#############BBBBBBB#############", "#########AAAABAABAABAAAA#########", "#############BBABABB#############", "##############BBBBB##############", "###############A#A###############", "###############A#A###############", "###############A#A###############", "###############A#A###############", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################")
                    .aisle("#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#############AAAAAAA#############", "############AAFFFFFAA############", "############AFFFFFFFA############", "############AFFAAAFFA############", "############AFFA@AFFA############", "############AFFAAAFFA############", "############AFFFFFFFA############", "############AAFFFFFAA############", "#############AAAAAAA#############", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################", "#################################")
                    .where("A", Predicates.blocks(Blocks.BRICKS))
                    .where("#", Predicates.any())
                    .where("B", Predicates.blocks(OAK_PLANKS))
                    .where("C", Predicates.blocks(Blocks.LAPIS_BLOCK))
                    .where("D", Predicates.blocks(Blocks.BOOKSHELF))
                    .where("E", Predicates.blocks(Blocks.CHISELED_STONE_BRICKS))
                    .where("F", Predicates.blocks(CASING_BRONZE_BRICKS.get())
                            .or(abilities(PartAbility.STEAM))
                            .or(abilities(PartAbility.IMPORT_ITEMS))
                            .or(abilities(PartAbility.EXPORT_ITEMS))
                            .or(abilities(PartAbility.STEAM_EXPORT_ITEMS))
                            .or(abilities(PartAbility.STEAM_IMPORT_ITEMS))
                            .or(abilities(PartAbility.INPUT_ENERGY))
                            .or(abilities(PartAbility.EXPORT_FLUIDS))
                            .or(abilities(PartAbility.IMPORT_FLUIDS)))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .model(createWorkableCasingMachineModel(GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"), GTCEu.id("block/multiblock/fusion_reactor"))
                    .andThen(b -> b.addDynamicRenderer(MartialMoralityEyeRender::new)))
            //.workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"), GTCEu.id("block/multiblock/fusion_reactor"))
            .register();

    public static final MultiblockMachineDefinition ADVANCED_COKE_OVEN = REGISTRATE.multiblock("advanced_coke_oven", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTRecipeTypes.COKE_OVEN_RECIPES)
            .recipeModifiers((machine, group, recipe) -> {
                var failure = CTNHRecipeModifiers.accurateParallel(machine, group, recipe, 32);
                if (failure != null) return failure;
                recipe.multiplyDuration((double) 300 / recipe.duration);
                return null;
            })
            .appearanceBlock(HIGH_GRADE_COKE_OVEN_BRICKS)
            .tooltips(Component.translatable("ctnh.multiblock.advanced_coke_oven.tooltip.0").withStyle(ChatFormatting.GRAY),
                    Component.translatable("ctnh.multiblock.advanced_coke_oven.tooltip.1"),
                    Component.translatable("ctnh.multiblock.advanced_coke_oven.tooltip.2"),
                    Component.translatable("ctnh.multiblock.advanced_coke_oven.tooltip.3"),
                    Component.translatable("ctnh.multiblock.advanced_coke_oven.tooltip.4"))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("BBB", "BBB", "BBB")
                    .aisle("BBB", "B#B", "BBB")
                    .aisle("BBB", "B@B", "BBB")
                    .where("B", Predicates.blocks(HIGH_GRADE_COKE_OVEN_BRICKS.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(abilities(PartAbility.EXPORT_ITEMS))
                            .or(abilities(PartAbility.STEAM_EXPORT_ITEMS).setPreviewCount(9))
                            .or(abilities(PartAbility.STEAM_IMPORT_ITEMS).setPreviewCount(9)))
                    .where("#", Predicates.any())
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel(CTNHCore.id("block/high_grade_coke_oven_bricks"), GTCEu.id("block/machines/arc_furnace"))
            .register();

    public static final MultiblockMachineDefinition DIMENSIONAL_GAS_COLLECTION_CHAMBER = REGISTRATE.multiblock("dimensional_gas_collection_chamber", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(CTNHRecipeTypes.DIMENSIONAL_GAS_COLLECTION)
            .recipeModifiers(OC_PERFECT_SUBTICK, BATCH_MODE)
            .appearanceBlock(PLASTCRETE)
            .tooltips(
                    Component.translatable("ctnh.multiblock.large_gas_collection_chamber.tooltip.0").withStyle(ChatFormatting.GRAY),
                    Component.translatable("ctnh.multiblock.large_gas_collection_chamber.tooltip.1"),
                    Component.translatable("ctnh.multiblock.large_gas_collection_chamber.tooltip.2"),
                    CTNHCommonTooltips.PERFECT_OVERCLOCK
            )
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAA", "ABBBA", "ABBBA", "ABBBA", "AAAAA")
                    .aisle("ABBBA", "BCCCB", "BCCCB", "BCCCB", "ABBBA")
                    .aisle("ABBBA", "BDDDB", "BDEDB", "BDDDB", "ABBBA")
                    .aisle("ABBBA", "BCCCB", "BCCCB", "BCCCB", "ABBBA")
                    .aisle("AAAAA", "ABBBA", "AB@BA", "ABBBA", "AAAAA")
                    .where("A", Predicates.blocks(PLASTCRETE.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1)))
                    .where("B", Predicates.blocks(FILTER_CASING.get()))
                    .where("C", Predicates.blocks(HERMETIC_CASING_HV.get()))
                    .where("D", Predicates.blocks(CASING_ASSEMBLY_LINE.get()))
                    .where("E", Predicates.blocks(CASING_PTFE_INERT.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build()
            )
            .workableCasingModel(GTCEu.id("block/casings/cleanroom/plascrete"), GTCEu.id("block/multiblock/fusion_reactor"))
            .register();

    public static final MultiblockMachineDefinition CONDENSING_DISCRETE = REGISTRATE.multiblock("condensing_discrete", CoilWorkableElectricMultiblockMachine::new)
            .allowExtendedFacing(false)
            .recipeType(CTNHRecipeTypes.CONDENSING_DISCRETE)
            .recipeModifiers(OC_NON_PERFECT, BATCH_MODE)
            .appearanceBlock(CASING_ALUMINIUM_FROSTPROOF)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("A###A", "BBCBB", "BBBBB", "#BDB#", "#BDB#", "#BDB#", "#BDB#", "#BDB#", "#BDB#", "#BDB#", "BBBBB", "CCCCC")
                    .aisle("#BBB#", "BBBBB", "BEAEB", "BF#FB", "BEAEB", "BF#FB", "BEAEB", "BF#FB", "BEAEB", "BF#FB", "BEAEB", "CBBBC")
                    .aisle("#BBB#", "CCACC", "BAAAB", "D#A#D", "DAAAD", "D#A#D", "DAAAD", "D#A#D", "DAAAD", "D#A#D", "BAAAB", "CBSBC")
                    .aisle("#BBB#", "BBCBB", "BEAEB", "BF#FB", "BEAEB", "BF#FB", "BEAEB", "BF#FB", "BEAEB", "BF#FB", "BEAEB", "CBBBC")
                    .aisle("A###A", "BB@BB", "BBBBB", "#BDB#", "#BDB#", "#BDB#", "#BDB#", "#BDB#", "#BDB#", "#BDB#", "BBBBB", "CCCCC")
                    .where("A", Predicates.frames(GTMaterials.BlueAlloy))
                    .where("#", Predicates.any())
                    .where("B", Predicates.blocks(CASING_ALUMINIUM_FROSTPROOF.get()).setMinGlobalLimited(100)
                            .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                            .or(Predicates.autoAbilities(definition.getRecipeTypes())))
                    .where("C", Predicates.blocks(HEAT_VENT.get()))
                    .where("D", Predicates.blocks(CASING_LAMINATED_GLASS.get()))
                    .where("E", Predicates.blocks(CASING_TUNGSTENSTEEL_PIPE.get()))
                    .where("F", Predicates.frames(GTMaterials.TungstenSteel))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("S", abilities(PartAbility.MUFFLER).setExactLimit(1))
                    .build()
            )
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_frost_proof"), GTCEu.id("block/multiblock/implosion_compressor"))
            .register();

    public static final MultiblockMachineDefinition ION_EXCHANGER = REGISTRATE.multiblock("ion_exchanger", CoilWorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.ION_EXCHANGER)
            .tooltips(Component.translatable("ctnh.multiblock.ion_exchanger.tooltip.0").withStyle(ChatFormatting.GRAY))
            .recipeModifiers(OC_NON_PERFECT, BATCH_MODE)
            .appearanceBlock(CASING_HSSE_STURDY)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("#AAAAA#", "AABBBAA", "ABBBBBA", "AABBBAA", "#AAAAA#", "#######")
                    .aisle("ACDCDCA", "A#EFE#A", "B#EGE#B", "A#EFE#A", "AADADAA", "#AAHAA#")
                    .aisle("ACDCDCA", "B#EFE#B", "B#EGE#B", "B#EFE#B", "AADADAA", "#AAHAA#")
                    .aisle("ACDCDCA", "A#EFE#A", "B#EGE#B", "A#EFE#A", "AADADAA", "#AAHAA#")
                    .aisle("#AA@AA#", "AABBBAA", "ABBBBBA", "AABBBAA", "#AAAAA#", "#######")
                    .where("#", Predicates.any())
                    .where("A", Predicates.blocks(CASING_HSSE_STURDY.get()).setMinGlobalLimited(60)
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.autoAbilities(true, true, false))
                    )
                    .where("B", Predicates.blocks(CASING_LAMINATED_GLASS.get()))
                    .where("C", Predicates.blocks(CASING_PTFE_INERT.get()))
                    .where("D", Predicates.blocks(CTNHBlocks.CASING_OSMIRIDIUM.get()))
                    .where("E", Predicates.blocks(CASING_PTFE_INERT.get()))
                    .where("F", Predicates.frames(GTMaterials.TungstenSteel))
                    .where("G", Predicates.frames(GTMaterials.BlueAlloy))
                    .where("H", Predicates.frames(GTMaterials.Tungsten))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build()
            )
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_sturdy_hsse"), GTCEu.id("block/multiblock/implosion_compressor"))
            .register();

    public static final MultiblockMachineDefinition LARGE_STEEL_FURNACE = REGISTRATE.multiblock("large_steel_furnace", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.FURNACE_RECIPES)
            .recipeModifiers((machine, group, recipe) -> CTNHRecipeModifiers.accurateParallel(machine, group, recipe, 32), GTRecipeModifiers.OC_PERFECT_SUBTICK)
            .appearanceBlock(CASING_PRIMITIVE_BRICKS)
            .tooltips(Component.translatable("ctnh.multiblock.large_steel_furnace.tooltip.0").withStyle(ChatFormatting.GRAY))
            .tooltips(Component.translatable("ctnh.common_tooltip.perfect_overclock").withStyle(ChatFormatting.GREEN))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAA", "BBB", "BBB", "#B#")
                    .aisle("AAA", "BCB", "BAB", "#B#")
                    .aisle("AAA", "B@B", "BBB", "#B#")
                    .where("A", Predicates.blocks(FIREBOX_STEEL.get()))
                    .where("B", Predicates.blocks(CASING_PRIMITIVE_BRICKS.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes())))
                    .where("#", Predicates.any())
                    .where("C", Predicates.blocks(CASING_STEEL_PIPE.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_primitive_bricks"), GTCEu.id("block/multiblock/implosion_compressor"))
            .register();

    public static final MultiblockMachineDefinition LARGE_STEEL_ALLOY_FURNACE = REGISTRATE.multiblock("large_steel_alloy_furnace", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.ALLOY_SMELTER_RECIPES)
            .recipeModifiers((machine, group, recipe) -> CTNHRecipeModifiers.accurateParallel(machine, group, recipe, 32), GTRecipeModifiers.OC_PERFECT_SUBTICK)
            .appearanceBlock(CASING_PRIMITIVE_BRICKS)
            .tooltips(Component.translatable("ctnh.multiblock.large_steel_alloy_furnace.tooltip.0").withStyle(ChatFormatting.GRAY))
            .tooltips(Component.translatable("ctnh.common_tooltip.perfect_overclock").withStyle(ChatFormatting.GREEN))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("ABA", "CCC", "CBC", "CCC")
                    .aisle("BBB", "CCC", "BDB", "CCC")
                    .aisle("ABA", "C@C", "CBC", "CCC")
                    .where("A", Predicates.frames(GTMaterials.Steel))
                    .where("B", Predicates.blocks(FIREBOX_STEEL.get()))
                    .where("C", Predicates.blocks(CASING_PRIMITIVE_BRICKS.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes())))
                    .where("D", Predicates.blocks(CASING_STEEL_PIPE.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_primitive_bricks"), GTCEu.id("block/machines/alloy_smelter"))
            .register();

    public static final MultiblockMachineDefinition[] LARGE_DIGITAL_MINER = CTNHMachineUtils.registerTieredMultis("large_digital_miner",
            (holder, tier) -> new LargeDigitalMinerMachine(holder, tier, 64 / tier, 2 * tier - 5, tier,
                    8 - (tier - 5)),
            (tier, builder) -> {
                var casing = switch (tier) {
                    case EV -> CASING_STEEL_SOLID;
                    case IV -> CASING_TITANIUM_STABLE;
                    case LuV -> CASING_TUNGSTENSTEEL_ROBUST;
                    case ZPM -> CASING_OSMIRIDIUM;
                    default -> throw new IllegalArgumentException("Unsupported large miner tier: " + tier);
                };
                var frame = switch (tier) {
                    case EV -> Steel;
                    case IV -> Titanium;
                    case LuV -> TungstenSteel;
                    case ZPM -> Osmiridium;
                    default -> throw new IllegalArgumentException("Unsupported large miner tier: " + tier);
                };
                var casingTexture = switch (tier) {
                    case EV -> GTCEu.id("block/casings/solid/machine_casing_solid_steel");
                    case IV -> GTCEu.id("block/casings/solid/machine_casing_stable_titanium");
                    case LuV -> GTCEu.id("block/casings/solid/machine_casing_robust_tungstensteel");
                    case ZPM -> CTNHCore.id("block/casings/osmiridium_casing");
                    default -> throw new IllegalArgumentException("Unsupported large miner tier: " + tier);
                };
                return builder
                        .cnLangValue(VNF[tier] + "大型数字采矿机")
                        .langValue(VNF[tier] + " Large Digital Miner")
                        .rotationState(RotationState.NON_Y_AXIS)
                        .appearanceBlock(casing)
                        .tooltips(Component.translatable("gtceu.machine.large_miner." + VN[tier].toLowerCase() + ".tooltip"),
                                Component.translatable("gtceu.machine.miner.multi.description"))
                        .tooltipBuilder((stack, tooltip) -> {
                            int workingAreaChunks = 2 * tier - 5;
                            tooltip.add(Component.translatable("gtceu.machine.miner.fluid_usage", 8 - (tier - 5),
                                    DrillingFluid.getLocalizedName()));
                            tooltip.add(Component.translatable("gtceu.universal.tooltip.working_area_chunks",
                                    workingAreaChunks, workingAreaChunks));
                            tooltip.add(Component.translatable("gtceu.universal.tooltip.energy_tier_range",
                                    GTValues.VNF[tier], GTValues.VNF[tier + 1]));
                        })
                        .pattern(definition -> FactoryBlockPattern.start()
                                .aisle("XXX", "#F#", "#F#", "#F#", "###", "###", "###")
                                .aisle("XXX", "FCF", "FCF", "FCF", "#F#", "#F#", "#F#")
                                .aisle("XSX", "#F#", "#F#", "#F#", "###", "###", "###")
                                .where("S", Predicates.controller(Predicates.blocks(definition.get())))
                                .where("X", Predicates.blocks(casing.get())
                                        .or(abilities(PartAbility.EXPORT_ITEMS).setExactLimit(1).setPreviewCount(1))
                                        .or(abilities(PartAbility.IMPORT_FLUIDS).setExactLimit(1).setPreviewCount(1))
                                        .or(abilities(PartAbility.INPUT_ENERGY).setMinGlobalLimited(1)
                                                .setMaxGlobalLimited(2).setPreviewCount(1)))
                                .where("C", Predicates.blocks(casing.get()))
                                .where("F", Predicates.frames(frame))
                                .where("#", Predicates.any())
                                .build())
                        .workableCasingModel(casingTexture, GTCEu.id("block/multiblock/large_miner"))
                        .register();
            }, EV, IV, LuV, ZPM);

//    public static final MultiblockMachineDefinition ZPM_LARGE_MINER = REGISTRATE.multiblock("zpm_large_miner", LargeDigitalMinerMachine::new)
//            .rotationState(RotationState.NON_Y_AXIS)
//            .tooltips(
//                    Component.translatable("ctnh.multiblock.large_miner_zpm.tooltip.0"),
//                    Component.translatable("gtceu.machine.miner.multi.description"))
//            .tooltipBuilder((stack, tooltip) -> {
//                int workingAreaChunks = (2 * ZPM - 5);
//                tooltip.add(Component.translatable("gtceu.machine.miner.multi.modes"));
//                tooltip.add(Component.translatable("gtceu.machine.miner.multi.production"));
//                tooltip.add(Component.translatable("gtceu.machine.miner.fluid_usage", 8 - (ZPM - 5),
//                        DrillingFluid.getLocalizedName()));
//                tooltip.add(Component.translatable("gtceu.universal.tooltip.working_area_chunks",
//                        workingAreaChunks, workingAreaChunks));
//                tooltip.add(Component.translatable("gtceu.universal.tooltip.energy_tier_range",
//                        GTValues.VNF[ZPM], GTValues.VNF[ZPM + 1]));
//            })
//            .pattern((definition) -> FactoryBlockPattern.start()
//                    .aisle("XXX", "#F#", "#F#", "#F#", "###", "###", "###")
//                    .aisle("XXX", "FCF", "FCF", "FCF", "#F#", "#F#", "#F#")
//                    .aisle("XSX", "#F#", "#F#", "#F#", "###", "###", "###")
//                    .where("S", Predicates.controller(Predicates.blocks(definition.get())))
//                    .where("X", Predicates.blocks(CASING_OSMIRIDIUM.get())
//                            .or(abilities(PartAbility.EXPORT_ITEMS).setMinGlobalLimited(1).setPreviewCount(1))
//                            .or(abilities(PartAbility.IMPORT_FLUIDS).setMinGlobalLimited(1).setPreviewCount(1))
//                            .or(abilities(PartAbility.INPUT_ENERGY).setMinGlobalLimited(1)
//                                    .setMaxGlobalLimited(2).setPreviewCount(1)))
//                    .where("C", Predicates.blocks(CASING_OSMIRIDIUM.get()))
//                    .where("F", Predicates.frames(GTMaterials.Osmiridium))
//                    .where("#", Predicates.any())
//                    .build())
//            .workableCasingModel(CTNHCore.id("block/casings/osmiridium_casing"), GTCEu.id("block/multiblock/large_miner"))
//            .register();
    public static final MultiblockMachineDefinition DECAY_POOLS = REGISTRATE.multiblock("decay_pools_machine", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(CTNHRecipeTypes.DECAY_POOLS)
            .appearanceBlock(GTBlocks.CASING_STAINLESS_CLEAN)
            .tooltips(Component.translatable("ctnh.multiblock.decay_pools.tooltip.0").withStyle(ChatFormatting.GRAY),
                    Component.translatable("ctnh.multiblock.decay_pools.tooltip.1"),
                    Component.translatable("ctnh.multiblock.decay_pools.tooltip.2"),
                    Component.translatable("ctnh.multiblock.decay_pools.tooltip.3"))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("##A#A##", "##A#A##", "##AAA##", "##AAA##", "##AAA##", "###A###", "#######")
                    .aisle("#######", "##AAA##", "#ABBBA#", "#ABBBA#", "#ABBBA#", "##AAA##", "###A###")
                    .aisle("A#AAA#A", "AABBBAA", "ABC#CBA", "AB#D#BA", "ABC#CBA", "#ABBBA#", "##AAA##")
                    .aisle("##AAA##", "#ABBBA#", "AB###BA", "AB#D#BA", "AB###BA", "#ABBBA#", "##AAA##")
                    .aisle("##AAA##", "#ABBBA#", "ABC#CBA", "AB#D#BA", "ABC#CBA", "#ABBBA#", "##AAA##")
                    .aisle("##AAA##", "#ABBBA#", "AB###BA", "AB#D#BA", "AB###BA", "#ABBBA#", "##AAA##")
                    .aisle("##AAA##", "#ABBBA#", "AB###BA", "AB#D#BA", "AB###BA", "#ABBBA#", "##AAA##")
                    .aisle("##AAA##", "#ABBBA#", "ABC#CBA", "AB#D#BA", "ABC#CBA", "#ABBBA#", "##AAA##")
                    .aisle("##AAA##", "#ABBBA#", "AB###BA", "AB#D#BA", "AB###BA", "#ABBBA#", "##AAA##")
                    .aisle("A#AAA#A", "AABBBAA", "ABC#CBA", "AB#D#BA", "ABC#CBA", "#ABBBA#", "##AAA##")
                    .aisle("#######", "##AAA##", "#ABBBA#", "#ABBBA#", "#ABBBA#", "##AAA##", "###A###")
                    .aisle("##A#A##", "##A#A##", "##AAA##", "##A@A##", "##AAA##", "###A###", "#######")
                    .where("#", Predicates.any())
                    .where("A", Predicates.blocks(CASING_STAINLESS_CLEAN.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1)))
                    .where("B", Predicates.blocks(MATERIAL_BLOCKS.get(TagPrefix.block, CTNHMaterials.Cerrobase140).get()))
                    .where("C", Predicates.any())
                    .where("D", Predicates.blocks(RADIATION_PROOF_MACHINE_CASING.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"), GTCEu.id("block/multiblock/generator/large_steam_turbine"))
            .register();
    public static final MultiblockMachineDefinition FUEL_REFINING_FACTORY = REGISTRATE.multiblock("fuel_refining_factory", CoilWorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.FUEL_REFINING)
            .recipeModifier(GTRecipeModifiers::ebfOverclock)
            .appearanceBlock(GTBlocks.CASING_STEEL_SOLID)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("#####ABBBA#####", "#####BCCCB#####", "#####BCCCB#####", "#####BCCCB#####", "#####ABBBA#####", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############")
                    .aisle("###DDBEEEBDD###", "###DEEDFDEED###", "###DDBDGDBDD###", "###DBBDBDBBD###", "######EAE######", "######EEE######", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############")
                    .aisle("####BBEEEBB####", "###EHHBFBHHE###", "####BBBCBBB####", "###E##BBB##E###", "######BBB######", "######EEE######", "######EEE######", "####BBBBBBB####", "####DDDDDDD####", "####EEEEEEE####", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############")
                    .aisle("#D#B##EEE##B#D#", "#DEHBBBFBBBHED#", "#D#B###C###B#D#", "#DED#######DED#", "###D#######D###", "###D##EEE##D###", "###D##DFD##D###", "###BEEECEEEB###", "###DHHHHHHHD###", "###EBBBBBBBE###", "######D#D######", "######D#D######", "######D#D######", "######D#D######", "######EEE######", "######DDD######", "######BBB######", "###############", "###############")
                    .aisle("#DB###EEE###BD#", "#EHB##DFD##BHE#", "#DB####C####BD#", "#B###########B#", "###############", "######EEE######", "######DFD######", "##BE###C###EB##", "##DHBBBBBBBHD##", "##EBF#####FBE##", "####F#####F####", "####F#####F####", "####EEEEEEE####", "###############", "#####EEEEE#####", "#####DHHHD#####", "#####BACAB#####", "######ACA######", "###############")
                    .aisle("ABB###EEE###BBA", "BEHB##DFD##BHEB", "BBB####C####BBB", "BB###########BB", "A#############A", "######EEE######", "######DFD######", "##BE###C###EB##", "##DHB#####BHD##", "##EB#######BE##", "###############", "#####BBBBB#####", "####EAAAAAE####", "###############", "####EBEEEBE####", "####DHDFDHD####", "####BB#C#BB####", "#####EEEEE#####", "######EEE######")
                    .aisle("BEEEEEEEEEEEEEB", "IDBBDDDFDDDBBDJ", "IDB###DKD###BDJ", "IDB###DKD###BDJ", "BEB###DKD###BEB", "#EEEEEDKDEEEEE#", "##EDDDDFDDDDE##", "##BE##DKD##EB##", "##DHB#DKD#BHD##", "##EB##DKD##BE##", "###D##DKD##D###", "###D#BDKDB#D###", "###DEADKDAED###", "###D##DKD##D###", "###EEEDKDEEE###", "###DHDLLLDHD###", "###BA#LLL#AB###", "####AELLLEA####", "#####EMMME#####")
                    .aisle("BEEEEEEEEEEEEEB", "IFFFFFFFFFFFFFJ", "IGCCCCKFKCCCCGJ", "IBB###KFK###BBJ", "BAB###KFK###BAB", "#EEEEEKFKEEEEE#", "##EFFFFFFFFFE##", "##BCCCKFKCCCB##", "##DHB#KFK#BHD##", "##EB##KFK##BE##", "######KFK######", "#####BKFKB#####", "####EAKFKAE####", "######KFK######", "###EEEKFKEEE###", "###DHFLLLFHD###", "###BCCLOLCCB###", "####CELLLEC####", "#####EMMME#####")
                    .aisle("BEEEEEEEEEEEEEB", "IDBBDDDFDDDBBDJ", "IDB###DKD###BDJ", "IDB###DKD###BDJ", "BEB###DKD###BEB", "#EEEEEDKDEEEEE#", "##EDDDDFDDDDE##", "##BE##DKD##EB##", "##DHB#DKD#BHD##", "##EB##DKD##BE##", "###D##DKD##D###", "###D#BDKDB#D###", "###DEADKDAED###", "###D##DKD##D###", "###EEEDKDEEE###", "###DHDLLLDHD###", "###BA#LLL#AB###", "####AELLLEA####", "#####EMMME#####")
                    .aisle("ABB###EEE###BBA", "BEHB##DFD##BHEB", "BBB####C####BBB", "BB###########BB", "A#############A", "######EEE######", "######DFD######", "##BE###C###EB##", "##DHB#####BHD##", "##EB#######BE##", "###############", "#####BBBBB#####", "####EAAAAAE####", "###############", "####EBEEEBE####", "####DHDFDHD####", "####BB#C#BB####", "#####EEEEE#####", "######EEE######")
                    .aisle("#DB###EEE###BD#", "#EHB##DFD##BHE#", "#DB####C####BD#", "#B###########B#", "###############", "######EEE######", "######DFD######", "##BE###C###EB##", "##DHBBBBBBBHD##", "##EBF#####FBE##", "####F#####F####", "####F#####F####", "####EEEEEEE####", "###############", "#####EEEEE#####", "#####DHHHD#####", "#####BACAB#####", "######ACA######", "###############")
                    .aisle("#D#B##EEE##B#D#", "#DEHBBBFBBBHED#", "#D#B###C###B#D#", "#DED#######DED#", "###D#######D###", "###D##EEE##D###", "###D##DFD##D###", "###BEEECEEEB###", "###DHHHHHHHD###", "###EBBBBBBBE###", "######D#D######", "######D#D######", "######D#D######", "######D#D######", "######EEE######", "######DDD######", "######BBB######", "###############", "###############")
                    .aisle("####BBEEEBB####", "###EHHBFBHHE###", "####BBBCBBB####", "###E##BBB##E###", "######BBB######", "######EEE######", "######EEE######", "####BBBBBBB####", "####DDDDDDD####", "####EEEEEEE####", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############")
                    .aisle("###DDBEEEBDD###", "###DEEDFDEED###", "###DDBDGDBDD###", "###DBBDBDBBD###", "######EAE######", "######EEE######", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############")
                    .aisle("####DNNNNND####", "####ENN@NNE####", "####DNNNNND####", "####EEDEDEE####", "######EAE######", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############")
                    .where("#", Predicates.any())
                    .where("A", Predicates.blocks(CASING_STEEL_GEARBOX.get()))
                    .where("B", Predicates.blocks(CASING_STEEL_SOLID.get()))
                    .where("C", Predicates.blocks(MATERIAL_BLOCKS.get(TagPrefix.block, GTMaterials.BlackSteel).get()))
                    .where("D", Predicates.frames(GTMaterials.BlackSteel))
                    .where("E", Predicates.blocks(FIREBOX_STEEL.get()))
                    .where("F", Predicates.blocks(CASING_STEEL_PIPE.get()))
                    .where("G", Predicates.blocks(HERMETIC_CASING_LV.get()))
                    .where("H", Predicates.heatingCoils())
                    .where("I", Predicates.blocks(MATERIAL_BLOCKS.get(TagPrefix.block, GTMaterials.RedSteel).get()))
                    .where("J", Predicates.blocks(MATERIAL_BLOCKS.get(TagPrefix.block, GTMaterials.BlueSteel).get()))
                    .where("K", Predicates.blocks(HERMETIC_CASING_HV.get()))
                    .where("L", Predicates.blocks(BLAZE_BLAST_FURNACE_CASING.get()))
                    .where("M", abilities(PartAbility.MUFFLER).setExactLimit(9))
                    .where("N", Predicates.blocks(CASING_STEEL_SOLID.get())
                            .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                            .or(Predicates.autoAbilities(definition.getRecipeTypes())))
                    .where("O", Predicates.blocks(ChemicalHelper.getBlock(TagPrefix.block, Ignitium)))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .additionalDisplay((machine, l) -> {
                if (machine.isFormed() && machine instanceof CoilWorkableElectricMultiblockMachine cmachine) {
                    l.add(Component.translatable("gtceu.multiblock.blast_furnace.max_temperature", Component.literal(cmachine.getCoilType().getCoilTemperature() + "K").withStyle(ChatFormatting.RED)));
                }
            })
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_solid_steel"), GTCEu.id("block/multiblock/fusion_reactor"))
            .register();
    public static final MultiblockMachineDefinition VACUUM_SINTERING_TOWER = REGISTRATE.multiblock("vacuum_sintering_tower", CoilWorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.VACUUM_SINTERING)
            .tooltips(Component.translatable("ctnh.multiblock.vacuum_sintering_tower.tooltip.0").withStyle(ChatFormatting.GRAY))
            .recipeModifiers((machine, group, recipe) -> CTNHRecipeModifiers.accurateParallel(machine, group, recipe, 16), GTRecipeModifiers::ebfOverclock)
            .appearanceBlock(GCYMBlocks.CASING_HIGH_TEMPERATURE_SMELTING)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAA", "AAAAA", "A###A", "#####", "#####", "#####")
                    .aisle("AAAAA", "ABBBA", "ABSBA", "#BBB#", "#BBB#", "#####")
                    .aisle("AAAAA", "CBBBC", "DBEBD", "DB#BD", "DBBBD", "DDDDD")
                    .aisle("AAAAA", "ABBBA", "ABEBA", "#B#B#", "#BBB#", "##C##")
                    .aisle("AAAAA", "CBBBC", "DBEBD", "DB#BD", "DBBBD", "DDDDD")
                    .aisle("AAAAA", "ABBBA", "ABEBA", "#B#B#", "#BBB#", "##C##")
                    .aisle("AAAAA", "CBBBC", "DBEBD", "DB#BD", "DBBBD", "DDDDD")
                    .aisle("AAAAA", "ABBBA", "ABEBA", "#B#B#", "#BBB#", "##C##")
                    .aisle("AAAAA", "CBBBC", "DB@BD", "DBBBD", "DBBBD", "DDDDD")
                    .aisle("AAAAA", "AA#AA", "A###A", "#####", "#####", "#####")
                    .where("A", Predicates.blocks(CASING_INVAR_HEATPROOF.get()))
                    .where("#", Predicates.any())
                    .where("B", Predicates.blocks(GCYMBlocks.CASING_HIGH_TEMPERATURE_SMELTING.get()).setMinGlobalLimited(60)
                            .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                            .or(Predicates.autoAbilities(definition.getRecipeTypes())))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("C", Predicates.blocks(CASING_TITANIUM_PIPE.get()))
                    .where("D", Predicates.frames(GTMaterials.TungstenSteel))
                    .where("E", Predicates.heatingCoils())
                    .where("S", abilities(PartAbility.MUFFLER).setExactLimit(1))
                    .build())
            .additionalDisplay((machine, l) -> {
                if (machine.isFormed() && machine instanceof CoilWorkableElectricMultiblockMachine cmachine) {
                    l.add(Component.translatable("gtceu.multiblock.blast_furnace.max_temperature", Component.literal(cmachine.getCoilType().getCoilTemperature() + "K").withStyle(ChatFormatting.RED)));
                }
            })
            .workableCasingModel(GTCEu.id("block/casings/gcym/high_temperature_smelting_casing"), GTCEu.id("block/multiblock/implosion_compressor"))
            .register();
    public static final MultiblockMachineDefinition CRYSTALLIZER = REGISTRATE.multiblock("crystallizer", CoilWorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.CRYSTALLIZER)
            .tooltips(Component.translatable("ctnh.multiblock.crystallizer.tooltip.0").withStyle(ChatFormatting.GRAY),
                    Component.translatable("ctnh.multiblock.crystallizer.tooltip.1"),
                    Component.translatable("ctnh.multiblock.crystallizer.tooltip.2"),
                    Component.translatable("ctnh.multiblock.crystallizer.tooltip.3"),
                    Component.translatable("ctnh.multiblock.crystallizer.tooltip.4"))
            .recipeModifiers((machine, group, recipe) -> CTNHRecipeModifiers.accurateParallel(machine, group, recipe, 16), GTRecipeModifiers::ebfOverclock, BATCH_MODE)
            .appearanceBlock(GCYMBlocks.CASING_HIGH_TEMPERATURE_SMELTING)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("##AAAAA##", "###BCB###", "###BBB###", "#########", "#########", "#########", "#########", "#########", "#########")
                    .aisle("#AAAAAAA#", "##AACAA##", "###AAA###", "####D####", "####D####", "####D####", "####D####", "####A####", "#########")
                    .aisle("AAAAAAAAA", "#ADCCCDA#", "##DAAAD##", "##D###D##", "##D###D##", "##D###D##", "##D#A#D##", "##AAAAA##", "####B####")
                    .aisle("AAACCCAAA", "BACEEECAB", "BAAAEAAAB", "####F####", "####F####", "####F####", "###AEA###", "##AAAAA##", "###AAA###")
                    .aisle("AAACCCAAA", "CCCEGECCC", "BAAEGEAAB", "#D#FGF#D#", "#D#FGF#D#", "#D#FGF#D#", "#DAEEEAD#", "#AAACAAA#", "##BASAB##")
                    .aisle("AAACCCAAA", "BACEEECAB", "BAAAEAAAB", "####F####", "####F####", "####F####", "###AEA###", "##AAAAA##", "###AAA###")
                    .aisle("AAAAAAAAA", "#ADCCCDA#", "##DAAAD##", "##D###D##", "##D###D##", "##D###D##", "##D#A#D##", "##AAAAA##", "####B####")
                    .aisle("#AAAAAAA#", "##AACAA##", "###AAA###", "####D####", "####D####", "####D####", "####D####", "####A####", "#########")
                    .aisle("##AAAAA##", "###B@B###", "###BBB###", "#########", "#########", "#########", "#########", "#########", "#########")
                    .where("#", Predicates.any())
                    .where("A", Predicates.blocks(CASING_HSSE_STURDY.get()).setMinGlobalLimited(120)
                            .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                            .or(Predicates.autoAbilities(definition.getRecipeTypes())))
                    .where("B", Predicates.blocks(HEAT_VENT.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("C", Predicates.blocks(GCYMBlocks.CASING_HIGH_TEMPERATURE_SMELTING.get()))
                    .where("D", Predicates.frames(GTMaterials.Tungsten))
                    .where("E", Predicates.heatingCoils())
                    .where("F", Predicates.blocks(CASING_LAMINATED_GLASS.get()))
                    .where("G", Predicates.blocks(CASING_POLYTETRAFLUOROETHYLENE_PIPE.get()))
                    .where("S", abilities(PartAbility.MUFFLER).setExactLimit(1))
                    .build())
            .additionalDisplay((machine, l) -> {
                if (machine.isFormed() && machine instanceof CoilWorkableElectricMultiblockMachine cmachine) {
                    l.add(Component.translatable("gtceu.multiblock.blast_furnace.max_temperature", Component.literal(cmachine.getCoilType().getCoilTemperature() + "K").withStyle(ChatFormatting.RED)));
                }
            })
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_sturdy_hsse"), GTCEu.id("block/multiblock/implosion_compressor"))
            .register();
    public static final MultiblockMachineDefinition SEAWATER_DESALTING_FACTORY = REGISTRATE.multiblock("seawater_desalting_factory", CoilWorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.DESALTING)
            .tooltips(Component.translatable("ctnh.multiblock.desalting_factory.tooltip.0").withStyle(ChatFormatting.GRAY),
                    Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.0"),
                    Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.1"),
                    Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.2"))
            .recipeModifiers(GTRecipeModifiers::ebfOverclock, BATCH_MODE)
            .appearanceBlock(GTBlocks.CASING_STEEL_SOLID)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("C   C", "C   C", "CGGGC", " GGG ")
                    .aisle("     ", "     ", "GMMMG", "G###G")
                    .aisle("     ", "     ", "GMBMG", "G###G")
                    .aisle("     ", "     ", "GMMMG", "G###G")
                    .aisle("C   C", "C   C", "CGKGC", " GGG ")
                    .where("C", Predicates.frames(GTMaterials.Steel))
                    .where("K", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("M", Predicates.heatingCoils())
                    .where("B", abilities(PartAbility.MUFFLER).setExactLimit(1))
                    .where("G", Predicates.blocks(GTBlocks.CASING_STEEL_SOLID.get()).setMinGlobalLimited(15)
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1)))
                    .where("#", Predicates.blocks(Blocks.WATER))
                    .where(" ", Predicates.any())
                    .build())
            .additionalDisplay((machine, l) -> {
                if (machine.isFormed() && machine instanceof CoilWorkableElectricMultiblockMachine cmachine) {
                    l.add(Component.translatable("gtceu.multiblock.blast_furnace.max_temperature", Component.literal(cmachine.getCoilType().getCoilTemperature() + "K").withStyle(ChatFormatting.RED)));
                }
            })
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_solid_steel"), GTCEu.id("block/multiblock/implosion_compressor"))
            .register();

    public static final MultiblockMachineDefinition BIO_REACTOR = REGISTRATE.multiblock("bio_reactor", BioMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.BIO_REACTOR)
            .tooltips(Component.translatable("ctnh.multiblock.bio_reactor.tooltip.0").withStyle(ChatFormatting.GRAY))
            .recipeModifiers(BioMachine::recipeModifier, OC_NON_PERFECT, BATCH_MODE)
            .appearanceBlock(BIO_REACTOR_CASING)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAA", "ABBBA", "ABBBA", "ABBBA", "AAAAA")
                    .aisle("AAAAA", "B###B", "B###B", "B###B", "AAAAA")
                    .aisle("AAAAA", "B###B", "B###B", "B###B", "AAAAA")
                    .aisle("AAAAA", "B###B", "B###B", "B###B", "AAAAA")
                    .aisle("AA@AA", "ABBBA", "ABBBA", "ABBBA", "AAAAA")
                    .where("A", Predicates.blocks(BIO_REACTOR_CASING.get()).setMinGlobalLimited(35)
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1)))
                    .where("B", Predicates.blocks(CLEANROOM_GLASS.get()))
                    .where("#", Predicates.air())
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel(CTNHCore.id("block/casings/bio_reactor_casing"), GTCEu.id("block/multiblock/implosion_compressor"))
            .register();


    public static final MultiblockMachineDefinition SUPER_CENTRIFUGE = REGISTRATE.multiblock("super_centrifuge", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeTypes(GTRecipeTypes.CENTRIFUGE_RECIPES, CTNHRecipeTypes.DIFFERENTIAL_CENTRIFUGE_RECIPES)
            .appearanceBlock(CASING_TITANIUM_STABLE)
            .recipeModifiers((metaMachine, group, gtRecipe) -> {
                if (gtRecipe.getType().equals(GTRecipeTypes.CENTRIFUGE_RECIPES)) {
                    return CTNHRecipeModifiers.accurateParallel(metaMachine, group, gtRecipe, 8);
                }
                return null;
            }, GTRecipeModifiers.OC_NON_PERFECT)
            .tooltips(Component.translatable("super_centrifuge").withStyle(ChatFormatting.GRAY),
                    Component.translatable("ctnh.super_centrifuge.parallel"))
            .pattern(definition -> FactoryBlockPattern.start()
                .aisle("#BBB#", "BBBBB", "#BBB#", "BBBBB", "#BBB#")
                .aisle("BBBBB", "B#C#B", "B#C#B", "B#C#B", "BBBBB")
                .aisle("BBBBB", "BC#CB", "BC#CB", "BC#CB", "BBBBB")
                .aisle("BBBBB", "B#C#B", "B#C#B", "B#C#B", "BBBBB")
                .aisle("#BBB#", "BBBBB", "#B@B#", "BBBBB", "#BBB#")
                .where("B", Predicates.blocks(CASING_TITANIUM_STABLE.get()).setMinGlobalLimited(40)
                        .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1)))
                .where("#", Predicates.any())
                .where("C", Predicates.blocks(CASING_TITANIUM_PIPE.get()))
                .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                .build()
            )
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_stable_titanium"), GTCEu.id("block/multiblock/implosion_compressor"))
            .register();
    public static final MultiblockMachineDefinition ULTRASONIC_APPARATUS = REGISTRATE.multiblock("ultrasonic_apparatus", CoilWorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTNHRecipeTypes.ULTRASONICATION_RECIPES)
            .appearanceBlock(CASING_STAINLESS_CLEAN)
            .tooltips(Component.translatable("ultrasonic_apparatus").withStyle(ChatFormatting.GRAY))
            .pattern(definition -> FactoryBlockPattern.start()
                .aisle("##BCFCB##", "#BBCFCBB#", "##BCFCB##")
                .aisle("#BBCFCBB#", "BDEEEEEDB", "#BBCFCBB#")
                .aisle("##BCFCB##", "#BBC@CBB#", "##BCFCB##")
                .where("#", Predicates.any())
                .where("B", Predicates.blocks(CASING_STAINLESS_CLEAN.get()))
                .where("C", Predicates.heatingCoils())
                .where("D", Predicates.blocks(CASING_STAINLESS_STEEL_GEARBOX.get()))
                .where("E", Predicates.blocks(CASING_STEEL_PIPE.get()))
                .where("F",Predicates.blocks(CASING_STAINLESS_CLEAN.get())
                    .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1)))
                .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                .build()
            )
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"), GTCEu.id("block/multiblock/implosion_compressor"))
            .register();
}
// spotless:on
