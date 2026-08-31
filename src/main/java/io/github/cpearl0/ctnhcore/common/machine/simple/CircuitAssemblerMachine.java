package io.github.cpearl0.ctnhcore.common.machine.simple;

import io.github.cpearl0.ctnhcore.common.circuit.CircuitStats;
import io.github.cpearl0.ctnhcore.common.circuit.CircuitTraits;
import io.github.cpearl0.ctnhcore.common.circuit.SubstrateTier;
import io.github.cpearl0.ctnhcore.common.item.CircuitBlueprintItem;
import io.github.cpearl0.ctnhcore.common.item.CustomCircuitBoardItem;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.TieredEnergyMachine;
import com.gregtechceu.gtceu.api.machine.feature.IFancyUIMachine;

import com.lowdragmc.lowdraglib.gui.texture.IGuiTexture;
import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;
import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.ProgressWidget;
import com.lowdragmc.lowdraglib.gui.widget.SlotWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.misc.ItemStackTransfer;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import org.jetbrains.annotations.Nullable;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.List;

/**
 * 自定义电路组装机：读取电路图纸（不消耗），按图纸材料清单消耗基板与元件，
 * 逐块掷良品率产出 NBT 自定义电路板（设计文档 §六）。
 * 良品率 = min(95% + 2%×机器超基准 tier, 45% + 0.4×稳定) + 「保护设计」8%；
 * 单块品质 = 图纸 stats × N(1, σ)，σ = max(0.02, 0.12 − 稳定×0.0005)。
 * 耗电/耗时按基板电压 tier：EUt = VA[基板 tier]/4，时长 200t；机器 tier 不足时拒绝组装。
 */
public class CircuitAssemblerMachine extends TieredEnergyMachine implements IFancyUIMachine {

    @CN("待机：请放入电路图纸")
    @EN("Idle: insert a blueprint")
    @Key("ctnhcore.circuit.asm_no_blueprint")
    public static Lang asmNoBlueprint;

    @CN("机器电压等级不足（需要 %s 级）")
    @EN("Machine tier too low (needs %s)")
    @Key("ctnhcore.circuit.asm_tier_low")
    public static Lang asmTierLow;

    @CN("材料不足")
    @EN("Insufficient materials")
    @Key("ctnhcore.circuit.asm_no_material")
    public static Lang asmNoMaterial;

    @CN("输出已满")
    @EN("Output full")
    @Key("ctnhcore.circuit.asm_output_full")
    public static Lang asmOutputFull;

    @CN("电力不足")
    @EN("Insufficient power")
    @Key("ctnhcore.circuit.asm_no_power")
    public static Lang asmNoPower;

    @CN("组装中… %d%%")
    @EN("Assembling... %d%%")
    @Key("ctnhcore.circuit.asm_working")
    public static Lang asmWorking;

    @CN("放入电路图纸（不会消耗）")
    @EN("Insert the circuit blueprint (not consumed)")
    @Key("ctnhcore.circuit.asm_slot_blueprint")
    public static Lang asmSlotBlueprint;

    @CN("按图纸放入基板与元件")
    @EN("Substrates & components per blueprint")
    @Key("ctnhcore.circuit.asm_slot_material")
    public static Lang asmSlotMaterial;

    private static final int STATUS_NO_BLUEPRINT = 0;
    private static final int STATUS_TIER_TOO_LOW = 1;
    private static final int STATUS_NO_MATERIAL = 2;
    private static final int STATUS_OUTPUT_FULL = 3;
    private static final int STATUS_NO_POWER = 4;
    private static final int STATUS_WORKING = 5;

    private static final int DURATION = 200;

    @Persisted
    protected final ItemStackTransfer blueprintSlot;
    @Persisted
    protected final ItemStackTransfer materialInventory;
    @Persisted
    protected final ItemStackTransfer outputInventory;

    @Persisted
    @DescSynced
    protected int progress;
    @DescSynced
    protected int status;
    @DescSynced
    protected int requiredTier;

    @Nullable
    protected TickableSubscription processSub;
    /** 材料清单缓存：按图纸栈引用缓存，避免每 tick 重算。 */
    @Nullable
    private ItemStack billSource;
    private List<ItemStack> cachedBill = List.of();

    public CircuitAssemblerMachine(IMachineBlockEntity holder, int tier) {
        super(holder, tier);
        blueprintSlot = new ItemStackTransfer(1);
        blueprintSlot.setFilter(stack -> CircuitBlueprintItem.read(stack) != null);
        materialInventory = new ItemStackTransfer(9);
        outputInventory = new ItemStackTransfer(2);
        outputInventory.setFilter(stack -> false);
    }

    //////////////////////////////////////
    // ***** 组装逻辑（§六） *****//
    //////////////////////////////////////

    @Override
    public void onLoad() {
        super.onLoad();
        if (!isRemote()) {
            processSub = subscribeServerTick(this::assemblyTick);
        }
    }

    @Override
    public void onUnload() {
        super.onUnload();
        if (processSub != null) {
            processSub.unsubscribe();
            processSub = null;
        }
    }

    protected void assemblyTick() {
        ItemStack blueprintStack = blueprintSlot.getStackInSlot(0);
        CircuitBlueprintItem.BlueprintData data = CircuitBlueprintItem.read(blueprintStack);
        if (data == null) {
            setStatus(STATUS_NO_BLUEPRINT);
            progress = 0;
            return;
        }
        SubstrateTier substrate = data.layout().getSubstrate();
        requiredTier = substrate.getVoltageTier();
        if (getTier() < requiredTier) {
            setStatus(STATUS_TIER_TOO_LOW);
            progress = 0;
            return;
        }
        List<ItemStack> bill = billOf(blueprintStack, data);
        if (!hasMaterials(bill)) {
            setStatus(STATUS_NO_MATERIAL);
            progress = 0;
            return;
        }
        if (outputFull()) {
            setStatus(STATUS_OUTPUT_FULL); // 保留进度等待取货
            return;
        }
        long eut = GTValues.VA[requiredTier] / 4;
        if (energyContainer.getEnergyStored() < eut) {
            setStatus(STATUS_NO_POWER); // 保留进度等待供电
            return;
        }
        energyContainer.removeEnergy(eut);
        setStatus(STATUS_WORKING);
        if (++progress >= DURATION) {
            progress = 0;
            consumeMaterials(bill);
            rollAndOutput(data);
        }
    }

    private void setStatus(int value) {
        if (status != value) {
            status = value;
        }
    }

    private List<ItemStack> billOf(ItemStack blueprintStack, CircuitBlueprintItem.BlueprintData data) {
        if (billSource != blueprintStack) {
            billSource = blueprintStack;
            cachedBill = CircuitBlueprintItem.materialBill(data.layout());
        }
        return cachedBill;
    }

    private boolean hasMaterials(List<ItemStack> bill) {
        for (ItemStack required : bill) {
            int remaining = required.getCount();
            for (int slot = 0; slot < materialInventory.getSlots() && remaining > 0; slot++) {
                ItemStack stored = materialInventory.getStackInSlot(slot);
                if (!stored.isEmpty() && ItemStack.isSameItem(stored, required)) {
                    remaining -= stored.getCount();
                }
            }
            if (remaining > 0) return false;
        }
        return true;
    }

    private void consumeMaterials(List<ItemStack> bill) {
        for (ItemStack required : bill) {
            int remaining = required.getCount();
            for (int slot = 0; slot < materialInventory.getSlots() && remaining > 0; slot++) {
                ItemStack stored = materialInventory.getStackInSlot(slot);
                if (!stored.isEmpty() && ItemStack.isSameItem(stored, required)) {
                    remaining -= materialInventory.extractItem(slot, remaining, false).getCount();
                }
            }
        }
    }

    private boolean outputFull() {
        for (int slot = 0; slot < outputInventory.getSlots(); slot++) {
            if (outputInventory.getStackInSlot(slot).isEmpty()) return false;
        }
        return true;
    }

    /** 良品判定 + 品质波动，产出单块自定义电路板。 */
    private void rollAndOutput(CircuitBlueprintItem.BlueprintData data) {
        CircuitStats stats = data.stats();
        double yieldChance = Math.min(0.95 + 0.02 * (getTier() - requiredTier), 0.45 + 0.004 * stats.stability()) +
                (stats.traits().contains(CircuitTraits.PROTECTION) ? 0.08 : 0);
        if (getLevel().random.nextDouble() >= yieldChance) return; // 不良品：材料损耗
        double sigma = Math.max(0.02, 0.12 - stats.stability() * 0.0005);
        double factor = Mth.clamp(1 + getLevel().random.nextGaussian() * sigma, 0.5, 1.5);
        CircuitStats rolled = new CircuitStats(stats.tier(),
                Mth.clamp((int) Math.round(stats.speed() * factor), 50, 180),
                Mth.clamp((int) Math.round(stats.efficiency() * factor), 40, 130),
                Mth.clamp((int) Math.round(stats.stability() * factor), 20, 200),
                stats.parallel(), stats.complexity(), stats.traits());
        ItemStack board = CustomCircuitBoardItem.create(data.layout().getSubstrate(), rolled);
        for (int slot = 0; slot < outputInventory.getSlots(); slot++) {
            if (outputInventory.getStackInSlot(slot).isEmpty()) {
                outputInventory.setStackInSlot(slot, board);
                return;
            }
        }
    }

    //////////////////////////////////////
    // ***** UI *****//
    //////////////////////////////////////

    @Override
    public Widget createUIWidget() {
        WidgetGroup group = new WidgetGroup(0, 24, 176, 60);
        SlotWidget blueprint = new SlotWidget(blueprintSlot, 0, 8, 8, true, true);
        blueprint.setBackground(GuiTextures.SLOT);
        blueprint.setHoverTooltips(asmSlotBlueprint.translate());
        group.addWidget(blueprint);
        for (int i = 0; i < 9; i++) {
            SlotWidget slot = new SlotWidget(materialInventory, i, 44 + (i % 3) * 18, 2 + (i / 3) * 18, true, true);
            slot.setBackground(GuiTextures.SLOT);
            if (i == 0) {
                slot.setHoverTooltips(asmSlotMaterial.translate());
            }
            group.addWidget(slot);
        }
        for (int i = 0; i < 2; i++) {
            group.addWidget(new SlotWidget(outputInventory, i, 124, 8 + i * 22, true, false)
                    .setBackground(GuiTextures.SLOT));
        }
        ProgressWidget energyBar = new ProgressWidget(ProgressWidget.JEIProgress, 150, 4, 10, 54,
                new ProgressTexture(IGuiTexture.EMPTY, GuiTextures.ENERGY_BAR_BASE));
        energyBar.setFillDirection(ProgressTexture.FillDirection.DOWN_TO_UP);
        energyBar.setBackground(GuiTextures.ENERGY_BAR_BACKGROUND);
        energyBar.setProgressSupplier(
                () -> energyContainer.getEnergyStored() * 1d / energyContainer.getEnergyCapacity());
        group.addWidget(energyBar);
        group.addWidget(new LabelWidget(8, 58, this::statusText).setTextColor(0xFFFFFF));
        return group;
    }

    private String statusText() {
        return switch (status) {
            case STATUS_TIER_TOO_LOW -> asmTierLow.translate(GTValues.VN[requiredTier]).getString();
            case STATUS_NO_MATERIAL -> asmNoMaterial.translate().getString();
            case STATUS_OUTPUT_FULL -> asmOutputFull.translate().getString();
            case STATUS_NO_POWER -> asmNoPower.translate().getString();
            case STATUS_WORKING -> asmWorking.translate(progress * 100 / DURATION).getString();
            default -> asmNoBlueprint.translate().getString();
        };
    }
}
