package io.github.cpearl0.ctnhcore.common.item;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.component.IAddInformation;
import com.gregtechceu.gtceu.api.item.component.ICustomDescriptionId;
import com.gregtechceu.gtceu.client.renderer.item.TagPrefixItemRenderer;
import com.lowdragmc.lowdraglib.Platform;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TagPrefixBehavior implements IAddInformation, ICustomDescriptionId {
    private TagPrefix tagPrefix;
    private Material material;
    public TagPrefixBehavior(TagPrefix tagPrefix, Material material) {
        this.tagPrefix = tagPrefix;
        this.material = material;
    }
    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        if (tagPrefix.tooltip() != null) {
            tagPrefix.tooltip().accept(material, list);
        }
    }

    @Override
    public void onAttached(Item item) {
        if (Platform.isClient()) {
            TagPrefixItemRenderer.create(
                    item, tagPrefix.materialIconType(), material.getMaterialIconSet()
            );
        }
    }

    @Override
    public @Nullable Component getItemName(ItemStack stack) {
        return tagPrefix.getLocalizedName(material);
    }
    @OnlyIn(Dist.CLIENT)
    public static ItemColor tintColor() {
        return (itemStack, index) -> {
            var behavior = getBehaviour(itemStack);
            if (behavior != null) {
                return behavior.material.getLayerARGB(index);
            }
            return -1;
        };
    }

    private static TagPrefixBehavior getBehaviour(ItemStack itemStack) {
        var item = itemStack.getItem();
        if (item instanceof ComponentItem componentItem) {
            for (var component : componentItem.getComponents()) {
                if (component instanceof TagPrefixBehavior tagPrefixBehavior) {
                    return tagPrefixBehavior;
                }
            }
        }
        return null;
    }
}
