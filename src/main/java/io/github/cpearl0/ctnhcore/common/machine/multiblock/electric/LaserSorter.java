package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.utils.GTUtil;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.MultiblockComputationMachine;
import io.github.cpearl0.ctnhcore.data.recipe.utils.ComputationModifier;
import io.github.cpearl0.ctnhcore.utils.CTNHRecipeHelper;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Collectors;

import static com.gregtechceu.gtceu.api.GTValues.*;

public class LaserSorter extends MultiblockComputationMachine{
    public LaserSorter(IMachineBlockEntity holder) {
        super(holder);
    }
    public int get_base(GTRecipe recipe){
        final var inputCWUt=CTNHRecipeHelper.getInputCWUt(recipe);
        return inputCWUt>0?inputCWUt : Math.max(8*(GTUtil.getTierByVoltage(RecipeHelper.getRealEUtWithIO(recipe).voltage())-IV),8);
    }
    public int caculate_effency(@Nullable GTRecipe recipe)
    {
        var maxcwut=getMaxCWUt();
        return maxcwut/get_base(recipe);
    }
    public int get_true_cwut(@Nullable GTRecipe recipe)
    {
        var base = get_base(recipe);
        var maxcwut=getMaxCWUt();

        return (maxcwut/base)*base;
    }
    public boolean check_right(GTRecipe recipe){
        return get_true_cwut(recipe) == getMaxCWUt();
    }
    public static ModifierFunction recipeModifier(MetaMachine machine, GTRecipe recipe) {
        if (machine instanceof LaserSorter lmachine) {
            var p=recipe.getInputContents(ItemRecipeCapability.CAP);
            for(var num:p)
            {
                var d=(SizedIngredient)num.getContent();
                var f=d.getItems()[0];
                var s=f.getItem();
                var j=1;

            }
            var itemsR = recipe.getInputContents(ItemRecipeCapability.CAP).stream()
                    .map(num -> (SizedIngredient) num.getContent()) // 转换为 SizedIngredient
                    .map(SizedIngredient::getItems) // 获取 ItemStack 数组
                    .filter(itemStacks -> itemStacks.length > 0) // 确保至少有一个 ItemStack
                    .map(itemStacks -> itemStacks[0].getItem()) // 获取第一个 ItemStack 的 Item
                    .collect(Collectors.toList()); // 收集到一个 List<Item>

            var input=1.0;
            var muti=0.25;
            var parallel=1;
            if(lmachine.check_right(recipe))
            {
                muti=lmachine.caculate_effency(recipe);
                parallel=(int)(muti*muti*muti);
                if(recipe.recipeType.equals(GTRecipeTypes.LASER_ENGRAVER_RECIPES))
                {
                    input=1.25;
                }
            }
            var maxparallel=ParallelLogic.getParallelAmount(lmachine,recipe,parallel);
            int allow_overload=lmachine.getTier()-RecipeHelper.getRecipeEUtTier(recipe);

            var other_odifier = ModifierFunction.builder()
                    .parallels(maxparallel)
                    .inputModifier(ContentModifier.multiplier(maxparallel))
                    .outputModifier(ContentModifier.multiplier((int)maxparallel*input))
                    .durationMultiplier(1/(Math.pow(2,Math.min(allow_overload,muti))))
                    .eutMultiplier(maxparallel)
                    .build();
            int true_cwut = lmachine.get_true_cwut(recipe);
            if(true_cwut>0) {
                var cwut_modifier = ComputationModifier.append(true_cwut);
                return cwut_modifier.andThen(other_odifier);
            }
            return other_odifier;
        }
        return ModifierFunction.NULL;
    }



}
