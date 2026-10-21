/*
 * Ported from BetterPonder (https://github.com/minecraftgive/BetterPonder_MOD)
 * Original author: Nothing · LGPL-3.0
 */
package io.github.cpearl0.ctnhcore.mixin.ponder;

import net.createmod.ponder.foundation.PonderScene;
import net.createmod.ponder.foundation.ui.PonderUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;

import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PonderUI.class, remap = false)
public class PonderUISpeederMixin {

    @Unique
    private static final int CTNHCORE_MAX_SPEED_LEVEL = 10;

    @Unique
    private static final int CTNHCORE_TEXT_IDLE = 0x888888;

    @Unique
    private static final int CTNHCORE_TEXT_ACTIVE = 0x00FF00;

    @Unique
    private static final int CTNHCORE_HINT_COLOR = 0xAAAAAA;

    @Unique
    private static final String CTNHCORE_SLOWER_HINT = "X-";

    @Unique
    private static final String CTNHCORE_FASTER_HINT = "C+";

    @Unique
    private static final int CTNHCORE_HINT_GAP = 10;

    @Unique
    private int ctnhcore$speedLevel = 1;

    @Unique
    private boolean ctnhcore$resetKeyHeld;

    @Unique
    private boolean ctnhcore$slowerKeyHeld;

    @Unique
    private boolean ctnhcore$fasterKeyHeld;

    @Inject(method = "tick", at = @At("HEAD"), remap = false)
    private void ctnhcore$handleSpeedKeys(CallbackInfo ci) {
        ctnhcore$resetKeyHeld = ctnhcore$pressed(GLFW.GLFW_KEY_Z, ctnhcore$resetKeyHeld, 1);
        ctnhcore$slowerKeyHeld = ctnhcore$pressed(GLFW.GLFW_KEY_X, ctnhcore$slowerKeyHeld, ctnhcore$speedLevel - 1);
        ctnhcore$fasterKeyHeld = ctnhcore$pressed(GLFW.GLFW_KEY_C, ctnhcore$fasterKeyHeld, ctnhcore$speedLevel + 1);
    }

    @Inject(
            method = "tick",
            at = @At(
                     value = "INVOKE",
                     target = "Lnet/createmod/ponder/foundation/PonderScene;tick()V",
                     ordinal = 0),
            remap = false)
    private void ctnhcore$speedUpActiveScene(CallbackInfo ci) {
        int extraTicks = ctnhcore$speedLevel - 1;
        if (extraTicks <= 0) {
            return;
        }
        PonderScene scene = ((PonderUI) (Object) this).getActiveScene();
        for (int i = 0; i < extraTicks; i++) {
            scene.tick();
        }
    }

    @Inject(method = "renderWidgets", at = @At("TAIL"), remap = false)
    private void ctnhcore$renderSpeedIndicator(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks,
                                               CallbackInfo ci) {
        Minecraft minecraft = Minecraft.getInstance();
        Font font = minecraft.font;
        String levelText = ctnhcore$speedLevel + "x";
        int centerX = minecraft.getWindow().getGuiScaledWidth() / 2;
        int y = minecraft.getWindow().getGuiScaledHeight() - 21 - 60;
        int color = ctnhcore$speedLevel == 1 ? CTNHCORE_TEXT_IDLE : CTNHCORE_TEXT_ACTIVE;
        int levelHalf = font.width(levelText) / 2;

        graphics.pose().pushPose();
        graphics.pose().translate(0, 0, 500);
        graphics.drawCenteredString(font, levelText, centerX, y, color);
        graphics.drawString(font, CTNHCORE_SLOWER_HINT,
                centerX - levelHalf - CTNHCORE_HINT_GAP - font.width(CTNHCORE_SLOWER_HINT), y,
                CTNHCORE_HINT_COLOR, false);
        graphics.drawString(font, CTNHCORE_FASTER_HINT, centerX + levelHalf + CTNHCORE_HINT_GAP, y,
                CTNHCORE_HINT_COLOR, false);
        graphics.pose().popPose();
    }

    @Unique
    private boolean ctnhcore$pressed(int key, boolean wasHeld, int targetLevel) {
        boolean held = ctnhcore$isKeyDown(key);
        if (held && !wasHeld) {
            ctnhcore$speedLevel = Mth.clamp(targetLevel, 1, CTNHCORE_MAX_SPEED_LEVEL);
        }
        return held;
    }

    @Unique
    private static boolean ctnhcore$isKeyDown(int key) {
        long window = Minecraft.getInstance().getWindow().getWindow();
        return GLFW.glfwGetKey(window, key) == GLFW.GLFW_PRESS;
    }
}
