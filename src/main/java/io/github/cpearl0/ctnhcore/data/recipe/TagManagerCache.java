package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.mixin.mc.ReloadableServerResourcesAccessor;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.tags.TagManager;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 缓存 TagManager.LoadResult，提供 tag → item ID 集合的查询。
 * <p>
 * 与 KubeJS 的 {@code TagContext.fromLoadResult} 原理完全一致：
 * 1. 在 {@code ReloadableServerResourcesMixin} 中保存 {@code ReloadableServerResources} 引用
 * 2. 在 {@code RecipeManagerApplyMixin} HEAD 阶段，通过保存的引用调用 {@code tagManager.getResult()}
 * 3. 将 LoadResult 转为查询表，用于 tag 展开
 */
public class TagManagerCache {

    /** 保存 ReloadableServerResources 引用，由 ReloadableServerResourcesMixin 写入 */
    private static volatile ReloadableServerResources serverResources;

    // key: ResourceKey<Registry<?>> → value: Map<ResourceLocation, Collection<Holder<?>>>
    private static volatile Map<ResourceKey<?>, Map<ResourceLocation, Collection<Holder<?>>>> tagData = Map.of();

    /**
     * 保存 ReloadableServerResources 引用。由 ReloadableServerResourcesMixin 调用。
     */
    public static void setServerResources(ReloadableServerResources resources) {
        serverResources = resources;
        CTNHCore.LOGGER.info("[TagManagerCache] Saved ReloadableServerResources reference");
    }

    /**
     * 从 ReloadableServerResources 的 TagManager 中缓存 tag 数据。
     * 在 RecipeManager.apply HEAD 阶段调用（此时 TagManager 已完成加载）。
     */
    public static void ensureCached() {
        if (!tagData.isEmpty()) return; // 已缓存
        if (serverResources == null) {
            CTNHCore.LOGGER
                    .warn("[TagManagerCache] ReloadableServerResources not available, tag expansion will not work");
            return;
        }
        try {
            var tagManager = ((ReloadableServerResourcesAccessor) serverResources).ctnhcore$getTagManager();
            cache(tagManager.getResult());
        } catch (Exception e) {
            CTNHCore.LOGGER.error("[TagManagerCache] Failed to cache tag data", e);
        }
    }

    /**
     * 缓存 TagManager 的 LoadResult 列表。
     */
    public static void cache(List<TagManager.LoadResult<?>> results) {
        tagData = results.stream()
                .collect(Collectors.toMap(
                        result -> castKey(result.key()),
                        result -> castTags(result.tags())));
        CTNHCore.LOGGER.info("[TagManagerCache] Cached {} registry tag groups", tagData.size());
    }

    /**
     * 将 tag 展开为 item ID 集合。
     *
     * @param tagName 形如 "forge:rods" 的 tag 名（不含 # 前缀）
     * @return 该 tag 中所有 item 的 ID 集合
     */
    public static Set<String> expandTagToItemIds(String tagName) {
        ensureCached();

        var itemTags = tagData.get(Registries.ITEM);
        if (itemTags == null) {
            CTNHCore.LOGGER.warn("[TagManagerCache] No item tag data available for '{}'", tagName);
            return Set.of();
        }

        ResourceLocation tagRl = ResourceLocation.parse(tagName);
        Collection<Holder<?>> holders = itemTags.get(tagRl);
        if (holders == null || holders.isEmpty()) {
            CTNHCore.LOGGER.warn("[TagManagerCache] Tag '#{}' not found or empty in cached data", tagName);
            return Set.of();
        }

        Set<String> ids = new HashSet<>();
        for (Holder<?> holder : holders) {
            holder.unwrapKey().ifPresent(key -> ids.add(key.location().toString()));
        }

        CTNHCore.LOGGER.info("[TagManagerCache] expandTagToItemIds('{}') → {} items (first 5: {})",
                tagName, ids.size(), ids.stream().limit(5).toList());
        return ids;
    }

    /**
     * 检查 tag 数据是否已缓存。
     */
    public static boolean isAvailable() {
        return !tagData.isEmpty() && tagData.containsKey(Registries.ITEM);
    }

    /**
     * 重置缓存（在资源重载时调用）。
     */
    public static void reset() {
        tagData = Map.of();
    }

    @SuppressWarnings("unchecked")
    private static ResourceKey<? extends Registry<?>> castKey(ResourceKey<?> key) {
        return (ResourceKey<? extends Registry<?>>) key;
    }

    @SuppressWarnings("unchecked")
    private static Map<ResourceLocation, Collection<Holder<?>>> castTags(Map<?, ?> tags) {
        return (Map<ResourceLocation, Collection<Holder<?>>>) tags;
    }
}
