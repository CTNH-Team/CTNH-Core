package io.github.cpearl0.ctnhcore.client.ponder.Electric;

import io.github.cpearl0.ctnhcore.client.ponder.CTNHCorePonderSceneBuilder;

import com.gregtechceu.gtceu.common.data.GTItems;

import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

import tech.luckyblock.mcmod.ctnhenergy.client.ponder.ae2.AE2CablePonderHelper;

/**
 * 装配线（{@code gtceu:assembly_line}）的思索场景。
 * <p>
 * 两个 storyboard 都在 {@code assets/ctnhcore/ponder/assembly_line/} 下。
 * <p>
 * <b>主方块必须朝上放置（facing=up / upwards_facing=south）。</b>
 * {@code FactoryBlockPattern.start(BACK, UP, RIGHT)} 在该朝向下把 pattern 的 aisle 序号映射到 +x、
 * 字符序号映射到 -y、行号映射到 +z —— 本该压在机器底部的输入行（row0）于是整排转向 -z（北），
 * 正对 Ponder 默认镜头：输入总线与输入仓的进料面直接露在正面，
 * 既不必把机器架高，也不必"先移开上层讲完再盖回去"。两个场景都按这个朝向搭建。
 * <p>
 * 相机的默认可见面只有 NORTH / WEST / UP；{@code pointAt} 与 {@code showControls} 只能用这三个朝向，
 * 用到背面会是穿模视角。需要看别的面时先 {@code rotateCameraY}，看完记得转回来
 * —— 它是<b>相对</b>角度且带动画（{@code RotateSceneInstruction(relative=true)}），正负配对即可复位。
 * <p>
 * <b>{@code scaleSceneView} 不是指令</b>：它只写 {@code scene.scaleFactor} 这个全局字段，
 * 一次场景里只有最后一次调用生效，做不出"推近看细节再拉远"的效果。
 * 想聚焦局部只能靠旋转与选择框；场景的整体缩放请在开场一次性定好。
 * <p>
 * <b>每段 {@code showText(D)} 到下一条 {@code showText} 之间的 {@code idle} 总和必须 ≥ {@code D + 10}。</b>
 * {@code FadeInOutInstruction} 把文本存活时间设为 {@code D + 2 * fadeTime}（fadeTime = 5），
 * 而 {@code DelayInstruction} 是阻塞指令，后面的指令要等它结束才开始计时；
 * {@code idle} 不够时两段文字会同时在屏幕上叠成一团。改动文案或时长后请重新核对这个差值。
 */
public class AssemblyLine {

    private AssemblyLine() {}

    /** 片数：1（主方块片）+ 7（可重复段）+ 1（输出片）。pattern 允许 5~16 片。 */
    private static final int SLICES = 9;

    /**
     * 场景一：基础教学。结构占 y=1..3，y=0 是地板。
     * <p>
     * 坐标：主方块 {@code (1,3,3)}、数据访问仓 {@code (2,3,3)}、能源仓 {@code (1,2,4)}、
     * ULV 输入总线 {@code (1..8,2,1)}、ULV 输出总线 {@code (9,2,1)}、流体输入仓 {@code (1,3,1)}。
     * <p>
     * 输入行（z=1）整排朝北：{@code SLICES - 1} 台输入总线加末端 1 台输出总线，
     * 流体输入仓只在最靠主方块的一端放 1 个（pattern 允许最多 4 个）。
     */
    public static void Basics(SceneBuilder builder, SceneBuildingUtil util) {
        CTNHCorePonderSceneBuilder scene = new CTNHCorePonderSceneBuilder(builder);
        scene.title("assembly_line_building", "How to build an Assembly Line", "如何搭建装配线", "Assembly Line",
                "装配线");
        // 结构 x 长 9、z 长 4；底板取 11，中心对齐结构中心。
        scene.configureBasePlate(0, -3, 11);
        scene.scaleSceneView(0.75f);
        scene.showBasePlate();
        scene.idle(10);

        // ---- 1. 主方块 ----
        scene.world().showSection(util.select().position(1, 3, 3), Direction.DOWN);
        scene.showText(140,
                "An Assembly Line is a multiblock of 5 to 16 slices - this one has 9 slices. Place the controller first.",
                "装配线由 5~16 片组成，本例是 9 片结构。先放置主方块。")
                .pointAt(surface(util, 1, 3, 3, Direction.NORTH))
                .attachKeyFrame();
        scene.idle(155);

        // ---- 2. 终端一键放置 ----
        scene.overlay().showControls(surface(util, 1, 3, 3, Direction.NORTH), Pointing.LEFT, 40)
                .rightClick()
                .withItem(GTItems.TERMINAL.asStack())
                .whileSneaking();
        scene.showText(110, "With enough blocks, the terminal places the whole machine in one click.",
                "方块充足时，用终端可以一键放置整台机器。")
                .attachKeyFrame();
        scene.idle(60);
        scene.world().showSection(util.select().fromTo(1, 1, 1, 9, 3, 4), Direction.DOWN);
        scene.idle(65);

        // ---- 3. 外壳材质 ----
        scene.overlay().showOutline(PonderPalette.GREEN, "assembly_line_shell", util.select().fromTo(1, 1, 1, 9, 3, 4),
                150);
        scene.showText(150,
                "Steel machine casing builds the frame, laminated glass is set into the walls, and assembly line grating covers the top.",
                "外层是钢质机壳，侧壁嵌着层压玻璃，顶部是装配线格栅。")
                .pointAt(surface(util, 5, 3, 1, Direction.NORTH))
                .attachKeyFrame();
        scene.idle(165);

        // ---- 4. 物品输入：整排朝北，从正面直接可读 ----
        scene.overlay().showOutline(PonderPalette.BLUE, "item_input_buses", util.select().fromTo(1, 2, 1, 8, 2, 1),
                200);
        scene.showText(190,
                "The input row runs along the whole front. Slot i is the i-th input bus counted from the controller end, not the order you filled them in; each bus only reads its first non-empty stack, and you need at least as many buses as the recipe has item inputs.",
                "输入行整排都在正面。第 i 个输入位是从主方块端数起的第 i 台输入总线，与放入的先后无关；每台总线只读取自己第一个非空的物品，且总线数量不能少于配方的物品输入数。")
                .pointAt(surface(util, 1, 2, 1, Direction.NORTH))
                .attachKeyFrame();
        scene.idle(205);

        // ---- 5. 流体输入：同一套顺序，一个就够 ----
        scene.overlay().showOutline(PonderPalette.INPUT, "fluid_input_hatch", util.select().position(1, 3, 1), 190);
        scene.showText(170,
                "Fluid input hatches use the same order. A recipe with a single fluid needs only one hatch; the pattern allows up to four.",
                "流体输入仓遵循同一套顺序。只用一种流体的配方放一台输入仓就够，结构上最多允许四台。")
                .pointAt(surface(util, 1, 3, 1, Direction.NORTH))
                .attachKeyFrame();
        scene.idle(185);

        // ---- 6. 输出总线 ----
        scene.overlay().showOutline(PonderPalette.OUTPUT, "output_bus", util.select().position(9, 2, 1), 160);
        scene.showText(150,
                "Only an output bus fits at the very end of the line - that is where the finished product leaves the machine.",
                "装配线的最末端只能放输出总线，成品就从这里离开机器。")
                .pointAt(surface(util, 9, 2, 1, Direction.NORTH))
                .attachKeyFrame();
        scene.idle(165);

        // ---- 7. 放置顺序 ----
        scene.overlay().showControls(surface(util, 1, 2, 1, Direction.NORTH), Pointing.LEFT, 60)
                .rightClick()
                .withItem(GTItems.INTEGRATED_CIRCUIT_LV.asStack());
        scene.showText(180,
                "Put the recipe's first item into the first input bus. If the order is wrong the machine refuses to start and reports an item or fluid input order error - move the stacks to the matching bus instead of filling the buses in any order.",
                "把配方的第 1 个物品放进第 1 台输入总线。顺序放错时机器不会开工，而是提示物品输入顺序错误或流体输入顺序错误——要把材料挪到对应位置的仓室，而不是随手填满。")
                .pointAt(surface(util, 1, 2, 1, Direction.NORTH))
                .attachKeyFrame();
        scene.idle(195);

        // ---- 8. 研究数据 ----
        scene.overlay().showControls(surface(util, 2, 3, 3, Direction.NORTH), Pointing.LEFT, 60)
                .rightClick()
                .withItem(GTItems.TOOL_DATA_STICK.asStack());
        scene.showText(180,
                "The data access hatch holds research data carriers such as a data stick, data orb or data module. It only reads them and never consumes them, only recipes with a research condition need them at all, and the structure takes exactly one such hatch.",
                "数据访问仓用来放研究数据载体，例如闪存、数据球或数据模块。它只读取、不消耗；只有带研究条件的配方才需要研究数据；结构上也必须恰好放一个数据访问仓。")
                .pointAt(surface(util, 2, 3, 3, Direction.NORTH))
                .attachKeyFrame();
        scene.idle(195);

        // ---- 9. 能源仓 ----
        scene.showText(140, "An energy input hatch supplies the machine - this build puts one at the back of the line.",
                "能源仓负责给装配线供电，本例把它放在整条线的后一排。")
                .pointAt(surface(util, 1, 2, 4, Direction.NORTH))
                .attachKeyFrame();
        scene.idle(155);

        scene.markAsFinished();
    }

    /**
     * 场景二：AE 自动化。机器本体在 y=1..3（z=3..6），AE 接线层在 z=1..2。
     * <p>
     * 坐标：主方块 {@code (1,3,5)}、数据访问仓 {@code (2,3,5)}、能源仓 {@code (1,2,6)}、
     * 输入总线 {@code (1..8,2,3)}、输出总线 {@code (9,2,3)}、流体输入仓 {@code (1,3,3)}；
     * 物品存储总线 {@code (1..8,2,2)} 优先级 16..9、流体存储总线 {@code (1,3,2)} 优先级 1、
     * 子网接口 {@code (8,2,1)}；
     * 主网：样板供应器 {@code (9,2,1)}（{@code push_direction=west}，紧贴接口东侧）、
     * 主网控制器 {@code (9,3,1)}、电源 {@code (9,4,1)} 位于控制器上方。
     * <p>
     * 供应器正对输出总线 {@code (9,2,3)}，中间用物品管道 {@code gtceu:brass_small_item_pipe}
     * {@code (9,2,2)} 连通：成品从输出总线沿管道回到供应器，再由供应器记入主网。
     * <p>
     * 两网能共存的关键：供应器的并网面排除了它的推送方向
     * （{@code PatternProviderBlockEntity.getGridConnectableSides}），
     * 所以紧贴的接口与供应器不会把子网并进主网；供应器同时给子网供电，无需单独的供电线或石英纤维。
     */
    public static void AeAutomation(SceneBuilder builder, SceneBuildingUtil util) {
        CTNHCorePonderSceneBuilder scene = new CTNHCorePonderSceneBuilder(builder);
        scene.title("assembly_line_ae_automation", "Automating an Assembly Line with AE2",
                "如何用 AE2 自动化装配线", "Assembly Line - AE2 Automation", "装配线 · AE2 自动化");
        // 结构 x 1..9、z 1..6；底板取 11，(0,-2) 时底板 x 0..10 / z -2..8，正好把结构摆在正中。
        scene.configureBasePlate(0, -2, 11);
        scene.scaleSceneView(0.65f);
        scene.showBasePlate();
        // 线缆连接交给 helper：它按"当前可见的线缆格"重算每条线缆的连接面，省得手写 connections。
        AE2CablePonderHelper cables = new AE2CablePonderHelper(scene, util);
        scene.idle(10);

        // ---- 1. 先看整机 ----
        scene.world().showSection(util.select().fromTo(1, 1, 3, 9, 3, 6), Direction.DOWN);
        scene.showText(150,
                "The idea is to turn the machine into a storage of an AE subnet, so that a pattern provider can feed the inputs in order.",
                "思路是把这台机器变成 AE 子网的一块存储，让样板供应器把材料按顺序推进去。")
                .pointAt(surface(util, 1, 3, 5, Direction.NORTH))
                .attachKeyFrame();
        scene.idle(165);

        // ---- 2. 转个角度，露出接线层 ----
        // 注意：rotateCameraY 是带动画的相对角度（RotateSceneInstruction, relative=true），
        // 而 scaleSceneView 只是写 scene.scaleFactor 这个全局字段、不是指令，
        // 一次场景里只有最后一次调用生效 —— 想要"推近看细节"只能靠旋转，不能靠中途改缩放。
        scene.rotateCameraY(30);
        scene.idle(40);
        cables.showSectionAndConnect(util.grid().at(1, 2, 2), util.grid().at(8, 2, 2), Direction.DOWN);
        cables.showSectionAndConnect(util.grid().at(1, 3, 2), util.grid().at(1, 3, 2), Direction.DOWN);
        scene.world().showSection(util.select().fromTo(8, 2, 1, 9, 4, 1), Direction.DOWN);
        scene.world().showSection(util.select().position(9, 2, 2), Direction.DOWN);
        scene.idle(30);
        scene.overlay().showOutline(PonderPalette.GREEN, "ae_deck", util.select().fromTo(1, 2, 1, 9, 4, 2), 190);
        scene.showText(170,
                "The controller is placed facing up, so the whole machine turns its input row to the front. Input buses and fluid hatches take their input from that exposed face, and every storage bus sits right on it.",
                "主方块朝上放置后，整台机器的输入行跟着转到了正面，输入总线与输入仓的进料面就露在这一侧，每条存储总线都贴在它正对的仓室上。")
                .pointAt(surface(util, 4, 2, 2, Direction.NORTH))
                .attachKeyFrame();
        scene.idle(185);

        // ---- 3. 物品存储总线与优先级 ----
        scene.overlay().showOutline(PonderPalette.BLUE, "item_storage_buses", util.select().fromTo(1, 2, 2, 8, 2, 2),
                200);
        scene.showText(180,
                "One storage bus per input bus, carrying priorities 16 down to 9 - highest at the controller end. The machine reads slot 1, 2, 3 and so on from the controller end, and priority decides which bus the materials land in first.",
                "每台输入总线正对一条存储总线，优先级从 16 递减到 9——主方块端最高。机器从主方块端开始读取第 1、2、3 个输入位，优先级决定材料先落进哪条总线。")
                .pointAt(surface(util, 1, 2, 2, Direction.NORTH))
                .attachKeyFrame();
        scene.idle(195);

        // ---- 4. 流体存储总线 ----
        scene.overlay().showOutline(PonderPalette.INPUT, "fluid_storage_bus", util.select().position(1, 3, 2), 180);
        scene.showText(160,
                "The single fluid hatch gets its own storage bus fed from the same subnet.",
                "唯一那台输入仓由同一条子网单独接一条存储总线。")
                .pointAt(surface(util, 1, 3, 2, Direction.NORTH))
                .attachKeyFrame();
        scene.idle(175);

        // ---- 5. 转回正视，讲两个网络的分界 ----
        scene.rotateCameraY(-30);
        scene.idle(45);
        scene.showText(150,
                "The pattern provider also powers the subnet interface it faces, so the subnet needs no extra power line of its own.",
                "样板供应器同时会给它正对的子网接口供电，所以子网自己不用再接供电线。")
                .pointAt(surface(util, 8, 2, 1, Direction.NORTH))
                .attachKeyFrame();
        scene.idle(165);

        // ---- 6. 子网接口 ----
        scene.showText(160,
                "The subnet interface sits right beside the provider and stays unconfigured - leave every request slot empty so that the provider pushes the whole batch of materials straight into the subnet.",
                "子网接口就紧贴在样板供应器旁边，不做任何配置：请求槽全部留空，供应器才会把整批材料直接推进子网。")
                .pointAt(surface(util, 8, 2, 1, Direction.NORTH))
                .attachKeyFrame();
        scene.idle(175);

        // ---- 7. 主网样板供应器 ----
        scene.showText(190,
                "On the main network side, point the provider's push direction at the adjacent interface, encode a processing pattern and turn on the finished-product return lock, so that it waits instead of pushing the next batch while the previous product has not come back yet.",
                "主网一侧，把供应器的推送方向设为朝紧邻的接口，编码处理样板并打开成品返回锁定模式：上一批成品还没沿管道返回时，它就不再推下一批。")
                .pointAt(surface(util, 9, 2, 2, Direction.UP))
                .attachKeyFrame();
        // 文字还在屏幕上时并行转镜头：绕到侧面，管道 (9,2,2) 沿 z 的整段就摊在画面上，
        // 南端接输出总线、北端接供应器这一层连接关系一眼可见。
        // rotateCameraY 是缓动追值（chase EXP），所以转过之后要留够帧数让它到位，再原路转回。
        scene.rotateCameraY(90);
        scene.idle(35);
        scene.idle(20);
        scene.rotateCameraY(-90);
        scene.idle(35);
        scene.idle(120);

        // ---- 8. 研究数据仍要手放 ----
        scene.overlay().showControls(surface(util, 2, 3, 5, Direction.NORTH), Pointing.LEFT, 60)
                .rightClick()
                .withItem(GTItems.TOOL_DATA_STICK.asStack());
        scene.showText(170,
                "The research data in the data access hatch still has to be inserted by hand: that hatch does not expose its item capability to the outside, so no automation can deliver the data for you.",
                "数据访问仓里的研究数据仍然要手动放入：这个仓室的物品能力不对外开放，任何自动化都没法替你把数据送进去。")
                .pointAt(surface(util, 2, 3, 5, Direction.NORTH))
                .attachKeyFrame();
        scene.idle(185);

        // ---- 9. 运行流程 ----
        scene.showText(180,
                "The flow is: the provider pushes a batch, the materials fall into the buses by priority, the machine checks them in order and starts, and the product leaves through the output bus and travels back up the pipe into the provider, which hands it to the main network.",
                "运行流程是：样板供应器推一批料，材料按优先级落进各条存储总线，装配线按顺序校验并开工，产物从输出总线出来、沿管道回收到供应器，再由供应器交给主网。")
                .pointAt(surface(util, 9, 2, 2, Direction.NORTH))
                .attachKeyFrame();
        scene.idle(195);

        scene.markAsFinished();
    }

    /** 方块某一面的中心点，{@code face} 必须指向当前镜头看得见的那一面。 */
    private static Vec3 surface(SceneBuildingUtil util, int x, int y, int z, Direction face) {
        return util.vector().blockSurface(util.grid().at(x, y, z), face);
    }
}
