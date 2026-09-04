package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import io.github.cpearl0.ctnhcore.registry.material.CTNHMaterials;

import com.gregtechceu.gtceu.api.capability.forge.GTCapability;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.handler.RecipeHandlerGroup;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.gregtechceu.gtceu.common.machine.trait.multiblock.CoilMachineTrait;

import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.network.chat.Component;

import tech.vixhentx.mcmod.ctnhlib.utils.MachineUtils;

import static com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys.PLASMA;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class PlasmaAlloyBlast extends CoilWorkableElectricMultiblockMachine {

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
        var coil_tier = getTrait(CoilMachineTrait.class).getCoilTier();
        var coil_type = getTrait(CoilMachineTrait.class).getCoilType().getCoilTemperature();
        islasor = 0;
        machine_level = coil_type / 1800;
        for (IMultiPart part : getParts()) {
            if (part.self().holder.self().getCapability(GTCapability.CAPABILITY_LASER).isPresent()) {
                islasor = 1;
            }
        }
    }

    public static Component recipeModifier(MetaMachine machine, RecipeHandlerGroup group, GTRecipe recipe) {
        if (machine instanceof PlasmaAlloyBlast pmachine) {
            var speed = 1.0;
            var output = 1.0;
            var eut = 1.0;
            var total_speed = 1.0;
            int parallel = ParallelLogic.getParallelAmount(group, recipe, pmachine.machine_level * 4);
            if (MachineUtils.inputFluids(pmachine, Iron.getFluid(PLASMA, 200 * parallel))) {
                speed = 4.0;
            }
            if (MachineUtils.inputFluids(pmachine, Nickel.getFluid(PLASMA, 200 * parallel))) {
                speed = 4.0;
            }
            total_speed += speed;
            if (MachineUtils.inputFluids(pmachine, Argon.getFluid(PLASMA, 300 * parallel))) {
                speed = 3.0;
            }
            if (MachineUtils.inputFluids(pmachine, Oxygen.getFluid(PLASMA, 300 * parallel))) {
                speed = 3.0;
            }
            if (MachineUtils.inputFluids(pmachine, Nitrogen.getFluid(PLASMA, 300 * parallel))) {
                speed = 3.0;
            }
            total_speed += speed;
            if (MachineUtils.inputFluids(pmachine, Helium.getFluid(PLASMA, 500 * parallel))) {
                speed = 2.0;
            }
            total_speed = speed;
            if (pmachine.getTrait(CoilMachineTrait.class).getCoilType().getCoilTemperature() > 10000) {
                total_speed += ((double) (pmachine.getTrait(CoilMachineTrait.class).getCoilType().getCoilTemperature() -
                        10000) / 1800);
            }
            if (MachineUtils.inputFluids(pmachine, CTNHMaterials.COMPRESSED_ADAMANTITE.getFluid(PLASMA, 100))) {
                total_speed *= 5;
                eut = 2.0;
            }
            if (MachineUtils.inputFluids(pmachine,
                    CTNHMaterials.COMPRESSED_AETHER.getFluid(PLASMA, 50 * parallel))) {
                total_speed *= 10;
                output = 1 - 0.2 * (Math.random());
            }
            if (pmachine.islasor == 1)
                speed = speed * (0.25 * pmachine.islasor);
            if (speed > 50) {
                output = 0.5 * (Math.random());
            }
            if (speed <= 0.5) {
                return RecipeModifier.DEFAULT_FAILURE;
            }
            recipe.multiplyInputs(parallel);
            recipe.multiplyOutputs((int) (parallel * output));
            recipe.multiplyEUt(eut * parallel);
            recipe.multiplyDuration(1 / total_speed);
            recipe.parallels *= parallel;
            return null;
        }
        return RecipeModifier.DEFAULT_FAILURE;
    }
}
