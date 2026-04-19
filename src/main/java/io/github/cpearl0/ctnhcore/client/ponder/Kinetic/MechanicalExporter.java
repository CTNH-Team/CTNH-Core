package io.github.cpearl0.ctnhcore.client.ponder.Kinetic;

import com.gregtechceu.gtceu.integration.ae2.gui.widget.list.AEListGridWidget;
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

import static io.github.cpearl0.ctnhcore.client.ponder.Kinetic.CTNHKineticPondersLang.*;

public class MechanicalExporter {

    private MechanicalExporter() {}

    public static void Common(SceneBuilder builder, SceneBuildingUtil util) {
        Vec3 mainBlockTextVec = util.vector().blockSurface(util.grid().at(2, 1, 2), Direction.WEST);
        Vec3 inputItemsVec = util.vector().blockSurface(util.grid().at(2, 1, 2), Direction.UP);
        Item goldenExporterManifest = ForgeRegistries.ITEMS.getValue(
                ResourceLocation.fromNamespaceAndPath("jackseconomy","golden_exporter_manifest"));
        ItemStack goldenExporterManifests = new ItemStack(goldenExporterManifest);

        CTNHPonderSceneBuilder scene = new CTNHPonderSceneBuilder(builder);
        scene.title("mechanical_exporter_common", MechanicalExporterCommonHeader.translate().getContents().toString());
        scene.setSceneOffsetY(-1);
        scene.idle(10);

        scene.world().showSection(util.select().layer(0), Direction.NORTH);
        scene.idle(10);

        scene.overlay().showText(50)
                .text(MechanicalExporterCommonText1.translate().getContents().toString())
                .pointAt(mainBlockTextVec)
                .attachKeyFrame();
        scene.idle(20);
        scene.world().showSection(util.select().position(2, 1, 2), Direction.NORTH);
        scene.idle(40);

        scene.overlay().showText(50)
                .text(MechanicalExporterCommonText2.translate().getContents().toString())
                .pointAt(mainBlockTextVec)
                .attachKeyFrame();
        scene.overlay().showControls(inputItemsVec, Pointing.DOWN,40)
                        .withItem(goldenExporterManifests);
        scene.idle(60);

        scene.overlay().showText(50)
                .text(MechanicalExporterCommonText3.translate().getContents().toString())
                .pointAt(mainBlockTextVec)
                .attachKeyFrame();
        scene.idle(20);
        scene.world().showSection(util.select().fromTo(2, 1, 3, 1, 1, 3), Direction.NORTH);
        scene.world().setKineticSpeed(util.select().fromTo(2, 1, 3, 1, 1, 3), 128);
        scene.idle(60);

        scene.overlay().showText(60)
                .text(MechanicalExporterCommonText4.translate().getContents().toString())
                .pointAt(mainBlockTextVec)
                .attachKeyFrame();
        scene.idle(60);

        scene.markAsFinished();
    }
}
