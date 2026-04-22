package io.github.cpearl0.ctnhcore.data.recipe.modmodify;

import io.github.cpearl0.ctnhcore.data.materials.CreateMaterials;
import io.github.cpearl0.ctnhcore.common.recipe.builder.CTNHRecipeBuilder;
import io.github.cpearl0.ctnhcore.registry.CTNHBlocks;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTRecipeCategories;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.crafting.Ingredient;

import com.enderio.base.common.init.EIOItems;

import java.util.List;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static io.github.cpearl0.ctnhcore.data.materials.EnderIOMaterials.*;
import static io.github.cpearl0.ctnhcore.data.recipe.RecipeRemoval.removePaths;

public class EIORecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        CTNHRecipeBuilder.of("conductive_alloy_ingot", ALLOY_SMELTER_RECIPES)
                .inputItems(ingot, Iron, 1)
                .inputItems(dust, Redstone, 2)
                .outputItems(ingot, ConductiveAlloy, 1)
                .EUt(30)
                .duration(40)
                .save(provider);

        CTNHRecipeBuilder.of("pulsating_alloy_ingot", ALLOY_SMELTER_RECIPES)
                .inputItems(ingot, Iron, 1)
                .inputItems(dust, EnderPearl, 1)
                .outputItems(ingot, PulsatingAlloy, 1)
                .EUt(30)
                .duration(50)
                .save(provider);

        CTNHRecipeBuilder.of("soularium_ingot", ALLOY_SMELTER_RECIPES)
                .inputItems(ingot, Gold, 1)
                .inputItems(Ingredient.of(
                        new TagKey<>(Registries.ITEM, ResourceLocation.tryBuild("minecraft", "soul_fire_base_blocks"))))
                .outputItems(ingot, Soularium, 1)
                .EUt(120)
                .duration(60)
                .save(provider);

        CTNHRecipeBuilder.of("copper_alloy_ingot", ALLOY_SMELTER_RECIPES)
                .inputItems(ingot, Copper, 1)
                .inputItems(ingot, Silicon, 1)
                .outputItems(ingot, CopperAlloy, 1)
                .EUt(120)
                .duration(40)
                .save(provider);

        CTNHRecipeBuilder.of("redstone_alloy_ingot", ALLOY_SMELTER_RECIPES)
                .inputItems(dust, Redstone, 1)
                .inputItems(ingot, Silicon, 1)
                .outputItems(ingot, RedstoneAlloy, 1)
                .EUt(120)
                .duration(50)
                .save(provider);

        CTNHRecipeBuilder.of("energetic_alloy", MIXER_RECIPES)
                .inputItems(dust, Redstone, 1)
                .inputItems(dust, Glowstone, 1)
                .inputItems(dust, Gold, 2)
                .outputItems(dust, EnergeticAlloy, 2)
                .EUt(100)
                .duration(30)
                .save(provider);

        CTNHRecipeBuilder.of("vibrant_alloy", MIXER_RECIPES)
                .inputItems(dust, EnergeticAlloy, 1)
                .inputItems(dust, EnderPearl, 1)
                .outputItems(dust, VibrantAlloy, 1)
                .EUt(120)
                .duration(100)
                .save(provider);

        CTNHRecipeBuilder.of("end_steel", MIXER_RECIPES)
                .inputItems(dust, DarkSteel, 1)
                .inputItems(dust, Obsidian, 1)
                .inputItems(dust, Endstone, 1)
                .outputItems(dust, EndSteel, 3)
                .EUt(480)
                .duration(100)
                .save(provider);

        CTNHRecipeBuilder.of("meloric_steel", MIXER_RECIPES)
                .inputItems(dust, EndSteel, 1)
                .inputItems(dust, ChorusiteAlloy, 1)
                .outputItems(dust, MelodicAlloy, 2)
                .EUt(1920)
                .duration(100)
                .save(provider);

        CTNHRecipeBuilder.of("stellar_steel", MIXER_RECIPES)
                .inputItems(dust, MelodicAlloy, 1)
                .inputItems(dust, CreateMaterials.RefinedRadiance, 1)
                .inputItems(dust, NetherStar, 1)
                .outputItems(dust, StellarAlloy, 3)
                .EUt(1920)
                .duration(100)
                .save(provider);

        CTNHRecipeBuilder.of("photovoltaic_composite", MIXER_RECIPES)
                .inputItems(dust, Silicon, 1)
                .inputItems(dust, Coal, 1)
                .inputItems(dust, Lapis, 1)
                .outputItems(EIOItems.PHOTOVOLTAIC_COMPOSITE, 3)
                .EUt(30)
                .duration(40)
                .save(provider);

        CTNHRecipeBuilder.of("photovoltaic_plate", FORGE_HAMMER_RECIPES)
                .inputItems(EIOItems.PHOTOVOLTAIC_COMPOSITE, 3)
                .outputItems(EIOItems.PHOTOVOLTAIC_PLATE)
                .EUt(30)
                .duration(40)
                .save(provider);

        CTNHRecipeBuilder.of("energetic_photovoltaic_block", ASSEMBLER_RECIPES)
                .inputItems(plate, Gold, 2)
                .inputItems(frameGt, Silver, 1)
                .inputItems(block, Glass, 2)
                .inputItems(EIOItems.PHOTOVOLTAIC_PLATE, 2)
                .outputItems(CTNHBlocks.ENERGETIC_PHOTOVOLTAIC_BLOCK)
                .EUt(30)
                .duration(200)
                .save(provider);

        CTNHRecipeBuilder.of("pulsating_photovoltaic_block", ASSEMBLER_RECIPES)
                .inputItems(plate, PulsatingAlloy, 2)
                .inputItems(CTNHBlocks.ENERGETIC_PHOTOVOLTAIC_BLOCK.asItem())
                .inputItems(GTBlocks.CASING_TEMPERED_GLASS.asItem(), 2)
                .inputItems(EIOItems.PHOTOVOLTAIC_PLATE, 2)
                .outputItems(CTNHBlocks.PULSATING_PHOTOVOLTAIC_BLOCK)
                .EUt(30)
                .duration(200)
                .save(provider);

        CTNHRecipeBuilder.of("vibrant_photovoltaic_block", ASSEMBLER_RECIPES)
                .inputItems(plate, VibrantAlloy, 2)
                .inputItems(CTNHBlocks.PULSATING_PHOTOVOLTAIC_BLOCK.asItem())
                .inputItems(GTBlocks.CASING_TEMPERED_GLASS.asItem(), 2)
                .inputItems(EIOItems.PHOTOVOLTAIC_PLATE, 2)
                .inputItems(foil, CTNHMaterials.Sunnarium, 32)
                .outputItems(CTNHBlocks.VIBRANT_PHOTOVOLTAIC_BLOCK)
                .EUt(30)
                .duration(200)
                .save(provider);

        CTNHRecipeBuilder.of("energetic_photovoltaic_block_recycling", MACERATOR_RECIPES)
                .inputItems(CTNHBlocks.ENERGETIC_PHOTOVOLTAIC_BLOCK.asItem())
                .outputItems(dust, Gold, 2)
                .outputItems(dust, Silver, 2)
                .outputItems(EIOItems.PHOTOVOLTAIC_COMPOSITE, 6)
                .EUt(30)
                .duration(100)
                .category(GTRecipeCategories.MACERATOR_RECYCLING)
                .save(provider);

        CTNHRecipeBuilder.of("pulsating_photovoltaic_block_recycling", MACERATOR_RECIPES)
                .inputItems(CTNHBlocks.PULSATING_PHOTOVOLTAIC_BLOCK.asItem())
                .outputItems(dust, Gold, 2)
                .outputItems(dust, Silver, 2)
                .outputItems(dust, PulsatingAlloy, 2)
                .outputItems(EIOItems.PHOTOVOLTAIC_COMPOSITE, 12)
                .EUt(30)
                .duration(100)
                .category(GTRecipeCategories.MACERATOR_RECYCLING)
                .save(provider);

        CTNHRecipeBuilder.of("vibrant_photovoltaic_block_recycling", MACERATOR_RECIPES)
                .inputItems(CTNHBlocks.VIBRANT_PHOTOVOLTAIC_BLOCK.asItem())
                .outputItems(dust, VibrantAlloy, 2)
                .outputItems(dust, PulsatingAlloy, 2)
                .outputItems(dust, CTNHMaterials.Sunnarium, 8)
                .outputItems(EIOItems.PHOTOVOLTAIC_COMPOSITE, 18)
                .EUt(30)
                .duration(100)
                .category(GTRecipeCategories.MACERATOR_RECYCLING)
                .save(provider);
    }

    public static void eioRemovals() {
        removePaths.addAll(List.of(
                "enderio:energy_conduit",
                "enderio:photovoltaic_composite",
                "enderio:basic_capacitor_bank",
                "enderio:vibrant_capacitor_bank",
                "enderio:advanced_capacitor_bank",
                "enderio:energetic_photovoltaic_module",
                "enderio:pulsating_photovoltaic_module",
                "enderio:vibrant_photovoltaic_module",
                "enderio:fluid_tank",
                "enderio:pressurized_fluid_tank",
                "enderio:primitive_alloy_smelter",
                "enderio:alloy_smelter",
                "enderio:stirling_generator",
                "enderio:vibrant_capacitor_bank_upgrade",
                "enderio:basic_capacitor",
                "enderio:double_layer_capacitor",
                "enderio:octadic_capacitor"));
    }
}
