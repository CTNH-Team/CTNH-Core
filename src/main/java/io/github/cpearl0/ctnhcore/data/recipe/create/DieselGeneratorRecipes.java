package io.github.cpearl0.ctnhcore.data.recipe.create;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.BiodieselFertileSoilMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import com.jesz.createdieselgenerators.CDGBlocks;
import com.mo_guang.ctpp.data.recipe.builder.create.CrushingRecipeBuilder;
import com.soytutta.mynethersdelight.common.registry.MNDItems;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Consumer;

public class DieselGeneratorRecipes {

    private static final ItemStack TALLOW = CTNHItems.TALLOW.asStack();

    public static void init(Consumer<FinishedRecipe> provider) {
        shapedRecipes(provider);
        mechanicalCraftingRecipes(provider);
        mixingRecipes(provider);
        compactingRecipes(provider);
        crushingRecipes(provider);
    }

    private static void shapedRecipes(Consumer<FinishedRecipe> provider) {
        // basin_lid
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/basin_lid"),
                CDGBlocks.BASIN_LID.asStack(),
                "   ", "ABA", "CDC",
                'A', new ItemStack(Items.REDSTONE),
                'B', ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.Steel),
                'C', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel),
                'D', new ItemStack(Items.CLOCK));
    }

    private static void mechanicalCraftingRecipes(Consumer<FinishedRecipe> provider) {
        // CTNH-specific pumpjack crank ingredients.
        // 原版配方：andesite_alloy_ingot → andesite_alloy_plate、iron_plate → steel_plate、zinc_ingot → zinc_plate
    }

    private static void mixingRecipes(Consumer<FinishedRecipe> provider) {
        // emulsified_bitumen_slurry: bitumen + simple_emulgator -> emulsified_bitumen_slurry
        com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder.builder("emulsified_bitumen_slurry")
                .inputFluid(BiodieselFertileSoilMaterials.BITUMEN.getFluid(800))
                .inputFluid(BiodieselFertileSoilMaterials.SIMPLE_EMULGATOR.getFluid(200))
                .resultFluid(BiodieselFertileSoilMaterials.EMULSIFIED_BITUMEN_SLURRY.getFluid(1000))
                .save(provider);

        // pure_bitumen: emulsified_bitumen + simple_demulsifier -> pure_bitumen
        com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder.builder("pure_bitumen")
                .inputFluid(BiodieselFertileSoilMaterials.EMULSIFIED_BITUMEN.getFluid(800))
                .inputFluid(BiodieselFertileSoilMaterials.SIMPLE_DEMULSIFIER.getFluid(200))
                .resultFluid(BiodieselFertileSoilMaterials.PURE_BITUMEN.getFluid(1000))
                .save(provider);

        // simple_emulgator: soda_ash + tallow + water -> simple_emulgator
        com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder.builder("simple_emulgator")
                .input(new ItemStack(ChemicalHelper.get(TagPrefix.dust, GTMaterials.SodaAsh).getItem(), 6))
                .input(TALLOW)
                .inputFluid(GTMaterials.Water.getFluid(1000))
                .resultFluid(BiodieselFertileSoilMaterials.SIMPLE_EMULGATOR.getFluid(1000))
                .save(provider);

        // simple_demulsifier: tallow + diluted_sulfuric_acid -> simple_demulsifier
        com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder.builder("simple_demulsifier")
                .input(TALLOW)
                .inputFluid(GTMaterials.DilutedSulfuricAcid.getFluid(1000))
                .resultFluid(BiodieselFertileSoilMaterials.SIMPLE_DEMULSIFIER.getFluid(1000))
                .save(provider);

        // asphalt_block: sand + gravel + pure_bitumen (heated)
        com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder.builder("asphalt_block")
                .input(new ItemStack(Items.SAND))
                .input(new ItemStack(Items.GRAVEL))
                .inputFluid(BiodieselFertileSoilMaterials.PURE_BITUMEN.getFluid(100))
                .result(CDGBlocks.ASPHALT_BLOCK.asStack(4))
                .heatRequirement("heated")
                .save(provider);

        // biodiesel: ethanol + seed_oil -> raw_bio_diesel
        com.mo_guang.ctpp.data.recipe.builder.create.MixingRecipeBuilder.builder("biodiesel")
                .inputFluid(GTMaterials.Ethanol.getFluid(100))
                .inputFluid(GTMaterials.SeedOil.getFluid(100))
                .resultFluid(BiodieselFertileSoilMaterials.RAW_BIO_DIESEL.getFluid(200))
                .save(provider);
    }

    private static void compactingRecipes(Consumer<FinishedRecipe> provider) {
        // petroleum_coke_gem: petroleum_coke fluid -> petroleum_coke_gem
        com.mo_guang.ctpp.data.recipe.builder.create.CompactingRecipeBuilder.builder("petroleum_coke_gem")
                .inputFluid(BiodieselFertileSoilMaterials.PETROLEUM_COKE.getFluid(144))
                .result(ChemicalHelper.get(TagPrefix.gem, BiodieselFertileSoilMaterials.PETROLEUM_COKE))
                .save(provider);
    }

    private static void crushingRecipes(Consumer<FinishedRecipe> provider) {
        // rich_soil -> rich_soil_dust x3
        com.mo_guang.ctpp.data.recipe.builder.create.CrushingRecipeBuilder.builder("crushing_rich_soil")
                .input(new ItemStack(ModItems.RICH_SOIL.get()))
                .result(new ItemStack(
                        ChemicalHelper.get(TagPrefix.dust, BiodieselFertileSoilMaterials.RICH_SOIL).getItem(), 3))
                .save(provider);

        // resurgent_soil -> rich_soul_soil_dust x3
        CrushingRecipeBuilder.builder("crushing_resurgent_soil")
                .input(new ItemStack(MNDItems.RESURGENT_SOIL.get()))
                .result(new ItemStack(
                        ChemicalHelper.get(TagPrefix.dust, BiodieselFertileSoilMaterials.RICH_SOUL_SOIL).getItem(), 3))
                .save(provider);
    }
}
