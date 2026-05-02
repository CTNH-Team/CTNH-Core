package io.github.cpearl0.ctnhcore.common.item;

import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import org.jetbrains.annotations.NotNull;

public class ArkOfHomoItem extends PickaxeItem {
    private static final Tier CUSTOM_TIER = new Tier() {
        @Override public int getUses() { return 114514; }
        @Override public float getSpeed() { return 100.0f; }
        @Override public float getAttackDamageBonus() { return 1595.0f; }
        @Override public int getLevel() { return 9; }
        @Override public int getEnchantmentValue() { return 15; }
        @Override public @NotNull Ingredient getRepairIngredient() { return Ingredient.EMPTY; }
    };

    public ArkOfHomoItem(Properties properties) {
        super(CUSTOM_TIER, 0, -2.4f, properties);
    }
}
