package io.github.cpearl0.ctnhcore.mixin.vintageimprovements;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import com.negodya1.vintageimprovements.content.kinetics.lathe.LatheMovingBlock;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

@Mixin(value = LatheMovingBlock.class)
public class LatheMovingBlockMixin implements IWrenchable {

    @Key("block.vintageimprovements.lathe_moving")
    @EN("Lathe")
    @CN("车床")
    private static Lang latheMovingName;

    /**
     * @author lucky block
     * @reason to fix lathe stack number in pattern preview
     */
    @Overwrite
    public ItemStack getCloneItemStack(BlockGetter pLevel, BlockPos pPos, BlockState pState) {
        return ItemStack.EMPTY;
    }
}
