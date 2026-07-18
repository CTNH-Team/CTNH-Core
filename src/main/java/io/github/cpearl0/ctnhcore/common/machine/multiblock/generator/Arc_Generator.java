package io.github.cpearl0.ctnhcore.common.machine.multiblock.generator;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.RecipeElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.handler.RecipeHandlerGroup;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;

import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Arc_Generator extends RecipeElectricMultiblockMachine implements ITieredMachine {

    public int arc = 0;
    public int arc_max = 0;
    public double efficiency = 0;
    public double rotor = 0;
    public String ARC = "arc";

    public Arc_Generator(IMachineBlockEntity holder, double efficiency, int arc_max) {
        super(holder);
        this.efficiency = efficiency;
        this.arc_max = arc_max;
    }

    @Override
    public Component beforeWorking(@NotNull GTRecipe recipe) {
        if (arc < recipe.data.getInt("requirearc")) {
            return RecipeModifier.DEFAULT_FAILURE;
        }
        arc -= recipe.data.getInt("requirearc") / 10;
        arc = Math.max(0, arc);

        return super.beforeWorking(recipe);
    }

    public static Component recipeModifier(MetaMachine machine, RecipeHandlerGroup group, GTRecipe recipe) {
        if (machine instanceof Arc_Generator gmachine) {
            var arc_difference = recipe.data.getInt("maxarc") - recipe.data.getInt("requirearc");
            double rotor = Math.max(0.00001,
                    (double) (gmachine.arc - recipe.data.getInt("requirearc")) / arc_difference);
            rotor = Math.min(gmachine.efficiency, rotor);
            gmachine.rotor = rotor;
            recipe.multiplyEUt(rotor);
            return null;
        }
        return RecipeModifier.DEFAULT_FAILURE;
    }

    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);
        var tier = getTier();

        textList.add(textList.size(),
                Component.translatable("ctnh.multiblock.arcgenerator.info.0", String.format("%d", arc_max)));
        textList.add(textList.size(),
                Component.translatable("ctnh.multiblock.arcgenerator.info.1", String.format("%d", arc)));
        textList.add(textList.size(),
                Component.translatable("ctnh.multiblock.arcgenerator.info.2", String.format("%.2f", efficiency * 100)));
        textList.add(textList.size(),
                Component.translatable("ctnh.multiblock.arcgenerator.info.3", String.format("%.2f", rotor * 100)));
    }

    @Override
    public boolean regressWhenWaiting() {
        return false;
    }
}
