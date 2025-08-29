package io.github.cpearl0.ctnhcore.data.lang;

import static io.github.cpearl0.ctnhcore.registry.CTNHBlocks.*;

public class BlockLang {
    public static void init(CTNHLangProvider provider) {
        provider.addBlockName(ADVANCED_FILTER_CASING, "Advanced Filter Casing", "高级过滤器机械方块");
        provider.addBlockName(IRIDIUM_CASING, "Iridium Casing", "铱机械方块");
        provider.addBlockName(
                CASING_POLYBENZIMIDAZOLE_PIPE, "Casing Polybenzimidazole Pipe", "聚苯并咪唑管道方块"
        );
        provider.addBlockName(PROCESS_MACHINE_CASING, "洁净机械方块");
        provider.addBlockName(FIELD_RESTRICTION_CASING, "立场约束机械方块");
        provider.addBlockName(RADIATION_PROOF_MACHINE_CASING, "防辐射机械方块");
        provider.addBlockName(SUPER_FREEZE_BLOCK, "Super Freeze Block", "超级冷冻外壳");
        provider.addBlockName(TWISTED_FUSION_CASING, "Twisted Fusion Casing", "扭曲聚变外壳");
    }
}
