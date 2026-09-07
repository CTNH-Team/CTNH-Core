package io.github.cpearl0.ctnhcore.common.machine.trait.providable_net;

class ProviderInfo {

    ProvidableNetTrait self;
    ProviderInfo next = null;
    ProviderInfo prev = null;

    ProviderInfo(ProvidableNetTrait self) {
        this.self = self;
    }

    boolean hasPrev() {
        return prev != null;
    }

    boolean hasNext() {
        return next != null;
    }

    void removeFromNet(ProvidableNetInfo netInfo) {
        if (this == netInfo.chainHead) netInfo.chainHead = next;
        if (this == netInfo.chainTail) netInfo.chainTail = prev;
        if (next != null)
            next.prev = prev;
        if (prev != null)
            prev.next = next;

        this.next = null;
        this.prev = null;
    }

    void insertIn(ProviderInfo node) {
        this.next = node;
        this.prev = node.prev;

        node.prev.next = this;
        node.prev = this;
    }

    void appendIn(ProviderInfo node) {
        this.prev = node;
        node.next = this;
    }
}
