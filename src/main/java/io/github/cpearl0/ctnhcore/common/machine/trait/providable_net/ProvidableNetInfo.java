package io.github.cpearl0.ctnhcore.common.machine.trait.providable_net;

import io.github.cpearl0.ctnhcore.event.ProvidableNetEventHandler;

import net.minecraft.world.level.Level;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

@Getter
public class ProvidableNetInfo {

    int netSize = 1;
    int storage = 0;
    int storageCache = 0;
    boolean dirty = false;
    long deadTime = -1;
    @Nonnull
    Level level;
    ProvidableNetInfo father = this;
    ProviderInfo chainHead;
    ProviderInfo chainTail;

    public ProvidableNetInfo(@NotNull Level level) {
        this.level = level;
        ProvidableNetEventHandler.nets.add(this);
    }

    // 扫一遍provider,更新storage
    public void preTick() {
        storage = 0;
        ProviderInfo provider = chainHead;
        while (provider != null) {
            storage += provider.self.getStorage();
            provider = provider.next;
        }
        storageCache = storage;
    }

    // 对provider进行消耗
    public void postTick() {
        int toConsume = storageCache - storage;
        ProviderInfo provider = chainHead;
        List<ProviderInfo> failedList = new ArrayList<>();
        while (provider != null && toConsume > 0) {
            var ret = provider.self.consume(toConsume);
            assert ret >= 0;
            if (ret == toConsume) failedList.add(provider);
            toConsume = ret;
            provider = provider.next;
        }
        // ?????????
        assert toConsume == 0;
        for (var failed : failedList) {
            failed.removeFromNet(this);
            failed.appendIn(chainTail);
            chainTail = failed;
        }
    }

    void merge(ProvidableNetInfo other) {
        var netA = this.getFather();
        var netB = other.getFather();
        if (netA == netB) return;

        if (netA.netSize >= netB.netSize)
            mergeInner(netB, netA);
        else
            mergeInner(netA, netB);
    }

    byte hasChain() {
        return chainHead == null ? (byte) 0 : (byte) 1;
    }

    private void mergeInner(ProvidableNetInfo smaller, ProvidableNetInfo bigger) {
        smaller.father = bigger;
        bigger.netSize += smaller.netSize;

        switch (bigger.hasChain() << 1 | smaller.hasChain()) {
            case 0b01:
                bigger.chainHead = smaller.chainHead;
                bigger.chainTail = smaller.chainTail;
                break;
            case 0b11:
                smaller.chainHead.appendIn(bigger.chainTail);
                bigger.chainTail = smaller.chainTail;
                break;
        }
    }

    ProvidableNetInfo getFather() {
        if (father == this) return this;
        ProvidableNetEventHandler.nets.remove(this);
        return father = father.getFather();
    }

    void markDirty() {
        deadTime = level.getGameTime() + 6 * 20;  // 拆除机器后6秒后再触发更新
        ProvidableNetEventHandler.dirtyNets.add(this);
    }

    public void invalidate() {
        dirty = true;
        ProvidableNetEventHandler.nets.remove(this);
        ProvidableNetEventHandler.removingNets.add(this);
    }

    public boolean isAlive() {
        return !dirty;
    }
}
