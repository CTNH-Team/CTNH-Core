package io.github.cpearl0.ctnhcore.mixin.gtceu;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TieredEnergyMachine;
import com.gregtechceu.gtceu.api.machine.WorkableTieredMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.hugehatch.HugeItemBusPartMachine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = WorkableTieredMachine.class, remap = false)
public abstract class WorkableTieredMachineMixin extends TieredEnergyMachine {

    public WorkableTieredMachineMixin(IMachineBlockEntity holder, int tier, Object... args) {
        super(holder, tier, args);
    }

    @Shadow
    public GTRecipeType getRecipeType() {
        return null;
    }

    @Inject(method = "createImportItemHandler", at = @At("HEAD"), cancellable = true)
    protected void createImportItemHandler(Object[] args, CallbackInfoReturnable<NotifiableItemStackHandler> cir) {
        cir.setReturnValue(
                new NotifiableItemStackHandler(this,
                        getRecipeType().getMaxInputs(ItemRecipeCapability.CAP),
                        IO.IN,
                        IO.IN,
                        i -> new HugeItemBusPartMachine.HugeItemStackHandler(i, ctnhcore$getSlotMultiplier(getTier()))
        ));
    }

    @Inject(method = "createExportItemHandler", at = @At("HEAD"), cancellable = true)
    protected void createExportItemHandler(Object[] args, CallbackInfoReturnable<NotifiableItemStackHandler> cir) {
        cir.setReturnValue(
                new NotifiableItemStackHandler(this,
                        getRecipeType().getMaxInputs(ItemRecipeCapability.CAP),
                        IO.OUT,
                        IO.OUT,
                        i -> new HugeItemBusPartMachine.HugeItemStackHandler(i, ctnhcore$getSlotMultiplier(getTier()))
                ));
    }

    @Unique
    private static int ctnhcore$getSlotMultiplier(int tier){
        if(tier == 0) return 1;
        return 1 <<  (2 * (tier -1));
    }
}
