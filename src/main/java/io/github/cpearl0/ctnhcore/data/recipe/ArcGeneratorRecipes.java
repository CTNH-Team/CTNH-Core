package io.github.cpearl0.ctnhcore.data.recipe;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.common.data.GCYMBlocks;
import com.gregtechceu.gtceu.common.data.machines.GCYMMachines;
import com.gregtechceu.gtceu.data.recipe.CraftingComponent;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.generator.Arc_Generator;
import io.github.cpearl0.ctnhcore.common.machine.simple.EfficiencyGeneratorMachine;
import io.github.cpearl0.ctnhcore.registry.CTNHBlocks;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeModifiers;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksB;
import io.github.cpearl0.ctnhcore.utils.CTNHMachineUtils;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.GTValues.EV;
import static com.gregtechceu.gtceu.api.GTValues.UV;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTItems.COVER_SCREEN;
import static com.gregtechceu.gtceu.common.data.GTMaterials.SamariumIronArsenicOxide;
import static com.gregtechceu.gtceu.common.data.GTMaterials.TungstenSteel;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLER_RECIPES;

public class ArcGeneratorRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("arc_cell"))
                .inputItems(GCYMBlocks.ELECTROLYTIC_CELL)
                .inputItems(cableGtOctal, TungstenSteel, 4)
                .inputItems(CustomTags.LuV_CIRCUITS, 2)
                .outputItems(CTNHBlocks.ARC_CELL)
                .duration(200).EUt(VA[IV]).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("arc_reactor"))
                .inputItems(CTNHBlocks.ARC_CELL,16)
                .inputItems(CustomTags.LuV_CIRCUITS, 16)
                .inputItems(GCYMBlocks.CASING_NONCONDUCTING, 64)
                .inputItems(GCYMMachines.LARGE_ELECTROLYZER)
                .outputItems(MultiblocksB.ARC_REACTOR)
                .duration(1000).EUt(VA[IV]).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(CTNHCore.id("arc_generator"))
                .inputItems(CTNHBlocks.ARC_CELL,16)
                .inputItems(CustomTags.LuV_CIRCUITS, 16)
                .inputItems(CustomTags.ZPM_CIRCUITS, 8)
                .inputItems(cableGtHex, SamariumIronArsenicOxide, 16)
                .inputItems(GCYMMachines.LARGE_ELECTROLYZER)
                .inputItems(GCYMBlocks.CASING_NONCONDUCTING)
                .outputItems(MultiblocksB.ARC_GENERATOR)
                .duration(2000).EUt(VA[IV]).save(provider);

        CTNHRecipeTypes.ARC_GENERATOR.recipeBuilder(CTNHCore.id("arc_generator_1"))
                .inputItems(block,TungstenSteel)
                .outputItems(dustTiny,TungstenSteel,4)
                .duration(200)
                .EUt(-262144)
                .addData("requirearc",100)
                .addData("maxarc",1000)
                .save(provider);

    }
}
