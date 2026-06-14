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
        // 1. Graphite gas: 2x graphite_dust -> graphite_steam 4000
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("graphite_gas"))
                .inputItems(dust, Graphite, 2)
                .outputFluids(GrapheneProductionLineMaterials.GRAPHITE_STEAM.getFluid(4000))
                .EUt(6144).duration(1000)
                .blastFurnaceTemp(4500)
                .save(provider);

        // 2. Graphene plate production method 1: graphite_steam 4000 + iridium_plate + nitrogen 1000 ->
        // graphite_ir_plate_plate
        CTNHRecipeTypes.CHEMICAL_VAPOR_DEPOSITION.recipeBuilder(CTNHCore.id("graphene_plate_production_method_1"))
                .inputFluids(GrapheneProductionLineMaterials.GRAPHITE_STEAM.getFluid(4000))
                .inputItems(plate, Iridium)
                .inputFluids(Nitrogen.getFluid(1000))
                .outputItems(plate, GrapheneProductionLineMaterials.GRAPHITE_IR_PLATE)
                .EUt(1920).duration(100)
                .save(provider);

        // 3. Graphene plate production method 2: graphite_steam 8000 + double_iridium_plate + nitrogen 2000 ->
        // double_graphite_ir_plate_plate
        CTNHRecipeTypes.CHEMICAL_VAPOR_DEPOSITION.recipeBuilder(CTNHCore.id("graphene_plate_production_method_2"))
                .inputFluids(GrapheneProductionLineMaterials.GRAPHITE_STEAM.getFluid(8000))
                .inputItems(plateDouble, Iridium)
                .inputFluids(Nitrogen.getFluid(2000))
                .outputItems(plateDouble, GrapheneProductionLineMaterials.GRAPHITE_IR_PLATE)
                .EUt(6144).duration(100)
                .save(provider);

        // 4. Iridium plate graphene separation 1: 2x graphite_ir_plate_plate + hydrochloric_acid 6000 -> hydrogen 6000
        // + 2x iridium_chloride_dust + 2x graphene_dust
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("iridium_plate_graphene_separation_1"))
                .inputItems(plate, GrapheneProductionLineMaterials.GRAPHITE_IR_PLATE, 2)
                .inputFluids(HydrochloricAcid.getFluid(6000))
                .outputFluids(Hydrogen.getFluid(6000))
                .outputItems(dust, IridiumChloride, 2)
                .outputItems(dust, Graphene, 2)
                .EUt(1920).duration(200)
                .save(provider);

        // 5. Iridium plate graphene separation 2: 2x double_graphite_ir_plate_plate + hydrochloric_acid 12000 ->
        // hydrogen 12000 + 4x iridium_chloride_dust + 4x graphene_dust
        CHEMICAL_RECIPES.recipeBuilder(CTNHCore.id("iridium_plate_graphene_separation_2"))
                .inputItems(plateDouble, GrapheneProductionLineMaterials.GRAPHITE_IR_PLATE, 2)
                .inputFluids(HydrochloricAcid.getFluid(12000))
                .outputFluids(Hydrogen.getFluid(12000))
                .outputItems(dust, IridiumChloride, 4)
                .outputItems(dust, Graphene, 4)
                .EUt(1920).duration(100)
                .save(provider);

        // 6. Graphene powder: 10x graphite_dust + 10x duct_tape -> chancedOutput(small_graphene_dust, 4500, 0)
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("graphene_powder"))
                .inputItems(dust, Graphite, 10)
                .inputItems(GTItems.DUCT_TAPE.asStack(10))
                .chancedOutput(dustSmall, Graphene, 4500, 0)
                .EUt(480).duration(180)
                .save(provider);

        // 7. Graphite gas production method 1: 24x fluid_cell + methane 6000 -> graphite_steam 3000 + 24x
        // fluid_cell(hydrogen) + hydrogen 24000
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("graphite_gas_production_method_1"))
                .inputItems(GTItems.FLUID_CELL.asStack(24))
                .inputFluids(Methane.getFluid(6000))
                .outputFluids(GrapheneProductionLineMaterials.GRAPHITE_STEAM.getFluid(3000), Hydrogen.getFluid(24000))
                .outputItems(GTItems.FLUID_CELL.asStack(24))
                .EUt(480).duration(200)
                .circuitMeta(1)
                .blastFurnaceTemp(2200)
                .save(provider);

        // 8. Graphite gas production method 1 no hydrogen: methane 6000 -> graphite_steam 3000
        BLAST_RECIPES.recipeBuilder(CTNHCore.id("graphite_gas_production_method_1_no_hydrogen"))
                .inputFluids(Methane.getFluid(6000))
                .outputFluids(GrapheneProductionLineMaterials.GRAPHITE_STEAM.getFluid(3000))
                .EUt(480).duration(200)
                .circuitMeta(2)
                .blastFurnaceTemp(2200)
                .save(provider);
    }
}
