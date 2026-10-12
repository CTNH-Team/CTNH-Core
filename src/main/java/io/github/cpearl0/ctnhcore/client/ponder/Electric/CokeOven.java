package io.github.cpearl0.ctnhcore.client.ponder.Electric;

import io.github.cpearl0.ctnhcore.client.ponder.CTNHCorePonderSceneBuilder;

import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;

import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.Direction;

/** 焦炉的思索场景。装配线的两个场景见 {@link AssemblyLine}。 */
public class CokeOven {

    private CokeOven() {}

    public static void Common(SceneBuilder builder, SceneBuildingUtil util) {
        CTNHCorePonderSceneBuilder scene = new CTNHCorePonderSceneBuilder(builder);
        scene.title("coke_oven_building", "How to build a GregTech multiblock", "如何搭建 GregTech 多方块", "Coke Oven",
                "焦炉");
        scene.showBasePlate();
        scene.idle(10);
        scene.world().showSection(util.select().position(3, 2, 2), Direction.DOWN);
        scene.showText(60, "First, place a multiblock controller. Coke Oven is used as the example.",
                "首先放置多方块主方块。这里以焦炉为例。")
                .pointAt(util.vector().blockSurface(util.grid().at(3, 2, 2), Direction.UP))
                .attachKeyFrame();

        scene.idle(60);
        scene.overlay().showControls(util.vector().blockSurface(util.grid().at(3, 2, 2), Direction.UP),
                Pointing.LEFT, 40)
                .rightClick()
                .withItem(GTItems.TERMINAL.asStack())
                .whileSneaking();
        scene.idle(40);
        scene.world().showSection(util.select().fromTo(2, 1, 2, 4, 3, 4), Direction.DOWN);
        scene.showText(40, "If you have enough blocks, the whole structure can be placed automatically.",
                "拥有足够方块时，终端会自动放置完整结构。")
                .attachKeyFrame();

        scene.idle(60);
        scene.world().setBlock(util.grid().at(2, 2, 2), GTMachines.COKE_OVEN_HATCH.defaultBlockState(), true);
        scene.showText(60, "Any valid casing position can be replaced by a Coke Oven Hatch.",
                "任意有效外壳位置都可以替换为焦炉仓。")
                .pointAt(util.vector().blockSurface(util.grid().at(2, 2, 2), Direction.WEST))
                .attachKeyFrame();

        scene.idle(60);
        scene.world().showSection(util.select().fromTo(1, 2, 2, 0, 1, 2), Direction.DOWN);
        scene.showText(60, "Use pipes to export fluid and items through the hatch.",
                "通过焦炉仓接入管道即可导出流体和物品。")
                .attachKeyFrame();
        scene.markAsFinished();
    }
}
