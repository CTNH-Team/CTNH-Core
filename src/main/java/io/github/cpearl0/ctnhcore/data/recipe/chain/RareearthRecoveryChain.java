package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.RareEarthMaterials;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;

/**
 * 稀土辅料回收：锌/氯气/盐酸/工艺水闭环，磷酸钠水解回收氢氧化钠与磷酸，
 * 钍钙浸出渣离心回收钍（供钍基燃料线使用）与生石灰。
 */
public class RareearthRecoveryChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("zinc_chloride_recycling"))
                .inputItems(dust, RareEarthMaterials.ZINC_CHLORIDE, 3)
                .outputItems(dust, Zinc, 3)
                .outputFluids(Chlorine.getFluid(6000))
                .EUt(1920)
                .duration(480)
                .save(provider);

        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_chlor_alkali_recovery"))
                .inputItems(dust, Salt, 34)
                .inputFluids(Water.getFluid(34000))
                .outputItems(dust, SodiumHydroxide, 34)
                .outputFluids(Hydrogen.getFluid(34000))
                .outputFluids(Chlorine.getFluid(34000))
                .EUt(1920)
                .duration(1200)
                .save(provider);

        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_process_water_electrolysis"))
                .inputFluids(Water.getFluid(51000))
                .outputFluids(Hydrogen.getFluid(102000))
                .outputFluids(Oxygen.getFluid(51000))
                .EUt(1920)
                .duration(1800)
                .save(provider);

        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_hydrochloric_acid_regeneration"))
                .inputFluids(Hydrogen.getFluid(136000))
                .inputFluids(Chlorine.getFluid(136000))
                .outputFluids(HydrochloricAcid.getFluid(136000))
                .EUt(480)
                .duration(1200)
                .save(provider);

        // 磷酸钠水解：独居石副产的磷酸根回收为磷酸与氢氧化钠
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("sodium_phosphate_hydrolysis"))
                .inputItems(dust, RareEarthMaterials.SODIUM_PHOSPHATE, 2)
                .inputFluids(Water.getFluid(6000))
                .outputItems(dust, SodiumHydroxide, 6)
                .outputFluids(PhosphoricAcid.getFluid(2000))
                .EUt(480)
                .duration(600)
                .save(provider);

        // 钍钙浸出渣离心：回收钍（钍基燃料线原料）与生石灰
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("rare_earth_thorium_residue_separation"))
                .inputItems(dust, RareEarthMaterials.THORIUM_CALCIUM_RESIDUE, 2)
                .outputItems(dust, Thorium, 1)
                .outputItems(dust, Quicklime, 1)
                .EUt(480)
                .duration(300)
                .save(provider);
    }
}
