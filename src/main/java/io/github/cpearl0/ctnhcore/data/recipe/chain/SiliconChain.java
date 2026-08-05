package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.BauxiteProcessingMaterials;
import io.github.cpearl0.ctnhcore.data.materials.NewExplosivesProductionMaterials;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.Alumina;

public class SiliconChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 从 SiliconChain.js 迁移
        // Zeolite 电解替换输出：aluminium_dust -> alumina_dust, silicon_dust -> silicon_dioxide_dust
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("silicon/zeolite_electrolysis"))
                .EUt(60).duration(288)
                .inputItems(dust, Zeolite, 19)
                .outputItems(dust, Sodium, 2)
                .outputItems(dust, Alumina, 2)
                .outputItems(dust, SiliconDioxide, 3)
                .outputFluids(Oxygen.getFluid(10000))
                .outputFluids(Water.getFluid(2000))
                .save(provider);

        // 红石离心替换输出：silicon_dust -> silicon_dioxide_dust
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("silicon/decomposition_centrifuging__redstone"))
                .EUt(30).duration(1400)
                .inputItems(dust, Redstone, 10)
                .outputItems(dust, SiliconDioxide)
                .outputItems(dust, Pyrite)
                .outputItems(dust, Ruby)
                .outputItems(dust, Mercury, 3)
                .save(provider);

        // 钙铁榴石
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("andradite"))
                .EUt(60).duration(480)
                .inputItems(dust, Andradite, 20)
                .outputItems(dust, Iron, 2)
                .outputItems(dust, Calcium, 3)
                .outputItems(dust, SiliconDioxide, 9)
                .outputFluids(Oxygen.getFluid(6000))
                .save(provider);

        // 铁辉石
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("ferrosilite"))
                .EUt(60).duration(120)
                .inputItems(dust, Ferrosilite, 5)
                .outputItems(dust, Iron)
                .outputItems(dust, SiliconDioxide, 3)
                .outputFluids(Oxygen.getFluid(1000))
                .save(provider);

        // 钙辉石
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("wollastonite"))
                .EUt(60).duration(110)
                .inputItems(dust, CTNHMaterials.Wollastonite, 5)
                .outputItems(dust, Calcium)
                .outputItems(dust, SiliconDioxide, 3)
                .outputFluids(Oxygen.getFluid(1000))
                .save(provider);

        // 黑曜石
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("obsidian"))
                .EUt(60).duration(192)
                .inputItems(dust, Obsidian, 8)
                .outputItems(dust, Iron)
                .outputItems(dust, Magnesium)
                .outputItems(dust, SiliconDioxide, 6)
                .save(provider);

        // 滑石
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("talc"))
                .EUt(60).duration(378)
                .inputItems(dust, Talc, 21)
                .outputItems(dust, Magnesium, 3)
                .outputItems(dust, SiliconDioxide, 12)
                .outputFluids(Oxygen.getFluid(4000))
                .outputFluids(Hydrogen.getFluid(2000))
                .save(provider);

        // 皂石
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("soapstone"))
                .EUt(60).duration(378)
                .inputItems(dust, Soapstone, 21)
                .outputItems(dust, Magnesium, 3)
                .outputItems(dust, SiliconDioxide, 12)
                .outputFluids(Oxygen.getFluid(4000))
                .outputFluids(Hydrogen.getFluid(2000))
                .save(provider);

        // 膨润土
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("bentonite"))
                .EUt(60).duration(480)
                .inputItems(dust, Bentonite, 30)
                .outputItems(dust, Sodium)
                .outputItems(dust, Magnesium, 6)
                .outputItems(dust, SiliconDioxide, 36)
                .outputFluids(Water.getFluid(5000))
                .outputFluids(Hydrogen.getFluid(6000))
                .save(provider);

        // 石棉粉
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("asbestos"))
                .EUt(60).duration(252)
                .inputItems(dust, Asbestos, 18)
                .outputItems(dust, Magnesium, 3)
                .outputItems(dust, SiliconDioxide, 6)
                .outputFluids(Oxygen.getFluid(5000))
                .outputFluids(Hydrogen.getFluid(4000))
                .save(provider);

        // 钙铬榴石
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("uvarovite"))
                .EUt(360).duration(480)
                .inputItems(dust, Uvarovite, 20)
                .outputItems(dust, Calcium, 3)
                .outputItems(dust, Chromium, 2)
                .outputItems(dust, SiliconDioxide, 9)
                .outputFluids(Oxygen.getFluid(6000))
                .save(provider);

        // 漂白土
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("fullers_earth"))
                .EUt(60).duration(336)
                .inputItems(dust, FullersEarth, 21)
                .outputItems(dust, Magnesium)
                .outputItems(dust, SiliconDioxide, 12)
                .outputFluids(Oxygen.getFluid(3000))
                .outputFluids(Hydrogen.getFluid(1000))
                .outputFluids(Water.getFluid(4000))
                .save(provider);

        // 二氧化硅处理
        // 碳还原二氧化硅
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("silicon_dioxide_reduction"))
                .EUt(300).duration(200)
                .inputItems(dust, SiliconDioxide, 3)
                .inputItems(dust, Carbon, 2)
                .outputItems(dust, Silicon)
                .outputFluids(CarbonMonoxide.getFluid(2000))
                .save(provider);

        // 二氧化硅氯化
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("silicon_dioxide_chloride"))
                .EUt(30).duration(40)
                .inputItems(dust, SiliconDioxide, 3)
                .inputFluids(HydrochloricAcid.getFluid(4000))
                .outputFluids(NewExplosivesProductionMaterials.SILICON_CHLORIDE.getFluid(1000))
                .outputFluids(Water.getFluid(2000))
                .save(provider);

        // 钠还原四氯化硅
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("silicon_from_sodium"))
                .EUt(30).duration(40)
                .inputItems(dust, Sodium, 4)
                .inputFluids(NewExplosivesProductionMaterials.SILICON_CHLORIDE.getFluid(1000))
                .outputItems(dust, Salt, 8)
                .outputItems(dust, Silicon)
                .save(provider);

        // 钾还原四氯化硅
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("silicon_from_potassium"))
                .EUt(30).duration(40)
                .inputItems(dust, Potassium, 4)
                .inputFluids(NewExplosivesProductionMaterials.SILICON_CHLORIDE.getFluid(1000))
                .outputItems(dust, RockSalt, 8)
                .outputItems(dust, Silicon)
                .save(provider);

        // 钠还原四氟化硅
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("silicon_from_sodium2"))
                .EUt(30).duration(40)
                .inputItems(dust, Sodium, 4)
                .inputFluids(CTNHMaterials.siliconFluoride.getFluid(1000))
                .outputItems(dust, BauxiteProcessingMaterials.SODIUM_FLUORIDE, 8)
                .outputItems(dust, Silicon)
                .save(provider);
    }
}
