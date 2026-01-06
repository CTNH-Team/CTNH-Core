package io.github.cpearl0.ctnhcore.mixin.gtceu;

import io.github.cpearl0.ctnhcore.common.gui.HugeSlotWidget;

import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.gui.widget.SlotWidget;

import com.lowdragmc.lowdraglib.gui.widget.Widget;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemRecipeCapability.class, remap = false)
public class ItemRecipeCapabilityMixin {

    @Inject(
            method = "createWidget",
            at = @At("HEAD"),
            cancellable = true)
    public void createHugeWidget(CallbackInfoReturnable<Widget> cir) {
        SlotWidget slot = new HugeSlotWidget();
        slot.initTemplate();
        cir.setReturnValue(slot);
    }
}
