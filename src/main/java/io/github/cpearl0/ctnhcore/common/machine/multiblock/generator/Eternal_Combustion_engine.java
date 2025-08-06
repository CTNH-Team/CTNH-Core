package io.github.cpearl0.ctnhcore.common.machine.multiblock.generator;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.gregtechceu.gtceu.api.capability.IEnergyContainer;
import com.gregtechceu.gtceu.api.capability.IWorkable;
import com.gregtechceu.gtceu.api.capability.forge.GTCapability;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.feature.IFancyUIMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IDisplayUIMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockDisplayText;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.misc.EnergyContainerList;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import earth.terrarium.adastra.api.planets.Planet;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.MachineUtils;
import io.github.cpearl0.ctnhcore.registry.CTNHMaterials;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.network.chat.Component;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Eternal_Combustion_engine extends WorkableElectricMultiblockMachine implements IWorkable {
    public Eternal_Combustion_engine(IMachineBlockEntity holder) {
        super(holder);
    }
    /// //////////////////////////////////////////////////////
    /// /        THE GOLDEN AGE WILL RETURN AGAIN!/       ////
    /// /////////////////////////////////////////////////////
    private int rate_mul = 0;

    private long lastOutputEnergy;
    //    @Override
    @Override
    public void onLoad() {
        super.onLoad();
        if (this.isFormed() && getLevel() instanceof ServerLevel serverLevel) {
            updateEnergyContainer();
            serverLevel.getServer().tell(new TickTask(0, this::updateTickSubscription));
        }
    }
    public int timer=0;
    //最好成型再用
    public void updateEnergyContainer() {
        List<IEnergyContainer> containers = new ArrayList<>();

        for (IMultiPart part : getParts())
            part.self().holder.self()
                    .getCapability(GTCapability.CAPABILITY_ENERGY_CONTAINER)
                    .ifPresent(containers::add);

        energyContainer = new EnergyContainerList(containers);
    }

    @Override
    public void onUnload() {
        super.onUnload();
        energyContainer = null;
        if (tickSubs != null) {
            tickSubs.unsubscribe();
            tickSubs = null;
        }
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
        if (tickSubs != null) {
            tickSubs.unsubscribe();
            tickSubs = null;
        }
        energyContainer = null;
    }
    /// ///////////////////////////////
    /// /        RUNNING LOGIC/       ////
    /// //////////////////////////
    @Nullable
    protected TickableSubscription tickSubs;
    protected void updateTickSubscription() {
        if (isFormed) {
            tickSubs = subscribeServerTick(tickSubs, this::tick);
        } else if (tickSubs != null) {
            tickSubs.unsubscribe();
            tickSubs = null;
        }
    }
    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        updateEnergyContainer();
        //计算发电效率
        if (getLevel() instanceof ServerLevel serverLevel) {
            serverLevel.getServer().tell(new TickTask(0, this::updateTickSubscription));
        }
    }
    public void tick(){

        lastOutputEnergy = 1;

        //获取时间
        if (MachineUtils.inputFluid(CTNHMaterials.NQ_END_OF_GASOLINE.getFluid((int)(5*(1+(double)Math.log(1+timer)/(360*20)))),this)) {
            timer+=1;
            lastOutputEnergy=(int)(1000000*(1+(double)(timer/(360*20))));
        }
        else
        {
            timer-=100;
        }
        timer=Math.max(0,timer);
        timer=Math.min(timer,36000*20);

        //计算发电功率
        energyContainer.addEnergy(lastOutputEnergy);

    }
    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);
        textList.add(Component.translatable("ctnh.eternal_engine.1",lastOutputEnergy));
        textList.add(Component.translatable("ctnh.eternal_engine.2",(double)timer/20));


    }

    @Override
    public int getProgress() {
        return 0;
    }

    @Override
    public int getMaxProgress() {
        return 0;
    }
    @Override
    public boolean isActive() {
        return true;
    }




}
