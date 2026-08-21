package io.github.cpearl0.ctnhcore.common.machine.multiblock.kinetic;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.mo_guang.ctpp.common.machine.multiblock.KineticWorkableMultiblockMachine;
import com.mo_guang.ctpp.dynamicPart.rotation.IContraptionMultiblock;
import com.mo_guang.ctpp.dynamicPart.rotation.SimpleRotatingContraptionEntity;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import tech.vixhentx.mcmod.ctnhlib.utils.MachineUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KineticMixerMachine extends KineticWorkableMultiblockMachine
                                 implements IContraptionMultiblock<SimpleRotatingContraptionEntity> {

    @Getter
    @Setter
    List<SimpleRotatingContraptionEntity> contraptionEntity = new ArrayList<>();

    public KineticMixerMachine(IMachineBlockEntity holder) {
        super(holder);
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        // assemble rotating entities using interface helper
        createAndAttachRotatingEntities(MachineUtils.getOffset(this, 0, 0, 3), getContraptionRotationAxis());
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
        if (getLevel() != null && !getLevel().isClientSide) {
            clearAndDisassembleRotatingEntities();
        }
    }

    @Override
    public void updateMachineSpeed() {
        super.updateMachineSpeed();
        if (!contraptionEntity.isEmpty() && isFormed) {
            contraptionEntity.forEach(entity -> {
                entity.setRotationSpeedRPM(new Vec3(0, 1, 0), Math.min(speed, 128));
            });
        }
    }

    @Override
    public void updateRotateBlocks(boolean active) {
        super.updateRotateBlocks(active);
        if (active) {
            if (contraptionEntity != null)
                contraptionEntity.forEach(entity -> {
                    entity.setRotationSpeedRPM(new Vec3(0, 1, 0), Math.min(speed, 128));
                });
        }
    }

    public Map<Integer, SimpleRotatingContraptionEntity> assemble(BlockPos pivot) {
        return assembleFromPattern(pivot, getContraptionRotationAxis());
    }

    private Direction.Axis getContraptionRotationAxis() {
        return getFrontFacing().getAxis() == Direction.Axis.Z ? Direction.Axis.X : Direction.Axis.Z;
    }

    @Override
    public BlockPos getAssemblyPivot() {
        return MachineUtils.getOffset(this, 0, 0, 3);
    }

    @Override
    public void onDebugAssembled() {
        updateMachineSpeed();
        updateRotateBlocks(getRecipeLogic().isWorking());
    }
}
