package io.github.cpearl0.ctnhcore.data.recipe.modmodify;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.recipe.builder.CTNHRecipeBuilder;
import io.github.cpearl0.ctnhcore.data.materials.CreateMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHBlocks;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTRecipeCategories;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;

import appeng.core.definitions.AEBlocks;
import com.enderio.base.common.init.EIOBlocks;
import com.enderio.base.common.init.EIOItems;
import com.enderio.conduits.common.init.ConduitItems;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static io.github.cpearl0.ctnhcore.data.materials.EnderIOMaterials.*;
import static io.github.cpearl0.ctnhcore.data.recipe.RecipeRemoval.removePaths;

public class EIORecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        addAlloyAndMachineRecipes(provider);
        addReplaceInputShapedRecipes(provider);
    }

    private static void addAlloyAndMachineRecipes(Consumer<FinishedRecipe> provider) {
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
                .outputItems(CTNHBlocks.ENERGETIC_PHOTOVOLTAIC_BLOCK.asItem())
                .EUt(30)
                .duration(200)
                .save(provider);

        CTNHRecipeBuilder.of("pulsating_photovoltaic_block", ASSEMBLER_RECIPES)
                .inputItems(plate, PulsatingAlloy, 2)
                .inputItems(CTNHBlocks.ENERGETIC_PHOTOVOLTAIC_BLOCK.asItem())
                .inputItems(GTBlocks.CASING_TEMPERED_GLASS.asItem(), 2)
                .inputItems(EIOItems.PHOTOVOLTAIC_PLATE, 2)
                .outputItems(CTNHBlocks.PULSATING_PHOTOVOLTAIC_BLOCK.asItem())
                .EUt(30)
                .duration(200)
                .save(provider);

        CTNHRecipeBuilder.of("vibrant_photovoltaic_block", ASSEMBLER_RECIPES)
                .inputItems(plate, VibrantAlloy, 2)
                .inputItems(CTNHBlocks.PULSATING_PHOTOVOLTAIC_BLOCK.asItem())
                .inputItems(GTBlocks.CASING_TEMPERED_GLASS.asItem(), 2)
                .inputItems(EIOItems.PHOTOVOLTAIC_PLATE, 2)
                .inputItems(foil, CTNHMaterials.Sunnarium, 32)
                .outputItems(CTNHBlocks.VIBRANT_PHOTOVOLTAIC_BLOCK.asItem())
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

    /**
     * 迁移自 kubejs/enderio.js replaceInput 操作。
     * replaceInput 是运行时替换配方原料的逻辑，Java 等效做法：移除原配方 + 用替换后的原料重建。
     */
    private static void addReplaceInputShapedRecipes(Consumer<FinishedRecipe> provider) {
        // 1. ensouled_chassis: forge:gems/quartz → enderio:void_chassis
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("crafttable/ensouled_chassis"),
                new ItemStack(EIOBlocks.ENSOULED_CHASSIS.asItem()),
                "CIC", "IVI", "CIC",
                'C', EIOBlocks.SOUL_CHAIN.asItem(),
                'I', TagUtil.createItemTag("ingots/soularium"),
                'V', EIOBlocks.VOID_CHASSIS.asItem());

        // 2. fluid_conduit: enderio:clear_glass → minecraft:glass
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("crafttable/fluid_conduit"),
                new ItemStack(ConduitItems.FLUID.get(), 8),
                "BBB", "GGG", "BBB",
                'B', EIOItems.CONDUIT_BINDER.asItem(),
                'G', Items.GLASS);

        // 3. conduit_probe: energy_conduit → fluid_conduit
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("crafttable/conduit_probe"),
                new ItemStack(ConduitItems.CONDUIT_PROBE.get()),
                "ARA", "PCP", "RFR",
                'A', EIOItems.CONDUCTIVE_ALLOY_INGOT.asItem(),
                'R', ConduitItems.REDSTONE.get(),
                'P', TagUtil.createItemTag("glass_panes"),
                'C', Items.COMPARATOR,
                'F', ConduitItems.FLUID.get());

        // 4. crafter: crafting_table → sophisticatedbackpacks:crafting_upgrade
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("crafttable/crafter"),
                new ItemStack(requireItem("enderio:crafter")),
                "SSS", "ICI", "GUG",
                'S', TagUtil.createItemTag("silicon"),
                'C', EIOBlocks.VOID_CHASSIS.asItem(),
                'I', TagUtil.createItemTag("ingots/iron"),
                'G', TagUtil.createItemTag("gears/iron"),
                'U', requireItem("sophisticatedbackpacks:crafting_upgrade"));

        // 5. empty_soul_vial: fused_quartz → ae2:quartz_glass
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("crafttable/empty_soul_vial"),
                new ItemStack(requireItem("enderio:empty_soul_vial")),
                " S ", "Q Q", " Q ",
                'S', TagUtil.createItemTag("ingots/soularium"),
                'Q', AEBlocks.QUARTZ_GLASS.asItem());

        // 6. pressurized_fluid_conduit: fused_quartz → ae2:quartz_glass
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("crafttable/pressurized_fluid_conduit"),
                new ItemStack(ConduitItems.PRESSURIZED_FLUID.get(), 8),
                "BBB", "GGG", "BBB",
                'B', EIOItems.CONDUIT_BINDER.asItem(),
                'G', AEBlocks.QUARTZ_GLASS.asItem());

        // 7. pressurized_fluid_tank: fused_quartz → ae2:quartz_glass
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("crafttable/pressurized_fluid_tank"),
                new ItemStack(requireItem("enderio:pressurized_fluid_tank")),
                "IBI", "BGB", "IBI",
                'I', TagUtil.createItemTag("ingots/dark_steel"),
                'B', EIOBlocks.DARK_STEEL_BARS.asItem(),
                'G', AEBlocks.QUARTZ_GLASS.asItem());
    }

    /** Resolve item by full registry name; throws if missing (indicates broken mod dependency). */
    private static net.minecraft.world.item.Item requireItem(String id) {
        return Objects.requireNonNull(
                ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(id)),
                "Item not found: " + id);
    }

    public static void eioRemovals() {
        removePaths.addAll(List.of(
                // Original EnderIO recipes removed by CTNH
                "enderio:iron_gear",
                "enderio:energy_conduit",
                "enderio:photovoltaic_composite",
                "enderio:basic_capacitor_bank",
                "enderio:vibrant_capacitor_bank",
                "enderio:advanced_capacitor_bank",
                "enderio:energetic_photovoltaic_module",
                "enderio:pulsating_photovoltaic_module",
                "enderio:vibrant_photovoltaic_module",
                "enderio:fluid_tank",
                "enderio:primitive_alloy_smelter",
                "enderio:alloy_smelter",
                "enderio:stirling_generator",
                "enderio:vibrant_capacitor_bank_upgrade",
                "enderio:basic_capacitor",
                "enderio:double_layer_capacitor",
                "enderio:octadic_capacitor",
                // 迁移自 enderio.js replaceInput 操作：移除原版配方（已被 CTNH 重建版覆盖）
                "enderio:ensouled_chassis",
                "enderio:fluid_conduit",
                "enderio:conduit_probe",
                "enderio:crafter",
                "enderio:empty_soul_vial",
                "enderio:pressurized_fluid_conduit",
                // 全局 replaceInput fused_quartz → ae2:quartz_glass 影响的范围
                "enderio:ender_fluid_conduit",
                "enderio:pressurized_fluid_conduit_upgrade",
                "enderio:ender_fluid_conduit_upgrade",
                "enderio:pressurized_fluid_tank"));
    }
}
