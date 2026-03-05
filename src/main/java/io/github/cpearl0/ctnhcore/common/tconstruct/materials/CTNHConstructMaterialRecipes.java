package io.github.cpearl0.ctnhcore.common.tconstruct.materials;

import io.github.cpearl0.ctnhcore.common.tconstruct.material.CTNHConstructMaterials;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;
import io.github.cpearl0.ctnhcore.utils.CTNHConstructRecipeProvider;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.crafting.Ingredient;

import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTMaterialBlocks.MATERIAL_BLOCKS;
import static com.gregtechceu.gtceu.common.data.GTMaterialItems.MATERIAL_ITEMS;

public final class CTNHConstructMaterialRecipes extends CTNHConstructRecipeProvider implements IMaterialRecipeHelper {

    public CTNHConstructMaterialRecipes(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    public String getType() {
        return "tinker/materials/";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        this.addMaterials(consumer);
    }

    private void addMaterials(Consumer<FinishedRecipe> consumer) {
        materialRecipe(consumer, CTNHConstructMaterials.Ids.SNOW_STEEL,
                Ingredient.of(MATERIAL_ITEMS.get(TagPrefix.ingot, CTNHMaterials.SNOW_STEEL)), 1, 1, "snow_steel/ingot");
        materialRecipe(consumer, CTNHConstructMaterials.Ids.SNOW_STEEL,
                Ingredient.of(MATERIAL_BLOCKS.get(TagPrefix.block, CTNHMaterials.SNOW_STEEL)), 9, 1,
                "snow_steel/block");
        // materialMeltingCasting(consumer, CTNHConstructMaterials.Ids.SNOW_STEEL, CTNHMaterials.SNOW_STEEL.getFluid(),
        // "snow_steel/fluid");
    }
}
