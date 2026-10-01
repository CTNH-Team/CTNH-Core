package io.github.cpearl0.ctnhcore.client.ponder.Electric;

import io.github.cpearl0.ctnhcore.client.ponder.CTNHCorePonderSceneBuilder;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GTMachines;

import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;

import tech.vixhentx.mcmod.ctnhlib.client.ponder.ui.MachineUI;

/**
 * LV 输入总线的界面思索：场景里只有一台输入总线，把它的真实 fancy UI（不含玩家背包）画在机器上方，
 * 再把 64 个草方块写进 0 号槽位。
 *
 * <p>
 * storyboard 为 3x3 地板 + (1,1,1) 的 {@code gtceu:lv_input_bus}（facing=north）。
 */
public class InputBusUi {

    /** 界面定义一次，其余场景可直接复用这个常量。 */
    private static final MachineUI LV_INPUT_BUS_UI = MachineUI.of(GTMachines.ITEM_IMPORT_BUS[GTValues.LV])
            .hidePlayerInventory()
            .scale(1.25f);

    private InputBusUi() {}

    public static void Common(SceneBuilder builder, SceneBuildingUtil util) {
        CTNHCorePonderSceneBuilder scene = new CTNHCorePonderSceneBuilder(builder);
        scene.title("input_bus_ui", "Input Bus UI", "输入总线界面", "LV Input Bus", "LV 输入总线");
        scene.showBasePlate();
        scene.idle(10);

        BlockPos busPos = util.grid().at(1, 1, 1);
        Vec3 anchor = util.vector().topOf(busPos);
        scene.world().showSection(util.select().position(busPos), Direction.DOWN);
        scene.showText(60, "The input bus has 4 slots; an LV slot holds up to 256 items.",
                "输入总线有 4 个槽位；LV 每格最多放 256 个物品。")
                .pointAt(util.vector().centerOf(busPos))
                .attachKeyFrame();
        scene.idle(20);

        // 同一个界面常量的第一次调用：只画界面。
        scene.showUI(LV_INPUT_BUS_UI).at(anchor).forMachine(busPos).show(120);
        scene.idle(130);

        // 第二次调用：把 64 个草方块写进 0 号槽位，界面显示的就是机器真实库存。
        scene.showUI(LV_INPUT_BUS_UI).at(anchor).forMachine(busPos)
                .slot(0)
                .withItem(new ItemStack(Items.GRASS_BLOCK, 64), 20)
                .show(200);
        scene.showText(80, "64 grass blocks go into slot 0; the panel shows the machine's real inventory.",
                "64 个草方块写入 0 号槽位；面板显示的就是机器真实库存。")
                .attachKeyFrame();
        scene.idle(120);
        scene.markAsFinished();
    }
}
