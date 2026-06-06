package io.github.cpearl0.ctnhcore.client.ponder.Electric;

import io.github.cpearl0.ctnhcore.client.ponder.CTNHCorePonderSceneBuilder;

import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;

import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class GregTechMultiblocks {

    private GregTechMultiblocks() {}

    public static void CokeOven(SceneBuilder builder, SceneBuildingUtil util) {
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

    // TODO: 待重写
    public static void AssemblyLine(SceneBuilder builder, SceneBuildingUtil util) {
        CTNHCorePonderSceneBuilder scene = new CTNHCorePonderSceneBuilder(builder);
        scene.title("assembly_line_building", "How to automate Assembly Line", "如何自动化装配线", "Assembly Line",
                "装配线");
        scene.scaleSceneView(0.45f);
        scene.world().showSection(util.select().fromTo(0, 0, 0, 17, 0, 10), Direction.DOWN);
        scene.idle(10);
        scene.world().showSection(util.select().position(1, 3, 8), Direction.DOWN);
        scene.showText(40, "First, place an Assembly Line controller.", "首先放置装配线主方块。")
                .pointAt(util.vector().blockSurface(util.grid().at(1, 3, 8), Direction.UP))
                .attachKeyFrame();

        scene.idle(60);
        scene.overlay().showControls(util.vector().blockSurface(util.grid().at(1, 3, 8), Direction.UP),
                Pointing.LEFT, 40)
                .rightClick()
                .withItem(GTItems.TERMINAL.asStack())
                .whileSneaking();
        scene.showText(60, "Use a terminal for one-click placement.", "使用终端一键放置结构。")
                .attachKeyFrame();
        scene.idle(20);
        scene.world().showSection(util.select().fromTo(1, 1, 6, 17, 4, 10), Direction.DOWN);

        scene.idle(80);
        scene.overlay().showOutline(PonderPalette.RED, "output_bus", util.select().position(16, 2, 6), 60);
        scene.showText(60, "Use an ULV output bus at the end.", "末端使用 ULV 输出总线。")
                .pointAt(util.vector().blockSurface(util.grid().at(16, 2, 6), Direction.UP))
                .attachKeyFrame();
        scene.idle(80);
        scene.overlay().showOutline(PonderPalette.RED, "fluid_hatch", util.select().position(16, 3, 6), 60);
        scene.showText(60, "Put quadruple input hatches above it.", "在其上方放置四重输入仓。")
                .pointAt(util.vector().blockSurface(util.grid().at(16, 3, 6), Direction.UP))
                .attachKeyFrame();
        scene.idle(80);
        scene.overlay().showOutline(PonderPalette.RED, "input_buses", util.select().fromTo(15, 2, 6, 1, 2, 6),
                60);
        scene.showText(60, "Arrange ULV input buses along the line.", "沿装配线布置 ULV 输入总线。")
                .attachKeyFrame();

        scene.idle(80);
        scene.world().showSection(util.select().fromTo(16, 1, 5, 16, 2, 5), Direction.DOWN);
        scene.showText(60, "Place the pattern provider in the main AE network next to the output bus.",
                "将样板供应器放在主 AE 网络中，并贴近输出总线。")
                .pointAt(util.vector().blockSurface(util.grid().at(16, 2, 5), Direction.UP))
                .attachKeyFrame();
        scene.idle(80);
        scene.world().showSection(util.select().position(16, 3, 5), Direction.DOWN);
        scene.showText(60, "Place a buffer next to the quadruple input hatch and set item auto-output.",
                "在四重输入仓旁放置任意等级缓存，并设置物品自动输出方向。")
                .attachKeyFrame();

        scene.idle(80);
        scene.world().showSection(util.select().fromTo(15, 3, 1, 1, 2, 5), Direction.DOWN);
        scene.showText(80,
                "Use quartz fiber to isolate the subnet from the main network while still supplying power.",
                "使用石英纤维隔离子网和主网，同时保持供电。")
                .attachKeyFrame();
        scene.idle(80);
        scene.overlay().showOutline(PonderPalette.RED, "storage_priority", util.select().fromTo(15, 2, 5, 1, 2, 5),
                120);
        scene.showText(120,
                "Set storage bus priority from the controller side toward the output bus, so inputs enter in order.",
                "按主方块到输出总线的方向依次设置存储总线优先级，确保材料按正确顺序进入。")
                .attachKeyFrame();

        scene.idle(140);
        scene.showText(80,
                "Encode the one-click pattern with the '+' button, then set the pattern provider lock mode to wait for the main product.",
                "使用“+”按钮编码一键模板，并将样板供应器锁定模式改为等待主产物返回。")
                .pointAt(util.vector().blockSurface(util.grid().at(16, 2, 5), Direction.UP))
                .attachKeyFrame();
        scene.idle(80);
        scene.showText(60, "Insert the required research data to start recipes.",
                "放入配方所需的研究数据即可开始运行。")
                .pointAt(util.vector().blockSurface(util.grid().at(2, 3, 8), Direction.UP))
                .attachKeyFrame();
        scene.markAsFinished();
    }

    static ItemStack item(String namespace, String path) {
        Item item = ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath(namespace, path));
        return item == null ? GTItems.TERMINAL.asStack() : new ItemStack(item);
    }
}
