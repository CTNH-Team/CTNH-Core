package io.github.cpearl0.ctnhcore.api.recipe;

import com.gregtechceu.gtceu.api.capability.recipe.EURecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.feature.IOverclockMachine;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.ActionResult;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreadRecipeLogic extends RecipeLogic {

    public static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(ThreadRecipeLogic.class);

    @Persisted
    @DescSynced
    boolean enabled;

    @Persisted
    @DescSynced
    boolean threadProtect;

    @Persisted
    @DescSynced
    boolean lockRecipe;

    @Persisted
    @DescSynced
    int overclockTier = -1;

    boolean modifying;

    public ThreadRecipeLogic(IRecipeLogicMachine machine) {
        super(machine);
        enabled = true;
        threadProtect = false;
        lockRecipe = false;
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Override
    public boolean checkMatchedRecipeAvailable(GTRecipe match) {

        modifying = true;
        var modified = machine.fullModifyRecipe(match);
        modifying = false;

        if (modified != null) {
            var recipeMatch = checkRecipe(modified);
            if (recipeMatch.isSuccess()) {
                setupRecipe(modified);
            }
            if (lastRecipe != null && getStatus() == Status.WORKING) {
                lastOriginRecipe = match;
                lastFailedMatches = null;
                return true;
            }
        }
        return false;
    }

    @Override
    protected ActionResult checkRecipe(GTRecipe recipe) {
        if(threadProtect
                && machine.getRecipeLogic() instanceof MultiThreadRecipeLogic multiThreadRecipeLogic
                && multiThreadRecipeLogic.isRunningRecipe(recipe, this))
            return ActionResult.FAIL_NO_REASON;
        return super.checkRecipe(recipe);
    }

    @Override
    public void onRecipeFinish() {
        modifying = true;
        super.onRecipeFinish();
        modifying = false;
    }

    @Override
    public void findAndHandleRecipe() {
        lastFailedMatches = null;
        // try to execute last recipe if possible
        if (!recipeDirty && lastRecipe != null && checkRecipe(lastRecipe).isSuccess()) {
            GTRecipe recipe = lastRecipe;
            lastRecipe = null;
            //lastOriginRecipe = null;
            setupRecipe(recipe);
        } else if(!lockRecipe) { // try to find and handle a new recipe if not locked
            //lastRecipe = null;
            //lastOriginRecipe = null;
            handleSearchingRecipes(searchRecipe());
        }
        recipeDirty = false;
    }

    @Override
    public void setStatus(Status status) {
        var lastStatus = getStatus();
        super.setStatus(status);
        if(lastStatus != status){
            machine.notifyStatusChanged(lastStatus, status);
        }
    }

    @Override
    public void resetRecipeLogic() {
        super.resetRecipeLogic();
        enabled = true;
        threadProtect = false;
        lockRecipe = false;

    }
}
