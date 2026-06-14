package io.github.cpearl0.ctnhcore.mixin.mc;

import io.github.cpearl0.ctnhcore.data.recipe.RecipeFilterProcessor;
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
 * Mixin into RecipeManager.apply() — 与 KubeJS RecipeManagerMixin 完全一致的介入阶段。
 * <p>
 * Tag 数据通过 {@code TagManagerCache} 获取（与 KubeJS 的 TagContext.fromLoadResult 原理一致）：
 * ReloadableServerResources 构造时保存引用 → RecipeManager.apply HEAD 时从 TagManager.getResult() 缓存。
 * <p>
 * 所有处理逻辑都在 {@code RecipeFilterProcessor} 中（非 mixin 包），
 * 以避免 Mixin 框架抛出 {@code IllegalClassLoadError}。
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
}
