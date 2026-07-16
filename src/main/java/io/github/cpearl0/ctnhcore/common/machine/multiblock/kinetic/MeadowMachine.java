package io.github.cpearl0.ctnhcore.common.machine.multiblock.kinetic;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;

import com.mo_guang.ctpp.common.machine.multiblock.KineticWorkableMultiblockMachine;
import com.moguang.ctnhbio.api.machine.trait.NotifiableEntityContainer;

public class MeadowMachine extends KineticWorkableMultiblockMachine {

    public NotifiableEntityContainer entityContainer;

    public MeadowMachine(IMachineBlockEntity holder) {
        super(holder);
        entityContainer = new NotifiableEntityContainer(this, getAABB(), IO.IN);
    }

    public AABB getAABB() {
        final Direction b = getFrontFacing().getOpposite();
        final Direction l = b.getCounterClockWise();
        final Direction u = Direction.UP;

        return new AABB(
                getPos().relative(b, 0).relative(l, 5).relative(u, 0),
                getPos().relative(b, 10).relative(l, -5).relative(u, 6));
    }
}
