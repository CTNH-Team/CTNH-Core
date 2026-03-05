package io.github.cpearl0.ctnhcore.client.ponder.Electric;

import io.github.cpearl0.ctnhcore.client.ponder.CTNHPonderSceneBuilder;

import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

import org.antarcticgardens.newage.NewAgeBlocks;

import static io.github.cpearl0.ctnhcore.client.ponder.Electric.CTNHElectricPondersLang.*;

public class CarbonBrushes {

    private CarbonBrushes() {}

    public static void ponder(SceneBuilder builder, SceneBuildingUtil util) {
        CTNHPonderSceneBuilder scene = new CTNHPonderSceneBuilder(builder);

        Selection magnet1 = util.select().fromTo(4, 5, 1, 2, 5, 8);
        Selection magnet2 = util.select().fromTo(4, 1, 1, 2, 1, 8);
        Selection magnet3 = util.select().fromTo(1, 4, 1, 1, 2, 8);
        Selection magnet4 = util.select().fromTo(5, 2, 1, 5, 4, 8);
        Selection allMagnets = magnet1.add(magnet2).add(magnet3).add(magnet4);
        scene.title("carbon_brushes", CarbonBrushesHeader.translate().getContents().toString());
        scene.configureBasePlate(0, 0, 7);
        scene.scaleSceneView(0.75f);
        scene.setSceneOffsetY(-1);

        scene.idle(10);
        scene.world().showSection(util.select().layer(0), Direction.NORTH);
        scene.idle(10);
        scene.world().showSection(util.select().position(3, 3, 0), Direction.NORTH);
        scene.overlay().showText(60)
                .text(CarbonBrushesText1.translate().getContents().toString())
                .pointAt(util.vector().blockSurface(util.grid().at(3, 3, 0), Direction.WEST))
                .attachKeyFrame();
        scene.idle(70);
        scene.world().showSection(util.select().fromTo(3, 3, 1, 3, 3, 1), Direction.NORTH);
        scene.idle(10);
        scene.overlay().showText(60)
                .text(CarbonBrushesText2.translate().getContents().toString())
                .pointAt(util.vector().blockSurface(util.grid().at(2, 3, 1), Direction.WEST))
                .attachKeyFrame();
        scene.world().setKineticSpeed(util.select().position(3, 3, 0), 64);
        scene.world().setKineticSpeed(util.select().fromTo(3, 3, 1, 3, 3, 8), 64);
        scene.idle(70);
        scene.world().showSection(util.select().position(3, 5, 1), Direction.NORTH);
        scene.idle(10);
        scene.overlay().showText(60)
                .text(CarbonBrushesText3.translate().getContents().toString())
                .pointAt(util.vector().blockSurface(util.grid().at(3, 5, 1), Direction.DOWN))
                .attachKeyFrame();
        scene.idle(70);
        scene.world().showSection(util.select().position(2, 3, 0), Direction.DOWN);
        scene.world().showSection(util.select().position(2, 2, 0), Direction.EAST);
        scene.idle(10);
        scene.overlay().showText(60)
                .text(CarbonBrushesText4.translate().getContents().toString())
                .pointAt(util.vector().blockSurface(util.grid().at(3, 2, 1), Direction.WEST))
                .attachKeyFrame();

        scene.idle(70);
        scene.overlay().showText(60)
                .text(CarbonBrushesText5.translate().getContents().toString())
                .pointAt(util.vector().blockSurface(util.grid().at(2, 5, 1), Direction.WEST))
                .attachKeyFrame();
        scene.idle(10);
        scene.world().showSection(util.select().fromTo(1, 5, 1, 5, 1, 1), Direction.NORTH);
        scene.idle(70);
        scene.overlay().showText(60)
                .text(CarbonBrushesText6.translate().getContents().toString())
                .attachKeyFrame();
        scene.idle(10);
        scene.world().showSection(util.select().fromTo(1, 5, 2, 5, 1, 9), Direction.NORTH);
        scene.idle(70);
        scene.overlay().showText(60)
                .text(CarbonBrushesText7.translate().getContents().toString())
                .pointAt(util.vector().blockSurface(util.grid().at(2, 5, 1), Direction.WEST))
                .attachKeyFrame();
        scene.idle(60);
        scene.overlay().showText(60)
                .text(CarbonBrushesText8.translate().getContents().toString())
                .pointAt(util.vector().blockSurface(util.grid().at(4, 3, 0), Direction.WEST))
                .attachKeyFrame();
        scene.overlay().showText(20);
        scene.world().setKineticSpeed(util.select().position(3, 3, 0), 128);
        scene.world().setKineticSpeed(util.select().fromTo(3, 3, 1, 3, 3, 8), 128);
        scene.idle(70);
        scene.overlay().showText(100)
                .text(CarbonBrushesText9.translate().getContents().toString())
                .attachKeyFrame();
        scene.idle(20);
        BlockState redstoneMagnet = NewAgeBlocks.REDSTONE_MAGNET.getDefaultState();
        scene.world().replaceBlocks(allMagnets, redstoneMagnet, true);
        scene.idle(20);
        BlockState fluxuatedMagnetite = NewAgeBlocks.FLUXUATED_MAGNETITE.getDefaultState();
        scene.world().replaceBlocks(allMagnets, fluxuatedMagnetite, true);
        scene.idle(20);
        BlockState netheriteMagnet = NewAgeBlocks.NETHERITE_MAGNET.getDefaultState();
        scene.world().replaceBlocks(allMagnets, netheriteMagnet, true);
        scene.idle(20);
        scene.markAsFinished();
    }
}
