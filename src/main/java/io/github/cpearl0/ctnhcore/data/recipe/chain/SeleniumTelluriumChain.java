package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.NewExplosivesProductionMaterials;
import io.github.cpearl0.ctnhcore.data.materials.YeastRelatedMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;

public class SeleniumTelluriumChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 1. Blue vitriol: purified_chalcopyrite_ore + nitric_acid -> blue_vitriol_solution +
        // tiny_platinum_group_sludge_dust
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("blue_vitriol"))
                .inputItems(crushedPurified, Chalcopyrite)
                .inputFluids(NitricAcid.getFluid(1000))
                .outputFluids(YeastRelatedMaterials.BLUE_VITRIOL_SOLUTION.getFluid(1000))
                .outputItems(dustTiny, PlatinumGroupSludge)
                .EUt(120).duration(200)
                .save(provider);

        // 2. Blue vitriol electrolysis: blue_vitriol_solution -> sulfuric_acid + oxygen + copper_dust +
        // chancedOutput(chalcogen_anode_mud_dust)
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("blue_vitriol1"))
                .inputFluids(YeastRelatedMaterials.BLUE_VITRIOL_SOLUTION.getFluid(1000))
                .outputFluids(SulfuricAcid.getFluid(1000))
                .outputFluids(Oxygen.getFluid(1000))
                .outputItems(dust, Copper)
                .chancedOutput(dust, NewExplosivesProductionMaterials.CHALCOGEN_ANODE_MUD, 2500, 500)
                .EUt(120).duration(200)
                .save(provider);

        // 3. Chalcogen anode mud centrifuge: chalcogen_anode_mud_dust -> silver_dust + chancedOutput(copper_dust) +
        // chancedOutput(gold_dust)
        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("chalcogen_anode_mud_bonus"))
                .inputItems(dust, NewExplosivesProductionMaterials.CHALCOGEN_ANODE_MUD)
                .outputItems(dust, Silver)
                .chancedOutput(dust, Copper, 2500, 500)
                .chancedOutput(dust, Gold, 1500, 500)
                .EUt(30).duration(200)
                .save(provider);

        // 4. Tellurium recycle: chalcogen_anode_mud_dust + soda_ash_dust + oxygen -> sodium_tellurite_dust +
        // selenium_dioxide_dust + silver_ingot + carbon_dioxide
        CTNHRecipeTypes.DEHYDRATOR_RECIPES.recipeBuilder(CTNHCore.id("tellurium_recycle"))
                .inputItems(dust, NewExplosivesProductionMaterials.CHALCOGEN_ANODE_MUD)
                .inputItems(dust, SodaAsh)
                .inputFluids(Oxygen.getFluid(1000))
                .outputItems(dust, NewExplosivesProductionMaterials.SODIUM_TELLURITE)
                .outputItems(dust, NewExplosivesProductionMaterials.SELENIUM_DIOXIDE)
                .outputItems(ingot, Silver)
                .outputFluids(CarbonDioxide.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 5. Tellurium recycle electrolysis: sodium_tellurite_dust + water -> tellurium_dioxide_dust +
        // sodium_hydroxide_dust
        ELECTROLYZER_RECIPES.recipeBuilder(CTNHCore.id("tellurium_recycle1"))
                .inputItems(dust, NewExplosivesProductionMaterials.SODIUM_TELLURITE)
                .inputFluids(Water.getFluid(1000))
                .outputItems(dust, NewExplosivesProductionMaterials.TELLURIUM_DIOXIDE)
                .outputItems(dust, SodiumHydroxide)
                .EUt(120).duration(200)
                .save(provider);

        // 6. Tellurium recycle chemical: tellurium_dioxide_dust + sulfur_dioxide + water -> tellurium_dust +
        // sulfuric_acid + sulfur_trioxide
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("tellurium_recycle2"))
                .inputItems(dust, NewExplosivesProductionMaterials.TELLURIUM_DIOXIDE)
                .inputFluids(SulfurDioxide.getFluid(1000))
                .inputFluids(Water.getFluid(1000))
                .outputItems(dust, Tellurium)
                .outputFluids(SulfuricAcid.getFluid(1000))
                .outputFluids(SulfurTrioxide.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);

        // 7. Selenium dioxide recycle: selenium_dioxide_dust + water -> selenous_acid
        MIXER_RECIPES.recipeBuilder(CTNHCore.id("selenium_dioxide_recycle"))
                .inputItems(dust, NewExplosivesProductionMaterials.SELENIUM_DIOXIDE)
                .inputFluids(Water.getFluid(1000))
                .outputFluids(NewExplosivesProductionMaterials.SELENOUS_ACID.getFluid(1000))
                .EUt(30).duration(200)
                .save(provider);

        // 8. Selenium recycle: selenous_acid + sulfur_dioxide -> selenium_dust + sulfuric_acid + sulfur_trioxide
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("selenium_dioxide_recycle1"))
                .inputFluids(NewExplosivesProductionMaterials.SELENOUS_ACID.getFluid(1000))
                .inputFluids(SulfurDioxide.getFluid(1000))
                .outputItems(dust, Selenium)
                .outputFluids(SulfuricAcid.getFluid(1000))
                .outputFluids(SulfurTrioxide.getFluid(1000))
                .EUt(120).duration(200)
                .save(provider);
    }
}
