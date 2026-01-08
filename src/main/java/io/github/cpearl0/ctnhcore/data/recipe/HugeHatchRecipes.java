package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.hugehatch.HugeDualHatchPartMachine;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;

import appeng.core.definitions.AEItems;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLER_RECIPES;
import static com.gregtechceu.gtceu.data.recipe.CustomTags.CIRCUITS_ARRAY;
import static io.github.cpearl0.ctnhcore.registry.CTNHMachines.*;

public class HugeHatchRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        for (var tier : GTValues.tiersBetween(ULV, OpV)) {
            var import_bus = HUGE_ITEM_IMPORT_BUS[tier].asStack();
            var export_bus = HUGE_ITEM_EXPORT_BUS[tier].asStack();
            var dual_input_hatch = HUGE_DUAL_IMPORT_HATCH[tier].asStack();
            var dual_output_hatch = HUGE_DUAL_EXPORT_HATCH[tier].asStack();
            var chest = tier <= EV ? SUPER_CHEST[tier] : QUANTUM_CHEST[tier];
            var tank = tier <= EV ? SUPER_TANK[tier] : QUANTUM_TANK[tier];
            var slotSize = 1 + tier;
            var tankSize = HugeDualHatchPartMachine.getTankSize(tier);
            if (tier != ULV) {
                ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("huge_import_bus_" + VN[tier].toLowerCase()))
                        .inputItems(CIRCUITS_ARRAY[tier])
                        .inputItems(ITEM_IMPORT_BUS[tier])
                        .inputItems(chest, slotSize)
                        .outputItems(import_bus)
                        .EUt(VA[tier])
                        .duration(100)
                        .save(provider);

                ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("huge_export_bus_" + VN[tier].toLowerCase()))
                        .inputItems(CIRCUITS_ARRAY[tier])
                        .inputItems(ITEM_EXPORT_BUS[tier])
                        .inputItems(chest, slotSize)
                        .outputItems(export_bus)
                        .EUt(VA[tier])
                        .duration(100)
                        .save(provider);

                ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("huge_dual_input_hatch_" + VN[tier].toLowerCase()))
                        .inputItems(CIRCUITS_ARRAY[tier])
                        .inputItems(import_bus)
                        .inputItems(tank, tankSize)
                        .outputItems(dual_input_hatch)
                        .EUt(VA[tier])
                        .duration(100)
                        .save(provider);

                ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("huge_dual_output_hatch_" + VN[tier].toLowerCase()))
                        .inputItems(CIRCUITS_ARRAY[tier])
                        .inputItems(export_bus)
                        .inputItems(tank, tankSize)
                        .outputItems(dual_output_hatch)
                        .EUt(VA[tier])
                        .duration(100)
                        .save(provider);
            } else {
                ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("huge_import_bus_" + VN[tier].toLowerCase()))
                        .inputItems(AEItems.SINGULARITY.asItem())
                        .inputItems(ITEM_IMPORT_BUS[tier])
                        .outputItems(import_bus)
                        .EUt(VA[tier])
                        .duration(100)
                        .save(provider);

                ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("huge_export_bus_" + VN[tier].toLowerCase()))
                        .inputItems(AEItems.SINGULARITY.asItem())
                        .inputItems(ITEM_EXPORT_BUS[tier])
                        .outputItems(export_bus)
                        .EUt(VA[tier])
                        .duration(100)
                        .save(provider);

                ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("huge_dual_input_hatch_" + VN[tier].toLowerCase()))
                        .inputItems(AEItems.SINGULARITY.asItem())
                        .inputItems(import_bus)
                        .outputItems(dual_input_hatch)
                        .EUt(VA[tier])
                        .duration(100)
                        .save(provider);

                ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("huge_dual_output_hatch_" + VN[tier].toLowerCase()))
                        .inputItems(AEItems.SINGULARITY.asItem())
                        .inputItems(export_bus)
                        .outputItems(dual_output_hatch)
                        .EUt(VA[tier])
                        .duration(100)
                        .save(provider);
            }

            VanillaRecipeHelper.addShapedRecipe(provider,
                    "huge_item_bus_output_to_input_" + VN[tier].toLowerCase(), import_bus,
                    "d", "B", 'B', export_bus);
            VanillaRecipeHelper.addShapedRecipe(provider,
                    "huge_item_bus_input_to_output_" + VN[tier].toLowerCase(), export_bus,
                    "d", "B", 'B', import_bus);
            VanillaRecipeHelper.addShapedRecipe(provider,
                    "huge_dual_hatch_output_to_input_" + VN[tier].toLowerCase(), dual_input_hatch,
                    "d", "B", 'B', dual_output_hatch);
            VanillaRecipeHelper.addShapedRecipe(provider,
                    "huge_dual_hatch_input_to_output_" + VN[tier].toLowerCase(), dual_output_hatch,
                    "d", "B", 'B', dual_input_hatch);

        }
    }
}
