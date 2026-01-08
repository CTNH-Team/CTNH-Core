package io.github.cpearl0.ctnhcore.registry;

import com.gregtechceu.gtceu.api.registry.GTRegistries;

import com.lowdragmc.lowdraglib.gui.texture.ResourceTexture;

import static io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes.PRECISION_ASSEMBLY_RECIPES;

public class CTNHRecipeCategories {

    public static void init() {
        GTRegistries.RECIPE_CATEGORIES.get(PRECISION_ASSEMBLY_RECIPES.registryName)
                .setIcon(new ResourceTexture("ctnhcore:textures/item/quark_core.png"));
    }
}
