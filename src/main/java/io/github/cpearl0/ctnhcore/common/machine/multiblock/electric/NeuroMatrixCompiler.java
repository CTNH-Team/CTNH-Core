package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import com.ctnhlang.CN;
import com.ctnhlang.EN;

import io.github.cpearl0.ctnhcore.common.machine.multiblock.part.CompilerMachine;
import io.github.cpearl0.ctnhcore.registry.CTNHItems;

import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockDisplayText;
import com.gregtechceu.gtceu.api.machine.multiblock.RecipeElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.handler.RecipeHandlerGroup;
import com.gregtechceu.gtceu.api.recipe.ingredient.item.ItemIngredient;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;

import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tech.vixhentx.mcmod.ctnhlib.utils.MachineUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NeuroMatrixCompiler extends RecipeElectricMultiblockMachine implements ITieredMachine {

    @CN("§c未知错误")
    @EN("§cUnknown error")
    public static Lang compilerError0;


    @CN("§c舱室等级与片区1不匹配")
    @EN("§cChamber tier does not match Region 1")
    public static Lang compilerError1;


    @CN("片区%d状态:%s")
    @EN("Region %d status: %s")
    public static Lang neuroMatrixCompilerInfoPartStates;


    @CN("§c错误,类型：%s")
    @EN("§cError, type: %s")
    public static Lang neuroMatrixCompilerInfoStateError;


    @CN("§a完成")
    @EN("§aComplete")
    public static Lang neuroMatrixCompilerInfoStateFinish;


    @CN("§6待机")
    @EN("§6Idle")
    public static Lang neuroMatrixCompilerInfoStateIdle;


    @CN("§b等待中......")
    @EN("§bWaiting......")
    public static Lang neuroMatrixCompilerInfoStateWaiting;


    @CN("§9运行中:%ss/%ss")
    @EN("§9Working: %ss/%ss")
    public static Lang neuroMatrixCompilerInfoStateWorking;



    public NeuroMatrixCompiler(IMachineBlockEntity holder) {
        super(holder);
    }

    @Persisted
    public GTRecipe lastrecipe;
    @Persisted
    public List<Long> Equation = new ArrayList<Long>();
    @Persisted
    public List<Long> RawEquation;
    @Persisted
    public long Noise = 1;
    @Persisted
    public long range;
    @Persisted
    public CompilerMachine part1;
    @Persisted
    public CompilerMachine part2;
    @Persisted
    public CompilerMachine part3;
    @Persisted
    public CompilerMachine part4;
    @Persisted
    public CompilerMachine part5;
    @Persisted
    public CompilerMachine part6;
    @Persisted
    public List<Integer> states = new ArrayList<>();
    @Persisted
    public double Noise_Muti = 5.0;
    @Persisted
    public int tiers = 0;
    @Persisted
    public int turn = 0;
    @Persisted
    public long ticks = 0;
    @Persisted
    public long true_answer = 0;
    @Persisted
    public int SLOT_COUNT = 1;
    @Persisted
    public double eff;
    @Persisted
    public double eff_fake;
    @Persisted
    public Item items;
    @Persisted
    public long noisea;
    @Persisted
    public long noiseb;
    @Persisted
    public List<Integer> error_message = new ArrayList<>();

    // 数学计算部分
    public List<Long> getRawEquation(long Noise) {
        // 获取原始方程式
        var noise = (long) (Noise * Math.random() * Noise_Muti);
        List<Long> Equation = new ArrayList<Long>();
        for (int i = 1; i <= 4; i++) {
            var ranger = (range) * (Math.random());
            var defaulter = range - ranger;
            Equation.add((long) (defaulter + ranger * ranger * 2));
        }
        return Equation;
    }

    public void getTrueAnswer(GTRecipe recipe) {
        // 获得真正的y
        true_answer = 0;
        for (int i = 0; i < 3; i++) {
            var num = recipe.data.getLong(String.valueOf(i));

            num = (long) ((num / 2) + Math.random() * num);
            if (num < 1) num = 1;
            if (num > 64) num = 64;
            true_answer = num * RawEquation.get(i);
        }
    }

    public double CalculateEquationNoise(long Noise) {
        // 计算方程式，但带噪声
        var noise = (long) (Noise * Math.random() * Noise_Muti);
        long answer = 0;
        for (int i = 0; i < 3; i++) {
            answer += RawEquation.get(i) * Equation.get(i);
        }
        answer += RawEquation.get(3);
        var fake_answer = (long) (answer + noise * Math.random());
        return ((double) fake_answer / true_answer);
    }

    public double CalculateEquation(long Noise) {
        // 计算方程式
        var noise = (long) (Noise * Math.random() * Noise_Muti);
        long answer = 0;
        for (int i = 0; i < 3; i++) {
            answer += RawEquation.get(i) * Equation.get(i);
        }
        answer += RawEquation.get(3);
        var fake_answer = (long) (answer + noise * Math.random());
        return ((double) answer / true_answer);
    }

    public void getNoise() {
        if (Equation.get(4) >= noiseb) {
            Noise_Muti -= 3;
        }
        if (Equation.get(3) > noisea)
            Noise_Muti = Noise_Muti * (1 + (noisea / Equation.get(3)));
        if (Equation.get(3) < noisea)
            Noise_Muti = Noise_Muti * (1 + (Equation.get(3) / noisea));
        if (Equation.get(3) == noisea && Equation.get(5) >= noiseb) {
            Noise_Muti = 0.5;
        }
    }
    //

    // 槽位检测部分
    public void reload_recipe(GTRecipe recipe) {
        range = recipe.data.getInt("range");
        noisea = recipe.data.getInt("noisea");
        noiseb = recipe.data.getInt("noiseb");
        RawEquation = getRawEquation(range);
        getTrueAnswer(recipe);
    }

    public Boolean CheckRecipeValid(GTRecipe recipe, List<Item> itemList) {
        // 这代码写的太丑陋了，我自己都受不了了
        var p = part1.getInventory().getStackInSlot(0).getItem().asItem();
        var p1 = part1.getInventory().getStackInSlot(0);
        var p2 = part2.getInventory().getStackInSlot(0);
        var p3 = part3.getInventory().getStackInSlot(0);
        var p4 = part4.getInventory().getStackInSlot(0);
        var p5 = part5.getInventory().getStackInSlot(0);
        var p6 = part6.getInventory().getStackInSlot(0);
        List<ItemStack> itemcontains = new ArrayList<ItemStack>();
        itemcontains.add(p1);
        itemcontains.add(p2);
        itemcontains.add(p3);
        itemcontains.add(p4);
        itemcontains.add(p5);
        for (int i = 0; i < itemList.size(); i++) {
            if (!itemcontains.get(i).getItem().asItem().equals(itemList.get(i).asItem())) {
                return false;
            }
        }
        if (!p6.getItem().asItem().equals(CTNHItems.RESEARCH_DATASET)) return false;
        return true;
    }

    public int RecipeConsume(int turn) {
        // 这写的就是一坨
        if (turn > 4) {
            return 0;
        }
        List<CompilerMachine> Inventorys = new ArrayList<CompilerMachine>();
        Inventorys.add(part1);
        Inventorys.add(part2);
        Inventorys.add(part3);
        Inventorys.add(part4);
        Inventorys.add(part5);
        var inventory = Inventorys.get(turn).getInventory();
        var num = 0;
        if (!inventory.getStackInSlot(0).isEmpty()) num = inventory.getStackInSlot(0).getCount();
        Inventorys.get(turn).getInventory().setStackInSlot(0, ItemStack.EMPTY);
        return num;
    }

    //

    @Override
    public boolean onWorking() {
        if (turn > 4 && turn < 10) {
            getRecipeLogic().setProgress(getRecipeLogic().getDuration() - 100);
            getNoise();
            eff_fake = CalculateEquationNoise(range);
            eff = CalculateEquation(range);
            turn = 10;
        } else if (turn <= 4) states.set(turn, 1);
        if (getRecipeLogic().getProgress() > 100 && turn <= 4) {
            states.set(turn, 3);
            Equation.add((long) RecipeConsume(turn) + 1);
            ticks = getOffsetTimer();
            var tier = getTier();
            turn += 1;
            getRecipeLogic().setProgress(0);
        }
        return super.onWorking();
    }

    @Override
    public void afterWorking() {
        turn = 0;
        super.afterWorking();
        for (int i = 0; i <= 4; i++) {
            states.set(i, 0);
        }
        if (eff >= 0.9 && eff <= 1.1) {
            part6.getInventory().setStackInSlot(0, new ItemStack(items));
        } else {
            var nbt = part6.getInventory().getStackInSlot(0).getOrCreateTag();
            nbt.putLongArray("formula", Equation);
            nbt.putDouble("muti", eff_fake);
            nbt.putDouble("Noise", Noise_Muti);
        }
        eff = 0;
        eff_fake = 0;
        Equation.clear();
        Noise_Muti = 5;
    }

    @Override
    public void onStructureFormed() {
        states.clear();
        error_message.clear();
        var tier = getTier();
        var pos = getPos();
        var m1 = getMachine(this.getLevel(), MachineUtils.getOffset(this, -11, 0, -6));
        states.clear();
        var mx = getBlockState();
        if (m1 instanceof CompilerMachine) {
            part1 = (CompilerMachine) m1;
            tiers = part1.getTier();
            part1.set_id(1);
        }
        var m2 = getMachine(this.getLevel(), MachineUtils.getOffset(this, 11, 0, -6));
        var m3 = getMachine(this.getLevel(), MachineUtils.getOffset(this, -15, 0, 7));
        var m4 = getMachine(this.getLevel(), MachineUtils.getOffset(this, 15, 0, 7));
        var m5 = getMachine(this.getLevel(), MachineUtils.getOffset(this, 0, 0, 22));
        var m6 = getMachine(this.getLevel(), MachineUtils.getOffset(this, 0, 1, 0));
        if (m2 instanceof IMultiPart) {
            part2 = (CompilerMachine) m2;
            part2.set_id(2);
        }
        if (m3 instanceof IMultiPart) {
            part3 = (CompilerMachine) m3;
            part3.set_id(3);
        }
        if (m4 instanceof IMultiPart) {
            part4 = (CompilerMachine) m4;
            part4.set_id(4);
        }
        if (m5 instanceof IMultiPart) {
            part5 = (CompilerMachine) m5;
            part5.set_id(5);
        }
        if (m6 instanceof IMultiPart) {
            part6 = (CompilerMachine) m6;
            part6.set_id(6);
        }
        for (int i = 0; i <= 4; i++) {
            states.add(0);
            error_message.add(-1);

        }
        if (part2.getTier() != tiers) {
            onStructureInvalid();
            states.set(1, 2);
            error_message.set(1, 0);
            return;
        }

        if (part3.getTier() != tiers) {
            onStructureInvalid();
            states.set(2, 2);
            error_message.set(2, 0);
            return;
        }

        if (part4.getTier() != tiers) {
            onStructureInvalid();
            states.set(3, 2);
            error_message.set(3, 0);
            return;
        }

        if (part5.getTier() != tiers) {
            onStructureInvalid();
            states.set(4, 2);
            error_message.set(4, 0);
            return;
        }

        if (part6.getTier() != tiers) {
            onStructureInvalid();
            states.set(5, 2);
            error_message.set(5, 0);
            return;
        }
        super.onStructureFormed();
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
    }

    @Override
    public @Nullable Component beforeWorking(@NotNull GTRecipe recipe) {
        var itemsR = recipe.getInputContents(ItemRecipeCapability.CAP).stream()
                .map(ItemIngredient::getItems) // 获取 ItemStack 数组
                .filter(itemStacks -> itemStacks.length > 0) // 确保至少有一个 ItemStack
                .map(itemStacks -> itemStacks[0].getItem()) // 获取第一个 ItemStack 的 Item
                .collect(Collectors.toList()); // 收集到一个 List<Item>
        if (!recipe.equals(lastrecipe)) {
            lastrecipe = recipe;
            reload_recipe(recipe);

        }
        CheckRecipeValid(recipe, itemsR);
        ticks = getOffsetTimer();

        return super.beforeWorking(recipe);
    }

    public static Component recipeModifier(MetaMachine machine, RecipeHandlerGroup group, GTRecipe recipe) {
        if (machine instanceof NeuroMatrixCompiler nmachine) {
            for (int i = 0; i <= 4; i++) {
                nmachine.states.set(i, 4);
            }
            var itemsX = recipe.getOutputContents(ItemRecipeCapability.CAP).stream()
                    .map(ItemIngredient::getItems) // 获取 ItemStack 数组
                    .filter(itemStacks -> itemStacks.length > 0) // 确保至少有一个 ItemStack
                    .map(itemStacks -> itemStacks[0].getItem()) // 获取第一个 ItemStack 的 Item
                    .toList(); // 收集到一个 List<Item>
            if (!itemsX.isEmpty()) nmachine.items = itemsX.get(0).asItem();

            recipe.outputs.remove(ItemRecipeCapability.CAP);
            return null;
        }
        return RecipeModifier.nullWrongType(NeuroMatrixCompiler.class, machine);
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        // super.addDisplayText(textList);
        MultiblockDisplayText.builder(textList, this.isFormed())
                .setWorkingStatus(this.recipeLogic.isWorkingEnabled(), this.recipeLogic.isActive())
                .addMachineModeLine(this.getRecipeType(), this.getRecipeTypes().length > 1)
                .addWorkingStatusLine()
                .addProgressLine((double) this.recipeLogic.getProgress(), (double) this.recipeLogic.getMaxProgress(),
                        this.recipeLogic.getProgressPercent())
                .addCustom(List -> {
                    for (int i = 0; i < states.size(); i++) {
                        List.add(addProgressPartSatus(i));
                    }
                });
        this.getDefinition().getAdditionalDisplay().accept(this, textList);
    }

    public MutableComponent error_analyse(int i) {
        if (i == -1) {
            return compilerError0.translate();
        }
        if (i == 0)
            return compilerError1.translate();
        return compilerError0.translate();
    }

    public MutableComponent addProgressPartSatus(int i) {
        if (states.get(i) == 0) {
            return (neuroMatrixCompilerInfoPartStates.translate(
                    new Object[] { i + 1, neuroMatrixCompilerInfoStateIdle.translate() }));
        }
        if (states.get(i) == 1) {
            return (neuroMatrixCompilerInfoPartStates.translate(
                    new Object[] { i + 1,
                            neuroMatrixCompilerInfoStateWorking.translate( new Object[] {
                                    String.format("%.2f", (double) this.recipeLogic.getProgress() / 20),
                                    String.format("%.2f", (double) this.recipeLogic.getMaxProgress() / 120) }) }));
        }
        if (states.get(i) == 2) {
            return (neuroMatrixCompilerInfoPartStates.translate(
                    new Object[] { i + 1, neuroMatrixCompilerInfoStateError.translate(
                            new Object[] { error_analyse(error_message.get(i)) }) }));
        }
        if (states.get(i) == 3) {
            return (neuroMatrixCompilerInfoPartStates.translate(
                    new Object[] { i + 1, neuroMatrixCompilerInfoStateFinish.translate() }));
        }
        if (states.get(i) == 4) {
            return (neuroMatrixCompilerInfoPartStates.translate(
                    new Object[] { i + 1, neuroMatrixCompilerInfoStateWaiting.translate() }));
        }
        return (neuroMatrixCompilerInfoPartStates.translate(
                new Object[] { i + 1, neuroMatrixCompilerInfoStateError.translate() }));
    }
}
