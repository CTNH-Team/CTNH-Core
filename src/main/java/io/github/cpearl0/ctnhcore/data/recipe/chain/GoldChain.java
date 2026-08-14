package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static io.github.cpearl0.ctnhcore.data.materials.CrudeGoldRefiningMaterials.*;
import static io.github.cpearl0.ctnhcore.data.materials.PlatinumLineMaterials.FerricSulfate;
import static io.github.cpearl0.ctnhcore.data.materials.PlatinumLineMaterials.FerrousSulfate;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.PreciousAlloy;

public class GoldChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("precious_alloy_centrifuge"))
                .inputItems(dust, PreciousAlloy, 4)
                .outputItems(dust, Gold, 2)
                .outputItems(dust, Copper, 1)
                .outputItems(dust, Silver, 1)
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("precious_alloy_centrifuge_tiny"))
                .inputItems(dust, PreciousAlloy, 1)
                .outputItems(dustTiny, Gold, 6)
                .outputItems(dustTiny, Copper, 2)
                .outputItems(dustTiny, Silver, 1)
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("precious_alloy_cyanidation"))
                .inputItems(dust, PreciousAlloy, 4)
                .inputFluids(SODIUM_CYANIDE.getFluid(8000))
                .inputFluids(Oxygen.getFluid(2000))
                .inputFluids(Water.getFluid(2000))
                .outputFluids(PRECIOUS_METAL_CYANO_COMPLEX.getFluid(4000))
                .outputItems(dust, CYANIDE_TAILINGS, 1)
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("gold_mud_precipitation"))
                .inputFluids(PRECIOUS_METAL_CYANO_COMPLEX.getFluid(4000))
                .inputItems(dust, Zinc, 2)
                .outputFluids(ZINC_CYANIDE_COMPLEX.getFluid(2000))
                .outputItems(dust, GOLD_MUD, 4)
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.EXTRACTOR_RECIPES.recipeBuilder(CTNHCore.id("gold_mud_melting"))
                .inputItems(dust, GOLD_MUD, 1)
                .outputFluids(GOLD_MUD.getFluid(144))
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("gold_mud_chlorination"))
                .inputFluids(GOLD_MUD.getFluid(576))
                .inputFluids(Chlorine.getFluid(3000))
                .outputFluids(Gold.getFluid(576))
                .outputItems(dust, COPPER_CHLORIDE, 3)
                .outputItems(dust, SILVER_CHLORIDE, 2)
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("gold_mud_aqua_regia"))
                .inputItems(dust, GOLD_MUD, 2)
                .inputFluids(AquaRegia.getFluid(7000))
                .outputFluids(CHLOROAURIC_ACID.getFluid(2000))
                .outputItems(dust, SILVER_CHLORIDE, 3)
                .outputFluids(NitrogenDioxide.getFluid(7000))
                .outputFluids(DilutedHydrochloricAcid.getFluid(5000))
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("gold_mud_electrolysis"))
                .inputItems(dust, GOLD_MUD, 1)
                .inputItems(plate, Gold, 1)
                .notConsumableFluid(CHLOROAURIC_ACID.getFluid(1000))
                .outputItems(dust, Gold, 1)
                .outputItems(plateDouble, Gold, 1)
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("gold_from_ferrous_sulfate"))
                .inputFluids(CHLOROAURIC_ACID.getFluid(2000))
                .inputFluids(FerrousSulfate.getFluid(3000))
                .outputItems(dust, Gold, 2)
                .outputFluids(FerricSulfate.getFluid(3000))
                .outputFluids(HydrochloricAcid.getFluid(8000))
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("hydrogen_cyanide_from_methane"))
                .inputFluids(Methane.getFluid(1000))
                .inputFluids(Ammonia.getFluid(1000))
                .outputFluids(HydrogenCyanide.getFluid(1000))
                .outputFluids(Hydrogen.getFluid(6000))
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("zinc_cyanide_complex_sulfidation"))
                .inputFluids(ZINC_CYANIDE_COMPLEX.getFluid(1000))
                .inputFluids(HydrogenSulfide.getFluid(1000))
                .outputItems(dust, ZincSulfide, 1)
                .outputFluids(SODIUM_CYANIDE.getFluid(4000))
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.BLAST_RECIPES.recipeBuilder(CTNHCore.id("zinc_sulfide_reduction"))
                .inputItems(dust, ZincSulfide, 1)
                .inputFluids(Hydrogen.getFluid(2000))
                .outputItems(dust, Zinc, 1)
                .outputFluids(HydrogenSulfide.getFluid(1000))
                .blastFurnaceTemp(1200)
                .EUt(30).duration(100)
                .save(provider);

        GTRecipeTypes.SIFTER_RECIPES.recipeBuilder(CTNHCore.id("cyanide_tailings_sifting"))
                .inputItems(dust, CYANIDE_TAILINGS, 4)
                .outputItems(dust, Copper, 1)
                .chancedOutput(dust, Lead, 1500, 500)
                .chancedOutput(dust, Iron, 1200, 400)
                .chancedOutput(dust, Gallium, 1200, 400)
                .chancedOutput(dust, Nickel, 1000, 300)
                .chancedOutput(dust, Silver, 800, 200)
                .EUt(30).duration(80)
                .save(provider);

        VanillaRecipeHelper.addSmeltingRecipe(provider, CTNHCore.id("precious_alloy_ingot_smelting"),
                ChemicalHelper.get(ingot, PreciousAlloy),
                ChemicalHelper.get(nugget, Gold, 3),
                0.5f);
    }
}
