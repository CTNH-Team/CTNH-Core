package io.github.cpearl0.ctnhcore.common.circuit;

import net.minecraft.nbt.CompoundTag;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.Arrays;
import java.util.List;

/**
 * 布局结算结果（写入图纸 NBT）。语义与钳位区间见 docs/design/circuit-board-system.md §4.0。
 * 四项属性的显示名与共用提示行文案就近放在这里，设计台面板、图纸与成品板 tooltip 共用。
 */
public record CircuitStats(int tier, int speed, int efficiency, int stability, int parallel, int complexity,
                           List<String> traits) {

    @CN("速度")
    @EN("Speed")
    @Key("ctnhcore.circuit.speed")
    public static Lang speedLabel;

    @CN("能效")
    @EN("Efficiency")
    @Key("ctnhcore.circuit.efficiency")
    public static Lang efficiencyLabel;

    @CN("稳定性")
    @EN("Stability")
    @Key("ctnhcore.circuit.stability")
    public static Lang stabilityLabel;

    @CN("并行度")
    @EN("Parallelism")
    @Key("ctnhcore.circuit.parallel")
    public static Lang parallelLabel;

    @CN("复杂度")
    @EN("Complexity")
    @Key("ctnhcore.circuit.complexity")
    public static Lang complexityLabel;

    @CN("等效电路：%s 级")
    @EN("Circuit Tier: %s")
    @Key("ctnhcore.circuit.equivalent_tier")
    public static Lang equivalentTier;

    @CN("基板：%s")
    @EN("Substrate: %s")
    @Key("ctnhcore.circuit.tooltip_substrate")
    public static Lang tooltipSubstrate;

    @CN("速度 %s%% / 能效 %s%% / 稳定 %s%% / 并行 %s")
    @EN("Speed %s%% / Eff %s%% / Stab %s%% / Par %s")
    @Key("ctnhcore.circuit.tooltip_stats")
    public static Lang tooltipStats;

    @CN("复杂度：%s")
    @EN("Complexity: %s")
    @Key("ctnhcore.circuit.tooltip_complexity")
    public static Lang tooltipComplexity;

    public static final CircuitStats EMPTY = new CircuitStats(0, 100, 115, 100, 1, 0, List.of());

    public CompoundTag toNbt() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("tier", tier);
        tag.putInt("speed", speed);
        tag.putInt("efficiency", efficiency);
        tag.putInt("stability", stability);
        tag.putInt("parallel", parallel);
        tag.putInt("complexity", complexity);
        tag.putString("traits", String.join(";", traits));
        return tag;
    }

    public static CircuitStats fromNbt(CompoundTag tag) {
        String traitsRaw = tag.getString("traits");
        List<String> traits = traitsRaw.isEmpty() ? List.of() : Arrays.asList(traitsRaw.split(";"));
        return new CircuitStats(tag.getInt("tier"), tag.getInt("speed"), tag.getInt("efficiency"),
                tag.getInt("stability"), tag.getInt("parallel"), tag.getInt("complexity"), traits);
    }
}
