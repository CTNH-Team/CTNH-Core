package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.handler.RecipeHandlerGroup;
import com.gregtechceu.gtceu.common.machine.multiblock.part.FluidHatchPartMachine;
import com.gregtechceu.gtceu.utils.FormattingUtil;

import com.lowdragmc.lowdraglib.side.fluid.FluidStack;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Prefix;
import org.jetbrains.annotations.Nullable;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.utils.MachineUtils;

import java.util.List;

import static sfiomn.legendarysurvivaloverhaul.api.temperature.TemperatureUtil.getWorldTemperature;

@Prefix("fermenting_tank_machine")
public class FermentingTankMachine extends CoilWorkableElectricMultiblockMachine {

    @Nullable
    protected TickableSubscription temperatureSubs;

    public double Machine_Temperature = 0;
    public double Efficiency = 1;
    public double Lower_limit = 0.2;

    public FermentingTankMachine(IMachineBlockEntity holder) {
        super(holder);
    }

    @CN("生长温度：§2%d°C§r")
    @EN("Growth Temperature：§2%d°C§r")
    static Lang growing_temperature;

    @CN("生长效率：%d%%")
    @EN("Growth Efficiency：%d%%")
    static Lang growing_efficiency;

    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);
        textList.add(Component.translatable("gtceu.multiblock.blast_furnace.max_temperature", Component
                .translatable(FormattingUtil.formatNumbers(
                        getCoilType().getCoilTemperature() + 100L * Math.max(0, getTier() - GTValues.MV)) + "K")
                .setStyle(Style.EMPTY.withColor(ChatFormatting.RED))));
        textList.add(textList.size(),
                growing_temperature.translate(String.format("%.1f", Machine_Temperature))
                        .setStyle(Style.EMPTY.withColor(ChatFormatting.GREEN)));
        textList.add(textList.size(), growing_efficiency.translate(String.format("%.1f", Efficiency * 100)));
    }

    public static Component recipeModifier(MetaMachine machine, RecipeHandlerGroup group, GTRecipe recipe) {
        if (machine instanceof FermentingTankMachine fmachine) {
            fmachine.Efficiency = 1;
            MachineUtils.applyContents(fmachine, (contents, part) -> {
                if (contents instanceof FluidStack fluid) {
                    double current = fluid.getAmount();
                    var total = FluidHatchPartMachine.getTankCapacity(FluidHatchPartMachine.INITIAL_TANK_CAPACITY,
                            part.self().getDefinition().getTier());
                    double density = current / total;
                    double logistic = 8 * (density - Math.pow(density, 2));
                    logistic = Math.max(fmachine.Lower_limit, logistic);
                    fmachine.Efficiency *= logistic;
                }
            }, FluidRecipeCapability.CAP, IO.IN);
            if (fmachine.Machine_Temperature >= 36 && fmachine.Machine_Temperature <= 38) {
                fmachine.Efficiency *= 1.2;
            } else {
                fmachine.Efficiency /= Math.min(3,
                        Math.pow(Math.max(36 - fmachine.Machine_Temperature, fmachine.Machine_Temperature - 38), 2) /
                                10 + 1);
            }
            recipe.multiplyDuration(1 / fmachine.Efficiency);
            return null;
        }
        return null;
    }

    @Override
    public void onLoad() {
        super.onLoad();

        if (getLevel() instanceof ServerLevel serverLevel) {
            serverLevel.getServer().tell(new TickTask(0, this::updateTempSubscription));
        }
    }

    protected void updateTempSubscription() {
        temperatureSubs = subscribeServerTick(temperatureSubs, this::updateCurrentTemperature);
    }

    protected void updateCurrentTemperature() {
        if (getOffsetTimer() % 10 == 0) {
            Machine_Temperature = (int) getWorldTemperature(getLevel(), getPos());
        }
    }
}
