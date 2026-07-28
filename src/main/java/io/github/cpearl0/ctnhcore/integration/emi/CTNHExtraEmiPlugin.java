package io.github.cpearl0.ctnhcore.integration.emi;

import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.common.data.GTMaterialItems;

import com.jesz.createdieselgenerators.CreateDieselGenerators;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.registry.EmiRecipes;

import java.util.List;
import java.util.Objects;

// This plugin is registered later than any other plugin
public class CTNHExtraEmiPlugin implements EmiPlugin {

    @Override
    public void register(EmiRegistry registry) {
        List<EmiStack> hammers = GTMaterialItems.TOOL_ITEMS.column(GTToolType.HARD_HAMMER)
                .values().stream()
                .filter(Objects::nonNull)
                .map(ItemProviderEntry::asItem)
                .map(EmiStack::of)
                .toList();

        EmiRecipes.categories.stream()
                .filter(c -> c.getId().equals(CreateDieselGenerators.rl("hammering")))
                .findFirst()
                .ifPresent(emiRecipeCategory -> {
                    registry.addWorkstation(emiRecipeCategory, EmiIngredient.of(hammers));
                });

        List<EmiStack> wireCutters = GTMaterialItems.TOOL_ITEMS.column(GTToolType.WIRE_CUTTER)
                .values().stream()
                .filter(Objects::nonNull)
                .map(ItemProviderEntry::asItem)
                .map(EmiStack::of)
                .toList();

        EmiRecipes.categories.stream()
                .filter(c -> c.getId().equals(CreateDieselGenerators.rl("wire_cutting")))
                .findFirst()
                .ifPresent(emiRecipeCategory -> {
                    registry.addWorkstation(emiRecipeCategory, EmiIngredient.of(wireCutters));
                });
    }
}
