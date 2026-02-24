package io.github.cpearl0.ctnhcore.registry;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;
import io.github.cpearl0.ctnhcore.utils.CTNHConstructRecipeProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.mantle.recipe.ingredient.FluidIngredient;
import slimeknights.tconstruct.library.recipe.fuel.MeltingFuelBuilder;

import java.util.function.Consumer;

public class CTNHConstructFuel extends CTNHConstructRecipeProvider {

    public CTNHConstructFuel(PackOutput generator) {
        super(generator);
    }

    @Override
    public String getType() {
        return "melting_fuel";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        // 生物柴油: duration=100, rate=25, temperature=2300, amount=25
        MeltingFuelBuilder.fuel(FluidIngredient.of(new FluidStack(GTMaterials.BioDiesel.getFluid(), 25)), 100,2300)
                .rate(25)
                .save(consumer, location("bio_diesel"));

        // 柴油: duration=100, rate=50, temperature=3500, amount=5
        MeltingFuelBuilder.fuel(FluidIngredient.of(new FluidStack(GTMaterials.Diesel.getFluid(), 5)), 100,3500)
                .rate(50)
                .save(consumer, location("diesel"));

        // 高十六烷值柴油: duration=200, rate=75, temperature=5000, amount=1
        MeltingFuelBuilder.fuel(FluidIngredient.of(new FluidStack(GTMaterials.CetaneBoostedDiesel.getFluid(), 1)), 200,5000)
                .rate(75)
                .save(consumer, location("cetane_boosted_diesel"));

        // 汽油: duration=100, rate=125, temperature=3800, amount=25
        MeltingFuelBuilder.fuel(FluidIngredient.of(new FluidStack(GTMaterials.Gasoline.getFluid(), 25)), 100,3800)
                .rate(125)
                .save(consumer, location("gasoline"));

        // 高辛烷值汽油: duration=200, rate=200, temperature=4700, amount=1
        MeltingFuelBuilder.fuel(FluidIngredient.of(new FluidStack(GTMaterials.HighOctaneGasoline.getFluid(), 1)), 200,4700)
                .rate(200)
                .save(consumer, location("high_octane_gasoline"));

        // 烈焰流体: duration=200, rate=30, temperature=4000, amount=100
        MeltingFuelBuilder.fuel(FluidIngredient.of(new FluidStack(GTMaterials.Blaze.getFluid(), 100)), 200,4000)
                .rate(30)
                .save(consumer, location("blaze"));

        // 烈焰之炽焱: duration=200, rate=500, temperature=5700, amount=1
        MeltingFuelBuilder.fuel(FluidIngredient.of(new FluidStack(CTNHMaterials.Pyrotheum.getFluid(), 1)), 200,5700)
                .rate(500)
                .save(consumer, location("pyrotheum"));

        MeltingFuelBuilder.fuel(FluidIngredient.of(new FluidStack(GTMaterials.Creosote.getFluid(), 50)), 200,800)
                .rate(500)
                .save(consumer, location("creosote"));

        //雪城钢: duration=114514, rate=1919, temperature=8100, amount=999
        MeltingFuelBuilder.fuel(new FluidStack(CTNHMaterials.SNOW_STEEL.getFluid(), 999), 114514)
                .rate(1919)
                .save(consumer, location("snow_steel"));
    }
}