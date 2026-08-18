package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.rareearth.ProcessControlProfile;
import io.github.cpearl0.ctnhcore.data.materials.RareEarthMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;

/**
 * 稀土产线总入口与单元素加工模板。三条互相独立、各自从矿物出发的产线：
 * 氟碳铈镧矿线（轻稀土 La/Ce/Pr/Nd，副产 HF）、独居石线（中稀土 Sm/Eu/Gd/Tb/Dy，副产磷酸钠与钍）、
 * 稀土矿粉线（重稀土 Sc/Y/Ho/Er/Tm/Yb/Lu），外加共用辅料回收闭环。
 * 每种元素都要经历粗混合物（分离/沉淀/焙烧）与提纯（复溶结晶/精制/还原）两段加工，
 * 轻稀土线最短、重稀土线最长；铕以 IV/LuV 电压与长时长单独对待。
 */
public class RareearthChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        RareearthBastnasiteChain.init(provider);
        RareearthMonaziteChain.init(provider);
        RareearthPowderChain.init(provider);
        RareearthRecoveryChain.init(provider);
    }

    /** 粗氯化物溶液 → 粗氢氧化物沉淀（粗混合物阶段）。 */
    static void addHydroxidePrecipitation(Consumer<FinishedRecipe> provider, String id, Material crudeSolution,
                                          Material crudeHydroxide, int eUt, int duration) {
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id(id + "_hydroxide_precipitation"))
                .inputFluids(crudeSolution.getFluid(2000))
                .inputItems(dust, SodiumHydroxide, 3)
                .outputItems(dust, crudeHydroxide, 2)
                .outputItems(dust, Salt, 3)
                .EUt(eUt)
                .duration(duration)
                .save(provider);
    }

    /** 粗氢氧化物焙烧脱水 → 粗氧化物（粗混合物阶段）。 */
    static void addHydroxideRoasting(Consumer<FinishedRecipe> provider, String id, Material crudeHydroxide,
                                     Material crudeOxide, int eUt, int duration) {
        CTNHRecipeTypes.OXIDATION_ROASTING.recipeBuilder(CTNHCore.id(id + "_hydroxide_roasting"))
                .inputItems(dust, crudeHydroxide, 2)
                .inputFluids(Oxygen.getFluid(3000))
                .outputItems(dust, crudeOxide, 2)
                .outputFluids(Water.getFluid(3000))
                .addData(ProcessControlProfile.PRIMARY_RECIPE_DATA, 110)
                .addData(ProcessControlProfile.SECONDARY_RECIPE_DATA, 7)
                .EUt(eUt)
                .duration(duration)
                .blastFurnaceTemp(3600)
                .save(provider);
    }

    /** 粗氧化物（或轻稀土线的粗氢氧化物）复溶重结晶 → 纯氯化物（提纯阶段）。 */
    static void addChlorideRecrystallization(Consumer<FinishedRecipe> provider, String id, Material crudeFeed,
                                             Material chloride, int eUt, int duration) {
        CTNHRecipeTypes.CRYSTALLIZER.recipeBuilder(CTNHCore.id(id + "_chloride_recrystallization"))
                .inputItems(dust, crudeFeed, 2)
                .inputFluids(HydrochloricAcid.getFluid(6000))
                .inputFluids(DistilledWater.getFluid(500))
                .outputItems(dust, chloride, 2)
                .outputFluids(Water.getFluid(3000))
                .outputFluids(DistilledWater.getFluid(500))
                .addData(ProcessControlProfile.PRIMARY_RECIPE_DATA, 27)
                .addData(ProcessControlProfile.SECONDARY_RECIPE_DATA, 122)
                .EUt(eUt)
                .duration(duration)
                .blastFurnaceTemp(1800)
                .save(provider);
    }

    /** 纯氯化物二次重结晶精制 → 高纯氯化物，析出杂质盐（提纯阶段）。 */
    static void addChlorideRefinement(Consumer<FinishedRecipe> provider, String id, Material chloride,
                                      Material refinedChloride, int eUt, int duration) {
        CTNHRecipeTypes.CRYSTALLIZER.recipeBuilder(CTNHCore.id(id + "_chloride_refinement"))
                .inputItems(dust, chloride, 2)
                .inputFluids(HydrochloricAcid.getFluid(2000))
                .inputFluids(DistilledWater.getFluid(500))
                .outputItems(dust, refinedChloride, 2)
                .outputItems(dust, Salt, 1)
                .outputFluids(DistilledWater.getFluid(500))
                .addData(ProcessControlProfile.PRIMARY_RECIPE_DATA, 27)
                .addData(ProcessControlProfile.SECONDARY_RECIPE_DATA, 122)
                .EUt(eUt)
                .duration(duration)
                .blastFurnaceTemp(1800)
                .save(provider);
    }

    /** 纯氯化物锌还原 → 稀土单质粉（提纯收尾）。 */
    static void addMetalReduction(Consumer<FinishedRecipe> provider, String id, Material chloride, Material metal,
                                  int primaryTarget, int secondaryTarget, int eUt, int duration) {
        CTNHRecipeTypes.REDUCTION_PRECIPITATION.recipeBuilder(CTNHCore.id(id + "_reduction"))
                .inputItems(dust, chloride, 2)
                .inputItems(dust, Zinc, 3)
                .inputFluids(DistilledWater.getFluid(500))
                .outputItems(dust, metal, 2)
                .outputItems(dust, RareEarthMaterials.ZINC_CHLORIDE, 3)
                .outputFluids(DistilledWater.getFluid(500))
                .addData(ProcessControlProfile.PRIMARY_RECIPE_DATA, primaryTarget)
                .addData(ProcessControlProfile.SECONDARY_RECIPE_DATA, secondaryTarget)
                .EUt(eUt)
                .duration(duration)
                .save(provider);
    }
}
