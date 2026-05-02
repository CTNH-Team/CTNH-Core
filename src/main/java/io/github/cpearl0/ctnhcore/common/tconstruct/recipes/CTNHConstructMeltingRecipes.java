package io.github.cpearl0.ctnhcore.common.tconstruct.recipes;

import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;
import io.github.cpearl0.ctnhcore.utils.CTNHConstructRecipeProvider;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;

import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.recipe.melting.MeltingRecipeBuilder;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTMaterialItems.MATERIAL_ITEMS;

public final class CTNHConstructMeltingRecipes extends CTNHConstructRecipeProvider {

    public CTNHConstructMeltingRecipes(PackOutput generator) {
        super(generator);
    }

    @Override
    public String getType() {
        return "melting";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        this.clayRecipes(consumer);
        // this.rubberRecipes(consumer);
        this.glassRecipes(consumer);
        this.wroughtIronRecipes(consumer);
        this.preciousMetalRecipes(consumer);
    }

    private void clayRecipes(Consumer<FinishedRecipe> consumer) {
        MeltingRecipeBuilder.melting(
                Ingredient.of(MATERIAL_ITEMS.get(TagPrefix.dust, GTMaterials.Fireclay)),
                new FluidStack(TinkerFluids.moltenClay.get(), 125),
                1000, 200).save(consumer, location("clay"));
    }

    // private void rubberRecipes(Consumer<FinishedRecipe> consumer) {
    // // 输入: ctnhcore:rubber_powder, 输出: 144mB gtceu:rubber流体, 温度: 400, 时间: 90
    // MeltingRecipeBuilder.melting(
    // Ingredient.of(RUBBER_POWDER.get()),
    // new FluidStack(GTMaterials.Rubber.getFluid(), 144),
    // 400, 90
    // ).save(consumer, location("rubber"));
    // }

    private void glassRecipes(Consumer<FinishedRecipe> consumer) {
        MeltingRecipeBuilder.melting(
                Ingredient.of(MATERIAL_ITEMS.get(TagPrefix.dust, GTMaterials.Glass)),
                new FluidStack(GTMaterials.Glass.getFluid(), 288),
                800, 90).save(consumer, location("glass"));
    }

    private void wroughtIronRecipes(Consumer<FinishedRecipe> consumer) {
        MeltingRecipeBuilder.melting(
                Ingredient.of(Items.IRON_NUGGET),
                new FluidStack(GTMaterials.WroughtIron.getFluid(), 32),
                600, 10).save(consumer, location("wrought_iron_from_iron_nugget"));

        MeltingRecipeBuilder.melting(
                Ingredient.of(MATERIAL_ITEMS.get(TagPrefix.nugget, GTMaterials.WroughtIron)),
                new FluidStack(GTMaterials.WroughtIron.getFluid(), 16),
                600, 5).save(consumer, location("wrought_iron_from_nugget"));
    }

    private void preciousMetalRecipes(Consumer<FinishedRecipe> consumer) {
        MeltingRecipeBuilder.melting(
                Ingredient.of(MATERIAL_ITEMS.get(TagPrefix.ingot, CTNHMaterials.PreciousAlloy)),
                new FluidStack(GTMaterials.Gold.getFluid(), 64),
                800, 40).save(consumer, location("gold_from_precious_alloy"));
    }
}
