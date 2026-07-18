package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;

import com.mo_guang.ctpp.common.recipe.builder.create.SequencedAssemblyRecipeBuilder;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Water;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLER_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MIXER_RECIPES;

public class CementChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        ItemStack cokeBricks = GTBlocks.CASING_COKE_BRICKS.asStack();
        ItemStack cokeBrick = GTItems.COKE_OVEN_BRICK.asStack();
        SequencedAssemblyRecipeBuilder.builder("coke_oven_bricks")
                .input(cokeBrick)
                .transitional(cokeBrick)
                .result(cokeBricks)
                .filling(cokeBrick, Concrete.getFluid(100))
                .deploying(GTItems.COKE_OVEN_BRICK.asStack())
                .filling(cokeBrick, Concrete.getFluid(100))
                .deploying(GTItems.COKE_OVEN_BRICK.asStack())
                .loops(1)
                .save(provider);
        // coke_oven_bricks -> firebricks
        ItemStack firebricks = GTItems.FIRECLAY_BRICK.asStack();
        SequencedAssemblyRecipeBuilder.builder("coke_oven_bricks_to_firebricks")
                .input(cokeBricks)
                .transitional(cokeBricks)
                .result(new ItemStack(firebricks.getItem(), 2))
                .deploying(firebricks)
                .deploying(firebricks)
                .deploying(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Gypsum))
                .filling(cokeBricks, Concrete.getFluid(250))
                .loops(2)
                .save(provider);
        // 15. cement: circuit 6, calcite_dust + 4x clay_dust + 2x iron_dust + water 10000 -> cement 14400. EUt 24, dur
        // 100
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("cement"))
                .EUt(24).duration(100)
                .circuitMeta(6)
                .inputItems(dust, Calcite)
                .inputItems(dust, Clay, 4)
                .inputItems(dust, Iron, 2)
                .inputFluids(Water.getFluid(10000))
                .outputFluids(Concrete.getFluid(14400))
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("coke_oven_bricks1"))
                .EUt(12).duration(40)
                .inputItems(GTItems.COKE_OVEN_BRICK.asStack(4))
                .inputFluids(Concrete.getFluid(144))
                .outputItems(GTBlocks.CASING_COKE_BRICKS.asStack())
                .save(provider);
    }
}
