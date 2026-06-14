package io.github.cpearl0.ctnhcore.data.recipe;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksB;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;

import com.mo_guang.ctpp.registry.CTPPBlocks;
import earth.terrarium.adastra.common.registry.ModBlocks;

import java.util.function.Consumer;

import static com.ctnh.ctnhastral.data.CAMaterials.*;

public class NanoRecipes {

    // ad_astra planet stone blocks: ModBlocks (RegistryEntry<Block>, use .get().asItem())
    // gtceu stone dust materials: CAMaterials (CTNH-Astral module)

    public static void init(Consumer<FinishedRecipe> provider) {
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/nanogenetor"),
                MultiblocksB.NANOGENERATOR.asStack(),
                "BAB", "ACA", "BAB",
                'A', CustomTags.MV_CIRCUITS,
                'B', CTPPBlocks.STEEL_CASING.asStack(),
                'C', GTMachines.HULL[GTValues.MV]);

        // Nano generator: stone -> stone_dust
        CTNHRecipeTypes.NANO_GENERATOR.recipeBuilder(CTNHCore.id("stone2"))
                .inputItems(Items.STONE)
                .outputItems(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Stone), 2)
                .EUt(-2)
                .duration(5)
                .save(provider);

        // Nano generator: netherrack -> netherrack_dust
        CTNHRecipeTypes.NANO_GENERATOR.recipeBuilder(CTNHCore.id("netherrack"))
                .inputItems(Items.NETHERRACK)
                .outputItems(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Netherrack), 2)
                .EUt(-3)
                .duration(10)
                .save(provider);

        // Nano generator: moon_cobblestone -> moon_stone_dust
        CTNHRecipeTypes.NANO_GENERATOR.recipeBuilder(CTNHCore.id("moon_cobblestone"))
                .inputItems(ModBlocks.MOON_COBBLESTONE.get().asItem())
                .outputItems(ChemicalHelper.get(TagPrefix.dust, Moonstone), 3)
                .EUt(-6)
                .duration(10)
                .save(provider);

        // Nano generator: venus_stone -> venus_stone_dust
        CTNHRecipeTypes.NANO_GENERATOR.recipeBuilder(CTNHCore.id("venus_stone"))
                .inputItems(ModBlocks.VENUS_STONE.get().asItem())
                .outputItems(ChemicalHelper.get(TagPrefix.dust, Venusstone), 3)
                .EUt(-16)
                .duration(20)
                .save(provider);

        // Nano generator: mars_stone -> mars_stone_dust
        CTNHRecipeTypes.NANO_GENERATOR.recipeBuilder(CTNHCore.id("mars_stone"))
                .inputItems(ModBlocks.MARS_STONE.get().asItem())
                .outputItems(ChemicalHelper.get(TagPrefix.dust, Marsstone), 2)
                .EUt(-8)
                .duration(15)
                .save(provider);

        // Nano generator: mercury_stone -> mercury_stone_dust
        CTNHRecipeTypes.NANO_GENERATOR.recipeBuilder(CTNHCore.id("mercury_stone"))
                .inputItems(ModBlocks.MERCURY_STONE.get().asItem())
                .outputItems(ChemicalHelper.get(TagPrefix.dust, Mercurystone), 2)
                .EUt(-16)
                .duration(20)
                .save(provider);

        // Nano generator: glacio_stone -> glacio_stone_dust
        CTNHRecipeTypes.NANO_GENERATOR.recipeBuilder(CTNHCore.id("glacio_stone"))
                .inputItems(ModBlocks.GLACIO_STONE.get().asItem())
                .outputItems(ChemicalHelper.get(TagPrefix.dust, Glaciostone), 2)
                .EUt(-30)
                .duration(20)
                .save(provider);
    }
}
