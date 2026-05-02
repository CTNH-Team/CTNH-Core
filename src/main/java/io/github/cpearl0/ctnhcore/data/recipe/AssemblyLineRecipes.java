package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHBlocks;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import io.github.cpearl0.ctnhcore.registry.machines.CTNHMachines;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.GTNNMultiblocks;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksA;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksB;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksC;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GCYMBlocks;
import io.github.cpearl0.ctnhcore.data.materials.BedrockMaterials;
import io.github.cpearl0.ctnhcore.data.materials.YeastRelatedMaterials;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.machines.GTAEMachines;
import com.gregtechceu.gtceu.common.data.machines.GTMultiMachines;
import com.gregtechceu.gtceu.common.data.machines.GTResearchMachines;
import com.gregtechceu.gtceu.data.recipe.CustomTags;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import appeng.core.definitions.AEBlocks;
import com.github.elenterius.biomancy.init.ModItems;
import com.moguang.ctnhbio.data.materials.CommonMaterials;
import com.moguang.ctnhbio.registry.CBBlocks;
import com.moguang.ctnhbio.registry.CBItems;
import com.moguang.ctnhbio.registry.CBMachines;
import com.moguang.ctnhbio.registry.CBMultiblocks;
import tech.luckyblock.mcmod.ctnhenergy.registry.CEMachines;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.DUAL_IMPORT_HATCH;
import static com.gregtechceu.gtceu.common.data.GTMachines.HULL;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLY_LINE_RECIPES;
import static com.gregtechceu.gtceu.common.data.machines.GCYMMachines.PARALLEL_HATCH;
import static com.moguang.ctnhbio.data.materials.OrganicMaterials.*;
import static dev.shadowsoffire.hostilenetworks.Hostile.Items.SIM_CHAMBER;
import static tech.luckyblock.mcmod.ctnhenergy.registry.CEMachines.*;
import static tech.luckyblock.mcmod.ctnhenergy.registry.CEMachines.ME_ULTIMATE_PATTERN_BUFFER_PROXY;
import static tech.luckyblock.mcmod.ctnhenergy.registry.CEMultiblock.JIUZHANG_QUANTUM_COMPUTER;
import static io.github.cpearl0.ctnhcore.registry.machines.CTNHMachines.STERILE_CLEANROOM_MAINTENANCE_HATCH;
import static io.github.cpearl0.ctnhcore.registry.machines.multiblock.GTNNMultiblocks.LARGE_NAQUADAH_REACTOR;

public class AssemblyLineRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("async_thread_hatch_luv"))
                .inputItems(HULL[LuV].asStack(), 1)
                .inputItems(GTMachines.QUANTUM_CHEST[LuV].asStack(), 4)
                .inputItems(GTMachines.QUANTUM_TANK[LuV].asStack(), 4)
                .inputItems(CustomTags.UV_CIRCUITS, 4)
                .inputItems(ENERGY_LAPOTRONIC_ORB_CLUSTER, 4)
                .inputItems(COVER_MACHINE_CONTROLLER, 64)
                .inputItems(COVER_ACTIVITY_DETECTOR_ADVANCED, 64)
                .inputItems(COVER_SCREEN, 64)
                .inputFluids(new FluidStack(CTNHMaterials.Cerrobase140.getFluid(), 144 * 36))
                .inputFluids(new FluidStack(GTMaterials.HSSS.getFluid(), 144 * 36))
                .inputFluids(new FluidStack(GTMaterials.HSSG.getFluid(), 144 * 36))
                .inputFluids(new FluidStack(GTMaterials.HSSE.getFluid(), 144 * 36))
                .outputItems(CTNHMachines.ASYNC_THREAD_HATCH[LuV])
                .scannerResearch(b -> b.researchStack(PARALLEL_HATCH[LuV].asStack()).EUt(VA[LuV]))
                .EUt(VA[LuV]).duration(1200)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("cnc_alloy_smelter"))
                .inputItems(HULL[LuV].asStack(), 1)
                .inputItems(GTMachines.ALLOY_SMELTER[LuV].asStack(), 4)
                .inputItems(GTMachines.EXTRACTOR[LuV].asStack(), 4)
                .inputItems(CustomTags.LuV_CIRCUITS, 4)
                .inputItems(TagPrefix.cableGtOctal, VanadiumGallium, 16)
                .inputItems(TagPrefix.cableGtOctal, NiobiumNitride, 16)
                .inputItems(TagPrefix.cableGtOctal, TungstenSteel, 16)
                .inputItems(TagPrefix.cableGtOctal, Platinum, 16)
                .inputFluids(new FluidStack(TitaniumTungstenCarbide.getFluid(), 144 * 40))
                .inputFluids(new FluidStack(TantalumCarbide.getFluid(), 144 * 40))
                .outputItems(MultiblocksC.CNC_ALLOY_SMELTER, 1)
                .scannerResearch(b -> b.researchStack(GTMachines.ALLOY_SMELTER[LuV].asStack()).EUt(VA[LuV]))
                .EUt(VA[LuV]).duration(800)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("parabiotic_bridge"))
                .inputItems(ModItems.CREATOR_MIX, 4)
                .inputItems(TagPrefix.plate, CommonMaterials.BLOODSTEEL, 16)
                .inputItems(CustomTags.LuV_CIRCUITS, 4)
                .inputItems(GTMachines.ITEM_IMPORT_BUS[ZPM], 1)
                .inputItems(GTMachines.ITEM_EXPORT_BUS[ZPM], 1)
                .inputFluids(new FluidStack(Primordial_Serum.getFluid(), 144 * 20))
                .outputItems(CBMachines.PARABIOTIC_BRIDGE, 1)
                .stationResearch(
                        b -> b.researchStack(ModItems.CREATOR_MIX.get().getDefaultInstance()).CWUt(16).EUt(VA[ZPM]))
                .EUt(VA[LuV]).duration(400)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("consciousness_linker"))
                .inputItems(CBBlocks.NEURAL_NETWORK_CASING, 1)
                .inputItems(CTNHBlocks.SUPER_FREEZE_BLOCK, 4)
                .inputItems(TagPrefix.dust, Promethium, 16)
                .inputItems(CTNHItems.HEAVY_PLATE_T2, 4)
                .inputFluids(new FluidStack(CTNHMaterials.Cerrobase140.getFluid(), 144 * 10))
                .inputFluids(new FluidStack(CTNHMaterials.Cryotheum.getFluid(), 144 * 10))
                .outputItems(CBBlocks.CONSCIOUSNESS_LINKER, 4)
                .stationResearch(b -> b.researchStack(CTNHBlocks.SUPER_FREEZE_BLOCK.asStack()).CWUt(4))
                .EUt(VA[LuV]).duration(800)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("consciousness_controller"))
                .inputItems(CBBlocks.NEURAL_NETWORK_CASING, 1)
                .inputItems(CTNHBlocks.BLAZE_BLAST_FURNACE_CASING, 4)
                .inputItems(TagPrefix.dust, Praseodymium, 16)
                .inputItems(CTNHItems.HEAVY_PLATE_T2, 4)
                .inputFluids(new FluidStack(CTNHMaterials.Cerrobase140.getFluid(), 144 * 10))
                .inputFluids(new FluidStack(CTNHMaterials.Pyrotheum.getFluid(), 144 * 10))
                .outputItems(CBBlocks.CONSCIOUSNESS_CONTROLLER, 4)
                .stationResearch(b -> b.researchStack(CTNHBlocks.BLAZE_BLAST_FURNACE_CASING.asStack()).CWUt(4))
                .EUt(VA[LuV]).duration(800)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("neural_cooling_conduit"))
                .inputItems(CBBlocks.NEURAL_NETWORK_CASING, 1)
                .inputItems(TagPrefix.pipeTinyFluid, Europium, 16)
                .inputItems(TagPrefix.pipeTinyFluid, Europium, 16)
                .inputItems(TagPrefix.pipeTinyFluid, Europium, 16)
                .inputFluids(new FluidStack(CTNHMaterials.Cerrobase140.getFluid(), 144 * 10))
                .inputFluids(new FluidStack(Helium.getFluid(), 144 * 10))
                .outputItems(CBBlocks.NEURAL_COOLING_CONDUIT, 1)
                .stationResearch(b -> b.researchStack(CTNHBlocks.CASING_POLYBENZIMIDAZOLE_PIPE.asStack()).CWUt(8))
                .EUt(VA[LuV]).duration(800)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("sim_chamber"))
                .inputItems(HULL[IV], 1)
                .inputItems(CTNHItems.HEAVY_PLATE_T3, 4)
                .inputItems(TagPrefix.plateDouble, VanadiumGallium, 4)
                .inputItems(TagPrefix.plateDouble, NiobiumTitanium, 4)
                .inputItems(TagPrefix.plateDouble, Tantalum, 4)
                .inputItems(CustomTags.UV_CIRCUITS, 8)
                .inputItems(CBBlocks.CONSCIOUSNESS_SENSOR_GLASS, 16)
                .inputFluids(new FluidStack(SolderingAlloy.getFluid(), 144 * 5))
                .inputFluids(new FluidStack(BlueSteel.getFluid(), 144 * 5))
                .inputFluids(new FluidStack(Heterogeneous_Compound.getFluid(), 144 * 5))
                .outputItems(SIM_CHAMBER)
                .stationResearch(b -> b.researchStack(CBBlocks.CONSCIOUSNESS_SENSOR_GLASS.asStack()).CWUt(8))
                .EUt(VA[LuV]).duration(500)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("hostile_observer"))
                .inputItems(CTNHItems.HEAVY_PLATE_T3, 16)
                .inputItems(CTNHItems.HEAVY_PLATE_T3, 16)
                .inputItems(CTNHItems.HEAVY_PLATE_T3, 16)
                .inputItems(CTNHItems.HEAVY_PLATE_T3, 16)
                .inputItems(CTNHBlocks.CASING_POLYBENZIMIDAZOLE_PIPE, 16)
                .inputItems(CASING_POLYTETRAFLUOROETHYLENE_PIPE, 4)
                .inputItems(CASING_TITANIUM_PIPE, 4)
                .inputItems(CASING_STEEL_PIPE, 4)
                .inputItems(CustomTags.UV_CIRCUITS, 16)
                .inputItems(CBBlocks.CONSCIOUSNESS_SENSOR_GLASS, 16)
                .inputItems(CBBlocks.NEURAL_NETWORK_CASING, 16)
                .inputItems(ModItems.LIVING_FLESH, 16)
                .inputFluids(new FluidStack(Primordial_Serum.getFluid(), 144 * 5))
                .inputFluids(new FluidStack(Heterogeneous_Compound.getFluid(), 144 * 5))
                .inputFluids(new FluidStack(Unstable_Compound.getFluid(), 144 * 5))
                .outputItems(CBMultiblocks.HOSTILE_OBSERVER)
                .stationResearch(b -> b.researchStack(SIM_CHAMBER.get().getDefaultInstance()).CWUt(32).EUt(VA[UV]))
                .EUt(VA[ZPM]).duration(1000)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("advanced_neural_model_accessor"))
                .inputItems(CBBlocks.CONSCIOUSNESS_CONTROLLER)
                .inputItems(TOOL_DATA_MODULE)
                .inputItems(CBMachines.NEURAL_MODEL_ACCESSOR)
                .inputItems(TagPrefix.plate, Tritanium, 16)
                .inputFluids(new FluidStack(SolderingAlloy.getFluid(), 144 * 5))
                .inputFluids(CTNHMaterials.Cerrobase140, 288)
                .outputItems(CBMachines.ADVANCED_NEURAL_MODEL_ACCESSOR)
                .stationResearch(b -> b.researchStack(CBMachines.NEURAL_MODEL_ACCESSOR.asStack()).CWUt(32).EUt(VA[UV]))
                .EUt(VA[UV]).duration(1000)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("jiuzhang_quantum_computer"))
                .inputItems(GTMachines.SCANNER[LuV].asStack())
                .inputItems(GTMultiMachines.ACTIVE_TRANSFORMER.asStack())
                .inputItems(GTResearchMachines.HPCA_COMPUTATION_COMPONENT.asStack(), 16)
                .inputItems(GTResearchMachines.HPCA_ACTIVE_COOLER_COMPONENT.asStack(), 16)
                .inputItems(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ae2omnicells:omni_cell_component_64k")),
                        4)
                .inputItems(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ae2omnicells:omni_cell_component_256k")),
                        2)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ae2:cell_component_64k")), 4)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ae2:cell_component_256k")), 2)
                .inputItems(OPTICAL_PIPES[0].asStack(64))
                .inputItems(OPTICAL_PIPES[0].asStack(64))
                .inputItems(OPTICAL_PIPES[0].asStack(64))
                .inputItems(OPTICAL_PIPES[0].asStack(64))
                .inputFluids(new FluidStack(PCBCoolant.getFluid(), 2880 * 5))
                .outputItems(JIUZHANG_QUANTUM_COMPUTER, 1)
                .stationResearch(b -> b.researchStack(AEBlocks.MYSTERIOUS_CUBE.asItem().getDefaultInstance()).CWUt(4)
                        .EUt(VA[LuV]))
                .EUt(VA[LuV]).duration(5000)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("advanced_me_pattern_buffer"))
                .inputItems(HULL[ZPM].asStack())
                .inputItems(GTAEMachines.STOCKING_IMPORT_BUS_ME.asStack())
                .inputItems(GTAEMachines.STOCKING_IMPORT_HATCH_ME.asStack())
                .inputItems(frameGt, NaquadahAlloy, 4)
                .inputItems(spring, NaquadahAlloy, 4)
                .inputItems(screw, NaquadahAlloy, 16)
                .inputItems(CustomTags.IV_CIRCUITS, 4)
                .inputItems(CustomTags.LuV_CIRCUITS, 2)
                .inputItems(CustomTags.ZPM_CIRCUITS, 1)
                .inputItems(CBItems.OMNI_CORE, 1)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("pccard:card_programmed_circuit")), 4)
                .inputFluids(new FluidStack(SolderingAlloy.getFluid(), 2880 * 2))
                .inputFluids(new FluidStack(BorosilicateGlass.getFluid(), 2880 * 4))
                .outputItems(ME_ADVANCED_PATTERN_BUFFER, 1)
                .stationResearch(b -> b.researchStack(DUAL_IMPORT_HATCH[ZPM].asStack()).CWUt(16).EUt(VA[ZPM]))
                .EUt(VA[ZPM]).duration(6000)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("advanced_me_pattern_buffer_proxy"))
                .inputItems(HULL[ZPM].asStack())
                .inputItems(GTAEMachines.STOCKING_IMPORT_BUS_ME.asStack())
                .inputItems(GTAEMachines.STOCKING_IMPORT_HATCH_ME.asStack())
                .inputItems(frameGt, NaquadahAlloy, 4)
                .inputItems(spring, NaquadahAlloy, 4)
                .inputItems(screw, NaquadahAlloy, 16)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("pccard:card_programmed_circuit")), 4)
                .inputItems(SENSOR_ZPM, 2)
                .inputItems(AEBlocks.QUANTUM_RING.asItem(), 4)
                .inputItems(AEBlocks.QUANTUM_LINK.asItem(), 1)
                .inputFluids(new FluidStack(SolderingAlloy.getFluid(), 2880 * 2))
                .inputFluids(new FluidStack(BorosilicateGlass.getFluid(), 2880 * 4))
                .outputItems(ME_ADVANCED_PATTERN_BUFFER_PROXY, 1)
                .stationResearch(b -> b.researchStack(ME_ADVANCED_PATTERN_BUFFER.asStack()).CWUt(16).EUt(VA[ZPM]))
                .EUt(VA[ZPM]).duration(6000)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("ultimate_me_pattern_buffer"))
                .inputItems(CEMachines.ENERGY_INPUT_HATCH_ME.asStack())
                .inputItems(GTAEMachines.STOCKING_IMPORT_BUS_ME.asStack())
                .inputItems(GTAEMachines.STOCKING_IMPORT_HATCH_ME.asStack())
                .inputItems(frameGt, Neutronium, 4)
                .inputItems(spring, CTNHMaterials.SpecialCompositeSteelM77, 4)
                .inputItems(screw, Neutronium, 16)
                .inputItems(CustomTags.ZPM_CIRCUITS, 4)
                .inputItems(CustomTags.UV_CIRCUITS, 2)
                .inputItems(CustomTags.UHV_CIRCUITS, 1)
                .inputItems(CBItems.OMNI_CORE, 16)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("pccard:card_programmed_circuit")), 8)
                .inputFluids(new FluidStack(SolderingAlloy.getFluid(), 2880 * 4))
                .inputFluids(new FluidStack(BorosilicateGlass.getFluid(), 2880 * 8))
                .outputItems(ME_ULTIMATE_PATTERN_BUFFER, 1)
                .stationResearch(b -> b.researchStack(DUAL_IMPORT_HATCH[UV].asStack()).CWUt(64).EUt(VA[UV]))
                .EUt(VA[UV]).duration(6000)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("ultimate_me_pattern_buffer_proxy"))
                .inputItems(CEMachines.ENERGY_INPUT_HATCH_ME.asStack())
                .inputItems(GTAEMachines.STOCKING_IMPORT_BUS_ME.asStack())
                .inputItems(GTAEMachines.STOCKING_IMPORT_HATCH_ME.asStack())
                .inputItems(frameGt, Neutronium, 4)
                .inputItems(spring, CTNHMaterials.SpecialCompositeSteelM77, 4)
                .inputItems(screw, Neutronium, 16)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("pccard:card_programmed_circuit")), 8)
                .inputItems(SENSOR_UV, 2)
                .inputItems(AEBlocks.QUANTUM_RING.asItem(), 4)
                .inputItems(AEBlocks.QUANTUM_LINK.asItem(), 1)
                .inputFluids(new FluidStack(SolderingAlloy.getFluid(), 2880 * 4))
                .inputFluids(new FluidStack(BorosilicateGlass.getFluid(), 2880 * 8))
                .outputItems(ME_ULTIMATE_PATTERN_BUFFER_PROXY, 1)
                .stationResearch(b -> b.researchStack(ME_ULTIMATE_PATTERN_BUFFER.asStack()).CWUt(64).EUt(VA[UV]))
                .EUt(VA[UV]).duration(6000)
                .save(provider);

        // ============== Assembly Line Recipes from KubeJS ==============

        // 1. naq_reactor_mk3
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("naq_reactor_mk3"))
                .inputItems(HULL[UV].asStack(64))
                .inputItems(CTNHMachines.NAQUADAH_REACTOR[LuV].asStack(64))
                .inputItems(CTNHMachines.NAQUADAH_REACTOR[ZPM].asStack(64))
                .inputItems(CTNHMachines.NAQUADAH_REACTOR[UV].asStack(64))
                .inputItems(LARGE_NAQUADAH_REACTOR.asStack(64))
                .inputItems(NEUTRON_REFLECTOR.asStack(64))
                .inputItems(CTNHBlocks.CASING_NAQUADAH_BLOCK.asStack(64))
                .inputItems(CustomTags.UV_CIRCUITS, 64)
                .inputItems(CustomTags.UHV_CIRCUITS, 64)
                .inputItems(FIELD_GENERATOR_LuV.asStack(64))
                .inputItems(FIELD_GENERATOR_ZPM.asStack(64))
                .inputItems(FIELD_GENERATOR_UV.asStack(64))
                .inputFluids(new FluidStack(Tritanium.getFluid(), 16000))
                .inputFluids(new FluidStack(Neutronium.getFluid(), 16000))
                .inputFluids(new FluidStack(BedrockMaterials.AETHER.getFluid(), 16000))
                .inputFluids(new FluidStack(BedrockMaterials.ADAMANTITE.getFluid(), 16000))
                .outputItems(MultiblocksA.NAQ_REACTOR_MK3.asStack())
                .EUt(2044152).duration(600)
                .stationResearch(b -> b.researchStack(CTNHMachines.NAQUADAH_REACTOR[LuV].asStack())
                        .dataStack(TOOL_DATA_MODULE.asStack())
                        .EUt(VA[UHV]).CWUt(256))
                .save(provider);

        // 2. annihilate_core_mki
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("annihilate_core_mki"))
                .inputItems(frameGt, Naquadria, 4)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ctnhcore:plate_radiation_protection")), 16)
                .inputItems(NEUTRON_REFLECTOR.asStack(16))
                .inputItems(FIELD_GENERATOR_IV.asStack(8))
                .inputItems(FIELD_GENERATOR_LuV.asStack(4))
                .inputItems(FIELD_GENERATOR_ZPM.asStack(2))
                .inputFluids(new FluidStack(Neutronium.getFluid(), 2000))
                .outputItems(CTNHBlocks.ANNIHILATE_CORE_MKI.asStack())
                .EUt(204415).duration(600)
                .stationResearch(b -> b.researchStack(FUSION_COIL.asStack())
                        .dataStack(TOOL_DATA_MODULE.asStack())
                        .EUt(VA[ZPM]).CWUt(32))
                .save(provider);

        // 3. compressed_mk1 (LuV compressed fusion reactor)
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("compressed_mk1"))
                .inputItems(GTMultiMachines.FUSION_REACTOR[LuV].asStack(16))
                .inputItems(CTNHBlocks.HIGH_SPEED_PIPE_BLOCK.asStack(4))
                .inputItems(frameGt, Europium, 8)
                .inputItems(FUSION_COIL.asStack(4))
                .inputItems(NEUTRON_REFLECTOR.asStack(4))
                .inputItems(FIELD_GENERATOR_LuV.asStack(6))
                .inputFluids(FluidStack.parseFluidStackOr("gtceu:molten_enriched_naquadah_trinium_europium_duranide", 8000))
                .inputFluids(new FluidStack(SolderingAlloy.getFluid(), 8000))
                .outputItems(MultiblocksA.COMPRESSED_FUSION_REACTOR[LuV].asStack())
                .EUt(24987).duration(450)
                .stationResearch(b -> b.researchStack(NEUTRON_REFLECTOR.asStack())
                        .dataStack(TOOL_DATA_ORB.asStack())
                        .EUt(VA[LuV]).CWUt(16))
                .save(provider);

        // 4. compressed_mk2 (ZPM compressed fusion reactor)
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("compressed_mk2"))
                .inputItems(GTMultiMachines.FUSION_REACTOR[ZPM].asStack(16))
                .inputItems(CTNHBlocks.HIGH_SPEED_PIPE_BLOCK.asStack(16))
                .inputItems(frameGt, Tritanium, 8)
                .inputItems(FUSION_COIL.asStack(16))
                .inputItems(NEUTRON_REFLECTOR.asStack(16))
                .inputItems(FIELD_GENERATOR_ZPM.asStack(6))
                .inputFluids(new FluidStack(Tritanium.getFluid(), 8000))
                .inputFluids(new FluidStack(SolderingAlloy.getFluid(), 8000))
                .outputItems(MultiblocksA.COMPRESSED_FUSION_REACTOR[ZPM].asStack())
                .EUt(122800).duration(450)
                .stationResearch(b -> b.researchStack(MultiblocksA.COMPRESSED_FUSION_REACTOR[LuV].asStack())
                        .dataStack(TOOL_DATA_ORB.asStack())
                        .EUt(VA[ZPM]).CWUt(32))
                .save(provider);

        // 5. compressed_mk3 (UV compressed fusion reactor)
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("compressed_mk3"))
                .inputItems(GTMultiMachines.FUSION_REACTOR[UV].asStack(16))
                .inputItems(CTNHBlocks.HIGH_SPEED_PIPE_BLOCK.asStack(64))
                .inputItems(frameGt, Neutronium, 8)
                .inputItems(FUSION_COIL.asStack(64))
                .inputItems(NEUTRON_REFLECTOR.asStack(64))
                .inputItems(FIELD_GENERATOR_UV.asStack(6))
                .inputFluids(FluidStack.parseFluidStackOr("gtceu:ruthenium_trinium_americium_neutronate", 8000))
                .inputFluids(new FluidStack(SolderingAlloy.getFluid(), 8000))
                .outputItems(MultiblocksA.COMPRESSED_FUSION_REACTOR[UV].asStack())
                .EUt(491020).duration(450)
                .stationResearch(b -> b.researchStack(MultiblocksA.COMPRESSED_FUSION_REACTOR[ZPM].asStack())
                        .dataStack(TOOL_DATA_ORB.asStack())
                        .EUt(VA[UV]).CWUt(64))
                .save(provider);

        // 6. plasma_condenser
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("plasma_condenser"))
                .inputItems(GTMultiMachines.VACUUM_FREEZER.asStack())
                .inputItems(PLASMA_COOLED_CORE.asStack(2))
                .inputItems(CustomTags.ZPM_CIRCUITS, 4)
                .inputItems(NEUTRON_REFLECTOR.asStack(4))
                .inputItems(frameGt, Europium, 8)
                .inputItems(FIELD_GENERATOR_LuV.asStack(8))
                .inputFluids(new FluidStack(SolderingAlloy.getFluid(), 4000))
                .outputItems(MultiblocksA.PLASMA_CONDENSER.asStack())
                .EUt(6000).duration(500)
                .stationResearch(b -> b.researchStack(GTMultiMachines.VACUUM_FREEZER.asStack())
                        .dataStack(TOOL_DATA_ORB.asStack())
                        .EUt(VA[LuV]).CWUt(8))
                .save(provider);

        // 7. void_miner_create
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("void_miner_create"))
                .inputItems(HULL[UV].asStack(16))
                .inputItems(frameGt, BedrockMaterials.ADAMANTITE, 64)
                .inputItems(CustomTags.UHV_CIRCUITS, 32)
                .inputItems(CustomTags.UV_CIRCUITS, 64)
                .inputItems(gear, BedrockMaterials.ADAMANTITE, 64)
                .inputItems(gear, BedrockMaterials.ADAMANTITE, 64)
                .inputItems(gear, BedrockMaterials.AETHER, 64)
                .inputItems(gear, BedrockMaterials.AETHER, 64)
                .inputItems(ELECTRIC_MOTOR_UV.asStack(16))
                .inputItems(CONVEYOR_MODULE_UV.asStack(16))
                .inputItems(ELECTRIC_PISTON_UV.asStack(16))
                .inputItems(ROBOT_ARM_UV.asStack(16))
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:ev_large_miner")), 64)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:iv_large_miner")), 48)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:luv_large_miner")), 32)
                .inputItems(MultiblocksA.ZPM_LARGE_MINER.asStack(16))
                .inputFluids(FluidStack.parseFluidStackOr("gtceu:cerrobase_140", 512000))
                .inputFluids(new FluidStack(SolderingAlloy.getFluid(), 512000))
                .inputFluids(FluidStack.parseFluidStackOr("gtceu:adamantite_plasma", 512000))
                .inputFluids(FluidStack.parseFluidStackOr("gtceu:aether_plasma", 512000))
                .outputItems(MultiblocksA.VOID_MINER.asStack())
                .EUt(491020).duration(3600)
                .stationResearch(b -> b.researchStack(MultiblocksA.ZPM_LARGE_MINER.asStack())
                        .dataStack(TOOL_DATA_MODULE.asStack())
                        .EUt(VA[UV]).CWUt(256))
                .save(provider);

        // 8. silica_rock_fuel_refinery
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("silica_rock_fuel_refinery"))
                .inputItems(frameGt, Naquadah, 64)
                .inputItems(frameGt, NaquadahEnriched, 64)
                .inputItems(frameGt, Naquadria, 64)
                .inputItems(NEUTRON_SOURCE.asStack(64))
                .inputItems(plateDense, Obsidian, 7)
                .inputItems(plateDense, TungstenSteel, 7)
                .inputItems(plateDense, RhodiumPlatedPalladium, 7)
                .inputItems(plateDense, NaquadahAlloy, 7)
                .inputItems(GCYMBlocks.HEAT_VENT.asStack(64))
                .inputItems(GCYMBlocks.ELECTROLYTIC_CELL.asStack(64))
                .inputItems(GCYMBlocks.CASING_HIGH_TEMPERATURE_SMELTING.asStack(64))
                .inputItems(CASING_PTFE_INERT.asStack(64))
                .inputItems(CustomTags.UHV_CIRCUITS, 8)
                .inputItems(CustomTags.UV_CIRCUITS, 8)
                .inputItems(CustomTags.ZPM_CIRCUITS, 8)
                .inputItems(CustomTags.LuV_CIRCUITS, 8)
                .inputFluids(FluidStack.parseFluidStackOr("gtceu:cerrobase_140", 16000))
                .inputFluids(new FluidStack(Naquadria.getFluid(), 16000))
                .inputFluids(FluidStack.parseFluidStackOr("gtceu:pulsating_alloy", 16000))
                .inputFluids(FluidStack.parseFluidStackOr("gtceu:molten_tungsten_carbide", 16000))
                .outputItems(MultiblocksB.SILICA_ROCK_FUEL_REFINERY.asStack())
                .EUt(491520).duration(1000)
                .stationResearch(b -> b.researchStack(MultiblocksA.FUEL_REFINING_FACTORY.asStack())
                        .dataStack(TOOL_DATA_ORB.asStack())
                        .EUt(VA[ZPM]).CWUt(64))
                .save(provider);

        // 9. atoms_split_blocks
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("atoms_split_blocks"))
                .inputItems(FIELD_GENERATOR_UHV.asStack(4))
                .inputItems(REACTOR_CONDENSATION_BLOCK.asStack())
                .inputItems(CTNHBlocks.ANNIHILATE_CORE_MKI.asStack())
                .inputFluids(FluidStack.parseFluidStackOr("gtceu:bedrock_dust", 16000))
                .inputFluids(FluidStack.parseFluidStackOr("gtceu:cerrobase_140", 16000))
                .inputFluids(FluidStack.parseFluidStackOr("gtceu:molten_naquadah_alloy", 16000))
                .inputFluids(new FluidStack(Polybenzimidazole.getFluid(), 16000))
                .outputItems(CTNHBlocks.ATOMS_SPLIT_BLOCKS.asStack(2))
                .EUt(491052).duration(500)
                .stationResearch(b -> b.researchStack(REACTOR_CONDENSATION_BLOCK.asStack())
                        .dataStack(TOOL_DATA_ORB.asStack())
                        .EUt(VA[ZPM]).CWUt(64))
                .save(provider);

        // 10. sterile_cleanroom_maintenance_hatch
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("sterile_cleanroom_maintenance_hatch"))
                .inputItems(FILTER_CASING_STERILE.asStack(8))
                .inputItems(FILTER_CASING.asStack(8))
                .inputItems(ELECTRIC_MOTOR_LuV.asStack(4))
                .inputItems(ELECTRIC_PUMP_LuV.asStack(4))
                .inputItems(CustomTags.ZPM_CIRCUITS, 4)
                .inputItems(rotor, RhodiumPlatedPalladium, 4)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("enderio:extraction_speed_upgrade_4")), 4)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ae2omnicells:complex_omni_cell_component_1m")), 4)
                .inputItems(STEM_CELLS.asStack(64))
                .inputItems(STEM_CELLS.asStack(64))
                .inputItems(STEM_CELLS.asStack(64))
                .inputItems(STEM_CELLS.asStack(64))
                .inputItems(QUANTUM_EYE.asStack())
                .inputItems(QUANTUM_STAR.asStack())
                .inputItems(AUTO_MAINTENANCE_HATCH.asStack())
                .inputFluids(FluidStack.parseFluidStackOr("gtceu:cerrobase_140", 16000))
                .inputFluids(new FluidStack(SolderingAlloy.getFluid(), 16000))
                .inputFluids(new FluidStack(Argon.getFluid(), 16000))
                .inputFluids(new FluidStack(Fluorine.getFluid(), 16000))
                .outputItems(STERILE_CLEANROOM_MAINTENANCE_HATCH.asStack())
                .EUt(122330).duration(1200)
                .stationResearch(b -> b.researchStack(FILTER_CASING_STERILE.asStack())
                        .dataStack(TOOL_DATA_ORB.asStack())
                        .EUt(VA[LuV]).CWUt(16))
                .save(provider);

        // 11. echo_processor_2
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("echo_processor_2"))
                .inputItems(frameGt, Osmiridium)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("kubejs:echo_printed_circuit_board")))
                .inputItems(ECHO_PROCESSOR.asStack())
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:exquisite_echo_shard_gem")))
                .inputItems(BIOLOGICAL_PATCH_INDUCTOR.asStack(12))
                .inputItems(BIOLOGICAL_PATCH_CAPACITOR.asStack(24))
                .inputItems(ENGRAVED_CRYSTAL_CHIP.asStack(16))
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ctnhcore:sculk_cell")), 2)
                .inputItems(ENERGY_LAPOTRONIC_ORB.asStack())
                .inputFluids(FluidStack.parseFluidStackOr("gtceu:cerrobase_140", 1440))
                .inputFluids(FluidStack.parseFluidStackOr("gtceu:sterilized_growth_medium", 1440))
                .outputItems(ECHO_PROCESSOR_ASSEMBLY.asStack())
                .EUt(491520).duration(800)
                .stationResearch(b -> b.researchStack(ECHO_PROCESSOR.asStack())
                        .dataStack(TOOL_DATA_MODULE.asStack())
                        .EUt(VA[ZPM]).CWUt(32))
                .save(provider);

        // 12. echo_processor_3
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("echo_processor_3"))
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("kubejs:echo_printed_circuit_board")))
                .inputItems(ECHO_PROCESSOR_ASSEMBLY.asStack())
                .inputItems(BIOLOGICAL_PATCH_DIODE.asStack(32))
                .inputItems(HIGHLY_ADVANCED_SOC.asStack(8))
                .inputItems(NOR_MEMORY_CHIP.asStack(32))
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:ram_chip")), 64)
                .inputItems(wireFine, Tritanium, 64)
                .inputItems(wireFine, IndiumTinBariumTitaniumCuprate, 64)
                .inputItems(foil, Polybenzimidazole, 64)
                .inputItems(foil, KaptonK, 64)
                .inputItems(plate, Duranium, 8)
                .inputItems(plate, Tritanium, 8)
                .inputFluids(FluidStack.parseFluidStackOr("gtceu:cerrobase_140", 2880))
                .inputFluids(FluidStack.parseFluidStackOr("gtceu:sterilized_growth_medium", 2880))
                .outputItems(ECHO_PROCESSOR_COMPUTER.asStack())
                .EUt(491520).duration(800)
                .stationResearch(b -> b.researchStack(ECHO_PROCESSOR_ASSEMBLY.asStack())
                        .dataStack(TOOL_DATA_MODULE.asStack())
                        .EUt(VA[UV]).CWUt(96))
                .save(provider);

        // 13. echo_processor_4
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("echo_processor_4"))
                .inputItems(frameGt, Europium, 4)
                .inputItems(frameGt, Duranium, 4)
                .inputItems(frameGt, Tritanium, 4)
                .inputItems(frameGt, Neutronium, 4)
                .inputItems(ECHO_PROCESSOR_COMPUTER.asStack())
                .inputItems(BIOLOGICAL_PATCH_TRANSISTOR.asStack(64))
                .inputItems(BIOLOGICAL_PATCH_RESISTOR.asStack(64))
                .inputItems(BIOLOGICAL_PATCH_CAPACITOR.asStack(64))
                .inputItems(BIOLOGICAL_PATCH_DIODE.asStack(64))
                .inputItems(BIOLOGICAL_PATCH_INDUCTOR.asStack(64))
                .inputItems(foil, KaptonK, 64)
                .inputItems(ENERGY_LAPOTRONIC_ORB_CLUSTER.asStack(4))
                .inputItems(wireGtQuadruple, CTNHMaterials.SpecialCompositeSteelM77, 8)
                .inputItems(pipeTinyFluid, CTNHMaterials.HiddenAlloy, 16)
                .inputItems(SCULK_CELL.asStack(4))
                .inputItems(plateDense, CTNHMaterials.HiddenAlloy, 4)
                .inputFluids(FluidStack.parseFluidStackOr("gtceu:cerrobase_140", 2880))
                .inputFluids(FluidStack.parseFluidStackOr("gtceu:sterilized_growth_medium", 2880))
                .inputFluids(FluidStack.parseFluidStackOr("gtceu:sterilebiologicalculturemediumstocksolution", 2880))
                .outputItems(ECHO_PROCESSOR_MAINFRAME.asStack())
                .EUt(1966800).duration(1600)
                .stationResearch(b -> b.researchStack(ECHO_PROCESSOR_COMPUTER.asStack())
                        .dataStack(TOOL_DATA_MODULE.asStack())
                        .EUt(VA[UV]).CWUt(288))
                .save(provider);

        // 14. cultivationroom
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("cultivationroom"))
                .inputItems(frameGt, Tritanium, 4)
                .inputItems(CASING_STAINLESS_CLEAN.asStack(32))
                .inputItems(STERILE_CLEANROOM_MAINTENANCE_HATCH.asStack(4))
                .inputItems(FUSION_GLASS.asStack(27))
                .inputItems(STEM_CELLS.asStack(64))
                .inputItems(STEM_CELLS.asStack(64))
                .inputItems(STEM_CELLS.asStack(64))
                .inputItems(STEM_CELLS.asStack(64))
                .inputFluids(FluidStack.parseFluidStackOr("gtceu:sterilized_growth_medium", 16000))
                .inputFluids(new FluidStack(YeastRelatedMaterials.NORMAL_YEAST_EXTRACT_LIQUID.getFluid(), 16000))
                .inputFluids(new FluidStack(YeastRelatedMaterials.END_YEAST_EXTRACT_LIQUID.getFluid(), 16000))
                .outputItems(MultiblocksB.CultivationRoom.asStack())
                .EUt(24768).duration(1200)
                .stationResearch(b -> b.researchStack(MultiblocksA.LARGE_BOTTLE.asStack())
                        .dataStack(TOOL_DATA_STICK.asStack())
                        .EUt(VA[LuV]).CWUt(16))
                .save(provider);

        // 15. advance_machine_casing_assembly_control
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("advance_machine_casing_assembly_control"))
                .inputItems(frameGt, CTNHMaterials.SpecialCompositeSteelM77, 4)
                .inputItems(HIGHLY_ADVANCED_SOC.asStack(16))
                .inputItems(CustomTags.UV_CIRCUITS, 4)
                .inputItems(CustomTags.UHV_CIRCUITS, 8)
                .inputItems(ELECTRIC_MOTOR_ZPM.asStack(4))
                .inputItems(EMITTER_ZPM.asStack(4))
                .inputItems(SENSOR_ZPM.asStack(4))
                .inputFluids(FluidStack.parseFluidStackOr("gtceu:cerrobase_140", 16000))
                .outputItems(CTNHBlocks.ADVANCE_MACHINE_CASING_ASSEMBLY_CONTROL.asStack(4))
                .EUt(122330).duration(600)
                .stationResearch(b -> b.researchStack(CASING_ASSEMBLY_CONTROL.asStack())
                        .dataStack(TOOL_DATA_STICK.asStack())
                        .EUt(VA[LuV]).CWUt(48))
                .save(provider);

        // 16. advance_machine_casing_grate
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("advance_machine_casing_grate"))
                .inputItems(frameGt, CTNHMaterials.SpecialCompositeSteelM77)
                .inputItems(rotor, Osmiridium, 4)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("enderio:end_steel_bars")), 4)
                .inputItems(ELECTRIC_MOTOR_LuV.asStack(4))
                .inputFluids(FluidStack.parseFluidStackOr("gtceu:cerrobase_140", 16000))
                .outputItems(CTNHBlocks.ADVANCE_MACHINE_CASING_GRATE.asStack(4))
                .EUt(122330).duration(600)
                .stationResearch(b -> b.researchStack(CASING_GRATE.asStack())
                        .dataStack(TOOL_DATA_STICK.asStack())
                        .EUt(VA[LuV]).CWUt(16))
                .save(provider);

        // 17. advance_machine_casing_assembly_line
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("advance_machine_casing_assembly_line"))
                .inputItems(frameGt, CTNHMaterials.SpecialCompositeSteelM77)
                .inputItems(plate, CTNHMaterials.SpecialCompositeSteelM77, 8)
                .inputItems(CustomTags.UHV_CIRCUITS)
                .inputItems(gearSmall, CTNHMaterials.Inconel625, 16)
                .inputItems(gearSmall, HSSS, 16)
                .inputItems(gearSmall, HSSG, 16)
                .inputItems(gearSmall, CTNHMaterials.Eglinalloy, 16)
                .inputItems(ROBOT_ARM_ZPM.asStack(4))
                .inputFluids(FluidStack.parseFluidStackOr("gtceu:cerrobase_140", 16000))
                .inputFluids(new FluidStack(NaquadahAlloy.getFluid(), 32000))
                .outputItems(CTNHBlocks.ADVANCE_MACHINE_CASING_ASSEMBLY_LINE.asStack())
                .EUt(122330).duration(600)
                .stationResearch(b -> b.researchStack(CASING_ASSEMBLY_LINE.asStack())
                        .dataStack(TOOL_DATA_STICK.asStack())
                        .EUt(VA[LuV]).CWUt(48))
                .save(provider);

        // 18. advance_assembly_line
        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("advance_assembly_line"))
                .inputItems(HULL[ZPM].asStack())
                .inputItems(CTNHBlocks.ADVANCE_MACHINE_CASING_ASSEMBLY_CONTROL.asStack(4))
                .inputItems(CTNHBlocks.ADVANCE_MACHINE_CASING_ASSEMBLY_LINE.asStack(12))
                .inputItems(CustomTags.ZPM_CIRCUITS, 32)
                .inputItems(ROBOT_ARM_ZPM.asStack(16))
                .inputItems(ROBOT_ARM_ZPM.asStack(16))
                .inputItems(ROBOT_ARM_ZPM.asStack(16))
                .inputItems(ROBOT_ARM_ZPM.asStack(16))
                .inputFluids(FluidStack.parseFluidStackOr("gtceu:cerrobase_140", 16000))
                .outputItems(MultiblocksB.ADVANCED_ASSEMBLY_LINE.asStack())
                .EUt(122330).duration(600)
                .stationResearch(b -> b.researchStack(CTNHBlocks.ADVANCE_MACHINE_CASING_ASSEMBLY_CONTROL.asStack())
                        .dataStack(TOOL_DATA_STICK.asStack())
                        .EUt(VA[LuV]).CWUt(48))
                .save(provider);
    }
}
