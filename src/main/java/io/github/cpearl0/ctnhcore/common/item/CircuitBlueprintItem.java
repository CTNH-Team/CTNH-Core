package io.github.cpearl0.ctnhcore.common.item;

import io.github.cpearl0.ctnhcore.common.circuit.CircuitLayout;
import io.github.cpearl0.ctnhcore.common.circuit.CircuitStats;
import io.github.cpearl0.ctnhcore.common.circuit.CircuitTraits;
import io.github.cpearl0.ctnhcore.common.circuit.SubstrateTier;
import io.github.cpearl0.ctnhcore.registry.CTNHCircuitItems;

import com.gregtechceu.gtceu.api.GTValues;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import org.jetbrains.annotations.Nullable;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 电路图纸：NBT 记录基板、元件布局、结算属性与设计者。
 * 结构见 docs/design/circuit-board-system.md §五。
 */
public class CircuitBlueprintItem extends Item {

    @CN("空白图纸（请在电路板设计台中导出）")
    @EN("Blank blueprint (export from a Circuit Design Bench)")
    @Key("ctnhcore.circuit.blueprint_empty")
    public static Lang blueprintEmpty;

    @CN("每块所需材料：")
    @EN("Materials per board:")
    @Key("ctnhcore.circuit.tooltip_materials")
    public static Lang tooltipMaterials;

    @CN("设计者：%s")
    @EN("Designer: %s")
    @Key("ctnhcore.circuit.tooltip_designer")
    public static Lang tooltipDesigner;

    @CN("预计良品率：%d%%")
    @EN("Estimated yield: %d%%")
    @Key("ctnhcore.circuit.asm_yield_tip")
    public static Lang yieldTip;

    public CircuitBlueprintItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    public record BlueprintData(CircuitLayout layout, CircuitStats stats, String designer) {}

    public static ItemStack create(CircuitLayout layout, CircuitStats stats, String designer) {
        ItemStack stack = CTNHCircuitItems.CIRCUIT_BLUEPRINT.asStack();
        CompoundTag tag = stack.getOrCreateTag();
        tag.put("layout", layout.writeNbt());
        tag.put("stats", stats.toNbt());
        tag.putString("designer", designer);
        return stack;
    }

    @Nullable
    public static BlueprintData read(ItemStack stack) {
        if (!(stack.getItem() instanceof CircuitBlueprintItem)) return null;
        CompoundTag tag = stack.getTag();
        if (tag == null || !tag.contains("layout")) return null;
        CircuitLayout layout = CircuitLayout.fromNbt(tag.getCompound("layout"));
        if (layout == null) return null;
        return new BlueprintData(layout, CircuitStats.fromNbt(tag.getCompound("stats")),
                tag.getString("designer"));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip,
                                TooltipFlag flag) {
        BlueprintData data = read(stack);
        if (data == null) {
            tooltip.add(blueprintEmpty.translate().withStyle(ChatFormatting.GRAY));
            return;
        }
        appendStatsTooltip(tooltip, data.layout().getSubstrate(), data.stats());
        tooltip.add(tooltipMaterials.translate().withStyle(ChatFormatting.DARK_GRAY));
        for (ItemStack material : materialBill(data.layout())) {
            tooltip.add(Component.literal("  ")
                    .append(Component.translatable(material.getDescriptionId()))
                    .append(" ×" + material.getCount())
                    .withStyle(ChatFormatting.DARK_GRAY));
        }
        // §六基准良品率（不含机器超档加成）
        int yieldPct = (int) Math.round(100 * (Math.min(0.95, 0.45 + 0.004 * data.stats().stability()) +
                (data.stats().traits().contains(CircuitTraits.PROTECTION) ? 0.08 : 0)));
        tooltip.add(yieldTip.translate(yieldPct).withStyle(ChatFormatting.GRAY));
        if (!data.designer().isEmpty()) {
            tooltip.add(tooltipDesigner.translate(data.designer())
                    .withStyle(ChatFormatting.DARK_GRAY));
        }
    }

    /** 图纸与成品电路板共用的属性提示行。 */
    public static void appendStatsTooltip(List<Component> tooltip, SubstrateTier substrate, CircuitStats stats) {
        tooltip.add(CircuitStats.tooltipSubstrate
                .translate(Component.translatable(substrate.item().get().getDescriptionId()))
                .withStyle(ChatFormatting.GRAY));
        tooltip.add(CircuitStats.tooltipStats
                .translate(stats.speed(), stats.efficiency(), stats.stability(), stats.parallel())
                .withStyle(ChatFormatting.GRAY));
        tooltip.add(CircuitStats.tooltipComplexity.translate(stats.complexity())
                .withStyle(ChatFormatting.GRAY));
        tooltip.add(CircuitStats.equivalentTier.translate(GTValues.VN[stats.tier()])
                .withStyle(ChatFormatting.GRAY));
        for (String trait : stats.traits()) {
            tooltip.add(CircuitTraits.displayName(trait).withStyle(ChatFormatting.AQUA));
        }
    }

    /**
     * 每块成品板的材料清单：基板 ×1 + 布局内各元件按其 GTM/CTNH 物品计数（§六组装机消耗依据）。
     * 返回的 ItemStack 仅作计数载体，不含 NBT。
     */
    public static List<ItemStack> materialBill(CircuitLayout layout) {
        Map<Item, Integer> counts = new LinkedHashMap<>();
        layout.forEachOrigin((x, y, component, rotation) -> counts.merge(component.item().get(), 1, Integer::sum));
        List<ItemStack> bill = new ArrayList<>();
        bill.add(layout.getSubstrate().item().asStack());
        counts.forEach((item, count) -> bill.add(new ItemStack(item, count)));
        return bill;
    }
}
