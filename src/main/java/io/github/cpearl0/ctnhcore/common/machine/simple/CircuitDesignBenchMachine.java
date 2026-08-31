package io.github.cpearl0.ctnhcore.common.machine.simple;

import io.github.cpearl0.ctnhcore.common.circuit.CircuitBoardCalculator;
import io.github.cpearl0.ctnhcore.common.circuit.CircuitComponent;
import io.github.cpearl0.ctnhcore.common.circuit.CircuitLayout;
import io.github.cpearl0.ctnhcore.common.circuit.CircuitStats;
import io.github.cpearl0.ctnhcore.common.circuit.SubstrateTier;
import io.github.cpearl0.ctnhcore.common.gui.circuit.CircuitGridWidget;
import io.github.cpearl0.ctnhcore.common.gui.circuit.CircuitStatsWidget;
import io.github.cpearl0.ctnhcore.common.item.CircuitBlueprintItem;

import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.fancy.FancyMachineUIWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IFancyUIMachine;

import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.texture.GuiTextureGroup;
import com.lowdragmc.lowdraglib.gui.texture.ResourceBorderTexture;
import com.lowdragmc.lowdraglib.gui.texture.TextTexture;
import com.lowdragmc.lowdraglib.gui.widget.ButtonWidget;
import com.lowdragmc.lowdraglib.gui.widget.SlotWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.misc.ItemStackTransfer;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.UUID;

/**
 * 电路板设计台：放入基板后在网格内虚拟摆放元件（不消耗），
 * 实时结算属性并可导出为电路图纸。见 docs/design/circuit-board-system.md §一~§三。
 */
public class CircuitDesignBenchMachine extends MetaMachine implements IFancyUIMachine {

    @CN("出图")
    @EN("Export")
    @Key("ctnhcore.circuit.export")
    public static Lang export;

    @CN("需要先放入基板")
    @EN("A substrate is required")
    @Key("ctnhcore.circuit.export_no_substrate")
    public static Lang exportNoSubstrate;

    @CN("布局为空，无法出图")
    @EN("Cannot export: layout is empty")
    @Key("ctnhcore.circuit.export_no_layout")
    public static Lang exportNoLayout;

    @CN("请先取出已有图纸")
    @EN("Take out the existing blueprint first")
    @Key("ctnhcore.circuit.export_slot_occupied")
    public static Lang exportSlotOccupied;

    @CN("图纸已导出")
    @EN("Blueprint exported")
    @Key("ctnhcore.circuit.export_success")
    public static Lang exportSuccess;

    @CN("放入基板（取出即清空布局）")
    @EN("Insert a substrate (taking it out clears the layout)")
    @Key("ctnhcore.circuit.substrate_slot_tip")
    public static Lang substrateSlotTip;

    @CN("图纸输出槽")
    @EN("Blueprint output slot")
    @Key("ctnhcore.circuit.blueprint_slot_tip")
    public static Lang blueprintSlotTip;

    // 高度预算：标题栏 20 + 网格 126（9×14px）+ 间隙 + 玩家背包 86 = 236，
    // 不可再压——内容区下沿必须 ≤ UI_HEIGHT - 86（背包起点），否则与背包重合；
    // 窗口总高若超过屏幕可用 GUI 高度，居中后上边框会被裁掉。
    private static final int UI_WIDTH = 256;
    private static final int UI_HEIGHT = 154;
    private static final int GRID_X = 6;
    private static final int GRID_Y = 22;
    private static final int PANEL_X = 154;

    @Persisted
    protected final ItemStackTransfer substrateSlot;
    @Persisted
    protected final ItemStackTransfer blueprintSlot;

    @Nullable
    @Getter
    protected CircuitLayout layout;
    @Getter
    protected CircuitStats stats = CircuitStats.EMPTY;
    @Getter
    protected int lastDerated;
    @Getter
    protected int lastCongested;
    @Getter
    protected int lastNoiseExcess;

    @Persisted
    private String lastDesigner = "";
    @Nullable
    private UUID lastOpenerUUID;

    public CircuitDesignBenchMachine(IMachineBlockEntity holder) {
        super(holder);
        this.substrateSlot = new ItemStackTransfer(1);
        this.substrateSlot.setFilter(stack -> SubstrateTier.fromStack(stack) != null);
        this.substrateSlot.setOnContentsChanged(this::onSubstrateChanged);
        this.blueprintSlot = new ItemStackTransfer(1);
        this.blueprintSlot.setFilter(stack -> false);
    }

    //////////////////////////////////////
    // ***** 布局状态 *****//
    //////////////////////////////////////

    protected void onSubstrateChanged() {
        reconcileLayoutWithSlot();
    }

    /** 布局始终与基板槽内的基板绑定：无基板则无布局，换基板则清空重开。 */
    private void reconcileLayoutWithSlot() {
        SubstrateTier slotTier = SubstrateTier.fromStack(substrateSlot.getStackInSlot(0));
        if (slotTier == null) {
            layout = null;
        } else if (layout == null || layout.getSubstrate() != slotTier) {
            layout = new CircuitLayout(slotTier);
        }
        recomputeStats();
        markDirty();
    }

    public void recomputeStats() {
        if (layout == null || layout.isEmpty()) {
            stats = CircuitStats.EMPTY;
            lastDerated = 0;
            lastCongested = 0;
            lastNoiseExcess = 0;
            return;
        }
        var result = CircuitBoardCalculator.evaluate(layout);
        stats = result.stats();
        lastDerated = result.derated();
        lastCongested = result.congested();
        lastNoiseExcess = result.noiseExcess();
    }

    /** UI 增量同步用的布局哈希。 */
    public int layoutStateHash() {
        return layout == null ? -1 : 31 * layout.getSubstrate().ordinal() + layout.stateHash();
    }

    //////////////////////////////////////
    // ***** 编辑操作（服务端，由控件发包触发） *****//
    //////////////////////////////////////

    public void placeComponent(int x, int y, int componentOrdinal, int rotation) {
        if (layout == null) return;
        CircuitComponent[] values = CircuitComponent.values();
        if (componentOrdinal < 0 || componentOrdinal >= values.length) return;
        if (layout.place(values[componentOrdinal], x, y, rotation)) afterLayoutChanged();
    }

    public void rotateComponent(int x, int y) {
        if (layout != null && layout.rotateAt(x, y)) afterLayoutChanged();
    }

    public void removeComponent(int x, int y) {
        if (layout != null && layout.removeAt(x, y)) afterLayoutChanged();
    }

    public void clearLayout() {
        if (layout != null && !layout.isEmpty()) {
            layout.clear();
            afterLayoutChanged();
        }
    }

    private void afterLayoutChanged() {
        recomputeStats();
        markDirty();
    }

    //////////////////////////////////////
    // ***** 图纸导出 *****//
    //////////////////////////////////////

    public void exportBlueprint() {
        Player player = lastOpener();
        if (layout == null) {
            notify(player, exportNoSubstrate);
            return;
        }
        if (layout.isEmpty()) {
            notify(player, exportNoLayout);
            return;
        }
        if (!blueprintSlot.getStackInSlot(0).isEmpty()) {
            notify(player, exportSlotOccupied);
            return;
        }
        blueprintSlot.setStackInSlot(0, CircuitBlueprintItem.create(layout, stats, lastDesigner));
        notify(player, exportSuccess);
    }

    private void notify(@Nullable Player player, Lang message) {
        if (player != null) player.sendSystemMessage(message.translate());
    }

    @Nullable
    private Player lastOpener() {
        if (lastOpenerUUID == null || !(getLevel() instanceof ServerLevel serverLevel)) return null;
        return serverLevel.getServer().getPlayerList().getPlayer(lastOpenerUUID);
    }

    //////////////////////////////////////
    // ***** 持久化 *****//
    //////////////////////////////////////

    @Override
    public void saveCustomPersistedData(@NotNull CompoundTag tag, boolean forDrop) {
        super.saveCustomPersistedData(tag, forDrop);
        if (layout != null) tag.put("circuit_layout", layout.writeNbt());
    }

    @Override
    public void loadCustomPersistedData(@NotNull CompoundTag tag) {
        super.loadCustomPersistedData(tag);
        layout = tag.contains("circuit_layout") ? CircuitLayout.fromNbt(tag.getCompound("circuit_layout")) : null;
        recomputeStats();
        // 与基板槽的一致性在下次打开 UI 时由 reconcileLayoutWithSlot 兜底
    }

    //////////////////////////////////////
    // ***** UI *****//
    //////////////////////////////////////

    @Override
    public ModularUI createUI(Player entityPlayer) {
        if (!entityPlayer.level().isClientSide) {
            this.lastOpenerUUID = entityPlayer.getUUID();
            this.lastDesigner = entityPlayer.getGameProfile().getName();
            reconcileLayoutWithSlot();
        }
        return new ModularUI(UI_WIDTH, UI_HEIGHT, this, entityPlayer)
                .widget(new FancyMachineUIWidget(this, UI_WIDTH, UI_HEIGHT));
    }

    @Override
    public Widget createMainPage(FancyMachineUIWidget widget) {
        WidgetGroup page = new WidgetGroup(0, 0, UI_WIDTH, UI_HEIGHT);
        page.addWidget(new CircuitGridWidget(GRID_X, GRID_Y, this));
        page.addWidget(new CircuitStatsWidget(PANEL_X, GRID_Y, this));
        // 基板 / 图纸槽（悬停提示代替文字标签，节省版面）
        SlotWidget substrate = new SlotWidget(substrateSlot, 0, PANEL_X, 130, true, true);
        substrate.setBackground(GuiTextures.SLOT);
        substrate.setHoverTooltips(substrateSlotTip.translate(),
                CircuitGridWidget.helpPlace.translate(),
                CircuitGridWidget.helpRotate.translate());
        page.addWidget(substrate);
        SlotWidget blueprint = new SlotWidget(blueprintSlot, 0, PANEL_X + 22, 130, true, false);
        blueprint.setBackground(GuiTextures.SLOT);
        blueprint.setHoverTooltips(blueprintSlotTip.translate());
        page.addWidget(blueprint);
        page.addWidget(new ButtonWidget(PANEL_X + 44, 130, 52, 18,
                new GuiTextureGroup(ResourceBorderTexture.BUTTON_COMMON, new TextTexture(export.key())),
                cd -> {
                    if (!cd.isRemote) exportBlueprint();
                }));
        return page;
    }
}
