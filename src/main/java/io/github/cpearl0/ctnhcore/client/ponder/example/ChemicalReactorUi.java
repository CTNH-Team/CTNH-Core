package io.github.cpearl0.ctnhcore.client.ponder.example;

import io.github.cpearl0.ctnhcore.client.ponder.CTNHCorePonderSceneBuilder;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;

import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;

import tech.vixhentx.mcmod.ctnhlib.client.ponder.machine.MachineEdits;
import tech.vixhentx.mcmod.ctnhlib.client.ponder.ui.MachineUI;

/**
 * LV 化学反应釜的完整示例：把机器真实的界面画进思索，并按它的真实槽位序号往里写物品、灌流体。
 *
 * <p>
 * 八段依次演示：界面本体、物品写入、流体写入、覆盖板、工作/待机模型、按配方 id 自动填充与进度条、
 * 自动输出口朝向、原版整套界面加配置器开关红框。
 *
 * <p>
 * storyboard：3x3 地板 + (1,1,1) 的 {@code gtceu:lv_chemical_reactor}（facing=north）。
 */
public class ChemicalReactorUi {

    /** 裁剪版界面：只画标题栏、页签与机器页，其余场景直接复用这个常量。 */
    private static final MachineUI LV_CHEMICAL_REACTOR_UI = MachineUI.of(GTMachines.CHEMICAL_REACTOR[GTValues.LV])
            .scale(0.6f);

    /** 原版整套：配置器面板、提示面板、玩家背包都在，配置器那一列的开关才框得到。 */
    private static final MachineUI FULL_CHEMICAL_REACTOR_UI = MachineUI.of(GTMachines.CHEMICAL_REACTOR[GTValues.LV])
            .showFullUI()
            .scale(0.6f);

    private ChemicalReactorUi() {}

    public static void Common(SceneBuilder builder, SceneBuildingUtil util) {
        CTNHCorePonderSceneBuilder scene = new CTNHCorePonderSceneBuilder(builder);
        scene.title("chemical_reactor_ui", "Chemical Reactor UI", "化学反应釜界面",
                "LV Chemical Reactor", "LV 化学反应釜");
        scene.showBasePlate();
        scene.idle(10);

        BlockPos machinePos = util.grid().at(1, 1, 1);
        scene.world().showSection(util.select().position(machinePos), Direction.DOWN);
        scene.idle(10);

        // 第一段：界面本体。
        scene.showUI(LV_CHEMICAL_REACTOR_UI).at(util.vector().topOf(machinePos)).forMachine(machinePos).show(140);
        scene.showText(80, "The panel is the machine's own UI, without the player inventory.",
                "面板就是这台机器自己的界面，不含玩家背包。")
                .attachKeyFrame();
        scene.idle(150);

        // 第二段：往 1、2 号槽位写物品，序号就是实机 UI 的槽位序号。
        scene.showUI(LV_CHEMICAL_REACTOR_UI).at(util.vector().topOf(machinePos)).forMachine(machinePos)
                .slot(1)
                .withItem(new ItemStack(Items.GRASS_BLOCK, 64), 20)
                .slot(2)
                .withItem(new ItemStack(Items.GLASS, 64), 20)
                .outlineSlot(1, 20)
                .show(160);
        scene.showText(80,
                "64 grass blocks go into slot 1 and 64 glass into slot 2; the panel shows the machine's real inventory.",
                "64 个草方块进 1 号槽位、64 个玻璃进 2 号槽位，面板显示的就是机器真实库存。")
                .attachKeyFrame();
        scene.idle(180);

        // 第三段：往 0 号储罐灌流体。
        scene.showUI(LV_CHEMICAL_REACTOR_UI).at(util.vector().topOf(machinePos)).forMachine(machinePos)
                .tank(0)
                .withFluid(new FluidStack(Fluids.WATER, 1000), 20)
                .show(160);
        scene.showText(80, "Fluids work the same way: tank 0 fills from 0 to 1000 mB in one second.",
                "流体同理：0 号储罐在 1 秒内从 0 灌到 1000 mB。")
                .attachKeyFrame();
        scene.idle(180);

        // 第四段：覆盖板是机器状态，跟界面无关。
        MachineEdits.placeCover(scene, machinePos, Direction.UP, GTItems.CONVEYOR_MODULE_LV.asStack(), 10);
        scene.idle(40);
        scene.showText(70, "Covers can be put on a chosen side as well: a conveyor on top.",
                "覆盖板也能指定面：顶面放一条传送带。")
                .pointAt(util.vector().topOf(machinePos))
                .attachKeyFrame();
        scene.idle(150);

        // 第五段：只换模型，配方逻辑不动。
        MachineEdits.setWorkingModel(scene, machinePos, true, 10);
        scene.showText(70, "The model can be switched between working and idle: only the front overlay changes.",
                "模型可以单独切成工作中或待机：只换正面贴图，配方逻辑不动。")
                .pointAt(util.vector().centerOf(machinePos))
                .attachKeyFrame();
        scene.idle(90);
        MachineEdits.setWorkingModel(scene, machinePos, false, 10);
        scene.idle(30);

        // 第六段：只给一个配方 id，入料、进度条、成品全自动。
        scene.showUI(LV_CHEMICAL_REACTOR_UI).at(util.vector().topOf(machinePos)).forMachine(machinePos)
                .recipe("gtceu:chemical_reactor/sodium_bisulfate_from_salt", 10)
                .show(160);
        scene.showText(80,
                "One recipe id does the rest: the panel fills the inputs, sets the circuit it needs, runs while the bar moves, then drops the product in.",
                "只给一个配方 id 就够了：面板自己入料、自己把电路设成配方要的那一档，进度条走着的时候机器就是工作状态，走完成品出来。")
                .attachKeyFrame();
        scene.idle(180);

        // 第七段：自动输出口。
        scene.showText(70,
                "Auto-output sides are machine state as well: items leave through the west side, fluids through the south side.",
                "自动输出口也是机器状态：物品走西面、流体走南面。")
                .pointAt(util.vector().centerOf(machinePos))
                .attachKeyFrame();
        MachineEdits.setItemOutput(scene, machinePos, Direction.WEST, 10);
        scene.idle(80);
        scene.rotateCameraY(180);
        scene.idle(40);
        MachineEdits.setFluidOutput(scene, machinePos, Direction.SOUTH, 10);
        scene.idle(150);
        scene.rotateCameraY(-180);
        scene.idle(30);

        // 第八段：原版整套界面，配置器那一列的开关逐个套红框。
        scene.showUI(FULL_CHEMICAL_REACTOR_UI).at(util.vector().topOf(machinePos)).forMachine(machinePos)
                .outlinePowerToggle(20)
                .outlineAutoOutput(20)
                .outlineCircuitButton(20)
                .show(160);
        scene.showText(80,
                "showFullUI() draws GT's whole UI, and each switch in that configurator column can be boxed on its own.",
                "showFullUI() 会把原版 GT 的整套界面画出来，那一列配置器里的开关也能逐个套红框。")
                .attachKeyFrame();
        scene.idle(180);
        scene.markAsFinished();
    }
}
