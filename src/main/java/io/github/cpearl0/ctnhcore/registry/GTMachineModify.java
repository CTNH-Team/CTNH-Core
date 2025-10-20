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
import com.gregtechceu.gtceu.common.machine.multiblock.electric.CleanroomMachine;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

import static com.gregtechceu.gtceu.common.data.machines.GTMultiMachines.CLEANROOM;

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
        modifyCleanroom();
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

    private static void modifyCleanroom() {
        CLEANROOM.setBeforeWorking(
                (machine, recipe) -> {
                    if (machine instanceof CleanroomMachine cleanroom) {
                        try {
                            // Use reflection to access the private distance fields
                            Class<?> cleanroomClass = cleanroom.getClass();

                            // Get the fields
                            Field lDistField = cleanroomClass.getDeclaredField("lDist");
                            Field rDistField = cleanroomClass.getDeclaredField("rDist");
                            Field bDistField = cleanroomClass.getDeclaredField("bDist");
                            Field fDistField = cleanroomClass.getDeclaredField("fDist");
                            Field hDistField = cleanroomClass.getDeclaredField("hDist");

                            // Make them accessible
                            lDistField.setAccessible(true);
                            rDistField.setAccessible(true);
                            bDistField.setAccessible(true);
                            fDistField.setAccessible(true);
                            hDistField.setAccessible(true);

                            // Get the values
                            int lDist = lDistField.getInt(cleanroom);
                            int rDist = rDistField.getInt(cleanroom);
                            int bDist = bDistField.getInt(cleanroom);
                            int fDist = fDistField.getInt(cleanroom);
                            int hDist = hDistField.getInt(cleanroom);

                            // Calculate length and width
                            int length = lDist + rDist;
                            int width = bDist + fDist;

                            // Check if height is greater than length or width
                            return hDist <= length && hDist <= width;
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            // Handle reflection errors
                            e.printStackTrace();
                            return true;
                        }
                    }
                    return true;
                }
        );
        CLEANROOM.setTooltipBuilder(CLEANROOM.getTooltipBuilder().andThen(
                (stack, tooltip) -> {
                    tooltip.add(Component.literal("高度大于长度或宽度时，将会停止工作"));
                }
        ));
    }
}
