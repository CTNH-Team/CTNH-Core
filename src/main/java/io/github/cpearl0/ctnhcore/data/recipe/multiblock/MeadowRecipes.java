package io.github.cpearl0.ctnhcore.data.recipe.multiblock;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.recipe.builder.CTNHRecipeBuilder;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;

import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class MeadowRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        CTNHRecipeBuilder.of(CTNHCore.id("pig"), CTNHRecipeTypes.MEADOW)
                .inputStress(256)
                .inputEntity(EntityType.PIG, 1, 0)
                .chancedInput(Items.POTATO.getDefaultInstance(), 100, 0)
                .outputItems(Items.PORKCHOP)
                .outputItems(CTNHItems.ANIMAL_EXCRETA)
                .duration(200)
                .save(provider);

        CTNHRecipeBuilder.of(CTNHCore.id("sheep"), CTNHRecipeTypes.MEADOW)
                .inputStress(256)
                .inputEntity(EntityType.SHEEP, 1, 0)
                .chancedInput(Items.WHEAT.getDefaultInstance(), 100, 0)
                .outputItems(Items.WHITE_WOOL)
                .outputItems(CTNHItems.ANIMAL_EXCRETA)
                .duration(200)
                .save(provider);

        CTNHRecipeBuilder.of(CTNHCore.id("cow"), CTNHRecipeTypes.MEADOW)
                .inputStress(256)
                .inputEntity(EntityType.COW, 1, 0)
                .chancedInput(Items.HAY_BLOCK.getDefaultInstance(), 50, 0)
                .outputItems(Items.LEATHER)
                .outputFluids(FluidIngredient.of(Tags.Fluids.MILK, 1000))
                .outputItems(CTNHItems.ANIMAL_EXCRETA)
                .duration(200)
                .save(provider);

        CTNHRecipeBuilder.of(CTNHCore.id("chicken"), CTNHRecipeTypes.MEADOW)
                .inputStress(256)
                .inputEntity(EntityType.CHICKEN, 1, 0)
                .chancedInput(Items.WHEAT_SEEDS.getDefaultInstance(), 100, 0)
                .outputItems(Items.EGG)
                .outputItems(CTNHItems.ANIMAL_EXCRETA)
                .duration(200)
                .save(provider);

        CTNHRecipeBuilder.of(CTNHCore.id("rabbit"), CTNHRecipeTypes.MEADOW)
                .inputStress(256)
                .inputEntity(EntityType.RABBIT, 1, 0)
                .chancedInput(Items.CARROT.getDefaultInstance(), 100, 0)
                .outputItems(Items.RABBIT)
                .outputItems(CTNHItems.ANIMAL_EXCRETA)
                .duration(200)
                .save(provider);
    }
}
