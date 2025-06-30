package io.github.cpearl0.ctnhcore.common.machine.multiblock.magic;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.MachineUtils;
import io.github.cpearl0.ctnhcore.registry.CTNHMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeModifiers;
import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.common.block.block_entity.mana.ManaPoolBlockEntity;

public class ManaCondenserMachine extends WorkableElectricMultiblockMachine {
    public BlockPos poolPos = MachineUtils.getOffset(this, 0, 4, 0);
    public int parallel = 1;
    public int basicMana = 1000;
    public boolean reverse = false;
    public ManaCondenserMachine(IMachineBlockEntity holder) {
        super(holder);
    }
    public static ModifierFunction recipeModifier(MetaMachine machine, GTRecipe recipe){
        if (machine instanceof ManaCondenserMachine mmachine) {
            mmachine.parallel = ParallelLogic.getParallelAmount(mmachine, recipe, Integer.MAX_VALUE);
            return CTNHRecipeModifiers.accurateParallel(machine, recipe, Integer.MAX_VALUE);
        }
        return ModifierFunction.IDENTITY;
    }

    @Override
    public boolean onWorking() {
        if (getOffsetTimer() % 5 == 0){
            if (getLevel().getBlockEntity(poolPos) instanceof ManaPoolBlockEntity manaPoolBlockEntity){
                if (reverse) {
                    manaPoolBlockEntity.receiveMana(basicMana * parallel);
                }
                else {
                    manaPoolBlockEntity.receiveMana(-basicMana * parallel);
                }
            }
        }
        return super.onWorking();
    }

    @Override
    public boolean beforeWorking(@Nullable GTRecipe recipe) {
        if (getLevel().getBlockEntity(poolPos) instanceof ManaPoolBlockEntity manaPoolBlockEntity){
            if (recipe.data.get("mode") != null && recipe.data.get("mode").equals("reverse")) {
                reverse = true;
                if (manaPoolBlockEntity.getAvailableSpaceForMana() < basicMana * parallel * 10) {
                    return false;
                }
                if (!MachineUtils.canInputFluid(CTNHMaterials.Mana.getFluid(basicMana * parallel / 5), this)) {
                    return false;
                }
                return super.beforeWorking(recipe);
            }
            else {
                reverse = false;
                if (manaPoolBlockEntity.getCurrentMana() < basicMana * parallel * 10) {
                    return false;
                }
                if (!MachineUtils.canOutputFluid(CTNHMaterials.Mana.getFluid(basicMana * parallel / 5), this)) {
                    return false;
                }
                return super.beforeWorking(recipe);
            }
        }
        return false;
    }
}
