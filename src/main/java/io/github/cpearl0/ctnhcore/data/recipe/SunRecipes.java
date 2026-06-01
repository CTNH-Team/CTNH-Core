package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.registry.CTNHBlocks;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksA;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksB;

import com.gregtechceu.gtceu.common.data.GCYMBlocks;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.machines.GCYMMachines;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

import com.enderio.base.common.init.EIOFluids;
import com.github.alexmodguy.alexscaves.server.item.ACItemRegistry;
import com.simibubi.create.AllItems;
import vazkii.botania.common.block.BotaniaBlocks;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.VA;
import static com.gregtechceu.gtceu.api.GTValues.ZPM;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys.PLASMA;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gregtechceu.gtceu.data.recipe.CustomTags.*;
import static io.github.cpearl0.ctnhcore.data.materials.AdastraMaterials.*;
import static io.github.cpearl0.ctnhcore.data.materials.CrudeGoldRefiningMaterials.SODIUM_HEXAFLUOROALUMINATE;
import static io.github.cpearl0.ctnhcore.data.materials.UncategorizedMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.machines.CTNHMachines.*;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.*;

public class SunRecipes {

    private static final Item BIOLUMINESSCENCE = ACItemRegistry.BIOLUMINESSCENCE.get();
    private static final Item LIGHT_RELAY = BotaniaBlocks.lightRelayDefault.asItem();
    private static final Item CHROMATIC_COMPOUND = AllItems.CHROMATIC_COMPOUND.asStack().getItem();
    private static final Fluid LIQUID_SUNSHINE = EIOFluids.LIQUID_SUNSHINE.getSource();

    public static void init(Consumer<FinishedRecipe> provider) {
        autoclaveRecipes(provider);
        mixerRecipes(provider);
        pvbRecipes(provider);
        chemicalVaporDepositionRecipes(provider);
        assemblerRecipes(provider);
        extractorRecipes(provider);
        fusionReactorRecipes(provider);
        cultivationRoomRecipes(provider);
        plasmaCondenserRecipes(provider);
        assemblyLineRecipes(provider);
        laserSorterRecipes(provider);
        photovoltaicAssemberRecipes(provider);
    }

    private static void autoclaveRecipes(Consumer<FinishedRecipe> provider) {
        AUTOCLAVE_RECIPES.recipeBuilder("sun1")
                .inputItems(Items.GLOW_BERRIES, 16)
                .inputFluids(UUMatter.getFluid(16))
                .outputItems(dust, Sunnarium, 16)
                .EUt(480)
                .duration(1600)
                .save(provider);

        AUTOCLAVE_RECIPES.recipeBuilder("sun2")
                .inputItems(Items.GLOW_INK_SAC, 64)
                .inputFluids(UUMatter.getFluid(16))
                .outputItems(dust, Sunnarium, 16)
                .EUt(480)
                .duration(2000)
                .save(provider);

        AUTOCLAVE_RECIPES.recipeBuilder("sun3")
                .inputItems(BIOLUMINESSCENCE, 16)
                .inputFluids(UUMatter.getFluid(16))
                .outputItems(dust, Sunnarium, 16)
                .EUt(480)
                .duration(800)
                .save(provider);

        AUTOCLAVE_RECIPES.recipeBuilder("sun4")
                .inputFluids(SUNNARIUM_EXTRACT.getFluid(576))
                .inputItems(Items.GLOWSTONE_DUST)
                .outputItems(dust, Sunnarium)
                .EUt(480)
                .duration(200)
                .save(provider);
    }

    private static void mixerRecipes(Consumer<FinishedRecipe> provider) {
        MIXER_RECIPES.recipeBuilder("sun1")
                .inputItems(dust, Thorium, 4)
                .inputItems(LIGHT_RELAY, 4)
                .inputItems(Items.GLOWSTONE_DUST, 4)
                .inputItems(CHROMATIC_COMPOUND)
                .outputItems(dust, Sunnarium, 16)
                .EUt(1920)
                .duration(120)
                .save(provider);

        MIXER_RECIPES.recipeBuilder("soda_glass")
                .inputItems(dust, SodaAsh, 2)
                .inputItems(dust, SiliconDioxide, 6)
                .inputItems(dust, Calcite)
                .outputItems(dust, SODA_DUST, 9)
                .EUt(120)
                .duration(200)
                .save(provider);
    }

    private static void pvbRecipes(Consumer<FinishedRecipe> provider) {
        CTNHRecipeTypes.PVB_RECIPE.recipeBuilder("clgs")
                .inputItems(dust, Selenium, 3)
                .inputItems(dust, Copper)
                .inputItems(dust, Gallium)
                .inputItems(dust, Indium)
                .inputItems(dust, Cryolite)
                .outputFluids(SODIUM_HEXAFLUOROALUMINATE.getFluid(144))
                .outputItems(dust, CLGS_BUFFER)
                .EUt(1280)
                .duration(200)
                .save(provider);

        CTNHRecipeTypes.PVB_RECIPE.recipeBuilder("zzz")
                .inputItems(dust, MolybdenumDisilicide)
                .inputItems(plateDouble, SODA_DUST)
                .inputItems(dust, Nickel, 5)
                .inputItems(dust, Aluminium, 5)
                .inputItems(foil, Sunnarium, 32)
                .inputItems(foil, Sunnarium, 32)
                .inputFluids(Polybenzimidazole.getFluid(500))
                .outputItems(CTNHItems.CLGS_ELECTRODE.asStack())
                .EUt(32768)
                .duration(400)
                .save(provider);

        CTNHRecipeTypes.PVB_RECIPE.recipeBuilder("magnetron_sputtering_target_material_plate")
                .inputItems(dust, Zinc, 11)
                .inputItems(dust, Aluminium, 11)
                .inputItems(dust, Sunnarium, 11)
                .outputItems(plate, MAGNETRON_SPUTTERING_TARGET_MATERIAL)
                .EUt(32768)
                .duration(400)
                .save(provider);

        CTNHRecipeTypes.PVB_RECIPE.recipeBuilder("clgs_quick")
                .inputItems(dust, Selenium, 3)
                .inputItems(dust, Copper)
                .inputItems(dust, Gallium)
                .inputItems(dust, Indium)
                .inputFluids(SODIUM_HEXAFLUOROALUMINATE.getFluid(144))
                .inputItems(dust, CADMIUM_SULFIDE)
                .outputFluids(SODIUM_HEXAFLUOROALUMINATE.getFluid(144))
                .outputItems(plate, CLGS_BUFFER)
                .EUt(130712)
                .duration(20)
                .save(provider);
    }

    private static void chemicalVaporDepositionRecipes(Consumer<FinishedRecipe> provider) {
        CTNHRecipeTypes.CHEMICAL_VAPOR_DEPOSITION.recipeBuilder("cds")
                .inputItems(dust, CADMIUM_SULFIDE)
                .inputItems(plate, CLGS_BUFFER)
                .outputItems(plate, CLGS_BUFFER)
                .EUt(8192)
                .duration(200)
                .save(provider);

        CTNHRecipeTypes.CHEMICAL_VAPOR_DEPOSITION.recipeBuilder("nano_c")
                .inputItems(dust, ActivatedCarbon, 64)
                .inputItems(CARBON_FIBERS, 64)
                .inputFluids(Glue.getFluid(1000))
                .inputFluids(Polybenzimidazole.getFluid(1000))
                .outputItems(dust, NAMI_C, 16)
                .EUt(32768)
                .duration(1000)
                .save(provider);
    }

    private static void assemblerRecipes(Consumer<FinishedRecipe> provider) {
        ASSEMBLER_RECIPES.recipeBuilder("sunny")
                .inputItems(CTNHItems.CLGS_ELECTRODE.asStack())
                .inputItems(plate, CLGS_BUFFER)
                .inputItems(plate, MAGNETRON_SPUTTERING_TARGET_MATERIAL)
                .inputItems(wireGtDouble, IndiumTinBariumTitaniumCuprate, 4)
                .inputItems(plate, BlueSteel, 16)
                .inputFluids(Sunnarium.getFluid(720))
                .outputItems(CTNHItems.CLGS.asStack())
                .EUt(32768)
                .duration(800)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("dense_calorite_plate")
                .inputItems(plateDense, OPTICAL_HEAT_RESISTANT_FERROCHROME_ALLOY_080)
                .inputItems(plate, IncoloyMA956, 4)
                .inputItems(plateDense, Calorite)
                .inputItems(dust, SUNNARIUM_EXTRACT, 4)
                .inputItems(plateDense, NAQUADAH_HEAT_RESISTANT_FERROCHROME_ALLOY_792)
                .outputItems(CTNHItems.THERMOTOLERANT_DISH.asStack())
                .EUt(522848)
                .duration(100)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("photon_press_cond_block")
                .inputItems(NEUTRON_REFLECTOR)
                .inputItems(CTNHItems.PlateRadiationProtection.asStack())
                .inputItems(plateDense, CADMIUM_SULFIDE)
                .inputItems(CTNHItems.MEASUREMENT_PV_CELL.asStack())
                .inputFluids(Sunnarium.getFluid(576))
                .inputItems(CTNHBlocks.CASING_REFLECT_LIGHT.asStack())
                .outputItems(CTNHBlocks.PHOTON_PRESS_COND_BLOCK.asStack())
                .EUt(32768)
                .duration(200)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("pv_coil")
                .inputItems(foil, Sunnarium, 48)
                .inputItems(wireFine, Electrum, 64)
                .inputItems(frameGt, OPTICAL_HEAT_RESISTANT_FERROCHROME_ALLOY_080, 6)
                .inputItems(CTNHItems.GENERAL_CIRCUIT_IV.asStack())
                .inputItems(VOLTAGE_COIL_EV, 6)
                .inputFluids(VanadiumGallium.getFluid(576))
                .outputItems(CTNHBlocks.PV_COIL.asStack())
                .EUt(32678)
                .duration(144)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("photovoltaic_drone_prototype")
                .inputItems(frameGt, Sunnarium, 64)
                .inputItems(screw, Sunnarium, 64)
                .inputItems(CTNHItems.CLGS.asStack(), 2)
                .inputItems(plateDense, OPTICAL_HEAT_RESISTANT_FERROCHROME_ALLOY_080, 7)
                .inputItems(CTNHItems.GENERAL_CIRCUIT_IV.asStack(), 2)
                .inputItems(CTNHItems.GENERAL_CIRCUIT_LUV.asStack(), 3)
                .inputItems(CTNHItems.GENERAL_CIRCUIT_ZPM.asStack(), 4)
                .outputItems(CTNHItems.PV_DRONE_PROTOTYPE.asStack())
                .EUt(32678)
                .duration(600)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("stellar_radiation_router_casing")
                .inputItems(GCYMBlocks.HEAT_VENT.asStack(4))
                .inputItems(pipeSmallFluid, NiobiumTitanium, 32)
                .inputItems(plateDense, HEAT_RESISTANT_FERROCHROME_ALLOY_DS)
                .inputItems(plateDense, HEAT_RESISTANT_FERROCHROME_ALLOY_020)
                .inputItems(plateDense, OPTICAL_HEAT_RESISTANT_FERROCHROME_ALLOY_080)
                .inputItems(frameGt, NAQUADAH_HEAT_RESISTANT_FERROCHROME_ALLOY_792, 4)
                .inputFluids(PCBCoolant.getFluid(4000))
                .inputFluids(Cryotheum.getFluid(4000))
                .inputFluids(Helium.getFluid(4000))
                .outputItems(CTNHBlocks.STELLAR_RADIATION_ROUTER_CASING.asStack(4))
                .EUt(32678)
                .duration(100)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("nq_excite_carbon_carbon_nanofiber_structural_block")
                .inputItems(plateDense, NaquadahAlloy)
                .inputItems(IV_CIRCUITS)
                .inputItems(dust, NAMI_C, 16)
                .inputItems(frameGt, Ruridit, 4)
                .inputItems(plateDouble, HastelloyC276, 4)
                .outputItems(CTNHBlocks.NQ_EXCITE_CARBON_CARBON_NANOFIBER_STRUCTURAL_BLOCK.asStack(2))
                .EUt(32768)
                .duration(200)
                .save(provider);
    }

    private static void extractorRecipes(Consumer<FinishedRecipe> provider) {
        EXTRACTOR_RECIPES.recipeBuilder("sun1")
                .inputItems(Items.GLOW_BERRIES)
                .chancedOutput(dust, SUNNARIUM_EXTRACT, 7000, 1000)
                .EUt(480)
                .duration(150)
                .save(provider);

        EXTRACTOR_RECIPES.recipeBuilder("sun2")
                .inputItems(Items.GLOW_INK_SAC, 4)
                .chancedOutput(dust, SUNNARIUM_EXTRACT, 6000, 1000)
                .EUt(480)
                .duration(200)
                .save(provider);

        EXTRACTOR_RECIPES.recipeBuilder("sun3")
                .inputItems(BIOLUMINESSCENCE)
                .chancedOutput(dust, SUNNARIUM_EXTRACT, 8000, 1000)
                .EUt(480)
                .duration(100)
                .save(provider);
    }

    private static void fusionReactorRecipes(Consumer<FinishedRecipe> provider) {
        FUSION_RECIPES.recipeBuilder("sun")
                .inputFluids(Glowstone.getFluid(1440))
                .inputFluids(SUNNARIUM_EXTRACT.getFluid(144))
                .outputFluids(Sunnarium.getFluid(1440))
                .fusionStartEU(150000000)
                .EUt(32678)
                .duration(100)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("sunnarium_plasma")
                .inputFluids(Oxygen.getFluid(PLASMA, 1000))
                .inputFluids(Sunnarium.getFluid(1000))
                .outputFluids(Sunnarium.getFluid(PLASMA, 1000))
                .fusionStartEU(300000000)
                .EUt(130712)
                .duration(100)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("light")
                .inputFluids(Sunnarium.getFluid(PLASMA, 144))
                .inputFluids(RADIATION_SUNNARIUM_EXTRACT.getFluid(144))
                .outputFluids(HIKARIUM.getFluid(PLASMA, 288))
                .fusionStartEU(600000000)
                .EUt(130712)
                .duration(100)
                .save(provider);
    }

    private static void cultivationRoomRecipes(Consumer<FinishedRecipe> provider) {
        CTNHRecipeTypes.CULTIVATION_ROOM.recipeBuilder("radiation_sunnarium_extract_plasma")
                .outputFluids(RADIATION_SUNNARIUM_EXTRACT.getFluid(PLASMA, 1000))
                .inputFluids(new FluidStack(
                        LIQUID_SUNSHINE, 100))
                .inputFluids(SterileBiologicalCultureMediumStockSolution.getFluid(200))
                .inputFluids(SUNNARIUM_EXTRACT.getFluid(1000))
                .chancedInput(CTNHItems.THERMOTOLERANT_DISH.asStack(), 2000, -1)
                .circuitMeta(1)
                .EUt(457492)
                .duration(200)
                .save(provider);

        CTNHRecipeTypes.CULTIVATION_ROOM.recipeBuilder("radiation_sunnarium_extract_plasma2")
                .outputFluids(RADIATION_SUNNARIUM_EXTRACT.getFluid(PLASMA, 6000))
                .inputFluids(new FluidStack(
                        LIQUID_SUNSHINE, 600))
                .inputFluids(SterileBiologicalCultureMediumStockSolution.getFluid(1200))
                .inputFluids(SUNNARIUM_EXTRACT.getFluid(6000))
                .inputItems(CTNHItems.THERMOTOLERANT_DISH.asStack())
                .circuitMeta(2)
                .EUt(457492)
                .duration(1200)
                .save(provider);
    }

    private static void plasmaCondenserRecipes(Consumer<FinishedRecipe> provider) {
        CTNHRecipeTypes.PLASMA_CONDENSER_RECIPES.recipeBuilder("helium_plasma")
                .outputFluids(Helium.getFluid(PLASMA, 4000))
                .inputFluids(Helium.getFluid(4000))
                .inputFluids(RADIATION_SUNNARIUM_EXTRACT.getFluid(PLASMA, 1000))
                .outputFluids(RADIATION_SUNNARIUM_EXTRACT.getFluid(1000))
                .EUt(94712)
                .duration(100)
                .save(provider);

        CTNHRecipeTypes.PLASMA_CONDENSER_RECIPES.recipeBuilder("hikarium_plasma")
                .notConsumable(Items.GLOWSTONE_DUST)
                .outputFluids(Helium.getFluid(PLASMA, 4000))
                .inputFluids(Helium.getFluid(4000))
                .inputFluids(HIKARIUM.getFluid(PLASMA, 1000))
                .outputFluids(HIKARIUM.getFluid(1000))
                .EUt(473560)
                .duration(100)
                .save(provider);
    }

    private static void assemblyLineRecipes(Consumer<FinishedRecipe> provider) {
        ASSEMBLY_LINE_RECIPES.recipeBuilder("combined_vapor_deposition_facility")
                .inputItems(MultiblocksA.CHEMICAL_VAPOR_DEPOSITION_MACHINE.asStack())
                .inputItems(GTBlocks.HIGH_POWER_CASING.asStack())
                .inputItems(GTBlocks.SUPERCONDUCTING_COIL.asStack())
                .inputItems(CONVEYOR_MODULE_LuV, 6)
                .inputItems(ELECTRIC_MOTOR_LuV, 6)
                .inputItems(ROBOT_ARM_LuV, 6)
                .inputItems(screw, CADMIUM_SULFIDE, 64)
                .inputItems(wireGtQuadruple, IndiumTinBariumTitaniumCuprate, 8)
                .inputItems(FIELD_GENERATOR_EV, 16)
                .inputFluids(PCBCoolant.getFluid(1000))
                .inputFluids(SolderingAlloy.getFluid(16000))
                .outputItems(MultiblocksB.COMBINED_VAPOR_DEPOSITION_FACILITY.asStack())
                .EUt(32678)
                .duration(2000)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("lasersorder")
                .inputItems(GCYMMachines.LARGE_ENGRAVING_LASER.asStack())
                .inputItems(lens, Diamond)
                .inputItems(lens, Emerald)
                .inputItems(lens, Ruby)
                .inputItems(lens, Sapphire)
                .inputItems(lens, NetherStar)
                .inputItems(EMITTER_LuV)
                .inputItems(rod, OPTICAL_HEAT_RESISTANT_FERROCHROME_ALLOY_080, 16)
                .inputItems(gear, OPTICAL_HEAT_RESISTANT_FERROCHROME_ALLOY_080, 16)
                .inputItems(rodLong, HEAT_RESISTANT_FERROCHROME_ALLOY_DS, 64)
                .inputFluids(Cerrobase140.getFluid(1600))
                .inputFluids(HEAT_RESISTANT_FERROCHROME_ALLOY_020.getFluid(1600))
                .outputItems(MultiblocksB.LaserSorder.asStack())
                .EUt(32678)
                .duration(800)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("photovoltaic_drone_tier1")
                .stationResearch(b -> b
                        .researchStack(CTNHItems.PV_DRONE_RESEARCH_1.asStack())
                        .dataStack(TOOL_DATA_ORB.asStack())
                        .EUt(VA[ZPM])
                        .CWUt(36))
                .inputItems(CTNHItems.MEASUREMENT_PV_CELL.asStack(), 2)
                .inputItems(gear, OPTICAL_HEAT_RESISTANT_FERROCHROME_ALLOY_080, 2)
                .inputItems(gear, NAQUADAH_HEAT_RESISTANT_FERROCHROME_ALLOY_792, 2)
                .inputItems(LuV_CIRCUITS, 2)
                .inputItems(ZPM_CIRCUITS, 2)
                .inputItems(screw, Sunnarium, 32)
                .inputItems(frameGt, Sunnarium, 4)
                .inputFluids(PCBCoolant.getFluid(2000))
                .inputFluids(Cryotheum.getFluid(2000))
                .inputFluids(Helium.getFluid(2000))
                .outputItems(CTNHItems.PV_DRONE_TIER1.asStack())
                .EUt(130712)
                .duration(400)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("photovoltaic_drone_tier2")
                .stationResearch(b -> b
                        .researchStack(CTNHItems.PV_DRONE_RESEARCH_2.asStack())
                        .dataStack(TOOL_DATA_ORB.asStack())
                        .EUt(VA[ZPM])
                        .CWUt(48))
                .inputItems(ENERGY_MODULE)
                .inputItems(CTNHItems.MEASUREMENT_PV_CELL.asStack())
                .inputItems(UV_CIRCUITS, 2)
                .inputItems(plateDense, Calorite)
                .inputItems(plateDense, OPTICAL_HEAT_RESISTANT_FERROCHROME_ALLOY_080)
                .inputItems(frameGt, Sunnarium, 64)
                .inputItems(frameGt, Sunnarium, 64)
                .inputItems(frameGt, Sunnarium, 64)
                .inputFluids(Sunnarium.getFluid(PLASMA, 16000))
                .inputFluids(Cryotheum.getFluid(2000))
                .inputFluids(Helium.getFluid(2000))
                .outputItems(CTNHItems.PV_DRONE_TIER2.asStack())
                .EUt(522848)
                .duration(400)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("space_photovoltai_cbase_station")
                .inputItems(MultiblocksA.PHOTOVOLTAIC_POWER_STATION_ENERGETIC.asStack(64))
                .inputItems(MultiblocksA.PHOTOVOLTAIC_POWER_STATION_PULSATING.asStack(64))
                .inputItems(MultiblocksA.PHOTOVOLTAIC_POWER_STATION_VIBRANT.asStack(64))
                .inputItems(CTNHItems.MEASUREMENT_PV_CELL.asStack(), 64)
                .inputItems(CTNHBlocks.PV_COIL.asStack(), 64)
                .inputItems(UV_CIRCUITS, 32)
                .inputItems(CTNHBlocks.NQ_EXCITE_CARBON_CARBON_NANOFIBER_STRUCTURAL_BLOCK.asStack(32))
                .inputFluids(Cerrobase140.getFluid(16000))
                .inputFluids(SolderingAlloy.getFluid(16000))
                .inputFluids(Cryotheum.getFluid(16000))
                .outputItems(MultiblocksB.SPACEPHOTOVOLTAICBASESTATION.asStack())
                .EUt(130712)
                .duration(1000)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("pvdrone")
                .stationResearch(b -> b
                        .researchStack(DRONEHOLDER.asStack())
                        .dataStack(TOOL_DATA_ORB.asStack())
                        .EUt(VA[ZPM])
                        .CWUt(48))
                .inputItems(CTNHItems.PV_DRONE_PROTOTYPE.asStack())
                .inputItems(plateDense, HEAT_RESISTANT_FERROCHROME_ALLOY_DS, 7)
                .inputItems(plateDense, HEAT_RESISTANT_FERROCHROME_ALLOY_020, 7)
                .inputItems(plateDense, OPTICAL_HEAT_RESISTANT_FERROCHROME_ALLOY_080, 7)
                .inputItems(plateDense, NAQUADAH_HEAT_RESISTANT_FERROCHROME_ALLOY_792, 7)
                .inputItems(plateDense, NaquadahAlloy, 7)
                .inputItems(Items.BEACON, 9)
                .inputItems(CTNHBlocks.PV_COIL.asStack(), 64)
                .inputItems(UV_CIRCUITS, 32)
                .inputFluids(Cerrobase140.getFluid(16000))
                .inputFluids(SolderingAlloy.getFluid(16000))
                .inputFluids(Cryotheum.getFluid(16000))
                .outputItems(MultiblocksB.PHOTOVOLTAIC_DRONE_STATION.asStack())
                .EUt(130712)
                .duration(1000)
                .save(provider);
    }

    private static void laserSorterRecipes(Consumer<FinishedRecipe> provider) {
        CTNHRecipeTypes.LS_RECIPE.recipeBuilder("clgs2")
                .inputItems(CTNHItems.CLGS.asStack(), 2)
                .notConsumable(lens, Ruby)
                .outputItems(CTNHItems.MEASUREMENT_PV_CELL.asStack())
                .EUt(128)
                .addData("cwut", 16)
                .duration(60000)
                .save(provider);

        CTNHRecipeTypes.LS_RECIPE.recipeBuilder("research_pvdrone")
                .inputItems(CTNHItems.PV_DRONE_PROTOTYPE.asStack(), 8)
                .inputItems(CTNHItems.CLGS.asStack(), 64)
                .inputItems(LuV_CIRCUITS, 64)
                .outputItems(CTNHItems.PV_DRONE_RESEARCH_1.asStack())
                .EUt(128)
                .addData("cwut", 8)
                .duration(2000000)
                .save(provider);

        CTNHRecipeTypes.LS_RECIPE.recipeBuilder("research_pvdone_2")
                .inputItems(ENERGY_MODULE, 16)
                .inputItems(CTNHItems.MEASUREMENT_PV_CELL.asStack(), 64)
                .inputItems(CTNHItems.PV_DRONE_TIER1.asStack(), 32)
                .inputItems(UV_CIRCUITS, 32)
                .inputFluids(Sunnarium.getFluid(PLASMA, 32000))
                .inputItems(CTNHBlocks.PV_COIL.asStack(), 16)
                .outputItems(CTNHItems.PV_DRONE_RESEARCH_2.asStack())
                .EUt(128)
                .addData("cwut", 8)
                .duration(20000000)
                .save(provider);

        CTNHRecipeTypes.LS_RECIPE.recipeBuilder("research_pvdone_3")
                .inputItems(HIGHLY_ADVANCED_SOC_WAFER, 64)
                .inputItems(CTNHItems.PV_DRONE_TIER2.asStack(), 32)
                .inputItems(UHV_CIRCUITS, 32)
                .inputFluids(Sunnarium.getFluid(PLASMA, 32000))
                .inputItems(gearSmall, HIKARIUM, 32)
                .outputItems(CTNHItems.PV_DRONE_RESEARCH_3.asStack())
                .EUt(128)
                .addData("cwut", 12)
                .duration(2000000000)
                .save(provider);
    }

    private static void photovoltaicAssemberRecipes(Consumer<FinishedRecipe> provider) {
        CTNHRecipeTypes.PHOTOVOLTAIC_ASSEMBER.recipeBuilder("pv_coil")
                .inputItems(foil, Sunnarium, 32)
                .inputItems(wireFine, Electrum, 32)
                .inputItems(frameGt, OPTICAL_HEAT_RESISTANT_FERROCHROME_ALLOY_080, 4)
                .inputItems(VOLTAGE_COIL_IV)
                .inputFluids(VanadiumGallium.getFluid(576))
                .outputItems(CTNHBlocks.PV_COIL.asStack())
                .addData("input", 130712)
                .duration(20)
                .save(provider);

        CTNHRecipeTypes.PHOTOVOLTAIC_ASSEMBER.recipeBuilder("pv_drone_tier1")
                .notConsumable(CTNHItems.PV_DRONE_RESEARCH_2.asStack())
                .inputItems(CTNHItems.MEASUREMENT_PV_CELL.asStack(), 2)
                .inputItems(plateDense, OPTICAL_HEAT_RESISTANT_FERROCHROME_ALLOY_080)
                .inputItems(screw, Sunnarium, 32)
                .inputItems(CRYSTAL_SYSTEM_ON_CHIP.asStack(8))
                .inputFluids(Cryotheum.getFluid(8000))
                .outputItems(CTNHItems.PV_DRONE_TIER1.asStack())
                .addData("input", 261424)
                .duration(400)
                .save(provider);

        CTNHRecipeTypes.PHOTOVOLTAIC_ASSEMBER.recipeBuilder("pv_drone_tier2")
                .notConsumable(CTNHItems.PV_DRONE_RESEARCH_3.asStack())
                .inputItems(CTNHItems.MEASUREMENT_PV_CELL.asStack(), 14)
                .inputItems(plateDense, OPTICAL_HEAT_RESISTANT_FERROCHROME_ALLOY_080, 7)
                .inputItems(gearSmall, HIKARIUM, 112)
                .inputItems(HIGHLY_ADVANCED_SOC_WAFER, 14)
                .inputFluids(Cryotheum.getFluid(28000))
                .outputItems(CTNHItems.PV_DRONE_TIER2.asStack(), 7)
                .addData("input", 2091392)
                .duration(400)
                .save(provider);

        CTNHRecipeTypes.PHOTOVOLTAIC_ASSEMBER
                .recipeBuilder("liquid_sunshine")
                .inputItems(Items.GLOWSTONE_DUST, 64)
                .outputFluids(new FluidStack(
                        LIQUID_SUNSHINE, 10000))
                .addData("input", 500000)
                .duration(1000)
                .save(provider);

        CTNHRecipeTypes.PHOTOVOLTAIC_ASSEMBER.recipeBuilder("pv_terminal")
                .inputItems(TERMINAL)
                .inputItems(dust, Sunnarium)
                .outputItems(CTNHItems.PV_TERMINAL.asStack())
                .addData("input", 10)
                .duration(1)
                .save(provider);
    }
}
