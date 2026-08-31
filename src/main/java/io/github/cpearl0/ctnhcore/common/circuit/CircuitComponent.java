package io.github.cpearl0.ctnhcore.common.circuit;

import io.github.cpearl0.ctnhcore.registry.CTNHCircuitItems;

import com.gregtechceu.gtceu.common.data.GTItems;

import net.minecraft.world.item.Item;

import com.tterrag.registrate.util.entry.ItemEntry;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 可在设计台上摆放的电路元件。全部对应 GTM 原版物品（电阻/电容/二极管/晶体管/电感各含
 * 普通/SMD/高级 SMD 三档升级线，芯片用 GTM 的 CPU/Nano-CPU/Qubit-CPU），
 * GTM 没有的（晶振、散热片）才是 CTNH 自定义物品。
 * 升级线收益：同占地、func +1、heat/noise 各 −1（不低于 0），但要求更高级基板。
 * 数值见 docs/design/circuit-board-system.md §4.1。
 * noise（噪声辐射强度）参与 §4.4 噪声场结算（晶振/芯片敏感）。
 */
public enum CircuitComponent {

    // 无源三族（普通 → SMD → 高级 SMD）
    RESISTOR("resistor", Family.RESISTOR, Category.PASSIVE, 1, 1, 0, SubstrateTier.COATED, 1, 1,
            () -> GTItems.RESISTOR),
    SMD_RESISTOR("smd_resistor", Family.RESISTOR, Category.PASSIVE, 2, 0, 0, SubstrateTier.PLASTIC, 1, 1,
            () -> GTItems.SMD_RESISTOR),
    ADVANCED_SMD_RESISTOR("advanced_smd_resistor", Family.RESISTOR, Category.PASSIVE, 3, 0, 0, SubstrateTier.FIBER,
            1, 1, () -> GTItems.ADVANCED_SMD_RESISTOR),
    CAPACITOR("capacitor", Family.CAPACITOR, Category.PASSIVE, 1, 0, 0, SubstrateTier.COATED, 1, 1,
            () -> GTItems.CAPACITOR),
    SMD_CAPACITOR("smd_capacitor", Family.CAPACITOR, Category.PASSIVE, 2, 0, 0, SubstrateTier.PLASTIC, 1, 1,
            () -> GTItems.SMD_CAPACITOR),
    ADVANCED_SMD_CAPACITOR("advanced_smd_capacitor", Family.CAPACITOR, Category.PASSIVE, 3, 0, 0, SubstrateTier.FIBER,
            1, 1, () -> GTItems.ADVANCED_SMD_CAPACITOR),
    DIODE("diode", Family.DIODE, Category.PASSIVE, 1, 1, 0, SubstrateTier.COATED, 1, 1,
            () -> GTItems.DIODE),
    SMD_DIODE("smd_diode", Family.DIODE, Category.PASSIVE, 2, 0, 0, SubstrateTier.PLASTIC, 1, 1,
            () -> GTItems.SMD_DIODE),
    ADVANCED_SMD_DIODE("advanced_smd_diode", Family.DIODE, Category.PASSIVE, 3, 0, 0, SubstrateTier.FIBER, 1, 1,
            () -> GTItems.ADVANCED_SMD_DIODE),
    // 有源
    VACUUM_TUBE("vacuum_tube", Family.VACUUM_TUBE, Category.ACTIVE, 2, 3, 1, SubstrateTier.COATED, 1, 1,
            () -> GTItems.VACUUM_TUBE),
    TRANSISTOR("transistor", Family.TRANSISTOR, Category.ACTIVE, 3, 2, 1, SubstrateTier.PHENOLIC, 1, 1,
            () -> GTItems.TRANSISTOR),
    SMD_TRANSISTOR("smd_transistor", Family.TRANSISTOR, Category.ACTIVE, 4, 1, 0, SubstrateTier.PLASTIC, 1, 1,
            () -> GTItems.SMD_TRANSISTOR),
    ADVANCED_SMD_TRANSISTOR("advanced_smd_transistor", Family.TRANSISTOR, Category.ACTIVE, 5, 0, 0,
            SubstrateTier.FIBER, 1, 1, () -> GTItems.ADVANCED_SMD_TRANSISTOR),
    INDUCTOR("inductor", Family.INDUCTOR, Category.ACTIVE, 2, 1, 3, SubstrateTier.PLASTIC, 1, 1,
            () -> GTItems.INDUCTOR),
    SMD_INDUCTOR("smd_inductor", Family.INDUCTOR, Category.ACTIVE, 3, 0, 2, SubstrateTier.EPOXY, 1, 1,
            () -> GTItems.SMD_INDUCTOR),
    ADVANCED_SMD_INDUCTOR("advanced_smd_inductor", Family.INDUCTOR, Category.ACTIVE, 4, 0, 1,
            SubstrateTier.MULTILAYER, 1, 1, () -> GTItems.ADVANCED_SMD_INDUCTOR),
    // 芯片与时钟
    OSCILLATOR("oscillator", Family.OSCILLATOR, Category.CHIP, 3, 0, 0, SubstrateTier.EPOXY, 1, 1,
            () -> CTNHCircuitItems.CIRCUIT_OSCILLATOR),
    CHIP("chip", Family.CHIP, Category.CHIP, 8, 5, 2, SubstrateTier.FIBER, 2, 1,
            () -> GTItems.CENTRAL_PROCESSING_UNIT),
    NANO_CHIP("nano_chip", Family.CHIP, Category.CHIP, 12, 6, 2, SubstrateTier.MULTILAYER, 2, 1,
            () -> GTItems.NANO_CENTRAL_PROCESSING_UNIT),
    QUBIT_CHIP("qubit_chip", Family.CHIP, Category.CHIP, 16, 7, 3, SubstrateTier.WETWARE, 2, 1,
            () -> GTItems.QUBIT_CENTRAL_PROCESSING_UNIT),
    // 结构
    HEATSINK("heatsink", Family.HEATSINK, Category.STRUCTURE, 0, -4, 0, SubstrateTier.EPOXY, 1, 1,
            () -> CTNHCircuitItems.CIRCUIT_HEATSINK);

    @Getter
    private final String id;
    @Getter
    private final Family family;
    @Getter
    private final Category category;
    @Getter
    private final int func;
    @Getter
    private final int heat;
    @Getter
    private final int noise;
    @Getter
    private final SubstrateTier minTier;
    private final int baseWidth;
    private final int baseHeight;
    private final Supplier<ItemEntry<? extends Item>> item;

    CircuitComponent(String id, Family family, Category category, int func, int heat, int noise,
                     SubstrateTier minTier, int baseWidth, int baseHeight,
                     Supplier<ItemEntry<? extends Item>> item) {
        this.id = id;
        this.family = family;
        this.category = category;
        this.func = func;
        this.heat = heat;
        this.noise = noise;
        this.minTier = minTier;
        this.baseWidth = baseWidth;
        this.baseHeight = baseHeight;
        this.item = item;
    }

    /** 朝向为偶数时元件横向（占宽 = baseWidth），奇数时竖向。 */
    public static boolean isHorizontal(int rotation) {
        return (rotation & 1) == 0;
    }

    public int width(int rotation) {
        return isHorizontal(rotation) ? baseWidth : baseHeight;
    }

    public int height(int rotation) {
        return isHorizontal(rotation) ? baseHeight : baseWidth;
    }

    public ItemEntry<? extends Item> item() {
        return item.get();
    }

    /** 布局存储编码：ordinal + 1（0 保留为空格；负值表示被多格元件覆盖的扩展格）。 */
    public byte cellId() {
        return (byte) (ordinal() + 1);
    }

    @Nullable
    public static CircuitComponent byCellId(byte cellId) {
        if (cellId == 0) return null;
        int ordinal = Math.abs(cellId) - 1;
        return ordinal < values().length ? values()[ordinal] : null;
    }

    /** 物品 → 元件反查（玩家手持元件物品标记格子用）。惰性构建，首次调用时物品已注册完毕。 */
    @Nullable
    public static CircuitComponent byItem(Item item) {
        if (BY_ITEM == null) {
            Map<Item, CircuitComponent> map = new IdentityHashMap<>();
            for (CircuitComponent component : values()) {
                map.put(component.item().get(), component);
            }
            BY_ITEM = map;
        }
        return BY_ITEM.get(item);
    }

    @Nullable
    private static Map<Item, CircuitComponent> BY_ITEM;

    /** 元件族：邻接规则（R1 RC 对、R2 去耦、R3 LC、R5 阵列）按族判定，升级件与同族基础件等价参与。 */
    public enum Family {
        RESISTOR,
        CAPACITOR,
        DIODE,
        VACUUM_TUBE,
        TRANSISTOR,
        INDUCTOR,
        OSCILLATOR,
        CHIP,
        HEATSINK
    }

    /** 调色板分页签。 */
    public enum Category {
        PASSIVE,
        ACTIVE,
        CHIP,
        STRUCTURE
    }
}
