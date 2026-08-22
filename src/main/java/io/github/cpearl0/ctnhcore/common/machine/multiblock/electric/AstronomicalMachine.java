package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.RecipeElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
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
}
