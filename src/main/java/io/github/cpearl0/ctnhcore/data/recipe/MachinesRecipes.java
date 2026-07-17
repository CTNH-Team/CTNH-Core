package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.AdastraMaterials;
import io.github.cpearl0.ctnhcore.data.materials.AviationFabricMaterials;
import io.github.cpearl0.ctnhcore.data.materials.UncategorizedMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHBlocks;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import io.github.cpearl0.ctnhcore.registry.machines.CTNHMachines;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksA;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.api.recipe.ingredient.fluid.FluidIngredient;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.gregtechceu.gtceu.data.recipe.CraftingComponent;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluids;

import com.ctnh.ctnhastral.data.CATagPrefixes;
import com.simibubi.create.AllBlocks;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gregtechceu.gtceu.common.data.machines.GTResearchMachines.OBJECT_HOLDER;
import static com.gregtechceu.gtceu.data.recipe.GTCraftingComponents.*;
import static com.gregtechceu.gtceu.data.recipe.misc.MetaTileEntityLoader.registerMachineRecipe;
import static io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes.PHOTOVOLTAIC_ASSEMBER;
import static io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes.PHOTOVOLTAIC_GENERATOR;
import static io.github.cpearl0.ctnhcore.registry.machines.CTNHMachines.*;
import static twilightforest.init.TFItems.STEELEAF_INGOT;

public class MachinesRecipes {

    public static CraftingComponent MONITOR = CraftingComponent.of("monitor", COVER_SCREEN.asStack());

    public static void init(Consumer<FinishedRecipe> provider) {
        VanillaRecipeHelper.addShapedRecipe(provider, true, "underfloor_heating_system",
                MultiblocksA.UNDERFLOOR_HEATING_SYSTEM.asStack(),
                "SPS", "IwI", "SPS",
                'S', new ItemStack(AllBlocks.COPPER_SHINGLES.getStandard().get()),
                'P', GTBlocks.CASING_BRONZE_PIPE.asStack(),
                'I', new MaterialEntry(TagPrefix.plate, Gold));

        VanillaRecipeHelper.addShapedRecipe(provider, true, "ultimate_engine_intake_casing",
                CTNHBlocks.CASING_ULTIMATE_ENGINE_INTAKE.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft), "PhP",
                "RFR", "PwP", 'R', new MaterialEntry(TagPrefix.rotor, GTMaterials.NaquadahAlloy), 'F',
                CTNHBlocks.CASING_NAQUADAH_BLOCK.asStack(), 'P',
                new MaterialEntry(TagPrefix.pipeNormalFluid, GTMaterials.NaquadahAlloy));
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("zpm_large_miner"))
                .inputItems(GTMachines.HULL[ZPM].asStack())
                .inputItems(frameGt, Osmiridium, 4)
                .inputItems(CustomTags.ZPM_CIRCUITS, 4)
                .inputItems(ELECTRIC_MOTOR_ZPM, 4)
                .inputItems(ELECTRIC_PUMP_ZPM, 4)
                .inputItems(CONVEYOR_MODULE_ZPM, 4)
                .inputItems(gear, Osmiridium, 4)
                .circuitMeta(2)
                .outputItems(MultiblocksA.LARGE_DIGITAL_MINER[ZPM].asStack())
                .duration(400).EUt(VA[ZPM]).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("ev_large_miner"))
                .inputItems(GTMachines.HULL[EV])
                .inputItems(frameGt, Titanium, 4)
                .inputItems(CustomTags.IV_CIRCUITS, 4)
                .inputItems(ELECTRIC_MOTOR_EV, 4)
                .inputItems(ELECTRIC_PUMP_EV, 4)
                .inputItems(CONVEYOR_MODULE_EV, 4)
                .inputItems(gear, Tungsten, 4)
                .circuitMeta(2)
                .outputItems(MultiblocksA.LARGE_DIGITAL_MINER[EV])
                .duration(400).EUt(VA[EV]).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("iv_large_miner"))
                .inputItems(GTMachines.HULL[IV])
                .inputItems(frameGt, TungstenSteel, 4)
                .inputItems(CustomTags.IV_CIRCUITS, 4)
                .inputItems(ELECTRIC_MOTOR_IV, 4)
                .inputItems(ELECTRIC_PUMP_IV, 4)
                .inputItems(CONVEYOR_MODULE_IV, 4)
                .inputItems(gear, Iridium, 4)
                .circuitMeta(2)
                .outputItems(MultiblocksA.LARGE_DIGITAL_MINER[IV])
                .duration(400).EUt(VA[IV]).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("luv_large_miner"))
                .inputItems(GTMachines.HULL[LuV])
                .inputItems(frameGt, HSSS, 4)
                .inputItems(CustomTags.LuV_CIRCUITS, 4)
                .inputItems(ELECTRIC_MOTOR_LuV, 4)
                .inputItems(ELECTRIC_PUMP_LuV, 4)
                .inputItems(CONVEYOR_MODULE_LuV, 4)
                .inputItems(gear, Ruridit, 4)
                .circuitMeta(2)
                .outputItems(MultiblocksA.LARGE_DIGITAL_MINER[LuV])
                .duration(400).EUt(VA[LuV]).save(provider);

        COMPRESSOR_RECIPES.recipeBuilder(CTNHCore.id("steelleaf"))
                .duration(300)
                .EUt(2)
                .inputItems(dust, CTNHMaterials.SteelLeaf, 1)
                .outputItems(STEELEAF_INGOT, 1)
                .save(provider);

        MIXER_RECIPES.recipeBuilder(CTNHCore.id("plantfood"))
                .duration(500)
                .EUt(28)
                .inputItems(dust, CTNHMaterials.SpiritAsh, 4)
                .inputItems(dust, Apatite, 4)
                .outputItems(FERTILIZER, 16)
                .save(provider);

        CTNHRecipeTypes.ARC_REACTOR.recipeBuilder(CTNHCore.id("test"))
                .EUt(8192)
                .duration(20)
                .circuitMeta(1)
                .save(provider);

        CTNHRecipeTypes.VOID_MINER.recipeBuilder(CTNHCore.id("void"))
                .EUt(32678 * 64)
                .duration(20 * 100)
                .circuitMeta(1)
                .save(provider);

        CTNHRecipeTypes.PVDRONE.recipeBuilder(CTNHCore.id("blank"))
                .duration(100)
                .circuitMeta(1)
                .save(provider);

        CTNHRecipeTypes.PVDRONE.recipeBuilder(CTNHCore.id("meteorite_capture"))
                .duration(1000)
                .circuitMeta(2)
                .outputItems(CATagPrefixes.oreMoonStone, AdastraMaterials.Desh)
                .outputItems(CATagPrefixes.oreVenusStone, AdastraMaterials.Calorite)
                .outputItems(CATagPrefixes.oreMarsStone, AdastraMaterials.Ostrum)
                .EUt(VA[LuV])
                .save(provider);

        CTNHRecipeTypes.COMPILER_RECIPE.recipeBuilder(CTNHCore.id("test"))
                .inputItems(dust, CTNHMaterials.SteelLeaf, 1)
                .inputItems(dust, CTNHMaterials.SteelLeaf, 1)
                .inputItems(dust, CTNHMaterials.SteelLeaf, 1)
                .inputItems(dust, CTNHMaterials.SteelLeaf, 1)
                .inputItems(dust, CTNHMaterials.SteelLeaf, 1)
                .outputItems(dust, CTNHMaterials.SpiritAsh, 1)
                .addData("1", 48)
                .addData("2", 30)
                .addData("3", 30)
                .addData("noisea", 20)
                .addData("noiseb", 2)
                .addData("range", 120)
                .duration(20 * 30)
                .EUt(1000000)
                .save(provider);

        COMBUSTION_GENERATOR_FUELS.recipeBuilder(CTNHCore.id("end"))
                .inputFluids(CTNHMaterials.NQ_END_OF_GASOLINE.getFluid(1))
                .duration(1000)
                .EUt(-320)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("naquadah_gearbox_casing"))
                .inputItems(plate, NaquadahAlloy, 4)
                .inputItems(gear, NaquadahAlloy, 2)
                .inputItems(frameGt, NaquadahAlloy)
                .circuitMeta(4)
                .outputItems(CTNHBlocks.CASING_NAQUADAH_GEARBOX.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft))
                .duration(50).EUt(16).save(provider);

        VanillaRecipeHelper.addShapedRecipe(provider, true, "casing_naquadah_alloy_gearbox",
                CTNHBlocks.CASING_NAQUADAH_GEARBOX.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft), "PhP", "GFG",
                "PwP", 'P', new MaterialEntry(TagPrefix.plate, GTMaterials.NaquadahAlloy), 'F',
                new MaterialEntry(frameGt, GTMaterials.NaquadahAlloy), 'G',
                new MaterialEntry(gear, GTMaterials.NaquadahAlloy));

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("empty_program"))
                .inputItems(wireFine, RedAlloy, 8)
                .inputItems(plate, Steel, 2)
                .inputFluids(FluidIngredient.of(Fluids.WATER, 1000))
                .outputItems(CTNHItems.PROGRAM_EMPTY.asStack())
                .EUt(30)
                .duration(200)
                .save(provider);

        registerMachineRecipe(provider,
                CTNHMachines.PERSONAL_COMPUTER,
                "PDP",
                "CAC",
                "PBP",
                'A', HULL, 'C', ROTOR, 'P', CABLE, 'D', CIRCUIT, 'B', SENSOR);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("mv_oxygen_enricher"))
                .inputItems(GTMachines.HULL[MV].asStack())
                .inputItems(ELECTRIC_PUMP_MV.asStack(2))
                .inputItems(FLUID_REGULATOR_MV.asStack())
                .inputItems(EMITTER_MV.asStack())
                .inputItems(SENSOR_MV.asStack())
                .inputItems(CustomTags.HV_CIRCUITS, 2)
                .inputFluids(GTMaterials.SolderingAlloy.getFluid(144))
                .outputItems(CTNHMachines.OXYGEN_ENRICHER[MV].asStack())
                .duration(200)
                .EUt(VA[MV])
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("hv_oxygen_enricher"))
                .inputItems(GTMachines.HULL[HV].asStack())
                .inputItems(ELECTRIC_PUMP_HV.asStack(2))
                .inputItems(FLUID_REGULATOR_HV.asStack())
                .inputItems(EMITTER_HV.asStack())
                .inputItems(SENSOR_HV.asStack())
                .inputItems(CustomTags.EV_CIRCUITS, 2)
                .inputFluids(GTMaterials.SolderingAlloy.getFluid(288))
                .outputItems(CTNHMachines.OXYGEN_ENRICHER[HV].asStack())
                .duration(200)
                .EUt(VA[HV])
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("ev_oxygen_enricher"))
                .inputItems(GTMachines.HULL[EV].asStack())
                .inputItems(ELECTRIC_PUMP_EV.asStack(2))
                .inputItems(FLUID_REGULATOR_EV.asStack())
                .inputItems(EMITTER_EV.asStack())
                .inputItems(SENSOR_EV.asStack())
                .inputItems(CustomTags.IV_CIRCUITS, 2)
                .inputFluids(GTMaterials.SolderingAlloy.getFluid(432))
                .outputItems(CTNHMachines.OXYGEN_ENRICHER[EV].asStack())
                .duration(200)
                .EUt(VA[EV])
                .save(provider);

        CTNHRecipeTypes.OXYGEN_ENRICHER_RECIPES.recipeBuilder(CTNHCore.id("oxygen_enrichment"))
                .inputFluids(GTMaterials.Oxygen.getFluid(10))
                .duration(20)
                .EUt(VA[MV] / 2)
                .save(provider);

        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("circuit_bus"),
                CIRCUIT_BUS.asStack(),
                "A",
                "B",
                'A', MONITOR.get(0),
                'B', CASING_STAINLESS_CLEAN.asStack());

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("astronomy_circuit"))
                .inputItems(PLASTIC_CIRCUIT_BOARD)
                .inputItems(CENTRAL_PROCESSING_UNIT, 2)
                .inputItems(NAND_MEMORY_CHIP, 32)
                .inputItems(RANDOM_ACCESS_MEMORY, 4)
                .inputItems(wireFine, Tin, 16)
                .inputItems(plate, Polyethylene, 4)
                .outputItems(CTNHItems.ASTRONOMY_CIRCUIT_1.asStack())
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(400).EUt(90).save(provider);

        PHOTOVOLTAIC_GENERATOR.recipeBuilder(CTNHCore.id("test"))
                .duration(20)
                .EUt(-1)
                .circuitMeta(1)
                .save(provider);

        PHOTOVOLTAIC_ASSEMBER.recipeBuilder(CTNHCore.id("test"))
                .duration(20)
                // .EUt(-1)
                .circuitMeta(1)
                .addData("input", 32678)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("drone_holder"))
                .duration(100)
                .EUt(32678 * 4)
                .circuitMeta(1)
                .inputItems(OBJECT_HOLDER)
                .outputItems(DRONEHOLDER)
                .inputItems(CustomTags.UV_CIRCUITS, 4)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("lv_digital_miner"))
                .inputItems(GTMachines.HULL[LV])
                .inputItems(ELECTRIC_MOTOR_LV, 3)
                .inputItems(cableGtSingle, Tin, 2)
                .inputItems(CustomTags.LV_CIRCUITS, 2)
                .inputItems(CONVEYOR_MODULE_LV.asStack(2))
                .inputItems(ROBOT_ARM_LV.asStack(2))
                .inputItems(EMITTER_LV.asStack())
                .inputItems(SENSOR_LV.asStack(2))
                .inputItems(CustomTags.MV_CIRCUITS, 2)
                .inputFluids(GTMaterials.SolderingAlloy.getFluid(144))
                .outputItems(CTNHMachines.DIGITAL_MINER[LV].asStack())
                .duration(200)
                .EUt(VA[LV])
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("mv_digital_miner"))
                .inputItems(GTMachines.HULL[MV])
                .inputItems(ELECTRIC_MOTOR_MV, 3)
                .inputItems(cableGtSingle, Copper, 2)
                .inputItems(CustomTags.MV_CIRCUITS, 2)
                .inputItems(CONVEYOR_MODULE_MV.asStack(2))
                .inputItems(ROBOT_ARM_MV.asStack(2))
                .inputItems(EMITTER_MV.asStack())
                .inputItems(SENSOR_MV.asStack(2))
                .inputItems(CustomTags.HV_CIRCUITS, 2)
                .inputFluids(GTMaterials.SolderingAlloy.getFluid(144))
                .outputItems(CTNHMachines.DIGITAL_MINER[MV].asStack())
                .duration(200)
                .EUt(VA[MV])
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("hv_digital_miner"))
                .inputItems(GTMachines.HULL[HV])
                .inputItems(ELECTRIC_MOTOR_HV, 3)
                .inputItems(cableGtSingle, Gold, 2)
                .inputItems(CustomTags.HV_CIRCUITS, 2)
                .inputItems(CONVEYOR_MODULE_HV.asStack(2))
                .inputItems(ROBOT_ARM_HV.asStack(2))
                .inputItems(EMITTER_HV.asStack())
                .inputItems(SENSOR_HV.asStack(2))
                .inputItems(CustomTags.EV_CIRCUITS, 2)
                .inputFluids(GTMaterials.SolderingAlloy.getFluid(144))
                .outputItems(CTNHMachines.DIGITAL_MINER[HV].asStack())
                .duration(200)
                .EUt(VA[HV])
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("uv_neuro_compiler"))
                .inputItems(HUGE_DUAL_IMPORT_HATCH[UV].asStack())
                .inputItems(CustomTags.UHV_CIRCUITS, 4)
                .inputItems(plateDense, UncategorizedMaterials.NAQUADAH_HEAT_RESISTANT_FERROCHROME_ALLOY_792, 7)
                .inputItems(CONVEYOR_MODULE_UV, 2)
                .inputItems(SENSOR_UV)
                .inputItems(plateDense, UncategorizedMaterials.RADIATION_SIGHT_ALLOY_X, 7)
                .inputFluids(Naquadria.getFluid(1000))
                .inputFluids(AviationFabricMaterials.KAPTON_K.getFluid(1000))
                .outputItems(COMPILERMACHINE[UV].asStack())
                .stationResearch(b -> b
                        .researchStack(HUGE_DUAL_IMPORT_HATCH[UV].asStack())
                        .CWUt(28)
                        .EUt(VA[ZPM]))
                .duration(100)
                .EUt(VA[UV])
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("uhv_neuro_compiler"))
                .inputItems(HUGE_DUAL_IMPORT_HATCH[UHV].asStack())
                .inputItems(CustomTags.UEV_CIRCUITS, 4)
                .inputItems(plateDense, UncategorizedMaterials.NAQUADAH_HEAT_RESISTANT_FERROCHROME_ALLOY_792, 7)
                .inputItems(CONVEYOR_MODULE_UHV, 2)
                .inputItems(SENSOR_UHV)
                .inputItems(plateDense, UncategorizedMaterials.RADIATION_SIGHT_ALLOY_INF, 7)
                .inputFluids(Naquadria.getFluid(1000))
                .inputFluids(AviationFabricMaterials.KAPTON_K.getFluid(1000))
                .outputItems(COMPILERMACHINE[UHV].asStack())
                .stationResearch(b -> b
                        .researchStack(COMPILERMACHINE[UV].asStack())
                        .CWUt(56)
                        .EUt(VA[UHV]))
                .duration(100)
                .EUt(VA[UHV])
                .save(provider);
    }
}
