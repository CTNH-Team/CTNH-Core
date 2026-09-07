package io.github.cpearl0.ctnhcore.common.machine.simple;

import io.github.cpearl0.ctnhcore.api.machine.feature.IDigitalMiner;
import io.github.cpearl0.ctnhcore.api.recipe.DigitalMinerLogic;
import io.github.cpearl0.ctnhcore.common.gui.SimpleNumberInputWidget;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.GTCapabilityHelper;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.cover.filter.ItemFilter;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.widget.LargeStackSlotWidget;
import com.gregtechceu.gtceu.api.gui.widget.SlotWidget;
import com.gregtechceu.gtceu.api.gui.widget.ToggleButtonWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.WorkableTieredMachine;
import com.gregtechceu.gtceu.api.machine.feature.IDataInfoProvider;
import com.gregtechceu.gtceu.api.machine.feature.IFancyUIMachine;
import com.gregtechceu.gtceu.api.machine.trait.AutoOutputTrait;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.machine.trait.WorkLogic;
import com.gregtechceu.gtceu.api.transfer.item.CustomItemStackHandler;
import com.gregtechceu.gtceu.api.transfer.item.LargeStackItemHandler;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.machines.GTMachineUtils;
import com.gregtechceu.gtceu.common.item.PortableScannerBehavior;
import com.gregtechceu.gtceu.common.machine.multiblock.part.ItemBusPartMachine;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.gregtechceu.gtceu.data.lang.LangHandler;

import com.lowdragmc.lowdraglib.gui.texture.TextTexture;
import com.lowdragmc.lowdraglib.gui.util.ClickData;
import com.lowdragmc.lowdraglib.gui.widget.*;
import com.lowdragmc.lowdraglib.syncdata.ISubscription;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Prefix;
import com.mojang.blaze3d.MethodsReturnNonnullByDefault;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@Prefix("gui.digital_miner")
public class DigitalMiner extends WorkableTieredMachine implements IDigitalMiner, IFancyUIMachine, IDataInfoProvider {

    @CN("水平范围:")
    @EN("Horizontal Range:")
    static Lang horizontal_range;

    @CN("最小高度:")
    @EN("Min Height:")
    static Lang min_height;

    @CN("最大高度:")
    @EN("Max Height:")
    static Lang max_height;

    @CN("重置")
    @EN("Reset")
    static Lang reset;

    @CN("修改配置后必须重置才能生效。")
    @EN("You must reset for changes to take effect.")
    static Lang reset_tooltip;

    @CN("精准")
    @EN("Silk")
    static Lang silk;

    @CN("开启精准采集模式，4倍耗电。")
    @EN("Enable Silk Touch mode. Uses 4x energy.")
    static Lang silk_tooltip;

    @CN("挖掘: ")
    @EN("Mined: ")
    static Lang mined_prefix;

    private long energyPerTick;
    @Nullable
    protected TickableSubscription batterySubs;
    @Nullable
    protected ISubscription energySubs;
    @Persisted
    protected final NotifiableItemStackHandler exportItems = attachTrait(
            new NotifiableItemStackHandler(this, 27, IO.OUT, IO.OUT,
                    slots -> new LargeStackItemHandler(slots, ItemBusPartMachine.getSlotMultiplier(getTier()))));
    @Persisted
    protected final CustomItemStackHandler filterInventory;
    @Getter
    @Persisted
    protected final CustomItemStackHandler chargerInventory;
    @Getter
    protected ItemFilter itemFilter;
    // widget
    protected SlotWidget filterSlot;
    protected ButtonWidget resetButton;
    protected ToggleButtonWidget silkButton;
    protected ButtonWidget fortuneButton;
    protected ButtonWidget overClockButton;
    // miner property
    @Getter
    @Setter
    @Persisted
    private int minerRadius;
    @Getter
    @Setter
    @Persisted
    private int minHeight;
    @Getter
    @Setter
    @Persisted
    private int maxHeight;
    @Persisted
    private int silkLevel;
    private int fortuneLevel;

    public DigitalMiner(IMachineBlockEntity holder, int tier, Object... args) {
        super(holder, tier, GTMachineUtils.defaultTankSizeFunction, args);
        this.energyPerTick = GTValues.VEX[tier - 1];
        this.filterInventory = createFilterItemHandler();
        this.chargerInventory = createChargerItemHandler(args);
        var autoOutput = AutoOutputTrait.ofItems(this, exportItems);
        autoOutput.setAutoOutputItems(true);
        attachPersistentTrait("auto_output", autoOutput);
        this.fortuneLevel = 1;
        this.silkLevel = 0;
        this.minHeight = 0;
        this.maxHeight = 256;
        this.minerRadius = getRange(tier);
    }

    public static int getRange(int tier) {
        return 1 << (tier + 3);// (int) (8 * Math.pow(2, tier));
    }

    protected CustomItemStackHandler createFilterItemHandler() {
        var transfer = new CustomItemStackHandler();
        transfer.setFilter(
                item -> item.is(GTItems.ITEM_FILTER.asItem()) || item.is(GTItems.TAG_FILTER.asItem()));
        return transfer;
    }

    protected CustomItemStackHandler createChargerItemHandler(Object... args) {
        var handler = new CustomItemStackHandler() {

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }
        };
        handler.setFilter(item -> GTCapabilityHelper.getElectricItem(item) != null ||
                (ConfigHolder.INSTANCE.compat.energy.nativeEUToFE &&
                        GTCapabilityHelper.getForgeEnergyItem(item) != null));
        return handler;
    }

    @Override
    protected WorkLogic createWorkLogic(Object... args) {
        return new DigitalMinerLogic(this, getRange(getTier()), 0, 256, 0, null,
                1, (int) (40 / Math.pow(2, getTier())));
    }

    @Override
    public DigitalMinerLogic getWorkLogic() {
        return (DigitalMinerLogic) super.getWorkLogic();
    }

    @Override
    public void onMachineRemoved() {
        if (!isRemote()) {
            getWorkLogic().ensureChunkUnforced();
        }
        clearInventory(exportItems.storage);
        clearInventory(filterInventory);
        clearInventory(chargerInventory);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (!isRemote()) {
            filterChange();
            updateBatterySubscription();
            energySubs = energyContainer.addChangedListener(this::updateBatterySubscription);
            chargerInventory.setOnContentsChanged(this::updateBatterySubscription);
        }
    }

    @Override
    public void onUnload() {
        if (!isRemote()) {
            getWorkLogic().ensureChunkUnforced();
        }
        super.onUnload();
        if (batterySubs != null) {
            batterySubs.unsubscribe();
            batterySubs = null;
        }
        if (energySubs != null) {
            energySubs.unsubscribe();
            energySubs = null;
        }
    }

    protected void updateBatterySubscription() {
        if (energyContainer.dischargeOrRechargeEnergyContainers(chargerInventory, 0, true)) {
            batterySubs = subscribeServerTick(batterySubs, this::chargeBattery);
        } else if (batterySubs != null) {
            batterySubs.unsubscribe();
            batterySubs = null;
        }
    }

    protected void chargeBattery() {
        if (!energyContainer.dischargeOrRechargeEnergyContainers(chargerInventory, 0, false)) {
            updateBatterySubscription();
        }
    }

    @Override
    public boolean drainInput(boolean simulate) {
        long resultEnergy = energyContainer.getEnergyStored() - energyPerTick;
        if (resultEnergy >= 0L && resultEnergy <= energyContainer.getEnergyCapacity()) {
            if (!simulate)
                energyContainer.removeEnergy(energyPerTick);
            return true;
        }
        return false;
    }

    @Override
    public List<NotifiableItemStackHandler> getOutputHandlers() {
        return List.of(exportItems);
    }

    private static final int BORDER_WIDTH = 3;

    @Override
    public Widget createUIWidget() {
        int rowSize = 3;
        int colSize = 9;
        int width = colSize * 18 + 2;
        int height = rowSize * 18 + 76 + 4;
        int index = 0;

        int leftPadding = 1;

        WidgetGroup group = new WidgetGroup(0, 0, width, height);

        // information screen
        var componentPanel = new ComponentPanelWidget(4, 5, this::addDisplayText).setMaxWidthLimit(110);
        var container = new WidgetGroup(leftPadding, 0, 84, 76);
        container.addWidget(new DraggableScrollableWidgetGroup(4, 4, container.getSize().width - 8,
                container.getSize().height - 8)
                .setBackground(GuiTextures.DISPLAY)
                .addWidget(componentPanel));
        container.setBackground(GuiTextures.BACKGROUND_INVERSE);
        group.addWidget(container);

        // output slots
        WidgetGroup slots = new WidgetGroup(leftPadding, 76 + 4 / 2, colSize * 18, rowSize * 18);
        for (int y = 0; y < rowSize; y++) {
            for (int x = 0; x < colSize; x++) {
                var slot = new LargeStackSlotWidget(exportItems.storage, index++, x * 18, y * 18, true, false)
                        .setBackground(GuiTextures.SLOT);
                slots.addWidget(slot);
            }
        }
        group.addWidget(slots);

        // filter slot
        this.filterSlot = new SlotWidget(this.filterInventory, 0, 110, 4, true, true);
        this.filterSlot.setChangeListener(this::filterChange)
                .setBackground(GuiTextures.SLOT, GuiTextures.FILTER_SLOT_OVERLAY);
        group.addWidget(filterSlot);

        // battery slot (charger)
        var batterySlot = new SlotWidget(this.chargerInventory, 0, 128, 4, true, true)
                .setBackground(GuiTextures.SLOT, GuiTextures.CHARGER_OVERLAY)
                .setHoverTooltips(LangHandler.getMultiLang("gtceu.gui.charger_slot.tooltip",
                        GTValues.VNF[getTier()], GTValues.VNF[getTier()]).toArray(Component[]::new));
        group.addWidget(batterySlot);

        // Radius
        group.addWidget(new LabelWidget(88, 26, horizontal_range.translate()));
        group.addWidget(new SimpleNumberInputWidget(132, 24, 30, 12, this::getMinerRadius, this::setMinerRadius)
                .setMin(1).setMax(getRange(getTier())));

        // Min height
        group.addWidget(new LabelWidget(88, 44, min_height.translate()));
        group.addWidget(new SimpleNumberInputWidget(132, 42, 30, 12, this::getMinHeight, this::setMinHeight)
                .setMin(getLevel().getMinBuildHeight()).setMax(getLevel().getMaxBuildHeight()));

        // Max height
        group.addWidget(new LabelWidget(88, 62, max_height.translate()));
        group.addWidget(new SimpleNumberInputWidget(132, 60, 30, 12, this::getMaxHeight, this::setMaxHeight)
                .setMin(getLevel().getMinBuildHeight()).setMax(getLevel().getMaxBuildHeight()));

        // reset button
        this.resetButton = new ButtonWidget(9, 54 + BORDER_WIDTH, 18, 16 - BORDER_WIDTH,
                new TextTexture("")
                        .setSupplier(() -> reset.translate().getString())
                        .setDropShadow(false)
                        .setColor(ChatFormatting.GRAY.getColor()),
                this::reset);
        this.resetButton.setHoverTooltips(reset_tooltip.translate());
        group.addWidget(this.resetButton);

        // silk button
        this.silkButton = new ToggleButtonWidget(29, 54 + BORDER_WIDTH, 18, 16 - BORDER_WIDTH,
                () -> silkLevel != 0, this::setSilkEnabled);
        this.silkButton.setTexture(
                new TextTexture("")
                        .setSupplier(() -> silk.translate().getString())
                        .setDropShadow(false)
                        .setColor(ChatFormatting.GRAY.getColor()),
                new TextTexture("")
                        .setSupplier(() -> silk.translate().getString())
                        .setDropShadow(false)
                        .setColor(ChatFormatting.GREEN.getColor()));
        this.silkButton.setHoverTooltips(silk_tooltip.translate());
        group.addWidget(this.silkButton);

        return group;
    }

    private void resetWorkLogic() {
        getWorkLogic().resetWorkLogic(this.minerRadius, this.minHeight, this.maxHeight, this.silkLevel, itemFilter);
    }

    private void filterChange() {
        this.itemFilter = null;
        if (!filterInventory.getStackInSlot(0).isEmpty())
            this.itemFilter = ItemFilter.loadFilter(filterInventory.getStackInSlot(0));
        resetWorkLogic();
    }

    private void reset(ClickData clickData) {
        resetWorkLogic();
        getWorkLogic().setWorkingEnabled(false);
    }

    private void setSilkEnabled(boolean enabled) {
        if (isRemote()) return;
        silkLevel = enabled ? 1 : 0;
        energyPerTick = GTValues.VEX[getTier() - 1] * (enabled ? 4 : 1);
        resetWorkLogic();
    }

    private void addDisplayText(@NotNull List<Component> textList) {
        textList.add(mined_prefix.translate().append(String.valueOf(getWorkLogic().getOreAmount())));
        if (getWorkLogic().isDone())
            textList.add(Component.translatable("gtceu.multiblock.large_miner.done")
                    .setStyle(Style.EMPTY.withColor(ChatFormatting.GREEN)));
        else if (getWorkLogic().isWorking())
            textList.add(Component.translatable("gtceu.multiblock.large_miner.working")
                    .setStyle(Style.EMPTY.withColor(ChatFormatting.GOLD)));
        else if (!this.isWorkingEnabled())
            textList.add(Component.translatable("gtceu.multiblock.work_paused"));
        if (getWorkLogic().isInventoryFull())
            textList.add(Component.translatable("gtceu.multiblock.large_miner.invfull")
                    .setStyle(Style.EMPTY.withColor(ChatFormatting.RED)));
        if (!drainInput(true))
            textList.add(Component.translatable("gtceu.multiblock.large_miner.needspower")
                    .setStyle(Style.EMPTY.withColor(ChatFormatting.RED)));
    }

    @Override
    protected InteractionResult onScrewdriverClick(Player playerIn, InteractionHand hand, Direction gridSide,
                                                   BlockHitResult hitResult) {
        if (isRemote()) return InteractionResult.SUCCESS;

        if (!this.isActive()) {
            int currentRadius = getWorkLogic().getCurrentRadius();
            if (currentRadius == 1)
                getWorkLogic().setCurrentRadius(getWorkLogic().getMaximumRadius());
            else if (playerIn.isShiftKeyDown())
                getWorkLogic().setCurrentRadius(Math.max(1, Math.round(currentRadius / 2.0f)));
            else
                getWorkLogic().setCurrentRadius(Math.max(1, currentRadius - 1));

            getWorkLogic().resetArea(true);

            int workingArea = IDigitalMiner.getWorkingArea(getWorkLogic().getCurrentRadius());
            playerIn.sendSystemMessage(
                    Component.translatable("gtceu.universal.tooltip.working_area", workingArea, workingArea));
        } else {
            playerIn.sendSystemMessage(Component.translatable("gtceu.multiblock.large_miner.errorradius"));
        }
        return InteractionResult.SUCCESS;
    }

    @NotNull
    @Override
    public List<Component> getDataInfo(PortableScannerBehavior.DisplayMode mode) {
        if (mode == PortableScannerBehavior.DisplayMode.SHOW_ALL ||
                mode == PortableScannerBehavior.DisplayMode.SHOW_MACHINE_INFO) {
            int workingArea = IDigitalMiner.getWorkingArea(getWorkLogic().getCurrentRadius());
            return Collections.singletonList(
                    Component.translatable("gtceu.universal.tooltip.working_area", workingArea, workingArea));
        }
        return new ArrayList<>();
    }
}
