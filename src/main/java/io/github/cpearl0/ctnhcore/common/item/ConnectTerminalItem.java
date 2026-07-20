package io.github.cpearl0.ctnhcore.common.item;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import com.ctnhlang.CN;
import com.ctnhlang.EN;

import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.SpacePhotovoltaicBaseStation;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.generator.PhotoVoltaicDroneStation;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ConnectTerminalItem extends ComponentItem {

    @CN("已经绑定的坐标：(%s,%s,%s)")
    @EN("Bound coordinates: (%s,%s,%s)")
    public static Lang itemTerminalLocation;


    @CN("已清除坐标！")
    @EN("Coordinates cleared!")
    public static Lang itemTerminalSuccessClear;


    @CN("已经获取坐标!")
    @EN("Coordinates acquired!")
    public static Lang itemTerminalSuccessGet;


    @CN("已成功写入坐标!")
    @EN("Coordinates written successfully!")
    public static Lang itemTerminalSuccessWrite;


    @CN("使用右键绑定光伏模块控制器，然后再右键将控制器和光伏基站绑定\nshift+右键任意方块清除坐标")
    @EN("Right-click a photovoltaic module controller to bind it, then right-click again to bind the controller to the photovoltaic station\nShift+right-click any block to clear the coordinates")
    public static Lang itemTerminalTips;



    public ConnectTerminalItem(Properties properties) {
        super(properties
                .rarity(Rarity.EPIC));
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        Player player = context.getPlayer();
        if (player == null) return InteractionResult.PASS;
        if (context.getPlayer() != null && context.getPlayer().isShiftKeyDown()) {
            CompoundTag newTag = new CompoundTag();
            stack.setTag(newTag);
            player.displayClientMessage(itemTerminalSuccessClear.translate(), true);

            // 也可以在这里处理右键点击方块的逻辑
            return InteractionResult.PASS;
        }
        Level level = context.getLevel();

        BlockPos blockPos = context.getClickedPos();
        IMultiController controller = getMachineController(level, blockPos);
        var machine = getMachine(level, blockPos);
        if (machine == null) return InteractionResult.PASS;
        CompoundTag nbt = stack.getOrCreateTag();
        if (machine instanceof PhotoVoltaicDroneStation) {
            nbt.putInt("block_x", blockPos.getX());
            nbt.putInt("block_y", blockPos.getY());
            nbt.putInt("block_z", blockPos.getZ());
            player.displayClientMessage(itemTerminalSuccessGet.translate(), true);
        }
        if (machine instanceof SpacePhotovoltaicBaseStation spb) {
            if (nbt.contains("block_x")) {

                var pos = new BlockPos(nbt.getInt("block_x"), nbt.getInt("block_y"), nbt.getInt("block_z"));
                spb.Drone_location = pos;
                player.displayClientMessage(itemTerminalSuccessWrite.translate(), true);
            }
        }
        return InteractionResult.SUCCESS;
    }

    private IMultiController getMachineController(Level level, BlockPos blockPos) {
        if (MetaMachine.getMachine(level, blockPos) instanceof IMultiController controller) {
            return controller;
        }
        return null;
    }

    public static @Nullable MetaMachine getMachine(BlockGetter level, BlockPos pos) {
        BlockEntity var3 = level.getBlockEntity(pos);
        if (var3 instanceof IMachineBlockEntity machineBlockEntity) {
            return machineBlockEntity.getMetaMachine();
        } else {
            return null;
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents,
                                TooltipFlag isAdvanced) {
        CompoundTag nbt = stack.getOrCreateTag();
        tooltipComponents.add(itemTerminalTips.translate());
        if (nbt.contains("block_x")) {

            tooltipComponents.add(
                    itemTerminalLocation.translate( String.format("%d", nbt.getInt("block_x")),
                            String.format("%d", nbt.getInt("block_y")), String.format("%d", nbt.getInt("block_y"))));
        }
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced); // 调用父类方法以处理原版提示信息
    }
}
