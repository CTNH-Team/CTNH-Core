package io.github.cpearl0.ctnhcore.registry;

import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifierList;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.common.data.machines.GCYMMachines;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public class GTMachineModify {

    public static BiConsumer<ItemStack, List<Component>> REDUCTION_INFO = (itemStack, list) -> list.add(Component.translatable("ctnh.gcym.reduction").withStyle(ChatFormatting.GREEN));
    public static void init() {
        List<MachineDefinition> gcymMachinesToModify = Arrays.asList(
                GCYMMachines.LARGE_ARC_SMELTER,
                GCYMMachines.LARGE_AUTOCLAVE,
                GCYMMachines.LARGE_BREWER,
                GCYMMachines.LARGE_CENTRIFUGE,
                GCYMMachines.LARGE_CHEMICAL_BATH,
                GCYMMachines.LARGE_CUTTER,
                GCYMMachines.LARGE_CIRCUIT_ASSEMBLER,
                GCYMMachines.LARGE_DISTILLERY,
                GCYMMachines.LARGE_ELECTROLYZER,
                GCYMMachines.LARGE_ELECTROMAGNET,
                GCYMMachines.LARGE_EXTRACTOR,
                GCYMMachines.LARGE_EXTRUDER,
                GCYMMachines.LARGE_ENGRAVING_LASER,
                GCYMMachines.LARGE_MACERATION_TOWER,
                GCYMMachines.LARGE_MIXER,
                GCYMMachines.LARGE_MATERIAL_PRESS,
                GCYMMachines.LARGE_PACKER,
                GCYMMachines.LARGE_SOLIDIFIER,
                GCYMMachines.LARGE_SIFTING_FUNNEL,
                GCYMMachines.LARGE_WIREMILL,
                GCYMMachines.MEGA_BLAST_FURNACE,
                GCYMMachines.MEGA_VACUUM_FREEZER
        );
        RecipeModifierList commonModifier = new RecipeModifierList(
                CTNHRecipeModifiers.GCYM_REDUCTION,
                GTRecipeModifiers.PARALLEL_HATCH,
                GTRecipeModifiers.OC_NON_PERFECT_SUBTICK,
                GTRecipeModifiers.BATCH_MODE
        );
        for (MachineDefinition machine : gcymMachinesToModify) {
            machine.setRecipeModifier(commonModifier);
            machine.setTooltipBuilder(machine.getTooltipBuilder().andThen(REDUCTION_INFO));
        }
        modifyGTAssembly();
    }
    private static void modifyGTAssembly() {
        var lASB = GCYMMachines.LARGE_ASSEMBLER;
        var lASBRecipeTypes = new java.util.ArrayList<>(Arrays.stream(lASB.getRecipeTypes()).toList());
        lASBRecipeTypes.add(CTNHRecipeTypes.PRECISION_ASSEMBLY_RECIPES);
        lASB.setRecipeTypes(lASBRecipeTypes.toArray(GTRecipeType[]::new));
        lASB.setTooltipBuilder(lASB.getTooltipBuilder().andThen((itemStack, components) -> {
            components.add(Component.translatable("ctnh.gcym.reduction").withStyle(ChatFormatting.GREEN));
            components.add(Component.translatable("ctnh.multiblock.precision_assembly.tooltip.0"));
            components.add(Component.translatable("ctnh.multiblock.precision_assembly.tooltip.1"));
        }
        ));

        lASB.setRecipeModifier(
                new RecipeModifierList(
                        CTNHRecipeModifiers.GCYM_REDUCTION,
                        GTMachineModify::assemblyRecipeModifier,
                        GTRecipeModifiers.BATCH_MODE
                )
        );
    }
    private static ModifierFunction assemblyRecipeModifier(MetaMachine machine, GTRecipe gtRecipe) {
        if (gtRecipe.recipeType == CTNHRecipeTypes.PRECISION_ASSEMBLY_RECIPES) {
            return  GTRecipeModifiers.OC_NON_PERFECT_SUBTICK.getModifier(machine, gtRecipe);
        } else {
            return new RecipeModifierList(
                    GTRecipeModifiers.PARALLEL_HATCH,
                    GTRecipeModifiers.OC_NON_PERFECT_SUBTICK
            ).getModifier(machine, gtRecipe);
        }
    }
}
