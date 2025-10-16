package io.github.cpearl0.ctnhcore.api.recipe;

import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.lowdragmc.lowdraglib.gui.texture.IGuiTexture;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import lombok.Getter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.*;

public class MultiThreadRecipeLogic extends RecipeLogic {
    //private final int maxParallel;
    @Persisted
    @DescSynced
    private final ThreadRecipeLogic[] threads;

    @Getter
    @Persisted
    @DescSynced
    boolean workingAllowed;

    public MultiThreadRecipeLogic(IRecipeLogicMachine machine, int maxParallel) {
        super(machine);
        this.threads = new ThreadRecipeLogic[maxParallel];
        for (int i = 0; i < maxParallel; i++) {
            this.threads[i] = new ThreadRecipeLogic(machine);
        }
    }

    private TickableSubscription subscription;

    public static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(MultiThreadRecipeLogic.class);

    public boolean isRunningRecipe(GTRecipe recipe, @Nullable RecipeLogic except){
        for (RecipeLogic worker : threads) {
            if(worker.getLastRecipe() == recipe && worker != except)
                return true;
        }
        return false;
    }

    @Override
    public void onMachineLoad() {
        super.onMachineLoad();
        updateTickSubscription();
    }

    @Override
    public void updateTickSubscription() {
        for (RecipeLogic worker : threads) {
            worker.updateTickSubscription();
        }
    }

    /* ------------------------
       状态聚合
       ------------------------ */

    @Override
    public boolean isWorking() {
        return Arrays.stream(threads).anyMatch(RecipeLogic::isWorking);
    }

    @Override
    public boolean isIdle() {
        return Arrays.stream(threads).allMatch(RecipeLogic::isIdle);
    }

    @Override
    public boolean isWaiting() {
        return Arrays.stream(threads).anyMatch(RecipeLogic::isWaiting);
    }

    @Override
    public boolean isSuspend() {
        return Arrays.stream(threads).allMatch(RecipeLogic::isSuspend);
    }

    @Override
    public boolean isActive() {
        return Arrays.stream(threads).anyMatch(RecipeLogic::isActive);
    }

    @Override
    public Status getStatus() {
        if (isWorking()) return Status.WORKING;
        if (isWaiting()) return Status.WAITING;
        if (isIdle()) return Status.IDLE;
        return Status.SUSPEND;
    }

    @Override
    public int getProgress() {
        return Arrays.stream(threads).mapToInt(RecipeLogic::getProgress).sum();
    }

    @Override
    public int getMaxProgress() {
        return Arrays.stream(threads).mapToInt(RecipeLogic::getMaxProgress).sum();
    }

    @Nullable
    @Override
    public GTRecipe getLastRecipe() {
        if (threads.length == 0) return null;
        return threads[threads.length - 1].getLastRecipe();
    }

    public List<RecipeLogic> getAllWorkers() {
        return Collections.unmodifiableList(Arrays.asList(threads));
    }

    @Override
    public void resetRecipeLogic() {
        for (RecipeLogic worker : threads) {
            worker.resetRecipeLogic();
        }
    }

    @Override
    public void setWorkingEnabled(boolean isWorkingAllowed) {
        workingAllowed = isWorkingAllowed;
        for (ThreadRecipeLogic worker : threads) {
            worker.setWorkingEnabled(isWorkingAllowed && worker.enabled);
        }
    }

    /* ------------------------
       FancyTooltip 聚合
       ------------------------ */
    @Override
    public IGuiTexture getFancyTooltipIcon() {
        if (isWaiting()) return GuiTextures.INSUFFICIENT_INPUT;
        return IGuiTexture.EMPTY;
    }

    @Override
    public List<Component> getFancyTooltip() {
        List<Component> tips = new ArrayList<>();
        for (RecipeLogic logic : threads) {
            tips.addAll(logic.getFancyTooltip());
        }
        return tips;
    }

    @Override
    public boolean showFancyTooltip() {
        return Arrays.stream(threads).anyMatch(RecipeLogic::showFancyTooltip);
    }

    /* ------------------------
       持久化
       ------------------------ */
    @Override
    public void saveCustomPersistedData(@NotNull CompoundTag tag, boolean forDrop) {
        super.saveCustomPersistedData(tag, forDrop);
        ListTag list = new ListTag();
        for (RecipeLogic logic : threads) {
            CompoundTag child = new CompoundTag();
            logic.saveCustomPersistedData(child, forDrop);
            list.add(child);
        }
        tag.put("workers", list);
    }

    @Override
    public void loadCustomPersistedData(@NotNull CompoundTag tag) {
        super.loadCustomPersistedData(tag);
        ListTag list = tag.getList("workers", CompoundTag.TAG_COMPOUND);
        for (int i = 0; i < Math.min(list.size(), threads.length); i++) {
            CompoundTag child = list.getCompound(i);
            threads[i].loadCustomPersistedData(child);
        }
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }
}
