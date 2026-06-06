package io.github.cpearl0.ctnhcore.client.ponder.Electric;

import io.github.cpearl0.ctnhcore.client.ponder.CTNHCorePonderSceneBuilder;
import io.github.cpearl0.ctnhcore.data.machines.GTNNMachines;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GTItems;

import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

public class NeutronActivator {

    private static final String NEUTRON_ACCELERATOR_COVER_NBT = "{ForgeCaps:{},cover:{west:{payload:{d:{attachItem:{Count:1b,id:\"gtceu:machine_controller_cover\"},controllerMode:\"MACHINE\",isInverted:0b,minRedstoneStrength:1,preventPowerFail:0b,redstoneSignalOutput:0},t:11b},uid:{id:\"gtceu:machine_controller\",side:4}}},energyContainer:{energyStored:0L,isDistinct:0b},ownerUUID:[I;940439953,-167562164,-1601161573,-1389718966],paintingColor:-1,renderState:{Name:\"ctnhcore:luv_neutron_accelerator\",Properties:{is_formed:\"true\",is_painted:\"false\"}},workingEnabled:1b}";

    private NeutronActivator() {}

    public static void Common(SceneBuilder builder, SceneBuildingUtil util) {
        CTNHCorePonderSceneBuilder scene = new CTNHCorePonderSceneBuilder(builder);
        scene.title("neutron_activator_building", "How to build Neutron Activator", "如何搭建中子活化器",
                "Neutron Activator", "中子活化器");
        scene.showBasePlate();
        scene.idle(10);
        scene.world().showSection(util.select().position(3, 1, 1), Direction.DOWN);
        scene.showText(40, "First, place a neutron activator controller.", "首先放置中子活化器主方块。")
                .pointAt(util.vector().blockSurface(util.grid().at(3, 1, 1), Direction.UP))
                .attachKeyFrame();
        scene.idle(60);

        scene.overlay().showControls(util.vector().blockSurface(util.grid().at(3, 1, 1), Direction.UP),
                Pointing.LEFT, 40)
                .rightClick()
                .withItem(GTItems.TERMINAL.asStack())
                .whileSneaking();
        scene.showText(40, "Use a terminal for one-click placement.", "使用终端一键放置结构。")
                .attachKeyFrame();
        scene.idle(10);
        scene.world().showSection(util.select().fromTo(1, 1, 5, 5, 6, 1), Direction.DOWN);

        scene.idle(60);
        scene.overlay().showControls(util.vector().blockSurface(util.grid().at(1, 1, 2), Direction.UP),
                Pointing.LEFT, 40)
                .rightClick()
                .withItem(GregTechMultiblocks.item("gtceu", "machine_controller_cover"))
                .whileSneaking();
        scene.world().showSection(util.select().fromTo(0, 1, 1, 0, 1, 2), Direction.DOWN);
        scene.world().setBlock(util.grid().at(1, 1, 1), GTNNMachines.NEUTRON_ACCELERATOR[GTValues.LuV]
                .defaultBlockState()
                .setValue(BlockStateProperties.FACING, Direction.NORTH), true);
        scene.world().modifyBlockEntityNBT(util.select().position(1, 1, 1), BlockEntity.class,
                NeutronActivator::applyNeutronAcceleratorCover, true);
        scene.showText(40, "Place a machine controller cover toward the redstone signal on the neutron accelerator.",
                "在中子加速器朝向红石的一侧放置机器控制覆盖板。")
                .pointAt(util.vector().blockSurface(util.grid().at(1, 1, 2), Direction.UP))
                .attachKeyFrame();

        scene.idle(60);
        scene.world().showSection(util.select().fromTo(1, 1, 0, 0, 1, 0), Direction.DOWN);
        scene.showText(40, "Connect it to the power grid.", "连接到电网。")
                .attachKeyFrame();

        scene.idle(60);
        scene.showText(60,
                "Set the neutron sensor maximum kinetic energy to the recipe maximum, and minimum to recipe minimum + 5 MeV.",
                "将中子传感器最大动能设为配方最大值，最小动能设为配方最小值 + 5 MeV。")
                .pointAt(util.vector().blockSurface(util.grid().at(1, 1, 3), Direction.UP))
                .attachKeyFrame();
        scene.idle(70);
        scene.showText(60, "The neutron activator will maintain kinetic energy inside the required recipe range.",
                "中子活化器会把动能维持在配方所需范围内。")
                .attachKeyFrame();
        scene.markAsFinished();
    }

    private static void applyNeutronAcceleratorCover(CompoundTag tag) {
        try {
            tag.merge(TagParser.parseTag(NEUTRON_ACCELERATOR_COVER_NBT));
        } catch (CommandSyntaxException e) {
            throw new IllegalStateException("Invalid neutron accelerator cover NBT", e);
        }
    }
}
