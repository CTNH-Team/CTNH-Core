package io.github.cpearl0.ctnhcore.mixin.gtceu;

import com.gregtechceu.gtceu.integration.xei.widgets.GTRecipeWidget;
import com.llamalad7.mixinextras.sugar.Local;
import org.apache.commons.lang3.mutable.MutableInt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import static com.gregtechceu.gtceu.integration.xei.widgets.GTRecipeWidget.LINE_HEIGHT;

@Mixin(value = GTRecipeWidget.class, remap = false)
public class GTRecipeWidgetMixin {
    @ModifyArg(method = "setRecipeWidget",
            at = @At(value = "INVOKE",
                    target = "Lcom/lowdragmc/lowdraglib/gui/widget/LabelWidget;<init>(IILjava/lang/String;)V"
            ),
            index = 1
    )
    private int setRecipeWidget(int y, @Local(name = "yOff") MutableInt yOff) {
        return yOff.addAndGet(LINE_HEIGHT);
    }
}
