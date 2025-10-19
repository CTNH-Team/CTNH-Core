package io.github.cpearl0.ctnhcore.data.recipe;

import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import io.github.cpearl0.ctnhcore.registry.CTNHMachines;
import io.github.cpearl0.ctnhcore.registry.CTNHMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.frameGt;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.wireGtSingle;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLY_LINE_RECIPES;
import static com.gregtechceu.gtceu.common.data.machines.GCYMMachines.PARALLEL_HATCH;
import static com.moguang.ctnhbio.data.materials.OrganicMaterials.Unstable_Compound;
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
                .inputFluids(new FluidStack(CTNHMaterials.Cerrobase140.getFluid(), 8000))
                .inputFluids(new FluidStack(GTMaterials.HSSS.getFluid(), 8000))
                .inputFluids(new FluidStack(GTMaterials.HSSG.getFluid(), 8000))
                .inputFluids(new FluidStack(GTMaterials.HSSE.getFluid(), 8000))
                .outputItems(CTNHMachines.ASYNC_THREAD_HATCH[LuV])
                .stationResearch(b -> b.researchStack(PARALLEL_HATCH[LuV].asStack()).CWUt(8))
                .EUt(VA[LuV]).duration(1200)
                .save(provider);
    }
}
