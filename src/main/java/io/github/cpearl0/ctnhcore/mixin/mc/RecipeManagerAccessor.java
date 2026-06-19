// RecipeManagerAccessor.java
package io.github.cpearl0.ctnhcore.mixin.mc;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(RecipeManager.class)
public interface RecipeManagerAccessor {

    @Accessor("byName")
    Map<ResourceLocation, Recipe<?>> ctnhcore$getByName();
}
