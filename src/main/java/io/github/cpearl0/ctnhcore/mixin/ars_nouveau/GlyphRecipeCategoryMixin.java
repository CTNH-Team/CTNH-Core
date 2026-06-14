package io.github.cpearl0.ctnhcore.mixin.ars_nouveau;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import com.hollingsworth.arsnouveau.client.jei.MultiInputCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * 修复 Ars Nouveau GlyphRecipe 中输入物品数量被错误放大的问题。
 *
 * <p>
 * {@link MultiInputCategory#setRecipe} 通过 {@code addIngredients(Ingredient)}
 * 添加输入，TMRV 会从 {@code Ingredient.getItems()} 返回的 ItemStack count 推断 amount，
 * Forge 的某些 Ingredient 实现的 getItems() 返回 count > 1（Forge bug）。
 *
 * <p>
 * 此 Mixin 通过 {@code @Redirect} 拦截 {@code addIngredients} 调用，
 * 在调用前规范化 Ingredient 中每个 ItemStack 的 count 为 1。
 */
@Mixin(value = MultiInputCategory.class, remap = false)
public class GlyphRecipeCategoryMixin {

    @Redirect(
              method = "setRecipe",
              at = @At(
                       value = "INVOKE",
                       target = "Lmezz/jei/api/gui/builder/IRecipeSlotBuilder;addIngredients(Lnet/minecraft/world/item/crafting/Ingredient;)Lmezz/jei/api/gui/builder/IRecipeSlotBuilder;"))
    private mezz.jei.api.gui.builder.IRecipeSlotBuilder ctnhcore$normalizeInput(
                                                                                mezz.jei.api.gui.builder.IRecipeSlotBuilder instance,
                                                                                Ingredient ingredient) {
        return instance.addIngredients(normalizeIngredient(ingredient));
    }

    private static Ingredient normalizeIngredient(Ingredient ingredient) {
        if (ingredient == null) return ingredient;
        ItemStack[] stacks = ingredient.getItems();
        boolean needsNormalize = false;
        for (ItemStack stack : stacks) {
            if (stack.getCount() > 1) {
                needsNormalize = true;
                break;
            }
        }
        if (!needsNormalize) return ingredient;
        for (ItemStack stack : stacks) {
            stack.setCount(1);
        }
        return Ingredient.of(stacks);
    }
}
