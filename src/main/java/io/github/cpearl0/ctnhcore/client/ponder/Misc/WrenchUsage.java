package io.github.cpearl0.ctnhcore.client.ponder.Misc;

import io.github.cpearl0.ctnhcore.client.ponder.CTNHCorePonderSceneBuilder;

import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.common.data.GTMaterialItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.element.InputElementBuilder;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

import tech.vixhentx.mcmod.ctnhlib.client.ponder.machine.MachineEdits;

/**
 * 扳手的三种玩法：不蹲着时点九宫格某一格，把自动输出口挪到那一格对应的方位；蹲下再点，把正面转到那一面；
 * 蹲下右键空气，在物品、流体、两者三档模式之间循环，只有当前那一档的输出口会跟着九宫格动。
 *
 * <p>
 * 格子从面外侧看是行优先的：0/2/6/8 是四角（对应背面），1 上、3 左、5 右、7 下，4 是正在看的这一面。
 *
 * <p>
 * 机器刚放下时输出口在正面背后，实机不给输出口那一面画图标，四角就会空着。开场先把它压到底面。
 *
 * <p>
 * storyboard：3x3 地板 + (1,1,1) 的 {@code gtceu:lv_chemical_reactor}（facing=north）。
 */
public class WrenchUsage {

    private WrenchUsage() {}

    public static void Common(SceneBuilder builder, SceneBuildingUtil util) {
        CTNHCorePonderSceneBuilder scene = new CTNHCorePonderSceneBuilder(builder);
        scene.title("wrench_usage", "Using a Wrench", "扳手的用法", "Chemical Reactor", "化学反应釜");
        scene.showBasePlate();
        scene.idle(10);

        BlockPos machinePos = util.grid().at(1, 1, 1);
        scene.world().showSection(util.select().position(machinePos), Direction.DOWN);
        MachineEdits.setAutoOutput(scene, machinePos, Direction.DOWN);
        scene.idle(10);

        scene.showToolGrid(machinePos, Direction.NORTH)
                .show(170);
        scene.idle(170);

        scene.showToolGrid(machinePos, Direction.NORTH)
                .highlight(0, 0)
                .highlight(2, 6)
                .highlight(6, 12)
                .highlight(8, 18)
                .show(220);
        scene.showText(80,
                "Right-clicking any of the four corners with a wrench moves the auto-output side to the back of the machine.",
                "使用扳手右键四个角会将自动输出口调整到机器后方")
                .attachKeyFrame();
        scene.idle(60);
        MachineEdits.setAutoOutput(scene, machinePos, Direction.SOUTH, 10);
        scene.idle(140);

        scene.rotateCameraY(180);
        scene.idle(150);
        scene.rotateCameraY(-180);
        scene.idle(30);

        scene.showToolGrid(machinePos, Direction.NORTH)
                .highlight(1)
                .show(200);
        scene.showText(80,
                "Right-clicking the upper cell with a wrench moves the auto-output side to the top of the machine.",
                "当使用扳手右键上方时，会将自动输出口调整到机器上方")
                .attachKeyFrame();
        scene.idle(60);
        MachineEdits.setAutoOutput(scene, machinePos, Direction.UP, 10);
        scene.idle(140);

        scene.showToolGrid(machinePos, Direction.NORTH)
                .highlight(3, 0)
                .highlight(5, 8)
                .highlight(7, 16)
                .show(210);
        scene.showText(80,
                "And so on: right-clicking any side with a wrench moves the auto-output side of the machine to that side.",
                "以此类推，使用扳手右键任意方位则可将机器的自动输出口调整到相对应的方位")
                .attachKeyFrame();
        scene.idle(210);

        // 空气里那一下：不对准任何方块，只落在机器跟前这段空当。
        Vec3 airPos = util.vector().centerOf(machinePos).add(0, 1.1, -1.2);
        showAirClick(scene, airPos, 120);
        scene.showText(80,
                "Sneaking and right-clicking the air cycles the wrench through item, fluid and both modes; the action bar names the current one.",
                "蹲下右键空气，扳手会在物品、流体、两者三种模式之间循环，动作栏会提示当前模式")
                .pointAt(airPos)
                .attachKeyFrame();
        scene.idle(120);

        scene.showToolGrid(machinePos, Direction.NORTH)
                .highlight(3)
                .show(190);
        scene.showText(80,
                "In item mode the grid only moves the item auto-output side; the fluid side stays where it is.",
                "物品模式下右键九宫格，只有物品的自动输出口会跟着动，流体输出口留在原处")
                .attachKeyFrame();
        scene.idle(50);
        MachineEdits.setItemOutput(scene, machinePos, Direction.WEST, 10);
        scene.idle(140);

        showAirClick(scene, airPos, 100);
        scene.idle(40);
        scene.showToolGrid(machinePos, Direction.NORTH)
                .highlight(3)
                .show(190);
        scene.showText(80,
                "Switch to fluid mode and click the same cell: this time the fluid side is the one that moves.",
                "切成流体模式再点同一格，这次动的换成流体输出口")
                .attachKeyFrame();
        scene.idle(50);
        MachineEdits.setFluidOutput(scene, machinePos, Direction.WEST, 10);
        scene.idle(140);

        showAirClick(scene, airPos, 100);
        scene.idle(40);
        scene.showToolGrid(machinePos, Direction.NORTH)
                .highlight(0, 0)
                .highlight(2, 6)
                .show(200);
        scene.showText(80,
                "In both mode a single click moves the item and fluid output sides together.",
                "两者模式下一次点击就把物品和流体输出口一起挪过去")
                .attachKeyFrame();
        scene.idle(50);
        MachineEdits.setAutoOutput(scene, machinePos, Direction.SOUTH, 10);
        scene.rotateCameraY(180);
        scene.idle(150);
        scene.rotateCameraY(-180);
        scene.idle(40);

        // 回放里没有真的蹲下，格子里的图标仍是挪输出口那套，会与这段讲的事打架，所以只留网格。
        scene.rotateCameraY(90);
        scene.idle(40);
        scene.showToolGrid(machinePos, Direction.EAST)
                .sneakClick()
                .hideTips()
                .show(220);
        scene.showText(90,
                "Sneaking and right-clicking a cell of the grid with a wrench turns the machine front to the direction of that cell.",
                "使用扳手蹲下右键九宫格，则会将机器正面旋转至对应方向")
                .pointAt(util.vector().centerOf(machinePos))
                .attachKeyFrame();
        scene.idle(60);
        MachineEdits.setFrontFacing(scene, machinePos, Direction.EAST, 10);
        scene.idle(170);

        scene.markAsFinished();
    }

    /** 在空当里挂一排「蹲下 + 右键 + 扳手」，指向那一次没有对准方块的点击。 */
    private static void showAirClick(CTNHCorePonderSceneBuilder scene, Vec3 at, int ticks) {
        InputElementBuilder controls = scene.overlay().showControls(at, Pointing.DOWN, ticks);
        controls.whileSneaking();
        controls.rightClick();
        controls.withItem(GTMaterialItems.TOOL_ITEMS.get(GTMaterials.Iron, GTToolType.WRENCH).asStack());
    }
}
