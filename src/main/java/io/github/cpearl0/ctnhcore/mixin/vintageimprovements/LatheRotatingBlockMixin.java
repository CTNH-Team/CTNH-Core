package io.github.cpearl0.ctnhcore.mixin.vintageimprovements;

import com.negodya1.vintageimprovements.content.kinetics.lathe.LatheRotatingBlock;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = LatheRotatingBlock.class, remap = false)
public class LatheRotatingBlockMixin implements IWrenchable {
    @Override
    public InteractionResult onWrenched(BlockState state, UseOnContext context) {
        return InteractionResult.PASS;
    }
}
