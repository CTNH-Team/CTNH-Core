package io.github.cpearl0.ctnhcore.client.ponder.Electric;

import io.github.cpearl0.ctnhcore.client.ponder.CTNHCorePonderSceneBuilder;
import io.github.cpearl0.ctnhcore.registry.machines.CTNHMachines;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;

import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.element.ElementLink;
import net.createmod.ponder.api.element.WorldSectionElement;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

import java.util.List;

/**
 * 埃克森美孚化工厂（{@code ctnhcore:exxonmobil_chemical_plant}）的思索。
 * <p>
 * 结构对应 {@code GTNNMultiblocks.CHEMICAL_PLANT} 的 pattern：7x7x7 的塔，外层是工厂机壳，
 * 内部是机器外壳框架、线圈层和管道层，中间留空腔。
 * <p>
 * 世界坐标 = 主方块 + (-(c-ctlC), r-ctlR, -(a-ctlA))。{@code facing=NORTH} 时 GT 把 LEFT
 * 映到 -X、FRONT 映到 -Z；主方块（符号 {@code S}）落在 {@code grid(4, 1, 1)}，即北面。
 * <p>
 * pattern 里只有符号 {@code V} 声明了 abilities，而 {@code V} 仅出现在 y=1 的底部一圈
 * （23 格，含主方块位），仓室因此只能装在这一圈。
 */
public class ChemicalPlant {

    // ------------------------------------------------------------------ 结构分组
    // 下面四组 box 的并集覆盖结构里的全部方块，逐格校验过。

    /** 工厂机壳，92 格 */
    private static final int[][] PLANT = {
            { 1, 1, 1, 3, 1, 1 },
            { 1, 1, 2, 1, 1, 7 },
            { 1, 2, 1, 1, 7, 1 },
            { 1, 2, 7, 1, 7, 7 },
            { 1, 7, 2, 7, 7, 6 },
            { 2, 1, 7, 7, 1, 7 },
            { 2, 7, 1, 7, 7, 1 },
            { 2, 7, 7, 7, 7, 7 },
            { 5, 1, 1, 7, 1, 1 },
            { 7, 1, 2, 7, 1, 6 },
            { 7, 2, 1, 7, 6, 1 },
            { 7, 2, 7, 7, 6, 7 },
    };

    /** 机器外壳，57 格 */
    private static final int[][] MACHINE = {
            { 2, 1, 2, 6, 1, 6 },
            { 2, 2, 2, 6, 2, 2 },
            { 2, 2, 3, 2, 2, 6 },
            { 2, 6, 2, 6, 6, 2 },
            { 2, 6, 3, 2, 6, 6 },
            { 3, 2, 6, 6, 2, 6 },
            { 3, 6, 6, 6, 6, 6 },
            { 6, 2, 3, 6, 2, 5 },
            { 6, 6, 3, 6, 6, 5 },
    };

    /** 线圈，27 格 */
    private static final int[][] COIL = {
            { 3, 2, 3, 5, 2, 5 },
            { 3, 4, 3, 5, 4, 5 },
            { 3, 6, 3, 5, 6, 5 },
    };

    /** 管道，18 格 */
    private static final int[][] PIPE = {
            { 3, 3, 3, 5, 3, 5 },
            { 3, 5, 3, 5, 5, 5 },
    };

    /** 主方块（符号 S），位于北面。 */
    private static final int CTRL_X = 4;
    private static final int CTRL_Y = 1;
    private static final int CTRL_Z = 1;

    /** 可装仓室的底部一圈（符号 V，y=1 的 23 格，不含被挖去的主方块位）。 */
    private static final int[][] HATCH_RING = {
            { 1, 1, 1, 1, 1, 7 },
            { 2, 1, 1, 3, 1, 1 },
            { 5, 1, 1, 7, 1, 1 },
            { 2, 1, 7, 6, 1, 7 },
            { 7, 1, 2, 7, 1, 7 },
    };

    /** 把结构挪到屏幕右上方的世界位移，屏幕右为 +Z、屏幕上为 +X+Y+Z。 */
    private static final Vec3 PARK = new Vec3(-3d, 5d, 6d);

    /** 位移动画总时长（tick）。 */
    private static final int MOVE_TICKS = 16;

    /** 缓动分段数。 */
    private static final int EASE_STEPS = 8;

    private ChemicalPlant() {}

    public static void Common(SceneBuilder builder, SceneBuildingUtil util) {
        CTNHCorePonderSceneBuilder scene = new CTNHCorePonderSceneBuilder(builder);
        scene.title("chemical_plant_building", "How to build the Exxonmobil Chemical Plant",
                "如何搭建埃克森美孚化工厂", "Exxonmobil Chemical Plant", "埃克森美孚化工厂");
        scene.configureBasePlate(0, 0, 9);
        scene.scaleSceneView(0.4f);
        scene.showBasePlate();
        scene.idle(10);

        // ---- 1. 先放主方块 ----
        ElementLink<WorldSectionElement> ctrl = scene.world()
                .showIndependentSection(util.select().position(CTRL_X, CTRL_Y, CTRL_Z), Direction.DOWN);
        scene.showText(60, "First, place a Chemical Plant controller.", "首先放置化工厂主方块。")
                .pointAt(surface(util, CTRL_X, CTRL_Y, CTRL_Z, Direction.UP))
                .attachKeyFrame();
        scene.idle(70);

        // ---- 2. 终端一键放置 ----
        scene.overlay()
                .showControls(surface(util, CTRL_X, CTRL_Y, CTRL_Z, Direction.UP), Pointing.LEFT, 40)
                .rightClick()
                .withItem(GTItems.TERMINAL.asStack())
                .whileSneaking();
        scene.showText(60, "With enough blocks, a terminal places the whole structure in one click.",
                "方块充足时，用终端可以一键放置整个结构。")
                .attachKeyFrame();
        scene.idle(20);
        ElementLink<WorldSectionElement> shell = scene.world()
                .showIndependentSection(select(util, PLANT), Direction.DOWN);
        scene.idle(20);
        ElementLink<WorldSectionElement> machine = scene.world()
                .showIndependentSection(select(util, MACHINE), Direction.DOWN);
        scene.idle(10);
        ElementLink<WorldSectionElement> coil = scene.world()
                .showIndependentSection(select(util, COIL), Direction.DOWN);
        ElementLink<WorldSectionElement> pipe = scene.world()
                .showIndependentSection(select(util, PIPE), Direction.DOWN);
        scene.idle(20);

        // ---- 3. 外壳 ----
        scene.overlay().showOutline(PonderPalette.GREEN, "plant_casing", select(util, PLANT), 70);
        scene.showText(70, "Plant casing forms the outer shell.", "工厂机壳构成外壳。")
                .pointAt(surface(util, 1, 1, 4, Direction.WEST))
                .attachKeyFrame();
        scene.idle(80);

        // ---- 4. 同类方块必须同级 ----
        scene.showText(80,
                "Every block of one kind must be the SAME tier - mixing tiers fails the structure.",
                "同一类方块必须保持同一等级，混用会导致结构不成型。")
                .pointAt(surface(util, 1, 1, 4, Direction.WEST))
                .attachKeyFrame();
        scene.idle(90);

        // ---- 5. 只看线圈：其余全部移开 ----
        move(scene, group(PARK, ctrl, shell, machine, pipe));
        scene.overlay().showOutline(PonderPalette.RED, "coils", select(util, COIL), 70);
        scene.showText(70, "Setting the rest aside - higher coil tiers speed up every recipe.",
                "把其余部分移到一边，只看线圈：等级越高，配方越快。")
                .pointAt(surface(util, 4, 4, 4, Direction.UP))
                .attachKeyFrame();
        scene.idle(80);

        // ---- 6. 换成管道：线圈移出、管道移入，同时进行 ----
        move(scene, group(PARK, coil), group(PARK.scale(-1d), pipe));
        scene.overlay().showOutline(PonderPalette.RED, "pipes", select(util, PIPE), 70);
        scene.showText(70, "Now the pipes - higher pipe tiers raise the parallel count.",
                "换成管道：等级越高，并行数越多。")
                .pointAt(surface(util, 4, 3, 4, Direction.UP))
                .attachKeyFrame();
        scene.idle(80);

        scene.showText(80, "They also lower the chance that a catalyst gets consumed, so it lasts longer.",
                "管道等级越高，催化剂被消耗的概率越低，也就更耐用。")
                .attachKeyFrame();
        scene.idle(90);

        // ---- 7. 换成机器外壳：管道移出、外壳移入，同时进行 ----
        move(scene, group(PARK, pipe), group(PARK.scale(-1d), machine));
        scene.overlay().showOutline(PonderPalette.BLUE, "machine_casing", select(util, MACHINE), 70);
        scene.showText(80, "And the machine casing - an energy hatch sets the machine's tier, and it caps it.",
                "再看机器外壳：能源仓决定机器等级，而机器外壳的等级会成为上限。")
                .pointAt(surface(util, 4, 6, 2, Direction.UP))
                .attachKeyFrame();
        scene.idle(90);

        // ---- 8. 全部归位，讲仓室所在的底部一圈 ----
        move(scene, group(PARK.scale(-1d), ctrl, shell, coil, pipe));
        scene.overlay().showOutline(PonderPalette.RED, "hatch_ring", select(util, HATCH_RING), 90);
        scene.showText(90, "Only this bottom ring accepts hatches - not the walls above it.",
                "只有底部这一圈可以安装仓室，上面的侧壁不行。")
                .pointAt(surface(util, 1, 1, 4, Direction.WEST))
                .attachKeyFrame();
        scene.idle(100);

        // ---- 9. 装上必要的仓室 ----
        scene.world().setBlock(util.grid().at(3, 1, 1),
                GTMachines.ENERGY_INPUT_HATCH[GTValues.LV].defaultBlockState(), true);
        scene.world().setBlock(util.grid().at(5, 1, 1),
                GTMachines.MAINTENANCE_HATCH.defaultBlockState(), true);
        scene.world().setBlock(util.grid().at(1, 1, 4),
                CTNHMachines.CATALYST_HATCH.defaultBlockState(), true);
        scene.showText(90,
                "An energy input hatch, a maintenance hatch, and up to two catalyst hatches go on this ring.",
                "在这一圈装上能源输入仓、维护仓，以及最多两个催化剂仓。")
                .attachKeyFrame();
        scene.idle(100);

        scene.showText(80, "The catalyst hatch holds catalysts and feeds them into recipes.",
                "催化剂仓用来存放催化剂，并把它们送进配方。")
                .pointAt(surface(util, 1, 1, 4, Direction.WEST))
                .attachKeyFrame();
        scene.idle(90);

        scene.markAsFinished();
    }

    /** 一组区块与它们共同的位移量。 */
    private record Group(Vec3 offset, List<ElementLink<WorldSectionElement>> links) {}

    @SafeVarargs
    private static Group group(Vec3 offset, ElementLink<WorldSectionElement>... links) {
        return new Group(offset, List.of(links));
    }

    /**
     * 把若干组区块一起搬走，函数内部吃掉动画时间。
     * <p>
     * Ponder 的 {@code moveSection} 是逐 tick 匀速补间（{@code AnimateElementInstruction}
     * 每 tick 加上 {@code totalDelta / ticks}），接口没有缓动参数；这里把整段位移按
     * smoothstep 曲线拆成 {@link #EASE_STEPS} 段小位移逐段发出，做成起步慢、中段快、收尾慢。
     * <p>
     * 同一段位移必须在同一 tick 内发给所有组再一起 idle，各组才保持同步。
     */
    private static void move(CTNHCorePonderSceneBuilder scene, Group... groups) {
        int total = MOVE_TICKS;
        int steps = Math.max(1, Math.min(EASE_STEPS, total));
        for (int i = 1; i <= steps; i++) {
            double share = smoothstep(i / (double) steps) - smoothstep((i - 1d) / steps);
            int ticks = (total * i) / steps - (total * (i - 1)) / steps;

            for (Group g : groups) {
                Vec3 delta = g.offset().scale(share);
                for (ElementLink<WorldSectionElement> link : g.links()) {
                    scene.world().moveSection(link, delta, ticks);
                }
            }
            // moveSection 不阻塞，必须自己 idle，否则下一段会在同一 tick 叠加。
            scene.idle(ticks);
        }
    }

    /** 3t^2 - 2t^3，两端导数为 0。 */
    private static double smoothstep(double t) {
        return t * t * (3d - 2d * t);
    }

    private static Selection select(SceneBuildingUtil util, int[][] boxes) {
        Selection result = null;
        for (int[] b : boxes) {
            Selection one = util.select().fromTo(b[0], b[1], b[2], b[3], b[4], b[5]);
            result = result == null ? one : result.add(one);
        }
        return result;
    }

    private static Vec3 surface(SceneBuildingUtil util, int x, int y, int z, Direction face) {
        return util.vector().blockSurface(util.grid().at(x, y, z), face);
    }
}
