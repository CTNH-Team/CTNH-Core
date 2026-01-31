package io.github.cpearl0.ctnhcore.common.tconstruct.recipes;


import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import io.github.cpearl0.ctnhcore.utils.CTNHConstructRecipeProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.recipe.modifiers.adding.ModifierRecipeBuilder;
import slimeknights.tconstruct.library.tools.SlotType;
import io.github.cpearl0.ctnhcore.registry.CTNHConstructModifier;

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

        ModifierRecipeBuilder.modifier(CTNHConstructModifier.Ids.GLOBALTRAVELLER)
                .addInput(ItemsRegistry.STABLE_WARP_SCROLL)
                .exactLevel(1)
                .setSlots(SlotType.ABILITY, 1)
                .setTools(TinkerTags.Items.DURABILITY)
                .disallowCrystal()
                .save(consumer, location(folder + "global_traveller"));
    }
}
