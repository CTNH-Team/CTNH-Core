package io.github.cpearl0.ctnhcore.integration.emi;

import com.gregtechceu.gtceu.api.item.IGTTool;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.common.data.GTMaterialItems;

import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

import com.github.elenterius.biomancy.init.ModEnchantments;
import com.jesz.createdieselgenerators.CreateDieselGenerators;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.item.tool.CBToolType;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.registry.EmiRecipes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.github.elenterius.biomancy.init.ModItems.DESPOIL_SICKLE;
import static com.moguang.ctnhbio.registry.CBMaterialItems.CB_TOOL_ITEMS;

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

        ItemStack enchantedBook = EnchantedBookItem
                .createForEnchantment(new EnchantmentInstance(ModEnchantments.DESPOIL.get(), 1));
        var list = new ArrayList<EmiIngredient>();
        for (ItemProviderEntry<IGTTool> entry : CB_TOOL_ITEMS.column(CBToolType.BONING_KNIFE).values()) {
            if (entry == null) continue;
            ItemStack stack = new ItemStack(entry.get());
            list.add(EmiIngredient.of(Ingredient.of(stack)));
        }

        EmiRecipes.categories.stream()
                .filter(c -> c.id.equals(CTNHBio.id("despoil_loot")))
                .findFirst()
                .ifPresent(category -> {
                    registry.addWorkstation(category, EmiIngredient.of(Ingredient.of(DESPOIL_SICKLE.get())));
                    registry.addWorkstation(category, EmiIngredient.of(Ingredient.of(enchantedBook)));
                    registry.addWorkstation(category, EmiIngredient.of(list));
                });
    }
}
