// SPDX-License-Identifier: GPL-3.0
// Copyright (C) 2026 mmyddd
package io.github.cpearl0.ctnhcore.client.ponder.machine;

import io.github.cpearl0.ctnhcore.common.machine.multiblock.part.NeutronSensorMachine;

import com.gregtechceu.gtceu.api.machine.MetaMachine;

import net.createmod.ponder.api.scene.SceneBuilder;
import net.minecraft.core.BlockPos;

import org.jetbrains.annotations.Nullable;
import tech.vixhentx.mcmod.ctnhlib.CTNHLib;
import tech.vixhentx.mcmod.ctnhlib.client.ponder.machine.MachineEdit;
import tech.vixhentx.mcmod.ctnhlib.client.ponder.machine.MachineEdits;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 改中子传感器的输出反转与门限：只写机器状态，界面里那几个文本框读的是同一批字段。
 *
 * <p>
 * {@code NeutronSensorMachine} 的字段与 setter 都是私有的，所以这里只能反射调用——它是本模组自己的类，
 * 不存在模块限制问题。回退时把改动前的值写回去。
 */
public final class NeutronSensorChange implements MachineEdit {

    /** 门限递增的时长：和面板里槽位、储罐的写入同一个节奏，1 秒。 */
    public static final int FILL_TICKS = 20;

    /**
     * 把门限按 {@link #FILL_TICKS} 个 tick 递增着铺到思索时间线上：每个 tick 送一档，一秒后正好落在目标值。
     *
     * <p>
     * 每一档都是一次独立的 {@link NeutronSensorChange}，各自记着自己动手前的读数；而场景回退时
     * Ponder 本来就会按 storyboard 重建世界与方块实体，所以不需要靠它们收尾回初始值。
     */
    public static void ramp(SceneBuilder scene, BlockPos sensorPos, int min, int max) {
        for (int tick = 1; tick <= FILL_TICKS; tick++) {
            MachineEdits.add(scene, sensorPos,
                    new NeutronSensorChange(min * tick / FILL_TICKS, max * tick / FILL_TICKS), tick);
        }
    }

    /** 要设置的反转状态；为 null 表示这一段不改它。 */
    @Nullable
    private final Boolean inverted;
    private final int min;
    private final int max;
    /** 这一段是否包含门限调整。 */
    private final boolean range;

    @Nullable
    private Boolean previousInverted;
    private int previousMin;
    private int previousMax;
    private boolean applied;

    /** 只改输出反转。 */
    public NeutronSensorChange(boolean inverted) {
        this.inverted = inverted;
        this.min = 0;
        this.max = 0;
        this.range = false;
    }

    /** 只改中子动能门限。 */
    public NeutronSensorChange(int min, int max) {
        this.inverted = null;
        this.min = min;
        this.max = max;
        this.range = true;
    }

    @Override
    public void apply(MetaMachine machine, BlockPos machinePos) {
        if (!(machine instanceof NeutronSensorMachine sensor)) {
            CTNHLib.LOGGER.error("NeutronSensorChange expected a neutron sensor, but {} sits at {}",
                    machine.getDefinition(), machinePos);
            return;
        }
        try {
            if (inverted != null) {
                previousInverted = read(sensor, "isInverted", Boolean.class);
                invoke(sensor, "setIsInverted", boolean.class, inverted);
            }
            if (range) {
                previousMin = read(sensor, "min", Integer.class);
                previousMax = read(sensor, "max", Integer.class);
                invoke(sensor, "setMin", int.class, min);
                invoke(sensor, "setMax", int.class, max);
            }
            applied = true;
        } catch (ReflectiveOperationException e) {
            CTNHLib.LOGGER.error("NeutronSensorChange could not touch the sensor at {}", machinePos, e);
        }
    }

    @Override
    public void revert(MetaMachine machine, BlockPos machinePos) {
        if (!applied || !(machine instanceof NeutronSensorMachine sensor)) {
            return;
        }
        try {
            if (previousInverted != null) {
                invoke(sensor, "setIsInverted", boolean.class, previousInverted);
            }
            if (range) {
                invoke(sensor, "setMin", int.class, previousMin);
                invoke(sensor, "setMax", int.class, previousMax);
            }
            applied = false;
        } catch (ReflectiveOperationException e) {
            CTNHLib.LOGGER.error("NeutronSensorChange could not restore the sensor at {}", machinePos, e);
        }
    }

    private static void invoke(Object target, String name, Class<?> type, Object value)
                                                                                        throws ReflectiveOperationException {
        Method method = target.getClass().getDeclaredMethod(name, type);
        method.setAccessible(true);
        method.invoke(target, value);
    }

    private static <T> T read(Object target, String name, Class<T> type) throws ReflectiveOperationException {
        Field field = target.getClass().getDeclaredField(name);
        field.setAccessible(true);
        return type.cast(field.get(target));
    }
}
