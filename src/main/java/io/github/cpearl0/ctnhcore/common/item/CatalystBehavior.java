package io.github.cpearl0.ctnhcore.common.item;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.component.IAddInformation;
import com.gregtechceu.gtceu.api.item.component.IDurabilityBar;
import com.gregtechceu.gtceu.api.item.component.IItemComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class CatalystBehavior implements IItemComponent, IDurabilityBar, IAddInformation {
    private int maxDurability;
    public CatalystBehavior(int maxDurability) {
        this.maxDurability = maxDurability;
    }
    private CompoundTag getCatalystStatsTag(ItemStack itemStack) {
        return itemStack.getTagElement("CTNH.CatalystStats");
    }

    private CompoundTag getOrCreateCatalystStatsTag(ItemStack itemStack) {
        return itemStack.getOrCreateTagElement("CTNH.CatalystStats");
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        var damage = this.getDamage(itemStack);
        list.add(
                Component.translatable(
                        "metaitem.tool.tooltip.durability", maxDurability - damage, maxDurability
                )
        );
    }

    @Override
    public int getMaxDurability(@NotNull ItemStack itemStack) {
        return maxDurability;
    }

    public int getDamage(@Nullable ItemStack itemStack) {
        CompoundTag compound = null;
        if (itemStack != null) {
            compound = getCatalystStatsTag(itemStack);
        }
        if (compound == null || !compound.contains("Damage", Tag.TAG_ANY_NUMERIC)) {
            return 0;
        }
        return compound.getInt("Damage");
    }

    public void setDamage(@Nullable ItemStack itemStack, int damage) {
        CompoundTag compound = null;
        if (itemStack != null) {
            compound = getOrCreateCatalystStatsTag(itemStack);
        }
        if (compound != null) {
            compound.putInt("Damage", min(maxDurability, damage));
        }
    }

    public void applyDamage(@NotNull ItemStack itemStack, int damageApplied) {
        if (getMaxDurability(itemStack) < 1){
            maxDurability = getMaxDurability(itemStack);
        return;
        }
        var resultDamage = getDamage(itemStack) + damageApplied;
        if (resultDamage >= maxDurability) {
            itemStack.shrink(1);
        } else {
            setDamage(itemStack, resultDamage);
        }
    }

    public int getDurability(@NotNull ItemStack itemStack) {
        return (int) max((getMaxDurability(itemStack) - getDamage(itemStack)), 0.0);
    }

    @Override
    public float getDurabilityForDisplay(@NotNull ItemStack itemStack) {
        return (float) getDurability(itemStack) / getMaxDurability(itemStack);
    }

    @Override
    public boolean showEmptyBar(@NotNull ItemStack itemStack) {
        return getMaxDurability(itemStack) > 0;
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack itemStack) {
        return getMaxDurability(itemStack) > 0;
    }
    public static CatalystBehavior getBehaviour(ItemStack itemStack) {
        var item = itemStack.getItem();
        if (item instanceof ComponentItem componentItem) {
            for (var component : componentItem.getComponents()) {
                if (component instanceof CatalystBehavior behavior) {
                    return behavior;
                }
            }
        }
        return null;
    }
}
