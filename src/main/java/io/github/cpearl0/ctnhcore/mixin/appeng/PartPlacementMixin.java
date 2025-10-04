package io.github.cpearl0.ctnhcore.mixin.appeng;

import appeng.api.parts.IPart;
import appeng.api.parts.IPartItem;
import appeng.parts.PartPlacement;
import appeng.parts.crafting.PatternProviderPart;
import com.gregtechceu.gtceu.api.blockentity.MetaMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PartPlacement.class)
public class PartPlacementMixin {
    @Inject(
            method = "placePart",
            at = @At("RETURN"),
            remap = false
    )
    private static <T extends IPart> void onPartPlaced(
            @Nullable Player player,
            Level level,
            IPartItem<T> partItem,
            @Nullable CompoundTag configTag,
            BlockPos pos,
            Direction side,
            CallbackInfoReturnable<T> cir) {
        T placedPart = cir.getReturnValue();
        if (!(placedPart instanceof PatternProviderPart) || level.isClientSide()) {
            return;
        }

        BlockPos targetPos = pos.relative(side);
        BlockEntity be = level.getBlockEntity(targetPos);
        System.out.println(targetPos);
        if(be instanceof MetaMachineBlockEntity metaMachineBlockEntity
        && metaMachineBlockEntity.getMetaMachine() instanceof SimpleTieredMachine tieredMachine)
        {
            tieredMachine.setAutoOutputFluids(true);
            tieredMachine.setAutoOutputItems(true);
            tieredMachine.setOutputFacingFluids(side.getOpposite());
            tieredMachine.setOutputFacingItems(side.getOpposite());
        }

    }

}
