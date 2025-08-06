package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.capability.recipe.EURecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.fancy.FancyMachineUIWidget;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyUIProvider;
import com.gregtechceu.gtceu.api.gui.fancy.TabsWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.fancyconfigurator.CombinedDirectionalFancyConfigurator;
import com.gregtechceu.gtceu.api.machine.fancyconfigurator.MachineModeFancyConfigurator;
import com.gregtechceu.gtceu.api.machine.feature.IFancyUIMachine;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.ingredient.EnergyStack;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.lowdragmc.lowdraglib.gui.editor.ColorPattern;
import com.lowdragmc.lowdraglib.gui.texture.*;
import com.lowdragmc.lowdraglib.gui.widget.*;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.lowdragmc.lowdraglib.syncdata.managed.IManagedVar;
import io.github.cpearl0.ctnhcore.common.gui.MachineModeFancyConfiguratorTest;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.MachineUtils;
import io.github.cpearl0.ctnhcore.registry.CTNHGuiTextures;
import io.github.cpearl0.ctnhcore.registry.CTNHMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.DoubleSupplier;

public class CryotheumFreezer extends WorkableElectricMultiblockMachine implements ITieredMachine, IFancyUIMachine {
    public CryotheumFreezer(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }
    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            CryotheumFreezer.class, WorkableElectricMultiblockMachine.MANAGED_FIELD_HOLDER);
    @Persisted
    public int a = 3;
    @Persisted public double speed_up=1.0;
    @Persisted public double energy_muti=1.0;
    @Persisted public int parallel_muti=1;
    public DoubleSupplier JEIProgress = () -> (double) Math.abs(speed_up-1) / (double) 2.5F;
    public DoubleSupplier JEIProgress2 = () -> (double) Math.abs(energy_muti-1) / (double) 2.5F;
    public DoubleSupplier JEIProgress3 = () -> (double) Math.abs(parallel_muti) / (double) 10F;
    @Persisted
    public long used_energy=0;

    public long store_energy_now=0;
    @Persisted
    public long target=100000L;
    public MutableComponent provider_a() {
        return Component.translatable("ctnh.testui.0", a);
    }

    @Override
    public boolean beforeWorking(@Nullable GTRecipe recipe) {
        var tier = getTier();
        if (MachineUtils.inputFluid(CTNHMaterials.Cryotheum.getFluid((int) (Math.pow(4, Math.max((tier - 4),0)) * 10)), this)) {
            used_energy+= (long) (Math.pow(4, Math.max((tier - 4),0)) )* 10;
            if(used_energy>=target)
            {
                a+=1;
                used_energy-=target;
                target*=4;
            }
            return super.beforeWorking(recipe);
        }
        getRecipeLogic().interruptRecipe();
        return false;
    }
    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        var tier = getTier();

    }

    public static ModifierFunction recipeModifier(MetaMachine machine, GTRecipe recipe) {
        if(machine instanceof CryotheumFreezer cmachine) {
            int parallel = ParallelLogic.getParallelAmount(machine, recipe, (int)(2*Math.pow(2, cmachine.parallel_muti)));
            var reduce = new ContentModifier(1/ cmachine.energy_muti * parallel, 0);
            if (parallel == 0)
                return ModifierFunction.NULL;
            var eut_consume=recipe.getTickInputContents(EURecipeCapability.CAP).stream()
                    .map(Content::getContent)
                    .map(EURecipeCapability.CAP::of)
                    .mapToLong(EnergyStack::voltage)
                    .sum();

            return ModifierFunction.builder()
                    .eutModifier(reduce)
                    .inputModifier(ContentModifier.multiplier(parallel))
                    .outputModifier(ContentModifier.multiplier(parallel))
                    .durationMultiplier(1 / cmachine.speed_up)
                    .parallels(parallel)
                    .build();
        }
        return ModifierFunction.NULL;
    }

    @Override
    public void attachSideTabs(TabsWidget sideTabs) {
        sideTabs.setMainTab(this);

        if (this.getRecipeTypes().length > 0) {
            sideTabs.attachSubTab(new MachineModeFancyConfiguratorTest(this));
        }
        var directionalConfigurator = CombinedDirectionalFancyConfigurator.of(self(), self());
        if (directionalConfigurator != null)
            sideTabs.attachSubTab(directionalConfigurator);
    }
    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);
        textList.add(textList.size(),Component.translatable("ctnh.freezeui.5",used_energy,target));
    }




}
