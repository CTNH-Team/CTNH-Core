package io.github.cpearl0.ctnhcore.common.ritualTypes;

import wayoftime.bloodmagic.ritual.*;

import java.util.function.Consumer;

@RitualRegister("charger")
public class RitualCharger extends Ritual {
    public RitualCharger(String name, int crystalLevel, int activationCost, RitualRenderer renderer, String unlocalizedName) {
        super(name, crystalLevel, activationCost, renderer, unlocalizedName);
    }

    @Override
    public void performRitual(IMasterRitualStone iMasterRitualStone) {

    }

    @Override
    public int getRefreshCost() {
        return 0;
    }

    @Override
    public void gatherComponents(Consumer<RitualComponent> consumer) {

    }

    @Override
    public Ritual getNewCopy() {
        return null;
    }
}
