package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.data.recipe.CustomTags;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import dev.shadowsoffire.apotheosis.spawn.modifiers.SpawnerModifier;
import dev.shadowsoffire.apotheosis.spawn.modifiers.SpawnerStat;
import dev.shadowsoffire.apotheosis.spawn.modifiers.SpawnerStats;
import dev.shadowsoffire.apotheosis.spawn.modifiers.StatModifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class ApothesisScriptRecipe {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 迁移来源：Z:/Git/Create-New-Horizon/kubejs/server_scripts/src/apothesis/spawner.js
        addSpawnerModifierRecipes(provider);
    }

    private static void addSpawnerModifierRecipes(Consumer<FinishedRecipe> provider) {
        addBooleanModifier(provider, "ignore_light", externalItem("torchmaster:feral_flare_lantern"),
                SpawnerStats.IGNORE_LIGHT);
        addIntModifier(provider, "spawn_count", GTItems.EMITTER_MV.asItem(), SpawnerStats.SPAWN_COUNT, 1, -1, 16,
                -1, 1, -1);
        addIntModifier(provider, "max_nearby", GTItems.SENSOR_MV.asItem(), SpawnerStats.MAX_NEARBY_ENTITIES, 2,
                -1,
                32, -2, 1, -1);
        addBooleanModifier(provider, "baby", GTItems.NAND_CHIP_ULV.asItem(), SpawnerStats.BABY);
        addBooleanModifier(provider, "redstone_control", GTItems.COVER_ACTIVITY_DETECTOR.asItem(),
                SpawnerStats.REDSTONE_CONTROL);
        addBooleanModifier(provider, "no_ai", GTItems.ROBOT_ARM_MV.asItem(), SpawnerStats.NO_AI);
        addIntModifier(provider, "min_delay", CustomTags.LV_CIRCUITS, SpawnerStats.MIN_DELAY, -10, 20, -1, 10, -1,
                -1);
        addIntModifier(provider, "max_delay", CustomTags.MV_CIRCUITS, SpawnerStats.MAX_DELAY, -10, 20, -1, 10, -1,
                -1);
        addBooleanModifier(provider, "ignore_conditions", CustomTags.IV_CIRCUITS, SpawnerStats.IGNORE_CONDITIONS);
        addIntModifier(provider, "player_range", GTItems.QUANTUM_EYE.asItem(), SpawnerStats.REQ_PLAYER_RANGE, 2,
                -1,
                48, -2, 1, -1);
        addBooleanModifier(provider, "ignore_players", GTItems.QUANTUM_STAR.asItem(),
                SpawnerStats.IGNORE_PLAYERS);
        addIntModifier(provider, "spawn_range", GTItems.FIELD_GENERATOR_LV.asItem(), SpawnerStats.SPAWN_RANGE, 1,
                -1,
                32, -1, 1, -1);
    }

    private static void addBooleanModifier(Consumer<FinishedRecipe> provider, String id, Object mainhand,
                                           SpawnerStat<Boolean> stat) {
        Ingredient mainhandIngredient = ingredient(mainhand);
        save(provider, id,
                modifier(id, mainhandIngredient, Ingredient.EMPTY, false, statModifier(stat, true, false, true)));
        save(provider, id + "_inverted", modifier(id + "_inverted", mainhandIngredient, quartz(), false,
                statModifier(stat, false, false, true)));
    }

    private static void addIntModifier(Consumer<FinishedRecipe> provider, String id, Object mainhand,
                                       SpawnerStat<Short> stat, int value, int min, int max, int invertedValue,
                                       int invertedMin, int invertedMax) {
        Ingredient mainhandIngredient = ingredient(mainhand);
        save(provider, id, modifier(id, mainhandIngredient, Ingredient.EMPTY, false,
                statModifier(stat, value, min, max)));
        save(provider, id + "_inverted", modifier(id + "_inverted", mainhandIngredient, quartz(), false,
                statModifier(stat, invertedValue, invertedMin, invertedMax)));
    }

    private static SpawnerModifier modifier(String id, Ingredient mainhand, Ingredient offhand, boolean consumesOffhand,
                                            StatModifier<?> statChange) {
        return new SpawnerModifier(CTNHCore.id("apotheosis/spawner/" + id), mainhand, offhand, consumesOffhand,
                List.of(statChange));
    }

    private static StatModifier<Boolean> statModifier(SpawnerStat<Boolean> stat, boolean value, boolean min,
                                                      boolean max) {
        return new StatModifier<>(stat, value, min, max);
    }

    private static StatModifier<Short> statModifier(SpawnerStat<Short> stat, int value, int min, int max) {
        return new StatModifier<>(stat, (short) value, (short) min, (short) max);
    }

    private static Ingredient ingredient(Object ingredient) {
        if (ingredient instanceof Ingredient directIngredient) {
            return directIngredient;
        }
        if (ingredient instanceof TagKey<?> tag) {
            return Ingredient.of(TagKey.create(net.minecraft.core.registries.Registries.ITEM, tag.location()));
        }
        if (ingredient instanceof ItemLike item) {
            return Ingredient.of(item);
        }
        throw new IllegalArgumentException("Unsupported spawner modifier ingredient: " + ingredient);
    }

    private static Ingredient externalItem(String id) {
        return Ingredient.of(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(id)), id));
    }

    private static Ingredient quartz() {
        return Ingredient.of(Items.QUARTZ);
    }

    private static void save(Consumer<FinishedRecipe> provider, String id, SpawnerModifier modifier) {
        ResourceLocation recipeId = CTNHCore.id("apotheosis/spawner/" + id);
        provider.accept(new FinishedRecipe() {

            @Override
            public void serializeRecipeData(JsonObject recipeJson) {
                recipeJson.add("conditions", conditions());
                recipeJson.add("mainhand", modifier.getMainhandInput().toJson());
                if (!modifier.getOffhandInput().isEmpty()) {
                    recipeJson.add("offhand", modifier.getOffhandInput().toJson());
                    recipeJson.addProperty("consumes_offhand", modifier.consumesOffhand());
                }
                JsonArray statChanges = new JsonArray();
                for (StatModifier<?> statModifier : modifier.getStatModifiers()) {
                    statChanges.add(encodeStatModifier(statModifier));
                }
                recipeJson.add("stat_changes", statChanges);
            }

            @Override
            public ResourceLocation getId() {
                return recipeId;
            }

            @Override
            public RecipeSerializer<?> getType() {
                return SpawnerModifier.SERIALIZER;
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
        });
    }

    private static <T> JsonObject encodeStatModifier(StatModifier<T> statModifier) {
        return statModifier.stat().getModifierCodec()
                .encodeStart(JsonOps.INSTANCE, statModifier)
                .getOrThrow(false, error -> {
                    throw new IllegalStateException("Failed to encode spawner stat modifier: " + error);
                })
                .getAsJsonObject();
    }

    private static JsonArray conditions() {
        JsonObject condition = new JsonObject();
        condition.addProperty("type", "apotheosis:module");
        condition.addProperty("module", "spawner");
        JsonArray conditions = new JsonArray();
        conditions.add(condition);
        return conditions;
    }
}
