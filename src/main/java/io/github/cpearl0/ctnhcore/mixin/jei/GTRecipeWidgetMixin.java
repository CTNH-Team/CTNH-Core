package io.github.cpearl0.ctnhcore.mixin.jei;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.integration.xei.widgets.GTRecipeWidget;
import io.github.cpearl0.ctnhcore.utils.VoltageBorderWidget;
import net.minecraft.ChatFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GTRecipeWidget.class, remap = false)
public abstract class GTRecipeWidgetMixin {
    @Accessor("tier")
    public abstract int getTier();

    @Accessor("recipe")
    public abstract GTRecipe getRecipe();

    @Accessor("xOffset")
    public abstract int getXOffset();


    @Inject(method = "initializeRecipeTextWidget", at = @At("TAIL"))
    private void injectVoltageBorder(CallbackInfo ci) {
        GTRecipeWidget self = (GTRecipeWidget) (Object) this;
        if(RecipeHelper.getRealEUt(getRecipe()).voltage() > 0 &&
                self.widgets.stream().noneMatch(w -> w instanceof VoltageBorderWidget)
        ){
             //获取颜色（ARGB）
            int color = cTNH_Core$getColorFromVNF(GTValues.VNF[getTier()]);

             //添加新的边框渲染 widget（放在最底层以确保不会遮挡其他元素）
            self.widgets.add(new VoltageBorderWidget(
                    -getXOffset(), 0, self.getSize().width, self.getSize().height, color
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

}



