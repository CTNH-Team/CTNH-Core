package io.github.cpearl0.ctnhcore.client.ponder.Mana;

import io.github.cpearl0.ctnhcore.client.ponder.CTNHPonderSceneBuilder;

import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import vazkii.botania.common.entity.BotaniaEntities;

public class ManaHatch {

    private ManaHatch() {}

    // ========== 魔力凝聚仓（合并场景） ==========
    @Key("ctnhcore.ponder.manahatch.title")
    @CN("魔力凝聚仓")
    @EN("Mana Hatch")
    static Lang Title;
    @Key("ctnhcore.ponder.manahatch.header")
    @CN("魔力凝聚仓")
    @EN("Mana Hatch")
    static Lang Header;
    @Key("ctnhcore.ponder.manahatch.text_1")
    @CN("魔力大机器运行所需的魔力能量由魔力凝聚仓提供")
    @EN("The mana energy required for large mana machines is provided by the Mana Hatch")
    static Lang Text1;
    @Key("ctnhcore.ponder.manahatch.text_2")
    @CN("凝聚仓可以将输入的植物魔法魔力自动转化为魔力能量")
    @EN("The hatch can automatically convert incoming Botania mana into mana energy")
    static Lang Text2;
    @Key("ctnhcore.ponder.manahatch.text_3")
    @CN("也可以将输入的LP魔力或液态魔力转化为魔力能量")
    @EN("It can also convert incoming LP or liquid mana into mana energy")
    static Lang Text3;
    @Key("ctnhcore.ponder.manahatch.text_4")
    @CN("转化得到的魔力能量将供给所属的魔力机器使用")
    @EN("The converted mana energy is supplied to adjacent mana machines")
    static Lang Text4;
    @Key("ctnhcore.ponder.manahatch.text_5")
    @CN("将魔力机器升级放入主方块UI中")
    @EN("Put the magic machine upgrade into the main block UI.")
    static Lang Text5;
    @Key("ctnhcore.ponder.manahatch.text_6")
    @CN("魔力机器升级会基于凝聚仓的容量提供增幅效果")
    @EN("Mana machine upgrades provide amplification effects based on the hatch's capacity")
    static Lang Text6;

    // ============================================================
    // 合并场景：魔力凝聚仓 — 输入、供能与升级
    // 对应 NBT: assets/ctnhcore/ponder/manahatch/scene.nbt (16x13x16)
    // 关键方块：
    // creative_pool (5, 1, 2)
    // giga_manahatch (7, 1, 4) ← 控制器
    // mana_wiremill (8, 1, 4) ← 魔力机器示例
    // hv_energy_input_hatch (9, 1, 4)
    // 魔力机器多方块 (5..11, 1..5, 5..10)
    // ============================================================
    public static void Common(SceneBuilder builder, SceneBuildingUtil util) {
        CTNHPonderSceneBuilder scene = new CTNHPonderSceneBuilder(builder);
        scene.setSceneOffsetY(-1);
        scene.scaleSceneView(0.4F);
        scene.idle(10);

        // 关键观察点
        Vec3 hatchVec = util.vector().blockSurface(util.grid().at(7, 1, 4), Direction.NORTH);
        Vec3 hatchTop = util.vector().blockSurface(util.grid().at(7, 1, 4), Direction.UP);
        Vec3 poolVec = util.vector().blockSurface(util.grid().at(5, 1, 2), Direction.UP);
        Vec3 wiremillVec = util.vector().blockSurface(util.grid().at(8, 1, 4), Direction.UP);

        // 1. 显示地面层
        scene.world().showSection(util.select().layer(0), Direction.NORTH);
        scene.idle(10);

        // 2. 显示魔力凝聚仓 + 介绍
        scene.world().showSection(util.select().position(7, 1, 4), Direction.DOWN);
        scene.title("manahatch");
        scene.idle(20);
        scene.showText(60,Text1)
                .pointAt(hatchVec)
                .attachKeyFrame();
        scene.idle(70);

        // 3. 展示 Botania 魔力输入：创造魔力池 + Spark + 流向凝聚仓的粒子
        scene.world().showSection(util.select().position(5, 1, 2), Direction.DOWN);
        scene.idle(10);
        scene.world().createEntity(world -> {
            var spark = BotaniaEntities.SPARK.create(world);
            spark.setPos(5.5, 2.5, 2.5);
            spark.setYRot(0);
            return spark;
        });
        scene.idle(10);
        PonderParticleUtil.sparkManaFlow(scene.effects(), poolVec, hatchVec, 160);
        scene.showText(60,Text2)
                .pointAt(poolVec)
                .attachKeyFrame();
        scene.idle(80);

        // 4. 展示 LP/液态魔力转化（指向凝聚仓本体说明）
        scene.showText(60,Text3)
                .pointAt(hatchVec)
                .attachKeyFrame();
        scene.idle(70);

        // 5. 展示魔力机器获取能量：魔力机器 + 能源仓 + 多方块结构
        scene.world().showSection(util.select().position(8, 1, 4), Direction.DOWN);
        scene.idle(5);
        scene.world().showSection(util.select().position(9, 1, 4), Direction.DOWN);
        scene.idle(5);
        scene.world().showSection(util.select().fromTo(5, 1, 5, 11, 5, 10), Direction.DOWN);
        scene.idle(20);
        scene.showText(60,Text4)
                .pointAt(wiremillVec)
                .attachKeyFrame();
        scene.idle(80);

        // 6. 展示升级符文放入主方块 UI
        Item upgradeRune = ForgeRegistries.ITEMS.getValue(
                ResourceLocation.fromNamespaceAndPath("ctnhmana", "clear_sky_flower_wish"));
        ItemStack upgradeStack = new ItemStack(upgradeRune);
        scene.overlay().showControls(wiremillVec, Pointing.DOWN, 60)
                .withItem(upgradeStack)
                .rightClick();
        scene.showText(60,Text5)
                .pointAt(wiremillVec)
                .attachKeyFrame();
        scene.idle(70);

        // 7. 升级基于容量提供增幅
        scene.showText(60,Text6)
                .pointAt(hatchVec)
                .attachKeyFrame();
        scene.idle(70);

        scene.markAsFinished();
    }
}
