package io.github.cpearl0.ctnhcore.common.item;

import com.gregtechceu.gtceu.api.block.MaterialBlock;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import io.github.cpearl0.ctnhcore.common.block.MaterialTurbineRotorBlock;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import static io.github.cpearl0.ctnhcore.registry.CTNHTagPrefixes.hyperRotor;

public class MaterialTurbineRotorItem extends TurbineRotorItem {
    public MaterialTurbineRotorItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    @NotNull
    public MaterialTurbineRotorBlock getBlock() {
        return (MaterialTurbineRotorBlock) super.getBlock();
    }


    @Override
    public String getDescriptionId() {
        return getBlock().getDescriptionId();
    }

    @Override
    public String getDescriptionId(ItemStack stack) {
        return getDescriptionId();
    }

    @Override
    public Component getDescription() {
        return getBlock().getName();
    }

    @Override
    public Component getName(ItemStack stack) {
        return getDescription();
    }
}
