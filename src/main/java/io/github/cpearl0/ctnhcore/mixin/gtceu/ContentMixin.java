package io.github.cpearl0.ctnhcore.mixin.gtceu;

import com.gregtechceu.gtceu.api.recipe.content.Content;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = Content.class, remap = false)
public class ContentMixin {
//    @ModifyArg(
//            method = "drawChance",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)I"),
//            index = 2
//    )
//    public int modifyX(int x){
//        return x-10;
//    }

    @ModifyArg(
            method = "drawChance",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)I"),
            index = 3
    )
    public int modifyY(int y){
        return y-5;
    }
}
