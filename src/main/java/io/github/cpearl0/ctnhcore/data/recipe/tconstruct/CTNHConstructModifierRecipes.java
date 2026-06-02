package io.github.cpearl0.ctnhcore.data.recipe.tconstruct;

// import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;

import io.github.cpearl0.ctnhcore.registry.CTNHConstructModifier;
import io.github.cpearl0.ctnhcore.utils.CTNHConstructRecipeProvider;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.recipe.modifiers.adding.ModifierRecipeBuilder;
import slimeknights.tconstruct.library.tools.SlotType;
import twilightforest.init.TFItems;

import java.util.function.Consumer;

public class CTNHConstructModifierRecipes extends CTNHConstructRecipeProvider {

    public CTNHConstructModifierRecipes(PackOutput generator) {
        super(generator);
    }

    @Override
    public String getType() {
        return "tinker/modifier";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        abilityRecipes(consumer);
    }

    private void abilityRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "ability/";

        ModifierRecipeBuilder.modifier(CTNHConstructModifier.Ids.GLOBAL_TRAVELLER)
                .addInput(ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse("ars_nouveau:stable_warp_scroll")))
                .exactLevel(1)
                .setMaxLevel(1)
                .setSlots(SlotType.ABILITY, 1)
                .setTools(TinkerTags.Items.DURABILITY)
                .disallowCrystal()
                .save(consumer, location(folder + "global_traveller"));

        // 迁移自 kubejs/startup_scripts/src/tconstruct_modifiers_register.js。
        ModifierRecipeBuilder.modifier(CTNHConstructModifier.Ids.FORTIFICATION)
                .addInput(TFItems.FORTIFICATION_SCEPTER.get(), 1)
                .setMaxLevel(5)
                .setSlots(SlotType.UPGRADE, 1)
                .setTools(TinkerTags.Items.ARMOR)
                .disallowCrystal()
                .save(consumer, location(folder + "fortification"));
    }
}
