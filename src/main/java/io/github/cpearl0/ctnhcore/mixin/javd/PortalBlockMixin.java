package io.github.cpearl0.ctnhcore.mixin.javd;
import org.spongepowered.asm.mixin.Unique;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import com.unrealdinnerbone.javd.JAVDRegistry;
import com.unrealdinnerbone.javd.block.PortalBlock;
import com.unrealdinnerbone.javd.util.TelerportUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = PortalBlock.class)
public class PortalBlockMixin {

    @Key("message.ctnhcore.portal.invalid_dimension")
    @CN("该传送门只能在主世界使用")
    @EN("This portal can only be used in the Overworld")
    @Unique
    private static Lang messagePortalInvalidDimension;



    /**
     * @author
     * @reason
     */
    @Overwrite
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand,
                                 BlockHitResult hit) {
        // ---- 客户端直接吞掉交互 防止误放 ----
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        boolean isInVoid = level.dimensionTypeId().equals(JAVDRegistry.Keys.DIMENSION_TYPE);
        boolean isOverworld = level.dimension() == Level.OVERWORLD;

        // ---- 从虚空返回主世界 ----
        if (isInVoid) {
            TelerportUtils.teleport(player, Level.OVERWORLD, pos, false);
            return InteractionResult.CONSUME;
        }

        // ---- 仅允许主世界进入虚空 ----
        if (isOverworld) {
            TelerportUtils.teleport(player, JAVDRegistry.Keys.LEVEL, pos, true);
        } else {
            // ---- 其他维度禁止 ----
            player.displayClientMessage(
                    messagePortalInvalidDimension.translate(),
                    true);
        }

        return InteractionResult.CONSUME;
    }
}
