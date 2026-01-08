package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.CustomTags;

import net.minecraft.data.recipes.FinishedRecipe;

import com.moguang.ctnhbio.registry.CBBlocks;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.foil;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.wireGtDouble;
import static com.gregtechceu.gtceu.common.data.GTBlocks.OPTICAL_PIPES;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLER_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLY_LINE_RECIPES;
import static com.gregtechceu.gtceu.common.data.machines.GTResearchMachines.DATA_BANK;
import static com.gregtechceu.gtceu.common.data.machines.GTResearchMachines.HIGH_PERFORMANCE_COMPUTING_ARRAY;

public class LuvModifyRecipe {

    public static void init(Consumer<FinishedRecipe> provider) {
        CTNHRecipeTypes.VACUUM_SINTERING.recipeBuilder(CTNHCore.id("consciousness_sensor_glass"))
                .notConsumable(GTItems.SHAPE_MOLD_BLOCK)
                .inputItems(TagPrefix.dust, GTMaterials.BorosilicateGlass, 21)
                .inputItems(TagPrefix.dust, GTMaterials.Lanthanum, 4)
                .inputItems(TagPrefix.dust, GTMaterials.Yttrium)
                .inputItems(TagPrefix.dust, Copper)
                .inputFluids(Oxygen, 3000)
                .outputItems(CBBlocks.CONSCIOUSNESS_SENSOR_GLASS)
                .blastFurnaceTemp(5400)
                .EUt(GTValues.V[GTValues.LuV])
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("high_performance_computing_array_modified"))
                .inputItems(DATA_BANK)
                .inputItems(CustomTags.ZPM_CIRCUITS, 4)
                .inputItems(FIELD_GENERATOR_LuV, 8)
                .inputItems(TOOL_DATA_ORB)
                .inputItems(COVER_SCREEN)
                .inputItems(CBBlocks.CONSCIOUSNESS_SENSOR_GLASS, 16)
                .inputItems(OPTICAL_PIPES[0].asStack(16))
                .inputFluids(SolderingAlloy, L * 8)
                .inputFluids(VanadiumGallium, L * 8)
                .inputFluids(PCBCoolant, 4000)
                .outputItems(HIGH_PERFORMANCE_COMPUTING_ARRAY)
                .scannerResearch(b -> b
                        .researchStack(COVER_SCREEN.asStack())
                        .duration(2400)
                        .EUt(VA[IV]))
                .duration(1200).EUt(100000)
                .addMaterialInfo(true, true).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("coil_trinium_modified"))
                .EUt(VA[ZPM])
                .inputItems(wireGtDouble, Trinium, 8)
                .inputItems(foil, NaquadahEnriched, 8)
                .inputFluids(Duranium, GTValues.L)
                .outputItems(GTBlocks.COIL_TRINIUM.asStack())
                .duration(800)
                .addMaterialInfo(true, true)
                .save(provider);
    }
}
