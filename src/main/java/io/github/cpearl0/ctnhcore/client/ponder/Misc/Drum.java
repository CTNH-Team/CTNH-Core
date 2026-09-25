package io.github.cpearl0.ctnhcore.client.ponder.Misc;

import io.github.cpearl0.ctnhcore.client.ponder.CTNHCorePonderSceneBuilder;

import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.api.item.tool.ToolHelper;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.element.ElementLink;
import net.createmod.ponder.api.element.WorldSectionElement;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.phys.Vec3;

import appeng.blockentity.storage.SkyStoneTankBlockEntity;

/**
 * 桶的思索。storyboard 为 1x1x3 立柱：桶(y=3)、青铜流体管道(y=2)、陨石储罐(y=1)。
 * <p>
 * 讲解顺序：桶与存取 → 螺丝刀切换自动输出 → 桶落下紧贴下方容器 → 也可以是管道 →
 * 桶升起、管道插入中间，演示流体经管道向下输送。
 */
public class Drum {

    private static final String DEMO_FLUID = "minecraft:water";
    private static final int TANK_CAPACITY = SkyStoneTankBlockEntity.BUCKET_CAPACITY * 1000;

    private static final int TANK_Y = 1;
    private static final int PIPE_Y = 2;
    private static final int DRUM_Y = 3;

    private Drum() {}

    public static void Common(SceneBuilder builder, SceneBuildingUtil util) {
        CTNHCorePonderSceneBuilder scene = new CTNHCorePonderSceneBuilder(builder);
        scene.title("drum_storage", "Storing and draining fluids with a Drum", "用桶存取与排出流体",
                "Drum", "桶");
        scene.showBasePlate();
        scene.idle(10);

        // ---- 1. 桶本体：储存单一流体，手持容器右键存取 ----
        ElementLink<WorldSectionElement> drumLink = scene.world()
                .showIndependentSection(util.select().position(1, DRUM_Y, 1), Direction.DOWN);
        scene.showText(50, "A Drum stores one fluid type.", "一个桶储存单一类型的流体。")
                .pointAt(util.vector().blockSurface(util.grid().at(1, DRUM_Y, 1), Direction.UP))
                .attachKeyFrame();
        scene.idle(60);

        scene.overlay().showControls(util.vector().blockSurface(util.grid().at(1, DRUM_Y, 1), Direction.UP),
                Pointing.LEFT, 40)
                .rightClick()
                .withItem(GTItems.FLUID_CELL.asStack());
        scene.showText(70,
                "Right-click it with a fluid container to fill the Drum, or to draw fluid back out.",
                "手持流体容器右键，即可向桶灌入流体，或从桶中取出。")
                .pointAt(util.vector().blockSurface(util.grid().at(1, DRUM_Y, 1), Direction.UP))
                .attachKeyFrame();
        scene.idle(70);

        // ---- 2. 螺丝刀切换自动输出 ----
        scene.overlay().showControls(util.vector().blockSurface(util.grid().at(1, DRUM_Y, 1), Direction.UP),
                Pointing.RIGHT, 40)
                .rightClick()
                .withItem(ToolHelper.get(GTToolType.SCREWDRIVER, GTMaterials.Bronze));
        scene.showText(80,
                "Right-click it with a Screwdriver to toggle automatic output.",
                "用螺丝刀右击可切换自动输出。")
                .pointAt(util.vector().blockSurface(util.grid().at(1, DRUM_Y, 1), Direction.UP))
                .attachKeyFrame();
        scene.idle(70);

        // ---- 3. 桶落下，底面紧贴下方容器 ----
        scene.world().showSection(util.select().position(1, TANK_Y, 1), Direction.DOWN);
        scene.idle(15);
        scene.world().moveSection(drumLink, new Vec3(0, -1, 0), 10);
        scene.idle(12);
        scene.showText(80,
                "Its automatic output only drains straight down, into the container touching its bottom.",
                "它的自动输出只向正下方排出流体，进入紧贴桶底的容器。")
                .pointAt(util.vector().blockSurface(util.grid().at(1, TANK_Y, 1), Direction.UP))
                .attachKeyFrame();
        scene.idle(80);

        // ---- 4. 也可以是管道：桶升起，管道插入中间一格 ----
        scene.world().moveSection(drumLink, new Vec3(0, 1, 0), 10);
        scene.idle(12);
        scene.world().showSection(util.select().position(1, PIPE_Y, 1), Direction.DOWN);
        scene.showText(70,
                "Of course, that can be a fluid pipe as well.",
                "当然，也可以是管道。")
                .pointAt(util.vector().blockSurface(util.grid().at(1, PIPE_Y, 1), Direction.UP))
                .attachKeyFrame();
        scene.idle(70);

        // ---- 5. 流体经管道向下输送，储罐液面上升 ----
        setTankFill(scene, util, 0);
        scene.showText(60,
                "The pipe carries the fluid on down to the container below.",
                "管道再把流体输送到下方的容器。")
                .attachKeyFrame();
        scene.idle(20);

        emitFlow(scene, util, Vec3.atLowerCornerOf(util.grid().at(1, DRUM_Y, 1)).add(0.5d, 0d, 0.5d));
        emitFlow(scene, util, Vec3.atLowerCornerOf(util.grid().at(1, PIPE_Y, 1)).add(0.5d, 0d, 0.5d));
        scene.idle(25);

        setTankFill(scene, util, TANK_CAPACITY / 3);
        scene.idle(25);
        setTankFill(scene, util, TANK_CAPACITY * 2 / 3);
        scene.idle(25);
        setTankFill(scene, util, TANK_CAPACITY);

        scene.showText(80,
                "As the Drum empties, the fluid level in the container below rises.",
                "桶排空的同时，下方容器的液面逐步上升。")
                .pointAt(util.vector().blockSurface(util.grid().at(1, TANK_Y, 1), Direction.UP))
                .attachKeyFrame();
        scene.idle(80);

        scene.showText(60, "Right-click it again with the Screwdriver to stop the automatic output.",
                "再次用螺丝刀右击即可停止自动输出。")
                .attachKeyFrame();
        scene.idle(50);
        scene.markAsFinished();
    }

    /** 沿管道滴落的水滴，表现流体正在向下输送。 */
    private static void emitFlow(CTNHCorePonderSceneBuilder scene, SceneBuildingUtil util, Vec3 from) {
        scene.effects().emitParticles(from,
                scene.effects().simpleParticleEmitter(ParticleTypes.FALLING_WATER, new Vec3(0, -0.15d, 0)),
                2f, 20);
    }

    /** 写入储罐液量：AE2 的 FluidTank 直接读写 FluidName / Amount 两个键。 */
    private static void setTankFill(CTNHCorePonderSceneBuilder scene, SceneBuildingUtil util, int amount) {
        scene.world().modifyBlockEntityNBT(util.select().position(1, TANK_Y, 1), SkyStoneTankBlockEntity.class,
                tag -> writeFluid(tag, amount), true);
    }

    private static void writeFluid(CompoundTag tag, int amount) {
        if (amount <= 0) {
            tag.remove("FluidName");
            tag.remove("Amount");
            return;
        }
        tag.putString("FluidName", DEMO_FLUID);
        tag.putInt("Amount", amount);
    }
}
