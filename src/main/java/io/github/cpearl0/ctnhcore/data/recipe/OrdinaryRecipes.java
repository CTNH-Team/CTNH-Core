package io.github.cpearl0.ctnhcore.data.recipe;

import appeng.core.definitions.AEParts;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHBlocks;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.common.data.machines.GTAEMachines;
import com.gregtechceu.gtceu.data.recipe.CustomTags;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import com.moguang.ctnhbio.data.recipe.CBRecipeBuilder;
import com.moguang.ctnhbio.registry.CBBlocks;
import com.moguang.ctnhbio.registry.CBItems;
import com.moguang.ctnhbio.registry.CBMachines;
import tech.luckyblock.mcmod.ctnhenergy.registry.CEBlocks;
import tech.luckyblock.mcmod.ctnhenergy.registry.CEItems;
import tech.luckyblock.mcmod.ctnhenergy.registry.CEMachines;
import tech.vixhentx.mcmod.ctnhlib.registrate.builders.CTNHMaterial;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.OPTICAL_PIPES;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.moguang.ctnhbio.registry.CBItems.WETWARE_PRINTED_CIRCUIT_BOARD;
import static io.github.cpearl0.ctnhcore.data.materials.WetWareLineMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHItems.ADVANCED_RAM_CHIP;
import static io.github.cpearl0.ctnhcore.registry.CTNHItems.HEAVY_PLATE_T3;

public class OrdinaryRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 磁选
        // CBRecipeBuilder 的配方已经使用了 CTNHCore.id()，不需要修改
        CBRecipeBuilder.of(CTNHCore.id("calcite_electromagnetic"), GTRecipeTypes.ELECTROMAGNETIC_SEPARATOR_RECIPES)
                .inputItems(dust, Calcite, 3)
                .outputItems(dust, CalciumCarbonate, 1)
                .outputItems(dust, Magnetite, 1)
                .outputItems(dust, Magnetite, 1)
                .duration(50)
                .EUt(108)
                .save(provider);

// 小化反
        CBRecipeBuilder.of(CTNHCore.id("fenton_reagent_mixing"), GTRecipeTypes.CHEMICAL_RECIPES)
                .inputFluids(GTMaterials.Iron2Chloride, 1000)
                .inputFluids(HydrogenPeroxide, 1000)
                .outputFluids(new FluidStack(FENTONS_REAGENT.getFluid(), 2000))
                .duration(100)
                .EUt(1920)
                .save(provider);

        CBRecipeBuilder.of(CTNHCore.id("polypyrrole_from_fenton"), GTRecipeTypes.CHEMICAL_RECIPES)
                .inputFluids(new FluidStack(PYRROLE.getFluid(), 1000))
                .inputFluids(new FluidStack(FENTONS_REAGENT.getFluid(), 1000))
                .outputFluids(new FluidStack(POLYPYRROLE.getFluid(), 1000))
                .outputFluidsRanged(new FluidStack(Iron3Chloride.getFluid(), 500), UniformInt.of(100, 200))
                .duration(200)
                .EUt(960)
                .save(provider);

        CBRecipeBuilder.of(CTNHCore.id("ferric_to_ferrous_chloride"), GTRecipeTypes.CHEMICAL_RECIPES)
                .inputFluids(new FluidStack(GTMaterials.Iron3Chloride.getFluid(), 2000))
                .inputItems(dust, Iron)
                .outputFluids(new FluidStack(GTMaterials.Iron2Chloride.getFluid(), 3000))
                .duration(100)
                .EUt(480)
                .save(provider);

// 大化反
        CBRecipeBuilder.of(CTNHCore.id("fenton_reagent_mixing_large"), GTRecipeTypes.LARGE_CHEMICAL_RECIPES)  // 修改了ID避免重复
                .inputFluids(GTMaterials.Iron2Chloride, 1000)
                .inputFluids(HydrogenPeroxide, 1000)
                .outputFluids(new FluidStack(FENTONS_REAGENT.getFluid(), 2000))
                .duration(100)
                .EUt(1920)
                .save(provider);

        CBRecipeBuilder.of(CTNHCore.id("polypyrrole_from_fenton_large"), GTRecipeTypes.LARGE_CHEMICAL_RECIPES)  // 修改了ID避免重复
                .inputFluids(new FluidStack(PYRROLE.getFluid(), 1000))
                .inputFluids(new FluidStack(FENTONS_REAGENT.getFluid(), 1000))
                .outputFluids(new FluidStack(POLYPYRROLE.getFluid(), 1000))
                .outputFluidsRanged(new FluidStack(Iron3Chloride.getFluid(), 500), UniformInt.of(100, 200))
                .outputFluidsRanged(new FluidStack(GTMaterials.Water.getFluid(), 500), UniformInt.of(100, 200))
                .duration(200)
                .EUt(960)
                .save(provider);

        CBRecipeBuilder.of(CTNHCore.id("ferric_to_ferrous_chloride_large"), GTRecipeTypes.LARGE_CHEMICAL_RECIPES)  // 修改了ID避免重复
                .inputFluids(new FluidStack(GTMaterials.Iron3Chloride.getFluid(), 2000))
                .inputItems(dust, Iron)
                .outputFluids(new FluidStack(GTMaterials.Iron2Chloride.getFluid(), 3000))
                .duration(100)
                .EUt(480)
                .save(provider);

// 蒸馏室
        CBRecipeBuilder.of(CTNHCore.id("coal_tar_distillation"), GTRecipeTypes.DISTILLERY_RECIPES)  // 修改了ID避免重复
                .circuitMeta(6)
                .inputFluids(new FluidStack(CoalTar.getFluid(), 1000))
                .outputFluids(new FluidStack(PYRROLE.getFluid(), 800))
                .duration(120)
                .EUt(384)
                .save(provider);

// 切割机
        CBRecipeBuilder.of(CTNHCore.id("advanced_ram_chip_recipe"), GTRecipeTypes.CUTTER_RECIPES)
                .inputItems(CTNHItems.ADVANCED_RAM_WAFER.get().getDefaultInstance())
                .inputFluids(Lubricant, 500)
                .outputItems(ADVANCED_RAM_CHIP, 16)
                .duration(900)
                .EUt(1920)
                .cleanroom(CleanroomType.CLEANROOM)
                .save(provider);

// 激光蚀刻
        CBRecipeBuilder.of(CTNHCore.id("advanced_ram_wafer_p_recipe"), GTRecipeTypes.LASER_ENGRAVER_RECIPES)
                .inputItems(PHOSPHORUS_WAFER.get().getDefaultInstance())
                .notConsumable(lens, CTNHMaterials.EuropiumFluorite)
                .outputItems(CTNHItems.ADVANCED_RAM_WAFER.get().getDefaultInstance())
                .duration(450)
                .EUt(1920)
                .cleanroom(CleanroomType.CLEANROOM)
                .save(provider);

        CBRecipeBuilder.of(CTNHCore.id("advanced_ram_wafer_na_recipe"), GTRecipeTypes.LASER_ENGRAVER_RECIPES)
                .inputItems(NAQUADAH_WAFER.get().getDefaultInstance())
                .notConsumable(lens, CTNHMaterials.EuropiumFluorite)
                .outputItems(CTNHItems.ADVANCED_RAM_WAFER.get().getDefaultInstance(), 4)
                .duration(450)
                .EUt(6144)
                .cleanroom(CleanroomType.CLEANROOM)
                .save(provider);

        CBRecipeBuilder.of(CTNHCore.id("advanced_ram_wafer_ne_recipe"), GTRecipeTypes.LASER_ENGRAVER_RECIPES)
                .inputItems(NEUTRONIUM_WAFER.get().getDefaultInstance())
                .notConsumable(lens, CTNHMaterials.EuropiumFluorite)
                .outputItems(CTNHItems.ADVANCED_RAM_WAFER.get().getDefaultInstance(), 8)
                .duration(450)
                .EUt(30720)
                .cleanroom(CleanroomType.CLEANROOM)
                .save(provider);

        CBRecipeBuilder.of(CTNHCore.id("bio_flexible_wire_pvb"), CTNHRecipeTypes.PVB_RECIPE)  // 修改了ID避免重复
                .inputFluids(POLYPYRROLE, 288)
                .inputItems(wireFine, BLUE_TITANIUM_ALLOY, 16)
                .outputItems(wireFine, BIO_FLEXIBLE, 16)
                .duration(200)
                .EUt(30720)
                .cleanroom(CleanroomType.CLEANROOM)
                .save(provider);

// 组装机
        CBRecipeBuilder.of(CTNHCore.id("neural_network_casing"), ASSEMBLER_RECIPES)
                .inputItems(CTNHBlocks.CASING_POLYBENZIMIDAZOLE_PIPE, 1)
                .inputItems(CustomTags.IV_CIRCUITS, 4)
                .inputItems(EMITTER_IV, 4)
                .inputItems(CTNHItems.HEAVY_PLATE_T2, 16)
                .inputItems(CARBON_FIBER_PLATE, 16)
                .inputItems(TagPrefix.plate, BlackSteel, 16)
                .inputFluids(new FluidStack(CTNHMaterials.Cerrobase140.getFluid(), 432))
                .outputItems(CBBlocks.NEURAL_NETWORK_CASING, 4)
                .duration(600)
                .EUt(30720)
                .save(provider);

        CBRecipeBuilder.of(CTNHCore.id("neural_model_accessor"), ASSEMBLER_RECIPES)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("hostilenetworks:sim_chamber")), 1)
                .inputItems(OPTICAL_PIPES[0].asStack(64))
                .inputItems(TOOL_DATA_STICK, 64)
                .inputItems(HEAVY_PLATE_T3, 16)
                .inputFluids(new FluidStack(CTNHMaterials.Cerrobase140.getFluid(), 288))
                .outputItems(CBMachines.NEURAL_MODEL_ACCESSOR, 1)
                .duration(800)
                .EUt(30720)
                .save(provider);

// 以下使用ASSEMBLER_RECIPES.recipeBuilder的配方需要修改
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("card_programmed_circuit"))
                .inputItems(AEItems.BASIC_CARD.asItem(), 1)
                .inputItems(GTMachines.STAINLESS_STEEL_CRATE, 1)
                .inputItems(CBItems.SYNET_CORE, 1)
                .inputItems(COVER_SCREEN, 32)
                .inputFluids(new FluidStack(SolderingAlloy.getFluid(), 1440))
                .outputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("pccard:card_programmed_circuit")),
                        1)
                .EUt(GTValues.VA[GTValues.HV])
                .duration(200)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("me_dual_output_hatch"))
                .inputItems(HULL[IV])
                .inputItems(GTAEMachines.ITEM_EXPORT_BUS_ME, 1)
                .inputItems(GTAEMachines.FLUID_EXPORT_HATCH_ME, 1)
                .inputFluids(new FluidStack(SolderingAlloy.getFluid(), 2880))
                .outputItems(CEMachines.DUAL_OUTPUT_HATCH_ME, 1)
                .EUt(GTValues.VA[GTValues.IV])
                .duration(500)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("quantum_computer_casing"))
                .inputItems(GTBlocks.SUPERCONDUCTING_COIL, 1)
                .inputItems(CustomTags.LuV_CIRCUITS, 4)
                .inputItems(CustomTags.IV_CIRCUITS, 4)
                .inputItems(AEItems.CALCULATION_PROCESSOR.asItem(), 16)
                .inputItems(AEItems.ENGINEERING_PROCESSOR.asItem(), 16)
                .inputItems(AEItems.LOGIC_PROCESSOR.asItem(), 16)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ae2omnicells:omni_link_processor")),
                        16)
                .inputItems(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ae2omnicells:complex_link_processor")),
                        16)
                .inputFluids(new FluidStack(BlueAlloy.getFluid(), 576))
                .outputItems(CEBlocks.QUANTUM_COMPUTER_CASING, 16)
                .EUt(GTValues.VA[LuV])
                .duration(1000)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("quantum_computer_me_network_port"))
                .inputItems(CEBlocks.STEADY_STATE_COMPUTING_MATRIX_SHELL, 1)
                .inputItems(AEBlocks.INTERFACE.asItem(), 1)
                .inputItems(AEBlocks.PATTERN_PROVIDER.asItem(), 1)
                .inputItems(GTItems.QUBIT_CENTRAL_PROCESSING_UNIT, 64)
                .inputItems(QUANTUM_EYE, 32)
                .inputItems(QUANTUM_STAR, 16)
                .inputFluids(new FluidStack(Argon.getFluid(), 5760))
                .outputItems(CEBlocks.QUANTUM_COMPUTER_ME_NETWORK_PORT, 1)
                .EUt(GTValues.VA[LuV])
                .duration(1000)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("quantum_pointing_block"))
                .inputItems(CEBlocks.STEADY_STATE_COMPUTING_MATRIX_SHELL, 1)
                .inputItems(plate, TungstenSteel, 16)
                .inputItems(pipeSmallItem, Osmium, 4)
                .inputItems(pipeSmallFluid, Iridium, 4)
                .inputItems(screw, Europium, 16)
                .inputFluids(new FluidStack(PCBCoolant.getFluid(), 288))
                .outputItems(CEBlocks.QUANTUM_POINTING_BLOCK, 1)
                .EUt(GTValues.VA[IV])
                .duration(400)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("steady_state_computing_matrix_shell"))
                .inputItems(HULL[LuV], 1)
                .inputItems(CustomTags.LuV_CIRCUITS, 2)
                .inputItems(round, Osmiridium, 64)
                .inputItems(plateDense, Iridium, 4)
                .inputItems(springSmall, VanadiumGallium, 4)
                .inputItems(wireFine, Ruridit, 64)
                .inputItems(wireFine, Platinum, 64)
                .inputFluids(new FluidStack(PCBCoolant.getFluid(), 144))
                .outputItems(CEBlocks.STEADY_STATE_COMPUTING_MATRIX_SHELL, 1)
                .EUt(GTValues.VA[EV])
                .duration(200)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("ctnh_assembler_matrix_wall"))
                .inputItems(ForgeRegistries.ITEMS
                        .getValue(ResourceLocation.parse("expatternprovider:assembler_matrix_wall")), 1)
                .outputItems(CEBlocks.ASSEMBLER_MATRIX_WALL, 1)
                .EUt(GTValues.VA[LV])
                .duration(20)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("ctnh_assembler_matrix_frame"))
                .inputItems(ForgeRegistries.ITEMS
                        .getValue(ResourceLocation.parse("expatternprovider:assembler_matrix_frame")), 1)
                .outputItems(CEBlocks.ASSEMBLER_MATRIX_FRAME, 1)
                .EUt(GTValues.VA[LV])
                .duration(20)
                .save(provider);
        // 对于进阶RAM适配原版电路的配方
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("mainframe_iv_aram"))
                .inputItems(frameGt, Aluminium, 2)
                .inputItems(WORKSTATION_EV, 2)
                .inputItems(ADVANCED_SMD_INDUCTOR, 2)
                .inputItems(ADVANCED_SMD_CAPACITOR, 4)
                .inputItems(ADVANCED_RAM_CHIP, 4)
                .inputItems(wireGtSingle, AnnealedCopper, 16)
                .outputItems(MAINFRAME_IV)
                .solderMultiplier(4)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(VA[HV]).duration(300)
                .save(provider);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("nano_processor_assembly_ev_aram"))
                .inputItems(ADVANCED_CIRCUIT_BOARD)
                .inputItems(NANO_PROCESSOR_HV, 2)
                .inputItems(ADVANCED_SMD_INDUCTOR)
                .inputItems(ADVANCED_SMD_CAPACITOR, 2)
                .inputItems(ADVANCED_RAM_CHIP, 2)
                .inputItems(wireFine, Electrum, 16)
                .outputItems(NANO_PROCESSOR_ASSEMBLY_EV, 2)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(600).duration(150)
                .save(provider);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("nano_computer_iv_aram"))
                .inputItems(ADVANCED_CIRCUIT_BOARD)
                .inputItems(NANO_PROCESSOR_ASSEMBLY_EV, 2)
                .inputItems(ADVANCED_SMD_DIODE, 2)
                .inputItems(NOR_MEMORY_CHIP, 4)
                .inputItems(ADVANCED_RAM_CHIP, 4)
                .inputItems(wireFine, Electrum, 16)
                .outputItems(NANO_COMPUTER_IV)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(600).duration(150)
                .save(provider);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("nano_mainframe_luv_aram"))
                .inputItems(frameGt, Aluminium, 2)
                .inputItems(NANO_COMPUTER_IV, 2)
                .inputItems(ADVANCED_SMD_INDUCTOR, 4)
                .inputItems(ADVANCED_SMD_CAPACITOR, 8)
                .inputItems(ADVANCED_RAM_CHIP, 4)
                .inputItems(wireGtSingle, AnnealedCopper, 32)
                .outputItems(NANO_MAINFRAME_LuV)
                .solderMultiplier(4)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(VA[EV]).duration(300)
                .save(provider);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("quantum_assembly_iv_aram"))
                .inputItems(EXTREME_CIRCUIT_BOARD)
                .inputItems(QUANTUM_PROCESSOR_EV, 2)
                .inputItems(ADVANCED_SMD_INDUCTOR, 2)
                .inputItems(ADVANCED_SMD_CAPACITOR, 4)
                .inputItems(ADVANCED_RAM_CHIP, 1)
                .inputItems(wireFine, Platinum, 16)
                .outputItems(QUANTUM_ASSEMBLY_IV, 2)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(2400).duration(150)
                .save(provider);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("quantum_computer_luv_aram"))
                .inputItems(EXTREME_CIRCUIT_BOARD)
                .inputItems(QUANTUM_ASSEMBLY_IV, 2)
                .inputItems(ADVANCED_SMD_DIODE, 2)
                .inputItems(NOR_MEMORY_CHIP, 4)
                .inputItems(ADVANCED_RAM_CHIP, 4)
                .inputItems(wireFine, Platinum, 32)
                .outputItems(QUANTUM_COMPUTER_LuV)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(2400).duration(150)
                .save(provider);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("quantum_mainframe_zpm_aram"))
                .inputItems(frameGt, HSSG, 2)
                .inputItems(QUANTUM_COMPUTER_LuV, 2)
                .inputItems(ADVANCED_SMD_INDUCTOR, 6)
                .inputItems(ADVANCED_SMD_CAPACITOR, 12)
                .inputItems(ADVANCED_RAM_CHIP, 6)
                .inputItems(wireGtSingle, AnnealedCopper, 48)
                .solderMultiplier(4)
                .outputItems(QUANTUM_MAINFRAME_ZPM)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(VA[IV]).duration(300)
                .save(provider);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("crystal_assembly_luv_aram"))
                .inputItems(ELITE_CIRCUIT_BOARD)
                .inputItems(CRYSTAL_PROCESSOR_IV, 2)
                .inputItems(ADVANCED_SMD_INDUCTOR, 4)
                .inputItems(ADVANCED_SMD_CAPACITOR, 8)
                .inputItems(ADVANCED_RAM_CHIP, 6)
                .inputItems(wireFine, NiobiumTitanium, 16)
                .outputItems(CRYSTAL_ASSEMBLY_LuV, 2)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(9600).duration(300)
                .save(provider);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("crystal_computer_zpm_aram"))
                .inputItems(ELITE_CIRCUIT_BOARD)
                .inputItems(CRYSTAL_ASSEMBLY_LuV, 2)
                .inputItems(ADVANCED_RAM_CHIP, 1)
                .inputItems(NOR_MEMORY_CHIP, 32)
                .inputItems(NAND_MEMORY_CHIP, 64)
                .inputItems(wireFine, NiobiumTitanium, 32)
                .solderMultiplier(2)
                .outputItems(CRYSTAL_COMPUTER_ZPM)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(9600).duration(300)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("crystal_mainframe_uv_aram"))
                .inputItems(frameGt, HSSE, 2)
                .inputItems(CRYSTAL_COMPUTER_ZPM, 2)
                .inputItems(ADVANCED_RAM_CHIP, 8)
                .inputItems(HIGH_POWER_INTEGRATED_CIRCUIT, 2)
                .inputItems(wireGtSingle, NiobiumTitanium, 8)
                .inputItems(ADVANCED_SMD_INDUCTOR, 8)
                .inputItems(ADVANCED_SMD_CAPACITOR, 16)
                .inputItems(ADVANCED_SMD_DIODE, 8)
                .inputFluids(SolderingAlloy.getFluid(L * 10))
                .outputItems(CRYSTAL_MAINFRAME_UV)
                .stationResearch(b -> b.researchStack(CRYSTAL_COMPUTER_ZPM.asStack()).CWUt(16))
                .EUt(VA[LuV]).duration(600)
                .save(provider);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("data_stick_aram"))
                .inputItems(PLASTIC_CIRCUIT_BOARD)
                .inputItems(CENTRAL_PROCESSING_UNIT, 2)
                .inputItems(NAND_MEMORY_CHIP, 32)
                .inputItems(ADVANCED_RAM_CHIP, 1)
                .inputItems(wireFine, RedAlloy, 16)
                .inputItems(plate, Polyethylene, 4)
                .outputItems(TOOL_DATA_STICK)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(100).EUt(90).save(provider);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("data_orb_aram"))
                .inputItems(ADVANCED_CIRCUIT_BOARD)
                .inputItems(CustomTags.HV_CIRCUITS, 2)
                .inputItems(ADVANCED_RAM_CHIP, 1)
                .inputItems(NOR_MEMORY_CHIP, 32)
                .inputItems(NAND_MEMORY_CHIP, 64)
                .inputItems(wireFine, Platinum, 32)
                .outputItems(TOOL_DATA_ORB)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(100).EUt(1200).save(provider);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("data_module_aram"))
                .inputItems(WETWARE_PRINTED_CIRCUIT_BOARD)
                .inputItems(CustomTags.ZPM_CIRCUITS, 2)
                .inputItems(ADVANCED_RAM_CHIP, 8)
                .inputItems(NOR_MEMORY_CHIP, 64)
                .inputItems(NAND_MEMORY_CHIP, 64)
                .inputItems(wireFine, YttriumBariumCuprate, 32)
                .outputItems(TOOL_DATA_MODULE)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .duration(100).EUt(38400).save(provider);



    }
}
