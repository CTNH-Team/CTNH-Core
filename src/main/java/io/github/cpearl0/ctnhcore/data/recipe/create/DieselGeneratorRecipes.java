package io.github.cpearl0.ctnhcore.data.recipe.create;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.BiodieselFertileSoilMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import com.jesz.createdieselgenerators.CDGBlocks;
import com.jesz.createdieselgenerators.CDGItems;
import com.mo_guang.ctpp.common.recipe.builder.create.*;
import com.mo_guang.ctpp.registry.CTPPBlocks;
import com.mo_guang.ctpp.registry.CTPPItems;
import com.simibubi.create.AllBlocks;
import com.soytutta.mynethersdelight.common.registry.MNDItems;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Consumer;

public class DieselGeneratorRecipes {

    private static final ItemStack HOSE_PULLEY = new ItemStack(AllBlocks.HOSE_PULLEY.asItem());
    private static final ItemStack COPPER_CASING = AllBlocks.COPPER_CASING.asStack();
    private static final ItemStack FLUID_PIPE = AllBlocks.FLUID_PIPE.asStack();
    private static final ItemStack MECHANICAL_BEARING = AllBlocks.MECHANICAL_BEARING.asStack();
    private static final ItemStack STEEL_CASING = CTPPBlocks.STEEL_CASING.asStack();
    private static final ItemStack HEAVY_MACHINERY_CASING = CTPPBlocks.HEAVY_MACHINERY_CASING.asStack();
    private static final ItemStack TREATED_WOOD_PLANKS = GTBlocks.TREATED_WOOD_PLANK.asStack();
    private static final ItemStack TALLOW = CTNHItems.TALLOW.asStack();

    public static void init(Consumer<FinishedRecipe> provider) {
        shapedRecipes(provider);
        itemApplicationRecipes(provider);
        mixingRecipes(provider);
        compactingRecipes(provider);
        crushingRecipes(provider);
    }

    private static void shapedRecipes(Consumer<FinishedRecipe> provider) {
        // engine_piston
        VanillaRecipeHelper.addShapedRecipe(provider, "crafttable/diesel_engine_piston",
                CDGItems.ENGINE_PISTON.asStack(2),
                "AB ", "BC ", "  D",
                'A', CTPPItems.STEEL_MECHANISM.asStack(),
                'B', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Iron),
                'C', new ItemStack(Items.PISTON),
                'D', ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Zinc));

        // pumpjack_hole
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/pumpjack_hole"),
                CDGBlocks.PUMPJACK_HOLE.asStack(),
                "ABA", "CDC", "EFE",
                'A', CTPPItems.STEEL_MECHANISM.asStack(),
                'B', HOSE_PULLEY,
                'C', COPPER_CASING,
                'D', STEEL_CASING,
                'E', new ItemStack(Items.CHAIN),
                'F', FLUID_PIPE);

        // pumpjack_bearing
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/pumpjack_bearing"),
                CDGBlocks.PUMPJACK_BEARING.asStack(),
                "   ", "ABA", "ACA",
                'A', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel),
                'B', MECHANICAL_BEARING,
                'C', HEAVY_MACHINERY_CASING);

        // basin_lid
        VanillaRecipeHelper.addShapedRecipe(provider, CTNHCore.id("crafttable/basin_lid"),
                CDGBlocks.BASIN_LID.asStack(),
                "   ", "ABA", "CDC",
                'A', new ItemStack(Items.REDSTONE),
                'B', ChemicalHelper.get(TagPrefix.plateDouble, GTMaterials.Steel),
                'C', ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel),
                'D', new ItemStack(Items.CLOCK));
    }

    private static void itemApplicationRecipes(Consumer<FinishedRecipe> provider) {
        // steel_casing: treated_wood_planks + wrought_iron_plate -> steel_casing
        ItemApplicationRecipeBuilder.builder("steel_casing")
                .input(TREATED_WOOD_PLANKS)
                .input(ChemicalHelper.get(TagPrefix.plate, GTMaterials.WroughtIron))
                .result(STEEL_CASING)
                .save(provider);

        // heavy_machinery_casing: steel_casing + steel_plate -> heavy_machinery_casing
        ItemApplicationRecipeBuilder.builder("heavy_machinery_casing")
                .input(STEEL_CASING)
                .input(ChemicalHelper.get(TagPrefix.plate, GTMaterials.Steel))
                .result(HEAVY_MACHINERY_CASING)
                .save(provider);
    }

    private static void mixingRecipes(Consumer<FinishedRecipe> provider) {
        // emulsified_bitumen_slurry: bitumen + simple_emulgator -> emulsified_bitumen_slurry
        MixingRecipeBuilder.builder("emulsified_bitumen_slurry")
                .inputFluid("gtceu:bitumen", 800)
                .inputFluid("gtceu:simple_emulgator", 200)
                .resultFluid("gtceu:emulsified_bitumen_slurry", 1000)
                .save(provider);

        // pure_bitumen: emulsified_bitumen + simple_demulsifier -> pure_bitumen
        MixingRecipeBuilder.builder("pure_bitumen")
                .inputFluid("gtceu:emulsified_bitumen", 800)
                .inputFluid("gtceu:simple_demulsifier", 200)
                .resultFluid("gtceu:pure_bitumen", 1000)
                .save(provider);

        // simple_emulgator: soda_ash + tallow + water -> simple_emulgator
        MixingRecipeBuilder.builder("simple_emulgator")
                .input(new ItemStack(ChemicalHelper.get(TagPrefix.dust, GTMaterials.SodaAsh).getItem(), 6))
                .input(TALLOW)
                .inputFluid("minecraft:water", 1000)
                .resultFluid("gtceu:simple_emulgator", 1000)
                .save(provider);

        // simple_demulsifier: tallow + diluted_sulfuric_acid -> simple_demulsifier
        MixingRecipeBuilder.builder("simple_demulsifier")
                .input(TALLOW)
                .inputFluid("gtceu:diluted_sulfuric_acid", 1000)
                .resultFluid("gtceu:simple_demulsifier", 1000)
                .save(provider);

        // asphalt_block: sand + gravel + pure_bitumen (heated)
        MixingRecipeBuilder.builder("asphalt_block")
                .input(new ItemStack(Items.SAND))
                .input(new ItemStack(Items.GRAVEL))
                .inputFluid("gtceu:pure_bitumen", 100)
                .result(CDGBlocks.ASPHALT_BLOCK.asStack(4))
                .heatRequirement("heated")
                .save(provider);

        // biodiesel: ethanol + seed_oil -> raw_bio_diesel
        MixingRecipeBuilder.builder("biodiesel")
                .inputFluid("gtceu:ethanol", 100)
                .inputFluid("gtceu:seed_oil", 100)
                .resultFluid("gtceu:raw_bio_diesel", 200)
                .save(provider);
    }

    private static void compactingRecipes(Consumer<FinishedRecipe> provider) {
        // petroleum_coke_gem: petroleum_coke fluid -> petroleum_coke_gem
        CompactingRecipeBuilder.builder("petroleum_coke_gem")
                .inputFluid(BiodieselFertileSoilMaterials.PETROLEUM_COKE.getFluid(144))
                .result(ChemicalHelper.get(TagPrefix.gem, BiodieselFertileSoilMaterials.PETROLEUM_COKE))
                .save(provider);
    }

    private static void crushingRecipes(Consumer<FinishedRecipe> provider) {
        // rich_soil -> rich_soil_dust x3
        CrushingRecipeBuilder.builder("crushing_rich_soil")
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
