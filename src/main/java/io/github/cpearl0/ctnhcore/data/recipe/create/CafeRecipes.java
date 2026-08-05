package io.github.cpearl0.ctnhcore.data.recipe.create;

import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;

import com.Imphuls3.createcafe.core.registry.FluidRegistry;
import com.aetherteam.aether.item.AetherItems;
import com.hollingsworth.arsnouveau.setup.registry.BlockRegistry;
import com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder;
import samebutdifferent.ecologics.registry.ModItems;

import java.util.function.Consumer;

public class CafeRecipes {

    private static final ItemStack BLUE_BERRY = new ItemStack(AetherItems.BLUE_BERRY.get());
    private static final ItemStack SNOW_STEEL_INGOT = ChemicalHelper.get(TagPrefix.ingot, CTNHMaterials.SNOW_STEEL);

    public static void init(Consumer<FinishedRecipe> provider) {
        mixingRecipes(provider);
    }

    private static void mixingRecipes(Consumer<FinishedRecipe> provider) {
        // blueberry_tea: aether:blue_berry + milk 250 + melted_sugar 250 -> blueberry_tea 500 (heated)
        MixingRecipeBuilder.builder("cafe_blueberry_tea")
                .input(BLUE_BERRY)
                .inputFluid(GTMaterials.Milk.getFluid(250))
                .inputFluid(FluidRegistry.MELTED_SUGAR.get(), 250)
                .resultFluid(FluidRegistry.BLUEBERRY_TEA.get(), 500)
                .heatRequirement("heated")
                .save(provider);

        // coconut_tea: ecologics:coconut_slice + milk 250 + melted_sugar 250 -> coconut_tea 500 (heated)
        MixingRecipeBuilder.builder("cafe_coconut_tea")
                .input(new ItemStack(ModItems.COCONUT_SLICE.get()))
                .inputFluid(GTMaterials.Milk.getFluid(250))
                .inputFluid(FluidRegistry.MELTED_SUGAR.get(), 250)
                .resultFluid(FluidRegistry.COCONUT_TEA.get(), 500)
                .heatRequirement("heated")
                .save(provider);

        // coconut_syrup: ecologics:coconut_slice + milk 250 + melted_sugar 750 -> coconut_syrup 1000 (heated)
        MixingRecipeBuilder.builder("cafe_coconut_syrup")
                .input(new ItemStack(ModItems.COCONUT_SLICE.get()))
                .inputFluid(GTMaterials.Milk.getFluid(250))
                .inputFluid(FluidRegistry.MELTED_SUGAR.get(), 750)
                .resultFluid(FluidRegistry.COCONUT_SYRUP.get(), 1000)
                .heatRequirement("heated")
                .save(provider);

        // pomegranate_tea: ars_nouveau:bombegranate_pod + milk 250 + melted_sugar 250 -> pomegranate_tea 500 (heated)
        MixingRecipeBuilder.builder("cafe_pomegranate_tea")
                .input(new ItemStack(BlockRegistry.BOMBEGRANTE_POD.get().asItem()))
                .inputFluid(GTMaterials.Milk.getFluid(250))
                .inputFluid(FluidRegistry.MELTED_SUGAR.get(), 250)
                .resultFluid(FluidRegistry.POMEGRANATE_TEA.get(), 500)
                .heatRequirement("heated")
                .save(provider);

        // blood_tea: ctnhcore:snow_steel_ingot + milk 250 + melted_sugar 250 -> blood_tea 500 (heated)
        MixingRecipeBuilder.builder("cafe_blood_tea")
                .input(SNOW_STEEL_INGOT)
                .inputFluid(GTMaterials.Milk.getFluid(250))
                .inputFluid(FluidRegistry.MELTED_SUGAR.get(), 250)
                .resultFluid(FluidRegistry.BLOOD_TEA.get(), 500)
                .heatRequirement("heated")
                .save(provider);
    }
}
