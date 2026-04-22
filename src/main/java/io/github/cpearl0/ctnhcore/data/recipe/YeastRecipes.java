package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.YeastRelatedMaterials;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;

import teamrazor.deepaether.init.DABlocks;
import twilightforest.init.TFBlocks;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.CENTRIFUGE_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MACERATOR_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MIXER_RECIPES;
import static io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes.FERMENTING;

public class YeastRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        for (YeastDefinition yeast : YEASTS) {
            for (Nutrition nutrition : NUTRITIONS) {
                registerFermenting(provider, yeast, nutrition);
            }

            MIXER_RECIPES.recipeBuilder(CTNHCore.id(yeast.name() + "_yeast1"))
                    .EUt(30)
                    .inputItems(yeast.dustMaterial().get(), 1)
                    .inputFluids(GTMaterials.Water.getFluid(1000))
                    .outputFluids(yeast.seedLiquidMaterial().get().getFluid(1000))
                    .duration(40)
                    .save(provider);

            MIXER_RECIPES.recipeBuilder(CTNHCore.id(yeast.name() + "_yeast2"))
                    .EUt(30)
                    .inputFluids(yeast.liquidMaterial().get().getFluid(1000))
                    .notConsumable(ChemicalHelper.get(TagPrefix.dust, YeastRelatedMaterials.LYASE))
                    .outputFluids(yeast.extractLiquidMaterial().get().getFluid(1000))
                    .duration(40)
                    .save(provider);

            CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id(yeast.name() + "_yeast3"))
                    .EUt(30)
                    .inputFluids(yeast.liquidMaterial().get().getFluid(500))
                    .outputFluids(YeastRelatedMaterials.WASTE_NUTRITION_LIQUID.getFluid(500))
                    .outputItems(ChemicalHelper.get(TagPrefix.dust, yeast.dustMaterial().get(), 2))
                    .duration(40)
                    .save(provider);

            MIXER_RECIPES.recipeBuilder(CTNHCore.id(yeast.name() + "_yeast4"))
                    .EUt(30)
                    .inputFluids(yeast.liquidMaterial().get().getFluid(500))
                    .inputFluids(GTMaterials.Water.getFluid(1500))
                    .outputFluids(yeast.seedLiquidMaterial().get().getFluid(2000))
                    .duration(40)
                    .save(provider);

            var splitBuilder = CENTRIFUGE_RECIPES.recipeBuilder(CTNHCore.id(yeast.name() + "_yeast5"))
                    .EUt(120)
                    .inputFluids(yeast.extractLiquidMaterial().get().getFluid(1000))
                    .duration(100);
            for (FluidStack output : yeast.outputs()) {
                splitBuilder.outputFluids(output);
            }
            splitBuilder.save(provider);

            if (yeast.rawMaterial() != null) {
                MACERATOR_RECIPES.recipeBuilder(CTNHCore.id(yeast.name() + "_yeast6"))
                        .EUt(120)
                        .inputItems(yeast.rawMaterial())
                        .outputItems(ChemicalHelper.get(TagPrefix.dust, yeast.dustMaterial().get(), 2))
                        .duration(40)
                        .save(provider);
            }
        }
        MACERATOR_RECIPES.recipeBuilder(CTNHCore.id("normal_yeast_from_red_mushroom"))
                .EUt(120)
                .inputItems(Items.RED_MUSHROOM)
                .outputItems(ChemicalHelper.get(TagPrefix.dust, YeastRelatedMaterials.NORMAL_YEAST, 2))
                .duration(40)
                .save(provider);
    }

    private static void registerFermenting(Consumer<FinishedRecipe> provider, YeastDefinition yeast,
                                           Nutrition nutrition) {
        var recipe = FERMENTING.recipeBuilder(CTNHCore.id(yeast.name() + "_yeast0_" + nutrition.suffix()))
                .EUt(yeast.eu())
                .inputFluids(yeast.seedLiquidMaterial().get().getFluid(500))
                .outputFluids(yeast.liquidMaterial().get().getFluid(500))
                .inputItems(nutrition.stack().copy())
                .inputItems(ChemicalHelper.get(TagPrefix.dustSmall, GTMaterials.Salt));

        if (yeast.requiresAmmoniumChloride()) {
            recipe.inputItems(ChemicalHelper.get(TagPrefix.dustSmall, GTMaterials.AmmoniumChloride));
        }

        recipe.blastFurnaceTemp(yeast.temperature())
                .duration(yeast.duration())
                .save(provider);
    }

    private record Nutrition(ItemStack stack, String suffix) {}

    private record YeastDefinition(
                                   String name,
                                   ItemStack rawMaterial,
                                   int temperature,
                                   int eu,
                                   int duration,
                                   boolean requiresAmmoniumChloride,
                                   Supplier<Material> dustMaterial,
                                   Supplier<Material> seedLiquidMaterial,
                                   Supplier<Material> liquidMaterial,
                                   Supplier<Material> extractLiquidMaterial,
                                   List<FluidStack> outputs) {}

    private static final List<Nutrition> NUTRITIONS = List.of(
            new Nutrition(new ItemStack(Items.SUGAR), "sugar"),
            new Nutrition(ChemicalHelper.get(TagPrefix.dust, YeastRelatedMaterials.LIGNIN, 3), "lignin_dust"));

    private static final List<YeastDefinition> YEASTS = List.of(
            new YeastDefinition(
                    "normal",
                    Items.BROWN_MUSHROOM.getDefaultInstance(),
                    1000,
                    96,
                    100,
                    false,
                    () -> YeastRelatedMaterials.NORMAL_YEAST,
                    () -> YeastRelatedMaterials.NORMAL_YEAST_SEED_LIQUID,
                    () -> YeastRelatedMaterials.NORMAL_YEAST_LIQUID,
                    () -> YeastRelatedMaterials.NORMAL_YEAST_EXTRACT_LIQUID,
                    List.of(
                            GTMaterials.Ethanol.getFluid(500),
                            GTMaterials.AceticAcid.getFluid(250),
                            GTMaterials.Methane.getFluid(250))),
            new YeastDefinition(
                    "crimson",
                    Items.CRIMSON_FUNGUS.getDefaultInstance(),
                    1800,
                    480,
                    200,
                    false,
                    () -> YeastRelatedMaterials.CRIMSON_YEAST,
                    () -> YeastRelatedMaterials.CRIMSON_YEAST_SEED_LIQUID,
                    () -> YeastRelatedMaterials.CRIMSON_YEAST_LIQUID,
                    () -> YeastRelatedMaterials.CRIMSON_YEAST_EXTRACT_LIQUID,
                    List.of(
                            GTMaterials.Ethane.getFluid(250),
                            GTMaterials.Benzene.getFluid(500),
                            GTMaterials.Butane.getFluid(250))),
            new YeastDefinition(
                    "warped",
                    Items.WARPED_FUNGUS.getDefaultInstance(),
                    1500,
                    480,
                    200,
                    false,
                    () -> YeastRelatedMaterials.WARPED_YEAST,
                    () -> YeastRelatedMaterials.WARPED_YEAST_SEED_LIQUID,
                    () -> YeastRelatedMaterials.WARPED_YEAST_LIQUID,
                    () -> YeastRelatedMaterials.WARPED_YEAST_EXTRACT_LIQUID,
                    List.of(
                            GTMaterials.Ethane.getFluid(250),
                            GTMaterials.Acetone.getFluid(500),
                            GTMaterials.Butane.getFluid(250))),
            new YeastDefinition(
                    "end",
                    Items.CHORUS_FRUIT.getDefaultInstance(),
                    2200,
                    480,
                    200,
                    false,
                    () -> YeastRelatedMaterials.END_YEAST,
                    () -> YeastRelatedMaterials.END_YEAST_SEED_LIQUID,
                    () -> YeastRelatedMaterials.END_YEAST_LIQUID,
                    () -> YeastRelatedMaterials.END_YEAST_EXTRACT_LIQUID,
                    List.of(
                            GTMaterials.Ethylene.getFluid(500),
                            GTMaterials.Phenol.getFluid(100),
                            GTMaterials.Ethanol.getFluid(150),
                            GTMaterials.Propene.getFluid(250))),
            new YeastDefinition(
                    "fluorescence",
                    TFBlocks.MUSHGLOOM.get().asItem().getDefaultInstance(),
                    2500,
                    480,
                    100,
                    true,
                    () -> YeastRelatedMaterials.FLUORESCENCE_YEAST,
                    () -> YeastRelatedMaterials.FLUORESCENCE_YEAST_SEED_LIQUID,
                    () -> YeastRelatedMaterials.FLUORESCENCE_YEAST_LIQUID,
                    () -> YeastRelatedMaterials.FLUORESCENCE_YEAST_EXTRACT_LIQUID,
                    List.of(
                            GTMaterials.Butadiene.getFluid(500),
                            YeastRelatedMaterials.AMINO_ACID.getFluid(250),
                            GTMaterials.Chlorobenzene.getFluid(150),
                            GTMaterials.Chloromethane.getFluid(100))),
            new YeastDefinition(
                    "light",
                    DABlocks.LIGHTCAP_MUSHROOMS.get().asItem().getDefaultInstance(),
                    3000,
                    1920,
                    200,
                    true,
                    () -> YeastRelatedMaterials.LIGHT_YEAST,
                    () -> YeastRelatedMaterials.LIGHT_YEAST_SEED_LIQUID,
                    () -> YeastRelatedMaterials.LIGHT_YEAST_LIQUID,
                    () -> YeastRelatedMaterials.LIGHT_YEAST_EXTRACT_LIQUID,
                    List.of(
                            GTMaterials.BisphenolA.getFluid(100),
                            GTMaterials.Octane.getFluid(250),
                            GTMaterials.Phenol.getFluid(250),
                            YeastRelatedMaterials.AMINO_ACID.getFluid(400))),
            new YeastDefinition(
                    "radiation_mutated",
                    null,
                    4500,
                    7680,
                    140,
                    false,
                    () -> YeastRelatedMaterials.RADIATION_MUTATED_YEAST,
                    () -> YeastRelatedMaterials.RADIATION_MUTATED_YEAST_SEED_LIQUID,
                    () -> YeastRelatedMaterials.RADIATION_MUTATED_YEAST_LIQUID,
                    () -> YeastRelatedMaterials.RADIATION_MUTATED_YEAST_EXTRACT_LIQUID,
                    List.of(
                            GTMaterials.BisphenolA.getFluid(100),
                            GTMaterials.Octane.getFluid(250),
                            GTMaterials.Phenol.getFluid(250),
                            YeastRelatedMaterials.AMINO_ACID.getFluid(400))));
}
