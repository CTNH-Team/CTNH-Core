package io.github.cpearl0.ctnhcore.common.tconstruct.recipes;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.jesz.createdieselgenerators.CDGItems;
import com.mo_guang.ctpp.registry.CTPPMaterials;
import fr.lucreeper74.createmetallurgy.registries.CMItems;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;
import io.github.cpearl0.ctnhcore.utils.CTNHConstructRecipeProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

import static com.gregtechceu.gtceu.common.data.GTMaterialBlocks.MATERIAL_BLOCKS;
import static com.gregtechceu.gtceu.common.data.GTMaterialItems.MATERIAL_ITEMS;

import java.util.Objects;
import java.util.function.Consumer;

public final class CTNHConstructCastingRecipes extends CTNHConstructRecipeProvider {

    public CTNHConstructCastingRecipes(PackOutput generator) {
        super(generator);
    }

    @Override
    public String getType() {
        return "casting";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        this.andesiteAlloyCasting(consumer);
        this.foundryUnitCasting(consumer);
        this.rubberCasting(consumer);
        this.snowSteelCasting(consumer);
        this.wroughtIronCasting(consumer);
    }

    private void andesiteAlloyCasting(Consumer<FinishedRecipe> consumer) {
        ItemCastingRecipeBuilder.tableRecipe(Objects.requireNonNull(MATERIAL_ITEMS.get(TagPrefix.ingot, CTPPMaterials.AndesiteAlloy)))
                .setCast(TinkerSmeltery.ingotCast.get(), false)
                .setFluidAndTime(new FluidStack(CTPPMaterials.AndesiteAlloy.getFluid(), 144))
                .save(consumer, location("andesite_alloy_ingot"));
    }

    private void foundryUnitCasting(Consumer<FinishedRecipe> consumer) {
        ItemCastingRecipeBuilder.tableRecipe(CMItems.FOUNDRY_UNIT)
                .setCast(CDGItems.DISTILLATION_CONTROLLER.get(), false)
                .setFluidAndTime(new FluidStack(GTMaterials.WroughtIron.getFluid(), 144))
                .save(consumer, location("foundry_unit"));
    }

    private void rubberCasting(Consumer<FinishedRecipe> consumer) {
        ItemCastingRecipeBuilder.tableRecipe(Objects.requireNonNull(MATERIAL_ITEMS.get(TagPrefix.ingot, GTMaterials.Rubber)))
                .setCast(TinkerSmeltery.ingotCast.get(), false)
                .setFluidAndTime(new FluidStack(GTMaterials.Rubber.getFluid(), 144))
                .save(consumer, location("rubber_ingot"));
    }
    private void snowSteelCasting(Consumer<FinishedRecipe> consumer) {
        ItemCastingRecipeBuilder.tableRecipe(Objects.requireNonNull(MATERIAL_ITEMS.get(TagPrefix.ingot, CTNHMaterials.SNOW_STEEL)))
                .setCast(TinkerSmeltery.ingotCast.get(), false)
                .setFluidAndTime(new FluidStack(CTNHMaterials.SNOW_STEEL.getFluid(), 144))
                .save(consumer, location("snow_steel_ingot"));
        ItemCastingRecipeBuilder.basinRecipe(Objects.requireNonNull(MATERIAL_BLOCKS.get(TagPrefix.block, CTNHMaterials.SNOW_STEEL)))
                .setFluidAndTime(new FluidStack(CTNHMaterials.SNOW_STEEL.getFluid(), 1296))
                .save(consumer, location("snow_steel_block"));
    }

    private void wroughtIronCasting(Consumer<FinishedRecipe> consumer) {
        ItemCastingRecipeBuilder.tableRecipe(Objects.requireNonNull(MATERIAL_ITEMS.get(TagPrefix.ingot, GTMaterials.WroughtIron)))
                .setCast(TinkerSmeltery.ingotCast.get(), false)
                .setFluidAndTime(new FluidStack(GTMaterials.WroughtIron.getFluid(), 144))
                .save(consumer, location("wrought_iron_ingot"));
        ItemCastingRecipeBuilder.basinRecipe(Objects.requireNonNull(MATERIAL_BLOCKS.get(TagPrefix.block, GTMaterials.WroughtIron)))
                .setFluidAndTime(new FluidStack(GTMaterials.WroughtIron.getFluid(), 144 * 9))
                .save(consumer, location("wrought_iron_block"));
    }
}
