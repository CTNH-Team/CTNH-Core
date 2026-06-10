package io.github.cpearl0.ctnhcore.mixin.emi;

import io.github.cpearl0.ctnhcore.utils.emi.collapsible.CTNHCollapsibleGroups;
import io.github.cpearl0.ctnhcore.utils.emi.collapsible.CTNHCollapsibleGroups.CollapsibleGroup;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
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

import java.util.ArrayList;
import java.util.List;

/**
 * 注入 EMI 的 {@link EmiScreenManager}，负责折叠组的输入与提示层逻辑。
 *
 * <p>
 * 本 mixin 不决定哪些物品属于同一组；分组数据由 {@link CTNHCollapsibleGroups} 管理。
 * 这里负责把玩家输入和 EMI 生命周期事件转发给分组管理器，包括：
 * </p>
 * <ul>
 * <li>Alt + 左键点击单个分组代表项时展开或折叠该组。</li>
 * <li>在搜索框旁绘制 G 按钮，用于批量展开或折叠所有分组。</li>
 * <li>在 EMI 搜索来源刷新时触发分组重建。</li>
 * <li>给折叠代表项 tooltip 追加分组名和操作提示。</li>
 * </ul>
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

    /**
     * 处理折叠组相关鼠标点击。
     *
     * <p>
     * 注入在 {@code mouseClicked} 开头，先于 EMI 原生点击逻辑执行。这样当玩家点击 G 按钮
     * 或 Alt + 左键点击分组项时，可以消费本次事件，避免 EMI 同时打开配方或执行其他默认动作。
     * </p>
     */
    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private static void ctnhcore$handleMouseClicked(double mouseX, double mouseY, int button,
                                                    CallbackInfoReturnable<Boolean> cir) {
        if (!CTNHCollapsibleGroups.isEnabled()) return;
        if (CTNHCollapsibleGroups.needsRebuild()) return;
        if (!CTNHCollapsibleGroups.hasGroups()) return;

        int mx = (int) mouseX;
        int my = (int) mouseY;

        // 先检查 G 按钮，避免按钮区域被误判为侧栏物品点击。
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

        // Alt + 左键点击 INDEX 侧栏中的分组项时，切换该项所属分组。
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

    /**
     * 在 EMI 小部件渲染结束后绘制 G 按钮。
     *
     * <p>
     * 按钮位于实时搜索框右侧。左键为智能展开/折叠全部：存在折叠组时展开全部，
     * 否则折叠全部；右键始终折叠全部。没有有效分组时会隐藏按钮并清空 hover 状态。
     * </p>
     */
    @Inject(method = "renderWidgets", at = @At("TAIL"))
    private static void ctnhcore$renderToggleButton(EmiDrawContext context, int mouseX, int mouseY,
                                                    float delta, EmiScreenBase base,
                                                    CallbackInfo ci) {
        if (!CTNHCollapsibleGroups.isEnabled()) return;
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

    /**
     * 在 EMI 获取搜索来源后触发折叠组重建。
     *
     * <p>
     * 这里故意使用 {@link EmiSidebars#getStacks(SidebarType)} 的 INDEX 完整来源，而不是搜索过滤后的
     * 返回值。这样分组成员来自完整 EMI 列表，搜索时再由投影逻辑只显示当前搜索结果中存在的成员。
     * </p>
     */
    @Inject(method = "getSearchSource", at = @At("RETURN"))
    private static void ctnhcore$rebuildOnSearch(CallbackInfoReturnable<List<? extends EmiIngredient>> cir) {
        if (!CTNHCollapsibleGroups.isEnabled()) return;
        if (CTNHCollapsibleGroups.needsRebuild()) {
            List<? extends EmiIngredient> source = EmiSidebars.getStacks(SidebarType.INDEX);
            if (source != null && !source.isEmpty()) {
                CTNHCollapsibleGroups.rebuild(source);
            }
        }
    }

    /**
     * 当 EMI 可见性切换时标记分组为脏。
     *
     * <p>
     * 可见性切换常伴随 EMI 重载、关闭或重新打开。标记 dirty 后，下次获取搜索来源时会重新扫描列表，
     * 避免继续使用旧的 ingredient 对象身份映射。
     * </p>
     */
    @Inject(method = "toggleVisibility", at = @At("HEAD"))
    private static void ctnhcore$markDirtyOnToggle(boolean notify, CallbackInfo ci) {
        CTNHCollapsibleGroups.markDirty();
    }

    /**
     * 包装 EMI 原生 tooltip，给折叠代表项追加分组信息。
     *
     * <p>
     * 只有当前 ingredient 是折叠代表项时才追加文本。展开后的普通成员不追加，避免 tooltip 噪音。
     * 追加内容包括分组显示名和 Alt + 左键展开/折叠提示。
     * </p>
     */
    @WrapOperation(method = "renderCurrentTooltip",
                   at = @At(value = "INVOKE",
                            target = "Ldev/emi/emi/api/stack/EmiIngredient;getTooltip()Ljava/util/List;"))
    private static List<ClientTooltipComponent> ctnhcore$wrapGroupTooltip(EmiIngredient instance,
                                                                          Operation<List<ClientTooltipComponent>> original) {
        List<ClientTooltipComponent> list = original.call(instance);
        if (CTNHCollapsibleGroups.needsRebuild() || !CTNHCollapsibleGroups.isEnabled()) return list;
        if (CTNHCollapsibleGroups.collapsedGroupCount() == 0) return list;

        CollapsibleGroup group = CTNHCollapsibleGroups.getGroup(instance);
        if (group != null && CTNHCollapsibleGroups.isCollapsedRepresentative(instance)) {
            List<ClientTooltipComponent> modified = new ArrayList<>(list.size() + 2);
            modified.addAll(list);
            modified.add(ClientTooltipComponent.create(
                    Component.translatable("ctnhcore.emi.collapsible.tooltip.group", group.displayName)
                            .getVisualOrderText()));
            modified.add(ClientTooltipComponent.create(
                    Component.translatable("ctnhcore.emi.collapsible.tooltip.toggle_hint").getVisualOrderText()));
            return modified;
        }
        return list;
    }
}
