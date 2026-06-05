package io.github.cpearl0.ctnhcore.data.recipe.migrated;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.BedrockMaterials;
import io.github.cpearl0.ctnhcore.data.materials.UncategorizedMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHBlocks;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksA;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksB;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.data.recipe.CustomTags;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraftforge.fluids.FluidStack;

import committee.nova.mods.avaritia.init.registry.ModItems;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;

public class BioScriptRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        addAdvancedBioReactorCasing(provider);
        addResearchDatasetLM(provider);
        addSupercooledBlock(provider);
        addNeuroMatrixCompiler(provider);
    }

    // 迁移自: bio.js
    // ctnh.assembly_line('ctnhcore:advanced_bio_reactor_casing')
    private static void addAdvancedBioReactorCasing(Consumer<FinishedRecipe> provider) {
        GTRecipeTypes.ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("advanced_bio_reactor_casing"))
                .inputItems(CTNHItems.PlateRadiationProtection.asStack(7))
                .inputItems(ChemicalHelper.get(TagPrefix.frameGt, GTMaterials.HastelloyX, 56))
                .inputItems(ChemicalHelper.get(TagPrefix.plateDense, UncategorizedMaterials.RADIATION_SIGHT_ALLOY_X, 7))
                .inputItems(CustomTags.UV_CIRCUITS, 2)
                .inputItems(CTNHBlocks.BIO_REACTOR_CASING.asStack(7))
                .inputItems(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Neutronium, 7))
                .inputFluids(new FluidStack(CTNHMaterials.Cerrobase140.getFluid(), 14370))
                .inputFluids(new FluidStack(GTMaterials.NaquadahAlloy.getFluid(), 14370))
                .outputItems(CTNHBlocks.ADVANCED_BIO_REACTOR_CASING.asStack(7))
                .stationResearch(b -> b
                        .researchStack(CTNHBlocks.BIO_REACTOR_CASING.asStack())
                        .dataStack(GTItems.TOOL_DATA_ORB.asStack())
                        .EUt(VA[ZPM])
                        .CWUt(28))
                .EUt(32768).duration(100)
                .save(provider);
    }

    // 迁移自: bio.js
    // ctnh.compiler_recipe("lm")
    private static void addResearchDatasetLM(Consumer<FinishedRecipe> provider) {
        CTNHRecipeTypes.COMPILER_RECIPE.recipeBuilder(CTNHCore.id("research_dataset_lm"))
                .inputItems(ChemicalHelper.get(TagPrefix.dust, GTMaterials.SolderingAlloy))
                .inputItems(CTNHItems.SCULK_CELL.asStack())
                .inputItems(ChemicalHelper.get(TagPrefix.rodLong, UncategorizedMaterials.RADIATION_SIGHT_ALLOY_INF))
                .inputItems(ModItems.crystal_matrix_ingot.get().getDefaultInstance())
                .inputItems(CustomTags.UEV_CIRCUITS)
                .outputItems(CTNHItems.RESEARCH_DATASET_LIVING_MATERIAL.asStack())
                .addData("1", 48)
                .addData("2", 15)
                .addData("3", 30)
                .addData("noisea", 20)
                .addData("noiseb", 2)
                .addData("range", 120)
                .duration(20 * 30)
                .EUt(1000000)
                .save(provider);
    }

    // 迁移自: bio.js
    // ctnh.assembler("cold")
    private static void addSupercooledBlock(Consumer<FinishedRecipe> provider) {
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("supercooled_bloock"))
                .inputItems(ChemicalHelper.get(TagPrefix.wireFine, GTMaterials.HSSG, 64))
                .inputItems(GTItems.VOLTAGE_COIL_IV.asStack(4))
                .inputFluids(new FluidStack(CTNHMaterials.Cryotheum.getFluid(), 10000))
                .outputItems(CTNHBlocks.SUPERCOOLED_BLOCK.asStack())
                .EUt(8192).duration(100)
                .save(provider);
    }

    // 迁移自: bio.js
    // ctnh.assembly_line('ctnhcore:bio_reactor') → 输出 neruo_martix_compiler
    private static void addNeuroMatrixCompiler(Consumer<FinishedRecipe> provider) {
        GTRecipeTypes.ASSEMBLY_LINE_RECIPES.recipeBuilder(CTNHCore.id("neruo_martix_compiler"))
                .inputItems(MultiblocksA.BIO_REACTOR.asStack(64))
                .inputItems(CTNHBlocks.ADVANCED_BIO_REACTOR_CASING.asStack(8))
                .inputItems(ChemicalHelper.get(TagPrefix.plate, GTMaterials.RutheniumTriniumAmericiumNeutronate, 64))
                .inputItems(ChemicalHelper.get(TagPrefix.plateDense, UncategorizedMaterials.RADIATION_SIGHT_ALLOY_X, 7))
                .inputItems(CustomTags.UHV_CIRCUITS, 8)
                .inputItems(GTItems.ELECTRIC_PUMP_ZPM.asStack(64))
                .inputItems(GTItems.ELECTRIC_PISTON_ZPM.asStack(64))
                .inputFluids(new FluidStack(CTNHMaterials.Cerrobase140.getFluid(), 10000))
                .inputFluids(new FluidStack(GTMaterials.SolderingAlloy.getFluid(), 10000))
                .inputFluids(new FluidStack(GTMaterials.NaquadahAlloy.getFluid(), 10000))
                .inputFluids(new FluidStack(BedrockMaterials.BEDROCK_NEUTRONIUM.getFluid(), 10000))
                .outputItems(MultiblocksB.NERUOMATRIXCOMPILER.asStack())
                .stationResearch(b -> b
                        .researchStack(MultiblocksA.BIO_REACTOR.asStack())
                        .dataStack(GTItems.TOOL_DATA_ORB.asStack())
                        .EUt(VA[ZPM])
                        .CWUt(128))
                .EUt(81920).duration(1000)
                .save(provider);
    }
}
