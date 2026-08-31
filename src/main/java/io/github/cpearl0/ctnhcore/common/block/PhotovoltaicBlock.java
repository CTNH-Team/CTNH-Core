package io.github.cpearl0.ctnhcore.common.block;

import io.github.cpearl0.ctnhcore.common.block.blockdata.IPBData;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.generator.PhotovoltaicPowerStationMachine;

import com.gregtechceu.gtceu.api.pattern.MultiblockWorldSavedData;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PhotovoltaicBlock extends Block {

    public final IPBData data;

    public PhotovoltaicBlock(BlockBehaviour.Properties properties, IPBData ipbData) {
        super(properties);
        this.data = ipbData;
    }

    public IPBData getData() {
        return this.data;
    }

    /**
     * 手持修复材料右键面板时，转交给所属的光伏发电站控制器处理。
     */
    @Override
    @SuppressWarnings("deprecation")
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos,
                                          @NotNull Player player, @NotNull InteractionHand hand,
                                          @NotNull BlockHitResult hit) {
        if (level.isClientSide) {
            return super.use(state, level, pos, player, hand, hit);
        }
        var controller = findOwningPowerStation(level, pos);
        if (controller != null) {
            var result = controller.tryRepairByHand(player, hand);
            if (result != InteractionResult.PASS) return result;
        }
        return super.use(state, level, pos, player, hand, hit);
    }

    /**
     * 通过多方块索引反查这个位置属于哪台光伏发电站。
     */
    @Nullable
    private static PhotovoltaicPowerStationMachine findOwningPowerStation(Level level, BlockPos pos) {
        if (!(level instanceof ServerLevel serverLevel)) return null;

        var mwsd = MultiblockWorldSavedData.getOrCreate(serverLevel);
        for (var multiblockState : mwsd.getControllersInChunk(new ChunkPos(pos))) {
            if (!multiblockState.isPosInCache(pos)) continue;
            if (multiblockState.getController() instanceof PhotovoltaicPowerStationMachine machine) {
                return machine;
            }
        }
        return null;
    }

    public static enum PhotovoltaicType implements StringRepresentable, IPBData {

        ENERGETIC_PHOTOVOLTAIC_BLOCK(1, 1),
        PULSATING_PHOTOVOLTAIC_BLOCK(2, 2),
        VIBRANT_PHOTOVOLTAIC_BLOCK(3, 4),
        PHOTON_PRESS_COND_BLOCK(4, 8),
        Photonic_Well_Photovoltaic(5, 32);

        private final int tier;
        private final int heatlevel;

        public int getTier() {
            return this.tier;
        }

        public int getheatlevel() {
            return this.heatlevel;
        }

        private PhotovoltaicType(int tier, int heatlevel) {
            this.tier = tier;
            this.heatlevel = heatlevel;
        }

        public @NotNull String getPhotovoltaicName() {
            return this.name().toLowerCase();
        }

        public @NotNull String getSerializedName() {
            return this.getPhotovoltaicName();
        }
    }
}
