package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.ScalableReservoirComputingMachine;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import static io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.ScalableReservoirComputingMachine.recipes;

public class ScalableReservoirComputingRecipes {
    public static void init(){
        register(EntityType.PLAYER,256,1000);
        register(EntityType.SHEEP,128,500);
    }
    @SuppressWarnings("SameParameterValue")
    static void register(EntityType<? extends LivingEntity> entity, int computation, int duration){
        recipes.put(entity, new ScalableReservoirComputingMachine.SacrificeValue(computation, duration));
    }
}
