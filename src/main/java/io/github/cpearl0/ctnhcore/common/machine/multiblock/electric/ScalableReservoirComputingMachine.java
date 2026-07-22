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

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3i;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

public class ScalableReservoirComputingMachine extends RecipeElectricMultiblockMachine
                                               implements ComputationProducer, IControllable {

    @CN("无牺牲者")
    @EN("No sacrifices found")
    public static Lang ctnhSrcSacrificeEmpty;

    @CN("已锁定牺牲者！")
    @EN("Sacrifice LOCKED")
    public static Lang ctnhSrcSacrificeLocked;

    @CN("无法锁定牺牲者")
    @EN("Sacrifice UNLOCKED")
    public static Lang ctnhSrcSacrificeUnlocked;

    @CN("湿件剩余存活时间: %s ticks")
    @EN("Wetware duration: %s ticks")
    public static Lang ctnhSrcWetwareDuration;

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

    private static Lang sacrificeStatus(SacrificeLockState state) {
        return switch (state) {
            case SACRIFICE_EMPTY -> ctnhSrcSacrificeEmpty;
            case SACRIFICE_LOCKED -> ctnhSrcSacrificeLocked;
            case SACRIFICE_UNLOCKED -> ctnhSrcSacrificeUnlocked;
        };
    }

    @Override
    public void addDisplayText(@NotNull List<Component> textList) {
        if (isFormed()) {
            if (!isActive()) {
                textList.add(sacrificeStatus(sacrificeLockState).translate());
            }
            textList.add(ctnhSrcWetwareDuration.translate(
                    FormattingUtil.formatNumbers(duration)));
            textList.add(Component.translatable("gtceu.multiblock.computation.max",
                    FormattingUtil.formatNumbers(maxCWUt)));
            textList.add(Component.translatable("gtceu.multiblock.computation.usage",
                    FormattingUtil.formatNumbers(lastCWUt)));
        }
        super.addDisplayText(textList);
    }
}
