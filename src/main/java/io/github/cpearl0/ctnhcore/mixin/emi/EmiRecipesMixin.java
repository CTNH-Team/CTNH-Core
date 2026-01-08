package io.github.cpearl0.ctnhcore.mixin.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.recipe.EmiRecipeManager;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.data.EmiData;
import dev.emi.emi.registry.EmiRecipes;
import dev.emi.emi.runtime.EmiLog;
import io.github.cpearl0.ctnhcore.integration.emi.FastRecipeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.Map;

@Mixin(value = EmiRecipes.class, remap = false)
public class EmiRecipesMixin {
    @Shadow
    public static EmiRecipeManager manager;

    @Shadow
    public static List<EmiRecipeCategory> categories;

    @Shadow
    private static Map<EmiRecipeCategory, List<EmiIngredient>> workstations;

    @Shadow
    private static List<EmiRecipe> recipes;

    /**
     * @author
     * @reason
     */
    @Overwrite
    public static void bake() {
        long start = System.currentTimeMillis();
        manager = new FastRecipeManager(categories, workstations, recipes);
        EmiLog.info(
                "Fast baked recipes in "
                        + (System.currentTimeMillis() - start) + "ms"
        );
    }
}
