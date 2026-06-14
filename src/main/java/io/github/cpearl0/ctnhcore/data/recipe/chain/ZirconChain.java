package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.NaquadahMaterials;
import io.github.cpearl0.ctnhcore.data.materials.NewExplosivesProductionMaterials;
import io.github.cpearl0.ctnhcore.data.materials.ZrHfSeparationMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHTagPrefixes;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.Zircon;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.zirconiumTetrachloride;

public class ZirconChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 1. Barium hydroxide: barium_dust + hydrogen_peroxide -> 5x barium_hydroxide_dust
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("barium_hydroxide"))
                .inputItems(dust, Barium)
                .inputFluids(HydrogenPeroxide.getFluid(1000))
                .outputItems(dust, NewExplosivesProductionMaterials.BARIUM_HYDROXIDE, 5)
                .EUt(480).duration(125)
                .save(provider);

        // 2. Mesityl oxide: notConsumable(barium_hydroxide_dust) + acetone -> mesityl_oxide + water
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("mesityl_oxide"))
                .notConsumable(dust, NewExplosivesProductionMaterials.BARIUM_HYDROXIDE)
                .inputFluids(Acetone.getFluid(2000))
                .outputFluids(NewExplosivesProductionMaterials.MESITYL_OXIDE.getFluid(1000))
                .outputFluids(Water.getFluid(1000))
                .EUt(120).duration(100)
                .save(provider);

        // 3. Methyl isobutyl ketone: notConsumable(palladium_on_carbon) + carbon_dust + mesityl_oxide + water ->
        // methyl_isobutyl_ketone + carbon_monoxide
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("methyl_isobutyl_ketone"))
                .notConsumable(CTNHTagPrefixes.catalyst, NaquadahMaterials.PalladiumOnCarbon)
                .inputItems(dust, Carbon)
                .inputFluids(NewExplosivesProductionMaterials.MESITYL_OXIDE.getFluid(1000))
                .inputFluids(Water.getFluid(1000))
                .outputFluids(NewExplosivesProductionMaterials.METHYL_ISOBUTYL_KETONE.getFluid(1000))
                .outputFluids(CarbonMonoxide.getFluid(1000))
                .EUt(480).duration(130)
                .save(provider);

        // 4. Thiocyanic acid: sulfur_dust + hydrogen_cyanide -> thiocyanic_acid
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("thiocyanic_acid"))
                .inputItems(dust, Sulfur)
                .inputFluids(HydrogenCyanide.getFluid(1000))
                .outputFluids(NewExplosivesProductionMaterials.THIOCYANIC_ACID.getFluid(1000))
                .EUt(120).duration(100)
                .save(provider);

        // 5. Zr-Hf separation mix: thiocyanic_acid + methyl_isobutyl_ketone -> zr_hf_separation_mix
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("zr_hf_separation_mix"))
                .inputFluids(NewExplosivesProductionMaterials.THIOCYANIC_ACID.getFluid(1000))
                .inputFluids(NewExplosivesProductionMaterials.METHYL_ISOBUTYL_KETONE.getFluid(1000))
                .outputFluids(ZrHfSeparationMaterials.ZR_HF_SEPARATION_MIX.getFluid(2000))
                .EUt(120).duration(80)
                .save(provider);

        // 6. Zr-Hf chloride: 6x zircon_dust + chlorine -> zr_hf_chloride + zircon_chlorinating_residue
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("zr_hf_chloride"))
                .inputItems(dust, Zircon, 6)
                .inputFluids(Chlorine.getFluid(4000))
                .outputFluids(ZrHfSeparationMaterials.ZR_HF_CHLORIDE.getFluid(1000))
                .outputFluids(ZrHfSeparationMaterials.ZIRCON_CHLORINATING_RESIDUE.getFluid(1000))
                .EUt(120).duration(120)
                .save(provider);

        // 7. Silicon chloride: zircon_chlorinating_residue -> silicon_chloride + chancedOutput(cobalt_dust) +
        // chancedOutput(rare_earth_dust)
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("silicon_chloride"))
                .inputFluids(ZrHfSeparationMaterials.ZIRCON_CHLORINATING_RESIDUE.getFluid(1000))
                .outputFluids(NewExplosivesProductionMaterials.SILICON_CHLORIDE.getFluid(500))
                .chancedOutput(dust, Cobalt, 7500, 450)
                .chancedOutput(dust, RareEarth, 200, 20)
                .EUt(120).duration(140)
                .save(provider);

        // 8. Zr-Hf oxy chlorides: water + zr_hf_chloride -> zr_hf_oxy_chloride + hydrochloric_acid
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("zr_hf_oxy_chlorides"))
                .inputFluids(Water.getFluid(1000))
                .inputFluids(ZrHfSeparationMaterials.ZR_HF_CHLORIDE.getFluid(1000))
                .outputFluids(ZrHfSeparationMaterials.ZR_HF_OXY_CHLORIDE.getFluid(1000))
                .outputFluids(HydrochloricAcid.getFluid(2000))
                .EUt(480).duration(100)
                .save(provider);

        // 9. Cubic zirconia: hydrogen_peroxide + zr_hf_oxy_chloride + sulfur_trioxide + ammonium_chloride +
        // notConsumableFluid(zr_hf_separation_mix) -> ammonium_sulfate + hydrochloric_acid + cubic_zirconia_dust +
        // chancedOutput(hafnium_oxide_dust)
        LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("cubic_zirconia"))
                .inputFluids(HydrogenPeroxide.getFluid(6000))
                .inputFluids(ZrHfSeparationMaterials.ZR_HF_OXY_CHLORIDE.getFluid(3000))
                .inputFluids(SulfurTrioxide.getFluid(3000))
                .inputFluids(AmmoniumChloride.getFluid(6000))
                .notConsumableFluid(ZrHfSeparationMaterials.ZR_HF_SEPARATION_MIX.getFluid(1000))
                .outputFluids(NewExplosivesProductionMaterials.AMMONIUM_SULFATE.getFluid(3000))
                .outputFluids(HydrochloricAcid.getFluid(12000))
                .outputItems(dust, ZrHfSeparationMaterials.CUBIC_ZIRCONIA, 3)
                .chancedOutput(dust, ZrHfSeparationMaterials.HAFNIUM_OXIDE, 3, 1000, 0)
                .EUt(1920).duration(100)
                .save(provider);

        // 10. Zirconium tetrachloride: carbon_dust + 3x cubic_zirconia_dust + chlorine -> carbon_dioxide +
        // 5x zirconium_tetrachloride_dust
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("zirconium_tetrachloride"))
                .inputItems(dust, Carbon)
                .inputItems(dust, ZrHfSeparationMaterials.CUBIC_ZIRCONIA, 3)
                .inputFluids(Chlorine.getFluid(4000))
                .outputFluids(CarbonDioxide.getFluid(1000))
                .outputItems(dust, zirconiumTetrachloride, 5)
                .EUt(480).duration(150)
                .save(provider);

        // 11. Zirconium dust: 5x zirconium_tetrachloride_dust + 2x magnesium_dust -> zirconium_dust + 6x
        // magnesium_chloride_dust
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("zirconium_dust"))
                .inputItems(dust, zirconiumTetrachloride, 5)
                .inputItems(dust, Magnesium, 2)
                .outputItems(dust, Zirconium)
                .outputItems(dust, MagnesiumChloride, 6)
                .EUt(120).duration(300)
                .blastFurnaceTemp(2500)
                .save(provider);

        // 12. Hafnium tetrachloride: carbon_dust + 3x hafnium_oxide_dust + chlorine -> carbon_dioxide +
        // 5x hafnium_chloride_dust
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("hafnium_tetrachloride"))
                .inputItems(dust, Carbon)
                .inputItems(dust, ZrHfSeparationMaterials.HAFNIUM_OXIDE, 3)
                .inputFluids(Chlorine.getFluid(4000))
                .outputFluids(CarbonDioxide.getFluid(1000))
                .outputItems(dust, ZrHfSeparationMaterials.HAFNIUM_CHLORIDE, 5)
                .EUt(120).duration(150)
                .save(provider);

        // 13. Hafnium dust: 5x hafnium_chloride_dust + 2x magnesium_dust -> hafnium_dust + 6x magnesium_chloride_dust
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("hafnium_dust"))
                .inputItems(dust, ZrHfSeparationMaterials.HAFNIUM_CHLORIDE, 5)
                .inputItems(dust, Magnesium, 2)
                .outputItems(dust, Hafnium)
                .outputItems(dust, MagnesiumChloride, 6)
                .EUt(120).duration(300)
                .blastFurnaceTemp(2500)
                .save(provider);
    }
}
