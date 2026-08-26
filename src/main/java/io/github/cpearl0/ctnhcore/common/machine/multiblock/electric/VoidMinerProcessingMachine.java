package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import io.github.cpearl0.ctnhcore.common.machine.multiblock.generator.NaqReactorMachine;
import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.RecipeElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.handler.RecipeHandlerGroup;
import com.gregtechceu.gtceu.api.recipe.ingredient.fluid.FluidIngredient;
import com.gregtechceu.gtceu.api.recipe.ingredient.item.ItemIngredient;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.utils.GTUtil;

import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import com.ctnh.ctnhastral.data.CAMaterials;
import com.ctnhlang.CN;
import com.ctnhlang.EN;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.utils.MachineUtils;

import java.util.*;

@Setter
@Getter
public class VoidMinerProcessingMachine extends RecipeElectricMultiblockMachine {

    @CN("极寒之凛冰消耗：%d ")
    @EN("Cryotheum consumption: %d ")
    public static Lang voidMinerInfoCryotheum;

    @CN("过热!!!")
    @EN("Overheating!!!")
    public static Lang voidMinerInfoOverheat;

    @CN("烈焰之炽焱消耗：%d ")
    @EN("Pyrotheum consumption: %d ")
    public static Lang voidMinerInfoPyrotheum;

    @Persisted
    @DescSynced
    private int currentTemperature = 0;  // 初始温度为0K
    private static final int MAX_TEMP = 25000;
    private static final int MIN_TEMP = 0;
    @Persisted
    private boolean isOverheated = false; // 过热状态标志
    private int fluidAmount = FLUID_AMOUNT; // 定义为类的成员变量
    // 流体消耗量（单位：mB）
    private static final int FLUID_AMOUNT = 100;  // 每次消耗的流体量（可调整）
    @Persisted
    private int nextPyrotheumAmount = 1000;  // 初始为 1000mb
    @Persisted
    private int nextCryotheumAmount = 1000;  // 初始为 1000mb
    private static final Random random = new Random();
    @Persisted
    private int fluidCycle = 1;

    private static List<ItemStack> rawOreItems = null;

    public VoidMinerProcessingMachine(IMachineBlockEntity holder) {
        super(holder);
    }

    public boolean onWorking() {
        if (getOffsetTimer() % 20 == 0) {
            int temperature = getCurrentTemperature();

            if (isOverheated) {
                // 降温逻辑
                temperature = Math.max(MIN_TEMP, temperature - 100);
                setCurrentTemperature(temperature);
                if (temperature == MIN_TEMP) {
                    isOverheated = false; // 降温结束，恢复正常状态
                }
                return false; // 无法继续运行
            }
            // 检查当前温度
            if (temperature >= MAX_TEMP) {
                isOverheated = true; // 进入过热状态
                nextPyrotheumAmount = FLUID_AMOUNT;
                nextCryotheumAmount = FLUID_AMOUNT;
            }

        }
        return super.onWorking();
    }

    public Component beforeWorking(@Nullable GTRecipe recipe) {
        int temperature = getCurrentTemperature();
        if (fluidCycle == 1) {  // 偶数次使用 Pyrotheum
            int currentFluidAmount = nextPyrotheumAmount;  // 使用更新后的流体量

            var pyrotheumFluid = CTNHMaterials.Pyrotheum.getFluid(currentFluidAmount);

            // 检查输入仓是否有足够流体
            boolean isFluidSufficient = MachineUtils.inputFluids(this, pyrotheumFluid);

            if (isFluidSufficient) {
                // 流体足够，执行温度变化
                int temperatureIncrease = currentFluidAmount / 100;
                temperature = Math.min(MAX_TEMP, temperature + temperatureIncrease);
                setCurrentTemperature(temperature);

                // 更新流体消耗量
                nextPyrotheumAmount = (int) Math.floor(currentFluidAmount * 1.02);  // 更新流体量
            }
            // 如果流体不足，什么也不做，机器继续工作，温度不变

            // 切换到下一轮流体
            fluidCycle = 2;  // 下一次使用 Cryotheum
        } else if (fluidCycle == 2) {  // 奇数次使用 Cryotheum
            int currentFluidAmount = nextCryotheumAmount;  // 使用更新后的流体量

            var cryotheumFluid = CTNHMaterials.Cryotheum.getFluid(currentFluidAmount);

            // 检查输入仓是否有足够流体
            boolean isFluidSufficient = MachineUtils.inputFluids(this, cryotheumFluid);

            if (isFluidSufficient) {
                // 流体足够，执行温度变化
                int temperatureDecrease = currentFluidAmount / 100;
                temperature = Math.max(MIN_TEMP, temperature - temperatureDecrease);
                setCurrentTemperature(temperature);

                // 更新流体消耗量
                nextCryotheumAmount = (int) Math.floor(currentFluidAmount * 1.02);  // 更新流体量
            }
            // 如果流体不足，什么也不做，机器继续工作，温度不变

            // 切换到下一轮流体
            fluidCycle = 1;  // 下一次使用 Pyrotheum
        }
        // 流体充足，允许配方运行
        return super.beforeWorking(recipe);
    }

    // 更新GUI显示文本
    @Override
    public void addDisplayText(@NotNull List<Component> textList) {
        super.addDisplayText(textList);

        if (isOverheated) {
            textList.add(
                    voidMinerInfoOverheat.translate().withStyle(ChatFormatting.RED));
        }
        textList.add(NaqReactorMachine.naqReactorInfoTemperature.translate(
                Component.literal(currentTemperature + "K").withStyle(ChatFormatting.RED)));
        textList.add(voidMinerInfoPyrotheum.translate(nextPyrotheumAmount + " mB")
                .withStyle(ChatFormatting.GOLD));
        textList.add(voidMinerInfoCryotheum.translate(nextCryotheumAmount + " mB")
                .withStyle(ChatFormatting.AQUA));
    }

    public int getParallelCount() {
        if (currentTemperature >= 24000) {
            return 16;  // 温度达到24000时并行数为16
        } else if (currentTemperature >= 20000) {
            return 12;  // 温度达到20000时并行数为12
        } else if (currentTemperature >= 17000) {
            return 8;   // 温度达到17000时并行数为8
        } else if (currentTemperature >= 12000) {
            return 4;   // 温度达到12000时并行数为4
        } else if (currentTemperature >= 10000) {
            return 2;   // 温度达到10000时并行数为2
        } else {
            return 1;   // 温度小于10000时并行数为1
        }
    }

    public static Component recipeModifier(MetaMachine machine, RecipeHandlerGroup group, GTRecipe recipe) {
        if (machine instanceof VoidMinerProcessingMachine reactorMachine && !reactorMachine.isOverheated) {

            int parallelCount = reactorMachine.getParallelCount();

            // 每种物品的基础数量
            int baseAmount = 640;

            // 输出物品的数量会根据并行数调整
            int adjustedAmount = baseAmount * parallelCount;

            // 获取带有 '#c:raw_ores' 标签的所有物品
            List<ItemStack> rawOreItems = getRawOreItems();

            // 确保 rawOreItems 不是空的
            if (rawOreItems.isEmpty()) {
                return null;
            }

            // 随机打乱物品顺序
            Collections.shuffle(rawOreItems, random);

            // 创建一个物品列表
            List<ItemIngredient> itemList = new ArrayList<>();

            // 如果我们有至少10个符合条件的物品，输出它们
            for (int i = 0; i < Math.min(10, rawOreItems.size()); i++) {
                // 获取当前随机选中的矿物
                ItemStack rawOreItem = rawOreItems.get(i);

                // 创建物品的内容并添加到 itemList
                if (!rawOreItem.isEmpty()) {
                    // 创建一个大小为 adjustedAmount 的物品
                    var ingredient = ItemIngredient.of(rawOreItem, adjustedAmount);
                    // 使用 SizedIngredient 创建 Content，并确保数量正确
                    itemList.add(ingredient);
                }
            }
            if (!itemList.isEmpty()) {
                recipe.inputs.put(FluidRecipeCapability.CAP,
                        GTUtil.list(FluidIngredient.of(GTMaterials.DrillingFluid, 100_000_000)));
                recipe.outputs.put(ItemRecipeCapability.CAP, itemList);
            }
            return null;
        }

        return null;
    }

    // 获取所有带有 '#c:raw_ores' 标签的物品，排除黑名单物品
    private static List<ItemStack> getRawOreItems() {
        if (rawOreItems == null) {
            rawOreItems = new ArrayList<>();
            TagKey<Item> rawOresTag = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(),
                    ResourceLocation.tryBuild("forge", "raw_materials"));

            // 黑名单物品列表（需要排除的物品）
            Set<Item> blacklist = getBlacklistedItems();

            // 遍历所有注册的物品
            for (Item item : ForgeRegistries.ITEMS) {
                // 获取物品的默认实例
                ItemStack itemStack = item.getDefaultInstance();

                // 检查物品是否属于 '#c:raw_ores' 标签，并且不在黑名单中
                if (itemStack.is(rawOresTag) && !blacklist.contains(item)) {
                    rawOreItems.add(itemStack);
                }
            }
        }

        return rawOreItems;
    }

    // 获取黑名单中的物品（返回黑名单列表）
    private static Set<Item> getBlacklistedItems() {
        Set<Item> blacklist = new HashSet<>();
        blacklist.add(ChemicalHelper.get(TagPrefix.rawOre, CTNHMaterials.Jasper).getItem());
        blacklist.add(ChemicalHelper.get(TagPrefix.rawOre, CTNHMaterials.FlowingAmberGold).getItem());
        blacklist.add(ChemicalHelper.get(TagPrefix.rawOre, CTNHMaterials.SpecialCompositeSteelM77).getItem());
        blacklist.add(ChemicalHelper.get(TagPrefix.rawOre, CTNHMaterials.HiddenAlloy).getItem());
        blacklist.add(ChemicalHelper.get(TagPrefix.rawOre, GTMaterials.Iridium).getItem());
        blacklist.add(ChemicalHelper.get(TagPrefix.rawOre, GTMaterials.Osmium).getItem());
        blacklist.add(ChemicalHelper.get(TagPrefix.rawOre, CTNHMaterials.Infinity).getItem());
        blacklist.add(ChemicalHelper.get(TagPrefix.rawOre, CAMaterials.Starmetal).getItem());
        return blacklist;
    }
}
