package io.github.cpearl0.ctnhcore.common.block;

import com.gregtechceu.gtceu.api.block.AppearanceBlock;
import io.github.cpearl0.ctnhcore.registry.CTNHBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class RuneBlock extends AppearanceBlock {
    public RuneBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockState getBlockAppearance(BlockState state, BlockAndTintGetter level, BlockPos pos, Direction side, BlockState sourceState, BlockPos sourcePos) {
        return CTNHBlocks.RUNE_STONE_0.getDefaultState();
    }
}
