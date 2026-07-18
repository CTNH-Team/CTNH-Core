package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import io.github.cpearl0.ctnhcore.api.machine.feature.IDigitalMiner;
import io.github.cpearl0.ctnhcore.api.recipe.DigitalMinerLogic;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeHandler;
import com.gregtechceu.gtceu.api.cover.filter.ItemFilter;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.widget.SlotWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.WorkLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.content.ContentListMap;
import com.gregtechceu.gtceu.api.recipe.handler.RecipeHandlerGroup;
import com.gregtechceu.gtceu.api.recipe.ingredient.fluid.FluidIngredient;
import com.gregtechceu.gtceu.api.transfer.item.CustomItemStackHandler;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;

import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import java.util.ArrayList;
import java.util.List;

public class LargeDigitalMinerMachine extends WorkableElectricMultiblockMachine implements IDigitalMiner {

    private final int machineTier;
    private final int radius;
    private final int fortune;
    private final int speed;
    private final int drillingFluidPerTick;

    @Persisted
    private final CustomItemStackHandler filterInventory = createFilterInventory();
    private ItemFilter itemFilter;
    private RecipeHandlerGroup handlerGroup = RecipeHandlerGroup.EMPTY;

    public LargeDigitalMinerMachine(IMachineBlockEntity holder) {
        this(holder, GTValues.ZPM, 64 / GTValues.ZPM, 2 * GTValues.ZPM - 5, 1, 7);
    }

    public LargeDigitalMinerMachine(IMachineBlockEntity holder, int tier, int speed, int maximumChunkDiameter,
                                    int fortune, int drillingFluidPerTick) {
        super(holder, tier, speed, maximumChunkDiameter, fortune, drillingFluidPerTick);
        this.machineTier = tier;
        this.radius = maximumChunkDiameter * 8;
        this.fortune = fortune;
        this.speed = speed;
        this.drillingFluidPerTick = drillingFluidPerTick;
    }

    @Override
    protected WorkLogic createWorkLogic(Object... args) {
        int maximumChunkDiameter = (int) args[2];
        return new DigitalMinerLogic(this, maximumChunkDiameter * 8,
                getLevel() == null ? -64 : getLevel().getMinBuildHeight(),
                getLevel() == null ? 320 : getLevel().getMaxBuildHeight(), 0, itemFilter,
                (int) args[3], (int) args[1]);
    }

    @Override
    public DigitalMinerLogic getWorkLogic() {
        return (DigitalMinerLogic) workLogic;
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        handlerGroup = new RecipeHandlerGroup();
        getParts().forEach(part -> part.getRecipeHandlers().forEach(handlerGroup::addHandlerList));
        updateFilter();
    }

    @Override
    public void onStructureInvalid() {
        getWorkLogic().ensureChunkUnforced();
        handlerGroup = RecipeHandlerGroup.EMPTY;
        super.onStructureInvalid();
    }

    @Override
    public boolean drainInput(boolean simulate) {
        if (energyContainer.getEnergyStored() < GTValues.VA[machineTier]) return false;
        GTRecipe fluidCost = drillingFluidCost();
        if (!RecipeHelper.matchRecipe(handlerGroup, fluidCost).isSuccess()) return false;
        if (!simulate) {
            energyContainer.removeEnergy(GTValues.VA[machineTier]);
            return RecipeHelper.handleRecipeIO(handlerGroup, fluidCost, IO.IN).isSuccess();
        }
        return true;
    }

    @Override
    public List<? extends IRecipeHandler<?>> getOutputHandlers() {
        return handlerGroup.getOutputHandlerMap().getOrDefault(
                com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability.CAP, List.of());
    }

    @Override
    public Widget createUIWidget() {
        Widget widget = super.createUIWidget();
        if (widget instanceof WidgetGroup group) {
            var size = group.getSize();
            var filterSlot = new SlotWidget(filterInventory, 0, size.width - 26, 5, true, true);
            filterSlot.setBackground(GuiTextures.SLOT, GuiTextures.FILTER_SLOT_OVERLAY);
            filterSlot.setChangeListener(this::updateFilter);
            group.addWidget(filterSlot);
        }
        return widget;
    }

    private CustomItemStackHandler createFilterInventory() {
        CustomItemStackHandler inventory = new CustomItemStackHandler();
        inventory.setFilter(item -> item.is(GTItems.ITEM_FILTER.asItem()) || item.is(GTItems.TAG_FILTER.asItem()));
        return inventory;
    }

    private void updateFilter() {
        itemFilter = filterInventory.getStackInSlot(0).isEmpty() ? null :
                ItemFilter.loadFilter(filterInventory.getStackInSlot(0));
        getWorkLogic().resetWorkLogic(radius, getLevel().getMinBuildHeight(), getLevel().getMaxBuildHeight(), 0,
                itemFilter);
    }

    private GTRecipe drillingFluidCost() {
        ContentListMap inputs = new ContentListMap();
        inputs.add(FluidRecipeCapability.CAP, FluidIngredient.of(GTMaterials.DrillingFluid.getFluid(
                drillingFluidPerTick)));
        return new GTRecipe(GTRecipeTypes.DUMMY_RECIPES, null, inputs, new ContentListMap(), new ContentListMap(),
                new ContentListMap(), new ArrayList<>(), new net.minecraft.nbt.CompoundTag(), 0, 1,
                GTRecipeTypes.DUMMY_RECIPES.getCategory());
    }
}
