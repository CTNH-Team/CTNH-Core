package io.github.cpearl0.ctnhcore.mixin.mc;

import io.github.cpearl0.ctnhcore.data.recipe.RecipeRemoval;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.RecipeManager;

import com.google.gson.JsonElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

/**
 * Removes matching datapack recipe entries before RecipeManager parses them.
 */
@Mixin(value = RecipeManager.class, priority = 1100)
public abstract class RecipeManagerApplyMixin {

    @Inject(method = "apply*", at = @At("HEAD"))
    private void ctnhcore$removeRecipes(Map<ResourceLocation, JsonElement> map,
                                        ResourceManager resourceManager,
                                        ProfilerFiller profiler,
                                        CallbackInfo ci) {
        var filters = RecipeRemoval.getFilters();
        if (filters.isEmpty()) return;

        map.keySet().removeIf(id -> filters.stream().anyMatch(filter -> filter.matches(id)));
    }
}
