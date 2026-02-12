package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.CN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.EN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.Prefix;

import java.util.List;
import java.util.Objects;

import static sfiomn.legendarysurvivaloverhaul.api.temperature.TemperatureUtil.getWorldTemperature;

@Prefix("bio_machine")
public class BioMachine extends WorkableElectricMultiblockMachine {

    public double machineTemperature = 0;
    public double efficiency = 1;

    public BioMachine(IMachineBlockEntity holder) {
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
        if(isFormed){
            machineTemperature = getWorldTemperature(Objects.requireNonNull(getLevel()), getPos());
            if (machineTemperature >= 36 && machineTemperature <= 38) {
                efficiency = 1.2;
            } else {
                efficiency = 1 /
                        Math.min(3, Math.pow(Math.max(36 - machineTemperature, machineTemperature - 38), 2) / 10 + 1);
            }
            textList.add(growing_temperature.translate(String.format("%.1f", machineTemperature))
                    .setStyle(Style.EMPTY.withColor(ChatFormatting.GREEN)));

            textList.add(growing_efficiency.translate(String.format("%.1f", efficiency * 100)));
        }
    }

    public static ModifierFunction recipeModifier(MetaMachine machine, GTRecipe recipe) {
        if (!(machine instanceof BioMachine dmachine)) return ModifierFunction.IDENTITY;
        dmachine.machineTemperature = getWorldTemperature(Objects.requireNonNull(dmachine.getLevel()),
                dmachine.getPos());
        if (dmachine.machineTemperature >= 36 && dmachine.machineTemperature <= 38) {
            dmachine.efficiency = 1.2;
        } else {
            dmachine.efficiency = 1 / Math.min(3,
                    Math.pow(Math.max(36 - dmachine.machineTemperature, dmachine.machineTemperature - 38), 2) / 10 +
                            1);
        }
        return ModifierFunction.builder().durationModifier(new ContentModifier(1 / dmachine.efficiency, 0)).build();
    }
}
