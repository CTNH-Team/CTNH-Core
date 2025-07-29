package io.github.cpearl0.ctnhcore.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.capability.IControllable;
import com.gregtechceu.gtceu.api.capability.IOpticalComputationProvider;
import com.gregtechceu.gtceu.api.machine.ConditionalSubscriptionHandler;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.mo_guang.ctpp.common.machine.multiblock.MachineUtils;
import io.github.cpearl0.ctnhcore.utils.MathUtils;
import lombok.Getter;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static io.github.cpearl0.ctnhcore.registry.CTNHDamageTypes.COMPUTATION_SACRIFICE;


public class ScalableReservoirComputingMachine extends WorkableElectricMultiblockMachine implements IOpticalComputationProvider, IControllable {


    public ScalableReservoirComputingMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
        subscriptionHandler = new ConditionalSubscriptionHandler(this,this::tick,this::isSubscriptionActive);
    }
    /// ///////////////////////
    //      Machine Life
    /// ///////////////////////
    @Getter
    AABB aabb;
    final ConditionalSubscriptionHandler subscriptionHandler;

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
        subscriptionHandler.unsubscribe();
        aabb = null;
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        aabb = new AABB(
                MachineUtils.getOffset(this,5,-2,0),
                MachineUtils.getOffset(this,-5,8,8)
        );
        subscriptionHandler.updateSubscription();
    }



    protected boolean isSubscriptionActive() {
        return isFormed();
    }

    /// ///////////////////////
    //      Working
    /// ///////////////////////
    public record SacrificeValue(int computation,int duration){}
    public static final HashMap<EntityType<? extends LivingEntity>,SacrificeValue> recipes = new HashMap<>();   //add externally

    enum State{
        SEARCHING,
        LOADING
    }
    State state = State.SEARCHING;

    long energyToLoad;

    private List<LivingEntity> lockedSacrifices = new ArrayList<>();
    public boolean updateSacrifice() {
        var level = getLevel();
        if (level == null || level.isClientSide() || level.getGameTime() % 20 != 0) return false;
        lockedSacrifices = level.getEntitiesOfClass(LivingEntity.class, aabb);
        return !lockedSacrifices.isEmpty();
    }
    //Setup,修改duration,maxCWUt
    public void setupSacrifice(){
        int n=0;
        long c=1;
        int d=0;
        for (var sacrifice : lockedSacrifices){
            if(!sacrifice.isAlive())continue;

            @SuppressWarnings("unchecked")
            EntityType<? extends LivingEntity> type = (EntityType<? extends LivingEntity>) sacrifice.getType();
            var recipe = recipes.getOrDefault(type,null);
            if(recipe==null)continue;

            sacrifice.hurt(COMPUTATION_SACRIFICE.source(getLevel()),Float.MAX_VALUE);
            if(sacrifice.isAlive())continue;

            n++;
            c*=recipe.computation();
            d+=recipe.duration();
        }
        if(n==0)return;
        c/= (long) n * n;

        if(MathUtils.fastLog2(c) > tier-1)return;

        if(c!=maxCWUt){
            duration = d;
            maxCWUt = (int) c;
        }
        else duration += d;
        isLoaded = false;
        computingEUt = 32*c*c;
        energyToLoad = computingEUt*16*d; //设定每tick lv16a算力=1,算力翻倍,能量*4

    }
    public boolean loadSacrifice(){
        return (energyToLoad -= energyContainer.removeEnergy(energyToLoad))<=0;
    }
    public void tick(){
        switch (state){
            case SEARCHING:
                if(updateSacrifice()){
                    setupSacrifice();
                    state = State.LOADING;
                }
                break;
            case LOADING:
                if(loadSacrifice()){
                    isLoaded = true;
                    state = State.SEARCHING;
                }
                break;
            default:
                return;
        }
    }
    public boolean drainEnergyIfEnough(long energyToDrain){
        return energyContainer.getEnergyStored() >= energyToDrain &&
                energyContainer.removeEnergy(energyToDrain) >= energyToDrain;
    }

    /// ///////////////////////
    //      Conmputation
    /// ///////////////////////

    private int maxCWUt,duration,lastCWUt;
    private long computingEUt;
    private boolean isLoaded = false;
    public boolean computeCondition(){
        return isLoaded &&
                duration > 0;
    }
    @Override
    public int requestCWUt(int cwut, boolean simulate, @NotNull Collection<IOpticalComputationProvider> seen) {
        seen.add(this);
        if(computeCondition()){
            if(!simulate && drainEnergyIfEnough(computingEUt)) {
                duration--;
                return lastCWUt = Math.min(cwut,maxCWUt);
            };
            return Math.min(cwut,maxCWUt);
        }
        return 0;
    }

    @Override
    public int getMaxCWUt(@NotNull Collection<IOpticalComputationProvider> seen) {
        seen.add(this);
        return computeCondition()?maxCWUt:0;
    }

    @Override
    public boolean canBridge(@NotNull Collection<IOpticalComputationProvider> seen) {
        return false;
    }


    /// ///////////////////////
    //          GUI
    /// ///////////////////////

    @Override
    public void addDisplayText(@NotNull List<Component> textList) {
        if(isFormed()&&getLevel() instanceof ServerLevel){
            textList.add(Component.translatable("ctnhcore.src."+state.name().toLowerCase()));
            if(!isActive()){
                textList.add(Component.translatable("ctnhcore.src.sacrifice_locked",FormattingUtil.formatNumbers(lockedSacrifices.size())));
            }
            if(isLoaded){
                textList.add(Component.translatable("ctnhcore.src.wetware_duration",
                        FormattingUtil.formatNumbers(duration)));
                textList.add(Component.translatable("gtceu.multiblock.computation.max",
                        FormattingUtil.formatNumbers(maxCWUt)));
                textList.add(Component.translatable("gtceu.multiblock.computation.usage",
                        FormattingUtil.formatNumbers(lastCWUt)));
            }else{
                if(energyToLoad>0){
                    textList.add(Component.translatable("ctnhcore.src.loading_info.0"));
                    textList.add(Component.translatable("ctnhcore.src.loading_info.1",FormattingUtil.formatNumbers(energyToLoad)));
                }
            }
        }
        super.addDisplayText(textList);
    }

    /// ///////////////////////
    //      Appearance
    /// ///////////////////////
    @Override
    public boolean isActive() {
        return isFormed() && state == State.LOADING;
    }
}
