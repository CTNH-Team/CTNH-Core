package io.github.cpearl0.ctnhcore.mixin.createmetallurgy;

import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import fr.lucreeper74.createmetallurgy.content.blocks.foundry_basin.FoundryBasinBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.List;

@Mixin(value = FoundryBasinBlockEntity.class, remap = false)
public abstract class FoundryBasinBlockEntityMixin extends BasinBlockEntity {

    public FoundryBasinBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        super.addBehaviours(behaviours);
        inputTank = new SmartFluidTankBehaviour(SmartFluidTankBehaviour.INPUT, this, 4, 3000, true);
        outputTank = inputTank;
        behaviours.add(inputTank);
        ((SmartBlockEntityAccessor)this).getBehaviours().put(SmartFluidTankBehaviour.OUTPUT, inputTank);
        this.fluidCapability = LazyOptional.of(() -> {
            LazyOptional<? extends IFluidHandler> inputCap = inputTank.getCapability();
            return inputCap.orElse(null);
        });
    }
}
