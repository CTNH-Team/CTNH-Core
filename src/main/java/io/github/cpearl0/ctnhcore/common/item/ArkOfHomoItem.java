package io.github.cpearl0.ctnhcore.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ArkOfHomoItem extends PickaxeItem {

    private static final Tier CUSTOM_TIER = new Tier() {

        @Override
        public int getUses() {
            return 114514;
        }

        @Override
        public float getSpeed() {
            return 100.0f;
        }

        @Override
        public float getAttackDamageBonus() {
            return 1595.0f;
        }

        @Override
        public int getLevel() {
            return 9;
        }

        @Override
        public int getEnchantmentValue() {
            return 15;
        }

        @Override
        public @NotNull Ingredient getRepairIngredient() {
            return Ingredient.EMPTY;
        }
    };

    public ArkOfHomoItem(Properties properties) {
        super(CUSTOM_TIER, 0, -2.4f, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(Component.literal("§m你的旅程的物理的顶点...§r\n以上均没有实现\n这里是工业包..."));
    }
}
