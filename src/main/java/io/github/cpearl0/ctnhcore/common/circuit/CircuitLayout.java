package io.github.cpearl0.ctnhcore.common.circuit;

import net.minecraft.nbt.CompoundTag;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 电路板布局。单元格存储编码：0 = 空；正数 = 元件原点格（值为 CircuitComponent.ordinal() + 1）；
 * 负数 = 被多格元件（芯片）覆盖的扩展格，其朝向与原点一致，据此可反推原点位置。
 * 偶数朝向横向（元件向右延伸），奇数朝向竖向（向下延伸）。
 */
public class CircuitLayout {

    @Getter
    private final SubstrateTier substrate;
    private final byte[] cellComponent;
    private final byte[] cellRotation;

    public CircuitLayout(SubstrateTier substrate) {
        this.substrate = substrate;
        int size = substrate.getGridWidth() * substrate.getGridHeight();
        this.cellComponent = new byte[size];
        this.cellRotation = new byte[size];
    }

    private CircuitLayout(SubstrateTier substrate, byte[] cellComponent, byte[] cellRotation) {
        this.substrate = substrate;
        this.cellComponent = cellComponent;
        this.cellRotation = cellRotation;
    }

    public int width() {
        return substrate.getGridWidth();
    }

    public int height() {
        return substrate.getGridHeight();
    }

    public boolean inBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < width() && y < height();
    }

    private int index(int x, int y) {
        return y * width() + x;
    }

    public boolean isEmptyCell(int x, int y) {
        return inBounds(x, y) && cellComponent[index(x, y)] == 0;
    }

    /** 该格上的元件（含被覆盖的扩展格）；空格返回 null。 */
    @Nullable
    public CircuitComponent componentAt(int x, int y) {
        if (!inBounds(x, y)) return null;
        return CircuitComponent.byCellId(cellComponent[index(x, y)]);
    }

    public boolean isOrigin(int x, int y) {
        return inBounds(x, y) && cellComponent[index(x, y)] > 0;
    }

    public int rotationAt(int x, int y) {
        return cellRotation[index(x, y)] & 3;
    }

    /** 返回该格所属元件的原点坐标；空格返回 null。 */
    @Nullable
    public int[] originOf(int x, int y) {
        if (!inBounds(x, y)) return null;
        byte id = cellComponent[index(x, y)];
        if (id > 0) return new int[] { x, y };
        if (id < 0) {
            int rotation = rotationAt(x, y);
            int ox = CircuitComponent.isHorizontal(rotation) ? x - 1 : x;
            int oy = CircuitComponent.isHorizontal(rotation) ? y : y - 1;
            if (inBounds(ox, oy) && cellComponent[index(ox, oy)] == -id) {
                return new int[] { ox, oy };
            }
        }
        return null;
    }

    /** 元件以 originX/originY 为原点、按朝向占据的所有格子（含原点）。 */
    public static List<int[]> footprint(CircuitComponent component, int originX, int originY, int rotation) {
        List<int[]> cells = new ArrayList<>();
        for (int dx = 0; dx < component.width(rotation); dx++) {
            for (int dy = 0; dy < component.height(rotation); dy++) {
                cells.add(new int[] { originX + dx, originY + dy });
            }
        }
        return cells;
    }

    public boolean canPlace(CircuitComponent component, int x, int y, int rotation) {
        if (!substrate.allows(component)) return false;
        for (int[] cell : footprint(component, x, y, rotation)) {
            if (!inBounds(cell[0], cell[1]) || cellComponent[index(cell[0], cell[1])] != 0) return false;
        }
        return true;
    }

    public boolean place(CircuitComponent component, int x, int y, int rotation) {
        if (!canPlace(component, x, y, rotation)) return false;
        byte id = component.cellId();
        boolean first = true;
        for (int[] cell : footprint(component, x, y, rotation)) {
            cellComponent[index(cell[0], cell[1])] = first ? id : (byte) -id;
            cellRotation[index(cell[0], cell[1])] = (byte) rotation;
            first = false;
        }
        return true;
    }

    /** 旋转该格上的元件 90°；新朝向放不下时不变。 */
    public boolean rotateAt(int x, int y) {
        int[] origin = originOf(x, y);
        if (origin == null) return false;
        CircuitComponent component = componentAt(origin[0], origin[1]);
        if (component == null) return false;
        int oldRotation = rotationAt(origin[0], origin[1]);
        int newRotation = (oldRotation + 1) & 3;
        List<int[]> current = footprint(component, origin[0], origin[1], oldRotation);
        for (int[] cell : footprint(component, origin[0], origin[1], newRotation)) {
            if (!inBounds(cell[0], cell[1])) return false;
            if (cellComponent[index(cell[0], cell[1])] != 0 && !containsCell(current, cell[0], cell[1]))
                return false;
        }
        writeFootprint(component, origin[0], origin[1], newRotation, current);
        return true;
    }

    public boolean removeAt(int x, int y) {
        int[] origin = originOf(x, y);
        if (origin == null) return false;
        CircuitComponent component = componentAt(origin[0], origin[1]);
        if (component == null) return false;
        for (int[] cell : footprint(component, origin[0], origin[1], rotationAt(origin[0], origin[1]))) {
            cellComponent[index(cell[0], cell[1])] = 0;
            cellRotation[index(cell[0], cell[1])] = 0;
        }
        return true;
    }

    private void writeFootprint(CircuitComponent component, int originX, int originY, int rotation,
                                List<int[]> clearFirst) {
        for (int[] cell : clearFirst) {
            cellComponent[index(cell[0], cell[1])] = 0;
            cellRotation[index(cell[0], cell[1])] = 0;
        }
        byte id = component.cellId();
        boolean first = true;
        for (int[] cell : footprint(component, originX, originY, rotation)) {
            cellComponent[index(cell[0], cell[1])] = first ? id : (byte) -id;
            cellRotation[index(cell[0], cell[1])] = (byte) rotation;
            first = false;
        }
    }

    public void clear() {
        Arrays.fill(cellComponent, (byte) 0);
        Arrays.fill(cellRotation, (byte) 0);
    }

    public boolean isEmpty() {
        for (byte b : cellComponent)
            if (b != 0) return false;
        return true;
    }

    public int countComponents() {
        int count = 0;
        for (byte b : cellComponent)
            if (b > 0) count++;
        return count;
    }

    public void forEachOrigin(CellVisitor visitor) {
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                byte id = cellComponent[index(x, y)];
                if (id > 0) {
                    CircuitComponent component = CircuitComponent.byCellId(id);
                    if (component != null) visitor.accept(x, y, component, rotationAt(x, y));
                }
            }
        }
    }

    /** 用于 UI 增量同步的哈希。 */
    public int stateHash() {
        return 31 * Arrays.hashCode(cellComponent) + Arrays.hashCode(cellRotation);
    }

    /** 序列化到网络缓冲（基板 ordinal + 两个原始数组），供 UI 同步使用。 */
    public void writeToBuffer(net.minecraft.network.FriendlyByteBuf buffer) {
        buffer.writeVarInt(substrate.ordinal());
        buffer.writeByteArray(cellComponent);
        buffer.writeByteArray(cellRotation);
    }

    public CompoundTag writeNbt() {
        CompoundTag tag = new CompoundTag();
        tag.putString("substrate", substrate.getId());
        int[] packed = new int[cellComponent.length];
        for (int i = 0; i < packed.length; i++) {
            packed[i] = (cellComponent[i] & 0xFF) | ((cellRotation[i] & 3) << 8);
        }
        tag.putIntArray("cells", packed);
        return tag;
    }

    @Nullable
    public static CircuitLayout fromNbt(CompoundTag tag) {
        SubstrateTier substrate = SubstrateTier.byId(tag.getString("substrate"));
        if (substrate == null) return null;
        int size = substrate.getGridWidth() * substrate.getGridHeight();
        int[] packed = tag.getIntArray("cells");
        if (packed.length != size) return null;
        byte[] comp = new byte[size];
        byte[] rot = new byte[size];
        for (int i = 0; i < size; i++) {
            comp[i] = (byte) (packed[i] & 0xFF);
            rot[i] = (byte) ((packed[i] >> 8) & 3);
        }
        return new CircuitLayout(substrate, comp, rot);
    }

    private static boolean containsCell(List<int[]> cells, int x, int y) {
        for (int[] cell : cells)
            if (cell[0] == x && cell[1] == y) return true;
        return false;
    }

    @FunctionalInterface
    public interface CellVisitor {

        void accept(int x, int y, CircuitComponent component, int rotation);
    }
}
