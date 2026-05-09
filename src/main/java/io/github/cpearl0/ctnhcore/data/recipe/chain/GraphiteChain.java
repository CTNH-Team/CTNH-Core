package io.github.cpearl0.ctnhcore.data.recipe.chain;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.data.materials.GrapheneProductionLineMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;

import com.gregtechceu.gtceu.common.data.GTItems;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;

public class GraphiteChain {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 1. Graphite gas: graphite_dust -> graphite_steam
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("graphite_gas"))
                .inputItems(dust, Graphite)
                .outputFluids(GrapheneProductionLineMaterials.GRAPHITE_STEAM.getFluid(1000))
                .EUt(480).duration(200)
                .blastFurnaceTemp(1700)
                .save(provider);

        // 2. Graphene plate production method 1: graphite_steam + iridium_plate + nitrogen -> graphite_ir_plate_plate
        CTNHRecipeTypes.CHEMICAL_VAPOR_DEPOSITION.recipeBuilder(CTNHCore.id("graphene_plate_production_method_1"))
                .inputFluids(GrapheneProductionLineMaterials.GRAPHITE_STEAM.getFluid(1000))
                .inputItems(plate, Iridium)
                .inputFluids(Nitrogen.getFluid(1000))
                .outputItems(plate, GrapheneProductionLineMaterials.GRAPHITE_IR_PLATE)
                .EUt(480).duration(200)
                .save(provider);

        // 3. Graphene plate production method 2: graphite_steam + double_iridium_plate + nitrogen ->
        // double_graphite_ir_plate_plate
        CTNHRecipeTypes.CHEMICAL_VAPOR_DEPOSITION.recipeBuilder(CTNHCore.id("graphene_plate_production_method_2"))
                .inputFluids(GrapheneProductionLineMaterials.GRAPHITE_STEAM.getFluid(1000))
                .inputItems(plateDouble, Iridium)
                .inputFluids(Nitrogen.getFluid(1000))
                .outputItems(plateDouble, GrapheneProductionLineMaterials.GRAPHITE_IR_PLATE)
                .EUt(480).duration(200)
                .save(provider);

        // 4. Iridium plate graphene separation 1: graphite_ir_plate_plate + hydrochloric_acid -> hydrogen +
        // iridium_chloride_dust + graphene_dust
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("iridium_plate_graphene_separation_1"))
                .inputItems(plate, GrapheneProductionLineMaterials.GRAPHITE_IR_PLATE)
                .inputFluids(HydrochloricAcid.getFluid(1000))
                .outputFluids(Hydrogen.getFluid(1000))
                .outputItems(dust, IridiumChloride)
                .outputItems(dust, Graphene)
                .EUt(480).duration(200)
                .save(provider);

        // 5. Iridium plate graphene separation 2: double_graphite_ir_plate_plate + hydrochloric_acid -> hydrogen +
        // iridium_chloride_dust + graphene_dust
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("iridium_plate_graphene_separation_2"))
                .inputItems(plateDouble, GrapheneProductionLineMaterials.GRAPHITE_IR_PLATE)
                .inputFluids(HydrochloricAcid.getFluid(1000))
                .outputFluids(Hydrogen.getFluid(2000))
                .outputItems(dust, IridiumChloride, 2)
                .outputItems(dust, Graphene, 2)
                .EUt(480).duration(200)
                .save(provider);

        // 6. Graphene powder: graphite_dust + duct_tape -> chancedOutput(small_graphene_dust)
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("graphene_powder"))
                .inputItems(dust, Graphite)
                .inputItems(GTItems.DUCT_TAPE.asStack())
                .chancedOutput(dustSmall, Graphene, 5000, 500)
                .EUt(120).duration(200)
                .save(provider);

        // 7. Graphite gas production method 1: fluid_cell + methane -> graphite_steam + fluid_cell(hydrogen)
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("graphite_gas_production_method_1"))
                .inputItems(GTItems.FLUID_CELL.asStack())
                .inputFluids(Methane.getFluid(1000))
                .outputFluids(GrapheneProductionLineMaterials.GRAPHITE_STEAM.getFluid(1000))
                .outputItems(GTItems.FLUID_CELL.asStack())
                .EUt(480).duration(200)
                .blastFurnaceTemp(1700)
                .save(provider);

        // 8. Graphite gas production method 1 no hydrogen: methane -> graphite_steam
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("graphite_gas_production_method_1_no_hydrogen"))
                .inputFluids(Methane.getFluid(1000))
                .outputFluids(GrapheneProductionLineMaterials.GRAPHITE_STEAM.getFluid(1000))
                .EUt(480).duration(200)
                .blastFurnaceTemp(1700)
                .save(provider);
    }
}
