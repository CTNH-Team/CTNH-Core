package io.github.cpearl0.ctnhcore.utils;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.block.ICoilType;
import com.gregtechceu.gtceu.common.block.CoilBlock;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CoilTierHelper {
    private static final Map<Integer, Map<ICoilType, Supplier<CoilBlock>>> COILS_BY_TIER = new HashMap<>();

    // 静态初始化块，在类加载时构建索引表
    static {
        // 遍历所有加热线圈
        for (Map.Entry<ICoilType, Supplier<CoilBlock>> entry : GTCEuAPI.HEATING_COILS.entrySet()) {
            ICoilType coilType = entry.getKey();
            int tier = coilType.getTier();

            // 如果该tier还没有对应的映射，创建一个新的
            COILS_BY_TIER.computeIfAbsent(tier, k -> new HashMap<>())
                    .put(coilType, entry.getValue());
        }
    }

    public static CoilBlock[] getCoilBlocks(int tier) {
        return COILS_BY_TIER.getOrDefault(tier, new HashMap<>())
                .values()
                .stream()
                .map(Supplier::get)
                .toArray(CoilBlock[]::new);
    }
}
