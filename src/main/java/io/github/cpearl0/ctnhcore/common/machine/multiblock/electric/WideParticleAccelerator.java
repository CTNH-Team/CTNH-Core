package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.capability.*;
import com.gregtechceu.gtceu.api.capability.forge.GTCapability;
import com.gregtechceu.gtceu.api.capability.recipe.*;
import com.gregtechceu.gtceu.api.gui.fancy.TabsWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.fancyconfigurator.CombinedDirectionalFancyConfigurator;
import com.gregtechceu.gtceu.api.machine.feature.IExplosionMachine;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockDisplayText;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.misc.EnergyContainerList;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import io.github.cpearl0.ctnhcore.common.gui.MachineModeFancyConfiguratorTest;
import io.github.cpearl0.ctnhcore.common.gui.WPAAcceleratorGui;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.generator.Arc_Generator;
import io.github.cpearl0.ctnhcore.registry.CTNHRecipeTypes;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.capability.IOpticalComputationProvider;
import com.gregtechceu.gtceu.api.capability.IOpticalComputationReceiver;
import com.gregtechceu.gtceu.api.capability.forge.GTCapability;
import com.gregtechceu.gtceu.api.capability.recipe.CWURecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeCapabilityHolder;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.common.blockentity.OpticalPipeBlockEntity;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import org.apache.commons.lang3.ObjectUtils;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.MachineUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.DoubleSupplier;


public class WideParticleAccelerator extends WorkableElectricMultiblockMachine implements ITieredMachine, IExplosionMachine {
    @Persisted
    @Getter
    public double nu_speed=0;
    @Persisted
    @Getter
    public double proton_speed=0;
    @Persisted
    @Getter
    public double electric_speed=0;
    public int max_speed = 5000;
    public int add_parallel_nu=0;
    public int add_parallel_proton=0;
    public  int add_parallel_element=0;
    public  int parallel_running=16;
    public  int getParallel_accelerate=1024;
    public String NU_SPEED="nu_speed";
    public String PROTON_SPEED="proton_speed";
    public String ELECTRIC_SPEED="electric_speed";
    public boolean isconnect=false;
    public boolean warring=false;
    public BlockPos pos;
    public Level level;
    public int anti_nu=0;
    public int anti_proton=0;
    public int anti_electirc=0;
    public double consume_mutiple=1.0;
    @Nullable
    protected TickableSubscription EnergySubs;
    @Persisted
    @Getter
    public int reverse=1;
    @Persisted
    public int nu_value=1;
    @Persisted
    public int proton_value=1;
    @Persisted
    public int electric_value=1;
    @Persisted
    public EnergyContainerList energyContainer;
    public WideParticleAccelerator(IMachineBlockEntity holder)
    {
        super(holder);
    }
    public DoubleSupplier get_nu = () ->(double)this.nu_speed/5000;
    public DoubleSupplier get_electric = () ->(double)this.electric_speed/5000;
    public DoubleSupplier get_proton = () ->(double)this.proton_speed/5000;

    public int GetParallel(MetaMachine machine, GTRecipe recipe, int parallelLimit)
    {
        int parallel= ParallelLogic.getParallelAmount(machine,recipe,(int) (parallelLimit/consume_mutiple));
        return parallel;
    }
    public long store_energy=0;
    public long max_energy=50000000000L;

    /// ///////////////////////////////
    /// /        tick逻辑/       ////
    /// //////////////////////////
    ///
    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        this.energyContainer = this.getEnergyContainer();
        if (getLevel() instanceof ServerLevel serverLevel) {
            serverLevel.getServer().tell(new TickTask(0, this::updateTempSubscription));
        }
    }

    @Override
    public void onLoad() {
        super.onLoad();

        if (getLevel() instanceof ServerLevel serverLevel) {
            this.energyContainer = this.getEnergyContainer();
            serverLevel.getServer().tell(new TickTask(0, this::updateTempSubscription));
        }
    }
    @Override
    public void onUnload() {
        super.onUnload();
        if (EnergySubs != null) {
            EnergySubs.unsubscribe();
            EnergySubs = null;
        }
    }
    protected void updateTempSubscription() {
        if (max_energy > store_energy&&isFormed) {
            EnergySubs = subscribeServerTick(EnergySubs, this::Energy_add);
        } else if (EnergySubs != null) {
            EnergySubs.unsubscribe();
            EnergySubs = null;
        }
    }
    protected void Energy_add()
    {
        if(store_energy<=max_energy&&this.energyContainer.getEnergyStored()>1)
        {

            var add_energy=Math.min(this.energyContainer.getEnergyStored(),max_energy-store_energy);
            this.energyContainer.removeEnergy(add_energy);
            store_energy+=add_energy;
        }

    }


    //初始化
    @Override
    public boolean beforeWorking(@Nullable GTRecipe recipe) {


        if(recipe.getType().equals(CTNHRecipeTypes.ACCELERATOR_DOWN))
        {
            if(recipe.data.getString("type").equals("nu"))
            {
                add_parallel_nu=-(int) (Math.sqrt(recipe.data.getDouble("speed")));
            }
            if(recipe.data.getString("type").equals("proton"))
            {
                add_parallel_proton=-(int) (Math.sqrt(recipe.data.getDouble("speed")));
            }
            if(recipe.data.getString("type").equals("element"))
            {
                add_parallel_element=-(int) (Math.sqrt(recipe.data.getDouble("speed")));
            }
        }
        return super.beforeWorking(recipe);
    }
    @Override
    public void afterWorking() {
        nu_speed+=add_parallel_nu;
        proton_speed+=add_parallel_proton;
        electric_speed+=add_parallel_element;

        add_parallel_element=add_parallel_proton=add_parallel_nu=0;
        nu_speed=Math.min(nu_speed,50000);
        proton_speed=Math.min(proton_speed,50000);
        electric_speed=Math.min(electric_speed,50000);
        nu_speed=Math.max(nu_speed,0);
        proton_speed=Math.max(proton_speed,0);
        electric_speed=Math.max(electric_speed,0);
        if(warring)
        {
            doExplosion(3f);
        }
        super.afterWorking();
        }

    public static ModifierFunction recipeModifier(MetaMachine machine, @NotNull GTRecipe recipe) {
        var hatchs=0;
        if (machine instanceof IMultiController controller) {
            if (controller.isFormed()) {
                int parallels = (Integer)controller.getParallelHatch()
                        .map(IParallelHatch::getCurrentParallel)
                        .orElse(0);
                if (parallels >= 0) {
                    hatchs=parallels;
                }

            }
        }

        if(machine instanceof WideParticleAccelerator wmachine){
            List<Content> itemList = new ArrayList<>();
            var level = wmachine.self().getLevel();
            var pos = wmachine.self().getPos();

            pos = MachineUtils.getOffset(wmachine,0, -20, -6);
            if (getMachine(level, pos) instanceof Superconducting_Penning_Trap gmachine) {
                wmachine.pos = pos;
                wmachine.level = level;
                wmachine.isconnect = true;
            }
            else
            {
                wmachine.isconnect=false;
            }
            //速度不足
            if(recipe.data.getString("type").equals("nu") && recipe.data.getDouble("speed")>=wmachine.nu_speed)
            {
                return ModifierFunction.NULL;
            }
            if(recipe.data.getString("type").equals("element") && recipe.data.getDouble("speed")>=wmachine.electric_speed)
            {
                return ModifierFunction.NULL;
            }
            if(recipe.data.getString("type").equals("proton") && recipe.data.getDouble("speed")>=wmachine.proton_speed)
            {
                return ModifierFunction.NULL;
            }
            //计算并行
            int parallel=16;

            var random = Math.random()*0.25;
            var eut_consume=recipe.getTickInputContents(EURecipeCapability.CAP).stream()
                    .map(Content::getContent)
                    .mapToLong(EURecipeCapability.CAP::of)
                    .sum();
            double total_eut= (wmachine.nu_speed+ wmachine.proton_speed+ wmachine.electric_speed)/1000;
            //计算能耗，减速模式根本没写笑死
            var true_eut=eut_consume*(1+total_eut);
            recipe.tickInputs.put(EURecipeCapability.CAP, EURecipeCapability.makeEUContent((long) true_eut));
            parallel = ParallelLogic.getParallelAmount(machine,recipe,16);
            if(hatchs>0)parallel=ParallelLogic.getParallelAmount(machine,recipe,hatchs);





            if(recipe.getType().equals(CTNHRecipeTypes.ACCELERATOR_DOWN))total_eut=(wmachine.nu_speed+ wmachine.proton_speed+ wmachine.electric_speed)/250;
            //加速粒子模式逻辑
            if(recipe.data.getString("type").equals("addnu")||recipe.data.getString("type").equals("addproton")||recipe.data.getString("type").equals("addelement"))
            {
                if(recipe.getType().equals(CTNHRecipeTypes.ACCELERATOR_DOWN)) {
                    parallel = -1;
                    if(hatchs>0)parallel=-hatchs*10;
                }
                else {
                    parallel = ParallelLogic.getParallelAmount(machine, recipe, 1024);
                    if(hatchs>0)parallel=hatchs*10;
                }

                if(recipe.data.getString("type").equals("addnu"))
                    wmachine.add_parallel_nu=parallel;
                if(recipe.data.getString("type").equals("addproton"))
                    wmachine.add_parallel_proton=parallel;
                if(recipe.data.getString("type").equals("addelement"))
                    wmachine.add_parallel_element=parallel;

                return ModifierFunction.builder()
                        .parallels(parallel)
                        .eutMultiplier(Math.abs(parallel))
                        .build();
            }
            //暗物质开始
            if(recipe.data.getString("darkmatter").equals("nu"))
            {
                if(getMachine(level, pos) instanceof Superconducting_Penning_Trap gmachine)
                {
                    if(wmachine.isconnect&&gmachine.isconnect)
                    {
                        gmachine.anti_nu+= (int) (1000*parallel*(1-random*Math.sqrt(0.05*parallel)));
                    }
                    else{
                        wmachine.warring=true;
                    }
                }
                else{
                    wmachine.warring=true;
                }

            }
            if(recipe.data.getString("darkmatter").equals("proton"))
            {
                if(getMachine(level, pos) instanceof Superconducting_Penning_Trap gmachine)
                {
                    if(wmachine.isconnect&&gmachine.isconnect)
                    {
                        gmachine.anti_proton+= (int) (1000*parallel*(1-random*Math.sqrt(0.05*parallel)));
                        gmachine.anti_nu+=(int)(1000*parallel*(random*random));
                    }
                    else{
                        wmachine.warring=true;
                    }
                }
                else{
                    wmachine.warring=true;
                }

            }
            if(recipe.data.getString("darkmatter").equals("electric"))
            {
                if(getMachine(level, pos) instanceof Superconducting_Penning_Trap gmachine)
                {
                    if(wmachine.isconnect&&gmachine.isconnect)
                    {
                        gmachine.anti_electron+= (int) (1000*parallel*(1-random*Math.sqrt(0.05*parallel)));
                    }
                else{
                        wmachine.warring=true;
                    }
                }
                else{
                    wmachine.warring=true;
                }

            }
            //暗物质结束
            var muti=1.0;
            if(wmachine.nu_speed+wmachine.electric_speed+ wmachine.proton_speed>10000) {
                 muti = 0.1;
            }
            else
            {
                 muti=1-(recipe.data.getDouble("speed")-(wmachine.nu_speed+ wmachine.proton_speed+ wmachine.electric_speed)/2000);
            }



            return ModifierFunction.builder()
                    .parallels(parallel)
                    .inputModifier(ContentModifier.multiplier(parallel))
                    .outputModifier(ContentModifier.multiplier(parallel))
                    .eutMultiplier(parallel)
                    .durationMultiplier(Math.max(0.1,muti))
                    .build();
        }
        return ModifierFunction.NULL;
    }
    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);
        if(isconnect)
        {
            textList.add(textList.size(),Component.translatable("ctnh.connect"));
        }

        textList.add(textList.size(),Component.translatable("ctnh.wpa.power",(double)this.store_energy/100000000,(double)this.max_energy/100000000));
        textList.add(textList.size(),Component.translatable("ctnh.accelerator.nu_speed",String.format("%.2f",nu_speed)));
        textList.add(textList.size(),Component.translatable("ctnh.accelerator.proton_speed",String.format("%.2f",proton_speed)));
        textList.add(textList.size(),Component.translatable("ctnh.accelerator.electric_speed",String.format("%.2f",electric_speed)));
        textList.add(textList.size(),Component.translatable("ctnh.accelerator.consume",String.format("%.2f",(nu_speed+proton_speed+electric_speed)/2000+1)));
    }
    @Override
    public void attachSideTabs(TabsWidget sideTabs) {
        sideTabs.setMainTab(this);

        if (this.getRecipeTypes().length > 0) {
            sideTabs.attachSubTab(new WPAAcceleratorGui(this));
        }
        var directionalConfigurator = CombinedDirectionalFancyConfigurator.of(self(), self());
        if (directionalConfigurator != null)
            sideTabs.attachSubTab(directionalConfigurator);
    }
    @Override
    public void saveCustomPersistedData(@NotNull CompoundTag tag, boolean forDrop) {
        super.saveCustomPersistedData(tag, forDrop);
        if (!forDrop) {
        tag.putDouble(NU_SPEED,nu_speed);
        tag.putDouble(PROTON_SPEED,proton_speed);
        tag.putDouble(ELECTRIC_SPEED,electric_speed);
        }
    }

    @Override
    public void loadCustomPersistedData(@NotNull CompoundTag tag) {
        super.loadCustomPersistedData(tag);
        nu_speed=tag.contains(NU_SPEED)?tag.getDouble(NU_SPEED):0;
        proton_speed=tag.contains(PROTON_SPEED)?tag.getDouble(PROTON_SPEED):0;
        electric_speed=tag.contains(ELECTRIC_SPEED)?tag.getDouble(ELECTRIC_SPEED):0;
    }

}
