package io.github.cpearl0.ctnhcore.data.recipe;

import appeng.core.definitions.AEBlocks;
import com.github.elenterius.biomancy.init.ModItems;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.machines.GTAEMachines;
import com.gregtechceu.gtceu.common.data.machines.GTMultiMachines;
import com.gregtechceu.gtceu.common.data.machines.GTResearchMachines;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.moguang.ctnhbio.data.materials.CommonMaterials;
import com.moguang.ctnhbio.registry.CBMultiblocks;
import com.moguang.ctnhbio.registry.CBBlocks;
import com.moguang.ctnhbio.registry.CBItems;
import com.moguang.ctnhbio.registry.CBMachines;
import io.github.cpearl0.ctnhcore.registry.*;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksC;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

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
import static tech.luckyblock.mcmod.ctnhenergy.registry.CEMachines.ADVANCED_ME_PATTERN_BUFFER;
import static tech.luckyblock.mcmod.ctnhenergy.registry.CEMachines.ADVANCED_ME_PATTERN_BUFFER_PROXY;
import static tech.luckyblock.mcmod.ctnhenergy.registry.CEMultiblock.JIUZHANG_QUANTUM_COMPUTER;

public class AssemblyLineRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        ASSEMBLY_LINE_RECIPES.recipeBuilder("async_thread_hatch_luv")
                .inputItems(HULL[LuV].asStack(),1)
                .inputItems(GTMachines.QUANTUM_CHEST[LuV].asStack(),4)
                .inputItems(GTMachines.QUANTUM_TANK[LuV].asStack(),4)
                .inputItems(CustomTags.UV_CIRCUITS, 4)
                .inputItems(ENERGY_LAPOTRONIC_ORB_CLUSTER,4)
                .inputItems(COVER_MACHINE_CONTROLLER,64)
                .inputItems(COVER_ACTIVITY_DETECTOR_ADVANCED,64)
                .inputItems(COVER_SCREEN,64)
                .inputFluids(new FluidStack(CTNHMaterials.Cerrobase140.getFluid(), 144*36))
                .inputFluids(new FluidStack(GTMaterials.HSSS.getFluid(), 144*36))
                .inputFluids(new FluidStack(GTMaterials.HSSG.getFluid(), 144*36))
                .inputFluids(new FluidStack(GTMaterials.HSSE.getFluid(), 144*36))
                .outputItems(CTNHMachines.ASYNC_THREAD_HATCH[LuV])
                .scannerResearch(b -> b.researchStack(PARALLEL_HATCH[LuV].asStack()).EUt(VA[LuV]))
                .EUt(VA[LuV]).duration(1200)
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("cnc_alloy_smelter")
                .inputItems(HULL[LuV].asStack(),1)
                .inputItems(GTMachines.ALLOY_SMELTER[LuV].asStack(),4)
                .inputItems(GTMachines.EXTRACTOR[LuV].asStack(),4)
                .inputItems(CustomTags.LuV_CIRCUITS, 4)
                .inputItems(TagPrefix.cableGtOctal,VanadiumGallium,16)
                .inputItems(TagPrefix.cableGtOctal,NiobiumNitride,16)
                .inputItems(TagPrefix.cableGtOctal,TungstenSteel,16)
                .inputItems(TagPrefix.cableGtOctal,Platinum,16)
                .inputFluids(new FluidStack(TitaniumTungstenCarbide.getFluid(), 144*40))
                .inputFluids(new FluidStack(TantalumCarbide.getFluid(), 144*40))
                .outputItems(MultiblocksC.CNC_ALLOY_SMELTER,1)
                .scannerResearch(b -> b.researchStack(GTMachines.ALLOY_SMELTER[LuV].asStack()).EUt(VA[LuV]))
                .EUt(VA[LuV]).duration(800)
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("parabiotic_bridge")
                .inputItems(ModItems.CREATOR_MIX,4)
                .inputItems(TagPrefix.plate, CommonMaterials.BLOODSTEEL, 16)
                .inputItems(CustomTags.LuV_CIRCUITS,4)
                .inputItems(CTNHMachines.CIRCUIT_BUS[IV],2)
                .inputItems(GTMachines.ITEM_IMPORT_BUS[ZPM],1)
                .inputItems(GTMachines.ITEM_EXPORT_BUS[ZPM],1)
                .inputFluids(new FluidStack(Primordial_Serum.getFluid(), 144*20))
                .outputItems(CBMachines.PARABIOTIC_BRIDGE,1)
                .stationResearch(b -> b.researchStack(ModItems.CREATOR_MIX.get().getDefaultInstance()).CWUt(16).EUt(VA[ZPM]))
                .EUt(VA[LuV]).duration(400)
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("consciousness_linker")
                .inputItems(CBBlocks.NEURAL_NETWORK_CASING,1)
                .inputItems(CTNHBlocks.SUPER_FREEZE_BLOCK,4)
                .inputItems(TagPrefix.dust,Promethium,16)
                .inputItems(CTNHItems.HEAVY_PLATE_T2,4)
                .inputFluids(new FluidStack(CTNHMaterials.Cerrobase140.getFluid(), 144*10))
                .inputFluids(new FluidStack(CTNHMaterials.Cryotheum.getFluid(), 144*10))
                .outputItems(CBBlocks.CONSCIOUSNESS_LINKER,4)
                .stationResearch(b -> b.researchStack(CTNHBlocks.SUPER_FREEZE_BLOCK.asStack()).CWUt(4))
                .EUt(VA[LuV]).duration(800)
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("consciousness_controller")
                .inputItems(CBBlocks.NEURAL_NETWORK_CASING,1)
                .inputItems(CTNHBlocks.BLAZE_BLAST_FURNACE_CASING,4)
                .inputItems(TagPrefix.dust,Praseodymium,16)
                .inputItems(CTNHItems.HEAVY_PLATE_T2,4)
                .inputFluids(new FluidStack(CTNHMaterials.Cerrobase140.getFluid(), 144*10))
                .inputFluids(new FluidStack(CTNHMaterials.Pyrotheum.getFluid(), 144*10))
                .outputItems(CBBlocks.CONSCIOUSNESS_CONTROLLER,4)
                .stationResearch(b -> b.researchStack(CTNHBlocks.BLAZE_BLAST_FURNACE_CASING.asStack()).CWUt(4))
                .EUt(VA[LuV]).duration(800)
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("neural_cooling_conduit")
                .inputItems(CBBlocks.NEURAL_NETWORK_CASING,1)
                .inputItems(TagPrefix.pipeTinyFluid,Europium,16)
                .inputItems(TagPrefix.pipeTinyFluid,Europium,16)
                .inputItems(TagPrefix.pipeTinyFluid,Europium,16)
                .inputFluids(new FluidStack(CTNHMaterials.Cerrobase140.getFluid(), 144*10))
                .inputFluids(new FluidStack(Helium.getFluid(), 144*10))
                .outputItems(CBBlocks.NEURAL_COOLING_CONDUIT,1)
                .stationResearch(b -> b.researchStack(CTNHBlocks.CASING_POLYBENZIMIDAZOLE_PIPE.asStack()).CWUt(8))
                .EUt(VA[LuV]).duration(800)
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("sim_chamber")
                .inputItems(HULL[IV],1)
                .inputItems(CTNHItems.HEAVY_PLATE_T3,4)
                .inputItems(TagPrefix.plateDouble,VanadiumGallium,4)
                .inputItems(TagPrefix.plateDouble,NiobiumTitanium,4)
                .inputItems(TagPrefix.plateDouble,Tantalum,4)
                .inputItems(CustomTags.UV_CIRCUITS,8)
                .inputItems(CBBlocks.CONSCIOUSNESS_SENSOR_GLASS,16)
                .inputFluids(new FluidStack(SolderingAlloy.getFluid(), 144*5))
                .inputFluids(new FluidStack(BlueSteel.getFluid(), 144*5))
                .inputFluids(new FluidStack(Heterogeneous_Compound.getFluid(), 144*5))
                .outputItems(SIM_CHAMBER)
                .stationResearch(b -> b.researchStack(CBBlocks.CONSCIOUSNESS_SENSOR_GLASS.asStack()).CWUt(8))
                .EUt(VA[LuV]).duration(500)
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("hostile_observer")
                .inputItems(CTNHItems.HEAVY_PLATE_T3,16)
                .inputItems(CTNHItems.HEAVY_PLATE_T3,16)
                .inputItems(CTNHItems.HEAVY_PLATE_T3,16)
                .inputItems(CTNHItems.HEAVY_PLATE_T3,16)
                .inputItems(CTNHBlocks.CASING_POLYBENZIMIDAZOLE_PIPE,16)
                .inputItems(CASING_POLYTETRAFLUOROETHYLENE_PIPE,4)
                .inputItems(CASING_TITANIUM_PIPE,4)
                .inputItems(CASING_STEEL_PIPE,4)
                .inputItems(CustomTags.UV_CIRCUITS,16)
                .inputItems(CBBlocks.CONSCIOUSNESS_SENSOR_GLASS,16)
                .inputItems(CBBlocks.NEURAL_NETWORK_CASING,16)
                .inputItems(ModItems.LIVING_FLESH,16)
                .inputFluids(new FluidStack(Primordial_Serum.getFluid(), 144*5))
                .inputFluids(new FluidStack(Heterogeneous_Compound.getFluid(), 144*5))
                .inputFluids(new FluidStack(Unstable_Compound.getFluid(), 144*5))
                .outputItems(CBMultiblocks.HOSTILE_OBSERVER)
                .stationResearch(b -> b.researchStack(SIM_CHAMBER.get().getDefaultInstance()).CWUt(32).EUt(VA[UV]))
                .EUt(VA[ZPM]).duration(1000)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("advanced_neural_model_accessor")
                .inputItems(CBBlocks.CONSCIOUSNESS_CONTROLLER)
                .inputItems(TOOL_DATA_MODULE)
                .inputItems(CBMachines.NEURAL_MODEL_ACCESSOR)
                .inputItems(TagPrefix.plate, Tritanium, 16)
                .inputFluids(new FluidStack(SolderingAlloy.getFluid(), 144*5))
                .inputFluids(CTNHMaterials.Cerrobase140, 288)
                .outputItems(CBMachines.ADVANCED_NEURAL_MODEL_ACCESSOR)
                .stationResearch(b->b.researchStack(CBMachines.NEURAL_MODEL_ACCESSOR.asStack()).CWUt(32).EUt(VA[UV]))
                .EUt(VA[UV]).duration(1000)
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("jiuzhang_quantum_computer")
                .inputItems(GTMachines.SCANNER[LuV].asStack())
                .inputItems(GTMultiMachines.ACTIVE_TRANSFORMER.asStack())
                .inputItems(GTResearchMachines.HPCA_COMPUTATION_COMPONENT.asStack(),16)
                .inputItems(GTResearchMachines.HPCA_ACTIVE_COOLER_COMPONENT.asStack(),16)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ae2omnicells:omni_cell_component_64k")),4)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ae2omnicells:omni_cell_component_256k")),2)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ae2:cell_component_64k")),4)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ae2:cell_component_256k")),2)
                .inputItems(OPTICAL_PIPES[0].asStack(64))
                .inputItems(OPTICAL_PIPES[0].asStack(64))
                .inputItems(OPTICAL_PIPES[0].asStack(64))
                .inputItems(OPTICAL_PIPES[0].asStack(64))
                .inputFluids(new FluidStack(PCBCoolant.getFluid(), 2880*5))
                .outputItems(JIUZHANG_QUANTUM_COMPUTER,1)
                .stationResearch(b->b.researchStack(AEBlocks.MYSTERIOUS_CUBE.asItem().getDefaultInstance()).CWUt(4).EUt(VA[LuV]))
                .EUt(VA[LuV]).duration(5000)
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("advanced_me_pattern_buffer")
                .inputItems(HULL[ZPM].asStack())
                .inputItems(GTAEMachines.STOCKING_IMPORT_BUS_ME.asStack())
                .inputItems(GTAEMachines.STOCKING_IMPORT_HATCH_ME.asStack())
                .inputItems(frameGt,NaquadahAlloy,4)
                .inputItems(spring,NaquadahAlloy,4)
                .inputItems(screw,NaquadahAlloy,16)
                .inputItems(CustomTags.IV_CIRCUITS,4)
                .inputItems(CustomTags.LuV_CIRCUITS,2)
                .inputItems(CustomTags.ZPM_CIRCUITS,1)
                .inputItems(CBItems.OMNI_CORE,1)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("pccard:card_programmed_circuit")),4)
                .inputFluids(new FluidStack(SolderingAlloy.getFluid(), 2880*2))
                .inputFluids(new FluidStack(BorosilicateGlass.getFluid(), 2880*4))
                .outputItems(ADVANCED_ME_PATTERN_BUFFER,1)
                .stationResearch(b->b.researchStack(DUAL_IMPORT_HATCH[ZPM].asStack()).CWUt(16).EUt(VA[ZPM]))
                .EUt(VA[ZPM]).duration(6000)
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("advanced_me_pattern_buffer_proxy")
                .inputItems(HULL[ZPM].asStack())
                .inputItems(GTAEMachines.STOCKING_IMPORT_BUS_ME.asStack())
                .inputItems(GTAEMachines.STOCKING_IMPORT_HATCH_ME.asStack())
                .inputItems(frameGt,NaquadahAlloy,4)
                .inputItems(spring,NaquadahAlloy,4)
                .inputItems(screw,NaquadahAlloy,16)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("pccard:card_programmed_circuit")),4)
                .inputItems(SENSOR_ZPM,2)
                .inputItems(AEBlocks.QUANTUM_RING.asItem(),4)
                .inputItems(AEBlocks.QUANTUM_LINK.asItem(),1)
                .inputFluids(new FluidStack(SolderingAlloy.getFluid(), 2880*2))
                .inputFluids(new FluidStack(BorosilicateGlass.getFluid(), 2880*4))
                .outputItems(ADVANCED_ME_PATTERN_BUFFER_PROXY,1)
                .stationResearch(b->b.researchStack(ADVANCED_ME_PATTERN_BUFFER.asStack()).CWUt(16).EUt(VA[ZPM]))
                .EUt(VA[ZPM]).duration(6000)
                .save(provider);
    }
}
