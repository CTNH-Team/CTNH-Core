package io.github.cpearl0.ctnhcore.data.recipe;

import com.github.elenterius.biomancy.init.ModItems;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.moguang.ctnhbio.registry.CBBlocks;
import com.moguang.ctnhbio.registry.CBMachines;
import com.moguang.ctnhbio.registry.CBMultiblockMachines;
import io.github.cpearl0.ctnhcore.registry.*;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksA;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksB;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksC;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.frameGt;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.wireGtSingle;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLY_LINE_RECIPES;
import static com.gregtechceu.gtceu.common.data.machines.GCYMMachines.PARALLEL_HATCH;
import static com.moguang.ctnhbio.data.materials.OrganicMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.CTNHItems.ADVANCED_RAM_CHIP;

public class AssemblyLineRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        ASSEMBLY_LINE_RECIPES.recipeBuilder("async_thread_hatch_luv")
                .inputItems(GTMachines.HULL[LuV].asStack(),1)
                .inputItems(GTMachines.QUANTUM_CHEST[LuV].asStack(),4)
                .inputItems(GTMachines.QUANTUM_TANK[LuV].asStack(),4)
                .inputItems(CustomTags.UV_CIRCUITS, 4)
                .inputItems(ENERGY_LAPOTRONIC_ORB_CLUSTER,4)
                .inputItems(COVER_MACHINE_CONTROLLER,64)
                .inputItems(COVER_ACTIVITY_DETECTOR_ADVANCED,64)
                .inputItems(PROGRAMMED_CIRCUIT,64)
                .inputFluids(new FluidStack(CTNHMaterials.Cerrobase140.getFluid(), 144*36))
                .inputFluids(new FluidStack(GTMaterials.HSSS.getFluid(), 144*36))
                .inputFluids(new FluidStack(GTMaterials.HSSG.getFluid(), 144*36))
                .inputFluids(new FluidStack(GTMaterials.HSSE.getFluid(), 144*36))
                .outputItems(CTNHMachines.ASYNC_THREAD_HATCH[LuV])
                .stationResearch(b -> b.researchStack(PARALLEL_HATCH[LuV].asStack()).CWUt(8))
                .EUt(VA[LuV]).duration(1200)
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("cnc_alloy_smelter")
                .inputItems(GTMachines.HULL[LuV].asStack(),1)
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
                .stationResearch(b -> b.researchStack(GTMachines.ALLOY_SMELTER[LuV].asStack()).CWUt(2))
                .EUt(VA[LuV]).duration(800)
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("parabiotic_bridge")
                .inputItems(ModItems.CREATOR_MIX,4)
                .inputItems(ModItems.LIVING_FLESH,4)
                .inputItems(CustomTags.LuV_CIRCUITS,4)
                .inputItems(CTNHMachines.CIRCUIT_BUS[IV],2)
                .inputItems(GTMachines.ITEM_IMPORT_BUS[ZPM],1)
                .inputItems(GTMachines.ITEM_EXPORT_BUS[ZPM],1)
                .inputFluids(new FluidStack(Primordial_Serum.getFluid(), 144*20))
                .outputItems(CBMachines.PARABIOTIC_BRIDGE,1)
                .stationResearch(b -> b.researchStack(ModItems.CREATOR_MIX.get().getDefaultInstance()).CWUt(8))
                .EUt(VA[LuV]).duration(400)
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("consciousness_linker")
                .inputItems(CBBlocks.NEURAL_NETWORK_CASING,1)
                .inputItems(CTNHBlocks.SUPER_FREEZE_BLOCK,4)
                .inputItems(TagPrefix.dust,Promethium,16)
                .inputItems(CTNHItems.HEAVY_PLATE_T2,16)
                .inputFluids(new FluidStack(CTNHMaterials.Cerrobase140.getFluid(), 144*10))
                .inputFluids(new FluidStack(CTNHMaterials.Cryotheum.getFluid(), 144*10))
                .outputItems(CBBlocks.CONSCIOUSNESS_LINKER,1)
                .stationResearch(b -> b.researchStack(CTNHBlocks.SUPER_FREEZE_BLOCK.asStack()).CWUt(4))
                .EUt(VA[LuV]).duration(800)
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("consciousness_controller")
                .inputItems(CBBlocks.NEURAL_NETWORK_CASING,1)
                .inputItems(CTNHBlocks.BLAZE_BLAST_FURNACE_CASING,4)
                .inputItems(TagPrefix.dust,Praseodymium,16)
                .inputItems(CTNHItems.HEAVY_PLATE_T2,16)
                .inputFluids(new FluidStack(CTNHMaterials.Cerrobase140.getFluid(), 144*10))
                .inputFluids(new FluidStack(CTNHMaterials.Pyrotheum.getFluid(), 144*10))
                .outputItems(CBBlocks.CONSCIOUSNESS_CONTROLLER,1)
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
                .inputItems(GTMachines.HULL[IV],1)
                .inputItems(CTNHItems.HEAVY_PLATE_T3,4)
                .inputItems(TagPrefix.plateDouble,VanadiumGallium,4)
                .inputItems(TagPrefix.plateDouble,NiobiumTitanium,4)
                .inputItems(TagPrefix.plateDouble,Tantalum,4)
                .inputItems(CustomTags.UV_CIRCUITS,8)
                .inputItems(CBBlocks.CONSCIOUSNESS_SENSOR_GLASS,16)
                .inputFluids(new FluidStack(SolderingAlloy.getFluid(), 144*5))
                .inputFluids(new FluidStack(BlueSteel.getFluid(), 144*5))
                .inputFluids(new FluidStack(Heterogeneous_Compound.getFluid(), 144*5))
                .outputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("hostilenetworks:sim_chamber")),1)
                .stationResearch(b -> b.researchStack(CBBlocks.CONSCIOUSNESS_SENSOR_GLASS.asStack()).CWUt(8))
                .EUt(VA[LuV]).duration(500)
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("hostile_observer")
                .inputItems(CTNHItems.HEAVY_PLATE_T3,64)
                .inputItems(CTNHItems.HEAVY_PLATE_T3,64)
                .inputItems(CTNHItems.HEAVY_PLATE_T3,64)
                .inputItems(CTNHItems.HEAVY_PLATE_T3,64)
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
                .outputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ctnhbio:hostile_observer")))
                .stationResearch(b -> b.researchStack(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("hostilenetworks:sim_chamber")).getDefaultInstance()).CWUt(24))
                .EUt(VA[ZPM]).duration(1000)
                .save(provider);
    }
}
