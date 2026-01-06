package io.github.cpearl0.ctnhcore.mixin.gtceu;

import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import io.github.cpearl0.ctnhcore.utils.IAllowSameContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = NotifiableFluidTank.class, remap = false)
public abstract class NotifiableFluidTankMixin implements IAllowSameContainer {

    @Shadow
    @Persisted
    protected boolean allowSameFluids;

    @Shadow
    public abstract void onContentsChanged();

    @Override
    public boolean isAllowSame() {
        return allowSameFluids;
    }

    @Override
    public void setAllowSame(boolean b) {
        allowSameFluids = b;
        onContentsChanged();
    }
}
