package io.github.cpearl0.ctnhcore.common.machine.multiblock.generator;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.EURecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.MultiblockComputationMachine;
import io.github.cpearl0.ctnhcore.client.renderer.utils.RenderUtils;
import io.github.cpearl0.ctnhcore.common.blockentity.TurbineRotorBE;
import io.github.cpearl0.ctnhcore.data.recipe.utils.ComputationModifier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3i;

import java.util.List;

import static io.github.cpearl0.ctnhcore.utils.MathUtils.fastLog2;

public class HyperPlasmaTurbineMachine extends MultiblockComputationMachine{
    public static final long BASE_EU_OUTPUT = GTValues.V[GTValues.ZPM] * 288;/*有算力时的基础功率*/
    public static final long DEFAULT_EU_OUTPUT = GTValues.V[GTValues.ZPM];/*没有算力时的默认功率*/
    public static final int CWUtStair = 64;

    public HyperPlasmaTurbineMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }


    @Override
    public boolean checkPattern() {
        if(super.checkPattern()){
            //转子：往里四格，往左/右[4,11]格
            var level = getLevel();
            if (level == null) return false;
            var dfront = getFrontFacing();
            var dup = Direction.UP;

            var front = RenderUtils.directionVectors.get(dfront);
            var up = RenderUtils.directionVectors.get(dup);
            var left = RenderUtils.cross(front, up);
            var right = new Vector3i(left).negate();
            Vector3i loffset, roffset;
            BlockPos lpos, rpos, pos = getPos();
            for (int i = 4; i <= 11; i++) {
                loffset = new Vector3i(left).mul(i).add(new Vector3i(front).mul(-4));
                roffset = new Vector3i(left).mul(-i).add(new Vector3i(front).mul(-4));
                lpos = new BlockPos(pos.getX() + loffset.x, pos.getY() + loffset.y, pos.getZ() + loffset.z);
                rpos = new BlockPos(pos.getX() + roffset.x, pos.getY() + roffset.y, pos.getZ() + roffset.z);
                if (level.getBlockEntity(lpos) instanceof TurbineRotorBE ltbe
                    /*&& RenderUtils.dircetionVectors.get(ltbe.getBlockState().getValue(BlockStateProperties.FACING)).equals(left)*/) {
                    if (!RenderUtils.directionVectors.get(ltbe.getBlockState().getValue(BlockStateProperties.FACING)).equals(left)) {
                        var new_state = ltbe.getBlockState().setValue(BlockStateProperties.FACING, RenderUtils.vectorDirections.get(left));
                        level.setBlockAndUpdate(lpos, new_state);
                    }
                } else return false;
                if (level.getBlockEntity(rpos) instanceof TurbineRotorBE rtbe
                    /*&& RenderUtils.dircetionVectors.get(rtbe.getBlockState().getValue(BlockStateProperties.FACING)).equals(right)*/) {
                    if (!RenderUtils.directionVectors.get(rtbe.getBlockState().getValue(BlockStateProperties.FACING)).equals(right)) {
                        var new_state = rtbe.getBlockState().setValue(BlockStateProperties.FACING, RenderUtils.vectorDirections.get(right));
                        level.setBlockAndUpdate(rpos, new_state);
                    }
                } else return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
    }

    @Override
    public long getOverclockVoltage() {
        /*算力足够时以基础功率zpm288a为底，每多提供64算力就翻倍输出功率*/
        var cwut = getMaxCWUt();
        if (cwut < CWUtStair) {
            return DEFAULT_EU_OUTPUT;
        }
        return Long.min(BASE_EU_OUTPUT << (int) (cwut / CWUtStair - 1),super.getOverclockVoltage());
    }

    /*产能效率*/
    static public double getEfficiency() {
        return 4.0;
    }

    @Override
    public boolean isGenerator() {
        return true;
    }


    //////////////////////////////////////
    // ****** Recipe Logic *******//
    /// ///////////////////////////////////

    public static ModifierFunction recipeModifier(@NotNull MetaMachine machine, @NotNull GTRecipe recipe) {
        if (!(machine instanceof HyperPlasmaTurbineMachine hptm)) {
            return RecipeModifier.nullWrongType(HyperPlasmaTurbineMachine.class, machine);
        }


        final long EUt = RecipeHelper.getRealEUtWithIO(recipe).voltage();
        final long turbineMaxVoltage = hptm.getOverclockVoltage();

        if (EUt <= 0 || turbineMaxVoltage <= EUt) return ModifierFunction.NULL;

        // get the amount of parallel required to match the desired output voltage
        final double euMultiplier = getEfficiency();
        final int maxParallel = (int) (turbineMaxVoltage / EUt);
        final int actualParallel = ParallelLogic.getParallelAmountFast(hptm, recipe, maxParallel);
        final long actualEUt = EUt * actualParallel;



        var ret = ModifierFunction.builder()
                .inputModifier(ContentModifier.multiplier(actualParallel))
                .outputModifier(ContentModifier.multiplier(actualParallel))
                .eutMultiplier(actualParallel)
                .parallels(actualParallel)
                .durationMultiplier(euMultiplier)
                .build();
        if(actualEUt > DEFAULT_EU_OUTPUT){
            final int cwut = (actualEUt <= BASE_EU_OUTPUT) ? CWUtStair :
                    CWUtStair * (fastLog2(actualEUt)/fastLog2(BASE_EU_OUTPUT-1) + 2);
            ret = ComputationModifier.append(cwut)  //顺序是个大坑
                    .andThen(ret);
        }
        return ret;
    }


    @Override
    public boolean regressWhenWaiting() {
        return false;
    }


    @Override
    public boolean canVoidRecipeOutputs(RecipeCapability<?> capability) {
        return capability != EURecipeCapability.CAP;
    }

    //////////////////////////////////////
    // ******* GUI ********//
    /// ///////////////////////////////////

    @Override
    public void addDisplayText(@NotNull List<Component> textList) {
        if (isFormed()) {

            textList.add(Component.translatable("gtceu.multiblock.turbine.efficiency",
                    getEfficiency() * 100));

            long maxProduction = getOverclockVoltage();
            long currentProduction = isActive() && recipeLogic.getLastRecipe() != null ?
                    RecipeHelper.getRealEUtWithIO(recipeLogic.getLastRecipe()).voltage() : 0;

            if (isActive()) {
                textList.add(Component.translatable("gtceu.multiblock.turbine.energy_per_tick",
                        FormattingUtil.formatNumbers(currentProduction),
                        FormattingUtil.formatNumbers(maxProduction)));
            }
        }
        super.addDisplayText(textList);
    }

}
