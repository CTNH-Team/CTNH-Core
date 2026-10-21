/*
 * Ported from BetterPonder (https://github.com/minecraftgive/BetterPonder_MOD)
 * Original author: Nothing · LGPL-3.0
 */
package io.github.cpearl0.ctnhcore.mixin.ponder;

import net.createmod.catnip.gui.ScreenOpener;
import net.createmod.ponder.foundation.ui.PonderUI;
import net.minecraft.client.Minecraft;

import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PonderUI.class, remap = false)
public class PonderUIPreviousScreenMixin {

    @Unique
    private boolean ctnhcore$backKeyHeld;

    @Inject(method = "tick", at = @At("HEAD"), remap = false)
    private void ctnhcore$handleBackKey(CallbackInfo ci) {
        boolean held = GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(),
                GLFW.GLFW_KEY_B) == GLFW.GLFW_PRESS;
        if (held && !ctnhcore$backKeyHeld) {
            ScreenOpener.openPreviousScreen((PonderUI) (Object) this, null);
        }
        ctnhcore$backKeyHeld = held;
    }
}
