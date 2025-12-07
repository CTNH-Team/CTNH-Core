package io.github.cpearl0.ctnhcore.api.jade;

import com.gregtechceu.gtceu.api.blockentity.MetaMachineBlockEntity;
import com.gregtechceu.gtceu.api.capability.GTCapabilityHelper;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.integration.jade.provider.CapabilityBlockProvider;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.api.recipe.multithread.MultiThreadRecipeLogic;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class ThreadStatusProvider extends CapabilityBlockProvider<MultiThreadRecipeLogic> {
    public ThreadStatusProvider() {
        super(CTNHCore.id("thread_status_provider"));
    }

    @Override
    protected @Nullable MultiThreadRecipeLogic getCapability(Level level, BlockPos pos, @Nullable Direction side) {
        var recipeLogic = GTCapabilityHelper.getRecipeLogic(level, pos, side);
        if(recipeLogic instanceof MultiThreadRecipeLogic multiThreadRecipeLogic)
            return multiThreadRecipeLogic;
        return null;
    }

    @Override
    protected void write(CompoundTag data, MultiThreadRecipeLogic capability) {
        data.putInt("enabled", capability.getEnabledThreadNum());
        data.putInt("active", capability.getActiveThreadNum());
    }

    @Override
    protected void addTooltip(CompoundTag capData, ITooltip tooltip, Player player, BlockAccessor block, BlockEntity blockEntity, IPluginConfig config) {
        int enabled = capData.getInt("enabled");
        int active = capData.getInt("active");
        if(block.getBlockEntity() instanceof MetaMachineBlockEntity metaMachineBlockEntity &&
                metaMachineBlockEntity.getMetaMachine() instanceof IMultiController controller &&
            controller.isFormed())
        {
            if(active != 0){
                Component actives = Component.literal(FormattingUtil.formatNumbers(active))
                        .withStyle(ChatFormatting.DARK_PURPLE);
                tooltip.add(Component.translatable("%s个线程正在工作", actives));
            }
            else if(enabled != 0){
                Component enableds = Component.literal(FormattingUtil.formatNumbers(enabled))
                        .withStyle(ChatFormatting.DARK_PURPLE);
                tooltip.add(Component.translatable("已启用%s个线程", enableds));
            }
        }


    }
}
