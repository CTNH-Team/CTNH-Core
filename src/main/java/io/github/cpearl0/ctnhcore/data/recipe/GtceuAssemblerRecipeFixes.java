package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.rodLong;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.toolHeadBuzzSaw;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Iron;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Wood;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLER_RECIPES;

public final class GtceuAssemblerRecipeFixes {

    private GtceuAssemblerRecipeFixes() {}

    public static void init(Consumer<FinishedRecipe> provider) {
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("stonecutter"))
                .inputItems(toolHeadBuzzSaw, Iron)
                .inputItems(new ItemStack(Blocks.STONE_SLAB))
                .circuitMeta(6)
                .outputItems(new ItemStack(Blocks.STONECUTTER))
                .duration(80).EUt(6).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("grindstone"))
                .inputItems(new ItemStack(Blocks.STONE_SLAB))
                .inputItems(toolHeadBuzzSaw, Iron)
                .inputItems(rodLong, Wood, 2)
                .circuitMeta(7)
                .outputItems(new ItemStack(Blocks.GRINDSTONE))
                .duration(80).EUt(6).save(provider);
    }
}
