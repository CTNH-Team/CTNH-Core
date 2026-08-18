package io.github.cpearl0.ctnhcore.mixin.creatediesel;

import io.github.cpearl0.ctnhcore.integration.creatediesel.DistillationCategoryLayout;

import net.minecraft.client.gui.GuiGraphics;

import com.jesz.createdieselgenerators.compat.jei.DistillationCategory;
import com.jesz.createdieselgenerators.content.distillation.DistillationRecipe;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = DistillationCategory.class, remap = false)
public class DistillationCategoryMixin {

    @Unique
    private int ctnhcore$verticalOffset;

    @Inject(
            method = "setRecipe(Lmezz/jei/api/gui/builder/IRecipeLayoutBuilder;Lcom/jesz/createdieselgenerators/content/distillation/DistillationRecipe;Lmezz/jei/api/recipe/IFocusGroup;)V",
            at = @At("HEAD"))
    private void ctnhcore$centerRecipeSlots(IRecipeLayoutBuilder builder, DistillationRecipe recipe,
                                            IFocusGroup focuses, CallbackInfo ci) {
        ctnhcore$updateVerticalOffset(recipe);
    }

    @ModifyConstant(
                    method = "setRecipe(Lmezz/jei/api/gui/builder/IRecipeLayoutBuilder;Lcom/jesz/createdieselgenerators/content/distillation/DistillationRecipe;Lmezz/jei/api/recipe/IFocusGroup;)V",
                    constant = {
                            @Constant(intValue = 145),
                            @Constant(intValue = 150),
                            @Constant(intValue = 171)
                    })
    private int ctnhcore$shiftRecipeSlotY(int original) {
        return original + ctnhcore$verticalOffset;
    }

    @Inject(
            method = "draw(Lcom/jesz/createdieselgenerators/content/distillation/DistillationRecipe;Lmezz/jei/api/gui/ingredient/IRecipeSlotsView;Lnet/minecraft/client/gui/GuiGraphics;DD)V",
            at = @At("HEAD"))
    private void ctnhcore$centerRecipeGraphics(DistillationRecipe recipe, IRecipeSlotsView recipeSlotsView,
                                               GuiGraphics graphics, double mouseX, double mouseY, CallbackInfo ci) {
        ctnhcore$updateVerticalOffset(recipe);
    }

    @ModifyConstant(
                    method = "draw(Lcom/jesz/createdieselgenerators/content/distillation/DistillationRecipe;Lmezz/jei/api/gui/ingredient/IRecipeSlotsView;Lnet/minecraft/client/gui/GuiGraphics;DD)V",
                    constant = {
                            @Constant(intValue = 142),
                            @Constant(intValue = 150),
                            @Constant(intValue = 153),
                            @Constant(intValue = 170),
                            @Constant(intValue = 176)
                    })
    private int ctnhcore$shiftRecipeGraphicY(int original) {
        return original + ctnhcore$verticalOffset;
    }

    @Unique
    private void ctnhcore$updateVerticalOffset(DistillationRecipe recipe) {
        ctnhcore$verticalOffset = DistillationCategoryLayout.getVerticalOffset(recipe);
    }
}
