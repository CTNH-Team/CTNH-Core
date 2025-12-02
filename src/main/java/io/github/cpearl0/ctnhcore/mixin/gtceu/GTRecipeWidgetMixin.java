package io.github.cpearl0.ctnhcore.mixin.gtceu;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.integration.xei.widgets.GTRecipeWidget;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.cpearl0.ctnhcore.utils.VoltageBorderWidget;
import net.minecraft.ChatFormatting;
import org.apache.commons.lang3.mutable.MutableInt;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.gregtechceu.gtceu.integration.xei.widgets.GTRecipeWidget.LINE_HEIGHT;

@Mixin(value = GTRecipeWidget.class, remap = false)
public class GTRecipeWidgetMixin {

    @Final
    @Shadow
    private GTRecipe recipe;

    @Shadow
    private int tier;

    @Final
    @Shadow
    private int xOffset;

    @Inject(method = "initializeRecipeTextWidget", at = @At("TAIL"))
    private void injectVoltageBorder(CallbackInfo ci) {
        GTRecipeWidget self = (GTRecipeWidget) (Object) this;
        if(RecipeHelper.getRealEUt(recipe).voltage() > 0 &&
                self.widgets.stream().noneMatch(w -> w instanceof VoltageBorderWidget)
        ){
            //获取颜色（ARGB）
            int color = cTNH_Core$getColorFromVNF(GTValues.VNF[tier]);

            //添加新的边框渲染 widget（放在最底层以确保不会遮挡其他元素）
            self.widgets.add(new VoltageBorderWidget(
                    -xOffset, 0, self.getSize().width, self.getSize().height, color
            ));
        }

    }

    @Unique
    private static int cTNH_Core$getColorFromVNF(String vnfText) {
        for (ChatFormatting format : ChatFormatting.values()) {
            // toString() 返回形如 "§c"，indexOf 检测是否包含
            if (vnfText.contains(format.toString()) && format.isColor()) {
                Integer rgb = format.getColor();
                if (rgb != null) {
                    return 0xFF000000 | rgb; // 加上不透明的 alpha 通道
                }
            }
        }
        return 0xFFFFFFFF; // 默认白色
    }

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
