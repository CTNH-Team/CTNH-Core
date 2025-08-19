package io.github.cpearl0.ctnhcore.common.machine.simple;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.SimpleGeneratorMachine;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;

public class EfficiencyGeneratorMachine extends SimpleGeneratorMachine {
    public int efficiency;
    public EfficiencyGeneratorMachine(IMachineBlockEntity holder, int tier, float hazardStrengthPerOperation, Int2IntFunction tankScalingFunction, Object... args) {
        super(holder, tier, hazardStrengthPerOperation, tankScalingFunction, args);
    }

    public EfficiencyGeneratorMachine(IMachineBlockEntity holder, int tier, Int2IntFunction tankScalingFunction, Object... args) {
        super(holder, tier, tankScalingFunction, args);
    }
    public static int getEfficiency(int tier, String name) {
        if (name.equals("naquadah_reactor")) {
            if (tier == 4) {
                return 80;
            }
            return (tier - 5) * 50 + 100;
        }
        else if(name.equals("rocket_engine")) {
            return 80 - (tier - 4) * 10;
        }
        return tier * 20 + 100;
    }
}
