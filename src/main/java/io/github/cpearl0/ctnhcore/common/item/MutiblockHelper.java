package io.github.cpearl0.ctnhcore.common.item;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.component.IInteractionItem;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.electric.SpacePhotovoltaicBaseStation;
import io.github.cpearl0.ctnhcore.common.machine.multiblock.generator.PhotoVoltaicDroneStation;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class MutiblockHelper extends ComponentItem implements IInteractionItem {
    public static class BlockData {
        int x, y, z;
        char symbol;

        public BlockData(int x, int y, int z, char symbol) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.symbol = symbol;
        }
    }
    public  MutiblockHelper(Properties properties) {
        super(properties);
    }
    public Level level;
    public int state=0;
    public boolean check_form(CompoundTag nbt)
    {
        String blockid;
        List<BlockData> blockArray = new ArrayList<>(); // 使用 ArrayList 代替 JavaScript 数组
        Map<String, Character> legend = new HashMap<>(); legend = new HashMap<>(); // 使用 HashMap 代替 JavaScript 对象
        String SYMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz#@";
        int symbolIndex = 0;
        int locatex=1;
        int locatey=1;
        int locatez=1;
        var x1=nbt.getInt("block_x_f");
        var x2=nbt.getInt("block_x_s");
        if(x1>x2)locatex=-1;

        var y1 = nbt.getInt("block_y_f");
        var y2 = nbt.getInt("block_y_s");
        if (y1 > y2) locatey = -1;
        var z1 = nbt.getInt("block_z_f");
        var z2 = nbt.getInt("block_z_s");
        if (z1 > z2) locatez = -1;
        int stepX = (x2 > x1) ? 1 : -1;
        int stepY = (y2 > y1) ? 1 : -1;
        int stepZ = (z2 > z1) ? 1 : -1;

// 遍历所有点
        for (int z = z1; z<=z2; z += 1) {
            for (int y = y1; y<=y2; y += 1) {
                for (int x = x1; x<=x2; x += 1) {
                    // 处理点 (x, y, z)
                    var Block = Objects.requireNonNull(level).getBlockState(new BlockPos(x,y,z)).getBlock();
                    var state=Objects.requireNonNull(level).getBlockState(new BlockPos(x,y,z));
                    String blockId = Block.toString().split("\\{|\\}")[1];

                    if (!legend.containsKey(blockId)) {
                        if (blockId.equals("minecraft:air")) {
                            legend.put(blockId, (SYMBOLS.charAt(52)));
                        } else if (blockId.equals("minecraft:oak_log")) {
                            legend.put(blockId, (SYMBOLS.charAt(53)));
                        } else {
                            legend.put(blockId, (SYMBOLS.charAt(symbolIndex)));
                            symbolIndex = (symbolIndex + 1) % SYMBOLS.length();
                        }
                    }

                    blockArray.add(new BlockData(x, y, z, legend.get(blockId)));
                }
            }
        }
        List<String> slices = new ArrayList<>();

        // 1. 生成每一层 (z 轴) 的字符串
        for (int z = z1; z<=z2; z += 1) {
            StringBuilder slice = new StringBuilder();
            for (int y = y1;y<=y2; y += 1) {
                StringBuilder row = new StringBuilder();
                for (int x = x1; x<=x2; x += 1) {
                    // 查找当前坐标的方块符号
                    int finalX = x;
                    int finalY = y;
                    int finalZ = z;
                    var blockOpt = blockArray.stream()
                            .filter(b -> b.x == finalX && b.y == finalY && b.z == finalZ)
                            .findFirst();
                    row.append(blockOpt.map(b -> b.symbol).orElse('?'));
                }
                // 添加行（最后一行不加逗号）
                if (y == y2) {
                    slice.append("\"").append(row).append("\"");
                } else {
                    slice.append("\"").append(row).append("\", ");
                }
            }
            slices.add(slice.toString());
        }

        // 2. 构建最终字符串
        StringBuilder finalString = new StringBuilder();
        finalString.append("\n.pattern(definition -> FactoryBlockPattern.start()\n");

        // 添加 .aisle() 层
        for (String slice : slices) {
            finalString.append("    .aisle(").append(slice).append(")\n");
        }

        // 添加 .where() 规则
        for (Map.Entry<String, Character> entry : legend.entrySet()) {
            String blockId = entry.getKey();
            char symbol = entry.getValue();
            String pred;

            switch (symbol) {
                case '@':
                    pred = "    .where(\"@\", Predicates.controller(Predicates.blocks(definition.get())))\n";
                    break;
                case '#':
                    pred = "    .where(\"#\", Predicates.any())\n";
                    break;
                default:
                    pred = String.format("    .where(\"%c\", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(\"%s\"))))\n", symbol, blockId);
                    break;
            }
            finalString.append(pred);
        }
        finalString.append("    .build())");




        System.out.println(finalString);
        return true;
    }
    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        Player player = context.getPlayer();
        if (player == null) return InteractionResult.PASS;
        level = context.getLevel();
        if (level instanceof ServerLevel) {
            return InteractionResult.PASS;  // 客户端不进行实际操作
        }
        BlockPos blockPos = context.getClickedPos();
        CompoundTag nbt = stack.getOrCreateTag();
        if (context.getPlayer() != null && context.getPlayer().isShiftKeyDown()) {
            if(state!=2) {
                CompoundTag newTag = new CompoundTag();
                stack.setTag(newTag);
                player.displayClientMessage(Component.translatable("ctnh.terminal.success_clear"), true);
            }
            else
            {
                check_form(nbt);
                CompoundTag newTag = new CompoundTag();
                stack.setTag(newTag);
                state=0;
            }
            // 也可以在这里处理右键点击方块的逻辑
            return InteractionResult.PASS;
        }
        if(state==0)
        {
            nbt.putInt("block_x_f", blockPos.getX());
            nbt.putInt("block_y_f", blockPos.getY());
            nbt.putInt("block_z_f", blockPos.getZ());
            player.displayClientMessage(Component.translatable("ctnh.terminal.success_get"), true);
            state=1;
        }
        else if (state==1) {
            nbt.putInt("block_x_s", blockPos.getX());
            nbt.putInt("block_y_s", blockPos.getY());
            nbt.putInt("block_z_s", blockPos.getZ());
            player.displayClientMessage(Component.translatable("ctnh.terminal.success_get"), true);
            state=2;
        }

        return InteractionResult.SUCCESS;
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        CompoundTag nbt = stack.getOrCreateTag();
        tooltipComponents.add(Component.translatable("ctnh.terminal.mutiblockhelper.tips"));
        if(nbt.contains("block_x_f"))
        {

            tooltipComponents.add(Component.translatable("ctnh.terminal.location",String.format("%d",nbt.getInt("block_x_f")),String.format("%d",nbt.getInt("block_y_f")),String.format("%d",nbt.getInt("block_y_f"))));
        }
        if(nbt.contains("block_x_s"))
        {

            tooltipComponents.add(Component.translatable("ctnh.terminal.location",String.format("%d",nbt.getInt("block_x_s")),String.format("%d",nbt.getInt("block_y_s")),String.format("%d",nbt.getInt("block_y_s"))));
        }
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced); // 调用父类方法以处理原版提示信息

    }
}
