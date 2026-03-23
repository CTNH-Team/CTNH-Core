package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.UncategorizedMaterials;

import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.common.data.GCYMRecipeTypes;
import com.gregtechceu.gtceu.common.data.machines.GCYMMachines;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.GTCraftingComponents;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;

import com.moguang.ctnhmana.registry.CMMaterials;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys.PLASMA;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static io.github.cpearl0.ctnhcore.data.materials.BedrockMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHBlocks.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHItems.*;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.*;

public class UHVPartsRecipe {

    public static void init(Consumer<FinishedRecipe> provider) {
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("electric_motor_uhv"))
                .inputItems(rodLong, SAMARIUM_DYSPROSIUM_TERBIUM_PERMANENT_MAGNET_ALLOY_MAGNETIC)
                .inputItems(rodLong, ADAMANTITE, 4)
                .inputItems(ring, ADAMANTITE, 4)
                .inputItems(round, ADAMANTITE, 8)
                .inputItems(wireFine, RutheniumTriniumAmericiumNeutronate, 64)
                .inputItems(wireFine, RutheniumTriniumAmericiumNeutronate, 64)
                .inputItems(cableGtSingle, Europium, 2)
                .inputFluids(SolderingAlloy, L * 4)
                .inputFluids(Lubricant, 2000)
                .inputFluids(Neutronium, L * 4)
                .outputItems(ELECTRIC_MOTOR_UHV)
                .stationResearch(b -> b
                        .researchStack(ELECTRIC_MOTOR_UV.asStack())
                        .CWUt(32)
                        .EUt(VA[UV]))
                .duration(600).EUt(388000)
                .addMaterialInfo(true).save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("electric_pump_uhv"))
                .inputItems(ELECTRIC_MOTOR_UHV)
                .inputItems(pipeLargeFluid, Duranium)
                .inputItems(plate, ADAMANTITE, 2)
                .inputItems(screw, ADAMANTITE, 8)
                .inputItems(ring, SiliconeRubber, 16)
                .inputItems(rotor, Darmstadtium)
                .inputItems(cableGtSingle, Europium, 2)
                .inputFluids(SolderingAlloy, L * 4)
                .inputFluids(Lubricant, 2000)
                .inputFluids(Neutronium, L * 4)
                .outputItems(ELECTRIC_PUMP_UHV)
                .stationResearch(b -> b
                        .researchStack(ELECTRIC_PUMP_UV.asStack())
                        .CWUt(32)
                        .EUt(VA[UV]))
                .duration(600).EUt(388000)
                .addMaterialInfo(true).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("fluid_regulator_uhv"))
                .inputItems(ELECTRIC_PUMP_UHV)
                .inputItems(CustomTags.UHV_CIRCUITS, 2)
                .circuitMeta(1)
                .outputItems(FLUID_REGULATOR_UHV)
                .EUt(VA[UHV])
                .duration(50)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("electric_piston_uhv"))
                .inputItems(ELECTRIC_MOTOR_UHV)
                .inputItems(plate, ADAMANTITE, 4)
                .inputItems(ring, ADAMANTITE, 4)
                .inputItems(round, ADAMANTITE, 16)
                .inputItems(rod, ADAMANTITE, 4)
                .inputItems(gear, ADAMANTITE)
                .inputItems(gearSmall, Darmstadtium, 2)
                .inputItems(cableGtSingle, Europium, 2)
                .inputFluids(SolderingAlloy, L * 4)
                .inputFluids(Lubricant, 2000)
                .inputFluids(Neutronium, L * 4)
                .outputItems(ELECTRIC_PISTON_UHV)
                .stationResearch(b -> b
                        .researchStack(ELECTRIC_PISTON_UV.asStack())
                        .CWUt(32)
                        .EUt(VA[UV]))
                .duration(600).EUt(388000)
                .addMaterialInfo(true).save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("conveyor_module_uhv"))
                .inputItems(ELECTRIC_MOTOR_UHV, 2)
                .inputItems(plate, ADAMANTITE, 2)
                .inputItems(ring, ADAMANTITE, 4)
                .inputItems(round, ADAMANTITE, 16)
                .inputItems(screw, ADAMANTITE, 4)
                .inputItems(cableGtSingle, Europium, 2)
                .inputFluids(SolderingAlloy, L * 4)
                .inputFluids(Lubricant, 2000)
                .inputFluids(StyreneButadieneRubber, L * 24)
                .inputFluids(Neutronium, L * 4)
                .outputItems(CONVEYOR_MODULE_UHV)
                .stationResearch(b -> b
                        .researchStack(CONVEYOR_MODULE_UV.asStack())
                        .CWUt(32)
                        .EUt(VA[UV]))
                .duration(600).EUt(388000)
                .addMaterialInfo(true).save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("robot_arm_uhv"))
                .inputItems(rodLong, ADAMANTITE, 4)
                .inputItems(gear, ADAMANTITE)
                .inputItems(gearSmall, ADAMANTITE, 3)
                .inputItems(ELECTRIC_MOTOR_UHV, 2)
                .inputItems(ELECTRIC_PISTON_UHV)
                .inputItems(CustomTags.UHV_CIRCUITS)
                .inputItems(CustomTags.UV_CIRCUITS, 2)
                .inputItems(CustomTags.ZPM_CIRCUITS, 4)
                .inputItems(cableGtSingle, Europium, 4)
                .inputFluids(SolderingAlloy, L * 12)
                .inputFluids(Lubricant, 2000)
                .inputFluids(Neutronium, L * 4)
                .outputItems(ROBOT_ARM_UHV)
                .stationResearch(b -> b
                        .researchStack(ROBOT_ARM_UV.asStack())
                        .CWUt(32)
                        .EUt(VA[UV]))
                .duration(600).EUt(388000)
                .addMaterialInfo(true).save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("sensor_uhv"))
                .inputItems(frameGt, ADAMANTITE)
                .inputItems(ELECTRIC_MOTOR_UHV)
                .inputItems(plate, ADAMANTITE, 4)
                .inputItems(GTCraftingComponents.SENSOR_EMITTER_GEM.get(UV)) // 目前尚无 UHV
                .inputItems(CustomTags.UHV_CIRCUITS, 2)
                .inputItems(foil, Tritanium, 64)
                .inputItems(foil, Tritanium, 32)
                .inputItems(cableGtSingle, Europium, 4)
                .inputFluids(SolderingAlloy, L * 8)
                .inputFluids(Neutronium, L * 4)
                .outputItems(SENSOR_UHV)
                .stationResearch(b -> b
                        .researchStack(SENSOR_UV.asStack())
                        .CWUt(64)
                        .EUt(VA[UV]))
                .duration(600).EUt(388000)
                .addMaterialInfo(true).save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("emitter_uhv"))
                .inputItems(frameGt, ADAMANTITE)
                .inputItems(ELECTRIC_MOTOR_UHV)
                .inputItems(rodLong, ADAMANTITE, 4)
                .inputItems(GTCraftingComponents.SENSOR_EMITTER_GEM.get(UV)) // 目前尚无 UHV
                .inputItems(CustomTags.UHV_CIRCUITS, 2)
                .inputItems(foil, Tritanium, 64)
                .inputItems(foil, Tritanium, 32)
                .inputItems(cableGtSingle, Europium, 4)
                .inputFluids(SolderingAlloy, L * 8)
                .inputFluids(Neutronium, L * 4)
                .outputItems(EMITTER_UHV)
                .stationResearch(b -> b
                        .researchStack(EMITTER_UV.asStack())
                        .CWUt(64)
                        .EUt(VA[UV]))
                .duration(600).EUt(388000)
                .addMaterialInfo(true).save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("field_generator_uhv"))
                .inputItems(frameGt, ADAMANTITE)
                .inputItems(plate, ADAMANTITE, 6)
                .inputItems(GRAVI_STAR)
                .inputItems(EMITTER_UHV, 2)
                .inputItems(CustomTags.UHV_CIRCUITS, 2)
                .inputItems(wireFine, Abyssalalloy, 64)
                .inputItems(wireFine, Abyssalalloy, 64)
                .inputItems(cableGtSingle, Europium, 4)
                .inputFluids(SolderingAlloy, L * 12)
                .inputFluids(Neutronium, L * 4)
                .outputItems(FIELD_GENERATOR_UHV)
                .stationResearch(b -> b
                        .researchStack(FIELD_GENERATOR_UV.asStack())
                        .CWUt(64)
                        .EUt(VA[UV]))
                .duration(600).EUt(388000)
                .addMaterialInfo(true).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("uhv_voltage_coil"))
                .inputItems(rod, SAMARIUM_DYSPROSIUM_TERBIUM_PERMANENT_MAGNET_ALLOY_MAGNETIC)
                .inputItems(wireFine, ADAMANTITE, 16)
                .outputItems(VOLTAGE_COIL_UHV)
                .circuitMeta(1)
                .EUt(VA[UHV])
                .duration(200)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("uhv_energy_output_hatch"))
                .inputItems(HULL[UHV])
                .inputItems(spring, Europium, 4)
                .inputItems(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 2)
                .inputItems(CustomTags.UHV_CIRCUITS)
                .inputItems(VOLTAGE_COIL_UHV, 2)
                .inputFluids(SodiumPotassium, 12000)
                .inputFluids(SolderingAlloy, 40 * L)
                .outputItems(ENERGY_OUTPUT_HATCH[UHV])
                .stationResearch(b -> b
                        .researchStack(ENERGY_OUTPUT_HATCH[UV].asStack())
                        .CWUt(128)
                        .EUt(VA[UV]))
                .duration(1000).EUt(VA[UHV]).save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("uhv_energy_input_hatch"))
                .inputItems(HULL[UHV])
                .inputItems(cableGtSingle, Europium, 4)
                .inputItems(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 2)
                .inputItems(CustomTags.UHV_CIRCUITS)
                .inputItems(VOLTAGE_COIL_UHV, 2)
                .inputFluids(SodiumPotassium, 12000)
                .inputFluids(SolderingAlloy, 40 * L)
                .outputItems(ENERGY_INPUT_HATCH[UHV])
                .stationResearch(b -> b
                        .researchStack(ENERGY_INPUT_HATCH[UV].asStack())
                        .CWUt(128)
                        .EUt(VA[UV]))
                .duration(1000).EUt(VA[UHV]).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("abyssalalloy_coil_block"))
                .inputItems(wireGtDouble, Abyssalalloy, 8)
                .inputItems(foil, Trinium, 8)
                .inputFluids(Trinium.getFluid(144))
                .outputItems(COIL_ABYSALALLOY)
                .EUt(VA[UHV])
                .duration(900)
                .save(provider);

        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("uhv_rotor_holder"),
                ROTOR_HOLDER[UHV].asStack(),
                "ABA",
                "BCB",
                "ABA",
                'A', new MaterialEntry(gear, Neutronium),
                'B', new MaterialEntry(gear, ADAMANTITE),
                'C', HULL[UHV].asStack());

        GCYMRecipeTypes.ALLOY_BLAST_RECIPES.recipeBuilder(CTNHCore.id("end_to_the_end")) // from 80extend.js
                .inputItems(dust, SNOW_STEEL, 64)
                .inputItems(dust, UncategorizedMaterials.QUANTUM_ALLOY, 64)
                .inputItems(dust, HiddenAlloy, 64)
                .inputItems(dust, SpecialCompositeSteelM77, 64)
                .inputItems(dust, CMMaterials.Ultra_Mana, 64)
                .inputItems(gemExquisite, COLORFUL_GEM, 64)
                .inputItems(dust, BOUNDLESS, 1)
                .inputItems(CustomTags.UEV_CIRCUITS, 64)
                .inputItems(GCYMMachines.MEGA_BLAST_FURNACE)
                .inputFluids(LIVING_METAL.getFluid(114514))
                .inputFluids(CMMaterials.Eve_Beam.getFluid(PLASMA, 114514))
                .inputFluids(SUPERFUELMK1.getFluid(6666))
                .outputFluids(UncategorizedMaterials.ORACLE.getFluid(1145))
                .EUt(VA[UEV])
                .duration(200000)
                .blastFurnaceTemp(12600)
                .save(provider);
    }
}
