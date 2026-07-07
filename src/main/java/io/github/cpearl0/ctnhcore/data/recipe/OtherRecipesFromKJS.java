package io.github.cpearl0.ctnhcore.data.recipe;

import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import io.github.cpearl0.ctnhcore.CTNHCore;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dustSmall;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class OtherRecipesFromKJS {
    public static void init(Consumer<FinishedRecipe> provider) {
        otherRecipesFromKJS(provider);
    }

    private static void otherRecipesFromKJS(Consumer<FinishedRecipe> provider) {

        /*
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("kjs/cement_bucket"), new ItemStack(Cement.getBucket()),
                "ABA", "CDC", " E ",
                'A', new MaterialEntry(TagPrefix.dustTiny, GTMaterials.Iron),
                'B', new ItemStack(Items.WATER_BUCKET),
                'C', new MaterialEntry(dustSmall, Calcite),
                'D', new ItemStack(Items.BUCKET),
                'E', new MaterialEntry(dust, Clay)
        );
        */
        
    }

}
