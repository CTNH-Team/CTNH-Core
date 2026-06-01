package io.github.cpearl0.ctnhcore.client.ponder.Kinetic;

import io.github.cpearl0.ctnhcore.client.ponder.CTNHCorePonderSceneBuilder;

import com.gregtechceu.gtceu.common.data.GTItems;

import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.Vec3;

public class Meadow {

    private Meadow() {}

    public static void Common(SceneBuilder builder, SceneBuildingUtil util) {
        Selection mainBlock = util.select().position(6, 1, 1);
        Vec3 mainBlockVec = util.vector().blockSurface(util.grid().at(6, 1, 1), Direction.WEST);

        CTNHCorePonderSceneBuilder scene = new CTNHCorePonderSceneBuilder(builder);
        scene.title("meadow_common", "How to build Meadow", "如何搭建牧场", "Meadow", "牧场");
        scene.initAll(util);
        scene.setSceneOffsetY(10);

        scene.world().setKineticSpeed(util.select().position(5, 1, 1), 0);

        scene.world().showSection(mainBlock, Direction.DOWN);
        scene.showText(40, "First, you need a meadow main block", "首先，你需要一个牧场主方块")
                .pointAt(util.vector().blockSurface(util.grid().at(6, 2, 2), Direction.WEST))
                .attachKeyFrame();
        scene.idle(60);
        scene.overlay().showControls(mainBlockVec, Pointing.LEFT, 40)
                .rightClick()
                .withItem(GTItems.TERMINAL.asStack())
                .whileSneaking();
        scene.showText(40, "One click placement using the terminal", "使用终端一键放置")
                .pointAt(util.vector().blockSurface(util.grid().at(6, 2, 1), Direction.WEST))
                .attachKeyFrame();
        scene.idle(10);
        scene.world().showSection(util.select().fromTo(1, 1, 1, 11, 7, 11), Direction.DOWN);
        scene.idle(60);
        scene.overlay().showOutline(PonderPalette.RED, "", util.select().fromTo(4, 1, 6, 2, 1, 2), 40);
        scene.showText(40, "Lily pad and water can not automatically place, remember to manually fill them in",
                "睡莲和水不能自动放置，记得手动填充")
                .pointAt(util.vector().blockSurface(util.grid().at(2, 5, 1), Direction.WEST))
                .attachKeyFrame();
        scene.idle(60);
        scene.overlay().showOutline(PonderPalette.RED, "", util.select().fromTo(4, 1, 1, 3, 1, 1), 40);
        scene.showText(40, "Ensure sufficient space for output items and liquids", "确保有足够的空间用于输出物品和液体")
                .pointAt(util.vector().blockSurface(util.grid().at(3, 2, 1), Direction.WEST))
                .attachKeyFrame();
        scene.idle(60);
        scene.showText(40, "Access stress", "接入应力")
                .pointAt(util.vector().blockSurface(util.grid().at(6, 1, 1), Direction.WEST))
                .attachKeyFrame();
        scene.world().setKineticSpeed(util.select().position(5, 1, 1), 128);
        scene.idle(60);
        scene.showText(60, "Put in animals, the more animals there are, the more they produce", "放入动物，动物越多，产量越高")
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
        scene.showText(30, "The output of cows is milk and leather", "牛的产出是牛奶和皮革")
                .pointAt(util.vector().blockSurface(util.grid().at(3, 3, 5), Direction.WEST))
                .attachKeyFrame();
        scene.idle(40);
        scene.overlay().showOutline(PonderPalette.RED, "", util.select().fromTo(5, 2, 5, 4, 3, 4), 30);
        scene.showText(30, "The output of sheep is wool", "羊的产出是羊毛")
                .pointAt(util.vector().blockSurface(util.grid().at(5, 3, 5), Direction.WEST))
                .attachKeyFrame();
        scene.idle(40);
        scene.overlay().showOutline(PonderPalette.RED, "", util.select().fromTo(7, 2, 5, 6, 3, 4), 30);
        scene.showText(30, "The output of pigs is pork", "猪的产出是猪肉")
                .pointAt(util.vector().blockSurface(util.grid().at(7, 3, 5), Direction.WEST))
                .attachKeyFrame();
        scene.idle(40);
        scene.overlay().showOutline(PonderPalette.RED, "", util.select().fromTo(9, 2, 5, 8, 3, 4), 30);
        scene.showText(30, "The output of chickens is eggs", "鸡的产出是鸡蛋")
                .pointAt(util.vector().blockSurface(util.grid().at(9, 3, 5), Direction.WEST))
                .attachKeyFrame();
        scene.idle(40);
        scene.overlay().showOutline(PonderPalette.RED, "", util.select().fromTo(9, 2, 5, 2, 3, 4), 60);
        scene.showText(60, "All animals produce feces", "所有动物都产生粪便")
                .pointAt(util.vector().blockSurface(util.grid().at(3, 3, 5), Direction.WEST))
                .attachKeyFrame();
        scene.idle(60);
        scene.world().showSection(util.select().fromTo(4, 1, 0, 3, 1, 0), Direction.DOWN);
        scene.showText(40,
                "Finally, place the container and you can use the meadow perfectly,if it doesn't work, try adding more output ports!",
                "最后，放置容器，你就可以完美使用牧场了，如果无法运行，试着多加几个输出口！")
                .pointAt(util.vector().blockSurface(util.grid().at(2, 1, 1), Direction.WEST))
                .attachKeyFrame();
        scene.markAsFinished();
    }
}
