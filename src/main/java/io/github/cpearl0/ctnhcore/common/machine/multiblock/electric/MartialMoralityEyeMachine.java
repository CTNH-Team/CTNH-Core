package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import tech.vixhentx.mcmod.ctnhlib.utils.MachineUtils;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;

public class MartialMoralityEyeMachine extends WorkableElectricMultiblockMachine {

    public MartialMoralityEyeMachine(IMachineBlockEntity holder) {
        super(holder);
    }

    @Override
    public boolean onWorking() {
        var center = MachineUtils.getOffset(this, 0, 0, 16);
        var entities = getLevel().getEntities(null,
                AABB.of(BoundingBox.fromCorners(center.offset(-2, -2, -2), center.offset(2, 2, 2))));
        entities.forEach(Entity::kill);
        return super.onWorking();
    }
}
