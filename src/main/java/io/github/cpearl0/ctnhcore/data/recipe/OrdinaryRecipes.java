package io.github.cpearl0.ctnhcore.data.recipe;

import com.gregtechceu.gtceu.api.data.chemical.material.MarkerMaterials;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.moguang.ctnhbio.data.recipe.CBRecipeBuilder;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.OrdinaryMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import io.github.cpearl0.ctnhcore.registry.CTNHMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.GTValues.LuV;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.wireFine;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Iron;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLY_LINE_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.CIRCUIT_ASSEMBLER_RECIPES;
import static com.moguang.ctnhbio.registry.CBItems.WETWARE_PRINTED_CIRCUIT_BOARD;
import static io.github.cpearl0.ctnhcore.data.materials.OrdinaryMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHItems.ADVANCED_RAM_CHIP;

public class OrdinaryRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
//小化反
        CBRecipeBuilder.of(CTNHCore.id("fenton_reagent_mixing"), GTRecipeTypes.CHEMICAL_RECIPES)
                .inputFluids(GTMaterials.Iron2Chloride, 1000)
                .inputFluids(HydrogenPeroxide, 1000)
                .outputFluids(new FluidStack(FENTONS_REAGENT.getFluid(),2000))
                .duration(100)
                .EUt(1920)
                .save(provider);
        CBRecipeBuilder.of(CTNHCore.id("polypyrrole_from_fenton"), GTRecipeTypes.CHEMICAL_RECIPES)
                .inputFluids(new FluidStack(PYRROLE.getFluid(), 1000))
                .inputFluids(new FluidStack(FENTONS_REAGENT.getFluid(), 1000))
                .outputFluids(new FluidStack(POLYPYRROLE.getFluid(), 1000))
                .outputFluidsRanged(new FluidStack(Iron3Chloride.getFluid(), 500), UniformInt.of(100,200 ))
                .duration(200)
                .EUt(960)
                .save(provider);
        CBRecipeBuilder.of(CTNHCore.id("ferric_to_ferrous_chloride"), GTRecipeTypes.CHEMICAL_RECIPES)
                .inputFluids(new FluidStack(GTMaterials.Iron3Chloride.getFluid(), 2000))
                .inputItems(dust,Iron)
                .outputFluids(new FluidStack(GTMaterials.Iron2Chloride.getFluid(), 3000))
                .duration(100)
                .EUt(480)
                .save(provider);

        //大化反
        CBRecipeBuilder.of(CTNHCore.id("fenton_reagent_mixing"), GTRecipeTypes.LARGE_CHEMICAL_RECIPES)
                .inputFluids(GTMaterials.Iron2Chloride, 1000)
                .inputFluids(HydrogenPeroxide, 1000)
                .outputFluids(new FluidStack(FENTONS_REAGENT.getFluid(),2000))
                .duration(100)
                .EUt(1920)
                .save(provider);
        CBRecipeBuilder.of(CTNHCore.id("polypyrrole_from_fenton"), GTRecipeTypes.LARGE_CHEMICAL_RECIPES)
                .inputFluids(new FluidStack(PYRROLE.getFluid(), 1000))
                .inputFluids(new FluidStack(FENTONS_REAGENT.getFluid(), 1000))
                .outputFluids(new FluidStack(POLYPYRROLE.getFluid(), 1000))
                .outputFluidsRanged(new FluidStack(Iron3Chloride.getFluid(), 500), UniformInt.of(100,200 ))
                .outputFluidsRanged(new FluidStack(GTMaterials.Water.getFluid(), 500), UniformInt.of(100, 200))
                .duration(200)
                .EUt(960)
                .save(provider);
        CBRecipeBuilder.of(CTNHCore.id("ferric_to_ferrous_chloride"), GTRecipeTypes.LARGE_CHEMICAL_RECIPES)
                .inputFluids(new FluidStack(GTMaterials.Iron3Chloride.getFluid(), 2000))
                .inputItems(dust,Iron)
                .outputFluids(new FluidStack(GTMaterials.Iron2Chloride.getFluid(), 3000))
                .duration(100)
                .EUt(480)
                .save(provider);
        //蒸馏室
        CBRecipeBuilder.of(CTNHCore.id("ferric_to_ferrous_chloride"), GTRecipeTypes.DISTILLERY_RECIPES)
                .circuitMeta(5)
                .inputFluids(new FluidStack(CoalTar.getFluid(), 1000))
                .outputFluids(new FluidStack(PYRROLE.getFluid(), 800))
                .duration(120)
                .EUt(384)
                .save(provider);
        //切割机
        CBRecipeBuilder.of(CTNHCore.id("advanced_ram_chip_recipe"), GTRecipeTypes.CUTTER_RECIPES)
                .inputItems(CTNHItems.ADVANCED_RAM_WAFER.get().getDefaultInstance())
                .inputFluids(Lubricant, 500)
                .outputItems(ADVANCED_RAM_CHIP,16)
                .duration(900)
                .EUt(1920)
                .cleanroom(CleanroomType.CLEANROOM)
                .save(provider);

        //激光蚀刻
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
                .outputItems(CTNHItems.ADVANCED_RAM_WAFER.get().getDefaultInstance(),4)
                .duration(450)
                .EUt(6144)
                .cleanroom(CleanroomType.CLEANROOM)
                .save(provider);
        CBRecipeBuilder.of(CTNHCore.id("advanced_ram_wafer_ne_recipe"), GTRecipeTypes.LASER_ENGRAVER_RECIPES)
                .inputItems(NEUTRONIUM_WAFER.get().getDefaultInstance())
                .notConsumable(lens, CTNHMaterials.EuropiumFluorite)
                .outputItems(CTNHItems.ADVANCED_RAM_WAFER.get().getDefaultInstance(),8)
                .duration(450)
                .EUt(30720)
                .cleanroom(CleanroomType.CLEANROOM)
                .save(provider);

        CBRecipeBuilder.of(CTNHCore.id("advanced_ram_wafer_ne_recipe"), CTNHRecipeTypes.PVB_RECIPE)
                .inputFluids(POLYPYRROLE,288)
                .inputItems(wireFine,BLUE_TITANIUM_ALLOY,1)
                .outputItems(wireFine,BIO_FLEXIBLE,1)
                .duration(200)
                .EUt(30720)
                .cleanroom(CleanroomType.CLEANROOM)
                .save(provider);
        //对于进阶RAM适配原版电路的配方
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("mainframe_iv_aram")
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

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("nano_processor_assembly_ev_aram")
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

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("nano_computer_iv_aram")
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

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("nano_mainframe_luv_aram")
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

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("quantum_assembly_iv_aram")
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

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("quantum_computer_luv_aram")
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

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("quantum_mainframe_zpm_aram")
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

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("crystal_assembly_luv_aram")
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

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("crystal_computer_zpm_aram")
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

        ASSEMBLY_LINE_RECIPES.recipeBuilder("crystal_mainframe_uv_aram")
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

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("data_stick_aram")
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

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("data_orb_aram")
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

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("data_module_aram")
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
