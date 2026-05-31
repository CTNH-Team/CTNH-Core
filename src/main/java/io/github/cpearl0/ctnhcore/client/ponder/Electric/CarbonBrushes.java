package io.github.cpearl0.ctnhcore.client.ponder.Electric;

import io.github.cpearl0.ctnhcore.client.ponder.CTNHPonderSceneBuilder;

import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

import org.antarcticgardens.cna.CNABlocks;

public class CarbonBrushes {

    private CarbonBrushes() {}

    public static void ponder(SceneBuilder builder, SceneBuildingUtil util) {
        CTNHPonderSceneBuilder scene = new CTNHPonderSceneBuilder(builder);

        Selection magnet1 = util.select().fromTo(4, 5, 1, 2, 5, 8);
        Selection magnet2 = util.select().fromTo(4, 1, 1, 2, 1, 8);
        Selection magnet3 = util.select().fromTo(1, 4, 1, 1, 2, 8);
        Selection magnet4 = util.select().fromTo(5, 2, 1, 5, 4, 8);
        Selection allMagnets = magnet1.add(magnet2).add(magnet3).add(magnet4);
        scene.title("carbon_brushes", "Magneto generated electricity", "磁电机发电", "Carbon Brushes", "碳刷");
        scene.configureBasePlate(0, 0, 7);
        scene.scaleSceneView(0.75f);
        scene.setSceneOffsetY(-1);

        scene.idle(10);
        scene.world().showSection(util.select().layer(0), Direction.NORTH);
        scene.idle(10);
        scene.world().showSection(util.select().position(3, 3, 0), Direction.NORTH);
        scene.showText(60,
                "This is carbon brushes. It can convert kinetic energy into electrical energy.",
                "这是碳刷。它可以将动能转化为电能。")
                .pointAt(util.vector().blockSurface(util.grid().at(3, 3, 0), Direction.WEST))
                .attachKeyFrame();
        scene.idle(70);
        scene.world().showSection(util.select().fromTo(3, 3, 1, 3, 3, 1), Direction.NORTH);
        scene.idle(10);
        scene.showText(60,
                "To provide sufficient kinetic energy, you need generator coils.",
                "为了提供足够的动能，你需要发电机线圈。")
                .pointAt(util.vector().blockSurface(util.grid().at(2, 3, 1), Direction.WEST))
                .attachKeyFrame();
        scene.world().setKineticSpeed(util.select().position(3, 3, 0), 64);
        scene.world().setKineticSpeed(util.select().fromTo(3, 3, 1, 3, 3, 8), 64);
        scene.idle(70);
        scene.world().showSection(util.select().position(3, 5, 1), Direction.NORTH);
        scene.idle(10);
        scene.showText(60,
                "Just having a generator coil is not enough to generate energy, you also need to install magnets around the coil.",
                "仅有发电机线圈是不够的，你还需要在线圈周围安装磁铁。")
                .pointAt(util.vector().blockSurface(util.grid().at(3, 5, 1), Direction.DOWN))
                .attachKeyFrame();
        scene.idle(70);
        scene.world().showSection(util.select().position(2, 3, 0), Direction.DOWN);
        scene.world().showSection(util.select().position(2, 2, 0), Direction.EAST);
        scene.idle(10);
        scene.showText(60,
                "Place cables and machines to export and utilize the generated energy",
                "放置电缆和机器来导出并使用产出的能量")
                .pointAt(util.vector().blockSurface(util.grid().at(3, 2, 1), Direction.WEST))
                .attachKeyFrame();

        scene.idle(70);
        scene.showText(60,
                "A generator coil can be accelerated by up to 12 magnets",
                "一个发电机线圈最多可以被12个磁铁加速")
                .pointAt(util.vector().blockSurface(util.grid().at(2, 5, 1), Direction.WEST))
                .attachKeyFrame();
        scene.idle(10);
        scene.world().showSection(util.select().fromTo(1, 5, 1, 5, 1, 1), Direction.NORTH);
        scene.idle(70);
        scene.showText(60,
                "One carbon brush can receive the kinetic energy of 8 generator coils",
                "一个碳刷可以接收8个发电机线圈的动能")
                .attachKeyFrame();
        scene.idle(10);
        scene.world().showSection(util.select().fromTo(1, 5, 2, 5, 1, 9), Direction.NORTH);
        scene.idle(70);
        scene.showText(60,
                "As the number of magnets increases, the stress consumption of the coil will also increase",
                "随着磁铁数量的增加，线圈的应力消耗也会增加")
                .pointAt(util.vector().blockSurface(util.grid().at(2, 5, 1), Direction.WEST))
                .attachKeyFrame();
        scene.idle(60);
        scene.showText(60,
                "The rotational speed is also a major contributor to the increase in stress consumption",
                "转速也是应力消耗增加的一个重要因素")
                .pointAt(util.vector().blockSurface(util.grid().at(4, 3, 0), Direction.WEST))
                .attachKeyFrame();
        scene.world().setKineticSpeed(util.select().position(3, 3, 0), 128);
        scene.world().setKineticSpeed(util.select().fromTo(3, 3, 1, 3, 3, 8), 128);
        scene.idle(70);
        scene.showText(100,
                "Similarly, The grade of the magnet will also increase stress consumption.",
                "同样，磁铁的等级也会增加应力消耗。")
                .attachKeyFrame();
        scene.idle(20);
        BlockState redstoneMagnet = CNABlocks.REDSTONE_MAGNET.getDefaultState();
        scene.world().replaceBlocks(allMagnets, redstoneMagnet, true);
        scene.idle(20);
        BlockState fluxuatedMagnetite = CNABlocks.FLUXUATED_MAGNETITE.getDefaultState();
        scene.world().replaceBlocks(allMagnets, fluxuatedMagnetite, true);
        scene.idle(20);
        BlockState netheriteMagnet = CNABlocks.NETHERITE_MAGNET.getDefaultState();
        scene.world().replaceBlocks(allMagnets, netheriteMagnet, true);
        scene.idle(20);
        scene.markAsFinished();
    }
}
