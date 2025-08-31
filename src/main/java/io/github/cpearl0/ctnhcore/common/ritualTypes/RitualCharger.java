package io.github.cpearl0.ctnhcore.common.ritualTypes;

import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.common.machine.electric.BatteryBufferMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import wayoftime.bloodmagic.ritual.*;
import wayoftime.bloodmagic.util.Utils;

import java.util.List;
import java.util.function.Consumer;

@RitualRegister("charger")
public class RitualCharger extends Ritual {
    long energyGen = 204800;
    int syphonCost = 25600;
    public RitualCharger() {
        super("ritualCharger", 0, 1500000, "ritual.ctnh.chargerRitual");
        this.addBlockRange("charge", new AreaDescriptor.Rectangle(new BlockPos(0, 1, 0), 1));
        this.setMaximumVolumeAndDistanceOfRange("charge", 1, 2, 2);
    }

    @Override
    public void performRitual(IMasterRitualStone masterRitualStone) {
        Level world = masterRitualStone.getWorldObj();
        int currentEssence = masterRitualStone.getOwnerNetwork().getCurrentEssence();
        BlockPos pos = masterRitualStone.getMasterBlockPos();
        int effect = 100;
        if (currentEssence < this.getRefreshCost()) {
            masterRitualStone.getOwnerNetwork().causeNausea();
        }
        else {
            AreaDescriptor chargerRange = masterRitualStone.getBlockRange("charge");
            List<BlockPos> chargerList = chargerRange.getContainedPositions(pos);
            if (chargerList.size() > 0) {
                MetaMachine machine = MetaMachine.getMachine(world, chargerList.get(0));
                if (machine instanceof BatteryBufferMachine batteryBufferMachine) {
                    var voltage = batteryBufferMachine.energyContainer.getInputVoltage();
                    long amp = energyGen / voltage;
                    var actualAmp = batteryBufferMachine.energyContainer.acceptEnergyFromNetwork(null, voltage, amp);
                    if (actualAmp < amp) {
                        effect = (int) (effect * actualAmp / amp);
                    }
                }
            }
            masterRitualStone.getOwnerNetwork().syphon(masterRitualStone.ticket(this.getRefreshCost() * effect / 100));
        }
    }

    @Override
    public int getRefreshCost() {
        return syphonCost;
    }

    @Override
    public int getRefreshTime() {
        return 25;
    }

    @Override
    public void gatherComponents(Consumer<RitualComponent> consumer) {
        this.addCornerRunes(consumer, 1, 0, EnumRuneType.AIR);
        this.addCornerRunes(consumer, 2, -1, EnumRuneType.FIRE);
        this.addCornerRunes(consumer, 3, 1, EnumRuneType.WATER);
        this.addParallelRunes(consumer, 2, 1, EnumRuneType.EARTH);
        this.addParallelRunes(consumer, 2, -1, EnumRuneType.EARTH);
        this.addOffsetRunes(consumer, 2, 1, -1, EnumRuneType.EARTH);
        this.addOffsetRunes(consumer, 2, 3, 0, EnumRuneType.WATER);
        this.addOffsetRunes(consumer, 2, 3, 2, EnumRuneType.WATER);
    }

    @Override
    public Ritual getNewCopy() {
        return new RitualCharger();
    }
}
