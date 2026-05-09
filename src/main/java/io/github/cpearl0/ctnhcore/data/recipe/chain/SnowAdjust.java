package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.data.recipe.CustomTags;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;

public class SnowAdjust {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 从 SnowAdjust.js 迁移
        VACUUM_RECIPES.recipeBuilder(CTNHCore.id("adjust_liquid_oxygen"))
                .inputFluids(Oxygen.getFluid(1000))
                .outputFluids(Oxygen.getFluid(FluidStorageKeys.LIQUID, 1000))
                .EUt(480)
                .duration(120)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("adjust_ender_fluid_link"))
                .inputItems(GTItems.SENSOR_LuV.asStack())
                .inputItems(GTItems.EMITTER_LuV.asStack())
                .inputItems(GTItems.ELECTRIC_PUMP_LuV.asStack())
                .inputItems(CustomTags.LuV_CIRCUITS, 2)
                .inputItems(plate, EnderEye, 8)
                .inputItems(plateDense, RhodiumPlatedPalladium, 4)
                .inputFluids(Polybenzimidazole.getFluid(576))
                .outputItems(GTItems.COVER_ENDER_FLUID_LINK.asStack())
                .EUt(30720)
                .duration(200)
                .save(provider);
    }
}
