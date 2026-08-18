package io.github.cpearl0.ctnhcore.integration.creatediesel;

import com.jesz.createdieselgenerators.content.distillation.DistillationRecipe;

public final class DistillationCategoryLayout {

    private static final int MAX_OUTPUTS = 6;
    private static final int LAYER_SPACING = 23;

    private DistillationCategoryLayout() {}

    public static int getVerticalOffset(DistillationRecipe recipe) {
        int outputCount = Math.min(MAX_OUTPUTS, recipe.getFluidResults().size());
        int unusedLayers = MAX_OUTPUTS - outputCount;
        return -(unusedLayers * LAYER_SPACING / 2);
    }

    public static int getDisplayHeight(DistillationRecipe recipe, int originalHeight) {
        return Math.max(1, originalHeight + getVerticalOffset(recipe));
    }
}
