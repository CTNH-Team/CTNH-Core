package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.moguang.ctnhmana.registry.CMMaterials.*;
import static com.moguang.ctnhmana.registry.CMRecipeTypes.TWISTED_FUSION;

public class TwistedFusionRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        TWISTED_FUSION.recipeBuilder(CTNHCore.id("twist_power_mana"))
                .inputFluids(Mana_Radiation_Mixture.getFluid(64))
                .inputFluids(Super_Plus_Mana.getFluid(32))
                .outputFluids(Twist_Power_Mana.getFluid(100))
                .EUt(491520/16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("caesium_argon"))
                .inputFluids(Arsenic.getFluid(144))
                .inputFluids(Chromium.getFluid(144))
                .outputFluids(Caesium.getFluid(144))
                .outputFluids(Argon.getFluid(1000))
                .EUt(491520/16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("carbon_iridium"))
                .inputFluids(Iodine.getFluid(144))
                .inputFluids(Chromium.getFluid(144))
                .outputFluids(Carbon.getFluid(144))
                .outputFluids(Iridium.getFluid(144))
                .EUt(491520/16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("fluorine_trinium"))
                .inputFluids(Potassium.getFluid(144))
                .inputFluids(Iron.getFluid(144))
                .outputFluids(Fluorine.getFluid(1000))
                .outputFluids(Trinium.getFluid(144))
                .EUt(1966000/16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("carbon_krypton"))
                .inputFluids(Potassium.getFluid(144))
                .inputFluids(Chromium.getFluid(144))
                .outputFluids(Carbon.getFluid(144))
                .outputFluids(Krypton.getFluid(1000))
                .EUt(491520/16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("sulfur_indium"))
                .inputFluids(Iodine.getFluid(144))
                .inputFluids(Tin.getFluid(144))
                .outputFluids(Sulfur.getFluid(144))
                .outputFluids(Indium.getFluid(144))
                .EUt(491520/16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("potassium_duranium"))
                .inputFluids(Deuterium.getFluid(1000))
                .inputFluids(Krypton.getFluid(1000))
                .outputFluids(Potassium.getFluid(144))
                .outputFluids(Duranium.getFluid(144))
                .EUt(1966000/16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("neon_fluorine"))
                .inputFluids(Iron.getFluid(144))
                .inputFluids(Nitrogen.getFluid(1000))
                .outputFluids(Neon.getFluid(1000))
                .outputFluids(Fluorine.getFluid(1000))
                .EUt(1966000/16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("tritanium_deuterium"))
                .inputFluids(Trinium.getFluid(1000))
                .inputFluids(Duranium.getFluid(144))
                .outputFluids(Tritanium.getFluid(144))
                .outputFluids(Deuterium.getFluid(1000))
                .EUt(7864320/16)
                .duration(16)
                .save(provider);

        TWISTED_FUSION.recipeBuilder(CTNHCore.id("neutronium_phosphorus"))
                .inputFluids(Nitrogen.getFluid(1000))
                .inputFluids(Platinum.getFluid(144))
                .outputFluids(Neutronium.getFluid(144))
                .outputFluids(Phosphorus.getFluid(144))
                .EUt(7864320/16)
                .duration(16)
                .save(provider);
    }

}
