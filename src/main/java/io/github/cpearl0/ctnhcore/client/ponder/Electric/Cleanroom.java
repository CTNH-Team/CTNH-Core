package io.github.cpearl0.ctnhcore.client.ponder.Electric;

import io.github.cpearl0.ctnhcore.client.ponder.CTNHCorePonderSceneBuilder;

import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public final class Cleanroom {

    private Cleanroom() {}

    public static void building(SceneBuilder builder, SceneBuildingUtil util) {
        var scene = new CTNHCorePonderSceneBuilder(builder);
        scene.title("cleanroom_building", "Building a Cleanroom", "搭建超净间", "Cleanroom Construction", "超净间搭建");
        scene.init9x9(util);
        scene.idle(15);

        scene.world().showSection(util.select().fromTo(1, 1, 1, 7, 1, 7), Direction.UP);
        scene.showText(150,
                "The smallest Cleanroom is 5 blocks on each side. This example is 7 blocks wide, 7 deep, and 5 tall.",
                "超净间最小为 5×5×5 格。这里搭一个更大的例子：宽 7 格、深 7 格、高 5 格。")
                .pointAt(surface(util, 4, 1, 4, Direction.UP)).attachKeyFrame();
        scene.idle(160);

        scene.world().showSection(util.select().fromTo(1, 2, 1, 7, 4, 7), Direction.UP);
        scene.showText(150, "Cleanroom Glass and Plascrete both seal the walls and can be used for the floor.",
                "超净间玻璃和塑料混凝土都能围合墙面，也都可以用来铺底板。")
                .pointAt(surface(util, 1, 3, 4, Direction.WEST)).attachKeyFrame();
        scene.idle(160);

        var roof = util.select().fromTo(1, 5, 1, 7, 5, 7);
        var roofInterior = util.select().fromTo(2, 5, 2, 6, 5, 6);
        var roofCenter = util.select().fromTo(3, 5, 3, 5, 5, 5);
        var controller = util.select().position(4, 5, 4);
        scene.world().showSection(roof.substract(roofInterior).add(controller), Direction.DOWN);
        scene.showText(145, "Place the controller at the center of the roof and frame the roof with Plascrete.",
                "主方块放在屋顶中央，屋顶边框用塑料混凝土。")
                .pointAt(surface(util, 4, 5, 4, Direction.UP)).attachKeyFrame();
        scene.idle(155);

        var innerFilters = roofCenter.substract(controller);
        var outerFilters = roofInterior.substract(roofCenter);
        scene.world().showSection(innerFilters, Direction.DOWN);
        scene.idle(6);
        scene.world().showSection(outerFilters, Direction.DOWN);
        scene.idle(15);
        scene.overlay().showOutline(PonderPalette.GREEN, "cleanroom_outer_filters", outerFilters, 160);
        scene.showText(160,
                "One ring around the controller is not enough. Fill every other block inside the roof frame with the same type of Filter Casing.",
                "只铺主方块周围一圈还不够。屋顶边框以内，除了主方块，每一格都要铺同一种过滤器机械方块。")
                .pointAt(surface(util, 2, 5, 4, Direction.UP)).attachKeyFrame();
        scene.idle(170);

        scene.showText(190,
                "Each outer dimension can reach 15 blocks. Leave at least two blocks from the controller to each roof edge, with opposite distances differing by at most one.",
                "外部每个方向最多 15 格。主方块离屋顶四边各至少两格，同一轴线上到相对两边的距离最多相差一格。")
                .pointAt(surface(util, 4, 5, 4, Direction.UP)).attachKeyFrame();
        scene.idle(200);

        scene.overlay().showOutline(PonderPalette.BLUE, "cleanroom_energy", util.select().position(3, 1, 1), 165);
        scene.overlay().showOutline(PonderPalette.GREEN, "cleanroom_maintenance", util.select().position(5, 1, 1), 165);
        scene.showText(165,
                "Use one or two Energy Hatches to power the room. With maintenance enabled, install one Maintenance Hatch or Auto Maintenance Hatch.",
                "用一到两个能源仓给超净间供电。启用维护时，还需要一个维护仓或自动维护仓。")
                .pointAt(surface(util, 3, 1, 1, Direction.NORTH)).attachKeyFrame();
        scene.idle(175);

        scene.showText(130, "A wall may include a door. Opening it does not lower cleanliness.",
                "墙上可以装门，开门不会降低洁净度。")
                .pointAt(surface(util, 4, 3, 1, Direction.NORTH)).attachKeyFrame();
        scene.idle(140);

        scene.overlay().showOutline(PonderPalette.BLUE, "cleanroom_item", util.select().position(3, 3, 1), 155);
        scene.overlay().showOutline(PonderPalette.GREEN, "cleanroom_fluid", util.select().position(5, 3, 1), 155);
        scene.showText(155,
                "Item and Fluid Passthrough Hatches let you move supplies through the wall without leaving holes.",
                "物品通行仓和流体通行仓可以穿墙输送物料，不用把墙挖开。")
                .pointAt(surface(util, 3, 3, 1, Direction.NORTH)).attachKeyFrame();
        scene.idle(165);

        scene.overlay().showOutline(PonderPalette.BLUE, "cleanroom_diode", util.select().position(3, 2, 1), 165);
        scene.overlay().showOutline(PonderPalette.GREEN, "cleanroom_hull", util.select().position(5, 2, 1), 165);
        scene.showText(165,
                "A wall-mounted Diode can feed EU to machines inside. A Machine Hull can carry an AE2 network connection through the wall.",
                "墙上的二极管可以给室内机器送电；机器外壳可以把 AE2 网络接进室内。")
                .pointAt(surface(util, 3, 2, 1, Direction.NORTH)).attachKeyFrame();
        scene.idle(175);
        scene.markAsFinished();
    }

    public static void operation(SceneBuilder builder, SceneBuildingUtil util) {
        var scene = new CTNHCorePonderSceneBuilder(builder);
        scene.title("cleanroom_operation", "Running a Cleanroom", "超净间如何工作", "Cleanroom Operation", "超净间运行");
        scene.init9x9(util);
        scene.idle(15);

        var cutter = util.select().position(4, 2, 4);
        var room = util.select().fromTo(1, 1, 1, 7, 6, 7);
        scene.world().showSection(util.select().layer(1), Direction.UP);
        scene.world().showSection(cutter, Direction.DOWN);
        scene.showText(170,
                "Place machines inside for recipes marked Cleanroom. For example, an HV Cutter needs this environment to slice a Phosphorus Boule.",
                "把机器放在室内，才能运行标有超净间要求的配方。例如，用 HV 切割机切磷晶体需要这个环境。")
                .pointAt(surface(util, 4, 2, 4, Direction.UP)).attachKeyFrame();
        scene.idle(180);

        scene.showText(150,
                "Machines inside still need their own power. Keep generators and machines with mufflers outside.",
                "室内机器仍需单独供电。发电机和带消声仓的机器应放在室外。")
                .pointAt(surface(util, 4, 2, 4, Direction.NORTH)).attachKeyFrame();
        scene.idle(160);

        scene.world().showSection(util.select().fromTo(1, 2, 1, 7, 6, 7).substract(cutter), Direction.DOWN);
        scene.showText(155,
                "CTNH rooms must be no taller than either horizontal side. An over-tall room can form, but will not gain cleanliness.",
                "在 CTNH，超净间的高度不能超过长或宽。太高的房间即使成型，也不会提升洁净度。")
                .pointAt(surface(util, 4, 6, 4, Direction.UP)).attachKeyFrame();
        scene.idle(165);

        scene.overlay().showOutline(PonderPalette.BLUE, "cleanroom_dimensions", room, 175);
        scene.showText(175,
                "Cleaning raises cleanliness in steps. At the same input tier, larger or taller rooms take longer to clean. Height has the stronger effect.",
                "洁净度是分轮提升的。供电等级相同时，底面积越大或房间越高，净化就越慢；加高的影响更明显。")
                .pointAt(surface(util, 1, 5, 1, Direction.NORTH)).attachKeyFrame();
        scene.idle(185);

        scene.showText(160,
                "A higher input tier raises cleanliness by more each cycle. Maintenance problems reduce the gain.",
                "供电等级越高，每轮提升的洁净度越多；维护故障会减少每轮的提升量。")
                .pointAt(surface(util, 3, 1, 1, Direction.NORTH)).attachKeyFrame();
        scene.idle(170);

        scene.showText(175,
                "Check the controller: the room counts as clean at 95 out of 100. Keep it powered, or cleanliness will fall.",
                "在主方块界面查看状态。洁净度达到 95 点（满值 100）才算洁净。请持续供电，缺电会让洁净度下降。")
                .pointAt(surface(util, 4, 6, 4, Direction.UP)).attachKeyFrame();
        scene.idle(185);

        scene.showText(140,
                "If the shell becomes invalid, cleanliness resets. Use doors or passthrough hatches instead of breaking the wall.",
                "结构失效时，洁净度会归零。进出或输送物料时，请用门或通行仓，不要拆墙。")
                .pointAt(surface(util, 4, 3, 1, Direction.NORTH)).attachKeyFrame();
        scene.idle(150);

        scene.overlay().showOutline(PonderPalette.GREEN, "cleanroom_filters", util.select().fromTo(2, 6, 2, 6, 6, 6)
                .substract(util.select().position(4, 6, 4)), 180);
        scene.showText(180,
                "Check the recipe's room type. For a Sterile Cleanroom recipe, use Sterilizing Filter Casings throughout the filter area.",
                "以配方标注的环境类型为准。需要无菌环境时，把过滤区全部改用除菌过滤器机械方块。")
                .pointAt(surface(util, 4, 6, 3, Direction.UP)).attachKeyFrame();
        scene.idle(190);
        scene.markAsFinished();
    }

    private static Vec3 surface(SceneBuildingUtil util, int x, int y, int z, Direction side) {
        return util.vector().blockSurface(util.grid().at(x, y, z), side);
    }
}
