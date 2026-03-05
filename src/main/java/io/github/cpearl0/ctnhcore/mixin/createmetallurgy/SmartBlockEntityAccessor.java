package io.github.cpearl0.ctnhcore.mixin.createmetallurgy;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BehaviourType;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(value = SmartBlockEntity.class, remap = false)
public interface SmartBlockEntityAccessor {

    @Accessor
    Map<BehaviourType<?>, BlockEntityBehaviour> getBehaviours();
}
