package io.github.cpearl0.ctnhcore.client.ponder.Electric;

import io.github.cpearl0.ctnhcore.client.ponder.CTNHCorePonderSceneBuilder;

import com.gregtechceu.gtceu.common.data.machines.GTResearchMachines;

import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

import tech.vixhentx.mcmod.ctnhlib.client.ponder.ui.MachineUI;

public final class ComputationNetwork {

    private static final BlockPos HPCA_CONTROLLER = new BlockPos(2, 1, 1);
    private static final BlockPos SWITCH_CONTROLLER = new BlockPos(2, 2, 1);
    private static final Direction NORTH = Direction.NORTH;
    private static final Direction DOWN = Direction.DOWN;

    private ComputationNetwork() {}

    public static void hpcaBasics(SceneBuilder builder, SceneBuildingUtil util) {
        var scene = new CTNHCorePonderSceneBuilder(builder);
        scene.title("hpca_building", "Building a High Performance Computation Array", "搭建高性能计算阵列",
                "HPCA - Building and cooling", "高性能计算阵列 · 搭建与散热");
        start(scene, -1, 0, 7, 0.87f);
        Selection components = util.select().fromTo(2, 2, 2, 2, 4, 4);
        scene.world().showSection(util.select().position(HPCA_CONTROLLER), DOWN);
        explain(scene, 120, face(util, 2, 1, 1, NORTH),
                "Start with the HPCA controller. The array supplies computation (CWU/t) when a machine requests it.",
                "先放置 HPCA 主方块。下游机器发出请求时，阵列才提供算力（CWU/t）。");

        scene.world().showSection(util.select().fromTo(1, 1, 1, 2, 5, 5)
                .substract(components).substract(util.select().position(HPCA_CONTROLLER)), DOWN);
        explain(scene, 150, face(util, 2, 5, 1, NORTH),
                "Build the frame with computer casings, advanced computer casings and 15 computer heat vents.",
                "用计算机外壳、高级计算机外壳和 15 个计算机散热口搭出外框。");

        scene.rotateCameraY(110);
        scene.idle(35);
        scene.world().showSection(components, Direction.EAST);
        scene.idle(15);
        scene.overlay().showOutline(PonderPalette.GREEN, "hpca_components", components, 170);
        explain(scene, 170, face(util, 2, 3, 3, Direction.EAST),
                "Fill all nine component positions. This example uses one computation component, two passive heat sinks and six empty components.",
                "9 个组件位都要填满。本例用 1 个计算组件、2 个被动散热组件和 6 个空组件。");

        scene.overlay().showOutline(PonderPalette.GREEN, "hpca_heat_sinks", util.select().position(2, 3, 2)
                .add(util.select().position(2, 3, 4)), 180);
        explain(scene, 180, face(util, 2, 3, 2, Direction.EAST),
                "Match cooling to the computation load. Passive heat sinks need no fluid; active cooling components need PCB coolant supplied through a fluid input hatch.",
                "让散热能力跟上算力负载。被动散热组件不耗液；使用主动冷却组件时，要从流体输入仓供应多氯联苯冷却液。");

        scene.rotateCameraY(-110);
        scene.idle(35);
        explain(scene, 175, face(util, 2, 3, 1, NORTH),
                "Fit an energy input hatch and the maintenance hatch, and leave at least five regular computer casings. This computation transmitter is the array's only CWU output.",
                "装上能源仓和维护仓，并保留至少 5 个普通计算机外壳。算力从这一个算力数据源仓输出。");

        scene.showUI(MachineUI.of(GTResearchMachines.HIGH_PERFORMANCE_COMPUTING_ARRAY).forceMultiblockActivated()
                .scale(0.60f))
                .at(HPCA_CONTROLLER).pointing(Pointing.DOWN).show(150);
        explain(scene, 140, face(util, 2, 1, 1, NORTH),
                "Check available computation and temperature on the controller. If temperature keeps rising under load, improve cooling or reduce the load.",
                "在主方块界面查看可用算力和温度。满载时若持续升温，就加强散热或降低负载。");
        scene.markAsFinished();
    }

    public static void opticalFiber(SceneBuilder builder, SceneBuildingUtil util) {
        var scene = new CTNHCorePonderSceneBuilder(builder);
        scene.title("computation_optical_fiber", "Connecting a research station directly", "给研究站直连光缆",
                "Computation - Optical fiber", "算力 · 光缆直连");
        start(scene, 0, -1, 10, 0.76f);
        scene.world().showSection(util.select().fromTo(1, 1, 1, 2, 5, 5), DOWN);
        scene.world().showSection(util.select().fromTo(6, 1, 1, 8, 7, 7), DOWN);
        scene.rotateCameraY(180);
        scene.idle(35);
        explain(scene, 145, face(util, 2, 3, 5, Direction.EAST),
                "Start at the HPCA's Computation Data Transmission Hatch. Run the optical fiber from the exposed hatch face.",
                "从 HPCA 的算力数据源仓出线。第一根光缆要接在仓室朝外的正面。");
        scene.world().showSection(util.select().fromTo(3, 3, 5, 4, 3, 5), DOWN);
        scene.world().showSection(util.select().position(4, 3, 6), DOWN);
        scene.world().showSection(util.select().fromTo(4, 3, 7, 5, 3, 7), DOWN);
        scene.rotateCameraY(65);
        scene.idle(35);
        explain(scene, 160, face(util, 6, 3, 7, Direction.WEST),
                "Connect the other end to the research station's Computation Data Reception Hatch. Optical data hatches are for research data, not CWU.",
                "另一头接研究站的算力数据靶仓。光学数据仓传的是研究数据，不是算力；两种仓不要混用。");
        explain(scene, 160, face(util, 4, 3, 6, Direction.UP),
                "Optical fiber can turn but cannot branch. Use wire cutters to connect or disconnect a face. Check both hatch faces and each joint if no CWU arrives.",
                "光缆可以转弯，但不能分岔。用剪线钳接通或断开一面；没有算力时，检查两端仓室的朝向和沿途接头。");
        explain(scene, 155, face(util, 6, 3, 7, Direction.WEST),
                "Power both machines. When a research recipe requests CWU, the station draws it over the cable. A direct link needs no HPCA bridge component.",
                "给两台机器供电。研究配方请求算力时，研究站会通过光缆取用。这样直连不需要 HPCA 桥接组件。");
        scene.markAsFinished();
    }

    public static void switchBasics(SceneBuilder builder, SceneBuildingUtil util) {
        var scene = new CTNHCorePonderSceneBuilder(builder);
        scene.title("computation_network_switch", "Building a Network Switch", "搭建网络交换机",
                "Network Switch - Setup and power", "网络交换机 · 搭建与供电");
        start(scene, 0, 0, 5, 0.95f);
        scene.world().showSection(util.select().position(SWITCH_CONTROLLER), DOWN);
        scene.world().showSection(util.select().position(2, 2, 2), DOWN);
        explain(scene, 125, face(util, 2, 2, 2, Direction.UP),
                "Build a 3×3×3 switch around one advanced computer casing in the center. Put the controller in the center of the front face.",
                "以一个高级计算机外壳为中心搭出 3×3×3 的结构，主方块放在正面中央。");
        scene.world().showSection(util.select().fromTo(1, 1, 1, 3, 3, 3), DOWN);
        explain(scene, 155, face(util, 2, 1, 1, NORTH),
                "Keep at least seven regular computer casings. Fit the maintenance hatch, an energy input hatch, at least one receiver and at least one transmitter.",
                "保留至少 7 个普通计算机外壳，装维护仓和能源仓，再至少各装一个算力数据靶仓和源仓。");
        scene.overlay().showOutline(PonderPalette.INPUT, "receiver", util.select().position(1, 2, 1), 145);
        scene.overlay().showOutline(PonderPalette.OUTPUT, "transmitter", util.select().position(3, 2, 1), 145);
        explain(scene, 145, face(util, 2, 2, 1, NORTH),
                "The reception hatch accepts upstream CWU; the transmission hatch serves downstream machines. Run a separate optical link for each connection.",
                "算力数据靶仓接上游，算力数据源仓接下游；每对端口各走一条独立光缆。");
        scene.showUI(MachineUI.of(GTResearchMachines.NETWORK_SWITCH).forceMultiblockActivated().scale(0.60f))
                .at(SWITCH_CONTROLLER).pointing(Pointing.DOWN).show(175);
        explain(scene, 165, face(util, 2, 2, 1, NORTH),
                "The switch routes CWU; it doesn't generate any. Budget 7,680 EU/t for each computation hatch, both inputs and outputs. Check EU/t and CWU/t in the controller UI.",
                "交换机转发而不生产算力。每个算力仓都要 7,680 EU/t，源仓和靶仓都算。主方块界面可查耗电和算力。");
        scene.markAsFinished();
    }

    public static void bridge(SceneBuilder builder, SceneBuildingUtil util) {
        var scene = new CTNHCorePonderSceneBuilder(builder);
        scene.title("computation_network_bridge", "Bridging two HPCAs through a Network Switch", "用交换机桥接两台 HPCA",
                "HPCA + Network Switch - Bridging", "HPCA + 网络交换机 · 桥接算力");
        start(scene, 0, 0, 12, 0.66f);
        Selection firstBridge = util.select().position(2, 4, 2);
        Selection secondBridge = util.select().position(6, 4, 2);
        scene.world().showSection(util.select().fromTo(1, 1, 1, 6, 5, 5).substract(firstBridge.add(secondBridge)),
                DOWN);
        // The shallower east-side view keeps the nearer array clear of the farther bridge.
        scene.rotateCameraY(65);
        scene.idle(35);
        scene.world().showSection(firstBridge, Direction.EAST);
        scene.idle(15);
        scene.overlay().showOutline(PonderPalette.GREEN, "first_bridge", firstBridge, 165);
        explain(scene, 165, face(util, 2, 4, 2, Direction.EAST),
                "To feed a switch, replace one HPCA component with a bridge component in each supplying array. A second bridge in the same array adds nothing.",
                "要接入交换机，每台供算力的 HPCA 都换上 1 个桥接组件，占用原有的组件位。同一台多装没有收益。");
        scene.world().showSection(secondBridge, Direction.EAST);
        scene.idle(15);
        scene.overlay().showOutline(PonderPalette.GREEN, "first_bridge", firstBridge, 110);
        scene.overlay().showOutline(PonderPalette.GREEN, "second_bridge", secondBridge, 110);
        explain(scene, 110, face(util, 6, 4, 2, Direction.EAST),
                "Both arrays now have a bridge. Keep the computation and cooling components in place.",
                "两台阵列各装了一个桥接组件，计算和散热组件仍然保留。");

        scene.rotateCameraY(-65);
        scene.idle(35);
        scene.world().showSection(util.select().fromTo(2, 1, 8, 4, 3, 10), DOWN);
        scene.world().showSection(util.select().fromTo(1, 3, 6, 5, 3, 7), DOWN);
        scene.overlay().showOutline(PonderPalette.INPUT, "switch_inputs",
                util.select().position(2, 3, 8).add(util.select().position(4, 3, 8)), 175);
        explain(scene, 175, face(util, 2, 3, 8, NORTH),
                "Connect each array's transmitter to its own reception hatch on the switch. These are two independent fibers, not a splitter.",
                "两台 HPCA 的源仓分别接入交换机的两个靶仓。两条光缆各自独立，不在中间分岔。");

        scene.world().showSection(util.select().fromTo(7, 1, 4, 9, 7, 10), DOWN);
        scene.world().showSection(util.select().fromTo(5, 3, 10, 6, 3, 10), DOWN);
        scene.rotateCameraY(-30);
        scene.idle(35);
        explain(scene, 155, face(util, 7, 3, 10, Direction.WEST),
                "Run a third fiber from the switch's transmission hatch to the research station's reception hatch.",
                "再走第三条光缆，从交换机的算力数据源仓接到研究站的算力数据靶仓。");
        explain(scene, 180, face(util, 2, 3, 8, NORTH),
                "Keep the switch powered and enabled. The research station can draw from both arrays when it requests computation. If one array is missing, check its bridge and both ends of its fiber.",
                "让交换机保持供电和启用。研究站请求算力时就能从两台阵列取用；少了一路，就检查那台 HPCA 的桥接组件和光缆两端。");
        scene.markAsFinished();
    }

    private static void start(CTNHCorePonderSceneBuilder scene, int x, int z, int size, float scale) {
        scene.configureBasePlate(x, z, size);
        scene.scaleSceneView(scale);
        scene.showBasePlate();
        scene.idle(15);
    }

    private static void explain(CTNHCorePonderSceneBuilder scene, int ticks, Vec3 target, String en, String cn) {
        scene.showText(ticks, en, cn).pointAt(target).attachKeyFrame();
        scene.idle(ticks + 12);
    }

    private static Vec3 face(SceneBuildingUtil util, int x, int y, int z, Direction side) {
        return util.vector().blockSurface(util.grid().at(x, y, z), side);
    }
}
