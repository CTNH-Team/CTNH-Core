package io.github.cpearl0.ctnhcore.utils;


import com.lowdragmc.lowdraglib.utils.BlockInfo;

import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;

public class OrientedItem {
    public final ItemStack itemStack;
    public final FluidState fluidState;
    public final Direction facing; // 可能为null

    public OrientedItem(ItemStack itemStack, FluidState fluidState, Direction facing) {
        this.itemStack = itemStack;
        this.fluidState = fluidState;
        this.facing = facing;
    }

    public static OrientedItem createOrientedItem(BlockInfo info) {
        var state = info.getBlockState();
        Direction facing = null;
        if(state.liquid())
        {
            return new OrientedItem(ItemStack.EMPTY, state.getFluidState(), facing);

        }
        else
        {
            if (state.hasProperty(BlockStateProperties.FACING)) {
                facing = state.getValue(BlockStateProperties.FACING);
            }
            else if (state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            }
            else if (state.hasProperty(BlockStateProperties.AXIS)) {
                facing = switch(state.getValue(BlockStateProperties.AXIS)){
                    case X -> Direction.EAST;
                    case Y -> Direction.UP;
                    case Z -> Direction.SOUTH;
                };
            }
            return new OrientedItem(info.getItemStackForm(), null, facing);
        }


    }
}
