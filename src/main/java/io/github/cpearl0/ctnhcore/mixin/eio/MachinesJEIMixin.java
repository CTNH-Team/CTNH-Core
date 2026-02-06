package io.github.cpearl0.ctnhcore.mixin.eio;

import com.enderio.machines.common.init.MachineBlocks;
import com.enderio.machines.common.integrations.jei.MachineJEIRecipes;
import com.enderio.machines.common.integrations.jei.MachinesJEI;
import com.enderio.machines.common.integrations.jei.category.*;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = MachinesJEI.class, remap = false)
public class MachinesJEIMixin {
    /**
     * @author luckyblock
     * @reason delete AlloySmelting and sth else
     */
    @Overwrite
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack((ItemLike)MachineBlocks.ENCHANTER.get()), new RecipeType[]{EnchanterCategory.TYPE});
        registration.addRecipeCatalyst(new ItemStack((ItemLike)MachineBlocks.SLICE_AND_SPLICE.get()), new RecipeType[]{SlicingRecipeCategory.TYPE});
        registration.addRecipeCatalyst(new ItemStack((ItemLike)MachineBlocks.SOUL_BINDER.get()), new RecipeType[]{SoulBindingCategory.TYPE});
    }

    /**
     * @author luckyblock
     * @reason delete AlloySmelting
     */
    @Overwrite
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new IRecipeCategory[]{new EnchanterCategory(registration.getJeiHelpers().getGuiHelper())});
        registration.addRecipeCategories(new IRecipeCategory[]{new SlicingRecipeCategory(registration.getJeiHelpers().getGuiHelper())});
        registration.addRecipeCategories(new IRecipeCategory[]{new SoulBindingCategory(registration.getJeiHelpers().getGuiHelper())});
    }

    /**
     * @author luckyblock
     * @reason delete AlloySmelting
     */
    @Overwrite
    public void registerRecipes(IRecipeRegistration registration) {
        MachineJEIRecipes recipes = new MachineJEIRecipes();
        registration.addRecipes(EnchanterCategory.TYPE, recipes.getEnchanterRecipes());
        registration.addRecipes(SlicingRecipeCategory.TYPE, recipes.getSlicingRecipes());
        registration.addRecipes(SoulBindingCategory.TYPE, recipes.getSoulBindingRecipes());
    }
}
