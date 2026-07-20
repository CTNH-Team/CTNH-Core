package io.github.cpearl0.ctnhcore.common.machine.multiblock.kinetic;

import io.github.cpearl0.ctnhcore.registry.CTNHRecipeModifiers;

import com.ctnhlang.CN;
import com.ctnhlang.EN;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.handler.RecipeHandlerGroup;

import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sfiomn.legendarysurvivaloverhaul.api.temperature.TemperatureUtil;

import java.util.List;

import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

public class IndustrialPrimitiveBlastFurnaceMachine extends NoEnergyMachine {

    @CN("并行数：%d")
    @EN("Parallel count: %d")
    public static Lang industrialPrimitiveBlastFurnaceInfoParallelCount;

    @Nullable
    protected TickableSubscription temperatureSubs;
    @Persisted
    public int currentTemperature = 0;
    @Getter
    public int maxTemperature = 2400;
    public int heatSpeed = 5;
    public int basicTemperature = 0;

    public IndustrialPrimitiveBlastFurnaceMachine(IMachineBlockEntity holder) {
        super(holder);
    }

    public static Component recipeModifier(MetaMachine machine, RecipeHandlerGroup group, @NotNull GTRecipe recipe) {
        if (machine instanceof IndustrialPrimitiveBlastFurnaceMachine imachine) {
            var parallel = imachine.getParallelCount();
            recipe.multiplyDuration(1.25 - (double) (imachine.currentTemperature - imachine.basicTemperature) /
                    (imachine.maxTemperature - imachine.basicTemperature) * 0.75);
            return CTNHRecipeModifiers.accurateParallel(imachine, group, recipe, parallel);
        }
        return null;
    }

    public int getParallelCount() {
        if (currentTemperature < 600) {
            return 1;
        } else if (currentTemperature < 1200) {
            return 2;
        } else if (currentTemperature < 1800) {
            return 4;
        } else {
            return 8;
        }
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        if (isFormed()) {
            textList.add(Component.translatable("gtceu.multiblock.blast_furnace.max_temperature",
                    Component.literal(currentTemperature + "K").withStyle(ChatFormatting.RED)));
            textList.add(industrialPrimitiveBlastFurnaceInfoParallelCount.translate(getParallelCount()));
        }
        super.addDisplayText(textList);
    }

    @Override
    public void onLoad() {
        super.onLoad();

        if (getLevel() instanceof ServerLevel serverLevel) {
            serverLevel.getServer().tell(new TickTask(0, this::updateTempSubscription));
        }
    }

    protected void updateTempSubscription() {
        if (currentTemperature >= basicTemperature) {
            temperatureSubs = subscribeServerTick(temperatureSubs, this::updateCurrentTemperature);
        } else if (temperatureSubs != null) {
            temperatureSubs.unsubscribe();
            temperatureSubs = null;
        }
    }

    protected void updateCurrentTemperature() {
        basicTemperature = (int) TemperatureUtil.getWorldTemperature(getLevel(), getPos()) + 273;
        if (recipeLogic.isWorking()) {
            if (getOffsetTimer() % 10 == 0) {
                if (currentTemperature < getMaxTemperature()) {
                    currentTemperature = Mth.clamp(currentTemperature + heatSpeed, basicTemperature,
                            getMaxTemperature());
                }
            }
        } else if (currentTemperature > basicTemperature) {
            currentTemperature -= getCoolDownRate();
        }
    }

    protected int getCoolDownRate() {
        return 1;
    }
}
