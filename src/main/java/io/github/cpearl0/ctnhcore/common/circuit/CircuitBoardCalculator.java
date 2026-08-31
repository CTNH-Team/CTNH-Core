package io.github.cpearl0.ctnhcore.common.circuit;

import io.github.cpearl0.ctnhcore.common.circuit.CircuitComponent.Family;

import com.gregtechceu.gtceu.api.GTValues;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 布局 → 属性结算器（设计文档 §四 五步管线）：
 * ① 元件基础值求和 → ② 热场 + 噪声场模拟 → ③ 邻接规则 R1-R7 → ④ 词条判定 → ⑤ 钳位。
 * 邻接规则按元件族（Family）判定：SMD/高级 SMD 升级件与同族基础件等价参与；
 * 拥塞（R6）则按精确型号判定，鼓励混用升级件。
 * 待实现：IO 连通性与多层板（§七 EV+）；「保护设计」的良品率加成在组装机（§六）生效。
 */
public final class CircuitBoardCalculator {

    /** 结算结果：属性 + UI 警告信息（过热降额件数 / 拥塞团数 / 噪声超标点数）。 */
    public record Result(CircuitStats stats, int derated, int congested, int noiseExcess) {}

    private record Cell(int x, int y, CircuitComponent component, int rotation) {}

    public static CircuitStats compute(CircuitLayout layout) {
        return evaluate(layout).stats();
    }

    public static Result evaluate(CircuitLayout layout) {
        SubstrateTier substrate = layout.getSubstrate();
        int w = layout.width();
        int h = layout.height();
        List<Cell> cells = new ArrayList<>();
        layout.forEachOrigin((x, y, c, r) -> cells.add(new Cell(x, y, c, r)));

        // ② 热场：辐射衰减 自身100% / 正交50% / 斜角25%；散热片为负热源（-4/-2/-1）
        double[] heat = new double[w * h];
        int totalHeat = 0;
        for (Cell cell : cells) {
            radiateHeat(heat, w, h, cell.x(), cell.y(), cell.component().getHeat());
            if (cell.component().getFamily() != Family.HEATSINK) {
                totalHeat += Math.max(0, cell.component().getHeat());
            }
        }
        // ② 噪声场：辐射衰减 自身100% / 曼哈顿距离1 → 50% / 距离2 → 25%
        double[] noise = new double[w * h];
        int noiseSources = 0;
        for (Cell cell : cells) {
            int noiseValue = cell.component().getNoise();
            if (noiseValue > 0) {
                noiseSources++;
                radiateNoise(noise, w, h, cell.x(), cell.y(), noiseValue);
            }
        }

        double warn = substrate.warnThreshold();
        double crit = substrate.critThreshold();
        int derated = 0;
        int deratedChips = 0;
        int complexity = 0;
        int transistors = 0;
        int chips = 0;
        int oscillators = 0;
        int heatsinks = 0;
        List<Double> oscillatorEfficiency = new ArrayList<>();
        double noiseExcessTotal = 0;
        int sensitiveCount = 0;
        for (Cell cell : cells) {
            // 热降额（按原点格）
            double cellHeat = heat[cell.y() * w + cell.x()];
            double heatFactor = cellHeat > crit ? 0.5 : cellHeat > warn ? 0.85 : 1.0;
            if (heatFactor == 0.5) {
                derated++;
                if (cell.component().getFamily() == Family.CHIP) deratedChips++;
            }
            complexity += Math.round(cell.component().getFunc() * heatFactor);
            switch (cell.component().getFamily()) {
                case TRANSISTOR -> transistors++;
                case CHIP -> chips++;
                case OSCILLATOR -> oscillators++;
                case HEATSINK -> heatsinks++;
                default -> {}
            }
            // 噪声敏感：晶振容忍 2、芯片族容忍 3（按占地取最大噪声）
            int tolerance = switch (cell.component().getFamily()) {
                case OSCILLATOR -> 2;
                case CHIP -> 3;
                default -> -1;
            };
            if (tolerance >= 0) {
                sensitiveCount++;
                double received = maxNoiseOnFootprint(noise, w, h, cell);
                double excess = Math.max(0, received - tolerance);
                noiseExcessTotal += excess;
                if (cell.component().getFamily() == Family.OSCILLATOR) {
                    oscillatorEfficiency.add(heatFactor * Math.max(0, 1 - 0.25 * excess));
                }
            }
        }

        // ③ 邻接规则（§4.5）
        int rcPairs = Math.min(countRcPairs(layout), 5); // R1
        int decoupling = decouplingBonus(layout); // R2
        int lcGroups = Math.min(countLcGroups(layout), 2); // R3
        int arrays = Math.min(countTransistorArrays(layout), 2); // R5
        int congested = countCongestionClusters(layout); // R6
        int noiseExcess = (int) Math.round(noiseExcessTotal); // R4：-6 稳定 / 点

        // ③→④ 先加算（规则），再乘算/特判（词条），最后钳位（§4.0）
        oscillatorEfficiency.sort((a, b) -> Double.compare(b, a));
        double oscillatorTerm = 0;
        for (int i = 0; i < Math.min(2, oscillatorEfficiency.size()); i++) {
            oscillatorTerm += oscillatorEfficiency.get(i);
        }
        double speed = 100 + 2 * Math.min(transistors, 5) + 8 * oscillatorTerm + 10 * lcGroups - 10 * deratedChips;
        double efficiency = 115 - totalHeat - 3 * derated - 5 * congested;
        double stability = 100 + 8 * rcPairs + decoupling + 12 * lcGroups - 6 * noiseExcessTotal - 5 * congested -
                5 * derated;

        // ④ 词条（§4.6）
        List<String> traits = new ArrayList<>();
        if (rcPairs >= 3) {
            traits.add(CircuitTraits.RC_FILTER);
            stability *= 1.08;
        }
        double minOscillatorEff = oscillatorEfficiency.stream().mapToDouble(Double::doubleValue).min().orElse(0);
        if (lcGroups >= 1 && !oscillatorEfficiency.isEmpty() && minOscillatorEff >= 0.9) {
            traits.add(CircuitTraits.HIGH_FREQUENCY);
            speed *= 1.10;
            stability *= 0.90;
        } else if (noiseSources > 0 && sensitiveCount > 0 && noiseExcessTotal <= 0) {
            // ⚡ 与 🛡 互斥：高频优先；抗干扰要求存在噪声源与敏感件且全场无超标
            traits.add(CircuitTraits.ANTI_INTERFERENCE);
            stability *= 1.15;
            speed *= 0.95;
        }
        if (hasProtectedChip(layout)) {
            traits.add(CircuitTraits.PROTECTION); // 良品率 +8%：§六组装机生效
        }
        if (hasAdjacentChipPair(layout)) {
            traits.add(CircuitTraits.REDUNDANCY);
            stability += 10; // 并行惩罚（并行计数只算 1）留待 §七多层/连通性
        }
        if (heatsinks > 0 && derated == 0) {
            traits.add(CircuitTraits.THERMAL);
            efficiency += 8;
        }

        // ⑤ 钳位
        int speedI = clamp((int) Math.round(speed), 50, 180);
        int efficiencyI = clamp((int) Math.round(efficiency), 40, 130);
        int stabilityI = clamp((int) Math.round(stability), 20, 200);
        int parallel = Math.min(1 + arrays, Math.min(substrate.getParallelCap(), 8));
        int tier = equivalentTier(substrate, complexity, transistors, chips, oscillators);

        return new Result(new CircuitStats(tier, speedI, efficiencyI, stabilityI, parallel, complexity,
                List.copyOf(traits)), derated, congested, noiseExcess);
    }

    /**
     * §4.3 复杂度 → 等效 GT 电路 tier：数值门槛与元件门槛需同时满足（防堆电阻刷 tier）。
     * EV 档要求多层基板（文档"双层板"在 v1 以多层基板物品代替，过孔层留待 §七）。
     */
    private static int equivalentTier(SubstrateTier substrate, int complexity, int transistors, int chips,
                                      int oscillators) {
        if (complexity >= 75 && chips >= 2 && substrate.ordinal() >= SubstrateTier.MULTILAYER.ordinal()) {
            return GTValues.EV;
        }
        if (complexity >= 40 && chips >= 1 && oscillators >= 1) return GTValues.HV;
        if (complexity >= 18 && transistors >= 2) return GTValues.MV;
        if (complexity >= 6) return GTValues.LV;
        return GTValues.ULV;
    }

    //////////////////////////////////////
    // ***** 场模拟 *****//
    //////////////////////////////////////

    private static void radiateHeat(double[] heat, int w, int h, int x, int y, double value) {
        if (value == 0) return;
        addField(heat, w, h, x, y, value);
        addField(heat, w, h, x - 1, y, value * 0.5);
        addField(heat, w, h, x + 1, y, value * 0.5);
        addField(heat, w, h, x, y - 1, value * 0.5);
        addField(heat, w, h, x, y + 1, value * 0.5);
        addField(heat, w, h, x - 1, y - 1, value * 0.25);
        addField(heat, w, h, x + 1, y - 1, value * 0.25);
        addField(heat, w, h, x - 1, y + 1, value * 0.25);
        addField(heat, w, h, x + 1, y + 1, value * 0.25);
    }

    private static void radiateNoise(double[] noise, int w, int h, int x, int y, double value) {
        if (value <= 0) return;
        addField(noise, w, h, x, y, value);
        for (int[] dir : DIRECTIONS) {
            addField(noise, w, h, x + dir[0], y + dir[1], value * 0.5);
        }
        // 曼哈顿距离 2：直两格 + 斜角
        addField(noise, w, h, x - 2, y, value * 0.25);
        addField(noise, w, h, x + 2, y, value * 0.25);
        addField(noise, w, h, x, y - 2, value * 0.25);
        addField(noise, w, h, x, y + 2, value * 0.25);
        addField(noise, w, h, x - 1, y - 1, value * 0.25);
        addField(noise, w, h, x + 1, y - 1, value * 0.25);
        addField(noise, w, h, x - 1, y + 1, value * 0.25);
        addField(noise, w, h, x + 1, y + 1, value * 0.25);
    }

    private static void addField(double[] field, int w, int h, int x, int y, double value) {
        if (x < 0 || y < 0 || x >= w || y >= h) return;
        field[y * w + x] += value;
    }

    private static double maxNoiseOnFootprint(double[] noise, int w, int h, Cell cell) {
        double max = 0;
        for (int[] c : CircuitLayout.footprint(cell.component(), cell.x(), cell.y(), cell.rotation())) {
            max = Math.max(max, noise[c[1] * w + c[0]]);
        }
        return max;
    }

    //////////////////////////////////////
    // ***** 邻接规则 *****//
    //////////////////////////////////////

    /** R1：电阻族与电容族正交相邻成对（每个电阻/电容最多参与一对）。 */
    private static int countRcPairs(CircuitLayout layout) {
        int w = layout.width();
        boolean[] usedCapacitor = new boolean[w * layout.height()];
        int[] pairs = { 0 };
        layout.forEachOrigin((x, y, c, r) -> {
            if (c.getFamily() != Family.RESISTOR) return;
            for (int[] dir : DIRECTIONS) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (layout.isOrigin(nx, ny) && isFamily(layout, nx, ny, Family.CAPACITOR) &&
                        !usedCapacitor[ny * w + nx]) {
                    usedCapacitor[ny * w + nx] = true;
                    pairs[0]++;
                    break;
                }
            }
        });
        return pairs[0];
    }

    /** R2：每颗芯片族的曼哈顿距离 ≤2 内的电容族（最多计 4 个），每个 +5。 */
    private static int decouplingBonus(CircuitLayout layout) {
        List<Cell> chips = originsOfFamily(layout, Family.CHIP);
        List<Cell> capacitors = originsOfFamily(layout, Family.CAPACITOR);
        int bonus = 0;
        for (Cell chip : chips) {
            int count = 0;
            for (Cell capacitor : capacitors) {
                if (minManhattanToFootprint(chip, capacitor.x(), capacitor.y()) <= 2) count++;
            }
            bonus += Math.min(count, 4) * 5;
        }
        return bonus;
    }

    /** R3：电感族与电容族相邻，且二者均在某个晶振曼哈顿距离 ≤2 内。 */
    private static int countLcGroups(CircuitLayout layout) {
        List<Cell> inductors = originsOfFamily(layout, Family.INDUCTOR);
        List<Cell> oscillators = originsOfFamily(layout, Family.OSCILLATOR);
        if (oscillators.isEmpty()) return 0;
        boolean[] usedCapacitor = new boolean[layout.width() * layout.height()];
        int groups = 0;
        for (Cell inductor : inductors) {
            boolean matched = false;
            for (int[] dir : DIRECTIONS) {
                int nx = inductor.x() + dir[0];
                int ny = inductor.y() + dir[1];
                if (!layout.isOrigin(nx, ny) || !isFamily(layout, nx, ny, Family.CAPACITOR) ||
                        usedCapacitor[ny * layout.width() + nx])
                    continue;
                for (Cell oscillator : oscillators) {
                    if (manhattan(inductor.x(), inductor.y(), oscillator.x(), oscillator.y()) <= 2 &&
                            manhattan(nx, ny, oscillator.x(), oscillator.y()) <= 2) {
                        usedCapacitor[ny * layout.width() + nx] = true;
                        groups++;
                        matched = true;
                        break;
                    }
                }
                if (matched) break;
            }
        }
        return groups;
    }

    /** R5：≥4 个同朝向晶体管族四连通成团，每团一个阵列。 */
    private static int countTransistorArrays(CircuitLayout layout) {
        int count = 0;
        for (int size : clusterSizes(layout, Family.TRANSISTOR, true)) {
            if (size >= 4) count++;
        }
        return count;
    }

    /** R6：≥5 个同型号元件（精确到型号，升级件与基础件不混算）四连通成团，返回团数。 */
    private static int countCongestionClusters(CircuitLayout layout) {
        int count = 0;
        for (int size : clusterSizes(layout, null, false)) {
            if (size >= 5) count++;
        }
        return count;
    }

    //////////////////////////////////////
    // ***** 词条条件 *****//
    //////////////////////////////////////

    /** 「保护设计」：存在一颗芯片，其 ≥3 个正交方向上紧邻二极管族。 */
    private static boolean hasProtectedChip(CircuitLayout layout) {
        for (Cell chip : originsOfFamily(layout, Family.CHIP)) {
            boolean[] covered = new boolean[4]; // 右/左/下/上
            for (int[] cell : CircuitLayout.footprint(chip.component(), chip.x(), chip.y(), chip.rotation())) {
                for (int d = 0; d < 4; d++) {
                    if (covered[d]) continue;
                    int nx = cell[0] + DIRECTIONS[d][0];
                    int ny = cell[1] + DIRECTIONS[d][1];
                    if (isFamily(layout, nx, ny, Family.DIODE)) covered[d] = true;
                }
            }
            int count = 0;
            for (boolean b : covered)
                if (b) count++;
            if (count >= 3) return true;
        }
        return false;
    }

    /** 「冗余设计」：存在两颗占地正交相邻的芯片。 */
    private static boolean hasAdjacentChipPair(CircuitLayout layout) {
        List<Cell> chips = originsOfFamily(layout, Family.CHIP);
        for (int i = 0; i < chips.size(); i++) {
            for (int j = i + 1; j < chips.size(); j++) {
                if (footprintsAdjacent(chips.get(i), chips.get(j))) return true;
            }
        }
        return false;
    }

    private static boolean footprintsAdjacent(Cell a, Cell b) {
        for (int[] ca : CircuitLayout.footprint(a.component(), a.x(), a.y(), a.rotation())) {
            for (int[] cb : CircuitLayout.footprint(b.component(), b.x(), b.y(), b.rotation())) {
                if (manhattan(ca[0], ca[1], cb[0], cb[1]) == 1) return true;
            }
        }
        return false;
    }

    //////////////////////////////////////
    // ***** 聚类与工具 *****//
    //////////////////////////////////////

    /**
     * 按族（或精确型号）对元件原点做四连通聚类，返回各团的元件数。
     * family 非 null 时按族聚类（可附加同朝向要求）；为 null 时要求精确同型号（拥塞判定）。
     * 邻接判定基于元件占地（芯片的扩展格也算相邻）。
     */
    private static List<Integer> clusterSizes(CircuitLayout layout, @Nullable Family family,
                                              boolean sameRotation) {
        boolean[] visited = new boolean[layout.width() * layout.height()];
        List<Integer> sizes = new ArrayList<>();
        List<Cell> cells = new ArrayList<>();
        layout.forEachOrigin((x, y, c, r) -> cells.add(new Cell(x, y, c, r)));
        for (Cell start : cells) {
            if (family != null && start.component().getFamily() != family) continue;
            int startIndex = start.y() * layout.width() + start.x();
            if (visited[startIndex]) continue;
            int size = 0;
            ArrayDeque<Cell> queue = new ArrayDeque<>();
            queue.add(start);
            visited[startIndex] = true;
            while (!queue.isEmpty()) {
                Cell current = queue.poll();
                size++;
                for (int[] cell : CircuitLayout.footprint(current.component(), current.x(), current.y(),
                        current.rotation())) {
                    for (int[] dir : DIRECTIONS) {
                        int nx = cell[0] + dir[0];
                        int ny = cell[1] + dir[1];
                        int[] origin = layout.originOf(nx, ny);
                        if (origin == null) continue;
                        int originIndex = origin[1] * layout.width() + origin[0];
                        if (visited[originIndex]) continue;
                        CircuitComponent neighbor = layout.componentAt(origin[0], origin[1]);
                        if (neighbor == null) continue;
                        boolean match = family != null ? neighbor.getFamily() == family :
                                neighbor == current.component();
                        if (!match) continue;
                        if (sameRotation && layout.rotationAt(origin[0], origin[1]) != current.rotation()) continue;
                        visited[originIndex] = true;
                        queue.add(new Cell(origin[0], origin[1], neighbor, layout.rotationAt(origin[0], origin[1])));
                    }
                }
            }
            sizes.add(size);
        }
        return sizes;
    }

    private static boolean isFamily(CircuitLayout layout, int x, int y, Family family) {
        CircuitComponent component = layout.componentAt(x, y);
        return component != null && component.getFamily() == family;
    }

    private static List<Cell> originsOfFamily(CircuitLayout layout, Family family) {
        List<Cell> result = new ArrayList<>();
        layout.forEachOrigin((x, y, c, r) -> {
            if (c.getFamily() == family) result.add(new Cell(x, y, c, r));
        });
        return result;
    }

    private static int minManhattanToFootprint(Cell origin, int x, int y) {
        int min = Integer.MAX_VALUE;
        for (int[] cell : CircuitLayout.footprint(origin.component(), origin.x(), origin.y(), origin.rotation())) {
            min = Math.min(min, manhattan(cell[0], cell[1], x, y));
        }
        return min;
    }

    private static int manhattan(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    private static final int[][] DIRECTIONS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
}
