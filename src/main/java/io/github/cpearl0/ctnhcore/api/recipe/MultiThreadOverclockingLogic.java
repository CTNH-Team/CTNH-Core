package io.github.cpearl0.ctnhcore.api.recipe;

import com.google.common.math.IntMath;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.utils.GTMath;
import com.gregtechceu.gtceu.utils.GTUtil;
import org.jetbrains.annotations.NotNull;

import java.math.RoundingMode;

public interface MultiThreadOverclockingLogic extends OverclockingLogic {

    MultiThreadOverclockingLogic MULTI_THREAD_PERFECT_OVERCLOCK =  create(PERFECT_DURATION_FACTOR, STD_VOLTAGE_FACTOR, false);
    MultiThreadOverclockingLogic MULTI_THREAD_NON_PERFECT_OVERCLOCK = create(STD_DURATION_FACTOR, STD_VOLTAGE_FACTOR, false);

    static MultiThreadOverclockingLogic create(double durationFactor, double voltageFactor, boolean subtick) {
        if (subtick) return (params, maxV) -> OverclockingLogic.subTickParallelOC(params, maxV, durationFactor, voltageFactor);
        else return (params, maxV) -> OverclockingLogic.standardOC(params, maxV, durationFactor, voltageFactor);
    }

    @Override
    default @NotNull ModifierFunction getModifier(MetaMachine machine, GTRecipe recipe, long maxVoltage, boolean shouldParallel) {
        long EUt = RecipeHelper.getRealEUt(recipe).getTotalEU();
        if (EUt == 0) return ModifierFunction.IDENTITY;

        int recipeTier = GTUtil.getTierByVoltage(EUt);
        int maximumTier = GTUtil.getOCTierByVoltage(maxVoltage);
        int OCs = maximumTier - recipeTier;
        if (recipeTier == GTValues.ULV) OCs--;

        if (OCs == 0) return ModifierFunction.IDENTITY;


        int maxParallels;
        if (!shouldParallel) { // don't parallel
            maxParallels = 1;
        } else {
            // lg = floor(log_4(duration)), which is how many OCs it takes to get duration < 4 with perfect duration
            // factor
            // If OCs <= lg, duration probably won't go below 4
            // If OCs > lg, then we could have 4^(OCs - lg) parallels
            // Note that 4^x = (2^2)^x = 2^(2x) = 1 << 2x
            int lg = IntMath.log2(recipe.duration, RoundingMode.FLOOR) / 2;
            if (lg > OCs) {
                maxParallels = 16;
            } else {
                int p = GTMath.saturatedCast((1L << (2 * (OCs - lg))) + 1);
                maxParallels = ParallelLogic.getParallelAmount(machine, recipe, p);
            }
        }

        OCParams params = new OCParams(EUt, recipe.duration, OCs, maxParallels);
        OCResult result = runOverclockingLogic(params, maxVoltage);
        return result.toModifier();
    }

}
