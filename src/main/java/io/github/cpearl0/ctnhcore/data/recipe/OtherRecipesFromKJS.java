package io.github.cpearl0.ctnhcore.data.recipe;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class OtherRecipesFromKJS {

    public static void init(Consumer<FinishedRecipe> provider) {
        otherRecipesFromKJS(provider);
    }

    private static void otherRecipesFromKJS(Consumer<FinishedRecipe> provider) {
        /*
         * VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("kjs/cement_bucket"), new
         * ItemStack(Cement.getBucket()),
         * "ABA", "CDC", " E ",
         * 'A', new MaterialEntry(TagPrefix.dustTiny, GTMaterials.Iron),
         * 'B', new ItemStack(Items.WATER_BUCKET),
         * 'C', new MaterialEntry(dustSmall, Calcite),
         * 'D', new ItemStack(Items.BUCKET),
         * 'E', new MaterialEntry(dust, Clay)
         * );
         */
    }
}
