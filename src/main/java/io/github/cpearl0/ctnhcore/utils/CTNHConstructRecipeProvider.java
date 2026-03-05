package io.github.cpearl0.ctnhcore.utils;

import io.github.cpearl0.ctnhcore.CTNHCore;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import slimeknights.mantle.recipe.data.IRecipeHelper;

public abstract class CTNHConstructRecipeProvider extends RecipeProvider implements IConditionBuilder, IRecipeHelper {

    public CTNHConstructRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    public ResourceLocation location(String id) {
        return CTNHCore.asResource(getType() + "/" + id);
    }

    @Override
    public String getName() {
        return "CTNH Recipe[" + getType() + "]";
    }

    @Override
    public String getModId() {
        return CTNHCore.MODID;
    }

    public abstract String getType();
}
