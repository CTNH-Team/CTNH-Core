package io.github.cpearl0.ctnhcore.common.circuit;

import net.minecraft.network.chat.MutableComponent;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

/**
 * 词条 id 常量（写入图纸 NBT 的 CircuitStats.traits）与显示名。判定条件与效果见设计文档 §4.6。
 * ⚡ 高频设计与 🛡 抗干扰设计互斥（高频优先）。
 */
public final class CircuitTraits {

    /** ≥3 对 RC → 稳定 ×1.08 */
    public static final String RC_FILTER = "rc_filter";
    /** 晶振效率 ≥90% 且 ≥1 组 LC → 速度 ×1.10、稳定 ×0.90 */
    public static final String HIGH_FREQUENCY = "high_frequency";
    /** 存在噪声源与敏感件且全场无噪声超标 → 稳定 ×1.15、速度 ×0.95 */
    public static final String ANTI_INTERFERENCE = "anti_interference";
    /** 芯片 ≥3 个正交方向紧邻二极管 → 良品率 +8%（§六组装机生效） */
    public static final String PROTECTION = "protection";
    /** 两颗芯片相邻 → 稳定 +10（并行惩罚留待 §七） */
    public static final String REDUNDANCY = "redundancy";
    /** ≥1 散热片且全场无降额 → 能效 +8 */
    public static final String THERMAL = "thermal";

    @CN("RC 滤波网络")
    @EN("RC Filter Network")
    @Key("ctnhcore.circuit.trait.rc_filter")
    public static Lang traitRcFilter;

    @CN("§e⚡ 高频设计")
    @EN("§e⚡ High-Frequency")
    @Key("ctnhcore.circuit.trait.high_frequency")
    public static Lang traitHighFrequency;

    @CN("§b🛡 抗干扰设计")
    @EN("§b🛡 Anti-Interference")
    @Key("ctnhcore.circuit.trait.anti_interference")
    public static Lang traitAntiInterference;

    @CN("保护设计")
    @EN("Protected")
    @Key("ctnhcore.circuit.trait.protection")
    public static Lang traitProtection;

    @CN("冗余设计")
    @EN("Redundant")
    @Key("ctnhcore.circuit.trait.redundancy")
    public static Lang traitRedundancy;

    @CN("散热优良")
    @EN("Well-Cooled")
    @Key("ctnhcore.circuit.trait.thermal")
    public static Lang traitThermal;

    private CircuitTraits() {}

    /** 按 NBT 中的词条 id 取显示名；未知 id 显示原始 id（不静默吞掉）。 */
    public static MutableComponent displayName(String traitId) {
        Lang lang = switch (traitId) {
            case RC_FILTER -> traitRcFilter;
            case HIGH_FREQUENCY -> traitHighFrequency;
            case ANTI_INTERFERENCE -> traitAntiInterference;
            case PROTECTION -> traitProtection;
            case REDUNDANCY -> traitRedundancy;
            case THERMAL -> traitThermal;
            default -> null;
        };
        return lang != null ? lang.translate() : net.minecraft.network.chat.Component.literal(traitId);
    }
}
