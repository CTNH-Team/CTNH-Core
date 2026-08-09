package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.data.recipe.CustomTags;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;

import com.moguang.ctnhbio.registry.CBBlocks;
import dev.shadowsoffire.hostilenetworks.Hostile;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.HULL;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.moguang.ctnhbio.data.materials.OrganicMaterials.Heterogeneous_Compound;
import static dev.shadowsoffire.hostilenetworks.Hostile.Items.SIM_CHAMBER;

public class HNNRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("deep_learner"))
                .EUt(480).duration(200)
                .inputItems(GTItems.COVER_SCREEN.asStack())
                .inputItems(plate, BlackSteel, 2)
                .inputItems(cableGtSingle, Gold, 2)
                .inputItems(CustomTags.EV_CIRCUITS)
                .outputItems(Hostile.Items.DEEP_LEARNER.get())
                .save(provider);

        FORMING_PRESS_RECIPES.recipeBuilder(CTNHCore.id("blank_data_model"))
                .EUt(480).duration(200)
                .inputItems(plate, EnderPearl, 2)
                .inputItems(plate, StainlessSteel, 2)
                .inputItems(wireFine, Platinum, 2)
                .inputItems(new ItemStack(Items.SMOOTH_STONE))
                .outputItems(Hostile.Items.BLANK_DATA_MODEL.get())
                .save(provider);

        LASER_ENGRAVER_RECIPES.recipeBuilder(CTNHCore.id("prediction_matrix"))
                .inputItems(gemExquisite, Glass)
                .notConsumable(lens, NetherStar)
                .outputItems(Hostile.Items.PREDICTION_MATRIX.get(), 16)
                .EUt(VA[IV])
                .duration(200)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("sim_chamber"))
                .inputItems(HULL[IV].asStack(), 1)
                .inputItems(CTNHItems.HEAVY_PLATE_T3, 4)
                .inputItems(TagPrefix.plateDouble, VanadiumGallium, 4)
                .inputItems(TagPrefix.plateDouble, NiobiumTitanium, 4)
                .inputItems(TagPrefix.plateDouble, Tantalum, 4)
                .inputItems(CustomTags.UV_CIRCUITS, 8)
                .inputItems(CBBlocks.CONSCIOUSNESS_SENSOR_GLASS.asItem(), 16)
                .inputFluids(new FluidStack(SolderingAlloy.getFluid(), 144 * 5))
                .inputFluids(new FluidStack(BlueSteel.getFluid(), 144 * 5))
                .inputFluids(new FluidStack(Heterogeneous_Compound.getFluid(), 144 * 5))
                .outputItems(SIM_CHAMBER.get())
                .stationResearch(b -> b.researchStack(CBBlocks.CONSCIOUSNESS_SENSOR_GLASS.asStack()).CWUt(8))
                .EUt(VA[LuV]).duration(500)
                .save(provider);
    }
}
