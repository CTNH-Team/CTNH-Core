package io.github.cpearl0.ctnhcore.common.machine.multiblock.kinetic;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.mo_guang.ctpp.common.data.recipe.builder.CTPPRecipeHelper;
import com.mo_guang.ctpp.common.machine.multiblock.KineticWorkableMultiblockMachine;
import com.moguang.ctnhbio.api.machine.trait.NotifiableEntityContainer;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.api.recipe.crossparalell.MergedGTRecipe;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class MeadowMachine extends KineticWorkableMultiblockMachine {

    public NotifiableEntityContainer entityContainer;

    public MeadowMachine(IMachineBlockEntity holder) {
        super(holder);
        entityContainer = new NotifiableEntityContainer(this, getAABB(), IO.IN);
    }

    public AABB getAABB() {
        final Direction b = getFrontFacing().getOpposite();
        final Direction l = b.getCounterClockWise();
        final Direction u = Direction.UP;

        return new AABB(
                getPos().relative(b,0).relative(l,5).relative(u,0),
                getPos().relative(b,10).relative(l,-5).relative(u,6)
        );
    }

    @Override
    public MeadowRecipeLogic getRecipeLogic() {
        return (MeadowRecipeLogic)super.getRecipeLogic();
    }

    @Override
    protected MeadowRecipeLogic createRecipeLogic(Object... args) {
        return new MeadowRecipeLogic(this);
    }

    @Override
    public boolean alwaysTryModifyRecipe() {
        return false;
    }

    @Override
    public GTRecipe fullModifyRecipe(GTRecipe recipe) {
        var newRecipe = super.fullModifyRecipe(recipe);
        if(newRecipe != null)
            availableStress -= CTPPRecipeHelper.getInputStress(newRecipe);
        return newRecipe;
    }

    public class MeadowRecipeLogic extends KineticRecipeLogic{

        public MergedGTRecipe mergedRecipe = new MergedGTRecipe(getRecipeType(),
                getRecipeType().getCategory(),
                CTNHCore.id(getRecipeType().registryName.getPath() + "/merged/" + this.hashCode())
        );

        public MeadowRecipeLogic(IRecipeLogicMachine machine) {
            super(machine);
        }

        @Override
        public void findAndHandleRecipe() {
            super.findAndHandleRecipe();
            markLastRecipeDirty();
        }

        @Override
        protected void handleSearchingRecipes(@NotNull Iterator<GTRecipe> matches) {
            mergedRecipe.clear();
            if(machine instanceof KineticWorkableMultiblockMachine kmachine)
                kmachine.resetAvailableStress();
            while (matches.hasNext()) {
                GTRecipe match = matches.next();
                if (match == null) continue;

                // If a new recipe was found, merge found recipe.
                checkMatchedRecipeAvailable(match);
            }
            if(mergedRecipe.isAvailable())
                setupRecipe(mergedRecipe);

        }

        @Override
        public boolean checkMatchedRecipeAvailable(GTRecipe match) {
            var modified = machine.fullModifyRecipe(match);
            if (modified != null) {
                var recipeMatch = checkRecipe(modified);
                if (recipeMatch.isSuccess()) {
                    mergedRecipe.add(modified);
                    lastOriginRecipe = match;
                    return true;
                }
            }
            return false;
        }
    }
}
