package io.github.cpearl0.ctnhcore.utils.emi.collapsible;

import io.github.cpearl0.ctnhcore.CTNHCore;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/** 管理 EMI 侧栏折叠组：读取规则、匹配成员、投影显示列表并保存展开状态。 */
public class CTNHCollapsibleGroups {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /** 折叠组展开状态的持久化文件，位于 config/ctnhcore/collapsible_emi_groups.json。 */
    private static final Path STATE_FILE = FMLPaths.CONFIGDIR.get().resolve("ctnhcore/collapsible_emi_groups.json");

    /** 折叠组规则定义文件。新增折叠组统一添加到该 assets JSON。 */
    private static final String DEFAULT_RULE_RESOURCE = "/assets/ctnhcore/emi/emi_collapsible_groups.json";

    /** 标记 EMI 来源列表是否需要重新扫描并重建分组。 */
    private static boolean dirty = true;

    /** 防止每次重建都重复读取状态文件。 */
    private static boolean statesLoaded = false;

    /** guid -> 分组对象。使用 LinkedHashMap 保持注册顺序，从而稳定投影顺序。 */
    private static final Map<String, CollapsibleGroup> GROUPS = Collections.synchronizedMap(new LinkedHashMap<>());

    /** EMI ingredient 对象身份 -> 分组 guid。这里依赖 EMI 当前侧栏列表中的对象身份。 */
    private static final Map<EmiIngredient, String> STACK_TO_GROUP = new IdentityHashMap<>();

    /** 分组 guid -> 是否展开。没有记录时默认视为折叠。 */
    private static final Map<String, Boolean> EXPANDED_STATE = new HashMap<>();

    /** 当前投影列表中的折叠代表项 -> 分组 guid，用于边框和点击切换。 */
    private static final IdentityHashMap<EmiIngredient, String> REPRESENTATIVE_TO_GROUP = new IdentityHashMap<>();

    /** 当前投影列表中的折叠代表项 -> 背景代表项。 */
    private static final IdentityHashMap<EmiIngredient, EmiIngredient> REPRESENTATIVE_TO_SECONDARY = new IdentityHashMap<>();

    /** 从 JSON 读取并编译后的分组规则。 */
    private static List<RuleGroupDefinition> configuredRules = List.of();

    /** 防止每次重建都重复读取规则文件。 */
    private static boolean rulesLoaded = false;

    /** 一个已匹配完成的折叠组。 */
    public static class CollapsibleGroup {

        /** 分组唯一标识，同时作为持久化状态的 key。 */
        public final String guid;

        /** 当前 EMI 来源列表中属于该分组的成员，顺序沿用 EMI 侧栏原始顺序。 */
        public final List<EmiIngredient> members;

        /**
         * 创建一个空分组。
         *
         * @param guid 分组唯一标识，建议使用命名空间格式，例如 ctnhcore:tools/swords
         */
        public CollapsibleGroup(String guid) {
            this.guid = guid;
            this.members = new ArrayList<>();
        }

        /** true 表示显示全部成员，false 表示只显示一个代表项。 */
        public boolean isExpanded() {
            Boolean state = EXPANDED_STATE.get(guid);
            return state != null && state;
        }

        /** 更新展开状态并保存。 */
        public void setExpanded(boolean expanded) {
            EXPANDED_STATE.put(guid, expanded);
            saveStates();
        }
    }

    // ---- 公共 API：供 EMI mixin 查询状态、触发重建和响应交互 ----

    /** 返回当前 EMI 来源列表是否需要重新扫描。 */
    public static boolean needsRebuild() {
        return dirty;
    }

    /** 标记分组需要重建，通常在 EMI 可见性变化或列表来源变化时调用。 */
    public static void markDirty() {
        dirty = true;
    }

    /** 用 EMI INDEX 完整列表重建分组和对象身份映射。 */
    public static void rebuild(List<? extends EmiIngredient> stacks) {
        synchronized (GROUPS) {
            loadStates();
            GROUPS.clear();
            STACK_TO_GROUP.clear();
            REPRESENTATIVE_TO_GROUP.clear();
            REPRESENTATIVE_TO_SECONDARY.clear();
            if (stacks == null || stacks.isEmpty()) {
                dirty = false;
                return;
            }

            loadRules();
            registerConfiguredGroups(stacks);
            dirty = false;
        }
    }

    /** 每个 ingredient 进入最高优先级命中的组；同优先级保持 JSON 顺序。 */
    private static void registerConfiguredGroups(List<? extends EmiIngredient> stacks) {
        Map<RuleGroupDefinition, CollapsibleGroup> groups = new IdentityHashMap<>();
        for (RuleGroupDefinition rule : configuredRules) {
            groups.put(rule, new CollapsibleGroup(rule.guid()));
        }

        for (EmiIngredient ingredient : stacks) {
            RuleGroupDefinition bestRule = null;
            for (RuleGroupDefinition rule : configuredRules) {
                if (!rule.matches(ingredient)) continue;
                if (bestRule == null || rule.priority() > bestRule.priority()) {
                    bestRule = rule;
                }
            }
            if (bestRule != null) {
                groups.get(bestRule).members.add(ingredient);
            }
        }

        for (RuleGroupDefinition rule : configuredRules) {
            registerGroup(groups.get(rule));
        }
    }

    /** 少于两个成员的组没有折叠价值，直接丢弃。 */
    private static void registerGroup(CollapsibleGroup group) {
        if (group.members.size() < 2) return;

        GROUPS.put(group.guid, group);
        for (EmiIngredient member : group.members) {
            STACK_TO_GROUP.put(member, group.guid);
        }
    }

    /** 把 EMI 当前列表转换为实际显示列表：展开组显示成员，折叠组按当前搜索结果折叠。 */
    public static List<? extends EmiIngredient> project(List<? extends EmiIngredient> source) {
        if (dirty || GROUPS.isEmpty()) {
            return source;
        }
        synchronized (GROUPS) {
            List<EmiIngredient> result = new ArrayList<>();
            Set<String> projectedGroups = new HashSet<>();
            Map<String, List<EmiIngredient>> visibleMembers = visibleMembersByGroup(source);
            Set<EmiIngredient> sourceStacks = Collections.newSetFromMap(new IdentityHashMap<>());
            sourceStacks.addAll(source);
            REPRESENTATIVE_TO_GROUP.clear();
            REPRESENTATIVE_TO_SECONDARY.clear();

            for (EmiIngredient stack : source) {
                String guid = STACK_TO_GROUP.get(stack);
                if (guid == null) {
                    result.add(stack);
                    continue;
                }
                CollapsibleGroup group = GROUPS.get(guid);
                if (group == null) {
                    result.add(stack);
                    continue;
                }
                if (group.members.size() < 2) {
                    result.add(stack);
                } else if (group.isExpanded()) {
                    if (projectedGroups.add(guid)) {
                        for (EmiIngredient member : group.members) {
                            if (sourceStacks.contains(member)) {
                                result.add(member);
                            }
                        }
                    }
                } else {
                    if (projectedGroups.add(guid)) {
                        List<EmiIngredient> visible = visibleMembers.getOrDefault(guid, List.of());
                        if (visible.size() > 1) {
                            REPRESENTATIVE_TO_GROUP.put(stack, guid);
                            REPRESENTATIVE_TO_SECONDARY.put(stack, visible.get(1));
                        }
                        result.add(stack);
                    }
                }
            }
            return result;
        }
    }

    private static Map<String, List<EmiIngredient>> visibleMembersByGroup(List<? extends EmiIngredient> source) {
        Map<String, List<EmiIngredient>> visibleMembers = new HashMap<>();
        for (EmiIngredient stack : source) {
            String guid = STACK_TO_GROUP.get(stack);
            if (guid != null) {
                visibleMembers.computeIfAbsent(guid, ignored -> new ArrayList<>()).add(stack);
            }
        }
        return visibleMembers;
    }

    /** 查询普通成员或折叠代表项所属的组。 */
    @Nullable
    public static CollapsibleGroup getGroup(EmiIngredient ingredient) {
        String guid = STACK_TO_GROUP.get(ingredient);
        if (guid == null) guid = REPRESENTATIVE_TO_GROUP.get(ingredient);
        if (guid == null) return null;
        return GROUPS.get(guid);
    }

    /** 判断 ingredient 是否是当前投影中的折叠代表项。 */
    public static boolean isCollapsedRepresentative(EmiIngredient ingredient) {
        return REPRESENTATIVE_TO_GROUP.containsKey(ingredient);
    }

    /** 返回折叠代表项对应的次代表项，用于叠层绘制。 */
    @Nullable
    public static EmiIngredient getSecondaryRepresentative(EmiIngredient ingredient) {
        return REPRESENTATIVE_TO_SECONDARY.get(ingredient);
    }

    private static void toggleGroup(String guid) {
        CollapsibleGroup group = GROUPS.get(guid);
        if (group != null) {
            group.setExpanded(!group.isExpanded());
        }
    }

    /** 从 config/ctnhcore/collapsible_emi_groups.json 读取展开状态。 */
    private static void loadStates() {
        if (statesLoaded) return;
        statesLoaded = true;
        if (!Files.isRegularFile(STATE_FILE)) return;

        try (Reader reader = Files.newBufferedReader(STATE_FILE)) {
            JsonObject states = JsonParser.parseReader(reader).getAsJsonObject();
            for (String key : states.keySet()) {
                EXPANDED_STATE.put(key, states.get(key).getAsBoolean());
            }
        } catch (RuntimeException | IOException e) {
            CTNHCore.LOGGER.warn("Failed to load EMI collapsible group states from {}", STATE_FILE, e);
        }
    }

    /** 保存展开状态。 */
    private static void saveStates() {
        if (!statesLoaded) return;

        try {
            Files.createDirectories(STATE_FILE.getParent());
            JsonObject states = new JsonObject();
            for (Map.Entry<String, Boolean> entry : EXPANDED_STATE.entrySet()) {
                states.addProperty(entry.getKey(), entry.getValue());
            }
            try (Writer writer = Files.newBufferedWriter(STATE_FILE)) {
                GSON.toJson(states, writer);
            }
        } catch (IOException e) {
            CTNHCore.LOGGER.warn("Failed to save EMI collapsible group states to {}", STATE_FILE, e);
        }
    }

    /** 从 assets JSON 读取并编译分组规则。 */
    private static void loadRules() {
        if (rulesLoaded) return;
        rulesLoaded = true;

        List<RuleGroupDefinition> definitions = new ArrayList<>();
        addRuleDefinitions(DEFAULT_RULE_RESOURCE, defaultRuleRoot(), definitions);
        configuredRules = List.copyOf(definitions);
    }

    private static JsonObject defaultRuleRoot() {
        try (InputStream stream = CTNHCollapsibleGroups.class.getResourceAsStream(DEFAULT_RULE_RESOURCE)) {
            if (stream == null) {
                CTNHCore.LOGGER.warn("Missing default EMI collapsible group rule resource {}", DEFAULT_RULE_RESOURCE);
                return new JsonObject();
            }
            try (Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                JsonElement root = JsonParser.parseReader(reader);
                if (root.isJsonObject()) {
                    return root.getAsJsonObject();
                }
                CTNHCore.LOGGER.warn("Default EMI collapsible group rule resource {} must be a JSON object",
                        DEFAULT_RULE_RESOURCE);
            }
        } catch (RuntimeException | IOException e) {
            CTNHCore.LOGGER.warn("Failed to read default EMI collapsible group rules from {}", DEFAULT_RULE_RESOURCE,
                    e);
        }
        return new JsonObject();
    }

    private static void addRuleDefinitions(String sourceName, JsonElement root,
                                           List<RuleGroupDefinition> definitions) {
        if (!root.isJsonObject()) {
            CTNHCore.LOGGER.warn("EMI collapsible group rule file {} must be a JSON object", sourceName);
            return;
        }

        for (Map.Entry<String, JsonElement> entry : root.getAsJsonObject().entrySet()) {
            RuleGroupDefinition definition = parseGroupDefinition(entry.getKey(), entry.getValue());
            if (definition != null && !definition.rules().isEmpty()) {
                definitions.add(definition);
            }
        }
    }

    /** 解析单个 JSON 分组。 */
    @Nullable
    private static RuleGroupDefinition parseGroupDefinition(String guid, JsonElement element) {
        List<GroupRule> rules = new ArrayList<>();
        Boolean expanded = null;
        int priority = 0;
        if (element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()) {
            addRule(guid, element.getAsString(), rules);
        } else if (element.isJsonArray()) {
            for (JsonElement ruleElement : element.getAsJsonArray()) {
                if (ruleElement.isJsonPrimitive() && ruleElement.getAsJsonPrimitive().isString()) {
                    addRule(guid, ruleElement.getAsString(), rules);
                } else {
                    CTNHCore.LOGGER.warn("Ignoring non-string EMI collapsible rule in group {}", guid);
                }
            }
        } else if (element.isJsonObject()) {
            JsonObject object = element.getAsJsonObject();
            if (object.has("expanded")) expanded = object.get("expanded").getAsBoolean();
            if (object.has("priority")) {
                priority = object.get("priority").getAsInt();
                if (priority < 0) {
                    CTNHCore.LOGGER.warn("Ignoring EMI collapsible group {} because priority must be non-negative",
                            guid);
                    return null;
                }
            }
            JsonElement rulesElement = object.has("key") ? object.get("key") : object.get("rules");
            if (rulesElement == null) {
                CTNHCore.LOGGER.warn("Ignoring EMI collapsible group {} because it has no key/rules", guid);
            } else if (rulesElement.isJsonPrimitive() && rulesElement.getAsJsonPrimitive().isString()) {
                addRule(guid, rulesElement.getAsString(), rules);
            } else if (rulesElement.isJsonArray()) {
                for (JsonElement ruleElement : rulesElement.getAsJsonArray()) {
                    if (ruleElement.isJsonPrimitive() && ruleElement.getAsJsonPrimitive().isString()) {
                        addRule(guid, ruleElement.getAsString(), rules);
                    } else {
                        CTNHCore.LOGGER.warn("Ignoring non-string EMI collapsible rule in group {}", guid);
                    }
                }
            } else {
                CTNHCore.LOGGER.warn(
                        "Ignoring EMI collapsible group {} because key/rules is not a string or string array",
                        guid);
            }
        } else {
            CTNHCore.LOGGER.warn(
                    "Ignoring EMI collapsible group {} because its rule is not a string, string array, or object",
                    guid);
        }

        if (rules.isEmpty()) return null;
        if (expanded != null && !EXPANDED_STATE.containsKey(guid)) {
            EXPANDED_STATE.put(guid, expanded);
        }
        return new RuleGroupDefinition(guid, priority, List.copyOf(rules));
    }

    /** 编译一个规则字符串。 */
    private static void addRule(String guid, String rule, List<GroupRule> rules) {
        rule = rule.trim();
        if (rule.isEmpty()) return;
        if (rule.startsWith("#")) {
            ResourceLocation tagId = ResourceLocation.tryParse(rule.substring(1));
            if (tagId == null) {
                CTNHCore.LOGGER.warn("Ignoring invalid EMI collapsible tag rule {} in group {}", rule, guid);
                return;
            }
            rules.add(tagRule(tagId));
            return;
        }

        if (rule.startsWith("regex:")) {
            String pattern = rule.substring("regex:".length());
            try {
                rules.add(new RegexRule(Pattern.compile(pattern)));
            } catch (PatternSyntaxException e) {
                CTNHCore.LOGGER.warn("Ignoring invalid EMI collapsible regex rule {} in group {}", rule, guid, e);
            }
            return;
        }

        try {
            rules.add(parseExpressionRule(rule));
        } catch (IllegalArgumentException e) {
            CTNHCore.LOGGER.warn(
                    "Ignoring EMI collapsible rule {} in group {}; expected #tag, regex:<pattern>, or item filter",
                    rule, guid, e);
        }
    }

    private static GroupRule parseExpressionRule(String rule) {
        List<GroupRule> alternatives = new ArrayList<>();
        for (String part : rule.split("\\|")) {
            String trimmed = part.trim();
            if (!trimmed.isEmpty()) {
                alternatives.add(parseAllRule(trimmed));
            }
        }
        if (alternatives.isEmpty()) throw new IllegalArgumentException("Empty rule");
        if (alternatives.size() == 1) return alternatives.get(0);
        return new AnyRule(List.copyOf(alternatives));
    }

    private static GroupRule parseAllRule(String rule) {
        String[] tokens = rule.split("\\s+");
        List<GroupRule> rules = new ArrayList<>();
        for (String token : tokens) {
            if (!token.isBlank()) {
                rules.add(parseTokenRule(token));
            }
        }
        if (rules.isEmpty()) throw new IllegalArgumentException("Empty rule");
        if (rules.size() == 1) return rules.get(0);
        return new AllRule(List.copyOf(rules));
    }

    private static GroupRule parseTokenRule(String token) {
        List<GroupRule> alternatives = new ArrayList<>();
        for (String part : token.split(",")) {
            String trimmed = part.trim();
            if (!trimmed.isEmpty()) {
                alternatives.add(parseSingleRule(trimmed));
            }
        }
        if (alternatives.isEmpty()) throw new IllegalArgumentException("Empty token");
        if (alternatives.size() == 1) return alternatives.get(0);
        return new AnyRule(List.copyOf(alternatives));
    }

    private static GroupRule parseSingleRule(String token) {
        if (token.startsWith("!")) {
            return new NotRule(parseSingleRule(token.substring(1)));
        }
        if (token.startsWith("$")) {
            ResourceLocation tagId = ResourceLocation.tryParse("forge:" + token.substring(1).toLowerCase(Locale.ROOT));
            if (tagId == null) throw new IllegalArgumentException("Invalid ore/tag token " + token);
            return tagRule(tagId);
        }
        if (token.startsWith("#")) {
            ResourceLocation tagId = ResourceLocation.tryParse(token.substring(1));
            if (tagId == null) throw new IllegalArgumentException("Invalid tag token " + token);
            return tagRule(tagId);
        }
        if (token.startsWith("r/") && token.endsWith("/") && token.length() > 3) {
            return new RegexRule(Pattern.compile(token.substring(2, token.length() - 1)));
        }
        if (token.matches("\\d+(?:-\\d+)?")) {
            return parseDamageRule(token);
        }

        ResourceLocation id = ResourceLocation.tryParse(token);
        if (id == null) {
            throw new IllegalArgumentException("Invalid item id " + token);
        }
        return new ItemIdRule(id);
    }

    private static TagRule tagRule(ResourceLocation tagId) {
        return new TagRule(TagKey.create(Registries.ITEM, tagId), TagKey.create(Registries.BLOCK, tagId));
    }

    private static GroupRule parseDamageRule(String token) {
        if (token.contains("-")) {
            String[] parts = token.split("-", 2);
            return new DamageRule(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        }
        int damage = Integer.parseInt(token);
        return new DamageRule(damage, damage);
    }

    /**
     * 根据鼠标悬停到的 ingredient 切换所属分组。
     *
     * <p>
     * 输入通常来自 EMI 的 hover 检测，可能是折叠代表项，也可能是展开后的普通成员。
     * </p>
     *
     * @return true 表示找到并切换了分组，调用方应刷新 EMI 面板
     */
    public static boolean toggleGroup(EmiIngredient representative) {
        String guid = REPRESENTATIVE_TO_GROUP.get(representative);
        if (guid == null) guid = STACK_TO_GROUP.get(representative);
        if (guid != null) {
            toggleGroup(guid);
            return true;
        }
        return false;
    }

    /**
     * 批量切换所有有效分组。
     *
     * <p>
     * 左键使用智能模式：只要还有任意折叠组，就展开全部；否则折叠全部。
     * 右键会传入 forceCollapse=true，始终折叠全部。
     * </p>
     */
    public static void toggleAll(boolean forceCollapse) {
        boolean anyCollapsed = false;
        synchronized (GROUPS) {
            for (CollapsibleGroup group : GROUPS.values()) {
                if (group.members.size() >= 2 && !group.isExpanded()) {
                    anyCollapsed = true;
                    break;
                }
            }
            boolean expand = !forceCollapse && anyCollapsed;
            for (CollapsibleGroup group : GROUPS.values()) {
                if (group.members.size() >= 2) {
                    group.setExpanded(expand);
                }
            }
        }
    }

    /** 统计当前处于折叠状态、且成员数不少于 2 的分组数量。 */
    public static int collapsedGroupCount() {
        int count = 0;
        synchronized (GROUPS) {
            for (CollapsibleGroup group : GROUPS.values()) {
                if (group.members.size() >= 2 && !group.isExpanded()) {
                    count++;
                }
            }
        }
        return count;
    }

    /** 统计所有有效分组数量。有效分组指成员数不少于 2，确实能被折叠的分组。 */
    public static int totalGroupCount() {
        int count = 0;
        synchronized (GROUPS) {
            for (CollapsibleGroup group : GROUPS.values()) {
                if (group.members.size() >= 2) count++;
            }
        }
        return count;
    }

    /** 判断当前是否存在至少一个有效折叠组。 */
    public static boolean hasGroups() {
        if (GROUPS.isEmpty()) return false;
        synchronized (GROUPS) {
            for (CollapsibleGroup group : GROUPS.values()) {
                if (group.members.size() >= 2) return true;
            }
        }
        return false;
    }

    /** 一条 JSON 分组定义和它编译后的匹配规则。 */
    private record RuleGroupDefinition(String guid, int priority, List<GroupRule> rules) {

        boolean matches(EmiIngredient ingredient) {
            for (EmiStack stack : ingredient.getEmiStacks()) {
                ItemStack itemStack = stack.getItemStack();
                if (!itemStack.isEmpty() && matchesItem(itemStack)) {
                    return true;
                }
            }
            return false;
        }

        private boolean matchesItem(ItemStack stack) {
            for (GroupRule rule : rules) {
                if (rule.matches(stack)) return true;
            }
            return false;
        }
    }

    /** 单条分组规则。 */
    private interface GroupRule {

        boolean matches(ItemStack stack);
    }

    private record AnyRule(List<GroupRule> rules) implements GroupRule {

        @Override
        public boolean matches(ItemStack stack) {
            for (GroupRule rule : rules) {
                if (rule.matches(stack)) return true;
            }
            return false;
        }
    }

    private record AllRule(List<GroupRule> rules) implements GroupRule {

        @Override
        public boolean matches(ItemStack stack) {
            for (GroupRule rule : rules) {
                if (!rule.matches(stack)) return false;
            }
            return true;
        }
    }

    private record NotRule(GroupRule rule) implements GroupRule {

        @Override
        public boolean matches(ItemStack stack) {
            return !rule.matches(stack);
        }
    }

    /** item/block tag 规则。 */
    private record TagRule(TagKey<Item> itemTag, TagKey<Block> blockTag) implements GroupRule {

        @Override
        public boolean matches(ItemStack stack) {
            return matchesTag(stack, itemTag, blockTag);
        }
    }

    private static boolean matchesTag(ItemStack stack, TagKey<Item> itemTag, TagKey<Block> blockTag) {
        if (stack.is(itemTag)) return true;
        return stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock().defaultBlockState().is(blockTag);
    }

    /** 物品注册 id 正则规则。 */
    private record RegexRule(Pattern pattern) implements GroupRule {

        @Override
        public boolean matches(ItemStack stack) {
            ResourceLocation id = ForgeRegistries.ITEMS.getKey(stack.getItem());
            return id != null && pattern.matcher(id.toString()).matches();
        }
    }

    /** 物品 id 规则；如果完整 id 不存在，则按注册 id 前缀匹配，兼容 GTNH 的 minecraft:record_ 风格。 */
    private record ItemIdRule(ResourceLocation id) implements GroupRule {

        @Override
        public boolean matches(ItemStack stack) {
            ResourceLocation stackId = ForgeRegistries.ITEMS.getKey(stack.getItem());
            if (stackId == null || !Objects.equals(stackId.getNamespace(), id.getNamespace())) return false;
            if (Objects.equals(stackId, id)) return true;

            Item item = ForgeRegistries.ITEMS.getValue(id);
            return item == null || item == Items.AIR ? stackId.getPath().startsWith(id.getPath()) : false;
        }
    }

    /** 物品 damage 规则。1.20 中很多物品不再使用 damage 子类型，但保留该语法以接近 GTNH 配置。 */
    private record DamageRule(int min, int max) implements GroupRule {

        @Override
        public boolean matches(ItemStack stack) {
            int damage = stack.getDamageValue();
            return damage >= min && damage <= max;
        }
    }
}
