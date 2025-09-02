package io.github.cpearl0.ctnhcore.common.machine.multiblock.magic;

import com.gregtechceu.gtceu.api.capability.IParallelHatch;
import com.gregtechceu.gtceu.api.data.worldgen.bedrockfluid.BedrockFluidVeinSavedData;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IExplosionMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.lowdragmc.lowdraglib.syncdata.managed.IManagedVar;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.MachineUtils;
import io.github.cpearl0.ctnhcore.registry.CTNHMaterials;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.Nullable;

public class ManaForceTransformer extends WorkableElectricMultiblockMachine implements IExplosionMachine{
    public ManaForceTransformer(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    private Level level;
    MFTSavedData parallel_data;
    int parallel=1;
    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        level = getLevel();
        if (!level.dimension().location().getPath().equals("mythicbotany:alfheim")) {
            doExplosion(10f);
        }
        else
        {
            parallel_data=MFTSavedData.get(level);
        }

    }
    public long Logistic(int x)
    {
        float t=x/100000;
        return (int) (1000 * (1.0f - (float) Math.pow(1.0f - t, 2)));
    }
    public long cost(int x)
    {
        return (int) x*x*x;
    }
    @Override
    public boolean beforeWorking(@Nullable GTRecipe recipe) {
        var tier = getTier();
        int num=parallel_data.mftValue;
        if (MachineUtils.canInputFluid(CTNHMaterials.Mana.getFluid((int) num*num),this)){
            num=(int) Math.sqrt(num);
        }
        if (MachineUtils.canInputFluid(CTNHMaterials.Mana.getFluid((int) cost(num)),this)){
            if (1==1) {
                var controller=this;
                if (controller.isFormed()) {
                    int parallels = (Integer)controller.getParallelHatch()
                            .map(IParallelHatch::getCurrentParallel)
                            .orElse(0);
                    if (parallels >= 0) {
                        parallel=parallels;
                    }

                }
            }
            num+=parallel;
            return super.beforeWorking(recipe);
        }

        return false;
    }
    public static ModifierFunction recipeModifier(MetaMachine machine, GTRecipe recipe) {
        if(machine instanceof ManaForceTransformer tmachine) {
            var muti=tmachine.Logistic(tmachine.parallel_data.mftValue);
            return ModifierFunction.builder()
                    .outputModifier(ContentModifier.multiplier(muti))
                    .build();
        }
        return ModifierFunction.NULL;
    }


    public static class MFTSavedData extends SavedData {
        private int mftValue = 0;

        // 默认构造函数（用于创建新数据）
        public MFTSavedData() {
            super();
        }

        // 带Tag的构造函数（用于加载数据）
        public MFTSavedData(CompoundTag tag) {
            this();
            if (tag !=null) {
                mftValue = tag.getInt("mftValue");
            }
        }

        @Override
        public CompoundTag save(CompoundTag tag) {
            tag.putInt("mftValue", mftValue);
            return tag;
        }

        public int getGlobalValue() {
            return mftValue;
        }

        public void setGlobalValue(int value) {
            this.mftValue = value;
            setDirty(); // 标记为需要保存
        }

        // 获取实例的静态方法
        public static MFTSavedData get(Level level) {
            if (level.isClientSide) {
                throw new RuntimeException("Don't access this client-side!");
            }

            // 获取或创建SavedData实例
            MinecraftServer server = level.getServer();
            return server.overworld().getDataStorage().computeIfAbsent(
                    MFTSavedData::new,  // 创建新实例的函数
                    () -> new MFTSavedData(),  // 如果不存在则创建新实例
                    "mana_force_transformer_data"  // 存储的键名
            );
        }
    }
}

