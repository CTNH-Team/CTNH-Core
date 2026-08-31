package io.github.cpearl0.ctnhcore.common.gui.circuit;

import io.github.cpearl0.ctnhcore.common.circuit.CircuitStats;
import io.github.cpearl0.ctnhcore.common.circuit.CircuitTraits;
import io.github.cpearl0.ctnhcore.common.machine.simple.CircuitDesignBenchMachine;

import com.gregtechceu.gtceu.api.GTValues;

import com.lowdragmc.lowdraglib.gui.util.DrawerHelper;
import com.lowdragmc.lowdraglib.gui.widget.Widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import org.jetbrains.annotations.NotNull;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.ArrayList;
import java.util.List;

/**
 * 设计台右侧属性面板（紧凑文本版）：速度/能效/稳定/并行/复杂度/等效 tier、
 * 警告（降额/拥塞/噪声）与词条（§4.6，"·"连接自动换行）。
 * 数值由服务端结算器计算，经同步包推送到客户端镜像。
 */
public class CircuitStatsWidget extends Widget {

    @CN("过热降额：%d 个元件")
    @EN("Overheating: %d component(s) derated")
    @Key("ctnhcore.circuit.derated_warn")
    public static Lang deratedWarn;

    @CN("布线拥塞：%d 处")
    @EN("Congested: %d cluster(s)")
    @Key("ctnhcore.circuit.congestion_warn")
    public static Lang congestionWarn;

    @CN("噪声超标：%d 点")
    @EN("Noise exceeded: %d point(s)")
    @Key("ctnhcore.circuit.noise_warn")
    public static Lang noiseWarn;

    private static final int SYNC_STATS = 0;

    private final CircuitDesignBenchMachine machine;

    private CircuitStats mirror = CircuitStats.EMPTY;
    private int mirrorDerated;
    private int mirrorCongested;
    private int mirrorNoiseExcess;
    private int lastHash = Integer.MIN_VALUE;

    public CircuitStatsWidget(int x, int y, CircuitDesignBenchMachine machine) {
        super(x, y, 96, 104);
        this.machine = machine;
    }

    @Override
    public void writeInitialData(@NotNull FriendlyByteBuf buffer) {
        writeStats(buffer);
    }

    @Override
    public void readInitialData(@NotNull FriendlyByteBuf buffer) {
        readStats(buffer);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        int hash = machine.getStats().hashCode() * 31 + machine.getLastDerated() * 7 + machine.getLastCongested() +
                machine.getLastNoiseExcess() * 13;
        if (hash != lastHash) {
            lastHash = hash;
            writeUpdateInfo(SYNC_STATS, this::writeStats);
        }
    }

    @Override
    public void readUpdateInfo(int id, @NotNull FriendlyByteBuf buffer) {
        if (id == SYNC_STATS) {
            readStats(buffer);
            return;
        }
        super.readUpdateInfo(id, buffer);
    }

    private void writeStats(FriendlyByteBuf buffer) {
        CircuitStats stats = machine.getStats();
        buffer.writeVarInt(stats.tier());
        buffer.writeVarInt(stats.speed());
        buffer.writeVarInt(stats.efficiency());
        buffer.writeVarInt(stats.stability());
        buffer.writeVarInt(stats.parallel());
        buffer.writeVarInt(stats.complexity());
        buffer.writeVarInt(machine.getLastDerated());
        buffer.writeVarInt(machine.getLastCongested());
        buffer.writeVarInt(machine.getLastNoiseExcess());
        buffer.writeVarInt(stats.traits().size());
        for (String trait : stats.traits()) {
            buffer.writeUtf(trait);
        }
    }

    private void readStats(FriendlyByteBuf buffer) {
        int tier = buffer.readVarInt();
        mirror = new CircuitStats(tier, buffer.readVarInt(), buffer.readVarInt(), buffer.readVarInt(),
                buffer.readVarInt(), buffer.readVarInt(), List.of());
        mirrorDerated = buffer.readVarInt();
        mirrorCongested = buffer.readVarInt();
        mirrorNoiseExcess = buffer.readVarInt();
        int traitCount = buffer.readVarInt();
        List<String> traits = new ArrayList<>(traitCount);
        for (int i = 0; i < traitCount; i++) {
            traits.add(buffer.readUtf());
        }
        mirror = new CircuitStats(tier, mirror.speed(), mirror.efficiency(), mirror.stability(), mirror.parallel(),
                mirror.complexity(), List.copyOf(traits));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void drawInBackground(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        super.drawInBackground(graphics, mouseX, mouseY, partialTicks);
        int x = getPosition().x;
        int y = getPosition().y;
        DrawerHelper.drawSolidRect(graphics, x, y, getSize().width, getSize().height, 0x80101018);
        var font = Minecraft.getInstance().font;
        int line = y + 4;
        graphics.drawString(font, CircuitStats.speedLabel.translate().getString() + " " + mirror.speed() + "%", x + 4,
                line, 0xFF4CAF50, false);
        line += 10;
        graphics.drawString(font,
                CircuitStats.efficiencyLabel.translate().getString() + " " + mirror.efficiency() + "%",
                x + 4, line, 0xFF2196F3, false);
        line += 10;
        graphics.drawString(font, CircuitStats.stabilityLabel.translate().getString() + " " + mirror.stability() + "%",
                x + 4, line, 0xFFFFC107, false);
        line += 10;
        graphics.drawString(font,
                CircuitStats.parallelLabel.translate().getString() + " " + mirror.parallel() + " · " +
                        CircuitStats.complexityLabel.translate().getString() + " " + mirror.complexity(),
                x + 4, line,
                0xFFFFFFFF, false);
        line += 10;
        graphics.drawString(font, CircuitStats.equivalentTier.translate(GTValues.VN[mirror.tier()]),
                x + 4, line, 0xFFB39DDB, false);
        line += 12;
        // 警告（R7 降额 / R6 拥塞 / R4 噪声）
        if (mirrorDerated > 0) {
            graphics.drawString(font, deratedWarn.translate(mirrorDerated), x + 4, line,
                    0xFFFF5252, false);
            line += 10;
        }
        if (mirrorCongested > 0) {
            graphics.drawString(font, congestionWarn.translate(mirrorCongested), x + 4,
                    line, 0xFFFFB300, false);
            line += 10;
        }
        if (mirrorNoiseExcess > 0) {
            graphics.drawString(font, noiseWarn.translate(mirrorNoiseExcess), x + 4, line,
                    0xFFFF5252, false);
            line += 10;
        }
        // 词条（§4.6，"·"连接自动换行）
        if (!mirror.traits().isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String trait : mirror.traits()) {
                if (sb.length() > 0) sb.append(" · ");
                sb.append(CircuitTraits.displayName(trait).getString());
            }
            int maxLines = (y + getSize().height - 4 - line) / 10;
            var lines = font.split(Component.literal(sb.toString()), getSize().width - 8);
            for (int i = 0; i < lines.size() && i < maxLines; i++) {
                graphics.drawString(font, lines.get(i), x + 4, line, 0xFF80DEEA, false);
                line += 10;
            }
        }
    }
}
