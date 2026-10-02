package io.github.cpearl0.ctnhcore.client.ponder.Electric;

import io.github.cpearl0.ctnhcore.client.ponder.CTNHCorePonderSceneBuilder;
import io.github.cpearl0.ctnhcore.client.ponder.machine.NeutronSensorChange;
import io.github.cpearl0.ctnhcore.data.machines.GTNNMachines;
import io.github.cpearl0.ctnhcore.data.materials.NaquadahMaterials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import tech.vixhentx.mcmod.ctnhlib.client.ponder.ui.MachineUI;

public class NeutronActivator {

    /** 示例配方 {@code ctnhcore:neutron_activator/naquadria_activation} 的中子动能区间（MeV）。 */
    private static final int RECIPE_MIN_MEV = 1050;
    private static final int RECIPE_MAX_MEV = 1100;
    /** 传感器下界比配方下界高 5 MeV：动能还没掉到配方下界，加速器就先被关掉。 */
    private static final int SENSOR_MIN_MEV = RECIPE_MIN_MEV + 5;

    /** 中子传感器：配方要求的动能区间就填在它那两个输入框里（单位 KeV）。 */
    private static final MachineUI SENSOR_UI = MachineUI.of(GTNNMachines.NEUTRON_SENSOR).scale(0.6f);

    /** 流体输入仓：酸性 Naquadria 铯氟化物从这里进。 */
    private static final MachineUI FLUID_INPUT_HATCH_UI = MachineUI.of(GTMachines.FLUID_IMPORT_HATCH[GTValues.LV])
            .scale(0.6f);

    /** 输出总成：一块方块上同时带物品槽与流体罐，物品产物和流体产物在同一个界面里出。 */
    private static final MachineUI DUAL_OUTPUT_HATCH_UI = MachineUI.of(GTMachines.DUAL_EXPORT_HATCH[GTValues.LV])
            .scale(0.6f);

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

        // 动能靠传感器控制：两个输入框写的是配方区间，最大取配方最大值，
        // 最小比配方最小值再高 5 MeV，让加速器在动能跌破配方下界之前就先停。
        BlockPos sensorPos = util.grid().at(1, 1, 2);
        scene.showUI(SENSOR_UI).at(util.vector().topOf(sensorPos)).machinePos(sensorPos)
                .show(240);
        scene.showText(150,
                "The naquadria activation recipe runs on 1050 to 1100 MeV: the maximum goes to 1100000 and the minimum to 1055000 - recipe minimum plus 5 MeV - both in KeV.",
                "Naquadria 活化这条配方要求 1050~1100 MeV：最大动能填 1100000，最小动能填配方最小值加 5 MeV 的 1055000，单位都是 KeV。")
                .pointAt(util.vector().blockSurface(sensorPos, Direction.UP))
                .attachKeyFrame();
        // 两个数字和槽位、储罐一个节奏：NeutronSensorChange 用 1 秒把它们从 0 递增到配方区间。
        NeutronSensorChange.ramp(scene, sensorPos, SENSOR_MIN_MEV * 1000, RECIPE_MAX_MEV * 1000);
        scene.idle(170);

        // 动能的闭环：传感器只在动能落在区间内时输出红石，加速器上的机器控制覆盖板跟着这个信号开关加速器。
        BlockPos acceleratorPos = util.grid().at(1, 1, 1);
        scene.overlay().showOutline(PonderPalette.GREEN, "sensor_band", util.select().position(sensorPos), 90);
        scene.showText(130,
                "The sensor only emits redstone while the energy sits inside that window, and the machine controller cover on the accelerator follows it - that loop holds the neutron energy inside the recipe's range.",
                "动能落在设定的区间里时传感器才输出红石，加速器上的机器控制覆盖板跟着这个信号开关加速器——动能就是这样被维持在配方所需范围内的。")
                .pointAt(util.vector().blockSurface(acceleratorPos, Direction.UP))
                .attachKeyFrame();
        scene.idle(210);

        // 输入：这条配方的料是流体，走顶圈的流体输入仓。
        BlockPos fluidInputPos = util.grid().at(3, 6, 1);
        scene.world().setBlock(fluidInputPos, GTMachines.FLUID_IMPORT_HATCH[GTValues.LV].defaultBlockState()
                .setValue(BlockStateProperties.FACING, Direction.NORTH), true);
        scene.showUI(FLUID_INPUT_HATCH_UI).at(util.vector().topOf(fluidInputPos)).machinePos(fluidInputPos)
                .tank(0)
                .withFluid(NaquadahMaterials.AcidicNaquadriaCaesiumfluoride.getFluid(9000), 20)
                .outlineTank(0, 20)
                .show(200);
        scene.showText(130,
                "The input of this recipe is a fluid: pump 9000 mB of acidic naquadria caesiumfluoride into the fluid input hatch on the top ring.",
                "这条配方的输入是流体：把 9000 mB 酸性 Naquadria 铯氟化物抽进顶圈的流体输入仓。")
                .pointAt(util.vector().blockSurface(fluidInputPos, Direction.NORTH))
                .attachKeyFrame();
        scene.idle(210);

        // 输出总成一块方块上就带物品槽与流体罐，两种产物在同一块界面里一次出。
        BlockPos outputHatchPos = util.grid().at(4, 1, 1);
        scene.world().setBlock(outputHatchPos, GTMachines.DUAL_EXPORT_HATCH[GTValues.LV].defaultBlockState()
                .setValue(BlockStateProperties.FACING, Direction.NORTH), true);
        scene.overlay().showOutline(PonderPalette.GREEN, "output", util.select().position(outputHatchPos), 250);
        scene.showUI(DUAL_OUTPUT_HATCH_UI).at(util.vector().topOf(outputHatchPos)).machinePos(outputHatchPos)
                .slot(0)
                .withItem(new ItemStack(ChemicalHelper.get(TagPrefix.dust, GTMaterials.NaquadriaSulfate).getItem(), 3),
                        20)
                .slot(1)
                .withItem(new ItemStack(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Caesium).getItem(), 3), 20)
                .tank(0)
                .withFluid(GTMaterials.Fluorine.getFluid(18000), 20)
                .outlineSlot(0, 20)
                .outlineSlot(1, 20)
                .outlineTank(0, 20)
                .show(250);
        scene.showText(160,
                "An output assembly carries item slots and fluid tanks on the same block: the 3 naquadria sulfate, the 3 caesium dust and the 18000 mB of fluorine all come out in this one UI.",
                "输出总成一块方块上就带物品槽和流体罐：3 个 Naquadria 硫酸盐、3 个铯粉和 18000 mB 氟，全都在这一块界面里出来。")
                .pointAt(util.vector().blockSurface(outputHatchPos, Direction.NORTH))
                .attachKeyFrame();
        scene.idle(260);
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
