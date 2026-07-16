package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import io.github.cpearl0.ctnhcore.common.machine.trait.ScalableReservoirComputingLogic;

import com.gregtechceu.gtceu.api.capability.IControllable;
import com.gregtechceu.gtceu.api.computation.ComputationProducer;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.RecipeElectricMultiblockMachine;
import com.gregtechceu.gtceu.utils.FormattingUtil;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3i;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

public class ScalableReservoirComputingMachine extends RecipeElectricMultiblockMachine
                                               implements ComputationProducer, IControllable {

    public ScalableReservoirComputingMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    public int maxCWUt, duration, lastCWUt;
    @Getter
    AABB aabb;
    public static int RANGE = 4;

    public void updateSacrifice() {
        var level = getLevel();
        if (level == null || level.isClientSide()) return;
        var sacrifices = level.getEntitiesOfClass(LivingEntity.class, aabb);
        if (sacrifices.size() == 1) {
            getRecipeLogic().lockedSacrifice = sacrifices.iterator().next();
            sacrificeLockState = SacrificeLockState.SACRIFICE_LOCKED;
        } else if (sacrifices.size() > 1) {
            sacrificeLockState = SacrificeLockState.SACRIFICE_UNLOCKED;
        } else sacrificeLockState = SacrificeLockState.SACRIFICE_EMPTY;
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
        aabb = null;
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        var pos = new Vector3i(holder.pos().getX(), holder.pos().getY(), holder.pos().getZ());
        // 往后4格，往上2格
        // pos.add(
        // new Vector3i( RenderUtils.dircetionVectors.get(getFrontFacing()) ).mul(4)
        // ).add(
        // new Vector3i( RenderUtils.dircetionVectors.get(getUpwardsFacing()) ).mul(2)
        // );
        aabb = new AABB(
                pos.x - RANGE, pos.y - RANGE, pos.z - RANGE,
                pos.x + RANGE, pos.y + RANGE, pos.z + RANGE);
    }

    @Override
    public int getOfferedCWUt() {
        return duration > 0 ? maxCWUt : 0;
    }

    @Override
    public void applyProducedCWUt(int allocatedCWUt) {
        lastCWUt = allocatedCWUt;
        if (allocatedCWUt > 0 && duration > 0) {
            duration--;
        }
    }

    @Override
    @NotNull
    @ParametersAreNonnullByDefault
    protected ScalableReservoirComputingLogic createRecipeLogic(Object... args) {
        return new ScalableReservoirComputingLogic(this);
    }

    @Override
    @NotNull
    public ScalableReservoirComputingLogic getRecipeLogic() {
        return (ScalableReservoirComputingLogic) recipeLogic;
    }

    /// ///////////////////////
    // GUI
    /// ///////////////////////
    public static enum SacrificeLockState {
        SACRIFICE_EMPTY,
        SACRIFICE_LOCKED,
        SACRIFICE_UNLOCKED
    }

    SacrificeLockState sacrificeLockState = SacrificeLockState.SACRIFICE_EMPTY;

    @Override
    public void addDisplayText(@NotNull List<Component> textList) {
        if (isFormed()) {
            if (!isActive()) {
                textList.add(Component.translatable("ctnhcore.src." + sacrificeLockState.name().toLowerCase()));
            }
            textList.add(Component.translatable("ctnhcore.src.wetware_duration",
                    FormattingUtil.formatNumbers(duration)));
            textList.add(Component.translatable("gtceu.multiblock.computation.max",
                    FormattingUtil.formatNumbers(maxCWUt)));
            textList.add(Component.translatable("gtceu.multiblock.computation.usage",
                    FormattingUtil.formatNumbers(lastCWUt)));
        }
        super.addDisplayText(textList);
    }
}
