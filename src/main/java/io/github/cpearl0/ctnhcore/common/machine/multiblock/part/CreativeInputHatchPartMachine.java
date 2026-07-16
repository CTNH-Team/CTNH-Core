package io.github.cpearl0.ctnhcore.common.machine.multiblock.part;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.blockentity.IPaintable;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.fancy.ConfiguratorPanel;
import com.gregtechceu.gtceu.api.gui.widget.PhantomFluidWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.fancyconfigurator.CircuitFancyConfigurator;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IDistinctPart;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.fluid.FluidIngredient;
import com.gregtechceu.gtceu.common.item.IntCircuitBehaviour;

import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraftforge.fluids.FluidStack;

import lombok.Getter;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CreativeInputHatchPartMachine extends TieredIOPartMachine implements IDistinctPart, IPaintable {

    private final int SLOT_COUNT = 25;

    @Persisted
    public final NotifiableFluidTank tank;
    private final int slots;

    @Persisted
    @Getter
    boolean workingEnabled = true;

    @Getter
    @Persisted
    protected final NotifiableItemStackHandler circuitInventory;

    public CreativeInputHatchPartMachine(IMachineBlockEntity holder) {
        super(holder, GTValues.MAX, IO.IN);
        this.slots = SLOT_COUNT;
        this.tank = new InfinityFluidTank(this, SLOT_COUNT, Integer.MAX_VALUE, IO.IN);
        this.circuitInventory = new NotifiableItemStackHandler(this, 1, IO.IN, IO.NONE)
                .setFilter(IntCircuitBehaviour::isIntegratedCircuit).shouldSearchContent(false);
    }

    //////////////////////////////////////
    // ***** Initialization ******//
    //////////////////////////////////////
    @Override
    public void onLoad() {
        super.onLoad();
        getHandlerList().setColor(getPaintingColor());
    }

    @Override
    public void setWorkingEnabled(boolean workingEnabled) {
        this.workingEnabled = workingEnabled;
        tank.notifyListeners();
    }

    @Override
    public void onPaintingColorChanged(int color) {
        getHandlerList().setColor(color, true);
    }

    @Override
    public int tintColor(int index) {
        if (index == 9) return getRealColor();
        return -1;
    }

    protected void clearAll() {
        for (int i = 0; i < SLOT_COUNT; i++) {
            if (!this.tank.getFluidInTank(i).isEmpty()) {
                this.tank.setFluidInTank(i, FluidStack.EMPTY);
            }
        }
    }

    //////////////////////////////////////
    // ********** GUI ***********//
    //////////////////////////////////////

    @Override
    public void attachConfigurators(ConfiguratorPanel configuratorPanel) {
        IDistinctPart.super.attachConfigurators(configuratorPanel);
        configuratorPanel.attachConfigurators(new CircuitFancyConfigurator(circuitInventory.storage));
    }

    @Override
    public Widget createUIWidget() {
        int rowSize = (int) Math.sqrt(slots);
        int colSize = rowSize;
        if (slots == 8) {
            rowSize = 4;
            colSize = 2;
        }

        var group = new WidgetGroup(0, 0, 18 * rowSize + 16, 18 * colSize + 16);
        var container = new WidgetGroup(4, 4, 18 * rowSize + 8, 18 * colSize + 8);

        int index = 0;
        for (int y = 0; y < colSize; y++) {
            for (int x = 0; x < rowSize; x++) {
                int finalIndex = index++;
                container.addWidget(new PhantomFluidWidget(
                        this.tank, finalIndex,
                        4 + x * 18, 4 + y * 18, 18, 18,
                        () -> this.tank.getFluidInTank(finalIndex),
                        (fluid -> {
                            setFluid(finalIndex, fluid);
                        })).setShowAmount(false).setBackground(GuiTextures.FLUID_SLOT));
            }
        }

        container.setBackground(GuiTextures.BACKGROUND_INVERSE);
        group.addWidget(container);

        return group;
    }

    private void setFluid(int index, FluidStack fs) {
        var newFluid = fs.copy();
        if (!newFluid.isEmpty()) {
            newFluid.setAmount(Integer.MAX_VALUE);
        }

        this.tank.setFluidInTank(index, newFluid);
    }

    @Override
    public boolean isDistinct() {
        return getHandlerList().isDistinct();
    }

    @Override
    public void setDistinct(boolean isDistinct) {
        getHandlerList().setDistinctAndNotify(isDistinct);
    }

    private class InfinityFluidTank extends NotifiableFluidTank {

        public InfinityFluidTank(MetaMachine machine, int slots, int capacity, IO io) {
            super(machine, slots, capacity, io);
        }

        @Override
        public List<FluidIngredient> handleRecipeInner(IO io, GTRecipe recipe, List<FluidIngredient> left,
                                                       boolean simulate) {
            if (isWorkingEnabled()) {
                return super.handleRecipeInner(io, recipe, left, true);
            } else {
                return left;
            }
        }
    }
}
