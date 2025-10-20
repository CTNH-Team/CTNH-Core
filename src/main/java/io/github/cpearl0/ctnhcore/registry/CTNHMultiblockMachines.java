package io.github.cpearl0.ctnhcore.registry;

import com.gregtechceu.gtceu.common.data.machines.GTMultiMachines;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.CleanroomMachine;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.GTNNMultiblocks;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksA;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksB;
import io.github.cpearl0.ctnhcore.registry.machines.multiblock.MultiblocksC;
import net.minecraft.network.chat.Component;

import java.lang.reflect.Field;

import static com.gregtechceu.gtceu.common.data.machines.GTMultiMachines.CLEANROOM;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class CTNHMultiblockMachines {
    static {
        REGISTRATE.creativeModeTab(() -> CTNHCreativeModeTabs.MACHINE);
    }
    public static void init() {
        MultiblocksA.init();
        MultiblocksB.init();
        MultiblocksC.init();
        GTNNMultiblocks.init();
        CLEANROOM.setBeforeWorking(
                (machine, recipe) -> {
                    if (machine instanceof CleanroomMachine cleanroom) {
                        try {
                            // Use reflection to access the private distance fields
                            Class<?> cleanroomClass = cleanroom.getClass();

                            // Get the fields
                            Field lDistField = cleanroomClass.getDeclaredField("lDist");
                            Field rDistField = cleanroomClass.getDeclaredField("rDist");
                            Field bDistField = cleanroomClass.getDeclaredField("bDist");
                            Field fDistField = cleanroomClass.getDeclaredField("fDist");
                            Field hDistField = cleanroomClass.getDeclaredField("hDist");

                            // Make them accessible
                            lDistField.setAccessible(true);
                            rDistField.setAccessible(true);
                            bDistField.setAccessible(true);
                            fDistField.setAccessible(true);
                            hDistField.setAccessible(true);

                            // Get the values
                            int lDist = lDistField.getInt(cleanroom);
                            int rDist = rDistField.getInt(cleanroom);
                            int bDist = bDistField.getInt(cleanroom);
                            int fDist = fDistField.getInt(cleanroom);
                            int hDist = hDistField.getInt(cleanroom);

                            // Calculate length and width
                            int length = lDist + rDist;
                            int width = bDist + fDist;

                            // Check if height is greater than length or width
                            return hDist <= length && hDist <= width;
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            // Handle reflection errors
                            e.printStackTrace();
                            return true;
                        }
                    }
                    return true;
                }
        );
        CLEANROOM.setTooltipBuilder(CLEANROOM.getTooltipBuilder().andThen(
                (stack, tooltip) -> {
                    tooltip.add(Component.literal("高度大于长度或宽度时，将会停止工作"));
                }
        ));
    }
}