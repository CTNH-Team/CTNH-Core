package io.github.cpearl0.ctnhcore.common.machine.simple;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.ActionResult;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.machines.GTMachineUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;

import com.ctnh.ctnhastral.common.oxygen.OxygenAreaSource;
import com.ctnhlang.CN;
import com.ctnhlang.EN;
import earth.terrarium.adastra.api.systems.OxygenApi;
import earth.terrarium.adastra.api.systems.TemperatureApi;
import earth.terrarium.adastra.common.utils.floodfill.FloodFill3D;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class OxygenEnricherMachine extends SimpleTieredMachine implements OxygenAreaSource {

    @CN("氧气供应不足")
    @EN("Insufficient oxygen supply")
    public static Lang ctnhMachineOxygenEnricherNoInput;

    private static final Component NO_OXYGEN_INPUT = ctnhMachineOxygenEnricherNoInput.translate();
    private static final short HABITABLE_TEMPERATURE = 22;

    private final Set<BlockPos> distributedBlocks = new HashSet<>();
    private TickableSubscription oxygenDistributionSubscription;

    public OxygenEnricherMachine(IMachineBlockEntity holder, int tier, Object... args) {
        super(holder, tier, GTMachineUtils.defaultTankSizeFunction, args);
    }

    @Override
    protected NotifiableFluidTank createImportFluidHandler(Object... args) {
        return super.createImportFluidHandler(args)
                .setFilter(fluid -> fluid.isFluidEqual(GTMaterials.Oxygen.getFluid(1)));
    }

    protected RecipeLogic createRecipeLogic() {
        return new OxygenEnricherRecipeLogic(this);
    }

    @Override
    public BlockPos getOxygenSourcePos() {
        return getPos();
    }

    @Override
    public int getOxygenRange() {
        return 12 + getTier() * 4;
    }

    @Override
    public boolean isOxygenSourceActive(ServerLevel level) {
        return isActive() && isWorkingEnabled();
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (!isRemote()) {
            oxygenDistributionSubscription = subscribeServerTick(oxygenDistributionSubscription,
                    this::tickOxygenDistribution);
        }
    }

    @Override
    public void onUnload() {
        if (!isRemote()) {
            clearDistributedBlocks();
            unsubscribe(oxygenDistributionSubscription);
            oxygenDistributionSubscription = null;
        }
        super.onUnload();
    }

    @Override
    public void onMachineRemoved() {
        if (!isRemote()) {
            clearDistributedBlocks();
        }
        super.onMachineRemoved();
    }

    private void tickOxygenDistribution() {
        if (!(getLevel() instanceof ServerLevel serverLevel) || getOffsetTimer() % 20 != 0) {
            return;
        }
        if (!getRecipeLogic().isWorking() || !isWorkingEnabled()) {
            clearDistributedBlocks();
            return;
        }
        updateDistributedBlocks(serverLevel, collectDistributedBlocks(serverLevel));
    }

    private Set<BlockPos> collectDistributedBlocks(ServerLevel level) {
        Set<BlockPos> best = Collections.emptySet();
        for (Direction direction : Direction.values()) {
            BlockPos seed = getPos().relative(direction);
            if (!isPassableSpace(level, seed)) {
                continue;
            }
            Set<BlockPos> distributed = new HashSet<>(
                    FloodFill3D.run(level, seed, getOxygenBlockLimit(), FloodFill3D.TEST_FULL_SEAL, true));
            distributed.removeIf(pos -> !getPos().closerThan(pos, getOxygenRange() + 1));
            if (distributed.size() > best.size()) {
                best = distributed;
            }
        }
        return best;
    }

    private int getOxygenBlockLimit() {
        int range = getOxygenRange();
        return Math.min(8192, Math.max(256, range * range * 4));
    }

    private boolean isPassableSpace(ServerLevel level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        return state.isAir() || state.canBeReplaced() || state.getCollisionShape(level, pos).isEmpty();
    }

    private void updateDistributedBlocks(ServerLevel level, Set<BlockPos> nextBlocks) {
        if (distributedBlocks.equals(nextBlocks)) {
            return;
        }

        Set<BlockPos> removed = new HashSet<>(distributedBlocks);
        removed.removeAll(nextBlocks);
        if (!removed.isEmpty()) {
            OxygenApi.API.removeOxygen(level, removed);
            TemperatureApi.API.removeTemperature(level, removed);
        }

        Set<BlockPos> added = new HashSet<>(nextBlocks);
        added.removeAll(distributedBlocks);
        if (!added.isEmpty()) {
            OxygenApi.API.setOxygen(level, added, true);
            TemperatureApi.API.setTemperature(level, added, HABITABLE_TEMPERATURE);
        }

        distributedBlocks.clear();
        distributedBlocks.addAll(nextBlocks);
    }

    private void clearDistributedBlocks() {
        if (distributedBlocks.isEmpty() || getLevel() == null) {
            return;
        }
        OxygenApi.API.removeOxygen(getLevel(), distributedBlocks);
        TemperatureApi.API.removeTemperature(getLevel(), distributedBlocks);
        distributedBlocks.clear();
    }

    private static final class OxygenEnricherRecipeLogic extends RecipeLogic {

        private final OxygenEnricherMachine machine;

        private OxygenEnricherRecipeLogic(OxygenEnricherMachine machine) {
            super(machine);
            this.machine = machine;
        }

        @Override
        public void findAndHandleRecipe() {
            super.findAndHandleRecipe();
            if (lastRecipe == null && getFailureReasonsMap().isEmpty()) {
                setWaiting(NO_OXYGEN_INPUT);
            }
        }

        @Override
        protected ActionResult matchRecipe(GTRecipe recipe) {
            if (machine.importFluids.getFluidInTank(0).isEmpty() ||
                    !machine.importFluids.getFluidInTank(0).isFluidEqual(GTMaterials.Oxygen.getFluid(1))) {
                return ActionResult.fail(NO_OXYGEN_INPUT, null, IO.IN);
            }
            return super.matchRecipe(recipe);
        }
    }
}
