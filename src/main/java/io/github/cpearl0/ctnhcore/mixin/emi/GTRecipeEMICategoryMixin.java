package io.github.cpearl0.ctnhcore.mixin.emi;

import io.github.cpearl0.ctnhcore.utils.CTNHMachineUtils;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.category.GTRecipeCategory;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.integration.emi.recipe.GTRecipeEMICategory;

import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.gregtechceu.gtceu.integration.emi.recipe.GTRecipeEMICategory.machineCategory;
import static com.gregtechceu.gtceu.integration.emi.recipe.GTRecipeEMICategory.sortDefinition;

@Mixin(value = GTRecipeEMICategory.class, remap = false)
public class GTRecipeEMICategoryMixin {

    /**
     * 结构缓存：只构建一次
     */
    @Unique
    private static Map<EmiRecipeCategory, CTNHMachineUtils.CategoryBuckets> CACHED_BUCKETS;

    @Unique
    private static Map<MachineDefinition, EmiStack> MACHINE_STACK_CACHE;

    /**
     * 构建 bucket（仅一次）
     */
    @Unique
    private static void buildBucketsIfNeeded() {
        if (CACHED_BUCKETS != null) return;

        CACHED_BUCKETS = new HashMap<>();
        MACHINE_STACK_CACHE = new HashMap<>();

        for (MachineDefinition machine : GTRegistries.MACHINES.values()
                .stream()
                .sorted(sortDefinition)
                .toList()) {

            EmiStack stack = EmiStack.of(machine.asStack());
            MACHINE_STACK_CACHE.put(machine, stack);

            for (GTRecipeType type : machine.getRecipeTypes()) {
                for (GTRecipeCategory category : type.getCategories()) {
                    if (!category.isXEIVisible() && !GTCEu.isDev()) continue;

                    EmiRecipeCategory emiCat = machineCategory(category);

                    CACHED_BUCKETS
                            .computeIfAbsent(
                                    emiCat,
                                    c -> new CTNHMachineUtils.CategoryBuckets()
                            )
                            .addMachine(machine);
                }
            }
        }
    }

    /**
     * @author
     * @reason 合并 workstation 显示，保持 GTRecipeEMICategory 的缓存策略
     */
    @Overwrite
    public static void registerWorkStations(EmiRegistry registry) {
        buildBucketsIfNeeded();

        for (Map.Entry<EmiRecipeCategory, CTNHMachineUtils.CategoryBuckets> e
                : CACHED_BUCKETS.entrySet()) {

            EmiRecipeCategory category = e.getKey();
            CTNHMachineUtils.CategoryBuckets buckets = e.getValue();

            // 普通机器：合并
            if (!buckets.singles.isEmpty()) {
                List<EmiIngredient> mergedList = buckets.singles.stream()
                        .map(m -> MACHINE_STACK_CACHE.get(m))
                        .collect(Collectors.toList());

                registry.addWorkstation(
                        category,
                        EmiIngredient.of(mergedList)
                );
            }

            // 多方块：逐个
            for (MachineDefinition m : buckets.multiblocks) {
                registry.addWorkstation(
                        category,
                        MACHINE_STACK_CACHE.get(m)
                );
            }
        }
    }
}

