package io.github.cpearl0.ctnhcore.client.ponder.Kinetic;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
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
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

public class BigDam {

    private BigDam() {}

    @Key("ctnhcore.ponder.big_dam_common.title")
    @CN("三峡大坝")
    @EN("Big Dam")
    static Lang CommonTitle;
    @Key("ctnhcore.ponder.big_dam_common.header")
    @CN("如何搭建三峡大坝")
    @EN("How to build Big Dam")
    static Lang CommonHeader;
    @Key("ctnhcore.ponder.big_dam_common.text_1")
    @CN("放置三峡大坝主方块")
    @EN("Place Big Dam Controller Block")
    static Lang CommonText1;
    @Key("ctnhcore.ponder.big_dam_common.text_2")
    @CN("使用任意终端蹲下右键进行一键放置")
    @EN("Use any terminal, sneak down, and right-click for one-click placement")
    static Lang CommonText2;
    @Key("ctnhcore.ponder.big_dam_work.header")
    @CN("如何使三峡大坝工作")
    @EN("How to make the Big Dam work")
    static Lang WorkHeader;
    @Key("ctnhcore.ponder.big_dam_work.text_1")
    @CN("你可以在石砖的位置放置接口，如输入仓，应力输出仓等")
    @EN("You can place interfaces at the location of the stone bricks, such as input bins, stress output bins, etc")
    static Lang WorkText1;
    @Key("ctnhcore.ponder.big_dam_work.text_2")
    @CN("不过需要注意的是，如果你没有放置足够的应力输出仓，三峡大坝的应力不会被完全输出")
    @EN("However, it should be noted that if you do not place enough kinetic output hatch, the stress of the Big Dam will not be fully outputted")
    static Lang WorkText2;
    @Key("ctnhcore.ponder.big_dam_work.text_3")
    @CN("现在只需要输入润滑油，就能使三峡大坝输出应力了")
    @EN("Now, just by inputting lubricating oil, the Big Dam can output stress")
    static Lang WorkText3;

    public static void Common(SceneBuilder builder, SceneBuildingUtil util) {
        Selection MainBlock = util.select().position(23, 2, 1);
        Vec3 MainBlockTextVec = util.vector().blockSurface(util.grid().at(24, 2, 1), Direction.WEST);

        CTNHPonderSceneBuilder scene = new CTNHPonderSceneBuilder(builder);
        scene.title("big_dam_common");
        scene.initAll(util);
        scene.scaleSceneView(0.2F);
        scene.setSceneOffsetY(-5);
        scene.idle(50);
        scene.showText(40)
                .pointAt(MainBlockTextVec)
                .attachKeyFrame();
        scene.world().showSection(MainBlock, Direction.NORTH);
        scene.world().setBlocks(MainBlock, CTPPMultiblockMachines.BIG_DAM.defaultBlockState(), false);
        scene.idle(50);
        scene.overlay().showControls(MainBlockTextVec, Pointing.RIGHT, 40)
                .rightClick()
                .withItem(GTItems.TERMINAL.asStack())
                .whileSneaking();
        scene.showText(40)
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

        scene.title("big_dam_work", "");
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
        scene.showText(40)
                .attachKeyFrame();
        scene.idle(50);
        scene.showText(40)
                .attachKeyFrame();
        scene.idle(50);
        scene.overlay().showControls(InputHatchVec, Pointing.RIGHT, 40)
                .rightClick()
                .withItem(GTMaterials.Lubricant.getBucket().getDefaultInstance())
                .whileSneaking();
        scene.showText(40)
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
