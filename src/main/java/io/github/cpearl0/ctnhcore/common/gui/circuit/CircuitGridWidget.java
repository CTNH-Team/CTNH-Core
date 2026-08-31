package io.github.cpearl0.ctnhcore.common.gui.circuit;

import io.github.cpearl0.ctnhcore.common.circuit.CircuitComponent;
import io.github.cpearl0.ctnhcore.common.circuit.CircuitLayout;
import io.github.cpearl0.ctnhcore.common.circuit.SubstrateTier;
import io.github.cpearl0.ctnhcore.common.machine.simple.CircuitDesignBenchMachine;

import com.gregtechceu.gtceu.api.gui.GuiTextures;

import com.lowdragmc.lowdraglib.gui.util.DrawerHelper;
import com.lowdragmc.lowdraglib.gui.widget.Widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.EnumMap;
import java.util.List;

/**
 * 设计台中央的摆放网格（16px 原版槽位样式）。布局状态由服务端持有；客户端通过镜像数组渲染。
 * 交互（§三改版）：手持元件物品左键标记格子（虚拟摆放，不消耗物品），空手左键移除，右键旋转。
 * 点击经 client action 发包到服务端校验执行。
 */
public class CircuitGridWidget extends Widget {

    @CN("请先放入基板")
    @EN("Insert a substrate first")
    @Key("ctnhcore.circuit.no_substrate")
    public static Lang noSubstrate;

    @CN("手持元件左键：放置（不消耗）")
    @EN("Hold a component + LMB: place")
    @Key("ctnhcore.circuit.help_place")
    public static Lang helpPlace;

    @CN("右键：旋转 / 空手左键：移除")
    @EN("RMB: rotate / empty-hand LMB: remove")
    @Key("ctnhcore.circuit.help_rotate")
    public static Lang helpRotate;

    private static final int ACTION_PLACE = 1;
    private static final int ACTION_ROTATE = 2;
    private static final int ACTION_REMOVE = 3;
    private static final int SYNC_LAYOUT = 0;

    public static final int CELL = 14;
    public static final int GRID_PIXELS = 9 * CELL;

    /** 按元件族着色的格子底色（升级件与同族基础件同色）。 */
    private static final int[] FAMILY_TINTS = {
            0x408B6B4A, // 电阻族 棕
            0x403A5FCD, // 电容族 蓝
            0x40C0392B, // 二极管族 红
            0x40AAAA55, // 真空管 暗黄
            0x402E8B57, // 晶体管族 绿
            0x40D68910, // 电感族 橙
            0x4017A5A5, // 晶振 青
            0x407D3CB5, // 芯片族 紫
            0x407F8C8D, // 散热片 灰
    };

    private final CircuitDesignBenchMachine machine;

    // 客户端镜像（同步包维护）
    @Nullable
    private SubstrateTier mirrorSubstrate;
    private byte[] mirrorComp = new byte[0];
    private byte[] mirrorRot = new byte[0];

    private int lastSyncHash = Integer.MIN_VALUE;

    public CircuitGridWidget(int x, int y, CircuitDesignBenchMachine machine) {
        super(x, y, GRID_PIXELS, GRID_PIXELS);
        this.machine = machine;
    }

    @Nullable
    public SubstrateTier getMirrorSubstrate() {
        return mirrorSubstrate;
    }

    //////////////////////////////////////
    // ***** 同步 *****//
    //////////////////////////////////////

    @Override
    public void writeInitialData(@NotNull FriendlyByteBuf buffer) {
        writeLayout(buffer);
    }

    @Override
    public void readInitialData(@NotNull FriendlyByteBuf buffer) {
        readLayout(buffer);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        int hash = machine.layoutStateHash();
        if (hash != lastSyncHash) {
            lastSyncHash = hash;
            writeUpdateInfo(SYNC_LAYOUT, this::writeLayout);
        }
    }

    @Override
    public void readUpdateInfo(int id, @NotNull FriendlyByteBuf buffer) {
        if (id == SYNC_LAYOUT) {
            readLayout(buffer);
            return;
        }
        super.readUpdateInfo(id, buffer);
    }

    private void writeLayout(FriendlyByteBuf buffer) {
        CircuitLayout layout = machine.getLayout();
        buffer.writeBoolean(layout != null);
        if (layout != null) {
            layout.writeToBuffer(buffer);
        }
    }

    private void readLayout(FriendlyByteBuf buffer) {
        if (!buffer.readBoolean()) {
            mirrorSubstrate = null;
            mirrorComp = new byte[0];
            mirrorRot = new byte[0];
            return;
        }
        SubstrateTier tier = SubstrateTier.values()[buffer.readVarInt()];
        mirrorSubstrate = tier;
        mirrorComp = buffer.readByteArray();
        mirrorRot = buffer.readByteArray();
    }

    //////////////////////////////////////
    // ***** 交互（客户端发包，服务端执行） *****//
    //////////////////////////////////////

    @Override
    public void handleClientAction(int id, @NotNull FriendlyByteBuf buffer) {
        int x = buffer.readVarInt();
        int y = buffer.readVarInt();
        switch (id) {
            case ACTION_PLACE -> machine.placeComponent(x, y, buffer.readVarInt(), buffer.readVarInt());
            case ACTION_ROTATE -> machine.rotateComponent(x, y);
            case ACTION_REMOVE -> machine.removeComponent(x, y);
            default -> super.handleClientAction(id, buffer);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int[] cell = hoveredCell(mouseX, mouseY);
        if (cell == null) return false;
        int x = cell[0];
        int y = cell[1];
        if (button == 0) {
            CircuitComponent carried = carriedComponent();
            if (carried != null) {
                // 手持元件：标记格子（虚拟摆放，不消耗物品）
                writeClientAction(ACTION_PLACE, buf -> {
                    buf.writeVarInt(x);
                    buf.writeVarInt(y);
                    buf.writeVarInt(carried.ordinal());
                    buf.writeVarInt(0);
                });
            } else {
                // 空手：移除
                writeClientAction(ACTION_REMOVE, buf -> {
                    buf.writeVarInt(x);
                    buf.writeVarInt(y);
                });
            }
            playButtonClickSound();
            return true;
        }
        if (button == 1) {
            writeClientAction(ACTION_ROTATE, buf -> {
                buf.writeVarInt(x);
                buf.writeVarInt(y);
            });
            playButtonClickSound();
            return true;
        }
        return false;
    }

    /** 玩家光标上携带的元件物品（无则 null）。 */
    @OnlyIn(Dist.CLIENT)
    @Nullable
    private static CircuitComponent carriedComponent() {
        var player = Minecraft.getInstance().player;
        if (player == null) return null;
        ItemStack carried = player.containerMenu.getCarried();
        return carried.isEmpty() ? null : CircuitComponent.byItem(carried.getItem());
    }

    //////////////////////////////////////
    // ***** 渲染（客户端） *****//
    //////////////////////////////////////

    @OnlyIn(Dist.CLIENT)
    private int[] hoveredCell(double mouseX, double mouseY) {
        if (mirrorSubstrate == null) return null;
        int w = mirrorSubstrate.getGridWidth();
        int h = mirrorSubstrate.getGridHeight();
        int ox = getPosition().x + (GRID_PIXELS - w * CELL) / 2;
        int oy = getPosition().y + (GRID_PIXELS - h * CELL) / 2;
        int x = (int) (mouseX - ox) / CELL;
        int y = (int) (mouseY - oy) / CELL;
        if (x < 0 || y < 0 || x >= w || y >= h) return null;
        return new int[] { x, y };
    }

    @OnlyIn(Dist.CLIENT)
    private static ItemStack iconOf(CircuitComponent component) {
        return IconCache.ICONS.computeIfAbsent(component, c -> c.item().asStack());
    }

    @OnlyIn(Dist.CLIENT)
    private static void drawCentered(GuiGraphics graphics, net.minecraft.client.gui.Font font, Component text,
                                     int posX, int y, int color) {
        graphics.drawString(font, text, posX + (GRID_PIXELS - font.width(text)) / 2, y, color, false);
    }

    @OnlyIn(Dist.CLIENT)
    private static final class IconCache {

        private static final EnumMap<CircuitComponent, ItemStack> ICONS = new EnumMap<>(CircuitComponent.class);
    }

    /** 物品图标原生 16px，按格子尺寸（14px）缩放绘制。 */
    @OnlyIn(Dist.CLIENT)
    private static void renderCellItem(GuiGraphics graphics, ItemStack stack, int x, int y) {
        graphics.pose().pushPose();
        graphics.pose().translate(x, y, 0);
        float scale = CELL / 16f;
        graphics.pose().scale(scale, scale, 1);
        graphics.renderItem(stack, 0, 0);
        graphics.pose().popPose();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void drawInBackground(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        super.drawInBackground(graphics, mouseX, mouseY, partialTicks);
        int posX = getPosition().x;
        int posY = getPosition().y;
        if (mirrorSubstrate == null) {
            DrawerHelper.drawSolidRect(graphics, posX, posY, GRID_PIXELS, GRID_PIXELS, 0xFF101418);
            var font = Minecraft.getInstance().font;
            drawCentered(graphics, font, noSubstrate.translate(), posX,
                    posY + GRID_PIXELS / 2 - 14, 0xFFAAAAAA);
            drawCentered(graphics, font, helpPlace.translate(), posX,
                    posY + GRID_PIXELS / 2, 0xFF888888);
            drawCentered(graphics, font, helpRotate.translate(), posX,
                    posY + GRID_PIXELS / 2 + 12, 0xFF888888);
            return;
        }
        int w = mirrorSubstrate.getGridWidth();
        int h = mirrorSubstrate.getGridHeight();
        int ox = posX + (GRID_PIXELS - w * CELL) / 2;
        int oy = posY + (GRID_PIXELS - h * CELL) / 2;
        // 原版槽位样式
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                GuiTextures.SLOT.draw(graphics, mouseX, mouseY, ox + x * CELL - 1, oy + y * CELL - 1, CELL + 2,
                        CELL + 2);
            }
        }
        // 元件（原点绘制图标，多格元件填充整个占地）
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                byte id = mirrorComp[y * w + x];
                if (id <= 0) continue;
                CircuitComponent component = CircuitComponent.byCellId(id);
                if (component == null) continue;
                int rotation = mirrorRot[y * w + x] & 3;
                int fw = component.width(rotation) * CELL;
                int fh = component.height(rotation) * CELL;
                DrawerHelper.drawSolidRect(graphics, ox + x * CELL, oy + y * CELL, fw - 1, fh - 1,
                        FAMILY_TINTS[component.getFamily().ordinal()]);
                renderCellItem(graphics, iconOf(component), ox + x * CELL + (fw - CELL) / 2,
                        oy + y * CELL + (fh - CELL) / 2);
                drawRotationIndicator(graphics, ox + x * CELL, oy + y * CELL, rotation);
            }
        }
        // 悬停：手持元件时预览占地（绿=可放置，红=不可），空手时高亮单格
        int[] hover = hoveredCell(mouseX, mouseY);
        if (hover != null) {
            CircuitComponent carried = carriedComponent();
            if (carried != null) {
                boolean valid = canPlaceAt(hover[0], hover[1], carried);
                int tint = valid ? 0x4055FF55 : 0x50FF4444;
                for (int[] c : CircuitLayout.footprint(carried, hover[0], hover[1], 0)) {
                    if (c[0] < 0 || c[1] < 0 || c[0] >= w || c[1] >= h) continue;
                    DrawerHelper.drawSolidRect(graphics, ox + c[0] * CELL, oy + c[1] * CELL, CELL - 1, CELL - 1,
                            tint);
                }
                renderCellItem(graphics, iconOf(carried), ox + hover[0] * CELL, oy + hover[1] * CELL);
            } else {
                DrawerHelper.drawSolidRect(graphics, ox + hover[0] * CELL, oy + hover[1] * CELL, CELL - 1, CELL - 1,
                        0x30FFFFFF);
            }
        }
    }

    /** 客户端放置合法性预检（仅用于预览着色；服务端仍会二次校验）。 */
    @OnlyIn(Dist.CLIENT)
    private boolean canPlaceAt(int x, int y, CircuitComponent component) {
        if (mirrorSubstrate == null || !mirrorSubstrate.allows(component)) return false;
        int w = mirrorSubstrate.getGridWidth();
        int h = mirrorSubstrate.getGridHeight();
        for (int[] c : CircuitLayout.footprint(component, x, y, 0)) {
            if (c[0] < 0 || c[1] < 0 || c[0] >= w || c[1] >= h) return false;
            if (mirrorComp[c[1] * w + c[0]] != 0) return false;
        }
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    private static void drawRotationIndicator(GuiGraphics graphics, int cellX, int cellY, int rotation) {
        // 3px 白点标在朝向边缘中点：0 右、1 下、2 左、3 上
        int dx = switch (rotation) {
            case 0 -> CELL - 4;
            case 2 -> 1;
            default -> CELL / 2 - 1;
        };
        int dy = switch (rotation) {
            case 1 -> CELL - 4;
            case 3 -> 1;
            default -> CELL / 2 - 1;
        };
        DrawerHelper.drawSolidRect(graphics, cellX + dx, cellY + dy, 3, 3, 0xCCFFFFFF);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void drawInForeground(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        super.drawInForeground(graphics, mouseX, mouseY, partialTicks);
        if (mirrorSubstrate == null || gui == null) return;
        int[] cell = hoveredCell(mouseX, mouseY);
        if (cell == null) return;
        int w = mirrorSubstrate.getGridWidth();
        byte id = mirrorComp[cell[1] * w + cell[0]];
        CircuitComponent component = CircuitComponent.byCellId(id);
        if (component != null) {
            gui.getModularUIGui().setHoverTooltip(List.of(component.item().asStack().getHoverName()),
                    ItemStack.EMPTY, null, null);
        }
    }
}
