package io.github.cpearl0.ctnhcore.api.recipe;

import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.lowdragmc.lowdraglib.gui.texture.IGuiTexture;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
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
    private final RecipeLogic[] workers;

    public MultiThreadRecipeLogic(IRecipeLogicMachine machine, int maxParallel) {
        super(machine);
        this.workers = new RecipeLogic[maxParallel];
        for (int i = 0; i < maxParallel; i++) {
            this.workers[i] = new RecipeLogic(machine);
        }
    }

    private TickableSubscription subscription;

    public static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(MultiThreadRecipeLogic.class);


    @Override
    public void onMachineLoad() {
        super.onMachineLoad();
        updateTickSubscription();
    }

    @Override
    public void updateTickSubscription() {
        for (RecipeLogic worker : workers) {
            worker.updateTickSubscription();
        }
    }

    /* ------------------------
       状态聚合
       ------------------------ */

    @Override
    public boolean isWorking() {
        return Arrays.stream(workers).anyMatch(RecipeLogic::isWorking);
    }

    @Override
    public boolean isIdle() {
        return Arrays.stream(workers).allMatch(RecipeLogic::isIdle);
    }

    @Override
    public boolean isWaiting() {
        return Arrays.stream(workers).anyMatch(RecipeLogic::isWaiting);
    }

    @Override
    public boolean isSuspend() {
        return Arrays.stream(workers).allMatch(RecipeLogic::isSuspend);
    }

    @Override
    public boolean isActive() {
        return Arrays.stream(workers).anyMatch(RecipeLogic::isActive);
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
        // 平均进度
        if (workers.length == 0) return 0;
        double avg = Arrays.stream(workers)
                .mapToDouble(w -> w.getDuration() == 0 ? 0 : (double) w.getProgress() / w.getDuration())
                .average()
                .orElse(0.0);
        return (int) (avg * 100);
    }

    @Override
    public int getMaxProgress() {
        return Arrays.stream(workers).mapToInt(RecipeLogic::getMaxProgress).sum();
    }

    @Nullable
    @Override
    public GTRecipe getLastRecipe() {
        if (workers.length == 0) return null;
        return workers[workers.length - 1].getLastRecipe();
    }

    public List<RecipeLogic> getAllWorkers() {
        return Collections.unmodifiableList(Arrays.asList(workers));
    }

    @Override
    public void resetRecipeLogic() {
        for (RecipeLogic worker : workers) {
            worker.resetRecipeLogic();
        }
    }

    @Override
    public void setWorkingEnabled(boolean isWorkingAllowed) {
        setSuspendAfterFinish(!isWorkingAllowed);
        for (RecipeLogic worker : workers) {
            worker.setWorkingEnabled(isWorkingAllowed);
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
        for (RecipeLogic logic : workers) {
            tips.addAll(logic.getFancyTooltip());
        }
        return tips;
    }

    @Override
    public boolean showFancyTooltip() {
        return Arrays.stream(workers).anyMatch(RecipeLogic::showFancyTooltip);
    }

    /* ------------------------
       持久化
       ------------------------ */
    @Override
    public void saveCustomPersistedData(@NotNull CompoundTag tag, boolean forDrop) {
        super.saveCustomPersistedData(tag, forDrop);
        ListTag list = new ListTag();
        for (RecipeLogic logic : workers) {
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
        for (int i = 0; i < Math.min(list.size(), workers.length); i++) {
            CompoundTag child = list.getCompound(i);
            workers[i].loadCustomPersistedData(child);
        }
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }
}
