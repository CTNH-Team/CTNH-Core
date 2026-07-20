package io.github.cpearl0.ctnhcore.common.machine.multiblock.generator;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import com.ctnhlang.CN;
import com.ctnhlang.EN;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.RecipeElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.handler.RecipeHandlerGroup;

import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import lombok.Getter;
import lombok.Setter;
import tech.vixhentx.mcmod.ctnhlib.utils.MachineUtils;

import java.util.List;
import java.util.Objects;

@Setter
@Getter
public class NaqReactorMachine extends RecipeElectricMultiblockMachine implements ITieredMachine {

    @CN("镍等离子体消耗量: %d")
    @EN("Nickel plasma consumption: %d")
    public static Lang naqReactorInfoNickelConsumption;


    @CN("发电并行数: %d")
    @EN("Power generation parallel count: %d")
    public static Lang naqReactorInfoParallelCount;


    @CN("§c内核温度: %d")
    @EN("§cCore temperature: %d")
    public static Lang naqReactorInfoTemperature;



    @Persisted
    private int currentTemperature = 0;  // 初始温度为0K
    private static final int MAX_TEMP = 80000;  // 温度最高为80000K
    private static final int MIN_TEMP = 0;     // 温度最低为0K
    private static final int HEAT_SPEED = 10;  // 每次运行配方后温度上升1000K

    // 流体消耗量（单位：mB）
    private static final int FLUID_AMOUNT = 40;  // 每次消耗40mb的镍等离子体
    private int inactiveTimer = 0;  // 记录停止工作时的计时器

    public NaqReactorMachine(IMachineBlockEntity holder) {
        super(holder);
    }

    @Override
    public boolean onWorking() {
        if (getOffsetTimer() % 20 == 0) { // 每秒执行一次
            int parallelCount = getParallelCount();  // 获取当前并行数
            int fluidConsumption = FLUID_AMOUNT * parallelCount;  // 计算消耗量

            FluidStack nickelPlasmaFluid = new FluidStack(
                    Objects.requireNonNull(ForgeRegistries.FLUIDS.getValue(
                            ResourceLocation.tryParse("gtceu:nickel_plasma"))),
                    fluidConsumption  // 动态调整流体消耗量
            );

            boolean isFluidSufficient = MachineUtils.inputFluids(this, nickelPlasmaFluid);  // 检查是否有足够流体

            if (isFluidSufficient) {
                increaseTemperature();  // 机器工作时升温
                inactiveTimer = 0;  // 重置停止工作计时器
                return super.onWorking();  // 如果有足够流体，继续执行配方
            }

            // 如果流体不足，停止配方进度
            getRecipeLogic().setProgress(0);  // 重置配方进度
            inactiveTimer++;  // 记录停止工作时的时间

            if (inactiveTimer >= 20) {  // 每1秒降低一次温度
                decreaseTemperature();
                inactiveTimer = 0;  // 重置计时器
            }

            return false;  // 流体不足时完全停止工作，不启动配方
        }

        return super.onWorking();  // 调用父类的 onWorking 方法
    }

    // 降低温度的方法（每秒减少 1000K）
    private void decreaseTemperature() {
        currentTemperature = Math.max(currentTemperature - 1000, MIN_TEMP);
    }

    // 增加温度，每次运行配方后温度增加10K
    private void increaseTemperature() {
        currentTemperature = Math.min(currentTemperature + HEAT_SPEED, MAX_TEMP);
    }

    // 根据温度调整并行数
    public int getParallelCount() {
        if (currentTemperature >= 60000) {
            return 64;  // 温度达到60000时并行数为64
        } else if (currentTemperature >= 30000) {
            return 16;  // 温度达到30000时并行数为16
        } else if (currentTemperature >= 10000) {
            return 8;   // 温度达到10000时并行数为8
        } else if (currentTemperature >= 5000) {
            return 4;   // 温度达到5000时并行数为4
        } else if (currentTemperature >= 1000) {
            return 2;   // 温度达到1000时并行数为2
        } else {
            return 1;   // 温度小于1000时并行数为1
        }
    }

    // recipeModifier 实现，根据温度调整并行数
    public static Component recipeModifier(MetaMachine machine, RecipeHandlerGroup group, GTRecipe recipe) {
        if (machine instanceof NaqReactorMachine reactorMachine) {
            int parallelCount = reactorMachine.getParallelCount();
            // 并行数影响Eut输出量

            recipe.multiplyAllContents(parallelCount);
            recipe.parallels *= parallelCount;
            recipe.multiplyEUt(parallelCount);
            return null;
        }
        return null;
    }

    // 更新GUI显示文本
    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);
        int parallelCount = getParallelCount();  // 获取当前并行数
        int fluidConsumption = FLUID_AMOUNT * parallelCount;  // 计算实际消耗量
        textList.add(naqReactorInfoTemperature.translate( currentTemperature + "K"));
        textList.add(
                naqReactorInfoNickelConsumption.translate( fluidConsumption + "mb"));
        textList.add(naqReactorInfoParallelCount.translate( getParallelCount()));
    }

    @Override
    public boolean regressWhenWaiting() {
        return false;
    }
}
