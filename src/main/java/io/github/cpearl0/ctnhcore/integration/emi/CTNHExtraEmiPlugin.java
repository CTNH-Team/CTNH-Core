package io.github.cpearl0.ctnhcore.integration.emi;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.item.IGTTool;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.common.data.GTMaterialItems;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

import com.github.elenterius.biomancy.init.ModEnchantments;
import com.jesz.createdieselgenerators.CreateDieselGenerators;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.item.tool.CBToolType;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.registry.EmiRecipes;
import tech.luckyblock.mcmod.ctnhenergy.registry.CEItems;

import java.util.*;
import java.util.function.Consumer;

import static com.github.elenterius.biomancy.init.ModItems.DESPOIL_SICKLE;
import static com.moguang.ctnhbio.registry.CBMaterialItems.CB_TOOL_ITEMS;

// This plugin is registered later than any other plugin
public class CTNHExtraEmiPlugin implements EmiPlugin {

    private static Map<ResourceLocation, EmiRecipeCategory> categoryMap = new HashMap<>();

    @Override
    public void register(EmiRegistry registry) {
        for (var category : EmiRecipes.categories) {
            categoryMap.put(category.id, category);
        }

        List<EmiStack> hammers = GTMaterialItems.TOOL_ITEMS.column(GTToolType.HARD_HAMMER)
                .values().stream()
                .filter(Objects::nonNull)
                .map(ItemProviderEntry::asItem)
                .map(EmiStack::of)
                .toList();

        handleCategory(CreateDieselGenerators.rl("hammering"), emiRecipeCategory -> {
            registry.addWorkstation(emiRecipeCategory, EmiIngredient.of(hammers));
        });

        List<EmiStack> wireCutters = GTMaterialItems.TOOL_ITEMS.column(GTToolType.WIRE_CUTTER)
                .values().stream()
                .filter(Objects::nonNull)
                .map(ItemProviderEntry::asItem)
                .map(EmiStack::of)
                .toList();

        handleCategory(CreateDieselGenerators.rl("wire_cutting"), emiRecipeCategory -> {
            registry.addWorkstation(emiRecipeCategory, EmiIngredient.of(wireCutters));
        });

        ItemStack enchantedBook = EnchantedBookItem
                .createForEnchantment(new EnchantmentInstance(ModEnchantments.DESPOIL.get(), 1));
        var boningKnifes = new ArrayList<EmiIngredient>();
        for (ItemProviderEntry<IGTTool> entry : CB_TOOL_ITEMS.column(CBToolType.BONING_KNIFE).values()) {
            if (entry == null) continue;
            ItemStack stack = new ItemStack(entry.get());
            boningKnifes.add(EmiStack.of(stack));
        }

        handleCategory(CTNHBio.id("despoil_loot"), category -> {
            registry.addWorkstation(category, EmiStack.of(DESPOIL_SICKLE.get()));
            registry.addWorkstation(category, EmiStack.of(enchantedBook));
            registry.addWorkstation(category, EmiIngredient.of(boningKnifes));
        });

        handleCategory(GTCEu.id("programmed_circuit"), category -> {
            registry.addWorkstation(category, EmiStack.of(CEItems.PROGRAMMED_CIRCUIT_CARD.get()));
        });
    }

    private static void handleCategory(ResourceLocation id, Consumer<EmiRecipeCategory> consumer) {
        var category = categoryMap.get(id);
        if (category != null) {
            consumer.accept(category);
        }
    }
}
