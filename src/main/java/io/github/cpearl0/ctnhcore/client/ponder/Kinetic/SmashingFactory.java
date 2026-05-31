package io.github.cpearl0.ctnhcore.client.ponder.Kinetic;

import io.github.cpearl0.ctnhcore.client.ponder.CTNHPonderSceneBuilder;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GTItems;

import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.element.ElementLink;
import net.createmod.ponder.api.element.WorldSectionElement;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;

import com.mo_guang.ctpp.registry.CTPPMachines;
import com.simibubi.create.AllBlocks;

public class SmashingFactory {

    private SmashingFactory() {}

    public static void Common(SceneBuilder builder, SceneBuildingUtil util) {
        Selection mainBlock = util.select().position(3, 2, 1);
        Vec3 mainBlockVec = util.vector().blockSurface(util.grid().at(3, 2, 1), Direction.WEST);

        Selection crushingWheelLeft = util.select().fromTo(2, 3, 2, 2, 3, 4);
        Selection crushingWheelRight = util.select().fromTo(4, 3, 2, 4, 3, 4);
        Selection crushingWheelControl = util.select().fromTo(3, 3, 2, 3, 3, 4);

        CTNHPonderSceneBuilder scene = new CTNHPonderSceneBuilder(builder);
        scene.title("smashing_factory_common", "How to build Smashing Factory", "如何搭建粉碎工厂", "Smashing Factory", "粉碎工厂");
        scene.init5x5(util);
        scene.setSceneOffsetY(-1);
        scene.idle(10);
        scene.world().showSection(mainBlock, Direction.DOWN);
        scene.showText(60, "First, you need a smashing factory main block", "首先，你需要一个粉碎工厂主方块")
                .pointAt(mainBlockVec)
                .attachKeyFrame();
        scene.idle(60);
        scene.overlay().showControls(mainBlockVec, Pointing.LEFT, 40)
                .rightClick()
                .withItem(GTItems.TERMINAL.asStack())
                .whileSneaking();
        scene.idle(40);
        Selection baseSelection = util.select().fromTo(1, 1, 1, 5, 3, 5);
        Selection structureWithoutWheels = baseSelection.substract(crushingWheelLeft).substract(crushingWheelRight);
        scene.world().showSection(structureWithoutWheels, Direction.DOWN);
        ElementLink<WorldSectionElement>[] crushingWheelLinks = new ElementLink[2];
        crushingWheelLinks[0] = scene.world().showIndependentSection(crushingWheelLeft, Direction.DOWN);
        crushingWheelLinks[1] = scene.world().showIndependentSection(crushingWheelRight, Direction.DOWN);

        scene.showText(40, "One click placement using the terminal", "使用终端蹲下右键进行一键放置")
                .attachKeyFrame();
        scene.idle(60);
        scene.addKeyframe();
        scene.showText(100,
                "Due to issues with the terminal, the terminal placed crushing wheel is not placed in the correct direction, but rather appears in this form",
                "由于终端的问题，终端放置的粉碎轮方向不正确，而是以这种形式出现")
                .attachKeyFrame();
        scene.idle(40);
        Selection allCrushingWheels = crushingWheelLeft.add(crushingWheelRight);
        scene.world().replaceBlocks(allCrushingWheels, Blocks.AIR.defaultBlockState(), true);
        scene.world().setBlocks(crushingWheelLeft, AllBlocks.CRUSHING_WHEEL.get().defaultBlockState(), true);
        scene.world().setBlocks(crushingWheelRight, AllBlocks.CRUSHING_WHEEL.get().defaultBlockState(), true);
        scene.overlay().showOutline(PonderPalette.RED, "", allCrushingWheels, 60);
        scene.idle(100);
        scene.addKeyframe();
        scene.showText(80,
                "At this point, we need to manually replace the crushing wheel in the correct direction",
                "此时，我们需要手动替换为正确方向的粉碎轮")
                .attachKeyFrame();
        BlockState crushingWheelZ = AllBlocks.CRUSHING_WHEEL.get().defaultBlockState()
                .setValue(BlockStateProperties.AXIS, Direction.Axis.Z);
        scene.world().setBlocks(crushingWheelLeft, crushingWheelZ, true);
        scene.world().setBlocks(crushingWheelRight, crushingWheelZ, true);
        scene.world().replaceBlocks(crushingWheelControl, Blocks.AIR.defaultBlockState(), false);
        scene.idle(100);
        scene.showText(80, "Access stress", "接入应力")
                .pointAt(util.vector().blockSurface(util.grid().at(3, 1, 2), Direction.WEST))
                .attachKeyFrame();
        scene.world().setKineticSpeed(util.select().position(3, 1, 2), 128);
        scene.world().rotateSection(crushingWheelLinks[0], 0, 0, -360, 400);
        scene.world().rotateSection(crushingWheelLinks[1], 0, 0, 360, 400);
        scene.overlay().showOutline(PonderPalette.RED, "", util.select().position(2, 2, 1), 80);
        scene.idle(100);
        BlockPos kineticInputBoxPos = util.grid().at(4, 2, 1);
        scene.world().setBlock(kineticInputBoxPos, CTPPMachines.KINETIC_INPUT_BOX[GTValues.MV].defaultBlockState(),
                true);
        scene.showText(80,
                "If there are multiple kinetic input hatch of different levels in the smashing factory, the actual operating speed will be calculated based on the highest level of kinetic input hatch",
                "如果粉碎工厂中有多个不同等级的应力输入箱，实际运行速度将根据最高等级的应力输入箱计算")
                .pointAt(util.vector().blockSurface(util.grid().at(5, 1, 2), Direction.WEST))
                .attachKeyFrame();
        scene.idle(100);
        scene.world().setBlocks(util.select().position(3, 3, 1),
                CTPPMachines.MECHANICAL_UPGRADE_BUS.defaultBlockState(), true);
        scene.showText(80,
                "Some recipes also require upgrading using a mechanical upgrade bus before they can be executed",
                "一些配方还需要使用机械升级仓进行升级后才能运行")
                .pointAt(util.vector().blockSurface(util.grid().at(4, 3, 1), Direction.WEST))
                .attachKeyFrame();
        scene.idle(100);
        scene.showText(80,
                "Now you can use the smashing factory normally. Please note that this machine does not produce any grinding by-products",
                "现在你可以正常使用粉碎工厂了。请注意，此机器不会产生任何研磨副产物")
                .pointAt(util.vector().blockSurface(util.grid().at(4, 1, 2), Direction.WEST))
                .attachKeyFrame();
        scene.markAsFinished();
    }
}
