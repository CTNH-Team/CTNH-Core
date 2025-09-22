package io.github.cpearl0.ctnhcore.mixin.createmetallurgy;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BehaviourType;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import fr.lucreeper74.createmetallurgy.content.blocks.foundry_basin.FoundryBasinBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = FoundryBasinBlockEntity.class, remap = false)
public class FoundryBasinBlockEntityMixin {
    @Redirect(
            method = "addBehaviours",
            at = @At(
                    value = "NEW",
                    target = "(Lcom/simibubi/create/foundation/blockEntity/behaviour/BehaviourType;Lcom/simibubi/create/foundation/blockEntity/SmartBlockEntity;IIZ)Lcom/simibubi/create/foundation/blockEntity/behaviour/fluid/SmartFluidTankBehaviour;"
            )
    )
    private SmartFluidTankBehaviour modifyTankCapacity(
            BehaviourType<SmartFluidTankBehaviour> type, SmartBlockEntity be, int tanks,
            int tankCapacity, boolean enforceVariety
    ) {
        return new SmartFluidTankBehaviour(type, be, tanks, 3000, enforceVariety);
    }
}
