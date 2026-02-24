package io.github.cpearl0.ctnhcore.client.ponder.Kinetic;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.mo_guang.ctpp.registry.CTPPMachines;
import com.mo_guang.ctpp.registry.CTPPMultiblockMachines;
import com.simibubi.create.AllBlocks;
import io.github.cpearl0.ctnhcore.client.ponder.CTNHPonderSceneBuilder;
import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.element.ElementLink;
import net.createmod.ponder.api.element.WorldSectionElement;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

import static io.github.cpearl0.ctnhcore.client.ponder.Kinetic.CTNHKineticPondersLang.*;

public class SmashingFactory {
    private SmashingFactory() {
    }

    public static void Common(SceneBuilder builder, SceneBuildingUtil util) {
        Selection mainBlock = util.select().position(3, 2, 1);
        Vec3 mainBlockVec = util.vector().blockSurface(util.grid().at(3, 2, 1), Direction.WEST);

        Selection crushingWheelLeft = util.select().fromTo(2, 3, 2, 2, 3, 4);
        Selection crushingWheelRight = util.select().fromTo(4, 3, 2, 4, 3, 4);
        Selection crushingWheelControl = util.select().fromTo(3, 3, 2, 3, 3, 4);
        
        CTNHPonderSceneBuilder scene = new CTNHPonderSceneBuilder(builder);
        scene.title("smashing_factory_common", SmashingFactoryHeader.translate().getContents().toString());
        scene.init5x5(util);
        scene.setSceneOffsetY(-1);
        scene.idle(10);
        scene.world().showSection(mainBlock, Direction.DOWN);
        scene.overlay().showText(60)
                .text(SmashingFactoryText1.translate().getContents().toString())
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
        
        scene.overlay().showText(40)
                .text(SmashingFactoryText2.translate().getContents().toString())
                .attachKeyFrame();
        scene.idle(60);
        scene.addKeyframe();
        scene.overlay().showText(100)
                .text(SmashingFactoryText3.translate().getContents().toString());
        scene.idle(40);
        Selection allCrushingWheels = crushingWheelLeft.add(crushingWheelRight);
        scene.world().replaceBlocks(allCrushingWheels, Blocks.AIR.defaultBlockState(), true);
        scene.world().setBlocks(crushingWheelLeft, AllBlocks.CRUSHING_WHEEL.get().defaultBlockState(), true);
        scene.world().setBlocks(crushingWheelRight, AllBlocks.CRUSHING_WHEEL.get().defaultBlockState(), true);
        scene.overlay().showOutline(PonderPalette.RED, "", allCrushingWheels, 60);
        scene.idle(100);
        scene.addKeyframe();
        scene.overlay().showText(80)
                .text(SmashingFactoryText4.translate().getContents().toString());
        BlockState crushingWheelZ = AllBlocks.CRUSHING_WHEEL.get().defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Z);
        scene.world().setBlocks(crushingWheelLeft, crushingWheelZ, true);
        scene.world().setBlocks(crushingWheelRight, crushingWheelZ, true);
        scene.world().replaceBlocks(crushingWheelControl, Blocks.AIR.defaultBlockState(), false);
        scene.idle(100);
        scene.overlay().showText(80)
                .text(SmashingFactoryText5.translate().getContents().toString())
                .pointAt(util.vector().blockSurface(util.grid().at(3, 1, 2), Direction.WEST))
                .attachKeyFrame();
        scene.world().setKineticSpeed(util.select().position(3, 1, 2), 128);
        scene.world().rotateSection(crushingWheelLinks[0], 0,0,-360,   400);
        scene.world().rotateSection(crushingWheelLinks[1], 0, 0, 360, 400);
        scene.overlay().showOutline(PonderPalette.RED, "", util.select().position(2, 2, 1), 80);
        scene.idle(100);
        BlockPos kineticInputBoxPos = util.grid().at(4, 2, 1);
        scene.world().setBlock(kineticInputBoxPos, CTPPMachines.KINETIC_INPUT_BOX[GTValues.MV].defaultBlockState(), true);
        scene.overlay().showText(80)
                .text(SmashingFactoryText6.translate().getContents().toString())
                .pointAt(util.vector().blockSurface(util.grid().at(5, 1, 2), Direction.WEST))
                .attachKeyFrame();
        scene.idle(100);
        scene.world().setBlocks(util.select().position(3, 3, 1), CTPPMachines.MECHANICAL_UPGRADE_BUS.defaultBlockState(), true);
        scene.overlay().showText(80)
                .text(SmashingFactoryText7.translate().getContents().toString())
                .pointAt(util.vector().blockSurface(util.grid().at(4, 3, 1), Direction.WEST))
                .attachKeyFrame();
        scene.idle(100);
        scene.overlay().showText(80)
                .text(SmashingFactoryText8.translate().getContents().toString())
                .pointAt(util.vector().blockSurface(util.grid().at(4, 1, 2), Direction.WEST))
                .attachKeyFrame();
        scene.markAsFinished();
    }
}