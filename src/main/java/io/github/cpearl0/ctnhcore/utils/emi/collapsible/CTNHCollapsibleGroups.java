package io.github.cpearl0.ctnhcore.utils.emi.collapsible;

import io.github.cpearl0.ctnhcore.CTNHCore;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * EMI 侧栏折叠组的核心管理类。
 *
 * <p>
 * 本类负责三件事：
 * </p>
 * <ul>
 * <li>根据 EMI 侧栏提供的 {@link EmiIngredient} 列表注册折叠组。</li>
 * <li>在侧栏渲染前把原始列表投影成“折叠代表项”或“展开成员列表”。</li>
 * <li>把每个分组的展开/折叠状态保存到配置目录，保证重启后状态仍然保留。</li>
 * </ul>
 *
 * <p>
 * 分组规则从 config/ctnhcore/emi_collapsible_groups.json 读取，支持 item/block tag、物品 id 正则和
 * 一组接近 GTNH NEI collapsibleitems.cfg 的简化 item filter 语法。
 * </p>
 */
public class CTNHCollapsibleGroups {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /** 折叠组展开状态的持久化文件，位于 config/ctnhcore/collapsible_emi_groups.json。 */
    private static final Path STATE_FILE = FMLPaths.CONFIGDIR.get().resolve("ctnhcore/collapsible_emi_groups.json");

    /** 折叠组规则定义文件，位于 config/ctnhcore/emi_collapsible_groups.json。 */
    private static final Path RULE_FILE = FMLPaths.CONFIGDIR.get().resolve("ctnhcore/emi_collapsible_groups.json");

    /** 兼容 GTNH/NEI collapsibleitems.cfg 风格的一行一组配置。 */
    private static final Path LEGACY_RULE_FILE = FMLPaths.CONFIGDIR.get().resolve("ctnhcore/collapsibleitems.cfg");

    /** 总开关。关闭后不会重建、投影或响应折叠组交互。 */
    private static boolean enabled = true;

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

    /** 当前投影列表中的折叠代表项 -> 分组 guid，用于 tooltip、边框和点击切换。 */
    private static final IdentityHashMap<EmiIngredient, String> REPRESENTATIVE_TO_GROUP = new IdentityHashMap<>();

    /** 从 JSON/legacy 配置读取并编译后的分组注册器。 */
    private static List<CollapsibleGroupProvider> configuredProviders = List.of();

    /** 防止每次重建都重复读取规则文件。 */
    private static boolean rulesLoaded = false;

    /**
     * 一个可折叠的 EMI 侧栏分组。
     *
     * <p>
     * 分组保存显示名、成员列表、代表项以及展开状态。实际渲染仍由 EMI 完成，
     * 本对象只描述“哪些 ingredient 属于同一组”和“当前应该折叠还是展开”。
     * </p>
     */
    public static class CollapsibleGroup {

        /** 分组唯一标识，同时作为持久化状态的 key。 */
        public final String guid;

        /** tooltip 中显示的分组名称。新增分组时应使用 Component.translatable。 */
        public Component displayName;

        /** 当前 EMI 来源列表中属于该分组的成员，顺序沿用 EMI 侧栏原始顺序。 */
        public final List<EmiIngredient> members;

        /** 分组的主代表项，通常是成员列表中的第一个 ingredient。 */
        public EmiIngredient primaryRepresentative;

        /** 分组的次代表项，折叠渲染时用于画出类似 GTNH NEI 的叠层图标。 */
        public EmiIngredient secondaryRepresentative;

        /**
         * 创建一个空分组。
         *
         * @param guid 分组唯一标识，建议使用命名空间格式，例如 ctnhcore:tools/swords
         */
        public CollapsibleGroup(String guid) {
            this.guid = guid;
            this.members = new ArrayList<>();
            this.displayName = Component.literal(guid);
            this.primaryRepresentative = null;
            this.secondaryRepresentative = null;
        }

        /**
         * 查询分组是否处于展开状态。
         *
         * @return true 表示投影时显示所有成员，false 表示只显示一个代表项
         */
        public boolean isExpanded() {
            Boolean state = EXPANDED_STATE.get(guid);
            return state != null && state;
        }

        /**
         * 设置分组展开状态，并立即保存到配置文件。
         *
         * @param expanded true 为展开，false 为折叠
         */
        public void setExpanded(boolean expanded) {
            EXPANDED_STATE.put(guid, expanded);
            saveStates();
        }

        /**
         * 返回成员数量，用于 UI 统计或判断分组是否值得折叠。
         */
        public int memberCount() {
            return members.size();
        }
    }

    // ---- 公共 API：供 EMI mixin 查询状态、触发重建和响应交互 ----

    /** 返回折叠功能总开关是否启用。 */
    public static boolean isEnabled() {
        return enabled;
    }

    /** 设置折叠功能总开关。 */
    public static void setEnabled(boolean e) {
        enabled = e;
    }

    /** 返回当前 EMI 来源列表是否需要重新扫描。 */
    public static boolean needsRebuild() {
        return dirty;
    }

    /** 标记分组需要重建，通常在 EMI 可见性变化或列表来源变化时调用。 */
    public static void markDirty() {
        dirty = true;
    }

    /**
     * 根据 EMI 侧栏完整来源列表重建所有折叠组。
     *
     * <p>
     * 重建会清空旧的对象身份映射，再按当前列表重新注册成员。这样能保证搜索、重载、
     * EMI 刷新后，{@link IdentityHashMap} 中保存的都是当前侧栏正在使用的 ingredient 对象。
     * </p>
     *
     * @param stacks EMI INDEX 侧栏的完整 ingredient 来源列表
     */
    public static void rebuild(List<? extends EmiIngredient> stacks) {
        synchronized (GROUPS) {
            loadStates();
            GROUPS.clear();
            STACK_TO_GROUP.clear();
            REPRESENTATIVE_TO_GROUP.clear();
            if (!enabled || stacks == null || stacks.isEmpty()) {
                dirty = false;
                return;
            }

            loadRules();
            registerConfiguredGroups(stacks);
            dirty = false;
        }
    }

    /**
     * 注册 JSON 配置中声明的折叠分组。
     *
     * <p>
     * 每个 ingredient 只会进入最高优先级的命中组；同优先级时保持 provider 注册顺序。
     * </p>
     */
    private static void registerConfiguredGroups(List<? extends EmiIngredient> stacks) {
        List<CollapsibleGroupProvider> validProviders = new ArrayList<>();
        for (CollapsibleGroupProvider provider : configuredProviders) {
            if (provider.priority() < 0) {
                CTNHCore.LOGGER.warn("Skipping EMI collapsible group {} because priority must be non-negative",
                        provider.guid());
                continue;
            }
            validProviders.add(provider);
        }

        Map<CollapsibleGroupProvider, CollapsibleGroup> groups = new IdentityHashMap<>();
        for (CollapsibleGroupProvider provider : validProviders) {
            CollapsibleGroup group = provider.createGroup();
            groups.put(provider, group);
        }

        for (EmiIngredient ingredient : stacks) {
            CollapsibleGroupProvider bestProvider = null;
            for (CollapsibleGroupProvider provider : validProviders) {
                if (!provider.matches(ingredient)) continue;
                if (bestProvider == null || provider.priority() > bestProvider.priority()) {
                    bestProvider = provider;
                }
            }
            if (bestProvider != null) {
                groups.get(bestProvider).members.add(ingredient);
            }
        }

        for (CollapsibleGroupProvider provider : validProviders) {
            registerGroup(groups.get(provider));
        }
    }

    /**
     * 根据分组 guid 生成显示名。
     *
     * <p>
     * 默认工具组沿用已有语言 key，例如 ctnhcore:tools/swords 会尝试读取
     * ctnhcore.emi.collapsible.group.swords。自定义组没有语言条目时显示 guid 本身。
     * </p>
     */
    private static Component displayNameForGroup(String guid) {
        CollapsibleGroupProvider provider = findProvider(guid);
        if (provider != null) return provider.createDisplayName();
        return fallbackDisplayName(guid);
    }

    private static Component fallbackDisplayName(String guid) {
        ResourceLocation id = ResourceLocation.tryParse(guid);
        String fallback = guid;
        String path = id == null ? guid : id.getPath();
        int slash = path.lastIndexOf('/');
        String name = slash >= 0 ? path.substring(slash + 1) : path;
        return Component.translatableWithFallback("ctnhcore.emi.collapsible.group." + name, fallback);
    }

    /**
     * 将构造完成的分组写入全局索引。
     *
     * <p>
     * 少于两个成员的分组没有折叠价值，因此不会注册。注册成功后会建立 member -> guid 映射，
     * 后续投影、tooltip 和点击逻辑都依赖这个映射。
     * </p>
     */
    private static void registerGroup(CollapsibleGroup group) {
        if (group.members.size() < 2) return;

        group.primaryRepresentative = group.members.get(0);
        if (group.members.size() > 1) {
            group.secondaryRepresentative = group.members.get(1);
        }
        GROUPS.put(group.guid, group);
        for (EmiIngredient member : group.members) {
            STACK_TO_GROUP.put(member, group.guid);
        }
    }

    /**
     * 将 EMI 原始侧栏列表投影成实际显示列表。
     *
     * <p>
     * 未分组项会原样通过；展开分组会显示所有仍存在于当前 source 中的成员；
     * 折叠分组只显示当前遍历到的第一个成员，并把它记录为本轮投影的代表项。
     * </p>
     *
     * @param source EMI 当前要渲染的原始列表，可能已经受搜索过滤影响
     * @return 投影后的列表，交给 EMI 继续渲染
     */
    public static List<? extends EmiIngredient> project(List<? extends EmiIngredient> source) {
        if (!enabled || dirty || GROUPS.isEmpty()) {
            return source;
        }
        synchronized (GROUPS) {
            List<EmiIngredient> result = new ArrayList<>();
            Set<String> projectedGroups = new HashSet<>();
            Set<EmiIngredient> sourceStacks = Collections.newSetFromMap(new IdentityHashMap<>());
            sourceStacks.addAll(source);
            REPRESENTATIVE_TO_GROUP.clear();

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
                        REPRESENTATIVE_TO_GROUP.put(stack, guid);
                        result.add(stack);
                    }
                }
            }
            return result;
        }
    }

    /**
     * 根据 ingredient 查询所属分组。
     *
     * <p>
     * 该 ingredient 可以是普通成员，也可以是当前投影生成的折叠代表项。
     * </p>
     */
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

    /** 判断 ingredient 是否属于任意已注册分组。 */
    public static boolean isInGroup(EmiIngredient ingredient) {
        return STACK_TO_GROUP.containsKey(ingredient);
    }

    /** 返回折叠代表项对应的次代表项，用于叠层绘制。 */
    @Nullable
    public static EmiIngredient getSecondaryRepresentative(EmiIngredient ingredient) {
        CollapsibleGroup group = getGroup(ingredient);
        if (group == null || !isCollapsedRepresentative(ingredient)) return null;
        return group.secondaryRepresentative;
    }

    /**
     * 按 guid 切换单个分组的展开状态。
     *
     * @param guid 分组唯一标识
     */
    public static void toggleGroup(String guid) {
        CollapsibleGroup group = GROUPS.get(guid);
        if (group != null) {
            group.setExpanded(!group.isExpanded());
        }
    }

    /**
     * 从配置文件读取所有分组的展开状态。
     *
     * <p>
     * 读取失败只记录警告，不阻止 EMI 打开；无法读取时所有分组按默认折叠处理。
     * </p>
     */
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

    /**
     * 将当前展开状态写回配置文件。
     *
     * <p>
     * 只有状态文件已经完成初次读取后才会保存，避免初始化阶段写出不完整状态。
     * </p>
     */
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

    /**
     * 从 JSON 配置文件读取分组规则。
     *
     * <p>
     * 文件不存在时会写出接近 GTNH 默认项的配置。读取或解析失败不会阻止 EMI 打开，只会回退为空规则。
     * </p>
     */
    private static void loadRules() {
        if (rulesLoaded) return;
        rulesLoaded = true;
        ensureDefaultRuleFile();

        List<CollapsibleGroupProvider> definitions = new ArrayList<>();
        addBuiltInTagGroups(definitions);
        try (Reader reader = Files.newBufferedReader(RULE_FILE)) {
            JsonElement root = JsonParser.parseReader(reader);
            if (!root.isJsonObject()) {
                CTNHCore.LOGGER.warn("EMI collapsible group rule file {} must be a JSON object", RULE_FILE);
                configuredProviders = List.of();
                return;
            }

            JsonObject groups = root.getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : groups.entrySet()) {
                RuleGroupDefinition definition = parseGroupDefinition(entry.getKey(), entry.getValue());
                if (definition != null && !definition.rules().isEmpty()) {
                    definitions.add(definition);
                }
            }
        } catch (RuntimeException | IOException e) {
            CTNHCore.LOGGER.warn("Failed to load EMI collapsible group rules from {}", RULE_FILE, e);
        }
        definitions.addAll(loadLegacyRuleFile());
        configuredProviders = List.copyOf(definitions);
    }

    /** 注册整合包内置的标签型折叠组。 */
    private static void addBuiltInTagGroups(List<CollapsibleGroupProvider> definitions) {
        definitions.add(tagGroup("ctnhcore:blocks/logs", "ctnhcore.emi.collapsible.group.logs", "Logs",
                "minecraft:logs"));
        definitions.add(tagGroup("ctnhcore:blocks/stairs", "ctnhcore.emi.collapsible.group.stairs", "Stairs",
                "minecraft:stairs"));
        definitions.add(tagGroup("ctnhcore:blocks/slabs", "ctnhcore.emi.collapsible.group.slabs", "Slabs",
                "minecraft:slabs"));
        definitions.add(tagGroup("ctnhcore:blocks/fences", "ctnhcore.emi.collapsible.group.fences", "Fences",
                "minecraft:fences"));
        definitions.add(tagGroup("ctnhcore:blocks/fence_gates", "ctnhcore.emi.collapsible.group.fence_gates",
                "Fence Gates", "minecraft:fence_gates"));
        definitions.add(tagGroup("ctnhcore:blocks/doors", "ctnhcore.emi.collapsible.group.doors", "Doors",
                "minecraft:doors"));
        definitions.add(tagGroup("ctnhcore:blocks/trapdoors", "ctnhcore.emi.collapsible.group.trapdoors",
                "Trapdoors", "minecraft:trapdoors"));
        definitions.add(tagGroup("ctnhcore:blocks/pressure_plates",
                "ctnhcore.emi.collapsible.group.pressure_plates", "Pressure Plates", "minecraft:pressure_plates"));
        definitions.add(tagGroup("ctnhcore:blocks/buttons", "ctnhcore.emi.collapsible.group.buttons", "Buttons",
                "minecraft:buttons"));
    }

    private static CollapsibleGroupProvider tagGroup(String guid, String translationKey, String fallbackName,
                                                     String tagId) {
        ResourceLocation id = ResourceLocation.tryParse(tagId);
        if (id == null) {
            throw new IllegalArgumentException("Invalid built-in EMI collapsible tag id: " + tagId);
        }
        return new TagGroupDefinition(guid, translationKey, fallbackName, TagKey.create(Registries.ITEM, id),
                TagKey.create(Registries.BLOCK, id));
    }

    /** 写出接近 GTNH NEI 默认折叠项的规则文件。 */
    private static void ensureDefaultRuleFile() {
        if (Files.isRegularFile(RULE_FILE)) return;

        try {
            Files.createDirectories(RULE_FILE.getParent());
            JsonObject defaults = new JsonObject();
            defaults.add("ctnhcore:spawn_eggs",
                    defaultGroup("ctnhcore.emi.collapsible.group.spawn_eggs", "regex:minecraft:.*_spawn_egg"));
            defaults.add("ctnhcore:spawners",
                    defaultGroup("ctnhcore.emi.collapsible.group.spawners", "minecraft:spawner"));
            defaults.add("ctnhcore:music_discs",
                    defaultGroup("ctnhcore.emi.collapsible.group.music_discs", "regex:minecraft:music_disc_.*"));
            defaults.add("ctnhcore:splash_potions",
                    defaultGroup("ctnhcore.emi.collapsible.group.splash_potions", "minecraft:splash_potion"));
            defaults.add("ctnhcore:lingering_potions",
                    defaultGroup("ctnhcore.emi.collapsible.group.lingering_potions", "minecraft:lingering_potion"));
            defaults.add("ctnhcore:tools/swords",
                    defaultGroup("ctnhcore.emi.collapsible.group.swords", "#minecraft:swords", "#forge:tools/swords"));
            defaults.add("ctnhcore:tools/pickaxes",
                    defaultGroup("ctnhcore.emi.collapsible.group.pickaxes", "#minecraft:pickaxes", "#forge:tools/pickaxes"));
            defaults.add("ctnhcore:tools/axes",
                    defaultGroup("ctnhcore.emi.collapsible.group.axes", "#minecraft:axes", "#forge:tools/axes"));
            defaults.add("ctnhcore:tools/shovels",
                    defaultGroup("ctnhcore.emi.collapsible.group.shovels", "#minecraft:shovels", "#forge:tools/shovels"));
            defaults.add("ctnhcore:tools/hoes",
                    defaultGroup("ctnhcore.emi.collapsible.group.hoes", "#minecraft:hoes", "#forge:tools/hoes"));
            try (Writer writer = Files.newBufferedWriter(RULE_FILE)) {
                GSON.toJson(defaults, writer);
            }
        } catch (IOException e) {
            CTNHCore.LOGGER.warn("Failed to create default EMI collapsible group rule file {}", RULE_FILE, e);
        }
    }

    /** 构造默认规则对象。 */
    private static JsonObject defaultGroup(String translationKey, String... rulesIn) {
        JsonObject group = new JsonObject();
        group.addProperty("translationKey", translationKey);
        group.addProperty("priority", 0);
        JsonArray rules = new JsonArray();
        for (String rule : rulesIn) {
            rules.add(rule);
        }
        group.add("rules", rules);
        return group;
    }

    /** 读取可选的 GTNH/NEI 风格 collapsibleitems.cfg。 */
    private static List<CollapsibleGroupProvider> loadLegacyRuleFile() {
        if (!Files.isRegularFile(LEGACY_RULE_FILE)) return List.of();

        List<CollapsibleGroupProvider> definitions = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(LEGACY_RULE_FILE);
            JsonObject settings = new JsonObject();
            int index = 0;
            for (String rawLine : lines) {
                String line = rawLine.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;

                if (line.startsWith(";")) {
                    String json = line.substring(1).trim();
                    if (!json.isEmpty()) {
                        JsonElement element = JsonParser.parseString(json);
                        if (element.isJsonObject()) {
                            settings = element.getAsJsonObject();
                        }
                    }
                    continue;
                }

                String guid = "ctnhcore:legacy/" + index++;
                JsonObject object = new JsonObject();
                if (settings.has("displayName")) object.add("displayName", settings.get("displayName"));
                if (settings.has("expanded")) object.add("expanded", settings.get("expanded"));
                JsonArray rules = new JsonArray();
                rules.add(line);
                object.add("rules", rules);
                RuleGroupDefinition definition = parseGroupDefinition(guid, object);
                if (definition != null) definitions.add(definition);
                settings = new JsonObject();
            }
        } catch (RuntimeException | IOException e) {
            CTNHCore.LOGGER.warn("Failed to load legacy EMI collapsible group rules from {}", LEGACY_RULE_FILE, e);
        }
        return definitions;
    }

    @Nullable
    private static CollapsibleGroupProvider findProvider(String guid) {
        for (CollapsibleGroupProvider provider : configuredProviders) {
            if (provider.guid().equals(guid)) return provider;
        }
        return null;
    }

    /** 解析单个 groupname: rule 定义。 */
    @Nullable
    private static RuleGroupDefinition parseGroupDefinition(String guid, JsonElement element) {
        List<GroupRule> rules = new ArrayList<>();
        String displayName = null;
        String translationKey = null;
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
            if (object.has("displayName")) displayName = object.get("displayName").getAsString();
            if (object.has("translationKey")) translationKey = object.get("translationKey").getAsString();
            if (object.has("expanded")) expanded = object.get("expanded").getAsBoolean();
            if (object.has("priority")) {
                priority = object.get("priority").getAsInt();
                if (priority < 0) {
                    CTNHCore.LOGGER.warn("Ignoring EMI collapsible group {} because priority must be non-negative",
                            guid);
                    return null;
                }
            }
            JsonElement rulesElement = object.get("rules");
            if (rulesElement == null) {
                CTNHCore.LOGGER.warn("Ignoring EMI collapsible group {} because it has no rules", guid);
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
                CTNHCore.LOGGER.warn("Ignoring EMI collapsible group {} because rules is not a string or string array",
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
        return new RuleGroupDefinition(guid, displayName, translationKey, priority, List.copyOf(rules));
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

    /** 创建折叠组并注册其中物品的通用接口。 */
    private interface CollapsibleGroupProvider {

        String guid();

        Component createDisplayName();

        default int priority() {
            return 0;
        }

        boolean matches(EmiIngredient ingredient);

        default CollapsibleGroup createGroup() {
            CollapsibleGroup group = new CollapsibleGroup(guid());
            group.displayName = createDisplayName();
            return group;
        }
    }

    /** 基于配置规则的折叠组定义。 */
    private record RuleGroupDefinition(String guid, @Nullable String displayName, @Nullable String translationKey,
                                       int priority, List<GroupRule> rules) implements CollapsibleGroupProvider {

        @Override
        public Component createDisplayName() {
            if (translationKey != null && !translationKey.isBlank()) {
                return Component.translatableWithFallback(translationKey, guid);
            }
            if (displayName != null && !displayName.isBlank()) {
                return Component.literal(displayName);
            }
            return fallbackDisplayName(guid);
        }

        @Override
        public int priority() {
            return priority;
        }

        @Override
        public boolean matches(EmiIngredient ingredient) {
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

    /** 基于物品或方块标签的代码型折叠组定义。 */
    private record TagGroupDefinition(String guid, String translationKey, String fallbackName,
                                      TagKey<Item> itemTag, TagKey<Block> blockTag) implements CollapsibleGroupProvider {

        @Override
        public Component createDisplayName() {
            return Component.translatableWithFallback(translationKey, fallbackName);
        }

        @Override
        public boolean matches(EmiIngredient ingredient) {
            for (EmiStack stack : ingredient.getEmiStacks()) {
                ItemStack itemStack = stack.getItemStack();
                if (!itemStack.isEmpty() && matchesTag(itemStack, itemTag, blockTag)) {
                    return true;
                }
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
