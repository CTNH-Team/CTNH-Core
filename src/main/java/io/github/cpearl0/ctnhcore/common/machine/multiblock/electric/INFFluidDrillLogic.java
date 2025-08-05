package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.data.worldgen.bedrockfluid.BedrockFluidDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.bedrockfluid.BedrockFluidVeinSavedData;
import com.gregtechceu.gtceu.api.data.worldgen.bedrockfluid.FluidVeinWorldEntry;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.FluidDrillMachine;
import com.gregtechceu.gtceu.common.machine.trait.FluidDrillLogic;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import lombok.Generated;
import lombok.Getter;
import net.minecraft.core.SectionPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

public class INFFluidDrillLogic extends RecipeLogic {

    public static final int MAX_PROGRESS = 20;
    private @Nullable Fluid veinFluid;

    public INFFluidDrillLogic(IRecipeLogicMachine machine) {
        super(machine);
    }


    public INFFluidDrillMachine getMachine() {
        return (INFFluidDrillMachine)super.getMachine();
    }

    public void findAndHandleRecipe() {
        if (getMachine().getLevel() instanceof ServerLevel serverLevel) {
            this.lastRecipe = null;
            BedrockFluidVeinSavedData data = BedrockFluidVeinSavedData.getOrCreate(serverLevel);
            if (this.veinFluid == null) {
                this.veinFluid = data.getFluidInChunk(this.getChunkX(), this.getChunkZ());
                if (this.veinFluid == null) {
                    if (this.subscription != null) {
                        this.subscription.unsubscribe();
                        this.subscription = null;
                    }

                    return;
                }
            }

            GTRecipe match = this.getFluidDrillRecipe();
            if (match != null && RecipeHelper.matchRecipe(this.machine, match).isSuccess() && RecipeHelper.matchTickRecipe(this.machine, match).isSuccess()) {
                this.setupRecipe(match);
            }
        }

    }

    private @Nullable GTRecipe getFluidDrillRecipe() {

        if (getMachine().getLevel() instanceof ServerLevel serverLevel) {
            if (this.veinFluid != null) {
                BedrockFluidVeinSavedData data = BedrockFluidVeinSavedData.getOrCreate(serverLevel);
                GTRecipe recipe = GTRecipeBuilder.ofRaw().duration(20).EUt((long)GTValues.VA[this.getMachine().getEnergyTier()]).outputFluids(new FluidStack(this.veinFluid, this.getFluidToProduce(data.getFluidVeinWorldEntry(this.getChunkX(), this.getChunkZ())))).buildRawRecipe();
                if (RecipeHelper.matchRecipe(this.getMachine(), recipe).isSuccess() && RecipeHelper.matchTickRecipe(this.getMachine(), recipe).isSuccess()) {
                    return recipe;
                }
            }
        }

        return null;
    }

    public int getFluidToProduce() {
        if (getMachine().getLevel() instanceof ServerLevel serverLevel) {
            if (this.veinFluid != null) {
                BedrockFluidVeinSavedData data = BedrockFluidVeinSavedData.getOrCreate(serverLevel);
                return this.getFluidToProduce(data.getFluidVeinWorldEntry(this.getChunkX(), this.getChunkZ()));
            }
        }

        return 0;
    }

    private int getFluidToProduce(FluidVeinWorldEntry entry) {
        BedrockFluidDefinition definition = entry.getDefinition();
        if (definition != null) {
            int depletedYield = definition.getDepletedYield();
            int regularYield = entry.getFluidYield();
            int remainingOperations = entry.getOperationsRemaining();
            int produced = Math.max(depletedYield, regularYield * remainingOperations / 100000);
            produced *= INFFluidDrillMachine.getRigMultiplier(this.getMachine().getTier());
            if (this.isOverclocked()) {
                produced = produced * 3 / 2;
            }

            return produced;
        } else {
            return 0;
        }
    }

    public void onRecipeFinish() {
        this.machine.afterWorking();
        if (this.lastRecipe != null) {
//            this.lastRecipe.postWorking(this.machine);
            RecipeHelper.handleRecipeIO(this.machine, this.lastRecipe, IO.OUT, this.chanceCaches);
        }

        this.depleteVein();
        GTRecipe match = this.getFluidDrillRecipe();
        if (match != null && RecipeHelper.matchRecipe(this.machine, match).isSuccess() && RecipeHelper.matchTickRecipe(this.machine, match).isSuccess()) {
            this.setupRecipe(match);
        } else {
            if (this.suspendAfterFinish) {
                this.setStatus(Status.SUSPEND);
                this.suspendAfterFinish = false;
            } else {
                this.setStatus(Status.IDLE);
            }

            this.progress = 0;
            this.duration = 0;
        }
    }

    protected void depleteVein() {
        if (getMachine().getLevel() instanceof ServerLevel serverLevel) {
            int chance = INFFluidDrillMachine.getDepletionChance(this.getMachine().getTier());
            BedrockFluidVeinSavedData data = BedrockFluidVeinSavedData.getOrCreate(serverLevel);
            if (1==2) {
                data.depleteVein(this.getChunkX(), this.getChunkZ(), 0, false);
            }
        }

    }

    protected boolean isOverclocked() {
        return this.getMachine().getEnergyTier() > this.getMachine().getTier();
    }

    private int getChunkX() {
        return SectionPos.blockToSectionCoord(this.getMachine().getPos().getX());
    }

    private int getChunkZ() {
        return SectionPos.blockToSectionCoord(this.getMachine().getPos().getZ());
    }

    @Generated
    public @Nullable Fluid getVeinFluid() {
        return this.veinFluid;
    }
}
