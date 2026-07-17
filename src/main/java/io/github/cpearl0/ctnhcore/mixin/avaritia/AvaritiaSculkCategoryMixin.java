package io.github.cpearl0.ctnhcore.mixin.avaritia;

import committee.nova.mods.avaritia.init.compat.emi.category.tables.SculkCraftingTableCategory;
import net.minecraft.world.item.crafting.Ingredient;

import dev.emi.emi.api.stack.EmiIngredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * 修复 Avaritia SculkCraftingTableCategory 中输入物品数量被错误放大的问题。
 *
 * <p>
 * 该 Category 使用 EMI 原生 API（{@code EmiIngredient.of(Ingredient)}）创建输入，
 * 而 EMI 会从 {@code Ingredient.getItems()} 返回的 ItemStack count 推断 amount。
 * Forge 的某些 Ingredient 实现的 getItems() 返回 count > 1（Forge bug），
 * 导致显示的输入数量被错误放大（如 soul_soil→16）。
 *
 * <p>
 * 此 Mixin 通过 {@code @Redirect} 拦截 {@code EmiIngredient.of(Ingredient)} 调用，
 * 强制将返回的 EmiIngredient 的 amount 设为 1。
 */
@Mixin(
       value = SculkCraftingTableCategory.class,
       remap = false)
public class AvaritiaSculkCategoryMixin {

    @Redirect(
              method = { "addWidgets", "shapelessRecipe", "getInputs" },
              at = @At(
                       value = "INVOKE",
                       target = "Ldev/emi/emi/api/stack/EmiIngredient;of(Lnet/minecraft/world/item/crafting/Ingredient;)Ldev/emi/emi/api/stack/EmiIngredient;"))
    private EmiIngredient ctnhcore$forceInputAmountOne(Ingredient ingredient) {
        return EmiIngredient.of(ingredient).copy().setAmount(1L);
    }
}
