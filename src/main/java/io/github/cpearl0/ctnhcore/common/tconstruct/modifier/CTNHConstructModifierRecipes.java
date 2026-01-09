package io.github.cpearl0.ctnhcore.common.tconstruct.modifier;


import com.simibubi.create.AllItems;
import io.github.cpearl0.ctnhcore.utils.CTNHTICRecipeProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.recipe.modifiers.adding.ModifierRecipeBuilder;
import slimeknights.tconstruct.library.tools.SlotType;
import io.github.cpearl0.ctnhcore.registry.CTNHTicModifier;

import java.util.function.Consumer;

public class CTNHModifierRecipes extends CTNHTICRecipeProvider {

    public CTNHModifierRecipes(PackOutput output) {
        super(output);
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

        ModifierRecipeBuilder.modifier(CTNHTicModifier.Ids.GLOBALTRAVELLER)
                .addInput(AllItems.ANDESITE_ALLOY)
                .addInput(AllItems.ANDESITE_ALLOY)
                .addInput(Items.IRON_NUGGET)
                .addInput(Items.IRON_NUGGET)
                .exactLevel(1)
                .setSlots(SlotType.UPGRADE, 1)
                .setTools(TinkerTags.Items.BOOTS)
                .disallowCrystal()
                .save(consumer, location(folder + "global_traveller"));
    }
}
