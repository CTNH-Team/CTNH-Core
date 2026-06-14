package io.github.cpearl0.ctnhcore.data.recipe.modmodify;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.recipe.builder.CTNHRecipeBuilder;
import io.github.cpearl0.ctnhcore.data.materials.CreateMaterials;
import io.github.cpearl0.ctnhcore.data.recipe.RecipeRemoval;
import io.github.cpearl0.ctnhcore.data.recipe.RecipeRemoval.RemoveFilter;
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
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;

import appeng.core.definitions.AEBlocks;
import com.enderio.base.common.init.EIOBlocks;
import com.enderio.base.common.init.EIOItems;
import com.enderio.conduits.common.init.ConduitItems;
import com.enderio.machines.common.init.MachineBlocks;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static io.github.cpearl0.ctnhcore.data.materials.EnderIOMaterials.*;

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
                new ItemStack(MachineBlocks.CRAFTER.get()),
                "SSS", "ICI", "GUG",
                'S', TagUtil.createItemTag("silicon"),
                'C', EIOBlocks.VOID_CHASSIS.asItem(),
                'I', TagUtil.createItemTag("ingots/iron"),
                'G', TagUtil.createItemTag("gears/iron"),
                'U', ModItems.CRAFTING_UPGRADE.get());

        // 5. empty_soul_vial: fused_quartz → ae2:quartz_glass
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("crafttable/empty_soul_vial"),
                new ItemStack(EIOItems.EMPTY_SOUL_VIAL.get()),
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
                new ItemStack(MachineBlocks.PRESSURIZED_FLUID_TANK.get()),
                "IBI", "BGB", "IBI",
                'I', TagUtil.createItemTag("ingots/dark_steel"),
                'B', EIOBlocks.DARK_STEEL_BARS.asItem(),
                'G', AEBlocks.QUARTZ_GLASS.asItem());
    }

    public static void eioRemovals() {
        RecipeRemoval.remove(new RemoveFilter().id("enderio:energy_conduit"));
        RecipeRemoval.remove(new RemoveFilter().id("enderio:photovoltaic_composite"));
        RecipeRemoval.remove(new RemoveFilter().id("enderio:basic_capacitor_bank"));
        RecipeRemoval.remove(new RemoveFilter().id("enderio:vibrant_capacitor_bank"));
        RecipeRemoval.remove(new RemoveFilter().id("enderio:advanced_capacitor_bank"));
        RecipeRemoval.remove(new RemoveFilter().id("enderio:energetic_photovoltaic_module"));
        RecipeRemoval.remove(new RemoveFilter().id("enderio:pulsating_photovoltaic_module"));
        RecipeRemoval.remove(new RemoveFilter().id("enderio:vibrant_photovoltaic_module"));
        RecipeRemoval.remove(new RemoveFilter().id("enderio:fluid_tank"));
        RecipeRemoval.remove(new RemoveFilter().id("enderio:pressurized_fluid_tank"));
        RecipeRemoval.remove(new RemoveFilter().id("enderio:primitive_alloy_smelter"));
        RecipeRemoval.remove(new RemoveFilter().id("enderio:alloy_smelter"));
        RecipeRemoval.remove(new RemoveFilter().id("enderio:stirling_generator"));
        RecipeRemoval.remove(new RemoveFilter().id("enderio:vibrant_capacitor_bank_upgrade"));
        RecipeRemoval.remove(new RemoveFilter().id("enderio:basic_capacitor"));
        RecipeRemoval.remove(new RemoveFilter().id("enderio:double_layer_capacitor"));
        RecipeRemoval.remove(new RemoveFilter().id("enderio:octadic_capacitor"));
        RecipeRemoval.remove(new RemoveFilter().id("enderio:iron_gear"));
        RecipeRemoval.remove(new RemoveFilter().id("enderio:stick"));
        RecipeRemoval.remove(new RemoveFilter().id("enderio:wood_gear"));
        RecipeRemoval.remove(new RemoveFilter().id("enderio:wood_gear_corner"));
        RecipeRemoval.remove(new RemoveFilter().id("enderio:void_chassis"));
        RecipeRemoval.remove(new RemoveFilter().output("enderio:creative_power"));
        RecipeRemoval.remove(new RemoveFilter().type("enderio:alloy_smelting"));
        RecipeRemoval.remove(new RemoveFilter().type("enderio:pressurized_fluid_conduit_upgrade"));

        String[] enderMetals = { "soularium", "energetic_alloy", "pulsating_alloy", "copper_alloy", "vibrant_alloy",
                "redstone_alloy", "conductive_alloy", "dark_steel", "end_steel" };
        for (String metal : enderMetals) {
            RecipeRemoval.remove(new RemoveFilter().output("enderio:" + metal + "_nugget"));
            RecipeRemoval.remove(new RemoveFilter().output("enderio:" + metal + "_ingot"));
            RecipeRemoval.remove(new RemoveFilter().output("enderio:" + metal + "_block"));
            RecipeRemoval.replaceInput(new RemoveFilter().output("enderio:" + metal + "_nugget"),
                    "enderio:" + metal + "_nugget", "gtceu:" + metal + "_nugget");
            RecipeRemoval.replaceInput(new RemoveFilter().output("enderio:" + metal + "_ingot"),
                    "enderio:" + metal + "_ingot", "gtceu:" + metal + "_ingot");
            RecipeRemoval.replaceInput(new RemoveFilter().output("enderio:" + metal + "_block"),
                    "enderio:" + metal + "_block", "gtceu:" + metal + "_block");
        }

        // ===== replaceInput（ingredient 级别替换）=====
        // [replaceInput] enderio.js:58 enderio:ensouled_chassis: minecraft:quartz → enderio:void_chassis
        RecipeRemoval.replaceInput(new RemoveFilter().id("enderio:ensouled_chassis"),
                "minecraft:quartz", "enderio:void_chassis");
        // [replaceInput] enderio.js:90 enderio:fluid_conduit: #enderio:clear_glass → minecraft:glass
        RecipeRemoval.replaceInput(new RemoveFilter().id("enderio:fluid_conduit"),
                "#enderio:clear_glass", "minecraft:glass");
        // [replaceInput] enderio.js:94 enderio:fused_quartz → ae2:quartz_glass
        RecipeRemoval.replaceInput(new RemoveFilter(),
                "enderio:fused_quartz", "ae2:quartz_glass");
        // [replaceInput] enderio.js:95 enderio:crafter: minecraft:crafting_table →
        // sophisticatedbackpacks:crafting_upgrade
        RecipeRemoval.replaceInput(new RemoveFilter().output("enderio:crafter"),
                "minecraft:crafting_table", "sophisticatedbackpacks:crafting_upgrade");
        // [replaceInput] enderio.js:96 enderio:conduit_probe: enderio:energy_conduit → enderio:fluid_conduit
        RecipeRemoval.replaceInput(new RemoveFilter().id("enderio:conduit_probe"),
                "enderio:energy_conduit", "enderio:fluid_conduit");
    }
}
