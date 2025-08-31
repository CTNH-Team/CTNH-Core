package io.github.cpearl0.ctnhcore.common.machine.multiblock.part;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableEnergyContainer;
import com.gregtechceu.gtceu.common.machine.multiblock.part.EnergyHatchPartMachine;
import com.lowdragmc.lowdraglib.syncdata.ISubscription;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;

import java.util.concurrent.ThreadLocalRandom;

public class NeutronAcceleratorMachine extends EnergyHatchPartMachine {
    private final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(NeutronAcceleratorMachine.class,
    EnergyHatchPartMachine.MANAGED_FIELD_HOLDER);
    private ISubscription powerListener = null;
    private TickableSubscription powerSubs = null;
    public NeutronAcceleratorMachine(IMachineBlockEntity holder, int tier) {
        super(holder, tier, IO.IN, 1);
    }
    //////////////////////////////////////
    //*****     Initialization    ******//
    //////////////////////////////////////

    @Override
    protected NotifiableEnergyContainer createEnergyContainer(Object... args) {
        var maxCapacity = GTValues.V[tier] * 72L * amperage;
        var container = NotifiableEnergyContainer.receiverContainer(
                this,
                maxCapacity,
                GTValues.V[tier],
                amperage
        );
        container.setSideInputCondition(direction -> direction == this.getFrontFacing() && this.isWorkingEnabled());
        container.setCapabilityValidator(direction -> direction == null || direction == this.getFrontFacing());
        return container;
    }

    public long consumeEnergy(){
        if (this.isWorkingEnabled() && this.energyContainer.getEnergyStored() > 0) {
            return Math.abs(this.energyContainer.changeEnergy(-getMaxEUConsume())) * (10 + ThreadLocalRandom.current().nextInt(11));
        } else {
            return 0;
        }
    }

    @Override
    public void onLoad() {
        super.onLoad();
        powerListener = energyContainer.addChangedListener(this::updateSubscription);
        updateSubscription();
    }

    @Override
    public void onUnload() {
        super.onUnload();
        if (powerListener != null) {
            powerListener.unsubscribe();
            powerListener = null;
        }
    }

    private void updateSubscription() {
        if (energyContainer.getEnergyStored() > 0) {
            powerSubs = subscribeServerTick(this::energyChanged);
        } else if (powerSubs != null) {
            powerSubs.unsubscribe();
            powerSubs = null;
        }
    }

    private void energyChanged() {
        if (energyContainer.getEnergyStored() > 0 && !isWorkingEnabled()) {
            energyContainer.changeEnergy(-GTValues.V[tier]);
        }
    }

    //////////////////////////////////////
    //**********     GUI     ***********//
    //////////////////////////////////////


    @Override
    public boolean shouldOpenUI(Player player, InteractionHand hand, BlockHitResult hit) {
        return true;
    }

    //////////////////////////////////////
    //**********     Data     **********//
    //////////////////////////////////////

    private long getMaxEUConsume() {
        return Math.round(GTValues.V[tier] * 0.8);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

}
