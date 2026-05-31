package io.github.cpearl0.ctnhcore.client.ponder.Kinetic;

import io.github.cpearl0.ctnhcore.client.ponder.CTNHPonderSceneBuilder;

import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;

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
import net.minecraft.world.phys.Vec3;

import com.mo_guang.ctpp.registry.CTPPMultiblockMachines;

public class BigDam {

    private BigDam() {}

    public static void Common(SceneBuilder builder, SceneBuildingUtil util) {
        Selection MainBlock = util.select().position(23, 2, 1);
        Vec3 MainBlockTextVec = util.vector().blockSurface(util.grid().at(24, 2, 1), Direction.WEST);

        CTNHPonderSceneBuilder scene = new CTNHPonderSceneBuilder(builder);
        scene.title("big_dam_common", "How to build Big Dam", "如何搭建三峡大坝", "Big Dam", "三峡大坝");
        scene.initAll(util);
        scene.scaleSceneView(0.2F);
        scene.setSceneOffsetY(-5);
        scene.idle(50);
        scene.showText(40, "Place Big Dam Controller Block", "放置三峡大坝主方块")
                .pointAt(MainBlockTextVec)
                .attachKeyFrame();
        scene.world().showSection(MainBlock, Direction.NORTH);
        scene.world().setBlocks(MainBlock, CTPPMultiblockMachines.BIG_DAM.defaultBlockState(), false);
        scene.idle(50);
        scene.overlay().showControls(MainBlockTextVec, Pointing.RIGHT, 40)
                .rightClick()
                .withItem(GTItems.TERMINAL.asStack())
                .whileSneaking();
        scene.showText(40, "Use any terminal, sneak down, and right-click for one-click placement", "使用任意终端蹲下右键进行一键放置")
                .pointAt(MainBlockTextVec)
                .attachKeyFrame();
        scene.idle(20);
        scene.world().showSection(util.select().everywhere(), Direction.NORTH);
        scene.markAsFinished();
    }

    public static void Work(SceneBuilder builder, SceneBuildingUtil util) {
        Selection IOPortLeft = util.select().fromTo(40, 2, 1, 24, 2, 1);
        Selection IOPortRight = util.select().fromTo(22, 2, 1, 6, 2, 1);
        Selection MainBlock = util.select().position(23, 2, 1);

        Selection[] waterwhellSelections = new Selection[12];
        Selection[] waterSelections = new Selection[12];

        int startX = 7;

        for (int i = 0; i < 12; i++) {
            int currentStartX = startX + (i * 3);
            int currentEndX = currentStartX - 1;

            waterwhellSelections[i] = util.select().fromTo(currentStartX, 13, 5, currentEndX, 3, 15);

            waterSelections[i] = util.select().fromTo(currentEndX, 3, 12, currentEndX + 1, 3, 15);
        }

        BlockPos InputHatchPos = util.grid().at(25, 3, 1);
        Vec3 InputHatchVec = util.vector().blockSurface(InputHatchPos.below(), Direction.WEST);

        CTNHPonderSceneBuilder scene = new CTNHPonderSceneBuilder(builder);

        scene.title("big_dam_work", "How to make the Big Dam work", "如何使三峡大坝工作");
        scene.initAll(util);
        scene.scaleSceneView(0.2F);
        scene.setSceneOffsetY(-5);
        scene.world().setBlocks(MainBlock, CTPPMultiblockMachines.BIG_DAM.defaultBlockState(), false);

        Selection baseSelection = util.select().everywhere();

        for (Selection s : waterwhellSelections) {
            baseSelection = baseSelection.substract(s);
        }

        scene.world().showSection(baseSelection, Direction.NORTH);
        scene.idle(10);

        ElementLink<WorldSectionElement>[] waterLinks = new ElementLink[12];

        for (int i = 0; i < 12; i++) {
            waterLinks[i] = scene.world().showIndependentSection(waterwhellSelections[i], Direction.NORTH);
        }

        for (int i = 0; i < 12; i++) {
            scene.world().setBlocks(waterSelections[i], Blocks.AIR.defaultBlockState(), false);
        }

        scene.idle(20);

        scene.overlay().showOutline(PonderPalette.RED, "left", IOPortLeft, 40);
        scene.overlay().showOutline(PonderPalette.RED, "right", IOPortRight, 40);
        scene.showText(40,
                "You can place interfaces at the location of the stone bricks, such as input bins, stress output bins, etc",
                "你可以在石砖的位置放置接口，如输入仓，应力输出仓等")
                .attachKeyFrame();
        scene.idle(50);
        scene.showText(40,
                "However, it should be noted that if you do not place enough kinetic output hatch, the stress of the Big Dam will not be fully outputted",
                "不过需要注意的是，如果你没有放置足够的应力输出仓，三峡大坝的应力不会被完全输出")
                .attachKeyFrame();
        scene.idle(50);
        scene.overlay().showControls(InputHatchVec, Pointing.RIGHT, 40)
                .rightClick()
                .withItem(GTMaterials.Lubricant.getBucket().getDefaultInstance())
                .whileSneaking();
        scene.showText(40, "Now, just by inputting lubricating oil, the Big Dam can output stress",
                "现在只需要输入润滑油，就能使三峡大坝输出应力了")
                .pointAt(InputHatchVec)
                .attachKeyFrame();
        scene.world().setKineticSpeed(util.select().position(22, 2, 1), 512);

        for (ElementLink<WorldSectionElement> link : waterLinks) {
            scene.world().rotateSection(link, 360, 0, 0, 400);
        }

        scene.idle(50);
        scene.markAsFinished();
    }
}
