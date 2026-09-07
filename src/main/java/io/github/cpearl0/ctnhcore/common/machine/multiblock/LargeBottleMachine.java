package io.github.cpearl0.ctnhcore.common.machine.multiblock;

import com.gregtechceu.gtceu.api.fluids.PropertyFluidFilter;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.pattern.util.RelativeDirection;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.MultiblockTankMachine;
import com.gregtechceu.gtceu.common.machine.trait.multiblock.MultiblockFluidRendererTrait;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import org.jetbrains.annotations.Nullable;
import tech.vixhentx.mcmod.ctnhlib.utils.MachineUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LargeBottleMachine extends MultiblockTankMachine {

    private BlockPos getOffset(int left, int up, int back) {
        return MachineUtils.getOffset(this, -left, up, back);
    }

    public LargeBottleMachine(IMachineBlockEntity holder, int capacity, @Nullable PropertyFluidFilter filter,
                              Object... args) {
        super(holder, capacity, filter, args);
        attachTrait(new MultiblockFluidRendererTrait(this, this::saveOffsets));
    }

    public final List<BlockPos> SideBlockOffsets = List.of(
            getOffset(0, 1, 1),
            getOffset(1, 1, 1),
            getOffset(2, 1, 1),
            getOffset(-1, 1, 1),
            getOffset(-2, 1, 1),
            getOffset(-3, 1, 2),
            getOffset(3, 1, 2),
            getOffset(-3, 1, 3),
            getOffset(3, 1, 3),
            getOffset(-3, 1, 4),
            getOffset(3, 1, 4),
            getOffset(-3, 1, 5),
            getOffset(3, 1, 5),
            getOffset(-3, 1, 6),
            getOffset(3, 1, 6),
            getOffset(0, 1, 7),
            getOffset(1, 1, 7),
            getOffset(2, 1, 7),
            getOffset(-1, 1, 7),
            getOffset(-2, 1, 7),
            getOffset(0, 2, 1),
            getOffset(1, 2, 1),
            getOffset(2, 2, 1),
            getOffset(-1, 2, 1),
            getOffset(-2, 2, 1),
            getOffset(-3, 2, 2),
            getOffset(3, 2, 2),
            getOffset(-3, 2, 3),
            getOffset(3, 2, 3),
            getOffset(-3, 2, 4),
            getOffset(3, 2, 4),
            getOffset(-3, 2, 5),
            getOffset(3, 2, 5),
            getOffset(-3, 2, 6),
            getOffset(3, 2, 6),
            getOffset(0, 2, 7),
            getOffset(1, 2, 7),
            getOffset(2, 2, 7),
            getOffset(-1, 2, 7),
            getOffset(-2, 2, 7),
            getOffset(0, 5, 1),
            getOffset(1, 5, 1),
            getOffset(2, 5, 1),
            getOffset(-1, 5, 1),
            getOffset(-2, 5, 1),
            getOffset(-3, 5, 2),
            getOffset(3, 5, 2),
            getOffset(-3, 5, 3),
            getOffset(3, 5, 3),
            getOffset(-3, 5, 4),
            getOffset(3, 5, 4),
            getOffset(-3, 5, 5),
            getOffset(3, 5, 5),
            getOffset(-3, 5, 6),
            getOffset(3, 5, 6),
            getOffset(0, 5, 7),
            getOffset(1, 5, 7),
            getOffset(2, 5, 7),
            getOffset(-1, 5, 7),
            getOffset(-2, 5, 7),
            getOffset(0, 4, 1),
            getOffset(1, 4, 1),
            getOffset(2, 4, 1),
            getOffset(-1, 4, 1),
            getOffset(-2, 4, 1),
            getOffset(-3, 4, 2),
            getOffset(3, 4, 2),
            getOffset(-3, 4, 3),
            getOffset(3, 4, 3),
            getOffset(-3, 4, 4),
            getOffset(3, 4, 4),
            getOffset(-3, 4, 5),
            getOffset(3, 4, 5),
            getOffset(-3, 4, 6),
            getOffset(3, 4, 6),
            getOffset(0, 4, 7),
            getOffset(1, 4, 7),
            getOffset(2, 4, 7),
            getOffset(-1, 4, 7),
            getOffset(-2, 4, 7));

    private Set<BlockPos> saveOffsets() {
        var offsets = new HashSet<BlockPos>();
        Direction up = RelativeDirection.UP.getRelative(getFrontFacing(), getUpwardsFacing(), isFlipped());
        Direction back = getFrontFacing().getOpposite();
        Direction clockWise;
        Direction counterClockWise;
        if (up == Direction.UP || up == Direction.DOWN) {
            clockWise = getFrontFacing().getClockWise();
            counterClockWise = getFrontFacing().getCounterClockWise();
        } else {
            clockWise = Direction.UP;
            counterClockWise = Direction.DOWN;

        }

        BlockPos pos = getPos();
        BlockPos center = pos.relative(up);
        var capacity = getTank().getTankCapacity(0);
        var amount = getTank().getFluidInTank(0).getAmount();
        var height = 12 * (double) amount / capacity;

        for (int i = 0; i < 5; i++) {
            center = center.relative(back);
            offsets.add(center.subtract(pos));
            offsets.add(center.relative(clockWise).subtract(pos));
            offsets.add(center.relative(counterClockWise).subtract(pos));
        }
        return offsets;
    }
}
