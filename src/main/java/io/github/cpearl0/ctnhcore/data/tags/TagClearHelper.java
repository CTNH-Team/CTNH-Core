package io.github.cpearl0.ctnhcore.data.tags;

import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;

import com.tterrag.registrate.providers.RegistrateTagsProvider;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
                    "Cannot find TagBuilder field in " + appender.getClass().getName()
                            + ". TagClearHelper requires a compatible TagsProvider implementation.");
        }
        try {
            return (TagBuilder) BUILDER_FIELDS.get(0).get(appender);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to access TagBuilder via reflection", e);
        }
    }
}
