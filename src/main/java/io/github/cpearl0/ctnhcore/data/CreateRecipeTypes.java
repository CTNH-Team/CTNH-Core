package io.github.cpearl0.ctnhcore.data;

import io.github.cpearl0.ctnhcore.CTNHConfig;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.chance.logic.ChanceLogic;
import com.gregtechceu.gtceu.api.sound.ExistingSoundEntry;
import com.gregtechceu.gtceu.common.data.GTRecipes;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import com.gregtechceu.gtceu.utils.GTUtil;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

import com.mo_guang.ctpp.common.data.recipe.builder.CTPPRecipeBuilder;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.lowdragmc.lowdraglib.gui.texture.ProgressTexture.FillDirection.LEFT_TO_RIGHT;
import static com.lowdragmc.lowdraglib.gui.texture.ProgressTexture.FillDirection.UP_TO_DOWN;
import static io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes.KINETIC;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class CreateRecipeTypes {

    public static ResourceLocation convert(ResourceLocation id, GTRecipeType recipeType) {
        return ResourceLocation.tryBuild(id.getNamespace(), recipeType.registryName.getPath() + "/" + id.getPath());
    }

    public static void init() {
        BENDER_RECIPES.onRecipeBuild((builder, provider) -> {
            if (!GTRecipes.RECIPE_FILTERS.contains(convert(builder.id, builder.recipeType))) {
                assert MECHANICAL_PRESSOR_RECIPES != null;
                if (GTUtil.getTierByVoltage(builder.EUt().voltage()) <= GTValues.HV) {
                    var newrecipe = MECHANICAL_PRESSOR_RECIPES.copyFrom(builder)
                            .duration(Math.max(
                                    (int) (builder.duration / CTNHConfig.INSTANCE.kinetic.pressorSpeedMultiplier), 1))
                            .buildRawRecipe();
                    new CTPPRecipeBuilder(newrecipe, MECHANICAL_PRESSOR_RECIPES)
                            .rpm(CTNHConfig.INSTANCE.kinetic.pressorRpmRequirement)
                            .noEUt()
                            .tier(Math.min(GTUtil.getTierByVoltage(builder.EUt().voltage()) * 2, 5))
                            .inputStress(builder.EUt().voltage() * CTNHConfig.INSTANCE.kinetic.pressorStressRequirement)
                            .save(provider);
                }
            }
        });
        MIXER_RECIPES.onRecipeBuild((builder, provider) -> {
            if (!GTRecipes.RECIPE_FILTERS.contains(convert(builder.id, builder.recipeType))) {
                assert MECHANICAL_MIXER_RECIPES != null;
                if (GTUtil.getTierByVoltage(builder.EUt().voltage()) <= GTValues.HV) {
                    var newrecipe = MECHANICAL_MIXER_RECIPES.copyFrom(builder)
                            .duration(Math.max(
                                    (int) (builder.duration / CTNHConfig.INSTANCE.kinetic.mixerSpeedMultiplier), 1))
                            .buildRawRecipe();
                    new CTPPRecipeBuilder(newrecipe, MECHANICAL_MIXER_RECIPES)
                            .rpm(CTNHConfig.INSTANCE.kinetic.mixerRpmRequirement)
                            .noEUt()
                            .tier(Math.min(GTUtil.getTierByVoltage(builder.EUt().voltage()) * 2, 5))
                            .inputStress(builder.EUt().voltage() * CTNHConfig.INSTANCE.kinetic.mixerStressRequirement)
                            .save(provider);
                }
            }
        });
        CENTRIFUGE_RECIPES.onRecipeBuild((builder, provider) -> {
            if (!GTRecipes.RECIPE_FILTERS.contains(convert(builder.id, builder.recipeType))) {
                assert MECHANICAL_CENTRIFUGE_RECIPES != null;
                if (GTUtil.getTierByVoltage(builder.EUt().voltage()) <= GTValues.HV) {
                    var newrecipe = MECHANICAL_CENTRIFUGE_RECIPES.copyFrom(builder)
                            .duration(Math.max(
                                    (int) (builder.duration / CTNHConfig.INSTANCE.kinetic.centrifugeSpeedMultiplier),
                                    1))
                            .buildRawRecipe();
                    new CTPPRecipeBuilder(newrecipe, MECHANICAL_CENTRIFUGE_RECIPES)
                            .rpm(CTNHConfig.INSTANCE.kinetic.centrifugeRpmRequirement)
                            .noEUt()
                            .tier(Math.min(GTUtil.getTierByVoltage(builder.EUt().voltage()) * 2, 5))
                            .inputStress(
                                    builder.EUt().voltage() * CTNHConfig.INSTANCE.kinetic.centrifugeStressRequirement)
                            .save(provider);
                }
            }
        });
        SIFTER_RECIPES.onRecipeBuild((builder, provider) -> {
            if (!GTRecipes.RECIPE_FILTERS.contains(convert(builder.id, builder.recipeType))) {
                assert MECHANICAL_SIFTER_RECIPES != null;
                if (GTUtil.getTierByVoltage(builder.EUt().voltage()) <= GTValues.HV) {
                    var newrecipe = MECHANICAL_SIFTER_RECIPES.copyFrom(builder)
                            .duration(Math.max(
                                    (int) (builder.duration / CTNHConfig.INSTANCE.kinetic.sifterSpeedMultiplier), 1))
                            .buildRawRecipe();
                    new CTPPRecipeBuilder(newrecipe, MECHANICAL_SIFTER_RECIPES)
                            .rpm(CTNHConfig.INSTANCE.kinetic.sifterRpmRequirement)
                            .noEUt()
                            .tier(Math.min(GTUtil.getTierByVoltage(builder.EUt().voltage()) * 2, 5))
                            .inputStress(builder.EUt().voltage() * CTNHConfig.INSTANCE.kinetic.sifterStressRequirement)
                            // .chancedOutputLogic(ItemRecipeCapability.CAP, CTNHChanceLogic.BASIC)
                            .chancedOutputLogic(ItemRecipeCapability.CAP, ChanceLogic.NONE)
                            .save(provider);
                }
            }
        });
        EXTRACTOR_RECIPES.onRecipeBuild((builder, provider) -> {
            if (!GTRecipes.RECIPE_FILTERS.contains(convert(builder.id, builder.recipeType))) {
                assert MECHANICAL_EXTRACTOR_RECIPES != null;
                if (GTUtil.getTierByVoltage(builder.EUt().voltage()) <= GTValues.HV) {
                    var newrecipe = MECHANICAL_EXTRACTOR_RECIPES.copyFrom(builder)
                            .duration(Math.max(
                                    (int) (builder.duration / CTNHConfig.INSTANCE.kinetic.extractorSpeedMultiplier), 1))
                            .buildRawRecipe();
                    new CTPPRecipeBuilder(newrecipe, MECHANICAL_EXTRACTOR_RECIPES)
                            .rpm(CTNHConfig.INSTANCE.kinetic.extractorRpmRequirement)
                            .noEUt()
                            .tier(Math.min(GTUtil.getTierByVoltage(builder.EUt().voltage()) * 2, 5))
                            .inputStress(
                                    builder.EUt().voltage() * CTNHConfig.INSTANCE.kinetic.extractorStressRequirement)
                            .chancedOutputLogic(ItemRecipeCapability.CAP, ChanceLogic.NONE)
                            .save(provider);
                }
            }
        });
        LATHE_RECIPES.onRecipeBuild((builder, provider) -> {
            if (!GTRecipes.RECIPE_FILTERS.contains(convert(builder.id, builder.recipeType))) {
                assert MECHANICAL_LATHE_RECIPES != null;
                if (GTUtil.getTierByVoltage(builder.EUt().voltage()) <= GTValues.HV) {
                    var newrecipe = MECHANICAL_LATHE_RECIPES.copyFrom(builder)
                            .duration(Math.max(
                                    (int) (builder.duration / CTNHConfig.INSTANCE.kinetic.latheSpeedMultiplier), 1))
                            .buildRawRecipe();
                    new CTPPRecipeBuilder(newrecipe, MECHANICAL_LATHE_RECIPES)
                            .rpm(CTNHConfig.INSTANCE.kinetic.latheRpmRequirement)
                            .noEUt()
                            .tier(Math.min(GTUtil.getTierByVoltage(builder.EUt().voltage()) * 2, 5))
                            .inputStress(builder.EUt().voltage() * CTNHConfig.INSTANCE.kinetic.latheStressRequirement)
                            .save(provider);
                }
            }
        });
    }

    public static final GTRecipeType MECHANICAL_PRESSOR_RECIPES = REGISTRATE
            .recipeType("mechanical_pressor_recipes", KINETIC)
            .cnlang("机械辊压")
            .setMaxIOSize(2, 1, 0, 0)
            .setSlotOverlay(false, false, false, GuiTextures.BENDER_OVERLAY)
            .setSlotOverlay(false, false, true, GuiTextures.INT_CIRCUIT_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BENDING, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CUT);
    public static final GTRecipeType MECHANICAL_MIXER_RECIPES = REGISTRATE
            .recipeType("mechanical_mixer_recipes", KINETIC)
            .cnlang("机械搅拌")
            .setMaxIOSize(6, 6, 3, 3)
            .setSlotOverlay(false, false, GuiTextures.DUST_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.DUST_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.MIXER);
    public static final GTRecipeType MECHANICAL_CENTRIFUGE_RECIPES = REGISTRATE
            .recipeType("mechanical_centrifuge_recipes", KINETIC)
            .cnlang("机械离心")
            .setMaxIOSize(2, 6, 1, 6)
            .setSlotOverlay(false, false, false, GuiTextures.EXTRACTOR_OVERLAY)
            .setSlotOverlay(false, false, true, GuiTextures.CANISTER_OVERLAY)
            .setSlotOverlay(false, true, true, GuiTextures.CENTRIFUGE_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_EXTRACT, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CENTRIFUGE);
    public static final GTRecipeType MECHANICAL_SIFTER_RECIPES = REGISTRATE
            .recipeType("mechanical_sifter_recipes", KINETIC)
            .cnlang("机械筛选")
            .setMaxIOSize(1, 6, 0, 0)
            .setProgressBar(GuiTextures.PROGRESS_BAR_SIFT, UP_TO_DOWN)
            .setSound(new ExistingSoundEntry(SoundEvents.SAND_PLACE, SoundSource.BLOCKS));
    public static final GTRecipeType MECHANICAL_EXTRACTOR_RECIPES = REGISTRATE
            .recipeType("mechanical_extractor_recipes", KINETIC)
            .cnlang("机械提取")
            .setMaxIOSize(1, 1, 0, 1)
            .setSlotOverlay(false, false, GuiTextures.EXTRACTOR_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_EXTRACT, LEFT_TO_RIGHT);
    public static final GTRecipeType MECHANICAL_LATHE_RECIPES = REGISTRATE
            .recipeType("mechanical_lathe_recipes", KINETIC)
            .cnlang("机械车床")
            .setMaxIOSize(1, 2, 0, 0)
            .setSlotOverlay(false, false, GuiTextures.PIPE_OVERLAY_1)
            .setSlotOverlay(true, false, false, GuiTextures.PIPE_OVERLAY_2)
            .setSlotOverlay(true, false, true, GuiTextures.DUST_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_LATHE, LEFT_TO_RIGHT);
}
