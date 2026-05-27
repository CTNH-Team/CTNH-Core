package io.github.cpearl0.ctnhcore.client.ponder.Kinetic;

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

public class MechanicalExporter {

    private MechanicalExporter() {}

    @Key("ctnhcore.ponder.mechanical_exporter_common.header")
    @CN("应力出售机")
    @EN("Mechanical Exporter")
    static Lang Header;
    @Key("ctnhcore.ponder.mechanical_exporter_common.text_1")
    @CN("放置应力出售机")
    @EN("Place Mechanical Exporter")
    static Lang Text1;
    @Key("ctnhcore.ponder.mechanical_exporter_common.text_2")
    @CN("首先，你需要放入金色物品清单，它允许你出售各种食物来获取货币")
    @EN("First, you need to put a golden item list, which allows you to sell various food to get currency")
    static Lang Text2;
    @Key("ctnhcore.ponder.mechanical_exporter_common.text_3")
    @CN("接入应力")
    @EN("Access stress")
    static Lang Text3;
    @Key("ctnhcore.ponder.mechanical_exporter_common.text_4")
    @CN("放入有标价的食物，就可以获取货币了，对食物按alt可以看标价")
    @EN("Put in food with a price, you can get currency, press alt to see the price of the food")
    static Lang Text4;

    public static void Common(SceneBuilder builder, SceneBuildingUtil util) {
        Vec3 mainBlockTextVec = util.vector().blockSurface(util.grid().at(2, 1, 2), Direction.WEST);
        Vec3 inputItemsVec = util.vector().blockSurface(util.grid().at(2, 1, 2), Direction.UP);
        Item goldenExporterManifest = ForgeRegistries.ITEMS.getValue(
                ResourceLocation.fromNamespaceAndPath("jackseconomy", "golden_exporter_manifest"));
        ItemStack goldenExporterManifests = new ItemStack(goldenExporterManifest);

        CTNHPonderSceneBuilder scene = new CTNHPonderSceneBuilder(builder);
        scene.title("mechanical_exporter_common");
        scene.setSceneOffsetY(-1);
        scene.idle(10);

        scene.world().showSection(util.select().layer(0), Direction.NORTH);
        scene.idle(10);

        scene.showText(50, Text1)
                .pointAt(mainBlockTextVec)
                .attachKeyFrame();
        scene.idle(20);
        scene.world().showSection(util.select().position(2, 1, 2), Direction.NORTH);
        scene.idle(40);

        scene.showText(50, Text2)
                .pointAt(mainBlockTextVec)
                .attachKeyFrame();
        scene.overlay().showControls(inputItemsVec, Pointing.DOWN, 40)
                .withItem(goldenExporterManifests);
        scene.idle(60);

        scene.showText(50, Text3)
                .pointAt(mainBlockTextVec)
                .attachKeyFrame();
        scene.idle(20);
        scene.world().showSection(util.select().fromTo(2, 1, 3, 1, 1, 3), Direction.NORTH);
        scene.world().setKineticSpeed(util.select().fromTo(2, 1, 3, 1, 1, 3), 128);
        scene.idle(60);

        scene.showText(60, Text4)
                .pointAt(mainBlockTextVec)
                .attachKeyFrame();
        scene.idle(60);

        scene.markAsFinished();
    }
}
