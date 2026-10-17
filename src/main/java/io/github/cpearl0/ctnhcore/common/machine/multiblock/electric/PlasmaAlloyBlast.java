package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.capability.forge.GTCapability;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.handler.RecipeHandlerGroup;
import com.gregtechceu.gtceu.api.recipe.ingredient.fluid.FluidIngredient;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.gregtechceu.gtceu.common.machine.trait.multiblock.CoilMachineTrait;

import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.network.chat.Component;
import net.minecraftforge.fluids.FluidStack;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.utils.MachineUtils;

import java.util.ArrayList;
import java.util.List;

import static com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys.PLASMA;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class PlasmaAlloyBlast extends CoilWorkableElectricMultiblockMachine {

    @CN("线圈温度不足：无法提供并行等级（至少需要 1800K 的线圈）")
    @EN("Coil temperature too low: cannot provide a parallel tier (requires a coil of at least 1800K)")
    public static Lang coilTierTooLow;

    @CN("缺少等离子体：没有消耗任何有效的等离子体输入")
    @EN("Missing plasma: no valid plasma input was consumed")
    public static Lang missingPlasma;

    public PlasmaAlloyBlast(IMachineBlockEntity holder) {
        super(holder);
    }

    @Persisted
    public int machine_level = 0;
    @Persisted
    public int islasor = 0;

    public void onStructureFormed() {
        super.onStructureFormed();
        var tier = getTier();
        var coil_type = getTraitOrThrow(CoilMachineTrait.class).getCoilType().getCoilTemperature();
        islasor = 0;
        machine_level = coil_type / 1800;
        for (IMultiPart part : getParts()) {
            if (part.self().holder.self().getCapability(GTCapability.CAPABILITY_LASER).isPresent()) {
                islasor = 1;
            }
        }
    }

    /**
     * 基础等离子体：每种都提供一份累加的加速，只有真正存在于输入仓里的才会被计入成本。
     */
    private record BasicPlasma(Material material, int amount, double speedBonus) {}

    private static final List<BasicPlasma> BASIC_PLASMAS = List.of(
            new BasicPlasma(Iron, 200, 4.0),
            new BasicPlasma(Nickel, 200, 4.0),
            new BasicPlasma(Argon, 300, 3.0),
            new BasicPlasma(Oxygen, 300, 3.0),
            new BasicPlasma(Nitrogen, 300, 3.0),
            new BasicPlasma(Helium, 500, 2.0));

    public static Component recipeModifier(MetaMachine machine, RecipeHandlerGroup group, GTRecipe recipe) {
        if (!(machine instanceof PlasmaAlloyBlast pmachine)) {
            return RecipeModifier.nullWrongType(PlasmaAlloyBlast.class, machine);
        }
        if (pmachine.machine_level <= 0) return coilTierTooLow.translate();

        var parallel = ParallelLogic.getParallelAmount(group, recipe, pmachine.machine_level * 4);
        var speed = 1.0;
        var eut = 1.0;
        var output = 1.0;
        // 修饰符在候选匹配阶段会被反复调用，这里只做只读检查；
        // 真正消耗的等离子体在末尾作为配方输入交给配方执行流程，避免搜索阶段就把输入仓抽空
        var plasmaCosts = new ArrayList<FluidStack>();

        for (var plasma : BASIC_PLASMAS) {
            var cost = plasma.material().getFluid(PLASMA, plasma.amount() * parallel);
            if (!MachineUtils.canInputFluids(group, cost)) continue;
            plasmaCosts.add(cost);
            speed += plasma.speedBonus();
        }

        var coilTemperature = pmachine.getTraitOrThrow(CoilMachineTrait.class).getCoilType().getCoilTemperature();
        if (coilTemperature > 10000) speed += (coilTemperature - 10000) / 1800.0;

        var adamantite = CTNHMaterials.COMPRESSED_ADAMANTITE.getFluid(PLASMA, 100);
        if (MachineUtils.canInputFluids(group, adamantite)) {
            plasmaCosts.add(adamantite);
            speed *= 5;
            eut = 2.0;
        }
        var aether = CTNHMaterials.COMPRESSED_AETHER.getFluid(PLASMA, 50 * parallel);
        if (MachineUtils.canInputFluids(group, aether)) {
            plasmaCosts.add(aether);
            speed *= 10;
            output -= 0.2 * Math.random();
        }

        if (plasmaCosts.isEmpty()) return missingPlasma.translate();

        // 激光导管是加速手段，原来写成 speed * (0.25 * islasor) 会把速度砍成四分之一
        speed *= 1 + 0.25 * pmachine.islasor;
        // 速率叠得过高时随机减产，作为平衡阀
        if (speed > 50) output = 0.5 * Math.random();

        // (int) 截断会让 parallel * output 小于 1 时把产出直接清零，至少保留 1 倍产出
        var outputMultiplier = Math.max(1, (int) (parallel * output));
        recipe.multiplyInputs(parallel);
        recipe.multiplyOutputs(outputMultiplier);
        recipe.multiplyEUt(eut * parallel);
        recipe.multiplyDuration(1 / speed);
        recipe.parallels *= parallel;
        for (var cost : plasmaCosts) {
            recipe.inputs.add(FluidRecipeCapability.CAP, FluidIngredient.of(cost));
        }
        return null;
    }
}
