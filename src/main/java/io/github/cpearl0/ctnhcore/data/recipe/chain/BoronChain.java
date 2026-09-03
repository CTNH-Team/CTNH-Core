package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.common.data.GTRecipeTypes;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Borax;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Boron;
import static com.gregtechceu.gtceu.common.data.GTMaterials.HydrochloricAcid;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Magnesia;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Magnesium;
import static com.gregtechceu.gtceu.common.data.GTMaterials.SaltWater;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Water;
import static io.github.cpearl0.ctnhcore.data.materials.BoronChainMaterials.BORAX_ACID_SOLUTION;
import static io.github.cpearl0.ctnhcore.data.materials.BoronChainMaterials.BORON_TRIOXIDE;
import static io.github.cpearl0.ctnhcore.data.materials.NewExplosivesProductionMaterials.BORIC_ACID;

public class BoronChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        GTRecipeTypes.CHEMICAL_BATH_RECIPES.recipeBuilder(CTNHCore.id("borax_acid_leaching"))// 硼砂酸浸洗
                .inputItems(dust, Borax, 23)
                .inputFluids(HydrochloricAcid.getFluid(2000))
                .outputFluids(BORAX_ACID_SOLUTION.getFluid(1000))
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.DISTILLATION_RECIPES.recipeBuilder(CTNHCore.id("borax_acid_solution_distillation"))// 蒸馏硼砂酸溶液
                .inputFluids(BORAX_ACID_SOLUTION.getFluid(1000))
                .outputItems(dust, BORIC_ACID, 28)
                .outputFluids(SaltWater.getFluid(2000))// 盐水在下层
                .outputFluids(Water.getFluid(3000))// 水在上层
                .disableDistilleryRecipes(true)
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.DISTILLERY_RECIPES.recipeBuilder(CTNHCore.id("borax_acid_solution_distill"))// 蒸馏室蒸馏硼砂酸溶液
                .inputFluids(BORAX_ACID_SOLUTION.getFluid(1000))
                .outputItems(dust, BORIC_ACID, 28)
                .outputFluids(SaltWater.getFluid(2000))
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.BLAST_RECIPES.recipeBuilder(CTNHCore.id("boric_acid_to_boron_trioxide"))// 焙烧硼酸制三氧化二硼
                .inputItems(dust, BORIC_ACID, 14)// 2mol 硼酸(H3BO3, 7原子/mol)
                .outputItems(dust, BORON_TRIOXIDE, 5)// 1mol 三氧化二硼(B2O3, 5原子/mol)
                .outputFluids(Water.getFluid(3000))
                .blastFurnaceTemp(1000)
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.BLAST_RECIPES.recipeBuilder(CTNHCore.id("boron_trioxide_reduction"))// 镁热还原三氧化二硼
                .inputItems(dust, BORON_TRIOXIDE, 5)// 1mol 三氧化二硼(B2O3, 5原子/mol)
                .inputItems(dust, Magnesium, 3)// 3mol 镁
                .outputItems(dust, Boron, 2)// 2mol 硼
                .outputItems(dust, Magnesia, 6)// 3mol 氧化镁(MgO, 2原子/mol)
                .blastFurnaceTemp(1000)
                .EUt(30).duration(100)
                .save(provider);
    }
}
