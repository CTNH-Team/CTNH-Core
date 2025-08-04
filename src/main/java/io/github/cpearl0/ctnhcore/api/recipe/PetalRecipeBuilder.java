package io.github.cpearl0.ctnhcore.api.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.recipe.GTRecipeSerializer;
import com.gregtechceu.gtceu.api.recipe.ResearchData;
import com.gregtechceu.gtceu.api.recipe.category.GTRecipeCategory;
import com.gregtechceu.gtceu.common.recipe.condition.ResearchCondition;
import io.github.cpearl0.ctnhcore.CTNHCore;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.common.crafting.BotaniaRecipeTypes;
import vazkii.botania.common.helper.ItemNBTHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class PetalRecipeBuilder {
    private final List<Ingredient> inputs = new ArrayList<>();
    private ItemStack output;
    private Ingredient reagent;
    private ResourceLocation id;
    public PetalRecipeBuilder(String name) {
        id = CTNHCore.id(name);
    }
    public PetalRecipeBuilder input(ItemStack itemStack) {
        inputs.add(Ingredient.of(itemStack));
        return this;
    }
    public PetalRecipeBuilder input(TagKey<Item> tagKey) {
        inputs.add(Ingredient.of(tagKey));
        return this;
    }
    public PetalRecipeBuilder input(Ingredient ingredient) {
        inputs.add(ingredient);
        return this;
    }
    public PetalRecipeBuilder input(Ingredient... ingredients) {
        Arrays.stream(ingredients).forEach(ingredient -> inputs.add(ingredient));
        return this;
    }
    public PetalRecipeBuilder output(ItemStack itemStack) {
        output = itemStack;
        return this;
    }
    public PetalRecipeBuilder reagent(ItemStack itemStack) {
        reagent = Ingredient.of(itemStack);
        return this;
    }
    public static PetalRecipeBuilder builder(String name) {
        return new PetalRecipeBuilder(name);
    }
    public void toJson(JsonObject json) {
        json.add("output", ItemNBTHelper.serializeStack(this.output));
        JsonArray ingredients = new JsonArray();
        List<Ingredient> inputs = this.inputs;
        int len = inputs.size();

        for(int var5 = 0; var5 < len; ++var5) {
            Ingredient ingr = inputs.get(var5);
            ingredients.add(ingr.toJson());
        }

        json.add("reagent", this.reagent.toJson());
        json.add("ingredients", ingredients);
    }
    public FinishedRecipe build() {
        return new FinishedRecipe() {

            @Override
            public void serializeRecipeData(JsonObject pJson) {
                toJson(pJson);
            }

            @Override
            public ResourceLocation getId() {
                return ResourceLocation.tryBuild(id.getNamespace(), "petal_apothecary" + "/" + id.getPath());
            }

            @Override
            public RecipeSerializer<?> getType() {
                return BotaniaRecipeTypes.PETAL_SERIALIZER;
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
        };
    }
    public void save(Consumer<FinishedRecipe> consumer) {
        consumer.accept(build());
    }
}
