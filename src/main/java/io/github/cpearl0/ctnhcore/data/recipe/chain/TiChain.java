package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.BauxiteProcessingMaterials;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.ingotHot;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.VanadiumPentoxide;

public class TiChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 1. Distill titanium tetrachloride: titanium_tetrachloride 3000 -> gallium_dust + iron_iii_chloride 1000 +
        // titanium_tetrachloride 1000 + refining_titanium_tetrachloride 1250
        DISTILLATION_RECIPES.recipeBuilder(CTNHCore.id("refining_titanium_tetrachloride_bucket"))
                .inputFluids(TitaniumTetrachloride.getFluid(3000))
                .outputItems(dust, Gallium, 3)
                .outputFluids(Iron3Chloride.getFluid(1000))
                .outputFluids(TitaniumTetrachloride.getFluid(1000))
                .outputFluids(BauxiteProcessingMaterials.REFINING_TITANIUM_TETRACHLORIDE.getFluid(1250))
                .EUt(120)
                .duration(100)
                .save(provider);

        // 2. Remove vanadium: titanium_tetrachloride_v 6000 + water 9000 + 2x aluminium_dust -> 8x
        // aluminium_chloride_dust + 21x vanadium_pentoxide_dust + hydrochloric_acid 18000 + titanium_tetrachloride 6000
        LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("vanadium_pentoxide_dust"))
                .inputFluids(BauxiteProcessingMaterials.TITANIUM_TETRACHLORIDE_V.getFluid(6000))
                .inputFluids(Water.getFluid(9000))
                .inputItems(dust, Aluminium, 2)
                .outputItems(dust, BauxiteProcessingMaterials.ALUMINIUM_CHLORIDE, 8)
                .outputItems(dust, VanadiumPentoxide, 21)
                .outputFluids(HydrochloricAcid.getFluid(18000))
                .outputFluids(TitaniumTetrachloride.getFluid(6000))
                .EUt(120)
                .duration(150)
                .save(provider);

        // 3. Synthesize vanadium-containing TiCl4: chlorine 48000 + 6x rutile_dust + 12x carbon_dust -> carbon_monoxide
        // 12000 + titanium_tetrachloride_v 6000
        LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("ticl4"))
                .inputFluids(Chlorine.getFluid(48000))
                .inputItems(dust, Rutile, 6)
                .inputItems(dust, Carbon, 12)
                .outputFluids(CarbonMonoxide.getFluid(12000))
                .outputFluids(BauxiteProcessingMaterials.TITANIUM_TETRACHLORIDE_V.getFluid(6000))
                .EUt(480)
                .duration(120)
                .save(provider);

        // 4. Convert high-purity TiCl4 to titanium ingot: refining_titanium_tetrachloride 5000 + 10x magnesium_dust ->
        // 5x hot_titanium_ingot + 30x magnesium_chloride_dust
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("titanium_ingot"))
                .inputFluids(BauxiteProcessingMaterials.REFINING_TITANIUM_TETRACHLORIDE.getFluid(5000))
                .inputItems(dust, Magnesium, 10)
                .outputItems(ChemicalHelper.get(ingotHot, Titanium, 5))
                .outputItems(dust, MagnesiumChloride, 30)
                .EUt(480)
                .duration(150)
                .blastFurnaceTemp(2200)
                .save(provider);
    }
}
