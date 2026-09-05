package io.github.cpearl0.ctnhcore.common.machine.trait.providable_net;

import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.MachineTrait;
import com.gregtechceu.gtceu.api.machine.trait.feature.IMultiblockMachineTrait;

import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;
import java.util.function.Supplier;

/** Runtime ownership of a shared resource network attached to a multiblock machine. */
public final class ProvidableNetTrait extends MachineTrait implements IMultiblockMachineTrait {

    private final Supplier<List<ProvidableNetTrait>> neighbours;
    private final BooleanSupplier canProvide;
    private final IntSupplier storageSupplier;
    private final IntUnaryOperator consume;
    @Nullable
    private ProvidableNetInfo netInfo;
    @Nullable
    private ProviderInfo providerData;

    public ProvidableNetTrait(MetaMachine machine, Supplier<List<ProvidableNetTrait>> neighbours,
                              BooleanSupplier canProvide, IntSupplier storageSupplier, IntUnaryOperator consume) {
        super(machine);
        this.neighbours = neighbours;
        this.canProvide = canProvide;
        this.storageSupplier = storageSupplier;
        this.consume = consume;
    }

    @Override
    public void onMachineLoad() {
        scheduleJoin();
    }

    @Override
    public void onMachineUnload() {
        invalidate();
    }

    @Override
    public void onStructureFormed() {
        scheduleJoin();
    }

    @Override
    public void onStructureInvalid() {
        invalidate();
    }

    @Override
    public void onPartUnload() {
        invalidate();
    }

    private void scheduleJoin() {
        if (getMultiMachine().isFormed() && getMachine().getLevel() instanceof ServerLevel serverLevel) {
            serverLevel.getServer().tell(new TickTask(0, this::join));
        }
    }

    public boolean ensureNetInfo() {
        if (netInfo == null) return false;
        if (netInfo.dirty) {
            netInfo = null;
            join();
        }
        return true;
    }

    @Nullable
    ProvidableNetInfo getNetInfo() {
        return netInfo == null ? null : (netInfo = netInfo.getFather());
    }

    public void join() {
        if (netInfo != null || getMachine().getLevel() == null) return;
        netInfo = new ProvidableNetInfo(getMachine().getLevel());
        if (canProvide()) {
            providerData = new ProviderInfo(this);
            netInfo.chainHead = providerData;
            netInfo.chainTail = providerData;
        }
        for (var neighbour : neighbours.get()) {
            var neighbourNet = neighbour.getNetInfo();
            if (neighbourNet != null && neighbourNet.isAlive()) {
                netInfo.merge(neighbourNet);
            }
        }
    }

    public void invalidate() {
        if (netInfo == null) return;
        netInfo.markDirty();
        if (providerData != null) providerData.removeFromNet(netInfo);
        providerData = null;
        netInfo = null;
    }

    public boolean checkAndConsume(int amount) {
        var net = getNetInfo();
        if (net == null || net.storage < amount) return false;
        net.storage -= amount;
        return true;
    }

    public int getNetSize() {
        var net = getNetInfo();
        return net == null ? 0 : net.netSize;
    }

    public long getDeadTime() {
        var net = getNetInfo();
        return net == null ? -1 : net.deadTime;
    }

    int getStorage() {
        return storageSupplier.getAsInt();
    }

    int consume(int amount) {
        return consume.applyAsInt(amount);
    }

    private boolean canProvide() {
        return canProvide.getAsBoolean();
    }
}
