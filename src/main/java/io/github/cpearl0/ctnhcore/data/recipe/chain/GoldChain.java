package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static io.github.cpearl0.ctnhcore.data.materials.GoldChainMaterials.*;
import static io.github.cpearl0.ctnhcore.data.materials.PlatinumLineMaterials.FerricSulfate;
import static io.github.cpearl0.ctnhcore.data.materials.PlatinumLineMaterials.FerrousSulfate;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.PreciousAlloy;

public class GoldChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("precious_alloy_centrifuge"))// 离心贵金属粉
                .inputItems(dust, PreciousAlloy, 4)
                .outputItems(dust, Gold, 2)
                .outputItems(dust, Copper, 1)
                .outputItems(dust, Silver, 1)
                .EUt(30).duration(200)
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("precious_alloy_cyanidation"))// 氰化贵金属粉
                .inputItems(dust, PreciousAlloy, 4)
                .inputFluids(SODIUM_CYANIDE.getFluid(8000))
                .inputFluids(Oxygen.getFluid(2000))
                .inputFluids(Water.getFluid(2000))
                .outputFluids(PRECIOUS_METAL_CYANO_COMPLEX.getFluid(4000))
                .outputItems(dust, CYANIDE_TAILINGS, 1)
                .EUt(30).duration(200).tier(MV)
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("gold_mud_precipitation"))// 置换金泥
                .inputFluids(PRECIOUS_METAL_CYANO_COMPLEX.getFluid(4000))
                .inputItems(dust, Zinc, 2)
                .outputFluids(ZINC_CYANIDE_COMPLEX.getFluid(2000))
                .outputItems(dust, GOLD_MUD, 4)
                .EUt(30).duration(200).tier(MV)
                .save(provider);

        GTRecipeTypes.EXTRACTOR_RECIPES.recipeBuilder(CTNHCore.id("gold_mud_melting"))// 熔化金泥
                .inputItems(dust, GOLD_MUD, 1)
                .outputFluids(GOLD_MUD.getFluid(144))
                .EUt(30).duration(200)
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("gold_mud_chlorination"))// 金泥吹氯
                .inputFluids(GOLD_MUD.getFluid(576))
                .inputFluids(Chlorine.getFluid(3000))
                .outputFluids(Gold.getFluid(576))
                .outputFluids(COPPER_CHLORIDE.getFluid(1000))
                .outputItems(dust, SILVER_CHLORIDE, 2)
                .EUt(30).duration(100).tier(MV)
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("copper_from_copper_chloride"))// 铁粉置换氯化铜
                .inputFluids(COPPER_CHLORIDE.getFluid(1000))
                .inputItems(dust, Iron, 1)
                .outputItems(dust, Copper, 1)
                .outputFluids(Iron2Chloride.getFluid(1000))
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("gold_mud_aqua_regia"))// 王水溶金泥
                .inputItems(dust, GOLD_MUD, 2)
                .inputFluids(AquaRegia.getFluid(7000))
                .outputFluids(CHLOROAURIC_ACID.getFluid(2000))
                .outputItems(dust, SILVER_CHLORIDE, 3)
                .outputFluids(NitrogenDioxide.getFluid(7000))
                .outputFluids(DilutedHydrochloricAcid.getFluid(5000))
                .EUt(30).duration(100).tier(HV)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("gold_mud_electrolysis"))// 电解金泥
                .inputItems(dust, GOLD_MUD, 1)
                .inputItems(plate, Gold, 1)
                .notConsumableFluid(CHLOROAURIC_ACID.getFluid(1000))
                .outputItems(dust, Gold, 1)
                .outputItems(plateDouble, Gold, 1)
                .EUt(240).duration(200)
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("gold_from_ferrous_sulfate"))// 硫化亚铁还原氯金酸
                .inputFluids(CHLOROAURIC_ACID.getFluid(2000))
                .inputFluids(FerrousSulfate.getFluid(3000))
                .outputItems(dust, Gold, 2)
                .outputFluids(FerricSulfate.getFluid(3000))
                .outputFluids(HydrochloricAcid.getFluid(8000))
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("hydrogen_cyanide_from_methane"))// 合成氢氰酸
                .inputFluids(Methane.getFluid(1000))
                .inputFluids(Ammonia.getFluid(1000))
                .outputFluids(HydrogenCyanide.getFluid(1000))
                .outputFluids(Hydrogen.getFluid(6000))
                .EUt(240).duration(200).tier(HV)
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("sodium_cyanide_from_hydrogen_cyanide"))// 制取氰化钠
                .inputFluids(HydrogenCyanide.getFluid(1000))
                .inputItems(dust, SodiumHydroxide, 3)
                .outputFluids(SODIUM_CYANIDE.getFluid(1000))
                .EUt(30).duration(100).tier(MV)
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("zinc_cyanide_complex_sulfidation"))// 回收氰化钠
                .inputFluids(ZINC_CYANIDE_COMPLEX.getFluid(1000))
                .inputFluids(HydrogenSulfide.getFluid(1000))
                .outputItems(dust, ZincSulfide, 1)
                .outputFluids(SODIUM_CYANIDE.getFluid(4000))
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.BLAST_RECIPES.recipeBuilder(CTNHCore.id("zinc_sulfide_reduction"))// 回收锌
                .inputItems(dust, ZincSulfide, 1)
                .inputFluids(Hydrogen.getFluid(2000))
                .outputItems(dust, Zinc, 1)
                .outputFluids(HydrogenSulfide.getFluid(1000))
                .blastFurnaceTemp(1200)
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.SIFTER_RECIPES.recipeBuilder(CTNHCore.id("cyanide_tailings_sifting"))// 筛选氰化尾渣
                .inputItems(dust, CYANIDE_TAILINGS, 1)
                .outputItems(dust, Copper, 1)
                .chancedOutput(dust, Lead, 1500, 500)
                .chancedOutput(dust, Iron, 1200, 400)
                .chancedOutput(dust, Gallium, 1200, 400)
                .chancedOutput(dust, Nickel, 1000, 300)
                .chancedOutput(dust, Silver, 800, 200)
                .chancedOutput(dustSmall, Platinum, 100, 100)
                .EUt(30).duration(20)
                .save(provider);

        VanillaRecipeHelper.addSmeltingRecipe(provider, CTNHCore.id("precious_alloy_ingot_smelting"),
                ChemicalHelper.get(ingot, PreciousAlloy),
                ChemicalHelper.get(nugget, Gold, 3),
                0.5f);
    }
}
