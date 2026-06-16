package io.github.cpearl0.ctnhcore.mixin.emi;

import io.github.cpearl0.ctnhcore.utils.emi.collapsible.CTNHCollapsibleGroups;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStackInteraction;
import dev.emi.emi.config.SidebarType;
import dev.emi.emi.runtime.EmiDrawContext;
import dev.emi.emi.runtime.EmiSidebars;
import dev.emi.emi.screen.EmiScreenBase;
import dev.emi.emi.screen.EmiScreenManager;
import dev.emi.emi.screen.widget.EmiSearchWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

/**
 * 处理 EMI 折叠组的输入：单组切换、全部切换按钮和列表重建。
 */
@Mixin(value = EmiScreenManager.class, remap = false)
public class EmiScreenManagerInputMixin {

    /** EMI 原生搜索框实例，用于把 G 按钮定位到搜索框右侧。 */
    @Shadow
    public static EmiSearchWidget search;

    /** G 按钮边长，和 EMI 侧栏单格尺寸保持接近。 */
    @Unique
    private static final int TOGGLE_BUTTON_SIZE = 16;

    /** G 按钮与搜索框之间的水平间距。 */
    @Unique
    private static final int TOGGLE_BUTTON_GAP = 4;

    /** G 按钮左上角 X 坐标；-1 表示当前没有可点击按钮。 */
    @Unique
    private static int ctnhcore$toggleBtnX = -1;

    /** G 按钮左上角 Y 坐标；-1 表示当前没有可点击按钮。 */
    @Unique
    private static int ctnhcore$toggleBtnY = -1;

    /** 鼠标当前是否悬停在 G 按钮上，用于按钮高亮和 tooltip。 */
    @Unique
    private static boolean ctnhcore$hoveredToggleBtn = false;

    /** 处理 G 按钮点击，以及 Alt + 左键切换单个分组。 */
    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private static void ctnhcore$handleMouseClicked(double mouseX, double mouseY, int button,
                                                    CallbackInfoReturnable<Boolean> cir) {
        if (CTNHCollapsibleGroups.needsRebuild()) return;
        if (!CTNHCollapsibleGroups.hasGroups()) return;

        int mx = (int) mouseX;
        int my = (int) mouseY;

        if (ctnhcore$toggleBtnX >= 0 && ctnhcore$toggleBtnY >= 0 && mx >= ctnhcore$toggleBtnX &&
                mx < ctnhcore$toggleBtnX + TOGGLE_BUTTON_SIZE && my >= ctnhcore$toggleBtnY &&
                my < ctnhcore$toggleBtnY + TOGGLE_BUTTON_SIZE) {
            if (button == 0) {
                CTNHCollapsibleGroups.toggleAll(false);
            } else if (button == 1) {
                CTNHCollapsibleGroups.toggleAll(true);
            }
            EmiScreenManager.repopulatePanels(SidebarType.INDEX);
            cir.setReturnValue(true);
            return;
        }

        if (button == 0 && Screen.hasAltDown()) {
            EmiStackInteraction interaction = EmiScreenManager.getHoveredStack(mx, my, false);
            EmiIngredient hovered = interaction.getStack();
            if (!hovered.isEmpty()) {
                if (CTNHCollapsibleGroups.toggleGroup(hovered)) {
                    EmiScreenManager.repopulatePanels(SidebarType.INDEX);
                    cir.setReturnValue(true);
                }
            }
        }
    }

    /** 在搜索框右侧绘制 G 按钮：左键智能切换，右键全部折叠。 */
    @Inject(method = "renderWidgets", at = @At("TAIL"))
    private static void ctnhcore$renderToggleButton(EmiDrawContext context, int mouseX, int mouseY,
                                                    float delta, EmiScreenBase base,
                                                    CallbackInfo ci) {
        if (CTNHCollapsibleGroups.needsRebuild()) return;
        if (!CTNHCollapsibleGroups.hasGroups()) {
            ctnhcore$toggleBtnX = -1;
            ctnhcore$toggleBtnY = -1;
            ctnhcore$hoveredToggleBtn = false;
            return;
        }

        if (base == null || search == null) return;
        ctnhcore$toggleBtnX = search.getX() + search.getWidth() + TOGGLE_BUTTON_GAP;
        ctnhcore$toggleBtnY = search.getY();

        int x = ctnhcore$toggleBtnX;
        int y = ctnhcore$toggleBtnY;

        GuiGraphics graphics = context.raw();

        ctnhcore$hoveredToggleBtn = mouseX >= x && mouseX < x + TOGGLE_BUTTON_SIZE && mouseY >= y &&
                mouseY < y + TOGGLE_BUTTON_SIZE;

        int bgColor = ctnhcore$hoveredToggleBtn ? 0xFF444444 : 0xFF333333;
        graphics.fill(x, y, x + TOGGLE_BUTTON_SIZE, y + TOGGLE_BUTTON_SIZE, bgColor);

        int borderColor = ctnhcore$hoveredToggleBtn ? 0xFF888888 : 0xFF555555;
        graphics.fill(x, y, x + TOGGLE_BUTTON_SIZE, y + 1, borderColor);
        graphics.fill(x, y + TOGGLE_BUTTON_SIZE - 1, x + TOGGLE_BUTTON_SIZE, y + TOGGLE_BUTTON_SIZE, borderColor);
        graphics.fill(x, y, x + 1, y + TOGGLE_BUTTON_SIZE, borderColor);
        graphics.fill(x + TOGGLE_BUTTON_SIZE - 1, y, x + TOGGLE_BUTTON_SIZE, y + TOGGLE_BUTTON_SIZE, borderColor);

        int collapsedCount = CTNHCollapsibleGroups.collapsedGroupCount();
        int textColor = collapsedCount > 0 ? 0xFF88FF88 : 0xFF888888;
        graphics.drawString(Minecraft.getInstance().font, "G", x + 4, y + 4, textColor, false);

        if (ctnhcore$hoveredToggleBtn) {
            int totalCount = CTNHCollapsibleGroups.totalGroupCount();
            if (collapsedCount > 0) {
                graphics.renderComponentTooltip(Minecraft.getInstance().font,
                        List.of(
                                Component.translatable("ctnhcore.emi.collapsible.button.expand_all", collapsedCount),
                                Component.translatable("ctnhcore.emi.collapsible.button.collapse_all.right_click")),
                        x, y + TOGGLE_BUTTON_SIZE + 4);
            } else {
                graphics.renderComponentTooltip(Minecraft.getInstance().font,
                        List.of(
                                Component.translatable("ctnhcore.emi.collapsible.button.collapse_all", totalCount),
                                Component.translatable("ctnhcore.emi.collapsible.button.collapse_all.right_click")),
                        x, y + TOGGLE_BUTTON_SIZE + 4);
            }
        }
    }

    /** EMI 刷新搜索来源时，用 INDEX 完整列表重建分组。 */
    @Inject(method = "getSearchSource", at = @At("RETURN"))
    private static void ctnhcore$rebuildOnSearch(CallbackInfoReturnable<List<? extends EmiIngredient>> cir) {
        if (CTNHCollapsibleGroups.needsRebuild()) {
            List<? extends EmiIngredient> source = EmiSidebars.getStacks(SidebarType.INDEX);
            if (source != null && !source.isEmpty()) {
                CTNHCollapsibleGroups.rebuild(source);
            }
        }
    }

    /** EMI 开关或重载后，下一次搜索刷新时重新扫描列表。 */
    @Inject(method = "toggleVisibility", at = @At("HEAD"))
    private static void ctnhcore$markDirtyOnToggle(boolean notify, CallbackInfo ci) {
        CTNHCollapsibleGroups.markDirty();
    }
}
