package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.YeastRelatedMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Prefix;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.Objects;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dustSmall;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dustTiny;
import static com.gregtechceu.gtceu.common.data.GTItems.BIO_CHAFF;
import static com.gregtechceu.gtceu.common.data.GTItems.FERTILIZER;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.CENTRIFUGE_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.CHEMICAL_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MACERATOR_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MIXER_RECIPES;
import static io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes.BIO_REACTOR;
import static io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes.DIFFERENTIAL_CENTRIFUGE_RECIPES;
import static io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes.DIGESTING;
import static io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes.FERMENTING;
import static io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes.ULTRASONICATION_RECIPES;

@Prefix("recipe.biochemistry")
public class BioChemistryRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        DIGESTING.recipeBuilder(CTNHCore.id("bio_chaff_digestion"))
                .EUt(30)
                .inputItems(BIO_CHAFF)
                .inputFluids(GTMaterials.Water.getFluid(1000))
                .outputFluids(GTMaterials.Biomass.getFluid(1000))
                .circuitMeta(0)
                .duration(200)
                .save(provider);

        DIGESTING.recipeBuilder(CTNHCore.id("bio_chaff_digestion2"))
                .EUt(120)
                .inputItems(BIO_CHAFF)
                .outputItems(FERTILIZER)
                .inputFluids(GTMaterials.Water.getFluid(800))
                .outputFluids(GTMaterials.FermentedBiomass.getFluid(800))
                .circuitMeta(1)
                .duration(200)
                .save(provider);

        DIGESTING.recipeBuilder(CTNHCore.id("animal_excreta_digestion"))
                .EUt(30)
                .inputItems(CTNHItems.ANIMAL_EXCRETA)
                .inputFluids(GTMaterials.Water.getFluid(1000))
                .outputFluids(GTMaterials.Biomass.getFluid(1000))
                .circuitMeta(0)
                .duration(200)
                .save(provider);

        DIGESTING.recipeBuilder(CTNHCore.id("animal_excreta_digestion2"))
                .EUt(120)
                .inputItems(CTNHItems.ANIMAL_EXCRETA)
                .outputItems(FERTILIZER)
                .inputFluids(GTMaterials.Water.getFluid(800))
                .outputFluids(GTMaterials.FermentedBiomass.getFluid(800))
                .circuitMeta(1)
                .duration(200)
                .save(provider);

        FERMENTING.recipeBuilder(CTNHCore.id("fermented"))
                .EUt(96)
                .inputFluids(GTMaterials.Biomass.getFluid(1000))
                .outputFluids(GTMaterials.FermentedBiomass.getFluid(1500))
                .inputItems(dust, YeastRelatedMaterials.NORMAL_YEAST, 2)
                .duration(200)
                .blastFurnaceTemp(1500)
                .save(provider);

        MACERATOR_RECIPES.recipeBuilder(CTNHCore.id("lyase"))
                .EUt(30)
                .inputItems(Blocks.SHROOMLIGHT.asItem())
                .outputItems(dust, YeastRelatedMaterials.LIGNIN, 4)
                .chancedOutput(dustTiny, YeastRelatedMaterials.LYASE, 1000, 500)
                .duration(200)
                .save(provider);

        MACERATOR_RECIPES.recipeBuilder(CTNHCore.id("lignin"))
                .EUt(30)
                .inputItems(Objects.requireNonNull(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("farmersdelight:tree_bark"))))
                .outputItems(dust, GTMaterials.Wood, 2)
                .chancedOutput(ChemicalHelper.get(dust, YeastRelatedMaterials.LIGNIN, 2), 7500, 500)
                .chancedOutput(ChemicalHelper.get(dust, YeastRelatedMaterials.LIGNIN), 5000, 0)
                .chancedOutput(ChemicalHelper.get(dust, YeastRelatedMaterials.LIGNIN), 1000, 1000)
                .duration(40)
                .save(provider);

        CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("lignin2"))
                .EUt(30)
                .inputItems(dust, GTMaterials.Wood)
                .outputItems(dust, YeastRelatedMaterials.CELLULOSE)
                .chancedOutput(dust, YeastRelatedMaterials.LIGNIN, 5000, 250)
                .duration(40)
                .save(provider);

        MACERATOR_RECIPES.recipeBuilder(CTNHCore.id("podzol"))
                .EUt(30)
                .duration(40)
                .inputItems(Blocks.PODZOL.asItem())
                .outputItems(dust, YeastRelatedMaterials.DIRT, 4)
                .chancedOutput(dust, YeastRelatedMaterials.RHIZOBIUM, 500, 250)
                .save(provider);

        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("trisodium_phosphate"))
                .inputItems(dust, GTMaterials.TricalciumPhosphate, 5)
                .inputItems(dust, GTMaterials.Salt, 12)
                .outputItems(dust, GTMaterials.CalciumChloride, 9)
                .outputItems(dust, CTNHMaterials.TrisodiumPhosphate, 8)
                .EUt(30)
                .duration(60)
                .save(provider);

        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("sodium_dihydrogen_phosphate_dust"))
                .inputItems(dust, CTNHMaterials.TrisodiumPhosphate, 4)
                .inputFluids(GTMaterials.Water.getFluid(2000))
                .outputItems(dust, YeastRelatedMaterials.SODIUM_DIHYDROGEN_PHOSPHATE, 4)
                .outputItems(dust, GTMaterials.SodiumBicarbonate, 12)
                .circuitMeta(0)
                .EUt(30)
                .duration(100)
                .save(provider);

        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("dibasic_sodium_phosphate_dust"))
                .inputItems(dust, CTNHMaterials.TrisodiumPhosphate, 4)
                .inputFluids(GTMaterials.Water.getFluid(1000))
                .outputItems(dust, YeastRelatedMaterials.DIBASIC_SODIUM_PHOSPHATE, 4)
                .outputItems(dust, GTMaterials.SodiumHydroxide, 3)
                .circuitMeta(1)
                .EUt(30)
                .duration(100)
                .save(provider);

        MIXER_RECIPES.recipeBuilder(CTNHCore.id("carbonate_buffer"))
                .circuitMeta(0)
                .EUt(30)
                .duration(120)
                .inputItems(dust, GTMaterials.SodaAsh, 6)
                .inputItems(dust, GTMaterials.SodiumBicarbonate, 12)
                .inputFluids(GTMaterials.Water.getFluid(2000))
                .outputFluids(YeastRelatedMaterials.CARBONATE_BUFFER.getFluid(2000))
                .save(provider);

        MIXER_RECIPES.recipeBuilder(CTNHCore.id("phosphate_buffer"))
                .EUt(30)
                .duration(120)
                .inputItems(dust, YeastRelatedMaterials.SODIUM_DIHYDROGEN_PHOSPHATE, 9)
                .inputItems(dust, YeastRelatedMaterials.DIBASIC_SODIUM_PHOSPHATE, 9)
                .inputFluids(GTMaterials.Water.getFluid(2000))
                .outputFluids(YeastRelatedMaterials.PHOSPHATE_BUFFER.getFluid(2000))
                .save(provider);

        ULTRASONICATION_RECIPES.recipeBuilder(CTNHCore.id("rhizobium"))
                .inputItems(dust, YeastRelatedMaterials.RHIZOBIUM)
                .inputFluids(YeastRelatedMaterials.CARBONATE_BUFFER.getFluid(1000))
                .outputFluids(YeastRelatedMaterials.RHIZOBIUM_EXTRACT.getFluid(1000))
                .EUt(480)
                .duration(400)
                .save(provider);

        DIFFERENTIAL_CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("azotase"))
                .inputFluids(YeastRelatedMaterials.RHIZOBIUM_EXTRACT.getFluid(1000))
                .outputItems(dust, YeastRelatedMaterials.AZOTASE)
                .outputFluids(YeastRelatedMaterials.AMINO_ACID.getFluid(1000))
                .EUt(1920)
                .duration(400)
                .save(provider);

        BIO_REACTOR.recipeBuilder(CTNHCore.id("ammonia"))
                .inputItems(dust, YeastRelatedMaterials.AZOTASE)
                .inputItems(dustSmall, GTMaterials.Molybdenum)
                .inputFluids(GTMaterials.Nitrogen.getFluid(10000))
                .inputFluids(GTMaterials.Hydrogen.getFluid(30000))
                .outputFluids(GTMaterials.Ammonia.getFluid(10000))
                .EUt(1920)
                .duration(160)
                .save(provider);

        MIXER_RECIPES.recipeBuilder(CTNHCore.id("radiation_mutated_yeast"))
                .inputItems(dust, YeastRelatedMaterials.POLLUTED_FLUORESCENCE_YEAST)
                .inputItems(dust, GTMaterials.Mendelevium)
                .inputFluids(GTMaterials.UraniumHexafluoride.getFluid(1000))
                .chancedOutput(ChemicalHelper.get(dust, YeastRelatedMaterials.RADIATION_MUTATED_YEAST), 200, 50)
                .EUt(1920)
                .duration(300)
                .save(provider);

        DIFFERENTIAL_CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("thermoduric_bacteria"))
                .inputFluids(CTNHMaterials.MysteryFluid.getFluid(1000))
                .outputItems(dust, YeastRelatedMaterials.THERMODURIC_BACTERIA)
                .outputFluids(GTMaterials.Biomass.getFluid(800))
                .EUt(480)
                .duration(160)
                .save(provider);

        ULTRASONICATION_RECIPES.recipeBuilder(CTNHCore.id("thermoduric_bacteria_extract"))
                .inputItems(dust, YeastRelatedMaterials.THERMODURIC_BACTERIA)
                .inputFluids(YeastRelatedMaterials.PHOSPHATE_BUFFER.getFluid(1000))
                .outputFluids(YeastRelatedMaterials.THERMODURIC_BACTERIA_EXTRACT.getFluid(1000))
                .EUt(1920)
                .duration(80)
                .save(provider);

        DIFFERENTIAL_CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("taq_enzyme"))
                .inputFluids(YeastRelatedMaterials.THERMODURIC_BACTERIA_EXTRACT.getFluid(1000))
                .outputItems(dust, YeastRelatedMaterials.TAQ_ENZYME)
                .outputFluids(YeastRelatedMaterials.AMINO_ACID.getFluid(1000))
                .EUt(7680)
                .duration(400)
                .save(provider);

        ULTRASONICATION_RECIPES.recipeBuilder(CTNHCore.id("escherichia_coli_extract"))
                .inputItems(dust, YeastRelatedMaterials.ESCHERICHIA_COLI)
                .inputFluids(YeastRelatedMaterials.CARBONATE_BUFFER.getFluid(1000))
                .outputFluids(YeastRelatedMaterials.ESCHERICHIA_COLI_EXTRACT.getFluid(1000))
                .EUt(120)
                .duration(80)
                .save(provider);

        DIFFERENTIAL_CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id("cellulase"))
                .inputFluids(YeastRelatedMaterials.ESCHERICHIA_COLI_EXTRACT.getFluid(1000))
                .outputItems(dust, YeastRelatedMaterials.CELLULASE)
                .outputFluids(YeastRelatedMaterials.AMINO_ACID.getFluid(1000))
                .EUt(480)
                .duration(100)
                .save(provider);

        BIO_REACTOR.recipeBuilder(CTNHCore.id("sugar"))
                .inputItems(dust, YeastRelatedMaterials.CELLULOSE)
                .chancedInput(ChemicalHelper.get(dust, YeastRelatedMaterials.CELLULASE), 500, 0)
                .outputItems(Items.SUGAR, 4)
                .EUt(120)
                .duration(80)
                .save(provider);

        MIXER_RECIPES.recipeBuilder(CTNHCore.id("simple_growth_medium"))
                .inputFluids(YeastRelatedMaterials.AMINO_ACID.getFluid(1000))
                .inputFluids(YeastRelatedMaterials.NORMAL_YEAST_EXTRACT_LIQUID.getFluid(500))
                .inputItems(dust, GTMaterials.Salt)
                .outputFluids(CTNHMaterials.SimpleGrowthMedium.getFluid(1500))
                .EUt(30)
                .duration(60)
                .save(provider);

        MACERATOR_RECIPES.recipeBuilder(CTNHCore.id("meat"))
                .inputItems(Objects.requireNonNull(
                        ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("biomesoplenty:flesh"))))
                .outputItems(dust, GTMaterials.Meat, 3)
                .chancedOutput(dust, GTMaterials.Meat, 4500, 500)
                .chancedOutput(dust, GTMaterials.Meat, 1500, 200)
                .EUt(30)
                .duration(100)
                .save(provider);

        var food = Items.BREAD.getDefaultInstance();
        food.setHoverName(food_info.translate());
        DIGESTING.recipeBuilder(CTNHCore.id("biomass_digestion1"))
                .addData("special", "food")
                .EUt(30)
                .inputItems(food)
                .circuitMeta(0)
                .duration(100)
                .inputFluids(GTMaterials.Water.getFluid(100))
                .outputFluids(GTMaterials.Biomass.getFluid(100))
                .save(provider);
        DIGESTING.recipeBuilder(CTNHCore.id("biomass_digestion2"))
                .addData("special", "food")
                .EUt(120)
                .inputItems(food)
                .circuitMeta(0)
                .duration(100)
                .inputFluids(GTMaterials.Water.getFluid(75))
                .outputFluids(GTMaterials.Biomass.getFluid(75))
                .chancedOutput(dust, YeastRelatedMaterials.ESCHERICHIA_COLI, 500, 500)
                .save(provider);

        ItemStack fluorescenceYeast = ChemicalHelper.get(dust, YeastRelatedMaterials.FLUORESCENCE_YEAST);
        ItemStack pollutedFluorescenceYeast = ChemicalHelper.get(dust,
                YeastRelatedMaterials.POLLUTED_FLUORESCENCE_YEAST);

        // SequencedAssemblyRecipeBuilder.builder("polluted_fluorescence_yeast")
        // .input(fluorescenceYeast)
        // .transitional(fluorescenceYeast)
        // .result(pollutedFluorescenceYeast)
        // .filling(fluorescenceYeast, "alexscaves:acid")
        // .filling(fluorescenceYeast, "deep_aether:poison_fluid")
        // .pressing()
        // .loops(1)
        // .save(provider);
    }

    @CN("任意食物")
    @EN("Any food")
    public static Lang food_info;
    @CN("实际产出与食物的饱食度有关")
    @EN("The Exact Output is related to the food's nutrition and saturation")
    public static Lang digestion_info;
}
