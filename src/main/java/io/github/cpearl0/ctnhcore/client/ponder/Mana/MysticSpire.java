package io.github.cpearl0.ctnhcore.client.ponder.Mana;

import io.github.cpearl0.ctnhcore.client.ponder.CTNHPonderSceneBuilder;

import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import com.moguang.ctnhmana.common.entity.DeltaSpark;
import com.moguang.ctnhmana.registry.CMEntities;
import vazkii.botania.common.entity.BotaniaEntities;

public class MysticSpire {

    private MysticSpire() {}

    // 奥法尖塔
    public static void Scene1(SceneBuilder builder, SceneBuildingUtil util) {
        CTNHPonderSceneBuilder scene = new CTNHPonderSceneBuilder(builder);
        scene.setSceneOffsetY(-1);
        scene.scaleSceneView(0.4F);
        scene.idle(10);
        scene.world().showSection(util.select().layer(0), Direction.NORTH);
        scene.idle(10);
        scene.world().showSection(util.select().fromTo(0, 1, 0, 15, 12, 15), Direction.NORTH);
        Vec3 controllerVec1 = util.vector().blockSurface(util.grid().at(12, 2, 3), Direction.WEST);
        Vec3 controllerVec2 = util.vector().blockSurface(util.grid().at(3, 2, 12), Direction.WEST);
        Vec3 sparkVec1 = util.vector().blockSurface(util.grid().at(12, 12, 3), Direction.WEST);
        Vec3 sparkVec2 = util.vector().blockSurface(util.grid().at(3, 12, 12), Direction.WEST);
        BlockPos controllerPos1 = util.grid().at(12, 2, 3);

        scene.world().createEntity(world -> {
            DeltaSpark spark1 = CMEntities.DELTA_SPARK.get().create(world);
            spark1.AttachPos = controllerPos1;
            spark1.setPos(11.5, 12.5, 2.5);
            spark1.setYRot(225F);
            return spark1;
        });

        BlockPos controllerPos2 = util.grid().at(3, 2, 12);

        scene.world().createEntity(world -> {
            DeltaSpark spark2 = CMEntities.DELTA_SPARK.get().create(world);
            spark2.AttachPos = controllerPos2;
            spark2.setPos(2.5, 12.5, 11.5);
            spark2.setYRot(225F);
            return spark2;
        });

        scene.title("mystic_spire_scene1", "Mystic Spire", "奥法尖塔");
        scene.showText(50, "Mystic Spire is used for remote magic power transmission", "奥法尖塔用于远距离魔力传输")
                .pointAt(controllerVec1)
                .attachKeyFrame();
        scene.idle(70);
        scene.showText(50,
                "After shaping the structure, a delta spark will be generated at the top of the tower",
                "尖塔结构成型后，会在塔尖生成德尔塔火花")
                .pointAt(sparkVec1)
                .attachKeyFrame();
        scene.idle(70);
        scene.showText(50,
                "Use the electronic spirit staff right-click the delta spark to bind two towers",
                "使用电子精灵法杖右键德尔塔火花绑定两个尖塔")
                .pointAt(sparkVec1)
                .attachKeyFrame();
        scene.idle(70);
        scene.showText(50, "The first one is set as the receiver", "第一个设置为接收端")
                .pointAt(sparkVec1)
                .attachKeyFrame();
        scene.idle(70);
        Item saberWand = ForgeRegistries.ITEMS.getValue(
                ResourceLocation.fromNamespaceAndPath("ctnhmana", "saber_wand"));
        ItemStack saberWandStack = new ItemStack(saberWand);
        scene.overlay().showControls(sparkVec1, Pointing.DOWN, 20)
                .withItem(saberWandStack)
                .rightClick();
        scene.idle(70);
        scene.showText(50, "The second one is set as the sender", "第二个设置为发射端")
                .pointAt(sparkVec2)
                .attachKeyFrame();
        scene.overlay().showControls(sparkVec2, Pointing.DOWN, 20)
                .withItem(saberWandStack)
                .rightClick();
        scene.idle(20);
        PonderParticleUtil.sparkManaFlow(builder.effects(), sparkVec2, sparkVec1, 100);
        scene.idle(70);
        scene.showText(50, "Shift right-click to cancel the binding", "shift右键取消绑定")
                .pointAt(sparkVec2)
                .attachKeyFrame();
        scene.overlay().showControls(sparkVec2, Pointing.DOWN, 20)
                .withItem(saberWandStack)
                .whileSneaking()
                .rightClick();
        scene.idle(70);
    }

    // 奥法尖塔的属性
    public static void Scene2(SceneBuilder builder, SceneBuildingUtil util) {
        CTNHPonderSceneBuilder scene = new CTNHPonderSceneBuilder(builder);
        scene.setSceneOffsetY(-1);
        scene.scaleSceneView(0.4F);
        scene.idle(10);
        scene.world().showSection(util.select().layer(0), Direction.NORTH);
        scene.idle(10);
        scene.world().showSection(util.select().fromTo(0, 1, 0, 15, 12, 15), Direction.NORTH);
        BlockPos controllerPos1 = util.grid().at(8, 2, 7);
        scene.world().createEntity(world -> {
            DeltaSpark spark1 = CMEntities.DELTA_SPARK.get().create(world);
            spark1.AttachPos = controllerPos1;
            spark1.setPos(7.5, 12.5, 7.5);
            spark1.setYRot(225F);
            return spark1;
        });
        Vec3 controllerVec = util.vector().blockSurface(util.grid().at(8, 2, 7), Direction.UP);

        scene.title("mystic_spire_scene2", "Mystic Spire's properties", "奥法尖塔的属性");
        scene.showText(50,
                "Mystic Spire has four properties: capacity, output speed, receiving speed, range",
                "奥法尖塔有四种属性：容量，输出速度，接收速度，范围")
                .pointAt(controllerVec)
                .attachKeyFrame();
        scene.idle(70);
        Item Upgtade = ForgeRegistries.ITEMS.getValue(
                ResourceLocation.fromNamespaceAndPath("ctnhmana", "upgrade_rune_speed_1"));
        ItemStack UpgtadeStack = new ItemStack(Upgtade);
        scene.showText(50,
                "Put the mystic spire upgrade in the main block UI to improve its properties",
                "在尖塔主方块UI中放入尖塔升级以提升其属性")
                .pointAt(controllerVec)
                .attachKeyFrame();
        scene.overlay().showControls(controllerVec, Pointing.DOWN, 40)
                .withItem(UpgtadeStack);
        scene.idle(70);
        scene.showText(50,
                "Capacity is the amount of magic power cached by the tower, initialized to 1000000, maximum is INT",
                "容量是尖塔的魔力缓存量，初始为1000000，最大为INT")
                .pointAt(controllerVec)
                .attachKeyFrame();
        scene.idle(70);
        scene.showText(50,
                "Output speed determines the speed at which the tower actively outputs, initialized to 10000/tick, maximum not exceeding 1/10 of the capacity",
                "输出速度决定尖塔主动输出的速度，初始为10000/tick，最大不超过容量的1/10")
                .pointAt(controllerVec)
                .attachKeyFrame();
        scene.idle(70);
        scene.showText(50,
                "Receiving speed determines the speed at which the tower absorbs magic power, initialized to 10000/tick",
                "接收速度决定尖塔吸取魔力的速度，初始为10000/tick")
                .pointAt(controllerVec)
                .attachKeyFrame();
        scene.idle(70);
        scene.showText(50,
                "The absorption and production of magic power speed is 10% of the receiving speed, which can be boosted by the transposition rune, and the maximum value is taken",
                "吸收产魔花魔力速度为接受速度的1/10，转位符文可提升此速度，取符文效果最大值")
                .pointAt(controllerVec)
                .attachKeyFrame();
        scene.idle(70);
        scene.showText(50, "Range determines the effect range of the tower, initialized to 15, maximum is 50",
                "范围决定了尖塔的影响范围，初始为15，最大为50")
                .pointAt(controllerVec)
                .attachKeyFrame();
        scene.idle(70);
    }

    // 奥法尖塔的模式
    public static void Scene3(SceneBuilder builder, SceneBuildingUtil util) {
        CTNHPonderSceneBuilder scene = new CTNHPonderSceneBuilder(builder);
        scene.setSceneOffsetY(-1);
        scene.scaleSceneView(0.4F);
        scene.idle(10);
        scene.world().showSection(util.select().layer(0), Direction.NORTH);
        scene.idle(10);
        scene.world().showSection(util.select().fromTo(0, 1, 0, 15, 12, 15), Direction.NORTH);
        BlockPos controllerPos1 = util.grid().at(8, 2, 7);
        BlockPos controllerPos2 = util.grid().at(13, 2, 12);
        BlockPos controllerPos3 = util.grid().at(2, 2, 12);
        scene.world().createEntity(world -> {
            DeltaSpark spark1 = CMEntities.DELTA_SPARK.get().create(world);
            spark1.AttachPos = controllerPos1;
            spark1.setPos(8, 12.5, 8);
            spark1.setYRot(225F);
            return spark1;
        });
        scene.world().createEntity(world -> {
            DeltaSpark spark2 = CMEntities.DELTA_SPARK.get().create(world);
            spark2.AttachPos = controllerPos2;
            spark2.setPos(13, 12.5, 13);
            spark2.setYRot(225F);
            return spark2;
        });
        scene.world().createEntity(world -> {
            DeltaSpark spark3 = CMEntities.DELTA_SPARK.get().create(world);
            spark3.AttachPos = controllerPos3;
            spark3.setPos(2, 12.5, 13);
            spark3.setYRot(225F);
            return spark3;
        });
        scene.world().createEntity(world -> {
            var spark4 = BotaniaEntities.SPARK.create(world);
            spark4.setPos(4, 2.5, 5);
            spark4.setYRot(225F);
            return spark4;
        });
        scene.world().createEntity(world -> {
            var spark5 = BotaniaEntities.SPARK.create(world);
            spark5.setPos(0, 2.5, 9);
            spark5.setYRot(225F);
            return spark5;
        });

        Vec3 controllerVec1 = util.vector().blockSurface(util.grid().at(8, 2, 7), Direction.UP);
        Vec3 controllerVec2 = util.vector().blockSurface(util.grid().at(13, 2, 12), Direction.UP);
        Vec3 controllerVec3 = util.vector().blockSurface(util.grid().at(2, 2, 12), Direction.UP);
        Vec3 sparkVec1 = util.vector().blockSurface(util.grid().at(8, 12, 7), Direction.UP);
        Vec3 sparkVec2 = util.vector().blockSurface(util.grid().at(13, 12, 12), Direction.UP);
        Vec3 sparkVec3 = util.vector().blockSurface(util.grid().at(2, 12, 12), Direction.UP);
        Vec3 poolVec1 = util.vector().blockSurface(util.grid().at(3, 1, 5), Direction.UP);
        Vec3 poolVec2 = util.vector().blockSurface(util.grid().at(3, 2, 9), Direction.UP);
        Vec3 poolVec3 = util.vector().blockSurface(util.grid().at(0, 2, 9), Direction.UP);
        Vec3 poolVec4 = util.vector().blockSurface(util.grid().at(0, 2, 5), Direction.UP);

        scene.title("mystic_spire_scene3", "Mystic Spire's mode", "奥法尖塔的模式");
        scene.showText(70, "Mystic Spire has four working modes: focus, spark spread, wide spread, transfer",
                "奥法尖塔有四种工作模式：聚焦，火花扩散，广域扩散，中转")
                .pointAt(controllerVec1)
                .attachKeyFrame();
        scene.idle(90);
        scene.showText(70,
                "In focus mode, the tower actively absorbs the magic power of nearby same-colored sparks or magic flowers",
                "聚焦模式下，尖塔主动吸取周围同色火花或产魔花的魔力")
                .pointAt(controllerVec1)
                .attachKeyFrame();
        PonderParticleUtil.sparkManaFlow(scene.effects(), poolVec1, sparkVec1, 360);
        scene.idle(90);
        scene.showText(70,
                "In spark spread mode, the tower actively outputs magic power to nearby same-colored sparks or magic condensers",
                "火花扩散模式下，尖塔将魔力主动输出到周围同色火花或者魔力凝聚仓中")
                .pointAt(controllerVec3)
                .attachKeyFrame();
        PonderParticleUtil.sparkManaFlow(scene.effects(), sparkVec1, sparkVec3, 160);
        PonderParticleUtil.sparkManaFlow(scene.effects(), sparkVec3, poolVec2, 70);
        PonderParticleUtil.sparkManaFlow(scene.effects(), sparkVec3, poolVec3, 70);
        scene.idle(90);
        scene.showText(70,
                "In wide spread mode, the tower ignores all restrictions and passes the magic power to the non-delta fireflower within the range",
                "广域扩散模式下，尖塔无视一切限制，向范围内非德尔塔火花的魔力容器传递魔力")
                .pointAt(controllerVec3)
                .attachKeyFrame();
        PonderParticleUtil.sparkManaFlow(scene.effects(), sparkVec3, poolVec2, 70);
        PonderParticleUtil.sparkManaFlow(scene.effects(), sparkVec3, poolVec3, 70);
        PonderParticleUtil.sparkManaFlow(scene.effects(), sparkVec3, poolVec4, 70);
        scene.idle(90);
        scene.showText(70,
                "In transfer mode, the tower does not perform any operations, only implementing the transfer of magic power between towers",
                "中转模式下，尖塔不执行任何操作，只实现尖塔间的魔力传递")
                .pointAt(controllerVec2)
                .attachKeyFrame();
        PonderParticleUtil.sparkManaFlow(scene.effects(), sparkVec1, sparkVec2, 70);
        scene.idle(10);
        PonderParticleUtil.sparkManaFlow(scene.effects(), sparkVec2, sparkVec3, 60);
        scene.idle(10);
        PonderParticleUtil.sparkManaFlow(scene.effects(), sparkVec3, poolVec2, 50);
        PonderParticleUtil.sparkManaFlow(scene.effects(), sparkVec3, poolVec3, 50);
        PonderParticleUtil.sparkManaFlow(scene.effects(), sparkVec3, poolVec4, 50);
        scene.idle(70);
        scene.showText(70, "Note: Wide Spread will not transfer magic power to the output mode's magic pool",
                "注意：广域扩散不会向输出模式的魔力池转递魔力")
                .pointAt(controllerVec3)
                .attachKeyFrame();
        scene.idle(70);
    }
}
