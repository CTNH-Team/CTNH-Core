package io.github.cpearl0.ctnhcore.common.circuit;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GTItems;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import com.tterrag.registrate.util.entry.ItemEntry;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * 电路基板等级：直接复用 GTM 原版七级电路基板链（覆膜 → 湿件），
 * 决定网格尺寸、热阈值系数与并行上限。数值见 docs/design/circuit-board-system.md §4.2。
 * 网格最大 9×9（UI 限制），高等级基板以热系数与并行上限拉开差距。
 */
public enum SubstrateTier {

    COATED("coated", 5, 5, 1.0, 1, GTValues.ULV, () -> GTItems.COATED_BOARD),
    PHENOLIC("phenolic", 7, 7, 1.1, 2, GTValues.LV, () -> GTItems.PHENOLIC_BOARD),
    PLASTIC("plastic", 7, 7, 1.25, 2, GTValues.MV, () -> GTItems.PLASTIC_BOARD),
    EPOXY("epoxy", 9, 7, 1.4, 4, GTValues.HV, () -> GTItems.EPOXY_BOARD),
    FIBER("fiber", 9, 9, 1.6, 4, GTValues.HV, () -> GTItems.FIBER_BOARD),
    MULTILAYER("multilayer", 9, 9, 1.85, 6, GTValues.EV, () -> GTItems.MULTILAYER_FIBER_BOARD),
    WETWARE("wetware", 9, 9, 2.1, 8, GTValues.LuV, () -> GTItems.WETWARE_BOARD);

    private final String id;
    private final int gridWidth;
    private final int gridHeight;
    private final double thermalFactor;
    private final int parallelCap;
    /** 组装该基板所需机器的电压等级（GTValues 索引）。 */
    private final int voltageTier;
    private final Supplier<ItemEntry<Item>> item;

    SubstrateTier(String id, int gridWidth, int gridHeight, double thermalFactor, int parallelCap,
                  int voltageTier, Supplier<ItemEntry<Item>> item) {
        this.id = id;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.thermalFactor = thermalFactor;
        this.voltageTier = voltageTier;
        this.parallelCap = parallelCap;
        this.item = item;
    }

    public String getId() {
        return id;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public double getThermalFactor() {
        return thermalFactor;
    }

    public int getParallelCap() {
        return parallelCap;
    }

    public int getVoltageTier() {
        return voltageTier;
    }

    /** 该基板是否允许摆放指定元件（元件有最低基板等级要求）。 */
    public boolean allows(CircuitComponent component) {
        return component.getMinTier().ordinal() <= ordinal();
    }

    /** 热场警告阈值（超过后元件降额 0.85）。 */
    public double warnThreshold() {
        return 6 * thermalFactor;
    }

    /** 热场临界阈值（超过后元件降额 0.5 并计入降额惩罚）。 */
    public double critThreshold() {
        return 10 * thermalFactor;
    }

    public ItemEntry<Item> item() {
        return item.get();
    }

    @Nullable
    public static SubstrateTier fromStack(ItemStack stack) {
        if (stack.isEmpty()) return null;
        for (SubstrateTier tier : values()) {
            if (stack.is(tier.item().get())) return tier;
        }
        return null;
    }

    @Nullable
    public static SubstrateTier byId(String id) {
        for (SubstrateTier tier : values()) {
            if (tier.id.equals(id)) return tier;
        }
        return null;
    }
}
