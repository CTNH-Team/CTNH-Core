package io.github.cpearl0.ctnhcore.common.machine.trait;

import io.github.cpearl0.ctnhcore.common.machine.multiblock.UnderfloorHeatingMachine;

import com.gregtechceu.gtceu.api.machine.trait.MachineTrait;
import com.gregtechceu.gtceu.api.machine.trait.feature.IMultiblockMachineTrait;

import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;

import com.ctnh.ctnhastral.common.environment.EnvironmentArea;
import com.ctnh.ctnhastral.common.environment.EnvironmentDelta;
import com.ctnh.ctnhastral.common.environment.EnvironmentEmission;
import com.ctnh.ctnhastral.common.environment.Temperature;
import lombok.Getter;

/** 地暖功率、效率与排放生命周期的唯一所有者。 */
public class UnderfloorHeatingTrait extends MachineTrait implements IMultiblockMachineTrait {

    @Getter
    @Persisted
    @DescSynced
    private int rate = 100;

    @Getter
    @DescSynced
    private double efficiency;

    private long lastSampleTick = Long.MIN_VALUE;

    public UnderfloorHeatingTrait(UnderfloorHeatingMachine machine) {
        super(machine);
    }

    @Override
    public UnderfloorHeatingMachine getMachine() {
        return (UnderfloorHeatingMachine) super.getMachine();
    }

    public void setRate(int rate) {
        this.rate = Mth.clamp(rate, 25, 100);
        onChanged();
    }

    public void refreshEfficiency() {
        if (machine.getLevel() instanceof ServerLevel level) {
            efficiency = getMachine().getEfficiency();
            lastSampleTick = level.getGameTime();
        }
    }

    public boolean emitHeat() {
        if (!(machine.getLevel() instanceof ServerLevel level)) return true;
        if (lastSampleTick == Long.MIN_VALUE || level.getGameTime() - lastSampleTick >= 20) refreshEfficiency();
        var pos = machine.getPos();
        AABB bounds = switch (machine.getFrontFacing()) {
            case NORTH -> AABB.of(BoundingBox.fromCorners(pos.offset(-39, 0, -32), pos.offset(40, 16, 47)));
            case SOUTH -> AABB.of(BoundingBox.fromCorners(pos.offset(-40, 0, -47), pos.offset(39, 16, 32)));
            case WEST -> AABB.of(BoundingBox.fromCorners(pos.offset(-32, 0, -40), pos.offset(47, 16, 39)));
            case EAST -> AABB.of(BoundingBox.fromCorners(pos.offset(-47, 0, -39), pos.offset(32, 16, 40)));
            default -> throw new IllegalStateException("Invalid floor heating orientation");
        };
        new EnvironmentEmission(pos, new EnvironmentArea.Box(bounds),
                EnvironmentDelta.of(Temperature.TYPE, (float) (30.0 * efficiency * rate / 100.0))).emit(level, 2);
        return true;
    }

    public void stopHeating() {
        if (machine.getLevel() instanceof ServerLevel level) EnvironmentEmission.cease(level, machine.getPos());
    }

    @Override
    public void onStructureFormed() {
        refreshEfficiency();
    }

    @Override
    public void onStructureInvalid() {
        efficiency = 0.0;
        stopHeating();
    }

    @Override
    public void onPartUnload() {
        stopHeating();
    }

    @Override
    public void onMachineUnload() {
        stopHeating();
    }

    @Override
    public void onMachineDestroyed() {
        stopHeating();
    }

    @Override
    public void onWorkAllowedChanged(boolean allowed) {
        if (!allowed) stopHeating();
    }
}
