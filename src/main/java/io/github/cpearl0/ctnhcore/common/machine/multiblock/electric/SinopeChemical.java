package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import tech.vixhentx.mcmod.ctnhlib.utils.MachineUtils;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.handler.RecipeHandlerGroup;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.common.data.GTMaterialBlocks;

import net.minecraft.network.chat.Component;

import java.util.List;
import java.util.Objects;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class SinopeChemical extends CoilWorkableElectricMultiblockMachine implements ITieredMachine {

    public int parallel = 0;
    public int machine_tier = 0;

    public SinopeChemical(IMachineBlockEntity holder) {
        super(holder);
        var tier = getTier();
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        var tier = getTier();
        var coil_tier = getCoilTier();
        var coil_type = getCoilType().getCoilTemperature();
        machine_tier = coil_type / 1800;
        var blockpos = MachineUtils.getOffset(this, 0, 2, 2);
        var coreblock = Objects.requireNonNull(getLevel()).getBlockState(blockpos).getBlock();
        if (coreblock.equals(GTMaterialBlocks.MATERIAL_BLOCKS.get(TagPrefix.block, Naquadah).get())) {
            parallel = 8;
        }
        if (coreblock.equals(GTMaterialBlocks.MATERIAL_BLOCKS.get(TagPrefix.block, NaquadahEnriched).get())) {
            parallel = 32;
        }
        if (coreblock.equals(GTMaterialBlocks.MATERIAL_BLOCKS.get(TagPrefix.block, Naquadria).get())) {
            parallel = 128;
        }
    }

    public static Component recipeModifier(MetaMachine machine, RecipeHandlerGroup group, GTRecipe recipe) {
        if (machine instanceof SinopeChemical zmachine) {
            var maxparallel = ParallelLogic.getParallelAmount(group, recipe, zmachine.parallel);
            if (maxparallel == 0) return null;
            var reduce = Math.max(1 - 0.005 * maxparallel, 0.75);
            var speed_up = reduce / (zmachine.machine_tier);
            recipe.multiplyEUt(reduce);
            recipe.multiplyAllContents(maxparallel);
            recipe.multiplyDuration(speed_up);
            recipe.parallels *= maxparallel;
            return null;
        }
        return RecipeModifier.DEFAULT_FAILURE;
    }

    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);
        var tier = getTier();

        textList.add(textList.size(), Component.translatable("ctnh.multiblock.sinope_chemical.info.level",
                String.format("%d", machine_tier * 2)));
        textList.add(textList.size(),
                Component.translatable("ctnh.multiblock.sinope_chemical.info.parallel", String.format("%d", parallel)));
    }
}
