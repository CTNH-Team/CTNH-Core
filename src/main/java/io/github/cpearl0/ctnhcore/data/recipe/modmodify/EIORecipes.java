package io.github.cpearl0.ctnhcore.data.recipe.modmodify;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.recipe.builder.CTNHRecipeBuilder;
import com.mo_guang.ctpp.registry.CreateMaterials;
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
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;

import appeng.core.definitions.AEBlocks;
import com.enderio.base.common.init.EIOBlocks;
import com.enderio.base.common.init.EIOItems;
import com.enderio.conduits.common.init.ConduitItems;
import com.enderio.machines.common.init.MachineBlocks;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static io.github.cpearl0.ctnhcore.data.materials.EnderIOMaterials.*;

public class EIORecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        addAlloyAndMachineRecipes(provider);
        addRebuiltRecipes(provider);
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

    /** Rebuilt EnderIO recipes with the intended CTNH ingredients. */
    private static void addRebuiltRecipes(Consumer<FinishedRecipe> provider) {
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

        addFusedQuartzAlloyRecipes(provider);
        addFusedQuartzCollisionRecipes(provider);
        addFusedQuartzRecolorRecipes(provider);
        addFusedQuartzCraftingRecipes(provider);
    }

    private static void addFusedQuartzAlloyRecipes(Consumer<FinishedRecipe> provider) {
        addFusedQuartzAlloyRecipe(provider, "fused_quartz_d_from_base", 4,
                tagIngredient("forge:gems/amethyst"), "enderio:fused_quartz_d");
        addFusedQuartzAlloyRecipe(provider, "fused_quartz_d_from_base_alt", 1,
                tagIngredient("forge:storage_blocks/amethyst"), "enderio:fused_quartz_d");
        addFusedQuartzAlloyRecipe(provider, "fused_quartz_e_from_base", 4,
                tagIngredient("forge:dusts/glowstone"), "enderio:fused_quartz_e");
        addFusedQuartzAlloyRecipe(provider, "fused_quartz_e_from_base_alt", 1,
                itemIngredient("minecraft:glowstone"), "enderio:fused_quartz_e");
    }

    private static void addFusedQuartzAlloyRecipe(Consumer<FinishedRecipe> provider, String name, int secondaryCount,
                                                  JsonObject secondaryIngredient, String result) {
        JsonObject recipe = new JsonObject();
        recipe.addProperty("type", "enderio:alloy_smelting");
        recipe.addProperty("energy", 3200);
        recipe.addProperty("experience", 0.3);
        JsonArray inputs = new JsonArray();
        inputs.add(alloyInput(1, itemIngredient("ae2:quartz_glass")));
        inputs.add(alloyInput(secondaryCount, secondaryIngredient));
        recipe.add("inputs", inputs);
        recipe.add("result", itemIngredient(result));
        provider.accept(new JsonFinishedRecipe(CTNHCore.id("enderio/alloy_smelting/" + name), recipe));
    }

    private static JsonObject alloyInput(int count, JsonObject ingredient) {
        JsonObject input = new JsonObject();
        input.addProperty("count", count);
        input.add("ingredient", ingredient);
        return input;
    }

    private static void addFusedQuartzCollisionRecipes(Consumer<FinishedRecipe> provider) {
        addFusedQuartzCollisionRecipe(provider, "a", EIOItems.ANIMAL_TOKEN.get());
        addFusedQuartzCollisionRecipe(provider, "m", EIOItems.MONSTER_TOKEN.get());
        addFusedQuartzCollisionRecipe(provider, "p", EIOItems.PLAYER_TOKEN.get());
    }

    private static void addFusedQuartzCollisionRecipe(Consumer<FinishedRecipe> provider, String suffix,
                                                      net.minecraft.world.item.Item token) {
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("enderio/collision_token_fused_quartz_" + suffix),
                itemStack("enderio:fused_quartz_" + suffix, 8),
                "GGG", "GTG", "GGG",
                'G', AEBlocks.QUARTZ_GLASS.asItem(),
                'T', token);
    }

    private static void addFusedQuartzRecolorRecipes(Consumer<FinishedRecipe> provider) {
        for (String color : new String[] {
                "black", "blue", "brown", "cyan", "gray", "green", "light_blue", "light_gray",
                "lime", "magenta", "orange", "pink", "purple", "red", "white", "yellow"
        }) {
            Object[] ingredients = new Object[9];
            Arrays.fill(ingredients, AEBlocks.QUARTZ_GLASS.asItem());
            ingredients[8] = TagUtil.createItemTag("dyes/" + color);
            VanillaRecipeHelper.addShapelessRecipe(provider,
                    CTNHCore.id("enderio/recolor_fused_quartz_" + color),
                    itemStack("enderio:fused_quartz_" + color, 8),
                    ingredients);
        }
    }

    private static void addFusedQuartzCraftingRecipes(Consumer<FinishedRecipe> provider) {
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("crafttable/ender_fluid_conduit"),
                new ItemStack(ConduitItems.ENDER_FLUID.get(), 8),
                "BBB", "IGI", "BBB",
                'B', EIOItems.CONDUIT_BINDER.asItem(),
                'I', TagUtil.createItemTag("ingots/vibrant_alloy"),
                'G', AEBlocks.QUARTZ_GLASS.asItem());
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("crafttable/pressurized_fluid_conduit_upgrade"),
                new ItemStack(ConduitItems.PRESSURIZED_FLUID.get(), 8),
                "BBB", "GCG", "BBB",
                'B', EIOItems.CONDUIT_BINDER.asItem(),
                'C', ConduitItems.FLUID.get(),
                'G', AEBlocks.QUARTZ_GLASS.asItem());
        VanillaRecipeHelper.addShapedRecipe(provider,
                CTNHCore.id("crafttable/soul_engine"),
                new ItemStack(MachineBlocks.SOUL_ENGINE.get()),
                "IGI", "BCB", "IZI",
                'B', Items.BUCKET,
                'C', EIOBlocks.ENSOULED_CHASSIS.asItem(),
                'G', AEBlocks.QUARTZ_GLASS.asItem(),
                'I', TagUtil.createItemTag("ingots/soularium"),
                'Z', EIOItems.ZOMBIE_ELECTRODE.get());
    }

    private static ItemStack itemStack(String id, int count) {
        ResourceLocation resourceLocation = ResourceLocation.parse(id);
        return new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(resourceLocation),
                "Unregistered item: " + resourceLocation), count);
    }

    private static JsonObject itemIngredient(String item) {
        JsonObject ingredient = new JsonObject();
        ingredient.addProperty("item", item);
        return ingredient;
    }

    private static JsonObject tagIngredient(String tag) {
        JsonObject ingredient = new JsonObject();
        ingredient.addProperty("tag", tag);
        return ingredient;
    }

    private record JsonFinishedRecipe(ResourceLocation id, JsonObject json) implements FinishedRecipe {

        @Override
        public void serializeRecipeData(JsonObject recipeJson) {
            json.entrySet().forEach(entry -> recipeJson.add(entry.getKey(), entry.getValue()));
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            ResourceLocation type = ResourceLocation.parse(json.get("type").getAsString());
            return Objects.requireNonNull(ForgeRegistries.RECIPE_SERIALIZERS.getValue(type),
                    "Recipe serializer not found: " + type);
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
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
        RecipeRemoval.remove(new RemoveFilter().id("enderio:creative_power"));
        RecipeRemoval.remove(new RemoveFilter().type("enderio:alloy_smelting"));
        RecipeRemoval.remove(new RemoveFilter().type("enderio:pressurized_fluid_conduit_upgrade"));

        String[] enderMetals = { "soularium", "energetic_alloy", "pulsating_alloy", "copper_alloy", "vibrant_alloy",
                "redstone_alloy", "conductive_alloy", "dark_steel", "end_steel" };
        for (String metal : enderMetals) {
            RecipeRemoval.remove(new RemoveFilter().id("enderio:" + metal + "_nugget"));
            RecipeRemoval.remove(new RemoveFilter().id("enderio:" + metal + "_ingot"));
            RecipeRemoval.remove(new RemoveFilter().id("enderio:" + metal + "_nugget_to_ingot"));
            RecipeRemoval.remove(new RemoveFilter().id("enderio:alloy_smelting/" + metal + "_ingot"));
            RecipeRemoval.remove(new RemoveFilter().id("enderio:" + metal + "_block"));
        }

        RecipeRemoval.remove(new RemoveFilter().id("enderio:ensouled_chassis"));
        RecipeRemoval.remove(new RemoveFilter().id("enderio:fluid_conduit"));
        RecipeRemoval.remove(new RemoveFilter().id("enderio:crafter"));
        RecipeRemoval.remove(new RemoveFilter().id("enderio:conduit_probe"));

        for (String recipe : new String[] {
                "enderio:empty_soul_vial",
                "enderio:ender_fluid_conduit",
                "enderio:pressurized_fluid_conduit",
                "enderio:pressurized_fluid_conduit_upgrade",
                "enderio:pressurized_fluid_tank",
                "enderio:soul_engine",
                "enderio:alloy_smelting/fused_quartz_d_from_base",
                "enderio:alloy_smelting/fused_quartz_d_from_base_alt",
                "enderio:alloy_smelting/fused_quartz_e_from_base",
                "enderio:alloy_smelting/fused_quartz_e_from_base_alt",
                "enderio:collision_token_fused_quartz_a",
                "enderio:collision_token_fused_quartz_m",
                "enderio:collision_token_fused_quartz_p"
        }) {
            RecipeRemoval.remove(new RemoveFilter().id(recipe));
        }

        for (String color : new String[] {
                "black", "blue", "brown", "cyan", "gray", "green", "light_blue", "light_gray",
                "lime", "magenta", "orange", "pink", "purple", "red", "white", "yellow"
        }) {
            RecipeRemoval.remove(new RemoveFilter().id("enderio:recolor_fused_quartz_" + color));
        }
    }
}
