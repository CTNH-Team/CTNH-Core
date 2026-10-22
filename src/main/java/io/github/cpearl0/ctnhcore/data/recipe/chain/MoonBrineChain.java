package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;

import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.data.recipe.CustomTags;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.ctnh.ctnhastral.data.CAMaterials.MoonEnrichedBrine;
import static com.ctnh.ctnhastral.data.CAMaterials.Moonstone;
import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static io.github.cpearl0.ctnhcore.data.materials.MoonBrineMaterials.*;
import static io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials.IodizedBrine;

/** Lunar co-extraction feeds the existing halogen chain and native GT solid-state batteries. */
public class MoonBrineChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        // Keep the complete co-extraction batch in the tower, without single-output distillery shortcuts.
        DISTILLATION_RECIPES.recipeBuilder(CTNHCore.id("moon_brine_concentration"))
                .inputFluids(MoonEnrichedBrine.getFluid(32000))
                .outputFluids(DistilledWater.getFluid(24000))
                .outputFluids(CONCENTRATED_MOON_BRINE.getFluid(8000))
                .outputItems(dust, Moonstone, 4)
                .disableDistilleryRecipes(true)
                .EUt(VA[HV]).duration(400)
                .save(provider);

        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("moon_brine_fractionation"))
                .inputFluids(CONCENTRATED_MOON_BRINE.getFluid(8000))
                .outputItems(dust, Salt, 32)
                .outputFluids(LITHIUM_MOTHER_LIQUOR.getFluid(4000))
                .outputFluids(IodizedBrine.getFluid(4000))
                .EUt(VA[HV]).duration(300)
                .save(provider);

        LARGE_CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("lithium_mother_liquor_purification"))
                .inputFluids(LITHIUM_MOTHER_LIQUOR.getFluid(4000))
                .inputItems(dust, SodiumHydroxide, 12)
                .inputItems(dust, SodaAsh, 6)
                .outputFluids(PURIFIED_LITHIUM_BRINE.getFluid(4000))
                .outputItems(dust, MAGNESIUM_CALCIUM_PRECIPITATE, 15)
                .outputItems(dust, Salt, 12)
                .EUt(VA[EV]).duration(300)
                .save(provider);

        CTNHRecipeTypes.DEHYDRATOR_RECIPES.recipeBuilder(CTNHCore.id("battery_grade_lithium_chloride"))
                .inputFluids(PURIFIED_LITHIUM_BRINE.getFluid(4000))
                .outputItems(dust, BATTERY_GRADE_LITHIUM_CHLORIDE, 12)
                .outputFluids(Water.getFluid(4000))
                .EUt(VA[EV]).duration(200)
                .save(provider);

        CTNHRecipeTypes.DEHYDRATOR_RECIPES.recipeBuilder(CTNHCore.id("moon_brine_precipitate_recovery"))
                .inputItems(dust, MAGNESIUM_CALCIUM_PRECIPITATE, 15)
                .outputItems(dust, Magnesia, 4)
                .outputItems(dust, Calcite, 5)
                .outputFluids(Water.getFluid(2000))
                .EUt(VA[HV]).duration(200)
                .save(provider);

        CHEMICAL_BATH_RECIPES.recipeBuilder(CTNHCore.id("lithium_chloride_purification"))
                .inputItems(dust, LithiumChloride, 12)
                .inputFluids(DistilledWater.getFluid(4000))
                .outputFluids(PURIFIED_LITHIUM_BRINE.getFluid(4000))
                .EUt(VA[EV]).duration(300)
                .save(provider);

        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("lithium_sulfide"))
                .inputItems(dust, Lithium, 10)
                .inputItems(dust, Sulfur, 5)
                .outputItems(dust, LITHIUM_SULFIDE, 15)
                .EUt(VA[HV]).duration(300)
                .save(provider);

        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("phosphorus_pentasulfide"))
                .inputItems(dust, Phosphorus, 2)
                .inputItems(dust, Sulfur, 5)
                .outputItems(dust, PHOSPHORUS_PENTASULFIDE, 7)
                .EUt(VA[HV]).duration(300)
                .save(provider);

        // GT dust counts: 5 Li2S + P2S5 + 2 LiCl -> 2 Li6PS5Cl (26 dust).
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("sulfide_electrolyte_mixture"))
                .inputItems(dust, LITHIUM_SULFIDE, 15)
                .inputItems(dust, PHOSPHORUS_PENTASULFIDE, 7)
                .inputItems(dust, BATTERY_GRADE_LITHIUM_CHLORIDE, 4)
                .outputItems(dust, SULFIDE_ELECTROLYTE_MIXTURE, 26)
                .EUt(VA[EV]).duration(400)
                .save(provider);

        BLAST_RECIPES.recipeBuilder(CTNHCore.id("lithium_thiophosphate_chloride_electrolyte"))
                .inputItems(dust, SULFIDE_ELECTROLYTE_MIXTURE, 26)
                .inputFluids(Argon.getFluid(1000))
                .outputItems(dust, LITHIUM_THIOPHOSPHATE_CHLORIDE_ELECTROLYTE, 26)
                .outputFluids(Argon.getFluid(950))
                .blastFurnaceTemp(823)
                .EUt(VA[IV]).duration(800)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("iv_moon_solid_state_battery"))
                .inputItems(GTItems.BATTERY_HULL_MEDIUM_VANADIUM)
                .inputItems(dust, LITHIUM_THIOPHOSPHATE_CHLORIDE_ELECTROLYTE, 13)
                .inputItems(dust, Lithium, 4)
                .inputItems(dust, Vanadium, 8)
                .inputItems(plate, Graphite, 4)
                .inputItems(foil, Copper, 16)
                .inputItems(CustomTags.EV_CIRCUITS)
                .inputFluids(SiliconeRubber.getFluid(576))
                .outputItems(CTNHItems.BATTERY_IV_MOON_SOLID_STATE)
                .EUt(VA[EV]).duration(400)
                .save(provider);
    }
}
