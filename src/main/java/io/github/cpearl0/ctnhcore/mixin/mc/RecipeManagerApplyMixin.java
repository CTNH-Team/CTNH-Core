package io.github.cpearl0.ctnhcore.mixin.mc;

import io.github.cpearl0.ctnhcore.data.recipe.RecipeFilterProcessor;
import io.github.cpearl0.ctnhcore.data.recipe.RecipeRemoval;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;

import com.google.gson.JsonElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Mixin into RecipeManager.apply().
 * - HEAD: filters JSON-level recipes (datapack JSON files)
 * - RETURN: filters compiled recipes (dynamically generated, e.g. TConstruct melting)
 */
@Mixin(value = RecipeManager.class, priority = 1100)
public abstract class RecipeManagerApplyMixin {

    @Inject(method = "apply*", at = @At("HEAD"))
    private void ctnhcore$processRecipes(Map<ResourceLocation, JsonElement> map,
                                         ResourceManager resourceManager,
                                         ProfilerFiller profiler,
                                         CallbackInfo ci) {
        var filters = RecipeRemoval.getFilters();
        var replaceOps = RecipeRemoval.getReplaceOperations();

        if (filters.isEmpty() && replaceOps.isEmpty()) return;

        RecipeFilterProcessor.processRemovals(map);
        RecipeFilterProcessor.processReplacements(map);
    }

    @Inject(method = "apply*", at = @At("RETURN"))
    private void ctnhcore$processCompiledRecipes(Map<ResourceLocation, JsonElement> map,
                                                 ResourceManager resourceManager,
                                                 ProfilerFiller profiler,
                                                 CallbackInfo ci) {
        var filters = RecipeRemoval.getFilters();
        if (filters.isEmpty()) return;

        Map<ResourceLocation, Recipe<?>> byName = ((RecipeManagerAccessor) this).ctnhcore$getByName();
        List<ResourceLocation> toRemove = new ArrayList<>();

        for (var filter : filters) {
            String exactId = filter.getSingleExactId();
            if (exactId == null) continue;
            ResourceLocation rl = ResourceLocation.tryParse(exactId);
            if (rl != null && byName.containsKey(rl)) {
                toRemove.add(rl);
            }
        }

        for (var id : toRemove) {
            byName.remove(id);
        }
    }
}
