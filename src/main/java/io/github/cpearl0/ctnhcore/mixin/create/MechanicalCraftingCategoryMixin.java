package io.github.cpearl0.ctnhcore.mixin.create;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;

import com.simibubi.create.compat.jei.category.MechanicalCraftingCategory;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.recipe.IFocusGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 强制 mechanical crafting (automatic_shaped) 配方的所有输入 count=1。
 * 某些 Ingredient.getItems() 返回的 ItemStack count > 1（如 sugar→16, flint→12），
 * 导致 EMI/JEI 显示错误的输入数量。此处统一规范化为 1。
 */
@Mixin(value = MechanicalCraftingCategory.class, remap = false)
public class MechanicalCraftingCategoryMixin {

    @Inject(method = "setRecipe", at = @At("HEAD"), remap = false)
    private void ctnhcore$normalizeInputAmount(IRecipeLayoutBuilder builder, CraftingRecipe recipe, IFocusGroup focuses,
                                               CallbackInfo ci) {
        for (Ingredient ingredient : recipe.getIngredients()) {
            for (ItemStack stack : ingredient.getItems()) {
                stack.setCount(1);
            }
        }
    }
}
