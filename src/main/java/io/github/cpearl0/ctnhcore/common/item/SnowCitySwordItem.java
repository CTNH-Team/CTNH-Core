package io.github.cpearl0.ctnhcore.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SnowCitySwordItem extends SwordItem {

    private static final Tier CUSTOM_TIER = new Tier() {

        @Override
        public int getUses() {
            return 114514;
        }

        @Override
        public float getSpeed() {
            return 2.0f;
        }

        @Override
        public float getAttackDamageBonus() {
            return 13.0f;
        }

        @Override
        public int getLevel() {
            return 10;
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

    public SnowCitySwordItem(Properties properties) {
        super(CUSTOM_TIER, 0, -2.4f, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(Component.literal("雪城的赐福,无敌的扳手！"));
    }
}
