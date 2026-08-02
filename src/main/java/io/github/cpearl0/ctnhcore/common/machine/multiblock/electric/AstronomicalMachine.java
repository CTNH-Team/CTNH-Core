package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import io.github.cpearl0.ctnhcore.common.machine.multiblock.part.CircuitBusPartMachine;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.RecipeElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.handler.RecipeHandlerList;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.List;
import java.util.Objects;

public class AstronomicalMachine extends RecipeElectricMultiblockMachine {

    @CN("只能在夜晚使用")
    @EN("Can only be used at night")
    public static Lang astronomicalInfoInvalid;

    public static final int START_TIME = 23000;
    public static final int END_TIME = 13000;

    @Getter
    private CircuitBusPartMachine circuitBus;

    public AstronomicalMachine(IMachineBlockEntity holder) {
        super(holder);
    }

    private boolean isValidPhotovoltaicPower() {
        var time = Objects.requireNonNull(getLevel()).getDayTime() % 24000;
        return time > END_TIME && time < START_TIME;
    }

    @Override
    public boolean keepSubscribing() {
        return true;
    }

    @Override
    public Component beforeWorking(@NotNull GTRecipe recipe) {
        if (isValidPhotovoltaicPower()) return null;
        return astronomicalInfoInvalid.translate();
    }

    @Override
    public void addDisplayText(@NotNull List<Component> textList) {
        if (isFormed()) {
            if (!isValidPhotovoltaicPower()) {
                textList.add(astronomicalInfoInvalid.translate()
                        .withStyle(ChatFormatting.RED));
            } else {
                super.addDisplayText(textList);
            }
        }
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        for (IMultiPart part : getParts()) {
            if (part instanceof CircuitBusPartMachine circuitBusPartMachine) {
                this.circuitBus = circuitBusPartMachine;
                var handlerList = RecipeHandlerList.of(List.of(circuitBus.getInventory()));
                recipeHandlerLists.add(handlerList);
                traitSubscriptions.add(handlerList.subscribe(recipeLogic::updateTickSubscription));
            }
        }
    }
}
