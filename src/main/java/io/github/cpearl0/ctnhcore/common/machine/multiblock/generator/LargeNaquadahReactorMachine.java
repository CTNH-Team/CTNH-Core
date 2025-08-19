package io.github.cpearl0.ctnhcore.common.machine.multiblock.generator;

import com.gregtechceu.gtceu.api.capability.recipe.EURecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IExplosionMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.machine.multiblock.part.FluidHatchPartMachine;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import io.github.cpearl0.ctnhcore.registry.CTNHMaterials;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import it.unimi.dsi.fastutil.longs.Long2ObjectMaps;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LargeNaquadahReactorMachine extends WorkableElectricMultiblockMachine implements IExplosionMachine {
    private ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(LargeNaquadahReactorMachine.class, WorkableElectricMultiblockMachine.MANAGED_FIELD_HOLDER);
    public LargeNaquadahReactorMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }
    private Map<Fluid, Integer> activeFluid = Map.of(
            GTMaterials.Caesium.getFluid(), 2,
            GTMaterials.Uranium235.getFluid(), 3,
            GTMaterials.Naquadah.getFluid(), 4
            );
    private Map<Fluid, Integer> activeFluidCost = Map.of(
            GTMaterials.Caesium.getFluid(), 180,
            GTMaterials.Uranium235.getFluid(), 180,
            GTMaterials.Naquadah.getFluid(), 20
            );
    private List<Fluid> fuelFluids = List.of(
            CTNHMaterials.ThoriumBasedLiquidFuelExcited.getFluid(),
            CTNHMaterials.UraniumBasedLiquidFuelExcited.getFluid(),
            CTNHMaterials.PlutoniumBasedLiquidFuelExcited.getFluid()
            );
    private Set<FluidHatchPartMachine> hatchPartMachines = new HashSet<>();

    @Persisted
    private boolean hasAir = false;

    @Persisted
    private boolean hasCool = false;

    @Persisted
    private int activeFluidPower = 1;
    private Fluid lockFluid = null;
    public static ModifierFunction modifyRecipe(MetaMachine machine, GTRecipe recipe) {
        if (recipe.recipeType != CTNHRecipeTypes.LARGE_NAQUADAH_REACTOR_RECIPES) return ModifierFunction.NULL;
        if (machine instanceof LargeNaquadahReactorMachine lmachine) {
            return (modifyRecipe) -> {
                var duration = modifyRecipe.duration;
                lmachine.checkHatch(lmachine, duration);
                var copyRecipe = modifyRecipe.copy();
                if (!lmachine.hasAir) {
                    copyRecipe.tickOutputs.clear();
                    copyRecipe.outputs.clear();
                    return copyRecipe;
                }
                double eut = copyRecipe.getOutputEUt().getTotalEU();
                if (lmachine.hasCool) {
                    eut = eut * 1.5;
                }
                eut *= lmachine.activeFluidPower;
                copyRecipe.tickOutputs.put(EURecipeCapability.CAP, List.of(new Content(eut, 1, 1, 0)));
                return copyRecipe;
            };
        }
        return ModifierFunction.NULL;
    }

    private void checkHatch(LargeNaquadahReactorMachine machine, int duration) {
        machine.hasCool = false;
        machine.hasAir = false;
        machine.activeFluidPower = 1;
        for (var hatch : machine.hatchPartMachines) {
            var tank = hatch.tank;
            var io = tank.getHandlerIO();
            if (io == IO.IN || io == IO.BOTH) {
                for (var i : tank.getStorages()) {
                    var fluid = i.getFluid();
                    if (i.getFluid() == FluidStack.EMPTY) continue;
                    checkLockFluid(machine, fluid);
                    active(machine, fluid, duration);
                    if (cool(fluid, duration)) machine.hasCool = true;
                    if (air(fluid, duration)) machine.hasAir = true;
                }
            }
            if (machine.hasCool && machine.hasAir && machine.activeFluidPower != 1) {
                return;
            }
        }
    }

    private void checkLockFluid(LargeNaquadahReactorMachine machine, FluidStack fluid) {
        if (machine.fuelFluids.contains(fluid.getFluid())) {
            if (machine.lockFluid == null) {
                machine.lockFluid = fluid.getFluid();
            } else if (machine.lockFluid != fluid.getFluid()) {
                machine.doExplosion((4 * 32));
            }
        }
    }


    //////////////////////////////////////
    //******     RECIPE LOGIC    *******//
    //////////////////////////////////////

    private boolean air(FluidStack fluid, int duration) {
        if (fluid.getFluid().isSame(GTMaterials.LiquidAir.getFluid())) {
            var airAmount = 2400 / 20 * duration;
            if (fluid.getAmount() >= airAmount) {
                fluid.setAmount(fluid.getAmount() - airAmount);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private void active(LargeNaquadahReactorMachine machine, FluidStack fluid, int duration) {
        if (activeFluid.containsKey(fluid.getFluid())) {
            var activeFluidCostI = activeFluidCost.get(fluid.getFluid()) / 20 * duration;
            var activeFluidPower = activeFluid.get(fluid.getFluid());
            if (machine.activeFluidPower <= activeFluidPower && fluid.getAmount() >= activeFluidCostI) {
                machine.activeFluidPower = activeFluidPower;
                fluid.setAmount(fluid.getAmount() - activeFluidCostI);
            }
        }
    }

    private boolean cool(FluidStack fluid, int duration) {
        if (fluid.getFluid().isSame(GTMaterials.PCBCoolant.getFluid())) {
            var coldAmount = 1000 / 20 * duration;
            if (fluid.getAmount() >= coldAmount) {
                fluid.setAmount(fluid.getAmount() - coldAmount);
                return true;
            }
        }
        return false;
    }

    //////////////////////////////////////
    //***    Multiblock LifeCycle    ***//
    //////////////////////////////////////

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        var matchContext = getMultiblockState().getMatchContext();
        var ioMap = matchContext.getOrCreate("ioMap", Long2ObjectMaps::emptyMap);
        // Cache the result of getParts() to prevent repetitive calls
        var parts = getParts();
        for (var part : parts) {
            var io = ioMap.getOrDefault(part.self().getPos().asLong(), IO.BOTH);
            if (io == IO.NONE) continue;
            if (part instanceof FluidHatchPartMachine fluidHatchPartMachine) {
                hatchPartMachines.add(fluidHatchPartMachine);
            }
        }
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
        hatchPartMachines = new HashSet<>();
        lockFluid = null;
    }

    @Override
    public boolean alwaysTryModifyRecipe() {
        return true;
    }
    public int getFinalPowerRate() {
        var activeFluidPower = this.activeFluidPower;
        if (hasCool) {
            activeFluidPower = (int) (activeFluidPower * 1.5);
        }
        return activeFluidPower;
    }

    //////////////////////////////////////
    //******       NBT SAVE      *******//
    //////////////////////////////////////

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }
}
