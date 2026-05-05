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

import static io.github.cpearl0.ctnhcore.client.ponder.Mana.CTNHManaPondersLang.*;

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

        builder.title("mystic_spire_scene1", MysticSpireScene1Header.translate().getContents().toString());
        scene.overlay().showText(50)
                .text(MysticSpireScene1Text1.translate().getContents().toString())
                .pointAt(controllerVec1)
                .attachKeyFrame();
        scene.idle(70);
        scene.overlay().showText(50)
                .text(MysticSpireScene1Text2.translate().getContents().toString())
                .pointAt(sparkVec1)
                .attachKeyFrame();
        scene.idle(70);
        scene.overlay().showText(50)
                .text(MysticSpireScene1Text3.translate().getContents().toString())
                .pointAt(sparkVec1)
                .attachKeyFrame();
        scene.idle(70);
        scene.overlay().showText(50)
                .text(MysticSpireScene1Text4.translate().getContents().toString())
                .pointAt(sparkVec1)
                .attachKeyFrame();
        Item saberWand = ForgeRegistries.ITEMS.getValue(
                ResourceLocation.fromNamespaceAndPath("ctnhmana", "saber_wand"));
        ItemStack saberWandStack = new ItemStack(saberWand);
        scene.overlay().showControls(sparkVec1, Pointing.DOWN, 20)
                .withItem(saberWandStack)
                .rightClick();
        scene.idle(70);
        scene.overlay().showText(50)
                .text(MysticSpireScene1Text5.translate().getContents().toString())
                .pointAt(sparkVec2)
                .attachKeyFrame();
        scene.overlay().showControls(sparkVec2, Pointing.DOWN, 20)
                .withItem(saberWandStack)
                .rightClick();
        scene.idle(20);
        PonderParticleUtil.sparkManaFlow(builder.effects(), sparkVec2, sparkVec1, 100);
        scene.idle(70);
        scene.overlay().showText(50)
                .text(MysticSpireScene1Text6.translate().getContents().toString())
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

        builder.title("mystic_spire_scene2", MysticSpireScene2Header.translate().getContents().toString());
        scene.overlay().showText(50)
                .text(MysticSpireScene2Text1.translate().getContents().toString())
                .pointAt(controllerVec)
                .attachKeyFrame();
        scene.idle(70);
        Item Upgtade = ForgeRegistries.ITEMS.getValue(
                ResourceLocation.fromNamespaceAndPath("ctnhmana", "upgrade_rune_speed_1"));
        ItemStack UpgtadeStack = new ItemStack(Upgtade);
        scene.overlay().showText(50)
                .text(MysticSpireScene2Text2.translate().getContents().toString())
                .pointAt(controllerVec)
                .attachKeyFrame();
        scene.overlay().showControls(controllerVec, Pointing.DOWN, 40)
                .withItem(UpgtadeStack);
        scene.idle(70);
        scene.overlay().showText(50)
                .text(MysticSpireScene2Text3.translate().getContents().toString())
                .pointAt(controllerVec)
                .attachKeyFrame();
        scene.idle(70);
        scene.overlay().showText(50)
                .text(MysticSpireScene2Text4.translate().getContents().toString())
                .pointAt(controllerVec)
                .attachKeyFrame();
        scene.idle(70);
        scene.overlay().showText(50)
                .text(MysticSpireScene2Text5.translate().getContents().toString())
                .pointAt(controllerVec)
                .attachKeyFrame();
        scene.idle(70);
        scene.overlay().showText(50)
                .text(MysticSpireScene2Text6.translate().getContents().toString())
                .pointAt(controllerVec)
                .attachKeyFrame();
        scene.idle(70);
        scene.overlay().showText(50)
                .text(MysticSpireScene2Text7.translate().getContents().toString())
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

        builder.title("mystic_spire_scene3", MysticSpireScene3Header.translate().getContents().toString());
        scene.overlay().showText(70)
                .text(MysticSpireScene3Text1.translate().getContents().toString())
                .pointAt(controllerVec1)
                .attachKeyFrame();
        scene.idle(90);
        scene.overlay().showText(70)
                .text(MysticSpireScene3Text2.translate().getContents().toString())
                .pointAt(controllerVec1)
                .attachKeyFrame();
        PonderParticleUtil.sparkManaFlow(scene.effects(), poolVec1, sparkVec1, 360);
        scene.idle(90);
        scene.overlay().showText(70)
                .text(MysticSpireScene3Text3.translate().getContents().toString())
                .pointAt(controllerVec3)
                .attachKeyFrame();
        PonderParticleUtil.sparkManaFlow(scene.effects(), sparkVec1, sparkVec3, 160);
        PonderParticleUtil.sparkManaFlow(scene.effects(), sparkVec3, poolVec2, 70);
        PonderParticleUtil.sparkManaFlow(scene.effects(), sparkVec3, poolVec3, 70);
        scene.idle(90);
        scene.overlay().showText(70)
                .text(MysticSpireScene3Text4.translate().getContents().toString())
                .pointAt(controllerVec3)
                .attachKeyFrame();
        PonderParticleUtil.sparkManaFlow(scene.effects(), sparkVec3, poolVec2, 70);
        PonderParticleUtil.sparkManaFlow(scene.effects(), sparkVec3, poolVec3, 70);
        PonderParticleUtil.sparkManaFlow(scene.effects(), sparkVec3, poolVec4, 70);
        scene.idle(90);
        scene.overlay().showText(70)
                .text(MysticSpireScene3Text5.translate().getContents().toString())
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
        scene.overlay().showText(70)
                .text(MysticSpireScene3Text6.translate().getContents().toString())
                .pointAt(controllerVec3)
                .attachKeyFrame();
        scene.idle(70);
    }
}
