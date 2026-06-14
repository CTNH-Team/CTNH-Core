package io.github.cpearl0.ctnhcore.data.tags;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagKey;

import com.tterrag.registrate.providers.RegistrateTagsProvider;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Tag 清空工具。
 * 生成 {@code {"replace": true, "values": []}} 覆盖下游数据包/模组的标签，
 * 等效于 KubeJS 的 {@code event.removeAll(tag)}。
 */
public class TagClearHelper {

    /**
     * 清空（覆盖为空）一个标签。
     *
     * @param provider Registrate 标签数据生成器
     * @param tagKey   要清空的标签
     * @param <T>      注册表类型（Item / Fluid / Block …）
     */
    public static <T> void clear(RegistrateTagsProvider<T> provider, TagKey<T> tagKey) {
        var appender = provider.addTag(tagKey);
        TagBuilder builder = extractBuilder(appender);
        builder.replace(true);
    }

    /**
     * 批量清空多个标签。
     */
    @SafeVarargs
    public static <T> void clearAll(RegistrateTagsProvider<T> provider, TagKey<T>... tagKeys) {
        for (var key : tagKeys) {
            clear(provider, key);
        }
    }

    /**
     * 从标签中移除特定项。
     * 生成 {@code {"remove": ["<entryId>"]}}，等效于 KubeJS 的 {@code event.remove(tag, item)}。
     *
     * @param provider Registrate 标签数据生成器
     * @param tagKey   要操作的标签
     * @param entryIds 要移除的项 ID（ResourceLocation）
     * @param <T>      注册表类型
     */
    public static <T> void removeFromTag(RegistrateTagsProvider<T> provider, TagKey<T> tagKey,
                                         ResourceLocation... entryIds) {
        var appender = provider.addTag(tagKey);
        TagBuilder builder = extractBuilder(appender);
        for (ResourceLocation entryId : entryIds) {
            builder.remove(TagEntry.optionalElement(entryId));
        }
    }

    /**
     * 从标签中移除匹配正则的所有项。
     * 遍历注册表中所有项，筛选 ID 匹配 pattern 的项并从标签中移除。
     *
     * @param provider Registrate 标签数据生成器
     * @param tagKey   要操作的标签
     * @param pattern  正则表达式，匹配完整的 ID 字符串（如 {@code "vintageimprovements:.*_rod"}）
     * @param registry 注册表（如 {@link BuiltInRegistries#ITEM}）
     * @param <T>      注册表类型
     */
    public static <T> void removeFromTagByPattern(RegistrateTagsProvider<T> provider, TagKey<T> tagKey,
                                                  String pattern, Registry<T> registry) {
        var ids = expandPattern(pattern, registry);
        removeFromTag(provider, tagKey, ids.toArray(new ResourceLocation[0]));
    }

    /**
     * 将正则 pattern 展开为注册表中匹配的所有 ResourceLocation。
     *
     * <p>
     * pattern 匹配的是完整的 ID 字符串（{@code namespace:path}），
     * 例如 {@code "vintageimprovements:.*_rod"} 会匹配 {@code vintageimprovements:iron_rod} 等。
     *
     * @param pattern  正则表达式
     * @param registry 注册表（如 {@link BuiltInRegistries#ITEM}）
     * @param <T>      注册表类型
     * @return 匹配的所有 ResourceLocation
     */
    public static <T> List<ResourceLocation> expandPattern(String pattern, Registry<T> registry) {
        var regex = Pattern.compile(pattern);
        return registry.keySet().stream()
                .filter(id -> regex.matcher(id.toString()).matches())
                .collect(Collectors.toList());
    }

    // -----------------------------------------------------------
    // 反射：从 TagAppender 中取出 TagBuilder 实例
    // -----------------------------------------------------------
    private static final Class<?> TAG_APPENDER_CLASS;
    private static final List<Field> BUILDER_FIELDS = new ArrayList<>();

    static {
        // RegistrateTagsProvider.addTag() 返回的对象类型取决于 registrate 版本。
        // 可能直接返回 net.minecraft.data.tags.TagsProvider.TagAppender，
        // 也可能是 registrate 自己的包装类。两者内部都持有 TagBuilder 字段。
        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.tterrag.registrate.providers.RegistrateTagsProvider$TagAppender");
        } catch (ClassNotFoundException ignored) {
            // 老版本 registrate 可能没有内部 TagAppender，回退到 TagsProvider.TagAppender
        }
        if (clazz == null) {
            clazz = TagsProvider.TagAppender.class;
        }
        TAG_APPENDER_CLASS = clazz;

        // 缓存所有类型为 TagBuilder 的字段
        for (var field : clazz.getDeclaredFields()) {
            if (TagBuilder.class.isAssignableFrom(field.getType())) {
                field.setAccessible(true);
                BUILDER_FIELDS.add(field);
            }
        }
        // 也查父类
        Class<?> superClazz = clazz.getSuperclass();
        while (superClazz != null && superClazz != Object.class) {
            for (var field : superClazz.getDeclaredFields()) {
                if (TagBuilder.class.isAssignableFrom(field.getType())) {
                    field.setAccessible(true);
                    BUILDER_FIELDS.add(field);
                }
            }
            superClazz = superClazz.getSuperclass();
        }
    }

    private static TagBuilder extractBuilder(Object appender) {
        if (BUILDER_FIELDS.isEmpty()) {
            // 如果反射没找到字段，尝试通过 TagsProvider 的 builders map 来设置
            throw new IllegalStateException(
                    "Cannot find TagBuilder field in " + appender.getClass().getName() +
                            ". TagClearHelper requires a compatible TagsProvider implementation.");
        }
        try {
            return (TagBuilder) BUILDER_FIELDS.get(0).get(appender);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to access TagBuilder via reflection", e);
        }
    }
}
