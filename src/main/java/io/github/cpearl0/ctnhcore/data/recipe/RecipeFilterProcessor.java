package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;

import net.minecraft.resources.ResourceLocation;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.*;
import java.util.regex.Pattern;

/**
 * JSON 级别的配方 filter 处理辅助类。
 * <p>
 * 所有 remove/replace 的 JSON 处理逻辑都放在这里（非 mixin 包），
 * 以避免 Mixin 框架的 {@code IllegalClassLoadError}（内部类不能出现在 mixin package）。
 * <p>
 * 由 {@code RecipeManagerApplyMixin} 调用。
 */
public class RecipeFilterProcessor {

    // ==================== Remove 逻辑 ====================

    /**
     * Recipe JSON 中表示输出的 key 列表。
     * 已知的 input key：{@code ingredient}, {@code ingredients}, {@code input}, {@code inputs},
     * {@code tickInputs}, {@code item}, {@code fluid}（在非 output 上下文中）。
     * 除这些和 {@link #OUTPUT_KEYS} 以及元数据 key（type, category, duration, eu 等）以外的 key，
     * 按约定不包含物品引用，因此无需处理。
     */
    private static final Set<String> OUTPUT_KEYS = Set.of(
            "result", "results", "output", "outputs", "tickOutputs");

    /**
     * 在 JSON map 上执行删除。
     * 使用 {@link TagManagerCache} 获取已解析的 tag 数据（与 KubeJS 方案一致）。
     */
    public static int processRemovals(Map<ResourceLocation, JsonElement> map) {
        // 确保 tag 数据已缓存（首次调用时从 ReloadableServerResources 获取）
        TagManagerCache.ensureCached();

        var filters = RecipeRemoval.getFilters();
        if (filters.isEmpty()) return 0;

        // 预编译：将 filters 按复杂度分级
        Set<String> exactIds = new HashSet<>();
        List<ComplexRemoveFilter> complexFilters = new ArrayList<>();

        for (RecipeRemoval.RemoveFilter f : filters) {
            // 跳过需要检查 input 的 filter（JSON 级别无法检查输入物品）
            if (f.hasInputCheck()) {
                CTNHCore.LOGGER.warn("[RecipeRemoval] Filter {} requires input inspection, " +
                        "skipping at JSON level (add TAIL hook if needed)", f);
                continue;
            }
            String exactId = f.getSingleExactId();
            if (exactId != null) {
                exactIds.add(exactId);
            } else {
                complexFilters.add(precompileFilter(f));
            }
        }

        var toRemove = new ArrayList<ResourceLocation>();

        for (var entry : map.entrySet()) {
            ResourceLocation id = entry.getKey();
            String idStr = id.toString();

            // 保护 ctnhcore 命名空间（精确 ID 匹配除外）
            if ("ctnhcore".equals(id.getNamespace())) {
                if (exactIds.contains(idStr)) {
                    toRemove.add(id);
                }
                continue;
            }

            // 快速路径：精确 ID
            if (exactIds.contains(idStr)) {
                toRemove.add(id);
                continue;
            }

            // 复杂 filter
            for (var cf : complexFilters) {
                if (cf.matches(idStr, id.getNamespace(), entry.getValue())) {
                    toRemove.add(id);
                    break;
                }
            }
        }

        for (var id : toRemove) {
            map.remove(id);
        }

        return toRemove.size();
    }

    /**
     * 从 JSON 中提取 type 字符串（"type" 字段）。
     */
    private static String getJsonType(JsonElement element) {
        if (element instanceof JsonObject obj) {
            JsonElement te = obj.get("type");
            if (te instanceof JsonPrimitive p && p.isString()) return p.getAsString();
        }
        return "";
    }

    /**
     * 预编译一个复杂 filter：提取正则表达式和条件矩阵。
     */
    private static ComplexRemoveFilter precompileFilter(RecipeRemoval.RemoveFilter filter) {
        // 如果 output 是 tag（# 开头），使用 TagManagerCache 预展开为 item ID 集合
        Set<String> tagOutputIds = null;
        String out = filter.output();
        if (out != null && out.startsWith("#")) {
            tagOutputIds = TagManagerCache.expandTagToItemIds(out.substring(1));
        }
        return new ComplexRemoveFilter(
                filter.idAsString(),
                filter.idRegex(),
                filter.mod(),
                filter.type(),
                filter.output(),
                filter.outputRegex(),
                filter.idAsList(),
                filter.getNotList(),
                filter.getOrList(),
                tagOutputIds,
                filter.toString());
    }

    /**
     * 从 JSON 中提取所有 output item/fluid id。
     * 遍历 {@link #OUTPUT_KEYS} 中的每个 key，自动判断值是数组还是单个对象。
     * 支持 "item"、"tag"、"fluid" 三种引用形式。
     */
    private static Set<String> getJsonOutputIds(JsonElement element) {
        Set<String> ids = new HashSet<>();
        if (element instanceof JsonObject obj) {
            for (String key : OUTPUT_KEYS) {
                JsonElement value = obj.get(key);
                if (value == null) continue;
                if (value instanceof JsonArray arr) {
                    for (JsonElement entry : arr) {
                        collectIds(entry, ids);
                    }
                } else {
                    collectIds(value, ids);
                }
            }
        }
        return ids;
    }

    /**
     * 从单个 JSON 元素（对象或字符串）中提取 item/tag/fluid id。
     */
    private static void collectIds(JsonElement element, Set<String> ids) {
        if (element instanceof JsonObject o) {
            for (String refKey : List.of("item", "fluid")) {
                JsonElement ref = o.get(refKey);
                if (ref instanceof JsonPrimitive p && p.isString()) ids.add(p.getAsString());
            }
            JsonElement tag = o.get("tag");
            if (tag instanceof JsonPrimitive p && p.isString()) ids.add("#" + p.getAsString());
        } else if (element instanceof JsonPrimitive p && p.isString()) {
            ids.add(p.getAsString());
        }
    }

    /**
     * 预编译的复杂移除 filter。
     */
    public record ComplexRemoveFilter(
                                      String id, String idRegex, String mod, String type,
                                      String output, String outputRegex, List<String> idList,
                                      List<RecipeRemoval.RemoveFilter> notList,
                                      List<RecipeRemoval.RemoveFilter> orList,
                                      Set<String> tagOutputIds,
                                      String summary) {

        public boolean matches(String idStr, String namespace, JsonElement json) {
            // id 精确匹配
            if (id != null && !id.equals(idStr)) return false;
            if (idList != null && !idList.isEmpty() && !idList.contains(idStr)) return false;

            // id 正则
            if (idRegex != null && !Pattern.matches(idRegex, idStr)) return false;

            // mod
            if (mod != null && !mod.equals(namespace)) return false;

            // type
            if (type != null) {
                String jsonType = getJsonType(json);
                if (!type.equals(jsonType)) return false;
            }

            // output
            if (output != null) {
                Set<String> jsonOutputs = getJsonOutputIds(json);
                boolean matched = false;
                for (String jsonOutput : jsonOutputs) {
                    if (output.startsWith("#")) {
                        // tag 匹配：JSON 中也写了 tag → 直接字符串比较
                        if (output.equals(jsonOutput)) {
                            matched = true;
                            break;
                        }
                        // tag 匹配：JSON 中写的是具体 item ID → 用预展开的 tag item 集合检查
                        if (!jsonOutput.startsWith("#") && tagOutputIds != null && tagOutputIds.contains(jsonOutput)) {
                            matched = true;
                            break;
                        }
                    } else {
                        if (output.equals(jsonOutput)) {
                            matched = true;
                            break;
                        }
                    }
                }
                if (!matched) return false;
            }

            // outputRegex
            if (outputRegex != null) {
                Set<String> jsonOutputs = getJsonOutputIds(json);
                boolean matched = false;
                for (String jsonOutput : jsonOutputs) {
                    if (Pattern.matches(outputRegex, jsonOutput)) {
                        matched = true;
                        break;
                    }
                }
                if (!matched) return false;
            }

            // not 反选
            if (notList != null) {
                for (var n : notList) {
                    if (precompileFilter(n).matches(idStr, namespace, json)) return false;
                }
            }

            // or 列表
            if (orList != null) {
                boolean anyMatched = false;
                for (var o : orList) {
                    if (precompileFilter(o).matches(idStr, namespace, json)) {
                        anyMatched = true;
                        break;
                    }
                }
                if (!anyMatched) return false;
            }

            return true;
        }
    }

    // ==================== Replace 逻辑 ====================

    /**
     * 在 JSON map 上执行替换。
     * <p>
     * 注意：不使用 entry.setValue()，因为某些 Map 实现（如 Forge 传入的 map）可能
     * 不持久化 entry.setValue() 的修改。改为收集所有变更到临时 map，循环结束后
     * 通过 map.putAll() 统一应用。
     */
    public static int processReplacements(Map<ResourceLocation, JsonElement> map) {
        var ops = RecipeRemoval.getReplaceOperations();
        if (ops.isEmpty()) return 0;

        int totalReplaced = 0;

        for (var op : ops) {
            Map<ResourceLocation, JsonElement> changes = new LinkedHashMap<>();

            for (var entry : map.entrySet()) {
                ResourceLocation id = entry.getKey();
                String idStr = id.toString();
                if ("ctnhcore".equals(id.getNamespace()) && op.type == RecipeRemoval.ReplaceOperation.Type.OUTPUT)
                    continue;

                if (!op.filter.matchesJsonLevel(idStr, id.getNamespace(), getJsonType(entry.getValue())))
                    continue;

                if (!entry.getValue().isJsonObject()) continue;
                JsonObject recipe = entry.getValue().getAsJsonObject();

                boolean[] modified = { false };
                JsonObject result = replaceInRecipe(recipe, op.from, op.to, modified, op.type);
                if (modified[0]) {
                    changes.put(id, result);
                    totalReplaced++;
                }
            }

            if (!changes.isEmpty()) {
                map.putAll(changes);
            }
        }

        return totalReplaced;
    }

    /**
     * 在 recipe JSON 的指定区域（input 或 output）执行替换。
     * <p>
     * INPUT 操作：替换 recipe 中除 {@link #OUTPUT_KEYS} 之外的所有内容。
     * OUTPUT 操作：只替换 {@link #OUTPUT_KEYS} 中的内容。
     */
    private static JsonObject replaceInRecipe(JsonObject recipe, String from, String to,
                                              boolean[] modified, RecipeRemoval.ReplaceOperation.Type type) {
        JsonObject copy = recipe.deepCopy();

        if (type == RecipeRemoval.ReplaceOperation.Type.INPUT) {
            // 保存 output 字段，避免被替换
            Map<String, JsonElement> saved = new LinkedHashMap<>();
            for (String key : OUTPUT_KEYS) {
                if (copy.has(key)) {
                    saved.put(key, copy.remove(key));
                }
            }

            // 对剩余部分（输入区域）执行全局替换
            JsonElement replaced = replaceJsonRecursive(copy, from, to, modified);

            // 恢复 output 字段
            JsonObject resultObj = replaced.getAsJsonObject();
            for (var entry : saved.entrySet()) {
                resultObj.add(entry.getKey(), entry.getValue());
            }
            return resultObj;
        } else {
            // OUTPUT 操作：只替换 output 相关字段
            for (String key : OUTPUT_KEYS) {
                if (copy.has(key)) {
                    boolean[] m = { false };
                    JsonElement replaced = replaceJsonRecursive(copy.get(key), from, to, m);
                    if (m[0]) {
                        copy.add(key, replaced);
                        modified[0] = true;
                    }
                }
            }
            return copy;
        }
    }

    /**
     * 递归遍历 JSON 树，替换匹配的字符串引用。
     */
    private static JsonElement replaceJsonRecursive(JsonElement element, String from, String to,
                                                    boolean[] modified) {
        return replaceJsonRecursive(element, from, to, modified, null);
    }

    /**
     * 递归遍历 JSON 树，替换匹配的字符串引用。
     * 
     * @param parent 父级 JsonObject，用于 tag→直接引用时的上下文判断（检查 "amount" 确定是否为流体）
     */
    private static JsonElement replaceJsonRecursive(JsonElement element, String from, String to,
                                                    boolean[] modified, JsonObject parent) {
        if (element == null) return null;

        if (element instanceof JsonObject obj) {
            JsonObject copy = null;
            var entries = new ArrayList<>(obj.entrySet());

            for (var entry : entries) {
                String key = entry.getKey();
                JsonElement value = entry.getValue();

                // 处理 "item": "from_id" 形式的直接替换
                if ("item".equals(key) && value instanceof JsonPrimitive p && p.isString()) {
                    if (!from.startsWith("#") && from.equals(p.getAsString())) {
                        if (copy == null) copy = deepCopy(obj);
                        copy.addProperty("item", to);
                        modified[0] = true;
                        continue;
                    }
                }

                // 处理 "tag": "tag_name" 形式的 tag 替换
                if ("tag".equals(key) && value instanceof JsonPrimitive p && p.isString()) {
                    if (from.startsWith("#")) {
                        String tagName = from.substring(1);
                        if (tagName.equals(p.getAsString())) {
                            if (copy == null) copy = deepCopy(obj);
                            if (to.startsWith("#")) {
                                // tag → tag：保留 "tag" key
                                copy.addProperty("tag", to.substring(1));
                            } else {
                                // tag → 直接引用：需要把 key 从 "tag" 改为 "item" 或 "fluid"
                                copy.remove("tag");
                                // 上下文判断：当前对象或父对象有 "amount" → 流体，否则 → 物品
                                boolean isFluid = obj.has("amount") ||
                                        (parent != null && parent.has("amount"));
                                copy.addProperty(isFluid ? "fluid" : "item", to);
                            }
                            modified[0] = true;
                            continue;
                        }
                    }
                }

                // 处理 "fluid": "mod:fluid" 形式的流体替换
                if ("fluid".equals(key) && value instanceof JsonPrimitive p && p.isString()) {
                    if (!from.startsWith("#") && from.equals(p.getAsString())) {
                        if (copy == null) copy = deepCopy(obj);
                        copy.addProperty("fluid", to);
                        modified[0] = true;
                        continue;
                    }
                }

                // 递归处理子节点
                JsonElement replaced = replaceJsonRecursive(value, from, to, modified, obj);
                if (replaced != value) {
                    if (copy == null) copy = deepCopy(obj);
                    copy.add(key, replaced);
                }
            }

            return copy != null ? copy : obj;
        }

        if (element instanceof JsonArray arr) {
            JsonArray copy = null;
            for (int i = 0; i < arr.size(); i++) {
                JsonElement replaced = replaceJsonRecursive(arr.get(i), from, to, modified, parent);
                if (replaced != arr.get(i)) {
                    if (copy == null) copy = deepCopy(arr);
                    copy.set(i, replaced);
                }
            }
            return copy != null ? copy : arr;
        }

        // 处理纯字符串值（某些 recipe 格式直接用字符串而非 object）
        if (element instanceof JsonPrimitive p && p.isString()) {
            if (!from.startsWith("#") && from.equals(p.getAsString())) {
                modified[0] = true;
                return new JsonPrimitive(to);
            }
        }

        return element;
    }

    private static JsonObject deepCopy(JsonObject obj) {
        return obj.deepCopy();
    }

    private static JsonArray deepCopy(JsonArray arr) {
        JsonArray copy = new JsonArray();
        for (var e : arr) {
            if (e instanceof JsonObject o) copy.add(deepCopy(o));
            else if (e instanceof JsonArray a) copy.add(deepCopy(a));
            else copy.add(e);
        }
        return copy;
    }
}
