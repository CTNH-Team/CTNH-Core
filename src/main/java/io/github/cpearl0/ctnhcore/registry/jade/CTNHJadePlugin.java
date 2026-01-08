package io.github.cpearl0.ctnhcore.registry.jade;

import io.github.cpearl0.ctnhcore.api.jade.MultithreadRecipeLogicProvider;
import io.github.cpearl0.ctnhcore.api.jade.ThreadStatusProvider;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

import tech.vixhentx.mcmod.ctnhlib.jade.JadePriorityManager;

public class CTNHJadePlugin {

    public static void init() {
        // 手动去除gt的组件以避免分不清哪些是新添加的
        // JadePriorityManager.unregisterBlockComponent("recipe_logic_component");
        // JadePriorityManager.unregisterBlockData("recipe_logic_data");

        JadePriorityManager.registerBlockComponent(
                new MultithreadRecipeLogicProvider(),
                Block.class,
                1350,
                "multithread_recipe_logic_data");
        JadePriorityManager.registerBlockData(
                new MultithreadRecipeLogicProvider(),
                BlockEntity.class,
                1350,
                "multithread_recipe_logic_component");

        JadePriorityManager.registerBlockComponent(
                new ThreadStatusProvider(),
                Block.class,
                1250,
                "thread_status_data");
        JadePriorityManager.registerBlockData(
                new ThreadStatusProvider(),
                BlockEntity.class,
                1250,
                "thread_status_component");
    }
}
