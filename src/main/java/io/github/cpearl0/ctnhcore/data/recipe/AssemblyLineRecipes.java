package io.github.cpearl0.ctnhcore.data.recipe;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.frameGt;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.wireGtSingle;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLY_LINE_RECIPES;
import static io.github.cpearl0.ctnhcore.registry.CTNHItems.ADVANCED_RAM_CHIP;

public class AssemblyLineRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
//        ASSEMBLY_LINE_RECIPES.recipeBuilder("crystal_mainframe_uv_aram")
//                .inputItems(frameGt, HSSE, 2)
//                .inputItems(CRYSTAL_COMPUTER_ZPM, 2)
//                .inputItems(ADVANCED_RAM_CHIP, 8)
//                .inputItems(HIGH_POWER_INTEGRATED_CIRCUIT, 2)
//                .inputItems(wireGtSingle, NiobiumTitanium, 8)
//                .inputItems(ADVANCED_SMD_INDUCTOR, 8)
//                .inputItems(ADVANCED_SMD_CAPACITOR, 16)
//                .inputItems(ADVANCED_SMD_DIODE, 8)
//                .inputFluids(SolderingAlloy.getFluid(L * 10))
//                .outputItems(CRYSTAL_MAINFRAME_UV)
//                .stationResearch(b -> b.researchStack(CRYSTAL_COMPUTER_ZPM.asStack()).CWUt(16))
//                .EUt(VA[LuV]).duration(600)
//                .save(provider);
    }
}
