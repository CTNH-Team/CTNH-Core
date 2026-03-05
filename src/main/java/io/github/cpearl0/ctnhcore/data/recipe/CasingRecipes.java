package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.BedrockMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHBlocks;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class CasingRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("osmiridium_casing"))
                .inputItems(TagPrefix.plate, GTMaterials.Osmiridium, 6)
                .inputItems(TagPrefix.frameGt, GTMaterials.Osmiridium)
                .outputItems(CTNHBlocks.CASING_OSMIRIDIUM.asStack(2))
                .EUt(GTValues.VA[GTValues.LV]).circuitMeta(6).duration(50).save(provider);
        VanillaRecipeHelper.addShapedRecipe(provider, true, "osmiridium_casing",
                CTNHBlocks.CASING_OSMIRIDIUM.asStack(2),
                "AhA", "ADA", "AwA",
                'A', new MaterialEntry(TagPrefix.plate, GTMaterials.Osmiridium),
                'D', new MaterialEntry(TagPrefix.frameGt, GTMaterials.Osmiridium));
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("reflect_light_casing"))
                .inputItems(TagPrefix.plate, GTMaterials.Silver, 6)
                .inputItems(TagPrefix.frameGt, GTMaterials.Silver)
                .outputItems(CTNHBlocks.CASING_REFLECT_LIGHT.asStack(2))
                .EUt(16).circuitMeta(6).duration(50).save(provider);
        VanillaRecipeHelper.addShapedRecipe(provider, true, "reflect_light_casing",
                CTNHBlocks.CASING_REFLECT_LIGHT.asStack(2),
                "AhA", "ADA", "AwA",
                'A', new MaterialEntry(TagPrefix.plate, GTMaterials.Silver),
                'D', new MaterialEntry(TagPrefix.frameGt, GTMaterials.Silver));
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("bio_reactor_casing"))
                .inputItems(TagPrefix.plate, CTNHMaterials.STABALLOY, 6)
                .inputItems(TagPrefix.frameGt, CTNHMaterials.STABALLOY)
                .outputItems(CTNHBlocks.BIO_REACTOR_CASING.asStack(2))
                .EUt(16).circuitMeta(6).duration(50).save(provider);
        VanillaRecipeHelper.addShapedRecipe(provider, true, "bio_reactor_casing",
                CTNHBlocks.BIO_REACTOR_CASING.asStack(2),
                "AhA", "ADA", "AwA",
                'A', new MaterialEntry(TagPrefix.plate, CTNHMaterials.STABALLOY),
                'D', new MaterialEntry(TagPrefix.frameGt, CTNHMaterials.STABALLOY));
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("sturdy_machine_casing"))
                .inputItems(TagPrefix.plate, GTMaterials.HSSE, 6)
                .inputItems(TagPrefix.frameGt, GTMaterials.TungstenCarbide)
                .outputItems(GTBlocks.CASING_HSSE_STURDY.asStack(2))
                .EUt(16).circuitMeta(6).duration(50).save(provider);
        VanillaRecipeHelper.addShapedRecipe(provider, true, "sturdy_machine_casing",
                GTBlocks.CASING_HSSE_STURDY.asStack(2),
                "AhA", "ADA", "AwA",
                'A', new MaterialEntry(TagPrefix.plate, GTMaterials.HSSE),
                'D', new MaterialEntry(TagPrefix.frameGt, GTMaterials.TungstenCarbide));
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("naquadah_casing_block"))
                .inputItems(TagPrefix.plateDouble, GTMaterials.Naquadah, 4)
                .inputItems(TagPrefix.plateDouble, GTMaterials.Europium, 2)
                .inputItems(TagPrefix.frameGt, GTMaterials.Duranium)
                .outputItems(CTNHBlocks.CASING_NAQUADAH_BLOCK.asStack(2))
                .EUt(16).circuitMeta(6).duration(50).save(provider);
        VanillaRecipeHelper.addShapedRecipe(provider, true, "naquadah_casing_block",
                CTNHBlocks.CASING_NAQUADAH_BLOCK.asStack(2),
                "AhA", "EDE", "AwA",
                'A', new MaterialEntry(TagPrefix.plateDouble, GTMaterials.Naquadah),
                'D', new MaterialEntry(TagPrefix.frameGt, GTMaterials.Duranium),
                'E', new MaterialEntry(TagPrefix.plateDouble, GTMaterials.Europium));
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("naquadah_alloy_casing_block"))
                .inputItems(TagPrefix.plateDouble, GTMaterials.NaquadahAlloy, 4)
                .inputItems(TagPrefix.plateDouble, GTMaterials.Neutronium, 2)
                .inputItems(TagPrefix.frameGt, GTMaterials.Tritanium)
                .outputItems(CTNHBlocks.CASING_NAQUADAH_ALLOY_BLOCK.asStack(2))
                .EUt(16).circuitMeta(6).duration(50).save(provider);
        VanillaRecipeHelper.addShapedRecipe(provider, true, "naquadah_alloy_casing_block",
                CTNHBlocks.CASING_NAQUADAH_ALLOY_BLOCK.asStack(2),
                "AhA", "EDE", "AwA",
                'A', new MaterialEntry(TagPrefix.plateDouble, GTMaterials.NaquadahAlloy),
                'D', new MaterialEntry(TagPrefix.frameGt, GTMaterials.Tritanium),
                'E', new MaterialEntry(TagPrefix.plateDouble, GTMaterials.Neutronium));
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("tungstencu_diamond_plating_casing"))
                .inputItems(TagPrefix.plate, BedrockMaterials.TUNGSTENCU_DIAMOND_PLATING, 2)
                .inputItems(TagPrefix.plateDense, GTMaterials.NaquadahAlloy, 4)
                .inputItems(TagPrefix.frameGt, BedrockMaterials.TUNGSTENCU_DIAMOND_PLATING)
                .outputItems(CTNHBlocks.CASING_TUNGSTENCU_DIAMOND_PLATING.asStack(2))
                .EUt(16).circuitMeta(6).duration(50).save(provider);
        VanillaRecipeHelper.addShapedRecipe(provider, true, "tungstencu_diamond_plating_casing",
                CTNHBlocks.CASING_TUNGSTENCU_DIAMOND_PLATING.asStack(2),
                "AhA", "EDE", "AwA",
                'A', new MaterialEntry(TagPrefix.plateDense, GTMaterials.NaquadahAlloy),
                'D', new MaterialEntry(TagPrefix.frameGt, BedrockMaterials.TUNGSTENCU_DIAMOND_PLATING),
                'E', new MaterialEntry(TagPrefix.plate, BedrockMaterials.TUNGSTENCU_DIAMOND_PLATING));
    }
}
