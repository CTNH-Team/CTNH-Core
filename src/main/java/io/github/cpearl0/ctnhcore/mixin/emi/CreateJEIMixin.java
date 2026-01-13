package io.github.cpearl0.ctnhcore.mixin.emi;

import io.github.cpearl0.ctnhcore.utils.emi.CreateJeiRecipeCache;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

import com.simibubi.create.compat.jei.CreateJEI;
import mezz.jei.api.registration.IExtraIngredientRegistration;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("all")
@Mixin(value = CreateJEI.class, remap = false)
public class CreateJEIMixin {

    @Inject(method = "loadCategories", at = @At("HEAD"), remap = false)
    void invalidCache(CallbackInfo ci) {
        CreateJeiRecipeCache.invalidate();
    }

    @Overwrite
    public static void consumeAllRecipes(Consumer<Recipe<?>> consumer) {
        for (Recipe<?> recipe : CreateJeiRecipeCache.allRecipes()) {
            consumer.accept(recipe);
        }
    }

    @Overwrite
    public static <T extends Recipe<?>> void consumeTypedRecipes(Consumer<T> consumer, RecipeType<?> type) {
        for (Recipe<?> recipe : CreateJeiRecipeCache.recipesByType(type)) {
            consumer.accept((T) recipe);
        }
    }

    @Overwrite
    public static List<Recipe<?>> getTypedRecipes(RecipeType<?> type) {
        return new ArrayList<>(CreateJeiRecipeCache.recipesByType(type));
    }

    @Overwrite
    public void registerExtraIngredients(IExtraIngredientRegistration registration) {}

    @Mixin(targets = "com.simibubi.create.compat.jei.CreateJEI$CategoryBuilder", remap = false)
    public static abstract class CategoryBuilderMixin<T extends Recipe<?>> {

        @Shadow
        @Final
        private List<Consumer<List<T>>> recipeListConsumers;

        @Inject(method = "addTypedRecipesExcluding", at = @At("HEAD"), cancellable = true)
        public void addTypedRecipesExcluding(Supplier<RecipeType<? extends T>> recipeType,
                                             Supplier<RecipeType<? extends T>> excluded,
                                             CallbackInfoReturnable<Object> cir) {
            recipeListConsumers.add(
                    recipes -> {
                        var excludedRecipes = CreateJeiRecipeCache.recipesByType(excluded.get());

                        Set<Item> excludedInputs = new HashSet<>();
                        for (Recipe<?> r : excludedRecipes) {
                            if (!r.getIngredients().isEmpty()) {
                                for (ItemStack stack : r.getIngredients().get(0).getItems()) {
                                    excludedInputs.add(stack.getItem());
                                }
                            }
                        }

                        for (Recipe<?> recipe : CreateJeiRecipeCache.recipesByType(recipeType.get())) {
                            if (recipe.getIngredients().isEmpty())
                                continue;

                            ItemStack[] inputs = recipe.getIngredients().get(0).getItems();
                            if (inputs.length == 0)
                                continue;

                            if (!excludedInputs.contains(inputs[0].getItem())) {
                                recipes.add((T) recipe);
                            }
                        }
                    });

            cir.setReturnValue(this);
        }
    }
}
