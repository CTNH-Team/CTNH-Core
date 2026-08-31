package io.github.cpearl0.ctnhcore.common.item;

import io.github.cpearl0.ctnhcore.common.circuit.CircuitStats;
import io.github.cpearl0.ctnhcore.common.circuit.SubstrateTier;
import io.github.cpearl0.ctnhcore.registry.CTNHCircuitItems;

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

import java.util.List;

/**
 * 自定义电路板（成品）：由电路组装机按图纸生产，NBT 记录基板、四项属性、并行/复杂度、
 * 词条与等效电压 tier（CircuitStats.tier，§4.3 复杂度映射）。
 * 单块品质 = 图纸 stats × N(1, σ) 已在产出时掷定（§六），这里的 NBT 即最终值。
 */
public class CustomCircuitBoardItem extends Item {

    @CN("未标定参数的电路板")
    @EN("Unrated circuit board")
    @Key("ctnhcore.circuit.board_no_stats")
    public static Lang boardNoStats;

    public CustomCircuitBoardItem(Properties properties) {
        super(properties.stacksTo(64));
    }

    public static ItemStack create(SubstrateTier substrate, CircuitStats stats) {
        ItemStack stack = CTNHCircuitItems.CUSTOM_CIRCUIT_BOARD.asStack();
        CompoundTag tag = stack.getOrCreateTag();
        tag.putString("substrate", substrate.getId());
        tag.put("stats", stats.toNbt());
        return stack;
    }

    @Nullable
    public static CircuitStats readStats(ItemStack stack) {
        if (!(stack.getItem() instanceof CustomCircuitBoardItem)) return null;
        CompoundTag tag = stack.getTag();
        if (tag == null || !tag.contains("stats")) return null;
        return CircuitStats.fromNbt(tag.getCompound("stats"));
    }

    @Nullable
    public static SubstrateTier readSubstrate(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        return tag == null ? null : SubstrateTier.byId(tag.getString("substrate"));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip,
                                TooltipFlag flag) {
        SubstrateTier substrate = readSubstrate(stack);
        CircuitStats stats = readStats(stack);
        if (substrate == null || stats == null) {
            tooltip.add(boardNoStats.translate().withStyle(ChatFormatting.GRAY));
            return;
        }
        CircuitBlueprintItem.appendStatsTooltip(tooltip, substrate, stats);
    }
}
