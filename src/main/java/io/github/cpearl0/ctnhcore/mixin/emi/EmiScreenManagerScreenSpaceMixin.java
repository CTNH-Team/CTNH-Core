package io.github.cpearl0.ctnhcore.mixin.emi;

import io.github.cpearl0.ctnhcore.utils.emi.collapsible.CTNHCollapsibleGroups;
import io.github.cpearl0.ctnhcore.utils.emi.collapsible.CTNHCollapsibleGroups.CollapsibleGroup;

import net.minecraft.client.gui.GuiGraphics;

import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.config.SidebarType;
import dev.emi.emi.runtime.EmiDrawContext;
import dev.emi.emi.screen.EmiScreenManager;
import dev.emi.emi.screen.StackBatcher;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mojang.blaze3d.systems.RenderSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * 注入 EMI 侧栏空间 {@link EmiScreenManager.ScreenSpace}，负责折叠组的显示层改写。
 *
 * <p>
 * 本 mixin 只作用于 INDEX 搜索侧栏。它会在 EMI 读取待显示列表时调用
 * {@link CTNHCollapsibleGroups#project(List)}，把已折叠分组压缩成一个代表项；随后在渲染阶段
 * 给折叠代表项和展开成员绘制边框，帮助玩家看出这些物品属于同一个折叠组。
 * </p>
 */
@Mixin(value = EmiScreenManager.ScreenSpace.class, remap = false)
public abstract class EmiScreenManagerScreenSpaceMixin {

    /** EMI 原字段：当前 ScreenSpace 是否处于搜索/索引侧栏模式。 */
    @Shadow
    @Final
    public boolean search;

    /** EMI 原方法：返回该侧栏空间的类型，例如 INDEX 或 FAVORITES。 */
    @Shadow
    public abstract SidebarType getType();

    /** EMI 原字段：侧栏渲染起点 X。当前类保留 shadow 以匹配原布局上下文。 */
    @Shadow
    @Final
    public int tx;

    /** EMI 原字段：侧栏渲染起点 Y。当前类保留 shadow 以匹配原布局上下文。 */
    @Shadow
    @Final
    public int ty;

    /** EMI 原字段：侧栏网格高度，按 18x18 单元格行数计算。 */
    @Shadow
    @Final
    public int th;

    /** EMI 原字段：每行可显示的单元格宽度数组。 */
    @Shadow
    @Final
    public int[] widths;

    /** EMI 原方法：取得指定行可显示的单元格数量。 */
    @Shadow
    public abstract int getWidth(int y);

    /** EMI 原方法：把网格列/行坐标转换成屏幕 X 坐标。 */
    @Shadow
    public abstract int getX(int x, int y);

    /** EMI 原方法：把网格列/行坐标转换成屏幕 Y 坐标。 */
    @Shadow
    public abstract int getY(int x, int y);

    /** EMI 原字段：当前页最多渲染多少个 ingredient。 */
    @Shadow
    @Final
    public int pageSize;

    /** EMI 原方法：返回当前侧栏空间要显示的 ingredient 列表。 */
    @Shadow
    public abstract List<? extends EmiIngredient> getStacks();

    /** EMI 原字段：侧栏使用的批量物品渲染器。 */
    @Shadow
    @Final
    public StackBatcher batcher;

    /** EMI 侧栏单格尺寸。 */
    @Unique
    private static final int ENTRY_SIZE = 18;

    /** 展开分组成员的外边框颜色。GuiGraphics.fill 使用 ABGR 格式。 */
    @Unique
    private static final int GROUP_BORDER_COLOR = 0xCC3344AA;

    /** 展开分组成员的半透明背景颜色。 */
    @Unique
    private static final int GROUP_BG_COLOR = 0x44113377;

    /** 折叠代表项边框颜色。这里只画边框，不覆盖 EMI 原本的物品图标。 */
    @Unique
    private static final int COLLAPSED_BORDER_COLOR = 0xCC4466CC;

    /** 后方叠层图标透明度。 */
    @Unique
    private static final float STACKED_BACK_ICON_ALPHA = 0.45F;

    /** 后方叠层图标遮罩，让不吃 shader alpha 的 item 渲染路径也能显得更靠后。 */
    @Unique
    private static final int STACKED_BACK_ICON_DIM_COLOR = 0x77000000;

    /**
     * 在 EMI 返回侧栏列表后替换为折叠投影列表。
     *
     * <p>
     * 只处理 INDEX 搜索侧栏，避免影响收藏夹等其他侧栏。分组尚未重建或没有有效分组时，
     * 保持 EMI 原始列表不变。
     * </p>
     */
    @Inject(method = "getStacks", at = @At("RETURN"), cancellable = true)
    private void ctnhcore$projectGetStacks(CallbackInfoReturnable<List<? extends EmiIngredient>> cir) {
        if (!CTNHCollapsibleGroups.isEnabled()) return;
        if (search && getType() == SidebarType.INDEX) {
            List<? extends EmiIngredient> original = cir.getReturnValue();
            if (original == null || original.isEmpty()) return;
            if (!CTNHCollapsibleGroups.needsRebuild() && CTNHCollapsibleGroups.hasGroups()) {
                cir.setReturnValue(CTNHCollapsibleGroups.project(original));
            }
        }
    }

    /**
     * 折叠代表项由本 mixin 在批量绘制完成后手动画双层图标。
     *
     * <p>
     * 跳过 EMI 原始的单图标绘制，避免“原图标 + 双层图标”三层重叠。
     * </p>
     */
    @Redirect(method = "render",
              at = @At(value = "INVOKE",
                       target = "Ldev/emi/emi/screen/StackBatcher;render(Ldev/emi/emi/api/stack/EmiIngredient;Lnet/minecraft/client/gui/GuiGraphics;IIF)V"))
    private void ctnhcore$skipCollapsedRepresentativeOriginalIcon(StackBatcher instance, EmiIngredient stack,
                                                                  GuiGraphics draw, int x, int y, float delta) {
        if (CTNHCollapsibleGroups.isEnabled() && search && getType() == SidebarType.INDEX &&
                !CTNHCollapsibleGroups.needsRebuild() && CTNHCollapsibleGroups.isCollapsedRepresentative(stack)) {
            return;
        }
        instance.render(stack, draw, x, y, delta);
    }

    /**
     * 在 EMI 批量绘制物品图标后绘制折叠组叠加层。
     *
     * <p>
     * 折叠代表项只画蓝色边框，保留 EMI 原本渲染出的代表物品图标。展开成员会绘制淡色背景，
     * 并根据相邻格子是否属于同一组来决定哪些边需要画，从而形成连贯的分组区域。
     * </p>
     */
    @Inject(method = "render",
            at = @At(value = "INVOKE",
                     target = "Ldev/emi/emi/screen/StackBatcher;draw()V",
                     shift = At.Shift.AFTER))
    private void ctnhcore$renderGroupOverlays(EmiDrawContext context, int mouseX, int mouseY,
                                              float delta, int startIndex, CallbackInfo ci) {
        if (!CTNHCollapsibleGroups.isEnabled()) return;
        if (!search || getType() != SidebarType.INDEX) return;
        if (CTNHCollapsibleGroups.needsRebuild()) return;

        List<? extends EmiIngredient> stacks = getStacks();
        if (stacks == null || stacks.isEmpty()) return;

        GuiGraphics graphics = context.raw();
        int endIndex = Math.min(startIndex + pageSize, stacks.size());

        List<Integer> expandedXos = new ArrayList<>();
        List<Integer> expandedYos = new ArrayList<>();
        List<Integer> expandedCxs = new ArrayList<>();
        List<Integer> expandedCys = new ArrayList<>();
        List<String> expandedGroupGuids = new ArrayList<>();

        int ri = startIndex;
        outer:
        for (int yo = 0; yo < th; yo++) {
            for (int xo = 0; xo < getWidth(yo); xo++) {
                if (ri >= endIndex) break outer;
                EmiIngredient stack = stacks.get(ri);
                ri++;

                CollapsibleGroup group = CTNHCollapsibleGroups.getGroup(stack);
                if (group == null) continue;

                int cx = getX(xo, yo);
                int cy = getY(xo, yo);

                if (CTNHCollapsibleGroups.isCollapsedRepresentative(stack)) {
                    drawCollapsedGroupStack(context, stack, cx, cy);
                    drawCollapsedGroupOverlay(graphics, cx, cy);
                } else {
                    expandedXos.add(xo);
                    expandedYos.add(yo);
                    expandedCxs.add(cx);
                    expandedCys.add(cy);
                    expandedGroupGuids.add(group.guid);
                }
            }
        }

        for (int index = 0; index < expandedCxs.size(); index++) {
            drawExpandedMemberCell(graphics, index, expandedXos, expandedYos, expandedCxs, expandedCys,
                    expandedGroupGuids);
        }
    }

    /** 为折叠代表项绘制单格边框。 */
    @Unique
    private void drawCollapsedGroupOverlay(GuiGraphics graphics, int cx, int cy) {
        graphics.fill(cx, cy, cx + ENTRY_SIZE, cy + 1, COLLAPSED_BORDER_COLOR);
        graphics.fill(cx, cy + ENTRY_SIZE - 1, cx + ENTRY_SIZE, cy + ENTRY_SIZE, COLLAPSED_BORDER_COLOR);
        graphics.fill(cx, cy, cx + 1, cy + ENTRY_SIZE, COLLAPSED_BORDER_COLOR);
        graphics.fill(cx + ENTRY_SIZE - 1, cy, cx + ENTRY_SIZE, cy + ENTRY_SIZE, COLLAPSED_BORDER_COLOR);
    }

    /** 为折叠代表项补画第二层图标，接近 GTNH NEI 的堆叠视觉。 */
    @Unique
    private void drawCollapsedGroupStack(EmiDrawContext context, EmiIngredient representative, int cx, int cy) {
        EmiIngredient secondary = CTNHCollapsibleGroups.getSecondaryRepresentative(representative);
        if (secondary == null || secondary.isEmpty()) return;

        context.raw().pose().pushPose();
        context.raw().pose().translate(0, 0, -50);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, STACKED_BACK_ICON_ALPHA);
        context.drawStack(secondary, cx + 2, cy - 2, EmiIngredient.RENDER_ICON);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        context.raw().fill(cx + 2, cy - 2, cx + 18, cy + 14, STACKED_BACK_ICON_DIM_COLOR);
        context.raw().pose().popPose();
        context.drawStack(representative, cx - 1, cy + 1, EmiIngredient.RENDER_ICON);
    }

    /**
     * 为展开状态下的单个成员绘制背景和外边框。
     *
     * <p>
     * 如果上下左右存在同组相邻成员，则省略共享边，视觉上形成连续的分组块。
     * </p>
     */
    @Unique
    private void drawExpandedMemberCell(GuiGraphics graphics, int index, List<Integer> xos, List<Integer> yos,
                                        List<Integer> cxs, List<Integer> cys, List<String> groupGuids) {
        int cx = cxs.get(index);
        int cy = cys.get(index);
        graphics.fill(cx + 1, cy + 1, cx + ENTRY_SIZE - 1, cy + ENTRY_SIZE - 1, GROUP_BG_COLOR);

        if (!hasNeighbor(index, xos, yos, groupGuids, 0, -1)) {
            graphics.fill(cx, cy, cx + ENTRY_SIZE, cy + 1, GROUP_BORDER_COLOR);
        }
        if (!hasNeighbor(index, xos, yos, groupGuids, 0, 1)) {
            graphics.fill(cx, cy + ENTRY_SIZE - 1, cx + ENTRY_SIZE, cy + ENTRY_SIZE, GROUP_BORDER_COLOR);
        }
        if (!hasNeighbor(index, xos, yos, groupGuids, -1, 0)) {
            graphics.fill(cx, cy, cx + 1, cy + ENTRY_SIZE, GROUP_BORDER_COLOR);
        }
        if (!hasNeighbor(index, xos, yos, groupGuids, 1, 0)) {
            graphics.fill(cx + ENTRY_SIZE - 1, cy, cx + ENTRY_SIZE, cy + ENTRY_SIZE, GROUP_BORDER_COLOR);
        }
    }

    /**
     * 判断指定方向上是否存在同组相邻格子。
     *
     * @param index 当前成员在临时坐标列表中的索引
     * @param dx    横向偏移，-1 表示左邻居，1 表示右邻居
     * @param dy    纵向偏移，-1 表示上邻居，1 表示下邻居
     * @return true 表示相邻格子存在且属于同一分组
     */
    @Unique
    private boolean hasNeighbor(int index, List<Integer> xos, List<Integer> yos, List<String> groupGuids, int dx,
                                int dy) {
        int nx = xos.get(index) + dx;
        int ny = yos.get(index) + dy;
        String groupGuid = groupGuids.get(index);
        for (int other = 0; other < xos.size(); other++) {
            if (xos.get(other) == nx && yos.get(other) == ny && groupGuids.get(other).equals(groupGuid)) {
                return true;
            }
        }
        return false;
    }
}
