package io.github.cpearl0.ctnhcore.client.ponder.Kinetic;

import com.gregtechceu.gtceu.common.data.GTItems;
import io.github.cpearl0.ctnhcore.client.ponder.CTNHPonderSceneBuilder;
import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.element.EntityElement;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.Vec3;

import static io.github.cpearl0.ctnhcore.client.ponder.Kinetic.CTNHKineticPondersLang.*;

public class Meadow {
    private Meadow() {
    }

    public static void Common(SceneBuilder builder, SceneBuildingUtil util) {
        Selection mainBlock = util.select().position(6, 1, 1);
        Vec3 mainBlockVec = util.vector().blockSurface(util.grid().at(6, 1, 1), Direction.WEST);
        
        CTNHPonderSceneBuilder scene = new CTNHPonderSceneBuilder(builder);
        scene.title("meadow_common", MeadowHeader.translate().getContents().toString());
        scene.initAll(util);
        scene.setSceneOffsetY(10);

        scene.world().setKineticSpeed(util.select().position(5, 1, 1), 0);

        scene.world().showSection(mainBlock, Direction.DOWN);
        scene.overlay().showText(40)
                .text(MeadowText1.translate().getContents().toString())
                .pointAt(util.vector().blockSurface(util.grid().at(6, 2, 2), Direction.WEST))
                .attachKeyFrame();
        scene.idle(60);
        scene.overlay().showControls(mainBlockVec, Pointing.LEFT, 40)
                .rightClick()
                .withItem(GTItems.TERMINAL.asStack())
                .whileSneaking();
        scene.overlay().showText(40)
                .text(MeadowText2.translate().getContents().toString())
                .pointAt(util.vector().blockSurface(util.grid().at(6, 2, 1), Direction.WEST))
                .attachKeyFrame();
        scene.idle(10);
        scene.world().showSection(util.select().fromTo(1, 1, 1, 11, 7, 11), Direction.DOWN);
        scene.idle(60);
        scene.overlay().showOutline(PonderPalette.RED, "", util.select().fromTo(4, 1, 6, 2, 1, 2), 40);
        scene.overlay().showText(40)
                .text(MeadowText3.translate().getContents().toString())
                .pointAt(util.vector().blockSurface(util.grid().at(2, 5, 1), Direction.WEST))
                .attachKeyFrame();
        scene.idle(60);
        scene.overlay().showOutline(PonderPalette.RED, "", util.select().fromTo(4, 1, 1, 3, 1, 1), 40);
        scene.overlay().showText(40)
                .text(MeadowText4.translate().getContents().toString())
                .pointAt(util.vector().blockSurface(util.grid().at(3, 2, 1), Direction.WEST))
                .attachKeyFrame();
        scene.idle(60);
        scene.overlay().showText(40)
                .text(MeadowText5.translate().getContents().toString())
                .pointAt(util.vector().blockSurface(util.grid().at(6, 1, 1), Direction.WEST))
                .attachKeyFrame();
        scene.world().setKineticSpeed(util.select().position(5, 1, 1), 128);
        scene.idle(60);
        scene.overlay().showText(60)
                .text(MeadowText6.translate().getContents().toString())
                .pointAt(util.vector().blockSurface(util.grid().at(5, 1, 5), Direction.WEST))
                .attachKeyFrame();
        scene.world().createEntity(world -> {
            Entity cow = EntityType.COW.create(world);
            cow.setYRot(180);
            cow.setPos(new Vec3(3, 2, 5));
            return cow;
        });
        scene.idle(10);
        scene.world().createEntity(world -> {
            Entity sheep = EntityType.SHEEP.create(world);
            sheep.setYRot(180);
            sheep.setPos(new Vec3(5, 2, 5));
            return sheep;
        });
        scene.idle(10);
        scene.world().createEntity(world -> {
            Entity pig = EntityType.PIG.create(world);
            pig.setYRot(180);
            pig.setPos(new Vec3(7, 2, 5));
            return pig;
        });
        scene.idle(10);
        scene.world().createEntity(world -> {
            Entity chicken = EntityType.CHICKEN.create(world);
            chicken.setYRot(180);
            chicken.setPos(new Vec3(9, 2, 5));
            return chicken;
        });
        scene.idle(10);
        scene.idle(60);
        scene.overlay().showOutline(PonderPalette.RED, "", util.select().fromTo(3, 2, 5, 2, 3, 4), 30);
        scene.overlay().showText(30)
                .text(MeadowText7.translate().getContents().toString())
                .pointAt(util.vector().blockSurface(util.grid().at(3, 3, 5), Direction.WEST))
                .attachKeyFrame();
        scene.idle(40);
        scene.overlay().showOutline(PonderPalette.RED, "", util.select().fromTo(5, 2, 5, 4, 3, 4), 30);
        scene.overlay().showText(30)
                .text(MeadowText8.translate().getContents().toString())
                .pointAt(util.vector().blockSurface(util.grid().at(5, 3, 5), Direction.WEST))
                .attachKeyFrame();
        scene.idle(40);
        scene.overlay().showOutline(PonderPalette.RED, "", util.select().fromTo(7, 2, 5, 6, 3, 4), 30);
        scene.overlay().showText(30)
                .text(MeadowText9.translate().getContents().toString())
                .pointAt(util.vector().blockSurface(util.grid().at(7, 3, 5), Direction.WEST))
                .attachKeyFrame();
        scene.idle(40);
        scene.overlay().showOutline(PonderPalette.RED, "", util.select().fromTo(9, 2, 5, 8, 3, 4), 30);
        scene.overlay().showText(30)
                .text(MeadowText10.translate().getContents().toString())
                .pointAt(util.vector().blockSurface(util.grid().at(9, 3, 5), Direction.WEST))
                .attachKeyFrame();
        scene.idle(40);
        scene.overlay().showOutline(PonderPalette.RED, "", util.select().fromTo(9, 2, 5, 2, 3, 4), 60);
        scene.overlay().showText(60)
                .text(MeadowText11.translate().getContents().toString())
                .pointAt(util.vector().blockSurface(util.grid().at(3, 3, 5), Direction.WEST))
                .attachKeyFrame();
        scene.idle(60);
        scene.world().showSection(util.select().fromTo(4, 1, 0, 3, 1, 0), Direction.DOWN);
        scene.overlay().showText(40)
                .text(MeadowText12.translate().getContents().toString())
                .pointAt(util.vector().blockSurface(util.grid().at(2, 1, 1), Direction.WEST))
                .attachKeyFrame();
        scene.markAsFinished();
    }
}