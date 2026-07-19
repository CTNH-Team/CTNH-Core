package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.RecipeElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.handler.RecipeHandlerGroup;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;
import tech.vixhentx.mcmod.ctnhlib.utils.MachineUtils;

import java.util.List;

public class ForestMachine extends RecipeElectricMultiblockMachine {

    @Key("ctnh.multiblock.forest_machine.info.humidity")
    @CN("湿度值：%d")
    @EN("Humidity level: %d")
    public static Lang forestMachineInfoHumidity;


    @Key("ctnh.multiblock.forest_machine.info.parallel_count")
    @CN("并行数：%d")
    @EN("Parallel count: %d")
    public static Lang forestMachineInfoParallelCount;



    // 湿度值
    private int humidity = 0;  // 初始湿度为0%
    private static final int MAX_HUMIDITY = 100;  // 最大湿度为100%
    private static final int MIN_HUMIDITY = 0;    // 最低湿度为0%

    private String HUMIDITY = "humidity";

    // 流体消耗量（单位：mB）
    private static final int FLUID_AMOUNT = 10000;  // 每次消耗10000mb的水

    public ForestMachine(IMachineBlockEntity holder) {
        super(holder);
    }

    // 处理工作状态
    @Override
    public boolean onWorking() {
        if (getOffsetTimer() % 100 == 0) { // 每100tick执行一次

            // 检查输入仓是否有水
            FluidStack waterFluid = new FluidStack(
                    Fluids.WATER,
                    FLUID_AMOUNT);

            boolean isFluidSufficient = MachineUtils.inputFluids(this, waterFluid);  // 检查是否有足够流体

            // 如果流体充足，增加湿度
            if (isFluidSufficient) {
                increaseHumidity();
            } else {
                decreaseHumidity(10);  // 流体不足时减少湿度10%
            }
            return super.onWorking();
        }
        return super.onWorking();
    }

    // 增加湿度
    private void increaseHumidity() {
        humidity = Math.min(humidity + 1, MAX_HUMIDITY);  // 湿度增加，最大不超过100%
    }

    // 降低湿度
    private void decreaseHumidity(int amount) {
        humidity = Math.max(humidity - amount, MIN_HUMIDITY);  // 湿度减少，最小不低于0%
    }

    // 根据湿度计算并行数，电压 * 湿度系数
    public int getParallelCount() {
        // 如果湿度为 0，默认并行数为 1
        int humidityCoefficient = humidity > 0 ? humidity : 1;  // 湿度大于0时才根据湿度计算，否则为1
        return (int) (getOverclockVoltage() * (humidityCoefficient / 100.0));  // 并行数 = 电压 * 湿度系数
    }

    // recipeModifier 实现，根据湿度调整并行数
    public static Component recipeModifier(MetaMachine machine, RecipeHandlerGroup group, GTRecipe recipe) {
        if (machine instanceof ForestMachine forestMachine) {
            int parallelCount = forestMachine.getParallelCount();
            recipe.multiplyAllContents(parallelCount);
            recipe.parallels *= parallelCount;
        }
        return null;
    }

    // GUI显示文本
    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);
        textList.add(forestMachineInfoHumidity.translate( humidity + "%"));
        textList.add(forestMachineInfoParallelCount.translate( getParallelCount()));
    }

    @Override
    public void saveCustomPersistedData(@NotNull CompoundTag tag, boolean forDrop) {
        super.saveCustomPersistedData(tag, forDrop);
        if (!forDrop) {
            tag.putInt(HUMIDITY, humidity);
        }
    }

    @Override
    public void loadCustomPersistedData(@NotNull CompoundTag tag) {
        super.loadCustomPersistedData(tag);
        humidity = tag.contains(HUMIDITY) ? tag.getInt(HUMIDITY) : 0;
    }
}
