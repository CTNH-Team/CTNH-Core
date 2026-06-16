package io.github.cpearl0.ctnhcore.mixin.emi;

import io.github.cpearl0.ctnhcore.utils.emi.collapsible.CTNHCollapsibleGroups;
import io.github.cpearl0.ctnhcore.utils.emi.collapsible.CTNHCollapsibleGroups.CollapsibleGroup;

import net.minecraft.client.gui.GuiGraphics;

import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.config.SidebarType;
import dev.emi.emi.runtime.EmiDrawContext;
import dev.emi.emi.screen.EmiScreenManager;
import dev.emi.emi.screen.StackBatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

/** 改写 EMI INDEX 侧栏显示列表，并绘制折叠组的边框和双层图标。 */
@Mixin(value = EmiScreenManager.ScreenSpace.class, remap = false)
public abstract class EmiScreenManagerScreenSpaceMixin {

    @Shadow
    public boolean search;

    @Shadow
    public abstract SidebarType getType();

    @Shadow
    public int th;

    @Shadow
    public abstract int getWidth(int y);

    @Shadow
    public abstract int getX(int x, int y);

    @Shadow
    public abstract int getY(int x, int y);

    @Shadow
    public int pageSize;

    @Shadow
    public abstract List<? extends EmiIngredient> getStacks();

    /** EMI 侧栏单格尺寸。 */
    @Unique
    private static final int ENTRY_SIZE = 18;

    /** 展开分组成员的外边框颜色。GuiGraphics.fill 使用 ARGB 格式。 */
    @Unique
    private static final int GROUP_BORDER_COLOR = 0xCC3344AA;

    /** 展开分组成员的半透明背景颜色。 */
    @Unique
    private static final int GROUP_BG_COLOR = 0x44113377;

    /** GTNH NEI 默认折叠背景色 0x335555EE，GuiGraphics 使用 ARGB。 */
    @Unique
    private static final int COLLAPSED_BG_COLOR = 0x335555EE;

    /** GTNH NEI 会把同色背景的 alpha 加深 2/5 作为边框色：0x33 + 0x66 = 0x99。 */
    @Unique
    private static final int COLLAPSED_BORDER_COLOR = 0x995555EE;

    /** GTNH NEI 折叠背景物品偏移：rect.offset(1, -1)。 */
    @Unique
    private static final int COLLAPSED_BACK_X_OFFSET = 1;

    @Unique
    private static final int COLLAPSED_BACK_Y_OFFSET = -1;

    /** GTNH NEI 折叠前景物品偏移：rect.offset(-2, 2)。 */
    @Unique
    private static final int COLLAPSED_FRONT_X_OFFSET = -2;

    @Unique
    private static final int COLLAPSED_FRONT_Y_OFFSET = 2;

    /** 只在 INDEX 侧栏把 EMI 原列表替换为折叠投影。 */
    @Inject(method = "getStacks", at = @At("RETURN"), cancellable = true)
    private void ctnhcore$projectGetStacks(CallbackInfoReturnable<List<? extends EmiIngredient>> cir) {
        if (search && getType() == SidebarType.INDEX) {
            List<? extends EmiIngredient> original = cir.getReturnValue();
            if (original == null || original.isEmpty()) return;
            if (!CTNHCollapsibleGroups.needsRebuild() && CTNHCollapsibleGroups.hasGroups()) {
                cir.setReturnValue(CTNHCollapsibleGroups.project(original));
            }
        }
    }

    /** 折叠代表项稍后手动画双层图标，这里跳过 EMI 原图标。 */
    @Redirect(method = "render",
              at = @At(value = "INVOKE",
                       target = "Ldev/emi/emi/screen/StackBatcher;render(Ldev/emi/emi/api/stack/EmiIngredient;Lnet/minecraft/client/gui/GuiGraphics;IIF)V"))
    private void ctnhcore$skipCollapsedRepresentativeOriginalIcon(StackBatcher instance, EmiIngredient stack,
                                                                  GuiGraphics draw, int x, int y, float delta) {
        if (search && getType() == SidebarType.INDEX &&
                !CTNHCollapsibleGroups.needsRebuild() && CTNHCollapsibleGroups.isCollapsedRepresentative(stack)) {
            return;
        }
        instance.render(stack, draw, x, y, delta);
    }

    /** EMI 画完物品后，补画折叠组背景、边框和双层图标。 */
    @Inject(method = "render",
            at = @At(value = "INVOKE",
                     target = "Ldev/emi/emi/screen/StackBatcher;draw()V",
                     shift = At.Shift.AFTER))
    private void ctnhcore$renderGroupOverlays(EmiDrawContext context, int mouseX, int mouseY,
                                              float delta, int startIndex, CallbackInfo ci) {
        if (!search || getType() != SidebarType.INDEX) return;
        if (CTNHCollapsibleGroups.needsRebuild()) return;

        List<? extends EmiIngredient> stacks = getStacks();
        if (stacks == null || stacks.isEmpty()) return;

        GuiGraphics graphics = context.raw();
        int endIndex = Math.min(startIndex + pageSize, stacks.size());

        List<ExpandedCell> expandedCells = new ArrayList<>();

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
                    drawCollapsedGroupBackground(graphics, cx, cy);
                    drawCollapsedGroupStack(context, stack, cx, cy);
                    drawCollapsedGroupOverlay(graphics, cx, cy);
                } else if (group.isExpanded()) {
                    expandedCells.add(new ExpandedCell(xo, yo, cx, cy, group.guid));
                }
            }
        }

        for (int index = 0; index < expandedCells.size(); index++) {
            drawExpandedMemberCell(graphics, expandedCells, index);
        }
    }

    /** 为折叠代表项绘制 GTNH NEI 风格半透明背景。 */
    @Unique
    private void drawCollapsedGroupBackground(GuiGraphics graphics, int cx, int cy) {
        graphics.fill(cx, cy, cx + ENTRY_SIZE, cy + ENTRY_SIZE, COLLAPSED_BG_COLOR);
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
        context.drawStack(secondary, cx + COLLAPSED_BACK_X_OFFSET, cy + COLLAPSED_BACK_Y_OFFSET,
                EmiIngredient.RENDER_ICON);
        context.raw().pose().popPose();
        context.drawStack(representative, cx + COLLAPSED_FRONT_X_OFFSET, cy + COLLAPSED_FRONT_Y_OFFSET,
                EmiIngredient.RENDER_ICON);
    }

    /** 展开成员有相邻同组格子时省略共享边，形成连续区域。 */
    @Unique
    private void drawExpandedMemberCell(GuiGraphics graphics, List<ExpandedCell> cells, int index) {
        ExpandedCell cell = cells.get(index);
        int cx = cell.cx();
        int cy = cell.cy();
        graphics.fill(cx + 1, cy + 1, cx + ENTRY_SIZE - 1, cy + ENTRY_SIZE - 1, GROUP_BG_COLOR);

        if (!hasNeighbor(cells, index, 0, -1)) {
            graphics.fill(cx, cy, cx + ENTRY_SIZE, cy + 1, GROUP_BORDER_COLOR);
        }
        if (!hasNeighbor(cells, index, 0, 1)) {
            graphics.fill(cx, cy + ENTRY_SIZE - 1, cx + ENTRY_SIZE, cy + ENTRY_SIZE, GROUP_BORDER_COLOR);
        }
        if (!hasNeighbor(cells, index, -1, 0)) {
            graphics.fill(cx, cy, cx + 1, cy + ENTRY_SIZE, GROUP_BORDER_COLOR);
        }
        if (!hasNeighbor(cells, index, 1, 0)) {
            graphics.fill(cx + ENTRY_SIZE - 1, cy, cx + ENTRY_SIZE, cy + ENTRY_SIZE, GROUP_BORDER_COLOR);
        }
    }

    @Unique
    private boolean hasNeighbor(List<ExpandedCell> cells, int index, int dx, int dy) {
        ExpandedCell cell = cells.get(index);
        int nx = cell.xo() + dx;
        int ny = cell.yo() + dy;
        for (ExpandedCell other : cells) {
            if (other.xo() == nx && other.yo() == ny && other.groupGuid().equals(cell.groupGuid())) {
                return true;
            }
        }
        return false;
    }

    private record ExpandedCell(int xo, int yo, int cx, int cy, String groupGuid) {}
}
