package io.github.cpearl0.ctnhcore.common.machine.trait;

import com.gregtechceu.gtceu.api.capability.IOpticalComputationProvider;
import com.gregtechceu.gtceu.api.capability.IOpticalComputationReceiver;
import com.gregtechceu.gtceu.api.capability.forge.GTCapability;
import com.gregtechceu.gtceu.api.capability.recipe.CWURecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableComputationContainer;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableRecipeHandlerTrait;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.utils.GTUtil;
import lombok.Getter;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SimpleComputationContainer extends NotifiableComputationContainer {
    //Notifiable
    protected long lastTimeStamp;
    private int currentOutputCWU = 0;
    private int lastOutputCWU = 0;

    @Nullable
    IOpticalComputationProvider computationProvider;

    public SimpleComputationContainer(MetaMachine machine) {
        super(machine, IO.IN, false);
    }


    @Override
    public int requestCWUt(int cwut, boolean simulate, @NotNull Collection<IOpticalComputationProvider> seen) {
        var latestTimeStamp = getMachine().getOffsetTimer();
        if (lastTimeStamp < latestTimeStamp) {
            lastOutputCWU = currentOutputCWU;
            currentOutputCWU = 0;
            lastTimeStamp = latestTimeStamp;
        }
        seen.add(this);
        var provider = getComputationProvider();
        return provider != null ? provider.requestCWUt(cwut, simulate, seen) : 0;
    }

    @Override
    public int getMaxCWUt(@NotNull Collection<IOpticalComputationProvider> seen) {
        seen.add(this);
        var provider = getComputationProvider();
        return provider != null ? provider.getMaxCWUt(seen) : 0;
    }

    @Override
    public boolean canBridge(@NotNull Collection<IOpticalComputationProvider> seen) {
        seen.add(this);
        return true;
    }

    @Override
    public IOpticalComputationProvider getComputationProvider() {
        if(computationProvider !=null)return computationProvider;
        updateComputationProvider();
        return computationProvider;
    }

    //GTM你干的好事GTM你干的好事GTM你干的好事GTM你干的好事GTM你干的好事GTM你干的好事GTM你干的好事GTM你干的好事GTM你干的好事GTM你干的好事GTM你干的好事
    @Override
    public List<Integer> handleRecipeInner(IO io, GTRecipe recipe, List<Integer> left,
                                           boolean simulate) {
        IOpticalComputationProvider provider = getComputationProvider();
        if (provider == null) return left;

        int sum = left.stream().mapToInt(Integer::intValue).sum();
        sum -= requestCWUt(sum, simulate);
        return sum <= 0 ? null : Collections.singletonList(sum);
    }

    @Override
    public @NotNull List<Object> getContents() {
        return List.of(lastOutputCWU);
    }

    @Override
    public double getTotalContentAmount() {
        return lastOutputCWU;
    }

    public void updateComputationProvider()
    {
        /*这里有隔空连算力的bug，先不急（*/
        for (Direction direction : GTUtil.DIRECTIONS) {
            BlockEntity blockEntity = machine.getLevel().getBlockEntity(machine.getPos().relative(direction));
            if (blockEntity == null) continue;
            computationProvider = blockEntity.getCapability(GTCapability.CAPABILITY_COMPUTATION_PROVIDER, direction.getOpposite()).orElse(null);
            if(computationProvider !=null) return;
        }
    }
}
