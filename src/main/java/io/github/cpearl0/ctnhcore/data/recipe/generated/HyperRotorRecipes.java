package io.github.cpearl0.ctnhcore.data.recipe.generated;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.simibubi.create.AllBlocks;
import net.minecraft.data.recipes.FinishedRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.FORMING_PRESS_RECIPES;
import static io.github.cpearl0.ctnhcore.registry.CTNHTagPrefixes.hyperRotor;

public class HyperRotorRecipes {
    public static void registerAll(@NotNull Consumer<FinishedRecipe> provider){
        for (Material material : GTCEuAPI.materialManager.getRegisteredMaterials()) {
            if(hyperRotor.generationCondition().test(material)){
                registerSingle(provider, material);
            }
        }
    }
    private static void registerSingle(@NotNull Consumer<FinishedRecipe> provider, Material material){
        FORMING_PRESS_RECIPES.recipeBuilder("press_" + material.getName() + "_hyper_rotor")
                .inputItems(TagPrefix.rotor,material,1)
                .inputItems(AllBlocks.SHAFT.get(), 1)
                .inputItems(TagPrefix.plateDense, material, 4*4)
                .inputItems(TagPrefix.screw, material, 9)
                .outputItems(hyperRotor,material, 1)
                .duration(200)
                .EUt(256)
                .save(provider);
    }
}
