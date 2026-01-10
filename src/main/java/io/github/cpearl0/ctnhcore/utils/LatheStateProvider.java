package io.github.cpearl0.ctnhcore.utils;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

import com.negodya1.vintageimprovements.VintageBlocks;

public class LatheStateProvider {

    public static BlockState LATHE_WEST;
    public static BlockState LATHE_EAST;
    static {
        for (var prop : VintageBlocks.LATHE_ROTATING.getDefaultState().getValues().entrySet()) {
            if (prop.getKey() instanceof DirectionProperty facingProp) {
                LATHE_WEST = VintageBlocks.LATHE_ROTATING.getDefaultState().setValue(facingProp, Direction.WEST);
                LATHE_EAST = VintageBlocks.LATHE_ROTATING.getDefaultState().setValue(facingProp, Direction.EAST);
                break;
            }
        }
    }
}
