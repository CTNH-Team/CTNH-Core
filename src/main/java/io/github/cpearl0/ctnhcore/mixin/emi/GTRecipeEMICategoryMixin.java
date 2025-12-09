package io.github.cpearl0.ctnhcore.mixin.emi;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.category.GTRecipeCategory;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.integration.emi.recipe.GTRecipeEMICategory;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import io.github.cpearl0.ctnhcore.utils.CTNHMachineUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.gregtechceu.gtceu.integration.emi.recipe.GTRecipeEMICategory.machineCategory;
import static com.gregtechceu.gtceu.integration.emi.recipe.GTRecipeEMICategory.sortDefinition;

@Mixin(value = GTRecipeEMICategory.class, remap = false)
public class GTRecipeEMICategoryMixin {
    /**
     * @author
     * @reason
     */
    @Overwrite
    public static void registerWorkStations(EmiRegistry registry) {
        // key: EmiRecipeCategory
        // value: 分类下的多方块 & 普通机器
        Map<EmiRecipeCategory, CTNHMachineUtils.CategoryBuckets> bucketMap = new HashMap<>();

        for (MachineDefinition machine : GTRegistries.MACHINES.values()
                .stream()
                .sorted(sortDefinition)
                .toList()) {

            for (GTRecipeType type : machine.getRecipeTypes()) {
                for (GTRecipeCategory category : type.getCategories()) {
                    if (!category.isXEIVisible() && !GTCEu.isDev()) continue;

                    EmiRecipeCategory emiCat = machineCategory(category);

                    bucketMap.computeIfAbsent(emiCat, c -> new CTNHMachineUtils.CategoryBuckets())
                            .addMachine(machine);
                }
            }
        }

        // 注册阶段
        for (Map.Entry<EmiRecipeCategory, CTNHMachineUtils.CategoryBuckets> e : bucketMap.entrySet()) {
            EmiRecipeCategory category = e.getKey();
            CTNHMachineUtils.CategoryBuckets buckets = e.getValue();
            //普通机器：合并成一个 EmiIngredient
            if (!buckets.singles.isEmpty()) {
                List<EmiIngredient> list = buckets.singles.stream()
                        .map(m -> EmiStack.of(m.asStack()))
                        .collect(Collectors.toList());

                EmiIngredient merged = EmiIngredient.of(list); // 合并
                registry.addWorkstation(category, merged);
            }

            //多方块机器：保持原有行为，一个个注册
            for (MachineDefinition m : buckets.multiblocks) {
                registry.addWorkstation(category, EmiStack.of(m.asStack()));
            }

        }
    }

}
